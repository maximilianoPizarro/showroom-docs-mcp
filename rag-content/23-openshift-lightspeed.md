# Red Hat OpenShift Lightspeed 1.0

Source: Official Red Hat Documentation (PDF)


---

## Lightspeed About

Red Hat OpenShift Lightspeed 1.0
About
Introduction to OpenShift Lightspeed
Last Updated: 2026-02-18

Red Hat OpenShift Lightspeed 1.0 About
Introduction to OpenShift Lightspeed

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
This document provides an overview of OpenShift Lightspeed features.

. . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 
Table of Contents
## ABOUT OPENSHIFT LIGHTSPEED
### OPENSHIFT LIGHTSPEED OVERVIEW
1.1.1. About product coverage
1.1.1.1. Product exceptions
### OPENSHIFT REQUIREMENTS
1.2.1. Cluster resource requirements
### LARGE LANGUAGE MODEL (LLM) REQUIREMENTS
1.3.1. IBM watsonx
1.3.2. Open AI
1.3.3. Microsoft Azure OpenAI
1.3.4. Red Hat Enterprise Linux AI
1.3.5. Red Hat OpenShift AI
### OPENSHIFT LIGHTSPEED FEDERAL INFORMATION PROCESSING STANDARDS (FIPS) SUPPORT
### SUPPORTED ARCHITECTURE
### ABOUT RUNNING OPENSHIFT LIGHTSPEED IN DISCONNECTED MODE
### ABOUT DATA USE
### ABOUT DATA, TELEMETRY, TRANSCRIPT, AND FEEDBACK COLLECTION
### REMOTE HEALTH MONITORING OVERVIEW
1.9.1. Transcript collection overview
1.9.2. Feedback collection overview
1.9.3. Disabling data collection on the OpenShift Lightspeed Service
### ADDITIONAL RESOURCES
3
3
3
3
3
3
4
4
4
5
5
5
5
6
6
6
6
7
7
7
7
8
Table of Contents
1

Red Hat OpenShift Lightspeed 1.0 About
2
## ABOUT OPENSHIFT LIGHTSPEED
Red Hat OpenShift Lightspeed is a generative AI service that helps developers and administrators solve
problems by providing context-aware recommendations for OpenShift Container Platform.
### OPENSHIFT LIGHTSPEED OVERVIEW
Use Red Hat OpenShift Lightspeed to fix and manage your OpenShift clusters. You can operate the
virtual assistant by using plain English right inside the OpenShift web console.
1.1.1. About product coverage
Red Hat OpenShift Lightspeed answers your questions by using information from official OpenShift
Container Platform documentation.
1.1.1.1. Product exceptions
The OpenShift Container Platform documentation does not cover every Red Hat product. Because of
this, OpenShift Lightspeed uses your large language model (LLM) to create answers for these products:
Builds for Red Hat OpenShift
Red Hat Advanced Cluster Security for Kubernetes
Red Hat Advanced Cluster Management for Kubernetes
Red Hat CodeReady Workspaces
Red Hat OpenShift GitOps
Red Hat OpenShift Pipelines
Red Hat OpenShift Serverless
Red Hat OpenShift Service Mesh 3.x
Red Hat Quay
### OPENSHIFT REQUIREMENTS
Hardware and software requirements for OpenShift Lightspeed, including supported OpenShift
Container Platform versions and CPU architectures.
OpenShift Container Platform clusters enable telemetry by default.
When telemetry is on, OpenShift Lightspeed sends your chats and feedback to Red Hat.
When telemetry is off, OpenShift Lightspeed does not send this data.
To stop OpenShift Lightspeed from sending your chats and feedback, you must disable
telemetry for the whole cluster.
1.2.1. Cluster resource requirements
## ABOUT OPENSHIFT LIGHTSPEED
3

Ensure that OpenShift Lightspeed has enough CPU, memory, and storage allocations to support
Service performance and cluster stability without impacting other cluster workloads.
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
OpenShift Container
Platform web console
0.1
50 Mi
100 Mi
OpenShift Lightspeed
operator
0.1
64 Mi
256 Mi
### LARGE LANGUAGE MODEL (LLM) REQUIREMENTS
OpenShift Lightspeed supports Software as a Service (SaaS) and self-hosted large language model
(LLM) providers that meet defined authentication requirements.
The LLM is a type of machine learning model that interprets and generates human-like language. When
you use the LLM with a virtual assistant, the LLM can accurately interpret questions and offers helpful
answers in a conversational manner. The OpenShift Lightspeed Service must have access to the LLM
provider.
The Service does not provide the LLM for you, so you must configure the LLM before installing the
OpenShift Lightspeed Operator.
NOTE
Red Hat does not provide support for any specific models or make suggestions or
support statements pertaining to models.
The OpenShift Lightspeed Service can rely on the following SaaS LLM providers:
OpenAI
Microsoft Azure OpenAI
IBM watsonx
If you want to self-host a model, you can use Red Hat OpenShift AI or Red Hat Enterprise Linux AI as
your model provider.
1.3.1. IBM watsonx
To use IBM watsonx with Red Hat OpenShift Lightspeed, you need an account with IBM Cloud watsonx.
For more information, see the Documentation for IBM watsonx as a Service .
1.3.2. Open AI
Red Hat OpenShift Lightspeed 1.0 About
4

To use OpenAI with Red Hat OpenShift Lightspeed, you need access to the OpenAI API platform. For
more information, see the OpenAI developer platform documentation.
1.3.3. Microsoft Azure OpenAI
To use Microsoft Azure with Red Hat OpenShift Lightspeed, you need access to Microsoft Azure
OpenAI. For more information, see the Azure OpenAI documentation.
1.3.4. Red Hat Enterprise Linux AI
Red Hat Enterprise Linux AI is OpenAI API-compatible, and you configure Red Hat Enterprise Linux AI in
a similar manner as the OpenAI provider.
You can configure Red Hat Enterprise Linux AI as the LLM provider.
Because the Red Hat Enterprise Linux is in a different environment than the OpenShift Lightspeed
deployment, the model deployment must allow access by using a secure connection. For more
information, see Optional: Allowing access to a model from a secure endpoint .
OpenShift Lightspeed version 1.0 and later supports vLLM Server version 0.8.4 and later. When self-
hosting the LLM with Red Hat Enterprise Linux AI, you can use vLLM Server as the inference engine.
1.3.5. Red Hat OpenShift AI
Red Hat OpenShift AI is OpenAI API-compatible, and you configure Red Hat OpenShift AI largely the
same as the OpenAI provider.
You must deploy the LLM on the Red Hat OpenShift AI single-model serving platform that uses the
virtual large language model (vLLM) runtime. If the model deployment runs in a different OpenShift
environment than the OpenShift Lightspeed deployment, include a route to expose the model
deployment outside the cluster. For more information, see About the single-model serving platform.
OpenShift Lightspeed version 1.0 and later supports vLLM Server version 0.8.4 and later. When self-
hosting the LLM with Red Hat OpenShift AI, you can use vLLM Server as the inference engine.
### OPENSHIFT LIGHTSPEED FEDERAL INFORMATION PROCESSING
STANDARDS (FIPS) SUPPORT
Red Hat OpenShift Lightspeed supports Federal Information Processing Standards (FIPS). You can run
Red Hat OpenShift Lightspeed on OpenShift clusters that use FIPS mode.
FIPS is a set of publicly announced standards developed by the National Institute of Standards and
Technology (NIST), a part of the U.S. Department of Commerce. The primary purpose of FIPS is to
ensure the security and interoperability of computer systems used by U.S. federal government agencies
and their associated contractors.
## ABOUT OPENSHIFT LIGHTSPEED
5

IMPORTANT
When running on OpenShift Container Platform in FIPS mode, it uses the Red Hat
Enterprise Linux cryptographic libraries submitted, or planned to be submitted, to NIST
for FIPS validation on only the x86_64, ppc64le, and s390X architectures. For more
information about the NIST validation program, see Cryptographic Module Validation
Program (NIST). For the latest NIST status of the individual versions of Red Hat
Enterprise Linux cryptographic libraries that have been submitted for validation, see
Product compliance.
### SUPPORTED ARCHITECTURE
OpenShift Lightspeed works with OpenShift Container Platform clusters that use the x86_64
architecture.
### ABOUT RUNNING OPENSHIFT LIGHTSPEED IN DISCONNECTED
MODE
OpenShift Lightspeed works in disconnected clusters without full internet access.
In a disconnected cluster, you must mirror the container images you need. For more help, see "Mirroring
in disconnected environments" in the OpenShift Container Platform documentation.
NOTE
When you mirror images in a disconnected cluster, list the OpenShift Lightspeed
Operator with the oc mirror command.
### ABOUT DATA USE
OpenShift Lightspeed adds cluster and environment details to your messages. Then, it sends this data
to the large language model (LLM) to get an answer.
OpenShift Lightspeed has limited ability to filter or hide the data you send to the LLM. Do not enter any
information into the interface that you want to keep private from the LLM.
When you send transcripts or feedback to Red Hat, you agree that Red Hat can use the data to improve
our Service. The transcript recording data uses the Red Hat Insights system. It follows the same security
rules and access limits as that system.
You can email Red Hat and ask us to delete your data.
### ABOUT DATA, TELEMETRY, TRANSCRIPT, AND FEEDBACK
COLLECTION
OpenShift Lightspeed sends your messages and cluster data through a redaction layer. It does this to
clean the data before it goes to the LLM.
Do not enter anything into the OpenShift Lightspeed interface that you want to keep private from the
LLM.
The transcript recording data uses the Red Hat Insights system. It follows the same security rules and
access limits as that system. You can learn more in the Red Hat Insights security guide .
Red Hat OpenShift Lightspeed 1.0 About
6
### REMOTE HEALTH MONITORING OVERVIEW
Remote Health Monitoring uses the Telemeter Client and Insights Operator to gather and report cluster
information for Red Hat analysis and support.
You can learn how Red Hat collects data in the OpenShift Container Platform documentation. To stop
sending chat transcripts or feedback, you must opt out of remote health monitoring. Follow the steps in
the "About remote health monitoring" section of the OpenShift Container Platform documentation.
1.9.1. Transcript collection overview
OpenShift Lightspeed sends chat transcripts to Red Hat on a set schedule. The Service uses a redaction
layer to filter data before the Service shares or logs it.
By default, OpenShift Lightspeed sends these transcripts every two hours. Red Hat cannot see your
original data. OpenShift Lightspeed hides sensitive data before it reaches any logs.
OpenShift Lightspeed saves conversation transcripts for a short time. This includes:
Queries from the user.
The complete message sent to the configured large language model (LLM) provider, which
includes system instructions, referenced documentation, and the user question.
The complete response from the LLM provider.
Transcripts come from your cluster and stay linked to it. Red Hat can match these clusters to specific
customer accounts. These transcripts do not contain any user data.
1.9.2. Feedback collection overview
OpenShift Lightspeed collects opt-in user feedback from the virtual assistant interface to analyze
response accuracy and improve Service quality.
If you submit feedback, Red Hat stores and receives your feedback score, text, and query. Red Hat also
receives the large language model (LLM) response on the same schedule as transcripts. When you use
the redaction tools, Red Hat receives only the filtered data. Red Hat does not see the original data.
OpenShift Lightspeed hides your data before the system logs it.
Your feedback stays associated with the cluster where it began. Red Hat can match these clusters to
specific customer accounts. This feedback does not contain any user details, and Red Hat cannot link
the feedback to any specific person.
1.9.3. Disabling data collection on the OpenShift Lightspeed Service
Disable data collection for Lightspeed by updating the telemetry settings in the OLSConfig custom
resource (CR) file settings.
By default, OpenShift Lightspeed collects information about the questions you ask and the feedback
you offer on the answers that the Service generates.
Prerequisites
You have a large language model (LLM) provider available for use with the OpenShift
Lightspeed Service.
## ABOUT OPENSHIFT LIGHTSPEED
7

You have installed the OpenShift Lightspeed Operator.
You have configured the OLSConfig CR file, which automatically deploys the OpenShift
Lightspeed Service.
Procedure
1. Open the OpenShift Lightspeed OLSConfig CR file by running the following command:
2. Change the spec.ols.userDataCollection field to disable data collection for the OpenShift
Lightspeed CR.
spec.ols.userDataCollection.feedbackDisabled specifies if the Service collects your
feedback.
spec.ols.userDataCollection.transcriptsDisabled specifies if the Service collects your
chat log transcripts.
3. Save the file.
### ADDITIONAL RESOURCES
About remote health monitoring
Mirroring in disconnected environments
Filtering and redacting information
Opting out of remote health reporting
Creating the credential secret using the web console
Creating the credential secret using the CLI
Creating the Lightspeed custom resource file using the web console
Creating the Lightspeed custom resource file using the CLI
$ oc edit olsconfig cluster
apiVersion: ols.openshift.io/v1alpha1
kind: OLSConfig
metadata:
  name: cluster
spec:
  ols:
    userDataCollection:
      feedbackDisabled: true
      transcriptsDisabled: true
Red Hat OpenShift Lightspeed 1.0 About
8

---

## Lightspeed Release Notes

Red Hat OpenShift Lightspeed 1.0
Release Notes
Release Notes
Last Updated: 2026-03-20

Red Hat OpenShift Lightspeed 1.0 Release Notes
Release Notes

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
This documentation provides information about each OpenShift Lightspeed release.

. . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 
Table of Contents
## OPENSHIFT LIGHTSPEED RELEASE NOTES
### OPENSHIFT LIGHTSPEED VERSION 1.0.10
1.1.1. Enhancements
### OPENSHIFT LIGHTSPEED VERSION 1.0.9
1.2.1. Enhancements
1.2.2. Fixed issues
### OPENSHIFT LIGHTSPEED VERSION 1.0.8
1.3.1. Enhancements
1.3.2. Fixed issues
### OPENSHIFT LIGHTSPEED VERSION 1.0.7
1.4.1. Enhancements
1.4.2. Fixed issues
### OPENSHIFT LIGHTSPEED VERSION 1.0.6
1.5.1. Enhancements
1.5.2. Fixed issues
### OPENSHIFT LIGHTSPEED VERSION 1.0.5
1.6.1. Enhancements
1.6.2. Fixed issues
### OPENSHIFT LIGHTSPEED VERSION 1.0.4
1.7.1. Enhancements
1.7.2. Fixed issues
### OPENSHIFT LIGHTSPEED VERSION 1.0.3
1.8.1. Enhancements
1.8.2. Fixed issues
### OPENSHIFT LIGHTSPEED VERSION 1.0.2
1.9.1. Enhancements
1.9.2. Fixed issues
### OPENSHIFT LIGHTSPEED VERSION 1.0.1
1.10.1. Enhancements
1.10.2. Known issues
### OPENSHIFT LIGHTSPEED VERSION 1.0
1.11.1. Enhancements
1.11.2. Known issues
3
3
3
3
4
4
4
5
5
5
5
6
6
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
8
9
9
9
9
9
10
10
10
10
Table of Contents
1

Red Hat OpenShift Lightspeed 1.0 Release Notes
2
## OPENSHIFT LIGHTSPEED RELEASE NOTES
The release notes highlight what is new and what has changed with each Red Hat OpenShift Lightspeed
release.
IMPORTANT
Red Hat OpenShift Lightspeed is designed for Federal Information Processing Standards
(FIPS). When running on OpenShift Container Platform in FIPS mode, it uses the Red
Hat Enterprise Linux cryptographic libraries submitted (or planned to be submitted) to
NIST for FIPS validation on only the x86_64, ppc64le, and s390X architectures. For more
information about the NIST validation program, see Cryptographic Module Validation
Program. For the latest NIST status of the individual versions of Red Hat Enterprise Linux
cryptographic libraries that have been submitted for validation, see Product compliance.
### OPENSHIFT LIGHTSPEED VERSION 1.0.10
Red Hat OpenShift Lightspeed 1.0.10 is now available on OpenShift Container Platform 4.16 and later.
1.1.1. Enhancements
The following enhancements have been made with Red Hat OpenShift Lightspeed 1.0.10:
With this update, the PatternFly 5 User Interface supports Markdown table rendering, ensuring
consistency and compatibility across UI versions. To achieve this, the PatternFly Chatbot
extension integrates the remark-gfm plugin. As a result, you can now present and view data
more effectively.
With this update, OpenShift Lightspeed supports the rendering of Markdown tables in
responses on OpenShift Container Platform versions 4.16, 4.17 and 4.18. As a result, these
product versions now align with OpenShift Container Platform version 4.19 and later, which
already support Markdown tables in responses.
With this update, OpenShift Lightspeed introduces token management for tool calls, which
prevents OpenShift Lightspeed from exceeding the message context window size. This
enhancement aims to avoid errors due to context_window limit during tool calls, ensuring a
better user experience. It also reserves tokens for tool output, managing the amount of data
displayed in a single tool output and helping to retain a smooth conversation flow without the
need to end and restart sessions.
With this update, MCP headers and MCP definitions have changed. Previously OpenShift
Lightspeed supported three methods for the MCP client to send messages back and forth with
the MCP server: Server-Sent Events (SSE), Standard Input/Output (stdio), and Streamable
Hypertext Transfer Protocol (HTTP). Now, OpenShift Lightspeed supports only Streamable
HTTP. See OLSConfig CR requires manual update of MCP server configuration before
upgrading OpenShift Lightspeed Operator to 1.0.10 for more information.
With this update, you can enable tool filtering to control the list of tools provided to the LLM
model. To enable this feature, specify the toolFilteringConfig field in the OLSConfig custom
resource (CR) file.
### OPENSHIFT LIGHTSPEED VERSION 1.0.9
Red Hat OpenShift Lightspeed 1.0.9 is now available on OpenShift Container Platform 4.16 and later.
## OPENSHIFT LIGHTSPEED RELEASE NOTES
3

1.2.1. Enhancements
The following enhancements have been made with Red Hat OpenShift Lightspeed 1.0.9:
With this update, OpenShift Lightspeed supports automatic updates of BYO Knowledge images
that use floating tags, such as latest. If over time a BYO Knowledge image tag points to
different underlying images, OpenShift Lightspeed detects those changes and updates the
corresponding BYO Knowledge database accordingly. This feature is built using Red Hat
OpenShift ImageStream objects. OpenShift Container Platform clusters check for updates to 
ImageStream objects every 15 minutes.
Starting with this release, you can specify an array of image pull secrets in the OLSConfig
Custom Resource (CR) file. Pull secrets contain authentication credentials for remote image
registries and are used to pull RAG BYO Knowledge images from image registries requiring
authentication.
1.2.2. Fixed issues
Red Hat OpenShift Lightspeed 1.0.9 fixes the following issues:
NOTE
Some linked Jira tickets are accessible only with Red Hat credentials.
Previously, you defined replicas in the DeploymentConfig and it was not obvious which pod
each replica referred to. As a result, when you changed the replica count it was not clear which
pod should be scaled. With this release, you configure replica count with each specific
component’s deployments: APIContainer, ConsoleContainer, and DatabaseContainer. This
change allows you to specify the number of replicas for each pod. Note that the value for the
number of replicas can only be changed for APIContainer. For all other containers, the number
of replicas is fixed to 1.
OLS-2322
Previously, support for custom HTTPS credentials for UI to backend communications was
spread between two configurations: UIDeployment and TLSConfig. As a result, you had to
ensure that both places are configured, which was error prone. With this release, support for
custom HTTPS credentials for UI to backend communications is consolidated in one place, 
TLSConfig.
NOTE
If you do not provide ca.crt in the current implementation, the OpenShift
Container Platform Console proxy uses the default system trust store.
OLS-2322
Before this update, OpenShift Lightspeed failed to handle unreachable MCP servers, resulting
in loss of all tool functionality. With this release, OpenShift Lightspeed isolates the failing MCP
server, keeping other tools operational and preserving full assistant capabilities.
OLS-2393
### OPENSHIFT LIGHTSPEED VERSION 1.0.8
Red Hat OpenShift Lightspeed 1.0 Release Notes
4

Red Hat OpenShift Lightspeed 1.0.8 is now available on OpenShift Container Platform 4.16 and later.
1.3.1. Enhancements
The following enhancements have been made with Red Hat OpenShift Lightspeed 1.0.8:
With this update, it is now possible to attach the cluster details as YAML code to the OpenShift
Lightspeed prompt from the Nodes and Add-ons tabs of the Advanced Cluster Management
UI.
With this update, it is now possible to attach ApplicationSet details as YAML to the OpenShift
Lightspeed prompt from the Application details page of the Advanced Cluster Management
UI.
1.3.2. Fixed issues
Red Hat OpenShift Lightspeed 1.0.8 fixes the following issues:
NOTE
Some linked Jira tickets are accessible only with Red Hat credentials.
Before this update, if you tried to attach two alerts with the same alertname to the OpenShift
Lightspeed prompt, the second alert would replace the first alert. With this update, it is now
possible to attach multiple alerts with the same alertname to the OpenShift Lightspeed
prompt. OLS-2293
### OPENSHIFT LIGHTSPEED VERSION 1.0.7
Red Hat OpenShift Lightspeed 1.0.7 is now available on OpenShift Container Platform 4.16 and later.
1.4.1. Enhancements
The following enhancements have been made with Red Hat OpenShift Lightspeed 1.0.7:
Beginning in this release, clusters deployed with OpenShift Container Platform 4.19 and later
use PatternFly 6 styling and components from the PatternFly Chatbot extension. This change
provides a more consistent user experience across Red Hat Artificial Intelligence (AI) products.
With this release, the Red Hat-provided Operator catalogs have moved from OperatorHub to
the software catalog and the Operators navigation item is renamed to Ecosystem in the
console. The unified software catalog presents Operators, Helm charts, and other installable
content in the same console view.
To access the Red Hat-provided Operator catalogs in the console, select Ecosystem →
Software Catalog.
To manage, update, and remove installed Operators, select Ecosystem → Installed
Operators.
## OPENSHIFT LIGHTSPEED RELEASE NOTES
5

NOTE
Currently, the console only supports managing Operators by using Operator
Lifecycle Manager (OLM) Classic. If you want to use OLM v1 to install and
manage cluster extensions, such as Operators, you must use the CLI.
To manage the default or custom catalog sources, you still interact with OperatorHub
custom resource (CR) in the console or CLI.
Beginning in this release, the Service CA Operator automatically adds the OpenShift Lightspeed
default cluster CA certificate to the OpenShift Lightspeed service trust store. This change
simplifies the configuration process for Red Hat OpenShift Application Integration. This
enhancement eliminates the need for additional configuration, reducing potential errors due to
manual management and ensuring a smoother user experience during deployment.
Beginning in this release, specifying a header for an MCP server requires creating a secret to
hold the header value. Then, you must reference the secret by name in the OLSConfig custom
resource, setting the secret name as the value in the 
MCPServer.MCPServerStreamableHTTPTransport.Headers field where key is the header
name. To specify a Kubernetes token as the header, enter the string kubernetes instead of the 
Secret name. This is a breaking change that addresses a security breach that occurs when you
specify a header value in the CR MCPServer.MCPServerStreamableHTTPTransport.Headers
field that is visible in the OLSConfig config map.
With this release, it is possible to attach VirtualMachine details from the VirtualMachine details
page to your OpenShift Lightspeed prompt on the Red Hat Advanced Cluster Management for
Kubernetes User Interface.
This release introduces the ability to copy the entire OpenShift Lightspeed conversation to the
clipboard.
1.4.2. Fixed issues
Red Hat OpenShift Lightspeed 1.0.7 fixes the following issues:
Previously, an invalid model reference in the OLSConfig custom resource (CR) caused the
OpenShift Lightspeed installation to fail. Now, the OLSConfig CR health check ensures that the
model exists before use, providing error details in the console. This results in improved server
health checks, providing more precise status information to the web console, improving the user
experience. OLS-1949
### OPENSHIFT LIGHTSPEED VERSION 1.0.6
Red Hat OpenShift Lightspeed 1.0.6 is now available on OpenShift Container Platform 4.16 and later.
1.5.1. Enhancements
Red Hat OpenShift Lightspeed 1.0.6 provides the following enhancements:
This release makes Red Hat OpenShift Lightspeed 1.0.6 generally available, and is supported on
OpenShift Container Platform 4.16 and later.
Red Hat OpenShift Lightspeed 1.0 Release Notes
6

When viewing the Cluster Overview page in the Red Hat Advanced Cluster Management for
Kubernetes web console, you can attach the ManagedCluster and ManagedClusterInfo YAML
using the Attach menu.
This release introduces a check for the total size of all uploaded attachments. This is in addition
to the existing check for individual attachment sizes. The check for total size helps prevent
exceeding the context window size of the large language model (LLM) so that the model does
not receive more text than it can process in a single request.
1.5.2. Fixed issues
Red Hat OpenShift Lightspeed 1.0.6 fixes the following issues:
Before this update, when the OpenShift Lightspeed Service was connecting to Microsoft Azure
OpenAI using an additional TLS certificate, the Service was not able to successfully connect to
the large language model (LLM). Now, the Service adds the TLS certificate to the trust bundle.
As a result, the Service connects to the LLM. OLS-2112
Before this update when you disabled transcript and feedback collection, the OpenShift
Lightspeed Service failed to deploy the Model Context Protocol (MCP) server. Now, when you
disable transcript and feedback collection, the Service deploys the MCP server. OLS-2113
### OPENSHIFT LIGHTSPEED VERSION 1.0.5
Red Hat OpenShift Lightspeed 1.0.5 is now available on OpenShift Container Platform 4.16 and later.
1.6.1. Enhancements
Red Hat OpenShift Lightspeed 1.0.5 provides the following enhancements:
This release makes Red Hat OpenShift Lightspeed 1.0.5 generally available, and is supported on
OpenShift Container Platform 4.16 and later.
With this release, you can attach cron job logs to the question you submit to Red Hat
OpenShift Lightspeed, enabling the Service to provide more context-aware troubleshooting,
diagnostics, and analysis of automated job outcomes, errors, or irregular behaviors.
1.6.2. Fixed issues
Red Hat OpenShift Lightspeed 1.0.5 fixes the following issues:
Before this update, Red Hat OpenShift Lightspeed failed to use the GPT-5 model, and OpenAI
generated an Error code: 400 error message. With this release, Red Hat OpenShift Lightspeed
supports the GPT-5 model. OLS-2041
### OPENSHIFT LIGHTSPEED VERSION 1.0.4
Red Hat OpenShift Lightspeed 1.0.4 is now available on OpenShift Container Platform 4.16 and later.
1.7.1. Enhancements
Red Hat OpenShift Lightspeed 1.0.4 provides the following enhancements:
## OPENSHIFT LIGHTSPEED RELEASE NOTES
7

1
This release makes Red Hat OpenShift Lightspeed 1.0.4 generally available, and is supported on
OpenShift Container Platform 4.16 and later.
The cluster interaction Technology Preview feature now uses the Model Context Protocol
(MCP). MCP is an open protocol that standardizes how applications provide context to large
language models (LLMs). Using the protocol, an MCP server offers a standardized way for an
LLM to increase context by requesting and receiving real-time information from external
resources.
1.7.2. Fixed issues
Red Hat OpenShift Lightspeed 1.0.4 fixes the following issues:
Before this update, the OpenShift Lightspeed Service incorrectly rejected the api_version
parameter for Microsoft Azure OpenAI providers, despite it being officially supported. This
resulted in Extra inputs are not permitted errors during API calls.
This release fixes the Microsoft Azure OpenAI API version validation issue in the OpenShift
Lightspeed Service, which now allows for correct parameter usage and eliminates the error.
OLS-1973
### OPENSHIFT LIGHTSPEED VERSION 1.0.3
Red Hat OpenShift Lightspeed 1.0.3 is now available on OpenShift Container Platform 4.15 and later.
1.8.1. Enhancements
The following enhancements are made with Red Hat OpenShift Lightspeed 1.0.3:
This release makes Red Hat OpenShift Lightspeed 1.0.3 generally available, and is supported on
OpenShift Container Platform 4.15 and later.
With this update, when you specify BYO Knowledge information sources in 
OLSConfig.spec.ols.rag specification file you only have to specify the URL path for the image.
The custom resource (CR) uses the following default settings:
Example configuration
Where image specifies the tag for the image that was pushed to the image registry so
that the OpenShift Lightspeed Operator can access the custom content. The OpenShift
Lightspeed Operator can work with more than one RAG database that you create.
Previously, the indexPath and indexID parameters were required. Now they are optional. The
default values for the parameters are /rag/vector_db and vector_db_index respectively.
1.8.2. Fixed issues
The following issues are fixed with Red Hat OpenShift Lightspeed 1.0.3:
spec:
  ols:
    rag:
      - image: quay.io/<username>/my-byok-image:latest 1
Red Hat OpenShift Lightspeed 1.0 Release Notes
8

Before this update, the OpenShift Lightspeed service could not reload BYO Knowledge images
with floating tags. This condition occurred even after a restart because the ImagePullPolicy
parameter was hardcoded to PullIfNotPresent. As a result, BYO Knowledge images failed to
reload from the container registry, causing users to experience stale images. With this update,
when the OpenShift Lightspeed service restarts, it pulls the BYO Knowledge images from the
container registry, ensuring consistent access to BYO Knowledge images. OLS-1956.
### OPENSHIFT LIGHTSPEED VERSION 1.0.2
Red Hat OpenShift Lightspeed 1.0.2 is now available on OpenShift Container Platform 4.15 and later.
1.9.1. Enhancements
The following enhancements are made with Red Hat OpenShift Lightspeed 1.0.2:
This release makes Red Hat OpenShift Lightspeed 1.0.2 generally available, and is supported on
OpenShift Container Platform 4.15 and later.
1.9.2. Fixed issues
The following issues are fixed with Red Hat OpenShift Lightspeed 1.0.2:
Before this update, the cluster-wide proxy incorrectly routed large language model (LLM)
provider connections, ignoring the no_proxy environment variable. This led to end users
experiencing OpenShift Lightspeed attempting to connect to the LLM provider through a
proxy when the no_proxy variable was explicitly defined. In this release, OpenShift Lightspeed
now respects the no_proxy environment variable for LLM provider connections. As a result,
OpenShift Lightspeed ignores proxy settings when no_proxy is set, enhancing direct LLM
provider connections. OLS-1861.
### OPENSHIFT LIGHTSPEED VERSION 1.0.1
Red Hat OpenShift Lightspeed 1.0.1 is now available on OpenShift Container Platform 4.15 and later.
1.10.1. Enhancements
The following enhancements are made with Red Hat OpenShift Lightspeed 1.0.1:
This release makes Red Hat OpenShift Lightspeed 1.0.1 generally available, and is supported on
OpenShift Container Platform 4.15 and later.
This release introduces the BYO Knowledge tool as a Technology Preview feature. You can use
this tool to add your own custom content as a knowledge source so that a large language model
(LLM) can make use of information that is unique to your environment. By using this
information, the tool creates a retrieval-augmented generation (RAG) database to enhance the
knowledge that is available to the LLM.
## OPENSHIFT LIGHTSPEED RELEASE NOTES
9

IMPORTANT
The BYO Knowledge tool is a Technology Preview feature only. Technology
Preview features are not supported with Red Hat production service level
agreements (SLAs) and might not be functionally complete. Red Hat does not
recommend using them in production. These features provide early access to
upcoming product features, enabling customers to test functionality and provide
feedback during the development process.
For more information about the support scope of Red Hat Technology Preview
features, see Technology Preview Features Support Scope .
1.10.2. Known issues
The following issues are identified with Red Hat OpenShift Lightspeed 1.0.1:
If the OpenShift Container Platform cluster has a cluster-wide proxy and the no_proxy
environment variable is set, the OpenShift Lightspeed service tries to connect to the large
language model (LLM) provider through the proxy. OLS-1861.
Workaround: None.
### OPENSHIFT LIGHTSPEED VERSION 1.0
Red Hat OpenShift Lightspeed 1.0 is now available on OpenShift Container Platform 4.15 and later.
1.11.1. Enhancements
The following enhancements are made with Red Hat OpenShift Lightspeed 1.0:
This release makes Red Hat OpenShift Lightspeed 1.0 generally available, adds new features,
addresses Common Vulnerabilities and Exposures (CVEs), and is supported on OpenShift
Container Platform 4.15 and later.
This release includes the following Technology Preview features:
Cluster interaction
PostgreSQL persistence
Token quota
IMPORTANT
Technology Preview features are not supported with Red Hat production service
level agreements (SLAs) and might not be functionally complete. Red Hat does
not recommend using them in production. These features provide early access to
upcoming product features, enabling customers to test functionality and provide
feedback during the development process.
For more information about the support scope of Red Hat Technology Preview
features, see Technology Preview Features Support Scope .
1.11.2. Known issues
The following issues are identified with Red Hat OpenShift Lightspeed 1.0:
Red Hat OpenShift Lightspeed 1.0 Release Notes
10

For OpenShift Container Platform 4.17 and later, the OpenShift Lightspeed icon disappears
when you click Create Namespace or Create Project from one of the following locations in the
OpenShift Container Platform web console:
Administration → Namespaces
Home → Projects → Create Project
The Project drop-down menu located at the top of most pages
Workaround: Refresh the web browser and the OpenShift Lightspeed icon appears. OLS-1815.
Changing the value of the quota parameter in the 
spec.ols.quotaHandlersConfig.limitersConfig specification of the OLSConfig custom
resource file does not take effect until the currently defined quota period expires.
Workaround: Delete the OpenShift Lightspeed Operator. Ensure that any operand pods that
the OpenShift Lightspeed Operator manages, and the Persistent Volume Claim lightspeed-
postgres-pvc associated with the postgres pod are also deleted. Then, install the OpenShift
Lightspeed Operator again. OLS-1826
After restarting the postgres pod, the OpenShift Lightspeed service pod fails to connect to the
postgres pod.
Workaround: Restart the OpenShift Lightspeed service pod so that the service pod connects to
the postgres pod. OLS-1835.
## OPENSHIFT LIGHTSPEED RELEASE NOTES
11

---

## Lightspeed Install

Red Hat OpenShift Lightspeed 1.0
Install
Installing OpenShift Lightspeed
Last Updated: 2026-02-18

Red Hat OpenShift Lightspeed 1.0 Install
Installing OpenShift Lightspeed

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
This documentation provides information about installing OpenShift Lightspeed.

. . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 
Table of Contents
## INSTALLING OPENSHIFT LIGHTSPEED
### LARGE LANGUAGE MODEL (LLM) OVERVIEW
1.1.1. Red Hat Enterprise Linux AI with OpenShift Lightspeed
1.1.2. Red Hat OpenShift AI with OpenShift Lightspeed
1.1.3. IBM watsonx with OpenShift Lightspeed
1.1.4. OpenAI with OpenShift Lightspeed
1.1.5. Microsoft Azure OpenAI with OpenShift Lightspeed
### ABOUT SUBSCRIPTION REQUIREMENTS
### ABOUT ADDING OPERATORS TO A CLUSTER
1.3.1. Installing the OpenShift Lightspeed Operator from the OperatorHub
1.3.2. Installing the OpenShift Lightspeed Operator from the software catalog
3
3
3
3
3
3
3
4
4
4
5
Table of Contents
1

Red Hat OpenShift Lightspeed 1.0 Install
2
## INSTALLING OPENSHIFT LIGHTSPEED
Enable OpenShift Lightspeed in your cluster by installing the OpenShift Lightspeed Operator and
configuring the Service to interact with a large language model (LLM) provider.
### LARGE LANGUAGE MODEL (LLM) OVERVIEW
Learn how the OpenShift Lightspeed Service uses large language models (LLMs) to generate
intelligent, context-aware answers to your questions about OpenShift Container Platform.
You can configure Red Hat Enterprise Linux AI or Red Hat OpenShift AI as the LLM provider for the
OpenShift Lightspeed Service. Either LLM provider can use a server or inference service that processes
inference queries.
Alternatively, you can connect the OpenShift Lightspeed Service to a publicly available LLM provider,
such as IBM watsonx, OpenAI, or Microsoft Azure OpenAI.
NOTE
Installing the OpenShift Lightspeed Operator does not install an LLM provider. You must
have an LLM provider available for use before you install the OpenShift Lightspeed
Operator.
1.1.1. Red Hat Enterprise Linux AI with OpenShift Lightspeed
You can use Red Hat Enterprise Linux AI to host an LLM.
For more information, see Generating a custom LLM by using RHEL AI .
1.1.2. Red Hat OpenShift AI with OpenShift Lightspeed
You can use Red Hat OpenShift AI to host an LLM.
For more information, see About model serving.
1.1.3. IBM watsonx with OpenShift Lightspeed
To configure IBM watsonx as the LLM provider, you need an IBM Cloud project with access to IBM
watsonx. You also need your IBM watsonx API key.
For more information, see the official IBM watsonx product documentation.
1.1.4. OpenAI with OpenShift Lightspeed
To configure OpenAI as the LLM provider with OpenShift Lightspeed, you need either the OpenAI API
key or the OpenAI project name during the configuration process.
The OpenAI Service has a feature for projects and service accounts. You can use a service account in a
dedicated project so that you can precisely track OpenShift Lightspeed usage.
For more information, see the official OpenAI product documentation.
1.1.5. Microsoft Azure OpenAI with OpenShift Lightspeed
## INSTALLING OPENSHIFT LIGHTSPEED
3

To configure Microsoft Azure OpenAI as the LLM provider, you need a Microsoft Azure OpenAI Service
instance. You must have at least one model deployment in Microsoft Azure OpenAI Studio for that
instance.
For more information, see the official Microsoft Azure OpenAI product documentation.
### ABOUT SUBSCRIPTION REQUIREMENTS
Review the supported OpenShift Container Platform product subscriptions and account requirements
necessary to activate and use the OpenShift Lightspeed Service in your environment.
Red Hat OpenShift Lightspeed requires a valid and active subscription to one of the listed products.
Red Hat OpenShift Kubernetes Engine
Red Hat OpenShift Virtualization Engine
OpenShift Container Platform
Red Hat OpenShift Platform Plus
### ABOUT ADDING OPERATORS TO A CLUSTER
Use the software catalog or OperatorHub to install Operators on your OpenShift Container Platform
cluster. These tools use the Operator Lifecycle Manager (OLM) to manage your software.
1.3.1. Installing the OpenShift Lightspeed Operator from the OperatorHub
Install the OpenShift Lightspeed Operator so that you can configure the OpenShift Lightspeed Service.
Prerequisites
You have deployed OpenShift Container Platform 4.16 to 4.19.
You have logged in to the OpenShift Container Platform web console as a user with the 
cluster-admin role.
You have successfully configured your large language model (LLM) provider so that OpenShift
Lightspeed can communicate with it.
Procedure
1. In the OpenShift Container Platform web console, navigate to the Operators → OperatorHub
page.
2. Search for OpenShift Lightspeed.
3. Locate the OpenShift Lightspeed Operator, and click to select it.
4. When the prompt that discusses the community operator displays, click Continue.
5. Click Install.
6. Use the default installation settings presented, and click Install to continue.
Red Hat OpenShift Lightspeed 1.0 Install
4

7. Click Operators → Installed Operators to verify the OpenShift Lightspeed Operator
installation. Succeeded displays in the Status column.
1.3.2. Installing the OpenShift Lightspeed Operator from the software catalog
Install the OpenShift Lightspeed Operator so that you can configure the OpenShift Lightspeed Service.
Prerequisites
You have deployed OpenShift Container Platform 4.20 or later.
You have logged in to the OpenShift Container Platform web console as a user with the 
cluster-admin role.
You have successfully configured your large language model (LLM) provider so that OpenShift
Lightspeed can communicate with it.
Procedure
1. In the OpenShift Container Platform web console, navigate to the Ecosystem → Software
Catalog page.
2. Click the Project drop-down list, and enable the toggle switch to show default projects.
3. Enter openshift-marketplace in the search field.
4. Click to select openshift-marketplace.
5. Search for OpenShift Lightspeed.
6. Locate the OpenShift Lightspeed Operator, and click to select it.
7. When the prompt that discusses the OpenShift Lightspeed Operator displays, click Install.
8. Use the default installation settings presented, and click Install to continue.
9. Click Ecosystem → Installed Operators to verify the OpenShift Lightspeed Operator
installation. Succeeded displays in the Status column.
Additional resources
About Operator installation with OperatorHub
About Operator installation with software catalog
## INSTALLING OPENSHIFT LIGHTSPEED
5

---

## Lightspeed Configure

Red Hat OpenShift Lightspeed 1.0
Configure
Configuring OpenShift Lightspeed
Last Updated: 2026-03-24

Red Hat OpenShift Lightspeed 1.0 Configure
Configuring OpenShift Lightspeed

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
This documentation provides information about configuring OpenShift Lightspeed.

. . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 
. . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 
Table of Contents
## CONFIGURING AND DEPLOYING OPENSHIFT LIGHTSPEED
### CREATING THE CREDENTIALS SECRET BY USING THE WEB CONSOLE
### CREATING THE OPENSHIFT LIGHTSPEED CUSTOM RESOURCE FILE BY USING THE WEB CONSOLE
1.2.1. Configuring custom TLS certificates
### CREATING THE CREDENTIALS SECRET BY USING THE CLI
### CREATING THE OPENSHIFT LIGHTSPEED CUSTOM RESOURCE FILE BY USING THE CLI
1.4.1. Support for trusted-ca certificates and LLM providers
1.4.2. Configuring OpenShift Lightspeed with a trusted CA certificate for the LLM
### VERIFYING THE OPENSHIFT LIGHTSPEED DEPLOYMENT
### ABOUT OPENSHIFT LIGHTSPEED AND ROLE-BASED ACCESS CONTROL (RBAC)
1.6.1. Granting a user access by using the CLI
1.6.2. Granting a user access by using a YAML configuration file
1.6.3. Granting a user group access by using the CLI
1.6.4. Granting a user group access by using a YAML configuration file
### FILTERING AND REDACTING INFORMATION
### ABOUT THE BYO KNOWLEDGE TOOL
1.8.1. About document title and URL
1.8.2. Providing custom knowledge to the LLM
1.8.3. Disabling the OpenShift Container Platform documentation retrieval-augmented generation (RAG)
database
### ABOUT CLUSTER INTERACTION
1.9.1. Enabling cluster interaction
1.9.2. Enabling a custom MCP server
### TOKENS AND TOKEN QUOTA LIMITS
1.10.1. Activating token quota limits
### ABOUT OPENSHIFT LIGHTSPEED AND POSTGRESQL PERSISTENCE
1.11.1. Enabling PostgreSQL persistence
1.11.2. Overriding default Persistent Volume Claim (PVC) specifications
### ABOUT QUERY-BASED TOOL FILTERING
1.12.1. Enabling query-based tool filtering
## OLSCONFIG API REFERENCE
### OLSCONFIG API SPECIFICATIONS
2.1.1. .metadata
2.1.2. .spec
2.1.3. .spec.llm
2.1.4. .spec.llm.providers
2.1.5. .spec.llm.providers[]
2.1.6. .spec.llm.providers[].credentialsSecretRef
2.1.7. .spec.llm.providers[].models
2.1.8. .spec.llm.providers[].models[]
2.1.9. .spec.llm.providers[].models[].parameters
2.1.10. .spec.llm.providers[].tlsSecurityProfile
2.1.11. .spec.mcpServers
2.1.12. .spec.mcpServers[]
2.1.13. .spec.mcpServers[].streamableHTTP
2.1.14. .spec.ols
2.1.15. .spec.ols.additionalCAConfigMapRef
2.1.16. .spec.ols.conversationCache
2.1.17. .spec.ols.conversationCache.postgres
2.1.18. .spec.ols.deployment
4
4
6
10
11
13
16
17
18
19
19
20
21
21
22
23
24
24
27
28
29
30
31
31
33
33
34
35
35
38
38
39
39
40
40
40
41
41
42
42
42
47
47
48
48
49
50
50
51
Table of Contents
1

2.1.19. .spec.ols.deployment.api
2.1.20. .spec.ols.deployment.api.resources
2.1.21. .spec.ols.deployment.api.resources.claims
2.1.22. .spec.ols.deployment.api.resources.claims[]
2.1.23. .spec.ols.deployment.api.tolerations
2.1.24. .spec.ols.deployment.api.tolerations[]
2.1.25. .spec.ols.deployment.console
2.1.26. .spec.ols.deployment.console.resources
2.1.27. .spec.ols.deployment.console.resources.claims
2.1.28. .spec.ols.deployment.console.resources.claims[]
2.1.29. .spec.ols.deployment.console.tolerations
2.1.30. .spec.ols.deployment.console.tolerations[]
2.1.31. .spec.ols.deployment.dataCollector
2.1.32. .spec.ols.deployment.dataCollector.resources
2.1.33. .spec.ols.deployment.dataCollector.resources.claims
2.1.34. .spec.ols.deployment.dataCollector.resources.claims[]
2.1.35. .spec.ols.deployment.database
2.1.36. .spec.ols.deployment.database.resources
2.1.37. .spec.ols.deployment.database.resources.claims
2.1.38. .spec.ols.deployment.database.resources.claims[]
2.1.39. .spec.ols.deployment.database.tolerations
2.1.40. .spec.ols.deployment.database.tolerations[]
2.1.41. .spec.ols.deployment.mcpServer
2.1.42. .spec.ols.deployment.mcpServer.resources
2.1.43. .spec.ols.deployment.mcpServer.resources.claims
2.1.44. .spec.ols.deployment.mcpServer.resources.claims[]
2.1.45. .spec.ols.proxyConfig
2.1.46. .spec.ols.proxyConfig.proxyCACertificate
2.1.47. .spec.ols.queryFilters
2.1.48. .spec.ols.queryFilters[]
2.1.49. .spec.ols.quotaHandlersConfig
2.1.50. .spec.ols.quotaHandlersConfig.limitersConfig
2.1.51. .spec.ols.quotaHandlersConfig.limitersConfig[]
2.1.52. .spec.ols.rag
2.1.53. .spec.ols.rag[]
2.1.54. .spec.ols.storage
2.1.55. .spec.ols.tlsConfig
2.1.56. .spec.ols.tlsConfig.keyCertSecretRef
2.1.57. .spec.ols.tlsSecurityProfile
2.1.58. .spec.ols.userDataCollection
2.1.59. .spec.olsDataCollector
51
52
53
53
53
53
54
55
56
56
57
57
58
58
59
59
60
60
61
61
62
62
63
63
64
64
65
65
66
66
66
67
67
67
68
68
68
69
69
74
74
Red Hat OpenShift Lightspeed 1.0 Configure
2

Table of Contents
3
## CONFIGURING AND DEPLOYING OPENSHIFT
LIGHTSPEED
Configure and deploy the OpenShift Lightspeed Service to enable AI-powered assistance in your
OpenShift Container Platform cluster.
NOTE
The instructions assume that you are installing OpenShift Lightspeed using the 
kubeadmin user account. If you are using a regular user account with cluster-admin
privileges, read the section of the documentation that discusses Role-Based Access
Control (RBAC).
First, create a credential secret using the credentials for your large language model (LLM) provider.
Next, create the OLSConfig custom resource (CR) that the Operator uses to deploy the Service.
Finally, verify that the OpenShift Lightspeed Service is operating.
IMPORTANT
Starting with OpenShift Container Platform 4.19, the perspectives in the web console are
unified. The Developer perspective is no longer enabled by default.
All users can interact with all OpenShift Container Platform web console features.
However, if you are not the cluster owner, you might need to request permission to
certain features from the cluster owner.
You can still enable the Developer perspective. On the Getting Started pane in the web
console, you can take a tour of the console, find information on setting up your cluster,
view a quick start for enabling the Developer perspective, and follow links to explore new
features and capabilities.
### CREATING THE CREDENTIALS SECRET BY USING THE WEB
CONSOLE
Use the OpenShift Container Platform web console to store the API token that OpenShift Lightspeed
uses to authenticate with the large language model (LLM) provider.
As another option, Microsoft Azure also supports authentication by using Microsoft Entra ID.
Prerequisites
You have logged in to the OpenShift Container Platform web console as a user with the 
cluster-admin role. As another option, you can log in to a user account that has permission to
create a secret to store the Provider tokens.
You have installed the OpenShift Lightspeed Operator.
Procedure
1. Click the Quick create ( 
 ) menu in the upper-right corner of the OpenShift web console and
select Import YAML.
Red Hat OpenShift Lightspeed 1.0 Configure
4

2. Paste the YAML content for your LLM provider into the text area of the web console.
NOTE
The YAML parameter is always apitoken regardless of what the LLM provider
calls the access details.
a. Use the following example to create the Secret to provide OpenShift Lightspeed with the
OpenAI API key.
stringData.apitoken represents the API token, and is not base64 encoded.
b. Use the following example to create the Secret to provide OpenShift Lightspeed with the
Red Hat Enterprise Linux AI key.
stringData.apitoken represents the API token. The token must be base64 encoded
when stored in a secret.
c. Use the following example to create the Secret to provide OpenShift Lightspeed with the
Red Hat OpenShift AI key.
stringData.apitoken represents the API token. The token must be base64 encoded
when stored in a secret.
d. Use the following example to create the Secret to provide OpenShift Lightspeed with the
IBM watsonx key.
apiVersion: v1
kind: Secret
metadata:
  name: openai
  namespace: openshift-lightspeed
type: Opaque
stringData:
  apitoken: <api_token>
apiVersion: v1
stringData:
  apitoken: <api_token>
kind: Secret
metadata:
  name: rhelai-api-keys
  namespace: openshift-lightspeed
type: Opaque
apiVersion: v1
kind: Secret
metadata:
  name: rhoai-api-keys
  namespace: openshift-lightspeed
type: Opaque
stringData:
  apitoken: <api_token>
## CONFIGURING AND DEPLOYING OPENSHIFT LIGHTSPEED
5

stringData.apitoken represents the API token. The token must be base64 encoded
when stored in a secret.
e. Use the following example to create the Secret to provide OpenShift Lightspeed with the
Microsoft Azure OpenAI key.
stringData.apitoken represents the API token. The token must be base64 encoded
when stored in a secret.
f. Optional: As another option with Microsoft Azure OpenAI you can use Microsoft Entra ID to
authenticate your LLM provider. Microsoft Entra ID users must configure the required roles
for their Microsoft Azure OpenAI resource. For more information, see the official Microsoft
Cognitive Services OpenAI Contributor(Microsoft Azure OpenAI Service documentation).
Use the following example to authenticate by using Microsoft Entra ID.
data.apitoken represents the API token. The token must be base64 encoded when
stored in a secret.
3. Click Create.
### CREATING THE OPENSHIFT LIGHTSPEED CUSTOM RESOURCE
FILE BY USING THE WEB CONSOLE
Use the OpenShift Container Platform web console to create the custom resource (CR) file required to
deploy OpenShift Lightspeed.
apiVersion: v1
kind: Secret
metadata:
  name: watsonx-api-keys
  namespace: openshift-lightspeed
type: Opaque
stringData:
  apitoken: <api_token>
apiVersion: v1
kind: Secret
metadata:
  name: azure-api-keys
  namespace: openshift-lightspeed
type: Opaque
stringData:
  apitoken: <api_token>
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
Red Hat OpenShift Lightspeed 1.0 Configure
6

The specific content of the CR file is unique for each large language model (LLM) provider. Choose the
configuration file for the LLM provider that you are using.
Prerequisites
You have logged in to the OpenShift Container Platform web console as a user with the 
cluster-admin role. As another option, you have logged in to a user account that has permission
to create a cluster-scoped CR file.
You have an LLM provider available for use with the OpenShift Lightspeed Service.
You have installed the OpenShift Lightspeed Operator.
Procedure
1. Click the Quick create ( 
 ) menu in the upper-right corner of the OpenShift web console and
select Import YAML.
2. Paste the YAML content for your LLM provider into the text area of the web console.
a. Use the following example to create the OLSConfig CR to configure OpenShift Lightspeed
with your OpenAI provider:
spec.ols.hideIcon specifies whether to hide the OpenShift Lightspeed icon in the
OpenShift Container Platform web console. The default value is false. Set this field to 
true to hide the icon from users, such as those who do not have access to the
OpenShift Lightspeed API.
b. Use the following example to create the OLSConfig CR to configure OpenShift Lightspeed
with your Red Hat Enterprise Linux AI provider:
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
apiVersion: ols.openshift.io/v1alpha1
kind: OLSConfig
metadata:
  name: cluster
spec:
## CONFIGURING AND DEPLOYING OPENSHIFT LIGHTSPEED
7

spec.llm.providers.credentialsSecretRef.name specifies the name of the Secret that
has the API key for the provider. By default, the Red Hat Enterprise Linux AI API key
requires a token as part of the request. If your Red Hat Enterprise Linux AI endpoint
does not require a token, you must still set the token value to any valid string for the
request to authenticate.
spec.llm.providers.url specifies the URL endpoint for the provider. The URL must end
with v1 to be valid. For example, https://3.23.103.8:8000/v1.
spec.ols.hideIcon specifies whether to hide the OpenShift Lightspeed icon in the
OpenShift Container Platform web console. The default value is false. Set this field to 
true to hide the icon from users, such as those who do not have access to the
OpenShift Lightspeed API.
c. Use the following example to create the OLSConfig CR to configure OpenShift Lightspeed
with your Red Hat OpenShift AI provider:
spec.llm.providers.credentialsSecretRef.name specifies the name of the Secret that
has the API key for the provider. If your provider configuration does not require a token,
you must still provide a Secret containing a valid string for the request to authenticate.
ll
id
l
ifi
th URL
d
i t f
th
id
Th URL
t
d
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
Red Hat OpenShift Lightspeed 1.0 Configure
8

spec.llm.providers.url specifies the URL endpoint for the provider. The URL must end
with v1 to be valid. For example, https://<model_name>.
<domain_name>.com:443/v1.
spec.ols.hideIcon specifies whether to hide the OpenShift Lightspeed icon in the
OpenShift Container Platform web console. The default value is false. Set this field to 
true to hide the icon from users, such as those who do not have access to the
OpenShift Lightspeed API.
d. Use the following example to create the OLSConfig CR to configure OpenShift Lightspeed
with your Microsoft Azure OpenAI provider:
spec.ols.hideIcon specifies whether to hide the OpenShift Lightspeed icon in the
OpenShift Container Platform web console. The default value is false. Set this field to 
true to hide the icon from users, such as those who do not have access to the
OpenShift Lightspeed API.
e. Use the following example to create the OLSConfig CR to configure OpenShift Lightspeed
with your IBM watsonx provider:
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
## CONFIGURING AND DEPLOYING OPENSHIFT LIGHTSPEED
9

spec.ols.hideIcon specifies whether to hide the OpenShift Lightspeed icon in the
OpenShift Container Platform web console. The default value is false. Set this field to 
true to hide the icon from users, such as those who do not have access to the
OpenShift Lightspeed API.
3. Click Create.
1.2.1. Configuring custom TLS certificates
Use the OpenShift Container Platform web console to configure custom TLS certificates for secure
communication between OpenShift Lightspeed and your service endpoints.
Prerequisites
You have logged in to the OpenShift Container Platform web console as a user with the 
cluster-admin role. As another option, you have logged in to a user account that has permission
to create or edit the OLSConfig custom resource (CR).
You have a large language model (LLM) provider.
You have installed the OpenShift Lightspeed Operator.
You have created the credentials secret and the OLSconfig CR.
Procedure
1. In the OpenShift Container Platform web console, click Operators → Installed Operators.
2. Select All Projects in the Project dropdown at the top of the screen.
3. Click OpenShift Lightspeed Operator.
4. Click OLSConfig, then click the cluster configuration instance in the list.
5. Click the YAML tab.
6. Update the OLSconfig CR to contain the file that has the TLS secret.
  ols:
    hideIcon: true
    defaultModel: ibm/<model_name>
    defaultProvider: myWatsonx
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
Red Hat OpenShift Lightspeed 1.0 Configure
10

spec.ols.tlsConfig.keyCertSecretRef.name specifies the secret that has the tls.crt and 
tls.key file.
apiVersion.data.tls specifies that the name of the certificate must be tls.crt and the name
of the key must be tls.key.
7. Click Save.
Verification
1. Verify that a new pod exists in the lightspeed-app-server deployment by running the following
command:
### CREATING THE CREDENTIALS SECRET BY USING THE CLI
Use the command line interface to store the API token that OpenShift Lightspeed uses to authenticate
with the large language model (LLM) provider.
Alternatively, Microsoft Azure also supports authentication using Microsoft Entra ID.
Prerequisites
You have access to the OpenShift CLI (oc) as a user with the cluster-admin role. Alternatively,
you are logged in to a user account that has permission to create a secret to store the Provider
tokens.
You have installed the OpenShift Lightspeed Operator.
Procedure
1. Create a YAML file that has the credential secret for the LLM provider that you are using.
NOTE
The YAML parameter is always apitoken regardless of what the LLM provider
calls the access details.
a. Use the following example to create the Secret to provide OpenShift Lightspeed with the
OpenAI API key.
  tls.crt: LS0tLS1CRUd...
  tls.key: LS0tLS1CRUd...
kind: Secret
metadata:
  name: <lightspeed_tls>
  namespace: <openshift_lightspeed>
$ oc get pod -n openshift-lightspeed
apiVersion: v1
kind: Secret
metadata:
  name: openai
## CONFIGURING AND DEPLOYING OPENSHIFT LIGHTSPEED
11

stringData.apitoken represents the API token. The token must be base64 encoded
when stored in a secret.
b. Use the following example to create the Secret to provide OpenShift Lightspeed with the
Red Hat OpenShift AI key.
stringData.apitoken represents the API token. The token must be base64 encoded
when stored in a secret.
c. Use the following example to create the Secret to provide OpenShift Lightspeed with the
IBM watsonx key.
stringData.apitoken represents the API token. The token must be base64 encoded
when stored in a secret.
d. Use the following example to create the Secret to provide OpenShift Lightspeed with the
Microsoft Azure OpenAI key.
stringData.apitoken represents the API token. The token must be base64 encoded
when stored in a secret.
e. Optional: As another option with Microsoft Azure OpenAI you can use Microsoft Entra ID to
authenticate your LLM provider. Microsoft Entra ID users must configure the required roles
  namespace: openshift-lightspeed
type: Opaque
stringData:
  apitoken: <api_token>
apiVersion: v1
kind: Secret
metadata:
  name: rhoai-api-keys
  namespace: openshift-lightspeed
type: Opaque
stringData:
  apitoken: <api_token>
apiVersion: v1
kind: Secret
metadata:
  name: watsonx-api-keys
  namespace: openshift-lightspeed
type: Opaque
stringData:
  apitoken: <api_token>
apiVersion: v1
kind: Secret
metadata:
  name: azure-api-keys
  namespace: openshift-lightspeed
type: Opaque
stringData:
  apitoken: <api_token>
Red Hat OpenShift Lightspeed 1.0 Configure
12

for their Microsoft Azure OpenAI resource. For more information, see the official Microsoft
Cognitive Services OpenAI Contributor(Microsoft Azure OpenAI Service documentation).
Use the following example to authenticate by using Microsoft Entra ID.
data.apitoken represents the API token. The token must be base64 encoded when
stored in a secret.
2. Create the Secret by running the following command:
### CREATING THE OPENSHIFT LIGHTSPEED CUSTOM RESOURCE
FILE BY USING THE CLI
Use the command line interface to create the custom resource (CR) file required to deploy OpenShift
Lightspeed.
The specific content of the CR file is unique for each large language model (LLM) provider. Choose the
configuration file for the LLM provider that you are using.
Prerequisites
You have access to the OpenShift CLI (oc) and have logged in as a user with the cluster-admin
role. As another option, you have logged in to a user account that has permission to create a
cluster-scoped CR file.
You have an LLM provider available for use with the OpenShift Lightspeed Service.
You have installed the OpenShift Lightspeed Operator.
Procedure
1. Create an OLSConfig file that has the YAML content for the LLM provider you use.
a. Use the following example to create the OLSConfig CR to configure OpenShift Lightspeed
with your OpenAI provider:
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
$ oc create -f /path/to/secret.yaml
apiVersion: ols.openshift.io/v1alpha1
kind: OLSConfig
metadata:
  name: cluster
spec:
  llm:
## CONFIGURING AND DEPLOYING OPENSHIFT LIGHTSPEED
13

spec.ols.hideIcon specifies whether to hide the OpenShift Lightspeed icon in the
OpenShift Container Platform web console. The default value is false. Set this field to 
true to hide the icon from users, such as those who do not have access to the
OpenShift Lightspeed API.
b. Use the following example to create the OLSConfig CR to configure OpenShift Lightspeed
with your Red Hat Enterprise Linux AI provider:
spec.llm.providers.credentialsSecretRef.name specifies the name of the Secret that
has the API key for the provider. By default, the Red Hat Enterprise Linux AI API key
requires a token as part of the request. If your Red Hat Enterprise Linux AI endpoint
does not require a token, you must still set the token value to any valid string for the
request to authenticate.
spec.llm.providers.url specifies the URL endpoint for the provider. The URL must end
with v1 to be valid. For example, https://3.23.103.8:8000/v1.
spec.ols.hideIcon specifies whether to hide the OpenShift Lightspeed icon in the
OpenShift Container Platform web console. The default value is false. Set this field to 
true to hide the icon from users, such as those who do not have access to the
OpenShift Lightspeed API.
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
Red Hat OpenShift Lightspeed 1.0 Configure
14

c. Use the following example to create the OLSConfig CR to configure OpenShift Lightspeed
with your Red Hat OpenShift AI provider:
spec.llm.providers.credentialsSecretRef.name specifies the name of the Secret that
has the API key for the provider. By default, the Red Hat OpenShift AI API key requires
a token as part of the request. If your Red Hat OpenShift AI endpoint does not require a
token, you must still set the token value to any valid string for the request to
authenticate.
spec.llm.providers.url specifies the URL endpoint for the provider. The URL must end
with v1 to be valid. For example, https://<model_name>.
<domain_name>.com:443/v1.
spec.ols.hideIcon specifies whether to hide the OpenShift Lightspeed icon in the
OpenShift Container Platform web console. The default value is false. Set this field to 
true to hide the icon from users, such as those who do not have access to the
OpenShift Lightspeed API.
d. Use the following example to create the OLSConfig CR to configure OpenShift Lightspeed
with your Microsoft Azure OpenAI provider:
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
## CONFIGURING AND DEPLOYING OPENSHIFT LIGHTSPEED
15

spec.ols.hideIcon specifies whether to hide the OpenShift Lightspeed icon in the
OpenShift Container Platform web console. The default value is false. Set this field to 
true to hide the icon from users, such as those who do not have access to the
OpenShift Lightspeed API.
e. Use the following example to create the OLSConfig CR to configure OpenShift Lightspeed
with your IBM watsonx provider:
spec.ols.hideIcon specifies whether to hide the OpenShift Lightspeed icon in the
OpenShift Container Platform web console. The default value is false. Set this field to 
true to hide the icon from users, such as those who do not have access to the
OpenShift Lightspeed API.
2. Run the following command so that the Operator deploys OpenShift Lightspeed using the
information in the YAML configuration file.
1.4.1. Support for trusted-ca certificates and LLM providers
Use the OpenShift Container Platform web console to configure custom TLS certificates for secure
communication between OpenShift Lightspeed and your service endpoints.
The OpenShift Lightspeed Service supports adding trusted-ca certificates for the following LLM
providers:
Red Hat Enterprise Linux AI vLLM
Red Hat OpenShift AI vLLM
  ols:
    hideIcon: true
    defaultModel: <model_name>
    defaultProvider: myAzure
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
$ oc create -f /path/to/config-cr.yaml
Red Hat OpenShift Lightspeed 1.0 Configure
16

OpenAI
Microsoft Azure OpenAI
To add a trusted-ca certificate you must create a ConfigMap object that has the certificates. Then, add
the name of the object to the OLSConfig custom resource (CR) file as shown in the following example:
1.4.2. Configuring OpenShift Lightspeed with a trusted CA certificate for the LLM
Use the OpenShift Container Platform web console to configure a trusted CA certificate for secure
communication between OpenShift Lightspeed and your large language model (LLM) provider.
NOTE
If the LLM provider you are using requires a trusted-ca certificate to authenticate the
OpenShift Lightspeed Service you must perform this procedure. If the LLM provider does
not require a trusted-ca certificate to authenticate the Service, you should skip this
procedure.
Procedure
1. Copy the contents of the certificate file and paste it into a file called caCertFileName.
2. Create a ConfigMap object called trusted-certs by running the following command:
This command returns an output similar to the following example:
data.caCertFileName specifies the CA certificates required to connect to your LLM
provider. You can include one or more certificates within this block to ensure secure
communication.
3. Update the OLSConfig custom resource (CR) file to include the name of the ConfigMap
object you just created. The following example uses Red Hat Enterprise Linux AI as the LLM
provider.
ols:
  additionalCAConfigMapRef:
    name: <config_map_name>
$ oc create configmap trusted-certs --from-file=caCertFileName --namespace openshift-
lightspeed
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
## CONFIGURING AND DEPLOYING OPENSHIFT LIGHTSPEED
17

spec.ols.additionalCAConfigMapRef.name specifies the name of ConfigMap object.
4. Create the custom CR by running the following command:
### VERIFYING THE OPENSHIFT LIGHTSPEED DEPLOYMENT
Use the OpenShift Container Platform web console to verify that the OpenShift Lightspeed Service is
running and deployed.
IMPORTANT
Starting with OpenShift Container Platform 4.19, the perspectives in the web console are
unified. The Developer perspective is no longer enabled by default.
All users can interact with all OpenShift Container Platform web console features.
However, if you are not the cluster owner, you might need to request permission to
certain features from the cluster owner.
You can still enable the Developer perspective. On the Getting Started pane in the web
console, you can take a tour of the console, find information on setting up your cluster,
view a quick start for enabling the Developer perspective, and follow links to explore new
features and capabilities.
Prerequisites
You have logged in to the OpenShift Container Platform web console as a user with the 
cluster-admin role.
You have access to the OpenShift CLI (oc).
You have installed the OpenShift Lightspeed Operator.
You have created the credentials secret and the OLSConfig Custom Resource configuration
file.
Procedure
1. In the OpenShift Container Platform web console, click the Project drop-down list.
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
$ oc apply -f <olfconfig_cr_filename>
Red Hat OpenShift Lightspeed 1.0 Configure
18

NOTE
For OpenShift Container Platform 4.18 and earlier, select the Developer
perspective from the drop-down list at the top of the pane to access the
Project drop-down list.
2. Enable the toggle switch to show default projects.
3. Select openshift-lightspeed from the list.
4. Verify that the OpenShift Lightspeed is ready by running the following command:
Example output
### ABOUT OPENSHIFT LIGHTSPEED AND ROLE-BASED ACCESS
CONTROL (RBAC)
Use role-based access control (RBAC) to manage system security by assigning permissions to specific
roles rather than individual users.
OpenShift Lightspeed RBAC is binary. By default, not all cluster users have access to the OpenShift
Lightspeed interface. Only users with administrative rights can grant access. All users of an OpenShift
cluster with OpenShift Lightspeed installed can see the OpenShift Lightspeed button; however, only
users with permissions can submit questions to OpenShift Lightspeed.
If you want to evaluate the RBAC features of OpenShift Lightspeed, your cluster will need users other
than the kubeadmin account. The kubeadmin account always has access to OpenShift Lightspeed.
1.6.1. Granting a user access by using the CLI
Grant OpenShift Lightspeed permissions to an individual user by running a single oc adm command to
apply the query-access role immediately.
Prerequisites
You have logged in to the OpenShift Container Platform web console as a user with the 
cluster-admin role. As another option, you have logged in as a user with the ability to grant
permissions.
You have deployed the OpenShift Lightspeed service.
You have access to the OpenShift CLI (oc).
Procedure
Grant the lightspeed-operator-query-access role to a user. Enter the actual user name in
place of <user_name> when running the following command:
$ oc logs deployment/lightspeed-app-server -c lightspeed-service-api -n openshift-lightspeed 
| grep Uvicorn
INFO:  Uvicorn running on https://0.0.0.0:8443 (Press CTRL+C to quit)
## CONFIGURING AND DEPLOYING OPENSHIFT LIGHTSPEED
19

Verification
Verify that the user has been successfully added to the cluster role binding by running the
following command:
1.6.2. Granting a user access by using a YAML configuration file
Grant OpenShift Lightspeed permissions to an individual user by creating and applying a 
ClusterRoleBinding YAML file for reproducible access management.
Prerequisites
You have logged in to the OpenShift Container Platform web console as a user with the 
cluster-admin role. As another option, you have logged in as a user with the ability to grant
permissions.
You have deployed the OpenShift Lightspeed service.
You have access to the OpenShift CLI (oc).
Procedure
1. Generate a YAML configuration file for the cluster role binding. Enter the actual user name in
place of <user_name> when running the following command:
2. Apply the generated configuration file to the cluster:
Verification
1. Verify the creation of the ClusterRoleBinding by running the following command:
2. Inspect the YAML configuration and ensure that it lists the correct user in the subjects section
by running the following command:
This command returns an output similar to the following example:
$ oc adm policy add-cluster-role-to-user \
    lightspeed-operator-query-access <user_name>
$ oc get clusterrolebinding lightspeed-operator-query-access
$ oc adm policy add-cluster-role-to-user lightspeed-operator-query-access <user_name> -o 
yaml --dry-run
$ oc apply -f ols-user-access.yaml
$ oc get clusterrolebinding lightspeed-operator-query-access
$ oc get clusterrolebinding lightspeed-operator-query-access -o yaml
apiVersion: rbac.authorization.k8s.io/v1
kind: ClusterRoleBinding
Red Hat OpenShift Lightspeed 1.0 Configure
20

1.6.3. Granting a user group access by using the CLI
Enable a user group to use the OpenShift Lightspeed Service by running a single command to apply
cluster permissions immediately.
If your cluster has more advanced identity management configured, including user groups, you can grant
all users of a specific group access to the OpenShift Lightspeed Service.
Prerequisites
You have logged in to the OpenShift Container Platform web console as a user with the 
cluster-admin role. As another option, you have logged in as a user with the ability to grant
permissions.
You have deployed the OpenShift Lightspeed Service.
You have access to the OpenShift CLI (oc).
Procedure
1. Grant the lightspeed-operator-query-access role to your user group by running the following
command. Replace <group_name> with the actual name of the user group in your cluster.
2. Optional: Verify that the role binding contains the user group by running the following
command:
1.6.4. Granting a user group access by using a YAML configuration file
Grant multiple users access to the OpenShift Lightspeed Service by applying a YAML configuration file.
If your cluster has more advanced identity management configured, including user groups, you can grant
all users of a specific group access to the OpenShift Lightspeed Service.
Prerequisites
h
l
d
h
h f
l
f
b
l
h h
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
$ oc adm policy add-cluster-role-to-group \
lightspeed-operator-query-access <group_name>
$ oc get clusterrolebinding lightspeed-operator-query-access -o wide
## CONFIGURING AND DEPLOYING OPENSHIFT LIGHTSPEED
21

You have logged in to the OpenShift Container Platform web console as a user with the 
cluster-admin role. As another option, you have logged in as a user with the ability to grant
permissions.
You have deployed the OpenShift Lightspeed Service.
You have access to the OpenShift CLI (oc).
Procedure
1. Generate the YAML configuration by running the following command:
2. Open the access-policy.yaml file and verify the subjects section contains the correct group
name:
subjects.name specifies the name of the user group you are granting access to.
3. Apply the configuration file to the cluster by running the following command:
### FILTERING AND REDACTING INFORMATION
Configure sensitive data filtering in OpenShift Lightspeed to redact private information before sending
it to the large language model (LLM) provider.
NOTE
You should test your regular expressions against sample data to confirm that they
identify the information you want to filter or redact, and that they do not identify
information you want to send to the LLM. There are several third-party websites that you
can use to test your regular expressions. When using third-party sites, you should practice
caution with regards to sharing your private data. As another option, you can test the
regular expressions locally using Python. In Python, it is possible to design very
computationally-expensive regular expressions. Using several complex expressions as
query filters can adversely impact the performance of OpenShift Lightspeed.
$ oc adm policy add-cluster-role-to-group lightspeed-operator-query-access <group_name> -
o yaml --dry-run > access-policy.yaml
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
$ oc create -f access-policy.yaml
Red Hat OpenShift Lightspeed 1.0 Configure
22

This example shows how to update the OLSConfig custom resource (CR) file to redact IP addresses,
but you can also filter or redact other types of sensitive information.
NOTE
If you configure filtering or redacting in the OLSConfig CR file, and you configure 
introspectionEnabled to enable a Model Context Protocol (MCP) server, any content
that the tools return is not filtered and is visible to the LLM.
Prerequisites
You have logged in to the OpenShift Container Platform web console as a user with the 
cluster-admin role.
You have access to the OpenShift CLI (oc).
You have installed the OpenShift Lightspeed Operator and deployed the OpenShift Lightspeed
service.
Procedure
1. Update the OLSConfig CR file and create an entry for each regular expression to filter. The
following example redacts IP addresses:
2. Run the following command to apply the modified OpenShift Lightspeed custom configuration:
### ABOUT THE BYO KNOWLEDGE TOOL
Enhance OpenShift Lightspeed responses by using the BYO Knowledge tool to create a retrieval-
augmented generation (RAG) database that includes documentation specific to your organization.
When you create a RAG database, you customize the OpenShift Lightspeed service for your
environment. For example, a network administrator can use a standard operating procedure (SOP) to
provision an OpenShift Container Platform cluster. Then, the network administrator can use the BYO
Knowledge tool to enhance the knowledge available to the LLM by including information from the SOP.
To bring your own knowledge to an LLM, you complete the following steps:
Create the custom content in Markdown format.
Use the BYO Knowledge tool to package the content as a container image.
Push the container image to an image registry, such as quay.io.
spec:
  ols:
    queryFilters:
      - name: ip-address
        pattern: '((25[0-5]|(2[0-4]|1\d|[1-9]|)\d)\.?\b){4}'
        replaceWith: <IP_ADDRESS>
$ oc apply -f OLSConfig.yaml
## CONFIGURING AND DEPLOYING OPENSHIFT LIGHTSPEED
23

Update the OLSConfig custom resource file to list the image that you pushed to the image
registry.
Access the OpenShift Lightspeed virtual assistant and submit a question associated with the
custom knowledge that you made available to the LLM.
NOTE
When you use the BYO Knowledge tool, you provide documents directly to the
LLM provider.
OpenShift Lightspeed supports automatic updates of BYO Knowledge images that use floating tags,
such as latest. If over time a BYO Knowledge image tag points to different underlying images,
OpenShift Lightspeed detects those changes and updates the corresponding BYO Knowledge
database accordingly. This feature uses OpenShift ImageStream objects. OpenShift Container
Platform clusters check for updates to ImageStream objects every 15 minutes.
1.8.1. About document title and URL
Display the source titles and URLs OpenShift Lightspeed uses to verify the accuracy of generated
responses and access the original documentation for additional context.
In the retrieval-augmented generation (RAG) database, titles and URLs accompany documents as
metadata. The BYO Knowledge tool obtains the title and URL attributes from metadata if they reside in
the Markdown files that the tool processes.
If a Markdown file does not have metadata with the title and url attributes, the first top-level Markdown
heading, for example # Introduction to Layers, becomes the title and the file path becomes the URL.
1.8.2. Providing custom knowledge to the LLM
Customize the information available to the large language model (LLM) by providing access to a
container image that resides in a remote image registry.
The container image that the tool generates contains a custom RAG database. The RAG database
provides additional information to the LLM.
The examples in this procedure use quay.io as the remote container image registry, and the path for the
custom image is quay.io/<username>/my-byok-image:latest.
---
title: "Introduction to Layers {#gimp-concepts-layers}"
url: "https://docs.gimp.org/3.0/en/gimp-using-layers.html"
---
# Introduction to Layers
...
Red Hat OpenShift Lightspeed 1.0 Configure
24

IMPORTANT
The BYO Knowledge tool is a Technology Preview feature only. Technology Preview
features are not supported with Red Hat production service level agreements (SLAs) and
might not be functionally complete. Red Hat does not recommend using them in
production. These features provide early access to upcoming product features, enabling
customers to test functionality and provide feedback during the development process.
For more information about the support scope of Red Hat Technology Preview features,
see Technology Preview Features Support Scope .
Prerequisites
You have logged in to the OpenShift Container Platform web console as a user account that
has permission to create a cluster-scoped custom resource (CR) file, such as a user with the 
cluster-admin role.
You have an LLM provider available for use with the OpenShift Lightspeed Service.
You have installed the OpenShift Lightspeed Operator.
Your custom information consists of Markdown files with .md extensions. The tool does not
support other file formats.
You have logged in to registry.redhat.io by using Podman.
You have an account for a container image registry, such as quay.io.
Procedure
1. Specify the location of the directory with the Markdown files for the retrieval-augmented
generation (RAG) database and the path for the image that the BYO Knowledge tool generates
by running the following command:
2. Load the container image that the BYO Knowledge tool generated by running the following
command:
3. Display the Podman images that are on your local computer by running the following command:
This command returns an output similar to the following example:
$ podman run -it --rm --device=/dev/fuse \
  -v $XDG_RUNTIME_DIR/containers/auth.json:/run/user/0/containers/auth.json:Z \
  -v <dir_tree_with_markdown_files>:/markdown:Z \
  -v <dir_for_image_tar>:/output:Z \
  registry.redhat.io/openshift-lightspeed-tech-preview/lightspeed-rag-tool-rhel9:latest
$ podman load < <directory_for_image_tar>/<my-byok-image.tar>
$ podman images
REPOSITORY                            TAG                IMAGE ID      CREATED       SIZE
localhost/my-byok-image               latest             be7d1770bf10  1 minute  ago    2.37 GB
...
## CONFIGURING AND DEPLOYING OPENSHIFT LIGHTSPEED
25

4. Tag the local image with a name and destination so that you can push the image to the
container image registry by running the following command:
5. Push the local container image to the container image registry by running the following
command:
6. Update the OLSconfig CR to deploy the newly created RAG database alongside the existing
one:
a. In the OpenShift Container Platform web console, click Operators → Installed Operators.
b. Select All Projects in the Project dropdown at the top of the screen.
c. Click OpenShift Lightspeed Operator.
d. Click OLSConfig, then click the cluster configuration instance in the list.
e. Click the YAML tab.
f. Insert the spec.ols.rag YAML code:
spec.ols.rag.image specifies the tag for the image that you pushed to the image
registry so that the OpenShift Lightspeed Operator can access the custom content.
The OpenShift Lightspeed Operator can work with more than one RAG database that
you create.
7. Optional: Specify pull secrets in the OLSSpec section of the OLSConfig CR file. These secrets
provide authentication for remote registries. Use this optional field if your RAG BYO Knowledge
images reside in a private registry that the standard cluster-wide pull secret cannot access.
$ podman tag localhost/my-byok-image:latest quay.io/<username>/my-byok-image:latest
$ podman push quay.io/<username>/my-byok-image:latest
apiVersion: ols.openshift.io/v1alpha1
kind: OLSConfig
metadata:
  name: cluster
spec:
  ols:
    rag:
      - image: quay.io/<username>/my-byok-image:latest
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
Red Hat OpenShift Lightspeed 1.0 Configure
26

spec.ols.imagePullSecrets defines the pull secrets that OpenShift Lightspeed uses only
when you specify BYO Knowledge RAG images. Instead of linking a specific secret to a
specific image, the system maintains a general list of pull secrets. For every BYO
Knowledge image, the system tries each pull secret in sequential order until it achieves a
successful authentication.
8. Click Save.
Verification
Access the OpenShift Lightspeed virtual assistant and submit a question associated with the
custom content that you provided to the LLM.
The OpenShift Lightspeed virtual assistant generates a response based on the custom content.
1.8.3. Disabling the OpenShift Container Platform documentation retrieval-
augmented generation (RAG) database
Disable the default OpenShift Container Platform documentation in the OLSConfig custom resource
(CR) to prevent the service from using the built-in database that has the OpenShift Container Platform
documentation.
Then, the only retrieval-augmented generation (RAG) databases OpenShift Lightspeed uses are the
ones that you provide to the service by using the BYO Knowledge feature.
Prerequisites
You have logged in to the OpenShift Container Platform web console as a user account with
permission to create a cluster-scoped CR file, such as a user with the cluster-admin role.
You have installed the OpenShift Lightspeed Operator.
You have configured the large language model provider.
You have configured the OLSConfig CR file, which automatically deploys the OpenShift
Lightspeed Service.
You have created a RAG database that contains the content you want to use, as described in
"Providing custom knowledge to the LLM".
Procedure
1. In the OpenShift Container Platform web console, click Operators → Installed Operators.
2. Select All Projects in the Project list at the top of the screen.
3. Click OpenShift Lightspeed Operator.
4. Click OLSConfig, then click the cluster configuration instance in the list.
5. Click the YAML tab.
    imagePullSecrets:
      - name: <my_pull_secret_1>
      - name: <my_pull_secret_2>
## CONFIGURING AND DEPLOYING OPENSHIFT LIGHTSPEED
27

6. Insert the spec.ols.byokRAGOnly YAML code.
spec.ols.byokRAGOnly specifies if the Service limits responses by using only the
information found in the local documentation that you provide. Specify true so that
OpenShift Lightspeed only uses RAG databases that you create by using the BYO
Knowledge feature. When true, OpenShift Lightspeed does not use the default RAG
database that contains the OpenShift Container Platform documentation.
7. Click Save.
Additional resources
Providing custom knowledge to the LLM
### ABOUT CLUSTER INTERACTION
Enable the cluster interaction feature to supply the large language model (LLM) with additional context
about your OpenShift Container Platform cluster.
OpenShift Lightspeed uses a large language model (LLM) to answer your questions. When you share
details about your cluster objects, the LLM can give specific answers for your environment.
The Model Context Protocol (MCP) is an open protocol. It creates a standard way for applications to
give context to an LLM. Using the protocol, an MCP server offers a standardized way for an LLM to
increase context by requesting and receiving real-time information from external resources.
NOTE
If you configure filtering or redacting in the OLSConfig CR file, and you configure 
introspectionEnabled to enable a Model Context Protocol (MCP) server, any content
that the tools return is not filtered and is visible to the LLM.
When you enable cluster interaction, the OpenShift Lightspeed Operator installs an MCP server. The
MCP server provides the OpenShift Lightspeed Service with access to the OpenShift API. Through this
access, the Service performs read operations to gather more context for the LLM, enabling the Service
to generate answers to questions about the Kubernetes objects that reside in your OpenShift cluster.
NOTE
The ability of OpenShift Lightspeed to choose and use a tool effectively is very sensitive
to the large language (LLM) model. In general, a larger model with more parameters
performs better, and the best performance comes from an extremely large frontier model
that represents the latest AI capabilities. When using a small model, you might notice poor
performance in tool selection or other aspects of cluster interaction.
apiVersion: ols.openshift.io/v1alpha1
kind: OLSConfig
metadata:
  name: cluster
spec:
  ols:
    byokRAGOnly: true
Red Hat OpenShift Lightspeed 1.0 Configure
28

You must enable tool calling in the LLM provider to activate the cluster interaction feature in the
OpenShift Lightspeed Service.
NOTE
Enabling tool calling can dramatically increase token usage. When you use a public model
provider, an increase in token usage usually increases billing costs.
1.9.1. Enabling cluster interaction
Enable the cluster interaction feature by modifying the OLSConfig custom resource (CR) to give
OpenShift Lightspeed cluster-specific context.
IMPORTANT
The cluster interaction feature is a Technology Preview feature only. Technology Preview
features are not supported with Red Hat production service level agreements (SLAs) and
might not be functionally complete. Red Hat does not recommend using them in
production. These features provide early access to upcoming product features, enabling
customers to test functionality and provide feedback during the development process.
For more information about the support scope of Red Hat Technology Preview features,
see Technology Preview Features Support Scope .
Prerequisites
You have logged in to the OpenShift Container Platform web console as a user with the 
cluster-admin role. As another option, you have logged in to a user account that has permission
to create a cluster-scoped custom resource.
You have configured the large language model (LLM) provider.
You have installed the OpenShift Lightspeed Operator.
Procedure
1. In the OpenShift Container Platform web console, click Operators → Installed Operators.
2. Click OpenShift Lightspeed Operator.
3. Click OLSConfig, then click the cluster configuration instance in the list.
4. Click the YAML tab.
5. Set the spec.ols.introspectionEnabled parameter to true to enable cluster interaction:
apiVersion: ols.openshift.io/v1alpha1
kind: OLSConfig
metadata:
  name: cluster
spec:
  ols:
    introspectionEnabled: true
## CONFIGURING AND DEPLOYING OPENSHIFT LIGHTSPEED
29

6. Click Save.
Verification
Access the OpenShift Lightspeed virtual assistant and submit a question associated with your
cluster.
The OpenShift Lightspeed virtual assistant generates a highly refined response specific to your
environment.
1.9.2. Enabling a custom MCP server
Add an additional Model Context Protocol (MCP) server that interfaces with a tool in your environment
so that the large language model (LLM) uses the tool to generate answers to your questions.
IMPORTANT
The MCP server feature is a Technology Preview feature only. Technology Preview
features are not supported with Red Hat production service level agreements (SLAs) and
might not be functionally complete. Red Hat does not recommend using them in
production. These features provide early access to upcoming product features, enabling
customers to test functionality and provide feedback during the development process.
For more information about the support scope of Red Hat Technology Preview features,
see Technology Preview Features Support Scope .
Prerequisites
You have installed the OpenShift Lightspeed Operator.
You have configured a large language model provider.
You have configured the OLSConfig CR file, which automatically deploys the OpenShift
Lightspeed Service.
Procedure
1. Open the OpenShift Lightspeed OLSconfig custom resource (CR) file by running the following
command:
2. Add MCPServer to the spec.featureGates specification file and include the MCP server
information.
$ oc edit olsconfig cluster
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
Red Hat OpenShift Lightspeed 1.0 Configure
30

spec.featureGates specifies the MCP server functionality on the OpenShift Lightspeed
pod.
spec.mcpServers.name specifies the name of the MCP server.
spec.mcpServers.url specifies the URL path that the MCP server uses to communicate
spec.mcpServers.timeout specifies the time that the MCP server has to respond to a
query. If the Service does not receive a response within the time specified, the connection
times out. In this example, the timeout is 30 seconds.
spec.mcpServers.headers specifies MCP headers as an array of structured objects, which
are required for MCP server authentication.
spec.mcpServers.headers.name specifies the name of the header that gets sent to the
MCP server.
spec.mcpServers.headers.valueFrom.type specify the string kubernetes to provide the
user’s bearer token
spec.mcpServers.headers.valueFrom.secretRef.name specifies the name of the secret
that contains the header value. Ensure that the secret has the key name header.
3. Click Save.
The save operation saves the file and applies the changes so that the MCP server is available to
the OpenShift Lightspeed service.
### TOKENS AND TOKEN QUOTA LIMITS
Token quotas manage the amount of text that the OpenShift Lightspeed Service exchanges with a large
language model (LLM). These limits control costs and ensure all users get fair access to resources.
The Service measures the text it exchanges with the LLM in tokens. A token is a small unit of text,
ranging from a single character to a full word. Every chat between the Service and the LLM uses tokens.
Token quotas limit how many tokens you can use within a specific time frame. You can define token
quota limits for OpenShift clusters or OpenShift user accounts.
1.10.1. Activating token quota limits
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
## CONFIGURING AND DEPLOYING OPENSHIFT LIGHTSPEED
31

Activate token quota limits for OpenShift Lightspeed by creating a ConfigMap resource and
referencing it in the OLSConfig custom resource.
The OpenShift Lightspeed pod mounts the ConfigMap resource as a volume, enabling access to the file
stored within it. The OLSConfig custom resource (CR) references the ConfigMap resource to obtain
the quota limit information.
Prerequisites
You have installed the OpenShift Lightspeed Operator.
You have configured a large language model provider.
The OpenShift Lightspeed Service has access to a PostgreSQL database.
Procedure
1. Open the OpenShift Lightspeed OLSconfig CR file by running the following command:
2. Update the spec.ols.quotaHandlersConfig specification to include token quota limit
information.
User quota configuration fields
spec.ols.quotaHandlersConfig.limitersConfig.name specifies the token limit for the
user account.
spec.ols.quotaHandlersConfig.limitersConfig.initialQuota specifies a token quota
limit of 100,000 for each user over the time period specified in the period field.
spec.ols.quotaHandlersConfig.limitersConfig.quotaIncrease increases the token
quota limit for the user by 1,000 at the end of the time period specified in the period
field.
$ oc edit olsconfig cluster
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
Red Hat OpenShift Lightspeed 1.0 Configure
32

Cluster quota configuration fields
spec.ols.quotaHandlersConfig.limitersConfig.name specifies the token limit for a
cluster.
spec.ols.quotaHandlersConfig.limitersConfig.initialQuota specifies a token quota
limit of 1,000,000 for each cluster over the time period specified in the period field.
spec.ols.quotaHandlersConfig.limitersConfig.quotaIncrease increases the token
quota limit for the cluster by 100,000 at the end of the time period specified in the 
period field.
Shared configuration field
spec.ols.quotaHandlersConfig.limitersConfig.period defines the amount of time
that the scheduler waits before the period resets or the quota limit increases.
3. Click Save.
The save operation saves the file and applies the changes to activate the token quota limits.
### ABOUT OPENSHIFT LIGHTSPEED AND POSTGRESQL
PERSISTENCE
PostgreSQL persistence ensures that OpenShift Lightspeed conversation history and quota usage data
remain available across pod restarts and rescheduling events.
By default, the Service disables PostgreSQL persistence.
To enable the functionality, add the spec.ols.storage specification to the OLSConfig custom resource
(CR).
1.11.1. Enabling PostgreSQL persistence
Enable PostgreSQL persistence for OpenShift Lightspeed by modifying the OLSConfig custom
resource (CR) file.
IMPORTANT
PostgreSQL persistence is a Technology Preview feature only. Technology Preview
features are not supported with Red Hat production service level agreements (SLAs) and
might not be functionally complete. Red Hat does not recommend using them in
production. These features provide early access to upcoming product features, enabling
customers to test functionality and provide feedback during the development process.
For more information about the support scope of Red Hat Technology Preview features,
see Technology Preview Features Support Scope .
Prerequisites
You have logged in to the OpenShift Container Platform web console as a user account with
permission to create a cluster-scoped CR file, such as a user with the cluster-admin role.
You have installed the OpenShift Lightspeed Operator.
You have configured the large language model provider.
## CONFIGURING AND DEPLOYING OPENSHIFT LIGHTSPEED
33

Procedure
1. In the OpenShift Container Platform web console, click Operators → Installed Operators.
2. Select All Projects in the Project list at the top of the screen.
3. Click OpenShift Lightspeed Operator.
4. Click OLSConfig, then click the cluster configuration instance in the list.
5. Click the YAML tab.
6. Insert the spec.ols.storage YAML code.
spec.ols.storage specifies how the assistant stores persistent data, specifically
conversation history. The class depends on the existing instances of the storage class in the
cluster. If you leave the storage class empty, the assistant uses default values. The
persistent volume allocated for the PostgreSQL database is 1 GB in size and uses the
storage class of the default cluster. Specify empty braces for the storage parameter to use
the default values.
7. Click Save.
1.11.2. Overriding default Persistent Volume Claim (PVC) specifications
Customize the storage capacity and storage class for the OpenShift Lightspeed database by modifying
the OLSConfig custom resource (CR).
Prerequisites
You have logged in to the OpenShift Container Platform web console as a user account with
permission to create a cluster-scoped CR, such as a user with the cluster-admin role.
You have installed the OpenShift Lightspeed Operator.
You have configured the large language model provider.
You have access to the OpenShift CLI (oc).
Procedure
1. In the OpenShift Container Platform web console, click Operators → Installed Operators.
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
Red Hat OpenShift Lightspeed 1.0 Configure
34

2. Select All Projects in the Project dropdown at the top of the screen.
3. Click OpenShift Lightspeed Operator.
4. Click OLSConfig, then click the cluster configuration instance in the list.
5. Click the YAML tab.
6. Update the OLSconfig CR to override the default PVC specifications as shown in the following
example.
spec.ols.storage.size specifies the total storage capacity for the database. If you do not
specify this parameter, the Operator uses the default size of 1 GiB.
spec.ols.storage.class specifies the Storage Class for the database volume. If you do not
specify this parameter, the Operator uses the default storage class setting of the cluster.
7. Click Save.
Verification
1. Verify that the cluster has successfully provisioned the storage by checking the status of the
Persistent Volume Claim.
### ABOUT QUERY-BASED TOOL FILTERING
Query-based filtering uses a hybrid retrieval-augmented generation (RAG) system to identify the most
appropriate set of tool for a user request.
When a large language model (LLM) application has access to hundreds of tools, sending the full list in
one prompt slows performance and raises costs. Query-based filtering finds and retrieves the most
relevant set of tools for a request in milliseconds. This pre-processing step removes selection
interference, and ensures that the LLM focuses its reasoning capabilities on a small, high-quality subset
of functions.
Restricting the set of tools available reduces token use, prevents model confusion, and maintains high
execution accuracy. This approach transforms a massive tool library into a fast, lean interface.
1.12.1. Enabling query-based tool filtering
Enable query-based tool filtering to automatically select the set of tools most relevant for your request.
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
$ oc get pvc -n openshift-lightspeed
## CONFIGURING AND DEPLOYING OPENSHIFT LIGHTSPEED
35

Prerequisites
You are logged in to the OpenShift Container Platform web console as a user with the cluster-
admin role. Alternatively, you are logged in to a user account that has permission to create a
cluster-scoped CR file.
You have an LLM provider available for use with the OpenShift Lightspeed Service.
You have installed the OpenShift Lightspeed Operator.
Procedure
1. In the OpenShift Container Platform web console, click Operators → Installed Operators.
2. Select All Projects in the Project dropdown at the top of the screen.
3. Click OpenShift Lightspeed Operator.
4. Click OLSConfig, then click the cluster configuration instance in the list.
5. Click the YAML tab.
6. Modify the ols.Config custom resource (CR) file to define a feature gate and the tools filtering
configuration.
spec.featureGates.ToolFilter specifies the feature gate.
spec.olsConfig.toolFilteringConfig.alpha specifies the weight balance between semantic
(RAG-based) and keyword matching. Increasing the value provides more weight to the
semantic search. The valid range of values is 0 to 1.
spec.olsConfig.toolFilteringConfig.topk specifies the maximum number of tools available
for the LLM.
spec.olsConfig.toolFilteringConfig.threshold specifies the minimum score for the tool to
be considered as a candidate. Tools with a value of the score lower then the threshold value
are discarded. Increasing the value discards more tools. The valid range of values is 0.01 to 
### apiVersion: ols.openshift.io/v1alpha1
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
Red Hat OpenShift Lightspeed 1.0 Configure
36

NOTE
This example uses the default values for the alpha, tpok and threshold
fields. If you use the default values in your configuration you do not have to
specify them.
7. Click Save.
Verification
1. Navigate to the OpenShift Container Platform web console.
2. Select Workloads → Pods and then click the pod that contains OpenShift Lightspeed.
3. Click Logs and confirm that the log displays RAG information.
## CONFIGURING AND DEPLOYING OPENSHIFT LIGHTSPEED
37
## OLSCONFIG API REFERENCE
OLSConfig is the Schema for the OpenShift Lightspeed configuration object and defines the structure
and parameters of the underlying deployments.
NOTE
The API parameter information originated in the OLSConfig API reference and is
provided here for convenience.
### OLSCONFIG API SPECIFICATIONS
Description
Red Hat Red Hat OpenShift Lightspeed Lightspeed instance. OLSConfig is the Schema for the
olsconfigs API
Type
object
Required
spec
Property
Type
Description
apiVersion
string
APIVersion defines the versioned
schema of this representation of
an object. Servers should convert
recognized schemas to the latest
internal value, and might reject
unrecognized values. More info:
https://git.k8s.io/community/con
tributors/devel/sig-
architecture/api-
conventions.md#resources
kind
string
Kind is a string value representing
the REST resource this object
represents. Servers might infer
this from the endpoint the client
submits requests to. Cannot be
updated. In CamelCase. More
info:
https://git.k8s.io/community/con
tributors/devel/sig-
architecture/api-
conventions.md#types-kinds
Red Hat OpenShift Lightspeed 1.0 Configure
38

metadata
object
Standard object’s metadata. More
info:
https://git.k8s.io/community/con
tributors/devel/sig-
architecture/api-
conventions.md#metadata
spec
object
OLSConfigSpec defines the
desired state of OLSConfig
Property
Type
Description
2.1.1. .metadata
Description
Standard object’s metadata. More info: https://git.k8s.io/community/contributors/devel/sig-
architecture/api-conventions.md#metadata
Type
object
2.1.2. .spec
Description
OLSConfigSpec defines the desired state of OLSConfig
Type
object
Required
llm
ols
Property
Type
Description
featureGates
array (string)
Feature Gates holds list of
features to be enabled explicitly,
otherwise they are disabled by
default. possible values:
MCPServer
llm
object
LLMSpec defines the desired
state of the large language model
(LLM).
mcpServers
array
MCP Server settings
## OLSCONFIG API REFERENCE
39

ols
object
OLSSpec defines the desired
state of OLS deployment.
olsDataCollector
object
OLSDataCollectorSpec defines
allowed OLS data collector
configuration.
Property
Type
Description
2.1.3. .spec.llm
Description
LLMSpec defines the desired state of the large language model (LLM).
Type
object
Required
providers
Property
Type
Description
providers
array
 
2.1.4. .spec.llm.providers
Description
Type
array
2.1.5. .spec.llm.providers[]
Description
ProviderSpec defines the desired state of LLM provider.
Type
object
Required
credentialsSecretRef
models
name
type
Red Hat OpenShift Lightspeed 1.0 Configure
40

Property
Type
Description
apiVersion
string
API Version for Azure OpenAI
provider
credentialsSecretRef
object
The name of the secret object
that stores API provider
credentials
deploymentName
string
Azure OpenAI deployment name
models
array
List of models from the provider
name
string
Provider name
projectID
string
Watsonx Project ID
tlsSecurityProfile
object
TLS Security Profile used by
connection to provider
type
string
Provider type
url
string
Provider API URL
2.1.6. .spec.llm.providers[].credentialsSecretRef
Description
The name of the secret object that stores API provider credentials
Type
object
Property
Type
Description
name
string
Name of the referent. This field is
effectively required, but due to
backwards compatibility is allowed
to be empty. Instances of this
type with an empty value here are
almost certainly wrong. More info:
https://kubernetes.io/docs/conc
epts/overview/working-with-
objects/names/#names
2.1.7. .spec.llm.providers[].models
Description
List of models from the provider
## OLSCONFIG API REFERENCE
41

Type
array
2.1.8. .spec.llm.providers[].models[]
Description
ModelSpec defines the LLM model to use and its parameters.
Type
object
Required
name
Property
Type
Description
contextWindowSize
integer
Defines the model’s context
window size, in tokens. The
default is 128k tokens.
name
string
Model name
parameters
object
Model API parameters
url
string
Model API URL
2.1.9. .spec.llm.providers[].models[].parameters
Description
Model API parameters
Type
object
Property
Type
Description
maxTokensForResponse
integer
Max tokens for response. The
default is 2048 tokens.
2.1.10. .spec.llm.providers[].tlsSecurityProfile
Description
TLS Security Profile used by connection to provider
Type
object
Red Hat OpenShift Lightspeed 1.0 Configure
42

Property
Type
Description
custom
``
custom is a user-defined TLS
security profile. Be extremely
careful using a custom profile as
invalid configurations can be
catastrophic. An example custom
profile looks like this:
ciphers:
- ECDHE-ECDSA-CHACHA20-
POLY1305
- ECDHE-RSA-CHACHA20-
POLY1305
- ECDHE-RSA-AES128-GCM-
SHA256
- ECDHE-ECDSA-AES128-GCM-
SHA256
minTLSVersion: VersionTLS11
## OLSCONFIG API REFERENCE
43

intermediate
``
intermediate is a TLS security
profile based on:
https://wiki.mozilla.org/Security/
Server_Side_TLS#Intermediate_c
ompatibility_.28recommended.29
and looks like this (yaml):
ciphers:
- TLS_AES_128_GCM_SHA256
- TLS_AES_256_GCM_SHA384
-
TLS_CHACHA20_POLY1305_SH
A256
- ECDHE-ECDSA-AES128-GCM-
SHA256
- ECDHE-RSA-AES128-GCM-
SHA256
- ECDHE-ECDSA-AES256-
GCM-SHA384
- ECDHE-RSA-AES256-GCM-
SHA384
- ECDHE-ECDSA-CHACHA20-
POLY1305
- ECDHE-RSA-CHACHA20-
POLY1305
- DHE-RSA-AES128-GCM-
SHA256
- DHE-RSA-AES256-GCM-
SHA384
minTLSVersion: VersionTLS12
Property
Type
Description
Red Hat OpenShift Lightspeed 1.0 Configure
44

modern
``
modern is a TLS security profile
based on:
https://wiki.mozilla.org/Security/
Server_Side_TLS#Modern_comp
atibility
and looks like this (yaml):
ciphers:
- TLS_AES_128_GCM_SHA256
- TLS_AES_256_GCM_SHA384
-
TLS_CHACHA20_POLY1305_SH
A256
minTLSVersion: VersionTLS13
old
``
old is a TLS security profile based
on:
https://wiki.mozilla.org/Security/
Server_Side_TLS#Old_backward_
compatibility
and looks like this (yaml):
ciphers:
- TLS_AES_128_GCM_SHA256
- TLS_AES_256_GCM_SHA384
-
TLS_CHACHA20_POLY1305_SH
A256
- ECDHE-ECDSA-AES128-GCM-
SHA256
- ECDHE-RSA-AES128-GCM-
SHA256
- ECDHE-ECDSA-AES256-
GCM-SHA384
- ECDHE-RSA-AES256-GCM-
SHA384
- ECDHE-ECDSA-CHACHA20-
POLY1305
- ECDHE-RSA-CHACHA20-
Property
Type
Description
## OLSCONFIG API REFERENCE
45

POLY1305
- DHE-RSA-AES128-GCM-
SHA256
- DHE-RSA-AES256-GCM-
SHA384
- DHE-RSA-CHACHA20-
POLY1305
- ECDHE-ECDSA-AES128-
SHA256
- ECDHE-RSA-AES128-SHA256
- ECDHE-ECDSA-AES128-SHA
- ECDHE-RSA-AES128-SHA
- ECDHE-ECDSA-AES256-
SHA384
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
Property
Type
Description
Red Hat OpenShift Lightspeed 1.0 Configure
46

type
string
type is one of Old, Intermediate,
Modern or Custom. Custom
provides the ability to specify
individual TLS security profile
parameters. Old, Intermediate
and Modern are TLS security
profiles based on:
https://wiki.mozilla.org/Security/
Server_Side_TLS#Recommended
_configurations
The profiles are intent based, so
they might change over time as
new ciphers are developed and
existing ciphers are found to be
insecure. Depending on precisely
which ciphers are available to a
process, the list might be reduced.
Note that the Modern profile is
currently not supported because
it is not yet well adopted by
common software libraries.
Property
Type
Description
2.1.11. .spec.mcpServers
Description
MCP Server settings
Type
array
2.1.12. .spec.mcpServers[]
Description
MCPServer defines the settings for a single MCP server.
Type
object
Required
name
Property
Type
Description
name
string
Name of the MCP server
## OLSCONFIG API REFERENCE
47

streamableHTTP
object
Streamable HTTP Transport
settings
Property
Type
Description
2.1.13. .spec.mcpServers[].streamableHTTP
Description
Streamable HTTP Transport settings
Type
object
Required
url
Property
Type
Description
enableSSE
boolean
Enable Server Sent Events
headers
object (string)
Headers to send to the MCP
server
sseReadTimeout
integer
SSE Read Timeout, default is 10
seconds
timeout
integer
Timeout for the MCP server,
default is 5 seconds
url
string
URL of the MCP server
2.1.14. .spec.ols
Description
OLSSpec defines the desired state of OLS deployment.
Type
object
Required
defaultModel
defaultProvider
Red Hat OpenShift Lightspeed 1.0 Configure
48

Property
Type
Description
additionalCAConfigMapRef
object
Additional CA certificates for TLS
communication between OLS
service and LLM Provider
byokRAGOnly
boolean
Only use BYOK RAG sources,
ignore the Red Hat OpenShift
Lightspeed documentation RAG
conversationCache
object
Conversation cache settings
defaultModel
string
Default model for usage
defaultProvider
string
Default provider for usage
deployment
object
OLS deployment settings
introspectionEnabled
boolean
Enable introspection features
logLevel
string
Log level. Valid options are
DEBUG, INFO, WARNING,
ERROR and CRITICAL. Default:
"INFO".
proxyConfig
object
Proxy settings for connecting to
external servers, such as LLM
providers.
queryFilters
array
Query filters
quotaHandlersConfig
object
LLM Token Quota Configuration
rag
array
RAG databases
storage
object
Persistent Storage Configuration
tlsConfig
object
TLS configuration of the
Lightspeed backend’s HTTPS
endpoint
tlsSecurityProfile
object
TLS Security Profile used by API
endpoints
userDataCollection
object
User data collection switches
2.1.15. .spec.ols.additionalCAConfigMapRef
## OLSCONFIG API REFERENCE
49

Description
Additional CA certificates for TLS communication between OLS service and LLM Provider
Type
object
Property
Type
Description
name
string
Name of the referent. This field is
effectively required, but due to
backwards compatibility is allowed
to be empty. Instances of this
type with an empty value here are
almost certainly wrong. More info:
https://kubernetes.io/docs/conc
epts/overview/working-with-
objects/names/#names
2.1.16. .spec.ols.conversationCache
Description
Conversation cache settings
Type
object
Property
Type
Description
postgres
object
PostgresSpec defines the desired
state of Postgres.
type
string
Conversation cache type. Default:
"postgres"
2.1.17. .spec.ols.conversationCache.postgres
Description
PostgresSpec defines the desired state of Postgres.
Type
object
Property
Type
Description
credentialsSecret
string
Secret that holds postgres
credentials
dbName
string
Postgres database name
Red Hat OpenShift Lightspeed 1.0 Configure
50

maxConnections
integer
Postgres maxconnections.
Default: "2000"
sharedBuffers
integer-or-string
Postgres sharedbuffers
user
string
Postgres user name
Property
Type
Description
2.1.18. .spec.ols.deployment
Description
OLS deployment settings
Type
object
Property
Type
Description
api
object
API container settings.
console
object
Console container settings.
dataCollector
object
Data Collector container settings.
database
object
Database container settings.
mcpServer
object
MCP server container settings.
replicas
integer
Defines the number of desired
OLS pods. Default: "1"
2.1.19. .spec.ols.deployment.api
Description
API container settings.
Type
object
Property
Type
Description
nodeSelector
object (string)
## OLSCONFIG API REFERENCE
51

resources
object
ResourceRequirements describes
the compute resource
requirements.
tolerations
array
 
Property
Type
Description
2.1.20. .spec.ols.deployment.api.resources
Description
ResourceRequirements describes the compute resource requirements.
Type
object
Property
Type
Description
claims
array
Claims lists the names of
resources, defined in
spec.resourceClaims, that are
used by this container.
This is an alpha field and requires
enabling the
DynamicResourceAllocation
feature gate.
This field is immutable. It can only
be set for containers.
limits
integer-or-string
Limits describes the maximum
amount of compute resources
allowed. More info:
https://kubernetes.io/docs/conc
epts/configuration/manage-
resources-containers/
requests
integer-or-string
Requests describes the minimum
amount of compute resources
required. If Requests is omitted
for a container, it defaults to
Limits if that is explicitly specified,
otherwise to an implementation-
defined value. Requests cannot
exceed Limits. More info:
https://kubernetes.io/docs/conc
epts/configuration/manage-
resources-containers/
Red Hat OpenShift Lightspeed 1.0 Configure
52

2.1.21. .spec.ols.deployment.api.resources.claims
Description
Claims lists the names of resources, defined in spec.resourceClaims, that are used by this container.
This is an alpha field and requires enabling the DynamicResourceAllocation feature gate.
This field is immutable. It can only be set for containers.
Type
array
2.1.22. .spec.ols.deployment.api.resources.claims[]
Description
ResourceClaim references one entry in PodSpec.ResourceClaims.
Type
object
Required
name
Property
Type
Description
name
string
Name must match the name of
one entry in
pod.spec.resourceClaims of the
Pod where this field is used. It
makes that resource available
inside a container.
request
string
Request is the name chosen for a
request in the referenced claim. If
empty, everything from the claim
is made available, otherwise only
the result of this request.
2.1.23. .spec.ols.deployment.api.tolerations
Description
Type
array
2.1.24. .spec.ols.deployment.api.tolerations[]
Description
The pod this Toleration is attached to tolerates any taint that matches the triple <key,value,effect>
using the matching operator <operator>.
Type
## OLSCONFIG API REFERENCE
53

object
Property
Type
Description
effect
string
Effect indicates the taint effect to
match. Empty means match all
taint effects. When specified,
allowed values are NoSchedule,
PreferNoSchedule and
NoExecute.
key
string
Key is the taint key that the
toleration applies to. Empty
means match all taint keys. If the
key is empty, operator must be
Exists; this combination means to
match all values and all keys.
operator
string
Operator represents a key’s
relationship to the value. Valid
operators are Exists and Equal.
Defaults to Equal. Exists is
equivalent to wildcard for value,
so that a pod can tolerate all
taints of a particular category.
tolerationSeconds
integer
TolerationSeconds represents the
period of time the toleration
(which must be of effect
NoExecute, otherwise this field is
ignored) tolerates the taint. By
default, it is not set, which means
tolerate the taint forever (do not
evict). Zero and negative values
will be treated as 0 (evict
immediately) by the system.
value
string
Value is the taint value the
toleration matches to. If the
operator is Exists, the value
should be empty, otherwise just a
regular string.
2.1.25. .spec.ols.deployment.console
Description
Console container settings.
Type
object
Red Hat OpenShift Lightspeed 1.0 Configure
54

Property
Type
Description
caCertificate
string
Certificate Authority (CA)
certificate used by the console
proxy endpoint.
nodeSelector
object (string)
 
replicas
integer
Defines the number of desired
Console pods. Default: "1"
resources
object
ResourceRequirements describes
the compute resource
requirements.
tolerations
array
 
2.1.26. .spec.ols.deployment.console.resources
Description
ResourceRequirements describes the compute resource requirements.
Type
object
Property
Type
Description
claims
array
Claims lists the names of
resources, defined in
spec.resourceClaims, that are
used by this container.
This is an alpha field and requires
enabling the
DynamicResourceAllocation
feature gate.
This field is immutable. It can only
be set for containers.
limits
integer-or-string
Limits describes the maximum
amount of compute resources
allowed. More info:
https://kubernetes.io/docs/conc
epts/configuration/manage-
resources-containers/
## OLSCONFIG API REFERENCE
55

requests
integer-or-string
Requests describes the minimum
amount of compute resources
required. If Requests is omitted
for a container, it defaults to
Limits if that is explicitly specified,
otherwise to an implementation-
defined value. Requests cannot
exceed Limits. More info:
https://kubernetes.io/docs/conc
epts/configuration/manage-
resources-containers/
Property
Type
Description
2.1.27. .spec.ols.deployment.console.resources.claims
Description
Claims lists the names of resources, defined in spec.resourceClaims, that are used by this container.
This is an alpha field and requires enabling the DynamicResourceAllocation feature gate.
This field is immutable. It can only be set for containers.
Type
array
2.1.28. .spec.ols.deployment.console.resources.claims[]
Description
ResourceClaim references one entry in PodSpec.ResourceClaims.
Type
object
Required
name
Property
Type
Description
name
string
Name must match the name of
one entry in
pod.spec.resourceClaims of the
Pod where this field is used. It
makes that resource available
inside a container.
Red Hat OpenShift Lightspeed 1.0 Configure
56

request
string
Request is the name chosen for a
request in the referenced claim. If
empty, everything from the claim
is made available, otherwise only
the result of this request.
Property
Type
Description
2.1.29. .spec.ols.deployment.console.tolerations
Description
Type
array
2.1.30. .spec.ols.deployment.console.tolerations[]
Description
The pod this Toleration is attached to tolerates any taint that matches the triple <key,value,effect>
using the matching operator <operator>.
Type
object
Property
Type
Description
effect
string
Effect indicates the taint effect to
match. Empty means match all
taint effects. When specified,
allowed values are NoSchedule,
PreferNoSchedule and
NoExecute.
key
string
Key is the taint key that the
toleration applies to. Empty
means match all taint keys. If the
key is empty, operator must be
Exists; this combination means to
match all values and all keys.
operator
string
Operator represents a key’s
relationship to the value. Valid
operators are Exists and Equal.
Defaults to Equal. Exists is
equivalent to wildcard for value,
so that a pod can tolerate all
taints of a particular category.
## OLSCONFIG API REFERENCE
57

tolerationSeconds
integer
TolerationSeconds represents the
period of time the toleration
(which must be of effect
NoExecute, otherwise this field is
ignored) tolerates the taint. By
default, it is not set, which means
tolerate the taint forever (do not
evict). Zero and negative values
will be treated as 0 (evict
immediately) by the system.
value
string
Value is the taint value the
toleration matches to. If the
operator is Exists, the value
should be empty, otherwise just a
regular string.
Property
Type
Description
2.1.31. .spec.ols.deployment.dataCollector
Description
Data Collector container settings.
Type
object
Property
Type
Description
resources
object
ResourceRequirements describes
the compute resource
requirements.
2.1.32. .spec.ols.deployment.dataCollector.resources
Description
ResourceRequirements describes the compute resource requirements.
Type
object
Property
Type
Description
Red Hat OpenShift Lightspeed 1.0 Configure
58

claims
array
Claims lists the names of
resources, defined in
spec.resourceClaims, that are
used by this container.
This is an alpha field and requires
enabling the
DynamicResourceAllocation
feature gate.
This field is immutable. It can only
be set for containers.
limits
integer-or-string
Limits describes the maximum
amount of compute resources
allowed. More info:
https://kubernetes.io/docs/conc
epts/configuration/manage-
resources-containers/
requests
integer-or-string
Requests describes the minimum
amount of compute resources
required. If Requests is omitted
for a container, it defaults to
Limits if that is explicitly specified,
otherwise to an implementation-
defined value. Requests cannot
exceed Limits. More info:
https://kubernetes.io/docs/conc
epts/configuration/manage-
resources-containers/
Property
Type
Description
2.1.33. .spec.ols.deployment.dataCollector.resources.claims
Description
Claims lists the names of resources, defined in spec.resourceClaims, that are used by this container.
This is an alpha field and requires enabling the DynamicResourceAllocation feature gate.
This field is immutable. It can only be set for containers.
Type
array
2.1.34. .spec.ols.deployment.dataCollector.resources.claims[]
Description
ResourceClaim references one entry in PodSpec.ResourceClaims.
## OLSCONFIG API REFERENCE
59

Type
object
Required
name
Property
Type
Description
name
string
Name must match the name of
one entry in
pod.spec.resourceClaims of the
Pod where this field is used. It
makes that resource available
inside a container.
request
string
Request is the name chosen for a
request in the referenced claim. If
empty, everything from the claim
is made available, otherwise only
the result of this request.
2.1.35. .spec.ols.deployment.database
Description
Database container settings.
Type
object
Property
Type
Description
nodeSelector
object (string)
 
resources
object
ResourceRequirements describes
the compute resource
requirements.
tolerations
array
 
2.1.36. .spec.ols.deployment.database.resources
Description
ResourceRequirements describes the compute resource requirements.
Type
object
Red Hat OpenShift Lightspeed 1.0 Configure
60

Property
Type
Description
claims
array
Claims lists the names of
resources, defined in
spec.resourceClaims, that are
used by this container.
This is an alpha field and requires
enabling the
DynamicResourceAllocation
feature gate.
This field is immutable. It can only
be set for containers.
limits
integer-or-string
Limits describes the maximum
amount of compute resources
allowed. More info:
https://kubernetes.io/docs/conc
epts/configuration/manage-
resources-containers/
requests
integer-or-string
Requests describes the minimum
amount of compute resources
required. If Requests is omitted
for a container, it defaults to
Limits if that is explicitly specified,
otherwise to an implementation-
defined value. Requests cannot
exceed Limits. More info:
https://kubernetes.io/docs/conc
epts/configuration/manage-
resources-containers/
2.1.37. .spec.ols.deployment.database.resources.claims
Description
Claims lists the names of resources, defined in spec.resourceClaims, that are used by this container.
This is an alpha field and requires enabling the DynamicResourceAllocation feature gate.
This field is immutable. It can only be set for containers.
Type
array
2.1.38. .spec.ols.deployment.database.resources.claims[]
Description
ResourceClaim references one entry in PodSpec.ResourceClaims.
Type
object
## OLSCONFIG API REFERENCE
61

Required
name
Property
Type
Description
name
string
Name must match the name of
one entry in
pod.spec.resourceClaims of the
Pod where this field is used. It
makes that resource available
inside a container.
request
string
Request is the name chosen for a
request in the referenced claim. If
empty, everything from the claim
is made available, otherwise only
the result of this request.
2.1.39. .spec.ols.deployment.database.tolerations
Description
Type
array
2.1.40. .spec.ols.deployment.database.tolerations[]
Description
The pod this Toleration is attached to tolerates any taint that matches the triple <key,value,effect>
using the matching operator <operator>.
Type
object
Property
Type
Description
effect
string
Effect indicates the taint effect to
match. Empty means match all
taint effects. When specified,
allowed values are NoSchedule,
PreferNoSchedule and
NoExecute.
key
string
Key is the taint key that the
toleration applies to. Empty
means match all taint keys. If the
key is empty, operator must be
Exists; this combination means to
match all values and all keys.
Red Hat OpenShift Lightspeed 1.0 Configure
62

operator
string
Operator represents a key’s
relationship to the value. Valid
operators are Exists and Equal.
Defaults to Equal. Exists is
equivalent to wildcard for value,
so that a pod can tolerate all
taints of a particular category.
tolerationSeconds
integer
TolerationSeconds represents the
period of time the toleration
(which must be of effect
NoExecute, otherwise this field is
ignored) tolerates the taint. By
default, it is not set, which means
tolerate the taint forever (do not
evict). Zero and negative values
will be treated as 0 (evict
immediately) by the system.
value
string
Value is the taint value the
toleration matches to. If the
operator is Exists, the value
should be empty, otherwise just a
regular string.
Property
Type
Description
2.1.41. .spec.ols.deployment.mcpServer
Description
MCP server container settings.
Type
object
Property
Type
Description
resources
object
ResourceRequirements describes
the compute resource
requirements.
2.1.42. .spec.ols.deployment.mcpServer.resources
Description
ResourceRequirements describes the compute resource requirements.
Type
object
## OLSCONFIG API REFERENCE
63

Property
Type
Description
claims
array
Claims lists the names of
resources, defined in
spec.resourceClaims, that are
used by this container.
This is an alpha field and requires
enabling the
DynamicResourceAllocation
feature gate.
This field is immutable. It can only
be set for containers.
limits
integer-or-string
Limits describes the maximum
amount of compute resources
allowed. More info:
https://kubernetes.io/docs/conc
epts/configuration/manage-
resources-containers/
requests
integer-or-string
Requests describes the minimum
amount of compute resources
required. If Requests is omitted
for a container, it defaults to
Limits if that is explicitly specified,
otherwise to an implementation-
defined value. Requests cannot
exceed Limits. More info:
https://kubernetes.io/docs/conc
epts/configuration/manage-
resources-containers/
2.1.43. .spec.ols.deployment.mcpServer.resources.claims
Description
Claims lists the names of resources, defined in spec.resourceClaims, that are used by this container.
This is an alpha field and requires enabling the DynamicResourceAllocation feature gate.
This field is immutable. It can only be set for containers.
Type
array
2.1.44. .spec.ols.deployment.mcpServer.resources.claims[]
Description
ResourceClaim references one entry in PodSpec.ResourceClaims.
Type
object
Red Hat OpenShift Lightspeed 1.0 Configure
64

Required
name
Property
Type
Description
name
string
Name must match the name of
one entry in
pod.spec.resourceClaims of the
Pod where this field is used. It
makes that resource available
inside a container.
request
string
Request is the name chosen for a
request in the referenced claim. If
empty, everything from the claim
is made available, otherwise only
the result of this request.
2.1.45. .spec.ols.proxyConfig
Description
Proxy settings for connecting to external servers, such as LLM providers.
Type
object
Property
Type
Description
proxyCACertificate
object
The configmap holding proxy CA
certificate
proxyURL
string
Proxy URL, e.g.
https://proxy.example.com:8080
If not specified, the cluster wide
proxy will be used, though env var
"https_proxy".
2.1.46. .spec.ols.proxyConfig.proxyCACertificate
Description
The configmap holding proxy CA certificate
Type
object
Property
Type
Description
## OLSCONFIG API REFERENCE
65

name
string
Name of the referent. This field is
effectively required, but due to
backwards compatibility is allowed
to be empty. Instances of this
type with an empty value here are
almost certainly wrong. More info:
https://kubernetes.io/docs/conc
epts/overview/working-with-
objects/names/#names
Property
Type
Description
2.1.47. .spec.ols.queryFilters
Description
Query filters
Type
array
2.1.48. .spec.ols.queryFilters[]
Description
QueryFiltersSpec defines filters to manipulate questions/queries.
Type
object
Property
Type
Description
name
string
Filter name.
pattern
string
Filter pattern.
replaceWith
string
Replacement for the matched
pattern.
2.1.49. .spec.ols.quotaHandlersConfig
Description
LLM Token Quota Configuration
Type
object
Red Hat OpenShift Lightspeed 1.0 Configure
66

Property
Type
Description
enableTokenHistory
boolean
Enable token history
limitersConfig
array
Token quota limiters
2.1.50. .spec.ols.quotaHandlersConfig.limitersConfig
Description
Token quota limiters
Type
array
2.1.51. .spec.ols.quotaHandlersConfig.limitersConfig[]
Description
LimiterConfig defines settings for a token quota limiter
Type
object
Required
initialQuota
name
period
quotaIncrease
type
Property
Type
Description
initialQuota
integer
Initial value of the token quota
name
string
Name of the limiter
period
string
Period of time the token quota is
for
quotaIncrease
integer
Token quota increase step
type
string
Type of the limiter
2.1.52. .spec.ols.rag
## OLSCONFIG API REFERENCE
67

Description
RAG databases
Type
array
2.1.53. .spec.ols.rag[]
Description
RAGSpec defines how to retrieve a RAG databases.
Type
object
Required
image
Property
Type
Description
image
string
The URL of the container image
to use as a RAG source
indexID
string
The Index ID of the RAG database
indexPath
string
The path to the RAG database
inside of the container image
2.1.54. .spec.ols.storage
Description
Persistent Storage Configuration
Type
object
Property
Type
Description
class
string
Storage class of the requested
volume
size
integer-or-string
Size of the requested volume
2.1.55. .spec.ols.tlsConfig
Description
TLS configuration of the Lightspeed backend’s HTTPS endpoint
Type
object
Red Hat OpenShift Lightspeed 1.0 Configure
68

Property
Type
Description
keyCertSecretRef
object
KeySecretRef is the secret that
holds the TLS key.
2.1.56. .spec.ols.tlsConfig.keyCertSecretRef
Description
KeySecretRef is the secret that holds the TLS key.
Type
object
Property
Type
Description
name
string
Name of the referent. This field is
effectively required, but due to
backwards compatibility is allowed
to be empty. Instances of this
type with an empty value here are
almost certainly wrong. More info:
https://kubernetes.io/docs/conc
epts/overview/working-with-
objects/names/#names
2.1.57. .spec.ols.tlsSecurityProfile
Description
TLS Security Profile used by API endpoints
Type
object
Property
Type
Description
## OLSCONFIG API REFERENCE
69

custom
``
custom is a user-defined TLS
security profile. Be extremely
careful using a custom profile as
invalid configurations can be
catastrophic. An example custom
profile looks like this:
ciphers:
- ECDHE-ECDSA-CHACHA20-
POLY1305
- ECDHE-RSA-CHACHA20-
POLY1305
- ECDHE-RSA-AES128-GCM-
SHA256
- ECDHE-ECDSA-AES128-GCM-
SHA256
minTLSVersion: VersionTLS11
Property
Type
Description
Red Hat OpenShift Lightspeed 1.0 Configure
70

intermediate
``
intermediate is a TLS security
profile based on:
https://wiki.mozilla.org/Security/
Server_Side_TLS#Intermediate_c
ompatibility_.28recommended.29
and looks like this (yaml):
ciphers:
- TLS_AES_128_GCM_SHA256
- TLS_AES_256_GCM_SHA384
-
TLS_CHACHA20_POLY1305_SH
A256
- ECDHE-ECDSA-AES128-GCM-
SHA256
- ECDHE-RSA-AES128-GCM-
SHA256
- ECDHE-ECDSA-AES256-
GCM-SHA384
- ECDHE-RSA-AES256-GCM-
SHA384
- ECDHE-ECDSA-CHACHA20-
POLY1305
- ECDHE-RSA-CHACHA20-
POLY1305
- DHE-RSA-AES128-GCM-
SHA256
- DHE-RSA-AES256-GCM-
SHA384
minTLSVersion: VersionTLS12
Property
Type
Description
## OLSCONFIG API REFERENCE
71

modern
``
modern is a TLS security profile
based on:
https://wiki.mozilla.org/Security/
Server_Side_TLS#Modern_comp
atibility
and looks like this (yaml):
ciphers:
- TLS_AES_128_GCM_SHA256
- TLS_AES_256_GCM_SHA384
-
TLS_CHACHA20_POLY1305_SH
A256
minTLSVersion: VersionTLS13
old
``
old is a TLS security profile based
on:
https://wiki.mozilla.org/Security/
Server_Side_TLS#Old_backward_
compatibility
and looks like this (yaml):
ciphers:
- TLS_AES_128_GCM_SHA256
- TLS_AES_256_GCM_SHA384
-
TLS_CHACHA20_POLY1305_SH
A256
- ECDHE-ECDSA-AES128-GCM-
SHA256
- ECDHE-RSA-AES128-GCM-
SHA256
- ECDHE-ECDSA-AES256-
GCM-SHA384
- ECDHE-RSA-AES256-GCM-
SHA384
- ECDHE-ECDSA-CHACHA20-
POLY1305
Property
Type
Description
Red Hat OpenShift Lightspeed 1.0 Configure
72

- ECDHE-RSA-CHACHA20-
POLY1305
- DHE-RSA-AES128-GCM-
SHA256
- DHE-RSA-AES256-GCM-
SHA384
- DHE-RSA-CHACHA20-
POLY1305
- ECDHE-ECDSA-AES128-
SHA256
- ECDHE-RSA-AES128-SHA256
- ECDHE-ECDSA-AES128-SHA
- ECDHE-RSA-AES128-SHA
- ECDHE-ECDSA-AES256-
SHA384
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
Property
Type
Description
## OLSCONFIG API REFERENCE
73

type
string
type is one of Old, Intermediate,
Modern or Custom. Custom
provides the ability to specify
individual TLS security profile
parameters. Old, Intermediate
and Modern are TLS security
profiles based on:
https://wiki.mozilla.org/Security/
Server_Side_TLS#Recommended
_configurations
The profiles are intent based, so
they might change over time as
new ciphers are developed and
existing ciphers are found to be
insecure. Depending on precisely
which ciphers are available to a
process, the list might be reduced.
Note that the Modern profile is
currently not supported because
it is not yet well adopted by
common software libraries.
Property
Type
Description
2.1.58. .spec.ols.userDataCollection
Description
User data collection switches
Type
object
Property
Type
Description
feedbackDisabled
boolean
 
transcriptsDisabled
boolean
 
2.1.59. .spec.olsDataCollector
Description
OLSDataCollectorSpec defines allowed OLS data collector configuration.
Type
object
Red Hat OpenShift Lightspeed 1.0 Configure
74

Property
Type
Description
logLevel
string
Log level. Valid options are
DEBUG, INFO, WARNING,
ERROR and CRITICAL. Default:
"INFO".
## OLSCONFIG API REFERENCE
75

---

## Lightspeed Operate

Red Hat OpenShift Lightspeed 1.0
Operate
Using OpenShift Lightspeed
Last Updated: 2026-03-05

Red Hat OpenShift Lightspeed 1.0 Operate
Using OpenShift Lightspeed

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
This documentation provides information about operating OpenShift Lightspeed.

. . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 
Table of Contents
## OPERATING OPENSHIFT LIGHTSPEED
### USING THE CHAT WINDOW TO ASK A QUESTION
### ABOUT LIGHTSPEED CONVERSATIONS
### PROVIDING FEEDBACK FOR A CONVERSATION
### SAMPLE CONVERSATION OVERVIEW
1.4.1. Asking a general question
1.4.2. Asking related questions
1.4.3. Attaching a resource object to your question
1.4.4. Using OpenShift Lightspeed to troubleshoot alerts
### STARTING A NEW CHAT CONVERSATION
3
3
3
3
4
4
4
5
5
5
Table of Contents
1

Red Hat OpenShift Lightspeed 1.0 Operate
2
## OPERATING OPENSHIFT LIGHTSPEED
Submit questions to OpenShift Lightspeed, manage resource attachments, and resolve system alerts to
optimize your troubleshooting experience.
### USING THE CHAT WINDOW TO ASK A QUESTION
Get immediate product information and support by asking questions through the Red Hat OpenShift
Lightspeed icon.
Procedure
1. Click the Red Hat OpenShift Lightspeed icon in the lower-right corner of the screen.
This action presents the Red Hat OpenShift Lightspeed user interface.
2. Enter a question.
3. Click the Submit button.
OpenShift Lightspeed returns information based on your question.
### ABOUT LIGHTSPEED CONVERSATIONS
Use OpenShift Lightspeed to get help for OpenShift, Kubernetes, and specialized OpenShift
components such as OpenShift Virtualization and OpenShift Service Mesh
OpenShift Lightspeed ignores questions unrelated to the target topics. If OpenShift Lightspeed
misinterprets your question, try rephrasing the question for better clarity. Conversation history provides
context that OpenShift Lightspeed references when generating answers. Using specific language helps
increase the success of responses. For example, instead of asking "How do I start a virtual machine?" try
asking "How do I start a virtual machine in OpenShift Virtualization?"
Conversation history does not persist if you reload the console page. Reloading the console page
performs the same action as clicking the New Chat button. Restarting OpenShift Lightspeed erases
conversation history.
### PROVIDING FEEDBACK FOR A CONVERSATION
Send feedback to Red Hat on specific OpenShift Lightspeed interactions to improve the quality and
accuracy of future responses.
Prerequisites
You have installed the OpenShift Lightspeed Operator and deployed the OpenShift Lightspeed
service.
Procedure
1. Click the Red Hat OpenShift Lightspeed icon in the lower-right corner of the screen.
2. Enter a question into the Send a message field:
3. Click the Submit button.
OpenShift Lightspeed returns information.
## OPERATING OPENSHIFT LIGHTSPEED
3

4. To send feedback on a particular question and response, click the thumbs up or thumbs down
button.
This action presents the field that you use to enter additional information.
5. Click the Submit button.
Your rating, any text you entered, the specific question you asked OpenShift Lightspeed, and
the response are all sent to Red Hat for review.
### SAMPLE CONVERSATION OVERVIEW
Use these sample prompts and follow-up strategies to start effective conversations with OpenShift
Lightspeed and improve response accuracy.
Some examples show specific workflows to use in the interface. Ask your follow-up questions in the
same chat to help create context. Because OpenShift Lightspeed uses the whole chat, follow-up
questions help refine the results. You can get better answers by rephrasing your question or asking for
more detail.
1.4.1. Asking a general question
Ask general questions about OpenShift Container Platform using OpenShift Lightspeed to quickly find
documentation, best practices, and product information.
Procedure
1. Click the Red Hat OpenShift Lightspeed icon in the lower-right corner of the screen.
2. Enter the following question into the Send a message field:
What is an OpenShift image stream used for?
3. Click the Submit button.
OpenShift Lightspeed returns information that provides an explanation of an image stream and
details about usage.
1.4.2. Asking related questions
Ask follow-up questions in OpenShift Lightspeed to refine results and obtain specific examples based
on prior conversation history.
Procedure
1. Click the Red Hat OpenShift Lightspeed icon in the lower-right corner of the screen.
2. Enter the following question into the Send a message field:
How are OpenShift security context constraints used?
3. Click the Submit button.
OpenShift Lightspeed returns information.
4. Enter the following question into the Send a message field:
Can I control who can use a particular SCC?
5. Click the Submit button.
OpenShift Lightspeed returns refined information that has additional details.
Red Hat OpenShift Lightspeed 1.0 Operate
4

6. Enter the following question into the Send a message field:
Can you give me an example?
7. Click the Submit button.
OpenShift Lightspeed returns sample code that you can copy and use.
1.4.3. Attaching a resource object to your question
Attach a cluster resource object to your OpenShift Lightspeed query to give specific context and
receive more relevant, data-driven troubleshooting advice.
Procedure
1. Navigate to a supported resource in the OpenShift Container Platform web console. For
example, click Workloads → Pods and then click the name of a pod.
2. Click the Red Hat OpenShift Lightspeed icon in the lower-right corner of the screen.
3. Click Add in the Red Hat OpenShift Lightspeed user interface to attach a resource object.
4. Select the resource object to attach to the question.
5. Enter a question.
6. Click the Submit button.
OpenShift Lightspeed returns information based on your question.
1.4.4. Using OpenShift Lightspeed to troubleshoot alerts
Analyze and resolve cluster alerts by querying OpenShift Lightspeed for root cause explanations,
documentation links, and recommended remediation steps.
Procedure
1. In the OpenShift Container Platform web console, select Observe → Alerting.
2. Click to expand an alert row.
3. Click the alert to present it on the screen and view details.
4. Click the Red Hat OpenShift Lightspeed icon in the lower-right corner of the screen.
5. Click Attach context and select Alert.
6. In the Red Hat OpenShift Lightspeed user interface, enter the following text:
What should I do about this alert?
7. Click the Submit button.
The alert provides OpenShift Lightspeed with context when generating a response.
### STARTING A NEW CHAT CONVERSATION
Reset your OpenShift Lightspeed session by clearing the chat history to start a new conversation
without influence from the prior context.
## OPERATING OPENSHIFT LIGHTSPEED
5

When you ask follow-up questions, OpenShift Lightspeed references the conversation history for
additional context that influences replies. Whenever you start a new conversation with OpenShift
Lightspeed you should clear the chat history.
Procedure
1. In the Red Hat OpenShift Lightspeed natural language interface, click Clear chat.
This action clears the history of prior conversations.
2. Enter a question.
3. Click the Submit button.
OpenShift Lightspeed only references the new question when generating a response.
Red Hat OpenShift Lightspeed 1.0 Operate
6

---

## Lightspeed Troubleshoot

Red Hat OpenShift Lightspeed 1.0
Troubleshoot
Troubleshooting OpenShift Lightspeed
Last Updated: 2026-02-27

Red Hat OpenShift Lightspeed 1.0 Troubleshoot
Troubleshooting OpenShift Lightspeed

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
This documentation provides information about troubleshooting OpenShift Lightspeed.

. . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 
Table of Contents
## TROUBLESHOOTING OPENSHIFT LIGHTSPEED
### 502 BAD GATEWAY ERRORS IN THE INTERFACE
### OPERATOR MISSING FROM THE OPERATORHUB LIST
### THINKING MODEL GENERATES DELINEATOR PROMPT
3
3
3
3
Table of Contents
1

Red Hat OpenShift Lightspeed 1.0 Troubleshoot
2
## TROUBLESHOOTING OPENSHIFT LIGHTSPEED
Review solutions and workarounds for common installation, configuration, and operational issues
encountered with OpenShift Lightspeed.
### 502 BAD GATEWAY ERRORS IN THE INTERFACE
Avoid 502 Bad Gateway errors by waiting for the service pods to finish starting.
Give OpenShift Lightspeed and OpenShift Container Platform a few minutes to get ready after you
deploy them. Then, try using OpenShift Lightspeed again.
### OPERATOR MISSING FROM THE OPERATORHUB LIST
The OperatorHub displays the OpenShift Lightspeed Operator only for supported architectures.
Filtering prevents the Operator from appearing on anything other than the x86_64 architecture.
### THINKING MODEL GENERATES DELINEATOR PROMPT
Reasoning models use tags such as THOUGHT or reasoning to separate their inner logic from the final
answer.
OpenShift Lightspeed does not control these tags or add them to the output. This feature is part of the
model itself. Usually, you can turn off these tags in one of two ways:
Add a keyword to your prompt if the model supports it. For example, /nothink. Check the
documentation for your model to obtain the right word to use.
Change the inference server configuration settings to disable the delineator feature. For more
information, see the documentation for the inference server or for the model you are using.
## TROUBLESHOOTING OPENSHIFT LIGHTSPEED
3