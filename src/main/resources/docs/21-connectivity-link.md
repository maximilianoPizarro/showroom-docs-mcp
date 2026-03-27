# Red Hat Connectivity Link 1.3

Source: Official Red Hat Documentation (PDF)


---

## Connectivity Link Discover

Red Hat Connectivity Link 1.3
Discover
Multicloud application connectivity and API management
Last Updated: 2026-03-26

Red Hat Connectivity Link 1.3 Discover
Multicloud application connectivity and API management

Legal Notice
Copyright © Red Hat.
Except as otherwise noted below, the text of and illustrations in this documentation are licensed by
Red Hat under the Creative Commons Attribution–Share Alike 3.0 Unported license . If you
distribute this document or an adaptation of it, you must provide the URL for the original version.
Red Hat, as the licensor of this document, waives the right to enforce, and agrees not to assert,
Section 4d of CC-BY-SA to the fullest extent permitted by applicable law.
Red Hat, the Red Hat logo, JBoss, Hibernate, and RHCE are trademarks or registered trademarks of
Red Hat, LLC. or its subsidiaries in the United States and other countries.
Linux ® is the registered trademark of Linus Torvalds in the United States and other countries.
XFS is a trademark or registered trademark of Hewlett Packard Enterprise Development LP or its
subsidiaries in the United States and other countries.
The OpenStack ® Word Mark and OpenStack logo are trademarks or registered trademarks of the
Linux Foundation, used under license.
All other trademarks are the property of their respective owners.
Abstract
This guide provides an overview of the main features, technologies, benefits, and user workflows
that are available with Red Hat Connectivity Link.

. . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 
Table of Contents
## INTRODUCTION TO CONNECTIVITY LINK
### ABOUT RED HAT CONNECTIVITY LINK
1.1.1. Red Hat Connectivity Link architecture
### CONNECTIVITY LINK BENEFITS
### CONNECTIVITY LINK FEATURES
### CONNECTIVITY LINK USER WORKFLOWS
1.4.1. Platform engineer workflow
1.4.2. Application developer workflow
1.4.3. Business user workflow
### USING CONNECTIVITY LINK TECHNOLOGIES AND PATTERNS
### CONNECTIVITY LINK POLICY APIS
### SUPPORTED CONFIGURATIONS WITH CONNECTIVITY LINK
1.7.1. Supported OpenShift Container Platform version configurations
1.7.2. Supported Operators
1.7.3. Supported cloud providers
1.7.4. Supported cloud DNS providers
1.7.5. Supported on-premise DNS providers
1.7.6. Supported data stores for rate limiting
1.7.7. Supported identity access management
### ADDITIONAL RESOURCES
3
3
3
4
5
6
7
7
8
8
10
11
12
12
12
12
13
13
13
13
Table of Contents
1

Red Hat Connectivity Link 1.3 Discover
2
## INTRODUCTION TO CONNECTIVITY LINK
Red Hat Connectivity Link is a control plane for configuring the Gateway API data plane in OpenShift
Container Platform clusters. You can use it to apply authentication, rate limiting, and DNS policies to
gateway resources.
### ABOUT RED HAT CONNECTIVITY LINK
You can use Connectivity Link to connect, secure, observe, and protect all of your service endpoints.
Use Connectivity Link in multicloud and hybrid cloud environments. Red Hat Connectivity Link is a single
data plane used to apply policies to Gateway API resources in OpenShift Container Platform clusters.
Gateway API is structured to meet the different needs of organizational teams.
First, configure and deploy ingress gateways with the role-oriented resources and components of the
Kubernetes Gateway API. Then, use Connectivity Link to attach policies to Gateway API resources.
Attaching policies means that you can skip the embedding of networking code into your applications,
and use a code-as-infrastructure approach instead.
Example policy types:
Configure gateways with TLS policies for:
Certificate management
Authentication
Authorization
Rate limiting
Integrate DNS policies for:
Multicluster load balancing
Health checks
Remediation
In addition, you can use the following included templates to see what is happening:
Observability dashboards
Observability metrics
Tracing
Alerts
1.1.1. Red Hat Connectivity Link architecture
The following diagram shows a high-level overview of the Connectivity Link architecture with its main
features and technologies:
## INTRODUCTION TO CONNECTIVITY LINK
3

Figure 1.1. Connectivity Link architecture
Connectivity Link supports OpenShift Service Mesh 3.2 as the Gateway API provider.
### CONNECTIVITY LINK BENEFITS
Connectivity Link provides the following main benefits:
Kubernetes-native
Connectivity Link uses Kubernetes-native features for resource efficiency and optimal use. These
features can run on any public or private OpenShift Container Platform cluster, offering multicloud
and hybrid-cloud behavior by default.
Hybrid cloud and multicloud
Red Hat Connectivity Link 1.3 Discover
4

Connectivity Link includes the flexibility to deploy the same application to any OpenShift Container
Platform cluster hosted on a public or private cloud. This removes the dependency on a specific
cloud provider.
For example, if one cloud provider is having network issues, you can switch your deployment and
traffic to another cloud provider to minimize the impact on your customers. This ability provides high
availability and disaster recovery. It also means that your platforms and applications can remain
resilient by ensuring that you are prepared for the unexpected and can establish uninterrupted
service.
Use infrastructure as code
You can define your infrastructure by using code to ensure that it is version controlled, tested, and
easily replicated. OpenShift Container Platform auto-scaling features dynamically adjust resources
based on workload demand. You can also use Connectivity Link to implement robust monitoring and
logging solutions to gain full visibility into your OpenShift Container Platform clusters.
Use the tools you have
You can use the technologies and tools that you already have in place with Connectivity Link. For
example, the following are services and tools you can use:
Cloud service providers: Amazon Web Services, Google Cloud, Microsoft Azure
DNS providers: Amazon Route 53, Google Cloud DNS, Microsoft Azure DNS, CoreDNS
Gateway API controllers: OpenShift Container Platform, OpenShift Container Platform
Service Mesh
Metrics and alerts: Prometheus, Thanos, Kiali
Dashboards: Grafana, Red Hat Developer Hub
GitOps and automation: Red Hat Ansible Automation Platform, OpenShift Container
Platform GitOps, GitHub
Additional integrations: Red Hat build of Keycloak, Red Hat Service Interconnect
Gateway API
Using Gateway API to set up ingress policies on each OpenShift Container Platform cluster means
that ingress can be identical and implemented simultaneously.
In Kubernetes-based environments, Gateway API is the standard for deploying ingress gateways and
managing application networking. Gateway API provides standardized APIs for ingress traffic
management and support for many protocols.
Observability
Connectivity Link uses Kuadrant-maintained Gateway API state metrics, metrics exposed by
Connectivity Link components, and standard metrics exposed by Envoy to build a set of example
template alerts and dashboards. You can download and use these Kuadrant community templates to
integrate with Grafana, Prometheus, and Alertmanager deployments, or use them as starting points
that you can tailor for your specific needs.
### CONNECTIVITY LINK FEATURES
Connectivity Link includes the following features:
Multicloud application connectivity
d
## INTRODUCTION TO CONNECTIVITY LINK
5

DNS provider integrations
High availability and disaster recovery
Global load balancing
Application portability
Application connectivity configuration
Endpoint health and status checks
Automatic TLS certificate generation
Universal authentication
Kubernetes ingress policy management
Global DNS policy
TLS policy
Auth policy
Rate-limiting policy
Token rate-limiting policy
Traffic weighting and distribution
User-role-based design
Multicluster administration
Observability dashboards and alerts
OpenShift Container Platform web console dynamic plugin
Composable API management
API security and governance
Advanced API metrics collection
API-level policies for authentication, authorization, and rate limiting
Flexible integration with open source tools
### CONNECTIVITY LINK USER WORKFLOWS
Similar to Gateway API, Connectivity Link is designed with specific user roles in mind, such as:
Platform or infrastructure engineer
Application developer
Red Hat Connectivity Link 1.3 Discover
6

Business user
Each persona has a different way to work with Connectivity Link, depending on how that person is
interacting with your OpenShift Container Platform clusters.
1.4.1. Platform engineer workflow
As a platform engineer or infrastructure provider, you can use Connectivity Link to set up ingress
gateways on OpenShift Container Platform clusters in specific regions. You can configure all policies
identically on all gateways for consistency.
For example, configure DNS policies to ensure that customers in Brazil are routed to the South
American data center and so on. You can also configure TLS, authentication and authorization, and rate-
limiting policies to ensure that gateway security, performance, and monitoring all conform to your
standards.
Connect gateways and load balance
As a platform engineer, you must start by creating at least one gateway. If gateways exist, you can
move on to configuring policies for your gateways.
You can connect gateways by creating a DNS policy and configuring a global load balancing strategy.
DNS records are reconciled with your cloud DNS provider automatically, whether in a single-cluster or
multicluster environment. You can use the following features:
Automatic DNS configuration
Mutiple DNS providers
DNS-based load balancing, such as round-robin, weighted, and geo-based
Single or multi-cluster ready
Secure gateways
After you create your gateways, secure them. You can secure gateways by using a TLS policy
that automatically generates certificate requests for the host names specified in your
gateway. You can also set up application security defaults and overrides by using
authentication and authorization policies and rate-limiting policies. You can use the following
features:
TLS: Automatic certificate requests and ACME providers
Auth policies
Rate-limiting: Global rate-limiting, external IDPs, default and overrides
Observe gateways
After your gateways are created and secured, you can configure your observability stack.
Observe your connectivity and runtime metrics by using dashboards and alerts. For example,
configure metrics for policy compliance and governance, resource consumption, error rates,
request latency and throughput.
1.4.2. Application developer workflow
As an application developer, you can use Connectivity Link to deploy applications and APIs on OpenShift
Container Platform clusters and gateways that are set up by platform engineers. You can also monitor
workloads and the status of OpenShift Container Platform resource metrics and tracing by using
## INTRODUCTION TO CONNECTIVITY LINK
7

observability dashboards and alerts.
Example tasks
Configure routes
Set up application routes and API definitions and publish them to the cluster.
Protect applications with Auth policies
OAuth 2
JWT authorization policies
API keys
Kubernetes tokens
Role-based access (RBAC)
Relationship-based access control (ReBAC)
Open Policy Agent (OPA)
Observe application and API performance
Monitor workloads and the status of OpenShift Container Platform resource metrics
and tracing by using observability dashboards and alerts.
Ensure that APIs meet performance and availability benchmarks achieved by other data
centers by viewing API metrics, such as uptime, requests per second, latency, and errors
per minute.
1.4.3. Business user workflow
Business users, such as account managers and application owners, can use the data from Connectivity
Link to work with customers.
You can use Grafana-based observability dashboards to monitor the status of applications and APIs in
data centers in specific regions, and work with customers on specific performance metrics.
Specifically, you can view API metrics, such as uptime, requests per second, latency, and errors per
minute, to ensure that APIs meet the performance and availability benchmarks that your customers
require.
### USING CONNECTIVITY LINK TECHNOLOGIES AND PATTERNS
You can use the following technologies and patterns with Connectivity Link:
Policy-based configuration
You can use the Connectivity Link policy attachment pattern to add behavior to a Kubernetes object
by using configuration that cannot be described in the object spec field.
With policy attachments comes the concept of defaults and overrides. These defaults and overrides
mean that you can configure different roles to operate with policy APIs at different levels of the
object hierarchy. These policies are then merged with specific rules and strategies to form an
effective policy that can be used across your organization.
Red Hat Connectivity Link 1.3 Discover
8

WebAssembly plugin
As a WebAssembly (WASM) plugin developed for the Envoy proxy, Connectivity Link is lightweight,
hardware independent, non-intrusive, and secure. This means that clusters that are using OpenShift
Container Platform OpenShift Service Mesh, Istio, or Envoy for ingress do not require major changes
to their existing ingress objects and policies to begin using Connectivity Link.
Multicluster configuration mirroring
You can configure and deploy your policies across different cloud service providers in a consistent
way with Connectivity Link. Connectivity Link uses multicluster configuration mirroring across
multicloud and hybrid cloud environments. You can deploy your routing, configuration, and policies
wherever you need them through one interface.
This means that your development, test, and production environments can be consistent. Use
Connectivity Link to supply unified experiences, global administration, and security compliance.
The following image shows Connectivity Link multicluster configuration mirroring:
Figure 1.2. Multicluster configuration mirroring across multicloud and hybrid cloud environments
API connectivity and API management
Connectivity Link provides scalable multicluster and multi-gateway connectivity management, along
with API management features such as API observability, authentication, and rate limiting.
## INTRODUCTION TO CONNECTIVITY LINK
9

Figure 1.3. Connectivity Link API management and connectivity
### CONNECTIVITY LINK POLICY APIS
Understand how and when you can use the Connectivity Link core policies and observability features
with your cloud applications and APIs.
Secure your applications with TLSPolicy
TLSPolicy is a lightweight wrapper API to manage TLS for targeted gateways.
Automatically provision TLS certificates based on the gateway listener hosts by using
integration with cert-manager and ACME providers such as Let’s Encrypt.
Configure secrets so that the gateway automatically retrieves them.
Protect your applications with AuthPolicy
Use AuthPolicy objects to apply authentication and authorization across your selected
listeners in a gateway or at the HTTPRoute or HTTPRouteRule level.
Use the hierarchical and role-based concept of defaults and overrides to improve
collaboration and ensure compliance.
Use dedicated OIDC authentication providers such as Red Hat build of Keycloak.
Apply fine-grained authorization requirements based on request and metadata attributes.
Protect your applications with RateLimitPolicy
Apply rate-limiting rules across all listeners in a gateway or at the HTTPRoute or 
HTTPRouteRule level.
Use the role-based and hierarchical concept of defaults and overrides to improve
collaboration and ensure compliance.
Red Hat Connectivity Link 1.3 Discover
10

Configure limits conditionally based on metadata and request data.
Share counters by using a backend store in multicluster environments.
For example, the following rate-limiting policy configures a specified limit of 5 requests per 10 seconds
for every listener defined in the target gateway that does not have its own rate limiting policy defined:
Rate-limiting policy example
Connect your applications with DNSPolicy
DNSPolicy is a standard API that is not based on custom annotations.
Automatically populate DNS records based on listener hosts and addresses expressed by
Gateway API resources.
Configure multicluster connectivity and routing options, for example, geographic and
weighted responses.
Use common cloud DNS providers: Amazon Route 53, Microsoft Azure DNS, Google Cloud
DNS, or CoreDNS.
Configure health checks to enable DNS failover.
Observe your ingress traffic
You can use the Connectivity Link observability features to observe and monitor your gateways,
applications, and APIs on OpenShift Container Platform. You can download and use community-
based templates to integrate with Grafana, Prometheus, and Alertmanager deployments, or use
these templates as starting points to modify for your specific needs.
### SUPPORTED CONFIGURATIONS WITH CONNECTIVITY LINK
Connectivity Link must run on a supported combination of OpenShift Container Platform and use the
cert-manager Operator for Red Hat OpenShift. To configure observability, use Red Hat OpenShift
Service Mesh. Red Hat provides both production and development support for supported
configurations and tested integrations according to your subscription agreement.
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
## INTRODUCTION TO CONNECTIVITY LINK
11

IMPORTANT
If you use a configuration that includes OpenShift Container Platform 4.18 or older, you
must also use Red Hat OpenShift Service Mesh as the Gateway API provider.
1.7.1. Supported OpenShift Container Platform version configurations
Red Hat Connectivity Link
Red Hat
OpenShift
Container
Platform
Red Hat
OpenShift
Dedicated
Red Hat
OpenShift
Service on
AWS
Microsoft
Azure Red Hat
OpenShift
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
For Microsoft Azure, see the Support lifecycle for Azure Red Hat OpenShift 4 .
1.7.2. Supported Operators
Red Hat Connectivity Link
Red Hat OpenShift Service
Mesh
cert-manager
Operator for
Red Hat
OpenShift
Version 1.3
3.2
1.18
Version 1.2
3.1
1.17
Version 1.1
3.0
1.15
1.7.3. Supported cloud providers
All versions of Connectivity Link support the following platforms as backing cloud providers for
OpenShift Container Platform:
Amazon Web Services
Google Cloud Platform
Microsoft Azure
For more information, see the documentation for your chosen cloud provider.
1.7.4. Supported cloud DNS providers
For DNS policies, all versions of Connectivity Link support the following cloud DNS providers:
Red Hat Connectivity Link 1.3 Discover
12

Amazon Route 53
Google Cloud Platform DNS
Microsoft Azure DNS
For more information, see the documentation for your chosen cloud DNS provider.
1.7.5. Supported on-premise DNS providers
You can use CoreDNS can to configure an on-cluster DNS zone. For more information, see About using
on-premise DNS with CoreDNS.
1.7.6. Supported data stores for rate limiting
For rate limiting policies, Connectivity Link supports the following Redis-based data stores for rate limit
counters in multicluster environments:
Red Hat Connectivity Link
Redis
Enterprise or
Cloud
Amazon
Elasticache
Dragonfly
Community or
Cloud
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
For more information, see the documentation for your chosen Redis-based datastore.
1.7.7. Supported identity access management
For authentication policies, Connectivity Link supports API keys and the following products:
Red Hat Connectivity Link Version
Red Hat build of Keycloak
Version 1.3
Version 26.4
Version 1.2
Version 26.4
Version 1.1
Version 26.2
For more information, see Supported Configurations for Red Hat build of Keycloak .
### ADDITIONAL RESOURCES
Red Hat Connectivity Link
Gateway API with OpenShift Container Platform networking
## INTRODUCTION TO CONNECTIVITY LINK
13

Red Hat OpenShift Service Mesh 3.2 documentation
Red Hat Connectivity Link 1.3 Discover
14

---

## Connectivity Link Whats New

Red Hat Connectivity Link 1.3
What’s New
What's new in Red Hat Connectivity Link
Last Updated: 2026-03-26

Red Hat Connectivity Link 1.3 What’s New
What's new in Red Hat Connectivity Link

Legal Notice
Copyright © Red Hat.
Except as otherwise noted below, the text of and illustrations in this documentation are licensed by
Red Hat under the Creative Commons Attribution–Share Alike 3.0 Unported license . If you
distribute this document or an adaptation of it, you must provide the URL for the original version.
Red Hat, as the licensor of this document, waives the right to enforce, and agrees not to assert,
Section 4d of CC-BY-SA to the fullest extent permitted by applicable law.
Red Hat, the Red Hat logo, JBoss, Hibernate, and RHCE are trademarks or registered trademarks of
Red Hat, LLC. or its subsidiaries in the United States and other countries.
Linux ® is the registered trademark of Linus Torvalds in the United States and other countries.
XFS is a trademark or registered trademark of Hewlett Packard Enterprise Development LP or its
subsidiaries in the United States and other countries.
The OpenStack ® Word Mark and OpenStack logo are trademarks or registered trademarks of the
Linux Foundation, used under license.
All other trademarks are the property of their respective owners.
Abstract
This guide provides the latest information on what's new in this release of Red Hat Connectivity
Link.

. . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 
Table of Contents
## CONNECTIVITY LINK 1.3 RELEASE NOTES
### RED HAT CONNECTIVITY LINK 1.3 RELEASE NOTES
1.1.1. New features and enhancements
1.1.2. Known issues
1.1.3. Async releases
3
3
3
3
3
Table of Contents
1

Red Hat Connectivity Link 1.3 What’s New
2
## CONNECTIVITY LINK 1.3 RELEASE NOTES
Welcome to the Red Hat Connectivity Link release notes, where you can learn about what is new and
what is fixed.
### RED HAT CONNECTIVITY LINK 1.3 RELEASE NOTES
Red Hat Connectivity Link is a modular and flexible solution for application connectivity, policy
management, and API management in multicloud and hybrid cloud environments. You can use
Connectivity Link to secure, protect, connect, and observe your APIs, applications, and infrastructure.
Connectivity Link is based on the Kuadrant community project. Connectivity Link provides a control
plane for configuring and deploying ingress gateways and policies based on the Kubernetes Gateway
API standard. Connectivity Link supports OpenShift Service Mesh 3.2 as the Gateway API provider,
which is based on the Istio community project.
See the Red Hat Connectivity Link Life Cycle Policy  for details about version support and OpenShift
Container Platform compatibility.
1.1.1. New features and enhancements
You can use the new features and enhancements that are available with Red Hat Connectivity Link 1.3.
CoreDNS integration for on-premise DNS is Generally Available
Connectivity Link 1.3 provides integration with CoreDNS for on-premise DNS as a Generally
Available feature. For more information, see About using on-premise DNS with CoreDNS.
Updated Observability documentation is now available
Connectivity Link 1.3 includes enhanced Observability documentation, including configuring access
logs, tracing, and request correlation. For more information, see Red Hat Connectivity Link
observability.
1.1.2. Known issues
Connectivity Link 1.3 has two known issues.
When the disk storage option is enabled in the Limitador custom resource (CR), both the initial 
limitador deployment and Operator update get stuck on a Multi-Attach error because of the
persistent volume claim (PVC) volume. As a workaround, you can change the limitador
deployment strategy to "Recreate" and the reconcilation process works as expected.
(CONNLINK-855)
When either the Redis or RedisCached storage option is set in a Limitador CR and the 
limitador pod gets restarted for any reason, the first request to the gateway is never rate-
limited. All http requests after this are rate-limited. ( CONNLINK-856)
1.1.3. Async releases
Security, bug fix, and enhancement updates for Red Hat Connectivity Link 1.3 are released
asynchronously through the Red Hat Network. All Red Hat Connectivity Link 1.3 updates are available on
the Red Hat Customer Portal. For more information about asynchronous updates, read the Red Hat
Connectivity Link Life Cycle Policy.
d
l
bl
d
f
h
f
d
## CONNECTIVITY LINK 1.3 RELEASE NOTES
3

Red Hat Customer Portal users can enable update notifications in the account settings for Red Hat
Subscription Management (RHSM). When notifications are enabled, you are notified through email
whenever new updates relevant to your registered systems are released.
This section is updated over time to provide notes on enhancements and bug fixes for future
asynchronous releases of Red Hat Connectivity Link 1.3. Versioned asynchronous releases, for example
with the Red Hat Connectivity Link 1.3.z, are detailed in the following subsections.
Red Hat Connectivity Link 1.3.1 bug fix and security update
Issued: 18 March 2026
Red Hat Connectivity Link release 1.3.1 is now available. Red Hat Connectivity Link uses the stable
update channel to track and receives updates for the Red Hat Connectivity Link Operator. You can
manage how your updates are applied through your Operator Lifecycle Manager (OLM) subscription
resource. For more information, see Updating Red Hat Connectivity Link
Previously, the wasm-shim networking protcol incorrectly appended values to existing
request headers instead of replacing them during the external authorization flow. This
caused inconsistent behavior, leading to comma-separated header values that could disrupt
upstream processing. With this release, the logic is updated to ensure that the headers
provided in the CheckResponse object now correctly replace existing values. This fix
restores predictable header management. (CONNLINK-867)
Red Hat Connectivity Link 1.3 What’s New
4

---

## Connectivity Link Installing

Red Hat Connectivity Link 1.3
Installing on OpenShift Container Platform
Single cluster and multicluster deployments
Last Updated: 2026-03-26

Red Hat Connectivity Link 1.3 Installing on OpenShift Container Platform
Single cluster and multicluster deployments

Legal Notice
Copyright © Red Hat.
Except as otherwise noted below, the text of and illustrations in this documentation are licensed by
Red Hat under the Creative Commons Attribution–Share Alike 3.0 Unported license . If you
distribute this document or an adaptation of it, you must provide the URL for the original version.
Red Hat, as the licensor of this document, waives the right to enforce, and agrees not to assert,
Section 4d of CC-BY-SA to the fullest extent permitted by applicable law.
Red Hat, the Red Hat logo, JBoss, Hibernate, and RHCE are trademarks or registered trademarks of
Red Hat, LLC. or its subsidiaries in the United States and other countries.
Linux ® is the registered trademark of Linus Torvalds in the United States and other countries.
XFS is a trademark or registered trademark of Hewlett Packard Enterprise Development LP or its
subsidiaries in the United States and other countries.
The OpenStack ® Word Mark and OpenStack logo are trademarks or registered trademarks of the
Linux Foundation, used under license.
All other trademarks are the property of their respective owners.
Abstract
This guide describes how to install Connectivity Link components on OpenShift in single-cluster
and multicluster environments.

. . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 
Table of Contents
## INSTALLING ON OPENSHIFT CONTAINER PLATFORM
### GETTING READY TO INSTALL CONNECTIVITY LINK
1.1.1. Required platforms and components
1.1.2. Optional components
1.1.3. Supported configurations with Connectivity Link
1.1.3.1. Supported OpenShift Container Platform version configurations
1.1.3.2. Supported Operators
1.1.3.3. Supported cloud providers
1.1.3.4. Supported cloud DNS providers
1.1.3.5. Supported on-premise DNS providers
1.1.3.6. Supported data stores for rate limiting
1.1.3.7. Supported identity access management
### INSTALLING CONNECTIVITY LINK WITH THE OPENSHIFT CONTAINER PLATFORM WEB CONSOLE
1.2.1. Using Istio as a gateway controller
### INSTALLING CONNECTIVITY LINK ON OPENSHIFT CONTAINER PLATFORM FROM THE COMMAND LINE
### INSTALLING CONNECTIVITY LINK ON OPENSHIFT CONTAINER PLATFORM FROM THE CLI WITH ISTIO
AS GATEWAY CONTROLLER
### CONFIGURING DNS PROVIDER CREDENTIALS FOR AWS
### CONFIGURING GOOGLE DNS PROVIDER CREDENTIALS
### CONFIGURING AZURE DNS PROVIDER CREDENTIALS
### CONFIGURING REDIS STORAGE FOR RATE LIMITING
### ENABLING THE CONNECTIVITY LINK DYNAMIC PLUGIN FOR OPENSHIFT CONTAINER PLATFORM WEB
CONSOLE
### USING YOUR RED HAT SUBSCRIPTION
### ADDITIONAL RESOURCES
3
3
3
3
4
4
5
5
5
5
6
6
6
8
9
11
12
14
15
17
18
19
19
Table of Contents
1

Red Hat Connectivity Link 1.3 Installing on OpenShift Container Platform
2
## INSTALLING ON OPENSHIFT CONTAINER
PLATFORM
As a platform engineer, you can install Connectivity Link on OpenShift Container Platform clusters.
### GETTING READY TO INSTALL CONNECTIVITY LINK
As you plan your Connectivity Link install, ensure that you have access to the required platforms in your
environment with the correct user permissions. You can also decide whether to use optional supported
components, such as rate limiting and Observability.
1.1.1. Required platforms and components
The following platforms and components are required to install Connectivity Link successfully:
Red Hat account
You have a Red Hat account with subscriptions for Connectivity Link and OpenShift Container
Platform.
OpenShift Container Platform
OpenShift Container Platform 4.19 or later is installed, or you have access to a supported OpenShift
Container Platform cloud service. See OpenShift Container Platform installation documentation .
IMPORTANT
When using the Gateway API custom resource definitions (CRDs) provided in
OpenShift Container Platform 4.19 or newer, you must create a GatewayClass named
openshift-default and specify a controllerName of openshift.io/gateway-
controller/v1. For more details, see the Getting started with Gateway API for the
Ingress Operator (OpenShift Container Platform documentation).
OpenShift Service Mesh
A separate OpenShift Service Mesh installation is not required with Connectivity Link 1.3. If you use
OpenShift Service Mesh, ensure that you are using 3.2 to stay in a supported configuration.
cert-manager Operator for Red Hat OpenShift
You installed cert-manager Operator for Red Hat OpenShift 1.18 to manage the TLS certificates for
your gateways. See the cert-manager Operator for Red Hat OpenShift documentation .
IMPORTANT
Before using a Connectivity Link TLSPolicy custom resource (CR), you must set up a
certificate issuer for your cloud provider platform. See the OpenShift documentation
on configuring an ACME issuer.
1.1.2. Optional components
The following components are optional with Connectivity Link. You can decide what you want to use and
plan for those configurations before beginning your installation.
## INSTALLING ON OPENSHIFT CONTAINER PLATFORM
3

DNSPolicy
For a DNSPolicy CR, you must have an account for one of the supported cloud DNS providers and
have set up a hosted zone for Connectivity Link. For more details, see your cloud DNS provider
documentation:
Amazon Route 53 documentation
Google Cloud DNS documentation
Microsoft Azure DNS documentation
RateLimitPolicy
For RateLimitPolicy CRs, you must have a shared accessible Redis-based datastore for rate-limit
counters in a multicluster environment. For details on how to install and configure a secure and highly
available datastore, see the documentation for your Redis-compatible datastore:
Redis documentation
AWS ElastiCache (Redis OSS) User Guide
Dragonfly documentation
Valkey documentation
AuthPolicy
For an AuthPolicy CR, you can install Red Hat build of Keycloak if required in your environment. For
more details, see the Red Hat build of Keycloak documentation .
Observability
For Observability, you must configure OpenShift Container Platform user workload monitoring to
remote-write to a central storage system.
1.1.3. Supported configurations with Connectivity Link
Connectivity Link must run on a supported combination of OpenShift Container Platform and use the
cert-manager Operator for Red Hat OpenShift. To configure observability, use Red Hat OpenShift
Service Mesh. Red Hat provides both production and development support for supported
configurations and tested integrations according to your subscription agreement.
IMPORTANT
If you use a configuration that includes OpenShift Container Platform 4.18 or older, you
must also use Red Hat OpenShift Service Mesh as the Gateway API provider.
1.1.3.1. Supported OpenShift Container Platform version configurations
Red Hat Connectivity Link
Red Hat
OpenShift
Container
Platform
Red Hat
OpenShift
Dedicated
Red Hat
OpenShift
Service on
AWS
Microsoft
Azure Red Hat
OpenShift
Version 1.3
4.21, 4.20, 4.19
4.21, 4.20, 4.19
4.21, 4.20, 4.19
4.19
Red Hat Connectivity Link 1.3 Installing on OpenShift Container Platform
4

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
Red Hat Connectivity Link
Red Hat
OpenShift
Container
Platform
Red Hat
OpenShift
Dedicated
Red Hat
OpenShift
Service on
AWS
Microsoft
Azure Red Hat
OpenShift
For Microsoft Azure, see the Support lifecycle for Azure Red Hat OpenShift 4 .
1.1.3.2. Supported Operators
Red Hat Connectivity Link
Red Hat OpenShift Service
Mesh
cert-manager
Operator for
Red Hat
OpenShift
Version 1.3
3.2
1.18
Version 1.2
3.1
1.17
Version 1.1
3.0
1.15
1.1.3.3. Supported cloud providers
All versions of Connectivity Link support the following platforms as backing cloud providers for
OpenShift Container Platform:
Amazon Web Services
Google Cloud Platform
Microsoft Azure
For more information, see the documentation for your chosen cloud provider.
1.1.3.4. Supported cloud DNS providers
For DNS policies, all versions of Connectivity Link support the following cloud DNS providers:
Amazon Route 53
Google Cloud Platform DNS
Microsoft Azure DNS
For more information, see the documentation for your chosen cloud DNS provider.
1.1.3.5. Supported on-premise DNS providers
## INSTALLING ON OPENSHIFT CONTAINER PLATFORM
5

You can use CoreDNS can to configure an on-cluster DNS zone. For more information, see About using
on-premise DNS with CoreDNS.
1.1.3.6. Supported data stores for rate limiting
For rate limiting policies, Connectivity Link supports the following Redis-based data stores for rate limit
counters in multicluster environments:
Red Hat Connectivity Link
Redis
Enterprise or
Cloud
Amazon
Elasticache
Dragonfly
Community or
Cloud
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
For more information, see the documentation for your chosen Redis-based datastore.
1.1.3.7. Supported identity access management
For authentication policies, Connectivity Link supports API keys and the following products:
Red Hat Connectivity Link Version
Red Hat build of Keycloak
Version 1.3
Version 26.4
Version 1.2
Version 26.4
Version 1.1
Version 26.2
For more information, see Supported Configurations for Red Hat build of Keycloak .
### INSTALLING CONNECTIVITY LINK WITH THE OPENSHIFT
CONTAINER PLATFORM WEB CONSOLE
You can use the OpenShift Container Platform web console to install the Red Hat Connectivity Link
Operator. You must perform these steps on each OpenShift Container Platform cluster that you want to
use Connectivity Link on.
The OpenShift Container Platform Cluster Ingress Operator is the default gateway controller for
Connectivity Link.
An OperatorGroup custom resource (CR) is created automatically when you use the web console. For
more information, see Operator Groups.
Red Hat Connectivity Link 1.3 Installing on OpenShift Container Platform
6

WARNING
Connectivity Link requires kuadrant.io/* labels to search and filter resources on the
cluster. Do not remove labels with this prefix. Removal might cause unexpected
behavior and degradation of Connectivity Link.
Prerequisites
You are using a supported configuration of OpenShift Container Platform and required
components.
You are logged into OpenShift Container Platform as a cluster administrator.
You are logged into the OpenShift Container Platform web console with cluster-admin
privileges.
Procedure
1. In the left navigation menu, click Ecosystem > Software Catalog.
2. In the Filter by keyword text box, enter Connectivity to find the Red Hat Connectivity Link
Operator.
3. Read the information about the Operator, and click Install to display the Operator subscription
page.
4. Select your subscription settings as follows:
Update Channel: stable
Version: 1.3.0
Installation mode: All namespaces on the cluster (default).
Installed namespace: Select the namespace where you want to install the Operator, for
example, kuadrant-system. If the namespace does not already exist, click this field and
select Create Project to create the namespace.
Approval Strategy: Select Automatic or Manual.
5. Click Install, and wait a few moments until the Operator is installed and ready for use.
6. Click Ecosystem > Installed Operators > Red Hat Connectivity Link.
7. Click the Kuadrant tab, and click Create Kuadrant to create a Kuadrant custom resource (CR).
8. In the Configure via field, click YAML view to edit the definition, for example, the Kuadrant CR
name.
9. Click Create and wait for the deployment to be displayed in the list.
Verification

## INSTALLING ON OPENSHIFT CONTAINER PLATFORM
7

After you have installed the Operator, click Ecosystem > Installed Operators to verify that the Red Hat
Connectivity Link Operator and the following component Operators are installed in your namespace:
Authorino Operator: Enables authentication and authorization for gateways and applications in
a Gateway API network.
DNS Operator: Configures how north-south traffic from outside the network is balanced and
reaches gateways.
Limitador Operator: Enables rate limiting for gateways and applications in a Gateway API
network.
Next step
Update your Subscription CR to use the OpenShift Container Platform Cluster Ingress
Operator.
1.2.1. Using Istio as a gateway controller
If you used the web console to install Connectivity Link and you want to use OpenShift Service Mesh,
you must patch the Subscription custom resource (CR) to use Istio as your Gateway object controller.
Prerequisites
You are using a supported configuration of OpenShift Container Platform and required
components.
You installed Connectivity Link using the OpenShift Container Platform web console.
You are logged into OpenShift Container Platform as a cluster administrator.
Procedure
1. Patch your Subscription CR by running the following command:
Replace <kuadrant_system> with the namespace you used when installing Connectivity Link.
2. Restart your deployment to apply the updated CR by running the following command:
Replace <kuadrant_system> with the namespace you used when installing Connectivity Link.
Verification
Check the status of the Kuadrant CR generation by running the following command:
$ oc patch subscription rhcl-operator -n <kuadrant_system> --type=json -
p='[{"op":"add","path":"/spec/config","value":{"env":
[{"name":"ISTIO_GATEWAY_CONTROLLER_NAMES","value":"istio.io/gateway-
controller"}]}}]'
$ oc rollout restart deployment/kuadrant-operator-controller-manager -n <kuadrant_system>
$ oc wait kuadrant/kuadrant --for="condition=Ready=true" -n <kuadrant_system> --
timeout=300s
Red Hat Connectivity Link 1.3 Installing on OpenShift Container Platform
8

Replace <kuadrant_system> with the namespace you used when installing Connectivity Link.
Example output
### INSTALLING CONNECTIVITY LINK ON OPENSHIFT CONTAINER
PLATFORM FROM THE COMMAND LINE
You can install Connectivity Link with OpenShift CLI (oc) using the OpenShift Container Platform
Cluster Ingress Operator as the default Gateway object controller. You must complete these steps on
each OpenShift Container Platform cluster that you want to use Connectivity Link on.
WARNING
Connectivity Link uses labels formatted as kuadrant.io/* to search and filter
resources on the cluster. Removing of any labels with the prefix might cause
unexpected behavior and degradation of Connectivity Link.
Prerequisites
You are logged into OpenShift Container Platform as a cluster administrator.
You are using a supported configuration of OpenShift Container Platform and required
components.
You installed the OpenShift CLI (oc).
Procedure
1. Create the namespace where you want to install Connectivity Link by running the following
command:
You can replace the default <kuadrant_system> with the namespace you want to use.
2. Install Connectivity Link by creating and applying Subscription and OperatorGroup custom
resources (CRs) by running the following command:
kuadrant.kuadrant.io/kuadrant Ready

$ oc create ns <kuadrant_system>
$ oc apply -f - <<EOF
apiVersion: operators.coreos.com/v1alpha1
kind: Subscription
metadata:
  name: rhcl-operator
  namespace: <kuadrant_system>
spec:
  channel: stable
  installPlanApproval: Automatic
## INSTALLING ON OPENSHIFT CONTAINER PLATFORM
9

Replace <kuadrant_system> with the namespace you used.
3. Confirm that the Connectivity Link installation has finished by running one of the following
commands:
Expect the status of installplan.operators.coreos.com/install-<suffix> when Connectivity
Link is ready. The name of the install plan has a random suffix, for example, 4rql7.
4. Create your Connectivity Link custom resource (CR) by running the following command:
Replace <kuadrant_system> with the namespace you used.
Verification
Check the status of the Connectivity Link CR generation by running the following command:
Replace <kuadrant_system> with the namespace you used.
Example output
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
$ oc wait --for=jsonpath={.status.installPlanRef.name} subscription rhcl-operator --
timeout=10s
ip=$(oc get subscription rhcl-operator -o=jsonpath={.status.installPlanRef.name})
$ oc wait --for=condition=Installed installplan ${ip} --timeout=60s
$ oc apply -f - <<EOF
apiVersion: kuadrant.io/v1beta1
kind: Kuadrant
metadata:
  name: kuadrant
  namespace: <kuadrant_system>
EOF
$ oc wait kuadrant/kuadrant --for="condition=Ready=true" -n <kuadrant_system> --
timeout=300s
kuadrant.kuadrant.io/kuadrant Ready
Red Hat Connectivity Link 1.3 Installing on OpenShift Container Platform
10
### INSTALLING CONNECTIVITY LINK ON OPENSHIFT CONTAINER
PLATFORM FROM THE CLI WITH ISTIO AS GATEWAY CONTROLLER
If you are using OpenShift Service Mesh, you can install Connectivity Link with OpenShift CLI (oc) using
Istio as your Gateway object controller. You must complete these steps on each OpenShift Container
Platform cluster that you want to use Connectivity Link on.
WARNING
Connectivity Link uses labels formatted as kuadrant.io/* to search and filter
resources on the cluster. Removing of any labels with the prefix might cause
unexpected behavior and degradation of Connectivity Link.
Prerequisites
You are logged into OpenShift Container Platform as a cluster administrator.
You are using a supported configuration of OpenShift Container Platform and required
components.
You installed the OpenShift CLI (oc).
You installed and configured OpenShift Service Mesh.
Procedure
1. Create the namespace where you want to install Connectivity Link by running the following
command:
You can replace the default <kuadrant-system> with the namespace you want to use.
2. Install Connectivity Link by running the following command:

$ oc create ns <kuadrant-system>
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
## INSTALLING ON OPENSHIFT CONTAINER PLATFORM
11

Replace <kuadrant-system> with the namespace you used.
3. Confirm that the Connectivity Link installation has finished by running one of the following
commands:
Expect the status of installplan.operators.coreos.com/install-<suffix> when Connectivity
Link is ready. The name of the install plan has a random suffix, for example, 4rql7.
4. Create your Connectivity Link custom resource (CR) by running the following command:
Replace <kuadrant-system> with the namespace you used.
Verification
Check the status of the Connectivity Link CR generation by running the following command:
Replace <kuadrant-system> with the namespace you used.
Example output
### CONFIGURING DNS PROVIDER CREDENTIALS FOR AWS
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
$ oc wait --for=jsonpath={.status.installPlanRef.name} subscription rhcl-operator --
timeout=10s
ip=$(oc get subscription rhcl-operator -o=jsonpath={.status.installPlanRef.name})
$ oc wait --for=condition=Installed installplan ${ip} --timeout=60s
$ oc apply -f - <<EOF
apiVersion: kuadrant.io/v1beta1
kind: Kuadrant
metadata:
  name: kuadrant
  namespace: <kuadrant-system>
EOF
$ oc wait kuadrant/kuadrant --for="condition=Ready=true" -n <kuadrant-system> --
timeout=300s
kuadrant.kuadrant.io/kuadrant Ready
Red Hat Connectivity Link 1.3 Installing on OpenShift Container Platform
12

If you want to configure AWS DNS policies in Connectivity Link, you must configure the DNS
credentials. You must perform the steps on each OpenShift Container Platform cluster that you want to
use Connectivity Link on.
You must configure a DNS hosted zone. The credentials for your DNS provider must have permissions
to update DNS records within this zone.
Prerequisites
You installed Connectivity Link on an OpenShift Container Platform cluster.
You have access to the namespace of your gateway, for example, api-gateway.
NOTE
If you already know your environment variable values, you can create the required
YAML files as required for your use case.
Procedure
1. Optional: Set up your environment variables as follows:
a. Assign AWS_ACCESS_KEY_ID, which is the key ID from AWS with Route 53 access:
b. Assign AWS_SECRET_ACCESS_KEY`, which is the key from AWS with Route 53 access.
c. Assign AWS_REGION, which is your AWS region, for example, us-east-2 or eu-west-1.
2. Create a Secret resource for your credentials as follows:
IMPORTANT
You must configure the secret in the same namespace as your gateway.
Additional resources
Amazon Route 53 documentation
Configuring a DNS Provider - AWS IAM Permissions Required
$ export AWS_ACCESS_KEY_ID=xxxxxxx
$ export AWS_SECRET_ACCESS_KEY=xxxxxxx
$ export AWS_REGION=your-aws-region
$ oc create secret generic aws-credentials \
  --namespace=api-gateway \
  --type=kuadrant.io/aws \
  --from-literal=AWS_ACCESS_KEY_ID=$AWS_ACCESS_KEY_ID \
  --from-literal=AWS_SECRET_ACCESS_KEY=$AWS_SECRET_ACCESS_KEY \
  --from-literal=AWS_REGION=$AWS_REGION
## INSTALLING ON OPENSHIFT CONTAINER PLATFORM
13
### CONFIGURING GOOGLE DNS PROVIDER CREDENTIALS
If you want to configure DNS policies in Connectivity Link using Google Cloud, you must configure the
DNS credentials. You must perform the steps on each OpenShift Container Platform cluster that you
want to use Connectivity Link on.
You must configure a DNS hosted zone. The credentials for your DNS provider must have permissions
to update DNS records within this zone.
Prerequisites
You installed Connectivity Link on an OpenShift Container Platform cluster.
You have access to the namespace of your gateway, for example, api-gateway.
NOTE
If you already know your environment variable values, you can create the required
YAML files as required for your use case.
Procedure
1. Optional: Specify your GOOGLE environment variable by running the following commands:
where:
GOOGLE: The GOOGLE variable specifies the JSON credentials generated by the gcloud
CLI or by the service account. For example, 
$HOME/.config/gcloud/application_default_credentials.json, which has the following
credentials:
2. Optional: Specify your PROJECT_ID environment variable by running the following commands:
PROJECT_ID: Google project ID.
3. Create a Secret resource for your credentials by running the following command:
IMPORTANT
You must configure the secret in the same namespace as your gateway.
$ export GOOGLE=xxxxxxx
{"client_id": "***","client_secret": "***","refresh_token": "***","type": "authorized_user"}
$ export PROJECT_ID=xxxxxxx
$ oc create secret generic test-gcp-credentials \
  --namespace=api-gateway \
  --type=kuadrant.io/gcp \
  --from-literal=PROJECT_ID=$PROJECT_ID \
  --from-file=GOOGLE=$GOOGLE
Red Hat Connectivity Link 1.3 Installing on OpenShift Container Platform
14

Additional resources
Google Cloud DNS documentation
### CONFIGURING AZURE DNS PROVIDER CREDENTIALS
If you want to configure Microsoft Azure DNS policies in Connectivity Link, you must configure the DNS
credentials. You must perform the steps on each OpenShift Container Platform cluster that you want to
use Connectivity Link on.
You must configure a DNS hosted zone. The credentials for your DNS provider must have permissions
to update DNS records within this zone.
Prerequisites
You installed Connectivity Link on an OpenShift Container Platform cluster.
You have access to the namespace of your gateway, for example, api-gateway.
NOTE
If you already know your environment variable values, you can create the required
YAML files as required for your use case.
Procedure
1. Create a new Azure service principal for managing DNS by setting the following environment
variables:
For more details on service principals, see the Microsoft Azure documentation.
2. Set the resource group environment variable by running the following command:
Replace "ExampleDNSResourceGroup" with the DNS resource group that you want to use.
3. To grant read and contributor access to the zones that you want managed for the service
principal you are using, perform the following steps:
a. Fetch the DNS ID used to grant access to the service principal as follows:
b. Get your resource group ID by running the following command:
$ DNS_NEW_SP_NAME=kuadrantDnsPrincipal \
  DNS_SP=$(az ad sp create-for-rbac --name $DNS_NEW_SP_NAME) \
  DNS_SP_APP_ID=$(echo $DNS_SP | jq -r '.appId') \
  DNS_SP_PASSWORD=$(echo $DNS_SP | jq -r '.password')
$ DNS_RESOURCE_GROUP="ExampleDNSResourceGroup"
$ DNS_ID=$(az network dns zone show --name example.com \
 --resource-group $DNS_RESOURCE_GROUP --query "id" --output tsv)
$ RESOURCE_GROUP_ID=$(az group show --resource-group 
$DNS_RESOURCE_GROUP | jq ".id" -r)
## INSTALLING ON OPENSHIFT CONTAINER PLATFORM
15

c. Give reader access to the resource group as follows:
d. Give contributor access to the DNS zone as follows:
4. Because you are setting up advanced traffic rules for geographic and weighted responses, you
must also grant traffic manager and DNS zone access:
a. Create the role assignment for the traffic manager contributor by running the following
command:
b. Create the role assignment for the DNA zone contributor by running the following
command:
c. Configure the DNS zone access by running the following command:
5. Create a Secret resource for your credentials by running the following command:
IMPORTANT
You must configure the secret in the same namespace as your gateway.
Additional resources
Microsoft Azure DNS documentation
$ az role assignment create --role "Reader" --assignee $DNS_SP_APP_ID --scope 
$DNS_ID
$ az role assignment create --role "Contributor" --assignee $DNS_SP_APP_ID --scope 
$DNS_ID
$ az role assignment create --role "Traffic Manager Contributor" --assignee 
$DNS_SP_APP_ID --scope $RESOURCE_GROUP_ID
$ az role assignment create --role "DNS Zone Contributor" --assignee 
$DNS_SP_APP_ID --scope $RESOURCE_GROUP_ID
$ cat <<-EOF > /local/path/to/azure.json
{
  "tenantId": "$(az account show --query tenantId -o tsv)",
  "subscriptionId": "$(az account show --query id -o tsv)",
  "resourceGroup": "$DNS_RESOURCE_GROUP",
  "aadClientId": "$DNS_SP_APP_ID",
  "aadClientSecret": "$DNS_SP_PASSWORD"
}
EOF
$ oc create secret generic test-azure-credentials \
  --namespace=api-gateway \
  --type=kuadrant.io/azure \
  --from-file=azure.json=/local/path/to/azure.json
Red Hat Connectivity Link 1.3 Installing on OpenShift Container Platform
16
### CONFIGURING REDIS STORAGE FOR RATE LIMITING
To configure persistence for rate limit counters in a multicluster environment, you must configure the
connection details for your shared Redis-based datastore. This datastore is used to persist shared rate
limit counters for the Limitador component of Connectivity Link.
IMPORTANT
You must configure connection details for your shared Redis-based datastore on each
OpenShift Container Platform cluster that you want to use Connectivity Link for rate
limiting.
Prerequisites
You installed Connectivity Link on one or more clusters.
You have a shared Redis-based datastore.
You installed the OpenShift CLI (oc).
You have write access to the OpenShift Container Platform namespaces you need to work with.
You have access to external or on-premise DNS.
You created a gateway.
You configured your gateway policies and HTTP routes.
Procedure
1. Set the following environment variable to your shared Redis-based instance URL:
Include the appropriate URI scheme for your environment:
Secure Redis: rediss://
Standard Redis: redis://
2. Create a Secret resource for your Redis URL as follows:
3. Update your Limitador custom resource to use the secret that you created as follows:
$ export REDIS_URL=rediss://user:xxxxxx@some-redis.com:10340
$ oc -n kuadrant-system create secret generic redis-config \
  --from-literal=URL=$REDIS_URL
$ oc patch limitador limitador --type=merge -n kuadrant-system -p '
spec:
  storage:
    redis:
      configSecretRef:
        name: redis-config
'
## INSTALLING ON OPENSHIFT CONTAINER PLATFORM
17

Additional resources
Redis documentation
Valkey documentation
AWS ElastiCache (Redis OSS) User Guide
Dragonfly documentation
### ENABLING THE CONNECTIVITY LINK DYNAMIC PLUGIN FOR
OPENSHIFT CONTAINER PLATFORM WEB CONSOLE
You can use the Connectivity Link dynamic plugin to view and manage your gateways and policies in the
OpenShift Container Platform web console. You must perform these steps on each OpenShift
Container Platform cluster.
Prerequisites
You are using a supported configuration of OpenShift Container Platform and required
components.
You are logged into OpenShift Container Platform as a cluster administrator.
You are logged into the OpenShift Container Platform web console with administrator access.
Procedure
1. In the left navigation menu, select the Administrator perspective.
2. Click Home > Overview.
3. In the Status panel, click Dynamic Plugins > View all.
4. On the Console plugins tab, find the kuadrant-console-plugin entry in the table, which should
be listed but disabled.
5. In the kuadrant-console-plugin row, click Disabled.
6. Select the Enable option, and click Save.
7. Wait for the plugin status to change to Loaded.
Verification
1. Refresh the OpenShift Container Platform web console. A new Connectivity Link menu item is
displayed in the navigation sidebar.
a. You can click Connectivity Link > Overview to explore the available resources and to get
started with creating a Gateway and configuring policies in the OpenShift Container
Platform web console.
Next steps
Create a gateway.
Red Hat Connectivity Link 1.3 Installing on OpenShift Container Platform
18

Create policies.
### USING YOUR RED HAT SUBSCRIPTION
Red Hat Connectivity Link is provided through a software subscription. To manage your subscriptions,
access your account at the Red Hat Customer Portal.
1. Go to access.redhat.com.
2. If you do not already have an account, create one.
3. Log in to your account.
4. In the menu bar, click Subscriptions to view and manage your subscriptions.
### ADDITIONAL RESOURCES
Supported Configurations for Red Hat Connectivity Link
OpenShift Operators
## INSTALLING ON OPENSHIFT CONTAINER PLATFORM
19

---

## Connectivity Link Gateway Policies

Red Hat Connectivity Link 1.3
Configuring and deploying gateway policies
Secure, protect, and connect APIs on OpenShift
Last Updated: 2026-03-26

Red Hat Connectivity Link 1.3 Configuring and deploying gateway policies
Secure, protect, and connect APIs on OpenShift

Legal Notice
Copyright © Red Hat.
Except as otherwise noted below, the text of and illustrations in this documentation are licensed by
Red Hat under the Creative Commons Attribution–Share Alike 3.0 Unported license . If you
distribute this document or an adaptation of it, you must provide the URL for the original version.
Red Hat, as the licensor of this document, waives the right to enforce, and agrees not to assert,
Section 4d of CC-BY-SA to the fullest extent permitted by applicable law.
Red Hat, the Red Hat logo, JBoss, Hibernate, and RHCE are trademarks or registered trademarks of
Red Hat, LLC. or its subsidiaries in the United States and other countries.
Linux ® is the registered trademark of Linus Torvalds in the United States and other countries.
XFS is a trademark or registered trademark of Hewlett Packard Enterprise Development LP or its
subsidiaries in the United States and other countries.
The OpenStack ® Word Mark and OpenStack logo are trademarks or registered trademarks of the
Linux Foundation, used under license.
All other trademarks are the property of their respective owners.
Abstract
This guide explains how to use Connectivity Link policies on OpenShift to secure, protect, and
connect an application API exposed by a Gateway based on Kubernetes Gateway API. This includes
Gateways deployed on a single OpenShift cluster or distributed across multiple clusters.

. . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 
. . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 
Table of Contents
## CONFIGURING AND DEPLOYING GATEWAY POLICIES
### SECURE, PROTECT, AND CONNECT APIS ON OPENSHIFT CONTAINER PLATFORM WITH CONNECTIVITY
LINK
1.1.1. Connectivity Link capabilities in multicluster environments
1.1.2. Connectivity Link user role workflows
### SET UP YOUR ENVIRONMENT
### SET UP A DNS PROVIDER SECRET
### ADD A TLS CERTIFICATE ISSUER
### CREATING A GATEWAY
### CONFIGURE YOUR GATEWAY POLICIES AND HTTP ROUTE
1.6.1. Create an HTTP route for your application
1.6.2. Set the default AuthPolicy
1.6.3. Set the default RateLimitPolicy
1.6.4. Set the DNS policy
1.6.5. Test your default rate limit and auth policies
### ABOUT TOKEN-BASED RATE LIMITING WITH TOKENRATELIMITPOLICY
1.7.1. How token rate limiting works
1.7.2. Key features and use cases
1.7.3. Integrating with AuthPolicy
### CONFIGURE TOKEN-BASED RATE LIMITING WITH TOKENRATELIMITPOLICY
1.8.1. How token rate limiting works
1.8.2. Key features and use cases
1.8.3. Integrating with AuthPolicy
1.8.4. Configure token-based rate limiting for LLM APIs
### OVERRIDE YOUR GATEWAY POLICIES FOR AUTH AND RATE LIMITING
### OVERRIDING THE LOW-LIMIT RATELIMITPOLICY FOR SPECIFIC USERS
### ADDITIONAL RESOURCES
## USING ON-PREMISE DNS WITH COREDNS
### ABOUT USING ON-PREMISE DNS WITH COREDNS
### COREDNS INTEGRATION ARCHITECTURE
2.2.1. Technical workflow
### COREDNS DNS RECORDS SECURITY CONSIDERATIONS
### USING COREDNS WITH A SINGLE CLUSTER
### USING COREDNS WITH PRIMARY AND SECONDARY CLUSTERS
### COREDNS COREFILE CONFIGURATION REFERENCE
2.6.1. Default enabled plugins in CoreDNS
### TROUBLESHOOTING COREDNS WITH THE KUADRANT PLUGIN
### COREDNS REMOVAL OR MIGRATION
### ADDITIONAL RESOURCES
3
3
3
3
4
5
6
7
8
10
10
11
12
13
13
13
14
14
14
14
15
15
15
17
19
21
22
22
22
23
24
25
28
32
33
35
35
35
Table of Contents
1

Red Hat Connectivity Link 1.3 Configuring and deploying gateway policies
2
## CONFIGURING AND DEPLOYING GATEWAY
POLICIES
As a platform engineer or application developer, you can secure, protect, and connect an API exposed
by a gateway that uses Gateway API by using Connectivity Link.
### SECURE, PROTECT, AND CONNECT APIS ON OPENSHIFT
CONTAINER PLATFORM WITH CONNECTIVITY LINK
This guide shows how you can use Connectivity Link on OpenShift Container Platform to secure,
protect, and connect an API exposed by a Gateway that uses Kubernetes Gateway API. This guide
applies to the platform engineer and application developer user roles in Connectivity Link.
IMPORTANT
In multi-cluster environments, you must perform the following steps in each cluster
individually, unless specifically excluded.
1.1.1. Connectivity Link capabilities in multicluster environments
You can use Connectivity Link capabilities in single or multiple OpenShift Container Platform clusters.
The following features are designed to work across multiple clusters and in a single-cluster environment:
Multicluster ingress: Connectivity Link provides multicluster ingress connectivity using DNS to
bring traffic to your gateways by using a strategy defined in a DNSPolicy.
Global rate limiting: Connectivity Link can enable global rate limiting use cases when configured
to use a shared Redis-based store for counters based on limits defined by a RateLimitPolicy.
Global auth: You can configure a Connectivity Link AuthPolicy to use external auth providers to
ensure that different clusters exposing the same API can authenticate and allow in the same
way.
Automatic TLS certificate generation: You can configure a TLSPolicy to automatically provision
TLS certificates based on Gateway listener hosts by using integration with cert-manager and
ACME providers such as Let’s Encrypt.
Integration with federated metrics stores: Connectivity Link has example dashboards and
metrics for visualizing your gateways and observing traffic hitting those gateways across
multiple clusters.
1.1.2. Connectivity Link user role workflows
Platform engineer: This guide shows how platform engineers can deploy gateways that provide
secure communication and are protected and ready for use by application development teams
to deploy APIs.
Platform engineers can use Connectivity Link in clusters in different geographic regions to bring
specific traffic to geo-located gateways. This approach reduces latency, distributes load, and
protects and secures with global rate limiting and auth policies.
Application developer: This guide shows how application developers can override the
Gateway-level global auth and rate limiting policies to configure application-level auth and rate
limiting requirements for specific users.
## CONFIGURING AND DEPLOYING GATEWAY POLICIES
3
### SET UP YOUR ENVIRONMENT
This section shows how you can set up your environment variables and deploy the example Toystore
application on your OpenShift Container Platform cluster.
Prerequisites
Connectivity Link is installed on the OpenShift Container Platform cluster you are working with.
The OpenShift CLI (oc) is installed.
You are logged in to the OpenShift Container Platform cluster with write access to the
namespaces you need to use.
You have a DNS zone available for use.
Optional. For rate limiting in a multicluster environment, you have installed Connectivity Link on
more than one cluster. You also have a shared and accessible Redis-based datastore.
Optional. For observability, OpenShift Container Platform user workload monitoring is
configured to remote-write to a central storage system.
Procedure
1. Optional: Set the following environment variables:
These environment variables are described as follows:
KUADRANT_GATEWAY_NS: Namespace for your gateway in OpenShift Container
Platform.
KUADRANT_GATEWAY_NAME: Name of your ingress gateway in OpenShift Container
Platform.
KUADRANT_DEVELOPER_NS: Namespace for the example Toystore app in OpenShift
Container Platform.
KUADRANT_AWS_ACCESS_KEY_ID: AWS key ID with access to manage your DNS zone.
KUADRANT_AWS_SECRET_ACCESS_KEY: AWS secret access key with permissions to
manage your DNS zone.
KUADRANT_AWS_DNS_PUBLIC_ZONE_ID: AWS Route 53 zone ID for the Gateway. This
is the ID of the hosted zone that is displayed in the AWS Route 53 console.
KUADRANT_ZONE_ROOT_DOMAIN: Root domain in AWS Route 53 associated with your
DNS zone ID.
$ export KUADRANT_GATEWAY_NS=api-gateway \
  export KUADRANT_GATEWAY_NAME=ingress-gateway \
  export KUADRANT_DEVELOPER_NS=toystore \
  export KUADRANT_AWS_ACCESS_KEY_ID=xxxx \
  export KUADRANT_AWS_SECRET_ACCESS_KEY=xxxx \
  export KUADRANT_AWS_DNS_PUBLIC_ZONE_ID=xxxx \
  export KUADRANT_ZONE_ROOT_DOMAIN=example.com
  export KUADRANT_CLUSTER_ISSUER_NAME=self-signed
Red Hat Connectivity Link 1.3 Configuring and deploying gateway policies
4

KUADRANT_CLUSTER_ISSUER_NAME: Name of the certificate authority or issuer TLS
certificates.
NOTE
If you know your environment variable values, you can set up the required
YAML files to suit your environment.
1. Create the namespace for the Toystore app as follows:
1. Deploy the Toystore app to the developer namespace:
### SET UP A DNS PROVIDER SECRET
Your DNS provider supplies credentials to access the DNS zones that Connectivity Link can use to set
up your DNS configuration. You must ensure that these credentials have access to only the DNS zones
that you want Connectivity Link to manage with your DNSPolicy.
NOTE
You must apply the following Secret resource to each cluster. If you are adding an
additional cluster, add it to the new cluster.
Prerequisites
You installed Connectivity Link on one or more clusters.
If you plan to use rate-limiting in a multicluster environment, you have a shared Redis-based
datastore.
You installed the OpenShift CLI (oc).
You have write access to the OpenShift Container Platform namespaces you need to work with.
You have access to external or on-premise DNS.
You created a gateway.
You configured your gateway policies and HTTP routes.
Procedure
1. Create the namespace that the Gateway will be deployed in as follows:
2. Create the secret credentials in the same namespace as the Gateway as follows:
$ oc create ns ${KUADRANT_DEVELOPER_NS}
$ oc apply -f https://raw.githubusercontent.com/Kuadrant/Kuadrant-
operator/main/examples/toystore/toystore.yaml -n ${KUADRANT_DEVELOPER_NS}
$ oc create ns ${KUADRANT_GATEWAY_NS}
## CONFIGURING AND DEPLOYING GATEWAY POLICIES
5

3. Before adding a TLS certificate issuer, create the secret credentials in the cert-manager
namespace as follows:
### ADD A TLS CERTIFICATE ISSUER
To secure communication to your Gateways, you must define a certification authority as an issuer for
TLS certificates.
NOTE
This example uses the Let’s Encrypt TLS certificate issuer for simplicity, but you can use
any certificate issuer supported by cert-manager. In multicluster environments, you must
add your TLS issuer in each OpenShift Container Platform cluster.
Prerequisites
You installed Connectivity Link on one or more clusters.
If you plan to use rate-limiting in a multicluster environment, you have a shared Redis-based
datastore.
You installed the OpenShift CLI (oc).
You have write access to the OpenShift Container Platform namespaces you need to work with.
You have access to external or on-premise DNS.
You created a gateway.
You configured your gateway policies and HTTP routes.
Procedure
1. Enter the following command to define a TLS certificate issuer:
$ oc -n ${KUADRANT_GATEWAY_NS} create secret generic aws-credentials \
  --type=kuadrant.io/aws \
  --from-literal=AWS_ACCESS_KEY_ID=$KUADRANT_AWS_ACCESS_KEY_ID \
  --from-
literal=AWS_SECRET_ACCESS_KEY=$KUADRANT_AWS_SECRET_ACCESS_KEY
$ oc -n cert-manager create secret generic aws-credentials \
  --type=kuadrant.io/aws \
  --from-literal=AWS_ACCESS_KEY_ID=$KUADRANT_AWS_ACCESS_KEY_ID \
  --from-
literal=AWS_SECRET_ACCESS_KEY=$KUADRANT_AWS_SECRET_ACCESS_KEY
$ oc apply -f - <<EOF
apiVersion: cert-manager.io/v1
kind: ClusterIssuer
metadata:
  name: ${KUADRANT_CLUSTER_ISSUER_NAME}
Red Hat Connectivity Link 1.3 Configuring and deploying gateway policies
6

2. Wait for the ClusterIssuer to become ready as follows:
### CREATING A GATEWAY
As a platform engineer, create a gateway to your OpenShift Container Platform cluster to set up the
infrastructure used by application developers. The following example assumes that you are using the
OpenShift Container Platform Cluster Ingress Operator (CIO).
IMPORTANT
When using the Gateway API custom resource definitions (CRDs) provided in OpenShift
Container Platform 4.19 or newer, you must create a GatewayClass named openshift-
default and specify a controllerName of openshift.io/gateway-controller/v1. For more
details, see the Getting started with Gateway API for the Ingress Operator (OpenShift
Container Platform documentation).
If you are using OpenShift Service Mesh on OpenShift Container Platform 4.19 and newer and you set
the ISTIO_GATEWAY_CONTROLLER_NAMES variable to istio.io/gateway-controller during your
Connectivity Link installation, then you can use the GatewayClass custom resource (CR) created by
default by OpenShift Service Mesh. Make sure you use the corresponding spec.gatewayClassName
value in your Gateway CR.
Prerequisites
Connectivity Link is installed on the OpenShift Container Platform cluster you are working with.
You set the ISTIO_GATEWAY_CONTROLLER_NAMES environment variable value to 
openshift.io/gateway-controller/v1 during your Connectivity Link installation.
You created a GatewayClass named openshift-default and specified a controllerName of 
openshift.io/gateway-controller/v1.
The OpenShift CLI (oc) is installed.
You are logged in to the OpenShift Container Platform cluster with write access to the
namespaces you need to use.
You have a DNS zone available for use.
Procedure
Create a gateway that uses the OpenShift Container Platform CIO by running the following
command:
spec:
  selfSigned: {}
EOF
$ oc wait clusterissuer/${KUADRANT_CLUSTER_ISSUER_NAME} --
for=condition=ready=true
$ oc apply -f - <<EOF
apiVersion: gateway.networking.k8s.io/v1
## CONFIGURING AND DEPLOYING GATEWAY POLICIES
7

IMPORTANT
In a multicluster environment, for Connectivity Link to balance traffic by using
DNS across clusters, you must specify a gateway with a shared hostname. You
can define this by using an HTTPS listener with a wildcard hostname based on the
root domain.
Verification
1. Check the status of your gateway by running the following command:
The statuses Accepted and Programmed mean that your gateway is valid and assigned an
external address.
2. Check the status of your HTTPS listener by running the following command:
Next steps
Configure a TLS policy so that the The HTTPS listener can accept traffic.
### CONFIGURE YOUR GATEWAY POLICIES AND HTTP ROUTE
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
$ oc get gateway ${KUADRANT_GATEWAY_NAME} -n ${KUADRANT_GATEWAY_NS} -
o=jsonpath='{.status.conditions[?(@.type=="Accepted")].message}{"\n"}{.status.conditions[?
(@.type=="Programmed")].message}'
$ oc get gateway ${KUADRANT_GATEWAY_NAME} -n ${KUADRANT_GATEWAY_NS} -
o=jsonpath='{.status.listeners[0].conditions[?(@.type=="Programmed")].message}'
Red Hat Connectivity Link 1.3 Configuring and deploying gateway policies
8

While your Gateway is now deployed, it has no exposed endpoints and your HTTPS listener is not
programmed. Next, you can do take the following steps:
Define a TLSPolicy that leverages your CertificateIssuer to set up your HTTPS listener
certificates.
Define an HTTPRoute for your Gateway to communicate with your backend application API.
Define an AuthPolicy to set up a default HTTP 403 response for any unprotected endpoints
Define and a RateLimitPolicy to set up a default artificially low global limit to further protect
any endpoints exposed by the Gateway.
Define a DNSPolicy with a load balancing strategy for your Gateway.
IMPORTANT
In multicluster environments, you must perform the following steps in each cluster
individually, unless specifically excluded.
Prerequisites
You installed Connectivity Link on one or more clusters.
If plan to use rate-limiting in a multicluster environment, you have a shared Redis-based
datastore.
You installed the OpenShift CLI (oc).
You have write access to the OpenShift Container Platform namespaces you need to work with.
You have access to external or on-premise DNS.
You created a gateway.
Procedure
1. Set the TLSPolicy for your Gateway as follows:
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
## CONFIGURING AND DEPLOYING GATEWAY POLICIES
9

2. Check that your TLS policy has an Accepted and Enforced status as follows:
This may take a few minutes depending on the TLS provider, for example, Let’s Encrypt.
1.6.1. Create an HTTP route for your application
Procedure
1. Create an HTTPRoute for the example Toystore application as follows:
1.6.2. Set the default AuthPolicy
Procedure
1. Set a default AuthPolicy with a deny-all setting for your Gateway as follows:
$ oc get tlspolicy ${KUADRANT_GATEWAY_NAME}-tls -n ${KUADRANT_GATEWAY_NS} -
o=jsonpath='{.status.conditions[?(@.type=="Accepted")].message}{"\n"}{.status.conditions[?
(@.type=="Enforced")].message}'
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
$ oc apply -f - <<EOF
apiVersion: kuadrant.io/v1
kind: AuthPolicy
metadata:
Red Hat Connectivity Link 1.3 Configuring and deploying gateway policies
10

2. Check that your AuthPolicy has Accepted and Enforced status as follows:
1.6.3. Set the default RateLimitPolicy
Procedure
1. Set the default RateLimitPolicy with a low-limit setting for your Gateway as follows:
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
              "message": "Access denied by default by the gateway operator. If you are the 
administrator of the service, create a specific auth policy for the route."
            }
EOF
$ oc get authpolicy ${KUADRANT_GATEWAY_NAME}-auth -n 
${KUADRANT_GATEWAY_NS} -o=jsonpath='{.status.conditions[?
(@.type=="Accepted")].message}{"\n"}{.status.conditions[?(@.type=="Enforced")].message}'
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
## CONFIGURING AND DEPLOYING GATEWAY POLICIES
11

It might take a few minutes for the RateLimitPolicy to be applied depending on your cluster.
The limit in this example is artificially low to show it working easily.
2. Check that your RateLimitPolicy has Accepted and Enforced status as follows:
1.6.4. Set the DNS policy
Procedure
1. Set the DNSPolicy for your Gateway as follows:
The DNSPolicy uses the DNS Provider Secret that you defined earlier. The geo in this example
is GEO-NA, but you can change this to suit your requirements.
2. Check that your DNSPolicy has status of Accepted and Enforced as follows:
This might take a few minutes.
      "low-limit":
        rates:
        - limit: 1
          window: 10s
EOF
$ oc get ratelimitpolicy ${KUADRANT_GATEWAY_NAME}-rlp -n 
${KUADRANT_GATEWAY_NS} -o=jsonpath='{.status.conditions[?
(@.type=="Accepted")].message}{"\n"}{.status.conditions[?(@.type=="Enforced")].message}'
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
$ oc get dnspolicy ${KUADRANT_GATEWAY_NAME}-dnspolicy -n 
${KUADRANT_GATEWAY_NS} -o=jsonpath='{.status.conditions[?
(@.type=="Accepted")].message}{"\n"}{.status.conditions[?(@.type=="Enforced")].message}'
Red Hat Connectivity Link 1.3 Configuring and deploying gateway policies
12

3. Check the status of the DNS health checks that are enabled on your DNSPolicy as follows:
These health checks flag a published endpoint as healthy or unhealthy based on defined
configuration. When unhealthy, an endpoint will not be published if it has not already been
published to the DNS provider. An endpoint will only be unpublished if it is part of a multi-value
A record, and in all cases can be observed in the DNSPolicy status.
Additional resources
For more information, see the community-based Kuadrant DNS Health Checks
documentation.
1.6.5. Test your default rate limit and auth policies
You can use a curl command to test the default low-limit and deny-all policies for your Gateway.
Procedure
Enter the following curl command:
You should see a HTTP 403 responses.
### ABOUT TOKEN-BASED RATE LIMITING WITH
TOKENRATELIMITPOLICY
Red Hat Connectivity Link provides the TokenRateLimitPolicy custom resource to enforce rate limits
based on token consumption rather than the number of requests. This policy extends the Envoy Rate
Limit Service (RLS) protocol with automatic token usage extraction. It is particularly useful for
protecting Large Language Model (LLM) APIs, where the cost and resource usage correlate more
closely with the number of tokens processed.
Unlike the standard RateLimitPolicy which counts requests, TokenRateLimitPolicy counts tokens by
extracting usage metrics in the body of the AI inference API call, allowing for finer-grained control over
API usage based on actual workload.
1.7.1. How token rate limiting works
The TokenRateLimitPolicy tracks cumulative token usage per client. Before forwarding a request, it
checks if the client has already exceeded their limit from previous usage. After the upstream responds, it
extracts the actual token cost and updates the client’s counter.
The flow is as follows:
1. On an incoming request, the gateway evaluates the matching rules and predicates from the 
TokenRateLimitPolicy resources.
$ oc get dnspolicy ${KUADRANT_GATEWAY_NAME}-dnspolicy -n 
${KUADRANT_GATEWAY_NS} -
$ while :; do curl -k --write-out '%{http_code}\n' --silent --output /dev/null  
"https://api.$KUADRANT_ZONE_ROOT_DOMAIN/cars" | grep -E --color "\b(429)\b|$"; sleep 
1; done
## CONFIGURING AND DEPLOYING GATEWAY POLICIES
13

2. If the request matches, the gateway prepares the necessary rate limit descriptors and monitors
the response.
3. After receiving the response, the gateway extracts the usage.total_tokens field from the
JSON response body.
4. The gateway then sends a RateLimitRequest to Limitador, including the actual token count as a
hits_addend.
5. Limitador tracks the cumulative token usage and responds to the gateway with OK or 
OVER_LIMIT.
1.7.2. Key features and use cases
Enforces limits based on token usage by extracting the usage.total_tokens field from an
OpenAI-style inference JSON response body.
Suitable for consumption-based APIs such as LLMs where the cost is tied to token counts.
Allows defining different limits based on criteria such as user identity, API endpoints, or HTTP
methods.
Works with AuthPolicy to apply specific limits to authenticated users or groups.
Inherits functionalities from RateLimitPolicy, including defining multiple limits with different
durations and using Redis for shared counters in multi-cluster environments.
1.7.3. Integrating with AuthPolicy
You can combine TokenRateLimitPolicy with AuthPolicy to apply token limits based on authenticated
user identity. When an AuthPolicy successfully authenticates a request, it injects identity information
that is used by the TokenRateLimitPolicy to select the appropriate limit.
For example, you can define different token limits for users belonging to 'free-tier' compared to
'premium-tier' groups, identified using claims in a JWT validated by AuthPolicy.
### CONFIGURE TOKEN-BASED RATE LIMITING WITH
TOKENRATELIMITPOLICY
Red Hat Connectivity Link provides the TokenRateLimitPolicy custom resource to enforce rate limits
based on token consumption rather than the number of requests. This policy extends the Envoy Rate
Limit Service (RLS) protocol with automatic token usage extraction. It is particularly useful for
protecting Large Language Model (LLM) APIs, where the cost and resource usage correlate more
closely with the number of tokens processed.
Unlike the standard RateLimitPolicy which counts requests, TokenRateLimitPolicy counts tokens by
extracting usage metrics in the body of the AI inference API call, allowing for finer-grained control over
API usage based on actual workload.
1.8.1. How token rate limiting works
The TokenRateLimitPolicy tracks cumulative token usage per client. Before forwarding a request, it
checks if the client has already exceeded their limit from previous usage. After the upstream responds, it
extracts the actual token cost and updates the client’s counter.
Red Hat Connectivity Link 1.3 Configuring and deploying gateway policies
14

The flow is as follows:
1. On an incoming request, the gateway evaluates the matching rules and predicates from the 
TokenRateLimitPolicy resources.
2. If the request matches, the gateway prepares the necessary rate limit descriptors and monitors
the response.
3. After receiving the response, the gateway extracts the usage.total_tokens field from the
JSON response body.
4. The gateway then sends a RateLimitRequest to Limitador, including the actual token count as a
hits_addend.
5. Limitador tracks the cumulative token usage and responds to the gateway with OK or 
OVER_LIMIT.
1.8.2. Key features and use cases
Token-based rate limiting means you complete the following tasks:
Enforces limits based on token usage by extracting the usage.total_tokens field from an
OpenAI-style inference JSON response body.
Suitable for consumption-based APIs such as LLMs where the cost is tied to token counts.
Allows defining different limits based on criteria such as user identity, API endpoints, or HTTP
methods.
Works with AuthPolicy to apply specific limits to authenticated users or groups.
Inherits functionalities from RateLimitPolicy, including defining multiple limits with different
durations and using Redis for shared counters in multi-cluster environments.
1.8.3. Integrating with AuthPolicy
You can combine TokenRateLimitPolicy with AuthPolicy to apply token limits based on authenticated
user identity. When an AuthPolicy successfully authenticates a request, it injects identity information
which can then be used by the TokenRateLimitPolicy to select the appropriate limit.
For example, you can define different token limits for users belonging to 'free-tier' versus 'premium-tier'
groups, identified using claims in a JWT validated by AuthPolicy.
1.8.4. Configure token-based rate limiting for LLM APIs
This guide shows how to configure TokenRateLimitPolicy to You can protect a hypothetical LLM API
deployed on OpenShift Container Platform, integrated with AuthPolicy for user-specific limits.
Prerequisites
Connectivity Link is installed on your OpenShift Container Platform cluster.
A Gateway and an HTTPRoute are configured to expose your service.
An AuthPolicy is configured for authentication (for example, using API keys or OIDC).
## CONFIGURING AND DEPLOYING GATEWAY POLICIES
15

Redis is configured for Limitador if running in a multi-cluster setup or requiring persistent
counters.
Your upstream service is configured to return an OpenAI-compatible JSON response containing
a usage.total_tokens field in the response body.
Procedure
1. Create a TokenRateLimitPolicy resource. This example defines two limits: one for free users
on a 10,000 tokens per day request limit, and one for pro users with a 100,000 tokens per day
request limit.
2. Apply the policy:
3. Check the status of the policy to ensure it has been accepted and enforced on the target 
HTTPRoute. Look for conditions with type: Accepted and type: Enforced with status: "True".
4. Send requests to your API endpoint, including the required authentication details.
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
$ oc apply -f your-tokenratelimitpolicy.yaml -n my-api-namespace
$ oc get tokenratelimitpolicy llm-protection -n my-api-namespace -o 
jsonpath='{.status.conditions}'
Red Hat Connectivity Link 1.3 Configuring and deploying gateway policies
16

Verification
Ensure that your upstream service responds with an OpenAI-compatible JSON body containing
the usage.total_tokens field.
Requests made when the client is within their token limits should receive a 200 OK response or
other success status and their token counter is updated.
Requests made when the client has already exceeded their token limits should receive a 429 Too 
Many Requests response.
### OVERRIDE YOUR GATEWAY POLICIES FOR AUTH AND RATE
LIMITING
As an application developer, you can override your existing gateway-level policies to configure your
application-level auth and rate limiting requirements.
You can allow authenticated access to the Toystore API by defining a new AuthPolicy that targets the 
HTTPRoute resource created in the previous section.
IMPORTANT
Any new HTTPRoutes are affected by the existing gateway-level policy. Because you
want users to now access this API, you must override that gateway policy. For simplicity,
you can use API keys to authenticate the requests, but other options such as OpenID
Connect are also available.
Prerequisites
Connectivity Link is installed.
You configured Connectivity Link policies.
You installed the OpenShift CLI (oc).
You are logged into OpenShift Container Platform as a cluster administrator.
Procedure
1. Ensure that your Connectivity Link system namespace is set correctly by running the following
command:
2. Define API keys for bob and alice users as follows:
$ curl -H "Authorization: <auth-details>" \
     -d '{"model": "gpt-4", "messages": [{"role": "user", "content": "Hello"}]}' \
     <your-api-endpoint>
$ export KUADRANT_SYSTEM_NS=$(oc get kuadrant -A -o jsonpath="
{.items[0].metadata.namespace}")
$ oc apply -f - <<EOF
apiVersion: v1
## CONFIGURING AND DEPLOYING GATEWAY POLICIES
17

3. Create a new AuthPolicy in a different namespace that overrides the deny-all policy created
earlier and accepts the API keys as follows:
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
Red Hat Connectivity Link 1.3 Configuring and deploying gateway policies
18
### OVERRIDING THE LOW-LIMIT RATELIMITPOLICY FOR SPECIFIC
USERS
The configured Gateway limits provide a good set of limits for the general case. However, as the
developer of the Toystore API, you might want to only allow a certain number of requests for specific
users, and a general limit for all other users.
IMPORTANT
Any new HTTPRoutes are affected by the existing Gateway-level policy. Because you
want users to now access this API, you must override that Gateway policy. For simplicity,
you can use API keys to authenticate the requests, but other options such as OpenID
Connect are also available.
Prerequisites
You installed Connectivity Link on one or more clusters.
If you plan to use rate-limiting in a multicluster environment, you have a shared Redis-based
datastore.
You installed the OpenShift CLI (oc).
You have write access to the OpenShift Container Platform namespaces you need to work with.
You have access to external or on-premise DNS.
You created a gateway.
You configured your gateway policies and HTTP routes.
Procedure
1. Create a new RateLimitPolicy in a different namespace to override the default low-limit policy
created previously and set rate limits for specific users as follows:
      success:
        filters:
          "identity":
            json:
              properties:
                "userid":
                  selector: auth.identity.metadata.annotations.secret\.kuadrant\.io/user-id
EOF
$ oc apply -f - <<EOF
apiVersion: kuadrant.io/v1
kind: RateLimitPolicy
metadata:
  name: toystore-rlp
  namespace: ${KUADRANT_DEVELOPER_NS}
spec:
  targetRef:
    group: gateway.networking.k8s.io
## CONFIGURING AND DEPLOYING GATEWAY POLICIES
19

It might take a few minutes for the RateLimitPolicy to be applied, depending on your cluster.
2. Check that the RateLimitPolicy has a status of Accepted and Enforced as follows:
3. Check that the status of the HTTPRoute is now affected by the RateLimitPolicy in the same
namespace:
Verification
1. Send requests as user alice as follows:
You should see HTTP status 200 every second for 5 seconds, followed by HTTP status 429
every second for 5 seconds.
2. Send requests as user bob as follows:
You should see HTTP status 200 every second for 2 seconds, followed by HTTP status 429
every second for 8 seconds.
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
$ oc get ratelimitpolicy -n ${KUADRANT_DEVELOPER_NS} toystore-rlp -
o=jsonpath='{.status.conditions[?(@.type=="Accepted")].message}{"\n"}{.status.conditions[?
(@.type=="Enforced")].message}'
$ oc get httproute toystore -n ${KUADRANT_DEVELOPER_NS} -
o=jsonpath='{.status.parents[0].conditions[?
(@.type=="kuadrant.io/RateLimitPolicyAffected")].message}'
$ while :; do curl -k --write-out '%{http_code}\n' --silent --output /dev/null -H 'Authorization: 
APIKEY IAMALICE' "https://api.$KUADRANT_ZONE_ROOT_DOMAIN/cars" | grep -E --color 
"\b(429)\b|$"; sleep 1; done
$ while :; do curl -k --write-out '%{http_code}\n' --silent --output /dev/null -H 'Authorization: 
APIKEY IAMBOB' "https://api.$KUADRANT_ZONE_ROOT_DOMAIN/cars" | grep -E --color 
"\b(429)\b|$"; sleep 1; done
Red Hat Connectivity Link 1.3 Configuring and deploying gateway policies
20
### ADDITIONAL RESOURCES
Load balancing with MetalLB
## CONFIGURING AND DEPLOYING GATEWAY POLICIES
21
## USING ON-PREMISE DNS WITH COREDNS
You can secure, protect, and connect an API exposed by a gateway that uses Gateway API by using
Connectivity Link.
### ABOUT USING ON-PREMISE DNS WITH COREDNS
You can self-manage your on-premise DNS by integrating CoreDNS with your DNS infrastructure
through access control and zone delegation. Connectivity Link combines the DNS Operator with
CoreDNS to simplify your management and security for on-premise DNS servers. You can use CoreDNS
in both single-cluster and multi-cluster scenarios.
CoreDNS is best used in environments that change often, where using a DNS-as-code approach makes
sense. The following situations are example use cases for integrating with CoreDNS:
You need to avoid dependency on external cloud DNS services.
You have regulatory or compliance requirements mandating self-hosted infrastructure.
You need to keep full control over DNS records.
You want to delegate specific DNS zones from existing DNS servers to Kubernetes-managed
CoreDNS.
You require consistent DNS management across hybrid or multicloud environments.
You need to reduce DNS operational costs by eliminating per-query charges.
You do not want to directly manage DNS records on the on-premise DNS server.
You need to keep authoritative control on edge DNS servers.
For example:
Configure your authoritative on-premise DNS server to delegate a specific subdomain, such as 
deployment.example.local, to CoreDNS instances managed by Connectivity Link.
Any pod-level dnsPolicy CR can then interact with the CoreDNS provider within the OpenShift
Container Platform cluster. You can specify the DNS provider that handles the records for the
targeted gateways in the delegate field of the DNS policy.
The CoreDNS instance becomes authoritative for the delegated subdomain and manages the
necessary DNS records for gateways within that subdomain.
### COREDNS INTEGRATION ARCHITECTURE
CoreDNS is a DNS server that consists of default plugins that do several tasks, for example:
Automatically detects when you add new services to your cluster and adds them to directories.
Caches recent addresses to avoid the latency of repeated lookups.
Runs health checks and skips over services that are down.
Provides dynamic redirects by rewriting queries as they come in.
Red Hat Connectivity Link 1.3 Configuring and deploying gateway policies
22

You can add plugins for observability and other services that you require by updating the CoreDNS with
the DNS Operator.
With the DNS Operator, DNS is the first layer of traffic management. You can deploy the DNS Operator
to multiple clusters and coordinate them all on a given zone. This means that you can use a shared
domain name across clusters to balance traffic based on your requirements.
2.2.1. Technical workflow
To give you integration with CoreDNS, Connectivity Link extends the DNS Operator with the kuadrant
CoreDNS plugin that sources records from the kuadrant.io/v1alpha1/DNSRecord custom resource
(CR) and applies location-based and weighted response capabilities.
You can create DNS records that point to the CoreDNS secret in one of the three following ways:
Create it manually.
Use a non-delegating DNS policy at a gateway with routes attached. The kuadrant-operator
CR creates DNSRecord CRs with the secret.
Use a delegating DNS policy at a gateway. The delegating policy results in the creation of a
delegating DNSRecord CR without a secret reference. All delegating DNS Records are
combined into a single authoritative DNS Record. The authoritative DNSRecord uses a default
provider secret.
The DNS Operator reconciles authoritative records that have the CoreDNS secret referenced and
applies labels only to those CRs. CoreDNS watches those records and matches the labels with zones
configured in the Corefile. If there is a match, the authoritative DNSRecord CR is used to serve a DNS
response.
There are no changes to the dnsPolicy API and no required changes to the policy controllers. This
integration is isolated to the DNS Operator and the CoreDNS plugin.
The CoreDNS integration supports both single-cluster and multi-cluster deployments.
Single cluster
Organizations that want to self-host their DNS infrastructure without the complexity of multi-cluster
coordination can use single-cluster CoreDNS integration. Using delegation is not required.
A single cluster runs both DNS Operator and CoreDNS with the plugin. CoreDNS only serves 
DNSRecord CRs that point to a CoreDNS provider secret. The CoreDNS plugin watches for DNS
records labeled with the appropriate zone name and serves them directly. Any authoritative 
DNSRecord CR has endpoints from the single cluster.
Multi-cluster delegation
Multiple clusters can participate in serving a single DNS zone through Kubernetes role-based
delegation that enables geographic distribution of DNS services and high availability. This
implementation enables workloads across multiple clusters to contribute DNS endpoints to a unified
zone, with primary clusters maintaining the authoritative view. The role of a cluster is determined by
the DNS Operator.
Multi-cluster delegation uses kubeconfig-based interconnection secrets that grant read access to 
DNSRecord resources across clusters. This approach reuses Kubernetes role-based access (RBAC).
Primary clusters: Run both the DNS Operator and CoreDNS and serve the DNS records that
are local. The DNS Operator running on primary clusters that delegate reconciles 
## USING ON-PREMISE DNS WITH COREDNS
23

DNSRecord CRs by reading and merging them. Primary clusters then serve these
authoritative DNSRecord CRs. Each CoreDNS instance serves the relevant authoritative 
DNSRecord for the configured zone. Each primary cluster can independently serve the
complete record set.
Secondary clusters: Only run the DNS Operator. These clusters create delegating 
DNSRecord CRs but do not interact with DNS providers directly. If the secret and
subdomain are properly configured, these DNS records are automatically reconciled in the
primary cluster.
Zone labeling
CoreDNS integration uses a label-based filtering mechanism. The DNS Operator applies a zone-
specific label to DNSRecord CRs when the CRs are reconciled. The CoreDNS plugin only watches
for DNSRecord CRs with labels that match configured zones. This method reduces resource use
and provides clear zone boundaries.
GEO and weighted routing
GEO and weighted routing use the same algorithmic approach as cloud providers. By using CoreDNS,
you can have parity with cloud DNS provider capabilities and maintain full control over your DNS
infrastructure.
GEO routing: The CoreDNS geoip plugin uses geographical-database integration to return
region-specific endpoints.
Weighted routing: Applies probabilistic selection based on endpoint weights.
Combined routing: First applies GEO filtering, then weighted selection within the matched
region.
### COREDNS DNS RECORDS SECURITY CONSIDERATIONS
As an infrastructure engineer or business lead, you can implement several security best practices when
using CoreDNS with Connectivity Link.
Zone configuration DNSRecord custom resources (CRs) have full control over a Zone’s name server
(NS) records. Anyone who can create or change a DNSRecord that targets the root of the main domain
name with NS records can decide where the all Zone traffic goes. Consider this as you plan your access
controls.
For example, use the following access-control best practices:
Separate namespaces: Keep zone configuration DNSRecord CRs in a dedicated, restricted
namespace
Use least-privilege policies:
Strict RBAC: Only grant DNSRecord creation permissions to trusted infrastructure
engineers and cluster administrators.
Namespace isolation: Grant application developers DNSRecord permissions only in their
own namespaces.
Audit logging: Enable Kubernetes audit logging to track all DNSRecord changes. CoreDNS audit
logging is enabled by default for network troubleshooting and traffic pattern observability.
Red Hat Connectivity Link 1.3 Configuring and deploying gateway policies
24

Version control: Use a DNS-as-code approach. Store zone configuration DNSRecord CRs in Git
and use standardized review processes.
You can use the following RBAC configuration example to get you started with defining access:
### USING COREDNS WITH A SINGLE CLUSTER
You can use CoreDNS as a DNS provider for Connectivity Link in a single-cluster, on-premise
environment. This integration allows Connectivity Link to manage DNS entries within your internal
network infrastructure.
IMPORTANT
In a single-cluster setup, ensure that the endpoints IP address value you use is reachable
from the kuadrant-system namespace. The default IP address, 10.96.0.10, is the internal
cluster-wide DNS address.
Prerequisites
Connectivity Link is installed on the OpenShift Container Platform cluster.
The OpenShift CLI (oc) is installed.
You have administrator privileges on the OpenShift Container Platform cluster.
You are logged in to the cluster you want to configure.
Your OpenShift Container Platform clusters have support for the loadbalanced service type
that allows UDP and TCP traffic on port 53, such as MetalLB.
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
## USING ON-PREMISE DNS WITH COREDNS
25

You have access to configure your authoritative on-premise DNS server.
Podman is installed.
Procedure
1. Set up your cluster. Set the following environment variables for your cluster context:
For the ONPREM_DOMAIN variable value, use your actual root domain. For the 
KUADRANT_SUBDOMAIN variable value, valid values are empty or kuadrant.
2. Extract the CoreDNS manifests from dns-operator bundle by running the following commands:
3. Apply the manifests to the cluster by running the following command:
4. Create a ConfigMap to define the authoritative zone for CoreDNS. This minimal configuration
enables the kuadrant plugin and GeoIP features.
$ export CTX_PRIMARY=$(oc config current-context) \
  export KUBECONFIG=~/.kube/config \
  export PRIMARY_CLUSTER_NAME=local-cluster \
  export ONPREM_DOMAIN=<onprem-domain> \
  export KUADRANT_SUBDOMAIN=""
$ podman create --name bundle registry.redhat.io/rhcl-1/dns-operator-bundle:rhcl-1.3.0
$ podman cp bundle:/coredns/manifests.yaml ./coredns-manifests.yaml
$ podman rm bundle
$ oc apply -f ./coredns-manifests.yaml
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
Red Hat Connectivity Link 1.3 Configuring and deploying gateway policies
26

NOTE
For production or exact GeoIP routing, mount your licensed MaxMind GeoIP
database into the CoreDNS pod and update the filename in the 
data.Corefile.geoip parameter.
5. Update the CoreDNS deployment to use the new configuration by running the following
command:
6. Set a watch-and-wait command for the deployment rollout to complete:
Example output
7. Create the Kubernetes Secret that Connectivity Link uses to interact with CoreDNS. This
secret specifies the zones this provider instance is authoritative for.
Verification
Check the status of the DNSRecord CR by running the following commands:
Expect the Ready condition to be True.
Troubleshooting
If you are having undetermined trouble, view the logs for all CoreDNS pods by running the
following command:
$ oc --context $CTX_PRIMARY -n kuadrant-system patch deployment kuadrant-coredns --
patch '{"spec":{"template":{"spec":{"volumes":[{"name":"config-volume","configMap":
{"name":"coredns-kuadrant-config","items":[{"key":"Corefile","path":"Corefile"}]}}]}}}}'
$ oc --context $CTX_PRIMARY -n kuadrant-system rollout status deployment/kuadrant-
coredns
kuadrant-coredns successfully rolled out
$ oc create secret generic coredns-credentials \
  --namespace=kuadrant-system \
  --type=kuadrant.io/coredns \
  --from-literal=ZONES="${KUADRANT_SUBDOMAIN}.${ONPREM_DOMAIN}" \
  --context ${CTX_PRIMARY}
$ oc get dnsrecord <name> -n <namespace> -o jsonpath='{.status.conditions[?
(@.type=="Ready")]}'
$ NS1=$(oc get svc kuadrant-coredns -n kuadrant-coredns -o 
jsonpath='{.status.loadBalancer.ingress[0].ip}')
  ROOT_HOST=$(oc get dnsrecord <name> -n <namespace> -o jsonpath='{.spec.rootHost}')
  dig @${NS1} ${ROOT_HOST}
## USING ON-PREMISE DNS WITH COREDNS
27

If the DNSRecord is not appearing in the zone, verify that the record has the zone label by
running the following command:
The output should include the zone name, for example kuadrant.io/coredns-zone-name: 
k.example.com.
If the output does not show the zone name, check that the DNS Operator is running by
using the following command:
You can also check the DNS Operator logs by running the following command:
A couple of common issues can be missing RBAC and GeoIP database.
RBAC permissions missing. Check your ClusterRole and ClusterRoleBinding
configurations.
GeoIP database file not found. Ensure that your database is accessible.
Next steps
Create dnsPolicy custom resources in your OpenShift Container Platform pods, referencing
the coredns-credentials secret as the provider. Connectivity Link manages DNS records within
the delegated ${KUADRANT_SUBDOMAIN}.${ONPREM_DOMAIN} zone through CoreDNS.
### USING COREDNS WITH PRIMARY AND SECONDARY CLUSTERS
You can use CoreDNS as a DNS provider for Connectivity Link in an existing multi-cluster, on-premise
environment. This integration allows Connectivity Link to manage DNS entries within your internal
network infrastructure.
Prerequisites
Connectivity Link is installed on two separate OpenShift Container Platform clusters (primary
and secondary).
OpenShift CLI (oc) is installed and configured for access to both clusters.
You have administrator privileges on both OpenShift Container Platform clusters.
Your OpenShift Container Platform clusters have support for the loadbalanced service type
that allows UDP and TCP traffic on port 53, such as MetalLB.
You have access to configure your authoritative on-premise DNS server to delegate a
subdomain.
$ oc logs -n kuadrant-coredns deployment/kuadrant-coredns
$ oc get dnsrecords.kuadrant.io -n dnstest -o jsonpath='{.items[*].metadata.labels}' | grep 
kuadrant.io/coredns-zone-name
$ oc get pods -n dns-operator-system
$ oc logs -n dns-operator-system deployment/dns-operator-controller-manager
Red Hat Connectivity Link 1.3 Configuring and deploying gateway policies
28

Podman is installed.
jq is installed.
Procedure
1. Set up the primary cluster. Set the following environment variables for your primary cluster
context:
Replace <primary_cluster_context_name> with the name of the cluster that you are
specifying as primary. For the ONPREM_DOMAIN variable value, use your actual root domain.
2. Extract the CoreDNS manifests from the dns-operator bundle by running the following
commands:
3. Apply the manifests to the cluster by running the following command:
4. Wait for the CoreDNS service to get an external IP address. You need the IP address to
configure delegation on your authoritative on-premise DNS server. Retrieve and store the IP
address by running the following command:
5. Create a ConfigMap to define the authoritative zone for CoreDNS on the primary cluster. This
minimal configuration enables the kuadrant plugin and GeoIP features.
$ export CTX_PRIMARY=<primary_cluster_context_name> # such as, primary \
  export KUBECONFIG=~/.kube/config # Adjust path if necessary \
  export PRIMARY_CLUSTER_NAME=<primary_cluster_name> # such as, primary \
  export ONPREM_DOMAIN=<onprem-domain> # such as, example.local \
  export KUADRANT_SUBDOMAIN=kuadrant # Subdomain to delegate
$ podman create --name bundle registry.redhat.io/rhcl-1/dns-operator-bundle:rhcl-1.3.0
$ podman cp bundle:/coredns/manifests.yaml ./coredns-manifests.yaml
$ podman rm bundle
$ oc apply -f ./coredns-manifests.yaml
$ export COREDNS_IP_PRIMARY=$(oc --context $CTX_PRIMARY -n kuadrant-system get 
service kuadrant-coredns -o jsonpath='{.status.loadBalancer.ingress[0].ip}')
echo "CoreDNS Primary IP: ${COREDNS_IP_PRIMARY}"
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
## USING ON-PREMISE DNS WITH COREDNS
29

NOTE
For production or exact GeoIP routing, mount your licensed MaxMind GeoIP
database into the CoreDNS pod and update the filename in the 
data.Corefile.geoip parameter.
6. Update the CoreDNS deployment to use the new configuration:
7. Set a watch-and-wait command for the deployment rollout to complete:
Example output
8. Create the Kubernetes Secret that Connectivity Link uses to interact with CoreDNS. This
secret specifies the zones this provider instance is authoritative for.
9. On your authoritative on-premise DNS server, configure delegation for the 
${KUADRANT_SUBDOMAIN}.${ONPREM_DOMAIN} subdomain to the external IP addresses
of the CoreDNS services running on your primary and secondary clusters, 
$COREDNS_IP_PRIMARY and $COREDNS_IP_SECONDARY. The specific steps depend on
your DNS server software, for example, BIND, Windows DNS Server. You typically need to add
Name Server (NS) records pointing the subdomain to the CoreDNS IP addresses.
Example delegation
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
$ oc --context $CTX_PRIMARY -n kuadrant-system patch deployment kuadrant-coredns --
patch '{"spec":{"template":{"spec":{"volumes":[{"name":"config-volume","configMap":
{"name":"coredns-kuadrant-config","items":[{"key":"Corefile","path":"Corefile"}]}}]}}}}'
$ oc --context $CTX_PRIMARY -n kuadrant-system rollout status deployment/kuadrant-
coredns
kuadrant-coredns successfully rolled out
$ oc create secret generic coredns-credentials \
  --namespace=kuadrant-system \
  --type=kuadrant.io/coredns \
  --from-literal=ZONES="${KUADRANT_SUBDOMAIN}.${ONPREM_DOMAIN}" \
  --context ${CTX_PRIMARY}
; Delegate kuadrant.example.local to CoreDNS instances
$ORIGIN ${KUADRANT_SUBDOMAIN}.${ONPREM_DOMAIN}.
Red Hat Connectivity Link 1.3 Configuring and deploying gateway policies
30

10. Restart CoreDNS by running the following command:
NOTE
After configuring delegation, you can test that the DNS resolution for the
delegated subdomain works correctly by querying your authoritative DNS server
for a record within the kuadrant subdomain. One of the CoreDNS instances is
expected to refer to and answer the query.
Verification
1. Launch a temporary pod for testing by running the following command:
Replace <node-name> with the node you are testing on.
2. Add transfer to your Corefile by running the following command:
3. Verify zone delegation by running the following command:
Example output
In this example, k.example is the delegated zone and ns1.k.example is the primary zone.
4. Optional. Remove the transfer from your Corefile by running the following command:
@       IN      SOA     ns1.${ONPREM_DOMAIN}. hostmaster.${ONPREM_DOMAIN}. (
                        2023102601 ; serial
                        7200       ; refresh (2 hours)
                        3600       ; retry (1 hour)
                        1209600    ; expire (2 weeks)
                        3600       ; minimum (1 hour)
                        )
        IN      NS      coredns-primary.${KUADRANT_SUBDOMAIN}.${ONPREM_DOMAIN}.
coredns-primary   IN A ${COREDNS_IP_PRIMARY}
$ oc -n kuadrant-coredns rollout restart deployment kuadrant-coredns
$ oc debug node/<node-name>
$ oc patch cm kuadrant-coredns -n kuadrant-coredns --type merge \
   -p "$(oc get cm kuadrant-coredns -n kuadrant-coredns -o jsonpath='{.data.Corefile}' | \
   sed 's/kuadrant/transfer {\n        to *\n    }\n    kuadrant/' | \
   jq -Rs '{data: {Corefile: .}}')"
$ dig @${EDGE_NS} -k config/bind9/ddns.key -t AXFR example.com
example.com.            30      IN      SOA     example.com. root.example.com. 17 30 30 30 30
example.com.            30      IN      NS      ns.example.com.
k.example.com.          300     IN      NS      ns1.k.example.com.
ns1.k.example.com.      300     IN      A       172.18.0.16
ns.example.com.         30      IN      A       127.0.0.1
example.com.            30      IN      SOA     example.com. root.example.com. 17 30 30 30 30
## USING ON-PREMISE DNS WITH COREDNS
31

5. Verify the start of authority (SOA) record for the delegated zone by running the following
command:
Example output
The SOA record is expected to show the primary name server (NS) as confirmation that
CoreDNS is responding authoritatively. In this example the primary NS is ns1.k.example.com.
Next steps
Create DNSPolicy resources in your OpenShift Container Platform clusters, referencing the 
coredns-credentials secret as the provider. Connectivity Link manages DNS records within the
delegated ${KUADRANT_SUBDOMAIN}.${ONPREM_DOMAIN} zone through CoreDNS.
### COREDNS COREFILE CONFIGURATION REFERENCE
A Corefile is organized into server blocks that define how DNS queries are handled based on the port
and zone. Plugin execution order is determined at build time, not by Corefile order, so you can list
plugins in any order. When making configurations by using the DNS Operator, you can check the 
ConfigMap for the resulting server block.
Connectivity Link includes a minimal Corefile that you can update for your uses:
Minimal Corefile
For a Corefile with configurations, see the following example:
Example configured Corefile
$ oc patch cm kuadrant-coredns -n kuadrant-coredns --type merge \
   -p "$(oc get cm kuadrant-coredns -n kuadrant-coredns -o jsonpath='{.data.Corefile}' | \
   sed '/transfer {/,/}/d' | \
   jq -Rs '{data: {Corefile: .}}')"
$ dig @${EDGE_NS} soa k.example.com
;; ANSWER SECTION:
k.example.com.          60      IN      SOA     ns1.k.example.com. hostmaster.k.example.com. 
12345 7200 1800 86400 60
Corefile: |
    . {
        health
        ready
    }
k.example.com {
    debug
    errors
    log
    health {
        lameduck 5s
    }
Red Hat Connectivity Link 1.3 Configuring and deploying gateway policies
32

Zone coordination
Each zone in the Corefile must match a zone listed in your CoreDNS provider secret’s ZONES field.
Required plugins
The geoip and metadata plugins are included by default with the Connectivity Link implementation
of the CoreDNS Corefile.
Corefile updates
After you update your Corefile, you must always restart your pods for the CoreDNS deployment. You
can use the following command:
You can check the status of the rollout by running the following command:
2.6.1. Default enabled plugins in CoreDNS
The following plugins are enabled by default in the Connectivity Link CoreDNS plugin. You must ensure
CoreDNS compatibility and enable any other plugins that you want to add.
Plugin
Function
acl
Enforces access control policies on source IP addresses and prevents unauthorized
access to DNS servers.
cache
Enables a front-end cache.
cancel
Cancels a request’s context after 5001 milliseconds.
debug
Disables the automatic recovery when a crash happens so that a stack trace is
generated.
errors
Enables error logging.
file
Enables serving zone data from an RFC 1035-style master file.
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
$ oc rollout restart deployment/coredns -n kuadrant-system
$ oc rollout status deployment/coredns -n kuadrant-system --watch
## USING ON-PREMISE DNS WITH COREDNS
33

forward
Enables IP forwarding. Facilitates proxying DNS messages to upstream resolvers.
geoip
Lookup .mmdb (MaxMind db file format) databases using the client IP, then adds
associated geoip data to the context request.
header
Modifies the header for queries and responses.
health
Enables a health check endpoint.
hosts
Enables serving zone data from an /etc/hosts style file.
kuadrant
Enables serving zone data from kuadrant DNSRecord custom resources. Uses logic
from the CoreDNS file plugin to create a functioning DNS server.
local
Responds with a basic reply to a local names in the following zones, localhost, 0.in-
addr.arpa, 127.in-addr.arpa, 255.in-addr.arpa and any query asking for 
localhost.<domain>.
log
Enables query logging to standard output. Logs are structured for aggregation by
cluster logging solutions.
loop
Detects simple forwarding loops and halts the server.
metadata
Enables a metadata collector.
minimal
Minimizes size of the DNS response message whenever possible.
nsid
Adds an identifier of this server to each reply.
prometheus
Enables Prometheus metrics. The default listens on localhost:9153. The metrics path
is to /metrics.
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
Means that you can configure the server read, write and idle timeouts for the TCP, TLS,
DoH and DoQ (idle only) servers.
Plugin
Function
Red Hat Connectivity Link 1.3 Configuring and deploying gateway policies
34

tls
Means that you can configure the server certificates for the TLS, gRPC, and DoH
servers.
transfer
Perform (outgoing) zone transfers for other plugins.
view
Defines the conditions that must be met for a DNS request to be routed to the server
block.
whoami
Returns your resolver’s local IP address, port and transport.
Plugin
Function
TIP
When using CoreDNS, if you do not need to keep all logs, you can set up the logs directive to only report
errors and use the prometheus plugin to gather primary metrics instead. Prometheus metrics give you
trends, for example, how many queries failed, without storing every single piece of traffic.
### TROUBLESHOOTING COREDNS WITH THE KUADRANT PLUGIN
You can troubleshoot your CoreDNS deployment by restarting CoreDNS and by checking the logs. Use
the following commands as needed to investigate your specific errors:
Restart CoreDNS by using the following command:
You can view CoreDNS logs by running the following command:
You can get recent logs by running the following command:
### COREDNS REMOVAL OR MIGRATION
You can remove your CoreDNS integration by deleting the CoreDNS deployment and deleting your
DNS policies. To migrate to a different provider, delete existing dnsPolicy CRs and re-create them with
the new provider secret reference. No data is permanently locked into CoreDNS.
### ADDITIONAL RESOURCES
Audit logging for network security
$ oc -n kuadrant-coredns rollout restart deployment kuadrant-coredns
$ oc logs -f deployments/kuadrant-coredns -n kuadrant-coredns
$ oc logs --tail=100 deployments/kuadrant-coredns -n kuadrant-coredns
## USING ON-PREMISE DNS WITH COREDNS
35

---

## Connectivity Link Observability

Red Hat Connectivity Link 1.3
Observability
Observe and monitor Gateways, APIs, and applications on OpenShift
Last Updated: 2026-03-26

Red Hat Connectivity Link 1.3 Observability
Observe and monitor Gateways, APIs, and applications on OpenShift

Legal Notice
Copyright © Red Hat.
Except as otherwise noted below, the text of and illustrations in this documentation are licensed by
Red Hat under the Creative Commons Attribution–Share Alike 3.0 Unported license . If you
distribute this document or an adaptation of it, you must provide the URL for the original version.
Red Hat, as the licensor of this document, waives the right to enforce, and agrees not to assert,
Section 4d of CC-BY-SA to the fullest extent permitted by applicable law.
Red Hat, the Red Hat logo, JBoss, Hibernate, and RHCE are trademarks or registered trademarks of
Red Hat, LLC. or its subsidiaries in the United States and other countries.
Linux ® is the registered trademark of Linus Torvalds in the United States and other countries.
XFS is a trademark or registered trademark of Hewlett Packard Enterprise Development LP or its
subsidiaries in the United States and other countries.
The OpenStack ® Word Mark and OpenStack logo are trademarks or registered trademarks of the
Linux Foundation, used under license.
All other trademarks are the property of their respective owners.
Abstract
This guide describes how to set up the Connectivity Link observability features in single-cluster and
multicluster environments.

. . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 
Table of Contents
## CONNECTIVITY LINK OBSERVABILITY
### CONNECTIVITY LINK OBSERVABILITY FEATURES
### CONFIGURE YOUR OBSERVABILITY MONITORING STACK
### ENABLING OBSERVABILITY MONITORING IN CONNECTIVITY LINK
### ABOUT CONFIGURING OBSERVABILITY DASHBOARDS AND ALERTS
1.4.1. Platform engineer Grafana dashboard
1.4.2. Application developer Grafana dashboard
1.4.3. Business user Grafana dashboard
1.4.4. Grafana dashboards available to import
1.4.5. Importing dashboards in Grafana
1.4.6. About importing dashboards automatically in OpenShift Container Platform
1.4.7. About configuring Prometheus alerts
### TRACING IN CONNECTIVITY LINK
1.5.1. Correlating control plane and data plane traces
1.5.2. Control-plane tracing environment variables
1.5.3. Configuring data plane tracing in Connectivity Link
1.5.4. Troubleshooting by using traces and logs
1.5.5. Viewing rate-limit logging with trace IDs
### CONFIGURING ACCESS LOGS
1.6.1. Filtering access logs
1.6.2. Common access log format variables
### ABOUT USING ACCESS LOGS FOR REQUEST CORRELATION
1.7.1. Setting up access log and tracing correlation
### ADDITIONAL RESOURCES
3
3
3
4
5
5
6
6
6
6
7
7
8
8
8
9
13
13
14
16
17
18
19
20
Table of Contents
1

Red Hat Connectivity Link 1.3 Observability
2
## CONNECTIVITY LINK OBSERVABILITY
You can use the Connectivity Link observability features to observe and monitor your gateways,
applications, and APIs on OpenShift Container Platform.
### CONNECTIVITY LINK OBSERVABILITY FEATURES
Connectivity Link uses metrics exposed by Connectivity Link components, Gateway API state metrics,
and standard metrics exposed by Envoy to build a set template dashboards and alerts. Envoy is part of
OpenShift Service Mesh. In this case, it runs as a gateway deployment.
You can download Kuadrant community-based templates to integrate with Grafana, Prometheus, and
Alertmanager deployments, or use those templates as starting points that you can change for your
specific needs. Use the secure images available in the Red Hat Catalog at: Red Hat Connectivity Link.
Connectivity Link includes the following observability features:
Metrics: Prometheus metrics for monitoring gateway and policy performance
Tracing: Distributed tracing with Red Hat build of OpenTelemetry support for request flows
Access Logs: Envoy access logs with request correlation and structured logging
Dashboards: Pre-built Grafana dashboards for visualization
### CONFIGURE YOUR OBSERVABILITY MONITORING STACK
You can prepare your monitoring stack to give yourself insight into your gateways, applications, and APIs
by setting up dashboards and alerts on your OpenShift Container Platform cluster. You must configure
your stack on each OpenShift Container Platform cluster that you want to use Connectivity Link on.
The example dashboards and alerts for observing Connectivity Link functionality use low-level CPU and
network metrics from the user monitoring stack in OpenShift Container Platform and resource-state
metrics from Gateway API and Connectivity Link resources. The user monitoring stack in OpenShift
Container Platform is based on the Prometheus open source project.
IMPORTANT
The following procedure is an example only and is not intended for production use.
Prerequisites
You installed Connectivity Link.
You set up metrics, such as prometheus.
You installed and configured Grafana on your OpenShift Container Platform cluster.
You cloned the Kuadrant Operator GitHub repository.
Procedure
1. Verify that the user workload monitoring is configured correctly in your OpenShift Container
Platform cluster as follows:
## CONNECTIVITY LINK OBSERVABILITY
3

The expected output is enableUserWorkload: true.
2. Install the Connectivity Link, Gateway, and Grafana component metrics and configuration as
follows:
3. From the root directory of your Kuadrant Operator repository, configure the OpenShift
Container Platform thanos-query instance as a data source in Grafana as follows:
4. Configure the example Grafana dashboards as follows:
### ENABLING OBSERVABILITY MONITORING IN CONNECTIVITY LINK
By enabling observability monitoring, you can view context, historical trends, and alerts based on the
metrics you configured. After you have configured your monitoring stack, use this step to expose metrics
endpoints, deploy monitoring resources, and configure the Envoy gateway.
When you enable observability monitoring, the following events occur:
Connectivity Link creates ServiceMonitor and PodMonitor custom resource definitions (CRDs)
for its components in the namespace where Connectivity Link is.
A single set of monitors is created in each gateway namespace to scrape metrics from any
gateways.
Monitors also scrape metrics from the corresponding gateway system namespace, generally the
istio-system namespace.
You can delete and re-create monitors as required. Monitors are only ever created or deleted, and not
updated or reverted. The following procedure is optional. You can create your own ServiceMonitor or 
PodMonitor definitions, or configure prometheus metrics directly.
IMPORTANT
To use Connectivity Link observability dashboards, you must enable observability on each
OpenShift Container Platform cluster that Connectivity Link runs on.
Prerequisites
$ oc get configmap cluster-monitoring-config -n openshift-monitoring -o 
jsonpath='{.data.config\.yaml}'|grep enableUserWorkload
$ oc apply -k https://github.com/Kuadrant/kuadrant-
operator/config/install/configure/observability?ref=v1.2.0
TOKEN="Bearer $(oc whoami -t)"
HOST="$(oc -n openshift-monitoring get route thanos-querier -o 
jsonpath='https://{.status.ingress[].host}')"
echo "TOKEN=$TOKEN" > config/observability/openshift/grafana/datasource.env
echo "HOST=$HOST" >> config/observability/openshift/grafana/datasource.env
oc apply -k config/observability/openshift/grafana
$ oc apply -k https://github.com/Kuadrant/kuadrant-operator/examples/dashboards?
ref=v1.3.0
Red Hat Connectivity Link 1.3 Observability
4

You installed Connectivity Link.
You have administrator access to your OpenShift Container Platform cluster.
You configured observability metrics.
Procedure
To enable default observability for Connectivity Link and any gateways, set 
spec.observability.enable parameter value to true in your Kuadrant custom resource (CR):
Example Kuadrant CR
You can also set the spec.observability.enable to false and create your own ServiceMonitor
or PodMonitor definitions, or configure Prometheus directly.
Verification
Check the created monitors by running the following command:
Additional resources
ServiceMonitor API reference
PodMonitor API reference
### ABOUT CONFIGURING OBSERVABILITY DASHBOARDS AND
ALERTS
Connectivity Link includes starting points for monitoring your Connectivity Link deployment with ready-
to-use example dashboards and alerts. You can customize these dashboards and alerts to fit your
environment.
Dashboards are organized with different metrics for different use cases.
1.4.1. Platform engineer Grafana dashboard
The platform engineer dashboard displays the following details:
Policy compliance and governance.
Resource consumption
apiVersion: kuadrant.io/v1beta1
kind: Kuadrant
metadata:
  name: kuadrant-sample
spec:
  observability:
    enable: true
# ...
$ oc get servicemonitor,podmonitor -A -l kuadrant.io/observability=true
## CONNECTIVITY LINK OBSERVABILITY
5

Error rates
Request latency and throughput
Multi-window, multi-burn alert templates for API error rates and latency
Multicluster split
1.4.2. Application developer Grafana dashboard
The application developer dashboard is less focused on policies than the platform engineer dashboard
and is more focused on APIs and applications. For example:
Request latency and throughput per API
Total requests and error rates by API path
1.4.3. Business user Grafana dashboard
The business user dashboard includes the following details:
Requests per second per API
Increase or decrease in rates of API usage over specified times
1.4.4. Grafana dashboards available to import
The Connectivity Link example dashboards are uploaded to the Grafana dashboards website. You can
import the following dashboards into your Grafana deployment on OpenShift Container Platform:
Table 1.1. Connectivity Link example dashboards in Grafana
Name
Dashboard ID
App Developer dashboard
21538
Platform Engineer dashboard
20982
Business User dashboard
20981
DNS Operator dashboard
22695
1.4.5. Importing dashboards in Grafana
As an infrastructure engineer, you can manually select and important dashboards to Grafana to conduct
rapid prototyping or emergency troubleshooting, test community dashboards, or perfect a dashboard
that you intend to automate for another team.
IMPORTANT
You must perform these steps on each OpenShift Container Platform cluster that you
want to use Connectivity Link on.
Red Hat Connectivity Link 1.3 Observability
6

Prerequisites
You configured your monitoring stack and other observability resources as needed.
You installed Connectivity Link.
You have administrator access to a running OpenShift Container Platform cluster.
Procedure
Click Dashboards > New > Import, and use one of the following options:
Upload a dashboard JSON file.
Enter a dashboard ID obtained from the Grafana dashboards website.
Enter JSON content directly.
For more information, see the Grafana documentation on how to import dashboards .
1.4.6. About importing dashboards automatically in OpenShift Container Platform
As an infrastructure engineer, automating the import of observability dashboards can give you more
consistency, version control, and operational velocity. Automation gives you the benefits of monitoring-
as-code, and helps keep Operators updated, clusters identical, and supports multi-tenancy.
You can use a GrafanaDashboard resource to reference a ConfigMap.
Data sources are configured as template variables, automatically integrating with your existing
data sources. The metrics for these dashboards are sourced from Prometheus.
IMPORTANT
For some example dashboard panels to work correctly, HTTPRoutes in
Connectivity Link must include a service and deployment label with a value that
matches the name of the service and deployment being routed to. For example, 
service=my-app and deployment=my-app. This allows low-level Istio and Envoy
metrics to be joined with Gateway API state metrics.
If you do not want to use the GUI, you can automate dashboard provisioning in Grafana by
adding JSON files to a ConfigMap object that you must mount at 
/etc/grafana/provisioning/dashboards.
1.4.7. About configuring Prometheus alerts
As an infrastructure engineer, you can configure Prometheus alerts in OpenShift Container Platform is a
proactive way to tune alerts so that you can ensure platform stability. For example, you can set alert
triggers for automated incident detection, usage, and cluster health.
You can integrate the Connectivity Link example alerts into Prometheus as PrometheusRule
resources, and then adjust the alert thresholds to suit your specific operational needs.
For details on how to configure Prometheus alerts, see Configuring alerts and notifications for
user workload monitoring.
l
b
l
d b
h
l
h
b
l
## CONNECTIVITY LINK OBSERVABILITY
7

Service Level Objective (SLO) alerts generated by using the Sloth GitHub project are also
included. You can use these alerts to integrate with the SLO Grafana dashboard, which uses
generated labels to comprehensively overview your SLOs.
### TRACING IN CONNECTIVITY LINK
Connectivity Link supports tracing at both the control plane and data-plane levels. Connectivity Link
exports control-plane traces to your OpenTelemetry Collector so that you can observe reconciliation
loops and internal operations. This is useful for debugging controller behavior, understanding operator
performance, and tracking policy lifecycle events.
Data-plane tracing traces actual user requests through the gateway and policy enforcement
components. You can see request flows through Istio, Authorino, Limitador, and the wasm-shim
module. Data-plane tracing is useful for debugging request-level issues and policy enforcement.
To use tracing, you must configure both types of tracing. You must configure the kuadrant custom
resource (CR) for the data plane. For control plane tracing, you must configure each operating
component separately, such as the kuadrant-operator, authorino-operator, and limitador-operator
deployments. This configuration sends traces to the same collector, providing a complete view of your
Connectivity Link system from policy reconciliation to request processing.
1.5.1. Correlating control plane and data plane traces
Even though control plane and data plane traces are separate, you can correlate them. For example,
create a RateLimitPolicy to understand how traces work together to show all events.
Create a RateLimitPolicy at 15:30:00, then view the control plane trace to see the following
events:
Policy reconciliation completed at 15:30:05.
Limitador configuration updated.
wasm-shim configuration updated.
Next, send a test request at 15:30:10, then view data plane trace to see the following events:
Request processed through the wasm-shim module.
Rate limit check sent to Limitador.
Response returned.
You can use a similar pattern of action for any events that you want to correlate manually. This type of
correlation is useful in development environments.
1.5.2. Control-plane tracing environment variables
You can enable control tracing in Connectivity Link by setting OpenTelemetry environment variables in
the deployment. The method for setting the variables depends on your deployment approach, for
example, whether you used the Operator Lifecycle Manager (OLM) or YAML manifests.
Control plane traces appear under the service name kuadrant-operator in the Grafana dashboard.
Table 1.2. Available OpenTelemetry environment variables
Red Hat Connectivity Link 1.3 Observability
8

Variable
Description
Default
OTEL_EX
PORTER_
OTLP_EN
DPOINT
OTLP collector endpoint URL, for example, 
rpc://tempo.tempo.svc.cluster.local:4317. The
following supported endpoint URL schemes are:
rpc://host:port → gRPC OTLP
Insecure: http://host:port → HTTP OTLP
Secure: https://host:port → HTTP OTLP
Tracing disabled
OTEL_EX
PORTER_
OTLP_TR
ACES_EN
DPOINT
Override endpoint specifically for traces
Uses 
OTEL_EXPORTER_OTL
P_ENDPOINT
OTEL_EX
PORTER_
OTLP_INS
ECURE
Use insecure connection to collector; set to false when used
in production with TLS
true
OTEL_SE
RVICE_NA
ME
Service name for traces
kuadrant-operator
OTEL_SE
RVICE_VE
RSION
Service version for telemetry data
Empty
1.5.3. Configuring data plane tracing in Connectivity Link
Enable data plane tracing in OpenShift Service Mesh with the kuadrant CR. You must perform these
steps on each OpenShift Container Platform cluster that you want to use Connectivity Link on.
Prerequisites
You installed Connectivity Link.
You are logged into a running OpenShift Container Platform cluster as an administrator.
You have Red Hat OpenShift Distributed Tracing Platform installed and configured to support
OpenTelemetry.
You installed a compatible version of OpenShift Service Mesh.
Procedure
1. Enable tracing in OpenShift Service Mesh by configuring your Telemetry custom resource (CR)
as follows:
## CONNECTIVITY LINK OBSERVABILITY
9

Example OpenShift Service Mesh Telemetry CR with tracing
2. Apply the configuration by running the following command:
3. Configure a tracing extension provider for OpenShift Service Mesh in your Istio CR by adding a
list value to the spec.values.meshConfig.extensionProviders parameter. Ensure that you also
add the otel port and service information:
Example Istio CR with tracing extension provider
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
$ oc apply -f mesh-default.yaml
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
Red Hat Connectivity Link 1.3 Observability
10

IMPORTANT
If you are setting the controller manually, you must set the OpenTelemetry
Collector protocol in the Service CR port name and appProtocol fields. For
example, when using gRPC, the port name should begin with grpc- or the 
appProtocol should be grpc:
4. Apply the configuration by running the following command:
5. Optional. If you want to collect Authorino and Limitador traces in a different location than your
Kaudrant traces, complete the following steps:
a. Enable request tracing in your Authorino custom resource (CR) and send authentication
and authorization traces to the central collector as follows:
Example Authorino CR with request tracing
Set insecure to true to skip TLS certificate verification in development environments. Set
to false for production environments.
b. Apply the configuration by running the following command:
c. Enable request tracing in your Limitador CR and send rate limit traces to the central
collector as follows:
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
$ oc apply -f istio.yaml
apiVersion: operator.authorino.kuadrant.io/v1beta1
kind: Authorino
metadata:
  name: authorino
spec:
  tracing:
    endpoint: rpc://authorino-collector:4317
    insecure: true
# ...
$ oc apply -f authorino.yaml
## CONNECTIVITY LINK OBSERVABILITY
11

Example Limitador CR with request tracing
Set insecure to true to skip TLS certificate verification in development environments. Set
to false for production environments.
d. Apply the configuration by running the following command:
IMPORTANT
Trace IDs do not propagate to WebAssembly modules in OpenShift Service
Mesh. This means that requests passed to Limitador do not have the relevant
parent trace ID. However, if the trace initiation point is outside OpenShift
Service Mesh, the parent trace ID is available to Limitador and included in
traces. This impacts correlating traces from Limitador with traces from
Authorino, the gateway, and any other components in the request path.
6. Configure data-plane tracing in the Kuadrant CR by providing the collector endpoint as shown
in the following example:
Example kuadrant CR
where:
spec.observability.tracing.defaultEndpoint:: The URL of the tracing collector backend.
That is, the OpenTelemetry endpoint. The following are supported protocols:
rpc:// for gRPC OTLP, port 4317
apiVersion: limitador.kuadrant.io/v1alpha1
kind: Limitador
metadata:
  name: limitador
spec:
  tracing:
    endpoint: rpc://limitador-collector:4317
    insecure: true
# ...
$ oc apply -f limitador.yaml
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
Red Hat Connectivity Link 1.3 Observability
12

http:// for HTTP OTLP, port 4318
`spec.observability.tracing.insecure:: Set to true to skip TLS certificate verification in
development environments. Set to false for production environments.
IMPORTANT
Point to the collector service, such as the Distributed Tracing Platform, not
the query service. The collector receives traces from your applications. The
query service is only for viewing traces in the GUI.
7. Apply the configuration by running the following command:
Verification
Verify that the CR applied successfully by listing the objects of that Kind by running the
following command:
1.5.4. Troubleshooting by using traces and logs
You can use a tracing user interface such Grafana to search for OpenShift Service Mesh and
Connectivity Link trace information by trace ID. You can get the trace ID from logs, or from a header in a
sample request that you want to troubleshoot. You can also search for recent traces, filtering by the
service that you want to focus on.
If you centrally aggregate logs by using tools such as Grafana Loki and Promtail, you can jump between
trace information and the relevant logs for that service.
By using a combination of tracing and logs, you can visualize and troubleshoot request timing issues and
narrow down to specific services. This method gives you even more insight a more complete picture of
your user traffic when you combine it with Connectivity Link metrics and dashboards.
1.5.5. Viewing rate-limit logging with trace IDs
You can enable request logging with trace IDs to get more information about requests when you use the
Limitador component of Connectivity Link for rate limiting. To do this, you must increase the log level.
Prerequisites
You installed Connectivity Link.
You have administrator access to a running OpenShift Container Platform cluster.
You configured Grafana dashboards.
You have Red Hat OpenShift Distributed Tracing Platform installed and configured to support
OpenTelemetry.
Procedure
$ oc apply -f kuadrant.yaml
$ oc get kuadrant
## CONNECTIVITY LINK OBSERVABILITY
13

Set the verbosity to 3 or higher in your Limitador custom resource (CR) as follows:
Example Limitador CR
Example log entry with the traceparent field holding the trace ID
### CONFIGURING ACCESS LOGS
You can configure Envoy access logs in OpenShift Service Mesh so that you can to track a single
request across multiple services and components by using a unique identifier.
Prerequisites
You installed Connectivity Link.
You have a running OpenShift Container Platform cluster.
You have administrator access to the OpenShift Container Platform cluster.
Procedure
1. Enable mesh-wide, default-format access logs by using the Istio Telemetry API. Use the
following example as a starting point:
Example Telemetry API config
You might also use the istio-system as your namespace, depending on your setup.
apiVersion: limitador.kuadrant.io/v1alpha1
kind: Limitador
metadata:
  name: limitador
spec:
  verbosity: 3
"Request received: Request { metadata: MetadataMap { headers: {"te": "trailers", "grpc-
timeout": "5000m", "content-type": "application/grpc", "traceparent": "00-
4a2a933a23df267aed612f4694b32141-00f067aa0ba902b7-01", "x-envoy-internal": "true", "x-
envoy-expected-rq-timeout-ms": "5000"} }, message: RateLimitRequest { domain: 
"default/toystore", descriptors: [RateLimitDescriptor { entries: [Entry { key: 
"limit.general_user__f5646550", value: "1" }, Entry { key: 
"metadata.filter_metadata.envoy\\.filters\\.http\\.ext_authz.identity.userid", value: "alice" }], 
limit: None }], hits_addend: 1 }, extensions: Extensions }"
apiVersion: telemetry.istio.io/v1alpha1
kind: Telemetry
metadata:
  name: access-logs
  namespace: gateway-system
spec:
  accessLogging:
    - providers:
      - name: envoy
Red Hat Connectivity Link 1.3 Observability
14

2. For better parsing and integration with log aggregation systems, enable JSON-formatted
access logs. Only log errors as shown in the following example:
Example JSON config
3. To enable logging for a specific workload and add filtering, use the following example:
Example JSON workload config
TIP
The expression field uses Common Expression Language (CEL). You can use CEL-based
filters to avoid excessive and meaningless logs.
4. If you are using the Sail Operator, check which Istio Operator is active in your cluster by running
the following command:
The expected output is a list of your mesh deployments, such as default, prod-mesh and their
current status.
5. Configure the Istio mesh with a custom access log provider to enable JSON encoding:
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
$ oc get istio -A
apiVersion: sailoperator.io/v1
kind: Istio
metadata:
## CONNECTIVITY LINK OBSERVABILITY
15

Next steps
Filter your access logs to focus on the errors you need to see.
Enable request, log, and tracing correlation.
1.6.1. Filtering access logs
You can filter your access logs to reduce extra messages and focus on the issues and errors that are
relevant to your use case.
Prerequisites
You installed Connectivity Link.
You have a running OpenShift Container Platform cluster.
You have administrator access to the OpenShift Container Platform cluster.
You enabled access logs.
Procedure
1. Configure your Telemetry custom resource (CR) to only log errors by using the following
example:
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
apiVersion: telemetry.istio.io/v1alpha1
Red Hat Connectivity Link 1.3 Observability
16

2. Configure your Telemetry custom resource (CR) to only log specific routes by using the
following example:
3. Configure your Telemetry custom resource (CR) to exclude health checks by using the
following example:
1.6.2. Common access log format variables
You can quickly set up Envoy logs by using the most common format variables so that you get exactly
the data you want.
Example configuration snippet
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
envoyFileAccessLog:
  path: /dev/stdout
  logFormat:
    text: "[%START_TIME%] %REQ(X-REQUEST-ID)% %RESP(HEADER)% 
%RESPONSE_FLAGS%\n"
## CONNECTIVITY LINK OBSERVABILITY
17

Table 1.3. Common Envoy access log format variables
Variable
Description
%START_TIME%
Request start time
%REQ(HEADER)%
Request header value, such as %REQ(X-REQUEST-ID)%
%RESP(HEADER)%
Response header value
%PROTOCOL%
Protocol, such as HTTP/1.1, HTTP/2
%RESPONSE_CODE%
HTTP response code
%RESPONSE_FLAGS%
Response flags indicating issues, such as UH, UF
%BYTES_RECEIVED%
Bytes received from client
%BYTES_SENT%
Bytes sent to client
%DURATION%
Total request duration in milliseconds
%UPSTREAM_HOST%
Upstream host address
%UPSTREAM_CLUSTER%
Upstream cluster name
%ROUTE_NAME%
Route name that matched
### ABOUT USING ACCESS LOGS FOR REQUEST CORRELATION
Access logs give you detailed information about each request processed by the gateway, including
timing, response codes, and request identifiers. For example, you can correlate requests across
gateways, Authorino, Limitador, and backend services.
You can correlate request information with traces and application logs for a variety of uses. Request
correlation uses x-request-id headers. These headers are automatically generated by Envoy for each
incoming request. For example:
Access logs show the x-request-id.
Traces include the x-request-id as a span attribute.
Use a dashboard to jump from logs to traces and vice versa.
The following fields are the most important access-log fields for request correlation:
request_id (%REQ(X-REQUEST-ID)%): The unique request identifier generated by Envoy.
start_time** (%START_TIME%): The request start time for time-based correlation.
Red Hat Connectivity Link 1.3 Observability
18

route_name** (%ROUTE_NAME%): The route that matched the request, which is useful for
policy debugging.
1.7.1. Setting up access log and tracing correlation
You can use access logs and tracing together to correlate requests. When you correlate request IDs,
you can search for an ID once and see the entire journey from the initial access through to an event that
you are investigating.
You can see the exact timing of a request as it entered and left each service. If you have configured user
or organization-based IDs, you can also determine who a problem is effecting so that you can prioritize
your response.
The following configuration example tells WASM filters to log the x-request-id header value and
enables request correlation across Envoy, Authorino, Limitador, and WASM logs.
Prerequisites
You installed Connectivity Link.
You have a running OpenShift Container Platform cluster.
You have administrator access to the OpenShift Container Platform cluster.
You enabled access logs and tracing.
Procedure
1. To enable request correlation across Connectivity Link components, configure the 
httpHeaderIdentifier in the Kuadrant CR:
2. You can correlate logs across all components using the x-request-id, by using the following
examples:
a. View the following Envoy access log entry:
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
{
  "start_time": "2026-01-23T15:45:12.345Z",
  "method": "GET",
  "path": "/api/users",
  "response_code": 200,
## CONNECTIVITY LINK OBSERVABILITY
19

b. Correlate the following Authorino log entry with the Envoy access log:
c. Correlate the following Limitador log entry with the Envoy and Authorino logs:
3. When you combine the three logs, the story of this request_id is:
At 15:45:12, a user named alice requested the users' API, /api/users. You can also see the 
request_id of a1b2c3d4-e5f6-7890-abcd-ef1234567890.
The request hit the toystore-route in Envoy.
Envoy paused the request and checked authentication with Authorino, see level and info.
Authorino verified Alice’s identity: auth check succeeded, identity, alice.
Simultaneously, Limitador noted the request to ensure that Alice did not exceed her
allowed limit.
Finally, Envoy allowed the traffic through, resulting in a 200 response code.
### ADDITIONAL RESOURCES
Configuring OpenShift Monitoring with Service Mesh
Grafana user documentation
Observability and Service Mesh
OpenShift Service Mesh tracing documentation
Migrating from Jaeger to Red Hat build of OpenTelemetry
Envoy access log documentation
Monitoring stack for Red Hat OpenShift 4.21
  "request_id": "a1b2c3d4-e5f6-7890-abcd-ef1234567890",
  "route_name": "toystore-route"
}
{"level":"info","ts":"2026-01-23T15:45:12.350Z","request_id":"a1b2c3d4-e5f6-7890-abcd-
ef1234567890","msg":"auth check succeeded","identity":"alice"}
Request received: ... "x-request-id": "a1b2c3d4-e5f6-7890-abcd-ef1234567890" ...
Red Hat Connectivity Link 1.3 Observability
20

---

## Connectivity Link Updating

Red Hat Connectivity Link 1.3
Updating Red Hat Connectivity Link
Updating Red Hat Connectivity Link
Last Updated: 2026-03-26

Red Hat Connectivity Link 1.3 Updating Red Hat Connectivity Link
Updating Red Hat Connectivity Link

Legal Notice
Copyright © Red Hat.
Except as otherwise noted below, the text of and illustrations in this documentation are licensed by
Red Hat under the Creative Commons Attribution–Share Alike 3.0 Unported license . If you
distribute this document or an adaptation of it, you must provide the URL for the original version.
Red Hat, as the licensor of this document, waives the right to enforce, and agrees not to assert,
Section 4d of CC-BY-SA to the fullest extent permitted by applicable law.
Red Hat, the Red Hat logo, JBoss, Hibernate, and RHCE are trademarks or registered trademarks of
Red Hat, LLC. or its subsidiaries in the United States and other countries.
Linux ® is the registered trademark of Linus Torvalds in the United States and other countries.
XFS is a trademark or registered trademark of Hewlett Packard Enterprise Development LP or its
subsidiaries in the United States and other countries.
The OpenStack ® Word Mark and OpenStack logo are trademarks or registered trademarks of the
Linux Foundation, used under license.
All other trademarks are the property of their respective owners.
Abstract
This guide provides instruction about how to update Red Hat Connectivity Link.

. . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 
Table of Contents
## UPDATING CONNECTIVITY LINK 1.3
### SUPPORTED CONFIGURATIONS WITH CONNECTIVITY LINK
1.1.1. Supported OpenShift Container Platform version configurations
1.1.2. Supported Operators
1.1.3. Supported cloud providers
1.1.4. Supported cloud DNS providers
1.1.5. Supported on-premise DNS providers
1.1.6. Supported data stores for rate limiting
1.1.7. Supported identity access management
### UPDATING CONNECTIVITY LINK WITH THE OPENSHIFT CONTAINER PLATFORM WEB CONSOLE
### UPDATING TO CONNECTIVITY LINK 1.3 IN THE WEB CONSOLE
### USING YOUR RED HAT SUBSCRIPTION
### ADDITIONAL RESOURCES
3
3
3
3
3
4
4
4
4
5
5
5
6
Table of Contents
1

Red Hat Connectivity Link 1.3 Updating Red Hat Connectivity Link
2
## UPDATING CONNECTIVITY LINK 1.3
You can update your Red Hat Connectivity Link from one version to the next if your supported
configuration meets the requirements of the version you want to update to.
### SUPPORTED CONFIGURATIONS WITH CONNECTIVITY LINK
Connectivity Link must run on a supported combination of OpenShift Container Platform and use the
cert-manager Operator for Red Hat OpenShift. To configure observability, use Red Hat OpenShift
Service Mesh. Red Hat provides both production and development support for supported
configurations and tested integrations according to your subscription agreement.
IMPORTANT
If you use a configuration that includes OpenShift Container Platform 4.18 or older, you
must also use Red Hat OpenShift Service Mesh as the Gateway API provider.
1.1.1. Supported OpenShift Container Platform version configurations
Red Hat Connectivity Link
Red Hat
OpenShift
Container
Platform
Red Hat
OpenShift
Dedicated
Red Hat
OpenShift
Service on
AWS
Microsoft
Azure Red Hat
OpenShift
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
For Microsoft Azure, see the Support lifecycle for Azure Red Hat OpenShift 4 .
1.1.2. Supported Operators
Red Hat Connectivity Link
Red Hat OpenShift Service
Mesh
cert-manager
Operator for
Red Hat
OpenShift
Version 1.3
3.2
1.18
Version 1.2
3.1
1.17
Version 1.1
3.0
1.15
1.1.3. Supported cloud providers
All versions of Connectivity Link support the following platforms as backing cloud providers for
OpenShift Container Platform:
## UPDATING CONNECTIVITY LINK 1.3
3

Amazon Web Services
Google Cloud Platform
Microsoft Azure
For more information, see the documentation for your chosen cloud provider.
1.1.4. Supported cloud DNS providers
For DNS policies, all versions of Connectivity Link support the following cloud DNS providers:
Amazon Route 53
Google Cloud Platform DNS
Microsoft Azure DNS
For more information, see the documentation for your chosen cloud DNS provider.
1.1.5. Supported on-premise DNS providers
You can use CoreDNS can to configure an on-cluster DNS zone. For more information, see About using
on-premise DNS with CoreDNS.
1.1.6. Supported data stores for rate limiting
For rate limiting policies, Connectivity Link supports the following Redis-based data stores for rate limit
counters in multicluster environments:
Red Hat Connectivity Link
Redis
Enterprise or
Cloud
Amazon
Elasticache
Dragonfly
Community or
Cloud
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
For more information, see the documentation for your chosen Redis-based datastore.
1.1.7. Supported identity access management
For authentication policies, Connectivity Link supports API keys and the following products:
Red Hat Connectivity Link Version
Red Hat build of Keycloak
Version 1.3
Version 26.4
Version 1.2
Version 26.4
Red Hat Connectivity Link 1.3 Updating Red Hat Connectivity Link
4

Version 1.1
Version 26.2
Red Hat Connectivity Link Version
Red Hat build of Keycloak
For more information, see Supported Configurations for Red Hat build of Keycloak .
### UPDATING CONNECTIVITY LINK WITH THE OPENSHIFT
CONTAINER PLATFORM WEB CONSOLE
You can update from Connectivity Link 1.2.x to Connectivity Link 1.3 by using the OpenShift Container
Platform web console. Make sure that the OpenShift Container Platform version you have is compatible
with the Connectivity Link version you want to update to. See the Red Hat Connectivity LInk Life Cycle
Policy for details about version support and OpenShift Container Platform compatibility.
IMPORTANT
On OpenShift Container Platform 4.19 or later, if you are updating your cluster from a
previous OpenShift Container Platform version that contains Gateway API Custom
Resource Definitions (CRDs), you must ensure that these resources exactly match the
Gateway API version supported by OpenShift Container Platform 4.19. For more
information, see the OpenShift documentation on managing Gateway API resources .
### UPDATING TO CONNECTIVITY LINK 1.3 IN THE WEB CONSOLE
Update your Red Hat Connectivity Link with the OpenShift Container Platform web console.
Prerequisites
You already have Connectivity Link 1.2.x installed on OpenShift Container Platform 4.19 or later.
Procedure
1. Click Ecosystem > Installed Operators > Red Hat Connectivity Link.
2. Ensure that the Update channel is set to stable.
3. If Update approval is set to Automatic, the update is installed immediately when the Update
channel is set to stable.
4. If Update approval is set to Manual, click Install.
5. Wait until the Connectivity Link Operator is deployed.
6. Verify that Connectivity Link 1.3 is installed and that your deployment is up and running.
### USING YOUR RED HAT SUBSCRIPTION
Red Hat Connectivity Link is provided through a software subscription. To manage your subscriptions,
access your account at the Red Hat Customer Portal.
1. Go to access.redhat.com.
## UPDATING CONNECTIVITY LINK 1.3
5

2. If you do not already have an account, create one.
3. Log in to your account.
4. In the menu bar, click Subscriptions to view and manage your subscriptions.
### ADDITIONAL RESOURCES
Updating installed Operators
Red Hat Connectivity Link 1.3 Updating Red Hat Connectivity Link
6