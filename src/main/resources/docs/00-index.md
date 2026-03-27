# IA Development From Zero To Hero

This workshop takes you through a complete journey from understanding a business use case to deploying a production-ready AI solution using Model Context Protocol (MCP) Agents.

## Business Use Case: Neuralbank
Neuralbank is a financial institution that provides loans to customers. The challenge: customers experience significant delays in obtaining credit due to manual credit risk assessment processes.

The Solution: Build an MCP Agent that allows commercial agents to query and update credit risk information through a chat interface, accelerating credit decisions while maintaining full compliance and auditability.

## Your Role: Kevin, Java Developer
You are Kevin, a new Java developer joining Neuralbank's development team. Your mission is to build an MCP Agent (customer-service-mcp) that integrates with Neuralbank's infrastructure to automate credit risk management.

### What You'll Build
- MCP Agent: A Quarkus-based service that implements the Model Context Protocol
- Integration: Connect with Neuralbank's credit risk service via Connectivity Link
- Authentication: Secure access using Keycloak
- Chat Interface: Enable commercial agents to interact via Playground (OpenShift AI)
- Observability: Full distributed tracing with OpenTelemetry

## Technologies & Tools
- Red Hat OpenShift AI: Platform for AI/ML workloads
- Model Context Protocol (MCP): Open protocol for AI assistants
- LlamaStack: Open-source framework for generative AI applications
- Red Hat DevSpaces: Cloud-based development environments
- Red Hat Developer Hub: Centralized portal with Software Templates
- Quarkus: Java framework for cloud-native microservices
- Keycloak: Authentication and authorization
- Connectivity Link: Service mesh for secure communication
- OpenTelemetry: Observability framework
- GitLab: Source code management and CI/CD
- ArgoCD: GitOps for declarative deployments

## Learning Path
### 1. Getting Connected
- Understand the Neuralbank business case
- Configure your environment
- Verify access to platform services (OpenShift Console, SSO, GitLab, Quay, Developer Hub)

### 2. MCP Agents & Tools
- Golden Path: Use Developer Hub Software Templates to generate the MCP service
- DevSpaces: Develop in cloud-based workspaces
- Build MCP Agent: Uncomment and configure pre-generated code
- Keycloak User Management: Create and manage users
- Connectivity Link: Understand service mesh communication
- MCP Inspector: Test and validate your agent
- Deploy & Integrate: Connect with Playground
- OpenTelemetry: Monitor distributed traces

### 3. Resources
- Source code repositories
- Installation methods (Ansible, Helm, GitOps)
- Additional documentation

## Key Resources
- Source Code: https://github.com/pkstaz/neuralbank-workshop
- Workshop: https://github.com/maximilianoPizarro/rhoai-roadshow-from-zero-to-hero
- Ansible Setup: https://github.com/panchoraposo/rh1-demo-rhoai3
- Helm Chart: https://github.com/maximilianoPizarro/neuralbank-stack

