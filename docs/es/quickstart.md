---
layout: default
title: Inicio Rapido
permalink: /es/quickstart/
lang: es
---

# Inicio Rapido

<p align="center">
  <img src="{{ '/assets/images/logo.svg' | relative_url }}" alt="Logo" width="80"/>
</p>

<p align="center"><a href="{{ '/quickstart/' | relative_url }}">English</a> | <strong>Espanol</strong></p>

## Requisitos previos

- Cluster OpenShift con OLS (OpenShift Lightspeed) instalado
- CLI `oc` autenticado en el cluster
- CLI `helm` (opcional, para instalacion con Helm)

## Opcion 1: Helm Chart (recomendado)

```bash
helm repo add showroom-docs-mcp \
  https://maximilianopizarro.github.io/showroom-docs-mcp/

helm install showroom-docs-mcp showroom-docs-mcp/showroom-docs-mcp \
  --namespace openshift-lightspeed \
  --set image.pullPolicy=Always
```

## Opcion 2: Manifiestos directos

```bash
git clone https://github.com/maximilianoPizarro/showroom-docs-mcp.git
cd showroom-docs-mcp
oc apply -f k8s/deployment.yaml
```

## Configurar OLSConfig

Agrega el servidor MCP a tu `OLSConfig`. Usa `/mcp` (Streamable HTTP), no `/mcp/sse`:

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

```bash
oc apply -f cluster-ols.yml
```

## Verificar

```bash
# Verificar que el pod esta corriendo
oc get pods -n openshift-lightspeed -l app=showroom-docs-mcp

# Verificar salud
oc exec -n openshift-lightspeed deploy/showroom-docs-mcp -- \
  curl -s http://localhost:8080/q/health/ready

# Verificar que OLS cargo las tools
oc logs -n openshift-lightspeed deploy/lightspeed-app-server \
  -c lightspeed-service-api | grep "showroom-docs-mcp"
```

## Probar con Lightspeed

Abre el chat de OpenShift Lightspeed en la consola y prueba estas preguntas:

### Productos Red Hat
- *"How do I install OpenShift Service Mesh 3.3?"*
- *"Explain the architecture of Red Hat Developer Hub"*
- *"How do I deploy a model with OpenShift AI?"*
- *"How do I configure Connectivity Link gateway policies?"*

### Workshop Neuralbank
- *"What is the Neuralbank workshop about?"*
- *"How do I build an MCP agent with Quarkus?"*
- *"What is RAG and how does it work with vector databases?"*

### Developer Products (con ejemplos quick-start)
- *"How do I install Quarkus CLI?"*
- *"Quick-start for Ansible playbooks"*
- *"How do I install OpenShift Local?"*

## Arquitectura

<p align="center">
  <img src="{{ '/assets/images/architecture-diagram.svg' | relative_url }}?v=2" alt="Arquitectura: OpenShift Lightspeed + MCP" width="100%"/>
</p>

## Desarrollo local

```bash
cd showroom-docs-mcp
./mvnw quarkus:dev

# MCP disponible en http://localhost:8080/mcp
# Salud en http://localhost:8080/q/health/ready
```
