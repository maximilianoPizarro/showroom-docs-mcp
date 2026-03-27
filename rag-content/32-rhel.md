# Red Hat Enterprise Linux

_Source: [developers.redhat.com](https://developers.redhat.com/products/rhel/overview)_

## Overview

Red Hat Enterprise Linux (RHEL) provides easy access to a more secure OS, a large ecosystem, and broad language and developer tooling for building and deploying applications on hybrid cloud.

Build and deploy with speed and confidence: a consistent, production-ready foundation with integrated tools for on-prem and cloud development.

Highlights:

- **Infrastructure-agnostic** — Bare metal to hybrid and public cloud (AWS, GCP, Azure); certified hardware and cloud images
- **bootc (image mode)** — Deploy RHEL as a bootable container image with OS and workload in one package
- **Command-line assistance** — AI-assisted guidance for build, deploy, and management (RHEL Lightspeed)
- **Integrated management** — Web console, application streams, system roles (Ansible), image builder

You can also **build Linux apps on Windows** using RHEL on WSL.

## Features

### Recent releases

- **RHEL 9.4** — Security and consistency for open hybrid cloud ([what’s new](https://developers.redhat.com/articles/2024/05/01/whats-new-red-hat-enterprise-linux-94), [release notes](https://access.redhat.com/documentation/en-us/red_hat_enterprise_linux/9/html/9.4_release_notes/index))
- **RHEL 10** — Image mode, extensions repository, Podman Desktop integration, command-line assistant ([release notes](https://docs.redhat.com/en/documentation/red_hat_enterprise_linux/10/html/10.0_release_notes))
- **RHEL 8.10** — See Red Hat documentation for release notes

### Developer themes

- **Runs everywhere** — Bare metal, hybrid, major public clouds
- **Developer friendly** — Current language runtimes, toolsets, compilers
- **Containers** — Improved container runtime and base images
- **Management** — System roles based on Ansible for network, storage, compute

### Why develop on RHEL

- **Security and compliance** — SELinux, application allowlisting, live kernel patching, OpenSSL 3
- **Automation and management** — Image builder, system roles, web console
- **Performance** — TuneD profiles, kernel backports for performance with stability
- **Container development** — Integrated container tools and secure base images
- **Cloud access** — [Red Hat Cloud Access](https://www.redhat.com/en/technologies/cloud-computing/cloud-access) for certified public clouds
- **Edge** — Zero-touch provisioning, health visibility, remediation from one interface

### Partner workloads

- **Microsoft SQL Server on RHEL** — Supported configurations and hardening ([overview](https://www.redhat.com/en/technologies/linux-platforms/enterprise-linux/sql-server))
- **RHEL for SAP Solutions** — HA, live kernel patching, monitoring; Smart Management for large estates ([why RHEL for SAP](https://developers.redhat.com/articles/2023/05/30/why-use-rhel-rhel-sap-solutions))

### CentOS migration

- [Migrate CentOS to RHEL](https://developers.redhat.com/articles/2023/04/11/how-use-convert2rhel-migrate-centos-rhel) and [hands-on lab](https://developers.redhat.com/courses/migrate-red-hat-enterprise-linux-centos)
- [Convert2RHEL cheat sheet](https://developers.redhat.com/cheat-sheets/convert-centos-linux-rhel)
- [CentOS and RHEL](https://developers.redhat.com/products/rhel/centos-and-rhel)

## Getting started

### Quick-start: set up a RHEL development environment

```bash
# Register your system
sudo subscription-manager register --username=your-rh-login

# Enable CodeReady Linux Builder (developer tools)
sudo subscription-manager repos --enable codeready-builder-for-rhel-9-x86_64-rpms

# Install development tools
sudo dnf group install "Development Tools"
sudo dnf install gcc make cmake python3-devel nodejs java-17-openjdk-devel
```

### Quick-start: run containers with Podman

```bash
# Pull and run a container (no daemon required)
podman run -d --name my-web -p 8080:8080 registry.access.redhat.com/ubi9/httpd-24

# Build a container from Dockerfile
podman build -t my-app:latest .

# Push to a registry
podman login quay.io
podman push my-app:latest quay.io/my-org/my-app:latest
```

### Quick-start: image mode (bootc) for RHEL 10

```bash
# Build a bootable container image
podman build -t my-rhel-image:latest -f Containerfile.bootc .

# Deploy with podman or push to registry for fleet deployment
podman push my-rhel-image:latest quay.io/my-org/my-rhel-image:latest
```

### Links

- [Download RHEL at no cost](https://developers.redhat.com/products/rhel/download)
- [Get started](https://developers.redhat.com/products/rhel/getting-started)
- [RHEL for business developers](https://developers.redhat.com/products/rhel/business)
- [Overview hub](https://developers.redhat.com/products/rhel)

## Technical details

- **CentOS Stream** — Upstream continuous delivery platform tracking ahead of RHEL ([contribute](https://developers.redhat.com/products/rhel/centos-and-rhel))
- **Fedora** — Upstream for major RHEL releases ([getfedora.org](https://getfedora.org/))
- **InstructLab** — Open project (IBM and Red Hat) for improving LLMs for gen AI ([GitHub](https://github.com/instructlab))

## Community and content

Upstream influence via CentOS Stream and Fedora. Explore e-books and learning on the [RHEL product page](https://developers.redhat.com/products/rhel/overview) (Podman, UBI, microservices, .NET on Linux, and more).
