---
layout: default
title: Configuration
permalink: /configuration/
---

# Configuration

<p align="center">
  <img src="{{ '/assets/images/logo.svg' | relative_url }}" alt="Logo" width="80"/>
</p>

<p align="center"><strong>English</strong> | <a href="{{ '/es/configuration/' | relative_url }}">Espanol</a></p>

## Prerequisites

Before configuring OLSConfig with the showroom-docs-mcp server, ensure you have:

1. **OpenShift Lightspeed Operator** installed on your cluster
2. **An LLM provider** configured (e.g. OpenShift AI with vLLM, or any compatible provider)
3. **The showroom-docs-mcp Helm chart** deployed in the `openshift-lightspeed` namespace

## Step-by-Step Setup

### Step 1: Install the MCP Server

```bash
# Add the Helm repository
helm repo add showroom-docs-mcp \
  https://maximilianopizarro.github.io/showroom-docs-mcp/

# Install in the openshift-lightspeed namespace
helm install showroom-docs-mcp showroom-docs-mcp/showroom-docs-mcp \
  --namespace openshift-lightspeed \
  --set image.pullPolicy=Always
```

Verify the pod is running:

```bash
oc get pods -n openshift-lightspeed -l app=showroom-docs-mcp
# Expected: 1/1 Running

oc exec -n openshift-lightspeed deploy/showroom-docs-mcp -- \
  curl -s http://localhost:8080/q/health/ready
# Expected: {"status":"UP","checks":[]}
```

### Step 2: Create LLM Credentials Secret

If you don't already have one, create the credentials secret for your LLM provider:

```bash
oc create secret generic ols-llm-credentials \
  -n openshift-lightspeed \
  --from-literal=apitoken=<your-api-token>
```

> For OpenShift AI with vLLM (self-hosted models), the token can be any non-empty string since authentication is handled internally.

### Step 3: Enable the MCPServer Feature Gate

The `MCPServer` feature gate must be enabled in the OLSConfig spec:

```yaml
spec:
  featureGates:
    - MCPServer
```

### Step 4: Configure MCP Servers

Add the showroom-docs-mcp server to the `mcpServers` section:

```yaml
  mcpServers:
    - name: showroom-docs-mcp
      timeout: 10
      url: 'http://showroom-docs-mcp.openshift-lightspeed.svc.cluster.local:8080/mcp'
```

> **Important**: Use `/mcp` (Streamable HTTP), **not** `/mcp/sse`. The OLS client uses POST requests which require the Streamable HTTP endpoint. Using `/mcp/sse` will result in a `405 Method Not Allowed` error.

You can add additional MCP servers alongside it (e.g. kubernetes-mcp for cluster state queries):

```yaml
  mcpServers:
    - name: kubernetes-mcp
      timeout: 5
      url: 'http://kubernetes-mcp-server.istio-system.svc.cluster.local:8080/mcp'
    - name: showroom-docs-mcp
      timeout: 10
      url: 'http://showroom-docs-mcp.openshift-lightspeed.svc.cluster.local:8080/mcp'
```

### Step 5: Apply the Complete OLSConfig

Save the following as `cluster-ols.yml` (also available in the repo at [`k8s/cluster-ols.yml`](https://github.com/maximilianoPizarro/showroom-docs-mcp/blob/main/k8s/cluster-ols.yml)):

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
              maxTokensForResponse: 512
        name: red_hat_openshift_ai
        type: rhoai_vllm
        url: 'http://llama-32-3b-instruct-openai.my-first-model.svc.cluster.local/v1'
  mcpServers:
    - name: kubernetes-mcp
      timeout: 5
      url: 'http://kubernetes-mcp-server.istio-system.svc.cluster.local:8080/mcp'
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

Apply it:

```bash
oc apply -f cluster-ols.yml
```

The OLS operator will automatically restart the `lightspeed-app-server` pod with the new configuration.

### Step 6: Verify the Integration

Wait for the new OLS pod to be ready:

```bash
oc get pods -n openshift-lightspeed -l app.kubernetes.io/name=lightspeed-service-api -w
```

Check that OLS successfully loads tools from both MCP servers:

```bash
oc logs -n openshift-lightspeed deploy/lightspeed-app-server \
  -c lightspeed-service-api | grep "tools from MCP"
```

Expected output:

```
Loaded 4 tools from MCP server 'showroom-docs-mcp'
Loaded 19 tools from MCP server 'kubernetes-mcp'
```

If you see `Failed to get tools from MCP server 'showroom-docs-mcp'`, check:

| Error | Cause | Fix |
|-------|-------|-----|
| `405 Method Not Allowed` | URL ends with `/mcp/sse` | Change URL to `/mcp` |
| `Connection refused` | MCP pod not running | Check `oc get pods -l app=showroom-docs-mcp` |
| `NoSuchMethodError: LaunchMode.isProduction()` | Quarkus version mismatch | Ensure Quarkus 3.27.3+ in pom.xml |

### Step 7: Test in Lightspeed Console

Open the OpenShift web console and click the Lightspeed chat icon. Try these questions:

**Red Hat Product Documentation:**
- "How do I install OpenShift Service Mesh 3.3?"
- "Explain the architecture of Red Hat Developer Hub"
- "What are the steps to deploy a model with OpenShift AI?"
- "How do I configure Connectivity Link gateway policies?"
- "How do I set up OpenTelemetry collectors?"
- "How does OpenShift Lightspeed OLSConfig work?"
- "How do I create a Tekton pipeline with Pipelines as Code?"

**Neuralbank Workshop:**
- "What is the Neuralbank workshop about?"
- "How do I build an MCP agent with Quarkus?"
- "What is RAG and how does it work with vector databases?"

---

## OLSConfig Field Reference

| Field | Description |
|-------|-------------|
| `spec.featureGates` | List of feature gates to enable. `MCPServer` is required for MCP integration |
| `spec.llm.providers` | LLM provider configuration (model name, type, URL, credentials) |
| `spec.mcpServers` | List of MCP servers with name, timeout (seconds), and URL |
| `spec.ols.conversationCache` | Conversation history storage (postgres recommended) |
| `spec.ols.defaultModel` | Default LLM model name to use |
| `spec.ols.defaultProvider` | Default LLM provider name |
| `spec.ols.deployment` | Replica counts for OLS components |
| `spec.ols.logLevel` | Log verbosity (DEBUG, INFO, WARNING, ERROR) |

## MCP Server Application Configuration

### application.properties

```properties
quarkus.http.port=8080
quarkus.http.host=0.0.0.0
quarkus.log.level=INFO
quarkus.log.category."com.neuralbank".level=DEBUG
```

### Environment Variables

| Variable | Default | Description |
|----------|---------|-------------|
| `QUARKUS_HTTP_PORT` | `8080` | Server port |
| `QUARKUS_LOG_LEVEL` | `INFO` | Log level |
| `JAVA_OPTS_APPEND` | `-Dquarkus.http.host=0.0.0.0` | Additional JVM options |

## Helm Chart Values

```yaml
replicaCount: 1

image:
  repository: quay.io/maximilianopizarro/showroom-docs-mcp
  tag: latest
  pullPolicy: Always

service:
  type: ClusterIP
  port: 8080

resources:
  requests:
    cpu: 100m
    memory: 256Mi
  limits:
    cpu: 500m
    memory: 512Mi

namespace: openshift-lightspeed
```

## RAG Option (BYO Knowledge)

If you prefer native RAG instead of MCP, use the markdown files in `rag-content/`:

```bash
podman run -it --rm --device=/dev/fuse \
  -v $XDG_RUNTIME_DIR/containers/auth.json:/run/user/0/containers/auth.json:Z \
  -v $(pwd)/rag-content:/markdown:Z \
  -v /tmp/rag-output:/output:Z \
  registry.redhat.io/openshift-lightspeed-tech-preview/lightspeed-rag-tool-rhel9:latest
```

Then add the RAG image to OLSConfig:

```yaml
spec:
  ols:
    rag:
      - image: image-registry.openshift-image-registry.svc:5000/openshift-lightspeed/showroom-docs-rag:latest
        indexID: showroom_docs_index
        indexPath: /rag/vector_db
```

Reference: [Bring your own knowledge to OpenShift Lightspeed](https://redhat.com/en/blog/bring-your-own-knowledge-openshift-lightspeed)
