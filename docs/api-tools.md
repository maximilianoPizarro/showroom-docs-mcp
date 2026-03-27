---
layout: default
title: API Tools
permalink: /api-tools/
---

# MCP Tools API

<p align="center">
  <img src="{{ '/assets/images/logo.svg' | relative_url }}" alt="Logo" width="80"/>
</p>

<p align="center"><strong>English</strong> | <a href="{{ '/es/api-tools/' | relative_url }}">Espanol</a></p>

The server exposes 4 tools via Model Context Protocol (MCP).

---

## searchDocs

Search all indexed documentation using keywords. This is the primary tool for answering questions about Red Hat products and the Neuralbank workshop.

<p align="center">
  <img src="{{ '/assets/images/lightspeed-search.png' | relative_url }}" alt="searchDocs tool output" width="100%"/>
</p>

**Input:**
```json
{
  "query": "string - Keywords to search for"
}
```

**Output:** Top 5 most relevant documentation sections with relevance score.

**Example Queries:**

| Query | What it finds |
|-------|---------------|
| `deploy model openshift ai` | Steps to deploy ML models with OpenShift AI |
| `developer hub architecture plugins` | Developer Hub architecture, plugin system |
| `service mesh install istio` | Service Mesh 3.3 installation guide |
| `connectivity link gateway policy` | Gateway and DNS policies in Connectivity Link |
| `pipelines as code tekton` | Tekton Pipelines as Code configuration |
| `lightspeed olsconfig configure` | OLSConfig API reference and setup |
| `neuralbank workshop mcp agent` | Neuralbank workshop MCP agent building |
| `keycloak user management` | Keycloak identity and user management |
| `rag vector database` | RAG implementation with vector databases |
| `opentelemetry collector instrumentation` | OpenTelemetry setup and instrumentation |

**Coverage:** Neuralbank workshop + 9 Red Hat products (Service Mesh, Connectivity Link, Developer Hub, OLS, Observability, OpenTelemetry, Pipelines, API Management, OpenShift AI).

---

## getDocSection

Get the full content of a specific documentation file. Supports both exact filenames and topic-based fuzzy matching.

**Input:**
```json
{
  "fileName": "string - Exact filename OR topic keywords"
}
```

**Output:** Full markdown content (truncated at 8000 chars for large documents).

**Examples:**

| Input | Resolves to |
|-------|-------------|
| `22-developer-hub.md` | Exact file match |
| `developer hub` | Fuzzy match → `22-developer-hub.md` |
| `service mesh` | Fuzzy match → `20-service-mesh.md` |
| `openshift ai` | Fuzzy match → `28-openshift-ai.md` |
| `pipelines` | Fuzzy match → `26-pipelines.md` |
| `lightspeed` | Fuzzy match → `23-openshift-lightspeed.md` |
| `connectivity link` | Fuzzy match → `21-connectivity-link.md` |
| `neuralbank` | Fuzzy match → `01-business-case.md` |

**Available Documents:**

| File | Topic |
|------|-------|
| `00-index.md` | Workshop index |
| `01-business-case.md` | Neuralbank business case |
| `02-workshop-overview.md` | Workshop overview |
| `03-configure-environment.md` | Environment configuration |
| `04-agents-intro.md` | Introduction to MCP agents |
| `05-golden-path.md` | Golden Path with Developer Hub |
| `06-devspaces.md` | Red Hat DevSpaces |
| `07-keycloak-user-management.md` | Keycloak user management |
| `08-build-mcp-agent.md` | Building the MCP Agent |
| `09-connectivity-link.md` | Connectivity Link (workshop) |
| `10-mcp-inspector.md` | MCP Inspector |
| `11-deploy-integrate.md` | Deploy and integrate |
| `12-opentelemetry.md` | OpenTelemetry (workshop) |
| `13-rag-intro.md` | Introduction to RAG |
| `14-vector-databases.md` | Vector databases |
| `15-simple-rag.md` | Simple RAG implementation |
| `20-service-mesh.md` | OpenShift Service Mesh 3.3 |
| `21-connectivity-link.md` | Connectivity Link 1.3 |
| `22-developer-hub.md` | Developer Hub 1.9 |
| `23-openshift-lightspeed.md` | OpenShift Lightspeed 1.0 |
| `24-observability.md` | OpenShift Observability |
| `25-opentelemetry.md` | OpenTelemetry 3.9 |
| `26-pipelines.md` | OpenShift Pipelines 1.21 |
| `27-api-management.md` | API Management |
| `28-openshift-ai.md` | OpenShift AI Cloud Service |

---

## listDocSections

List all available documentation files with their titles.

**Input:** none

**Output:** Table of contents organized by category (Workshop / Red Hat Products).

<p align="center">
  <img src="{{ '/assets/images/lightspeed-doc-index.png' | relative_url }}" alt="listDocSections tool output" width="100%"/>
</p>

---

## getDocSummary

Get a summary of the entire knowledge base including statistics and example questions.

**Input:** none

**Output:** Overview of indexed content, product versions, and suggested queries.

<p align="center">
  <img src="{{ '/assets/images/lightspeed-summary.png' | relative_url }}" alt="getDocSummary tool output" width="100%"/>
</p>

---

## MCP Endpoint

```
Streamable HTTP: http://<host>:8080/mcp
SSE:             http://<host>:8080/mcp/sse
Health:          http://<host>:8080/q/health/ready
```

---

## Example Questions for OpenShift Lightspeed

Once the MCP server is registered in OLSConfig, you can ask Lightspeed these types of questions:

### Red Hat Products
- "How do I install OpenShift Service Mesh 3.3?"
- "Explain the architecture of Red Hat Developer Hub"
- "What are the steps to deploy a model with OpenShift AI?"
- "How do I configure Connectivity Link gateway policies?"
- "How do I set up OpenTelemetry collectors for distributed tracing?"
- "What is OpenShift Lightspeed and how do I configure OLSConfig?"
- "How do I create a Tekton pipeline with Pipelines as Code?"
- "How do I manage APIs with Red Hat OpenShift API Management?"
- "What monitoring tools are available in OpenShift Observability?"

### Neuralbank Workshop
- "What is the Neuralbank workshop about?"
- "How do I build an MCP agent with Quarkus?"
- "Explain the Golden Path template in Developer Hub"
- "How is Keycloak used for user management in the workshop?"
- "What is RAG and how does it work with vector databases?"
- "How do I use DevSpaces for development?"
- "How do I deploy and integrate the MCP agent?"

## Official Red Hat Documentation Links

| Product | Documentation |
|---------|---------------|
| OpenShift Service Mesh 3.3 | [docs.redhat.com](https://docs.redhat.com/en/documentation/red_hat_openshift_service_mesh/3.3) |
| Connectivity Link 1.3 | [docs.redhat.com](https://docs.redhat.com/en/documentation/red_hat_connectivity_link/1.3) |
| Developer Hub 1.9 | [docs.redhat.com](https://docs.redhat.com/en/documentation/red_hat_developer_hub/1.9) |
| OpenShift Lightspeed 1.0 | [docs.redhat.com](https://docs.redhat.com/en/documentation/red_hat_openshift_lightspeed/1.0) |
| OpenShift Observability 1 | [docs.redhat.com](https://docs.redhat.com/en/documentation/red_hat_openshift_observability/1) |
| OpenTelemetry 3.9 | [docs.redhat.com](https://docs.redhat.com/en/documentation/red_hat_build_of_opentelemetry/3.9) |
| OpenShift Pipelines 1.21 | [docs.redhat.com](https://docs.redhat.com/en/documentation/red_hat_openshift_pipelines/1.21) |
| API Management 1 | [docs.redhat.com](https://docs.redhat.com/en/documentation/red_hat_openshift_api_management/1) |
| OpenShift AI 1 | [docs.redhat.com](https://docs.redhat.com/en/documentation/red_hat_openshift_ai_cloud_service/1) |
