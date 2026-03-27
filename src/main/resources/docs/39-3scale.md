# Red Hat 3scale API Management

_Source: [developers.redhat.com](https://developers.redhat.com/products/3scale/overview)_

## Overview

Red Hat 3scale API Management helps you **share, secure, distribute, control, analyze, and monetize** APIs for internal and external consumers. Deploy **on-prem**, in the **cloud**, as a **managed service**, or in **hybrid** combinations—built for performance, operator control, and growth.

A distinguishing **hybrid architecture** splits:

1. **API gateway (traffic managers)** — Policy *execution* at the edge, with caching  
2. **API manager** — Policy *configuration* and administration  

The two communicate **asynchronously**, so gateways keep working if the control plane is temporarily unavailable, calls avoid routing through the manager (lower latency), and customer traffic/data can remain on customer infrastructure.

## Setup advantages

- API program stays functional if the configuration plane is down
- Traffic does not need to hairpin through the manager
- API call flow and sensitive data stay under customer control
- Rich admin portal, performance dashboards, and **developer portals** for API exposure and docs
- Full feature access via **APIs** and the **3scale Tool Box** for automation

## Features

### Developer focused

Custom developer portal and interactive API documentation for consumers.

### Operations and scale

- **Agility** — Add APIs or consumers quickly  
- **Lower operational cost** — Automated signup and billing  
- **Deployment flexibility** — Independently scale managers and gateways; managed and hosted options  
- **Horizontal scale** — Add gateways as load grows  
- **Time to market** — Reduce bespoke build-and-deploy cycles for API program changes  

## Getting started

- [Overview](https://developers.redhat.com/products/3scale)
- [Getting started](https://developers.redhat.com/products/3scale/getting-started)
- **Try 3scale** and **3scale GitHub org** links on the product page

## Technical details

### Upstream-style components

- **APIcast** — NGINX-based API gateway; Lua policies per NGINX phase; custom policies; Lua DNS resolver  
- **Apisonator** — Listener and workers for auth and reporting; async backend; retry scheduler  
- **Porta** — Rails app for APIs and developer accounts (API + GUI); CMS with Liquid for portal customization  

Red Hat develops 3scale in the open with community participation across multiple GitHub repositories. See open issues and contribution guides in the **3scale** organization on GitHub.

### Related products

Managed API management on OpenShift: [Red Hat OpenShift API Management](https://developers.redhat.com/products/red-hat-openshift-api-management/overview).
