# Showroom Docs MCP Server

![Version: 1.8.0](https://img.shields.io/badge/Version-1.8.0-informational?style=flat-square)
![Type: application](https://img.shields.io/badge/Type-application-informational?style=flat-square)
![AppVersion: 1.8.0](https://img.shields.io/badge/AppVersion-1.8.0-informational?style=flat-square)
[![Artifact Hub](https://img.shields.io/endpoint?url=https://artifacthub.io/badge/repository/showroom-docs-mcp)](https://artifacthub.io/packages/helm/showroom-docs-mcp/showroom-docs-mcp)

Quarkus MCP Server that indexes Red Hat product documentation and the "IA Development From Zero To Hero" workshop for OpenShift Lightspeed.

## Description

This Helm chart deploys a [Model Context Protocol (MCP)](https://modelcontextprotocol.io/) server built with [Quarkus](https://quarkus.io/) on OpenShift. The server indexes **46 documents** covering:

- **Neuralbank Workshop** (27 docs): MCP agents, Golden Path, DevSpaces, Keycloak, RAG, OpenTelemetry, LLM, GPU, agents, platform engineering, data engineering
- **Red Hat Product Documentation** (9 docs): Service Mesh, Connectivity Link, Developer Hub, Lightspeed, Observability, OpenTelemetry, Pipelines, API Management, OpenShift AI
- **Red Hat Developer Products** (10 docs): Quarkus, OpenShift, RHEL, Dev Spaces, OpenShift AI, Developer Hub, Ansible, OpenShift Local, Connectivity Link, 3scale

## Prerequisites

- OpenShift 4.x or Kubernetes >= 1.25
- OpenShift Lightspeed Operator installed (for MCP integration)
- `helm` CLI v3+
- `oc` CLI authenticated to the cluster

## Installation

### Step 1: Add the Helm Repository

```bash
helm repo add showroom-docs-mcp \
  https://maximilianopizarro.github.io/showroom-docs-mcp/

helm repo update
```

### Step 2: Install the Chart

```bash
helm install showroom-docs-mcp showroom-docs-mcp/showroom-docs-mcp \
  --namespace openshift-lightspeed \
  --create-namespace \
  --set image.pullPolicy=Always
```

With custom values:

```bash
helm install showroom-docs-mcp showroom-docs-mcp/showroom-docs-mcp \
  --namespace openshift-lightspeed \
  --create-namespace \
  --set image.pullPolicy=Always \
  --set resources.limits.memory=1Gi \
  --set replicaCount=2
```

Or from source:

```bash
git clone https://github.com/maximilianoPizarro/showroom-docs-mcp.git
cd showroom-docs-mcp
helm install showroom-docs-mcp helm/showroom-docs-mcp \
  --namespace openshift-lightspeed
```

### Step 3: Verify the Deployment

```bash
# Check pod status
oc get pods -n openshift-lightspeed -l app=showroom-docs-mcp

# Expected output:
# NAME                              READY   STATUS    RESTARTS   AGE
# showroom-docs-mcp-xxxxxxxxxx-xxxxx     1/1     Running   0          30s

# Verify health endpoint
oc exec -n openshift-lightspeed deploy/showroom-docs-mcp -- \
  curl -s http://localhost:8080/q/health/ready

# Expected: {"status":"UP","checks":[]}

# Check application logs
oc logs -n openshift-lightspeed -l app=showroom-docs-mcp --tail=20
```

### Step 4: Configure OLSConfig

Create a file `cluster-ols.yml` with the MCP server configuration:

```yaml
apiVersion: ols.openshift.io/v1alpha1
kind: OLSConfig
metadata:
  name: cluster
spec:
  featureGates:
    - MCPServer
  llm:
    providers:
      - credentialsSecretRef:
          name: ols-llm-credentials
        models:
          - name: llama-32-3b-instruct
            parameters:
              maxTokensForResponse: 8192
        name: red_hat_openshift_ai
        type: rhoai_vllm
        url: 'http://llama-32-3b-instruct-openai.my-first-model.svc.cluster.local/v1'
  mcpServers:
    - name: showroom-docs-mcp
      timeout: 10
      url: 'http://showroom-docs-mcp.openshift-lightspeed.svc.cluster.local:8080/mcp'
  ols:
    conversationCache:
      postgres:
        maxConnections: 2000
        sharedBuffers: 256MB
      type: postgres
    defaultModel: llama-32-3b-instruct
    defaultProvider: red_hat_openshift_ai
    deployment:
      api:
        replicas: 1
      console:
        replicas: 1
      dataCollector: {}
      database:
        replicas: 1
      llamaStack: {}
      mcpServer: {}
    logLevel: INFO
    userDataCollection: {}
  olsDataCollector:
    logLevel: INFO
```

> **Important**: Use `/mcp` (Streamable HTTP), **not** `/mcp/sse`. Using `/mcp/sse` will result in a `405 Method Not Allowed` error.

If you don't have an LLM credentials secret yet:

```bash
oc create secret generic ols-llm-credentials \
  -n openshift-lightspeed \
  --from-literal=apitoken=<your-api-token>
```

Apply the configuration:

```bash
oc apply -f cluster-ols.yml
```

### Step 5: Verify MCP Integration

Wait for the OLS pod to restart with the new configuration:

```bash
oc get pods -n openshift-lightspeed \
  -l app.kubernetes.io/name=lightspeed-service-api -w
```

Check that OLS loaded tools from the MCP server:

```bash
oc logs -n openshift-lightspeed deploy/lightspeed-app-server \
  -c lightspeed-service-api | grep "tools from MCP"

# Expected:
# Loaded 4 tools from MCP server 'showroom-docs-mcp'
```

### Step 6: Test with Lightspeed

Open the OpenShift web console and click the Lightspeed chat icon. Ask:

- "How do I install OpenShift Service Mesh 3.3?"
- "Explain the architecture of Red Hat Developer Hub"
- "What is the Neuralbank workshop about?"
- "How do I build an MCP agent with Quarkus?"
- "What are the steps to deploy a model with OpenShift AI?"

## Upgrading the Chart

```bash
helm repo update
helm upgrade showroom-docs-mcp showroom-docs-mcp/showroom-docs-mcp \
  --namespace openshift-lightspeed \
  --set image.pullPolicy=Always
```

After upgrading, restart the OLS app server to reload MCP tools:

```bash
oc rollout restart deploy/lightspeed-app-server -n openshift-lightspeed
```

## Uninstalling the Chart

```bash
helm uninstall showroom-docs-mcp -n openshift-lightspeed
```

After uninstalling, remove the `showroom-docs-mcp` entry from your OLSConfig `mcpServers` list and reapply.

## Troubleshooting

| Problem | Cause | Fix |
|---------|-------|-----|
| Pod `CrashLoopBackOff` | Old cached image | Set `image.pullPolicy=Always` and reinstall |
| `405 Method Not Allowed` in OLS logs | URL ends with `/mcp/sse` | Change OLSConfig URL to end with `/mcp` |
| `Connection refused` | MCP pod not running | Check `oc get pods -l app=showroom-docs-mcp` |
| `NoSuchMethodError: LaunchMode.isProduction()` | Quarkus version mismatch | Rebuild image with Quarkus 3.27.3+ |
| `Tool 'listWorkshopSections' not found` | Old tool names cached | Restart `lightspeed-app-server` deployment |
| Truncated LLM responses | Low `maxTokensForResponse` | Increase to `4096` in OLSConfig |
| LLM ignores documentation / hallucinates | MCP tools not loaded | Verify tools loaded in OLS logs |
| Inspector disconnects quickly | Route timeout too low | Increase `inspector.route.timeout` (default: `300s`) |

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
| `inspector.enabled` | bool | `true` | Enable MCP Inspector deployment |
| `inspector.image.repository` | string | `mcpuse/inspector` | Inspector container image |
| `inspector.image.tag` | string | `latest` | Inspector image tag |
| `inspector.service.port` | int | `8080` | Inspector service port |
| `inspector.route.enabled` | bool | `true` | Create OpenShift Route for Inspector |
| `inspector.route.timeout` | string | `300s` | Route timeout for MCP connections |
| `inspector.route.tls.termination` | string | `edge` | TLS termination type |
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

## Links

- **Documentation**: [maximilianopizarro.github.io/showroom-docs-mcp](https://maximilianopizarro.github.io/showroom-docs-mcp)
- **Source**: [github.com/maximilianoPizarro/showroom-docs-mcp](https://github.com/maximilianoPizarro/showroom-docs-mcp)
- **Container Image**: [quay.io/maximilianopizarro/showroom-docs-mcp](https://quay.io/repository/maximilianopizarro/showroom-docs-mcp)

## License

Apache-2.0
