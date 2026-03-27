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
</p>

<p align="center"><strong>English</strong> | <a href="es/">Espanol</a></p>

---

## What is it?

**Showroom Docs MCP Server** is a [Model Context Protocol (MCP)](https://modelcontextprotocol.io/) server built with [Quarkus](https://quarkus.io/) that indexes and exposes documentation from the **"IA Development From Zero To Hero"** workshop (Neuralbank) along with official documentation from **9 Red Hat products**, enabling [OpenShift Lightspeed](https://docs.redhat.com/en/documentation/red_hat_openshift_lightspeed/1.0) to answer questions based on that context.

## Features

- **Intelligent search** by keywords across 3MB+ of indexed documentation
- **4 MCP Tools** exposed via Streamable HTTP for OLS integration
- **35 documents** covering the workshop + Red Hat products + Developer products
- **Container image** ready for OpenShift deployment
- **Helm chart** for declarative installation

## Indexed Documentation

| Source | Content |
|--------|---------|
| Neuralbank Workshop | Business case, MCP agents, Golden Path, DevSpaces, Keycloak, Connectivity Link, MCP Inspector, Deploy, OpenTelemetry, RAG |
| OpenShift Service Mesh 3.3 | About, Installing, Observability, Gateways, Updating |
| Connectivity Link 1.3 | Discover, Install, Configure, Observe |
| Developer Hub 1.9 | Install, Configure, Auth, GitHub, MCP Tools |
| OpenShift Lightspeed 1.0 | Install, Configure, Operate, Troubleshoot |
| OpenShift Observability 1 | Overview hub |
| OpenTelemetry 3.9 | Install, Collector, Instrumentation |
| OpenShift Pipelines 1.21 | Install, Pipelines as Code, CI/CD |
| API Management 1 | Getting started, Administering |
| OpenShift AI Cloud Service 1 | Data Science, Model Serving, Llama Stack |

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
          - name: llama-32-3b-instruct
            parameters:
              maxTokensForResponse: 4096
        name: red_hat_openshift_ai
        type: rhoai_vllm
        url: 'http://llama-32-3b-instruct-openai.my-first-model.svc.cluster.local/v1'
  mcpServers:
    - name: kubernetes-mcp
      timeout: 5
      url: 'http://kubernetes-mcp-server.istio-system.svc.cluster.local:8080/mcp'
    - name: showroom-docs-mcp
      timeout: 10
      url: 'http://showroom-docs-mcp.openshift-lightspeed.svc.cluster.local:8080/mcp'
  ols:
    conversationCache:
      postgres:
        maxConnections: 2000
        sharedBuffers: 256MB
      type: postgres
    defaultModel: llama-32-3b-instruct
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

## Screenshots

### OpenShift Topology with MCP Server Deployed

<p align="center">
  <img src="{{ '/assets/images/topology-overview.png' | relative_url }}" alt="OpenShift Topology showing showroom-docs-mcp deployment" width="100%"/>
</p>

### Lightspeed Chat Using Documentation Tools

<p align="center">
  <img src="{{ '/assets/images/topology-chat.png' | relative_url }}" alt="Lightspeed chat answering questions from MCP documentation" width="100%"/>
</p>

### MCP Tool Output - Knowledge Base Summary

<p align="center">
  <img src="{{ '/assets/images/lightspeed-summary.png' | relative_url }}" alt="getDocSummary tool output in Lightspeed" width="100%"/>
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

[Full installation guide](quickstart/) -- [Architecture](architecture/) -- [OLSConfig configuration](configuration/)
