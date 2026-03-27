# Red Hat Connectivity Link 1.3

> Fetched from Red Hat Documentation (`html-single`). `html-single/what_s_new/index` returned 404; `whats_new` succeeded. What's New and Updating sections use article text extracted from HTML.

Discover | Red Hat Connectivity Link | 1.3 | Red Hat Documentation

 

 

 

 

 

 

 

 

# Discover

---

Red Hat Connectivity Link 1.3

## Multicloud application connectivity and API management

Red Hat OpenShift Documentation Team

Legal Notice

Abstract

This guide provides an overview of the main features, technologies, benefits, and user workflows that are available with Red Hat Connectivity Link.

---

## Chapter 1. Introduction to Connectivity LinkCopy link

Red Hat Connectivity Link is a control plane for configuring the Gateway API data plane in OpenShift Container Platform clusters. You can use it to apply authentication, rate limiting, and DNS policies to gateway resources.

### 1.1. About Red Hat Connectivity LinkCopy link

You can use Connectivity Link to connect, secure, observe, and protect all of your service endpoints. Use Connectivity Link in multicloud and hybrid cloud environments. Red Hat Connectivity Link is a single data plane used to apply policies to Gateway API resources in OpenShift Container Platform clusters. Gateway API is structured to meet the different needs of organizational teams.

First, configure and deploy ingress gateways with the role-oriented resources and components of the [Kubernetes Gateway API](https://gateway-api.sigs.k8s.io/). Then, use Connectivity Link to attach policies to Gateway API resources. Attaching policies means that you can skip the embedding of networking code into your applications, and use a code-as-infrastructure approach instead.

Example policy types:

Configure gateways with TLS policies for:

- Certificate management
- Authentication
- Authorization
- Rate limiting

Integrate DNS policies for:

- Multicluster load balancing
- Health checks
- Remediation

In addition, you can use the following included templates to see what is happening:

- Observability dashboards
- Observability metrics
- Tracing
- Alerts

#### 1.1.1. Red Hat Connectivity Link architectureCopy link

The following diagram shows a high-level overview of the Connectivity Link architecture with its main features and technologies:

Figure 1.1. Connectivity Link architecture

Connectivity Link supports [OpenShift Service Mesh 3.2](https://docs.redhat.com/en/documentation/red_hat_openshift_service_mesh/latest/html-single/about/index) as the Gateway API provider.

### 1.2. Connectivity Link benefitsCopy link

Connectivity Link provides the following main benefits:

Kubernetes-native Connectivity Link uses Kubernetes-native features for resource efficiency and optimal use. These features can run on any public or private OpenShift Container Platform cluster, offering multicloud and hybrid-cloud behavior by default. Hybrid cloud and multicloud

Connectivity Link includes the flexibility to deploy the same application to any OpenShift Container Platform cluster hosted on a public or private cloud. This removes the dependency on a specific cloud provider.

For example, if one cloud provider is having network issues, you can switch your deployment and traffic to another cloud provider to minimize the impact on your customers. This ability provides high availability and disaster recovery. It also means that your platforms and applications can remain resilient by ensuring that you are prepared for the unexpected and can establish uninterrupted service.

Use infrastructure as code You can define your infrastructure by using code to ensure that it is version controlled, tested, and easily replicated. OpenShift Container Platform auto-scaling features dynamically adjust resources based on workload demand. You can also use Connectivity Link to implement robust monitoring and logging solutions to gain full visibility into your OpenShift Container Platform clusters. Use the tools you have

You can use the technologies and tools that you already have in place with Connectivity Link. For example, the following are services and tools you can use:

- Cloud service providers: Amazon Web Services, Google Cloud, Microsoft Azure
- DNS providers: Amazon Route 53, Google Cloud DNS, Microsoft Azure DNS, CoreDNS
- Gateway API controllers: OpenShift Container Platform, OpenShift Container Platform Service Mesh
- Metrics and alerts: Prometheus, Thanos, Kiali
- Dashboards: Grafana, Red Hat Developer Hub
- GitOps and automation: Red Hat Ansible Automation Platform, OpenShift Container Platform GitOps, GitHub
- Additional integrations: Red Hat build of Keycloak, Red Hat Service Interconnect

Gateway API

Using Gateway API to set up ingress policies on each OpenShift Container Platform cluster means that ingress can be identical and implemented simultaneously.

In Kubernetes-based environments, [Gateway API](https://gateway-api.sigs.k8s.io/) is the standard for deploying ingress gateways and managing application networking. Gateway API provides standardized APIs for ingress traffic management and support for many protocols.

Observability Connectivity Link uses Kuadrant-maintained Gateway API state metrics, metrics exposed by Connectivity Link components, and standard metrics exposed by Envoy to build a set of example template alerts and dashboards. You can download and use these Kuadrant community templates to integrate with Grafana, Prometheus, and Alertmanager deployments, or use them as starting points that you can tailor for your specific needs.

### 1.3. Connectivity Link featuresCopy link

Connectivity Link includes the following features:

Multicloud application connectivity

- DNS provider integrations
- High availability and disaster recovery
- Global load balancing
- Application portability
- Application connectivity configuration
- Endpoint health and status checks
- Automatic TLS certificate generation
- Universal authentication

Kubernetes ingress policy management

- Global DNS policy
- TLS policy
- Auth policy
- Rate-limiting policy
- Token rate-limiting policy
- Traffic weighting and distribution
- User-role-based design
- Multicluster administration
- Observability dashboards and alerts
- OpenShift Container Platform web console dynamic plugin

Composable API management

- API security and governance
- Advanced API metrics collection
- API-level policies for authentication, authorization, and rate limiting
- Flexible integration with open source tools

### 1.4. Connectivity Link user workflowsCopy link

Similar to Gateway API, Connectivity Link is designed with specific user roles in mind, such as:

- Platform or infrastructure engineer
- Application developer
- Business user

Each persona has a different way to work with Connectivity Link, depending on how that person is interacting with your OpenShift Container Platform clusters.

#### 1.4.1. Platform engineer workflowCopy link

As a platform engineer or infrastructure provider, you can use Connectivity Link to set up ingress gateways on OpenShift Container Platform clusters in specific regions. You can configure all policies identically on all gateways for consistency.

For example, configure DNS policies to ensure that customers in Brazil are routed to the South American data center and so on. You can also configure TLS, authentication and authorization, and rate-limiting policies to ensure that gateway security, performance, and monitoring all conform to your standards.

Connect gateways and load balance As a platform engineer, you must start by creating at least one gateway. If gateways exist, you can move on to configuring policies for your gateways.

You can connect gateways by creating a DNS policy and configuring a global load balancing strategy. DNS records are reconciled with your cloud DNS provider automatically, whether in a single-cluster or multicluster environment. You can use the following features:

Single or multi-cluster ready

Secure gateways After you create your gateways, secure them. You can secure gateways by using a TLS policy that automatically generates certificate requests for the host names specified in your gateway. You can also set up application security defaults and overrides by using authentication and authorization policies and rate-limiting policies. You can use the following features:

Rate-limiting: Global rate-limiting, external IDPs, default and overrides

Observe gateways After your gateways are created and secured, you can configure your observability stack. Observe your connectivity and runtime metrics by using dashboards and alerts. For example, configure metrics for policy compliance and governance, resource consumption, error rates, request latency and throughput.

- Automatic DNS configuration
- Mutiple DNS providers
- DNS-based load balancing, such as round-robin, weighted, and geo-based
- TLS: Automatic certificate requests and ACME providers
- Auth policies

#### 1.4.2. Application developer workflowCopy link

As an application developer, you can use Connectivity Link to deploy applications and APIs on OpenShift Container Platform clusters and gateways that are set up by platform engineers. You can also monitor workloads and the status of OpenShift Container Platform resource metrics and tracing by using observability dashboards and alerts.

Example tasks

Configure routes

- Set up application routes and API definitions and publish them to the cluster.

Protect applications with Auth policies

- OAuth 2
- JWT authorization policies
- API keys
- Kubernetes tokens
- Role-based access (RBAC)
- Relationship-based access control (ReBAC)
- Open Policy Agent (OPA)

Observe application and API performance

- Monitor workloads and the status of OpenShift Container Platform resource metrics and tracing by using observability dashboards and alerts.
- Ensure that APIs meet performance and availability benchmarks achieved by other data centers by viewing API metrics, such as uptime, requests per second, latency, and errors per minute.

#### 1.4.3. Business user workflowCopy link

Business users, such as account managers and application owners, can use the data from Connectivity Link to work with customers.

You can use Grafana-based observability dashboards to monitor the status of applications and APIs in data centers in specific regions, and work with customers on specific performance metrics.

Specifically, you can view API metrics, such as uptime, requests per second, latency, and errors per minute, to ensure that APIs meet the performance and availability benchmarks that your customers require.

### 1.5. Using Connectivity Link technologies and patternsCopy link

You can use the following technologies and patterns with Connectivity Link:

Policy-based configuration

You can use the Connectivity Link policy attachment pattern to add behavior to a Kubernetes object by using configuration that cannot be described in the object`spec` field.

With policy attachments comes the concept of defaults and overrides. These defaults and overrides mean that you can configure different roles to operate with policy APIs at different levels of the object hierarchy. These policies are then merged with specific rules and strategies to form an effective policy that can be used across your organization.

WebAssembly plugin As a [WebAssembly (WASM)](https://webassembly.org/) plugin developed for the Envoy proxy, Connectivity Link is lightweight, hardware independent, non-intrusive, and secure. This means that clusters that are using OpenShift Container Platform OpenShift Service Mesh, Istio, or Envoy for ingress do not require major changes to their existing ingress objects and policies to begin using Connectivity Link. Multicluster configuration mirroring

You can configure and deploy your policies across different cloud service providers in a consistent way with Connectivity Link. Connectivity Link uses multicluster configuration mirroring across multicloud and hybrid cloud environments. You can deploy your routing, configuration, and policies wherever you need them through one interface.

This means that your development, test, and production environments can be consistent. Use Connectivity Link to supply unified experiences, global administration, and security compliance.

The following image shows Connectivity Link multicluster configuration mirroring:

Figure 1.2. Multicluster configuration mirroring across multicloud and hybrid cloud environments

API connectivity and API management

Connectivity Link provides scalable multicluster and multi-gateway connectivity management, along with API management features such as API observability, authentication, and rate limiting.

Figure 1.3. Connectivity Link API management and connectivity

### 1.6. Connectivity Link policy APIsCopy link

Understand how and when you can use the Connectivity Link core policies and observability features with your cloud applications and APIs.

Secure your applications with`TLSPolicy`

- `TLSPolicy` is a lightweight wrapper API to manage TLS for targeted gateways.
- Automatically provision TLS certificates based on the gateway listener hosts by using integration with`cert-manager` and ACME providers such as Let’s Encrypt.
- Configure secrets so that the gateway automatically retrieves them.

Protect your applications with`AuthPolicy`

- Use`AuthPolicy` objects to apply authentication and authorization across your selected listeners in a gateway or at the`HTTPRoute` or`HTTPRouteRule` level.
- Use the hierarchical and role-based concept of defaults and overrides to improve collaboration and ensure compliance.
- Use dedicated OIDC authentication providers such as Red Hat build of Keycloak.
- Apply fine-grained authorization requirements based on`request` and`metadata` attributes.

Protect your applications with`RateLimitPolicy`

- Apply rate-limiting rules across all listeners in a gateway or at the`HTTPRoute` or`HTTPRouteRule` level.
- Use the role-based and hierarchical concept of defaults and overrides to improve collaboration and ensure compliance.
- Configure limits conditionally based on metadata and request data.
- Share counters by using a backend store in multicluster environments.

For example, the following rate-limiting policy configures a specified limit of 5 requests per 10 seconds for every listener defined in the target gateway that does not have its own rate limiting policy defined:

Rate-limiting policy example

```yaml
apiVersion: kuadrant.io/v1
kind: RateLimitPolicy
metadata:
  name: gw-rlp
spec:
  targetRef: # Specifies Gateway API policy attachment
    group: gateway.networking.k8s.io
    kind: Gateway
    name: external
  defaults: # Means it can be overridden
    limits: # Limitador component configuration
      "global":
        rates:
        - limit: 5
          window: 10s
```

Connect your applications with`DNSPolicy`

- `DNSPolicy` is a standard API that is not based on custom annotations.
- Automatically populate DNS records based on listener hosts and addresses expressed by Gateway API resources.
- Configure multicluster connectivity and routing options, for example, geographic and weighted responses.
- Use common cloud DNS providers: Amazon Route 53, Microsoft Azure DNS, Google Cloud DNS, or CoreDNS.
- Configure health checks to enable DNS failover.

Observe your ingress traffic You can use the Connectivity Link observability features to observe and monitor your gateways, applications, and APIs on OpenShift Container Platform. You can download and use community-based templates to integrate with Grafana, Prometheus, and Alertmanager deployments, or use these templates as starting points to modify for your specific needs.

### 1.7. Supported configurations with Connectivity LinkCopy link

Connectivity Link must run on a supported combination of OpenShift Container Platform and use the cert-manager Operator for Red Hat OpenShift. To configure observability, use Red Hat OpenShift Service Mesh. Red Hat provides both production and development support for supported configurations and tested integrations according to your subscription agreement.

Important

If you use a configuration that includes OpenShift Container Platform 4.18 or older, you must also use Red Hat OpenShift Service Mesh as the Gateway API provider.

#### 1.7.1. Supported OpenShift Container Platform version configurationsCopy link

Expand

Version 1.3

4.21, 4.20, 4.19

4.21, 4.20, 4.19

4.21, 4.20, 4.19

4.19

Version 1.2

4.20, 4.19, 4.18

4.20, 4.19, 4.18

4.20, 4.19, 4.18

4.17

Version 1.1

4.19, 4.18, 4.17

4.19, 4.18, 4.17

4.19, 4.18, 4.17

4.17

| | Red Hat Connectivity Link | Red Hat OpenShift Container Platform | Red Hat OpenShift Dedicated | Red Hat OpenShift Service on AWS | Microsoft Azure Red Hat OpenShift |
| --- | --- | --- | --- | --- | --- |

Show more

For Microsoft Azure, see the [Support lifecycle for Azure Red Hat OpenShift 4](https://learn.microsoft.com/en-us/azure/openshift/support-lifecycle).

#### 1.7.2. Supported OperatorsCopy link

Expand

Version 1.3

3.2

1.18

Version 1.2

3.1

1.17

Version 1.1

3.0

1.15

| | Red Hat Connectivity Link | Red Hat OpenShift Service Mesh | cert-manager Operator for Red Hat OpenShift |
| --- | --- | --- | --- |

Show more

#### 1.7.3. Supported cloud providersCopy link

All versions of Connectivity Link support the following platforms as backing cloud providers for OpenShift Container Platform:

- Amazon Web Services
- Google Cloud Platform
- Microsoft Azure

For more information, see the documentation for your chosen cloud provider.

#### 1.7.4. Supported cloud DNS providersCopy link

For DNS policies, all versions of Connectivity Link support the following cloud DNS providers:

- Amazon Route 53
- Google Cloud Platform DNS
- Microsoft Azure DNS

For more information, see the documentation for your chosen cloud DNS provider.

#### 1.7.5. Supported on-premise DNS providersCopy link

You can use CoreDNS can to configure an on-cluster DNS zone. For more information, see [About using on-premise DNS with CoreDNS](https://docs.redhat.com/en/documentation/red_hat_connectivity_link/1.3/html-single/configuring_and_deploying_gateway_policies/#con-about-using-onprem-dns-coredns_rhcl-using-on-prem-dns-coredns).

#### 1.7.6. Supported data stores for rate limitingCopy link

For rate limiting policies, Connectivity Link supports the following Redis-based data stores for rate limit counters in multicluster environments:

Expand

Version 1.3

latest

latest

latest

Version 1.2

latest

latest

latest

Version 1.1

latest

latest

latest

| | Red Hat Connectivity Link | Redis Enterprise or Cloud | Amazon Elasticache | Dragonfly Community or Cloud |
| --- | --- | --- | --- | --- |

Show more

For more information, see the documentation for your chosen Redis-based datastore.

#### 1.7.7. Supported identity access managementCopy link

For authentication policies, Connectivity Link supports API keys and the following products:

Expand

Version 1.3

Version 26.4

Version 1.2

Version 26.4

Version 1.1

Version 26.2

| | Red Hat Connectivity Link Version | Red Hat build of Keycloak |
| --- | --- | --- |

Show more

For more information, see [Supported Configurations for Red Hat build of Keycloak](https://access.redhat.com/articles/7033107).

## Legal NoticeCopy link

Copyright © Red Hat.

Except as otherwise noted below, the text of and illustrations in this documentation are licensed by Red Hat under the Creative Commons Attribution–Share Alike 3.0 Unported license . If you distribute this document or an adaptation of it, you must provide the URL for the original version.

Red Hat, as the licensor of this document, waives the right to enforce, and agrees not to assert, Section 4d of CC-BY-SA to the fullest extent permitted by applicable law.

Red Hat, the Red Hat logo, JBoss, Hibernate, and RHCE are trademarks or registered trademarks of Red Hat, LLC. or its subsidiaries in the United States and other countries.

Linux® is the registered trademark of Linus Torvalds in the United States and other countries.

XFS is a trademark or registered trademark of Hewlett Packard Enterprise Development LP or its subsidiaries in the United States and other countries.

The OpenStack® Word Mark and OpenStack logo are trademarks or registered trademarks of the Linux Foundation, used under license.

All other trademarks are the property of their respective owners.

 

 

[Github](https://github.com/redhat-documentation) [Youtube](https://www.youtube.com/@redhat) [Twitter](https://twitter.com/RedHat)

### Learn

- [Developer resources](https://developers.redhat.com/learn)
- [Cloud learning hub](https://docs.redhat.com/learn/learning-paths)
- [Interactive labs](https://www.redhat.com/en/interactive-labs)
- [Training and certification](https://www.redhat.com/services/training-and-certification)
- [Customer support](https://access.redhat.com/support)
- [See all documentation](https://docs.redhat.com/en/products)

### Try, buy, & sell

- [Product trial center](https://redhat.com/en/products/trials)
- [Red Hat Ecosystem Catalog](https://catalog.redhat.com/)
- [Red Hat Store](https://www.redhat.com/en/store)
- [Buy online (Japan)](https://www.redhat.com/about/japan-buy)

### Communities

- [Customer Portal Community](https://access.redhat.com/community)
- [Events](https://www.redhat.com/events)
- [How we contribute](https://www.redhat.com/about/our-community-contributions)

### About Red Hat Documentation

We help Red Hat users innovate and achieve their goals with our products and services with content they can trust. [Explore our recent updates](https://www.redhat.com/en/blog/whats-new-docsredhatcom).

### Making open source more inclusive

Red Hat is committed to replacing problematic language in our code, documentation, and web properties. For more details, see the [Red Hat Blog](https://www.redhat.com/en/blog/making-open-source-more-inclusive-eradicating-problematic-language).

### About Red Hat

We deliver hardened solutions that make it easier for enterprises to work across platforms and environments, from the core datacenter to the network edge.

### Theme

 

- [About Red Hat](https://redhat.com/en/about/company)
- [Jobs](https://redhat.com/en/jobs)
- [Events](https://redhat.com/en/events)
- [Locations](https://redhat.com/en/about/office-locations)
- [Contact Red Hat](https://redhat.com/en/contact)
- [Red Hat Blog](https://redhat.com/en/blog)
- [Inclusion at Red Hat](https://redhat.com/en/about/our-culture/diversity-equity-inclusion)
- [Cool Stuff Store](https://coolstuff.redhat.com/)
- [Red Hat Summit](https://www.redhat.com/en/summit)

© 2026 Red Hat

- [Privacy statement](https://redhat.com/en/about/privacy-policy)
- [Terms of use](https://redhat.com/en/about/terms-use)
- [All policies and guidelines](https://redhat.com/en/about/all-policies-guidelines)
- [Digital accessibility](https://redhat.com/en/about/digital-accessibility)

Back to top

 



---

## What's New

What’s New
Red Hat Connectivity Link 1.3
What's new in Red Hat Connectivity Link

Red Hat OpenShift Documentation Team

Legal Notice
Abstract

				This guide provides the latest information on what's new in this release of Red Hat Connectivity Link.

Chapter 1. Connectivity Link 1.3 release notes


			Welcome to the Red Hat Connectivity Link release notes, where you can learn about what is new and what is fixed.

1.1. Red Hat Connectivity Link 1.3 release notes


				Red Hat Connectivity Link is a modular and flexible solution for application connectivity, policy management, and API management in multicloud and hybrid cloud environments. You can use Connectivity Link to secure, protect, connect, and observe your APIs, applications, and infrastructure.

				Connectivity Link is based on the Kuadrant community project. Connectivity Link provides a control plane for configuring and deploying ingress gateways and policies based on the Kubernetes Gateway API standard. Connectivity Link supports OpenShift Service Mesh 3.2 as the Gateway API provider, which is based on the Istio community project.

				See the Red Hat Connectivity Link Life Cycle Policy for details about version support and OpenShift Container Platform compatibility.

1.1.1. New features and enhancements


					You can use the new features and enhancements that are available with Red Hat Connectivity Link 1.3.

CoreDNS integration for on-premise DNS is Generally Available
								Connectivity Link 1.3 provides integration with CoreDNS for on-premise DNS as a Generally Available feature. For more information, see About using on-premise DNS with CoreDNS.
							Updated Observability documentation is now available
								Connectivity Link 1.3 includes enhanced Observability documentation, including configuring access logs, tracing, and request correlation. For more information, see Red Hat Connectivity Link observability.

1.1.2. Known issues


					Connectivity Link 1.3 has two known issues.

-
							When the disk storage option is enabled in the Limitador custom resource (CR), both the initial limitador deployment and Operator update get stuck on a Multi-Attach error because of the persistent volume claim (PVC) volume. As a workaround, you can change the limitador deployment strategy to "Recreate" and the reconcilation process works as expected. (CONNLINK-855)

-
							When either the Redis or RedisCached storage option is set in a Limitador CR and the limitador pod gets restarted for any reason, the first request to the gateway is never rate-limited. All http requests after this are rate-limited. (CONNLINK-856)

1.1.3. Async releases


					Security, bug fix, and enhancement updates for Red Hat Connectivity Link 1.3 are released asynchronously through the Red Hat Network. All Red Hat Connectivity Link 1.3 updates are available on the Red Hat Customer Portal. For more information about asynchronous updates, read the Red Hat Connectivity Link Life Cycle Policy.

					Red Hat Customer Portal users can enable update notifications in the account settings for Red Hat Subscription Management (RHSM). When notifications are enabled, you are notified through email whenever new updates relevant to your registered systems are released.

					This section is updated over time to provide notes on enhancements and bug fixes for future asynchronous releases of Red Hat Connectivity Link 1.3. Versioned asynchronous releases, for example with the Red Hat Connectivity Link 1.3.z, are detailed in the following subsections.

Red Hat Connectivity Link 1.3.1 bug fix and security update

								Issued: 18 March 2026

								Red Hat Connectivity Link release 1.3.1 is now available. Red Hat Connectivity Link uses the stable update channel to track and receives updates for the Red Hat Connectivity Link Operator. You can manage how your updates are applied through your Operator Lifecycle Manager (OLM) subscription resource. For more information, see Updating Red Hat Connectivity Link

-
										Previously, the wasm-shim networking protcol incorrectly appended values to existing request headers instead of replacing them during the external authorization flow. This caused inconsistent behavior, leading to comma-separated header values that could disrupt upstream processing. With this release, the logic is updated to ensure that the headers provided in the CheckResponse object now correctly replace existing values. This fix restores predictable header management. (CONNLINK-867)

Legal Notice


		Copyright © Red Hat.

		Except as otherwise noted below, the text of and illustrations in this documentation are licensed by Red Hat under the Creative Commons Attribution–Share Alike 3.0 Unported license . If you distribute this document or an adaptation of it, you must provide the URL for the original version.

		Red Hat, as the licensor of this document, waives the right to enforce, and agrees not to assert, Section 4d of CC-BY-SA to the fullest extent permitted by applicable law.

		Red Hat, the Red Hat logo, JBoss, Hibernate, and RHCE are trademarks or registered trademarks of Red Hat, LLC. or its subsidiaries in the United States and other countries.

		Linux® is the registered trademark of Linus Torvalds in the United States and other countries.

		XFS is a trademark or registered trademark of Hewlett Packard Enterprise Development LP or its subsidiaries in the United States and other countries.

		The OpenStack® Word Mark and OpenStack logo are trademarks or registered trademarks of the Linux Foundation, used under license.

		All other trademarks are the property of their respective owners.

---

Installing on OpenShift Container Platform | Red Hat Connectivity Link | 1.3 | Red Hat Documentation

 

 

 

 

 

 

 

 

# Installing on OpenShift Container Platform

---

Red Hat Connectivity Link 1.3

## Single cluster and multicluster deployments

Red Hat OpenShift Documentation Team

Legal Notice

Abstract

This guide describes how to install Connectivity Link components on OpenShift in single-cluster and multicluster environments.

---

## Chapter 1. Installing on OpenShift Container PlatformCopy link

As a platform engineer, you can install Connectivity Link on OpenShift Container Platform clusters.

### 1.1. Getting ready to install Connectivity LinkCopy link

As you plan your Connectivity Link install, ensure that you have access to the required platforms in your environment with the correct user permissions. You can also decide whether to use optional supported components, such as rate limiting and Observability.

#### 1.1.1. Required platforms and componentsCopy link

The following platforms and components are required to install Connectivity Link successfully:

Red Hat account You have a Red Hat account with subscriptions for Connectivity Link and OpenShift Container Platform. OpenShift Container Platform

OpenShift Container Platform 4.19 or later is installed, or you have access to a supported OpenShift Container Platform cloud service. See [OpenShift Container Platform installation documentation](https://docs.redhat.com/en/documentation/openshift_container_platform/4.21/html/installation_overview/index).

Important

When using the Gateway API custom resource definitions (CRDs) provided in OpenShift Container Platform 4.19 or newer, you must create a`GatewayClass` named`openshift-default` and specify a`controllerName` of`openshift.io/gateway-controller/v1`. For more details, see the [Getting started with Gateway API for the Ingress Operator (OpenShift Container Platform documentation)](https://docs.redhat.com/en/documentation/openshift_container_platform/4.21/html/ingress_and_load_balancing/configuring-ingress-cluster-traffic#nw-ingress-gateway-api-enable_ingress-gateway-api).

OpenShift Service Mesh A separate OpenShift Service Mesh installation is not required with Connectivity Link 1.3. If you use OpenShift Service Mesh, ensure that you are using 3.2 to stay in a supported configuration. cert-manager Operator for Red Hat OpenShift

You installed cert-manager Operator for Red Hat OpenShift 1.18 to manage the TLS certificates for your gateways. See the [cert-manager Operator for Red Hat OpenShift documentation](https://docs.redhat.com/en/documentation/openshift_container_platform/4.21/html/security_and_compliance/cert-manager-operator-for-red-hat-openshift).

Important

Before using a Connectivity Link`TLSPolicy` custom resource (CR), you must set up a certificate issuer for your cloud provider platform. See the [OpenShift documentation on configuring an ACME issuer](https://docs.redhat.com/en/documentation/openshift_container_platform/4.21/html-single/security_and_compliance/index#cert-manager-operator-issuer-acme).

#### 1.1.2. Optional componentsCopy link

The following components are optional with Connectivity Link. You can decide what you want to use and plan for those configurations before beginning your installation.

DNSPolicy

For a`DNSPolicy` CR, you must have an account for one of the supported cloud DNS providers and have set up a hosted zone for Connectivity Link. For more details, see your cloud DNS provider documentation:

- Legal Notice
- [Amazon Route 53 documentation](https://docs.aws.amazon.com/route53/?icmpid=docs_homepage_networking)
- [Google Cloud DNS documentation](https://cloud.google.com/dns/docs)
- [Microsoft Azure DNS documentation](https://learn.microsoft.com/en-us/azure/dns/)

RateLimitPolicy

For`RateLimitPolicy` CRs, you must have a shared accessible Redis-based datastore for rate-limit counters in a multicluster environment. For details on how to install and configure a secure and highly available datastore, see the documentation for your Redis-compatible datastore:

- [Redis documentation](https://redis.io/docs/latest/)
- [AWS ElastiCache (Redis OSS) User Guide](https://docs.aws.amazon.com/AmazonElastiCache/latest/red-ug/WhatIs.html)
- [Dragonfly documentation](https://www.dragonflydb.io/docs)
- [Valkey documentation](https://valkey.io/docs/)

AuthPolicy For an`AuthPolicy` CR, you can install Red Hat build of Keycloak if required in your environment. For more details, see the [Red Hat build of Keycloak documentation](https://docs.redhat.com/en/documentation/red_hat_build_of_keycloak). Observability For Observability, you must configure OpenShift Container Platform user workload monitoring to remote-write to a central storage system.

#### 1.1.3. Supported configurations with Connectivity LinkCopy link

Connectivity Link must run on a supported combination of OpenShift Container Platform and use the cert-manager Operator for Red Hat OpenShift. To configure observability, use Red Hat OpenShift Service Mesh. Red Hat provides both production and development support for supported configurations and tested integrations according to your subscription agreement.

Important

If you use a configuration that includes OpenShift Container Platform 4.18 or older, you must also use Red Hat OpenShift Service Mesh as the Gateway API provider.

##### 1.1.3.1. Supported OpenShift Container Platform version configurationsCopy link

Expand

Version 1.3

4.21, 4.20, 4.19

4.21, 4.20, 4.19

4.21, 4.20, 4.19

4.19

Version 1.2

4.20, 4.19, 4.18

4.20, 4.19, 4.18

4.20, 4.19, 4.18

4.17

Version 1.1

4.19, 4.18, 4.17

4.19, 4.18, 4.17

4.19, 4.18, 4.17

4.17

| | Red Hat Connectivity Link | Red Hat OpenShift Container Platform | Red Hat OpenShift Dedicated | Red Hat OpenShift Service on AWS | Microsoft Azure Red Hat OpenShift |
| --- | --- | --- | --- | --- | --- |

Show more

For Microsoft Azure, see the [Support lifecycle for Azure Red Hat OpenShift 4](https://learn.microsoft.com/en-us/azure/openshift/support-lifecycle).

##### 1.1.3.2. Supported OperatorsCopy link

Expand

Version 1.3

3.2

1.18

Version 1.2

3.1

1.17

Version 1.1

3.0

1.15

| | Red Hat Connectivity Link | Red Hat OpenShift Service Mesh | cert-manager Operator for Red Hat OpenShift |
| --- | --- | --- | --- |

Show more

##### 1.1.3.3. Supported cloud providersCopy link

All versions of Connectivity Link support the following platforms as backing cloud providers for OpenShift Container Platform:

- Amazon Web Services
- Google Cloud Platform
- Microsoft Azure

For more information, see the documentation for your chosen cloud provider.

##### 1.1.3.4. Supported cloud DNS providersCopy link

For DNS policies, all versions of Connectivity Link support the following cloud DNS providers:

- Amazon Route 53
- Google Cloud Platform DNS
- Microsoft Azure DNS

For more information, see the documentation for your chosen cloud DNS provider.

##### 1.1.3.5. Supported on-premise DNS providersCopy link

You can use CoreDNS can to configure an on-cluster DNS zone. For more information, see [About using on-premise DNS with CoreDNS](https://docs.redhat.com/en/documentation/red_hat_connectivity_link/1.3/html-single/configuring_and_deploying_gateway_policies/#con-about-using-onprem-dns-coredns_rhcl-using-on-prem-dns-coredns).

##### 1.1.3.6. Supported data stores for rate limitingCopy link

For rate limiting policies, Connectivity Link supports the following Redis-based data stores for rate limit counters in multicluster environments:

Expand

Version 1.3

latest

latest

latest

Version 1.2

latest

latest

latest

Version 1.1

latest

latest

latest

| | Red Hat Connectivity Link | Redis Enterprise or Cloud | Amazon Elasticache | Dragonfly Community or Cloud |
| --- | --- | --- | --- | --- |

Show more

For more information, see the documentation for your chosen Redis-based datastore.

##### 1.1.3.7. Supported identity access managementCopy link

For authentication policies, Connectivity Link supports API keys and the following products:

Expand

Version 1.3

Version 26.4

Version 1.2

Version 26.4

Version 1.1

Version 26.2

| | Red Hat Connectivity Link Version | Red Hat build of Keycloak |
| --- | --- | --- |

Show more

For more information, see [Supported Configurations for Red Hat build of Keycloak](https://access.redhat.com/articles/7033107).

### 1.2. Installing Connectivity Link with the OpenShift Container Platform web consoleCopy link

You can use the OpenShift Container Platform web console to install the Red Hat Connectivity Link Operator. You must perform these steps on each OpenShift Container Platform cluster that you want to use Connectivity Link on.

The OpenShift Container Platform Cluster Ingress Operator is the default gateway controller for Connectivity Link.

An`OperatorGroup` custom resource (CR) is created automatically when you use the web console. For more information, see [Operator Groups](https://docs.redhat.com/en/documentation/openshift_container_platform/4.21/html-single/operators/index#olm-understanding-operatorgroups).

Warning

Connectivity Link requires`kuadrant.io/*` labels to search and filter resources on the cluster. Do not remove labels with this prefix. Removal might cause unexpected behavior and degradation of Connectivity Link.

Prerequisites

- You are using a supported configuration of OpenShift Container Platform and required components.
- You are logged into OpenShift Container Platform as a cluster administrator.
- You are logged into the OpenShift Container Platform web console with`cluster-admin` privileges.

Procedure

Select your subscription settings as follows:

- Update Channel: stable
- Version: 1.3.0
- Installation mode: All namespaces on the cluster (default).
- Installed namespace: Select the namespace where you want to install the Operator, for example, kuadrant-system. If the namespace does not already exist, click this field and select Create Project to create the namespace.
- Approval Strategy: Select Automatic or Manual.

Verification

After you have installed the Operator, click Ecosystem > Installed Operators to verify that the Red Hat Connectivity Link Operator and the following component Operators are installed in your namespace:

- Click Create and wait for the deployment to be displayed in the list.
- Authorino Operator: Enables authentication and authorization for gateways and applications in a Gateway API network.
- DNS Operator: Configures how north-south traffic from outside the network is balanced and reaches gateways.
- Limitador Operator: Enables rate limiting for gateways and applications in a Gateway API network.

Next step

- Update your Subscription CR to use the OpenShift Container Platform Cluster Ingress Operator.

#### 1.2.1. Using Istio as a gateway controllerCopy link

If you used the web console to install Connectivity Link and you want to use OpenShift Service Mesh, you must patch the`Subscription` custom resource (CR) to use Istio as your`Gateway` object controller.

Prerequisites

- You are using a supported configuration of OpenShift Container Platform and required components.
- You installed Connectivity Link using the OpenShift Container Platform web console.
- You are logged into OpenShift Container Platform as a cluster administrator.

Procedure

Patch your`Subscription` CR by running the following command:

```shell-session
$ oc patch subscription rhcl-operator -n <kuadrant_system> --type=json -p='[{"op":"add","path":"/spec/config","value":{"env":[{"name":"ISTIO_GATEWAY_CONTROLLER_NAMES","value":"istio.io/gateway-controller"}]}}]'
```

Replace with the namespace you used when installing Connectivity Link.

Restart your deployment to apply the updated CR by running the following command:

```shell-session
$ oc rollout restart deployment/kuadrant-operator-controller-manager -n <kuadrant_system>
```

Replace with the namespace you used when installing Connectivity Link.

Verification

Check the status of the`Kuadrant` CR generation by running the following command:

```shell-session
$ oc wait kuadrant/kuadrant --for="condition=Ready=true" -n <kuadrant_system> --timeout=300s
```

Replace with the namespace you used when installing Connectivity Link.

Example output

```shell-session
kuadrant.kuadrant.io/kuadrant Ready
```

### 1.3. Installing Connectivity Link on OpenShift Container Platform from the command lineCopy link

You can install Connectivity Link with OpenShift CLI (`oc`) using the OpenShift Container Platform Cluster Ingress Operator as the default`Gateway` object controller. You must complete these steps on each OpenShift Container Platform cluster that you want to use Connectivity Link on.

Warning

Connectivity Link uses labels formatted as`kuadrant.io/*` to search and filter resources on the cluster. Removing of any labels with the prefix might cause unexpected behavior and degradation of Connectivity Link.

Prerequisites

- You are logged into OpenShift Container Platform as a cluster administrator.
- You are using a supported configuration of OpenShift Container Platform and required components.
- You installed the OpenShift CLI (`oc`).

Procedure

Create the namespace where you want to install Connectivity Link by running the following command:

```shell-session
$ oc create ns <kuadrant_system>
```

You can replace the default with the namespace you want to use.

Install Connectivity Link by creating and applying`Subscription` and`OperatorGroup` custom resources (CRs) by running the following command:

```shell-session
$ oc apply -f - <<EOF
apiVersion: operators.coreos.com/v1alpha1
kind: Subscription
metadata:
  name: rhcl-operator
  namespace: <kuadrant_system>
spec:
  channel: stable
  installPlanApproval: Automatic
  name: rhcl-operator
  source: redhat-operators
  sourceNamespace: openshift-marketplace
---
kind: OperatorGroup
apiVersion: operators.coreos.com/v1
metadata:
  name: kuadrant
  namespace: <kuadrant_system>
spec:
  upgradeStrategy: Default
EOF
```

Replace with the namespace you used.

Confirm that the Connectivity Link installation has finished by running one of the following commands:

```shell-session
$ oc wait --for=jsonpath={.status.installPlanRef.name} subscription rhcl-operator --timeout=10s
ip=$(oc get subscription rhcl-operator -o=jsonpath={.status.installPlanRef.name})
```

```shell-session
$ oc wait --for=condition=Installed installplan ${ip} --timeout=60s
```

Expect the status of`installplan.operators.coreos.com/install- ` when Connectivity Link is ready. The name of the install plan has a random suffix, for example,`4rql7`.

Create your Connectivity Link custom resource (CR) by running the following command:

```shell-session
$ oc apply -f - <<EOF
apiVersion: kuadrant.io/v1beta1
kind: Kuadrant
metadata:
  name: kuadrant
  namespace: <kuadrant_system>
EOF
```

Replace with the namespace you used.

Verification

Check the status of the Connectivity Link CR generation by running the following command:

```shell-session
$ oc wait kuadrant/kuadrant --for="condition=Ready=true" -n <kuadrant_system> --timeout=300s
```

Replace with the namespace you used.

Example output

```shell-session
kuadrant.kuadrant.io/kuadrant Ready
```

### 1.4. Installing Connectivity Link on OpenShift Container Platform from the CLI with Istio as Gateway controllerCopy link

If you are using OpenShift Service Mesh, you can install Connectivity Link with OpenShift CLI (`oc`) using Istio as your`Gateway` object controller. You must complete these steps on each OpenShift Container Platform cluster that you want to use Connectivity Link on.

Warning

Connectivity Link uses labels formatted as`kuadrant.io/*` to search and filter resources on the cluster. Removing of any labels with the prefix might cause unexpected behavior and degradation of Connectivity Link.

Prerequisites

- You are logged into OpenShift Container Platform as a cluster administrator.
- You are using a supported configuration of OpenShift Container Platform and required components.
- You installed the OpenShift CLI (`oc`).
- You installed and configured OpenShift Service Mesh.

Procedure

Create the namespace where you want to install Connectivity Link by running the following command:

```shell-session
$ oc create ns <kuadrant-system>
```

You can replace the default with the namespace you want to use.

Install Connectivity Link by running the following command:

```shell-session
$ oc apply -f - <<EOF
apiVersion: operators.coreos.com/v1alpha1
kind: Subscription
metadata:
  name: rhcl-operator
  namespace: kuadrant-system
spec:
  channel: stable
  installPlanApproval: Automatic
  name: rhcl-operator
  source: redhat-operators
  sourceNamespace: openshift-marketplace
  config:
    env:
    - name: ISTIO_GATEWAY_CONTROLLER_NAMES
      value: istio.io/gateway-controller
---
kind: OperatorGroup
apiVersion: operators.coreos.com/v1
metadata:
  name: kuadrant
  namespace: kuadrant-system
spec:
  upgradeStrategy: Default
EOF
```

Replace with the namespace you used.

Confirm that the Connectivity Link installation has finished by running one of the following commands:

```shell-session
$ oc wait --for=jsonpath={.status.installPlanRef.name} subscription rhcl-operator --timeout=10s
ip=$(oc get subscription rhcl-operator -o=jsonpath={.status.installPlanRef.name})
```

```shell-session
$ oc wait --for=condition=Installed installplan ${ip} --timeout=60s
```

Expect the status of`installplan.operators.coreos.com/install- ` when Connectivity Link is ready. The name of the install plan has a random suffix, for example,`4rql7`.

Create your Connectivity Link custom resource (CR) by running the following command:

```shell-session
$ oc apply -f - <<EOF
apiVersion: kuadrant.io/v1beta1
kind: Kuadrant
metadata:
  name: kuadrant
  namespace: <kuadrant-system>
EOF
```

Replace with the namespace you used.

Verification

Check the status of the Connectivity Link CR generation by running the following command:

```shell-session
$ oc wait kuadrant/kuadrant --for="condition=Ready=true" -n <kuadrant-system> --timeout=300s
```

Replace with the namespace you used.

Example output

```shell-session
kuadrant.kuadrant.io/kuadrant Ready
```

### 1.5. Configuring DNS provider credentials for AWSCopy link

If you want to configure AWS DNS policies in Connectivity Link, you must configure the DNS credentials. You must perform the steps on each OpenShift Container Platform cluster that you want to use Connectivity Link on.

You must configure a DNS hosted zone. The credentials for your DNS provider must have permissions to update DNS records within this zone.

Prerequisites

You have access to the namespace of your gateway, for example,`api-gateway`.

Note

If you already know your environment variable values, you can create the required YAML files as required for your use case.

- You installed Connectivity Link on an OpenShift Container Platform cluster.

Procedure

Optional: Set up your environment variables as follows:

Assign`AWS_ACCESS_KEY_ID`, which is the key ID from AWS with Route 53 access:

```shell-session
$ export AWS_ACCESS_KEY_ID=xxxxxxx
```

Assign AWS_SECRET_ACCESS_KEY`, which is the key from AWS with Route 53 access.

```shell-session
$ export AWS_SECRET_ACCESS_KEY=xxxxxxx
```

Assign`AWS_REGION`, which is your AWS region, for example,`us-east-2` or`eu-west-1`.

```shell-session
$ export AWS_REGION=your-aws-region
```

Create a`Secret` resource for your credentials as follows:

```shell-session
$ oc create secret generic aws-credentials \
  --namespace=api-gateway \
  --type=kuadrant.io/aws \
  --from-literal=AWS_ACCESS_KEY_ID=$AWS_ACCESS_KEY_ID \
  --from-literal=AWS_SECRET_ACCESS_KEY=$AWS_SECRET_ACCESS_KEY \
  --from-literal=AWS_REGION=$AWS_REGION
```

Important

You must configure the secret in the same namespace as your gateway.

### 1.6. Configuring Google DNS provider credentialsCopy link

If you want to configure DNS policies in Connectivity Link using Google Cloud, you must configure the DNS credentials. You must perform the steps on each OpenShift Container Platform cluster that you want to use Connectivity Link on.

You must configure a DNS hosted zone. The credentials for your DNS provider must have permissions to update DNS records within this zone.

Prerequisites

You have access to the namespace of your gateway, for example,`api-gateway`.

Note

If you already know your environment variable values, you can create the required YAML files as required for your use case.

- You installed Connectivity Link on an OpenShift Container Platform cluster.

Procedure

Optional: Specify your`GOOGLE` environment variable by running the following commands:

```shell-session
$ export GOOGLE=xxxxxxx
```

where:

`GOOGLE`: The`GOOGLE` variable specifies the JSON credentials generated by the`gcloud` CLI or by the service account. For example,`$HOME/.config/gcloud/application_default_credentials.json`, which has the following credentials:

```plaintext
{"client_id": "***","client_secret": "***","refresh_token": "***","type": "authorized_user"}
```

Optional: Specify your`PROJECT_ID` environment variable by running the following commands:

```shell-session
$ export PROJECT_ID=xxxxxxx
```

`PROJECT_ID`: Google project ID.

Create a`Secret` resource for your credentials by running the following command:

```shell-session
$ oc create secret generic test-gcp-credentials \
  --namespace=api-gateway \
  --type=kuadrant.io/gcp \
  --from-literal=PROJECT_ID=$PROJECT_ID \
  --from-file=GOOGLE=$GOOGLE
```

Important

You must configure the secret in the same namespace as your gateway.

### 1.7. Configuring Azure DNS provider credentialsCopy link

If you want to configure Microsoft Azure DNS policies in Connectivity Link, you must configure the DNS credentials. You must perform the steps on each OpenShift Container Platform cluster that you want to use Connectivity Link on.

You must configure a DNS hosted zone. The credentials for your DNS provider must have permissions to update DNS records within this zone.

Prerequisites

You have access to the namespace of your gateway, for example,`api-gateway`.

Note

If you already know your environment variable values, you can create the required YAML files as required for your use case.

- You installed Connectivity Link on an OpenShift Container Platform cluster.

Procedure

Create a new Azure service principal for managing DNS by setting the following environment variables:

```shell-session
$ DNS_NEW_SP_NAME=kuadrantDnsPrincipal \
  DNS_SP=$(az ad sp create-for-rbac --name $DNS_NEW_SP_NAME) \
  DNS_SP_APP_ID=$(echo $DNS_SP | jq -r '.appId') \
  DNS_SP_PASSWORD=$(echo $DNS_SP | jq -r '.password')
```

For more details on service principals, see the [Microsoft Azure documentation](https://learn.microsoft.com/en-us/entra/identity-platform/app-objects-and-service-principals?tabs=browser#service-principal-object).

Set the resource group environment variable by running the following command:

```shell-session
$ DNS_RESOURCE_GROUP="ExampleDNSResourceGroup"
```

Replace "ExampleDNSResourceGroup" with the DNS resource group that you want to use.

To grant read and contributor access to the zones that you want managed for the service principal you are using, perform the following steps:

Fetch the DNS ID used to grant access to the service principal as follows:

```shell-session
$ DNS_ID=$(az network dns zone show --name example.com \
 --resource-group $DNS_RESOURCE_GROUP --query "id" --output tsv)
```

Get your resource group ID by running the following command:

```shell-session
$ RESOURCE_GROUP_ID=$(az group show --resource-group $DNS_RESOURCE_GROUP | jq ".id" -r)
```

Give reader access to the resource group as follows:

```shell-session
$ az role assignment create --role "Reader" --assignee $DNS_SP_APP_ID --scope $DNS_ID
```

Give contributor access to the DNS zone as follows:

```shell-session
$ az role assignment create --role "Contributor" --assignee $DNS_SP_APP_ID --scope $DNS_ID
```

Because you are setting up advanced traffic rules for geographic and weighted responses, you must also grant traffic manager and DNS zone access:

Create the role assignment for the traffic manager contributor by running the following command:

```shell-session
$ az role assignment create --role "Traffic Manager Contributor" --assignee $DNS_SP_APP_ID --scope $RESOURCE_GROUP_ID
```

Create the role assignment for the DNA zone contributor by running the following command:

```shell-session
$ az role assignment create --role "DNS Zone Contributor" --assignee $DNS_SP_APP_ID --scope $RESOURCE_GROUP_ID
```

Configure the DNS zone access by running the following command:

```shell-session
$ cat <<-EOF > /local/path/to/azure.json
{
  "tenantId": "$(az account show --query tenantId -o tsv)",
  "subscriptionId": "$(az account show --query id -o tsv)",
  "resourceGroup": "$DNS_RESOURCE_GROUP",
  "aadClientId": "$DNS_SP_APP_ID",
  "aadClientSecret": "$DNS_SP_PASSWORD"
}
EOF
```

Create a`Secret` resource for your credentials by running the following command:

```shell-session
$ oc create secret generic test-azure-credentials \
  --namespace=api-gateway \
  --type=kuadrant.io/azure \
  --from-file=azure.json=/local/path/to/azure.json
```

Important

You must configure the secret in the same namespace as your gateway.

### 1.8. Configuring Redis storage for rate limitingCopy link

To configure persistence for rate limit counters in a multicluster environment, you must configure the connection details for your shared Redis-based datastore. This datastore is used to persist shared rate limit counters for the Limitador component of Connectivity Link.

Important

You must configure connection details for your shared Redis-based datastore on each OpenShift Container Platform cluster that you want to use Connectivity Link for rate limiting.

Prerequisites

- You installed Connectivity Link on one or more clusters.
- You have a shared Redis-based datastore.
- You installed the OpenShift CLI (`oc`).
- You have write access to the OpenShift Container Platform namespaces you need to work with.
- You have access to external or on-premise DNS.
- You created a gateway.
- You configured your gateway policies and`HTTP` routes.

Procedure

Set the following environment variable to your shared Redis-based instance URL:

```shell-session
$ export REDIS_URL=rediss://user:xxxxxx@some-redis.com:10340
```

Include the appropriate URI scheme for your environment:

- Secure Redis:`rediss://`
- Standard Redis:`redis://`

Create a`Secret` resource for your Redis URL as follows:

```shell-session
$ oc -n kuadrant-system create secret generic redis-config \
  --from-literal=URL=$REDIS_URL
```

Update your Limitador custom resource to use the secret that you created as follows:

```shell-session
$ oc patch limitador limitador --type=merge -n kuadrant-system -p '
spec:
  storage:
    redis:
      configSecretRef:
        name: redis-config
'
```

### 1.9. Enabling the Connectivity Link dynamic plugin for OpenShift Container Platform web consoleCopy link

You can use the Connectivity Link dynamic plugin to view and manage your gateways and policies in the OpenShift Container Platform web console. You must perform these steps on each OpenShift Container Platform cluster.

Prerequisites

- You are using a supported configuration of OpenShift Container Platform and required components.
- You are logged into OpenShift Container Platform as a cluster administrator.
- You are logged into the OpenShift Container Platform web console with administrator access.

Procedure

1. In the left navigation menu, select the Administrator perspective.
2. Click Home > Overview.
3. In the Status panel, click Dynamic Plugins > View all.
4. On the Console plugins tab, find the kuadrant-console-plugin entry in the table, which should be listed but disabled.
5. In the kuadrant-console-plugin row, click Disabled.
6. Select the Enable option, and click Save.
7. Wait for the plugin status to change to Loaded.

Verification

Refresh the OpenShift Container Platform web console. A new Connectivity Link menu item is displayed in the navigation sidebar.

1. You can click Connectivity Link > Overview to explore the available resources and to get started with creating a Gateway and configuring policies in the OpenShift Container Platform web console.

Next steps

- Create a gateway.
- Create policies.

### 1.10. Using your Red Hat subscriptionCopy link

Red Hat Connectivity Link is provided through a software subscription. To manage your subscriptions, access your account at the Red Hat Customer Portal.

1. Go to [access.redhat.com](https://access.redhat.com/).
2. If you do not already have an account, create one.
3. Log in to your account.
4. In the menu bar, click Subscriptions to view and manage your subscriptions.

## Legal NoticeCopy link

Copyright © Red Hat.

Except as otherwise noted below, the text of and illustrations in this documentation are licensed by Red Hat under the Creative Commons Attribution–Share Alike 3.0 Unported license . If you distribute this document or an adaptation of it, you must provide the URL for the original version.

Red Hat, as the licensor of this document, waives the right to enforce, and agrees not to assert, Section 4d of CC-BY-SA to the fullest extent permitted by applicable law.

Red Hat, the Red Hat logo, JBoss, Hibernate, and RHCE are trademarks or registered trademarks of Red Hat, LLC. or its subsidiaries in the United States and other countries.

Linux® is the registered trademark of Linus Torvalds in the United States and other countries.

XFS is a trademark or registered trademark of Hewlett Packard Enterprise Development LP or its subsidiaries in the United States and other countries.

The OpenStack® Word Mark and OpenStack logo are trademarks or registered trademarks of the Linux Foundation, used under license.

All other trademarks are the property of their respective owners.

 

 

[Github](https://github.com/redhat-documentation) [Youtube](https://www.youtube.com/@redhat) [Twitter](https://twitter.com/RedHat)

### Learn

- [Developer resources](https://developers.redhat.com/learn)
- [Cloud learning hub](https://docs.redhat.com/learn/learning-paths)
- [Interactive labs](https://www.redhat.com/en/interactive-labs)
- [Training and certification](https://www.redhat.com/services/training-and-certification)
- [Customer support](https://access.redhat.com/support)
- [See all documentation](https://docs.redhat.com/en/products)

### Try, buy, & sell

- [Product trial center](https://redhat.com/en/products/trials)
- [Red Hat Ecosystem Catalog](https://catalog.redhat.com/)
- [Red Hat Store](https://www.redhat.com/en/store)
- [Buy online (Japan)](https://www.redhat.com/about/japan-buy)

### Communities

- [Customer Portal Community](https://access.redhat.com/community)
- [Events](https://www.redhat.com/events)
- [How we contribute](https://www.redhat.com/about/our-community-contributions)

### About Red Hat Documentation

We help Red Hat users innovate and achieve their goals with our products and services with content they can trust. [Explore our recent updates](https://www.redhat.com/en/blog/whats-new-docsredhatcom).

### Making open source more inclusive

Red Hat is committed to replacing problematic language in our code, documentation, and web properties. For more details, see the [Red Hat Blog](https://www.redhat.com/en/blog/making-open-source-more-inclusive-eradicating-problematic-language).

### About Red Hat

We deliver hardened solutions that make it easier for enterprises to work across platforms and environments, from the core datacenter to the network edge.

### Theme

 

- [About Red Hat](https://redhat.com/en/about/company)
- [Jobs](https://redhat.com/en/jobs)
- [Events](https://redhat.com/en/events)
- [Locations](https://redhat.com/en/about/office-locations)
- [Contact Red Hat](https://redhat.com/en/contact)
- [Red Hat Blog](https://redhat.com/en/blog)
- [Inclusion at Red Hat](https://redhat.com/en/about/our-culture/diversity-equity-inclusion)
- [Cool Stuff Store](https://coolstuff.redhat.com/)
- [Red Hat Summit](https://www.redhat.com/en/summit)

© 2026 Red Hat

- [Privacy statement](https://redhat.com/en/about/privacy-policy)
- [Terms of use](https://redhat.com/en/about/terms-use)
- [All policies and guidelines](https://redhat.com/en/about/all-policies-guidelines)
- [Digital accessibility](https://redhat.com/en/about/digital-accessibility)

Back to top

 



---

Configuring and deploying gateway policies | Red Hat Connectivity Link | 1.3 | Red Hat Documentation

 

 

 

 

 

 

 

 

# Configuring and deploying gateway policies

---

Red Hat Connectivity Link 1.3

## Secure, protect, and connect APIs on OpenShift

Red Hat OpenShift Documentation Team

Legal Notice

Abstract

This guide explains how to use Connectivity Link policies on OpenShift to secure, protect, and connect an application API exposed by a Gateway based on Kubernetes Gateway API. This includes Gateways deployed on a single OpenShift cluster or distributed across multiple clusters.

---

## Chapter 1. Configuring and deploying gateway policiesCopy link

As a platform engineer or application developer, you can secure, protect, and connect an API exposed by a gateway that uses Gateway API by using Connectivity Link.

### 1.1. Secure, protect, and connect APIs on OpenShift Container Platform with Connectivity LinkCopy link

This guide shows how you can use Connectivity Link on OpenShift Container Platform to secure, protect, and connect an API exposed by a Gateway that uses Kubernetes Gateway API. This guide applies to the platform engineer and application developer user roles in Connectivity Link.

Important

In multi-cluster environments, you must perform the following steps in each cluster individually, unless specifically excluded.

#### 1.1.1. Connectivity Link capabilities in multicluster environmentsCopy link

You can use Connectivity Link capabilities in single or multiple OpenShift Container Platform clusters. The following features are designed to work across multiple clusters and in a single-cluster environment:

- Legal Notice
- Multicluster ingress: Connectivity Link provides multicluster ingress connectivity using DNS to bring traffic to your gateways by using a strategy defined in a`DNSPolicy`.
- Global rate limiting: Connectivity Link can enable global rate limiting use cases when configured to use a shared Redis-based store for counters based on limits defined by a`RateLimitPolicy`.
- Global auth: You can configure a Connectivity Link`AuthPolicy` to use external auth providers to ensure that different clusters exposing the same API can authenticate and allow in the same way.
- Automatic TLS certificate generation: You can configure a`TLSPolicy` to automatically provision TLS certificates based on Gateway listener hosts by using integration with cert-manager and ACME providers such as Let’s Encrypt.
- Integration with federated metrics stores: Connectivity Link has example dashboards and metrics for visualizing your gateways and observing traffic hitting those gateways across multiple clusters.

#### 1.1.2. Connectivity Link user role workflowsCopy link

Platform engineer: This guide shows how platform engineers can deploy gateways that provide secure communication and are protected and ready for use by application development teams to deploy APIs.

Platform engineers can use Connectivity Link in clusters in different geographic regions to bring specific traffic to geo-located gateways. This approach reduces latency, distributes load, and protects and secures with global rate limiting and auth policies.

- Application developer: This guide shows how application developers can override the Gateway-level global auth and rate limiting policies to configure application-level auth and rate limiting requirements for specific users.

### 1.2. Set up your environmentCopy link

This section shows how you can set up your environment variables and deploy the example Toystore application on your OpenShift Container Platform cluster.

Prerequisites

- Connectivity Link is installed on the OpenShift Container Platform cluster you are working with.
- The OpenShift CLI (`oc`) is installed.
- You are logged in to the OpenShift Container Platform cluster with write access to the namespaces you need to use.
- You have a DNS zone available for use.
- Optional. For rate limiting in a multicluster environment, you have installed Connectivity Link on more than one cluster. You also have a shared and accessible Redis-based datastore.
- Optional. For observability, OpenShift Container Platform user workload monitoring is configured to remote-write to a central storage system.

Procedure

Optional: Set the following environment variables:

```shell-session
$ export KUADRANT_GATEWAY_NS=api-gateway \
  export KUADRANT_GATEWAY_NAME=ingress-gateway \
  export KUADRANT_DEVELOPER_NS=toystore \
  export KUADRANT_AWS_ACCESS_KEY_ID=xxxx \
  export KUADRANT_AWS_SECRET_ACCESS_KEY=xxxx \
  export KUADRANT_AWS_DNS_PUBLIC_ZONE_ID=xxxx \
  export KUADRANT_ZONE_ROOT_DOMAIN=example.com
  export KUADRANT_CLUSTER_ISSUER_NAME=self-signed
```

These environment variables are described as follows:

`KUADRANT_CLUSTER_ISSUER_NAME`: Name of the certificate authority or issuer TLS certificates.

Note

If you know your environment variable values, you can set up the required YAML files to suit your environment.

- `KUADRANT_GATEWAY_NS`: Namespace for your gateway in OpenShift Container Platform.
- `KUADRANT_GATEWAY_NAME`: Name of your ingress gateway in OpenShift Container Platform.
- `KUADRANT_DEVELOPER_NS`: Namespace for the example Toystore app in OpenShift Container Platform.
- `KUADRANT_AWS_ACCESS_KEY_ID`: AWS key ID with access to manage your DNS zone.
- `KUADRANT_AWS_SECRET_ACCESS_KEY`: AWS secret access key with permissions to manage your DNS zone.
- `KUADRANT_AWS_DNS_PUBLIC_ZONE_ID`: AWS Route 53 zone ID for the Gateway. This is the ID of the hosted zone that is displayed in the AWS Route 53 console.
- `KUADRANT_ZONE_ROOT_DOMAIN`: Root domain in AWS Route 53 associated with your DNS zone ID.

Create the namespace for the Toystore app as follows:

```shell-session
$ oc create ns ${KUADRANT_DEVELOPER_NS}
```

Deploy the Toystore app to the developer namespace:

```shell-session
$ oc apply -f https://raw.githubusercontent.com/Kuadrant/Kuadrant-operator/main/examples/toystore/toystore.yaml -n ${KUADRANT_DEVELOPER_NS}
```

### 1.3. Set up a DNS provider secretCopy link

Your DNS provider supplies credentials to access the DNS zones that Connectivity Link can use to set up your DNS configuration. You must ensure that these credentials have access to only the DNS zones that you want Connectivity Link to manage with your DNSPolicy.

Note

You must apply the following`Secret` resource to each cluster. If you are adding an additional cluster, add it to the new cluster.

Prerequisites

- You installed Connectivity Link on one or more clusters.
- If you plan to use rate-limiting in a multicluster environment, you have a shared Redis-based datastore.
- You installed the OpenShift CLI (`oc`).
- You have write access to the OpenShift Container Platform namespaces you need to work with.
- You have access to external or on-premise DNS.
- You created a gateway.
- You configured your gateway policies and`HTTP` routes.

Procedure

Create the namespace that the Gateway will be deployed in as follows:

```shell-session
$ oc create ns ${KUADRANT_GATEWAY_NS}
```

Create the secret credentials in the same namespace as the Gateway as follows:

```shell-session
$ oc -n ${KUADRANT_GATEWAY_NS} create secret generic aws-credentials \
  --type=kuadrant.io/aws \
  --from-literal=AWS_ACCESS_KEY_ID=$KUADRANT_AWS_ACCESS_KEY_ID \
  --from-literal=AWS_SECRET_ACCESS_KEY=$KUADRANT_AWS_SECRET_ACCESS_KEY
```

Before adding a TLS certificate issuer, create the secret credentials in the`cert-manager` namespace as follows:

```shell-session
$ oc -n cert-manager create secret generic aws-credentials \
  --type=kuadrant.io/aws \
  --from-literal=AWS_ACCESS_KEY_ID=$KUADRANT_AWS_ACCESS_KEY_ID \
  --from-literal=AWS_SECRET_ACCESS_KEY=$KUADRANT_AWS_SECRET_ACCESS_KEY
```

### 1.4. Add a TLS certificate issuerCopy link

To secure communication to your Gateways, you must define a certification authority as an issuer for TLS certificates.

Note

This example uses the Let’s Encrypt TLS certificate issuer for simplicity, but you can use any certificate issuer supported by`cert-manager`. In multicluster environments, you must add your TLS issuer in each OpenShift Container Platform cluster.

Prerequisites

- You installed Connectivity Link on one or more clusters.
- If you plan to use rate-limiting in a multicluster environment, you have a shared Redis-based datastore.
- You installed the OpenShift CLI (`oc`).
- You have write access to the OpenShift Container Platform namespaces you need to work with.
- You have access to external or on-premise DNS.
- You created a gateway.
- You configured your gateway policies and`HTTP` routes.

Procedure

Enter the following command to define a TLS certificate issuer:

```shell-session
$ oc apply -f - <<EOF
apiVersion: cert-manager.io/v1
kind: ClusterIssuer
metadata:
  name: ${KUADRANT_CLUSTER_ISSUER_NAME}
spec:
  selfSigned: {}
EOF
```

Wait for the`ClusterIssuer` to become ready as follows:

```shell-session
$ oc wait clusterissuer/${KUADRANT_CLUSTER_ISSUER_NAME} --for=condition=ready=true
```

### 1.5. Creating a gatewayCopy link

As a platform engineer, create a gateway to your OpenShift Container Platform cluster to set up the infrastructure used by application developers. The following example assumes that you are using the OpenShift Container Platform Cluster Ingress Operator (CIO).

Important

When using the Gateway API custom resource definitions (CRDs) provided in OpenShift Container Platform 4.19 or newer, you must create a`GatewayClass` named`openshift-default` and specify a`controllerName` of`openshift.io/gateway-controller/v1`. For more details, see the [Getting started with Gateway API for the Ingress Operator (OpenShift Container Platform documentation)](https://docs.redhat.com/en/documentation/openshift_container_platform/4.21/html/ingress_and_load_balancing/configuring-ingress-cluster-traffic#nw-ingress-gateway-api-enable_ingress-gateway-api).

If you are using OpenShift Service Mesh on OpenShift Container Platform 4.19 and newer and you set the`ISTIO_GATEWAY_CONTROLLER_NAMES` variable to`istio.io/gateway-controller` during your Connectivity Link installation, then you can use the`GatewayClass` custom resource (CR) created by default by OpenShift Service Mesh. Make sure you use the corresponding`spec.gatewayClassName` value in your`Gateway` CR.

Prerequisites

- Connectivity Link is installed on the OpenShift Container Platform cluster you are working with.
- You set the`ISTIO_GATEWAY_CONTROLLER_NAMES` environment variable value to`openshift.io/gateway-controller/v1` during your Connectivity Link installation.
- You created a`GatewayClass` named`openshift-default` and specified a`controllerName` of`openshift.io/gateway-controller/v1`.
- The OpenShift CLI (`oc`) is installed.
- You are logged in to the OpenShift Container Platform cluster with write access to the namespaces you need to use.
- You have a DNS zone available for use.

Procedure

Create a gateway that uses the OpenShift Container Platform CIO by running the following command:

```shell-session
$ oc apply -f - <<EOF
apiVersion: gateway.networking.k8s.io/v1
kind: Gateway
metadata:
  name: ${KUADRANT_GATEWAY_NAME}
  namespace: ${KUADRANT_GATEWAY_NS}
  labels:
    kuadrant.io/gateway: "true"
spec:
    gatewayClassName: openshift-default
    listeners:
    - allowedRoutes:
        namespaces:
          from: All
      hostname: "api.${KUADRANT_ZONE_ROOT_DOMAIN}"
      name: api
      port: 443
      protocol: HTTPS
      tls:
        certificateRefs:
        - group: ""
          kind: Secret
          name: api-${KUADRANT_GATEWAY_NAME}-tls
        mode: Terminate
EOF
```

Important

In a multicluster environment, for Connectivity Link to balance traffic by using DNS across clusters, you must specify a gateway with a shared hostname. You can define this by using an HTTPS listener with a wildcard hostname based on the root domain.

Verification

Check the status of your gateway by running the following command:

```shell-session
$ oc get gateway ${KUADRANT_GATEWAY_NAME} -n ${KUADRANT_GATEWAY_NS} -o=jsonpath='{.status.conditions[?(@.type=="Accepted")].message}{"\n"}{.status.conditions[?(@.type=="Programmed")].message}'
```

The statuses`Accepted` and`Programmed` mean that your gateway is valid and assigned an external address.

Check the status of your HTTPS listener by running the following command:

```shell-session
$ oc get gateway ${KUADRANT_GATEWAY_NAME} -n ${KUADRANT_GATEWAY_NS} -o=jsonpath='{.status.listeners[0].conditions[?(@.type=="Programmed")].message}'
```

Next steps

- Configure a TLS policy so that the The HTTPS listener can accept traffic.

### 1.6. Configure your Gateway policies and HTTP routeCopy link

While your Gateway is now deployed, it has no exposed endpoints and your HTTPS listener is not programmed. Next, you can do take the following steps:

- Define a`TLSPolicy` that leverages your`CertificateIssuer` to set up your HTTPS listener certificates.
- Define an`HTTPRoute` for your Gateway to communicate with your backend application API.
- Define an`AuthPolicy` to set up a default HTTP`403` response for any unprotected endpoints
- Define and a`RateLimitPolicy` to set up a default artificially low global limit to further protect any endpoints exposed by the Gateway.
- Define a`DNSPolicy` with a load balancing strategy for your Gateway.

Important

In multicluster environments, you must perform the following steps in each cluster individually, unless specifically excluded.

Prerequisites

- You installed Connectivity Link on one or more clusters.
- If plan to use rate-limiting in a multicluster environment, you have a shared Redis-based datastore.
- You installed the OpenShift CLI (`oc`).
- You have write access to the OpenShift Container Platform namespaces you need to work with.
- You have access to external or on-premise DNS.
- You created a gateway.

Procedure

Set the`TLSPolicy` for your Gateway as follows:

```shell-session
$ oc apply -f - <<EOF
apiVersion: kuadrant.io/v1
kind: TLSPolicy
metadata:
  name: ${KUADRANT_GATEWAY_NAME}-tls
  namespace: ${KUADRANT_GATEWAY_NS}
spec:
  targetRef:
    name: ${KUADRANT_GATEWAY_NAME}
    group: gateway.networking.k8s.io
    kind: Gateway
  issuerRef:
    group: cert-manager.io
    kind: ClusterIssuer
    name: ${KUADRANT_CLUSTER_ISSUER_NAME}
EOF
```

Check that your TLS policy has an`Accepted` and`Enforced` status as follows:

```shell-session
$ oc get tlspolicy ${KUADRANT_GATEWAY_NAME}-tls -n ${KUADRANT_GATEWAY_NS} -o=jsonpath='{.status.conditions[?(@.type=="Accepted")].message}{"\n"}{.status.conditions[?(@.type=="Enforced")].message}'
```

This may take a few minutes depending on the TLS provider, for example, Let’s Encrypt.

#### 1.6.1. Create an HTTP route for your applicationCopy link

Procedure

Create an`HTTPRoute` for the example Toystore application as follows:

```shell-session
$ oc apply -f - <<EOF
apiVersion: gateway.networking.k8s.io/v1
kind: HTTPRoute
metadata:
  name: toystore
  namespace: ${KUADRANT_DEVELOPER_NS}
  labels:
    deployment: toystore
    service: toystore
spec:
  parentRefs:
  - name: ${KUADRANT_GATEWAY_NAME}
    namespace: ${KUADRANT_GATEWAY_NS}
  hostnames:
  - "api.${KUADRANT_ZONE_ROOT_DOMAIN}"
  rules:
  - matches:
    - method: GET
      path:
        type: PathPrefix
        value: "/cars"
    - method: GET
      path:
        type: PathPrefix
        value: "/health"
    backendRefs:
    - name: toystore
      port: 80
EOF
```

#### 1.6.2. Set the default AuthPolicyCopy link

Procedure

Set a default`AuthPolicy` with a`deny-all` setting for your Gateway as follows:

```shell-session
$ oc apply -f - <<EOF
apiVersion: kuadrant.io/v1
kind: AuthPolicy
metadata:
  name: ${KUADRANT_GATEWAY_NAME}-auth
  namespace: ${KUADRANT_GATEWAY_NS}
spec:
  targetRef:
    group: gateway.networking.k8s.io
    kind: Gateway
    name: ${KUADRANT_GATEWAY_NAME}
  defaults:
   when:
     - predicate: "request.path != '/health'"
   rules:
    authorization:
      deny-all:
        opa:
          rego: "allow = false"
    response:
      unauthorized:
        headers:
          "content-type":
            value: application/json
        body:
          value: |
            {
              "error": "Forbidden",
              "message": "Access denied by default by the gateway operator. If you are the administrator of the service, create a specific auth policy for the route."
            }
EOF
```

Check that your`AuthPolicy` has`Accepted` and`Enforced` status as follows:

```shell-session
$ oc get authpolicy ${KUADRANT_GATEWAY_NAME}-auth -n ${KUADRANT_GATEWAY_NS} -o=jsonpath='{.status.conditions[?(@.type=="Accepted")].message}{"\n"}{.status.conditions[?(@.type=="Enforced")].message}'
```

#### 1.6.3. Set the default RateLimitPolicyCopy link

Procedure

Set the default`RateLimitPolicy` with a`low-limit` setting for your Gateway as follows:

```shell-session
$ oc apply -f  - <<EOF
apiVersion: kuadrant.io/v1
kind: RateLimitPolicy
metadata:
  name: ${KUADRANT_GATEWAY_NAME}-rlp
  namespace: ${KUADRANT_GATEWAY_NS}
spec:
  targetRef:
    group: gateway.networking.k8s.io
    kind: Gateway
    name: ${KUADRANT_GATEWAY_NAME}
  defaults:
    limits:
      "low-limit":
        rates:
        - limit: 1
          window: 10s
EOF
```

It might take a few minutes for the`RateLimitPolicy` to be applied depending on your cluster. The limit in this example is artificially low to show it working easily.

Check that your`RateLimitPolicy` has`Accepted` and`Enforced` status as follows:

```shell-session
$ oc get ratelimitpolicy ${KUADRANT_GATEWAY_NAME}-rlp -n ${KUADRANT_GATEWAY_NS} -o=jsonpath='{.status.conditions[?(@.type=="Accepted")].message}{"\n"}{.status.conditions[?(@.type=="Enforced")].message}'
```

#### 1.6.4. Set the DNS policyCopy link

Procedure

Set the`DNSPolicy` for your Gateway as follows:

```shell-session
$ oc apply -f - <<EOF
apiVersion: kuadrant.io/v1
kind: DNSPolicy
metadata:
  name: ${KUADRANT_GATEWAY_NAME}-dnspolicy
  namespace: ${KUADRANT_GATEWAY_NS}
spec:
  healthCheck:
    failureThreshold: 3
    interval: 1m
    path: /health
  loadBalancing:
    defaultGeo: true
    geo: GEO-NA
    weight: 120
  targetRef:
    name: ${KUADRANT_GATEWAY_NAME}
    group: gateway.networking.k8s.io
    kind: Gateway
  providerRefs:
  - name: aws-credentials # Secret created earlier
EOF
```

The`DNSPolicy` uses the DNS Provider`Secret` that you defined earlier. The`geo` in this example is`GEO-NA`, but you can change this to suit your requirements.

Check that your`DNSPolicy` has status of`Accepted` and`Enforced` as follows:

```shell-session
$ oc get dnspolicy ${KUADRANT_GATEWAY_NAME}-dnspolicy -n ${KUADRANT_GATEWAY_NS} -o=jsonpath='{.status.conditions[?(@.type=="Accepted")].message}{"\n"}{.status.conditions[?(@.type=="Enforced")].message}'
```

This might take a few minutes.

Check the status of the DNS health checks that are enabled on your DNSPolicy as follows:

```shell-session
$ oc get dnspolicy ${KUADRANT_GATEWAY_NAME}-dnspolicy -n ${KUADRANT_GATEWAY_NS} -
```

These health checks flag a published endpoint as healthy or unhealthy based on defined configuration. When unhealthy, an endpoint will not be published if it has not already been published to the DNS provider. An endpoint will only be unpublished if it is part of a multi-value A record, and in all cases can be observed in the DNSPolicy status.

#### 1.6.5. Test your default rate limit and auth policiesCopy link

You can use a`curl` command to test the default`low-limit` and`deny-all` policies for your Gateway.

Procedure

Enter the following`curl` command:

```shell-session
$ while :; do curl -k --write-out '%{http_code}\n' --silent --output /dev/null  "https://api.$KUADRANT_ZONE_ROOT_DOMAIN/cars" | grep -E --color "\b(429)\b|$"; sleep 1; done
```

You should see a HTTP`403` responses.

### 1.7. About token-based rate limiting with TokenRateLimitPolicyCopy link

Red Hat Connectivity Link provides the`TokenRateLimitPolicy` custom resource to enforce rate limits based on token consumption rather than the number of requests. This policy extends the Envoy Rate Limit Service (RLS) protocol with automatic token usage extraction. It is particularly useful for protecting Large Language Model (LLM) APIs, where the cost and resource usage correlate more closely with the number of tokens processed.

Unlike the standard`RateLimitPolicy` which counts requests,`TokenRateLimitPolicy` counts tokens by extracting usage metrics in the body of the AI inference API call, allowing for finer-grained control over API usage based on actual workload.

#### 1.7.1. How token rate limiting worksCopy link

The`TokenRateLimitPolicy` tracks cumulative token usage per client. Before forwarding a request, it checks if the client has already exceeded their limit from previous usage. After the upstream responds, it extracts the actual token cost and updates the client’s counter.

The flow is as follows:

1. On an incoming request, the gateway evaluates the matching rules and predicates from the`TokenRateLimitPolicy` resources.
2. If the request matches, the gateway prepares the necessary rate limit descriptors and monitors the response.
3. After receiving the response, the gateway extracts the`usage.total_tokens` field from the JSON response body.
4. The gateway then sends a`RateLimitRequest` to Limitador, including the actual token count as a`hits_addend`.
5. Limitador tracks the cumulative token usage and responds to the gateway with`OK` or`OVER_LIMIT`.

#### 1.7.2. Key features and use casesCopy link

- Enforces limits based on token usage by extracting the`usage.total_tokens` field from an OpenAI-style inference JSON response body.
- Suitable for consumption-based APIs such as LLMs where the cost is tied to token counts.
- Allows defining different limits based on criteria such as user identity, API endpoints, or HTTP methods.
- Works with`AuthPolicy` to apply specific limits to authenticated users or groups.
- Inherits functionalities from`RateLimitPolicy`, including defining multiple limits with different durations and using Redis for shared counters in multi-cluster environments.

#### 1.7.3. Integrating with AuthPolicyCopy link

You can combine`TokenRateLimitPolicy` with`AuthPolicy` to apply token limits based on authenticated user identity. When an`AuthPolicy` successfully authenticates a request, it injects identity information that is used by the`TokenRateLimitPolicy` to select the appropriate limit.

For example, you can define different token limits for users belonging to 'free-tier' compared to 'premium-tier' groups, identified using claims in a JWT validated by`AuthPolicy`.

### 1.8. Configure token-based rate limiting with TokenRateLimitPolicyCopy link

Red Hat Connectivity Link provides the`TokenRateLimitPolicy` custom resource to enforce rate limits based on token consumption rather than the number of requests. This policy extends the Envoy Rate Limit Service (RLS) protocol with automatic token usage extraction. It is particularly useful for protecting Large Language Model (LLM) APIs, where the cost and resource usage correlate more closely with the number of tokens processed.

Unlike the standard`RateLimitPolicy` which counts requests,`TokenRateLimitPolicy` counts tokens by extracting usage metrics in the body of the AI inference API call, allowing for finer-grained control over API usage based on actual workload.

#### 1.8.1. How token rate limiting worksCopy link

The`TokenRateLimitPolicy` tracks cumulative token usage per client. Before forwarding a request, it checks if the client has already exceeded their limit from previous usage. After the upstream responds, it extracts the actual token cost and updates the client’s counter.

The flow is as follows:

1. On an incoming request, the gateway evaluates the matching rules and predicates from the`TokenRateLimitPolicy` resources.
2. If the request matches, the gateway prepares the necessary rate limit descriptors and monitors the response.
3. After receiving the response, the gateway extracts the`usage.total_tokens` field from the JSON response body.
4. The gateway then sends a`RateLimitRequest` to Limitador, including the actual token count as a`hits_addend`.
5. Limitador tracks the cumulative token usage and responds to the gateway with`OK` or`OVER_LIMIT`.

#### 1.8.2. Key features and use casesCopy link

Token-based rate limiting means you complete the following tasks:

- Enforces limits based on token usage by extracting the`usage.total_tokens` field from an OpenAI-style inference JSON response body.
- Suitable for consumption-based APIs such as LLMs where the cost is tied to token counts.
- Allows defining different limits based on criteria such as user identity, API endpoints, or HTTP methods.
- Works with`AuthPolicy` to apply specific limits to authenticated users or groups.
- Inherits functionalities from`RateLimitPolicy`, including defining multiple limits with different durations and using Redis for shared counters in multi-cluster environments.

#### 1.8.3. Integrating with AuthPolicyCopy link

You can combine`TokenRateLimitPolicy` with`AuthPolicy` to apply token limits based on authenticated user identity. When an`AuthPolicy` successfully authenticates a request, it injects identity information which can then be used by the`TokenRateLimitPolicy` to select the appropriate limit.

For example, you can define different token limits for users belonging to 'free-tier' versus 'premium-tier' groups, identified using claims in a JWT validated by`AuthPolicy`.

#### 1.8.4. Configure token-based rate limiting for LLM APIsCopy link

This guide shows how to configure`TokenRateLimitPolicy` to You can protect a hypothetical LLM API deployed on OpenShift Container Platform, integrated with`AuthPolicy` for user-specific limits.

Prerequisites

- Connectivity Link is installed on your OpenShift Container Platform cluster.
- A Gateway and an`HTTPRoute` are configured to expose your service.
- An`AuthPolicy` is configured for authentication (for example, using API keys or OIDC).
- Redis is configured for Limitador if running in a multi-cluster setup or requiring persistent counters.
- Your upstream service is configured to return an OpenAI-compatible JSON response containing a`usage.total_tokens` field in the response body.

Procedure

Create a`TokenRateLimitPolicy` resource. This example defines two limits: one for free users on a 10,000 tokens per day request limit, and one for pro users with a 100,000 tokens per day request limit.

```yaml
apiVersion: kuadrant.io/v1alpha1
kind: TokenRateLimitPolicy
metadata:
  name: llm-protection
spec:
  targetRef:
    group: gateway.networking.k8s.io
    kind: Gateway
    name: ai-gateway
  limits:
    free-users:
      rates:
        - limit: 10000 # 10k tokens per day for free tier
          window: 24h
      when:
        - predicate: request.path == "/v1/chat/completions" # Inference traffic only
        - predicate: |
            auth.identity.groups.split(",").exists(g, g == "free")
      counters:
        - expression: auth.identity.userid
    pro-users:
      rates:
        - limit: 100000 # 200 tokens per minute for pro users
          window: 24h
      when:
        - predicate: request.path == "/v1/chat/completions" # Inference traffic only
        - predicate: |
            auth.identity.groups.split(",").exists(g, g == "pro")
      counters:
        - expression: auth.identity.userid
```

Apply the policy:

```shell-session
$ oc apply -f your-tokenratelimitpolicy.yaml -n my-api-namespace
```

Check the status of the policy to ensure it has been accepted and enforced on the target`HTTPRoute`. Look for conditions with`type: Accepted` and`type: Enforced` with`status: "True"`.

```shell-session
$ oc get tokenratelimitpolicy llm-protection -n my-api-namespace -o jsonpath='{.status.conditions}'
```

Send requests to your API endpoint, including the required authentication details.

```shell-session
$ curl -H "Authorization: <auth-details>" \
     -d '{"model": "gpt-4", "messages": [{"role": "user", "content": "Hello"}]}' \
     <your-api-endpoint>
```

Verification

- Ensure that your upstream service responds with an OpenAI-compatible JSON body containing the`usage.total_tokens` field.
- Requests made when the client is within their token limits should receive a`200 OK` response or other success status and their token counter is updated.
- Requests made when the client has already exceeded their token limits should receive a`429 Too Many Requests` response.

### 1.9. Override your gateway policies for auth and rate limitingCopy link

As an application developer, you can override your existing gateway-level policies to configure your application-level auth and rate limiting requirements.

You can allow authenticated access to the Toystore API by defining a new`AuthPolicy` that targets the`HTTPRoute` resource created in the previous section.

Important

Any new`HTTPRoutes` are affected by the existing gateway-level policy. Because you want users to now access this API, you must override that gateway policy. For simplicity, you can use API keys to authenticate the requests, but other options such as OpenID Connect are also available.

Prerequisites

- Connectivity Link is installed.
- You configured Connectivity Link policies.
- You installed the OpenShift CLI (`oc`).
- You are logged into OpenShift Container Platform as a cluster administrator.

Procedure

Ensure that your Connectivity Link system namespace is set correctly by running the following command:

```shell-session
$ export KUADRANT_SYSTEM_NS=$(oc get kuadrant -A -o jsonpath="{.items[0].metadata.namespace}")
```

Define API keys for bob and alice users as follows:

```shell-session
$ oc apply -f - <<EOF
apiVersion: v1
kind: Secret
metadata:
  name: bob-key
  namespace: ${KUADRANT_SYSTEM_NS}
  labels:
    authorino.kuadrant.io/managed-by: authorino
    app: toystore
  annotations:
    secret.kuadrant.io/user-id: bob
stringData:
  api_key: IAMBOB
type: Opaque
---
apiVersion: v1
kind: Secret
metadata:
  name: alice-key
  namespace: ${KUADRANT_SYSTEM_NS}
  labels:
    authorino.kuadrant.io/managed-by: authorino
    app: toystore
  annotations:
    secret.kuadrant.io/user-id: alice
stringData:
  api_key: IAMALICE
type: Opaque
EOF
```

Create a new`AuthPolicy` in a different namespace that overrides the`deny-all` policy created earlier and accepts the API keys as follows:

```shell-session
$ oc apply -f - <<EOF
apiVersion: kuadrant.io/v1
kind: AuthPolicy
metadata:
  name: toystore-auth
  namespace: ${KUADRANT_DEVELOPER_NS}
spec:
  targetRef:
    group: gateway.networking.k8s.io
    kind: HTTPRoute
    name: toystore
  defaults:
   when:
     - predicate: "request.path != '/health'"
   rules:
    authentication:
      "api-key-users":
        apiKey:
          selector:
            matchLabels:
              app: toystore
        credentials:
          authorizationHeader:
            prefix: APIKEY
    response:
      success:
        filters:
          "identity":
            json:
              properties:
                "userid":
                  selector: auth.identity.metadata.annotations.secret\.kuadrant\.io/user-id
EOF
```

### 1.10. Overriding the low-limit RateLimitPolicy for specific usersCopy link

The configured Gateway limits provide a good set of limits for the general case. However, as the developer of the Toystore API, you might want to only allow a certain number of requests for specific users, and a general limit for all other users.

Important

Any new HTTPRoutes are affected by the existing Gateway-level policy. Because you want users to now access this API, you must override that Gateway policy. For simplicity, you can use API keys to authenticate the requests, but other options such as OpenID Connect are also available.

Prerequisites

- You installed Connectivity Link on one or more clusters.
- If you plan to use rate-limiting in a multicluster environment, you have a shared Redis-based datastore.
- You installed the OpenShift CLI (`oc`).
- You have write access to the OpenShift Container Platform namespaces you need to work with.
- You have access to external or on-premise DNS.
- You created a gateway.
- You configured your gateway policies and`HTTP` routes.

Procedure

Create a new`RateLimitPolicy` in a different namespace to override the default`low-limit` policy created previously and set rate limits for specific users as follows:

```shell-session
$ oc apply -f - <<EOF
apiVersion: kuadrant.io/v1
kind: RateLimitPolicy
metadata:
  name: toystore-rlp
  namespace: ${KUADRANT_DEVELOPER_NS}
spec:
  targetRef:
    group: gateway.networking.k8s.io
    kind: HTTPRoute
    name: toystore
  limits:
    "general-user":
      rates:

      - limit: 5
        window: 10s
      counters:
      - expression: auth.identity.userid
      when:
      - predicate: "auth.identity.userid != 'bob'"
    "bob-limit":
      rates:
      - limit: 2
        window: 10s
      when:
      - predicate: "auth.identity.userid == 'bob'"
EOF
```

It might take a few minutes for the`RateLimitPolicy` to be applied, depending on your cluster.

Check that the`RateLimitPolicy` has a status of`Accepted` and`Enforced` as follows:

```shell-session
$ oc get ratelimitpolicy -n ${KUADRANT_DEVELOPER_NS} toystore-rlp -o=jsonpath='{.status.conditions[?(@.type=="Accepted")].message}{"\n"}{.status.conditions[?(@.type=="Enforced")].message}'
```

Check that the status of the`HTTPRoute` is now affected by the`RateLimitPolicy` in the same namespace:

```shell-session
$ oc get httproute toystore -n ${KUADRANT_DEVELOPER_NS} -o=jsonpath='{.status.parents[0].conditions[?(@.type=="kuadrant.io/RateLimitPolicyAffected")].message}'
```

Verification

Send requests as user alice as follows:

```shell-session
$ while :; do curl -k --write-out '%{http_code}\n' --silent --output /dev/null -H 'Authorization: APIKEY IAMALICE' "https://api.$KUADRANT_ZONE_ROOT_DOMAIN/cars" | grep -E --color "\b(429)\b|$"; sleep 1; done
```

You should see HTTP status`200` every second for 5 seconds, followed by HTTP status`429` every second for 5 seconds.

Send requests as user bob as follows:

```shell-session
$ while :; do curl -k --write-out '%{http_code}\n' --silent --output /dev/null -H 'Authorization: APIKEY IAMBOB' "https://api.$KUADRANT_ZONE_ROOT_DOMAIN/cars" | grep -E --color "\b(429)\b|$"; sleep 1; done
```

You should see HTTP status`200` every second for 2 seconds, followed by HTTP status`429` every second for 8 seconds.

## Chapter 2. Using on-premise DNS with CoreDNSCopy link

You can secure, protect, and connect an API exposed by a gateway that uses Gateway API by using Connectivity Link.

### 2.1. About using on-premise DNS with CoreDNSCopy link

You can self-manage your on-premise DNS by integrating CoreDNS with your DNS infrastructure through access control and zone delegation. Connectivity Link combines the DNS Operator with CoreDNS to simplify your management and security for on-premise DNS servers. You can use CoreDNS in both single-cluster and multi-cluster scenarios.

CoreDNS is best used in environments that change often, where using a DNS-as-code approach makes sense. The following situations are example use cases for integrating with CoreDNS:

- You need to avoid dependency on external cloud DNS services.
- You have regulatory or compliance requirements mandating self-hosted infrastructure.
- You need to keep full control over DNS records.
- You want to delegate specific DNS zones from existing DNS servers to Kubernetes-managed CoreDNS.
- You require consistent DNS management across hybrid or multicloud environments.
- You need to reduce DNS operational costs by eliminating per-query charges.
- You do not want to directly manage DNS records on the on-premise DNS server.
- You need to keep authoritative control on edge DNS servers.

For example:

- Configure your authoritative on-premise DNS server to delegate a specific subdomain, such as`deployment.example.local`, to CoreDNS instances managed by Connectivity Link.
- Any pod-level`dnsPolicy` CR can then interact with the CoreDNS provider within the OpenShift Container Platform cluster. You can specify the DNS provider that handles the records for the targeted gateways in the`delegate` field of the DNS policy.
- The CoreDNS instance becomes authoritative for the delegated subdomain and manages the necessary DNS records for gateways within that subdomain.

### 2.2. CoreDNS integration architectureCopy link

CoreDNS is a DNS server that consists of default plugins that do several tasks, for example:

- Automatically detects when you add new services to your cluster and adds them to directories.
- Caches recent addresses to avoid the latency of repeated lookups.
- Runs health checks and skips over services that are down.
- Provides dynamic redirects by rewriting queries as they come in.

You can add plugins for observability and other services that you require by updating the CoreDNS with the DNS Operator.

With the DNS Operator, DNS is the first layer of traffic management. You can deploy the DNS Operator to multiple clusters and coordinate them all on a given zone. This means that you can use a shared domain name across clusters to balance traffic based on your requirements.

#### 2.2.1. Technical workflowCopy link

To give you integration with CoreDNS, Connectivity Link extends the DNS Operator with the`kuadrant` CoreDNS plugin that sources records from the`kuadrant.io/v1alpha1/DNSRecord` custom resource (CR) and applies location-based and weighted response capabilities.

You can create DNS records that point to the CoreDNS secret in one of the three following ways:

- Create it manually.
- Use a non-delegating DNS policy at a gateway with routes attached. The`kuadrant-operator` CR creates`DNSRecord` CRs with the secret.
- Use a delegating DNS policy at a gateway. The delegating policy results in the creation of a delegating`DNSRecord` CR without a secret reference. All delegating DNS Records are combined into a single authoritative DNS Record. The authoritative`DNSRecord` uses a default provider secret.

The DNS Operator reconciles authoritative records that have the CoreDNS secret referenced and applies labels only to those CRs. CoreDNS watches those records and matches the labels with zones configured in the Corefile. If there is a match, the authoritative`DNSRecord` CR is used to serve a DNS response.

There are no changes to the`dnsPolicy` API and no required changes to the policy controllers. This integration is isolated to the DNS Operator and the CoreDNS plugin.

The CoreDNS integration supports both single-cluster and multi-cluster deployments.

Single cluster

Organizations that want to self-host their DNS infrastructure without the complexity of multi-cluster coordination can use single-cluster CoreDNS integration. Using delegation is not required.

A single cluster runs both DNS Operator and CoreDNS with the plugin. CoreDNS only serves`DNSRecord` CRs that point to a CoreDNS provider secret. The CoreDNS plugin watches for DNS records labeled with the appropriate zone name and serves them directly. Any authoritative`DNSRecord` CR has endpoints from the single cluster.

Multi-cluster delegation

Multiple clusters can participate in serving a single DNS zone through Kubernetes role-based delegation that enables geographic distribution of DNS services and high availability. This implementation enables workloads across multiple clusters to contribute DNS endpoints to a unified zone, with primary clusters maintaining the authoritative view. The role of a cluster is determined by the DNS Operator.

Multi-cluster delegation uses`kubeconfig`-based interconnection secrets that grant read access to`DNSRecord` resources across clusters. This approach reuses Kubernetes role-based access (RBAC).

- Primary clusters: Run both the DNS Operator and CoreDNS and serve the DNS records that are local. The DNS Operator running on primary clusters that delegate reconciles`DNSRecord` CRs by reading and merging them. Primary clusters then serve these authoritative`DNSRecord` CRs. Each CoreDNS instance serves the relevant authoritative`DNSRecord` for the configured zone. Each primary cluster can independently serve the complete record set.
- Secondary clusters: Only run the DNS Operator. These clusters create delegating`DNSRecord` CRs but do not interact with DNS providers directly. If the secret and subdomain are properly configured, these DNS records are automatically reconciled in the primary cluster.

Zone labeling CoreDNS integration uses a label-based filtering mechanism. The DNS Operator applies a zone-specific label to`DNSRecord` CRs when the CRs are reconciled. The CoreDNS plugin only watches for`DNSRecord` CRs with labels that match configured zones. This method reduces resource use and provides clear zone boundaries. GEO and weighted routing

GEO and weighted routing use the same algorithmic approach as cloud providers. By using CoreDNS, you can have parity with cloud DNS provider capabilities and maintain full control over your DNS infrastructure.

- GEO routing: The CoreDNS`geoip` plugin uses geographical-database integration to return region-specific endpoints.
- Weighted routing: Applies probabilistic selection based on endpoint weights.
- Combined routing: First applies GEO filtering, then weighted selection within the matched region.

### 2.3. CoreDNS DNS records security considerationsCopy link

As an infrastructure engineer or business lead, you can implement several security best practices when using CoreDNS with Connectivity Link.

Zone configuration`DNSRecord` custom resources (CRs) have full control over a Zone’s name server (NS) records. Anyone who can create or change a`DNSRecord` that targets the root of the main domain name with NS records can decide where the all Zone traffic goes. Consider this as you plan your access controls.

For example, use the following access-control best practices:

Use least-privilege policies:

- Strict RBAC: Only grant`DNSRecord` creation permissions to trusted infrastructure engineers and cluster administrators.
- Namespace isolation: Grant application developers`DNSRecord` permissions only in their own namespaces.

You can use the following RBAC configuration example to get you started with defining access:

```yaml
apiVersion: rbac.authorization.k8s.io/v1
kind: Role
metadata:
  name: dns-zone-config-admin
  namespace: kuadrant-coredns
rules:
- apiGroups: ["kuadrant.io"]
  resources: ["dnsrecords"]
  verbs: ["create", "update", "patch", "delete"]
---
apiVersion: rbac.authorization.k8s.io/v1
kind: RoleBinding
metadata:
  name: dns-zone-config-admin-binding
  namespace: kuadrant-coredns
subjects:
- kind: User
  name: dns-admin@example.com  # Only trusted administrators
  apiGroup: rbac.authorization.k8s.io
roleRef:
  kind: Role
  name: dns-zone-config-admin
  apiGroup: rbac.authorization.k8s.io
```

### 2.4. Using CoreDNS with a single clusterCopy link

You can use CoreDNS as a DNS provider for Connectivity Link in a single-cluster, on-premise environment. This integration allows Connectivity Link to manage DNS entries within your internal network infrastructure.

Important

In a single-cluster setup, ensure that the`endpoints` IP address value you use is reachable from the`kuadrant-system` namespace. The default IP address,`10.96.0.10`, is the internal cluster-wide DNS address.

Prerequisites

- Version control: Use a DNS-as-code approach. Store zone configuration`DNSRecord` CRs in Git and use standardized review processes.
- Connectivity Link is installed on the OpenShift Container Platform cluster.
- The OpenShift CLI (`oc`) is installed.
- You have administrator privileges on the OpenShift Container Platform cluster.
- You are logged in to the cluster you want to configure.
- Your OpenShift Container Platform clusters have support for the`loadbalanced` service type that allows UDP and TCP traffic on port 53, such as MetalLB.
- You have access to configure your authoritative on-premise DNS server.
- Podman is installed.

Procedure

Set up your cluster. Set the following environment variables for your cluster context:

```shell-session
$ export CTX_PRIMARY=$(oc config current-context) \
  export KUBECONFIG=~/.kube/config \
  export PRIMARY_CLUSTER_NAME=local-cluster \
  export ONPREM_DOMAIN=<onprem-domain> \
  export KUADRANT_SUBDOMAIN=""
```

For the`ONPREM_DOMAIN` variable value, use your actual root domain. For the`KUADRANT_SUBDOMAIN` variable value, valid values are empty or`kuadrant`.

Extract the CoreDNS manifests from`dns-operator` bundle by running the following commands:

```shell-session
$ podman create --name bundle registry.redhat.io/rhcl-1/dns-operator-bundle:rhcl-1.3.0
```

```shell-session
$ podman cp bundle:/coredns/manifests.yaml ./coredns-manifests.yaml
```

```shell-session
$ podman rm bundle
```

Apply the manifests to the cluster by running the following command:

```shell-session
$ oc apply -f ./coredns-manifests.yaml
```

Create a`ConfigMap` to define the authoritative zone for CoreDNS. This minimal configuration enables the`kuadrant` plugin and GeoIP features.

```shell-session
$ cat | oc --context $CTX_PRIMARY apply -f -
apiVersion: v1
kind: ConfigMap
metadata:
  name: coredns-kuadrant-config
  namespace: kuadrant-coredns
data:
  Corefile: |
    ${KUADRANT_SUBDOMAIN}.${ONPREM_DOMAIN}:53 {
        debug
        errors
        health {
            lameduck 5s
        }
        ready
        log
        geoip <GeoIP-database-name>.mmdb {
            edns-subnet
        }
        metadata
        kuadrant
    }
```

Note

For production or exact GeoIP routing, mount your licensed MaxMind GeoIP database into the CoreDNS pod and update the filename in the`data.Corefile.geoip` parameter.

Update the CoreDNS deployment to use the new configuration by running the following command:

```shell-session
$ oc --context $CTX_PRIMARY -n kuadrant-system patch deployment kuadrant-coredns --patch '{"spec":{"template":{"spec":{"volumes":[{"name":"config-volume","configMap":{"name":"coredns-kuadrant-config","items":[{"key":"Corefile","path":"Corefile"}]}}]}}}}'
```

Set a watch-and-wait command for the deployment rollout to complete:

```shell-session
$ oc --context $CTX_PRIMARY -n kuadrant-system rollout status deployment/kuadrant-coredns
```

Example output

```plaintext
kuadrant-coredns successfully rolled out
```

Create the Kubernetes`Secret` that Connectivity Link uses to interact with CoreDNS. This secret specifies the zones this provider instance is authoritative for.

```shell-session
$ oc create secret generic coredns-credentials \
  --namespace=kuadrant-system \
  --type=kuadrant.io/coredns \
  --from-literal=ZONES="${KUADRANT_SUBDOMAIN}.${ONPREM_DOMAIN}" \
  --context ${CTX_PRIMARY}
```

Verification

Check the status of the`DNSRecord` CR by running the following commands:

```shell-session
$ oc get dnsrecord <name> -n <namespace> -o jsonpath='{.status.conditions[?(@.type=="Ready")]}'
```

```shell-session
$ NS1=$(oc get svc kuadrant-coredns -n kuadrant-coredns -o jsonpath='{.status.loadBalancer.ingress[0].ip}')
  ROOT_HOST=$(oc get dnsrecord <name> -n <namespace> -o jsonpath='{.spec.rootHost}')
  dig @${NS1} ${ROOT_HOST}
```

Expect the`Ready` condition to be`True`.

Troubleshooting

If you are having undetermined trouble, view the logs for all CoreDNS pods by running the following command:

```shell-session
$ oc logs -n kuadrant-coredns deployment/kuadrant-coredns
```

If the`DNSRecord` is not appearing in the zone, verify that the record has the zone label by running the following command:

```shell-session
$ oc get dnsrecords.kuadrant.io -n dnstest -o jsonpath='{.items[*].metadata.labels}' | grep kuadrant.io/coredns-zone-name
```

The output should include the zone name, for example`kuadrant.io/coredns-zone-name: k.example.com`.

If the output does not show the zone name, check that the DNS Operator is running by using the following command:

```shell-session
$ oc get pods -n dns-operator-system
```

You can also check the DNS Operator logs by running the following command:

```shell-session
$ oc logs -n dns-operator-system deployment/dns-operator-controller-manager
```

A couple of common issues can be missing RBAC and GeoIP database.

- RBAC permissions missing. Check your`ClusterRole` and`ClusterRoleBinding` configurations.
- GeoIP database file not found. Ensure that your database is accessible.

Next steps

- Create`dnsPolicy` custom resources in your OpenShift Container Platform pods, referencing the`coredns-credentials` secret as the provider. Connectivity Link manages DNS records within the delegated`${KUADRANT_SUBDOMAIN}.${ONPREM_DOMAIN}` zone through CoreDNS.

### 2.5. Using CoreDNS with primary and secondary clustersCopy link

You can use CoreDNS as a DNS provider for Connectivity Link in an existing multi-cluster, on-premise environment. This integration allows Connectivity Link to manage DNS entries within your internal network infrastructure.

Prerequisites

- Connectivity Link is installed on two separate OpenShift Container Platform clusters (primary and secondary).
- OpenShift CLI (`oc`) is installed and configured for access to both clusters.
- You have administrator privileges on both OpenShift Container Platform clusters.
- Your OpenShift Container Platform clusters have support for the`loadbalanced` service type that allows UDP and TCP traffic on port 53, such as MetalLB.
- You have access to configure your authoritative on-premise DNS server to delegate a subdomain.
- Podman is installed.
- `jq` is installed.

Procedure

Set up the primary cluster. Set the following environment variables for your primary cluster context:

```shell-session
$ export CTX_PRIMARY=<primary_cluster_context_name> # such as, primary \
  export KUBECONFIG=~/.kube/config # Adjust path if necessary \
  export PRIMARY_CLUSTER_NAME=<primary_cluster_name> # such as, primary \
  export ONPREM_DOMAIN=<onprem-domain> # such as, example.local \
  export KUADRANT_SUBDOMAIN=kuadrant # Subdomain to delegate
```

Replace` ` with the name of the cluster that you are specifying as primary. For the`ONPREM_DOMAIN` variable value, use your actual root domain.

Extract the CoreDNS manifests from the`dns-operator` bundle by running the following commands:

```shell-session
$ podman create --name bundle registry.redhat.io/rhcl-1/dns-operator-bundle:rhcl-1.3.0
```

```shell-session
$ podman cp bundle:/coredns/manifests.yaml ./coredns-manifests.yaml
```

```shell-session
$ podman rm bundle
```

Apply the manifests to the cluster by running the following command:

```shell-session
$ oc apply -f ./coredns-manifests.yaml
```

Wait for the CoreDNS service to get an external IP address. You need the IP address to configure delegation on your authoritative on-premise DNS server. Retrieve and store the IP address by running the following command:

```shell-session
$ export COREDNS_IP_PRIMARY=$(oc --context $CTX_PRIMARY -n kuadrant-system get service kuadrant-coredns -o jsonpath='{.status.loadBalancer.ingress[0].ip}')
echo "CoreDNS Primary IP: ${COREDNS_IP_PRIMARY}"
```

Create a`ConfigMap` to define the authoritative zone for CoreDNS on the primary cluster. This minimal configuration enables the`kuadrant` plugin and GeoIP features.

```shell-session
$ cat | oc --context $CTX_PRIMARY apply -f -
apiVersion: v1
kind: ConfigMap
metadata:
  name: coredns-kuadrant-config
  namespace: kuadrant-coredns
data:
  Corefile: |
    ${KUADRANT_SUBDOMAIN}.${ONPREM_DOMAIN}:53 {
        debug
        errors
        health {
            lameduck 5s
        }
        ready
        log
        geoip <GeoIP-database-name>.mmdb {
            edns-subnet
        }
        metadata
        kuadrant
    }
```

Note

For production or exact GeoIP routing, mount your licensed MaxMind GeoIP database into the CoreDNS pod and update the filename in the`data.Corefile.geoip` parameter.

Update the CoreDNS deployment to use the new configuration:

```shell-session
$ oc --context $CTX_PRIMARY -n kuadrant-system patch deployment kuadrant-coredns --patch '{"spec":{"template":{"spec":{"volumes":[{"name":"config-volume","configMap":{"name":"coredns-kuadrant-config","items":[{"key":"Corefile","path":"Corefile"}]}}]}}}}'
```

Set a watch-and-wait command for the deployment rollout to complete:

```shell-session
$ oc --context $CTX_PRIMARY -n kuadrant-system rollout status deployment/kuadrant-coredns
```

Example output

```plaintext
kuadrant-coredns successfully rolled out
```

Create the Kubernetes`Secret` that Connectivity Link uses to interact with CoreDNS. This secret specifies the zones this provider instance is authoritative for.

```shell-session
$ oc create secret generic coredns-credentials \
  --namespace=kuadrant-system \
  --type=kuadrant.io/coredns \
  --from-literal=ZONES="${KUADRANT_SUBDOMAIN}.${ONPREM_DOMAIN}" \
  --context ${CTX_PRIMARY}
```

On your authoritative on-premise DNS server, configure delegation for the`${KUADRANT_SUBDOMAIN}.${ONPREM_DOMAIN}` subdomain to the external IP addresses of the CoreDNS services running on your primary and secondary clusters,`$COREDNS_IP_PRIMARY` and`$COREDNS_IP_SECONDARY`. The specific steps depend on your DNS server software, for example, BIND, Windows DNS Server. You typically need to add Name Server (NS) records pointing the subdomain to the CoreDNS IP addresses.

Example delegation

```plaintext
; Delegate kuadrant.example.local to CoreDNS instances
$ORIGIN ${KUADRANT_SUBDOMAIN}.${ONPREM_DOMAIN}.
@       IN      SOA     ns1.${ONPREM_DOMAIN}. hostmaster.${ONPREM_DOMAIN}. (
                        2023102601 ; serial
                        7200       ; refresh (2 hours)
                        3600       ; retry (1 hour)
                        1209600    ; expire (2 weeks)
                        3600       ; minimum (1 hour)
                        )
        IN      NS      coredns-primary.${KUADRANT_SUBDOMAIN}.${ONPREM_DOMAIN}.

coredns-primary   IN A ${COREDNS_IP_PRIMARY}
```

Restart CoreDNS by running the following command:

```shell-session
$ oc -n kuadrant-coredns rollout restart deployment kuadrant-coredns
```

Note

After configuring delegation, you can test that the DNS resolution for the delegated subdomain works correctly by querying your authoritative DNS server for a record within the`kuadrant` subdomain. One of the CoreDNS instances is expected to refer to and answer the query.

Verification

Launch a temporary pod for testing by running the following command:

```shell-session
$ oc debug node/<node-name>
```

Replace` ` with the node you are testing on.

Add`transfer` to your Corefile by running the following command:

```shell-session
$ oc patch cm kuadrant-coredns -n kuadrant-coredns --type merge \
   -p "$(oc get cm kuadrant-coredns -n kuadrant-coredns -o jsonpath='{.data.Corefile}' | \
   sed 's/kuadrant/transfer {\n        to *\n    }\n    kuadrant/' | \
   jq -Rs '{data: {Corefile: .}}')"
```

Verify zone delegation by running the following command:

```shell-session
$ dig @${EDGE_NS} -k config/bind9/ddns.key -t AXFR example.com
```

Example output

```shell-session
example.com.            30      IN      SOA     example.com. root.example.com. 17 30 30 30 30
example.com.            30      IN      NS      ns.example.com.
k.example.com.          300     IN      NS      ns1.k.example.com.
ns1.k.example.com.      300     IN      A       172.18.0.16
ns.example.com.         30      IN      A       127.0.0.1
example.com.            30      IN      SOA     example.com. root.example.com. 17 30 30 30 30
```

In this example,`k.example` is the delegated zone and`ns1.k.example` is the primary zone.

Optional. Remove the`transfer` from your Corefile by running the following command:

```shell-session
$ oc patch cm kuadrant-coredns -n kuadrant-coredns --type merge \
   -p "$(oc get cm kuadrant-coredns -n kuadrant-coredns -o jsonpath='{.data.Corefile}' | \
   sed '/transfer {/,/}/d' | \
   jq -Rs '{data: {Corefile: .}}')"
```

Verify the start of authority (SOA) record for the delegated zone by running the following command:

```shell-session
$ dig @${EDGE_NS} soa k.example.com
```

Example output

```shell-session
;; ANSWER SECTION:
k.example.com.          60      IN      SOA     ns1.k.example.com. hostmaster.k.example.com. 12345 7200 1800 86400 60
```

The SOA record is expected to show the primary name server (NS) as confirmation that CoreDNS is responding authoritatively. In this example the primary NS is`ns1.k.example.com`.

Next steps

- Create`DNSPolicy` resources in your OpenShift Container Platform clusters, referencing the`coredns-credentials` secret as the provider. Connectivity Link manages DNS records within the delegated`${KUADRANT_SUBDOMAIN}.${ONPREM_DOMAIN}` zone through CoreDNS.

### 2.6. CoreDNS Corefile configuration referenceCopy link

A Corefile is organized into server blocks that define how DNS queries are handled based on the port and zone. Plugin execution order is determined at build time, not by Corefile order, so you can list plugins in any order. When making configurations by using the DNS Operator, you can check the`ConfigMap` for the resulting server block.

Connectivity Link includes a minimal Corefile that you can update for your uses:

Minimal Corefile

```plaintext
Corefile: |
    . {
        health
        ready
    }
```

For a Corefile with configurations, see the following example:

Example configured Corefile

```plaintext
k.example.com {
    debug
    errors
    log
    health {
        lameduck 5s
    }
    ready
    geoip GeoLite2-City-demo.mmdb {
        edns-subnet
    }
    metadata
    transfer {
        to *
    }
    kuadrant
    prometheus 0.0.0.0:9153
}
```

Zone coordination Each zone in the Corefile must match a zone listed in your CoreDNS provider secret’s`ZONES` field. Required plugins The`geoip` and`metadata` plugins are included by default with the Connectivity Link implementation of the CoreDNS Corefile. Corefile updates

After you update your Corefile, you must always restart your pods for the CoreDNS deployment. You can use the following command:

```shell-session
$ oc rollout restart deployment/coredns -n kuadrant-system
```

You can check the status of the rollout by running the following command:

```shell-session
$ oc rollout status deployment/coredns -n kuadrant-system --watch
```

#### 2.6.1. Default enabled plugins in CoreDNSCopy link

The following plugins are enabled by default in the Connectivity Link CoreDNS plugin. You must ensure CoreDNS compatibility and enable any other plugins that you want to add.

Expand

acl

Enforces access control policies on source IP addresses and prevents unauthorized access to DNS servers.

cache

Enables a front-end cache.

cancel

Cancels a request’s context after 5001 milliseconds.

debug

Disables the automatic recovery when a crash happens so that a stack trace is generated.

errors

Enables error logging.

file

Enables serving zone data from an RFC 1035-style`master` file.

forward

Enables IP forwarding. Facilitates proxying DNS messages to upstream resolvers.

geoip

Lookup`.mmdb`(MaxMind db file format) databases using the client IP, then adds associated`geoip` data to the context request.

header

Modifies the header for queries and responses.

health

Enables a health check endpoint.

hosts

Enables serving zone data from an`/etc/hosts style` file.

kuadrant

Enables serving zone data from kuadrant`DNSRecord` custom resources. Uses logic from the CoreDNS file plugin to create a functioning DNS server.

local

Responds with a basic reply to a local names in the following zones,`localhost`,`0.in-addr.arpa`,`127.in-addr.arpa`,`255.in-addr.arpa` and any query asking for`localhost. `.

log

Enables query logging to standard output. Logs are structured for aggregation by cluster logging solutions.

loop

Detects simple forwarding loops and halts the server.

metadata

Enables a metadata collector.

minimal

Minimizes size of the DNS response message whenever possible.

nsid

Adds an identifier of this server to each reply.

prometheus

Enables Prometheus metrics. The default listens on`localhost:9153`. The metrics path is to`/metrics`.

ready

Enables a readiness check HTTP endpoint.

reload

Allows automatic reload of a changed Corefile.

rewrite

Rewrites queries for automatic port forwarding.

root

Simply specifies the root of where to find files.

secondary

Enables serving a zone retrieved from a primary server.

timeouts

Means that you can configure the server read, write and idle timeouts for the TCP, TLS, DoH and DoQ (idle only) servers.

tls

Means that you can configure the server certificates for the TLS, gRPC, and DoH servers.

transfer

Perform (outgoing) zone transfers for other plugins.

view

Defines the conditions that must be met for a DNS request to be routed to the server block.

whoami

Returns your resolver’s local IP address, port and transport.

| | Plugin | Function |
| --- | --- | --- |

Show more

Tip

When using CoreDNS, if you do not need to keep all logs, you can set up the`logs` directive to only report errors and use the`prometheus` plugin to gather primary metrics instead. Prometheus metrics give you trends, for example, how many queries failed, without storing every single piece of traffic.

### 2.7. Troubleshooting CoreDNS with the kuadrant pluginCopy link

You can troubleshoot your CoreDNS deployment by restarting CoreDNS and by checking the logs. Use the following commands as needed to investigate your specific errors:

Restart CoreDNS by using the following command:

```shell-session
$ oc -n kuadrant-coredns rollout restart deployment kuadrant-coredns
```

You can view CoreDNS logs by running the following command:

```shell-session
$ oc logs -f deployments/kuadrant-coredns -n kuadrant-coredns
```

You can get recent logs by running the following command:

```shell-session
$ oc logs --tail=100 deployments/kuadrant-coredns -n kuadrant-coredns
```

### 2.8. CoreDNS removal or migrationCopy link

You can remove your CoreDNS integration by deleting the CoreDNS deployment and deleting your DNS policies. To migrate to a different provider, delete existing`dnsPolicy` CRs and re-create them with the new provider secret reference. No data is permanently locked into CoreDNS.

## Legal NoticeCopy link

Copyright © Red Hat.

Except as otherwise noted below, the text of and illustrations in this documentation are licensed by Red Hat under the Creative Commons Attribution–Share Alike 3.0 Unported license . If you distribute this document or an adaptation of it, you must provide the URL for the original version.

Red Hat, as the licensor of this document, waives the right to enforce, and agrees not to assert, Section 4d of CC-BY-SA to the fullest extent permitted by applicable law.

Red Hat, the Red Hat logo, JBoss, Hibernate, and RHCE are trademarks or registered trademarks of Red Hat, LLC. or its subsidiaries in the United States and other countries.

Linux® is the registered trademark of Linus Torvalds in the United States and other countries.

XFS is a trademark or registered trademark of Hewlett Packard Enterprise Development LP or its subsidiaries in the United States and other countries.

The OpenStack® Word Mark and OpenStack logo are trademarks or registered trademarks of the Linux Foundation, used under license.

All other trademarks are the property of their respective owners.

 

 

[Github](https://github.com/redhat-documentation) [Youtube](https://www.youtube.com/@redhat) [Twitter](https://twitter.com/RedHat)

### Learn

- [Developer resources](https://developers.redhat.com/learn)
- [Cloud learning hub](https://docs.redhat.com/learn/learning-paths)
- [Interactive labs](https://www.redhat.com/en/interactive-labs)
- [Training and certification](https://www.redhat.com/services/training-and-certification)
- [Customer support](https://access.redhat.com/support)
- [See all documentation](https://docs.redhat.com/en/products)

### Try, buy, & sell

- [Product trial center](https://redhat.com/en/products/trials)
- [Red Hat Ecosystem Catalog](https://catalog.redhat.com/)
- [Red Hat Store](https://www.redhat.com/en/store)
- [Buy online (Japan)](https://www.redhat.com/about/japan-buy)

### Communities

- [Customer Portal Community](https://access.redhat.com/community)
- [Events](https://www.redhat.com/events)
- [How we contribute](https://www.redhat.com/about/our-community-contributions)

### About Red Hat Documentation

We help Red Hat users innovate and achieve their goals with our products and services with content they can trust. [Explore our recent updates](https://www.redhat.com/en/blog/whats-new-docsredhatcom).

### Making open source more inclusive

Red Hat is committed to replacing problematic language in our code, documentation, and web properties. For more details, see the [Red Hat Blog](https://www.redhat.com/en/blog/making-open-source-more-inclusive-eradicating-problematic-language).

### About Red Hat

We deliver hardened solutions that make it easier for enterprises to work across platforms and environments, from the core datacenter to the network edge.

### Theme

 

- [About Red Hat](https://redhat.com/en/about/company)
- [Jobs](https://redhat.com/en/jobs)
- [Events](https://redhat.com/en/events)
- [Locations](https://redhat.com/en/about/office-locations)
- [Contact Red Hat](https://redhat.com/en/contact)
- [Red Hat Blog](https://redhat.com/en/blog)
- [Inclusion at Red Hat](https://redhat.com/en/about/our-culture/diversity-equity-inclusion)
- [Cool Stuff Store](https://coolstuff.redhat.com/)
- [Red Hat Summit](https://www.redhat.com/en/summit)

© 2026 Red Hat

- [Privacy statement](https://redhat.com/en/about/privacy-policy)
- [Terms of use](https://redhat.com/en/about/terms-use)
- [All policies and guidelines](https://redhat.com/en/about/all-policies-guidelines)
- [Digital accessibility](https://redhat.com/en/about/digital-accessibility)

Back to top

 



---

Observability | Red Hat Connectivity Link | 1.3 | Red Hat Documentation

 

 

 

 

 

 

 

 

# Observability

---

Red Hat Connectivity Link 1.3

## Observe and monitor Gateways, APIs, and applications on OpenShift

Red Hat OpenShift Documentation Team

Legal Notice

Abstract

This guide describes how to set up the Connectivity Link observability features in single-cluster and multicluster environments.

---

## Chapter 1. Connectivity Link observabilityCopy link

You can use the Connectivity Link observability features to observe and monitor your gateways, applications, and APIs on OpenShift Container Platform.

### 1.1. Connectivity Link observability featuresCopy link

Connectivity Link uses metrics exposed by Connectivity Link components, Gateway API state metrics, and standard metrics exposed by Envoy to build a set template dashboards and alerts. Envoy is part of OpenShift Service Mesh. In this case, it runs as a gateway deployment.

You can download Kuadrant community-based templates to integrate with Grafana, Prometheus, and Alertmanager deployments, or use those templates as starting points that you can change for your specific needs. Use the secure images available in the Red Hat Catalog at: [Red Hat Connectivity Link](https://catalog.redhat.com/en/software/containers/rhcl-1/rhcl-rhel9-operator/672a14ffb6ba8300a48c3799#get-this-image).

Connectivity Link includes the following observability features:

- Legal Notice
- Metrics: Prometheus metrics for monitoring gateway and policy performance
- Tracing: Distributed tracing with Red Hat build of OpenTelemetry support for request flows
- Access Logs: Envoy access logs with request correlation and structured logging
- Dashboards: Pre-built Grafana dashboards for visualization

### 1.2. Configure your observability monitoring stackCopy link

You can prepare your monitoring stack to give yourself insight into your gateways, applications, and APIs by setting up dashboards and alerts on your OpenShift Container Platform cluster. You must configure your stack on each OpenShift Container Platform cluster that you want to use Connectivity Link on.

The example dashboards and alerts for observing Connectivity Link functionality use low-level CPU and network metrics from the user monitoring stack in OpenShift Container Platform and resource-state metrics from Gateway API and Connectivity Link resources. The user monitoring stack in OpenShift Container Platform is based on the [Prometheus](https://prometheus.io/) open source project.

Important

The following procedure is an example only and is not intended for production use.

Prerequisites

- You installed Connectivity Link.
- You set up metrics, such as`prometheus`.
- You installed and configured Grafana on your OpenShift Container Platform cluster.
- You cloned the [Kuadrant Operator GitHub repository](https://github.com/Kuadrant/kuadrant-operator).

Procedure

Verify that the user workload monitoring is configured correctly in your OpenShift Container Platform cluster as follows:

```shell-session
$ oc get configmap cluster-monitoring-config -n openshift-monitoring -o jsonpath='{.data.config\.yaml}'|grep enableUserWorkload
```

The expected output is`enableUserWorkload: true`.

Install the Connectivity Link, Gateway, and Grafana component metrics and configuration as follows:

```shell-session
$ oc apply -k https://github.com/Kuadrant/kuadrant-operator/config/install/configure/observability?ref=v1.2.0
```

From the root directory of your Kuadrant Operator repository, configure the OpenShift Container Platform`thanos-query` instance as a data source in Grafana as follows:

```shell-session
TOKEN="Bearer $(oc whoami -t)"
HOST="$(oc -n openshift-monitoring get route thanos-querier -o jsonpath='https://{.status.ingress[].host}')"
echo "TOKEN=$TOKEN" > config/observability/openshift/grafana/datasource.env
echo "HOST=$HOST" >> config/observability/openshift/grafana/datasource.env
oc apply -k config/observability/openshift/grafana
```

Configure the example Grafana dashboards as follows:

```shell-session
$ oc apply -k https://github.com/Kuadrant/kuadrant-operator/examples/dashboards?ref=v1.3.0
```

### 1.3. Enabling observability monitoring in Connectivity LinkCopy link

By enabling observability monitoring, you can view context, historical trends, and alerts based on the metrics you configured. After you have configured your monitoring stack, use this step to expose metrics endpoints, deploy monitoring resources, and configure the Envoy gateway.

When you enable observability monitoring, the following events occur:

- Connectivity Link creates`ServiceMonitor` and`PodMonitor` custom resource definitions (CRDs) for its components in the namespace where Connectivity Link is.
- A single set of monitors is created in each gateway namespace to scrape metrics from any gateways.
- Monitors also scrape metrics from the corresponding gateway system namespace, generally the`istio-system` namespace.

You can delete and re-create monitors as required. Monitors are only ever created or deleted, and not updated or reverted. The following procedure is optional. You can create your own`ServiceMonitor` or`PodMonitor` definitions, or configure`prometheus` metrics directly.

Important

To use Connectivity Link observability dashboards, you must enable observability on each OpenShift Container Platform cluster that Connectivity Link runs on.

Prerequisites

- You installed Connectivity Link.
- You have administrator access to your OpenShift Container Platform cluster.
- You configured observability metrics.

Procedure

To enable default observability for Connectivity Link and any gateways, set`spec.observability.enable` parameter value to`true` in your`Kuadrant` custom resource (CR):

Example`Kuadrant` CR

```yaml
apiVersion: kuadrant.io/v1beta1
kind: Kuadrant
metadata:
  name: kuadrant-sample
spec:
  observability:
    enable: true
# ...
```

You can also set the`spec.observability.enable` to`false` and create your own`ServiceMonitor` or`PodMonitor` definitions, or configure Prometheus directly.

Verification

Check the created monitors by running the following command:

```shell-session
$ oc get servicemonitor,podmonitor -A -l kuadrant.io/observability=true
```

### 1.4. About configuring observability dashboards and alertsCopy link

Connectivity Link includes starting points for monitoring your Connectivity Link deployment with ready-to-use example dashboards and alerts. You can customize these dashboards and alerts to fit your environment.

Dashboards are organized with different metrics for different use cases.

#### 1.4.1. Platform engineer Grafana dashboardCopy link

The platform engineer dashboard displays the following details:

- Policy compliance and governance.
- Resource consumption
- Error rates
- Request latency and throughput
- Multi-window, multi-burn alert templates for API error rates and latency
- Multicluster split

#### 1.4.2. Application developer Grafana dashboardCopy link

The application developer dashboard is less focused on policies than the platform engineer dashboard and is more focused on APIs and applications. For example:

- Request latency and throughput per API
- Total requests and error rates by API path

#### 1.4.3. Business user Grafana dashboardCopy link

The business user dashboard includes the following details:

- Requests per second per API
- Increase or decrease in rates of API usage over specified times

#### 1.4.4. Grafana dashboards available to importCopy link

The Connectivity Link example dashboards are uploaded to the Grafana dashboards website. You can import the following dashboards into your Grafana deployment on OpenShift Container Platform:

Expand

[App Developer dashboard](https://grafana.com/grafana/dashboards/21538-app-developer-dashboard/)

21538

[Platform Engineer dashboard](https://grafana.com/grafana/dashboards/20982-platform-engineer-dashboard/)

20982

[Business User dashboard](https://grafana.com/grafana/dashboards/20981-business-user-dashboard/)

20981

[DNS Operator dashboard](https://grafana.com/grafana/dashboards/22695-dns-operator-overview/)

22695

| Table 1.1. Connectivity Link example dashboards in Grafana | Name | Dashboard ID |
| --- | --- | --- |

Show more

#### 1.4.5. Importing dashboards in GrafanaCopy link

As an infrastructure engineer, you can manually select and important dashboards to Grafana to conduct rapid prototyping or emergency troubleshooting, test community dashboards, or perfect a dashboard that you intend to automate for another team.

Important

You must perform these steps on each OpenShift Container Platform cluster that you want to use Connectivity Link on.

Prerequisites

- You configured your monitoring stack and other observability resources as needed.
- You installed Connectivity Link.
- You have administrator access to a running OpenShift Container Platform cluster.

Procedure

Click Dashboards > New > Import, and use one of the following options:

Enter JSON content directly.

For more information, see the [Grafana documentation on how to import dashboards](https://grafana.com/docs/grafana/latest/dashboards/build-dashboards/import-dashboards/).

- Upload a dashboard JSON file.
- Enter a dashboard ID obtained from the [Grafana dashboards website](https://grafana.com/grafana/dashboards/).

#### 1.4.6. About importing dashboards automatically in OpenShift Container PlatformCopy link

As an infrastructure engineer, automating the import of observability dashboards can give you more consistency, version control, and operational velocity. Automation gives you the benefits of monitoring-as-code, and helps keep Operators updated, clusters identical, and supports multi-tenancy.

You can use a`GrafanaDashboard` resource to reference a`ConfigMap`.

Data sources are configured as template variables, automatically integrating with your existing data sources. The metrics for these dashboards are sourced from Prometheus.

Important

For some example dashboard panels to work correctly, HTTPRoutes in Connectivity Link must include a`service` and`deployment` label with a value that matches the name of the service and deployment being routed to. For example,`service=my-app` and`deployment=my-app`. This allows low-level Istio and Envoy metrics to be joined with Gateway API state metrics.

- If you do not want to use the GUI, you can automate dashboard provisioning in Grafana by adding JSON files to a`ConfigMap` object that you must mount at`/etc/grafana/provisioning/dashboards`.

#### 1.4.7. About configuring Prometheus alertsCopy link

As an infrastructure engineer, you can configure Prometheus alerts in OpenShift Container Platform is a proactive way to tune alerts so that you can ensure platform stability. For example, you can set alert triggers for automated incident detection, usage, and cluster health.

- You can integrate the Connectivity Link example alerts into Prometheus as`PrometheusRule` resources, and then adjust the alert thresholds to suit your specific operational needs.
- For details on how to configure Prometheus alerts, see [Configuring alerts and notifications for user workload monitoring](https://docs.redhat.com/en/documentation/monitoring_stack_for_red_hat_openshift/4.21/html/configuring_user_workload_monitoring/configuring-alerts-and-notifications-uwm).
- Service Level Objective (SLO) alerts generated by using the Sloth GitHub project are also included. You can use these alerts to integrate with the [SLO Grafana dashboard](https://grafana.com/grafana/dashboards/14348-slo-detail/), which uses generated labels to comprehensively overview your SLOs.

### 1.5. Tracing in Connectivity LinkCopy link

Connectivity Link supports tracing at both the control plane and data-plane levels. Connectivity Link exports control-plane traces to your OpenTelemetry Collector so that you can observe reconciliation loops and internal operations. This is useful for debugging controller behavior, understanding operator performance, and tracking policy lifecycle events.

Data-plane tracing traces actual user requests through the gateway and policy enforcement components. You can see request flows through Istio, Authorino, Limitador, and the`wasm-shim` module. Data-plane tracing is useful for debugging request-level issues and policy enforcement.

To use tracing, you must configure both types of tracing. You must configure the`kuadrant` custom resource (CR) for the data plane. For control plane tracing, you must configure each operating component separately, such as the`kuadrant-operator`,`authorino-operator`, and`limitador-operator` deployments. This configuration sends traces to the same collector, providing a complete view of your Connectivity Link system from policy reconciliation to request processing.

#### 1.5.1. Correlating control plane and data plane tracesCopy link

Even though control plane and data plane traces are separate, you can correlate them. For example, create a`RateLimitPolicy` to understand how traces work together to show all events.

Create a RateLimitPolicy at`15:30:00`, then view the control plane trace to see the following events:

- Policy reconciliation completed at`15:30:05`.
- Limitador configuration updated.
- `wasm-shim` configuration updated.

Next, send a test request at`15:30:10`, then view data plane trace to see the following events:

- Request processed through the`wasm-shim` module.
- Rate limit check sent to Limitador.
- Response returned.

You can use a similar pattern of action for any events that you want to correlate manually. This type of correlation is useful in development environments.

#### 1.5.2. Control-plane tracing environment variablesCopy link

You can enable control tracing in Connectivity Link by setting OpenTelemetry environment variables in the deployment. The method for setting the variables depends on your deployment approach, for example, whether you used the Operator Lifecycle Manager (OLM) or YAML manifests.

Control plane traces appear under the service name`kuadrant-operator` in the Grafana dashboard.

Expand

`OTEL_EXPORTER_OTLP_ENDPOINT`

OTLP collector endpoint URL, for example,`rpc://tempo.tempo.svc.cluster.local:4317`. The following supported endpoint URL schemes are:

- `rpc://host:port`→ gRPC OTLP
- Insecure: [http://host:port](http://host:port)→ HTTP OTLP
- Secure: [https://host:port](https://host:port)→ HTTP OTLP

Tracing disabled

`OTEL_EXPORTER_OTLP_TRACES_ENDPOINT`

Override endpoint specifically for traces

Uses`OTEL_EXPORTER_OTLP_ENDPOINT`

`OTEL_EXPORTER_OTLP_INSECURE`

Use insecure connection to collector; set to`false` when used in production with TLS

`true`

`OTEL_SERVICE_NAME`

Service name for traces

`kuadrant-operator`

`OTEL_SERVICE_VERSION`

Service version for telemetry data

Empty

| Table 1.2. Available OpenTelemetry environment variables | Variable | Description | Default |
| --- | --- | --- | --- |

Show more

#### 1.5.3. Configuring data plane tracing in Connectivity LinkCopy link

Enable data plane tracing in OpenShift Service Mesh with the`kuadrant` CR. You must perform these steps on each OpenShift Container Platform cluster that you want to use Connectivity Link on.

Prerequisites

- You installed Connectivity Link.
- You are logged into a running OpenShift Container Platform cluster as an administrator.
- You have Red Hat OpenShift Distributed Tracing Platform installed and configured to support OpenTelemetry.
- You installed a compatible version of OpenShift Service Mesh.

Procedure

Enable tracing in OpenShift Service Mesh by configuring your`Telemetry` custom resource (CR) as follows:

Example OpenShift Service Mesh Telemetry CR with tracing

```yaml
apiVersion: telemetry.istio.io/v1alpha1
kind: Telemetry
metadata:
  name: mesh-default
  namespace: gateway-system
spec:
  tracing:
  - providers:
    - name: tempo-otlp
    randomSamplingPercentage: 100
# ...
```

Apply the configuration by running the following command:

```shell-session
$ oc apply -f mesh-default.yaml
```

Configure a tracing extension provider for OpenShift Service Mesh in your`Istio` CR by adding a list value to the`spec.values.meshConfig.extensionProviders` parameter. Ensure that you also add the`otel` port and service information:

Example Istio CR with tracing extension provider

```yaml
apiVersion: operator.istio.io/v1alpha1
kind: Istio
metadata:
  name: default
spec:
  namespace: gateway-system
  values:
    meshConfig:
      defaultConfig:
        tracing: {}
      enableTracing: true
      extensionProviders:
      - name: tempo-otlp
        opentelemetry:
          port: 4317
          service: tempo.tempo.svc.cluster.local
# ...
```

Important

If you are setting the controller manually, you must set the OpenTelemetry Collector protocol in the`Service` CR port`name` and`appProtocol` fields. For example, when using gRPC, the port name should begin with`grpc-` or the`appProtocol` should be`grpc`:

```yaml
kind: Service
apiVersion: v1
metadata:
  name: otel-collector
spec:
  ports:
    - name: otlp-grpc
      port: 4317
      targetPort: 4317
      appProtocol: grpc
  selector:
    app: otel-collector
# ...
```

Apply the configuration by running the following command:

```shell-session
$ oc apply -f istio.yaml
```

Optional. If you want to collect`Authorino` and`Limitador` traces in a different location than your`Kaudrant` traces, complete the following steps:

Enable request tracing in your`Authorino` custom resource (CR) and send authentication and authorization traces to the central collector as follows:

Example Authorino CR with request tracing

```yaml
apiVersion: operator.authorino.kuadrant.io/v1beta1
kind: Authorino
metadata:
  name: authorino
spec:
  tracing:
    endpoint: rpc://authorino-collector:4317
    insecure: true
# ...
```

Set`insecure` to`true` to skip TLS certificate verification in development environments. Set to`false` for production environments.

Apply the configuration by running the following command:

```shell-session
$ oc apply -f authorino.yaml
```

Enable request tracing in your`Limitador` CR and send rate limit traces to the central collector as follows:

Example Limitador CR with request tracing

```yaml
apiVersion: limitador.kuadrant.io/v1alpha1
kind: Limitador
metadata:
  name: limitador
spec:
  tracing:
    endpoint: rpc://limitador-collector:4317
    insecure: true
# ...
```

Set`insecure` to`true` to skip TLS certificate verification in development environments. Set to`false` for production environments.

Apply the configuration by running the following command:

```shell-session
$ oc apply -f limitador.yaml
```

Important

Trace IDs do not propagate to WebAssembly modules in OpenShift Service Mesh. This means that requests passed to Limitador do not have the relevant parent trace ID. However, if the trace initiation point is outside OpenShift Service Mesh, the parent trace ID is available to Limitador and included in traces. This impacts correlating traces from Limitador with traces from Authorino, the gateway, and any other components in the request path.

Configure data-plane tracing in the`Kuadrant` CR by providing the collector endpoint as shown in the following example:

Example`kuadrant` CR

```yaml
apiVersion: kuadrant.io/v1beta1
kind: Kuadrant
metadata:
  name: kuadrant
  namespace: kuadrant-system
spec:
  observability:
    dataPlane:
      defaultLevels:
        - debug: "true" # Enable DEBUG level trace filtering
      httpHeaderIdentifier: x-request-id
    tracing:
      defaultEndpoint: rpc://tempo.tempo.svc.cluster.local:4317
      insecure: true
```

where:

`spec.observability.tracing.defaultEndpoint`:: The URL of the tracing collector backend. That is, the OpenTelemetry endpoint. The following are supported protocols:

- `rpc://` for gRPC OTLP, port 4317
- `http://` for HTTP OTLP, port 4318

``spec.observability.tracing.insecure`:: Set to`true` to skip TLS certificate verification in development environments. Set to`false` for production environments.

Important

Point to the collector service, such as the Distributed Tracing Platform, not the query service. The collector receives traces from your applications. The query service is only for viewing traces in the GUI.

Apply the configuration by running the following command:

```shell-session
$ oc apply -f kuadrant.yaml
```

Verification

Verify that the CR applied successfully by listing the objects of that`Kind` by running the following command:

```shell-session
$ oc get kuadrant
```

#### 1.5.4. Troubleshooting by using traces and logsCopy link

You can use a tracing user interface such Grafana to search for OpenShift Service Mesh and Connectivity Link trace information by trace ID. You can get the trace ID from logs, or from a header in a sample request that you want to troubleshoot. You can also search for recent traces, filtering by the service that you want to focus on.

If you centrally aggregate logs by using tools such as Grafana Loki and Promtail, you can jump between trace information and the relevant logs for that service.

By using a combination of tracing and logs, you can visualize and troubleshoot request timing issues and narrow down to specific services. This method gives you even more insight a more complete picture of your user traffic when you combine it with Connectivity Link metrics and dashboards.

#### 1.5.5. Viewing rate-limit logging with trace IDsCopy link

You can enable request logging with trace IDs to get more information about requests when you use the Limitador component of Connectivity Link for rate limiting. To do this, you must increase the log level.

Prerequisites

- You installed Connectivity Link.
- You have administrator access to a running OpenShift Container Platform cluster.
- You configured Grafana dashboards.
- You have Red Hat OpenShift Distributed Tracing Platform installed and configured to support OpenTelemetry.

Procedure

Set the verbosity to`3` or higher in your`Limitador` custom resource (CR) as follows:

Example Limitador CR

```yaml
apiVersion: limitador.kuadrant.io/v1alpha1
kind: Limitador
metadata:
  name: limitador
spec:
  verbosity: 3
```

Example log entry with the`traceparent` field holding the trace ID

```shell-session
"Request received: Request { metadata: MetadataMap { headers: {"te": "trailers", "grpc-timeout": "5000m", "content-type": "application/grpc", "traceparent": "00-4a2a933a23df267aed612f4694b32141-00f067aa0ba902b7-01", "x-envoy-internal": "true", "x-envoy-expected-rq-timeout-ms": "5000"} }, message: RateLimitRequest { domain: "default/toystore", descriptors: [RateLimitDescriptor { entries: [Entry { key: "limit.general_user__f5646550", value: "1" }, Entry { key: "metadata.filter_metadata.envoy\\.filters\\.http\\.ext_authz.identity.userid", value: "alice" }], limit: None }], hits_addend: 1 }, extensions: Extensions }"
```

### 1.6. Configuring access logsCopy link

You can configure Envoy access logs in OpenShift Service Mesh so that you can to track a single request across multiple services and components by using a unique identifier.

Prerequisites

- You installed Connectivity Link.
- You have a running OpenShift Container Platform cluster.
- You have administrator access to the OpenShift Container Platform cluster.

Procedure

Enable mesh-wide, default-format access logs by using the Istio Telemetry API. Use the following example as a starting point:

Example Telemetry API config

```yaml
apiVersion: telemetry.istio.io/v1alpha1
kind: Telemetry
metadata:
  name: access-logs
  namespace: gateway-system
spec:
  accessLogging:
    - providers:
      - name: envoy
```

You might also use the`istio-system` as your namespace, depending on your setup.

For better parsing and integration with log aggregation systems, enable JSON-formatted access logs. Only log errors as shown in the following example:

Example JSON config

```plaintext
apiVersion: telemetry.istio.io/v1alpha1
kind: Telemetry
metadata:
  name: access-logs-json
  namespace: istio-system
spec:
  accessLogging:
    - providers:
      - name: envoy
    filter:
      expression: "response.code >= 400"
```

To enable logging for a specific workload and add filtering, use the following example:

Example JSON workload config

```plaintext
apiVersion: telemetry.istio.io/v1
kind: Telemetry
metadata:
  name: selective-logging
  namespace: my-app-ns
spec:
  selector:
    matchLabels:
      app: productpage
  accessLogging:
    - providers:
        - name: access-logs-json
      filter:
        expression: "response.code >= 400"
```

Tip

The`expression` field uses Common Expression Language (CEL). You can use CEL-based filters to avoid excessive and meaningless logs.

If you are using the Sail Operator, check which`Istio` Operator is active in your cluster by running the following command:

```shell-session
$ oc get istio -A
```

The expected output is a list of your mesh deployments, such as`default`,`prod-mesh` and their current status.

Configure the Istio mesh with a custom access log provider to enable JSON encoding:

```yaml
apiVersion: sailoperator.io/v1
kind: Istio
metadata:
  name: default
spec:
  namespace: istio-system
  values:
    meshConfig:
      accessLogFile: /dev/stdout
      accessLogEncoding: JSON
      accessLogFormat: |
        {
          "start_time": "%START_TIME%",
          "method": "%REQ(:METHOD)%",
          "path": "%REQ(X-ENVOY-ORIGINAL-PATH?:PATH)%",
          "protocol": "%PROTOCOL%",
          "response_code": "%RESPONSE_CODE%",
          "response_flags": "%RESPONSE_FLAGS%",
          "bytes_received": "%BYTES_RECEIVED%",
          "bytes_sent": "%BYTES_SENT%",
          "duration": "%DURATION%",
          "upstream_service_time": "%RESP(X-ENVOY-UPSTREAM-SERVICE-TIME)%",
          "x_forwarded_for": "%REQ(X-FORWARDED-FOR)%",
          "user_agent": "%REQ(USER-AGENT)%",
          "request_id": "%REQ(X-REQUEST-ID)%",
          "authority": "%REQ(:AUTHORITY)%",
          "upstream_host": "%UPSTREAM_HOST%",
          "upstream_cluster": "%UPSTREAM_CLUSTER%",
          "route_name": "%ROUTE_NAME%"
        }
```

Next steps

- Filter your access logs to focus on the errors you need to see.
- Enable request, log, and tracing correlation.

#### 1.6.1. Filtering access logsCopy link

You can filter your access logs to reduce extra messages and focus on the issues and errors that are relevant to your use case.

Prerequisites

- You installed Connectivity Link.
- You have a running OpenShift Container Platform cluster.
- You have administrator access to the OpenShift Container Platform cluster.
- You enabled access logs.

Procedure

Configure your`Telemetry` custom resource (CR) to only log errors by using the following example:

```yaml
apiVersion: telemetry.istio.io/v1alpha1
kind: Telemetry
metadata:
  name: access-logs-errors-only
  namespace: istio-system
spec:
  accessLogging:
    - providers:
      - name: envoy
      filter:
        expression: "response.code >= 400"
```

Configure your`Telemetry` custom resource (CR) to only log specific routes by using the following example:

```yaml
apiVersion: telemetry.istio.io/v1alpha1
kind: Telemetry
metadata:
  name: access-logs-api-only
  namespace: istio-system
spec:
  accessLogging:
    - providers:
      - name: envoy
      filter:
        expression: 'request.url_path.startsWith("/api/")'
```

Configure your`Telemetry` custom resource (CR) to exclude health checks by using the following example:

```yaml
apiVersion: telemetry.istio.io/v1alpha1
kind: Telemetry
metadata:
  name: access-logs-no-healthz
  namespace: istio-system
spec:
  accessLogging:
    - providers:
      - name: envoy
      filter:
        expression: '!request.url_path.startsWith("/healthz")'
```

#### 1.6.2. Common access log format variablesCopy link

You can quickly set up Envoy logs by using the most common format variables so that you get exactly the data you want.

Example configuration snippet

```yaml
envoyFileAccessLog:
  path: /dev/stdout
  logFormat:
    text: "[%START_TIME%] %REQ(X-REQUEST-ID)% %RESP(HEADER)% %RESPONSE_FLAGS%\n"
```

Expand

`%START_TIME%`

Request start time

`%REQ(HEADER)%`

Request header value, such as`%REQ(X-REQUEST-ID)%`

`%RESP(HEADER)%`

Response header value

`%PROTOCOL%`

Protocol, such as HTTP/1.1, HTTP/2

`%RESPONSE_CODE%`

HTTP response code

`%RESPONSE_FLAGS%`

Response flags indicating issues, such as UH, UF

`%BYTES_RECEIVED%`

Bytes received from client

`%BYTES_SENT%`

Bytes sent to client

`%DURATION%`

Total request duration in milliseconds

`%UPSTREAM_HOST%`

Upstream host address

`%UPSTREAM_CLUSTER%`

Upstream cluster name

`%ROUTE_NAME%`

Route name that matched

| Table 1.3. Common Envoy access log format variables | Variable | Description |
| --- | --- | --- |

Show more

### 1.7. About using access logs for request correlationCopy link

Access logs give you detailed information about each request processed by the gateway, including timing, response codes, and request identifiers. For example, you can correlate requests across gateways, Authorino, Limitador, and backend services.

You can correlate request information with traces and application logs for a variety of uses. Request correlation uses`x-request-id` headers. These headers are automatically generated by Envoy for each incoming request. For example:

- Access logs show the`x-request-id`.
- Traces include the`x-request-id` as a span attribute.
- Use a dashboard to jump from logs to traces and vice versa.

The following fields are the most important access-log fields for request correlation:

- `request_id`(`%REQ(X-REQUEST-ID)%`): The unique request identifier generated by Envoy.
- `start_time`** (`%START_TIME%`): The request start time for time-based correlation.
- `route_name`** (`%ROUTE_NAME%`): The route that matched the request, which is useful for policy debugging.

#### 1.7.1. Setting up access log and tracing correlationCopy link

You can use access logs and tracing together to correlate requests. When you correlate request IDs, you can search for an ID once and see the entire journey from the initial access through to an event that you are investigating.

You can see the exact timing of a request as it entered and left each service. If you have configured user or organization-based IDs, you can also determine who a problem is effecting so that you can prioritize your response.

The following configuration example tells WASM filters to log the`x-request-id` header value and enables request correlation across Envoy, Authorino, Limitador, and WASM logs.

Prerequisites

- You installed Connectivity Link.
- You have a running OpenShift Container Platform cluster.
- You have administrator access to the OpenShift Container Platform cluster.
- You enabled access logs and tracing.

Procedure

To enable request correlation across Connectivity Link components, configure the`httpHeaderIdentifier` in the Kuadrant CR:

```yaml
apiVersion: kuadrant.io/v1beta1
kind: Kuadrant
metadata:
  name: kuadrant
  namespace: kuadrant-system
spec:
  observability:
    dataPlane:
      httpHeaderIdentifier: x-request-id
    tracing:
      defaultEndpoint: rpc://tempo.tempo.svc.cluster.local:4317
      insecure: true
```

You can correlate logs across all components using the`x-request-id`, by using the following examples:

View the following Envoy access log entry:

```plaintext
{
  "start_time": "2026-01-23T15:45:12.345Z",
  "method": "GET",
  "path": "/api/users",
  "response_code": 200,
  "request_id": "a1b2c3d4-e5f6-7890-abcd-ef1234567890",
  "route_name": "toystore-route"
}
```

Correlate the following Authorino log entry with the Envoy access log:

```plaintext
{"level":"info","ts":"2026-01-23T15:45:12.350Z","request_id":"a1b2c3d4-e5f6-7890-abcd-ef1234567890","msg":"auth check succeeded","identity":"alice"}
```

Correlate the following Limitador log entry with the Envoy and Authorino logs:

```plaintext
Request received: ... "x-request-id": "a1b2c3d4-e5f6-7890-abcd-ef1234567890" ...
```

When you combine the three logs, the story of this`request_id` is:

- At`15:45:12`, a user named`alice` requested the users' API,`/api/users`. You can also see the`request_id` of`a1b2c3d4-e5f6-7890-abcd-ef1234567890`.
- The request hit the`toystore-route` in Envoy.
- Envoy paused the request and checked authentication with Authorino, see`level` and`info`.
- Authorino verified Alice’s identity:`auth check succeeded`,`identity`,`alice`.
- Simultaneously, Limitador noted the request to ensure that Alice did not exceed her allowed limit.
- Finally, Envoy allowed the traffic through, resulting in a`200` response code.

## Legal NoticeCopy link

Copyright © Red Hat.

Except as otherwise noted below, the text of and illustrations in this documentation are licensed by Red Hat under the Creative Commons Attribution–Share Alike 3.0 Unported license . If you distribute this document or an adaptation of it, you must provide the URL for the original version.

Red Hat, as the licensor of this document, waives the right to enforce, and agrees not to assert, Section 4d of CC-BY-SA to the fullest extent permitted by applicable law.

Red Hat, the Red Hat logo, JBoss, Hibernate, and RHCE are trademarks or registered trademarks of Red Hat, LLC. or its subsidiaries in the United States and other countries.

Linux® is the registered trademark of Linus Torvalds in the United States and other countries.

XFS is a trademark or registered trademark of Hewlett Packard Enterprise Development LP or its subsidiaries in the United States and other countries.

The OpenStack® Word Mark and OpenStack logo are trademarks or registered trademarks of the Linux Foundation, used under license.

All other trademarks are the property of their respective owners.

 

 

[Github](https://github.com/redhat-documentation) [Youtube](https://www.youtube.com/@redhat) [Twitter](https://twitter.com/RedHat)

### Learn

- [Developer resources](https://developers.redhat.com/learn)
- [Cloud learning hub](https://docs.redhat.com/learn/learning-paths)
- [Interactive labs](https://www.redhat.com/en/interactive-labs)
- [Training and certification](https://www.redhat.com/services/training-and-certification)
- [Customer support](https://access.redhat.com/support)
- [See all documentation](https://docs.redhat.com/en/products)

### Try, buy, & sell

- [Product trial center](https://redhat.com/en/products/trials)
- [Red Hat Ecosystem Catalog](https://catalog.redhat.com/)
- [Red Hat Store](https://www.redhat.com/en/store)
- [Buy online (Japan)](https://www.redhat.com/about/japan-buy)

### Communities

- [Customer Portal Community](https://access.redhat.com/community)
- [Events](https://www.redhat.com/events)
- [How we contribute](https://www.redhat.com/about/our-community-contributions)

### About Red Hat Documentation

We help Red Hat users innovate and achieve their goals with our products and services with content they can trust. [Explore our recent updates](https://www.redhat.com/en/blog/whats-new-docsredhatcom).

### Making open source more inclusive

Red Hat is committed to replacing problematic language in our code, documentation, and web properties. For more details, see the [Red Hat Blog](https://www.redhat.com/en/blog/making-open-source-more-inclusive-eradicating-problematic-language).

### About Red Hat

We deliver hardened solutions that make it easier for enterprises to work across platforms and environments, from the core datacenter to the network edge.

### Theme

 

- [About Red Hat](https://redhat.com/en/about/company)
- [Jobs](https://redhat.com/en/jobs)
- [Events](https://redhat.com/en/events)
- [Locations](https://redhat.com/en/about/office-locations)
- [Contact Red Hat](https://redhat.com/en/contact)
- [Red Hat Blog](https://redhat.com/en/blog)
- [Inclusion at Red Hat](https://redhat.com/en/about/our-culture/diversity-equity-inclusion)
- [Cool Stuff Store](https://coolstuff.redhat.com/)
- [Red Hat Summit](https://www.redhat.com/en/summit)

© 2026 Red Hat

- [Privacy statement](https://redhat.com/en/about/privacy-policy)
- [Terms of use](https://redhat.com/en/about/terms-use)
- [All policies and guidelines](https://redhat.com/en/about/all-policies-guidelines)
- [Digital accessibility](https://redhat.com/en/about/digital-accessibility)

Back to top

 



---

## Updating Red Hat Connectivity Link

Updating Red Hat Connectivity Link
Red Hat Connectivity Link 1.3
Updating Red Hat Connectivity Link

Red Hat OpenShift Documentation Team

Legal Notice
Abstract

				This guide provides instruction about how to update Red Hat Connectivity Link.

Chapter 1. Updating Connectivity Link 1.3


			You can update your Red Hat Connectivity Link from one version to the next if your supported configuration meets the requirements of the version you want to update to.

1.1. Supported configurations with Connectivity Link


				Connectivity Link must run on a supported combination of OpenShift Container Platform and use the cert-manager Operator for Red Hat OpenShift. To configure observability, use Red Hat OpenShift Service Mesh. Red Hat provides both production and development support for supported configurations and tested integrations according to your subscription agreement.

Important

					If you use a configuration that includes OpenShift Container Platform 4.18 or older, you must also use Red Hat OpenShift Service Mesh as the Gateway API provider.

1.1.1. Supported OpenShift Container Platform version configurations


Expand
Red Hat Connectivity LinkRed Hat OpenShift Container PlatformRed Hat OpenShift DedicatedRed Hat OpenShift Service on AWSMicrosoft Azure Red Hat OpenShift

									Version 1.3

									4.21, 4.20, 4.19

									4.21, 4.20, 4.19

									4.21, 4.20, 4.19

									4.19

									Version 1.2

									4.20, 4.19, 4.18

									4.20, 4.19, 4.18

									4.20, 4.19, 4.18

									4.17

									Version 1.1

									4.19, 4.18, 4.17

									4.19, 4.18, 4.17

									4.19, 4.18, 4.17

									4.17

Show more

					For Microsoft Azure, see the Support lifecycle for Azure Red Hat OpenShift 4.

1.1.2. Supported Operators


Expand
Red Hat Connectivity LinkRed Hat OpenShift Service Meshcert-manager Operator for Red Hat OpenShift

									Version 1.3

									3.2

									1.18

									Version 1.2

									3.1

									1.17

									Version 1.1

									3.0

									1.15

Show more

1.1.3. Supported cloud providers


					All versions of Connectivity Link support the following platforms as backing cloud providers for OpenShift Container Platform:

-
							Amazon Web Services

-
							Google Cloud Platform

-
							Microsoft Azure

					For more information, see the documentation for your chosen cloud provider.

1.1.4. Supported cloud DNS providers


					For DNS policies, all versions of Connectivity Link support the following cloud DNS providers:

-
							Amazon Route 53

-
							Google Cloud Platform DNS

-
							Microsoft Azure DNS

					For more information, see the documentation for your chosen cloud DNS provider.

1.1.5. Supported on-premise DNS providers


					You can use CoreDNS can to configure an on-cluster DNS zone. For more information, see About using on-premise DNS with CoreDNS.

1.1.6. Supported data stores for rate limiting


					For rate limiting policies, Connectivity Link supports the following Redis-based data stores for rate limit counters in multicluster environments:

Expand
Red Hat Connectivity LinkRedis Enterprise or CloudAmazon ElasticacheDragonfly Community or Cloud

									Version 1.3

									latest

									latest

									latest

									Version 1.2

									latest

									latest

									latest

									Version 1.1

									latest

									latest

									latest

Show more

					For more information, see the documentation for your chosen Redis-based datastore.

1.1.7. Supported identity access management


					For authentication policies, Connectivity Link supports API keys and the following products:

Expand
Red Hat Connectivity Link VersionRed Hat build of Keycloak

									Version 1.3

									Version 26.4

									Version 1.2

									Version 26.4

									Version 1.1

									Version 26.2

Show more

					For more information, see Supported Configurations for Red Hat build of Keycloak.

1.2. Updating Connectivity Link with the OpenShift Container Platform web console


				You can update from Connectivity Link 1.2.x to Connectivity Link 1.3 by using the OpenShift Container Platform web console. Make sure that the OpenShift Container Platform version you have is compatible with the Connectivity Link version you want to update to. See the Red Hat Connectivity LInk Life Cycle Policy for details about version support and OpenShift Container Platform compatibility.

Important

					On OpenShift Container Platform 4.19 or later, if you are updating your cluster from a previous OpenShift Container Platform version that contains Gateway API Custom Resource Definitions (CRDs), you must ensure that these resources exactly match the Gateway API version supported by OpenShift Container Platform 4.19. For more information, see the OpenShift documentation on managing Gateway API resources.

1.3. Updating to Connectivity Link 1.3 in the web console


				Update your Red Hat Connectivity Link with the OpenShift Container Platform web console.

Prerequisites
-
						You already have Connectivity Link 1.2.x installed on OpenShift Container Platform 4.19 or later.

Procedure
-
						Click Ecosystem > Installed Operators > Red Hat Connectivity Link.

-
						Ensure that the Update channel is set to stable.

-
						If Update approval is set to Automatic, the update is installed immediately when the Update channel is set to stable.

-
						If Update approval is set to Manual, click Install.

-
						Wait until the Connectivity Link Operator is deployed.

-
						Verify that Connectivity Link 1.3 is installed and that your deployment is up and running.

1.4. Using your Red Hat subscription


				Red Hat Connectivity Link is provided through a software subscription. To manage your subscriptions, access your account at the Red Hat Customer Portal.

-
						Go to access.redhat.com.

-
						If you do not already have an account, create one.

-
						Log in to your account.

-
						In the menu bar, click Subscriptions to view and manage your subscriptions.

1.5. Additional resources


-
						Updating installed Operators

Legal Notice


		Copyright © Red Hat.

		Except as otherwise noted below, the text of and illustrations in this documentation are licensed by Red Hat under the Creative Commons Attribution–Share Alike 3.0 Unported license . If you distribute this document or an adaptation of it, you must provide the URL for the original version.

		Red Hat, as the licensor of this document, waives the right to enforce, and agrees not to assert, Section 4d of CC-BY-SA to the fullest extent permitted by applicable law.

		Red Hat, the Red Hat logo, JBoss, Hibernate, and RHCE are trademarks or registered trademarks of Red Hat, LLC. or its subsidiaries in the United States and other countries.

		Linux® is the registered trademark of Linus Torvalds in the United States and other countries.

		XFS is a trademark or registered trademark of Hewlett Packard Enterprise Development LP or its subsidiaries in the United States and other countries.

		The OpenStack® Word Mark and OpenStack logo are trademarks or registered trademarks of the Linux Foundation, used under license.

		All other trademarks are the property of their respective owners.
