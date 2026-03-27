# Red Hat OpenShift Dev Spaces

_Source: [developers.redhat.com](https://developers.redhat.com/products/openshift-dev-spaces/overview)_

## Overview

Kubernetes-based cloud development environments for enterprise teams. Built on the open source **Eclipse Che** project, OpenShift Dev Spaces uses Kubernetes and containers to give developers and IT staff a consistent, secure, zero-configuration environment comparable to a laptop IDE—with fast startup and familiar workflows.

OpenShift Dev Spaces is included with OpenShift and available in **OperatorHub**. It speeds development while giving operations centralized control.

Try it at no cost in the [Developer Sandbox](https://developers.redhat.com/content-gateway/link/3885616), which includes OpenShift Dev Spaces.

## Features

### Cloud development environments

Focus on code: containerized apps and environments on OpenShift without hand-managing Kubernetes. Access environments via a web link.

### IDEs in the browser

Includes **VS Code — Open Source** and **JetBrains IntelliJ IDEA**. Admins and developers can customize Dev Spaces and other web-based IDEs. Any machine with a modern browser is enough to code, build, test, and run on OpenShift.

### Workspaces as code (**devfile**)

Declarative **devfile** definitions make environments consistent, portable, and reproducible—runtimes, source, tools, and the full code-build-test-debug loop.

### Enterprise integration

Runs inside the corporate network with existing source access patterns. Workspaces can use enterprise proxies and trusted TLS bundles. Access via **OpenShift OAuth** with LDAP or Active Directory. Workspaces are manageable like other Kubernetes resources.

### Kubernetes development

Pre-configured developer credentials on OpenShift; no manual credential juggling for typical inner-loop work.

### Onboarding and samples

Launch workspaces from the developer console **Topology** view. Bundled samples use certified Red Hat container images for common stacks.

### Security

Runs on OpenShift: project isolation, RBAC, security context constraints, quotas, limit ranges, plus product-specific authorization.

## Getting started

### Quick-start: install Dev Spaces operator

```bash
# Install from OperatorHub (CLI)
oc apply -f - <<EOF
apiVersion: operators.coreos.com/v1alpha1
kind: Subscription
metadata:
  name: devspaces
  namespace: openshift-operators
spec:
  channel: stable
  name: devspaces
  source: redhat-operators
  sourceNamespace: openshift-marketplace
EOF

# Create a CheCluster instance
oc create namespace openshift-devspaces
oc apply -f - <<EOF
apiVersion: org.eclipse.che/v2
kind: CheCluster
metadata:
  name: devspaces
  namespace: openshift-devspaces
spec:
  components:
    cheServer:
      debug: false
  devEnvironments:
    defaultEditor: che-incubator/che-code/latest
    storage:
      pvcStrategy: per-user
EOF
```

### Quick-start: create a workspace from a devfile

```yaml
# devfile.yaml in your repo root
schemaVersion: 2.2.0
metadata:
  name: my-quarkus-app
components:
  - name: tools
    container:
      image: registry.redhat.io/devspaces/udi-rhel8:latest
      memoryLimit: 3Gi
commands:
  - id: build
    exec:
      component: tools
      commandLine: mvn package -DskipTests
      workingDir: ${PROJECT_SOURCE}
```

Open `https://devspaces.your-cluster.com/#https://github.com/your-org/your-repo` to launch.

### Links

- [Product overview](https://developers.redhat.com/products/openshift-dev-spaces)
- [Documentation](https://docs.redhat.com/en/documentation/red_hat_openshift_dev_spaces/latest)
- [Developer Sandbox](https://developers.redhat.com/content-gateway/link/3885616)

## Technical details

- Upstream: [Eclipse Che](https://www.eclipse.org/che/) — [GitHub README](https://github.com/eclipse/che/blob/master/README.md)
- Community contribution improves docs, features, and support experience

## Learning and articles

Featured topics on the product site include private AI coding assistants in CDEs (Ollama, Continue), nested containers with user namespaces, Ansible automation in Dev Spaces, and security best practices.
