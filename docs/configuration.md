---
layout: default
title: Configuration
---

# Configuration

<p align="center">
  <img src="assets/images/logo.svg" alt="Logo" width="80"/>
</p>

## Complete OLSConfig

This is the full `OLSConfig` with both MCP servers integrated:

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

> **Important**: Use `/mcp` (Streamable HTTP) for the showroom-docs-mcp URL, not `/mcp/sse`. The OLS client uses POST requests which require the Streamable HTTP endpoint.

## MCP Server Configuration

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

## Verifying MCP Connection

After applying the OLSConfig, check that OLS can connect to the MCP server:

```bash
# Check OLS logs for successful tool loading
oc logs -n openshift-lightspeed deploy/lightspeed-app-server \
  -c lightspeed-service-api | grep "tools from MCP"
```

You should see:
```
Loaded 4 tools from MCP server 'showroom-docs-mcp'
Loaded 19 tools from MCP server 'kubernetes-mcp'
```

If you see `405 Method Not Allowed`, make sure the URL uses `/mcp` (not `/mcp/sse`).

## RAG Option (BYO Knowledge)

If you prefer native RAG instead of MCP, use the markdown files in `rag-content/`:

```bash
podman run -it --rm --device=/dev/fuse \
  -v $XDG_RUNTIME_DIR/containers/auth.json:/run/user/0/containers/auth.json:Z \
  -v $(pwd)/rag-content:/markdown:Z \
  -v /tmp/rag-output:/output:Z \
  registry.redhat.io/openshift-lightspeed-tech-preview/lightspeed-rag-tool-rhel9:latest
```

Reference: [Bring your own knowledge to OpenShift Lightspeed](https://redhat.com/en/blog/bring-your-own-knowledge-openshift-lightspeed)
