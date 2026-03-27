# 🎯 Introduction to MCP Agents

?> **Access Note**: To access the OpenShift console and observe the architecture, authenticate using:
- **OpenShift Console**: <a href="https://console-openshift-console.apps.<CLUSTER_DOMAIN>" target="_blank">https://console-openshift-console.apps.<CLUSTER_DOMAIN></a>

## What is MCP?

**Model Context Protocol (MCP)** is an open protocol that enables AI assistants to securely access external data sources and tools. MCP servers provide a standardized way for AI applications to interact with various services, databases, and APIs.

For Neuralbank, MCP Agents serve as intelligent intermediaries that:

- **Query Credit Risk Data**: Retrieve customer credit risk information from backend services
- **Update Risk Levels**: Modify credit risk assessments based on loan requests
- **Provide Transparency**: Maintain audit trails for compliance
- **Accelerate Decisions**: Automate manual processes to speed up loan approvals

## LlamaStack Overview

**LlamaStack** is an open-source framework for building production-ready generative AI applications on Kubernetes. It provides a comprehensive, enterprise-grade platform for deploying, managing, and orchestrating Large Language Models (LLMs), tools, agents, and MCP servers at scale.

![LlamaStack Overview](../images/llama-stack.png)

> **Reference**: This section is based on insights from the Red Hat blog post: ["Llama Stack and the case for an open 'run-anywhere' contract for agents"](https://www.redhat.com/en/blog/llama-stack-and-case-open-run-anywhere-contract-agents)

### The 4 Layers of LlamaStack

LlamaStack is better understood as four distinct layers, rather than just another agent framework:

#### 1. Build Layer (Client SDK/Toolkit)
A familiar surface for building agents, similar to LangChain, LangFlow, and CrewAI. Developers can author agents using common abstractions. The agent artifacts (YAML configs, Python tasks, tool bindings) are portable across environments.

#### 2. Agent Artifacts and Dependencies
The tangible artifacts of agent development that need runtimes and API endpoints for model inference, tool calling, safety, and telemetry. LlamaStack enables both local development and remote endpoint deployments, ensuring artifacts run consistently regardless of backend models or tools.

#### 3. Platform / API Layer
A standardized API surface for core AI services, including inference, memory, tool use, post-training, data and synthetic generation, and evaluation. LlamaStack has built-in support for:
- **OpenAI-compatible APIs**: One of the few open implementations of OpenAI's APIs (Chat Completions, Responses API, file_search, vectorstores, and more)
- **Model Context Protocol (MCP)**: Open protocol for tool calling and standardized communication
- **Extended APIs**: Beyond OpenAI APIs for eval, fine-tuning, model-customization, scoring, and dataset management

#### 4. Provider Model
A plugin system for backends—whether open source or proprietary. This allows you to swap model providers, vector databases, or runtime implementations without touching agent code. Your agent logic remains unchanged while infrastructure can be swapped.

### The Kubernetes Analogy

Much like Kubernetes defined a **control plane + plugin contract** (CNI for networking, CSI for storage, CRI for runtimes) that enabled portability across vendors and clouds, **LlamaStack aims to be the "run-anywhere contract" for agents**:

- **For developers**: The APIs you use and artifacts you produce should run without change across environments
- **For platforms**: The underlying infrastructure (models, vector databases, training runtimes, tool APIs) should be pluggable providers

Think of Kubernetes orchestrating containers, and LlamaStack orchestrating agents and their providers.

### Core Capabilities

LlamaStack offers a complete ecosystem for AI application development:

- **Multiple Model Support**: Seamlessly integrate and switch between various LLM models (Llama, DeepSeek, Mistral, and more) through a unified interface
- **RAG (Retrieval Augmented Generation)**: Built-in vector database support with automatic embedding generation, enabling context-aware responses from your knowledge base
- **Tools and Agents Framework**: Extensible tool system that allows you to create custom functions and chain them together for complex workflows
- **MCP (Model Context Protocol) Integration**: Native support for MCP servers, enabling standardized communication between AI assistants and external data sources
- **Playground Interface**: Interactive web-based UI for testing, debugging, and developing AI applications without writing code
- **Kubernetes-Native**: Designed for OpenShift/Kubernetes, providing scalability, reliability, and enterprise security features
- **Observability**: Built-in monitoring, logging, and tracing capabilities for production deployments

### Architecture Benefits

LlamaStack's architecture provides several advantages for enterprise AI deployments:

- **Microservices-Based**: Modular design allows independent scaling of components (models, tools, agents)
- **API-First**: RESTful and streaming APIs for easy integration with existing systems
- **Security**: Role-based access control, authentication, and secure communication channels
- **High Availability**: Designed for 99.9% uptime with health checks, auto-recovery, and load balancing
- **Resource Management**: Efficient GPU and CPU utilization with intelligent workload scheduling
- **Open Standards**: Supports both OpenAI-compatible APIs (for compatibility) and open standards like MCP (for vendor independence)

### Integration with Neuralbank

The Neuralbank MCP Agent integrates seamlessly with LlamaStack's Playground, creating a powerful workflow:

1. **Commercial agents** interact with the system through LlamaStack's intuitive chat interface
2. **LlamaStack** routes requests to the MCP Agent using the Model Context Protocol
3. **MCP Agent** processes queries and interacts with Neuralbank's backend services
4. **Results** flow back through LlamaStack to provide real-time responses to commercial agents

This integration enables commercial agents to:
- Query customer credit risk information in natural language
- Update risk levels based on loan application parameters
- Get instant feedback on credit decisions
- Maintain full audit trails for compliance

The combination of LlamaStack's AI capabilities and MCP's standardized protocol creates a robust, scalable solution for Neuralbank's credit risk management needs.

## LlamaStack with Podman Desktop

**LlamaStack Podman Desktop** is a local development environment that allows you to run LlamaStack models and services directly on your machine using Podman containers. This provides a convenient way to test and develop AI applications locally before deploying to OpenShift.

![LlamaStack Podman Desktop](../images/llama-stack-podman-desktop.png)

### What is LlamaStack Podman Desktop?

LlamaStack Podman Desktop is an extension for Podman Desktop that integrates LlamaStack's AI capabilities into your local development workflow. It provides:

- **Local Model Execution**: Run LLM models directly on your machine without cloud dependencies
- **Model Catalog**: Access to a curated catalog of pre-configured models
- **Playground Interface**: Interactive UI for testing models and prompts
- **Recipe Templates**: Pre-built templates for common AI tasks
- **FastAPI Integration**: Easy integration with Python applications

### How to Use LlamaStack Podman Desktop

#### 1. Install Podman

**For Windows:**

1. Download Podman Desktop from [podman-desktop.io](https://podman-desktop.io/)
2. Run the installer and follow the setup wizard
3. Ensure WSL2 is installed (Podman Desktop will prompt you if needed)
4. Verify installation:
   ```powershell
   podman --version
   ```

**For Linux:**

```bash
# Ubuntu/Debian
sudo apt-get update
sudo apt-get install -y podman

# Fedora/RHEL
sudo dnf install -y podman

# Verify installation
podman --version
```

**For macOS:**

```bash
# Using Homebrew (recommended)
brew install podman

# Or download from podman-desktop.io
# Verify installation
podman --version
```

#### 2. Install AI Lab Extension

1. Open Podman Desktop
2. Navigate to **Extensions** in the left sidebar
3. Search for **"AI Lab"** or **"LlamaStack"**
4. Click **Install** on the AI Lab extension
5. Wait for the installation to complete

![LlamaStack Podman Desktop Extension](../images/llama-stack-podman-desktop-1.png)

#### 3. Access the Model Catalog

Once the AI Lab extension is installed, you can access the model catalog:

![LlamaStack Model Catalog](../images/llama-stack-podman-desktop-models.png)

The catalog provides:
- **Pre-configured Models**: Ready-to-use LLM models
- **Model Information**: Details about each model (size, capabilities, requirements)
- **One-Click Deployment**: Deploy models with a single click
- **Recipe Templates**: Pre-built templates for common use cases

![LlamaStack Recipe Catalog](../images/llama-stack-podman-desktop-recipe-catalog.png)

#### 4. Using the Playground

The Playground interface allows you to interact with deployed models:

![LlamaStack Playground](../images/llama-stack-podman-desktop-playground.png)

![LlamaStack Playground Interface](../images/llama-stack-podman-desktop-playground-2.png)

Features:
- **Chat Interface**: Natural language interaction with models
- **Prompt Testing**: Test different prompts and see responses
- **Model Switching**: Switch between different deployed models
- **Response Analysis**: Analyze model outputs and performance

### Activity: Testing IBM Granite Model

In this activity, you'll download and test the IBM Granite model using LlamaStack Podman Desktop.

#### Step 1: Download IBM Granite Model

1. Open Podman Desktop with AI Lab extension installed
2. Navigate to the **Model Catalog**
3. Search for **"ibm-granite"** or **"granite"**
4. Click on the IBM Granite model
5. Click **Deploy** or **Download** to start the download
6. Wait for the model to download and deploy (this may take several minutes depending on your internet connection)

#### Step 2: Access the Playground

1. Once the model is deployed, navigate to the **Playground** section
2. Select the **IBM Granite** model from the model selector
3. You should see the chat interface ready for interaction

#### Step 3: Test the Model

Use the following prompt to test the IBM Granite model's code generation capabilities:

```
Write a Python function that takes a list of dictionaries (representing products with 'name', 'price', and 'stock') and returns a new list containing only the products that cost more than 50 dollars, sorted from highest to lowest price. Include exception handling in case the price is not a number and add docstrings in Red Hat format.
```

#### Step 4: Review the Response

The model should generate a Python function similar to this:

```python
def filter_expensive_products(products):
    """
    Filter products that cost more than 50 dollars and sort by price.
    
    This function takes a list of product dictionaries and returns a filtered
    and sorted list containing only products with prices above 50 dollars.
    
    Args:
        products (list): A list of dictionaries, where each dictionary
            contains 'name', 'price', and 'stock' keys.
    
    Returns:
        list: A new list containing only products with price > 50, sorted
            from highest to lowest price.
    
    Raises:
        TypeError: If the input is not a list.
        ValueError: If a product dictionary is missing required keys.
    
    Example:
        >>> products = [
        ...     {'name': 'Laptop', 'price': 1200, 'stock': 5},
        ...     {'name': 'Mouse', 'price': 25, 'stock': 10},
        ...     {'name': 'Keyboard', 'price': 75, 'stock': 8}
        ... ]
        >>> filter_expensive_products(products)
        [{'name': 'Laptop', 'price': 1200, 'stock': 5}, {'name': 'Keyboard', 'price': 75, 'stock': 8}]
    """
    if not isinstance(products, list):
        raise TypeError("Input must be a list")
    
    filtered_products = []
    
    for product in products:
        try:
            # Validate required keys
            if not all(key in product for key in ['name', 'price', 'stock']):
                raise ValueError(f"Product missing required keys: {product}")
            
            # Convert price to number if it's a string
            price = product['price']
            if isinstance(price, str):
                price = float(price)
            
            # Filter products with price > 50
            if isinstance(price, (int, float)) and price > 50:
                filtered_products.append(product)
        except (ValueError, TypeError) as e:
            # Handle cases where price is not a number
            print(f"Warning: Skipping product due to invalid price: {product.get('name', 'Unknown')} - {e}")
            continue
    
    # Sort by price from highest to lowest
    filtered_products.sort(key=lambda x: x['price'], reverse=True)
    
    return filtered_products
```

#### Step 5: Test the Function

You can test the generated function in a Python environment:

```python
# Test with valid products
products = [
    {'name': 'Laptop', 'price': 1200, 'stock': 5},
    {'name': 'Mouse', 'price': 25, 'stock': 10},
    {'name': 'Keyboard', 'price': 75, 'stock': 8},
    {'name': 'Monitor', 'price': 300, 'stock': 3}
]

result = filter_expensive_products(products)
print(result)
# Expected: [{'name': 'Laptop', 'price': 1200, 'stock': 5}, 
#           {'name': 'Monitor', 'price': 300, 'stock': 3}, 
#           {'name': 'Keyboard', 'price': 75, 'stock': 8}]

# Test with invalid price
products_with_invalid = [
    {'name': 'Product A', 'price': 'not_a_number', 'stock': 5},
    {'name': 'Product B', 'price': 100, 'stock': 2}
]

result = filter_expensive_products(products_with_invalid)
# Should handle the invalid price gracefully and return only Product B
```

#### Expected Results

- ✅ The model generates a complete Python function with proper structure
- ✅ Exception handling is included for invalid price values
- ✅ Docstrings follow Red Hat format (with Args, Returns, Raises, Example sections)
- ✅ The function correctly filters products with price > 50
- ✅ Products are sorted from highest to lowest price

#### FastAPI Integration

LlamaStack Podman Desktop also provides FastAPI integration for building API endpoints:

![LlamaStack FastAPI](../images/llama-stack-podman-desktop-fastapi.png)

This allows you to:
- Create REST API endpoints for your models
- Integrate models into existing applications
- Deploy models as microservices
- Test API endpoints using the built-in interface

### Benefits of Local Development

Using LlamaStack Podman Desktop for local development provides:

- **No Cloud Costs**: Test models without incurring cloud charges
- **Faster Iteration**: Quick feedback loop for prompt engineering
- **Privacy**: Keep sensitive data on your local machine
- **Offline Development**: Work without internet connectivity
- **Easy Integration**: Seamless transition from local to cloud deployment

## The Neuralbank MCP Agent

The MCP Agent you'll build will:

1. **Receive Queries**: Commercial agents query customer credit risk via chat interface
2. **Process Requests**: The MCP Agent processes the query and determines required actions
3. **Interact with Services**: Connects to Neuralbank's credit risk service via Connectivity Link
4. **Update Risk Levels**: Modifies credit risk based on loan request parameters
5. **Return Results**: Provides updated information back to the commercial agent

## Neuralbank Architecture

The Neuralbank topology is already installed in your environment. The complete architecture includes:

- **Frontend Services**: Customer and commercial agent interfaces
- **Authentication Layer**: Keycloak for identity management
- **Backend Services**: Credit risk and loan management services
- **Connectivity Link**: Service mesh for secure communication
- **MCP Agent**: The `customer-service-mcp` service you'll develop

![Neuralbank Topology with Connectivity Link](../images/neuralbank-tology-connectivity-link.png)

?> **Note**: The complete Neuralbank topology diagram is available in the [Resources section](6-resources/README) under Helm Chart deployment.

## Architecture Flow

```
Commercial Agent (Frontend/Playground)
         │
         │ Chat Query: "Update credit risk for customer X"
         │ (Authenticated via Keycloak)
         ▼
    LlamaStack Playground
         │
         │ MCP Protocol Request
         ▼
    customer-service-mcp (OpenShift)
         │
         │ Query Credit Risk Service via Connectivity Link
         ▼
    Credit Risk Service (Backend)
         │
         │ Update Risk Level
         ▼
    Database
         │
         │ Return Updated Risk
         ▼
    customer-service-mcp
         │
         │ Return Result via MCP
         ▼
    LlamaStack Playground
         │
         │ Display Result
         ▼
Commercial Agent (Frontend)
    "Credit risk updated successfully"
```

## Key Components

### MCP Server
The MCP server runs on OpenShift and implements the Model Context Protocol. It exposes:
- **Tools**: Functions that can query and update credit risk
- **Resources**: Access to credit risk data
- **Prompts**: Pre-configured prompts for common operations

### Integration Points
- **Keycloak**: Authentication and authorization
- **Connectivity Link**: Service-to-service communication
- **Credit Risk Service**: Backend Java service managing risk data
- **Frontend**: Commercial agent chat interface (Playground)

## Benefits for Neuralbank

<input type="checkbox" id="benefit1"> <label for="benefit1">**Faster Decisions**: Reduce credit approval time from days to minutes</label>  
<input type="checkbox" id="benefit2"> <label for="benefit2">**Scalability**: Handle increasing loan volumes without proportional staff increases</label>  
<input type="checkbox" id="benefit3"> <label for="benefit3">**Compliance**: Full audit trail of all operations</label>  
<input type="checkbox" id="benefit4"> <label for="benefit4">**Transparency**: Clear visibility into risk assessment changes</label>  
<input type="checkbox" id="benefit5"> <label for="benefit5">**Developer Experience**: Easy to develop, test, and deploy using Golden Path</label>  

## What's Next?

Now that you understand the basics, let's explore the **Golden Path** that the Platform Engineering team has prepared for you. This will make it easy to get started with your development environment.

Click **Golden Path & Developer Hub** to continue.
