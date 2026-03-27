# 🔗 Connectivity Link

## Overview

**Connectivity Link** is Red Hat's service mesh solution that provides secure, observable, and manageable communication between services in Neuralbank's architecture. It enables:

- **Secure Communication**: Encrypted service-to-service communication
- **Traffic Management**: Load balancing, routing, and rate limiting
- **Observability**: Metrics, logs, and traces
- **Policy Enforcement**: Security and compliance policies

## Connectivity Link Architecture

Connectivity Link provides a comprehensive service mesh for Neuralbank's microservices architecture.

![Connectivity Link Overview](../images/connectivity-link-policy-overview.png)

## Topology View

The Neuralbank topology shows how Connectivity Link connects all services:

![Neuralbank Topology with Connectivity Link](../images/neuralbank-tology-connectivity-link.png)

### Complete Topology

![Connectivity Link Topology](../images/connectivity-link-policy-topology.png)

The topology view shows:

- **Services**: All microservices in the Neuralbank architecture
- **Gateways**: Entry points for external traffic
- **HTTPRoutes**: Routing rules for service communication
- **Policies**: Applied policies (rate limiting, security, etc.)

### Topology Components

#### Gateway

![Topology Gateway](../images/connectivity-link-policy-topology-gateway.png)

The Gateway is the entry point for external traffic:
- Receives requests from clients
- Routes to appropriate services
- Applies gateway-level policies

#### HTTPRoutes

![Topology HTTPRoutes](../images/connectivity-link-policy-topology-httproutes.png)

HTTPRoutes define routing rules:
- **Path Matching**: Routes requests based on URL paths
- **Service Selection**: Directs traffic to specific services
- **Load Balancing**: Distributes requests across service instances

#### Policies

![Topology Policies](../images/connectivity-link-policy-topology-policies.png)

Policies enforce rules and behaviors:
- **Rate Limiting**: Controls request throughput
- **Security**: Authentication and authorization
- **Traffic Shaping**: Quality of service controls

#### Internal Components

![Topology Internals](../images/connectivity-link-policy-topology-internals.png)

Internal components handle:
- **Service Discovery**: Finding and connecting to services
- **Load Balancing**: Distributing traffic
- **Health Checking**: Monitoring service availability

## Rate Limiting

Connectivity Link provides rate limiting capabilities to protect services from overload and ensure fair resource usage.

### Rate Limit Policy Overview

![Rate Limit Policy](../images/connectivity-link-policy-ratelimit.png)

Rate limiting policies:
- **Protect Services**: Prevent overload
- **Ensure Fairness**: Distribute resources equitably
- **Control Costs**: Manage API usage
- **Comply with SLAs**: Meet service level agreements

### Rate Limit Configuration

Rate limits can be configured:
- **Per Service**: Different limits for different services
- **Per User**: User-specific rate limits
- **Per Endpoint**: Endpoint-specific limits
- **Time Windows**: Limits over specific time periods

#### Rate Limit Policy YAML Example

```yaml
apiVersion: networking.connectivity.redhat.com/v1alpha1
kind: RateLimitPolicy
metadata:
  name: customer-service-rate-limit
  namespace: neuralbank
spec:
  targetRef:
    group: networking.connectivity.redhat.com
    kind: HTTPRoute
    name: customer-service-route
  rules:
    - matches:
      - path:
          type: PathPrefix
          value: /api/credit-risk
      limits:
        - limit:
            requests: 100
            unit: Minute
          burst: 10
        - limit:
            requests: 1000
            unit: Hour
          burst: 50
```

**Configuration Options**:
- **requests**: Number of requests allowed
- **unit**: Time unit (Second, Minute, Hour, Day)
- **burst**: Maximum burst capacity (allows temporary spikes)

### Testing Rate Limiting

You can test rate limiting using `curl` commands. **Note**: You'll need an access token to authenticate your requests. See [Obtaining Bearer Token for API Calls](2.5-keycloak-user-management#obtaining-bearer-token-for-api-calls) for detailed instructions on how to obtain a token from Keycloak.

#### Basic Rate Limit Test

```bash
# Test rate limit on backend API
# Replace YOUR_TOKEN with an access token obtained from Keycloak
# Replace 1 with the actual customer ID
curl -v https://neuralbank.apps.<CLUSTER_DOMAIN>/api/customers/1 \
  -H "Authorization: Bearer YOUR_TOKEN" \
  -H "Content-Type: application/json"
```

#### Rate Limit in Action

![Rate Limit in Action](../images/connectivity-link-policy-ratelimit-in-acction.png)

When rate limits are exceeded:
- **429 Too Many Requests**: HTTP status code returned
- **Retry-After Header**: Indicates when to retry
- **Rate Limit Headers**: Show current usage and limits

#### Advanced Rate Limit Test with Headers

```bash
# Test with multiple requests to trigger rate limit
# Replace 1 with the actual customer ID
for i in {1..20}; do
  echo "Request $i:"
  curl -v https://neuralbank.apps.<CLUSTER_DOMAIN>/api/customers/1 \
    -H "Authorization: Bearer YOUR_TOKEN" \
    -H "Content-Type: application/json" \
    -w "\nHTTP Status: %{http_code}\n\n"
  sleep 0.5
done
```

#### Rate Limit API Details

The rate limit API provides:
- **Current Usage**: How many requests have been made
- **Remaining Quota**: How many requests are allowed
- **Reset Time**: When the limit resets
- **Limit Information**: Total allowed requests

**Example curl command with rate limit headers**:

```bash
# Test rate limit and inspect response headers
# Replace 1 with the actual customer ID
curl -v https://neuralbank.apps.<CLUSTER_DOMAIN>/api/customers/1 \
  -H "Authorization: Bearer YOUR_TOKEN" \
  -H "Content-Type: application/json" \
  -i
```

**Expected response headers:**

```
HTTP/1.1 200 OK
X-RateLimit-Limit: 100
X-RateLimit-Remaining: 99
X-RateLimit-Reset: 1640995200
X-RateLimit-Window: 60
Content-Type: application/json
```

**When rate limit is exceeded**:

```bash
# After exceeding rate limit, you'll receive:
# HTTP/1.1 429 Too Many Requests
# X-RateLimit-Limit: 100
# X-RateLimit-Remaining: 0
# X-RateLimit-Reset: 1640995200
# Retry-After: 60
# Content-Type: application/json
# 
# {
#   "error": "Rate limit exceeded",
#   "message": "You have exceeded the rate limit of 100 requests per minute",
#   "retry_after": 60
# }
```

#### Example: Testing Rate Limit with curl

```bash
# Get rate limit information
# Replace 1 with the actual customer ID
curl -v https://neuralbank.apps.<CLUSTER_DOMAIN>/api/customers/1 \
  -H "Authorization: Bearer YOUR_TOKEN" \
  -H "Content-Type: application/json" \
  -i | grep -i "rate-limit"

# Expected headers:
# X-RateLimit-Limit: 100
# X-RateLimit-Remaining: 99
# X-RateLimit-Reset: 1640995200
# Retry-After: 60 (when limit exceeded)
```

### Rate Limit Policy Configuration

Rate limit policies are configured in Connectivity Link:

```yaml
apiVersion: networking.connectivity.redhat.com/v1alpha1
kind: RateLimitPolicy
metadata:
  name: customer-service-rate-limit
  namespace: neuralbank
spec:
  targetRef:
    group: networking.connectivity.redhat.com
    kind: HTTPRoute
    name: customer-service-route
  rules:
    - matches:
      - path:
          type: PathPrefix
          value: /api/credit-risk
      limits:
        - limit:
            requests: 100
            unit: Minute
          burst: 10
```

## Optional: Token-Based Rate Limiting for MCP Service

?> **Note**: This section is **optional** and is typically performed by a **Platform Engineer**. It demonstrates how to configure advanced rate limiting using token-based policies for the MCP service.

### Overview

Token-based rate limiting allows you to apply different rate limits based on user tokens or groups. This is useful for implementing tiered access (e.g., free tier vs. gold tier) for the MCP service.

### Step 1: Create Gateway for MCP Service

First, create a Gateway that will serve as the entry point for the MCP service. The Gateway should be created in the same namespace where the MCP service is deployed (`customer-service-mcp`):

```yaml
apiVersion: gateway.networking.k8s.io/v1
kind: Gateway
metadata:
  name: customer-service-mcp-gateway
  namespace: customer-service-mcp
spec:
  gatewayClassName: istio
  listeners:
    - name: http
      protocol: HTTP
      port: 80
      allowedRoutes:
        namespaces:
          from: Same
```

### Step 2: Create HTTPRoute for MCP Endpoint

Create an HTTPRoute that routes traffic to the MCP service's `/mcp/sse` endpoint. The service is deployed in the `customer-service-mcp` namespace:

```yaml
apiVersion: gateway.networking.k8s.io/v1
kind: HTTPRoute
metadata:
  name: customer-service-mcp-route
  namespace: customer-service-mcp
spec:
  parentRefs:
    - name: customer-service-mcp-gateway
      namespace: customer-service-mcp
  hostnames:
    - customer-service-mcp.neuralbank.apps.<CLUSTER_DOMAIN>
  rules:
    - matches:
        - path:
            type: PathPrefix
            value: /mcp/sse
      backendRefs:
        - name: customer-service-mcp
          namespace: customer-service-mcp
          port: 8081
```

### Step 3: Apply TokenRateLimitPolicy

Apply a TokenRateLimitPolicy to the Gateway to enforce rate limits based on user tokens:

```yaml
apiVersion: kuadrant.io/v1alpha1
kind: TokenRateLimitPolicy
metadata:
  name: token-limit-free
  namespace: istio-system
spec:
  limits:
    free:
      rates:
        - limit: 2
          window: 1m
    gold:
      counters:
        - expression: auth.identity.userid
      rates:
        - limit: 200000
          window: 24h
      when:
        - predicate: 'request.auth.claims["kuadrant.io/groups"].split(",").exists(g, g == "gold")'
  targetRef:
    group: gateway.networking.k8s.io
    kind: Gateway
    name: customer-service-mcp-gateway
```

**Policy Explanation**:
- **Free Tier**: Limited to 2 requests per minute
- **Gold Tier**: Limited to 200,000 requests per 24 hours, with counters based on user ID
- **Gold Detection**: Uses a predicate to check if the user belongs to the "gold" group

#### Example: TokenRateLimitPolicy in LiteMaaS (OpenSource Project)

The following example shows a TokenRateLimitPolicy implementation from the LiteMaaS OpenSource project, demonstrating how token-based rate limiting appears in the OpenShift console:

![TokenRateLimitPolicy LiteMaaS Example](../images/connectivity-link-policy-tokenratelimit-litemaas.png)

?> **Note**: To view the TokenRateLimitPolicy in the OpenShift console, you need to **activate the filter** in the topology view. The filter allows you to display policies and their relationships with Gateways and HTTPRoutes.

### Step 4: Test Rate Limiting

After applying the policy, you can test the rate limiting using `curl` commands. **Note**: You'll need an access token to authenticate your requests. See [Obtaining Bearer Token for API Calls](2.5-keycloak-user-management#obtaining-bearer-token-for-api-calls) for detailed instructions on how to obtain a token from Keycloak.

**Test Commands**:
```bash
# Test MCP endpoint with authentication
# Replace YOUR_TOKEN with an access token obtained from Keycloak
curl -v http://customer-service-mcp.neuralbank.apps.<CLUSTER_DOMAIN>/mcp/sse \
  -H "Authorization: Bearer YOUR_TOKEN" \
  -H "Content-Type: application/json"

# Test multiple requests to trigger rate limit
for i in {1..5}; do
  echo "Request $i:"
  curl -v http://customer-service-mcp.neuralbank.apps.<CLUSTER_DOMAIN>/mcp/sse \
    -H "Authorization: Bearer YOUR_TOKEN" \
    -w "\nHTTP Status: %{http_code}\n\n"
  sleep 1
done
```

**Expected Behavior**:
- Free tier users: After 2 requests in 1 minute, subsequent requests will return `429 Too Many Requests`
- Gold tier users: Can make up to 200,000 requests per 24 hours before hitting the limit

?> **Platform Engineer Note**: This configuration should be applied by a Platform Engineer as part of the infrastructure setup. Developers (like Kevin) typically don't need to configure this, but should be aware of the rate limits when testing their MCP services.

## Service Communication

Connectivity Link enables secure communication between services:

1. **Service Discovery**: Automatically discovers services
2. **mTLS**: Mutual TLS encryption between services
3. **Load Balancing**: Distributes requests across instances
4. **Health Checks**: Monitors service health
5. **Circuit Breaking**: Prevents cascading failures

## Integration with customer-service-mcp

The `customer-service-mcp` service uses Connectivity Link to communicate with the backend API:

### MCP Endpoint

- **MCP Service URL**: `http://customer-service-mcp.neuralbank.apps.<CLUSTER_DOMAIN>/mcp/sse`
- **Protocol**: Server-Sent Events (SSE)
- **Port**: 8081
- **Usage**: Accessed via LlamaStack Playground, MCP Inspector, or Cursor

### Backend API Endpoints

The MCP service calls the following backend endpoints via Connectivity Link:

- **Base URL**: `https://neuralbank.apps.<CLUSTER_DOMAIN>/api`
- **Get Customer**: `GET /api/customers/{customerId}`
- **Get Customer Email**: `GET /api/customers/{customerId}/email`
- **Get Customer Phone**: `GET /api/customers/{customerId}/phone`
- **Update Customer**: `PUT /api/customers/{customerId}`
- **Update Risk Level**: `POST /api/customers/{customerId}/risk-level`

### Connectivity Link Features

The `customer-service-mcp` service uses Connectivity Link to:

- **Call Backend Services**: Secure communication to `neuralbank.apps.<CLUSTER_DOMAIN>/api` endpoints
- **Authenticate Requests**: Use Keycloak tokens for authorization (see [Obtaining Bearer Token for API Calls](2.5-keycloak-user-management#obtaining-bearer-token-for-api-calls))
- **Respect Rate Limits**: Handle rate limit responses gracefully
- **Monitor Traffic**: Track request patterns and performance

## Observability

Connectivity Link provides observability through:

- **Metrics**: Request rates, latencies, error rates
- **Logs**: Request and response logging
- **Traces**: Distributed tracing (integrated with OpenTelemetry)
- **Dashboards**: Visual representation of service health

## 🎉 Congratulations!

You've successfully completed the Connectivity Link activity! You've learned how to:

- <input type="checkbox" id="cl-check1"> <label for="cl-check1">Understand Connectivity Link's service mesh architecture</label>
- <input type="checkbox" id="cl-check2"> <label for="cl-check2">Configure rate limiting policies for your services</label>
- <input type="checkbox" id="cl-check3"> <label for="cl-check3">Set up token-based rate limiting for MCP services</label>
- <input type="checkbox" id="cl-check4"> <label for="cl-check4">Test rate limits using curl commands</label>
- <input type="checkbox" id="cl-check5"> <label for="cl-check5">Integrate Connectivity Link with your MCP agent</label>

You now have a solid understanding of how Connectivity Link provides secure, observable, and manageable communication between services in your Neuralbank architecture.

## Next Steps

Now that you understand Connectivity Link, let's test your `customer-service-mcp` service using **MCP Inspector** and **Cursor**.

Click **Test with MCP Inspector** to continue.

