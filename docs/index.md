---
layout: default
title: Home
---

<p align="center">
  <img src="{{ '/assets/images/logo.svg' | relative_url }}" alt="Showroom Docs MCP" width="200"/>
</p>

<h1 align="center">Showroom Docs MCP Server</h1>

<p align="center">
  <strong>Quarkus MCP Server for OpenShift Lightspeed</strong><br/>
  <em>Intelligent documentation at your fingertips via chat</em>
</p>

<p align="center">
  <a href="https://quay.io/repository/maximilianopizarro/showroom-docs-mcp">
    <img src="https://img.shields.io/badge/quay.io-showroom--docs--mcp-blueviolet?logo=redhat" alt="Quay.io"/>
  </a>
  <img src="https://img.shields.io/badge/Quarkus-3.27.3-blue?logo=quarkus" alt="Quarkus"/>
  <img src="https://img.shields.io/badge/MCP-1.8.1-purple" alt="MCP Server"/>
  <img src="https://img.shields.io/badge/Java-21-orange?logo=openjdk" alt="Java 21"/>
  <img src="https://img.shields.io/badge/license-Apache--2.0-green" alt="License"/>
  <a href="https://artifacthub.io/packages/helm/showroom-docs-mcp/showroom-docs-mcp">
    <img src="https://img.shields.io/endpoint?url=https://artifacthub.io/badge/repository/showroom-docs-mcp" alt="Artifact Hub"/>
  </a>
</p>

<p align="center"><strong>English</strong> | <a href="es/">Espanol</a></p>

---

## What is it?

**Showroom Docs MCP Server** is a [Model Context Protocol (MCP)](https://modelcontextprotocol.io/) server built with [Quarkus](https://quarkus.io/) that indexes and exposes documentation from the **"IA Development From Zero To Hero"** workshop (Neuralbank) along with a **partial snapshot** of official documentation from **9 Red Hat products**, enabling [OpenShift Lightspeed](https://docs.redhat.com/en/documentation/red_hat_openshift_lightspeed/1.0) to answer questions based on that context.

> **Note:** The Red Hat product documentation included is a **partial extract** and may not reflect the latest updates. Red Hat publishes updates to its official documentation on a regular basis. Always refer to [docs.redhat.com](https://docs.redhat.com/) for the most current and complete information.

## Features

- **Intelligent search** by keywords across 3MB+ of indexed documentation
- **4 MCP Tools** exposed via Streamable HTTP for OLS integration
- **46 documents** covering the workshop + Red Hat products + Developer products
- **Auto-sanitized** PDF content: page numbers, legal notices, and artifacts removed
- **Quick-start examples** from developers.redhat.com with CLI commands and code
- **Smart truncation** at paragraph boundaries to avoid cut responses
- **Container image** ready for OpenShift deployment
- **Helm chart** for declarative installation

## Indexed Documentation

<p align="center">
  <img src="{{ '/assets/images/content-sources.svg' | relative_url }}?v=2" alt="Knowledge base: 46 indexed documents" width="100%"/>
</p>

| Source | Documents | Content |
|--------|-----------|---------|
| Neuralbank Workshop | 27 | Business case, MCP agents, Golden Path, DevSpaces, Keycloak, Connectivity Link, RAG, LLM, Agents, GPU, Platform Eng., Data Eng. |
| Red Hat Products (docs.redhat.com) | 9 | Service Mesh 3.3, Connectivity Link 1.3, Developer Hub 1.9, Lightspeed 1.0, Observability, OpenTelemetry 3.9, Pipelines 1.21, API Mgmt, OpenShift AI *(partial extract)* |
| Developer Products (developers.redhat.com) | 10 | Quarkus, OpenShift, RHEL, Dev Spaces, OpenShift AI, Developer Hub, Ansible, OpenShift Local, Connectivity Link, 3scale |

> **Disclaimer:** The product documentation is a partial snapshot included for contextual assistance. Red Hat official documentation is updated frequently. For the latest and most complete information, visit [docs.redhat.com](https://docs.redhat.com/) and [developers.redhat.com](https://developers.redhat.com/).

## Installation Steps

### Step 1: Add Helm Repository and Install

```bash
helm repo add showroom-docs-mcp \
  https://maximilianopizarro.github.io/showroom-docs-mcp/

helm repo update

helm install showroom-docs-mcp showroom-docs-mcp/showroom-docs-mcp \
  --namespace openshift-lightspeed \
  --create-namespace \
  --set image.pullPolicy=Always
```

### Step 2: Verify the Pod is Running

```bash
oc get pods -n openshift-lightspeed -l app=showroom-docs-mcp
# Expected: 1/1 Running
```

### Step 3: Create LLM Credentials (if needed)

```bash
oc create secret generic ols-llm-credentials \
  -n openshift-lightspeed \
  --from-literal=apitoken=<your-api-token>
```

### Step 4: Configure OLSConfig

```yaml
apiVersion: ols.openshift.io/v1alpha1
kind: OLSConfig
metadata:
  name: cluster
spec:
  featureGates:
    - MCPServer
  llm:
    providers:
      - credentialsSecretRef:
          name: ols-llm-credentials
        models:
          - name: granite-3-8b-instruct
            parameters:
              maxTokensForResponse: 8192
        name: red_hat_openshift_ai
        type: rhoai_vllm
        url: 'http://granite-3-8b-instruct.my-model.svc.cluster.local/v1'
  mcpServers:
    - name: showroom-docs-mcp
      timeout: 10
      url: 'http://showroom-docs-mcp.openshift-lightspeed.svc.cluster.local:8080/mcp'
  ols:
    conversationCache:
      postgres:
        maxConnections: 2000
        sharedBuffers: 256MB
      type: postgres
    defaultModel: granite-3-8b-instruct
    defaultProvider: red_hat_openshift_ai
    deployment:
      api:
        replicas: 1
      console:
        replicas: 1
      dataCollector: {}
      database:
        replicas: 1
      llamaStack: {}
      mcpServer: {}
    logLevel: INFO
    userDataCollection: {}
  olsDataCollector:
    logLevel: INFO
```

> **Important**: Use `/mcp` (Streamable HTTP), **not** `/mcp/sse`.

```bash
oc apply -f cluster-ols.yml
```

### Step 5: Verify MCP Integration

```bash
oc logs -n openshift-lightspeed deploy/lightspeed-app-server \
  -c lightspeed-service-api | grep "tools from MCP"

# Expected:
# Loaded 4 tools from MCP server 'showroom-docs-mcp'
```

### Step 6: Test with Lightspeed

Open the OpenShift web console, click the Lightspeed chat icon, and ask any question about the indexed documentation.

## Architecture

<p align="center">
  <img src="{{ '/assets/images/architecture-diagram.svg' | relative_url }}?v=2" alt="Architecture: OpenShift Lightspeed + MCP" width="100%"/>
</p>

## How It Works

<p align="center">
  <img src="{{ '/assets/images/mcp-tools-flow.svg' | relative_url }}?v=2" alt="MCP Tool Selection Flow" width="100%"/>
</p>

## Example Questions for Lightspeed

Once deployed, open the OpenShift Lightspeed chat and ask:

- *"How do I install OpenShift Service Mesh 3.3?"*
- *"Explain the architecture of Red Hat Developer Hub"*
- *"What are the steps to deploy a model with OpenShift AI?"*
- *"How do I configure Connectivity Link gateway policies?"*
- *"What is the Neuralbank workshop about?"*
- *"How do I build an MCP agent with Quarkus?"*
- *"How do I set up Pipelines as Code with Tekton?"*
- *"How does OpenShift Lightspeed OLSConfig work?"*
- *"What is RAG and how does it work with vector databases?"*

## Official Red Hat Documentation

> The versions listed below reflect the documentation snapshot included in this server. Visit each link for the latest official content — Red Hat updates its documentation regularly.

| Product | Version | Documentation |
|---------|---------|---------------|
| OpenShift Service Mesh | 3.3 | [docs.redhat.com](https://docs.redhat.com/en/documentation/red_hat_openshift_service_mesh/3.3) |
| Connectivity Link | 1.3 | [docs.redhat.com](https://docs.redhat.com/en/documentation/red_hat_connectivity_link/1.3) |
| Developer Hub | 1.9 | [docs.redhat.com](https://docs.redhat.com/en/documentation/red_hat_developer_hub/1.9) |
| OpenShift Lightspeed | 1.0 | [docs.redhat.com](https://docs.redhat.com/en/documentation/red_hat_openshift_lightspeed/1.0) |
| OpenShift Observability | 1 | [docs.redhat.com](https://docs.redhat.com/en/documentation/red_hat_openshift_observability/1) |
| OpenTelemetry | 3.9 | [docs.redhat.com](https://docs.redhat.com/en/documentation/red_hat_build_of_opentelemetry/3.9) |
| OpenShift Pipelines | 1.21 | [docs.redhat.com](https://docs.redhat.com/en/documentation/red_hat_openshift_pipelines/1.21) |
| API Management | 1 | [docs.redhat.com](https://docs.redhat.com/en/documentation/red_hat_openshift_api_management/1) |
| OpenShift AI | 1 | [docs.redhat.com](https://docs.redhat.com/en/documentation/red_hat_openshift_ai_cloud_service/1) |

## Screenshots

<p align="center">
  <img src="{{ '/assets/images/topology-lightspeed-chat.png' | relative_url }}" alt="Lightspeed Chat with MCP" width="100%"/>
</p>

<p align="center"><em>OpenShift Lightspeed answering questions using MCP tools</em></p>

<p align="center">
  <img src="{{ '/assets/images/mcp-inspector-tools.png' | relative_url }}" alt="MCP Inspector" width="100%"/>
</p>

<p align="center"><em>MCP Inspector showing the 4 available tools</em></p>

[View all screenshots](screenshots/)

---

[Full installation guide](quickstart/) -- [Architecture](architecture/) -- [OLSConfig configuration](configuration/) -- [Screenshots](screenshots/)
