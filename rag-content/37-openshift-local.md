# Red Hat OpenShift Local

_Source: [developers.redhat.com](https://developers.redhat.com/products/openshift-local/overview)_

## Overview

**Red Hat OpenShift Local** (formerly Red Hat CodeReady Containers) brings a **minimal, preconfigured OpenShift cluster** to your **Linux, macOS, or Windows 10** workstation. Use it to learn OpenShift, prototype microservices, build container images, and run workloads in Kubernetes-hosted containers **without** standing up full data-center infrastructure.

OpenShift Local is intended for **local development and testing**, not production.

## Features

- Local **OpenShift Container Platform (OCP)** experience
- Improved startup vs. older minishift-style flows; integrated **service mesh**, **serverless**, and **VS Code OpenShift Connector**
- Auto-install of **xpaas** templates, **anyuid** access controls, and **admin-user** add-ons
- **Single-node** cluster with templates, **local container registry**, and API access

Whatever your language, OpenShift Local hosts your app and mirrors cloud-style Kubernetes workflows on your laptop or desktop.

## Getting started

### Quick-start: install and run OpenShift Local

```bash
# Download from developers.redhat.com/products/openshift-local/download
# After downloading, extract and set up:

# Linux/macOS
tar xvf crc-linux-amd64.tar.xz
sudo cp crc-linux-*/crc /usr/local/bin/

# Initial setup (downloads VM image, ~4GB)
crc setup

# Start the cluster (default: 9GB RAM, 4 CPUs)
crc start

# Get login credentials
crc console --credentials

# Login as developer
eval $(crc oc-env)
oc login -u developer -p developer https://api.crc.testing:6443

# Open the web console
crc console
```

### Quick-start: customize resources

```bash
# Increase memory and CPUs before starting
crc config set memory 16384
crc config set cpus 6
crc start
```

### Quick-start: clean up

```bash
crc stop
crc delete    # removes the VM
crc cleanup   # removes system changes from 'crc setup'
```

### Links

- [Product hub](https://developers.redhat.com/products/openshift-local)
- [Getting started](https://developers.redhat.com/products/openshift-local/getting-started)
- [Installation](https://developers.redhat.com/content-gateway/link/3875380)
- [Release notes (GitHub)](https://github.com/crc-org/crc/releases)
- [Troubleshooting](https://crc.dev/docs/troubleshooting/)

## Technical details

OpenShift Local is the **crc** (CodeReady Containers) family of tools—see upstream release notes and docs for version-specific behavior. Related topics on Red Hat Developer include air-gapped OpenShift, GPU acceleration with OpenShift Local, MCP servers on OpenShift, and MicroShift/MINC for containerized inner-loop testing.

## Articles

Recent articles linked from the product page cover air-gapped operations, NVIDIA GPU sharing with OpenShift Local, MCP server deployment with ToolHive, and MicroShift as a container (MINC).
