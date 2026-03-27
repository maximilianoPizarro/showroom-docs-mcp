# 🔧 Build MCP Agent (customer-service-mcp)

Now let's work with the `customer-service-mcp` service that was generated from the Golden Path template. This Quarkus-based MCP server handles customer operations and credit risk management for Neuralbank.

## Service Overview

The `customer-service-mcp` service is a **Quarkus MCP Server** that:

1. **Implements MCP Protocol**: Provides standardized interface for AI assistants
2. **Exposes Customer Tools**: Functions to manage customers, credit scores, and risk levels
3. **Integrates with Backend**: Connects to Neuralbank's services via Connectivity Link
4. **Handles Authentication**: Uses Keycloak for secure access
5. **Observability**: Pre-configured OpenTelemetry for distributed tracing

![Customer Service MCP Component](../images/customer-service-mcp-component.png)

## Generated Code Structure

The Golden Path template generated a complete Quarkus project following the [Quarkus MCP Template Skeleton](https://github.com/panchoraposo/rh1-demo/tree/main/software-templates/quarkus-mcp-template/skeleton/src) structure:

```
customer-service-mcp/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/neuralbank/
│   │   │       ├── CustomerServiceMcp.java        (Main server class)
│   │   │       ├── tools/
│   │   │       │   └── CustomerTools.java        (Main tools class - needs uncommenting)
│   │   │       ├── client/
│   │   │       │   └── CustomerClient.java       (REST client for backend)
│   │   │       ├── dto/
│   │   │       │   ├── request/                   (Request DTOs)
│   │   │       │   └── response/                  (Response DTOs)
│   │   │       └── enums/
│   │   │           └── CustomerType.java          (Enumerations)
│   │   └── resources/
│   │       ├── application.properties             (Configuration)
│   │       └── mcp-config.yaml                    (MCP configuration)
│   └── test/
│       └── java/
│           └── com/neuralbank/
├── .devcontainer/
│   └── devcontainer.json                          (DevSpaces config)
├── pom.xml                                         (Maven dependencies)
└── README.md
```

### Key File Location

The main class to modify is located at:
- **GitLab Path**: `/developers/customer-service-mcp/-/blob/main/src/main/java/com/neuralbank/tools/CustomerTools.java`
- **Local Path**: `src/main/java/com/neuralbank/tools/CustomerTools.java`

## Kevin's Main Task: Uncomment updateRiskLevel

The template generates code with **intentional commented sections**. The **main activity** is to uncomment the `updateRiskLevel` method in `CustomerTools.java`. This method allows commercial agents to update customer risk levels through the MCP interface.

### Step 1: Open the Project in DevSpaces

1. **Navigate to DevSpaces**: Open your workspace
2. **Open Project**: Navigate to the `customer-service-mcp` directory
3. **Explore Structure**: Review the generated code

### Step 2: Locate CustomerTools.java

1. **Open File**: `src/main/java/com/neuralbank/tools/CustomerTools.java`
2. **Review Structure**: The class contains multiple tools for customer management:
   - Customer creation and retrieval
   - Customer search
   - Customer updates
   - Credit score operations
   - Customer status management
   - Executive assignment

### Step 3: Uncomment updateRiskLevel (Main Activity)

The **main activity** is to uncomment the `updateRiskLevel` method. This is the core functionality that enables commercial agents to update customer risk levels:

1. **Find the Method**: Look for the commented `updateRiskLevel` method in `CustomerTools.java`
2. **Uncomment**: Remove the comment markers (`/*` and `*/`) around the method
3. **Review Implementation**: Understand how it:
   - Takes customer ID, risk level, and justification
   - Calls the backend service via `CustomerClient`
   - Returns a success message

```java
@Tool(description = "Update the risk level for a customer with justification.")
public String updateRiskLevel(
        @ToolArg(description = "Customer ID. Can be passed as a number (e.g., 2) or string (e.g., \"2\")") String customerId,
        @ToolArg(description = "New risk level: Bajo, Medio, Alto, Muy Alto") String nivelRiesgo,
        @ToolArg(description = "Justification for the risk level change") String justificacion
) {
    // Implementation...
}
```

### Additional Tools (Testing Scenarios)

The `CustomerTools` class includes many other tools that are **pre-configured and ready to use** for testing different scenarios:

- **Customer Management**: `createCustomer`, `getCustomer`, `updateCustomer`
- **Credit Operations**: `getCreditScore`, `calculateCreditScore`
- **Status Management**: `activateCustomer`, `deactivateCustomer`, `blockCustomer`, `unblockCustomer`
- **Search**: `searchCustomers` with various filters
- **Summary**: `getCustomerSummary` for comprehensive customer information

These tools are **already implemented** and can be used to test various scenarios, but the **main focus** is on `updateRiskLevel`.

### Step 4: Review Configuration

1. **application.properties**: Check configuration values
   - Keycloak URL
   - Connectivity Link endpoints
   - Service URLs
   - **OpenTelemetry settings** (see below)

2. **mcp-config.yaml**: Review MCP server configuration
   - Tool definitions
   - Resource definitions
   - Prompt templates

### OpenTelemetry Configuration

The service includes pre-configured OpenTelemetry settings in `application.properties` for distributed tracing:

```properties
# OpenTelemetry Configuration
quarkus.otel.metrics.enabled=true
quarkus.otel.simple=true
quarkus.otel.exporter.otlp.endpoint=http://dev-collector.observability.svc.cluster.local:4317
```

**Configuration Details:**
- **`quarkus.otel.metrics.enabled=true`**: Enables OpenTelemetry metrics collection
- **`quarkus.otel.simple=true`**: Uses simplified OpenTelemetry configuration
- **`quarkus.otel.exporter.otlp.endpoint`**: Points to the OpenTelemetry collector in the `observability` namespace

**Connection to Backend:**
- The `customer-service-mcp` service connects to the backend API at `https://neuralbank.apps.<CLUSTER_DOMAIN>/api`
- Backend endpoints used:
  - `GET /api/customers/{customerId}` - Get customer information
  - `GET /api/customers/{customerId}/email` - Get customer email
  - `GET /api/customers/{customerId}/phone` - Get customer phone
  - `PUT /api/customers/{customerId}` - Update customer information
  - `POST /api/customers/{customerId}/risk-level` - Update risk level
- The OpenTelemetry collector receives traces from `customer-service-mcp`
- Traces include spans for:
  - MCP tool invocations
  - REST client calls to backend services via Connectivity Link
  - Keycloak authentication flows
  - Database operations (if applicable)
- All traces are sent to the collector, which forwards them to the observability backend for visualization

This configuration enables end-to-end distributed tracing from the MCP agent through Connectivity Link to the backend services, providing full visibility into the request flow.

### Step 5: Build and Test Locally

In your DevSpaces terminal:

```bash
# Navigate to project
cd customer-service-mcp

# Build the project
mvn clean install

# Run in dev mode (Quarkus)
mvn quarkus:dev
```

The service will start in development mode, allowing you to:
- Test endpoints locally
- See live code reloading
- Debug issues
- View logs

### Step 6: Commit Your Changes

Once you've uncommented the code and verified it builds:

```bash
# Stage changes
git add .

# Commit
git commit -m "Uncomment MCP tools implementation"

# Push (if repository is configured)
git push
```

?> **Important**: This commit enables the integration with Cursor and Playground. The service needs to be committed before it can be used in the LlamaStack Playground.

## Understanding the Code

### MCP Server Implementation

The main server class (`CustomerServiceMcp.java`) sets up the MCP protocol server:

- **Registers Tools**: Makes tools available to AI assistants
- **Handles Requests**: Processes MCP protocol messages
- **Manages Lifecycle**: Starts and stops the server

### Customer Tools

The `CustomerTools` class provides comprehensive customer management capabilities:

1. **Main Activity - updateRiskLevel**: 
   - Updates customer risk level with justification
   - This is the primary tool for commercial agents
   - Takes customer ID, risk level (Bajo, Medio, Alto, Muy Alto), and justification

2. **Additional Tools** (for testing scenarios):
   - **Customer Operations**: Create, retrieve, update, search customers
   - **Credit Score**: Get and calculate credit scores
   - **Status Management**: Activate, deactivate, block, unblock customers
   - **Executive Assignment**: Assign account executives

### Integration Layer

The `CustomerClient` (REST client) handles:
- **Keycloak Authentication**: Gets access tokens automatically via MicroProfile REST Client
- **Connectivity Link**: Makes secure service-to-service calls to backend services
- **Error Handling**: Manages failures gracefully with proper exception handling
- **OpenTelemetry**: Automatically adds tracing spans for all REST calls

**Backend Connection:**
- The `CustomerClient` is configured to call backend services through Connectivity Link
- All requests are automatically authenticated using Keycloak tokens
- OpenTelemetry traces show the complete flow from MCP tool → REST client → Connectivity Link → Backend service

## Development Workflow

Since the service is developed in DevSpaces (not deployed with `oc` commands), your workflow is:

1. **Edit Code**: Make changes in DevSpaces editor
2. **Build Locally**: Use `mvn clean install`
3. **Test Locally**: Use `mvn quarkus:dev`
4. **Commit Changes**: Git commit and push
5. **Test in Playground**: Use LlamaStack Playground to test MCP integration

## Testing the Service

### Local Testing

With Quarkus dev mode running:

```bash
# Test health endpoint
curl http://localhost:8080/health

# Test MCP endpoint (SSE)
curl http://localhost:8081/mcp/sse
```

### Integration Testing

Once committed, test in LlamaStack Playground:
1. **Open Playground**: Navigate to LlamaStack Playground
2. **Select MCP Server**: Choose `customer-service-mcp`
3. **Test Tools**: Try querying and updating credit risk

## 🎉 Congratulations!

You've successfully built your MCP Agent! You've learned how to:

- <input type="checkbox" id="build1"> <label for="build1">Navigate the generated project structure</label>
- <input type="checkbox" id="build2"> <label for="build2">Implement the updateRiskLevel tool in CustomerTools</label>
- <input type="checkbox" id="build3"> <label for="build3">Configure OpenTelemetry for observability</label>
- <input type="checkbox" id="build4"> <label for="build4">Integrate with backend services via Connectivity Link</label>
- <input type="checkbox" id="build5"> <label for="build5">Commit and push changes to GitLab</label>

Your MCP Agent is now ready for testing! The `updateRiskLevel` tool will allow commercial agents to modify credit risk assessments based on loan requests.

## Next Steps

Now that you've uncommented the code and committed it, let's test the service using **MCP Inspector** and **Cursor** to verify everything works correctly.

Click **Test with MCP Inspector** to continue.
