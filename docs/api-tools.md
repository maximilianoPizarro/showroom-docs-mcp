---
layout: default
title: API Tools
---

# MCP Tools API

<p align="center">
  <img src="assets/images/logo.svg" alt="Logo" width="80"/>
</p>

El servidor expone 4 herramientas via Model Context Protocol (MCP).

---

## searchWorkshopDocs

Busca en toda la documentacion indexada usando keywords.

**Input:**
```json
{
  "query": "string - Palabras clave para buscar"
}
```

**Output:** Las 5 secciones mas relevantes con su score de relevancia.

**Ejemplo:**
```
query: "connectivity link gateway policy"
```

**Cobertura:** Workshop Neuralbank + 9 productos Red Hat (Service Mesh, Connectivity Link, Developer Hub, OLS, Observability, OpenTelemetry, Pipelines, API Management, OpenShift AI).

---

## listWorkshopSections

Lista todas las secciones de documentacion disponibles.

**Input:** ninguno

**Output:** Tabla de contenidos con nombre de archivo y titulo de cada seccion.

**Ejemplo de respuesta:**
```
- 00-index.md: IA Development From Zero To Hero
- 01-business-case.md: Neuralbank Business Case
- 20-service-mesh.md: Red Hat OpenShift Service Mesh 3.3
- 22-developer-hub.md: Red Hat Developer Hub 1.9
...
```

---

## getWorkshopSection

Obtiene el contenido completo de una seccion especifica.

**Input:**
```json
{
  "fileName": "string - Nombre del archivo, ej: '08-build-mcp-agent.md'"
}
```

**Output:** Contenido markdown completo (truncado a 8000 chars si es muy largo).

**Archivos disponibles:**

| Archivo | Tema |
|---------|------|
| `00-index.md` | Indice general del workshop |
| `01-business-case.md` | Caso de negocio Neuralbank |
| `02-workshop-overview.md` | Vision general |
| `03-configure-environment.md` | Configuracion del entorno |
| `04-agents-intro.md` | Intro a agentes MCP |
| `05-golden-path.md` | Golden Path con Developer Hub |
| `06-devspaces.md` | Red Hat DevSpaces |
| `07-keycloak-user-management.md` | Keycloak |
| `08-build-mcp-agent.md` | Build del MCP Agent |
| `09-connectivity-link.md` | Connectivity Link |
| `10-mcp-inspector.md` | MCP Inspector |
| `11-deploy-integrate.md` | Deploy e integracion |
| `12-opentelemetry.md` | OpenTelemetry |
| `13-rag-intro.md` | Intro a RAG |
| `14-vector-databases.md` | Bases de datos vectoriales |
| `15-simple-rag.md` | RAG simple |
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

## getWorkshopSummary

Retorna un resumen ejecutivo de toda la documentacion indexada.

**Input:** ninguno

**Output:** Resumen del workshop, productos Red Hat cubiertos, repositorios clave, y sugerencias de busqueda.

---

## Endpoint MCP

```
SSE: http://<host>:8080/mcp/sse
Health: http://<host>:8080/q/health/ready
```
