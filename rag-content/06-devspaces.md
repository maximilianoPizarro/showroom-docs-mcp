# 💻 Working with DevSpaces

## What is DevSpaces?

**Red Hat DevSpaces** (formerly CodeReady Workspaces) provides cloud-based development environments that run directly on your OpenShift cluster. This means:

- **No Local Setup**: Everything runs in the cloud
- **Consistent Environment**: Same setup for all developers
- **Resource Efficiency**: Share cluster resources
- **Easy Collaboration**: Share workspaces with team members

For Kevin, DevSpaces means you can:
- Develop directly on OpenShift
- Access all cluster resources
- Test against real services
- Work with the generated `customer-service-mcp` code

## Accessing Your DevSpaces Workspace

The Golden Path template automatically creates a DevSpaces workspace configuration. Here's how to access it:

### Step 1: Access from Developer Hub

The easiest way to open your DevSpaces workspace is directly from Developer Hub:

1. **Navigate to Developer Hub**: <a href="https://backstage-developer-hub-backstage.apps.<CLUSTER_DOMAIN>" target="_blank">https://backstage-developer-hub-backstage.apps.<CLUSTER_DOMAIN></a>
2. **Find Your Component**: Look for the `customer-service-mcp` component
3. **Open DevSpaces**: Click on the DevSpaces link or use the direct URL:
   <a href="https://devspaces.apps.<CLUSTER_DOMAIN>/#https://gitlab-gitlab.apps.<CLUSTER_DOMAIN>/developers/customer-service-mcp" target="_blank">Open DevSpaces for customer-service-mcp</a>

This will automatically open a DevSpaces workspace with the `customer-service-mcp` repository cloned and ready to work.

### Step 2: Alternative Access Methods

You can also access DevSpaces through:

1. **From OpenShift Console**: Go to **Applications** → **Red Hat DevSpaces**
2. **Direct URL**: <a href="https://devspaces.apps.<CLUSTER_DOMAIN>" target="_blank">https://devspaces.apps.<CLUSTER_DOMAIN></a>

### Step 3: Open Your Workspace

1. **Find Your Workspace**: Look for a workspace named `customer-service-mcp`
2. **Click to Open**: This will launch your development environment

### Step 3: Wait for Startup

The workspace will automatically:
- Clone the generated repository
- Start all required containers
- Configure the development environment
- Install necessary tools and dependencies

![DevSpaces Workspaces](../images/devspaces-workspaces.png)

![DevSpaces Initialization](../images/devspaces-init.png)

![DevSpaces Running](../images/devspaces-running.png)

## Workspace Features

Your DevSpaces workspace includes the following features:

| Feature | Description |
|---------|-------------|
| **Integrated Terminal** | Access to OpenShift CLI (`oc`), direct access to cluster resources, run build and test commands, Git operations |
| **Code Editor** | Full IDE experience in the browser, IntelliSense and code completion, Git integration, extension support, multi-file editing |
| **Port Forwarding** | Access services running in the cluster, test MCP Agent endpoints locally, connect to databases, debug running applications |
| **Volume Mounts** | Persistent storage for your code, shared volumes between containers, ConfigMap and Secret mounts, workspace data persistence |

## Development Workflow

In your DevSpaces workspace:

1. **Navigate to Code**: The generated `customer-service-mcp` code is already cloned
2. **Review Structure**: Explore the generated code structure
3. **Uncomment Code**: Find and uncomment the relevant sections (as mentioned in the template)
4. **Build**: Run `mvn clean install` to build the project
5. **Test**: Execute unit and integration tests
6. **Run Locally**: Use Quarkus dev mode for local testing
7. **Commit Changes**: Commit your uncommented code to start working

![DevSpaces Build](../images/devspaces-build.png)

## Preview MCP Service

To preview and test the MCP service locally in your DevSpaces workspace:

1. **Open Terminal**: Open a terminal in your DevSpaces workspace

2. **Navigate to Project**: 
   ```bash
   cd customer-service-mcp
   ```

3. **Check README**: Review the README.md file for specific commands:
   ```bash
   cat README.md
   ```

![DevSpaces README](../images/devspaces-readme.png)

4. **Run MCP Service**: Execute the commands from the README to start the MCP service in development mode. Typically:
   ```bash
   mvn quarkus:dev
   ```

5. **Access MCP Endpoint**: The MCP service will be available at:
   - **Endpoint**: `http://localhost:8081/mcp/sse`
   - **Protocol**: HTTP
   - **Port**: 8081

6. **Test MCP Connection**: You can test the MCP endpoint using curl or MCP Inspector:
   ```bash
   curl http://localhost:8081/mcp/sse
   ```

?> **Tip**: The MCP service uses Server-Sent Events (SSE) for real-time communication. Make sure to configure your MCP client (like Playground or Cursor) to use `http://localhost:8081/mcp/sse` when testing locally in DevSpaces.

## 🎉 Congratulations!

You've successfully set up your DevSpaces workspace! You've learned how to:

- <input type="checkbox" id="devspaces1"> <label for="devspaces1">Access and configure DevSpaces workspaces</label>
- <input type="checkbox" id="devspaces2"> <label for="devspaces2">Navigate the development environment</label>
- <input type="checkbox" id="devspaces3"> <label for="devspaces3">Preview the MCP service locally</label>
- <input type="checkbox" id="devspaces4"> <label for="devspaces4">Understand the development workflow</label>

Your development environment is now ready! You can start building and testing your MCP Agent.

## Working with the Generated Code

The template generates code with commented sections. Your tasks:

1. **Uncomment Tools**: Find `QueryCreditRiskTool.java` and `UpdateCreditRiskTool.java`
2. **Review Implementation**: Understand how the tools work
3. **Configure Properties**: Update `application.properties` with your settings
4. **Test Locally**: Use Quarkus dev mode to test
5. **Commit**: Once ready, commit to enable Cursor and Playground integration

## Connecting to Cluster Services

From your DevSpaces workspace, you can:

- **Access Keycloak**: Test authentication flows
- **Connect to Credit Risk Service**: Test integration via Connectivity Link
- **Use Connectivity Link**: Verify service-to-service communication
- **Access OpenTelemetry**: View traces and metrics
- **View Topology**: Access OpenShift topology view to see the architecture

## Viewing the Neuralbank Topology

To observe the complete Neuralbank architecture:

1. **OpenShift Console**: Navigate to your project (`neuralbank` - Neuralbank project)
2. **Topology View**: Click on "Topology" in the left menu
3. **View Architecture**: You'll see all services including:
   - Frontend services
   - Keycloak
   - Connectivity Link components
   - Backend services
   - Your `customer-service-mcp` (once deployed)

?> **Note** You may need to authenticate in the OpenShift console to view the topology. Use your provided credentials.

## Next Steps

Now that your DevSpaces workspace is ready, let's start **building and configuring the MCP Agent** (`customer-service-mcp`) that will query and update credit risk information.

Click **Build MCP Agent** to continue.
