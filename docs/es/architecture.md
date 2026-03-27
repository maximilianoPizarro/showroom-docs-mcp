---
layout: default
title: Arquitectura
permalink: /es/architecture/
---

# Arquitectura

<p align="center">
  <img src="{{ '/assets/images/logo.svg' | relative_url }}" alt="Logo" width="80"/>
</p>

<p align="center"><a href="{{ '/architecture/' | relative_url }}">English</a> | <strong>Espanol</strong></p>

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
│                    ┌────────────┴─────────────┐      │
│                    │                          │      │
│                    ▼                          ▼      │
│             ┌────────────┐            ┌──────────────┐ │
│             │  LLM       │            │ showroom-docs│ │
│             │  Provider  │            │     -mcp     │ │
│             │ (Llama 3.2)│            └──────┬───────┘ │
│             └────────────┘                   │        │
│                                              │        │
│                                          ┌───────────┴───┐  │
│                                          │  Embedded     │  │
│                                          │  Documentation│  │
│                                          │ (46 archivos) │  │
│                                          └───────────────┘  │
└─────────────────────────────────────────────────────────────┘
```

## Componentes

### Showroom Docs MCP Server

- **Runtime**: Quarkus 3.27.3 con Java 21
- **Protocolo**: MCP via Streamable HTTP (`/mcp`) y SSE (`/mcp/sse`)
- **Contenido**: 46 documentos markdown embebidos (~2.5MB de PDFs oficiales Red Hat)
- **Puerto**: 8080

### Flujo de datos

1. El usuario hace una pregunta en la consola de OLS
2. OLS API Server recibe la consulta y la envia al LLM
3. El LLM puede invocar tools del servidor MCP:
   - `showroom-docs-mcp`: para consultas de documentacion (productos Red Hat, workshop, developer products)
4. El MCP server busca en los documentos indexados y devuelve secciones relevantes
5. El LLM genera una respuesta contextualizada usando la documentacion obtenida

### MCP Tools

| Tool | Descripcion | Input |
|------|-------------|-------|
| `searchDocs` | Busqueda por keywords en toda la documentacion | `query: string` |
| `listDocSections` | Lista todas las secciones disponibles | (sin parametros) |
| `getDocSection` | Contenido completo de una seccion (soporta fuzzy matching) | `fileName: string` |
| `getDocSummary` | Resumen del knowledge base y preguntas de ejemplo | (sin parametros) |

### Seleccion de herramientas por el LLM

| Tipo de pregunta | Tool esperado |
|------------------|---------------|
| "Como instalo Service Mesh?" | `searchDocs` → busca documentacion |
| "Lista toda la documentacion disponible" | `listDocSections` → devuelve indice |
| "Mostrame los docs de Developer Hub" | `getDocSection` → devuelve doc completo |
| "Que productos estan indexados?" | `getDocSummary` → devuelve resumen |
| "Como instalo Ansible?" | `searchDocs` → busca docs de developer |
| "Quick-start para Quarkus?" | `searchDocs` → devuelve comandos CLI |

## Stack Tecnologico

| Componente | Tecnologia |
|-----------|------------|
| Framework | Quarkus 3.27.3 |
| Extension MCP | quarkus-mcp-server-http 1.8.1 |
| Health Checks | SmallRye Health |
| Imagen Base | UBI9 OpenJDK 21 Runtime |
| Registry | quay.io |
| Orquestacion | Kubernetes / OpenShift |
| Packaging | Helm Chart |
