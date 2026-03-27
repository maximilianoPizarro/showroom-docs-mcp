# 🔐 Keycloak User Management

## Overview

Neuralbank uses **Keycloak** for authentication and authorization. As part of the setup, you may need to create users for testing and development purposes.

## Accessing Keycloak

1. **Navigate to Keycloak Console**:
   <a href="https://sso.apps.<CLUSTER_DOMAIN>" target="_blank">https://sso.apps.<CLUSTER_DOMAIN></a>

2. **Login**: Use your admin credentials to access the Keycloak administration console

![Keycloak Neuralbank](../images/rhbk-neuralbank.png)

## Creating a New User

### Step 1: Navigate to Users

1. **Select Realm**: Choose the `neuralbank` realm from the dropdown
2. **Go to Users**: Click on "Users" in the left menu
3. **Add User**: Click the "Add user" button

![Create User](../images/rhbk-create-user.png)

### Step 2: Configure User Details

1. **Username**: Enter a unique username (e.g., `kevin`, `commercial-agent-1`)
2. **Email**: Enter the user's email address
3. **First Name**: Enter the user's first name
4. **Last Name**: Enter the user's last name
5. **Email Verified**: Toggle if the email is verified
6. **Enabled**: Ensure this is enabled (default)

![Create User Set Password](../images/rhbk-create-user-set-password.png)

### Step 2.5: Verify User Login and Access

After creating the user, you can verify that the user can successfully log in and access the Neuralbank services:

1. **Login to Neuralbank**: Navigate to <a href="https://neuralbank.apps.<CLUSTER_DOMAIN>" target="_blank">https://neuralbank.apps.<CLUSTER_DOMAIN></a>
2. **Enter Credentials**: Use the username and password you just created
3. **Verify Access**: Confirm the user can access the customer list and other services

### Step 3: Set Password

1. **Go to Credentials Tab**: Click on the "Credentials" tab
2. **Set Password**: Click "Set password"
3. **Enter Password**: Provide a secure password
4. **Temporary**: Uncheck "Temporary" if you want a permanent password
5. **Save**: Click "Save"

### Step 4: Assign Roles (Optional)

1. **Go to Role Mappings**: Click on "Role Mappings" tab
2. **Assign Groups**: Add the user to appropriate groups:
   - `US-Clients` - For US-based clients
   - `LATAM-Clients` - For Latin America clients
   - `EU-Clients` - For European clients
3. **Assign Realm Roles**: Add appropriate realm roles:
   - `personal-client` - For individual clients
   - `corporate-client` - For corporate clients
4. **Assign Client Roles**: If needed, assign client-specific roles

## Login Flow

When users access Neuralbank services, they go through the following authentication flow:

![Login Flow](../images/login.png)

### Step 1: Access Service

User attempts to access a protected Neuralbank service:
- **Neuralbank Web Site**: <a href="https://neuralbank.apps.<CLUSTER_DOMAIN>" target="_blank">https://neuralbank.apps.<CLUSTER_DOMAIN></a>
- **Playground**: LlamaStack Playground interface
- **Frontend**: Customer and commercial agent interfaces

### Step 2: Redirect to Keycloak

The service redirects the user to Keycloak for authentication

![Keycloak Login](../images/rhbk-neuralbank-login.png)

After successful authentication, the user is redirected back to the Neuralbank application:

![Neuralbank Home](../images/neuralbank-home.png)

![Neuralbank Home 2](../images/neuralbank-home-2.png)

### Step 3: User Authentication

User enters credentials:
- **Username**: The username created in Keycloak
- **Password**: The password set for the user

### Step 4: Token Issuance

Upon successful authentication, Keycloak issues:
- **Access Token**: Used for API calls
- **Refresh Token**: Used to obtain new access tokens
- **ID Token**: Contains user identity information

### Step 5: Service Access

The user is redirected back to the service with the tokens, allowing access to protected resources

## Testing Authentication

You can test the authentication flow by:

1. **Accessing Playground**: Navigate to the LlamaStack Playground
2. **Login Prompt**: You'll be redirected to Keycloak
3. **Enter Credentials**: Use the user credentials you created
4. **Access Granted**: Once authenticated, you can use the Playground

## Obtaining Bearer Token for API Calls

To make API calls to Neuralbank services programmatically, you need to obtain a bearer token from Keycloak. This token can be used in `curl` commands or any HTTP client to authenticate requests.

### Step 0: Get Client Secret from Keycloak

Before obtaining a token, you need to extract the client secret for the `neuralbank-frontend` client. This is required for the password grant type flow.

1. **Navigate to Keycloak Console**: <a href="https://sso.apps.<CLUSTER_DOMAIN>" target="_blank">https://sso.apps.<CLUSTER_DOMAIN></a>
2. **Select Realm**: Choose the `neuralbank` realm
3. **Go to Clients**: Click on "Clients" in the left menu
4. **Select Client**: Click on `neuralbank-frontend`
5. **Go to Credentials Tab**: Click on the "Credentials" tab
6. **Copy Client Secret**: Copy the "Client secret" value

![Keycloak Client Secret](../images/rhbk-client-secret.png)

?> **Note**: The client secret is sensitive information. Keep it secure and never commit it to version control.

### Step 1: Get Access Token from Keycloak

Use the following `curl` command to obtain an access token using a Neuralbank user's credentials. **Important**: You must include the `client_secret` parameter:

```bash
# Replace <CLUSTER_DOMAIN> with your cluster domain
# Replace <USERNAME> and <PASSWORD> with your Neuralbank user credentials
# Replace <CLIENT_SECRET> with the client secret from Step 0

curl -X POST https://rhbk.apps.<CLUSTER_DOMAIN>/realms/neuralbank/protocol/openid-connect/token \
  -H "Content-Type: application/x-www-form-urlencoded" \
  -d "grant_type=password" \
  -d "client_id=neuralbank-frontend" \
  -d "client_secret=<CLIENT_SECRET>" \
  -d "username=<USERNAME>" \
  -d "password=<PASSWORD>"
```

**Example with actual values**:
```bash
curl -X POST https://rhbk.apps.cluster-gpzvq.gpzvq.sandbox670.opentlc.com/realms/neuralbank/protocol/openid-connect/token \
  -H "Content-Type: application/x-www-form-urlencoded" \
  -d "grant_type=password" \
  -d "client_id=neuralbank-frontend" \
  -d "client_secret=hg6hlG50pjVtiNpzzPpgSQxCJVPVwNLu" \
  -d "username=mapizarr@redhat.com" \
  -d "password=neuralbank"
```

**Expected Response**:
```json
{
  "access_token": "eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJ...",
  "expires_in": 300,
  "refresh_expires_in": 1800,
  "refresh_token": "eyJhbGciOiJIUzUxMiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJ...",
  "token_type": "Bearer",
  "not-before-policy": 0,
  "session_state": "6d7e876c-7bd9-4556-9b22-6dccdb3b9a07",
  "scope": "email profile"
}
```

### Step 2: Extract the Access Token

The response will be a JSON object containing the access token:

```json
{
  "access_token": "eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJ...",
  "expires_in": 300,
  "refresh_expires_in": 1800,
  "refresh_token": "eyJhbGciOiJIUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJ...",
  "token_type": "Bearer",
  "not-before-policy": 0,
  "session_state": "abc123-def456-ghi789",
  "scope": "profile email"
}
```

**Extract the token** (using `jq` if available):
```bash
# Save the response to a variable
# Replace <CLIENT_SECRET> with the client secret from Step 0
TOKEN_RESPONSE=$(curl -s -X POST https://rhbk.apps.<CLUSTER_DOMAIN>/realms/neuralbank/protocol/openid-connect/token \
  -H "Content-Type: application/x-www-form-urlencoded" \
  -d "grant_type=password" \
  -d "client_id=neuralbank-frontend" \
  -d "client_secret=<CLIENT_SECRET>" \
  -d "username=mapizarr@redhat.com" \
  -d "password=neuralbank")

# Extract access token
ACCESS_TOKEN=$(echo $TOKEN_RESPONSE | jq -r '.access_token')

# Display the token (for verification)
echo $ACCESS_TOKEN
```

**Or extract manually** (without `jq`):
```bash
# Get token and extract using grep/sed
# Replace <CLIENT_SECRET> with the client secret from Step 0
ACCESS_TOKEN=$(curl -s -X POST https://rhbk.apps.<CLUSTER_DOMAIN>/realms/neuralbank/protocol/openid-connect/token \
  -H "Content-Type: application/x-www-form-urlencoded" \
  -d "grant_type=password" \
  -d "client_id=neuralbank-frontend" \
  -d "client_secret=<CLIENT_SECRET>" \
  -d "username=mapizarr@redhat.com" \
  -d "password=neuralbank" | grep -o '"access_token":"[^"]*' | sed 's/"access_token":"//')
```

### Step 3: Use Token to Call Backend Services

Once you have the access token, you can use it to make authenticated API calls to Neuralbank backend services:

#### Example 1: Query Customer Information

```bash
# Call the backend API to get customer information
# Replace 1 with the actual customer ID
curl -X GET https://neuralbank.apps.<CLUSTER_DOMAIN>/api/customers/1 \
  -H "Authorization: Bearer $ACCESS_TOKEN" \
  -H "Content-Type: application/json"
```

**Example Response:**

```json
{
  "id": 1,
  "nombre": "Robert",
  "apellido": "Anderson",
  "identificacion": "12345678-9",
  "tipoIdentificacion": "RUT",
  "email": "robert.anderson@email.com",
  "telefono": "+56912345678",
  "direccion": "123 Main Street",
  "ciudad": "Santiago",
  "estadoProvincia": "Región Metropolitana",
  "codigoPostal": "8320000",
  "paisId": 1,
  "tipoCliente": "PERSONAL",
  "activo": true,
  "scoreCrediticio": 750.5,
  "nivelRiesgo": "Medio",
  "ejecutivoId": 10,
  "sucursalId": 5,
  "fechaRegistro": "2024-01-15T10:30:00Z",
  "fechaUltimaActualizacion": "2025-01-10T14:22:00Z"
}
```

#### Example 2: Query Customer Email

```bash
# Get customer email by ID
# Replace 1 with the actual customer ID
curl -X GET https://neuralbank.apps.<CLUSTER_DOMAIN>/api/customers/1/email \
  -H "Authorization: Bearer $ACCESS_TOKEN" \
  -H "Content-Type: application/json"
```

**Example Response:**

```json
{
  "customerId": 1,
  "email": "robert.anderson@email.com"
}
```

#### Example 3: Query Customer Phone

```bash
# Get customer phone by ID
# Replace 1 with the actual customer ID
curl -X GET https://neuralbank.apps.<CLUSTER_DOMAIN>/api/customers/1/phone \
  -H "Authorization: Bearer $ACCESS_TOKEN" \
  -H "Content-Type: application/json"
```

**Example Response:**

```json
{
  "customerId": 1,
  "telefono": "+56912345678"
}
```

#### Example 4: Update Customer Risk Level (via MCP Service)

To update customer risk level, use the MCP service endpoint:

```bash
# Call the MCP service endpoint
# MCP endpoint: customer-service-mcp.neuralbank.apps.<CLUSTER_DOMAIN>/mcp/sse
# This is accessed through LlamaStack Playground or MCP Inspector, not directly via curl
```

?> **Note**: The MCP service endpoint (`customer-service-mcp.neuralbank.apps.<CLUSTER_DOMAIN>/mcp/sse`) is used for MCP protocol communication. For direct backend API calls, use `neuralbank.apps.<CLUSTER_DOMAIN>/api/customers/{id}`.

#### Example 4: Complete Customer Response

Based on the [customer domain model](https://github.com/pkstaz/neuralbank-workshop/tree/master/customer-service/src/main/java/com/neuralbank/domain), here's a complete example of a customer response from the backend API:

```json
{
  "id": 1,
  "nombre": "Robert",
  "apellido": "Anderson",
  "identificacion": "12345678-9",
  "tipoIdentificacion": "RUT",
  "email": "robert.anderson@email.com",
  "telefono": "+56912345678",
  "direccion": "123 Main Street",
  "ciudad": "Santiago",
  "estadoProvincia": "Región Metropolitana",
  "codigoPostal": "8320000",
  "paisId": 1,
  "tipoCliente": "PERSONAL",
  "activo": true,
  "scoreCrediticio": 750.5,
  "nivelRiesgo": "Medio",
  "ejecutivoId": 10,
  "sucursalId": 5,
  "fechaRegistro": "2024-01-15T10:30:00Z",
  "fechaUltimaActualizacion": "2025-01-10T14:22:00Z"
}
```

**Field Descriptions:**
- **id**: Unique customer identifier
- **nombre**: Customer first name
- **apellido**: Customer last name
- **identificacion**: Identification number (RUT, DNI, etc.)
- **tipoIdentificacion**: Type of identification document
- **email**: Customer email address
- **telefono**: Phone number with country code
- **direccion**: Street address
- **ciudad**: City name
- **estadoProvincia**: State or province
- **codigoPostal**: Postal code
- **paisId**: Country identifier
- **tipoCliente**: Customer type (PERSONAL, EMPRESARIAL, CORPORATIVO)
- **activo**: Active status (true/false)
- **scoreCrediticio**: Credit score (0-1000)
- **nivelRiesgo**: Risk level (Bajo, Medio, Alto, Muy Alto)
- **ejecutivoId**: Assigned account executive ID
- **sucursalId**: Branch office ID
- **fechaRegistro**: Registration date (ISO 8601 format)
- **fechaUltimaActualizacion**: Last update timestamp (ISO 8601 format)

#### Example 4: Complete Workflow Script

Here's a complete example that gets a token and makes a call:

```bash
#!/bin/bash

# Configuration
CLUSTER_DOMAIN="cluster-gpzvq.gpzvq.sandbox670.opentlc.com"
USERNAME="mapizarr@redhat.com"
PASSWORD="neuralbank"
CLIENT_SECRET="hg6hlG50pjVtiNpzzPpgSQxCJVPVwNLu"
KEYCLOAK_URL="https://rhbk.apps.${CLUSTER_DOMAIN}/realms/neuralbank/protocol/openid-connect/token"
API_URL="https://neuralbank.apps.${CLUSTER_DOMAIN}/api"

# Step 1: Get access token
echo "Obtaining access token..."
TOKEN_RESPONSE=$(curl -s -X POST "${KEYCLOAK_URL}" \
  -H "Content-Type: application/x-www-form-urlencoded" \
  -d "grant_type=password" \
  -d "client_id=neuralbank-frontend" \
  -d "client_secret=${CLIENT_SECRET}" \
  -d "username=${USERNAME}" \
  -d "password=${PASSWORD}")

# Step 2: Extract token
ACCESS_TOKEN=$(echo $TOKEN_RESPONSE | jq -r '.access_token')

if [ -z "$ACCESS_TOKEN" ] || [ "$ACCESS_TOKEN" == "null" ]; then
  echo "Error: Failed to obtain access token"
  echo "Response: $TOKEN_RESPONSE"
  exit 1
fi

echo "Token obtained successfully!"
echo "Token (first 50 chars): ${ACCESS_TOKEN:0:50}..."

# Step 3: Make API call
echo ""
echo "Calling backend API..."
curl -X GET "${API_URL}/customers/1" \
  -H "Authorization: Bearer $ACCESS_TOKEN" \
  -H "Content-Type: application/json" \
  -v

echo ""
echo "API call completed!"
```

### Token Expiration

Access tokens typically expire after **5 minutes** (300 seconds). If your token expires:

1. **Get a new token**: Repeat Step 1 to obtain a fresh token
2. **Use refresh token**: Use the `refresh_token` from the initial response to get a new access token without re-entering credentials:

```bash
# Refresh the access token
# Replace <CLIENT_SECRET> with the client secret from Step 0
curl -X POST https://rhbk.apps.<CLUSTER_DOMAIN>/realms/neuralbank/protocol/openid-connect/token \
  -H "Content-Type: application/x-www-form-urlencoded" \
  -d "grant_type=refresh_token" \
  -d "client_id=neuralbank-frontend" \
  -d "client_secret=<CLIENT_SECRET>" \
  -d "refresh_token=<REFRESH_TOKEN_FROM_PREVIOUS_RESPONSE>"
```

### Security Best Practices

- **Never commit tokens**: Don't store tokens in version control
- **Use environment variables**: Store tokens in environment variables, not in scripts
- **Token expiration**: Always handle token expiration in your applications
- **HTTPS only**: Always use HTTPS when transmitting tokens
- **Rotate credentials**: Regularly update user passwords

## User Roles and Groups

The Neuralbank realm includes the following groups and realm roles:

### Groups

- **US-Clients**: Clients from the United States
- **LATAM-Clients**: Clients from Latin America
- **EU-Clients**: Clients from Europe

### Realm Roles

- **personal-client**: For individual/personal clients
- **corporate-client**: For corporate/business clients

### Additional Roles

- **commercial-agent**: Can query and update credit risk
- **developer**: Can develop and test MCP agents
- **admin**: Full administrative access
- **viewer**: Read-only access to credit risk data

## Next Steps

Now that you understand user management in Keycloak, let's explore **Connectivity Link** to understand how services communicate securely in Neuralbank.

Click **Connectivity Link** to continue, or proceed to **Build MCP Agent** if you're ready to start coding.

