---
layout: default
title: Architecture
---

# Architecture

<p align="center">
  <img src="assets/images/logo.svg" alt="Logo" width="80"/>
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
│                                          │  (25 files)   │  │
│                                          └───────────────┘  │
└─────────────────────────────────────────────────────────────┘
```

## Components

### Showroom Docs MCP Server

- **Runtime**: Quarkus 3.27.3 with Java 21
- **Protocol**: MCP via Streamable HTTP (`/mcp`) and SSE (`/mcp/sse`)
- **Content**: 25 embedded markdown documents (~3MB)
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
