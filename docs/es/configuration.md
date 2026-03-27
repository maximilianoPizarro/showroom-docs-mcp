---
layout: default
title: Configuracion
permalink: /es/configuration/
---

# Configuracion

<p align="center">
  <img src="{{ '/assets/images/logo.svg' | relative_url }}" alt="Logo" width="80"/>
</p>

<p align="center"><a href="{{ '/configuration/' | relative_url }}">English</a> | <strong>Espanol</strong></p>

## Prerequisitos

Antes de configurar OLSConfig con el servidor showroom-docs-mcp, asegurate de tener:

1. **OpenShift Lightspeed Operator** instalado en tu cluster
2. **Un proveedor LLM** configurado (p. ej. OpenShift AI con vLLM, o cualquier proveedor compatible)
3. **El chart Helm showroom-docs-mcp** desplegado en el espacio de nombres `openshift-lightspeed`

## Configuracion paso a paso

### Paso 1: Instalar el servidor MCP

```bash
# Add the Helm repository
helm repo add showroom-docs-mcp \
  https://maximilianopizarro.github.io/showroom-docs-mcp/

# Install in the openshift-lightspeed namespace
helm install showroom-docs-mcp showroom-docs-mcp/showroom-docs-mcp \
  --namespace openshift-lightspeed \
  --set image.pullPolicy=Always
```

Verifica que el pod este en ejecucion:

```bash
oc get pods -n openshift-lightspeed -l app=showroom-docs-mcp
# Expected: 1/1 Running

oc exec -n openshift-lightspeed deploy/showroom-docs-mcp -- \
  curl -s http://localhost:8080/q/health/ready
# Expected: {"status":"UP","checks":[]}
```

### Paso 2: Crear el secret de credenciales LLM

Si aun no tienes uno, crea el secret de credenciales para tu proveedor LLM:

```bash
oc create secret generic ols-llm-credentials \
  -n openshift-lightspeed \
  --from-literal=apitoken=<your-api-token>
```

> Para OpenShift AI con vLLM (modelos autohospedados), el token puede ser cualquier cadena no vacia ya que la autenticacion se gestiona internamente.

### Paso 3: Habilitar el Feature Gate MCPServer

El feature gate `MCPServer` debe estar habilitado en el spec de OLSConfig:

```yaml
spec:
  featureGates:
    - MCPServer
```

### Paso 4: Configurar los servidores MCP

Anade el servidor showroom-docs-mcp a la seccion `mcpServers`:

```yaml
  mcpServers:
    - name: showroom-docs-mcp
      timeout: 10
      url: 'http://showroom-docs-mcp.openshift-lightspeed.svc.cluster.local:8080/mcp'
```

> **Importante**: Usa `/mcp` (Streamable HTTP), **no** `/mcp/sse`. El cliente OLS usa solicitudes POST que requieren el endpoint Streamable HTTP. Usar `/mcp/sse` provocara un error `405 Method Not Allowed`.

### Paso 5: Aplicar el OLSConfig completo

Guarda lo siguiente como `cluster-ols.yml` (tambien disponible en el repositorio en [`k8s/cluster-ols.yml`](https://github.com/maximilianoPizarro/showroom-docs-mcp/blob/main/k8s/cluster-ols.yml)):

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

Aplicalo:

```bash
oc apply -f cluster-ols.yml
```

El operador OLS reiniciara automaticamente el pod `lightspeed-app-server` con la nueva configuracion.

### Paso 6: Verificar la integracion

Espera a que el nuevo pod de OLS este listo:

```bash
oc get pods -n openshift-lightspeed -l app.kubernetes.io/name=lightspeed-service-api -w
```

Comprueba que OLS carga las herramientas del servidor MCP con exito:

```bash
oc logs -n openshift-lightspeed deploy/lightspeed-app-server \
  -c lightspeed-service-api | grep "tools from MCP"
```

Salida esperada:

```
Loaded 4 tools from MCP server 'showroom-docs-mcp'
```

Si ves `Failed to get tools from MCP server 'showroom-docs-mcp'`, revisa:

| Error | Causa | Solucion |
|-------|-------|----------|
| `405 Method Not Allowed` | La URL termina en `/mcp/sse` | Cambia la URL a `/mcp` |
| `Connection refused` | El pod MCP no esta en ejecucion | Revisa `oc get pods -l app=showroom-docs-mcp` |
| `NoSuchMethodError: LaunchMode.isProduction()` | Incompatibilidad de version de Quarkus | Asegura Quarkus 3.27.3+ en pom.xml |

### Paso 7: Probar en la consola de Lightspeed

Abre la consola web de OpenShift y haz clic en el icono del chat de Lightspeed. Prueba estas preguntas:

**Documentacion de productos Red Hat:**
- "Como instalo OpenShift Service Mesh 3.3?"
- "Explica la arquitectura de Red Hat Developer Hub"
- "Cuales son los pasos para desplegar un modelo con OpenShift AI?"
- "Como configuro las politicas de gateway de Connectivity Link?"
- "Como configuro recolectores de OpenTelemetry?"
- "Como funciona OLSConfig en OpenShift Lightspeed?"
- "Como creo un pipeline de Tekton con Pipelines as Code?"

**Taller Neuralbank:**
- "De que trata el taller Neuralbank?"
- "Como construyo un agente MCP con Quarkus?"
- "Que es RAG y como funciona con bases de datos vectoriales?"

---

## Referencia de campos OLSConfig

| Campo | Descripcion |
|-------|-------------|
| `spec.featureGates` | Lista de feature gates a habilitar. `MCPServer` es necesario para la integracion MCP |
| `spec.llm.providers` | Configuracion del proveedor LLM (nombre del modelo, tipo, URL, credenciales) |
| `spec.mcpServers` | Lista de servidores MCP con nombre, timeout (segundos) y URL |
| `spec.ols.conversationCache` | Almacenamiento del historial de conversacion (se recomienda postgres) |
| `spec.ols.defaultModel` | Nombre del modelo LLM predeterminado a usar |
| `spec.ols.defaultProvider` | Nombre del proveedor LLM predeterminado |
| `spec.ols.deployment` | Numero de replicas de los componentes de OLS |
| `spec.ols.logLevel` | Verbosidad de los registros (DEBUG, INFO, WARNING, ERROR) |

## Configuracion de la aplicacion MCP Server

### application.properties

```properties
quarkus.http.port=8080
quarkus.http.host=0.0.0.0
quarkus.log.level=INFO
quarkus.log.category."com.neuralbank".level=DEBUG
```

### Variables de entorno

| Variable | Valor por defecto | Descripcion |
|----------|-------------------|-------------|
| `QUARKUS_HTTP_PORT` | `8080` | Puerto del servidor |
| `QUARKUS_LOG_LEVEL` | `INFO` | Nivel de registro |
| `JAVA_OPTS_APPEND` | `-Dquarkus.http.host=0.0.0.0` | Opciones JVM adicionales |

## Valores del Helm Chart

El chart incluye un `values.schema.json` para validacion de entrada. Parametros completos:

| Parametro | Tipo | Valor por defecto | Descripcion |
|-----------|------|-------------------|-------------|
| `replicaCount` | int | `1` | Numero de replicas |
| `image.repository` | string | `quay.io/maximilianopizarro/showroom-docs-mcp` | Imagen del contenedor |
| `image.tag` | string | `latest` | Tag de la imagen |
| `image.pullPolicy` | string | `Always` | Politica de descarga (`Always`, `IfNotPresent`, `Never`) |
| `nameOverride` | string | `""` | Sobreescribir nombre del chart |
| `fullnameOverride` | string | `""` | Sobreescribir nombre completo del release |
| `namespace` | string | `openshift-lightspeed` | Namespace destino |
| `service.type` | string | `ClusterIP` | Tipo de servicio (`ClusterIP`, `NodePort`, `LoadBalancer`) |
| `service.port` | int | `8080` | Puerto del servicio |
| `resources.requests.cpu` | string | `100m` | Solicitud de CPU |
| `resources.requests.memory` | string | `256Mi` | Solicitud de memoria |
| `resources.limits.cpu` | string | `500m` | Limite de CPU |
| `resources.limits.memory` | string | `512Mi` | Limite de memoria |
| `readinessProbe.httpGet.path` | string | `/q/health/ready` | Ruta del probe de disponibilidad |
| `readinessProbe.initialDelaySeconds` | int | `5` | Demora inicial del probe de disponibilidad |
| `livenessProbe.httpGet.path` | string | `/q/health/live` | Ruta del probe de vida |
| `livenessProbe.initialDelaySeconds` | int | `10` | Demora inicial del probe de vida |
| `nodeSelector` | object | `{}` | Restricciones de seleccion de nodo |
| `tolerations` | list | `[]` | Toleraciones del pod |
| `affinity` | object | `{}` | Reglas de afinidad del pod |
| `olsConfig.enabled` | bool | `false` | Habilitar integracion OLSConfig |
| `olsConfig.mcpServerName` | string | `showroom-docs-mcp` | Nombre del servidor MCP |
| `olsConfig.mcpServerTimeout` | int | `10` | Timeout MCP (segundos) |

Ejemplo con valores personalizados:

```yaml
replicaCount: 2

image:
  repository: quay.io/maximilianopizarro/showroom-docs-mcp
  tag: latest
  pullPolicy: Always

service:
  type: ClusterIP
  port: 8080

resources:
  requests:
    cpu: 200m
    memory: 512Mi
  limits:
    cpu: 1
    memory: 1Gi

namespace: openshift-lightspeed
```

## Opcion RAG (Trae tu propia documentacion)

Si prefieres RAG nativo en lugar de MCP, usa los archivos markdown en `rag-content/`:

```bash
podman run -it --rm --device=/dev/fuse \
  -v $XDG_RUNTIME_DIR/containers/auth.json:/run/user/0/containers/auth.json:Z \
  -v $(pwd)/rag-content:/markdown:Z \
  -v /tmp/rag-output:/output:Z \
  registry.redhat.io/openshift-lightspeed-tech-preview/lightspeed-rag-tool-rhel9:latest
```

Luego anade la imagen RAG a OLSConfig:

```yaml
spec:
  ols:
    rag:
      - image: image-registry.openshift-image-registry.svc:5000/openshift-lightspeed/showroom-docs-rag:latest
        indexID: showroom_docs_index
        indexPath: /rag/vector_db
```

Referencia: [Trae tu propio conocimiento a OpenShift Lightspeed](https://redhat.com/en/blog/bring-your-own-knowledge-openshift-lightspeed)
