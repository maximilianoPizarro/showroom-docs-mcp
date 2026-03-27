# Red Hat Developer Hub 1.9

Assembled from Red Hat Documentation (html-single), version 1.9. Section URLs are listed at the start of each block below.

---

*Source: https://docs.redhat.com/en/documentation/red_hat_developer_hub/1.9/html-single/about_red_hat_developer_hub/index*

About Red Hat Developer Hub | Red Hat Developer Hub | 1.9 | Red Hat Documentation

 

 

 

 

 

 

 

 

# About Red Hat Developer Hub

---

Red Hat Developer Hub 1.9

## Red Hat Developer Hub is a customizable developer portal with enterprise-level support and a centralized software catalog that you can use to build high-quality software efficiently in a streamlined development environment

Red Hat Customer Content Services

Legal Notice

Abstract

Red Hat Developer Hub (RHDH) is a customizable developer portal with enterprise-level support and a centralized software catalog that you can use to build high-quality software efficiently in a streamlined development environment.

---

## PrefaceCopy link

Red Hat Developer Hub (RHDH) is an enterprise-grade internal developer portal (IDP) that helps simplify and accelerates software delivery. It provides a customizable web-based interface that centralizes access to key development resources, including source code repositories, CI and CD pipelines, APIs, documentation, and runtime environments.

Red Hat Developer Hub is designed for cloud-native environments, including supported Kubernetes platforms, Red Hat OpenShift Container Platform, and hybrid infrastructure. By consolidating tools and standardizing development workflows, it helps teams deliver software faster with more consistency.

Designed for enterprise-scale software teams, RHDH helps developers focus on building software rather than managing tools. Developers can onboard quickly, create environments, and integrate with existing systems. With enterprise-grade security, role-based access control, and 24x7 support, teams stay productive while meeting compliance and reliability standards.

## Chapter 1. Understanding internal developer platformsCopy link

An internal developer platform (IDP) is a curated set of tools and services that supports developer self-service. Instead of navigating multiple systems, developers use a unified interface to provision environments, deploy code, and access APIs.

Why IDPs matter IDPs address the challenges of modern software delivery by enabling self-service, enforcing standards, and improving the developer experience. For organizations

- Legal Notice
- Scalability: RHDH enables consistent developer onboarding and application delivery across growing teams and environments.
- Security: Role-based access control (RBAC) and integration with enterprise systems ensure access is managed securely and in line with compliance requirements.
- Operational efficiency: By removing manual handoffs and centralizing key development workflows, RHDH improves time to value and increases return on engineering investment.

For platform engineers

- Curated platforms: Platform teams can design reusable templates and integrations aligned with organizational policies and developer needs.
- Central configuration: Infrastructure and policies are defined as code and centrally managed, reducing drift and maintenance overhead.
- Governance at scale: Policies and best practices are embedded into developer workflows using automation and templates, without adding friction to the process.

For developers

- Faster onboarding: Developers can use learning paths, software templates, and software catalog to deploy compliant services within minutes, without depending on other teams for setup.
- Reduced cognitive load: Developers can find tools, documentation, and deployment environments in one place, eliminating the need to switch between systems or manage disconnected resources.
- Self-service workflows: Developers can create applications or environments on demand, without raising tickets or waiting for approvals.
- Built-in standards: Developers can use preconfigured templates that enforce secure, compliant workflows without requiring manual setup.
- Cross-team visibility: Developers can discover shared service catalogs and documentation to improve reuse and reduce duplication.
- Higher productivity: Developers can spend more time building features and less time configuring infrastructure or resolving toolchain inconsistencies.

Key features

Centralized dashboard Access development tools, CI/CD pipelines, APIs, monitoring tools, and documentation from a single interface. Integrate with systems like Git, Red Hat OpenShift Container Platform, Kubernetes, and JIRA. Learning paths Guide developers through structured tutorials and onboarding steps. Help teams upskill with internal and Red Hat training resources in one place. Plugins and integrations Extend RHDH with verified plugins that add new functionality without downtime. Dynamically integrate with supported tools such as Tekton for pipelines, GitOps for deployment automation, Nexus Repository for artifact storage, and JFrog Artifactory. RHDH also supports connecting to Red Hat OpenShift Container Platform, CI/CD systems, and security scanners through Red Hat-curated extensions. Role-Based Access Control (RBAC) Manage user access with robust security permissions tailored to organizational needs. Software catalog Search, view, and manage services, APIs, and libraries from a central inventory. Track ownership, metadata, and component health in one place. Software templates Accelerate project setup using preconfigured templates for CI/CD, runtime, and security. Standardize implementation while enabling developer autonomy. Tech docs Create, store, and view technical documentation alongside code. Make content searchable, consistently formatted, and accessible through the portal. Scalability Support growing teams and applications while maintaining access to the same tools and services.

## Chapter 2. System architecture for deployment planningCopy link

Red Hat Developer Hub (RHDH) uses a client-server architecture with a browser-based frontend and a stateless backend service layer. Use this architecture to plan for horizontal scaling, high availability, and efficient data synchronization across the Software Catalog.

By understanding the RHDH architecture, you can perform the following planning tasks:

Plan scalable deployments Deploy multiple backend instances behind a load balancer to manage increased load. Ensure high availability Configure database replication and cache clustering to eliminate single points of failure. Optimize resource allocation Assign infrastructure resources based on which components require persistent storage or high-performance memory.

The following diagram shows the RHDH internal architecture (frontend and backend) and its external dependencies, such as authentication providers, load balancers, and databases:

The RHDH architecture includes three primary layers. While the data layer (PostgreSQL and optional Redis cache) stores the indexed Software Catalog, the source of truth remains in external systems, such as Git repositories, CI/CD platforms, and other integrations. Catalog providers continuously scan these external systems and synchronize data to the database for fast querying.

Frontend (Client)

The frontend is a browser-based single-page application (SPA). Use the frontend interface to browse the Software Catalog, interact with plugins, and connect to external integrations. The frontend communicates with the backend exclusively using REST API calls.

Backend (Service Layer)

The backend provides REST API endpoints for the frontend. It manages the Software Catalog (an inventory of your organization’s software components, APIs, and resources) and handles authentication.

The stateless design allows you to scale the backend horizontally by running multiple instances behind a load balancer. The backend externalizes all persistent state to a PostgreSQL database, including:

- Catalog entities
- Task history
- Session data (managed through a database-backed session store)

External data dependencies

RHDH requires PostgreSQL for persistence. For production environments, use a logical cache to improve performance.

PostgreSQL database Stores indexed Software Catalog entities (synchronized from external systems like Git repositories and CI/CD platforms), profiles, authentication data, and backend state. You must configure PostgreSQL with high availability (HA) for production deployments. Redis Cache (Optional) Configure Redis as a shared logical cache across backend instances to improve performance for frequently accessed data, such as rendered TechDocs and catalog entities.

Tip

The default in-memory cache is suitable only for single-instance deployments. You must use Redis for production deployments with multiple backend instances to ensure cache consistency.

## Chapter 3. Achieve high availability with database and cache layersCopy link

To achieve high availability (HA) in Red Hat Developer Hub, you must implement redundancy and failover for the backend service and its external data dependencies. This configuration uses horizontal scaling, database replication, and a shared logical cache to make sure RHDH remains operational during component failures.

Backend scalability

RHDH backend uses a stateless design to support horizontal scaling. PostgreSQL stores persistent data and the database manages sessions, allowing multiple backend instances to serve any request simultaneously. To improve performance, you can configure an optional logical cache using Redis.

To maintain backend availability, observe the following architectural requirements:

Deploy multiple backend instances Run at least two backend instances for basic HA. Configure a load balancer Use platform-provided load balancing, such as OpenShift Routes, Kubernetes Ingress, or cloud provider load balancers. Enable health checks Configure the load balancer to probe backend health and remove failed instances from rotation. Disable session affinity (sticky sessions) Database-backed sessions allow any instance to serve any request.

Database high availability

RHDH operations rely on PostgreSQL for persistence. A database outage renders the deployment non-functional until the database is restored. For production deployments, you must configure PostgreSQL with high availability (primary-replica replication) to minimize downtime.

Important

If you use catalog providers exclusively, the database acts as an indexed cache. You do not require disaster recovery backups because you can repopulate catalog data from external sources of truth, such as Git repositories, CI/CD platforms, and monitoring tools.

Cache high availability (optional)

Configuring Redis as a shared logical cache improves production performance by sharing cached data across multiple backend instances. A shared cache makes sure that all instances access the same processed data, such as rendered TechDocs.

If the logical cache fails, the platform remains functional, but you might experience the following symptoms:

- Slower response times due to cache misses.
- Increased database load because the backend must fetch data from PostgreSQL.
- No impact on authentication or core functionality.

For maximum performance stability in production, configure Redis with high availability using Redis Sentinel for small deployments or Redis Cluster for larger deployments.

## Chapter 4. Integrations in Red Hat Developer HubCopy link

Red Hat Developer Hub integrates seamlessly with Red Hat OpenShift Container Platform and other tools, enabling comprehensive development and deployment workflows across enterprise.

### 4.1. Integration with Red Hat OpenShift Container PlatformCopy link

Red Hat Developer Hub is fully integrated with Red Hat OpenShift Container Platform, offering:

- Operators to manage application lifecycle.
- Access to advanced OpenShift capabilities such as service mesh, serverless functions, GitOps, and distributed tracing.
- Pipelines and GitOps plugins for streamlined cloud-native workflows.

### 4.2. Integration with Red Hat Advanced Developer Suite - secure supply chainCopy link

Red Hat Advanced Developer Suite - secure supply chain (RHADS - ssc) enhances Red Hat Developer Hub by providing secure CI/CD capabilities that integrate security measures into every stage of the development process.

While Red Hat Developer Hub focuses on the inner loop (code, build, and test), RHADS - ssc manages the outer loop, automating:

- Code scanning
- Image building
- Vulnerability detection
- Deployment

RHADS - ssc includes tools like Red Hat Trusted Artifact Signer (TAS) for code integrity, Red Hat Trusted Profile Analyzer (TPA) for automated Software build of Materials (SBOM) creation, and Red Hat Advanced Cluster Security (ACS) for vulnerability scanning.

### 4.3. Extending Backstage with Red Hat Developer HubCopy link

Red Hat Developer Hub which is a fully supported, enterprise-grade productized version of upstream Backstage extends the upstream project by adding:

- Enhanced search capabilities that aggregate data from CI/CD pipelines, cloud providers, source control, and more.
- A centralized software catalog for locating applications, APIs, and resources.
- Automation through open-source plugins that expand Backstage’s core functionality.
- Simplified technical documentation using Markdown and GitHub, with integrated search for easy navigation.

## Chapter 5. Supported platformsCopy link

You can find the supported platforms and life cycle dates for both current and past versions of Red Hat Developer Hub on the Life Cycle page.

Additional resources

- [Life Cycle page](https://access.redhat.com/support/policy/updates/developerhub)

## Chapter 6. Sizing requirements for Red Hat Developer HubCopy link

Learn about sizing requirements for Red Hat Developer Hub. Table 1 lists the sizing requirements for installing and running Red Hat Developer Hub, including Developer Hub application, database components, and Operator. Table 2 lists recommended sizing requirements for [external PostgreSQL deployment](https://docs.redhat.com/en/documentation/red_hat_developer_hub/1.9/html-single/configuring_red_hat_developer_hub/index#configuring-external-postgresql-databases) based on the deployment scale.

Expand

Central Processing Unit (CPU)

4 vCPU

2 vCPU

1 vCPU

Memory

16 GB

8 GB

1500 Mi

Storage size

2 GB

20 GB

50 Mi

Replicas

2 or more

3 or more

1 or more

| Table 6.1. Recommended sizing for running Red Hat Developer Hub | Components | Red Hat Developer Hub application | Red Hat Developer Hub database | Red Hat Developer Hub Operator |
| --- | --- | --- | --- | --- |

Show more

Expand

Application usage

up to 5 thousand entities, up to 50 concurrent users

5–20 thousand entities, 50–150 concurrent users

20–50 thousand entities, 150–400 concurrent users

50–150 thousand entities, 400–800 concurrent users

vCPU

2

4

8

16

Memory

8 GiB

16 GiB

32 GiB

64 GiB

Storage

50 GiB

100 GiB

200 GiB

500 GiB

Number of replicas

1

2

2-3

3+

PostgreSQL Database HA

1 primary

1 primary, 1 standby

1 primary, 1 synchronous standby

1 primary, 1 synchronous standby, 1 asynchronous replica

| Table 6.2. Recommended sizing for external PostgreSQL deployments with Red Hat Developer Hub | Sizing legend | Small-scale | Mid-scale | Large-scale | Enterprise-scale |
| --- | --- | --- | --- | --- | --- |

Show more

## Chapter 7. Red Hat Developer Hub supportCopy link

If you experience difficulty with a procedure described in this documentation, visit the Red Hat Customer Portal. You can use the Red Hat Customer Portal for the following purposes:

- To search or browse through the Red Hat Knowledgebase of technical support articles about Red Hat products.
- To create a support case for Red Hat Global Support Services (GSS), select Red Hat Developer Hub as the product and select the appropriate product version.

Additional resources

- [Red Hat Customer Portal](https://access.redhat.com/)
- [Create a support case for Red Hat Global Support Services (GSS)](https://access.redhat.com/support/cases/#/case/new)
- [Red Hat Developer Hub Life Cycle](https://access.redhat.com/support/policy/updates/developerhub)

Next steps

- [Installing Red Hat Developer Hub on Amazon Elastic Kubernetes Service](https://docs.redhat.com/en/documentation/red_hat_developer_hub/1.9/html-single/installing_red_hat_developer_hub_on_amazon_elastic_kubernetes_service_eks/index)
- [Installing Red Hat Developer Hub on Google Cloud on Google Cloud](https://docs.redhat.com/en/documentation/red_hat_developer_hub/1.9/html-single/installing_red_hat_developer_hub_on_openshift_dedicated_on_google_cloud/index)
- [Installing Red Hat Developer Hub on Google Kubernetes Engine](https://docs.redhat.com/en/documentation/red_hat_developer_hub/1.9/html-single/installing_red_hat_developer_hub_on_google_kubernetes_engine_gke/index)
- [Installing Red Hat Developer Hub on Microsoft Azure Kubernetes Service](https://docs.redhat.com/en/documentation/red_hat_developer_hub/1.9/html-single/installing_red_hat_developer_hub_on_microsoft_azure_kubernetes_service_aks/index)
- [Installing Red Hat Developer Hub on OpenShift Container Platform](https://docs.redhat.com/en/documentation/red_hat_developer_hub/1.9/html-single/installing_red_hat_developer_hub_on_openshift_container_platform/index)

## Legal NoticeCopy link

Copyright © Red Hat.

Except as otherwise noted below, the text of and illustrations in this documentation are licensed by Red Hat under the Creative Commons Attribution–Share Alike 3.0 Unported license . If you distribute this document or an adaptation of it, you must provide the URL for the original version.

Red Hat, as the licensor of this document, waives the right to enforce, and agrees not to assert, Section 4d of CC-BY-SA to the fullest extent permitted by applicable law.

Red Hat, the Red Hat logo, JBoss, Hibernate, and RHCE are trademarks or registered trademarks of Red Hat, LLC. or its subsidiaries in the United States and other countries.

Linux® is the registered trademark of Linus Torvalds in the United States and other countries.

XFS is a trademark or registered trademark of Hewlett Packard Enterprise Development LP or its subsidiaries in the United States and other countries.

The OpenStack® Word Mark and OpenStack logo are trademarks or registered trademarks of the Linux Foundation, used under license.

All other trademarks are the property of their respective owners.

---

*Source: https://docs.redhat.com/en/documentation/red_hat_developer_hub/1.9/html-single/red_hat_developer_hub_release_notes/index*

Red Hat Developer Hub release notes | Red Hat Developer Hub | 1.9 | Red Hat Documentation

 

 

 

 

 

 

 

 

# Red Hat Developer Hub release notes

---

Red Hat Developer Hub 1.9

## Release notes for Red Hat Developer Hub 1.9

Red Hat Customer Content Services

Legal Notice

Abstract

Red Hat Developer Hub (Developer Hub) 1.9 is now generally available. Developer Hub is a fully supported, enterprise-grade productized version of upstream Backstage 1.45.3. This document contains release notes for the Red Hat Developer Hub 1.9.

---

## PrefaceCopy link

Red Hat Developer Hub (Developer Hub) 1.9 is now generally available. Developer Hub is a fully supported, enterprise-grade productized version of upstream Backstage, compatible with version 1.45.3. Plugins might be compatible with a newer Backstage version. You can access and download the Red Hat Developer Hub application from the Red Hat [Customer Portal](https://access.redhat.com/downloads) or from the [Ecosystem Catalog](https://catalog.redhat.com/en/search?gs&amp;q=rhdh&amp;searchType=containers).

## Chapter 1. New features and enhancementsCopy link

This section highlights new features in Red Hat Developer Hub 1.9.

Optional cache label filter to fix Operator pod out-of-memory errors This update introduces an optional cache-level label filter. Previously, the Operator pod could experience out-of-memory errors on clusters with many Secrets and ConfigMaps. This new filter limits the Operator cache to only Secrets and ConfigMaps labeled with`rhdh.redhat.com/external-config=true`. As a result, the Operator consumes less memory. To enable the filter, use the`--enable-cache-label-filter` command-line parameter.

Scaffolder Backend Module HTTP Request package from RoadieHQ is now GA supported

With this update, the`roadiehq-scaffolder-backend-module-http-request` package is now fully supported.

Additional resources

- Legal Notice
- [Community plugins migration to the GitHub Container Registry](https://docs.redhat.com/en/documentation/red_hat_developer_hub/1.9/html-single/dynamic_plugins_reference/index#ref-community-plugins-migration_title-plugins-rhdh-about).

Documentation for disabling the guest user login

Before this update, the documentation did not explain how to disable the guest user from the login page. With this update, the documentation contains procedures explaining how to disable and enable the guest user.

Additional resources

- [Authentication in Red Hat Developer Hub](https://docs.redhat.com/en/documentation/red_hat_developer_hub/1.9/html-single/authentication_in_red_hat_developer_hub/index)

Floating "Lightspeed" button

This update introduces a globally accessible floating button that replaces the Lightspeed entry in the navigation menu to provide immediate access to generative AI assistance from any page without interrupting your workflow. As a result, you can interact with the chat window in overlay, docked, or fullscreen display modes. While the window provides a convenient interface, your complete chat history is still visible, managed, and accessible from the fullscreen Developer Lightspeed application.

Additional resources

- [Managing chats](https://docs.redhat.com/en/documentation/red_hat_developer_hub/1.9/html-single/navigate_red_hat_developer_hub_on_your_first_day/index#proc-managing-chats_import-and-use-an-existing-template-for-faster-development)

Simplified plugin management

When installing dynamic plugins as OCI packages, the`!path` suffix after the OCI reference is no longer needed if the OCI package only contains 1 plugin. For example, you can use`oci://my-quay/my-org/my-repo:tagOrSHA` rather than`oci://my-quay/my-org/my-repo:tagOrSHA!some-plugin-folder-name`.

With this update, administrators can also use the`{{inherit}}` tag when referencing OCI images in dynamic plugin config files. This simplifies plugin updates, decouples plugin releases from the main Red Hat Developer Hub release flow, and improves air-gapped deployments by centralizing plugin versioning. The script will infer the precise version from an included default plugin configuration, reducing the manual burden of updating plugin versions. This change allows users to reference dynamic plugins without specifying tags or SHAs in OCI references, streamlining updates and enhancing air-gapped deployments.

Additional resources

- [Loading a plugin packaged as an OCI image](https://docs.redhat.com/en/documentation/red_hat_developer_hub/1.9/html-single/installing_and_viewing_plugins_in_red_hat_developer_hub/index#proc-load-plugin-oci-image_assembly-install-third-party-plugins-rhdh)

Support for mirroring Red Hat Developer Hub dynamic plugin OCI artifacts to disconnected environments

This update introduces support for mirroring Red Hat Developer Hub dynamic plugin OCI artifacts to disconnected environments. You can use the new`mirror-plugins.sh` script to mirror dynamic plugins from the official RHDH plugin catalog to your internal registries. The script supports multiple plugin sources, including catalog index resolution (`--plugin-index`), file-based plugin lists (`--plugin-list`), and direct OCI URLs (`--plugins`).

For fully disconnected environments, the script provides a two-phase workflow. First, you export plugins to a local directory (`--to-dir`) on a connected host. Then, you import the plugins from that directory (`--from-dir`) to an internal registry on the disconnected network.

The script automatically rebuilds the catalog index with updated registry references and generates a`rhdh-plugin-mirroring-summary.txt` mapping file to reference when configuring your deployment. This solution is installation-method agnostic and works with both Operator and Helm deployments on OpenShift Container Platform and Kubernetes platforms.

Additional resources

- [Mirroring Red Hat Developer Hub dynamic plugins in disconnected environment](https://docs.redhat.com/en/documentation/red_hat_developer_hub/1.9/html-single/installing_and_viewing_plugins_in_red_hat_developer_hub/index#assembly-mirroring-dynamic-plugins-in-disconnected-environments_rhdh-installing-rhdh-plugins)

Auto-logout mechanism for inactive users

This update introduces an auto-logout feature that helps mitigate the risks associated with unauthorized access through abandoned sessions. As a result, you can configure an idle session timeout that automatically logs users out after a set period of inactivity. To prevent data loss, a warning notifies users before the session terminates so that they can extend their session or conclude their work securely.

Additional resources

- [Enable auto-logout for inactive users](https://docs.redhat.com/en/documentation/red_hat_developer_hub/1.9/html-single/authentication_in_red_hat_developer_hub/index#enable-auto-logout-for-inactive-users)

Aggregated scorecard metrics by ownership

With this update, you can save scorecard metrics to the database. Because the scheduler manages these saves, you can independently configure the collection frequency for each metric provider and the data retention period. This enhancement improves data fetching times for scorecards and enables data aggregation.

This update also includes scorecard aggregation cards for the homepage, which are particularly useful for Engineering and Product Managers. Users gain role-based access to these aggregated metrics. This feature provides Key Performance Indicators (KPIs) aggregated across multiple owned applications or components, providing a quick overview of all owned scorecard entities.

Additional resources

- [Monitor collective health across components](https://docs.redhat.com/en/documentation/red_hat_developer_hub/1.9/html-single/evaluate_project_health_using_scorecards/index#monitoring-collective-health-across-components)

Template update pull requests

With this update, Red Hat Developer Hub introduces support for automatically creating pull requests when a software component becomes out of sync with the scaffolder template it was created from. When the scaffolder relation processor detects a template version update, it can generate a pull request (or merge request) containing the required updates.

This capability helps teams keep their components aligned with the latest template structure and best practices without requiring manual comparison or updates. The feature supports both GitHub and GitLab repositories and can optionally assign reviewers based on component ownership.

The automatic pull request functionality is disabled by default and can be controlled through configuration.

Loki logs integration for Orchestrator workflows

With this update, you can integrate Loki logs into the Red Hat Developer Hub interface to troubleshoot and debug workflow errors. To use this feature, you must install the Loki backend module and configure the connection details in your application configuration.

Additional resources

- [Integrating Loki logs for Orchestrator workflows](https://docs.redhat.com/en/documentation/red_hat_developer_hub/1.9/html-single/orchestrator_in_red_hat_developer_hub/index#proc-integrating-loki-logs-for-orchestrator-workflows_orchestrator-rhdh)

Workflow deployment automation with Orchestrator

With this update, the Orchestrator plugin automates the software development lifecycle (SDLC) for serverless workflows. You can use the Basic workflow bootstrap software template to generate a complete project structure that supports use cases such as cloud migration, developer onboarding, and custom workflows.

When you launch this template, the Orchestrator creates two distinct repositories:

- Source code repository: Contains the serverless workflow project, which utilizes the Quarkus project layout.
- GitOps repository: Stores deployment configurations, Tekton pipeline templates, and bootstrap instructions for Argo CD.

This feature integrates the following components to enable automated continuous integration (CI) and continuous deployment (CD):

- Red Hat Developer Hub Helm chart
- Tekton or Red Hat OpenShift Pipelines
- Argo CD or Red Hat OpenShift GitOps
- `Quay.io` container registry
- OpenShift Serverless Logic Operator

Additional resources

- [Automating workflow deployment with Orchestrator](https://docs.redhat.com/en/documentation/red_hat_developer_hub/1.9/html-single/orchestrator_in_red_hat_developer_hub/index#assembly-automating-workflow-deployment-with-orchestrator)

Secure service-to-service authentication

With this update, you can configure secure service-to-service authentication within Red Hat Developer Hub. Service-to-service authentication is a security mechanism that verifies the identity of a service making a request to another service without requiring direct user interaction. You can securely authenticate internal plugin-to-plugin requests and external service-to-plugin communication, and apply fine-grained access controls for external systems.

Additional resources

- [Enable service-to-service authentication](https://docs.redhat.com/en/documentation/red_hat_developer_hub/1.9/html-single/authentication_in_red_hat_developer_hub/index#enable-service-to-service-authentication)

Backstage Operator supports`StatefulSet` deployments

This update introduces support for deploying the Backstage application using a`StatefulSet` instead of a`Deployment`. This deployment method provides stable network identities and persistent storage guarantees for Backstage pods, which is beneficial for configurations such as persistent volume claim (PVC) based plugin cache storage. As a result, you can use the new`spec.deployment.kind` field in the`v1alpha5` API to specify the workload type as`Deployment`(default) or`StatefulSet`(new).

Additional resources

- [Fix 404 error after cached dynamic plugins configuration change](https://docs.redhat.com/en/documentation/red_hat_developer_hub/1.9/html-single/configuring_red_hat_developer_hub/index#fix-404-after-cached-dynamic-plugins-config-change_configuring-and-operating)

PostgreSQL 17 support With this update, Red Hat Developer Hub now validates its compatibility with PostgreSQL 17 as an external database on all supported cloud providers (AWS RDS, Azure Database for PostgreSQL, GCP Cloud SQL). This ensures users can confidently deploy and upgrade to PostgreSQL 17, maintaining parity with cloud provider database offerings. The testing matrixes have been updated to account for PostgreSQL versions 14-17 across all supported cloud platforms, providing a seamless experience for users across different platforms.

Automated Software Template lifecycle management

With this update, you can automatically synchronize security updates and configuration changes from Software Templates to downstream repositories. As a result, applications remain compliant and consistent with their source templates without the need for manual file comparisons.

Additional resources

- [Automated Software Template lifecycle management](https://docs.redhat.com/en/documentation/red_hat_developer_hub/1.9/html-single/customizing_red_hat_developer_hub/index#automated-software-template-lifecycle-management)

Red Hat Argo CD plugin renamed to Argo CD The Red Hat Argo CD plugin has been renamed to Argo CD. This change includes updates to NPM package names, removing the`redhat` prefix. As a result, you must use the plugin’s new`oci://ghcr.io` reference without the`redhat` prefix in your`dynamic-plugins` configuration file.

## Chapter 2. Technology Preview featuresCopy link

This section lists Technology Preview features in Red Hat Developer Hub 1.9.

Important

Technology Preview features provide early access to upcoming product innovations, enabling you to test functionality and provide feedback during the development process. However, these features are not fully supported under Red Hat Subscription Level Agreements, may not be functionally complete, and are not intended for production use. As Red Hat considers making future iterations of Technology Preview features generally available, we will attempt to resolve any issues that customers experience when using these features. See: [Technology Preview support scope](https://access.redhat.com/support/offerings/techpreview/).

OCI artifacts for dynamic plugins (Technology Preview)

With this update, you can switch from using the bundled plugin wrapper to the OCI artifact.

Important

Do not enable both the wrapper and the OCI artifact simultaneously. If you enable both simultaneously, the system crashes.

## Chapter 3. Developer Preview featuresCopy link

This section lists Developer Preview features in Red Hat Developer Hub 1.9.

Important

Developer Preview features are not supported by Red Hat in any way and are not functionally complete or production-ready. Do not use Developer Preview features for production or business-critical workloads. Developer Preview features provide early access to functionality in advance of possible inclusion in a Red Hat product offering. Customers can use these features to test functionality and provide feedback during the development process. Developer Preview features might not have any documentation, are subject to change or removal at any time, and have received limited testing. Red Hat might provide ways to submit feedback on Developer Preview features without an associated SLA.

For more information about the support scope of Red Hat Developer Preview features, see [Developer Preview Support Scope](https://access.redhat.com/support/offerings/devpreview/).

OpenShift AI Connector annotation values updated (Developer Preview) With this update, the OpenShift AI Connector for Red Hat Developer Hub correctly imports annotation values for AI model-related entities. Previously, the`rh.modelcatalog.io/model-name` annotation on resources of type`ai-model` did not match the model name in the model server. This update ensures proper integration between the imported entities and example templates that illustrate how to use the imported entities.

Catalog entity registration and unregistration MCP tools are available (Developer Preview) This update introduces additional Model Context Protocol (MCP) capabilities for Developer Lightspeed. These tools expand the ability of AI clients to interact with your software catalog and templates. You can use AI clients to register software catalog entities by location URL, unregister entities by location URL and UID, and query software template metadata tool with flexible filtering options. Furthermore, you can track your MCP tool executions directly in Developer Lightspeed using the MCP Tool.

Quay Backend Plugin now available in Extensions Marketplace (Developer Preview) Before this update, Red Hat Developer Hub included only a Quay frontend plugin. While the Quay frontend plugin remains functional on its own, use it together with Quay backend plugin to ensure Quay permissions are set up correctly. With this update, Red Hat Developer Hub provides the Quay backend plugin in the Extensions Marketplace.

Red Hat Developer Hub must-gather tool (Developer Preview)

This release introduces a dedicated Red Hat Developer Hub RHDH must-gather tool as a Developer Preview feature to streamline the diagnostic process of RHDH installations. This tool automatically collects essential versions, logs, and configurations across all installation methods and platforms supported by RHDH. Attaching the output of the RHDH must-gather when opening a support case can accelerate troubleshooting by providing Red Hat support and engineering teams with comprehensive technical insights into your RHDH instances.

More details in this [Support article](https://access.redhat.com/solutions/7133774) and the [README](https://github.com/redhat-developer/rhdh-must-gather/blob/main/README.md).

Support for multiple identity providers on the login screen (Developer Preview) With this release, you can configure multiple authentication providers. For configuration instructions and essential security considerations, see [Configuring multiple authentication providers](https://developers.redhat.com/articles/2026/02/11/guide-configuring-multiple-authentication-providers-developer-hub).

Dynamic plugin management is available by default in Red Hat Developer Hub Local (Developer Preview) This update provides dynamic plugin management by default within the Extensions catalog for Red Hat Developer Hub Local. This feature provides a streamlined, out-of-the-box experience for evaluating and customizing the platform. As a result, you can install, edit, and manage plugins directly from the user interface instead of manually editing configuration files.

Quay plugin provides multi-instance capability (Developer Preview) With this update, you can connect to multiple Quay instances, including both self-hosted and managed deployments. As a result, you can integrate with various Quay environments by defining the instances in your configuration file and adding the`quay.io/instance-name` annotation to specify which instance an entity uses. If you omit this annotation, the plugin defaults to the first instance defined in the configuration.

Argo CD plugin with multi-cluster support (Developer Preview) This update introduces a multi-cluster support for the Argo CD plugin. Administrators can discover applications across multiple Argo CD instances and namespaces per catalog entity, enabling you to visualize deployments across different environments, such as development, staging, and production. You can specify multiple instances in the`argocd/instance-name` annotation. If you omit this annotation, the plugin searches all Argo CD instances, matching the behavior of the Roadie Argo CD plugin. Additionally, you can filter applications across instances by using the`argocd/app-selector` annotation.

## Chapter 4. Deprecated featuresCopy link

This section lists deprecated functionalities in Red Hat Developer Hub 1.9.

Helm chart template helper is deprecated The Helm chart template helpers are renamed from`janus-idp.` to`rhdh.` to align with the Red Hat Developer Hub branding. If your custom`values` files reference`janus-idp.hostname`,`janus-idp.backend-secret-name`, or`janus-idp.postgresql.secretName`, update them to use`rhdh.hostname`,`rhdh.backend-secret-name`, and`rhdh.postgresql.secretName` respectively. The old template names remain are still accepted but will be removed in a future release. We recommend migrating to the new names during this grace period to avoid issues when the deprecated templates are removed.

Global Floating Action Button plugin is deprecated The Global Floating Action Button plugin (`@red-hat-developer-hub/backstage-plugin-global-floating-action-button`) is deprecated and might be removed in a future release. You can use the Quick links feature in the global header instead. To avoid future errors, remove or disable the plugin package and its associated configuration from your`dynamic-plugins.yaml` and`app-config.yaml` files. For more information about configuring the alternative feature, see [Enabling Quicklinks and Starred Items after an upgrade](https://docs.redhat.com/en/documentation/red_hat_developer_hub/1.9/html-single/customizing_red_hat_developer_hub/indexl#enabling-quicklinks-starred-items-upgrade_configuring-the-global-header-in-rhdh).

Support status updated for Azure and ServiceNow Scaffolder modules

With this update, the support status for the following plugins is changed from Technology Preview to Community Support:

- Azure Scaffolder backend module (`backstage-plugin-scaffolder-backend-module-azure`)
- ServiceNow Scaffolder backend module (`backstage-community-plugin-servicenow`)

Plugin distribution and support status

Red Hat Developer Hub 1.9 has begun a transition of the plugin ecosystem structure. Review the following changes carefully, because they impact both installation procedures and support definitions.

Plugin distribution for Community Supported plugins moves to OCI images

To provide a more modular and cloud-native experience, community-supported plugins are no longer "wrapped" and bundled inside the RHDH application container image. Instead, plugins are now packaged as Open Container Initiative (OCI) compliant images. You can download them from [ghcr.io](https://github.com/orgs/redhat-developer/packages?repo_name=rhdh-plugin-export-overlays).

Impact This results in a lighter core application. Action required Migrate the configurations of your existing plugins as displayed in the [Community plugins migration table](https://docs.redhat.com/en/documentation/red_hat_developer_hub/1.9/html-single/dynamic_plugins_reference/index#community-plugins-migration-table) in the Dynamic Plugins Reference guide. Air-gapped users If you operate in a disconnected environment, you must update your mirroring scripts to include the specific OCI images for the plugins you use. You can also use our [utility script](https://docs.redhat.com/en/documentation/red_hat_developer_hub/1.9/html-single/installing_and_viewing_plugins_in_red_hat_developer_hub/index#assembly-mirroring-dynamic-plugins-in-disconnected-environments_rhdh-installing-rhdh-plugins) to mirror these OCI images. The core product image no longer contains these extensions.

For details about the community-supported plugins that have been migrated from bundled wrappers to [ghcr.io](https://github.com/orgs/redhat-developer/packages?repo_name=rhdh-plugin-export-overlays), see the [Community plugins migration to the GitHub Container Registry](https://docs.redhat.com/en/documentation/red_hat_developer_hub/1.9/html-single/dynamic_plugins_reference/index#ref-community-plugins-migration_title-plugins-rhdh-about) section of the Dynamic Plugins Reference guide.

Some plugins that will transition to`ghcr.io` in a future release remain bundled in 1.9. For more details, see the [Plugins remaining bundled in RHDH 1.9 table](https://docs.redhat.com/en/documentation/red_hat_developer_hub/1.9/html-single/dynamic_plugins_reference/index#plugins-remaining-bundled-in-rhdh-1-9) in the Dynamic Plugins Reference guide.

Tech Preview and GA plugins will continue to be bundled with the RHDH application container. OCI artifacts for these plugins will be offered with unofficial support in [Red Hat Ecosystem Catalog](http://registry.access.redhat.com/).

Clarification of plugin support levels

Previously, Red Hat’s secure build systems required certain open-source plugins to be wrapped for distribution which resulted in robust community plugins labeled as Technology Preview or Generally Available (GA).

With the move to OCI-based distribution, this wrapping requirement is being removed for many plugins. Consequently, we are updating the support labels for several plugins, in many cases to Community Supported.

What this means This is a terminology correction to accurately reflect that the code is maintained in the open-source community. It does not represent a degradation in functionality or availability. It signifies that NPM packages can now be published as OCI artifacts that contain the original upstream package, with labelling that is reflective of the plugin’s lifecycle status outside the context of the RHDH container.

Additional resources

- [Plugins whose support level has changed between RHDH 1.8 and RHDH 1.9](https://github.com/redhat-developer/rhdh-plugin-export-overlays/blob/d2a2af2590753a4853ced56d58e6aaf2d5e4d99f/docs/rhdh1.8_vs_1.9.adoc)

## Chapter 5. Removed featuresCopy link

This section lists breaking changes in Red Hat Developer Hub 1.9.

Existing Operator users with Orchestrator 1.7 or 1.8 require a manual update in their`dynamic-plugins` ConfigMap

If you have an existing Operator-backed instance of Developer Hub with the Orchestrator, you must update your`dynamic-plugins` ConfigMap once the Developer Hub Operator is upgraded to 1.9. Otherwise, the Developer Hub instance will not be upgraded at all.

Example of a dynamic-plugins ConfigMap enabling the Orchestrator plugins in 1.9 for Operator-backed instances

```yaml
apiVersion: v1
kind: ConfigMap
metadata:
  name: dynamic-plugins-rhdh
data:
  dynamic-plugins.yaml: |
    includes:
      - dynamic-plugins.default.yaml
    plugins:
      - package: 'oci://registry.access.redhat.com/rhdh/red-hat-developer-hub-backstage-plugin-orchestrator:{{inherit}}'
        disabled: false
      - package: 'oci://registry.access.redhat.com/rhdh/red-hat-developer-hub-backstage-plugin-orchestrator-backend:{{inherit}}'
        disabled: false
        dependencies:
          - ref: sonataflow
      - package: 'oci://registry.access.redhat.com/rhdh/red-hat-developer-hub-backstage-plugin-scaffolder-backend-module-orchestrator:{{inherit}}'
        disabled: false
      - package: 'oci://registry.access.redhat.com/rhdh/red-hat-developer-hub-backstage-plugin-orchestrator-form-widgets:{{inherit}}'
        disabled: false
```

Helm upgrade might require manual update of custom values files if overriding specific list fields

When upgrading the Red Hat Developer Hub (RHDH) Helm release to 1.9, users who use custom`values.yaml` files to override specific lists in the default configuration will need to manually update their files to include some new defaults.

This requirement is due to a known Helm limitation where the tool does not perform a "deep merge" on list fields. Instead, if a list is defined in a custom values file, it completely replaces the corresponding list in the default values. If you have custom overrides for the fields listed below, they will inadvertently strip out the new mandatory configurations required for the Extensions Catalog Index feature introduced in 1.9.

Affected fields:

- `upstream.backstage.extraVolumeMounts`
- `upstream.backstage.extraVolumes`
- `upstream.backstage.initContainers`

Failure to include the new default volume mounts and environment variables in your custom`values.yaml` will result in the RHDH application failing to initialize or failing to discover dynamic plugins correctly.

If you override any of the affected fields, you must manually merge the 1.9 default items into your custom configuration. You can view the full list of default values for the current version by running the following command:

```bash
helm show values redhat-developer-hub --repo https://charts.openshift.io --version 1.9.2
```

Make sure your custom`values.yaml` includes the following highlighted items within their respective lists to avoid configuration loss:

```yaml
upstream:
  backstage:
    extraVolumeMounts:
      # TODO: In addition to your custom mounts and the RHDH defaults, ensure this item is present (defined in the default RHDH chart):
      - name: extensions-catalog
        mountPath: /extensions

    extraVolumes:
      # TODO: In addition to your custom volumes and the RHDH defaults, ensure this item is present (defined in the default RHDH chart):
      - name: extensions-catalog
        emptyDir: {}

    initContainers:
      # TODO: Ensure the 'install-dynamic-plugins' container includes these environment variables and volume mounts:
      - name: install-dynamic-plugins
        env:
          # TODO: In addition to your custom env vars and the RHDH defaults for the install-dynamic-plugins init container,
          # ensure the following items are present (defined in the default RHDH chart):
          - name: CATALOG_INDEX_IMAGE
            value: '{{ .Values.global.catalogIndex.image.registry }}/{{ .Values.global.catalogIndex.image.repository }}:{{ .Values.global.catalogIndex.image.tag }}'
          - name: CATALOG_ENTITIES_EXTRACT_DIR
            value: '/extensions'
        volumeMounts:
          # TODO: In addition to your custom volume mounts and the RHDH defaults for the install-dynamic-plugins init container,
          # ensure the following item is present (defined in the default RHDH chart):
          - name: extensions-catalog
            mountPath: /extensions
        # ... other fields omitted for brevity
```

Azure DevOps Scaffolder module replaces Azure Repositories module

The`@parfuemerie-douglas/scaffolder-backend-module-azure-repositories` is incompatible with Backstage 1.45 and is no longer maintained.

With this update, the`@backstage-community/plugin-scaffolder-backend-module-azure-devops` module is available as a replacement. To use this module, switch to this plugin from the list of available OCI artifacts at the [rhdh-plugin-export-overlays repository](https://github.com/redhat-developer/rhdh-plugin-export-overlays/pkgs/container/rhdh-plugin-export-overlays%2Fbackstage-community-plugin-scaffolder-backend-module-azure-devops).

Bundled community-supported plugins are removed

Before this update, community-supported plugins were bundled as wrappers in the Red Hat Developer Hub container image and referenced using local paths.

With this update, these plugins are removed from the container image and are published exclusively to the GitHub Container Registry (`ghcr.io`). As a result, if you use these plugins, you must update your dynamic plugin configuration (such as`dynamic-plugins.default.yaml`) to reference the new`oci:// paths`.

Additional resources

- [Community plugins migration to the GitHub Container Registry](https://docs.redhat.com/en/documentation/red_hat_developer_hub/1.9/html-single/dynamic_plugins_reference/index#ref-community-plugins-migration_title-plugins-rhdh-about).

CRD v1alpha1 and v1alpha2 versions have been removed With this update, the deprecated CRD v1alpha1 and v1alpha2 versions have been removed.

Change of support level for selected plugins

With this release, the following plugin has moved from Technology Preview to Community support.

- `roadiehq-scaffolder-backend-argocd`

You can continue using the plugin as a legacy embedded wrapper until you migrate to an`oci://` reference in your configuration.

The following plugins have moved from GA to Community support.

- `backstage-community-plugin-quay`
- `backstage-community-plugin-scaffolder-backend-module-quay-dynamic`
- `backstage-community-plugin-tekton`
- `roadiehq-backstage-plugin-argo-cd-backend`

You can continue using them as legacy embedded wrappers until you migrate to an`oci://` reference in your configuration.

For more information, see [Plugins remaining bundled in RHDH 1.9](https://docs.redhat.com/en/documentation/red_hat_developer_hub/1.9/html-single/dynamic_plugins_reference/index#plugins-remaining-bundled-in-rhdh-1-9)

Marketplace plugins are renamed to Extensions

Marketplace plugins are officially renamed to Extensions. Accordingly, the package registry names are changed from`@red-hat-developer-hub/backstage-plugin-marketplace` to`@red-hat-developer-hub/backstage-plugin-extensions` to ensure a unified naming convention.

Important

You must search your configuration files for any`backstage-plugin-marketplace` references and update them to use the new`backstage-plugin-extensions` naming convention to ensure a smooth transition.

Red Hat backend plugin for Argo CD replaces RoadieHQ plugin

Before this update, Red Hat Developer Hub included a RoadieHQ backend plugin for Argo CD support. With this update, Red Hat Developer Hub provides the Red Hat backend plugin for Argo CD in the Extensions Marketplace. While the RoadieHQ plugin remains available, use the Red Hat backend plugin.

Note

Argo CD backend plugin is incompatible with RoadieHQ Scaffolder Argo CD Actions. If you want to use these actions, enable Argo CD frontend plugin together with the RoadieHQ ArgoCD backend plugin. In that case, permissions are not displayed in the RBAC UI. As a workaround, apply these permissions by using a CSV file or directly calling the REST API of the RBAC backend plugin.

## Chapter 6. Known issuesCopy link

This section lists known issues in Red Hat Developer Hub 1.9.

Missing documentation links in Developer Lightspeed for RHDH responses

Developer Lightspeed for RHDH does not display the RAG Sources section at the end of a response. Although the tool generates an answer based on Red Hat Developer Hub documentation, the links to the specific source materials are unavailable.

There is no workaround for this issue. This behavior will be resolved in a future update.

[RHDHBUGS-2746](https://redhat.atlassian.net/browse/RHDHBUGS-2746)

Operator-backed RHDH instances might fail to start when using a mirrored plugin catalog index image

Using the`mirror-plugins.sh` script to mirror the plugin catalog index image in a disconnected environment might cause Operator-backed Red Hat Developer Hub (RHDH) instances to fail to start. Running the script rebuilds the catalog index image after replacing all plugin OCI references. If you subsequently use that mirrored catalog index image in your Backstage custom resource (CR), the init container fails to start and logs an error similar to the following:

```plaintext
InstallException: Cannot use {{inherit}} for oci://registry.access.redhat.com/rhdh/red-hat-developer-hub-backstage-plugin-orchestrator: no existing plugin configuration found. Ensure a plugin from this image is defined in an included file with an explicit version.
```

This occurs because the`install-dynamic-plugins` process resolves OCI references and attempts to merge plugin configurations even for plugins that are disabled by default in the Operator’s configuration. This issue specifically affects Operator-backed instances of RHDH.

As a workaround, manually empty the default`plugins` list in the Operator’s default`ConfigMap`.

Note

These manual changes are reverted when the Operator is upgraded.

Modify the`plugins` field so that it is an empty list (`plugins: []`), as shown in the example below:

```yaml
# -- Truncated --
  dynamic-plugins.yaml: |
    apiVersion: v1
    kind: ConfigMap
    metadata:
      name: default-dynamic-plugins
    data:
      dynamic-plugins.yaml: |
        includes:
          - dynamic-plugins.default.yaml
        plugins: []
# -- Truncated --
```

1. Edit the`rhdh-default-config` ConfigMap located in the`rhdh-operator` namespace.
2. Locate the`dynamic-plugins.yaml` key within the ConfigMap data.
3. Save the ConfigMap and wait a few seconds for the changes to automatically propagate to the Operator pod.

[RHDHBUGS-2738](https://redhat.atlassian.net/browse/RHDHBUGS-2738)

Orchestrator plugin fails to access the`LokiStack` on Openshift logging

When you configure the`workflowProviders` section in the Orchestrator plugin to access the`LokiStack` on OpenShift logging by using both the Loki`baseUrl` and an authorization token, the backend plugin does not pass the token to the public route. As a result, the plugin UI displays a failed to Fetch message.

Configuration example

```yaml
orchestrator:
  workflowLogProvider:
    loki:
      baseUrl: <link_to_openshift_logging_loki_public_route>
      token: <auth_token>
```

[RHDHBUGS-2735](https://redhat.atlassian.net/browse/RHDHBUGS-2735)

Switching`spec.deployment.kind` from`Deployment` to`StatefulSet`(or vice-versa) requires manual deletion of the previous RHDH Deployment (or`StatefulSet`) resource from the cluster

In this release, a new`spec.deployment.kind` field was introduced in the Backstage v1alpha5 CRD so that users can specify the RHDH workload type:`Deployment`(default) or`StatefulSet`(new). However, user action of changing the deployment kind from Deployment to StatefulSet in the Backstage Custom Resource results in orphaned Deployment resources. As a consequence, previous Deployment resources remain, causing traffic to be potentially sent to old pods, leading to user confusion. To work around this problem, manually delete the previous Deployment resource from the cluster after your updated CR is reconciled by the Operator, as shown:

```shell-session
$ oc delete deployment -l app.kubernetes.io/instance=<CR_name>
```

[RHDHBUGS-2734](https://redhat.atlassian.net/browse/RHDHBUGS-2734)

Lightspeed chatbot overlay opens underneath the Quick Start panel

When the Quick Start panel is open, the Lightspeed chatbot opens underneath the drawer panel in the overlay display mode. As a consequence, the chatbot interface is obscured.

[RHDHBUGS-2701](https://redhat.atlassian.net/browse/RHDHBUGS-2701)

RHDH backend fails to start with outdated scaffolder plugins

Scaffolder backend modules that access the scaffolder extension point from the`alpha` export are incompatible with`scaffolder-node` package version 0.12.0. As a consequence, the RHDH backend fails to start, and the log file contains the following error:

```plaintext
{code}
Plugin 'scaffolder' threw an error during startup, waiting for 14 other plugins to finish before shutting down the process.
TypeError: Cannot read properties of undefined (reading 'id')
    at BackendInitializer.cjs.js:262:72
{code}
```

To work around this problem, ensure that all scaffolder modules support Backstage 1.45.x and that scaffolder modules are updated to scaffolder-node version 0.12.0 or later.

[RHDHBUGS-2497](https://redhat.atlassian.net/browse/RHDHBUGS-2497)

ArgoCD deployment fails due to PostgreSQL serviceBindings incompatibility

The orchestrator chart uses the PostgreSQL chart’s`serviceBindings` functionality to share database credentials. The PostgreSQL chart implements this feature using Helm`lookup()` function, which is not supported by ArgoCD server-side rendering. This prevents successful deployment when using ArgoCD.

As a consequence, ArgoCD deployments fail with password authentication errors, database creation jobs fail silently, and SonataFlow services cannot connect to PostgreSQL.

As a workaround to deploy with ArgoCD, complete the following steps:

Disable`serviceBindings` in your configuration:

```yaml
  upstream:
    postgresql:
      serviceBindings:
        enabled: false
```

Create a pre-existing secret with the name` -postgresql-svcbind-postgres` containing the following content:

```yaml
  apiVersion: v1
  kind: Secret
  metadata:
    name: <release-name>-postgresql-svcbind-postgres
  type: Opaque
  data:
    username: <base64_encoded_username>      # e.g., "postgres"
    password: <base64_encoded_password>
    postgres-password: <base64_encoded_password>  # Same as password
```

Configure the chart to use the existing secret:

```yaml
  upstream:
    postgresql:
      auth:
        existingSecret: <release-name>-postgresql-svcbind-postgres
        secretKeys:
          adminPasswordKey: password
```

[RHDHBUGS-2438](https://redhat.atlassian.net/browse/RHDHBUGS-2438)

## Chapter 7. Fixed issuesCopy link

This section lists issues fixed in Red Hat Developer Hub 1.9.

For fixed issues in Red Hat Developer Hub 1.9.1, see [Red Hat Bug Advisory RHBA-2026:4397](https://access.redhat.com/errata/RHBA-2026:4397).

For fixed issues in Red Hat Developer Hub 1.9.2, see [Red Hat Bug Advisory RHBA-2026:5433](https://access.redhat.com/errata/RHBA-2026:5433).

### 7.1. Fixed issues in 1.9.1Copy link

Pulling images no longer requires an authentication secret by default

In Red Hat Developer Hub 1.9.0, the default image registry was incorrectly set to`registry.redhat.io`(the Red Hat Ecosystem Catalog). This registry requires an authentication secret to be configured in the cluster, which often led to image pull errors during a standard installation unless you applied a manual workaround.

In 1.9.2, this has been resolved by switching the default registry in the Helm chart and Operator bundle to`registry.access.redhat.com`. This is an unauthenticated registry, enabling installation without requiring pre-configured credentials.

[RHDHBUGS-2767](https://redhat.atlassian.net/browse/RHDHBUGS-2767)

Catalog index signature errors no longer prevent Red Hat Developer Hub initialization

Before this update, Red Hat Developer Hub (RHDH) failed to initialize even if you configured it to consume the catalog index image from the unauthenticated`registry.access.redhat.com` registry. The RHDH init container would fail with a`Source image rejected: None of the signatures were accepted` error message.

With this update, RHDH initializes successfully.

[RHDHBUGS-2799](https://redhat.atlassian.net/browse/RHDHBUGS-2799)

### 7.2. Fixed issues in 1.9.0Copy link

OAuth authentication cookies are configured as host-only

Before this update, when Developer Hub was deployed on different subdomains (for example,`example.foo.com` and`staging.example.foo.com`), the application set cookies for both the parent domain and the subdomain. As a consequence, refreshing the browser on a subdomain caused the session re-establishment to use the parent domain cookie, which caused the refresh API request to fail.

With this update, OAuth authentication cookies are configured as host-only. As a result, cookies are not shared across subdomains, and users can switch between RHDH instances hosted on different subdomains without manually clearing their browser cookies.

[RHDHBUGS-945](https://redhat.atlassian.net/browse/RHDHBUGS-945)

Hide package for entities missing`dynamicArtifact` value in code editor

Before this update, Red Hat Developer Hub displayed`- package: ./dynamic-plugins/dis/…​.` for packages with missing a`spec.dynamicArtifact` value in their catalog entity. With this update, Developer Hub hides package for entities missing a`dynamicArtifact` value.

[RHDHBUGS-2058](https://redhat.atlassian.net/browse/RHDHBUGS-2058)

Error message displayed when manually accessing plugins without an associated entity YAML

Before this update, manually modifying a plugin or package name in the URL to access an item without an associated entity YAML file caused an error. As a consequence, the application did not indicate why access was denied. With this update, the application displays a clear error message indicating why access to the particular plugin is not allowed.

[RHDHBUGS-2059](https://redhat.atlassian.net/browse/RHDHBUGS-2059)

API failures handled gracefully on the Installed Packages page

Before this update, when installation was disabled or the YAML file was misconfigured, actions on the Installed Packages page were still shown. Triggering these actions caused API calls to fail with an error that broke the UI. With this update, the API failure is handled gracefully. As a result, the correct reason for the failure is displayed and the UI does not break.

[RHDHBUGS-2126](https://redhat.atlassian.net/browse/RHDHBUGS-2126)

Bulk-import scaffolder task parses import locations correctly

Before this update, the bulk-import scaffolder task failed to parse the import location causing errors to appear in the bulk-import log. With this update, the parsing logic is corrected. As a result, the task parses the import location correctly.

[RHDHBUGS-2150](https://redhat.atlassian.net/browse/RHDHBUGS-2150)

Support for conditional permissions in Scorecard plugin

Before this update, Scorecard conditional permissions did not function correctly. With this update, you can restrict access to specific metrics by their identifiers using conditional policies. For more information, see [Authenticating and managing Scorecard plugins](https://docs.redhat.com/en/documentation/red_hat_developer_hub/1.9/html-single/evaluate_project_health_using_scorecards/index#proc-authenticating-and-managing-scorecard).

[RHDHBUGS-2186](https://redhat.atlassian.net/browse/RHDHBUGS-2186)

Extension filter dropdowns update dynamically based on search results

Before this update, when searching in the Extensions page, the Category, Author, and Support Type filter dropdowns displayed all available options with their original counts. The filter options did not accurately reflect the items available in the search results. With this update, the filter logic is corrected. As a result, the available options and counts in the filter dropdowns dynamically update to match the current search results.

[RHDHBUGS-2191](https://redhat.atlassian.net/browse/RHDHBUGS-2191)

Scorecard permissions appear in RBAC UI by default

Before this update, Scorecard permissions were not automatically registered in the RBAC UI. You had to manually add permissions to the`app-config.yaml` file. With this update, the permissions are registered automatically. As a result, Scorecard permissions appear in the RBAC UI by default.

[RHDHBUGS-2202](https://redhat.atlassian.net/browse/RHDHBUGS-2202)

Scorecard plugin configuration schema validation errors fixed

Before this update, the`package.json` file for the Scorecard plugin was missing the`config.d.ts` export causing configuration schema validation errors to occur during plugin integration, displaying a missing Config export message. With this update, the`config.d.ts` file is added to the`package.json` exports to resolve the configuration schema validation errors.

[RHDHBUGS-2228](https://redhat.atlassian.net/browse/RHDHBUGS-2228)

Deployment strategy can be changed to Recreate

Before this update, the Operator defaulted to the`rollingUpdate` deployment strategy and prevented users from switching to the Recreate strategy. As a consequence, attempts to patch the deployment definition failed with validation errors. With this update, the Operator allows patching the deployment strategy. As a result, you can change the deployment update strategy from`rollingUpdate` to Recreate.

[RHDHBUGS-2278](https://redhat.atlassian.net/browse/RHDHBUGS-2278)

Updated plugin installation steps for environments using corporate CA bundles

Before this update, installing customized widgets packaged as OCI containers failed in environments using a corporate certificate authority (CA). Certificate validation errors occurred because the corporate CA was not included in the default CA bundle. With this update, instructions for installing plugins from OCI registries by using custom certificates are added to the documentation. For more information, see [Installing plugins from OCI registries by using custom certificates](https://docs.redhat.com/en/documentation/red_hat_developer_hub/1.9/html-single/installing_and_viewing_plugins_in_red_hat_developer_hub/index#rinstall-plugins-from-oci-registries-by-using-custom-certificates).

[RHDHBUGS-2299](https://redhat.atlassian.net/browse/RHDHBUGS-2299)

TechDocs and Kubernetes tabs render correctly in Software Catalog

Before this update, when the Quick Start side panel was open, the Kubernetes and TechDocs tabs in the Software Catalog did not render correctly. With this update, the layout logic is fixed. As a result, these tabs display correctly when the Quick Start side panel is open. If you are using 1.7.0 and 1.8.0, simply close the Quick Start as a workaround.

[RHDHBUGS-2319](https://redhat.atlassian.net/browse/RHDHBUGS-2319)

Base64 images supported in iconUrl for Quick Start and Homepage plugins

Before this update, the iconUrl parameter did not render inline base64 images in the Quick Start and Homepage plugins causing icons defined with base64 strings not to appear. With this update, you can use base64 encoded images as an icon source in the Quick Start and Homepage plugins.

[RHDHBUGS-2397](https://redhat.atlassian.net/browse/RHDHBUGS-2397)

Skopeo errors during OCI plugin installation are logged

Before this update, the`install-dynamic-plugins` init container did not display error messages from the skopeo copy command when OCI plugin installation failed. As a consequence, only the exit code was shown, which made troubleshooting difficult. With this update, the error output from skopeo (`stderr`/`stdout`) is captured and displayed in the logs. As a result, you can identify problems, such as registry connectivity failures or authentication issues, without manually accessing the container.

[RHDHBUGS-2439](https://redhat.atlassian.net/browse/RHDHBUGS-2439)

Form widgets support suppressing errors for unready dependencies

Before this update, widgets with`fetch:retrigger` dependencies failed their initial fetch if dependent fields were empty. As a consequence, HTTP errors (`404` or`400`) were displayed to the user during the initial load. With this update, the`fetch:error:ignoreUnready` property is supported. As a result, you can suppress error displays until dependencies have values. Additionally, you can use`fetch:response:default` to provide immediate fallback defaults.

[RHDHBUGS-2453](https://redhat.atlassian.net/browse/RHDHBUGS-2453)

Widgets support static fallback defaults

Before this update, widgets required a successful fetch call to set a default value using`fetch:response:value`. With this update, the`fetch:response:default` option is supported. As a result, you can define a static fallback default that pre-populates the field immediately on widget mount.

[RHDHBUGS-2454](https://redhat.atlassian.net/browse/RHDHBUGS-2454)

Plugins using the alpha language API render correctly

Before this update, an incorrect webpack configuration in the Red Hat Developer Hub frontend caused plugins using the alpha language API from the Backstage`core-plugin-api` package to fail. As a consequence, plugins such as the RBAC UI wrapper failed to load and displayed a`TypeError: Cannot read properties of undefined (reading 'id')` error. With this update, this was fixed by rebuilding the RHDH frontend with an updated configuration and required releasing an updated`janus-idp/cli`(3.7.0) to support building the RHDH frontend with this new configuration. These plugins, therefore, render correctly without requiring you to rebuild them.

[RHDHBUGS-2495](https://redhat.atlassian.net/browse/RHDHBUGS-2495)

Pressing Enter in`ActiveMultiSelect` adds values as chips

Before this update, pressing Enter in the`ActiveMultiSelect` field caused the form to advance to the next step. With this update, pressing Enter adds the typed value as a chip and keeps the user on the same step.

[RHDHBUGS-2506](https://redhat.atlassian.net/browse/RHDHBUGS-2506)

ActiveText widget displays loading indicator during updates

Before this update, the`ActiveText` widget provided no visual feedback while data was fetching or when`fetch:retrigger` dependencies changed. With this update, a loading indicator is displayed while dependencies resolve. As a result, you can clearly see when the widget is re-fetching data.

[RHDHBUGS-2507](https://redhat.atlassian.net/browse/RHDHBUGS-2507)

Form widgets support skipping initial fetch values

Before this update,`fetch:response:value` always overrode a widget default value, causing forms not to start with an empty field. With this update, the`fetch:skipInitialValue` property is supported, and`fetch:response:value` accepts empty strings from JSONata expressions. As a result, form widgets skip applying initial fetch defaults, enabling you to control when defaults appear.

[RHDHBUGS-2508](https://redhat.atlassian.net/browse/RHDHBUGS-2508)

Fetch error notifications can be suppressed

Before this update,`fetch:url` requests that returned`4xx` or`5xx` errors automatically displayed error notifications to the user. As a consequence, schema authors could not suppress these notifications to handle error states internally. With this update, you can set`fetch:error:silent: true` to suppress notifications and allow UI logic to handle error states without user popups.

[RHDHBUGS-2509](https://redhat.atlassian.net/browse/RHDHBUGS-2509)

Hidden fields are omitted from workflow payloads and viewable in review

Before this update, hidden fields were included in the workflow input payload, and users could not inspect them on the review page. With this update, fields marked`omitFromWorkflowInput` are no longer sent in execution payloads, and users can reveal hidden parameters on the review step using a toggle.

[RHDHBUGS-2510](https://redhat.atlassian.net/browse/RHDHBUGS-2510)

JSONata evaluation supported in fetch and validate bodies

Before this update,`fetch:body` and`validate:body` did not evaluate JSONata expressions. As a consequence, dynamic values were treated as literal strings and not resolved. With this update, JSONata expressions in these fields are evaluated before the request is sent. As a result, you can build request payloads dynamically from form data and responses.

[RHDHBUGS-2519](https://redhat.atlassian.net/browse/RHDHBUGS-2519)

ArgoCD DeploymentLifecycle displays invalid application information for apps with the same name

Before this update, when multiple applications shared the same name, the Argo CD DeploymentLifecycle page displayed an invalid active item. With this update, the ArgoCD DeploymentLifecycle page displays the correct active items.

[RHDHBUGS-2550](https://redhat.atlassian.net/browse/RHDHBUGS-2550)

Aggregated scorecard widgets display correctly on the homepage

Before this update, when you did not own any entities and were not part of a group that owned entities, or when entities existed but values or statuses could not be retrieved due to credential errors or threshold failures, the aggregated scorecard widgets were hidden. As a consequence, the widgets disappeared from the homepage. With this update the homepage displays the aggregated scorecard widgets.

[RHDHBUGS-2554](https://redhat.atlassian.net/browse/RHDHBUGS-2554)

Red Hat Developer Hub disable creating Orchestrator plugin dependencies

Before this update, it was not clear how to disable creating Orchestrator plugin dependencies (`SonataFlowPlatform` objects) by the Red Hat Developer Hub Operator. With this update, you can disable default plugin dependencies by declaring an empty array (`dependencies: []`) in your configuration.

[RHDHBUGS-2623](https://redhat.atlassian.net/browse/RHDHBUGS-2623)

## Chapter 8. Fixed security issuesCopy link

You can view the security issues fixed in Red Hat Developer Hub 1.9 at [Red Hat Security Updates](https://access.redhat.com/security/security-updates/cve?q=red+hat+developer+hub&amp;p=1&amp;sort=cve_publicDate+desc,allTitle+desc&amp;rows=10&amp;documentKind=Cve).

- For 1.9.0, see [Red Hat Security Advisory RHSA-2026:3751](https://access.redhat.com/errata/RHSA-2026:3751).

## Legal NoticeCopy link

Copyright © Red Hat.

Except as otherwise noted below, the text of and illustrations in this documentation are licensed by Red Hat under the Creative Commons Attribution–Share Alike 3.0 Unported license . If you distribute this document or an adaptation of it, you must provide the URL for the original version.

Red Hat, as the licensor of this document, waives the right to enforce, and agrees not to assert, Section 4d of CC-BY-SA to the fullest extent permitted by applicable law.

Red Hat, the Red Hat logo, JBoss, Hibernate, and RHCE are trademarks or registered trademarks of Red Hat, LLC. or its subsidiaries in the United States and other countries.

Linux® is the registered trademark of Linus Torvalds in the United States and other countries.

XFS is a trademark or registered trademark of Hewlett Packard Enterprise Development LP or its subsidiaries in the United States and other countries.

The OpenStack® Word Mark and OpenStack logo are trademarks or registered trademarks of the Linux Foundation, used under license.

All other trademarks are the property of their respective owners.

---

*Source: https://docs.redhat.com/en/documentation/red_hat_developer_hub/1.9/html-single/setting_up_and_configuring_your_first_red_hat_developer_hub_instance/index*

Setting up and configuring your first Red Hat Developer Hub instance | Red Hat Developer Hub | 1.9 | Red Hat Documentation

 

 

 

 

 

 

 

 

# Setting up and configuring your first Red Hat Developer Hub instance

---

Red Hat Developer Hub 1.9

## Prepare your IT infrastructure including Red Hat OpenShift Container Platform and required external components, and run your first Red Hat Developer Hub (RHDH) instance in production.

Red Hat Customer Content Services

Legal Notice

Abstract

Prepare your IT infrastructure including Red Hat OpenShift Container Platform and required external components, and run your first Red Hat Developer Hub (RHDH) instance in production.

---

## PrefaceCopy link

Prepare your IT infrastructure including Red Hat OpenShift Container Platform and required external components, and run your first Red Hat Developer Hub (RHDH) instance in production.

## Chapter 1. Checklist to run your first Red Hat Developer Hub (RHDH) instance in production.Copy link

With the default configuration, Developer Hub runs with a minimal feature set that does not require secure connection to external services such as an identity provider, a Git provider, and external PostgreSQL and Redis databases.

Using critical features therefore requires following additional configuration:

For resiliency

- Legal Notice
- Use an external PostgreSQL database.
- Enable high-availability.

For performance

- Enable assets caching to an external Redis database.

For security

- Use secure connections to your external services.
- Provision users and enable authentication.
- Enable role-based access control, and configure the permission policy by using the Web UI.

For adapting to your environment

- Enable GitHub repository discovery.
- Customize Developer Hub appearance with your logo.

## Chapter 2. Installing the Red Hat Developer Hub OperatorCopy link

As an administrator, you can install the Red Hat Developer Hub Operator. Authorized users can use the Operator to install Red Hat Developer Hub on Red Hat OpenShift Container Platform (OpenShift Container Platform) and supported Kubernetes platforms. For more information on supported platforms and versions, see the [Red Hat Developer Hub Life Cycle](https://access.redhat.com/support/policy/updates/developerhub) page.

Containers are available for the following CPU architectures:

- AMD64 and Intel 64 (`x86_64`)

Prerequisites

- You are logged in as an administrator on the OpenShift Container Platform web console.
- You have configured the appropriate roles and permissions within your project to create or access an application. For more information, see the [Red Hat OpenShift Container Platform documentation on Building applications](https://docs.redhat.com/en/documentation/openshift_container_platform/4.20/html-single/building_applications/index#building-applications-overview).
- You have installed Red Hat OpenShift Container Platform 4.16 to 4.20.
- Make sure that your system meets the minimum sizing requirements. See [Sizing requirements for Red Hat Developer Hub](https://docs.redhat.com/en/documentation/red_hat_developer_hub/1.9/html-single/about_red_hat_developer_hub/index#rhdh-sizing_about-rhdh).

Important

You can upgrade Red Hat Developer Hub directly from any previous version to the latest release without installing intermediate versions. However, you must review the release notes for every skipped version to identify breaking changes or required migration steps. For example, if upgrading from version 1.5 to 1.7, check the release notes for both 1.6 and 1.7.

Procedure

After the Operator is successfully installed, provision your custom configuration:

Before you create a Developer Hub instance, you must create the required config map and Secret resources in your project. These include the`baseUrl` and service-to-service authentication secrets.

For detailed steps, see [Provisioning your custom Red Hat Developer Hub configuration](https://docs.redhat.com/en/documentation/red_hat_developer_hub/1.9/html-single/configuring_red_hat_developer_hub/index#provisioning-and-using-your-custom-configuration).

From the Update channel drop-down menu, select the update channel that you want to use, for example, fast or fast-1.9.

Important

The `fast channel includes all of the updates available for a particular version. Any update might introduce unexpected changes in your Red Hat Developer Hub deployment. Check the release notes for details about any potentially breaking changes.

The fast-1.9 channel only provides z-stream updates, for example, updating from version 1.9.1 to 1.9.2. If you want to update the Red Hat Developer Hub y-version in the future, for example, updating from 1.9 to 1.10, you must switch to the fast-1.10 channel manually.

Select the Operator Installation mode.

Note

The All namespaces on the cluster (default) option is selected by default. The Specific namespace on the cluster option is not currently supported.

In the Installed Namespace field, do one of the following actions:

Select Select a Namespace to use an alternative namespace.

From the Select Project drop-down menu, do one of the following actions:

Select Create Project to create a new project for the Operator.

On the Create Project dialog, enter text into the required fields and click Create.

Important

For enhanced security, better control over the Operator lifecycle, and preventing potential privilege escalation, install the Red Hat Developer Hub Operator in a dedicated default`rhdh-operator` namespace. You can restrict other users' access to the Operator resources through role bindings or cluster role bindings.

You can also install the Operator in another namespace by creating the necessary resources, such as an Operator group. For more information, see [Installing global Operators in custom namespaces](https://docs.redhat.com/en/documentation/openshift_container_platform/4.20/html-single/operators/index#olm-installing-global-namespaces_olm-adding-operators-to-a-cluster).

However, if the Red Hat Developer Hub Operator shares a namespace with other Operators, then it shares the same update policy as well, preventing the customization of the update policy. For example, if one Operator is set to manual updates, the Red Hat Developer Hub Operator update policy is also set to manual. For more information, see [Colocation of Operators in a namespace](https://docs.redhat.com/en/documentation/openshift_container_platform/4.20/html-single/operators/index#olm-colocation-namespaces_olm-colocation).

Select the Update approval method for the Operator.

- If you select the Automatic option, the Operator is updated without requiring manual confirmation.
- If you select the Manual option, a notification opens when a new update is released in the update channel. The update must be manually approved by an administrator before installation can begin.

Click Install.

Note

If you selected a Manual approval strategy, the upgrade status of the subscription remains Upgrading until you review and approve the install plan. After you click Approve on the Install Plan page, the subscription upgrade status changes to Up to date.

If you selected an Automatic approval strategy, the upgrade status should resolve to Up to date without intervention.

Verification

From the dialog box, do one of the following actions:

Click View all installed operators to open the Installed Operators page.

- From the list of installed Operators, locate the Red Hat Developer Hub Operator name and details.
- Click Red Hat Developer Hub Operator to open the Operator details page for the Red Hat Developer Hub Operator.

## Chapter 3. Preparing your external servicesCopy link

Red Hat Developer Hub relies on external services. Prepare the following required external services:

PostgreSQL database Developer Hub stores data in a PostgreSQL database. Use an external database for resiliency and include it in your disaster recovery plan. Redis cache For efficiency, Developer Hub caches plugin and TechDocs assets when you provide a Redis cache server. GitHub API access Provide credentials to a GitHub app to enable access to the GitHub API for repository discovery. Connection to your identity provider Provide credentials to your identity provider to enable user provisioning and authentication.

Procedure

Get your external PostgreSQL database connection strings and certificates.

postgres-host Your PostgreSQL instance Domain Name System (DNS) or IP address. postgres-port Your PostgreSQL instance port number, such as 5432. postres-username The user name to connect to your PostgreSQL instance. postgres-password The password to connect to your PostgreSQL instance. postgres-ca.pem, postgres-key.key, postgres-crt.pem

For security, use TLS certificates to secure the connection to the database.

Create a GitHub App to allow Developer Hub to access the GitHub API for repository. Opt for a GitHub App instead of an OAuth app to use fine-grained permissions, gain more control over which repositories the application can access, and use short-lived tokens.

[Register a GitHub App](https://docs.github.com/en/apps/creating-github-apps/registering-a-github-app/registering-a-github-app) with the following configuration:

GitHub App name Enter a unique name identifying your GitHub App, such as`integrating-with-rhdh- `. Homepage URL Enter your Developer Hub URL:`https:// `. Authorization callback URL Enter your Developer Hub authentication backend URL:`https:// /api/auth/github/handler/frame`. Webhook Clear "Active", as this is not needed for authentication and catalog providers. App permissions Select permissions to define the level of access for the app. Adapt permissions to your needs:

Reading software components

Contents`Read-only` Commit statuses`Read-only`

Reading organization data

Members`Read-only`

Publishing software templates

Set permissions if you intend to use the same GitHub App for software templates.

Administration`Read & write`(for creating repositories) Contents`Read & write` Metadata`Read-only` Pull requests`Read & write` Issues`Read & write` Workflows`Read & write`(if templates include GitHub workflows) Variables`Read & write`(if templates include GitHub Action Repository Variables) Secrets`Read & write`(if templates include GitHub Action Repository Secrets) Environments

`Read & write`(if templates include GitHub Environments)

Organization permissions 

Members

`Read-only`

Where can this GitHub App be installed?

Select`Only on this account`.

1. In the General → Clients secrets section, click Generate a new client secret.
2. In the General → Private keys section, click Generate a private key.
3. In the Install App tab, choose an account to install your GitHub App on.
4. Save the following values for the next step:

## Chapter 4. Provisioning your custom Red Hat Developer Hub configurationCopy link

To configure Red Hat Developer Hub, provision your custom Red Hat Developer Hub config maps and secrets to {platform-long} before running Red Hat Developer Hub.

Tip

On Red Hat OpenShift Container Platform, you can skip this step to run Developer Hub with the default config map and secret. Your changes on this configuration might get reverted on Developer Hub restart.

Prerequisites

You have an external PostgreSQL database, with the following details. See See Chapter 3, Preparing your external services.

postgres-host Your PostgreSQL instance Domain Name System (DNS) or IP address. postgres-port Your PostgreSQL instance port number, such as 5432. postres-username The user name to connect to your PostgreSQL instance. postgres-password The password to connect to your PostgreSQL instance. postgres-ca.pem, postgres-key.key, postgres-crt.pem TLS certificates to secure the connection to the database.

You have a GitHub App enabling access to the GitHub API for repository discovery, with the following details. See Chapter 3, Preparing your external services.

GITHUB_INTEGRATION_APP_ID Your GitHub integration App ID. GITHUB_INTEGRATION_CLIENT_ID Your GitHub integration App client ID. GITHUB_INTEGRATION_CLIENT_SECRET Your GitHub integration App client secret. GITHUB_INTEGRATION_PRIVATE_KEY_FILE Your GitHub integration App private key.

- Private key
- By using the {platform-cli-link}, you have access, with developer permissions, to the OpenShift cluster aimed at containing your Developer Hub instance.
- You have the connection string to an active Redis server, such as`rediss://user:pass@cache.example.com:6379`. For security, consider using a`rediss` secure server connection. See Chapter 3, Preparing your external services.

Procedure

For security, store your secrets as environment variables values in an OpenShift Container Platform secret, rather than in clear text in your configuration files. Collect all your secrets in the`secrets.txt` file, with one secret per line in`KEY=value` form.

Enter your custom logo.

```plaintext
BASE64_EMBEDDED_FULL_LOGO="data:image/svg+xml;base64,<base64_full_logo_data>"
BASE64_EMBEDDED_ICON_LOGO="data:image/svg+xml;base64,<base64_icon_logo_data>"
```

`BASE64_EMBEDDED_FULL_LOGO`

Enter your logo for the expanded (pinned) sidebar as a base64 encoded SVG image.

To encode your logo in base64, run:

```plaintext
$ base64 -i logo.svg
```

`BASE64_EMBEDDED_ICON_LOGO` Enter your logo for the collapsed (unpinned) sidebar as a base64 encoded SVG image.

Enter the connection string to your Redis server that caches plugin assets.

```plaintext
REDIS_CONNECTION=rediss://user:pass@cache.example.com:6379
```

Enter your GitHub integration credentials:

```plaintext
GITHUB_INTEGRATION_APP_ID=_<Enter_the_saved_App_ID>
GITHUB_INTEGRATION_CLIENT_ID=<Enter_the_saved_Client_ID>
GITHUB_INTEGRATION_CLIENT_SECRET=<Enter_the_saved_Client_Secret>
GITHUB_INTEGRATION_HOST_DOMAIN=github.com
GITHUB_INTEGRATION_ORGANIZATION=<Enter_your_github_organization_name>
GITHUB_INTEGRATION_PRIVATE_KEY_FILE= <Enter_the_saved_Private_key>
```

Enter your PosgreSQL database secrets:

```plaintext
POSTGRES_PASSWORD: <postgres-password>
POSTGRES_PORT: "<postgres-port>"
POSTGRES_USER: <postgres-username>
POSTGRES_HOST: <postgres-host>
PGSSLMODE: verify-full
NODE_EXTRA_CA_CERTS: /opt/app-root/src/postgres-crt.pem
```

1. [Enter your authentication secrets](https://docs.redhat.com/en/documentation/red_hat_developer_hub/1.9/html-single/authentication_in_red_hat_developer_hub/index).

Author your custom`app-config.yaml` file. This is the main Developer Hub configuration file. You need a custom`app-config.yaml` file to avoid the Developer Hub installer to revert user edits during upgrades. When your custom`app-config.yaml` file is empty, Developer Hub is using default values.

For a production environment, start with the following setup:

`app-config.yaml`

```yaml
app:
  title: <Red Hat Developer Hub>
  branding:
    fullLogo: ${BASE64_EMBEDDED_FULL_LOGO}
    fullLogoWidth: 110px
    iconLogo: ${BASE64_EMBEDDED_ICON_LOGO}
backend:
  cache:
    store: redis
    connection: ${REDIS_CONNECTION}
techdocs:
  cache:
    ttl: 3600000
catalog:
  providers:
    <enter_your_provider_configuration>
integrations:
    <enter_your_integrations_configuration>
permission:
  enabled: true
  rbac:
    admin:
      users:
        - name: user:default/<your_policy_administrator_name>
    pluginsWithPermission:
      - catalog
      - scaffolder
      - permission
```

Most fields use environment variables that you defined in secrets in the previous step.

`app`

`title` Enter your Developer Hub instance display name, such as.`branding`

Set your custom logo.

Optionally, customize the width of the branding logo by changing value for the`fullLogoWidth` field. The following units are supported: integer, px, em, rem, percentage.

`backend`

`cache` Enable the plugins assets cache.

`techdocs`

`cache` Enable the TechDocs cache.

`catalog`

`provider` Enter your catalog provider configuration.

`integrations` Enter your repository discovery configuration.

`permissions` Enable Role-based access control. Enter your policy administrator name.

1. Additionally, [provision users and enable authentication with your external identity provider](https://docs.redhat.com/en/documentation/red_hat_developer_hub/1.9/html-single/authentication_in_red_hat_developer_hub/index).

Author your custom`dynamic-plugins.yaml` file to enable plugins. By default, Developer Hub enables a minimal plugin set, and disables plugins that require configuration or secrets, such as the GitHub repository discovery plugin and the Role-based access control (RBAC) plugin.

Enable the GitHub repository discovery and the RBAC features:

`dynamic.plugins.yaml`

```yaml
includes:
  - dynamic-plugins.default.yaml
plugins:
  - package: ./dynamic-plugins/dist/backstage-plugin-catalog-backend-module-github
    disabled: false
  - package: ./dynamic-plugins/dist/backstage-community-plugin-rbac
    disabled: false
```

Provision your custom configuration files to your {platform} cluster.

Create the {namespace} aimed at containing your Developer Hub instance.

```shell-session
$ oc create namespace my-rhdh-project
```

Provision your`app-config.yaml` and`dynamic-plugins.yaml` files respectively to the`my-rhdh-app-config`, and`dynamic-plugins-rhdh` config maps in the project.

```shell-session
$ oc create configmap my-rhdh-app-config --from-file=app-config.yaml --namespace=my-rhdh-project
$ oc create configmap dynamic-plugins-rhdh --from-file=dynamic-plugins.yaml --namespace=my-rhdh-project
```

Alternatively, [create the config maps by using the web console](https://docs.redhat.com/en/documentation/openshift_container_platform/4.20/html-single/nodes/index#nnodes-pods-configmap-create-from-console_configmaps).

Provision your`secrets.txt` file to the`my-rhdh-secrets` secret in the project.

```shell-session
$ oc create secret generic my-rhdh-secrets --from-file=secrets.txt --namespace=my-rhdh-project
```

Alternatively, [create the secret by using the web console](https://docs.redhat.com/en/documentation/openshift_container_platform/4.20/html-single/nodes/index#nodes-pods-secrets-creating-web-console-secrets_nodes-pods-secrets).

Provision your PosgreSQL TLS certificates to the`my-rhdh-database-secrets` secret in the project.

```shell-session
$ oc create secret generic my-rhdh-secrets --from-file=postgres-ca.pem --from-file=postgres-crt.pem --from-file=postgres-key.key --namespace=my-rhdh-project
```

## Chapter 5. Enable authentication in Red Hat Developer Hub (with mandatory steps only)Copy link

You can enable authentication in Red Hat Developer Hub to allow users to sign in to Red Hat Developer Hub using credentials from an external identity provider, such as RHBK, GitHub, or Microsoft Azure. This integration also allows you to provision user and group data from the identity provider to the Developer Hub software catalog, enabling features that rely on synchronized user and group data.

### 5.1. Understand authentication and user provisioningCopy link

Learn about the authentication process from creating user and group entities in the software catalog to user sign-in, and how authentication and catalog plugins enable each step. Understanding this process is essential for successfully configuring your Developer Hub instance, securing access through authorization, and enabling features that rely on synchronized user and group data.

To fully enable catalog features, provision user and group data from the Identity Provider to the Developer Hub software catalog. Catalog provider plugins handle this task asynchronously. These plugins query the Identity Provider (IdP) for relevant user and group information, and create or update corresponding entities in the Developer Hub catalog. Scheduled provisioning ensures that the catalog accurately reflects the users and groups in your organization.

When a user attempts to access Developer Hub, Developer Hub redirects them to a configured authentication provider, such as Red Hat Build of Keycloak (RHBK), GitHub, or Microsoft Azure. This external IdP is responsible for authenticating the user.

On successful authentication, the Developer Hub authentication plugin, configured in your`app-config.yaml` file, processes the response from the IdP, resolves the identity in the Developer Hub software catalog, and establishes a user session within Developer Hub.

Configuring authentication and user provisioning is critical for several reasons.

Provisioning user and group data to the catalog is necessary for various catalog features that rely on understanding entity ownership and relationships between users, groups, and software components.

Important

Without this provisioning step, features such as displaying who owns a catalog entity might not function correctly.

- Securing your Developer Hub instance by ensuring only authenticated users can gain access.
- Enabling authorization by allowing you to define access controls based on user and group memberships synchronized from your IdP.

Tip

To explore Developer Hub features in a non-production environment, you can:

- To use Developer Hub without external IdP, enable the guest user to skip configuring authentication and authorization, log in as the guest user, and access all Developer Hub features.
- To use Developer Hub without authorization policies and features relying on the software catalog, you can enable the`dangerouslyAllowSignInWithoutUserInCatalog` resolver option. This setting bypasses the check requiring a user to be in the catalog but still enforces authentication.

Important

Developer Hub uses a one-way synchronization model, where user and group data flow from your Identity Provider to the Developer Hub software catalog. As a result, deleting users or groups manually through the Developer Hub Web UI or REST API might be ineffective or cause inconsistencies, since Developer Hub will create those entities again during the next import.

### 5.2. Enable or disable authentication with the Guest userCopy link

For trial or non-production environments, you can enable guest access to skip configuring authentication and authorization and explore Developer Hub features.

For production environments, disable guest access to ensure secure authentication and authorization.

#### 5.2.1. Enable the Guest loginCopy link

To allow users to log in as a guest on the login page, enable the guest login option.

Procedure

In the`app-config.yaml` file, set the authentication environment to`development`:

```yaml
auth:
  environment: development
```

1. Restart the Developer Hub application to apply the changes.

Verification

1. Go to the login page of your Developer Hub instance.
2. Verify that the option to log in as a guest is available.

#### 5.2.2. Disable the Guest loginCopy link

To prevent users from logging in as a guest on the login page, disable the guest login option.

Procedure

In the`app-config.yaml` file, set the authentication environment to`production`:

```yaml
auth:
  environment: production
```

1. Restart the Developer Hub application to apply the changes.

Verification

1. Go to the login page of your Developer Hub instance.
2. Verify that the option to log in as a guest is no longer available.

### 5.3. Enabling user authentication with Red Hat Build of Keycloak (RHBK)Copy link

Authenticate users with Red Hat Build of Keycloak (RHBK) by provisioning the users and groups from Red Hat Build of Keycloak to the Developer Hub software catalog, and configuring the Red Hat Build of Keycloak authentication provider in Red Hat Developer Hub. .Prerequisites :_mod-docs-content-type: SNIPPET

You have [access to RHBK](https://docs.redhat.com/en/documentation/red_hat_build_of_keycloak/26.0/html-single/getting_started_guide/index#getting-started-zip-log-in-to-the-admin-console) with an [admin user](https://docs.redhat.com/en/documentation/red_hat_build_of_keycloak/26.0/html-single/getting_started_guide/index#getting-started-zip-create-an-admin-user).

Tip

Alternatively, ask your RHBK administrator to prepare in RHBK the required realm and client.

- You [added a custom Developer Hub application configuration](https://docs.redhat.com/en/documentation/red_hat_developer_hub/1.9/html-single/configuring_red_hat_developer_hub/index), and have enough permissions to change it.
- RHBK version 26.0.

Procedure

Register your Developer Hub app in RHBK:

Use an existing realm, or [create a realm](https://docs.redhat.com/en/documentation/red_hat_build_of_keycloak/26.0/html-single/getting_started_guide/index#getting-started-zip-create-a-realm), with a distinctive Name such as. Save the value for the next step:

- RHBK realm base URL, such as: /realms/.

To register your Developer Hub in RHBK, in the created realm, [secure the first application](https://docs.redhat.com/en/documentation/red_hat_build_of_keycloak/26.0/html-single/getting_started_guide/index#getting-started-zip-secure-the-first-application), with:

Save the values for the next step:

- Client ID
- Client Secret

Add your RHBK credentials and the session secret key to Developer Hub, by adding the following key-value pairs to [your Developer Hub secrets](https://docs.redhat.com/en/documentation/red_hat_developer_hub/1.9/html-single/configuring_dynamic_plugins/index#provisioning-your-custom-configuration). You can use these secrets in the Developer Hub configuration files by using their environment variable name.

`KEYCLOAK_CLIENT_ID` Enter the saved Client ID.`KEYCLOAK_CLIENT_SECRET` Enter the saved Client Secret.`KEYCLOAK_BASE_URL` Enter the saved RHBK realm base URL.`KEYCLOAK_REALM` Enter the realm name to provision users.`KEYCLOAK_LOGIN_REALM` Enter the realm name to authenticate users.`SESSION_SECRET` Enter the created session secret key.

Enable the Keycloak catalog provider plugin in your`dynamic-plugins.yaml` file.

The plugin is named after RHBK upstream project.

This plugin imports RHBK users and groups to the Developer Hub software catalog.

```yaml
plugins:
  - package: './dynamic-plugins/dist/backstage-community-plugin-catalog-backend-module-keycloak-dynamic'
    disabled: false
```

The OIDC provider authentication backend plugin requires Developer Hub to support sessions. Enable session support by adding the session secret to your`app-config.yaml` file:

```yaml
auth:
  session:
    secret: ${SESSION_SECRET}
```

Enable provisioning RHBK users and groups to the Developer Hub software catalog, by adding the`catalog.providers.keycloakOrg` section to your`app-config.yaml` file:

```yaml
catalog:
  providers:
    keycloakOrg:
      default:
        baseUrl: ${KEYCLOAK_BASE_URL}
        clientId: ${KEYCLOAK_CLIENT_ID}
        clientSecret: ${KEYCLOAK_CLIENT_SECRET}
        realm: ${KEYCLOAK_REALM}
        loginRealm: ${KEYCLOAK_LOGIN_REALM}
```

`baseUrl` Enter your RHBK server URL, defined earlier.`clientId` Enter your Developer Hub application client ID in RHBK, defined earlier.`clientSecret` Enter your Developer Hub application client secret in RHBK, defined earlier.`realm` Enter the realm name to provision users.`loginRealm`

Enter the realm name to authenticate users.

Optional: To configure optional fields, see [Configuring Red Hat Developer Hub](https://docs.redhat.com/en/documentation/red_hat_developer_hub/1.9/html-single/configuring_red_hat_developer_hub/index). :_mod-docs-content-type: SNIPPET

Enable the RHBK authentication provider, by adding the OIDC provider section in your`app-config.yaml` file:

```yaml
auth:
  environment: production
  providers:
    oidc:
      production:
        metadataUrl: ${KEYCLOAK_BASE_URL}
        clientId: ${KEYCLOAK_CLIENT_ID}
        clientSecret: ${KEYCLOAK_CLIENT_SECRET}
        prompt: auto
signInPage: oidc
```

`environment: production` Mark the environment as`production` to hide the Guest login in the Developer Hub home page.`metadataUrl`,`clientId`,`clientSecret` Configure the OIDC provider with your secrets.`prompt`

Enter`auto` to allow the identity provider to automatically determine whether to prompt for credentials or bypass the login redirect if an active RHBK session exists.

The identity provider defaults to`none`, which assumes that you are already logged in. Sign-in requests without an active session are rejected.

`signInPage`

Enter`oidc` to enable the OIDC provider as default sign-in provider.

Optional: To configure optional fields, see [Configuring Red Hat Developer Hub](https://docs.redhat.com/en/documentation/red_hat_developer_hub/1.9/html-single/configuring_red_hat_developer_hub/index). :_mod-docs-content-type: SNIPPET

To disable the guest login option, in the`app-config.yaml` file, set the authentication environment to`production`:

```yaml
auth:
  environment: production
```

Verification

To verify user and group provisioning, check the console logs.

Successful synchronization example:

```plaintext
2025-06-27T16:02:34.647Z catalog info Read 5 Keycloak users and 3 Keycloak groups in 0.4 seconds. Committing... class="KeycloakOrgEntityProvider" taskId="KeycloakOrgEntityProvider:default:refresh" taskInstanceId="db55c34b-46b3-402b-b12f-2fbc48498e82" trace_id="606f80a9ce00d1c86800718c4522f7c6" span_id="7ebc2a254a546e90" trace_flags="01"

2025-06-27T16:02:34.650Z catalog info Committed 5 Keycloak users and 3 Keycloak groups in 0.0 seconds. class="KeycloakOrgEntityProvider" taskId="KeycloakOrgEntityProvider:default:refresh" taskInstanceId="db55c34b-46b3-402b-b12f-2fbc48498e82" trace_id="606f80a9ce00d1c86800718c4522f7c6" span_id="7ebc2a254a546e90" trace_flags="01"
```

To verify RHBK user authentication:

1. Go to the Developer Hub login page.
2. Your Developer Hub sign-in page displays Sign in using OIDC and the Guest user sign-in is disabled.
3. Log in with OIDC by using the saved Username and Password values.

### 5.4. Enable user authentication with GitHubCopy link

Authenticate users with GitHub by provisioning the users and groups from GitHub to the Developer Hub software catalog, and configuring the GitHub authentication provider in Red Hat Developer Hub.

Prerequisites

You have enough permissions in GitHub to create and manage a [GitHub App](https://docs.github.com/en/apps/overview).

Tip

Alternatively, ask your GitHub administrator to prepare the required GitHub App.

- You have [added a custom Developer Hub application configuration](https://docs.redhat.com/en/documentation/red_hat_developer_hub/1.9/html-single/configuring_red_hat_developer_hub/index), and have enough permissions to change it.

Procedure

Allow Developer Hub to authenticate with GitHub, by creating a GitHub App.

Note

Use a GitHub App instead of an OAuth app to use fine-grained permissions, use short-lived tokens, scale with the number of installations by avoiding rate limits, and have a more transparent integration by avoiding to request user input.

[Register a GitHub App](https://docs.github.com/en/apps/creating-github-apps/registering-a-github-app/registering-a-github-app) with the following configuration:

GitHub App name Enter a unique name identifying your GitHub App, such as`authenticating-with-rhdh- `. Homepage URL Enter your Developer Hub URL:`https:// `. Authorization callback URL Enter your Developer Hub authentication backend URL:`https:// /api/auth/github/handler/frame`. Webhook Clear "Active". Organization permissions Enable`Read-only` access to Members. Where can this GitHub App be installed? Select`Only on this account`.

Save the following values for the next step:

- Client ID
- Client secret

Add your GitHub credentials to Developer Hub by adding the following key/value pairs to [your Developer Hub secrets](https://docs.redhat.com/en/documentation/red_hat_developer_hub/1.9/html-single/configuring_red_hat_developer_hub/index#provisioning-your-custom-configuration). You can use these secrets in the Developer Hub configuration files by using their environment variable name.

`GITHUB_CLIENT_ID` Enter the saved Client ID.`GITHUB_CLIENT_SECRET` Enter the saved Client Secret.`GITHUB_URL` Enter the GitHub host domain: [https://github.com](https://github.com/).`GITHUB_ORG` Enter your GitHub organization name, such as` `.

Enable the GitHub catalog provider plugin in your`dynamic-plugins.yaml` file to import GitHub users and groups to the Developer Hub software catalog.

```yaml
plugins:
  - package: './dynamic-plugins/dist/backstage-plugin-catalog-backend-module-github-org-dynamic'
    disabled: false
```

Enable provisioning GitHub users and groups to the Developer Hub software catalog by adding the GitHub catalog provider section to your`app-config.yaml` file:

```yaml
catalog:
  providers:
    githubOrg:
      id: githuborg
      githubUrl: "${GITHUB_URL}"
      orgs: [ "${GITHUB_ORG}" ]
      schedule:
        frequency:
          minutes: 30
        initialDelay:
          seconds: 15
        timeout:
          minutes: 15
```

`id`

Enter a stable identifier for this provider, such as`githuborg`.

Warning

Entities from this provider are associated with this identifier. Therefore, do not to change the identifier over time since that might lead to orphaned entities or conflicts.

`githubUrl` Enter the configured secret variable name:`${GITHUB_URL}`.`orgs` Enter the configured secret variable name:`${GITHUB_ORG}`.`schedule.frequency` Enter your schedule frequency, in the cron, ISO duration, or "human duration" format.`schedule.timeout` Enter your schedule timeout, in the ISO duration or "human duration" format.`schedule.initialDelay` Enter your schedule initial delay, in the ISO duration or "human duration" format.

Enable the GitHub authentication provider, by adding the GitHub authentication provider section to your`app-config.yaml` file:

```yaml
auth:
  environment: production
  providers:
    github:
      production:
        clientId: ${GITHUB_CLIENT_ID}
        clientSecret: ${GITHUB_CLIENT_SECRET}
signInPage: github
```

`environment` Enter`production` to disable the Guest login option in the Developer Hub login page.`clientId` Enter the configured secret variable name:`${GITHUB_CLIENT_ID}`.`clientSecret` Enter the configured secret variable name:`${GITHUB_CLIENT_SECRET}`.`signInPage`

Enter`github` to enable the GitHub provider as your Developer Hub sign-in provider.

Optional: To configure optional fields, see [Configuring Red Hat Developer Hub](https://docs.redhat.com/en/documentation/red_hat_developer_hub/1.9/html-single/configuring_red_hat_developer_hub/index). :_mod-docs-content-type: SNIPPET

To disable the guest login option, in the`app-config.yaml` file, set the authentication environment to`production`:

```yaml
auth:
  environment: production
```

Verification

Verify user and group provisioning by checking the console logs.

Successful synchronization example:

```plaintext
{"class":"GithubMultiOrgEntityProvider","level":"info","message":"Reading GitHub users and teams for org: rhdh-dast","plugin":"catalog","service":"backstage","target":"https://github.com","taskId":"GithubMultiOrgEntityProvider:githuborg:refresh","taskInstanceId":"801b3c6c-167f-473b-b43e-e0b4b780c384","timestamp":"2024-09-09 23:55:58"}
{"class":"GithubMultiOrgEntityProvider","level":"info","message":"Read 7 GitHub users and 2 GitHub groups in 0.4 seconds. Committing...","plugin":"catalog","service":"backstage","target":"https://github.com","taskId":"GithubMultiOrgEntityProvider:githuborg:refresh","taskInstanceId":"801b3c6c-167f-473b-b43e-e0b4b780c384","timestamp":"2024-09-09 23:55:59"}
```

To verify GitHub authentication:

1. Go to the Developer Hub login page.
2. Your Developer Hub sign-in page displays Sign in using GitHub and the Guest user sign-in is disabled.
3. Log in with a GitHub account.

Additional resources

- [Integrating Red Hat Developer Hub with GitHub](https://docs.redhat.com/en/documentation/red_hat_developer_hub/1.9/html-single/integrating_red_hat_developer_hub_with_github/index)

### 5.5. Enabling user authentication with Microsoft AzureCopy link

Authenticate users with Microsoft Azure by provisioning the users and groups from Microsoft Azure to the Developer Hub software catalog, and configuring the Microsoft Azure authentication provider in Red Hat Developer Hub.

Prerequisites

You have the permission to register an application in Azure.

Tip

Alternatively, ask your Azure administrator to prepare the required Azure application.

Your Developer Hub backend can access the following hosts:

`login.microsoftonline.com` The Microsoft Azure authorization server, which enables the authentication flow.`graph.microsoft.com` The server for retrieving organization data, including user and group data, to import into the Developer Hub catalog.

- You [added a custom Developer Hub application configuration](https://docs.redhat.com/en/documentation/red_hat_developer_hub/1.9/html-single/configuring_red_hat_developer_hub/index), and have enough permissions to change it.

Procedure

Register your Developer Hub app in Azure, [by using the Azure portal](https://learn.microsoft.com/en-us/entra/identity-platform/scenario-web-app-sign-user-app-configuration?tabs=aspnetcore#register-an-app-by-using-the-azure-portal).

Browse to Applications > App registrations, and create a New registration with the configuration:

Name Enter a name to identify your application in Azure, such as. Supported account types Select Accounts in this organizational directory only. Redirect URI

Select a platform Select Web. URL Enter the backend authentication URI set in Developer Hub:`https:// /api/auth/microsoft/handler/frame`

On the Applications > App registrations > > Manage > API permissions page, Add a Permission, Microsoft Graph, select the following permissions:

Application Permissions

`GroupMember.Read.All`,`User.Read.All`

Enter permissions that enable provisioning user and groups to the Developer Hub software catalog.

Optional: Grant admin consent for these permissions. Even if your company does not require admin consent, consider doing so as it means users do not need to individually consent the first time they access Developer Hub.

Delegated Permissions

`User.Read`,`email`,`offline_access`,`openid`,`profile`

Enter permissions that enable authenticating users.

Optional: Enter optional custom scopes for the Microsoft Graph API that you define both here and in your`app-config.yaml` Developer Hub configuration file.

Save the following values for the next step:

- Directory (tenant) ID
- Application (client) ID
- Application (client) Secret ID

Add your Azure credentials to Developer Hub, by adding the following key/value pairs to [your Developer Hub secrets](https://docs.redhat.com/en/documentation/red_hat_developer_hub/1.9/html-single/configuring_red_hat_developer_hub/index#provisioning-your-custom-configuration):

`MICROSOFT_TENANT_ID` Enter your saved Directory (tenant) ID.`MICROSOFT_CLIENT_ID` Enter your saved Application (client) ID.`MICROSOFT_CLIENT_SECRET` Enter your saved Application (client) secret.

Enable the Microsoft Graph catalog provider plugin in your`dynamic-plugins.yaml` file. This plugin imports Azure users and groups to the Developer Hub software catalog.

```yaml
plugins:
  - package: './dynamic-plugins/dist/backstage-plugin-catalog-backend-module-msgraph-dynamic'
    disabled: false
```

Enable provisioning Azure users and groups to the Developer Hub software catalog, by adding the Microsoft Graph catalog provider section in your`app-config.yaml` file:

```yaml
catalog:
  providers:
    microsoftGraphOrg:
      providerId:
        target: https://graph.microsoft.com/v1.0
        tenantId: ${MICROSOFT_TENANT_ID}
        clientId: ${MICROSOFT_CLIENT_ID}
        clientSecret: ${MICROSOFT_CLIENT_SECRET}
        schedule:
          frequency:
            hours: 1
          timeout:
            minutes: 50
          initialDelay:
            minutes: 50
```

`target` Enter`https://graph.microsoft.com/v1.0` to define the MSGraph API endpoint the provider is connecting to. You might change this parameter to use a different version, such as the [beta endpoint](https://learn.microsoft.com/en-us/graph/api/overview?view=graph-rest-beta#call-the-beta-endpoint).`tenandId` Enter the configured secret variable name:`${MICROSOFT_TENANT_ID}`.`clientId` Enter the configured secret variable name:`${MICROSOFT_CLIENT_ID}`.`clientSecret` Enter the configured secret variable name:`${MICROSOFT_CLIENT_SECRET}`.`schedule`

`frequency` Enter the schedule frequency in the cron, ISO duration, or human duration format. In a large organization, user provisioning might take a long time, therefore avoid using a low value.`timeout` Enter the schedule timeout in the ISO duration or human duration format. In a large organization, user provisioning might take a long time, therefore avoid using a low value.`initialDelay`

Enter the schedule initial delay in the ISO duration or human duration format.

Optional: To configure optional fields, see [Configuring Red Hat Developer Hub](https://docs.redhat.com/en/documentation/red_hat_developer_hub/1.9/html-single/configuring_red_hat_developer_hub/index). :_mod-docs-content-type: SNIPPET

Enable Azure authentication, by adding the Microsoft authentication provider to your`app-config.yaml` file content:

```yaml
auth:
  environment: production
  providers:
    microsoft:
      production:
        clientId: ${MICROSOFT_CLIENT_ID}
        clientSecret: ${MICROSOFT_CLIENT_SECRET}
        tenantId: ${MICROSOFT_TENANT_ID}
signInPage: microsoft
```

`environment` Enter`production` to disable the Guest login option in the Developer Hub login page.`clientId` Enter the configured secret variable name:`${MICROSOFT_CLIENT_ID}`.`clientSecret` Enter the configured secret variable name:`${MICROSOFT_CLIENT_SECRET}`.`tenantId` Enter the configured secret variable name:`${MICROSOFT_TENANT_ID}`.`signInPage`

Enter`microsoft` to set the Azure provider as your Developer Hub sign-in provider.

Optional: To configure optional fields, see [Configuring Red Hat Developer Hub](https://docs.redhat.com/en/documentation/red_hat_developer_hub/1.9/html-single/configuring_red_hat_developer_hub/index). :_mod-docs-content-type: SNIPPET

To disable the guest login option, in the`app-config.yaml` file, set the authentication environment to`production`:

```yaml
auth:
  environment: production
```

Verification

To verify user and group provisioning, check the console logs for`MicrosoftGraphOrgEntityProvider` events.

Successful synchronization example:

```plaintext
2025-06-23T13:37:55.804Z catalog info Read 9 msgraph users and 3 msgraph groups in 1.5 seconds. Committing... class="MicrosoftGraphOrgEntityProvider" taskId="MicrosoftGraphOrgEntityProvider:providerId:refresh" taskInstanceId="e104a116-6481-4ceb-9bc4-0f8f9581f959" trace_id="e4c633659cffd6b1529afa55a5bfbad7" span_id="76affd0420e8baa6" trace_flags="01"

2025-06-23T13:37:55.811Z catalog info Committed 9 msgraph users and 3 msgraph groups in 0.0 seconds. class="MicrosoftGraphOrgEntityProvider" taskId="MicrosoftGraphOrgEntityProvider:providerId:refresh" taskInstanceId="e104a116-6481-4ceb-9bc4-0f8f9581f959" trace_id="e4c633659cffd6b1529afa55a5bfbad7" span_id="76affd0420e8baa6" trace_flags="01"
```

To verify Azure user authentication:

1. Go to the Developer Hub login page.
2. Your Developer Hub sign-in page displays Sign in using Microsoft and the Guest user sign-in is disabled.
3. Log in with an Azure account.

## Chapter 6. Using the Red Hat Developer Hub Operator to run Developer Hub with your custom configurationCopy link

Use the Developer Hub Operator to run Red Hat Developer Hub with your custom configuration by creating your Backstage custom resource (CR) that can perform the following actions:

- Mount files provisioned in your custom config maps.
- Inject environment variables provisioned in your custom secrets.

Prerequisites

- By using the [OpenShift CLI (oc)](https://docs.redhat.com/en/documentation/openshift_container_platform/4.20/html-single/cli_tools/index#cli-about-cli_cli-developer-commands), you have access, with developer permissions, to the OpenShift Container Platform cluster aimed at containing your Developer Hub instance.
- Chapter 2, Installing the Red Hat Developer Hub Operator
- Chapter 4, Provisioning your custom Red Hat Developer Hub configuration

Procedure

Author your Backstage CR in a`my-rhdh-custom-resource.yaml` file to use your custom config maps and secrets.

`my-rhdh-custom-resource.yaml` custom resource example with dynamic plugins and RBAC policies config maps, and external PostgreSQL database secrets.

```yaml
apiVersion: rhdh.redhat.com/v1alpha5
kind: Backstage
metadata:
  name: <my-rhdh-custom-resource>
spec:
  application:
    appConfig:
      mountPath: /opt/app-root/src
      configMaps:
         - name: my-rhdh-app-config
         - name: rbac-policies
    dynamicPluginsConfigMapName: dynamic-plugins-rhdh
    extraEnvs:
      envs:
        - name: HTTP_PROXY
          value: 'http://10.10.10.105:3128'
        - name: HTTPS_PROXY
          value: 'http://10.10.10.106:3128'
        - name: NO_PROXY
          value: 'localhost,example.org'
      secrets:
         - name: my-rhdh-secrets
    extraFiles:
      mountPath: /opt/app-root/src
      secrets:
        - name: my-rhdh-database-certificates-secrets
          key: postgres-crt.pem, postgres-ca.pem, postgres-key.key
    replicas: 2
  database:
    enableLocalDb: false
```

`application`

`appConfig` Register your`my-rhdh-app-config` and`rbac-policies` config maps.`dynamicPluginsConfigMapName` Register your`dynamic-plugins-rhdh` config map.`extraEnvs`

`env` Enter your proxy environment variables.`secrets` Register your` ` and`my-rhdh-database-secrets` secrets.

`extraFiles`

`secrets` Register the`postgres-crt.pem`,`postgres-ca.pem`, and`postgres-key.key` files contained in the`my-rhdh-database-certificates-secrets` secret.

`replicas` Enable high availability (HA) by increasing the replicas count to a value higher or equal to 2.

`database`

`enableLocalDb` Use your external PostgreSQL database rather than the internal PostgreSQL database.

Apply your Backstage CR to start or update your Developer Hub instance.

```shell-session
$ oc apply --filename=my-rhdh-custom-resource.yaml --namespace=my-rhdh-project
```

## Chapter 7. Switching the theme mode for your Developer Hub instanceCopy link

You can switch the RHDH interface between the following modes:

- Light
- Dark
- Auto (Default, matches your system preference)

Note

In RHDH, theme configurations are used to change the look and feel of different UI components. So, you might notice changes in different UI components, such as buttons, tabs, sidebars, cards, and tables along with some changes in background color and font used on the RHDH pages.

Prerequisites

- You are logged in to the RHDH web console.

Procedure

From the Appearance panel, select Light, Dark, or Auto to change the theme mode.

1. From the Developer Hub web console, click Settings.

Verification

- The interface immediately updates to reflect the selected theme.

## Chapter 8. Managing role-based access controls (RBAC) using the Red Hat Developer Hub Web UICopy link

Policy administrators can use the Developer Hub web interface (Web UI) to allocate specific roles and permissions to individual users or groups. Allocating roles ensures that access to resources and functionalities is regulated across the Developer Hub.

With the policy administrator role in Developer Hub, you can assign permissions to users and groups. This role allows you to view, create, modify, and delete the roles using Developer Hub Web UI.

### 8.1. Creating a role in the Red Hat Developer Hub Web UICopy link

You can create a role in the Red Hat Developer Hub using the Web UI.

Prerequisites

- You have enabled RBAC, have a policy administrator role in Developer Hub, and have added plugins with permission.

Procedure

Go to Administration at the bottom of the sidebar in the Developer Hub.

The RBAC tab appears, displaying all the created roles in the Developer Hub.

1. (Optional) Click any role to view the role information on the OVERVIEW page.
2. Click CREATE to create a role.
3. Enter the name and description of the role in the given fields and click NEXT.
4. Add users and groups using the search field, and click NEXT.
5. Select Plugin and Permission from the drop-downs in the Add permission policies section.
6. Select or clear the Policy that you want to set in the Add permission policies section, and click NEXT.
7. Review the added information in the Review and create section.
8. Click CREATE.

Verification

The created role appears in the list available in the RBAC tab.

### 8.2. Editing a role in the Red Hat Developer Hub Web UICopy link

You can edit a role in the Red Hat Developer Hub using the Web UI.

Note

The policies generated from a`policy.csv` or ConfigMap file cannot be edited or deleted using the Developer Hub Web UI.

Prerequisites

- You have enabled RBAC, have a policy administrator role in Developer Hub, and have added plugins with permission.
- The role that you want to edit is created in the Developer Hub.

Procedure

Go to Administration at the bottom of the sidebar in the Developer Hub.

The RBAC tab appears, displaying all the created roles in the Developer Hub.

1. (Optional) Click any role to view the role information on the OVERVIEW page.
2. Select the edit icon for the role that you want to edit.
3. Edit the details of the role, such as name, description, users and groups, and permission policies, and click NEXT.
4. Review the edited details of the role and click SAVE.

After editing a role, you can view the edited details of a role on the OVERVIEW page of a role. You can also edit a role’s users and groups or permissions by using the edit icon on the respective cards on the OVERVIEW page.

### 8.3. Deleting a role in the Red Hat Developer Hub Web UICopy link

You can delete a role in the Red Hat Developer Hub using the Web UI.

Note

The policies generated from a`policy.csv` or ConfigMap file cannot be edited or deleted using the Developer Hub Web UI.

Prerequisites

- You have enabled RBAC and have a policy administrator role in Developer Hub.
- The role that you want to delete is created in the Developer Hub.

Procedure

Go to Administration at the bottom of the sidebar in the Developer Hub.

The RBAC tab appears, displaying all the created roles in the Developer Hub.

Select the delete icon from the Actions column for the role that you want to delete.

Delete this role? pop-up appears on the screen.

1. (Optional) Click any role to view the role information on the OVERVIEW page.
2. Click DELETE.

## Legal NoticeCopy link

Copyright © Red Hat.

Except as otherwise noted below, the text of and illustrations in this documentation are licensed by Red Hat under the Creative Commons Attribution–Share Alike 3.0 Unported license . If you distribute this document or an adaptation of it, you must provide the URL for the original version.

Red Hat, as the licensor of this document, waives the right to enforce, and agrees not to assert, Section 4d of CC-BY-SA to the fullest extent permitted by applicable law.

Red Hat, the Red Hat logo, JBoss, Hibernate, and RHCE are trademarks or registered trademarks of Red Hat, LLC. or its subsidiaries in the United States and other countries.

Linux® is the registered trademark of Linus Torvalds in the United States and other countries.

XFS is a trademark or registered trademark of Hewlett Packard Enterprise Development LP or its subsidiaries in the United States and other countries.

The OpenStack® Word Mark and OpenStack logo are trademarks or registered trademarks of the Linux Foundation, used under license.

All other trademarks are the property of their respective owners.

---

*Source: https://docs.redhat.com/en/documentation/red_hat_developer_hub/1.9/html-single/navigate_red_hat_developer_hub_on_your_first_day/index*

Navigate Red Hat Developer Hub on your first day | Red Hat Developer Hub | 1.9 | Red Hat Documentation

 

 

 

 

 

 

 

 

# Navigate Red Hat Developer Hub on your first day

---

Red Hat Developer Hub 1.9

## Log in, navigate the Red Hat Developer Hub (RHDH) interface, and personalize your workspace to become productive immediately.

Red Hat Customer Content Services

Legal Notice

Abstract

As a new developer, you will learn how to personalize your experience by customizing settings and starring frequently used items, enabling you to independently find the software catalog, templates, and documentation you need.

---

## PrefaceCopy link

As a new developer, you will learn how to personalize your experience by customizing settings and starring frequently used items, enabling you to independently find the software catalog, templates, and documentation you need.

## Chapter 1. Purpose and structure of Red Hat Developer HubCopy link

To streamline your software development lifecycle, your organization has chosen to use Red Hat Developer Hub (RHDH) as your primary internal developer portal (IDP).

RHDH offers a unified interface to manage infrastructure, generate software projects, use tools and services, and access technical documentation in a centralized location.

The developer experience challenge Modern software development often involves fragmented workflows. Developers frequently switch contexts between code repositories, ticketing systems, CI/CD pipelines, monitoring dashboards, and so on. This fragmentation creates information silos and increases the time required to find API documentation, service ownership details, or deployment status. Improving developer productivity with RHDH

RHDH centralizes fragmented technical resources to streamline the developer experience. The platform provides the following advantages:

- Legal Notice
- Unified discovery: Locate applications, APIs, and documentation in a single, accessible location.
- Search capabilities: Find users, groups, and catalog using an integrated search feature. Once indexed, all entities become discoverable across the entire ecosystem within seconds.
- Self-service capabilities: Create projects and deploy services using automated Software Templates. This ensures consistency and reduces reliance on manual ticketing.
- Service visibility: Map relationships between services, dependencies, and API contracts to clarify ownership and component linkages.
- Extensibility: Integrate external tools, such as monitoring dashboards and pipelines, directly into the portal to create a unified interface.

### 1.1. Overview of the onboarding workflowCopy link

To begin using RHDH, you must verify your permissions, confirm software entity ownership, and personalize your workspace. After you complete these initial tasks, you can locate team services and use self-service capabilities.

Log in and verify your profile

Your first step will be to confirm you are logged in as the correct user and that the platform recognizes your identity.

- Authenticate: Access your organization’s RHDH URL and log in using your corporate credentials.
- Check your identity: Click your avatar or name in the Global Header and select Settings. Verify that the User Entity matches your corporate identity.
- Verify ownership: Navigate to My Profile from the user menu. Check the Ownership card to make sure it lists the components, APIs, and systems you or your team are responsible for. If this list is empty, you might need to register your ownership in the Software Catalog. For more information, see [Log in to RHDH](https://docs.redhat.com/en/documentation/red_hat_developer_hub/1.9/html-single/navigate_red_hat_developer_hub_on_your_first_day/index#log-in-to-rhdh).

Organize your workspace

To reduce navigation time across the software ecosystem, curate a list of high-priority resources.

- Star your services: Locate your frequently used components in the Catalog and select the Add to favourites (star) icon to add them to your favorites. See [starred items](https://docs.redhat.com/en/documentation/red_hat_developer_hub/1.9/html-single/navigate_red_hat_developer_hub_on_your_first_day/index#managing-starred-items-for-quick-access).
- Access starred items: Use the Starred Items menu in the Global Header to open your favorite resources quickly.
- Pin the Sidebar: Use this if you switch frequently between tools. The sidebar is pinned by default. To unpin the sidebar, go to Settings and disable Pin Sidebar. See [Customize your RHDH interface settings](https://docs.redhat.com/en/documentation/red_hat_developer_hub/1.9/html-single/navigate_red_hat_developer_hub_on_your_first_day/index#customize-interface-settings).

Explore production resources

Production instances provide access to live infrastructure data and technical guides required for operational tasks.

- [See production documentation](https://docs.redhat.com/en/documentation/red_hat_developer_hub/1.9/html-single/navigate_red_hat_developer_hub_on_your_first_day/index#finding-and-viewing-techdocs): Use the Docs menu to find production-specific documentation, such as runbooks or deployment guides linked to your components.
- [Check production APIs](https://docs.redhat.com/en/documentation/red_hat_developer_hub/1.9/html-single/navigate_red_hat_developer_hub_on_your_first_day/index#verify-api-contracts-before-integration): Navigate to the APIs page to confirm that production endpoint definitions are accurate and discoverable.
- [Test Self-Service templates](https://docs.redhat.com/en/documentation/red_hat_developer_hub/1.9/html-single/navigate_red_hat_developer_hub_on_your_first_day/index#import-and-use-an-existing-template-for-faster-development): Click Create (+) in the Global Header to examine available Software Templates. Confirm that you have access to the Software Templates required to generate new production services.

Next steps

- [Start your day](https://docs.redhat.com/en/documentation/red_hat_developer_hub/1.9/html-single/navigate_red_hat_developer_hub_on_your_first_day/index#con-navigate-the-rhdh-interface_quickly-gain-access-and-find-what-you-need-in-rhdh): Navigate to the RHDH Home Page to view your personalized dashboard.
- [Learn the interface](https://docs.redhat.com/en/documentation/red_hat_developer_hub/1.9/html-single/navigate_red_hat_developer_hub_on_your_first_day/index#con-navigate-the-rhdh-interface_quickly-gain-access-and-find-what-you-need-in-rhdh): Understand the Global Header and Sidebar tools.
- [Get AI assistance](https://docs.redhat.com/en/documentation/red_hat_developer_hub/1.9/html-single/navigate_red_hat_developer_hub_on_your_first_day/index#get-ai-assisted-help): If enabled in production, open Developer Lightspeed for RHDH to ask questions about your new environment.

### 1.2. Leverage RHDH capabilities for daily productivityCopy link

To streamline your software development lifecycle, use the integrated toolset within Red Hat Developer Hub (RHDH). These core capabilities allow you to perform self-service tasks and discover organizational resources from a centralized interface.

By using the following workflow capabilities, you can reduce context switching and automate repetitive setup tasks:

Software Catalog Use this centralized inventory to manage software components, APIs, and systems. You can visualize ownership, track dependencies, and monitor the lifecycle status of assets across the organization. Software Templates Automate the creation of new projects by using self-service templates. These templates bootstrap repositories, configure CI/CD pipelines, and apply organizational best practices to eliminate manual configuration. TechDocs Access "docs-as-code" documentation that renders directly within the portal. This makes sure that technical guides remain synchronized with the codebase and are easily discoverable alongside their corresponding services. Plugins Platform engineers can extend the functionality of the portal by connecting it to your existing toolchain. For example, your RHDH portal might have been configured to allow you to monitor Argo CD deployments, view Jira tickets, or review Quay registry scans without navigating away from the portal interface. Global Search Find specific components, documentation, or team members instantly by using a unified search engine that indexes the entire software ecosystem.

## Chapter 2. Log in to Red Hat Developer HubCopy link

To access your organization’s software catalog, templates, and documentation, you must authenticate with Red Hat Developer Hub (RHDH) using your configured identity provider. With secure authentication, you have the appropriate permissions to view and manage software entities linked to your role.

By logging in, you gain entry to a centralized portal that streamlines your development workflow. This secure access allows you to locate required software building blocks and documentation without navigating multiple disparate systems.

### 2.1. Log in to RHDHCopy link

To access your organization’s software ecosystem, tools, and self-service templates, log in to the Red Hat Developer Hub (RHDH) web console. With a successful login, you can access resources permitted by your role-based access control (RBAC) settings.

Procedure

On the login page, select the appropriate authentication method configured for your environment:

Guest access: If available, select Enter as Guest for read-only exploration.

Note

Guest access permissions are limited and do not allow you to create or register components. Production environments typically disable guest access for security. To perform these actions, you must use an authenticated account with the required permissions.

- Enterprise authentication: Log in with your corporate credentials, such as Single Sign-On or Keycloak.
- OAuth provider: Select an external provider, such as GitHub or GitLab, if your organization manages developer identities through those services.

Verification

- Enter your credentials and complete the required authentication prompts.
- The portal redirects you to the Home page.

- Your personalized dashboard appears, displaying your username and recently accessed items.

### 2.2. Authentication methods to enable self-service capabilitiesCopy link

To access the specific tools, repositories, and permissions assigned to your role, you must authenticate to Red Hat Developer Hub (RHDH) using a supported provider. Selecting the correct authentication method makes sure that the portal correctly associates your identity with your team’s software components and self-service capabilities.

Common authentication methods:

Corporate Single Sign-On (SSO) Select this option to use your standard organizational credentials. This method typically provides full access to internal resources and links your portal identity to your official team membership. Federated authentication Use services such as Keycloak or Ping Federate to unify your login across multiple enterprise applications. This makes sure that your permissions remain consistent as you navigate between the portal and external development tools. OAuth providers Select providers such as GitHub, GitLab, or Azure DevOps to link your developer identity directly to the portal. This is required if you need to perform actions in the Software Catalog that involve external version control systems. Guest access

Use this restricted, read-only mode for initial platform exploration.

Important

Guest access is intended for demonstration purposes. While in this mode, you cannot register components, use Software Templates, or access restricted organizational data.

### 2.3. Navigate the RHDH interfaceCopy link

To locate resources, manage projects, and customize your workspace, use the two primary navigation areas in Red Hat Developer Hub (RHDH). By understanding these regions, you can navigate the software ecosystem and execute development tasks efficiently.

Using the Global Header

The top bar provides immediate access to frequency used actions and personal settings.

Search: Locate components, APIs, documentation, and users across the entire ecosystem instantly.

Self-service (+): Access Software Templates to create new components or projects.

User profile: Manage interface settings, set appearance preferences (such as Dark Mode), or log out.

Help (?): Access support resources and toggle the Quick start panel.

Support and notifications: View system alerts and platform help resources.

Application Launcher: Access integrated applications and tools.

Starred Items: View your bookmarked entities.

Using the left navigation sidebar

The sidebar organizes core platform features into distinct functional sections.

Home: View your personalized dashboard, including team updates and recently accessed items.

Catalog: Browse the inventory of software components, services, and resources to visualize ownership and dependencies.

Docs: Access the technical documentation library (TechDocs) to read guides linked to specific software entities.

APIs: Explore and test available API definitions in the centralized browser.

Additional resources

- [Authentication in Red Hat Developer Hub](https://docs.redhat.com/en/documentation/red_hat_developer_hub/1.9/html-single/authentication_in_red_hat_developer_hub/index)
- [Customizing Red Hat Developer Hub](https://docs.redhat.com/en/documentation/red_hat_developer_hub/1.9/html-single/customizing_red_hat_developer_hub/index)

## Chapter 3. Find software components to discover assetsCopy link

To integrate with existing software, verify ownership, or prevent redundant development, use the Software Catalog in Red Hat Developer Hub (RHDH). You can use this centralized inventory to discover assets without searching through multiple documentation sources or repositories.

By using the Software Catalog, you can achieve the following outcomes:

Reuse existing code Locate shared libraries and components to prevent duplication of effort. Understand dependencies Visualize connections between services and API contracts. Identify ownership Find the team or individual responsible for an asset to facilitate collaboration.

Note

Your administrator must grant specific permissions if you want to add the products you are working on to this catalog. Contact your administrator for more information.

### 3.1. Filter components in the Software CatalogCopy link

To identify existing software building blocks, view source code, or check lifecycle status, use the catalog filters in RHDH. Filtering the catalog makes sure you can locate production-ready components and documentation without performing manual searches across multiple repositories.

Procedure

1. Log in to the RHDH interface.
2. In the navigation sidebar, click Catalog.
3. Click the Kind filter and select Component.
4. Optional: Click the Type filter to select a functional category, such as Library, Website, or Tool.
5. In the Search field, enter the name of the component.
6. Select the component card to view the Overview, Docs, and Relationships tabs.

### 3.2. Analyze microservice dependencies in the Software CatalogCopy link

To identify backend applications and microservices, view their deployment status, and understand their API relationships, use the filters in the Red Hat Developer Hub (RHDH) Software Catalog. Filtering for services makes sure you can verify architectural connections and ownership without manually tracing code across disparate repositories.

Procedure

1. Navigate to the RHDH interface.
2. In the left navigation sidebar, click Catalog.
3. Click the Kind filter and select Component.
4. Click the Type filter and select Service.
5. Optional: Narrow the list by selecting a specific Owner or Lifecycle, such as`production` or`experimental`.
6. Select a service card to open the detailed entity view.
7. Review the Overview tab to identify the assigned owner and access links to the source code repository.
8. Select the Dependencies or Relations tab to see upstream and downstream connections for that service.

### 3.3. Review API contracts to verify service endpoints and schemasCopy link

To verify endpoints, schemas, and server URLs for your services, locate and inspect API contracts in Red Hat Developer Hub (RHDH). Reviewing these definitions allows you to validate required parameters and data models in a rendered format without manually searching through source code repositories.

Procedure

In the left navigation sidebar, click APIs.

Note

You can also find these resources by clicking Catalog and selecting API from the Kind filter.

1. Navigate to the RHDH interface.
2. Select an API from the list to open the detail view.
3. Select the Overview tab to examine the metadata, assigned owner, and lifecycle status.
4. Select the Definition tab to view the rendered specification, such as an interactive Swagger or AsyncAPI interface.

Note

In some cases, to test APIs in the RHDH interface before writing a single line of code, you must correctly configure the API definition.

## Chapter 4. Import and use an existing Software Template for faster developmentCopy link

To standardize and accelerate the creation of new software, use Software Templates in Red Hat Developer Hub (RHDH). You can generate consistent software components, publish them to Git repositories, and register them in the Software Catalog to make sure they are discoverable across your organization.

Each template uses a YAML definition to present a functional interface for inputting project metadata. Software Templates run a sequential series of actions, such as scaffolding code or creating repositories, which you can configure to run conditionally based on user input.

### 4.1. Creating a software component using Software TemplatesCopy link

To ensure project consistency and reduce manual configuration time, use Software Templates to create new components. These templates automate the scaffolding process, providing a pre-configured project structure directly from your Red Hat Developer Hub portal.

Prerequisites

- You are logged in to the Red Hat Developer Hub instance.

Procedure

In the Review step, verify your parameters and click Create.

The scaffolding process begins. You can monitor the progress in the logs or click Cancel to stop the operation.

1. On the Red Hat Developer Hub navigation menu, click Catalog > Self-service or in the Global Header, click Create (+).
2. On the Self-service page, locate the Templates you want to use and click Choose to start the scaffolding process.
3. Follow the wizard instructions to enter the required project details.

Verification

If the component is created successfully, a success page appears.

Troubleshooting

To retry, click Start Over; the system retains your previously entered values on the Self-service page.

- If the creation process fails, you must review the logs on the error page for specific failure details.

### 4.2. Searching and filtering Software Templates in your Red Hat Developer Hub instanceCopy link

To quickly locate the correct configuration for your project and reduce time spent browsing the catalog, search and filter the available Software Templates. Narrowing your search ensures you select the specific template that meets your development requirements.

Procedure

1. In the Red Hat Developer Hub navigation menu, click Catalog > Self-service.
2. In the Search field, enter the name for the Software Template.
3. Optional: To refine the results, select a category from the Category list.

### 4.3. Importing an existing Software TemplateCopy link

You can use the Catalog Processor to import an existing Software Template into your Red Hat Developer Hub instance.

Prerequisites

- You have created a directory or repository that contains at least one template YAML file.
- Optional: To use a template stored in GitHub, you have configured [Developer Hub integration with GitHub](https://docs.redhat.com/en/documentation/red_hat_developer_hub/1.9/html-single/integrating_red_hat_developer_hub_with_github/index).

Procedure

In the`catalog.rules` section, add a rule to allow`Templates` kinds, as shown in the following example:

```yaml
# ...
catalog:
  rules:
    - allow: [Template]
  locations:
    - type: url
      target: https://<repository_url/template-name>.yaml
# ...
```

where:

`catalog.rules.allow` Specify the`Template` rule to allow new Software Templates in the catalog.`catalog.locations.type` Specify the`url` type when importing templates from a repository (for example, GitHub or GitLab).`catalog.locations.target` Specify the full URL to the template file.

1. Open your RHDH`app-config.yaml` configuration file.

Verification

1. In the Red Hat Developer Hub navigation menu, click Catalog.
2. From the Kind list, select Template.
3. Verify that your template appears in the Template list.

## Chapter 5. Searching for relevant content in Technical Documentation (TechDocs)Copy link

To reduce context switching and to make sure technical resources are accessible, use TechDocs in Red Hat Developer Hub. Centralizing documentation allows you to review architecture diagrams, installation guides, and component details alongside software entities in a single interface.

Prerequisites

- The TechDocs plugin is enabled and configured.
- Documentation is imported into TechDocs.
- You have the required roles and permissions to access TechDocs content.

### 5.1. Search for relevant contentCopy link

To quickly find the information required for your services, search or filter the TechDocs catalog. Narrowing your search makes sure you find relevant resources without browsing multiple repositories.

Procedure

On the Documentation page, use the Search bar or filters to locate your document:

- Search: Enter keywords to find specific terms within documents.
- Filter by Owner: View documents owned by specific users or groups.
- Filter by Tags: Narrow results by specific labels or categories.
- Filter by Owned: View documents belonging to you or your group.
- Filter by Starred: View your bookmarked favorites.

The results update automatically to show the number of documents that meet your criteria.

### 5.2. Access and navigate documentationCopy link

Use the built-in navigation tools to move between related documents within a book. This ensures you can easily reference implementation details and requirements in a logical sequence.

Procedure

Navigate the content using the following on-screen tools:

- Search bar: Find keywords within the current document.
- Table of contents: Jump to specific sections.
- Navigation menu: Switch between different documents in the same book.
- Next: Proceed to the next sequential document.
- Add-ons: Perform additional actions if plugins are configured, such as setting the text size.

## Chapter 6. Verify API contracts before integrating with backend servicesCopy link

To integrate with existing services and understand component communication, use the Red Hat Developer Hub (RHDH) API browser to locate, inspect, and validate API definitions in a centralized interface. You can review API contracts and test endpoints to verify responses directly from the portal.

Apart from viewing specifications, you can use the interactive interface to test available endpoints and review data models. This verification confirms that your application correctly interfaces with backend services before you begin integration.

### 6.1. Test API endpoints interactivelyCopy link

To verify API behavior and debug service responses, use the interactive test interface in Red Hat Developer Hub (RHDH). Validating endpoints directly within the portal makes sure they return the expected status codes and data models, which eliminates the need to configure external testing tools during the initial integration phase.

Prerequisites

- You have the required credentials or tokens if the API requires authentication.

Procedure

1. Navigate to the specific API you must validate and select the Definition tab.
2. Locate the operation you want to verify and select the header to expand the details.
3. Click Try it out to enable the interactive input fields.
4. Enter the required parameters or the request body JSON in the provided fields.
5. Click Execute.
6. Examine the Server response section to verify the status code, response body, and headers.

Note

If you receive 401 (Unauthorized) or 403 (Forbidden) errors, you must verify your credentials with the API owner or check the authorization header requirements in the specification.

### 6.2. Select supported API specificationsCopy link

To make sure your API definitions are discoverable and interactive within the Red Hat Developer Hub (RHDH) portal, you must use a supported specification format. Selecting the appropriate format based on your service architecture allows the platform to render documentation and provide testing interfaces for other developers.

Supported API formats

- OpenAPI: Use this standard format (formerly Swagger) for defining RESTful APIs. RHDH renders these specifications into interactive documentation, allowing users to execute requests directly from the Definition tab.
- AsyncAPI: Use this format for event-driven architectures and message-based services. This ensures that producers and consumers can visualize message schemas and broker details within the Software Catalog.
- GraphQL: Use this format to define GraphQL schemas. This allows developers to explore your data graph, including queries, mutations, and types, from a centralized location.

Note

Support for specific formats, such as AsyncAPI or GraphQL, depends on your organization’s instance configuration and enabled plugins. If a specification does not render, verify your`catalog-info.yaml` metadata or contact your platform administrator.

## Chapter 7. Find resources and documentation using searchCopy link

To find software components, APIs, and technical documentation across the enterprise, use the centralized search feature in Red Hat Developer Hub (RHDH). By indexing the entire software ecosystem, this tool helps you locate services, templates, and team members directly, without navigating through nested menus.

By using the Search bar, you can access matching resources from the Software Catalog and TechDocs instantly. This unified access reduces the time spent switching between disparate documentation sites or service registries during the development lifecycle.

### 7.1. Locate resources instantlyCopy link

To bypass menu navigation and locate technical assets quickly, use the universal search bar in Red Hat Developer Hub (RHDH). The search engine provides real-time suggestions to help you navigate directly to services, documentation, or team members, which reduces the time spent searching across disparate repositories.

Procedure

1. Click the Search input field in the Global Header.
2. Enter your query, such as a service name, API endpoint, or technical concept.
3. Review the instant results in the dropdown menu, which categorize matches by entity types such as Components, APIs, TechDocs, and Users.
4. Select a suggestion from the list to navigate directly to that resource.

### 7.2. Filter and refine search resultsCopy link

To isolate specific components, APIs, or documentation, within a large software ecosystem, use the advanced search filters in RHDH. Refining your search results makes sure you can locate production-ready services and ownership details without navigating through unrelated resources.

Procedure

In the Filters panel, select one or more of the following options:

- Kind: Filters by entity category, such as Component, API, Template, or Group.
- Type: Narrows the results by functional subtype, such as service, library, or website.
- Lifecycle: Isolates resources by operational status, such as production, experimental, or deprecated.

## Chapter 8. Get AI-assisted help for your development tasksCopy link

To assist with development tasks, platform questions, and debugging, use Red Hat Developer Lightspeed for Red Hat Developer Hub, a generative AI virtual assistant integrated directly into Red Hat Developer Hub (RHDH). You can use the conversational interface to ask platform-specific questions, analyze logs, generate code, and create test plans, which reduces the time spent searching through official documentation or disparate tools.

### 8.1. Configure safety guards in Red Hat Developer HubCopy link

To protect users from insecure or harmful AI model outputs, Red Hat Developer Hub (RHDH) uses Llama Guard as a default safety shield. You must configure these guards to align with your organization’s security policies.

`Default safety guard configuration` The system uses Llama Guard as the default safety shield. Override these settings in the`run.yaml` file.

Note

The`external_providers_dir` parameter defaults to null and is no longer required in your configuration.

`Overriding safety guards` To implement custom security layers or different safety shields, you must define a new safety provider within a custom`run.yaml` file.`Disabling safety guards` To run RHDH without safety guards, you must use the`run-no-guard.yaml` configuration file.

Important

Running without safety guards increases the risk of unvalidated model output. Only use this configuration in secure development environments.

`Applying the no-guard configuration` To run the system without a safety guard, perform these steps:

Procedure

Add the following YAML file as a config map to your namespace:

```yaml
version: 2
image_name: redhat-ai-dev-llama-stack-no-guard
apis:
  - agents
  - inference
  - safety
  - tool_runtime
  - vector_io
  - files
container_image:
external_providers_dir:
providers:
  agents:
    - config:
        persistence:
          agent_state:
            namespace: agents
            backend: kv_default
          responses:
            table_name: responses
            backend: sql_default
      provider_id: meta-reference
      provider_type: inline::meta-reference
  inference:
    - provider_id: ${env.ENABLE_VLLM:+vllm}
      provider_type: remote::vllm
      config:
        url: ${env.VLLM_URL:=}
        api_token: ${env.VLLM_API_KEY:=}
        max_tokens: ${env.VLLM_MAX_TOKENS:=4096}
        tls_verify: ${env.VLLM_TLS_VERIFY:=true}
    - provider_id: ${env.ENABLE_OLLAMA:+ollama}
      provider_type: remote::ollama
      config:
        url: ${env.OLLAMA_URL:=http://localhost:11434}
    - provider_id: ${env.ENABLE_OPENAI:+openai}
      provider_type: remote::openai
      config:
        api_key: ${env.OPENAI_API_KEY:=}
    - provider_id: ${env.ENABLE_VERTEX_AI:+vertexai}
      provider_type: remote::vertexai
      config:
        project: ${env.VERTEX_AI_PROJECT:=}
        location: ${env.VERTEX_AI_LOCATION:=us-central1}
    - provider_id: sentence-transformers
      provider_type: inline::sentence-transformers
      config: {}
  tool_runtime:
    - provider_id: model-context-protocol
      provider_type: remote::model-context-protocol
      config: {}
    - provider_id: rag-runtime
      provider_type: inline::rag-runtime
      config: {}
  vector_io:
    - provider_id: faiss
      provider_type: inline::faiss
      config:
        persistence:
          namespace: vector_io::faiss
          backend: faiss_kv
  files:
    - provider_id: localfs
      provider_type: inline::localfs
      config:
        storage_dir: /tmp/llama-stack-files
        metadata_store:
          table_name: files_metadata
          backend: sql_files
storage:
  backends:
    kv_default:
      type: kv_sqlite
      db_path: /tmp/kvstore.db
    sql_default:
      type: sql_sqlite
      db_path: /tmp/sql_store.db
    sql_files:
      type: sql_sqlite
      db_path: /rag-content/vector_db/rhdh_product_docs/1.9/files_metadata.db
    faiss_kv:
      type: kv_sqlite
      db_path: /rag-content/vector_db/rhdh_product_docs/1.9/faiss_store.db
  stores:
    metadata:
      namespace: registry
      backend: faiss_kv
    inference:
      table_name: inference_store
      backend: sql_default
      max_write_queue_size: 10000
      num_writers: 4
    conversations:
      table_name: openai_conversations
      backend: sql_default
registered_resources:
  models:
    - model_id: sentence-transformers/all-mpnet-base-v2
      metadata:
        embedding_dimension: 768
      model_type: embedding
      provider_id: sentence-transformers
      provider_model_id: /rag-content/embeddings_model
  tool_groups:
    - provider_id: rag-runtime
      toolgroup_id: builtin::rag
  vector_dbs:
    - vector_db_id: rhdh-product-docs-1_8
      embedding_model: sentence-transformers/all-mpnet-base-v2
      embedding_dimension: 768
      provider_id: faiss
server:
  auth:
  host:
  port: 8321
  quota:
  tls_cafile:
  tls_certfile:
  tls_keyfile:
```

Mount the config map to your Llama Stack container at`/app-root/run.yaml` to make sure it overrides the default image file:

```yaml
name: llama-stack
volumeMounts:
- mountPath: /app-root/run.yaml
  subPath: run.yaml
  name: llama-stack-config
```

Configure the required volume:

```yaml
volumes:
- name: llama-stack-config
  configMap:
    name: llama-stack-config
```

where:

`llama-stack-config` The config map where you added the new no-guard configuration file.

1. Restart the deployment if it does not trigger an automatic rollout.

Prerequisites

- Your platform engineer has installed and configured the Developer Lightspeed for RHDH plugin in your RHDH instance.

### 8.2. Get the best results for assistant queriesCopy link

To resolve technical blockers and accelerate development tasks, you must structure your queries to provide specific context to the AI assistant. Using precise prompts ensures that Developer Lightspeed for RHDH generates relevant code snippets, architectural advice, or platform-specific instructions.

Use the following strategies to improve the accuracy of the assistant’s output during your development workflow:

Specify technologies Instead of asking "How do I use templates?", ask "How do I create a Software Template that scaffolds a Node.js service with a CI/CD pipeline". Provide context Include details about your environment, such as "I am deploying to OpenShift; how do I configure my catalog-info.yaml to show pod health?". Leverage conversation context Ask follow-up questions to refine a previous answer. For example, if the assistant provides a code snippet, you can ask "Now rewrite that using TypeScript interfaces." Validate with citations Examine the provided documentation links and citations in the response to verify that the generated advice aligns with your organization’s official standards. Improve assistant accuracy Rate the utility of responses by selecting the Thumbs up or Thumbs down icons. This feedback helps tune the model for your organization’s specific requirements.

Important

To maintain security standards, do not include sensitive personal information, plain-text credentials, or confidential business data in your queries.

### 8.3. Monitor AI responses and context managementCopy link

Developer Lightspeed for RHDH provides features to track the AI reasoning process and maintain the context of your development tasks.

Thinking cards An expandable thinking card appears while the AI processes a query. A pulse animation indicates the reasoning phase. You can expand the card to view detailed reasoning or collapse it to minimize screen clutter. Tool call transparency An expandable card displays details for Model Context Protocol (MCP) tool calls, which allows you to monitor background processes. Context-aware citations Retrieval-Augmented Generation (RAG) citations appear only when the AI uses internal documentation. This makes sure that general knowledge responses remain concise. Context preservation during model changes When you select a different AI model, Developer Lightspeed for RHDH starts a new conversation. This makes sure that your previous chats remain available in your history. Structural readability The interface formats headings and bullet points automatically to make sure responses are scannable.

### 8.4. Managing chatsCopy link

Manage your chat history in RHDH to organize your workspace, resume previous tasks, or find past solutions.

Prerequisites

- You have configured the Developer Lightspeed for RHDH plugin in Red Hat Developer Hub.
- You must be logged in to the portal.

Procedure

Optional: Configure the interface display:

Click the Display menu icon and select any of the following views:

Overlay: A floating window appears over the current page content.

Dock to window: A panel attaches to the right side of the screen. Activating this mode automatically closes the Quickstart panel if it is already open.

Fullscreen: A dedicated page opens for intensive chat sessions. You can bookmark this URL for direct access.

- Optional: Toggle Enable pinned chats/Disable pinned chats to enable or hide the pinned chats. This is enabled by default.

Start a chat or load a previous session:

Attach a file: Click Attach to upload a`.yaml`,`.json`, or`.txt` file.

View or edit the content of the file:

1. Click the file name to open the preview model.

Organize your chat history:

Rename a session: Click Options next to a chat title, select Rename, and enter a new name.

Sort chats:: Click Sort control and choose a sorting criteria, such as Date (Newest first).

Delete a chat: Click Options next to a chat title and select Delete.

- Start a new topic: Click New chat to reset the assistant’s context.
- Search history: Enter a keyword in the Search field.
- Pin a chat:: Click Options next to a chat title and select Pin. The chat moves to the Pinned group.
- Hide the chat history section and reduce visual noise: Select the Close icon (x) next to New chat.
- Restore access to your pinned chat: Select the Chat history menu icon.

Verification

- Optional: In Fullscreen mode, bookmark the URL in your browser to save a direct link to the chat interface.
- The main window displays the active chat or selected history.
- The chat history list reflects renamed, pinned, or deleted entries.

## Chapter 9. Integrate and customize your daily tools using extensionsCopy link

To integrate your development tools into a single interface and customize your experience, use the extensions and plugins in Red Hat Developer Hub RHDH. While core features such as the Software Catalog and TechDocs are pre-installed, you can add plugins to interact with external services such as Jira, Jenkins, or ArgoCD. By discovering global features in the navigation sidebar or reviewing entity-specific data in the catalog, you extend portal capabilities and access information from your daily tools directly within the portal.

### 9.1. Access platform-wide tools using global pluginsCopy link

To manage technology standards, track project costs, or access AI assistance, use the global plugins located in the main navigation sidebar of Red Hat Developer Hub (RHDH). These plugins provide features that apply across the entire portal rather than to a specific software entity.

Procedure

In the left navigation sidebar, locate the standard or custom items that represent specific global extensions, such as:

- Tech Radar: Use this to visualize your organization’s approved and deprecated technologies.
- Cost Insights: Use this to track and analyze cloud infrastructure spending for your projects.
- Developer Lightspeed: Access this conversational interface for AI-assisted development help and platform queries.
- Learning Paths: Access training and educational resources to improve your technical skills.

### 9.2. Review RHDH resource dataCopy link

To review data specific to a particular resource in RHDH, use entity plugins that appear when viewing components, services, or APIs in the Software Catalog.

Procedure

Review the tabs at the top of the entity page to view configured plugins:

- CI/CD: Displays build status and history from tools such as Jenkins or GitHub Actions.
- Kubernetes: Shows deployment status and pod health.
- Topology: Visualizes the relationships and status of running resources.

Next steps

- If a build fails in the CI/CD tab, select the build number to view the logs in the source provider.
- If a pod is failing in the Kubernetes tab, examine the error events to troubleshoot the deployment.

### 9.3. Identify integrations in the RHDH extensions marketplaceCopy link

To enhance your development workflow with additional tools, browse the extensions marketplace in Red Hat Developer Hub (RHDH). Identifying available plugins allows you to see which integrations are currently active or which supported tools your team can request to improve project visibility and automation.

Procedure

Browse the marketplace to review the following categories:

- Installed: Review the integrations currently active in your instance, such as Jira, ArgoCD, or GitHub.
- Available: Examine additional integrations supported by the platform that are not yet enabled for your workspace.

Note

End users typically cannot install plugins. If you locate a plugin in the marketplace or the Backstage Plugin Directory that your team requires, contact your administrator to request its enablement.

## Chapter 10. Customize your RHDH interface settingsCopy link

Personalize your Red Hat Developer Hub (RHDH) environment to align the portal with your system preferences and optimize your workspace. Reviewing your user profile also confirms your account details and software ownership in the Software Catalog.

### 10.1. Customize your interface settings and profile detailsCopy link

Personalize your workspace appearance and verify that you are correctly associated with your team’s services.

Procedure

To customize the interface:

In the Appearance section, configure the following options:

- Theme: Select Light, Dark, or Auto to synchronize the portal with your operating system preferences.
- Language: Select your preferred interface language from the list.
- Pin Sidebar: Use this toggle to keep the left navigation menu expanded. This feature is enabled by default.

To verify your identity and software ownership:

Use the relations toggle to switch between the following views:

- Direct Relations: View entities where you are explicitly listed as the owner.
- Aggregated Relations: View entities inherited through team memberships or group hierarchies.

## Chapter 11. Managing starred items for quick accessCopy link

To streamline your daily workflow and reduce navigation time, use the starring feature in Red Hat Developer Hub (RHDH). By starring key components, APIs, and services in the Software Catalog, you create a personalized list of high-priority resources accessible directly from your sidebar or homepage.

This personalization makes sure that you can locate the specific tools and documentation you use most frequently without performing repetitive searches across the software ecosystem.

### 11.1. Starring key components in the Software CatalogCopy link

You can add frequently used components to the Your Starred Entities card for quick access.

Procedure

1. In the Red Hat Developer Hub navigation menu, select Catalog.
2. Locate the components you want to add as a favorite.
3. In the Actions column for that component, click the Add to favorites (star) icon.

Verification

- Navigate to the Home page and verify the component is listed in the Your Starred Entities card.

## Legal NoticeCopy link

Copyright © Red Hat.

Except as otherwise noted below, the text of and illustrations in this documentation are licensed by Red Hat under the Creative Commons Attribution–Share Alike 3.0 Unported license . If you distribute this document or an adaptation of it, you must provide the URL for the original version.

Red Hat, as the licensor of this document, waives the right to enforce, and agrees not to assert, Section 4d of CC-BY-SA to the fullest extent permitted by applicable law.

Red Hat, the Red Hat logo, JBoss, Hibernate, and RHCE are trademarks or registered trademarks of Red Hat, LLC. or its subsidiaries in the United States and other countries.

Linux® is the registered trademark of Linus Torvalds in the United States and other countries.

XFS is a trademark or registered trademark of Hewlett Packard Enterprise Development LP or its subsidiaries in the United States and other countries.

The OpenStack® Word Mark and OpenStack logo are trademarks or registered trademarks of the Linux Foundation, used under license.

All other trademarks are the property of their respective owners.

---

*Source: https://docs.redhat.com/en/documentation/red_hat_developer_hub/1.9/html-single/installing_red_hat_developer_hub_on_openshift_container_platform/index*

Installing Red Hat Developer Hub on OpenShift Container Platform | Red Hat Developer Hub | 1.9 | Red Hat Documentation

 

 

 

 

 

 

 

 

# Installing Red Hat Developer Hub on OpenShift Container Platform

---

Red Hat Developer Hub 1.9

## Running Red Hat Developer Hub on Red Hat OpenShift Container Platform by using either the Operator or Helm chart

Red Hat Customer Content Services

Legal Notice

Abstract

Platform administrators can configure roles, permissions, and other settings to enable other authorized users to deploy a Red Hat Developer Hub (RHDH) instance on Red Hat OpenShift Container Platform using either the Operator or Helm chart.

---

## PrefaceCopy link

You can install Red Hat Developer Hub on OpenShift Container Platform by using one of the following installers:

The Red Hat Developer Hub Operator

- Legal Notice
- Ready for immediate use in OpenShift Container Platform after an administrator installs it with OperatorHub
- Uses Operator Lifecycle Management (OLM) to manage automated subscription updates on OpenShift Container Platform
- Requires preinstallation of Operator Lifecycle Management (OLM) to manage automated subscription updates on Kubernetes

The Red Hat Developer Hub Helm chart

- Ready for immediate use in both OpenShift Container Platform and Kubernetes
- Requires manual installation and management

Important

You must set the`baseUrl` in`app-config.yaml` to match the external URL of your Developer Hub instance (for example,`https://rhdh.example.com`). This value is required for the Red Hat Developer Hub to function correctly. If it is not set, frontend and backend services cannot communicate properly, and features may not work as expected.

Use the installation method that best meets your needs and preferences.

Additional resources

- [Choosing an installation method: Helm Charts vs. Operators](https://www.redhat.com/en/technologies/cloud-computing/openshift/helm)
- [Working with Operators](https://docs.redhat.com/en/documentation/openshift_container_platform/4.20/html-single/operators/index)
- [Working with Helm charts](https://docs.redhat.com/en/documentation/openshift_container_platform/4.20/html-single/building_applications/index#working-with-helm-charts)

## Chapter 1. Installing Red Hat Developer Hub on OpenShift Container Platform with the OperatorCopy link

You can install Red Hat Developer Hub on OpenShift Container Platform by using the Red Hat Developer Hub Operator in the OpenShift Container Platform console.

### 1.1. Installing the Red Hat Developer Hub OperatorCopy link

As an administrator, you can install the Red Hat Developer Hub Operator. Authorized users can use the Operator to install Red Hat Developer Hub on Red Hat OpenShift Container Platform (OpenShift Container Platform) and supported Kubernetes platforms. For more information on supported platforms and versions, see the [Red Hat Developer Hub Life Cycle](https://access.redhat.com/support/policy/updates/developerhub) page.

Containers are available for the following CPU architectures:

- AMD64 and Intel 64 (`x86_64`)

Prerequisites

- You are logged in as an administrator on the OpenShift Container Platform web console.
- You have configured the appropriate roles and permissions within your project to create or access an application. For more information, see the [Red Hat OpenShift Container Platform documentation on Building applications](https://docs.redhat.com/en/documentation/openshift_container_platform/4.20/html-single/building_applications/index#building-applications-overview).
- You have installed Red Hat OpenShift Container Platform 4.16 to 4.20.
- Make sure that your system meets the minimum sizing requirements. See [Sizing requirements for Red Hat Developer Hub](https://docs.redhat.com/en/documentation/red_hat_developer_hub/1.9/html-single/about_red_hat_developer_hub/index#rhdh-sizing_about-rhdh).

Important

You can upgrade Red Hat Developer Hub directly from any previous version to the latest release without installing intermediate versions. However, you must review the release notes for every skipped version to identify breaking changes or required migration steps. For example, if upgrading from version 1.5 to 1.7, check the release notes for both 1.6 and 1.7.

Procedure

After the Operator is successfully installed, provision your custom configuration:

Before you create a Developer Hub instance, you must create the required config map and Secret resources in your project. These include the`baseUrl` and service-to-service authentication secrets.

For detailed steps, see [Provisioning your custom Red Hat Developer Hub configuration](https://docs.redhat.com/en/documentation/red_hat_developer_hub/1.9/html-single/configuring_red_hat_developer_hub/index#provisioning-and-using-your-custom-configuration).

From the Update channel drop-down menu, select the update channel that you want to use, for example, fast or fast-1.9.

Important

The `fast channel includes all of the updates available for a particular version. Any update might introduce unexpected changes in your Red Hat Developer Hub deployment. Check the release notes for details about any potentially breaking changes.

The fast-1.9 channel only provides z-stream updates, for example, updating from version 1.9.1 to 1.9.2. If you want to update the Red Hat Developer Hub y-version in the future, for example, updating from 1.9 to 1.10, you must switch to the fast-1.10 channel manually.

Select the Operator Installation mode.

Note

The All namespaces on the cluster (default) option is selected by default. The Specific namespace on the cluster option is not currently supported.

In the Installed Namespace field, do one of the following actions:

Select Select a Namespace to use an alternative namespace.

From the Select Project drop-down menu, do one of the following actions:

Select Create Project to create a new project for the Operator.

On the Create Project dialog, enter text into the required fields and click Create.

Important

For enhanced security, better control over the Operator lifecycle, and preventing potential privilege escalation, install the Red Hat Developer Hub Operator in a dedicated default`rhdh-operator` namespace. You can restrict other users' access to the Operator resources through role bindings or cluster role bindings.

You can also install the Operator in another namespace by creating the necessary resources, such as an Operator group. For more information, see [Installing global Operators in custom namespaces](https://docs.redhat.com/en/documentation/openshift_container_platform/4.20/html-single/operators/index#olm-installing-global-namespaces_olm-adding-operators-to-a-cluster).

However, if the Red Hat Developer Hub Operator shares a namespace with other Operators, then it shares the same update policy as well, preventing the customization of the update policy. For example, if one Operator is set to manual updates, the Red Hat Developer Hub Operator update policy is also set to manual. For more information, see [Colocation of Operators in a namespace](https://docs.redhat.com/en/documentation/openshift_container_platform/4.20/html-single/operators/index#olm-colocation-namespaces_olm-colocation).

Select the Update approval method for the Operator.

- If you select the Automatic option, the Operator is updated without requiring manual confirmation.
- If you select the Manual option, a notification opens when a new update is released in the update channel. The update must be manually approved by an administrator before installation can begin.

Click Install.

Note

If you selected a Manual approval strategy, the upgrade status of the subscription remains Upgrading until you review and approve the install plan. After you click Approve on the Install Plan page, the subscription upgrade status changes to Up to date.

If you selected an Automatic approval strategy, the upgrade status should resolve to Up to date without intervention.

Verification

From the dialog box, do one of the following actions:

Click View all installed operators to open the Installed Operators page.

- From the list of installed Operators, locate the Red Hat Developer Hub Operator name and details.
- Click Red Hat Developer Hub Operator to open the Operator details page for the Red Hat Developer Hub Operator.

### 1.2. Deploying Red Hat Developer Hub on OpenShift Container Platform with the OperatorCopy link

As a developer, you can deploy a Red Hat Developer Hub instance on OpenShift Container Platform by using the Developer Catalog in the Red Hat OpenShift Container Platform web console. This deployment method uses the Red Hat Developer Hub Operator.

Prerequisites

- You have set the`baseUrl` in your`app-config.yaml` to match the external URL of your Developer Hub instance. Without it, frontend and backend services cannot communicate, and features might not work as expected.
- An OpenShift Container Platform administrator has installed the Red Hat Developer Hub Operator.
- [You have provisioned your custom config maps and secrets in your project](https://docs.redhat.com/en/documentation/red_hat_developer_hub/1.9/html-single/configuring_red_hat_developer_hub/index#provisioning-your-custom-configuration).
- [You have authored your Backstage custom resource](https://docs.redhat.com/en/documentation/red_hat_developer_hub/1.9/html-single/configuring_red_hat_developer_hub/index#using-the-operator-to-run-rhdh-with-your-custom-configuration).

Procedure

Provision your custom configuration using the following template:

```yaml
apiVersion: v1
kind: ConfigMap
metadata:
  name: app-config-rhdh
data:
  "app-config.yaml": |
    app:
      title: Red Hat Developer Hub
      baseUrl: https://__&lt;my_developer_hub_domain&gt;__
    backend:
      auth:
        externalAccess:
            - type: legacy
              options:
                subject: legacy-default-config
                secret: "${BACKEND_SECRET}"
      baseUrl: https://__&lt;my_developer_hub_domain&gt;__
      cors:
        origin: https://__&lt;my_developer_hub_domain&gt;__
```

Use a config map named`app-config-rhdh` to provide your`app-config.yaml` file, and a Secret for service-to-service authentication (such as`BACKEND_SECRET`).

To create and apply these configuration resources, follow the steps in [Provisioning your custom Red Hat Developer Hub configuration](https://docs.redhat.com/en/documentation/red_hat_developer_hub/1.9/html-single/configuring_red_hat_developer_hub/index#provisioning-and-using-your-custom-configuration) for the full procedure.

Note

The`app-config-rhdh` config map must include your customized`app-config.yaml` file. This config map is mounted into the Developer Hub container at runtime.

Create a secret named`my-rhdh-secrets` and add a key named`BACKEND_SECRET` with a`Base64-encoded` string as value, as shown in the following example:

```yaml
apiVersion: v1
kind: Secret
metadata:
  name: my-rhdh-secrets
stringData:
  # TODO: Add the necessary auth secrets for service-to-service auth setup
  BACKEND_SECRET: "xxx" # Replace with your Base64-encoded secret
```

Note

Ensure that your secret includes`BACKEND_SECRET`, used for service-to-service authentication. For structure and provisioning steps, see [Provisioning your custom Red Hat Developer Hub configuration](https://docs.redhat.com/en/documentation/red_hat_developer_hub/1.9/html-single/configuring_red_hat_developer_hub/index#provisioning-and-using-your-custom-configuration).

1. In the OpenShift Container Platform web console, select your`<{my_product_namespace}>` project, then click Add.
2. From the Developer Catalog panel, click Operator Backed.
3. In the Filter by keyword box, enter Developer Hub and click the Red Hat Developer Hub card.
4. Click Create.
5. [Add your Backstage custom resource content](https://docs.redhat.com/en/documentation/red_hat_developer_hub/1.9/html-single/configuring_red_hat_developer_hub/index#using-the-operator-to-run-rhdh-with-your-custom-configuration).
6. On the Create Backstage page, click Create.

Verification

After the pods are ready, you can access the Red Hat Developer Hub platform by opening the URL.

From the Topology view, click the Open URL icon on the Developer Hub pod.

1. Confirm that the pods are ready by clicking the pod in the Topology view and confirming the Status in the Details panel. The pod status is Active when the pod is ready.

## Chapter 2. Installing Red Hat Developer Hub on OpenShift Container Platform with the Helm chartCopy link

You can install Red Hat Developer Hub on OpenShift Container Platform by using the Helm chart with one of the following methods:

- The OpenShift Container Platform console
- The Helm CLI

### 2.1. Deploying Developer Hub from the OpenShift Container Platform web console with the Helm ChartCopy link

You can use a Helm chart to install Developer Hub on the Red Hat OpenShift Container Platform web console.

Helm is a package manager on OpenShift Container Platform that provides the following features:

- Applies regular application updates using custom hooks
- Manages the installation of complex applications
- Provides charts that you can host on public and private servers
- Supports rolling back to previous application versions

The Red Hat Developer Hub Helm chart is available in the Helm catalog on OpenShift Dedicated and OpenShift Container Platform.

Prerequisites

- You are logged in to your OpenShift Container Platform account.
- A user with the OpenShift Container Platform`admin` role has configured the appropriate roles and permissions within your project to create an application. For more information about OpenShift Container Platform roles, see [Using RBAC to define and apply permissions](https://docs.redhat.com/en/documentation/openshift_container_platform/4.20/html-single/authentication_and_authorization/index#authorization-overview_using-rbac).
- You have created a project in OpenShift Container Platform. For more information about creating a project in OpenShift Container Platform, see [Red Hat OpenShift Container Platform documentation](https://docs.redhat.com/en/documentation/openshift_container_platform/4.20/html-single/building_applications/index#working-with-projects).
- Make sure that your system meets the minimum sizing requirements. See [Sizing requirements for Red Hat Developer Hub](https://docs.redhat.com/en/documentation/red_hat_developer_hub/1.9/html-single/about_red_hat_developer_hub/index#rhdh-sizing_about-rhdh).

Procedure

Select the radio button to configure the Developer Hub instance with either the form view or YAML view. The Form view is selected by default.

Using Form view

1. To configure the instance with the Form view, go to Root Schema → global → Enable service authentication within Backstage instance and paste your OpenShift Container Platform router host into the field on the form.

Using YAML view

To configure the instance with the YAML view, paste your OpenShift Container Platform router hostname in the`global.clusterRouterBase` parameter value as shown in the following example:

```yaml
global:
  auth:
    backend:
      enabled: true
  clusterRouterBase: apps.<clusterName>.com
```

Edit the other values if needed.

Note

The information about the host is copied and can be accessed by the Developer Hub backend.

When an OpenShift Container Platform route is generated automatically, the host value for the route is inferred and the same host information is sent to the Developer Hub. Also, if the Developer Hub is present on a custom domain by setting the host manually using values, the custom host takes precedence.

Click the Open URL icon to start using the Developer Hub platform.

Note

Your`developer-hub` pod might be in a`CrashLoopBackOff` state if the Developer Hub container cannot access the configuration files. This error is indicated by the following log:

```plaintext
Loaded config from app-config-from-configmap.yaml, env
...
2023-07-24T19:44:46.223Z auth info Configuring "database" as KeyStore provider type=plugin
Backend failed to start up Error: Missing required config value at 'backend.database.client'
```

To resolve the error, verify the configuration files.

### 2.2. Deploying Developer Hub on OpenShift Container Platform with the Helm CLICopy link

You can use the Helm CLI to install Red Hat Developer Hub on Red Hat OpenShift Container Platform.

Prerequisites

- You have installed the OpenShift CLI (`oc`) on your workstation.
- You are logged in to your OpenShift Container Platform account.
- A user with the OpenShift Container Platform admin role has configured the appropriate roles and permissions within your project to create an application. For more information about OpenShift Container Platform roles, see [Using RBAC to define and apply permissions](https://docs.redhat.com/en/documentation/openshift_container_platform/4.20/html-single/authentication_and_authorization/index#authorization-overview_using-rbac).
- You have created a project in OpenShift Container Platform. For more information about creating a project in OpenShift Container Platform, see [Red Hat OpenShift Container Platform documentation](https://docs.redhat.com/en/documentation/openshift_container_platform/4.20/html-single/building_applications/index#working-with-projects).
- You have installed the Helm CLI tool.

Procedure

Create and activate the OpenShift Container Platform project:

```plaintext
NAMESPACE=<emphasis><rhdh></emphasis>
oc new-project ${NAMESPACE} || oc project ${NAMESPACE}
```

Install the Red Hat Developer Hub Helm chart:

```plaintext
helm upgrade redhat-developer-hub -i https://github.com/openshift-helm-charts/charts/releases/download/redhat-redhat-developer-hub-1.9.2/redhat-developer-hub-1.9.2.tgz
```

Configure your Developer Hub Helm chart instance with the Developer Hub database password and router base URL values from your OpenShift Container Platform cluster:

```plaintext
PASSWORD=$(oc get secret redhat-developer-hub-postgresql -o jsonpath="{.data.password}" | base64 -d)
CLUSTER_ROUTER_BASE=$(oc get route console -n openshift-console -o=jsonpath='{.spec.host}' | sed 's/^[^.]*\.//')
helm upgrade redhat-developer-hub -i "https://github.com/openshift-helm-charts/charts/releases/download/redhat-redhat-developer-hub-1.9.2/redhat-developer-hub-1.9.2.tgz" \
    --set global.clusterRouterBase="$CLUSTER_ROUTER_BASE" \
    --set global.postgresql.auth.password="$PASSWORD"
```

Display the running Developer Hub instance URL:

```plaintext
echo "https://redhat-developer-hub-$NAMESPACE.$CLUSTER_ROUTER_BASE"
```

Verification

- Open the running Developer Hub instance URL in your browser to use Developer Hub.

Additional resources

- [Installing Helm](https://docs.redhat.com/en/documentation/openshift_dedicated/4/html-single/building_applications/index#installing-helm)

## Legal NoticeCopy link

Copyright © Red Hat.

Except as otherwise noted below, the text of and illustrations in this documentation are licensed by Red Hat under the Creative Commons Attribution–Share Alike 3.0 Unported license . If you distribute this document or an adaptation of it, you must provide the URL for the original version.

Red Hat, as the licensor of this document, waives the right to enforce, and agrees not to assert, Section 4d of CC-BY-SA to the fullest extent permitted by applicable law.

Red Hat, the Red Hat logo, JBoss, Hibernate, and RHCE are trademarks or registered trademarks of Red Hat, LLC. or its subsidiaries in the United States and other countries.

Linux® is the registered trademark of Linus Torvalds in the United States and other countries.

XFS is a trademark or registered trademark of Hewlett Packard Enterprise Development LP or its subsidiaries in the United States and other countries.

The OpenStack® Word Mark and OpenStack logo are trademarks or registered trademarks of the Linux Foundation, used under license.

All other trademarks are the property of their respective owners.

---

*Source: https://docs.redhat.com/en/documentation/red_hat_developer_hub/1.9/html-single/configuring_red_hat_developer_hub/index*

Configuring Red Hat Developer Hub | Red Hat Developer Hub | 1.9 | Red Hat Documentation

 

 

 

 

 

 

 

 

# Configuring Red Hat Developer Hub

---

Red Hat Developer Hub 1.9

## Adding custom config maps and secrets to configure your Red Hat Developer Hub instance to work in your IT ecosystem

Red Hat Customer Content Services

Legal Notice

Abstract

Learn how to configure Red Hat Developer Hub (RHDH) for production to work in your IT ecosystem by adding custom config maps and secrets.

---

## PrefaceCopy link

Learn how to configure Red Hat Developer Hub (RHDH) for production to work in your IT ecosystem by adding custom config maps and secrets.

## Chapter 1. Provisioning and using your custom Red Hat Developer Hub configurationCopy link

To configure Red Hat Developer Hub, use these methods, which are widely used to configure a Red Hat OpenShift Container Platform application:

- Legal Notice
- Use config maps to mount files and directories.
- Use secrets to inject environment variables.

Learn to apply these methods to Developer Hub:

Use your selected deployment method to mount the config maps and inject the secrets:

- Use the Red Hat Developer Hub operator to deploy Developer Hub.
- Use the Red Hat Developer Hub Helm chart to deploy Developer Hub.

### 1.1. Provisioning your custom Red Hat Developer Hub configurationCopy link

To configure Red Hat Developer Hub, provision your custom Red Hat Developer Hub config maps and secrets to Red Hat OpenShift Container Platform (RHOCP) before running Red Hat Developer Hub.

Tip

On Red Hat OpenShift Container Platform, you can skip this step to run Developer Hub with the default config map and secret. Your changes on this configuration might get reverted on Developer Hub restart.

Prerequisites

- By using the [OpenShift CLI (oc)](https://docs.redhat.com/en/documentation/openshift_container_platform/4.20/html-single/cli_tools/index#cli-about-cli_cli-developer-commands), you have access, with developer permissions, to the OpenShift cluster aimed at containing your Developer Hub instance.

Procedure

For security, store your secrets as environment variables values in an OpenShift Container Platform secret, rather than in clear text in your configuration files. Collect all your secrets in the`secrets.txt` file, with one secret per line in`KEY=value` form.

- [Enter your authentication secrets](https://docs.redhat.com/en/documentation/red_hat_developer_hub/1.9/html-single/authentication_in_red_hat_developer_hub/index).

Author your custom`app-config.yaml` file. This is the main Developer Hub configuration file. You need a custom`app-config.yaml` file to avoid the Developer Hub installer to revert user edits during upgrades. When your custom`app-config.yaml` file is empty, Developer Hub is using default values.

To prepare a deployment with the Red Hat Developer Hub Helm chart, or on Kubernetes, enter the Developer Hub base URL in the relevant fields in your`app-config.yaml` file to ensure proper functionality of Developer Hub. The base URL is what a Developer Hub user sees in their browser when accessing Developer Hub. The relevant fields are`baseUrl` in the`app` and`backend` sections, and`origin` in the`backend.cors` subsection:

Example 1.1. Configuring the`baseUrl` in`app-config.yaml`

```yaml
app:
  title: Red Hat Developer Hub
  baseUrl: https://<my_developer_hub_domain>

backend:
  auth:
    externalAccess:
      - type: legacy
        options:
          subject: legacy-default-config
          secret: "${BACKEND_SECRET}"
  baseUrl: https://<my_developer_hub_domain>
  cors:
    origin: https://<my_developer_hub_domain>
```

Optionally, enter your configuration such as:

- [Authentication in Red Hat Developer Hub](https://docs.redhat.com/en/documentation/red_hat_developer_hub/1.9/html-single/authentication_in_red_hat_developer_hub/index).
- [Authorization in Red Hat Developer Hub](https://docs.redhat.com/en/documentation/red_hat_developer_hub/1.9/html-single/authorization_in_red_hat_developer_hub/index).
- [Customization](https://docs.redhat.com/en/documentation/red_hat_developer_hub/1.9/html-single/customizing_red_hat_developer_hub/index).
- [Configure your OpenShift Container Platform integration](https://docs.redhat.com/en/documentation/red_hat_developer_hub/1.9/html-single/customizing_red_hat_developer_hub/index#configuring-an-rhdh-instance-with-tls-in-kubernetes_provisioning-and-using-your-custom-configuration).

Author your custom`dynamic-plugins.yaml` file to enable plugins. By default, Developer Hub enables a minimal plugin set, and disables plugins that require configuration or secrets, such as the GitHub repository discovery plugin and the Role-based access control (RBAC) plugin.

Enable the GitHub repository discovery and the RBAC features:

`dynamic.plugins.yaml`

```yaml
includes:
  - dynamic-plugins.default.yaml
plugins:
  - package: ./dynamic-plugins/dist/backstage-plugin-catalog-backend-module-github
    disabled: false
  - package: ./dynamic-plugins/dist/backstage-community-plugin-rbac
    disabled: false
```

Provision your custom configuration files to your OpenShift Container Platform cluster.

Create the {namespace} aimed at containing your Developer Hub instance.

```shell-session
$ oc create namespace my-rhdh-project
```

Provision your`app-config.yaml` and`dynamic-plugins.yaml` files respectively to the`my-rhdh-app-config`, and`dynamic-plugins-rhdh` config maps in the project.

```shell-session
$ oc create configmap my-rhdh-app-config --from-file=app-config.yaml --namespace=my-rhdh-project
$ oc create configmap dynamic-plugins-rhdh --from-file=dynamic-plugins.yaml --namespace=my-rhdh-project
```

Alternatively, [create the config maps by using the web console](https://docs.redhat.com/en/documentation/openshift_container_platform/4.20/html-single/nodes/index#nnodes-pods-configmap-create-from-console_configmaps).

Provision your`secrets.txt` file to the`my-rhdh-secrets` secret in the project.

```shell-session
$ oc create secret generic my-rhdh-secrets --from-file=secrets.txt --namespace=my-rhdh-project
```

Alternatively, [create the secret by using the web console](https://docs.redhat.com/en/documentation/openshift_container_platform/4.20/html-single/nodes/index#nodes-pods-secrets-creating-web-console-secrets_nodes-pods-secrets).

Next steps

- [Provision your PostgreSQL database secrets](https://docs.redhat.com/en/documentation/red_hat_developer_hub/1.9/html-single/configuring_red_hat_developer_hub/index#configuring-external-postgresql-databases)
- [Provision your dynamic plugins config map](https://docs.redhat.com/en/documentation/red_hat_developer_hub/1.9/html-single/installing_and_viewing_plugins_in_red_hat_developer_hub/index)
- [Provision your RBAC policies config map](https://docs.redhat.com/en/documentation/red_hat_developer_hub/1.9/html-single/authorization_in_red_hat_developer_hub/index#managing-authorizations-by-using-external-files)

### 1.2. Using the Red Hat Developer Hub Operator to run Developer Hub with your custom configurationCopy link

To use the Developer Hub Operator to run Red Hat Developer Hub with your custom configuration, create your Backstage custom resource (CR) that:

- Mounts files provisioned in your custom config maps.
- Injects environment variables provisioned in your custom secrets.

Prerequisites

- By using the [OpenShift CLI (oc)](https://docs.redhat.com/en/documentation/openshift_container_platform/4.20/html-single/cli_tools/index#cli-about-cli_cli-developer-commands), you have access, with developer permissions, to the OpenShift Container Platform cluster aimed at containing your Developer Hub instance.
- Your administrator has installed the Red Hat Developer Hub Operator in the cluster.
- You have provisioned your custom config maps and secrets in your` ` project.
- You have a working default storage class, such as the EBS storage add-on, configured in your EKS cluster.

Procedure

Author your Backstage CR in a`my-rhdh-custom-resource.yaml` file to use your custom config maps and secrets.

Minimal`my-rhdh-custom-resource.yaml` custom resource example:

```yaml
apiVersion: rhdh.redhat.com/v1alpha5
kind: Backstage
metadata:
  name: my-rhdh-custom-resource
spec:
  application:
    appConfig:
      mountPath: /opt/app-root/src
      configMaps:
         - name: my-rhdh-app-config
    extraEnvs:
      secrets:
         - name: <my_product_secrets>
    extraFiles:
      mountPath: /opt/app-root/src
    route:
      enabled: true
  database:
    enableLocalDb: true
```

`my-rhdh-custom-resource.yaml` custom resource example with dynamic plugins and RBAC policies config maps, and external PostgreSQL database secrets:

```yaml
apiVersion: rhdh.redhat.com/v1alpha5
kind: Backstage
metadata:
  name: <my-rhdh-custom-resource>
spec:
  application:
    appConfig:
      mountPath: /opt/app-root/src
      configMaps:
         - name: my-rhdh-app-config
         - name: rbac-policies
    dynamicPluginsConfigMapName: dynamic-plugins-rhdh
    extraEnvs:
      secrets:
         - name: <my_product_secrets>
         - name: my-rhdh-database-secrets
    extraFiles:
      mountPath: /opt/app-root/src
      secrets:
        - name: my-rhdh-database-certificates-secrets
          key: postgres-crt.pem, postgres-ca.pem, postgres-key.key
    route:
      enabled: true
  database:
    enableLocalDb: false
```

Mandatory fields No fields are mandatory. You can create an empty Backstage CR and run Developer Hub with the default configuration. Optional fields

`spec.application.appConfig.configMaps` Enter your config map name list.

Mount files in the`my-rhdh-app-config` config map:

```yaml
spec:
  application:
    appConfig:
      mountPath: /opt/app-root/src
      configMaps:
         - name: my-rhdh-app-config
```

Mount files in the`my-rhdh-app-config` and`rbac-policies` config maps:

```yaml
spec:
  application:
    appConfig:
      mountPath: /opt/app-root/src
      configMaps:
         - name: my-rhdh-app-config
         - name: rbac-policies
```

`spec.application.extraEnvs.envs`

Optionally, enter your additional environment variables that are not secrets, such as [your proxy environment variables](https://docs.redhat.com/en/documentation/red_hat_developer_hub/1.9/html-single/configuring_red_hat_developer_hub/index#proc-configuring-proxy-in-operator-deployment_running-behind-a-proxy).

Inject your`HTTP_PROXY`,`HTTPS_PROXY` and`NO_PROXY` environment variables:

```yaml
spec:
  application:
    extraEnvs:
      envs:
        - name: HTTP_PROXY
          value: 'http://10.10.10.105:3128'
        - name: HTTPS_PROXY
          value: 'http://10.10.10.106:3128'
        - name: NO_PROXY
          value: 'localhost,example.org'
```

`spec.application.extraEnvs.secrets`

Enter your environment variables secret name list.

Inject the environment variables in your Red Hat Developer Hub secret:

```yaml
spec:
  application:
    extraEnvs:
      secrets:
         - name: <my_product_secrets>
```

Inject the environment variables in the Red Hat Developer Hub and`my-rhdh-database-secrets` secrets:

```yaml
spec:
  application:
    extraEnvs:
      secrets:
         - name: <my_product_secrets>
         - name: my-rhdh-database-secrets
```

Note

` ` is your preferred Developer Hub secret name, specifying the identifier for your secret configuration within Developer Hub.

`spec.application.extraFiles.secrets`

Enter your certificates files secret name and files list.

Mount the`postgres-crt.pem`,`postgres-ca.pem`, and`postgres-key.key` files contained in the`my-rhdh-database-certificates-secrets` secret:

```yaml
spec:
  application:
    extraFiles:
      mountPath: /opt/app-root/src
      secrets:
        - name: my-rhdh-database-certificates-secrets
          key: postgres-crt.pem, postgres-ca.pem, postgres-key.key
```

`spec.database.enableLocalDb`

Enable or disable the local PostgreSQL database.

Disable the local PostgreSQL database generation to use an external postgreSQL database:

```yaml
spec:
  database:
    enableLocalDb: false
```

On a development environment, use the local PostgreSQL database:

```yaml
spec:
  database:
    enableLocalDb: true
```

`spec.deployment` Optionally, [enter your deployment configuration](https://docs.redhat.com/en/documentation/red_hat_developer_hub/1.9/html-single/configuring_red_hat_developer_hub/index#configuring-the-deployment).

Apply your Backstage CR to start or update your Developer Hub instance:

```shell-session
$ oc apply --filename=my-rhdh-custom-resource.yaml --namespace=my-rhdh-project
```

#### 1.2.1. Injecting extra files and environment variables into Backstage containersCopy link

The`mountPath` field specifies the location where a ConfigMap or Secret is mounted. The behavior of the mount, whether it includes or excludes a`subPath`, depends on the specification of the`key` or`mountPath` fields.

- If`key` and`mountPath` are not specified: Each key or value is mounted as a`filename` or content with a`subPath`.
- If`key` is specified with or without`mountPath`: The specified key or value is mounted with a`subPath`.
- If only`mountPath` is specified: A directory containing all the keys or values is mounted without a`subPath`.
- If the`containers` field is not specified: The volume mounts only to the`backstage-backend` container. By default, files mount only to the`backstage-backend` container. You can also specify other targets, including a list of containers by name (such as`dynamic-plugin-install` or`selectcustom` sidecars) or select all containers in the Backstage Pod.

Note

- OpenShift Container Platform does not automatically update a volume mounted with`subPath`. By default, the RHDH Operator monitors these ConfigMaps or Secrets and refreshes the RHDH Pod when changes occur.
- For security purposes, Red Hat Developer Hub does not give the Operator Service Account read access to Secrets. As a result, mounting files from Secrets without specifying both mountPath and key is not supported.

Procedure

Apply the configuration to your`Backstage custom resource (CR)`. The following code block is an example:

```yaml
spec:
  application:
    extraFiles:
      mountPath: _<default_mount_path>_
      configMaps:
        - name: _<configmap_name_all_entries>_
        - name: _<configmap_name_single_key>_
          key: _<specific_file_key>_
          containers:
            - "*"
        - name: _<configmap_name_custom_path>_
          mountPath: _<custom_cm_mount_path>_
          containers:
            - backstage-backend
            - install-dynamic-plugins
      secrets:
        - name: _<secret_name_single_key>_
          key: _<specific_secret_key>_
          containers:
            - install-dynamic-plugins
        - name: _<secret_name_custom_path>_
          mountPath: _<custom_secret_mount_path>_
      pvcs:
        - name: _<pvc_name_default_path>_
        - name: _<pvc_name_custom_path>_
          mountPath: _<custom_pvc_mount_path>_
    extraEnvs:
      configMaps:
        - name: _<configmap_name_env_var>_
          key: _<env_var_key>_
          containers:
            - "*"
      secrets:
        - name: _<secret_name_all_envs>_
      envs:
        - name: _<static_env_var_name>_
          value: "_<static_env_var_value>_"
          containers:
           - install-dynamic-plugins
```

where:

`spec.application.extraFiles.mountPath` Specifies the default base mount path for files if no specific`mountPath` is set for a resource (for example,`/ `).`spec.application.extraFiles.configMaps.name` Mounts all entries from` ` to the default mount path.`spec.application.extraFiles.configMaps.key` Mounts **only the specified key (for example,`.txt`) from the ConfigMap.`spec.application.extraFiles.configMaps.containers` Targets all containers (`"*"`) for mounting.`spec.application.extraFiles.configMaps.mountPath` Overrides the default and mounts all ConfigMap entries as a directory at the specified path (for example,`/ `).`spec.application.extraFiles.secrets.key` Mounts only a specific key from the Secret.`spec.application.extraFiles.secrets.mountPath` Overrides the default and mounts all Secret entries as a directory at the specified path (for example,`/ `).`spec.application.extraFiles.pvcs.name` Mounts the PVC to the default mount path, appending the PVC name (for example,`/ / `).`spec.application.extraFiles.pvcs.mountPath` Overrides the default and mounts the PVC to the specified path (for example,`/ `).`spec.application.extraEnvs.configMaps.containers` Injects the specified ConfigMap key as an environment variable into all containers (`"*"`).`spec.application.extraEnvs.secrets.name` Injects all keys from the Secret as environment variables into the default container.`spec.application.envs.containers` Targets only the listed container for the static environment variable injection.

Note

The following explicit options are supported:

- No or an empty field: Mounts only to the`backstage-backend` container.
- `*`(asterisk) as the first and only array element: Mounts to all containers.
- Explicit container names, for example,`install-dynamic-plugins`: Mounts only to the listed containers.

Verification

The files are mounted with the following correct paths and container targets:

Expand

ConfigMap (` `)

File

`/ / `,`/ / `

`backstage-backend`

ConfigMap (` `)

File

`/ /.txt`

All

ConfigMap (` `)

Directory

`/ /`

`backstage-backend`,`install-dynamic-plugins`

Secret (` `)

File

`/ /.txt`

`install-dynamic-plugins`

Secret (` `)

Directory

`/ /`

`backstage-backend`

PVC (` `)

Directory

`/ / `

`backstage-backend`

ConfigMap (` `)

Env Var

` `

All

Secret (` `)

Env Var

` `,` `

`backstage-backend`

CRD (`envs`)

Env Var

` = `

`install-dynamic-plugins`

| | Resource | Target type | Path(s) or name(s) | Container(s) |
| --- | --- | --- | --- | --- |

Show more

### 1.3. Using the Red Hat Developer Hub Helm chart to run Developer Hub with your custom configurationCopy link

You can use the Red Hat Developer Hub Helm chart to add a custom application configuration file to your OpenShift Container Platform instance.

Prerequisites

- By using the OpenShift Container Platform web console, you have access with developer permissions, to [an OpenShift Container Platform project](https://docs.redhat.com/en/documentation/openshift_container_platform/4.20/html-single/building_applications/index#working-with-projects) named, aimed at containing your Developer Hub instance.
- You have uploaded your custom configuration files and secrets in your` ` project.

Procedure

Configure Helm to use your custom configuration files in Developer Hub.

Set the value of the`upstream.backstage.extraAppConfig.configMapRef` and`upstream.backstage.extraAppConfig.filename` parameters as follows:

Helm configuration excerpt

```yaml
upstream:
  backstage:
    extraAppConfig:
      - configMapRef: my-rhdh-app-config
        filename: app-config.yaml
```

1. Go to the Helm tab to see the list of Helm releases.
2. Click the overflow menu on the Helm release that you want to use and select Upgrade.
3. Use the YAML view to edit the Helm configuration.
4. Click Upgrade.

Next steps

- Install Developer Hub by using Helm.

## Chapter 2. Red Hat Developer Hub default configurationCopy link

You can deploy a standard Red Hat Developer Hub (RHDH) instance, understand the structure, and tailor RHDH instance to meet your needs.

### 2.1. Red Hat Developer Hub default configuration guideCopy link

The Red Hat Developer Hub (RHDH) Operator creates a set of Kubernetes resources to deploy and manage a Backstage instance. The default configuration for these default resources is defined at the Operator level and can be customized for a specific instance using the Backstage Custom Resource (CR). This approach provides a clear starting point while offering flexibility to tailor each deployment.

The default configuration is stored in a ConfigMap named`rhdh-default-config` located in the`rhdh-operator` namespace on OpenShift. This ConfigMap contains the YAML manifests that define the foundational structure of the RHDH instance.

You can create a basic RHDH instance by applying an empty Backstage Custom Resource as follows:

Example creating a RHDH instance

```yaml
apiVersion: backstage.redhat.com/v1alpha4
kind: Backstage
metadata:
name: my-rhdh-instance
namespace: rhdh
```

The Operator automatically creates the following resources in the specified RHDH namespace by default based on the default configuration:

Expand

`deployment.yaml`

`apps/v1/Deployment`

`backstage-{cr-name}`

(Mandatory) The main Backstage application deployment.

`service.yaml`

`v1/Service`

`backstage-{cr-name}`

(Mandatory) The Backstage application service.

`db-statefulset.yaml`

`apps/v1/StatefulSet`

`backstage-psql-{cr-name}`

The PostgreSQL database stateful set. Needed if`spec.enabledDb=true`.

`db-service.yaml`

`v1/Service`

`backstage-psql-{cr-name}`

The PostgreSQL database service. Needed if`spec.enabledDb=true`.

`db-secret.yaml`

`v1/Secret`

`backstage-psql-{cr-name}`

The PostgreSQL database credentials secret. Needed if`spec.enabledDb=true`.

`route.yaml`

`route.openshift.io/v1`

`backstage-{cr-name}`

The OpenShift Route to expose Backstage externally. (Optional) Applied to OpenShift only.

`app-config.yaml`

`v1/ConfigMap`

`backstage-config-{cr-name}`

(Optional) Specifies one or more Backstage`app-config.yaml` files.

`configmap-files.yaml`

`v1/ConfigMap`

`backstage-files-{cr-name}`

(Optional) Specifies additional ConfigMaps to be mounted as files into Backstage Pod.

`configmap-envs.yaml`

`v1/ConfigMap`

`backstage-envs-{cr-name}`

(Optional) Specifies additional ConfigMaps to be exposed as environment variables into Backstage Pod.

`secret-files.yaml`

`v1/Secret` or list of`v1/Secret`

`backstage-files-{cr-name}-{secret-name}`

(Optional) Specifies additional Secrets to be mounted as files into Backstage Pod.

`secret-envs.yaml`

`v1/Secret` or list of`v1/Secret`

`backstage-envs-{cr-name}`

(Optional) Specifies additional Secrets to be exposed as environment variables into Backstage Pod.

`dynamic-plugins.yaml`

`v1/ConfigMap`

`backstage-dynamic-plugins-{cr-name}`

(Optional) Specifies the dynamic plugins that the Operator installs into the Backstage instance.

`pvcs.yaml`

list of`v1/PersistentVolumeClaim`

`backstage-{cr-name}-{pvc-name}`

(Optional) The Persistent Volume Claim for PostgreSQL database.

| Table 2.1. Floating action button parameters | File Name | Resource GVK | Resource Name | Description |
| --- | --- | --- | --- | --- |

Show more

Note

`{cr-name}` is the name of the Backstage Custom Resource, for example 'my-rhdh-instance' in the above example.

### 2.2. Automated Operator featuresCopy link

You can use the Operator to automate several key processes to effectively configure your Backstage application.

#### 2.2.1. Metadata generationCopy link

The Operator automatically generates specific metadata values for all default resources at runtime to ensure your Backstage application functions properly.

For all the default resources,`metadata.name` is generated according to the rules defined in the Default Configuration files, particularly the Resource name column. For example, Backstage Custom Resource (CR) named`mybackstage` creates Kubernetes Deployment resource called`backstage-mybackstage`.

The following metadata is generated for each resource:

`deployment.yaml`

- `spec.selector.matchLabels[rhdh.redhat.com/app] = backstage-{cr-name}`
- `spec.template.metadata.labels[rhdh.redhat.com/app] = backstage-{cr-name}`

`service.yaml`

- `spec.selector[rhdh.redhat.com/app] = backstage-{cr-name}`

`db-statefulset.yaml`

- `spec.selector.matchLabels[rhdh.redhat.com/app] = backstage-psql-{cr-name}`
- `spec.template.metadata.labels[rhdh.redhat.com/app] = backstage-psql-{cr-name}`

`db-service.yaml`

- `spec.selector[rhdh.redhat.com/app] = backstage-psql-{cr-name}`

#### 2.2.2. Multiple resourcesCopy link

You can define and create multiple resources of the same type in a single YAML file. This is applicable to any resource type that is a`list` in the resource table. To define multiple resources, use the`---` delimiter to separate each resource definition.

For example, adding the following code snip to`pvcs.yaml` creates two PersistentVolumeClaims (PVCs) called`backstage-{cr-name}-myclaim1` and`backstage-{cr-name}-myclaim2` and mounts them to the Backstage container accordingly.

Example creating multiple PVCs

```yaml
apiVersion: v1
kind: PersistentVolumeClaim
metadata:
  name: myclaim1
...
---
apiVersion: v1
kind: PersistentVolumeClaim
metadata:
  name: myclaim2
...
```

#### 2.2.3. Default base URLsCopy link

The Operator automatically sets the base URLs for your Backstage application in the default`app-config` ConfigMap known as`backstage-appconfig-{CR_name}`. The Operator does so based on your Route parameters and the OpenShift cluster ingress domain.

The Operator follows these rules to set the base URLs for your application:

- If the cluster is not OpenShift, the Operator makes no changes.
- If you explicitly set the`spec.application.route.enabled` field in your Custom Resource (CR) to`false`, no changes are made.
- If you define`spec.application.route.host` in the Backstage CR, the base URLs are set to`https:// `.
- If you specify the`spec.application.route.subdomain` in the Backstage CR, the base URLs are set to`https://. `.
- If no custom host or subdomain is provided, the Operator sets the base URLs to`https://backstage- -. `, which is the default domain for the created Route resource.

The Operator updates the following base URLs in the default`app-config` ConfigMap:

- `app.baseUrl`
- `backend.baseUrl`
- `backend.cors.origin`

Note

You can perform these actions on a best-effort basis and only on OpenShift. During an error or on non-OpenShift clusters, you can still override these defaults by providing a custom`app-config` ConfigMap.

### 2.3. Mounts for default Secrets and Persistent Volume Claims (PVCs)Copy link

You can use annotations to configure mount paths and specify containers for Secrets and Persistent Volume Claims (PVCs) that are attached to the Operator default resources in your Red Hat Developer Hub deployment. This method is specific for default objects, for instance, the Backstage Deployment that the Operator manages.

#### 2.3.1. Configuring mount paths for default Secrets and Persistent Volume Claims (PVCs)Copy link

By default, the mount path is`/opt/app-root/src`. To specify a different path, add the`rhdh.redhat.com/mount-path` annotation to your resource.

Procedure

To specify a PVC mount path, add the`rhdh.redhat.com/mount-path` annotation to your configuration file as shown in the following example:

Example specifying the PVC mount path

```yaml
apiVersion: v1
kind: PersistentVolumeClaim
metadata:
  name: <my_claim> # Specifies the PVC to mount
  annotations:
    # Specifies which mount path the PVC mounts to (in this case, /mount/path/from/annotation directory)
    rhdh.redhat.com/mount-path: /mount/path/from/annotation
```

To specify a Secret mount path, add the`rhdh.redhat.com/mount-path` annotation to your configuration file as shown in the following example:

Example specifying where the Secret mounts

```yaml
apiVersion: v1
kind: Secret
metadata:
  name: <my_secret> # Specifies the Secret name
  annotations:
    rhdh.redhat.com/mount-path: /mount/path/from/annotation
```

#### 2.3.2. Mounting Secrets and PVCs to specific containersCopy link

By default, Secrets and PVCs mount only to the Red Hat Developer Hub`backstage-backend` container. You can add the`rhdh.redhat.com/containers` annotation to your configuration file to specify the containers to mount to.

Procedure

To mount Secrets to all containers, set the`rhdh.redhat.com/containers` annotation to`*` in your configuration file:

Example mounting to all containers

```yaml
apiVersion: v1
kind: Secret
metadata:
  name: <my_secret>
  annotations:
    rhdh.redhat.com/containers: *
```

Important

Set`rhdh.redhat.com/containers` to`*` to mount it to all containers in the deployment.

To mount to specific containers, separate the names with commas:

Example separating the list of containers

```yaml
apiVersion: v1
kind: PersistentVolumeClaim
metadata:
  name: <my_claim>
  annotations:
    rhdh.redhat.com/containers: "init-dynamic-plugins,backstage-backend"
```

Note

This configuration mounts the` ` PVC to the`init-dynamic-plugins` and`backstage-backend` containers.

## Chapter 3. Time syntax in Red Hat Developer HubCopy link

Learn about time duration formats supported by Red Hat Developer Hub.

Expand

Format

Description

Example

Compound values

Human-readable strings

Simple strings compatible with the`ms` library.

`30m`

No

Duration objects

A structured object specifying time units. Matches the`HumanDuration` TypeScript interface.

```yaml
  timeout:
    minutes: 30
```

Yes

ISO 8601 duration strings

Standard ISO 8601 duration strings.

`PT30M`

Yes

| Table 3.1. Generally supported time formats |
| --- |

Show more

Expand

Format

Description

Example

Cron

An object containing a`cron` key with a crontab-style string. Used primarily by Scheduler services for tasks such as`frequency`).

```yaml
  frequency:
    cron: '*/30 * * * *'
```

| Table 3.2. Context-dependent time formats |
| --- |

Show more

Warning

RHDH configuration reader`readDurationFromConfig` explicitly disallows plain numbers to prevent ambiguity.

However, specific raw configuration fields, such as direct Node.js HTTP server settings, might strictly require numbers. Always check the specific documentation for the field you are configuring.

## Chapter 4. Configuring external PostgreSQL databasesCopy link

The Red Hat Developer Hub Operator and Helm chart default to creating a local PostgreSQL database, but you need an external database to support a production environment.

As part of configuring an external PostgreSQL database in this chapter, you disable creation of the local database.

Important

Configure your database to use the date format of the International Organization for Standardization (ISO) through the`DateStyle` setting. Other formats are incompatible with the software catalog’s internal tracking, which causes scheduling tasks to fail and prevents your catalog items from refreshing.

### 4.1. Configuring an external PostgreSQL instance using the OperatorCopy link

You can configure an external PostgreSQL instance using the Red Hat Developer Hub Operator. By default, the Operator creates and manages a local instance of PostgreSQL in the same namespace where you have deployed the RHDH instance. However, you can change this default setting to configure an external PostgreSQL database server, for example, Amazon Web Services (AWS) Relational Database Service (RDS) or Azure database.

Prerequisites

You have the following details:

- `db-host`: Denotes your PostgreSQL instance Domain Name System (DNS) or IP address
- `db-port`: Denotes your PostgreSQL instance port number, such as`5432`
- `username`: Denotes the user name to connect to your PostgreSQL instance
- `password`: Denotes the password to connect to your PostgreSQL instance

Note

By default, Developer Hub uses a database for each plugin and automatically creates it if none is found. You might need the`Create Database` privilege in addition to`PSQL Database` privileges for configuring an external PostgreSQL instance.

Procedure

Optional: Create a certificate secret to configure your PostgreSQL instance with a TLS connection:

```shell-session
$ cat <<EOF | oc -n my-rhdh-project create -f -
apiVersion: v1
kind: Secret
metadata:
 name: my-rhdh-database-certificates-secrets 
```

1

```shell-session

type: Opaque
stringData:
 postgres-ca.pem: |-
  -----BEGIN CERTIFICATE-----
  <ca-certificate-key> 
```

2

```shell-session

 postgres-key.key: |-
  -----BEGIN CERTIFICATE-----
  <tls-private-key> 
```

3

```shell-session

 postgres-crt.pem: |-
  -----BEGIN CERTIFICATE-----
  <tls-certificate-key> 
```

4

```shell-session

  # ...
EOF
```

1

Provide the name of the certificate secret.

2

Provide the CA certificate key.

3

Optional: Provide the TLS private key.

4

Optional: Provide the TLS certificate key.

Create a credential secret to connect with the PostgreSQL instance:

```shell-session
$ cat <<EOF | oc -n my-rhdh-project create -f -
apiVersion: v1
kind: Secret
metadata:
 name: my-rhdh-database-secrets 
```

1

```shell-session

type: Opaque
stringData: 
```

2

```shell-session

 POSTGRES_PASSWORD: <password>
 POSTGRES_PORT: "<db-port>"
 POSTGRES_USER: <username>
 POSTGRES_HOST: <db-host>
 PGSSLMODE: <ssl-mode> # for TLS connection 
```

3

```shell-session

 NODE_EXTRA_CA_CERTS: <abs-path-to-pem-file> # for TLS connection, e.g. /opt/app-root/src/postgres-crt.pem 
```

4

```shell-session

EOF
```

1

Provide the name of the credential secret.

2

Provide credential data to connect with your PostgreSQL instance.

3

Optional: Provide the value based on the required [Secure Sockets Layer (SSL) mode](https://www.postgresql.org/docs/15/libpq-connect.html#LIBPQ-CONNECT-SSLMODE).

4

Optional: Provide the value only if you need a TLS connection for your PostgreSQL instance.

Create your`Backstage` custom resource (CR):

```shell-session
cat <<EOF | oc -n my-rhdh-project create -f -
apiVersion: rhdh.redhat.com/v1alpha5
kind: Backstage
metadata:
  name: <backstage-instance-name>
spec:
  database:
    enableLocalDb: false 
```

1

```shell-session

  application:
    extraFiles:
      mountPath: <path> # e g /opt/app-root/src
      secrets:
        - name: my-rhdh-database-certificates-secrets 
```

2

```shell-session

          key: postgres-crt.pem, postgres-ca.pem, postgres-key.key # key name as in my-rhdh-database-certificates-secrets Secret
    extraEnvs:
      secrets:
        - name: my-rhdh-database-secrets 
```

3

```shell-session

        # ...
```

1

Set the value of the`enableLocalDb` parameter to`false` to disable creating local PostgreSQL instances.

2

Provide the name of the certificate secret if you have configured a TLS connection.

3

Provide the name of the credential secret that you created.

Note

The environment variables listed in the`Backstage` CR work with the Operator default configuration. If you have changed the Operator default configuration, you must reconfigure the`Backstage` CR accordingly.

1. Optional: You have a CA certificate, Transport Layer Security (TLS) private key, and TLS certificate so that you can secure your database connection by using the TLS protocol. For more information, refer to your PostgreSQL vendor documentation.
2. Apply the`Backstage` CR to the namespace where you have deployed the Developer Hub instance.

### 4.2. Configuring an external PostgreSQL instance using the Helm ChartCopy link

You can configure an external PostgreSQL instance by using the Helm Chart. By default, the Helm Chart creates and manages a local instance of PostgreSQL in the same namespace where you have deployed the RHDH instance. However, you can change this default setting to configure an external PostgreSQL database server, for example, Amazon Web Services (AWS) Relational Database Service (RDS) or Azure database.

Prerequisites

You have the following details:

- `db-host`: Denotes your PostgreSQL instance Domain Name System (DNS) or IP address
- `db-port`: Denotes your PostgreSQL instance port number, such as`5432`
- `username`: Denotes the user name to connect to your PostgreSQL instance
- `password`: Denotes the password to connect to your PostgreSQL instance

Note

By default, Developer Hub uses a database for each plugin and automatically creates it if none is found. You might need the`Create Database` privilege in addition to`PSQL Database` privileges for configuring an external PostgreSQL instance.

Procedure

Optional: Create a certificate secret to configure your PostgreSQL instance with a TLS connection:

```shell-session
$ cat <<EOF | oc -n <your-namespace> create -f -
apiVersion: v1
kind: Secret
metadata:
 name: my-rhdh-database-certificates-secrets 
```

1

```shell-session

type: Opaque
stringData:
 postgres-ca.pem: |-
  -----BEGIN CERTIFICATE-----
  <ca-certificate-key> 
```

2

```shell-session

 postgres-key.key: |-
  -----BEGIN CERTIFICATE-----
  <tls-private-key> 
```

3

```shell-session

 postgres-crt.pem: |-
  -----BEGIN CERTIFICATE-----
  <tls-certificate-key> 
```

4

```shell-session

  # ...
EOF
```

1

Provide the name of the certificate secret.

2

Provide the CA certificate key.

3

Optional: Provide the TLS private key.

4

Optional: Provide the TLS certificate key.

Create a credential secret to connect with the PostgreSQL instance:

```shell-session
$ cat <<EOF | oc -n <your-namespace> create -f -
apiVersion: v1
kind: Secret
metadata:
 name: my-rhdh-database-secrets 
```

1

```shell-session

type: Opaque
stringData: 
```

2

```shell-session

 POSTGRES_PASSWORD: <password>
 POSTGRES_PORT: "<db-port>"
 POSTGRES_USER: <username>
 POSTGRES_HOST: <db-host>
 PGSSLMODE: <ssl-mode> # for TLS connection 
```

3

```shell-session

 NODE_EXTRA_CA_CERTS: <abs-path-to-pem-file> # for TLS connection, e.g. /opt/app-root/src/postgres-crt.pem 
```

4

```shell-session

EOF
```

1

Provide the name of the credential secret.

2

Provide credential data to connect with your PostgreSQL instance.

3

Optional: Provide the value based on the required [Secure Sockets Layer (SSL) mode](https://www.postgresql.org/docs/15/libpq-connect.html#LIBPQ-CONNECT-SSLMODE).

4

Optional: Provide the value only if you need a TLS connection for your PostgreSQL instance.

Configure your PostgreSQL instance in the Helm configuration file named`values.yaml`:

```yaml
# ...
upstream:
  postgresql:
    enabled: false  # disable PostgreSQL instance creation 
```

1

```yaml

    auth:
      existingSecret: my-rhdh-database-secrets # inject credentials secret to Backstage 
```

2

```yaml

  backstage:
    appConfig:
      backend:
        database:
          connection:  # configure Backstage DB connection parameters
            host: ${POSTGRES_HOST}
            port: ${POSTGRES_PORT}
            user: ${POSTGRES_USER}
            password: ${POSTGRES_PASSWORD}
            ssl:
              rejectUnauthorized: true,
              ca:
                $file: /opt/app-root/src/postgres-ca.pem
              key:
                $file: /opt/app-root/src/postgres-key.key
              cert:
                $file: /opt/app-root/src/postgres-crt.pem
  extraEnvVarsSecrets:
    - my-rhdh-database-secrets # inject credentials secret to Backstage 
```

3

```yaml

  extraEnvVars:
    - name: BACKEND_SECRET
      valueFrom:
        secretKeyRef:
          key: backend-secret
          name: '{{ include "janus-idp.backend-secret-name" $ }}'
  extraVolumeMounts:
    - mountPath: /opt/app-root/src/dynamic-plugins-root
      name: dynamic-plugins-root
    - mountPath: /opt/app-root/src/postgres-crt.pem
      name: postgres-crt # inject TLS certificate to Backstage cont. 
```

4

```yaml

      subPath: postgres-crt.pem
    - mountPath: /opt/app-root/src/postgres-ca.pem
      name: postgres-ca # inject CA certificate to Backstage cont. 
```

5

```yaml

      subPath: postgres-ca.pem
    - mountPath: /opt/app-root/src/postgres-key.key
      name: postgres-key # inject TLS private key to Backstage cont. 
```

6

```yaml

      subPath: postgres-key.key
  extraVolumes:
    - ephemeral:
        volumeClaimTemplate:
          spec:
            accessModes:
              - ReadWriteOnce
            resources:
              requests:
                storage: 1Gi
      name: dynamic-plugins-root
    - configMap:
        defaultMode: 420
        name: dynamic-plugins
        optional: true
      name: dynamic-plugins
    - name: dynamic-plugins-npmrc
      secret:
        defaultMode: 420
        optional: true
        secretName: '{{ printf "%s-dynamic-plugins-npmrc" .Release.Name }}'
    - name: postgres-crt
      secret:
        secretName: my-rhdh-database-certificates-secrets 
```

7

```yaml

        # ...
```

1

Set the value of the`upstream.postgresql.enabled` parameter to`false` to disable creating local PostgreSQL instances.

2

Provide the name of the credential secret.

3

Provide the name of the credential secret.

4

Optional: Provide the name of the TLS certificate only for a TLS connection.

5

Optional: Provide the name of the CA certificate only for a TLS connection.

6

Optional: Provide the name of the TLS private key only if your TLS connection requires a private key.

7

Provide the name of the certificate secret if you have configured a TLS connection.

Apply the configuration changes in your Helm configuration file named`values.yaml`:

```shell-session
$ helm upgrade -n <your-namespace> <your-deploy-name> openshift-helm-charts/redhat-developer-hub -f values.yaml --version 1.9.2
```

1. Optional: You have a CA certificate, Transport Layer Security (TLS) private key, and TLS certificate so that you can secure your database connection by using the TLS protocol. For more information, refer to your PostgreSQL vendor documentation.

### 4.3. Migrating local databases to an external database server using the OperatorCopy link

By default, Red Hat Developer Hub hosts the data for each plugin in a PostgreSQL database. When you fetch the list of databases, you might see multiple databases based on the number of plugins configured in Developer Hub. You can migrate the data from an RHDH instance hosted on a local PostgreSQL server to an external PostgreSQL service, such as AWS RDS, Azure database, or Crunchy database. To migrate the data from each RHDH instance, you can use PostgreSQL utilities, such as [pg_dump](https://www.postgresql.org/docs/current/app-pgdump.html) with [psql](https://www.postgresql.org/docs/current/app-psql.html) or [pgAdmin](https://www.pgadmin.org/).

Note

The following procedure uses a database copy script to do a quick migration.

Prerequisites

- You have installed the [pg_dump](https://www.postgresql.org/docs/current/app-pgdump.html) and [psql](https://www.postgresql.org/docs/current/app-psql.html) utilities on your local machine.
- For data export, you have the PGSQL user privileges to make a full dump of local databases.
- For data import, you have the PGSQL admin privileges to create an external database and populate it with database dumps.

Procedure

Configure port forwarding for the local PostgreSQL database pod by running the following command on a terminal:

```shell-session
$ oc port-forward -n <your-namespace> <pgsql-pod-name> <forward-to-port>:<forward-from-port>
```

Where:

The` ` variable denotes the local PostgreSQL instance port, such as`5432`.

Example: Configuring port forwarding

```shell-session
$ oc port-forward -n developer-hub backstage-psql-developer-hub-0 15432:5432
```

- The` ` variable denotes the name of a PostgreSQL pod with the format`backstage-psql- -<_index>`.
- The` ` variable denotes the port of your choice to forward PostgreSQL data to.

Make a copy of the following`db_copy.sh` script and edit the details based on your configuration:

```bash
#!/bin/bash

to_host=<db-service-host> 
```

1

```bash

to_port=5432 
```

2

```bash

to_user=postgres 
```

3

```bash


from_host=127.0.0.1 
```

4

```bash

from_port=15432 
```

5

```bash

from_user=postgres 
```

6

```bash


allDB=("backstage_plugin_app" "backstage_plugin_auth" "backstage_plugin_catalog" "backstage_plugin_permission" "backstage_plugin_scaffolder" "backstage_plugin_search") 
```

7

```bash


for db in ${!allDB[@]};
do
  db=${allDB[$db]}
  echo Copying database: $db
  PGPASSWORD=$TO_PSW psql -h $to_host -p $to_port -U $to_user -c "create database $db;"
  pg_dump -h $from_host -p $from_port -U $from_user -d $db | PGPASSWORD=$TO_PSW psql -h $to_host -p $to_port -U $to_user -d $db
done
```

1

The destination host name, for example,`.rds.amazonaws.com`.

2

The destination port, such as`5432`.

3

The destination server username, for example,`postgres`.

4

The source host name, such as`127.0.0.1`.

5

The source port number, such as the` ` variable.

6

The source server username, for example,`postgres`.

7

The name of databases to import in double quotes separated by spaces, for example,`("backstage_plugin_app" "backstage_plugin_auth" "backstage_plugin_catalog" "backstage_plugin_permission" "backstage_plugin_scaffolder" "backstage_plugin_search")`.

Create a destination database for copying the data:

```shell-session
/bin/bash TO_PSW=<destination-db-password> /path/to/db_copy.sh 
```

1

1

The` ` variable denotes the password to connect to the destination database.

Note

You can stop port forwarding when the copying of the data is complete. For more information about handling large databases and using the compression tools, see the [Handling Large Databases](https://www.postgresql.org/docs/current/backup-dump.html#BACKUP-DUMP-LARGE) section on the PostgreSQL website.

Check that the following code is present at the end of your`Backstage` CR after reconfiguration:

```yaml
# ...
spec:
  database:
    enableLocalDb: false
  application:
  # ...
    extraFiles:
      secrets:
        - name: my-rhdh-database-certificates-secrets
          key: postgres-crt.pem # key name as in my-rhdh-database-certificates-secrets Secret
    extraEnvs:
      secrets:
        - name: my-rhdh-database-secrets
# ...
```

Note

Reconfiguring the`Backstage` CR deletes the corresponding`StatefulSet` and`Pod` objects, but does not delete the`PersistenceVolumeClaim` object. Use the following command to delete the local`PersistenceVolumeClaim` object:

```shell-session
oc -n developer-hub delete pvc <local-psql-pvc-name>
```

where, the` ` variable is in the`data- ` format.

Verification

Verify that your RHDH instance is running with the migrated data and does not contain the local PostgreSQL database by running the following command:

```shell-session
oc get pods -n <your-namespace>
```

Check the output for the following details:

The`backstage-psql-developer-hub-0` pod is not available.

You can also verify these details using the Topology view in the OpenShift Container Platform web console.

- The`backstage-developer-hub-xxx` pod is in running state.

## Chapter 5. Configuring Red Hat Developer Hub deployment when using the OperatorCopy link

The Red Hat Developer Hub Operator exposes a`rhdh.redhat.com/v1alpha5` API Version of its custom resource (CR). This CR exposes a generic`spec.deployment.patch` field, which gives you full control over the Developer Hub Deployment resource. This field can be a fragment of the standard`apps.Deployment` Kubernetes object.

Procedure

1. Create a`Backstage` CR with the following fields:

Example

```yaml
apiVersion: rhdh.redhat.com/v1alpha5
kind: Backstage
metadata:
  name: developer-hub
spec:
  deployment:
    patch:
      spec:
        template:
```

`labels`

Add labels to the Developer Hub pod.

Example adding the label`my=true`

```yaml
apiVersion: rhdh.redhat.com/v1alpha5
kind: Backstage
metadata:
  name: developer-hub
spec:
  deployment:
    patch:
      spec:
        template:
          metadata:
            labels:
              my: true
```

`volumes` 

Add an additional volume named`my-volume` and mount it under`/my/path` in the Developer Hub application container.

Example additional volume

```yaml
apiVersion: rhdh.redhat.com/v1alpha5
kind: Backstage
metadata:
  name: developer-hub
spec:
  deployment:
    patch:
      spec:
        template:
          spec:
            containers:
              - name: backstage-backend
                volumeMounts:
                  - mountPath: /my/path
                    name: my-volume
            volumes:
              - ephemeral:
                  volumeClaimTemplate:
                    spec:
                      storageClassName: "special"
                name: my-volume
```

Replace the default`dynamic-plugins-root` volume with a persistent volume claim (PVC) named`dynamic-plugins-root`. Note the`$patch: replace` directive, otherwise a new volume will be added.

Example`dynamic-plugins-root` volume replacement

```yaml
apiVersion: rhdh.redhat.com/v1alpha5
kind: Backstage
metadata:
  name: developer-hub
spec:
  deployment:
    patch:
      spec:
        template:
          spec:
            volumes:
              - $patch: replace
                name: dynamic-plugins-root
                persistentVolumeClaim:
                  claimName: dynamic-plugins-root
```

`cpu` request

Set the CPU request for the Developer Hub application container to 250m.

Example CPU request

```yaml
apiVersion: rhdh.redhat.com/v1alpha5
kind: Backstage
metadata:
  name: developer-hub
spec:
  deployment:
    patch:
      spec:
        template:
          spec:
            containers:
              - name: backstage-backend
                resources:
                  requests:
                    cpu: 250m
```

`my-sidecar` container

Add a new`my-sidecar` sidecar container into the Developer Hub Pod.

Example side car container

```yaml
apiVersion: rhdh.redhat.com/v1alpha5
kind: Backstage
metadata:
  name: developer-hub
spec:
  deployment:
    patch:
      spec:
        template:
          spec:
            containers:
              - name: my-sidecar
                image: quay.io/my-org/my-sidecar:latest
```

## Chapter 6. Configuring high availability in Red Hat Developer HubCopy link

High availability (HA) is a system design approach that ensures a service remains continuously accessible, even during failures of individual components, by eliminating single points of failure. It introduces redundancy and failover mechanisms to minimize downtime and maintain operational continuity.

Red Hat Developer Hub supports HA deployments on the following platforms:

- Red Hat OpenShift Container Platform
- Azure Kubernetes Service
- Elastic Kubernetes Service
- Google Kubernetes Engine

The HA deployments enable more resilient and reliable service availability across supported environments.

In a single instance deployment, if a failure occurs, whether due to software crashes, hardware issues, or other unexpected disruptions, it would make the entire service unavailable, interrupting development workflows and access to key resources.

With HA enabled, you can scale the number of backend replicas to introduce redundancy. This setup ensures that if one pod or component fails, others continue to serve requests without disruption. The built-in load balancer manages ingress traffic and distributes the load across the available pods. Meanwhile, the RHDH backend manages concurrent requests and resolves resource-level conflicts effectively.

As an administrator, you can configure high availability by adjusting replica values in your configuration file:

- If you installed using the Operator, configure the replica values in your`Backstage` custom resource.
- If you used the Helm chart, set the replica values in the Helm configuration.

### 6.1. Configuring high availability in a Red Hat Developer Hub Operator deploymentCopy link

RHDH instances that are deployed with the Operator use configurations in the`Backstage` custom resource (CR). In the`Backstage` CR, the default value for the`replicas` field is`1`. If you want to configure your RHDH instance for high availability, you must set`replicas` to a value greater than`1`.

Procedure

In your`Backstage` custom resource (CR), set`replicas` to a value greater than`1`.

For example, to configure two replicas (one backup instance):

```yaml
apiVersion: rhdh.redhat.com/v1alpha5
kind: Backstage
metadata:
  name: <your_yaml_file>
spec:
  deployment:
    patch:
      spec:
        replicas: 2
```

### 6.2. Configuring high availability in a Red Hat Developer Hub Helm chart deploymentCopy link

When you are deploying Developer Hub using the Helm chart, you must set`replicas` to a value greater than`1` in your Helm chart. The default value for`replicas` is`1`.

Procedure

In your Helm chart configuration file, set`replicas` to a value greater than`1`.

For example, to configure two replicas (one backup instance):

```yaml
upstream:
  backstage:
    replicas: 2
```

## Chapter 7. Running Red Hat Developer Hub behind a corporate proxyCopy link

In a network restricted environment, configure Red Hat Developer Hub to use your proxy to access remote network resources.

You can run the Developer Hub application behind a corporate proxy by setting any of the following environment variables before starting the application:

`HTTP_PROXY` Denotes the proxy to use for HTTP requests.`HTTPS_PROXY` Denotes the proxy to use for HTTPS requests.`NO_PROXY` Set the environment variable to bypass the proxy for certain domains. The variable value is a comma-separated list of hostnames or IP addresses that can be accessed without the proxy, even if one is specified.

### 7.1. The NO_PROXY exclusion rulesCopy link

`NO_PROXY` is a comma or space-separated list of hostnames or IP addresses, with optional port numbers. If the input URL matches any of the entries listed in`NO_PROXY`, a direct request fetches that URL, for example, bypassing the proxy settings.

Note

The default value for`NO_PROXY` in RHDH is`localhost,127.0.0.1`. If you want to override it, include at least`localhost` or`localhost:7007` in the list. Otherwise, the RHDH backend might fail.

Matching follows the rules below:

- `NO_PROXY=*` will bypass the proxy for all requests.
- Space and commas might separate the entries in the`NO_PROXY` list. For example,`NO_PROXY="localhost,example.com"`, or`NO_PROXY="localhost example.com"`, or`NO_PROXY="localhost, example.com"` would have the same effect.
- If`NO_PROXY` contains no entries, configuring the`HTTP(S)_PROXY` settings makes the backend send all requests through the proxy.
- The backend does not perform a DNS lookup to determine if a request should bypass the proxy or not. For example, if DNS resolves`example.com` to`1.2.3.4`, setting`NO_PROXY=1.2.3.4` has no effect on requests sent to`example.com`. Only requests sent to the IP address`1.2.3.4` bypass the proxy.
- If you add a port after the hostname or IP address, the request must match both the host/IP and port to bypass the proxy. For example,`NO_PROXY=example.com:1234` would bypass the proxy for requests to`http(s)://example.com:1234`, but not for requests on other ports, like`http(s)://example.com`.
- If you do not specify a port after the hostname or IP address, all requests to that host/IP address will bypass the proxy regardless of the port. For example,`NO_PROXY=localhost` would bypass the proxy for requests sent to URLs like`http(s)://localhost:7077` and`http(s)://localhost:8888`.
- IP Address blocks in CIDR notation will not work. So setting`NO_PROXY=10.11.0.0/16` will not have any effect, even if the backend sends a request to an IP address in that block.
- Supports only IPv4 addresses. IPv6 addresses like`::1` will not work.
- Generally, the proxy is only bypassed if the hostname is an exact match for an entry in the`NO_PROXY` list. The only exceptions are entries that start with a dot (`.`) or with a wildcard (`*`). In such a case, bypass the proxy if the hostname ends with the entry.

Note

List the domain and the wildcard domain if you want to exclude a given domain and all its subdomains. For example, you would set`NO_PROXY=example.com,.example.com` to bypass the proxy for requests sent to`http(s)://example.com` and`http(s)://subdomain.example.com`.

### 7.2. Configuring proxy information in Operator deploymentCopy link

For Operator-based deployment, the approach you use for proxy configuration is based on your role:

- As a cluster administrator with access to the Operator namespace, you can configure the proxy variables in the Operator’s default ConfigMap file. This configuration applies the proxy settings to all the users of the Operator.
- As a developer, you can configure the proxy variables in a custom resource (CR) file. This configuration applies the proxy settings to the RHDH application created from that CR.

Prerequisites

- You have installed the Red Hat Developer Hub application.

Procedure

Perform one of the following steps based on your role:

As an administrator, set the proxy information in the Operator’s default ConfigMap file:

Set the value of the`HTTP_PROXY`,`HTTPS_PROXY`, and`NO_PROXY` environment variables in the`Deployment` spec as shown in the following example:

Example: Setting proxy variables in a ConfigMap file

```yaml
# Other fields omitted
  deployment.yaml: |-
    apiVersion: apps/v1
    kind: Deployment
    spec:
      template:
        spec:
          # Other fields omitted
          initContainers:
            - name: install-dynamic-plugins
              # command omitted
              env:
                - name: NPM_CONFIG_USERCONFIG
                  value: /opt/app-root/src/.npmrc.dynamic-plugins
                - name: HTTP_PROXY
                  value: 'http://10.10.10.105:3128'
                - name: HTTPS_PROXY
                  value: 'http://10.10.10.106:3128'
                - name: NO_PROXY
                  value: 'localhost,example.org'
              # Other fields omitted
          containers:
            - name: backstage-backend
              # Other fields omitted
              env:
                - name: APP_CONFIG_backend_listen_port
                  value: "7007"
                - name: HTTP_PROXY
                  value: 'http://10.10.10.105:3128'
                - name: HTTPS_PROXY
                  value: 'http://10.10.10.106:3128'
                - name: NO_PROXY
                  value: 'localhost,example.org'
```

1. Search for a ConfigMap file named`backstage-default-config` in the default namespace`rhdh-operator` and open it.
2. Find the`deployment.yaml` key.

As a developer, set the proxy information in your`Backstage` CR file as shown in the following example:

Example: Setting proxy variables in a CR file

```yaml
spec:
  # Other fields omitted
  application:
    extraEnvs:
      envs:
        - name: HTTP_PROXY
          value: 'http://10.10.10.105:3128'
        - name: HTTPS_PROXY
          value: 'http://10.10.10.106:3128'
        - name: NO_PROXY
          value: 'localhost,example.org'
```

### 7.3. Configuring proxy information in Helm deploymentCopy link

For Helm-based deployment, either a developer or a cluster administrator with permissions to create resources in the cluster can configure the proxy variables in a`values.yaml` Helm configuration file.

Prerequisites

- Save the configuration changes.
- You have installed the Red Hat Developer Hub application.

Procedure

Set the proxy information in your Helm configuration file:

```yaml
upstream:
  backstage:
    extraEnvVars:
      - name: HTTP_PROXY
        value: '<http_proxy_url>'
      - name: HTTPS_PROXY
        value: '<https_proxy_url>'
      - name: NO_PROXY
        value: '<no_proxy_settings>'
```

Where,

` ` Denotes a variable that you must replace with the HTTP proxy URL.` ` Denotes a variable that you must replace with the HTTPS proxy URL.` `

Denotes a variable that you must replace with comma-separated URLs, which you want to exclude from proxying, for example,`foo.com,baz.com`.

Example: Setting proxy variables using Helm Chart

```yaml
upstream:
  backstage:
    extraEnvVars:
      - name: HTTP_PROXY
        value: 'http://10.10.10.105:3128'
      - name: HTTPS_PROXY
        value: 'http://10.10.10.106:3128'
      - name: NO_PROXY
        value: 'localhost,example.org'
```

1. Save the configuration changes.

## Chapter 8. Configuring an RHDH instance with a TLS connection in KubernetesCopy link

You can configure a RHDH instance with a Transport Layer Security (TLS) connection in a Kubernetes cluster, such as an Azure Red Hat OpenShift (ARO) cluster, any cluster from a supported cloud provider, or your own cluster with proper configuration. Transport Layer Security (TLS) ensures a secure connection for the RHDH instance with other entities, such as third-party applications, or external databases. However, you must use a public Certificate Authority (CA)-signed certificate to configure your Kubernetes cluster.

Prerequisites

You have created a namespace and setup a service account with proper read permissions on resources.

Example: Kubernetes manifest for role-based access control

```yaml
apiVersion: rbac.authorization.k8s.io/v1
kind: ClusterRole
metadata:
  name: backstage-read-only
rules:
  - apiGroups:
      - '*'
    resources:
      - pods
      - configmaps
      - services
      - deployments
      - replicasets
      - horizontalpodautoscalers
      - ingresses
      - statefulsets
      - limitranges
      - resourcequotas
      - daemonsets
    verbs:
      - get
      - list
      - watch
#...
```

You have created some resources and added annotations to them so they can be discovered by the Kubernetes plugin. You can apply these Kubernetes annotations:

- `backstage.io/kubernetes-id` to label components
- `backstage.io/kubernetes-namespace` to label namespaces

Procedure

Enable the Kubernetes plugins in the`dynamic-plugins-rhdh.yaml` file:

```yaml
kind: ConfigMap
apiVersion: v1
metadata:
  name: dynamic-plugins-rhdh
data:
  dynamic-plugins.yaml: |
    includes:
      - dynamic-plugins.default.yaml
    plugins:
      - package: ./dynamic-plugins/dist/backstage-plugin-kubernetes-backend-dynamic
        disabled: false 
```

1

```yaml

      - package: ./dynamic-plugins/dist/backstage-plugin-kubernetes
        disabled: false 
```

2

```yaml

        # ...
```

1

Set the value to`false` to enable the`backstage-plugin-kubernetes-backend-dynamic` plugin.

2

Set the value to`false` to enable the`backstage-plugin-kubernetes` plugin.

Note

The`backstage-plugin-kubernetes` plugin is currently in [Technology Preview](https://access.redhat.com/support/offerings/techpreview). As an alternative, you can use the`./dynamic-plugins/dist/backstage-plugin-topology-dynamic` plugin, which is Generally Available (GA).

Set the Kubernetes cluster details and configure the catalog sync options in the [app-config.yaml configuration file](https://docs.redhat.com/en/documentation/red_hat_developer_hub/1.9/html-single/configuring_red_hat_developer_hub/index).

```yaml
kind: ConfigMap
apiVersion: v1
metadata:
  name: my-rhdh-app-config
data:
  "app-config.yaml": |
  # ...
  catalog:
    rules:
      - allow: [Component, System, API, Resource, Location]
    providers:
      kubernetes:
        openshift:
          cluster: openshift
          processor:
            namespaceOverride: default
            defaultOwner: guests
          schedule:
            frequency:
              seconds: 30
            timeout:
              seconds: 5
  kubernetes:
    serviceLocatorMethod:
      type: 'multiTenant'
    clusterLocatorMethods:
      - type: 'config'
        clusters:
          - url: <target-cluster-api-server-url> 
```

1

```yaml

            name: openshift
            authProvider: 'serviceAccount'
            skipTLSVerify: false 
```

2

```yaml

            skipMetricsLookup: true
            dashboardUrl: <target-cluster-console-url> 
```

3

```yaml

            dashboardApp: openshift
            serviceAccountToken: ${K8S_SERVICE_ACCOUNT_TOKEN} 
```

4

```yaml

            caData: ${K8S_CONFIG_CA_DATA} 
```

5

```yaml

            # ...
```

1

The base URL to the Kubernetes control plane. You can run the`kubectl cluster-info` command to get the base URL.

2

Set the value of this parameter to`false` to enable the verification of the TLS certificate.

3

Optional: The link to the Kubernetes dashboard managing the ARO cluster.

4

Optional: Pass the service account token using a`K8S_SERVICE_ACCOUNT_TOKEN` environment variable that you define in your` ` secret.

5

Pass the CA data using a`K8S_CONFIG_CA_DATA` environment variable that you define in your` ` secret.

1. Save the configuration changes.

Verification

Run the RHDH application to import your catalog:

```shell-session
$ kubectl -n rhdh-operator get pods -w
```

1. Verify that the pod log shows no errors for your configuration.
2. Go to Catalog and check the component page in the Developer Hub instance to verify the cluster connection and the presence of your created resources.

Note

If you encounter connection errors, such as certificate issues or permissions, check the message box in the component page or view the logs of the pod.

## Chapter 9. Using the dynamic plugins cacheCopy link

### 9.1. Using the dynamic plugins cacheCopy link

The dynamic plugins cache in Red Hat Developer Hub (RHDH) enhances the installation process and reduces platform boot time by storing previously installed plugins. If the configuration remains unchanged, this feature prevents the need to re-download plugins on subsequent boots.

When you enable dynamic plugins cache:

- The system calculates a checksum of each plugin’s YAML configuration (excluding`pluginConfig`).
- The checksum is stored in a file named`dynamic-plugin-config.hash` within the plugin’s directory.
- During boot, if a plugin’s package reference matches the previous installation and the checksum is unchanged, the download is skipped.
- Plugins that are disabled since the previous boot are automatically removed.

Note

To enable the dynamic plugins cache in RHDH, the plugins directory`dynamic-plugins-root` must be a persistent volume.

### 9.2. Creating a PVC for the dynamic plugin cache by using the OperatorCopy link

For operator-based installations, you must manually create the persistent volume claim (PVC) by replacing the default`dynamic-plugins-root` volume with a PVC named`dynamic-plugins-root`.

Prerequisites

- You have installed Red Hat Developer Hub on OpenShift Container Platform using the Red Hat Developer Hub Operator.
- You have installed the OpenShift CLI (`oc`).

Procedure

Create the persistent volume definition and save it to a file, such as`pvc.yaml`. For example:

```yaml
kind: PersistentVolumeClaim
apiVersion: v1
metadata:
  name: dynamic-plugins-root
spec:
  accessModes:
    - ReadWriteOnce
  resources:
    requests:
      storage: 5Gi
```

Note

This example uses`ReadWriteOnce` as the access mode which prevents multiple replicas from sharing the PVC across different nodes. To run multiple replicas on different nodes, depending on your storage driver, you must use an access mode such as`ReadWriteMany`.

To apply this PVC to your cluster, run the following command:

```shell-session
$ oc apply -f pvc.yaml
```

Replace the default`dynamic-plugins-root` volume with a PVC named`dynamic-plugins-root`. For example:

```yaml
apiVersion: rhdh.redhat.com/v1alpha5
kind: Backstage
metadata:
  name: developer-hub
spec:
  deployment:
    patch:
      spec:
        template:
          spec:
            volumes:
              - $patch: replace
                name: dynamic-plugins-root
                persistentVolumeClaim:
                  claimName: dynamic-plugins-root
```

Note

To avoid adding a new volume, you must use the`$patch: replace` directive.

### 9.3. Fix 404 error after cached dynamic plugins configuration changeCopy link

When multiple Developer Hub replicas share a single dynamic plugins cache PVC, updating configurations with the Operator can trigger temporary 404 errors. This occurs because the replicas may access inconsistent cache states during the update process, before all replicas have synchronized.

The solution is to use an individual cache per pod.

Prerequisites

- Your API version is`v1alpha5` or later.

Procedure

In the Backstage Custom Resource (CR) file, set`spec.deployment` to use the optional`StatefulSet` as a resource kind. For example:

```shell-session
apiVersion: rhdh.redhat.com/v1alpha5
kind: Backstage
metadata:
  name: <CR_name>
...
spec:
 deployment:
  kind: StatefulSet
  patch:
   spec:
     replicas: 2
     template:
       spec:
         volumes:
           - $patch: replace
             name: dynamic-plugins-root
             persistentVolumeClaim:
               claimName: dynamic-plugins-root
     volumeClaimTemplates:
       - apiVersion: v1
         kind: PersistentVolumeClaim
         metadata:
           name: dynamic-plugins-root
         spec:
           accessModes:
             - ReadWriteOnce
           resources:
             requests:
               storage: 1Gi
```

Note

Using`StatefulSet` with a single replica can lead to downtime, while the application deletes the old pod and creates a new pod.

If you are updating an existing CR, remove the previous`Deployment` resource from the cluster:

```shell-session
oc delete deployment -l app.kubernetes.io/instance=<CR_name>
```

1. Wait a few minutes until the CR is reconciled and the`StatefulSet` resource is ready.

Note

The same requirement applies for changing the resource kind from`StatefulSet` to`Deployment`. You must manually delete the previously created resource from the cluster, because the Operator does not automatically remove the legacy resource.

### 9.4. Creating a PVC for the dynamic plugin cache using the Helm ChartCopy link

For Helm chart installations, if you require the dynamic plugin cache to persist across pod restarts, you must create a persistent volume claim (PVC) and configure the Helm chart to use it.

Prerequisites

- You have installed Red Hat Developer Hub using the Helm chart.
- You have installed the OpenShift CLI (`oc`).

Procedure

Create the persistent volume definition. For example:

```yaml
kind: PersistentVolumeClaim
apiVersion: v1
metadata:
  name: dynamic-plugins-root
spec:
  accessModes:
    - ReadWriteOnce
  resources:
    requests:
      storage: 5Gi
```

Note

This example uses`ReadWriteOnce` as the access mode which prevents multiple replicas from sharing the PVC across different nodes. To run multiple replicas on different nodes, depending on your storage driver, you must use an access mode such as`ReadWriteMany`.

To apply this PVC to your cluster, run the following command:

```shell-session
$ oc apply -f pvc.yaml
```

Configure the Helm chart to use the PVC. For example:

```yaml
upstream:
  backstage:
    extraVolumes:
      - name: dynamic-plugins-root
        persistentVolumeClaim:
          claimName: dynamic-plugins-root
      - name: dynamic-plugins
        configMap:
          defaultMode: 420
          name: '{{ printf "%s-dynamic-plugins" .Release.Name }}'
          optional: true
      - name: dynamic-plugins-npmrc
        secret:
          defaultMode: 420
          optional: true
          secretName: '{{ printf "%s-dynamic-plugins-npmrc" .Release.Name }}'
      - name: dynamic-plugins-registry-auth
        secret:
          defaultMode: 416
          optional: true
          secretName: '{{ printf "%s-dynamic-plugins-registry-auth" .Release.Name }}'
      - name: npmcacache
        emptyDir: {}
      - name: temp
        emptyDir: {}
```

Note

When you configure the Helm chart to use the PVC, you must also include the`extraVolumes` section defined in the [default Helm chart values](https://github.com/redhat-developer/rhdh-chart/blob/release-1.9/charts/backstage/values.yaml).

### 9.5. Configuring the dynamic plugins cacheCopy link

Procedure

To configure the dynamic plugins cache, set the following optional dynamic plugin cache parameters in your`dynamic-plugins.yaml` file:

`pullPolicy: IfNotPresent`(default) Download the artifact if it is not already present in the dynamic-plugins-root folder, without checking image digests.`pullPolicy: Always`

Compare the image digest in the remote registry and downloads the artifact if it has changed, even if Developer Hub has already downloaded the plugin before.

When applied to the Node Package Manager (NPM) downloading method, download the remote artifact without a digest check.

Example`dynamic-plugins.yaml` file configuration to download the remote artifact without a digest check:

```yaml
plugins:
  - disabled: false
    pullPolicy: Always
    package: 'oci://quay.io/example-org/example-plugin:v1.0.0!internal-backstage-plugin-example'
```

`forceDownload: false`(default) Older option to download the artifact if it is not already present in the dynamic-plugins-root folder, without checking image digests.`forceDownload: true`

Older option to force a reinstall of the plugin, bypassing the cache.

Note

The`pullPolicy` option takes precedence over the`forceDownload` option.

The`forceDownload` option might become deprecated in a future Developer Hub release.

## Chapter 10. Enabling the Red Hat Developer Hub plugin assets cacheCopy link

By default, Red Hat Developer Hub does not cache plugin assets. You can use a Redis cache store to improve Developer Hub performance and reliability. Configured plugins in Developer Hub receive dedicated cache connections, which are powered by the Keyv Redis client.

Prerequisites

- You have installed Red Hat Developer Hub.
- You have an active Redis server. For more information on setting up an external Redis server, see the [official Redis documentation](https://redis.io/docs/latest/).

Procedure

Enable the Developer Hub cache by defining Redis as the cache store type and entering your Redis server connection URL in your`app-config.yaml` file.

`app-config.yaml` file fragment

```yaml
backend:
  cache:
    store: redis
    connection: redis://user:pass@cache.example.com:6379
```

Enable the cache for TechDocs by adding the`techdocs.cache.ttl` setting in your`app-config.yaml` file. This setting specifies how long, in milliseconds, a statically built asset should stay in the cache.

`app-config.yaml` file fragment

```yaml
techdocs:
  cache:
    ttl: 3600000
```

Tip

Optionally, enable the cache for unsupported plugins that support this feature. See their respective documentation for details.

## Legal NoticeCopy link

Copyright © Red Hat.

Except as otherwise noted below, the text of and illustrations in this documentation are licensed by Red Hat under the Creative Commons Attribution–Share Alike 3.0 Unported license . If you distribute this document or an adaptation of it, you must provide the URL for the original version.

Red Hat, as the licensor of this document, waives the right to enforce, and agrees not to assert, Section 4d of CC-BY-SA to the fullest extent permitted by applicable law.

Red Hat, the Red Hat logo, JBoss, Hibernate, and RHCE are trademarks or registered trademarks of Red Hat, LLC. or its subsidiaries in the United States and other countries.

Linux® is the registered trademark of Linus Torvalds in the United States and other countries.

XFS is a trademark or registered trademark of Hewlett Packard Enterprise Development LP or its subsidiaries in the United States and other countries.

The OpenStack® Word Mark and OpenStack logo are trademarks or registered trademarks of the Linux Foundation, used under license.

All other trademarks are the property of their respective owners.

---

*Source: https://docs.redhat.com/en/documentation/red_hat_developer_hub/1.9/html-single/authentication_in_red_hat_developer_hub/index*

Authentication in Red Hat Developer Hub | Red Hat Developer Hub | 1.9 | Red Hat Documentation

 

 

 

 

 

 

 

 

# Authentication in Red Hat Developer Hub

---

Red Hat Developer Hub 1.9

## Configuring authentication to external services in Red Hat Developer Hub

Red Hat Customer Content Services

Legal Notice

Abstract

As a Red Hat Developer Hub (RHDH) platform engineer, you can manage authentication of other users to meet the specific needs of your organization.

---

## PrefaceCopy link

You can enable authentication in Red Hat Developer Hub to allow users to sign in to Red Hat Developer Hub using credentials from an external identity provider, such as RHBK, GitHub, or Microsoft Azure. This integration also allows you to provision user and group data from the identity provider to the Developer Hub software catalog, enabling features that rely on synchronized user and group data.

## Chapter 1. Understand authentication and user provisioningCopy link

Learn about the authentication process from creating user and group entities in the software catalog to user sign-in, and how authentication and catalog plugins enable each step. Understanding this process is essential for successfully configuring your Developer Hub instance, securing access through authorization, and enabling features that rely on synchronized user and group data.

To fully enable catalog features, provision user and group data from the Identity Provider to the Developer Hub software catalog. Catalog provider plugins handle this task asynchronously. These plugins query the Identity Provider (IdP) for relevant user and group information, and create or update corresponding entities in the Developer Hub catalog. Scheduled provisioning ensures that the catalog accurately reflects the users and groups in your organization.

When a user attempts to access Developer Hub, Developer Hub redirects them to a configured authentication provider, such as Red Hat Build of Keycloak (RHBK), GitHub, or Microsoft Azure. This external IdP is responsible for authenticating the user.

On successful authentication, the Developer Hub authentication plugin, configured in your`app-config.yaml` file, processes the response from the IdP, resolves the identity in the Developer Hub software catalog, and establishes a user session within Developer Hub.

Configuring authentication and user provisioning is critical for several reasons.

Provisioning user and group data to the catalog is necessary for various catalog features that rely on understanding entity ownership and relationships between users, groups, and software components.

Important

Without this provisioning step, features such as displaying who owns a catalog entity might not function correctly.

- Legal Notice
- Securing your Developer Hub instance by ensuring only authenticated users can gain access.
- Enabling authorization by allowing you to define access controls based on user and group memberships synchronized from your IdP.

Tip

To explore Developer Hub features in a non-production environment, you can:

- To use Developer Hub without external IdP, enable the guest user to skip configuring authentication and authorization, log in as the guest user, and access all Developer Hub features.
- To use Developer Hub without authorization policies and features relying on the software catalog, you can enable the`dangerouslyAllowSignInWithoutUserInCatalog` resolver option. This setting bypasses the check requiring a user to be in the catalog but still enforces authentication.

Important

Developer Hub uses a one-way synchronization model, where user and group data flow from your Identity Provider to the Developer Hub software catalog. As a result, deleting users or groups manually through the Developer Hub Web UI or REST API might be ineffective or cause inconsistencies, since Developer Hub will create those entities again during the next import.

## Chapter 2. Enable or disable authentication with the Guest userCopy link

For trial or non-production environments, you can enable guest access to skip configuring authentication and authorization and explore Developer Hub features.

For production environments, disable guest access to ensure secure authentication and authorization.

### 2.1. Enable the Guest loginCopy link

To allow users to log in as a guest on the login page, enable the guest login option.

Procedure

In the`app-config.yaml` file, set the authentication environment to`development`:

```yaml
auth:
  environment: development
```

1. Restart the Developer Hub application to apply the changes.

Verification

1. Go to the login page of your Developer Hub instance.
2. Verify that the option to log in as a guest is available.

### 2.2. Disable the Guest loginCopy link

To prevent users from logging in as a guest on the login page, disable the guest login option.

Procedure

In the`app-config.yaml` file, set the authentication environment to`production`:

```yaml
auth:
  environment: production
```

1. Restart the Developer Hub application to apply the changes.

Verification

1. Go to the login page of your Developer Hub instance.
2. Verify that the option to log in as a guest is no longer available.

## Chapter 3. Enable authentication with Red Hat Build of Keycloak (RHBK)Copy link

You can enable authentication with RHBK to allow users to sign in to Developer Hub using their RHBK credentials. This integration also allows you to provision user and group data from RHBK or from an LDAP directory to the Developer Hub software catalog, enabling features that rely on synchronized user and group data. If the default provisioning options do not meet your needs, you can also create a custom transformer to provision user and group data from RHBK to the software catalog.

### 3.1. Enable user authentication with Red Hat Build of Keycloak (RHBK), with optional stepsCopy link

Authenticate users with Red Hat Build of Keycloak by provisioning the users and groups from Red Hat Build of Keycloak to the Developer Hub software catalog, and configuring the Red Hat Build of Keycloak authentication provider in Red Hat Developer Hub.

Prerequisites

You have [access to RHBK](https://docs.redhat.com/en/documentation/red_hat_build_of_keycloak/26.0/html-single/getting_started_guide/index#getting-started-zip-log-in-to-the-admin-console) with an [admin user](https://docs.redhat.com/en/documentation/red_hat_build_of_keycloak/26.0/html-single/getting_started_guide/index#getting-started-zip-create-an-admin-user).

Tip

Alternatively, ask your RHBK administrator to prepare in RHBK the required realm and client.

- You [added a custom Developer Hub application configuration](https://docs.redhat.com/en/documentation/red_hat_developer_hub/1.9/html-single/configuring_red_hat_developer_hub/index), and have enough permissions to change it.
- RHBK version 26.0.

Procedure

Register your Developer Hub app in RHBK:

Use an existing realm, or [create a realm](https://docs.redhat.com/en/documentation/red_hat_build_of_keycloak/26.0/html-single/getting_started_guide/index#getting-started-zip-create-a-realm), with a distinctive Name such as. Save the value for the next step:

- RHBK realm base URL, such as: /realms/.

To register your Developer Hub in RHBK, in the created realm, [secure the first application](https://docs.redhat.com/en/documentation/red_hat_build_of_keycloak/26.0/html-single/getting_started_guide/index#getting-started-zip-secure-the-first-application), with:

Save the values for the next step:

- Client ID
- Client Secret

Add your RHBK credentials and the session secret key to Developer Hub, by adding the following key-value pairs to [your Developer Hub secrets](https://docs.redhat.com/en/documentation/red_hat_developer_hub/1.9/html-single/configuring_dynamic_plugins/index#provisioning-your-custom-configuration). You can use these secrets in the Developer Hub configuration files by using their environment variable name.

`KEYCLOAK_CLIENT_ID` Enter the saved Client ID.`KEYCLOAK_CLIENT_SECRET` Enter the saved Client Secret.`KEYCLOAK_BASE_URL` Enter the saved RHBK realm base URL.`KEYCLOAK_REALM` Enter the realm name to provision users.`KEYCLOAK_LOGIN_REALM` Enter the realm name to authenticate users.`SESSION_SECRET` Enter the created session secret key.

Enable the Keycloak catalog provider plugin in your`dynamic-plugins.yaml` file.

The plugin is named after RHBK upstream project.

This plugin imports RHBK users and groups to the Developer Hub software catalog.

```yaml
plugins:
  - package: './dynamic-plugins/dist/backstage-community-plugin-catalog-backend-module-keycloak-dynamic'
    disabled: false
```

The OIDC provider authentication backend plugin requires Developer Hub to support sessions. Enable session support by adding the session secret to your`app-config.yaml` file:

```yaml
auth:
  session:
    secret: ${SESSION_SECRET}
```

Enable provisioning RHBK users and groups to the Developer Hub software catalog, by adding the`catalog.providers.keycloakOrg` section to your`app-config.yaml` file:

```yaml
catalog:
  providers:
    keycloakOrg:
      default:
        baseUrl: ${KEYCLOAK_BASE_URL}
        clientId: ${KEYCLOAK_CLIENT_ID}
        clientSecret: ${KEYCLOAK_CLIENT_SECRET}
        realm: ${KEYCLOAK_REALM}
        loginRealm: ${KEYCLOAK_LOGIN_REALM}
```

`baseUrl` Enter your RHBK server URL, defined earlier.`clientId` Enter your Developer Hub application client ID in RHBK, defined earlier.`clientSecret` Enter your Developer Hub application client secret in RHBK, defined earlier.`realm` Enter the realm name to provision users.`loginRealm` Enter the realm name to authenticate users. :_mod-docs-content-type: SNIPPET

Optional: Add optional fields to the`keycloackOrg` catalog provider section in your`app-config.yaml` file:

```yaml
catalog:
  providers:
    keycloakOrg:
      default:
        baseUrl: ${KEYCLOAK_BASE_URL}
        clientId: ${KEYCLOAK_CLIENT_ID}
        clientSecret: ${KEYCLOAK_CLIENT_SECRET}
        realm: ${KEYCLOAK_REALM}
        loginRealm: ${KEYCLOAK_LOGIN_REALM}
        userQuerySize: 100
        groupQuerySize: 100
        schedule:
          frequency: { hours: 1 }
          timeout: { minutes: 50 }
          initialDelay: { seconds: 15}
```

`userQuerySize` Enter the user count to query simultaneously. Default value:`100`.`groupQuerySize` Enter the group count to query simultaneously. Default value:`100`.`schedule`

`frequency` Enter the schedule frequency. Supports cron, ISO duration, and "human duration" as used in code.`timeout` Enter the timeout for the user provisioning job. Supports ISO duration and "human duration" as used in code.`initialDelay` Enter the initial delay to wait for before starting the user provisioning job. Supports ISO duration and "human duration" as used in code. :_mod-docs-content-type: SNIPPET

Enable the RHBK authentication provider, by adding the OIDC provider section in your`app-config.yaml` file:

```yaml
auth:
  environment: production
  providers:
    oidc:
      production:
        metadataUrl: ${KEYCLOAK_BASE_URL}
        clientId: ${KEYCLOAK_CLIENT_ID}
        clientSecret: ${KEYCLOAK_CLIENT_SECRET}
        prompt: auto
signInPage: oidc
```

`environment: production` Mark the environment as`production` to hide the Guest login in the Developer Hub home page.`metadataUrl`,`clientId`,`clientSecret` Configure the OIDC provider with your secrets.`prompt`

Enter`auto` to allow the identity provider to automatically determine whether to prompt for credentials or bypass the login redirect if an active RHBK session exists.

The identity provider defaults to`none`, which assumes that you are already logged in. Sign-in requests without an active session are rejected.

`signInPage` Enter`oidc` to enable the OIDC provider as default sign-in provider. :_mod-docs-content-type: SNIPPET

Optional: Add optional fields to the OIDC authentication provider section in your`app-config.yaml` file:

```yaml
auth:
  providers:
    oidc:
      production:
        metadataUrl: ${KEYCLOAK_BASE_URL}
        clientId: ${KEYCLOAK_CLIENT_ID}
        clientSecret: ${KEYCLOAK_CLIENT_SECRET}
        callbackUrl: ${KEYCLOAK_CALLBACK_URL}
        tokenEndpointAuthMethod: ${KEYCLOAK_TOKEN_ENDPOINT_METHOD}
        tokenSignedResponseAlg: ${KEYCLOAK_SIGNED_RESPONSE_ALG}
        additionalScopes: ${KEYCLOAK_SCOPE}
        signIn:
          resolvers:
            - resolver: oidcSubClaimMatchingKeycloakUserId
            - resolver: preferredUsernameMatchingUserEntityName
            - resolver: emailMatchingUserEntityProfileEmail
            - resolver: emailLocalPartMatchingUserEntityName
              dangerouslyAllowSignInWithoutUserInCatalog: true
        sessionDuration: { hours: 24 }
  backstageTokenExpiration: { minutes: _<user_defined_value>_ }
signInPage: oidc
```

`callbackUrl` RHBK callback URL.`tokenEndpointAuthMethod` Enter your token endpoint authentication method.`tokenSignedResponseAlg` Token signed response algorithm.`additionalScopes` Enter additional RHBK scopes to request for during the authentication flow.`signIn`

`resolvers`

After successful authentication, the user signing in must be resolved to an existing user in the Developer Hub catalog. To best match users securely for your use case, consider configuring a specific resolver.

Enter the resolver list to override the default resolver:`oidcSubClaimMatchingKeycloakUserId`.

Available values:

`oidcSubClaimMatchingKeycloakUserId` Matches the user with the immutable`sub` parameter from OIDC to the RHBK user ID. Consider using this resolver for enhanced security.`emailLocalPartMatchingUserEntityName` Matches the email local part with the user entity name.`emailMatchingUserEntityProfileEmail` Matches the email with the user entity profile email.`preferredUsernameMatchingUserEntityName`

Matches the preferred username with the user entity name.

The authentication provider tries each sign-in resolver in order until it succeeds, and fails if none succeed.

Warning

In production mode, configure only one resolver to make sure users are securely matched.

`dangerouslyAllowSignInWithoutUserInCatalog: true`

Configure the sign-in resolver to bypass the user provisioning requirement in the Developer Hub software catalog.

Warning

In production mode, do not enable the`dangerouslyAllowSignInWithoutUserInCatalog` option.

`sessionDuration` Lifespan of the user session. Enter a duration in`ms` library format (such as '24h', '2 days'), ISO duration, or "human duration" as used in code.`backstageTokenExpiration`

Enter a value to modify the Developer Hub token expiration from its default value of one hour. It refers to the validity of short-term cryptographic tokens, not to the session duration. The expiration value must be set between 10 minutes and 24 hours.

Warning

If multiple valid refresh tokens are issued due to frequent refresh token requests, older tokens will remain valid until they expire. Enhance security and prevent potential misuse of older tokens by enabling a refresh token rotation strategy in your RHBK realm.

1. From the Configure section of the navigation menu, click Realm Settings.
2. From the Realm Settings page, click the Tokens tab.
3. From the Refresh tokens section of the Tokens tab, toggle the Revoke Refresh Token to the Enabled position.

To disable the guest login option, in the`app-config.yaml` file, set the authentication environment to`production`:

```yaml
auth:
  environment: production
```

Verification

To verify user and group provisioning, check the console logs.

Successful synchronization example:

```plaintext
2025-06-27T16:02:34.647Z catalog info Read 5 Keycloak users and 3 Keycloak groups in 0.4 seconds. Committing... class="KeycloakOrgEntityProvider" taskId="KeycloakOrgEntityProvider:default:refresh" taskInstanceId="db55c34b-46b3-402b-b12f-2fbc48498e82" trace_id="606f80a9ce00d1c86800718c4522f7c6" span_id="7ebc2a254a546e90" trace_flags="01"

2025-06-27T16:02:34.650Z catalog info Committed 5 Keycloak users and 3 Keycloak groups in 0.0 seconds. class="KeycloakOrgEntityProvider" taskId="KeycloakOrgEntityProvider:default:refresh" taskInstanceId="db55c34b-46b3-402b-b12f-2fbc48498e82" trace_id="606f80a9ce00d1c86800718c4522f7c6" span_id="7ebc2a254a546e90" trace_flags="01"
```

To verify RHBK user authentication:

1. Go to the Developer Hub login page.
2. Your Developer Hub sign-in page displays Sign in using OIDC and the Guest user sign-in is disabled.
3. Log in with OIDC by using the saved Username and Password values.

### 3.2. Enable user provisioning with LDAPCopy link

When Red Hat Build of Keycloak (RHBK) depends on Lightweight Directory Access Protocol (LDAP) to resolve user and group identities, you can opt to provision users and groups from LDAP directly to the Red Hat Developer Hub software catalog, rather than using the RHBK provisioning mechanism.

Prerequisites

You have collected the required LDAP credentials:

LDAP URL Your LDAP server URL, such as`ldaps://ds.example.net`. Bind dn Your bind distinguished name, such as`cn=admin,OU=Users,DC=rhdh,DC=test` LDAP secret Your LDAP secret. Recommended: LDAP certificates and keys

To use a secure LDAP connexion (`ldaps://`): you stored your LDAP certificates and keys respectively in the`ldap_certs.pem` and`ldap_keys.pem` files.

Warning

In production mode, use a secure LDAP connexion.

- You have configured authentication with Red Hat Build of Keycloak (RHBK).

Procedure

Enter your LDAP credentials to Developer Hub, by adding the`LDAP_SECRET` environment variable to [your Developer Hub secrets](https://docs.redhat.com/en/documentation/red_hat_developer_hub/1.9/html-single/configuring_red_hat_developer_hub/index#provisioning-your-custom-configuration).

```plaintext
$ oc patch secret my-rhdh-secrets --patch '{"stringData": { "LDAP_SECRET": "<ldap_secret>" }}'
```

 Enter your LDAP secret.

Recommended: To use a secure LDAP connection (`ldaps://`), add your LDAP certificates and keys files to a {a-platform-generic} secret.

```plaintext
$ oc create secret generic my-rhdh-ldap-secrets \
    --from-file=./ldap_certs.pem \
    --from-file=./ldap_keys.pem
```

Enable the LDAP catalog provider plugin in your`dynamic-plugins.yaml` file.

```yaml
plugins:
  - package: './dynamic-plugins/dist/backstage-plugin-catalog-backend-module-ldap-dynamic'
    disabled: false
```

Enable provisioning GitHub users and groups to the Developer Hub software catalog, by adding the LDAP catalog provider section to your`app-config.yaml` file:

Enter the mandatory fields:

```yaml
catalog:
  providers:
    ldapOrg:
      default:
        target: ldaps://ds.example.net
        bind:
          dn: cn=admin,ou=Users,dc=rhdh
          secret: ${LDAP_SECRET}
        users:
          - dn: OU=Users,OU=RHDH Local,DC=rhdh,DC=test
            options:
              filter: (uid=*)
        groups:
          - dn: OU=Groups,OU=RHDH Local,DC=rhdh,DC=test
        schedule:
          frequency: PT1H
          timeout: PT15M
```

`target` Enter your LDAP server URL, such as`ldaps://ds.example.net`.`bind`

Enter your service account information:

`dn` Enter your service account distinguished name (DN), such as`cn=admin,OU=Users,DC=rhdh,DC=test``secret` Enter the name of the variable containing your LDAP secret:`${LDAP_SECRET}`.

`users`

Enter information about how to find your users:

`dn` Enter the DN containing the user information.`options`

`filter` Enter your filter, such as`(uid=*)` to provision to the RHDH software catalog only users with an existing`uid`.

`groups`

Enter information about how to find your groups:

`dn` Enter the DN containing the group information.

`schedule`

Enter your schedule information:

`frequency` Enter your schedule frequency, in the cron, ISO duration, or "human duration" format.`timeout` Enter your schedule timeout, in the ISO duration or "human duration" format.`initialDelay` Enter your schedule initial delay, in the ISO duration or "human duration" format.

Optional: To change how Developer Hub maps LDAP user fields to the software catalog, enter optional`maps` and`set` fields.

```yaml
catalog:
  providers:
    ldapOrg:
      default:
        target: ldaps://ds.example.net
        bind:
          dn: cn=admin,ou=Users,dc=rhdh
          secret: ${LDAP_SECRET}
        users:
          - dn: OU=Users,OU=RHDH Local,DC=rhdh,DC=test
            options:
              filter: (uid=*)
            map:
              rdn: uid
              name: uid
              description: {}
              displayName: cn
              email: mail
              picture: {}
              memberOf: memberOf
            set:
              metadata.customField: 'hello'
        groups:
          - dn: OU=Groups,OU=RHDH Local,DC=rhdh,DC=test
        schedule:
          frequency: PT1H
          timeout: PT15M
```

`rdn` To change the default value:`uid`, enter the relative distinguished name of each entry.`name` To change the default value:`uid`, enter the LDAP field to map to the RHDH`metadata.name` field.`description` To set a value, enter the LDAP field to map to the RHDH`metadata.description` field.`displayName` To change the default value:`cn`, enter the LDAP field to map to the RHDH`metadata.displayName` field.`email` To change the default value:`mail`, enter the LDAP field to map to the RHDH`spec.profile.email` field.`picture` To set a value, enter the LDAP field to map to the RHDH`spec.profile.picture` field.`memberOf` To change the default value:`memberOf`, enter the LDAP field to map to the RHDH`spec.memberOf` field.`set` To set a value, enter the hard coded JSON to apply to the entities after ingestion, such as`metadata.customField: 'hello'`.

Optional: To change how Developer Hub maps LDAP group fields to the software catalog, enter optional`groups.maps` fields.

```yaml
catalog:
  providers:
    ldapOrg:
      default:
        target: ldaps://ds.example.net
        bind:
          dn: cn=admin,ou=Users,dc=rhdh
          secret: ${LDAP_SECRET}
        users:
          - dn: OU=Users,OU=RHDH Local,DC=rhdh,DC=test
            options:
              filter: (uid=*)
        groups:
          - dn: OU=Groups,OU=RHDH Local,DC=rhdh,DC=test
            map:
              rdn: uid
              name: uid
              description: {}
              displayName: cn
              email: mail
              picture: {}
              memberOf: memberOf
              members: member
              type: groupType
            set:
              metadata.customField: 'hello'
        schedule:
          frequency: PT1H
          timeout: PT15M
```

`rdn` To change the default value:`cn`, enter the relative distinguished name of each entry.`name` To change the default value:`cn`, enter the LDAP field to map to the RHDH`metadata.name` field.`description` To set a value, enter the LDAP field to map to the RHDH`metadata.description` field.`displayName` To change the default value:`cn`, enter the LDAP field to map to the RHDH`metadata.displayName` field.`email` To change the default value:`mail`, enter the LDAP field to map to the RHDH`spec.profile.email` field.`picture` To set a value, enter the LDAP field to map to the RHDH`spec.profile.picture` field.`memberOf` To change the default value:`memberOf`, enter the LDAP field to map to the RHDH`spec.memberOf` field.`members` To change the default value:`member`, enter the LDAP field to map to the RHDH`spec.children` field.`type` To change the default value:`groupType`, enter the LDAP field to map to the RHDH`spec.type` field.`set` To set a value, enter the hard coded JSON to apply to the entities after ingestion, such as`metadata.customField: 'hello'`.

Recommended: To use a secure LDAP connection (`ldaps://`), enter optional`tls` fields.

Optional`tls` fields

```yaml
catalog:
  providers:
    ldapOrg:
      default:
        target: ldaps://ds.example.net
        bind:
          dn: cn=admin,ou=Users,dc=rhdh
          secret: ${LDAP_SECRET}
        users:
ldapOrg:
  default:
    tls:
      rejectUnauthorized: true
      keys: '/path/to/keys.pem'
      certs: '/path/to/certs.pem'
```

`rejectUnauthorized`

Set to`false` to allow self-signed certificates

Warning

This option is not recommended for production.

`keys` Enter a file containing private keys in PEM format`certs` Enter a file containing cert chains in PEM format

Optional: Enter configuration for vendor-specific attributes to set custom attribute names for distinguished names (DN) and universally unique identifiers (UUID) in LDAP directories. Default values are defined per supported vendor and automatically detected.

```yaml
catalog:
  providers:
    ldapOrg:
      default:
        vendor:
          dnAttributeName: customDN
          uuidAttributeName: customUUID
```

`dnAttributeName` Enter the attribute name that holds the distinguished name (DN) for an entry.`uuidAttributeName` Enter the attribute name that holds a universal unique identifier (UUID) for an entry.

Optional: Enter low level users and groups configuration in the`options` subsection.

```yaml
catalog:
  providers:
    ldapOrg:
      default:
        target: ldaps://ds.example.net
        bind:
          dn: cn=admin,ou=Users,dc=rhdh
          secret: ${LDAP_SECRET}
        users:
          options:
            scope: sub
            filter: (uid=*)
            attributes:
              - cn
              - uid
              - description
            paged:
            pageSize: 500
        groups:
          options:
            scope: sub
            filter: (cn=*)
            attributes:
              - cn
              - uid
              - description
            paged:
              pageSize: 500
              pagePause: true
```

`scope`

To change the default value:`one`, enter how deep the search should go within the directory tree:

- `base` to search only the base DN.
- `one` to search one level below the base DN.
- `sub` to search all descendant entries.

`filter`

To change the default value:`(objectclass=*)`, enter your LDAP filter. With the default mapping:

For groups, enter`(cn=*)`

Tip

When you change the mapping, also update the filter.

- For users, enter`(uid=*)` to make sure only users with valid uid field is synced, since users without uid will cause error and ingestion fails.

`attributes` To change the default value: all attributes`['*', '+']`, enter the array of attribute names to import from LDAP.`paged`

Enter a value to enable paged results.

`pageSize` Enter a value to set the results page size, such as`500`.`pagePause` Enter`true` to tell the client to wait for the asynchronous results of the next page, when the page limit has been reached.

Recommended: To use a secure LDAP connection (`ldaps://`), mount your LDAP certificates and keys files in your Developer Hub deployment, by editing your Backstage custom resource.

```plaintext
kind: Backstage
spec:
  application:
    extraFiles:
      mountPath: /opt/ldap-secrets
      secrets:
        - name: my-rhdh-database-database-secrets
          key: ldap-certs.pem, ldap-keys.pem
```

Verification

To verify user and group provisioning, check the console logs.

Successful synchronization example:

```plaintext
2025-10-15T20:45:49.072Z catalog info Read 4 LDAP users and 6 LDAP groups in 0.3 seconds. Committing... class="LdapOrgEntityProvider" taskId="LdapOrgEntityProvider:default:refresh" taskInstanceId="9bb48fd5-2f55-4096-9fd0-61cee6679952" trace_id="6a318e2eadba84e20df773948668aa4c" span_id="cbec568cb6e64985" trace_flags="01"
2025-10-15T20:45:49.075Z catalog info Committed 4 LDAP users and 6 LDAP groups in 0.0 seconds. class="LdapOrgEntityProvider" taskId="LdapOrgEntityProvider:default:refresh" taskInstanceId="9bb48fd5-2f55-4096-9fd0-61cee6679952" trace_id="6a318e2eadba84e20df773948668aa4c" span_id="cbec568cb6e64985" trace_flags="01"
```

### 3.3. Create a custom transformer to provision users from Red Hat Build of Keycloak (RHBK) to the software catalogCopy link

Customize how Red Hat Developer Hub provisions users and groups to Red Hat Developer Hub software catalog entities, by creating a backend module that uses the`keycloakTransformerExtensionPoint` to offer custom user and group transformers for the Keycloak backend.

Prerequisites

- You have enabled provisioning users from Red Hat Build of Keycloak (RHBK) to the software catalog.

Procedure

Create a new backend module with the`yarn new` command.

```plaintext
$ yarn new
? What type of module would you like to create? backend-plugin-module
? Enter the ID of the plugin [required] catalog
? Enter the ID of the module [required] keycloak-org-transformer
```

The command creates a plugin named:`catalog-backend-module-keycloak-org-transformer`.

Install required packages:

```plaintext
$ yarn --cwd plugins/catalog-backend-module-keycloak-org-transformer add @backstage/plugin-catalog-backend-module-keycloak-org
```

Package and export the plugin as a Dynamic Plugin, and embed the required package for the custom transformer.

```plaintext
$ npx @red-hat-developer-hub/cli@latest plugin export \
  --embed-package @backstage/plugin-catalog-backend-module-keycloak-org
```

Important

Verify that the installed plugin version is compatible with the Backstage version

See plugin marketplace for the version to import.

1. Refer to the [sample plugin](https://github.com/redhat-developer/rhdh-examples/tree/main/plugins/backstage-plugin-catalog-backend-module-keycloak-org-transformer) and implement`plugins/catalog-backend-module-keycloak-org-transformer/src/module.ts`.

Verification

Developer Hub imports the users and groups each time when started. Check the console logs to verify the synchronization result.

Successful synchronization example:

```plaintext
{"class":"KeycloakOrgEntityProvider","level":"info","message":"Read 3 Keycloak users and 2 Keycloak groups in 1.5 seconds. Committing...","plugin":"catalog","service":"backstage","taskId":"KeycloakOrgEntityProvider:default:refresh","taskInstanceId":"bf0467ff-8ac4-4702-911c-380270e44dea","timestamp":"2024-09-25 13:58:04"}
{"class":"KeycloakOrgEntityProvider","level":"info","message":"Committed 3 Keycloak users and 2 Keycloak groups in 0.0 seconds.","plugin":"catalog","service":"backstage","taskId":"KeycloakOrgEntityProvider:default:refresh","taskInstanceId":"bf0467ff-8ac4-4702-911c-380270e44dea","timestamp":"2024-09-25 13:58:04"}
```

- After the first import is complete, go to the Catalog page and select User to view the list of users.
- When you select a user, you see the information imported from RHBK.
- You can select a group, view the list, and access or review the information imported from RHBK.
- You can log in with an RHBK account.

## Chapter 4. Enable authentication with GitHubCopy link

You can enable authentication with GitHub to allow users to sign in to Developer Hub using their GitHub credentials. This integration also allows you to provision user and group data from GitHub to the Developer Hub software catalog, enabling features that rely on synchronized user and group data.

### 4.1. Enable user authentication with GitHub, with optional stepsCopy link

Authenticate users with GitHub by provisioning the users and groups from GitHub to the Developer Hub software catalog, and configuring the GitHub authentication provider in Red Hat Developer Hub.

Prerequisites

You have enough permissions in GitHub to create and manage a [GitHub App](https://docs.github.com/en/apps/overview).

Tip

Alternatively, ask your GitHub administrator to prepare the required GitHub App.

- You have [added a custom Developer Hub application configuration](https://docs.redhat.com/en/documentation/red_hat_developer_hub/1.9/html-single/configuring_red_hat_developer_hub/index), and have enough permissions to change it.

Procedure

Allow Developer Hub to authenticate with GitHub, by creating a GitHub App.

Note

Use a GitHub App instead of an OAuth app to use fine-grained permissions, use short-lived tokens, scale with the number of installations by avoiding rate limits, and have a more transparent integration by avoiding to request user input.

[Register a GitHub App](https://docs.github.com/en/apps/creating-github-apps/registering-a-github-app/registering-a-github-app) with the following configuration:

GitHub App name Enter a unique name identifying your GitHub App, such as`authenticating-with-rhdh- `. Homepage URL Enter your Developer Hub URL:`https:// `. Authorization callback URL Enter your Developer Hub authentication backend URL:`https:// /api/auth/github/handler/frame`. Webhook Clear "Active". Organization permissions Enable`Read-only` access to Members. Where can this GitHub App be installed? Select`Only on this account`.

Save the following values for the next step:

- Client ID
- Client secret

Add your GitHub credentials to Developer Hub by adding the following key/value pairs to [your Developer Hub secrets](https://docs.redhat.com/en/documentation/red_hat_developer_hub/1.9/html-single/configuring_red_hat_developer_hub/index#provisioning-your-custom-configuration). You can use these secrets in the Developer Hub configuration files by using their environment variable name.

`GITHUB_CLIENT_ID` Enter the saved Client ID.`GITHUB_CLIENT_SECRET` Enter the saved Client Secret.`GITHUB_URL` Enter the GitHub host domain: [https://github.com](https://github.com/).`GITHUB_ORG` Enter your GitHub organization name, such as` `.

Enable the GitHub catalog provider plugin in your`dynamic-plugins.yaml` file to import GitHub users and groups to the Developer Hub software catalog.

```yaml
plugins:
  - package: './dynamic-plugins/dist/backstage-plugin-catalog-backend-module-github-org-dynamic'
    disabled: false
```

Enable provisioning GitHub users and groups to the Developer Hub software catalog by adding the GitHub catalog provider section to your`app-config.yaml` file:

```yaml
catalog:
  providers:
    githubOrg:
      id: githuborg
      githubUrl: "${GITHUB_URL}"
      orgs: [ "${GITHUB_ORG}" ]
      schedule:
        frequency:
          minutes: 30
        initialDelay:
          seconds: 15
        timeout:
          minutes: 15
```

`id`

Enter a stable identifier for this provider, such as`githuborg`.

Warning

Entities from this provider are associated with this identifier. Therefore, do not to change the identifier over time since that might lead to orphaned entities or conflicts.

`githubUrl` Enter the configured secret variable name:`${GITHUB_URL}`.`orgs` Enter the configured secret variable name:`${GITHUB_ORG}`.`schedule.frequency` Enter your schedule frequency, in the cron, ISO duration, or "human duration" format.`schedule.timeout` Enter your schedule timeout, in the ISO duration or "human duration" format.`schedule.initialDelay` Enter your schedule initial delay, in the ISO duration or "human duration" format.

Enable the GitHub authentication provider, by adding the GitHub authentication provider section to your`app-config.yaml` file:

```yaml
auth:
  environment: production
  providers:
    github:
      production:
        clientId: ${GITHUB_CLIENT_ID}
        clientSecret: ${GITHUB_CLIENT_SECRET}
signInPage: github
```

`environment` Enter`production` to disable the Guest login option in the Developer Hub login page.`clientId` Enter the configured secret variable name:`${GITHUB_CLIENT_ID}`.`clientSecret` Enter the configured secret variable name:`${GITHUB_CLIENT_SECRET}`.`signInPage` Enter`github` to enable the GitHub provider as your Developer Hub sign-in provider. :_mod-docs-content-type: SNIPPET

Optional: Add optional fields to the GitHub authentication provider section in your`app-config.yaml` file:

```yaml
auth:
  environment: production
  providers:
    github:
      production:
        clientId: ${GITHUB_CLIENT_ID}
        clientSecret: ${GITHUB_CLIENT_SECRET}
        callbackUrl: <your_intermediate_service_url/handler>
        sessionDuration: { hours: 24 }
        signIn:
          resolvers:
            - resolver: usernameMatchingUserEntityName
              dangerouslyAllowSignInWithoutUserInCatalog: true
signInPage: github
```

`callbackUrl` Enter the callback URL that GitHub uses when initiating an OAuth flow, such as:. Define it when Developer Hub is not the immediate receiver, such as in cases when you use one OAuth app for many Developer Hub instances.`sessionDuration` Enter the user session lifespan, in`ms` library format (such as '24h', '2 days'), ISO duration, or "human duration".`signIn`

`resolvers` After successful authentication, Developer Hub resolves the user signing in to an existing user in the Developer Hub catalog. Configure a specific resolver to best match users securely for your use case..

Enter the resolver list to override the default resolver:`usernameMatchingUserEntityName`.

The authentication provider tries each sign-in resolver in order until it succeeds. If none of the attempts succeed, the sign-in fails.

Warning

In production mode, configure only one resolver to make sure users are securely matched.

`resolver`

Enter the sign-in resolver name. Available resolvers:

- `usernameMatchingUserEntityName`
- `preferredUsernameMatchingUserEntityName`
- `emailMatchingUserEntityProfileEmail`

`dangerouslyAllowSignInWithoutUserInCatalog`

Enter`true` to configure the sign-in resolver to bypass the user provisioning requirement in the Developer Hub software catalog.

Warning

In production more, do not enable`dangerouslyAllowSignInWithoutUserInCatalog`.

To disable the guest login option, in the`app-config.yaml` file, set the authentication environment to`production`:

```yaml
auth:
  environment: production
```

Verification

Verify user and group provisioning by checking the console logs.

Successful synchronization example:

```plaintext
{"class":"GithubMultiOrgEntityProvider","level":"info","message":"Reading GitHub users and teams for org: rhdh-dast","plugin":"catalog","service":"backstage","target":"https://github.com","taskId":"GithubMultiOrgEntityProvider:githuborg:refresh","taskInstanceId":"801b3c6c-167f-473b-b43e-e0b4b780c384","timestamp":"2024-09-09 23:55:58"}
{"class":"GithubMultiOrgEntityProvider","level":"info","message":"Read 7 GitHub users and 2 GitHub groups in 0.4 seconds. Committing...","plugin":"catalog","service":"backstage","target":"https://github.com","taskId":"GithubMultiOrgEntityProvider:githuborg:refresh","taskInstanceId":"801b3c6c-167f-473b-b43e-e0b4b780c384","timestamp":"2024-09-09 23:55:59"}
```

To verify GitHub authentication:

1. Go to the Developer Hub login page.
2. Your Developer Hub sign-in page displays Sign in using GitHub and the Guest user sign-in is disabled.
3. Log in with a GitHub account.

Additional resources

- [Integrating Red Hat Developer Hub with GitHub](https://docs.redhat.com/en/documentation/red_hat_developer_hub/1.9/html-single/integrating_red_hat_developer_hub_with_github/index)

### 4.2. Enable user authentication with GitHub as an auxiliary authentication providerCopy link

If your primary authentication provider is not GitHub, users might lack the permissions needed for templates or plugins that require GitHub access. The recommended solution is to configure GitHub as an auxiliary authentication provider. This approach uses the primary provider for user identity management and the auxiliary provider to grant the necessary GitHub permissions, without re-resolving the user’s identity.

Give users access to these features by configuring GitHub as an auxiliary authentication provider.

Prerequisites

You have enough permissions in GitHub to create and manage a [GitHub App](https://docs.github.com/en/apps/overview).

Tip

Alternatively, ask your GitHub administrator to prepare the required GitHub App.

- You have [added a custom Developer Hub application configuration](https://docs.redhat.com/en/documentation/red_hat_developer_hub/1.9/html-single/configuring_red_hat_developer_hub/index), and have enough permissions to change it.
- You have configured a primary authentication provider to provision user and group identities to the Red Hat Developer Hub software catalog, and establish Developer Hub user sessions.

Procedure

Add the`auth.providers.github` section to your`app-config.yaml` file:

```yaml
auth:
  providers:
    github:
      production:
        clientId: ${GITHUB_CLIENT_ID}
        clientSecret: ${GITHUB_CLIENT_SECRET}
        disableIdentityResolution: true
```

where:`clientId`:: Enter the configured secret variable name:`${GITHUB_CLIENT_ID}`.

`clientSecret` Enter the configured secret variable name:`${GITHUB_CLIENT_SECRET}`.`disableIdentityResolution`

Enter`true` to skip user identity resolution for this provider to enable sign-in from an auxiliary authentication provider.

Warning

Do not enable this setting on the primary authentication provider you plan on using for sign-in and identity management.

To disable the guest login option, in the`app-config.yaml` file, set the authentication environment to`production`:

```yaml
auth:
  environment: production
```

Verification

1. Go to the Developer Hub login page.
2. Log in with your primary authentication provider account.
3. In the top user menu, go to Settings > Authentication Providers.
4. In the GitHub line, click the Sign in button and log in.
5. In the GitHub line, the button displays Sign out.

Additional resources

- [Integrating Red Hat Developer Hub with GitHub](https://docs.redhat.com/en/documentation/red_hat_developer_hub/1.9/html-single/integrating_red_hat_developer_hub_with_github/index)

## Chapter 5. Enable authentication with Microsoft AzureCopy link

You can enable authentication wit Microsoft Azure to allow users to sign in to Developer Hub using their Microsoft Azure credentials. This integration also allows you to provision user and group data from Microsoft Azure to the Developer Hub software catalog, enabling features that rely on synchronized user and group data.

### 5.1. Enable user authentication with Microsoft Azure, with optional stepsCopy link

Authenticate users with Microsoft Azure by provisioning the users and groups from Microsoft Azure to the Developer Hub software catalog, and configuring the Microsoft Azure authentication provider in Red Hat Developer Hub.

Prerequisites

You have the permission to register an application in Azure.

Tip

Alternatively, ask your Azure administrator to prepare the required Azure application.

Your Developer Hub backend can access the following hosts:

`login.microsoftonline.com` The Microsoft Azure authorization server, which enables the authentication flow.`graph.microsoft.com` The server for retrieving organization data, including user and group data, to import into the Developer Hub catalog.

- You [added a custom Developer Hub application configuration](https://docs.redhat.com/en/documentation/red_hat_developer_hub/1.9/html-single/configuring_red_hat_developer_hub/index), and have enough permissions to change it.

Procedure

Register your Developer Hub app in Azure, [by using the Azure portal](https://learn.microsoft.com/en-us/entra/identity-platform/scenario-web-app-sign-user-app-configuration?tabs=aspnetcore#register-an-app-by-using-the-azure-portal).

Browse to Applications > App registrations, and create a New registration with the configuration:

Name Enter a name to identify your application in Azure, such as. Supported account types Select Accounts in this organizational directory only. Redirect URI

Select a platform Select Web. URL Enter the backend authentication URI set in Developer Hub:`https:// /api/auth/microsoft/handler/frame`

On the Applications > App registrations > > Manage > API permissions page, Add a Permission, Microsoft Graph, select the following permissions:

Application Permissions

`GroupMember.Read.All`,`User.Read.All`

Enter permissions that enable provisioning user and groups to the Developer Hub software catalog.

Optional: Grant admin consent for these permissions. Even if your company does not require admin consent, consider doing so as it means users do not need to individually consent the first time they access Developer Hub.

Delegated Permissions

`User.Read`,`email`,`offline_access`,`openid`,`profile`

Enter permissions that enable authenticating users.

Optional: Enter optional custom scopes for the Microsoft Graph API that you define both here and in your`app-config.yaml` Developer Hub configuration file.

Save the following values for the next step:

- Directory (tenant) ID
- Application (client) ID
- Application (client) Secret ID

Add your Azure credentials to Developer Hub, by adding the following key/value pairs to [your Developer Hub secrets](https://docs.redhat.com/en/documentation/red_hat_developer_hub/1.9/html-single/configuring_red_hat_developer_hub/index#provisioning-your-custom-configuration):

`MICROSOFT_TENANT_ID` Enter your saved Directory (tenant) ID.`MICROSOFT_CLIENT_ID` Enter your saved Application (client) ID.`MICROSOFT_CLIENT_SECRET` Enter your saved Application (client) secret.

Enable the Microsoft Graph catalog provider plugin in your`dynamic-plugins.yaml` file. This plugin imports Azure users and groups to the Developer Hub software catalog.

```yaml
plugins:
  - package: './dynamic-plugins/dist/backstage-plugin-catalog-backend-module-msgraph-dynamic'
    disabled: false
```

Enable provisioning Azure users and groups to the Developer Hub software catalog, by adding the Microsoft Graph catalog provider section in your`app-config.yaml` file:

```yaml
catalog:
  providers:
    microsoftGraphOrg:
      providerId:
        target: https://graph.microsoft.com/v1.0
        tenantId: ${MICROSOFT_TENANT_ID}
        clientId: ${MICROSOFT_CLIENT_ID}
        clientSecret: ${MICROSOFT_CLIENT_SECRET}
        schedule:
          frequency:
            hours: 1
          timeout:
            minutes: 50
          initialDelay:
            minutes: 50
```

`target` Enter`https://graph.microsoft.com/v1.0` to define the MSGraph API endpoint the provider is connecting to. You might change this parameter to use a different version, such as the [beta endpoint](https://learn.microsoft.com/en-us/graph/api/overview?view=graph-rest-beta#call-the-beta-endpoint).`tenandId` Enter the configured secret variable name:`${MICROSOFT_TENANT_ID}`.`clientId` Enter the configured secret variable name:`${MICROSOFT_CLIENT_ID}`.`clientSecret` Enter the configured secret variable name:`${MICROSOFT_CLIENT_SECRET}`.`schedule`

`frequency` Enter the schedule frequency in the cron, ISO duration, or human duration format. In a large organization, user provisioning might take a long time, therefore avoid using a low value.`timeout` Enter the schedule timeout in the ISO duration or human duration format. In a large organization, user provisioning might take a long time, therefore avoid using a low value.`initialDelay` Enter the schedule initial delay in the ISO duration or human duration format. :_mod-docs-content-type: SNIPPET

Optional: Add optional fields to the Microsoft authentication provider section in your`app-config.yaml` file:

```yaml
catalog:
  providers:
    microsoftGraphOrg:
      providerId:
        authority: https://login.microsoftonline.com/
        queryMode: advanced
        user:
          expand: manager
          filter: accountEnabled eq true and userType eq 'member'
          loadPhotos: true
          select: ['id', 'displayName', 'description']
        userGroupMember:
          filter: "displayName eq 'Backstage Users'"
          search: '"description:One" AND ("displayName:Video" OR "displayName:Drive")'
        group:
          expand: member
          filter: securityEnabled eq false and mailEnabled eq true and groupTypes/any(c:c+eq+'Unified')
          search: '"description:One" AND ("displayName:Video" OR "displayName:Drive")'
          select: ['id', 'displayName', 'description']
```

`authority` Enter your [Azure authority URL](https://learn.microsoft.com/en-us/graph/deployments#app-registration-and-token-service-root-endpoints) if it is different from the default:`https://login.microsoftonline.com`.`queryMode` Enter`advanced` when the default`basic` query mode is insufficient for your queries to the Microsoft Graph API. See [Microsoft Azure advanced queries](https://learn.microsoft.com/en-us/graph/aad-advanced-queries).`user`

Add this section to configure optional user query parameters.

`expand`

Enter your expansion parameter to include the expanded resource or collection referenced by a single relationship (navigation property) in your results. A single request can expand only one relationship. See [Microsoft Graph query expand parameter](https://learn.microsoft.com/en-us/graph/query-parameters#expand-parameter).

You can combine this parameter with`userGroupMember.filter` or`user.filter`.

`filter`

Enter your user filter. See [Microsoft Graph API](https://learn.microsoft.com/en-us/graph/api/resources/user?view=graph-rest-1.0#properties) and [Microsoft Graph API query filter parameters syntax](https://learn.microsoft.com/en-us/graph/query-parameters#filter-parameter).

This parameter and`userGroupMember.filter` are mutually exclusive, specify only one.

`loadPhotos` Developer Hub loads photos by default. Enter`false` to avoid loading user photos.`select` Enter the [Microsoft Graph resource type](https://learn.microsoft.com/en-us/graph/api/resources/schemaextension?view=graph-rest-1.0) list to retrieve.

`userGroupMember`

Add this section to use group membership to get users.

`filter`

Enter your filter to filter groups and fetch their members.

This parameter and`user.filter` are mutually exclusive, specify only one.

`search`

Enter your search query to search for groups and fetch their members.

This parameter and`user.filter` are mutually exclusive, specify only one.

`group`

Enter your configuration to get groups.

`expand`

Enter your expansion parameter to include the expanded resource or collection referenced by a single relationship (navigation property) in your results. A single request can expand only one relationship. See [Customize Microsoft Graph responses with query parameters](https://learn.microsoft.com/en-us/graph/query-parameters#expand-parameter).

You can combine this parameter with`user.filter` or`userGroupMember.filter`.

`filter` Enter your group filter parameter. See [Microsoft Graph API query group syntax](https://learn.microsoft.com/en-us/graph/api/resources/group?view=graph-rest-1.0#properties).`search` Enter your group search parameter. See [Microsoft Graph API query search parameter](https://learn.microsoft.com/en-us/graph/search-query-parameter).`select` Enter the [Microsoft Graph resource type](https://learn.microsoft.com/en-us/graph/api/resources/schemaextension?view=graph-rest-1.0) list to retrieve. :_mod-docs-content-type: SNIPPET

Enable Azure authentication, by adding the Microsoft authentication provider to your`app-config.yaml` file content:

```yaml
auth:
  environment: production
  providers:
    microsoft:
      production:
        clientId: ${MICROSOFT_CLIENT_ID}
        clientSecret: ${MICROSOFT_CLIENT_SECRET}
        tenantId: ${MICROSOFT_TENANT_ID}
signInPage: microsoft
```

`environment` Enter`production` to disable the Guest login option in the Developer Hub login page.`clientId` Enter the configured secret variable name:`${MICROSOFT_CLIENT_ID}`.`clientSecret` Enter the configured secret variable name:`${MICROSOFT_CLIENT_SECRET}`.`tenantId` Enter the configured secret variable name:`${MICROSOFT_TENANT_ID}`.`signInPage` Enter`microsoft` to set the Azure provider as your Developer Hub sign-in provider. :_mod-docs-content-type: SNIPPET

Optional: Add optional fields to the Microsoft authentication provider section in your`app-config.yaml` file:

```yaml
auth:
  environment: production
  providers:
    microsoft:
      production:
        clientId: ${MICROSOFT_CLIENT_ID}
        clientSecret: ${MICROSOFT_CLIENT_SECRET}
        tenantId: ${MICROSOFT_TENANT_ID}
        domainHint: ${MICROSOFT_TENANT_ID}
        additionalScopes:
           - Mail.Send
        sessionDuration:
          hours: 24
        signIn:
          resolvers:
            - resolver: usernameMatchingUserEntityName
              dangerouslyAllowSignInWithoutUserInCatalog: true
signInPage: microsoft
```

`domainHint`

Enter the tenant ID to reduce login friction for users with accounts in multiple tenants, by automatically filtering out accounts from other tenants.

For more information, see [Home Realm Discovery](https://learn.microsoft.com/en-us/entra/identity/enterprise-apps/home-realm-discovery-policy).

- Leave this parameter empty, or enter the tenant ID when your application registration is single-tenant.
- Leave this parameter empty when your application registration is multi-tenant.

`additionalScopes`

Enter the list of additional scopes to add scopes for the application registration. The default and mandatory value lists following scopes:

- `openid`
- `offline_access`
- `profile`
- `email`
- `User.Read`

`sessionDuration` Lifespan of the user session. Enter a duration in`ms` library (such as '24h', '2 days'), ISO duration, or "human duration" format.`signIn.resolvers`

After successful authentication, Developer Hub resolves the user signing in to an existing user in the Developer Hub catalog. To best match users securely for your use case, consider configuring a specific resolver.

Enter the resolver list to override the default resolver:`userIdMatchingUserEntityAnnotation`.

The authentication provider tries each sign-in resolver in order until it succeeds, and fails if none succeed.

Warning

In production mode, configure only one resolver to make sure users are securely matched.

`resolver`

Enter the sign-in resolver name. Available resolvers:

`emailMatchingUserEntityAnnotation` Use this resolver to look up the user by matching their Microsoft email to the email entity annotation.`emailLocalPartMatchingUserEntityName` Use this resolver to look up the user by matching their Microsoft email user name to the user entity name.`emailMatchingUserEntityProfileEmail` Use this resolver to look up the user by matching their Microsoft email to the user entity profile email.

`dangerouslyAllowSignInWithoutUserInCatalog`

Enter`true` to configure the sign-in resolver to bypass the user provisioning requirement in the Developer Hub software catalog.

Warning

In production mode, do not enable`dangerouslyAllowSignInWithoutUserInCatalog`.

To disable the guest login option, in the`app-config.yaml` file, set the authentication environment to`production`:

```yaml
auth:
  environment: production
```

Verification

To verify user and group provisioning, check the console logs for`MicrosoftGraphOrgEntityProvider` events.

Successful synchronization example:

```plaintext
2025-06-23T13:37:55.804Z catalog info Read 9 msgraph users and 3 msgraph groups in 1.5 seconds. Committing... class="MicrosoftGraphOrgEntityProvider" taskId="MicrosoftGraphOrgEntityProvider:providerId:refresh" taskInstanceId="e104a116-6481-4ceb-9bc4-0f8f9581f959" trace_id="e4c633659cffd6b1529afa55a5bfbad7" span_id="76affd0420e8baa6" trace_flags="01"

2025-06-23T13:37:55.811Z catalog info Committed 9 msgraph users and 3 msgraph groups in 0.0 seconds. class="MicrosoftGraphOrgEntityProvider" taskId="MicrosoftGraphOrgEntityProvider:providerId:refresh" taskInstanceId="e104a116-6481-4ceb-9bc4-0f8f9581f959" trace_id="e4c633659cffd6b1529afa55a5bfbad7" span_id="76affd0420e8baa6" trace_flags="01"
```

To verify Azure user authentication:

1. Go to the Developer Hub login page.
2. Your Developer Hub sign-in page displays Sign in using Microsoft and the Guest user sign-in is disabled.
3. Log in with an Azure account.

## Chapter 6. Enable authentication with GitLabCopy link

By configuring GitLab as an identity provider, you can enable users to authenticate with Red Hat Developer Hub and import your GitLab users and groups into the software catalog.

### 6.1. Enable user authentication with GitLabCopy link

You can enable authentication with GitLab to allow users to sign in to Developer Hub using their GitLab credentials. This integration also allows you to provision user and group data from GitLab to the Developer Hub software catalog, enabling features that rely on synchronized user and group data.

Prerequisites

You have enough permissions in GitLab to create and manage a [GitLab OAuth 2 application](https://docs.gitlab.com/api/oauth2/).

Tip

Alternatively, ask your GitLab administrator to prepare the required GitLab OAuth 2 application.

- You must create a GitLab personal access token with the`read_api` scope.
- You have [added a custom Developer Hub application configuration](https://docs.redhat.com/en/documentation/red_hat_developer_hub/1.9/html-single/configuring_red_hat_developer_hub/index), and have enough permissions to change it.

Procedure

Register GitLab OAuth 2 application to allow RHDH to authenticate with GitLab. You must use the required callback URL and permissions.

[Register a GitLab OAuth 2 application](https://docs.gitlab.com/integration/oauth_provider/) using the following configuration:

GitLab OAuth 2 application name Enter a unique name, such as`authenticating-with-rhdh- `. Redirect URI Enter your Developer Hub URL:`https:// `. Authorization callback URL Enter your authentication backend URL:`https:// /api/auth/gitlab/handler/frame`. Authorized application scope Enable`email`,`profile`,`openid`, and`read_user`.

Save the application and record these values for the next step:

- OAuth 2 Client ID, available in the Application ID field
- OAuth 2 Client secret, accessible by selecting Copy in the Secret field

Add your GitLab credentials to [your RHDH secrets](https://docs.redhat.com/en/documentation/red_hat_developer_hub/1.9/html-single/configuring_red_hat_developer_hub/index#provisioning-your-custom-configuration) using the following key/value pairs. Use these environment variables in your RHDH configuration files.

`GITLAB_HOST` Enter your GitLab host:` `.`GITLAB_CLIENT_ID` Enter the saved OAuth 2 Client ID.`GITLAB_CLIENT_SECRET` Enter the saved OAuth 2 Client Secret.`GITLAB_URL` Enter the GitLab host domain: ` `.`GITLAB_PARENT_ORG` Enter your GitLab organization name, such as` `.

Enable provisioning for GitLab users and groups to the Developer Hub software catalog by adding the provider section to your RHDH`app-config.yaml` file:

```yaml
catalog:
  providers:
    gitlab:
      default:
        host: ${GITLAB_HOST}
        orgEnabled: true
        group: ${GITLAB_PARENT_ORG}
        relations:
          - INHERITED
          - DESCENDANTS
          - SHARED_FROM_GROUPS
        groupPattern: [\s\S]*
        restrictUsersToGroup: true
        includeUsersWithoutSeat: true
        schedule:
          initialDelay:
            seconds: 0
          frequency:
            minutes: 50
          timeout:
            minutes: 50
```

`host` Enter your GitLab instance address:`${GITLAB_HOST}`.`orgEnabled` Set to`true` to enable the ingestion of GitLab organizational data, such as users and groups. For the Gitlab site, you must also provide a value for the`group` parameter.`group` Enter your configured [GitLab parent group](https://docs.gitlab.com/user/group/):`${GITLAB_PARENT_ORG}`.`relations`

Optional. Specify the types of group memberships to include during ingestion. You can use the following values:

- `INHERITED`: Optional. Includes members of any ancestor groups as members of the current group.
- `DESCENDANTS`: Optional. Includes members of any descendant groups as members of the current group.
- `SHARED_FROM_GROUPS`: Optional. Includes members of any invited groups as members of the current group.

`groupPattern` Optional. Filters found groups based on provided pattern. Defaults to`[\s\S]*`, which means to not filter anything.`restrictUsersToGroup` Set to`true` to ingest only users who are direct members of the configured group.`includeUsersWithoutSeat` Set to`true` to include users who do not occupy a paid seat. This setting applies only to GitLab SaaS.`schedule.initialDelay` Enter your schedule initial delay, in the ISO duration or "human duration" format.`schedule.frequency` Enter your schedule frequency, in the cron, ISO duration, or "human duration" format.`schedule.timeout` Enter your schedule timeout, in the ISO duration or "human duration" format.

Enable the GitLab authentication provider by adding the GitLab authentication provider section to your RHDH`app-config.yaml` file:

```yaml
includeTransitiveGroupOwnership: true
signInPage: gitlab
auth:
  environment: production
  session:
    secret: <name_of_secret>
  providers:
    gitlab:
      production:
        audience: https://${GITLAB_HOST}
        clientId: ${GITLAB_CLIENT_ID}
        clientSecret: ${GITLAB_CLIENT_SECRET}
        callbackUrl: https://__<my_developer_hub_domain>__/api/auth/gitlab/handler/frame
```

`audience` Enter your GitLab instance address:`https://${GITLAB_HOST}``clientId` Enter the configured client ID:`${GITLAB_CLIENT_ID}`.`clientSecret` Enter the configured secret variable name:`${GITLAB_CLIENT_SECRET}`.`callbackUrl` Enter your Developer Hub authentication backend URL:`https:// /api/auth/gitlab/handler/frame`

Verification

1. Open RHDH and wait for first ingestion.
2. Log in with your GitLab account.

## Chapter 7. Enable service-to-service authenticationCopy link

To secure communication between services and verify identities without manual intervention, you can configure service-to-service authentication. Red Hat Developer Hub can use this mechanism for plugin-to-plugin communication, and for external service to plugin communication. Developer Hub supports the following service-to-service authentication methods:

- Static tokens
- JSON Web Key Sets (JWKS)

Important

The availability of service-to-service authentication might vary for REST APIs. Each plugin defines the restrictions on this feature. Consult your specific plugin’s documentation for detailed limitations.

For example, the RBAC plugin supports exclusively all`GET` requests, but no`POST` requests.

### 7.1. Enable service-to-service authentication by using a static tokenCopy link

You can use a static token to enable service-to-service authentication. This method is simpler to set up compared to using JSON Web Key Sets (JWKS) tokens, but it requires careful management of the static token to ensure security. While it might not be suitable for all scenarios, particularly for compliance needs, it offers a practical solution for many development and testing use cases.

The key to successful implementation lies in:

- Proper token generation and management.
- Careful access control and restriction.
- Regular monitoring and auditing.
- Following security best practices.

Some security best practices when using static tokens include:

Regular rotation Rotate tokens on a regular schedule to limit the impact of potential leaks. Document the rotation process to ensure consistency. Secure storage Never store tokens in plain text in the`app-config.yaml` configuration file. Instead, use the environment variable mechanism available in Developer Hub. Access control Implement the principle of least privilege, restricting tokens to specific plugins and operations; regularly review and update access permissions. Analyze logs Monitor and track token usage to identify unusual patterns and set up alerts for failed authentication attempts if you have a monitoring system integration available. Documentation Document all authentication methods in use and keep an inventory of all tokens and their purposes, and keep security policies up to date.

Static token authentication might be a good solution for simple, non-critical scenarios, such as:

Development and testing environments These require quick setup and configuration, simple debugging and troubleshooting, and easy integration with development tools. Static token authentication can be an easy option, especially when using ephemeral testing environments. Simple automation tasks Basic CI/CD pipelines, simple maintenance scripts, and basic monitoring systems. Internal tools and utilities Development tools, testing frameworks, and internal automation scripts.

However, static token authentication might not be suitable for:

- Production environments with high security requirements.
- Systems handling sensitive data.
- Large-scale deployments with many external services.
- Environments requiring frequent token rotation.

Prerequisites

- You have administrative access to configure Developer Hub in your OpenShift cluster.

Procedure

Generate a secure token.

You can use a tool such as Node.js:

```shell-session
$ node -p'require('crypto').randomBytes(24).toString('base64')'
```

This command generates a 24-byte random value and encodes it in base64 format. The resulting token is sufficiently strong for authentication purposes, and properly encoded for use in HTTP headers.

Add the generated token or JWKS URL to your`app-config.yaml` Developer Hub configuration file in OpenShift.

```yaml
backend:
  auth:
    - type: static
      options:
        token: "$<YOUR_SERVICE_TOKEN_ENV_VAR>"
        subject: "<your_service_name>"
      accessRestrictions:
        - plugin: "<target_plugin_name>"
```

`type` Enter`static` to specify that authentication is using a static token.`options`

Enter the configuration options for static token authentication.

`token` Enter the environment variable name from the earlier step.`subject`(Optional) Enter a unique identifier for the service that will be using this token.`plugin`(Optional) Enter the name of the target plugin that the service will communicate with.

Use the token in the`Authorization` header of your service requests.

When making requests from one service to another, include the static token in the`Authorization` header as follows:

```plaintext
Authorization: Bearer <your_generated_token>
```

Replace` ` with the actual token you generated in step 1.

For instance, to list all available locations in the catalog by using the`curl` command, you would use:

```shell-session
$ curl --location --request GET 'https://<my_developer_hub_domain>/api/catalog/locations' \
--header 'Content-Type: application/json' \
--header 'Authorization: Bearer <your_generated_token>'
```

1. Add the generated token in your Developer Hub secrets in OpenShift to define the` ` environment variable where your services can access it.

Verification

- In the Audit Logs of the service receiving the request, verify that Developer Hub authenticated the request successfully by using the`subject` value as the actor.

### 7.2. Enable service-to-service authentication by using JSON web key sets (JWKS) tokensCopy link

You can use JSON Web Key Sets (JWKS) tokens to enable service-to-service authentication.

Consider using JWKS tokens when you need a more secure and scalable authentication method compared to static tokens. While JWKS tokens require more setup and configuration, they offer enhanced security features that are crucial for production environments and sensitive applications:

Asymmetric encryption Your trusted Identity Provider issues JWKS tokens by using asymmetric encryption. JWKS uses a pair of shared keys: one public, one private, instead of a single shared static token. The Identity Provider signs the JSON Web Token (JWT) with its private key, then Developer Hub verifies it using the public key fetched from the JWKS endpoint. Developer Hub can validate these tokens without sharing secret keys directly. This means Developer Hub never has access to the private signing key, reducing the risk of compromise. Easy key rotation The Identity Provider can rotate signing keys regularly without requiring changes to Developer Hub afterward. This minimizes downtime and enhances security. Ability to validate claims JWKS tokens include claims such as issuer and audience. Developer Hub can verify these claims to ensure the token is from a trusted source and prevent the external service from using the token in unintended contexts.

The diagram illustrates the authentication flow between an external service and Developer Hub:

- The external service requests, receives, and returns an access token from the identity provider to request a resource from Developer Hub.
- The identity provider issues a JWKS token signed with its private key, and provides the public key via the JWKS endpoint.
- Developer Hub receives and validates the token and its claims.

Prerequisites

- You have administrative access to configure Developer Hub in your OpenShift cluster.
- Developer Hub can access a JWKS endpoint available from your Identity Provider.
- You have configured the external service to obtain a JWT from your Identity Provider and include it in the`Authorization` header of requests to Developer Hub.

Procedure

Add the JWKS URL to your`app-config.yaml` Developer Hub configuration file:

```yaml
backend:
  auth:
    externalAccess:
      - type: jwks
        options:
          url: <your_jwks_endpoint>
          algorithm: RS256
          issuer: <your_issuer_claim>
          audience: <your_audience_claim>
          subjectPrefix: <your_subject_prefix>
```

where:

`type` Enter`jwks` to specify that authentication is using JWKS tokens.`options`

Enter the configuration options for JWKS authentication.

`url` Enter the URL of your JWKS endpoint, such as`http://your-idp.example.com/well-known/jwks.json`.`algorithm`(Optional) Enter the signing algorithm used by your Identity Provider, such as`RS256`.`issuer`(Optional) Enter the expected issuer claim in the token`iss` field, such as`http://your-idp.example.com`.`audience`(Optional) Enter the expected audience claim in the token`aud` field, such as`management`.`subjectPrefix`(Optional) Enter a prefix to add to the subject claim, and to display in the Audit Log for debugging and tracking purposes, such as`your_prefix`.

### 7.3. Set access restrictions to external service tokensCopy link

By default, when you configure service-to-service access in Red Hat Developer Hub, any external service with a valid token has unrestricted access to all backend plugins and actions. To limit the scope of an external service, you can define access restrictions.

Procedure

Restrict access to specific plugins.

For example, to restrict access to the catalog plugin for the static tokens, add the following`accessRestrictions` section to your`app-config.yaml` Developer Hub configuration file:

```yaml
backend:
  auth:
    externalAccess:
      - type: static
          accessRestrictions:
          - plugin: catalog
```

`type` Specify whether this is a`jwks` or`static` token.`plugin` Specify the allowed plugin, such as`catalog`,`scaffolder`, or`techdocs`.

With this configuration:

- The token is only allowed to make requests to the`catalog` plugin.
- The token has unrestricted access to all actions within the`catalog` plugin.

Restrict access by action attributes, to filter permissions based on what kind of action to allow.

List the specific actions defined by the permission, such as`create` and`read`.

```yaml
backend:
  auth:
    externalAccess:
      - type: jwks
        accessRestrictions:
          - plugin: catalog
            permissionAttribute:
              action:
                - create
                - read
```

Restrict access by explicit permission IDs, to control access at the permission rule level.

List the exact ID of the permission to allow.

```yaml
backend:
  auth:
    externalAccess:
      - type: jwks
        accessRestrictions:
          - plugin: catalog
            permission:
              - catalog.entity.create
              - catalog.entity.read
```

By choosing between explicit permission IDs and action-based attributes, you can strike the right balance between precision and flexibility depending on your external service needs.

## Chapter 8. Enable auto-logout for inactive usersCopy link

To enhance security, you can configure Red Hat Developer Hub to automatically log out users after a specified period of inactivity. This helps prevent unauthorized access to stale user sessions.

Prerequisites

- You have administrative access to the Red Hat Developer Hub configuration files.

Procedure

Add the`auth.autologout` section to your`{my-app-config.yaml}` file.

```yaml
auth:
  autologout:
    enabled: true
    idleTimeoutMinutes: 60
    promptBeforeIdleSeconds: 10
    useWorkerTimers: false
    logoutIfDisconnected: true
```

where:

`enabled`

Enter`true` to enable auto-logout.

Enter`false` to disable auto-logout.

The default value is`false`.

`idleTimeoutMinutes`

(Optional) Enter the number of minutes of inactivity before automatically logging out the user.

The default value is`60` minutes.

`promptBeforeIdleSeconds`

(Optional) Enter the number of seconds before the auto-logout occurs to prompt the user about the pending logout.

The default value is`10` seconds.

`useWorkerTimers`

(Optional) Enter`false` to use main thread timers, when your browser does not support web workers. Your browser might stop timers in inactive tabs, which can affect the auto-logout functionality.

Enter`true` to use web worker timers for tracking user activity, and avoid issues when your browser stops timers in inactive tabs.

The default value is`false`.

`logoutIfDisconnected`

(Optional) Enter`true` to log out the users with no active connection, in case of network issues, or when they have no active Developer Hub tab open in their browser.

Enter`false` to keep the user logged in during temporary disconnections, or when they have no active Developer Hub tab open in their browser.

The default value is`true`.

1. Restart the Red Hat Developer Hub application to apply the changes.

Verification

1. Log in to the Red Hat Developer Hub application.
2. Remain inactive for the duration specified in`idleTimeoutMinutes`.
3. Observe that a prompt is displayed before the auto-logout occurs, as specified in`promptBeforeIdleSeconds`.
4. Confirm that you are automatically logged out after the inactivity period.

## Chapter 9. Troubleshoot authentication issuesCopy link

Learn how to troubleshoot authentication issues.

### 9.1. Reduce the size of issued tokensCopy link

By default, the authentication backend issues user identity tokens with ownership references of the user in the`ent` claim of the JSON Web Token (JWT) payload. This makes it easier for consumers of the token to resolve ownership of the user. However, depending on the structure of your organization and how you resolve ownership claims, the tokens can grow large and cause HTTP errors that prevent you from accessing parts of RHDH. Use the`omitIdentityTokenOwnershipClaim` flag to remove the`ent` claim from tokens and reduce their size.

Procedure

In the`app-config.yaml` file, set`omitIdentityTokenOwnershipClaim` to`true` as follows:

```yaml
auth:
  omitIdentityTokenOwnershipClaim: true
```

## Legal NoticeCopy link

Copyright © Red Hat.

Except as otherwise noted below, the text of and illustrations in this documentation are licensed by Red Hat under the Creative Commons Attribution–Share Alike 3.0 Unported license . If you distribute this document or an adaptation of it, you must provide the URL for the original version.

Red Hat, as the licensor of this document, waives the right to enforce, and agrees not to assert, Section 4d of CC-BY-SA to the fullest extent permitted by applicable law.

Red Hat, the Red Hat logo, JBoss, Hibernate, and RHCE are trademarks or registered trademarks of Red Hat, LLC. or its subsidiaries in the United States and other countries.

Linux® is the registered trademark of Linus Torvalds in the United States and other countries.

XFS is a trademark or registered trademark of Hewlett Packard Enterprise Development LP or its subsidiaries in the United States and other countries.

The OpenStack® Word Mark and OpenStack logo are trademarks or registered trademarks of the Linux Foundation, used under license.

All other trademarks are the property of their respective owners.

---

*Source: https://docs.redhat.com/en/documentation/red_hat_developer_hub/1.9/html-single/authorization_in_red_hat_developer_hub/index*

Authorization in Red Hat Developer Hub | Red Hat Developer Hub | 1.9 | Red Hat Documentation

 

 

 

 

 

 

 

 

# Authorization in Red Hat Developer Hub

---

Red Hat Developer Hub 1.9

## Configuring authorization by using role based access control (RBAC) in Red Hat Developer Hub

Red Hat Customer Content Services

Legal Notice

Abstract

Red Hat Developer Hub (RHDH) administrators can use role-based access control (RBAC) to manage authorizations of other users.

---

## PrefaceCopy link

Administrators can authorize users to perform actions and define what users can do in Developer Hub.

Role-based access control (RBAC) is a security concept that defines how to control access to resources in a system by specifying a mapping between users of the system and the actions that those users can perform on resources in the system. You can use RBAC to define roles with specific permissions and then assign the roles to users and groups.

RBAC on Developer Hub is built on top of the Permissions framework, which defines RBAC policies in code. Rather than defining policies in code, you can use the Developer Hub RBAC feature to define policies in a declarative fashion by using a simple CSV based format. You can define the policies by using Developer Hub web interface or REST API instead of editing the CSV directly.

An administrator can define authorizations in Developer Hub by taking the following steps:

Define roles and policies by combining the following methods:

- The Developer Hub policy administrator uses the Developer Hub web interface or REST API.
- The Developer Hub administrator edits the main Developer Hub configuration file.
- The Developer Hub administrator edits external files.

## Chapter 1. Enabling and giving access to the Role-Based Access Control (RBAC) featureCopy link

The Role-Based Access Control (RBAC) feature is disabled by default. Enable the RBAC plugin and declare policy administrators to start using RBAC features.

The permission policies for users and groups in the Developer Hub are managed by permission policy administrators. Only permission policy administrators can access the Role-Based Access Control REST API.

Prerequisites

- You have [added a custom Developer Hub application configuration](https://docs.redhat.com/en/documentation/red_hat_developer_hub/1.9/html-single/configuring_red_hat_developer_hub/index), and have necessary permissions to modify it.
- You have [enabled an authentication provider](https://docs.redhat.com/en/documentation/red_hat_developer_hub/1.9/html-single/authentication_in_red_hat_developer_hub/index).

Procedure

The RBAC plugin is installed but disabled by default. To enable the`./dynamic-plugins/dist/backstage-community-plugin-rbac` plugin, edit your`dynamic-plugins.yaml` with the following content.

`dynamic-plugins.yaml` fragment

```yaml
plugins:
  - package: ./dynamic-plugins/dist/backstage-community-plugin-rbac
    disabled: false
```

See [Installing and viewing plugins in Red Hat Developer Hub](https://docs.redhat.com/en/documentation/red_hat_developer_hub/1.9/html-single/installing_and_viewing_plugins_in_red_hat_developer_hub/index).

Declare policy administrators to enable a select number of authenticated users to configure RBAC policies through the REST API or Web UI, instead of modifying the CSV file directly. The permissions can be specified in a separate CSV file referenced in your`my-rhdh-app-config` config map, or permissions can be created using the REST API or Web UI.

To declare users such as as policy administrators, edit your custom Developer Hub ConfigMap, such as`app-config-rhdh`, and add following code to the`app-config.yaml` content:

`app-config.yaml` fragment

```yaml
permission:
  enabled: true
  rbac:
    admin:
      users:
        - name: user:default/<your_policy_administrator_name>
```

In order to display the available permissions provided by installed plugins in the Developer Hub UI, you must supply the corresponding list of plugin IDs. There are two ways to do this, by updating your application configuration or by using the RBAC REST API permissions endpoint.

To provide plugins by updating your application configuration, you can specify the plugins with permissions in your`app-config.yaml` file as follows:

`app-config.yaml` fragment

```yaml
permission:
  enabled: true
  rbac:
    admin:
      users:
        - name: user:default/<your_policy_administrator_name>
    pluginsWithPermission:
      - catalog
      - scaffolder
      - permission
```

1. To specify the plugins with permissions by using the RBAC REST API permissions endpoint, see the RBAC REST API permissions endpoint.

Verification

With RBAC enabled, most features are disabled by default.

1. Navigate to the Catalog page in RHDH. The Create button is not visible. You cannot create new components.
2. Navigate to the API page. The Register button is not visible.

Next steps

- Explicitly enable permissions to resources in Developer Hub.

## Chapter 2. Determining permission policy and role configuration sourceCopy link

You can configure Red Hat Developer Hub policy and roles by using different sources. To maintain data consistency, Developer Hub associates each permission policy and role with one unique source. You can only use this source to change the resource.

The available sources are:

Configuration file

Configure roles and policies in the Developer Hub`app-config.yaml` configuration file, for instance to declare your policy administrators.

The Configuration file pertains to the default`role:default/rbac_admin` role provided by the RBAC plugin. The default role has limited permissions to create, read, update, delete permission policies or roles, and to read catalog entities.

Note

In case the default permissions are insufficient for your administrative requirements, you can create a custom admin role with the required permission policies.

REST API Configure roles and policies by using the Developer Hub Web UI or by using the REST API. CSV file Configure roles and policies by using external CSV files. Legacy

The legacy source applies to policies and roles defined before RBAC backend plugin version`2.1.3`, and is the least restrictive among the source location options.

Important

Replace the permissions and roles using the legacy source with the permissions using the REST API or the CSV file sources.

Procedure

- To determine the source of a role or policy, use a`GET` request.

## Chapter 3. Designing your policy rulesCopy link

Carefully design your policies to avoid permission conflicts that can result in unintended access denials.

Red Hat Developer Hub applies policies as follows:

- The default policy is`deny`.
- A conditional rule overrides a basic rule.
- A`deny` basic rule overrides an`allow` basic rule.
- An`allow` conditional rule overrides a`deny` basic rule.
- A`deny` conditional rule overrides an`allow` conditional rule.

Procedure

1. Use`allow` rules to explicitly allow access.
2. Avoid creating`deny` rules unless you know precisely how they can affect existing basic`allow` rules and existing conditional rules.

## Chapter 4. Managing role-based access controls (RBAC) using the Red Hat Developer Hub Web UICopy link

Policy administrators can use the Developer Hub web interface (Web UI) to allocate specific roles and permissions to individual users or groups. Allocating roles ensures that access to resources and functionalities is regulated across the Developer Hub.

With the policy administrator role in Developer Hub, you can assign permissions to users and groups. This role allows you to view, create, modify, and delete the roles using Developer Hub Web UI.

### 4.1. Creating a role in the Red Hat Developer Hub Web UICopy link

You can create a role in the Red Hat Developer Hub using the Web UI.

Prerequisites

- You have enabled RBAC, have a policy administrator role in Developer Hub, and have added plugins with permission.

Procedure

Go to Administration at the bottom of the sidebar in the Developer Hub.

The RBAC tab appears, displaying all the created roles in the Developer Hub.

1. (Optional) Click any role to view the role information on the OVERVIEW page.
2. Click CREATE to create a role.
3. Enter the name and description of the role in the given fields and click NEXT.
4. Add users and groups using the search field, and click NEXT.
5. Select Plugin and Permission from the drop-downs in the Add permission policies section.
6. Select or clear the Policy that you want to set in the Add permission policies section, and click NEXT.
7. Review the added information in the Review and create section.
8. Click CREATE.

Verification

The created role appears in the list available in the RBAC tab.

### 4.2. Editing a role in the Red Hat Developer Hub Web UICopy link

You can edit a role in the Red Hat Developer Hub using the Web UI.

Note

The policies generated from a`policy.csv` or ConfigMap file cannot be edited or deleted using the Developer Hub Web UI.

Prerequisites

- You have enabled RBAC, have a policy administrator role in Developer Hub, and have added plugins with permission.
- The role that you want to edit is created in the Developer Hub.

Procedure

Go to Administration at the bottom of the sidebar in the Developer Hub.

The RBAC tab appears, displaying all the created roles in the Developer Hub.

1. (Optional) Click any role to view the role information on the OVERVIEW page.
2. Select the edit icon for the role that you want to edit.
3. Edit the details of the role, such as name, description, users and groups, and permission policies, and click NEXT.
4. Review the edited details of the role and click SAVE.

After editing a role, you can view the edited details of a role on the OVERVIEW page of a role. You can also edit a role’s users and groups or permissions by using the edit icon on the respective cards on the OVERVIEW page.

### 4.3. Deleting a role in the Red Hat Developer Hub Web UICopy link

You can delete a role in the Red Hat Developer Hub using the Web UI.

Note

The policies generated from a`policy.csv` or ConfigMap file cannot be edited or deleted using the Developer Hub Web UI.

Prerequisites

- You have enabled RBAC and have a policy administrator role in Developer Hub.
- The role that you want to delete is created in the Developer Hub.

Procedure

Go to Administration at the bottom of the sidebar in the Developer Hub.

The RBAC tab appears, displaying all the created roles in the Developer Hub.

Select the delete icon from the Actions column for the role that you want to delete.

Delete this role? pop-up appears on the screen.

1. (Optional) Click any role to view the role information on the OVERVIEW page.
2. Click DELETE.

## Chapter 5. Managing authorizations by using the REST APICopy link

To automate the maintenance of Red Hat Developer Hub permission policies and roles, you can use Developer Hub role-based access control (RBAC) REST API.

You can perform the following actions with the REST API:

Retrieve information about:

- All permission policies
- Specific permission policies
- Specific roles
- Static plugins permission policies

Create, update, or delete:

- Permission policy
- Role

### 5.1. Sending requests to the RBAC REST API by using the curl utilityCopy link

You can send RBAC REST API requests by using the curl utility.

Prerequisites

- You have access to the RBAC feature.

Procedure

Find your Bearer token to authenticate to the REST API.

1. In your browser, open the web console Network tab.
2. In the main screen, reload the Developer Hub Homepage.
3. In the web console Network tab, search for the`query?term=` network call.
4. Save the token in the response JSON for the next steps.

In a terminal, run the curl command and review the response:

`GET` request, or`DELETE` request not requiring JSON body data

```plaintext
$ curl -v \
  -H "Authorization: Bearer <token>" \
  -X <method> "https://<my_developer_hub_domain>/<endpoint>" \
```

`POST` or`PUT` request, or`DELETE` request requiring JSON body data

```plaintext
$ curl -v -H "Content-Type: application/json" \
  -H "Authorization: Bearer <token>" \
  -X <method> "https://<my_developer_hub_domain>/<endpoint>" \
  -d <body>
```

 Enter your saved authorization token. 

Enter the HTTP method for your API endpoint.

- `GET`: To retrieve specified information from a specified resource endpoint.
- `POST`: To create or update a resource.
- `PUT`: To update a resource.
- `DELETE`: To delete a resource.

https:// Enter your Developer Hub URL. Enter the API endpoint to which you want to send a request, such as`/api/permission/policies`. Enter the JSON body with data that your API endpoint requires with the HTTP`POST`, or`PUT` request, and might require with the HTTP`DELETE` request.

Example request to create a role

```plaintext
$ curl -v -H "Content-Type: application/json" \
  -H "Authorization: Bearer <token>" \
  -X POST "https://<my_developer_hub_domain>/api/permission/roles" \
  -d '{
      "memberReferences": ["group:default/example"],
      "name": "role:default/test",
      "metadata": { "description": "This is a test role" }
    }'
```

Example request to update a role

```plaintext
$ curl -v -H "Content-Type: application/json" \
  -H "Authorization: Bearer <token>" \
  -X PUT "https://<my_developer_hub_domain>/api/permission/roles/role/default/test" \
  -d '{
          "oldRole": {
            "memberReferences":  [ "group:default/example" ],
            "name": "role:default/test"
          },
          "newRole": {
            "memberReferences": [ "group:default/example", "user:default/test" ],
            "name": "role:default/test"
          }
        }'
```

Example request to create a permission policy

```plaintext
$ curl -v -H "Content-Type: application/json" \
  -H "Authorization: Bearer $token" \
  -X POST "https://<my_developer_hub_domain>/api/permission/policies" \
  -d '[{
      "entityReference":"role:default/test",
      "permission": "catalog-entity",
      "policy": "read", "effect":"allow"
    }]'
```

Example request to update a permission policy

```plaintext
$ curl -v -H "Content-Type: application/json" \
  -H "Authorization: Bearer $token" \
  -X PUT "https://<my_developer_hub_domain>/api/permission/policies/role/default/test" \
  -d '{
         "oldPolicy": [
           {
             "permission": "catalog-entity", "policy": "read", "effect": "allow"
           }
         ],
         "newPolicy":
           [
             {
               "permission": "policy-entity", "policy": "read", "effect": "allow"
             }
           ]
       }'
```

Example request to create a condition

```plaintext
$ curl -v -H "Content-Type: application/json" \
  -H "Authorization: Bearer $token" \
  -X POST "https://<my_developer_hub_domain>/api/permission/roles/conditions" \
  -d '{
      "result": "CONDITIONAL",
      "roleEntityRef": "role:default/test",
      "pluginId": "catalog",
      "resourceType": "catalog-entity",
      "permissionMapping": ["read"],
      "conditions": {
        "rule": "IS_ENTITY_OWNER",
        "resourceType": "catalog-entity",
        "params": {"claims": ["group:default/janus-authors"]}
      }
    }'
```

Example request to update a condition

```plaintext
$ curl -v -H "Content-Type: application/json" \
  -H "Authorization: Bearer $token" \
  -X PUT "https://<my_developer_hub_domain>/api/permission/roles/conditions/1" \
  -d '{
          "result":"CONDITIONAL",
          "roleEntityRef":"role:default/test",
          "pluginId":"catalog",
          "resourceType":"catalog-entity",
          "permissionMapping": ["read",  "update", "delete"],
          "conditions": {
            "rule": "IS_ENTITY_OWNER",
            "resourceType": "catalog-entity",
            "params": {"claims": ["group:default/janus-authors"]}
          }
       }'
```

Verification

Review the returned HTTP status code:

`200` OK The request was successful.`201` Created The request resulted in a new resource being successfully created.`204` No Content The request was successful, and the response payload has no more content.`400` Bad Request Input error with the request.`401` Unauthorized Lacks valid authentication for the requested resource.`403` Forbidden Refusal to authorize request.`404` Not Found Could not find requested resource.`409` Conflict Request conflict with the current state and the target resource.

### 5.2. Sending requests to the RBAC REST API by using a REST clientCopy link

You can send RBAC REST API requests using any REST client.

Prerequisites

- You have access to the RBAC feature.

Procedure

Find your Bearer token to authenticate to the REST API.

1. In your browser, open the web console Network tab.
2. In the main screen, reload the Developer Hub Homepage.
3. In the web console Network tab, search for the`query?term=` network call.
4. Save the token in the response JSON for the next steps.

In your REST client, run a command with the following parameters and review the response:

Authorization Enter your saved authorization token. HTTP method

Enter the HTTP method for your API endpoint.

- `GET`: To retrieve specified information from a specified resource endpoint.
- `POST`: To create or update a resource.
- `PUT`: To update a resource.
- `DELETE`: To delete a resource.

URL Enter your Developer Hub URL and API endpoint: https:// /, such as`https:// /api/permission/policies`. Body Enter the JSON body with data that your API endpoint might need with the HTTP`POST` request.

### 5.3. Sending requests to the RBAC REST API by using an external serviceCopy link

You can send GET requests to the RBAC REST API by using an external service authenticated by using a service-to-service token.

Prerequisites

- You have access to the RBAC feature.
- The external service can send HTTP GET requests, and is [authenticated by using a service-to-service token](https://docs.redhat.com/en/documentation/red_hat_developer_hub/1.9/html-single/authentication_in_red_hat_developer_hub/index#service-to-service-authentication).

Procedure

1. The external service sends a GET request to the RBAC REST API with the service-to-service token in the`Authorization` header.
2. The RBAC REST API validates the service-to-service token, and then processes the request if the token is valid. Otherwise, the RBAC REST API returns an error response.
3. The RBAC REST API returns the response to the external service.
4. The external service processes the response from the RBAC REST API.

### 5.4. Supported RBAC REST API endpointsCopy link

The RBAC REST API provides endpoints for managing roles, permissions, and conditional policies in the Developer Hub and for retrieving information about the roles and policies.

#### 5.4.1. RolesCopy link

The RBAC REST API supports the following endpoints for managing roles in the Red Hat Developer Hub.

[GET] /api/permission/roles

Returns all roles in Developer Hub.

Example response (JSON)

```plaintext
[
  {
    "memberReferences": ["user:default/username"],
    "name": "role:default/guests"
  },
  {
    "memberReferences": [
      "group:default/groupname",
      "user:default/username"
    ],
    "name": "role:default/rbac_admin"
  }
]
```

[GET] /api/permission/roles/ / / 

Returns information for a single role in Developer Hub.

Example response (JSON)

```plaintext
[
  {
    "memberReferences": [
      "group:default/groupname",
      "user:default/username"
    ],
    "name": "role:default/rbac_admin"
  }
]
```

[POST] /api/permission/roles/ / / 

Creates a role in Developer Hub.

Expand

`body`

The`memberReferences`,`group`,`namespace`, and`name` the new role to be created.

Request body

Required

| Table 5.1. Request parameters | Name | Description | Type | Presence |
| --- | --- | --- | --- | --- |

Show more

Example request body (JSON)

```plaintext
{
  "memberReferences": ["group:default/test"],
  "name": "role:default/test_admin"
}
```

Example response

```plaintext
201 Created
```

[PUT] /api/permission/roles/ / / 

Updates`memberReferences`,`kind`,`namespace`, or`name` for a role in Developer Hub.

Request parameters

The request body contains the`oldRole` and`newRole` objects:

Expand

`body`

The`memberReferences`,`group`,`namespace`, and`name` the new role to be created.

Request body

Required

| | Name | Description | Type | Presence |
| --- | --- | --- | --- | --- |

Show more

Example request body (JSON)

```plaintext
{
  "oldRole": {
    "memberReferences": ["group:default/test"],
    "name": "role:default/test_admin"
  },
  "newRole": {
    "memberReferences": ["group:default/test", "user:default/test2"],
    "name": "role:default/test_admin"
  }
}
```

Example response

```plaintext
200 OK
```

[DELETE] /api/permission/roles/ / /?memberReferences= 

Deletes the specified user or group from a role in Developer Hub.

Expand

`kind`

Kind of the entity

String

Required

`namespace`

Namespace of the entity

String

Required

`name`

Name of the entity

String

Required

`memberReferences`

Associated group information

String

Required

| Table 5.2. Request parameters | Name | Description | Type | Presence |
| --- | --- | --- | --- | --- |

Show more

Example response

```plaintext
204
```

[DELETE] /api/permission/roles/ / / 

Deletes a specified role from Developer Hub.

Expand

`kind`

Kind of the entity

String

Required

`namespace`

Namespace of the entity

String

Required

`name`

Name of the entity

String

Required

| Table 5.3. Request parameters | Name | Description | Type | Presence |
| --- | --- | --- | --- | --- |

Show more

Example response

```plaintext
204
```

#### 5.4.2. Permission policiesCopy link

The RBAC REST API supports the following endpoints for managing permission policies in the Red Hat Developer Hub.

[GET] /api/permission/policies

Returns permission policies list for all users.

Example response (JSON)

```plaintext
[
  {
    "entityReference": "role:default/test",
    "permission": "catalog-entity",
    "policy": "read",
    "effect": "allow",
    "metadata": {
      "source": "csv-file"
    }
  },
  {
    "entityReference": "role:default/test",
    "permission": "catalog.entity.create",
    "policy": "use",
    "effect": "allow",
    "metadata": {
      "source": "csv-file"
    }
  },
]
```

[GET] /api/permission/policies/ / / 

Returns permission policies related to the specified entity reference.

Expand

`kind`

Kind of the entity

String

Required

`namespace`

Namespace of the entity

String

Required

`name`

Name related to the entity

String

Required

| Table 5.4. Request parameters | Name | Description | Type | Presence |
| --- | --- | --- | --- | --- |

Show more

Example response (JSON)

```plaintext
[
  {
    "entityReference": "role:default/test",
    "permission": "catalog-entity",
    "policy": "read",
    "effect": "allow",
    "metadata": {
      "source": "csv-file"
    }
  },
  {
    "entityReference": "role:default/test",
    "permission": "catalog.entity.create",
    "policy": "use",
    "effect": "allow",
    "metadata": {
      "source": "csv-file"
    }
  }
]
```

[POST] /api/permission/policies

Creates a permission policy for a specified entity.

Expand

`entityReference`

Reference values of an entity including`kind`,`namespace`, and`name`

String

Required

`permission`

Permission from a specific plugin, resource type, or name

String

Required

`policy`

Policy action for the permission, such as`create`,`read`,`update`,`delete`, or`use`

String

Required

`effect`

Indication of allowing or not allowing the policy

String

Required

| Table 5.5. Request parameters | Name | Description | Type | Presence |
| --- | --- | --- | --- | --- |

Show more

Example request body (JSON)

```plaintext
[
  {
    "entityReference": "role:default/test",
    "permission": "catalog-entity",
    "policy": "read",
    "effect": "allow"
  }
]
```

Example response

```plaintext
201 Created
```

[PUT] /api/permission/policies/ / / 

Updates a permission policy for a specified entity.

Request parameters

The request body contains the`oldPolicy` and`newPolicy` objects:

Expand

`permission`

Permission from a specific plugin, resource type, or name

String

Required

`policy`

Policy action for the permission, such as`create`,`read`,`update`,`delete`, or`use`

String

Required

`effect`

Indication of allowing or not allowing the policy

String

Required

| | Name | Description | Type | Presence |
| --- | --- | --- | --- | --- |

Show more

Example request body (JSON)

```plaintext
{
  "oldPolicy": [
    {
      "permission": "catalog-entity",
      "policy": "read",
      "effect": "allow"
    },
    {
      "permission": "catalog.entity.create",
      "policy": "create",
      "effect": "allow"
    }
  ],
  "newPolicy": [
    {
      "permission": "catalog-entity",
      "policy": "read",
      "effect": "deny"
    },
    {
      "permission": "policy-entity",
      "policy": "read",
      "effect": "allow"
    }
  ]
}
```

Example response

```plaintext
200
```

[DELETE] /api/permission/policies/ / /?permission={value1}&policy={value2}&effect={value3}

Deletes a permission policy added to the specified entity.

Expand

`kind`

Kind of the entity

String

Required

`namespace`

Namespace of the entity

String

Required

`name`

Name related to the entity

String

Required

`permission`

Permission from a specific plugin, resource type, or name

String

Required

`policy`

Policy action for the permission, such as`create`,`read`,`update`,`delete`, or`use`

String

Required

`effect`

Indication of allowing or not allowing the policy

String

Required

| Table 5.6. Request parameters | Name | Description | Type | Presence |
| --- | --- | --- | --- | --- |

Show more

Example response

```plaintext
204 No Content
```

[DELETE] /api/permission/policies/ / / 

Deletes all permission policies added to the specified entity.

Expand

`kind`

Kind of the entity

String

Required

`namespace`

Namespace of the entity

String

Required

`name`

Name related to the entity

String

Required

| Table 5.7. Request parameters | Name | Description | Type | Presence |
| --- | --- | --- | --- | --- |

Show more

Example request body (JSON)

```plaintext
[
  {
    "entityReference": "role:default/test",
    "permission": "catalog-entity",
    "policy": "delete",
    "effect": "allow"
  },
  {
    "entityReference": "role:default/test",
    "permission": "catalog-entity",
    "policy": "update",
    "effect": "allow"
  }
]
```

Example response

```plaintext
204 No Content
```

[GET] /api/permission/plugins/policies

Returns permission policies for all static plugins.

Example response (JSON)

```plaintext
[
  {
    "pluginId": "catalog",
    "policies": [
      {
        "isResourced": true,
        "permission": "catalog-entity",
        "policy": "read"
      },
      {
        "isResourced": false,
        "permission": "catalog.entity.create",
        "policy": "create"
      },
      {
        "isResourced": true,
        "permission": "catalog-entity",
        "policy": "delete"
      },
      {
        "isResourced": true,
        "permission": "catalog-entity",
        "policy": "update"
      },
      {
        "isResourced": false,
        "permission": "catalog.location.read",
        "policy": "read"
      },
      {
        "isResourced": false,
        "permission": "catalog.location.create",
        "policy": "create"
      },
      {
        "isResourced": false,
        "permission": "catalog.location.delete",
        "policy": "delete"
      }
    ]
  },
  ...
]
```

[GET] /api/permission/plugins/id

Returns object with list plugin IDs:

Example response (JSON)

```plaintext
[
  {
    "ids": ["catalog", "permission"]
  }
]
```

[POST] /api/permission/plugins/id

Add more plugins IDs defined in the request object.

Request Parameters: object in JSON format.

Example request body (JSON)

```plaintext
[
  {
    "ids": ["scaffolder"]
  }
]
```

Returns a status code of 200 and JSON with actual object stored in the server:

Example response (JSON)

```plaintext
[
  {
    "ids": ["catalog", "permission", "scaffolder"]
  }
]
```

[DELETE] /api/permission/plugins/id

Delete plugins IDs defined in the request object.

Request Parameters: object in JSON format.

Example request body (JSON)

```plaintext
[
  {
    "ids": ["scaffolder"]
  }
]
```

Returns a status code of 200 and JSON with actual object stored in the server:

Example response (JSON)

```plaintext
[
  {
    "ids": ["catalog", "permission"]
  }
]
```

Note

In order to prevent an inconsistent state after a deployment restart, the REST API does not allow deletion of plugin IDs that were provided by using the application configuration. These ID values can only be removed through the configuration file.

#### 5.4.3. Conditional policiesCopy link

The RBAC REST API supports the following endpoints for managing conditional policies in the Red Hat Developer Hub.

[GET] /api/permission/plugins/condition-rules

Returns available conditional rule parameter schemas for the available plugins that are enabled in Developer Hub.

Example response (JSON)

```plaintext
[
   {
      "pluginId": "catalog",
      "rules": [
         {
            "name": "HAS_ANNOTATION",
            "description": "Allow entities with the specified annotation",
            "resourceType": "catalog-entity",
            "paramsSchema": {
               "type": "object",
               "properties": {
                  "annotation": {
                     "type": "string",
                     "description": "Name of the annotation to match on"
                  },
                  "value": {
                     "type": "string",
                     "description": "Value of the annotation to match on"
                  }
               },
               "required": [
                  "annotation"
               ],
               "additionalProperties": false,
               "$schema": "http://json-schema.org/draft-07/schema#"
            }
         },
         {
            "name": "HAS_LABEL",
            "description": "Allow entities with the specified label",
            "resourceType": "catalog-entity",
            "paramsSchema": {
               "type": "object",
               "properties": {
                  "label": {
                     "type": "string",
                     "description": "Name of the label to match on"
                  }
               },
               "required": [
                  "label"
               ],
               "additionalProperties": false,
               "$schema": "http://json-schema.org/draft-07/schema#"
            }
         },
         {
            "name": "HAS_METADATA",
            "description": "Allow entities with the specified metadata subfield",
            "resourceType": "catalog-entity",
            "paramsSchema": {
               "type": "object",
               "properties": {
                  "key": {
                     "type": "string",
                     "description": "Property within the entities metadata to match on"
                  },
                  "value": {
                     "type": "string",
                     "description": "Value of the given property to match on"
                  }
               },
               "required": [
                  "key"
               ],
               "additionalProperties": false,
               "$schema": "http://json-schema.org/draft-07/schema#"
            }
         },
         {
            "name": "HAS_SPEC",
            "description": "Allow entities with the specified spec subfield",
            "resourceType": "catalog-entity",
            "paramsSchema": {
               "type": "object",
               "properties": {
                  "key": {
                     "type": "string",
                     "description": "Property within the entities spec to match on"
                  },
                  "value": {
                     "type": "string",
                     "description": "Value of the given property to match on"
                  }
               },
               "required": [
                  "key"
               ],
               "additionalProperties": false,
               "$schema": "http://json-schema.org/draft-07/schema#"
            }
         },
         {
            "name": "IS_ENTITY_KIND",
            "description": "Allow entities matching a specified kind",
            "resourceType": "catalog-entity",
            "paramsSchema": {
               "type": "object",
               "properties": {
                  "kinds": {
                     "type": "array",
                     "items": {
                        "type": "string"
                     },
                     "description": "List of kinds to match at least one of"
                  }
               },
               "required": [
                  "kinds"
               ],
               "additionalProperties": false,
               "$schema": "http://json-schema.org/draft-07/schema#"
            }
         },
         {
            "name": "IS_ENTITY_OWNER",
            "description": "Allow entities owned by a specified claim",
            "resourceType": "catalog-entity",
            "paramsSchema": {
               "type": "object",
               "properties": {
                  "claims": {
                     "type": "array",
                     "items": {
                        "type": "string"
                     },
                     "description": "List of claims to match at least one on within ownedBy"
                  }
               },
               "required": [
                  "claims"
               ],
               "additionalProperties": false,
               "$schema": "http://json-schema.org/draft-07/schema#"
            }
         }
      ]
   }
   ... <another plugin condition parameter schemas>
]
```

[GET] /api/permission/roles/conditions/:id

Returns conditions for the specified ID.

Example response (JSON)

```plaintext
{
  "id": 1,
  "result": "CONDITIONAL",
  "roleEntityRef": "role:default/test",
  "pluginId": "catalog",
  "resourceType": "catalog-entity",
  "permissionMapping": ["read"],
  "conditions": {
    "anyOf": [
      {
        "rule": "IS_ENTITY_OWNER",
        "resourceType": "catalog-entity",
        "params": {
          "claims": ["group:default/team-a"]
        }
      },
      {
        "rule": "IS_ENTITY_KIND",
        "resourceType": "catalog-entity",
        "params": {
          "kinds": ["Group"]
        }
      }
    ]
  }
}
```

[GET] /api/permission/roles/conditions

Returns list of all conditions for all roles.

Example response (JSON)

```plaintext
[
  {
    "id": 1,
    "result": "CONDITIONAL",
    "roleEntityRef": "role:default/test",
    "pluginId": "catalog",
    "resourceType": "catalog-entity",
    "permissionMapping": ["read"],
    "conditions": {
      "anyOf": [
        {
          "rule": "IS_ENTITY_OWNER",
          "resourceType": "catalog-entity",
          "params": {
            "claims": ["group:default/team-a"]
          }
        },
        {
          "rule": "IS_ENTITY_KIND",
          "resourceType": "catalog-entity",
          "params": {
            "kinds": ["Group"]
          }
        }
      ]
    }
  }
]
```

[POST] /api/permission/roles/conditions

Creates a conditional policy for the specified role.

Expand

`result`

Always has the value`CONDITIONAL`

String

Required

`roleEntityRef`

String entity reference to the RBAC role, such as`role:default/dev`

String

Required

`pluginId`

Corresponding plugin ID, such as`catalog`

String

Required

`permissionMapping`

Array permission action, such as`['read', 'update', 'delete']`

String array

Required

`resourceType`

Resource type provided by the plugin, such as`catalog-entity`

String

Required

`conditions`

Condition JSON with parameters or array parameters joined by criteria

JSON

Required

`name`

Name of the role

String

Required

`metadata.description`

The description of the role

String

Optional

| Table 5.8. Request parameters | Name | Description | Type | Presence |
| --- | --- | --- | --- | --- |

Show more

Example request body (JSON)

```plaintext
{
  "result": "CONDITIONAL",
  "roleEntityRef": "role:default/test",
  "pluginId": "catalog",
  "resourceType": "catalog-entity",
  "permissionMapping": ["read"],
  "conditions": {
    "rule": "IS_ENTITY_OWNER",
    "resourceType": "catalog-entity",
    "params": {
      "claims": ["group:default/team-a"]
    }
  }
}
```

Example response (JSON)

```plaintext
{
  "id": 1
}
```

[PUT] /permission/roles/conditions/:id

Updates a condition policy for a specified ID.

Expand

`result`

Always has the value`CONDITIONAL`

String

Required

`roleEntityRef`

String entity reference to the RBAC role, such as`role:default/dev`

String

Required

`pluginId`

Corresponding plugin ID, such as`catalog`

String

Required

`permissionMapping`

Array permission action, such as`['read', 'update', 'delete']`

String array

Required

`resourceType`

Resource type provided by the plugin, such as`catalog-entity`

String

Required

`conditions`

Condition JSON with parameters or array parameters joined by criteria

JSON

Required

`name`

Name of the role

String

Required

`metadata.description`

The description of the role

String

Optional

| Table 5.9. Request parameters | Name | Description | Type | Presence |
| --- | --- | --- | --- | --- |

Show more

Example request body (JSON)

```plaintext
{
  "result": "CONDITIONAL",
  "roleEntityRef": "role:default/test",
  "pluginId": "catalog",
  "resourceType": "catalog-entity",
  "permissionMapping": ["read"],
  "conditions": {
    "anyOf": [
      {
        "rule": "IS_ENTITY_OWNER",
        "resourceType": "catalog-entity",
        "params": {
          "claims": ["group:default/team-a"]
        }
      },
      {
        "rule": "IS_ENTITY_KIND",
        "resourceType": "catalog-entity",
        "params": {
          "kinds": ["Group"]
        }
      }
    ]
  }
}
```

Example response

```plaintext
200
```

[DELETE] /api/permission/roles/conditions/:id

Deletes a conditional policy for the specified ID.

Example response

```plaintext
204
```

#### 5.4.4. User statisticsCopy link

The`licensed-users-info-backend` plugin exposes various REST API endpoints to retrieve data related to logged-in users.

No additional configuration is required for the`licensed-users-info-backend` plugin. If the RBAC backend plugin is enabled, then an administrator role must be assigned to access the endpoints, as the endpoints are protected by the`policy.entity.read` permission.

The base URL for user statistics endpoints is`http://SERVER:PORT/api/licensed-users-info`, such as`http://localhost:7007/api/licensed-users-info`.

[GET] /users/quantity

Returns the total number of logged-in users.

Example request

```bash
curl -X GET "http://localhost:7007/api/licensed-users-info/users/quantity" \
-H "Content-Type: application/json" \
-H "Authorization: Bearer $token"
```

Example response

```plaintext
{ "quantity": "2" }
```

[GET] /users

Returns a list of logged-in users with their details.

Example request

```bash
curl -X GET "http://localhost:7007/api/licensed-users-info/users" \
-H "Content-Type: application/json" \
-H "Authorization: Bearer $token"
```

Example response

```plaintext
[
  {
    "userEntityRef": "user:default/dev",
    "lastTimeLogin": "Thu, 22 Aug 2024 16:27:41 GMT",
    "displayName": "John Leavy",
    "email": "dev@redhat.com"
  }
]
```

[GET] /users

Returns a list of logged-in users in CSV format.

Example request

```bash
curl -X GET "http://localhost:7007/api/licensed-users-info/users" \
-H "Content-Type: text/csv" \
-H "Authorization: Bearer $token"
```

Example response

```plaintext
userEntityRef,displayName,email,lastTimeLogin
user:default/dev,John Leavy,dev@redhat.com,"Thu, 22 Aug 2024 16:27:41 GMT"
```

## Chapter 6. Managing authorizations by using external filesCopy link

To automate Red Hat Developer Hub maintenance, you can configure permissions and roles in external files, before starting Developer Hub.

### 6.1. Defining authorizations in external files by using the OperatorCopy link

To automate Red Hat Developer Hub maintenance, you can define permissions and roles in external files, before starting Developer Hub. You need to prepare your files, upload them to your OpenShift Container Platform project, and configure Developer Hub to use the external files.

Prerequisites

- You enabled the RBAC feature.

Procedure

Define your policies in a`rbac-policies.csv` CSV file by using the following format:

Define role permissions:

```plaintext
p, <role_entity_reference>, <permission>, <action>, <allow_or_deny>
```

 Role entity reference, such as:`role:default/guest`. 

Permission, such as:`bulk.import`,`catalog.entity.read`, or`catalog.entity.refresh`, or permission resource type, such as:`bulk-import` or`catalog-entity`.

See: Permission policies reference.

 Action type, such as:`use`,`read`,`create`,`update`,`delete`. Access granted:`allow` or`deny`.

Assign the role to a group or a user:

```plaintext
g, <group_or_user>, <role_entity_reference>
```

 

Group, such as:`user:default/mygroup`, or user, such as:`user:default/myuser`.

Sample`rbac-policies.csv`

```plaintext
p, role:default/guests, catalog-entity, read, allow
p, role:default/guests, catalog.entity.create, create, allow
g, user:default/my-user, role:default/guests
g, group:default/my-group, role:default/guests
```

Define your conditional policies in a`rbac-conditional-policies.yaml` YAML file by using the following format:

```yaml
result: CONDITIONAL
roleEntityRef: <role_entity_reference>
pluginId: <plugin_id>
permissionMapping:
  - read
  - update
  - delete
conditions: <conditions>
```

See: Conditional policies reference.

Upload your`rbac-policies.csv` and`rbac-conditional-policies.yaml` files to a`rbac-policies` config map in your OpenShift Container Platform project containing Developer Hub.

```shell-session
$ oc create configmap rbac-policies \
     --from-file=rbac-policies.csv \
     --from-file=rbac-conditional-policies.yaml
```

Update [your Backstage custom resource](https://docs.redhat.com/en/documentation/red_hat_developer_hub/1.9/html-single/configuring_red_hat_developer_hub/index) to mount in the Developer Hub filesystem your files from the`rbac-policies` config map:

`Backstage` custom resource fragment

```yaml
apiVersion: rhdh.redhat.com/v1alpha5
kind: Backstage
spec:
  application:
    extraFiles:
      mountPath: /opt/app-root/src
      configMaps:
        - name: rbac-policies
```

Update your Developer Hub`app-config.yaml` configuration file to use the`rbac-policies.csv` and`rbac-conditional-policies.yaml` external files:

`app-config.yaml` file fragment

```yaml
permission:
  enabled: true
  rbac:
    conditionalPoliciesFile: /opt/app-root/src/rbac-conditional-policies.yaml
    policies-csv-file: /opt/app-root/src/rbac-policies.csv
    policyFileReload: true
```

### 6.2. Defining authorizations in external files by using HelmCopy link

To automate Red Hat Developer Hub maintenance, you can define permissions and roles in external files, before starting Developer Hub. You need to prepare your files, upload them to your OpenShift Container Platform project, and configure Developer Hub to use the external files.

Prerequisites

- You enabled the RBAC feature.

Procedure

Define your policies in a`rbac-policies.csv` CSV file by using the following format:

Define role permissions:

```plaintext
p, <role_entity_reference>, <permission>, <action>, <allow_or_deny>
```

 Role entity reference, such as:`role:default/guest`. 

Permission, such as:`bulk.import`,`catalog.entity.read`, or`catalog.entity.refresh`, or permission resource type, such as:`bulk-import` or`catalog-entity`.

See: Permission policies reference.

 Action type, such as:`use`,`read`,`create`,`update`,`delete`. Access granted:`allow` or`deny`.

Assign the role to a group or a user:

```plaintext
g, <group_or_user>, <role_entity_reference>
```

 

Group, such as:`user:default/mygroup`, or user, such as:`user:default/myuser`.

Sample`rbac-policies.csv`

```plaintext
p, role:default/guests, catalog-entity, read, allow
p, role:default/guests, catalog.entity.create, create, allow
g, user:default/my-user, role:default/guests
g, group:default/my-group, role:default/guests
```

Define your conditional policies in a`rbac-conditional-policies.yaml` YAML file by using the following format:

```yaml
result: CONDITIONAL
roleEntityRef: <role_entity_reference>
pluginId: <plugin_id>
permissionMapping:
  - read
  - update
  - delete
conditions: <conditions>
```

See: Conditional policies reference.

Upload your`rbac-policies.csv` and`rbac-conditional-policies.yaml` files to a`rbac-policies` config map in your OpenShift Container Platform project containing Developer Hub.

```shell-session
$ oc create configmap rbac-policies \
     --from-file=rbac-policies.csv \
     --from-file=rbac-conditional-policies.yaml
```

Update your Developer Hub`Backstage` Helm chart to mount in the Developer Hub filesystem your files from the`rbac-policies` config map:

Select Add Backstage container additional volume mounts and add the following values:

mountPath`/opt/app-root/src/rbac` Name`rbac-policies`

Add the RBAC policy to the Backstage container additional volumes in the Developer Hub Helm Chart:

name`rbac-policies` configMap

defaultMode`420` name`rbac-policies`

1. In the Developer Hub Helm Chart, go to Root Schema → Backstage chart schema → Backstage parameters → Backstage container additional volume mounts.

Update your Developer Hub`app-config.yaml` configuration file to use the`rbac-policies.csv` and`rbac-conditional-policies.yaml` external files:

`app-config.yaml` file fragment

```yaml
permission:
  enabled: true
  rbac:
    conditionalPoliciesFile: /opt/app-root/src/rbac-conditional-policies.yaml
    policies-csv-file: /opt/app-root/src/rbac-policies.csv
    policyFileReload: true
```

## Chapter 7. Configuring guest access with RBAC UICopy link

Use guest access with the role-based access control (RBAC) front-end plugin to allow a user to test role and policy creation without the need to set up and configure an authentication provider.

Note

Guest access is not recommended for production.

### 7.1. Configuring the RBAC backend pluginCopy link

You can configure the RBAC backend plugin by updating the`app-config.yaml` file to enable the permission framework.

Prerequisites

- You have installed the`@backstage-community/plugin-rbac` plugin in Developer Hub. For more information, see [Configuring dynamic plugins](https://docs.redhat.com/en/documentation/red_hat_developer_hub/1.9/html-single/configuring_dynamic_plugins/index).

Procedure

- Update the`app-config.yaml` file to enable the permission framework as shown:

```yaml
permission
  enabled: true
  rbac:
    admin:
      users:
        - name: user:default/guest
    pluginsWithPermission:
      - catalog
      - permission
      - scaffolder
```

Note

The`pluginsWithPermission` section of the`app-config.yaml` file includes only three plugins by default. Update the section as needed to include any additional plugins that also incorporate permissions.

### 7.2. Setting up the guest authentication providerCopy link

You can enable guest authentication and use it alongside the RBAC frontend plugin.

Prerequisites

- You have installed the`@backstage-community/plugin-rbac` plugin in Developer Hub. For more information, see [Configuring dynamic plugins](https://docs.redhat.com/en/documentation/red_hat_developer_hub/1.9/html-single/configuring_dynamic_plugins/index).

Procedure

- In the`app-config.yaml` file, add the user entity reference to resolve and enable the`dangerouslyAllowOutsideDevelopment` option, as shown in the following example:

```yaml
auth:
  environment: development
  providers:
    guest:
      userEntityRef: user:default/guest
      dangerouslyAllowOutsideDevelopment: true
```

Note

You can use`user:default/guest` as the user entity reference to match the added user under the`permission.rbac.admin.users` section of the`app-config.yaml` file.

## Chapter 8. Delegating role-based access controls (RBAC) access in Red Hat Developer HubCopy link

An enterprise customer requires the ability to delegate role-based access control (RBAC) responsibilities to other individuals in the organization. In this scenario, you, as the administrator, can provide access to the RBAC plugin specifically to designated users, such as team leads. Each team lead is then able to manage permissions exclusively for users within their respective team or department, without visibility into or control over permissions outside their assigned scope. This approach allows team leads to manage access and permissions for their own teams independently, while administrators maintain global oversight.

In Red Hat Developer Hub, you can delegate RBAC access using the multitenancy feature of the RBAC plugin, specifically the`IS_OWNER` conditional rule. You can either use the web UI or the RBAC backend API, depending on your preferred workflow and level of automation:

- Use the web UI to create roles, assign users or groups, define permissions, and apply ownership conditions through an intuitive interface.
- Use the API for a more flexible and automatable approach, where you can programmatically manage roles, permissions, and ownership conditions using authenticated curl requests.

By delegating RBAC access through either method, you can expect the following outcomes:

- Team leads can manage RBAC settings for their teams independently.
- Visibility of other users' or teams' permissions is restricted.
- Administrators retain overarching control while delegating team-specific access.

Prerequisites

- Your RHDH instance is running with the RBAC plugin installed and configured.
- You have administrative access to RHDH.

### 8.1. Delegating RBAC access in Red Hat Developer Hub by using the web UICopy link

You can delegate the RBAC access in Red Hat Developer Hub by using the web UI.

Procedure

Grant permissions required by team leads, such as:

- `policy.entity.create` to allow policy creation.
- `catalog-entity:read` to allow catalog access.

Apply conditions to limit access as follows:

- Use the`IS_OWNER` rule to ensure team leads can only manage resources they own.

Verification

Verify the following:

- RBAC UI is accessible.
- Only users or roles related to their team are visible.
- No access to roles or permissions outside their scope is granted.

### 8.2. Delegating RBAC access in Red Hat Developer Hub by using APICopy link

You can delegate the RBAC access in Red Hat Developer Hub by using the RBAC backend API.

Prerequisites

- You have API access using`curl` or another tool.

Procedure

Create a new role designated for team leads using the RBAC backend API:

Example of creating a new role for the team lead using the RBAC backend API

```bash
$ curl -X POST 'http://localhost:7007/api/permission/roles' \
--header "Authorization: Bearer $ADMIN_TOKEN" \
--header "Content-Type: application/json" \
--data '{
  "memberReferences": ["user:default/team_lead"],
  "name": "role:default/team_lead",
  "metadata": {
    "description": "This is an example team lead role"
  }
}'
```

Allow team leads to read catalog entities and create permissions in the RBAC plugin using the following API request:

Example of granting the team lead role permission to create RBAC policies and read catalog entities

```bash
$ curl -X POST 'http://localhost:7007/api/permission/policies' \
--header "Authorization: Bearer $ADMIN_TOKEN" \
--header "Content-Type: application/json" \
--data '[
  {
    "entityReference": "role:default/team_lead",
    "permission": "policy.entity.create",
    "policy": "create",
    "effect": "allow"
  },
  {
    "entityReference": "role:default/team_lead",
    "permission": "catalog-entity",
    "policy": "read",
    "effect": "allow"
  }
]'
```

To ensure team leads can only manage what they own, use the`IS_OWNER` conditional rule as follows:

Example`curl` of applying a conditional access policy using the`IS_OWNER` rule for the team lead role

```bash
$ curl -X POST 'http://localhost:7007/api/permission/roles/conditions' \
--header "Authorization: Bearer $ADMIN_TOKEN" \
--header "Content-Type: application/json" \
--data '{
 "result": "CONDITIONAL",
 "pluginId": "permission",
 "resourceType": "policy-entity",
 "conditions": {
   "rule": "IS_OWNER",
   "resourceType": "policy-entity",
   "params": {
     "owners": [
       "user:default/team_lead"
     ]
   }
 },
 "roleEntityRef": "role:default/team_lead",
 "permissionMapping": [
   "read",
   "update",
   "delete"
 ]
}'
```

The previous example of conditional policy limits visibility and control to only owned roles and policies.

Log in to RHDH as team lead and verify the following:

Use the following request and verify that you do not see any roles:

Example`curl` to retrieve roles visible to the team lead

```bash
$ curl -X GET 'http://localhost:7007/api/permission/roles' \
--header "Authorization: Bearer $TEAM_LEAD_TOKEN"
```

Use the following request to create a new role for their team:

Example`curl` of team lead creating a new role for their team with ownership assigned

```bash
$ curl -X POST 'http://localhost:7007/api/permission/roles' \
--header "Authorization: Bearer $TEAM_LEAD_TOKEN" \
--header "Content-Type: application/json" \
--data '{
  "memberReferences": ["user:default/team_member"],
  "name": "role:default/team_a",
  "metadata": {
    "description": "This is an example team_a role",
    "owner": "user:default/team_lead"
  }
}'
```

Note

You can set the ownership during creation, but you can also update the ownership at any time.

Use the following request to assign a permission policy to the new role:

Example`curl` for granting read access to catalog entities for the new role

```bash
$ curl -X POST 'http://localhost:7007/api/permission/policies' \
--header "Authorization: Bearer $ADMIN_TOKEN" \
--header "Content-Type: application/json" \
--data '[
  {
    "entityReference": "role:default/team_a",
    "permission": "catalog-entity",
    "policy": "read",
    "effect": "allow"
  }
]'
```

Use the following request to verify that only team-owned roles and policies are visible:

Example`curl` to retrieve roles and permission policies visible to the team lead

```bash
$ curl -X GET 'http://localhost:7007/api/permission/roles' \
--header "Authorization: Bearer $TEAM_LEAD_TOKEN"

$ curl -X GET 'http://localhost:7007/api/permission/policies' \
--header "Authorization: Bearer $TEAM_LEAD_TOKEN"
```

Verification

Log in as a team lead and verify the following:

- The RBAC UI is accessible.
- Only the assigned users or group is visible.
- Permissions outside the scoped team are not viewable or editable.

## Chapter 9. Permission policies referenceCopy link

Permission policies in Red Hat Developer Hub are a set of rules to govern access to resources or functionalities. These policies state the authorization level that is granted to users based on their roles. The permission policies are implemented to maintain security and confidentiality within a given environment.

You can define the following types of permissions in Developer Hub:

- Log in as an administrator and verify that you retain full visibility and control.
- resource type
- basic

The distinction between the two permission types depends on whether a permission includes a defined resource type.

You can define the resource type permission using either the associated resource type or the permission name as shown in the following example:

Example resource type permission definition

```plaintext
p, role:default/myrole, catalog.entity.read, read, allow
g, user:default/myuser, role:default/myrole

p, role:default/another-role, catalog-entity, read, allow
g, user:default/another-user, role:default/another-role
```

You can define the basic permission in Developer Hub using the permission name as shown in the following example:

Example basic permission definition

```plaintext
p, role:default/myrole, catalog.entity.create, create, allow
g, user:default/myuser, role:default/myrole
```

Developer Hub supports following permission policies:

Catalog permissions 

Expand

`catalog.entity.read`

`catalog-entity`

`read`

Enables a user or role to read from the catalog

`catalog.entity.create`

`create`

Enables a user or role to create catalog entities, including registering an existing component in the catalog

`catalog.entity.refresh`

`catalog-entity`

`update`

Enables a user or role to refresh a single or multiple entities from the catalog

`catalog.entity.delete`

`catalog-entity`

`delete`

Enables a user or role to delete a single or multiple entities from the catalog

`catalog.location.read`

`read`

Enables a user or role to read a single or multiple locations from the catalog

`catalog.location.create`

`create`

Enables a user or role to create locations within the catalog

`catalog.location.delete`

`delete`

Enables a user or role to delete locations from the catalog

| | Name | Resource type | Policy | Description |
| --- | --- | --- | --- | --- |

Show more

Bulk import permission 

Expand

`bulk.import`

`bulk-import`

`use`

Enables the user to access the bulk import endpoints, such as listing all repositories and organizations accessible by all GitHub integrations and managing the import requests

| | Name | Resource type | Policy | Description |
| --- | --- | --- | --- | --- |

Show more

Scaffolder permissions 

Expand

`scaffolder.action.execute`

`scaffolder-action`

`use`

Enables the execution of an action from a template

`scaffolder.template.parameter.read`

`scaffolder-template`

`read`

Enables a user or role to read a single or multiple one parameters from a template

`scaffolder.template.step.read`

`scaffolder-template`

`read`

Enables a user or role to read a single or multiple steps from a template

`scaffolder.task.create`

`create`

Enables a user or role to trigger software templates which create new scaffolder tasks

`scaffolder.task.cancel`

`use`

Enables a user or role to cancel currently running scaffolder tasks

`scaffolder.task.read`

`read`

Enables a user or role to read all scaffolder tasks and their associated events and logs

`scaffolder.template.management`

`use`

Enables a user or role to access frontend template management features, including editing, previewing, and trying templates, forms, and custom fields.

| | Name | Resource type | Policy | Description |
| --- | --- | --- | --- | --- |

Show more

RBAC permissions 

Expand

`policy.entity.read`

`policy-entity`

`read`

Enables a user or role to read permission policies and roles

`policy.entity.create`

`create`

Enables a user or role to create a single or multiple permission policies and roles

`policy.entity.update`

`policy-entity`

`update`

Enables a user or role to update a single or multiple permission policies and roles

`policy.entity.delete`

`policy-entity`

`delete`

Enables a user or role to delete a single or multiple permission policies and roles

| | Name | Resource type | Policy | Description |
| --- | --- | --- | --- | --- |

Show more

Kubernetes permissions 

Expand

`kubernetes.clusters.read`

`read`

Enables a user to read Kubernetes cluster details under the`/clusters` path

`kubernetes.resources.read`

`read`

Enables a user to read information about Kubernetes resources located at`/services/:serviceId` and`/resources`

`kubernetes.proxy`

`use`

Enables a user or role to access the proxy endpoint

| | Name | Resource type | Policy | Description |
| --- | --- | --- | --- | --- |

Show more

OCM permissions Basic OCM permissions only restrict access to the cluster view, but they do not prevent access to the Kubernetes clusters in the resource view. For more effective permissions, consider applying a conditional policy to restrict access to catalog entities that are of type`kubernetes-cluster`. Access restriction is dependent on the set of permissions granted to a role. For example, if the role had full permissions (`read`,`update`, and`delete`), then you must specify all its permissions in the`permissionMapping` field.

Example permissionMapping definition

```plaintext
result: CONDITIONAL
roleEntityRef: 'role:default/<YOUR_ROLE>'
pluginId: catalog
resourceType: catalog-entity
permissionMapping:
  - read
  - update
  - delete
conditions:
  not:
    rule: HAS_SPEC
    resourceType: catalog-entity
    params:
      key: type
      value: kubernetes-cluster
```

OCM permissions 

Expand

`ocm.entity.read`

`read`

Enables a user or role to read from the OCM plugin

`ocm.cluster.read`

`read`

Enables a user or role to read the cluster information in the OCM plugin

| | Name | Resource type | Policy | Description |
| --- | --- | --- | --- | --- |

Show more

Topology permissions 

Expand

`kubernetes.clusters.read`

`read`

Enables a user to read Kubernetes cluster details under the`/clusters` path

`kubernetes.resources.read`

`read`

Enables a user to read information about Kubernetes resources located at`/services/:serviceId` and`/resources`

`kubernetes.proxy`

`use`

Enables a user or role to access the proxy endpoint, allowing the user or role to read pod logs and events within RHDH

| | Name | Resource type | Policy | Description |
| --- | --- | --- | --- | --- |

Show more

Tekton permissions 

Expand

`kubernetes.clusters.read`

`read`

Enables a user to read Kubernetes cluster details under the`/clusters` path

`kubernetes.resources.read`

`read`

Enables a user to read information about Kubernetes resources located at`/services/:serviceId` and`/resources`

`kubernetes.proxy`

`use`

Enables a user or role to access the proxy endpoint, allowing the user or role to read pod logs and events within RHDH

| | Name | Resource type | Policy | Description |
| --- | --- | --- | --- | --- |

Show more

ArgoCD permissions 

Expand

`argocd.view.read`

`read`

Enables a user to read from the ArgoCD plugin

| | Name | Resource type | Policy | Description |
| --- | --- | --- | --- | --- |

Show more

Quay permissions 

Expand

`quay.view.read`

`read`

Enables a user to read from the Quay plugin

| | Name | Resource type | Policy | Description |
| --- | --- | --- | --- | --- |

Show more

## Chapter 10. Conditional policies in Red Hat Developer HubCopy link

The permission framework in Red Hat Developer Hub provides conditions, supported by the RBAC backend plugin (`backstage-plugin-rbac-backend`). The conditions work as content filters for the Developer Hub resources that are provided by the RBAC backend plugin.

The RBAC backend API stores conditions assigned to roles in the database. When you request to access the frontend resources, the RBAC backend API searches for the corresponding conditions and delegates them to the appropriate plugin using its plugin ID. If you are assigned to multiple roles with different conditions, then the RBAC backend merges the conditions using the`anyOf` criteria.

Conditional criteria

A condition in Developer Hub is a simple condition with a rule and parameters. However, a condition can also contain a parameter or an array of parameters combined by conditional criteria. The supported conditional criteria includes:

- `allOf`: Ensures that all conditions within the array must be true for the combined condition to be satisfied.
- `anyOf`: Ensures that at least one of the conditions within the array must be true for the combined condition to be satisfied.
- `not`: Ensures that the condition within it must not be true for the combined condition to be satisfied.

Conditional object

The plugin specifies the parameters supported for conditions. You can access the conditional object schema from the RBAC API endpoint to understand how to construct a conditional JSON object, which is then used by the RBAC backend plugin API.

A conditional object contains the following parameters:

Expand

`result`

String

Always has the value`CONDITIONAL`

`roleEntityRef`

String

String entity reference to the RBAC role, such as`role:default/dev`

`pluginId`

String

Corresponding plugin ID, such as`catalog`

`permissionMapping`

String array

Array permission actions, such as`['read', 'update', 'delete']`

`resourceType`

String

Resource type provided by the plugin, such as`catalog-entity`

`conditions`

JSON

Condition JSON with parameters or array parameters joined by criteria

| | Parameter | Type | Description |
| --- | --- | --- | --- |

Show more

Conditional policy aliases

The RBAC backend plugin (`backstage-plugin-rbac-backend`) supports the use of aliases in conditional policy rule parameters. The conditional policy aliases are dynamically replaced with the corresponding values during policy evaluation. Each alias in conditional policy is prefixed with a`$` sign indicating its special function.

The supported conditional aliases include:

`$currentUser`: This alias is replaced with the user entity reference for the user who requests access to the resource. For example, if user Tom from the default namespace requests access,`$currentUser` becomes`user:default/tom`.

Example conditional policy object with`$currentUser` alias:

```plaintext
{
  "result": "CONDITIONAL",
  "roleEntityRef": "role:default/developer",
  "pluginId": "catalog",
  "resourceType": "catalog-entity",
  "permissionMapping": ["delete"],
  "conditions": {
    "rule": "IS_ENTITY_OWNER",
    "resourceType": "catalog-entity",
    "params": {
      "claims": ["$currentUser"]
    }
  }
}
```

`$ownerRefs`: This alias is replaced with ownership references, usually as an array that includes the user entity reference and the user’s parent group entity reference. For example, for user Tom from team-a,`$ownerRefs` becomes`['user:default/tom', 'group:default/team-a']`.

Example conditional policy object with`$ownerRefs` alias:

```plaintext
{
  "result": "CONDITIONAL",
  "roleEntityRef": "role:default/developer",
  "pluginId": "catalog",
  "resourceType": "catalog-entity",
  "permissionMapping": ["delete"],
  "conditions": {
    "rule": "IS_ENTITY_OWNER",
    "resourceType": "catalog-entity",
    "params": {
      "claims": ["$ownerRefs"]
    }
  }
}
```

### 10.1. Enabling transitive parent groupsCopy link

By default, Red Hat Developer Hub does not resolve indirect parent groups during authentication. In this case, with the following group hierarchy, the`user_alice` user is only a member of the`group_developers` group:

```plaintext
group_admin
  └── group_developers
    └── user_alice
```

To support multi-level group hierarchies when using the $ownerRefs alias, you can configure Developer Hub to include indirect parent groups in the user’s ownership entities. In that case the`user_alice` user is a member of both`group_developers` and`group_admin` groups.

Procedure

Enable the`includeTransitiveGroupOwnership` option in your`app-config.yaml` file.

```yaml
includeTransitiveGroupOwnership: true
```

### 10.2. Conditional policies referenceCopy link

You can access API endpoints for conditional policies in Red Hat Developer Hub. For example, to retrieve the available conditional rules, which can help you define these policies, you can access the`GET [api/plugins/condition-rules]` endpoint.

The`api/plugins/condition-rules` returns the condition parameters schemas, for example:

```plaintext
[
   {
      "pluginId": "catalog",
      "rules": [
         {
            "name": "HAS_ANNOTATION",
            "description": "Allow entities with the specified annotation",
            "resourceType": "catalog-entity",
            "paramsSchema": {
               "type": "object",
               "properties": {
                  "annotation": {
                     "type": "string",
                     "description": "Name of the annotation to match on"
                  },
                  "value": {
                     "type": "string",
                     "description": "Value of the annotation to match on"
                  }
               },
               "required": [
                  "annotation"
               ],
               "additionalProperties": false,
               "$schema": "http://json-schema.org/draft-07/schema#"
            }
         },
         {
            "name": "HAS_LABEL",
            "description": "Allow entities with the specified label",
            "resourceType": "catalog-entity",
            "paramsSchema": {
               "type": "object",
               "properties": {
                  "label": {
                     "type": "string",
                     "description": "Name of the label to match on"
                  }
               },
               "required": [
                  "label"
               ],
               "additionalProperties": false,
               "$schema": "http://json-schema.org/draft-07/schema#"
            }
         },
         {
            "name": "HAS_METADATA",
            "description": "Allow entities with the specified metadata subfield",
            "resourceType": "catalog-entity",
            "paramsSchema": {
               "type": "object",
               "properties": {
                  "key": {
                     "type": "string",
                     "description": "Property within the entities metadata to match on"
                  },
                  "value": {
                     "type": "string",
                     "description": "Value of the given property to match on"
                  }
               },
               "required": [
                  "key"
               ],
               "additionalProperties": false,
               "$schema": "http://json-schema.org/draft-07/schema#"
            }
         },
         {
            "name": "HAS_SPEC",
            "description": "Allow entities with the specified spec subfield",
            "resourceType": "catalog-entity",
            "paramsSchema": {
               "type": "object",
               "properties": {
                  "key": {
                     "type": "string",
                     "description": "Property within the entities spec to match on"
                  },
                  "value": {
                     "type": "string",
                     "description": "Value of the given property to match on"
                  }
               },
               "required": [
                  "key"
               ],
               "additionalProperties": false,
               "$schema": "http://json-schema.org/draft-07/schema#"
            }
         },
         {
            "name": "IS_ENTITY_KIND",
            "description": "Allow entities matching a specified kind",
            "resourceType": "catalog-entity",
            "paramsSchema": {
               "type": "object",
               "properties": {
                  "kinds": {
                     "type": "array",
                     "items": {
                        "type": "string"
                     },
                     "description": "List of kinds to match at least one of"
                  }
               },
               "required": [
                  "kinds"
               ],
               "additionalProperties": false,
               "$schema": "http://json-schema.org/draft-07/schema#"
            }
         },
         {
            "name": "IS_ENTITY_OWNER",
            "description": "Allow entities owned by a specified claim",
            "resourceType": "catalog-entity",
            "paramsSchema": {
               "type": "object",
               "properties": {
                  "claims": {
                     "type": "array",
                     "items": {
                        "type": "string"
                     },
                     "description": "List of claims to match at least one on within ownedBy"
                  }
               },
               "required": [
                  "claims"
               ],
               "additionalProperties": false,
               "$schema": "http://json-schema.org/draft-07/schema#"
            }
         }
      ]
   }
   ... <another plugin condition parameter schemas>
]
```

The RBAC backend API constructs a condition JSON object based on the previous condition schema.

#### 10.2.1. Examples of conditional policiesCopy link

In Red Hat Developer Hub, you can define conditional policies with or without criteria. You can use the following examples to define the conditions based on your use case:

A condition without criteria

Consider a condition without criteria displaying catalogs only if user is a member of the owner group. To add this condition, you can use the catalog plugin schema`IS_ENTITY_OWNER` as follows:

Example condition without criteria

```plaintext
{
  "rule": "IS_ENTITY_OWNER",
  "resourceType": "catalog-entity",
  "params": {
    "claims": ["group:default/team-a"]
  }
}
```

In the previous example, the only conditional parameter used is`claims`, which contains a list of user or group entity references.

You can apply the previous example condition to the RBAC REST API by adding additional parameters as follows:

```plaintext
{
  "result": "CONDITIONAL",
  "roleEntityRef": "role:default/test",
  "pluginId": "catalog",
  "resourceType": "catalog-entity",
  "permissionMapping": ["read"],
  "conditions": {
    "rule": "IS_ENTITY_OWNER",
    "resourceType": "catalog-entity",
    "params": {
      "claims": ["group:default/team-a"]
    }
  }
}
```

A condition with criteria

Consider a condition with criteria, which displays catalogs only if user is a member of owner group OR displays list of all catalog user groups.

To add the criteria, you can add another rule as`IS_ENTITY_KIND` in the condition as follows:

Example condition with criteria

```plaintext
{
  "anyOf": [
    {
      "rule": "IS_ENTITY_OWNER",
      "resourceType": "catalog-entity",
      "params": {
        "claims": ["group:default/team-a"]
      }
    },
    {
      "rule": "IS_ENTITY_KIND",
      "resourceType": "catalog-entity",
      "params": {
        "kinds": ["Group"]
      }
    }
  ]
}
```

Note

Running conditions in parallel during creation is not supported. Therefore, consider defining nested conditional policies based on the available criteria.

Example of nested conditions

```plaintext
{
  "anyOf": [
    {
      "rule": "IS_ENTITY_OWNER",
      "resourceType": "catalog-entity",
      "params": {
        "claims": ["group:default/team-a"]
      }
    },
    {
      "rule": "IS_ENTITY_KIND",
      "resourceType": "catalog-entity",
      "params": {
        "kinds": ["Group"]
      }
    }
  ],
  "not": {
    "rule": "IS_ENTITY_KIND",
    "resourceType": "catalog-entity",
    "params": { "kinds": ["Api"] }
  }
}
```

You can apply the previous example condition to the RBAC REST API by adding additional parameters as follows:

```plaintext
{
  "result": "CONDITIONAL",
  "roleEntityRef": "role:default/test",
  "pluginId": "catalog",
  "resourceType": "catalog-entity",
  "permissionMapping": ["read"],
  "conditions": {
    "anyOf": [
      {
        "rule": "IS_ENTITY_OWNER",
        "resourceType": "catalog-entity",
        "params": {
          "claims": ["group:default/team-a"]
        }
      },
      {
        "rule": "IS_ENTITY_KIND",
        "resourceType": "catalog-entity",
        "params": {
          "kinds": ["Group"]
        }
      }
    ]
  }
}
```

The following examples can be used with Developer Hub plugins. These examples can help you determine how to define conditional policies:

Conditional policy defined for Keycloak plugin

```plaintext
{
  "result": "CONDITIONAL",
  "roleEntityRef": "role:default/developer",
  "pluginId": "catalog",
  "resourceType": "catalog-entity",
  "permissionMapping": ["update", "delete"],
  "conditions": {
    "not": {
      "rule": "HAS_ANNOTATION",
      "resourceType": "catalog-entity",
      "params": { "annotation": "keycloak.org/realm", "value": "<YOUR_REALM>" }
    }
  }
}
```

The previous example of Keycloak plugin prevents users in the`role:default/developer` from updating or deleting users that are ingested into the catalog from the Keycloak plugin.

Note

In the previous example, the annotation`keycloak.org/realm` requires the value of` `.

Conditional policy defined for Quay plugin

```plaintext
{
  "result": "CONDITIONAL",
  "roleEntityRef": "role:default/developer",
  "pluginId": "scaffolder",
  "resourceType": "scaffolder-action",
  "permissionMapping": ["use"],
  "conditions": {
    "not": {
      "rule": "HAS_ACTION_ID",
      "resourceType": "scaffolder-action",
      "params": { "actionId": "quay:create-repository" }
    }
  }
}
```

The previous example of Quay plugin prevents the role`role:default/developer` from using the Quay scaffolder action. Note that`permissionMapping` contains`use`, signifying that`scaffolder-action` resource type permission does not have a permission policy.

Additional resources

- Chapter 9, Permission policies reference

## Chapter 11. User statistics in Red Hat Developer HubCopy link

In Red Hat Developer Hub, the`licensed-users-info-backend` plugin provides statistical information about the logged-in users using the Web UI or REST API endpoints.

The`licensed-users-info-backend` plugin enables administrators to monitor the number of active users on Developer Hub. Using this feature, organizations can compare their actual usage with the number of licenses they have purchased. Additionally, you can share the user metrics with Red Hat for transparency and accurate licensing.

The`licensed-users-info-backend` plugin is enabled by default. This plugin enables a Download User List link at the bottom of the Administration → RBAC tab.

### 11.1. Downloading active users list in Red Hat Developer HubCopy link

You can download the list of users in CSV format using the Developer Hub web interface.

Prerequisites

- RBAC plugins (`@backstage-community/plugin-rbac` and`@backstage-community/plugin-rbac-backend`) must be enabled in Red Hat Developer Hub.
- An administrator role must be assigned.

Procedure

1. In Red Hat Developer Hub, navigate to Administration and select the RBAC tab.
2. At the bottom of the RBAC page, click Download User List.
3. Optional: Modify the file name in the Save as field and click Save.
4. To access the downloaded users list, go to the Downloads folder on your local machine and open the CSV file.

## Legal NoticeCopy link

Copyright © Red Hat.

Except as otherwise noted below, the text of and illustrations in this documentation are licensed by Red Hat under the Creative Commons Attribution–Share Alike 3.0 Unported license . If you distribute this document or an adaptation of it, you must provide the URL for the original version.

Red Hat, as the licensor of this document, waives the right to enforce, and agrees not to assert, Section 4d of CC-BY-SA to the fullest extent permitted by applicable law.

Red Hat, the Red Hat logo, JBoss, Hibernate, and RHCE are trademarks or registered trademarks of Red Hat, LLC. or its subsidiaries in the United States and other countries.

Linux® is the registered trademark of Linus Torvalds in the United States and other countries.

XFS is a trademark or registered trademark of Hewlett Packard Enterprise Development LP or its subsidiaries in the United States and other countries.

The OpenStack® Word Mark and OpenStack logo are trademarks or registered trademarks of the Linux Foundation, used under license.

All other trademarks are the property of their respective owners.

---

*Source: https://docs.redhat.com/en/documentation/red_hat_developer_hub/1.9/html-single/integrating_red_hat_developer_hub_with_github/index*

Integrating Red Hat Developer Hub with GitHub | Red Hat Developer Hub | 1.9 | Red Hat Documentation

 

 

 

 

 

 

 

 

# Integrating Red Hat Developer Hub with GitHub

---

Red Hat Developer Hub 1.9

## Configuring integration to the GitHub Git provider in Red Hat Developer Hub

Red Hat Customer Content Services

Legal Notice

Abstract

As a Red Hat Developer Hub (RHDH) platform engineer, you can integrate RHDH with the GitHub Git provider.

---

## Chapter 1. Enabling GitHub repository discoveryCopy link

Consider configuring Developer Hub to discover and ingest your GitHub repositories automatically. If a repository contains a`catalog-info.yaml` file, Developer Hub ingests the repository into the catalog as a component.

Prerequisites

- Legal Notice
- You [added a custom Developer Hub application configuration](https://docs.redhat.com/en/documentation/red_hat_developer_hub/1.9/html-single/configuring_red_hat_developer_hub/index), and have sufficient permissions to modify it.
- You have sufficient permissions in GitHub to create and manage a [GitHub App](https://docs.github.com/en/apps/overview).
- To allow users to access GitHub templates or plugins that require GitHub authentication, you have configured GitHub either [as an auxiliary authentication provider](https://docs.redhat.com/en/documentation/red_hat_developer_hub/1.9/html-single/authentication_in_red_hat_developer_hub/index#enabling-user-authentication-with-github-as-an-auxiliary-authentication-provider) or [as your main authentication provider](https://docs.redhat.com/en/documentation/red_hat_developer_hub/1.9/html-single/authentication_in_red_hat_developer_hub/index#enabling-user-authentication-with-github).

Procedure

To allow Developer Hub to access the GitHub API, create a GitHub App. Opt for a GitHub App instead of an OAuth app to use fine-grained permissions, gain more control over which repositories the application can access, and use short-lived tokens.

[Register a GitHub App](https://docs.github.com/en/apps/creating-github-apps/registering-a-github-app/registering-a-github-app) with the following configuration:

GitHub App name Enter a unique name identifying your GitHub App, such as`integrating-with-rhdh- `. Homepage URL Enter your Developer Hub URL:`https:// `. Authorization callback URL Enter your Developer Hub authentication backend URL:`https:// /api/auth/github/handler/frame`. Webhook Clear "Active", as this is not needed for authentication and catalog providers. App permissions

Select permissions to define the level of access for the app. Adapt permissions to your needs:

Reading software components

Contents`Read-only` Commit statuses`Read-only`

Reading organization data

Members`Read-only`

Publishing software templates

Set permissions if you intend to use the same GitHub App for software templates.

Administration`Read & write`(for creating repositories) Contents`Read & write` Metadata`Read-only` Pull requests`Read & write` Issues`Read & write` Workflows`Read & write`(if templates include GitHub workflows) Variables`Read & write`(if templates include GitHub Action Repository Variables) Secrets`Read & write`(if templates include GitHub Action Repository Secrets) Environments`Read & write`(if templates include GitHub Environments)

Organization permissions

Members`Read-only`

Where can this GitHub App be installed? Select`Only on this account`.

Save the following values for the next step:

- App ID
- Client ID
- Client secret
- Private key

To add your GitHub credentials to Developer Hub, add the following key/value pairs to [your Developer Hub secrets](https://docs.redhat.com/en/documentation/red_hat_developer_hub/1.9/html-single/configuring_dynamic_plugins/index#provisioning-your-custom-configuration). You can use these secrets in the Developer Hub configuration files by using their respective environment variable name.

`GITHUB_INTEGRATION_APP_ID` Enter the saved App ID.`GITHUB_INTEGRATION_CLIENT_ID` Enter the saved Client ID.`GITHUB_INTEGRATION_CLIENT_SECRET` Enter the saved Client Secret.`GITHUB_INTEGRATION_HOST_DOMAIN` Enter the GitHub host domain:`github.com`.`GITHUB_INTEGRATION_ORGANIZATION` Enter your GitHub organization name, such as ` '.`GITHUB_INTEGRATION_PRIVATE_KEY_FILE` Enter the saved Private key.

Enable the`plugin-catalog-backend-module-github` plugin in your`dynamic-plugins.yaml` file.

This plugin discovers catalog entities by scanning repositories within a GitHub organization for`catalog-info.yaml` files. It provides an automated alternative to manually registering components via`catalog.locations`. When a repository contains a`catalog-info.yaml` file, the entity is ingested into the catalog as a component.

`dynamic-plugins.yaml` file fragment

```yaml
plugins:
  - package: './dynamic-plugins/dist/backstage-plugin-catalog-backend-module-github'
    disabled: false
```

Configure the GitHub integration, by adding the`catalog.providers.github` and the`integrations.github` sections to your custom Developer Hub`app-config.yaml` configuration file:

`app-config.yaml` file fragment with mandatory fields to enable GitHub integration

```yaml
catalog:
  providers:
    github:
      providerId:
        organization: "${GITHUB_INTEGRATION_ORGANIZATION}"
        schedule:
          frequency:
            minutes: 30
          initialDelay:
            seconds: 15
          timeout:
            minutes: 15
integrations:
  github:
    - host: ${GITHUB_INTEGRATION_HOST_DOMAIN}
      apps:
        - appId: ${GITHUB_INTEGRATION_APP_ID}
          clientId: ${GITHUB_INTEGRATION_CLIENT_ID}
          clientSecret: ${GITHUB_INTEGRATION_CLIENT_SECRET}
          privateKey: |
            ${GITHUB_INTEGRATION_PRIVATE_KEY_FILE}
```

## Chapter 2. Bulk importing in Red Hat Developer HubCopy link

Important

These features are for Technology Preview only. Technology Preview features are not supported with Red Hat production service level agreements (SLAs), might not be functionally complete, and Red Hat does not recommend using them for production. These features provide early access to upcoming product features, enabling customers to test functionality and provide feedback during the development process.

For more information on Red Hat Technology Preview features, see [Technology Preview Features Scope](https://access.redhat.com/support/offerings/techpreview/).

Red Hat Developer Hub can automate the onboarding of GitHub repositories and GitLab projects, and track their import status.

### 2.1. Enabling and authorizing Bulk Import capabilities in Red Hat Developer HubCopy link

You can enable the Bulk Import feature for users and give them the necessary permissions to access it. This feature is available for GitHub repositories and GitLab projects.

Prerequisites

- For GitHub only: You have [enabled GitHub repository discovery](https://docs.redhat.com/en/documentation/red_hat_developer_hub/1.9/html-single/integrating_red_hat_developer_hub_with_github/index#enabling-github-repository-discovery).

Procedure

The Bulk Import plugins are installed but disabled by default. To enable the`./dynamic-plugins/dist/red-hat-developer-hub-backstage-plugin-bulk-import-backend-dynamic` and`./dynamic-plugins/dist/red-hat-developer-hub-backstage-plugin-bulk-import` plugins, edit your`dynamic-plugins.yaml` with the following content:

```yaml
plugins:
  - package: ./dynamic-plugins/dist/red-hat-developer-hub-backstage-plugin-bulk-import-backend-dynamic
    disabled: false
  - package: ./dynamic-plugins/dist/red-hat-developer-hub-backstage-plugin-bulk-import
    disabled: false
```

See [Installing and viewing plugins in Red Hat Developer Hub](https://docs.redhat.com/en/documentation/red_hat_developer_hub/1.9/html-single/installing_and_viewing_plugins_in_red_hat_developer_hub/index).

Configure the required`bulk.import` RBAC permission for the users who are not administrators as shown in the following code:

`rbac-policy.csv` fragment

```plaintext
p, role:default/bulk-import, bulk.import, use, allow
g, user:default/<your_user>, role:default/bulk-import
```

Note that only Developer Hub administrators or users with the`bulk.import` permission can use the Bulk Import feature. See [Permission policies in Red Hat Developer Hub](https://docs.redhat.com/en/documentation/red_hat_developer_hub/1.9/html-single/authorization_in_red_hat_developer_hub/index#ref-rbac-permission-policies_title-authorization).

Verification

- The sidebar displays a Bulk Import option.
- The Bulk Import page shows a list of added GitHub repositories and GitLab projects.

### 2.2. Importing multiple GitHub repositoriesCopy link

In Red Hat Developer Hub, you can select your GitHub repositories and automate their onboarding to the Developer Hub catalog.

Prerequisites

- You have enabled the Bulk Import feature and gave access to it.

Procedure

Select the repositories to import, and validate.

Developer Hub creates a pull request in each selected repository to add the required`catalog-info.yaml` file.

1. Click Bulk Import in Developer Hub left sidebar.
2. If your RHDH instance has multiple source control tools configured, select GitHub from the Source control tool list.
3. For each repository to import, click on the PR link to review and merge the changes in GitHub.

Verification

1. Click Bulk Import in Developer Hub left sidebar.
2. Verify that each imported GitHub repository in the Selected repositories list has the status Waiting for approval or Imported.
3. For each Waiting for approval repository, click the pull request link to review and merge the`catalog-info.yaml` file in the corresponding repository.

### 2.3. Importing multiple GitLab repositoriesCopy link

In Red Hat Developer Hub, you can select your GitLab projects and automate their onboarding to the Developer Hub catalog. This feature is a Technology Preview.

Important

Technology Preview features provide early access to upcoming product innovations, enabling you to test functionality and provide feedback during the development process. However, these features are not fully supported under Red Hat Subscription Level Agreements, may not be functionally complete, and are not intended for production use. As Red Hat considers making future iterations of Technology Preview features generally available, we will attempt to resolve any issues that customers experience when using these features. See: [Technology Preview support scope](https://access.redhat.com/support/offerings/techpreview/).

Prerequisites

You configured the GitLab integration by adding the following section to your RHDH`app-config.yaml` file:

```yaml
integrations:
  gitlab:
    - host: ${GITLAB_HOST}
      token: ${GITLAB_TOKEN}
```

You enabled the GitLab catalog provider plugin in your`dynamic-plugins.yaml` file to import GitLab users and groups:

```yaml
plugins:
  - package: './dynamic-plugins/dist/backstage-plugin-catalog-backend-module-gitlab-org-dynamic'
    disabled: false
```

- You have [enabled the Bulk Import feature and given access to it](https://docs.redhat.com/en/documentation/red_hat_developer_hub/1.9/html-single/configuring_dynamic_plugins/index#bulk-importing-github-repositories).
- You have set up a [GitLab personal access token (PAT)](https://docs.gitlab.com/user/profile/personal_access_tokens/).

Procedure

Select the projects to import, and validate.

Developer Hub creates a merge request in each selected project to add the required`catalog-info.yaml` file.

1. In the Developer Hub left sidebar, click Bulk Import.
2. If your RHDH instance has multiple source control tools configured, select GitLab as your Source control tool option.
3. For each project to import, click the PR link to review and merge the changes in Gitlab.

Verification

1. Click Bulk Import in Developer Hub left sidebar.
2. Verify that each imported GitLab project in the Selected projects list has the status Waiting for approval or Imported.
3. For projects with the Waiting for approval status, click the merge request link to add the`catalog-info.yaml` file to the project repository.

### 2.4. Monitoring Bulk Import actions using audit logsCopy link

The Bulk Import backend plugin adds the following events to the Developer Hub audit logs. See [Audit logs in Red Hat Developer Hub](https://docs.redhat.com/en/documentation/red_hat_developer_hub/1.9/html-single/audit_logs_in_red_hat_developer_hub/index) for more information on how to configure and view audit logs.

Bulk Import Events:

`BulkImportUnknownEndpoint` Tracks requests to unknown endpoints.`BulkImportPing` Tracks`GET` requests to the`/ping` endpoint, which allows us to make sure the bulk import backend is up and running.`BulkImportFindAllOrganizations` Tracks`GET` requests to the`/organizations` endpoint, which returns the list of organizations accessible from all configured GitHub Integrations.`BulkImportFindRepositoriesByOrganization` Tracks`GET` requests to the`/organizations/:orgName/repositories` endpoint, which returns the list of repositories for the specified organization (accessible from any of the configured GitHub Integrations).`BulkImportFindAllRepositories` Tracks GET requests to the`/repositories` endpoint, which returns the list of repositories accessible from all configured GitHub Integrations.`BulkImportFindAllImports` Tracks`GET` requests to the`/imports` endpoint, which returns the list of existing import jobs along with their statuses.`BulkImportCreateImportJobs` Tracks`POST` requests to the`/imports` endpoint, which allows to submit requests to bulk-import one or many repositories into the Developer Hub catalog, by eventually creating import pull requests in the target repositories.`BulkImportFindImportStatusByRepo` Tracks`GET` requests to the`/import/by-repo` endpoint, which fetches details about the import job for the specified repository.`BulkImportDeleteImportByRepo` Tracks`DELETE` requests to the`/import/by-repo` endpoint, which deletes any existing import job for the specified repository, by closing any open import pull request that could have been created.

Example bulk import audit logs

```plaintext
{
  "actor": {
    "actorId": "user:default/myuser",
    "hostname": "localhost",
    "ip": "::1",
    "userAgent": "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/128.0.0.0 Safari/537.36"
  },
  "eventName": "BulkImportFindAllOrganizations",
  "isAuditLog": true,
  "level": "info",
  "message": "'get /organizations' endpoint hit by user:default/myuser",
  "meta": {},
  "plugin": "bulk-import",
  "request": {
    "body": {},
    "method": "GET",
    "params": {},
    "query": {
      "pagePerIntegration": "1",
      "sizePerIntegration": "5"
    },
    "url": "/api/bulk-import/organizations?pagePerIntegration=1&sizePerIntegration=5"
  },
  "response": {
    "status": 200
  },
  "service": "backstage",
  "stage": "completion",
  "status": "succeeded",
  "timestamp": "2024-08-26 16:41:02"
}
```

### 2.5. Input parameters for Bulk Import Scaffolder templateCopy link

As an administrator, you can use the Bulk Import plugin to run a Scaffolder template task with specified parameters, which you must define within the template.

The Bulk Import plugin analyzes Git repository information and provides the following parameters for the Scaffolder template task:

`repoUrl`

Normalized repository URL in the following format:

```yaml
  ${gitProviderHost}?owner=${owner}&repo=${repository-name}
```

`name` The repository name.`organization` The repository owner, which can be a user nickname or organization name.`branchName` The proposed repository branch. By default, the proposed repository branch is`bulk-import-catalog-entity`.`targetBranchName` The default branch of the Git repository.`gitProviderHost` The Git provider host parsed from the repository URL. You can use this parameter to write`Git-provider-agnostic` templates.

Example of a Scaffolder template:

```yaml
parameters:
  - title: Repository details
    required:
      - repoUrl
      - branchName
      - targetBranchName
      - name
      - organization
    properties:
      repoUrl:
        type: string
        title: Repository URL (Backstage format)
        description: github.com?owner=Org&repo=repoName
      organization:
        type: string
        title: Owner of the repository
      name:
        type: string
        title: Name of the repository
      branchName:
        type: string
        title: Branch to add the catalog entity to
      targetBranchName:
        type: string
        title: Branch to target the PR/MR to
      gitProviderHost:
        type: string
        title: Git provider host
```

### 2.6. Setting up a custom Scaffolder workflow for Bulk ImportCopy link

As an administrator, you can create a custom Scaffolder template in line with the repository conventions of your organization and add the template into the Red Hat Developer Hub catalog for use by the Bulk Import plugin on multiple selected repositories.

You can define various custom tasks, including, but not limited to the following:

- Importing existing catalog entities from a repository
- Creating pull requests for cleanup
- Calling webhooks for external system integration

Prerequisites

You have run your RHDH instance with the following environment variable enabled to allow the use of the Scaffolder functionality:

```yaml
export NODE_OPTIONS=--no-node-snapshot
```

- You created a custom Scaffolder template for the Bulk Import plugin.

Procedure

Configure your app-config.yaml configuration to instruct the Bulk Import plugin to use your custom template as shown in the following example:

```yaml
bulkImport:
  importTemplate: <your_template_entity_reference_or_template_name>
  importAPI: `open-pull-requests` | `scaffolder`;
```

where:

`importTemplate:` Enter your Scaffolder template entity reference.`importAPI` Set the API to 'scaffolder' to trigger the defined workflow for high-fidelity automation. This field defines the import workflow and currently supports two following options:`open-pull-requests` This is the default import workflow, which includes the logic for creating pull requests for every selected repository.`scaffolder`

This workflow uses an import scenario defined in the Scaffolder template to create import jobs. Select this option to use the custom import scenario defined in your Scaffolder template.

Optional: You can direct the Bulk Import plugin to hand off the entire list of selected repositories to a custom Orchestrator workflow.

Important

The Scaffolder template must be generic and not specific to a single repository if you want your custom Scaffolder template to run successfully for every repository in the bulk list.

Verification

- The Bulk Import plugin runs the custom Scaffolder template for the list of repositories using the`/task-imports` API endpoint.

### 2.7. Running Orchestrator workflows for bulk importsCopy link

As a platform engineer, you can configure the Bulk Import plugin to run Orchestrator workflows for bulk import operations. This mode uses the Orchestrator engine to provide advanced capabilities, such as creating pull requests or publishing configurations across multiple repositories.

Prerequisites

- You have installed and configured the Orchestrator plugin in your Backstage instance.
- You have registered a generic custom workflow (for example,`universal-pr`) in the Orchestrator plugin.
- You have Role-Based Access Control (RBAC) permissions to configure the Bulk Import plugin.

Procedure

Configure the Bulk Import plugin by editing your`app-config.yaml` file to enable Orchestrator mode.

```yaml
bulkImport:
  orchestratorWorkflow: your_workflow_id
  importAPI: 'orchestrator'
```

where:

`orchestratorWorkflow` The ID of the workflow to run for each repository.`importAPI` The execution mode for the workflow. Enter`orchestrator` to enable workflow execution.

Verify that the Orchestrator workflow receives the following input:

```yaml
{
  "inputData": {
    "owner": "redhat-developer",
    "repo": "rhdh-plugins",
    "baseBranch": "main",
    "targetBranch": "bulk-import-orchestrator"
  },
  "authTokens": [
    {
      "token": "<github_token>",
      "provider": "github"
    }
  ]
}
```

where:

`owner` Specifies the repository owner (organization or user name).`repo` Specifies the repository name.`baseBranch` Specifies the default branch of the Git repository (for example, main).`targetBranch` Specifies the target branch for the import operation. By default, this is set to`bulk-import-orchestrator`.`authTokens`

Specifies the authentication tokens for the Git provider:

- For GitHub: {`token:, provider: github`}
- For GitLab: {`token:, provider: gitlab`}

Navigate to the Bulk Import page in the sidebar and complete the following steps:

1. Select your Git provider (for example, GitHub or GitLab).
2. Select the projects you want to import.

Verification

- Click import to run the workflow.
- Locate your repository and confirm status is COMPLETED.

### 2.8. Data handoff and custom workflow designCopy link

When you configure the Bulk Import plugin by setting the importAPI field to`scaffolder`, the Bulk Import Backend passes all necessary context directly to the Scaffolder API.

As an administrator, you can define the Scaffolder template workflow and structure the workflow to do the following:

Define template parameters to consume input Structure the Scaffolder template to receive the repository data as template parameters for the current workflow run. The template must be generic, and not specific to a single repository, so that it can successfully run for every repository in the bulk list. Automate processing for each repository Implement the custom logic needed for a single repository within the template. The Orchestrator iterates through the repository list, launching the template once for each repository and passes only the data for that single repository to the template run. This allows you to automate tasks such as creating the`catalog-info.yaml`, running compliance checks, or registering the entity with the catalog.

## Legal NoticeCopy link

Copyright © Red Hat.

Except as otherwise noted below, the text of and illustrations in this documentation are licensed by Red Hat under the Creative Commons Attribution–Share Alike 3.0 Unported license . If you distribute this document or an adaptation of it, you must provide the URL for the original version.

Red Hat, as the licensor of this document, waives the right to enforce, and agrees not to assert, Section 4d of CC-BY-SA to the fullest extent permitted by applicable law.

Red Hat, the Red Hat logo, JBoss, Hibernate, and RHCE are trademarks or registered trademarks of Red Hat, LLC. or its subsidiaries in the United States and other countries.

Linux® is the registered trademark of Linus Torvalds in the United States and other countries.

XFS is a trademark or registered trademark of Hewlett Packard Enterprise Development LP or its subsidiaries in the United States and other countries.

The OpenStack® Word Mark and OpenStack logo are trademarks or registered trademarks of the Linux Foundation, used under license.

All other trademarks are the property of their respective owners.

---

*Source: https://docs.redhat.com/en/documentation/red_hat_developer_hub/1.9/html-single/interacting_with_model_context_protocol_tools_for_red_hat_developer_hub/index*

Interacting with Model Context Protocol tools for Red Hat Developer Hub | Red Hat Developer Hub | 1.9 | Red Hat Documentation

 

 

 

 

 

 

 

 

# Interacting with Model Context Protocol tools for Red Hat Developer Hub

---

Red Hat Developer Hub 1.9

## Leveraging the Model Context Protocol (MCP) server to integrate Red Hat Developer Hub (RHDH) with AI clients

Red Hat Customer Content Services

Legal Notice

Abstract

Leverage the Model Context Protocol (MCP) server to integrate Red Hat Developer Hub (RHDH) with AI clients. This connection provides a standardized method for AI applications to access RHDH information and workflows through defined MCP tools.

---

## Chapter 1. Interacting with Model Context Protocol tools for Red Hat Developer HubCopy link

### 1.1. Understanding Model Context ProtocolCopy link

Important

This section describes Developer Preview features in the Model Context Protocol plugin. Developer Preview features are not supported by Red Hat in any way and are not functionally complete or production-ready. Do not use Developer Preview features for production or business-critical workloads. Developer Preview features provide early access to functionality in advance of possible inclusion in a Red Hat product offering. Customers can use these features to test functionality and provide feedback during the development process. Developer Preview features might not have any documentation, are subject to change or removal at any time, and have received limited testing. Red Hat might provide ways to submit feedback on Developer Preview features without an associated SLA.

For more information about the support scope of Red Hat Developer Preview features, see [Developer Preview Support Scope](https://access.redhat.com/support/offerings/devpreview/).

Model Context Protocol (MCP) connects AI models and applications (MCP clients) to external systems (MCP servers) to access information and workflows. MCP servers define the tools that MCP clients can interact with. Red Hat Developer Hub (RHDH) supports MCP tools through the`mcp-actions-backend` plugin available in Backstage 1.40 or later.

Important

You must verify that your model supports tool calling before you enable Model Context Protocol (MCP) features. Using an incompatible model results in error messages.

### 1.2. Installing the MCP server and tool plugins in Red Hat Developer HubCopy link

To enable MCP support in Red Hat Developer Hub, you must install the following components:

Backend MCP server plugin Responsible for running MCP tools. MCP tool plugins Individual MCP plugins that expose capabilities relating to the Software Catalog and TechDocs features in RHDH.

Prerequisites

- Legal Notice
- Your RHDH instance is installed and running.

Procedure

Install the backend MCP server plugin: In your dynamic plugins ConfigMap (for example,`dynamic-plugins-rhdh.yaml`), add the MCP server plugin as shown in the following example:

```yaml
plugins:
    - package: oci://ghcr.io/redhat-developer/rhdh-plugin-export-overlays/backstage-plugin-mcp-actions-backend:bs_1.45.3__0.1.5
      disabled: false
```

Install any of the following MCP tools that you would like to use:

Software Catalog MCP tool:

```yaml
  - package: oci://ghcr.io/redhat-developer/rhdh-plugin-export-overlays/red-hat-developer-hub-backstage-plugin-software-catalog-mcp-tool:bs_1.45.3__0.4.1
    disabled: false
```

TechDocs MCP

```yaml
- package: oci://ghcr.io/redhat-developer/rhdh-plugin-export-overlays/red-hat-developer-hub-backstage-plugin-techdocs-mcp-tool:bs_1.45.3__0.3.2
  disabled: false
```

### 1.3. Configuring Model Context Protocol in Red Hat Developer HubCopy link

You can enable your MCP client applications to use the MCP protocol to access RHDH information and workflows. This configuration is a prerequisite for MCP clients to use the defined MCP tools and access the exposed capabilities of RHDH.

Prerequisite

- You have [installed the MCP server and tool plugins in Red Hat Developer Hub](https://docs.redhat.com/en/documentation/red_hat_developer_hub/1.9/html-single/interacting_with_model_context_protocol_tools_for_red_hat_developer_hub/index#proc-installing-the-mcp-server-and-tool-plugins).

Procedure

In your Red Hat Developer Hub`app-config.yaml` file, configure a static token for authentication against the MCP server endpoint. MCP clients (such as`Cursor`,`Continue`, or`Lightspeed Core`) use these tokens to authenticate against the Backstage MCP server. For example:

```yaml
backend:
  auth:
    externalAccess:
      - type: static
        options:
          token: ${MCP_TOKEN}
          subject: mcp-clients
```

where:

`${MCP_TOKEN}`

Set the token value that you generate manually and share with your MCP clients.

Note

Tokens must be long and complex strings without whitespace to prevent brute-force guessing.

1. To generate a sample token, use the following command:

```plaintext
$ node -p `require("crypto").randomBytes(24).toString("base64")`
```

Register the MCP tools that you install as a plugin source:

```yaml
backend:
  actions:
    pluginSources:
      - software-catalog-mcp-tool
      - techdocs-mcp-tool
```

```yaml
# Full app-config.yaml file example with MCP configuration
app:
  title: AI Dev Developer Hub
  baseUrl: "${RHDH_BASE_URL}"
auth:
  environment: development
  session:
    secret: "${BACKEND_SECRET}"
  providers:
    guest:
      dangerouslyAllowOutsideDevelopment: true
backend:
  actions:
    pluginSources:
      - 'software-catalog-mcp-tool'
      - 'techdocs-mcp-tool'
  auth:
    externalAccess:
      - type: static
        options:
          token: ${MCP_TOKEN}
          subject: mcp-clients
    keys:
      - secret: "${BACKEND_SECRET}"
  baseUrl: "${RHDH_BASE_URL}"
  cors:
    origin: "${RHDH_BASE_URL}"
signInPage: oidc
```

#### 1.3.1. Configuring MCP clients to access the RHDH serverCopy link

You must configure an MCP client before it can interact with the MCP server. For more information on the list of clients and their respective configurations, see [Example Clients](https://modelcontextprotocol.io/clients).

Prerequisites

You have configured one of the following endpoints as the server URL, where` ` is the hostname of your RHDH instance.

SSE (Legacy):`https:// /api/mcp-actions/v1/sse`

Note

Some clients do not yet support the Streamable endpoint, and you might need to use the SSE endpoint instead.

- Streamable:`https:// /api/mcp-actions/v1`

Procedure

Configure Cursor as a client.

Add the following configuration:

```yaml
{
  "mcpServers": {
    "backstage-actions": {
      "url": "https://<my_developer_hub_domain>/api/mcp-actions/v1",
      "headers": {
        "Authorization": "Bearer <MCP_TOKEN>"
      }
    }
  }
}
```

where:

` ` Enter the previously configured static token` ` Enter the hostname of your RHDH instance

1. From your Desktop app, navigate to Cursor Settings and select MCP Tools > New MCP Server.

Configure Continue as a client.

In your agent yaml configuration file, add the following configuration:

```yaml
mcpServers:
  - name: backstage-actions
    type: sse
    url: https://<my_developer_hub_domain>/api/mcp-actions/v1/sse
    requestOptions:
      headers:
        Authorization: "Bearer <MCP_TOKEN>"
```

where:

` ` Enter the previously configured static token` ` Enter the hostname of your RHDH instance

Configure Developer Lightspeed for RHDH as a client. For more details, see [Red Hat Developer Lightspeed for Red Hat Developer Hub](https://docs.redhat.com/en/documentation/red_hat_developer_hub/1.9/html-single/interacting_with_red_hat_developer_lightspeed_for_red_hat_developer_hub/index).

In the`lightspeed-stack.yaml` configuration, add the following configuration for`mcp_servers`:

```yaml
mcp_servers:
  - name: mcp::backstage
    provider_id: model-context-protocol
    url: https://<my_developer_hub_domain>/api/mcp-actions/v1
    authorization_headers:
      Authorization: "client"
```

where:

`model-context-protocol` This is the tool runtime provider defined and configured in the llama-stack`run.yaml` configuration for use in LCS.

Optional: If you want to use your own Llama Stack configuration, add the following code to your Llama Stack configuration file (`run.yaml`).

```yaml
providers:
  tool_runtime:
  - provider_id: model-context-protocol
    provider_type: remote::model-context-protocol
    config: {}
```

To authorize requests to the MCP endpoint using` `, add it in the Developer Lightspeed for RHDH`app-config.yaml` file, to make POST requests to LCS, as shown in the following code:

```yaml
lightspeed:
  mcpServers:
  - name: mcp::backstage
    token: ${MCP_TOKEN}
```

Optional: You can query the LCS`/v1/streaming_query` endpoint directly by providing the`MCP_TOKEN` in the header, as shown in the following code:

```yaml
curl -X POST \
  -H `Content-Type: application/json` \
  -H `MCP-HEADERS: {"mcp::backstage": {"Authorization": "Bearer <MCP_TOKEN>"}}` \
  -d `{"query": "Can you give me all catalog templates of type 'service', "model": "gpt-4o-mini", "provider": "openai"}` \
  _<url>_/v1/streaming_query
```

where:

` ` Enter the LCS endpoint. You can use localhost(pass:c,a,q:[`.my-rhdh-project.svc.cluster.local:8080`]) or the service name for this field if you are inside the Backstage container.

### 1.4. Accessing RHDH data using the Software Catalog MCP toolsCopy link

Use Software Catalog MCP tools to manage and retrieve RHDH entities such as Components, Systems, Resources, APIs, Locations, Users, and Groups.

Software Catalog tool reference

The`software-catalog-mcp-tool` plugin provides tools to interact with the software catalog. By default, these tools return data in a JSON array format.

Expand

`fetch-catalog-entities`

Lists RHDH entities such as components, APIs, and resources.

`kind`,`type`,`name`,`owner`,`lifecycle`,`tags`,`verbose`

`catalog-register-tool`

Registers a new entity in the software catalog using a`catalog-info.yaml` file URL.

`url`

`catalog-unregister-tool`

Removes an existing entity from the software catalog.

`entityRef`

`software-template-metadata-tool`

Retrieves metadata for a specific software template.

`templateRef`

| Table 1.1. Available tools in the software-catalog-mcp-tool plugin | Tool | Description | Parameters |
| --- | --- | --- | --- |

Show more

#### 1.4.1. Fetching entities using fetch-catalog-entitiesCopy link

The`fetch-catalog-entities` tool lists RHDH entities, including components, APIs, and resources.

Common query examples

- "Fetch all ai-model resources in the Backstage catalog"
- "Fetch the API definition for the beneficiary-management-api API"
- "Construct a curl command based on the API definition for the “insert beneficiary” endpoint in the beneficiary-management-api"

Expand

`kind`

Filters by entity kind.

`"Component"`

`type`

Filters by entity type. [NOTE]: You must use the`kind` parameter with this filter.

`"model-server"`

`name`

Specifies a specific entity name.

`"vllm-model-server"`

`owner`

Filters entities by their owner.

`"test-platform"`

`lifecycle`

Filters entities by their lifecycle.

`"development"`

`tags`

Filters entities by their tags.

`["genai", "ibm", "llm"]`

`verbose`

Retrieves the full Backstage entity object instead of a shortened output when set to`true`.

`true`

| Table 1.2. Parameters for the fetch-catalog-entities tool | Parameter | Description | Example |
| --- | --- | --- | --- |

Show more

#### 1.4.2. Registering entities using catalog-register-toolCopy link

Use the`catalog-register-tool` to add new entities to your Software Catalog.

Expand

`url`

The URL to the`catalog-info.yaml` file.

| Table 1.3. catalog-register-tool parameters | Parameter | Description |
| --- | --- | --- |

Show more

##### 1.4.2.1. Unregistering entities using catalog-unregister-toolCopy link

Use the`catalog-unregister-tool` to remove an existing entity from the Software Catalog.

Expand

`location`

A valid catalog location URL or a UUID.

| Table 1.4. Parameters for catalog-unregister-tool | Parameter | Description |
| --- | --- | --- |

Show more

#### 1.4.3. Retrieving Software Template metadataCopy link

Use the`software-template-metadata-tool` to retrieve metadata for a specific Software Template.

Expand

`templateRef`

The reference identifier for the Software Template.

| Table 1.5. Parameters for software-template-metadata-tool | Parameter | Description |
| --- | --- | --- |

Show more

### 1.5. Accessing and analyzing documentation using the TechDocs MCP toolsCopy link

The TechDocs MCP tool enables MCP clients to search and retrieve documentation from RHDH for use as context in AI applications.

Expand

`fetch-techdocs`

Lists all entities with registered TechDocs. Includes metadata such as`lastModified` and build information.

`entityType`,`namespace`,`owner`,`lifecycle`,`tags`

`analyze-techdocs-coverage`

Calculates the percentage of entities with TechDocs configured to identify documentation gaps.

`entityType`,`namespace`,`owner`,`lifecycle`,`tags`

`retrieve-techdocs-content`

Retrieves the content of a specific TechDocs resource.

`entityRef`,`pagePath`

| Table 1.6. TechDocs tools and parameters | Tool | Description | Parameters |
| --- | --- | --- | --- |

Show more

#### 1.5.1. Retrieving TechDocs URLs and metadata using fetch-techdocsCopy link

The`fetch-techdocs` TechDocs MCP tool lists all Backstage entities with TechDocs. By default, the tool returns results in a JSON array format. Each entry includes entity details and TechDocs metadata, like last update timestamp and build information.

By default, each entry in the JSON array is an entity with the following fields:`name`,`title`,`tags`,`description`,`owner`,`lifecycle`,`namespace`,`kind`,`techDocsUrl`,`matadataUrl` and`metadata`.

The following examples show common queries:

- “Fetch all techdocs from the Backstage server”
- “Fetch all techdocs of the default namespace”
- “Fetch all techdocs created by user:john.doe”

Expand

`entityType`

Filters entities by their type.

"Component"

`namespace`

Filter entities by their namespace.

"default"

`owner`

Filters entities by owner.

"user:john.doe"

`lifecycle`

Filters entities by their lifecycle.

"development"

`tags`

Filters entities by their tags.

["genai, "ibm", "llm", "granite", "conversational", "task-text-generation"]

| Table 1.7. fetch-techdocs TechDocs MCP tool. | Name | Description | Example |
| --- | --- | --- | --- |

Show more

#### 1.5.2. Measuring documentation gaps using analyze-techdocs-coverageCopy link

The`analyze-techdocs-coverage` TechDocs MCP tool calculates the percentage of entities that have TechDocs configured. Use this tool to identify documentation gaps and improve overall documentation coverage.

You can filter results by the following entity attributes: *`type`*`namespace`*`owner`*`lifecycle`*`tags` By default,`analyze-techdocs-coverage` returns a JSON entity that includes the`totalEntities`,`entitiesWithDocs`, and`coveragePercentage` fields.

The following examples show common queries:

- “What is the coverage of techdocs in the backstage server”
- “What is the coverage of techdocs in the default namespace”

Expand

`entityType`

Filters entities by their type.

"Component"

`namespace`

Filter entities by their namespace.

"default"

`owner`

Filters entities by owner.

"user:john.doe"

`lifecycle`

Filters entities by their lifecycle.

"development"

`tags`

Filters entities by their tags.

["genai, "ibm", "llm", "granite", "conversational", "task-text-generation"]

| Table 1.8. Parameters for the analyze-techdocs-coverage TechDocs MCP tool | Name | Description | Example |
| --- | --- | --- | --- |

Show more

#### 1.5.3. Finding a specific TechDoc using retrieve-techdocs-contentCopy link

The`retrieve-techdocs-content` TechDocs MCP tool retrieves the content of a TechDocs resource, enabling AI clients to access documentation content for specific Software Catalog entities. By default, the tool returns a JSON entity with the following fields:`entityRef`,`name`,`title`,`kind`,`namespace`,`content`,`path`,`contentType`,`lastModified`, and`metadata`.

The following examples show common queries:

- “Fetch techdoc with reference component:default/my-service”
- “Fetch page about.html from techdoc with reference component:default/my-service”

Procedure

- Use the parameters as shown in the following table to configure your`retrieve-techdocs-content` TechDocs MCP tool.

Expand

`entityRef`

Specifies the entity to retrieve using the`kind:namespace/name` format.

"component:default/my-service"

`pagePath`

Specifies the path to a specific documentation page. Defaults to`index.html`

"index.html"

| | Name | Description | Example |
| --- | --- | --- | --- |

Show more

### 1.6. Troubleshooting MCP server and client problemsCopy link

The following procedures guide you through resolving common issues when using a Model Communication Protocol (MCP) server and tool.

#### 1.6.1. Verifying successful installation of MCP pluginsCopy link

Procedure

Log in to the OCP cluster running RHDH and go to your RHDH project using the following code:

```plaintext
$ oc project {my-product-namespace}
```

Inspect the logs for the installation of the RHDH dynamic plugins using the following code:

```plaintext
$ oc logs -c install-dynamic-plugins deployment/<my-product-deployment>
```

Verification

You must see an entry for the MCP backend server plugin as shown in the following code:

```plaintext
..... prior logs ....
======= Installing dynamic plugin oci://ghcr.io/redhat-developer/rhdh-plugin-export-overlays/backstage-plugin-mcp-actions-backend:bs_1.42.5__0.1.2!backstage-plugin-mcp-actions-backend
	==> Copying image oci://ghcr.io/redhat-developer/rhdh-plugin-export-overlays/backstage-plugin-mcp-actions-backend:next__0.2.0 to local filesystem
	==> Successfully installed dynamic plugin oci://ghcr.io/redhat-developer/rhdh-plugin-export-overlays/backstage-plugin-mcp-actions-backend:bs_1.42.5__0.1.2!backstage-plugin-mcp-actions-backend
```

You must see entries for any of the MCP tool plugins you installed as shown in the following code:

```plaintext
..... prior logs ....
======= Installing dynamic plugin oci://ghcr.io/redhat-developer/rhdh-plugin-export-overlays/red-hat-developer-hub-backstage-plugin-software-catalog-mcp-tool:bs_1.42.5__0.2.3!red-hat-developer-hub-backstage-plugin-software-catalog-mcp-tool
	==> Copying image oci://ghcr.io/redhat-developer/rhdh-plugin-export-overlays/red-hat-developer-hub-backstage-plugin-software-catalog-mcp-tool:bs_1.42.5__0.2.3!red-hat-developer-hub-backstage-plugin-software-catalog-mcp-tool to local filesystem
	==> Successfully installed dynamic plugin oci://ghcr.io/redhat-developer/rhdh-plugin-export-overlays/red-hat-developer-hub-backstage-plugin-software-catalog-mcp-tool:bs_1.42.5__0.2.3!red-hat-developer-hub-backstage-plugin-software-catalog-mcp-tool
```

#### 1.6.2. Checking MCP tool logs for status and errorsCopy link

The Backstage`LoggerService` target name starts with the name of the MCP tool (either`software-catalog-mcp-tool` or`techdocs-mcp-tool`). The MCP tools generate a log by default. For example:

```shell-session
`[backend]: 2025-09-25T16:24:22.660Z software-catalog-mcp-tool info fetch-catalog-entities: Fetching catalog entities with options: kind="Component"`
```

If any errors occur in the MCP tools, check the logs.

#### 1.6.3. Understand and respond to MCP tool error messagesCopy link

The MCP tools response provides an optional error message that communicates any issues encountered during the use of the tool, including potential input validation errors.

#### 1.6.4. Resolving the Model does not support tool calling errorCopy link

This error indicates that the model configured in your MCP client lacks the required functionality to handle tool calls. The error message appears similar to:`Invalid request: model gemma3:27b does not support tool calls`.

Procedure

1. Consult your model documentation to confirm its support for tool calling.
2. If the current model does not support tool calling, change the model that your MCP client uses to a tool-calling compatible model.

#### 1.6.5. Resolving authentication issues when tools are not foundCopy link

If your MCP client connects to the server but cannot find deployed tools, an authentication or configuration issue is likely.

Procedure

Verify the authentication token.

1. Configure a static token for the RHDH MCP server.
2. Set this token as the bearer token in your MCP client and make sure the authorization header is the configuration that specifies`Bearer` before the token, for example,`Bearer `.

Check the MCP endpoint configuration.

1. Confirm that the MCP server URL properly resolves, particularly if you are using a desktop client.
2. Use legacy SSE endpoint if your MCP client requires it instead of the Streamable endpoint. (For more details, see the Configuration topic).

Verify your RHDH`app-config.yaml` file for duplicate backend entries and make sure that the indentation is accurate.

1. Make sure that the configuration for the static token and MCP plugin sources go under an existing backend field, if one is present.
2. If you are unsure, see [the sample app configuration provided in the reference procedure](https://docs.redhat.com/en/documentation/red_hat_developer_hub/1.9/html-single/interacting_with_model_context_protocol_tools_for_red_hat_developer_hub/index#proc-configuring-model-context-protocol).

#### 1.6.6. Resolve nonsensical MCP tool outputCopy link

Nonsensical output often occurs when smaller models or models with smaller context sizes cannot effectively manage repeated tool calls within the same context window.

Procedure

To improve the quality of the tool output, take the following actions:

Select an appropriate model for tool calling.

1. Verify that the model has good support for tool calling.
2. Make sure your model is not too small. We recommend a model with at least 7 billion parameters and a context window of 32k.

Refine your queries.

1. Use more well-defined queries that limit the amount of data returned in the response from the tool.

## Legal NoticeCopy link

Copyright © Red Hat.

Except as otherwise noted below, the text of and illustrations in this documentation are licensed by Red Hat under the Creative Commons Attribution–Share Alike 3.0 Unported license . If you distribute this document or an adaptation of it, you must provide the URL for the original version.

Red Hat, as the licensor of this document, waives the right to enforce, and agrees not to assert, Section 4d of CC-BY-SA to the fullest extent permitted by applicable law.

Red Hat, the Red Hat logo, JBoss, Hibernate, and RHCE are trademarks or registered trademarks of Red Hat, LLC. or its subsidiaries in the United States and other countries.

Linux® is the registered trademark of Linus Torvalds in the United States and other countries.

XFS is a trademark or registered trademark of Hewlett Packard Enterprise Development LP or its subsidiaries in the United States and other countries.

The OpenStack® Word Mark and OpenStack logo are trademarks or registered trademarks of the Linux Foundation, used under license.

All other trademarks are the property of their respective owners.
