# Showroom Docs MCP Server

![Version: 1.0.0](https://img.shields.io/badge/Version-1.0.0-informational?style=flat-square)
![Type: application](https://img.shields.io/badge/Type-application-informational?style=flat-square)
![AppVersion: 1.0.0](https://img.shields.io/badge/AppVersion-1.0.0-informational?style=flat-square)

Quarkus MCP Server that indexes Red Hat product documentation and the "IA Development From Zero To Hero" workshop for OpenShift Lightspeed.

## Description

This Helm chart deploys a [Model Context Protocol (MCP)](https://modelcontextprotocol.io/) server built with [Quarkus](https://quarkus.io/) on OpenShift. The server indexes **35 documents** covering:

- **Neuralbank Workshop** (16 docs): MCP agents, Golden Path, DevSpaces, Keycloak, RAG, OpenTelemetry
- **Red Hat Product Documentation** (9 docs): Service Mesh, Connectivity Link, Developer Hub, Lightspeed, Observability, OpenTelemetry, Pipelines, API Management, OpenShift AI
- **Red Hat Developer Products** (10 docs): Quarkus, OpenShift, RHEL, Dev Spaces, OpenShift AI, Developer Hub, Ansible, OpenShift Local, Connectivity Link, 3scale

## Prerequisites

- OpenShift 4.x or Kubernetes >= 1.25
- OpenShift Lightspeed Operator installed (for MCP integration)
- `helm` CLI v3+

## Installing the Chart

```bash
helm repo add showroom-docs-mcp \
  https://maximilianopizarro.github.io/showroom-docs-mcp/

helm install showroom-docs-mcp showroom-docs-mcp/showroom-docs-mcp \
  --namespace openshift-lightspeed \
  --set image.pullPolicy=Always
```

## Uninstalling the Chart

```bash
helm uninstall showroom-docs-mcp -n openshift-lightspeed
```

## Configuration

### OLSConfig Integration

After deploying, add the MCP server to your OLSConfig:

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

> **Important**: Use `/mcp` (Streamable HTTP), **not** `/mcp/sse`.

## Values

| Key | Type | Default | Description |
|-----|------|---------|-------------|
| `replicaCount` | int | `1` | Number of MCP server replicas |
| `image.repository` | string | `quay.io/maximilianopizarro/showroom-docs-mcp` | Container image repository |
| `image.tag` | string | `latest` | Container image tag |
| `image.pullPolicy` | string | `Always` | Image pull policy |
| `nameOverride` | string | `""` | Override chart name |
| `fullnameOverride` | string | `""` | Override full release name |
| `namespace` | string | `openshift-lightspeed` | Target namespace |
| `service.type` | string | `ClusterIP` | Service type |
| `service.port` | int | `8080` | Service port |
| `resources.requests.cpu` | string | `100m` | CPU request |
| `resources.requests.memory` | string | `256Mi` | Memory request |
| `resources.limits.cpu` | string | `500m` | CPU limit |
| `resources.limits.memory` | string | `512Mi` | Memory limit |
| `readinessProbe.httpGet.path` | string | `/q/health/ready` | Readiness probe path |
| `readinessProbe.initialDelaySeconds` | int | `5` | Readiness probe initial delay |
| `livenessProbe.httpGet.path` | string | `/q/health/live` | Liveness probe path |
| `livenessProbe.initialDelaySeconds` | int | `10` | Liveness probe initial delay |
| `nodeSelector` | object | `{}` | Node selector constraints |
| `tolerations` | list | `[]` | Pod tolerations |
| `affinity` | object | `{}` | Pod affinity rules |
| `olsConfig.enabled` | bool | `false` | Enable OLSConfig integration |
| `olsConfig.mcpServerName` | string | `showroom-docs-mcp` | MCP server name in OLSConfig |
| `olsConfig.mcpServerTimeout` | int | `10` | MCP server timeout (seconds) |

## MCP Tools

The server exposes 4 tools via the Model Context Protocol:

| Tool | Description |
|------|-------------|
| `searchDocs` | Search all documentation by keywords |
| `listDocSections` | List all available document sections |
| `getDocSection` | Get full content of a specific document (supports fuzzy matching) |
| `getDocSummary` | Get knowledge base overview and statistics |

## Endpoints

| Endpoint | Protocol | Description |
|----------|----------|-------------|
| `/mcp` | Streamable HTTP | Primary MCP endpoint (use this for OLS) |
| `/mcp/sse` | Server-Sent Events | SSE transport |
| `/q/health/ready` | HTTP | Readiness health check |
| `/q/health/live` | HTTP | Liveness health check |

## Example Questions for Lightspeed

Once configured, ask OpenShift Lightspeed:

- "How do I install OpenShift Service Mesh 3.3?"
- "Explain the architecture of Red Hat Developer Hub"
- "What are the steps to deploy a model with OpenShift AI?"
- "What is the Neuralbank workshop about?"
- "How do I build an MCP agent with Quarkus?"

## Links

- **Documentation**: [maximilianopizarro.github.io/showroom-docs-mcp](https://maximilianopizarro.github.io/showroom-docs-mcp)
- **Source**: [github.com/maximilianoPizarro/showroom-docs-mcp](https://github.com/maximilianoPizarro/showroom-docs-mcp)
- **Container Image**: [quay.io/maximilianopizarro/showroom-docs-mcp](https://quay.io/repository/maximilianopizarro/showroom-docs-mcp)

## License

Apache-2.0
