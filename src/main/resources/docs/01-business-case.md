# 🏦 Neuralbank Business Case

## Overview

**Neuralbank** is a forward-thinking financial institution that provides loans to customers. Like many financial organizations, Neuralbank faces challenges in accelerating credit decisions while maintaining compliance and transparency.

![Business Use Case](../images/business-use-case.png)

## The Problem

Currently, customers experience significant delays when applying for loans. The bottleneck occurs in the **credit risk assessment process**:

1. **Manual Risk Updates**: Credit risk levels must be manually updated by analysts
2. **Time Delays**: The manual process creates delays in credit approval
3. **Compliance Requirements**: All operations must be transparent and auditable
4. **Scalability Issues**: As the institution grows, manual processes don't scale

![Business Use Case 2](../images/business-use-case-2.png)

## The Solution: MCP Agents

To solve this challenge, Neuralbank is implementing an **MCP (Model Context Protocol) Agent** that:

- **Queries Credit Risk**: Commercial agents can query customer credit risk information via a chat interface
- **Updates Risk Levels**: Automatically updates credit risk levels based on loan requests
- **Accelerates Decisions**: Reduces approval time from days to minutes
- **Maintains Compliance**: Provides transparent, auditable operations with full traceability

## Architecture Overview

The Neuralbank topology is already installed in your environment. The complete architecture includes:

Neuralbank's infrastructure includes:

- **Frontend Layer**: Customer Portal and Commercial Agent Interface (Playground)
- **Authentication Layer**: Keycloak for identity management
- **Backend Services**: Java-based services including Credit Risk Service and MCP Agent
- **Connectivity Link**: Service mesh for secure service-to-service communication

?> **Note**: The complete Neuralbank topology diagrams are available later in this journey when you explore the architecture in detail.

## Your Role as Kevin

![Kevin - Java Developer](../images/kevin-dev.png)

As a new Java developer joining Neuralbank, you will:

1. **Understand the Architecture**: Learn about Neuralbank's frontend/backend architecture with Keycloak and Connectivity Link
2. **Work with the Golden Path**: Use the Platform Engineering team's pre-configured development environment
3. **Build the MCP Agent**: Develop and deploy the agent that queries and updates credit risk
4. **Test and Validate**: Use MCP Inspector and Cursor to verify functionality
5. **Integrate with Frontend**: Ensure commercial agents can use the chat interface
6. **Monitor Operations**: Use OpenTelemetry to observe distributed traces

## Success Criteria

By the end of this journey, you will have:

✅ A working MCP Agent deployed on OpenShift  
✅ Integration with Neuralbank's credit risk system  
✅ A chat interface for commercial agents  
✅ Keycloak authentication and user management configured  
✅ Connectivity Link integration for secure service communication  
✅ Full observability with OpenTelemetry traces  
✅ Understanding of best practices for AI/ML on OpenShift

## Next Steps

Ready to begin? Let's start by [configuring your environment](0-getting-connected/2-configure-environment) to access OpenShift AI.
