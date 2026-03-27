<p align="center">
  <img src="docs/assets/images/logo.svg" alt="Showroom Docs MCP" width="180"/>
</p>

<h1 align="center">Showroom Docs MCP Server</h1>

<p align="center">
  <strong>Quarkus MCP Server for OpenShift Lightspeed RAG</strong>
</p>

<p align="center">
  <a href="https://quay.io/repository/maximilianopizarro/showroom-docs-mcp">
    <img src="https://img.shields.io/badge/quay.io-showroom--docs--mcp-blueviolet?logo=redhat" alt="Quay.io"/>
  </a>
  <img src="https://img.shields.io/badge/Quarkus-3.21.3-blue?logo=quarkus" alt="Quarkus"/>
  <img src="https://img.shields.io/badge/MCP_Server-1.8.1-purple" alt="MCP"/>
  <img src="https://img.shields.io/badge/Java-21-orange?logo=openjdk" alt="Java"/>
  <a href="https://maximilianopizarro.github.io/showroom-docs-mcp">
    <img src="https://img.shields.io/badge/docs-GitHub_Pages-blue?logo=github" alt="Docs"/>
  </a>
  <img src="https://img.shields.io/badge/license-Apache--2.0-green" alt="License"/>
</p>

---

Servidor [MCP (Model Context Protocol)](https://modelcontextprotocol.io/) construido con [Quarkus](https://quarkus.io/) que indexa documentacion del workshop **"IA Development From Zero To Hero"** (Neuralbank) y documentacion oficial de **9 productos Red Hat**, exponiendola como tools para [OpenShift Lightspeed](https://docs.redhat.com/en/documentation/red_hat_openshift_lightspeed/1.0).

## Arquitectura

```
  Usuario <-> OLS Console <-> OLS API Server
                                  |
                    +-------------+-------------+
                    |             |              |
              LLM Provider  kubernetes-mcp  showroom-docs-mcp
              (Llama 3.2)                    (este proyecto)
```

## MCP Tools

| Tool | Descripcion |
|------|-------------|
| `searchWorkshopDocs` | Busqueda por keywords en toda la documentacion |
| `listWorkshopSections` | Lista secciones disponibles |
| `getWorkshopSection` | Contenido completo de una seccion |
| `getWorkshopSummary` | Resumen general del knowledge base |

## Contenido indexado (25 documentos, ~3MB)

| Fuente | Docs |
|--------|------|
| Workshop Neuralbank | Business case, MCP agents, Golden Path, DevSpaces, Keycloak, Connectivity Link, MCP Inspector, Deploy, OpenTelemetry, RAG (16 archivos) |
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

### Helm (recomendado)

```bash
HELM_REGISTRY_CONFIG="$HOME/.config/containers/auth.json" \
  helm install showroom-docs-mcp \
  oci://quay.io/maximilianopizarro/showroom-docs-mcp \
  --namespace openshift-lightspeed
```

### Manifiestos directos

```bash
oc apply -f k8s/deployment.yaml
```

### Configurar OLSConfig

Agregar a `mcpServers` en tu `OLSConfig`:

```yaml
mcpServers:
  - name: showroom-docs-mcp
    timeout: 10
    url: 'http://showroom-docs-mcp.openshift-lightspeed.svc.cluster.local:8080/mcp/sse'
```

## Desarrollo local

```bash
./mvnw quarkus:dev
# MCP en http://localhost:8080/mcp/sse
```

## Build

```bash
podman build -t quay.io/maximilianopizarro/showroom-docs-mcp:latest -f Containerfile .
podman push quay.io/maximilianopizarro/showroom-docs-mcp:latest
```

## Documentacion

[https://maximilianopizarro.github.io/showroom-docs-mcp](https://maximilianopizarro.github.io/showroom-docs-mcp)

## Licencia

Apache License 2.0
