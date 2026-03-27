---
layout: default
title: Quick Start
---

# Quick Start

<p align="center">
  <img src="assets/images/logo.svg" alt="Logo" width="80"/>
</p>

## Prerequisitos

- OpenShift cluster con OLS (OpenShift Lightspeed) instalado
- `oc` CLI autenticado al cluster
- `helm` CLI (opcional, para instalacion via Helm)

## Opcion 1: Helm Chart (recomendado)

```bash
helm install showroom-docs-mcp \
  oci://quay.io/maximilianopizarro/showroom-docs-mcp-chart \
  --namespace openshift-lightspeed
```

## Opcion 2: Manifiestos directos

```bash
# Clonar el repositorio
git clone https://github.com/maximilianoPizarro/showroom-docs-mcp.git
cd showroom-docs-mcp

# Aplicar manifiestos
oc apply -f k8s/deployment.yaml
```

## Configurar OLSConfig

Agregar el MCP server al `OLSConfig`:

```yaml
apiVersion: ols.openshift.io/v1alpha1
kind: OLSConfig
metadata:
  name: cluster
spec:
  mcpServers:
    - name: showroom-docs-mcp
      timeout: 10
      url: 'http://showroom-docs-mcp.openshift-lightspeed.svc.cluster.local:8080/mcp/sse'
```

Aplicar:

```bash
oc apply -f cluster-ols.yml
```

El OLS operator reiniciara los pods automaticamente.

## Verificar

```bash
# Verificar que el pod esta corriendo
oc get pods -n openshift-lightspeed -l app=showroom-docs-mcp

# Verificar health
oc exec -n openshift-lightspeed deploy/showroom-docs-mcp -- \
  curl -s http://localhost:8080/q/health/ready

# Ver logs
oc logs -n openshift-lightspeed -l app=showroom-docs-mcp -f
```

## Probar

Abre la consola de OpenShift Lightspeed y pregunta:

- *"Que es Neuralbank y cual es el caso de uso?"*
- *"Como configuro el MCP Agent con Quarkus?"*
- *"Como instalo OpenShift Service Mesh?"*
- *"Que es Connectivity Link y como se configura?"*
- *"Como funciona Pipelines as Code?"*

## Desarrollo local

```bash
cd showroom-docs-mcp
./mvnw quarkus:dev

# MCP disponible en http://localhost:8080/mcp/sse
```
