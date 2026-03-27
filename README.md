<p align="center">
  <img src="docs/assets/images/logo.svg" alt="Showroom Docs MCP" width="180"/>
</p>

<h1 align="center">Showroom Docs MCP Server</h1>

<p align="center">
  <strong>Quarkus MCP Server for OpenShift Lightspeed</strong>
</p>

<p align="center">
  <a href="https://quay.io/repository/maximilianopizarro/showroom-docs-mcp">
    <img src="https://img.shields.io/badge/quay.io-showroom--docs--mcp-blueviolet?logo=redhat" alt="Quay.io"/>
  </a>
  <img src="https://img.shields.io/badge/Quarkus-3.27.3-blue?logo=quarkus" alt="Quarkus"/>
  <img src="https://img.shields.io/badge/MCP_Server-1.8.1-purple" alt="MCP"/>
  <img src="https://img.shields.io/badge/Java-21-orange?logo=openjdk" alt="Java"/>
  <a href="https://maximilianopizarro.github.io/showroom-docs-mcp">
    <img src="https://img.shields.io/badge/docs-GitHub_Pages-blue?logo=github" alt="Docs"/>
  </a>
  <img src="https://img.shields.io/badge/license-Apache--2.0-green" alt="License"/>
</p>

---

[MCP (Model Context Protocol)](https://modelcontextprotocol.io/) server built with [Quarkus](https://quarkus.io/) that indexes documentation from the **"IA Development From Zero To Hero"** workshop (Neuralbank) and official documentation from **9 Red Hat products**, exposing them as tools for [OpenShift Lightspeed](https://docs.redhat.com/en/documentation/red_hat_openshift_lightspeed/1.0).

## Architecture

```
  User <-> OLS Console <-> OLS API Server
                               |
                 +-------------+-------------+
                 |             |              |
           LLM Provider  kubernetes-mcp  showroom-docs-mcp
           (Llama 3.2)                    (this project)
```

## MCP Tools

| Tool | Description |
|------|-------------|
| `searchDocs` | Keyword search across all indexed documentation |
| `listDocSections` | List all available document sections |
| `getDocSection` | Get full content of a section (supports fuzzy topic matching) |
| `getDocSummary` | Knowledge base overview with statistics and example queries |

## Indexed Content (25 documents, ~3MB)

| Source | Topics |
|--------|--------|
| Neuralbank Workshop | Business case, MCP agents, Golden Path, DevSpaces, Keycloak, Connectivity Link, MCP Inspector, Deploy, OpenTelemetry, RAG (16 files) |
| OpenShift Service Mesh 3.3 | About, Installing, Observability, Gateways |
| Connectivity Link 1.3 | Discover, Install, Configure, Observe |
| Developer Hub 1.9 | Install, Configure, Auth, MCP Tools |
| OpenShift Lightspeed 1.0 | Install, Configure, Operate |
| OpenShift Observability 1 | Overview |
| OpenTelemetry 3.9 | Install, Collector, Instrumentation |
| OpenShift Pipelines 1.21 | Install, Pipelines as Code, CI/CD |
| API Management 1 | Getting started, Administering |
| OpenShift AI Cloud Service 1 | Data Science, Model Serving, Llama Stack |

## Quick Start

### 1. Deploy with Helm (recommended)

```bash
helm repo add showroom-docs-mcp \
  https://maximilianopizarro.github.io/showroom-docs-mcp/

helm install showroom-docs-mcp showroom-docs-mcp/showroom-docs-mcp \
  --namespace openshift-lightspeed \
  --set image.pullPolicy=Always
```

### 2. Configure OLSConfig

Apply the provided `cluster-ols.yml` or add the MCP server to your existing OLSConfig:

```bash
# Option A: Use the provided sample (edit LLM provider as needed)
oc apply -f k8s/cluster-ols.yml

# Option B: Add to your existing OLSConfig
```

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
  # ... rest of your OLS configuration
```

> **Important**: Use `/mcp` (Streamable HTTP), not `/mcp/sse`. OLS uses POST requests which require the Streamable HTTP endpoint.

### 3. Verify

```bash
# Check MCP pod is running
oc get pods -n openshift-lightspeed -l app=showroom-docs-mcp

# Check OLS loaded the tools
oc logs -n openshift-lightspeed deploy/lightspeed-app-server \
  -c lightspeed-service-api | grep "tools from MCP"
# Expected: Loaded 4 tools from MCP server 'showroom-docs-mcp'
```

### 4. Test in Lightspeed

Open the OpenShift console and ask Lightspeed:

- *"How do I install OpenShift Service Mesh 3.3?"*
- *"Explain the architecture of Red Hat Developer Hub"*
- *"What are the steps to deploy a model with OpenShift AI?"*
- *"What is the Neuralbank workshop about?"*
- *"How do I build an MCP agent with Quarkus?"*

## Local Development

```bash
./mvnw quarkus:dev
# MCP at http://localhost:8080/mcp
```

## Build

```bash
podman build -t quay.io/maximilianopizarro/showroom-docs-mcp:latest -f Containerfile .
podman push quay.io/maximilianopizarro/showroom-docs-mcp:latest
```

## Documentation

Full documentation with step-by-step setup: [https://maximilianopizarro.github.io/showroom-docs-mcp](https://maximilianopizarro.github.io/showroom-docs-mcp)

## License

Apache License 2.0
