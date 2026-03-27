# Red Hat OpenShift Lightspeed 1.0

Assembled from Red Hat Documentation (html-single), version 1.0.

---

*Source: https://docs.redhat.com/en/documentation/red_hat_openshift_lightspeed/1.0/html-single/about/index*

Red Hat OpenShift Lightspeed 1.0 
Introduction to OpenShift Lightspeed 
Red Hat OpenShift Documentation Team 
Legal Notice 
Abstract 
This document provides an overview of OpenShift Lightspeed features. 
Chapter 1. About OpenShift Lightspeed 
Copy link Link copied to clipboard! 
Red Hat OpenShift Lightspeed is a generative AI service that helps developers and administrators solve problems by providing context-aware recommendations for OpenShift Container Platform. 
1.1. OpenShift Lightspeed overview 
Copy link Link copied to clipboard! 
Use Red Hat OpenShift Lightspeed to fix and manage your OpenShift clusters. You can operate the virtual assistant by using plain English right inside the OpenShift web console. 
1.1.1. About product coverage 
Copy link Link copied to clipboard! 
Red Hat OpenShift Lightspeed answers your questions by using information from official OpenShift Container Platform documentation. 
1.1.1.1. Product exceptions 
Copy link Link copied to clipboard! 
The OpenShift Container Platform documentation does not cover every Red Hat product. Because of this, OpenShift Lightspeed uses your large language model (LLM) to create answers for these products: 
Builds for Red Hat OpenShift 
Red Hat Advanced Cluster Security for Kubernetes 
Red Hat Advanced Cluster Management for Kubernetes 
Red Hat CodeReady Workspaces 
Red Hat OpenShift GitOps 
Red Hat OpenShift Pipelines 
Red Hat OpenShift Serverless 
Red Hat OpenShift Service Mesh 3.x 
Red Hat Quay 
1.2. OpenShift requirements 
Copy link Link copied to clipboard! 
Hardware and software requirements for OpenShift Lightspeed, including supported OpenShift Container Platform versions and CPU architectures. 
OpenShift Container Platform clusters enable telemetry by default. 
When telemetry is on, OpenShift Lightspeed sends your chats and feedback to Red Hat. 
When telemetry is off, OpenShift Lightspeed does not send this data. 
To stop OpenShift Lightspeed from sending your chats and feedback, you must disable telemetry for the whole cluster. 
1.2.1. Cluster resource requirements 
Copy link Link copied to clipboard! 
Ensure that OpenShift Lightspeed has enough CPU, memory, and storage allocations to support Service performance and cluster stability without impacting other cluster workloads. 
Expand 
Component 
Minimum CPU (Cores) 
Minimum Memory 
Maximum Memory 
Application server 
0.5 
1 GB 
4 Gi 
PostgreSQL database 
0.3 
300 Mi 
2 Gi 
OpenShift Container Platform web console 
0.1 
50 Mi 
100 Mi 
OpenShift Lightspeed operator 
0.1 
64 Mi 
256 Mi 
Show more 
1.3. Large language model (LLM) requirements 
Copy link Link copied to clipboard! 
OpenShift Lightspeed supports Software as a Service (SaaS) and self-hosted large language model (LLM) providers that meet defined authentication requirements. 
The LLM is a type of machine learning model that interprets and generates human-like language. When you use the LLM with a virtual assistant, the LLM can accurately interpret questions and offers helpful answers in a conversational manner. The OpenShift Lightspeed Service must have access to the LLM provider. 
The Service does not provide the LLM for you, so you must configure the LLM before installing the OpenShift Lightspeed Operator. 
Note 
Red Hat does not provide support for any specific models or make suggestions or support statements pertaining to models. 
The OpenShift Lightspeed Service can rely on the following SaaS LLM providers: 
OpenAI 
Microsoft Azure OpenAI 
IBM watsonx 
If you want to self-host a model, you can use Red Hat OpenShift AI or Red Hat Enterprise Linux AI as your model provider. 
1.3.1. IBM watsonx 
Copy link Link copied to clipboard! 
To use IBM watsonx with Red Hat OpenShift Lightspeed, you need an account with IBM Cloud watsonx . For more information, see the Documentation for IBM watsonx as a Service . 
1.3.2. Open AI 
Copy link Link copied to clipboard! 
To use OpenAI with Red Hat OpenShift Lightspeed, you need access to the OpenAI API platform . For more information, see the OpenAI developer platform documentation. 
1.3.3. Microsoft Azure OpenAI 
Copy link Link copied to clipboard! 
To use Microsoft Azure with Red Hat OpenShift Lightspeed, you need access to Microsoft Azure OpenAI . For more information, see the Azure OpenAI documentation . 
1.3.4. Red Hat Enterprise Linux AI 
Copy link Link copied to clipboard! 
Red Hat Enterprise Linux AI is OpenAI API-compatible, and you configure Red Hat Enterprise Linux AI in a similar manner as the OpenAI provider. 
You can configure Red Hat Enterprise Linux AI as the LLM provider. 
Because the Red Hat Enterprise Linux is in a different environment than the OpenShift Lightspeed deployment, the model deployment must allow access by using a secure connection. For more information, see Optional: Allowing access to a model from a secure endpoint . 
OpenShift Lightspeed version 1.0 and later supports vLLM Server version 0.8.4 and later. When self-hosting the LLM with Red Hat Enterprise Linux AI, you can use vLLM Server as the inference engine. 
1.3.5. Red Hat OpenShift AI 
Copy link Link copied to clipboard! 
Red Hat OpenShift AI is OpenAI API-compatible, and you configure Red Hat OpenShift AI largely the same as the OpenAI provider. 
You must deploy the LLM on the Red Hat OpenShift AI single-model serving platform that uses the virtual large language model (vLLM) runtime. If the model deployment runs in a different OpenShift environment than the OpenShift Lightspeed deployment, include a route to expose the model deployment outside the cluster. For more information, see About the single-model serving platform . 
OpenShift Lightspeed version 1.0 and later supports vLLM Server version 0.8.4 and later. When self-hosting the LLM with Red Hat OpenShift AI, you can use vLLM Server as the inference engine. 
1.4. OpenShift Lightspeed Federal Information Processing Standards (FIPS) support 
Copy link Link copied to clipboard! 
Red Hat OpenShift Lightspeed supports Federal Information Processing Standards (FIPS). You can run Red Hat OpenShift Lightspeed on OpenShift clusters that use FIPS mode. 
FIPS is a set of publicly announced standards developed by the National Institute of Standards and Technology (NIST), a part of the U.S. Department of Commerce. The primary purpose of FIPS is to ensure the security and interoperability of computer systems used by U.S. federal government agencies and their associated contractors. 
Important 
When running on OpenShift Container Platform in FIPS mode, it uses the Red Hat Enterprise Linux cryptographic libraries submitted, or planned to be submitted, to NIST for FIPS validation on only the x86_64 , ppc64le , and s390X architectures. For more information about the NIST validation program, see Cryptographic Module Validation Program (NIST). For the latest NIST status of the individual versions of Red Hat Enterprise Linux cryptographic libraries that have been submitted for validation, see Product compliance . 
1.5. Supported architecture 
Copy link Link copied to clipboard! 
OpenShift Lightspeed works with OpenShift Container Platform clusters that use the x86_64 architecture. 
1.6. About running OpenShift Lightspeed in disconnected mode 
Copy link Link copied to clipboard! 
OpenShift Lightspeed works in disconnected clusters without full internet access. 
In a disconnected cluster, you must mirror the container images you need. For more help, see "Mirroring in disconnected environments" in the OpenShift Container Platform documentation. 
Note 
When you mirror images in a disconnected cluster, list the OpenShift Lightspeed Operator with the oc mirror command. 
1.7. About data use 
Copy link Link copied to clipboard! 
OpenShift Lightspeed adds cluster and environment details to your messages. Then, it sends this data to the large language model (LLM) to get an answer. 
OpenShift Lightspeed has limited ability to filter or hide the data you send to the LLM. Do not enter any information into the interface that you want to keep private from the LLM. 
When you send transcripts or feedback to Red Hat, you agree that Red Hat can use the data to improve our Service. The transcript recording data uses the Red Hat Insights system. It follows the same security rules and access limits as that system. 
You can email Red Hat and ask us to delete your data. 
1.8. About data, telemetry, transcript, and feedback collection 
Copy link Link copied to clipboard! 
OpenShift Lightspeed sends your messages and cluster data through a redaction layer. It does this to clean the data before it goes to the LLM. 
Do not enter anything into the OpenShift Lightspeed interface that you want to keep private from the LLM. 
The transcript recording data uses the Red Hat Insights system. It follows the same security rules and access limits as that system. You can learn more in the Red Hat Insights security guide . 
1.9. Remote health monitoring overview 
Copy link Link copied to clipboard! 
Remote Health Monitoring uses the Telemeter Client and Insights Operator to gather and report cluster information for Red Hat analysis and support. 
You can learn how Red Hat collects data in the OpenShift Container Platform documentation. To stop sending chat transcripts or feedback, you must opt out of remote health monitoring. Follow the steps in the "About remote health monitoring" section of the OpenShift Container Platform documentation. 
1.9.1. Transcript collection overview 
Copy link Link copied to clipboard! 
OpenShift Lightspeed sends chat transcripts to Red Hat on a set schedule. The Service uses a redaction layer to filter data before the Service shares or logs it. 
By default, OpenShift Lightspeed sends these transcripts every two hours. Red Hat cannot see your original data. OpenShift Lightspeed hides sensitive data before it reaches any logs. 
OpenShift Lightspeed saves conversation transcripts for a short time. This includes: 
Queries from the user. 
The complete message sent to the configured large language model (LLM) provider, which includes system instructions, referenced documentation, and the user question. 
The complete response from the LLM provider. 
Transcripts come from your cluster and stay linked to it. Red Hat can match these clusters to specific customer accounts. These transcripts do not contain any user data. 
1.9.2. Feedback collection overview 
Copy link Link copied to clipboard! 
OpenShift Lightspeed collects opt-in user feedback from the virtual assistant interface to analyze response accuracy and improve Service quality. 
If you submit feedback, Red Hat stores and receives your feedback score, text, and query. Red Hat also receives the large language model (LLM) response on the same schedule as transcripts. When you use the redaction tools, Red Hat receives only the filtered data. Red Hat does not see the original data. OpenShift Lightspeed hides your data before the system logs it. 
Your feedback stays associated with the cluster where it began. Red Hat can match these clusters to specific customer accounts. This feedback does not contain any user details, and Red Hat cannot link the feedback to any specific person. 
1.9.3. Disabling data collection on the OpenShift Lightspeed Service 
Copy link Link copied to clipboard! 
Disable data collection for Lightspeed by updating the telemetry settings in the OLSConfig custom resource (CR) file settings. 
By default, OpenShift Lightspeed collects information about the questions you ask and the feedback you offer on the answers that the Service generates. 
Prerequisites 
You have a large language model (LLM) provider available for use with the OpenShift Lightspeed Service. 
You have installed the OpenShift Lightspeed Operator. 
You have configured the OLSConfig CR file, which automatically deploys the OpenShift Lightspeed Service. 
Procedure 
Open the OpenShift Lightspeed OLSConfig CR file by running the following command: 
$ oc edit olsconfig cluster 
Change the spec.ols.userDataCollection field to disable data collection for the OpenShift Lightspeed CR. 
apiVersion : ols.openshift.io/v1alpha1 kind : OLSConfig metadata : name : cluster spec : ols : userDataCollection : feedbackDisabled : true transcriptsDisabled : true 
spec.ols.userDataCollection.feedbackDisabled specifies if the Service collects your feedback. 
spec.ols.userDataCollection.transcriptsDisabled specifies if the Service collects your chat log transcripts. 
Save the file. 
1.10. Additional resources 
Copy link Link copied to clipboard! 
About remote health monitoring 
Mirroring in disconnected environments 
Filtering and redacting information 
Opting out of remote health reporting 
Creating the credential secret using the web console 
Creating the credential secret using the CLI 
Creating the Lightspeed custom resource file using the web console 
Creating the Lightspeed custom resource file using the CLI 
Legal Notice 
Copy link Link copied to clipboard! 
Copyright © Red Hat. 
Except as otherwise noted below, the text of and illustrations in this documentation are licensed by Red Hat under the Creative Commons Attribution–Share Alike 3.0 Unported license . If you distribute this document or an adaptation of it, you must provide the URL for the original version. 
Red Hat, as the licensor of this document, waives the right to enforce, and agrees not to assert, Section 4d of CC-BY-SA to the fullest extent permitted by applicable law. 
Red Hat, the Red Hat logo, JBoss, Hibernate, and RHCE are trademarks or registered trademarks of Red Hat, LLC. or its subsidiaries in the United States and other countries. 
Linux ® is the registered trademark of Linus Torvalds in the United States and other countries. 
XFS is a trademark or registered trademark of Hewlett Packard Enterprise Development LP or its subsidiaries in the United States and other countries. 
The OpenStack ® Word Mark and OpenStack logo are trademarks or registered trademarks of the Linux Foundation, used under license. 
All other trademarks are the property of their respective owners.

---

*Source: https://docs.redhat.com/en/documentation/red_hat_openshift_lightspeed/1.0/html-single/release_notes/index*

Release Notes | Red Hat OpenShift Lightspeed | 1.0 | Red Hat Documentation

 

 

 

 

 

 

 

 

# Release Notes

---

Red Hat OpenShift Lightspeed 1.0

## Release Notes

Red Hat OpenShift Documentation Team

Legal Notice

Abstract

This documentation provides information about each OpenShift Lightspeed release.

---

## Chapter 1. OpenShift Lightspeed release notesCopy link

The release notes highlight what is new and what has changed with each Red Hat OpenShift Lightspeed release.

Important

Red Hat OpenShift Lightspeed is designed for Federal Information Processing Standards (FIPS). When running on OpenShift Container Platform in FIPS mode, it uses the Red Hat Enterprise Linux cryptographic libraries submitted (or planned to be submitted) to NIST for FIPS validation on only the`x86_64`,`ppc64le`, and`s390X` architectures. For more information about the NIST validation program, see [Cryptographic Module Validation Program](https://csrc.nist.gov/Projects/cryptographic-module-validation-program/validated-modules). For the latest NIST status of the individual versions of Red Hat Enterprise Linux cryptographic libraries that have been submitted for validation, see [Product compliance](https://access.redhat.com/en/compliance).

### 1.1. OpenShift Lightspeed version 1.0.10Copy link

Red Hat OpenShift Lightspeed 1.0.10 is now available on OpenShift Container Platform 4.16 and later.

#### 1.1.1. EnhancementsCopy link

The following enhancements have been made with Red Hat OpenShift Lightspeed 1.0.10:

- Legal Notice
- With this update, the PatternFly 5 User Interface supports Markdown table rendering, ensuring consistency and compatibility across UI versions. To achieve this, the PatternFly Chatbot extension integrates the`remark-gfm` plugin. As a result, you can now present and view data more effectively.
- With this update, OpenShift Lightspeed supports the rendering of Markdown tables in responses on OpenShift Container Platform versions 4.16, 4.17 and 4.18. As a result, these product versions now align with OpenShift Container Platform version 4.19 and later, which already support Markdown tables in responses.
- With this update, OpenShift Lightspeed introduces token management for tool calls, which prevents OpenShift Lightspeed from exceeding the message context window size. This enhancement aims to avoid errors due to`context_window` limit during tool calls, ensuring a better user experience. It also reserves tokens for tool output, managing the amount of data displayed in a single tool output and helping to retain a smooth conversation flow without the need to end and restart sessions.
- With this update, MCP headers and MCP definitions have changed. Previously OpenShift Lightspeed supported three methods for the MCP client to send messages back and forth with the MCP server: Server-Sent Events (SSE), Standard Input/Output (stdio), and Streamable Hypertext Transfer Protocol (HTTP). Now, OpenShift Lightspeed supports only Streamable HTTP. See [OLSConfig CR requires manual update of MCP server configuration before upgrading OpenShift Lightspeed Operator to 1.0.10](https://access.redhat.com/solutions/7139435) for more information.
- With this update, you can enable tool filtering to control the list of tools provided to the LLM model. To enable this feature, specify the`toolFilteringConfig` field in the`OLSConfig` custom resource (CR) file.

### 1.2. OpenShift Lightspeed version 1.0.9Copy link

Red Hat OpenShift Lightspeed 1.0.9 is now available on OpenShift Container Platform 4.16 and later.

#### 1.2.1. EnhancementsCopy link

The following enhancements have been made with Red Hat OpenShift Lightspeed 1.0.9:

- With this update, OpenShift Lightspeed supports automatic updates of BYO Knowledge images that use floating tags, such as`latest`. If over time a BYO Knowledge image tag points to different underlying images, OpenShift Lightspeed detects those changes and updates the corresponding BYO Knowledge database accordingly. This feature is built using Red Hat OpenShift`ImageStream` objects. OpenShift Container Platform clusters check for updates to`ImageStream` objects every 15 minutes.
- Starting with this release, you can specify an array of image pull secrets in the`OLSConfig` Custom Resource (CR) file. Pull secrets contain authentication credentials for remote image registries and are used to pull RAG BYO Knowledge images from image registries requiring authentication.

#### 1.2.2. Fixed issuesCopy link

Red Hat OpenShift Lightspeed 1.0.9 fixes the following issues:

Note

Some linked Jira tickets are accessible only with Red Hat credentials.

Previously, you defined replicas in the`DeploymentConfig` and it was not obvious which pod each replica referred to. As a result, when you changed the replica count it was not clear which pod should be scaled. With this release, you configure replica count with each specific component’s deployments:`APIContainer`,`ConsoleContainer`, and`DatabaseContainer`. This change allows you to specify the number of replicas for each pod. Note that the value for the number of replicas can only be changed for`APIContainer`. For all other containers, the number of replicas is fixed to`1`.

[OLS-2322](https://issues.redhat.com/browse/OLS-2322)

Previously, support for custom HTTPS credentials for UI to backend communications was spread between two configurations:`UIDeployment` and`TLSConfig`. As a result, you had to ensure that both places are configured, which was error prone. With this release, support for custom HTTPS credentials for UI to backend communications is consolidated in one place,`TLSConfig`.

Note

If you do not provide`ca.crt` in the current implementation, the OpenShift Container Platform Console proxy uses the default system trust store.

[OLS-2322](https://issues.redhat.com/browse/OLS-2322)

Before this update, OpenShift Lightspeed failed to handle unreachable MCP servers, resulting in loss of all tool functionality. With this release, OpenShift Lightspeed isolates the failing MCP server, keeping other tools operational and preserving full assistant capabilities.

[OLS-2393](https://issues.redhat.com/browse/OLS-2322)

### 1.3. OpenShift Lightspeed version 1.0.8Copy link

Red Hat OpenShift Lightspeed 1.0.8 is now available on OpenShift Container Platform 4.16 and later.

#### 1.3.1. EnhancementsCopy link

The following enhancements have been made with Red Hat OpenShift Lightspeed 1.0.8:

- With this update, it is now possible to attach the cluster details as YAML code to the OpenShift Lightspeed prompt from the Nodes and Add-ons tabs of the Advanced Cluster Management UI.
- With this update, it is now possible to attach`ApplicationSet` details as YAML to the OpenShift Lightspeed prompt from the Application details page of the Advanced Cluster Management UI.

#### 1.3.2. Fixed issuesCopy link

Red Hat OpenShift Lightspeed 1.0.8 fixes the following issues:

Note

Some linked Jira tickets are accessible only with Red Hat credentials.

- Before this update, if you tried to attach two alerts with the same`alertname` to the OpenShift Lightspeed prompt, the second alert would replace the first alert. With this update, it is now possible to attach multiple alerts with the same`alertname` to the OpenShift Lightspeed prompt. [OLS-2293](https://issues.redhat.com/browse/OLS-2293)

### 1.4. OpenShift Lightspeed version 1.0.7Copy link

Red Hat OpenShift Lightspeed 1.0.7 is now available on OpenShift Container Platform 4.16 and later.

#### 1.4.1. EnhancementsCopy link

The following enhancements have been made with Red Hat OpenShift Lightspeed 1.0.7:

With this release, the Red Hat-provided Operator catalogs have moved from OperatorHub to the software catalog and the Operators navigation item is renamed to Ecosystem in the console. The unified software catalog presents Operators, Helm charts, and other installable content in the same console view.

To manage, update, and remove installed Operators, select Ecosystem → Installed Operators.

Note

Currently, the console only supports managing Operators by using Operator Lifecycle Manager (OLM) Classic. If you want to use OLM v1 to install and manage cluster extensions, such as Operators, you must use the CLI.

To manage the default or custom catalog sources, you still interact with OperatorHub custom resource (CR) in the console or CLI.

- To access the Red Hat-provided Operator catalogs in the console, select Ecosystem → Software Catalog.

#### 1.4.2. Fixed issuesCopy link

Red Hat OpenShift Lightspeed 1.0.7 fixes the following issues:

- This release introduces the ability to copy the entire OpenShift Lightspeed conversation to the clipboard.
- Previously, an invalid model reference in the`OLSConfig` custom resource (CR) caused the OpenShift Lightspeed installation to fail. Now, the`OLSConfig` CR health check ensures that the model exists before use, providing error details in the console. This results in improved server health checks, providing more precise status information to the web console, improving the user experience. [OLS-1949](https://issues.redhat.com/browse/OLS-1949)

### 1.5. OpenShift Lightspeed version 1.0.6Copy link

Red Hat OpenShift Lightspeed 1.0.6 is now available on OpenShift Container Platform 4.16 and later.

#### 1.5.1. EnhancementsCopy link

Red Hat OpenShift Lightspeed 1.0.6 provides the following enhancements:

- This release makes Red Hat OpenShift Lightspeed 1.0.6 generally available, and is supported on OpenShift Container Platform 4.16 and later.
- When viewing the Cluster Overview page in the Red Hat Advanced Cluster Management for Kubernetes web console, you can attach the`ManagedCluster` and`ManagedClusterInfo` YAML using the Attach menu.
- This release introduces a check for the total size of all uploaded attachments. This is in addition to the existing check for individual attachment sizes. The check for total size helps prevent exceeding the context window size of the large language model (LLM) so that the model does not receive more text than it can process in a single request.

#### 1.5.2. Fixed issuesCopy link

Red Hat OpenShift Lightspeed 1.0.6 fixes the following issues:

- Before this update, when the OpenShift Lightspeed Service was connecting to Microsoft Azure OpenAI using an additional TLS certificate, the Service was not able to successfully connect to the large language model (LLM). Now, the Service adds the TLS certificate to the trust bundle. As a result, the Service connects to the LLM. [OLS-2112](https://issues.redhat.com/browse/OLS-2112)
- Before this update when you disabled transcript and feedback collection, the OpenShift Lightspeed Service failed to deploy the Model Context Protocol (MCP) server. Now, when you disable transcript and feedback collection, the Service deploys the MCP server. [OLS-2113](https://issues.redhat.com/browse/OLS-2113)

### 1.6. OpenShift Lightspeed version 1.0.5Copy link

Red Hat OpenShift Lightspeed 1.0.5 is now available on OpenShift Container Platform 4.16 and later.

#### 1.6.1. EnhancementsCopy link

Red Hat OpenShift Lightspeed 1.0.5 provides the following enhancements:

- This release makes Red Hat OpenShift Lightspeed 1.0.5 generally available, and is supported on OpenShift Container Platform 4.16 and later.
- With this release, you can attach`cron job` logs to the question you submit to Red Hat OpenShift Lightspeed, enabling the Service to provide more context-aware troubleshooting, diagnostics, and analysis of automated job outcomes, errors, or irregular behaviors.

#### 1.6.2. Fixed issuesCopy link

Red Hat OpenShift Lightspeed 1.0.5 fixes the following issues:

Before this update, Red Hat OpenShift Lightspeed failed to use the GPT-5 model, and OpenAI generated an`Error code: 400` error message. With this release, Red Hat OpenShift Lightspeed supports the GPT-5 model. [OLS-2041](https://issues.redhat.com/browse/OLS-2041)

### 1.7. OpenShift Lightspeed version 1.0.4Copy link

Red Hat OpenShift Lightspeed 1.0.4 is now available on OpenShift Container Platform 4.16 and later.

#### 1.7.1. EnhancementsCopy link

Red Hat OpenShift Lightspeed 1.0.4 provides the following enhancements:

- This release makes Red Hat OpenShift Lightspeed 1.0.4 generally available, and is supported on OpenShift Container Platform 4.16 and later.
- The cluster interaction Technology Preview feature now uses the Model Context Protocol (MCP). MCP is an open protocol that standardizes how applications provide context to large language models (LLMs). Using the protocol, an MCP server offers a standardized way for an LLM to increase context by requesting and receiving real-time information from external resources.

#### 1.7.2. Fixed issuesCopy link

Red Hat OpenShift Lightspeed 1.0.4 fixes the following issues:

Before this update, the OpenShift Lightspeed Service incorrectly rejected the`api_version` parameter for Microsoft Azure OpenAI providers, despite it being officially supported. This resulted in`Extra inputs are not permitted` errors during API calls.

This release fixes the Microsoft Azure OpenAI API version validation issue in the OpenShift Lightspeed Service, which now allows for correct parameter usage and eliminates the error. [OLS-1973](https://issues.redhat.com/browse/OLS-1973)

### 1.8. OpenShift Lightspeed version 1.0.3Copy link

Red Hat OpenShift Lightspeed 1.0.3 is now available on OpenShift Container Platform 4.15 and later.

#### 1.8.1. EnhancementsCopy link

The following enhancements are made with Red Hat OpenShift Lightspeed 1.0.3:

With this update, when you specify BYO Knowledge information sources in`OLSConfig.spec.ols.rag` specification file you only have to specify the URL path for the image.

The custom resource (CR) uses the following default settings:

Example configuration

```yaml
spec:
  ols:
    rag:
      - image: quay.io/<username>/my-byok-image:latest 
```

1

1

Where`image` specifies the tag for the image that was pushed to the image registry so that the OpenShift Lightspeed Operator can access the custom content. The OpenShift Lightspeed Operator can work with more than one RAG database that you create.

Previously, the`indexPath` and`indexID` parameters were required. Now they are optional. The default values for the parameters are`/rag/vector_db` and`vector_db_index` respectively.

- This release makes Red Hat OpenShift Lightspeed 1.0.3 generally available, and is supported on OpenShift Container Platform 4.15 and later.

#### 1.8.2. Fixed issuesCopy link

The following issues are fixed with Red Hat OpenShift Lightspeed 1.0.3:

- Before this update, the OpenShift Lightspeed service could not reload BYO Knowledge images with floating tags. This condition occurred even after a restart because the`ImagePullPolicy` parameter was hardcoded to`PullIfNotPresent`. As a result, BYO Knowledge images failed to reload from the container registry, causing users to experience stale images. With this update, when the OpenShift Lightspeed service restarts, it pulls the BYO Knowledge images from the container registry, ensuring consistent access to BYO Knowledge images. [OLS-1956](https://issues.redhat.com/browse/OLS-1956).

### 1.9. OpenShift Lightspeed version 1.0.2Copy link

Red Hat OpenShift Lightspeed 1.0.2 is now available on OpenShift Container Platform 4.15 and later.

#### 1.9.1. EnhancementsCopy link

The following enhancements are made with Red Hat OpenShift Lightspeed 1.0.2:

- This release makes Red Hat OpenShift Lightspeed 1.0.2 generally available, and is supported on OpenShift Container Platform 4.15 and later.

#### 1.9.2. Fixed issuesCopy link

The following issues are fixed with Red Hat OpenShift Lightspeed 1.0.2:

- Before this update, the cluster-wide proxy incorrectly routed large language model (LLM) provider connections, ignoring the`no_proxy` environment variable. This led to end users experiencing OpenShift Lightspeed attempting to connect to the LLM provider through a proxy when the`no_proxy` variable was explicitly defined. In this release, OpenShift Lightspeed now respects the`no_proxy` environment variable for LLM provider connections. As a result, OpenShift Lightspeed ignores proxy settings when`no_proxy` is set, enhancing direct LLM provider connections. [OLS-1861](https://issues.redhat.com/browse/OLS-1861).

### 1.10. OpenShift Lightspeed version 1.0.1Copy link

Red Hat OpenShift Lightspeed 1.0.1 is now available on OpenShift Container Platform 4.15 and later.

#### 1.10.1. EnhancementsCopy link

The following enhancements are made with Red Hat OpenShift Lightspeed 1.0.1:

This release introduces the BYO Knowledge tool as a Technology Preview feature. You can use this tool to add your own custom content as a knowledge source so that a large language model (LLM) can make use of information that is unique to your environment. By using this information, the tool creates a retrieval-augmented generation (RAG) database to enhance the knowledge that is available to the LLM.

Important

The BYO Knowledge tool is a Technology Preview feature only. Technology Preview features are not supported with Red Hat production service level agreements (SLAs) and might not be functionally complete. Red Hat does not recommend using them in production. These features provide early access to upcoming product features, enabling customers to test functionality and provide feedback during the development process.

For more information about the support scope of Red Hat Technology Preview features, see [Technology Preview Features Support Scope](https://access.redhat.com/support/offerings/techpreview/).

- This release makes Red Hat OpenShift Lightspeed 1.0.1 generally available, and is supported on OpenShift Container Platform 4.15 and later.

#### 1.10.2. Known issuesCopy link

The following issues are identified with Red Hat OpenShift Lightspeed 1.0.1:

If the OpenShift Container Platform cluster has a cluster-wide proxy and the`no_proxy` environment variable is set, the OpenShift Lightspeed service tries to connect to the large language model (LLM) provider through the proxy. [OLS-1861](https://issues.redhat.com/browse/OLS-1861).

Workaround: None.

### 1.11. OpenShift Lightspeed version 1.0Copy link

Red Hat OpenShift Lightspeed 1.0 is now available on OpenShift Container Platform 4.15 and later.

#### 1.11.1. EnhancementsCopy link

The following enhancements are made with Red Hat OpenShift Lightspeed 1.0:

This release includes the following Technology Preview features:

- Cluster interaction
- PostgreSQL persistence
- Token quota

Important

Technology Preview features are not supported with Red Hat production service level agreements (SLAs) and might not be functionally complete. Red Hat does not recommend using them in production. These features provide early access to upcoming product features, enabling customers to test functionality and provide feedback during the development process.

For more information about the support scope of Red Hat Technology Preview features, see [Technology Preview Features Support Scope](https://access.redhat.com/support/offerings/techpreview/).

#### 1.11.2. Known issuesCopy link

The following issues are identified with Red Hat OpenShift Lightspeed 1.0:

For OpenShift Container Platform 4.17 and later, the OpenShift Lightspeed icon disappears when you click Create Namespace or Create Project from one of the following locations in the OpenShift Container Platform web console:

- Administration → Namespaces
- Home → Projects → Create Project
- The Project drop-down menu located at the top of most pages

Workaround: Refresh the web browser and the OpenShift Lightspeed icon appears. [OLS-1815](https://issues.redhat.com/browse/OLS-1815).

Changing the value of the`quota` parameter in the`spec.ols.quotaHandlersConfig.limitersConfig` specification of the`OLSConfig` custom resource file does not take effect until the currently defined quota period expires.

Workaround: Delete the OpenShift Lightspeed Operator. Ensure that any operand pods that the OpenShift Lightspeed Operator manages, and the Persistent Volume Claim`lightspeed-postgres-pvc` associated with the`postgres` pod are also deleted. Then, install the OpenShift Lightspeed Operator again. [OLS-1826](https://issues.redhat.com/browse/OLS-1826)

After restarting the`postgres` pod, the OpenShift Lightspeed service pod fails to connect to the`postgres` pod.

Workaround: Restart the OpenShift Lightspeed service pod so that the service pod connects to the`postgres` pod. [OLS-1835](https://issues.redhat.com/browse/OLS-1815).

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

*Source: https://docs.redhat.com/en/documentation/red_hat_openshift_lightspeed/1.0/html-single/install/index*

Red Hat OpenShift Lightspeed 1.0 
Installing OpenShift Lightspeed 
Red Hat OpenShift Documentation Team 
Legal Notice 
Abstract 
This documentation provides information about installing OpenShift Lightspeed. 
Chapter 1. Installing OpenShift Lightspeed 
Copy link Link copied to clipboard! 
Enable OpenShift Lightspeed in your cluster by installing the OpenShift Lightspeed Operator and configuring the Service to interact with a large language model (LLM) provider. 
1.1. Large language model (LLM) overview 
Copy link Link copied to clipboard! 
Learn how the OpenShift Lightspeed Service uses large language models (LLMs) to generate intelligent, context-aware answers to your questions about OpenShift Container Platform. 
You can configure Red Hat Enterprise Linux AI or Red Hat OpenShift AI as the LLM provider for the OpenShift Lightspeed Service. Either LLM provider can use a server or inference service that processes inference queries. 
Alternatively, you can connect the OpenShift Lightspeed Service to a publicly available LLM provider, such as IBM watsonx, OpenAI, or Microsoft Azure OpenAI. 
Note 
Installing the OpenShift Lightspeed Operator does not install an LLM provider. You must have an LLM provider available for use before you install the OpenShift Lightspeed Operator. 
1.1.1. Red Hat Enterprise Linux AI with OpenShift Lightspeed 
Copy link Link copied to clipboard! 
You can use Red Hat Enterprise Linux AI to host an LLM. 
For more information, see Generating a custom LLM by using RHEL AI . 
1.1.2. Red Hat OpenShift AI with OpenShift Lightspeed 
Copy link Link copied to clipboard! 
You can use Red Hat OpenShift AI to host an LLM. 
For more information, see About model serving . 
1.1.3. IBM watsonx with OpenShift Lightspeed 
Copy link Link copied to clipboard! 
To configure IBM watsonx as the LLM provider, you need an IBM Cloud project with access to IBM watsonx. You also need your IBM watsonx API key. 
For more information, see the official IBM watsonx product documentation . 
1.1.4. OpenAI with OpenShift Lightspeed 
Copy link Link copied to clipboard! 
To configure OpenAI as the LLM provider with OpenShift Lightspeed, you need either the OpenAI API key or the OpenAI project name during the configuration process. 
The OpenAI Service has a feature for projects and service accounts. You can use a service account in a dedicated project so that you can precisely track OpenShift Lightspeed usage. 
For more information, see the official OpenAI product documentation . 
1.1.5. Microsoft Azure OpenAI with OpenShift Lightspeed 
Copy link Link copied to clipboard! 
To configure Microsoft Azure OpenAI as the LLM provider, you need a Microsoft Azure OpenAI Service instance. You must have at least one model deployment in Microsoft Azure OpenAI Studio for that instance. 
For more information, see the official Microsoft Azure OpenAI product documentation . 
1.2. About subscription requirements 
Copy link Link copied to clipboard! 
Review the supported OpenShift Container Platform product subscriptions and account requirements necessary to activate and use the OpenShift Lightspeed Service in your environment. 
Red Hat OpenShift Lightspeed requires a valid and active subscription to one of the listed products. 
Red Hat OpenShift Kubernetes Engine 
Red Hat OpenShift Virtualization Engine 
OpenShift Container Platform 
Red Hat OpenShift Platform Plus 
1.3. About adding Operators to a cluster 
Copy link Link copied to clipboard! 
Use the software catalog or OperatorHub to install Operators on your OpenShift Container Platform cluster. These tools use the Operator Lifecycle Manager (OLM) to manage your software. 
1.3.1. Installing the OpenShift Lightspeed Operator from the OperatorHub 
Copy link Link copied to clipboard! 
Install the OpenShift Lightspeed Operator so that you can configure the OpenShift Lightspeed Service. 
Prerequisites 
You have deployed OpenShift Container Platform 4.16 to 4.19. 
You have logged in to the OpenShift Container Platform web console as a user with the cluster-admin role. 
You have successfully configured your large language model (LLM) provider so that OpenShift Lightspeed can communicate with it. 
Procedure 
In the OpenShift Container Platform web console, navigate to the Operators → OperatorHub page. 
Search for OpenShift Lightspeed. 
Locate the OpenShift Lightspeed Operator, and click to select it. 
When the prompt that discusses the community operator displays, click Continue . 
Click Install . 
Use the default installation settings presented, and click Install to continue. 
Click Operators → Installed Operators to verify the OpenShift Lightspeed Operator installation. Succeeded displays in the Status column. 
1.3.2. Installing the OpenShift Lightspeed Operator from the software catalog 
Copy link Link copied to clipboard! 
Install the OpenShift Lightspeed Operator so that you can configure the OpenShift Lightspeed Service. 
Prerequisites 
You have deployed OpenShift Container Platform 4.20 or later. 
You have logged in to the OpenShift Container Platform web console as a user with the cluster-admin role. 
You have successfully configured your large language model (LLM) provider so that OpenShift Lightspeed can communicate with it. 
Procedure 
In the OpenShift Container Platform web console, navigate to the Ecosystem → Software Catalog page. 
Click the Project drop-down list, and enable the toggle switch to show default projects. 
Enter openshift-marketplace in the search field. 
Click to select openshift-marketplace . 
Search for OpenShift Lightspeed. 
Locate the OpenShift Lightspeed Operator, and click to select it. 
When the prompt that discusses the OpenShift Lightspeed Operator displays, click Install . 
Use the default installation settings presented, and click Install to continue. 
Click Ecosystem → Installed Operators to verify the OpenShift Lightspeed Operator installation. Succeeded displays in the Status column. 
Additional resources 
About Operator installation with OperatorHub 
About Operator installation with software catalog 
Legal Notice 
Copy link Link copied to clipboard! 
Copyright © Red Hat. 
Except as otherwise noted below, the text of and illustrations in this documentation are licensed by Red Hat under the Creative Commons Attribution–Share Alike 3.0 Unported license . If you distribute this document or an adaptation of it, you must provide the URL for the original version. 
Red Hat, as the licensor of this document, waives the right to enforce, and agrees not to assert, Section 4d of CC-BY-SA to the fullest extent permitted by applicable law. 
Red Hat, the Red Hat logo, JBoss, Hibernate, and RHCE are trademarks or registered trademarks of Red Hat, LLC. or its subsidiaries in the United States and other countries. 
Linux ® is the registered trademark of Linus Torvalds in the United States and other countries. 
XFS is a trademark or registered trademark of Hewlett Packard Enterprise Development LP or its subsidiaries in the United States and other countries. 
The OpenStack ® Word Mark and OpenStack logo are trademarks or registered trademarks of the Linux Foundation, used under license. 
All other trademarks are the property of their respective owners.

---

*Source: https://docs.redhat.com/en/documentation/red_hat_openshift_lightspeed/1.0/html-single/configure/index*

Configure | Red Hat OpenShift Lightspeed | 1.0 | Red Hat Documentation

 

 

 

 

 

 

 

 

# Configure

---

Red Hat OpenShift Lightspeed 1.0

## Configuring OpenShift Lightspeed

Red Hat OpenShift Documentation Team

Legal Notice

Abstract

This documentation provides information about configuring OpenShift Lightspeed.

---

## Chapter 1. Configuring and deploying OpenShift LightspeedCopy link

Configure and deploy the OpenShift Lightspeed Service to enable AI-powered assistance in your OpenShift Container Platform cluster.

Note

The instructions assume that you are installing OpenShift Lightspeed using the`kubeadmin` user account. If you are using a regular user account with`cluster-admin` privileges, read the section of the documentation that discusses Role-Based Access Control (RBAC).

First, create a credential secret using the credentials for your large language model (LLM) provider. Next, create the`OLSConfig` custom resource (CR) that the Operator uses to deploy the Service. Finally, verify that the OpenShift Lightspeed Service is operating.

Important

Starting with OpenShift Container Platform 4.19, the perspectives in the web console are unified. The Developer perspective is no longer enabled by default.

All users can interact with all OpenShift Container Platform web console features. However, if you are not the cluster owner, you might need to request permission to certain features from the cluster owner.

You can still enable the Developer perspective. On the Getting Started pane in the web console, you can take a tour of the console, find information on setting up your cluster, view a quick start for enabling the Developer perspective, and follow links to explore new features and capabilities.

### 1.1. Creating the credentials secret by using the web consoleCopy link

Use the OpenShift Container Platform web console to store the API token that OpenShift Lightspeed uses to authenticate with the large language model (LLM) provider.

As another option, Microsoft Azure also supports authentication by using Microsoft Entra ID.

Prerequisites

- Legal Notice
- You have logged in to the OpenShift Container Platform web console as a user with the`cluster-admin` role. As another option, you can log in to a user account that has permission to create a secret to store the Provider tokens.
- You have installed the OpenShift Lightspeed Operator.

Procedure

Paste the YAML content for your LLM provider into the text area of the web console.

Note

The YAML parameter is always`apitoken` regardless of what the LLM provider calls the access details.

Use the following example to create the Secret to provide OpenShift Lightspeed with the OpenAI API key.

```yaml
apiVersion: v1
kind: Secret
metadata:
  name: openai
  namespace: openshift-lightspeed
type: Opaque
stringData:
  apitoken: <api_token>
```

- `stringData.apitoken` represents the API token, and is not`base64` encoded.

Use the following example to create the Secret to provide OpenShift Lightspeed with the Red Hat Enterprise Linux AI key.

```yaml
apiVersion: v1
stringData:
  apitoken: <api_token>
kind: Secret
metadata:
  name: rhelai-api-keys
  namespace: openshift-lightspeed
type: Opaque
```

- `stringData.apitoken` represents the API token. The token must be`base64` encoded when stored in a secret.

Use the following example to create the Secret to provide OpenShift Lightspeed with the Red Hat OpenShift AI key.

```yaml
apiVersion: v1
kind: Secret
metadata:
  name: rhoai-api-keys
  namespace: openshift-lightspeed
type: Opaque
stringData:
  apitoken: <api_token>
```

- `stringData.apitoken` represents the API token. The token must be`base64` encoded when stored in a secret.

Use the following example to create the Secret to provide OpenShift Lightspeed with the IBM watsonx key.

```yaml
apiVersion: v1
kind: Secret
metadata:
  name: watsonx-api-keys
  namespace: openshift-lightspeed
type: Opaque
stringData:
  apitoken: <api_token>
```

- `stringData.apitoken` represents the API token. The token must be`base64` encoded when stored in a secret.

Use the following example to create the Secret to provide OpenShift Lightspeed with the Microsoft Azure OpenAI key.

```yaml
apiVersion: v1
kind: Secret
metadata:
  name: azure-api-keys
  namespace: openshift-lightspeed
type: Opaque
stringData:
  apitoken: <api_token>
```

- `stringData.apitoken` represents the API token. The token must be`base64` encoded when stored in a secret.

Optional: As another option with Microsoft Azure OpenAI you can use Microsoft Entra ID to authenticate your LLM provider. Microsoft Entra ID users must configure the required roles for their Microsoft Azure OpenAI resource. For more information, see the official Microsoft [Cognitive Services OpenAI Contributor](https://learn.microsoft.com/en-us/azure/ai-services/openai/how-to/role-based-access-control#cognitive-services-openai-contributor)(Microsoft Azure OpenAI Service documentation). Use the following example to authenticate by using Microsoft Entra ID.

```yaml
apiVersion: v1
data:
  client_id: <base64_encoded_client_id>
  client_secret: <base64_encoded_client_secret>
  tenant_id: <base64_encoded_tenant_id>
kind: Secret
metadata:
  name: azure-api-keys
  namespace: openshift-lightspeed
type: Opaque
```

- `data.apitoken` represents the API token. The token must be`base64` encoded when stored in a secret.

### 1.2. Creating the OpenShift Lightspeed custom resource file by using the web consoleCopy link

Use the OpenShift Container Platform web console to create the custom resource (CR) file required to deploy OpenShift Lightspeed.

The specific content of the CR file is unique for each large language model (LLM) provider. Choose the configuration file for the LLM provider that you are using.

Prerequisites

- Click Create.
- You have logged in to the OpenShift Container Platform web console as a user with the`cluster-admin` role. As another option, you have logged in to a user account that has permission to create a cluster-scoped CR file.
- You have an LLM provider available for use with the OpenShift Lightspeed Service.
- You have installed the OpenShift Lightspeed Operator.

Procedure

Paste the YAML content for your LLM provider into the text area of the web console.

Use the following example to create the`OLSConfig` CR to configure OpenShift Lightspeed with your OpenAI provider:

```yaml
apiVersion: ols.openshift.io/v1alpha1
kind: OLSConfig
metadata:
  name: cluster
spec:
  llm:
    providers:
      - name: myOpenai
        type: openai
        credentialsSecretRef:
          name: credentials
        url: https://api.openai.com/v1
        models:
          - name: <model_name>
  ols:
    hideIcon: true
    defaultModel: <model_name>
    defaultProvider: myOpenai
```

- `spec.ols.hideIcon` specifies whether to hide the OpenShift Lightspeed icon in the OpenShift Container Platform web console. The default value is`false`. Set this field to`true` to hide the icon from users, such as those who do not have access to the OpenShift Lightspeed API.

Use the following example to create the`OLSConfig` CR to configure OpenShift Lightspeed with your Red Hat Enterprise Linux AI provider:

```yaml
apiVersion: ols.openshift.io/v1alpha1
kind: OLSConfig
metadata:
  name: cluster
spec:
  llm:
    providers:
    - credentialsSecretRef:
        name: rhelai-api-keys
      models:
      - name: models/<model_name>
      name: rhelai
      type: rhelai_vllm
      url: <url>
  ols:
    hideIcon: true
    defaultProvider: rhelai
    defaultModel: models/<model_name>
```

- `spec.llm.providers.credentialsSecretRef.name` specifies the name of the Secret that has the API key for the provider. By default, the Red Hat Enterprise Linux AI API key requires a token as part of the request. If your Red Hat Enterprise Linux AI endpoint does not require a token, you must still set the token value to any valid string for the request to authenticate.
- `spec.llm.providers.url` specifies the URL endpoint for the provider. The URL must end with`v1` to be valid. For example, [https://3.23.103.8:8000/v1](https://3.23.103.8:8000/v1).
- `spec.ols.hideIcon` specifies whether to hide the OpenShift Lightspeed icon in the OpenShift Container Platform web console. The default value is`false`. Set this field to`true` to hide the icon from users, such as those who do not have access to the OpenShift Lightspeed API.

Use the following example to create the`OLSConfig` CR to configure OpenShift Lightspeed with your Red Hat OpenShift AI provider:

```yaml
apiVersion: ols.openshift.io/v1alpha1
kind: OLSConfig
metadata:
  name: cluster
spec:
  llm:
    providers:
    - credentialsSecretRef:
        name: rhoai-api-keys
      models:
      - name: <model_name>
      name: red_hat_openshift_ai
      type: rhoai_vllm
      url: <url>
  ols:
    hideIcon: true
    defaultProvider: red_hat_openshift_ai
    defaultModel: <model_name>
```

- `spec.llm.providers.credentialsSecretRef.name` specifies the name of the Secret that has the API key for the provider. If your provider configuration does not require a token, you must still provide a Secret containing a valid string for the request to authenticate.
- `spec.llm.providers.url` specifies the URL endpoint for the provider. The URL must end with`v1` to be valid. For example,`https://..com:443/v1`.
- `spec.ols.hideIcon` specifies whether to hide the OpenShift Lightspeed icon in the OpenShift Container Platform web console. The default value is`false`. Set this field to`true` to hide the icon from users, such as those who do not have access to the OpenShift Lightspeed API.

Use the following example to create the`OLSConfig` CR to configure OpenShift Lightspeed with your Microsoft Azure OpenAI provider:

```yaml
apiVersion: ols.openshift.io/v1alpha1
kind: OLSConfig
metadata:
  name: cluster
spec:
  llm:
    providers:
      - credentialsSecretRef:
          name: azure-api-keys
        apiVersion: <api_version_for_azure_model>
        deploymentName: <azure_ai_deployment_name>
        models:
        - name: <model_name>
        name: myAzure
        type: azure_openai
        url: <azure_ai_deployment_url>
  ols:
    hideIcon: true
    defaultModel: <model_name>
    defaultProvider: myAzure
```

- `spec.ols.hideIcon` specifies whether to hide the OpenShift Lightspeed icon in the OpenShift Container Platform web console. The default value is`false`. Set this field to`true` to hide the icon from users, such as those who do not have access to the OpenShift Lightspeed API.

Use the following example to create the`OLSConfig` CR to configure OpenShift Lightspeed with your IBM watsonx provider:

```yaml
apiVersion: ols.openshift.io/v1alpha1
kind: OLSConfig
metadata:
  name: cluster
spec:
  llm:
    providers:
      - name: myWatsonx
        type: watsonx
        credentialsSecretRef:
          name: watsonx-api-keys
        url: <ibm_watsonx_deployment_name>
        projectID: <ibm_watsonx_project_id>
        models:
          - name: ibm/<model_name>
  ols:
    hideIcon: true
    defaultModel: ibm/<model_name>
    defaultProvider: myWatsonx
```

- `spec.ols.hideIcon` specifies whether to hide the OpenShift Lightspeed icon in the OpenShift Container Platform web console. The default value is`false`. Set this field to`true` to hide the icon from users, such as those who do not have access to the OpenShift Lightspeed API.

#### 1.2.1. Configuring custom TLS certificatesCopy link

Use the OpenShift Container Platform web console to configure custom TLS certificates for secure communication between OpenShift Lightspeed and your service endpoints.

Prerequisites

- Click Create.
- You have logged in to the OpenShift Container Platform web console as a user with the`cluster-admin` role. As another option, you have logged in to a user account that has permission to create or edit the`OLSConfig` custom resource (CR).
- You have a large language model (LLM) provider.
- You have installed the OpenShift Lightspeed Operator.
- You have created the credentials secret and the`OLSconfig` CR.

Procedure

Update the`OLSconfig` CR to contain the file that has the TLS secret.

```yaml
apiVersion: ols.openshift.io/v1alpha1
kind: OLSConfig
metadata:
  name: cluster
spec:
  ols:
    tlsConfig:
      keyCertSecretRef:
        name: <lightspeed_tls>
---
apiVersion: v1
data:
  tls.crt: LS0tLS1CRUd...
  tls.key: LS0tLS1CRUd...
kind: Secret
metadata:
  name: <lightspeed_tls>
  namespace: <openshift_lightspeed>
```

- `spec.ols.tlsConfig.keyCertSecretRef.name` specifies the secret that has the`tls.crt` and`tls.key` file.
- `apiVersion.data.tls` specifies that the name of the certificate must be`tls.crt` and the name of the key must be`tls.key`.

Verification

Verify that a new pod exists in the`lightspeed-app-server` deployment by running the following command:

```shell-session
$ oc get pod -n openshift-lightspeed
```

1. Click Save.

### 1.3. Creating the credentials secret by using the CLICopy link

Use the command line interface to store the API token that OpenShift Lightspeed uses to authenticate with the large language model (LLM) provider.

Alternatively, Microsoft Azure also supports authentication using Microsoft Entra ID.

Prerequisites

- You have access to the OpenShift CLI (`oc`) as a user with the`cluster-admin` role. Alternatively, you are logged in to a user account that has permission to create a secret to store the Provider tokens.
- You have installed the OpenShift Lightspeed Operator.

Procedure

Create a YAML file that has the credential secret for the LLM provider that you are using.

Note

The YAML parameter is always`apitoken` regardless of what the LLM provider calls the access details.

Use the following example to create the Secret to provide OpenShift Lightspeed with the OpenAI API key.

```yaml
apiVersion: v1
kind: Secret
metadata:
  name: openai
  namespace: openshift-lightspeed
type: Opaque
stringData:
  apitoken: <api_token>
```

- `stringData.apitoken` represents the API token. The token must be`base64` encoded when stored in a secret.

Use the following example to create the Secret to provide OpenShift Lightspeed with the Red Hat OpenShift AI key.

```yaml
apiVersion: v1
kind: Secret
metadata:
  name: rhoai-api-keys
  namespace: openshift-lightspeed
type: Opaque
stringData:
  apitoken: <api_token>
```

- `stringData.apitoken` represents the API token. The token must be`base64` encoded when stored in a secret.

Use the following example to create the Secret to provide OpenShift Lightspeed with the IBM watsonx key.

```yaml
apiVersion: v1
kind: Secret
metadata:
  name: watsonx-api-keys
  namespace: openshift-lightspeed
type: Opaque
stringData:
  apitoken: <api_token>
```

- `stringData.apitoken` represents the API token. The token must be`base64` encoded when stored in a secret.

Use the following example to create the Secret to provide OpenShift Lightspeed with the Microsoft Azure OpenAI key.

```yaml
apiVersion: v1
kind: Secret
metadata:
  name: azure-api-keys
  namespace: openshift-lightspeed
type: Opaque
stringData:
  apitoken: <api_token>
```

- `stringData.apitoken` represents the API token. The token must be`base64` encoded when stored in a secret.

Optional: As another option with Microsoft Azure OpenAI you can use Microsoft Entra ID to authenticate your LLM provider. Microsoft Entra ID users must configure the required roles for their Microsoft Azure OpenAI resource. For more information, see the official Microsoft [Cognitive Services OpenAI Contributor](https://learn.microsoft.com/en-us/azure/ai-services/openai/how-to/role-based-access-control#cognitive-services-openai-contributor)(Microsoft Azure OpenAI Service documentation). Use the following example to authenticate by using Microsoft Entra ID.

```yaml
apiVersion: v1
data:
  client_id: <base64_encoded_client_id>
  client_secret: <base64_encoded_client_secret>
  tenant_id: <base64_encoded_tenant_id>
kind: Secret
metadata:
  name: azure-api-keys
  namespace: openshift-lightspeed
type: Opaque
```

- `data.apitoken` represents the API token. The token must be`base64` encoded when stored in a secret.

Create the Secret by running the following command:

```shell-session
$ oc create -f /path/to/secret.yaml
```

### 1.4. Creating the OpenShift Lightspeed custom resource file by using the CLICopy link

Use the command line interface to create the custom resource (CR) file required to deploy OpenShift Lightspeed.

The specific content of the CR file is unique for each large language model (LLM) provider. Choose the configuration file for the LLM provider that you are using.

Prerequisites

- You have access to the OpenShift CLI (`oc`) and have logged in as a user with the`cluster-admin` role. As another option, you have logged in to a user account that has permission to create a cluster-scoped CR file.
- You have an LLM provider available for use with the OpenShift Lightspeed Service.
- You have installed the OpenShift Lightspeed Operator.

Procedure

Create an`OLSConfig` file that has the YAML content for the LLM provider you use.

Use the following example to create the`OLSConfig` CR to configure OpenShift Lightspeed with your OpenAI provider:

```yaml
apiVersion: ols.openshift.io/v1alpha1
kind: OLSConfig
metadata:
  name: cluster
spec:
  llm:
    providers:
      - name: myOpenai
        type: openai
        credentialsSecretRef:
          name: credentials
        url: https://api.openai.com/v1
        models:
          - name: <model_name>
  ols:
    hideIcon: true
    defaultModel: <model_name>
    defaultProvider: myOpenai
```

- `spec.ols.hideIcon` specifies whether to hide the OpenShift Lightspeed icon in the OpenShift Container Platform web console. The default value is`false`. Set this field to`true` to hide the icon from users, such as those who do not have access to the OpenShift Lightspeed API.

Use the following example to create the`OLSConfig` CR to configure OpenShift Lightspeed with your Red Hat Enterprise Linux AI provider:

```yaml
apiVersion: ols.openshift.io/v1alpha1
kind: OLSConfig
metadata:
  name: cluster
spec:
  llm:
    providers:
    - credentialsSecretRef:
        name: rhelai-api-keys
      models:
      - name: models/<model_name>
      name: rhelai
      type: rhelai_vllm
      url: <url>
  ols:
    hideIcon: true
    defaultProvider: rhelai
    defaultModel: models/<model_name>
```

- `spec.llm.providers.credentialsSecretRef.name` specifies the name of the Secret that has the API key for the provider. By default, the Red Hat Enterprise Linux AI API key requires a token as part of the request. If your Red Hat Enterprise Linux AI endpoint does not require a token, you must still set the token value to any valid string for the request to authenticate.
- `spec.llm.providers.url` specifies the URL endpoint for the provider. The URL must end with`v1` to be valid. For example, [https://3.23.103.8:8000/v1](https://3.23.103.8:8000/v1).
- `spec.ols.hideIcon` specifies whether to hide the OpenShift Lightspeed icon in the OpenShift Container Platform web console. The default value is`false`. Set this field to`true` to hide the icon from users, such as those who do not have access to the OpenShift Lightspeed API.

Use the following example to create the`OLSConfig` CR to configure OpenShift Lightspeed with your Red Hat OpenShift AI provider:

```yaml
apiVersion: ols.openshift.io/v1alpha1
kind: OLSConfig
metadata:
  name: cluster
spec:
  llm:
    providers:
    - credentialsSecretRef:
        name: rhoai-api-keys
      models:
      - name: <model_name>
      name: red_hat_openshift_ai
      type: rhoai_vllm
      url: <url>
  ols:
    hideIcon: true
    defaultProvider: red_hat_openshift_ai
    defaultModel: <model_name>
```

- `spec.llm.providers.credentialsSecretRef.name` specifies the name of the Secret that has the API key for the provider. By default, the Red Hat OpenShift AI API key requires a token as part of the request. If your Red Hat OpenShift AI endpoint does not require a token, you must still set the token value to any valid string for the request to authenticate.
- `spec.llm.providers.url` specifies the URL endpoint for the provider. The URL must end with`v1` to be valid. For example,`https://..com:443/v1`.
- `spec.ols.hideIcon` specifies whether to hide the OpenShift Lightspeed icon in the OpenShift Container Platform web console. The default value is`false`. Set this field to`true` to hide the icon from users, such as those who do not have access to the OpenShift Lightspeed API.

Use the following example to create the`OLSConfig` CR to configure OpenShift Lightspeed with your Microsoft Azure OpenAI provider:

```yaml
apiVersion: ols.openshift.io/v1alpha1
kind: OLSConfig
metadata:
  name: cluster
spec:
  llm:
    providers:
      - credentialsSecretRef:
          name: azure-api-keys
        apiVersion: <api_version_for_azure_model>
        deploymentName: <azure_ai_deployment_name>
        models:
        - name: <model_name>
        name: myAzure
        type: azure_openai
        url: <azure_ai_deployment_url>
  ols:
    hideIcon: true
    defaultModel: <model_name>
    defaultProvider: myAzure
```

- `spec.ols.hideIcon` specifies whether to hide the OpenShift Lightspeed icon in the OpenShift Container Platform web console. The default value is`false`. Set this field to`true` to hide the icon from users, such as those who do not have access to the OpenShift Lightspeed API.

Use the following example to create the`OLSConfig` CR to configure OpenShift Lightspeed with your IBM watsonx provider:

```yaml
apiVersion: ols.openshift.io/v1alpha1
kind: OLSConfig
metadata:
  name: cluster
spec:
  llm:
    providers:
      - name: myWatsonx
        type: watsonx
        credentialsSecretRef:
          name: watsonx-api-keys
        url: <ibm_watsonx_deployment_name>
        projectID: <ibm_watsonx_project_id>
        models:
          - name: ibm/<model_name>
  ols:
    hideIcon: true
    defaultModel: ibm/<model_name>
    defaultProvider: myWatsonx
```

- `spec.ols.hideIcon` specifies whether to hide the OpenShift Lightspeed icon in the OpenShift Container Platform web console. The default value is`false`. Set this field to`true` to hide the icon from users, such as those who do not have access to the OpenShift Lightspeed API.

Run the following command so that the Operator deploys OpenShift Lightspeed using the information in the YAML configuration file.

```shell-session
$ oc create -f /path/to/config-cr.yaml
```

#### 1.4.1. Support for trusted-ca certificates and LLM providersCopy link

Use the OpenShift Container Platform web console to configure custom TLS certificates for secure communication between OpenShift Lightspeed and your service endpoints.

The OpenShift Lightspeed Service supports adding trusted-ca certificates for the following LLM providers:

- Red Hat Enterprise Linux AI vLLM
- Red Hat OpenShift AI vLLM
- OpenAI
- Microsoft Azure OpenAI

To add a trusted-ca certificate you must create a`ConfigMap` object that has the certificates. Then, add the name of the object to the`OLSConfig` custom resource (CR) file as shown in the following example:

```yaml
ols:
  additionalCAConfigMapRef:
    name: <config_map_name>
```

#### 1.4.2. Configuring OpenShift Lightspeed with a trusted CA certificate for the LLMCopy link

Use the OpenShift Container Platform web console to configure a trusted CA certificate for secure communication between OpenShift Lightspeed and your large language model (LLM) provider.

Note

If the LLM provider you are using requires a trusted-ca certificate to authenticate the OpenShift Lightspeed Service you must perform this procedure. If the LLM provider does not require a trusted-ca certificate to authenticate the Service, you should skip this procedure.

Procedure

Create a`ConfigMap` object called`trusted-certs` by running the following command:

```shell-session
$ oc create configmap trusted-certs --from-file=caCertFileName --namespace openshift-lightspeed
```

This command returns an output similar to the following example:

```shell-session
kind: ConfigMap
apiVersion: v1
metadata:
  name: trusted-certs
  namespace: openshift-lightspeed
data:
  caCertFileName: |
    -----BEGIN CERTIFICATE-----
    .
    .
    .
    -----END CERTIFICATE-----
```

- `data.caCertFileName` specifies the CA certificates required to connect to your LLM provider. You can include one or more certificates within this block to ensure secure communication.

Update the`OLSConfig` custom resource (CR) file to include the name of the`ConfigMap` object you just created. The following example uses Red Hat Enterprise Linux AI as the LLM provider.

```yaml
apiVersion: ols.openshift.io/v1alpha1
kind: OLSConfig
metadata:
  name: cluster
spec:
  ols:
    defaultProvider: rhelai
    defaultModel: models/<model_name>
    additionalCAConfigMapRef:
      name: trusted-certs
```

- `spec.ols.additionalCAConfigMapRef.name` specifies the name of`ConfigMap` object.

Create the custom CR by running the following command:

```shell-session
$ oc apply -f <olfconfig_cr_filename>
```

### 1.5. Verifying the OpenShift Lightspeed deploymentCopy link

Use the OpenShift Container Platform web console to verify that the OpenShift Lightspeed Service is running and deployed.

Important

Starting with OpenShift Container Platform 4.19, the perspectives in the web console are unified. The Developer perspective is no longer enabled by default.

All users can interact with all OpenShift Container Platform web console features. However, if you are not the cluster owner, you might need to request permission to certain features from the cluster owner.

You can still enable the Developer perspective. On the Getting Started pane in the web console, you can take a tour of the console, find information on setting up your cluster, view a quick start for enabling the Developer perspective, and follow links to explore new features and capabilities.

Prerequisites

- You have logged in to the OpenShift Container Platform web console as a user with the`cluster-admin` role.
- You have access to the OpenShift CLI (`oc`).
- You have installed the OpenShift Lightspeed Operator.
- You have created the credentials secret and the`OLSConfig` Custom Resource configuration file.

Procedure

In the OpenShift Container Platform web console, click the Project drop-down list.

Note

For OpenShift Container Platform 4.18 and earlier, select the Developer perspective from the drop-down list at the top of the pane to access the Project drop-down list.

Verify that the OpenShift Lightspeed is ready by running the following command:

```shell-session
$ oc logs deployment/lightspeed-app-server -c lightspeed-service-api -n openshift-lightspeed | grep Uvicorn
```

Example output

```shell-session
INFO: 	Uvicorn running on https://0.0.0.0:8443 (Press CTRL+C to quit)
```

1. Enable the toggle switch to show default projects.
2. Select openshift-lightspeed from the list.

### 1.6. About OpenShift Lightspeed and role-based access control (RBAC)Copy link

Use role-based access control (RBAC) to manage system security by assigning permissions to specific roles rather than individual users.

OpenShift Lightspeed RBAC is binary. By default, not all cluster users have access to the OpenShift Lightspeed interface. Only users with administrative rights can grant access. All users of an OpenShift cluster with OpenShift Lightspeed installed can see the OpenShift Lightspeed button; however, only users with permissions can submit questions to OpenShift Lightspeed.

If you want to evaluate the RBAC features of OpenShift Lightspeed, your cluster will need users other than the`kubeadmin` account. The`kubeadmin` account always has access to OpenShift Lightspeed.

#### 1.6.1. Granting a user access by using the CLICopy link

Grant OpenShift Lightspeed permissions to an individual user by running a single`oc adm` command to apply the query-access role immediately.

Prerequisites

- You have logged in to the OpenShift Container Platform web console as a user with the`cluster-admin` role. As another option, you have logged in as a user with the ability to grant permissions.
- You have deployed the OpenShift Lightspeed service.
- You have access to the OpenShift CLI (`oc`).

Procedure

Grant the`lightspeed-operator-query-access` role to a user. Enter the actual user name in place of` ` when running the following command:

```shell-session
$ oc adm policy add-cluster-role-to-user \
    lightspeed-operator-query-access <user_name>
```

Verification

Verify that the user has been successfully added to the cluster role binding by running the following command:

```shell-session
$ oc get clusterrolebinding lightspeed-operator-query-access
```

#### 1.6.2. Granting a user access by using a YAML configuration fileCopy link

Grant OpenShift Lightspeed permissions to an individual user by creating and applying a`ClusterRoleBinding` YAML file for reproducible access management.

Prerequisites

- You have logged in to the OpenShift Container Platform web console as a user with the`cluster-admin` role. As another option, you have logged in as a user with the ability to grant permissions.
- You have deployed the OpenShift Lightspeed service.
- You have access to the OpenShift CLI (`oc`).

Procedure

Generate a YAML configuration file for the cluster role binding. Enter the actual user name in place of` ` when running the following command:

```shell-session
$ oc adm policy add-cluster-role-to-user lightspeed-operator-query-access <user_name> -o yaml --dry-run
```

Apply the generated configuration file to the cluster:

```shell-session
$ oc apply -f ols-user-access.yaml
```

Verification

Verify the creation of the`ClusterRoleBinding` by running the following command:

```shell-session
$ oc get clusterrolebinding lightspeed-operator-query-access
```

Inspect the YAML configuration and ensure that it lists the correct user in the`subjects` section by running the following command:

```shell-session
$ oc get clusterrolebinding lightspeed-operator-query-access -o yaml
```

This command returns an output similar to the following example:

```yaml
apiVersion: rbac.authorization.k8s.io/v1
kind: ClusterRoleBinding
metadata:
  creationTimestamp: null
  name: lightspeed-operator-query-access
roleRef:
  apiGroup: rbac.authorization.k8s.io
  kind: ClusterRole
  name: lightspeed-operator-query-access
subjects:
- apiGroup: rbac.authorization.k8s.io
  kind: User
  name: <user_name>
```

#### 1.6.3. Granting a user group access by using the CLICopy link

Enable a user group to use the OpenShift Lightspeed Service by running a single command to apply cluster permissions immediately.

If your cluster has more advanced identity management configured, including user groups, you can grant all users of a specific group access to the OpenShift Lightspeed Service.

Prerequisites

- You have logged in to the OpenShift Container Platform web console as a user with the`cluster-admin` role. As another option, you have logged in as a user with the ability to grant permissions.
- You have deployed the OpenShift Lightspeed Service.
- You have access to the OpenShift CLI (oc).

Procedure

Grant the`lightspeed-operator-query-access` role to your user group by running the following command. Replace` ` with the actual name of the user group in your cluster.

```shell-session
$ oc adm policy add-cluster-role-to-group \
lightspeed-operator-query-access <group_name>
```

Optional: Verify that the role binding contains the user group by running the following command:

```shell-session
$ oc get clusterrolebinding lightspeed-operator-query-access -o wide
```

#### 1.6.4. Granting a user group access by using a YAML configuration fileCopy link

Grant multiple users access to the OpenShift Lightspeed Service by applying a YAML configuration file.

If your cluster has more advanced identity management configured, including user groups, you can grant all users of a specific group access to the OpenShift Lightspeed Service.

Prerequisites

- You have logged in to the OpenShift Container Platform web console as a user with the`cluster-admin` role. As another option, you have logged in as a user with the ability to grant permissions.
- You have deployed the OpenShift Lightspeed Service.
- You have access to the OpenShift CLI (oc).

Procedure

Generate the YAML configuration by running the following command:

```shell-session
$ oc adm policy add-cluster-role-to-group lightspeed-operator-query-access <group_name> -o yaml --dry-run > access-policy.yaml
```

Open the`access-policy.yaml` file and verify the`subjects` section contains the correct group name:

```yaml
apiVersion: rbac.authorization.k8s.io/v1
kind: ClusterRoleBinding
metadata:
  creationTimestamp: null
  name: lightspeed-operator-query-access
roleRef:
  apiGroup: rbac.authorization.k8s.io
  kind: ClusterRole
  name: lightspeed-operator-query-access
subjects:
- apiGroup: rbac.authorization.k8s.io
  kind: Group
  name: <user_group>
```

- `subjects.name` specifies the name of the user group you are granting access to.

Apply the configuration file to the cluster by running the following command:

```shell-session
$ oc create -f access-policy.yaml
```

### 1.7. Filtering and redacting informationCopy link

Configure sensitive data filtering in OpenShift Lightspeed to redact private information before sending it to the large language model (LLM) provider.

Note

You should test your regular expressions against sample data to confirm that they identify the information you want to filter or redact, and that they do not identify information you want to send to the LLM. There are several third-party websites that you can use to test your regular expressions. When using third-party sites, you should practice caution with regards to sharing your private data. As another option, you can test the regular expressions locally using Python. In Python, it is possible to design very computationally-expensive regular expressions. Using several complex expressions as query filters can adversely impact the performance of OpenShift Lightspeed.

This example shows how to update the`OLSConfig` custom resource (CR) file to redact IP addresses, but you can also filter or redact other types of sensitive information.

Note

If you configure filtering or redacting in the`OLSConfig` CR file, and you configure`introspectionEnabled` to enable a Model Context Protocol (MCP) server, any content that the tools return is not filtered and is visible to the LLM.

Prerequisites

- You have logged in to the OpenShift Container Platform web console as a user with the`cluster-admin` role.
- You have access to the OpenShift CLI (`oc`).
- You have installed the OpenShift Lightspeed Operator and deployed the OpenShift Lightspeed service.

Procedure

Update the`OLSConfig` CR file and create an entry for each regular expression to filter. The following example redacts IP addresses:

```yaml
spec:
  ols:
    queryFilters:
      - name: ip-address
        pattern: '((25[0-5]|(2[0-4]|1\d|[1-9]|)\d)\.?\b){4}'
        replaceWith: <IP_ADDRESS>
```

Run the following command to apply the modified OpenShift Lightspeed custom configuration:

```shell-session
$ oc apply -f OLSConfig.yaml
```

### 1.8. About the BYO Knowledge toolCopy link

Enhance OpenShift Lightspeed responses by using the BYO Knowledge tool to create a retrieval-augmented generation (RAG) database that includes documentation specific to your organization.

When you create a RAG database, you customize the OpenShift Lightspeed service for your environment. For example, a network administrator can use a standard operating procedure (SOP) to provision an OpenShift Container Platform cluster. Then, the network administrator can use the BYO Knowledge tool to enhance the knowledge available to the LLM by including information from the SOP.

To bring your own knowledge to an LLM, you complete the following steps:

Access the OpenShift Lightspeed virtual assistant and submit a question associated with the custom knowledge that you made available to the LLM.

Note

When you use the BYO Knowledge tool, you provide documents directly to the LLM provider.

- Create the custom content in Markdown format.
- Use the BYO Knowledge tool to package the content as a container image.
- Push the container image to an image registry, such as`quay.io`.
- Update the`OLSConfig` custom resource file to list the image that you pushed to the image registry.

OpenShift Lightspeed supports automatic updates of BYO Knowledge images that use floating tags, such as`latest`. If over time a BYO Knowledge image tag points to different underlying images, OpenShift Lightspeed detects those changes and updates the corresponding BYO Knowledge database accordingly. This feature uses OpenShift`ImageStream` objects. OpenShift Container Platform clusters check for updates to`ImageStream` objects every 15 minutes.

#### 1.8.1. About document title and URLCopy link

Display the source titles and URLs OpenShift Lightspeed uses to verify the accuracy of generated responses and access the original documentation for additional context.

In the retrieval-augmented generation (RAG) database, titles and URLs accompany documents as metadata. The BYO Knowledge tool obtains the title and URL attributes from metadata if they reside in the Markdown files that the tool processes.

```plaintext
---
title: "Introduction to Layers {#gimp-concepts-layers}"
url: "https://docs.gimp.org/3.0/en/gimp-using-layers.html"
---

# Introduction to Layers
...
```

If a Markdown file does not have metadata with the`title` and`url` attributes, the first top-level Markdown heading, for example`# Introduction to Layers`, becomes the title and the file path becomes the URL.

#### 1.8.2. Providing custom knowledge to the LLMCopy link

Customize the information available to the large language model (LLM) by providing access to a container image that resides in a remote image registry.

The container image that the tool generates contains a custom RAG database. The RAG database provides additional information to the LLM.

The examples in this procedure use`quay.io` as the remote container image registry, and the path for the custom image is`quay.io/ /my-byok-image:latest`.

Important

The BYO Knowledge tool is a Technology Preview feature only. Technology Preview features are not supported with Red Hat production service level agreements (SLAs) and might not be functionally complete. Red Hat does not recommend using them in production. These features provide early access to upcoming product features, enabling customers to test functionality and provide feedback during the development process.

For more information about the support scope of Red Hat Technology Preview features, see [Technology Preview Features Support Scope](https://access.redhat.com/support/offerings/techpreview/).

Prerequisites

- You have logged in to the OpenShift Container Platform web console as a user account that has permission to create a cluster-scoped custom resource (CR) file, such as a user with the`cluster-admin` role.
- You have an LLM provider available for use with the OpenShift Lightspeed Service.
- You have installed the OpenShift Lightspeed Operator.
- Your custom information consists of Markdown files with`.md` extensions. The tool does not support other file formats.
- You have logged in to`registry.redhat.io` by using Podman.
- You have an account for a container image registry, such as`quay.io`.

Procedure

Specify the location of the directory with the Markdown files for the retrieval-augmented generation (RAG) database and the path for the image that the BYO Knowledge tool generates by running the following command:

```shell-session
$ podman run -it --rm --device=/dev/fuse \
  -v $XDG_RUNTIME_DIR/containers/auth.json:/run/user/0/containers/auth.json:Z \
  -v <dir_tree_with_markdown_files>:/markdown:Z \
  -v <dir_for_image_tar>:/output:Z \
  registry.redhat.io/openshift-lightspeed-tech-preview/lightspeed-rag-tool-rhel9:latest
```

Load the container image that the BYO Knowledge tool generated by running the following command:

```shell-session
$ podman load < <directory_for_image_tar>/<my-byok-image.tar>
```

Display the Podman images that are on your local computer by running the following command:

```shell-session
$ podman images
```

This command returns an output similar to the following example:

```shell-session
REPOSITORY                            TAG                IMAGE ID      CREATED       SIZE
localhost/my-byok-image               latest             be7d1770bf10  1 minute  ago    2.37 GB
...
```

Tag the local image with a name and destination so that you can push the image to the container image registry by running the following command:

```shell-session
$ podman tag localhost/my-byok-image:latest quay.io/<username>/my-byok-image:latest
```

Push the local container image to the container image registry by running the following command:

```shell-session
$ podman push quay.io/<username>/my-byok-image:latest
```

Update the`OLSconfig` CR to deploy the newly created RAG database alongside the existing one:

Insert the`spec.ols.rag` YAML code:

```yaml
apiVersion: ols.openshift.io/v1alpha1
kind: OLSConfig
metadata:
  name: cluster
spec:
  ols:
    rag:
      - image: quay.io/<username>/my-byok-image:latest
```

- `spec.ols.rag.image` specifies the tag for the image that you pushed to the image registry so that the OpenShift Lightspeed Operator can access the custom content. The OpenShift Lightspeed Operator can work with more than one RAG database that you create.

Optional: Specify pull secrets in the`OLSSpec` section of the`OLSConfig` CR file. These secrets provide authentication for remote registries. Use this optional field if your RAG BYO Knowledge images reside in a private registry that the standard cluster-wide pull secret cannot access.

```yaml
apiVersion: ols.openshift.io/v1alpha1
kind: OLSConfig
metadata:
  name: cluster
  namespace: openshift-lightspeed
spec:
  llm:
    providers:
...
  ols:
    imagePullSecrets:
      - name: <my_pull_secret_1>
      - name: <my_pull_secret_2>
```

- `spec.ols.imagePullSecrets` defines the pull secrets that OpenShift Lightspeed uses only when you specify BYO Knowledge RAG images. Instead of linking a specific secret to a specific image, the system maintains a general list of pull secrets. For every BYO Knowledge image, the system tries each pull secret in sequential order until it achieves a successful authentication.

Verification

Access the OpenShift Lightspeed virtual assistant and submit a question associated with the custom content that you provided to the LLM.

The OpenShift Lightspeed virtual assistant generates a response based on the custom content.

- Click Save.

#### 1.8.3. Disabling the OpenShift Container Platform documentation retrieval-augmented generation (RAG) databaseCopy link

Disable the default OpenShift Container Platform documentation in the`OLSConfig` custom resource (CR) to prevent the service from using the built-in database that has the OpenShift Container Platform documentation.

Then, the only retrieval-augmented generation (RAG) databases OpenShift Lightspeed uses are the ones that you provide to the service by using the BYO Knowledge feature.

Prerequisites

- You have logged in to the OpenShift Container Platform web console as a user account with permission to create a cluster-scoped CR file, such as a user with the`cluster-admin` role.
- You have installed the OpenShift Lightspeed Operator.
- You have configured the large language model provider.
- You have configured the`OLSConfig` CR file, which automatically deploys the OpenShift Lightspeed Service.
- You have created a RAG database that contains the content you want to use, as described in "Providing custom knowledge to the LLM".

Procedure

Insert the`spec.ols.byokRAGOnly` YAML code.

```yaml
apiVersion: ols.openshift.io/v1alpha1
kind: OLSConfig
metadata:
  name: cluster
spec:
  ols:
    byokRAGOnly: true
```

- `spec.ols.byokRAGOnly` specifies if the Service limits responses by using only the information found in the local documentation that you provide. Specify`true` so that OpenShift Lightspeed only uses RAG databases that you create by using the BYO Knowledge feature. When`true`, OpenShift Lightspeed does not use the default RAG database that contains the OpenShift Container Platform documentation.

### 1.9. About cluster interactionCopy link

Enable the cluster interaction feature to supply the large language model (LLM) with additional context about your OpenShift Container Platform cluster.

OpenShift Lightspeed uses a large language model (LLM) to answer your questions. When you share details about your cluster objects, the LLM can give specific answers for your environment.

The Model Context Protocol (MCP) is an open protocol. It creates a standard way for applications to give context to an LLM. Using the protocol, an MCP server offers a standardized way for an LLM to increase context by requesting and receiving real-time information from external resources.

Note

If you configure filtering or redacting in the`OLSConfig` CR file, and you configure`introspectionEnabled` to enable a Model Context Protocol (MCP) server, any content that the tools return is not filtered and is visible to the LLM.

When you enable cluster interaction, the OpenShift Lightspeed Operator installs an MCP server. The MCP server provides the OpenShift Lightspeed Service with access to the OpenShift API. Through this access, the Service performs read operations to gather more context for the LLM, enabling the Service to generate answers to questions about the Kubernetes objects that reside in your OpenShift cluster.

Note

The ability of OpenShift Lightspeed to choose and use a tool effectively is very sensitive to the large language (LLM) model. In general, a larger model with more parameters performs better, and the best performance comes from an extremely large frontier model that represents the latest AI capabilities. When using a small model, you might notice poor performance in tool selection or other aspects of cluster interaction.

You must enable tool calling in the LLM provider to activate the cluster interaction feature in the OpenShift Lightspeed Service.

Note

Enabling tool calling can dramatically increase token usage. When you use a public model provider, an increase in token usage usually increases billing costs.

#### 1.9.1. Enabling cluster interactionCopy link

Enable the cluster interaction feature by modifying the`OLSConfig` custom resource (CR) to give OpenShift Lightspeed cluster-specific context.

Important

The cluster interaction feature is a Technology Preview feature only. Technology Preview features are not supported with Red Hat production service level agreements (SLAs) and might not be functionally complete. Red Hat does not recommend using them in production. These features provide early access to upcoming product features, enabling customers to test functionality and provide feedback during the development process.

For more information about the support scope of Red Hat Technology Preview features, see [Technology Preview Features Support Scope](https://access.redhat.com/support/offerings/techpreview/).

Prerequisites

- You have logged in to the OpenShift Container Platform web console as a user with the`cluster-admin` role. As another option, you have logged in to a user account that has permission to create a cluster-scoped custom resource.
- You have configured the large language model (LLM) provider.
- You have installed the OpenShift Lightspeed Operator.

Procedure

Set the`spec.ols.introspectionEnabled` parameter to`true` to enable cluster interaction:

```yaml
apiVersion: ols.openshift.io/v1alpha1
kind: OLSConfig
metadata:
  name: cluster
spec:
  ols:
    introspectionEnabled: true
```

1. In the OpenShift Container Platform web console, click Operators → Installed Operators.
2. Click OpenShift Lightspeed Operator.
3. Click OLSConfig, then click the`cluster` configuration instance in the list.
4. Click the YAML tab.
5. Click Save.

Verification

Access the OpenShift Lightspeed virtual assistant and submit a question associated with your cluster.

The OpenShift Lightspeed virtual assistant generates a highly refined response specific to your environment.

#### 1.9.2. Enabling a custom MCP serverCopy link

Add an additional Model Context Protocol (MCP) server that interfaces with a tool in your environment so that the large language model (LLM) uses the tool to generate answers to your questions.

Important

The MCP server feature is a Technology Preview feature only. Technology Preview features are not supported with Red Hat production service level agreements (SLAs) and might not be functionally complete. Red Hat does not recommend using them in production. These features provide early access to upcoming product features, enabling customers to test functionality and provide feedback during the development process.

For more information about the support scope of Red Hat Technology Preview features, see [Technology Preview Features Support Scope](https://access.redhat.com/support/offerings/techpreview/).

Prerequisites

- You have installed the OpenShift Lightspeed Operator.
- You have configured a large language model provider.
- You have configured the`OLSConfig` CR file, which automatically deploys the OpenShift Lightspeed Service.

Procedure

Open the OpenShift Lightspeed`OLSconfig` custom resource (CR) file by running the following command:

```shell-session
$ oc edit olsconfig cluster
```

Add`MCPServer` to the`spec.featureGates` specification file and include the MCP server information.

```yaml
apiVersion: ols.openshift.io/v1alpha1
kind: OLSConfig
metadata:
  name: cluster
spec:
  featureGates:
    - MCPServer
  mcpServers:
  - name: mcp-server-1
    url: https://mcp.example.com
    timeout: 30
    headers:
      - name: Authorization
        valueFrom:
         type: kubernetes
  - name: mcp-server-2
    url: https://mcp.example.com
    timeout: 30
    headers:
      - name: X-Special
        valueFrom:
          type: secret
          secretRef:
            name: <secret_name>
```

- `spec.featureGates` specifies the MCP server functionality on the OpenShift Lightspeed pod.
- `spec.mcpServers.name` specifies the name of the MCP server.
- `spec.mcpServers.url` specifies the URL path that the MCP server uses to communicate
- `spec.mcpServers.timeout` specifies the time that the MCP server has to respond to a query. If the Service does not receive a response within the time specified, the connection times out. In this example, the timeout is 30 seconds.
- `spec.mcpServers.headers` specifies MCP headers as an array of structured objects, which are required for MCP server authentication.
- `spec.mcpServers.headers.name` specifies the name of the header that gets sent to the MCP server.
- `spec.mcpServers.headers.valueFrom.type` specify the string`kubernetes` to provide the user’s bearer token
- `spec.mcpServers.headers.valueFrom.secretRef.name` specifies the name of the secret that contains the header value. Ensure that the secret has the key name`header`.

Click Save.

The save operation saves the file and applies the changes so that the MCP server is available to the OpenShift Lightspeed service.

### 1.10. Tokens and token quota limitsCopy link

Token quotas manage the amount of text that the OpenShift Lightspeed Service exchanges with a large language model (LLM). These limits control costs and ensure all users get fair access to resources.

The Service measures the text it exchanges with the LLM in tokens. A token is a small unit of text, ranging from a single character to a full word. Every chat between the Service and the LLM uses tokens.

Token quotas limit how many tokens you can use within a specific time frame. You can define token quota limits for OpenShift clusters or OpenShift user accounts.

#### 1.10.1. Activating token quota limitsCopy link

Activate token quota limits for OpenShift Lightspeed by creating a`ConfigMap` resource and referencing it in the`OLSConfig` custom resource.

The OpenShift Lightspeed pod mounts the`ConfigMap` resource as a volume, enabling access to the file stored within it. The`OLSConfig` custom resource (CR) references the`ConfigMap` resource to obtain the quota limit information.

Prerequisites

- You have installed the OpenShift Lightspeed Operator.
- You have configured a large language model provider.
- The OpenShift Lightspeed Service has access to a PostgreSQL database.

Procedure

Open the OpenShift Lightspeed`OLSconfig` CR file by running the following command:

```shell-session
$ oc edit olsconfig cluster
```

Update the`spec.ols.quotaHandlersConfig` specification to include token quota limit information.

```yaml
apiVersion: ols.openshift.io/v1alpha1
kind: OLSConfig
metadata:
  name: cluster
spec:
  ols:
    quotaHandlersConfig:
      limitersConfig:
      - name: user_limits
        type: user_limiter
        initialQuota: 100000
        quotaIncrease: 1000
        period: 30 days
      - name: cluster_limits
        type: cluster_limiter
        initialQuota: 1000000
        quotaIncrease: 100000
        period: 30 days
```

User quota configuration fields

- `spec.ols.quotaHandlersConfig.limitersConfig.name` specifies the token limit for the user account.
- `spec.ols.quotaHandlersConfig.limitersConfig.initialQuota` specifies a token quota limit of`100,000` for each user over the time period specified in the`period` field.
- `spec.ols.quotaHandlersConfig.limitersConfig.quotaIncrease` increases the token quota limit for the user by`1,000` at the end of the time period specified in the`period` field.

Cluster quota configuration fields

- `spec.ols.quotaHandlersConfig.limitersConfig.name` specifies the token limit for a cluster.
- `spec.ols.quotaHandlersConfig.limitersConfig.initialQuota` specifies a token quota limit of`1,000,000` for each cluster over the time period specified in the`period` field.
- `spec.ols.quotaHandlersConfig.limitersConfig.quotaIncrease` increases the token quota limit for the cluster by 100,000 at the end of the time period specified in the`period` field.

Shared configuration field

- `spec.ols.quotaHandlersConfig.limitersConfig.period` defines the amount of time that the scheduler waits before the period resets or the quota limit increases.

Click Save.

The save operation saves the file and applies the changes to activate the token quota limits.

### 1.11. About OpenShift Lightspeed and PostgreSQL persistenceCopy link

PostgreSQL persistence ensures that OpenShift Lightspeed conversation history and quota usage data remain available across pod restarts and rescheduling events.

By default, the Service disables PostgreSQL persistence.

To enable the functionality, add the`spec.ols.storage` specification to the`OLSConfig` custom resource (CR).

#### 1.11.1. Enabling PostgreSQL persistenceCopy link

Enable PostgreSQL persistence for OpenShift Lightspeed by modifying the`OLSConfig` custom resource (CR) file.

Important

PostgreSQL persistence is a Technology Preview feature only. Technology Preview features are not supported with Red Hat production service level agreements (SLAs) and might not be functionally complete. Red Hat does not recommend using them in production. These features provide early access to upcoming product features, enabling customers to test functionality and provide feedback during the development process.

For more information about the support scope of Red Hat Technology Preview features, see [Technology Preview Features Support Scope](https://access.redhat.com/support/offerings/techpreview/).

Prerequisites

- You have logged in to the OpenShift Container Platform web console as a user account with permission to create a cluster-scoped CR file, such as a user with the`cluster-admin` role.
- You have installed the OpenShift Lightspeed Operator.
- You have configured the large language model provider.

Procedure

Insert the`spec.ols.storage` YAML code.

```yaml
apiVersion: ols.openshift.io/v1alpha1
kind: OLSConfig
metadata:
  name: cluster
  namespace: openshift-lightspeed
spec:
  llm:
    providers:
...
  ols:
    storage: {}
```

- `spec.ols.storage` specifies how the assistant stores persistent data, specifically conversation history. The class depends on the existing instances of the storage class in the cluster. If you leave the storage class empty, the assistant uses default values. The persistent volume allocated for the PostgreSQL database is 1 GB in size and uses the storage class of the default cluster. Specify empty braces for the`storage` parameter to use the default values.

#### 1.11.2. Overriding default Persistent Volume Claim (PVC) specificationsCopy link

Customize the storage capacity and storage class for the OpenShift Lightspeed database by modifying the`OLSConfig` custom resource (CR).

Prerequisites

- Click Save.
- You have logged in to the OpenShift Container Platform web console as a user account with permission to create a cluster-scoped CR, such as a user with the`cluster-admin` role.
- You have installed the OpenShift Lightspeed Operator.
- You have configured the large language model provider.
- You have access to the OpenShift CLI (`oc`).

Procedure

Update the`OLSconfig` CR to override the default PVC specifications as shown in the following example.

```yaml
apiVersion: ols.openshift.io/v1alpha1
kind: OLSConfig
metadata:
  name: cluster
  namespace: openshift-lightspeed
spec:
  ols:
    storage:
      size: 768Mi
      class: gp2-csi
```

- `spec.ols.storage.size` specifies the total storage capacity for the database. If you do not specify this parameter, the Operator uses the default size of 1 GiB.
- `spec.ols.storage.class` specifies the Storage Class for the database volume. If you do not specify this parameter, the Operator uses the default storage class setting of the cluster.

Verification

Verify that the cluster has successfully provisioned the storage by checking the status of the Persistent Volume Claim.

```shell-session
$ oc get pvc -n openshift-lightspeed
```

1. Click Save.

### 1.12. About query-based tool filteringCopy link

Query-based filtering uses a hybrid retrieval-augmented generation (RAG) system to identify the most appropriate set of tool for a user request.

When a large language model (LLM) application has access to hundreds of tools, sending the full list in one prompt slows performance and raises costs. Query-based filtering finds and retrieves the most relevant set of tools for a request in milliseconds. This pre-processing step removes selection interference, and ensures that the LLM focuses its reasoning capabilities on a small, high-quality subset of functions.

Restricting the set of tools available reduces token use, prevents model confusion, and maintains high execution accuracy. This approach transforms a massive tool library into a fast, lean interface.

#### 1.12.1. Enabling query-based tool filteringCopy link

Enable query-based tool filtering to automatically select the set of tools most relevant for your request.

Prerequisites

- You are logged in to the OpenShift Container Platform web console as a user with the`cluster-admin` role. Alternatively, you are logged in to a user account that has permission to create a cluster-scoped CR file.
- You have an LLM provider available for use with the OpenShift Lightspeed Service.
- You have installed the OpenShift Lightspeed Operator.

Procedure

Modify the`ols.Config` custom resource (CR) file to define a feature gate and the tools filtering configuration.

```yaml
apiVersion: ols.openshift.io/v1alpha1
kind: OLSConfig
metadata:
  name: cluster
spec:
  featureGates:
    - ToolFiltering
  olsConfig:
    toolFilteringConfig:
      alpha: 0.8
      topK: 10
      threshold: 0.01
```

`spec.olsConfig.toolFilteringConfig.threshold` specifies the minimum score for the tool to be considered as a candidate. Tools with a value of the score lower then the threshold value are discarded. Increasing the value discards more tools. The valid range of values is`0.01` to`0.1`.

Note

This example uses the default values for the`alpha`,`tpok` and`threshold` fields. If you use the default values in your configuration you do not have to specify them.

- `spec.featureGates.ToolFilter` specifies the feature gate.
- `spec.olsConfig.toolFilteringConfig.alpha` specifies the weight balance between semantic (RAG-based) and keyword matching. Increasing the value provides more weight to the semantic search. The valid range of values is`0` to`1`.
- `spec.olsConfig.toolFilteringConfig.topk` specifies the maximum number of tools available for the LLM.

Verification

1. Click Save.
2. Navigate to the OpenShift Container Platform web console.
3. Select Workloads → Pods and then click the pod that contains OpenShift Lightspeed.
4. Click Logs and confirm that the log displays RAG information.

## Chapter 2. OLSConfig API referenceCopy link

`OLSConfig` is the Schema for the OpenShift Lightspeed configuration object and defines the structure and parameters of the underlying deployments.

Note

The API parameter information originated in the`OLSConfig` API reference and is provided here for convenience.

### 2.1. OLSConfig API specificationsCopy link

Description Red Hat Red Hat OpenShift Lightspeed Lightspeed instance. OLSConfig is the Schema for the olsconfigs API Type`object` Required

- `spec`

Expand

`apiVersion`

`string`

APIVersion defines the versioned schema of this representation of an object. Servers should convert recognized schemas to the latest internal value, and might reject unrecognized values. More info: [https://git.k8s.io/community/contributors/devel/sig-architecture/api-conventions.md#resources](https://git.k8s.io/community/contributors/devel/sig-architecture/api-conventions.md#resources)

`kind`

`string`

Kind is a string value representing the REST resource this object represents. Servers might infer this from the endpoint the client submits requests to. Cannot be updated. In CamelCase. More info: [https://git.k8s.io/community/contributors/devel/sig-architecture/api-conventions.md#types-kinds](https://git.k8s.io/community/contributors/devel/sig-architecture/api-conventions.md#types-kinds)

`metadata`

`object`

Standard object’s metadata. More info: [https://git.k8s.io/community/contributors/devel/sig-architecture/api-conventions.md#metadata](https://git.k8s.io/community/contributors/devel/sig-architecture/api-conventions.md#metadata)

`spec`

`object`

OLSConfigSpec defines the desired state of OLSConfig

| | Property | Type | Description |
| --- | --- | --- | --- |

Show more

#### 2.1.1. .metadataCopy link

Description Standard object’s metadata. More info: [https://git.k8s.io/community/contributors/devel/sig-architecture/api-conventions.md#metadata](https://git.k8s.io/community/contributors/devel/sig-architecture/api-conventions.md#metadata) Type`object`

#### 2.1.2. .specCopy link

Description OLSConfigSpec defines the desired state of OLSConfig Type`object` Required

- `llm`
- `ols`

Expand

`featureGates`

`array (string)`

Feature Gates holds list of features to be enabled explicitly, otherwise they are disabled by default. possible values: MCPServer

`llm`

`object`

LLMSpec defines the desired state of the large language model (LLM).

`mcpServers`

`array`

MCP Server settings

`ols`

`object`

OLSSpec defines the desired state of OLS deployment.

`olsDataCollector`

`object`

OLSDataCollectorSpec defines allowed OLS data collector configuration.

| | Property | Type | Description |
| --- | --- | --- | --- |

Show more

#### 2.1.3. .spec.llmCopy link

Description LLMSpec defines the desired state of the large language model (LLM). Type`object` Required

- `providers`

Expand

`providers`

`array`

| | Property | Type | Description |
| --- | --- | --- | --- |

Show more

#### 2.1.4. .spec.llm.providersCopy link

Description Type`array`

#### 2.1.5. .spec.llm.providers[]Copy link

Description ProviderSpec defines the desired state of LLM provider. Type`object` Required

- `credentialsSecretRef`
- `models`
- `name`
- `type`

Expand

`apiVersion`

`string`

API Version for Azure OpenAI provider

`credentialsSecretRef`

`object`

The name of the secret object that stores API provider credentials

`deploymentName`

`string`

Azure OpenAI deployment name

`models`

`array`

List of models from the provider

`name`

`string`

Provider name

`projectID`

`string`

Watsonx Project ID

`tlsSecurityProfile`

`object`

TLS Security Profile used by connection to provider

`type`

`string`

Provider type

`url`

`string`

Provider API URL

| | Property | Type | Description |
| --- | --- | --- | --- |

Show more

#### 2.1.6. .spec.llm.providers[].credentialsSecretRefCopy link

Description The name of the secret object that stores API provider credentials Type`object`

Expand

`name`

`string`

Name of the referent. This field is effectively required, but due to backwards compatibility is allowed to be empty. Instances of this type with an empty value here are almost certainly wrong. More info: [https://kubernetes.io/docs/concepts/overview/working-with-objects/names/#names](https://kubernetes.io/docs/concepts/overview/working-with-objects/names/#names)

| | Property | Type | Description |
| --- | --- | --- | --- |

Show more

#### 2.1.7. .spec.llm.providers[].modelsCopy link

Description List of models from the provider Type`array`

#### 2.1.8. .spec.llm.providers[].models[]Copy link

Description ModelSpec defines the LLM model to use and its parameters. Type`object` Required

- `name`

Expand

`contextWindowSize`

`integer`

Defines the model’s context window size, in tokens. The default is 128k tokens.

`name`

`string`

Model name

`parameters`

`object`

Model API parameters

`url`

`string`

Model API URL

| | Property | Type | Description |
| --- | --- | --- | --- |

Show more

#### 2.1.9. .spec.llm.providers[].models[].parametersCopy link

Description Model API parameters Type`object`

Expand

`maxTokensForResponse`

`integer`

Max tokens for response. The default is 2048 tokens.

| | Property | Type | Description |
| --- | --- | --- | --- |

Show more

#### 2.1.10. .spec.llm.providers[].tlsSecurityProfileCopy link

Description TLS Security Profile used by connection to provider Type`object`

Expand

`custom`

``

custom is a user-defined TLS security profile. Be extremely careful using a custom profile as invalid configurations can be catastrophic. An example custom profile looks like this:

ciphers:

- ECDHE-ECDSA-CHACHA20-POLY1305

- ECDHE-RSA-CHACHA20-POLY1305

- ECDHE-RSA-AES128-GCM-SHA256

- ECDHE-ECDSA-AES128-GCM-SHA256

minTLSVersion: VersionTLS11

`intermediate`

``

intermediate is a TLS security profile based on:

[https://wiki.mozilla.org/Security/Server_Side_TLS#Intermediate_compatibility_.28recommended.29](https://wiki.mozilla.org/Security/Server_Side_TLS#Intermediate_compatibility_.28recommended.29)

and looks like this (yaml):

ciphers:

- TLS_AES_128_GCM_SHA256

- TLS_AES_256_GCM_SHA384

- TLS_CHACHA20_POLY1305_SHA256

- ECDHE-ECDSA-AES128-GCM-SHA256

- ECDHE-RSA-AES128-GCM-SHA256

- ECDHE-ECDSA-AES256-GCM-SHA384

- ECDHE-RSA-AES256-GCM-SHA384

- ECDHE-ECDSA-CHACHA20-POLY1305

- ECDHE-RSA-CHACHA20-POLY1305

- DHE-RSA-AES128-GCM-SHA256

- DHE-RSA-AES256-GCM-SHA384

minTLSVersion: VersionTLS12

`modern`

``

modern is a TLS security profile based on:

[https://wiki.mozilla.org/Security/Server_Side_TLS#Modern_compatibility](https://wiki.mozilla.org/Security/Server_Side_TLS#Modern_compatibility)

and looks like this (yaml):

ciphers:

- TLS_AES_128_GCM_SHA256

- TLS_AES_256_GCM_SHA384

- TLS_CHACHA20_POLY1305_SHA256

minTLSVersion: VersionTLS13

`old`

``

old is a TLS security profile based on:

[https://wiki.mozilla.org/Security/Server_Side_TLS#Old_backward_compatibility](https://wiki.mozilla.org/Security/Server_Side_TLS#Old_backward_compatibility)

and looks like this (yaml):

ciphers:

- TLS_AES_128_GCM_SHA256

- TLS_AES_256_GCM_SHA384

- TLS_CHACHA20_POLY1305_SHA256

- ECDHE-ECDSA-AES128-GCM-SHA256

- ECDHE-RSA-AES128-GCM-SHA256

- ECDHE-ECDSA-AES256-GCM-SHA384

- ECDHE-RSA-AES256-GCM-SHA384

- ECDHE-ECDSA-CHACHA20-POLY1305

- ECDHE-RSA-CHACHA20-POLY1305

- DHE-RSA-AES128-GCM-SHA256

- DHE-RSA-AES256-GCM-SHA384

- DHE-RSA-CHACHA20-POLY1305

- ECDHE-ECDSA-AES128-SHA256

- ECDHE-RSA-AES128-SHA256

- ECDHE-ECDSA-AES128-SHA

- ECDHE-RSA-AES128-SHA

- ECDHE-ECDSA-AES256-SHA384

- ECDHE-RSA-AES256-SHA384

- ECDHE-ECDSA-AES256-SHA

- ECDHE-RSA-AES256-SHA

- DHE-RSA-AES128-SHA256

- DHE-RSA-AES256-SHA256

- AES128-GCM-SHA256

- AES256-GCM-SHA384

- AES128-SHA256

- AES256-SHA256

- AES128-SHA

- AES256-SHA

- DES-CBC3-SHA

minTLSVersion: VersionTLS10

`type`

`string`

type is one of Old, Intermediate, Modern or Custom. Custom provides the ability to specify individual TLS security profile parameters. Old, Intermediate and Modern are TLS security profiles based on:

[https://wiki.mozilla.org/Security/Server_Side_TLS#Recommended_configurations](https://wiki.mozilla.org/Security/Server_Side_TLS#Recommended_configurations)

The profiles are intent based, so they might change over time as new ciphers are developed and existing ciphers are found to be insecure. Depending on precisely which ciphers are available to a process, the list might be reduced.

Note that the Modern profile is currently not supported because it is not yet well adopted by common software libraries.

| | Property | Type | Description |
| --- | --- | --- | --- |

Show more

#### 2.1.11. .spec.mcpServersCopy link

Description MCP Server settings Type`array`

#### 2.1.12. .spec.mcpServers[]Copy link

Description MCPServer defines the settings for a single MCP server. Type`object` Required

- `name`

Expand

`name`

`string`

Name of the MCP server

`streamableHTTP`

`object`

Streamable HTTP Transport settings

| | Property | Type | Description |
| --- | --- | --- | --- |

Show more

#### 2.1.13. .spec.mcpServers[].streamableHTTPCopy link

Description Streamable HTTP Transport settings Type`object` Required

- `url`

Expand

`enableSSE`

`boolean`

Enable Server Sent Events

`headers`

`object (string)`

Headers to send to the MCP server

`sseReadTimeout`

`integer`

SSE Read Timeout, default is 10 seconds

`timeout`

`integer`

Timeout for the MCP server, default is 5 seconds

`url`

`string`

URL of the MCP server

| | Property | Type | Description |
| --- | --- | --- | --- |

Show more

#### 2.1.14. .spec.olsCopy link

Description OLSSpec defines the desired state of OLS deployment. Type`object` Required

- `defaultModel`
- `defaultProvider`

Expand

`additionalCAConfigMapRef`

`object`

Additional CA certificates for TLS communication between OLS service and LLM Provider

`byokRAGOnly`

`boolean`

Only use BYOK RAG sources, ignore the Red Hat OpenShift Lightspeed documentation RAG

`conversationCache`

`object`

Conversation cache settings

`defaultModel`

`string`

Default model for usage

`defaultProvider`

`string`

Default provider for usage

`deployment`

`object`

OLS deployment settings

`introspectionEnabled`

`boolean`

Enable introspection features

`logLevel`

`string`

Log level. Valid options are DEBUG, INFO, WARNING, ERROR and CRITICAL. Default: "INFO".

`proxyConfig`

`object`

Proxy settings for connecting to external servers, such as LLM providers.

`queryFilters`

`array`

Query filters

`quotaHandlersConfig`

`object`

LLM Token Quota Configuration

`rag`

`array`

RAG databases

`storage`

`object`

Persistent Storage Configuration

`tlsConfig`

`object`

TLS configuration of the Lightspeed backend’s HTTPS endpoint

`tlsSecurityProfile`

`object`

TLS Security Profile used by API endpoints

`userDataCollection`

`object`

User data collection switches

| | Property | Type | Description |
| --- | --- | --- | --- |

Show more

#### 2.1.15. .spec.ols.additionalCAConfigMapRefCopy link

Description Additional CA certificates for TLS communication between OLS service and LLM Provider Type`object`

Expand

`name`

`string`

Name of the referent. This field is effectively required, but due to backwards compatibility is allowed to be empty. Instances of this type with an empty value here are almost certainly wrong. More info: [https://kubernetes.io/docs/concepts/overview/working-with-objects/names/#names](https://kubernetes.io/docs/concepts/overview/working-with-objects/names/#names)

| | Property | Type | Description |
| --- | --- | --- | --- |

Show more

#### 2.1.16. .spec.ols.conversationCacheCopy link

Description Conversation cache settings Type`object`

Expand

`postgres`

`object`

PostgresSpec defines the desired state of Postgres.

`type`

`string`

Conversation cache type. Default: "postgres"

| | Property | Type | Description |
| --- | --- | --- | --- |

Show more

#### 2.1.17. .spec.ols.conversationCache.postgresCopy link

Description PostgresSpec defines the desired state of Postgres. Type`object`

Expand

`credentialsSecret`

`string`

Secret that holds postgres credentials

`dbName`

`string`

Postgres database name

`maxConnections`

`integer`

Postgres maxconnections. Default: "2000"

`sharedBuffers`

`integer-or-string`

Postgres sharedbuffers

`user`

`string`

Postgres user name

| | Property | Type | Description |
| --- | --- | --- | --- |

Show more

#### 2.1.18. .spec.ols.deploymentCopy link

Description OLS deployment settings Type`object`

Expand

`api`

`object`

API container settings.

`console`

`object`

Console container settings.

`dataCollector`

`object`

Data Collector container settings.

`database`

`object`

Database container settings.

`mcpServer`

`object`

MCP server container settings.

`replicas`

`integer`

Defines the number of desired OLS pods. Default: "1"

| | Property | Type | Description |
| --- | --- | --- | --- |

Show more

#### 2.1.19. .spec.ols.deployment.apiCopy link

Description API container settings. Type`object`

Expand

`nodeSelector`

`object (string)`

`resources`

`object`

ResourceRequirements describes the compute resource requirements.

`tolerations`

`array`

| | Property | Type | Description |
| --- | --- | --- | --- |

Show more

#### 2.1.20. .spec.ols.deployment.api.resourcesCopy link

Description ResourceRequirements describes the compute resource requirements. Type`object`

Expand

`claims`

`array`

Claims lists the names of resources, defined in spec.resourceClaims, that are used by this container.

This is an alpha field and requires enabling the DynamicResourceAllocation feature gate.

This field is immutable. It can only be set for containers.

`limits`

`integer-or-string`

Limits describes the maximum amount of compute resources allowed. More info: [https://kubernetes.io/docs/concepts/configuration/manage-resources-containers/](https://kubernetes.io/docs/concepts/configuration/manage-resources-containers/)

`requests`

`integer-or-string`

Requests describes the minimum amount of compute resources required. If Requests is omitted for a container, it defaults to Limits if that is explicitly specified, otherwise to an implementation-defined value. Requests cannot exceed Limits. More info: [https://kubernetes.io/docs/concepts/configuration/manage-resources-containers/](https://kubernetes.io/docs/concepts/configuration/manage-resources-containers/)

| | Property | Type | Description |
| --- | --- | --- | --- |

Show more

#### 2.1.21. .spec.ols.deployment.api.resources.claimsCopy link

Description

Claims lists the names of resources, defined in spec.resourceClaims, that are used by this container.

This is an alpha field and requires enabling the DynamicResourceAllocation feature gate.

This field is immutable. It can only be set for containers.

Type`array`

#### 2.1.22. .spec.ols.deployment.api.resources.claims[]Copy link

Description ResourceClaim references one entry in PodSpec.ResourceClaims. Type`object` Required

- `name`

Expand

`name`

`string`

Name must match the name of one entry in pod.spec.resourceClaims of the Pod where this field is used. It makes that resource available inside a container.

`request`

`string`

Request is the name chosen for a request in the referenced claim. If empty, everything from the claim is made available, otherwise only the result of this request.

| | Property | Type | Description |
| --- | --- | --- | --- |

Show more

#### 2.1.23. .spec.ols.deployment.api.tolerationsCopy link

Description Type`array`

#### 2.1.24. .spec.ols.deployment.api.tolerations[]Copy link

Description The pod this Toleration is attached to tolerates any taint that matches the triple using the matching operator. Type`object`

Expand

`effect`

`string`

Effect indicates the taint effect to match. Empty means match all taint effects. When specified, allowed values are NoSchedule, PreferNoSchedule and NoExecute.

`key`

`string`

Key is the taint key that the toleration applies to. Empty means match all taint keys. If the key is empty, operator must be Exists; this combination means to match all values and all keys.

`operator`

`string`

Operator represents a key’s relationship to the value. Valid operators are Exists and Equal. Defaults to Equal. Exists is equivalent to wildcard for value, so that a pod can tolerate all taints of a particular category.

`tolerationSeconds`

`integer`

TolerationSeconds represents the period of time the toleration (which must be of effect NoExecute, otherwise this field is ignored) tolerates the taint. By default, it is not set, which means tolerate the taint forever (do not evict). Zero and negative values will be treated as 0 (evict immediately) by the system.

`value`

`string`

Value is the taint value the toleration matches to. If the operator is Exists, the value should be empty, otherwise just a regular string.

| | Property | Type | Description |
| --- | --- | --- | --- |

Show more

#### 2.1.25. .spec.ols.deployment.consoleCopy link

Description Console container settings. Type`object`

Expand

`caCertificate`

`string`

Certificate Authority (CA) certificate used by the console proxy endpoint.

`nodeSelector`

`object (string)`

`replicas`

`integer`

Defines the number of desired Console pods. Default: "1"

`resources`

`object`

ResourceRequirements describes the compute resource requirements.

`tolerations`

`array`

| | Property | Type | Description |
| --- | --- | --- | --- |

Show more

#### 2.1.26. .spec.ols.deployment.console.resourcesCopy link

Description ResourceRequirements describes the compute resource requirements. Type`object`

Expand

`claims`

`array`

Claims lists the names of resources, defined in spec.resourceClaims, that are used by this container.

This is an alpha field and requires enabling the DynamicResourceAllocation feature gate.

This field is immutable. It can only be set for containers.

`limits`

`integer-or-string`

Limits describes the maximum amount of compute resources allowed. More info: [https://kubernetes.io/docs/concepts/configuration/manage-resources-containers/](https://kubernetes.io/docs/concepts/configuration/manage-resources-containers/)

`requests`

`integer-or-string`

Requests describes the minimum amount of compute resources required. If Requests is omitted for a container, it defaults to Limits if that is explicitly specified, otherwise to an implementation-defined value. Requests cannot exceed Limits. More info: [https://kubernetes.io/docs/concepts/configuration/manage-resources-containers/](https://kubernetes.io/docs/concepts/configuration/manage-resources-containers/)

| | Property | Type | Description |
| --- | --- | --- | --- |

Show more

#### 2.1.27. .spec.ols.deployment.console.resources.claimsCopy link

Description

Claims lists the names of resources, defined in spec.resourceClaims, that are used by this container.

This is an alpha field and requires enabling the DynamicResourceAllocation feature gate.

This field is immutable. It can only be set for containers.

Type`array`

#### 2.1.28. .spec.ols.deployment.console.resources.claims[]Copy link

Description ResourceClaim references one entry in PodSpec.ResourceClaims. Type`object` Required

- `name`

Expand

`name`

`string`

Name must match the name of one entry in pod.spec.resourceClaims of the Pod where this field is used. It makes that resource available inside a container.

`request`

`string`

Request is the name chosen for a request in the referenced claim. If empty, everything from the claim is made available, otherwise only the result of this request.

| | Property | Type | Description |
| --- | --- | --- | --- |

Show more

#### 2.1.29. .spec.ols.deployment.console.tolerationsCopy link

Description Type`array`

#### 2.1.30. .spec.ols.deployment.console.tolerations[]Copy link

Description The pod this Toleration is attached to tolerates any taint that matches the triple using the matching operator. Type`object`

Expand

`effect`

`string`

Effect indicates the taint effect to match. Empty means match all taint effects. When specified, allowed values are NoSchedule, PreferNoSchedule and NoExecute.

`key`

`string`

Key is the taint key that the toleration applies to. Empty means match all taint keys. If the key is empty, operator must be Exists; this combination means to match all values and all keys.

`operator`

`string`

Operator represents a key’s relationship to the value. Valid operators are Exists and Equal. Defaults to Equal. Exists is equivalent to wildcard for value, so that a pod can tolerate all taints of a particular category.

`tolerationSeconds`

`integer`

TolerationSeconds represents the period of time the toleration (which must be of effect NoExecute, otherwise this field is ignored) tolerates the taint. By default, it is not set, which means tolerate the taint forever (do not evict). Zero and negative values will be treated as 0 (evict immediately) by the system.

`value`

`string`

Value is the taint value the toleration matches to. If the operator is Exists, the value should be empty, otherwise just a regular string.

| | Property | Type | Description |
| --- | --- | --- | --- |

Show more

#### 2.1.31. .spec.ols.deployment.dataCollectorCopy link

Description Data Collector container settings. Type`object`

Expand

`resources`

`object`

ResourceRequirements describes the compute resource requirements.

| | Property | Type | Description |
| --- | --- | --- | --- |

Show more

#### 2.1.32. .spec.ols.deployment.dataCollector.resourcesCopy link

Description ResourceRequirements describes the compute resource requirements. Type`object`

Expand

`claims`

`array`

Claims lists the names of resources, defined in spec.resourceClaims, that are used by this container.

This is an alpha field and requires enabling the DynamicResourceAllocation feature gate.

This field is immutable. It can only be set for containers.

`limits`

`integer-or-string`

Limits describes the maximum amount of compute resources allowed. More info: [https://kubernetes.io/docs/concepts/configuration/manage-resources-containers/](https://kubernetes.io/docs/concepts/configuration/manage-resources-containers/)

`requests`

`integer-or-string`

Requests describes the minimum amount of compute resources required. If Requests is omitted for a container, it defaults to Limits if that is explicitly specified, otherwise to an implementation-defined value. Requests cannot exceed Limits. More info: [https://kubernetes.io/docs/concepts/configuration/manage-resources-containers/](https://kubernetes.io/docs/concepts/configuration/manage-resources-containers/)

| | Property | Type | Description |
| --- | --- | --- | --- |

Show more

#### 2.1.33. .spec.ols.deployment.dataCollector.resources.claimsCopy link

Description

Claims lists the names of resources, defined in spec.resourceClaims, that are used by this container.

This is an alpha field and requires enabling the DynamicResourceAllocation feature gate.

This field is immutable. It can only be set for containers.

Type`array`

#### 2.1.34. .spec.ols.deployment.dataCollector.resources.claims[]Copy link

Description ResourceClaim references one entry in PodSpec.ResourceClaims. Type`object` Required

- `name`

Expand

`name`

`string`

Name must match the name of one entry in pod.spec.resourceClaims of the Pod where this field is used. It makes that resource available inside a container.

`request`

`string`

Request is the name chosen for a request in the referenced claim. If empty, everything from the claim is made available, otherwise only the result of this request.

| | Property | Type | Description |
| --- | --- | --- | --- |

Show more

#### 2.1.35. .spec.ols.deployment.databaseCopy link

Description Database container settings. Type`object`

Expand

`nodeSelector`

`object (string)`

`resources`

`object`

ResourceRequirements describes the compute resource requirements.

`tolerations`

`array`

| | Property | Type | Description |
| --- | --- | --- | --- |

Show more

#### 2.1.36. .spec.ols.deployment.database.resourcesCopy link

Description ResourceRequirements describes the compute resource requirements. Type`object`

Expand

`claims`

`array`

Claims lists the names of resources, defined in spec.resourceClaims, that are used by this container.

This is an alpha field and requires enabling the DynamicResourceAllocation feature gate.

This field is immutable. It can only be set for containers.

`limits`

`integer-or-string`

Limits describes the maximum amount of compute resources allowed. More info: [https://kubernetes.io/docs/concepts/configuration/manage-resources-containers/](https://kubernetes.io/docs/concepts/configuration/manage-resources-containers/)

`requests`

`integer-or-string`

Requests describes the minimum amount of compute resources required. If Requests is omitted for a container, it defaults to Limits if that is explicitly specified, otherwise to an implementation-defined value. Requests cannot exceed Limits. More info: [https://kubernetes.io/docs/concepts/configuration/manage-resources-containers/](https://kubernetes.io/docs/concepts/configuration/manage-resources-containers/)

| | Property | Type | Description |
| --- | --- | --- | --- |

Show more

#### 2.1.37. .spec.ols.deployment.database.resources.claimsCopy link

Description

Claims lists the names of resources, defined in spec.resourceClaims, that are used by this container.

This is an alpha field and requires enabling the DynamicResourceAllocation feature gate.

This field is immutable. It can only be set for containers.

Type`array`

#### 2.1.38. .spec.ols.deployment.database.resources.claims[]Copy link

Description ResourceClaim references one entry in PodSpec.ResourceClaims. Type`object` Required

- `name`

Expand

`name`

`string`

Name must match the name of one entry in pod.spec.resourceClaims of the Pod where this field is used. It makes that resource available inside a container.

`request`

`string`

Request is the name chosen for a request in the referenced claim. If empty, everything from the claim is made available, otherwise only the result of this request.

| | Property | Type | Description |
| --- | --- | --- | --- |

Show more

#### 2.1.39. .spec.ols.deployment.database.tolerationsCopy link

Description Type`array`

#### 2.1.40. .spec.ols.deployment.database.tolerations[]Copy link

Description The pod this Toleration is attached to tolerates any taint that matches the triple using the matching operator. Type`object`

Expand

`effect`

`string`

Effect indicates the taint effect to match. Empty means match all taint effects. When specified, allowed values are NoSchedule, PreferNoSchedule and NoExecute.

`key`

`string`

Key is the taint key that the toleration applies to. Empty means match all taint keys. If the key is empty, operator must be Exists; this combination means to match all values and all keys.

`operator`

`string`

Operator represents a key’s relationship to the value. Valid operators are Exists and Equal. Defaults to Equal. Exists is equivalent to wildcard for value, so that a pod can tolerate all taints of a particular category.

`tolerationSeconds`

`integer`

TolerationSeconds represents the period of time the toleration (which must be of effect NoExecute, otherwise this field is ignored) tolerates the taint. By default, it is not set, which means tolerate the taint forever (do not evict). Zero and negative values will be treated as 0 (evict immediately) by the system.

`value`

`string`

Value is the taint value the toleration matches to. If the operator is Exists, the value should be empty, otherwise just a regular string.

| | Property | Type | Description |
| --- | --- | --- | --- |

Show more

#### 2.1.41. .spec.ols.deployment.mcpServerCopy link

Description MCP server container settings. Type`object`

Expand

`resources`

`object`

ResourceRequirements describes the compute resource requirements.

| | Property | Type | Description |
| --- | --- | --- | --- |

Show more

#### 2.1.42. .spec.ols.deployment.mcpServer.resourcesCopy link

Description ResourceRequirements describes the compute resource requirements. Type`object`

Expand

`claims`

`array`

Claims lists the names of resources, defined in spec.resourceClaims, that are used by this container.

This is an alpha field and requires enabling the DynamicResourceAllocation feature gate.

This field is immutable. It can only be set for containers.

`limits`

`integer-or-string`

Limits describes the maximum amount of compute resources allowed. More info: [https://kubernetes.io/docs/concepts/configuration/manage-resources-containers/](https://kubernetes.io/docs/concepts/configuration/manage-resources-containers/)

`requests`

`integer-or-string`

Requests describes the minimum amount of compute resources required. If Requests is omitted for a container, it defaults to Limits if that is explicitly specified, otherwise to an implementation-defined value. Requests cannot exceed Limits. More info: [https://kubernetes.io/docs/concepts/configuration/manage-resources-containers/](https://kubernetes.io/docs/concepts/configuration/manage-resources-containers/)

| | Property | Type | Description |
| --- | --- | --- | --- |

Show more

#### 2.1.43. .spec.ols.deployment.mcpServer.resources.claimsCopy link

Description

Claims lists the names of resources, defined in spec.resourceClaims, that are used by this container.

This is an alpha field and requires enabling the DynamicResourceAllocation feature gate.

This field is immutable. It can only be set for containers.

Type`array`

#### 2.1.44. .spec.ols.deployment.mcpServer.resources.claims[]Copy link

Description ResourceClaim references one entry in PodSpec.ResourceClaims. Type`object` Required

- `name`

Expand

`name`

`string`

Name must match the name of one entry in pod.spec.resourceClaims of the Pod where this field is used. It makes that resource available inside a container.

`request`

`string`

Request is the name chosen for a request in the referenced claim. If empty, everything from the claim is made available, otherwise only the result of this request.

| | Property | Type | Description |
| --- | --- | --- | --- |

Show more

#### 2.1.45. .spec.ols.proxyConfigCopy link

Description Proxy settings for connecting to external servers, such as LLM providers. Type`object`

Expand

`proxyCACertificate`

`object`

The configmap holding proxy CA certificate

`proxyURL`

`string`

Proxy URL, e.g. [https://proxy.example.com:8080](https://proxy.example.com:8080/) If not specified, the cluster wide proxy will be used, though env var "https_proxy".

| | Property | Type | Description |
| --- | --- | --- | --- |

Show more

#### 2.1.46. .spec.ols.proxyConfig.proxyCACertificateCopy link

Description The configmap holding proxy CA certificate Type`object`

Expand

`name`

`string`

Name of the referent. This field is effectively required, but due to backwards compatibility is allowed to be empty. Instances of this type with an empty value here are almost certainly wrong. More info: [https://kubernetes.io/docs/concepts/overview/working-with-objects/names/#names](https://kubernetes.io/docs/concepts/overview/working-with-objects/names/#names)

| | Property | Type | Description |
| --- | --- | --- | --- |

Show more

#### 2.1.47. .spec.ols.queryFiltersCopy link

Description Query filters Type`array`

#### 2.1.48. .spec.ols.queryFilters[]Copy link

Description QueryFiltersSpec defines filters to manipulate questions/queries. Type`object`

Expand

`name`

`string`

Filter name.

`pattern`

`string`

Filter pattern.

`replaceWith`

`string`

Replacement for the matched pattern.

| | Property | Type | Description |
| --- | --- | --- | --- |

Show more

#### 2.1.49. .spec.ols.quotaHandlersConfigCopy link

Description LLM Token Quota Configuration Type`object`

Expand

`enableTokenHistory`

`boolean`

Enable token history

`limitersConfig`

`array`

Token quota limiters

| | Property | Type | Description |
| --- | --- | --- | --- |

Show more

#### 2.1.50. .spec.ols.quotaHandlersConfig.limitersConfigCopy link

Description Token quota limiters Type`array`

#### 2.1.51. .spec.ols.quotaHandlersConfig.limitersConfig[]Copy link

Description LimiterConfig defines settings for a token quota limiter Type`object` Required

- `initialQuota`
- `name`
- `period`
- `quotaIncrease`
- `type`

Expand

`initialQuota`

`integer`

Initial value of the token quota

`name`

`string`

Name of the limiter

`period`

`string`

Period of time the token quota is for

`quotaIncrease`

`integer`

Token quota increase step

`type`

`string`

Type of the limiter

| | Property | Type | Description |
| --- | --- | --- | --- |

Show more

#### 2.1.52. .spec.ols.ragCopy link

Description RAG databases Type`array`

#### 2.1.53. .spec.ols.rag[]Copy link

Description RAGSpec defines how to retrieve a RAG databases. Type`object` Required

- `image`

Expand

`image`

`string`

The URL of the container image to use as a RAG source

`indexID`

`string`

The Index ID of the RAG database

`indexPath`

`string`

The path to the RAG database inside of the container image

| | Property | Type | Description |
| --- | --- | --- | --- |

Show more

#### 2.1.54. .spec.ols.storageCopy link

Description Persistent Storage Configuration Type`object`

Expand

`class`

`string`

Storage class of the requested volume

`size`

`integer-or-string`

Size of the requested volume

| | Property | Type | Description |
| --- | --- | --- | --- |

Show more

#### 2.1.55. .spec.ols.tlsConfigCopy link

Description TLS configuration of the Lightspeed backend’s HTTPS endpoint Type`object`

Expand

`keyCertSecretRef`

`object`

KeySecretRef is the secret that holds the TLS key.

| | Property | Type | Description |
| --- | --- | --- | --- |

Show more

#### 2.1.56. .spec.ols.tlsConfig.keyCertSecretRefCopy link

Description KeySecretRef is the secret that holds the TLS key. Type`object`

Expand

`name`

`string`

Name of the referent. This field is effectively required, but due to backwards compatibility is allowed to be empty. Instances of this type with an empty value here are almost certainly wrong. More info: [https://kubernetes.io/docs/concepts/overview/working-with-objects/names/#names](https://kubernetes.io/docs/concepts/overview/working-with-objects/names/#names)

| | Property | Type | Description |
| --- | --- | --- | --- |

Show more

#### 2.1.57. .spec.ols.tlsSecurityProfileCopy link

Description TLS Security Profile used by API endpoints Type`object`

Expand

`custom`

``

custom is a user-defined TLS security profile. Be extremely careful using a custom profile as invalid configurations can be catastrophic. An example custom profile looks like this:

ciphers:

- ECDHE-ECDSA-CHACHA20-POLY1305

- ECDHE-RSA-CHACHA20-POLY1305

- ECDHE-RSA-AES128-GCM-SHA256

- ECDHE-ECDSA-AES128-GCM-SHA256

minTLSVersion: VersionTLS11

`intermediate`

``

intermediate is a TLS security profile based on:

[https://wiki.mozilla.org/Security/Server_Side_TLS#Intermediate_compatibility_.28recommended.29](https://wiki.mozilla.org/Security/Server_Side_TLS#Intermediate_compatibility_.28recommended.29)

and looks like this (yaml):

ciphers:

- TLS_AES_128_GCM_SHA256

- TLS_AES_256_GCM_SHA384

- TLS_CHACHA20_POLY1305_SHA256

- ECDHE-ECDSA-AES128-GCM-SHA256

- ECDHE-RSA-AES128-GCM-SHA256

- ECDHE-ECDSA-AES256-GCM-SHA384

- ECDHE-RSA-AES256-GCM-SHA384

- ECDHE-ECDSA-CHACHA20-POLY1305

- ECDHE-RSA-CHACHA20-POLY1305

- DHE-RSA-AES128-GCM-SHA256

- DHE-RSA-AES256-GCM-SHA384

minTLSVersion: VersionTLS12

`modern`

``

modern is a TLS security profile based on:

[https://wiki.mozilla.org/Security/Server_Side_TLS#Modern_compatibility](https://wiki.mozilla.org/Security/Server_Side_TLS#Modern_compatibility)

and looks like this (yaml):

ciphers:

- TLS_AES_128_GCM_SHA256

- TLS_AES_256_GCM_SHA384

- TLS_CHACHA20_POLY1305_SHA256

minTLSVersion: VersionTLS13

`old`

``

old is a TLS security profile based on:

[https://wiki.mozilla.org/Security/Server_Side_TLS#Old_backward_compatibility](https://wiki.mozilla.org/Security/Server_Side_TLS#Old_backward_compatibility)

and looks like this (yaml):

ciphers:

- TLS_AES_128_GCM_SHA256

- TLS_AES_256_GCM_SHA384

- TLS_CHACHA20_POLY1305_SHA256

- ECDHE-ECDSA-AES128-GCM-SHA256

- ECDHE-RSA-AES128-GCM-SHA256

- ECDHE-ECDSA-AES256-GCM-SHA384

- ECDHE-RSA-AES256-GCM-SHA384

- ECDHE-ECDSA-CHACHA20-POLY1305

- ECDHE-RSA-CHACHA20-POLY1305

- DHE-RSA-AES128-GCM-SHA256

- DHE-RSA-AES256-GCM-SHA384

- DHE-RSA-CHACHA20-POLY1305

- ECDHE-ECDSA-AES128-SHA256

- ECDHE-RSA-AES128-SHA256

- ECDHE-ECDSA-AES128-SHA

- ECDHE-RSA-AES128-SHA

- ECDHE-ECDSA-AES256-SHA384

- ECDHE-RSA-AES256-SHA384

- ECDHE-ECDSA-AES256-SHA

- ECDHE-RSA-AES256-SHA

- DHE-RSA-AES128-SHA256

- DHE-RSA-AES256-SHA256

- AES128-GCM-SHA256

- AES256-GCM-SHA384

- AES128-SHA256

- AES256-SHA256

- AES128-SHA

- AES256-SHA

- DES-CBC3-SHA

minTLSVersion: VersionTLS10

`type`

`string`

type is one of Old, Intermediate, Modern or Custom. Custom provides the ability to specify individual TLS security profile parameters. Old, Intermediate and Modern are TLS security profiles based on:

[https://wiki.mozilla.org/Security/Server_Side_TLS#Recommended_configurations](https://wiki.mozilla.org/Security/Server_Side_TLS#Recommended_configurations)

The profiles are intent based, so they might change over time as new ciphers are developed and existing ciphers are found to be insecure. Depending on precisely which ciphers are available to a process, the list might be reduced.

Note that the Modern profile is currently not supported because it is not yet well adopted by common software libraries.

| | Property | Type | Description |
| --- | --- | --- | --- |

Show more

#### 2.1.58. .spec.ols.userDataCollectionCopy link

Description User data collection switches Type`object`

Expand

`feedbackDisabled`

`boolean`

`transcriptsDisabled`

`boolean`

| | Property | Type | Description |
| --- | --- | --- | --- |

Show more

#### 2.1.59. .spec.olsDataCollectorCopy link

Description OLSDataCollectorSpec defines allowed OLS data collector configuration. Type`object`

Expand

`logLevel`

`string`

Log level. Valid options are DEBUG, INFO, WARNING, ERROR and CRITICAL. Default: "INFO".

| | Property | Type | Description |
| --- | --- | --- | --- |

Show more

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

*Source: https://docs.redhat.com/en/documentation/red_hat_openshift_lightspeed/1.0/html-single/operate/index*

Red Hat OpenShift Lightspeed 1.0 
Using OpenShift Lightspeed 
Red Hat OpenShift Documentation Team 
Legal Notice 
Abstract 
This documentation provides information about operating OpenShift Lightspeed. 
Chapter 1. Operating OpenShift Lightspeed 
Copy link Link copied to clipboard! 
Submit questions to OpenShift Lightspeed, manage resource attachments, and resolve system alerts to optimize your troubleshooting experience. 
1.1. Using the chat window to ask a question 
Copy link Link copied to clipboard! 
Get immediate product information and support by asking questions through the Red Hat OpenShift Lightspeed icon. 
Procedure 
Click the Red Hat OpenShift Lightspeed icon in the lower-right corner of the screen. 
This action presents the Red Hat OpenShift Lightspeed user interface. 
Enter a question. 
Click the Submit button. 
OpenShift Lightspeed returns information based on your question. 
1.2. About Lightspeed conversations 
Copy link Link copied to clipboard! 
Use OpenShift Lightspeed to get help for OpenShift, Kubernetes, and specialized OpenShift components such as OpenShift Virtualization and OpenShift Service Mesh 
OpenShift Lightspeed ignores questions unrelated to the target topics. If OpenShift Lightspeed misinterprets your question, try rephrasing the question for better clarity. Conversation history provides context that OpenShift Lightspeed references when generating answers. Using specific language helps increase the success of responses. For example, instead of asking "How do I start a virtual machine?" try asking "How do I start a virtual machine in OpenShift Virtualization?" 
Conversation history does not persist if you reload the console page. Reloading the console page performs the same action as clicking the New Chat button. Restarting OpenShift Lightspeed erases conversation history. 
1.3. Providing feedback for a conversation 
Copy link Link copied to clipboard! 
Send feedback to Red Hat on specific OpenShift Lightspeed interactions to improve the quality and accuracy of future responses. 
Prerequisites 
You have installed the OpenShift Lightspeed Operator and deployed the OpenShift Lightspeed service. 
Procedure 
Click the Red Hat OpenShift Lightspeed icon in the lower-right corner of the screen. 
Enter a question into the Send a message field: 
Click the Submit button. 
OpenShift Lightspeed returns information. 
To send feedback on a particular question and response, click the thumbs up or thumbs down button. 
This action presents the field that you use to enter additional information. 
Click the Submit button. 
Your rating, any text you entered, the specific question you asked OpenShift Lightspeed, and the response are all sent to Red Hat for review. 
1.4. Sample conversation overview 
Copy link Link copied to clipboard! 
Use these sample prompts and follow-up strategies to start effective conversations with OpenShift Lightspeed and improve response accuracy. 
Some examples show specific workflows to use in the interface. Ask your follow-up questions in the same chat to help create context. Because OpenShift Lightspeed uses the whole chat, follow-up questions help refine the results. You can get better answers by rephrasing your question or asking for more detail. 
1.4.1. Asking a general question 
Copy link Link copied to clipboard! 
Ask general questions about OpenShift Container Platform using OpenShift Lightspeed to quickly find documentation, best practices, and product information. 
Procedure 
Click the Red Hat OpenShift Lightspeed icon in the lower-right corner of the screen. 
Enter the following question into the Send a message field: 
What is an OpenShift image stream used for? 
Click the Submit button. 
OpenShift Lightspeed returns information that provides an explanation of an image stream and details about usage. 
1.4.2. Asking related questions 
Copy link Link copied to clipboard! 
Ask follow-up questions in OpenShift Lightspeed to refine results and obtain specific examples based on prior conversation history. 
Procedure 
Click the Red Hat OpenShift Lightspeed icon in the lower-right corner of the screen. 
Enter the following question into the Send a message field: 
How are OpenShift security context constraints used? 
Click the Submit button. 
OpenShift Lightspeed returns information. 
Enter the following question into the Send a message field: 
Can I control who can use a particular SCC? 
Click the Submit button. 
OpenShift Lightspeed returns refined information that has additional details. 
Enter the following question into the Send a message field: 
Can you give me an example? 
Click the Submit button. 
OpenShift Lightspeed returns sample code that you can copy and use. 
1.4.3. Attaching a resource object to your question 
Copy link Link copied to clipboard! 
Attach a cluster resource object to your OpenShift Lightspeed query to give specific context and receive more relevant, data-driven troubleshooting advice. 
Procedure 
Navigate to a supported resource in the OpenShift Container Platform web console. For example, click Workloads → Pods and then click the name of a pod. 
Click the Red Hat OpenShift Lightspeed icon in the lower-right corner of the screen. 
Click Add in the Red Hat OpenShift Lightspeed user interface to attach a resource object. 
Select the resource object to attach to the question. 
Enter a question. 
Click the Submit button. 
OpenShift Lightspeed returns information based on your question. 
1.4.4. Using OpenShift Lightspeed to troubleshoot alerts 
Copy link Link copied to clipboard! 
Analyze and resolve cluster alerts by querying OpenShift Lightspeed for root cause explanations, documentation links, and recommended remediation steps. 
Procedure 
In the OpenShift Container Platform web console, select Observe → Alerting . 
Click to expand an alert row. 
Click the alert to present it on the screen and view details. 
Click the Red Hat OpenShift Lightspeed icon in the lower-right corner of the screen. 
Click Attach context and select Alert . 
In the Red Hat OpenShift Lightspeed user interface, enter the following text: 
What should I do about this alert? 
Click the Submit button. 
The alert provides OpenShift Lightspeed with context when generating a response. 
1.5. Starting a new chat conversation 
Copy link Link copied to clipboard! 
Reset your OpenShift Lightspeed session by clearing the chat history to start a new conversation without influence from the prior context. 
When you ask follow-up questions, OpenShift Lightspeed references the conversation history for additional context that influences replies. Whenever you start a new conversation with OpenShift Lightspeed you should clear the chat history. 
Procedure 
In the Red Hat OpenShift Lightspeed natural language interface, click Clear chat . 
This action clears the history of prior conversations. 
Enter a question. 
Click the Submit button. 
OpenShift Lightspeed only references the new question when generating a response. 
Legal Notice 
Copy link Link copied to clipboard! 
Copyright © Red Hat. 
Except as otherwise noted below, the text of and illustrations in this documentation are licensed by Red Hat under the Creative Commons Attribution–Share Alike 3.0 Unported license . If you distribute this document or an adaptation of it, you must provide the URL for the original version. 
Red Hat, as the licensor of this document, waives the right to enforce, and agrees not to assert, Section 4d of CC-BY-SA to the fullest extent permitted by applicable law. 
Red Hat, the Red Hat logo, JBoss, Hibernate, and RHCE are trademarks or registered trademarks of Red Hat, LLC. or its subsidiaries in the United States and other countries. 
Linux ® is the registered trademark of Linus Torvalds in the United States and other countries. 
XFS is a trademark or registered trademark of Hewlett Packard Enterprise Development LP or its subsidiaries in the United States and other countries. 
The OpenStack ® Word Mark and OpenStack logo are trademarks or registered trademarks of the Linux Foundation, used under license. 
All other trademarks are the property of their respective owners.

---

*Source: https://docs.redhat.com/en/documentation/red_hat_openshift_lightspeed/1.0/html-single/troubleshoot/index*

Red Hat OpenShift Lightspeed 1.0 
Troubleshooting OpenShift Lightspeed 
Red Hat OpenShift Documentation Team 
Legal Notice 
Abstract 
This documentation provides information about troubleshooting OpenShift Lightspeed. 
Chapter 1. Troubleshooting OpenShift Lightspeed 
Copy link Link copied to clipboard! 
Review solutions and workarounds for common installation, configuration, and operational issues encountered with OpenShift Lightspeed. 
1.1. 502 Bad Gateway errors in the interface 
Copy link Link copied to clipboard! 
Avoid 502 Bad Gateway errors by waiting for the service pods to finish starting. 
Give OpenShift Lightspeed and OpenShift Container Platform a few minutes to get ready after you deploy them. Then, try using OpenShift Lightspeed again. 
1.2. Operator missing from the OperatorHub list 
Copy link Link copied to clipboard! 
The OperatorHub displays the OpenShift Lightspeed Operator only for supported architectures. Filtering prevents the Operator from appearing on anything other than the x86_64 architecture. 
1.3. Thinking model generates delineator prompt 
Copy link Link copied to clipboard! 
Reasoning models use tags such as THOUGHT or reasoning to separate their inner logic from the final answer. 
OpenShift Lightspeed does not control these tags or add them to the output. This feature is part of the model itself. Usually, you can turn off these tags in one of two ways: 
Add a keyword to your prompt if the model supports it. For example, /nothink . Check the documentation for your model to obtain the right word to use. 
Change the inference server configuration settings to disable the delineator feature. For more information, see the documentation for the inference server or for the model you are using. 
Legal Notice 
Copy link Link copied to clipboard! 
Copyright © Red Hat. 
Except as otherwise noted below, the text of and illustrations in this documentation are licensed by Red Hat under the Creative Commons Attribution–Share Alike 3.0 Unported license . If you distribute this document or an adaptation of it, you must provide the URL for the original version. 
Red Hat, as the licensor of this document, waives the right to enforce, and agrees not to assert, Section 4d of CC-BY-SA to the fullest extent permitted by applicable law. 
Red Hat, the Red Hat logo, JBoss, Hibernate, and RHCE are trademarks or registered trademarks of Red Hat, LLC. or its subsidiaries in the United States and other countries. 
Linux ® is the registered trademark of Linus Torvalds in the United States and other countries. 
XFS is a trademark or registered trademark of Hewlett Packard Enterprise Development LP or its subsidiaries in the United States and other countries. 
The OpenStack ® Word Mark and OpenStack logo are trademarks or registered trademarks of the Linux Foundation, used under license. 
All other trademarks are the property of their respective owners.
