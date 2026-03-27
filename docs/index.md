---
layout: default
title: Home
---

<p align="center">
  <img src="{{ '/assets/images/logo.svg' | relative_url }}" alt="Showroom Docs MCP" width="200"/>
</p>

<h1 align="center">Showroom Docs MCP Server</h1>

<p align="center">
  <strong>Quarkus MCP Server for OpenShift Lightspeed</strong><br/>
  <em>Intelligent documentation at your fingertips via chat</em>
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

<p align="center"><strong>English</strong> | <a href="es/">Espanol</a></p>

---

## What is it?

**Showroom Docs MCP Server** is a [Model Context Protocol (MCP)](https://modelcontextprotocol.io/) server built with [Quarkus](https://quarkus.io/) that indexes and exposes documentation from the **"IA Development From Zero To Hero"** workshop (Neuralbank) along with official documentation from **9 Red Hat products**, enabling [OpenShift Lightspeed](https://docs.redhat.com/en/documentation/red_hat_openshift_lightspeed/1.0) to answer questions based on that context.

## Features

- **Intelligent search** by keywords across 3MB+ of indexed documentation
- **4 MCP Tools** exposed via Streamable HTTP for OLS integration
- **25 documents** covering the workshop + Red Hat products
- **Container image** ready for OpenShift deployment
- **Helm chart** for declarative installation

## Indexed Documentation

| Source | Content |
|--------|---------|
| Neuralbank Workshop | Business case, MCP agents, Golden Path, DevSpaces, Keycloak, Connectivity Link, MCP Inspector, Deploy, OpenTelemetry, RAG |
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
# Deploy on OpenShift with Helm
helm install showroom-docs-mcp \
  oci://quay.io/maximilianopizarro/showroom-docs-mcp-chart \
  --namespace openshift-lightspeed

# Or apply manifests directly
oc apply -f k8s/deployment.yaml
```

## Screenshots

### OpenShift Topology with MCP Server Deployed

<p align="center">
  <img src="{{ '/assets/images/topology-overview.png' | relative_url }}" alt="OpenShift Topology showing showroom-docs-mcp deployment" width="100%"/>
</p>

### Lightspeed Chat Using Documentation Tools

<p align="center">
  <img src="{{ '/assets/images/topology-chat.png' | relative_url }}" alt="Lightspeed chat answering questions from MCP documentation" width="100%"/>
</p>

### MCP Tool Output - Knowledge Base Summary

<p align="center">
  <img src="{{ '/assets/images/lightspeed-summary.png' | relative_url }}" alt="getDocSummary tool output in Lightspeed" width="100%"/>
</p>

## Example Questions for Lightspeed

Once deployed, open the OpenShift Lightspeed chat and ask:

- *"How do I install OpenShift Service Mesh 3.3?"*
- *"Explain the architecture of Red Hat Developer Hub"*
- *"What are the steps to deploy a model with OpenShift AI?"*
- *"How do I configure Connectivity Link gateway policies?"*
- *"What is the Neuralbank workshop about?"*
- *"How do I build an MCP agent with Quarkus?"*
- *"How do I set up Pipelines as Code with Tekton?"*
- *"How does OpenShift Lightspeed OLSConfig work?"*
- *"What is RAG and how does it work with vector databases?"*

## Official Red Hat Documentation

| Product | Version | Documentation |
|---------|---------|---------------|
| OpenShift Service Mesh | 3.3 | [docs.redhat.com](https://docs.redhat.com/en/documentation/red_hat_openshift_service_mesh/3.3) |
| Connectivity Link | 1.3 | [docs.redhat.com](https://docs.redhat.com/en/documentation/red_hat_connectivity_link/1.3) |
| Developer Hub | 1.9 | [docs.redhat.com](https://docs.redhat.com/en/documentation/red_hat_developer_hub/1.9) |
| OpenShift Lightspeed | 1.0 | [docs.redhat.com](https://docs.redhat.com/en/documentation/red_hat_openshift_lightspeed/1.0) |
| OpenShift Observability | 1 | [docs.redhat.com](https://docs.redhat.com/en/documentation/red_hat_openshift_observability/1) |
| OpenTelemetry | 3.9 | [docs.redhat.com](https://docs.redhat.com/en/documentation/red_hat_build_of_opentelemetry/3.9) |
| OpenShift Pipelines | 1.21 | [docs.redhat.com](https://docs.redhat.com/en/documentation/red_hat_openshift_pipelines/1.21) |
| API Management | 1 | [docs.redhat.com](https://docs.redhat.com/en/documentation/red_hat_openshift_api_management/1) |
| OpenShift AI | 1 | [docs.redhat.com](https://docs.redhat.com/en/documentation/red_hat_openshift_ai_cloud_service/1) |

[Full installation guide](quickstart/) -- [Architecture](architecture/) -- [OLSConfig configuration](configuration/)
