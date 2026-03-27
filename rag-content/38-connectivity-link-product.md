# Red Hat Connectivity Link

_Source: [developers.redhat.com](https://developers.redhat.com/products/red-hat-connectivity-link/overview)_

## Overview

Red Hat Connectivity Link **connects, secures, and protects** distributed Kubernetes services using **lightweight policy attachments** on top of the Kubernetes **Gateway API**—the next evolution beyond classic Ingress. Gateway API separates concerns for platform, infrastructure, and application teams.

Connectivity Link attaches policies to gateways for **DNS connectivity**, **automatic TLS**, **authorization**, **rate limiting**, and more—for **single-cluster or multicluster** ingress in a Kubernetes-native way.

## Features

- **Dynamic DNS and multicluster routing** — Route53, Azure DNS, Google DNS, CoreDNS (coming soon); GEO and weighted routing; failover patterns
- **TLS for gateways** — **cert-manager** integration; ACME (e.g. Let’s Encrypt); full certificate lifecycle
- **Authorization** — Policy on Gateway API resources (HTTPRoute, Gateway) using HTTP context, hostnames, user attributes; hierarchical defaults and overrides
- **Rate limiting** — Per route/gateway with defaults and overrides without exposing low-level protocol details
- **Observability** — Role-oriented dashboards: platform (compliance, usage, errors, latency, throughput, multicluster), developers (API metrics), LOB (usage, RPS, SLOs)
- **API design and governance (Developer Preview)** — **Apicurio Studio** and **Apicurio Registry** for schema workflow and management

## Use cases

- **Multicloud / multicluster** — Multiple DNS providers; load balancing and failover across gateways and clusters from one model
- **Standardized ingress policy** — Consistent security, RBAC, and compliance across teams, clusters, clouds, and VMs
- **Location-based routing** — DNS strategies for latency and regulatory placement
- **Developer self-service** — Auth and rate limits as code with **GitOps** for scalable API governance

## Getting started

- [Overview](https://developers.redhat.com/products/red-hat-connectivity-link)
- [Getting started](https://developers.redhat.com/products/red-hat-connectivity-link/getting-started)
- [See it in action](https://developers.redhat.com/products/red-hat-connectivity-link/overview) (CTA on product page)
- Additional product and customer content: follow **Take me there** on the [Developer overview](https://developers.redhat.com/products/red-hat-connectivity-link/overview) to redhat.com

## Technical details

### Upstream: Kuadrant

**Kuadrant** combines Gateway API with Istio-based gateway controllers for TLS, DNS, authentication, authorization, rate limiting, and observability templates across clusters.  
[https://kuadrant.io/](https://kuadrant.io/)

Gateway API reference: [Kubernetes Ingress](https://kubernetes.io/docs/concepts/services-networking/ingress/) evolution to Gateway API resources and roles.
