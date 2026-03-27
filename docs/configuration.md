---
layout: default
title: Configuration
---

# Configuracion

<p align="center">
  <img src="assets/images/logo.svg" alt="Logo" width="80"/>
</p>

## OLSConfig completo

Este es el `OLSConfig` completo con el MCP server integrado:

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
      url: 'http://showroom-docs-mcp.openshift-lightspeed.svc.cluster.local:8080/mcp/sse'
  ols:
    conversationCache:
      postgres:
        maxConnections: 2000
        sharedBuffers: 256MB
      type: postgres
    defaultModel: llama-32-3b-instruct
    defaultProvider: red_hat_openshift_ai
    rag:
      - image: image-registry.openshift-image-registry.svc:5000/openshift-lightspeed/showroom-docs-rag:latest
        indexID: showroom_docs_index
        indexPath: /rag/vector_db
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

## Configuracion del MCP Server

### application.properties

```properties
quarkus.http.port=8080
quarkus.http.host=0.0.0.0
quarkus.mcp-server.server-info.name=showroom-docs-mcp
quarkus.mcp-server.server-info.version=1.0.0
quarkus.log.level=INFO
quarkus.log.category."com.neuralbank".level=DEBUG
```

### Variables de entorno

| Variable | Default | Descripcion |
|----------|---------|-------------|
| `QUARKUS_HTTP_PORT` | `8080` | Puerto del servidor |
| `QUARKUS_LOG_LEVEL` | `INFO` | Nivel de log |
| `JAVA_OPTS_APPEND` | `-Dquarkus.http.host=0.0.0.0` | Opciones JVM adicionales |

## Helm Chart Values

```yaml
replicaCount: 1

image:
  repository: quay.io/maximilianopizarro/showroom-docs-mcp
  tag: latest
  pullPolicy: IfNotPresent

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

## Opcion RAG (BYO Knowledge)

Si prefieres RAG nativo en vez de MCP, usa los archivos markdown en `rag-content/`:

```bash
podman run -it --rm --device=/dev/fuse \
  -v $XDG_RUNTIME_DIR/containers/auth.json:/run/user/0/containers/auth.json:Z \
  -v $(pwd)/rag-content:/markdown:Z \
  -v /tmp/rag-output:/output:Z \
  registry.redhat.io/openshift-lightspeed-tech-preview/lightspeed-rag-tool-rhel9:latest
```

Referencia: [Bring your own knowledge to OpenShift Lightspeed](https://redhat.com/en/blog/bring-your-own-knowledge-openshift-lightspeed)
