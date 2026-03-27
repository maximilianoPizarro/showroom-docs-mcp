# Red Hat Developer Hub

_Source: [developers.redhat.com](https://developers.redhat.com/products/developer-hub/overview)_  
_Note: The path `https://developers.redhat.com/products/developerhub/overview` returned 404; this page reflects the current product URL above._

## Overview

Red Hat Developer Hub is an enterprise **internal developer portal** based on **Backstage**. It improves onboarding, streamlines workflows, and scales innovation through a centralized, self-service experience—unifying tools, documentation, and practices while meeting security and compliance needs.

Start with a **no-cost trial** from the product page.

## Features

### Unified developer experience

- Faster developer and application onboarding
- Streamlined development workflows
- Innovation at scale with governance

### Turnkey portal

Enterprise-supported portal vs. DIY Backstage maintenance:

- 24/7 enterprise support
- Built-in enterprise portal capabilities  
  Demo: [Turnkey internal developer portal](https://developers.redhat.com/demos/turnkey-internal-developer-portal)

### Enterprise security

- No-code enterprise **RBAC**
- **Audit logging** for compliance
- Self-service with guardrails  
  Demo: [Enterprise-grade security](https://developers.redhat.com/demos/enterprise-grade-security)

### Extensibility

- **Dynamic plug-ins** — add capabilities without heavy custom code
- Large plug-in ecosystem with verified compatibility per release  
  Demo: [Simplified extensibility](https://developers.redhat.com/demos/simplified-extensibility)

### Developer Lightspeed (AI assistant)

**Developer Lightspeed for Red Hat Developer Hub** helps troubleshoot apps, design features, draft tests and docs, and search less across documentation. You can bring your own LLM for cost, performance, and privacy.

Available with Developer Hub or [Advanced Developer Suite](https://developers.redhat.com/products/advanced-developer-suite); currently in **Developer preview**.

### Key product capabilities

- **Support** — Timely patches, expert help, knowledge base
- **Deployment** — On-premises (including air-gapped) or managed Kubernetes (EKS, AKS, GKE); single deployment environment per organization
- **Red Hat integrations** — OpenShift, Ansible Automation Platform, OpenShift GitOps, OpenShift Pipelines, OpenShift Dev Spaces, Red Hat AI, Quay, cost management, and more
- **Out-of-the-box enterprise** — RBAC, audit logging, multi-cloud scale, adoption analytics, workflow orchestration, verified plug-in catalog, dynamic plug-in management, bulk import, curated templates

## Getting started

### Quick-start: install Developer Hub on OpenShift

```bash
# Install the RHDH operator from OperatorHub
oc apply -f - <<EOF
apiVersion: operators.coreos.com/v1alpha1
kind: Subscription
metadata:
  name: rhdh
  namespace: openshift-operators
spec:
  channel: fast
  name: rhdh
  source: redhat-operators
  sourceNamespace: openshift-marketplace
EOF

# Create a Backstage instance
oc create namespace developer-hub
oc apply -f - <<EOF
apiVersion: rhdh.redhat.com/v1alpha3
kind: Backstage
metadata:
  name: developer-hub
  namespace: developer-hub
spec:
  application:
    replicas: 1
    route:
      enabled: true
EOF
```

### Quick-start: register a component in the catalog

Create a `catalog-info.yaml` in your repo:

```yaml
apiVersion: backstage.io/v1alpha1
kind: Component
metadata:
  name: my-quarkus-app
  description: My Quarkus microservice
  annotations:
    backstage.io/techdocs-ref: dir:.
spec:
  type: service
  lifecycle: production
  owner: team-backend
```

Then register it via the Developer Hub UI: **Create > Register existing component > paste repo URL**.

### Quick-start: create a Golden Path Template

```yaml
apiVersion: scaffolder.backstage.io/v1beta3
kind: Template
metadata:
  name: quarkus-service
  title: Quarkus Service
  description: Create a new Quarkus microservice
spec:
  owner: platform-team
  type: service
  parameters:
    - title: Service details
      properties:
        name:
          title: Name
          type: string
        description:
          title: Description
          type: string
  steps:
    - id: fetch
      name: Fetch template
      action: fetch:template
      input:
        url: ./skeleton
        values:
          name: ${{ parameters.name }}
    - id: publish
      name: Publish to GitLab
      action: publish:gitlab
      input:
        repoUrl: gitlab.example.com?repo=${{ parameters.name }}
```

### Links

- [Overview (RHDH hub)](https://developers.redhat.com/products/rhdh)
- [Getting started](https://developers.redhat.com/products/rhdh/getting-started)
- [Plug-ins](https://developers.redhat.com/products/rhdh/plugins)
- [Developer Hub vs. Backstage](https://developers.redhat.com/products/rhdh/developer-hub-comparison)

## Technical details

Developer Hub combines **Backstage** community innovation with Red Hat enterprise hardening, support, and integrations for platform teams standardizing developer experience across the organization.
