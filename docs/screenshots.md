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

---

## Developer Sandbox - Step-by-Step Guide

The following screenshots demonstrate the complete manual procedure for deploying and testing the Showroom Docs MCP Server on a [Red Hat Developer Sandbox](https://developers.redhat.com/developer-sandbox) with LiteLLM proxy and Qwen3 model (with native tool calling support).

### Step 1: Deploy with Helm

Deploy the chart to your Developer Sandbox namespace. The Helm chart deploys three components: the MCP server, the MCP Inspector, and the LiteLLM proxy (with PostgreSQL for the dashboard).

```bash
helm repo add showroom-docs-mcp \
  https://maximilianopizarro.github.io/showroom-docs-mcp/

helm install showroom-docs-mcp showroom-docs-mcp/showroom-docs-mcp \
  --set namespace=$(oc project -q) \
  --set image.pullPolicy=Always \
  --set litellm.model.apiKey=$(oc whoami -t)
```

### Step 2: MCP Inspector - Connected with 4 Tools

<p align="center">
  <img src="{{ '/assets/images/inspector-connected-tools.png' | relative_url }}" alt="MCP Inspector - Connected with 4 Tools" width="100%"/>
</p>

The MCP Inspector auto-connects to the MCP server via the internal Kubernetes service URL (`http://showroom-docs-mcp.<namespace>.svc.cluster.local:8080/mcp`). The Tools tab displays all 4 available tools: `getDocSection`, `getDocSummary`, `listDocSections`, and `searchDocs`.

### Step 3: MCP Inspector - Execute a Tool

<p align="center">
  <img src="{{ '/assets/images/inspector-tool-result.png' | relative_url }}" alt="MCP Inspector - Tool Execution Result" width="100%"/>
</p>

Executing the `listDocSections` tool from the Inspector. The result panel shows the JSON response with the full documentation index (197ms response time), confirming the MCP server is operational and returning data correctly.

### Step 4: LiteLLM Dashboard - Registered MCP Server

<p align="center">
  <img src="{{ '/assets/images/litellm-mcp-servers.png' | relative_url }}" alt="LiteLLM Dashboard - MCP Servers" width="100%"/>
</p>

The LiteLLM Dashboard (v1.82.3) **MCP Servers** page shows the `showroom_docs_mcp` server registered and connected. The MCP server is configured via the Helm chart's `litellm-configmap.yaml` with the internal service URL and HTTP transport.

### Step 5: LiteLLM Dashboard - Qwen3 Model

<p align="center">
  <img src="{{ '/assets/images/litellm-models-granite.png' | relative_url }}" alt="LiteLLM Dashboard - Model" width="100%"/>
</p>

The **Models + Endpoints** page shows the Qwen3 model (`openai/isvc-qwen3-8b-fp8`) registered and available through the LiteLLM proxy. The model supports native tool calling via vLLM's `--tool-call-parser`.

### Step 6: LiteLLM Playground - Select Model

<p align="center">
  <img src="{{ '/assets/images/litellm-select-model.png' | relative_url }}" alt="LiteLLM Playground - Select Model" width="100%"/>
</p>

In the LiteLLM **Playground**, open the "Select Model" dropdown to choose the `qwen3` model for chat testing.

### Step 7: LiteLLM Playground - Chat with Qwen3

<p align="center">
  <img src="{{ '/assets/images/litellm-playground-granite.png' | relative_url }}" alt="LiteLLM Playground - Qwen3 Selected" width="100%"/>
</p>

The Playground with the `qwen3` model selected, ready to start a conversation. The configuration panel shows the Endpoint Type (`/v1/chat/completions`), the selected model, and optional fields for Tags and MCP Servers.

### Step 8: LiteLLM Playground - Qwen3 Response

<p align="center">
  <img src="{{ '/assets/images/litellm-chat-response.png' | relative_url }}" alt="LiteLLM Playground - Qwen3 Response" width="100%"/>
</p>

The Qwen3 model responds through the LiteLLM proxy. With native tool calling support, the model can automatically invoke MCP tools when needed.

### API Testing with curl

You can also test the LiteLLM proxy directly via the OpenAI-compatible API:

```bash
LITELLM_HOST=$(oc get route showroom-docs-mcp-litellm -o jsonpath='{.spec.host}')

curl -s https://${LITELLM_HOST}/v1/chat/completions \
  -H "Authorization: Bearer sk-showroom-mcp-1234" \
  -H "Content-Type: application/json" \
  -d '{
    "model": "qwen3",
    "messages": [{"role": "user", "content": "What is OpenShift?"}]
  }'
```

### LiteLLM Dashboard Credentials

| Field | Value |
|-------|-------|
| **Username** | `admin` |
| **Password** | `sk-showroom-mcp-1234` |

> **Note**: The API key uses an OpenShift OAuth token that expires after ~24 hours. Refresh it with:
> ```bash
> helm upgrade showroom-docs-mcp showroom-docs-mcp/showroom-docs-mcp \
>   --set namespace=$(oc project -q) \
>   --set litellm.model.apiKey=$(oc whoami -t)
> ```
