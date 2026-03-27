---
layout: default
title: Architecture
permalink: /architecture/
---

# Architecture

<p align="center">
  <img src="{{ '/assets/images/logo.svg' | relative_url }}" alt="Logo" width="80"/>
</p>

<p align="center"><strong>English</strong> | <a href="{{ '/es/architecture/' | relative_url }}">Espanol</a></p>

## OpenShift Topology

The following screenshot shows the showroom-docs-mcp deployment in the OpenShift topology view, alongside the Lightspeed components:

<p align="center">
  <img src="{{ '/assets/images/topology-overview.png' | relative_url }}" alt="OpenShift Topology showing showroom-docs-mcp deployment" width="100%"/>
</p>

## Overview Diagram

```
┌─────────────────────────────────────────────────────────────┐
│                    OpenShift Cluster                         │
│                                                             │
│  ┌──────────┐    ┌──────────────┐    ┌──────────────────┐  │
│  │   User    │───▶│ OLS Console  │───▶│  OLS API Server  │  │
│  │ (Browser) │    │  (Frontend)  │    │                  │  │
│  └──────────┘    └──────────────┘    └────────┬─────────┘  │
│                                               │             │
│                         ┌─────────────────────┼──────┐      │
│                         │                     │      │      │
│                         ▼                     ▼      ▼      │
│                  ┌────────────┐  ┌─────────────┐ ┌───────┐ │
│                  │  LLM       │  │ kubernetes  │ │showroo│ │
│                  │  Provider  │  │   -mcp      │ │m-docs │ │
│                  │ (Llama 3.2)│  │             │ │ -mcp  │ │
│                  └────────────┘  └─────────────┘ └───┬───┘ │
│                                                      │      │
│                                          ┌───────────┴───┐  │
│                                          │  Embedded     │  │
│                                          │  Documentation│  │
│                                          │  (35 files)   │  │
│                                          └───────────────┘  │
└─────────────────────────────────────────────────────────────┘
```

## Components

### Showroom Docs MCP Server

- **Runtime**: Quarkus 3.27.3 with Java 21
- **Protocol**: MCP via Streamable HTTP (`/mcp`) and SSE (`/mcp/sse`)
- **Content**: 35 embedded markdown documents (~3MB)
- **Port**: 8080

### Data Flow

1. The user asks a question in the OLS console
2. OLS API Server receives the query and sends it to the LLM
3. The LLM can invoke tools from both MCP servers:
   - `kubernetes-mcp`: for cluster state queries (pods, services, namespaces)
   - `showroom-docs-mcp`: for documentation queries (Red Hat products, workshop)
4. The MCP server searches indexed documents and returns relevant sections
5. The LLM generates a contextualized response using the retrieved documentation

### MCP Tools

| Tool | Description | Input |
|------|-------------|-------|
| `searchDocs` | Keyword search across all documentation | `query: string` |
| `listDocSections` | List all available document sections | (no parameters) |
| `getDocSection` | Get full content of a specific section (supports fuzzy matching) | `fileName: string` |
| `getDocSummary` | Get knowledge base overview and example questions | (no parameters) |

### Tool Selection by the LLM

The LLM decides which tools to call based on the question:

| Question Type | Expected Tool |
|---------------|---------------|
| "How do I install Service Mesh?" | `searchDocs` → searches documentation |
| "List all available documentation" | `listDocSections` → returns index |
| "Show me the Developer Hub docs" | `getDocSection` → returns full doc |
| "What products are indexed?" | `getDocSummary` → returns overview |
| "How many pods are running?" | `kubernetes-mcp` tools |
| "What namespaces exist?" | `kubernetes-mcp` tools |

## Tool Output Examples

### searchDocs - Searching Documentation

<p align="center">
  <img src="{{ '/assets/images/lightspeed-search.png' | relative_url }}" alt="searchDocs tool output in Lightspeed" width="100%"/>
</p>

### listDocSections - Documentation Index

<p align="center">
  <img src="{{ '/assets/images/lightspeed-doc-index.png' | relative_url }}" alt="listDocSections tool output in Lightspeed" width="100%"/>
</p>

### getDocSummary - Knowledge Base Summary

<p align="center">
  <img src="{{ '/assets/images/lightspeed-summary.png' | relative_url }}" alt="getDocSummary tool output in Lightspeed" width="100%"/>
</p>

### Lightspeed Chat Using getDocSection

<p align="center">
  <img src="{{ '/assets/images/topology-chat.png' | relative_url }}" alt="Lightspeed answering questions using documentation" width="100%"/>
</p>

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

## Technology Stack

| Component | Technology |
|-----------|------------|
| Framework | Quarkus 3.27.3 |
| MCP Extension | quarkus-mcp-server-http 1.8.1 |
| Health Checks | SmallRye Health |
| Container Base | UBI9 OpenJDK 21 Runtime |
| Container Registry | quay.io |
| Orchestration | Kubernetes / OpenShift |
| Packaging | Helm Chart |
