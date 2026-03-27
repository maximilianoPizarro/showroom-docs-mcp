# IA Development From Zero To Hero

Welcome to **IA Development From Zero To Hero** - a comprehensive journey from business use case to production-ready AI solution.

## Business Use Case

**Neuralbank** is a financial institution that provides loans to its customers. Currently, customers experience significant delays in obtaining credit due to the manual process of updating credit risk levels. The institution needs a scalable, transparent, and auditable AI solution that, based on customer loan requests, provides a human-interface to update their credit risk level, accelerating credit decisions while minimizing compliance exposure.

## The Scenario

You are **Kevin**, a new Java developer joining Neuralbank's development team. Your mission is to help incorporate **MCP (Model Context Protocol) Agents** into the organization's infrastructure.

### About Neuralbank

Neuralbank operates with a modern architecture:
- **Frontend**: Customer-facing applications
- **Backend**: Java-based services
- **Authentication**: Keycloak for identity management
- **Connectivity**: Connectivity Link for service integration

### The Challenge

The current credit approval process involves manual risk assessment updates, causing delays. Commercial agents need a faster way to:
1. Query customer credit risk information
2. Update credit risk levels based on loan requests
3. Accelerate the decision-making process

### The Solution

An **MCP Agent** integrated into the system that allows commercial agents to:
- Query credit risk information via chat interface (Playground)
- Automatically update credit risk levels
- Provide transparent and auditable operations

## Developer Experience Journey

To improve Developer Experience, the Platform Engineering team has created a **Golden Path** with best practices and quality standards. This includes everything needed for developers like Kevin to:

- Generate a development environment with the agent and code repository
- Work directly on the OpenShift cluster using **DevSpaces** workspace
- Access pre-configured **Software Templates** via **Developer Hub** (consulted through Cursor)
- Test and validate using **MCP Inspector** and **Cursor**
- Monitor operations with **OpenTelemetry** distributed tracing

## What You'll Learn

This journey covers:

1. **OpenShift AI Setup** - Getting connected and configuring your environment
2. **MCP Agents** - Building and deploying agents for credit risk management
3. **Observability** - Monitoring with OpenTelemetry distributed traces

Let's begin your journey from zero to hero!

?> **Note** All images have _lightbox_ attached to them so they can overlay on top of the web page so you can see them. Just click on them ! (and click to minimize again)

?> **Tip** Be sure to set your cluster domain in the top navigation bar and hit `Save` - this updates the documentation hyperlinks to point to your cluster.

![images/top-nav-bar.png](images/top-nav-bar.png)

Your cluster should be available from the Console URL

 Console URL - https://console-openshift-console.apps. 

or if you prefer via the CLI

```bash
oc login --server=https://api.<CLUSTER_DOMAIN>:6443 -u admin -p password
```
