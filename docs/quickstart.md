---
layout: default
title: Quick Start
permalink: /quickstart/
---

# Quick Start

<p align="center">
  <img src="{{ '/assets/images/logo.svg' | relative_url }}" alt="Logo" width="80"/>
</p>

<p align="center"><strong>English</strong> | <a href="{{ '/es/quickstart/' | relative_url }}">Espanol</a></p>

## Prerequisites

- OpenShift cluster with OLS (OpenShift Lightspeed) installed
- `oc` CLI authenticated to the cluster
- `helm` CLI (optional, for Helm-based installation)

## Option 1: Helm Chart (recommended)

```bash
helm repo add showroom-docs-mcp \
  https://maximilianopizarro.github.io/showroom-docs-mcp/

helm install showroom-docs-mcp showroom-docs-mcp/showroom-docs-mcp \
  --namespace openshift-lightspeed \
  --set image.pullPolicy=Always
```

### Helm Chart Values

The chart includes a `values.schema.json` for input validation. Key configurable values:

| Parameter | Default | Description |
|-----------|---------|-------------|
| `replicaCount` | `1` | Number of replicas |
| `image.repository` | `quay.io/maximilianopizarro/showroom-docs-mcp` | Container image |
| `image.tag` | `latest` | Image tag |
| `image.pullPolicy` | `Always` | Pull policy (`Always`, `IfNotPresent`, `Never`) |
| `namespace` | `openshift-lightspeed` | Target namespace |
| `service.type` | `ClusterIP` | Service type |
| `service.port` | `8080` | Service port |
| `resources.requests.cpu` | `100m` | CPU request |
| `resources.requests.memory` | `256Mi` | Memory request |
| `resources.limits.cpu` | `500m` | CPU limit |
| `resources.limits.memory` | `512Mi` | Memory limit |
| `inspector.enabled` | `true` | Enable MCP Inspector deployment |
| `inspector.route.timeout` | `300s` | Route timeout for MCP connections |
| `olsConfig.enabled` | `false` | Enable OLSConfig integration |
| `olsConfig.mcpServerName` | `showroom-docs-mcp` | MCP server name in OLSConfig |
| `olsConfig.mcpServerTimeout` | `10` | MCP timeout (seconds) |

Example with custom values:

```bash
helm install showroom-docs-mcp showroom-docs-mcp/showroom-docs-mcp \
  --namespace openshift-lightspeed \
  --set image.pullPolicy=Always \
  --set resources.limits.memory=1Gi \
  --set replicaCount=2
```

## Option 2: Direct Manifests

```bash
# Clone the repository
git clone https://github.com/maximilianoPizarro/showroom-docs-mcp.git
cd showroom-docs-mcp

# Apply manifests
oc apply -f k8s/deployment.yaml
```

## Configure OLSConfig

Add the MCP server to your `OLSConfig`. Note: use `/mcp` (Streamable HTTP), not `/mcp/sse`:

```yaml
apiVersion: ols.openshift.io/v1alpha1
kind: OLSConfig
metadata:
  name: cluster
spec:
  featureGates:
    - MCPServer
  mcpServers:
    - name: showroom-docs-mcp
      timeout: 10
      url: 'http://showroom-docs-mcp.openshift-lightspeed.svc.cluster.local:8080/mcp'
```

Apply:

```bash
oc apply -f cluster-ols.yml
```

The OLS operator will automatically restart pods with the new configuration.

## Verify

```bash
# Check that the pod is running
oc get pods -n openshift-lightspeed -l app=showroom-docs-mcp

# Verify health
oc exec -n openshift-lightspeed deploy/showroom-docs-mcp -- \
  curl -s http://localhost:8080/q/health/ready

# Check logs (should show no errors)
oc logs -n openshift-lightspeed -l app=showroom-docs-mcp

# Verify MCP tools are loaded by OLS
oc logs -n openshift-lightspeed deploy/lightspeed-app-server \
  -c lightspeed-service-api | grep "showroom-docs-mcp"
```

## Test

Once deployed, open the OpenShift Lightspeed chat in the console and try these questions:

### Red Hat Product Questions
- *"How do I install OpenShift Service Mesh 3.3?"*
- *"Explain the architecture of Red Hat Developer Hub"*
- *"What are the steps to deploy a model with OpenShift AI?"*
- *"How do I configure Connectivity Link gateway policies?"*
- *"How do I set up OpenTelemetry collectors for distributed tracing?"*
- *"How does OpenShift Lightspeed OLSConfig work?"*
- *"How do I create a Tekton pipeline with Pipelines as Code?"*
- *"How do I manage APIs with Red Hat OpenShift API Management?"*
- *"What monitoring tools are available in OpenShift Observability?"*

### Neuralbank Workshop Questions
- *"What is the Neuralbank workshop about?"*
- *"How do I build an MCP agent with Quarkus?"*
- *"Explain the Golden Path template in Developer Hub"*
- *"How is Keycloak used for user management in the workshop?"*
- *"What is RAG and how does it work with vector databases?"*
- *"How do I use DevSpaces for development?"*

### Architecture Overview

<p align="center">
  <img src="{{ '/assets/images/architecture-diagram.svg' | relative_url }}?v=2" alt="Architecture: OpenShift Lightspeed + MCP" width="100%"/>
</p>

## Local Development

```bash
cd showroom-docs-mcp
./mvnw quarkus:dev

# MCP available at http://localhost:8080/mcp
# SSE transport at http://localhost:8080/mcp/sse
# Health at http://localhost:8080/q/health/ready
```
