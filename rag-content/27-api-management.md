# Red Hat OpenShift API Management 1

Source: Official Red Hat Documentation (PDF)


---

## Api Management Getting Started

Red Hat OpenShift API Management 1
Getting Started with Red Hat OpenShift API
Management
Getting started with your Red Hat OpenShift API Management installation.
Last Updated: 2025-05-16

Red Hat OpenShift API Management 1 Getting Started with Red Hat
OpenShift API Management
Getting started with your Red Hat OpenShift API Management installation.

Legal Notice
Copyright © 2025 Red Hat, Inc.
The text of and illustrations in this document are licensed by Red Hat under a Creative Commons
Attribution–Share Alike 3.0 Unported license ("CC-BY-SA"). An explanation of CC-BY-SA is
available at
http://creativecommons.org/licenses/by-sa/3.0/
. In accordance with CC-BY-SA, if you distribute this document or an adaptation of it, you must
provide the URL for the original version.
Red Hat, as the licensor of this document, waives the right to enforce, and agrees not to assert,
Section 4d of CC-BY-SA to the fullest extent permitted by applicable law.
Red Hat, Red Hat Enterprise Linux, the Shadowman logo, the Red Hat logo, JBoss, OpenShift,
Fedora, the Infinity logo, and RHCE are trademarks of Red Hat, Inc., registered in the United States
and other countries.
Linux ® is the registered trademark of Linus Torvalds in the United States and other countries.
Java ® is a registered trademark of Oracle and/or its affiliates.
XFS ® is a trademark of Silicon Graphics International Corp. or its subsidiaries in the United States
and/or other countries.
MySQL ® is a registered trademark of MySQL AB in the United States, the European Union and
other countries.
Node.js ® is an official trademark of Joyent. Red Hat is not formally related to or endorsed by the
official Joyent Node.js open source or commercial project.
The OpenStack ® Word Mark and OpenStack logo are either registered trademarks/service marks
or trademarks/service marks of the OpenStack Foundation, in the United States and other
countries and are used with the OpenStack Foundation's permission. We are not affiliated with,
endorsed or sponsored by the OpenStack Foundation, or the OpenStack community.
All other trademarks are the property of their respective owners.
Abstract
This guide provides information about how you can start working with Red Hat OpenShift API
Management.

. . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 
. . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 
Table of Contents
MAKING OPEN SOURCE MORE INCLUSIVE
## INTRODUCTION TO RED HAT OPENSHIFT API MANAGEMENT
### WHAT IS OPENSHIFT API MANAGEMENT
Understanding Red Hat 3scale API Management
Understanding Red Hat Single Sign-On
Red Hat OpenShift API Management considerations
### HOW TO SET UP OPENSHIFT API MANAGEMENT
Configure an identity provider
Add OpenShift API Management
Configure 3scale API provider account permissions
### HOW TO USE OPENSHIFT API MANAGEMENT
Get started with 3scale
Create and configure an API
Configure APIcast policies
Secure your API
Set up a 3scale Developer Portal
Set up monitoring and analytics for your API
Launch the API product
Monitor your API
### GET OPENSHIFT API MANAGEMENT
3
4
4
4
4
5
6
7
7
7
7
7
7
8
8
8
9
9
9
9
Table of Contents
1

Red Hat OpenShift API Management 1 Getting Started with Red Hat OpenShift API Management
2

MAKING OPEN SOURCE MORE INCLUSIVE
Red Hat is committed to replacing problematic language in our code, documentation, and web
properties. We are beginning with these four terms: master, slave, blacklist, and whitelist. Because of the
enormity of this endeavor, these changes will be implemented gradually over several upcoming releases.
For more details, see our CTO Chris Wright’s message.
MAKING OPEN SOURCE MORE INCLUSIVE
3
## INTRODUCTION TO RED HAT OPENSHIFT API
MANAGEMENT
Learn about the features and functions available in the Red Hat OpenShift API Management cloud
service.
### WHAT IS OPENSHIFT API MANAGEMENT
Red Hat OpenShift API Management is a cloud service for creating, securing, and publishing your APIs.
The OpenShift API Management service is an add-on for Red Hat OpenShift Dedicated and Red Hat
OpenShift Service on AWS. The service is based on the Red Hat 3scale API Management platform and
also includes an implementation of Red Hat Single Sign-On.
Understanding Red Hat 3scale API Management
Application Programming Interface (API) management refers to the processes of distributing,
controlling, and analyzing the APIs that connect applications and data across cloud environments.
Red Hat OpenShift API Management provides a management platform that allows users to share,
secure, distribute, control, and monetize APIs. After setting up authentication and user accounts,
OpenShift API Management developers, also referred to as API providers, can configure, and publish
their APIs.
The main OpenShift API Management components include:
APIcast - the 3scale API gateway
Admin Portal - the 3scale console that API providers work in
Developer Portal - the interface for API consumers
Red Hat Single Sign-On - for authenticating access to the Developer Portal as well as to APIs
API providers are developers who work in the 3scale Admin Portal, for which an administrator has given
them accounts. API providers also work in the OpenShift Dedicated cluster to deploy applications, such
as a backend for service API requests. API providers create and publish APIs, and can configure Red Hat
Single Sign-On authentication to secure APIs. 3scale separates APIs into two main groups:
Backends are internal APIs bundled in a product. Backends grant API providers the freedom to
map internal API organization structures to 3scale. A backend contains a private URL for an
internal API. It is exposed through mapping rules and the public URL of one or more 3scale
products.
Products are customer-facing APIs. Products facilitate the creation of robust yet simplified
offerings for API consumers. A product includes application plans and configuration of the
APIcast gateway. A product can bundle multiple backends.
When a 3scale product is ready for use, an API provider publishes it in the Developer Portal. API
consumers visit the Developer Portal to subscribe to a plan that enables them to use the 3scale product
that contains that API. Consumers can then call the API’s operations, subject to any usage policies that
may be in effect.
Understanding Red Hat Single Sign-On
Red Hat Single Sign-On provides single sign-on (SSO) authentication to secure web applications. You
use this SSO implementation to control access to 3scale Developer Portals and to 3scale API products.
It is not supported as a company-wide SSO solution.
Red Hat OpenShift API Management 1 Getting Started with Red Hat OpenShift API Management
4

Red Hat OpenShift API Management considerations
Red Hat OpenShift API Management introduces several product considerations that need to be
thoroughly understood before proceeding with the installation and configuration of the service:
Authentication Options: OpenShift API Management provides various authentication options
within the service to ensure secure access control and identity verification:
OAuth 2.0: An authorization framework that enables secure and delegated access to APIs.
OAuth 2.0 allows users and applications to obtain limited, scoped access tokens, which can
be used to authenticate and authorize API requests.
OpenID Connect: An identity layer built on top of OAuth 2.0 that provides additional
features for authentication, such as user profile information and identity federation. OpenID
Connect allows users to authenticate using their existing accounts from various identity
providers.
LDAP (Lightweight Directory Access Protocol): A protocol commonly used for accessing
and managing directory information. LDAP integration enables organizations to leverage
their existing user directories for authentication within OpenShift API Management.
Token-based authentication: A mechanism that involves exchanging a token for
authentication purposes. Tokens are typically issued by an identity provider or
authentication service and can be used to validate and authorize API requests.
CIDR (Classless Inter-Domain Routing): CIDR is a method used to allocate and manage IP
addresses more efficiently. It replaces the older system of class-based IP addressing and
enables flexible allocation of IP address blocks. CIDR notation is used to define network ranges
and subnets. Understanding CIDR is important for correctly configuring networking
components, such as IP whitelisting, firewall rules, and defining network policies for secure
communication. The CIDR range must not overlap with any network you would like to peer within
the OpenShift cluster VPC. If you do not specify a CIDR value, you can click the link in the
OpenShift Cluster Manager to apply the default CIDR range. After submitting the initial
configuration, you cannot modify the CIDR range. If you want to change the CIDR range, you
must delete and reinstall Red Hat OpenShift API Management. The CIDR prefix length range
must be between /16 and /26. Only CIDR values within this range are permitted. You can use 
10.1.0.0/26 as the default CIDR range.
Custom Wildcard Domain: A wildcard domain name allows you to handle dynamic routing of
API traffic across various endpoints and services within your infrastructure. By using a wildcard
DNS record (for example *.example.com), you can ensure that any subdomain under the
specified domain is automatically routed to the corresponding API service or endpoint. This
flexibility is particularly useful when dealing with multiple APIs or microservices, as it simplifies
the management of API endpoints and enables dynamic scaling and routing. To configure a
custom wildcard domain name with 3scale and Red Hat OpenShift API Management, you would
typically do the following:
Obtain a registered domain: You need to have a registered domain name that you own and
have administrative control over.
Configure DNS settings: Update your DNS settings for the domain to include a wildcard
DNS record pointing to the appropriate IP address or load balancer associated with your
API infrastructure.
Obtain an SSL/TLS certificate: Obtain an SSL/TLS certificate for your custom wildcard
domain name to ensure secure communication between clients and your API services. This
certificate can be either self-signed or issued by a trusted certificate authority (CA).
## INTRODUCTION TO RED HAT OPENSHIFT API MANAGEMENT
5

Configure 3scale and OpenShift API Management: In the configuration settings of both
3scale and OpenShift API Management, specify the custom wildcard domain name as the
endpoint for your APIs. This ensures that API requests made to any subdomain under the
wildcard domain are correctly routed and processed by the respective API services.
SMTP (Simple Mail Transfer Protocol): SMTP is a widely used standard protocol for email
transmission. In the context of OpenShift API Management, SMTP configuration allows you to
specify the email server settings required for email notifications, alerts, and communication
within the system. By providing the necessary SMTP details, such as the server address, port
number, authentication credentials, and encryption settings, you enable the platform to send
emails seamlessly. To successully apply an SMTP configuration, you must enter values for all
related fields. Values for all Custom SMTP fields are required, if you specify values for any of the
fields. Entering an SMTP configuration is optional. Red Hat OpenShift API Management default
values are applied if you leave the SMTP configuration fields blank. You can enter values for the
following fields:
Custom SMTP Mail Server Address - The remote mail server as a relay
Custom SMTP From Address - Email address of the outgoing mail
Custom SMTP Username - The mail server username
Custom SMTP Password - The mail server password
Custom SMTP Port - The port on which the mail server is listening for new connections
VPC Configurations: A VPC (Virtual Private Cloud) is a virtual network infrastructure that
allows you to provision and manage network resources within a logically isolated environment.
OpenShift API Management supports the option to bring your own VPC, which means you can
use your existing VPC setup instead of relying on the default networking configuration. The
following Availability Zone (AZ) scenarios represent the tested configurations. Configurations
that differ from the following, may not work as expected and are not supported.
Single-AZ installation: The tested architecture includes a VPC with an internet gateway, an
availability zone containing a public subnet, and a private subnet.
Multi-AZ installation: The tested architecture includes a VPC with an internet gateway, up
to three availability zones (with each containing one public subnet), and a private subnet.
PrivateLink Multi-AZ installation: The tested architecture includes connections to clusters
using AWS PrivateLink endpoints instead of public endpoints for OpenShift Service on
AWS (ROSA) or OpenShift Dedicated (OSD).
Additional resources
Supported bring-your-own VPC architectures
### HOW TO SET UP OPENSHIFT API MANAGEMENT
A Red Hat OpenShift Dedicated cluster administrator sets up the cluster and identity provider and adds
the OpenShift API Management service to a cluster. Then, you configure the service users.
If desired, you can customize APIcast, which is the interface that handles calls to a 3scale API product.
Red Hat OpenShift API Management 1 Getting Started with Red Hat OpenShift API Management
6

NOTE
In Red Hat OpenShift API Management documentation, ignore content for 3scale
Hosted (SaaS). It does not apply to OpenShift API Management.
Configure an identity provider
If an identity provider is already configured, there is no need to configure another one. Otherwise, you
must choose and configure an identity provider, which can be LDAP, GitHub, GitHub Enterprise, Google,
or OpenID Connect.
Instructions:
Configuring identity providers
Add OpenShift API Management
Adding OpenShift API Management to a cluster makes the service available for use by 3scale API
providers. You can add OpenShift API Management to an OpenShift Dedicated cluster, or to a ROSA
cluster.
Instructions:
Adding OpenShift API Management to your cluster
Adding OpenShift API Management to your Red Hat OpenShift Service on AWS cluster
Configure 3scale API provider account permissions
In the 3scale Admin Portal, configure account permissions so that API providers in your organization can
create, configure, and launch 3scale API products.
When a new user logs in to the OpenShift Dedicated cluster by using the configured identity provider,
the user automatically receives an OpenShift account with permission to access OpenShift API
Management.
You manage these accounts in the 3scale Admin Portal.
By default, Single Sign-On is configured for 3scale in OpenShift API Management.
Instructions:
Red Hat Single Sign-On for the 3scale Admin Portal
### HOW TO USE OPENSHIFT API MANAGEMENT
Use OpenShift API Management to create, secure, and publish your APIs.
Get started with 3scale
You can use the 3scale wizard to start learning about how to add and test a 3scale API product.
Instructions:
First steps with 3scale
Create and configure an API
In the 3scale Admin Portal, create and configure an API to ensure that access is protected by API keys,
tracked, and monitored by 3scale with basic rate limits and controls in place.
## INTRODUCTION TO RED HAT OPENSHIFT API MANAGEMENT
7

This involves the following steps:
Create API backends
Create API products
Create mapping rules and application plans to define a customer-facing API product
Capture metrics
Configure API access rules
Mapping rules define the metrics or methods to report. Application plans define the rules such as limits,
pricing, and features for using an API product. An application subscribes to an application plan.
Instructions:
Adding and configuring APIs
Configure APIcast policies
APIcast is the 3scale API gateway, which is the endpoint that accepts API product calls and routes them
to bundled backends. OpenShift API Management provides APIcast staging for developing and testing
APIs and also APIcast production, for published APIs.
APIcast policies are units of functionality that modify how APIcast operates. Policies can be enabled,
disabled, and configured to control APIcast behavior. Use custom policies to add functionality that is not
available in a default APIcast deployment.
Instructions:
APIcast policies
Secure your API
If you want to secure your API by using OpenID and OAuth, then in the Red Hat Single Sign-On Admin
Console, create a Red Hat Single Sign-On realm. An SSO realm is required to manage authentication for
access to the Developer Portal and 3scale API products.
In the 3scale Admin Portal, set up authentication to control access to your API product and to the 3scale
Developer Portal.
Instructions:
Enabling and disabling authentication through Red Hat Single Sign-On
Set up a 3scale Developer Portal
A well-structured developer portal and great documentation are key elements to assure adoption. A
developer portal is the main hub for managing interactions with API consumers and for API consumers
to access their API keys in a secure way.
In the 3scale Admin Portal, add OpenAPI Specification 3.0 conforming documents for use in a
Developer Portal. API consumers use the Developer Portal to access the APIs defined in these
documents.
Then, configure the Developer Portal and add your APIs.
Instructions:
Red Hat OpenShift API Management 1 Getting Started with Red Hat OpenShift API Management
8

Providing APIs in the Developer Portal
Discover and import APIs available in your OpenShift Dedicated cluster
Summary: from zero to hero Developer Portal
Set up monitoring and analytics for your API
You can designate methods in your API and add metrics to set access limits for any of an API product’s
application plans. For an API backend, methods and metrics can be used to set access limits in the
application plan of any API product that bundles the backend.
Instructions:
Designating methods and adding metrics for capturing usage details
API analytics
Launch the API product
After you have configured and secured your API and created a Developer Portal, you can launch your
API so that consumers can begin to use it.
Instructions:
Going live
Monitor your API
After your API is launched, you can monitor metrics that indicate how it is being used. Knowing how a
3scale API product is used is a crucial step for managing traffic, provisioning for peaks, and identifying
the users who most often send requests to the API product.
Instructions:
Viewing 3scale built-in traffic analytics for applications
### GET OPENSHIFT API MANAGEMENT
To get OpenShift API Management, you can add it to your OpenShift Dedicated cluster or ROSA
cluster. To learn more, go to https://cloud.redhat.com/application-services/overview.
## INTRODUCTION TO RED HAT OPENSHIFT API MANAGEMENT
9

---

## Api Management Adding Accessing

Red Hat OpenShift API Management 1
Adding and accessing Red Hat OpenShift API
Management
Adding and accessing Red Hat OpenShift API Management.
Last Updated: 2024-10-08

Red Hat OpenShift API Management 1 Adding and accessing Red Hat
OpenShift API Management
Adding and accessing Red Hat OpenShift API Management.

Legal Notice
Copyright © 2024 Red Hat, Inc.
The text of and illustrations in this document are licensed by Red Hat under a Creative Commons
Attribution–Share Alike 3.0 Unported license ("CC-BY-SA"). An explanation of CC-BY-SA is
available at
http://creativecommons.org/licenses/by-sa/3.0/
. In accordance with CC-BY-SA, if you distribute this document or an adaptation of it, you must
provide the URL for the original version.
Red Hat, as the licensor of this document, waives the right to enforce, and agrees not to assert,
Section 4d of CC-BY-SA to the fullest extent permitted by applicable law.
Red Hat, Red Hat Enterprise Linux, the Shadowman logo, the Red Hat logo, JBoss, OpenShift,
Fedora, the Infinity logo, and RHCE are trademarks of Red Hat, Inc., registered in the United States
and other countries.
Linux ® is the registered trademark of Linus Torvalds in the United States and other countries.
Java ® is a registered trademark of Oracle and/or its affiliates.
XFS ® is a trademark of Silicon Graphics International Corp. or its subsidiaries in the United States
and/or other countries.
MySQL ® is a registered trademark of MySQL AB in the United States, the European Union and
other countries.
Node.js ® is an official trademark of Joyent. Red Hat is not formally related to or endorsed by the
official Joyent Node.js open source or commercial project.
The OpenStack ® Word Mark and OpenStack logo are either registered trademarks/service marks
or trademarks/service marks of the OpenStack Foundation, in the United States and other
countries and are used with the OpenStack Foundation's permission. We are not affiliated with,
endorsed or sponsored by the OpenStack Foundation, or the OpenStack community.
All other trademarks are the property of their respective owners.
Abstract
This guide provides information about how you can get started with adding and accessing Red Hat
OpenShift API Management.

. . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 
. . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 
Table of Contents
MAKING OPEN SOURCE MORE INCLUSIVE
## ADDING AND ACCESSING OPENSHIFT API MANAGEMENT
### INTRODUCTION TO RED HAT OPENSHIFT API MANAGEMENT
### ADDING OPENSHIFT API MANAGEMENT TO YOUR CLUSTER
### ADDING OPENSHIFT API MANAGEMENT TO YOUR RED HAT OPENSHIFT SERVICE ON AWS CLUSTER
### ADDING OPENSHIFT API MANAGEMENT TO YOUR STS ENABLED RED HAT OPENSHIFT SERVICE ON
AWS CLUSTER
1.4.1. Additional
### ACCESSING OPENSHIFT API MANAGEMENT IN YOUR CLUSTER
### SUPPORTED BRING-YOUR-OWN VPC ARCHITECTURES
### DELETING OPENSHIFT API MANAGEMENT FROM YOUR CLUSTER
### ADDITIONAL RESOURCES
3
4
4
5
8
9
10
10
11
12
13
Table of Contents
1

Red Hat OpenShift API Management 1 Adding and accessing Red Hat OpenShift API Management
2

MAKING OPEN SOURCE MORE INCLUSIVE
Red Hat is committed to replacing problematic language in our code, documentation, and web
properties. We are beginning with these four terms: master, slave, blacklist, and whitelist. Because of the
enormity of this endeavor, these changes will be implemented gradually over several upcoming releases.
For more details, see our CTO Chris Wright’s message.
MAKING OPEN SOURCE MORE INCLUSIVE
3
## ADDING AND ACCESSING OPENSHIFT API
MANAGEMENT
Red Hat OpenShift API Management is a managed API traffic control and program management
service based on the Red Hat 3scale API Management platform. It includes an implementation of Red
Hat Single Sign-On. You can add OpenShift API Management to an existing OpenShift Dedicated or
ROSA cluster in the OpenShift Cluster Manager.
### INTRODUCTION TO RED HAT OPENSHIFT API MANAGEMENT
Application Programming Interface (API) management refers to the processes of distributing,
controlling, and analyzing the APIs that connect applications and data across cloud environments. You
can streamline API management in a unified service by using Red Hat OpenShift API Management.
Red Hat OpenShift API Management is a managed service that is based on the Red Hat 3scale API
Management platform and is available as an add-on in Red Hat OpenShift Dedicated. With OpenShift
API Management you can secure, manage, and monitor APIs at every stage of the development
lifecycle. Use OpenShift API Management to manage users, oversee the API gateway, and configure
APIs for increased accessibility.
You can deploy Red Hat OpenShift API Management on Amazon Web Services (AWS) versions of
OpenShift Dedicated and the Red Hat OpenShift Service on AWS. OpenShift API Management
includes capabilities of the following Red Hat products:
Red Hat Single Sign-On  provides single sign-on (SSO) authentication to secure web
applications.
Red Hat 3scale API Management is a management platform that allows users to share, secure,
distribute, control, and monetize APIs. After setting up authentication and user accounts,
OpenShift API Management developers, also referred to as API providers, can configure, and
publish 3scale API products.
The following actions are important to understand before you use Red Hat OpenShift API Management:
Configuring your identity provider to provision OpenShift Dedicated and secure APIs.
NOTE
The email address and the User Principal Name (UPN) must be paired for Red
Hat Single Sign-On configured with OpenID to work between OpenShift and Red
Hat 3scale API Management. The UPN and email must match or the OpenID
UPN and email fields must map to the username field and email field of the
customer in the OpenShift IDP configuration.
Accessing the Red Hat OpenShift API Management service definition , to understand the
features, considerations, limits, and alerts of OpenShift API Management.
Understanding the roles in Red Hat OpenShift API Management to recognize user permissions.
Accessing the workflows for Red Hat OpenShift API Management to understand how to create,
secure, and publish APIs.
Setting the Classless Inter-Domain Routing (CIDR) block to ensure there is no overlap with any
network that the Red Hat OpenShift API Management customer would like to peer with in the
Red Hat OpenShift API Management 1 Adding and accessing Red Hat OpenShift API Management
4

OpenShift cluster virtual private cloud (VPC). You can click the link in the OpenShift Cluster
Manager to apply the default CIDR block, or you can provide a custom CIDR block. After
submitting the initial configuration, you cannot modify the CIDR block. If you want to change the
CIDR block, you must delete and reinstall Red Hat OpenShift API Management.
Optional: Creating a service control policy  for Customer Cloud Subscription (CCS) users who
must create an AWS OpenShift Dedicated cluster.
Additional resources
Red Hat OpenShift API Management service definition
Red Hat OpenShift API Management Cloud Resources on OpenShift Dedicated 4
What is API management?
### ADDING OPENSHIFT API MANAGEMENT TO YOUR CLUSTER
Red Hat OpenShift API Management can be added to a Red Hat OpenShift Dedicated cluster or a Red
Hat OpenShift Service on AWS cluster. You can use the OpenShift Cluster Manager to add OpenShift
API Management to an existing Red Hat OpenShift Dedicated cluster, to make the service available for
use by API providers.
Prerequisites
You have provisioned an OpenShift Dedicated cluster or an ROSA cluster, that meets the
product requirements for adding the OpenShift API Management service, as outlined in the Red
Hat OpenShift API Management service definition.
NOTE
You cannot use the Red Hat OpenShift Cluster Manager to add the Red Hat
OpenShift API Management to a Red Hat OpenShift Service on AWS cluster.
You must use the CLI to add Red Hat OpenShift API Management to a Red Hat
OpenShift Service on AWS cluster.
You have configured your identity provider (IDP).
NOTE
Email addresses must match for Red Hat Single Sign-On configured with OpenID
to work between OpenShift and Red Hat 3scale API Management. The User
Principal Name (UPN) and email must match or the OpenID UPN and email fields
must map to the username field and email field of the customer in the OpenShift
IDP configuration.
Procedure
1. Enter the following URL in a browser:
https://console.redhat.com
2. Log in to your Red Hat account.
## ADDING AND ACCESSING OPENSHIFT API MANAGEMENT
5

3. Click All apps and services and then click API Management from the drop-down menu. The
OpenShift Cluster Manager console is displayed.
4. In OpenShift Cluster Manager, click Clusters in the menu. A list of clusters in the console is
displayed.
5. Select a cluster from the list of clusters.
6. Click the Add-ons tab.
7. Select the Red Hat OpenShift API Management option.
8. Click Install on the Red Hat OpenShift API Management option.
9. Enter the CIDR value in the CIDR Range field.
NOTE
The CIDR prefix length range must be between /16 and /26. Only CIDR values
within this range are permitted. You can use 10.1.0.0/26 as the default CIDR
range.
NOTE
The CIDR range must not overlap with any network you would like to peer within
the OpenShift cluster VPC. If you do not specify a CIDR value, click the link in the
OpenShift Cluster Manager to apply the default CIDR range. After submitting the
initial configuration, you cannot modify the CIDR range. If you want to change the
CIDR range, you must delete and reinstall Red Hat OpenShift API Management.
10. In the Notification Email field, enter the email address you would like to receive OpenShift API
Management service notifications.
11. In the Quota field, select a Daily Rate Limit Quota  from the drop-down menu.
NOTE
You cannot select a daily rate limit quota that exceeds the SKU specified and
assigned during the purchase of the Red Hat OpenShift API Management
service. For example, if you purchased a 10 Million SKU, you cannot apply a daily
rate limit quota greater than 10 Million.
NOTE
The Evaluation option has a daily rate limit of 100,000 API calls and does not
include production-level support. You can select the Evaluation option from the
Quota drop-down menu. For more information on accessing all features of
OpenShift API Management, refer to the Red Hat OpenShift API Management
service definition.
12. Optional: In the 3scale custom wildcard domain name field, enter the designated domain
name.
Red Hat OpenShift API Management 1 Adding and accessing Red Hat OpenShift API Management
6

NOTE
Adding the custom wildcard domain name is optional. If you want to add it to your
add-on service, you must configure the custom wildcard domain before installing
Red Hat OpenShift API Management to the cluster using the OpenShift Cluster
Manager. You cannot add or modify the domain name after installing the add-on
service using the OpenShift Cluster Manager. You can only change the 3scale
routes in the 3scale Admin Portal after the initial configuration and installation.
NOTE
Red Hat OpenShift API Management will fail to install if the wildcard domain
name add-on value is not the same as the domain name in the custom domain
custom resource. You cannot use a self-signed wildcard certificate.
13. Optional: Enter values for an SMTP configuration. Red Hat OpenShift API Management
provides a default SMTP server and applies default values if you do not specify an SMTP
configuration. You can enter values for the following fields:
Custom SMTP Mail Server Address - The remote mail server as a relay
Custom SMTP From Address - Email address of the outgoing mail
Custom SMTP Username - The mail server username
Custom SMTP Password - The mail server password
Custom SMTP Port - The port on which the mail server is listening for new connections
NOTE
To successully apply an SMTP configuration, you must enter values for all
related fields. Values for all Custom SMTP fields are required, if you specify
values for any of the fields.
NOTE
Entering an SMTP configuration is optional. Red Hat OpenShift API
Management default values are applied if you leave the SMTP configuration
fields blank.
NOTE
You can edit the SMTP configuration after installing the OpenShift API
Management add-on.
14. Click Install.
Verification
1. Ensure Installed and a check mark are displayed on the Red Hat OpenShift API Management
option.
## ADDING AND ACCESSING OPENSHIFT API MANAGEMENT
7

NOTE
The Installed status is displayed before Red Hat OpenShift API Management is
completely installed. To verify installation is complete, check the operator status
in the console.
2. Click Open in Console to see the Red Hat OpenShift API Management operator details.
Additional resources
Adding services to a Red Hat OpenShift Service on AWS cluster
Configuring custom domains for OpenShift Dedicated
Configuring custom domains for ROSA
Creating a ROSA cluster
Deleting a service from your OpenShift Dedicated cluster
Understanding CIDR blocks in AWS VPC
### ADDING OPENSHIFT API MANAGEMENT TO YOUR RED HAT
OPENSHIFT SERVICE ON AWS CLUSTER
Red Hat OpenShift Service on AWS (ROSA) allows Red Hat to deploy OpenShift clusters into an
existing Amazon Web Service (AWS) account.
You can add OpenShift API Management to your ROSA cluster to make the service available for use by
API providers.
You can install the OpenShift API Management add-on to an ROSA cluster using the rosa CLI.
Prerequisites
You have configured the rosa CLI.
You have provisioned an ROSA cluster that meets the requirements for adding the OpenShift
API Management service.
You have configured the identity provider (IDP).
You are logged in to your Red Hat OpenShift Service on AWS cluster .
Procedure
1. Enter the following command in the rosa CLI to add the Red Hat OpenShift API Management
to the Red Hat OpenShift Service on AWS cluster :
rosa install addon --cluster=<cluster_name> managed-api-service
Replace <cluster_name> with the name of your cluster in ROSA.
Interactive mode opens. In interactive mode you are prompted to enter a CIDR range and the
email addresses that should receive Red Hat OpenShift API Management service notifications.
Red Hat OpenShift API Management 1 Adding and accessing Red Hat OpenShift API Management
8

2. Enter the CIDR range.
NOTE
The CIDR prefix length range must be between /16 and /26. Only CIDR values
within this range are permitted. You can use 10.1.0.0/26 as the default CIDR
range.
NOTE
The CIDR range must not overlap with any network you would like to peer with in
the OpenShift cluster VPC. After submitting the initial configuration, you cannot
modify the CIDR range. If you want to change the CIDR range, you must delete
and reinstall Red Hat OpenShift API Management.
3. Enter the email address you would like to receive OpenShift API Management service
notifications.
NOTE
You can enter multiple email addresses to receive service notifications. Use a
comma to separate multiple email addresses.
Additional resources
About Red Hat OpenShift Service on AWS
Red Hat OpenShift Service on AWS quickstart guide
### ADDING OPENSHIFT API MANAGEMENT TO YOUR STS ENABLED
RED HAT OPENSHIFT SERVICE ON AWS CLUSTER
Red Hat OpenShift Service on AWS (ROSA) allows Red Hat to deploy OpenShift clusters into an
existing Amazon Web Service (AWS) account. Red Hat OpenShift Service on AWS supports AWS
Security Token Service (STS) for authentication with AWS APIs.
AWS STS is a global web service that provides short-term credentials for IAM or federated users. Red
Hat OpenShift Service on AWS with STS is the recommended credential mode for ROSA clusters. You
can use AWS STS with ROSA to allocate temporary, limited-privilege credentials for component-
specific IAM roles. The service enables cluster components to make AWS API calls using secure cloud
resource management practices.
You can add OpenShift API Management to your ROSA cluster to make the service available to API
providers that use AWS STS for authentication. Create an AWS IAM role and policies that are required
for installing OpenShift API Management onto a cluster that uses AWS STS.
Prerequisites
You have installed the AWS CLI.
You have installed the OpenShift CLI.
You have configured the rosa CLI.
## ADDING AND ACCESSING OPENSHIFT API MANAGEMENT
9

Procedure
1. In the AWS CLI, create a policy for SRE Support. Enter the following:
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
2. In the AWS CLI, enter the following command to attach the Red Hat OpenShift API
Management SRE policy to the OpenShift Support Role:
aws iam put-role-policy --role-name ManagedOpenShift-Support-Role --policy-name rhoam-
sre-support-policy --policy-document "file://rhoam-sre-support-policy.json"
3. In the rosa CLI, enter the following command to add the Red Hat OpenShift API Management
to the Red Hat OpenShift Service on AWS cluster:
rosa install addon --cluster <cluster-name> managed-api-service -y --addon-resource-
required true --rosa-cli-required true --billing-model standard
Replace <cluster_name> with the name of your cluster in ROSA.
1.4.1. Additional
ROSA with STS.
### ACCESSING OPENSHIFT API MANAGEMENT IN YOUR CLUSTER
Red Hat OpenShift API Management 1 Adding and accessing Red Hat OpenShift API Management
10

In OpenShift API Management you can create, secure, and publish APIs. You can access OpenShift API
Management from the OpenShift application launcher in the OpenShift Cluster Manager.
Prerequisites
Red Hat OpenShift API Management was added to your OpenShift Dedicated or ROSA cluster.
Procedure
1. Enter the following URL in a browser:
https://console.redhat.com
2. Log in to your Red Hat account.
3. Click All apps and services and then click API Management from the drop-down menu. The
OpenShift Cluster Manager console is displayed.
4. In the OpenShift Cluster Manager, click Clusters in the menu.
5. Select the cluster with OpenShift API Management installed, from the list of clusters.
6. Click the Add-ons tab.
7. Select the Red Hat OpenShift API Management option.
8. On the Red Hat OpenShift API Management option, click View in console. The Red Hat
OpenShift Dedicated console opens.
9. Click the application launcher in the OpenShift Dedicated console.
10. Select OpenShift API Management from the OpenShift Managed Services drop-down menu.
A new browser tab opens.
11. Use Red Hat Single Sign-On to authenticate your login.
### SUPPORTED BRING-YOUR-OWN VPC ARCHITECTURES
Red Hat OpenShift API Management supports the use of a bring-your-own virtual private cloud
(BYOVPC) configuration. The following Availability Zone (AZ) scenarios represent the tested
configurations. Configurations that differ from the following, may not work as expected and are not
supported.
Single-AZ installation: The tested architecture includes a VPC with an internet gateway, an
availability zone containing a public subnet, and a private subnet.
Multi-AZ installation: The tested architecture includes a VPC with an internet gateway, up to
three availability zones (with each containing one public subnet), and a private subnet.
PrivateLink Multi-AZ installation: The tested architecture includes connections to clusters
using AWS PrivateLink endpoints instead of public endpoints for ROSA (ROSA) or OpenShift
Dedicated (OSD).
Outbound firewall rules are required for Red Hat OpenShift API Management} installations
on clusters that have PrivateLink enabled.
## ADDING AND ACCESSING OPENSHIFT API MANAGEMENT
11

The following domains are required and must be allowed through the firewall in order to
successfully configure the Red Hat OpenShift API Management with PrivateLink:
echo-api.3scale.net
gcr.io
grafana.com
stats.grafana.org
.sendgrid.net
raw.githubusercontent.com
objects.githubusercontent.com
The following domains shoud not be blocked by the firewall:
cloud.redhat.com
aws.amazon.com
www.redhat.com
www.okd.io
access.redhat.com
observatorium.api.openshift.com
Bring-your-own-VPC (BYOC) configurations for Red Hat OpenShift API Management
### DELETING OPENSHIFT API MANAGEMENT FROM YOUR CLUSTER
IMPORTANT
When you delete Red Hat OpenShift API Management from your cluster, backups of the
databases are automatically created in your Amazon Web Services (AWS) account:
RDS for PostgreSQL: Backups are under 'Snapshots'.
ElastiCache for Redis: Backups are under 'Backups'.
Backups persist after Red Hat OpenShift API Management deletion and require manual
removal. To change this default behavior, add the annotation skip_final_db_snapshots: 
'true' to the Red Hat Managed Integration (RHMI) custom resource (CR) before
deletion:
Failure to delete backups may result in continued storage costs.
You can delete the Red Hat OpenShift API Management add-on service from your OpenShift
Dedicated cluster through the OpenShift Cluster Manager(OCM) or the OCM CLI.
$ oc patch rhmi rhoam -n redhat-rhoam-operator --type=merge --patch '{"metadata":
{"annotations": {"skip_final_db_snapshots": "true"}}}'
Red Hat OpenShift API Management 1 Adding and accessing Red Hat OpenShift API Management
12

To delete the add-on service using the OpenShift Cluster Manager CLI, enter the following command:
ocm delete api/clusters_mgmt/v1/clusters/<cluster_id>/addons/<addon_id>
Replace the <cluster_id> with the identification tag for your OpenShift Dedicated cluster.
Replace the <addon_id> with the identification tag for the Red Hat OpenShift API
Management add-on service.
NOTE
The following procedure describes how to delete a Red Hat OpenShift API Management
add-on service through OCM.
Prerequisites
Red Hat OpenShift API Management was added to your OpenShift Dedicated cluster.
Procedure
1. Navigate to the Clusters page in the OpenShift Cluster Manager.
2. Select the cluster with the installed instance of Red Hat OpenShift API Management you want
to delete.
3. Click the Add-ons tab.
4. Locate the installed OpenShift API Management service you want to delete.
5. From the installed Red Hat OpenShift API Management option, click the main menu.
6. Click Uninstall add-on from the drop-down menu.
7. Enter the Red Hat OpenShift API Management name in the confirmation message that is
displayed.
8. Click Uninstall. You return to the Add-ons tab and an uninstalling state icon is displayed on the
Red Hat OpenShift API Management service option you deleted.
### ADDITIONAL RESOURCES
Red Hat OpenShift API Management service definition
## ADDING AND ACCESSING OPENSHIFT API MANAGEMENT
13

---

## Api Management Configuring Apis

Red Hat OpenShift API Management 1
Adding and configuring APIs
Adding and configuring APIs in Red Hat OpenShift API Management.
Last Updated: 2024-04-11

Red Hat OpenShift API Management 1 Adding and configuring APIs
Adding and configuring APIs in Red Hat OpenShift API Management.

Legal Notice
Copyright © 2024 Red Hat, Inc.
The text of and illustrations in this document are licensed by Red Hat under a Creative Commons
Attribution–Share Alike 3.0 Unported license ("CC-BY-SA"). An explanation of CC-BY-SA is
available at
http://creativecommons.org/licenses/by-sa/3.0/
. In accordance with CC-BY-SA, if you distribute this document or an adaptation of it, you must
provide the URL for the original version.
Red Hat, as the licensor of this document, waives the right to enforce, and agrees not to assert,
Section 4d of CC-BY-SA to the fullest extent permitted by applicable law.
Red Hat, Red Hat Enterprise Linux, the Shadowman logo, the Red Hat logo, JBoss, OpenShift,
Fedora, the Infinity logo, and RHCE are trademarks of Red Hat, Inc., registered in the United States
and other countries.
Linux ® is the registered trademark of Linus Torvalds in the United States and other countries.
Java ® is a registered trademark of Oracle and/or its affiliates.
XFS ® is a trademark of Silicon Graphics International Corp. or its subsidiaries in the United States
and/or other countries.
MySQL ® is a registered trademark of MySQL AB in the United States, the European Union and
other countries.
Node.js ® is an official trademark of Joyent. Red Hat is not formally related to or endorsed by the
official Joyent Node.js open source or commercial project.
The OpenStack ® Word Mark and OpenStack logo are either registered trademarks/service marks
or trademarks/service marks of the OpenStack Foundation, in the United States and other
countries and are used with the OpenStack Foundation's permission. We are not affiliated with,
endorsed or sponsored by the OpenStack Foundation, or the OpenStack community.
All other trademarks are the property of their respective owners.
Abstract
This guide provides information about how you can add and configure APIs in Red Hat OpenShift
API Management.

. . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 
. . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 
Table of Contents
MAKING OPEN SOURCE MORE INCLUSIVE
## ADDING AND CONFIGURING APIS IN RED HAT OPENSHIFT API MANAGEMENT
### PRODUCTS AND BACKENDS FOR 3SCALE APIS
### CREATING NEW PRODUCTS TO TEST API CALLS
### CREATING BACKENDS FOR YOUR PRODUCTS
### ADDING BACKENDS TO YOUR PRODUCTS
### THE BACKEND PATH OF A SPECIFIC PRODUCT
### DEFINING MAPPING RULES
### CREATING 3SCALE APPLICATION PLANS FOR PRODUCTS
### CREATING APPLICATIONS FOR THE DEFAULT ACCOUNT TO TEST API CALLS
### SENDING REQUESTS TO YOUR PRODUCT TO TEST THE INTEGRATION OF A BACKEND
3
4
4
4
5
6
6
7
8
8
9
Table of Contents
1

Red Hat OpenShift API Management 1 Adding and configuring APIs
2

MAKING OPEN SOURCE MORE INCLUSIVE
Red Hat is committed to replacing problematic language in our code, documentation, and web
properties. We are beginning with these four terms: master, slave, blacklist, and whitelist. Because of the
enormity of this endeavor, these changes will be implemented gradually over several upcoming releases.
For more details, see our CTO Chris Wright’s message.
MAKING OPEN SOURCE MORE INCLUSIVE
3
## ADDING AND CONFIGURING APIS IN RED HAT
OPENSHIFT API MANAGEMENT
As a 3scale API provider, you can add and configure your APIs.
Prerequisites
You have accessed OpenShift API Management.
### PRODUCTS AND BACKENDS FOR 3SCALE APIS
Red Hat 3scale API Management separates your APIs into two main groups:
Backends: Internal APIs bundled in a product. Backends grant API providers the freedom to map
their internal API organization structure to 3scale.
Products: Customer-facing APIs. Products facilitate the creation of strong and simplified
offerings for API consumers.
A product can contain multiple backends, and a backend can be used in multiple products. In other
words, to integrate and manage your API in 3scale you need to create both:
A backend containing at least the URL of your API. The backend can optionally be configured
with mapping rules, methods and metrics to facilitate reusability.
A product for which you define the application plans, and configure APIcast.
### CREATING NEW PRODUCTS TO TEST API CALLS
As a 3scale API provider, create products to test API calls through these public APIs. A product is a
customer-facing API that packages one or more backends.
You can create a new product by following one of these options:
Define the product manually.
Import the product from OpenShift.
Here you will find details about the manual definition. If you want to import a product from OpenShift,
see Service Discovery.
Prerequisites
You need a 3scale account.
Procedure
1. Go to the Dashboard. Under the APIs section, click Create Product in the Products card.
2. Provide the following details:
a. Name: Product identifier.
b
d
f
d f
l
d
d
Red Hat OpenShift API Management 1 Adding and configuring APIs
4

b. System name: Identifier used for internal purposes. A product system_name is used to
generate proxy endpoints and domain names. By default, system_name is part of a label
whose pattern can be as one of the alternatives below:
For APIcast staging: %{system_name}-%{tenant_name}-apicast-staging
For APIcast production: %{system_name}-%{tenant_name}-apicast-production
When an auto-generated URL label exceeds 63 characters, the system shortens the
label as follows: <truncated-label>-<unique-hash>
<truncated-label> is the first 54 or 55 characters of the original URL.
<unique-hash> is the first 7 characters of a unique SHA-1 hash calculated from the
original label.
For example, this is the URL before truncation:
https://my-very-long-system-name-also-very-long-tenant-name-apicast-
staging.3scale.net
This is the URL after the truncation:
https://my-very-long-system-name-also-very-long-tenant-name-api-
72588d2.3scale.net
c. Description: Optional field containing more details about the product.
3. Click Create Product.
After these steps, you have a product that represents the public facing API. The next steps are creating
backends and adding them to the product .
### CREATING BACKENDS FOR YOUR PRODUCTS
A backend is an internal API that is bundled to a product.
Prerequisites
You need a 3scale account.
Procedure
1. Go to the Dashboard.
2. Under the APIs section, click Create Backend in the Backends card.
3. Provide the following details:
Name: Backend identifier.
System name: Identifier used for internal purposes.
Description: Optional field containing more details about the backend.
Private endpoint: Base URL of the private API.
4. Click Create Backend.
## ADDING AND CONFIGURING APIS IN RED HAT OPENSHIFT API MANAGEMENT
5

After performing the steps for Creating backends for your API , you have an internal API. Add more
backends as needed.
Next steps
Add this backend to a product .
### ADDING BACKENDS TO YOUR PRODUCTS
By the end of this procedure you will have added a backend to a product. Repeat the procedure to add
more backends as you require them.
Prerequisites
A product. To create one, see Creating new products.
One or more backends. To create one, see Creating backends for your products .
Procedure
1. Navigate to [Your_product_name] > Integration > Backends.
2. Click Add Backend.
3. From the drop down list, select an existing backend.
4. Specify the routing path in the Path textbox. For more details, see The backend path of a
specific product.
5. Click Add to Product.
After these steps, your product will have a backend. You can follow this procedure again to add more
backends to a product or to multiple products.
### THE BACKEND PATH OF A SPECIFIC PRODUCT
When you add a backend to a product, you define the path that the backend uses to communicate with
the specified product. This path is not part of the backend.
From the procedure described in Adding backends to your products , APIcast is the API gateway that
uses the path of the backend you indicated in step 4. APIcast redirects the traffic to the backend with
the specified path matching the prefix of the requested endpoint path.
When defining the path for a backend:
You can indicate / as the path of one of the backends.
Paths must be unique inside the product. This means that you cannot add two backends with
the same path inside the same product. Neither can you add the same backend twice to the
same product.
You can give the same backend the same path in different products.
This is how the backend path works:
Red Hat OpenShift API Management 1 Adding and configuring APIs
6

Each backend added to a product is mounted in the specified path.
Before redirecting the traffic, the path is removed from the public URL of the request to the
product.
Example
Consider this configuration to add a backend to a product:
Backend: Inventory
Resource path: /list
Product: Petstore
Backend path: /supplies
These are the URLs used by your configuration:
Public URL: <public-api-domain>/supplies/list
Private URL: <private-api-domain>/list
This is the action flow when a request is sent:
1. The application sends a request.
2. The request is sent to the public URL: <public-api-domain>/supplies/list
3. The backend path is removed before redirecting to the private URL: <private-api-domain>/list
4. The request is redirected to the Inventory backend.
### DEFINING MAPPING RULES
A mapping rule associates a call to an endpoint with designated methods and metrics for tracking and
limiting access to your API. You can define mapping rules at the backend and product levels. The
advantage of defining mapping rules at the backend level is that you can add a backend to multiple
products. To learn more about the metrics or methods for which to collect usage information depending
on the requests to your API, both at the product and backend levels, see How APIcast applies mapping
rules for capturing usage of 3scale APIs.
Prerequisites
A backend. To create it, see Creating backends for your products .
Procedure
1. Navigate to the Backend you want to define mapping rules for.
2. In the page containing information about the backend, navigate to Mapping Rules.
3. Click Add Mapping Rule.
4. Specify the following settings:
Verb: The HTTP request verb ( GET, POST, DELETE, or PUT).
## ADDING AND CONFIGURING APIS IN RED HAT OPENSHIFT API MANAGEMENT
7

Pattern: The pattern to match. For example, /hello.
Metric or method to increment : The metric or method name.
Increment by: The metric increment number. For example, 1.
Last?: If this mapping rule should be considered as the last one, to stop processing other
mapping rules.
Position: Number that indicates the position of the execution of the mapping rule, to sort
the mapping rules.
5. Click Create Mapping Rule.
Next steps
After these steps, the mapping rule is added to Backends under [Your_API_backend] > Mapping
Rules. The mapping rule is also available for each product currently using the backend. To have the
mapping rule active at the product level, promote the latest configuration under the Products tab in
[Your_product_name] > Integration > Configuration.
Example
After you promote the configuration, 3scale activates the backend mapping rules at the product level.
The mapping rules follow the backend path specified in the product. For example, suppose you have this
configuration:
Pattern of the mapping rule at the backend: /thousands
Backend is added to a product with path: /unitprice
The mapping rule at the product level is: /unitprice/thousands.
### CREATING 3SCALE APPLICATION PLANS FOR PRODUCTS
A 3scale application plan defines the rules such as limits, pricing, and features for using your API product.
For more information, refer to Application plans and Designating methods and adding metrics for
capturing usage details.
Prerequisites
A product. To create one, see Creating new products.
Procedure
1. Navigate to [Your_product_name] > Applications > Application Plans.
2. Click Create Application Plan.
3. On the Create Application Plan page, enter a name and a system name for your new plan. A
system name must be unique in your 3scale installation.
4. Click Create Application Plan.
### CREATING APPLICATIONS FOR THE DEFAULT ACCOUNT TO TEST
API CALLS
Red Hat OpenShift API Management 1 Adding and configuring APIs
8

As a 3scale user, create applications for the default Developer account. An application subscribes to an
application plan. As a result of this subscription, 3scale provides the user key required to call an API
product.
An application is always associated with an application plan. Applications are stored within developer
accounts. In basic 3scale plans only a single application is allowed. In enterprise plans, multiple
applications per account are allowed.
Prerequisites
An application plan. To create one, see Creating 3scale application plans for products .
Procedure
1. Navigate to Audience > Accounts > Listing.
2. Click the default Developer account.
3. Go to the Application tab.
4. Click Create Application.
5. Choose an application plan.
6. Service plan contains the product plan configured by the Account Manager, which will be
associated to your application.
7. Specify an application name.
8. In the Description field, enter information about this application.
9. Click Create Application.
You can see your new application in Dashboard > Audience > Accounts > Applications > Listing.
### SENDING REQUESTS TO YOUR PRODUCT TO TEST THE
INTEGRATION OF A BACKEND
As a 3scale API provider, you can send a command line request to a product to test the integration of a
backend based on the first mapping rule added to the product.
Before you can send a test request, you must promote an APIcast configuration that includes the
backend that you want to test. A specific APIcast configuration consists of the backends you have added
to a product with the corresponding mapping rules, applications, and application plans.
3scale directs requests to the backend of a product according to the path specified in the request call.
For each backend of a product, you configured the backend path when you added the backend to the
product. In other words, each backend has its own path.
Prerequisites
One or more backends that you added to a product .
A mapping rule for each backend in a product.
An application plan to define the access policies.
## ADDING AND CONFIGURING APIS IN RED HAT OPENSHIFT API MANAGEMENT
9

An application that subscribes to the application plan.
Procedure
1. Promote the new APIcast configuration to staging:
a. Navigate to [Your_product_name] > Integration > Configuration.
b. Under APIcast Configuration, click Promote v.[n] to Staging APIcast.
v.[n] indicates the version number to be promoted.
If there are no changes to be promoted, there is a grayed button with the text Nothing
to promote.
2. Under Staging APIcast, promote the APIcast configuration to production by clicking Promote v.
[n] to Production APIcast.
v.[n] indicates the version number to be promoted.
If there are no changes to be promoted, you will see a grayed button with the text Nothing
to promote.
3. To test requests to your API product, copy the command provided in Example curl for testing
and run it in a terminal.
The curl command example is based on the first mapping rule of the product.
After you run the command, you should get an HTML response containing results from the
backend you are testing.
If you do not get a response, delete the catch-all mapping rule from your product, promote
the new APIcast configuration to staging and then to production, and run the example curl
command.
Next steps
You can confirm the different responses when changing metrics and methods, such as limits and pricing
rules. For any of the application plans of a product, modify the methods and metrics when testing
requests to your product. For more details, refer to adding methods and metrics.
Every time you modify the product configuration and before making calls to your API, you must promote
the updated configuration to the staging and production environments. When there are pending
changes to be promoted to the staging environment, there is an exclamation mark in the Admin Portal,
next to the Integration menu item.
Red Hat OpenShift API Management 1 Adding and configuring APIs
10

---

## Api Management Administering

Red Hat OpenShift API Management 1
Administering Red Hat OpenShift API
Management
Administering Red Hat OpenShift API Management.
Last Updated: 2024-04-11

Red Hat OpenShift API Management 1 Administering Red Hat OpenShift
API Management
Administering Red Hat OpenShift API Management.

Legal Notice
Copyright © 2024 Red Hat, Inc.
The text of and illustrations in this document are licensed by Red Hat under a Creative Commons
Attribution–Share Alike 3.0 Unported license ("CC-BY-SA"). An explanation of CC-BY-SA is
available at
http://creativecommons.org/licenses/by-sa/3.0/
. In accordance with CC-BY-SA, if you distribute this document or an adaptation of it, you must
provide the URL for the original version.
Red Hat, as the licensor of this document, waives the right to enforce, and agrees not to assert,
Section 4d of CC-BY-SA to the fullest extent permitted by applicable law.
Red Hat, Red Hat Enterprise Linux, the Shadowman logo, the Red Hat logo, JBoss, OpenShift,
Fedora, the Infinity logo, and RHCE are trademarks of Red Hat, Inc., registered in the United States
and other countries.
Linux ® is the registered trademark of Linus Torvalds in the United States and other countries.
Java ® is a registered trademark of Oracle and/or its affiliates.
XFS ® is a trademark of Silicon Graphics International Corp. or its subsidiaries in the United States
and/or other countries.
MySQL ® is a registered trademark of MySQL AB in the United States, the European Union and
other countries.
Node.js ® is an official trademark of Joyent. Red Hat is not formally related to or endorsed by the
official Joyent Node.js open source or commercial project.
The OpenStack ® Word Mark and OpenStack logo are either registered trademarks/service marks
or trademarks/service marks of the OpenStack Foundation, in the United States and other
countries and are used with the OpenStack Foundation's permission. We are not affiliated with,
endorsed or sponsored by the OpenStack Foundation, or the OpenStack community.
All other trademarks are the property of their respective owners.
Abstract
This guide provides information about administering Red Hat OpenShift API Management.

. . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 
. . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 
. . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 
. . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 
Table of Contents
MAKING OPEN SOURCE MORE INCLUSIVE
## ADMINISTERING OPENSHIFT API MANAGEMENT
### OVERVIEW OF RATE LIMITING, ALERTING, AND MONITORING IN OPENSHIFT API MANAGEMENT
### MONITORING SERVICE CAPACITY WITH GRAFANA
## NETWORK POLICIES
### ENABLING COMMUNICATION BETWEEN MANAGED SERVICES AND CUSTOMER APPLICATIONS
### ENABLING COMMUNICATION BETWEEN MANAGED SERVICES AND PROJECTS
### ENABLING COMMUNICATION BETWEEN CUSTOMER APPLICATIONS
### DISABLING COMMUNICATION FROM A MANAGED SERVICE TO A PROJECT
### ADDITIONAL RESOURCES
## DEFINING METHODS AND METRICS
### ADDING METHODS TO PRODUCTS AND BACKENDS
### ADDING METRICS TO PRODUCTS AND BACKENDS
### ALTERNATIVES FOR IMPORTING METHODS AND METRICS
### ADDING MAPPING RULES TO METHODS AND METRICS
3
4
4
4
6
6
7
8
9
9
10
10
11
12
12
Table of Contents
1

Red Hat OpenShift API Management 1 Administering Red Hat OpenShift API Management
2

MAKING OPEN SOURCE MORE INCLUSIVE
Red Hat is committed to replacing problematic language in our code, documentation, and web
properties. We are beginning with these four terms: master, slave, blacklist, and whitelist. Because of the
enormity of this endeavor, these changes will be implemented gradually over several upcoming releases.
For more details, see our CTO Chris Wright’s message.
MAKING OPEN SOURCE MORE INCLUSIVE
3
## ADMINISTERING OPENSHIFT API MANAGEMENT
You can perform configuration and monitoring tasks for managed services. With OpenShift API
Management you can manage network configurations, define policies, monitor API usage, and update
notification settings.
### OVERVIEW OF RATE LIMITING, ALERTING, AND MONITORING IN
OPENSHIFT API MANAGEMENT
Red Hat OpenShift API Management provides a limited number of API calls. The API request hard limits
are set as part of your Red Hat OpenShift API Management purchase.
You are alerted to ensure you do not exceed the set API usage hard limit. OpenShift API Management
uses a tiered approach for alerting customers when the API usage is close to the hard limit, to ensure
request calls are not rejected and service is not interrupted.
An email notification is sent to the email address provided during the OpenShift API Management
installation. The email notification includes a link to the Grafana dashboard, which provides a
visualization of your API usage.
NOTE
You can also access the Grafana dashboard through the OpenShift Dedicated console.
To access the dashboard, click the application launcher in OpenShift Dedicated and then
click API Management Dashboards in the OpenShift Managed Services drop-down
menu.
A notification is sent in the following instances:
If API usage is between 80% and 90% for a period of 4 hours, a notification is sent every 4
hours.
If API usage is between 90% and 95% for a period of 2 hours, a notification is sent every 2 hours.
If API usage is over 95% for a period of 30 minutes, a notification is sent every 30 minutes.
OpenShift API Management offers you the flexibility to share rate limiting quotas across multiple
environments. You can divide a purchased OpenShift API Management subscription level across
multiple OpenShift Dedicated environments. For example, if a 10 million API calls per day subscription
level is selected, you can allocate 5 million API calls per day to both a stage environment and a
production environment. For more information on increasing your Red Hat OpenShift API Management
subscription level, refer to the service definition.
### MONITORING SERVICE CAPACITY WITH GRAFANA
You can monitor the service capacity and API usage of OpenShift API Management with Grafana.
Access the Grafana dashboard from the OpenShift Dedicated console, to monitor the OpenShift API
Management API rate limits for the following durations:
last minute
last 24 hours
Red Hat OpenShift API Management 1 Administering Red Hat OpenShift API Management
4

In the dashboard, you can view, share, and inspect the data for the OpenShift API Management API
requests.
Prerequisites
Red Hat OpenShift API Management was added to your OpenShift Dedicated cluster.
Procedure
1. Click the application launcher in OpenShift Dedicated.
2. Click API Management Dashboards in the OpenShift Managed Services drop-down menu.
3. You are prompted to log in. Click Log in with OpenShift.
4. Enter login credentials, if prompted.
5. Click Allow selected permissions to authorize monitoring access.
6. In the Grafana console, click the Dashboards dropdown menu from the main menu to access
the available dashboards and folders.
7. Click Manage.
8. Click redhat-rhoam-customer-monitoring-operator to open the folder.
9. Click Rate Limiting to access the monitoring data.
NOTE
Alternatively, click Home to search for a dashboard by name.
## ADMINISTERING OPENSHIFT API MANAGEMENT
5
## NETWORK POLICIES
A cluster hosts two types of projects:
Projects associated with managed services. These projects support inbound and outbound
connections.
User projects. These projects support communication from managed services.
In OpenShift Dedicated, there are two approaches to enabling communications:
Using network policies
Using the join-project option of the oc command
In OpenShift API Management, you can use network policies to enable communication and allow 3scale
to communicate directly with the service endpoint, instead of the external URL.
You cannot use the join-projects option of the oc command with managed services projects.
### ENABLING COMMUNICATION BETWEEN MANAGED SERVICES
AND CUSTOMER APPLICATIONS
You can create NetworkPolicy objects to define granular rules describing the Ingress network traffic
that is allowed for projects in your cluster. By default, when you create projects in a cluster,
communication between the projects is disabled.
This procedure describes how to enable communication for a project so that managed services, such as
3scale, can access customer applications.
Prerequisites
You have installed the OpenShift command-line interface (CLI), commonly known as oc.
Procedure
1. Log in to the cluster using the oc login command.
2. Use the following command to change the project:
$ oc project <project_name>
where <project_name> is the name of a project that you want to accept communications from
other projects.
3. Create a NetworkPolicy object:
a. Create a allow-from-middleware-namespaces.yaml file.
b. Define a policy in the file you just created, such as in the following example:
apiVersion: networking.k8s.io/v1
kind: NetworkPolicy
metadata:
  name: allow-from-middleware-namespaces
Red Hat OpenShift API Management 1 Administering Red Hat OpenShift API Management
6

spec:
  podSelector:
  ingress:
    - from:
        - namespaceSelector:
            matchLabels:
              integreatly-middleware-service: 'true'
c. Run the following command to create the policy object:
$ oc create -f allow-from-middleware-namespaces.yaml -n <project>
networkpolicy "allow-from-middleware-namespaces" created
### ENABLING COMMUNICATION BETWEEN MANAGED SERVICES
AND PROJECTS
By default, when you create projects in a cluster, communication between the projects is disabled. Use
this procedure to enable communication in a project.
Prerequisites
You have installed the OpenShift command-line interface (CLI), commonly known as oc.
Procedure
1. Log in to the cluster using the oc login command.
2. Use the following command to change the project:
$ oc project <project_name>
where <project_name> is the name of a project that you want to accept communications from
other projects.
3. Create a NetworkPolicy object:
a. Create a NetworkPolicy.yaml file.
b. Define a policy in the file you just created, such as in the following example.
This policy enables incoming communication for all projects in the cluster:
kind: NetworkPolicy
apiVersion: networking.k8s.io/v1
metadata:
  name: allow-all
spec:
  podSelector:
  ingress:
  - {}
## NETWORK POLICIES
7

NOTE
This policy configuration enables this project to communicate with all
projects in the cluster.
c. Run the following command to create the policy object:
$ oc create -f <policy-name>.yaml -n <project>
### ENABLING COMMUNICATION BETWEEN CUSTOMER
APPLICATIONS
You can enable communication between user applications.
Prerequisites
You have installed the OpenShift command-line interface (CLI), commonly known as oc.
Procedure
1. Log in to the cluster using the oc login command.
2. Use the following command to change the project:
$ oc project <project_name>
<project_name> is the name of a project that you want to accept communications from.
3. Create a NetworkPolicy object:
a. Create a allow-from-myproject-namespace.yaml file.
b. Define a policy in the file you just created, such as in the following example.
This policy enables incoming communication for a specific project (myproject):
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
4. Run the following commands to create the policy object:
$ oc create -f allow-from-myproject-namespace.yaml -n <project>
networkpolicy "allow-from-myproject-namespace" created
Red Hat OpenShift API Management 1 Administering Red Hat OpenShift API Management
8
### DISABLING COMMUNICATION FROM A MANAGED SERVICE TO A
PROJECT
By default, projects are created with a template that allows communication from a managed service. For
example, 3scale can communicate with all of your projects.
You can disable the communication from a managed service to a project.
Prerequisites
You have installed the OpenShift command-line interface (CLI), commonly known as oc
You have a project you want to isolate from the managed services.
Procedure
1. Log in to the cluster using the oc login command.
2. Use the following command to change the project:
$ oc project <project_name>
where <project_name> is the name of a project that you want to isolate from the managed
services.
3. Create a NetworkPolicy object:
a. Create a deny-all.yaml file.
b. Define a policy in the file you just created, such as in the following example:
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
c. Run the following command to create the policy object:
$ oc create -f <policy-name>.yaml -n <project>
### ADDITIONAL RESOURCES
Networking in OpenShift Dedicated
## NETWORK POLICIES
9
## DEFINING METHODS AND METRICS
An application plan sets limits and pricing rules for consumer access to your API. To enable enforcement
of limits and rules, designate methods in your API for which to collect individual usage data or add
metrics. Add a mapping rule to each designated method and each custom metric. The mapping rule
specifies details about the usage data that you want to capture.
For more information about methods and metrics, see Designating methods and adding metrics for
capturing usage details.
### ADDING METHODS TO PRODUCTS AND BACKENDS
Adding a method to a product or backend means that you are designating a method in your API for
which you want to capture individual usage details. An application plan provides the ability to set a limit
for each method that you add to a product or backend. The procedure for adding a method or metric to
a product is similar to adding a method or metric to a backend.
Procedure
1. Navigate to [Your_product_name] > Integration > Methods & Metrics or
[Your_backend_name] > Methods & Metrics.
2. Click New method.
3. In the Friendly name field, enter a short description of the method. This name is displayed in
different sections of the 3scale Admin Portal. The friendly name must be unique for the
product.
IMPORTANT
Be careful with changing the system name of the methods or deleting them.
These changes can break your already deployed 3scale integration if there are
mapping rules pointing to the previous system name of the method.
4. In the System name field, enter the name of the method in your API to use to report the usage
through the 3scale Service Management API. The system name must conform to these rules:
Unique in the product or backend
Contain only alphanumeric characters, underscore _, hyphen - or forward slash /
No spaces
Otherwise, you are free to decide what the system name looks like. It can be the same as the
endpoint (/status), or, for example, it can include the method and the path ( GET_/status).
5. Optional: In the Description field, enter a more detailed description of the method.
6. Click Create Method.
Verification steps
Added methods are available in your application plans.
Red Hat OpenShift API Management 1 Administering Red Hat OpenShift API Management
10

Next steps
Edit limits and pricing rules for each method by going to [Your_product_name] > Applications
> Application Plans > [plan_you_want_to_edit].
### ADDING METRICS TO PRODUCTS AND BACKENDS
Adding a metric specifies a usage unit that you want to capture for all calls to your API. An application
plan provides the ability to set a limit for each metric that you add to a product or backend. The
procedure for adding a method or metric to a product is similar to adding a method or metric to a
backend.
Procedure
1. Navigate to [Your_product_name] > Integration > Methods & Metrics or
[Your_backend_name] > Methods & Metrics.
2. Click New metric.
3. In the Friendly name field, enter a short description of the metric. This name is displayed in
different sections of the 3scale Admin Portal. The friendly name must be unique for the
product.
IMPORTANT
Be careful with changing the system name of the metrics or deleting them. These
changes can break your already deployed 3scale integration if there are mapping
rules pointing to the previous system name of the metric.
4. In the System name field, enter the name of the metric in your API to use to report the usage
through the 3scale Service Management API. The system name must conform to these rules:
Unique in the product or backend
Contain only alphanumeric characters, underscore _, hyphen - or forward slash /
No spaces
Otherwise, you are free to decide what the system name looks like.
5. In the Unit field, enter the unit.
Use a singular noun, for example, hit. The singular will become plural in the analytics charts.
6. Optional: In the Description field, enter a more detailed description of the metric.
7. Click Create Metric.
Verification steps
Added metrics are available in your application plans.
Next steps
## DEFINING METHODS AND METRICS
11

Edit limits and pricing rules for each metric by going to [Your_product_name] > Applications >
Application Plans > [plan_you_want_to_edit].
Map your metrics to one or more URL pattern by going to [Your_product_name] > Integration
> Mapping Rules. See Adding mapping rules to methods and metrics .
### ALTERNATIVES FOR IMPORTING METHODS AND METRICS
If your API has multiple endpoints, there are two ways to automatically designate methods and add
metrics to 3scale products and backends:
Importing via Swagger spec .
Importing via RAML spec .
### ADDING MAPPING RULES TO METHODS AND METRICS
Mapping rules are operations that are mapped to previously created methods and metrics in your
products and backends.
NOTE
Mapping rules are required in your previously created methods, however, they are
optional for metrics.
Procedure
1. Navigate to [Your_product_name] > Integration > Mapping Rules.
2. Click Add Mapping Rule.
3. The Verb field is pre-populated with the HTTP method, GET, however you can select other
options from the dropdown list.
4. In the Pattern field, add a valid URL that starts with an forward slash /. The URL can be from a
wildcard you specified inside curly brackets {}.
5. In the Metric or Method to increment field, select from one of your previously created
methods or metrics.
6. The Increment by field is pre-populated with 1, however, change this to suit your own needs.
7. Click the Create Mapping Rule button.
Verification steps
To verify your mapping rules, navigate to [Your_product_name] > Integration > Methods &
Metrics. Each method and metric should have a check mark in the Mapped column.
Red Hat OpenShift API Management 1 Administering Red Hat OpenShift API Management
12