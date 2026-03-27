# ⚙️ Configure Environment

Now that you're connected to OpenShift AI, let's configure your environment for the Neuralbank MCP Agent project.

## Environment Dashboard

Before we begin, familiarize yourself with the **Environment Dashboard** - your centralized access point to all platform services, tools, and credentials.

![Environment Dashboard](../images/dashboard-env.png)

### Dashboard Access

- **URL**: https://dashboard.apps. 
- **Description**: Centralized dashboard providing quick access to all platform services, tools, and credentials
- **Access**: Available to all users with cluster access

The dashboard provides direct links and credentials for:

- **Platform & Tools**: Keycloak Admin Console, Quay Container Registry
- **DevOps & Development**: GitLab (SCM), Red Hat Developer Hub
- **Demo & GitOps**: NeuralBank App, ArgoCD (GitOps)

?> **Tip**: The dashboard link is also available in the top navigation bar (next to the GitHub icon) when you set your Cluster Domain.

## Verify Your Access

Ensure you can access the following services. **Note**: The basename (cluster domain) is set using the **Cluster Domain** field in the top navigation bar. Click `Save` to update all links.

### OpenShift Console

- **URL**: https://console-openshift-console.apps. 
- **Access**: Use your OpenShift credentials

### Single Sign-On (SSO)

- **URL**: https://sso.apps. 
- **Access**: Use your OpenShift credentials

### GitLab

- **URL**: https://gitlab-gitlab.apps. 
- **Users**:
 - `root` / `backstage`
 - `pe1` / `backstage`
 - `pe2` / `backstage`
 - `pe3` / `backstage`
 - `dev1` / `backstage`
 - `dev2` / `backstage`
 - `dev3` / `backstage`

### Quay Container Registry

- **URL**: https://quay.apps. 
- **Users**:
 - `quayadmin` / `backstage`

### Developer Hub (Backstage)

- **URL**: https://backstage-developer-hub-backstage.apps. 
- **Users**:
 - `pe1` / `backstage`
 - `pe2` / `backstage`
 - `pe3` / `backstage`
 - `dev1` / `backstage`
 - `dev2` / `backstage`
 - `dev3` / `backstage`

### Neuralbank Web Site

- **URL**: https://neuralbank.apps. 
- **Realm**: `neuralbank`
- **Admin User**: `admin` / `Welcome123`
- **All Users Password**: `Welcome123`

#### Available Users

The following users are pre-configured in the `neuralbank` realm with password `Welcome123`:

**US Clients:**
- `robert.anderson@email.com` - Robert Anderson (personal-client)
- `jennifer.martinez@email.com` - Jennifer Martinez (personal-client)
- `david.lee@email.com` - David Lee (personal-client)
- `michelle.garcia@email.com` - Michelle Garcia (personal-client)
- `contact@techcorp.com` - TechCorp Solutions Inc (corporate-client)

**LATAM Clients:**
- `mario.gomez@email.com` - Mario Gómez Morales (personal-client)
- `laura.martinez@email.com` - Laura Martínez Pérez (personal-client)
- `carlos.sanchez.mex@email.com` - Carlos Sánchez Muñoz (personal-client)
- `paulo.silva@email.com` - Paulo Silva Santos (personal-client)
- `ricardo.ferreira@email.com` - Ricardo Ferreira Lima (personal-client)
- `diego.martinez.ar@email.com` - Diego Martínez Suárez (personal-client)
- `lucia.fernandez@email.com` - Lucía Fernández Gómez (personal-client)
- `juan.perez@email.com` - Juan Pérez González (personal-client)
- `maria.silva@email.com` - María Silva Rojas (personal-client)
- `info@comercializadora.mx` - Comercializadora Global SA de CV (corporate-client)
- `contacto@constructora.ar` - Constructora del Sur SRL (corporate-client)

**EU Clients:**
- `antonio.garcia@email.com` - Antonio García López (personal-client)
- `carmen.martin@email.com` - Carmen Martín Sánchez (personal-client)
- `javier.rodriguez@email.com` - Javier Rodríguez Torres (personal-client)
- `william.johnson@email.com` - William Johnson (personal-client)
- `sophie.thompson@email.com` - Sophie Thompson (personal-client)
- `info@inversionesmadrid.es` - Inversiones Madrid SA (corporate-client)

## Environment Variables

Make sure you have the following information ready:

- **Cluster Domain**: ` ` (set in the top navigation bar)
- **Project Name**: `neuralbank` (Neuralbank project)
- **Username**: Your OpenShift username
- **Password**: Your OpenShift password

## Next Steps

Your environment is now configured. In the next section, you'll learn about the **Golden Path** and how to use the Developer Hub to create your development workspace.

?> **Tip** Keep your cluster domain handy - you'll use it throughout this journey. Set it in the top navigation bar and click `Save` to update all documentation links.

---

## Continue Your Journey

✅ **Next**: [Introduction to MCP Agents](3-agents-tools/0-intro) - Learn about MCP and the Neuralbank use case

