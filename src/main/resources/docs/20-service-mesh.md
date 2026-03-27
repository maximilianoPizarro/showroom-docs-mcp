# Red Hat OpenShift Service Mesh 3.3

> Fetched from Red Hat Documentation (`html-single`). The Integration category guide **Observability and Service Mesh** is at `html-single/observability/index`; URLs using the segment `integration` returned 404.

About | Red Hat OpenShift Service Mesh | 3.3 | Red Hat Documentation

 

 

 

 

 

 

 

 

# About

---

Red Hat OpenShift Service Mesh 3.3

## About OpenShift Service Mesh

Red Hat OpenShift Documentation Team

Legal Notice

Abstract

This document provides an overview of OpenShift Service Mesh features.

---

## Chapter 1. About OpenShift Service MeshCopy link

You can use Red Hat OpenShift Service Mesh to manage the connectivity, security, and observability of microservices. Based on the [Istio project](https://istio.io/), OpenShift Service Mesh provides a centralized control point in your application.

### 1.1. Introduction to Red Hat OpenShift Service MeshCopy link

Red Hat OpenShift Service Mesh adds a transparent layer on existing distributed applications without requiring any changes to the application code. The mesh introduces an easy way to create a network of deployed services that provides discovery, load balancing, service-to-service authentication, failure recovery, metrics, and monitoring. A service mesh also provides more complex operational functionality, including A/B testing, canary releases, access control, and end-to-end authentication.

Microservice architectures split the work of enterprise applications into modular services, which can make scaling and maintenance easier. However, as an enterprise application built on a microservice architecture grows in size and complexity, it becomes difficult to understand and manage. Service Mesh can address those architecture problems by capturing or intercepting traffic between services and can modify, redirect, or create new requests to other services.

### 1.2. Core featuresCopy link

Red Hat OpenShift Service Mesh provides a number of key capabilities uniformly across a network of services:

- Legal Notice
- Traffic Management - Control the flow of traffic and API calls between services, make calls more reliable, and make the network more robust in the face of adverse conditions.
- Service Identity and Security - Provide services in the mesh with a verifiable identity and provide the ability to protect service traffic as it flows over networks of varying degrees of trustworthiness.
- Policy Enforcement - Apply organizational policy to the interaction between services, ensure access policies are enforced and resources are fairly distributed among consumers. Policy changes are made by configuring the mesh, not by changing application code.
- Telemetry - Gain understanding of the dependencies between services and the nature and flow of traffic between them, providing the ability to quickly identify issues.

## Chapter 2. Understanding OpenShift Service MeshCopy link

You can use Red Hat OpenShift Service Mesh to connect, secure, and monitor microservices in your Red Hat OpenShift Service Mesh environment. Core resources, Kiali integrations, and observability components comprise the service mesh ecosystem.

### 2.1. Red Hat OpenShift Service Mesh resourcesCopy link

Red Hat OpenShift Service Mesh is composed of two parts:

- Red Hat OpenShift Service Mesh resources
- Kiali provided by Red Hat

Kiali provided by Red Hat is composed of three parts:

- Kiali Operator provided by Red Hat
- Kiali Server
- OpenShift Service Mesh Console (OSSMC) plugin

OpenShift Service Mesh integrates with the following:

Observability components such as:

- OpenShift Monitoring
- Red Hat OpenShift distributed tracing platform
- Red Hat OpenShift distributed tracing data collection Operator

Red Hat OpenShift Service Mesh Operator manages the lifecycle of your Istio control planes. Instead of creating a new configuration schema, OpenShift Service Mesh Operator APIs are built around Istio’s Helm chart APIs.

Note

- Argo rollouts
- Though Red Hat OpenShift Service Mesh APIs are built around Istio’s Helm chart APIs, Helm charts are not supported.
- All installation and configuration options that are exposed by Istio’s Helm charts are available through the Red Hat OpenShift Service Mesh Custom Resource Definition (CRD)`values` fields.

#### 2.1.1. Istio resourceCopy link

The`Istio` resource is used to manage your Istio control planes. It is a cluster-wide resource, because the Istio control plane operates in and requires access to the entire cluster.

To select a namespace to run the control plane pods in, you can use the`spec.namespace` field.

Note

The`spec.namespace` field is immutable: in order to move a control plane to another namespace, you must remove the`Istio` resource and recreate it with a different`spec.namespace`.

You can access all`Istio` custom resource definition (CRD) options through`spec.values` fields:

Example`Istio` resource CRD

```yaml
apiVersion: sailoperator.io/v1
kind: Istio
metadata:
  name: default
spec:
  version: v1.22.3
  namespace: istio-system
  updateStrategy:
    type: InPlace
  values:
    pilot:
      resources:
        requests:
          cpu: 100m
          memory: 1024Mi
```

You can run the following command to see all the customization options:

```shell-session
$ oc explain istios.spec.values
```

To perform canary updates of the control plane, OpenShift Service Mesh supports multiple Istio versions. You can set the`version` field to the new version by either using the full version or the`v. -latest` alias to automatically select the latest version for a specific minor version. For example, setting`v1.23-latest` ensures that the Operator maintains the latest version of Istio 1.23.

OpenShift Service Mesh supports two different update strategies for your control planes:

`InPlace` The OpenShift Service Mesh Operator immediately replaces your existing control plane resources with the ones for the new version.`RevisionBased` Uses Istio’s canary update mechanism by creating a second control plane to which you can migrate your workloads to complete the update.

After creating an Istio resource, OpenShift Service Mesh generates a revision name for the resource based on the`updateStrategy`, and creates a corresponding`IstioRevision`.

#### 2.1.2. IstioRevision resourceCopy link

The`IstioRevision` is a cluster-wide resource and the lowest-level API OpenShift Service Mesh provides. It is usually not created by the user, but by the Operator itself. Its schema closely resembles that of the`Istio` resource - but instead of representing the state of a control plane you want to be present in your cluster, it represents a revision of that control plane.

A revision of the control plane you want to be present in your cluster is an instance of Istio with a specific version and revision name, and its revision name can be used to add workloads or entire namespaces to the mesh. For example: by using the`istio.io/rev= ` label.

You can think of the relationship between the`Istio` and`IstioRevision` resources as similar to the relationship between Kubernetes' replica set and pod: a replica set can be created by users and results in the automatic creation of pods, which will trigger the instantiation of your containers.

Similarly, users create an`Istio` resource which instructs the OpenShift Service Mesh Operator to create a matching`IstioRevision` resource, which then in turn triggers the creation of the Istio control plane. To do that, the OpenShift Service Mesh Operator will copy all of your relevant configuration from the`Istio` resource to the`IstioRevision` resource.

#### 2.1.3. IstioRevisionTag resourceCopy link

The`IstioRevisionTag` resource represents a stable revision tag that functions as an alias for Istio control plane revisions. With the stable tag,`prod`, you can use the label`istio.io/rev=prod` to inject proxies into your workloads. When you perform an upgrade to a control plane with a new revision name, you can update your tag to point to the new revision instead of having to relabel your workloads and namespaces. For more information, see [Stable revision labels](https://istio.io/latest/docs/setup/upgrade/canary/#stable-revision-labels)(Istio documentation).

You can use the`IstioRevisionTag` resource with the OpenShift Service Mesh Operator. Therefore you can reference both an`IstioRevision` and an`Istio` resource. When using an`Istio` resource, after you update your control plane, the underlying`IstioRevision` resource changes, and the OpenShift Service Mesh Operator automatically updates your revision tag. You only need to restart your deployments to re-inject the new proxies.

The`IstioRevisionTag` has one field in its`spec:` field,`targetRef`, which can reference an`Istio` or`IstioRevision` resource. After deploying the`IstioRevisionTag`, you can use both the`istio.io/rev=default` and`istio-injection=enabled` labels to inject proxies into your workloads.

The`istio-injection` label can only be used for revisions and revision tags that have the name`default`, like the`IstioRevisionTag` resource in the following example:

Example`IstioRevisionTag` resource

```yaml
apiVersion: sailoperator.io/v1
kind: IstioRevisionTag
metadata:
  name: default
spec:
  targetRef:
    kind: Istio 
```

1

```yaml

    name: prod  
```

2

1

This value can be either`Istio` or`IstioRevision`.

2

The name of the`Istio` or`IstioRevision` resource.

#### 2.1.4. IstioCNI resourceCopy link

The lifecycle of Istio’s Container Network Interface (CNI) plugin is managed separately when using OpenShift Service Mesh Operator. To install Istio’s CNI plugin, you create an`IstioCNI` resource.

The`IstioCNI` resource is a cluster-wide resource as it installs a daemon set that operates on all nodes of your cluster. You can select a version by setting the`spec.version` field, as you can see in the example that follows. To update the CNI plugin, change the version field to the version you want to install. Like the`Istio` resource, it also has a`values` field that exposes all of the options provided in the`istio-cni` chart:

Example`IstioCNI` resource

```yaml
apiVersion: sailoperator.io/v1
kind: IstioCNI
metadata:
  name: default
spec:
  version: v1.22.3
  namespace: istio-cni
  values:
    cni:
      cniConfDir: /etc/cni/net.d
      excludeNamespaces:
      - kube-system
```

### 2.2. Red Hat OpenShift Service Mesh and KialiCopy link

Kiali is based on the open source Kiali project. See [Kiali project](https://kiali.io/). Kiali provided by Red Hat is composed of three parts:

- Kiali Operator provided by Red Hat
- Kiali Server
- OpenShift Service Mesh Console (OSSMC) plugin

Working together, they form the user interface (UI) for OpenShift Service Mesh. Kiali provides visibility into your service mesh by showing you the microservices and how they are connected.

Kiali helps you define, validate, and observe your Istio service mesh. It helps you to understand the structure of your service mesh by inferring the topology, and also provides information about the health of your service mesh.

Kiali provides an interactive graph view of your mesh namespaces in near real time that provides visibility into features like circuit breakers, request rates, latency, and even graphs of traffic flows. Kiali offers insights about components at different levels, such as applications, services, workloads, and can display the interactions with contextual information and charts on the selected graph node or edge.

Kiali also provides the ability to validate your Istio configurations, such as gateways, destination rules, virtual services, mesh policies, and so on. Kiali provides detailed metrics, and a basic Grafana integration is available for advanced queries. Distributed tracing is provided by integrating Red Hat OpenShift distributed tracing platform (Tempo) and Red Hat OpenShift distributed tracing data collection into the Kiali console.

#### 2.2.1. Kiali architectureCopy link

Kiali Server (back end) This component runs in the container application platform and communicates with the service mesh components, retrieves and processes data, and exposes this data to the console. The Kiali Server does not need storage. When deploying the Server to a cluster, configurations are set in config maps and secrets. Kiali console (front end) The Kiali console is a web application. The console queries the Kiali Server for data to present it to the user.

In addition, Kiali depends on external services and components provided by the container application platform and Istio.

Red Hat Service Mesh (Istio) Istio is a Kiali requirement. Istio is the component that provides and controls the service mesh. Although Kiali and Istio can be installed separately, Kiali depends on Istio and will not work if it is not present. Kiali needs to retrieve Istio data and configurations, which are exposed through Prometheus and the Red Hat OpenShift Service Mesh cluster API. Prometheus A dedicated Prometheus instance is optional. When Istio telemetry is enabled, metrics data are stored in Prometheus. Kiali uses this Prometheus data to determine the mesh topology, display metrics, calculate health, show possible problems, and so on. Kiali communicates directly with Prometheus and assumes the data schema used by Istio Telemetry. Prometheus is an Istio dependency and a hard dependency for Kiali, and many of Kiali’s features will not work without Prometheus. OpenShift Container Platform API Kiali uses the OpenShift Container Platform API to fetch and resolve service mesh configurations. For example, Kiali queries the cluster API to retrieve definitions for namespaces, services, deployments, pods, and other entities. Kiali also makes queries to resolve relationships between the different cluster entities. The cluster API is also queried to retrieve Istio configurations like virtual services, destination rules, route rules, gateways, quotas, and so on. Tracing Tracing is optional, but when you install Red Hat OpenShift distributed tracing platform and Kiali is configured, the Kiali console includes a tab to display distributed tracing data, and tracing integration on the graph itself. Note that tracing data will not be available if you disable Istio’s distributed tracing feature. Also note that the user must have access to the namespace where the user needs to see tracing data. Grafana Grafana is optional. When available, the metrics pages of Kiali display links to direct the user to the same metric in Grafana. Note that Grafana is not supported as part of OpenShift Container Platform or OpenShift Service Mesh.

#### 2.2.2. Kiali featuresCopy link

The Kiali console is integrated with OpenShift Service Mesh and provides the following capabilities:

Health Quickly identify issues with applications, services, or workloads. Topology Visualize how your applications, services, or workloads communicate through the Kiali graph. Metrics Predefined metrics dashboards let you chart service mesh and application performance for Go, Node.js. Quarkus, Spring Boot, Thorntail and Vert.x. You can also create your own custom dashboards. Tracing Integration with Red Hat OpenShift distributed tracing platform (Tempo) lets you follow the path of a request through various microservices that make up an application. Validations Perform advanced validations on the most common Istio objects (Destination Rules, Service Entries, Virtual Services, and so on). Configuration Optional ability to create, update, and delete Istio routing configuration using wizards or directly in the YAML editor in the Kiali Console.

#### 2.2.3. OpenShift Service Mesh Console (OSSMC) pluginCopy link

The OpenShift Service Mesh Console (OSSMC) plugin is an OpenShift Container Platform plugin for Red Hat OpenShift Service Mesh. It integrates much of the Kiali Operator provided by Red Hat interface into the OpenShift Console, injecting both a Service Mesh main menu option with dedicated screens, as well as integrating Service Mesh tabs throughout console.

The OSSMC plugin is installed using Kiali Operator provided by Red Hat, and requires the Kiali Server component. OSSMC plugin has its own Custom Resource (CR) configuration.

### 2.3. Red Hat OpenShift Service Mesh and ObservabilityCopy link

Red Hat OpenShift Service Mesh integrates with Red Hat Observability components, including:

OpenShift Monitoring

Monitoring stack components are deployed by default in every OpenShift Container Platform installation and are managed by the Cluster Monitoring Operator (CMO). These components include Prometheus, Alertmanager, Thanos Querier, and so on. The CMO also deploys the Telemeter Client, which sends a subset of data from platform Prometheus instances to Red Hat to facilitate Remote Health Monitoring for clusters.

When you have added your application to the mesh, you can monitor the in-cluster health and performance of your applications running on OpenShift Container Platform with metrics and customized alerts for CPU and memory usage, network connectivity, and other resource usage.

Red Hat OpenShift distributed tracing platform

Red Hat OpenShift Service Mesh uses Red Hat OpenShift distributed tracing platform to allow developers to view call flows in a microservice application.

Integrating Red Hat OpenShift distributed tracing platform with Red Hat OpenShift Service Mesh is made of up two parts: Red Hat OpenShift distributed tracing platform (Tempo) and Red Hat OpenShift distributed tracing data collection.

Red Hat OpenShift distributed tracing platform (Tempo)

Provides distributed tracing to monitor and troubleshoot transactions in complex distributed systems. It is based on the open source [Grafana Tempo](https://grafana.com/oss/tempo/) project.

For more information about distributed tracing platform (Tempo), its features, installation, and configuration, see: [Red Hat OpenShift distributed tracing platform (Tempo)](https://docs.redhat.com/en/documentation/openshift_container_platform/4.16/html/distributed_tracing/distributed-tracing-platform-tempo).

Red Hat OpenShift distributed tracing data collection

Is based on the open source OpenTelemetry project, which aims to provide unified, standardized, and vendor-neutral telemetry data collection for cloud-native software. Red Hat OpenShift distributed tracing data collection product provides support for deploying and managing the OpenTelemetry Collector and simplifying the workload instrumentation. See [OpenTelemetry project](https://opentelemetry.io/)

The OpenTelemetry Collector can receive, process, and forward telemetry data in multiple formats, making it the ideal component for telemetry processing and interoperability between telemetry systems. The Collector provides a unified solution for collecting and processing metrics, traces, and logs. See [OpenTelemetry Collector](https://opentelemetry.io/docs/collector/).

For more information about distributed tracing data collection, its features, installation, and configuration, see: [Red Hat OpenShift distributed tracing data collection](https://docs.redhat.com/en/documentation/red_hat_build_of_opentelemetry/latest).

### 2.4. Red Hat OpenShift Service Mesh and cert-managerCopy link

The cert-manager tool is a solution for X.509 certificate management on Kubernetes. It delivers a unified API to integrate applications with private or public key infrastructure (PKI), such as Vault, Google Cloud Certificate Authority Service, Let’s Encrypt, and other providers.

The cert-manager tool ensures that the certificates are valid and up-to-date by attempting to renew the certificates at a configured time before they expire.

For Istio users, cert-manager also provides integration with`istio-csr`, which is a certificate authority (CA) server that handles certificate signing requests (CSR) from Istio proxies. The server then delegates signing to cert-manager, which forwards CSRs to the configured CA server.

### 2.5. Red Hat OpenShift Service Mesh and Argo RolloutsCopy link

Red Hat OpenShift Service Mesh, when used with Argo Rollouts, provides more advanced routing capabilities by using Istio, and does not require the configuration of a sidecar container.

You can use OpenShift Service Mesh to split traffic between two application versions.

- Canary version: A new version of an application where you gradually route the traffic.
- Stable version: The current version of an application. After the canary version is stable and has all the user traffic directed to it, it becomes the new stable version. The previous stable version is discarded.

The Istio-support within Argo Rollouts uses the`Gateway` and`VirtualService` resources to handle traffic routing.

- Gateway: You can use a Gateway to manage inbound and outbound traffic for your mesh. The gateway is the entry point of OpenShift Service Mesh and handles traffic requests sent to an application.
- VirtualService:`VirtualService` defines traffic routing rules and the percentage of traffic that goes to underlying services, such as the stable and canary services.

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

Installing | Red Hat OpenShift Service Mesh | 3.3 | Red Hat Documentation

 

 

 

 

 

 

 

 

# Installing

---

Red Hat OpenShift Service Mesh 3.3

## Installing OpenShift Service Mesh

Red Hat OpenShift Documentation Team

Legal Notice

Abstract

This documentation provides information about installing OpenShift Service Mesh.

---

## Chapter 1. Supported platforms and configurationsCopy link

Before you can install Red Hat OpenShift Service Mesh 3.3.1, you must subscribe to OpenShift Container Platform and install OpenShift Container Platform in a supported configuration. If you do not have a subscription on your Red Hat account, contact your sales representative for more information.

### 1.1. Supported platforms for Service MeshCopy link

The following platform versions support Service Mesh control plane version 3.3.1:

- Legal Notice
- Red Hat OpenShift Container Platform version 4.18 or later
- Red Hat OpenShift Dedicated version 4
- Azure Red Hat OpenShift (ARO) version 4
- Red Hat OpenShift Service on AWS (ROSA)

The Red Hat OpenShift Service Mesh Operator supports multiple versions of`Istio`.

If you are installing Red Hat OpenShift Service Mesh on a [restricted network](https://docs.redhat.com/en/documentation/openshift_container_platform/latest/html/installation_overview/installing-preparing#supported-installation-methods-for-different-platforms), follow the instructions for your chosen OpenShift Container Platform infrastructure.

For additional information about Red Hat OpenShift Service Mesh lifecycle and supported platforms, refer to the [Support Policy](https://access.redhat.com/support/policy/updates/openshift_operators).

### 1.2. Supported configurations for Service MeshCopy link

Red Hat OpenShift Service Mesh supports the following configurations:

- This release of Red Hat OpenShift Service Mesh is supported on OpenShift Container Platform x86_64, IBM Z®, IBM Power®, and Advanced RISC Machine (ARM).
- Configurations where all Service Mesh components are contained within a single OpenShift Container Platform cluster.
- Configurations that do not integrate external services such as virtual machines.

Note

Red Hat OpenShift Service Mesh does not support the`EnvoyFilter` configuration except where explicitly documented.

### 1.3. Supported network configurations for Service MeshCopy link

You can use the following OpenShift networking plugins for the Red Hat OpenShift Service Mesh:

- OpenShift-SDN.
- OVN-Kubernetes. See [About the OVN-Kubernetes network plugin](https://docs.redhat.com/en/documentation/openshift_dedicated/latest/html/networking/ovn-kubernetes-network-plugin#about-ovn-kubernetes) for more information.
- Third-Party Container Network Interface (CNI) plugins that have been certified on OpenShift Container Platform and passed Service Mesh conformance testing. See [Certified OpenShift CNI Plug-ins](https://access.redhat.com/articles/5436171) for more information.

#### 1.3.1. Supported configurations for KialiCopy link

- The Kiali console is supported on Google Chrome, Microsoft Edge, Mozilla Firefox, or Apple Safari browsers.
- The`openshift` authentication strategy is the only supported authentication configuration when Kiali is deployed with Red Hat OpenShift Service Mesh (OSSM). The`openshift` strategy controls access based on the user’s role-based access control (RBAC) roles of the OpenShift Container Platform.

## Chapter 2. Installing OpenShift Service MeshCopy link

Installing OpenShift Service Mesh consists of three main tasks: installing the OpenShift Operator, deploying Istio, and customizing the Istio configuration. Then, you can also choose to install the sample`bookinfo` application to push data through the mesh and explore mesh functionality.

Warning

Before installing OpenShift Service Mesh 3, make sure you are not running OpenShift Service Mesh 3 and OpenShift Service Mesh 2 in the same cluster, because it causes conflicts unless configured correctly. To migrate from OpenShift Service Mesh 2, see [Migrating from OpenShift Service Mesh 2.6](https://docs.redhat.com/en/documentation/red_hat_openshift_service_mesh/3.3/html-single/migrating_from_service_mesh_2_to_service_mesh_3/#ossm-migrating-read-me).

### 2.1. About deploying Istio using the Red Hat OpenShift Service Mesh OperatorCopy link

To deploy Istio using the Red Hat OpenShift Service Mesh Operator, you must create an`Istio` resource. Then, the Operator creates an`IstioRevision` resource, which represents one revision of the Istio control plane. Based on the`IstioRevision` resource, the Operator deploys the Istio control plane, which includes the`istiod``Deployment` resource and other resources.

The Red Hat OpenShift Service Mesh Operator may create additional instances of the`IstioRevision` resource, depending on the update strategy defined in the`Istio` resource.

#### 2.1.1. About Istio control plane update strategiesCopy link

The update strategy affects how the update process is performed. The`spec.updateStrategy` field in the`Istio` resource configuration determines how the OpenShift Service Mesh Operator updates the Istio control plane. When the Operator detects a change in the`spec.version` field or identifies a new minor release with a configured`vX.Y-latest` alias, it initiates an upgrade procedure. For each mesh, you select one of two strategies:

- `InPlace`
- `RevisionBased`

`InPlace` is the default strategy for updating OpenShift Service Mesh. Both the update strategies apply to sidecar and ambient modes.

If you use ambient mode, you must update the Istio Container Network Interface (CNI) and`ZTunnel` components in addition to the standard control plane update procedures.

Important

The`InPlace` update strategy is recommended for ambient mode. Using`RevisionBased` updates with ambient mode has limitations and requires manual intervention.

### 2.2. Installing the Service Mesh OperatorCopy link

Warning

For clusters without OpenShift Service Mesh instances, install the Service Mesh Operator. OpenShift Service Mesh operates cluster-wide and needs a scope configuration to prevent conflicts between Istio control planes. For clusters with OpenShift Service Mesh 3 or later, see "Deploying multiple service meshes on a single cluster".

Prerequisites

- You have deployed a cluster on OpenShift Container Platform 4.14 or later.
- You are logged in to the OpenShift Container Platform web console as a user with the cluster-admin role.

Procedure

On the Install Operator page, perform the following steps:

Select an Update Channel.

- Choose the stable channel to install the latest stable version of the Red Hat OpenShift Service Mesh 3 Operator. It is the default channel for installing the Operator.
- To install a specific version of the Red Hat OpenShift Service Mesh 3 Operator, choose the corresponding`stable- ` channel. For example, to install the Red Hat OpenShift Service Mesh Operator version 3.0.x, use the stable-3.0 channel.

Verification

1. Click Install to install the Operator.
2. Click Operators → Installed Operators to verify that the Service Mesh Operator is installed.`Succeeded` should show in the Status column.

#### 2.2.1. About Service Mesh custom resource definitionsCopy link

Installing the Red Hat OpenShift Service Mesh Operator also installs custom resource definitions (CRD) that administrators can use to configure Istio for Service Mesh installations. The Operator Lifecycle Manager (OLM) installs two categories of CRDs: Sail Operator CRDs and Istio CRDs.

Sail Operator CRDs define custom resources for installing and maintaining the Istio components required to operate a service mesh. These custom resources belong to the`sailoperator.io` API group and include the`Istio`,`IstioRevision`,`IstioCNI`, and`ZTunnel` resource kinds. For more information on how to configure these resources, see the`sailoperator.io` [API reference](https://github.com/istio-ecosystem/sail-operator/blob/main/docs/api-reference/sailoperator.io.md) documentation.

Istio CRDs are associated with mesh configuration and service management. These CRDs define custom resources in several`istio.io` API groups, such as`networking.istio.io` and`security.istio.io`. The CRDs also include various resource kinds, such as`AuthorizationPolicy`,`DestinationRule`, and`VirtualService`, that administrators use to configure a service mesh.

### 2.3. About Istio deploymentCopy link

To deploy Istio, you must create two resources:`Istio` and`IstioCNI`. The`Istio` resource deploys and configures the Istio Control Plane. The`IstioCNI` resource deploys and configures the Istio Container Network Interface (CNI) plugin. You should create these resources in separate projects; therefore, you must create two projects as part of the Istio deployment process.

You can use the OpenShift web console or the OpenShift CLI (oc) to create a project or a resource in your cluster.

Note

In the OpenShift Container Platform, a project is essentially a Kubernetes namespace with additional annotations, such as the range of user IDs that can be used in the project. Typically, the OpenShift Container Platform web console uses the term project, and the CLI uses the term namespace, but the terms are essentially synonymous.

#### 2.3.1. Creating the Istio project using the web consoleCopy link

The Service Mesh Operator deploys the Istio control plane to a project that you create. In this example,`istio-system` is the name of the project.

Prerequisties

- The Red Hat OpenShift Service Mesh Operator must be installed.
- You are logged in to the OpenShift Container Platform web console as cluster-admin.

Procedure

1. In the OpenShift Container Platform web console, click Home → Projects.
2. Click Create Project.
3. At the prompt, enter a name for the project in the Name field. For example,`istio-system`. The other fields provide supplementary information to the`Istio` resource definition and are optional.
4. Click Create. The Service Mesh Operator deploys Istio to the project you specified.

#### 2.3.2. Creating the Istio resource using the web consoleCopy link

Create the Istio resource that will contain the YAML configuration file for your Istio deployment. The Red Hat OpenShift Service Mesh Operator uses information in the YAML file to create an instance of the Istio control plane.

Prerequisties

- The Service Mesh Operator must be installed.
- You are logged in to the OpenShift Container Platform web console as cluster-admin.

Procedure

Click Create. This action deploys the Istio control plane.

When`State: Healthy` appears in the Status column, Istio is successfully deployed.

1. In the OpenShift Container Platform web console, click Operators → Installed Operators.
2. Select`istio-system` in the Project drop-down menu.
3. Click the Service Mesh Operator.
4. Click Istio.
5. Click Create Istio.
6. Select the`istio-system` project from the Namespace drop-down menu.

#### 2.3.3. Creating the IstioCNI project using the web consoleCopy link

The Service Mesh Operator deploys the Istio CNI plugin to a project that you create. In this example,`istio-cni` is the name of the project.

Prerequisties

- The Red Hat OpenShift Service Mesh Operator must be installed.
- You are logged in to the OpenShift Container Platform web console as cluster-admin.

Procedure

1. In the OpenShift Container Platform web console, click Home → Projects.
2. Click Create Project.
3. At the prompt, you must enter a name for the project in the Name field. For example,`istio-cni`. The other fields provide supplementary information and are optional.
4. Click Create.

#### 2.3.4. Creating the IstioCNI resource using the web consoleCopy link

Create an Istio Container Network Interface (CNI) resource, which contains the configuration file for the Istio CNI plugin. The Service Mesh Operator uses the configuration specified by this resource to deploy the CNI pod.

Prerequisties

- The Red Hat OpenShift Service Mesh Operator must be installed.
- You are logged in to the OpenShift Container Platform web console as cluster-admin.

Procedure

Click Create. This action deploys the Istio CNI plugin.

When`State: Healthy` appears in the Status column, the Istio CNI plugin is successfully deployed.

1. In the OpenShift Container Platform web console, click Operators → Installed Operators.
2. Select`istio-cni` in the Project drop-down menu.
3. Click the Service Mesh Operator.
4. Click IstioCNI.
5. Click Create IstioCNI.
6. Ensure that the name is`default`.

### 2.4. Scoping the Service Mesh with discovery selectorsCopy link

Service Mesh includes workloads that meet the following criteria:

- The control plane has discovered the workload.
- The workload has an Envoy proxy sidecar injected.

By default, the control plane discovers workloads in all namespaces across the cluster, with the following results:

- Each proxy instance receives configuration for all namespaces, including workloads not enrolled in the mesh.
- Any workload with the appropriate pod or namespace injection label receives a proxy sidecar.

In shared clusters, you might want to limit the scope of Service Mesh to only certain namespaces. This approach is especially useful if multiple service meshes run in the same cluster.

#### 2.4.1. About discovery selectorsCopy link

With discovery selectors, the mesh administrator can control which namespaces the control plane can access. By using a Kubernetes label selector, the administrator sets the criteria for the namespaces visible to the control plane, excluding any namespaces that do not match the specified criteria.

Note

Istiod always opens a watch to OpenShift for all namespaces. However, discovery selectors ignore objects that are not selected very early in its processing, minimizing costs.

The`discoverySelectors` field accepts an array of Kubernetes selectors, which apply to labels on namespaces. You can configure each selector for different use cases:

- Custom label names and values. For example, configure all namespaces with the label`istio-discovery=enabled`.
- A list of namespace labels by using set-based selectors with OR logic. For instance, configure namespaces with`istio-discovery=enabled` OR`region=us-east1`.
- Inclusion and exclusion of namespaces. For example, configure namespaces with`istio-discovery=enabled` and the label`app=helloworld`.

Note

Discovery selectors are not a security boundary. Istiod continues to have access to all namespaces even when you have configured the`discoverySelector` field.

#### 2.4.2. Scoping a Service Mesh by using discovery selectorsCopy link

If you know which namespaces to include in the Service Mesh, configure`discoverySelectors` during or after installation by adding the required selectors to the`meshConfig.discoverySelectors` section of the`Istio` resource. For example, configure Istio to discover only namespaces labeled`istio-discovery=enabled`.

Prerequisites

- The OpenShift Service Mesh operator is installed.
- An Istio CNI resource is created.

Procedure

Add a label to the namespace containing the Istio control plane, for example, the`istio-system` system namespace.

```shell-session
$ oc label namespace istio-system istio-discovery=enabled
```

Modify the`Istio` control plane resource to include a`discoverySelectors` section with the same label.

```yaml
kind: Istio
apiVersion: sailoperator.io/v1
metadata:
  name: default
spec:
  namespace: istio-system
  values:
    meshConfig:
      discoverySelectors:
        - matchLabels:
            istio-discovery: enabled
```

Apply the Istio CR:

```shell-session
$ oc apply -f istio.yaml
```

1. Ensure that all namespaces that will contain workloads that are to be part of the Service Mesh have both the`discoverySelector` label and, if needed, the appropriate Istio injection label.

Note

Discovery selectors help restrict the scope of a single Service Mesh and are essential for limiting the control plane scope when you deploy multiple Istio control planes in a single cluster.

### 2.5. About the Bookinfo applicationCopy link

Installing the`bookinfo` example application consists of two main tasks: deploying the application and creating a gateway so the application is accessible outside the cluster.

You can use the`bookinfo` application to explore service mesh features. Using the`bookinfo` application, you can easily confirm that requests from a web browser pass through the mesh and reach the application.

The`bookinfo` application displays information about a book, similar to a single catalog entry of an online book store. The application displays a page that describes the book, lists book details (ISBN, number of pages, and other information), and book reviews.

The`bookinfo` application is exposed through the mesh, and the mesh configuration determines how the microservices comprising the application are used to serve requests. The review information comes from one of three services:`reviews-v1`,`reviews-v2` or`reviews-v3`. If you deploy the`bookinfo` application without defining the`reviews` virtual service, then the mesh uses a round robin rule to route requests to a service.

By deploying the`reviews` virtual service, you can specify a different behavior. For example, you can specify that if a user logs into the`bookinfo` application, then the mesh routes requests to the`reviews-v2` service, and the application displays reviews with black stars. If a user does not log into the`bookinfo` application, then the mesh routes requests to the`reviews-v3` service, and the application displays reviews with red stars.

For more information, see [Bookinfo Application](https://istio.io/latest/docs/examples/bookinfo/) in the upstream Istio documentation.

#### 2.5.1. Deploying the Bookinfo applicationCopy link

Prerequisites

- You have deployed a cluster on OpenShift Container Platform 4.15 or later.
- You are logged in to the OpenShift Container Platform web console as a user with the`cluster-admin` role.
- You have access to the OpenShift CLI (oc).
- You have installed the Red Hat OpenShift Service Mesh Operator, created the Istio resource, and the Operator has deployed Istio.
- You have created IstioCNI resource, and the Operator has deployed the necessary IstioCNI pods.

Procedure

Enter`bookinfo` in the Project name field.

The Display name and Description fields provide supplementary information and are not required.

Apply the Istio discovery selector and injection label to the`bookinfo` namespace by entering the following command:

```shell-session
$ oc label namespace bookinfo istio-discovery=enabled istio-injection=enabled
```

Note

In this example, the name of the Istio resource is`default`. If the Istio resource name is different, you must set the`istio.io/rev` label to the name of the Istio resource instead of adding the`istio-injection=enabled` label.

Apply the`bookinfo` YAML file to deploy the`bookinfo` application by entering the following command:

```shell-session
oc apply -f https://raw.githubusercontent.com/openshift-service-mesh/istio/release-1.24/samples/bookinfo/platform/kube/bookinfo.yaml -n bookinfo
```

1. In the OpenShift Container Platform web console, navigate to the Home → Projects page.
2. Click Create Project.
3. Click Create.

Verification

Verify that the`bookinfo` service is available by running the following command:

```shell-session
$ oc get services -n bookinfo
```

Example output

```shell-session
NAME          TYPE        CLUSTER-IP      EXTERNAL-IP   PORT(S)    AGE
details       ClusterIP   172.30.137.21   <none>        9080/TCP   44s
productpage   ClusterIP   172.30.2.246    <none>        9080/TCP   43s
ratings       ClusterIP   172.30.33.85    <none>        9080/TCP   44s
reviews       ClusterIP   172.30.175.88   <none>        9080/TCP   44s
```

Verify that the`bookinfo` pods are available by running the following command:

```shell-session
$ oc get pods -n bookinfo
```

Example output

```shell-session
NAME                             READY   STATUS    RESTARTS   AGE
details-v1-698d88b-km2jg         2/2     Running   0          66s
productpage-v1-675fc69cf-cvxv9   2/2     Running   0          65s
ratings-v1-6484c4d9bb-tpx7d      2/2     Running   0          65s
reviews-v1-5b5d6494f4-wsrwp      2/2     Running   0          65s
reviews-v2-5b667bcbf8-4lsfd      2/2     Running   0          65s
reviews-v3-5b9bd44f4-44hr6       2/2     Running   0          65s
```

When the`Ready` columns displays`2/2`, the proxy sidecar was successfully injected. Confirm that`Running` appears in the`Status` column for each pod.

Verify that the`bookinfo` application is running by sending a request to the`bookinfo` page. Run the following command:

```shell-session
$ oc exec "$(oc get pod -l app=ratings -n bookinfo -o jsonpath='{.items[0].metadata.name}')" -c ratings -n bookinfo -- curl -sS productpage:9080/productpage | grep -o "<title>.*</title>"
```

#### 2.5.2. About accessing the Bookinfo application using a gatewayCopy link

The Red Hat OpenShift Service Mesh Operator does not deploy gateways. Gateways are not part of the control plane. As a security best-practice, Ingress and Egress gateways should be deployed in a different namespace than the namespace that contains the control plane.

You can deploy gateways using either the Gateway API or the gateway injection method.

#### 2.5.3. Accessing the Bookinfo application by using Istio gateway injectionCopy link

Gateway injection uses the same mechanisms as Istio sidecar injection to create a gateway from a`Deployment` resource that is paired with a`Service` resource. The`Service` resource can be made accessible from outside an OpenShift Container Platform cluster.

Prerequisites

- You are logged in to the OpenShift Container Platform web console as`cluster-admin`.
- The Red Hat OpenShift Service Mesh Operator must be installed.
- The Istio resource must be deployed.

Procedure

Create the`istio-ingressgateway` deployment and service by running the following command:

```shell-session
$ oc apply -n bookinfo -f ingress-gateway.yaml
```

Note

This example uses a sample`ingress-gateway.yaml` [file](https://raw.githubusercontent.com/istio-ecosystem/sail-operator/main/chart/samples/ingress-gateway.yaml) that is available in the Istio community repository.

Configure the`bookinfo` application to use the new gateway. Apply the gateway configuration by running the following command:

```shell-session
$ oc apply -f https://raw.githubusercontent.com/openshift-service-mesh/istio/release-1.24/samples/bookinfo/networking/bookinfo-gateway.yaml -n bookinfo
```

Note

To configure gateway injection with the`bookinfo` application, this example uses a sample gateway configuration file that must be applied in the namespace where the application is installed.

Use a route to expose the gateway external to the cluster by running the following command:

```shell-session
$ oc expose service istio-ingressgateway -n bookinfo
```

Modify the YAML file to automatically scale the pod when ingress traffic increases.

Example configuration

```yaml
apiVersion: autoscaling/v2
kind: HorizontalPodAutoscaler
metadata:
  labels:
    istio: ingressgateway
    release: istio
  name: ingressgatewayhpa
  namespace: bookinfo
spec:
  maxReplicas: 5 
```

1

```yaml

  metrics:
  - resource:
      name: cpu
      target:
        averageUtilization: 80
        type: Utilization
    type: Resource
  minReplicas: 2
  scaleTargetRef:
    apiVersion: apps/v1
    kind: Deployment
    name: istio-ingressgateway
```

1

This example sets the the maximum replicas to`5` and the minimum replicas to`2`. It also creates another replica when utilization reaches 80%.

Specify the minimum number of pods that must be running on the node.

Example configuration

```yaml
apiVersion: policy/v1
kind: PodDisruptionBudget
metadata:
  labels:
    istio: ingressgateway
    release: istio
  name: ingressgatewaypdb
  namespace: bookinfo
spec:
  minAvailable: 1 
```

1

```yaml

  selector:
    matchLabels:
      istio: ingressgateway
```

1

This example ensures one replica is running if a pod gets restarted on a new node.

Obtain the gateway host name and the URL for the product page by running the following command:

```shell-session
$ HOST=$(oc get route istio-ingressgateway -n bookinfo -o jsonpath='{.spec.host}')
```

Verify that the`productpage` is accessible from a web browser by running the following command:

```shell-session
$ echo productpage URL: http://$HOST/productpage
```

#### 2.5.4. Accessing the Bookinfo application by using Gateway APICopy link

The Kubernetes Gateway API deploys a gateway by creating a`Gateway` resource. In OpenShift Container Platform 4.15 and later, Red Hat OpenShift Service Mesh implements the Gateway API custom resource definitions (CRDs). However, in OpenShift Container Platform 4.18 and earlier, the CRDs are not installed by default. Hence, in OpenShift Container Platform 4.15 through 4.18, you must manually install the CRDs. Starting with OpenShift Container Platform 4.19, these CRDs are automatically installed and managed, and you can no longer create, update, or delete them.

For details about enabling Gateway API for Ingress in OpenShift Container Platform 4.19 and later, see "Configuring ingress cluster traffic" in the OpenShift Container Platform documentation.

Note

Red Hat provides support for using the Kubernetes Gateway API with Red Hat OpenShift Service Mesh. Red Hat does not provide support for the Kubernetes Gateway API custom resource definitions (CRDs). In this procedure, the use of community Gateway API CRDs is shown for demonstration purposes only.

Prerequisites

- You are logged in to the OpenShift Container Platform web console as`cluster-admin`.
- The Red Hat OpenShift Service Mesh Operator must be installed.
- The Istio resource must be deployed.

Procedure

Enable the Gateway API CRDs for OpenShift Container Platform 4.18 and earlier, by running the following command:

```shell-session
$ oc get crd gateways.gateway.networking.k8s.io &> /dev/null ||  { oc kustomize "github.com/kubernetes-sigs/gateway-api/config/crd?ref=v1.0.0" | oc apply -f -; }
```

Create and configure a gateway by using the`Gateway` and`HTTPRoute` resources by running the following command:

```shell-session
$ oc apply -f https://raw.githubusercontent.com/openshift-service-mesh/istio/release-1.24/samples/bookinfo/gateway-api/bookinfo-gateway.yaml -n bookinfo
```

Note

To configure a gateway with the`bookinfo` application by using the Gateway API, this example uses a sample gateway configuration file that must be applied in the namespace where the application is installed.

Ensure that the Gateway API service is ready, and has an address allocated by running the following command:

```shell-session
$ oc wait --for=condition=programmed gtw bookinfo-gateway -n bookinfo
```

Retrieve the host by running the following command:

```shell-session
$ export INGRESS_HOST=$(oc get gtw bookinfo-gateway -n bookinfo -o jsonpath='{.status.addresses[0].value}')
```

Retrieve the port by running the following command:

```shell-session
$ export INGRESS_PORT=$(oc get gtw bookinfo-gateway -n bookinfo -o jsonpath='{.spec.listeners[?(@.name=="http")].port}')
```

Retrieve the gateway URL by running the following command:

```shell-session
$ export GATEWAY_URL=$INGRESS_HOST:$INGRESS_PORT
```

Obtain the gateway host name and the URL of the product page by running the following command:

```shell-session
$ echo "http://${GATEWAY_URL}/productpage"
```

Verification

- Verify that the productpage is accessible from a web browser.

### 2.6. Customizing Istio configurationCopy link

The`values` field of the`Istio` custom resource definition, which was created when the control plane was deployed, can be used to customize Istio configuration using Istio’s`Helm` configuration values. When you create this resource using the OpenShift Container Platform web console, it is pre-populated with configuration settings to enable Istio to run on OpenShift.

Procedure

1. Click Operators → Installed Operators.
2. Click Istio in the Provided APIs column.
3. Click the`Istio` instance, named`default`, in the Name column.
4. Click YAML to view the`Istio` configuration and make modifications.

For a list of available configuration for the`values` field, refer to [Istio’s artifacthub chart documentation](https://artifacthub.io/packages/search?org=istio&amp;sort=relevance&amp;page=1).

- [Base parameters](https://artifacthub.io/packages/helm/istio-official/base?modal=values)
- [Istiod parameters](https://artifacthub.io/packages/helm/istio-official/istiod?modal=values)
- [Gateway parameters](https://artifacthub.io/packages/helm/istio-official/gateway?modal=values)
- [CNI parameters](https://artifacthub.io/packages/helm/istio-official/cni?modal=values)
- [ZTunnel parameters](https://artifacthub.io/packages/helm/istio-official/ztunnel?modal=values)

### 2.7. About Istio High AvailabilityCopy link

Running the Istio control plane in High Availability (HA) mode prevents single points of failure, and ensures continuous mesh operation even if an`istiod` pod fails. By using HA, if one`istiod` pod becomes unavailable, another one continues to manage and configure the Istio data plane, preventing service outages or disruptions. HA provides scalability by distributing the control plane workload, enables graceful upgrades, supports disaster recovery operations, and protects against zone-wide mesh outages.

There are two ways for a system administrator to configure HA for the Istio deployment:

- Defining a static replica count: This approach involves setting a fixed number of`istiod` pods, providing a consistent level of redundancy.
- Using autoscaling: This approach dynamically adjusts the number of`istiod` pods based on resource utilization or custom metrics, providing more efficient resource consumption for fluctuating workloads.

#### 2.7.1. Configuring Istio HA by using autoscalingCopy link

Configure the Istio control plane in High Availability (HA) mode to prevent a single point of failure, and ensure continuous mesh operation even if one of the`istiod` pods fails. Autoscaling defines the minimum and maximum number of Istio control plane pods that can operate. OpenShift Container Platform uses these values to scale the number of control planes in operation based on resource utilization, such as CPU or memory, to efficiently respond to the varying number of workloads and overall traffic patterns within the mesh.

Prerequisites

- You are logged in to the OpenShift Container Platform web console as a user with the`cluster-admin` role.
- You have installed the Red Hat OpenShift Service Mesh Operator.
- You have deployed the Istio resource.

Procedure

Modify the`Istio` custom resource (CR) similar to the following example:

Example configuration

```yaml
apiVersion: sailoperator.io/v1
kind: Istio
metadata:
  name: default
spec:
  namespace: istio-system
  values:
    pilot:
      autoscaleMin: 2 
```

1

```yaml

      autoscaleMax: 5 
```

2

```yaml

      cpu:
        targetAverageUtilization: 80 
```

3

```yaml

      memory:
        targetAverageUtilization: 80 
```

4

1

Specifies the minimum number of Istio control plane replicas that always run.

2

Specifies the maximum number of Istio control plane replicas, allowing for scaling based on load. To support HA, there must be at least two replicas.

3

Specifies the target CPU utilization for autoscaling to 80%. If the average CPU usage exceeds this threshold, the Horizontal Pod Autoscaler (HPA) automatically increases the number of replicas.

4

Specifies the target memory utilization for autoscaling to 80%. If the average memory usage exceeds this threshold, the HPA automatically increases the number of replicas.

1. In the OpenShift Container Platform web console, click Installed Operators.
2. Click Red Hat OpenShift Service Mesh 3 Operator.
3. Click Istio.
4. Click the name of the Istio installation. For example,`default`.
5. Click YAML.

Verification

Verify the status of the Istio control pods by running the following command:

```shell-session
$ oc get pods -n istio-system -l app=istiod
```

Example output

```shell-session
NAME                      READY   STATUS    RESTARTS   AGE
istiod-7c7b6564c9-nwhsg   1/1     Running   0          70s
istiod-7c7b6564c9-xkmsl   1/1     Running   0          85s
```

Two`istiod` pods are running. Two pods, the minimum requirement for an HA Istio control plane, indicates that a basic HA setup is in place.

##### 2.7.1.1. API settings for Service Mesh HA autoscaling modeCopy link

Use the following`istio` custom resource definition (CRD) parameters when you configure a service mesh for High Availability (HA) by using autoscaling.

Expand

`autoScaleMin`

Defines the minimum number of`istiod` pods for an istio deployment. Each pod contains one instance of the Istio control plane.

OpenShift only uses this parameter when the Horizontal Pod Autoscaler (HPA) is enabled for the Istio deployment. This is the default behavior.

`autoScaleMax`

Defines the maximum number of`istiod` pods for an Istio deployment. Each pod contains one instance of the Istio control plane.

For OpenShift to automatically scale the number of`istiod` pods based on load, you must set this parameter to a value that is greater than the value that you defined for the`autoScaleMin` parameter.

You must also configure metrics for autoscaling to work properly. If no metrics are configured, the autoscaler does not scale up or down.

OpenShift only uses this parameter when Horizontal Pod Autoscaler (HPA) is enabled for the Istio deployment. This is the default behavior.

`cpu.targetAverageUtilization`

Defines the target CPU utilization for the`istiod` pod. If the average CPU usage exceeds the threshold that this parameter defines, the HPA automatically increases the number of replica pods.

`memory.targetAverageUtilization`

Defines the target memory utilization for the`istiod` pod. If the average memory usage exceeds the threshold that this parameter defines, the HPA automatically increases the number of replica pods.

`behavior`

You can use the`behavior` field to define additional policies that OpenShift uses to scale Istio resources up or down.

For more information, see [Configurable Scaling Behavior](https://kubernetes.io/docs/tasks/run-application/horizontal-pod-autoscale/#configurable-scaling-behavior).

| Table 2.1. HA API parameters | Parameter | Description |
| --- | --- | --- |

Show more

#### 2.7.2. Configuring Istio HA by using replica countCopy link

Configure the Istio control plane in High Availability (HA) mode to prevent a single point of failure, and ensure continuous mesh operation even if one of the`istiod` pods fails. The replica count defines a fixed number of Istio control plane pods that can operate. Use replica count for mesh environments where the control plane workload is relatively stable or predictable, or when you prefer to manually scale the`istiod` pod.

Prerequisites

- You are logged in to the OpenShift Container Platform web console as a user with the`cluster-admin` role.
- You have installed the Red Hat OpenShift Service Mesh Operator.
- You have deployed the Istio resource.

Procedure

Obtain the name of the`Istio` resource by running the following command:

```shell-session
$ oc get istio -n istio-sytem
```

Example output

```shell-session
NAME      REVISIONS   READY   IN USE   ACTIVE REVISION   STATUS    VERSION   AGE
default   1           1       0        default           Healthy   v1.24.6   24m
```

The name of the`Istio` resource is`default`.

Update the`Istio` custom resource (CR) by adding the`autoscaleEnabled` and`replicaCount` parameters by running the following command:

```shell-session
$ oc patch istio default -n istio-system --type merge -p '
spec:
  values:
    pilot:
      autoscaleEnabled: false 
```

1

```shell-session

      replicaCount: 2 
```

2

```shell-session

'
```

1

Specifies a setting that disables autoscaling and ensures that the number of replicas remains fixed.

2

Specifies the number of Istio control plane replicas. To support HA, there must be at least two replicas.

Verification

Verify the status of the Istio control pods by running the following command:

```shell-session
$ oc get pods -n istio-system -l app=istiod
```

Example output

```shell-session
NAME                      READY   STATUS    RESTARTS   AGE
istiod-7c7b6564c9-nwhsg   1/1     Running   0          70s
istiod-7c7b6564c9-xkmsl   1/1     Running   0          85s
```

Two`istiod` pods are running, which is the minimum requirement for an HA Istio control plane and indicates that a basic HA setup is in place.

## Chapter 3. Sidecar injectionCopy link

Sidecar proxies are deployed into each application pod to intercept network traffic and enable service mesh features like security, observability, and traffic management.

### 3.1. About sidecar injectionCopy link

Sidecar injection is enabled using labels at the namespace or pod level. These labels also indicate the specific control plane managing the proxy. When you apply a valid injection label to the pod template defined in a deployment, any new pods created by that deployment automatically receive a sidecar. Similarly, applying a pod injection label at the namespace level ensures any new pods in that namespace include a sidecar.

Note

Injection happens at pod creation through an admission controller, so changes appear on individual pods rather than the deployment resources. To confirm sidecar injection, check the pod details directly using`oc describe`, where you can see the injected Istio proxy container.

### 3.2. Identifying the revision nameCopy link

The label required to enable sidecar injection is determined by the specific control plane instance, known as a revision. Each revision is managed by an`IstioRevision` resource, which is automatically created and managed by the`Istio` resource, so manual creation or modification of`IstioRevision` resources is generally unnecessary.

The naming of an`IstioRevision` depends on the`spec.updateStrategy.type` setting in the`Istio` resource. If set to`InPlace`, the revision shares the`Istio` resource name. If set to`RevisionBased`, the revision name follows the format` -v `. Typically, each`Istio` resource corresponds to a single`IstioRevision`. However, during a revision-based upgrade, multiple`IstioRevision` resources may exist, each representing a distinct control plane instance.

To see available revision names, use the following command:

```shell-session
$ oc get istiorevisions
```

You should see output similar to the following example:

Example output

```shell-session
NAME              READY   STATUS    IN USE   VERSION   AGE
my-mesh-v1-23-0   True    Healthy   False    v1.23.0   114s
```

#### 3.2.1. Enabling sidecar injection with default revisionCopy link

When the service mesh’s`IstioRevision` name is`default`, it’s possible to use the following labels on a namespace or a pod to enable sidecar injection:

Expand

Namespace

`istio-injection`

`enabled`

`disabled`

Pod

`sidecar.istio.io/inject`

`true`

`false`

| | Resource | Label | Enabled value | Disabled value |
| --- | --- | --- | --- | --- |

Show more

Note

You can also enable injection by setting the`istio.io/rev: default` label in the namespace or pod.

#### 3.2.2. Enabling sidecar injection with other revisionsCopy link

When the`IstioRevision` name is not`default`, use the specific`IstioRevision` name with the`istio.io/rev` label to map the pod to the desired control plane and enable sidecar injection. To enable injection, set the`istio.io/rev: default` label in either the namespace or the pod, as adding it to both is not required.

For example, with the revision shown above, the following labels would enable sidecar injection:

Expand

Namespace

`istio.io/rev=my-mesh-v1-23-0`

`istio-injection=disabled`

Pod

`istio.io/rev=my-mesh-v1-23-0`

`sidecar.istio.io/inject="false"`

| | Resource | Enabled label | Disabled label |
| --- | --- | --- | --- |

Show more

Note

When both`istio-injection` and`istio.io/rev` labels are applied, the`istio-injection` label takes precedence and treats the namespace as part of the default revision.

### 3.3. Enabling sidecar injectionCopy link

To demonstrate different approaches for configuring sidecar injection, the following procedures use the Bookinfo application.

Prerequisites

- You have installed the Red Hat OpenShift Service Mesh Operator, created an`Istio` resource, and the Operator has deployed Istio.
- You have created the`IstioCNI` resource, and the Operator has deployed the necessary`IstioCNI` pods.
- You have created the namespaces that are to be part of the mesh, and they are discoverable by the Istio control plane.
- Optional: You have deployed the workloads to be included in the mesh. In the following examples, the Bookinfo has been deployed to the`bookinfo` namespace, but sidecar injection (step 5) has not been configured. For more information, see "Deploying the Bookinfo application".

#### 3.3.1. Enabling sidecar injection with namespace labelsCopy link

In this example, all workloads within a namespace receive a sidecar proxy injection, making it the best approach when the majority of workloads in the namespace should be included in the mesh.

Procedure

Verify the revision name of the Istio control plane using the following command:

```shell-session
$ oc get istiorevisions
```

You should see output similar to the following example:

Example output

```shell-session
NAME      TYPE    READY   STATUS    IN USE   VERSION   AGE
default   Local   True    Healthy   False    v1.23.0   4m57s
```

Since the revision name is default, you can use the default injection labels without referencing the exact revision name.

Verify that workloads already running in the desired namespace show`1/1` containers as`READY` by using the following command. This confirms that the pods are running without sidecars.

```shell-session
$ oc get pods -n bookinfo
```

You should see output similar to the following example:

Example output

```shell-session
NAME                             READY   STATUS    RESTARTS   AGE
details-v1-65cfcf56f9-gm6v7      1/1     Running   0          4m55s
productpage-v1-d5789fdfb-8x6bk   1/1     Running   0          4m53s
ratings-v1-7c9bd4b87f-6v7hg      1/1     Running   0          4m55s
reviews-v1-6584ddcf65-6wqtw      1/1     Running   0          4m54s
reviews-v2-6f85cb9b7c-w9l8s      1/1     Running   0          4m54s
reviews-v3-6f5b775685-mg5n6      1/1     Running   0          4m54s
```

To apply the injection label to the`bookinfo` namespace, run the following command at the CLI:

```shell-session
$ oc label namespace bookinfo istio-injection=enabled
namespace/bookinfo labeled
```

To ensure sidecar injection is applied, redeploy the existing workloads in the`bookinfo` namespace. Use the following command to perform a rolling update of all workloads:

```shell-session
$ oc -n bookinfo rollout restart deployments
```

Verification

Verify the rollout by checking that the new pods display`2/2` containers as`READY`, confirming successful sidecar injection by running the following command:

```shell-session
$ oc get pods -n bookinfo
```

You should see output similar to the following example:

Example output

```shell-session
NAME                              READY   STATUS    RESTARTS   AGE
details-v1-7745f84ff-bpf8f        2/2     Running   0          55s
productpage-v1-54f48db985-gd5q9   2/2     Running   0          55s
ratings-v1-5d645c985f-xsw7p       2/2     Running   0          55s
reviews-v1-bd5f54b8c-zns4v        2/2     Running   0          55s
reviews-v2-5d7b9dbf97-wbpjr       2/2     Running   0          55s
reviews-v3-5fccc48c8c-bjktn       2/2     Running   0          55s
```

#### 3.3.2. Exclude a workload from the meshCopy link

You can exclude specific workloads from sidecar injection within a namespace where injection is enabled for all workloads.

Note

This example is for demonstration purposes only. The bookinfo application requires all workloads to be part of the mesh for proper functionality.

Procedure

Modify the`spec.template.metadata.labels` section of your`Deployment` resource to include the label`sidecar.istio.io/inject: false` to disable sidecar injection.

```yaml
kind: Deployment
apiVersion: apps/v1
metadata:
name: ratings-v1
namespace: bookinfo
labels:
  app: ratings
  version: v1
spec:
  template:
    metadata:
      labels:
        sidecar.istio.io/inject: 'false'
```

Note

Adding the label to the top-level`labels` section of the`Deployment` does not affect sidecar injection.

Updating the deployment triggers a rollout, creating a new ReplicaSet with updated pod(s).

1. Open the application’s`Deployment` resource in an editor. In this case, exclude the`ratings-v1` service.

Verification

Verify that the updated pod(s) do not contain a sidecar container and show`1/1` containers as`Running` by running the following command:

```shell-session
$ oc get pods -n bookinfo
```

You should see output similar to the following example:

Example output

```shell-session
NAME                              READY   STATUS    RESTARTS   AGE
details-v1-6bc7b69776-7f6wz       2/2     Running   0          29m
productpage-v1-54f48db985-gd5q9   2/2     Running   0          29m
ratings-v1-5d645c985f-xsw7p       1/1     Running   0          7s
reviews-v1-bd5f54b8c-zns4v        2/2     Running   0          29m
reviews-v2-5d7b9dbf97-wbpjr       2/2     Running   0          29m
reviews-v3-5fccc48c8c-bjktn       2/2     Running   0          29m
```

#### 3.3.3. Enabling sidecar injection with pod labelsCopy link

This approach allows you to include individual workloads for sidecar injection instead of applying it to all workloads within a namespace, making it ideal for scenarios where only a few workloads need to be part of a service mesh. This example also demonstrates the use of a revision label for sidecar injection, where the`Istio` resource is created with the name`my-mesh`. A unique`Istio` resource name is required when multiple Istio control planes are present in the same cluster or during a revision-based control plane upgrade.

Procedure

Verify the revision name of the Istio control plane by running the following command:

```shell-session
$ oc get istiorevisions
```

You should see output similar to the following example:

Example output

```shell-session
NAME      TYPE    READY   STATUS    IN USE   VERSION   AGE
my-mesh   Local   True    Healthy   False    v1.23.0   47s
```

Since the revision name is`my-mesh`, use the revision label`istio.io/rev=my-mesh` to enable sidecar injection.

Verify that workloads already running show`1/1` containers as`READY`, indicating that the pods are running without sidecars by running the following command:

```shell-session
$ oc get pods -n bookinfo
```

You should see output similar to the following example:

Example output

```shell-session
NAME                             READY   STATUS    RESTARTS   AGE
details-v1-65cfcf56f9-gm6v7      1/1     Running   0          4m55s
productpage-v1-d5789fdfb-8x6bk   1/1     Running   0          4m53s
ratings-v1-7c9bd4b87f-6v7hg      1/1     Running   0          4m55s
reviews-v1-6584ddcf65-6wqtw      1/1     Running   0          4m54s
reviews-v2-6f85cb9b7c-w9l8s      1/1     Running   0          4m54s
reviews-v3-6f5b775685-mg5n6      1/1     Running   0          4m54s
```

Update the`spec.template.metadata.labels` section of your`Deployment` to include the appropriate pod injection or revision label. In this case,`istio.io/rev: my-mesh`:

```yaml
kind: Deployment
apiVersion: apps/v1
metadata:
name: ratings-v1
namespace: bookinfo
labels:
  app: ratings
  version: v1
spec:
  template:
    metadata:
      labels:
        istio.io/rev: my-mesh
```

Note

Adding the label to the top-level`labels` section of the`Deployment` resource does not impact sidecar injection.

Updating the deployment triggers a rollout, creating a new ReplicaSet with the updated pod(s).

1. Open the application’s`Deployment` resource in an editor. In this case, update the`ratings-v1` service.

Verification

Verify that only the ratings-v1 pod now shows`2/2` containers`READY`, indicating that the sidecar has been successfully injected by running the following command:

```shell-session
$ oc get pods -n bookinfo
```

You should see output similar to the following example:

Example output

```shell-session
NAME                              READY   STATUS    RESTARTS   AGE
details-v1-559cd49f6c-b89hw       1/1     Running   0          42m
productpage-v1-5f48cdcb85-8ppz5   1/1     Running   0          42m
ratings-v1-848bf79888-krdch       2/2     Running   0          9s
reviews-v1-6b7444ffbd-7m5wp       1/1     Running   0          42m
reviews-v2-67876d7b7-9nmw5        1/1     Running   0          42m
reviews-v3-84b55b667c-x5t8s       1/1     Running   0          42m
```

1. Repeat for other workloads that you want to include in the mesh.

### 3.4. Enabling sidecar injection with namespace labels and an IstioRevisionTag resourceCopy link

To use the`istio-injection=enabled` label when your revision name is not`default`, you must create an`IstioRevisionTag` resource with the name`default` that references your`Istio` resource.

Prerequisites

- You have installed the Red Hat OpenShift Service Mesh Operator, created an`Istio` resource, and the Operator has deployed Istio.
- You have created the`IstioCNI` resource, and the Operator has deployed the necessary`IstioCNI` pods.
- You have created the namespaces that are to be part of the mesh, and they are discoverable by the Istio control plane.
- Optional: You have deployed the workloads to be included in the mesh. In the following examples, the Bookinfo has been deployed to the`bookinfo` namespace, but sidecar injection (step 5 in "Deploying the Bookinfo application" procedure) has not been configured. For more information, see "Deploying the Bookinfo application".

Procedure

Find the name of your`Istio` resource by running the following command:

```shell-session
$ oc get istio
```

Example output

```shell-session
NAME      REVISIONS   READY   IN USE   ACTIVE REVISION   STATUS    VERSION   AGE
default   1           1       1        default-v1-24-3   Healthy   v1.24.3   11s
```

In this example, the`Istio` resource has the name`default`, but the underlying revision is called`default-v1-24-3`.

Create the`IstioRevisionTag` resource in a YAML file:

Example`IstioRevistionTag` resource YAML file

```yaml
apiVersion: sailoperator.io/v1
kind: IstioRevisionTag
metadata:
  name: default
spec:
  targetRef:
    kind: Istio
    name: default
```

Apply the`IstioRevisionTag` resource by running the following command:

```shell-session
$ oc apply -f istioRevisionTag.yaml
```

Verify that the`IstioRevisionTag` resource has been created successfully by running the following command:

```shell-session
$ oc get istiorevisiontags.sailoperator.io
```

Example output

```shell-session
NAME      STATUS    IN USE   REVISION          AGE
default   Healthy   True     default-v1-24-3   4m23s
```

In this example, the new tag is referencing your active revision,`default-v1-24-3`. Now you can use the`istio-injection=enabled` label as if your revision was called`default`.

Confirm that the pods are running without sidecars by running the following command. Any workloads that are already running in the desired namespace should show`1/1` containers in the`READY` column.

```shell-session
$ oc get pods -n bookinfo
```

Example output

```shell-session
NAME                             READY   STATUS    RESTARTS   AGE
details-v1-65cfcf56f9-gm6v7      1/1     Running   0          4m55s
productpage-v1-d5789fdfb-8x6bk   1/1     Running   0          4m53s
ratings-v1-7c9bd4b87f-6v7hg      1/1     Running   0          4m55s
reviews-v1-6584ddcf65-6wqtw      1/1     Running   0          4m54s
reviews-v2-6f85cb9b7c-w9l8s      1/1     Running   0          4m54s
reviews-v3-6f5b775685-mg5n6      1/1     Running   0          4m54s
```

Apply the injection label to the`bookinfo` namespace by running the following command:

```shell-session
$ oc label namespace bookinfo istio-injection=enabled \
namespace/bookinfo labeled
```

To ensure sidecar injection is applied, redeploy the workloads in the`bookinfo` namespace by running the following command:

```shell-session
$ oc -n bookinfo rollout restart deployments
```

Verification

Verify the rollout by running the following command and confirming that the new pods display`2/2` containers in the`READY` column:

```shell-session
$ oc get pods -n bookinfo
```

Example output

```shell-session
NAME                              READY   STATUS    RESTARTS   AGE
details-v1-7745f84ff-bpf8f        2/2     Running   0          55s
productpage-v1-54f48db985-gd5q9   2/2     Running   0          55s
ratings-v1-5d645c985f-xsw7p       2/2     Running   0          55s
reviews-v1-bd5f54b8c-zns4v        2/2     Running   0          55s
reviews-v2-5d7b9dbf97-wbpjr       2/2     Running   0          55s
reviews-v3-5fccc48c8c-bjktn       2/2     Running   0          55s
```

## Chapter 4. Istio ambient modeCopy link

Istio ambient mode provides a sidecar-less architecture for Red Hat OpenShift Service Mesh that reduces operational complexity and resource overhead by using node-level Layer 4 (L4) proxies and optional Layer 7 proxies.

### 4.1. About Istio ambient modeCopy link

To understand the Istio ambient mode architecture, see the following definitions:

ZTunnel proxy A per-node proxy that manages secure, transparent Transmission Control Protocol (TCP) connections for all workloads on the node. It operates at Layer 4 (L4), offloading mutual Transport Layer Security (mTLS) and L4 policy enforcement from application pods. Waypoint proxy An optional proxy that runs per service account or namespace to provide advanced Layer 7 (L7) features such as traffic management, policy enforcement, and observability. You can apply L7 features selectively to avoid the overhead of sidecars for every service. Istio CNI plugin Redirects traffic to the Ztunnel proxy on each node, enabling transparent interception without requiring modifications to application pods.

Istio ambient mode offers the following benefits:

Incremental adoption that enables workloads to join the mesh with the L4 features like mutual Transport Layer Security (mTLS) and basic policies with optional`waypoint` proxies added later to use L7 service mesh features, such as HTTP(L7) traffic management.

Note

The L7 features require deploying`waypoint` proxies, which introduces minimal additional overhead for the selected services.

- Simplified operations that remove the need to manage sidecar injection, reducing the complexity of mesh adoption and operations.
- Reduced resource consumption with a per-node Ztunnel proxy that provides L4 service mesh features and an optional`waypoint` proxy that reduces resource overhead per pod.
- Enhanced security that provides a secure, zero-trust network foundation with mTLS by default for all meshed workloads.

Note

Ambient mode is a newer architecture and may involve different operational considerations than traditional sidecar models.

While well-defined discovery selectors allow a service mesh deployed in ambient mode alongside a mesh in sidecar mode, this scenario has not been thoroughly validated. To avoid potential conflicts, install Istio ambient mode only on clusters that do not have an existing Red Hat OpenShift Service Mesh installation. Ambient mode remains a Technology Preview feature.

Important

Istio ambient mode is not compatible with clusters that use Red Hat OpenShift Service Mesh 2.6 or earlier. You must not install or use them together.

### 4.2. Installing Istio ambient modeCopy link

You can install Istio ambient mode on OpenShift Container Platform 4.19 or later and Red Hat OpenShift Service Mesh 3.1.0 or later with the required Gateway API custom resource definitions (CRDs).

Prerequisites

- You have deployed a cluster on OpenShift Container Platform 4.19 or later.
- You have installed the OpenShift Service Mesh Operator 3.1.0 or later in the OpenShift Container Platform cluster.
- You are logged in to the OpenShift Container Platform cluster either through the web console as a user with the`cluster-admin` role, or with the`oc login` command, depending on the installation method.
- You have configured the OVN-Kubernetes Container Network Interface (CNI) to use local gateway mode by setting the`routingViaHost` field as`true` in the`gatewayConfig` specification for the Cluster Network Operator. For more information, see "Configuring gateway mode".

Procedure

Install the Istio control plane:

Create the`istio-system` namespace by running the following command:

```shell-session
$ oc create namespace istio-system
```

Create an`Istio` resource named`istio.yaml` similar to the following example:

Example configuration

```yaml
apiVersion: sailoperator.io/v1
kind: Istio
metadata:
  name: default
spec:
  namespace: istio-system
  profile: ambient
  values:
    pilot:
      trustedZtunnelNamespace: ztunnel
```

Important

You must set the`profile` field to`ambient`, and configure the`.spec.values.pilot.trustedZtunnelNamespace` value to match the namespace where the`ZTunnel` resource will be installed.

Apply the`Istio` custom resource (CR) by running the following command:

```shell-session
$ oc apply -f istio.yaml
```

Wait for the Istio control plane to contain the`Ready` status condition by running the following command:

```shell-session
$ oc wait --for=condition=Ready istios/default --timeout=3m
```

Install the Istio Container Network Interface (CNI):

Create the`istio-cni` namespace by running the following command:

```shell-session
$ oc create namespace istio-cni
```

Create the`IstioCNI` resource named`istio-cni.yaml` similar to the following example:

Example configuration

```yaml
apiVersion: sailoperator.io/v1
kind: IstioCNI
metadata:
  name: default
spec:
  namespace: istio-cni
  profile: ambient
```

Set the`profile` field to`ambient`.

Apply the`IstioCNI` CR by running the following command:

```shell-session
$ oc apply -f istio-cni.yaml
```

Wait for the`IstioCNI` pods to contain the`Ready` status condition by running the following command:

```shell-session
$ oc wait --for=condition=Ready istios/default --timeout=3m
```

Install the Ztunnel proxy:

Create the`ztunnel` namespace for Ztunnel proxy by running the following command:

```shell-session
$ oc create namespace ztunnel
```

The namespace name for`ztunnel` project must match the`trustedZtunnelNamespace` parameter in Istio configuration.

Create the`Ztunnel` resource named`ztunnel.yaml` similar to the following example:

Example configuration

```yaml
apiVersion: sailoperator.io/v1alpha1
kind: ZTunnel
metadata:
  name: default
spec:
  namespace: ztunnel
  profile: ambient
```

Apply the`Ztunnel` CR by running the following command:

```shell-session
$ oc apply -f ztunnel.yaml
```

Wait for the`Ztunnel` pods to contain the`Ready` status condition by running the following command:

```shell-session
$ oc wait --for=condition=Ready ztunnel/default --timeout=3m
```

### 4.3. About discovery selectors and Istio ambient modeCopy link

Istio ambient mode includes workloads when the control plane discovers each workload and the appropriate label enables traffic redirection through the Ztunnel proxy. By default, the control plane discovers workloads in all namespaces across the cluster. As a result, each proxy receives configuration for every namespace, including workloads that are not enrolled in the mesh. In shared or multi-tenant clusters, limiting mesh participation to specific namespaces helps reduce configuration overhead and supports multiple service meshes within the same cluster.

For more information on discovery selectors, see "Scoping the Service Mesh with discovery selectors".

#### 4.3.1. Scoping the Service Mesh with discovery selectors in Istio ambient modeCopy link

To limit the scope of the OpenShift Service Mesh in Istio ambient mode, you can configure`discoverySelectors` parameter in the`meshConfig` section of the`Istio` resource. The configuration controls which namespaces the control plane discovers based on label selectors.

Prerequisites

- You have deployed a cluster on OpenShift Container Platform 4.19 or later.
- You have created an`Istio` control plane resource.
- You have created an`IstioCNI` resource.
- You have created a`Ztunnel` resource.

Procedure

Add a label to the namespace containing the`Istio` control plane resource, for example, the`istio-system` namespace, by running the following command:

```shell-session
$ oc label namespace istio-system istio-discovery=enabled
```

Add a label to the namespace containing the`IstioCNI` resource, for example, the`istio-cni` namespace, by running the following command:

```shell-session
$ oc label namespace istio-cni istio-discovery=enabled
```

Add a label to the namespace containing the`Ztunnel` resource, for example, the`ztunnel` namespace, by running the following command:

```shell-session
$ oc label namespace ztunnel istio-discovery=enabled
```

Modify the`Istio` control plane resource to include a`discoverySelectors` section with the same label:

Create a YAML file with the name`istio-discovery-selectors.yaml` similar to the following example:

Example configuration

```yaml
apiVersion: sailoperator.io/v1
kind: Istio
metadata:
  name: default
spec:
  namespace: istio-system
  values:
    pilot:
      trustedZtunnelNamespace: ztunnel
    profile: ambient
    meshConfig:
      discoverySelectors:
      - matchLabels:
          istio-discovery: enabled
```

Apply the YAML file to`Istio` control plane resource by running the following command:

```shell-session
$ oc apply -f istio-discovery-selectors.yaml
```

### 4.4. Deploying the Bookinfo application in Istio ambient modeCopy link

You can deploy the`bookinfo` sample application in Istio ambient mode without sidecar injection by using the`ZTunnel` proxy. For more information on`bookinfo` application, see "About the Bookinfo application".

Prerequisites

- You have deployed a cluster on OpenShift Container Platform 4.15 or later, which includes the supported Kubernetes Gateway API custom resource definitions (CRDs) required for Istio ambient mode.
- You are logged in to the OpenShift Container Platform cluster either through the web console as a user with the`cluster-admin` role, or with the`oc login` command, depending on the installation method.
- You have installed the Red Hat OpenShift Service Mesh Operator, created the Istio resource, and the Operator has deployed Istio.
- You have created an`IstioCNI` resource, and the Operator has deployed the necessary`IstioCNI` pods.
- You have created a`Ztunnel` resource, and the Operator has deployed the necessary`Ztunnel` pods.

Procedure

Create the`bookinfo` namespace by running the following command:

```shell-session
$ oc create namespace bookinfo
```

Add the`istio-discovery=enabled` label to the`bookinfo` namespace by running the following command:

```shell-session
$ oc label namespace bookinfo istio-discovery=enabled
```

Apply the`bookinfo` YAML file to deploy the`bookinfo` application by running the following command:

```shell-session
$ oc apply -n bookinfo -f https://raw.githubusercontent.com/openshift-service-mesh/istio/release-1.26/samples/bookinfo/platform/kube/bookinfo.yaml
```

Apply the`bookinfo-versions` YAML file to deploy the`bookinfo` application by running the following command:

```shell-session
$ oc apply -n bookinfo -f https://raw.githubusercontent.com/openshift-service-mesh/istio/release-1.26/samples/bookinfo/platform/kube/bookinfo-versions.yaml
```

Verify that the`bookinfo` pods are running by entering the following command:

```shell-session
$ oc -n bookinfo get pods
```

Example output

```shell-session
NAME                             READY   STATUS    RESTARTS   AGE
details-v1-54ffdd5947-8gk5h      1/1     Running   0          5m9s
productpage-v1-d49bb79b4-cb9sl   1/1     Running   0          5m3s
ratings-v1-856f65bcff-h6kkf      1/1     Running   0          5m7s
reviews-v1-848b8749df-wl5br      1/1     Running   0          5m6s
reviews-v2-5fdf9886c7-8xprg      1/1     Running   0          5m5s
reviews-v3-bb6b8ddc7-bvcm5       1/1     Running   0          5m5s
```

Verify that the`bookinfo` application is running by entering the following command:

```shell-session
$ oc exec "$(oc get pod -l app=ratings -n bookinfo \
  -o jsonpath='{.items[0].metadata.name}')" \
  -c ratings -n bookinfo \
  -- curl -sS productpage:9080/productpage | grep -o "<title>.*</title>"
```

Add the bookinfo application to the Istio ambient mesh by labeling either the entire namespace or the individual pods:

To include all workloads in the bookinfo namespace, apply the`istio.io/dataplane-mode=ambient` label to the`bookinfo` namespace, by running the following command:

```shell-session
$ oc label namespace bookinfo istio.io/dataplane-mode=ambient
```

1. To include only specific workloads, apply the`istio.io/dataplane-mode=ambient` label directly to individual pods. See the "Additional resources" section for more details on the labels used to add or exclude workloads in a mesh.

Note

Adding workloads to the ambient mesh does not require restarting or redeploying application pods. Unlike sidecar mode, the number of containers in each pod remains unchanged.

Confirm that Ztunnel proxy has successfully opened listening sockets in the pod network namespace by running the following command:

```shell-session
$ istioctl ztunnel-config workloads --namespace ztunnel
```

Example output

```shell-session
NAMESPACE    POD NAME                       ADDRESS      NODE                        WAYPOINT PROTOCOL
bookinfo     details-v1-54ffdd5947-cflng    10.131.0.69  ip-10-0-47-239.ec2.internal None     HBONE
bookinfo     productpage-v1-d49bb79b4-8sgwx 10.128.2.80  ip-10-0-24-198.ec2.internal None     HBONE
bookinfo     ratings-v1-856f65bcff-c6ldn    10.131.0.70  ip-10-0-47-239.ec2.internal None     HBONE
bookinfo     reviews-v1-848b8749df-45hfd    10.131.0.72  ip-10-0-47-239.ec2.internal None     HBONE
bookinfo     reviews-v2-5fdf9886c7-mvwft    10.128.2.78  ip-10-0-24-198.ec2.internal None     HBONE
bookinfo     reviews-v3-bb6b8ddc7-fl8q2     10.128.2.79  ip-10-0-24-198.ec2.internal None     HBONE
istio-cni    istio-cni-node-7hwd2           10.0.61.108  ip-10-0-61-108.ec2.internal None     TCP
istio-cni    istio-cni-node-bfqmb           10.0.30.129  ip-10-0-30-129.ec2.internal None     TCP
istio-cni    istio-cni-node-cv8cw           10.0.75.71   ip-10-0-75-71.ec2.internal  None     TCP
istio-cni    istio-cni-node-hj9cz           10.0.47.239  ip-10-0-47-239.ec2.internal None     TCP
istio-cni    istio-cni-node-p8wrg           10.0.24.198  ip-10-0-24-198.ec2.internal None     TCP
istio-system istiod-6bd6b8664b-r74js        10.131.0.80  ip-10-0-47-239.ec2.internal None     TCP
ztunnel      ztunnel-2w5mj                  10.128.2.61  ip-10-0-24-198.ec2.internal None     TCP
ztunnel      ztunnel-6njq8                  10.129.0.131 ip-10-0-75-71.ec2.internal  None     TCP
ztunnel      ztunnel-96j7k                  10.130.0.146 ip-10-0-61-108.ec2.internal None     TCP
ztunnel      ztunnel-98mrk                  10.131.0.50  ip-10-0-47-239.ec2.internal None     TCP
ztunnel      ztunnel-jqcxn                  10.128.0.98  ip-10-0-30-129.ec2.internal None     TCP
```

### 4.5. About waypoint proxies in Istio ambient modeCopy link

After setting up Istio ambient mode with ztunnel proxies, you can add waypoint proxies to enable advanced Layer 7 (L7) processing features that Istio provides.

Istio ambient mode separates the functionality of Istio into two layers:

- A secure Layer 4 (L4) overlay managed by ztunnel proxies
- An L7 layer managed by optional waypoint proxies

A waypoint proxy is an Envoy-based proxy that performs L7 processing for workloads running in ambient mode. It functions as a gateway to a resource such as a namespace, service, or pod. You can install, upgrade, and scale waypoint proxies independently of applications. The configuration uses the Kubernetes Gateway API.

Unlike the sidecar model, where each workload runs its own Envoy proxy, waypoint proxies reduce resource use by serving multiple workloads within the same security boundary, such as all workloads in a namespace.

A destination waypoint enforces policies by acting as a gateway. All incoming traffic to a resource, such as a namespace, service, or pod, passes through the waypoint for policy enforcement.

The`ztunnel` node proxy manages L4 functions in ambient mode, including mutual Transport Layer Security (mTLS) encryption, L4 traffic processing, and telemetry. Ztunnel and waypoint proxies communicate using HBONE (HTTP-Based Overlay Network), a protocol that tunnels traffic over HTTP/2 CONNECT with mutual TLS (mTLS) on port`15008`.

You can add a waypoint proxy if workloads require any of the following L7 capabilities:

Traffic management Advanced HTTP routing, load balancing, circuit breaking, rate limiting, fault injection, retries, and timeouts Security Authorization policies based on L7 attributes such as request type or HTTP headers Observability HTTP metrics, access logging, and tracing for application traffic

### 4.6. Deploying waypoint proxies using gateway APICopy link

You can deploy waypoint proxies using Kubernetes Gateway resource.

Prerequisites

- You have logged in to the OpenShift Container Platform 4.19 or later, which provides supported Kubernetes Gateway API CRDs required for ambient mode functionality.
- You have the Red Hat OpenShift Service Mesh Operator 3.2.0 or later installed on the OpenShift cluster.
- You have Istio deployed in ambient mode.
- You have applied the required labels to workloads or namespaces to enable`ztunnel` traffic redirection.

Important

Istio ambient mode is not compatible with clusters that use Red Hat OpenShift Service Mesh 2.6 or earlier. You must not deploy both versions in the same cluster.

Procedure

On OpenShift Container Platform 4.18 and earlier, install the community-maintained Kubernetes Gateway API CRDs by running the following command:

```shell-session
$ oc get crd gateways.gateway.networking.k8s.io &> /dev/null || \
  { oc apply -f https://github.com/kubernetes-sigs/gateway-api/releases/download/v1.2.0/standard-install.yaml; }
```

From OpenShift Container Platform 4.19 onwards, the Gateway API CRDs are installed by default.

Note

The CRDs are community maintained and not supported by Red Hat. Upgrading to OpenShift Container Platform 4.19 or later, which includes supported Gateway API CRDs, may disrupt applications.

### 4.7. Deploying a waypoint proxyCopy link

You can deploy a waypoint proxy in the`bookinfo` application namespace to route traffic through the Istio ambient data plane and enforce L7 policies.

Prerequisites

- You have logged in to the OpenShift Container Platform 4.19 or later, which provides supported Kubernetes Gateway API custom resource definitions (CRDs) required for ambient mode functionality.
- You have the Red Hat OpenShift Service Mesh Operator 3.2.0 or later installed on the OpenShift cluster.
- You have Istio deployed in ambient mode.
- You have deployed the`bookinfo` sample application for the following example.
- You have added the`label istio.io/dataplane-mode=ambient` to the target namespace.

Procedure

Deploy a waypoint proxy in the`bookinfo` application namespace similar to the following example:

Example configuration

```yaml
apiVersion: gateway.networking.k8s.io/v1
kind: Gateway
metadata:
  labels:
    istio.io/waypoint-for: service
  name: waypoint
  namespace: bookinfo
spec:
  gatewayClassName: istio-waypoint
  listeners:
  - name: mesh
    port: 15008
    protocol: HBONE
```

Apply the`waypoint` custom resource (CR) by running the following command:

```shell-session
$ oc apply -f waypoint.yaml
```

The`istio.io/waypoint-for: service` label indicates that the waypoint handles traffic for services. The label determines the type of traffic processed. For more information, see "Waypoint traffic types".

Enroll the`bookinfo` namespace to use the waypoint by running the following command:

```shell-session
$ oc label namespace bookinfo istio.io/use-waypoint=waypoint
```

After enrolling the namespace, requests from any pods using the ambient data plane to services in`bookinfo` will route through the waypoint for L7 processing and policy enforcement.

Verification

Confirm that the waypoint proxy is used by all the services in the`bookinfo` namespace by running the following command:

```shell-session
$ istioctl ztunnel-config svc --namespace ztunnel
```

Example output

```shell-session
NAMESPACE    SERVICE NAME     SERVICE VIP     WAYPOINT   ENDPOINTS
bookinfo     details          172.30.15.248   waypoint   1/1
bookinfo     details-v1       172.30.114.128  waypoint   1/1
bookinfo     productpage      172.30.155.45   waypoint   1/1
bookinfo     productpage-v1   172.30.76.27    waypoint   1/1
bookinfo     ratings          172.30.24.145   waypoint   1/1
bookinfo     ratings-v1       172.30.139.144  waypoint   1/1
bookinfo     reviews          172.30.196.50   waypoint   3/3
bookinfo     reviews-v1       172.30.172.192  waypoint   1/1
bookinfo     reviews-v2       172.30.12.41    waypoint   1/1
bookinfo     reviews-v3       172.30.232.12   waypoint   1/1
bookinfo     waypoint         172.30.92.147   None       1/1
```

Note

You can also configure only specific services or pods to use a waypoint by labeling the respective service or pod. When enrolling a pod explicitly, also add the`istio.io/waypoint-for: workload` label to the corresponding`gateway` resource.

### 4.8. Enabling cross-namespace waypoint usageCopy link

You can use a cross-namespace waypoint to allow resources in one namespace to route traffic through a waypoint deployed in a different namespace.

Procedure

Create a`Gateway` resource that allows workloads in the`bookinfo` namespace to use the`waypoint-default` from the`default` namespace similar to the following example:

Example configuration

```yaml
apiVersion: gateway.networking.k8s.io/v1
kind: Gateway
metadata:
  name: waypoint-default
  namespace: default
spec:
  gatewayClassName: istio-waypoint
  listeners:
  - name: mesh
    port: 15008
    protocol: HBONE
    allowedRoutes:
      namespaces:
        from: Selector
        selector:
          matchLabels:
            kubernetes.io/metadata.name: bookinfo
```

Apply the cross-namespace waypoint by running the following command:

```shell-session
$ oc apply -f waypoint-default.yaml
```

Add the labels required to use a cross-namespace waypoint:

Add the`istio.io/use-waypoint-namespace` label to specify the namespace where the waypoint resides by running the following command:

```shell-session
$ oc label namespace bookinfo istio.io/use-waypoint-namespace=default
```

Add the`istio.io/use-waypoint` label to specify the waypoint to use by running the following command:

```shell-session
$ oc label namespace bookinfo istio.io/use-waypoint=waypoint-default
```

### 4.9. About Layer 7 features in ambient modeCopy link

Ambient mode includes stable Layer 7 (L7) capabilities implemented through the Gateway API`HTTPRoute` resource and the Istio`AuthorizationPolicy` resource.

The`AuthorizationPolicy` resource works in both sidecar and ambient modes. In ambient mode, authorization policies can be targeted for`ztunnel` enforcement or attached for waypoint enforcement. To attach a policy to a waypoint, include a`targetRef` that references either the waypoint itself or a Service configured to use that waypoint.

You can attach Layer 4 (L4) or L7 policies to the waypoint proxy to ensure correct identity-based enforcement, as the destination`ztunnel` recognizes traffic by the identity of the waypoint, once it is part of the traffic path.

Istio peer authentication policies, which configure mutual TLS (mTLS) modes, are supported by ztunnel. In ambient mode, policies that set the mode to`DISABLE` are ignored because ztunnel and HBONE always enforce mTLS. For more information, see "Peer authentication".

### 4.10. Routing traffic using waypoint proxiesCopy link

You can use a deployed waypoint proxy to split traffic between different versions of the Bookinfo`reviews` service for feature testing or A/B testing.

Procedure

Create the traffic routing configuration similar to the following example:

Example configuration

```yaml
apiVersion: gateway.networking.k8s.io/v1
kind: HTTPRoute
metadata:
  name: reviews
  namespace: bookinfo
spec:
  parentRefs:
  - group: ""
    kind: Service
    name: reviews
    port: 9080
  rules:
  - backendRefs:
    - name: reviews-v1
      port: 9080
      weight: 90
    - name: reviews-v2
      port: 9080
      weight: 10
```

Apply the traffic routing configuration by running the following command:

```shell-session
$ oc apply -f traffic-route.yaml
```

Verification

Access the`productpage` service from within the ratings pod by running the following command:

```shell-session
$ oc exec "$(oc get pod -l app=ratings -n bookinfo \
-o jsonpath='{.items[0].metadata.name}')" -c ratings -n bookinfo \
-- curl -sS productpage:9080/productpage | grep -om1 'reviews-v[12]'
```

Most responses (90%) will contain`reviews-v1` output, while a smaller portion (10%) will contain`reviews-v2` output.

### 4.11. Adding authorization policyCopy link

Use an Layer 7 (L7) authorization policy to explicitly allow the`curl` service to send`GET` requests to the`productpage` service while blocking all other operations.

Procedure

Create the authorization policy similar to the following example:

Example configuration

```yaml
apiVersion: security.istio.io/v1
kind: AuthorizationPolicy
metadata:
  name: productpage-waypoint
  namespace: bookinfo
spec:
  targetRefs:
  - kind: Service
    group: ""
    name: productpage
  action: ALLOW
  rules:
  - from:
    - source:
        principals:
        - cluster.local/ns/curl/sa/curl
    to:
    - operation:
        methods: ["GET"]
```

Apply the authorization policy by running the following command:

```shell-session
$ oc apply -f authorization-policy.yaml
```

Note

The`targetRefs` field specifies the service targeted by the authorization policy of the waypoint proxy.

Verification

Create a namespace for a`curl` client by running the following command:

```shell-session
$ oc create namespace curl
```

Deploy a`curl` client by running the following command:

```shell-session
$ oc apply -n curl -f https://raw.githubusercontent.com/openshift-service-mesh/istio/refs/heads/master/samples/curl/curl.yaml
```

Apply the label for ambient mode to the`curl` namespace by running the following command:

```shell-session
$ oc label namespace curl istio.io/dataplane-mode=ambient
```

Verify that a`GET` request to the`productpage` service succeeds with an HTTP 200 response when made from the`default/curl` pod, by running the following command:

```shell-session
$ oc -n curl exec deploy/curl -- sh -c \
  'curl -s -o /dev/null -w "HTTP %{http_code}\n" http://productpage.bookinfo.svc.cluster.local:9080/productpage'
```

Verify that a`POST` request to the same service is denied with an HTTP 403 response due to the applied authorization policy, by running the following command:

```shell-session
$ oc -n curl exec deploy/curl -- sh -c \
  'curl -s -o /dev/null -w "HTTP %{http_code}\n" -X POST http://productpage.bookinfo.svc.cluster.local:9080/productpage'
```

Verify that a`GET` request from another service, such as the`ratings` pod in the`bookinfo` namespace, is also denied with`RBAC: access denied`, by running the following command:

```shell-session
$ oc exec "$(oc get pod -l app=ratings -n bookinfo \
-o jsonpath='{.items[0].metadata.name}')" \
-c ratings -n bookinfo \
-- curl -sS productpage:9080/productpage
```

Clean up the resources by running the following commands:

Delete the`curl` application by running the following command:

```shell-session
$ oc delete -n curl -f https://raw.githubusercontent.com/openshift-service-mesh/istio/refs/heads/master/samples/curl/curl.yaml
```

Delete the`curl` namespace by running the following command:

```shell-session
$ oc delete namespace curl
```

## Chapter 5. OpenShift Service Mesh and cert-managerCopy link

The cert-manager tool provides a unified API to manage X.509 certificates for applications in a Kubernetes environment. You can use cert-manager to integrate with public or private key infrastructures (PKI) and automate certificate renewal.

### 5.1. About the cert-manager Operator istio-csr agentCopy link

The cert-manager Operator for Red Hat OpenShift enhances certificate management for securing workloads and control plane components in Red Hat OpenShift Service Mesh and Istio. It supports issuing, delivering, and renewing certificates used for mutual Transport Layer Security (mTLS) through cert-manager issuers.

By integrating Istio with the`istio-csr` agent that is managed by the cert-manager Operator, you enable Istio to request and manage the certificates directly. The integration simplifies security configuration and centralizes certificate management within the cluster.

Note

The cert-manager Operator for Red Hat OpenShift must be installed before you create and install your`Istio` resource.

#### 5.1.1. Integrating Service Mesh with the cert-manager Operator by using the istio-csr agentCopy link

You can integrate the cert-manager Operator with OpenShift Service Mesh by deploying the`istio-csr` agent and configuring an`Istio` resource that uses the`istio-csr` agent to process workload and control plane certificate signing requests. The following procedure creates a self-signed`issuer` object.

Prerequisites

- You have installed the cert-manager Operator for Red Hat OpenShift version 1.15.1.
- You are logged in to OpenShift Container Platform 4.14 or later.
- You have installed the OpenShift Service Mesh Operator.
- You have a`IstioCNI` instance running in the cluster.
- You have installed the`istioctl` command.

Procedure

Create the`istio-system` namespace by running the following command:

```shell-session
$ oc create namespace istio-system
```

Patch the cert-manager Operator to install the`istio-csr` agent by running the following command:

```shell-session
$ oc -n cert-manager-operator patch subscription openshift-cert-manager-operator \
  --type='merge' -p \
  '{"spec":{"config":{"env":[{"name":"UNSUPPORTED_ADDON_FEATURES","value":"IstioCSR=true"}]}}}'
```

Create the root certificate authority (CA) issuer by creating an`Issuer` object for the`istio-csr` agent:

Create a new project for installing the`istio-csr` agent by running the following command:

```shell-session
$ oc new-project istio-csr
```

Create an`Issuer` object similar to the following example:

Note

The`selfSigned` issuer is intended for demonstration, testing, or proof-of-concept environments. For production deployments, use a secure and trusted CA.

Example`issuer.yaml` file

```yaml
apiVersion: cert-manager.io/v1
kind: Issuer
metadata:
  name: selfsigned
  namespace: istio-system
spec:
  selfSigned: {}
---
apiVersion: cert-manager.io/v1
kind: Certificate
metadata:
  name: istio-ca
  namespace: istio-system
spec:
  isCA: true
  duration: 87600h
  secretName: istio-ca
  commonName: istio-ca
  privateKey:
    algorithm: ECDSA
    size: 256
  subject:
    organizations:
      - cluster.local
      - cert-manager
  issuerRef:
    name: selfsigned
    kind: Issuer
    group: cert-manager.io
---
apiVersion: cert-manager.io/v1
kind: Issuer
metadata:
  name: istio-ca
  namespace: istio-system
spec:
  ca:
    secretName: istio-ca
```

Create the objects by running the following command:

```shell-session
$ oc apply -f issuer.yaml
```

Wait for the`istio-ca` certificate to contain the "Ready" status condition by running the following command:

```shell-session
$ oc wait --for=condition=Ready certificates/istio-ca -n istio-system
```

Create the`IstioCSR` custom resource:

Create the`IstioCSR` custom resource similar to the following example:

Example`istioCSR.yaml` file

```yaml
apiVersion: operator.openshift.io/v1alpha1
kind: IstioCSR
metadata:
  name: default
  namespace: istio-csr
spec:
  istioCSRConfig:
    certManager:
      issuerRef:
        name: istio-ca
        kind: Issuer
        group: cert-manager.io
    istiodTLSConfig:
      trustDomain: cluster.local
    istio:
      namespace: istio-system
```

Create the`istio-csr` agent by by running the following command:

```shell-session
$ oc create -f istioCSR.yaml
```

Verify that the`istio-csr` deployment is ready by running the following command:

```shell-session
$ oc get deployment -n istio-csr
```

Install the`istio` resource:

Note

The configuration disables the built-in CA server for Istio and forwards certificate signing requests from`istiod` to the`istio-csr` agent. The`istio-csr` agent obtains certificates for both`istiod` and mesh workloads from the cert-manager Operator. The`istiod` TLS certificate that is generated by the`istio-csr` agent is mounted into the pod at a known location for use.

Create the`Istio` object similar to the following example:

Example`istio.yaml` file

```yaml
apiVersion: sailoperator.io/v1
kind: Istio
metadata:
  name: default
spec:
  version: v1.24-latest
  namespace: istio-system
  values:
    global:
      caAddress: cert-manager-istio-csr.istio-csr.svc:443
    pilot:
      env:
        ENABLE_CA_SERVER: "false"
```

Create the`Istio` resource by running the following command:

```shell-session
$ oc apply -f istio.yaml
```

Verify that the`istio` resource displays the "Ready" status condition by running the following command:

```shell-session
$ oc wait --for=condition=Ready istios/default -n istio-system
```

#### 5.1.2. Verifying Service Mesh with the cert-manager Operator using the istio-csr agentCopy link

You can use the sample`httpbin` service and`sleep` application to verify traffic between workloads. Check the workload proxy certificate to verify that the cert-manager Operator is installed correctly.

Create the namespaces:

Create the`apps-1` namespace by running the following command:

```shell-session
$ oc new-project apps-1
```

Create the`apps-2` namespace by running the following command:

```shell-session
$ oc new-project apps-2
```

Add the`istio-injection=enabled` label on the namespaces:

Add the`istio-injection=enabled` label on the`apps-1` namespace by running the following command:

```shell-session
$ oc label namespaces apps-1 istio-injection=enabled
```

Add the`istio-injection=enabled` label on the`apps-2` namespace by running the following command:

```shell-session
$ oc label namespaces apps-2 istio-injection=enabled
```

Deploy the`httpbin` app in the namespaces:

Deploy the`httpbin` app in the`apps-1` namespace by running the following command:

```shell-session
$ oc apply -n apps-1 -f https://raw.githubusercontent.com/openshift-service-mesh/istio/release-1.24/samples/httpbin/httpbin.yaml
```

Deploy the`httpbin` app in the`apps-2` namespace by running the following command:

```shell-session
$ oc apply -n apps-2 -f https://raw.githubusercontent.com/openshift-service-mesh/istio/release-1.24/samples/httpbin/httpbin.yaml
```

Deploy the`sleep` app in the namespaces:

Deploy the`sleep` app in the`apps-1` namespace by running the following command:

```shell-session
$ oc apply -n apps-1 -f https://raw.githubusercontent.com/openshift-service-mesh/istio/release-1.24/samples/sleep/sleep.yaml
```

Deploy the`sleep` app in the`apps-2` namespace by running the following command:

```shell-session
$ oc apply -n apps-2 -f https://raw.githubusercontent.com/openshift-service-mesh/istio/release-1.24/samples/sleep/sleep.yaml
```

Verify that the created apps have sidecars injected:

Verify that the created apps have sidecars injected for`apps-1` namespace by running the following command:

```shell-session
$ oc get pods -n apps-1
```

Verify that the created apps have sidecars injected for`apps-2` namespace by running the following command:

```shell-session
$ oc get pods -n apps-2
```

Create a mesh-wide strict mutual Transport Layer Security (mTLS) policy similar to the following example:

Note

Enabling`PeerAuthentication` in strict mTLS mode verifies that certificates are distributed correctly and that mTLS communication functions between workloads.

Example`peer_auth.yaml` file

```yaml
apiVersion: security.istio.io/v1beta1
kind: PeerAuthentication
metadata:
  name: default
  namespace: istio-system
spec:
  mtls:
    mode: STRICT
```

Apply the mTLS policy by running the following command:

```shell-session
$ oc apply -f peer_auth.yaml
```

Verify that the`apps-1/sleep` app can access the`apps-2/httpbin` service by running the following command:

```shell-session
$ oc -n apps-1 exec "$(oc -n apps-1 get pod \
  -l app=sleep -o jsonpath={.items..metadata.name})" \
  -c sleep -- curl -sIL http://httpbin.apps-2.svc.cluster.local:8000
```

Example output

```shell-session
HTTP/1.1 200 OK
access-control-allow-credentials: true
access-control-allow-origin: *
content-security-policy: default-src 'self'; style-src 'self' 'unsafe-inline'; img-src 'self' camo.githubusercontent.com
content-type: text/html; charset=utf-8
date: Wed, 18 Jun 2025 09:20:55 GMT
x-envoy-upstream-service-time: 14
server: envoy
transfer-encoding: chunked
```

Verify that the`apps-2/sleep` app can access the`apps-1/httpbin` service by running the following command:

```shell-session
$ oc -n apps-2 exec "$(oc -n apps-1 get pod \
  -l app=sleep -o jsonpath={.items..metadata.name})" \
  -c sleep -- curl -sIL http://httpbin.apps-2.svc.cluster.local:8000
```

Example output

```shell-session
HTTP/1.1 200 OK
access-control-allow-credentials: true
access-control-allow-origin: *
content-security-policy: default-src 'self'; style-src 'self' 'unsafe-inline'; img-src 'self' camo.githubusercontent.com
content-type: text/html; charset=utf-8
date: Wed, 18 Jun 2025 09:21:23 GMT
x-envoy-upstream-service-time: 16
server: envoy
transfer-encoding: chunked
```

Verify that the`httpbin` workload certificate matches as expected by running the following command:

```shell-session
$ istioctl proxy-config secret -n apps-1 \
  $(oc get pods -n apps-1 -o jsonpath='{.items..metadata.name}' --selector app=httpbin) \
  -o json | jq -r '.dynamicActiveSecrets[0].secret.tlsCertificate.certificateChain.inlineBytes' \
  | base64 --decode | openssl x509 -text -noout
```

Example output

```shell-session
...
Issuer: O = cert-manager + O = cluster.local, CN = istio-ca
...
X509v3 Subject Alternative Name:
URI:spiffe://cluster.local/ns/apps-1/sa/httpbin
```

#### 5.1.3. Uninstalling Service Mesh with the cert-manager Operator by using the istio-csr agentCopy link

You can uninstall the cert-manager Operator with OpenShift Service Mesh by completing the following procedure. Before you remove the following resources, verify that no Red Hat OpenShift Service Mesh or Istio components reference the`Istio-CSR` agent or the certificates it issued. Removing these resources while they are still in use might disrupt mesh functionality.

Procedure

Remove the`IstioCSR` custom resource by running the following command:

```shell-session
$ oc -n <istio-csr_project_name> delete istiocsrs.operator.openshift.io default
```

Remove the related resources:

List the cluster scoped-resources by running the following command:

```shell-session
$ oc get clusterrolebindings,clusterroles -l "app=cert-manager-istio-csr,app.kubernetes.io/name=cert-manager-istio-csr"
```

Save the names of the listed resources for later reference.

List the resources in`istio-csr` agent deployed namespace by running the following command:

```shell-session
$ oc get certificate,deployments,services,serviceaccounts -l "app=cert-manager-istio-csr,app.kubernetes.io/name=cert-manager-istio-csr" -n <istio_csr_project_name>
```

Save the names of the listed resources for later reference.

List the resources in Red Hat OpenShift Service Mesh or Istio deployed namespaces by running the following command:

```shell-session
$ oc get roles,rolebindings \
  -l "app=cert-manager-istio-csr,app.kubernetes.io/name=cert-manager-istio-csr" \
  -n <istio_csr_project_name>
```

Save the names of the listed resources for later reference.

For each resource listed in previous steps, delete the resources by running the following command:

```shell-session
$ oc -n <istio_csr_project_name> delete <resource_type>/<resource_name>
```

## Chapter 6. Multi-cluster topologiesCopy link

Multi-cluster topologies are useful for organizations with distributed systems or environments seeking enhanced scalability, fault tolerance, and regional redundancy.

### 6.1. About multi-cluster mesh topologiesCopy link

In a multi-cluster mesh topology, you install and manage a single Istio mesh across multiple OpenShift Container Platform clusters, enabling communication and service discovery between the services. Two factors determine the multi-cluster mesh topology: control plane topology and network topology. There are two options for each topology. Therefore, there are four possible multi-cluster mesh topology configurations.

- Multi-Primary Single Network: Combines the multi-primary control plane topology and the single network network topology models.
- Multi-Primary Multi-Network: Combines the multi-primary control plane topology and the multi-network network topology models.
- Primary-Remote Single Network: Combines the primary-remote control plane topology and the single network network topology models.
- Primary-Remote Multi-Network: Combines the primary-remote control plane topology and the multi-network network topology models.

#### 6.1.1. Control plane topology modelsCopy link

A multi-cluster mesh must use one of the following control plane topologies:

- Multi-Primary: In this configuration, a control plane resides on every cluster. Each control plane observes the API servers in all of the other clusters for services and endpoints.
- Primary-Remote: In this configuration, the control plane resides only on one cluster, called the primary cluster. No control plane runs on any of the other clusters, called remote clusters. The control plane on the primary cluster discovers services and endpoints and configures the sidecar proxies for the workloads in all clusters.

#### 6.1.2. Network topology modelsCopy link

A multi-cluster mesh must use one of the following network topologies:

- Single Network: All clusters reside on the same network and there is direct connectivity between the services in all the clusters. There is no need to use gateways for communication between the services across cluster boundaries.
- Multi-Network: Clusters reside on different networks and there is no direct connectivity between services. Gateways must be used to enable communication across network boundaries.

### 6.2. Multi-Cluster configuration overviewCopy link

To configure a multi-cluster topology you must perform the following actions:

- Install the OpenShift Service Mesh Operator for each cluster.
- Create or have access to root and intermediate certificates for each cluster.
- Apply the security certificates for each cluster.
- Install Istio for each cluster.

#### 6.2.1. Creating certificates for a multi-cluster topologyCopy link

Create the root and intermediate certificate authority (CA) certificates for two clusters.

Prerequisites

- You have OpenSSL installed locally.

Procedure

Create the root CA certificate:

Create a key for the root certificate by running the following command:

```shell-session
$ openssl genrsa -out root-key.pem 4096
```

Create an OpenSSL configuration certificate file named`root-ca.conf` for the root CA certificates:

Example root certificate configuration file

```shell-session
encrypt_key = no
prompt = no
utf8 = yes
default_md = sha256
default_bits = 4096
req_extensions = req_ext
x509_extensions = req_ext
distinguished_name = req_dn
[ req_ext ]
subjectKeyIdentifier = hash
basicConstraints = critical, CA:true
keyUsage = critical, digitalSignature, nonRepudiation, keyEncipherment, keyCertSign
[ req_dn ]
O = Istio
CN = Root CA
```

Create the certificate signing request by running the following command:

```shell-session
$ openssl req -sha256 -new -key root-key.pem \
  -config root-ca.conf \
  -out root-cert.csr
```

Create a shared root certificate by running the following command:

```shell-session
$ openssl x509 -req -sha256 -days 3650 \
  -signkey root-key.pem \
  -extensions req_ext -extfile root-ca.conf \
  -in root-cert.csr \
  -out root-cert.pem
```

Create the intermediate CA certificate for the East cluster:

Create a directory named`east` by running the following command:

```shell-session
$ mkdir east
```

Create a key for the intermediate certificate for the East cluster by running the following command:

```shell-session
$ openssl genrsa -out east/ca-key.pem 4096
```

Create an OpenSSL configuration file named`intermediate.conf` in the`east/` directory for the intermediate certificate of the East cluster. Copy the following example file and save it locally:

Example configuration file

```plaintext
[ req ]
encrypt_key = no
prompt = no
utf8 = yes
default_md = sha256
default_bits = 4096
req_extensions = req_ext
x509_extensions = req_ext
distinguished_name = req_dn
[ req_ext ]
subjectKeyIdentifier = hash
basicConstraints = critical, CA:true, pathlen:0
keyUsage = critical, digitalSignature, nonRepudiation, keyEncipherment, keyCertSign
subjectAltName=@san
[ san ]
DNS.1 = istiod.istio-system.svc
[ req_dn ]
O = Istio
CN = Intermediate CA
L = east
```

Create a certificate signing request by running the following command:

```shell-session
$ openssl req -new -config east/intermediate.conf \
   -key east/ca-key.pem \
   -out east/cluster-ca.csr
```

Create the intermediate CA certificate for the East cluster by running the following command:

```shell-session
$ openssl x509 -req -sha256 -days 3650 \
   -CA root-cert.pem \
   -CAkey root-key.pem -CAcreateserial \
   -extensions req_ext -extfile east/intermediate.conf \
   -in east/cluster-ca.csr \
   -out east/ca-cert.pem
```

Create a certificate chain from the intermediate and root CA certificate for the east cluster by running the following command:

```shell-session
$ cat east/ca-cert.pem root-cert.pem > east/cert-chain.pem && cp root-cert.pem east
```

Create the intermediate CA certificate for the West cluster:

Create a directory named`west` by running the following command:

```shell-session
$ mkdir west
```

Create a key for the intermediate certificate for the West cluster by running the following command:

```shell-session
$ openssl genrsa -out west/ca-key.pem 4096
```

Create an OpenSSL configuration file named`intermediate.conf` in the`west/` directory for for the intermediate certificate of the West cluster. Copy the following example file and save it locally:

Example configuration file

```plaintext
[ req ]
encrypt_key = no
prompt = no
utf8 = yes
default_md = sha256
default_bits = 4096
req_extensions = req_ext
x509_extensions = req_ext
distinguished_name = req_dn
[ req_ext ]
subjectKeyIdentifier = hash
basicConstraints = critical, CA:true, pathlen:0
keyUsage = critical, digitalSignature, nonRepudiation, keyEncipherment, keyCertSign
subjectAltName=@san
[ san ]
DNS.1 = istiod.istio-system.svc
[ req_dn ]
O = Istio
CN = Intermediate CA
L = west
```

Create a certificate signing request by running the following command:

```shell-session
$ openssl req -new -config west/intermediate.conf \
   -key west/ca-key.pem \
   -out west/cluster-ca.csr
```

Create the certificate by running the following command:

```shell-session
$ openssl x509 -req -sha256 -days 3650 \
   -CA root-cert.pem \
   -CAkey root-key.pem -CAcreateserial \
   -extensions req_ext -extfile west/intermediate.conf \
   -in west/cluster-ca.csr \
   -out west/ca-cert.pem
```

Create the certificate chain by running the following command:

```shell-session
$ cat west/ca-cert.pem root-cert.pem > west/cert-chain.pem && cp root-cert.pem west
```

#### 6.2.2. Applying certificates to a multi-cluster topologyCopy link

Apply root and intermediate certificate authority (CA) certificates to the clusters in a multi-cluster topology.

Note

In this procedure,`CLUSTER1` is the East cluster and`CLUSTER2` is the West cluster.

Prerequisites

- You have access to two OpenShift Container Platform clusters with external load balancer support.
- You have created the root CA certificate and intermediate CA certificates for each cluster or someone has made them available for you.

Procedure

Apply the certificates to the East cluster of the multi-cluster topology:

Log in to East cluster by running the following command:

```shell-session
$ oc login -u https://<east_cluster_api_server_url>
```

Set up the environment variable that contains the`oc` command context for the East cluster by running the following command:

```shell-session
$ export CTX_CLUSTER1=$(oc config current-context)
```

Create a project called`istio-system` by running the following command:

```shell-session
$ oc get project istio-system --context "${CTX_CLUSTER1}" || oc new-project istio-system --context "${CTX_CLUSTER1}"
```

Configure Istio to use`network1` as the default network for the pods on the East cluster by running the following command:

```shell-session
$ oc --context "${CTX_CLUSTER1}" label namespace istio-system topology.istio.io/network=network1
```

Create the CA certificates, certificate chain, and the private key for Istio on the East cluster by running the following command:

```shell-session
$ oc get secret -n istio-system --context "${CTX_CLUSTER1}" cacerts || oc create secret generic cacerts -n istio-system --context "${CTX_CLUSTER1}" \
  --from-file=east/ca-cert.pem \
  --from-file=east/ca-key.pem \
  --from-file=east/root-cert.pem \
  --from-file=east/cert-chain.pem
```

Note

If you followed the instructions in "Creating certificates for a multi-cluster mesh", your certificates will reside in the`east/` directory. If your certificates reside in a different directory, modify the syntax accordingly.

Apply the certificates to the West cluster of the multi-cluster topology:

Log in to the West cluster by running the following command:

```shell-session
$ oc login -u https://<west_cluster_api_server_url>
```

Set up the environment variable that contains the`oc` command context for the West cluster by running the following command:

```shell-session
$ export CTX_CLUSTER2=$(oc config current-context)
```

Create a project called`istio-system` by running the following command:

```shell-session
$ oc get project istio-system --context "${CTX_CLUSTER2}" || oc new-project istio-system --context "${CTX_CLUSTER2}"
```

Configure Istio to use`network2` as the default network for the pods on the West cluster by running the following command:

```shell-session
$ oc --context "${CTX_CLUSTER2}" label namespace istio-system topology.istio.io/network=network2
```

Create the CA certificate secret for Istio on the West cluster by running the following command:

```shell-session
$ oc get secret -n istio-system --context "${CTX_CLUSTER2}" cacerts || oc create secret generic cacerts -n istio-system --context "${CTX_CLUSTER2}" \
  --from-file=west/ca-cert.pem \
  --from-file=west/ca-key.pem \
  --from-file=west/root-cert.pem \
  --from-file=west/cert-chain.pem
```

Note

If you followed the instructions in "Creating certificates for a multi-cluster mesh", your certificates will reside in the`west/` directory. If the certificates reside in a different directory, modify the syntax accordingly.

Next steps

Install Istio on all the clusters comprising the mesh topology.

### 6.3. Installing a multi-primary multi-network meshCopy link

Install Istio in the multi-primary multi-network topology on two OpenShift Container Platform clusters.

Note

In this procedure,`CLUSTER1` is the East cluster and`CLUSTER2` is the West cluster.

You can adapt these instructions for a mesh spanning more than two clusters.

Prerequisites

- You have installed the OpenShift Service Mesh 3 Operator on all of the clusters that include the mesh.
- You have created certificates for the multi-cluster mesh.
- You have applied certificates to the multi-cluster topology.
- You have created an Istio Container Network Interface (CNI) resource.
- You have`istioctl` installed.

Important

In on-premise environments, such as those running on bare metal, OpenShift Container Platform clusters often do not include a native load-balancer capability. A service of type`LoadBalancer`, such as the`istio-eastwestgateway`, will not automatically be assigned an external IP address. To ensure the required external IP assignment for cross-cluster communication, cluster administrators must install and configure the MetalLB Operator. MetalLB is valuable in bare metal or bare metal-like infrastructures when fault-tolerant access to an application via an external IP address is necessary. Once deployed, MetalLB provides a platform-native load balancer. In addition to bare metal, the MetalLB Operator can offer load balancing for installations on other infrastructures that might lack native load-balancer capability, including:

- VMware vSphere
- IBM Z® and IBM® LinuxONE
- IBM Z® and IBM® LinuxONE for Red Hat Enterprise Linux (RHEL) KVM
- IBM Power®

For more information, see [MetalLB Operator](https://docs.redhat.com/en/documentation/openshift_container_platform/latest/html/networking_operators/metallb-operator).

Procedure

Create an`ISTIO_VERSION` environment variable that defines the Istio version to install by running the following command:

```shell-session
$ export ISTIO_VERSION=1.24.3
```

Install Istio on the East cluster:

Create an`Istio` resource on the East cluster by running the following command:

```shell-session
$ cat <<EOF | oc --context "${CTX_CLUSTER1}" apply -f -
apiVersion: sailoperator.io/v1
kind: Istio
metadata:
  name: default
spec:
  version: v${ISTIO_VERSION}
  namespace: istio-system
  values:
    global:
      meshID: mesh1
      multiCluster:
        clusterName: cluster1
      network: network1
EOF
```

Wait for the control plane to return the`Ready` status condition by running the following command:

```shell-session
$ oc --context "${CTX_CLUSTER1}" wait --for condition=Ready istio/default --timeout=3m
```

Create an East-West gateway on the East cluster by running the following command:

```shell-session
$ oc --context "${CTX_CLUSTER1}" apply -f https://raw.githubusercontent.com/istio-ecosystem/sail-operator/main/docs/deployment-models/resources/east-west-gateway-net1.yaml
```

Expose the services through the gateway by running the following command:

```shell-session
$ oc --context "${CTX_CLUSTER1}" apply -n istio-system -f https://raw.githubusercontent.com/istio-ecosystem/sail-operator/main/docs/deployment-models/resources/expose-services.yaml
```

Install Istio on the West cluster:

Create an`Istio` resource on the West cluster by running the following command:

```shell-session
$ cat <<EOF | oc --context "${CTX_CLUSTER2}" apply -f -
apiVersion: sailoperator.io/v1
kind: Istio
metadata:
  name: default
spec:
  version: v${ISTIO_VERSION}
  namespace: istio-system
  values:
    global:
      meshID: mesh1
      multiCluster:
        clusterName: cluster2
      network: network2
EOF
```

Wait for the control plane to return the`Ready` status condition by running the following command:

```shell-session
$ oc --context "${CTX_CLUSTER2}" wait --for condition=Ready istio/default --timeout=3m
```

Create an East-West gateway on the West cluster by running the following command:

```shell-session
$ oc --context "${CTX_CLUSTER2}" apply -f https://raw.githubusercontent.com/istio-ecosystem/sail-operator/main/docs/deployment-models/resources/east-west-gateway-net2.yaml
```

Expose the services through the gateway by running the following command:

```shell-session
$ oc --context "${CTX_CLUSTER2}" apply -n istio-system -f https://raw.githubusercontent.com/istio-ecosystem/sail-operator/main/docs/deployment-models/resources/expose-services.yaml
```

Create the`istio-reader-service-account` service account for the East cluster by running the following command:

```shell-session
$ oc --context="${CTX_CLUSTER1}" create serviceaccount istio-reader-service-account -n istio-system
```

Create the`istio-reader-service-account` service account for the West cluster by running the following command:

```shell-session
$ oc --context="${CTX_CLUSTER2}" create serviceaccount istio-reader-service-account -n istio-system
```

Add the`cluster-reader` role to the East cluster by running the following command:

```shell-session
$ oc --context="${CTX_CLUSTER1}" adm policy add-cluster-role-to-user cluster-reader -z istio-reader-service-account -n istio-system
```

Add the`cluster-reader` role to the West cluster by running the following command:

```shell-session
$ oc --context="${CTX_CLUSTER2}" adm policy add-cluster-role-to-user cluster-reader -z istio-reader-service-account -n istio-system
```

Install a remote secret on the East cluster that provides access to the API server on the West cluster by running the following command:

```shell-session
$ istioctl create-remote-secret \
  --context="${CTX_CLUSTER2}" \
  --name=cluster2 \
  --create-service-account=false | \
  oc --context="${CTX_CLUSTER1}" apply -f -
```

Install a remote secret on the West cluster that provides access to the API server on the East cluster by running the following command:

```shell-session
$ istioctl create-remote-secret \
  --context="${CTX_CLUSTER1}" \
  --name=cluster1 \
  --create-service-account=false | \
  oc --context="${CTX_CLUSTER2}" apply -f -
```

#### 6.3.1. Verifying a multi-cluster topologyCopy link

Deploy sample applications and verify traffic on a multi-cluster topology on two OpenShift Container Platform clusters.

Note

In this procedure,`CLUSTER1` is the East cluster and`CLUSTER2` is the West cluster.

Prerequisites

- You have installed the OpenShift Service Mesh Operator on all of the clusters that comprise the mesh.
- You have completed "Creating certificates for a multi-cluster mesh".
- You have completed "Applying certificates to a multi-cluster topology".
- You have created an Istio Container Network Interface (CNI) resource.
- You have`istioctl` installed on the laptop you will use to run these instructions.
- You have installed a multi-cluster topology.

Procedure

Deploy sample applications on the East cluster:

Create a sample application namespace on the East cluster by running the following command:

```shell-session
$ oc --context "${CTX_CLUSTER1}" get project sample || oc --context="${CTX_CLUSTER1}" new-project sample
```

Label the application namespace to support sidecar injection by running the following command:

```shell-session
$ oc --context="${CTX_CLUSTER1}" label namespace sample istio-injection=enabled
```

Deploy the`helloworld` application:

Create the`helloworld` service by running the following command:

```shell-session
$ oc --context="${CTX_CLUSTER1}" apply \
  -f https://raw.githubusercontent.com/openshift-service-mesh/istio/release-1.24/samples/helloworld/helloworld.yaml \
  -l service=helloworld -n sample
```

Create the`helloworld-v1` deployment by running the following command:

```shell-session
$ oc --context="${CTX_CLUSTER1}" apply \
  -f https://raw.githubusercontent.com/openshift-service-mesh/istio/release-1.24/samples/helloworld/helloworld.yaml \
  -l version=v1 -n sample
```

Deploy the`sleep` application by running the following command:

```shell-session
$ oc --context="${CTX_CLUSTER1}" apply \
  -f https://raw.githubusercontent.com/openshift-service-mesh/istio/release-1.24/samples/sleep/sleep.yaml -n sample
```

Wait for the`helloworld` application on the East cluster to return the`Ready` status condition by running the following command:

```shell-session
$ oc --context="${CTX_CLUSTER1}" wait --for condition=available -n sample deployment/helloworld-v1
```

Wait for the`sleep` application on the East cluster to return the`Ready` status condition by running the following command:

```shell-session
$ oc --context="${CTX_CLUSTER1}" wait --for condition=available -n sample deployment/sleep
```

Deploy the sample applications on the West cluster:

Create a sample application namespace on the West cluster by running the following command:

```shell-session
$ oc --context "${CTX_CLUSTER2}" get project sample || oc --context="${CTX_CLUSTER2}" new-project sample
```

Label the application namespace to support sidecar injection by running the following command:

```shell-session
$ oc --context="${CTX_CLUSTER2}" label namespace sample istio-injection=enabled
```

Deploy the`helloworld` application:

Create the`helloworld` service by running the following command:

```shell-session
$ oc --context="${CTX_CLUSTER2}" apply \
  -f https://raw.githubusercontent.com/openshift-service-mesh/istio/release-1.24/samples/helloworld/helloworld.yaml \
  -l service=helloworld -n sample
```

Create the`helloworld-v2` deployment by running the following command:

```shell-session
$ oc --context="${CTX_CLUSTER2}" apply \
  -f https://raw.githubusercontent.com/openshift-service-mesh/istio/release-1.24/samples/helloworld/helloworld.yaml \
  -l version=v2 -n sample
```

Deploy the`sleep` application by running the following command:

```shell-session
$ oc --context="${CTX_CLUSTER2}" apply \
  -f https://raw.githubusercontent.com/openshift-service-mesh/istio/release-1.24/samples/sleep/sleep.yaml -n sample
```

Wait for the`helloworld` application on the West cluster to return the`Ready` status condition by running the following command:

```shell-session
$ oc --context="${CTX_CLUSTER2}" wait --for condition=available -n sample deployment/helloworld-v2
```

Wait for the`sleep` application on the West cluster to return the`Ready` status condition by running the following command:

```shell-session
$ oc --context="${CTX_CLUSTER2}" wait --for condition=available -n sample deployment/sleep
```

Verifying traffic flows between clusters

For the East cluster, send 10 requests to the`helloworld` service by running the following command:

```shell-session
$ for i in {0..9}; do \
  oc --context="${CTX_CLUSTER1}" exec -n sample deploy/sleep -c sleep -- curl -sS helloworld.sample:5000/hello; \
done
```

Verify that you see responses from both clusters. This means version 1 and version 2 of the service can be seen in the responses.

For the West cluster, send 10 requests to the`helloworld` service:

```shell-session
$ for i in {0..9}; do \
  oc --context="${CTX_CLUSTER2}" exec -n sample deploy/sleep -c sleep -- curl -sS helloworld.sample:5000/hello; \
done
```

Verify that you see responses from both clusters. This means version 1 and version 2 of the service can be seen in the responses.

#### 6.3.2. Removing a multi-cluster topology from a development environmentCopy link

After experimenting with the multi-cluster functionality in a development environment, remove the multi-cluster topology from all the clusters.

Note

In this procedure,`CLUSTER1` is the East cluster and`CLUSTER2` is the West cluster.

Prerequisites

- You have installed a multi-cluster topology.

Procedure

Remove Istio and the sample applications from the East cluster of the development environment by running the following command:

```shell-session
$ oc --context="${CTX_CLUSTER1}" delete istio/default ns/istio-system ns/sample ns/istio-cni
```

Remove Istio and the sample applications from the West cluster of development environment by running the following command:

```shell-session
$ oc --context="${CTX_CLUSTER2}" delete istio/default ns/istio-system ns/sample ns/istio-cni
```

### 6.4. Installing a primary-remote multi-network meshCopy link

Install Istio in a primary-remote multi-network topology on two OpenShift Container Platform clusters.

Note

In this procedure,`CLUSTER1` is the East cluster and`CLUSTER2` is the West cluster. The East cluster is the primary cluster and the West cluster is the remote cluster.

You can adapt these instructions for a mesh spanning more than two clusters.

Prerequisites

- You have installed the OpenShift Service Mesh 3 Operator on all of the clusters that comprise the mesh.
- You have completed "Creating certificates for a multi-cluster mesh".
- You have completed "Applying certificates to a multi-cluster topology".
- You have created an Istio Container Network Interface (CNI) resource.
- You have`istioctl` installed on the laptop you will use to run these instructions.

Procedure

Create an`ISTIO_VERSION` environment variable that defines the Istio version to install by running the following command:

```shell-session
$ export ISTIO_VERSION=1.24.3
```

Install Istio on the East cluster:

Set the default network for the East cluster by running the following command:

```shell-session
$ oc --context="${CTX_CLUSTER1}" label namespace istio-system topology.istio.io/network=network1
```

Create an`Istio` resource on the East cluster by running the following command:

```shell-session
$ cat <<EOF | oc --context "${CTX_CLUSTER1}" apply -f -
apiVersion: sailoperator.io/v1
kind: Istio
metadata:
  name: default
spec:
  version: v${ISTIO_VERSION}
  namespace: istio-system
  values:
    global:
      meshID: mesh1
      multiCluster:
        clusterName: cluster1
      network: network1
      externalIstiod: true 
```

1

```shell-session

EOF
```

1

This enables the control plane installed on the East cluster to serve as an external control plane for other remote clusters.

Wait for the control plane to return the "Ready" status condition by running the following command:

```shell-session
$ oc --context "${CTX_CLUSTER1}" wait --for condition=Ready istio/default --timeout=3m
```

Create an East-West gateway on the East cluster by running the following command:

```shell-session
$ oc --context "${CTX_CLUSTER1}" apply -f https://raw.githubusercontent.com/istio-ecosystem/sail-operator/main/docs/deployment-models/resources/east-west-gateway-net1.yaml
```

Expose the control plane through the gateway so that services in the West cluster can access the control plane by running the following command:

```shell-session
$ oc --context "${CTX_CLUSTER1}" apply -n istio-system -f https://raw.githubusercontent.com/istio-ecosystem/sail-operator/main/docs/deployment-models/resources/expose-istiod.yaml
```

Expose the application services through the gateway by running the following command:

```shell-session
$ oc --context "${CTX_CLUSTER1}" apply -n istio-system -f https://raw.githubusercontent.com/istio-ecosystem/sail-operator/main/docs/deployment-models/resources/expose-services.yaml
```

Install Istio on the West cluster:

Save the IP address of the East-West gateway running in the East cluster by running the following command:

```shell-session
$ export DISCOVERY_ADDRESS=$(oc --context="${CTX_CLUSTER1}" \
    -n istio-system get svc istio-eastwestgateway \
    -o jsonpath='{.status.loadBalancer.ingress[0].ip}')
```

Create an`Istio` resource on the West cluster by running the following command:

```shell-session
$ cat <<EOF | oc --context "${CTX_CLUSTER2}" apply -f -
apiVersion: sailoperator.io/v1
kind: Istio
metadata:
  name: default
spec:
  version: v${ISTIO_VERSION}
  namespace: istio-system
  profile: remote
  values:
    istiodRemote:
      injectionPath: /inject/cluster/cluster2/net/network2
    global:
      remotePilotAddress: ${DISCOVERY_ADDRESS}
EOF
```

Annotate the`istio-system` namespace in the West cluster so that it is managed by the control plane in the East cluster by running the following command:

```shell-session
$ oc --context="${CTX_CLUSTER2}" annotate namespace istio-system topology.istio.io/controlPlaneClusters=cluster1
```

Set the default network for the West cluster by running the following command:

```shell-session
$ oc --context="${CTX_CLUSTER2}" label namespace istio-system topology.istio.io/network=network2
```

Install a remote secret on the East cluster that provides access to the API server on the West cluster by running the following command:

```shell-session
$ istioctl create-remote-secret \
  --context="${CTX_CLUSTER2}" \
  --name=cluster2 | \
  oc --context="${CTX_CLUSTER1}" apply -f -
```

Wait for the`Istio` resource to return the "Ready" status condition by running the following command:

```shell-session
$ oc --context "${CTX_CLUSTER2}" wait --for condition=Ready istio/default --timeout=3m
```

Create an East-West gateway on the West cluster by running the following command:

```shell-session
$ oc --context "${CTX_CLUSTER2}" apply -f https://raw.githubusercontent.com/istio-ecosystem/sail-operator/main/docs/deployment-models/resources/east-west-gateway-net2.yaml
```

Note

Since the West cluster is installed with a remote profile, exposing the application services on the East cluster exposes them on the East-West gateways of both clusters.

### 6.5. Installing Kiali in a multi-cluster meshCopy link

Install Kiali in a multi-cluster mesh configuration on two OpenShift Container Platform clusters.

Note

In this procedure,`CLUSTER1` is the East cluster and`CLUSTER2` is the West cluster.

You can adapt these instructions for a mesh spanning more than two clusters.

Prerequisites

- You have installed the latest Kiali Operator on each cluster.
- Istio installed in a multi-cluster configuration on each cluster.
- You have`istioctl` installed on the laptop you can use to run these instructions.
- You are logged in to the OpenShift Container Platform web console as a user with the`cluster-admin` role.
- You have configured a metrics store so that Kiali can query metrics from all the clusters. Kiali queries metrics and traces from their respective endpoints.

Procedure

Install Kiali on the East cluster:

Create a YAML file named`kiali.yaml` that creates a namespace for the Kiali deployment.

Example configuration

```yaml
apiVersion: kiali.io/v1alpha1
kind: Kiali
metadata:
  name: kiali
  namespace: istio-system
spec:
  version: default
  external_services:
    prometheus:
      auth:
        type: bearer
        use_kiali_token: true
      thanos_proxy:
        enabled: true
      url: https://thanos-querier.openshift-monitoring.svc.cluster.local:9091
```

Note

The endpoint for this example uses OpenShift Monitoring to configure metrics. For more information, see "Configuring OpenShift Monitoring with Kiali".

Apply the YAML file on the East cluster by running the following command:

```shell-session
$ oc --context cluster1 apply -f kiali.yaml
```

Example output

```shell-session
kiali-istio-system.apps.example.com
```

Ensure that the Kiali custom resource (CR) is ready by running the following command:

```shell-session
$ oc wait --context cluster1 --for=condition=Successful kialis/kiali -n istio-system --timeout=3m
```

Example output

```shell-session
kiali.kiali.io/kiali condition met
```

Display your Kiali Route hostname.

```shell-session
$ oc --context cluster1 get route kiali -n istio-system -o jsonpath='{.spec.host}'
```

Create a Kiali CR on the West cluster.

Example configuration

```yaml
apiVersion: kiali.io/v1alpha1
kind: Kiali
metadata:
  name: kiali
  namespace: istio-system
spec:
  version: default
  auth:
    openshift:
      redirect_uris:
        # Replace kiali-route-hostname with the hostname from the previous step.
        - "https://{kiali-route-hostname}/api/auth/callback/cluster2"
  deployment:
    remote_cluster_resources_only: true
```

The Kiali Operator creates the resources necessary for the Kiali server on the East cluster to connect to the West cluster. The Kiali server is not installed on the West cluster.

Apply the YAML file on the West cluster by running the following command:

```shell-session
$ oc --context cluster2 apply -f kiali-remote.yaml
```

Ensure that the Kiali CR is ready by running the following command:

```shell-session
$ oc wait --context cluster2 --for=condition=Successful kialis/kiali -n istio-system --timeout=3m
```

Create a remote cluster secret so that Kiali installation in the East cluster can access the West cluster.

Create a long lived API token bound to the kiali-service-account in the West cluster. Kiali uses this token to authenticate to the West cluster.

Example configuration

```yaml
apiVersion: v1
kind: Secret
metadata:
  name: "kiali-service-account"
  namespace: "istio-system"
  annotations:
    kubernetes.io/service-account.name: "kiali-service-account"
type: kubernetes.io/service-account-token
```

Apply the YAML file on the West cluster by running the following command:

```shell-session
$ oc --context cluster2 apply -f kiali-svc-account-token.yaml
```

Create a`kubeconfig` file and save it as a secret in the namespace on the East cluster where the Kiali deployment resides.

To simplify this process, use the`kiali-prepare-remote-cluster.sh` script to generate the`kubeconfig` file by running the following`curl` command:

```shell-session
$ curl -L -o kiali-prepare-remote-cluster.sh https://raw.githubusercontent.com/kiali/kiali/master/hack/istio/multicluster/kiali-prepare-remote-cluster.sh
```

Modify the script to make it executeable by running the following command:

```shell-session
chmod +x kiali-prepare-remote-cluster.sh
```

Execute the script so that it passes the East and West cluster contexts to the`kubeconfig` file by running the following command:

```shell-session
$ ./kiali-prepare-remote-cluster.sh --kiali-cluster-context cluster1 --remote-cluster-context cluster2 --view-only false --kiali-resource-name kiali-service-account --remote-cluster-namespace istio-system --process-kiali-secret true --process-remote-resources false --remote-cluster-name cluster2
```

Note

Use the`--help` option to display additional details about how to use the script.

Trigger the reconciliation loop so that the Kiali Operator registers the remote secret that the CR contains by running the following command:

```shell-session
$ oc --context cluster1 annotate kiali kiali -n istio-system --overwrite kiali.io/reconcile="$(date)"
```

Wait for Kiali resource to become ready by running the following command:

```shell-session
oc --context cluster1 wait --for=condition=Successful --timeout=2m kialis/kiali -n istio-system
```

Wait for Kiali server to become ready by running the following command:

```shell-session
oc --context cluster1 rollout status deployments/kiali -n istio-system
```

Log in to Kiali.

Display the hostname of the Kiali route by running the following command:

```shell-session
oc --context cluster1 get route kiali -n istio-system -o jsonpath='{.spec.host}'
```

1. When you first access Kiali, log in to the cluster that contains the Kiali deployment. In this example, access the`East` cluster.
2. Navigate to the Kiali URL in your browser: [">https://](https://).

Log in to the West cluster through Kiali.

In order to see other clusters in the Kiali UI, you must first login as a user to those clusters through Kiali.

1. Click on the user profile dropdown in the top right hand menu.
2. Select Login to West. You are redirected to an OpenShift login page and prompted for credentials for the West cluster.

Verify that Kiali shows information from both clusters.

1. Click Overview and verify that you can see namespaces from both clusters.
2. Click Navigate and verify that you see both clusters on the mesh graph.

## Chapter 7. Deploying multiple service meshes on a single clusterCopy link

You can use the Red Hat OpenShift Service Mesh to operate many service meshes in a single cluster, with each mesh managed by a separate control plane. Using discovery selectors and revisions prevents conflicts between control planes.

### 7.1. About deploying multiple control planesCopy link

To configure a cluster to host two control planes, set up separate Istio resources with unique names in independent Istio system namespaces. Assign a unique revision name to each Istio resource to identify the control planes, workloads, or namespaces it manages. Apply these revision names using injection or`istio.io/rev` labels to specify which control plane injects the sidecar proxy into application pods.

Each`Istio` resource must also configure discovery selectors to specify which namespaces the Istio control plane observes. Only namespaces with labels that match the configured discovery selectors can join the mesh. Additionally, discovery selectors determine which control plane creates the`istio-ca-root-cert` config map in each namespace, which is used to encrypt traffic between services with mutual TLS within each mesh.

When adding an additional Istio control plane to a cluster with an existing control plane, ensure that the existing`Istio` instance has discovery selectors configured to avoid overlapping with the new control plane.

Note

Only one`IstioCNI` resource is shared by all control planes in a cluster, and you must update this resource independent of other cluster resources.

### 7.2. Using multiple control planes on a single clusterCopy link

You can use discovery selectors to limit the visibility of an Istio control plane to specific namespaces in a cluster. By combining discovery selectors with control plane revisions, you can deploy multiple control planes in a single cluster, ensuring that each control plane manages only its assigned namespaces. This approach avoids conflicts between control planes and enables soft multi-tenancy for service meshes.

#### 7.2.1. Deploying the first control planeCopy link

You deploy the first control plane by creating its assigned namespace.

Prerequisites

You have created an Istio Container Network Interface (CNI) resource.

Note

You can run the following command to check for existing`Istio` instances:

```shell-session
$ oc get istios
```

- You have installed the OpenShift Service Mesh operator.
- You have installed the`istioctl` binary on your localhost.

Note

You can have extended support for more than two control planes. The maximum number of service meshes in a single cluster depends on the available cluster resources.

Procedure

Create the namespace for the first Istio control plane called`istio-system-1` by running the following command:

```shell-session
$ oc new-project istio-system-1
```

Add the following label to the first namespace, which is used with the Istio`discoverySelectors` field by running the following command:

```shell-session
$ oc label namespace istio-system-1 istio-discovery=mesh-1
```

Create a YAML file named`istio-1.yaml` with the name`mesh-1` and the`discoverySelector` as`mesh-1`:

Example configuration

```yaml
kind: Istio
apiVersion: sailoperator.io/v1
metadata:
  name: mesh-1
spec:
  namespace: istio-system-1
  values:
    meshConfig:
      discoverySelectors:
        - matchLabels:
            istio-discovery: mesh-1
# ...
```

Create the first`Istio` resource by running the following command:

```shell-session
$ oc apply -f istio-1.yaml
```

To restrict workloads in`mesh-1` from communicating freely with decrypted traffic between meshes, deploy a`PeerAuthentication` resource to enforce mutual TLS (mTLS) traffic within the`mesh-1` data plane. Apply the`PeerAuthentication` resource in the`istio-system-1` namespace by using a configuration file, such as`peer-auth-1.yaml`:

```shell-session
$ oc apply -f peer-auth-1.yaml
```

Example configuration

```yaml
apiVersion: security.istio.io/v1
kind: PeerAuthentication
metadata:
  name: "mesh-1-peerauth"
  namespace: "istio-system-1"
spec:
  mtls:
    mode: STRICT
```

#### 7.2.2. Deploying the second control planeCopy link

After deploying the first control plane, you can deploy the second control plane by creating its assigned namespace.

Procedure

Create a namespace for the second Istio control plane called`istio-system-2` by running the following command:

```shell-session
$ oc new-project istio-system-2
```

Add the following label to the second namespace, which is used with the Istio`discoverySelectors` field by running the following command:

```shell-session
$ oc label namespace istio-system-2 istio-discovery=mesh-2
```

Create a YAML file named`istio-2.yaml`:

Example configuration

```yaml
kind: Istio
apiVersion: sailoperator.io/v1
metadata:
  name: mesh-2
spec:
  namespace: istio-system-2
  values:
    meshConfig:
      discoverySelectors:
        - matchLabels:
            istio-discovery: mesh-2
# ...
```

Create the second`Istio` resource by running the following command:

```shell-session
$ oc apply -f istio-2.yaml
```

Deploy a policy for workloads in the`istio-system-2` namespace to only accept mutual TLS traffic`peer-auth-2.yaml` by running the following command:

```shell-session
$ oc apply -f peer-auth-2.yaml
```

Example configuration

```yaml
apiVersion: security.istio.io/v1
kind: PeerAuthentication
metadata:
  name: "mesh-2-peerauth"
  namespace: "istio-system-2"
spec:
  mtls:
    mode: STRICT
```

#### 7.2.3. Verifying multiple control planesCopy link

Verify that both of the Istio control planes are deployed and running properly. You can validate that the`istiod` pod is successfully running in each Istio system namespace.

Verify that the workloads are assigned to the control plane in`istio-system-1` by running the following command:

```shell-session
$ oc get pods -n istio-system-1
```

Example output

```shell-session
NAME                            READY   STATUS    RESTARTS   AGE
istiod-mesh-1-b69646b6f-kxrwk   1/1     Running   0          4m14s
```

Verify that the workloads are assigned to the control plane in`istio-system-2` by running the following command:

```shell-session
$ oc get pods -n istio-system-2
```

Example output

```shell-session
NAME                            READY   STATUS    RESTARTS   AGE
istiod-mesh-2-8666fdfc6-mqp45   1/1     Running   0          118s
```

### 7.3. Deploy application workloads in each meshCopy link

To deploy application workloads, assign each workload to a separate namespace.

Procedure

Create an application namespace called`app-ns-1` by running the following command:

```shell-session
$ oc create namespace app-ns-1
```

To ensure that the namespace is discovered by the first control plane, add the`istio-discovery=mesh-1` label by running the following command:

```shell-session
$ oc label namespace app-ns-1 istio-discovery=mesh-1
```

To enable sidecar injection into all the pods by default while ensuring that pods in this namespace are mapped to the first control plane, add the`istio.io/rev=mesh-1` label to the namespace by running the following command:

```shell-session
$ oc label namespace app-ns-1 istio.io/rev=mesh-1
```

Optional: You can verify the`mesh-1` revision name by running the following command:

```shell-session
$ oc get istiorevisions
```

Deploy the`sleep` and`httpbin` applications by running the following command:

```shell-session
$ oc apply -n app-ns-1 \
   -f https://raw.githubusercontent.com/openshift-service-mesh/istio/release-1.24/samples/sleep/sleep.yaml \
   -f https://raw.githubusercontent.com/openshift-service-mesh/istio/release-1.24/samples/httpbin/httpbin.yaml
```

Wait for the`httpbin` and`sleep` pods to run with sidecars injected by running the following command:

```shell-session
$ oc get pods -n app-ns-1
```

Example output

```shell-session
NAME                       READY   STATUS    RESTARTS   AGE
httpbin-7f56dc944b-kpw2x   2/2     Running   0          2m26s
sleep-5577c64d7c-b5wd2     2/2     Running   0          91m
```

Create a second application namespace called`app-ns-2` by running the following command:

```shell-session
$ oc create namespace app-ns-2
```

Create a third application namespace called`app-ns-3` by running the following command:

```shell-session
$ oc create namespace app-ns-3
```

Add the label`istio-discovery=mesh-2` to both namespaces and the revision label`mesh-2` to match the discovery selector of the second control plane by running the following command:

```shell-session
$ oc label namespace app-ns-2 app-ns-3 istio-discovery=mesh-2 istio.io/rev=mesh-2
```

Deploy the`sleep` and`httpbin` applications to the`app-ns-2` namespace by running the following command:

```shell-session
$ oc apply -n app-ns-2 \
   -f https://raw.githubusercontent.com/openshift-service-mesh/istio/release-1.24/samples/sleep/sleep.yaml \
   -f https://raw.githubusercontent.com/openshift-service-mesh/istio/release-1.24/samples/httpbin/httpbin.yaml
```

Deploy the`sleep` and`httpbin` applications to the`app-ns-3` namespace by running the following command:

```shell-session
$ oc apply -n app-ns-3 \
   -f https://raw.githubusercontent.com/openshift-service-mesh/istio/release-1.24/samples/sleep/sleep.yaml \
   -f https://raw.githubusercontent.com/openshift-service-mesh/istio/release-1.24/samples/httpbin/httpbin.yaml
```

Optional: Use the following command to wait for a deployment to be available:

```shell-session
$ oc wait deployments -n app-ns-2 --all --for condition=Available
```

Verification

Verify that each application workload is managed by its assigned control plane by using the`istioctl ps` command after deploying the applications:

Verify that the workloads are assigned to the control plane in`istio-system-1` by running the following command:

```shell-session
$ istioctl ps -i istio-system-1
```

Example output

```shell-session
NAME                                  CLUSTER        CDS              LDS              EDS              RDS              ECDS        ISTIOD                            VERSION
httpbin-7f56dc944b-vwfm5.app-ns-1     Kubernetes     SYNCED (11m)     SYNCED (11m)     SYNCED (11m)     SYNCED (11m)     IGNORED     istiod-mesh-1-b69646b6f-kxrwk     1.23.0
sleep-5577c64d7c-d675f.app-ns-1       Kubernetes     SYNCED (11m)     SYNCED (11m)     SYNCED (11m)     SYNCED (11m)     IGNORED     istiod-mesh-1-b69646b6f-kxrwk     1.23.0
```

Verify that the workloads are assigned to the control plane in`istio-system-2` by running the following command:

```shell-session
$ istioctl ps -i istio-system-2
```

Example output

```shell-session
NAME                                  CLUSTER        CDS                LDS                EDS                RDS                ECDS        ISTIOD                            VERSION
httpbin-7f56dc944b-54gjs.app-ns-3     Kubernetes     SYNCED (3m59s)     SYNCED (3m59s)     SYNCED (3m59s)     SYNCED (3m59s)     IGNORED     istiod-mesh-2-8666fdfc6-mqp45     1.23.0
httpbin-7f56dc944b-gnh72.app-ns-2     Kubernetes     SYNCED (4m1s)      SYNCED (4m1s)      SYNCED (3m59s)     SYNCED (4m1s)      IGNORED     istiod-mesh-2-8666fdfc6-mqp45     1.23.0
sleep-5577c64d7c-k9mxz.app-ns-2       Kubernetes     SYNCED (4m1s)      SYNCED (4m1s)      SYNCED (3m59s)     SYNCED (4m1s)      IGNORED     istiod-mesh-2-8666fdfc6-mqp45     1.23.0
sleep-5577c64d7c-m9hvm.app-ns-3       Kubernetes     SYNCED (4m1s)      SYNCED (4m1s)      SYNCED (3m59s)     SYNCED (4m1s)      IGNORED     istiod-mesh-2-8666fdfc6-mqp45     1.23.0
```

Verify that the application connectivity is restricted to workloads within their respective mesh:

Send a request from the`sleep` pod in`app-ns-1` to the`httpbin` service in`app-ns-2` to check that the communication fails by running the following command:

```shell-session
$ oc -n app-ns-1 exec deploy/sleep -c sleep -- curl -sIL http://httpbin.app-ns-2.svc.cluster.local:8000
```

The`PeerAuthentication` resources created earlier enforce mutual TLS (mTLS) traffic in`STRICT` mode within each mesh. Each mesh uses its own root certificate, managed by the`istio-ca-root-cert` config map, which prevents communication between meshes. The output indicates a communication failure, similar to the following example:

Example output

```shell-session
HTTP/1.1 503 Service Unavailable
content-length: 95
content-type: text/plain
date: Wed, 16 Oct 2024 12:05:37 GMT
server: envoy
```

Confirm that the communication works by sending a request from the`sleep` pod to the`httpbin` service that are present in the`app-ns-2` namespace which is managed by`mesh-2`. Run the following command:

```shell-session
$ oc -n app-ns-2 exec deploy/sleep -c sleep -- curl -sIL http://httpbin.app-ns-3.svc.cluster.local:8000
```

Example output

```shell-session
HTTP/1.1 200 OK
access-control-allow-credentials: true
access-control-allow-origin: *
content-security-policy: default-src 'self'; style-src 'self' 'unsafe-inline'; img-src 'self' camo.githubusercontent.com
content-type: text/html; charset=utf-8
date: Wed, 16 Oct 2024 12:06:30 GMT
x-envoy-upstream-service-time: 8
server: envoy
transfer-encoding: chunked
```

## Chapter 8. External control plane topologyCopy link

You can use the external control plane topology to isolate the control plane from the data plane on separate clusters.

### 8.1. About external control plane topologyCopy link

The external control plane topology improves security and allows the Service Mesh to be hosted as a service. In this installation configuration one cluster hosts and manages the Istio control plane, and applications are hosted on other clusters.

#### 8.1.1. Installing the control plane and data plane on separate clustersCopy link

Install Istio on a control plane cluster and a separate data plane cluster. This installation approach provides increased security.

Note

You can adapt these instructions for a mesh spanning more than one data plane cluster. You can also adapt these instructions for multiple meshes with multiple control planes on the same control plane cluster.

Prerequisites

- You have installed the OpenShift Service Mesh Operator on the control plane cluster and the data plane cluster.
- You have`istioctl` installed on the laptop you will use to run these instructions.

Procedure

Create an`ISTIO_VERSION` environment variable that defines the Istio version to install on all the clusters by running the following command:

```shell-session
$ export ISTIO_VERSION=1.24.3
```

Create a`REMOTE_CLUSTER_NAME` environment variable that defines the name of the cluster by running the following command:

```shell-session
$ export REMOTE_CLUSTER_NAME=cluster1
```

Set up the environment variable that contains the`oc` command context for the control plane cluster by running the following command:

```shell-session
$ export CTX_CONTROL_PLANE_CLUSTER=<context_name_of_the_control_plane_cluster>
```

Set up the environment variable that contains the`oc` command context for the data plane cluster by running the following command:

```shell-session
$ export CTX_DATA_PLANE_CLUSTER=<context_name_of_the_data_plane_cluster>
```

Set up the ingress gateway for the control plane:

Create a project called`istio-system` by running the following command:

```shell-session
$ oc get project istio-system --context "${CTX_CONTROL_PLANE_CLUSTER}" || oc new-project istio-system --context "${CTX_CONTROL_PLANE_CLUSTER}"
```

Create an`Istio` resource on the control plane cluster to manage the ingress gateway by running the following command:

```shell-session
$ cat <<EOF | oc --context "${CTX_CONTROL_PLANE_CLUSTER}" apply -f -
apiVersion: sailoperator.io/v1
kind: Istio
metadata:
  name: default
spec:
  version: v${ISTIO_VERSION}
  namespace: istio-system
  value:
    global:
      network: network1
EOF
```

Create the ingress gateway for the control plane by running the following command:

```shell-session
$ oc --context "${CTX_CONTROL_PLANE_CLUSTER}" apply -f https://raw.githubusercontent.com/istio-ecosystem/sail-operator/main/docs/deployment-models/resources/controlplane-gateway.yaml
```

Get the assigned IP address for the ingress gateway by running the following command:

```shell-session
$ oc --context "${CTX_CONTROL_PLANE_CLUSTER}" get svc istio-ingressgateway -n istio-system -o jsonpath='{.status.loadBalancer.ingress[0].ip}'
```

Store the IP address of the ingress gateway in an environment variable by running the following command:

```shell-session
$ export EXTERNAL_ISTIOD_ADDR=$(oc -n istio-system --context="${CTX_CONTROL_PLANE_CLUSTER}" get svc istio-ingressgateway -o jsonpath='{.status.loadBalancer.ingress[0].ip}')
```

Install Istio on the data plane cluster:

Create a project called`external-istiod` on the data plane cluster by running the following command:

```shell-session
$ oc get project external-istiod --context "${CTX_DATA_PLANE_CLUSTER}" || oc new-project external-istiod --context "${CTX_DATA_PLANE_CLUSTER}"
```

Create an`Istio` resource on the data plane cluster by running the following command:

```shell-session
$ cat <<EOF | oc --context "${CTX_DATA_PLANE_CLUSTER}" apply -f -
apiVersion: sailoperator.io/v1
kind: Istio
metadata:
  name: external-istiod
spec:
  version: v${ISTIO_VERSION}
  namespace: external-istiod
  profile: remote
  values:
    defaultRevision: external-istiod
    global:
      remotePilotAddress: ${EXTERNAL_ISTIOD_ADDR}
      configCluster: true 
```

1

```shell-session

    pilot:
      configMap: true
      istiodRemote:
        injectionPath: /inject/cluster/cluster2/net/network1
EOF
```

1

This setting identifies the data plane cluster as the source of the mesh configuration.

Create a project called`istio-cni` on the data plane cluster by running the following command:

```shell-session
$ oc get project istio-cni --context "${CTX_DATA_PLANE_CLUSTER}" || oc new-project istio-cni --context "${CTX_DATA_PLANE_CLUSTER}"
```

Create an`IstioCNI` resource on the data plane cluster by running the following command:

```shell-session
$ cat <<EOF | oc --context "${CTX_DATA_PLANE_CLUSTER}" apply -f -
apiVersion: sailoperator.io/v1
kind: IstioCNI
metadata:
  name: default
spec:
  version: v${ISTIO_VERSION}
  namespace: istio-cni
EOF
```

Set up the external Istio control plane on the control plane cluster:

Create a project called`external-istiod` on the control plane cluster by running the following command:

```shell-session
$ oc get project external-istiod --context "${CTX_CONTROL_PLANE_CLUSTER}" || oc new-project external-istiod --context "${CTX_CONTROL_PLANE_CLUSTER}"
```

Create a`ServiceAccount` resource on the control plane cluster by running the following command:

```shell-session
$ oc --context="${CTX_CONTROL_PLANE_CLUSTER}" create serviceaccount istiod-service-account -n external-istiod
```

Store the API server address for the data plane cluster in an environment variable by running the following command:

```shell-session
$ DATA_PLANE_API_SERVER=https://<hostname_or_IP_address_of_the_API_server_for_the_data_plane_cluster>:6443
```

Install a remote secret on the control plane cluster that provides access to the API server on the data plane cluster by running the following command:

```shell-session
$ istioctl create-remote-secret \
  --context="${CTX_DATA_PLANE_CLUSTER}" \
  --type=config \
  --namespace=external-istiod \
  --service-account=istiod-external-istiod \
  --create-service-account=false \
  --server="${DATA_PLANE_API_SERVER}" | \
  oc --context="${CTX_CONTROL_PLANE_CLUSTER}" apply -f -
```

Create an`Istio` resource on the control plane cluster by running the following command:

```shell-session
$ cat <<EOF | oc --context "${CTX_CONTROL_PLANE_CLUSTER}" apply -f -
apiVersion: sailoperator.io/v1
kind: Istio
metadata:
  name: external-istiod
spec:
  version: v${ISTIO_VERSION}
  namespace: external-istiod
  profile: empty
  values:
    meshConfig:
      rootNamespace: external-istiod
      defaultConfig:
        discoveryAddress: $EXTERNAL_ISTIOD_ADDR:15012
    pilot:
      enabled: true
      volumes:
        - name: config-volume
          configMap:
            name: istio-external-istiod
        - name: inject-volume
          configMap:
            name: istio-sidecar-injector-external-istiod
      volumeMounts:
        - name: config-volume
          mountPath: /etc/istio/config
        - name: inject-volume
          mountPath: /var/lib/istio/inject
      env:
        INJECTION_WEBHOOK_CONFIG_NAME: "istio-sidecar-injector-external-istiod-external-istiod"
        VALIDATION_WEBHOOK_CONFIG_NAME: "istio-validator-external-istiod-external-istiod"
        EXTERNAL_ISTIOD: "true"
        LOCAL_CLUSTER_SECRET_WATCHER: "true"
        CLUSTER_ID: cluster2
        SHARED_MESH_CONFIG: istio
    global:
      caAddress: $EXTERNAL_ISTIOD_ADDR:15012
      configValidation: false
      meshID: mesh1
      multiCluster:
        clusterName: cluster2
      network: network1
EOF
```

Create`Gateway` and`VirtualService` resources so that the sidecar proxies on the data plane cluster can access the control plane by running the following command:

```shell-session
$ oc --context "${CTX_CONTROL_PLANE_CLUSTER}" apply -f - <<EOF
apiVersion: networking.istio.io/v1
kind: Gateway
metadata:
  name: external-istiod-gw
  namespace: external-istiod
spec:
  selector:
    istio: ingressgateway
  servers:
    - port:
        number: 15012
        protocol: tls
        name: tls-XDS
      tls:
        mode: PASSTHROUGH
      hosts:
      - "*"
    - port:
        number: 15017
        protocol: tls
        name: tls-WEBHOOK
      tls:
        mode: PASSTHROUGH
      hosts:
      - "*"
---
apiVersion: networking.istio.io/v1
kind: VirtualService
metadata:
  name: external-istiod-vs
  namespace: external-istiod
spec:
    hosts:
    - "*"
    gateways:
    - external-istiod-gw
    tls:
    - match:
      - port: 15012
        sniHosts:
        - "*"
      route:
      - destination:
          host: istiod-external-istiod.external-istiod.svc.cluster.local
          port:
            number: 15012
    - match:
      - port: 15017
        sniHosts:
        - "*"
      route:
      - destination:
          host: istiod-external-istiod.external-istiod.svc.cluster.local
          port:
            number: 443
EOF
```

Wait for the`external-istiod``Istio` resource on the control plane cluster to return the "Ready" status condition by running the following command:

```shell-session
$ oc --context "${CTX_CONTROL_PLANE_CLUSTER}" wait --for condition=Ready istio/external-istiod --timeout=3m
```

Wait for the`Istio` resource on the data plane cluster to return the "Ready" status condition by running the following command:

```shell-session
$ oc --context "${CTX_DATA_PLANE_CLUSTER}" wait --for condition=Ready istio/external-istiod --timeout=3m
```

Wait for the`IstioCNI` resource on the data plane cluster to return the "Ready" status condition by running the following command:

```shell-session
$ oc --context "${CTX_DATA_PLANE_CLUSTER}" wait --for condition=Ready istiocni/default --timeout=3m
```

Verification

Deploy sample applications on the data plane cluster:

Create a namespace for sample applications on the data plane cluster by running the following command:

```shell-session
$ oc --context "${CTX_DATA_PLANE_CLUSTER}" get project sample || oc --context="${CTX_DATA_PLANE_CLUSTER}" new-project sample
```

Label the namespace for the sample applications to support sidecar injection by running the following command:

```shell-session
$ oc --context="${CTX_DATA_PLANE_CLUSTER}" label namespace sample istio.io/rev=external-istiod
```

Deploy the`helloworld` application:

Create the`helloworld` service by running the following command:

```shell-session
$ oc --context="${CTX_DATA_PLANE_CLUSTER}" apply \
  -f https://raw.githubusercontent.com/istio/istio/${ISTIO_VERSION}/samples/helloworld/helloworld.yaml \
  -l service=helloworld -n sample
```

Create the`helloworld-v1` deployment by running the following command:

```shell-session
$ oc --context="${CTX_DATA_PLANE_CLUSTER}" apply \
  -f https://raw.githubusercontent.com/istio/istio/${ISTIO_VERSION}/samples/helloworld/helloworld.yaml \
  -l version=v1 -n sample
```

Deploy the`sleep` application by running the following command:

```shell-session
$ oc --context="${CTX_DATA_PLANE_CLUSTER}" apply \
  -f https://raw.githubusercontent.com/istio/istio/${ISTIO_VERSION}/samples/sleep/sleep.yaml -n sample
```

Verify that the pods on the`sample` namespace have a sidecar injected by running the following command:

```shell-session
$ oc --context="${CTX_DATA_PLANE_CLUSTER}" get pods -n sample
```

The terminal should return`2/2` for each pod on the`sample` namespace by running the following command:

Example output

```shell-session
NAME                             READY   STATUS    RESTARTS   AGE
helloworld-v1-6d65866976-jb6qc   2/2     Running   0          1m
sleep-5fcd8fd6c8-mg8n2           2/2     Running   0          1m
```

Verify that internal traffic can reach the applications on the cluster:

Verify a request can be sent to the`helloworld` application through the`sleep` application by running the following command:

```shell-session
$ oc exec --context="${CTX_DATA_PLANE_CLUSTER}" -n sample -c sleep deploy/sleep -- curl -sS helloworld.sample:5000/hello
```

The terminal should return a response from the`helloworld` application:

Example output

```shell-session
Hello version: v1, instance: helloworld-v1-6d65866976-jb6qc
```

Install an ingress gateway to expose the sample application to external clients:

Create the ingress gateway by running the following command:

```shell-session
$ oc --context="${CTX_DATA_PLANE_CLUSTER}" apply
-f https://raw.githubusercontent.com/istio-ecosystem/sail-operator/refs/heads/main/chart/samples/ingress-gateway.yaml -n sample
```

Confirm that the ingress gateway is running by running the following command:

```shell-session
$ oc get pod -l app=istio-ingressgateway -n sample --context="${CTX_DATA_PLANE_CLUSTER}"
```

The terminal should return output confirming that the gateway is running:

Example output

```shell-session
NAME                                    READY   STATUS    RESTARTS   AGE
istio-ingressgateway-7bcd5c6bbd-kmtl4   1/1     Running   0          8m4s
```

Expose the`helloworld` application through the ingress gateway by running the following command:

```shell-session
$ oc apply -f https://raw.githubusercontent.com/istio/istio/refs/heads/master/samples/helloworld/helloworld-gateway.yaml -n sample --context="${CTX_DATA_PLANE_CLUSTER}"
```

Set the gateway URL environment variable by running the following command:

```shell-session
$ export INGRESS_HOST=$(oc -n sample --context="${CTX_DATA_PLANE_CLUSTER}" get service istio-ingressgateway -o jsonpath='{.status.loadBalancer.ingress[0].ip}'); \
  export INGRESS_PORT=$(oc -n sample --context="${CTX_DATA_PLANE_CLUSTER}" get service istio-ingressgateway -o jsonpath='{.spec.ports[?(@.name=="http2")].port}'); \
  export GATEWAY_URL=$INGRESS_HOST:$INGRESS_PORT
```

Verify that external traffic can reach the applications on the mesh:

Confirm that the`helloworld` application is accessible through the gateway by running the following command:

```shell-session
$ curl -s "http://${GATEWAY_URL}/hello"
```

The`helloworld` application should return a response.

Example output

```shell-session
Hello version: v1, instance: helloworld-v1-6d65866976-jb6qc
```

## Chapter 9. Istioctl toolCopy link

Use the`istioctl` command line utility to perform diagnostic and debugging tasks for OpenShift Service Mesh 3 service mesh components.

### 9.1. Support for IstioctlCopy link

OpenShift Service Mesh 3 supports a selection of Istioctl commands.

Expand

`admin`

Manage the control plane (`istiod`) configuration

`analyze`

Analyze the Istio configuration and print validation messages

`completion`

Generate the autocompletion script for the specified shell

`create-remote-secret`

Create a secret with credentials to allow Istio to access remote Kubernetes API servers

`help`

Display help about any command

`proxy-config`,`pc`

Retrieve information about the proxy configuration from Envoy (Kubernetes only)

`proxy-status`,`ps`

Retrieve the synchronization status of each Envoy in the mesh

`remote-clusters`

List the remote clusters each`istiod` instance is connected to

`validate`,`v`

Validate the Istio policy and rules files

`version`

Print out the build version information

`waypoint`

Manage the waypoint configuration

`ztunnel-config`

Update or retrieve the current Ztunnel configuration.

| Table 9.1. Supported Istioctl commands | Command | Description |
| --- | --- | --- |

Show more

Note

Any other commands display the WARNING: Not supported in OpenShift Service Mesh context message. Do not use these commands in production environments.

### 9.2. Installing the Istioctl toolCopy link

Install the`istioctl` command-line utility to debug and diagnose Istio service mesh deployments.

Prerequisites

- You have access to the OpenShift Container Platform web console.
- The OpenShift Service Mesh 3 Operator is installed and running.
- You have created at least one`Istio` resource.

Procedure

Confirm which version of the`Istio` resource runs on the installation by running the following command:

```shell-session
$ oc get istio -ojsonpath="{range .items[*]}{.spec.version}{'\n'}{end}" | sed s/^v// | sort
```

If there are multiple`Istio` resources with different versions, choose the latest version. The latest version is displayed last.

Click Download istioctl. Choose the version and architecture that matches your system.

- [Linux (x86_64, amd64)](https://mirror.openshift.com/pub/cgw/servicemesh/latest/istioctl-1.24.4-linux-amd64.tar.gz)
- [Linux on ARM (aarch64, arm64)](https://mirror.openshift.com/pub/cgw/servicemesh/latest/istioctl-1.24.4-linux-arm64.tar.gz)
- [MacOS (x86_64, amd64)](https://mirror.openshift.com/pub/cgw/servicemesh/latest/istioctl-1.24.4-darwin-amd64.tar.gz)
- [MacOS on ARM (aarch64, arm64)](https://mirror.openshift.com/pub/cgw/servicemesh/latest/istioctl-1.24.4-darwin-arm64.tar.gz)
- [Windows (x86_64, amd64)](https://mirror.openshift.com/pub/cgw/servicemesh/latest/istioctl-1.24.4-windows-amd64.zip)

Extract the`istioctl` binary file.

If you are using a Linux operating system, run the following command:

```shell-session
$ tar xzf istioctl-<VERSION>-<OS>-<ARCH>.tar.gz
```

1. If you are using an Apple Mac operating system, unpack and extract the archive.
2. If you are using a Microsoft Windows operating system, use the zip software to extract the archive.

Move to the uncompressed directory by running the following command:

```shell-session
$ cd istioctl-<VERSION>-<OS>-<ARCH>
```

Add the`istioctl` client to the path by running the following command:

```shell-session
$ export PATH=$PWD:$PATH
```

Confirm that the`istioctl` client version and the Istio control plane version match or are within one version by running the following command:

```shell-session
$ istioctl version
```

Sample output:

```shell-session
client version: 1.20.0
control plane version: 1.24.3_ossm
data plane version: none
```

## Chapter 10. Enabling mutual Transport Layer SecurityCopy link

You can use Red Hat OpenShift Service Mesh for your application to customize the communication security between the complex array of microservices. Mutual Transport Layer Security (mTLS) is a protocol that enables two parties to authenticate each other.

### 10.1. About mutual Transport Layer Security (mTLS)Copy link

In OpenShift Service Mesh 3, you use the`Istio` resource instead of the`ServiceMeshControlPlane` resource to configure mTLS settings.

In OpenShift Service Mesh 3, you configure`STRICT` mTLS mode by using the`PeerAuthentication` and`DestinationRule` resources. You set TLS protocol versions through Istio Workload Minimum TLS Version Configuration.

Review the following`Istio` resources and concepts to configure mTLS settings properly:

`PeerAuthentication` defines the type of mTLS traffic a sidecar accepts. In`PERMISSIVE` mode, both plaintext and mTLS traffic are accepted. In`STRICT` mode, only mTLS traffic is allowed.`DestinationRule` configures the type of TLS traffic a sidecar sends. In`DISABLE` mode, the sidecar sends plaintext. In`SIMPLE`,`MUTUAL`, and`ISTIO_MUTUAL` modes, the sidecar establishes a TLS connection.`Auto mTLS` ensures that all inter-mesh traffic is encrypted with mTLS by default, regardless of the`PeerAuthentication` mode configuration.`Auto mTLS` is controlled by the global mesh configuration field`enableAutoMtls`, which is enabled by default in OpenShift Service Mesh 2 and 3. The mTLS setting operates entirely between sidecar proxies, requiring no changes to application or service code.

By default,`PeerAuthentication` is set to`PERMISSIVE` mode, allowing sidecars in the Service Mesh to accept both plain-text and mTLS-encrypted traffic.

### 10.2. Enabling strict mTLS mode by using the namespaceCopy link

You can restrict workloads to accept only encrypted mTLS traffic by enabling the`STRICT` mode in`PeerAuthentication`.

Example`PeerAuthentication` policy for a namespace

```yaml
apiVersion: security.istio.io/v1
kind: PeerAuthentication
metadata:
  name: default
  namespace: <namespace>
spec:
  mtls:
    mode: STRICT
```

You can enable mTLS for all destination hosts in the` ` by creating a`DestinationRule` resource with`MUTUAL` or`ISTIO_MUTUAL` mode when`auto mTLS` is disabled and`PeerAuthentication` is set to`STRICT` mode.

Example`DestinationRule` policy for a namespace

```yaml
apiVersion: networking.istio.io/v1
kind: DestinationRule
metadata:
  name: enable-mtls
  namespace: <namespace>
spec:
  host: "*.<namespace>.svc.cluster.local"
  trafficPolicy:
   tls:
    mode: ISTIO_MUTUAL
```

### 10.3. Enabling strict mTLS across the whole service meshCopy link

You can configure mTLS across the entire mesh by applying the`PeerAuthentication` policy to the`istiod` namespace, such as`istio-system`. The`istiod` namespace name must match to the`spec.namespace` field of your`Istio` resource.

Example`PeerAuthentication` policy for the whole mesh

```yaml
apiVersion: security.istio.io/v1
kind: PeerAuthentication
metadata:
  name: default
  namespace: istio-system
spec:
  mtls:
    mode: STRICT
```

Additionally, create a`DestinationRule` resource to disable mTLS for communication with the API server, as it does not have a sidecar. Apply similar`DestinationRule` configurations for other services without sidecars.

Example`DestinationRule` policy for the whole mesh

```yaml
apiVersion: networking.istio.io/v1
kind: DestinationRule
metadata:
  name: api-server
  namespace: istio-system
spec:
  host: kubernetes.default.svc.cluster.local
  trafficPolicy:
    tls:
      mode: DISABLE
```

### 10.4. Validating encryptions with KialiCopy link

The Kiali console offers several ways to validate whether or not your applications, services, and workloads have Mutual Transport Layer Security (mTLS) encryption enabled.

The Services Detail Overview page displays a Security icon on the graph edges where at least one request with mTLS enabled is present. Also note that Kiali displays a lock icon in the Network section next to ports that are configured for mTLS.

## Chapter 11. Post-quantum cryptographyCopy link

Post-quantum cryptography (PQC) provides cryptographic algorithms resistant to quantum computing threats, replacing traditional methods such as RSA and ECDSA that are vulnerable to quantum-based attacks.

### 11.1. About post-quantum cryptography (PQC) in service meshCopy link

Post-quantum cryptography (PQC), also known as quantum-resistant cryptography, uses encryption algorithms designed to resist attacks from quantum computers.

Quantum computers use principles of quantum mechanics to perform certain calculations significantly faster than classical computers, compromising widely used cryptographic algorithms.

Most current encryption methods rely on mathematical problems that classical computers cannot solve in a practical time. Large-scale quantum computers could solve some of these problems more efficiently, which would weaken the security of existing cryptographic systems.

In Red Hat OpenShift Service Mesh, cryptographic algorithms protect control plane and data plane communications, including mutual TLS (mTLS) between workloads. Enabling PQC strengthens these communications by introducing quantum-resistant key exchange mechanisms while maintaining compatibility with existing infrastructure.

Note

Post-quantum cryptography (PQC) algorithms are not available on OpenShift clusters running in FIPS mode.

### 11.2. Configuring service mesh with post-quantum cryptography (PQC) for gatewaysCopy link

Configure a quantum-secure gateway by using hybrid key exchange to protect service mesh ingress traffic against quantum computing threats.

Prerequisites

You have installed the following CLI tools locally:

- `oc`
- `podman`
- `curl`

Procedure

Update the`Istio` control plane to enable PQC by running the following command:

```shell-session
$ oc apply -f - <<EOF
apiVersion: sailoperator.io/v1
kind: Istio
metadata:
  name: default
spec:
  version: v1.28.5
  namespace: istio-system
  updateStrategy:
    type: InPlace
  values:
    meshConfig:
      accessLogFile: /dev/stdout
      tlsDefaults:
        ecdhCurves:
        - X25519MLKEM768
EOF
```

`spec.values.meshConfig.tlsDefaults.ecdhCurves` defines the setting that applies to all non-mesh Transport Layer Security (TLS) connections in your Istio deployment, including:

- Ingress gateways: TLS connections from external clients.
- Egress gateways: TLS connections to external services.
- External service connections: Any TLS connections to services outside the mesh.

Note

This setting does not apply to mesh-internal mutual Transport Layer Security (mTLS). Communication between services within the mesh uses the default Istio mTLS configuration.

- `spec.values.meshConfig.tlsDefaults` defines a configuration that is a mesh-wide setting that applies to all gateways and mesh-internal traffic. You cannot enable PQC algorithms for individual workloads. To use different TLS configurations for specific gateways, you must deploy separate control planes with a unique`meshConfig.tlsDefaults` settings.

### 11.3. Configuring service mesh with mesh-wide post-quantum cryptography (PQC)Copy link

Configure the Istio control plane to enforce a post-quantum cryptography (PQC) compliance policy, enabling quantum-resistant security for service mesh communications.

Prerequisites

You have installed the following CLI tools locally:

- `oc`
- `podman`
- `curl`

Procedure

Update the`Istio` control plane to enable PQC by running the following command:

```shell-session
$ oc apply -f - <<EOF
apiVersion: sailoperator.io/v1
kind: Istio
metadata:
  name: default
spec:
  version: v1.28.5
  namespace: istio-system
  updateStrategy:
    type: InPlace
  values:
    pilot:
      env:
        COMPLIANCE_POLICY: "pqc"
EOF
```

- `spec.values.pilot.env.COMPLIANCE_POLICY` specifies the compliance policy that the Istio control plane enforces. Set the field to`pqc` to enable PQC.

### 11.4. Configuring service mesh in ambient mode with post-quantum cryptography (PQC)Copy link

Configure the Istio control plane and ztunnel to enforce a post-quantum cryptography (PQC) compliance policy, enabling quantum-resistant security for ambient mode service mesh communications.

Prerequisites

You have installed the following CLI tools locally:

- `oc`
- `podman`
- `curl`

Procedure

Update the`Istio` control plane and`ztunnel` to enable PQC by running the following command:

```shell-session
$ oc apply -f - <<EOF
apiVersion: sailoperator.io/v1
kind: Istio
metadata:
  name: default
spec:
  version: v1.28.5
  namespace: istio-system
  updateStrategy:
    type: InPlace
  values:
    pilot:
      env:
        COMPLIANCE_POLICY: "pqc"
    ztunnel:
      env:
        COMPLIANCE_POLICY: "pqc"
EOF
```

- `spec.values.pilot.env.COMPLIANCE_POLICY` specifies the compliance policy for the Istio control plane. Set the field to`pqc` to enable PQC.
- `spec.values.ztunnel.env.COMPLIANCE_POLICY` specifies the compliance policy for`ztunnel` in ambient mode. Set the field to`pqc` to enable PQC.

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

Observability | Red Hat OpenShift Service Mesh | 3.3 | Red Hat Documentation

 

 

 

 

 

 

 

 

# Observability

---

Red Hat OpenShift Service Mesh 3.3

## Observability and Service Mesh

Red Hat OpenShift Documentation Team

Legal Notice

Abstract

This document provides an overview of Red Hat OpenShift Observability integrations with OpenShift Service Mesh.

---

## Chapter 1. Observability and Service MeshCopy link

Red Hat OpenShift Observability provides real-time visibility, monitoring, and analysis of various system metrics, logs, and events to help you quickly diagnose and troubleshoot issues before they impact systems or applications.

### 1.1. About Observability and Service MeshCopy link

Red Hat OpenShift Observability connects open source observability tools and technologies to create a unified Observability solution. The components of Red Hat OpenShift Observability work together to help you collect, store, deliver, analyze, and visualize data.

Red Hat OpenShift Service Mesh integrates with the following Red Hat OpenShift Observability components:

- Legal Notice
- OpenShift Monitoring
- Red Hat OpenShift distributed tracing platform

OpenShift Service Mesh also integrates with:

- Kiali provided by Red Hat, a powerful console for visualizing and managing your service mesh.
- OpenShift Service Mesh Console (OSSMC) plugin, an OpenShift Container Platform console plugin that seamlessly integrates Kiali console features into your OpenShift console.

The following components in OpenShift Service Mesh ambient mode generate a detailed telemetry for all service communications within a mesh:

- Ztunnel generates Layer 4 (L4) telemetry such as TCP metrics.
- Waypoint proxies generates Layer 7 (L7) telemetry for HTTP, HTTP/2, gRPC traffic metrics, and distributed traces.

## Chapter 2. Metrics and Service MeshCopy link

### 2.1. Using metricsCopy link

You can use the OpenShift Container Platform monitoring stack and Red Hat OpenShift Service Mesh to track the health and performance of your applications. You can learn how to monitor metrics and alerts for both standard and ambient mesh modes.

#### 2.1.1. About metricsCopy link

Monitoring stack components are deployed by default in every OpenShift Container Platform installation and are managed by the Cluster Monitoring Operator (CMO). These components include Prometheus, Alertmanager, Thanos Querier, and others. The CMO also deploys the Telemeter Client, which sends a subset of data from platform Prometheus instances to Red Hat to facilitate Remote Health Monitoring for clusters.

When you have added your application to the mesh, you can monitor the in-cluster health and performance of your applications running on OpenShift Container Platform with metrics and customized alerts for CPU and memory usage, network connectivity, and other resource usage.

When you have added your application to the mesh in ambient mode, you can monitor the Istio standard metrics of your application from the`ztunnel` resource and the waypoint proxies. The ztunnel also exposes a variety of DNS and debugging metrics.

Ambient mode uses two proxy layers, which results in two types of metrics for each application service. You can collect Layer 4 (L4) TCP metrics from both the ztunnel and the waypoint proxies. You can collect Layer 7 (L7) metrics, such as HTTP traffic metrics, from the waypoint proxies.

#### 2.1.2. Configuring OpenShift Monitoring with Service MeshCopy link

You can integrate Red Hat OpenShift Service Mesh with user-workload monitoring to enable observability in your service mesh. User-workload monitoring provides access to essential built-in tools and is required to run Kiali, the dedicated console for Istio.

Prerequisites

You have enabled the user-workload monitoring.

Note

You can enable user-workload monitoring by applying the`ConfigMap` change for metrics integration. For more information, see [Configuring user workload monitoring](https://docs.redhat.com/en/documentation/openshift_container_platform/latest/html/monitoring/configuring-user-workload-monitoring).

- You have installed the Red Hat OpenShift Service Mesh Operator.

Procedure

Create a`ServiceMonitor` resource that monitors the Istio control plane, similar to the following example:

```yaml
apiVersion: monitoring.coreos.com/v1
kind: ServiceMonitor
metadata:
  name: istiod-monitor
  namespace: istio-system
spec:
  targetLabels:
  - app
  selector:
    matchLabels:
      istio: pilot
  endpoints:
  - port: http-monitoring
    interval: 30s
```

To validate that the`ServiceMonitor` resource is monitoring the Istio control plane, go to the OpenShift Console, navigate to Observe → Metrics, and run the query`istio_requests_total`. Confirm that the metrics for the Istio request are displayed.

Note

The Metrics implementation can take a few minutes for the query to return results.

#### 2.1.3. Configuring OpenShift Monitoring with Service Mesh ambient modeCopy link

You can integrate Red Hat OpenShift Service Mesh with user-workload monitoring to enable observability in your service mesh ambient mode. User-workload monitoring provides access to essential built-in tools and is required to run Kiali, the dedicated console for Istio.

Prerequisites

You have enabled the user-workload monitoring.

Note

You can enable user workload monitoring by applying the`ConfigMap` change for metrics integration. For more information, see [Configuring user workload monitoring](https://docs.redhat.com/en/documentation/openshift_container_platform/latest/html/monitoring/configuring-user-workload-monitoring).

- You have installed the Red Hat OpenShift Service Mesh Operator.

Procedure

Create a`Service` resource to define a port that uses the metrics exposed by the ztunnel, similar to the following example:

```yaml
apiVersion: v1
kind: Service
metadata:
  name: ztunnel
  namespace: ztunnel
  labels:
    app: ztunnel
    service: ztunnel
spec:
  selector:
    app: ztunnel
  ports:
    - name: http-monitoring
      protocol: TCP
      port: 15020
      targetPort: 15020
```

Create a`ServiceMonitor` resource that monitors the Istio control plane, similar to the following example:

```yaml
apiVersion: monitoring.coreos.com/v1
kind: ServiceMonitor
metadata:
  name: istiod-monitor
  namespace: istio-system
spec:
  targetLabels:
  - app
  selector:
    matchLabels:
      istio: pilot
  endpoints:
  - port: http-monitoring
    interval: 30s
```

Create a`ServiceMonitor` resource that collects the ztunnel metrics, similar to the following example:

```yaml
apiVersion: monitoring.coreos.com/v1
kind: ServiceMonitor
metadata:
  name: ztunnel-monitor
  namespace: ztunnel
spec:
  targetLabels:
  - app
  selector:
    matchLabels:
      service: ztunnel
  endpoints:
  - port: http-monitoring
    interval: 30s
```

Optional: Deploy a waypoint proxy to enable the Layer 7 (L7) OpenShift Service Mesh features in ambient mode:

Deploy a custom waypoint proxy with additional labels and ports for the`bookinfo` namespace, similar to the following example:

```yaml
 apiVersion: gateway.networking.k8s.io/v1
kind: Gateway
metadata:
  labels:
    istio.io/waypoint-for: service
    app: waypoint
    service: waypoint
  name: waypoint
  namespace: bookinfo
spec:
  gatewayClassName: istio-waypoint
  listeners:
  - name: mesh
    port: 15008
    protocol: HBONE
  - name: http-monitoring
    protocol: TCP
    port: 15020
```

` ` Specifies an additional port that exposes the metrics used by the waypoint proxy.

Enroll the namespace to use the waypoint by running the following command:

```shell-session
$ oc label namespace bookinfo istio.io/use-waypoint=waypoint
```

Create a`ServiceMonitor` resource that collects the waypoint proxy metrics for the`bookinfo` namespace, similar to the following example:

```yaml
apiVersion: monitoring.coreos.com/v1
kind: ServiceMonitor
metadata:
  name: waypoint-monitor
  namespace: bookinfo
spec:
  targetLabels:
  - app
  selector:
    matchLabels:
      service: waypoint
  endpoints:
  - port: http-monitoring
    interval: 30s
```

Note

A waypoint proxy generates Layer 4 (L4) and L7 metrics. It scopes these statistics by Envoy proxy functions. The Envoy statistic functions are described in Envoy proxy documentation, for example,`Upstream connection`,`Listener`,`HTTP Connection Manager`,`TCP proxy`, and`Router`.

##### 2.1.3.1. Verifying metrics in ambient modeCopy link

You can verify that the metrics for your application available in the OpenShift Console.

Prerequisites

- You have deployed the Bookinfo application in ambient mode to use the following example. For more information, see [Deploying the Bookinfo application in Istio ambient mode](https://docs.redhat.com/en/documentation/red_hat_openshift_service_mesh/3.2/html/installing/ossm-istio-ambient-mode#ossm-deploying-bookinfo-application-istio-ambient-mode_ossm-istio-ambient-mode).

Procedure

Find the status of`Metrics Targets` by searching for targets such as`istiod-monitor`,`ztunnel-monitor`, and`waypoint-monitor`.`waypoint-monitor` is created only when the waypoint proxy is created to use Layer 7 (L7) OpenShift Service Mesh features.

Note

The`ServiceMonitor` resource configuration can take a few minutes to show in the`Metrics Targets` results.

Send some traffic to the Bookinfo`productpage` service for generating metrics, by running the following command:

```shell-session
$ curl "http://${GATEWAY_URL}/productpage" | grep "<title>"
```

1. On the OpenShift Console go to Observe → Targets.
2. On the OpenShift Console go to Observe → Metrics and run a query such as,`istio_build`,`istio_tcp_received_bytes_total`, or`istio_requests_total`.

## Chapter 3. Distributed tracing and Service MeshCopy link

### 3.1. Configuring Red Hat OpenShift distributed tracing platform with Service MeshCopy link

Integrate Red Hat OpenShift distributed tracing platform with Red Hat OpenShift Service Mesh by using Red Hat OpenShift distributed tracing platform (Tempo) for distributed tracing storage and Red Hat OpenShift distributed tracing data collection for standardized telemetry data collection and processing.

#### 3.1.1. About Red Hat OpenShift distributed tracing platform and Red Hat OpenShift Service MeshCopy link

Integrating Red Hat OpenShift distributed tracing platform with Red Hat OpenShift Service Mesh is made of up two parts: Red Hat OpenShift distributed tracing platform (Tempo) and Red Hat OpenShift distributed tracing data collection.

Red Hat OpenShift distributed tracing platform (Tempo)

Provides distributed tracing to monitor and troubleshoot transactions in complex distributed systems. Tempo is based on the open source [Grafana Tempo](https://grafana.com/oss/tempo/) project.

For more about information about distributed tracing platform (Tempo), its features, installation, and configuration, see: [Red Hat OpenShift distributed tracing platform (Tempo)](https://docs.redhat.com/en/documentation/openshift_container_platform/latest/html/distributed_tracing/distr-tracing-tempo-architecture).

Red Hat OpenShift distributed tracing data collection

Is based on the open source [OpenTelemetry project](https://opentelemetry.io/), which aims to provide unified, standardized, and vendor-neutral telemetry data collection for cloud-native software. Red Hat OpenShift distributed tracing data collection product provides support for deploying and managing the OpenTelemetry Collector and simplifying the workload instrumentation.

The [OpenTelemetry Collector](https://opentelemetry.io/docs/collector/) can receive, process, and forward telemetry data in many formats, making it the ideal component for telemetry processing and interoperability between telemetry systems. The Collector provides a unified solution for collecting and processing metrics, traces, and logs.

For more information about distributed tracing data collection, its features, installation, and configuration, see: [Red Hat OpenShift distributed tracing data collection](https://docs.redhat.com/en/documentation/openshift_container_platform/latest/html/red_hat_build_of_opentelemetry/index).

#### 3.1.2. Configuring Red Hat OpenShift distributed tracing data collection with Service MeshCopy link

You can integrate Red Hat OpenShift Service Mesh with Red Hat OpenShift distributed tracing data collection to instrument, generate, collect, and export OpenTelemetry traces, metrics, and logs to analyze and understand the performance and behavior of the software.

Prerequisites

- You have installed the Tempo Operator. For more information see, [Installing the Tempo Operator](https://docs.redhat.com/en/documentation/openshift_container_platform/latest/html/distributed_tracing/distr-tracing-tempo-installing#installing-the-tempo-operator_distr-tracing-tempo-installing).
- You have installed the Red Hat OpenShift distributed tracing data collection Operator. For more information see, [Installing the Red Hat build of OpenTelemetry](https://docs.redhat.com/en/documentation/openshift_container_platform/latest/html/red_hat_build_of_opentelemetry/install-otel).
- You have installed a`TempoStack` which is configured in a`tempo` namespace. For more information see, [Installing a TempoStack instance](https://docs.redhat.com/en/documentation/openshift_container_platform/latest/html/distributed_tracing/distr-tracing-tempo-installing#installing-a-tempostack-instance_distr-tracing-tempo-installing).
- You have created an Istio instance.
- You have created an Istio CNI instance.

Procedure

Navigate to the Red Hat OpenShift distributed tracing data collection Operator and install the`OpenTelemetryCollector` resource in the`istio-system` namespace, similar to the following example:

```yaml
kind: OpenTelemetryCollector
apiVersion: opentelemetry.io/v1beta1
metadata:
  name: otel
  namespace: istio-system
spec:
  observability:
    metrics: {}
  deploymentUpdateStrategy: {}
  config:
    exporters:
      otlp:
        endpoint: 'tempo-sample-distributor.tempo.svc.cluster.local:4317'
        tls:
          insecure: true
    receivers:
      otlp:
        protocols:
          grpc:
            endpoint: '0.0.0.0:4317'
          http: {}
    service:
      pipelines:
        traces:
          exporters:
            - otlp
          receivers:
            - otlp
```

Update the Red Hat OpenShift Service Mesh Istio custom resource (CR) to enable tracing and define the distributed tracing data collection tracing providers in your`meshConfig`, similar to the following example:

```yaml
apiVersion: sailoperator.io/v1
kind: Istio
metadata:
#  ...
  name: default
spec:
  namespace: istio-system
#  ...
  values:
    meshConfig:
      enableTracing: true
      extensionProviders:
      - name: otel
        opentelemetry:
          port: 4317
          service: otel-collector.istio-system.svc.cluster.local 
```

1

1

The`service` field is the`OpenTelemetry` collector service in the`istio-system` namespace.

Create an Istio Telemetry resource to enable tracers defined in`spec.values.meshConfig.ExtensionProviders`, similar to the following example:

```yaml
apiVersion: telemetry.istio.io/v1
kind: Telemetry
metadata:
  name: otel-demo
  namespace: istio-system
spec:
  tracing:
    - providers:
        - name: otel
      randomSamplingPercentage: 100
```

After you verify that you can see traces, lower the`randomSamplingPercentage` value or set it to`default` to reduce the number of requests.

Note

You can use a single Istio Telemetry resource for both the Prometheus metrics provider and a tracing provider by setting`spec.metrics.overrides.disabled` to`false`. This enables the Prometheus metrics provider. This is an optional step and you can skip it if you configured metrics through the OpenShift Cluster Monitoring method described in the previous step.

Create the`bookinfo` namespace by running the following command:

```shell-session
$ oc create ns bookinfo
```

Depending on the update strategy you are using, enable sidecar injection in the namespace by running the appropriate commands:

If you are using the`InPlace` update strategy, run the following command:

```shell-session
$ oc label namespace curl istio-injection=enabled
```

If you are using the`RevisionBased` update strategy, run the following commands:

Display the revision name by running the following command:

```shell-session
$ oc get istiorevisions.sailoperator.io
```

Example output

```shell-session
NAME      TYPE    READY   STATUS    IN USE   VERSION   AGE
default   Local   True    Healthy   True     v1.24.3   3m33s
```

Label the namespace with the revision name to enable sidecar injection by running the following command:

```shell-session
$ oc label namespace curl istio.io/rev=default
```

Deploy the`bookinfo` application in the`bookinfo` namespace by running the following command:

```shell-session
$ oc apply -f https://raw.githubusercontent.com/openshift-service-mesh/istio/release-1.24/samples/bookinfo/platform/kube/bookinfo.yaml -n bookinfo
```

Generate traffic to the`productpage` pod to generate traces:

```shell-session
$ oc exec -it -n bookinfo deployments/productpage-v1 -c istio-proxy -- curl localhost:9080/productpage
```

Validate the integration by running the following command to see traces in the UI:

```shell-session
$ oc get routes -n tempo tempo-sample-query-frontend
```

Note

The OpenShift route for Jaeger UI must be created in the Tempo namespace. You can either manually create it for the`tempo-sample-query-frontend` service, or update the`Tempo` custom resource with`.spec.template.queryFrontend.jaegerQuery.ingress.type: route`.

#### 3.1.3. Configuring Red Hat OpenShift distributed tracing platform (Tempo) with Service Mesh ambient modeCopy link

You can configure a distributed tracing platform (Tempo) with OpenShift Service Mesh ambient mode by using waypoint or gateway proxies to generate Layer 7 (L7) spans. The`ztunnel` component generates only Layer 4 (L4) data, so L7 spans appear only when a workload or service uses an attached proxy.

Prerequisites

- You have installed the Tempo Operator. For more information see: [Installing the Tempo Operator](https://docs.redhat.com/en/documentation/openshift_container_platform/latest/html/distributed_tracing/distr-tracing-tempo-installing#installing-the-tempo-operator_distr-tracing-tempo-installing).
- You have installed the Red Hat OpenShift distributed tracing data collection Operator. For more information see: [Installing the Red Hat build of OpenTelemetry](https://docs.redhat.com/en/documentation/openshift_container_platform/latest/html/red_hat_build_of_opentelemetry/install-otel)
- You have installed a`TempoStack` which is configured in a`tempo` namespace. For more information see: [Installing a TempoStack instance](https://docs.redhat.com/en/documentation/openshift_container_platform/latest/html/distributed_tracing/distr-tracing-tempo-installing#installing-a-tempostack-instance_distr-tracing-tempo-installing).
- You have created an Istio instance.

Procedure

Navigate to the Red Hat OpenShift distributed tracing data collection Operator and install the`OpenTelemetryCollector` resource in the`istio-system` namespace, similar to the following example:

```yaml
kind: OpenTelemetryCollector
apiVersion: opentelemetry.io/v1beta1
metadata:
  name: otel
  namespace: istio-system
spec:
  mode: deployment
  observability:
    metrics: {}
  deploymentUpdateStrategy: {}
  config:
    exporters:
      otlp:
        endpoint: 'tempo-sample-distributor.tempo.svc.cluster.local:4317'
        tls:
          insecure: true
    receivers:
      otlp:
        protocols:
          grpc:
            endpoint: '0.0.0.0:4317'
          http: {}
    service:
      pipelines:
        traces:
          exporters:
            - otlp
          receivers:
            - otlp
```

- `spec.config.exporters.otlp.endpoint` defines the Tempo sample distributor service in a namespace such as`tempo`.

Update the Red Hat OpenShift Service Mesh Istio custom resource (CR) to define a tracing provider in the`spec.values.meshConfig` field, similar to the following example:

```yaml
apiVersion: sailoperator.io/v1
kind: Istio
metadata:
#  ...
  name: default
spec:
  namespace: istio-system
  profile: ambient
#  ...
  values:
    meshConfig:
      enableTracing: true
      extensionProviders:
      - name: otel
        opentelemetry:
          port: 4317
          service: otel-collector.istio-system.svc.cluster.local
    pilot:
      trustedZtunnelNamespace: ztunnel
```

- `spec.values.meshConfig.extensionProviders.opentelemetry.service` defines the OpenTelemetry collector service in the`istio-system` namespace.

Create an Istio Telemetry CR to enable the tracing provider defined in the`spec.values.meshConfig.ExtensionProviders` field, similar to the following example:

```yaml
apiVersion: telemetry.istio.io/v1
kind: Telemetry
metadata:
  name: otel-demo
  namespace: istio-system
spec:
  tracing:
    - providers:
        - name: otel
      randomSamplingPercentage: 100
```

Note

After you can see the traces, lower the`randomSamplingPercentage` value or set it to`default` to reduce the number of requests. You can also use the`spec.targetRefs` field to enable tracing at a gateway or a waypoint level.

##### 3.1.3.1. Verifying traces in ambient modeCopy link

You can verify that the traces for your application are in ambient mode. The following example uses the Bookinfo application.

Prerequisites

- Optional: Use a single Istio Telemetry resource for both a Prometheus metrics provider and a tracing provider by setting`spec.metrics.overrides.disabled` field to`false`. This enables the Prometheus metrics provider. You do not need this step if you have configured metrics through the OpenShift Cluster Monitoring approach described in the previous step.
- You have deployed the Bookinfo application in ambient mode to use the following example. For more information, see [Deploying the Bookinfo application in Istio ambient mode](https://docs.redhat.com/en/documentation/red_hat_openshift_service_mesh/latest/html/installing/ossm-istio-ambient-mode#ossm-deploying-bookinfo-application-istio-ambient-mode_ossm-istio-ambient-mode).
- You have deployed a waypoint proxy and enrolled the`bookinfo` namespace to use the waypoint. For more information, see [Deploying a waypoint proxy](https://docs.redhat.com/en/documentation/red_hat_openshift_service_mesh/latest/html/installing/ossm-istio-ambient-mode#ossm-deploying-waypoint-proxy_ossm-istio-ambient-mode).

Procedure

Send some traffic to the Bookinfo`productpage` service for generating traces by running the following command:

```shell-session
$ curl "http://${GATEWAY_URL}/productpage" | grep "<title>"
```

Verify that the Bookinfo application traces appear in a Tempo dashboard UI by running the following command:

```shell-session
$ oc get routes -n tempo tempo-sample-query-frontend
```

Click Find Traces.

Note

The route for Tempo dashboard UI can be created from the`TempoStack` custom resource (CR) with`.spec.template.queryFrontend.jaegerQuery.ingress.type: route` field.

1. Select the`bookinfo-gateway-istio.booinfo` or the`waypoint.bookinfo` service from the dashboard UI.

## Chapter 4. Kiali Operator provided by Red HatCopy link

### 4.1. Using Kiali Operator provided by Red HatCopy link

Once you have added your application to the mesh, you can use Kiali Operator provided by Red Hat to view the data flow through your application.

#### 4.1.1. About KialiCopy link

You can use Kiali Operator provided by Red Hat to view configurations, monitor traffic, and analyze traces in a single console. It is based on the open source [Kiali](https://www.kiali.io/) project.

Kiali Operator provided by Red Hat is the management console for Red Hat OpenShift Service Mesh. It provides dashboards, observability, and robust configuration and validation capabilities. It shows the structure of your service mesh by inferring traffic topology and displays the health of your mesh. Kiali provides detailed metrics, powerful validation, access to Grafana, and strong integration with the Red Hat OpenShift distributed tracing platform (Tempo).

#### 4.1.2. About Kiali and Istio ambient modeCopy link

When running in Istio ambient mode, Kiali introduces new behaviors and visualizations to support the Ambient data plane. The following information describes key aspects of Kiali in this context:

Access requirements Kiali requires access to the`ztunnel` namespace to detect whether ambient mode is enabled. Without this access, Kiali does not display ambient-related features. Visualizations and features With ambient badges, Kiali displays badges for namespaces and workloads that are enrolled in the ambient mesh, allowing quick identification. Traffic graph adjustments

Ambient mode introduces new telemetry sources. Kiali collects and displays metrics from both ztunnel and waypoint proxies to provide complete visibility into mesh traffic. New filters and selectors in Kiali allow users to focus on ambient-specific traffic sources. Kiali provides a display option for visualizing waypoint nodes in the traffic graph.

The traffic graph changes based on the ambient enrollment:

- Without waypoint proxies, the traffic graph displays only Layer 4 (L4) traffic.
- With waypoint proxies, the graph includes Layer 7 (L7) traffic and may also include L4 traffic.

Workload proxy logs Kiali aggregates and filters logs from both ztunnel and waypoint proxies. This unified view simplifies troubleshooting by showing only the relevant log entries for each workload. Distributed tracing Tracing data is available only when waypoint proxies are deployed, because traces are generated by waypoint services. Kiali automatically correlates workload traces with their associated waypoint proxies. Dedicated pages for ambient components

The dedicated pages allow users to analyze ambient components separately from workloads and services:

- Waypoint pages display detailed information about captured workloads.
- Ztunnel pages focus on telemetry, metrics, and diagnostics, based on data from`istioctl` utilities.

Kiali integration with ambient mode ensures full observability for workloads running in the ambient mesh and simplifies operational monitoring and troubleshooting tasks.

#### 4.1.3. Installing the Kiali Operator provided by Red HatCopy link

The following steps show how to install the Kiali Operator provided by Red Hat.

Warning

Do not install the Community version of the Operator. The Community version is not supported.

Prerequisites

- Access to the Red Hat OpenShift Service Mesh web console.

Procedure

Select the Automatic Approval Strategy.

Note

The Manual approval strategy requires a user with appropriate credentials to approve the Operator installation and subscription process.

1. Log in to the Red Hat OpenShift Service Mesh web console.
2. Navigate to Operators → OperatorHub.
3. Type Kiali into the filter box to find the Kiali Operator provided by Red Hat.
4. Click Kiali Operator provided by Red Hat to display information about the Operator.
5. Click Install.
6. On the Operator Installation page, select the stable Update Channel.
7. Select All namespaces on the cluster (default). This installs the Operator in the default`openshift-operators` project and makes the Operator available to all projects in the cluster.
8. Click Install.
9. The Installed Operators page displays the Kiali Operator’s installation progress.

#### 4.1.4. Configuring OpenShift Monitoring with KialiCopy link

The following steps show how to integrate the Kiali Operator provided by Red Hat with user-workload monitoring.

Prerequisites

- Red Hat OpenShift Service Mesh is installed.
- User-workload monitoring is enabled. See [Enabling monitoring for user-defined projects](https://docs.openshift.com/container-platform/4.16/observability/monitoring/enabling-monitoring-for-user-defined-projects.html).
- OpenShift Monitoring has been configured with Service Mesh. See "Configuring OpenShift Monitoring with Service Mesh".
- Kiali Operator provided by Red Hat 2.4 is installed.

Procedure

Create a`ClusterRoleBinding` resource for Kiali:

Example`ClusterRoleBinding` configuration

```yaml
apiVersion: rbac.authorization.k8s.io/v1
kind: ClusterRoleBinding
metadata:
  name: kiali-monitoring-rbac
roleRef:
  apiGroup: rbac.authorization.k8s.io
  kind: ClusterRole
  name: cluster-monitoring-view
subjects:
- kind: ServiceAccount
  name: kiali-service-account
  namespace: istio-system
```

Create a Kiali resource and point it to your Istio instance:

Example Kiali resource configuration

```yaml
apiVersion: kiali.io/v1alpha1
kind: Kiali
metadata:
  name: kiali-user-workload-monitoring
  namespace: istio-system
spec:
  external_services:
    prometheus:
      auth:
        type: bearer
        use_kiali_token: true
      thanos_proxy:
        enabled: true
      url: https://thanos-querier.openshift-monitoring.svc.cluster.local:9091
```

When the Kiali resource is ready, get the Kiali URL from the Route by running the following command:

```shell-session
$ echo "https://$(oc get routes -n istio-system kiali -o jsonpath='{.spec.host}')"
```

1. Follow the URL to open Kiali in your web browser.
2. Navigate to the Traffic Graph tab to check the traffic in the Kiali UI.

#### 4.1.5. Integrating Red Hat OpenShift distributed tracing platform with Kiali Operator provided by Red HatCopy link

You can integrate Red Hat OpenShift distributed tracing platform with Kiali Operator provided by Red Hat, which enables the following features:

- Display trace overlays and details on the graph.
- Display scatterplot charts and in-depth trace/span information on detail pages.
- Integrated span information in logs and metric charts.
- Offer links to the external tracing UI.

##### 4.1.5.1. Configuring Red Hat OpenShift distributed tracing platform with Kiali Operator provided by Red HatCopy link

After you integrate Kiali Operator provided by Red Hat with Red Hat OpenShift distributed tracing platform, you can view distributed traces in the Kiali console. Viewing traces provides insight into the communication between services within the service mesh, helping you understand how requests are flowing through your system and where potential issues might reside.

Prerequisites

- You installed Red Hat OpenShift Service Mesh.
- You configured distributed tracing platform with Red Hat OpenShift Service Mesh.

Procedure

Update the`Kiali` resource`spec` configuration for tracing:

Example`Kiali` resource`spec` configuration for tracing

```yaml
spec:
  external_services:
    tracing:
      enabled: true 
```

1

```yaml

      provider: tempo 
```

2

```yaml

      use_grpc: false
      internal_url: https://tempo-sample-gateway.tempo.svc.cluster.local:8080/api/traces/v1/default/tempo 
```

3

```yaml

      external_url: https://tempo-sample-gateway-tempo.apps-crc.testing/api/traces/v1/default/search 
```

4

```yaml

      health_check_url: https://tempo-sample-gateway-tempo.apps-crc.testing/api/traces/v1/default/tempo/api/echo 
```

5

```yaml

      auth: 
```

6

```yaml

        ca_file: /var/run/secrets/kubernetes.io/serviceaccount/service-ca.crt
        insecure_skip_verify: false
        type: bearer
        use_kiali_token: true
      tempo_config:
         url_format: "jaeger" 
```

7

1

Specifies whether tracing is enabled.

2

Specifies either distributed tracing platform (Tempo) or distributed tracing platform (Jaeger). The distributed tracing platform can expose a Jaeger API or a Tempo API.

3

Specifies the internal URL for the Tempo API. When you deploy the distributed tracing platform in multitenancy, include the tenant name in the URL path of the`internal_url` parameter. In this example,`default` represents the tenant name.

4

Specifies the OpenShift route for the Jaeger UI. When you deploy the distributed tracing platform in multitenancy, the gateway creates the route. Otherwise, you must create the route in the`Tempo` namespace. You can manually create the route for the`tempo-sample-query-frontend` service or update the`Tempo` custom resource with`.spec.template.queryFrontend.jaegerQuery.ingress.type: route`.

5

Specifies the health check URL. Not required by default. When you deploy the distributed tracing platform in multitenancy, it does not expose the default health check URL. This is an example of a valid health URL.

6

Specifies the configuration used when the access URL is`HTTPS` or requires authentication. Not required by default.

7

Specifies the configuration that defaults to`grafana`. Not required by default. Change to`jaeger` if the Kiali`View in tracing` link redirects to the Jaeger console UI.

Run the following command to apply the configuration:

```shell-session
$ oc patch -n istio-system kiali kiali --type merge -p "$(cat kiali_cr.yaml)"
```

Example output:

```shell-session
 kiali.kiali.io/kiali patched
```

1. Save the updated`spec` in`kiali_cr.yaml`.

Verification

Run the following command to get the Kiali route:

```shell-session
$ oc get route kiali ns istio-system
```

1. Navigate to the Kiali UI.
2. Navigate to Workload → Traces tab to see traces in the Kiali UI.

#### 4.1.6. External Kiali deployment modelCopy link

Large mesh deployments can separate mesh operation from mesh observability by deploying Kiali away from the mesh. This separation provides dedicated management of observability, reduced resource consumption on mesh clusters, centralized visibility, and improved security isolation.

The external deployment model requires a minimum of two clusters:

- Management cluster: The home cluster where you deploy Kiali.
- Mesh clusters: The remote clusters where you deploy the service mesh.

In this model, Kiali is not co-located with an Istio control plane. You can also colocate other observability tools, such as Prometheus, on the management cluster to improve metric query performance.

##### 4.1.6.1. Installing Kiali Operator on remote clustersCopy link

In an external deployment, you must install the Kiali Operator on all clusters, including those where Kiali is not deployed, to ensure the creation of required namespaces and remote cluster resources.

Prerequisites

- You are logged in to the OpenShift Container Platform web console as a user with the`cluster-admin` role.
- You have Istio installed in a multi-cluster configuration on each cluster.
- You have configured a metrics store so that Kiali can query metrics from all the clusters. Kiali queries metrics and traces from their endpoints.
- You have the necessary secrets for Kiali to access remote clusters.
- You have set the`clustering.ignore_home_cluster` field to`true` in the`Kiali` custom reource (CR).
- You have given a unique cluster name for the Kiali home cluster in`.spec.kubernetes_config.cluster_name` specification. In an external deployment, you must manually set this name because there is no colocated Istio control plane to provide it.

Procedure

For clusters where Kiali is not deployed, configure the`Kiali` CR to create only the remote cluster resources by setting the`spec.deployment.remote_cluster_resources_only` field to`true`, similar to the following example:

```yaml
apiVersion: kiali.io/v1alpha1
kind: Kiali
metadata:
  name: kiali
  namespace: istio-system
spec:
  version: default
  auth:
  deployment:
    remote_cluster_resources_only: true
```

Ensure the Kiali namespace and instance name are consistent across all clusters. If you change the default namespace (`istio-system`) or instance name (`kiali`), you must apply the same values to the following`Kiali` CR settings on every cluster:

- `spec.deployment.namespace`
- `spec.deployment.instance_name`

### 4.2. Using OpenShift Service Mesh Console pluginCopy link

The OpenShift Service Mesh Console (OSSMC) plugin extends the OpenShift Container Platform web console with a Service Mesh menu and enhanced tabs for workloads and services.

#### 4.2.1. About OpenShift Service Mesh Console pluginCopy link

The OpenShift Service Mesh Console (OSSMC) plugin is an extension to OpenShift Container Platform web console that provides visibility into your Service Mesh.

Warning

The OSSMC plugin supports only one Kiali instance, regardless of its project access scope.

The OSSMC plugin provides a new category, Service Mesh, in the main OpenShift Container Platform web console navigation with the following menu options:

Overview Provides a summary of your mesh, displayed as cards that represent the namespaces in the mesh. Traffic Graph Provides a full topology view of your mesh, represented by nodes and edges. Each node represents a component of the mesh and each edge represents traffic flowing through the mesh between components. Istio config Provides a list of all Istio configuration files in your mesh, with a column that provides a quick way to know if the configuration for each resource is valid. Mesh Provides detailed information about the Istio infrastructure status. It shows an infrastructure topology view with core and add-on components, their health, and how they are connected to each other.

In the web console Workloads details page, the OSSMC plugin adds a Service Mesh tab that has the following subtabs:

Overview Shows a summary of the selected workload, including a localized topology graph showing the workload with all inbound and outbound edges and nodes. Traffic Shows information about all inbound and outbound traffic to the workload. Logs Shows the logs for the workload’s containers. You can see container logs individually ordered by log time and how the Envoy sidecar proxy logs relate to your workload’s application logs. You can enable the tracing span integration, which allows you to see which logs correspond to trace spans. Metrics Shows inbound and outbound metric graphs in the corresponding subtabs. All the workload metrics are here, providing a detailed view of the performance of your workload. You can enable the tracing span integration, which allows you to see which spans occurred at the same time as the metrics. With the span marker in the graph, you can see the specific spans associated with that timeframe. Traces Provides a chart showing the trace spans collected over the given timeframe. The trace spans show the most low-level detail within your workload application. The trace details further show heatmaps that provide a comparison of one span in relation to other requests and spans in the same timeframe. Envoy Shows information about the Envoy sidecar configuration.

In the web console Networking details page, the OSSMC plugin adds a Service Mesh tab similar to the Workloads details page.

In the web console Projects details page, the OSSMC plugin adds a Service Mesh tab that provides traffic graph information about that project. It is the same information shown in the Traffic Graph page but specific to that project.

##### 4.2.1.1. About installing OpenShift Service Mesh Console pluginCopy link

You can install the OSSMC plugin with the Kiali Operator by creating a`OSSMConsole` resource with the corresponding plugin settings. It is recommended to install the latest version of the Kiali Operator, even while installing a previous OSSMC plugin version, as it includes the latest z-stream release.

Expand

3.1

v2.11

v2.11

4.16+

3.0

v2.4

v2.4

4.15+

2.6

v1.73

v1.73

4.15-4.18

2.5

v1.73

v1.73

4.14-4.18

| Table 4.1. OSSM version compatibility | OSSM version | Kiali Server version | OSSMC plugin version | OCP version |
| --- | --- | --- | --- | --- |

Show more

Note

OSSMC plugin is only supported on OpenShift Container Platform 4.15 and above. For OpenShift Container Platform 4.14 users, only the standalone Kiali console is accessible.

You can install the OSSMC plugin by using the OpenShift Container Platform web console or the OpenShift CLI (`oc`).

##### 4.2.1.2. Installing OSSMC plugin by using the OpenShift Container Platform web consoleCopy link

You can install the OpenShift Service Mesh Console (OSSMC) plugin by using the OpenShift Container Platform web console.

Prerequisites

- You have the administrator access to the OpenShift Container Platform web console.
- You have installed the OpenShift Service Mesh (OSSM).
- You have installed the`Istio` control plane from OSSM 3.0.
- You have installed the Kiali Server 2.4.

Procedure

Use the Create OSSMConsole form to create an instance of the`OSSMConsole` custom resource (CR). Name and Version are the required fields.

Note

The Version field must match with the`spec.version` field in your Kiali custom resource (CR). If Version value is the string`default`, the Kiali Operator installs OpenShift Service Mesh Console (OSSMC) with the same version as the operator. The`spec.version` field requires the`v` prefix in the version number. The version number must only include the major and minor version numbers (not the patch number); for example:`v1.73`.

1. Navigate to Installed Operators.
2. Click Kiali Operator provided by Red Hat.
3. Click Create instance on the Red Hat OpenShift Service Mesh Console tile. You can also click Create OSSMConsole button under the OpenShift Service Mesh Console tab.
4. Click Create.

Verification

1. Wait until the web console notifies you that the OSSMC plugin is installed and prompts you to refresh.
2. Verify that the Service Mesh category is added in the main OpenShift Container Platform web console navigation.

##### 4.2.1.3. Installing OSSMC plugin by using the CLICopy link

You can install the OpenShift Service Mesh Console (OSSMC) plugin by using the OpenShift CLI.

Prerequisites

- You have access to the OpenShift CLI (`oc`) on the cluster as an administrator.
- You have installed the OpenShift Service Mesh (OSSM).
- You have installed the`Istio` control plane from OSSM 3.0.
- You have installed the Kiali Server 2.4.

Procedure

Create a`OSSMConsole` custom resource (CR) to install the plugin by running the following command:

```yaml
$ cat <<EOM | oc apply -f -
apiVersion: kiali.io/v1alpha1
kind: OSSMConsole
metadata:
  namespace: openshift-operators
  name: ossmconsole
spec:
  version: default
EOM
```

Note

The OpenShift Service Mesh Console (OSSMC) version must match with the Kiali Server version. If`spec.version` field value is the string`default` or is not specified, the Kiali Operator installs OSSMC with the same version as the operator. The`spec.version` field requires the`v` prefix in the version number. The version number must only include the major and minor version numbers (not the patch number); for example:`v1.73`.

The plugin resources deploy in the same namespace as the`OSSMConsole` CR.

Optional: If more than one Kiali Server is installed in the cluster, specify the`spec.kiali` setting in the OSSMC CR by running a command similar to the following example:

```yaml
$ cat <<EOM | oc apply -f -
apiVersion: kiali.io/v1alpha1
kind: OSSMConsole
metadata:
  namespace: openshift-operators
  name: ossmconsole
spec:
  kiali:
    serviceName: kiali
    serviceNamespace: istio-system-two
    servicePort: 20001
EOM
```

Verification

1. Go to the OpenShift Container Platform web console.
2. Verify that the Service Mesh category is added in the main OpenShift Container Platform web console navigation.
3. If the OSSMC plugin is not installed yet, wait until the web console notifies you that the OSSMC plugin is installed and prompts you to refresh.

##### 4.2.1.3.1. About uninstalling OpenShift Service Mesh Console pluginCopy link

You can uninstall the OSSMC plugin by using the OpenShift Container Platform web console or the OpenShift CLI (`oc`).

You must uninstall the OSSMC plugin before removing the Kiali Operator. Deleting the Operator first may leave OSSMC and Kiali CRs stuck, requiring manual removal of the finalizer. Use the following command with` ` as`kiali` or`ossmconsole` to remove the finalizer, if needed:

```shell-session
$ oc patch <custom_resource_type> <custom_resource_name> -n <custom_resource_namespace> -p '{"metadata":{"finalizers": []}}' --type=merge
```

##### 4.2.1.4. Uninstalling OSSMC plugin by using the web consoleCopy link

You can uninstall the OpenShift Service Mesh Console (OSSMC) plugin by using the OpenShift Container Platform web console.

Procedure

1. Navigate to Installed Operators.
2. Click Kiali Operator.
3. Select the OpenShift Service Mesh Console tab.
4. Click Delete OSSMConsole option from the entry menu.
5. Confirm that you want to delete the plugin.

##### 4.2.1.5. Uninstalling OSSMC plugin by using the CLICopy link

You can uninstall the OpenShift Service Mesh Console (OSSMC) plugin by using the OpenShift CLI (`oc`).

Procedure

Remove the OSSMC custom resource (CR) by running the following command:

```shell-session
$ oc delete ossmconsoles <custom_resource_name> -n <custom_resource_namespace>
```

Verification

Verify all the CRs are deleted from all namespaces by running the following command:

```shell-session
$ for r in $(oc get ossmconsoles --ignore-not-found=true --all-namespaces -o custom-columns=NS:.metadata.namespace,N:.metadata.name --no-headers | sed 's/  */:/g'); do oc delete ossmconsoles -n $(echo $r|cut -d: -f1) $(echo $r|cut -d: -f2); done
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

Gateways | Red Hat OpenShift Service Mesh | 3.3 | Red Hat Documentation

 

 

 

 

 

 

 

 

# Gateways

---

Red Hat OpenShift Service Mesh 3.3

## Gateways and OpenShift Service Mesh

Red Hat OpenShift Documentation Team

Legal Notice

Abstract

This documentation provides information about using gateways with OpenShift Service Mesh.

---

## Chapter 1. About gatewaysCopy link

A gateway is a standalone Envoy proxy deployment and an associated Kubernetes service operating at the edge of a service mesh. You can configure a gateway to give fine-grained control over the traffic that enters or leaves the mesh. In Red Hat OpenShift Service Mesh, you can install gateways by using gateway injection or via the Gateway API.

Red Hat OpenShift Service Mesh supports different gateway configurations based on the deployment mode. You can deploy gateways by using gateway injection and configure them with Istio Gateway and`VirtualService` resources in sidecar mode or with Kubernetes Gateway API resources in both sidecar and ambient modes.

### 1.1. About gateway injectionCopy link

Gateway injection relies upon the same mechanism as sidecar injection to inject the Envoy proxy into gateway pods. To install a gateway using gateway injection, you create a Kubernetes`Deployment` object and an associated Kubernetes`Service` object in a namespace that is visible to the Istio control plane. When creating the`Deployment` object you label and annotate it so that the Istio control plane injects a proxy, and the proxy is configured as a gateway. After installing the gateway, you configure it to control ingress and egress traffic using the Istio`Gateway` and`VirtualService` resources.

#### 1.1.1. Installing a gateway by using gateway injectionCopy link

This procedure explains how to install a gateway by using gateway injection.

Note

You can use this procedure to create ingress or egress gateways.

Prerequisites

- Legal Notice
- You have installed the OpenShift Service Mesh Operator version 3.0 or later.
- You have created an Istio control plane.
- You have created an`IstioCNI` resource.

Procedure

Create a namespace that you will use to install the gateway.

```shell-session
$ oc create namespace <gateway_namespace>
```

Note

Install the gateway and the Istio control plane in different namespaces.

You can install the gateway in a dedicated gateway namespace. This approach allows the gateway to be shared by many applications operating in different namespaces. Alternatively, you can install the gateway in an application namespace. In this approach, the gateway acts as a dedicated gateway for the application in that namespace.

Create a YAML file named`secret-reader.yml` that defines the service account, role, and role binding for the gateway deployment. These settings enable the gateway to read the secrets, which is required for obtaining TLS credentials.

```yaml
apiVersion: v1
kind: ServiceAccount
metadata:
  name: secret-reader
  namespace: <gateway_namespace>
---
apiVersion: rbac.authorization.k8s.io/v1
kind: Role
metadata:
  name: secret-reader
  namespace: <gateway_namespace>
rules:
  - apiGroups: [""]
    resources: ["secrets"]
    verbs: ["get", "watch", "list"]
---
apiVersion: rbac.authorization.k8s.io/v1
kind: RoleBinding
metadata:
  name:  secret-reader
  namespace: <gateway_namespace>
roleRef:
  apiGroup: rbac.authorization.k8s.io
  kind: Role
  name: secret-reader
subjects:
  - kind: ServiceAccount
    name:  secret-reader
```

Apply the YAML file by running the following command:

```shell-session
$ oc apply -f secret-reader.yml
```

Create a YAML file named`gateway-deployment.yml` that defines the Kubernetes`Deployment` object for the gateway.

```yaml
apiVersion: apps/v1
kind: Deployment
metadata:
  name: <gateway_name>
  namespace: <gateway_namespace>
spec:
  selector:
    matchLabels:
      istio: <gateway_name>
  template:
    metadata:
      annotations:
        inject.istio.io/templates: gateway 
```

1

```yaml

      labels:
        istio: <gateway_name> 
```

2

```yaml

        sidecar.istio.io/inject: "true" 
```

3

```yaml

    spec:
      containers:
        - name: istio-proxy
          image: auto 
```

4

```yaml

          securityContext:
            capabilities:
              drop:
              - ALL
            allowPrivilegeEscalation: false
            privileged: false
            readOnlyRootFilesystem: true
            runAsNonRoot: true
          ports:
          - containerPort: 15090
            protocol: TCP
            name: http-envoy-prom
          resources:
            limits:
              cpu: 2000m
              memory: 1024Mi
            requests:
              cpu: 100m
              memory: 128Mi
      securityContext:
        sysctls:
        - name: net.ipv4.ip_unprivileged_port_start
          value: "0"
      serviceAccountName: secret-reader 
```

5

1

Indicates that the Istio control plane uses the gateway injection template instead of the default sidecar template.

2

Ensure that a unique label is set for the gateway deployment. A unique label is required so that Istio`Gateway` resources can select gateway workloads.

3

Enables gateway injection by setting the`sidecar.istio.io/inject` label to`true`. If the name of the Istio resource is not`default` you must use the`istio.io/rev: ` label instead, where the revision represents the active revision of the Istio resource.

4

Sets the image field to`auto` so that the image automatically updates each time the pod starts.

5

Sets the`serviceAccountName` to the name of the`ServiceAccount` created previously.

Apply the YAML file by running the following command:

```shell-session
$ oc apply -f gateway-deployment.yml
```

Verify that the gateway`Deployment` rollout was successful by running the following command:

```shell-session
$ oc rollout status deployment/<gateway_name> -n <gateway_namespace>
```

You should see output similar to the following:

Example output

```shell-session
Waiting for deployment "<gateway_name>" rollout to finish: 0 of 1 updated replicas are available...
deployment "<gateway_name>" successfully rolled out
```

Create a YAML file named`gateway-service.yml` that contains the Kubernetes`Service` object for the gateway.

```yaml
apiVersion: v1
kind: Service
metadata:
  name: <gateway_name>
  namespace: <gateway_namespace>
spec:
  type: ClusterIP 
```

1

```yaml

  selector:
    istio: <gateway_name> 
```

2

```yaml

  ports:
    - name: status-port
      port: 15021
      protocol: TCP
      targetPort: 15021
    - name: http2
      port: 80
      protocol: TCP
      targetPort: 80
    - name: https
      port: 443
      protocol: TCP
      targetPort: 443
```

1

When you set`spec.type` to`ClusterIP` the gateway`Service` object can be accessed only from within the cluster. If the gateway has to handle ingress traffic from outside the cluster, set`spec.type` to`LoadBalancer`. Alternatively, you can use OpenShift Routes.

2

Set the`selector` to the unique label or set of labels specified in the pod template of the gateway deployment that you previously created.

Apply the YAML file by running the following command:

```shell-session
$ oc apply -f gateway-service.yml
```

Verify that the gateway service is targeting the endpoint of the gateway pods by running the following command:

```shell-session
$ oc get endpoints <gateway_name> -n <gateway_namespace>
```

You should see output similar to the following example:

Example output

```shell-session
NAME              ENDPOINTS                             AGE
<gateway_name>    10.131.0.181:8080,10.131.0.181:8443   1m
```

Optional: Create a YAML file named`gateway-hpa.yml` that defines a horizontal pod autoscaler for the gateway. The following example sets the minimum replicas to`2` and the maximum replicas to`5` and scales the replicas up when average CPU utilization exceeds 80% of the CPU resource limit. This limit is specified in the pod template of the deployment for the gateway.

```yaml
apiVersion: autoscaling/v2
kind: HorizontalPodAutoscaler
metadata:
  name: <gateway_name>
  namespace: <gateway_namespace>
spec:
  minReplicas: 2
  maxReplicas: 5
  metrics:
  - resource:
      name: cpu
      target:
        averageUtilization: 80
        type: Utilization
    type: Resource
  scaleTargetRef:
    apiVersion: apps/v1
    kind: Deployment
    name: <gateway_name> 
```

1

1

Set`spec.scaleTargetRef.name` to the name of the gateway deployment previously created.

Optional: Apply the YAML file by running the following command:

```shell-session
$ oc apply -f gateway-hpa.yml
```

Optional: Create a YAML file named`gateway-pdb.yml` that defines a pod disruption budget for the gateway. The following example allows gateway pods to be evicted only when at least 1 healthy gateway pod will remain on the cluster after the eviction.

```yaml
apiVersion: policy/v1
kind: PodDisruptionBudget
metadata:
  name: <gateway_name>
  namespace: <gateway_namespace>
spec:
  minAvailable: 1
  selector:
    matchLabels:
      istio: <gateway_name> 
```

1

1

Set the`spec.selector.matchLabels` to the unique label or set of labels specified in the pod template of the gateway deployment previously created.

Optional: Apply the YAML file by running the following command:

```shell-session
$ oc apply -f gateway-pdb.yml
```

## Chapter 2. Getting traffic into a meshCopy link

Using Istio APIs, you can configure gateway proxies that were installed using gateway injection to accept traffic originating from outside the mesh, and route that traffic to the services within the mesh.

You can expose gateway proxies to traffic outside a cluster by using either a`LoadBalancer` type`Service` or OpenShift`Routes`.

### 2.1. About ingress traffic routing approachesCopy link

Red Hat OpenShift Service Mesh offers two approaches to configure ingress traffic routing to services in the mesh. The approach depends on the service mesh deployment mode and traffic management requirements.

Ingress routing with gateway injection and Istio APIs When you install a gateway by using gateway injection, you can configure it to receive ingress traffic by using the Istio`Gateway` and`VirtualService` resources in combination.

The gateway injection approach is compatible with sidecar-based service mesh deployments where you enable sidecar injection in namespaces by using the`istio-injection=enabled` label or the`istio.io/rev= ` label.

Ingress routing with Kubernetes Gateway API The Kubernetes Gateway API provides a standardized approach for configuring ingress traffic routing using native Kubernetes resources. With this approach, you use`Gateway` and`HTTPRoute`(or`GRPCRoute`) resources to configure how traffic enters the mesh and routes to services.

While Istio`Gateway` and`VirtualService` resources can be used for certain ingress use cases in ambient mode, the recommended approach is to use the Kubernetes Gateway API, which provides full support and integration with ambient. You can also use the Gateway API with sidecar-based deployments.

### 2.2. Exposing a service by using the Istio Gateway and VirtualService resourcesCopy link

You can use the Istio`Gateway` and`VirtualService` resources to configure a gateway that was deployed by using gateway injection. The resources expose a service in the mesh to traffic outside the mesh. You can set the gateway`Service` type to`LoadBalancer` to allow traffic from outside the cluster.

Prerequisites

- You have installed Istio gateways using gateway injection.
- You are using the Istio`Gateway` and`VirtualService` resources.
- You have existing`VirtualService` configurations and do not plan on migrating to ambient mode.

Procedure

Create namespace called`httpbin` by running the following command:

```shell-session
$ oc create namespace httpbin
```

Enable sidecar injection in the namespace. If you are using the`InPlace` upgrade strategy, run the following command:

```shell-session
$ oc label namespace httpbin istio-injection=enabled
```

Note

If you are using the`RevisionBased` upgrade strategy, run the following commands:

To find your` `, run the following command:

```shell-session
$ oc get istiorevisions.sailoperator.io
```

You will get an output similar to the following example:

```plaintext
NAME      TYPE    READY   STATUS    IN USE   VERSION   AGE
default   Local   True    Healthy   True    v1.24.3   3m33s
```

Label the namespace with the revision name to enable sidecar injection:

```shell-session
$ oc label namespace httpbin istio.io/rev=default
```

Deploy a sample service named`httpbin` by running the following command:

```shell-session
$ oc apply -n httpbin -f https://raw.githubusercontent.com/openshift-service-mesh/istio/refs/heads/master/samples/httpbin/httpbin.yaml
```

Create a YAML file named`httpbin-gw.yaml` that defines an Istio`Gateway` resource. This resource configures gateway proxies to expose port 80 (HTTP) for the host,`httpbin.example.com`.

```yaml
apiVersion: networking.istio.io/v1
kind: Gateway
metadata:
  name: httpbin-gateway
  namespace: httpbin
spec:
  selector:
    istio: <gateway_name> 
```

1

```yaml

  servers:
  - port:
      number: 80
      name: http
      protocol: HTTP
    hosts:
    - httpbin.example.com 
```

2

1

Set the`selector` to the unique label or set of labels specified in the pod template of the gateway proxy`Deployment`. By default, the Istio`Gateway` resource configuration will apply to matching gateway pods in all namespaces.

2

Using the`hosts` field, specify a list of addresses that can be used by clients when attempting to access a mesh service at the associated port.

Apply the YAML file by running the following command:

```shell-session
$ oc apply -f httpbin-gw.yaml
```

Create a YAML file named`httpbin-vs.yaml` for a`VirtualService`. The`VirtualService` defines the rules that route traffic from the gateway proxy to the`httpbin` service.

```yaml
apiVersion: networking.istio.io/v1
kind: VirtualService
metadata:
  name: httpbin
  namespace: httpbin
spec:
  hosts:
  - httpbin.example.com 
```

1

```yaml

  gateways:
  - httpbin-gateway 
```

2

```yaml

  http:
  - match:
    - uri:
        prefix: /status
    - uri:
        prefix: /headers
    route:
    - destination: 
```

3

```yaml

        port:
          number: 8000
        host: httpbin
```

1

Specify the`hosts` that the routing rules of the`VirtualService` will be applied to. The`hosts` specified must be exposed by the Istio`Gateway` resource the VirtualService is bound to.

2

Bind the`VirtualService` to the Istio`Gateway` resource created in the previous step by adding the`Gateway` name to the list of gateways.

3

Route matching traffic to the`httpbin` service deployed earlier by defining a`destination` that includes the`host` and`port` of the`httpbin``Service`.

Apply the YAML file by running the following command:

```shell-session
$ oc apply -f httpbin-vs.yaml
```

For verification purposes, create a namespace for a`curl` client by running the following command:

```shell-session
$ oc create namespace curl
```

Deploy the`curl` client by running the following command:

```shell-session
$ oc apply -n curl -f https://raw.githubusercontent.com/openshift-service-mesh/istio/refs/heads/master/samples/curl/curl.yaml
```

Set a`CURL_POD` variable with the name of the`curl` pod by running the following command:

```shell-session
$ CURL_POD=$(oc get pods -n curl -l app=curl -o jsonpath='{.items[*].metadata.name}')
```

Using the`curl` client, send a request to the`/headers` endpoint of the`httpbin` application through the ingress gateway`Service` resource. Set the`Host` header of the request to`httpbin.example.com` to match the host that the Istio`Gateway` and`VirtualService` resources specify. Run the following`curl` command to send the request:

```shell-session
$ oc exec $CURL_POD -n curl -- \
  curl -s -I \
    -H Host:httpbin.example.com \
    <gateway_name>.<gateway_namespace>.svc.cluster.local/headers
```

The response should have a`200 OK HTTP` status indicating that the request was successful.

```shell-session
HTTP/1.1 200 OK
server: istio-envoy
...
```

Send a curl request to an endpoint that does not have a corresponding URI prefix match defined in the`httpbin``VirtualService` by running the following command:

```shell-session
$ oc exec $CURL_POD -n curl -- \
  curl -s -I \
    -H Host:httpbin.example.com \
    <gateway_name>.<gateway_namespace>.svc.cluster.local/get
```

The response should return a`404 Not Found` status. This is expected because the`/get` endpoint does not have a matching URI prefix in the`httpbin``VirtualService` resource.

```shell-session
HTTP/1.1 404 Not Found
server: istio-envoy
...
```

Expose the gateway proxy to traffic outside the cluster by setting the`Service` type to`LoadBalancer`:

```shell-session
$ oc patch service <gateway_name> -n <gateway_namespace> -p '{"spec": {"type": "LoadBalancer"}}'
```

Note

A gateway can also be exposed to traffic outside the cluster by using OpenShift Routes. For more information, see "Exposing a gateway to traffic outside the cluster using OpenShift Routes".

Verify that`httpbin` service can be accessed from outside the cluster when using the external hostname or IP address of the gateway`Service` resource. Ensure that you set the`INGRESS_HOST` variable appropriately for the environment that your cluster is running in.

If the cluster runs on AWS, set the`INGRESS_HOST` variable by running the following command:

```shell-session
$ INGRESS_HOST=$(oc get service <gateway_name> -n <gateway_namespace> -o jsonpath='{.status.loadBalancer.ingress[0].hostname}')
```

If the cluster runs on GCP or Azure, set the`INGRESS_HOST` variable by running the following command:

```shell-session
$ INGRESS_HOST=$(oc get service <gateway_name> -n <gateway_namespace> -o jsonpath='{.status.loadBalancer.ingress[0].ip}')
```

Send a`curl` request to the`httpbin` service using the host of the gateway by running the following command:

```shell-session
$ curl -s -I -H Host:httpbin.example.com http://$INGRESS_HOST/headers
```

### 2.3. About exposing services to traffic outside a clusterCopy link

To enable traffic from outside an OpenShift cluster to access services in a mesh, you must expose a gateway proxy by either setting its`Service` type to`LoadBalancer` or by using the OpenShift Router.

Using Kubernetes load balancing to handle incoming traffic directly through the inbound gateway can reduce latency associated with data encryption. By managing encryption at the inbound gateway, you avoid the intermediate decryption and re-encryption steps within the mesh that often add latency. This approach allows mesh traffic to be encrypted and decrypted only once, which is generally more efficient.

The OpenShift Router provides a standard approach for managing ingress traffic, and you can use the router to manage certificates for all cluster ingress traffic using the same methods. However, the OpenShift Router introduces an additional hop between the inbound traffic and the mesh applications. Typically, you route the traffic by decrypting it at the router and then re-encrypting it at the service mesh ingress gateway, which introduces latency.

#### 2.3.1. Exposing a gateway to traffic outside the cluster by using OpenShift RoutesCopy link

You can expose a gateway to traffic outside the cluster by using OpenShift Routes. This approach provides an alternative to using Kubernetes load balancer service when you have to expose gateways to traffic outside the cluster.

Prerequisites

- You have completed the procedure, Exposing a Service by using the Istio Gateway and VirtualService resources.

Procedure

Ensure that the`Service` type is set to`ClusterIP` by running the following command:

```shell-session
$ oc patch service <gateway_name> -n <gateway_namespace> -p '{"spec": {"type": "ClusterIP"}}'
```

Create a YAML file named`httpbin-route.yaml` that defines a`Route` for the`httpbin` service.

```yaml
apiVersion: route.openshift.io/v1
kind: Route
metadata:
  name: httpbin
  namespace: <gateway_namespace>
spec:
  host: httpbin.example.com
  port:
    targetPort: http2
  to:
    kind: Service
    name: <gateway_name>
    weight: 100
  wildcardPolicy: None
```

Apply the YAML file by running the following command:

```shell-session
$ oc apply -f httpbin-route.yaml
```

Verify that`httpbin` service can be accessed from outside the cluster through the ingress router. Ensure that you set the`INGRESS_HOST` variable appropriately for the environment that your cluster is running in.

If the cluster runs on AWS, set the`INGRESS_HOST` variable by running the following command:

```shell-session
$ INGRESS_HOST=$(oc get service router-default -n openshift-ingress -o jsonpath='{.status.loadBalancer.ingress[0].hostname}')
```

If the cluster runs on GCP or Azure, set the`INGRESS_HOST` variable by running the following command:

```shell-session
$ INGRESS_HOST=$(oc get service router-default -n openshift-ingress -o jsonpath='{.status.loadBalancer.ingress[0].ip}')
```

Send a`curl` request to the`httpbin` service using the host of the ingress router by running the following command:

```shell-session
$ curl -s -I -H Host:httpbin.example.com http://$INGRESS_HOST/headers
```

### 2.4. Exposing a service by using the Kubernetes Gateway API in sidecar modeCopy link

You can use the Kubernetes Gateway API to create`Gateway` and`HTTPRoute` resources and deploy a gateway. The resources configure the gateway to expose a service in the mesh to traffic outside the mesh.

Prerequisites

- You are logged in to the OpenShift Container Platform web console as a user with the`cluster-admin` role.
- You installed the Red Hat OpenShift Service Mesh Operator.
- You have deployed the`Istio` resource.

Procedure

Create a namespace called`httpbin` by running the following command:

```shell-session
$ oc create namespace httpbin
```

When using sidecar injection instead of ambient mode, you must enable the sidecar injection in the namespace:

For the`InPlace` upgrade strategy, run the following command:

```shell-session
$ oc label namespace httpbin istio-injection=enabled
```

For the`RevisionBased` upgrade strategy, run the following command:

```shell-session
$ oc label namespace httpbin istio.io/rev=<revision-name>
```

Deploy a sample service named`httpbin` by running the following command:

```shell-session
$ oc apply -n httpbin -f https://raw.githubusercontent.com/openshift-service-mesh/istio/refs/heads/master/samples/httpbin/httpbin.yaml
```

Create a YAML file named`httpbin-gw.yaml` that defines a Kubernetes Gateway resource, similar to the following example:

```yaml
apiVersion: gateway.networking.k8s.io/v1
kind: Gateway
metadata:
  name: httpbin-gateway
  namespace: httpbin
spec:
  gatewayClassName: istio
  listeners:
  - name: default
    hostname: "httpbin.example.com"
    port: 80
    protocol: HTTP
    allowedRoutes:
      namespaces:
        from: All
```

`"httpbin.example.com"` Specifies the virtual hostname that clients use when attempting to access a mesh service on the associated port.

The`HTTPRoute` resource specifies the rules that route traffic from the gateway proxy to the`httpbin` service.

Apply the YAML file by running the following command:

```shell-session
$ oc apply -f httpbin-gw.yaml
```

Create a YAML file named`httpbin-ingress-hr.yaml` that defines an`HTTPRoute` resource for the ingress gateway, similar to the following example:

```yaml
apiVersion: gateway.networking.k8s.io/v1
kind: HTTPRoute
metadata:
  name: httpbin-ingress
  namespace: httpbin
spec:
  parentRefs:
  - name: httpbin-gateway
    namespace: httpbin
  hostnames:
  - "httpbin.example.com"
  rules:
  - matches:
    - path:
        type: PathPrefix
        value: /status
    - path:
        type: PathPrefix
        value: /headers
  - backendRefs:
    - name: httpbin
      port: 8000
```

- `spec.parentRefs` binds the`HTTPROUTE` resource to the Kubernetes Gateway resource that was created in the earlier step.
- `spec.rules.backendRefs` routes the matching traffic to the`httpbin` service by defining a`backendRefs` that includes the name and port of the`httpbin` service.

The`HTTPRoute` resource specifies the rules that route traffic from the gateway proxy to the`httpbin` service.

Apply the YAML file by running the following command:

```shell-session
$ oc apply -f httpbin-ingress-hr.yaml
```

Ensure that the Gateway API service is ready, and that an address is allocated to the service, by running the following command:

```shell-session
$ oc wait --for=condition=programmed gtw httpbin-gateway -n httpbin
```

Verification

Create a namespace for a`curl` client by running the following command:

```shell-session
$ oc create namespace curl
```

Deploy a`curl` client by running the following command:

```shell-session
$ oc apply -n curl -f https://raw.githubusercontent.com/openshift-service-mesh/istio/refs/heads/master/samples/curl/curl.yaml
```

Set a`CURL_POD` variable with the name of the`curl` pod by running the following command:

```shell-session
$ CURL_POD=$(oc get pods -n curl -l app=curl -o jsonpath='{.items[*].metadata.name}')
```

Using the`curl` client, send a request to the`/headers` endpoint of the`httpbin` application through the ingress gateway`Service` resource. Set the Host header of the request to`httpbin.example.com` to match the host that the Kubernetes Gateway and`HTTPROUTE` resources specify. Send the`curl` request by running the following command:

```shell-session
$ oc exec $CURL_POD -n curl -- \
  curl -s -I \
    -H Host:httpbin.example.com \
    <gateway_name>-istio.<gateway_namespace>.svc.cluster.local/headers
```

The response should return a`200 OK` HTTP status, which indicates that the request was successful, similar to the following example:

```shell-session
HTTP/1.1 200 OK
server: istio-envoy
...
```

Send a`curl` request to an endpoint that does not have a corresponding Uniform Resource Identifier (URI) prefix match defined in the`httpbin``HTTPROUTE` by running the following command:

```shell-session
$ oc exec $CURL_POD -n curl -- \
  curl -s -I \
    -H Host:httpbin.example.com \
    <gateway_name>-istio.<gateway_namespace>.svc.cluster.local/get
```

The response returns a`404 Not Found` status, as expected, because the`/get` endpoint does not have a matching URI prefix in the`httpbin``HTTPROUTE` resource, similar to the following example:

```shell-session
HTTP/1.1 404 Not Found
server: istio-envoy
...
```

Expose the gateway proxy to traffic outside the cluster by setting the`Service` type to`LoadBalancer`. Run the following command:

```shell-session
$ oc patch service <gateway_name>-istio -n <gateway_namespace> -p '{"spec": {"type": "LoadBalancer"}}'
```

Note

A gateway can also be exposed to traffic outside the cluster by using OpenShift Routes. For more information, see "Exposing a gateway to traffic outside the cluster using OpenShift Routes".

Verify that the`httpbin` service can be accessed from outside the cluster when using the external hostname or IP address of the gateway Service resource. Ensure that you set the`INGRESS_HOST` variable appropriately for the environment in which your cluster is running.

Set the`INGRESS_HOST` variable by running the following command:

```shell-session
$ export INGRESS_HOST=$(oc get gtw <gateway_name> -n <gateway_namespace> -o jsonpath='{.status.addresses[0].value}')
```

Set the`INGRESS_PORT` variable by running the following command:

```shell-session
$ INGRESS_PORT=$(oc get gtw <gateway_name> -n <gateway_namespace> -o jsonpath='{.spec.listeners[?(@.name=="http")].port}')
```

Using the gateway host, send a`curl` request to the`httpbin` service by running the following command:

```shell-session
$ curl -s -I -H Host:httpbin.example.com http://$INGRESS_HOST:$INGRESS_PORT/headers
```

### 2.5. About ingress traffic routing approaches in ambient modeCopy link

When using the Istio ambient mode, you can use the Kubernetes Gateway API to configure ingress traffic routing.

Waypoint proxies for Layer 7 routing You can deploy a waypoint proxy in the namespace that has your service to apply Layer 7 (L7) routing policies, such as path-based routing or header matching. In ambient mode, waypoint proxies process L7 traffic and enforce`HTTPRoute` and`GRPCRoute` rules.

Important

`VirtualService` resources are considered technology preview in ambient mode and should not be mixed with Gateway API configuration. The recommended approach in ambient mode is to use Kubernetes Gateway API resources.

### 2.6. Exposing a service by using the Kubernetes Gateway API in ambient modeCopy link

You can use the Kubernetes Gateway API to create`Gateway` and`HTTPRoute` resources and deploy a gateway in ambient mode. The resources configure the gateway to expose a service in the mesh to traffic outside the mesh.

Prerequisites

- You are logged in to the OpenShift Container Platform web console as a user with the`cluster-admin` role.
- You have installed the Red Hat OpenShift Service Mesh Operator.
- You have deployed the`Istio` resource.
- You use the Kubernetes-native Gateway API resources.
- You are either using the Istio ambient mode or planning on migrating to the ambient mode.

Note

When using ambient mode (`istio.io/dataplane-mode=ambient`), it is recommended to use the Kubernetes Gateway API for ingress configuration, as Istio`Gateway` and`VirtualService` resources are not fully compatible with ambient mode.

Procedure

Create a namespace called`httpbin` by running the following command:

```shell-session
$ oc create namespace httpbin
```

Apply the label for ambient mode by running the following command:

```shell-session
$ oc label namespace httpbin istio.io/dataplane-mode=ambient
```

Deploy a sample service named`httpbin` by running the following command:

```shell-session
$ oc apply -n httpbin -f https://raw.githubusercontent.com/openshift-service-mesh/istio/refs/heads/master/samples/httpbin/httpbin.yaml
```

Deploy a waypoint proxy by creating a YAML file named`httpbin-waypoint.yaml`, similar to the following example:

```yaml
apiVersion: gateway.networking.k8s.io/v1
kind: Gateway
metadata:
  name: httpbin-waypoint
  namespace: httpbin
  labels:
    istio.io/waypoint-for: service
spec:
  gatewayClassName: istio-waypoint
  listeners:
  - name: mesh
    port: 15008
    protocol: HBONE
```

Apply the YAML file by running the following command:

```shell-session
$ oc apply -f httpbin-waypoint.yaml
```

Enable ingress waypoint routing on the`httpbin` service by running the following command:

```shell-session
$ oc label service httpbin -n httpbin istio.io/ingress-use-waypoint=true
```

The label ensures that traffic from the ingress gateway routes through the waypoint proxy and the Layer 7 (L7) policies configured on the waypoint proxy are applied to the ingress traffic, before it reaches the`httpbin` service.

Apply the waypoint label to the namespace so that all the services inside the namespace routes through the waypoint, by running the following command:

```shell-session
$ oc label ns httpbin istio.io/use-waypoint=httpbin-waypoint
```

Create a YAML file named`httpbin-gw.yaml` that defines a Kubernetes Gateway resource. This resource configures gateway proxies to expose port 80 (HTTP) for the host,`httpbin.example.com`.

```yaml
apiVersion: gateway.networking.k8s.io/v1
kind: Gateway
metadata:
  name: httpbin-gateway
  namespace: httpbin
spec:
  gatewayClassName: istio
  listeners:
  - name: default
    hostname: "httpbin.example.com"
    port: 80
    protocol: HTTP
    allowedRoutes:
      namespaces:
        from: All
```

`"httpbin.example.com"` Specifies the virtual hostname that clients use when attempting to access a mesh service on the associated port.

Apply the YAML file by running the following command:

```shell-session
$ oc apply -f httpbin-gw.yaml
```

Create a YAML file named`httpbin-ingress-hr.yaml` that defines an`HTTPRoute` resource for the ingress gateway, similar to the following example:

```yaml
apiVersion: gateway.networking.k8s.io/v1
kind: HTTPRoute
metadata:
  name: httpbin-ingress
  namespace: httpbin
spec:
  parentRefs:
  - name: httpbin-gateway
    namespace: httpbin
  hostnames:
  - "httpbin.example.com"
  rules:
  - backendRefs:
    - name: httpbin
      port: 8000
```

- `spec.parentRefs` binds the`HTTPROUTE` resource to the Kubernetes Gateway resource that was created in the earlier step.
- `spec.rules.backendRefs` routes the matching traffic to the`httpbin` service by defining a`backendRefs` that includes the name and port of the`httpbin` service.

The`HTTPRoute` resource specifies the rules that route traffic from the gateway proxy to the`httpbin` service.

Apply the YAML file by running the following command:

```shell-session
$ oc apply -f httpbin-ingress-hr.yaml
```

Create a YAML file named`httpbin-waypoint-hr.yaml` that defines an`HTTPRoute` resource for the waypoint proxy.

```yaml
apiVersion: gateway.networking.k8s.io/v1
kind: HTTPRoute
metadata:
  name: httpbin-waypoint-route
  namespace: httpbin
spec:
  parentRefs:
  - group: ""
    kind: service
    name: httpbin
    namespace: httpbin
  rules:
  - matches:
    - path:
        type: PathPrefix
        value: /status
    - path:
        type: PathPrefix
        value: /headers
    backendRefs:
    - name: httpbin
      port: 8000
```

- `spec.parentRefs` binds the`HTTPRoute` resource to the`httpbin` service. When combined with the`istio.io/ingress-use-waypoint=true` label on the service, the`HTTPRoute` configures the L7 routing rules that the waypoint proxy will enforce for traffic destined to that service.
- `spec.rules.backendRefs` routes the matching traffic to the`httpbin` service by defining a`backendRefs` that includes the name and port of the`httpbin` service.

Apply the YAML file by running the following command:

```shell-session
$ oc apply -f httpbin-waypoint-hr.yaml
```

Note

In this example use case, traffic from the ingress gateway flows through the waypoint proxy because of the`istio.io/ingress-use-waypoint=true` label. The`HTTPRoute` resource then applies path-based routing policies before the traffic reaches the httpbin service.

Ensure that the waypoint proxy is ready by running the following command:

```shell-session
$ oc wait --for=condition=programmed gtw httpbin-waypoint -n httpbin
```

Verification

Create a namespace for a`curl` client by running the following command:

```shell-session
$ oc create namespace curl
```

Deploy a`curl` client by running the following command:

```shell-session
$ oc apply -n curl -f https://raw.githubusercontent.com/openshift-service-mesh/istio/refs/heads/master/samples/curl/curl.yaml
```

Apply the label for ambient mode to the`curl` namespace by running the following command:

```shell-session
$ oc label namespace curl istio.io/dataplane-mode=ambient
```

Set a`CURL_POD` variable with the name of the`curl` pod by running the following command:

```shell-session
$ CURL_POD=$(oc get pods -n curl -l app=curl -o jsonpath='{.items[*].metadata.name}')
```

Using the`curl` client, send a request to the`/headers` endpoint of the`httpbin` application through the ingress gateway`Service` resource. Set the Host header of the request to`httpbin.example.com` to match the host that the Kubernetes Gateway and`HTTPROUTE` resources specify. Send the`curl` request by running the following command:

```shell-session
$ oc exec $CURL_POD -n curl -- \
  curl -s -I \
    -H Host:httpbin.example.com \
    httpbin-gateway-istio.httpbin.svc.cluster.local/headers
```

The response should return a`200 OK` HTTP status, which indicates that the request was successful, similar to the following example:

```shell-session
HTTP/1.1 200 OK
server: istio-envoy
...
```

Send a`curl` request to an endpoint that does not have a corresponding Uniform Resource Identifier (URI) prefix match defined in the`httpbin``HTTPROUTE` by running the following command:

```shell-session
$ oc exec $CURL_POD -n curl -- \
  curl -s -I \
    -H Host:httpbin.example.com \
    httpbin-gateway-istio.httpbin.svc.cluster.local/get
```

The response returns a`404 Not Found` status, as expected, because the`/get` endpoint does not have a matching URI prefix in the`httpbin``HTTPROUTE` resource, similar to the following example:

```shell-session
HTTP/1.1 404 Not Found
server: istio-envoy
...
```

Expose the gateway proxy to traffic outside the cluster by setting the`Service` type to`LoadBalancer`. Run the following command:

```shell-session
$ oc patch service httpbin-gateway-istio -n httpbin -p '{"spec": {"type": "LoadBalancer"}}'
```

Note

A gateway can also be exposed to traffic outside the cluster by using OpenShift Routes. For more information, see "Exposing a gateway to traffic outside the cluster using OpenShift Routes".

Verify that the`httpbin` service can be accessed from outside the cluster when using the external hostname or IP address of the gateway Service resource. Ensure that you set the`INGRESS_HOST` variable appropriately for the environment in which your cluster is running.

Set the`INGRESS_HOST` variable by running the following command:

```shell-session
$ export INGRESS_HOST=$(oc get gtw httpbin-gateway -n httpbin -o jsonpath='{.status.addresses[0].value}')
```

Set the`INGRESS_PORT` variable by running the following command:

```shell-session
$ INGRESS_PORT=$(oc get gtw httpbin-gateway -n httpbin -o jsonpath='{.spec.listeners[?(@.name=="http")].port}')
```

Using the gateway host, send a`curl` request to the`httpbin` service by running the following command:

```shell-session
$ curl -s -I -H Host:httpbin.example.com http://$INGRESS_HOST:$INGRESS_PORT/headers
```

## Chapter 3. Directing outbound traffic through a gatewayCopy link

Using Istio APIs, you can configure gateway proxies that were installed using gateway injection to direct traffic that is bound for an external service.

### 3.1. About directing egress traffic through a gatewayCopy link

You can configure a gateway installed through gateway injection as an exit point for traffic leaving the service mesh. It acts as a forward proxy for requests sent to services external to the mesh.

Egress gateway

An egress gateway is configured as an exit point for traffic leaving the service mesh, acting as a forward proxy for requests sent to external services. You can configure an egress gateway to fulfill security requirements:

- Verify that the response has the`HTTP/1.1 200 OK` status, which indicates that the request was successful.
- Traffic Restrictions: In environments with strict traffic restrictions, an egress gateway ensures all outbound traffic flows through a dedicated set of nodes.
- Network Policy Enforcement: When network policies prevent application nodes from directly accessing external services, the egress gateway handles the external access.

In these scenarios, gateway proxies are deployed on dedicated egress nodes capable of accessing external services. These nodes can then be subjected to strict network policy enforcement or additional monitoring to enhance security.

Configure egress traffic

You can configure a gateway installed through gateway injection to direct the egress traffic by combining the following Istio resources:

- Use the`ServiceEntry` resource to define the properties of an external service. The external service is added to the Istio service registry for the mesh, which enables you to apply Istio features, such as monitoring and routing rules, to the traffic exiting the mesh that is destined for an external service.
- Use the`Gateway`,`VirtualService`, and`DestinationRule` resources to set up rules that route traffic from the mesh to the external service using the gateway proxy.

Egress routing in ambient mode

If your deployment uses ambient mode, you must configure egress routing using the Kubernetes Gateway API instead of Istio`Gateway` and`VirtualService` resources. The Kubernetes Gateway API provides a standardized, Kubernetes-native method for defining how traffic exits the mesh and reaches external services.

You can use`Gateway` and`HTTPRoute`(or`GRPCRoute`) resources to control how mesh traffic is routed to destinations outside the cluster. The Gateway API is fully supported in ambient mode and can also be used with sidecar-based deployments, providing a consistent configuration model for both ingress and egress routing.

### 3.2. Directing egress traffic through a gateway using Istio APIsCopy link

Use Istio APIs to direct outbound HTTP traffic through a gateway that was installed using gateway injection.

Prerequisites

- You have installed a gateway using gateway injection.

Procedure

Create a namespace called`curl` by running the following command:

```shell-session
$ oc create namespace curl
```

Depending on the update strategy you are using, enable sidecar injection in the namespace by running the appropriate commands:

If you are using the`InPlace` update strategy, run the following command:

```shell-session
$ oc label namespace curl istio-injection=enabled
```

If you are using the`RevisionBased` update strategy, run the following commands:

Display the revision name by running the following command:

```shell-session
$ oc get istiorevisions.sailoperator.io
```

Example output

```shell-session
NAME     TYPE    READY   STATUS    IN USE   VERSION   AGE
default  Local   True    Healthy   True     v1.24.3   3m33s
```

Label the namespace with the revision name to enable sidecar injection by running the following command:

```shell-session
$ oc label namespace curl istio.io/rev=default
```

Deploy a`curl` application by running the following command:

```shell-session
$ oc apply -n curl -f https://raw.githubusercontent.com/openshift-service-mesh/istio/refs/heads/master/samples/curl/curl.yaml
```

Export a`CURL_POD` environment variable that has been initialized with the name of the curl pod:

```shell-session
$ export CURL_POD=$(oc get pod -n curl -l app=curl -o jsonpath='{.items[0].metadata.name}')
```

Create a YAML file named`http-se.yaml` that directs traffic from the mesh to an external service. The following example defines a`ServiceEntry` for a URL.

```yaml
apiVersion: networking.istio.io/v1
kind: ServiceEntry
metadata:
  name: egress-se
  namespace: curl
spec:
  hosts:
    - docs.redhat.com
  ports:
    - number: 80
      name: http-port
      protocol: HTTP
  location: MESH_EXTERNAL
  resolution: DNS
```

Apply the YAML file by running the following command:

```shell-session
$ oc apply -f http-se.yaml
```

Ensure the`ServiceEntry` configuration was applied correctly. Send an HTTP request to the host that you specified in the previous step by running the following command:

```shell-session
$ oc exec "$CURL_POD" -n curl -c curl -- curl -sSL -o /dev/null -D - http://docs.redhat.com
```

This command should return HTTP status codes, such as`301`(redirect) or`200`(success), indicating that the connection works.

Create a YAML file named`http-gtw.yaml` that creates an egress`Gateway` and routes traffic from the mesh to the host specified for the external service.

```yaml
apiVersion: networking.istio.io/v1alpha3
kind: Gateway
metadata:
  name: egress-gw
  namespace: <gateway_namespace> # Namespace where the egress gateway is deployed
spec:
  selector:
    istio: <gateway_name> # Selects the egress-gateway instance to handle this traffic
  servers:
    - port:
        number: 80
        name: http
        protocol: HTTP
      hosts:
        - docs.redhat.com # External service host, not a full URL.
---
apiVersion: networking.istio.io/v1alpha3
kind: DestinationRule
metadata:
  name: egress-dr
  namespace: <gateway_namespace> # Namespace where the egress gateway is deployed
spec:
  host: <gateway_name>.<gateway_namespace>.svc.cluster.local
  subsets:
    - name: rh-docs
```

Apply the YAML file by running the following command:

```shell-session
$ oc apply -f http-gtw.yaml
```

Create a YAML file named`http-vs.yaml` that sets up a`VirtualService` to manage the flow of traffic from the application sidecars through the egress gateway to the external host.

```yaml
apiVersion: networking.istio.io/v1alpha3
kind: VirtualService
metadata:
  name: egress-vs
  namespace: curl # Namespace where the curl pod is running
spec:
  hosts:
    - docs.redhat.com # External service host, not a full URL.
  gateways:
    - mesh
    - <gateway_namespace>/egress-gw # Egress gateway name defined in the file that you used in the previous step.
  http:
    - match:
        - gateways:
            - mesh
          port: 80
      route:
        - destination:
            host: <gateway_name>.<gateway_namespace>.svc.cluster.local
            subset: rh-docs
            port:
              number: 80
          weight: 100
    - match:
        - gateways:
            - <gateway_namespace>/egress-gw # Egress gateway name defined in the file that you used in the previous step.
          port: 80
      route:
        - destination:
            host: docs.redhat.com
            port:
              number: 80
          weight: 100
```

Apply the YAML file by running the following command:

```shell-session
$ oc apply -f http-vs.yaml
```

Resend the HTTP request to the URL:

```shell-session
$ oc exec "$CURL_POD" -n curl -c curl -- curl -sSL -o /dev/null -D - http://docs.redhat.com
```

The terminal should display information similar to the following output:

```shell-session
...
HTTP/1.1 301 Moved Permanently
...
location: <example_url>
...

HTTP/2 200
Content-Type: text/html; charset=utf-8
```

Ensure that the request was routed through the gateway by running the following command:

```shell-session
$ oc logs deployment/<gateway_name> -n <gateway_namespace> | tail -1
```

Note

Access logging must be enabled for this verification step to work. You can enable access logging to the standard output by setting the`spec.values.meshConfig.accessLogFile` field to`/dev/stdout` in the Istio resource.

The terminal should display information similar to the following output:

```shell-session
[2024-11-07T14:35:52.428Z] "GET / HTTP/2" 301 - via_upstream - "-" 0 0 24 24 "10.128.2.30" "curl/8.11.0" "79551af2-341b-456d-b414-9220b487a03b" "docs.redhat.com" "23.55.176.201:80" outbound|80||docs.redhat.com 10.128.2.29:49766 10.128.2.29:80 10.128.2.30:38296 -
```

### 3.3. Directing egress traffic through a gateway by using the Kubernetes Gateway APICopy link

Use the Kubernetes Gateway API to direct outbound HTTP traffic through an egress gateway.

Prerequisites

- You installed an Istio control plane.
- You configured the`Istio` and`IstioCNI` resources.

Procedure

Optional: Enable the Kubernetes Gateway API custom resource definitions (CRDs).

Note

As of Kubernetes 1.28 and OpenShift Container Platform 4.18 or earlier version of Red Hat OpenShift Service Mesh, the Kubernetes Gateway API CRDs are not available by default and you must install the CRDs before you can use them. OpenShift Container Platform 4.19 and later versions install the CRDs by default.

Create a YAML file named`gateway-cr.yaml` that enables the Kubernetes Gateway API CRDs.

```yaml
apiVersion: gateway.networking.k8s.io/v1
kind: Gateway
metadata:
  name: bookinfo-gateway
spec:
  gatewayClassName: istio
  listeners:
  - name: http
    port: 80
    protocol: HTTP
    allowedRoutes:
      namespaces:
        from: Same
---
apiVersion: gateway.networking.k8s.io/v1
kind: HTTPRoute
metadata:
  name: bookinfo
spec:
  parentRefs:
  - name: bookinfo-gateway
  rules:
  - matches:
    - path:
        type: Exact
        value: /productpage
    - path:
        type: PathPrefix
        value: /static
    - path:
        type: Exact
        value: /login
    - path:
        type: Exact
        value: /logout
    - path:
        type: PathPrefix
        value: /api/v1/products
    backendRefs:
    - name: productpage
      port: 9080
```

Apply the YAML file by running the following command:

```shell-session
$ oc apply -f gateway-cr.yaml
```

Create a namespace called`egress-gateway` by running the following command:

```shell-session
$ oc create namespace egress-gateway
```

Apply the`istio-injection` label to the namespace by running the following command:

```shell-session
$ oc label namespace egress-gateway istio-injection=enabled
```

Create a YAML file named`egress-gateway-cr.yaml` that defines the egress gateway.

```yaml
# ServiceEntry to allow traffic to httpbin.org
apiVersion: networking.istio.io/v1
kind: ServiceEntry
metadata:
  name: httpbin-ext
spec:
  hosts:
  - httpbin.org
  ports:
  - number: 80
    name: http
    protocol: HTTP
  location: MESH_EXTERNAL
  resolution: DNS
---
# Gateway API Gateway for egress
apiVersion: gateway.networking.k8s.io/v1
kind: Gateway
metadata:
  name: httpbin-egress-gateway
  annotations:
    networking.istio.io/service-type: ClusterIP
spec:
  gatewayClassName: istio
  listeners:
  - name: http
    hostname: httpbin.org
    port: 80
    protocol: HTTP
    allowedRoutes:
      namespaces:
        from: Same
---
# HTTPRoute to direct traffic from sidecars to the egress gateway
apiVersion: gateway.networking.k8s.io/v1
kind: HTTPRoute
metadata:
  name: direct-httpbin-to-egress-gateway
spec:
  parentRefs:
  - kind: ServiceEntry
    group: networking.istio.io
    name: httpbin-ext
  rules:
  - backendRefs:
    - name: httpbin-egress-gateway-istio
      port: 80
---
# HTTPRoute to forward traffic from the egress gateway to httpbin.org
apiVersion: gateway.networking.k8s.io/v1
kind: HTTPRoute
metadata:
  name: forward-httpbin-from-egress-gateway
spec:
  parentRefs:
  - name: httpbin-egress-gateway
  hostnames:
  - httpbin.org
  rules:
  - backendRefs:
    - kind: Hostname
      group: networking.istio.io
      name: httpbin.org
      port: 80
```

Apply the YAML file by running the following command:

```shell-session
$ oc apply -f egress-gateway-cr.yaml
```

Verification

Verify the status of the gateway configuration by running the following command:

```shell-session
$ oc describe gateway -n egress-gateway
```

Desired output is indicated by`Programmed` showing in the`Status` column.

Create a`curl` pod in the`egress-gateway` namespace by running the following command:

```shell-session
$ oc run test-pod --image=curlimages/curl:latest -n egress-gateway --rm -it --restart=Never -- sh
```

By using the`curl` client, verify that you can access`httpbin.org` through the egress gateway by entering following command:

```shell-session
$ curl -v http://httpbin.org/get
```

Desired output shows a response from`httpbin.org` that indicates egress traffic routes through the configured gateway.

### 3.4. Directing egress traffic through a gateway using the Kubernetes Gateway API in ambient modeCopy link

Use the Kubernetes Gateway API and waypoint proxy to direct outbound HTTP traffic through an egress gateway.

Prerequisites

- You have installed the OpenShift Service Mesh Operator version 3.2 or later.
- You configured the`Istio` and`IstioCNI` resources with ambient profile.
- You have created a`Ztunnel` resource.

Procedure

Optional: Enable the {k8} Gateway API custom resource definitions (CRDs).

Note

As of Kubernetes 1.28 and OpenShift Container Platform 4.18 or earlier version of Red Hat OpenShift Service Mesh, the Kubernetes Gateway API CRDs are not available by default and you must install the CRDs before you can use them. OpenShift Container Platform 4.19 and later versions install the CRDs by default.

Create a namespace called`egress-gateway` by running the following command:

```shell-session
$ oc create namespace egress-gateway
```

Apply the ambient mode label to the namespace by running the following command:

```shell-session
$ oc label namespace egress-gateway istio.io/dataplane-mode=ambient
```

Create a YAML file named`egress-se.yaml` that defines the`ServiceEntry`.

```yaml
apiVersion: networking.istio.io/v1
kind: ServiceEntry
metadata:
  name: httpbin-ext
  namespace: egress-gateway
  labels:
    istio.io/use-waypoint: waypoint
spec:
  hosts:
  - httpbin.org
  ports:
  - number: 80
    name: http
    protocol: HTTP
  resolution: DNS
```

Apply the YAML file by running the following command:

```shell-session
$ oc apply -f egress-se.yaml
```

Create a YAML file named`waypoint.yaml` that creates a waypoint proxy in`egress-gateway` namespace similar to the following example:

```yaml
apiVersion: gateway.networking.k8s.io/v1
kind: Gateway
metadata:
  name: waypoint
  namespace: egress-gateway
  labels:
    istio.io/gateway-for: service
spec:
  gatewayClassName: istio-waypoint
  listeners:
  - name: mesh
    port: 15008
    protocol: HBONE
```

Apply the YAML file by running the following command:

```shell-session
$ oc apply -f waypoint.yaml
```

Note

As an alternate to creating the`waypoint.yaml` YAML file, you can also set up waypoint proxy by running the following command:

```shell-session
$ istioctl waypoint apply --enroll-namespace --name waypoint --namespace egress-gateway
```

When you use the`--enroll-namespace` option, all services in the`egress-gateway` namespace (including`ServiceEntries`), will route their traffic through the waypoint.

Verification

Verify the status of the gateway configuration by running the following command:

```shell-session
$ oc get gateways.gateway.networking.k8s.io waypoint -n egress-gateway
```

The`PROGRAMMED` column shows`True` when the configuration succeeds, similar to the following example:

```shell-session
NAME       CLASS            ADDRESS          PROGRAMMED   AGE
waypoint   istio-waypoint   172.30.227.148   True         9s
```

Create a`curl` pod in the`egress-gateway` namespace by running the following command:

```shell-session
$ oc run test-pod --image=curlimages/curl:latest -n egress-gateway --rm -it --restart=Never -- sh
```

By using the`curl` client, verify that you can access`httpbin.org` through the egress gateway by running the following command:

```shell-session
$ curl -v http://httpbin.org/get
```

The output shows a response from`httpbin.org` service that indicates egress traffic routes through the configured gateway. The ztunnel logs should show traffic routed through the waypoint. The terminal should display information similar to the following output:

```shell-session
2025-10-24T08:08:35.242159Z info access connection complete src.addr=[fd01:0:0:5::b0]:56288 src.workload="test-pod" src.namespace="egress-gateway" src.identity="spiffe://cluster.local/ns/egress-gateway/sa/default" dst.addr=[fd01:0:0:5::af]:15008 dst.hbone_addr=[2001:2::2]:80 dst.service="httpbin.org" dst.workload="waypoint-5b668759d5-vrnx8" dst.namespace="egress-gateway" dst.identity="spiffe://cluster.local/ns/egress-gateway/sa/waypoint" direction="outbound" bytes_sent=78 bytes_recv=540 duration="957ms"
```

## Chapter 4. Configuring quantum secure gatewaysCopy link

Use the Kubernetes Gateway API with a post-quantum cryptographic algorithm to establish a secure ingress gateway.

### 4.1. Generating TLS certificatesCopy link

Generate Transport Layer Security (TLS) certificates and create a secret to secure ingress traffic for a service mesh gateway.

Procedure

Create a directory for your certificates by running the following command:

```shell-session
$ mkdir certs
```

Generate the certificates for your gateway similar to the following examples:

Create the first certificate by running the following command:

```shell-session
$ openssl req -x509 -sha256 -nodes -days 365 -newkey rsa:2048 \
  -subj '/O=example Inc./CN=example.com' \
  -keyout certs/example.com.key \
  -out certs/example.com.crt
```

Create the second certificate by running the following command:

```shell-session
$ openssl req -out certs/httpbin.example.com.csr -newkey rsa:2048 -nodes \
  -keyout certs/httpbin.example.com.key \
  -subj "/CN=httpbin.example.com/O=httpbin organization"
```

Create the third certificate by running the following command:

```shell-session
$ openssl x509 -req -sha256 -days 365 \
  -CA certs/example.com.crt -CAkey certs/example.com.key \
  -set_serial 0 -in certs/httpbin.example.com.csr \
  -out certs/httpbin.example.com.crt
```

Create a secret containing the TLS certificate for your gateway by running the following command:

```shell-session
$ oc create -n istio-system secret tls httpbin-credential \
    --key=certs/httpbin.example.com.key \
    --cert=certs/httpbin.example.com.crt
```

### 4.2. Deploying quantum secure gatewaysCopy link

Configure and deploy a service mesh gateway by using the Kubernetes Gateway API to enable post-quantum cryptographic protection for ingress traffic.

Prerequisites

You have installed the following CLI tools locally:

- `oc`
- `podman`
- `curl`

Procedure

Deploy a gateway using the Kubernetes Gateway API by running the following command:

```shell-session
$ oc apply -f - <<EOF
apiVersion: gateway.networking.k8s.io/v1beta1
kind: Gateway
metadata:
 name: pqc-gateway
 namespace: istio-system
spec:
 gatewayClassName: istio
 listeners:
 - name: https
   port: 443
   protocol: HTTPS
   tls:
     mode: Terminate
     certificateRefs:
     - name: httpbin-credential
       namespace: istio-system
   allowedRoutes:
     namespaces:
       from: All
---
apiVersion: gateway.networking.k8s.io/v1beta1
kind: HTTPRoute
metadata:
 name: httpbin-route
 namespace: default
spec:
 parentRefs:
 - name: pqc-gateway
   namespace: istio-system
 hostnames:
 - "httpbin.example.com"
 rules:
 - matches:
   - path:
       type: PathPrefix
       value: /
   backendRefs:
   - name: httpbin
     port: 8000
EOF
```

Add the`istio-injection=enabled` label to the`default` namespace by running the following command:

```shell-session
$ oc label ns default istio-injection=enabled
```

Deploy the backend server by running the following command:

```shell-session
$ oc apply -n default -f \
https://raw.githubusercontent.com/openshift-service-mesh/istio/master/samples/httpbin/httpbin.yaml
```

### 4.3. Verifying quantum secure gatewaysCopy link

Verify the post-quantum gateway configuration by testing connections with both post-quantum cryptography (PQC)-enabled and standard clients to confirm that only quantum-safe handshakes are accepted.

Procedure

Retrieve the external address based on the type of load balancer:

Retrieve the external address for your hostname-based load balancer by running the following command:

```shell-session
$ INGRESS_ADDR=$(oc get svc pqc-gateway-istio \
-n istio-system \
-o jsonpath='{.status.loadBalancer.ingress[0].hostname}')
```

Retrieve the external address for your IP-based load balancer by running the following command:

```shell-session
$ INGRESS_ADDR=$(oc get svc pqc-gateway-istio \
-n istio-system \
-o jsonpath='{.status.loadBalancer.ingress[0].ip}')
```

Test the connection with the PQC-enabled client by running the following command:

```shell-session
$ podman run --rm -it \
  -v ./certs/example.com.crt:/etc/certs/example.com.crt \
  docker.io/openquantumsafe/curl \
  curl -vk "https://$INGRESS_ADDR:443/headers" \
  -H "Host: httpbin.example.com" \
  --curves X25519MLKEM768 \
  --cacert /etc/certs/example.com.crt
```

The request should succeed, and you should receive the`HTTP` status as`200`.

Verify that the connection fails with a standard`curl` client by running the following command:

```shell-session
$ curl -vk "https://$INGRESS_ADDR:443/headers" \
  -H "Host: httpbin.example.com" \
  --cacert ./certs/example.com.crt
```

You will get errors similar to the following example:

```shell-session
* TLSv1.3 (OUT), TLS handshake, Client hello (1):
* TLSv1.3 (IN), TLS alert, handshake failure (552):
* TLS connect error: error:0A000410:SSL routines::ssl/tls alert handshake failure
* closing connection #0
curl: (35) TLS connect error: error:0A000410:SSL routines::ssl/tls alert handshake failure
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

Updating | Red Hat OpenShift Service Mesh | 3.3 | Red Hat Documentation

 

 

 

 

 

 

 

 

# Updating

---

Red Hat OpenShift Service Mesh 3.3

## Updating OpenShift Service Mesh

Red Hat OpenShift Documentation Team

Legal Notice

Abstract

This documentation provides information about how to update OpenShift Service Mesh.

---

## Chapter 1. About updating OpenShift Service MeshCopy link

Red Hat OpenShift Service Mesh upgrade lifecycle involves aligning product and Istio versions through specific Operator channels and orchestrated workflows for the control plane and data plane.

### 1.1. Understanding versioningCopy link

Red Hat OpenShift Service Mesh follows Semantic Versioning for all product releases. Semantic Versioning uses a three-part version number in the format`X.Y.Z` to communicate the nature of changes in each release.

X (Major version) Indicates significant updates that might include breaking changes, such as architectural shifts, API changes, or schema modifications. Y (Minor version) Introduces new features and enhancements while maintaining backward compatibility. Z (Patch version or z-stream release) Delivers critical bug fixes and security updates, such as Common Vulnerabilities and Exposures (CVEs) resolutions. Patch versions do not include new features.

#### 1.1.1. Understanding Service Mesh and Istio versionsCopy link

The most current OpenShift Service Mesh Operator version is 3.3.1. This version supports the features listed in the "Service Mesh 3.3.1 feature support tables". The OpenShift Service Mesh Operator includes additional Istio releases for upgrades but supports only the latest Istio version available for each Operator version. See the "Service Mesh version support tables" to identify the supported Istio version for each Operator release.

### 1.2. Understanding Operator updates and channelsCopy link

The Operator Lifecycle Manager (OLM) manages Operators and their associated services by using channels to organize and distribute updates. Channels are a way to group related updates.

To ensure that your OpenShift Service Mesh stays current with the latest security patches, bug fixes, and software updates, keep the OpenShift Service Mesh Operator up to date. The upgrade process depends on the configured channel and approval strategy.

OLM provides the following channels for the OpenShift Service Mesh Operator:

- Legal Notice
- Stable channel: tracks the most recent version of the OpenShift Service Mesh 3 Operator and the latest supported version of Istio. This channel enables upgrades to new operator versions and corresponding Istio updates as soon as they are released. Use the stable channel to stay current with the latest features, bug fixes, and security updates.
- Versioned channel: restricts updates to patch-level releases within a specific minor version. For example,`stable-3.0` provides access to the latest`3.3.1` patch version. When a new patch release becomes available, you can upgrade the Operator to the newer patch version. To move to a newer minor release, you must manually switch to a different channel. You can use a versioned channel to maintain a consistent minor version while applying only patch updates.

Note

You can find the update strategy field in the Install Operator section and under the sub-section update approval. The default value for the update strategy is`Automatic`.

#### 1.2.1. About Operator update processCopy link

The OpenShift Service Mesh Operator will upgrade automatically to the latest available version based on the selected channel when the approval strategy field is set to`Automatic`(default). If the approval strategy field is set to`Manual`, Operator Lifecycle Manager (OLM) will generate an update request, which a cluster administrator must approve to update the Operator to the latest version.

The Operator update process does not automatically update the Istio control plane unless the`Istio` resource version is set to an alias (for example,`vX.Y-latest`) and the`updateStrategy` is set to`InPlace`. This triggers a control plane update when a new version is available in the Operator. By default, the Operator will not update the Istio control plane unless the`Istio` resource is updated with a new version.

#### 1.2.2. About Istio update processCopy link

After updating the OpenShift Service Mesh Operator, update the Istio control plane to the latest supported version. The`Istio` resource configuration determines how the control plane upgrade is performed, including which steps require manual action and which are handled automatically.

The`Istio` resource configuration includes the following fields that are relevant to the upgrade process:

`spec.version` specifies the version of Istio to install. Use the format`vX.Y.Z`, where`X.Y.Z` is the desired Istio release. For example, set the field to`v1.24.4` to install Istio`1.24.4`. Alternatively, set the value to an alias such as`vX.Y-latest` to automatically install the latest supported patch version for the specified minor release.`spec.updateStrategy` defines the strategy for updating the Istio control plane. The available update strategies are`InPlace` and`RevisionBased`.

Note

To enable automatic patch upgrades, set the approval strategy of the Operator to`Automatic`. When the Operator detects a new patch release and the`version` field uses the`vX.Y-latest` alias, the control plane is updated based on the configured`updateStrategy` type.

## Chapter 2. Updating OpenShift Service MeshCopy link

Compare the available strategies for updating the Istio control plane in Red Hat OpenShift Service Mesh. Identify when to use the`InPlace` or`RevisionBased` strategy and learn how to apply each during an upgrade.

### 2.1. About Istio control plane update strategiesCopy link

The update strategy affects how the update process is performed. The`spec.updateStrategy` field in the`Istio` resource configuration determines how the OpenShift Service Mesh Operator updates the Istio control plane. When the Operator detects a change in the`spec.version` field or identifies a new minor release with a configured`vX.Y-latest` alias, it initiates an upgrade procedure. For each mesh, you select one of two strategies:

- `InPlace`
- `RevisionBased`

`InPlace` is the default strategy for updating OpenShift Service Mesh. Both the update strategies apply to sidecar and ambient modes.

If you use ambient mode, you must update the Istio Container Network Interface (CNI) and`ZTunnel` components in addition to the standard control plane update procedures.

Important

The`InPlace` update strategy is recommended for ambient mode. Using`RevisionBased` updates with ambient mode has limitations and requires manual intervention.

### 2.2. About InPlace strategyCopy link

The`InPlace` update strategy runs only one revision of the control plane at a time. During an update, all the workloads immediately connect to the new control plane version. To maintain compatibility between the sidecars and the control plane, you can upgrade only one minor version at a time.

The`InPlace` strategy updates and restarts the existing Istio control plane in place. During this process, only one instance of the control plane exists, eliminating the need to move workloads to a new control plane instance. To complete the update, restart the application workloads and gateways to refresh the Envoy proxies.

While the`InPlace` strategy offers simplicity and efficiency, there’s a slight possibility of application traffic interruption if a workload pod updates, restarts, or scales while the control plane is restarting. You can mitigate this risk by running multiple replicas of the Istio control plane (istiod).

#### 2.2.1. Selecting InPlace strategyCopy link

To select the`InPlace` strategy, set the`spec.updateStrategy.type` value in the Istio resource to`InPlace`.

Example specification to select InPlace update strategy

```yaml
kind: Istio
spec:
  updateStrategy:
    type: InPlace
```

You can set this value while creating the resource or edit it later. If you edit the resource after creation, make the change before updating the Istio control plane.

Running the Istio resource in High Availability mode to minimize traffic disruptions requires additional property settings. For more information, see "About Istio High Availability".

#### 2.2.2. Installing with InPlace update strategyCopy link

You can install the Istio control plane, Istio CNI, and the Bookinfo demo application using the`Inplace` update strategy.

Note

You can skip this installation procedure if the cluster already includes an Istio deployment.

When using the`InPlace` strategy, the`IstioRevision` resource created by the OpenShift Service Mesh Operator always uses the same name as the`Istio` resource.

Procedure

Create the`istio-system` namespace by running the following command:

```shell-session
$ oc create ns istio-system
```

Attach the workloads to a control plane deployed using the`InPlace` strategy:

Label the namespace to automatically include all workloads by entering the following command:

```shell-session
$ oc label namespace <namespace_name> istio.io/rev=<revision_name>
```

Apply the revision label to individual workloads by modifying the pod template in the`Deployment` resource. For example:

```yaml
apiVersion: apps/v1
kind: Deployment
spec:
  template:
    metadata:
      labels:
        istio.io/rev: <revision_name>
```

If the revision name is`default`, attach the workloads to the revision by running the following command. The following example labels the namespace with`istio-injection: enabled` label.

```shell-session
$ oc label namespace <namespace_name> istio-injection=enabled
```

Deploy the Istio control plane using the`InPlace` update strategy. The following example configuration creates an`Istio` resource named`default` in the`istio-system` namespace:

```yaml
apiVersion: sailoperator.io/v1
kind: Istio
metadata:
  name: default
spec:
  namespace: istio-system
  version: v1.24.3
  updateStrategy:
    type: InPlace
```

Install the Istio CNI plugin with the desired version. The following example configuration creates an`IstioCNI` resource named`default` in the`istio-cni` namespace:

```yaml
apiVersion: sailoperator.io/v1
kind: IstioCNI
metadata:
  name: default
spec:
  version: v1.24.3
  namespace: istio-cni
```

Configure application workloads to run in the cluster. The following example deploys the`bookinfo` application in the`bookinfo` namespace.

Create the`bookinfo` namespace by running the following command:

```shell-session
$ oc create ns bookinfo
```

Label the`bookinfo` namespace to enable sidecar injection by running the following command:

```shell-session
$ oc label namespace bookinfo istio-injection=enabled
```

Install the`bookinfo` pods in the`bookinfo` namespace by running the following command:

```shell-session
$ oc apply -f https://raw.githubusercontent.com/openshift-service-mesh/istio/release-1.24/samples/bookinfo/platform/kube/bookinfo.yaml -n bookinfo
```

Review the`Istio` resource by running the following command:

```shell-session
$ oc get istio -n istio-system
```

Example output

```shell-session
NAME      REVISIONS   READY   IN USE   ACTIVE REVISION   STATUS    VERSION   AGE
default   1           1       1        default           Healthy   v1.24.3   115s
```

The`IN USE` field shows`1` because both the namespace label and the injected proxies reference the`IstioRevision` resource.

#### 2.2.3. Updating Istio control plane with InPlace strategyCopy link

When updating Istio using the`InPlace` strategy, you can increment the version by only one minor release at a time. To update by more than one minor version, you must increment the version and restart the workloads after each update. Restarting workloads ensures compatibility between the sidecar and control plane versions. The update process is complete after restarting all workloads.

Prerequisites

- You are logged in to OpenShift Container Platform as a user with the`cluster-admin` role.
- You have installed the Red Hat OpenShift Service Mesh Operator, and deployed Istio.
- You have installed`istioctl` on your local machine.
- You have configured the Istio control plane to use the`InPlace` update strategy. In this example, the`Istio` resource named`default` is deployed in the`istio-system` namespace.
- You have installed the Istio CNI plugin with the desired version. In this example, the`IstioCNI` resource named`default` is deployed in the`istio-cni` namespace.
- You have labeled the`bookinfo` namespace to enable sidecar injection.
- You have application workloads running in the cluster. In this example, the`bookinfo` application is deployed in the`bookinfo` namespace.

Procedure

Change the version in the`Istio` resource. For example, to update to Istio`1.24.4`, set the`spec.version` field to`v1.24.4` by running the following command:

```shell-session
$ oc patch istio default --type='merge' -p '{"spec":{"version":"v1.24.4"}}'
```

Version update in Istio CR

```yaml
kind: Istio
spec:
  version: v1.24.4
  updateStrategy:
    type: InPlace
```

The Service Mesh Operator deploys a new version of the control plane that replaces the old version of the control plane. The sidecars automatically reconnect to the new control plane.

Confirm that the new version of the control plane is ready by running the following command:

```shell-session
$ oc get istio
```

Example output

```shell-session
NAME      REVISIONS   READY   IN USE   ACTIVE REVISION   STATUS    VERSION   AGE
default   1           1       1        default           Healthy   v1.24.4   4m50s
```

Restart the application workloads so that the new version of the sidecar gets injected by running the following command:

```shell-session
$ oc rollout restart deployment -n bookinfo
```

Verification

Verify that the new version of the sidecar is running by entering the following command:

```shell-session
$ istioctl proxy-status
```

Example output

```shell-session
NAME                                                    CLUSTER        CDS                LDS                EDS                RDS                ECDS        ISTIOD                                     VERSION
details-v1-7d775cb4f6-5t9zm.bookinfo                    Kubernetes     SYNCED (2m25s)     SYNCED (2m25s)     SYNCED (2m17s)     SYNCED (2m25s)     IGNORED     istiod-default-v1-24-4-c98fd9675-r7bfw     1.24.4
productpage-v1-7c4b6b857-mxrw6.bookinfo                 Kubernetes     SYNCED (2m35s)     SYNCED (2m35s)     SYNCED (2m17s)     SYNCED (2m35s)     IGNORED     istiod-default-v1-24-4-c98fd9675-r7bfw     1.24.4
ratings-v1-5b896f8544-r552l.bookinfo                    Kubernetes     SYNCED (2m21s)     SYNCED (2m21s)     SYNCED (2m17s)     SYNCED (2m21s)     IGNORED     istiod-default-v1-24-4-c98fd9675-r7bfw     1.24.4
reviews-v1-746f96c9d4-9pw8k.bookinfo                    Kubernetes     SYNCED (2m17s)     SYNCED (2m17s)     SYNCED (2m17s)     SYNCED (2m17s)     IGNORED     istiod-default-v1-24-4-c98fd9675-r7bfw     1.24.4
reviews-v2-97bdf5876-4mzx5.bookinfo                     Kubernetes     SYNCED (2m35s)     SYNCED (2m35s)     SYNCED (2m17s)     SYNCED (2m35s)     IGNORED     istiod-default-v1-24-4-c98fd9675-r7bfw     1.24.4
reviews-v3-77d9db6844-djgjk.bookinfo                    Kubernetes     SYNCED (2m19s)     SYNCED (2m19s)     SYNCED (2m17s)     SYNCED (2m19s)     IGNORED     istiod-default-v1-24-4-c98fd9675-r7bfw     1.24.4
```

The column`VERSION` should match with the new control plane version.

### 2.3. About RevisionBased strategyCopy link

The`RevisionBased` strategy runs two revisions of the control plane during an upgrade. This approach supports gradual workload migration from the old control plane to the new one, enabling canary upgrades. It also supports upgrades across more than one minor version.

The`RevisionBased` strategy creates a new Istio control plane instance for each change to the`spec.version` field. The existing control plane remains active until all workloads transition to the new instance. You can move the workloads to the new control plane by updating the`istio.io/rev` labels or using the`IstioRevisionTag` resource, followed by a restart.

Although the`RevisionBased` strategy involves additional steps and requires multiple control plane instances to run concurrently during the upgrade, it allows for gradual migration of workloads. This approach enables validation of the updated control plane with a subset of workloads before migrating the rest, making it useful for large meshes with mission-critical workloads.

#### 2.3.1. Selecting RevisionBased strategyCopy link

To deploy Istio with the`RevisionBased` strategy, create the`Istio` resource with the following`spec.updateStrategy` value:

Example specification to select`RevisionBased` strategy

```yaml
kind: Istio
spec:
  version: v1.24.4
  updateStrategy:
    type: RevisionBased
```

After you select the strategy for the`Istio` resource, the Operator creates a new`IstioRevision` resource with the name` - `.

#### 2.3.2. Installing Istio with RevisionBased strategyCopy link

You can install the Istio control plane, Istio CNI, and the Bookinfo demo application using the`RevisionBased` update strategy.

Note

You can use the following section to understand the update process. You can skip this installation if the cluster already includes an Istio deployment.

Procedure

Create the`istio-system` namespace by running the following command:

```shell-session
$ oc create ns istio-system
```

Deploy the Istio control plane using the`RevisionBased` update strategy. The following example configuration creates an`Istio` resource named`default` in the`istio-system` namespace:

Example configuration

```yaml
apiVersion: sailoperator.io/v1
kind: Istio
metadata:
  name: default
spec:
  namespace: istio-system
  version: v1.24.3
  updateStrategy:
    type: RevisionBased
```

Install the Istio CNI plugin with the desired version. The following example configuration creates an`IstioCNI` resource named`default` in the`istio-cni` namespace:

Example configuration

```yaml
apiVersion: sailoperator.io/v1
kind: IstioCNI
metadata:
  name: default
spec:
  version: v1.24.3
  namespace: istio-cni
```

Get the`IstioRevision` name by running the following command:

```shell-session
$ oc get istiorevision -n istio-system
```

Example output

```shell-session
NAME              TYPE    READY   STATUS    IN USE   VERSION   AGE
default-v1-24-3   Local   True    Healthy   False    v1.24.3   3m4s
```

The`IstioRevision` name is in the format` - `.

Configure application workloads to run in the cluster. The following example deploys the`bookinfo` application in the`bookinfo` namespace:

Create the`bookinfo` namespace by running the following command:

```shell-session
$ oc create ns bookinfo
```

Label the`bookinfo` namespace to enable sidecar injection by running the following command:

```shell-session
$ oc label namespace bookinfo istio.io/rev=<revision_name>
```

Install the`bookinfo` pods in the`bookinfo` namespace by running the following command:

```shell-session
$ oc apply -f https://raw.githubusercontent.com/openshift-service-mesh/istio/release-1.24/samples/bookinfo/platform/kube/bookinfo.yaml -n bookinfo
```

Review the`Istio` resource by running the following command:

```shell-session
$ oc get istio -n istio-system
```

Example output

```shell-session
NAME      REVISIONS   READY   IN USE   ACTIVE REVISION   STATUS    VERSION   AGE
default   1           1       1        default-v1-24-3   Healthy   v1.24.3   5m13s
```

The`IN USE` field shows`1` after you deploy the application.

Confirm that the proxy version matches the control plane version by running the following command:

```shell-session
$ istioctl proxy-status
```

The`VERSION` column should match the control plane version.

Example output

```shell-session
NAME              TYPE    READY   STATUS    IN USE   VERSION   AGE
default-v1-24-3   Local   True    Healthy   True     v1.24.3   5m31s
```

#### 2.3.3. Updating Istio control plane with RevisionBased strategyCopy link

When updating Istio using the`RevisionBased` strategy, you can upgrade by more than one minor version at a time. The Red Hat OpenShift Service Mesh Operator creates a new`IstioRevision` resource for each change to the`.spec.version` field and deploys a corresponding control plane instance. To migrate workloads to the new control plane, set the`istio.io/rev` label on the namespace to match the name of the`IstioRevision` resource, and then restart the workloads.

Prerequisites

- You are logged in to OpenShift Container Platform as a user with the`cluster-admin` role.
- You have installed the Red Hat OpenShift Service Mesh Operator 3, and deployed Istio with the`RevisionBased` strategy. In this example, the`Istio` resource named`default` is deployed in the`istio-system` namespace.
- You have installed the Istio CNI plugin with the desired version. In this example, the`IstioCNI` resource named`default` is deployed in the`istio-cni` namespace.
- You have labeled the`bookinfo` namespace to enable sidecar injection.
- You have application workloads running in the cluster. In this example, the`bookinfo` application is deployed in the`bookinfo` namespace.
- You have installed`istioctl` on your local machine.

Procedure

Change the version in the`Istio` resource. For example, to update to Istio`1.24.4`, set the`spec.version` field to`v1.24.4` by running the following command:

```shell-session
$ oc patch istio default --type='merge' -p '{"spec":{"version":"v1.24.4"}}'
```

Version Update in Istio CR

```yaml
kind: Istio
spec:
  version: v1.24.4
  updateStrategy:
    type: RevisionBased
```

The Service Mesh Operator deploys a new version of the control plane alongside the old version of the control plane. The sidecars remain connected to the old control plane.

Confirm that both`Istio` and`IstioRevision` resources are ready with the new revision.

Confirm that`Istio` resource is ready by running the following command:

```shell-session
$ oc get istio
```

Example output

```shell-session
NAME      REVISIONS   READY   IN USE   ACTIVE REVISION   STATUS    VERSION   AGE
default   2           2       1        default-v1-2-4   Healthy   v1.24.4   9m23s
```

Confirm that`IstioRevision` resource is ready by running the following command:

```shell-session
$ oc get istiorevision
```

Example output

```shell-session
NAME              TYPE    READY   STATUS    IN USE   VERSION   AGE
default-v1-24-3   Local   True    Healthy   True     v1.24.3   10m
default-v1-24-4   Local   True    Healthy   False    v1.24.4   66s
```

Confirm that there are two control plane pods running, one for each revision by running the following command:

```shell-session
$ oc get pods -n istio-system
```

Example output

```shell-session
NAME                                      READY   STATUS    RESTARTS   AGE
istiod-default-v1-24-3-c98fd9675-r7bfw    1/1     Running   0          10m
istiod-default-v1-24-4-7495cdc7bf-v8t4g   1/1     Running   0          113s
```

Confirm that the workload sidecars are still connected to the previous control plane by running the following command:

```shell-session
$ istioctl proxy-status
```

Example output

```shell-session
NAME                                                    CLUSTER        CDS                LDS                EDS                RDS                ECDS        ISTIOD                                     VERSION
details-v1-7d775cb4f6-5t9zm.bookinfo                    Kubernetes     SYNCED (2m25s)     SYNCED (2m25s)     SYNCED (2m17s)     SYNCED (2m25s)     IGNORED     istiod-default-v1-24-3-c98fd9675-r7bfw     1.24.3
productpage-v1-7c4b6b857-mxrw6.bookinfo                 Kubernetes     SYNCED (2m35s)     SYNCED (2m35s)     SYNCED (2m17s)     SYNCED (2m35s)     IGNORED     istiod-default-v1-24-3-c98fd9675-r7bfw     1.24.3
ratings-v1-5b896f8544-r552l.bookinfo                    Kubernetes     SYNCED (2m21s)     SYNCED (2m21s)     SYNCED (2m17s)     SYNCED (2m21s)     IGNORED     istiod-default-v1-24-3-c98fd9675-r7bfw     1.24.3
reviews-v1-746f96c9d4-9pw8k.bookinfo                    Kubernetes     SYNCED (2m17s)     SYNCED (2m17s)     SYNCED (2m17s)     SYNCED (2m17s)     IGNORED     istiod-default-v1-24-3-c98fd9675-r7bfw     1.24.3
reviews-v2-97bdf5876-4mzx5.bookinfo                     Kubernetes     SYNCED (2m35s)     SYNCED (2m35s)     SYNCED (2m17s)     SYNCED (2m35s)     IGNORED     istiod-default-v1-24-3-c98fd9675-r7bfw     1.24.3
reviews-v3-77d9db6844-djgjk.bookinfo                    Kubernetes     SYNCED (2m19s)     SYNCED (2m19s)     SYNCED (2m17s)     SYNCED (2m19s)     IGNORED     istiod-default-v1-24-3-c98fd9675-r7bfw     1.24.3
```

The`VERSION` column should match the old control plane version.

Move the workloads to the new control plane by updating the`istio.io/rev` label on the application namespace or pods to the revision name. For example, update the label for the entire namespace by running the following command:

```shell-session
$ oc label namespace bookinfo istio.io/rev=<new_revision_name> --overwrite
```

Restart the application workloads so that the new version of the sidecar gets injected by running the following command:

```shell-session
$ oc rollout restart deployment -n bookinfo
```

Verification

Verify that the new version of the sidecar is running by entering the following command:

```shell-session
$ istioctl proxy-status
```

The`VERSION` column should match the new control plane version.

Verify that the old control plane,`Istio`, and`IstioRevision` resources has been deleted.

Verify that the old control plane has beend deleted by running the following command:

```shell-session
$ oc get pods -n istio-system
```

Verify that the`Istio` resource has been deleted by running the following command:

```shell-session
$ oc get istio
```

Verify that the`IstioRevision` resource has been deleted by running the following command:

```shell-session
$ oc get istiorevision
```

The OpenShift Service Mesh Operator deletes the old`IstioRevision` resource and the associated control plane after the grace period defined in the`spec.updateStrategy.inactiveRevisionDeletionGracePeriodSeconds` field expires. The default grace period is 30 seconds.

You can increase the grace period to allow sufficient time to test the new control plane before removing the previous revision. Set a higher value during canary upgrades to ensure workload stability before fully transitioning.

#### 2.3.4. Installing Istio with RevisionBased strategy and IstioRevisionTagCopy link

You can install the Istio control plane,`IstioRevisionTag` resource, Istio CNI, and the Bookinfo demo application using the`RevisionBased` update strategy.

Note

You can use the following section to understand the update process. You can skip this installation if the cluster already includes an Istio deployment.

Procedure

Create the`istio-system` namespace by running the following command:

```shell-session
$ oc create ns istio-system
```

Deploy the Istio control plane using the`RevisionBased` update strategy. The following example configuration creates an`Istio` resource named`default` in the`istio-system` namespace:

Example configuration

```yaml
apiVersion: sailoperator.io/v1
kind: Istio
metadata:
  name: default
spec:
  namespace: istio-system
  updateStrategy:
    type: RevisionBased
  version: v1.24.3
```

Create an`IstioRevisionTag` resource. The following example configuration creates an`IstioRevisionTag` resource named`default`:

Example configuration

```yaml
apiVersion: sailoperator.io/v1
kind: IstioRevisionTag
metadata:
  name: default
spec:
  targetRef:
    kind: Istio
    name: default
```

Verify that the`targetRef` field points to the desired`Istio` resource. In the example above, the`IstioRevisionTag` references the`Istio` resource named`default`.

Create the`istio-cni` namespace by running the following command:

```shell-session
$ oc create ns istion-cni
```

Install the Istio CNI plugin with the desired version. The following example configuration creates an`IstioCNI` resource named`default` in the`istio-cni` namespace:

Example configuration

```yaml
apiVersion: sailoperator.io/v1
kind: IstioCNI
metadata:
  name: default
spec:
  version: v1.24.3
  namespace: istio-cni
```

Configure application workloads to run in the cluster. The following example deploys the`bookinfo` application in the`bookinfo` namespace.

Create the`bookinfo` namespace by running the following command:

```shell-session
$ oc create ns bookinfo
```

Label the`bookinfo` namespace to enable sidecar injection by running the following command:

```shell-session
$ oc label namespace bookinfo istio-injection=enabled
```

Install the`bookinfo` pods in the`bookinfo` namespace by running the following command:

```shell-session
$ oc apply -f https://raw.githubusercontent.com/openshift-service-mesh/istio/release-1.24/samples/bookinfo/platform/kube/bookinfo.yaml -n bookinfo
```

Review the`IstioRevisionTag` resource by running the following command:

```shell-session
$ oc get istiorevisiontag
```

Example output

```shell-session
NAME      STATUS    IN USE   REVISION          AGE
default   Healthy   True     default-v1-24-3   2m46s
```

The`IN USE` field shows`True` because both active workloads and the`bookinfo` namespace now reference the tag.

Confirm that the proxy version matches the control plane version by running the following command:

```shell-session
$ istioctl proxy-status
```

The`VERSION` column should match the control plane version.

Example output

```shell-session
NAME              TYPE    READY   STATUS    IN USE   VERSION   AGE
default-v1-24-3   Local   True    Healthy   True     v1.24.3   5m31s
```

#### 2.3.5. Updating Istio control plane with RevisionBased strategy and IstioRevisionTagCopy link

When updating Istio using the`RevisionBased` strategy, you can create an`IstioRevisionTag` resource to tag a specific`IstioRevision` resource. You can use the`IstioRevisionTag` resource to attach workloads to a specific`IstioRevision` resource without modifying the`istio.io/rev` label on namespaces or pods.

Prerequisites

- You are logged in to OpenShift Container Platform as a user with the`cluster-admin` role.
- You have installed the Red Hat OpenShift Service Mesh Operator 3, and deployed Istio with the`RevisionBased` strategy. In this example, the`Istio` resource named`default` is deployed in the`istio-system` namespace.
- You have created an`IstioRevisionTag` resource and the`targetRef` field is referencing the desired`Istio` resource.
- You have installed the Istio CNI plugin with the desired version.
- You have labeled the`bookinfo` namespace to enable sidecar injection.
- You have application workloads running in the cluster. In this example, the`bookinfo` application is deployed in the`bookinfo` namespace.
- You have installed`istioctl` on your local machine.
- You have confirmed that the`InUse` field in the`IstioRevisionTag` resource is set to`true`.

Procedure

Change the version in the`Istio` resource. For example, to update to Istio`1.24.4`, set the`spec.version` field to`v1.24.4` by running the following command:

```shell-session
$ oc patch istio default --type='merge' -p '{"spec":{"version":"v1.24.4"}}'
```

Version Update in Istio CR

```yaml
kind: Istio
spec:
  version: v1.24.4
  updateStrategy:
    type: RevisionBased
```

The Service Mesh Operator deploys a new version of the control plane alongside the old version of the control plane. The sidecars remain connected to the old control plane.

Confirm that the`Istio`,`IstioRevision` and`IstioRevisionTag` resources are ready with the new revision.

Confirm that`Istio` resource is ready by running the following command:

```shell-session
$ oc get istio
```

Example output

```shell-session
NAME      REVISIONS   READY   IN USE   ACTIVE REVISION   STATUS    VERSION   AGE
default   2           2       1        default-v1-24-3   Healthy   v1.24.3   9m23s
```

Confirm that`IstioRevision` resource is ready by running the following command:

```shell-session
$ oc get istiorevision
```

Example output

```shell-session
NAME              TYPE    READY   STATUS    IN USE   VERSION   AGE
default-v1-24-3   Local   True    Healthy   True     v1.24.3   10m
default-v1-24-4   Local   True    Healthy   True     v1.24.4   66s
```

Confirm that`IstioRevisionTag` resource is ready by running the following command:

```shell-session
$ oc get istiorevisiontag
```

Example output

```shell-session
NAME      STATUS    IN USE   REVISION          AGE
default   Healthy   True     default-v1-24-4   10m44s
```

Confirm that there are two control plane pods ready for each revision by running the following command:

```shell-session
$ oc get pods -n istio-system
```

Example output

```shell-session
NAME                                      READY   STATUS    RESTARTS   AGE
istiod-default-v1-24-3-c98fd9675-r7bfw    1/1     Running   0          10m
istiod-default-v1-24-4-7495cdc7bf-v8t4g   1/1     Running   0          113s
```

Confirm that the proxy sidecar version is the same by running the following command:

```shell-session
$ istioctl proxy-status
```

Example output

```shell-session
NAME                                                    CLUSTER        CDS                LDS                EDS                RDS                ECDS        ISTIOD                                     VERSION
details-v1-7d775cb4f6-5t9zm.bookinfo                    Kubernetes     SYNCED (2m25s)     SYNCED (2m25s)     SYNCED (2m17s)     SYNCED (2m25s)     IGNORED     istiod-default-v1-24-3-c98fd9675-r7bfw     1.24.3
productpage-v1-7c4b6b857-mxrw6.bookinfo                 Kubernetes     SYNCED (2m35s)     SYNCED (2m35s)     SYNCED (2m17s)     SYNCED (2m35s)     IGNORED     istiod-default-v1-24-3-c98fd9675-r7bfw     1.24.3
ratings-v1-5b896f8544-r552l.bookinfo                    Kubernetes     SYNCED (2m21s)     SYNCED (2m21s)     SYNCED (2m17s)     SYNCED (2m21s)     IGNORED     istiod-default-v1-24-3-c98fd9675-r7bfw     1.24.3
reviews-v1-746f96c9d4-9pw8k.bookinfo                    Kubernetes     SYNCED (2m17s)     SYNCED (2m17s)     SYNCED (2m17s)     SYNCED (2m17s)     IGNORED     istiod-default-v1-24-3-c98fd9675-r7bfw     1.24.3
reviews-v2-97bdf5876-4mzx5.bookinfo                     Kubernetes     SYNCED (2m35s)     SYNCED (2m35s)     SYNCED (2m17s)     SYNCED (2m35s)     IGNORED     istiod-default-v1-24-3-c98fd9675-r7bfw     1.24.3
reviews-v3-77d9db6844-djgjk.bookinfo                    Kubernetes     SYNCED (2m19s)     SYNCED (2m19s)     SYNCED (2m17s)     SYNCED (2m19s)     IGNORED     istiod-default-v1-24-3-c98fd9675-r7bfw     1.24.3
```

The`VERSION` column should match the old control plane version.

Restart the application workloads so that the new version of the sidecar gets injected by running the following command:

```shell-session
$ oc rollout restart deployment -n bookinfo
```

Verification

Verify that the new version of the sidecar is running by entering the following command:

```shell-session
$ istioctl proxy-status
```

The`VERSION` column should match the new control plane version.

Verify that the old control plane,`Istio`, and`IstioRevision` resources has been deleted.

Verify that the old control plane has been deleted by running the following command:

```shell-session
$ oc get pods -n istio-system
```

Verify that the`Istio` resource has been deleted by running the following command:

```shell-session
$ oc get istio
```

Verify that the`IstioRevision` resource has beend deleted by running the following command:

```shell-session
$ oc get istiorevision
```

The OpenShift Service Mesh Operator deletes the old`IstioRevision` resource and the associated control plane after the grace period defined in the`spec.updateStrategy.inactiveRevisionDeletionGracePeriodSeconds` field expires. The default grace period is 30 seconds.

You can increase the grace period to allow sufficient time to test the new control plane before removing the previous revision. Set a higher value during canary upgrades to ensure workload stability before fully transitioning.

## Chapter 3. Updating OpenShift Service Mesh in ambient modeCopy link

Update Red Hat OpenShift Service Mesh in ambient mode by transitioning the control plane and waypoint proxies to new revisions while maintaining Layer 7 (L7) functionality and resource compatibility.

### 3.1. About the update strategies in ambient modeCopy link

In ambient mode, components update directly through`InPlace` updates. Unlike sidecar mode, ambient mode allows you to move application pods to an upgraded ztunnel proxy without restarting or rescheduling the pods.

Update sequence

To update in ambient mode, use the following sequence:

1. Istio control plane: Update the patch version in the Istio resource.
2. Istio CNI: Update to the same patch version as the control plane.
3. ZTunnel: Update to the same patch version as the control plane.

### 3.2. Updating waypoint proxies with InPlace strategy in ambient modeCopy link

During an`InPlace` update in ambient mode, waypoint proxies are going to be updated to the latest control plane version without restarting application workloads because they are deployed and managed as separate Gateway API resources that scale and upgrade independently.

Prerequisites

- You have updated the Istio control plane with`InPlace` update strategy.

Procedure

Confirm that the waypoint proxy was updated proxy version by running the following command:

```shell-session
$ istioctl proxy-status | grep waypoint
```

You should see an output similar to the following example:

```shell-session
waypoint-5d9c8b7f9-abc12.bookinfo     SYNCED     SYNCED     SYNCED     SYNCED     istiod-6cf8d4f9cb-wm7x6.istio-system     1.28.5
```

You can run the command to query the Istio control plane and verify that the waypoint proxy connects and synchronizes. The output lists the waypoint proxy name and namespace, the synchronization status for each configuration type, the connected`istiod` pod, and the Istio version of the running proxy. Columns showing`SYNCED` confirm that the waypoint proxy is successfully receiving configuration from the control plane.

### 3.3. Updating waypoint proxies with RevisionBased strategy in ambient modeCopy link

In ambient mode, you can update waypoint proxies by using the`RevisionBased` update strategy. During the migration period, the proxies remain compatible with many control plane versions and automatically connect to the active control plane revision.

Note

Keep waypoint proxies within one minor version of the control plane (same version or`n–1`). This recommendation aligns with the support policy of Istio, which states that data plane components must not run ahead of the control plane version. Apply the same versioning guidance to Istio Container Network Interface (CNI) and`Ztunnel` components. For more details, see the "Istio Supported Releases" documentation.

Prerequisites

- You have updated the Istio control plane with`RevisionBased` update strategy.

Procedure

After the new Istio control plane revision is ready, verify waypoint proxy pods are running by entering the following command:

```shell-session
$ oc get pods -n bookinfo -l gateway.networking.k8s.io/gateway-name=waypoint
```

You should see an output similar to the following example:

```shell-session
NAME                       READY   STATUS    RESTARTS   AGE
waypoint-5d9c8b7f9-abc12   1/1     Running   0          5m
```

Confirm that the waypoint proxy is updated to the latest version by running the following command:

```shell-session
$ istioctl proxy-status | grep waypoint
```

You should see an output similar to the following example:

```shell-session
waypoint-5d9c8b7f9-abc12.bookinfo     SYNCED     SYNCED     SYNCED     SYNCED     istiod-1-27-3-7b9f8c5d6-xyz78.istio-system     1.28.5
```

You can run the command to query the Istio control plane and verify that the waypoint proxy is connected to the new revision. The output lists the revision-specific`istiod` pod (for example,`istiod-1-27-3`) and shows that the waypoint proxy is running the updated version, 1.28.5. The revision-specific name in the`ISTIOD` column confirms that the waypoint proxy has successfully migrated to the new control plane revision.

### 3.4. Verifying Layer 7 (L7) features with traffic routingCopy link

After updating the waypoint proxies, verify that Layer 7 (L7) features function as expected. If you use traffic routing rules such as`HTTPRoute`, confirm that they continue to enforce the intended behavior.

Prerequisites

- You have updated the waypoint proxies.
- You have deployed the`bookinfo` application.
- You have created an`HTTPRoute` resource.

Procedure

Optional: Create the`HTTPRoute` resource if it does not already exist by running the following command:

```shell-session
$ oc apply -f - <<EOF
apiVersion: gateway.networking.k8s.io/v1
kind: HTTPRoute
metadata:
  name: reviews
  namespace: bookinfo
spec:
  parentRefs:
  - group: ""
    kind: Service
    name: reviews
    port: 9080
  rules:
  - backendRefs:
    - name: reviews-v1
      port: 9080
      weight: 90
    - name: reviews-v2
      port: 9080
      weight: 10
EOF
```

Verify that the`HTTPRoute` rules distribute traffic correctly by running the following command:

```shell-session
for i in {1..10}; do
  kubectl exec "$(
    kubectl get pod \
      -l app=productpage \
      -n bookinfo \
      -o jsonpath='{.items[0].metadata.name}'
  )" \
  -c istio-proxy \
  -n bookinfo -- \
  curl -s http://reviews:9080/reviews/0 | grep -o "reviews-v[0-9]"
done
```

The output should reflect the traffic distribution defined in your`HTTPRoute`. For example, with a`90/10` weight split between`reviews-v1` and`reviews-v2`, you should observe about nine requests routed to`reviews-v1` and one request routed to`reviews-v2`. The exact ratio can vary slightly due to load-balancing behavior, but should closely match the configured weights over multiple test runs.

### 3.5. Verifying Layer 7 (L7) features with authorization policiesCopy link

After updating the waypoint proxies, verify that the Layer 7 (L7) authorization policies are enforced correctly. In this example, the`AuthorizationPolicy` resource named`productpage-waypoint` allows only requests from the`default/sa/curl` service account to send`GET` requests to the`productpage` service.

Prerequisites

- You have updated the waypoint proxies.
- You have created an application pod using the described service account in the`AuthorizationPolicy` resource.
- You have created an`AuthorizationPolicy` resource.

Procedure

Optional: Create the`AuthorizationPolicy` resource if it does not already exist by running the following command:

```shell-session
$ oc apply -f - <<EOF
apiVersion: security.istio.io/v1
kind: AuthorizationPolicy
metadata:
  name: productpage-waypoint
  namespace: bookinfo
spec:
  targetRefs:
  - kind: Service
    group: ""
    name: productpage
  action: ALLOW
  rules:
  - from:
    - source:
        principals:
        - cluster.local/ns/default/sa/curl
    to:
    - operation:
        methods: ["GET"]
EOF
```

Verify that services not included in the allow list, such as the ratings service, are denied access by running the following command:

```shell-session
$ oc exec "$(
  kubectl get pod \
    -l app=ratings \
    -n bookinfo \
    -o jsonpath='{.items[0].metadata.name}'
)" \
-c ratings \
-n bookinfo -- \
curl -sS productpage:9080/productpage
```

The request will be denied because the`ratings` service is not included in the authorization policy’s`allow` list. Only the`curl` pod using the`default/curl` service account can access`productpage` service.

Verify that the`curl` service can access the`productpage` service with`GET` requests by running the following command:

```shell-session
oc exec "$(
  kubectl get pod \
    -l app=curl \
    -n default \
    -o jsonpath='{.items[0].metadata.name}'
)" \
-c curl \
-n default -- \
curl -sS http://productpage.bookinfo:9080/productpage | grep -o "<title>.*</title>"
```

The request will succeed because the`curl` service meets the authorization policy rules. It uses the`cluster.local/ns/default/sa/curl` principal and performs a`GET` operation, both allowed by the policy. The successful response containing the page title confirms that the waypoint proxy correctly enforces L7 authorization rules and allows valid traffic.

### 3.6. Updating cross-namespace waypointCopy link

If you are using cross-namespace waypoints, verify that the`istio.io/use-waypoint-namespace` and`istio.io/use-waypoint` labels are correctly applied to the relevant namespaces before updating.

Verify the namespace with any of the waypoint labels by running the following command:

```shell-session
$ oc get ns bookinfo --show-labels | grep waypoint
```

If there is no namespace with the label or if the label is wrong, re-apply the labels:

Apply the`istio.io/use-waypoint-namespace` by running the following command:

```shell-session
$ oc label ns bookinfo istio.io/use-waypoint-namespace=foo --overwrite
```

Apply the`istio.io/use-waypoint` by running the following command:

```shell-session
$ oc label ns bookinfo istio.io/use-waypoint=waypoint-foo --overwrite
```

### 3.7. About Ztunnel update lifecycleCopy link

Understand the Ztunnel rolling update process, and how the process affects connection persistence during a proxy restart.

Ztunnel operates at Layer 4 (L4) of the Open Systems Interconnection (OSI) model and proxies TCP traffic. Ztunnel cannot transfer connection states to another process. Upgrading the Ztunnel DaemonSet affects all traffic on at least one node at a time.

Ztunnel operates at Layer 4 (L4) of the Open Systems Interconnection (OSI) model and proxies TCP traffic. Ztunnel cannot transfer connection states to another process. Upgrading the Ztunnel DaemonSet affects all traffic on at least one node at a time. By default, the Ztunnel DaemonSet uses a`RollingUpdate` strategy. During a restart, each node goes through the following phases:

- Startup: A new`Ztunnel` pod starts on the node while the old pod continues running.
- Readiness: The new`Ztunnel` establishes listeners in each pod on the node and marks itself as ready. For a brief period, both instances run simultaneously, and new connections may be handled by either one.
- Draining: Kubernetes sends a`SIGTERM` to the old`Ztunnel`, which begins the draining process. The old instance closes its listeners so that only the new`Ztunnel` accepts new connections. At all times, at least one`Ztunnel` remains available to handle incoming connections.
- Connection processing: The old Ztunnel continues processing existing connections until the`terminationGracePeriodSeconds` expires.
- Termination: Once the`terminationGracePeriodSeconds` expires, the old`Ztunnel` forcefully terminates any remaining connections.

#### 3.7.1. Configuring the Ztunnel termination grace periodCopy link

Configure a high termination grace period in the`ZTunnel` custom resource (CR) for the application pods to ensure that active connections close gracefully during a rolling update.

Procedure

Update the`terminationGracePeriodSeconds` value in the`ZTunnel` CR to a higher value similar to the following example:

```yaml
apiVersion: sailoperator.io/v1
kind: ZTunnel
metadata:
  name: default
spec:
  version: 1.28.5
  namespace: ztunnel
  values:
    ztunnel:
      terminationGracePeriodSeconds: 300
```

#### 3.7.2. Updating Ztunnel using node drainingCopy link

Drain nodes to force long-lived TCP connections to reconnect through a new`Ztunnel` instance, without risking traffic loss because the node is empty during the proxy swap.

Procedure

Configure the`OnDelete` update strategy in the`ZTunnel` custom resource (CR) to need manual pod deletion before the update to the new version starts, similar to the following example:

```yaml
apiVersion: sailoperator.io/v1
kind: ZTunnel
metadata:
  name: default
spec:
  version: 1.28.5
  namespace: ztunnel
  values:
    ztunnel:
      updateStrategy:
        type: OnDelete
```

1. Update the`version` field in the`ZTunnel` CR to the target version.
2. Drain a node to force all applications to move to other nodes, allowing their long-lived connections to close gracefully based on their`terminationGracePeriodSeconds`.
3. Delete the old`Ztunnel` pod on the empty node and wait for the new pod to start.
4. Mark the node as`schedulable`. Applications that return to the node will automatically use the new Ztunnel.
5. Repeat steps 3 through 5 for all remaining nodes in the cluster.

## Chapter 4. Updating the Istio CNICopy link

Review the update procedure for the Istio Container Network Interface (CNI). Ensure the CNI plugin remains compatible with the OpenShift Service Mesh control plane during an upgrade.

### 4.1. About the Istio CNI update processCopy link

The Istio Container Network Interface (CNI) update process uses`Inplace` updates. When the`IstioCNI` resource changes, the daemonset automatically replaces the existing`istio-cni-node` pods with the specified version of the CNI plugin.

You can use the following field to manage version updates:

`spec.version` defines the CNI plugin version to install. Specify the value in the format`vX.Y.Z`, where`X.Y.Z` represents the required version. For example, use`v1.28.5` to install the CNI plugin version`1.28.5`.

To update the CNI plugin, change the`spec.version` field with the target version. The`IstioCNI` resource also includes a`values` field that exposes configuration options from the`istio-cni` chart.

In ambient mode, the Istio CNI component manages traffic redirection. During`RevisionBased` upgrades, the component remains compatible with the control plane’s old version and continues to manage traffic redirection for both the old and the new control planes throughout the migration.

Note

The Istio CNI is compatible with a control plane running the same minor version or one minor version higher.

After you update the Istio control plane, update the Istio CNI component. The OpenShift Service Mesh Operator deploys a new version of the CNI plugin, replacing the existing one. The`istio-cni-node` DaemonSet pods update using a rolling update strategy, ensuring that traffic redirection rules remain active during the entire update process.

#### 4.1.1. Updating the Istio CNI resource versionCopy link

You can update the Istio Container Network Interface (CNI) resource version by changing the version in the resource. Then, the Service Mesh Operator deploys a new version of the CNI plugin that replaces the old version of the CNI plugin. The`istio-cni-node` pods automatically reconnect to the new CNI plugin.

Prerequisites

- You are logged in to OpenShift Container Platform as a user with the`cluster-admin` role.
- You have installed the Red Hat OpenShift Service Mesh Operator and deployed Istio.
- You have installed the Istio CNI plugin with the required version. In the following example, the`IstioCNI` resource named`default` is deployed in the`istio-cni` namespace.
- You have either updated the Istio control plane to the required version (for`Inplace` strategy) or created a new control plane revision (for`RevisionBased` strategy).

Procedure

Change the version in the`Istio` resource. For example, to update to Istio`1.28.5`, set the`spec.version` field to`1.28.5` by running the following command:

```shell-session
$ oc patch istiocni default -n istio-cni --type='merge' -p '{"spec":{"version":"v1.28.5"}}'
```

Wait for the`IstioCNI` DaemonSet to reach the`Ready` status after the update by running the following command:

```shell-session
$ oc wait --for=condition=Ready istiocnis/default --timeout=5m
```

Confirm that the new version of the CNI plugin is ready by running the following command:

```shell-session
$ oc get istiocni default
```

You should see an output similar to the following example:

```shell-session
NAME      READY   STATUS    VERSION   AGE
default   True    Healthy   v{istio-latest}   7d1h
```

Check the status of the pods by running the following command:

```shell-session
$ oc get pods -n istio-cni
```

You should see an output similar to the following example:

```shell-session
NAME                   READY   STATUS    RESTARTS   AGE
istio-cni-node-abc12   1/1     Running   0          3m
istio-cni-node-def34   1/1     Running   0          3m
istio-cni-node-ghi56   1/1     Running   0          3m
```

Note

When you use the`RevisionBased` strategy, the Istio CNI component remains compatible with many control plane versions. It continues to manage traffic redirection for both the old and the new control planes throughout the migration. The Istio CNI is compatible with a control plane running the same minor version or one minor version higher.

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

## Uninstalling

_Source: https://docs.redhat.com/en/documentation/red_hat_openshift_service_mesh/3.3/html-single/uninstalling/index — body text extracted from HTML `<article>`._

Uninstalling
Red Hat OpenShift Service Mesh 3.3
Uninstalling OpenShift Service Mesh

Red Hat OpenShift Documentation Team

Legal Notice
Abstract

				This documentation provides information about uninstalling OpenShift Service Mesh.

Chapter 1. Uninstalling OpenShift Service Mesh


			Uninstall the Red Hat OpenShift Service Mesh Operator and remove its associated resources, including custom resource definitions (CRDs), by using the OpenShift Container Platform web console or the CLI.

1.1. Uninstalling OpenShift Service Mesh Operator 3 and the Istio control plane using the web console


				Uninstalling Red Hat OpenShift Service Mesh Operator 3 and the Istio control plane from an existing OpenShift Container Platform instance requires removing the following:

-
						Istio resource

-
						IstioCNI resource

-
						Red Hat OpenShift Service Mesh Operator 3

-
						istio-system project

-
						istio-cni project

				Optionally, you can remove the Istio custom resource definitions (CRDs).

				You can uninstall the Red Hat OpenShift Service Mesh Operator 3 either by using the OpenShift Container Platform web console.

Procedure
-

						Delete the Istio resource:

-
								In the OpenShift web console, click Operators → Installed Operators.

-
								Click Istio in the Provided APIs column.

-
								Click the Options menu → Delete Istio.

-
								At the prompt to confirm the action, click Delete.

-

						Delete the IstioCNI resource:

-
								In the OpenShift web console, click Operators → Installed Operators.

-
								Click IstioCNI in the Provided APIs column.

-
								Click the Options menu → Delete IstioCNI.

-
								At the prompt to confirm the action, click Delete.

-

						Uninstall Red Hat OpenShift Service Mesh 3 Operator:

-
								In the OpenShift web console, click Operators → Installed Operators.

-
								Locate Red Hat OpenShift Service Mesh 3 Operator.

-
								Click the Options menu → Uninstall Operator.

-
								At the prompt to confirm the action, select the Delete all operand instances for this operator checkbox.

-
								Click Uninstall.

-

						Delete the istio-system project:

-
								In the OpenShift web console, click Home → Projects.

-
								Locate the name of the istio-system project.

-
								Click the Options menu → Delete Project.

-
								At the prompt to confirm the action, enter the name of the project.

-
								Click Delete.

-

						Delete the istio-cni project:

-
								In the OpenShift web console, click Home → Projects.

-
								Locate the name of the istio-cni project.

-
								Click the Options menu → Delete Project.

-
								At the prompt to confirm the action, enter the name of the project.

-
								Click Delete.

1.2. Uninstall OpenShift Service Mesh by using the CLI


				Uninstalling Red Hat OpenShift Service Mesh Operator 3 and the Istio control plane from an existing OpenShift Container Platform instance requires removing the following:

-
						Istio resource

-
						IstioCNI resource

-
						Red Hat OpenShift Service Mesh Operator 3

-
						istio-system project

-
						istio-cni project

				Optionally, you can remove the Istio custom resource definitions (CRDs).

				You can uninstall the Red Hat OpenShift Service Mesh Operator 3 either by using the OpenShift Container Platform command line interface (CLI).

Procedure
-

						Delete the Istio resource:

-

								Retrieve the Istio resource name by running the following command:

$ oc get istio -n istio-system

-

								Replace <name_of_custom_resource> with the output from the previous command, and run the following command to remove the custom resource:

$ oc delete istio/<name_of_custom_resource>

-

						Delete the IstioCNI resource:

-

								Retrieve the name of the IstioCNI resource by running the following command:

$ oc get istioCNI -n istio-system

-

								Replace <name_of_custom_resource> with the output from the previous command, and run the following command to remove the custom resource:

$ oc delete istioCNI/<name_of_custom_resource>

-

						Delete the istio-system and istio-cni projects:

-

								Delete the istio-system namespace by running the following command:

$ oc delete ns istio-system

-

								Delete the istio-cni namespace by running the following command:

$ oc delete ns istio-cni

-

						Uninstall OpenShift Service Mesh Operator 3:

-

								Delete the servicemeshoperator3 subscription:

$ oc delete subscription servicemeshoperator3 -n openshift-operators

Example output

output: subscription.operators.coreos.com "servicemeshoperator3" deleted

-

								Check the current version of the servicemeshoperator3 subscribed Operator in the currentCSV field by running the following command:

$ oc get clusterserviceversion -n openshift-operators | grep servicemeshoperator3 | awk '{print $1}'

Example output

output: currentCSV: servicemeshoperator3.v3.0.0

-

								Delete the cluster service version (CSV) for the Operator in the target namespace using the currentCSV value from the previous step:

$ oc delete clusterserviceversion servicemeshoperator3.v3.0.0 -n openshift-operators

Example output

clusterserviceversion.operators.coreos.com "servicemeshoperator3.v3.0.0" deleted.

1.3. Delete Istio custom resource definitions


				Deleting Istio custom resource definitions (CRDs) are optional.

Procedure
-
						To delete the Istio CRDs, run the following command:

$ oc get crds -oname | grep -e istio.io -e sailoperator.io | xargs kubectl delete

1.4. Additional resources


-
						Operator Lifecycle Management

Legal Notice


		Copyright © Red Hat.

		Except as otherwise noted below, the text of and illustrations in this documentation are licensed by Red Hat under the Creative Commons Attribution–Share Alike 3.0 Unported license . If you distribute this document or an adaptation of it, you must provide the URL for the original version.

		Red Hat, as the licensor of this document, waives the right to enforce, and agrees not to assert, Section 4d of CC-BY-SA to the fullest extent permitted by applicable law.

		Red Hat, the Red Hat logo, JBoss, Hibernate, and RHCE are trademarks or registered trademarks of Red Hat, LLC. or its subsidiaries in the United States and other countries.

		Linux® is the registered trademark of Linus Torvalds in the United States and other countries.

		XFS is a trademark or registered trademark of Hewlett Packard Enterprise Development LP or its subsidiaries in the United States and other countries.

		The OpenStack® Word Mark and OpenStack logo are trademarks or registered trademarks of the Linux Foundation, used under license.

		All other trademarks are the property of their respective owners.