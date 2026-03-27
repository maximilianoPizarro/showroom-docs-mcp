---
layout: default
title: Home
---

<p align="center">
  <img src="assets/images/logo.svg" alt="Showroom Docs MCP" width="200"/>
</p>

<h1 align="center">Showroom Docs MCP Server</h1>

<p align="center">
  <strong>Quarkus MCP Server for OpenShift Lightspeed RAG</strong><br/>
  <em>Documentacion inteligente al alcance de tu chat</em>
</p>

<p align="center">
  <a href="https://quay.io/repository/maximilianopizarro/showroom-docs-mcp">
    <img src="https://img.shields.io/badge/quay.io-showroom--docs--mcp-blueviolet?logo=redhat" alt="Quay.io"/>
  </a>
  <img src="https://img.shields.io/badge/Quarkus-3.21.3-blue?logo=quarkus" alt="Quarkus"/>
  <img src="https://img.shields.io/badge/MCP-1.8.1-purple" alt="MCP Server"/>
  <img src="https://img.shields.io/badge/Java-21-orange?logo=openjdk" alt="Java 21"/>
  <img src="https://img.shields.io/badge/license-Apache--2.0-green" alt="License"/>
</p>

---

## Que es?

**Showroom Docs MCP Server** es un servidor [Model Context Protocol (MCP)](https://modelcontextprotocol.io/) construido con [Quarkus](https://quarkus.io/) que indexa y expone documentacion del workshop **"IA Development From Zero To Hero"** (Neuralbank) junto con documentacion oficial de **9 productos Red Hat**, permitiendo que [OpenShift Lightspeed](https://docs.redhat.com/en/documentation/red_hat_openshift_lightspeed/1.0) responda preguntas basadas en ese contexto.

## Caracteristicas

- **Busqueda inteligente** por keywords en +3MB de documentacion indexada
- **4 MCP Tools** expuestos via SSE para integracion con OLS
- **25 documentos** cubriendo workshop + productos Red Hat
- **Container image** lista para deploy en OpenShift
- **Helm chart** para instalacion declarativa

## Documentacion indexada

| Fuente | Contenido |
|--------|-----------|
| Workshop Neuralbank | Business case, MCP agents, Golden Path, DevSpaces, Keycloak, Connectivity Link, MCP Inspector, Deploy, OpenTelemetry, RAG |
| OpenShift Service Mesh 3.3 | About, Installing, Observability, Gateways, Updating |
| Connectivity Link 1.3 | Discover, Install, Configure, Observe |
| Developer Hub 1.9 | Install, Configure, Auth, GitHub, MCP Tools |
| OpenShift Lightspeed 1.0 | Install, Configure, Operate, Troubleshoot |
| OpenShift Observability 1 | Overview hub |
| OpenTelemetry 3.9 | Install, Collector, Instrumentation |
| OpenShift Pipelines 1.21 | Install, Pipelines as Code, CI/CD |
| API Management 1 | Getting started, Administering |
| OpenShift AI Cloud Service 1 | Data Science, Model Serving, Llama Stack |

## Quick Start

```bash
# Deploy en OpenShift
oc apply -f https://raw.githubusercontent.com/maximilianoPizarro/showroom-docs-mcp/main/helm/showroom-docs-mcp/templates/deployment.yaml

# O con Helm
helm install showroom-docs-mcp oci://quay.io/maximilianopizarro/showroom-docs-mcp-chart
```

[Guia completa de instalacion](quickstart/) -- [Arquitectura](architecture/) -- [Configuracion OLSConfig](configuration/)
