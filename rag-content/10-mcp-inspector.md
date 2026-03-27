# 🔍 Test with MCP Inspector

Before deploying to production, it's crucial to test your MCP Agent. **MCP Inspector** is a tool that helps you validate MCP server functionality, and **Cursor** can be used to interact with your agent.

## What is MCP Inspector?

**MCP Inspector** is a testing and debugging tool for MCP servers. It allows you to:

- **List Available Tools**: See all tools your MCP server exposes
- **Test Tool Calls**: Execute tools with different parameters
- **Validate Responses**: Ensure tools return correct data
- **Debug Issues**: Identify problems before deployment

## Setting Up MCP Inspector

### Local Installation

MCP Inspector is not running as a service in the cluster. You need to install it locally on your machine. MCP Inspector is a Node.js application that you can run locally to test your MCP servers.

#### Prerequisites

- **Node.js**: Version 18 or higher
- **npm**: Comes with Node.js (version 9 or higher)

#### Installation Steps

##### For Linux

1. **Install Node.js** (if not already installed):
   ```bash
   # Using NodeSource repository (recommended)
   curl -fsSL https://deb.nodesource.com/setup_20.x | sudo -E bash -
   sudo apt-get install -y nodejs
   
   # Or using package manager
   sudo apt update
   sudo apt install nodejs npm
   ```

2. **Verify Installation**:
   ```bash
   node --version  # Should show v18.x.x or higher
   npm --version   # Should show 9.x.x or higher
   ```

3. **Install MCP Inspector**:
   ```bash
   npm install -g @modelcontextprotocol/inspector
   ```

4. **Run MCP Inspector**:
   ```bash
   mcp-inspector
   ```
   
   This will start the MCP Inspector web interface, typically available at `http://localhost:3000`

##### For Windows

1. **Install Node.js**:
   - Download the Windows installer from [nodejs.org](https://nodejs.org/)
   - Run the installer and follow the setup wizard
   - Ensure "Add to PATH" is checked during installation

2. **Verify Installation** (using PowerShell or Command Prompt):
   ```powershell
   node --version  # Should show v18.x.x or higher
   npm --version   # Should show 9.x.x or higher
   ```

3. **Install MCP Inspector**:
   ```powershell
   npm install -g @modelcontextprotocol/inspector
   ```

4. **Run MCP Inspector**:
   ```powershell
   mcp-inspector
   ```
   
   This will start the MCP Inspector web interface, typically available at `http://localhost:3000`

##### For macOS

1. **Install Node.js** (choose one method):
   
   **Option A: Using Homebrew** (recommended):
   ```bash
   brew install node
   ```
   
   **Option B: Using Official Installer**:
   - Download the macOS installer from [nodejs.org](https://nodejs.org/)
   - Run the installer package

2. **Verify Installation**:
   ```bash
   node --version  # Should show v18.x.x or higher
   npm --version   # Should show 9.x.x or higher
   ```

3. **Install MCP Inspector**:
   ```bash
   npm install -g @modelcontextprotocol/inspector
   ```

4. **Run MCP Inspector**:
   ```bash
   mcp-inspector
   ```
   
   This will start the MCP Inspector web interface, typically available at `http://localhost:3000`

#### Alternative: Run from Source

If you prefer to run from source or the global installation doesn't work:

```bash
# Clone the repository
git clone https://github.com/modelcontextprotocol/inspector.git
cd inspector

# Install dependencies
npm install

# Run the inspector
npm start
```

?> **Note**: The MCP Inspector will open in your default web browser. If it doesn't, navigate to `http://localhost:3000` manually.

## Testing Your MCP Agent

### 1. Connect to Your MCP Server

In MCP Inspector:

1. **Add Server**: Click "Add MCP Server"
2. **Enter Details**:
   - **Name**: `neuralbank-credit-risk`
   - **URL**: `http://customer-service-mcp.neuralbank.apps.<CLUSTER_DOMAIN>/mcp/sse`
   - **Protocol**: `MCP` (SSE - Server-Sent Events)

3. **Connect**: Click "Connect"

?> **Important**: Use the external route URL (`http://customer-service-mcp.neuralbank.apps.<CLUSTER_DOMAIN>/mcp/sse`) instead of the internal service URL. The external route is accessible from your local machine, while the internal `svc.cluster.local` URL is only accessible from within the cluster.

### 2. List Available Tools

Once connected, MCP Inspector will show:

- **Tools**: All available tools
  - `query_credit_risk`
  - `update_credit_risk`
- **Resources**: Available data resources
- **Prompts**: Pre-configured prompts

### 3. Test Query Tool

Test the `query_credit_risk` tool:

1. **Select Tool**: Click on `query_credit_risk`
2. **Enter Parameters**:
   ```json
   {
     "customer_id": "CUST-12345"
   }
   ```
3. **Execute**: Click "Execute Tool"
4. **Review Response**:
   ```json
   {
     "customer_id": "CUST-12345",
     "risk_level": "MEDIUM",
     "score": 650,
     "last_updated": "2025-01-15T10:30:00Z"
   }
   ```

### 4. Test Update Tool

Test the `update_credit_risk` tool:

1. **Select Tool**: Click on `update_credit_risk`
2. **Enter Parameters**:
   ```json
   {
     "customer_id": "CUST-12345",
     "loan_amount": "50000",
     "loan_purpose": "HOME_IMPROVEMENT"
   }
   ```
3. **Execute**: Click "Execute Tool"
4. **Review Response**:
   ```json
   {
     "customer_id": "CUST-12345",
     "previous_risk": "MEDIUM",
     "new_risk": "LOW",
     "updated_at": "2025-01-15T11:00:00Z"
   }
   ```

## Using Cursor to Test

**Cursor** can also interact with your MCP Agent directly. This simulates how commercial agents will use the system.

### Connect Cursor to MCP Server

1. **Open Cursor**: In your DevSpaces workspace or locally
2. **Configure MCP**: Add your MCP server configuration
3. **Test Queries**: Ask Cursor to use your MCP tools

### Example Cursor Queries

**Query Credit Risk**:
```
"Use the MCP agent to query credit risk for customer CUST-12345"
```

Cursor will:
1. Identify the appropriate tool (`query_credit_risk`)
2. Call the MCP server with the customer ID
3. Return the credit risk information

**Update Credit Risk**:
```
"Update credit risk for customer CUST-12345 with a loan request of $50,000 for home improvement"
```

Cursor will:
1. Use the `update_credit_risk` tool
2. Pass all required parameters
3. Return the updated risk level

## What is MCP Inspector?

MCP Inspector is a comprehensive testing tool that provides:

- **Tool Discovery**: Automatically discovers all tools your server exposes
- **Parameter Validation**: Ensures required parameters are provided
- **Response Validation**: Verifies responses match expected schemas
- **Error Handling**: Tests error scenarios and edge cases
- **Performance Metrics**: Measures response times and throughput

## Testing Scenarios

### Happy Path Tests

✅ **Query Existing Customer**: Should return valid risk data  
✅ **Update Risk Successfully**: Should update and return new risk level  
✅ **Multiple Concurrent Requests**: Should handle load  

### Error Handling Tests

❌ **Invalid Customer ID**: Should return appropriate error  
❌ **Missing Parameters**: Should validate and reject  
❌ **Service Unavailable**: Should handle backend failures gracefully  

### Integration Tests

🔗 **Keycloak Authentication**: Verify token handling  
🔗 **Connectivity Link**: Test service-to-service communication  
🔗 **Database Updates**: Confirm data persistence  

## Validation Checklist

Before moving to deployment, verify:

- <input type="checkbox" id="check1"> <label for="check1">All tools are discoverable</label>
- <input type="checkbox" id="check2"> <label for="check2">Tools execute successfully with valid parameters</label>
- <input type="checkbox" id="check3"> <label for="check3">Error handling works for invalid inputs</label>
- <input type="checkbox" id="check4"> <label for="check4">Authentication via Keycloak functions correctly</label>
- <input type="checkbox" id="check5"> <label for="check5">Connectivity Link integration works</label>
- <input type="checkbox" id="check6"> <label for="check6">Responses match expected schemas</label>
- <input type="checkbox" id="check7"> <label for="check7">Performance meets requirements (< 2s response time)</label>

## 🎉 Congratulations!

You've successfully tested your MCP Agent! You've learned how to:

- <input type="checkbox" id="test1"> <label for="test1">Install MCP Inspector locally (Windows, Linux, macOS)</label>
- <input type="checkbox" id="test2"> <label for="test2">Connect to your MCP server using external routes</label>
- <input type="checkbox" id="test3"> <label for="test3">Test tools with valid and invalid parameters</label>
- <input type="checkbox" id="test4"> <label for="test4">Validate error handling and responses</label>
- <input type="checkbox" id="test5"> <label for="test5">Use Cursor to interact with your MCP Agent</label>

Your MCP Agent has been thoroughly tested and is ready for deployment! All tools are working correctly and handling edge cases properly.

## Next Steps

Great! Your MCP Agent is tested and validated. Now let's **deploy it to OpenShift** and **integrate it with the frontend** so commercial agents can use it.

Click **Deploy & Integrate** to continue.

