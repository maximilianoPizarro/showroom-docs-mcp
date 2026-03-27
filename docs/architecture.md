---
layout: default
title: Architecture
---

# Arquitectura

<p align="center">
  <img src="assets/images/logo.svg" alt="Logo" width="80"/>
</p>

## Diagrama General

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

## Componentes

### Showroom Docs MCP Server

- **Runtime**: Quarkus 3.21.3 con Java 21
- **Protocolo**: MCP via HTTP SSE (`/mcp/sse`)
- **Contenido**: 25 documentos markdown embebidos (~3MB)
- **Puerto**: 8080

### Flujo de datos

1. El usuario hace una pregunta en la consola de OLS
2. OLS API Server recibe la consulta y la envia al LLM
3. El LLM puede invocar tools del MCP server `showroom-docs-mcp`
4. El MCP server busca en los documentos indexados
5. Los resultados se envian de vuelta al LLM
6. El LLM genera una respuesta contextualizada

### MCP Tools disponibles

| Tool | Descripcion | Input |
|------|-------------|-------|
| `searchWorkshopDocs` | Busqueda por keywords | `query: string` |
| `listWorkshopSections` | Lista secciones | (sin parametros) |
| `getWorkshopSection` | Contenido de seccion | `fileName: string` |
| `getWorkshopSummary` | Resumen general | (sin parametros) |

## Stack Tecnologico

| Componente | Tecnologia |
|------------|------------|
| Framework | Quarkus 3.21.3 |
| MCP Extension | quarkus-mcp-server-http 1.8.1 |
| Health Checks | SmallRye Health |
| Container Base | UBI9 OpenJDK 21 Runtime |
| Container Registry | quay.io |
| Orquestacion | Kubernetes / OpenShift |
| Packaging | Helm Chart |
