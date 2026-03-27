---
layout: default
title: API Tools
permalink: /es/api-tools/
---

# API de herramientas MCP

<p align="center">
  <img src="../assets/images/logo.svg" alt="Logo" width="80"/>
</p>

<p align="center"><a href="{{ '/api-tools/' | relative_url }}">English</a> | <strong>Espanol</strong></p>

El servidor expone 4 herramientas mediante Model Context Protocol (MCP).

---

## searchDocs

Buscar en toda la documentacion indexada mediante palabras clave. Esta es la herramienta principal para responder preguntas sobre productos Red Hat y el workshop Neuralbank.

**Entrada:**
```json
{
  "query": "string - Palabras clave para buscar"
}
```

**Salida:** Las 5 secciones mas relevantes de documentacion con puntuacion de relevancia.

**Consultas de ejemplo:**

| Consulta | Que encuentra |
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

**Cobertura:** Workshop Neuralbank + 9 productos Red Hat (Service Mesh, Connectivity Link, Developer Hub, OLS, Observability, OpenTelemetry, Pipelines, API Management, OpenShift AI).

---

## getDocSection

Obtener el contenido completo de un archivo de documentacion especifico. Soporta tanto nombres exactos como busqueda difusa por tema.

**Entrada:**
```json
{
  "fileName": "string - Exact filename OR topic keywords"
}
```

**Salida:** Contenido markdown completo (truncado a 8000 caracteres en documentos grandes).

**Ejemplos:**

| Entrada | Se resuelve a |
|-------|---------------|
| `22-developer-hub.md` | Coincidencia exacta |
| `developer hub` | Busqueda difusa → `22-developer-hub.md` |
| `service mesh` | Busqueda difusa → `20-service-mesh.md` |
| `openshift ai` | Busqueda difusa → `28-openshift-ai.md` |
| `pipelines` | Busqueda difusa → `26-pipelines.md` |
| `lightspeed` | Busqueda difusa → `23-openshift-lightspeed.md` |
| `connectivity link` | Busqueda difusa → `21-connectivity-link.md` |
| `neuralbank` | Busqueda difusa → `01-business-case.md` |

**Documentos disponibles:**

| Archivo | Tema |
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

Listar todos los archivos de documentacion disponibles con sus titulos.

**Entrada:** ninguna

**Salida:** Devuelve tabla de contenidos organizada por categoria (Workshop / Productos Red Hat).

---

## getDocSummary

Obtener un resumen de toda la base de conocimiento, incluyendo estadisticas y preguntas de ejemplo.

**Entrada:** ninguna

**Salida:** Resumen del contenido indexado, versiones de productos y consultas sugeridas.

---

## Endpoint MCP

```
Streamable HTTP: http://<host>:8080/mcp
SSE:             http://<host>:8080/mcp/sse
Health:          http://<host>:8080/q/health/ready
```

---

## Preguntas de ejemplo para OpenShift Lightspeed

Una vez que el servidor MCP este registrado en OLSConfig, puedes hacerle a Lightspeed este tipo de preguntas:

### Productos Red Hat
- "How do I install OpenShift Service Mesh 3.3?"
- "Explain the architecture of Red Hat Developer Hub"
- "What are the steps to deploy a model with OpenShift AI?"
- "How do I configure Connectivity Link gateway policies?"
- "How do I set up OpenTelemetry collectors for distributed tracing?"
- "What is OpenShift Lightspeed and how do I configure OLSConfig?"
- "How do I create a Tekton pipeline with Pipelines as Code?"
- "How do I manage APIs with Red Hat OpenShift API Management?"
- "What monitoring tools are available in OpenShift Observability?"

### Workshop Neuralbank
- "What is the Neuralbank workshop about?"
- "How do I build an MCP agent with Quarkus?"
- "Explain the Golden Path template in Developer Hub"
- "How is Keycloak used for user management in the workshop?"
- "What is RAG and how does it work with vector databases?"
- "How do I use DevSpaces for development?"
- "How do I deploy and integrate the MCP agent?"
