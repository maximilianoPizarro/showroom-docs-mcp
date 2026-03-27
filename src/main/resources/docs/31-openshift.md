# Red Hat OpenShift

_Source: [developers.redhat.com](https://developers.redhat.com/products/openshift/overview)_

## Overview

Red Hat OpenShift is an enterprise application platform to develop, modernize, and deploy applications at scale—including AI-enabled apps—with a consistent experience across infrastructure choices.

- **Powered by Kubernetes** — Enterprise-ready enhancements for container deployment, scaling, and management
- **Built for developer workflow** — Standardized workflows, multiple environments, CI, release management
- **Continuous security** — Checks across the application lifecycle and stack
- **Public cloud** — Portable across major cloud providers

Explore what’s new in [Red Hat OpenShift 4.21](https://developers.redhat.com/articles/2026/02/03/whats-new-developers-openshift-4-21).

## Features

### Kubernetes engine

- Enterprise Kubernetes with a security-focused stack
- **kubectl** and **OpenShift CLI** for build, deploy, and cluster management
- **Administrator web console** for resources and visualization
- **OpenShift Virtualization** — VMs alongside containers
- **Operator Lifecycle Manager (OLM)** — Install, update, and manage Operators

### Cluster services

- **Install and updates** — OpenShift Installer; updates with minimal disruption
- **Monitoring** — Self-updating stack, dashboards, alerts
- **Networking** — Hybrid cluster traffic management
- **Storage** — Persistent volumes and multiple storage types
- **Authentication and authorization** — RBAC, roles, namespaces, security context constraints
- **Helm** — Charts in the web console; Helm CLI integration
- **Container registry** — Built-in registry; scales with **Red Hat Quay**

### Platform services (workloads)

- **Builds** — [Builds for Red Hat OpenShift](https://docs.redhat.com/en/documentation/builds_for_red_hat_openshift/1.6)
- **CI/CD** — [OpenShift Pipelines](https://docs.redhat.com/en/documentation/red_hat_openshift_pipelines/1.21)
- **GitOps** — [OpenShift GitOps](https://docs.redhat.com/en/documentation/red_hat_openshift_gitops/1.19) with Argo CD patterns
- **Serverless** — [OpenShift Serverless](https://docs.redhat.com/en/documentation/red_hat_openshift_serverless/1.37)
- **Service mesh** — [OpenShift Service Mesh](https://docs.redhat.com/en/documentation/red_hat_openshift_service_mesh/3.2)
- **Cost visibility** — [Lightspeed cost management](https://docs.redhat.com/en/documentation/cost_management_service/1-latest)

### Application services and integrated AI

- **Runtimes** — [OpenShift Runtimes](https://developers.redhat.com/java)
- **API management** — [OpenShift API Management](https://developers.redhat.com/products/red-hat-openshift-api-management/overview)
- **Integration** — [Red Hat Integration](https://developers.redhat.com/products/integration/overview)
- **Messaging** — [Red Hat AMQ](https://developers.redhat.com/products/amq/overview)
- **OpenShift AI** — Jupyter, TensorFlow, PyTorch; model lifecycle on OpenShift
- **AI Inference Server** — Hybrid-cloud inference ([product page](https://www.redhat.com/en/products/ai/inference-server))
- **OpenShift Lightspeed** — Generative AI assistant in the console ([overview](https://developers.redhat.com/products/openshift/lightspeed))

### Developer tools

- **OpenShift Dev Spaces** — Cloud IDEs on Kubernetes
- **Advanced Developer Suite** — Broader developer experience and supply-chain tooling
- **Web terminal** — Operator-based console terminal
- **Dynamic plugins** — Extend console and IDE workflows

### Security and multicluster

- **Zero trust workload identity** — With OpenShift Platform Plus
- **Advanced Cluster Security for Kubernetes** — Shift-left and runtime security
- **Advanced Cluster Management** — Multi-cluster lifecycle and policy
- **Multicluster observability** — Historical data and health views

### Registry and data

- **Quay** — Image management, Clair scanning, georeplication, builds
- **OpenShift Data Foundation** — Software-defined storage for the application lifecycle

## Getting started

### Quick-start: deploy an app with oc CLI

```bash
# Login to your cluster
oc login https://api.your-cluster.example.com:6443 -u developer -p developer

# Create a new project
oc new-project my-app

# Deploy from source (S2I build)
oc new-app https://github.com/sclorg/nodejs-ex.git

# Expose the service externally
oc expose svc/nodejs-ex

# Check the route
oc get route nodejs-ex
```

### Quick-start: deploy from container image

```bash
oc new-app --image=quay.io/my-org/my-image:latest --name=my-service
oc expose svc/my-service
```

### Quick-start: GitOps with Argo CD

```bash
# Install OpenShift GitOps operator (from OperatorHub)
oc apply -f - <<EOF
apiVersion: argoproj.io/v1alpha1
kind: Application
metadata:
  name: my-app
  namespace: openshift-gitops
spec:
  destination:
    namespace: my-app
    server: https://kubernetes.default.svc
  source:
    path: k8s/
    repoURL: https://github.com/my-org/my-app.git
    targetRevision: main
  project: default
  syncPolicy:
    automated:
      prune: true
      selfHeal: true
EOF
```

### Quick-start: try free (no install)

Use the [Developer Sandbox](https://developers.redhat.com/developer-sandbox) for a free OpenShift cluster with no setup required.

### Links

- [Try Developer Sandbox](https://developers.redhat.com/developer-sandbox)
- [Getting started](https://developers.redhat.com/products/openshift/getting-started)
- [Download / sign up](https://developers.redhat.com/products/openshift/download)
- [Architecture & design patterns](https://developers.redhat.com/topics/red-hat-architecture-and-design-patterns)

### Local and related

- [OpenShift Local](https://developers.redhat.com/products/openshift-local)
- [OpenShift Dev Spaces](https://developers.redhat.com/products/openshift-dev-spaces/overview)
- [IDE extensions](https://developers.redhat.com/products/openshift-ide-extensions/overview), [VS Code extensions](https://developers.redhat.com/products/vscode-extensions/overview)

## Technical details

OpenShift Kubernetes Engine delivers foundational capabilities for hybrid cloud containers ([datasheet](https://www.redhat.com/en/resources/openshift-kubernetes-engine-datasheet)). Full portfolio details: [redhat.com OpenShift](https://www.redhat.com/en/technologies/cloud-computing/openshift).
