# Red Hat OpenShift API Management 1

_Compiled from Red Hat Documentation and Customer Portal articles, March 2026._



## Release notes (Customer Portal)

Official release notes for this product are published as a **subscriber Knowledgebase article**, not as an `html-single` guide on docs.redhat.com.

- URL: https://access.redhat.com/articles/6958831

_Automated fetch returned the Customer Portal login wall; paste or mirror content here after signing in._



## Service definition (Customer Portal)

Red Hat OpenShift API Management Service Definition - Red Hat Customer Portal Red Hat Customer Portal - Access to 24x7 support and knowledge 

 

 

 

 

## Introduction

[Application programming interfaces](https://www.redhat.com/en/topics/api)(APIs) are key to [agile integration](https://www.redhat.com/en/topics/integration/why-choose-red-hat-integration) and delivering business value in a digital world. APIs support innovation, enable cross-enterprise agility, and simplify the creation of new products and revenue streams.

The Red Hat OpenShift API Management service makes it easy to manage your APIs. Share, secure, distribute, and control your APIs on an infrastructure platform built for performance, customer control, and future growth.

It is available as an add-on to the following OpenShift-managed services:

- [Red Hat OpenShift Dedicated](https://access.redhat.com/documentation/en-us/openshift_dedicated/4)- a platform for developing and running containerized applications
- [Red Hat OpenShift Service on AWS](https://access.redhat.com/documentation/en-us/red_hat_openshift_service_on_aws/4)- a fully-managed OpenShift service, jointly managed and supported by both Red Hat and Amazon Web Services (AWS)

The Red Hat OpenShift API Management Service is a fully Red Hat-managed API traffic control and API program management solution that includes analytics, access control, developer workflows, and more. It is based on the [Red Hat 3scale API Management platform](https://www.redhat.com/en/technologies/jboss-middleware/3scale).

The API management service also includes an implementation of the [Red Hat Single Sign-on](https://access.redhat.com/products/red-hat-single-sign-on) to secure and protect your APIs. This service is intended solely for use within API Management (e.g., restricting access to your APIs and the 3scale Developer Portal) and not as a company-wide SSO solution.

## Advanced API Management Service Configurations

### Customer OpenShift Dedicated IDP

Because many of the development and notification procedures of the API management service are based on email correspondence, it is important that your identity provider (IDP) contains a valid email address for each enabled developer.

### Tenancy Model

With your subscription you can create up to 20 additional tenants as well as 500 backends and 500 products. This is in addition to the primary tenant that comes pre-configured with the installation of the add-on. You can configure, add, and modify these custom resources after you install Red Hat OpenShift API Management. Refer to [our documentation](https://access.redhat.com/documentation/en-us/red_hat_openshift_api_management/1/topic/993dbe8f-2f1a-4a82-924a-c2dfc5631040) for more information.

### Path-Based Routing

This feature has been supplanted by the products and backends feature in 3scale. More information on this feature can be found in the [Getting Started: Product and Backends](https://access.redhat.com/documentation/en-us/red_hat_3scale_api_management/2.13/html/getting_started/first-steps-with-threescale_configuring-your-api) documentation.

### Staging and Production Managed APIcast

Included in the OpenShift API Management service and managed by Red Hat are two managed APIcast instances: Staging and Production. These are preconfigured by Red Hat and are ready for consumption right away. Both of these are installed in a single cluster and are intended for code promotion in 3scale.

### Self-Managed APIcast & Custom Policies

In the event you need to manage your custom APIs (e.g., in a different region or data center from your Red Hat OpenShift API Management service deployment), your subscription includes the ability to install and self-manage localized APIcast instances.

NOTE: In order to use custom policies, you must install the policies in self-managed APIcast instances.

Commercially reasonable support is included with your subscription and can be accessed by filing a ticket with Red Hat Support. It is the customer's responsibility to monitor and maintain any deployed self-managed APIcast instances.

More information can be found in the [APIcast self-managed Red Hat 3scale API Management](https://access.redhat.com/documentation/en-us/red_hat_3scale_api_management/2.13/html-single/installing_3scale/index#apicast-deployment-options) documentation.

### Billing Support

Red Hat OpenShift API Management has achieved Payment Card Industry Data Security Standard (PCI DSS) Compliance Certification and now supports API Management Billing features.

Currently, Braintree global payments are not supported on Red Hat OpenShift API Management.

For more information on the support provided, please refer to the [Billing Settings](https://access.redhat.com/documentation/en-us/red_hat_3scale_api_management/2.13/html/admin_portal_guide/configure-billing) documentation.

### Invite Feature

All users can access the 3scale API Management Admin Portal through the Red Hat OpenShift API Management service. To add users to the 3scale API Management Admin Portal you must add users by configuring the identity provider for your cluster. For more information, see [Configuring identity providers](https://access.redhat.com/documentation/en-us/openshift_dedicated/4/html/installing_accessing_and_deleting_openshift_dedicated_clusters/config-identity-providers). Although the 3scale Admin Portal appears to have the capability to invite new users to access the Admin Portal, this feature is not currently supported in OpenShift API Management.

## Deployment Models

Red Hat OpenShift API Management Service is available only on the AWS versions of OpenShift Dedicated, including the Red Hat OpenShift Service on AWS. For more details about the AWS version offerings for OpenShift, please refer to the following documentation:

- [OpenShift Dedicated Service Definition](https://access.redhat.com/documentation/en-us/openshift_dedicated/4/html/introduction_to_openshift_dedicated/policies-and-service-definition)
- [Red Hat OpenShift Service on AWS 4](https://access.redhat.com/documentation/en-us/red_hat_openshift_service_on_aws/4)

OpenShift Dedicated and the Red Hat OpenShift Service on AWS 4 can be configured to be private, public, or public/private. Red Hat OpenShift API Management is available in all of those configurations; however, the visibility of the clusters determines which APIs can be managed. It is critical to test your configuration before releasing it to production, to validate that the routes are available and working as expected.

## Environment Isolation for Development, Testing/Staging, and Production

Red Hat recommends separating different environments into distinct clusters with automated build and deployment pipelines, that are used to migrate development or testing clusters to staging and production clusters.

This separation ensures that software in development does not impact your production workloads. In addition, you will have the ability to test newer versions of both OpenShift and the API Management service on pre-production clusters.

## Security and Compliance

As a Red Hat-managed service, the included components and services are installed in protected namespaces (`redhat-rhoam` prefix). These namespaces are monitored and managed by our certified Red Hat SRE teams. Default customer access to these namespaces is restricted; users of the`dedicated-admins` group are given read access for monitoring. Note that if the customer has requested the OpenShift Dedicated`cluster-admin` access, the customer does have the ability to modify settings in these namespaces. If that happens, then there is no longer a guarantee of service availability until the namespaces have been restored to the base Red Hat configuration.

The Red Hat managed services inherit the security and compliance protocols from the OpenShift Dedicated environment. As such, ISO 27001 and PCI certifications are in process, with future work planned for FedRAMP.

## Platform Logging & API Metrics

If you enable the optional OpenShift Dedicated logging stack, the Red Hat OpenShift API Management service logs will be available in the cluster logging stack. Retention and visibility are maintained in the OpenShift Dedicated logging stack. For more information, please refer to the [OpenShift Dedicated Service Definition](https://access.redhat.com/documentation/en-us/openshift_dedicated/4/html/introduction_to_openshift_dedicated/policies-and-service-definition#sdpolicy-logging_osd-service-definition).

Red Hat OpenShift API Management-specific metrics are retained for a maximum of 45 days or up to 50GiB of storage - whichever is reached first. Currently, there is no way to extend this time period or storage limit.

## User Workload Monitoring

If the [User Workload Monitoring](https://docs.openshift.com/dedicated/monitoring/osd-configuring-the-monitoring-stack.html#osd-configuring-the-monitoring-stack) feature of managed OpenShift is enabled and Red Hat OpenShift API Management is installed, users will receive false alerts.

## Scalability and Service Levels

There are a number of subscriptions with associated message quotas available to meet your needs. At this time, you can deploy a single Red Hat OpenShift API Management service on each cluster and must choose a single subscription accordingly. You cannot add multiple subscriptions to increase the quota on an individual cluster.

While the subscription levels are purchased based on a daily total request rate, each subscription is monitored, and the quota is based on the number of calls per minute. Please refer to the following table (Table 1) for the breakdown:

Table 1: Subscription levels and associated quotas

| Subscription level | Maximum throughput |
| --- | --- |
| 100 Thousand API calls per day | 69.4 API calls per minute |
| 1 Million API calls per day | 695 API calls per minute |
| 5 Million API calls per day | 3473 API calls per minute |
| 10 Million API calls per day | 6945 API calls per minute |
| 20 Million API calls per day | 13889 API calls per minute |
| 50 Million API calls per day | 34723 API calls per minute |
| 100 Million API calls per day | 69444 API calls per minute |

Red Hat has specified the compute resources based on an average payload size of 1MB. Your APIs can have different payload sizes which will impact your overall performance and throughput. You are encouraged to run performance tests to validate actual throughput based on your specific APIs and custom API payloads.

You will be notified via email as you approach the maximum throughput for your subscription, as defined in Table 1: Subscription levels and associated quotas.

It is important to note that API calls routed through self-managed APIcast instances also count toward the total quota.

The benchmark is based on the following:

- Lightweight customer-like application (no heavy computation)
- 10% of requests are for authentication
- 45% of requests are GETs against 3scale APIcast
- 45% of requests are POSTs against 3scale APIcast
- POST requests payload up to 1MB
- Use of production APIcast server

NOTE: Red Hat does not support an equivalent load on the staging APIcast. Performance testing should use the production APIcast.

## Resource Requirements

When you install and configure the Red Hat OpenShift API Management service it is automatically distributed to your OpenShift Dedicated compute nodes. Currently, it is not possible to manage the distribution of the Red Hat OpenShift API Management service to specific nodes.

If you have purchased a Multi-AZ OpenShift Dedicated cluster instance, the managed service will automatically be spread across multiple availability zones to minimize service disruptions. For more information on Multi-AZ please refer to the [Availability](https://access.redhat.com/node/5534341/#Availability) section.

The following tables detail the resource requirements for each of the Red Hat OpenShift API Management supported SKUs.

Table 2: Approximate AWS resource requirements for Single-AZ

| Quota | vCPU | Memory | Storage |
| --- | --- | --- | --- |
| 100 Thousand API Calls | 11 Reserved | 24 GiB Reserved | 50GiB Reserved |
| 1 Million API Calls | 11 Reserved | 24 GiB Reserved | 50GiB Reserved |
| 5 Million API Calls | 12 Reserved | 25 GiB Reserved | 50GiB Reserved |
| 10 Million API Calls | 13 Reserved | 25 GiB Reserved | 50GiB Reserved |
| 20 Million API Calls | 14 Reserved | 27 GiB Reserved | 50GiB Reserved |
| 50 Million API Calls | 19 Reserved | 28 GiB Reserved | 50GiB Reserved |
| 100 Million API Calls | 25 Reserved | 32 GiB Reserved | 50GiB Reserved |

Table 3: Approximate AWS resource requirements for Multi-AZ (presumes 3 zones) - Total vCPU

| Quota | vCPU | Memory | Storage |
| --- | --- | --- | --- |
| 1 Million API Calls | 11 Reserved 16 Recommended* | 24 GiB Reserved 36 GiB Recommended* | 150GiB Reserved (50GiB Reserved per AZ) |
| 5 Million API Calls | 12 Reserved 17 Recommended* | 25 GiB Reserved 38 GiB Recommended* | 150GiB Reserved (50GiB Reserved per AZ) |
| 10 Million API Calls | 13 Reserved 19 Recommended* | 25 GiB Reserved 38 GiB Recommended* | 150GiB Reserved (50GiB Reserved per AZ) |
| 20 Million API Calls | 14 Reserved 21 Recommended* | 27 GiB Reserved 41 GiB Recommended* | 150GiB Reserved (50GiB Reserved per AZ) |
| 50 Million API Calls | 19 Reserved 27 Recommended* | 28 GiB Reserved 41 GiB Recommended* | 150GiB Reserved (50GiB Reserved per AZ) |
| 100 Million API Calls | 25 Reserved 37 Recommended* | 32 GiB Reserved 48 GiB Recommended* | 150GiB Reserved (50GiB Reserved per AZ) |

* The additional vCPU and memory for 3 availability zones are to ensure total throughput in the event of a zone loss. The remaining two zones need enough resources to fulfill the demand for the API service.

NOTE: If you install the API Management service on a cluster, it is configured to take resource priority over other customer workloads. Specifically, other pods will be a lower priority and can be stopped to free space for the API Management service according to the table above. To avoid this, please ensure that you have allocated enough compute resources to your OpenShift Dedicated cluster.

If you are a [Customer Cloud Subscription](https://access.redhat.com/documentation/en-us/openshift_dedicated/4/html/getting_started/osd-understanding-your-cloud-deployment-options)(CCS) user, it is important to note that Red Hat leverages AWS Multi-AZ services for Redis and Postgres, including the associated backups. These are required and consume resources from your AWS account as follows:

1 VPC

- Red Hat creates a new VPC and peers it to the cluster default VPC. This VPC contains Red Hat's AWS service instances. The CIDR range for this VPC can be specified at installation.

Redis

- 3 cache.t3.micro instance (2 AZs Enabled)

Postgres

- 3 db.t3.small instances for Postgres (2 AZs Enabled)
- 20GiB of AWS Storage by default auto-scaling to 100 GiB (3 RDS 20GiB each across 2 AZ = 120GiB)

Backups

- S3 buckets for metrics/backups - Size dependent on consumption

Encryption is enabled by default for data at rest for Redis, Postgres and their backups.

NOTE: The CIDR prefix length range must be between`/16` and`/26`. Only CIDR values within this range are permitted. You can use`10.1.0.0/26` as the default CIDR range. The CIDR range must not overlap with any network you would like to peer with in the OpenShift cluster VPC. After submitting the initial configuration, you cannot modify the CIDR range. If you want to change the CIDR range, you must delete and reinstall Red Hat OpenShift API Management.

NOTE: Red Hat OpenShift API Management supports bring-your-own VPC (BYOVPC) configurations. Single-AZ, Multi-AZ, and PrivateLink Multi-AZ installations are [supported](https://access.redhat.com/articles/6981667).

NOTE: For CCS consumers, Red Hat reserves the right to increase the resources required to meet the SLAs of our service. It is expected that the customer will be notified of the increase. To modify your subscription level, please contact your account representative directly or visit [www.redhat.com/en/contact](https://www.redhat.com/en/contact) to submit a request.

NOTE: Changes to the subscription level or quota updates may affect the Redis cache and result in a minor disruption to your Red Hat OpenShift API Management service.

### Cluster Autoscaling

Ensure the minimum resource requirements required for the Red Hat OpenShift API Management service are met at all times for clusters that have autoscaling features enabled. To achieve this, configure the autoscaler so that the cluster has enough resources to satisfy Red Hat OpenShift API Management minimum requirements when the cluster is scaled to the maximum allowable number of nodes.

## Updates and Upgrades

Red Hat will perform periodic maintenance on the Redis instances associated with Red Hat OpenShift API which may include deleting the analytics data to prevent these instances from reaching their limits and potentially degrading or disrupting performance of the platform. The data will be deleted from the instances in a first in, first out (FIFO) procedure and Red Hat will notify customers prior to performing this maintenance. CCS customers may request an increase in their Redis instance size by submitting a support ticket with the request. Non-CCS customers Redis instance sizes are set by Red Hat and may not be increased.

Upgrades of the Managed API product will be scheduled with the customer and rolled out by our SRE team. Non-customer-impacting upgrades and critical CVEs to our management platform will be automatically rolled out to clusters by our SRE team.

### OpenShift Version Support

Red Hat OpenShift API Management is validated against the current version and the version immediately preceding the current version, of the managed OpenShift service only. In the event an issue arises relating to OpenShift API Management where an older version of the OpenShift service is in use, customers are advised to upgrade to a supported version.

## Availability

Red Hat maintains a 99.95% availability for its managed services, including the underlying OpenShift Dedicated managed environment. For more information, refer to Appendix 4 (Online Subscription Services) of the [Red Hat Enterprise Agreements](https://www.redhat.com/en/about/agreements).

Multi-AZ High Availability (HA) deployments are supported if the underlying cluster is configured to be Multi-AZ. In order to support a Multi-AZ HA deployment, the Red Hat OpenShift API Management service is deployed with multiple replicas of the pods that make up the service. These pods are given a set of pod anti-affinity rules that influence the Kubernetes scheduler and should avoid scheduling them on nodes within the same AZ. Additionally, Red Hat has raised the priority of these pods using pod priority, to ensure the Kubernetes scheduler takes the managed service scheduling needs into consideration ahead of other non-infrastructure pods on the cluster.

### Backups and Disaster Recovery

In addition to the daily backups that occur in the OpenShift Dedicated environment, additional snapshots are taken of the managed API service, including its data and configurations. In the event of a catastrophic failure, Red Hat SREs will use a commercially reasonable approach to first recover your OpenShift Dedicated environment, and then your Managed API service.

### Managed Service Removal

The Red Hat OpenShift API Management Service may be removed by the customer from their cluster via the standard add-on deletion flows. Note that once this operation is invoked, this action is non-reversible and cannot be undone. Deletion includes the automatic removal of all Red Hat OpenShift API Management add-on data and backups.

### Getting Support

As a premium offering by Red Hat, you have full access to the [Red Hat Customer Portal](https://access.redhat.com/) with 24x7 production and developer-level support. To achieve the best resolutions, file a ticket whenever you have a question or issue. When opening a support case for the Red Hat OpenShift API Management Service, select the product named “Red Hat OpenShift API Management Service”.

Refer to the [Support Matrix](https://access.redhat.com/support/offerings/production/sla) for more information.

- Product(s)
- [Other](https://access.redhat.com/search?q=Other&amp;documentKind=Article%26Solution)

- Category
- [Supportability](https://access.redhat.com/search?q=Supportability&amp;documentKind=Article%26Solution)

- Component
- [hosted offering](https://access.redhat.com/search?q=hosted+offering&amp;documentKind=Article%26Solution)

- Article Type
- [General](https://access.redhat.com/search?q=General&amp;documentKind=Article%26Solution)

 

 

### Quick Links

- [Downloads](https://access.redhat.com/downloads/)
- [Subscriptions](https://access.redhat.com/management)
- [Support Cases](https://access.redhat.com/support)
- [Customer Service](https://access.redhat.com/support/customer-service)
- [Product Documentation](https://docs.redhat.com/)

### Help

- [Contact Customer Portal](https://access.redhat.com/support/contact/)
- [Customer Portal FAQ](https://access.redhat.com/articles/33844)
- [Log-in Assistance](https://access.redhat.com/help/login_assistance)

### Site Info

- [Trust Red Hat](https://www.redhat.com/en/trust)
- [Browser Support Policy](https://www.redhat.com/en/about/browser-support)
- [Accessibility](https://www.redhat.com/en/about/digital-accessibility)
- [Awards and Recognition](https://access.redhat.com/recognition/)
- [Colophon](https://access.redhat.com/help/colophon/)

### Related Sites

- [redhat.com](https://www.redhat.com/)
- [developers.redhat.com](http://developers.redhat.com/)
- [connect.redhat.com](https://connect.redhat.com/)
- [cloud.redhat.com](https://cloud.redhat.com/)

### About

- [Red Hat Subscription Value](https://access.redhat.com/subscription-value)
- [About Red Hat](https://www.redhat.com/about/)
- [Red Hat Jobs](http://jobs.redhat.com/)

 

- [About Red Hat](https://redhat.com/en/about/company)
- [Jobs](https://redhat.com/en/jobs)
- [Events](https://redhat.com/en/events)
- [Locations](https://redhat.com/en/about/office-locations)
- [Contact Red Hat](https://redhat.com/en/contact)
- [Red Hat Blog](https://redhat.com/en/blog)
- [Inclusion at Red Hat](https://www.redhat.com/en/about/our-culture/inclusion)
- [Cool Stuff Store](https://coolstuff.redhat.com/)
- [Red Hat Summit](https://www.redhat.com/en/summit)

Copyright © 2026 Red Hat

- [Privacy statement](https://redhat.com/en/about/privacy-policy)
- [Terms of use](https://redhat.com/en/about/terms-use)
- [All policies and guidelines](https://redhat.com/en/about/all-policies-guidelines)
- [Digital accessibility](https://redhat.com/en/about/digital-accessibility)
-



## Getting started

Getting Started with Red Hat OpenShift API Management | Red Hat OpenShift API Management | 1 | Red Hat Documentation

 

 

 

 

 

 

 

 

# Getting Started with Red Hat OpenShift API Management

---

Red Hat OpenShift API Management 1

## Getting started with your Red Hat OpenShift API Management installation.

Red Hat Customer Content Services

Legal Notice

Abstract

This guide provides information about how you can start working with Red Hat OpenShift API Management.

---

## Making open source more inclusiveCopy link

Red Hat is committed to replacing problematic language in our code, documentation, and web properties. We are beginning with these four terms: master, slave, blacklist, and whitelist. Because of the enormity of this endeavor, these changes will be implemented gradually over several upcoming releases. For more details, see our [CTO Chris Wright’s message](https://www.redhat.com/en/blog/making-open-source-more-inclusive-eradicating-problematic-language).

## Chapter 1. Introduction to Red Hat OpenShift API ManagementCopy link

Learn about the features and functions available in the Red Hat OpenShift API Management cloud service.

### 1.1. What is OpenShift API ManagementCopy link

Red Hat OpenShift API Management is a cloud service for creating, securing, and publishing your APIs. The OpenShift API Management service is an add-on for Red Hat OpenShift Dedicated and Red Hat OpenShift Service on AWS. The service is based on the Red Hat 3scale API Management platform and also includes an implementation of Red Hat Single Sign-On.

##### Understanding Red Hat 3scale API Management

Application Programming Interface (API) management refers to the processes of distributing, controlling, and analyzing the APIs that connect applications and data across cloud environments.

Red Hat OpenShift API Management provides a management platform that allows users to share, secure, distribute, control, and monetize APIs. After setting up authentication and user accounts, OpenShift API Management developers, also referred to as API providers, can configure, and publish their APIs.

The main OpenShift API Management components include:

- Legal Notice
- APIcast - the 3scale API gateway
- Admin Portal - the 3scale console that API providers work in
- Developer Portal - the interface for API consumers
- Red Hat Single Sign-On - for authenticating access to the Developer Portal as well as to APIs

API providers are developers who work in the 3scale Admin Portal, for which an administrator has given them accounts. API providers also work in the OpenShift Dedicated cluster to deploy applications, such as a backend for service API requests. API providers create and publish APIs, and can configure Red Hat Single Sign-On authentication to secure APIs. 3scale separates APIs into two main groups:

- Backends are internal APIs bundled in a product. Backends grant API providers the freedom to map internal API organization structures to 3scale. A backend contains a private URL for an internal API. It is exposed through mapping rules and the public URL of one or more 3scale products.
- Products are customer-facing APIs. Products facilitate the creation of robust yet simplified offerings for API consumers. A product includes application plans and configuration of the APIcast gateway. A product can bundle multiple backends.

When a 3scale product is ready for use, an API provider publishes it in the Developer Portal. API consumers visit the Developer Portal to subscribe to a plan that enables them to use the 3scale product that contains that API. Consumers can then call the API’s operations, subject to any usage policies that may be in effect.

##### Understanding Red Hat Single Sign-On

Red Hat Single Sign-On provides single sign-on (SSO) authentication to secure web applications. You use this SSO implementation to control access to 3scale Developer Portals and to 3scale API products. It is not supported as a company-wide SSO solution.

##### Red Hat OpenShift API Management considerations

Red Hat OpenShift API Management introduces several product considerations that need to be thoroughly understood before proceeding with the installation and configuration of the service:

Authentication Options: OpenShift API Management provides various authentication options within the service to ensure secure access control and identity verification:

- OAuth 2.0: An authorization framework that enables secure and delegated access to APIs. OAuth 2.0 allows users and applications to obtain limited, scoped access tokens, which can be used to authenticate and authorize API requests.
- OpenID Connect: An identity layer built on top of OAuth 2.0 that provides additional features for authentication, such as user profile information and identity federation. OpenID Connect allows users to authenticate using their existing accounts from various identity providers.
- LDAP (Lightweight Directory Access Protocol): A protocol commonly used for accessing and managing directory information. LDAP integration enables organizations to leverage their existing user directories for authentication within OpenShift API Management.
- Token-based authentication: A mechanism that involves exchanging a token for authentication purposes. Tokens are typically issued by an identity provider or authentication service and can be used to validate and authorize API requests.

Custom Wildcard Domain: A wildcard domain name allows you to handle dynamic routing of API traffic across various endpoints and services within your infrastructure. By using a wildcard DNS record (for example`*.example.com`), you can ensure that any subdomain under the specified domain is automatically routed to the corresponding API service or endpoint. This flexibility is particularly useful when dealing with multiple APIs or microservices, as it simplifies the management of API endpoints and enables dynamic scaling and routing. To configure a custom wildcard domain name with 3scale and Red Hat OpenShift API Management, you would typically do the following:

- Obtain a registered domain: You need to have a registered domain name that you own and have administrative control over.
- Configure DNS settings: Update your DNS settings for the domain to include a wildcard DNS record pointing to the appropriate IP address or load balancer associated with your API infrastructure.
- Obtain an SSL/TLS certificate: Obtain an SSL/TLS certificate for your custom wildcard domain name to ensure secure communication between clients and your API services. This certificate can be either self-signed or issued by a trusted certificate authority (CA).
- Configure 3scale and OpenShift API Management: In the configuration settings of both 3scale and OpenShift API Management, specify the custom wildcard domain name as the endpoint for your APIs. This ensures that API requests made to any subdomain under the wildcard domain are correctly routed and processed by the respective API services.

SMTP (Simple Mail Transfer Protocol): SMTP is a widely used standard protocol for email transmission. In the context of OpenShift API Management, SMTP configuration allows you to specify the email server settings required for email notifications, alerts, and communication within the system. By providing the necessary SMTP details, such as the server address, port number, authentication credentials, and encryption settings, you enable the platform to send emails seamlessly. To successully apply an SMTP configuration, you must enter values for all related fields. Values for all Custom SMTP fields are required, if you specify values for any of the fields. Entering an SMTP configuration is optional. Red Hat OpenShift API Management default values are applied if you leave the SMTP configuration fields blank. You can enter values for the following fields:

- Custom SMTP Mail Server Address - The remote mail server as a relay
- Custom SMTP From Address - Email address of the outgoing mail
- Custom SMTP Username - The mail server username
- Custom SMTP Password - The mail server password
- Custom SMTP Port - The port on which the mail server is listening for new connections

VPC Configurations: A VPC (Virtual Private Cloud) is a virtual network infrastructure that allows you to provision and manage network resources within a logically isolated environment. OpenShift API Management supports the option to bring your own VPC, which means you can use your existing VPC setup instead of relying on the default networking configuration. The following Availability Zone (AZ) scenarios represent the tested configurations. Configurations that differ from the following, may not work as expected and are not supported.

- Single-AZ installation: The tested architecture includes a VPC with an internet gateway, an availability zone containing a public subnet, and a private subnet.
- Multi-AZ installation: The tested architecture includes a VPC with an internet gateway, up to three availability zones (with each containing one public subnet), and a private subnet.
- PrivateLink Multi-AZ installation: The tested architecture includes connections to clusters using AWS PrivateLink endpoints instead of public endpoints for OpenShift Service on AWS (ROSA) or OpenShift Dedicated (OSD).

### 1.2. How to set up OpenShift API ManagementCopy link

A Red Hat OpenShift Dedicated cluster administrator sets up the cluster and identity provider and adds the OpenShift API Management service to a cluster. Then, you configure the service users.

If desired, you can customize APIcast, which is the interface that handles calls to a 3scale API product.

Note

In Red Hat OpenShift API Management documentation, ignore content for 3scale Hosted (SaaS). It does not apply to OpenShift API Management.

##### Configure an identity provider

If an identity provider is already configured, there is no need to configure another one. Otherwise, you must choose and configure an identity provider, which can be LDAP, GitHub, GitHub Enterprise, Google, or OpenID Connect.

Instructions:

- [Configuring identity providers](https://access.redhat.com/documentation/en-us/openshift_dedicated/4/html/getting_started/osd-getting-started#config-idp_osd-getting-started)

##### Add OpenShift API Management

Adding OpenShift API Management to a cluster makes the service available for use by 3scale API providers. You can add OpenShift API Management to an OpenShift Dedicated cluster, or to a ROSA cluster.

Instructions:

- [Adding OpenShift API Management to your cluster](https://docs.redhat.com/en/documentation/red_hat_openshift_api_management/1/html-single/adding_and_accessing_red_hat_openshift_api_management/index#adding-openshift-api-management-to-your-cluster_openshift-api-management-adding)
- [Adding OpenShift API Management to your Red Hat OpenShift Service on AWS cluster](https://docs.redhat.com/en/documentation/red_hat_openshift_api_management/1/html-single/adding_and_accessing_red_hat_openshift_api_management/index#adding-openshift-api-management-to-your-rosa-cluster_openshift-api-management-adding)

##### Configure 3scale API provider account permissions

In the 3scale Admin Portal, configure account permissions so that API providers in your organization can create, configure, and launch 3scale API products.

When a new user logs in to the OpenShift Dedicated cluster by using the configured identity provider, the user automatically receives an OpenShift account with permission to access OpenShift API Management.

You manage these accounts in the 3scale Admin Portal.

By default, Single Sign-On is configured for 3scale in OpenShift API Management.

Instructions:

- [Red Hat Single Sign-On for the 3scale Admin Portal](https://access.redhat.com/documentation/en-us/red_hat_3scale_api_management/2.13/html-single/admin_portal_guide/index#inviting-users-managing-rights)

### 1.3. How to use OpenShift API ManagementCopy link

Use OpenShift API Management to create, secure, and publish your APIs.

##### Get started with 3scale

You can use the 3scale wizard to start learning about how to add and test a 3scale API product.

Instructions:

- [First steps with 3scale](https://access.redhat.com/documentation/en-us/red_hat_3scale_api_management/2.13/html-single/getting_started/index#first-steps-with-threescale_configuring-your-api)

##### Create and configure an API

In the 3scale Admin Portal, create and configure an API to ensure that access is protected by API keys, tracked, and monitored by 3scale with basic rate limits and controls in place.

This involves the following steps:

- Create API backends
- Create API products
- Create mapping rules and application plans to define a customer-facing API product
- Capture metrics
- Configure API access rules

Mapping rules define the metrics or methods to report. Application plans define the rules such as limits, pricing, and features for using an API product. An application subscribes to an application plan.

Instructions:

- [Adding and configuring APIs](https://access.redhat.com/documentation/en-us/red_hat_openshift_api_management/1/guide/0e5d14ec-dc6e-480f-88ff-3f769c675cdf)

##### Configure APIcast policies

APIcast is the 3scale API gateway, which is the endpoint that accepts API product calls and routes them to bundled backends. OpenShift API Management provides APIcast staging for developing and testing APIs and also APIcast production, for published APIs.

APIcast policies are units of functionality that modify how APIcast operates. Policies can be enabled, disabled, and configured to control APIcast behavior. Use custom policies to add functionality that is not available in a default APIcast deployment.

Instructions:

- [APIcast policies](https://access.redhat.com/documentation/en-us/red_hat_3scale_api_management/2.13/html-single/administering_the_api_gateway/index#apicast-policies)

##### Secure your API

If you want to secure your API by using OpenID and OAuth, then in the Red Hat Single Sign-On Admin Console, create a Red Hat Single Sign-On realm. An SSO realm is required to manage authentication for access to the Developer Portal and 3scale API products.

In the 3scale Admin Portal, set up authentication to control access to your API product and to the 3scale Developer Portal.

Instructions:

- [Enabling and disabling authentication through Red Hat Single Sign-On](https://access.redhat.com/documentation/en-us/red_hat_3scale_api_management/2.13/html-single/creating_the_developer_portal/index#enabling-disabling-authentication-rhsso)

##### Set up a 3scale Developer Portal

A well-structured developer portal and great documentation are key elements to assure adoption. A developer portal is the main hub for managing interactions with API consumers and for API consumers to access their API keys in a secure way.

In the 3scale Admin Portal, add OpenAPI Specification 3.0 conforming documents for use in a Developer Portal. API consumers use the Developer Portal to access the APIs defined in these documents.

Then, configure the Developer Portal and add your APIs.

Instructions:

- [Providing APIs in the Developer Portal](https://access.redhat.com/documentation/en-us/red_hat_3scale_api_management/2.13/html-single/providing_apis_in_the_developer_portal)
- [Discover and import APIs available in your OpenShift Dedicated cluster](https://access.redhat.com/documentation/en-us/red_hat_3scale_api_management/2.13/html-single/admin_portal_guide/index#service-discovery_service-discovery)
- [Summary: from zero to hero Developer Portal](https://access.redhat.com/documentation/en-us/red_hat_3scale_api_management/2.12/html-single/creating_the_developer_portal/index)

##### Set up monitoring and analytics for your API

You can designate methods in your API and add metrics to set access limits for any of an API product’s application plans. For an API backend, methods and metrics can be used to set access limits in the application plan of any API product that bundles the backend.

Instructions:

- [Designating methods and adding metrics for capturing usage details](https://access.redhat.com/documentation/en-us/red_hat_3scale_api_management/2.13/html-single/admin_portal_guide/index#designating-methods-and-adding-metrics-for-capturing-usage-details_methods-and-metrics-for-products-and-backends)
- [API analytics](https://access.redhat.com/documentation/en-us/red_hat_3scale_api_management/2.13/html-single/admin_portal_guide/index#analytics)

##### Launch the API product

After you have configured and secured your API and created a Developer Portal, you can launch your API so that consumers can begin to use it.

Instructions:

- [Going live](https://access.redhat.com/documentation/en-us/red_hat_3scale_api_management/2.13/html-single/getting_started/index#going-live)

##### Monitor your API

After your API is launched, you can monitor metrics that indicate how it is being used. Knowing how a 3scale API product is used is a crucial step for managing traffic, provisioning for peaks, and identifying the users who most often send requests to the API product.

Instructions:

- [Viewing 3scale built-in traffic analytics for applications](https://access.redhat.com/documentation/en-us/red_hat_3scale_api_management/2.13/html-single/admin_portal_guide/index#viewing-threescale-built-in-traffic-analytics-for-applications_analytics-for-threescale-apis)

### 1.4. Get OpenShift API ManagementCopy link

To get OpenShift API Management, you can add it to your OpenShift Dedicated cluster or ROSA cluster. To learn more, go to [https://cloud.redhat.com/application-services/overview](https://cloud.redhat.com/application-services/overview).

## Legal NoticeCopy link

Copyright © 2025 Red Hat, Inc.

The text of and illustrations in this document are licensed by Red Hat under a Creative Commons Attribution–Share Alike 3.0 Unported license ("CC-BY-SA"). An explanation of CC-BY-SA is available at [http://creativecommons.org/licenses/by-sa/3.0/](http://creativecommons.org/licenses/by-sa/3.0/). In accordance with CC-BY-SA, if you distribute this document or an adaptation of it, you must provide the URL for the original version.

Red Hat, as the licensor of this document, waives the right to enforce, and agrees not to assert, Section 4d of CC-BY-SA to the fullest extent permitted by applicable law.

Red Hat, Red Hat Enterprise Linux, the Shadowman logo, the Red Hat logo, JBoss, OpenShift, Fedora, the Infinity logo, and RHCE are trademarks of Red Hat, Inc., registered in the United States and other countries.

Linux® is the registered trademark of Linus Torvalds in the United States and other countries.

Java® is a registered trademark of Oracle and/or its affiliates.

XFS® is a trademark of Silicon Graphics International Corp. or its subsidiaries in the United States and/or other countries.

MySQL® is a registered trademark of MySQL AB in the United States, the European Union and other countries.

Node.js® is an official trademark of Joyent. Red Hat is not formally related to or endorsed by the official Joyent Node.js open source or commercial project.

The OpenStack® Word Mark and OpenStack logo are either registered trademarks/service marks or trademarks/service marks of the OpenStack Foundation, in the United States and other countries and are used with the OpenStack Foundation's permission. We are not affiliated with, endorsed or sponsored by the OpenStack Foundation, or the OpenStack community.

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



## Adding and accessing

Adding and accessing Red Hat OpenShift API Management | Red Hat OpenShift API Management | 1 | Red Hat Documentation

 

 

 

 

 

 

 

 

# Adding and accessing Red Hat OpenShift API Management

---

Red Hat OpenShift API Management 1

## Adding and accessing Red Hat OpenShift API Management.

Red Hat Customer Content Services

Legal Notice

Abstract

This guide provides information about how you can get started with adding and accessing Red Hat OpenShift API Management.

---

## Making open source more inclusiveCopy link

Red Hat is committed to replacing problematic language in our code, documentation, and web properties. We are beginning with these four terms: master, slave, blacklist, and whitelist. Because of the enormity of this endeavor, these changes will be implemented gradually over several upcoming releases. For more details, see our [CTO Chris Wright’s message](https://www.redhat.com/en/blog/making-open-source-more-inclusive-eradicating-problematic-language).

## Chapter 1. Adding and accessing OpenShift API ManagementCopy link

Red Hat OpenShift API Management is a managed API traffic control and program management service based on the Red Hat 3scale API Management platform. It includes an implementation of Red Hat Single Sign-On. You can add OpenShift API Management to an existing OpenShift Dedicated or ROSA cluster in the OpenShift Cluster Manager.

### 1.1. Introduction to Red Hat OpenShift API ManagementCopy link

Application Programming Interface (API) management refers to the processes of distributing, controlling, and analyzing the APIs that connect applications and data across cloud environments. You can streamline API management in a unified service by using Red Hat OpenShift API Management.

Red Hat OpenShift API Management is a managed service that is based on the Red Hat 3scale API Management platform and is available as an add-on in Red Hat OpenShift Dedicated. With OpenShift API Management you can secure, manage, and monitor APIs at every stage of the development lifecycle. Use OpenShift API Management to manage users, oversee the API gateway, and configure APIs for increased accessibility.

You can deploy Red Hat OpenShift API Management on Amazon Web Services (AWS) versions of OpenShift Dedicated and the Red Hat OpenShift Service on AWS. OpenShift API Management includes capabilities of the following Red Hat products:

- Legal Notice
- [Red Hat Single Sign-On](https://access.redhat.com/documentation/en-us/red_hat_single_sign-on/7.6/) provides single sign-on (SSO) authentication to secure web applications.
- [Red Hat 3scale API Management](https://access.redhat.com/documentation/en-us/red_hat_3scale_api_management/2.13/) is a management platform that allows users to share, secure, distribute, control, and monetize APIs. After setting up authentication and user accounts, OpenShift API Management developers, also referred to as API providers, can configure, and publish 3scale API products.

The following actions are important to understand before you use Red Hat OpenShift API Management:

Configuring your [identity provider](https://access.redhat.com/documentation/en-us/openshift_dedicated/4/html/getting_started/osd-getting-started#config-idp_osd-getting-started) to provision OpenShift Dedicated and secure APIs.

Note

The email address and the User Principal Name (UPN) must be paired for Red Hat Single Sign-On configured with OpenID to work between OpenShift and Red Hat 3scale API Management. The UPN and email must match or the OpenID UPN and email fields must map to the username field and email field of the customer in the OpenShift IDP configuration.

- Accessing the [Red Hat OpenShift API Management service definition](https://access.redhat.com/articles/5534341), to understand the features, considerations, limits, and alerts of OpenShift API Management.
- Understanding the roles in Red Hat OpenShift API Management to recognize user permissions.
- Accessing the workflows for Red Hat OpenShift API Management to understand how to create, secure, and publish APIs.
- Setting the Classless Inter-Domain Routing (CIDR) block to ensure there is no overlap with any network that the Red Hat OpenShift API Management customer would like to peer with in the OpenShift cluster virtual private cloud (VPC). You can click the link in the OpenShift Cluster Manager to apply the default CIDR block, or you can provide a custom CIDR block. After submitting the initial configuration, you cannot modify the CIDR block. If you want to change the CIDR block, you must delete and reinstall Red Hat OpenShift API Management.
- Optional: Creating a [service control policy](https://access.redhat.com/articles/5581801) for Customer Cloud Subscription (CCS) users who must create an AWS OpenShift Dedicated cluster.

### 1.2. Adding OpenShift API Management to your clusterCopy link

Red Hat OpenShift API Management can be added to a Red Hat OpenShift Dedicated cluster or a Red Hat OpenShift Service on AWS cluster. You can use the OpenShift Cluster Manager to add OpenShift API Management to an existing Red Hat OpenShift Dedicated cluster, to make the service available for use by API providers.

Prerequisites

You have provisioned an OpenShift Dedicated cluster or an ROSA cluster, that meets the product requirements for adding the OpenShift API Management service, as outlined in the Red Hat OpenShift API Management service definition.

Note

You cannot use the Red Hat OpenShift Cluster Manager to add the Red Hat OpenShift API Management to a Red Hat OpenShift Service on AWS cluster. You must use the CLI to [add Red Hat OpenShift API Management to a Red Hat OpenShift Service on AWS cluster](https://access.redhat.com/documentation/en-us/red_hat_openshift_api_management/1/guide/53dfb804-2038-4545-b917-2cb01a09ef98#_87a4ad29-2e4a-42b6-8650-52309097b607).

You have configured your [identity provider (IDP)](https://access.redhat.com/documentation/en-us/openshift_dedicated/4/html/getting_started/osd-getting-started#config-idp_osd-getting-started).

Note

Email addresses must match for Red Hat Single Sign-On configured with OpenID to work between OpenShift and Red Hat 3scale API Management. The User Principal Name (UPN) and email must match or the OpenID UPN and email fields must map to the username field and email field of the customer in the OpenShift IDP configuration.

Procedure

Enter the following URL in a browser:

```plaintext
https://console.redhat.com
```

Enter the CIDR value in the CIDR Range field.

Note

The CIDR prefix length range must be between`/16` and`/26`. Only CIDR values within this range are permitted. You can use`10.1.0.0/26` as the default CIDR range.

Note

The CIDR range must not overlap with any network you would like to peer within the OpenShift cluster VPC. If you do not specify a CIDR value, click the link in the OpenShift Cluster Manager to apply the default CIDR range. After submitting the initial configuration, you cannot modify the CIDR range. If you want to change the CIDR range, you must delete and reinstall Red Hat OpenShift API Management.

In the Quota field, select a Daily Rate Limit Quota from the drop-down menu.

Note

You cannot select a daily rate limit quota that exceeds the SKU specified and assigned during the purchase of the Red Hat OpenShift API Management service. For example, if you purchased a 10 Million SKU, you cannot apply a daily rate limit quota greater than 10 Million.

Note

The Evaluation option has a daily rate limit of 100,000 API calls and does not include production-level support. You can select the Evaluation option from the Quota drop-down menu. For more information on accessing all features of OpenShift API Management, refer to the Red Hat OpenShift API Management service definition.

Optional: In the 3scale custom wildcard domain name field, enter the designated domain name.

Note

Adding the custom wildcard domain name is optional. If you want to add it to your add-on service, you must [configure the custom wildcard domain](https://docs.openshift.com/dedicated/applications/deployments/osd-config-custom-domains-applications.html) before installing Red Hat OpenShift API Management to the cluster using the OpenShift Cluster Manager. You cannot add or modify the domain name after installing the add-on service using the OpenShift Cluster Manager. You can only change the 3scale routes in the 3scale Admin Portal after the initial configuration and installation.

Note

Red Hat OpenShift API Management will fail to install if the wildcard domain name add-on value is not the same as the domain name in the custom domain custom resource. You cannot use a self-signed wildcard certificate.

Optional: Enter values for an SMTP configuration. Red Hat OpenShift API Management provides a default SMTP server and applies default values if you do not specify an SMTP configuration. You can enter values for the following fields:

Custom SMTP Port - The port on which the mail server is listening for new connections

Note

To successully apply an SMTP configuration, you must enter values for all related fields. Values for all Custom SMTP fields are required, if you specify values for any of the fields.

Note

Entering an SMTP configuration is optional. Red Hat OpenShift API Management default values are applied if you leave the SMTP configuration fields blank.

Note

You can edit the SMTP configuration after installing the OpenShift API Management add-on.

- Custom SMTP Mail Server Address - The remote mail server as a relay
- Custom SMTP From Address - Email address of the outgoing mail
- Custom SMTP Username - The mail server username
- Custom SMTP Password - The mail server password

Verification

Ensure Installed and a check mark are displayed on the Red Hat OpenShift API Management option.

Note

The Installed status is displayed before Red Hat OpenShift API Management is completely installed. To verify installation is complete, check the operator status in the console.

1. Click Install.
2. Click Open in Console to see the Red Hat OpenShift API Management operator details.

### 1.3. Adding OpenShift API Management to your Red Hat OpenShift Service on AWS clusterCopy link

Red Hat OpenShift Service on AWS (ROSA) allows Red Hat to deploy OpenShift clusters into an existing Amazon Web Service (AWS) account.

You can add OpenShift API Management to your ROSA cluster to make the service available for use by API providers.

You can install the OpenShift API Management add-on to an ROSA cluster using the`rosa` CLI.

Prerequisites

- You have [configured the rosa CLI](https://access.redhat.com/documentation/en-us/red_hat_openshift_service_on_aws/4/html/rosa_cli/rosa-get-started-cli).
- You have [provisioned an ROSA cluster](https://access.redhat.com/documentation/en-us/red_hat_openshift_service_on_aws/4/html-single/getting_started/index#rosa-getting-started-environment-setup_rosa-getting-started) that meets the [requirements](https://access.redhat.com/documentation/en-us/red_hat_openshift_service_on_aws/4/html-single/prepare_your_environment/index#rosa-planning-environment) for adding the OpenShift API Management service.
- You have configured the identity provider (IDP).
- You are [logged in to your Red Hat OpenShift Service on AWS cluster](https://access.redhat.com/documentation/en-us/red_hat_openshift_service_on_aws/4/html-single/getting_started/index#rosa-getting-started-access-cluster-web-console_rosa-getting-started).

Procedure

Enter the following command in the`rosa` CLI to add the Red Hat OpenShift API Management to the Red Hat OpenShift Service on AWS cluster :

```plaintext
rosa install addon --cluster=<cluster_name> managed-api-service
```

- Replace` ` with the name of your cluster in ROSA.

Interactive mode opens. In interactive mode you are prompted to enter a CIDR range and the email addresses that should receive Red Hat OpenShift API Management service notifications.

Enter the CIDR range.

Note

The CIDR prefix length range must be between`/16` and`/26`. Only CIDR values within this range are permitted. You can use`10.1.0.0/26` as the default CIDR range.

Note

The CIDR range must not overlap with any network you would like to peer with in the OpenShift cluster VPC. After submitting the initial configuration, you cannot modify the CIDR range. If you want to change the CIDR range, you must delete and reinstall Red Hat OpenShift API Management.

Enter the email address you would like to receive OpenShift API Management service notifications.

Note

You can enter multiple email addresses to receive service notifications. Use a comma to separate multiple email addresses.

### 1.4. Adding OpenShift API Management to your STS enabled Red Hat OpenShift Service on AWS clusterCopy link

Red Hat OpenShift Service on AWS (ROSA) allows Red Hat to deploy OpenShift clusters into an existing Amazon Web Service (AWS) account. Red Hat OpenShift Service on AWS supports AWS Security Token Service (STS) for authentication with AWS APIs.

AWS STS is a global web service that provides short-term credentials for IAM or federated users. Red Hat OpenShift Service on AWS with STS is the recommended credential mode for ROSA clusters. You can use AWS STS with ROSA to allocate temporary, limited-privilege credentials for component-specific IAM roles. The service enables cluster components to make AWS API calls using secure cloud resource management practices.

You can add OpenShift API Management to your ROSA cluster to make the service available to API providers that use AWS STS for authentication. Create an AWS IAM role and policies that are required for installing OpenShift API Management onto a cluster that uses AWS STS.

Prerequisites

- You have installed the [AWS CLI](https://docs.aws.amazon.com/cli/latest/userguide/getting-started-install.html).
- You have installed the [OpenShift CLI](https://access.redhat.com/documentation/en-us/openshift_container_platform/4.7/html/cli_tools/openshift-cli-oc).
- You have [configured the rosa CLI](https://access.redhat.com/documentation/en-us/red_hat_openshift_service_on_aws/4/html/rosa_cli/rosa-get-started-cli).

Procedure

In the AWS CLI, create a policy for SRE Support. Enter the following:

```plaintext
cat <<EOM >"rhoam-sre-support-policy.json"
{
    "Version": "2012-10-17",
    "Statement": [
        {
            "Effect": "Allow",
            "Action": [
                "rds:DescribeDBInstances",
                "rds:DescribeDBClusters",
                "rds:DescribeGlobalClusters",
                "rds:ModifyDBInstance",
                "rds:DeleteDBInstance",
                "rds:DescribeDBSnapshots",
                "rds:RestoreDBInstanceFromDBSnapshot",
                "elasticache:DescribeReplicationGroups",
                "elasticache:ModifyReplicationGroup",
                "elasticache:DescribeSnapshots",
                "elasticache:CreateReplicationGroup",
                "elasticache:DescribeCacheClusters",
                "elasticache:DeleteReplicationGroup",
                "sts:GetCallerIdentity",
                "tag:TagResources"
            ],
            "Resource": "*"
        }
    ]
}
EOM
```

In the AWS CLI, enter the following command to attach the Red Hat OpenShift API Management SRE policy to the OpenShift Support Role:

```plaintext
aws iam put-role-policy --role-name ManagedOpenShift-Support-Role --policy-name rhoam-sre-support-policy --policy-document "file://rhoam-sre-support-policy.json"
```

In the`rosa` CLI, enter the following command to add the Red Hat OpenShift API Management to the Red Hat OpenShift Service on AWS cluster:

```plaintext
rosa install addon --cluster <cluster-name> managed-api-service -y --addon-resource-required true --rosa-cli-required true --billing-model standard
```

- Replace` ` with the name of your cluster in ROSA.

### 1.5. Accessing OpenShift API Management in your clusterCopy link

In OpenShift API Management you can create, secure, and publish APIs. You can access OpenShift API Management from the OpenShift application launcher in the OpenShift Cluster Manager.

Prerequisites

- Red Hat OpenShift API Management was added to your OpenShift Dedicated or ROSA cluster.

Procedure

Enter the following URL in a browser:

```plaintext
https://console.redhat.com
```

1. Log in to your Red Hat account.
2. Click All apps and services and then click API Management from the drop-down menu. The OpenShift Cluster Manager console is displayed.
3. In the OpenShift Cluster Manager, click Clusters in the menu.
4. Select the cluster with OpenShift API Management installed, from the list of clusters.
5. Click the Add-ons tab.
6. Select the Red Hat OpenShift API Management option.
7. On the Red Hat OpenShift API Management option, click View in console. The Red Hat OpenShift Dedicated console opens.
8. Click the application launcher in the OpenShift Dedicated console.
9. Select OpenShift API Management from the OpenShift Managed Services drop-down menu. A new browser tab opens.
10. Use Red Hat Single Sign-On to authenticate your login.

### 1.6. Supported bring-your-own VPC architecturesCopy link

Red Hat OpenShift API Management supports the use of a bring-your-own virtual private cloud (BYOVPC) configuration. The following Availability Zone (AZ) scenarios represent the tested configurations. Configurations that differ from the following, may not work as expected and are not supported.

PrivateLink Multi-AZ installation: The tested architecture includes connections to clusters using AWS PrivateLink endpoints instead of public endpoints for ROSA (ROSA) or OpenShift Dedicated (OSD).

The following domains are required and must be allowed through the firewall in order to successfully configure the Red Hat OpenShift API Management with PrivateLink:

- `echo-api.3scale.net`
- `gcr.io`
- `grafana.com`
- `stats.grafana.org`
- `.sendgrid.net`
- `raw.githubusercontent.com`
- `objects.githubusercontent.com`

The following domains shoud not be blocked by the firewall:

- `cloud.redhat.com`
- `aws.amazon.com`
- `www.redhat.com`
- `www.okd.io`
- `access.redhat.com`
- `observatorium.api.openshift.com`

### 1.7. Deleting OpenShift API Management from your clusterCopy link

Important

When you delete Red Hat OpenShift API Management from your cluster, backups of the databases are automatically created in your Amazon Web Services (AWS) account:

- RDS for PostgreSQL: Backups are under 'Snapshots'.
- ElastiCache for Redis: Backups are under 'Backups'.

Backups persist after Red Hat OpenShift API Management deletion and require manual removal. To change this default behavior, add the annotation`skip_final_db_snapshots: 'true'` to the Red Hat Managed Integration (RHMI) custom resource (CR) before deletion:

```shell-session
$ oc patch rhmi rhoam -n redhat-rhoam-operator --type=merge --patch '{"metadata":{"annotations": {"skip_final_db_snapshots": "true"}}}'
```

Failure to delete backups may result in continued storage costs.

You can delete the Red Hat OpenShift API Management add-on service from your OpenShift Dedicated cluster through the OpenShift Cluster Manager(OCM) or the OCM CLI.

To delete the add-on service using the OpenShift Cluster Manager CLI, enter the following command:

```plaintext
ocm delete api/clusters_mgmt/v1/clusters/<cluster_id>/addons/<addon_id>
```

- Replace the` ` with the identification tag for your OpenShift Dedicated cluster.
- Replace the` ` with the identification tag for the Red Hat OpenShift API Management add-on service.

Note

The following procedure describes how to delete a Red Hat OpenShift API Management add-on service through OCM.

Prerequisites

- Red Hat OpenShift API Management was added to your OpenShift Dedicated cluster.

Procedure

1. Navigate to the Clusters page in the OpenShift Cluster Manager.
2. Select the cluster with the installed instance of Red Hat OpenShift API Management you want to delete.
3. Click the Add-ons tab.
4. Locate the installed OpenShift API Management service you want to delete.
5. From the installed Red Hat OpenShift API Management option, click the main menu.
6. Click Uninstall add-on from the drop-down menu.
7. Enter the Red Hat OpenShift API Management name in the confirmation message that is displayed.
8. Click Uninstall. You return to the Add-ons tab and an uninstalling state icon is displayed on the Red Hat OpenShift API Management service option you deleted.

## Legal NoticeCopy link

Copyright © 2024 Red Hat, Inc.

The text of and illustrations in this document are licensed by Red Hat under a Creative Commons Attribution–Share Alike 3.0 Unported license ("CC-BY-SA"). An explanation of CC-BY-SA is available at [http://creativecommons.org/licenses/by-sa/3.0/](http://creativecommons.org/licenses/by-sa/3.0/). In accordance with CC-BY-SA, if you distribute this document or an adaptation of it, you must provide the URL for the original version.

Red Hat, as the licensor of this document, waives the right to enforce, and agrees not to assert, Section 4d of CC-BY-SA to the fullest extent permitted by applicable law.

Red Hat, Red Hat Enterprise Linux, the Shadowman logo, the Red Hat logo, JBoss, OpenShift, Fedora, the Infinity logo, and RHCE are trademarks of Red Hat, Inc., registered in the United States and other countries.

Linux® is the registered trademark of Linus Torvalds in the United States and other countries.

Java® is a registered trademark of Oracle and/or its affiliates.

XFS® is a trademark of Silicon Graphics International Corp. or its subsidiaries in the United States and/or other countries.

MySQL® is a registered trademark of MySQL AB in the United States, the European Union and other countries.

Node.js® is an official trademark of Joyent. Red Hat is not formally related to or endorsed by the official Joyent Node.js open source or commercial project.

The OpenStack® Word Mark and OpenStack logo are either registered trademarks/service marks or trademarks/service marks of the OpenStack Foundation, in the United States and other countries and are used with the OpenStack Foundation's permission. We are not affiliated with, endorsed or sponsored by the OpenStack Foundation, or the OpenStack community.

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



## Adding users (docs.redhat.com)

Red Hat OpenShift API Management
1

Adding users in Red Hat OpenShift API Management.

Red Hat Customer Content Services

Legal Notice

Abstract

This guide provides information about how you can get started with adding users in Red Hat OpenShift API Management.

Making open source more inclusive

Red Hat is committed to replacing problematic language in our code, documentation, and web properties. We are beginning with these four terms: master, slave, blacklist, and whitelist. Because of the enormity of this endeavor, these changes will be implemented gradually over several upcoming releases. For more details, see our
CTO Chris Wright’s message
.

Chapter 1. Adding users in OpenShift API Management

1.1. Roles in OpenShift API Management

OpenShift API Management includes administrator and developer roles. These roles determine the actions a user can perform.

All OpenShift API Management users belong to the
rhoam-developers
group. Additionally, administrators are members of the
dedicated-admins
group and are granted the
dedicated-admin
role in OpenShift Dedicated. Administrators are managed using the
dedicated-admins
group in the OpenShift Dedicated cluster and have elevated privileges in OpenShift API Management.

Administrator role

An administrator has rights to view and modify resources in OpenShift API Management and can assign cluster roles to control who has various access levels and permissions in OpenShift API Management, Red Hat 3scale API Management, and Red Hat Single Sign-On. Administrators in OpenShift API Management manage users, and the API gateway, APIcast, which is the interface that handles calls to an API. The onboarding process creates an administrator with the highest level of access in OpenShift API Management and with
admin
privileges in 3scale.

As an administrator, you can perform the following tasks:

Red Hat Single Sign-On

Manage users and permissions in the master realm.

Create realms.

Administer user-created realms.

Red Hat 3scale API Management

Elevate permissions of developers to an administrator level.

Edit routes.

View pod logs in the 3scale namespace.

Create a product in the 3scale console.

Developer role

Developers have access to the services in OpenShift API Management. With the developer role, you can use the Red Hat Single Sign-On instance to secure your applications and you have basic member access in 3scale. Developers, which are referred to as API providers in 3scale, make APIs accessible by adding them to OpenShift API Management, configuring their use, and publishing them.

As a developer, you can perform the following tasks:

Red Hat Single Sign-On

Access the console.

Create realms.

Red Hat 3scale API Management

Access the 3scale Admin Portal.

Access the 3scale Developer Portal.

An OpenShift API Management administrator can grant 3scale
admin
privileges to developers.

Additional resources

Roles in OpenShift Dedicated

Managing OpenShift Dedicated administrators

Inviting users and managing rights in 3scale

1.2. Granting 3scale administrator privileges to developers

As an administrator in OpenShift API Management, you also have
admin
privileges in 3scale. However, the developer role in OpenShift API Management only has
member
privileges in 3scale and limited access to 3scale features.

An administrator must explicitly grant 3scale
admin
permissions to OpenShift API Management developers.

Prerequisites

You are an administrator in OpenShift API Management.

You have a developer you want to grant
admin
privileges to in 3scale.

Procedure

Log in to the Red Hat 3scale API Management console
Admin Portal
.

Click
Dashboard
.

In the
Dashboard
drop-down menu, click
Accounts Settings
.

In the menu, click
Users
>
Listing
. The
Users
page is displayed.

Choose a user.

Click
Edit
for the user whose permission you would like to modify. The
Edit User
page opens.

In the
ADMINISTRATIVE
section of the
Edit User
page, choose
Admin (full access)
to grant
admin
privileges to the selected user.

Click
Update User
.

Verification

Navigate to the
Users
page. In the menu, click
Users
>
Listing
.

On the
Users
page, find the user whose permissions you modified.

In the
Role
column, ensure
admin
is displayed in the row of the chosen user.

Additional resources

Giving new users rights in 3scale

1.3. Removing a user from OpenShift API Management from your cluster

To completely remove a user from Red Hat OpenShift API Management, you must remove them from the allowed group in your OpenShift identity provider (IDP)and then delete the user custom resource (CR).

Prerequisites

You have added an identity provider (IDP) to your cluster.

You have the IDP user name for the user whose privileges you are revoking.

You are logged in to the OpenShift Cluster Manager console using the OpenShift Cluster Manager account that you used to create the cluster or the administrator user.

Procedure

You can enter the following command to identify all users:

oc get users

Enter the following command to delete a specific user.

oc delete user <username>

Delete the desired user from the configured IDP.

Delete the user CR.

In OpenShift Cluster Manager, delete references to the user in the Access Control section. Ensure the user is removed from both the
Cluster Roles and Access
and the
Roles and Access
section.

Legal Notice

Copyright
© 2024 Red Hat, Inc.

The text of and illustrations in this document are licensed by Red Hat under a Creative Commons Attribution–Share Alike 3.0 Unported license ("CC-BY-SA"). An explanation of CC-BY-SA is available at
http://creativecommons.org/licenses/by-sa/3.0/
. In accordance with CC-BY-SA, if you distribute this document or an adaptation of it, you must provide the URL for the original version.

Red Hat, as the licensor of this document, waives the right to enforce, and agrees not to assert, Section 4d of CC-BY-SA to the fullest extent permitted by applicable law.

Red Hat, Red Hat Enterprise Linux, the Shadowman logo, the Red Hat logo, JBoss, OpenShift, Fedora, the Infinity logo, and RHCE are trademarks of Red Hat, Inc., registered in the United States and other countries.

Linux
® is the registered trademark of Linus Torvalds in the United States and other countries.

Java
® is a registered trademark of Oracle and/or its affiliates.

XFS
® is a trademark of Silicon Graphics International Corp. or its subsidiaries in the United States and/or other countries.

MySQL
® is a registered trademark of MySQL AB in the United States, the European Union and other countries.

Node.js
® is an official trademark of Joyent. Red Hat is not formally related to or endorsed by the official Joyent Node.js open source or commercial project.

The
OpenStack
® Word Mark and OpenStack logo are either registered trademarks/service marks or trademarks/service marks of the OpenStack Foundation, in the United States and other countries and are used with the OpenStack Foundation's permission. We are not affiliated with, endorsed or sponsored by the OpenStack Foundation, or the OpenStack community.

All other trademarks are the property of their respective owners.



## Adding and configuring APIs (docs.redhat.com)

Red Hat OpenShift API Management
1

Adding and configuring APIs in Red Hat OpenShift API Management.

Red Hat Customer Content Services

Legal Notice

Abstract

This guide provides information about how you can add and configure APIs in Red Hat OpenShift API Management.

Making open source more inclusive

Red Hat is committed to replacing problematic language in our code, documentation, and web properties. We are beginning with these four terms: master, slave, blacklist, and whitelist. Because of the enormity of this endeavor, these changes will be implemented gradually over several upcoming releases. For more details, see our
CTO Chris Wright’s message
.

Chapter 1. Adding and configuring APIs in Red Hat OpenShift API Management

As a 3scale API provider, you can add and configure your APIs.

Prerequisites

You have
accessed OpenShift API Management
.

1.1. Products and backends for 3scale APIs

Red Hat 3scale API Management separates your APIs into two main groups:

Backends
: Internal APIs bundled in a product. Backends grant API providers the freedom to map their internal API organization structure to 3scale.

Products
: Customer-facing APIs. Products facilitate the creation of strong and simplified offerings for API consumers.

A product can contain multiple backends, and a backend can be used in multiple products. In other words, to integrate and manage your API in 3scale you need to create both:

A backend containing at least the URL of your API. The backend can optionally be configured with mapping rules, methods and metrics to facilitate reusability.

A product for which you define the application plans, and configure APIcast.

1.2. Creating new products to test API calls

As a 3scale API provider, create products to test API calls through these public APIs. A product is a customer-facing API that packages one or more backends.

You can create a new product by following one of these options:

Define the product manually.

Import the product from OpenShift.

Here you will find details about the manual definition. If you want to import a product from OpenShift, see
Service Discovery
.

Prerequisites

You need a 3scale account.

Procedure

Go to the Dashboard. Under the APIs section, click
Create Product
in the
Products
card.

Provide the following details:

Name
: Product identifier.

System name
: Identifier used for internal purposes. A product
system_name
is used to generate proxy endpoints and domain names. By default,
system_name
is part of a label whose pattern can be as one of the alternatives below:

For APIcast staging:
%{system_name}-%{tenant_name}-apicast-staging

For APIcast production:
%{system_name}-%{tenant_name}-apicast-production

When an auto-generated URL label exceeds 63 characters, the system shortens the label as follows:
<truncated-label>-<unique-hash>

<truncated-label>
is the first 54 or 55 characters of the original URL.

<unique-hash>
is the first 7 characters of a unique SHA-1 hash calculated from the original label.

For example, this is the URL before truncation:

https://my-very-long-system-name-also-very-long-tenant-name-apicast-staging.3scale.net

This is the URL after the truncation:

https://my-very-long-system-name-also-very-long-tenant-name-api-72588d2.3scale.net

Description
: Optional field containing more details about the product.

Click
Create Product
.

After these steps, you have a product that represents the public facing API. The next steps are
creating backends
and
adding them to the product
.

1.3. Creating backends for your products

A backend is an internal API that is bundled to a product.

Prerequisites

You need a 3scale account.

Procedure

Go to the Dashboard.

Under the APIs section, click
Create Backend
in the
Backends
card.

Provide the following details:

Name
: Backend identifier.

System name
: Identifier used for internal purposes.

Description
: Optional field containing more details about the backend.

Private endpoint
: Base URL of the private API.

Click
Create Backend
.

After performing the steps for
Creating backends for your API
, you have an internal API. Add more backends as needed.

Next steps

Add this backend to a product
.

1.4. Adding backends to your products

By the end of this procedure you will have added a backend to a product. Repeat the procedure to add more backends as you require them.

Prerequisites

A product. To create one, see
Creating new products
.

One or more backends. To create one, see
Creating backends for your products
.

Procedure

Navigate to
[Your_product_name] > Integration > Backends
.

Click
Add Backend
.

From the drop down list, select an existing backend.

Specify the routing path in the
Path
textbox. For more details, see
The backend path of a specific product
.

Click
Add to Product
.

After these steps, your product will have a backend. You can follow this procedure again to add more backends to a product or to multiple products.

1.5. The backend path of a specific product

When you add a backend to a product, you define the path that the backend uses to communicate with the specified product. This path is not part of the backend.

From the procedure described in
Adding backends to your products
, APIcast is the API gateway that uses the path of the backend you indicated in step 4. APIcast redirects the traffic to the backend with the specified path matching the prefix of the requested endpoint path.

When defining the path for a backend:

You can indicate
/
as the path of one of the backends.

Paths must be unique inside the product. This means that you cannot add two backends with the same path inside the same product. Neither can you add the same backend twice to the same product.

You can give the same backend the same path in different products.

This is how the backend path works:

Each backend added to a product is mounted in the specified path.

Before redirecting the traffic, the path is removed from the public URL of the request to the product.

Example

Consider this configuration to add a backend to a product:

Backend: Inventory

Resource path:
/list

Product: Petstore

Backend path:
/supplies

These are the URLs used by your configuration:

Public URL:
<public-api-domain>/supplies/list

Private URL:
<private-api-domain>/list

This is the action flow when a request is sent:

The application sends a request.

The request is sent to the public URL:
<public-api-domain>/supplies/list

The backend path is removed before redirecting to the private URL:
<private-api-domain>/list

The request is redirected to the
Inventory
backend.

1.6. Defining mapping rules

A mapping rule associates a call to an endpoint with designated methods and metrics for tracking and limiting access to your API. You can define mapping rules at the backend and product levels. The advantage of defining mapping rules at the backend level is that you can add a backend to multiple products. To learn more about the metrics or methods for which to collect usage information depending on the requests to your API, both at the product and backend levels, see
How APIcast applies mapping rules for capturing usage of 3scale APIs
.

Prerequisites

A backend. To create it, see
Creating backends for your products
.

Procedure

Navigate to the
Backend
you want to define mapping rules for.

In the page containing information about the backend, navigate to
Mapping Rules
.

Click
Add Mapping Rule
.

Specify the following settings:

Verb
: The HTTP request verb (
GET
,
POST
,
DELETE
, or
PUT
).

Pattern
: The pattern to match. For example,
/hello
.

Metric or method to increment
: The metric or method name.

Increment by
: The metric increment number. For example,
1
.

Last?
: If this mapping rule should be considered as the last one, to stop processing other mapping rules.

Position
: Number that indicates the position of the execution of the mapping rule, to sort the mapping rules.

Click
Create Mapping Rule
.

Next steps

After these steps, the mapping rule is added to
Backends
under
[Your_API_backend] > Mapping Rules
. The mapping rule is also available for each product currently using the backend. To have the mapping rule active at the product level, promote the latest configuration under the
Products
tab in
[Your_product_name] > Integration > Configuration
.

Example

After you promote the configuration, 3scale activates the backend mapping rules at the product level. The mapping rules follow the backend path specified in the product. For example, suppose you have this configuration:

Pattern of the mapping rule at the backend:
/thousands

Backend is added to a product with path:
/unitprice

The mapping rule at the product level is:
/unitprice/thousands
.

1.7. Creating 3scale application plans for products

A 3scale application plan defines the rules such as limits, pricing, and features for using your API product. For more information, refer to
Application plans
and
Designating methods and adding metrics for capturing usage details
.

Prerequisites

A product. To create one, see
Creating new products
.

Procedure

Navigate to
[Your_product_name] > Applications > Application Plans
.

Click
Create Application Plan
.

On the
Create Application Plan
page, enter a name and a system name for your new plan. A system name must be unique in your 3scale installation.

Click
Create Application Plan
.

1.8. Creating applications for the default account to test API calls

As a 3scale user, create applications for the default
Developer
account. An application subscribes to an application plan. As a result of this subscription, 3scale provides the user key required to call an API product.

An application is always associated with an application plan. Applications are stored within developer accounts. In basic 3scale plans only a single application is allowed. In enterprise plans, multiple applications per account are allowed.

Prerequisites

An application plan. To create one, see
Creating 3scale application plans for products
.

Procedure

Navigate to
Audience > Accounts > Listing
.

Click the default
Developer
account.

Go to the
Application
tab.

Click
Create Application
.

Choose an application plan.

Service plan
contains the product plan configured by the Account Manager, which will be associated to your application.

Specify an application name.

In the
Description
field, enter information about this application.

Click
Create Application
.

You can see your new application in
Dashboard > Audience > Accounts > Applications > Listing
.

1.9. Sending requests to your product to test the integration of a backend

As a 3scale API provider, you can send a command line request to a product to test the integration of a backend based on the first mapping rule added to the product.

Before you can send a test request, you must promote an APIcast configuration that includes the backend that you want to test. A specific APIcast configuration consists of the backends you have added to a product with the corresponding mapping rules, applications, and application plans.

3scale directs requests to the backend of a product according to the path specified in the request call. For each backend of a product, you configured the
backend path
when you added the backend to the product. In other words, each backend has its own path.

Prerequisites

One or more
backends that you added to a product
.

A
mapping rule
for each backend in a product.

An
application plan
to define the access policies.

An
application
that subscribes to the application plan.

Procedure

Promote the new APIcast configuration to staging:

Navigate to
[Your_product_name] > Integration > Configuration
.

Under
APIcast Configuration
, click
Promote v.[n] to Staging APIcast
.

v.[n]
indicates the version number to be promoted.

If there are no changes to be promoted, there is a grayed button with the text
Nothing to promote
.

Under
Staging APIcast
, promote the APIcast configuration to production by clicking
Promote v.[n] to Production APIcast
.

v.[n]
indicates the version number to be promoted.

If there are no changes to be promoted, you will see a grayed button with the text
Nothing to promote
.

To test requests to your API product, copy the command provided in
Example curl for testing
and run it in a terminal.

The
curl
command example is based on the first mapping rule of the product.

After you run the command, you should get an HTML response containing results from the backend you are testing.

If you do not get a response, delete the catch-all mapping rule from your product, promote the new APIcast configuration to staging and then to production, and run the example
curl
command.

Next steps

You can confirm the different responses when changing metrics and methods, such as limits and pricing rules. For any of the application plans of a product, modify the methods and metrics when testing requests to your product. For more details, refer to
adding methods and metrics
.

Every time you modify the product configuration and before making calls to your API, you must promote the updated configuration to the staging and production environments. When there are pending changes to be promoted to the staging environment, there is an exclamation mark in the Admin Portal, next to the
Integration
menu item.

Legal Notice

Copyright
© 2024 Red Hat, Inc.

The text of and illustrations in this document are licensed by Red Hat under a Creative Commons Attribution–Share Alike 3.0 Unported license ("CC-BY-SA"). An explanation of CC-BY-SA is available at
http://creativecommons.org/licenses/by-sa/3.0/
. In accordance with CC-BY-SA, if you distribute this document or an adaptation of it, you must provide the URL for the original version.

Red Hat, as the licensor of this document, waives the right to enforce, and agrees not to assert, Section 4d of CC-BY-SA to the fullest extent permitted by applicable law.

Red Hat, Red Hat Enterprise Linux, the Shadowman logo, the Red Hat logo, JBoss, OpenShift, Fedora, the Infinity logo, and RHCE are trademarks of Red Hat, Inc., registered in the United States and other countries.

Linux
® is the registered trademark of Linus Torvalds in the United States and other countries.

Java
® is a registered trademark of Oracle and/or its affiliates.

XFS
® is a trademark of Silicon Graphics International Corp. or its subsidiaries in the United States and/or other countries.

MySQL
® is a registered trademark of MySQL AB in the United States, the European Union and other countries.

Node.js
® is an official trademark of Joyent. Red Hat is not formally related to or endorsed by the official Joyent Node.js open source or commercial project.

The
OpenStack
® Word Mark and OpenStack logo are either registered trademarks/service marks or trademarks/service marks of the OpenStack Foundation, in the United States and other countries and are used with the OpenStack Foundation's permission. We are not affiliated with, endorsed or sponsored by the OpenStack Foundation, or the OpenStack community.

All other trademarks are the property of their respective owners.



## Administering (docs.redhat.com)

Red Hat OpenShift API Management
1

Administering Red Hat OpenShift API Management.

Red Hat Customer Content Services

Legal Notice

Abstract

This guide provides information about administering Red Hat OpenShift API Management.

Making open source more inclusive

Red Hat is committed to replacing problematic language in our code, documentation, and web properties. We are beginning with these four terms: master, slave, blacklist, and whitelist. Because of the enormity of this endeavor, these changes will be implemented gradually over several upcoming releases. For more details, see our
CTO Chris Wright’s message
.

Chapter 1. Administering OpenShift API Management

You can perform configuration and monitoring tasks for managed services. With OpenShift API Management you can manage network configurations, define policies, monitor API usage, and update notification settings.

1.1. Overview of rate limiting, alerting, and monitoring in OpenShift API Management

Red Hat OpenShift API Management provides a limited number of API calls. The API request hard limits are set as part of your Red Hat OpenShift API Management purchase.

You are alerted to ensure you do not exceed the set API usage hard limit. OpenShift API Management uses a tiered approach for alerting customers when the API usage is close to the hard limit, to ensure request calls are not rejected and service is not interrupted.

An email notification is sent to the email address provided during the OpenShift API Management installation. The email notification includes a link to the Grafana dashboard, which provides a visualization of your API usage.

Note

You can also access the Grafana dashboard through the OpenShift Dedicated console. To access the dashboard, click the application launcher in OpenShift Dedicated and then click
API Management Dashboards
in the
OpenShift Managed Services
drop-down menu.

A notification is sent in the following instances:

If API usage is between 80% and 90% for a period of 4 hours, a notification is sent every 4 hours.

If API usage is between 90% and 95% for a period of 2 hours, a notification is sent every 2 hours.

If API usage is over 95% for a period of 30 minutes, a notification is sent every 30 minutes.

OpenShift API Management offers you the flexibility to share rate limiting quotas across multiple environments. You can divide a purchased OpenShift API Management subscription level across multiple OpenShift Dedicated environments. For example, if a 10 million API calls per day subscription level is selected, you can allocate 5 million API calls per day to both a stage environment and a production environment. For more information on increasing your Red Hat OpenShift API Management subscription level, refer to the
service definition
.

1.2. Monitoring service capacity with Grafana

You can monitor the service capacity and API usage of OpenShift API Management with Grafana. Access the Grafana dashboard from the OpenShift Dedicated console, to monitor the OpenShift API Management API rate limits for the following durations:

last minute

last 24 hours

In the dashboard, you can view, share, and inspect the data for the OpenShift API Management API requests.

Prerequisites

Red Hat OpenShift API Management was added to your OpenShift Dedicated cluster.

Procedure

Click the application launcher in OpenShift Dedicated.

Click
API Management Dashboards
in the
OpenShift Managed Services
drop-down menu.

You are prompted to log in. Click
Log in with OpenShift
.

Enter login credentials, if prompted.

Click
Allow selected permissions
to authorize monitoring access.

In the Grafana console, click the
Dashboards
dropdown menu from the main menu to access the available dashboards and folders.

Click
Manage
.

Click
redhat-rhoam-customer-monitoring-operator
to open the folder.

Click
Rate Limiting
to access the monitoring data.

Note

Alternatively, click
Home
to search for a dashboard by name.

Chapter 2. Network policies

A cluster hosts two types of projects:

Projects associated with managed services. These projects support inbound and outbound connections.

User projects. These projects support communication from managed services.

In OpenShift Dedicated, there are two approaches to enabling communications:

Using network policies

Using the
join-project
option of the
oc
command

In OpenShift API Management, you can use network policies to enable communication and allow 3scale to communicate directly with the service endpoint, instead of the external URL.

You cannot use the
join-projects
option of the
oc
command with managed services projects.

2.1. Enabling communication between managed services and customer applications

You can create
NetworkPolicy
objects to define granular rules describing the Ingress network traffic that is allowed for projects in your cluster. By default, when you create projects in a cluster, communication between the projects is disabled.

This procedure describes how to enable communication for a project so that managed services, such as 3scale, can access customer applications.

Prerequisites

You have installed the OpenShift command-line interface (CLI), commonly known as
oc
.

Procedure

Log in to the cluster using the
oc
login command.

Use the following command to change the project:

$ oc project <project_name>

where
<project_name>
is the name of a project that you want to accept communications from other projects.

Create a
NetworkPolicy
object:

Create a
allow-from-middleware-namespaces.yaml
file.

Define a policy in the file you just created, such as in the following example:

apiVersion: networking.k8s.io/v1
kind: NetworkPolicy
metadata:
  name: allow-from-middleware-namespaces
spec:
  podSelector:
  ingress:
    - from:
        - namespaceSelector:
            matchLabels:
              integreatly-middleware-service: 'true'

Run the following command to create the policy object:

$ oc create -f allow-from-middleware-namespaces.yaml -n <project>

networkpolicy "allow-from-middleware-namespaces" created

2.2. Enabling communication between managed services and projects

By default, when you create projects in a cluster, communication between the projects is disabled. Use this procedure to enable communication in a project.

Prerequisites

You have installed the OpenShift command-line interface (CLI), commonly known as
oc
.

Procedure

Log in to the cluster using the
oc
login command.

Use the following command to change the project:

$ oc project <project_name>

where
<project_name>
is the name of a project that you want to accept communications from other projects.

Create a NetworkPolicy object:

Create a
NetworkPolicy.yaml
file.

Define a policy in the file you just created, such as in the following example.

This policy enables incoming communication for all projects in the cluster:

kind: NetworkPolicy
apiVersion: networking.k8s.io/v1
metadata:
  name: allow-all
spec:
  podSelector:
  ingress:
  - {}

Note

This policy configuration enables this project to communicate with all projects in the cluster.

Run the following command to create the policy object:

$ oc create -f <policy-name>.yaml -n <project>

2.3. Enabling communication between customer applications

You can enable communication between user applications.

Prerequisites

You have installed the OpenShift command-line interface (CLI), commonly known as
oc
.

Procedure

Log in to the cluster using the
oc
login command.

Use the following command to change the project:

$ oc project <project_name>

<project_name>
is the name of a project that you want to accept communications from.

Create a NetworkPolicy object:

Create a
allow-from-myproject-namespace.yaml
file.

Define a policy in the file you just created, such as in the following example.

This policy enables incoming communication for a specific project (
myproject
):

apiVersion: networking.k8s.io/v1
kind: NetworkPolicy
metadata:
  name: allow-from-myproject-namespace
spec:
  podSelector:
  ingress:
    - from:
        - namespaceSelector:
             matchLabels:
               project: myproject

Run the following commands to create the policy object:

$ oc create -f allow-from-myproject-namespace.yaml -n <project>
networkpolicy "allow-from-myproject-namespace" created

2.4. Disabling communication from a managed service to a project

By default, projects are created with a template that allows communication from a managed service. For example, 3scale can communicate with all of your projects.

You can disable the communication from a managed service to a project.

Prerequisites

You have installed the OpenShift command-line interface (CLI), commonly known as
oc

You have a project you want to isolate from the managed services.

Procedure

Log in to the cluster using the
oc
login command.

Use the following command to change the project:

$ oc project <project_name>

where
<project_name>
is the name of a project that you want to isolate from the managed services.

Create a NetworkPolicy object:

Create a
deny-all.yaml
file.

Define a policy in the file you just created, such as in the following example:

kind: NetworkPolicy
apiVersion: networking.k8s.io/v1
metadata:
  name: deny-all
spec:
  podSelector: {}
  ingress:
    - from:
        - namespaceSelector:
            matchLabels:
              integreatly-middleware-service: 'true'

Run the following command to create the policy object:

$ oc create -f <policy-name>.yaml -n <project>

2.5. Additional resources

Networking in OpenShift Dedicated

Chapter 3. Defining methods and metrics

An application plan sets limits and pricing rules for consumer access to your API. To enable enforcement of limits and rules, designate methods in your API for which to collect individual usage data or add metrics. Add a mapping rule to each designated method and each custom metric. The mapping rule specifies details about the usage data that you want to capture.

For more information about methods and metrics, see
Designating methods and adding metrics for capturing usage details
.

3.1. Adding methods to products and backends

Adding a method to a product or backend means that you are designating a method in your API for which you want to capture individual usage details. An application plan provides the ability to set a limit for each method that you add to a product or backend. The procedure for adding a method or metric to a product is similar to adding a method or metric to a backend.

Procedure

Navigate to
[Your_product_name] > Integration > Methods & Metrics
or
[Your_backend_name] > Methods & Metrics
.

Click
New method
.

In the
Friendly name
field, enter a short description of the method. This name is displayed in different sections of the 3scale Admin Portal. The friendly name must be unique for the product.

Important

Be careful with changing the system name of the methods or deleting them. These changes can break your already deployed 3scale integration if there are mapping rules pointing to the previous system name of the method.

In the
System name
field, enter the name of the method in your API to use to report the usage through the 3scale Service Management API. The system name must conform to these rules:

Unique in the product or backend

Contain only alphanumeric characters, underscore
_
, hyphen
-
or forward slash
/

No spaces

Otherwise, you are free to decide what the system name looks like. It can be the same as the endpoint (
/status
), or, for example, it can include the method and the path (
GET_/status
).

Optional: In the
Description
field, enter a more detailed description of the method.

Click
Create Method
.

Verification steps

Added methods are available in your application plans.

Next steps

Edit limits and pricing rules for each method by going to
[Your_product_name] > Applications > Application Plans > [plan_you_want_to_edit]
.

3.2. Adding metrics to products and backends

Adding a metric specifies a usage unit that you want to capture for all calls to your API. An application plan provides the ability to set a limit for each metric that you add to a product or backend. The procedure for adding a method or metric to a product is similar to adding a method or metric to a backend.

Procedure

Navigate to
[Your_product_name] > Integration > Methods & Metrics
or
[Your_backend_name] > Methods & Metrics
.

Click
New metric
.

In the
Friendly name
field, enter a short description of the metric. This name is displayed in different sections of the 3scale Admin Portal. The friendly name must be unique for the product.

Important

Be careful with changing the system name of the metrics or deleting them. These changes can break your already deployed 3scale integration if there are mapping rules pointing to the previous system name of the metric.

In the
System name
field, enter the name of the metric in your API to use to report the usage through the 3scale Service Management API. The system name must conform to these rules:

Unique in the product or backend

Contain only alphanumeric characters, underscore
_
, hyphen
-
or forward slash
/

No spaces

Otherwise, you are free to decide what the system name looks like.

In the
Unit
field, enter the unit.

Use a singular noun, for example,
hit
. The singular will become plural in the analytics charts.

Optional: In the
Description
field, enter a more detailed description of the metric.

Click
Create Metric
.

Verification steps

Added metrics are available in your application plans.

Next steps

Edit limits and pricing rules for each metric by going to
[Your_product_name] > Applications > Application Plans > [plan_you_want_to_edit]
.

Map your metrics to one or more URL pattern by going to
[Your_product_name] > Integration > Mapping Rules
. See
Adding mapping rules to methods and metrics
.

3.3. Alternatives for importing methods and metrics

If your API has multiple endpoints, there are two ways to automatically designate methods and add metrics to 3scale products and backends:

Importing via Swagger spec
.

Importing via RAML spec
.

3.4. Adding mapping rules to methods and metrics

Mapping rules are operations that are mapped to previously created
methods
and
metrics
in your products and backends.

Note

Mapping rules are required in your previously created methods, however, they are optional for metrics.

Procedure

Navigate to
[Your_product_name] > Integration > Mapping Rules
.

Click
Add Mapping Rule
.

The
Verb
field is pre-populated with the HTTP method,
GET
, however you can select other options from the dropdown list.

In the
Pattern
field, add a valid URL that starts with an forward slash
/
. The URL can be from a wildcard you specified inside curly brackets
{}
.

In the
Metric or Method to increment
field, select from one of your previously created methods or metrics.

The
Increment by
field is pre-populated with
1
, however, change this to suit your own needs.

Click the
Create Mapping Rule
button.

Verification steps

To verify your mapping rules, navigate to
[Your_product_name] > Integration > Methods & Metrics
. Each method and metric should have a check mark in the
Mapped
column.

Legal Notice

Copyright
© 2024 Red Hat, Inc.

The text of and illustrations in this document are licensed by Red Hat under a Creative Commons Attribution–Share Alike 3.0 Unported license ("CC-BY-SA"). An explanation of CC-BY-SA is available at
http://creativecommons.org/licenses/by-sa/3.0/
. In accordance with CC-BY-SA, if you distribute this document or an adaptation of it, you must provide the URL for the original version.

Red Hat, as the licensor of this document, waives the right to enforce, and agrees not to assert, Section 4d of CC-BY-SA to the fullest extent permitted by applicable law.

Red Hat, Red Hat Enterprise Linux, the Shadowman logo, the Red Hat logo, JBoss, OpenShift, Fedora, the Infinity logo, and RHCE are trademarks of Red Hat, Inc., registered in the United States and other countries.

Linux
® is the registered trademark of Linus Torvalds in the United States and other countries.

Java
® is a registered trademark of Oracle and/or its affiliates.

XFS
® is a trademark of Silicon Graphics International Corp. or its subsidiaries in the United States and/or other countries.

MySQL
® is a registered trademark of MySQL AB in the United States, the European Union and other countries.

Node.js
® is an official trademark of Joyent. Red Hat is not formally related to or endorsed by the official Joyent Node.js open source or commercial project.

The
OpenStack
® Word Mark and OpenStack logo are either registered trademarks/service marks or trademarks/service marks of the OpenStack Foundation, in the United States and other countries and are used with the OpenStack Foundation's permission. We are not affiliated with, endorsed or sponsored by the OpenStack Foundation, or the OpenStack community.

All other trademarks are the property of their respective owners.
