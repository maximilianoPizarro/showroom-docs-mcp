---
layout: default
title: Inicio
permalink: /es/
---

<p align="center">
  <img src="{{ '/assets/images/logo.svg' | relative_url }}" alt="Showroom Docs MCP" width="200"/>
</p>

<h1 align="center">Showroom Docs MCP Server</h1>

<p align="center">
  <strong>Servidor MCP Quarkus para OpenShift Lightspeed</strong><br/>
  <em>Documentacion inteligente al alcance de tu chat</em>
</p>

<p align="center">
  <a href="https://quay.io/repository/maximilianopizarro/showroom-docs-mcp">
    <img src="https://img.shields.io/badge/quay.io-showroom--docs--mcp-blueviolet?logo=redhat" alt="Quay.io"/>
  </a>
  <img src="https://img.shields.io/badge/Quarkus-3.27.3-blue?logo=quarkus" alt="Quarkus"/>
  <img src="https://img.shields.io/badge/MCP-1.8.1-purple" alt="MCP Server"/>
  <img src="https://img.shields.io/badge/Java-21-orange?logo=openjdk" alt="Java 21"/>
  <img src="https://img.shields.io/badge/license-Apache--2.0-green" alt="License"/>
</p>

<p align="center"><a href="{{ '/' | relative_url }}">English</a> | <strong>Espanol</strong></p>

---

## Que es?

**Showroom Docs MCP Server** es un servidor [Model Context Protocol (MCP)](https://modelcontextprotocol.io/) construido con [Quarkus](https://quarkus.io/) que indexa y expone documentacion del workshop **"IA Development From Zero To Hero"** (Neuralbank) junto con documentacion oficial de **9 productos Red Hat**, permitiendo que [OpenShift Lightspeed](https://docs.redhat.com/en/documentation/red_hat_openshift_lightspeed/1.0) responda preguntas basadas en ese contexto.

## Caracteristicas

- **Busqueda inteligente** por keywords en mas de 2.5 MB de documentacion oficial (PDFs de Red Hat)
- **4 MCP Tools** expuestos via Streamable HTTP para integracion con OLS
- **35 documentos** cubriendo el workshop + productos Red Hat + productos Developer
- **Imagen de contenedor** lista para deploy en OpenShift
- **Helm chart** para instalacion declarativa

## Documentacion indexada

| Fuente | Contenido |
|--------|-----------|
| Workshop Neuralbank | Caso de negocio, agentes MCP, Golden Path, DevSpaces, Keycloak, Connectivity Link, MCP Inspector, Deploy, OpenTelemetry, RAG |
| OpenShift Service Mesh 3.3 | About, Installing, Observability, Gateways, Updating |
| Connectivity Link 1.3 | Discover, Install, Configure, Observe |
| Developer Hub 1.9 | Install, Configure, Auth, GitHub, MCP Tools |
| OpenShift Lightspeed 1.0 | Install, Configure, Operate, Troubleshoot |
| OpenShift Observability 1 | Overview hub |
| OpenTelemetry 3.9 | Install, Collector, Instrumentation |
| OpenShift Pipelines 1.21 | Install, Pipelines as Code, CI/CD |
| API Management 1 | Getting started, Administering |
| OpenShift AI Cloud Service 1 | Data Science, Model Serving, Llama Stack |

## Pasos de instalacion

### Paso 1: Agregar repositorio Helm e instalar

```bash
helm repo add showroom-docs-mcp \
  https://maximilianopizarro.github.io/showroom-docs-mcp/

helm repo update

helm install showroom-docs-mcp showroom-docs-mcp/showroom-docs-mcp \
  --namespace openshift-lightspeed \
  --create-namespace \
  --set image.pullPolicy=Always
```

### Paso 2: Verificar que el pod este corriendo

```bash
oc get pods -n openshift-lightspeed -l app=showroom-docs-mcp
# Esperado: 1/1 Running
```

### Paso 3: Crear credenciales LLM (si es necesario)

```bash
oc create secret generic ols-llm-credentials \
  -n openshift-lightspeed \
  --from-literal=apitoken=<tu-api-token>
```

### Paso 4: Configurar OLSConfig

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
              maxTokensForResponse: 4096
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

> **Importante**: Usa `/mcp` (Streamable HTTP), **no** `/mcp/sse`.

```bash
oc apply -f cluster-ols.yml
```

### Paso 5: Verificar la integracion MCP

```bash
oc logs -n openshift-lightspeed deploy/lightspeed-app-server \
  -c lightspeed-service-api | grep "tools from MCP"

# Esperado:
# Loaded 4 tools from MCP server 'showroom-docs-mcp'
```

### Paso 6: Probar con Lightspeed

Abri la consola web de OpenShift, hace clic en el icono de chat de Lightspeed, y pregunta sobre la documentacion indexada.

## Preguntas de ejemplo para Lightspeed

Una vez desplegado, abri el chat de OpenShift Lightspeed y pregunta:

- *"How do I install OpenShift Service Mesh 3.3?"*
- *"Explain the architecture of Red Hat Developer Hub"*
- *"What are the steps to deploy a model with OpenShift AI?"*
- *"How do I configure Connectivity Link gateway policies?"*
- *"What is the Neuralbank workshop about?"*
- *"How do I build an MCP agent with Quarkus?"*
- *"How do I set up Pipelines as Code with Tekton?"*
- *"How does OpenShift Lightspeed OLSConfig work?"*
- *"What is RAG and how does it work with vector databases?"*

[Guia de instalacion completa](../es/quickstart/) -- [Arquitectura](../es/architecture/) -- [Configuracion OLSConfig](../es/configuration/)
