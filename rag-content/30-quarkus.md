# Red Hat build of Quarkus

_Source: [developers.redhat.com](https://developers.redhat.com/products/quarkus/overview)_

## Overview

Kubernetes-native Java with low memory footprint and fast boot times for microservices and serverless applications. Quarkus is a Kubernetes-native Java stack tuned for fast startup, low memory footprint, and small application size—ideal for hybrid cloud workloads.

Creating applications for the hybrid cloud involves data persistence, service integration, security, and more. Quarkus supports both traditional and cloud-native architectures, optimizes Java for containers, and can improve developer productivity while reducing operational costs.

## Features

### Use cases

- **Microservices** — Loosely coupled, independently deployable components
- **Serverless** — Optimized scaling with broad connectivity
- **Event-driven** — Real-time information and reactions
- **Kube-native** — Automation and integration with Kubernetes Operators
- **IoT** — Device data collection and exchange
- **Edge** — Processing closer to the source
- **CLI applications** — Lightweight, scriptable tools
- **Traditional applications** — From CLIs to enterprise systems
- **Distributed applications** — Multi-node or multi-server deployments

### Developer productivity

- **Dev mode** — Frictionless inner-loop development ([Quarkus dev mode](https://quarkus.io/guides/maven-tooling#dev-mode))
- **Dev UI** — Visualize extensions, logs, and testing
- **Dev Services** — Automatic provisioning of databases, identity servers, and supporting services
- **Continuous testing** — Background tests on impacted code as you edit
- **CLI** — Create projects, manage extensions, build and run
- **Remote development** — Dev mode in containers with local file sync

### Where to run

Quarkus is available through Red Hat entitlements. You can run it locally (including with OpenShift Local / former CodeReady Containers) and integrate with **Red Hat Integration** and **Red Hat Enterprise Linux**.

## Getting started

### Quick-start: install Quarkus CLI and create a project

```bash
# Install Quarkus CLI via JBang on RHEL/Fedora/macOS
curl -Ls https://sh.jbang.dev | bash -s - trust add https://repo1.maven.org/maven2/io/quarkus/quarkus-cli/
curl -Ls https://sh.jbang.dev | bash -s - app install --fresh --force quarkus@quarkusio

# Create a new Quarkus project
quarkus create app com.example:my-app --extension='resteasy-reactive'

# Run in dev mode (live reload)
cd my-app
quarkus dev
```

### Quick-start: REST endpoint example

```java
@Path("/hello")
public class GreetingResource {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "Hello from RESTEasy Reactive";
    }
}
```

Access at `http://localhost:8080/hello` after running `quarkus dev`.

### Quick-start: deploy to OpenShift

```bash
# Add OpenShift extension
quarkus extension add openshift

# Deploy directly to OpenShift
quarkus build -Dquarkus.kubernetes.deploy=true
```

### Quick-start: generate project online

Use the [Quarkus project generator](https://code.quarkus.redhat.com/) to pick extensions, version, and build tool, then download and run locally.

### Links

- [Overview](https://developers.redhat.com/products/quarkus)
- [Download / install / deploy](https://developers.redhat.com/products/quarkus/download)
- [Getting started](https://developers.redhat.com/products/quarkus/getting-started)
- [Spring developers](https://developers.redhat.com/products/quarkus/spring-developers)
- [Start coding](https://developers.redhat.com/content-gateway/link/3881815)
- Try on **Developer Sandbox** or follow **More ways to try** on the product page

## Technical details

- Red Hat build of Quarkus is based on the upstream [Quarkus](https://quarkus.io/) project ([GitHub](https://github.com/quarkusio/quarkus)).
- Design themes: [container first](https://quarkus.io/container-first/), [imperative and reactive](https://quarkus.io/continuum/), [developer joy](https://quarkus.io/developer-joy/), [libraries and standards](https://quarkus.io/standards/).
- E-book: [Quarkus for Spring Developers](https://developers.redhat.com/e-books/quarkus-spring-developers) maps Spring concepts to Quarkus and Kubernetes-native Java.

## Community

- [Quarkus.io](https://quarkus.io/)
- [@QuarkusIO](https://twitter.com/QuarkusIO)
