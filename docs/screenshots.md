---
layout: default
title: Screenshots
permalink: /screenshots/
---

# Screenshots

<p align="center">
  <img src="{{ '/assets/images/logo.svg' | relative_url }}" alt="Logo" width="80"/>
</p>

<p align="center"><strong>English</strong> | <a href="{{ '/es/' | relative_url }}">Espanol</a></p>

---

## OpenShift Topology

### Deployment with Helm Release Notes

<p align="center">
  <img src="{{ '/assets/images/topology-helm-notes.png' | relative_url }}" alt="OpenShift Topology - Helm Release Notes" width="100%"/>
</p>

The OpenShift Topology view shows the Showroom Docs MCP Server deployed alongside OpenShift Lightspeed components in the `openshift-lightspeed` namespace. The Helm release notes panel displays post-install instructions including the MCP endpoint URL and available tools.

### Lightspeed Chat from Topology View

<p align="center">
  <img src="{{ '/assets/images/topology-lightspeed-chat.png' | relative_url }}" alt="OpenShift Topology - Lightspeed Chat" width="100%"/>
</p>

OpenShift Lightspeed chat integrated in the console, answering a question about "Deploy & Integrate" using the `getDocSection` MCP tool. The response is generated from the indexed workshop documentation.

---

## OpenShift Lightspeed - MCP Tool Outputs

### getDocSummary

<p align="center">
  <img src="{{ '/assets/images/lightspeed-tool-summary.png' | relative_url }}" alt="getDocSummary Tool Output" width="100%"/>
</p>

The `getDocSummary` tool returns a complete summary of the knowledge base, listing all indexed workshop modules and Red Hat product documentation with versions and key topics.

### listDocSections

<p align="center">
  <img src="{{ '/assets/images/lightspeed-tool-sections.png' | relative_url }}" alt="listDocSections Tool Output" width="100%"/>
</p>

The `listDocSections` tool output shows the full documentation index with 46 files organized by workshop modules and Red Hat products.

### listDocSections - Lightspeed Response

<p align="center">
  <img src="{{ '/assets/images/lightspeed-tool-list.png' | relative_url }}" alt="listDocSections Lightspeed Response" width="100%"/>
</p>

Lightspeed processes the `listDocSections` output and presents a formatted list of all available Red Hat product documentation with version numbers.

### searchDocs - Connectivity Link

<p align="center">
  <img src="{{ '/assets/images/lightspeed-tool-search.png' | relative_url }}" alt="searchDocs Tool Output - Connectivity Link" width="100%"/>
</p>

The `searchDocs` tool searching for "Red Hat Connectivity Link" returns relevant documentation from the indexed knowledge base with relevance scores.

### searchDocs - NeuralBank MCP Agent

<p align="center">
  <img src="{{ '/assets/images/lightspeed-tool-searchdocs.png' | relative_url }}" alt="searchDocs Tool Output - NeuralBank MCP Agent" width="100%"/>
</p>

Search results for "NeuralBank workshop MCP Agent" showing how the tool finds Developer Hub documentation with MCP server configuration details.

### getDocSection - OpenTelemetry Observability

<p align="center">
  <img src="{{ '/assets/images/lightspeed-observability.png' | relative_url }}" alt="getDocSection - OpenTelemetry Observability" width="100%"/>
</p>

Lightspeed uses the `getDocSection` tool to retrieve and present detailed information about OpenTelemetry Observability, including features for instrumentation and telemetry collection.

### NeuralBank Workshop MCP Agent

<p align="center">
  <img src="{{ '/assets/images/lightspeed-neuralbank-agent.png' | relative_url }}" alt="Lightspeed - NeuralBank Workshop MCP Agent" width="100%"/>
</p>

A conversation with Lightspeed about the NeuralBank workshop MCP Agent integration, showing how it explains LlamaStack Playground and backend services integration with step-by-step configuration.

---

## MCP Inspector

The [MCP Inspector](https://github.com/modelcontextprotocol/inspector) is included as an optional deployment for testing and debugging the MCP server directly, without requiring OpenShift Lightspeed.

### Connecting to the MCP Server

<p align="center">
  <img src="{{ '/assets/images/mcp-inspector-connect.png' | relative_url }}" alt="MCP Inspector - Connect" width="100%"/>
</p>

The Inspector connection dialog configured with **Streamable HTTP** transport type pointing to the internal MCP server service URL.

### Available Tools

<p align="center">
  <img src="{{ '/assets/images/mcp-inspector-tools.png' | relative_url }}" alt="MCP Inspector - Tools" width="100%"/>
</p>

Once connected, the Inspector shows all 4 MCP tools with their descriptions: `getDocSection`, `getDocSummary`, `listDocSections`, and `searchDocs`.

### Executing a Tool

<p align="center">
  <img src="{{ '/assets/images/mcp-inspector-query.png' | relative_url }}" alt="MCP Inspector - Query" width="100%"/>
</p>

Executing the `getDocSection` tool from the Inspector UI, showing the tool parameters and JSON response with the documentation content.
