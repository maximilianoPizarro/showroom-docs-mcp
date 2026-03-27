# Red Hat OpenShift AI Cloud Service 1

_Compiled from Red Hat Documentation (html-single), March 2026._



## Introduction to Red Hat OpenShift AI Cloud Service

Red Hat OpenShift AI Cloud Service
1

OpenShift AI is a platform for data scientists and developers of artificial intelligence and machine learning (AI/ML) applications

Legal Notice

Abstract

Red Hat OpenShift AI is a platform for data scientists and developers of artificial intelligence and machine learning applications.

Chapter 1. Overview of OpenShift AI

Red Hat OpenShift AI is a platform for data scientists and developers of artificial intelligence and machine learning (AI/ML) applications.

OpenShift AI provides an environment to develop, train, serve, test, and monitor AI/ML models and applications on-premise or in the cloud.

For data scientists, OpenShift AI includes Jupyter and a collection of default workbench images optimized with the tools and libraries required for model development, and the TensorFlow and PyTorch frameworks. Deploy and host your models, integrate models into external applications, and export models to host them in any hybrid cloud environment. You can enhance your data science projects on OpenShift AI by building portable machine learning (ML) workflows with data science pipelines, using Docker containers. You can also accelerate your data science experiments through the use of graphics processing units (GPUs) and Intel Gaudi AI accelerators.

For administrators, OpenShift AI enables data science workloads in an existing Red Hat OpenShift or ROSA environment. Manage users with your existing OpenShift identity provider, and manage the resources available to workbenches to ensure data scientists have what they require to create, train, and host models. Use accelerators to reduce costs and allow your data scientists to enhance the performance of their end-to-end data science workflows using graphics processing units (GPUs) and Intel Gaudi AI accelerators.

OpenShift AI has two deployment options:

Self-managed software
that you can install on-premise or in the cloud. You can install OpenShift AI Self-Managed in a self-managed environment such as OpenShift Container Platform, or in Red Hat-managed cloud environments such as Red Hat OpenShift Dedicated (with a Customer Cloud Subscription for AWS or GCP), Red Hat OpenShift Service on Amazon Web Services (ROSA classic or ROSA HCP), or Microsoft Azure Red Hat OpenShift.

For information about OpenShift AI as self-managed software on your OpenShift cluster in a connected or a disconnected environment, see
Product Documentation for Red Hat OpenShift AI Self-Managed
.

A
managed cloud service
, installed as an add-on in Red Hat OpenShift Dedicated (with a Customer Cloud Subscription for AWS or GCP) or in Red Hat OpenShift Service on Amazon Web Services (ROSA classic).

For information about OpenShift AI supported software platforms, components, and dependencies, see the
Red Hat OpenShift AI: Supported Configurations
Knowledgebase article.

For a detailed view of the release lifecycle, including the full support phase window, see the
Red Hat OpenShift AI Cloud Service Life Cycle
Knowledgebase article.

Chapter 2. Product features

Red Hat OpenShift AI provides several features for data scientists and OpenShift cluster administrators.

2.1. Features for data scientists

Containers
While tools such as JupyterLab already offer intuitive ways for data scientists to develop models on their machines, there are always inherent complexities involved with collaboration and sharing work. Moreover, using specialized hardware such as powerful GPUs can be very expensive when you have to buy and maintain your own. The Jupyter environment that is included with OpenShift AI lets you take your development environment anywhere you need it to be. Because all of the workloads are run as containers, collaboration is as easy as sharing an image with your team members, or even simply adding it to the list of default containers that they can use. As a result, GPUs and large amounts of memory are significantly more accessible, since you are no longer limited by what your laptop can support.
Integration with third-party machine learning tools
We have all run into situations where our favorite tools or services do not play well with one another. OpenShift AI is designed with flexibility in mind. You can use a wide range of open source and third-party tools with OpenShift AI. These tools support the complete machine learning lifecycle, from data engineering and feature extraction to model deployment and management.
Collaboration on Jupyter notebooks with Git
Use Jupyter’s Git interface to work collaboratively with others, and track the changes to your code.
Securely built workbench images
Choose from a default set of workbench images that are pre-configured with the tools and libraries that you need for model development. Software stacks, especially those involved in machine learning, tend to be complex systems. There are many modules and libraries in the Python ecosystem that can be used, so determining which versions of what libraries to use can be very challenging. OpenShift AI includes many packaged workbench images that have been built with insight from data scientists and recommendation engines. You can start new projects quickly on the right foot without worrying about downloading unproven and possibly insecure images from random upstream repositories.
Custom workbench images
In addition to workbench images provided and supported by Red Hat and independent software vendors (ISVs), you can configure custom workbench images that cater to your project’s specific requirements.
Data science pipelines
OpenShift AI with data science pipelines 2.0 provides an efficient way to run your data science workloads. You can standardize and automate machine learning workflows that enable you to develop and deploy your data science models.
Model serving
As a data scientist, you can deploy your trained machine-learning models to serve intelligent applications in production. Deploying or serving a model makes the model’s functions available as a service endpoint that can be used for testing or integration into applications. You have much control over how this serving is performed.

Optimize your data science models with accelerators
If you work with large data sets, you can optimize the performance of your data science models in OpenShift AI with NVIDIA graphics processing units (GPUs) or Intel Gaudi AI accelerators. Accelerators enable you to scale your work, reduce latency, and increase productivity.
Model catalog and model registry

The model catalog provides a curated library where data scientists and AI engineers can discover and evaluate the available generative AI models to find the best fit for their use cases.

A model registry acts as a central repository for AI administrators and data scientists to register, version, and manage the lifecycle of AI models before configuring them for deployment. A model registry is a key component for AI model governance.

2.2. Features for OpenShift cluster administrators

Manage users with an identity provider
OpenShift AI uses the same authentication systems as your OpenShift cluster. By default, OpenShift AI is accessible to all users listed in your identity provider and those users do not need a separate set of credentials to access OpenShift AI. Optionally, you can limit the set of users who have access by creating an OpenShift group that specifies a subset of users. You can also create an OpenShift group that identifies the list of users who have administrator access to OpenShift AI.
Manage resources with OpenShift
Use your existing OpenShift knowledge to configure and manage resources for your OpenShift AI users.
Control Red Hat usage data collection
Choose whether to allow Red Hat to collect data about OpenShift AI usage in your cluster. Usage data collection is enabled by default when you install OpenShift AI on your OpenShift cluster.
Apply autoscaling to your cluster to reduce usage costs
Use the cluster autoscaler to adjust the size of your cluster to meet its current needs and optimize costs.
Manage resource usage by stopping idle workbenches
Reduce resource usage in your OpenShift AI deployment by automatically stopping workbenches that have been idle for a period of time.
Implement model-serving runtimes
OpenShift AI provides support for model-serving runtimes. A model-serving runtime provides integration with a specified model server and the model frameworks that it supports. By default, OpenShift AI includes the OpenVINO Model Server runtime. However, if this runtime does not meet your needs (for example, if it does not support a particular model framework), you can add your own custom runtimes.
Install in a disconnected environment
OpenShift AI Self-Managed supports installation in a disconnected environment. Disconnected clusters are on a restricted network, typically behind a firewall and unable to reach the Internet. In this case, clusters cannot access the remote registries where Red Hat provided OperatorHub sources reside. In this case, you deploy the OpenShift AI Operator to a disconnected environment by using a private registry in which you have mirrored (copied) the relevant images.
Manage accelerators
Enable NVIDIA graphics processing units (GPUs) or Intel Gaudi AI accelerators in OpenShift AI and allow your data scientists to use compute-heavy workloads.

Chapter 3. Try It

Data scientists and developers can try OpenShift AI and access tutorials and activities in the
Red Hat Developer Sandbox
environment.

OpenShift cluster administrators can try OpenShift AI in your own cluster with a
60-day product trial
.

Chapter 4. Get It

Managed cloud service

You have the following options for subscribing to OpenShift AI as a managed service:

For OpenShift Dedicated, subscribe through Red Hat.

For Red Hat OpenShift Service on Amazon Web Services (ROSA), subscribe through Red Hat or subscribe through the AWS Marketplace.
Self-managed software
To get Red Hat OpenShift AI as self-managed software, sign up for it with your Red Hat account team.

Legal Notice

Copyright
© 2025 Red Hat, Inc.

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



## Release notes

Release notes | Red Hat OpenShift AI Cloud Service | 1 | Red Hat Documentation

 

 

 

 

 

 

 Updates for Red Hat OpenShift AI Cloud Service are only provided up to the end of October 2025. 

 

# Release notes

---

Red Hat OpenShift AI Cloud Service 1

## Features, enhancements, resolved issues, and known issues associated with this release

Legal Notice

Abstract

These release notes provide an overview of new features, enhancements, resolved issues, and known issues in this release of Red Hat OpenShift AI. OpenShift AI is currently available in Red Hat OpenShift Dedicated and Red Hat OpenShift Service on Amazon Web Services (ROSA).

---

## Chapter 1. Overview of OpenShift AICopy link

Red Hat OpenShift AI is a platform for data scientists and developers of artificial intelligence and machine learning (AI/ML) applications.

OpenShift AI provides an environment to develop, train, serve, test, and monitor AI/ML models and applications on-premise or in the cloud.

For data scientists, OpenShift AI includes Jupyter and a collection of default workbench images optimized with the tools and libraries required for model development, and the TensorFlow and PyTorch frameworks. Deploy and host your models, integrate models into external applications, and export models to host them in any hybrid cloud environment. You can enhance your data science projects on OpenShift AI by building portable machine learning (ML) workflows with data science pipelines, using Docker containers. You can also accelerate your data science experiments through the use of graphics processing units (GPUs) and Intel Gaudi AI accelerators.

For administrators, OpenShift AI enables data science workloads in an existing Red Hat OpenShift or ROSA environment. Manage users with your existing OpenShift identity provider, and manage the resources available to workbenches to ensure data scientists have what they require to create, train, and host models. Use accelerators to reduce costs and allow your data scientists to enhance the performance of their end-to-end data science workflows using graphics processing units (GPUs) and Intel Gaudi AI accelerators.

OpenShift AI has two deployment options:

Self-managed software that you can install on-premise or in the cloud. You can install OpenShift AI Self-Managed in a self-managed environment such as OpenShift Container Platform, or in Red Hat-managed cloud environments such as Red Hat OpenShift Dedicated (with a Customer Cloud Subscription for AWS or GCP), Red Hat OpenShift Service on Amazon Web Services (ROSA classic or ROSA HCP), or Microsoft Azure Red Hat OpenShift.

For information about OpenShift AI as self-managed software on your OpenShift cluster in a connected or a disconnected environment, see [Product Documentation for Red Hat OpenShift AI Self-Managed](https://docs.redhat.com/en/documentation/red_hat_openshift_ai_self-managed/latest).

- Legal Notice
- A managed cloud service, installed as an add-on in Red Hat OpenShift Dedicated (with a Customer Cloud Subscription for AWS or GCP) or in Red Hat OpenShift Service on Amazon Web Services (ROSA classic).

For information about OpenShift AI supported software platforms, components, and dependencies, see the [Red Hat OpenShift AI: Supported Configurations](https://access.redhat.com/articles/rhoai-supported-configs) Knowledgebase article.

For a detailed view of the release lifecycle, including the full support phase window, see the [Red Hat OpenShift AI Cloud Service Life Cycle](https://access.redhat.com/support/policy/updates/rhoai-cs/lifecycle) Knowledgebase article.

## Chapter 2. New features and enhancementsCopy link

This section describes new features and enhancements in Red Hat OpenShift AI.

### 2.1. New featuresCopy link

Model registry and model catalog general availability

OpenShift AI model registry and model catalog are now available as general availability (GA) features.

A model registry acts as a central repository for administrators and data scientists to register, version, and manage the lifecycle of AI models before configuring them for deployment. A model registry is a key component for AI model governance.

The model catalog provides a curated library where data scientists and AI engineers can discover and evaluate the available generative AI models to find the best fit for their use cases.

LLM Compressor library added to OpenShift AI workbench images and pipelines

The LLM Compressor library is now generally available and fully integrated into standard OpenShift AI workbench images and pipelines.

This library provides a supported, integrated method to optimize large language models for improved inference, particularly for deployment on vLLM, without leaving your OpenShift AI environment. You can run model compression as an interactive notebook task or as a batch job in a pipeline, which significantly reduces the hardware costs and improves the inference speeds of their generative AI workloads.

Use an existing Argo Workflows instance with pipelines

You can now configure OpenShift AI to use an existing Argo Workflows instance instead of the one included with Data Science Pipelines. This feature supports users who maintain their own Argo Workflows environments and simplifies adoption of pipelines on clusters where Argo Workflows is already deployed.

A new global configuration option disables deployment of the embedded Argo WorkflowControllers, allowing clusters that already use Argo Workflows to integrate with pipelines without conflicts. Cluster administrators can choose whether to deploy the embedded controllers or use their own Argo instance and manage both lifecycles independently. For more information, see [Configuring pipelines with your own Argo Workflows instance](https://docs.redhat.com/en/documentation/red_hat_openshift_ai_cloud_service/1/html/installing_and_uninstalling_openshift_ai_cloud_service/configuring-pipelines-with-your-own-argo-workflows-instance_install).

Support added for workbench images You can now install and upgrade Python 3.12 workbench images in OpenShift AI for your JupyterLab and code-server IDEs.

### 2.2. EnhancementsCopy link

Support for customizing OAuth proxy sidecar resource allocation

You can now customize the CPU and memory requests and limits for the OAuth proxy sidecar in workbench pods. To do this, add one or more of the following annotations to the notebooks custom resource (CR):

`notebooks.opendatahub.io/auth-sidecar-memory-limit`

If you do not specify these annotations, the sidecar uses the default values of 100m CPU and 64Mi memory to maintain backward compatibility. After you add or modify the annotations, you must restart the workbench for the new resource allocations to take effect.

The annotation values must follow the Kubernetes resource unit convention. For more information, see [Resource units in Kubernetes](https://kubernetes.io/docs/concepts/configuration/manage-resources-containers/#resource-units-in-kubernetes).

- `notebooks.opendatahub.io/auth-sidecar-cpu-request`
- `notebooks.opendatahub.io/auth-sidecar-memory-request`
- `notebooks.opendatahub.io/auth-sidecar-cpu-limit`

Enhanced workbench authentication Workbench authentication is now smoother in OpenShift AI. When you create a new workbench, a reconciler automatically generates the required`OAuthClient`, removing the need to manually grant permissions to the`oauth-proxy` container.

Support for flexible storage class management With this release, administrators can now choose any supported access mode for a storage class when adding cluster storage to a project or workbench in OpenShift AI. This enhancement removes deployment issues caused by unsupported storage classes or incorrect access mode assumptions.

Support for deployment on the Grace Hopper Arm platform OpenShift AI can now be deployed on the Grace Hopper Arm platform. This enhancement expands hardware compatibility beyond x86 architectures, enabling you to deploy and run workloads on Arm-based NVIDIA Grace Hopper systems. These systems provide a scalable, power-efficient, and high-performance environment for AI and machine-learning workloads.

Note

The following components and image variants are currently unavailable:

- The`pytorch` and`pytorch+llmcompressor` workbench and pipeline runtime images
- CUDA-accelerated Kubeflow training images
- The`fms-hf-tuning` image

Define and manage pipelines with Kubernetes API

You can now define and manage data science pipelines and pipeline versions by using the Kubernetes API, which stores them as custom resources in the cluster instead of the internal database. This enhancement makes it easier to use OpenShift GitOps (Argo CD) or similar tools to manage pipelines, while still allowing you to manage them through the OpenShift AI user interface, API, and`kfp` SDK.

This option, enabled by default, is configurable with the Store pipeline definitions in Kubernetes checkbox when you create or edit a pipeline server. OpenShift AI administrators and project owners can also configure this option by setting the`spec.apiServer.pipelineStore` field to`kubernetes` or`database` in the`DataSciencePipelinesApplication`(DSPA) custom resource. For more information, see [Defining a pipeline by using the Kubernetes API](https://docs.redhat.com/en/documentation/red_hat_openshift_ai_cloud_service/1/html/working_with_data_science_pipelines/managing-data-science-pipelines_ds-pipelines#defining-a-pipeline-by-using-the-kubernetes-api_ds-pipelines).

Support added for configuring TrustyAI global settings with the DataScienceCluster (DSC) resource Administrators can now declaratively manage settings such as LMEval’s`allowOnline` and`allowCodeExecution` through the DSC interface, with changes automatically propagated to the TrustyAI operator. This unifies TrustyAI configuration with other OpenShift AI components and removes the need for manual ConfigMap edits or Operator restarts.

Support added to move unwanted files to trash directory You can now increase your container storage by moving and permanently deleting your unwanted files to a trash directory in the Jupyter Notebook. To delete these files, click the Move to Trash icon on your Jupyter notebook toolbar and browse through your trash directory. Select the files that you would like to permanently delete, and delete them to prevent full notebook storage.

Updated workbench images A new set of workbench images is now available. These pre-built workbench images and upgraded packages include Python libraries and frameworks for data analysis and exploration, as well as CUDA and ROCm packages for accelerating compute-intensive tasks. Additionally, they feature runtimes and updated IDEs for RStudio and code-server.

## Chapter 3. Technology Preview featuresCopy link

Important

This section describes Technology Preview features in Red Hat OpenShift AI. Technology Preview features are not supported with Red Hat production service level agreements (SLAs) and might not be functionally complete. Red Hat does not recommend using them in production. These features provide early access to upcoming product features, enabling customers to test functionality and provide feedback during the development process.

For more information about the support scope of Red Hat Technology Preview features, see [Technology Preview Features Support Scope](https://access.redhat.com/support/offerings/techpreview/).

IBM Spyre AI Accelerator model serving support on x86 platforms Model serving with the IBM Spyre AI Accelerator is now available as a Technology Preview feature for x86 platforms. The IBM Spyre Operator automates installation and integrates the device plugin, secondary scheduler, and monitoring. For more information, see the [IBM Spyre Operator catalog entry](https://catalog.redhat.com/en/software/containers/ibm-aiu/spyre-operator/688a1121575e62c686a471d4).

Distributed Inference with llm-d Distributed Inference with llm-d is currently available as a Technology Preview feature. Distributed Inference with llm-d supports multi-model serving, intelligent inference scheduling, and disaggregated serving for improved GPU utilization on GenAI models. For more information, see [Deploying models by using Distributed Inference with llm-d](https://docs.redhat.com/en/documentation/red_hat_openshift_ai_cloud_service/1/html/deploying_models/deploying_models_on_the_single_model_serving_platform#deploying-models-using-distributed-inference_rhoai-user).

Build Generative AI Apps with Llama Stack on OpenShift AI

With this release, the Llama Stack Technology Preview feature enables Retrieval-Augmented Generation (RAG) and agentic workflows for building next-generation generative AI applications. It supports remote inference, built-in embeddings, and vector database operations. It also integrates with providers like TrustyAI’s provider for safety and Trusty AI’s LM-Eval provider for evaluation.

This preview includes tools, components, and guidance for enabling the Llama Stack Operator, interacting with the RAG Tool, and automating PDF ingestion and keyword search capabilities to enhance document discovery.

Centralized platform observability

Centralized platform observability, including metrics, traces, and built-in alerts, is available as a Technology Preview feature. This solution introduces a dedicated, pre-configured observability stack for OpenShift AI that allows cluster administrators to perform the following actions:

Export platform and workload metrics to external 3rd party observability tools by editing the`DataScienceClusterInitialization`(DSCI) custom resource.

You can enable this feature by integrating with the Cluster Observability Operator, Red Hat build of OpenTelemetry, and Tempo Operator. For more information, see Monitoring and observability. For more information, see [Managing observability](https://docs.redhat.com/en/documentation/red_hat_openshift_ai_cloud_service/1/html/managing_openshift_ai/managing-observability_managing-rhoai).

- View platform metrics (Prometheus) and distributed traces (Tempo) for OpenShift AI components and workloads.
- Manage a set of built-in alerts (alertmanager) that cover critical component health and performance issues.

Support for Llama Stack Distribution version 0.2.17

The Llama Stack Distribution now includes Llama-stack version 0.2.17 as Technology Preview. This feature brings a number of capabilities, including:

- Model providers: Self-hosted providers like vLLM are now automatically registered, so you no longer need to manually set INFERENCE_MODEL variables.
- Infrastructure and backends: Improved the OpenAI inference and added support for the Vector Store API.
- Error handling: Errors are now standardized, and library client initialization has been improved.
- Access Control: The Vector Store and File APIs now enforce access control, and telemetry read APIs are gated by user roles.
- Bug fixes.

Support for IBM Power accelerated Triton Inference Server

You can now enable Power architecture support for Triton inference server (CPU only) with Python and ONNX backend. You can deploy Triton inference server as a custom model serving runtime on IBM Power architecture as a Technology Preview feature in Red Hat OpenShift AI.

For details, see [Triton Inference Server image](https://quay.io/repository/powercloud/tritonserver).

Support for IBM Z accelerated Triton Inference Server

You can now enable Z architecture support for the Triton Inference Server (Telum I/Telum II) with multiple backend options, including ONNX-MLIR, Snap ML (C++), and PyTorch. The Triton Inference Server can be deployed as a custom model serving runtime on IBM Z architecture as a Technology Preview feature in Red Hat OpenShift AI.

For details, see [IBM Z accelerated Triton Inference Server](https://ibm.github.io/ibm-z-oss-hub/containers/ibmz-accelerated-for-nvidia-triton-inference-server.html).

Support for Kubernetes Event-driven Autoscaling (KEDA)

OpenShift AI now supports Kubernetes Event-driven Autoscaling (KEDA) in its KServe RawDeployment mode. This Technology Preview feature enables metrics-based autoscaling for inference services, allowing for more efficient management of accelerator resources, reduced operational costs, and improved performance for your inference services.

To set up autoscaling for your inference service in KServe RawDeployment mode, you need to install and configure the OpenShift Custom Metrics Autoscaler (CMA), which is based on KEDA.

For more information about this feature, see: [Configuring metrics-based autoscaling](https://docs.redhat.com/en/documentation/red_hat_openshift_ai_cloud_service/1/html/managing_and_monitoring_models/managing_and_monitoring_models_on_the_single_model_serving_platform#configuring-metrics-based-autoscaling_cluster-admin).

LM-Eval model evaluation UI feature TrustyAI now offers a user-friendly UI for LM-Eval model evaluations as Technology Preview. This feature allows you to input evaluation parameters for a given model and returns an evaluation-results page, all from the UI.

Use Guardrails Orchestrator with LlamaStack

You can now run detections using the Guardrails Orchestrator tool from TrustyAI with Llama Stack as a Technology Preview feature, using the built-in detection component. To use this feature, ensure TrustyAI is enabled, the FMS Orchestrator and detectors are set up, and KServe RawDeployment mode is in use for full compatibility if needed. There is no manual set up required. Then, in the`DataScienceCluster` custom resource for the Red Hat OpenShift AI Operator, set the`spec.llamastackoperator.managementState` field to`Managed`.

For more information, see [Trusty AI FMS Provider](https://github.com/trustyai-explainability/llama-stack-provider-trustyai-fms) on GitHub.

New Feature Store component

You can now install and manage Feature Store as a configurable component in OpenShift AI. Based on the open-source [Feast](https://feast.dev/) project, Feature Store acts as a bridge between ML models and data, enabling consistent and scalable feature management across the ML lifecycle.

This Technology Preview release introduces the following capabilities:

Data lineage tracking for enhanced observability

For configuration details, see [Configuring Feature Store](https://docs.redhat.com/en/documentation/red_hat_openshift_ai_cloud_service/1/html/working_with_machine_learning_features/configuring_feature_store).

- Centralized feature repository for consistent feature reuse
- Python SDK and CLI for programmatic and command-line interactions to define, manage, and retrieve features for ML models
- Feature definition and management
- Support for a wide range of data sources
- Data ingestion via feature materialization
- Feature retrieval for both online model inference and offline model training
- Role-Based Access Control (RBAC) to protect sensitive features
- Extensibility and integration with third-party data and compute providers
- Scalability to meet enterprise ML needs
- Searchable feature catalog

Enable targeted deployment of workbenches to specific worker nodes in Red Hat OpenShift AI Dashboard using node selectors

Hardware profiles are now available as a Technology Preview. The hardware profiles feature enables users to target specific worker nodes for workbenches or model-serving workloads. It allows users to target specific accelerator types or CPU-only nodes.

This feature replaces the current accelerator profiles feature and container size selector field, offering a broader set of capabilities for targeting different hardware configurations. While accelerator profiles, taints, and tolerations provide some capabilities for matching workloads to hardware, they do not ensure that workloads land on specific nodes, especially if some nodes lack the appropriate taints.

The hardware profiles feature supports both accelerator and CPU-only configurations, along with node selectors, to enhance targeting capabilities for specific worker nodes. Administrators can configure hardware profiles in the settings menu. Users can select the enabled profiles using the UI for workbenches, model serving, and Data Science Pipelines where applicable.

RStudio Server workbench image

With the RStudio Server workbench image, you can access the RStudio IDE, an integrated development environment for R. The R programming language is used for statistical computing and graphics to support data analysis and predictions.

To use the RStudio Server workbench image, you must first build it by creating a secret and triggering the`BuildConfig`, and then enable it in the OpenShift AI UI by editing the`rstudio-rhel9` image stream. For more information, see [Building the RStudio Server workbench images](https://docs.redhat.com/en/documentation/red_hat_openshift_ai_cloud_service/1/html/getting_started_with_red_hat_openshift_ai_cloud_service/creating-a-workbench-select-ide_get-started#building-the-rstudio-server-workbench-images_get-started).

Important

Disclaimer: Red Hat supports managing workbenches in OpenShift AI. However, Red Hat does not provide support for the RStudio software. RStudio Server is available through [rstudio.org](https://rstudio.org/) and is subject to their licensing terms. You should review their licensing terms before you use this sample workbench.

CUDA - RStudio Server workbench image

With the CUDA - RStudio Server workbench image, you can access the RStudio IDE and NVIDIA CUDA Toolkit. The RStudio IDE is an integrated development environment for the R programming language for statistical computing and graphics. With the NVIDIA CUDA toolkit, you can enhance your work by using GPU-accelerated libraries and optimization tools.

To use the CUDA - RStudio Server workbench image, you must first build it by creating a secret and triggering the`BuildConfig`, and then enable it in the OpenShift AI UI by editing the`rstudio-rhel9` image stream. For more information, see [Building the RStudio Server workbench images](https://docs.redhat.com/en/documentation/red_hat_openshift_ai_cloud_service/1/html/getting_started_with_red_hat_openshift_ai_cloud_service/creating-a-workbench-select-ide_get-started#building-the-rstudio-server-workbench-images_get-started).

Important

Disclaimer: Red Hat supports managing workbenches in OpenShift AI. However, Red Hat does not provide support for the RStudio software. RStudio Server is available through [rstudio.org](https://rstudio.org/) and is subject to their licensing terms. You should review their licensing terms before you use this sample workbench.

The CUDA - RStudio Server workbench image contains NVIDIA CUDA technology. CUDA licensing information is available in the [CUDA Toolkit](https://docs.nvidia.com/cuda/) documentation. You should review their licensing terms before you use this sample workbench.

Support for multinode deployment of very large models Serving models over multiple graphical processing unit (GPU) nodes when using a single-model serving runtime is now available as a Technology Preview feature. Deploy your models across multiple GPU nodes to improve efficiency when deploying large models such as large language models (LLMs). For more information, see [Deploying models by using multiple GPU nodes](https://docs.redhat.com/en/documentation/red_hat_openshift_ai_cloud_service/1/html/managing_and_monitoring_models/managing_and_monitoring_models_on_the_single_model_serving_platform#deploying-models-using-multiple-gpu-nodes_cluster-admin).

## Chapter 4. Developer Preview featuresCopy link

Important

This section describes Developer Preview features in Red Hat OpenShift AI.

Developer Preview features are not supported by Red Hat in any way and are not functionally complete or production-ready. Do not use Developer Preview features for production or business-critical workloads. Developer Preview features provide early access to functionality in advance of possible inclusion in a Red Hat product offering. Customers can use these features to test functionality and provide feedback during the development process. Developer Preview features might not have any documentation, are subject to change or removal at any time, and have received limited testing. Red Hat might provide ways to submit feedback on Developer Preview features without an associated SLA.

For more information about the support scope of Red Hat Developer Preview features, see [Developer Preview Support Scope](https://access.redhat.com/support/offerings/devpreview/).

Support for AppWrapper in Kueue AppWrapper support in Kueue is available as a Developer Preview feature. The experimental API enables the use of AppWrapper-based workloads with the distributed workloads feature.

## Chapter 5. Support removalsCopy link

This section describes major changes in support for user-facing features in Red Hat OpenShift AI. For information about OpenShift AI supported software platforms, components, and dependencies, see the [Red Hat OpenShift AI: Supported Configurations](https://access.redhat.com/articles/rhoai-supported-configs) Knowledgebase article.

### 5.1. DeprecatedCopy link

#### 5.1.1. Deprecated Kubeflow Training operator v1Copy link

The Kubeflow Training Operator (v1) is deprecated starting OpenShift AI 2.25 and is planned to be removed in a future release. This deprecation is part of our transition to Kubeflow Trainer v2, which delivers enhanced capabilities and improved functionality.

#### 5.1.2. Deprecated TrustyAI service CRD v1alpha1Copy link

Starting with OpenShift AI 2.25, the`v1apha1` version is deprecated and planned for removal in an upcoming release. You must update the TrustyAI Operator to version`v1` to receive future Operator updates.

#### 5.1.3. Deprecated KServe Serverless deployment modeCopy link

Starting with OpenShift AI 2.25, The KServe Serverless deployment mode is deprecated. You can continue to deploy models by migrating to the KServe RawDeployment mode. If you are upgrading to Red Hat OpenShift AI 3.0, all workloads that use the retired Serverless or ModelMesh modes must be migrated before upgrading.

#### 5.1.4. Deprecated LAB-tuningCopy link

Starting with OpenShift AI 2.25, the LAB-tuning feature is deprecated. If you are using LAB-tuning for large language model customization, plan to migrate to alternative fine-tuning or model customization methods as they become available.

#### 5.1.5. Deprecated embedded Kueue componentCopy link

Starting with OpenShift AI 2.24, the embedded Kueue component for managing distributed workloads is deprecated. OpenShift AI now uses the Red Hat Build of Kueue Operator to provide enhanced workload scheduling across distributed training, workbench, and model serving workloads. The deprecated embedded Kueue component is not supported in any Extended Update Support (EUS) release. To ensure workloads continue using queue management, you must migrate from the embedded Kueue component to the Red Hat Build of Kueue Operator, which requires OpenShift Container Platform 4.18 or later. To migrate, complete the following steps:

1. Install the Red Hat Build of Kueue Operator from OperatorHub.
2. Edit your`DataScienceCluster` custom resource to set the`spec.components.kueue.managementState` field to`Unmanaged`.
3. Verify that existing Kueue configurations (`ClusterQueue` and`LocalQueue`) are preserved after migration.

For detailed instructions, see [Migrating to the Red Hat build of Kueue Operator](https://docs.redhat.com/en/documentation/red_hat_openshift_ai_cloud_service/1/html/managing_openshift_ai/managing-workloads-with-kueue#migrating-to-the-rhbok-operator_kueue).

#### 5.1.6. Deprecated CodeFlare OperatorCopy link

Starting with OpenShift AI 2.24, the CodeFlare Operator is deprecated and will be removed in a future release of OpenShift AI.

Note

This deprecation does not affect the [Red Hat OpenShift AI API tiers](https://access.redhat.com/articles/7047935).

#### 5.1.7. Deprecated model registry API v1alpha1Copy link

Starting with OpenShift AI 2.24, the model registry API version`v1alpha1` is deprecated and will be removed in a future release of OpenShift AI. The latest model registry API version is`v1beta1`.

#### 5.1.8. Multi-model serving platform (ModelMesh)Copy link

Starting with OpenShift AI version 2.19, the multi-model serving platform based on ModelMesh is deprecated. You can continue to deploy models on the multi-model serving platform, but it is recommended that you migrate to the single-model serving platform.

For more information or for help on using the single-model serving platform, contact your account manager.

#### 5.1.9. Deprecated Text Generation Inference Server (TGIS)Copy link

Starting with OpenShift AI version 2.19, the Text Generation Inference Server (TGIS) is deprecated. TGIS will continue to be supported through the OpenShift AI 2.16 EUS lifecycle. Caikit-TGIS and Caikit are not affected and will continue to be supported. The out-of-the-box serving runtime template will no longer be deployed. vLLM is recommended as a replacement runtime for TGIS.

#### 5.1.10. Deprecated accelerator profilesCopy link

Accelerator profiles are now deprecated. To target specific worker nodes for workbenches or model serving workloads, use hardware profiles.

#### 5.1.11. Deprecated OpenVINO Model Server (OVMS) pluginCopy link

The CUDA plugin for the OpenVINO Model Server (OVMS) is now deprecated and will no longer be available in future releases of OpenShift AI.

#### 5.1.12. OpenShift AI dashboard user management moved from OdhDashboardConfig to Auth resourceCopy link

Previously, cluster administrators used the`groupsConfig` option in the`OdhDashboardConfig` resource to manage the OpenShift groups (both administrators and non-administrators) that can access the OpenShift AI dashboard. Starting with OpenShift AI 2.17, this functionality has moved to the`Auth` resource. If you have workflows (such as GitOps workflows) that interact with`OdhDashboardConfig`, you must update them to reference the`Auth` resource instead.

Expand

`apiVersion`

`opendatahub.io/v1alpha`

`services.platform.opendatahub.io/v1alpha1`

`kind`

`OdhDashboardConfig`

`Auth`

`name`

`odh-dashboard-config`

`auth`

Admin groups

`spec.groupsConfig.adminGroups`

`spec.adminGroups`

User groups

`spec.groupsConfig.allowedGroups`

`spec.allowedGroups`

| Table 5.1. Updated configurations | Resource | 2.16 and earlier | 2.17 and later versions |
| --- | --- | --- | --- |

Show more

#### 5.1.13. Deprecated cluster configuration parametersCopy link

When using the CodeFlare SDK to run distributed workloads in Red Hat OpenShift AI, the following parameters in the Ray cluster configuration are now deprecated and should be replaced with the new parameters as indicated.

Expand

`head_cpus`

`head_cpu_requests`,`head_cpu_limits`

`head_memory`

`head_memory_requests`,`head_memory_limits`

`min_cpus`

`worker_cpu_requests`

`max_cpus`

`worker_cpu_limits`

`min_memory`

`worker_memory_requests`

`max_memory`

`worker_memory_limits`

`head_gpus`

`head_extended_resource_requests`

`num_gpus`

`worker_extended_resource_requests`

| | Deprecated parameter | Replaced by |
| --- | --- | --- |

Show more

You can also use the new`extended_resource_mapping` and`overwrite_default_resource_mapping` parameters, as appropriate. For more information about these new parameters, see the [CodeFlare SDK documentation](https://github.com/project-codeflare/codeflare-sdk/blob/v0.18.0/src/codeflare_sdk/cluster/config.py#L43-L73)(external).

### 5.2. Removed functionalityCopy link

#### 5.2.1. Microsoft SQL Server command-line tool removalCopy link

Starting with OpenShift AI 2.24, the Microsoft SQL Server command-line tools (sqlcmd, bcp) have been removed from workbenches. You can no longer manage Microsoft SQL Server using the preinstalled command-line client.

#### 5.2.2. Model registry ML Metadata (MLMD) server removalCopy link

Starting with OpenShift AI 2.23, the ML Metadata (MLMD) server has been removed from the model registry component. The model registry now interacts directly with the underlying database by using the existing model registry API and database schema. This change simplifies the overall architecture and ensures the long-term maintainability and efficiency of the model registry by transitioning from the`ml-metadata` component to direct database access within the model registry itself.

If you see the following error for your model registry deployment, this means that your database schema migration has failed:

```plaintext
error: error connecting to datastore: Dirty database version {version}. Fix and force version.
```

You can fix this issue by manually changing the database from a dirty state to 0 before traffic can be routed to the pod. Perform the following steps:

Find the name of your model registry database pod as follows:

`kubectl get pods -n | grep model-registry-db`

Replace` ` with the namespace where your model registry is deployed.

Use`kubectl exec` to run the query on the model registry database pod as follows:

`kubectl exec -n -c mysql -- mysql -u root -p"$MYSQL_ROOT_PASSWORD" -e "USE; UPDATE schema_migrations SET dirty = 0;"`

Replace` ` with your model registry namespace and` ` with the pod name that you found in the previous step. Replace` ` with your model registry database name.

This will reset the dirty state in the database, allowing the model registry to start correctly.

#### 5.2.3. Anaconda removalCopy link

Anaconda is an open source distribution of the Python and R programming languages. Starting with OpenShift AI version 2.18, Anaconda is no longer included in OpenShift AI, and Anaconda resources are no longer supported or managed by OpenShift AI.

If you previously installed Anaconda from OpenShift AI, a cluster administrator must complete the following steps from the OpenShift command-line interface to remove the Anaconda-related artifacts:

Remove the secret that contains your Anaconda password:

`oc delete secret -n redhat-ods-applications anaconda-ce-access`

Remove the`ConfigMap` for the Anaconda validation cronjob:

`oc delete configmap -n redhat-ods-applications anaconda-ce-validation-result`

Remove the Anaconda image stream:

`oc delete imagestream -n redhat-ods-applications s2i-minimal-notebook-anaconda`

Remove the Anaconda job that validated the downloading of images:

`oc delete job -n redhat-ods-applications anaconda-ce-periodic-validator-job-custom-run`

Remove any pods related to Anaconda cronjob runs:

`oc get pods n redhat-ods-applications --no-headers=true | awk '/anaconda-ce-periodic-validator-job-custom-run*/'`

#### 5.2.4. Pipeline logs for Python scripts running in Elyra pipelines are no longer stored in S3Copy link

Logs are no longer stored in S3-compatible storage for Python scripts which are running in Elyra pipelines. From OpenShift AI version 2.11, you can view these logs in the pipeline log viewer in the OpenShift AI dashboard.

Note

For this change to take effect, you must use the Elyra runtime images provided in workbench images at version 2024.1 or later.

If you have an older workbench image version, update the Version selection field to a compatible workbench image version, for example, 2024.1, as described in [Updating a project workbench](https://docs.redhat.com/en/documentation/red_hat_openshift_ai_cloud_service/1/html/working_on_data_science_projects/using-project-workbenches_projects#updating-a-project-workbench_projects).

Updating your workbench image version will clear any existing runtime image selections for your pipeline. After you have updated your workbench version, open your workbench IDE and update the properties of your pipeline to select a runtime image.

#### 5.2.5. NVIDIA GPU Operator replaces NVIDIA GPU add-onCopy link

Previously, to enable graphics processing units (GPUs) to help with compute-heavy workloads, you installed the NVIDIA GPU add-on. OpenShift AI no longer supports this add-on.

Now, to enable GPU support, you must install the NVIDIA GPU Operator. To learn how to install the GPU Operator, see [NVIDIA GPU Operator on Red Hat OpenShift Container Platform](https://docs.nvidia.com/datacenter/cloud-native/openshift/latest/index.html)(external).

#### 5.2.6. Kubeflow Notebook Controller replaces JupyterHubCopy link

In OpenShift AI 1.15 and earlier, JupyterHub was used to create and launch basic workbenches. In OpenShift AI 1.16 and later, JupyterHub is no longer included, and its functionality is replaced by Kubeflow Notebook Controller.

This change provides the following benefits:

- Users can now immediately cancel a request, make changes, and retry the request, instead of waiting 5+ minutes for the initial request to time out. This means that users do not wait as long when requests fail, for example, when a basic workbench does not start correctly.
- The architecture no longer prevents a single user from having more than one basic workbench session, expanding future feature possibilities.
- The removal of the PostgreSQL database requirement allows for future expanded environment support in OpenShift AI.

However, this update also creates the following behavior changes:

- For cluster administrators, the administration interface for basic workbenches does not currently allow login access to data scientist users' workbenches. This is planned to be added in future releases.
- For data scientists, the JupyterHub interface URL is no longer valid. Update your bookmarks to point to the OpenShift AI Dashboard.

The JupyterLab interface is unchanged and data scientists can continue to use JupyterLab to work with their Jupyter notebook files as usual.

#### 5.2.7. HabanaAI workbench image removalCopy link

Support for the HabanaAI 1.10 workbench image has been removed. New installations of OpenShift AI from version 2.14 do not include the HabanaAI workbench image. However, if you upgrade OpenShift AI from a previous version, the HabanaAI workbench image remains available, and existing HabanaAI workbench images continue to function.

## Chapter 6. Resolved issuesCopy link

The following notable issues are resolved in Red Hat OpenShift AI.

[RHOAIENG-9418](https://issues.redhat.com/browse/RHOAIENG-9418)- Elyra raises error when you use parameters in uppercase

Previously, Elyra raised an error when you tried to run a pipeline that used parameters in uppercase. This issue is now resolved.

[RHOAIENG-30493](https://issues.redhat.com/browse/RHOAIENG-30493)- Error creating a workbench in a Kueue-enabled project

Previously, when using the dashboard to create a workbench in a Kueue-enabled project, the creation failed if Kueue was disabled on the cluster or if the selected hardware profile was not associated with a LocalQueue. In this case, the required LocalQueue could not be referenced, the admission webhook validation failed, and an error message was shown. This issue has been resolved.

[RHOAIENG-32942](https://issues.redhat.com/browse/RHOAIENG-32942)- Elyra requires unsupported filters on the REST API when pipeline store is Kubernetes

Before this update, when the pipeline store was configured to use Kubernetes, Elyra required equality (`eq`) filters that were not supported by the REST API. Only substring filters were supported in this mode. As a result, pipelines created and submitted through Elyra from a workbench could not run successfully. This issue has been resolved.

[RHOAIENG-32897](https://issues.redhat.com/browse/RHOAIENG-32897)- Pipelines defined with the Kubernetes API and invalid`platformSpec` do not appear in the UI or run

Before this update, when a pipeline version defined with the Kubernetes API included an empty or invalid`spec.platformSpec` field (for example,`{}` or missing the`kubernetes` key), the system misidentified the field as the pipeline specification. As a result, the REST API omitted the`pipelineSpec`, which prevented the pipeline version from being displayed in the UI and from running. This issue is now resolved.

[RHOAIENG-31386](https://issues.redhat.com/browse/RHOAIENG-31386)- Error deploying an Inference Service with`authenticationRef`

Before this update, when deploying an InferenceService with`authenticationRef` under external metrics, the`authenticationRef` field was removed. This issue is now resolved.

[RHOAIENG-33914](https://issues.redhat.com/browse/RHOAIENG-33914)- LM-Eval Tier2 task test failures

Previously, there could be failures with LM-Eval Tier2 task tests because the Massive Multitask Language Understanding Symbol Replacement (MMLUSR) tasks were broken. This issue is resolved witih the latest version of the`trustyai-service-operator`.

[RHOAIENG-35532](https://issues.redhat.com/browse/RHOAIENG-35532)- Unable to deploy models with`HardwareProfiles` and GPU

Before this update, the HardwareProfile to use GPU for model deployment had stopped working. The issue is now resolved.

[RHOAIENG-4570](https://issues.redhat.com/browse/RHOAIENG-4570)- Existing Argo Workflows installation conflicts with install or upgrade

Previously, installing or upgrading OpenShift AI on a cluster that already included an existing Argo Workflows instance could cause conflicts with the embedded Argo components deployed by Data Science Pipelines. This issue has been resolved. You can now configure OpenShift AI to use an existing Argo Workflows instance, enabling clusters that already run Argo Workflows to integrate with Data Science Pipelines without conflicts.

[RHOAIENG-35623](https://issues.redhat.com/browse/RHOAIENG-35623)- Model deployment fails when using hardware profiles

Previously, model deployments that used hardware profiles failed because the Red Hat OpenShift AI Operator did not inject the`tolerations`,`nodeSelector`, or`identifiers` from the hardware profile into the underlying`InferenceService` when manually creating`InferenceService` resources. As a result, the model deployment pods could not be scheduled to suitable nodes and the deployment fails to enter a ready state. This issue is now resolved.

[OCPBUGS-44432](https://issues.redhat.com/browse/OCPBUGS-44432)- ImageStream unable to import image tags in a disconnected OpenShift environment

Before this update, if you used the`ImageTagMirrorSet`(ITMS) or`ImageDigestMirrorSet`(IDMS) in a disconnected OpenShift environment, the ImageStream resource prevented the mirror from importing the image, and a RHOAI workbench instance could not be created. This issue is now resolved in OpenShift Container Platform 4.19.13 or later. Update your OpenShift instances to 4.19.13 or later to avoid this issue.

[RHOAIENG-31248](https://issues.redhat.com/browse/RHOAIENG-31248)- KServe http: TLS handshake error

Previously, the OpenShift CA auto-injection in the`localmodelcache` validation webhook configuration was missing the necessary annotation, leading to repeated TLS handshake errors. This issue is now resolved.

[RHOAIENG-31376](https://issues.redhat.com/browse/RHOAIENG-31376)- Inference service creation using vLLM runtime fails on IBM Power cluster

Previously, when you attempted to create an inference service using the vLLM runtime on an IBM Power cluster, it failed with the following error:`OpNamespace' '_C_utils' object has no attribute 'init_cpu_threads_env` error. This issue is now resolved.

[RHOAIENG-31377](https://issues.redhat.com/browse/RHOAIENG-31377)- Inference service creation fails on IBM Power cluster

Previously, when you attempted to create an inference service using the vLLM runtime on an IBM Power cluster, it failed with the following error:`ValueError: 'aimv2' is already used by a Transformers config, pick another name`. This issue is now resolved.

[RHOAIENG-31498](https://issues.redhat.com/browse/RHOAIENG-31498)- Incorrect inference URL in LlamaStack LMEval provider

Before this update, when you ran evaluations on Llama Stack using the LMEval provider, the evaluation jobs erroneously used the model server endpoint as`v1/openai/v1/completions`. This resulted in a job failure because the correct model server endpoint was`v1/completions`. This issue is now resolved.

[RHOAIENG-31536](https://issues.redhat.com/browse/RHOAIENG-31536)- Prometheus configuration not reconciled properly

Before this update, the Monitoring resource did not reconcile properly and showed a "Not Ready" status when upgrading to or installing 2.23. This issue occurred because the resource required the OpenTelemetry and Cluster Observability Operators to be installed, even if no new monitoring or tracing configurations were added to the`DSCInitialization` resource. As a result, Prometheus configuration did not reconcile and led to empty or outdated alert configurations. This issue is now resolved.

[RHOAIENG-4148](https://issues.redhat.com/browse/RHOAIENG-4148)- Standalone notebook fails to start due to character length

Previously, the notebook controller logic did not proactively check username lengths before it attempted to create resources. The notebook controller creates OpenShift resources using your username directly. As a result, if the combined name of the OpenShift Route and namespace exceeded the 63-character limit for DNS subdomains, the creation of the OpenShift Route failed with the following validation error:`spec.host: ... must be no more than 63 characters`. Without the Route, the dependent OAuthClient could not be configured, and workbenches could not start.

With this release, the notebook controller’s logic has been updated to proactively check name character lengths before creating resources. For Routes, if the combined length of the notebook name and namespace would exceed the 63-character limit, the controller now creates the Route using the`generateName` field with a prefix of`nb-`. For StatefulSets, if the notebook name is longer than 52 characters, the controller also uses`generateName: "nb-"` to prevent naming conflicts.

[RHOAIENG-3913](https://issues.redhat.com/browse/RHOAIENG-3913)- Red Hat OpenShift AI Operator incorrectly shows`Degraded` condition of`False` with an error

Previously, if you had enabled the KServe component in the DataScienceCluster (DSC) object used by the OpenShift AI Operator, but had not installed the dependent Red Hat OpenShift Service Mesh and Red Hat OpenShift Serverless Operators, the`kserveReady` condition in the DSC object correctly showed that KServe is not ready. However, the`Degraded` condition incorrectly showed a value of`False`. This issue is now resolved.

[RHOAIENG-29352](https://issues.redhat.com/browse/RHOAIENG-29352)- Missing Documentation and Support menu items

Previously, in the OpenShift AI top navigation bar, when you clicked the help icon (), the menu contained only the About menu item and the Documentation and Support menu items were missing. This issue is now resolved.

[RHAIENG-496](https://issues.redhat.com/browse/RHAIENG-496)- Error creating LlamaStackDistribution as a non-administrator user

Previously, non-administrator requests failed due to insufficient role-based access control (RBAC) as the deployed role definitions were outdated or incomplete for the current Llama Stack resources (for example, the`LlamaStackDistribution` CRD). This issue is now resolved.

[RHOAIENG-27676](https://issues.redhat.com/browse/RHOAIENG-27676)- Accelerator profile does not work correctly with deleted case

If you deleted your accelerator profile after creating a workbench, deployment, or model server, the Edit page did not use existing settings and showed the wrong accelerator profile. This issue is now resolved.

[RHOAIENG-25733](https://issues.redhat.com/browse/RHOAIENG-25733)- Accelerator profile does not work correctly with duplicate name

When you created a workbench, deployment, or model and used the same name for the project-scoped Accelerator profile as the global-scoped Accelerator profile, the Edit page and server form displayed incorrect labels in the respective tables and form.

[RHOAIENG-26537](https://issues.redhat.com/browse/RHOAIENG-26537)- Users cannot access the dashboard after installing OpenShift AI 2.21

After you installed OpenShift AI 2.21 and created a`DataScienceCluster` on a new cluster, you could not access the dashboard because the`Auth` custom resource was created without the default group configuration. This issue is now resolved.

[RHOAIENG-26263](https://issues.redhat.com/browse/RHOAIENG-26263)- Node selector not cleared when changing the hardware profile for a workbench or model deployment

If you edited an existing workbench or model deployment to change the hardware profile from one that included a node selector to one that did not, the previous node placement settings could not be removed. With this release, the issue is resolved.

[RHOAIENG-26099](https://issues.redhat.com/browse/RHOAIENG-26099)- Environment variable HTTP_PROXY and HTTPS_PROXY added to notebooks

Previously, the notebook controller injected a cluster-wide OpenShift Proxy configuration to all newly created and restarted workbenches. With this release, proxy configurations are not injected unless a cluster administrator enables proxy configuration through the ConfigMap.

To enable proxy configuration, run the following command:

```plaintext
$ oc create configmap notebook-controller-setting-config --from-literal=INJECT_CLUSTER_PROXY_ENV=true -n redhat-ods-applications
```

Important

Any change to the config map`INJECT_CLUSTER_PROXY_ENV` key is propagated only after the`odh-notebook-controller` pod is recreated. To update the behavior, you need to either delete the relevant pod or perform a deployment rollout.

To delete the pod, run the following command:

```plaintext
$ oc delete pod -l app=odh-notebook-controller -A
```

To perform a deployment rollout, run the following command:

```plaintext
$ oc rollout restart -n redhat-ods-applications deployment/odh-notebook-controller-manager
```

[RHOAIENG-23475](https://issues.redhat.com/browse/RHOAIENG-23475)- Inference requests on IBM Power in a disconnected environment fail with a timeout error

Previously, when you used the IBM Power architecture to send longer prompts of more than 100 input tokens to the inference service, there was no response from the inference service. With this release, the issue is resolved.

[RHOAIENG-20595](https://issues.redhat.com/browse/RHOAIENG-20595)- Pipelines tasks fail to run when defining an`http_proxy` environment variable

The pipeline tasks failed to run if you attempted to set the`http_proxy` or`https_proxy` environment variables in a pipeline task. With this release, the issue is resolved.

[RHOAIENG-16568](https://issues.redhat.com/browse/RHOAIENG-16568)- Unable to download notebook as a PDF from JupyterLab Workbenches

Previously, you could not download a notebook as a PDF file in Jupyter. With this release, the issue is resolved.

[RHOAIENG-14271](https://issues.redhat.com/browse/RHOAIENG-14271)- Compatibility errors occur when using different Python versions in Ray clusters with Jupyter notebooks

Previously, when you used Python version 3.11 in a Jupyter notebook and then created a Ray cluster, the cluster defaulted to a workbench image that contained both Ray version 2.35 and Python version 3.9, which caused compatibility errors. With this release, the issue is resolved.

[RHOAIENG-7947](https://issues.redhat.com/browse/RHOAIENG-7947)- Model serving fails during query in KServe

Previously, if you initially installed the ModelMesh component and enabled the multi-model serving platform, but later installed the KServe component and enable the single-model serving platform, inference requests to models deployed on the single-model serving platform could have failed. This issue no longer occurs.

[RHOAIENG-580](https://issues.redhat.com/browse/RHOAIENG-580)(previously documented as [RHODS-9412](https://issues.redhat.com/browse/RHODS-9412)) - Elyra pipeline fails to run if workbench is created by a user with edit permissions

If you were granted edit permissions for a project and created a project workbench, you saw the following behavior:

If you tried to use the workbench to run an Elyra pipeline, Jupyter showed an`Error making request` message that described failed initialization.

With this release, these issues are resolved.

- During the workbench creation process, you received an`Error creating workbench` message related to the creation of Kubernetes role bindings.
- Despite the preceding error message, OpenShift AI still created the workbench. However, the error message meant that you were not able to use the workbench to run Elyra data science pipelines.

[RHOAIENG-24682](https://issues.redhat.com/browse/RHOAIENG-24682)- [vLLM-Cuda] Unable to deploy model on FIPS enabled cluster

Previously, if you deployed a model by using the vLLM NVIDIA GPU ServingRuntime for KServe or vLLM ServingRuntime Multi-Node for KServe runtimes on NVIDIA accelerators in a FIPS-enabled cluster, the deployment could fail. This issue is now resolved.

[RHOAIENG-23596](https://issues.redhat.com/browse/RHOAIENG-23596)- Inference requests on IBM Power with longer prompts to the inference service fail with a timeout error

Previously, when using the IBM Power architecture to send longer prompts of more than 100 input tokens to the inference service, there was no response from the inference service. This issue no longer occurs.

[RHOAIENG-24886](https://issues.redhat.com/browse/RHOAIENG-24886)- Cannot deploy OCI model when Model URI field includes prefix

Previously, when deploying an OCI model, if you pasted the complete URI in the Model URI field and then moved the cursor to another field, the URL prefix (for example,`http://`) was removed from the Model URI field, but it was included in the`storageUri` value in the`InferenceService` resource. As a result, you could not deploy the OCI model. This issue is now resolved.

[RHOAIENG-24104](https://issues.redhat.com/browse/RHOAIENG-24104)- KServe reconciler should only deploy certain resources when Authorino is installed

Previously, when Authorino was not installed, Red Hat OpenShift AI applied the`AuthorizationPolicy` and`EnvoyFilter` resources to the KServe serverless deployment mode. This could block some inference requests. This issue is now resolved.

[RHOAIENG-23562](https://issues.redhat.com/browse/RHOAIENG-23562)- TrustyAIService TLS handshake error in FIPS clusters

Previously, when using a FIPS cluster that uses an external route to send a request to the TrustyAIService, a TLS handshake error appeared in the logs and the request was not processed. This issue is now resolved.

[RHOAIENG-23169](https://issues.redhat.com/browse/RHOAIENG-23169)- StorageInitializer fails to download models from Hugging Face repository

Previously, deploying models from Hugging Face in a KServe environment using the`hf://` protocol failed when the cluster lacked built-in support for this protocol. Additionally, the storage initializer`InitContainer` in KServe could encounter a`PermissionError` error because of insufficient write permissions in the default cache directory (`/.cache)`. This issue is now resolved.

[RHOAIENG-22965](https://issues.redhat.com/browse/RHOAIENG-22965)- Data science pipeline task fails when optional input parameters are not set

Previously, when a pipeline had optional input parameters, creating a pipeline run and tasks with unset parameters failed with the following error:

```plaintext
failed: failed to resolve inputs: resolving input parameter optional_input with spec component_input_parameter:"parameter_name": parent DAG does not have input parameter
```

This issue is now resolved.

[RHOAIENG-22439](https://issues.redhat.com/browse/RHOAIENG-22439)- cuda-rstudio-rhel9 cannot be built

Previously, when building the RStudio Server workbench images, building the`cuda-rstudio-rhel9` failed with the following error:

```plaintext
Package supervisor-4.2.5-6.el9.noarch is already installed.
Dependencies resolved.
Nothing to do.
Complete!
.M...U...    /run/supervisor
error: build error: building at STEP "RUN yum -y module enable nginx:$NGINX_VERSION &&     INSTALL_PKGS="nss_wrapper bind-utils gettext hostname nginx nginx-mod-stream nginx-mod-http-perl fcgiwrap initscripts chkconfig supervisor" &&     yum install -y --setopt=tsflags=nodocs $INSTALL_PKGS &&     rpm -V $INSTALL_PKGS &&     nginx -v 2>&1 | grep -qe "nginx/$NGINX_VERSION\." && echo "Found VERSION $NGINX_VERSION" &&     yum -y clean all --enablerepo='*'": while running runtime: exit status 1
```

This issue is now resolved.

[RHOAIENG-21274](https://issues.redhat.com/browse/RHOAIENG-21274)- Connection type changes back to S3 or URI when deploying a model with an OCI connection type

Previously, if you deployed a model that was using the S3 or URI connection type in a project with no matching connections, the Create new connection section was pre-populated using data from the model location of the S3 or URI storage location. If you changed the connection type to OCI and entered a value in the Model URI field, the connection type changed back to S3 or URI. This issue is now resolved.

[RHOAIENG-6486](https://issues.redhat.com/browse/RHOAIENG-6486)- Pod labels, annotations, and tolerations cannot be configured when using the Elyra JupyterLab extension with the TensorFlow 2024.1 notebook image

Previously, using TensorFlow-based workbench images did not allow users to use pod labels, annotations, and tolerations when using the Elyra JupyterLab extension. With the 2025.1 images, the TensorFlow-based workbench is upgraded with the Kubeflow pipeline SDK (kfp). With the upgraded SDK, you can set pod labels, annotations, and tolerations when using the Elyra extension to schedule the Data Science pipelines.

[RHOAIENG-21197](https://issues.redhat.com/browse/RHOAIENG-21197)- Deployment failure when using vLLM runtime on AMD GPU accelerators in a FIPS-enabled cluster

Previously, when deploying a model by using the vLLM runtime on AMD GPU accelerators in a FIPS-enabled cluster, the deployment could fail. This issue is now resolved.

[RHOAIENG-20245](https://issues.redhat.com/browse/RHOAIENG-20245)- Certain model registry operations remove custom properties from the registered model and version

Previously, editing the description, labels, or properties of a model version removed labels and custom properties from the associated model. Deploying a model version, or editing its model source format, removed labels and custom properties from the version and from the associated model. This issue is now resolved.

[RHOAIENG-19954](https://issues.redhat.com/browse/RHOAIENG-19954)- Kueue alerts not monitored in OpenShift

Previously, in the OpenShift console, Kueue alerts were not monitored. The new`ServiceMonitor` resource rejected the usage of the`BearerTokenFile` field, which meant that Prometheus did not have the required permissions to scrape the target. As a result, the Kueue alerts were not shown on the Observe → Alerting page, and the Kueue targets were not shown on the Observe → Targets page. This issue is now resolved.

[RHOAIENG-19716](https://issues.redhat.com/browse/RHOAIENG-19716)- The`system-authenticated` user group cannot be removed by using the dashboard

Previously, after installing or upgrading Red Hat OpenShift AI, the`system-authenticated` user group displayed in Settings > User management under Data science user groups. If you removed this user group from Data science user groups and saved the changes, the group was erroneously added again. This issue is now resolved.

[RHOAIENG-18238](https://issues.redhat.com/browse/RHOAIENG-18238)- Inference endpoints for deployed models return 403 error after upgrading the Authorino Operator

Previously, after upgrading the Authorino Operator, the automatic Istio sidecar injection may not have been reapplied. Without the sidecar, Authorino was not correctly integrated into the service mesh, and caused inference endpoint requests to fail with an HTTP 403 error. This issue is now resolved.

[RHOAIENG-11371](https://issues.redhat.com/browse/RHOAIENG-11371)- Incorrect run status reported for runs using ExitHandler

Previously, when using pipeline exit handlers (`dsl.ExitHandler`), if a task inside the handle failed but the exit task succeeded, the overall pipeline run status was inaccurately reported as`Succeeded` instead of`Failed`. This issue is now resolved.

[RHOAIENG-16146](https://issues.redhat.com/browse/RHOAIENG-16146)- Connection sometimes not preselected when deploying a model from model registry

Previously, when deploying a model from a model registry, the object storage connection (previously called data connection) might not have been preselected. This issue is now resolved.

[RHOAIENG-19711](https://issues.redhat.com/browse/RHOAIENG-19711)- Kueue-controller-manager uses old metrics port after upgrade from 2.16.0 to 2.17.0

Previously, after upgrading, the Kueue Operator continued to use the old port (8080) instead of the new port (8443) for metrics. As a result, the OpenShift console Observe > Targets page showed that the status of the Kueue Operator was`Down`. This issue is now resolved.

[RHOAIENG-19261](https://issues.redhat.com/browse/RHOAIENG-19261)- The TrustyAI installation might fail due to missing custom resource definitions (CRDs)

Previously, when installing or upgrading OpenShift AI, the TrustyAI installation might have failed due to missing`InferenceService` and`ServingRuntime` CRDs. As a result, the Trusty AI controller went into the`CrashLoopBackOff` state. This issue is now resolved.

[RHOAIENG-18933](https://issues.redhat.com/browse/RHOAIENG-18933)- Increased workbench image size can delay workbench startup

Previously, as a result of the presence of the`kubeflow-training` Python SDK in the 2024.2 workbench images, the workbench image size was increased and may have caused a delay when starting the workbench. This issue is now resolved.

[RHOAIENG-18884](https://issues.redhat.com/browse/RHOAIENG-18884)- Enabling NIM account setup is incomplete

Previously, when you tried to enable the NVIDIA NIM model serving platform, the`odh-model-controller` deployment started before the NIM account setup was complete. As a result, the NIM account setup was incomplete and the platform was not enabled. This issue is now resolved.

[RHOAIENG-18675](https://issues.redhat.com/browse/RHOAIENG-18675)- Workbenches component fails after upgrading

Previously, when upgrading to OpenShift AI 1, the workbench component did not upgrade correctly. Specifically,`BuildConfigs` and resources that follow it (for example, RStudio`BuildConfigs` and ROCm`imagestreams`) were not updated, which caused the workbench component reconciliation in the`DataScienceCluster` to fail. This issue is now resolved.

[RHOAIENG-15123](https://issues.redhat.com/browse/RHOAIENG-15123)(also documented as [RHOAIENG-10790](https://issues.redhat.com/browse/RHOAIENG-10790) and [RHOAIENG-14265](https://issues.redhat.com/browse/RHOAIENG-14265)) - Pipelines schedule might fail after upgrading

Previously, when you upgraded to OpenShift AI 1, any data science pipeline scheduled runs that existed before the upgrade might fail to execute, resulting in an error message in the task pod. This issue is now resolved.

[RHOAIENG-16900](https://issues.redhat.com/browse/RHOAIENG-16900)- Space-separated format in serving-runtime arguments can cause deployment failure

Previously, when deploying models, using a space-separated format to specify additional serving runtime arguments could cause`unrecognized arguments` errors. This issue is now resolved.

[RHOAIENG-16073](https://issues.redhat.com/browse/RHOAIENG-16073)- Attribute error when retrieving the job client for a cluster object

Previously, when initializing a cluster with the`get_cluster` method, assigning`client = cluster.job_client` sometimes resulted in an`AttributeError: 'Cluster' object has no attribute '_job_submission_client'` error. This issue is now resolved.

[RHOAIENG-15773](https://issues.redhat.com/browse/RHOAIENG-15773)- Cannot add a new model registry user

Previously, when managing the permissions of a model registry, you could not add a new user, group, or project as described in [Managing model registry permissions](https://docs.redhat.com/en/documentation/red_hat_openshift_ai_cloud_service/1/html/managing_model_registries/managing-model-registry-permissions_managing-model-registries). An`HTTP request failed` error was displayed. This issue is now resolved.

[RHOAIENG-14197](https://issues.redhat.com/browse/RHOAIENG-14197)- Tooltip text for CPU and Memory graphs is clipped and therefore unreadable

Previously, when you hovered the cursor over the CPU and Memory graphs in the Top resource-consuming distributed workloads section on the Project metrics tab of the Distributed Workloads Metrics page, the tooltip text was clipped, and therefore unreadable. This issue is now resolved.

[RHOAIENG-11024](https://issues.redhat.com/browse/RHOAIENG-11024)- Resources entries get wiped out after removing opendatahub.io/managed annotation

Previously, manually removing the`opendatahub.io/managed` annotation from any component deployment YAML file might have caused`resource` entry values in the file to be erased. This issue is now resolved.

[RHOAIENG-8102](https://issues.redhat.com/browse/RHOAIENG-8102)- Incorrect requested resources reported when cluster has multiple cluster queues

Previously, when a cluster had multiple cluster queues, the resources requested by all projects was incorrectly reported as zero instead of the true value. This issue is now resolved.

[RHOAIENG-16484](https://issues.redhat.com/browse/RHOAIENG-16484)- vLLM server engine for Gaudi accelerators fails after a period of inactivity

Previously, when using the vLLM ServingRuntime with Gaudi accelerators support for KServe model-serving runtime on a cluster equipped with Gaudi hardware, the vLLM server could fail with a`TimeoutError` message after a period of inactivity where it was not processing continuous inference requests. This issue no longer occurs.

[RHOAIENG-15033](https://issues.redhat.com/browse/RHOAIENG-15033)- Model registry instances do not restart or update after upgrading OpenShift AI

Previously, when you upgraded OpenShift AI, existing instances of the model registry component were not updated, which caused the instance pods to use older images than the ones referenced by the operator pod. This issue is now resolved.

[RHOAIENG-15008](https://issues.redhat.com/browse/RHOAIENG-15008)- Error when creating a bias metric from the CLI without a request name

Previously, the user interface sometimes displayed an error message when you viewed bias metrics if the`requestName` parameter was not set. If you used the user interface to view bias metrics, but wanted to configure them through the CLI, you had to specify a`requestName` parameter within your payload. This issue is now resolved.

[RHOAIENG-14986](https://issues.redhat.com/browse/RHOAIENG-14986)- Incorrect package path causes`copy_demo_nbs` to fail

Previously, the`copy_demo_nbs()` function of the CodeFlare SDK failed because of an incorrect path to the SDK package. Running this function resulted in a`FileNotFound` error. This issue is now resolved.

[RHOAIENG-14552](https://issues.redhat.com/browse/RHOAIENG-14552)- Workbench or notebook OAuth proxy fails with FIPS on OpenShift Container Platform 4.16

Previously, when using OpenShift 4.16 or newer in a FIPS-enabled cluster, connecting to a running workbench failed because the connection between the internal component`oauth-proxy` and the OpenShift ingress failed with a TLS handshake error. When opening a workbench, the browser showed an "Application is not available" screen without any additional diagnostics. This issue is now resolved.

[RHOAIENG-14095](https://issues.redhat.com/browse/RHOAIENG-14095)- The dashboard is temporarily unavailable after the installing OpenShift AI Operator

Previously, after you installed the OpenShift AI Operator, the OpenShift AI dashboard was unavailable for approximately three minutes. As a result, a Cannot read properties of undefined page sometimes appeared. This issue is now resolved.

[RHOAIENG-13633](https://issues.redhat.com/browse/RHOAIENG-13633)- Cannot set a serving platform for a project without first deploying a model from outside of the model registry

Previously, you could not set a serving platform for a project without first deploying a model from outside of the model registry. You could not deploy a model from a model registry to a project unless the project already had single-model or multi-model serving selected. The only way to select single-model or multi-model serving from the OpenShift AI UI was to first deploy a model or model server from outside the registry. This issue is now resolved.

[RHOAIENG-545](https://issues.redhat.com/browse/RHOAIENG-545)- Cannot specify a generic default node runtime image in JupyterLab pipeline editor

Previously, when you edited an Elyra pipeline in the JupyterLab IDE pipeline editor, and you clicked the PIPELINE PROPERTIES tab, and scrolled to the Generic Node Defaults section and edited the Runtime Image field, your changes were not saved. This issue is now resolved.

[RHOAIENG-14571](https://issues.redhat.com/browse/RHOAIENG-14571)- Data Science Pipelines API Server unreachable in managed IBM Cloud OpenShift OpenShift AI installation

Previously, when configuring a data science pipeline server, communication errors that prevented successful interaction with the pipeline server occurred. This issue is now resolved.

[RHOAIENG-14195](https://issues.redhat.com/browse/RHOAIENG-14195)- Ray cluster creation fails when deprecated head_memory parameter is used

Previously, if you included the deprecated`head_memory` parameter in your Ray cluster configuration, the Ray cluster creation failed. This issue is now resolved.

[RHOAIENG-11895](https://issues.redhat.com/browse/RHOAIENG-11895)- Unable to clone a GitHub repo in JupyterLab when configuring a custom CA bundle using`|-`

Previously, if you configured a custom Certificate Authority (CA) bundle in the`DSCInitialization`(DSCI) object using`|-`, cloning a repo from JupyterLab failed. This issue is now resolved.

[RHOAIENG-1132](https://issues.redhat.com/browse/RHOAIENG-1132)(previously documented as [RHODS-6383](https://issues.redhat.com/browse/RHODS-6383)) - An`ImagePullBackOff` error message is not displayed when required during the workbench creation process

Previously, pods experienced issues pulling container images from the container registry. When an error occurred, the relevant pod entered into an`ImagePullBackOff` state. During the workbench creation process, if an`ImagePullBackOff` error occurred, an appropriate message was not displayed. This issue is now resolved.

[RHOAIENG-13327](https://issues.redhat.com/browse/RHOAIENG-13327)- Importer component (dsl.importer) prevents pipelines from running

Pipelines could not run when using the data science pipelines importer component,`dsl.importer`. This issue is now resolved.

[RHOAIENG-14652](https://issues.redhat.com/browse/RHOAIENG-14652)-`kfp-client` unable to connect to the pipeline server on OpenShift Container Platform 4.16 and later

In OpenShift 4.16 and later FIPS clusters, data science pipelines were accessible through the OpenShift AI Dashboard. However, connections to the pipelines API server from the KFP SDK failed due to a TLS handshake error. This issue is now resolved.

[RHOAIENG-10129](https://issues.redhat.com/browse/RHOAIENG-10129)- Notebook and Ray cluster with matching names causes secret resolution failure

Previously, if you created a notebook and a Ray cluster that had matching names in the same namespace, one controller failed to resolve its secret because the secret already had an owner. This issue is now resolved.

[RHOAIENG-7887](https://issues.redhat.com/browse/RHOAIENG-7887)- Kueue fails to monitor RayCluster or PyTorchJob resources

Previously, when you created a`DataScienceCluster` CR with all components enabled, the Kueue component was installed before the Ray component and the Training Operator component. As a result, the Kueue component did not monitor`RayCluster` or`PyTorchJob` resources. When a user created`RayCluster` or`PyTorchJob` resources, Kueue did not control the admission of those resources. This issue is now resolved.

[RHOAIENG-583](https://issues.redhat.com/browse/RHOAIENG-583)(previously documented as [RHODS-8921](https://issues.redhat.com/browse/RHODS-8921) and [RHODS-6373](https://issues.redhat.com/browse/RHODS-6373)) - You cannot create a pipeline server or start a workbench when cumulative character limit is exceeded

When the cumulative character limit of a data science project name and a pipeline server name exceeded 62 characters, you were unable to successfully create a pipeline server. Similarly, when the cumulative character limit of a data science project name and a workbench name exceeded 62 characters, workbenches failed to start. This issue is now resolved.

Incorrect logo on dashboard after upgrading

Previously, after upgrading from OpenShift AI 2.11 to OpenShift AI 2.12, the dashboard could incorrectly display the Open Data Hub logo instead of the Red Hat OpenShift AI logo. This issue is now resolved.

[RHOAIENG-11297](https://issues.redhat.com/browse/RHOAIENG-11297)- Authentication failure after pipeline run

Previously, during the execution of a pipeline run, a connection error could occur due to a certificate authentication failure. This certificate authentication failure could be caused by the use of a multi-line string separator for`customCABundle` in the`default-dsci` object, which was not supported by data science pipelines. This issue is now resolved.

[RHOAIENG-11232](https://issues.redhat.com/browse/RHOAIENG-11232)- Distributed workloads: Kueue alerts do not provide runbook link

After a Kueue alert fires, the cluster administrator can click Observe → Alerting → Alerts and click the name of the alert to open its Alert details page. On the Alert details page, the Runbook section now provides a link to the appropriate runbook to help to diagnose and resolve the issues that triggered the alert. Previously, the runbook link was missing.

[RHOAIENG-10665](https://issues.redhat.com/browse/RHOAIENG-10665)- Unable to query Speculating with a draft model for granite model

Previously, you could not use speculative decoding on the`granite-7b` model and`granite-7b-accelerator` draft model. When querying these models, the queries failed with an internal error. This issue is now resolved.

[RHOAIENG-9481](https://issues.redhat.com/browse/RHOAIENG-9481)- Pipeline runs menu glitches when clicking action menu

Previously, when you clicked the action menu (⋮) next to a pipeline run on the Experiments > Experiments and runs page, the menu that appeared was not fully visible, and you had to scroll to see all of the menu items. This issue is now resolved.

[RHOAIENG-8553](https://issues.redhat.com/browse/RHOAIENG-8553)- Workbench created with custom image shows`!Deleted` flag

Previously, if you disabled the internal image registry on your OpenShift cluster and then created a workbench with a custom image that was imported by using the image tag, for example:`quay.io/my-wb-images/my-image:tag`, a`!Deleted` flag was shown in the Notebook image column on the Workbenches tab of the Data science projects page. If you stopped the workbench, you could not restart it. This issue is now resolved.

[RHOAIENG-6376](https://issues.redhat.com/browse/RHOAIENG-6376)- Pipeline run creation fails after setting`pip_index_urls` in a pipeline component to a URL that contains a port number and path

Previously, when you created a pipeline and set the`pip_index_urls` value for a component to a URL that contains a port number and path, compiling the pipeline code and then creating a pipeline run could result in an error. This issue is now resolved.

[RHOAIENG-4240](https://issues.redhat.com/browse/RHOAIENG-4240)- Jobs fail to submit to Ray cluster in unsecured environment

Previously, when running distributed data science workloads from Jupyter notebooks in an unsecured OpenShift cluster, a`ConnectionError: Failed to connect to Ray` error message might be shown. This issue is now resolved.

[RHOAIENG-9670](https://issues.redhat.com/browse/RHOAIENG-9670)- vLLM container intermittently crashes while processing requests

Previously, if you deployed a model by using the vLLM ServingRuntime for KServe runtime on the single-model serving platform and also configured`tensor-parallel-size`, depending on the hardware platform you used, the`kserve-container` container would intermittently crash while processing requests. This issue is now resolved.

[RHOAIENG-8043](https://issues.redhat.com/browse/RHOAIENG-8043)- vLLM errors during generation with mixtral-8x7b

Previously, some models, such as Mixtral-8x7b might have experienced sporadic errors due to a triton issue, such as`FileNotFoundError:No such file or directory`. This issue is now resolved.

[RHOAIENG-2974](https://issues.redhat.com/browse/RHOAIENG-2974)- Data science cluster cannot be deleted without its associated initialization object

Previously, you could not delete a`DataScienceCluster`(DSC) object if its associated`DSCInitialization` object (DSCI) did not exist. This issue has now been resolved.

[RHOAIENG-1205](https://issues.redhat.com/browse/RHOAIENG-1205)(previously documented as RHODS-11791) - Usage data collection is enabled after upgrade

Previously, the`Allow collection of usage data` option would activate whenever you upgraded OpenShift AI. Now, you no longer need to manually deselect the`Allow collection of usage data` option when you upgrade.

[RHOAIENG-1204](https://issues.redhat.com/browse/RHOAIENG-1204)(previously documented as [ODH-DASHBOARD-1771](https://github.com/opendatahub-io/odh-dashboard/issues/1771)) - JavaScript error during Pipeline step initializing

Previously, the pipeline Run details page stopped working when a run started. This issue has now been resolved.

[RHOAIENG-582](https://issues.redhat.com/browse/RHOAIENG-582)(previously documented as [ODH-DASHBOARD-1335](https://github.com/opendatahub-io/odh-dashboard/issues/1335)) - Rename Edit permission to Contributor

On the Permissions tab for a project, the term Edit has been replaced with Contributor to more accurately describe the actions granted by this permission.

For a complete list of updates, see the [Errata advisory](https://access.redhat.com/errata-search/?q=&amp;p=1&amp;sort=portal_publication_date+desc&amp;rows=10&amp;portal_product=Red%5C+Hat%5C+OpenShift%5C+AI).

[RHOAIENG-8819](https://issues.redhat.com/browse/RHOAIENG-8819)-`ibm-granite/granite-3b-code-instruct` model fails to deploy on single-model serving platform

Previously, if you tried to deploy the`ibm-granite/granite-3b-code-instruct` model on the single-model serving platform by using the`vLLM ServingRuntime for KServe` runtime, the model deployment would fail with an error. This issue is now resolved.

[RHOAIENG-7209](https://issues.redhat.com/browse/RHOAIENG-7209)- Error displays when setting the default pipeline root

Previously, if you tried to set the default pipeline root using the data science pipelines SDK or the OpenShift AI user interface, an error would appear. This issue is now resolved.

[RHOAIENG-6711](https://issues.redhat.com/browse/RHOAIENG-6711)- ODH-model-controller overwrites the`spec.memberSelectors` field in`ServiceMeshMemberRoll` objects

Previously, if you tried to add a project or namespace to a`ServiceMeshMemberRoll` resource using the`spec.memberSelectors` field of the`ServiceMeshMemberRoll` resource, the ODH-model-controller would overwrite the field. This issue is now resolved.

[RHOAIENG-6649](https://issues.redhat.com/browse/RHOAIENG-6649)- An error is displayed when viewing a model on a model server that has no external route defined

Previously, if you tried to use the dashboard to deploy a model on a model server that did not have external routes enabled, a`t.components is undefined` error message would appear while the model creation was in progress. This issue is now resolved.

[RHOAIENG-3981](https://issues.redhat.com/browse/RHOAIENG-3981)- In unsecured environment, the functionality to wait for Ray cluster to be ready gets stuck

Previously, when running distributed data science workloads from Jupyter notebooks in an unsecured OpenShift cluster, the functionality to wait for the Ray cluster to be ready before proceeding (`cluster.wait_ready()`) got stuck even when the Ray cluster was ready. This issue is now resolved.

[RHOAIENG-2312](https://issues.redhat.com/browse/RHOAIENG-2312)- Importing numpy fails in`code-server` workbench

Previously, if you tried to import numpy, your code-server workbench would fail. This issue is now resolved.

[RHOAIENG-1197](https://issues.redhat.com/browse/RHOAIENG-1197)- Cannot create pipeline due to the End date picker in the pipeline run creation page defaulting to NaN values when using Firefox on Linux

Previously, if you tried to create a pipeline with a scheduled recurring run using Firefox on Linux, enabling the End Date parameter would result in Not a number (Nan) values for both the date and time. This issue is now resolved.

[RHOAIENG-1196](https://issues.redhat.com/browse/RHOAIENG-1196)(previously documented as [ODH-DASHBOARD-2140](https://github.com/opendatahub-io/odh-dashboard/issues/2140)) - Package versions displayed in dashboard do not match installed versions

Previously, the dashboard would display inaccurate version numbers for packages such as JupterLab and Notebook. This issue is now resolved.

[RHOAIENG-880](https://issues.redhat.com/browse/RHOAIENG-880)- Default pipelines service account is unable to create Ray clusters

Previously, you could not create Ray clusters using the default pipelines Service Account. This issue is now resolved.

[RHOAIENG-52](https://issues.redhat.com/browse/RHOAIENG-52)- Token authentication fails in clusters with self-signed certificates

Previously, if you used self-signed certificates, and you used the Python`codeflare-sdk` in a notebook or in a Python script as part of a pipeline, token authentication would fail. This issue is now resolved.

[RHOAIENG-7312](https://issues.redhat.com/browse/RHOAIENG-7312)- Model serving fails during query with token authentication in KServe

Previously, if you enabled both the ModelMesh and KServe components in your`DataScienceCluster` object and added Authorino as an authorization provider, a race condition could occur that resulted in the`odh-model-controller` pods being rolled out in a state that is appropriate for ModelMesh, but not for KServe and Authorino. In this situation, if you made an inference request to a running model that was deployed using KServe, you saw a`404 - Not Found` error. In addition, the logs for the`odh-model-controller` deployment object showed a`Reconciler` error message. This issue is now resolved.

[RHOAIENG-7181](https://issues.redhat.com/browse/RHOAIENG-7181)(previously documented as [RHOAIENG-6343](https://issues.redhat.com/browse/RHOAIENG-6343))- Some components are set to`Removed` after installing OpenShift AI

Previously, after you installed OpenShift AI, the`managementState` field for the`codeflare`,`kueue`, and`ray` components was incorrectly set to`Removed` instead of`Managed` in the`DataScienceCluster` custom resource. This issue is now resolved.

[RHOAIENG-7079](https://issues.redhat.com/browse/RHOAIENG-7079)(previously documented as [RHOAIENG-6317](https://issues.redhat.com/browse/RHOAIENG-6317)) - Pipeline task status and logs sometimes not shown in OpenShift AI dashboard

Previously, when running pipelines by using Elyra, the OpenShift AI dashboard might not show the pipeline task status and logs, even when the related pods had not been pruned and the information was still available in the OpenShift Console. This issue is now resolved.

[RHOAIENG-7070](https://issues.redhat.com/browse/RHOAIENG-7070)(previously documented as [RHOAIENG-6709](https://issues.redhat.com/browse/RHOAIENG-6709)) - Jupyter notebook creation might fail when different environment variables specified

Previously, if you started and then stopped a Jupyter notebook, and edited its environment variables in an OpenShift AI workbench, the notebook failed to restart. This issue is now resolved.

[RHOAIENG-6853](https://issues.redhat.com/browse/RHOAIENG-6853)- Cannot set pod toleration in Elyra pipeline pods

Previously, if you set a pod toleration for an Elyra pipeline pod, the toleration did not take effect. This issue is now resolved.

[RHOAIENG-5314](https://issues.redhat.com/browse/RHOAIENG-5314)- Data science pipeline server fails to deploy in fresh cluster due to network policies

Previously, if you created a data science pipeline server on a fresh cluster, the user interface remained in a loading state and the pipeline server did not start. This issue is now resolved.

[RHOAIENG-4252](https://issues.redhat.com/browse/RHOAIENG-4252)- Data science pipeline server deletion process fails to remove`ScheduledWorkFlow` resource

Previously, the pipeline server deletion process did not remove the`ScheduledWorkFlow` resource. As a result, new`DataSciencePipelinesApplications`(DSPAs) did not recognize the redundant`ScheduledWorkFlow` resource. This issue is now resolved

[RHOAIENG-3411](https://issues.redhat.com/browse/RHOAIENG-3411)(previously documented as [RHOAIENG-3378](https://issues.redhat.com/browse/RHOAIENG-3378)) - Internal Image Registry is an undeclared hard dependency for Jupyter notebooks spawn process

Previously, before you could start OpenShift AI notebooks and workbenches, you must have already enabled the internal, integrated container image registry in OpenShift. Attempts to start notebooks or workbenches without first enabling the image registry failed with an "InvalidImageName" error. You can now create and use workbenches in OpenShift AI without enabling the internal OpenShift image registry. If you update a cluster to enable or disable the internal image registry, you must recreate existing workbenches for the registry changes to take effect.

[RHOAIENG-2541](https://issues.redhat.com/browse/RHOAIENG-2541)- KServe controller pod experiences OOM because of too many secrets in the cluster

Previously, if your OpenShift cluster had a large number of secrets, the KServe controller pod could continually crash due to an out-of-memory (OOM) error. This issue is now resolved.

[RHOAIENG-1452](https://issues.redhat.com/browse/RHOAIENG-1452)- The Red Hat OpenShift AI Add-on gets stuck

Previously, the Red Hat OpenShift AI Add-on uninstall did not delete OpenShift AI components when the install was triggered via OCM APIs. This issue is now resolved.

[RHOAIENG-307](https://issues.redhat.com/browse/RHOAIENG-307)- Removing the DataScienceCluster deletes all OpenShift Serverless CRs

Previously, if you deleted the DataScienceCluster custom resource (CR), all OpenShift Serverless CRs (including knative-serving, deployments, gateways, and pods) were also deleted. This issue is now resolved.

[RHOAIENG-6709](https://issues.redhat.com/browse/RHOAIENG-6709)- Jupyter notebook creation might fail when different environment variables specified

Previously, if you started and then stopped a Jupyter notebook, and edited its environment variables in an OpenShift AI workbench, the notebook failed to restart. This issue is now resolved.

[RHOAIENG-6701](https://issues.redhat.com/browse/RHOAIENG-6701)- Users without cluster administrator privileges cannot access the job submission endpoint of the Ray dashboard

Previously, users of the distributed workloads feature who did not have cluster administrator privileges for OpenShift might not have been able to access or use the job submission endpoint of the Ray dashboard. This issue is now resolved.

[RHOAIENG-6578](https://issues.redhat.com/browse/RHOAIENG-6578)- Request without token to a protected inference point not working by default

Previously, if you added Authorino as an authorization provider for the single-model serving platform and enabled token authorization for models that you deployed, it was still possible to query the models without specifying the tokens. This issue is now resolved.

[RHOAIENG-6343](https://issues.redhat.com/browse/RHOAIENG-6343)- Some components are set to`Removed` after installing OpenShift AI

Previously, after you installed OpenShift AI, the`managementState` field for the`codeflare`,`kueue`, and`ray` components was incorrectly set to`Removed` instead of`Managed` in the`DataScienceCluster` custom resource. This issue is now resolved.

[RHOAIENG-5067](https://issues.redhat.com/browse/RHOAIENG-5067)- Model server metrics page does not load for a model server based on the ModelMesh component

Previously, data science project names that contained capital letters or spaces could cause issues on the model server metrics page for model servers based on the ModelMesh component. The metrics page might not have received data correctly, resulting in a`400 Bad Request` error and preventing the page from loading. This issue is now resolved.

[RHOAIENG-4966](https://issues.redhat.com/browse/RHOAIENG-4966)- Self-signed certificates in a custom CA bundle might be missing from the`odh-trusted-ca-bundle` configuration map

Previously, if you added a custom certificate authority (CA) bundle to use self-signed certificates, sometimes the custom certificates were missing from the`odh-trusted-ca-bundle` ConfigMap, or the non-reserved namespaces did not contain the`odh-trusted-ca-bundle` ConfigMap when the ConfigMap was set to`managed`. This issue is now resolved.

[RHOAIENG-4938](https://issues.redhat.com/browse/RHOAIENG-4938)(previously documented as [RHOAIENG-4327](https://issues.redhat.com/browse/RHOAIENG-4327)) - Workbenches do not use the self-signed certificates from centrally configured bundle automatically

There are two bundle options to include self-signed certificates in OpenShift AI,`ca-bundle.crt` and`odh-ca-bundle.crt`. Previously, workbenches did not automatically use the self-signed certificates from the centrally configured bundle and you had to define environment variables that pointed to your certificate path. This issue is now resolved.

[RHOAIENG-4572](https://issues.redhat.com/browse/RHOAIENG-4572)- Unable to run data science pipelines after install and upgrade in certain circumstances

Previously, you were unable to run data science pipelines after installing or upgrading OpenShift AI in the following circumstances:

- You installed OpenShift AI and you had a valid CA certificate. Within the default-dsci object, you changed the`managementState` field for the`trustedCABundle` field to`Removed` post-installation.
- You upgraded OpenShift AI from version 2.6 to version 2.8 and you had a valid CA certificate.
- You upgraded OpenShift AI from version 2.7 to version 2.8 and you had a valid CA certificate.

This issue is now resolved.

[RHOAIENG-4524](https://issues.redhat.com/browse/RHOAIENG-4524)- BuildConfig definitions for RStudio images contain occurrences of incorrect branch

Previously, the`BuildConfig` definitions for the RStudio and CUDA - RStudio workbench images pointed to the wrong branch in OpenShift AI. This issue is now resolved.

[RHOAIENG-3963](https://issues.redhat.com/browse/RHOAIENG-3963)- Unnecessary managed resource warning

Previously, when you edited and saved the`OdhDashboardConfig` custom resource for the`redhat-ods-applications` project, the system incorrectly displayed a`Managed resource` warning message. This issue is now resolved.

[RHOAIENG-2542](https://issues.redhat.com/browse/RHOAIENG-2542)- Inference service pod does not always get an Istio sidecar

Previously, when you deployed a model using the single-model serving platform (which uses KServe), the`istio-proxy` container could be missing in the resulting pod, even if the inference service had the`sidecar.istio.io/inject=true` annotation. This issue is now resolved.

[RHOAIENG-1666](https://issues.redhat.com/browse/RHOAIENG-1666)- The Import Pipeline button is prematurely accessible

Previously, when you imported a pipeline to a workbench that belonged to a data science project, the Import Pipeline button was accessible before the pipeline server was fully available. This issue is now resolved.

[RHOAIENG-673](https://issues.redhat.com/browse/RHOAIENG-673)(previously documented as RHODS-12946) - Cannot install from PyPI mirror in disconnected environment or when using private certificates

In disconnected environments, Red Hat OpenShift AI cannot connect to the public-facing PyPI repositories, so you must specify a repository inside your network. Previously, if you were using private TLS certificates and a data science pipeline was configured to install Python packages, the pipeline run would fail. This issue is now resolved.

[RHOAIENG-3355](https://issues.redhat.com/browse/RHOAIENG-3355)- OVMS on KServe does not use accelerators correctly

Previously, when you deployed a model using the single-model serving platform and selected the OpenVINO Model Server serving runtime, if you requested an accelerator to be attached to your model server, the accelerator hardware was detected but was not used by the model when responding to queries. This issue is now resolved.

[RHOAIENG-2869](https://issues.redhat.com/browse/RHOAIENG-2869)- Cannot edit existing model framework and model path in a multi-model project

Previously, when you tried to edit a model in a multi-model project using the Deploy model dialog, the Model framework and Path values did not update. This issue is now resolved.

[RHOAIENG-2724](https://issues.redhat.com/browse/RHOAIENG-2724)- Model deployment fails because fields automatically reset in dialog

Previously, when you deployed a model or edited a deployed model, the Model servers and Model framework fields in the "Deploy model" dialog might have reset to the default state. The Deploy button might have remained enabled even though these mandatory fields no longer contained valid values. This issue is now resolved.

[RHOAIENG-2099](https://issues.redhat.com/browse/RHOAIENG-2099)- Data science pipeline server fails to deploy in fresh cluster

Previously, when you created a data science pipeline server on a fresh cluster, the user interface remained in a loading state and the pipeline server did not start. This issue is now resolved.

[RHOAIENG-1199](https://issues.redhat.com/browse/RHOAIENG-1199)(previously documented as [ODH-DASHBOARD-1928](https://github.com/opendatahub-io/odh-dashboard/issues/1928)) - Custom serving runtime creation error message is unhelpful

Previously, when you tried to create or edit a custom model-serving runtime and an error occurred, the error message did not indicate the cause of the error. The error messages have been improved.

[RHOAIENG-556](https://issues.redhat.com/browse/RHOAIENG-556)- ServingRuntime for KServe model is created regardless of error

Previously, when you tried to deploy a KServe model and an error occurred, the`InferenceService` custom resource (CR) was still created and the model was shown in the Data science projects page, but the status would always remain unknown. The KServe deploy process has been updated so that the ServingRuntime is not created if an error occurs.

[RHOAIENG-548](https://issues.redhat.com/browse/RHOAIENG-548)(previously documented as [ODH-DASHBOARD-1776](https://github.com/opendatahub-io/odh-dashboard/issues/1776)) - Error messages when user does not have project administrator permission

Previously, if you did not have administrator permission for a project, you could not access some features, and the error messages did not explain why. For example, when you created a model server in an environment where you only had access to a single namespace, an`Error creating model server` error message appeared. However, the model server is still successfully created. This issue is now resolved.

[RHOAIENG-66](https://issues.redhat.com/browse/RHOAIENG-66)- Ray dashboard route deployed by CodeFlare SDK exposes self-signed certs instead of cluster cert

Previously, when you deployed a Ray cluster by using the CodeFlare SDK with the`openshift_oauth=True` option, the resulting route for the Ray cluster was secured by using the`passthrough` method and as a result, the self-signed certificate used by the OAuth proxy was exposed. This issue is now resolved.

[RHOAIENG-12](https://issues.redhat.com/browse/RHOAIENG-12)- Cannot access Ray dashboard from some browsers

In some browsers, users of the distributed workloads feature might not have been able to access the Ray dashboard because the browser automatically changed the prefix of the dashboard URL from`http` to`https`. This issue is now resolved.

[RHODS-6216](https://issues.redhat.com/browse/RHODS-6216)- The ModelMesh oauth-proxy container is intermittently unstable

Previously, ModelMesh pods did not deploy correctly due to a failure of the ModelMesh`oauth-proxy` container. This issue occurred intermittently and only if authentication was enabled in the ModelMesh runtime environment. This issue is now resolved.

[RHOAIENG-535](https://issues.redhat.com/browse/RHOAIENG-535)- Metrics graph showing HTTP requests for deployed models is incorrect if there are no HTTP requests

Previously, if a deployed model did not receive at least one HTTP request for each of the two data types (success and failed), the graphs that show HTTP request performance metrics (for all models on the model server or for the specific model) rendered incorrectly, with a straight line that indicated a steadily increasing number of failed requests. This issue is now resolved.

[RHOAIENG-1467](https://issues.redhat.com/browse/RHOAIENG-1467)- Serverless net-istio controller pod might hit OOM

Previously, the Knative`net-istio-controller` pod (which is a dependency for KServe) might continuously crash due to an out-of-memory (OOM) error. This issue is now resolved.

[RHOAIENG-1899](https://issues.redhat.com/browse/RHOAIENG-1899)(previously documented as [RHODS-6539](https://issues.redhat.com/browse/RHODS-6539)) - The Anaconda Professional Edition cannot be validated and enabled

Previously, you could not enable the Anaconda Professional Edition because the dashboard’s key validation for it was inoperable. This issue is now resolved.

[RHOAIENG-2269](https://issues.redhat.com/browse/RHOAIENG-2269)- (Single-model) Dashboard fails to display the correct number of model replicas

Previously, on a single-model serving platform, the Models and model servers section of a data science project did not show the correct number of model replicas. This issue is now resolved.

[RHOAIENG-2270](https://issues.redhat.com/browse/RHOAIENG-2270)- (Single-model) Users cannot update model deployment settings

Previously, you couldn’t edit the deployment settings (for example, the number of replicas) of a model you deployed with a single-model serving platform. This issue is now resolved.

[RHODS-8865](https://issues.redhat.com/browse/RHODS-8865)- A pipeline server fails to start unless you specify an Amazon Web Services (AWS) Simple Storage Service (S3) bucket resource

Previously, when you created a data connection for a data science project, the`AWS_S3_BUCKET` field was not designated as a mandatory field. However, if you attempted to configure a pipeline server with a data connection where the`AWS_S3_BUCKET` field was not populated, the pipeline server failed to start successfully. This issue is now resolved. The Configure pipeline server dialog has been updated to include the`Bucket` field as a mandatory field.

[RHODS-12899](https://issues.redhat.com/browse/RHODS-12899)- OpenVINO runtime missing annotation for NVIDIA GPUs

Previously, if a user selected the OpenVINO model server (supports GPUs) runtime and selected an NVIDIA GPU accelerator in the model server user interface, the system could display a unnecessary warning that the selected accelerator was not compatible with the selected runtime. The warning is no longer displayed.

[RHOAIENG-84](https://issues.redhat.com/browse/RHOAIENG-84)- Cannot use self-signed certificates with KServe

Previously, the single-model serving platform did not support self-signed certificates. This issue is now resolved. To use self-signed certificates with KServe, follow the steps described in [Working with certificates](https://docs.redhat.com/en/documentation/red_hat_openshift_ai_cloud_service/1/html/installing_and_uninstalling_openshift_ai_cloud_service/working-with-certificates_certs).

[RHOAIENG-164](https://issues.redhat.com/browse/RHOAIENG-164)- Number of model server replicas for Kserve is not applied correctly from the dashboard

Previously, when you set a number of model server replicas different from the default (1), the model (server) was still deployed with 1 replica. This issue is now resolved.

[RHOAIENG-288](https://issues.redhat.com/browse/RHOAIENG-288)- Recommended image version label for workbench is shown for two versions

Most of the workbench images that are available in OpenShift AI are provided in multiple versions. The only recommended version is the latest version. In Red Hat OpenShift AI 2.4 and 2.5, the Recommended tag was erroneously shown for multiple versions of an image. This issue is now resolved.

[RHOAIENG-293](https://issues.redhat.com/browse/RHOAIENG-293)- Deprecated ModelMesh monitoring stack not deleted after upgrading from 2.4 to 2.5

In Red Hat OpenShift AI 2.5, the former ModelMesh monitoring stack was no longer deployed because it was replaced by user workload monitoring. However, the former monitoring stack was not deleted during an upgrade to OpenShift AI 2.5. Some components remained and used cluster resources. This issue is now resolved.

[RHOAIENG-343](https://issues.redhat.com/browse/RHOAIENG-343)- Manual configuration of OpenShift Service Mesh and OpenShift Serverless does not work for KServe

If you installed OpenShift Serverless and OpenShift Service Mesh and then installed Red Hat OpenShift AI with KServe enabled, KServe was not deployed. This issue is now resolved.

[RHOAIENG-517](https://issues.redhat.com/browse/RHOAIENG-517)- User with edit permissions cannot see created models

A user with edit permissions could not see any created models, unless they were the project owner or had admin permissions for the project. This issue is now resolved.

[RHOAIENG-804](https://issues.redhat.com/browse/RHOAIENG-804)- Cannot deploy Large Language Models with KServe on FIPS-enabled clusters

Previously, Red Hat OpenShift AI was not yet fully designed for FIPS. You could not deploy Large Language Models (LLMs) with KServe on FIPS-enabled clusters. This issue is now resolved.

[RHOAIENG-908](https://issues.redhat.com/browse/RHOAIENG-908)- Cannot use ModelMesh if KServe was previously enabled and then removed

Previously, when both ModelMesh and KServe were enabled in the`DataScienceCluster` object, and you subsequently removed KServe, you could no longer deploy new models with ModelMesh. You could continue to use models that were previously deployed with ModelMesh. This issue is now resolved.

[RHOAIENG-2184](https://issues.redhat.com/browse/RHOAIENG-2184)- Cannot create Ray clusters or distributed workloads

Previously, users could not create Ray clusters or distributed workloads in namespaces where they have`admin` or`edit` permissions. This issue is now resolved.

[ODH-DASHBOARD-1991](https://github.com/opendatahub-io/odh-dashboard/issues/1991)- ovms-gpu-ootb is missing recommended accelerator annotation

Previously, when you added a model server to your project, the Serving runtime list did not show the Recommended serving runtime label for the NVIDIA GPU. This issue is now resolved.

[RHOAIENG-807](https://issues.redhat.com/browse/RHOAIENG-807)- Accelerator profile toleration removed when restarting a workbench

Previously, if you created a workbench that used an accelerator profile that in turn included a toleration, restarting the workbench removed the toleration information, which meant that the restart could not complete. A freshly created GPU-enabled workbench might start the first time, but never successfully restarted afterwards because the generated pod remained forever pending. This issue is now resolved.

[DATA-SCIENCE-PIPELINES-OPERATOR-294](https://github.com/opendatahub-io/data-science-pipelines-operator/issues/294)- Scheduled pipeline run that uses data-passing might fail to pass data between steps, or fail the step entirely

A scheduled pipeline run that uses an S3 object store to store the pipeline artifacts might fail with an error such as the following:

```plaintext
Bad value for --endpoint-url "cp": scheme is missing. Must be of the form http://<hostname>/ or https://<hostname>/
```

This issue occurred because the S3 object store endpoint was not successfully passed to the pods for the scheduled pipeline run. This issue is now resolved.

[RHODS-4769](https://issues.redhat.com/browse/RHODS-4769)- GPUs on nodes with unsupported taints cannot be allocated to notebook servers

GPUs on nodes marked with any taint other than the supported nvidia.com/gpu taint could not be selected when creating a notebook server. This issue is now resolved.

[RHODS-6346](https://issues.redhat.com/browse/RHODS-6346)- Unclear error message displays when using invalid characters to create a data science project

When creating a data science project’s data connection, workbench, or storage connection using invalid special characters, the following error message was displayed:

```plaintext
the object provided is unrecognized (must be of type Secret): couldn't get version/kind; json parse error: unexpected end of JSON input ({"apiVersion":"v1","kind":"Sec ...)
```

The error message failed to clearly indicate the problem. The error message now indicates that invalid characters were entered.

[RHODS-6950](https://issues.redhat.com/browse/RHODS-6950)- Unable to scale down workbench GPUs when all GPUs in the cluster are being used

In earlier releases, it was not possible to scale down workbench GPUs if all GPUs in the cluster were being used. This issue applied to GPUs being used by one workbench, and GPUs being used by multiple workbenches. You can now scale down the GPUs by selecting None from the Accelerators list.

[RHODS-8939](https://issues.redhat.com/browse/RHODS-8939)- Default shared memory for a Jupyter notebook created in a previous release causes a runtime error

Starting with release 1.31, this issue is resolved, and the shared memory for any new notebook is set to the size of the node.

For a Jupyter notebook created in a release earlier than 1.31, the default shared memory for a Jupyter notebook is set to 64 MB and you cannot change this default value in the notebook configuration.

To fix this issue, you must recreate the notebook or follow the process described in the Knowledgebase article [How to change the shared memory for a Jupyter notebook in Red Hat OpenShift AI](https://access.redhat.com/articles/7040815).

[RHODS-9030](https://issues.redhat.com/browse/RHODS-9030)- Uninstall process for OpenShift AI might become stuck when removing`kfdefs` resources

The steps for uninstalling the OpenShift AI managed service are described in [Uninstalling OpenShift AI](https://docs.redhat.com/en/documentation/red_hat_openshift_ai_cloud_service/1/html/installing_and_uninstalling_openshift_ai_cloud_service/assembly_uninstalling-openshift-ai_uninstall).

However, even when you followed this guide, you might have seen that the uninstall process did not finish successfully. Instead, the process stayed on the step of deleting`kfdefs` resources that were used by the Kubeflow Operator. As shown in the following example,`kfdefs` resources might exist in the`redhat-ods-applications`,`redhat-ods-monitoring`, and`rhods-notebooks` namespaces:

```plaintext
$ oc get kfdefs.kfdef.apps.kubeflow.org -A

NAMESPACE                  NAME                                   AGE
redhat-ods-applications    rhods-anaconda                         3h6m
redhat-ods-applications    rhods-dashboard                        3h6m
redhat-ods-applications    rhods-data-science-pipelines-operator  3h6m
redhat-ods-applications    rhods-model-mesh                       3h6m
redhat-ods-applications    rhods-nbc                              3h6m
redhat-ods-applications    rhods-osd-config                       3h6m
redhat-ods-monitoring      modelmesh-monitoring                   3h6m
redhat-ods-monitoring      monitoring                             3h6m
rhods-notebooks            rhods-notebooks                        3h6m
rhods-notebooks            rhods-osd-config                       3h5m
```

Failed removal of the`kfdefs` resources might have also prevented later installation of a newer version of OpenShift AI. This issue no longer occurs.

[RHODS-9764](https://issues.redhat.com/browse/RHODS-9764)- Data connection details get reset when editing a workbench

When you edited a workbench that had an existing data connection and then selected the Create new data connection option, the edit page might revert to the Use existing data connection option before you had finished specifying the new connection details. This issue is now resolved.

[RHODS-9583](https://issues.redhat.com/browse/RHODS-9583)- Data Science dashboard did not detect an existing OpenShift Pipelines installation

When the OpenShift Pipelines Operator was installed as a global operator on your cluster, the OpenShift AI dashboard did not detect it. The OpenShift Pipelines Operator is now detected successfully.

[ODH-DASHBOARD-1639](https://github.com/opendatahub-io/odh-dashboard/issues/1639)- Wrong TLS value in dashboard route

Previously, when a route was created for the OpenShift AI dashboard on OpenShift, the`tls.termination` field had an invalid default value of`Reencrypt`. This issue is now resolved. The new value is`reencrypt`.

[ODH-DASHBOARD-1638](https://github.com/opendatahub-io/odh-dashboard/issues/1638)- Name placeholder in Triggered Runs tab shows Scheduled run name

Previously, when you clicked Pipelines > Runs and then selected the Triggered tab to configure a triggered run, the example value shown in the Name field was`Scheduled run name`. This issue is now resolved.

[ODH-DASHBOARD-1547](https://github.com/opendatahub-io/odh-dashboard/issues/1547)- "We can’t find that page" message displayed in dashboard when pipeline operator installed in background

Previously, when you used the Data Science Pipelines page of the dashboard to install the OpenShift Pipelines Operator, when the Operator installation was complete, the page refreshed to show a`We can't find that page` message. This issue is now resolved. When the Operator installation is complete, the dashboard redirects you to the Pipelines page, where you can create a pipeline server.

[ODH-DASHBOARD-1545](https://github.com/opendatahub-io/odh-dashboard/issues/1545)- Dashboard keeps scrolling to bottom of project when Models tab is expanded

Previously, on the Data science projects page of the dashboard, if you clicked the Deployed models tab to expand it and then tried to perform other actions on the page, the page automatically scrolled back to the Deployed models section. This affected your ability to perform other actions. This issue is now resolved.

[NOTEBOOKS-156](https://github.com/opendatahub-io/notebooks/issues/156)- Elyra included an example runtime called Test

Previously, Elyra included an example runtime configuration called`Test`. If you selected this configuration when running a data science pipeline, you could see errors. The`Test` configuration has now been removed.

[RHODS-9622](https://issues.redhat.com/browse/RHODS-9622)- Duplicating a scheduled pipeline run does not copy the existing period and pipeline input parameter values

Previously, when you duplicated a scheduled pipeline run that had a periodic trigger, the duplication process did not copy the configured execution frequency for the recurring run or the specified pipeline input parameters. This issue is now resolved.

[RHODS-8932](https://issues.redhat.com/browse/RHODS-8932)- Incorrect cron format was displayed by default when scheduling a recurring pipeline run

When you scheduled a recurring pipeline run by configuring a cron job, the OpenShift AI interface displayed an incorrect format by default. It now displays the correct format.

[RHODS-9374](https://issues.redhat.com/browse/RHODS-9374)- Pipelines with non-unique names did not appear in the data science project user interface

If you launched a notebook from a Jupyter application that supported Elyra, or if you used a workbench, when you submitted a pipeline to be run, pipelines with non-unique names did not appear in the Pipelines section of the relevant data science project page or the Pipelines heading of the data science pipelines page. This issue has now been resolved.

[RHODS-9329](https://issues.redhat.com/browse/RHODS-9329)- Deploying a custom model-serving runtime could result in an error message

Previously, if you used the OpenShift AI dashboard to deploy a custom model-serving runtime, the deployment process could fail with an`Error retrieving Serving Runtime` message. This issue is now resolved.

[RHODS-9064](https://issues.redhat.com/browse/RHODS-9064)- After upgrade, the Data Science Pipelines tab was not enabled on the OpenShift AI dashboard

When you upgraded from OpenShift AI 1.26 to OpenShift AI 1.28, the Data Science Pipelines tab was not enabled in the OpenShift AI dashboard. This issue is resolved in OpenShift AI 1.29.

[RHODS-9443](https://issues.redhat.com/browse/RHODS-9443)- Exporting an Elyra pipeline exposed S3 storage credentials in plain text

In OpenShift AI 1.28.0, when you exported an Elyra pipeline from JupyterLab in Python DSL format or YAML format, the generated output contained S3 storage credentials in plain text. This issue has been resolved in OpenShift AI 1.28.1. However, after you upgrade to OpenShift AI 1.28.1, if your deployment contains a data science project with a pipeline server and a data connection, you must perform the following additional actions for the fix to take effect:

1. Refresh your browser page.
2. Stop any running workbenches in your deployment and restart them.

Furthermore, to confirm that your Elyra runtime configuration contains the fix, perform the following actions:

Hover the cursor over the runtime configuration that you want to view and click the Edit button ().

The Data Science Pipelines runtime configuration page opens.

1. In the left sidebar of JupyterLab, click Runtimes ().
2. Confirm that`KUBERNETES_SECRET` is defined as the value in the Cloud Object Storage Authentication Type field.
3. Close the runtime configuration without changing it.

[RHODS-8460](https://issues.redhat.com/browse/RHODS-8460)- When editing the details of a shared project, the user interface remained in a loading state without reporting an error

When a user with permission to edit a project attempted to edit its details, the user interface remained in a loading state and did not display an appropriate error message. Users with permission to edit projects cannot edit any fields in the project, such as its description. Those users can edit only components belonging to a project, such as its workbenches, data connections, and storage.

The user interface now displays an appropriate error message and does not try to update the project description.

[RHODS-8482](https://issues.redhat.com/browse/RHODS-8482)- Data science pipeline graphs did not display node edges for running pipelines

If you ran pipelines that did not contain Tekton-formatted`Parameters` or`when` expressions in their YAML code, the OpenShift AI user interface did not display connecting edges to and from graph nodes. For example, if you used a pipeline containing the`runAfter` property or`Workspaces`, the user interface displayed the graph for the executed pipeline without edge connections. The OpenShift AI user interface now displays connecting edges to and from graph nodes.

[RHODS-8923](https://issues.redhat.com/browse/RHODS-8923)- Newly created data connections were not detected when you attempted to create a pipeline server

If you created a data connection from within a Data Science project, and then attempted to create a pipeline server, the Configure a pipeline server dialog did not detect the data connection that you created. This issue is now resolved.

[RHODS-8461](https://issues.redhat.com/browse/RHODS-8461)- When sharing a project with another user, the OpenShift AI user interface text was misleading

When you attempted to share a Data Science project with another user, the user interface text misleadingly implied that users could edit all of its details, such as its description. However, users can edit only components belonging to a project, such as its workbenches, data connections, and storage. This issue is now resolved and the user interface text no longer misleadingly implies that users can edit all of its details.

[RHODS-8462](https://issues.redhat.com/browse/RHODS-8462)- Users with "Edit" permission could not create a Model Server

Users with "Edit" permissions can now create a Model Server without token authorization. Users must have "Admin" permissions to create a Model Server with token authorization.

[RHODS-8796](https://issues.redhat.com/browse/RHODS-8796)- OpenVINO Model Server runtime did not have the required flag to force GPU usage

OpenShift AI includes the OpenVINO Model Server (OVMS) model-serving runtime by default. When you configured a new model server and chose this runtime, the Configure model server dialog enabled you to specify a number of GPUs to use with the model server. However, when you finished configuring the model server and deployed models from it, the model server did not actually use any GPUs. This issue is now resolved and the model server uses the GPUs.

[RHODS-8861](https://issues.redhat.com/browse/RHODS-8861)- Changing the host project when creating a pipeline ran resulted in an inaccurate list of available pipelines

If you changed the host project while creating a pipeline run, the interface failed to make the pipelines of the new host project available. Instead, the interface showed pipelines that belong to the project you initially selected on the Data Science Pipelines > Runs page. This issue is now resolved. You no longer select a pipeline from the Create run page. The pipeline selection is automatically updated when you click the Create run button, based on the current project and its pipeline.

[RHODS-8249](https://issues.redhat.com/browse/RHODS-8249)- Environment variables uploaded as ConfigMap were stored in Secret instead

Previously, in the OpenShift AI interface, when you added environment variables to a workbench by uploading a`ConfigMap` configuration, the variables were stored in a`Secret` object instead. This issue is now resolved.

[RHODS-7975](https://issues.redhat.com/browse/RHODS-7975)- Workbenches could have multiple data connections

Previously, if you changed the data connection for a workbench, the existing data connection was not released. As a result, a workbench could stay connected to multiple data sources. This issue is now resolved.

[RHODS-7948](https://issues.redhat.com/browse/RHODS-7948)- Uploading a secret file containing environment variables resulted in double-encoded values

Previously, when creating a workbench in a data science project, if you uploaded a YAML-based secret file containing environment variables, the environment variable values were not decoded. Then, in the resulting OpenShift secret created by this process, the encoded values were encoded again. This issue is now resolved.

[RHODS-6429](https://issues.redhat.com/browse/RHODS-6429)- An error was displayed when creating a workbench with the Intel OpenVINO or Anaconda Professional Edition images

Previously, when you created a workbench with the Intel OpenVINO or Anaconda Professional Edition images, an error appeared during the creation process. However, the workbench was still successfully created. This issue is now resolved.

[RHODS-6372](https://issues.redhat.com/browse/RHODS-6372)- Idle notebook culler did not take active terminals into account

Previously, if a notebook image had a running terminal, but no active, running kernels, the idle notebook culler detected the notebook as inactive and stopped the terminal. This issue is now resolved.

[RHODS-5700](https://issues.redhat.com/browse/RHODS-5700)- Data connections could not be created or connected to when creating a workbench

When creating a workbench, users were unable to create a new data connection, or connect to existing data connections.

[RHODS-6281](https://issues.redhat.com/browse/RHODS-6281)- OpenShift AI administrators could not access Settings page if an admin group was deleted from cluster

Previously, if a Red Hat OpenShift AI administrator group was deleted from the cluster, OpenShift AI administrator users could no longer access the Settings page on the OpenShift AI dashboard. In particular, the following behavior was seen:

- When an OpenShift AI administrator user tried to access the Settings → User management page, a "Page Not Found" error appeared.
- Cluster administrators did not lose access to the Settings page on the OpenShift AI dashboard. When a cluster administrator accessed the Settings → User management page, a warning message appeared, indicating that the deleted OpenShift AI administrator group no longer existed in OpenShift. The deleted administrator group was then removed from`OdhDashboardConfig`, and administrator access was restored.

This issue is now resolved.

[RHODS-1968](https://issues.redhat.com/browse/RHODS-1968)- Deleted users stayed logged in until dashboard was refreshed

Previously, when a user’s permissions for the Red Hat OpenShift AI dashboard were revoked, the user would notice the change only after a refresh of the dashboard page.

This issue is now resolved. When a user’s permissions are revoked, the OpenShift AI dashboard locks the user out within 30 seconds, without the need for a refresh.

[RHODS-6384](https://issues.redhat.com/browse/RHODS-6384)- A workbench data connection was incorrectly updated when creating a duplicated data connection

When creating a data connection that contained the same name as an existing data connection, the data connection creation failed, but the associated workbench still restarted and connected to the wrong data connection. This issue has been resolved. Workbenches now connect to the correct data connection.

[RHODS-6370](https://issues.redhat.com/browse/RHODS-6370)- Workbenches failed to receive the latest toleration

Previously, to acquire the latest toleration, users had to attempt to edit the relevant workbench, make no changes, and save the workbench again. Users can now apply the latest toleration change by stopping and then restarting their data science project’s workbench.

[RHODS-6779](https://issues.redhat.com/browse/RHODS-6779)- Models failed to be served after upgrading from OpenShift AI 1.20 to OpenShift AI 1.21

When upgrading from OpenShift AI 1.20 to OpenShift AI 1.21, the`modelmesh-serving` pod attempted to pull a non-existent image, causing an image pull error. As a result, models were unable to be served using the model serving feature in OpenShift AI. The`odh-openvino-servingruntime-container-v1.21.0-15` image now deploys successfully.

[RHODS-5945](https://issues.redhat.com/browse/RHODS-5945)- Anaconda Professional Edition could not be enabled in OpenShift AI

Anaconda Professional Edition could not be enabled for use in OpenShift AI. Instead, an`InvalidImageName` error was displayed in the associated pod’s Events page. Anaconda Professional Edition can now be successfully enabled.

[RHODS-5822](https://issues.redhat.com/browse/RHODS-5822)- Admin users were not warned when usage exceeded 90% and 100% for PVCs created by data science projects.

Warnings indicating when a PVC exceeded 90% and 100% of its capacity failed to display to admin users for PVCs created by data science projects. Admin users can now view warnings about when a PVC exceeds 90% and 100% of its capacity from the dashboard.

[RHODS-5889](https://issues.redhat.com/browse/RHODS-5889)- Error message was not displayed if a data science notebook was stuck in "pending" status

If a notebook pod could not be created, the OpenShift AI interface did not show an error message. An error message is now displayed if a data science notebook cannot be spawned.

[RHODS-5886](https://issues.redhat.com/browse/RHODS-5886)- Returning to the Hub Control Panel dashboard from the data science workbench failed

If you attempted to return to the dashboard from your workbench Jupyter notebook by clicking on File → Log Out, you were redirected to the dashboard and remained on a "Logging out" page. Likewise, if you attempted to return to the dashboard by clicking on File → Hub Control Panel, you were incorrectly redirected to the Start a notebook server page. Returning to the Hub Control Panel dashboard from the data science workbench now works as expected.

[RHODS-6101](https://issues.redhat.com/browse/RHODS-6101)- Administrators were unable to stop all notebook servers

OpenShift AI administrators could not stop all notebook servers simultaneously. Administrators can now stop all notebook servers using the Stop all servers button and stop a single notebook by selecting Stop server from the action menu beside the relevant user.

[RHODS-5891](https://issues.redhat.com/browse/RHODS-5891)- Workbench event log was not clearly visible

When creating a workbench, users could not easily locate the event log window in the OpenShift AI interface. The Starting label under the Status column is now underlined when you hover over it, indicating you can click on it to view the notebook status and the event log.

[RHODS-6296](https://issues.redhat.com/browse/RHODS-6296)- ISV icons did not render when using a browser other than Google Chrome

When using a browser other than Google Chrome, not all ISV icons under Explore and Resources pages were rendered. ISV icons now display properly on all supported browsers.

[RHODS-3182](https://issues.redhat.com/browse/RHODS-3182)- Incorrect number of available GPUs was displayed in Jupyter

When a user attempts to create a notebook instance in Jupyter, the maximum number of GPUs available for scheduling was not updated as GPUs are assigned. Jupyter now displays the correct number of GPUs available.

[RHODS-5890](https://issues.redhat.com/browse/RHODS-5890)- When multiple persistent volumes were mounted to the same directory, workbenches failed to start

When mounting more than one persistent volume (PV) to the same mount folder in the same workbench, creation of the notebook pod failed and no errors were displayed to indicate there was an issue.

[RHODS-5768](https://issues.redhat.com/browse/RHODS-5768)- Data science projects were not visible to users in Red Hat OpenShift AI

Removing the`[DSP]` suffix at the end of a project’s Display Name property caused the associated data science project to no longer be visible. It is no longer possible for users to remove this suffix.

[RHODS-5701](https://issues.redhat.com/browse/RHODS-5701)- Data connection configuration details were overwritten

When a data connection was added to a workbench, the configuration details for that data connection were saved in environment variables. When a second data connection was added, the configuration details are saved using the same environment variables, which meant the configuration for the first data connection was overwritten. At the moment, users can add a maximum of one data connection to each workbench.

[RHODS-5252](https://issues.redhat.com/browse/RHODS-5252)- The notebook Administration page did not provide administrator access to a user’s notebook server

The notebook Administration page, accessed from the OpenShift AI dashboard, did not provide the means for an administrator to access a user’s notebook server. Administrators were restricted to only starting or stopping a user’s notebook server.

[RHODS-2438](https://issues.redhat.com/browse/RHODS-2438)- PyTorch and TensorFlow images were unavailable when upgrading

When upgrading from OpenShift AI 1.3 to a later version, PyTorch and TensorFlow images were unavailable to users for approximately 30 minutes. As a result, users were unable to start PyTorch and TensorFlow notebooks in Jupyter during the upgrade process. This issue has now been resolved.

[RHODS-5354](https://issues.redhat.com/browse/RHODS-5354)- Environment variable names were not validated when starting a notebook server

Environment variable names were not validated on the Start a notebook server page. If an invalid environment variable was added, users were unable to successfully start a notebook. The environmental variable name is now checked in real-time. If an invalid environment variable name is entered, an error message displays indicating valid environment variable names must consist of alphabetic characters, digits, _, -, or ., and must not start with a digit.

[RHODS-4617](https://issues.redhat.com/browse/RHODS-4617)- The Number of GPUs drop-down was only visible if there were GPUs available

Previously, the Number of GPUs drop-down was only visible on the Start a notebook server page if GPU nodes were available. The Number of GPUs drop-down now also correctly displays if an autoscaling machine pool is defined in the cluster, even if no GPU nodes are currently available, possibly resulting in the provisioning of a new GPU node on the cluster.

[RHODS-5420](https://issues.redhat.com/browse/RHODS-5420)- Cluster admin did not get administrator access if it was the only user present in the cluster

Previously, when the cluster admin was the only user present in the cluster, it did not get Red Hat OpenShift administrator access automatically. Administrator access is now correctly applied to the cluster admin user.

[RHODS-4321](https://issues.redhat.com/browse/RHODS-4312)- Incorrect package version displayed during notebook selection

The Start a notebook server page displayed an incorrect version number (11.4 instead of 11.7) for the CUDA notebook image. The version of CUDA installed is no longer specified on this page.

[RHODS-5001](https://issues.redhat.com/browse/RHODS-5001)- Admin users could add invalid tolerations to notebook pods

An admin user could add invalid tolerations on the Cluster settings page without triggering an error. If a invalid toleration was added, users were unable to successfully start notebooks. The toleration key is now checked in real-time. If an invalid toleration name is entered, an error message displays indicating valid toleration names consist of alphanumeric characters, -, _, or ., and must start and end with an alphanumeric character.

[RHODS-5100](https://issues.redhat.com/browse/RHODS-5100)- Group role bindings were not applied to cluster administrators

Previously, if you had assigned cluster admin privileges to a group rather than a specific user, the dashboard failed to recognize administrative privileges for users in the administrative group. Group role bindings are now correctly applied to cluster administrators as expected.

[RHODS-4947](https://issues.redhat.com/browse/RHODS-4947)- Old Minimal Python notebook image persisted after upgrade

After upgrading from OpenShift AI 1.14 to 1.15, the older version of the Minimal Python notebook persisted, including all associated package versions. The older version of the Minimal Python notebook no longer persists after upgrade.

[RHODS-4935](https://issues.redhat.com/browse/RHODS-4935)- Excessive "missing x-forwarded-access-token header" error messages displayed in dashboard log

The`rhods-dashboard` pod’s log contained an excessive number of "missing x-forwarded-access-token header" error messages due to a readiness probe hitting the`/status` endpoint. This issue has now been resolved.

[RHODS-2653](https://issues.redhat.com/browse/RHODS-2653)- Error occurred while fetching the generated images in the sample Pachyderm notebook

An error occurred when a user attempted to fetch an image using the sample Pachyderm notebook in Jupyter. The error stated that the image could not be found. Pachyderm has corrected this issue.

[RHODS-4584](https://issues.redhat.com/browse/RHODS-4584)- Jupyter failed to start a notebook server using the OpenVINO notebook image

Jupyter’s Start a notebook server page failed to start a notebook server using the OpenVINO notebook image. Intel has provided an update to the OpenVINO operator to correct this issue.

[RHODS-4923](https://issues.redhat.com/browse/RHODS-4923)- A non-standard check box displayed after disabling usage data collection

After disabling usage data collection on the Cluster settings page, when a user accessed another area of the OpenShift AI dashboard, and then returned to the Cluster settings page, the Allow collection of usage data check box had a non-standard style applied, and therefore did not look the same as other check boxes when selected or cleared.

[RHODS-4938](https://issues.redhat.com/browse/RHODS-4938)- Incorrect headings were displayed in the Notebook Images page

The Notebook Images page, accessed from the Settings page on the OpenShift AI dashboard, displayed incorrect headings in the user interface. The Notebook image settings heading displayed as BYON image settings, and the Import Notebook images heading displayed as Import BYON images. The correct headings are now displayed as expected.

[RHODS-4818](https://issues.redhat.com/browse/RHODS-4818)- Jupyter was unable to display images when the NVIDIA GPU add-on was installed

The Start a notebook server page did not display notebook images after installing the NVIDIA GPU add-on. Images are now correctly displayed, and can be started from the Start a notebook server page.

[RHODS-4797](https://issues.redhat.com/browse/RHODS-4797)- PVC usage limit alerts were not sent when usage exceeded 90% and 100%

Alerts indicating when a PVC exceeded 90% and 100% of its capacity failed to be triggered and sent. These alerts are now triggered and sent as expected.

[RHODS-4366](https://issues.redhat.com/browse/RHODS-4366)- Cluster settings were reset on operator restart

When the OpenShift AI operator pod was restarted, cluster settings were sometimes reset to their default values, removing any custom configuration. The OpenShift AI operator was restarted when a new version of OpenShift AI was released, and when the node that ran the operator failed. This issue occurred because the operator deployed ConfigMaps incorrectly. Operator deployment instructions have been updated so that this no longer occurs.

[RHODS-4318](https://issues.redhat.com/browse/RHODS-4318)- The OpenVINO notebook image failed to build successfully

The OpenVINO notebook image failed to build successfully and displayed an error message. This issue has now been resolved.

[RHODS-3743](https://issues.redhat.com/browse/RHODS-3743)- Starburst Galaxy quick start did not provide download link in the instruction steps

The Starburst Galaxy quick start, located on the Resources page on the dashboard, required the user to open the`explore-data.ipynb notebook`, but failed to provide a link within the instruction steps. Instead, the link was provided in the quick start’s introduction.

[RHODS-1974](https://issues.redhat.com/browse/RHODS-1974)- Changing alert notification emails required pod restart

Changes to the list of notification email addresses in the Red Hat OpenShift AI Add-On were not applied until after the`rhods-operator` pod and the`prometheus-*` pod were restarted.

[RHODS-2738](https://issues.redhat.com/browse/RHODS-2738)- Red Hat OpenShift API Management 1.15.2 add-on installation did not successfully complete

For OpenShift AI installations that are integrated with the Red Hat OpenShift API Management 1.15.2 add-on, the Red Hat OpenShift API Management installation process did not successfully obtain the SMTP credentials secret. Subsequently, the installation did not complete.

[RHODS-3237](https://issues.redhat.com/browse/RHODS-3237)- GPU tutorial did not appear on dashboard

The "GPU computing" tutorial, located at [Gtc2018-numba](https://github.com/ContinuumIO/gtc2018-numba), did not appear on the Resources page on the dashboard.

[RHODS-3069](https://issues.redhat.com/browse/RHODS-3069)- GPU selection persisted when GPU nodes were unavailable

When a user provisioned a notebook server with GPU support, and the utilized GPU nodes were subsequently removed from the cluster, the user could not create a notebook server. This occurred because the most recently used setting for the number of attached GPUs was used by default.

[RHODS-3181](https://issues.redhat.com/browse/RHODS-3181)- Pachyderm now compatible with OpenShift Dedicated 4.10 clusters

Pachyderm was not initially compatible with OpenShift Dedicated 4.10, and so was not available in OpenShift AI running on an OpenShift Dedicated 4.10 cluster. Pachyderm is now available on and compatible with OpenShift Dedicated 4.10.

[RHODS-2160](https://issues.redhat.com/browse/RHODS-2160)- Uninstall process failed to complete when both OpenShift AI and OpenShift API Management were installed

When OpenShift AI and OpenShift API Management are installed together on the same cluster, they use the same Virtual Private Cluster (VPC). The uninstall process for these Add-ons attempts to delete the VPC. Previously, when both Add-ons are installed, the uninstall process for one service was blocked because the other service still had resources in the VPC. The cleanup process has been updated so that this conflict does not occur.

[RHODS-2747](https://issues.redhat.com/browse/RHODS-2747)- Images were incorrectly updated after upgrading OpenShift AI

After the process to upgrade OpenShift AI completed, Jupyter failed to update its notebook images. This was due to an issue with the image caching mechanism. Images are now correctly updating after an upgrade.

[RHODS-2425](https://issues.redhat.com/browse/RHODS-2425)- Incorrect TensorFlow and TensorBoard versions displayed during notebook selection

The Start a notebook server page displayed incorrect version numbers (2.4.0) for TensorFlow and TensorBoard in the TensorFlow notebook image. These versions have been corrected to TensorFlow 2.7.0 and TensorBoard 2.6.0.

[RHODS-24339](https://issues.redhat.com/browse/RHODS-2433)- Quick start links did not display for enabled applications

For some applications, the Open quick start link failed to display on the application tile on the Enabled page. As a result, users did not have direct access to the quick start tour for the relevant application.

[RHODS-2215](https://issues.redhat.com/browse/RHODS-2215)- Incorrect Python versions displayed during notebook selection

The Start a notebook server page displayed incorrect versions of Python for the TensorFlow and PyTorch notebook images. Additionally, the third integer of package version numbers is now no longer displayed.

[RHODS-1977](https://issues.redhat.com/browse/RHODS-1977)- Ten minute wait after notebook server start fails

If the Jupyter leader pod failed while the notebook server was being started, the user could not access their notebook server until the pod restarted, which took approximately ten minutes. This process has been improved so that the user is redirected to their server when a new leader pod is elected. If this process times out, users see a 504 Gateway Timeout error, and can refresh to access their server.

## Chapter 7. Known issuesCopy link

This section describes known issues in Red Hat OpenShift AI and any known methods of working around these issues.

[RHAIENG-1139](https://issues.redhat.com/browse/RHAIENG-1139)- Cannot deploy LlamaStackDistribution with the same name in multiple namespaces

If you create two`LlamaStackDistribution` resources with the same name in different namespaces, the ReplicaSet for the second resource fails to start the Llama Stack pod. The Llama Stack Operator does not correctly assign security constraints when duplicate names are used across namespaces.

Workaround Use a unique name for each`LlamaStackDistribution` in every namespace. For example, include the project name or add a suffix such as`llama-stack-distribution-209342`.

[RHOAIENG-34923](https://issues.redhat.com/browse/RHOAIENG-34923)- Runtime configuration missing when running a pipeline from JupyterLab

The runtime configuration might not appear in the Elyra pipeline editor when you run a pipeline from the first active workbench in a project. This occurs because the configuration fails to populate for the initial workbench session.

Workaround Restart the workbench. After restarting, the runtime configuration becomes available for pipeline execution.

[RHAIENG-35055](https://issues.redhat.com/browse/RHAIENG-35055)- Model catalog fails to initialize after upgrading from OpenShift AI 2.24

After upgrading from OpenShift AI 2.24, the model catalog might fail to initialize and load. The OpenShift AI dashboard displays a Request access to model catalog error.

Workaround

Delete the existing model catalog ConfigMap and deployment by running the following commands:

```shell-session
$ oc delete configmap model-catalog-sources -n rhoai-model-registries --ignore-not-found
$ oc delete deployment model-catalog -n rhoai-model-registries --ignore-not-found
```

[RHAIENG-35529](https://issues.redhat.com/browse/RHAIENG-35529)- Reconciliation issues in Data Science Pipelines Operator when using external Argo Workflows

If you enable the embedded Argo Workflows controllers (`argoWorkflowsControllers: Managed`) before deleting an existing external Argo Workflows installation, the workflow controller might fail to start and the Data Science Pipelines Operator (DSPO) might not reconcile its custom resources correctly.

Workaround Before enabling the embedded Argo Workflows controllers, delete any existing external Argo Workflows instance from the cluster.

[RHAIENG-36756](https://issues.redhat.com/browse/RHAIENG-36756)- Existing cluster storage option missing during model deployment when no connections exist

When creating a model deployment in a project with no defined data connections, the Existing cluster storage option does not appear, even if Persistent Volume Claims (PVCs) are available. As a result, you cannot select an existing PVC for model storage.

Workaround Create at least one connection of type`URI` in the project. Afterward, the Existing cluster storage option becomes available.

[RHOAIENG-36817](https://issues.redhat.com/browse/RHOAIENG-36817)- Inference server fails when Model server size is set to`small`

When creating an inference service via the dashboard, selecting a`small` Model server size causes subsequent inferencing requests to fail. As a result, the deployment of the inference service itself succeeds, but the inferencing requests fail with a timeout error.

Workaround To resolve this issue, select the Model server size as`large` from the dropdown.

[RHOAIENG-33995](https://issues.redhat.com/browse/RHOAIENG-33995)- Deployment of an inference service for Phi and Mistral models fails

The creation of an inference service for Phi and Mistral models using vLLM runtime on IBM Power cluster with {openshift-platform-url} 4.19 fails due to an error related to CPU backend. As a result, deployment of these models is affected, causing inference service creation failure.

Workaround To resolve this issue, disable the`sliding_window` mechanism in the serving runtime if it is enabled for CPU and Phi models. Sliding window is not currently supported in V1.

[RHOAIENG-33795](https://issues.redhat.com/browse/RHOAIENG-33795)- Manual`Route` creation needed for gRPC endpoint verification for Triton Inference Server on IBM Z

When verifying Triton Inference Server with gRPC endpoint,`Route` does not get created automatically. This happens because the Operator currently defaults to creating an edge-terminated route for REST only.

Workaround

To resolve this issue, manual Route creation is needed for gRPC endpoint verification for Triton Inference Server on IBM Z.

When the model deployment pod is up and running, define an edge-terminated`Route` object in a YAML file with the following contents:

```plaintext
apiVersion: route.openshift.io/v1
kind: Route
metadata:
  name: <grpc-route-name>                  # e.g. triton-grpc
  namespace: <model-deployment-namespace>  # namespace where your model is deployed
  labels:
    inferenceservice-name: <inference-service-name>
  annotations:
    haproxy.router.openshift.io/timeout: 30s
spec:
  host: <custom-hostname>                  # e.g. triton-grpc.<apps-domain>
  to:
    kind: Service
    name: <service-name>                   # name of the predictor service (e.g. triton-predictor)
    weight: 100
  port:
    targetPort: grpc                       # must match the gRPC port exposed by the service
  tls:
    termination: edge
  wildcardPolicy: None
```

Create the`Route` object:

```plaintext
oc apply -f <route-file-name>.yaml
```

To send an inference request, enter the following command:

```plaintext
grpcurl -cacert <ca_cert_file>\ 
```

1

```plaintext

  -protoset triton_desc.pb \
  -d '{
    "model_name": "<model_name>",
    "inputs": [
      {
        "name": "<input_tensor_name>",
        "shape": [<shape>],
        "datatype": "<data_type>",
        "contents": {
          "<datatype_specific_contents>": [<input_data_values>]
        }
      }
    ],
    "outputs": [
      {
        "name": "<output_tensor_name>"
      }
    ]
  }' \
  <grpc_route_host>:443 \
  inference.GRPCInferenceService/ModelInfer
```

1

 is the path to your cluster router CA cert (for example, router-ca.crt).

Note

 is compiled as a protobuf descriptor file. You can generate it as`protoc -I. --descriptor_set_out=triton_desc.pb --include_imports grpc_service.proto`.

Download`grpc_service.proto` and`model_config.proto` files from the [triton-inference-service](https://github.com/triton-inference-server/common/tree/main/protobuf) GitHub page.

[RHOAIENG-33697](https://issues.redhat.com/browse/RHOAIENG-33697)- Unable to Edit or Delete models unless status is "Started"

When you deploy a model on the NVIDIA NIM or single-model serving platform, the Edit and Delete options in the action menu are not available for models in the Starting or Pending states. These options become available only after the model has been successfully deployed.

Workaround Wait until the model is in the Started state to make any changes or to delete the model.

[RHOAIENG-33645](https://issues.redhat.com/browse/RHOAIENG-33645)- LM-Eval Tier1 test failures

There can be failures with LM-Eval Tier1 tests because`confirm_run_unsafe_code` is not passed as an argument when a job is run, if you are using an older version of the`trustyai-service-operator`.

Workaround Ensure that you are using the latest version of the`trustyai-service-operator` and that`AllowCodeExecution` is enabled.

[RHOAIENG-29729](https://issues.redhat.com/browse/RHOAIENG-29729)- Model registry Operator in a restart loop after upgrade

After upgrading from OpenShift AI version 2.22 or earlier to version 2.23 or later with the model registry component enabled, the model registry Operator might enter a restart loop. This is due to an insufficient memory limit for the manager container in the`model-registry-operator-controller-manager` pod.

Workaround

To resolve this issue, you must trigger a reconciliation for the`model-registry-operator-controller-manager` deployment. Adding the`opendatahub.io/managed='true'` annotation to the deployment will accomplish this and apply the correct memory limit. You can add the annotation by running the following command:

```plaintext
oc annotate deployment model-registry-operator-controller-manager -n redhat-ods-applications opendatahub.io/managed='true' --overwrite
```

Note

This command overwrites custom values in the`model-registry-operator-controller-manager` deployment. For more information about custom deployment values, see [Customizing component deployment resources](https://docs.redhat.com/en/documentation/red_hat_openshift_ai_cloud_service/1/html/managing_openshift_ai/customizing-component-deployment-resources_resource-mgmt).

After the deployment updates and the memory limit increases from 128Mi to 256Mi, the container memory usage will stabilize and the restart loop will stop.

[RHOAIENG-31238](https://issues.redhat.com/browse/RHOAIENG-31238)- New observability stack enabled when creating DSCInitialization

When you remove a DSCInitialization resource and create a new one using OpenShift AI console form view, it enables a Technology Preview observability stack. This results in the deployment of an unwanted observability stack when recreating a DSCInitialization resource.

Workaround

To resolve this issue, manually remove the "metrics" and "traces" fields when recreating the DSCInitiliazation resource using the form view.

This is not required if you want to use the Technology Preview observability stack.

[RHOAIENG-32145](https://issues.redhat.com/browse/RHOAIENG-32145)- Llama Stack Operator deployment failures on OpenShift versions earlier than 4.17

When installing OpenShift AI on OpenShift clusters running versions earlier than 4.17, the integrated Llama Stack Operator (`llamastackoperator`) might fail to deploy.

The Llama Stack Operator requires Kubernetes version 1.32 or later, but OpenShift 4.15 uses Kubernetes 1.28. This version gap can cause schema validation failures when applying the`LlamaStackDistribution` custom resource definition (CRD), due to unsupported selectable fields introduced in Kubernetes 1.32.

Workaround Install OpenShift AI on an OpenShift cluster running version 4.17 or later.

[RHOAIENG-32242](https://issues.redhat.com/browse/RHOAIENG-32242)- Failure on creating NetworkPolicies for OpenShift versions 4.15 and 4.16

When installing OpenShift AI on OpenShift clusters running versions 4.15 or 4.16, deployment of certain`NetworkPolicy` resources might fail. This can occur when the`llamastackoperator` or related components attempt to create a`NetworkPolicy` in a protected namespace, such as`redhat-ods-applications`. The request can be blocked by the admission webhook`networkpolicies-validation.managed.openshift.io`, which restricts modifications to certain namespaces and resources, even for`cluster-admin` users. This restriction can apply to both self-managed and Red Hat–managed OpenShift environments.

Workaround Deploy OpenShift AI on an OpenShift cluster running version 4.17 or later. For clusters where the webhook restriction is enforced, contact your OpenShift administrator or Red Hat Support to determine an alternative deployment pattern or approved change to the affected namespace.

[RHOAIENG-32599](https://issues.redhat.com/browse/RHOAIENG-32599)- Inference service creation fails on IBM Z cluster

When you attempt to create an inference service using the vLLM runtime on an IBM Z cluster, it fails with the following error:`ValueError: 'aimv2' is already used by a Transformers config, pick another name`.

Workaround None.

[RHOAIENG-29731](https://issues.redhat.com/browse/RHOAIENG-29731)- Inference service creation fails on IBM Power cluster with OpenShift 4.19

When you attempt to create an inference service by using the vLLM runtime on an IBM Power cluster on OpenShift Container Platform version 4.19, it fails due to an error related to Non-Uniform Memory Access (NUMA).

Workaround When you create an inference service, set the environment variable`VLLM_CPU_OMP_THREADS_BIND` to`all`.

[RHOAIENG-29292](https://issues.redhat.com/browse/RHOAIENG-29292)- vLLM logs permission errors on IBM Z due to usage stats directory access

When running vLLM on the IBM Z architecture, the inference service starts successfully, but logs an error in a background thread related to usage statistics reporting. This happens because the service tries to write usage data to a restricted location (`/.config`), which it does not have permission to access.

The following error appears in the logs:

```plaintext
Exception in thread Thread-2 (_report_usage_worker):
Traceback (most recent call last):
 ...
PermissionError: [Error 13] Permission denied: '/.config'
```

Workaround To prevent this error and suppress the usage stats logging, set the`VLLM_NO_USAGE_STATS=1` environment variable in the inference service deployment. This disables automatic usage reporting, avoiding permission issues when you write to system directories.

[RHOAIENG-28910](https://issues.redhat.com/browse/RHOAIENG-28910)- Unmanaged KServe resources are deleted after upgrading from 2.16 to 2.19 or later

During the upgrade from OpenShift AI 2.16 to 1, the`FeatureTracker` custom resource (CR) is deleted before its owner references are fully removed from associated KServe-related resources. As a result, resources that were originally created by the Red Hat OpenShift AI Operator with a`Managed` state and later changed to`Unmanaged` in the`DataScienceCluster`(DSC) custom resource (CR) might be unintentionally removed. This issue can disrupt model serving functionality until the resources are manually restored.

The following resources might be deleted in 1 if they were changed to`Unmanaged` in 2.16:

Expand

`KnativeServing`

`knative-serving`

`knative-serving`

`ServiceMeshMember`

`knative-serving`

`default`

`Gateway`

`istio-system`

`kserve-local-gateway`

`Gateway`

`knative-serving`

`knative-ingress-gateway`

`Gateway`

`knative-serving`

`knative-local-gateway`

| | Kind | Namespace | Name |
| --- | --- | --- | --- |

Show more

Workaround

If you have already upgraded from OpenShift AI 2.16 to 1, perform one of the following actions:

If you do not have an existing backup, you can use the Operator to recreate the deleted resources:

In the DSC, set`spec.components.kserve.serving.managementState` to`Managed`, and then save the change to allow the Operator to recreate the resources.

Wait until the Operator has recreated the resources.

1. Back up any resources you have already recreated.
2. In the DSC, set`spec.components.kserve.serving.managementState` back to`Unmanaged`, and then save the change.
3. Reapply any previous custom changes to the recreated`KnativeServing`,`ServiceMeshMember`, and`Gateway` CRs resources.

If you have not yet upgraded, perform the following actions before upgrading to prevent this issue:

1. In the DSC, set`spec.components.kserve.serving.managementState` to`Unmanaged`.
2. For each of the affected`KnativeServing`,`ServiceMeshMember`, and`Gateway` resources listed in the above table, edit its CR by deleting the`FeatureTracker` owner reference. This edit removes the resource’s dependency on the`FeatureTracker` and prevents the deletion of the resource during the upgrade process.

[RHOAIENG-24545](https://issues.redhat.com/browse/RHOAIENG-24545)- Runtime images are not present in the workbench after the first start

The list of runtime images does not properly populate the first running workbench instance in the namespace, therefore no image is shown for selection in the Elyra pipeline editor.

Workaround Restart the workbench. After restarting the workbench, the list of runtime images populates both the workbench and the select box for the Elyra pipeline editor.

[RHOAIENG-20209](https://issues.redhat.com/browse/RHOAIENG-20209)- Warning message not displayed when requested resources exceed threshold

When you click Distributed workloads → Project metrics and view the Requested resources section, the charts show the requested resource values and the total shared quota value for each resource (CPU and Memory). However, when the Requested by all projects value exceeds the Warning threshold value for that resource, the expected warning message is not displayed.

Workaround None.

[SRVKS-1301](https://issues.redhat.com/browse/SRVKS-1301)(previously documented as RHOAIENG-18590) - The`KnativeServing` resource fails after disabling and enabling KServe

After disabling and enabling the`kserve` component in the DataScienceCluster, the`KnativeServing` resource might fail.

Workaround

Delete all`ValidatingWebhookConfiguration` and`MutatingWebhookConfiguration` webhooks related to Knative:

Get the webhooks:

```plaintext
oc get ValidatingWebhookConfiguration,MutatingWebhookConfiguration | grep -i knative
```

Get the webhooks:

```plaintext
oc get ValidatingWebhookConfiguration,MutatingWebhookConfiguration | grep -i knative
```

1. Ensure KServe is disabled.
2. Delete the webhooks.
3. Enable KServe.
4. Verify that the KServe pod can successfully spawn, and that pods in the`knative-serving` namespace are active and operational.

[RHOAIENG-16247](https://issues.redhat.com/browse/RHOAIENG-16247)- Elyra pipeline run outputs are overwritten when runs are launched from OpenShift AI dashboard

When a pipeline is created and run from Elyra, outputs generated by the pipeline run are stored in the folder`bucket-name/pipeline-name-timestamp` of object storage.

When a pipeline is created from Elyra and the pipeline run is started from the OpenShift AI dashboard, the timestamp value is not updated. This can cause pipeline runs to overwrite files created by previous pipeline runs of the same pipeline.

This issue does not affect pipelines compiled and imported using the OpenShift AI dashboard because`runid` is always added to the folder used in object storage. For more information about storage locations used in data science pipelines, see [Storing data with data science pipelines](https://docs.redhat.com/en/documentation/red_hat_openshift_ai_cloud_service/1/html/working_with_data_science_pipelines/managing-pipeline-runs_ds-pipelines#storing-data-with-data-science-pipelines_ds-pipelines).

Workaround When storing files in an Elyra pipeline, use different subfolder names on each pipeline run.

[RHOAIENG-8294](https://issues.redhat.com/browse/RHOAIENG-8294)- CodeFlare error when upgrading OpenShift AI 2.8 to version 2.10 or later

If you try to upgrade OpenShift AI 2.8 to version 2.10 or later, the following error message is shown for the CodeFlare component, due to a mismatch with the`AppWrapper` custom resource definition (CRD) version.

```plaintext
ReconcileCompletedWithComponentErrors DataScienceCluster resource reconciled with component errors: 1 error occurred: * CustomResourceDefinition.apiextensions.k8s.io "appwrappers.workload.codeflare.dev" is invalid: status.storedVersions[0]: Invalid value: "v1beta1": must appear in spec.versions
```

Workaround

Delete the existing`AppWrapper` CRD:

```plaintext
$ oc delete crd appwrappers.workload.codeflare.dev
```

Wait for about 20 seconds, and then ensure that a new`AppWrapper` CRD is automatically applied, as shown in the following example:

```plaintext
$ oc get crd appwrappers.workload.codeflare.dev
NAME                                 CREATED AT
appwrappers.workload.codeflare.dev   2024-11-22T18:35:04Z
```

[RHOAIENG-7716](https://issues.redhat.com/browse/RHOAIENG-7716)- Pipeline condition group status does not update

When you run a pipeline that has loops (`dsl.ParallelFor`) or condition groups (`dsl.lf`), the UI displays a Running status for the loops and groups, even after the pipeline execution is complete.

Workaround

You can confirm if a pipeline is still running by checking that no child tasks remain active.

Expand the condition group and click a child task.

A panel that contains information about the child task is displayed

On the panel, click the Task details tab.

The Status field displays the correct status for the child task.

1. From the OpenShift AI dashboard, click Data Science Pipelines → Runs.
2. From the Project list, click your data science project.
3. From the Runs tab, click the pipeline run that you want to check the status of.

[RHOAIENG-6409](https://issues.redhat.com/browse/RHOAIENG-6409)-`Cannot save parameter` errors appear in pipeline logs for successful runs

When you run a pipeline more than once with data science pipelines 2.0,`Cannot save parameter` errors appear in the pipeline logs for successful pipeline runs. You can safely ignore these errors.

Workaround None.

[RHOAIENG-3025](https://issues.redhat.com/browse/RHOAIENG-3025)- OVMS expected directory layout conflicts with the KServe StoragePuller layout

When you use the OpenVINO Model Server (OVMS) runtime to deploy a model on the single-model serving platform (which uses KServe), there is a mismatch between the directory layout expected by OVMS and that of the model-pulling logic used by KServe. Specifically, OVMS requires the model files to be in the`/ /models/1/` directory, while KServe places them in the`/ /models/` directory.

Workaround

Perform the following actions:

To use the OVMS runtime to deploy a model on the single-model serving platform, choose one of the following options to specify the path to your model files:

- If you are using the OpenShift AI dashboard to deploy your model, in the Path field for your data connection, use the`/ /models/` format to specify the path to your model files. Do not specify the`1/` directory as part of the path.
- If you are creating your own`InferenceService` custom resource to deploy your model, configure the value of the`storageURI` field as`/ /models/`. Do not specify the`1/` directory as part of the path.

KServe pulls model files from the subdirectory in the path that you specified. In this case, KServe correctly pulls model files from the`/ /models/1/` directory in your S3-compatible storage.

[RHOAIENG-3018](https://issues.redhat.com/browse/RHOAIENG-3018)- OVMS on KServe does not expose the correct endpoint in the dashboard

When you use the OpenVINO Model Server (OVMS) runtime to deploy a model on the single-model serving platform, the URL shown in the Inference endpoint field for the deployed model is not complete.

Workaround To send queries to the model, you must add the`/v2/models/_ _/infer` string to the end of the URL. Replace`_ _` with the name of your deployed model.

[RHOAIENG-2602](https://issues.redhat.com/browse/RHOAIENG-2602)- “Average response time" server metric graph shows multiple lines due to ModelMesh pod restart

The Average response time server metric graph shows multiple lines if the ModelMesh pod is restarted.

Workaround None.

[RHOAIENG-2228](https://issues.redhat.com/browse/RHOAIENG-2228)- The performance metrics graph changes constantly when the interval is set to 15 seconds

On the Endpoint performance tab of the model metrics screen, if you set the Refresh interval to 15 seconds and the Time range to 1 hour, the graph results change continuously.

Workaround None.

[RHOAIENG-2183](https://issues.redhat.com/browse/RHOAIENG-2183)- Endpoint performance graphs might show incorrect labels

In the Endpoint performance tab of the model metrics screen, the graph tooltip might show incorrect labels.

Workaround None.

[RHOAIENG-131](https://issues.redhat.com/browse/RHOAIENG-131)- gRPC endpoint not responding properly after the InferenceService reports as Loaded

When numerous`InferenceService` instances are generated and directed requests, Service Mesh Control Plane (SMCP) becomes unresponsive. The status of the`InferenceService` instance is`Loaded`, but the call to the gRPC endpoint returns with errors.

Workaround Edit the`ServiceMeshControlPlane` custom resource (CR) to increase the memory limit of the Istio egress and ingress pods.

[RHOAIENG-1619](https://issues.redhat.com/browse/RHOAIENG-1619)(previously documented as DATA-SCIENCE-PIPELINES-165) - Poor error message when S3 bucket is not writable

When you set up a data connection and the S3 bucket is not writable, and you try to upload a pipeline, the error message`Failed to store pipelines` is not helpful.

Workaround Verify that your data connection credentials are correct and that you have write access to the bucket you specified.

[RHOAIENG-1207](https://issues.redhat.com/browse/RHOAIENG-1207)(previously documented as [ODH-DASHBOARD-1758](https://github.com/opendatahub-io/odh-dashboard/issues/1758)) - Error duplicating OOTB custom serving runtimes several times

If you duplicate a model-serving runtime several times, the duplication fails with the`Serving runtime name " " already exists` error message.

Workaround Change the`metadata.name` field to a unique value.

[RHOAIENG-133](https://issues.redhat.com/browse/RHOAIENG-133)- Existing workbench cannot run Elyra pipeline after workbench restart

If you use the Elyra JupyterLab extension to create and run data science pipelines within JupyterLab, and you configure the pipeline server after you created a workbench and specified a workbench image within the workbench, you cannot execute the pipeline, even after restarting the workbench.

Workaround

1. Stop the running workbench.
2. Edit the workbench to make a small modification. For example, add a new dummy environment variable, or delete an existing unnecessary environment variable. Save your changes.
3. Restart the workbench.
4. In the left sidebar of JupyterLab, click Runtimes.
5. Confirm that the default runtime is selected.

[RHODS-12798](https://issues.redhat.com/browse/RHODS-12798)- Pods fail with "unable to init seccomp" error

Pods fail with`CreateContainerError` status or`Pending` status instead of`Running` status, because of a known kernel bug that introduced a`seccomp` memory leak. When you check the events on the namespace where the pod is failing, or run the`oc describe pod` command, the following error appears:

```plaintext
runc create failed: unable to start container process: unable to init seccomp: error loading seccomp filter into kernel: error loading seccomp filter: errno 524
```

Workaround Increase the value of`net.core.bpf_jit_limit` as described in the Red Hat Knowledgebase solution [Pods failing with error loading seccomp filter into kernel: errno 524 in OpenShift 4](https://access.redhat.com/solutions/7030968).

[KUBEFLOW-177](https://github.com/opendatahub-io/kubeflow/issues/177)- Bearer token from application not forwarded by OAuth-proxy

You cannot use an application as a custom workbench image if its internal authentication mechanism is based on a bearer token. The OAuth-proxy configuration removes the bearer token from the headers, and the application cannot work properly.

Workaround None.

[KUBEFLOW-157](https://github.com/opendatahub-io/kubeflow/issues/157)- Logging out of JupyterLab does not work if you are already logged out of the OpenShift AI dashboard

If you log out of the OpenShift AI dashboard before you log out of JupyterLab, then logging out of JupyterLab is not successful. For example, if you know the URL for a Jupyter notebook, you are able to open this again in your browser.

Workaround Log out of JupyterLab before you log out of the OpenShift AI dashboard.

[RHODS-7718](https://issues.redhat.com/browse/RHODS-7718)- User without dashboard permissions is able to continue using their running workbenches indefinitely

When a Red Hat OpenShift AI administrator revokes a user’s permissions, the user can continue to use their running workbenches indefinitely.

Workaround When the OpenShift AI administrator revokes a user’s permissions, the administrator should also stop any running workbenches for that user.

[RHOAIENG-1152](https://issues.redhat.com/browse/RHOAIENG-1152)(previously documented as [RHODS-6356](https://issues.redhat.com/browse/RHODS-6356)) - The basic-workbench creation process fails for users who have never logged in to the dashboard

The dashboard’s Administration page for basic workbenches displays users who belong to the user group and admin group in OpenShift. However, if an administrator attempts to start a basic workbench on behalf of a user who has never logged in to the dashboard, the basic-workbench creation process fails and displays the following error message:

```plaintext
Request invalid against a username that does not exist.
```

Workaround Request that the relevant user logs into the dashboard.

[RHODS-5543](https://issues.redhat.com/browse/RHODS-5543)- When using the NVIDIA GPU Operator, more nodes than needed are created by the Node Autoscaler

When a pod cannot be scheduled due to insufficient available resources, the Node Autoscaler creates a new node. There is a delay until the newly created node receives the relevant GPU workload. Consequently, the pod cannot be scheduled and the Node Autoscaler’s continuously creates additional new nodes until one of the nodes is ready to receive the GPU workload. For more information about this issue, see the Red Hat Knowledgebase solution [When using the NVIDIA GPU Operator, more nodes than needed are created by the Node Autoscaler](https://access.redhat.com/solutions/6055181).

Workaround Apply the`cluster-api/accelerator` label in`machineset.spec.template.spec.metadata`. This causes the autoscaler to consider those nodes as unready until the GPU driver has been deployed.

[RHODS-4799](https://issues.redhat.com/browse/RHODS-4799)- Tensorboard requires manual steps to view

When a user has TensorFlow or PyTorch workbench images and wants to use TensorBoard to display data, manual steps are necessary to include environment variables in the workbench environment, and to import those variables for use in your code.

Workaround

When you start your basic workbench, use the following code to set the value for the TENSORBOARD_PROXY_URL environment variable to use your OpenShift AI user ID.

```plaintext
import os
os.environ["TENSORBOARD_PROXY_URL"]= os.environ["NB_PREFIX"]+"/proxy/6006/"
```

[RHODS-4718](https://issues.redhat.com/browse/RHODS-4718)- The Intel® oneAPI AI Analytics Toolkits quick start references nonexistent sample notebooks

The Intel® oneAPI AI Analytics Toolkits quick start, located on the Resources page on the dashboard, requires the user to load sample notebooks as part of the instruction steps, but refers to notebooks that do not exist in the associated repository.

Workaround None.

[RHODS-3984](https://issues.redhat.com/browse/RHODS-3984)- Incorrect package versions displayed during notebook selection

In the OpenShift AI interface, the Start a notebook server page displays incorrect version numbers for the JupyterLab and Notebook packages included in the oneAPI AI Analytics Toolkit notebook image. The page might also show an incorrect value for the Python version used by this image.

Workaround When you start your oneAPI AI Analytics Toolkit notebook server, you can check which Python packages are installed on your notebook server and which version of the package you have by running the`!pip list` command in a notebook cell.

[RHOAING-1147](https://issues.redhat.com/browse/RHOAIENG-1147)(previously documented as RHODS-2881) - Actions on dashboard not clearly visible

The dashboard actions to revalidate a disabled application license and to remove a disabled application tile are not clearly visible to the user. These actions appear when the user clicks on the application tile’s`Disabled` label. As a result, the intended workflows might not be clear to the user.

Workaround None.

[RHODS-2096](https://issues.redhat.com/browse/RHODS-2096)- IBM Watson Studio not available in OpenShift AI

IBM Watson Studio is not available when OpenShift AI is installed on OpenShift Dedicated 4.9 or higher, because it is not compatible with these versions of OpenShift Dedicated.

Workaround Contact the [Red Hat Customer Portal](https://access.redhat.com/support/contact) for assistance with manually configuring Watson Studio on OpenShift Dedicated 4.9 and higher.

[RHODS-1888](https://issues.redhat.com/browse/RHODS-1888)- OpenShift AI hyperlink still visible after uninstall

When the OpenShift AI Add-on is uninstalled from an OpenShift Dedicated cluster, the link to the OpenShift AI interface remains visible in the application launcher menu. Clicking this link results in a "Page Not Found" error because OpenShift AI is no longer available.

Workaround None.

## Chapter 8. Product featuresCopy link

Red Hat OpenShift AI provides a rich set of features for data scientists and cluster administrators. To learn more, see [Introduction to Red Hat OpenShift AI](https://docs.redhat.com/en/documentation/red_hat_openshift_ai_cloud_service/1/html/introduction_to_red_hat_openshift_ai_cloud_service).

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



## Installing and uninstalling

Installing and uninstalling OpenShift AI Cloud Service | Red Hat OpenShift AI Cloud Service | 1 | Red Hat Documentation

 

 

 

 

 

 

 Updates for Red Hat OpenShift AI Cloud Service are only provided up to the end of October 2025. 

 

# Installing and uninstalling OpenShift AI Cloud Service

---

Red Hat OpenShift AI Cloud Service 1

## Install and uninstall OpenShift AI as an add-on to an OpenShift cluster

Legal Notice

Abstract

Install and uninstall OpenShift AI as an add-on to a Red Hat managed environment, such as Red Hat OpenShift Dedicated or Red Hat OpenShift Service on Amazon Web Services (ROSA).

---

## Chapter 1. Architecture of OpenShift AICopy link

Red Hat OpenShift AI is a fully Red Hat managed cloud service that is available as an add-on to Red Hat OpenShift Dedicated and to Red Hat OpenShift Service on Amazon Web Services (ROSA classic).

OpenShift AI integrates the following components and services:

At the service layer:

OpenShift AI dashboard

A customer-facing dashboard that shows available and installed applications for the OpenShift AI environment as well as learning resources such as tutorials, quick start examples, and documentation. You can also access administrative functionality from the dashboard, such as user management, cluster settings, accelerator profiles, hardware profiles, and workbench image settings. In addition, data scientists can create their own projects from the dashboard. This enables them to organize their data science work into a single project.

Important

By default, hardware profiles are hidden in the dashboard navigation menu and user interface, while accelerator profiles remain visible. In addition, user interface components associated with the deprecated accelerator profiles functionality are still displayed. To show the Settings → Hardware profiles option in the dashboard navigation menu, and the user interface components associated with hardware profiles, set the`disableHardwareProfiles` value to`false` in the`OdhDashboardConfig` custom resource (CR) in OpenShift. For more information about setting dashboard configuration options, see [Customizing the dashboard](https://docs.redhat.com/en/documentation/red_hat_openshift_ai_cloud_service/1/html/managing_resources/customizing-the-dashboard).

Model serving Data scientists can deploy trained machine-learning models to serve intelligent applications in production. After deployment, applications can send requests to the model using its deployed API endpoint. Data science pipelines Data scientists can build portable machine learning (ML) workflows with data science pipelines 2.0, using Docker containers. With data science pipelines, data scientists can automate workflows as they develop their data science models. Jupyter (Red Hat managed) A Red Hat managed application that allows data scientists to configure a basic standalone workbench and develop machine learning models in JupyterLab. Distributed workloads Data scientists can use multiple nodes in parallel to train machine-learning models or process data more quickly. This approach significantly reduces the task completion time, and enables the use of larger datasets and more complex models. Retrieval-Augmented Generation (RAG) Data scientists and AI engineers can leverage Retrieval-Augmented Generation (RAG) capabilities provided by the integrated Llama Stack Operator. By combining large language model inference, semantic retrieval, and vector database storage, data scientists and AI engineers can obtain tailored, accurate, and verifiable answers to complex queries based on their own datasets within a data science project.

At the management layer:

The Red Hat OpenShift AI Operator A meta-operator that deploys and maintains all components and sub-operators that are part of OpenShift AI. Monitoring services Alertmanager, OpenShift Telemetry, and Prometheus work together to gather metrics from OpenShift AI and organize and display those metrics in useful ways for monitoring and billing purposes. Alerts from Alertmanager are sent to PagerDuty, responsible for notifying Red Hat of any issues with your managed cloud service.

- Legal Notice

When you install the Red Hat OpenShift AI Add-on in the OpenShift Cluster Manager, the following new projects are created:

- The`redhat-ods-operator` project contains the Red Hat OpenShift AI Operator.
- The`redhat-ods-applications` project includes the dashboard and other required components of OpenShift AI.
- The`redhat-ods-monitoring` project contains services for monitoring and billing.
- The`rhods-notebooks` project is where basic workbenches are deployed by default.

You or your data scientists must create additional projects for the applications that will use your machine learning models.

Do not install independent software vendor (ISV) applications in namespaces associated with OpenShift AI add-ons unless you are specifically directed to do so on the application tile on the dashboard.

## Chapter 2. Installing and deploying OpenShift AICopy link

Red Hat OpenShift AI is a platform for data scientists and developers of artificial intelligence (AI) applications. It provides a fully supported environment that lets you rapidly develop, train, test, and deploy machine learning models on-premises and/or in the public cloud.

OpenShift AI is provided as a managed cloud service add-on for Red Hat OpenShift or as self-managed software that you can install on-premise or in the public cloud on OpenShift.

For information about installing OpenShift AI as self-managed software on your OpenShift cluster in a connected or a disconnected environment, see [Product Documentation for Red Hat OpenShift AI Self-Managed](https://docs.redhat.com/en/documentation/red_hat_openshift_ai_self-managed/latest).

There are two deployment options for Red Hat OpenShift AI as a managed cloud service add-on:

OpenShift Dedicated with a Customer Cloud Subscription on Amazon Web Services or Google Cloud Platform

OpenShift Dedicated is a complete OpenShift Container Platform cluster provided as a cloud service, configured for high availability, and dedicated to a single customer. OpenShift Dedicated is professionally managed by Red Hat and hosted on Amazon Web Services (AWS) or Google Cloud Platform (GCP). The Customer Cloud Subscription (CCS) model allows Red Hat to deploy and manage clusters into a customer’s AWS or GCP account. Contact your Red Hat account manager to get OpenShift Dedicated through a CCS.

Red Hat OpenShift Service on AWS (ROSA classic)

ROSA is a fully-managed, turnkey application platform that allows you to focus on delivering value to your customers by building and deploying applications. You subscribe to the service directly from your AWS account.

Installing OpenShift AI as a managed cloud service involves the following high-level tasks:

Subscribe to the Red Hat OpenShift AI Add-on.

For OpenShift Dedicated with a CCS for AWS or GCP, get a subscription through Red Hat.

For ROSA classic, get a subscription through the AWS Marketplace.

1. Confirm that your OpenShift cluster meets all requirements.
2. Configure an identity provider for your OpenShift cluster.
3. Add administrative users for your OpenShift cluster.
4. Install the Red Hat OpenShift AI Add-on.
5. Access the OpenShift AI dashboard.
6. Optionally, configure and enable your accelerators in OpenShift AI to ensure that your data scientists can use compute-heavy workloads in their models. See [Enabling accelerators](https://docs.redhat.com/en/documentation/red_hat_openshift_ai_cloud_service/1/html/installing_and_uninstalling_openshift_ai_cloud_service/enabling-accelerators_install).

### 2.1. Requirements for OpenShift AICopy link

You must meet the following requirements before you can install OpenShift AI on your Red Hat OpenShift Dedicated or Red Hat OpenShift Service on Amazon Web Services (ROSA classic) cluster:

A subscription for Red Hat OpenShift Dedicated or a subscription for ROSA

You can deploy Red Hat OpenShift Dedicated on your Amazon Web Services (AWS) or Google Cloud Platform (GCP) account by using the [Customer Cloud Subscription on AWS](https://docs.redhat.com/en/documentation/openshift_dedicated/4/html/planning_your_environment/aws-ccs) or [Customer Cloud Subscription on GCP](https://docs.redhat.com/en/documentation/openshift_dedicated/4/html/planning_your_environment/gcp-ccs) model. Note that while Red Hat provides an option to install OpenShift Dedicated on a Red Hat cloud account, if you want to install OpenShift AI then you must install OpenShift Dedicated on your own cloud account.

Contact your Red Hat account manager to purchase a new Red Hat OpenShift Dedicated subscription. If you do not yet have an account manager, complete the form at [https://www.redhat.com/en/technologies/cloud-computing/openshift/dedicated#contact-form](https://www.redhat.com/en/technologies/cloud-computing/openshift/dedicated#contact-form) to request one.

You can subscribe to Red Hat OpenShift Service on AWS (ROSA classic) directly from your AWS account or by contacting your Red Hat account manager.

A Red Hat customer account

Go to OpenShift Cluster Manager ([http://console.redhat.com/openshift](https://console.redhat.com/openshift/)) and log in or register for a new account.

Cluster administrator access to your OpenShift cluster

You must have an OpenShift cluster with cluster administrator access. Use an existing cluster, or create a cluster by following the steps in the relevant documentation:

- [Creating an OpenShift Dedicated cluster](https://docs.redhat.com/en/documentation/openshift_dedicated/4/html/getting_started/osd-getting-started#osd-getting-started-create-cluster)
- [Install ROSA classic clusters](https://docs.redhat.com/en/documentation/red_hat_openshift_service_on_aws_classic_architecture/4/html/install_rosa_classic_clusters/index)

At least 2 worker nodes with at least 8 CPUs and 32 GiB RAM available for OpenShift AI to use when you install the Add-on. If this requirement is not met, the installation process fails to start and an error is displayed.

When you create a new cluster, select m6a.2xlarge for the computer node instance type to satisfy the requirements.

For an existing ROSA classic cluster, you can get the compute node instance type by using this command:

`rosa list machinepools --cluster=cluster-name`

You cannot alter a cluster’s compute node instance type, but you can add an additional machine pool or modify the default pool to meet the minimum requirements. However, the minimum resource requirements must be met by a single machine pool in the cluster.

For more information, see the relevant documentation:

- [Creating a machine pool in OpenShift Dedicated](https://docs.redhat.com/en/documentation/openshift_dedicated/4/html/cluster_administration/managing-compute-nodes-using-machine-pools#creating_machine_pools_ocm_osd-managing-worker-nodes)
- [OpenShift AI Service Definition](https://access.redhat.com/support/policy/updates/rhoai/service)
- [Creating a machine pool in ROSA](https://docs.redhat.com/en/documentation/red_hat_openshift_service_on_aws_classic_architecture/4/html/cluster_administration/managing-compute-nodes-using-machine-pools#creating_a_machine_pool_rosa-managing-worker-nodes)
- [Prepare your environment in ROSA](https://docs.redhat.com/en/documentation/red_hat_openshift_service_on_aws_classic_architecture/4/html/prepare_your_environment/index)

For a ROSA cluster, select an access management strategy

For installing OpenShift AI on a ROSA classic cluster, decide whether you want to install on a ROSA cluster that uses AWS Security Token Service (STS) or one that uses AWS Identity and Access Management (IAM) credentials. See [Install ROSA classic clusters](https://docs.redhat.com/en/documentation/red_hat_openshift_service_on_aws_classic_architecture/4/html/install_rosa_classic_clusters/index) for advice on deploying a ROSA cluster with or without AWS STS.

Install KServe dependencies

- To support the KServe component, which is used by the single-model serving platform to serve large models, you must also install Operators for Red Hat OpenShift Serverless and Red Hat OpenShift Service Mesh and perform additional configuration. For more information, see [About the single-model serving platform](https://docs.redhat.com/en/documentation/red_hat_openshift_ai_cloud_service/1/html/installing_and_uninstalling_openshift_ai_cloud_service/installing-the-single-model-serving-platform_component-install#About-the-single-model-serving-platform_component-install).
- If you want to add an authorization provider for the single-model serving platform, you must install the`Red Hat - Authorino` Operator. For information, see [Adding an authorization provider for the single-model serving platform](https://docs.redhat.com/en/documentation/red_hat_openshift_ai_cloud_service/1/html/installing_and_uninstalling_openshift_ai_cloud_service/installing-the-single-model-serving-platform_component-install#adding-an-authorization-provider_component-install).

Install RAG dependencies

If you plan to deploy Retrieval-Augmented Generation (RAG) workloads by using Llama Stack, you must meet the following requirements:

Access to object storage

Object storage is required for the following components:

- Single- or multi-model serving platforms, to deploy stored models. See [Deploying models on the single-model serving platform](https://docs.redhat.com/en/documentation/red_hat_openshift_ai_cloud_service/1/html/deploying_models/deploying_models_on_the_single_model_serving_platform#deploying-models-on-the-single-model-serving-platform_rhoai-user) or [Deploying a model by using the multi-model serving platform](https://docs.redhat.com/en/documentation/red_hat_openshift_ai_cloud_service/1/html/deploying_models/deploying_models_on_the_multi_model_serving_platform#deploying-a-model-using-the-multi-model-serving-platform_rhoai-user).
- Data science pipelines, to store artifacts, logs, and intermediary results. See [Configuring a pipeline server](https://docs.redhat.com/en/documentation/red_hat_openshift_ai_cloud_service/1/html/working_with_data_science_pipelines/managing-data-science-pipelines_ds-pipelines#configuring-a-pipeline-server_ds-pipelines) and [About pipeline logs](https://docs.redhat.com/en/documentation/red_hat_openshift_ai_cloud_service/1/html/working_with_data_science_pipelines/working-with-pipeline-logs_ds-pipelines#about-pipeline-logs_ds-pipelines).

Object storage can be used by the following components:

- Workbenches, to access large datasets. See [Adding a connection to your data science project](https://docs.redhat.com/en/documentation/red_hat_openshift_ai_cloud_service/1/html/working_on_data_science_projects/using-project-workbenches_projects#adding-a-connection-to-your-data-science-project_projects).
- Distributed workloads, to pull input data from and push results to. See [Running distributed data science workloads from data science pipelines](https://docs.redhat.com/en/documentation/red_hat_openshift_ai_cloud_service/1/html/working_with_distributed_workloads/running-ray-based-distributed-workloads_distributed-workloads#running-distributed-data-science-workloads-from-ds-pipelines_distributed-workloads).
- Code executed inside a pipeline. For example, to store the resulting model in object storage. See [Overview of pipelines in Jupyterlab](https://docs.redhat.com/en/documentation/red_hat_openshift_ai_cloud_service/1/html/working_with_data_science_pipelines/working-with-pipelines-in-jupyterlab_ds-pipelines#overview-of-pipelines-in-jupyterlab_ds-pipelines).

### 2.2. Configuring an identity provider for your OpenShift clusterCopy link

Configure an identity provider for your OpenShift Dedicated or Red Hat OpenShift Service on Amazon Web Services (ROSA) cluster to manage users and groups.

Red Hat OpenShift AI uses the same authentication systems as Red Hat OpenShift Dedicated and ROSA. Check the appropriate documentation for your cluster for more information.

- [Supported identity providers on OpenShift Dedicated](https://docs.redhat.com/en/documentation/openshift_dedicated/4/html/authentication_and_authorization/sd-configuring-identity-providers#understanding-idp-supported_sd-configuring-identity-providers)
- [Supported identity providers on ROSA](https://docs.redhat.com/en/documentation/red_hat_openshift_service_on_aws_classic_architecture/4/html/install_rosa_classic_clusters/rosa-sts-config-identity-providers)

Important

Adding more than one OpenShift Identity Provider can create problems when the same user name exists in multiple providers.

When`mappingMethod` is set to`claim`(the default mapping method for identity providers) and multiple providers have credentials associated with the same user name, the first provider used to log in to OpenShift is the one that works for that user, regardless of the order in which identity providers are configured.

For more information about mapping methods, see [Identity provider parameters in OpenShift Dedicated](https://docs.redhat.com/en/documentation/openshift_dedicated/4/html/authentication_and_authorization/sd-configuring-identity-providers#identity-provider-parameters_sd-configuring-identity-providers) or [Identity provider parameters in ROSA](https://docs.redhat.com/en/documentation/red_hat_openshift_service_on_aws_classic_architecture/4/html/install_rosa_classic_clusters/rosa-sts-config-identity-providers#identity-provider-parameters_rosa-sts-config-identity-providers).

Prerequisites

- Credentials for OpenShift Cluster Manager ([https://console.redhat.com/openshift/](https://console.redhat.com/openshift/)).
- An existing OpenShift Dedicated or ROSA cluster.

Procedure

Click Add identity provider.

1. Select your provider from the Identity Provider list.
2. Complete the remaining fields relevant to the identity provider that you selected. For more information, see [Configuring identity providers in OpenShift Dedicated](https://docs.redhat.com/en/documentation/openshift_dedicated/4/html/authentication_and_authorization/sd-configuring-identity-providers) or [Configuring identity providers in ROSA](https://docs.redhat.com/en/documentation/red_hat_openshift_service_on_aws_classic_architecture/4/html/install_rosa_classic_clusters/rosa-sts-config-identity-providers).

Verification

- Click Confirm.
- The configured identity providers are visible on the Access control tab of the Cluster details page.

### 2.3. Adding administrative users in OpenShiftCopy link

Before you can install and configure OpenShift AI for your data scientist users, you must obtain OpenShift cluster administrator (`cluster-admin`) privileges.

Prerequisites

- Credentials for Red Hat OpenShift Cluster Manager ([https://console.redhat.com/openshift/](https://console.redhat.com/openshift/)).
- An existing OpenShift Dedicated or Red Hat OpenShift Service on AWS (ROSA classic) cluster with an identity provider configured.

Procedure

Under Cluster administrative users click the Add user button.

The Add cluster user popover appears.

Select an appropriate Group for the user.

Important

If this user needs to use existing groups in an identity provider to control OpenShift AI access, select`cluster-admins`.

For more information about these user types, see [Managing administration roles and users](https://docs.redhat.com/en/documentation/openshift_dedicated/4/html/authentication_and_authorization/osd-admin-roles) in the OpenShift Dedicated documentation or [Default cluster roles](https://docs.redhat.com/en/documentation/red_hat_openshift_service_on_aws/4/html/authentication_and_authorization/using-rbac#default-roles_using-rbac) in the ROSA documentation.

1. Log in to OpenShift Cluster Manager ([https://console.redhat.com/openshift/](https://console.redhat.com/openshift/)).
2. Click Cluster List. The Cluster List page opens.
3. Click the name of the cluster to configure.
4. Click the Access control tab.
5. Click Cluster Roles and Access.
6. Enter the user name in the User ID field.
7. Click Add user.

Verification

- The user name and selected group are visible in the list of Cluster administrative users.

### 2.4. Subscribing to the Red Hat OpenShift AI Cloud ServiceCopy link

You can subscribe to the Red Hat OpenShift AI managed cloud service in the following ways:

- Subscribe through Red Hat if you have a Red Hat OpenShift Dedicated cluster deployed with a Customer Cloud Subscription (CCS) on Amazon Web Services (AWS) or Google Cloud Platform (GCP).
- Subscribe through the AWS Marketplace if you have a Red Hat OpenShift Service on AWS (ROSA classic) cluster.

Note

You can also purchase Red Hat OpenShift AI as self-managed software. To purchase a new subscription, contact your Red Hat account manager. If you do not yet have an account manager, complete the form at [https://www.redhat.com/en/contact](https://www.redhat.com/en/contact/) to request one.

#### 2.4.1. Subscribing to the OpenShift AI managed cloud service on AWS or GCPCopy link

For a Red Hat OpenShift Dedicated cluster that is deployed on AWS or GCP, contact your Red Hat account manager to purchase a new subscription. If you do not yet have an account manager, complete the form at [https://www.redhat.com/en/technologies/cloud-computing/openshift/dedicated#contact-form](https://www.redhat.com/en/technologies/cloud-computing/openshift/dedicated#contact-form) to request one.

Prerequisite

- You have worked with Red Hat Sales to enable a private offer of OpenShift AI, follow these steps to accept your offer and deploy the solution.

Procedure

1. Visit your Private Offer with the URL link provided by your Red Hat Sales representative.
2. Click Accept Terms to subscribe to the AMI Private Offer named OpenShift AI from AWS Marketplace.
3. After accepting the offer terms, click Continue to Configuration.

#### 2.4.2. Subscribing to the OpenShift AI managed cloud service on Red Hat OpenShift Service on AWS (ROSA)Copy link

For a ROSA classic cluster, you can subscribe to the OpenShift AI managed cloud service through the Amazon Web Services (AWS) Marketplace.

Prerequisites

- Access to a ROSA classic cluster, including permissions to view and install add-ons.
- An AWS account with permission to view and subscribe to offerings in the AWS marketplace.

Procedure

In the AWS Console, navigate to the AWS Marketplace. For example:

1. Click the help icon and then select Getting Started Resource Center.
2. Select AWS Marketplace > Browse AWS Marketplace.

Select one of the two options depending on the geographical location of the billing address for your AWS account (note that this location might differ from the geographical location of the cluster):

- Europe, the Middle East, and Africa (EMEA region)
- North America and regions outside EMEA

Link your AWS account with your Red Hat account to complete your registration:

On the top banner, click Set up account.

This link takes you to the Red Hat Hybrid Cloud Console.

If you are not already logged in, log in.

Note

You must use your Red Hat login to log in to your Red Hat account. Each Red Hat account has a user email address and a Red Hat login associated with it. The email address and the Red Hat login can be the same. However, if the email address and the Red Hat login are different, you must use the Red Hat login to log in to your Red Hat account; you cannot use your email address to log in.

For more information, see [Finding your Red Hat login](https://docs.redhat.com/en/documentation/red_hat_hybrid_cloud_console/1-latest/html-single/getting_started_with_the_red_hat_hybrid_cloud_console/index#finding-login_getting-started).

1. In the AWS Marketplace console, navigate to the Manage Subscriptions page.
2. On the Red Hat OpenShift AI tile, click Set up product.
3. Review and then accept the terms and agreements.
4. Click Connect accounts.

Verification

The Data Science product page opens.

### 2.5. Installing OpenShift AI on your OpenShift clusterCopy link

You can use Red Hat OpenShift Cluster Manager to install Red Hat OpenShift AI as an Add-on to your Red Hat OpenShift cluster.

Prerequisites

- A subscription to the Red Hat OpenShift AI Add-on, as described in [Subscribing to the OpenShift AI managed cloud service on AWS or GCP](https://docs.redhat.com/en/documentation/red_hat_openshift_ai_cloud_service/1/html/installing_and_uninstalling_openshift_ai_cloud_service/installing-and-deploying-openshift-ai_install#subscribing-to-openshift-ai-add-on_ODS_install).
- If you purchased the Red Hat OpenShift AI Add-on for ROSA classic by using the AWS Marketplace, you have associated your AWS account with your Red Hat account as described in [Subscribing to the OpenShift AI managed cloud service on Red Hat OpenShift Service on AWS (ROSA)](https://docs.redhat.com/en/documentation/red_hat_openshift_ai_cloud_service/1/html/installing_and_uninstalling_openshift_ai_cloud_service/installing-and-deploying-openshift-ai_install#subscribing-to-openshift-ai-add-on_AWS_install).
- Credentials for Red Hat OpenShift Cluster Manager ([https://console.redhat.com/openshift/](https://console.redhat.com/openshift/)).
- Administrator access to the OpenShift cluster.
- To support KServe components, you installed the dependent Operators, including the Red Hat OpenShift Serverless and Red Hat OpenShift Service Mesh Operators. For more information, see [Configuring your model-serving platform](https://docs.redhat.com/en/documentation/red_hat_openshift_ai_cloud_service/1/html/configuring_your_model-serving_platform).

Note

For information about the lifecycle associated with Red Hat OpenShift AI, see the [Red Hat OpenShift AI Life Cycle](https://access.redhat.com/support/policy/updates/rhoai-cs/lifecycle) Knowledgebase article.

Procedure

Click Cluster List.

The Cluster List page opens.

Click the name of the cluster you want to install OpenShift AI on.

The Details page for the cluster opens.

Click the Add-ons tab and locate the Red Hat OpenShift AI tile.

Note

If there is a Prerequisites not met warning message, click the Prerequisites tab. Note down the error message. If the error message states that you require a new machine pool, or that more resources are required, take the appropriate action to resolve the problem. You might need to add more resources to your cluster, or increase the size of your default machine pool. To increase your cluster’s resources, contact your infrastructure administrator. For more information about increasing the size of your machine pool, see [Allocating additional resources to OpenShift AI users](https://docs.redhat.com/en/documentation/red_hat_openshift_ai_cloud_service/1/html/managing_openshift_ai/allocating-additional-resources-to-data-science-users_managing-rhoai).

Select a Subscription type:

If you obtained your Red Hat OpenShift AI subscription through your Red Hat account manager, select Standard and then skip to Step 7.

If you obtained your Red Hat OpenShift AI subscription directly from the AWS Marketplace, select Marketplace and then continue to Step 6.

For a Marketplace subscription, select your AWS account number from the list.

Note

If your AWS account number is not in the list, you might need to link your Red Hat and AWS accounts, as described in [Subscribing to the OpenShift AI managed cloud service on Red Hat OpenShift Service on AWS (ROSA)](https://docs.redhat.com/en/documentation/red_hat_openshift_ai_cloud_service/1/html/installing_and_uninstalling_openshift_ai_cloud_service/installing-and-deploying-openshift-ai_install#subscribing-to-openshift-ai-add-on_AWS_install).

1. Log in to OpenShift Cluster Manager ([https://console.redhat.com/openshift/](https://console.redhat.com/openshift/)).
2. Click Install. The Configure Red Hat OpenShift AI pane appears.
3. In the Notification email field, enter any email addresses that you want to receive important alerts about the state of Red Hat OpenShift AI, such as outage alerts.
4. Click Install.

Verification

In Red Hat OpenShift Cluster Manager, on the Add-ons tab for the cluster, confirm that the OpenShift AI tile shows one of the following states:

- `Installing`- installation is in progress; wait for this to change to`Installed`. This takes around 30 minutes.
- `Installed`- installation is complete; verify that the View in console button is visible.

In OpenShift AI, click Home → Projects and confirm that the following project namespaces are visible and listed as Active:

- `redhat-ods-applications`
- `redhat-ods-monitoring`
- `redhat-ods-operator`
- `rhods-notebooks`

### 2.6. Installing and managing Red Hat OpenShift AI componentsCopy link

You can use the OpenShift web console to install and manage components of Red Hat OpenShift AI on your OpenShift cluster.

#### 2.6.1. Installing Red Hat OpenShift AI components by using the web consoleCopy link

When you install Red Hat OpenShift AI as an add-on to your OpenShift cluster, the install process automatically creates a default`DataScienceCluster` object. To install Red Hat OpenShift AI components by using the OpenShift web console, you must configure the`DataScienceCluster` object.

Important

The following procedure describes how to configure the`DataScienceCluster` object to install Red Hat OpenShift AI components as part of a new installation.

- For information about changing the installation status of OpenShift AI components after installation, see [Updating the installation status of Red Hat OpenShift AI components by using the web console](https://docs.redhat.com/en/documentation/red_hat_openshift_ai_cloud_service/1/html/installing_and_uninstalling_openshift_ai_cloud_service/installing-and-deploying-openshift-ai_install#updating-installation-status-of-openshift-ai-components-using-web-console_component-install).
- For information about upgrading OpenShift AI, see [Upgrading OpenShift AI Cloud Service](https://docs.redhat.com/en/documentation/red_hat_openshift_ai_cloud_service/1/html/upgrading_openshift_ai_cloud_service/index).

Prerequisites

- Red Hat OpenShift AI is installed as an add-on to your Red Hat OpenShift cluster.
- You have cluster administrator privileges for your OpenShift cluster.

Procedure

Select the YAML tab.

An embedded YAML editor opens showing a default custom resource (CR) for the`DataScienceCluster` object, similar to the following example:

```yaml
apiVersion: datasciencecluster.opendatahub.io/v1
kind: DataScienceCluster
metadata:
  name: default-dsc
spec:
  components:
    codeflare:
      managementState: Removed
    dashboard:
      managementState: Removed
    datasciencepipelines:
      argoWorkflowsControllers:
        managementState: Removed 
```

1

```yaml

      managementState: Removed
    feastoperator:
      managementState: Removed
    kserve:
      managementState: Removed 
```

2

3

```yaml

    kueue:
      defaultClusterQueueName: default
      defaultLocalQueueName: default
      managementState: Removed
    llamastackoperator:
      managementState: Removed
    modelmeshserving:
      managementState: Removed
    modelregistry:
      managementState: Removed
    ray:
      managementState: Removed
    trainingoperator:
      managementState: Removed
    trustyai:
      managementState: Removed
    workbenches:
      managementState: Removed
```

1

To use your own Argo Workflows instance with the`datasciencepipelines` component, set`argoWorkflowsControllers.managementState` to`Removed`. This allows you to integrate with a managed Argo Workflows installation already on your OpenShift cluster and avoid conflicts with the embedded controller. See Configuring pipelines with your own Argo Workflows instance. <2>To fully install the KServe component, which is used by the single-model serving platform to serve large models, you must install Operators for Red Hat OpenShift Service Mesh and Red Hat OpenShift Serverless and perform additional configuration. See [Installing the single-model serving platform](https://docs.redhat.com/en/documentation/red_hat_openshift_ai_cloud_service/1/html/installing_and_uninstalling_openshift_ai_cloud_service/installing-the-single-model-serving-platform_component-install).

2

If you have not enabled the KServe component (that is, you set the value of the`managementState` field to`Removed`), you must also disable the dependent Service Mesh component to avoid errors. See [Disabling KServe dependencies](https://docs.redhat.com/en/documentation/red_hat_openshift_ai_cloud_service/1/html/installing_and_uninstalling_openshift_ai_cloud_service/installing-the-single-model-serving-platform_component-install#disabling-kserve-dependencies_component-install).

In the`spec.components` section of the CR, for each OpenShift AI component shown, set the value of the`managementState` field to either`Managed` or`Removed`. These values are defined as follows:

Managed The Operator actively manages the component, installs it, and tries to keep it active. The Operator will upgrade the component only if it is safe to do so. Removed The Operator actively manages the component but does not install it. If the component is already installed, the Operator will try to remove it.

Important

- To learn how to fully install the KServe component, which is used by the single-model serving platform to serve large models, see [Installing the single-model serving platform](https://docs.redhat.com/en/documentation/red_hat_openshift_ai_cloud_service/1/html/installing_and_uninstalling_openshift_ai_cloud_service/installing-the-single-model-serving-platform_component-install).
- If you have not enabled the KServe component (that is, you set the value of the`managementState` field to`Removed`), you must also disable the dependent Service Mesh component to avoid errors. See [Disabling KServe dependencies](https://docs.redhat.com/en/documentation/red_hat_openshift_ai_cloud_service/1/html/installing_and_uninstalling_openshift_ai_cloud_service/installing-the-single-model-serving-platform_component-install#disabling-kserve-dependencies_component-install).
- To learn how to install the distributed workloads components, see [Installing the distributed workloads components](https://docs.redhat.com/en/documentation/red_hat_openshift_ai_cloud_service/1/html/installing_and_uninstalling_openshift_ai_cloud_service/installing-the-distributed-workloads-components_install).

Verification

Confirm the status of all installed components:

For the`DataScienceCluster` object called`default-dsc`, verify that the status is`Phase: Ready`.

Note

When you edit the`spec.components` section to change the installation status of a component, the`default-dsc` status also changes. During the initial installation, it might take a few minutes for the status phase to change from`Progressing` to`Ready`. You can access the OpenShift AI dashboard before the`default-dsc` status phase is`Ready`, but all components might not be ready.

In the`status.installedComponents` section, confirm that the components you installed have a status value of`true`.

Note

If a component shows with the`component-name: {}` format in the`spec.components` section of the CR, the component is not installed.

1. In the OpenShift web console, click Operators → Installed Operators.
2. Click the Red Hat OpenShift AI Operator.
3. Click the Data Science Cluster tab.
4. Click the`default-dsc` link to display the data science cluster details.
5. Select the YAML tab.

Confirm that there is at least one running pod for each component:

1. In the OpenShift web console, click Workloads → Pods.
2. In the Project list at the top of the page, select`redhat-ods-applications`.
3. In the applications namespace, confirm that there are one or more running pods for each of the OpenShift AI components that you installed.

#### 2.6.2. Updating the installation status of Red Hat OpenShift AI components by using the web consoleCopy link

You can use the OpenShift web console to update the installation status of components of Red Hat OpenShift AI on your OpenShift cluster.

Important

If you upgraded OpenShift AI, the upgrade process automatically used the values of the previous version’s`DataScienceCluster` object. New components are not automatically added to the`DataScienceCluster` object.

After upgrading OpenShift AI:

- In the OpenShift AI dashboard, users can view the list of the installed OpenShift AI components, their corresponding source (upstream) components, and the versions of the installed components, as described in [Viewing installed OpenShift AI components](https://docs.redhat.com/en/documentation/red_hat_openshift_ai_cloud_service/1/html/installing_and_uninstalling_openshift_ai_cloud_service/installing-and-deploying-openshift-ai_install#viewing-installed-components_component-install).
- Inspect the default`DataScienceCluster` object to check and optionally update the`managementState` status of the existing components.
- Add any new components to the`DataScienceCluster` object.

Prerequisites

- Red Hat OpenShift AI is installed as an Add-on to your Red Hat OpenShift cluster.
- You have cluster administrator privileges for your OpenShift cluster.

Procedure

Click the YAML tab.

An embedded YAML editor opens showing the default custom resource (CR) for the`DataScienceCluster` object, similar to the following example:

```yaml
apiVersion: datasciencecluster.opendatahub.io/v1
kind: DataScienceCluster
metadata:
  name: default-dsc
spec:
  components:
    codeflare:
      managementState: Removed
    dashboard:
      managementState: Removed
    datasciencepipelines:
      managementState: Removed
    kserve:
      managementState: Removed
    kueue:
      managementState: Removed
    llamastackoperator:
      managementState: Removed
    modelmeshserving:
      managementState: Removed
    ray:
      managementState: Removed
    trainingoperator:
      managementState: Removed
    trustyai:
      managementState: Removed
    workbenches:
      managementState: Removed
      workbenchNamespace: rhods-notebooks
```

In the`spec.components` section of the CR, for each OpenShift AI component shown, set the value of the`managementState` field to either`Managed` or`Removed`. These values are defined as follows:

Managed The Operator actively manages the component, installs it, and tries to keep it active. The Operator will upgrade the component only if it is safe to do so. Removed The Operator actively manages the component but does not install it. If the component is already installed, the Operator will try to remove it.

Important

- To learn how to install the KServe component, which is used by the single-model serving platform to serve large models, see [Installing the single-model serving platform](https://docs.redhat.com/en/documentation/red_hat_openshift_ai_cloud_service/1/html/installing_and_uninstalling_openshift_ai_cloud_service/installing-the-single-model-serving-platform_component-install).
- If you have not enabled the KServe component (that is, you set the value of the`managementState` field to`Removed`), you must also disable the dependent Service Mesh component to avoid errors. See [Disabling KServe dependencies](https://docs.redhat.com/en/documentation/red_hat_openshift_ai_cloud_service/1/html/installing_and_uninstalling_openshift_ai_cloud_service/installing-the-single-model-serving-platform_component-install#disabling-kserve-dependencies_component-install).
- To learn how to install the distributed workloads feature, see [Installing the distributed workloads components](https://docs.redhat.com/en/documentation/red_hat_openshift_ai_cloud_service/1/html/installing_and_uninstalling_openshift_ai_cloud_service/installing-the-distributed-workloads-components_install).

Click Save.

For any components that you updated, OpenShift AI initiates a rollout that affects all pods to use the updated image.

If you are upgrading from OpenShift AI 2.19 or earlier, upgrade the Authorino Operator to the`stable` update channel, version 1.2.1 or later.

Switch to the`stable` channel:

1. Navigate to the Subscription settings of the Authorino Operator.
2. Under Update channel, click on the highlighted tech-preview-v1.
3. Change the channel to`stable`.

Verification

Confirm that there is at least one running pod for each component:

1. In the OpenShift web console, click Workloads → Pods.
2. In the Project list at the top of the page, select`redhat-ods-applications` or your custom applications namespace.
3. In the applications namespace, confirm that there are one or more running pods for each of the OpenShift AI components that you installed.

Confirm the status of all installed components:

In the`status.installedComponents` section, confirm that the components you installed have a status value of`true`.

Note

If a component shows with the`component-name: {}` format in the`spec.components` section of the CR, the component is not installed.

1. In the OpenShift web console, click Operators → Installed Operators.
2. Click the Red Hat OpenShift AI Operator.
3. Click the Data Science Cluster tab and select the`DataScienceCluster` object called`default-dsc`.
4. Select the YAML tab.

#### 2.6.3. Viewing installed OpenShift AI componentsCopy link

In the Red Hat OpenShift AI dashboard, you can view a list of the installed OpenShift AI components, their corresponding source (upstream) components, and the versions of the installed components.

Prerequisites

- In the OpenShift AI dashboard, users can view the list of the installed OpenShift AI components, their corresponding source (upstream) components, and the versions of the installed components, as described in [Viewing installed OpenShift AI components](https://docs.redhat.com/en/documentation/red_hat_openshift_ai_cloud_service/1/html/installing_and_uninstalling_openshift_ai_cloud_service/installing-and-deploying-openshift-ai_install#viewing-installed-components_component-install).
- OpenShift AI is installed in your OpenShift cluster.

Procedure

1. Log in to the OpenShift AI dashboard.
2. In the top navigation bar, click the help icon () and then select About.

Verification

The About page shows a list of the installed OpenShift AI components along with their corresponding upstream components and upstream component versions.

Additional resources

- [Installing and managing Red Hat OpenShift AI components](https://docs.redhat.com/en/documentation/red_hat_openshift_ai_cloud_service/1/html/installing_and_uninstalling_openshift_ai_cloud_service/installing-and-deploying-openshift-ai_install#installing-and-managing-openshift-ai-components_component-install).

## Chapter 3. Configuring pipelines with your own Argo Workflows instanceCopy link

You can configure OpenShift AI to use an existing Argo Workflows instance instead of the embedded one included with Data Science Pipelines. This configuration is useful if your OpenShift cluster already includes a managed Argo Workflows instance and you want to integrate it with OpenShift AI pipelines without conflicts. Disabling the embedded Argo Workflows controller allows cluster administrators to manage the lifecycles of OpenShift AI and Argo Workflows independently.

Note

You cannot enable both the embedded Argo Workflows instance and your own Argo Workflows instance on the same cluster.

Prerequisites

- You have cluster administrator privileges for your OpenShift cluster.
- You have installed Red Hat OpenShift AI.

Procedure

Disable the embedded Argo Workflows controllers that are managed by the OpenShift AI Operator:

In the`spec.components.datasciencepipelines` section, set the value of the`managementState` field for`argoWorkflowsControllers` to`Removed`, as shown in the following example:

Example datasciencepipelines specification

```yaml
# ...
spec:
  components:
    datasciencepipelines:
      argoWorkflowsControllers:
        managementState: Removed
      managementState: Managed
# ...
```

1. In the`spec.components` section, set the value of the`managementState` field for the`datasciencepipelines` component to`Managed`.

Verification

Verify that the`ds-pipeline-workflow-controller` pod does not exist:

1. Go to Workloads → Pods.
2. Search for the`ds-pipeline-workflow-controller` pod.
3. Verify that this pod does not exist. The absence of this pod confirms that the embedded Argo Workflows controller is disabled.

## Chapter 4. Installing the distributed workloads componentsCopy link

To use the distributed workloads feature in OpenShift AI, you must install several components.

Prerequisites

If you want to use graphics processing units (GPUs), you have enabled GPU support in OpenShift AI. If you use NVIDIA GPUs, see [Enabling NVIDIA GPUs](https://docs.redhat.com/en/documentation/red_hat_openshift_ai_cloud_service/1/html/managing_openshift_ai/enabling-accelerators#enabling-nvidia-gpus_managing-rhoai). If you use AMD GPUs, see [AMD GPU integration](https://docs.redhat.com/en/documentation/red_hat_openshift_ai_cloud_service/1/html/managing_openshift_ai/enabling-accelerators#amd-gpu-integration_managing-rhoai).

Note

In OpenShift AI, Red Hat supports the use of accelerators within the same cluster only.

Red Hat supports remote direct memory access (RDMA) for NVIDIA GPUs only, enabling them to communicate directly with each other by using NVIDIA GPUDirect RDMA across either Ethernet or InfiniBand networks.

If you want to use self-signed certificates, you have added them to a central Certificate Authority (CA) bundle as described in [Working with certificates](https://docs.redhat.com/en/documentation/red_hat_openshift_ai_cloud_service/1/html/installing_and_uninstalling_openshift_ai_cloud_service/working-with-certificates_certs). No additional configuration is necessary to use those certificates with distributed workloads. The centrally configured self-signed certificates are automatically available in the workload pods at the following mount points:

Cluster-wide CA bundle:

```bash
/etc/pki/tls/certs/odh-trusted-ca-bundle.crt
/etc/ssl/certs/odh-trusted-ca-bundle.crt
```

Custom CA bundle:

```bash
/etc/pki/tls/certs/odh-ca-bundle.crt
/etc/ssl/certs/odh-ca-bundle.crt
```

Procedure

Enable the required distributed workloads components. In the`spec.components` section, set the`managementState` field correctly for the required components:

- Set`kueue` to`Unmanaged` to allow the Red Hat build of Kueue Operator to manage Kueue.
- If you want to use the CodeFlare framework to tune models, set`codeflare` and`ray` to`Managed`.
- If you want to use the Kubeflow Training Operator to tune models, set`trainingoperator` to`Managed`.
- The list of required components depends on whether the distributed workload is run from a pipeline or workbench or both, as shown in the following table.

Expand

`codeflare`

`Managed`

`Managed`

`Managed`

`dashboard`

`Managed`

`Managed`

`Managed`

`datasciencepipelines`

`Managed`

`Removed`

`Managed`

`kueue`

`Unmanaged`

`Unmanaged`

`Unmanaged`

`ray`

`Managed`

`Managed`

`Managed`

`trainingoperator`

`Managed`

`Managed`

`Managed`

`workbenches`

`Removed`

`Managed`

`Managed`

| Table 4.1. Components required for distributed workloads | Component | Pipelines only | Workbenches only | Pipelines and workbenches |
| --- | --- | --- | --- | --- |

Show more

Verification

Check the status of the codeflare-operator-manager, kubeflow-training-operator, kuberay-operator, kueue-controller-manager, and openshift-kueue-operator pods, as follows:

In the Search by name field, enter the following search strings:

- In the redhat-ods-applications project, search for codeflare-operator-manager, kubeflow-training-operator, and kuberay-operator.
- In the openshift-kueue-operator project, search for kueue-controller-manager and openshift-kueue-operator.

In each case, check the status as follows:

Check the pod status.

When the status of the pods is Running, the pods are ready to use.

1. Click the deployment name to open the deployment details page.
2. Click the Pods tab.
3. To see more information about each pod, click the pod name to open the pod details page, and then click the Logs tab.

Next Step

Configure the distributed workloads feature as described in [Managing distributed workloads](https://docs.redhat.com/en/documentation/red_hat_openshift_ai_cloud_service/1/html/managing_openshift_ai/managing-distributed-workloads_managing-rhoai).

## Chapter 5. Installing the single-model serving platformCopy link

### 5.1. About the single-model serving platformCopy link

For deploying large models such as large language models (LLMs), OpenShift AI includes a single-model serving platform that is based on the [KServe](https://github.com/kserve/kserve) component. To install the single-model serving platform, the following components are required:

[Red Hat OpenShift Service Mesh](https://docs.redhat.com/en/documentation/red_hat_openshift_service_on_aws/4/html/service_mesh/service-mesh-2-x#ossm-architecture): A service mesh networking layer that manages traffic flows and enforces access policies. OpenShift Service Mesh is based on the open source [Istio](https://istio.io/) project.

Note

Currently, only OpenShift Service Mesh v2 is supported. For more information, see [Supported Configurations](https://access.redhat.com/articles/rhoai-supported-configs).

- [KServe](https://github.com/opendatahub-io/kserve): A Kubernetes custom resource definition (CRD) that orchestrates model serving for all types of models. KServe includes model-serving runtimes that implement the loading of given types of model servers. KServe also handles the lifecycle of the deployment object, storage access, and networking setup.
- [Red Hat OpenShift Serverless](https://docs.redhat.com/en/documentation/openshift_container_platform/4.19/html/serverless/about-serverless): A cloud-native development model that allows for serverless deployments of models. OpenShift Serverless is based on the open source [Knative](https://knative.dev/docs/) project.

You can install the single-model serving platform manually or in an automated fashion:

Automated installation If you have not already created a`ServiceMeshControlPlane` or`KNativeServing` resource on your OpenShift cluster, you can configure the Red Hat OpenShift AI Operator to install KServe and configure its dependencies. For more information, see [Configuring automated installation of KServe](https://docs.redhat.com/en/documentation/red_hat_openshift_ai_cloud_service/1/html/installing_and_uninstalling_openshift_ai_cloud_service/installing-the-single-model-serving-platform_component-install#configuring-automated-installation-of-kserve_component-install). Manual installation

If you have already created a`ServiceMeshControlPlane` or`KNativeServing` resource on your OpenShift cluster, you cannot configure the Red Hat OpenShift AI Operator to install KServe and configure its dependencies. In this situation, you must install KServe manually. For more information, see [Manually installing KServe](https://docs.redhat.com/en/documentation/red_hat_openshift_ai_cloud_service/1/html/installing_and_uninstalling_openshift_ai_cloud_service/installing-the-single-model-serving-platform_component-install#manually-installing-kserve_component-install).

Note

You can run KServe in`Unmanaged` mode during manual installations of the single-model serving platform. This mode is useful when you need more control over KServe components, such as modifying resource limits for the KServe controller.

### 5.2. Configuring automated installation of KServeCopy link

If you have not already created a`ServiceMeshControlPlane` or`KNativeServing` resource on your OpenShift cluster, you can configure the Red Hat OpenShift AI Operator to install KServe and configure its dependencies.

You can configure KServe in advanced or standard deployment mode. For more information, see [About KServe deployment modes](https://docs.redhat.com/en/documentation/red_hat_openshift_ai_cloud_service/1/html/deploying_models/deploying_models_on_the_single_model_serving_platform#about-kserve-deployment-modes_rhoai-user). If you configure KServe for advanced deployment mode, you can set up your data science project to serve models in both advanced and standard deployment mode. However, if you configure KServe for only standard deployment mode, you can only use standard deployment mode.

Important

If you have created a`ServiceMeshControlPlane` or`KNativeServing` resource on your cluster, the Red Hat OpenShift AI Operator cannot install KServe and configure its dependencies and the installation does not proceed. In this situation, you must follow the manual installation instructions to install KServe.

Prerequisites

You have installed the OpenShift CLI (`oc`) as described in the appropriate documentation for your cluster:

- [Installing the OpenShift CLI](https://docs.redhat.com/en/documentation/openshift_dedicated/4/html/cli_tools/openshift-cli-oc#installing-openshift-cli) for OpenShift Dedicated
- [Installing the OpenShift CLI](https://docs.redhat.com/en/documentation/red_hat_openshift_service_on_aws_classic_architecture/4/html/cli_tools/openshift-cli-oc#installing-openshift-cli) for Red Hat OpenShift Service on AWS (classic architecture)

(Knative Serverless mode): You have [installed](https://docs.redhat.com/en/documentation/red_hat_openshift_service_on_aws/4/html/service_mesh/service-mesh-2-x#installing-ossm) the Red Hat OpenShift Service Mesh Operator and dependent Operators.

Note

To enable automated installation of KServe, install only the required Operators for Red Hat OpenShift Service Mesh. Do not perform any additional configuration or create a`ServiceMeshControlPlane` resource.

(Knative Serverless mode): You have [installed](https://docs.redhat.com/en/documentation/red_hat_openshift_serverless/1.36/html/installing_openshift_serverless/index) the Red Hat OpenShift Serverless Operator.

Note

To enable automated installation of KServe, install only the Red Hat OpenShift Serverless Operator. Do not perform any additional configuration or create a`KNativeServing` resource.

Procedure

Install OpenShift Service Mesh as follows:

(Knative Serverless mode): In the`spec` section, validate that the value of the`managementState` field for the`serviceMesh` component is set to`Managed`, as shown:

```plaintext
spec:
 applicationsNamespace: redhat-ods-applications
 serviceMesh:
   controlPlane:
     metricsCollection: Istio
     name: data-science-smcp
     namespace: istio-system
   managementState: Managed
```

(KServe RawDeployment mode): In the`spec` section, validate that the value of the`managementState` field for the`serviceMesh` component is set to`Removed`, as shown:

```plaintext
spec:
 applicationsNamespace: redhat-ods-applications
 serviceMesh:
   controlPlane:
     metricsCollection: Istio
     name: data-science-smcp
     namespace: istio-system
   managementState: Removed
```

Note

Do not change the`istio-system` namespace that is specified for the`serviceMesh` component by default. Other namespace values are not supported.

Click Save.

Based on the configuration you added to the`DSCInitialization` object, the Red Hat OpenShift AI Operator installs OpenShift Service Mesh.

1. Click the DSC Initialization tab.
2. Click the default-dsci object.
3. Click the YAML tab.

(Red Hat OpenShift Service on AWS only): If your OpenShift cluster is running on Red Hat OpenShift Service on AWS (ROSA classic), there is an additional setting required to make the service mesh control plane configuration work. To add this setting, edit the`data-science-smcp` service mesh control plane object as follows:

In the`spec.security.identity` section, add a field called`type`, and set the value to`ThirdParty`, as shown.

```plaintext
 security:
    dataPlane:
      mtls: true
    identity:
      type: ThirdParty
```

1. In the web console, click Operators → Installed Operators and then click the Red Hat OpenShift Service Mesh Operator.
2. Click the Istio Service Mesh Control Plane tab.
3. Click the data-science-smcp object.
4. Click the YAML tab.
5. Click Save.

(KServe RawDeployment mode only): Install KServe as follows:

In the`spec.components` section, configure the`kserve` component as shown.

```plaintext
kserve:
    defaultDeploymentMode: RawDeployment
    rawDeploymentServiceConfig: Headed 
```

1

```plaintext

    managementState: Managed
    serving:
      managementState: Removed 
```

2

```plaintext

      name: knative-serving
```

Click Save.

The preceding configuration installs KServe in the KServe RawDeployment mode. In this configuration, observe the following details:

1

The configuration shown uses the`Headed` mode to set the cluster to perform normal load balancing over workload replicas. For environments in which inference request load balancing is performed on the client side, set`rawDeploymentServiceConfig` to the`Headless` mode.

2

The`managementState` is set to`Removed`.

1. In the web console, click Operators → Installed Operators and then click the Red Hat OpenShift AI Operator.
2. Click the Data Science Cluster tab.
3. Click the default-dsc DSC object.
4. Click the YAML tab.

(Knative Serverless mode): Install both KServe and OpenShift Serverless as follows:

In the`spec.components` section, configure the`kserve` component as shown.

```plaintext
spec:
 components:
   kserve:
     managementState: Managed
     defaultDeploymentMode: Serverless 
```

1

```plaintext

     rawDeploymentServiceConfig: Headed 
```

2

```plaintext

     serving:
       ingressGateway:
         certificate:
           secretName: knative-serving-cert 
```

3

```plaintext

           type: OpenshiftDefaultIngress 
```

4

```plaintext

       managementState: Managed
       name: knative-serving
```

Click Save.

The preceding configuration creates an ingress gateway for OpenShift Serverless to receive traffic from OpenShift Service Mesh. In this configuration, both KServe RawDeployment and Knative Serverless deployment modes can be used.

1

The configuration shown uses the default deployment mode that is selected after configuring KServe. You can set the default value when you create and deploy a model using KServe. To use KServe RawDeployment mode as the default, set`defaultDeploymentMode` to`RawDeployment`. To use Knative Serverless mode as the default, set`defaultDeploymentMode` to`Serverless`.

2

The configuration shown uses the`Headed` mode to set the cluster to perform normal load balancing over workload replicas. For environments in which inference request load balancing is performed on the client side, set`rawDeploymentServiceConfig` to the`Headless` mode.

3

The configuration shown uses the default ingress certificate configured for OpenShift to secure incoming traffic to your OpenShift cluster and stores the certificate in the`knative-serving-cert` secret that is specified in the`secretName` field. The`secretName` field can only be set at the time of installation. The default value of the`secretName` field is`knative-serving-cert`. Subsequent changes to the certificate secret must be made manually. If you did not use the default`secretName` value during installation, create a new secret named`knative-serving-cert` in the`istio-system` namespace, and then restart the`istiod-datascience-smcp- ` pod.

4

You can specify the following certificate types by updating the value of the`type` field:

`OpenshiftDefaultIngress`

To use a self-signed certificate or to provide your own, update the value of the`secretName` field to specify your secret name and change the value of the`type` field to`SelfSigned` or`Provided`.

Note

If you provide your own certificate, the certificate must specify the domain name used by the ingress controller of your OpenShift cluster. You can check this value by running the following command:

`$ oc get ingresses.config.openshift.io cluster -o jsonpath='{.spec.domain}'`

- `Provided`
- `SelfSigned`
- You must set the value of the`managementState` field to`Managed` for both the`kserve` and`serving` components. Setting`kserve.managementState` to`Managed` triggers automated installation of KServe. Setting`serving.managementState` to`Managed` triggers automated installation of OpenShift Serverless. However, installation of OpenShift Serverless will not be triggered if`kserve.managementState` is not also set to`Managed`.

Verification

Verify installation of KServe as follows:

Confirm that the project includes a running pod for the KServe controller manager, similar to the following example:

```plaintext
NAME                                          READY   STATUS    RESTARTS   AGE
kserve-controller-manager-7fbb7bccd4-t4c5g    1/1     Running   0          22h
odh-model-controller-6c4759cc9b-cftmk         1/1     Running   0          129m
odh-model-controller-6c4759cc9b-ngj8b         1/1     Running   0          129m
odh-model-controller-6c4759cc9b-vnhq5         1/1     Running   0          129m
```

- In the web console, click Workloads → Pods.
- From the project list, select redhat-ods-applications.This is the project in which OpenShift AI components are installed, including KServe.

(Knative Serverless mode only): Verify installation of OpenShift Service Mesh as follows:

Confirm that there are running pods for the service mesh control plane, ingress gateway, and egress gateway. These pods have the naming patterns shown in the following example:

```plaintext
NAME                                      		  READY     STATUS    RESTARTS   AGE
istio-egressgateway-7c46668687-fzsqj      	 	  1/1       Running   0          22h
istio-ingressgateway-77f94d8f85-fhsp9      		  1/1       Running   0          22h
istiod-data-science-smcp-cc8cfd9b8-2rkg4  		  1/1       Running   0          22h
```

- In the web console, click Workloads → Pods.
- From the project list, select istio-system. This is the project in which OpenShift Service Mesh is installed.

(Knative Serverless mode only): Verify installation of OpenShift Serverless as follows:

Confirm that there are numerous running pods in the`knative-serving` project, including activator, autoscaler, controller, and domain mapping pods, as well as pods for the Knative Istio controller (which controls the integration of OpenShift Serverless and OpenShift Service Mesh). An example is shown.

```plaintext
NAME                                     	READY     STATUS    RESTARTS  AGE
activator-7586f6f744-nvdlb               	2/2       Running   0         22h
activator-7586f6f744-sd77w               	2/2       Running   0         22h
autoscaler-764fdf5d45-p2v98             	2/2       Running   0         22h
autoscaler-764fdf5d45-x7dc6              	2/2       Running   0         22h
autoscaler-hpa-7c7c4cd96d-2lkzg          	1/1       Running   0         22h
autoscaler-hpa-7c7c4cd96d-gks9j         	1/1       Running   0         22h
controller-5fdfc9567c-6cj9d              	1/1       Running   0         22h
controller-5fdfc9567c-bf5x7              	1/1       Running   0         22h
domain-mapping-56ccd85968-2hjvp          	1/1       Running   0         22h
domain-mapping-56ccd85968-lg6mw          	1/1       Running   0         22h
domainmapping-webhook-769b88695c-gp2hk   	1/1       Running   0         22h
domainmapping-webhook-769b88695c-npn8g   	1/1       Running   0         22h
net-istio-controller-7dfc6f668c-jb4xk    	1/1       Running   0         22h
net-istio-controller-7dfc6f668c-jxs5p    	1/1       Running   0         22h
net-istio-webhook-66d8f75d6f-bgd5r       	1/1       Running   0         22h
net-istio-webhook-66d8f75d6f-hld75      	1/1       Running   0         22h
webhook-7d49878bc4-8xjbr                 	1/1       Running   0         22h
webhook-7d49878bc4-s4xx4                 	1/1       Running   0         22h
```

- In the web console, click Workloads → Pods.
- From the project list, select knative-serving. This is the project in which OpenShift Serverless is installed.

### 5.3. Manually installing KServeCopy link

If you have already installed the Red Hat OpenShift Service Mesh Operator and created a`ServiceMeshControlPlane` resource or if you have installed the Red Hat OpenShift Serverless Operator and created a`KNativeServing` resource, the Red Hat OpenShift AI Operator cannot install KServe and configure its dependencies. In this situation, you must install KServe manually.

Important

The procedures in this section show how to perform a new installation of KServe and its dependencies and are intended as a complete installation and configuration reference. If you have already installed and configured OpenShift Service Mesh or OpenShift Serverless, you might not need to follow all steps. If you are unsure about what updates to apply to your existing configuration to use KServe, contact Red Hat Support.

Note

You can run KServe in`Unmanaged` mode during manual installations of the single-model serving platform. This mode is useful when you need more control over KServe components, such as modifying resource limits for the KServe controller.

#### 5.3.1. Installing KServe dependenciesCopy link

Before you install KServe, you must install and configure some dependencies. Specifically, you must create Red Hat OpenShift Service Mesh and Knative Serving instances and then configure secure gateways for Knative Serving.

Note

Currently, only OpenShift Service Mesh v2 is supported. For more information, see [Supported Configurations](https://access.redhat.com/articles/rhoai-supported-configs).

#### 5.3.2. Creating an OpenShift Service Mesh instanceCopy link

The following procedure shows how to create a Red Hat OpenShift Service Mesh instance.

Prerequisites

You have installed the OpenShift CLI (`oc`) as described in the appropriate documentation for your cluster:

- [Installing the OpenShift CLI](https://docs.redhat.com/en/documentation/openshift_dedicated/4/html/cli_tools/openshift-cli-oc#installing-openshift-cli) for OpenShift Dedicated
- [Installing the OpenShift CLI](https://docs.redhat.com/en/documentation/red_hat_openshift_service_on_aws_classic_architecture/4/html/cli_tools/openshift-cli-oc#installing-openshift-cli) for Red Hat OpenShift Service on AWS (classic architecture)

Procedure

In a terminal window, if you are not already logged in to your OpenShift cluster as a cluster administrator, log in to the OpenShift CLI (`oc`) as shown in the following example:

```plaintext
$ oc login <openshift_cluster_url> -u <admin_username> -p <password>
```

Create the required namespace for Red Hat OpenShift Service Mesh.

```plaintext
$ oc create ns istio-system
```

You see the following output:

```plaintext
namespace/istio-system created
```

Define a`ServiceMeshControlPlane` object in a YAML file named`smcp.yaml` with the following contents:

```plaintext
apiVersion: maistra.io/v2
kind: ServiceMeshControlPlane
metadata:
  name: minimal
  namespace: istio-system
spec:
  tracing:
    type: None
  addons:
    grafana:
      enabled: false
    kiali:
      name: kiali
      enabled: false
    prometheus:
      enabled: false
    jaeger:
      name: jaeger
  security:
    dataPlane:
      mtls: true
    identity:
      type: ThirdParty
  techPreview:
    meshConfig:
      defaultConfig:
        terminationDrainDuration: 35s
  gateways:
    ingress:
      service:
        metadata:
          labels:
            knative: ingressgateway
  proxy:
    networking:
      trafficControl:
        inbound:
          excludedPorts:
            - 8444
            - 8022
```

For more information about the values in the YAML file, see the [Service Mesh control plane configuration reference](https://docs.redhat.com/en/documentation/red_hat_openshift_service_on_aws/4/html/service_mesh/service-mesh-2-x#ossm-reference).

Create the service mesh control plane.

```plaintext
$ oc apply -f smcp.yaml
```

1. You have [installed](https://docs.redhat.com/en/documentation/red_hat_openshift_service_on_aws/4/html/service_mesh/service-mesh-2-x#installing-ossm) the Red Hat OpenShift Service Mesh Operator and dependent Operators.

Verification

Verify creation of the service mesh instance as follows:

In the OpenShift CLI, enter the following command:

```plaintext
$ oc get pods -n istio-system
```

The preceding command lists all running pods in the`istio-system` project. This is the project in which OpenShift Service Mesh is installed.

Confirm that there are running pods for the service mesh control plane, ingress gateway, and egress gateway. These pods have the following naming patterns:

```plaintext
NAME                                          READY   STATUS   	  RESTARTS    AGE
istio-egressgateway-7c46668687-fzsqj          1/1     Running     0           22h
istio-ingressgateway-77f94d8f85-fhsp9         1/1     Running     0           22h
istiod-data-science-smcp-cc8cfd9b8-2rkg4      1/1     Running     0           22h
```

#### 5.3.3. Creating a Knative Serving instanceCopy link

The following procedure shows how to install Knative Serving and then create an instance.

Prerequisites

You have installed the OpenShift CLI (`oc`) as described in the appropriate documentation for your cluster:

- [Installing the OpenShift CLI](https://docs.redhat.com/en/documentation/openshift_dedicated/4/html/cli_tools/openshift-cli-oc#installing-openshift-cli) for OpenShift Dedicated
- [Installing the OpenShift CLI](https://docs.redhat.com/en/documentation/red_hat_openshift_service_on_aws_classic_architecture/4/html/cli_tools/openshift-cli-oc#installing-openshift-cli) for Red Hat OpenShift Service on AWS (classic architecture)

Procedure

In a terminal window, if you are not already logged in to your OpenShift cluster as a cluster administrator, log in to the OpenShift CLI (`oc`) as shown in the following example:

```plaintext
$ oc login <openshift_cluster_url> -u <admin_username> -p <password>
```

Check whether the required project (that is, namespace) for Knative Serving already exists.

```plaintext
$ oc get ns knative-serving
```

If the project exists, you see output similar to the following example:

```plaintext
NAME              STATUS   AGE
knative-serving   Active   4d20h
```

If the`knative-serving` project doesn’t already exist, create it.

```plaintext
$ oc create ns knative-serving
```

You see the following output:

```plaintext
namespace/knative-serving created
```

Define a`ServiceMeshMember` object in a YAML file called`default-smm.yaml` with the following contents:

```plaintext
apiVersion: maistra.io/v1
kind: ServiceMeshMember
metadata:
  name: default
  namespace: knative-serving
spec:
  controlPlaneRef:
    namespace: istio-system
    name: minimal
```

Create the`ServiceMeshMember` object in the`istio-system` namespace.

```plaintext
$ oc apply -f default-smm.yaml
```

You see the following output:

```plaintext
servicemeshmember.maistra.io/default created
```

Define a`KnativeServing` object in a YAML file called`knativeserving-istio.yaml` with the following contents:

```plaintext
apiVersion: operator.knative.dev/v1beta1
kind: KnativeServing
metadata:
  name: knative-serving
  namespace: knative-serving
  annotations:
    serverless.openshift.io/default-enable-http2: "true"
spec:
  workloads:
    - name: net-istio-controller
      env:
        - container: controller
          envVars:
            - name: ENABLE_SECRET_INFORMER_FILTERING_BY_CERT_UID
              value: 'true'
    - annotations:
        sidecar.istio.io/inject: "true" 
```

1

```plaintext

        sidecar.istio.io/rewriteAppHTTPProbers: "true" 
```

2

```plaintext

      name: activator
    - annotations:
        sidecar.istio.io/inject: "true"
        sidecar.istio.io/rewriteAppHTTPProbers: "true"
      name: autoscaler
  ingress:
    istio:
      enabled: true
  config:
    features:
      kubernetes.podspec-affinity: enabled
      kubernetes.podspec-nodeselector: enabled
      kubernetes.podspec-tolerations: enabled
      kubernetes.podspec-schedulername: enabled
      multi-container-probing: enabled
```

The preceding file defines a custom resource (CR) for a`KnativeServing` object. The CR also adds the following actions to each of the activator and autoscaler pods:

1

Injects an Istio sidecar to the pod. This makes the pod part of the service mesh.

2

Enables the Istio sidecar to rewrite the HTTP liveness and readiness probes for the pod.

Note

If you configure a custom domain for a Knative service, you can use a TLS certificate to secure the mapped service. To do this, you must create a TLS secret, and then update the`DomainMapping` CR to use the TLS secret that you have created. For more information, see [Securing a mapped service using a TLS certificate](https://docs.redhat.com/en/documentation/red_hat_openshift_serverless/1.36/html/serving/configuring-custom-domains-for-knative-services#domain-mapping-custom-tls-cert) in the Red Hat OpenShift Serverless documentation.

Optional: You can configure`knative serving` to allow zero initial scale for revisions by using the`allow-zero-initial-scale` autoscaler configuration:

```plaintext
spec:
  config:
    autoscaler:
      allow-zero-initial-scale: "true"
```

Optional: You can enable`initContainers` for your`knative serving` instance by adding the`kubernetes.podspec-init-containers` feature flag as shown in the following example:

```plaintext
spec:
  config:
    features:
      kubernetes.podspec-init-containers: "enabled"
```

You can also add the flag in the`config-feature` ConfigMap in the`knative-serving` namespace. For more information, see [Feature and extension flags](https://knative.dev/docs/serving/configuration/feature-flags/).

Create the`KnativeServing` object in the specified`knative-serving` namespace.

```plaintext
$ oc apply -f knativeserving-istio.yaml
```

You see the following output:

```plaintext
knativeserving.operator.knative.dev/knative-serving created
```

1. You have [installed](https://docs.redhat.com/en/documentation/red_hat_openshift_serverless/1.36/html/installing_openshift_serverless/index) the Red Hat OpenShift Serverless Operator.

Verification

Review the default`ServiceMeshMemberRoll` object in the`istio-system` namespace.

```plaintext
$ oc describe smmr default -n istio-system
```

In the description of the`ServiceMeshMemberRoll` object, locate the`Status.Members` field and confirm that it includes the`knative-serving` namespace.

Verify creation of the Knative Serving instance as follows:

In the OpenShift CLI, enter the following command:

```plaintext
$ oc get pods -n knative-serving
```

The preceding command lists all running pods in the`knative-serving` project. This is the project in which you created the Knative Serving instance.

Confirm that there are numerous running pods in the`knative-serving` project, including activator, autoscaler, controller, and domain mapping pods, as well as pods for the Knative Istio controller, which controls the integration of OpenShift Serverless and OpenShift Service Mesh. An example is shown.

```plaintext
NAME                                     	READY       STATUS    	RESTARTS   	AGE
activator-7586f6f744-nvdlb               	2/2         Running   	0          	22h
activator-7586f6f744-sd77w               	2/2         Running   	0          	22h
autoscaler-764fdf5d45-p2v98             	2/2         Running   	0          	22h
autoscaler-764fdf5d45-x7dc6              	2/2         Running   	0          	22h
autoscaler-hpa-7c7c4cd96d-2lkzg          	1/1         Running   	0          	22h
autoscaler-hpa-7c7c4cd96d-gks9j         	1/1         Running   	0          	22h
controller-5fdfc9567c-6cj9d              	1/1         Running   	0          	22h
controller-5fdfc9567c-bf5x7              	1/1         Running   	0          	22h
domain-mapping-56ccd85968-2hjvp          	1/1         Running   	0          	22h
domain-mapping-56ccd85968-lg6mw          	1/1         Running   	0          	22h
domainmapping-webhook-769b88695c-gp2hk   	1/1         Running     0          	22h
domainmapping-webhook-769b88695c-npn8g   	1/1         Running   	0          	22h
net-istio-controller-7dfc6f668c-jb4xk    	1/1         Running   	0          	22h
net-istio-controller-7dfc6f668c-jxs5p    	1/1         Running   	0          	22h
net-istio-webhook-66d8f75d6f-bgd5r       	1/1         Running   	0          	22h
net-istio-webhook-66d8f75d6f-hld75      	1/1         Running   	0          	22h
webhook-7d49878bc4-8xjbr                 	1/1         Running   	0          	22h
webhook-7d49878bc4-s4xx4                 	1/1         Running   	0          	22h
```

#### 5.3.4. Creating secure gateways for Knative ServingCopy link

To secure traffic between your Knative Serving instance and the service mesh, you must create secure gateways for your Knative Serving instance.

The following procedure shows how to use OpenSSL version 3 or later to generate a wildcard certificate and key and then use them to create local and ingress gateways for Knative Serving.

Important

If you have your own wildcard certificate and key to specify when configuring the gateways, you can skip to step 11 of this procedure.

Prerequisites

You have installed the OpenShift CLI (`oc`) as described in the appropriate documentation for your cluster:

- [Installing the OpenShift CLI](https://docs.redhat.com/en/documentation/openshift_dedicated/4/html/cli_tools/openshift-cli-oc#installing-openshift-cli) for OpenShift Dedicated
- [Installing the OpenShift CLI](https://docs.redhat.com/en/documentation/red_hat_openshift_service_on_aws_classic_architecture/4/html/cli_tools/openshift-cli-oc#installing-openshift-cli) for Red Hat OpenShift Service on AWS (classic architecture)

Procedure

In a terminal window, if you are not already logged in to your OpenShift cluster as a cluster administrator, log in to the OpenShift CLI (`oc`) as shown in the following example:

```plaintext
$ oc login <openshift_cluster_url> -u <admin_username> -p <password>
```

Important

If you have your own wildcard certificate and key to specify when configuring the gateways, skip to step 11 of this procedure.

Set environment variables to define base directories for generation of a wildcard certificate and key for the gateways.

```plaintext
$ export BASE_DIR=/tmp/kserve
$ export BASE_CERT_DIR=${BASE_DIR}/certs
```

Set an environment variable to define the common name used by the ingress controller of your OpenShift cluster.

```plaintext
$ export COMMON_NAME=$(oc get ingresses.config.openshift.io cluster -o jsonpath='{.spec.domain}' | awk -F'.' '{print $(NF-1)"."$NF}')
```

Set an environment variable to define the domain name used by the ingress controller of your OpenShift cluster.

```plaintext
$ export DOMAIN_NAME=$(oc get ingresses.config.openshift.io cluster -o jsonpath='{.spec.domain}')
```

Create the required base directories for the certificate generation, based on the environment variables that you previously set.

```plaintext
$ mkdir ${BASE_DIR}
$ mkdir ${BASE_CERT_DIR}
```

Create the OpenSSL configuration for generation of a wildcard certificate.

```plaintext
$ cat <<EOF> ${BASE_DIR}/openssl-san.config
[ req ]
distinguished_name = req
[ san ]
subjectAltName = DNS:*.${DOMAIN_NAME}
EOF
```

Generate a root certificate.

```plaintext
$ openssl req -x509 -sha256 -nodes -days 3650 -newkey rsa:2048 \
-subj "/O=Example Inc./CN=${COMMON_NAME}" \
-keyout ${BASE_CERT_DIR}/root.key \
-out ${BASE_CERT_DIR}/root.crt
```

Generate a wildcard certificate signed by the root certificate.

```plaintext
$ openssl req -x509 -newkey rsa:2048 \
-sha256 -days 3560 -nodes \
-subj "/CN=${COMMON_NAME}/O=Example Inc." \
-extensions san -config ${BASE_DIR}/openssl-san.config \
-CA ${BASE_CERT_DIR}/root.crt \
-CAkey ${BASE_CERT_DIR}/root.key \
-keyout ${BASE_CERT_DIR}/wildcard.key  \
-out ${BASE_CERT_DIR}/wildcard.crt

$ openssl x509 -in ${BASE_CERT_DIR}/wildcard.crt -text
```

Verify the wildcard certificate.

```plaintext
$ openssl verify -CAfile ${BASE_CERT_DIR}/root.crt ${BASE_CERT_DIR}/wildcard.crt
```

Export the wildcard key and certificate that were created by the script to new environment variables.

```plaintext
$ export TARGET_CUSTOM_CERT=${BASE_CERT_DIR}/wildcard.crt
$ export TARGET_CUSTOM_KEY=${BASE_CERT_DIR}/wildcard.key
```

Optional: To export your own wildcard key and certificate to new environment variables, enter the following commands:

```plaintext
$ export TARGET_CUSTOM_CERT=<path_to_certificate>
$ export TARGET_CUSTOM_KEY=<path_to_key>
```

Note

In the certificate that you provide, you must specify the domain name used by the ingress controller of your OpenShift cluster. You can check this value by running the following command:

`$ oc get ingresses.config.openshift.io cluster -o jsonpath='{.spec.domain}'`

Create a TLS secret in the`istio-system` namespace using the environment variables that you set for the wildcard certificate and key.

```plaintext
$ oc create secret tls wildcard-certs --cert=${TARGET_CUSTOM_CERT} --key=${TARGET_CUSTOM_KEY} -n istio-system
```

Create a`gateways.yaml` YAML file with the following contents:

```plaintext
apiVersion: v1
kind: Service 
```

1

```plaintext

metadata:
  labels:
    experimental.istio.io/disable-gateway-port-translation: "true"
  name: knative-local-gateway
  namespace: istio-system
spec:
  ports:
    - name: http2
      port: 80
      protocol: TCP
      targetPort: 8081
  selector:
    knative: ingressgateway
  type: ClusterIP
---
apiVersion: networking.istio.io/v1beta1
kind: Gateway
metadata:
  name: knative-ingress-gateway 
```

2

```plaintext

  namespace: knative-serving
spec:
  selector:
    knative: ingressgateway
  servers:
    - hosts:
        - '*'
      port:
        name: https
        number: 443
        protocol: HTTPS
      tls:
        credentialName: wildcard-certs
        mode: SIMPLE
---
apiVersion: networking.istio.io/v1beta1
kind: Gateway
metadata:
 name: knative-local-gateway 
```

3

```plaintext

 namespace: knative-serving
spec:
 selector:
   knative: ingressgateway
 servers:
   - port:
       number: 8081
       name: https
       protocol: HTTPS
     tls:
       mode: ISTIO_MUTUAL
     hosts:
       - "*"
```

1

Defines a service in the`istio-system` namespace for the Knative local gateway.

2

Defines an ingress gateway in the`knative-serving namespace`. The gateway uses the TLS secret you created earlier in this procedure. The ingress gateway handles external traffic to Knative.

3

Defines a local gateway for Knative in the`knative-serving` namespace.

Apply the`gateways.yaml` file to create the defined resources.

```plaintext
$ oc apply -f gateways.yaml
```

You see the following output:

```plaintext
service/knative-local-gateway created
gateway.networking.istio.io/knative-ingress-gateway created
gateway.networking.istio.io/knative-local-gateway created
```

1. If you intend to generate a wildcard certificate and key, you have [downloaded and installed](https://www.openssl.org/source/) OpenSSL version 3 or later.

Verification

Review the gateways that you created.

```plaintext
$ oc get gateway --all-namespaces
```

Confirm that you see the local and ingress gateways that you created in the`knative-serving` namespace, as shown in the following example:

```plaintext
NAMESPACE         	NAME                      	AGE
knative-serving   	knative-ingress-gateway   	69s
knative-serving     knative-local-gateway     	2m
```

#### 5.3.5. Installing KServeCopy link

To complete manual installation of KServe, you must install the Red Hat OpenShift AI Add-on, which installs the Red Hat OpenShift AI Operator. Then, you can use the Operator to install KServe.

Prerequisites

You have installed the OpenShift CLI (`oc`) as described in the appropriate documentation for your cluster:

- [Installing the OpenShift CLI](https://docs.redhat.com/en/documentation/openshift_dedicated/4/html/cli_tools/openshift-cli-oc#installing-openshift-cli) for OpenShift Dedicated
- [Installing the OpenShift CLI](https://docs.redhat.com/en/documentation/red_hat_openshift_service_on_aws_classic_architecture/4/html/cli_tools/openshift-cli-oc#installing-openshift-cli) for Red Hat OpenShift Service on AWS (classic architecture)

Procedure

For installation of KServe, configure the OpenShift Service Mesh component as follows:

In the`spec` section, add and configure the`serviceMesh` component as shown:

```plaintext
spec:
 serviceMesh:
   managementState: Unmanaged
```

1. Click the DSC Initialization tab.
2. Click the default-dsci object.
3. Click the YAML tab.
4. Click Save.

For installation of KServe, configure the KServe and OpenShift Serverless components as follows:

In the`spec.components` section, configure the`kserve` component as shown:

```plaintext
spec:
 components:
   kserve:
     managementState: Managed
```

Within the`kserve` component, add the`serving` component, and configure it as shown:

```plaintext
spec:
 components:
   kserve:
     managementState: Managed
     serving:
       managementState: Unmanaged
```

1. In the web console, click Operators → Installed Operators and then click the Red Hat OpenShift AI Operator.
2. Click the Data Science Cluster tab.
3. Click the default-dsc DSC object.
4. Click the YAML tab.
5. Click Save.

#### 5.3.6. Disabling KServe dependenciesCopy link

If you have not enabled the KServe component (that is, you set the value of the`managementState` field to`Removed`), you must also disable the dependent Service Mesh component to avoid errors.

Prerequisites

- You have used the OpenShift CLI (`oc`) or web console to disable the KServe component.

Procedure

Disable the OpenShift Service Mesh component as follows:

In the`spec` section, add the`serviceMesh` component (if it is not already present) and configure the`managementState` field as shown:

```plaintext
spec:
 serviceMesh:
   managementState: Removed
```

1. Click the DSC Initialization tab.
2. Click the default-dsci object.
3. Click the YAML tab.
4. Click Save.

Verification

In the web console, click Operators → Installed Operators and then click the Red Hat OpenShift AI Operator.

The Operator details page opens.

1. In the Conditions section, confirm that there is no ReconcileComplete condition with a status value of Unknown.

### 5.4. Adding an authorization provider for the single-model serving platformCopy link

You can add [Authorino](https://github.com/kuadrant/authorino) as an authorization provider for the single-model serving platform. Adding an authorization provider allows you to enable token authentication for models that you deploy on the platform, which ensures that only authorized parties can make inference requests to the models.

The method that you use to add Authorino as an authorization provider depends on how you install the single-model serving platform. The installation options for the platform are described as follows:

Automated installation

If you have not already created a`ServiceMeshControlPlane` or`KNativeServing` resource on your OpenShift cluster, you can configure the Red Hat OpenShift AI Operator to install KServe and its dependencies. You can include Authorino as part of the automated installation process.

For more information about automated installation, including Authorino, see [Configuring automated installation of KServe](https://docs.redhat.com/en/documentation/red_hat_openshift_ai_cloud_service/1/html/installing_and_uninstalling_openshift_ai_cloud_service/installing-the-single-model-serving-platform_component-install#configuring-automated-installation-of-kserve_component-install).

Manual installation

If you have already created a`ServiceMeshControlPlane` or`KNativeServing` resource on your OpenShift cluster, you cannot configure the Red Hat OpenShift AI Operator to install KServe and its dependencies. In this situation, you must install KServe manually. You must also manually configure Authorino.

For more information about manual installation, including Authorino, see [Manually installing KServe](https://docs.redhat.com/en/documentation/red_hat_openshift_ai_cloud_service/1/html/installing_and_uninstalling_openshift_ai_cloud_service/installing-the-single-model-serving-platform_component-install#manually-installing-kserve_component-install).

Note

You can run KServe in`Unmanaged` mode during manual installations of the single-model serving platform. This mode is useful when you need more control over KServe components, such as modifying resource limits for the KServe controller.

#### 5.4.1. Manually adding an authorization providerCopy link

You can add [Authorino](https://github.com/kuadrant/authorino) as an authorization provider for the single-model serving platform. Adding an authorization provider allows you to enable token authentication for models that you deploy on the platform, which ensures that only authorized parties can make inference requests to the models.

To manually add Authorino as an authorization provider, you must install the`Red Hat - Authorino` Operator, create an Authorino instance, and then configure the OpenShift Service Mesh and KServe components to use the instance.

Important

To manually add an authorization provider, you must make configuration updates to your OpenShift Service Mesh instance. To ensure that your OpenShift Service Mesh instance remains in a supported state, make only the updates shown in this section.

Prerequisites

- You have reviewed the options for adding Authorino as an authorization provider and identified manual installation as the appropriate option. See [Adding an authorization provider](https://docs.redhat.com/en/documentation/red_hat_openshift_ai_cloud_service/1/html/installing_and_uninstalling_openshift_ai_cloud_service/installing-the-single-model-serving-platform_component-install#adding-an-authorization-provider_component-install).
- You have manually installed KServe and its dependencies, including OpenShift Service Mesh. See [Manually installing KServe](https://docs.redhat.com/en/documentation/red_hat_openshift_ai_cloud_service/1/html/installing_and_uninstalling_openshift_ai_cloud_service/installing-the-single-model-serving-platform_component-install#manually-installing-kserve_component-install).
- When you manually installed KServe, you set the value of the`managementState` field for the`serviceMesh` component to`Unmanaged`. This setting is required for manually adding Authorino. See [Installing KServe](https://docs.redhat.com/en/documentation/red_hat_openshift_ai_cloud_service/1/html/installing_and_uninstalling_openshift_ai_cloud_service/installing-the-single-model-serving-platform_component-install#installing-kserve_component-install).

#### 5.4.2. Installing the Red Hat Authorino OperatorCopy link

Before you can add Authorino as an authorization provider, you must install the`Red Hat - Authorino` Operator on your OpenShift cluster.

Prerequisites

- You have cluster administrator privileges for your OpenShift cluster.

Procedure

On the Install Operator page, keep the default values for Installation mode, Installed Namespace and Update Approval.

1. For Update channel, select Stable.
2. For Version, select 1.2.1 or later.

Verification

In the OpenShift web console, click Operators → Installed Operators and confirm that the`Red Hat - Authorino` Operator shows one of the following statuses:

- `Installing`- installation is in progress; wait for this to change to`Succeeded`. This might take several minutes.
- `Succeeded`- installation is successful.

#### 5.4.3. Creating an Authorino instanceCopy link

When you have installed the`Red Hat - Authorino` Operator on your OpenShift cluster, you must create an Authorino instance.

Prerequisites

You have privileges to add resources to the project in which your OpenShift Service Mesh instance was created. See [Creating an OpenShift Service Mesh instance](https://docs.redhat.com/en/documentation/red_hat_openshift_ai_cloud_service/1/html/installing_and_uninstalling_openshift_ai_cloud_service/installing-the-single-model-serving-platform_component-install#creating-a-service-mesh-instance_component-install).

For more information about OpenShift permissions, see [Using RBAC to define and apply permissions for OpenShift Dedicated](https://docs.redhat.com/en/documentation/openshift_dedicated/4/html/authentication_and_authorization/using-rbac) or [Using RBAC to define and apply permissions for Red Hat OpenShift Service on AWS (classic architecture)](https://docs.redhat.com/en/documentation/red_hat_openshift_service_on_aws_classic_architecture/4/html/authentication_and_authorization/using-rbac).

- You have installed the`Red Hat - Authorino` Operator.

Procedure

Log in to the OpenShift CLI (`oc`) as follows:

```plaintext
$ oc login <openshift_cluster_url> -u <username> -p <password>
```

Create a namespace to install the Authorino instance.

```plaintext
$ oc new-project <namespace_for_authorino_instance>
```

Note

The automated installation process creates a namespace called`redhat-ods-applications-auth-provider` for the Authorino instance. Consider using the same namespace name for the manual installation.

To enroll the new namespace for the Authorino instance in your existing OpenShift Service Mesh instance, create a new YAML file with the following contents:

```plaintext
apiVersion: maistra.io/v1
kind: ServiceMeshMember
metadata:
  name: default
  namespace: <namespace_for_authorino_instance>
spec:
  controlPlaneRef:
    namespace: <namespace_for_service_mesh_instance>
    name: <name_of_service_mesh_instance>
```

Create the`ServiceMeshMember` resource on your cluster.

```plaintext
$ oc create -f <file_name>.yaml
```

To configure an Authorino instance, create a new YAML file as shown in the following example:

```plaintext
apiVersion: operator.authorino.kuadrant.io/v1beta1
kind: Authorino
metadata:
  name: authorino
  namespace: <namespace_for_authorino_instance>
spec:
  authConfigLabelSelectors: security.opendatahub.io/authorization-group=default
  clusterWide: true
  listener:
    tls:
      enabled: false
  oidcServer:
    tls:
      enabled: false
```

Create the`Authorino` resource on your cluster.

```plaintext
$ oc create -f <file_name>.yaml
```

Patch the Authorino deployment to inject an Istio sidecar, which makes the Authorino instance part of your OpenShift Service Mesh instance.

```plaintext
$ oc patch deployment <name_of_authorino_instance> -n <namespace_for_authorino_instance> -p '{"spec": {"template":{"metadata":{"labels":{"sidecar.istio.io/inject":"true"}}}} }'
```

1. Open a new terminal window.
2. Save the YAML file.
3. Save the YAML file.

Verification

Confirm that the Authorino instance is running as follows:

Check the pods (and containers) that are running in the namespace that you created for the Authorino instance, as shown in the following example:

```plaintext
$ oc get pods -n redhat-ods-applications-auth-provider -o="custom-columns=NAME:.metadata.name,STATUS:.status.phase,CONTAINERS:.spec.containers[*].name"
```

Confirm that the output resembles the following example:

```plaintext
NAME                         STATUS    CONTAINERS
authorino-6bc64bd667-kn28z   Running   authorino,istio-proxy
```

As shown in the example, there is a single running pod for the Authorino instance. The pod has containers for Authorino and for the Istio sidecar that you injected.

#### 5.4.4. Configuring an OpenShift Service Mesh instance to use AuthorinoCopy link

When you have created an Authorino instance, you must configure your OpenShift Service Mesh instance to use Authorino as an authorization provider.

Important

To ensure that your OpenShift Service Mesh instance remains in a supported state, make only the configuration updates shown in the following procedure.

Prerequisites

- You have created an Authorino instance and enrolled the namespace for the Authorino instance in your OpenShift Service Mesh instance.
- You have privileges to modify the OpenShift Service Mesh instance. See [Creating an OpenShift Service Mesh instance](https://docs.redhat.com/en/documentation/red_hat_openshift_ai_cloud_service/1/html/installing_and_uninstalling_openshift_ai_cloud_service/installing-the-single-model-serving-platform_component-install#creating-a-service-mesh-instance_component-install).

Procedure

In a terminal window, if you are not already logged in to your OpenShift cluster as a user that has privileges to update the OpenShift Service Mesh instance, log in to the OpenShift CLI (`oc`) as shown in the following example:

```plaintext
$ oc login <openshift_cluster_url> -u <username> -p <password>
```

Create a new YAML file with the following contents:

```plaintext
spec:
 techPreview:
   meshConfig:
     extensionProviders:
     - name: redhat-ods-applications-auth-provider
       envoyExtAuthzGrpc:
         service: <name_of_authorino_instance>-authorino-authorization.<namespace_for_authorino_instance>.svc.cluster.local
         port: 50051
```

Use the`oc patch` command to apply the YAML file to your OpenShift Service Mesh instance.

```plaintext
$ oc patch smcp <name_of_service_mesh_instance> --type merge -n <namespace_for_service_mesh_instance> --patch-file <file_name>.yaml
```

Important

You can apply the configuration shown as a patch only if you have not already specified other extension providers in your OpenShift Service Mesh instance. If you have already specified other extension providers, you must manually edit your`ServiceMeshControlPlane` resource to add the configuration.

1. Save the YAML file.

Verification

Verify that your Authorino instance has been added as an extension provider in your OpenShift Service Mesh configuration as follows:

Inspect the`ConfigMap` object for your OpenShift Service Mesh instance:

```plaintext
$ oc get configmap istio-<name_of_service_mesh_instance> -n <namespace_for_service_mesh_instance> --output=jsonpath={.data.mesh}
```

Confirm that you see output similar to the following example, which shows that the Authorino instance has been successfully added as an extension provider.

```plaintext
defaultConfig:
  discoveryAddress: istiod-data-science-smcp.istio-system.svc:15012
  proxyMetadata:
    ISTIO_META_DNS_AUTO_ALLOCATE: "true"
    ISTIO_META_DNS_CAPTURE: "true"
    PROXY_XDS_VIA_AGENT: "true"
  terminationDrainDuration: 35s
  tracing: {}
dnsRefreshRate: 300s
enablePrometheusMerge: true
extensionProviders:
- envoyExtAuthzGrpc:
    port: 50051
    service: authorino-authorino-authorization.opendatahub-auth-provider.svc.cluster.local
  name: opendatahub-auth-provider
ingressControllerMode: "OFF"
rootNamespace: istio-system
trustDomain: null%
```

#### 5.4.5. Configuring authorization for KServeCopy link

To configure the single-model serving platform to use Authorino, you must create a global`AuthorizationPolicy` resource that is applied to the KServe predictor pods that are created when you deploy a model. In addition, to account for the multiple network hops that occur when you make an inference request to a model, you must create an`EnvoyFilter` resource that continually resets the HTTP host header to the one initially included in the inference request.

Prerequisites

- You have created an Authorino instance and configured your OpenShift Service Mesh to use it.
- You have privileges to update the KServe deployment on your cluster.
- You have privileges to add resources to the project in which your OpenShift Service Mesh instance was created. See [Creating an OpenShift Service Mesh instance](https://docs.redhat.com/en/documentation/red_hat_openshift_ai_cloud_service/1/html/installing_and_uninstalling_openshift_ai_cloud_service/installing-the-single-model-serving-platform_component-install#creating-a-service-mesh-instance_component-install).

Procedure

In a terminal window, if you are not already logged in to your OpenShift cluster as a user that has privileges to update the KServe deployment, log in to the OpenShift CLI (`oc`) as shown in the following example:

```plaintext
$ oc login <openshift_cluster_url> -u <username> -p <password>
```

Create a new YAML file with the following contents:

```plaintext
apiVersion: security.istio.io/v1beta1
kind: AuthorizationPolicy
metadata:
  name: kserve-predictor
spec:
  action: CUSTOM
  provider:
     name: redhat-ods-applications-auth-provider 
```

1

```plaintext

  rules:
     - to:
          - operation:
               notPaths:
                  - /healthz
                  - /debug/pprof/
                  - /metrics
                  - /wait-for-drain
  selector:
     matchLabels:
        component: predictor
```

1

The name that you specify must match the name of the extension provider that you added to your OpenShift Service Mesh instance.

Create the`AuthorizationPolicy` resource in the namespace for your OpenShift Service Mesh instance.

```plaintext
$ oc create -n <namespace_for_service_mesh_instance> -f <file_name>.yaml
```

Create another new YAML file with the following contents:

```plaintext
apiVersion: networking.istio.io/v1alpha3
kind: EnvoyFilter
metadata:
  name: activator-host-header
spec:
  priority: 20
  workloadSelector:
    labels:
      component: predictor
  configPatches:
  - applyTo: HTTP_FILTER
    match:
      listener:
        filterChain:
          filter:
            name: envoy.filters.network.http_connection_manager
    patch:
      operation: INSERT_BEFORE
      value:
        name: envoy.filters.http.lua
        typed_config:
          '@type': type.googleapis.com/envoy.extensions.filters.http.lua.v3.Lua
          inlineCode: |
           function envoy_on_request(request_handle)
              local headers = request_handle:headers()
              if not headers then
                return
              end
              local original_host = headers:get("k-original-host")
              if original_host then
                port_seperator = string.find(original_host, ":", 7)
                if port_seperator then
                  original_host = string.sub(original_host, 0, port_seperator-1)
                end
                headers:replace('host', original_host)
              end
            end
```

The`EnvoyFilter` resource shown continually resets the HTTP host header to the one initially included in any inference request.

Create the`EnvoyFilter` resource in the namespace for your OpenShift Service Mesh instance.

```plaintext
$ oc create -n <namespace_for_service_mesh_instance> -f <file_name>.yaml
```

1. Save the YAML file.

Verification

Check that the`AuthorizationPolicy` resource was successfully created.

```plaintext
$ oc get authorizationpolicies -n <namespace_for_service_mesh_instance>
```

Confirm that you see output similar to the following example:

```plaintext
NAME               AGE
kserve-predictor   28h
```

Check that the`EnvoyFilter` resource was successfully created.

```plaintext
$ oc get envoyfilter -n <namespace_for_service_mesh_instance>
```

Confirm that you see output similar to the following example:

```plaintext
NAME                                          AGE
activator-host-header                         28h
```

## Chapter 6. Installing the multi-model serving platformCopy link

For deploying small and medium-sized models, OpenShift AI includes a multi-model serving platform that is based on the ModelMesh component. On the multi-model serving platform, multiple models can be deployed from the same model server and share the server resources.

Important

Starting with OpenShift AI version 2.19, the multi-model serving platform based on ModelMesh is deprecated. You can continue to deploy models on the multi-model serving platform, but it is recommended that you migrate to the single-model serving platform.

For more information or for help on using the single-model serving platform, contact your account manager.

To install the multi-model serving platform or ModelMesh, follow the steps described in [Installing Red Hat OpenShift AI components by using the web console](https://docs.redhat.com/en/documentation/red_hat_openshift_ai_cloud_service/1/html/installing_and_uninstalling_openshift_ai_cloud_service/installing-and-deploying-openshift-ai_install#installing-openshift-ai-components-using-web-console_install).

## Chapter 7. Accessing the dashboardCopy link

After you have installed OpenShift AI and added users, you can access the URL for your OpenShift AI console and share the URL with the users to let them log in and work on their models.

Prerequisites

- You have installed OpenShift AI on your OpenShift cluster.
- You have added at least one user to the user group for OpenShift AI as described in [Adding users to OpenShift AI user groups](https://docs.redhat.com/en/documentation/red_hat_openshift_ai_cloud_service/1/html/managing_openshift_ai/managing-users-and-groups#adding-users-to-user-groups_managing-rhoai).

Procedure

1. Log in to OpenShift web console.
2. Click the application launcher ().
3. Right-click on Red Hat OpenShift AI and copy the URL for your OpenShift AI instance.
4. Provide this instance URL to your data scientists to let them log in to OpenShift AI.

Verification

- Confirm that you and your users can log in to OpenShift AI by using the instance URL.

Note: In the Red Hat OpenShift AI dashboard, users can view the list of the installed OpenShift AI components, their corresponding source (upstream) components, and the versions of the installed components, as described in [Viewing installed components](https://docs.redhat.com/en/documentation/red_hat_openshift_ai_cloud_service/1/html/getting_started_with_red_hat_openshift_ai_cloud_service#viewing-installed-components_get-started).

## Chapter 8. Enabling acceleratorsCopy link

Before you can use an accelerator in OpenShift AI, you must install the relevant software components. The installation process varies based on the accelerator type.

Prerequisites

- You have logged in to your OpenShift cluster.
- You have the`cluster-admin` role in your OpenShift cluster.
- You have installed an accelerator and confirmed that it is detected in your environment.

Procedure

Follow the appropriate documentation to enable your accelerator:

- NVIDIA GPUs: See [Enabling NVIDIA GPUs](https://docs.redhat.com/en/documentation/red_hat_openshift_ai_cloud_service/1/html/working_with_accelerators/enabling-nvidia-gpus_accelerators).
- Intel Gaudi AI accelerators: See [Enabling Intel Gaudi AI accelerators](https://docs.redhat.com/en/documentation/red_hat_openshift_ai_cloud_service/1/html/working_with_accelerators/intel-gaudi-ai-accelerator-integration_accelerators).
- AMD GPUs: See [Enabling AMD GPUs](https://docs.redhat.com/en/documentation/red_hat_openshift_ai_cloud_service/1/html/working_with_accelerators/amd-gpu-integration_accelerators).

After installing your accelerator, create an accelerator profile as described in: [Working with accelerator profiles](https://docs.redhat.com/en/documentation/red_hat_openshift_ai_cloud_service/1/html/working_with_accelerators/working-with-accelerator-profiles_accelerators).

Important

By default, hardware profiles are hidden in the dashboard navigation menu and user interface, while accelerator profiles remain visible. In addition, user interface components associated with the deprecated accelerator profiles functionality are still displayed. To show the Settings → Hardware profiles option in the dashboard navigation menu, and the user interface components associated with hardware profiles, set the`disableHardwareProfiles` value to`false` in the`OdhDashboardConfig` custom resource (CR) in OpenShift. For more information about setting dashboard configuration options, see [Customizing the dashboard](https://docs.redhat.com/en/documentation/red_hat_openshift_ai_cloud_service/1/html/managing_resources/customizing-the-dashboard).

Verification

From the Administrator perspective, go to the Operators → Installed Operators page. Confirm that the following Operators appear:

- The Operator for your accelerator
- Node Feature Discovery (NFD)
- Kernel Module Management (KMM)

The accelerator is correctly detected a few minutes after full installation of the Node Feature Discovery (NFD) and the relevant accelerator Operator. The OpenShift CLI (`oc`) displays the appropriate output for the GPU worker node. For example, here is output confirming that an NVIDIA GPU is detected:

```plaintext
# Expected output when the accelerator is detected correctly
oc describe node <node name>
...
Capacity:
  cpu:                4
  ephemeral-storage:  313981932Ki
  hugepages-1Gi:      0
  hugepages-2Mi:      0
  memory:             16076568Ki
  nvidia.com/gpu:     1
  pods:               250
Allocatable:
  cpu:                3920m
  ephemeral-storage:  288292006229
  hugepages-1Gi:      0
  hugepages-2Mi:      0
  memory:             12828440Ki
  nvidia.com/gpu:     1
  pods:               250
```

## Chapter 9. Working with certificatesCopy link

When you install Red Hat OpenShift AI, OpenShift automatically applies a default Certificate Authority (CA) bundle to manage authentication for most OpenShift AI components, such as workbenches and model servers. These certificates are trusted self-signed certificates that help secure communication. However, as a cluster administrator, you might need to configure additional self-signed certificates to use some components, such as the data science pipeline server and object storage solutions. If an OpenShift AI component uses a self-signed certificate that is not part of the existing cluster-wide CA bundle, you have the following options for including the certificate:

- Add it to the OpenShift cluster-wide CA bundle.
- Add it to a custom CA bundle, separate from the cluster-wide CA bundle.

As a cluster administrator, you can also change how to manage authentication for OpenShift AI as follows:

- Manually manage certificate changes, instead of relying on the OpenShift AI Operator to handle them automatically.
- Remove the cluster-wide CA bundle, either from all namespaces or specific ones. If you prefer to implement a different authentication approach, you can override the default OpenShift AI behavior, as described in Removing the CA bundle.

### 9.1. Understanding how OpenShift AI handles certificatesCopy link

After installing OpenShift AI, the Red Hat OpenShift AI Operator automatically creates an empty`odh-trusted-ca-bundle` configuration file (ConfigMap). The Cluster Network Operator (CNO) injects the cluster-wide CA bundle into the`odh-trusted-ca-bundle` configMap with the label`"config.openshift.io/inject-trusted-cabundle"`.

```plaintext
apiVersion: v1
kind: ConfigMap
metadata:
  labels:
    app.kubernetes.io/part-of: opendatahub-operator
    config.openshift.io/inject-trusted-cabundle: 'true'
  name: odh-trusted-ca-bundle
```

After the CNO operator injects the bundle, it updates the ConfigMap with the contents of the`ca-bundle.crt` file.

```plaintext
apiVersion: v1
kind: ConfigMap
metadata:
  labels:
    app.kubernetes.io/part-of: opendatahub-operator
    config.openshift.io/inject-trusted-cabundle: 'true'
  name: odh-trusted-ca-bundle
data:
  ca-bundle.crt: |
    <BUNDLE OF CLUSTER-WIDE CERTIFICATES>
```

The management of CA bundles is configured through the Data Science Cluster Initialization (DSCI) object. Within this object, you can set the`spec.trustedCABundle.managementState` field to one of the following values:

`Unmanaged`: The Red Hat OpenShift AI administrator manually manages the`odh-trusted-ca-bundle` ConfigMap, instead of allowing the Operator to manage it. Changing the`managementState` from`Managed` to`Unmanaged` does not remove the`odh-trusted-ca-bundle` ConfigMap. However, the ConfigMap is no longer automatically updated if changes are made to the`customCABundle` field.

The`Unmanaged` setting is useful if your organization implements a different method for managing trusted CA bundles, such as Ansible automation, and does not want the Red Hat OpenShift AI Operator to handle certificates automatically. This setting provides greater control, preventing the Operator from overwriting custom configurations.

- `Managed`: (Default) The Red Hat OpenShift AI Operator manages the`odh-trusted-ca-bundle` ConfigMap and adds it to all non-reserved existing and new namespaces. It does not add the ConfigMap to any reserved or system namespaces, such as`default`,`openshift-\*` or`kube-*`. The Red Hat OpenShift AI Operator automatically updates the ConfigMap to reflect any changes made to the`customCABundle` field.

### 9.2. Adding certificatesCopy link

If you must use a self-signed certificate that is not part of the existing cluster-wide CA bundle, you have two options for configuring the certificate:

Add it to the cluster-wide CA bundle.

This option is useful when the certificate is needed for secure communication across multiple services or when it’s required by security policies to be trusted cluster-wide. This option ensures that all services and components in the cluster trust the certificate automatically. It simplifies management because the certificate is trusted across the entire cluster, avoiding the need to configure the certificate separately for each service.

Add it to a custom CA bundle that is separate from the OpenShift cluster-wide bundle.

Consider this option for the following scenarios:

- Limit scope: Only specific services need the certificate, not the whole cluster.
- Isolation: Keeps custom certificates separate, preventing changes to the global configuration.
- Avoid global impact: Does not affect services that do not need the certificate.
- Easier management: Makes it simpler to manage certificates for specific services.

### 9.3. Adding certificates to a cluster-wide CA bundleCopy link

You can add a self-signed certificate to a cluster-wide Certificate Authority (CA) bundle (`ca-bundle.crt`).

When the cluster-wide CA bundle is updated, the Cluster Network Operator (CNO) automatically detects the change and injects the updated bundle into the`odh-trusted-ca-bundle` ConfigMap, making the certificate available to OpenShift AI components.

Note: By default, the management state for the Trusted CA bundle is`Managed`(that is, the`spec.trustedCABundle.managementState` field in the Red Hat OpenShift AI Operator’s DSCI object is set to`Managed`). If you change this setting to`Unmanaged`, you must manually update the`odh-trusted-ca-bundle` ConfigMap to include the updated cluster-wide CA bundle.

Alternatively, you can add certificates to a custom CA bundle, as described in [Adding certificates to a custom CA bundle](https://docs.redhat.com/en/documentation/red_hat_openshift_ai_cloud_service/1/html/installing_and_uninstalling_openshift_ai_cloud_service/working-with-certificates_certs#adding-certificates-to-a-custom-ca-bundle_certs).

Prerequisites

You have installed the OpenShift CLI (`oc`) as described in the appropriate documentation for your cluster:

- [Installing the OpenShift CLI](https://docs.redhat.com/en/documentation/openshift_dedicated/4/html/cli_tools/openshift-cli-oc#installing-openshift-cli) for OpenShift Dedicated
- [Installing the OpenShift CLI](https://docs.redhat.com/en/documentation/red_hat_openshift_service_on_aws_classic_architecture/4/html/cli_tools/openshift-cli-oc#installing-openshift-cli) for Red Hat OpenShift Service on AWS (classic architecture)

Procedure

Create a ConfigMap that includes the root CA certificate used to sign the certificate, where` ` is the path to the CA certificate bundle on your local file system:

```plaintext
oc create configmap custom-ca \
 	--from-file=ca-bundle.crt=</path/to/example-ca.crt> \
 	-n openshift-config
```

Update the cluster-wide proxy configuration with the newly-created ConfigMap:

```plaintext
oc patch proxy/cluster \
    	 --type=merge \
   	 --patch='{"spec":{"trustedCA":{"name":"custom-ca"}}}'
```

Verification

Run the following command to verify that all non-reserved namespaces contain the`odh-trusted-ca-bundle` ConfigMap:

```plaintext
oc get configmaps --all-namespaces -l app.kubernetes.io/part-of=opendatahub-operator | grep odh-trusted-ca-bundle
```

Additional resources

- [Configuring certificates](https://docs.redhat.com/en/documentation/openshift_container_platform/4.19/html/security_and_compliance/configuring-certificates) in the OpenShift Container Platform documentation
- [Injecting a custom CA Bundle](https://docs.redhat.com/en/documentation/red_hat_openshift_service_on_aws/4/html-single/operators/index#olm-inject-custom-ca_olm-configuring-proxy-support) in the Red Hat OpenShift Service on AWS documentation
- [Injecting a custom CA Bundle](https://docs.redhat.com/en/documentation/openshift_dedicated/4/html/operators/administrator-tasks#olm-inject-custom-ca_olm-configuring-proxy-support) in the OpenShift Dedicated documentation

### 9.4. Adding certificates to a custom CA bundleCopy link

You can add self-signed certificates to a custom CA bundle that is separate from the OpenShift cluster-wide bundle.

This method is ideal for scenarios where components need access to external resources that require a self-signed certificate. For example, you may need to add self-signed certificates to grant data science pipelines access S3-compatible object storage.

Prerequisites

You have installed the OpenShift CLI (`oc`) as described in the appropriate documentation for your cluster:

- [Installing the OpenShift CLI](https://docs.redhat.com/en/documentation/openshift_dedicated/4/html/cli_tools/openshift-cli-oc#installing-openshift-cli) for OpenShift Dedicated
- [Installing the OpenShift CLI](https://docs.redhat.com/en/documentation/red_hat_openshift_service_on_aws_classic_architecture/4/html/cli_tools/openshift-cli-oc#installing-openshift-cli) for Red Hat OpenShift Service on AWS (classic architecture)

Procedure

In the`spec.trustedCABundle` section, add the custom certificate to the`customCABundle` field, as shown in the following example:

```plaintext
spec:
  trustedCABundle:
    managementState: Managed
    customCABundle: |
      -----BEGIN CERTIFICATE-----
      examplebundle123
      -----END CERTIFICATE-----
```

1. Log in to OpenShift.
2. Click Operators → Installed Operators and then click the Red Hat OpenShift AI Operator.
3. Click the DSC Initialization tab.
4. Click the default-dsci object.
5. Click the YAML tab.
6. Click Save.

The Red Hat OpenShift AI Operator automatically updates the ConfigMap to reflect any changes made to the`customCABundle` field. It adds the`odh-ca-bundle.crt` file containing the certificates to the`odh-trusted-ca-bundle` ConfigMap, as shown in the following example:

```plaintext
apiVersion: v1
kind: ConfigMap
metadata:
  labels:
    app.kubernetes.io/part-of: opendatahub-operator
    config.openshift.io/inject-trusted-cabundle: 'true'
  name: odh-trusted-ca-bundle
data:
  ca-bundle.crt: |
    <BUNDLE OF CLUSTER-WIDE CERTIFICATES>
  odh-ca-bundle.crt: |
    <BUNDLE OF CUSTOM CERTIFICATES>
```

Verification

Run the following command to verify that a non-reserved namespace contains the`odh-trusted-ca-bundle` ConfigMap and that the ConfigMap contains your`customCABundle` value. In the following command, example-namespace is the non-reserved namespace and examplebundle123 is the`customCABundle` value.

```plaintext
oc get configmap odh-trusted-ca-bundle -n example-namespace -o yaml | grep examplebundle123
```

### 9.5. Using self-signed certificates with OpenShift AI componentsCopy link

Some OpenShift AI components have additional options or required configuration for self-signed certificates.

#### 9.5.1. Accessing S3-compatible object storage with self-signed certificatesCopy link

To securely connect OpenShift AI components to object storage solutions or databases that are deployed within an OpenShift cluster that uses self-signed certificates, you must provide a certificate authority (CA) certificate. Each namespace includes a ConfigMap named`kube-root-ca.crt`, which contains the CA certificate of the internal API Server.

Prerequisites

You have installed the OpenShift CLI (`oc`) as described in the appropriate documentation for your cluster:

- [Installing the OpenShift CLI](https://docs.redhat.com/en/documentation/openshift_dedicated/4/html/cli_tools/openshift-cli-oc#installing-openshift-cli) for OpenShift Dedicated
- [Installing the OpenShift CLI](https://docs.redhat.com/en/documentation/red_hat_openshift_service_on_aws_classic_architecture/4/html/cli_tools/openshift-cli-oc#installing-openshift-cli) for Red Hat OpenShift Service on AWS (classic architecture)

Procedure

In a terminal window, log in to the OpenShift CLI (`oc`) as shown in the following example:

```plaintext
oc login api.<cluster_name>.<cluster_domain>:6443 --web
```

Retrieve the current OpenShift AI trusted CA configuration and store it in a new file:

```plaintext
oc get dscinitializations.dscinitialization.opendatahub.io default-dsci -o json | jq -r '.spec.trustedCABundle.customCABundle' > /tmp/my-custom-ca-bundles.crt
```

Add the cluster’s`kube-root-ca.crt` ConfigMap to the OpenShift AI trusted CA configuration:

```plaintext
oc get configmap kube-root-ca.crt -o jsonpath="{['data']['ca\.crt']}" >> /tmp/my-custom-ca-bundles.crt
```

Update the OpenShift AI trusted CA configuration to trust certificates issued by the certificate authorities in`kube-root-ca.crt`:

```plaintext
oc patch dscinitialization default-dsci --type='json' -p='[{"op":"replace","path":"/spec/trustedCABundle/customCABundle","value":"'"$(awk '{printf "%s\\n", $0}' /tmp/my-custom-ca-bundles.crt)"'"}]'
```

1. You have deployed an object storage solution or database in your OpenShift cluster.

Verification

- You can successfully deploy components that are configured to use object storage solutions or databases that are deployed in the OpenShift cluster. For example, a pipeline server that is configured to use a database deployed in the cluster starts successfully.

Note

You can verify your new certificate configuration by following the steps in the OpenShift AI tutorial - Fraud Detection example. Run the script to install local object storage buckets and create connections, and then enable data science pipelines.

For more information about running the script to install local object storage buckets, see [Running a script to install local object storage buckets and create connections](https://docs.redhat.com/en/documentation/red_hat_openshift_ai_cloud_service/1/html/openshift_ai_tutorial_-_fraud_detection_example/setting-up-a-project-and-storage#running-a-script-to-install-storage).

For more information about enabling data science pipelines, see [Enabling data science pipelines](https://docs.redhat.com/en/documentation/red_hat_openshift_ai_cloud_service/1/html/openshift_ai_tutorial_-_fraud_detection_example/setting-up-a-project-and-storage#enabling-data-science-pipelines).

#### 9.5.2. Configuring a certificate for data science pipelinesCopy link

By default, OpenShift AI includes OpenShift cluster-wide certificates in the`odh-trusted-ca-bundle` ConfigMap. These cluster-wide certificates cover most components, such as workbenches and model servers. However, the pipeline server might require additional Certificate Authority (CA) configuration, especially when interacting with external systems that use self-signed or custom certificates.

You have the following options for adding the certificate for data science pipelines:

- Add them to the cluster-wide CA bundle, as described in [Adding certificates to a cluster-wide CA bundle](https://docs.redhat.com/en/documentation/red_hat_openshift_ai_cloud_service/1/html/installing_and_uninstalling_openshift_ai_cloud_service/working-with-certificates_certs#adding-certificates-to-a-cluster-ca-bundle_certs).
- Alternatively, you can add certificates to a custom CA bundle, as described in [Adding certificates to a custom CA bundle](https://docs.redhat.com/en/documentation/red_hat_openshift_ai_cloud_service/1/html/installing_and_uninstalling_openshift_ai_cloud_service/working-with-certificates_certs#adding-certificates-to-a-custom-ca-bundle_certs).
- Provide a CA bundle that is only used for data science pipelines, as described in the following procedure.

Prerequisites

- You have cluster administrator access for the OpenShift cluster where Red Hat OpenShift AI is installed.
- You have created a self-signed certificate and saved the certificate to a file. For example, you have created a certificate using OpenSSL and saved it to a file named`example-ca.crt`.
- You have configured a data science pipeline server.

Procedure

From Workloads → ConfigMaps, create a ConfigMap with the required bundle in the same data science project as the target data science pipeline:

```plaintext
kind: ConfigMap
apiVersion: v1
metadata:
    name: custom-ca-bundle
data:
    ca-bundle.crt: |
    # contents of ca-bundle.crt
```

Add the following snippet to the`.spec.apiserver.caBundle` field of the underlying Data Science Pipelines Application (DSPA):

```plaintext
apiVersion: datasciencepipelinesapplications.opendatahub.io/v1
kind: DataSciencePipelinesApplication
metadata:
    name: data-science-dspa
spec:
    ...
    apiServer:
    ...
    cABundle:
        configMapName: custom-ca-bundle
        configMapKey: ca-bundle.crt
```

1. Log in to the OpenShift console.
2. Save the ConfigMap. The pipeline server pod automatically redeploys with the updated bundle.

Verification

Confirm that your CA bundle was successfully mounted:

1. Log in to the OpenShift console.
2. Go to the data science project that has the target data science pipeline.
3. Click the Pods tab.
4. Click the pipeline server pod with the`ds-pipeline-dspa- ` prefix.
5. Click Terminal.
6. Enter`cat /dsp-custom-certs/dsp-ca.crt`.
7. Verify that your CA bundle is present within this file.

#### 9.5.3. Configuring a certificate for workbenchesCopy link

Important

By default, self-signed certificates apply to workbenches that you create after configuring cluster-wide certificates. To apply cluster-wide certificates to an existing workbench, stop and then restart the workbench.

Self-signed certificates are stored in`/etc/pki/tls/custom-certs/ca-bundle.crt`. Workbenches use a preset environment variable that many popular HTTP client packages point to for certificates. For packages that are not included by default, you can provide this certificate path. For example, for the`kfp` package to connect to the data science pipeline server:

```plaintext
from kfp.client import Client

with open(sa_token_file_path, 'r') as token_file:
    bearer_token = token_file.read()

    client = Client(
        host='https://<GO_TO_ROUTER_OF_DS_PROJECT>/',
        existing_token=bearer_token,
        ssl_ca_cert='/etc/pki/tls/custom-certs/ca-bundle.crt'
    )
    print(client.list_experiments())
```

#### 9.5.4. Using the cluster-wide CA bundle for the single-model serving platformCopy link

By default, the single-model serving platform in OpenShift AI uses a self-signed certificate generated at installation for the endpoints that are created when deploying a server.

If you have configured cluster-wide certificates on your OpenShift cluster, they are used by default for other types of endpoints, such as endpoints for routes.

The following procedure explains how to use the same certificate that you already have for your OpenShift cluster.

Prerequisites

- You have cluster administrator access for the OpenShift cluster where Red Hat OpenShift AI is installed.
- You have configured cluster-wide certificates in OpenShift.
- You have configured the single-model serving platform, as described in [Installing the single-model serving platform](https://docs.redhat.com/en/documentation/red_hat_openshift_ai_cloud_service/1/html/installing_and_uninstalling_openshift_ai_cloud_service/installing-the-single-model-serving-platform_component-install).

Procedure

Edit the YAML code to keep only the relevant content, as shown in the following example. Replace`rhods-internal-primary-cert-bundle-secret` with the name of your secret:

```yaml
kind: Secret
apiVersion: v1
metadata:
name: rhods-internal-primary-cert-bundle-secret
data:
tls.crt: >-
    LS0tLS1CRUd...
tls.key: >-
    LS0tLS1CRUd...
type: kubernetes.io/tls
```

Edit the`kserve` configuration section to refer to your secret as shown in the following example. Replace`rhods-internal-primary-cert-bundle-secret` with the name of the secret that you created in Step 8.

```yaml
kserve:
devFlags: {}
managementState: Managed
serving:
    ingressGateway:
    certificate:
        secretName: rhods-internal-primary-cert-bundle-secret
        type: Provided
    managementState: Managed
    name: knative-serving
```

1. Log in to the OpenShift console.
2. From the list of projects, open the`openshift-ingress` project.
3. Click YAML.
4. Search for "cert" to find a secret with a name that includes "cert". For example,`rhods-internal-primary-cert-bundle-secret`. The contents of the secret should contain two items that are used for all OpenShift Routes:`tls.cert`(the certificate) and`tls.key`(the key).
5. Copy the reference to the secret.
6. From the list of projects, open the`istio-system` project.
7. Create a YAML file and paste the reference to the secret that you copied from the`openshift-ingress` YAML file.
8. Save the YAML file in the`istio-system` project.
9. Navigate to Operators → Installed Operators → Red Hat OpenShift AI.
10. Click Data Science Cluster*, and then click default-dsc → YAML.

### 9.6. Managing certificates without the Red Hat OpenShift AI OperatorCopy link

By default, the Red Hat OpenShift AI Operator manages the`odh-trusted-ca-bundle` ConfigMap, which contains the trusted CA bundle and is applied to all non-reserved namespaces in the cluster. The Operator automatically updates this ConfigMap whenever changes are made to the CA bundle.

If your organization prefers to manage trusted CA bundles independently, for example, by using Ansible automation, you can disable this default behavior to prevent automatic updates by the Red Hat OpenShift AI Operator.

Prerequisites

- You have cluster administrator privileges for your OpenShift cluster.

Procedure

In the`spec` section, change the value of the`managementState` field for`trustedCABundle` to`Unmanaged`, as shown:

```plaintext
spec:
  trustedCABundle:
    managementState: Unmanaged
```

Click Save.

Changing the`managementState` from`Managed` to`Unmanaged` prevents automatic updates when the`customCABundle` field is modified, but does not remove the`odh-trusted-ca-bundle` ConfigMap.

1. In the OpenShift web console, click Operators → Installed Operators and then click the Red Hat OpenShift AI Operator.
2. Click the DSC Initialization tab.
3. Click the default-dsci object.
4. Click the YAML tab.

Verification

In the`spec` section, set the value of the`customCABundle` field for`trustedCABundle`, for example:

```plaintext
spec:
  trustedCABundle:
    managementState: Unmanaged
    customCABundle: example123
```

1. Click Save.
2. Click Workloads → ConfigMaps.
3. Select a project from the project list.
4. Click the`odh-trusted-ca-bundle` ConfigMap.
5. Click the YAML tab and verify that the value of the`customCABundle` field did not update.

### 9.7. Removing the CA bundleCopy link

If you prefer to implement a different authentication approach for your OpenShift AI installation, you can override the default behavior by removing the CA bundle.

You have two options for removing the CA bundle:

- Remove the CA bundle from all non-reserved projects in OpenShift AI.
- Remove the CA bundle from a specific project.

#### 9.7.1. Removing the CA bundle from all namespacesCopy link

You can remove a Certificate Authority (CA) bundle from all non-reserved namespaces in OpenShift AI. This process changes the default configuration and disables the creation of the`odh-trusted-ca-bundle` configuration file (ConfigMap), as described in [Working with certificates](https://docs.redhat.com/en/documentation/red_hat_openshift_ai_cloud_service/1/html/installing_and_uninstalling_openshift_ai_cloud_service/working-with-certificates_certs).

Note

The`odh-trusted-ca-bundle` ConfigMaps are only deleted from namespaces when you set the`managementState` of`trustedCABundle` to`Removed`; deleting the DSC Initialization does not delete the ConfigMaps.

To remove a CA bundle from a single namespace only, see [Removing the CA bundle from a single namespace](https://docs.redhat.com/en/documentation/red_hat_openshift_ai_cloud_service/1/html/installing_and_uninstalling_openshift_ai_cloud_service/working-with-certificates_certs#removing-a-ca-bundle-from-a-namespace_certs).

Prerequisites

You have installed the OpenShift CLI (`oc`) as described in the appropriate documentation for your cluster:

- [Installing the OpenShift CLI](https://docs.redhat.com/en/documentation/openshift_dedicated/4/html/cli_tools/openshift-cli-oc#installing-openshift-cli) for OpenShift Dedicated
- [Installing the OpenShift CLI](https://docs.redhat.com/en/documentation/red_hat_openshift_service_on_aws_classic_architecture/4/html/cli_tools/openshift-cli-oc#installing-openshift-cli) for Red Hat OpenShift Service on AWS (classic architecture)

Procedure

In the`spec` section, change the value of the`managementState` field for`trustedCABundle` to`Removed`:

```plaintext
spec:
  trustedCABundle:
    managementState: Removed
```

1. In the OpenShift web console, click Operators → Installed Operators and then click the Red Hat OpenShift AI Operator.
2. Click the DSC Initialization tab.
3. Click the default-dsci object.
4. Click the YAML tab.
5. Click Save.

Verification

Run the following command to verify that the`odh-trusted-ca-bundle` ConfigMap has been removed from all namespaces:

```plaintext
oc get configmaps --all-namespaces | grep odh-trusted-ca-bundle
```

The command should not return any ConfigMaps.

#### 9.7.2. Removing the CA bundle from a single namespaceCopy link

You can remove a custom Certificate Authority (CA) bundle from individual namespaces in OpenShift AI. This process disables the creation of the`odh-trusted-ca-bundle` configuration file (ConfigMap) for the specified namespace only.

To remove a CA bundle from all namespaces, [Removing the CA bundle from all namespaces](https://docs.redhat.com/en/documentation/red_hat_openshift_ai_cloud_service/1/html/installing_and_uninstalling_openshift_ai_cloud_service/working-with-certificates_certs#removing-the-ca-bundle-from-all-namespaces_certs).

Prerequisites

You have installed the OpenShift CLI (`oc`) as described in the appropriate documentation for your cluster:

- [Installing the OpenShift CLI](https://docs.redhat.com/en/documentation/openshift_dedicated/4/html/cli_tools/openshift-cli-oc#installing-openshift-cli) for OpenShift Dedicated
- [Installing the OpenShift CLI](https://docs.redhat.com/en/documentation/red_hat_openshift_service_on_aws_classic_architecture/4/html/cli_tools/openshift-cli-oc#installing-openshift-cli) for Red Hat OpenShift Service on AWS (classic architecture)

Procedure

Run the following command to remove a CA bundle from a namespace. In the following command, example-namespace is the non-reserved namespace.

```plaintext
oc annotate ns example-namespace security.opendatahub.io/inject-trusted-ca-bundle=false
```

Verification

Run the following command to verify that the CA bundle has been removed from the namespace. In the following command, example-namespace is the non-reserved namespace.

```plaintext
oc get configmap odh-trusted-ca-bundle -n example-namespace
```

The command should return`configmaps "odh-trusted-ca-bundle" not found`.

## Chapter 10. Viewing logs and audit recordsCopy link

As a cluster administrator, you can use the OpenShift AI Operator logger to monitor and troubleshoot issues. You can also use OpenShift audit records to review a history of changes made to the OpenShift AI Operator configuration.

### 10.1. Configuring the OpenShift AI Operator loggerCopy link

You can change the log level for OpenShift AI Operator components by setting the`.spec.devFlags.logmode` flag for the DSC Initialization/`DSCI` custom resource during runtime. If you do not set a`logmode` value, the logger uses the INFO log level by default.

The log level that you set with`.spec.devFlags.logmode` applies to all components, not just those in a Managed state.

The following table shows the available log levels:

Expand

`devel` or`development`

WARN

INFO

Console

Epoch timestamps

`""`(or no`logmode` value set)

ERROR

INFO

JSON

Human-readable timestamps

`prod` or`production`

ERROR

INFO

JSON

Human-readable timestamps

| | Log level | Stacktrace level | Verbosity | Output | Timestamp type |
| --- | --- | --- | --- | --- | --- |

Show more

Logs that are set to`devel` or`development` generate in a plain text console format. Logs that are set to`prod`,`production`, or which do not have a level set generate in a JSON format.

Prerequisites

You have installed the OpenShift CLI (`oc`) as described in the appropriate documentation for your cluster:

- [Installing the OpenShift CLI](https://docs.redhat.com/en/documentation/openshift_dedicated/4/html/cli_tools/openshift-cli-oc#installing-openshift-cli) for OpenShift Dedicated
- [Installing the OpenShift CLI](https://docs.redhat.com/en/documentation/red_hat_openshift_service_on_aws_classic_architecture/4/html/cli_tools/openshift-cli-oc#installing-openshift-cli) for Red Hat OpenShift Service on AWS (classic architecture)

Procedure

In the`spec` section, update the`.spec.devFlags.logmode` flag with the log level that you want to set.

```plaintext
apiVersion: dscinitialization.opendatahub.io/v1
kind: DSCInitialization
metadata:
  name: default-dsci
spec:
  devFlags:
    logmode: development
```

1. Log in to the OpenShift as a cluster administrator.
2. Click Operators → Installed Operators and then click the Red Hat OpenShift AI Operator.
3. Click the DSC Initialization tab.
4. Click the default-dsci object.
5. Click the YAML tab.
6. Click Save.

You can also configure the log level from the OpenShift CLI (`oc`) by using the following command with the`logmode` value set to the log level that you want.

```plaintext
oc patch dsci default-dsci -p '{"spec":{"devFlags":{"logmode":"development"}}}' --type=merge
```

Verification

- If you set the component log level to`devel` or`development`, logs generate more frequently and include logs at`WARN` level and above.
- If you set the component log level to`prod` or`production`, or do not set a log level, logs generate less frequently and include logs at`ERROR` level or above.

#### 10.1.1. Viewing the OpenShift AI Operator logsCopy link

Run the following command to stream logs from all Operator pods:

```plaintext
for pod in $(oc get pods -l name=rhods-operator -n redhat-ods-operator -o name); do
  oc logs -f "$pod" -n redhat-ods-operator &
done
```

The Operator pod logs open in your terminal.

Tip

Press`Ctrl+C` to stop viewing. To fully stop all log streams, run`kill $(jobs -p)`.

1. Log in to the OpenShift CLI (`oc`).

You can also view each Operator pod log in the OpenShift console by navigating to Workloads → Pods, selecting the`redhat-ods-operator` project, clicking a pod name, and then clicking the Logs tab.

### 10.2. Viewing audit recordsCopy link

Cluster administrators can use OpenShift auditing to see changes made to the OpenShift AI Operator configuration by reviewing modifications to the DataScienceCluster (DSC) and DSCInitialization (DSCI) custom resources. Audit logging is enabled by default in standard OpenShift cluster configurations. For more information, see [Viewing audit logs](https://docs.redhat.com/en/documentation/openshift_container_platform/4.19/html/security_and_compliance/audit-log-view#audit-log-view) in the OpenShift documentation.

The following example shows how to use the OpenShift audit logs to see the history of changes made (by users) to the DSC and DSCI custom resources.

Prerequisites

You have installed the OpenShift CLI (`oc`) as described in the appropriate documentation for your cluster:

- [Installing the OpenShift CLI](https://docs.redhat.com/en/documentation/openshift_dedicated/4/html/cli_tools/openshift-cli-oc#installing-openshift-cli) for OpenShift Dedicated
- [Installing the OpenShift CLI](https://docs.redhat.com/en/documentation/red_hat_openshift_service_on_aws_classic_architecture/4/html/cli_tools/openshift-cli-oc#installing-openshift-cli) for Red Hat OpenShift Service on AWS (classic architecture)

Procedure

In a terminal window, if you are not already logged in to your OpenShift cluster as a cluster administrator, log in to the OpenShift CLI as shown in the following example:

```plaintext
$ oc login <openshift_cluster_url> -u <admin_username> -p <password>
```

Fetch the audit log files that are available for the relevant control plane nodes. For example:

```plaintext
oc adm node-logs --role=master --path=kube-apiserver/ \
  | awk '{ print $1 }' | sort -u \
  | while read node ; do
      oc adm node-logs $node --path=kube-apiserver/audit.log < /dev/null
    done \
  | grep opendatahub > /tmp/kube-apiserver-audit-opendatahub.log
```

Search the files for the DSC and DSCI custom resources. For example:

```plaintext
jq 'select((.objectRef.apiGroup == "dscinitialization.opendatahub.io"
                or .objectRef.apiGroup == "datasciencecluster.opendatahub.io")
              and .user.username != "system:serviceaccount:redhat-ods-operator:redhat-ods-operator-controller-manager"
              and .verb != "get" and .verb != "watch" and .verb != "list")' < /tmp/kube-apiserver-audit-opendatahub.log
```

1. To access the full content of the changed custom resources, set the OpenShift audit log policy to`WriteRequestBodies` or a more comprehensive profile. For more information, see [Configuring the audit log policy](https://docs.redhat.com/en/documentation/openshift_container_platform/4.19/html/security_and_compliance/audit-log-policy-config#configuring-audit-policy_audit-log-policy-config).

Verification

- The commands return relevant log entries.

Tip

To configure the log retention time, see the [Logging](https://docs.redhat.com/en/documentation/openshift_dedicated/4/html/logging/index) section in the OpenShift documentation.

## Chapter 11. Troubleshooting common installation problemsCopy link

If you are experiencing difficulties installing the Red Hat OpenShift AI Add-on, read this section to understand what could be causing the problem and how to resolve it.

If the problem is not included here or in the release notes, contact [Red Hat Support](https://access.redhat.com/support). When opening a support case, it is helpful to include debugging information about your cluster. You can collect this information by using the`must-gather` tool as described in [Must-Gather for Red Hat OpenShift AI](https://access.redhat.com/solutions/7061604) and [Gathering data about your cluster](https://docs.redhat.com/en/documentation/openshift_container_platform/latest/html/support/gathering-cluster-data).

You can also adjust the log level of OpenShift AI Operator components to increase or reduce log verbosity to suit your use case. For more information, see [Configuring the OpenShift AI Operator logger](https://docs.redhat.com/en/documentation/red_hat_openshift_ai_cloud_service/1/html/installing_and_uninstalling_openshift_ai_cloud_service/viewing-logs-and-audit-records_install#configuring-the-operator-logger_install).

### 11.1. The Red Hat OpenShift AI Operator cannot be retrieved from the image registryCopy link

Problem

When attempting to retrieve the Red Hat OpenShift AI Operator from the image registry, an`Failure to pull from quay` error message appears. The Red Hat OpenShift AI Operator might be unavailable for retrieval in the following circumstances:

- The image registry is unavailable.
- There is a problem with your network connection.
- Your cluster is not operational and is therefore unable to retrieve the image registry.

Diagnosis

Check the logs in the Events section in OpenShift for further information about the`Failure to pull from quay` error message.

Resolution

- Contact Red Hat support.

### 11.2. OpenShift AI cannot be installed due to insufficient cluster resourcesCopy link

Problem

When attempting to install OpenShift AI, an error message appears stating that installation prerequisites have not been met.

Diagnosis

Click Cluster List.

The Cluster List page opens.

Click the name of the cluster you want to install OpenShift AI on.

The Details page for the cluster opens.

1. Log in to Red Hat OpenShift Cluster Manager ([https://console.redhat.com/openshift/](https://console.redhat.com/openshift/)).
2. Click the Add-ons tab and locate the Red Hat OpenShift AI tile.
3. Click Install. The Configure Red Hat OpenShift AI pane appears.
4. If the installation fails, click the Prerequisites tab.
5. Note down the error message. If the error message states that you require a new machine pool, or that more resources are required, take the appropriate action to resolve the problem.

Resolution

- You might need to add more resources to your cluster, or increase the size of your machine pool. To increase your cluster’s resources, contact your infrastructure administrator. For more information about increasing the size of your machine pool, see [Nodes](https://docs.redhat.com/en/documentation/openshift_dedicated/4/html/nodes/index) and [Allocating additional resources to OpenShift AI users](https://docs.redhat.com/en/documentation/red_hat_openshift_ai_cloud_service/1/html/managing_openshift_ai/allocating-additional-resources-to-data-science-users_managing-rhoai).

### 11.3. OpenShift AI does not install on unsupported infrastructureCopy link

Problem

You are deploying on an environment that is not documented as supported by the Red Hat OpenShift AI Operator.

Diagnosis

Click the`rhods-operator- ` pod that shows an error in the Status column.

The Pod details page appears.

1. In the OpenShift web console, switch to the Administrator perspective.
2. Click Workloads → Pods.
3. Set the Project to All Projects or redhat-ods-operator.
4. Click Logs.
5. Select rhods-operator from the drop-down list.
6. Check the log for the`ERROR: Deploying on $infrastructure, which is not supported. Failing Installation` error message.

Resolution

- Before proceeding with a new installation, ensure that you have a fully supported environment on which to install OpenShift AI. For more information, see [Red Hat OpenShift AI: Supported Configurations](https://access.redhat.com/articles/rhoai-supported-configs).

### 11.4. The creation of the OpenShift AI Custom Resource (CR) failsCopy link

Problem

During the installation process, the OpenShift AI Custom Resource (CR) does not get created. This issue occurs in unknown circumstances.

Diagnosis

Click the`rhods-operator- ` pod that shows an error in the Status column.

The Pod details page appears.

1. In the OpenShift web console, switch to the Administrator perspective.
2. Click Workloads → Pods.
3. Set the Project to All Projects or redhat-ods-operator.
4. Click Logs.
5. Select rhods-operator from the drop-down list.
6. Check the log for the`ERROR: Attempt to create the ODH CR failed.` error message.

Resolution

- Contact Red Hat support.

### 11.5. The creation of the OpenShift AI Notebooks Custom Resource (CR) failsCopy link

Problem

During the installation process, the OpenShift AI Notebooks Custom Resource (CR) does not get created. This issue occurs in unknown circumstances.

Diagnosis

Click the`rhods-operator- ` pod that shows an error in the Status column.

The Pod details page appears.

1. In the OpenShift web console, switch to the Administrator perspective.
2. Click Workloads → Pods.
3. Set the Project to All Projects or redhat-ods-operator.
4. Click Logs.
5. Select rhods-operator from the drop-down list.
6. Check the log for the`ERROR: Attempt to create the RHODS Notebooks CR failed.` error message.

Resolution

- Contact Red Hat support.

### 11.6. The OpenShift AI dashboard is not accessibleCopy link

Problem

After installing OpenShift AI, the`redhat-ods-applications`,`redhat-ods-monitoring`, and`redhat-ods-operator` project namespaces are`Active` but you cannot access the dashboard due to an error in one of the pods.

Diagnosis

Click Filter and select the checkbox for every status except Running and Completed.

The page displays the pods that have an error.

1. In the OpenShift web console, switch to the Administrator perspective.
2. Click Workloads → Pods.
3. Set the Project to All Projects.

Resolution

- To see more information and troubleshooting steps for a pod, on the Pods page, click the link in the Status column for the pod.
- If the Status column does not display a link, click the pod name to open the pod details page and then click the Logs tab.

### 11.7. The dedicated-admins Role-based access control (RBAC) policy cannot be createdCopy link

Problem

The Role-based access control (RBAC) policy for the dedicated-admins group in the target project cannot be created. This issue occurs in unknown circumstances.

Diagnosis

Click the`rhods-operator- ` pod that shows an error in the Status column.

The Pod details page appears.

1. In the OpenShift web console, switch to the Administrator perspective.
2. Click Workloads → Pods.
3. Set the Project to All Projects or redhat-ods-operator.
4. Click Logs.
5. Select rhods-operator from the drop-down list.
6. Check the log for the`ERROR: Attempt to create the RBAC policy for dedicated admins group in $target_project failed.` error message.

Resolution

- Contact Red Hat support.

### 11.8. The Dead Man’s Snitch operator’s secret does not get createdCopy link

Problem

An issue with Managed Tenants SRE automation process causes the Dead Man’s Snitch operator’s secret to not get created.

Diagnosis

Click the`rhods-operator- ` pod that shows an error in the Status column.

The Pod details page appears.

1. In the OpenShift web console, switch to the Administrator perspective.
2. Click Workloads → Pods.
3. Set the Project to All Projects or redhat-ods-operator.
4. Click Logs.
5. Select rhods-operator from the drop-down list.
6. Check the log for the`ERROR: Dead Man Snitch secret does not exist.` error message.

Resolution

- Contact Red Hat support.

### 11.9. The PagerDuty secret does not get createdCopy link

Problem

An issue with Managed Tenants SRE automation process causes the PagerDuty’s secret to not get created.

Diagnosis

Click the`rhods-operator- ` pod that shows an error in the Status column.

The Pod details page appears.

1. In the OpenShift web console, switch to the Administrator perspective.
2. Click Workloads → Pods.
3. Set the Project to All Projects or redhat-ods-operator.
4. Click Logs.
5. Select rhods-operator from the drop-down list.
6. Check the log for the`ERROR: Pagerduty secret does not exist` error message.

Resolution

- Contact Red Hat support.

### 11.10. The SMTP secret does not existCopy link

Problem

An issue with Managed Tenants SRE automation process causes the SMTP secret to not get created.

Diagnosis

Click the`rhods-operator- ` pod that shows an error in the Status column.

The Pod details page appears.

1. In the OpenShift web console, switch to the Administrator perspective.
2. Click Workloads → Pods.
3. Set the Project to All Projects or redhat-ods-operator.
4. Click Logs.
5. Select rhods-operator from the drop-down list.
6. Check the log for the`ERROR: SMTP secret does not exist` error message.

Resolution

- Contact Red Hat support.

### 11.11. The ODH parameter secret does not get createdCopy link

Problem

An issue with the OpenShift AI Add-on’s flow could result in failure to create the ODH parameter.

Diagnosis

Click the`rhods-operator- ` pod that shows an error in the Status column.

The Pod details page appears.

1. In the OpenShift web console, switch to the Administrator perspective.
2. Click Workloads → Pods.
3. Set the Project to All Projects or redhat-ods-operator.
4. Click Logs.
5. Select rhods-operator from the drop-down list.
6. Check the log for the`ERROR: Addon managed odh parameter secret does not exist.` error message.

Resolution

- Contact Red Hat support.

## Chapter 12. Uninstalling OpenShift AICopy link

Use Red Hat OpenShift Cluster Manager to uninstall Red Hat OpenShift AI from your OpenShift cluster.

### 12.1. Understanding the uninstallation processCopy link

Installing Red Hat OpenShift AI created several custom resource instances on your OpenShift cluster for various components of OpenShift AI. After installation, users likely created several additional resources while using OpenShift AI. Uninstalling OpenShift AI removes the resources that were created by the Operator, but retains the resources created by users to prevent inadvertently deleting information you might want.

What is deleted

Uninstalling OpenShift AI removes the following resources from your OpenShift cluster:

- `DataScienceCluster` custom resource instance and the custom resource instances it created for each component
- `DSCInitialization` custom resource instance
- `Auth` custom resource instance created during or after installation
- `FeatureTracker` custom resource instances created during or after installation
- `ServiceMesh` custom resource instance created by the Operator during or after installation
- `KNativeServing` custom resource instance created by the Operator during or after installation
- `redhat-ods-applications`,`redhat-ods-monitoring`, and`rhods-notebooks` namespaces created by the Operator
- Workloads in the`rhods-notebooks` namespace
- `Subscription`,`ClusterServiceVersion`, and`InstallPlan` objects
- `KfDef` object (version 1 Operator only)

What might remain

Uninstalling OpenShift AI retains the following resources in your OpenShift cluster:

- Data science projects created by users
- Custom resource instances created by users
- Custom resource definitions (CRDs) created by users or by the Operator

While these resources might still remain in your OpenShift cluster, they are not functional. After uninstalling, Red Hat recommends that you review the data science projects and custom resources in your OpenShift cluster and delete anything no longer in use to prevent potential issues, such as pipelines that cannot run, notebooks that cannot be undeployed, or models that cannot be undeployed.

Additional resources

[Operator Lifecycle Manager (OLM) uninstall documentation](https://olm.operatorframework.io/docs/tasks/uninstall-operator/)

### 12.2. Backing up storage data from Amazon EBSCopy link

Red Hat recommends that you back up the data on your persistent volume claims (PVCs) regularly. Backing up your data is particularly important before deleting a user and before uninstalling OpenShift AI, as all PVCs are deleted when you uninstall OpenShift AI.

Prerequisites

- You have credentials for Red Hat OpenShift Cluster Manager ([https://console.redhat.com/openshift/](https://console.redhat.com/openshift/)).
- You have administrator access to the OpenShift Dedicated cluster.
- You have credentials for the Amazon Web Services (AWS) account that the OpenShift Dedicated cluster is deployed under.

Procedure

Determine the IDs of the persistent volumes (PVs) that you want to back up.

Click the rhods-notebooks project.

The Details page for the project opens.

Click the PersistentVolumeClaims in the Inventory section.

The PersistentVolumeClaims page opens.

Note the ID of the persistent volume (PV) that you want to back up.

Note

The persistent volumes (PV) that you make a note of are required to identify the correct EBS volume to back up in your AWS instance.

1. In the OpenShift Dedicated web console, change into the Administrator perspective.
2. Click Home → Projects.

Locate the EBS volume containing the PVs that you want to back up.

See [Amazon Web Services documentation: Create Amazon EBS snapshots](https://docs.aws.amazon.com/AWSEC2/latest/UserGuide/ebs-creating-snapshot.html) for more information.

Click Elastic Block Storage → Volumes in the side navigation.

The Volumes page opens.

In the search bar, enter the ID of the persistent volume (PV) that you made a note of earlier.

The Volumes page reloads to display the search results.

1. Log in to AWS ([https://aws.amazon.com](https://aws.amazon.com/)) and ensure that you are viewing the region that your OpenShift Dedicated cluster is deployed in.
2. Click Services.
3. Click Compute → EC2.
4. Click the volume shown and verify that any`kubernetes.io/created-for/pvc/namespace` tags contain the value`rhods-notebooks`, and any`kubernetes.io/created-for/pvc/name` tags match the name of the persistent volume that the EC2 volume is being used for, for example,`jupyter-nb-user1-pvc`.

Back up the EBS volume that contains your persistent volume (PV).

Right-click the volume that you want to back up and select Create Snapshot from the list.

The Create Snapshot page opens.

Click Create Snapshot.

The snapshot of the volume is created.

1. Enter a Description for the volume.
2. Click Close.

Verification

- The snapshot that you created is visible on the Snapshots page in AWS.

### 12.3. Backing up storage data from Google Persistent DiskCopy link

Red Hat recommends that you back up the data on your persistent volume claims (PVCs) regularly. Backing up your data is particularly important before deleting a user and before uninstalling OpenShift AI, as all PVCs are deleted when OpenShift AI is uninstalled.

Prerequisites

- You have credentials for Red Hat OpenShift Cluster Manager ([https://console.redhat.com/openshift/](https://console.redhat.com/openshift/)).
- You have administrator access to the OpenShift Dedicated cluster.
- You have credentials for the Google Cloud Platform (GCP) account that the OpenShift Dedicated cluster is deployed under.

Procedure

Determine the IDs of the persistent volumes (PVs) that you want to back up.

Click the rhods-notebooks project.

The Details page for the project opens.

Click the PersistentVolumeClaims in the Inventory section.

The PersistentVolumeClaims page opens.

Note the ID of the persistent volume (PV) that you want to back up.

The persistent volume (PV) IDs are required to identify the correct persistent disk to back up in your GCP instance.

1. In the OpenShift Dedicated web console, change into the Administrator perspective.
2. Click Home → Projects.

Locate the persistent disk containing the PVs that you want to back up.

From the side navigation, under Storage, click Disks.

The Disks page opens.

In the Filter query box, enter the ID of the persistent volume (PV) that you made a note of earlier.

The Disks page reloads to display the search results.

1. Log in to the Google Cloud console ([https://console.cloud.google.com](https://console.cloud.google.com/)) and ensure that you are viewing the region that your OpenShift Dedicated cluster is deployed in.
2. Click the navigation menu (≡) and then click Compute Engine.
3. Click the disk shown and verify that any`kubernetes.io/created-for/pvc/namespace` tags contain the value`rhods-notebooks`, and any`kubernetes.io/created-for/pvc/name` tags match the name of the persistent volume that the persistent disk is being used for, for example,`jupyterhub-nb-user1-pvc`.

Back up the persistent disk that contains your persistent volume (PV).

Select CREATE SNAPSHOT from the top navigation.

The Create a snapshot page opens.

Click CREATE.

The snapshot of the persistent disk is created.

1. Enter a unique Name for the snapshot.
2. Under Source disk, verify the persistent disk you want to back up is displayed.
3. Change any optional settings as needed.

Verification

- The snapshot that you created is visible on the Snapshots page in GCP.

### 12.4. Uninstalling OpenShift AICopy link

You can use Red Hat OpenShift Cluster Manager to safely uninstall Red Hat OpenShift AI from your OpenShift cluster.

Prerequisites

- Credentials for Red Hat OpenShift Cluster Manager ([https://console.redhat.com/openshift/](https://console.redhat.com/openshift/)).
- Administrator access to the OpenShift cluster.
- For AWS clusters, you have backed up the EBS volume containing your Persistent Volume Claims (PVCs). See [Amazon Web Services documentation: Create Amazon EBS snapshots](https://docs.aws.amazon.com/AWSEC2/latest/UserGuide/ebs-creating-snapshot.html) for more information.
- For GCP clusters, you have backed up the persistent disk containing your Persistent Volume Claims (PVCs). See [Google Cloud documentation: Create and manage disk snapshots](https://cloud.google.com/compute/docs/disks/create-snapshots) for more information.

Procedure

Click Cluster List.

The Cluster List page opens.

Click the name of the cluster that hosts the instance OpenShift AI to uninstall.

The Details page for the cluster opens.

Click Uninstall.

This process takes approximately 30 minutes to complete. Do not manually delete any resources while uninstalling OpenShift AI, as this can interfere with the uninstall process.

OpenShift AI is uninstalled and any persistent volume claims (PVCs) associated with your OpenShift AI instance are deleted. However, any user groups for OpenShift AI that you previously created remain on your cluster.

1. Log in to Red Hat OpenShift Cluster Manager ([https://console.redhat.com/openshift/](https://console.redhat.com/openshift/)).
2. Click the Add-ons tab and locate the Red Hat OpenShift AI tile.

Verification

In your OpenShift cluster, click Home → Projects and confirm that the following project namespaces are not visible:

- `redhat-ods-applications`
- `redhat-ods-monitoring`
- `redhat-ods-operator`

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



## Getting started

Getting started with Red Hat OpenShift AI Cloud Service | Getting started with Red Hat OpenShift AI Cloud Service | Red Hat OpenShift AI Cloud Service | 1 | Red Hat Documentation

 

 

 

 

 

 

 Updates for Red Hat OpenShift AI Cloud Service are only provided up to the end of October 2025. 

 

# Getting started with Red Hat OpenShift AI Cloud Service

---

Red Hat OpenShift AI Cloud Service 1

## Learn how to work in an OpenShift AI environment

Legal Notice

Abstract

Learn how to work in an OpenShift AI environment.

---

## Chapter 1. OverviewCopy link

Red Hat OpenShift AI is an artificial intelligence (AI) platform that provides tools to rapidly train, serve, and monitor machine learning (ML) models onsite, in the public cloud, or at the edge.

OpenShift AI provides a powerful AI/ML platform for building AI-enabled applications. Data scientists and MLOps engineers can collaborate to move from experiment to production in a consistent environment quickly.

You can deploy OpenShift AI on any supported version of OpenShift, whether on-premise, in the cloud, or in disconnected environments. For details on supported versions, see [Red Hat OpenShift AI: Supported Configurations](https://access.redhat.com/articles/rhoai-supported-configs).

### 1.1. Data science workflowCopy link

For the purpose of getting you started with OpenShift AI, the following figure illustrates a simplified data science workflow. The real world process of developing ML models is an iterative one.

Figure 1.1. Simplified data science workflow

The simplified data science workflow for predictive AI use cases includes the following tasks:

- Legal Notice
- Defining your business problem and setting goals to solve it.
- Gathering, cleaning, and preparing data. Data often has to be federated from a range of sources, and exploring and understanding data plays a key role in the success of a data science project.
- Evaluating and selecting ML models for your business use case.
- Train models for your business use case by tuning model parameters based on your set of training data. In practice, data scientists train a range of models, and compare performance while considering tradeoffs such as time and memory constraints.
- Integrate models into an application, including deployment and testing. After model training, the next step of the workflow is production. Data scientists are often responsible for putting the model in production and making it accessible so that a developer can integrate the model into an application.
- Monitor and manage deployed models. Depending on the organization, data scientists, data engineers, or ML engineers must monitor the performance of models in production, tracking prediction and performance metrics.
- Refine and retrain models. Data scientists can evaluate model performance results and refine models to improve outcome by excluding or including features, changing the training data, and modifying other configuration parameters.

### 1.2. About this guideCopy link

This guide assumes you are familiar with data science and ML Ops concepts. It describes the following tasks to get you started with using OpenShift AI:

Learn where to get information about the next steps:

- Developing and training a model
- Automating the workflow with pipelines
- Implementing distributed workloads
- Testing your model
- Deploying your model
- Monitoring and managing your model

See also [OpenShift AI tutorial: Fraud detection example](https://docs.redhat.com/en/documentation/red_hat_openshift_ai_cloud_service/1/html/openshift_ai_tutorial_-_fraud_detection_example/index). It provides step-by-step guidance for using OpenShift AI to develop and train an example model in JupyterLab, deploy the model, and refine the model by using automated pipelines.

### 1.3. Glossary of common termsCopy link

This glossary defines common terms for Red Hat OpenShift AI.

accelerator In high-performance computing, a specialized circuit that is used to take some of the computational load from the CPU, increasing the efficiency of the system. For example, in deep learning, GPU-accelerated computing is often employed to offload part of the compute workload to a GPU while the main application runs off the CPU. artificial intelligence (AI) The capability to acquire, process, create and apply knowledge in the form of a model to make predictions, recommendations or decisions. bias detection The process of calculating fairness metrics to detect when AI models are delivering unfair outcomes based on certain attributes. custom resource (CR) A resource implemented through the Kubernetes CustomResourceDefinition API. A custom resource is distinct from the built-in Kubernetes resources, such as the pod and service resources. Every CR is part of an API group. custom resource definition (CRD) In Red Hat OpenShift, a custom resource definition (CRD) defines a new, unique object`Kind` in the cluster and lets the Kubernetes API server handle its entire lifecycle. connections A configuration that stores the parameters required to connect to an S3-compatible object storage, database or OCI-compliant container registry from a data science project. connection type The type of external source to connect to from a data science project, such as an OCI-compliant container registry, S3-compatible object storage, or Uniform Resource Identifiers (URIs). data science pipelines A workflow engine that is used by data scientists and AI engineers to automate pipelines, such as model training and evaluation pipelines. Data science pipelines also includes experiment tracking capabilities, artifact storage, and versioning. data science project An OpenShift project for organizing data science work. Each project is scoped to its own Kubernetes namespace. disconnected environment An environment on a restricted network that does not have an active connection to the internet. distributed workloads Data science workloads that are run simultaneously across multiple nodes in an OpenShift cluster. fine-tuning The process of adapting a pre-trained model to perform a specific task by conducting additional training. Fine tuning may involve (1) updating the model’s existing parameters, known as full fine tuning, or (2) updating a subset of the model’s existing parameters or adding new parameters to the model and training them while freezing the model’s existing parameters, known as parameter-efficient fine tuning. graphics processing unit (GPU) A specialized processor designed to rapidly manipulate and alter memory to accelerate the creation of images in a frame buffer intended for output to a display. GPUs are heavily utilized in machine learning due to their parallel processing capabilities. inference The process of using a trained AI model to generate predictions or conclusions based on the input data provided to the model. inference server A server that performs inference. Inference servers feed the input requests through a machine learning model and return an output. large language model (LLM) A language model with a large number of parameters, trained on a large quantity of text. machine learning (ML) A branch of artificial intelligence (AI) and computer science that focuses on the use of data and algorithms to imitate the way that humans learn, gradually improving the accuracy of AI models. model In a machine learning context, a set of functions and algorithms that have been trained and tested on a data set to provide predictions or decisions. model registry A central repository containing metadata related to machine learning models from inception to deployment. The metadata ranges from high-level information such as the deployment environment and project origins, to intricate details like training hyperparameters, performance metrics, and deployment events. model server A container that hosts a machine learning model, exposes an API to handle incoming requests, performs inference, and returns model predictions. model-serving runtime A component or framework that helps create model servers for deploying machine learning models and build APIs optimized for inference. MLOps The practice for collaboration between data scientists and operations professionals to help manage the production machine learning (or deep learning) lifecycle. MLOps looks to increase automation and improve the quality of production ML while also focusing on business and regulatory requirements. It involves model development, training, validation, deployment, monitoring, and management and uses methods like CI/CD. notebook interface An interactive document that contains executable code, descriptive text for that code, and the results of any code that is run. object storage A method of storing data, typically used in the cloud, in which data is stored as discrete units, or objects, in a storage pool or repository that does not use a file hierarchy but that stores all objects at the same level. OpenShift Container Platform cluster A group of physical machines that contains the controllers, pods, services, and configurations required to build and run containerized applications. persistent storage A persistent volume that retains files, models or other artifacts across components such as model deployments, data science pipelines and workbenches. persistent volume claim (PVC) A persistent volume claim (PVC) is a request for storage in the cluster by a user. quantization A method of compressing foundation model weights to speed up inferencing and reduce memory needs. serving The process of hosting a trained machine learning model as a network-accessible service. Real-world applications can send inference requests to the service by using a REST or gRPC API and receive predictions. ServingRuntime A custom resource definition (CRD) that defines the templates for pods that can serve one or more particular model formats. Each ServingRuntime CRD defines key information such as the container image of the runtime and a list of the model formats that the runtime supports. Other configuration settings for the runtime can be conveyed through environment variables in the container specification. It also dynamically loads and unloads models from disk into memory on demand and exposes a gRPC service endpoint to serve inferencing requests for loaded models. vLLM A high-throughput and efficient inference engine for running large-language models that integrates with popular models and frameworks. workbench An isolated environment for development and experimentation with ML models. Workbenches typically contain integrated development environments (IDEs), such as JupyterLab, RStudio, and Visual Studio Code. workbench image An image that includes preinstalled tools and libraries that you need for model development. Includes an IDE for developing your machine learning (ML) models. YAML A human-readable data-serialization language. It is commonly used for configuration files and in applications where data is being stored or transmitted.

## Chapter 2. Logging in to OpenShift AICopy link

After you install OpenShift AI, log in to the OpenShift AI dashboard so that you can set up your development and deployment environment.

Prerequisites

You know the OpenShift AI identity provider and your login credentials.

If you are a data scientist, data engineer, or ML engineer, your administrator must provide you with the OpenShift AI instance URL, for example:

```plaintext
https://rhoai-dashboard-redhat-oai-applications.apps.example.abc1.p1.openshiftapps.com/
```

You have the latest version of one of the following supported browsers:

- Google Chrome
- Mozilla Firefox
- Safari

Procedure

Browse to the OpenShift AI instance URL and click Log in with OpenShift.

- If you have access to OpenShift, you can browse to the OpenShift web console and click the Application Launcher () → Red Hat OpenShift AI.

Verification

- Enter your credentials and click Log in (or equivalent for your identity provider).
- The OpenShift AI dashboard opens on the Home page.

### 2.1. Viewing installed OpenShift AI componentsCopy link

In the Red Hat OpenShift AI dashboard, you can view a list of the installed OpenShift AI components, their corresponding source (upstream) components, and the versions of the installed components.

Prerequisites

- OpenShift AI is installed in your OpenShift cluster.

Procedure

1. Log in to the OpenShift AI dashboard.
2. In the top navigation bar, click the help icon () and then select About.

Verification

The About page shows a list of the installed OpenShift AI components along with their corresponding upstream components and upstream component versions.

Additional resources

- [Installing and managing Red Hat OpenShift AI components](https://docs.redhat.com/en/documentation/red_hat_openshift_ai_cloud_service/1/html/installing_and_uninstalling_openshift_ai_cloud_service/installing-and-deploying-openshift-ai_install#installing-and-managing-openshift-ai-components_component-install).

## Chapter 3. Creating a data science projectCopy link

To implement a data science workflow, you must create a project. In OpenShift, a project is a Kubernetes namespace with additional annotations, and is the main way that you can manage user access to resources. A project organizes your data science work in one place and also allows you to collaborate with other developers and data scientists in your organization.

Within a project, you can add the following functionality:

- Connections so that you can access data without having to hardcode information like endpoints or credentials.
- Workbenches for working with and processing data, and for developing models.
- Deployed models so that you can test them and then integrate them into intelligent applications. Deploying a model makes it available as a service that you can access by using an API.
- Pipelines for automating your ML workflow.

Prerequisites

- You have logged in to Red Hat OpenShift AI.
- You have the appropriate roles and permissions to create projects.

Procedure

From the OpenShift AI dashboard, select Data science projects.

The Data science projects page shows a list of projects that you can access. For each user-requested project in the list, the Name column shows the project display name, the user who requested the project, and the project description.

Optional: If you want to change the default resource name for your project, click Edit resource name.

The resource name is what your resource is labeled in OpenShift. Valid characters include lowercase letters, numbers, and hyphens (-). The resource name cannot exceed 30 characters, and it must start with a letter and end with a letter or number.

Note: You cannot change the resource name after the project is created. You can edit only the display name and the description.

1. Click Create project.
2. In the Create project dialog, update the Name field to enter a unique display name for your project.
3. Optional: In the Description field, provide a project description.
4. Click Create.

Verification

- A project details page opens. From this page, you can add connections, create workbenches, configure pipelines, and deploy models.

## Chapter 4. Creating a workbench and selecting an IDECopy link

A workbench is an isolated area where you can examine and work with ML models. You can also work with data and run programs, for example to prepare and clean data. While a workbench is not required if, for example, you only want to service an existing model, one is needed for most data science workflow tasks, such as writing code to process data or training a model.

When you create a workbench, you specify an image (an IDE, packages, and other dependencies). Supported IDEs include JupyterLab, code-server, and RStudio (Technology Preview).

The IDEs are based on a server-client architecture. Each IDE provides a server that runs in a container on the OpenShift cluster, while the user interface (the client) is displayed in your web browser. For example, the Jupyter workbench runs in a container on the Red Hat OpenShift cluster. The client is the JupyterLab interface that opens in your web browser on your local computer. All of the commands that you enter in JupyterLab are executed by the workbench. Similarly, other IDEs like code-server or RStudio Server provide a server that runs in a container on the OpenShift cluster, while the user interface is displayed in your web browser. This architecture allows you to interact through your local computer in a browser environment, while all processing occurs on the cluster. The cluster provides the benefits of larger available resources and security because the data being processed never leaves the cluster.

In a workbench, you can also configure connections (to access external data for training models and to save models so that you can deploy them) and cluster storage (for persisting data). Workbenches within the same project can share models and data through object storage with the data science pipelines and model servers.

For data science projects that require data retention, you can add container storage to the workbench you are creating.

Within a project, you can create multiple workbenches. When to create a new workbench depends on considerations, such as the following:

- The workbench configuration (for example, CPU, RAM, or IDE). If you want to avoid editing the configuration of an existing workbench’s configuration to accommodate a new task, you can create a new workbench instead.
- Separation of tasks or activities. For example, you might want to use one workbench for your Large Language Models (LLM) experimentation activities, another workbench dedicated to a demo, and another workbench for testing.

### 4.1. About workbench imagesCopy link

A workbench image is preinstalled with the tools and libraries that you need for model development. You can use the provided workbench images or an OpenShift AI administrator can create custom workbench images adapted to your needs.

To provide a consistent, stable platform for your model development, many provided workbench images contain the same version of Python. Most workbench images available on OpenShift AI are pre-built and ready for you to use immediately after OpenShift AI is installed or upgraded.

For information about Red Hat support of workbench images and packages, see [Red Hat OpenShift AI: Supported Configurations](https://access.redhat.com/articles/rhoai-supported-configs).

The following table lists the workbench images that are installed with Red Hat OpenShift AI by default.

If the preinstalled packages that are provided in these images are not sufficient for your use case, you have the following options:

- Install additional libraries after launching a default image. This option is good if you want to add libraries on an ad hoc basis as you develop models. However, it can be challenging to manage the dependencies of installed libraries and your changes are not saved when the workbench restarts.
- Create a custom image that includes the additional libraries or packages. For more information, see [Creating custom workbench images](https://docs.redhat.com/en/documentation/red_hat_openshift_ai_cloud_service/1/html/managing_openshift_ai/creating-custom-workbench-images).

Important

Workbench images denoted with`(Technology Preview)` in this table are not supported with Red Hat production service level agreements (SLAs) and might not be functionally complete. Red Hat does not recommend using Technology Preview features in production. These features provide early access to upcoming product features, enabling customers to test functionality and provide feedback during the development process. For more information about the support scope of Red Hat Technology Preview features, see [Technology Preview Features Support Scope](https://access.redhat.com/support/offerings/techpreview/).

Expand

CUDA

If you are working with compute-intensive data science models that require GPU support, use the Compute Unified Device Architecture (CUDA) workbench image to gain access to the NVIDIA CUDA Toolkit. Using this toolkit, you can optimize your work by using GPU-accelerated libraries and optimization tools.

Standard Data Science

Use the Standard Data Science workbench image for models that do not require TensorFlow or PyTorch. This image contains commonly-used libraries to assist you in developing your machine learning models.

TensorFlow

TensorFlow is an open source platform for machine learning. With TensorFlow, you can build, train and deploy your machine learning models. TensorFlow contains advanced data visualization features, such as computational graph visualizations. It also allows you to easily monitor and track the progress of your models.

PyTorch

PyTorch is an open source machine learning library optimized for deep learning. If you are working with computer vision or natural language processing models, use the Pytorch workbench image.

Minimal Python

If you do not require advanced machine learning features, or additional resources for compute-intensive data science work, you can use the Minimal Python image to develop your models.

TrustyAI

Use the TrustyAI workbench image to leverage your data science work with model explainability, tracing, and accountability, and runtime monitoring. See the [TrustyAI Explainability repository](https://github.com/trustyai-explainability/trustyai-explainability-python-examples) for some example Jupyter notebooks.

code-server

With the code-server workbench image, you can customize your workbench environment to meet your needs using a variety of extensions to add new languages, themes, debuggers, and connect to additional services. Enhance the efficiency of your data science work with syntax highlighting, auto-indentation, and bracket matching, as well as an automatic task runner for seamless automation. For more information, see [code-server in GitHub](https://github.com/coder/code-server).

NOTE: Elyra-based pipelines are not available with the code-server workbench image.

RStudio Server (Technology preview)

Use the RStudio Server workbench image to access the RStudio IDE, an integrated development environment for R, a programming language for statistical computing and graphics. For more information, see [the RStudio Server site](https://posit.co/products/open-source/rstudio-server/).

To use the RStudio Server workbench image, you must first build it by creating a secret and triggering the BuildConfig, and then enable it in the OpenShift AI UI by editing the`rstudio-rhel9` image stream. For more information, see [Building the RStudio Server workbench images](https://docs.redhat.com/en/documentation/red_hat_openshift_ai_cloud_service/1/html/getting_started_with_red_hat_openshift_ai_cloud_service/creating-a-workbench-select-ide_get-started#building-the-rstudio-server-workbench-images_get-started).

Important

Disclaimer: Red Hat supports managing workbenches in OpenShift AI. However, Red Hat does not provide support for the RStudio software. RStudio Server is available through [https://rstudio.org/](https://rstudio.org/) and is subject to RStudio licensing terms. Review the licensing terms before you use this sample workbench.

CUDA - RStudio Server (Technology Preview)

Use the CUDA - RStudio Server workbench image to access the RStudio IDE and NVIDIA CUDA Toolkit. RStudio is an integrated development environment for R, a programming language for statistical computing and graphics. With the NVIDIA CUDA toolkit, you can optimize your work using GPU-accelerated libraries and optimization tools. For more information, see [the RStudio Server site](https://posit.co/products/open-source/rstudio-server/).

To use the CUDA - RStudio Server workbench image, you must first build it by creating a secret and triggering the BuildConfig, and then enable it in the OpenShift AI UI by editing the`cuda-rstudio-rhel9` image stream. For more information, see [Building the RStudio Server workbench images](https://docs.redhat.com/en/documentation/red_hat_openshift_ai_cloud_service/1/html/getting_started_with_red_hat_openshift_ai_cloud_service/creating-a-workbench-select-ide_get-started#building-the-rstudio-server-workbench-images_get-started).

Important

Disclaimer: Red Hat supports managing workbenches in OpenShift AI. However, Red Hat does not provide support for the RStudio software. RStudio Server is available through [https://rstudio.org/](https://rstudio.org/) and is subject to RStudio licensing terms. Review the licensing terms before you use this sample workbench.

The CUDA - RStudio Server workbench image contains NVIDIA CUDA technology. CUDA licensing information is available at [https://docs.nvidia.com/cuda/](https://docs.nvidia.com/cuda/). Review the licensing terms before you use this sample workbench.

ROCm

Use the ROCm workbench image to run AI and machine learning workloads on AMD GPUs in OpenShift AI. It includes ROCm libraries and tools optimized for high-performance GPU acceleration, supporting custom AI workflows and data processing tasks. Use this image integrating additional frameworks or dependencies tailored to your specific AI development needs.

ROCm-PyTorch

Use the ROCm-PyTorch workbench image to run PyTorch workloads on AMD GPUs in OpenShift AI. It includes ROCm-accelerated PyTorch libraries, enabling efficient deep learning training, inference, and experimentation. This image is designed for data scientists working with PyTorch-based workflows, offering integration with GPU scheduling.

ROCm-TensorFlow

Use the ROCm-TensorFlow workbench image to run TensorFlow workloads on AMD GPUs in OpenShift AI. It includes ROCm-accelerated TensorFlow libraries to support high-performance deep learning model training and inference. This image simplifies TensorFlow development on AMD GPUs and integrates with OpenShift AI for resource scaling and management.

| Table 4.1. Default workbench images | Image name | Description |
| --- | --- | --- |

Show more

### 4.2. Building the RStudio Server workbench imagesCopy link

Important

The RStudio Server and CUDA - RStudio Server workbench images are currently available in Red Hat OpenShift AI as Technology Preview features.

Technology Preview features are not supported with Red Hat production service level agreements (SLAs) and might not be functionally complete. Red Hat does not recommend using them in production. These features provide early access to upcoming product features, enabling customers to test functionality and provide feedback during the development process.

For more information about the support scope of Red Hat Technology Preview features, see [Technology Preview Features Support Scope](https://access.redhat.com/support/offerings/techpreview/).

Red Hat OpenShift AI includes the following RStudio Server workbench images:

RStudio Server workbench image

With the RStudio Server workbench image, you can access the RStudio IDE, an integrated development environment for the R programming language. R is used for statistical computing and graphics to support data analysis and predictions.

Important

Disclaimer: Red Hat supports managing workbenches in OpenShift AI. However, Red Hat does not provide support for the RStudio software. RStudio Server is available through [rstudio.org](https://rstudio.org/) and is subject to their licensing terms. You should review their licensing terms before you use this sample workbench.

CUDA - RStudio Server workbench image

With the CUDA - RStudio Server workbench image, you can access the RStudio IDE and NVIDIA CUDA Toolkit. The RStudio IDE is an integrated development environment for the R programming language for statistical computing and graphics. With the NVIDIA CUDA toolkit, you can enhance your work by using GPU-accelerated libraries and optimization tools.

Important

Disclaimer: Red Hat supports managing workbenches in OpenShift AI. However, Red Hat does not provide support for the RStudio software. RStudio Server is available through [rstudio.org](https://rstudio.org/) and is subject to their licensing terms. You should review their licensing terms before you use this sample workbench.

The CUDA - RStudio Server workbench image contains NVIDIA CUDA technology. CUDA licensing information is available in the [CUDA Toolkit documentation](https://docs.nvidia.com/cuda/). You should review their licensing terms before you use this sample workbench.

To use the RStudio Server and CUDA - RStudio Server workbench images, you must first build them by creating a secret and triggering the`BuildConfig`, and then enable them in the OpenShift AI UI by editing the`rstudio-rhel9` and`cuda-rstudio-rhel9` image streams.

Prerequisites

- Before starting the RStudio Server build process, you have at least 1 CPU and 2Gi memory available for`rstudio-server-rhel9`, and 1.5 CPUs and 8Gi memory available for`cuda-rstudio-server-rhel9` on your cluster.
- You are logged in to your OpenShift cluster.
- You have the`cluster-admin` role in OpenShift.
- You have an active Red Hat Enterprise Linux (RHEL) subscription.

Procedure

Create a secret with Subscription Manager credentials. These are usually your Red Hat Customer Portal username and password.

Note: The secret must be named`rhel-subscription-secret`, and its`USERNAME` and`PASSWORD` keys must be in capital letters.

```plaintext
oc create secret generic rhel-subscription-secret --from-literal=USERNAME=<username> --from-literal=PASSWORD=<password> -n redhat-ods-applications
```

Start the build:

To start the lightweight RStudio Server build:

```plaintext
oc start-build rstudio-server-rhel9 -n redhat-ods-applications --follow
```

To start the CUDA-enabled RStudio Server build, trigger the`cuda-rstudio-server-rhel9` BuildConfig:

```plaintext
oc start-build cuda-rstudio-server-rhel9 -n redhat-ods-applications --follow
```

Confirm that the build process has completed successfully using the following command. Successful builds appear as`Complete`.

```plaintext
oc get builds -n redhat-ods-applications
```

After the builds complete successfully, use the following commands to make the workbench images available in the OpenShift AI UI.

To enable the RStudio Server workbench image:

```plaintext
oc label -n redhat-ods-applications imagestream rstudio-rhel9 opendatahub.io/notebook-image='true'
```

To enable the CUDA - RStudio Server workbench image:

```plaintext
oc label -n redhat-ods-applications imagestream cuda-rstudio-rhel9 opendatahub.io/notebook-image='true'
```

Verification

- You can see RStudio Server and CUDA - RStudio Server images on the Applications → Enabled menu in the Red Hat OpenShift AI dashboard.
- You can see R Studio Server or CUDA - RStudio Server in the Data science projects → Workbenches → Create workbench → Notebook image → Image selection dropdown list.

### 4.3. Creating a workbenchCopy link

When you create a workbench, you specify an image (an IDE, packages, and other dependencies). You can also configure connections, cluster storage, and add container storage.

Prerequisites

If you created a Simple Storage Service (S3) account outside of Red Hat OpenShift AI and you want to create connections to your existing S3 storage buckets, you have the following credential information for the storage buckets:

- Endpoint URL
- Access key
- Secret key
- Region
- Bucket name

For more information, see [Working with data in an S3-compatible object store](https://docs.redhat.com/en/documentation/red_hat_openshift_ai_cloud_service/1/html/working_with_data_in_an_s3-compatible_object_store).

Procedure

From the OpenShift AI dashboard, click Data science projects.

The Data science projects page opens.

Click the name of the project that you want to add the workbench to.

A project details page opens.

Click Create workbench.

The Create workbench page opens.

Optional: If you want to change the default resource name for your workbench, click Edit resource name.

The resource name is what your resource is labeled in OpenShift. Valid characters include lowercase letters, numbers, and hyphens (-). The resource name cannot exceed 30 characters, and it must start with a letter and end with a letter or number.

Note: You cannot change the resource name after the workbench is created. You can edit only the display name and the description.

In the Workbench image section, complete the fields to specify the workbench image to use with your workbench.

From the Image selection list, select a workbench image that suits your use case. A workbench image includes an IDE and Python packages (reusable code). If project-scoped images exist, the Image selection list includes subheadings to distinguish between global images and project-scoped images.

Optionally, click View package information to view a list of packages that are included in the image that you selected.

If the workbench image has multiple versions available, select the workbench image version to use from the Version selection list. To use the latest package versions, Red Hat recommends that you use the most recently added image.

Note

You can change the workbench image after you create the workbench.

In the Deployment size section, select one of the following options, depending on whether the hardware profiles feature is enabled.

Important

The hardware profiles feature is currently available in Red Hat OpenShift AI as a Technology Preview feature. Technology Preview features are not supported with Red Hat production service level agreements (SLAs) and might not be functionally complete. Red Hat does not recommend using them in production. These features provide early access to upcoming product features, enabling customers to test functionality and provide feedback during the development process.

For more information about the support scope of Red Hat Technology Preview features, see [Technology Preview Features Support Scope](https://access.redhat.com/support/offerings/techpreview/).

If the hardware profiles feature is not enabled:

From the Container size list, select the appropriate size for the size of the model that you want to train or tune.

For example, to run the example fine-tuning job described in [Fine-tuning a model by using Kubeflow Training](https://docs.redhat.com/en/documentation/red_hat_openshift_ai_cloud_service/1/html/working_with_distributed_workloads/running-kfto-based-distributed-training-workloads_distributed-workloads#fine-tuning-a-model-by-using-kubeflow-training_distributed-workloads), select Medium.

From the Accelerator list, select a suitable accelerator profile for your workbench.

If project-scoped accelerator profiles exist, the Accelerator list includes subheadings to distinguish between global accelerator profiles and project-scoped accelerator profiles.

If the hardware profiles feature is enabled:

From the Hardware profile list, select a suitable hardware profile for your workbench.

If project-scoped hardware profiles exist, the Hardware profile list includes subheadings to distinguish between global hardware profiles and project-scoped hardware profiles.

The hardware profile specifies the number of CPUs and the amount of memory allocated to the container, setting the guaranteed minimum (request) and maximum (limit) for both.

If you want to change the default values, click Customize resource requests and limit and enter new minimum (request) and maximum (limit) values.

Important

By default, the hardware profiles feature is not enabled: hardware profiles are not shown in the dashboard navigation menu or elsewhere in the user interface. In addition, user interface components associated with the deprecated accelerator profiles functionality are still displayed. To show the Settings → Hardware profiles option in the dashboard navigation menu, and the user interface components associated with hardware profiles, set the`disableHardwareProfiles` value to`false` in the`OdhDashboardConfig` custom resource (CR) in OpenShift. For more information about setting dashboard configuration options, see [Customizing the dashboard](https://docs.redhat.com/en/documentation/red_hat_openshift_ai_cloud_service/1/html/managing_resources/customizing-the-dashboard).

Optional: In the Environment variables section, select and specify values for any environment variables.

Setting environment variables during the workbench configuration helps you save time later because you do not need to define them in the body of your workbenches, or with the IDE command line interface.

If you are using S3-compatible storage, add these recommended environment variables:

- `AWS_ACCESS_KEY_ID` specifies your Access Key ID for Amazon Web Services.
- `AWS_SECRET_ACCESS_KEY` specifies your Secret access key for the account specified in`AWS_ACCESS_KEY_ID`.

OpenShift AI stores the credentials as Kubernetes secrets in a protected namespace if you select Secret when you add the variable.

In the Cluster storage section, configure the storage for your workbench. Select one of the following options:

Create new persistent storage to create storage that is retained after you shut down your workbench. Complete the relevant fields to define the storage:

Select a storage class for the cluster storage.

Note

You cannot change the storage class after you add the cluster storage to the workbench.

For storage classes that support multiple access modes, select an Access mode to define how the volume can be accessed. For more information, see [About persistent storage](https://docs.redhat.com/en/documentation/red_hat_openshift_ai_cloud_service/1/html/managing_resources/managing-storage-classes#about-persistent-storage_resource-mgmt).

Only the access modes that have been enabled for the storage class by your cluster and OpenShift AI administrators are visible.

1. Enter a name for the cluster storage.
2. Enter a description for the cluster storage.
3. Under Persistent storage size, enter a new size in gibibytes or mebibytes.

Optional: You can add a connection to your workbench. A connection is a resource that contains the configuration parameters needed to connect to a data source or sink, such as an object storage bucket. You can use storage buckets for storing data, models, and pipeline artifacts. You can also use a connection to specify the location of a model that you want to deploy.

In the Connections section, use an existing connection or create a new connection:

Use an existing connection as follows:

1. Click Attach existing connections.
2. From the Connection list, select a connection that you previously defined.

Create a new connection as follows:

If you selected S3 compatible object storage in the preceding step, configure the connection details:

1. In the Connection name field, enter a unique name for the connection.
2. Optional: In the Description field, enter a description for the connection.
3. In the Access key field, enter the access key ID for the S3-compatible object storage provider.
4. In the Secret key field, enter the secret access key for the S3-compatible object storage account that you specified.
5. In the Endpoint field, enter the endpoint of your S3-compatible object storage bucket.
6. In the Region field, enter the default region of your S3-compatible object storage account.
7. In the Bucket field, enter the name of your S3-compatible object storage bucket.
8. Click Create.

If you selected URI in the preceding step, configure the connection details:

1. In the Connection name field, enter a unique name for the connection.
2. Optional: In the Description field, enter a description for the connection.
3. In the URI field, enter the Uniform Resource Identifier (URI).
4. Click Create.

Verification

- Click Create workbench.
- The workbench that you created is visible on the Workbenches tab for the project.
- Any cluster storage that you associated with the workbench during the creation process is displayed on the Cluster storage tab for the project.
- The Status column on the Workbenches tab displays a status of Starting when the workbench server is starting, and Running when the workbench has successfully started.
- Optional: Click the open icon () to open the IDE in a new window.

## Chapter 5. Next stepsCopy link

The following product documentation provides more information on how to develop, test, and deploy data science solutions with OpenShift AI.

Try the end-to-end tutorial

[OpenShift AI tutorial - Fraud detection example](https://docs.redhat.com/en/documentation/red_hat_openshift_ai_cloud_service/1/html/openshift_ai_tutorial_-_fraud_detection_example)

Step-by-step guidance to complete the following tasks with an example fraud detection model:

- Explore a pre-trained fraud detection model by using a Jupyter notebook.
- Deploy the model by using OpenShift AI model serving.
- Refine and train the model by using automated pipelines.

Develop and train a model in your workbench IDE

[Working in your data science IDE](https://docs.redhat.com/en/documentation/red_hat_openshift_ai_cloud_service/1/html/working_in_your_data_science_ide/)

Learn how to access your workbench IDE (JupyterLab, code-server, or RStudio Server).

For the JupyterLab IDE, learn about the following tasks:

- Creating and importing Jupyter notebooks
- Using Git to collaborate on Jupyter notebooks
- Viewing and installing Python packages
- Troubleshooting common problems

Automate your ML workflow with pipelines

[Working with data science pipelines](https://docs.redhat.com/en/documentation/red_hat_openshift_ai_cloud_service/1/html/working_with_data_science_pipelines/)

Enhance your data science projects on OpenShift AI by building portable machine learning (ML) workflows with data science pipelines, by using Docker containers. Use pipelines for continuous retraining and updating of a model based on newly received data.

Deploy and test a model

[Deploying models](https://docs.redhat.com/en/documentation/red_hat_openshift_ai_cloud_service/1/html/deploying_models/)

Deploy your ML models on your OpenShift cluster to test and then integrate them into intelligent applications. When you deploy a model, it is available as a service that you can access by using API calls. You can return predictions based on data inputs that you provide through API calls.

Monitor and manage models

[Deploying models](https://docs.redhat.com/en/documentation/red_hat_openshift_ai_cloud_service/1/html/deploying_models/)

The Red Hat OpenShift AI service includes model deployment options for hosting the model on Red Hat OpenShift Dedicated or Red Hat OpenShift Service on AWS for integration into an external application.

Add accelerators to optimize performance

[Working with accelerators](https://docs.redhat.com/en/documentation/red_hat_openshift_ai_cloud_service/1/html/working_with_accelerators/)

If you work with large data sets, you can use accelerators, such as NVIDIA GPUs, AMD GPUs, and Intel Gaudi AI accelerators, to optimize the performance of your data science models in OpenShift AI. With accelerators, you can scale your work, reduce latency, and increase productivity.

Implement distributed workloads for higher performance

[Working with distributed workloads](https://docs.redhat.com/en/documentation/red_hat_openshift_ai_cloud_service/1/html/working_with_distributed_workloads/)

Implement distributed workloads to use multiple cluster nodes in parallel for faster, more efficient data processing and model training.

Explore extensions

[Working with connected applications](https://docs.redhat.com/en/documentation/red_hat_openshift_ai_cloud_service/1/html/working_with_connected_applications/)

Extend your core OpenShift AI solution with integrated third-party applications. Several leading AI/ML software technology partners, including Starburst, Intel AI Tools, and IBM are also available through [Red Hat partners](https://catalog.redhat.com/en/partners) and [IBM Partner Plus Directory](https://www.ibm.com/partnerplus/directory/companies).

### 5.1. Additional resourcesCopy link

In addition to product documentation, Red Hat provides a rich set of learning resources for OpenShift AI and supported applications.

On the Resources page of the OpenShift AI dashboard, you can use the category links to filter the resources for various stages of your data science workflow. For example, click the Model serving category to display resources that describe various methods of deploying models. Click All items to show the resources for all categories.

For the selected category, you can apply additional options to filter the available resources. For example, you can filter by type, such as how-to articles, quick starts, or tutorials; these resources provide the answers to common questions.

For information about Red Hat OpenShift AI support requirements and limitations, see [Red Hat OpenShift AI: Supported Configurations](https://access.redhat.com/articles/rhoai-supported-configs).

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



## Working on data science projects

Working on data science projects | Red Hat OpenShift AI Cloud Service | 1 | Red Hat Documentation

 

 

 

 

 

 

 Updates for Red Hat OpenShift AI Cloud Service are only provided up to the end of October 2025. 

 

# Working on data science projects

---

Red Hat OpenShift AI Cloud Service 1

## Organize your work in projects and workbenches.

Legal Notice

Abstract

Organize your work in projects and workbenches.

---

## PrefaceCopy link

As a data scientist, you can organize your data science work into a single project. A data science project in OpenShift AI can consist of the following components:

Workbenches Creating a workbench allows you to work with models in your preferred IDE, such as JupyterLab. Cluster storage For data science projects that require data retention, you can add cluster storage to the project. Connections Adding a connection to your project allows you to connect data inputs to your workbenches. Pipelines Standardize and automate machine learning workflows to enable you to further enhance and deploy your data science models. Models and model servers Deploy a trained data science model to serve intelligent applications. Your model is deployed with an endpoint that allows applications to send requests to the model.

## Chapter 1. Using data science projectsCopy link

### 1.1. Creating a data science projectCopy link

To implement a data science workflow, you must create a project. In OpenShift, a project is a Kubernetes namespace with additional annotations, and is the main way that you can manage user access to resources. A project organizes your data science work in one place and also allows you to collaborate with other developers and data scientists in your organization.

Within a project, you can add the following functionality:

- Legal Notice
- Connections so that you can access data without having to hardcode information like endpoints or credentials.
- Workbenches for working with and processing data, and for developing models.
- Deployed models so that you can test them and then integrate them into intelligent applications. Deploying a model makes it available as a service that you can access by using an API.
- Pipelines for automating your ML workflow.

Prerequisites

- You have logged in to Red Hat OpenShift AI.
- You have the appropriate roles and permissions to create projects.

Procedure

From the OpenShift AI dashboard, select Data science projects.

The Data science projects page shows a list of projects that you can access. For each user-requested project in the list, the Name column shows the project display name, the user who requested the project, and the project description.

Optional: If you want to change the default resource name for your project, click Edit resource name.

The resource name is what your resource is labeled in OpenShift. Valid characters include lowercase letters, numbers, and hyphens (-). The resource name cannot exceed 30 characters, and it must start with a letter and end with a letter or number.

Note: You cannot change the resource name after the project is created. You can edit only the display name and the description.

1. Click Create project.
2. In the Create project dialog, update the Name field to enter a unique display name for your project.
3. Optional: In the Description field, provide a project description.
4. Click Create.

Verification

- A project details page opens. From this page, you can add connections, create workbenches, configure pipelines, and deploy models.

### 1.2. Updating a data science projectCopy link

You can update the project details by changing the project name and description.

Prerequisites

- You have logged in to Red Hat OpenShift AI.
- You have created a data science project.

Procedure

From the OpenShift AI dashboard, click Data science projects.

The Data science projects page opens.

Click the action menu (⋮) beside the project whose details you want to update and click Edit project.

The Edit project dialog opens.

1. Optional: Edit the Name field to change the display name for your project.
2. Optional: Edit the Description field to change the description of your project.
3. Click Update.

Verification

- You can see the updated project details on the Data science projects page.

### 1.3. Deleting a data science projectCopy link

You can delete data science projects so that they do not appear on the OpenShift AI Data science projects page when you no longer want to use them.

Prerequisites

- You have logged in to Red Hat OpenShift AI.
- You have created a data science project.

Procedure

From the OpenShift AI dashboard, click Data science projects.

The Data science projects page opens.

Click the action menu (⋮) beside the project that you want to delete and then click Delete project.

The Delete project dialog opens.

1. Enter the project name in the text field to confirm that you intend to delete it.
2. Click Delete project.

Verification

- The data science project that you deleted is no longer displayed on the Data science projects page.
- Deleting a data science project deletes any associated workbenches, data science pipelines, cluster storage, and connections. This data is permanently deleted and is not recoverable.

## Chapter 2. Using project workbenchesCopy link

### 2.1. Creating a workbench and selecting an IDECopy link

A workbench is an isolated area where you can examine and work with ML models. You can also work with data and run programs, for example to prepare and clean data. While a workbench is not required if, for example, you only want to service an existing model, one is needed for most data science workflow tasks, such as writing code to process data or training a model.

When you create a workbench, you specify an image (an IDE, packages, and other dependencies). Supported IDEs include JupyterLab, code-server, and RStudio (Technology Preview).

The IDEs are based on a server-client architecture. Each IDE provides a server that runs in a container on the OpenShift cluster, while the user interface (the client) is displayed in your web browser. For example, the Jupyter workbench runs in a container on the Red Hat OpenShift cluster. The client is the JupyterLab interface that opens in your web browser on your local computer. All of the commands that you enter in JupyterLab are executed by the workbench. Similarly, other IDEs like code-server or RStudio Server provide a server that runs in a container on the OpenShift cluster, while the user interface is displayed in your web browser. This architecture allows you to interact through your local computer in a browser environment, while all processing occurs on the cluster. The cluster provides the benefits of larger available resources and security because the data being processed never leaves the cluster.

In a workbench, you can also configure connections (to access external data for training models and to save models so that you can deploy them) and cluster storage (for persisting data). Workbenches within the same project can share models and data through object storage with the data science pipelines and model servers.

For data science projects that require data retention, you can add container storage to the workbench you are creating.

Within a project, you can create multiple workbenches. When to create a new workbench depends on considerations, such as the following:

- The workbench configuration (for example, CPU, RAM, or IDE). If you want to avoid editing the configuration of an existing workbench’s configuration to accommodate a new task, you can create a new workbench instead.
- Separation of tasks or activities. For example, you might want to use one workbench for your Large Language Models (LLM) experimentation activities, another workbench dedicated to a demo, and another workbench for testing.

#### 2.1.1. About workbench imagesCopy link

A workbench image is preinstalled with the tools and libraries that you need for model development. You can use the provided workbench images or an OpenShift AI administrator can create custom workbench images adapted to your needs.

To provide a consistent, stable platform for your model development, many provided workbench images contain the same version of Python. Most workbench images available on OpenShift AI are pre-built and ready for you to use immediately after OpenShift AI is installed or upgraded.

For information about Red Hat support of workbench images and packages, see [Red Hat OpenShift AI: Supported Configurations](https://access.redhat.com/articles/rhoai-supported-configs).

The following table lists the workbench images that are installed with Red Hat OpenShift AI by default.

If the preinstalled packages that are provided in these images are not sufficient for your use case, you have the following options:

- Install additional libraries after launching a default image. This option is good if you want to add libraries on an ad hoc basis as you develop models. However, it can be challenging to manage the dependencies of installed libraries and your changes are not saved when the workbench restarts.
- Create a custom image that includes the additional libraries or packages. For more information, see [Creating custom workbench images](https://docs.redhat.com/en/documentation/red_hat_openshift_ai_cloud_service/1/html/managing_openshift_ai/creating-custom-workbench-images).

Important

Workbench images denoted with`(Technology Preview)` in this table are not supported with Red Hat production service level agreements (SLAs) and might not be functionally complete. Red Hat does not recommend using Technology Preview features in production. These features provide early access to upcoming product features, enabling customers to test functionality and provide feedback during the development process. For more information about the support scope of Red Hat Technology Preview features, see [Technology Preview Features Support Scope](https://access.redhat.com/support/offerings/techpreview/).

Expand

CUDA

If you are working with compute-intensive data science models that require GPU support, use the Compute Unified Device Architecture (CUDA) workbench image to gain access to the NVIDIA CUDA Toolkit. Using this toolkit, you can optimize your work by using GPU-accelerated libraries and optimization tools.

Standard Data Science

Use the Standard Data Science workbench image for models that do not require TensorFlow or PyTorch. This image contains commonly-used libraries to assist you in developing your machine learning models.

TensorFlow

TensorFlow is an open source platform for machine learning. With TensorFlow, you can build, train and deploy your machine learning models. TensorFlow contains advanced data visualization features, such as computational graph visualizations. It also allows you to easily monitor and track the progress of your models.

PyTorch

PyTorch is an open source machine learning library optimized for deep learning. If you are working with computer vision or natural language processing models, use the Pytorch workbench image.

Minimal Python

If you do not require advanced machine learning features, or additional resources for compute-intensive data science work, you can use the Minimal Python image to develop your models.

TrustyAI

Use the TrustyAI workbench image to leverage your data science work with model explainability, tracing, and accountability, and runtime monitoring. See the [TrustyAI Explainability repository](https://github.com/trustyai-explainability/trustyai-explainability-python-examples) for some example Jupyter notebooks.

code-server

With the code-server workbench image, you can customize your workbench environment to meet your needs using a variety of extensions to add new languages, themes, debuggers, and connect to additional services. Enhance the efficiency of your data science work with syntax highlighting, auto-indentation, and bracket matching, as well as an automatic task runner for seamless automation. For more information, see [code-server in GitHub](https://github.com/coder/code-server).

NOTE: Elyra-based pipelines are not available with the code-server workbench image.

RStudio Server (Technology preview)

Use the RStudio Server workbench image to access the RStudio IDE, an integrated development environment for R, a programming language for statistical computing and graphics. For more information, see [the RStudio Server site](https://posit.co/products/open-source/rstudio-server/).

To use the RStudio Server workbench image, you must first build it by creating a secret and triggering the BuildConfig, and then enable it in the OpenShift AI UI by editing the`rstudio-rhel9` image stream. For more information, see [Building the RStudio Server workbench images](https://docs.redhat.com/en/documentation/red_hat_openshift_ai_cloud_service/1/html/getting_started_with_red_hat_openshift_ai_cloud_service/creating-a-workbench-select-ide_get-started#building-the-rstudio-server-workbench-images_get-started).

Important

Disclaimer: Red Hat supports managing workbenches in OpenShift AI. However, Red Hat does not provide support for the RStudio software. RStudio Server is available through [https://rstudio.org/](https://rstudio.org/) and is subject to RStudio licensing terms. Review the licensing terms before you use this sample workbench.

CUDA - RStudio Server (Technology Preview)

Use the CUDA - RStudio Server workbench image to access the RStudio IDE and NVIDIA CUDA Toolkit. RStudio is an integrated development environment for R, a programming language for statistical computing and graphics. With the NVIDIA CUDA toolkit, you can optimize your work using GPU-accelerated libraries and optimization tools. For more information, see [the RStudio Server site](https://posit.co/products/open-source/rstudio-server/).

To use the CUDA - RStudio Server workbench image, you must first build it by creating a secret and triggering the BuildConfig, and then enable it in the OpenShift AI UI by editing the`cuda-rstudio-rhel9` image stream. For more information, see [Building the RStudio Server workbench images](https://docs.redhat.com/en/documentation/red_hat_openshift_ai_cloud_service/1/html/getting_started_with_red_hat_openshift_ai_cloud_service/creating-a-workbench-select-ide_get-started#building-the-rstudio-server-workbench-images_get-started).

Important

Disclaimer: Red Hat supports managing workbenches in OpenShift AI. However, Red Hat does not provide support for the RStudio software. RStudio Server is available through [https://rstudio.org/](https://rstudio.org/) and is subject to RStudio licensing terms. Review the licensing terms before you use this sample workbench.

The CUDA - RStudio Server workbench image contains NVIDIA CUDA technology. CUDA licensing information is available at [https://docs.nvidia.com/cuda/](https://docs.nvidia.com/cuda/). Review the licensing terms before you use this sample workbench.

ROCm

Use the ROCm workbench image to run AI and machine learning workloads on AMD GPUs in OpenShift AI. It includes ROCm libraries and tools optimized for high-performance GPU acceleration, supporting custom AI workflows and data processing tasks. Use this image integrating additional frameworks or dependencies tailored to your specific AI development needs.

ROCm-PyTorch

Use the ROCm-PyTorch workbench image to run PyTorch workloads on AMD GPUs in OpenShift AI. It includes ROCm-accelerated PyTorch libraries, enabling efficient deep learning training, inference, and experimentation. This image is designed for data scientists working with PyTorch-based workflows, offering integration with GPU scheduling.

ROCm-TensorFlow

Use the ROCm-TensorFlow workbench image to run TensorFlow workloads on AMD GPUs in OpenShift AI. It includes ROCm-accelerated TensorFlow libraries to support high-performance deep learning model training and inference. This image simplifies TensorFlow development on AMD GPUs and integrates with OpenShift AI for resource scaling and management.

| Table 2.1. Default workbench images | Image name | Description |
| --- | --- | --- |

Show more

#### 2.1.2. Creating a workbenchCopy link

When you create a workbench, you specify an image (an IDE, packages, and other dependencies). You can also configure connections, cluster storage, and add container storage.

Prerequisites

If you created a Simple Storage Service (S3) account outside of Red Hat OpenShift AI and you want to create connections to your existing S3 storage buckets, you have the following credential information for the storage buckets:

- Endpoint URL
- Access key
- Secret key
- Region
- Bucket name

For more information, see [Working with data in an S3-compatible object store](https://docs.redhat.com/en/documentation/red_hat_openshift_ai_cloud_service/1/html/working_with_data_in_an_s3-compatible_object_store).

Procedure

From the OpenShift AI dashboard, click Data science projects.

The Data science projects page opens.

Click the name of the project that you want to add the workbench to.

A project details page opens.

Click Create workbench.

The Create workbench page opens.

Optional: If you want to change the default resource name for your workbench, click Edit resource name.

The resource name is what your resource is labeled in OpenShift. Valid characters include lowercase letters, numbers, and hyphens (-). The resource name cannot exceed 30 characters, and it must start with a letter and end with a letter or number.

Note: You cannot change the resource name after the workbench is created. You can edit only the display name and the description.

In the Workbench image section, complete the fields to specify the workbench image to use with your workbench.

From the Image selection list, select a workbench image that suits your use case. A workbench image includes an IDE and Python packages (reusable code). If project-scoped images exist, the Image selection list includes subheadings to distinguish between global images and project-scoped images.

Optionally, click View package information to view a list of packages that are included in the image that you selected.

If the workbench image has multiple versions available, select the workbench image version to use from the Version selection list. To use the latest package versions, Red Hat recommends that you use the most recently added image.

Note

You can change the workbench image after you create the workbench.

In the Deployment size section, select one of the following options, depending on whether the hardware profiles feature is enabled.

Important

The hardware profiles feature is currently available in Red Hat OpenShift AI as a Technology Preview feature. Technology Preview features are not supported with Red Hat production service level agreements (SLAs) and might not be functionally complete. Red Hat does not recommend using them in production. These features provide early access to upcoming product features, enabling customers to test functionality and provide feedback during the development process.

For more information about the support scope of Red Hat Technology Preview features, see [Technology Preview Features Support Scope](https://access.redhat.com/support/offerings/techpreview/).

If the hardware profiles feature is not enabled:

From the Container size list, select the appropriate size for the size of the model that you want to train or tune.

For example, to run the example fine-tuning job described in [Fine-tuning a model by using Kubeflow Training](https://docs.redhat.com/en/documentation/red_hat_openshift_ai_cloud_service/1/html/working_with_distributed_workloads/running-kfto-based-distributed-training-workloads_distributed-workloads#fine-tuning-a-model-by-using-kubeflow-training_distributed-workloads), select Medium.

From the Accelerator list, select a suitable accelerator profile for your workbench.

If project-scoped accelerator profiles exist, the Accelerator list includes subheadings to distinguish between global accelerator profiles and project-scoped accelerator profiles.

If the hardware profiles feature is enabled:

From the Hardware profile list, select a suitable hardware profile for your workbench.

If project-scoped hardware profiles exist, the Hardware profile list includes subheadings to distinguish between global hardware profiles and project-scoped hardware profiles.

The hardware profile specifies the number of CPUs and the amount of memory allocated to the container, setting the guaranteed minimum (request) and maximum (limit) for both.

If you want to change the default values, click Customize resource requests and limit and enter new minimum (request) and maximum (limit) values.

Important

By default, the hardware profiles feature is not enabled: hardware profiles are not shown in the dashboard navigation menu or elsewhere in the user interface. In addition, user interface components associated with the deprecated accelerator profiles functionality are still displayed. To show the Settings → Hardware profiles option in the dashboard navigation menu, and the user interface components associated with hardware profiles, set the`disableHardwareProfiles` value to`false` in the`OdhDashboardConfig` custom resource (CR) in OpenShift. For more information about setting dashboard configuration options, see [Customizing the dashboard](https://docs.redhat.com/en/documentation/red_hat_openshift_ai_cloud_service/1/html/managing_resources/customizing-the-dashboard).

Optional: In the Environment variables section, select and specify values for any environment variables.

Setting environment variables during the workbench configuration helps you save time later because you do not need to define them in the body of your workbenches, or with the IDE command line interface.

If you are using S3-compatible storage, add these recommended environment variables:

- `AWS_ACCESS_KEY_ID` specifies your Access Key ID for Amazon Web Services.
- `AWS_SECRET_ACCESS_KEY` specifies your Secret access key for the account specified in`AWS_ACCESS_KEY_ID`.

OpenShift AI stores the credentials as Kubernetes secrets in a protected namespace if you select Secret when you add the variable.

In the Cluster storage section, configure the storage for your workbench. Select one of the following options:

Create new persistent storage to create storage that is retained after you shut down your workbench. Complete the relevant fields to define the storage:

Select a storage class for the cluster storage.

Note

You cannot change the storage class after you add the cluster storage to the workbench.

For storage classes that support multiple access modes, select an Access mode to define how the volume can be accessed. For more information, see [About persistent storage](https://docs.redhat.com/en/documentation/red_hat_openshift_ai_cloud_service/1/html/managing_resources/managing-storage-classes#about-persistent-storage_resource-mgmt).

Only the access modes that have been enabled for the storage class by your cluster and OpenShift AI administrators are visible.

1. Enter a name for the cluster storage.
2. Enter a description for the cluster storage.
3. Under Persistent storage size, enter a new size in gibibytes or mebibytes.

Optional: You can add a connection to your workbench. A connection is a resource that contains the configuration parameters needed to connect to a data source or sink, such as an object storage bucket. You can use storage buckets for storing data, models, and pipeline artifacts. You can also use a connection to specify the location of a model that you want to deploy.

In the Connections section, use an existing connection or create a new connection:

Use an existing connection as follows:

1. Click Attach existing connections.
2. From the Connection list, select a connection that you previously defined.

Create a new connection as follows:

If you selected S3 compatible object storage in the preceding step, configure the connection details:

1. In the Connection name field, enter a unique name for the connection.
2. Optional: In the Description field, enter a description for the connection.
3. In the Access key field, enter the access key ID for the S3-compatible object storage provider.
4. In the Secret key field, enter the secret access key for the S3-compatible object storage account that you specified.
5. In the Endpoint field, enter the endpoint of your S3-compatible object storage bucket.
6. In the Region field, enter the default region of your S3-compatible object storage account.
7. In the Bucket field, enter the name of your S3-compatible object storage bucket.
8. Click Create.

If you selected URI in the preceding step, configure the connection details:

1. In the Connection name field, enter a unique name for the connection.
2. Optional: In the Description field, enter a description for the connection.
3. In the URI field, enter the Uniform Resource Identifier (URI).
4. Click Create.

Verification

- Click Create workbench.
- The workbench that you created is visible on the Workbenches tab for the project.
- Any cluster storage that you associated with the workbench during the creation process is displayed on the Cluster storage tab for the project.
- The Status column on the Workbenches tab displays a status of Starting when the workbench server is starting, and Running when the workbench has successfully started.
- Optional: Click the open icon () to open the IDE in a new window.

### 2.2. Starting a workbenchCopy link

You can manually start a data science project’s workbench from the Workbenches tab on the project details page. By default, workbenches start immediately after you create them.

Prerequisites

- You have logged in to Red Hat OpenShift AI.
- You have created a data science project that contains a workbench.

Procedure

From the OpenShift AI dashboard, click Data science projects.

The Data science projects page opens.

Click the name of the project whose workbench you want to start.

A project details page opens.

In the Status column for the workbench that you want to start, click Start.

The Status column changes from Stopped to Starting when the workbench server is starting, and then to Running when the workbench has successfully started. * Optional: Click the open icon () to open the IDE in a new window.

1. Click the Workbenches tab.

Verification

- The workbench that you started is displayed on the Workbenches tab for the project, with the status of Running.

### 2.3. Updating a project workbenchCopy link

If your data science work requires you to change your workbench image, container size, or identifying information, you can update the properties of your project’s workbench. If you require extra power for use with large datasets, you can assign accelerators to your workbench to optimize performance.

Prerequisites

- You have logged in to Red Hat OpenShift AI.
- You have created a data science project that has a workbench.

Procedure

From the OpenShift AI dashboard, click Data science projects.

The Data science projects page opens.

Click the name of the project whose workbench you want to update.

A project details page opens.

Click the action menu (⋮) beside the workbench that you want to update and then click Edit workbench.

The Edit workbench page opens.

1. Click the Workbenches tab.
2. Update any of the workbench properties and then click Update workbench.

Verification

- The workbench that you updated is displayed on the Workbenches tab for the project.

### 2.4. Deleting a workbench from a data science projectCopy link

You can delete workbenches from your data science projects to help you remove Jupyter notebooks that are no longer relevant to your work.

Prerequisites

- You have logged in to Red Hat OpenShift AI.
- You have created a data science project with a workbench.

Procedure

From the OpenShift AI dashboard, click Data science projects.

The Data science projects page opens.

Click the name of the project that you want to delete the workbench from.

A project details page opens.

Click the action menu (⋮) beside the workbench that you want to delete and then click Delete workbench.

The Delete workbench dialog opens.

1. Click the Workbenches tab.
2. Enter the name of the workbench in the text field to confirm that you intend to delete it.
3. Click Delete workbench.

Verification

- The workbench that you deleted is no longer displayed on the Workbenches tab for the project.
- The custom resource (CR) associated with the workbench’s Jupyter notebook is deleted.

## Chapter 3. Using connectionsCopy link

### 3.1. Adding a connection to your data science projectCopy link

You can enhance your data science project by adding a connection that contains the configuration parameters needed to connect to a data source or sink.

When you want to work with a very large data sets, you can store your data in an Open Container Initiative (OCI)-compliant registry, S3-compatible object storage bucket, or a URI-based repository, so that you do not fill up your local storage. You also have the option of associating the connection with an existing workbench that does not already have a connection.

Prerequisites

- You have logged in to Red Hat OpenShift AI.
- You have created a data science project that you can add a connection to.
- You have access to S3-compatible object storage, URI-based repository, or OCI-compliant registry.
- If you intend to add the connection to an existing workbench, you have saved any data in the workbench to avoid losing work.

Procedure

From the OpenShift AI dashboard, click Data science projects.

The Data science projects page opens.

Click the name of the project that you want to add a connection to.

A project details page opens.

In the Add connection modal, select a Connection type. The OCI-compliant registry, S3 compatible object storage, and URI options are pre-installed connection types. Additional options might be available if your OpenShift AI administrator added them.

The Add connection form opens with fields specific to the connection type that you selected.

Enter a unique name for the connection.

A resource name is generated based on the name of the connection. A resource name is the label for the underlying resource in OpenShift.

Complete the form depending on the connection type that you selected. For example:

If you selected S3 compatible object storage as the connection type, configure the connection details:

In the Endpoint field, enter the endpoint of your S3-compatible object storage bucket.

Note

Make sure to use the appropriate endpoint format. Improper formatting might cause connection errors or restrict access to storage resources. For more information about how to format object storage endpoints, see [Overview of object storage endpoints](https://docs.redhat.com/en/documentation/red_hat_openshift_ai_cloud_service/1/html/managing_resources/managing-storage-classes#overview-of-object-storage-endpoints_resource-mgmt).

1. In the Access key field, enter the access key ID for the S3-compatible object storage provider.
2. In the Secret key field, enter the secret access key for the S3-compatible object storage account that you specified.
3. In the Region field, enter the default region of your S3-compatible object storage account.
4. In the Bucket field, enter the name of your S3-compatible object storage bucket.
5. Click Create.

Verification

- Click Add connection.
- The connection that you added is displayed on the Connections tab for the project.

### 3.2. Updating a connectionCopy link

You can edit the configuration of an existing connection as described in this procedure.

Note

Any changes that you make to a connection are not applied to dependent resources (for example, a workbench) until those resources are restarted, redeployed, or otherwise regenerated.

Prerequisites

- You have logged in to Red Hat OpenShift AI.
- You have created a data science project, created a workbench, and you have defined a connection.

Procedure

From the OpenShift AI dashboard, click Data science projects.

The Data science projects page opens.

Click the name of the project that contains the connection that you want to change.

A project details page opens.

Click the action menu (⋮) beside the connection that you want to change and then click Edit.

The Edit connection form opens.

1. Click the Connections tab.
2. Make your changes.
3. Click Save.

Verification

- The updated connection is displayed on the Connections tab for the project.

### 3.3. Deleting a connectionCopy link

You can delete connections that are no longer relevant to your data science project.

Prerequisites

- You have logged in to Red Hat OpenShift AI.
- You have created a data science project with a connection.

Procedure

From the OpenShift AI dashboard, click Data science projects.

The Data science projects page opens.

Click the name of the project that you want to delete the connection from.

A project details page opens.

Click the action menu (⋮) beside the connection that you want to delete and then click Delete connection.

The Delete connection dialog opens.

1. Click the Connections tab.
2. Enter the name of the connection in the text field to confirm that you intend to delete it.
3. Click Delete connection.

Verification

- The connection that you deleted is no longer displayed on the Connections page for the project.

## Chapter 4. Configuring cluster storageCopy link

### 4.1. About persistent storageCopy link

OpenShift AI uses persistent storage to support workbenches, project data, and model training.

Persistent storage is provisioned through OpenShift storage classes and persistent volumes. Volume provisioning and data access are determined by access modes.

Understanding storage classes and access modes can help you choose the right storage for your use case and avoid potential risks when sharing data across multiple workbenches.

#### 4.1.1. Storage classes in OpenShift AICopy link

Storage classes in OpenShift AI are available from the underlying OpenShift cluster. A storage class defines how persistent volumes are provisioned, including which storage backend is used and what access modes the provisioned volumes can support. For more information, see [Dynamic provisioning](https://docs.redhat.com/en/documentation/openshift_container_platform/4.19/html/storage/understanding-persistent-storage) in the OpenShift documentation.

Cluster administrators create and configure storage classes in the OpenShift cluster. These storage classes provision persistent volumes that support one or more access modes, depending on the capabilities of the storage backend. OpenShift AI administrators then enable specific storage classes and access modes for use in OpenShift AI.

When adding cluster storage to your project or workbench, you can choose from any enabled storage classes and access modes.

#### 4.1.2. Access modesCopy link

Storage classes create persistent volumes that can support different access modes, depending on the storage backend. Access modes control how a volume can be mounted and used by one or more workbenches. If a storage class allows more than one access mode, you can select the one that best fits your needs when you request storage. All persistent volumes support`ReadWriteOnce (RWO)` by default.

Expand

`ReadWriteOnce (RWO)`(Default)

The storage can be attached to a single workbench or pod at a time and is ideal for most individual workloads.`RWO` is always enabled by default and cannot be disabled by the administrator.

`ReadWriteMany (RWX)`

The storage can be attached to many workbenches simultaneously.`RWX` enables shared data access, but can introduce data risks.

`ReadOnlyMany (ROX)`

The storage can be attached to many workbenches as read-only.`ROX` is useful for sharing reference data without allowing changes.

`ReadWriteOncePod (RWOP)`

The storage can be attached to a single pod on a single node with read-write permissions.`RWOP` is similar to`RWO` but includes additional node-level restrictions.

| | Access mode | Description |
| --- | --- | --- |

Show more

Note

You can enable access modes that are required. A warning is displayed if you request an access mode with unknown support, but you can continue to select Save to create the storage class with the selected access mode.

##### 4.1.2.1. Using shared storage (RWX)Copy link

The`ReadWriteMany (RWX)` access mode allows multiple workbenches to access and write to the same storage volume at the same time. Use`RWX` access mode for collaborative work where multiple users need to access shared datasets or project files.

However, shared storage introduces several risks:

- Data corruption or data loss: If multiple workbenches modify the same part of a file simultaneously, the data can become corrupted or lost. Ensure your applications or workflows are designed to safely handle shared access, for example, by using file locking or database transactions.
- Security and privacy: If a workbench with access to shared storage is compromised, all data on that volume might be at risk. Only share sensitive data with trusted workbenches and users.

To use shared storage safely:

- Ensure that your tools or workflows are designed to work with shared storage and can manage simultaneous writes. For example, use databases or distributed data processing frameworks.
- Be cautious with changes. Deleting or editing files affects everyone who shares the volume.
- Back up your data regularly, which can help prevent data loss due to mistakes or misconfigurations.
- Limit access to RWX volumes to trusted users and secure workbenches.
- Use`ReadWriteMany (RWX)` only when collaboration on a shared volume is required. For most individual tasks,`ReadWriteOnce (RWO)` is ideal because only one workbench can write to the volume at a time.

### 4.2. Adding cluster storage to your data science projectCopy link

For data science projects that require data to be retained, you can add cluster storage to the project. Additionally, you can also connect cluster storage to a specific project’s workbench.

Prerequisites

- You have logged in to Red Hat OpenShift AI.
- You have created a data science project that you can add cluster storage to.

Procedure

From the OpenShift AI dashboard, click Data science projects.

The Data science projects page opens.

Click the name of the project that you want to add the cluster storage to.

A project details page opens.

Click Add cluster storage.

The Add cluster storage dialog opens.

From the Storage class list, select the type of cluster storage.

Note

You cannot change the storage class after you add the cluster storage to the project.

For storage classes that support multiple access modes, select an Access mode to define how the volume can be accessed. For more information, see [About persistent storage](https://docs.redhat.com/en/documentation/red_hat_openshift_ai_cloud_service/1/html/managing_resources/managing-storage-classes#about-persistent-storage_resource-mgmt).

Only the access modes that have been enabled for the storage class by your cluster and OpenShift AI administrators are visible.

Optional: If you want to connect the cluster storage to an existing workbench:

1. In the Workbench connections section, click Add workbench.
2. In the Name field, select an existing workbench from the list.
3. In the Path format field, select Standard if your storage directory begins with`/opt/app-root/src`, otherwise select Custom.
4. In the Mount path field, enter the path to a model or directory within a container where a volume is mounted and accessible. The path must consist of lowercase alphanumeric characters or`-`. Use`/` to indicate subdirectories.

Verification

- Click Add storage.
- The cluster storage that you added is displayed on the Cluster storage tab for the project.
- A new persistent volume claim (PVC) is created with the storage size that you defined.
- The persistent volume claim (PVC) is visible as an attached storage on the Workbenches tab for the project.

### 4.3. Updating cluster storageCopy link

If your data science work requires you to change the identifying information of a project’s cluster storage or the workbench that the storage is connected to, you can update your project’s cluster storage to change these properties.

Note

You cannot directly change the storage class for cluster storage that is already configured for a workbench or project. To switch to a different storage class, you need to migrate your data to a new cluster storage instance that uses the required storage class. For more information, see [Changing the storage class for an existing cluster storage instance](https://docs.redhat.com/en/documentation/red_hat_openshift_ai_cloud_service/1/html/working_on_data_science_projects/configuring-cluster-storage_projects#changing-the-storage-class-for-an-existing-cluster-storage-instance_projects).

Prerequisites

- You have logged in to Red Hat OpenShift AI.
- You have created a data science project that contains cluster storage.

Procedure

From the OpenShift AI dashboard, click Data science projects.

The Data science projects page opens.

Click the name of the project whose storage you want to update.

A project details page opens.

Click the action menu (⋮) beside the storage that you want to update and then click Edit storage.

The Update cluster storage page opens.

Optional: In the Persistent storage size section, specify a new size in gibibytes or mebibytes.

Note that you can only increase the storage size. Updating the storage size restarts the workbench and makes it unavailable for a period of time that is usually proportional to the size change.

Optional: If you want to connect the cluster storage to a different workbench:

1. In the Workbench connections section, click Add workbench.
2. In the Name field, select an existing workbench from the list.
3. In the Path format field, select Standard if your storage directory begins with`/opt/app-root/src`, otherwise select Custom.
4. In the Mount path field, enter the path to a model or directory within a container where a volume is mounted and accessible. The path must consist of lowercase alphanumeric characters or`-`. Use`/` to indicate subdirectories.

If you increased the storage size, the workbench restarts and is unavailable for a period of time that is usually proportional to the size change.

Verification

- Click Update storage.
- The storage that you updated is displayed on the Cluster storage tab for the project.

### 4.4. Changing the storage class for an existing cluster storage instanceCopy link

When you create a workbench with cluster storage, the cluster storage is tied to a specific storage class. Later, if your data science work requires a different storage class, or if the current storage class has been deprecated, you cannot directly change the storage class on the existing cluster storage instance. Instead, you must migrate your data to a new cluster storage instance that uses the storage class that you want to use.

Prerequisites

- You have logged in to Red Hat OpenShift AI.
- You have created a workbench or data science project that contains cluster storage.

Procedure

Stop the workbench with the storage class that you want to change.

From the OpenShift AI dashboard, click Data science projects.

The Data science projects page opens.

Click the name of the project with the cluster storage instance that uses the storage class you want to change.

The project details page opens.

In the Status column for the relevant workbench, click Stop.

Wait until the Status column for the relevant workbench changes from Running to Stopped.

1. Click the Workbenches tab.

Add a new cluster storage instance that uses the needed storage class.

Click Add cluster storage.

The Add cluster storage dialog opens.

For storage classes that support multiple access modes, select an Access mode to define how the volume can be accessed. For more information, see [About persistent storage](https://docs.redhat.com/en/documentation/red_hat_openshift_ai_cloud_service/1/html/managing_resources/managing-storage-classes#about-persistent-storage_resource-mgmt).

Only the access modes that have been enabled for the storage class by your cluster and OpenShift AI administrators are visible.

1. Click the Cluster storage tab.
2. Enter a name for the cluster storage.
3. Optional: Enter a description for the cluster storage.
4. Select the needed storage class for the cluster storage.
5. Under Persistent storage size, enter a size in gibibytes or mebibytes.
6. In the Workbench connections section, click Add workbench.
7. In the Name field, select an existing workbench from the list.
8. In the Path format field, select Standard if your storage directory begins with`/opt/app-root/src`, otherwise select Custom.
9. In the Mount path field, enter the path to a model or directory within a container where a volume is mounted and accessible. For example,`backup`.
10. Click Add storage.

Copy the data from the existing cluster storage instance to the new cluster storage instance.

Copy the data to the new storage directory. Replace with the storage directory of your new cluster storage instance.

```plaintext
rsync -avO --exclude='/opt/app-root/src/__<mount_folder_name>__' /opt/app-root/src/ /opt/app-root/src/__<mount_folder_name>__/
```

For example:

```plaintext
rsync -avO --exclude='/opt/app-root/src/backup' /opt/app-root/src/ /opt/app-root/src/backup/
```

1. Click the Workbenches tab.
2. In the Status column for the relevant workbench, click Start.
3. When the workbench status is Running, click Open to open the workbench.
4. In JupyterLab, click File → New → Terminal.
5. After the data has finished copying, log out of JupyterLab.

Stop the workbench.

In the Status column for the relevant workbench, click Stop.

Wait until the Status column for the relevant workbench changes from Running to Stopped.

1. Click the Workbenches tab.

Remove the original cluster storage instance from the workbench.

1. Click the Cluster storage tab.
2. Click the action menu (⋮) beside the existing cluster storage instance, and then click Edit storage.
3. Under Existing connected workbenches, remove the workbench.
4. Click Update.

Update the mount folder of the new cluster storage instance by removing it and re-adding it to the workbench.

1. On the Cluster storage tab, click the action menu (⋮) beside the new cluster storage instance, and then click Edit storage.
2. Under Existing connected workbenches, remove the workbench.
3. Click Update.
4. Click the Workbenches tab.
5. Click the action menu (⋮) beside the workbench and then click Edit workbench.
6. In the Cluster storage section, under Use existing persistent storage, select the new cluster storage instance.
7. Click Update workbench.

Restart the workbench.

1. Click the Workbenches tab.
2. In the Status column for the relevant workbench, click Start.

Verification

- Optional: You can delete the mount folder of your new cluster storage instance (for example, the`backup` folder).
- On the Cluster storage tab for the project, the new cluster storage instance is displayed with the needed storage class in the Storage class column and the relevant workbench in the Connected workbenches column.
- On the Workbenches tab for the project, the new cluster storage instance is displayed for the workbench in the Cluster storage section and has the mount path:`/opt/app-root/src`.

### 4.5. Deleting cluster storage from a data science projectCopy link

You can delete cluster storage from your data science projects to help you free up resources and delete unwanted storage space.

Prerequisites

- You have logged in to Red Hat OpenShift AI.
- You have created a data science project with cluster storage.

Procedure

From the OpenShift AI dashboard, click Data science projects.

The Data science projects page opens.

Click the name of the project that you want to delete the storage from.

A project details page opens.

Click the action menu (⋮) beside the storage that you want to delete and then click Delete storage.

The Delete storage dialog opens.

1. Click the Cluster storage tab.
2. Enter the name of the storage in the text field to confirm that you intend to delete it.
3. Click Delete storage.

Verification

- The storage that you deleted is no longer displayed on the Cluster storage tab for the project.
- The persistent volume (PV) and persistent volume claim (PVC) associated with the cluster storage are both permanently deleted. This data is not recoverable.

## Chapter 5. Managing access to data science projectsCopy link

### 5.1. Granting access to a data science projectCopy link

To enable your organization to work collaboratively, you can grant access to your data science project to other users and groups.

Prerequisites

- You have logged in to Red Hat OpenShift AI.
- You have created a data science project.

Procedure

From the OpenShift AI dashboard, click Data science projects.

The Data science projects page opens.

From the list of data science projects, click the name of the data science project that you want to grant access to.

A project details page opens.

Click the Permissions tab.

The Permissions page for the project opens.

Provide one or more users with access to the project.

From the Permissions list, select one of the following access permission levels:

- Admin: Users with this access level can edit project details and manage access to the project.
- Contributor: Users with this access level can view and edit project components, such as its workbenches, connections, and storage.

Provide one or more OpenShift groups with access to the project.

From the Name list, select a group to provide access to the project.

Note

If you do not have`cluster-admin` or`dedicated-admin` permissions, the Name list is not visible. Instead, an input field is displayed enabling you to configure group permissions.

From the Permissions list, select one of the following access permission levels:

- Admin: Groups with this access level can edit project details and manage access to the project.
- Contributor: Groups with this access level can view and edit project components, such as its workbenches, connections, and storage.

Verification

- Optional: To add an additional group, click Add group and repeat the process.
- Users to whom you provided access to the project can perform only the actions permitted by their access permission level.
- The Users and Groups sections on the Permissions tab show the respective users and groups that you granted access to.

### 5.2. Updating access to a data science projectCopy link

To change the level of collaboration on your data science project, you can update the access permissions of users and groups who have access to your project.

Prerequisites

- You have logged in to Red Hat OpenShift AI.
- You have OpenShift AI administrator privileges or you are the project owner.
- You have created a data science project.
- You have previously shared access to your project with other users or groups.

Procedure

From the OpenShift AI dashboard, click Data science projects.

The Data science projects page opens.

Click the name of the project that you want to change the access permissions of.

A project details page opens.

Click the Permissions tab.

The Permissions page for the project opens.

Update the user access permissions to the project.

From the Permissions list, update the user access permissions by selecting one of the following:

- Admin: Users with this access level can edit project details and manage access to the project.
- Contributor: Users with this access level can view and edit project components, such as its workbenches, connections, and storage.

Update the OpenShift groups access permissions to the project.

From the Name list, update the group that has access to the project by selecting another group from the list.

Note

If you do not have`cluster-admin` or`dedicated-admin` permissions, the Name list is not visible. Instead, an input field displays enabling you to configure group permissions.

From the Permissions list, update the group access permissions by selecting one of the following:

- Admin: Groups with this access level can edit project details and manage access to the project.
- Contributor: Groups with this access level can view and edit project components, such as its workbenches, connections, and storage.

Verification

- To confirm the update to the entry, click Confirm ().
- The Users and Groups sections on the Permissions tab show the respective users and groups whose project access permissions you changed.

### 5.3. Removing access to a data science projectCopy link

If you no longer want to work collaboratively on your data science project, you can restrict access to your project by removing users and groups that you previously provided access to your project.

Prerequisites

- You have logged in to Red Hat OpenShift AI.
- You have OpenShift AI administrator privileges or you are the project owner.
- You have created a data science project.
- You have previously shared access to your project with other users or groups.

Procedure

From the OpenShift AI dashboard, click Data science projects.

The Data science projects page opens.

Click the name of the project that you want to change the access permissions of.

A project details page opens.

Click the Permissions tab.

The Permissions page for the project opens.

1. Click the action menu (⋮) beside the user or group whose access permissions you want to revoke and click Delete.

Verification

- Users whose access you have revoked can no longer perform the actions that were permitted by their access permission level.

## Chapter 6. Creating project-scoped resources for your projectCopy link

As an OpenShift AI user, you can access global resources in all OpenShift AI projects, but you can access project-scoped resources within the specified project only.

As a user with access permissions to a project, you can create the following types of project-scoped resources for your OpenShift AI project:

- Workbench images
- Model-serving runtimes for KServe

All resource names must be unique within a project.

Note

A user with access permissions to a project can create project-scoped resources for that project, as described in [Creating project-scoped resources](https://docs.redhat.com/en/documentation/red_hat_openshift_ai_cloud_service/1/html/managing_openshift_ai/creating-project-scoped-resources_managing-rhoai).

Prerequisites

You have example YAML code for the type of resource that you want to create.

You can get the YAML code from a trusted source, such as an existing project-scoped resource, a Git repository, or documentation. Alternatively, you can contact your cluster administrator to request the relevant YAML code.

- You can access the OpenShift console.
- An OpenShift AI administrator has set the`disableProjectScoped` dashboard configuration option to`false`, as described in [Customizing the dashboard](https://docs.redhat.com/en/documentation/red_hat_openshift_ai_cloud_service/1/html/managing_resources/customizing-the-dashboard).
- You can access a project in the OpenShift AI console.

Procedure

From a trusted source, copy the YAML code that you want to use for your project resource.

For example, if you can access an existing project-scoped resource in one of your projects, you can copy the YAML code as follows:

In the Resources list, search for the relevant resource type, as follows:

- For workbench images, search for`ImageStream`.
- For serving runtimes, search for`Template`. From the resulting list, find the templates that have the`objects.kind` specification set to`ServingRuntime`.

Optional: Edit the resource name that is displayed in the OpenShift AI console, as follows:

- For workbench images, edit the`metadata.annotations.opendatahub.io/notebook-image-name` value.
- For serving runtimes, edit the`objects.metadata.annotations.openshift.io/display-name` value.

Verification

Verify that the project-scoped resource is shown in the specified project:

- For workbench images, when you create a workbench in the project, as described in [Creating a workbench](https://docs.redhat.com/en/documentation/red_hat_openshift_ai_cloud_service/1/html/working_on_data_science_projects/using-project-workbenches_projects#creating-a-project-workbench_projects), the workbench image that you added is available in the Image selection list.
- For model-serving runtimes, see [Deploying models on the single-model serving platform](https://docs.redhat.com/en/documentation/red_hat_openshift_ai_cloud_service/1/html/deploying_models/deploying_models_on_the_single_model_serving_platform#deploying-models-on-the-single-model-serving-platform_rhoai-user).

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



## Deploying models

Deploying models | Red Hat OpenShift AI Cloud Service | 1 | Red Hat Documentation

 

 

 

 

 

 

 Updates for Red Hat OpenShift AI Cloud Service are only provided up to the end of October 2025. 

 

# Deploying models

---

Red Hat OpenShift AI Cloud Service 1

## Deploy models in Red Hat OpenShift AI Cloud Service

Legal Notice

Abstract

As a Red Hat OpenShift AI user, you can deploy your machine-learning models in Red Hat OpenShift AI Cloud Service.

---

## Chapter 1. Storing modelsCopy link

You must store your model before you can deploy it. You can store a model in an S3 bucket, URI or Open Container Initiative (OCI) containers.

### 1.1. Using OCI containers for model storageCopy link

As an alternative to storing a model in an S3 bucket or URI, you can upload models to Open Container Initiative (OCI) containers. Deploying models from OCI containers is also known as modelcars in KServe.

Using OCI containers for model storage can help you:

- Legal Notice
- Reduce startup times by avoiding downloading the same model multiple times.
- Reduce disk space usage by reducing the number of models downloaded locally.
- Improve model performance by allowing pre-fetched images.

Using OCI containers for model storage involves the following tasks:

Deploying a model from an OCI image by using either the user interface or the command line interface. To deploy a model by using:

- The user interface, see [Deploying models on the single-model serving platform](https://docs.redhat.com/en/documentation/red_hat_openshift_ai_cloud_service/1/html/deploying_models/deploying_models_on_the_single_model_serving_platform#deploying-models-on-the-single-model-serving-platform_rhoai-user).
- The command line interface, see [Deploying a model stored in an OCI image by using the CLI](https://docs.redhat.com/en/documentation/red_hat_openshift_ai_cloud_service/1/html/deploying_models/deploying_models_on_the_single_model_serving_platform#deploying-model-stored-in-oci-image_rhoai-user).

### 1.2. Storing a model in an OCI imageCopy link

You can store a model in an OCI image. The following procedure uses the example of storing a MobileNet v2-7 model in ONNX format.

Prerequisites

- You have a model in the ONNX format. The example in this procedure uses the MobileNet v2-7 model in ONNX format.
- You have installed the Podman tool.

Procedure

In a terminal window on your local machine, create a temporary directory for storing both the model and the support files that you need to create the OCI image:

```plaintext
cd $(mktemp -d)
```

Create a`models` folder inside the temporary directory:

```plaintext
mkdir -p models/1
```

Note

This example command specifies the subdirectory`1` because OpenVINO requires numbered subdirectories for model versioning. If you are not using OpenVINO, you do not need to create the`1` subdirectory to use OCI container images.

Download the model and support files:

```plaintext
DOWNLOAD_URL=https://github.com/onnx/models/raw/main/validated/vision/classification/mobilenet/model/mobilenetv2-7.onnx
curl -L $DOWNLOAD_URL -O --output-dir models/1/
```

Use the`tree` command to confirm that the model files are located in the directory structure as expected:

```plaintext
tree
```

The`tree` command should return a directory structure similar to the following example:

```plaintext
.
├── Containerfile
└── models
    └── 1
        └── mobilenetv2-7.onnx
```

Create a Docker file named`Containerfile`:

Note

- Specify a base image that provides a shell. In the following example,`ubi9-micro` is the base container image. You cannot specify an empty image that does not provide a shell, such as`scratch`, because KServe uses the shell to ensure the model files are accessible to the model server.
- Change the ownership of the copied model files and grant read permissions to the root group to ensure that the model server can access the files. OpenShift runs containers with a random user ID and the root group ID.

```plaintext
FROM registry.access.redhat.com/ubi9/ubi-micro:latest
COPY --chown=0:0 models /models
RUN chmod -R a=rX /models

# nobody user
USER 65534
```

Use`podman build` commands to create the OCI container image and upload it to a registry. The following commands use Quay as the registry.

Note

If your repository is private, ensure that you are authenticated to the registry before uploading your container image.

```plaintext
podman build --format=oci -t quay.io/<user_name>/<repository_name>:<tag_name> .
podman push quay.io/<user_name>/<repository_name>:<tag_name>
```

### 1.3. Uploading model files to a Persistent Volume Claim (PVC)Copy link

When deploying a model, you can serve it from a preexisting Persistent Volume Claim (PVC) where your model files are stored. You can upload your local model files to a PVC in the IDE that you access from a running workbench.

Prerequisites

The workbench is attached to the persistent volume (PVC).

- For instructions on attaching a PVC, see [Creating a project workbench](https://docs.redhat.com/en/documentation/red_hat_openshift_ai_cloud_service/1/html/working_on_data_science_projects/using-project-workbenches_projects#creating-a-project-workbench_projects).

Procedure

Follow these steps to upload your model files to the PVC mount point (`/opt/app-root/src/`) within your workbench:

In your IDE, navigate to the File Browser pane on the left-hand side.

1. In JupyterLab, this is usually labeled Files.
2. In code-server, this is usually the Explorer view.

In the file browser, navigate to the`/opt/app-root/src/` folder. This folder represents the root of your attached PVC.

Note

Any files or folders that you create or upload to this folder persist in the PVC.

Optional: Create a new folder to organize your models:

1. In the file browser, right-click within the`/opt/app-root/src/` folder in the file browser and select New Folder.
2. Name the folder (for example,`models`).
3. Double-click the new`models` folder to enter it.

Upload your model files to the current folder (`/opt/app-root/src/` or`/opt/app-root/src/models/`):

Using JupyterLab:

1. Click the Upload Files icon () in the file browser toolbar above the folder listing.
2. In the file selection dialog, navigate to and select the model files from your local computer. Click Open.
3. Wait for the upload progress bars next to the filenames to complete.

Using code-server:

1. Drag the model files directly from your local file explorer and drop them into the file browser pane in the target folder within code-server.

Verification

- Wait for the upload process to complete.
- Confirm that your files appear in the file browser at the path where you uploaded them.

Next steps

When you follow the procedure to deploy a model, you can access the model files from the specified path within your PVC:

1. In the Deploy model dialog, select Existing cluster storage under the Source model location section.
2. From the Cluster storage list, select the PVC associated with your workbench.
3. In the Model path field, enter the path to your model or the folder containing your model.

## Chapter 2. Deploying models on the single-model serving platformCopy link

The single-model serving platform deploys each model from its own dedicated model server. This architecture is ideal for deploying, monitoring, scaling, and maintaining large models that require more resources, such as large language models (LLMs).

The platform is based on the KServe component and offers two deployment modes:

- KServe RawDeployment: Uses a standard deployment method that does not require serverless dependencies.
- Knative Serverless: Uses Red Hat OpenShift Serverless for deployments that can automatically scale based on demand.

### 2.1. About KServe deployment modesCopy link

KServe offers two deployment modes for serving models. The default mode, Knative Serverless, is based on the open-source Knative project and provides powerful autoscaling capabilities. It integrates with Red Hat OpenShift Serverless and Red Hat OpenShift Service Mesh. Alternatively, the KServe RawDeployment mode offers a more traditional deployment method with fewer dependencies.

Before you choose an option, understand how your initial configuration affects future deployments:

- If you configure for Knative Serverless: You can use both Knative Serverless and KServe RawDeployment modes.
- If you configure for KServe RawDeployment only: You can only use the KServe RawDeployment mode.

Use the following comparison to choose the option that best fits your requirements.

Expand

Default mode

Yes

No

Recommended use case

Most workloads.

Custom serving setups or models that must remain active.

Autoscaling

- Scales up automatically based on request volume.
- Supports scaling down to zero when idle to save costs.

- No built-in autoscaling; you can configure Kubernetes Event-Driven Autoscaling (KEDA) or Horizontal Pod Autoscaler (HPA) on your deployment.
- Does not support scaling to zero by default, which might result in higher costs during periods of low traffic.

Dependencies

- Red Hat OpenShift Serverless Operator
- Red Hat OpenShift Service Mesh
- Authorino. Only required only if you enable token authentication and external routes.

None; uses standard Kubernetes resources such as`Deployment`,`Service`, and`Horizontal Pod Autoscaler`.

Configuration flexibility

Has some customization limitations inherited from Knative compared to raw Kubernetes deployments.

Provides full control over pod specifications because it uses standard Kubernetes`Deployment` resources.

Resource footprint

Larger, due to the additional dependencies required for serverless functionality.

Smaller.

Setup complexity

Might require additional configuration in setup and management. If Serverless is not already installed on the cluster, you must install and configure it.

Requires a simpler setup with fewer dependencies.

| Table 2.1. Comparison of deployment modes | Criterion | `Knative Serverless` | `KServe RawDeployment` |
| --- | --- | --- | --- |

Show more

### 2.2. Deploying models on the single-model serving platformCopy link

When you have enabled the single-model serving platform, you can enable a preinstalled or custom model-serving runtime and deploy models on the platform.

You can use preinstalled model-serving runtimes to start serving models without modifying or defining the runtime yourself. For help adding a custom runtime, see [Adding a custom model-serving runtime for the single-model serving platform](https://docs.redhat.com/en/documentation/red_hat_openshift_ai_cloud_service/1/html/configuring_your_model-serving_platform/configuring_model_servers_on_the_single_model_serving_platform#adding-a-custom-model-serving-runtime-for-the-single-model-serving-platform_rhoai-admin).

To successfully deploy a model, you must meet the following prerequisites.

General prerequisites

- You have logged in to Red Hat OpenShift AI.
- You have installed KServe and enabled the single-model serving platform.
- (Knative Serverless deployments only) To enable token authentication and external model routes for deployed models, you have added Authorino as an authorization provider. For more information, see [Adding an authorization provider for the single-model serving platform](https://docs.redhat.com/en/documentation/red_hat_openshift_ai_cloud_service/1/html/installing_and_uninstalling_openshift_ai_cloud_service/installing-the-single-model-serving-platform_component-install#adding-an-authorization-provider_component-install).
- (Knative Serverless deployments only) To enable token authentication and external model routes for deployed models, you have added Authorino as an authorization provider.
- You have created a data science project.
- You have access to S3-compatible object storage, a URI-based repository, an OCI-compliant registry or a persistent volume claim (PVC) and have added a connection to your data science project. For more information about adding a connection, see [Adding a connection to your data science project](https://docs.redhat.com/en/documentation/red_hat_openshift_ai_cloud_service/1/html/working_on_data_science_projects/using-connections_projects#adding-a-connection-to-your-data-science-project_projects).
- If you want to use graphics processing units (GPUs) with your model server, you have enabled GPU support in OpenShift AI. If you use NVIDIA GPUs, see [Enabling NVIDIA GPUs](https://docs.redhat.com/en/documentation/red_hat_openshift_ai_cloud_service/1/html/managing_openshift_ai/enabling-accelerators#enabling-nvidia-gpus_managing-rhoai). If you use AMD GPUs, see [AMD GPU integration](https://docs.redhat.com/en/documentation/red_hat_openshift_ai_cloud_service/1/html/managing_openshift_ai/enabling-accelerators#amd-gpu-integration_managing-rhoai).

Runtime-specific prerequisites

Meet the requirements for the specific runtime you intend to use.

Caikit-TGIS runtime

- To use the Caikit-TGIS runtime, you have converted your model to Caikit format. For an example, see [Converting Hugging Face Hub models to Caikit format](https://github.com/opendatahub-io/caikit-tgis-serving/blob/main/demo/kserve/built-tip.md#bootstrap-process) in the [caikit-tgis-serving](https://github.com/opendatahub-io/caikit-tgis-serving/tree/main) repository.

vLLM NVIDIA GPU ServingRuntime for KServe

- To use the vLLM NVIDIA GPU ServingRuntime for KServe runtime, you have enabled GPU support in OpenShift AI and have installed and configured the Node Feature Discovery Operator on your cluster. For more information, see [Installing the Node Feature Discovery Operator](https://docs.redhat.com/en/documentation/openshift_container_platform/4.19/html/specialized_hardware_and_driver_enablement/psap-node-feature-discovery-operator#installing-the-node-feature-discovery-operator_psap-node-feature-discovery-operator) and [Enabling NVIDIA GPUs](https://docs.redhat.com/en/documentation/red_hat_openshift_ai_cloud_service/1/html/managing_openshift_ai/enabling-accelerators#enabling-nvidia-gpus_managing-rhoai).

vLLM Intel Gaudi Accelerator ServingRuntime for KServe

- To use the vLLM Intel Gaudi Accelerator ServingRuntime for KServe runtime, you have enabled support for hybrid processing units (HPUs) in OpenShift AI. This includes installing the Intel Gaudi Base Operator and configuring a hardware profile. For more information, see [Intel Gaudi Base Operator OpenShift installation](https://docs.habana.ai/en/latest/Installation_Guide/Additional_Installation/OpenShift_Installation/index.html#openshift-installation) in the AMD documentation and [Working with hardware profiles](https://docs.redhat.com/en/documentation/red_hat_openshift_ai_cloud_service/1/html/working_with_accelerators/working-with-hardware-profiles_accelerators).

vLLM AMD GPU ServingRuntime for KServe

- To use the vLLM AMD GPU ServingRuntime for KServe runtime, you have enabled support for AMD graphic processing units (GPUs) in OpenShift AI. This includes installing the AMD GPU operator and configuring a hardware profile. For more information, see [Deploying the AMD GPU operator on OpenShift](https://instinct.docs.amd.com/projects/gpu-operator/en/latest/installation/openshift-olm.html) and [Working with hardware profiles](https://docs.redhat.com/en/documentation/red_hat_openshift_ai_cloud_service/1/html/working_with_accelerators/working-with-hardware-profiles_accelerators).

vLLM Spyre AI Accelerator ServingRuntime for KServe

Important

Support for IBM Spyre AI Accelerators on x86 is currently available in Red Hat OpenShift AI as a Technology Preview feature. Technology Preview features are not supported with Red Hat production service level agreements (SLAs) and might not be functionally complete. Red Hat does not recommend using them in production. These features provide early access to upcoming product features, enabling customers to test functionality and provide feedback during the development process.

For more information about the support scope of Red Hat Technology Preview features, see [Technology Preview Features Support Scope](https://access.redhat.com/support/offerings/techpreview/).

- To use the vLLM Spyre AI Accelerator ServingRuntime for KServe runtime on x86, you have installed the Spyre Operator and configured a hardware profile. For more information, see [Spyre operator image](https://catalog.redhat.com/en/software/containers/ibm-aiu/spyre-operator/688a1121575e62c686a471d4) and [Working with hardware profiles](https://docs.redhat.com/en/documentation/red_hat_openshift_ai_cloud_service/1/html/working_with_accelerators/working-with-hardware-profiles_accelerators).

Procedure

Click the name of the project that you want to deploy a model in.

A project details page opens.

Click the Deploy model button.

The Deploy model dialog opens.

The following options are only available if you have created a hardware profile:

From the Hardware profile list, select a hardware profile. If project-scoped hardware profiles exist, the Hardware profile list includes subheadings to distinguish between global hardware profiles and project-scoped hardware profiles.

Important

By default, hardware profiles are hidden in the dashboard navigation menu and user interface, while accelerator profiles remain visible. In addition, user interface components associated with the deprecated accelerator profiles functionality are still displayed. If you enable hardware profiles, the Hardware profiles list is displayed instead of the Accelerator profiles list. To show the Settings → Hardware profiles option in the dashboard navigation menu, and the user interface components associated with hardware profiles, set the`disableHardwareProfiles` value to`false` in the`OdhDashboardConfig` custom resource (CR) in OpenShift. For more information about setting dashboard configuration options, see [Customizing the dashboard](https://docs.redhat.com/en/documentation/red_hat_openshift_ai_cloud_service/1/html/managing_resources/customizing-the-dashboard).

1. Optional: To change these default values, click Customize resource requests and limit and enter new minimum (request) and maximum (limit) values. The hardware profile specifies the number of CPUs and the amount of memory allocated to the container, setting the guaranteed minimum (request) and maximum (limit) for both.

To require token authentication for inference requests to the deployed model, perform the following actions:

1. Select Require token authentication.
2. In the Service account name field, enter the service account name that the token will be generated for.
3. To add an additional service account, click Add a service account and enter another service account name.

To specify the location of your model, select a Connection type that you have added. The OCI-compliant registry, S3 compatible object storage, and URI options are pre-installed connection types. Additional options might be available if your OpenShift AI administrator added them.

For S3-compatible object storage: In the Path field, enter the folder path that contains the model in your specified data source.

Important

The OpenVINO Model Server runtime has specific requirements for how you specify the model path. For more information, see known issue [RHOAIENG-3025](https://docs.redhat.com/en/documentation/red_hat_openshift_ai_cloud_service/1/html/release_notes/index#known-issues_RHOAIENG-3025_relnotes) in the OpenShift AI release notes.

For Open Container Image connections: In the OCI storage location field, enter the model URI where the model is located.

Note

If you are deploying a registered model version with an existing S3, URI, or OCI data connection, some of your connection details might be autofilled. This depends on the type of data connection and the number of matching connections available in your data science project. For example, if only one matching connection exists, fields like the path, URI, endpoint, model URI, bucket, and region might populate automatically. Matching connections will be labeled as Recommended.

Optional: If you have uploaded model files to a persistent volume claim (PVC) and the PVC is attached to your workbench, use the Existing cluster storage option to select the PVC and specify the path to the model file.

Important

If your connection type is an S3-compatible object storage, you must provide the folder path that contains your data file. The OpenVINO Model Server runtime has specific requirements for how you specify the model path. For more information, see known issue [RHOAIENG-3025](https://docs.redhat.com/en/documentation/red_hat_openshift_ai_cloud_service/1/html/release_notes/index#known-issues_RHOAIENG-3025_relnotes) in the OpenShift AI release notes.

1. Complete the connection detail fields.

(Optional) Customize the runtime parameters in the Configuration parameters section:

Modify the values in Additional environment variables to define variables in the model’s environment.

The Configuration parameters section shows predefined serving runtime parameters, if any are available.

Note

Do not modify the port or model serving runtime arguments, because they require specific values to be set. Overwriting these parameters can cause the deployment to fail.

1. Modify the values in Additional serving runtime arguments to define how the deployed model behaves.

Verification

- Click Deploy.
- Confirm that the deployed model is shown on the Models tab for the project, and on the Model deployments page of the dashboard with a checkmark in the Status column.

### 2.3. Deploying a model stored in an OCI image by using the CLICopy link

You can deploy a model that is stored in an OCI image from the command line interface.

The following procedure uses the example of deploying a MobileNet v2-7 model in ONNX format, stored in an OCI image on an OpenVINO model server.

Note

By default in KServe, models are exposed outside the cluster and not protected with authentication.

Prerequisites

- You have stored a model in an OCI image as described in [Storing a model in an OCI image](https://docs.redhat.com/en/documentation/red_hat_openshift_ai_cloud_service/1/html/deploying_models/deploying-models_rhoai-user#storing-a-model-in-oci-image_rhoai-user).
- If you want to deploy a model that is stored in a private OCI repository, you must configure an image pull secret. For more information about creating an image pull secret, see [Using image pull secrets](https://docs.openshift.com/container-platform/latest/openshift_images/managing_images/using-image-pull-secrets.html).
- You are logged in to your OpenShift cluster.

Procedure

Create a project to deploy the model:

```plaintext
oc new-project oci-model-example
```

Use the OpenShift AI Applications project`kserve-ovms` template to create a`ServingRuntime` resource and configure the OpenVINO model server in the new project:

```plaintext
oc process -n redhat-ods-applications -o yaml kserve-ovms | oc apply -f -
```

Verify that the`ServingRuntime` named`kserve-ovms` is created:

```plaintext
oc get servingruntimes
```

The command should return output similar to the following:

```plaintext
NAME          DISABLED   MODELTYPE     CONTAINERS         AGE
kserve-ovms              openvino_ir   kserve-container   1m
```

Create an`InferenceService` YAML resource, depending on whether the model is stored from a private or a public OCI repository:

For a model stored in a public OCI repository, create an`InferenceService` YAML file with the following values, replacing` `,` `, and` ` with values specific to your environment:

```plaintext
apiVersion: serving.kserve.io/v1beta1
kind: InferenceService
metadata:
  name: sample-isvc-using-oci
spec:
  predictor:
    model:
      runtime: kserve-ovms # Ensure this matches the name of the ServingRuntime resource
      modelFormat:
        name: onnx
      storageUri: oci://quay.io/<user_name>/<repository_name>:<tag_name>
      resources:
        requests:
          memory: 500Mi
          cpu: 100m
          # nvidia.com/gpu: "1" # Only required if you have GPUs available and the model and runtime will use it
        limits:
          memory: 4Gi
          cpu: 500m
          # nvidia.com/gpu: "1" # Only required if you have GPUs available and the model and runtime will use it
```

For a model stored in a private OCI repository, create an`InferenceService` YAML file that specifies your pull secret in the`spec.predictor.imagePullSecrets` field, as shown in the following example:

```plaintext
apiVersion: serving.kserve.io/v1beta1
kind: InferenceService
metadata:
  name: sample-isvc-using-private-oci
spec:
  predictor:
    model:
      runtime: kserve-ovms # Ensure this matches the name of the ServingRuntime resource
      modelFormat:
        name: onnx
      storageUri: oci://quay.io/<user_name>/<repository_name>:<tag_name>
      resources:
        requests:
          memory: 500Mi
          cpu: 100m
          # nvidia.com/gpu: "1" # Only required if you have GPUs available and the model and runtime will use it
        limits:
          memory: 4Gi
          cpu: 500m
          # nvidia.com/gpu: "1" # Only required if you have GPUs available and the model and runtime will use it
    imagePullSecrets: # Specify image pull secrets to use for fetching container images, including OCI model images
    - name: <pull-secret-name>
```

After you create the`InferenceService` resource, KServe deploys the model stored in the OCI image referred to by the`storageUri` field.

Verification

Check the status of the deployment:

```plaintext
oc get inferenceservice
```

The command should return output that includes information, such as the URL of the deployed model and its readiness state.

### 2.4. Deploying models by using Distributed Inference with llm-dCopy link

Important

Distributed Inference with llm-d is currently available in Red Hat OpenShift AI as a Technology Preview feature. Technology Preview features are not supported with Red Hat production service level agreements (SLAs) and might not be functionally complete. Red Hat does not recommend using them in production. These features provide early access to upcoming product features, enabling customers to test functionality and provide feedback during the development process.

For more information about the support scope of Red Hat Technology Preview features, see [Technology Preview Features Support Scope](https://access.redhat.com/support/offerings/techpreview/).

Distributed Inference with llm-d is a Kubernetes-native, open-source framework designed for serving large language models (LLMs) at scale. You can use Distributed Inference with llm-d to simplify the deployment of generative AI, focusing on high performance and cost-effectiveness across various hardware accelerators.

Key features of Distributed Inference with llm-d include:

- Efficiently handles large models using optimizations such as prefix-cache aware routing and disaggregated serving.
- Integrates into a standard Kubernetes environment, where it leverages specialized components like the Envoy proxy to handle networking and routing, and high-performance libraries such as vLLM and NVIDIA Inference Transfer Library (NIXL).
- Tested recipes and well-known presets reduce the complexity of deploying inference at scale, so users can focus on building applications rather than managing infrastructure.

Serving models using Distributed Inference with llm-d on Red Hat OpenShift AI consists of the following steps:

Installing OpenShift AI.

Note

Because KServe Serverless conflicts with the Gateway API used for Distributed Inference with llm-d, KServe Serverless is not supported on the same cluster. Instead, use KServe RawDeployment.

1. Enabling the single model serving platform.
2. Enabling Distributed Inference with llm-d on a Kubernetes cluster.
3. Creating an LLMInferenceService Custom Resource (CR).
4. Deploying a model.

This procedure describes how to create a custom resource (CR) for an`LLMInferenceService` resource. You replace the default`InferenceService` with the`LLMInferenceService`.

Prerequisites

- You have enabled the single model-serving platform.
- You have access to an OpenShift cluster running version 4.19.9 or later.
- OpenShift Service Mesh v2 is not installed in the cluster.
- You have created a`GatewayClass` and a`Gateway` named`openshift-ai-inference` in the`openshift-ingress` namespace as described in [Gateway API with OpenShift Container Platform Networking](https://docs.redhat.com/en/documentation/openshift_container_platform/latest/html/ingress_and_load_balancing/configuring-ingress-cluster-traffic#ingress-gateway-api).
- You have installed the`LeaderWorkerSet` Operator in OpenShift. For more information, see the OpenShift documentation.

Procedure

Create a data science cluster initialization (DSCI) and set the`serviceMesh.managementState` to`removed`, as shown in the following example:

```plaintext
serviceMesh:
  ...
  managementState: Removed
```

Create a data science cluster (DSC) with the following information set in`kserve` and`serving`:

```plaintext
kserve:
  defaultDeploymentMode: RawDeployment
  managementState: Managed
  ...
  serving:
    ...
    managementState: Removed
    ...
```

Create the`LLMInferenceService` CR with the following information:

```plaintext
apiVersion: serving.kserve.io/v1alpha1
kind: LLMInferenceService
metadata:
  name: sample-llm-inference-service
spec:
  replicas: 2
  model:
    uri: hf://RedHatAI/Qwen3-8B-FP8-dynamic
    name: RedHatAI/Qwen3-8B-FP8-dynamic
  router:
    route: {}
    gateway: {}
    scheduler: {}
    template:
      containers:
      - name: main
        resources:
          limits:
            cpu: '4'
            memory: 32Gi
            nvidia.com/gpu: "1"
          requests:
            cpu: '2'
            memory: 16Gi
            nvidia.com/gpu: "1"
```

Customize the following parameters in the`spec` section of the inference service:

`model`- Provide the URI to the model based on how the model is stored (`uri`) and the model name to use in chat completion requests (`name`).

- S3 bucket:`s3:// / `
- Persistent volume claim (PVC):`pvc:// / `
- OCI container image:`oci:// / / `
- HuggingFace:`hf:// / `

#### 2.4.1. Example usage for Distributed Inference with llm-dCopy link

These examples show how to use Distributed Inference with llm-d in common scenarios.

Important

Distributed Inference with llm-d is currently available in Red Hat OpenShift AI as a Technology Preview feature. Technology Preview features are not supported with Red Hat production service level agreements (SLAs) and might not be functionally complete. Red Hat does not recommend using them in production. These features provide early access to upcoming product features, enabling customers to test functionality and provide feedback during the development process.

For more information about the support scope of Red Hat Technology Preview features, see [Technology Preview Features Support Scope](https://access.redhat.com/support/offerings/techpreview/).

##### 2.4.1.1. Single-node GPU deploymentCopy link

Use single-GPU-per-replica deployment patterns for development, testing, or production deployments of smaller models, such as 7-billion-parameter models.

You can use the following examples for single-node GPU deployments:

- Save the file.
- [Basic deployment with default scheduler](https://github.com/red-hat-data-services/kserve/blob/rhoai-2.25/docs/samples/llmisvc/single-node-gpu/README.md#1-basic-deployment-with-default-scheduler-)
- [Deployment without scheduler](https://github.com/red-hat-data-services/kserve/blob/rhoai-2.25/docs/samples/llmisvc/single-node-gpu/README.md#2-deployment-without-scheduler-)
- [Prefill-decode separation](https://github.com/red-hat-data-services/kserve/blob/rhoai-2.25/docs/samples/llmisvc/single-node-gpu/README.md#3-prefill-decode-separation-)

##### 2.4.1.2. Multi-node deploymentCopy link

You can use the following examples for multi-node deployments:

- [Basic Data Parallelism (DP) and Expert Parallelism (EP) configuration](https://github.com/red-hat-data-services/kserve/blob/rhoai-2.25/docs/samples/llmisvc/dp-ep/deepseek-r1-gpu-rdma-roce/README.md#1-basic-dpep-configuration-llm-inference-service-dp-ep-deepseek-r1-gpu-deepep-htyaml)
- [Prefill-decode separation with a high-throughput backend](https://github.com/red-hat-data-services/kserve/blob/rhoai-2.25/docs/samples/llmisvc/dp-ep/deepseek-r1-gpu-rdma-roce/README.md#2-prefill-decode-separation-with-high-throughput-backend-llm-inference-service-dp-ep-deepseek-r1-pd-gpu-p-deepep-ht-d-deepep-htyaml)
- [Prefill-decode separation with a mixed backend](https://github.com/red-hat-data-services/kserve/blob/rhoai-2.25/docs/samples/llmisvc/dp-ep/deepseek-r1-gpu-rdma-roce/README.md#3-prefill-decode-with-mixed-backend-llm-inference-service-dp-ep-deepseek-r1-pd-gpu-p-deepep-ht-d-pplxyaml)

##### 2.4.1.3. Intelligent inference scheduler with KV cache routingCopy link

You can configure the scheduler to track key-value (KV) cache blocks across inference endpoints and route requests to the endpoint with the highest cache hit rate. This configuration improves throughput and reduces latency by maximizing cache reuse.

For an example, see [Precise Prefix KV Cache Routing](https://github.com/red-hat-data-services/kserve/blob/rhoai-2.25/docs/samples/llmisvc/precise-prefix-kv-cache-routing/README.md#precise-prefix-kv-cache-routing).

### 2.5. Monitoring models on the single-model serving platformCopy link

You can monitor models that are deployed on the single-model serving platform to view performance and resource usage metrics.

#### 2.5.1. Viewing performance metrics for a deployed modelCopy link

You can monitor the following metrics for a specific model that is deployed on the single-model serving platform:

- Number of requests - The number of requests that have failed or succeeded for a specific model.
- Average response time (ms) - The average time it takes a specific model to respond to requests.
- CPU utilization (%) - The percentage of the CPU limit per model replica that is currently utilized by a specific model.
- Memory utilization (%) - The percentage of the memory limit per model replica that is utilized by a specific model.

You can specify a time range and a refresh interval for these metrics to help you determine, for example, when the peak usage hours are and how the model is performing at a specified time.

Prerequisites

The following dashboard configuration options are set to the default values as shown:

```plaintext
disablePerformanceMetrics:false
disableKServeMetrics:false
```

For more information about setting dashboard configuration options, see [Customizing the dashboard](https://docs.redhat.com/en/documentation/red_hat_openshift_ai_cloud_service/1/html/managing_resources/customizing-the-dashboard).

You have deployed a model on the single-model serving platform by using a preinstalled runtime.

Note

Metrics are only supported for models deployed by using a preinstalled model-serving runtime or a custom runtime that is duplicated from a preinstalled runtime.

- You have installed Red Hat OpenShift AI.
- You have logged in to Red Hat OpenShift AI.

Procedure

From the OpenShift AI dashboard navigation menu, click Data science projects.

The Data science projects page opens.

On the Endpoint performance tab, set the following options:

- Time range - Specifies how long to track the metrics. You can select one of these values: 1 hour, 24 hours, 7 days, and 30 days.
- Refresh interval - Specifies how frequently the graphs on the metrics page are refreshed (to show the latest data). You can select one of these values: 15 seconds, 30 seconds, 1 minute, 5 minutes, 15 minutes, 30 minutes, 1 hour, 2 hours, and 1 day.

Verification

The Endpoint performance tab shows graphs of metrics for the model.

#### 2.5.2. Viewing model-serving runtime metrics for the single-model serving platformCopy link

When a cluster administrator has configured monitoring for the single-model serving platform, non-admin users can use the OpenShift web console to view model-serving runtime metrics for the KServe component.

Prerequisites

- Scroll down to view data graphs for number of requests, average response time, CPU utilization, and memory utilization.
- You have access to the OpenShift cluster as a developer or as a user with view permissions for the project that you are viewing metrics for.
- You are familiar with querying metrics in user-defined projects. See [Monitoring project and application metrics using the Developer perspective in Red Hat OpenShift Dedicated](https://docs.redhat.com/en/documentation/openshift_dedicated/4/html-single/building_applications/index#odc-monitoring-project-and-application-metrics-using-developer-perspective) or [Monitoring project and application metrics using the Developer perspective in Red Hat OpenShift Service on AWS (classic architecture)](https://docs.redhat.com/en/documentation/red_hat_openshift_service_on_aws_classic_architecture/4/html/building_applications/odc-monitoring-project-and-application-metrics-using-developer-perspective).

Procedure

As described in [Monitoring your project metrics in Red Hat OpenShift Dedicated](https://docs.redhat.com/en/documentation/openshift_dedicated/4/html/building_applications/odc-monitoring-project-and-application-metrics-using-developer-perspective#odc-monitoring-your-project-metrics_monitoring-project-and-application-metrics-using-developer-perspective) or [Monitoring your project metrics in Red Hat OpenShift Service on AWS (classic architecture)](https://docs.redhat.com/en/documentation/red_hat_openshift_service_on_aws_classic_architecture/4/html/building_applications/odc-monitoring-project-and-application-metrics-using-developer-perspective#odc-monitoring-your-project-metrics_monitoring-project-and-application-metrics-using-developer-perspective), use the web console to run queries for`caikit_*`,`tgi_*`,`ovms_*` and`vllm:*` model-serving runtime metrics. You can also run queries for`istio_*` metrics that are related to OpenShift Service Mesh. Some examples are shown.

The following query displays the number of successful inference requests over a period of time for a model deployed with the vLLM runtime:

```plaintext
sum(increase(vllm:request_success_total{namespace=${namespace},model_name=${model_name}}[${rate_interval}]))
```

The following query displays the number of successful inference requests over a period of time for a model deployed with the standalone TGIS runtime:

```plaintext
sum(increase(tgi_request_success{namespace=${namespace}, pod=~${model_name}-predictor-.*}[${rate_interval}]))
```

The following query displays the number of successful inference requests over a period of time for a model deployed with the Caikit Standalone runtime:

```plaintext
sum(increase(predict_rpc_count_total{namespace=${namespace},code=OK,model_id=${model_name}}[${rate_interval}]))
```

The following query displays the number of successful inference requests over a period of time for a model deployed with the OpenVINO Model Server runtime:

```plaintext
sum(increase(ovms_requests_success{namespace=${namespace},name=${model_name}}[${rate_interval}]))
```

## Chapter 3. Deploying models on the NVIDIA NIM model serving platformCopy link

You can deploy models using NVIDIA NIM inference services on the NVIDIA NIM model serving platform.

NVIDIA NIM, part of NVIDIA AI Enterprise, is a set of microservices designed for secure, reliable deployment of high performance AI model inferencing across clouds, data centers and workstations.

### 3.1. Deploying models on the NVIDIA NIM model serving platformCopy link

When you have enabled the NVIDIA NIM model serving platform, you can start to deploy NVIDIA-optimized models on the platform.

Prerequisites

- You have logged in to Red Hat OpenShift AI.
- You have enabled the NVIDIA NIM model serving platform.
- You have created a data science project.
- You have enabled support for graphic processing units (GPUs) in OpenShift AI. This includes installing the Node Feature Discovery Operator and NVIDIA GPU Operator. For more information, see [Installing the Node Feature Discovery Operator](https://docs.redhat.com/en/documentation/openshift_container_platform/4.19/html/specialized_hardware_and_driver_enablement/psap-node-feature-discovery-operator#installing-the-node-feature-discovery-operator_psap-node-feature-discovery-operator) and [Enabling NVIDIA GPUs](https://docs.redhat.com/en/documentation/red_hat_openshift_ai_cloud_service/1/html/managing_openshift_ai/enabling-accelerators#enabling-nvidia-gpus_managing-rhoai).

Procedure

In the left menu, click Data science projects.

The Data science projects page opens.

Click the name of the project that you want to deploy a model in.

A project details page opens.

In the Models section, perform one of the following actions:

- On the ​​NVIDIA NIM model serving platform tile, click Select NVIDIA NIM on the tile, and then click Deploy model.
- If you have previously selected the NVIDIA NIM model serving type, the Models page displays NVIDIA model serving enabled on the upper-right corner, along with the Deploy model button. To proceed, click Deploy model.

The Deploy model dialog opens.

Configure properties for deploying your model as follows:

In the NVIDIA NIM storage size field, specify the size of the cluster storage instance that will be created to store the NVIDIA NIM model.

Note

When resizing a PersistentVolumeClaim (PVC) backed by Amazon EBS in OpenShift AI, you may encounter`VolumeModificationRateExceeded: You've reached the maximum modification rate per volume limit.` To avoid this error, wait at least six hours between modifications per EBS volume. If you resize a PVC before the cooldown expires, the Amazon EBS CSI driver (`ebs.csi.aws.com`) fails with this error. This error is an Amazon EBS service limit that applies to all workloads using EBS-backed PVCs.

1. In the Model deployment name field, enter a unique name for the deployment.
2. From the NVIDIA NIM list, select the NVIDIA NIM model that you want to deploy. For more information, see [Supported Models](https://docs.nvidia.com/nim/large-language-models/latest/supported-models.html)
3. In the Number of model server replicas to deploy field, specify a value.
4. From the Model server size list, select a value.

From the Hardware profile list, select a hardware profile.

Important

By default, hardware profiles are hidden in the dashboard navigation menu and user interface, while accelerator profiles remain visible. In addition, user interface components associated with the deprecated accelerator profiles functionality are still displayed. If you enable hardware profiles, the Hardware profiles list is displayed instead of the Accelerator profiles list. To show the Settings → Hardware profiles option in the dashboard navigation menu, and the user interface components associated with hardware profiles, set the`disableHardwareProfiles` value to`false` in the`OdhDashboardConfig` custom resource (CR) in OpenShift. For more information about setting dashboard configuration options, see [Customizing the dashboard](https://docs.redhat.com/en/documentation/red_hat_openshift_ai_cloud_service/1/html/managing_resources/customizing-the-dashboard).

Optional: Click Customize resource requests and limit and update the following values:

1. In the CPUs requests field, specify the number of CPUs to use with your model server. Use the list beside this field to specify the value in cores or millicores.
2. In the CPU limits field, specify the maximum number of CPUs to use with your model server. Use the list beside this field to specify the value in cores or millicores.
3. In the Memory requests field, specify the requested memory for the model server in gibibytes (Gi).
4. In the Memory limits field, specify the maximum memory limit for the model server in gibibytes (Gi).

To require token authentication for inference requests to the deployed model, perform the following actions:

1. Select Require token authentication.
2. In the Service account name field, enter the service account name that the token will be generated for.
3. To add an additional service account, click Add a service account and enter another service account name.

Verification

- Click Deploy.
- Confirm that the deployed model is shown on the Models tab for the project, and on the Model deployments page of the dashboard with a checkmark in the Status column.

### 3.2. Viewing NVIDIA NIM metrics for a NIM modelCopy link

In OpenShift AI, you can observe the following NVIDIA NIM metrics for a NIM model deployed on the NVIDIA NIM model serving platform:

- GPU cache usage over time (ms)
- Current running, waiting, and max requests count
- Tokens count
- Time to first token
- Time per output token
- Request outcomes

You can specify a time range and a refresh interval for these metrics to help you determine, for example, the peak usage hours and model performance at a specified time.

Prerequisites

The`disableKServeMetrics` OpenShift AI dashboard configuration option is set to its default value of`false`:

```plaintext
disableKServeMetrics: false
```

For more information about setting dashboard configuration options, see [Customizing the dashboard](https://docs.redhat.com/en/documentation/red_hat_openshift_ai_cloud_service/1/html/managing_resources/customizing-the-dashboard).

- You have enabled the NVIDIA NIM model serving platform.
- You have deployed a NIM model on the NVIDIA NIM model serving platform.
- A cluster administrator has enabled metrics collection and graph generation for your deployment.

Procedure

From the OpenShift AI dashboard navigation menu, click Data science projects.

The Data science projects page opens.

On the NIM Metrics tab, set the following options:

- Time range - Specifies how long to track the metrics. You can select one of these values: 1 hour, 24 hours, 7 days, and 30 days.
- Refresh interval - Specifies how frequently the graphs on the metrics page are refreshed (to show the latest data). You can select one of these values: 15 seconds, 30 seconds, 1 minute, 5 minutes, 15 minutes, 30 minutes, 1 hour, 2 hours, and 1 day.

Verification

The NIM Metrics tab shows graphs of NIM metrics for the deployed NIM model.

Additional resources

- Scroll down to view data graphs for NIM metrics.
- [NVIDIA NIM observability](https://docs.nvidia.com/nim/large-language-models/latest/observability.html)

### 3.3. Viewing performance metrics for a NIM modelCopy link

You can observe the following performance metrics for a NIM model deployed on the NVIDIA NIM model serving platform:

- Number of requests - The number of requests that have failed or succeeded for a specific model.
- Average response time (ms) - The average time it takes a specific model to respond to requests.
- CPU utilization (%) - The percentage of the CPU limit per model replica that is currently utilized by a specific model.
- Memory utilization (%) - The percentage of the memory limit per model replica that is utilized by a specific model.

You can specify a time range and a refresh interval for these metrics to help you determine, for example, the peak usage hours and model performance at a specified time.

Prerequisites

The`disableKServeMetrics` OpenShift AI dashboard configuration option is set to its default value of`false`:

```plaintext
disableKServeMetrics: false
```

For more information about setting dashboard configuration options, see [Customizing the dashboard](https://docs.redhat.com/en/documentation/red_hat_openshift_ai_cloud_service/1/html/managing_resources/customizing-the-dashboard).

- You have enabled the NVIDIA NIM model serving platform.
- You have deployed a NIM model on the NVIDIA NIM model serving platform.
- A cluster administrator has enabled metrics collection and graph generation for your deployment.

Procedure

From the OpenShift AI dashboard navigation menu, click Data science projects.

The Data science projects page opens.

On the Endpoint performance tab, set the following options:

- Time range - Specifies how long to track the metrics. You can select one of these values: 1 hour, 24 hours, 7 days, and 30 days.
- Refresh interval - Specifies how frequently the graphs on the metrics page are refreshed to show the latest data. You can select one of these values: 15 seconds, 30 seconds, 1 minute, 5 minutes, 15 minutes, 30 minutes, 1 hour, 2 hours, and 1 day.

Verification

The Endpoint performance tab shows graphs of performance metrics for the deployed NIM model.

## Chapter 4. Deploying models on the multi-model serving platformCopy link

For deploying small and medium-sized models, OpenShift AI includes a multi-model serving platform that is based on the ModelMesh component. On the multi-model serving platform, multiple models can be deployed from the same model server and share the server resources.

Important

Starting with OpenShift AI version 2.19, the multi-model serving platform based on ModelMesh is deprecated. You can continue to deploy models on the multi-model serving platform, but it is recommended that you migrate to the single-model serving platform.

For more information or for help on using the single-model serving platform, contact your account manager.

### 4.1. Adding a model server for the multi-model serving platformCopy link

When you have enabled the multi-model serving platform, you must configure a model server to deploy models. If you require extra computing power for use with large datasets, you can assign accelerators to your model server.

Note

In OpenShift AI, Red Hat supports only NVIDIA and AMD GPU accelerators for model serving.

Prerequisites

- Scroll down to view data graphs for performance metrics.
- You have logged in to Red Hat OpenShift AI.
- You have created a data science project that you can add a model server to.
- You have enabled the multi-model serving platform.
- If you want to use a custom model-serving runtime for your model server, you have added and enabled the runtime. See [Adding a custom model-serving runtime](https://docs.redhat.com/en/documentation/red_hat_openshift_ai_cloud_service/1/html/configuring_your_model-serving_platform/configuring_model_servers_on_the_multi_model_serving_platform#adding-a-custom-model-serving-runtime-for-the-multi-model-serving-platform_rhoai-admin).
- If you want to use graphics processing units (GPUs) with your model server, you have enabled GPU support in OpenShift AI. If you use NVIDIA GPUs, see [Enabling NVIDIA GPUs](https://docs.redhat.com/en/documentation/red_hat_openshift_ai_cloud_service/1/html/managing_openshift_ai/enabling-accelerators#enabling-nvidia-gpus_managing-rhoai). If you use AMD GPUs, see [AMD GPU integration](https://docs.redhat.com/en/documentation/red_hat_openshift_ai_cloud_service/1/html/managing_openshift_ai/enabling-accelerators#amd-gpu-integration_managing-rhoai).

Procedure

In the left menu of the OpenShift AI dashboard, click Data science projects.

The Data science projects page opens.

Click the name of the project that you want to configure a model server for.

A project details page opens.

Perform one of the following actions:

- If you see a ​Multi-model serving platform tile, click Add model server on the tile.
- If you do not see any tiles, click the Add model server button.

The Add model server dialog opens.

From the Serving runtime list, select a model-serving runtime that is installed and enabled in your OpenShift AI deployment.

Note

If you are using a custom model-serving runtime with your model server and want to use GPUs, you must ensure that your custom runtime supports GPUs and is appropriately configured to use them.

From the Accelerator profile list, select an accelerator profile.

Important

By default, hardware profiles are hidden in the dashboard navigation menu and user interface, while accelerator profiles remain visible. In addition, user interface components associated with the deprecated accelerator profiles functionality are still displayed. If you enable hardware profiles, the Hardware profiles list is displayed instead of the Accelerator profiles list. To show the Settings → Hardware profiles option in the dashboard navigation menu, and the user interface components associated with hardware profiles, set the`disableHardwareProfiles` value to`false` in the`OdhDashboardConfig` custom resource (CR) in OpenShift. For more information about setting dashboard configuration options, see [Customizing the dashboard](https://docs.redhat.com/en/documentation/red_hat_openshift_ai_cloud_service/1/html/managing_resources/customizing-the-dashboard).

Optional: Click Customize resource requests and limit and update the following values:

1. In the CPUs requests field, specify the number of CPUs to use with your model server. Use the list beside this field to specify the value in cores or millicores.
2. In the CPU limits field, specify the maximum number of CPUs to use with your model server. Use the list beside this field to specify the value in cores or millicores.
3. In the Memory requests field, specify the requested memory for the model server in gibibytes (Gi).
4. In the Memory limits field, specify the maximum memory limit for the model server in gibibytes (Gi).

Optional: In the Token authentication section, select the Require token authentication checkbox to require token authentication for your model server. To finish configuring token authentication, perform the following actions:

1. In the Service account name field, enter a service account name for which the token will be generated. The generated token is created and displayed in the Token secret field when the model server is configured.
2. To add an additional service account, click Add a service account and enter another service account name.

Click Add.

- The model server that you configured is displayed on the Models tab for the project, in the Models and model servers list.

### 4.2. Deleting a model serverCopy link

When you no longer need a model server to host models, you can remove it from your data science project.

Note

When you remove a model server, you also remove the models that are hosted on that model server. As a result, the models are no longer available to applications.

Prerequisites

- Optional: To update the model server, click the action menu (⋮) beside the model server and select Edit model server.
- You have created a data science project and an associated model server.
- You have notified the users of the applications that access the models that the models will no longer be available.

Procedure

From the OpenShift AI dashboard, click Data science projects.

The Data science projects page opens.

Click the name of the project from which you want to delete the model server.

A project details page opens.

Click the action menu (⋮) beside the project whose model server you want to delete and then click Delete model server.

The Delete model server dialog opens.

1. Click the Models tab.
2. Enter the name of the model server in the text field to confirm that you intend to delete it.
3. Click Delete model server.

Verification

- The model server that you deleted is no longer displayed on the Models tab for the project.

### 4.3. Deploying a model by using the multi-model serving platformCopy link

You can deploy trained models on OpenShift AI to enable you to test and implement them into intelligent applications. Deploying a model makes it available as a service that you can access by using an API. This enables you to return predictions based on data inputs.

When you have enabled the multi-model serving platform, you can deploy models on the platform.

Prerequisites

- You have logged in to Red Hat OpenShift AI.
- You have enabled the multi-model serving platform.
- You have created a data science project and added a model server.
- You have access to S3-compatible object storage.
- For the model that you want to deploy, you know the associated folder path in your S3-compatible object storage bucket.

Procedure

In the left menu of the OpenShift AI dashboard, click Data science projects.

The Data science projects page opens.

Click the name of the project that you want to deploy a model in.

A project details page opens.

Configure properties for deploying your model as follows:

From the Model framework list, select a framework for your model.

Note

The Model framework list shows only the frameworks that are supported by the model-serving runtime that you specified when you configured your model server.

To specify the location of the model you want to deploy from S3-compatible object storage, perform one of the following sets of actions:

To use an existing connection

In the Path field, enter the folder path that contains the model in your specified data source.

Note

If you are deploying a registered model version with an existing S3 or URI data connection, some of your connection details might be autofilled. This depends on the type of data connection and the number of matching connections available in your data science project. For example, if only one matching connection exists, fields like the path, URI, endpoint, bucket, and region might populate automatically. Matching connections will be labeled as Recommended.

1. Select Existing connection.
2. From the Name list, select a connection that you previously defined.

To use a new connection

In the Add connection modal, select a Connection type. The S3 compatible object storage and URI options are pre-installed connection types. Additional options might be available if your OpenShift AI administrator added them.

The Add connection form opens with fields specific to the connection type that you selected.

1. To define a new connection that your model can access, select New connection.
2. Enter the connection detail fields.

(Optional) Customize the runtime parameters in the Configuration parameters section:

1. Modify the values in Additional serving runtime arguments to define how the deployed model behaves.
2. Modify the values in Additional environment variables to define variables in the model’s environment.

Verification

- Click Deploy.
- Confirm that the deployed model is shown on the Models tab for the project, and on the Model deployments page of the dashboard with a checkmark in the Status column.

### 4.4. Viewing a deployed modelCopy link

To analyze the results of your work, you can view a list of deployed models on Red Hat OpenShift AI. You can also view the current statuses of deployed models and their endpoints.

Prerequisites

- You have logged in to Red Hat OpenShift AI.

Procedure

From the OpenShift AI dashboard, click Models → Model deployments.

The Model deployments page opens.

For each model, the page shows details such as the model name, the project in which the model is deployed, the model-serving runtime that the model uses, and the deployment status.

1. Optional: For a given model, click the link in the Inference endpoint column to see the inference endpoints for the deployed model.

Verification

- A list of previously deployed data science models is displayed on the Model deployments page.

### 4.5. Updating the deployment properties of a deployed modelCopy link

You can update the deployment properties of a model that has been deployed previously. For example, you can change the model’s connection and name.

Prerequisites

- You have logged in to Red Hat OpenShift AI.
- You have deployed a model on OpenShift AI.

Procedure

From the OpenShift AI dashboard, click Models → Model deployments.

The Model deployments page opens.

Click the action menu (⋮) beside the model whose deployment properties you want to update and click Edit.

The Edit model dialog opens.

Update the deployment properties of the model as follows:

From the Model framework list, select a framework for your model.

Note

The Model framework list shows only the frameworks that are supported by the model-serving runtime that you specified when you configured your model server.

1. In the Model name field, enter a new, unique name for your model.
2. From the Model servers list, select a model server for your model.
3. Optionally, update the connection by specifying an existing connection or by creating a new connection.
4. Click Redeploy.

Verification

- The model whose deployment properties you updated is displayed on the Model deployments page of the dashboard.

### 4.6. Deleting a deployed modelCopy link

You can delete models you have previously deployed. This enables you to remove deployed models that are no longer required.

Prerequisites

- You have logged in to Red Hat OpenShift AI.
- You have deployed a model.

Procedure

From the OpenShift AI dashboard, click Models → Model deployments.

The Model deployments page opens.

Click the action menu (⋮) beside the deployed model that you want to delete and click Delete.

The Delete deployed model dialog opens.

1. Enter the name of the deployed model in the text field to confirm that you intend to delete it.
2. Click Delete deployed model.

Verification

- The model that you deleted is no longer displayed on the Model deployments page.

### 4.7. Configuring monitoring for the multi-model serving platformCopy link

The multi-model serving platform includes model and model server metrics for the ModelMesh component. ModelMesh generates its own set of metrics and does not rely on the underlying model-serving runtimes to provide them. The set of metrics that ModelMesh generates includes metrics for model request rates and timings, model loading and unloading rates, times and sizes, internal queuing delays, capacity and usage, cache state, and least recently-used models. For more information, see [ModelMesh metrics](https://github.com/kserve/modelmesh-serving/blob/main/docs/monitoring.md).

After you have configured monitoring, you can view metrics for the ModelMesh component.

Prerequisites

You have installed the OpenShift CLI (`oc`) as described in the appropriate documentation for your cluster:

- [Installing the OpenShift CLI](https://docs.redhat.com/en/documentation/openshift_dedicated/4/html/cli_tools/openshift-cli-oc#installing-openshift-cli) for OpenShift Dedicated
- [Installing the OpenShift CLI](https://docs.redhat.com/en/documentation/red_hat_openshift_service_on_aws_classic_architecture/4/html/cli_tools/openshift-cli-oc#installing-openshift-cli) for Red Hat OpenShift Service on AWS (classic architecture)

Procedure

In a terminal window, if you are not already logged in to your OpenShift cluster as a cluster administrator, log in to the OpenShift CLI (`oc`) as shown in the following example:

```plaintext
$ oc login <openshift_cluster_url> -u <admin_username> -p <password>
```

Define a`ConfigMap` object in a YAML file called`uwm-cm-conf.yaml` with the following contents:

```plaintext
apiVersion: v1
kind: ConfigMap
metadata:
  name: user-workload-monitoring-config
  namespace: openshift-user-workload-monitoring
data:
  config.yaml: |
    prometheus:
      logLevel: debug
      retention: 15d
```

The`user-workload-monitoring-config` object configures the components that monitor user-defined projects. Observe that the retention time is set to the recommended value of 15 days.

Apply the configuration to create the`user-workload-monitoring-config` object.

```plaintext
$ oc apply -f uwm-cm-conf.yaml
```

Define another`ConfigMap` object in a YAML file called`uwm-cm-enable.yaml` with the following contents:

```plaintext
apiVersion: v1
kind: ConfigMap
metadata:
  name: cluster-monitoring-config
  namespace: openshift-monitoring
data:
  config.yaml: |
    enableUserWorkload: true
```

The`cluster-monitoring-config` object enables monitoring for user-defined projects.

Apply the configuration to create the`cluster-monitoring-config` object.

```plaintext
$ oc apply -f uwm-cm-enable.yaml
```

1. You have [assigned](https://docs.redhat.com/en/documentation/openshift_container_platform/4.19/html/monitoring/configuring-user-workload-monitoring#granting-users-permission-to-monitor-user-defined-projects_preparing-to-configure-the-monitoring-stack-uwm) the`monitoring-rules-view` role to users that will monitor metrics.

### 4.8. Viewing model-serving runtime metrics for the multi-model serving platformCopy link

After a cluster administrator has configured monitoring for the multi-model serving platform, non-admin users can use the OpenShift web console to view model-serving runtime metrics for the ModelMesh component.

Prerequisites

- You have access to the OpenShift cluster as a developer or as a user with view permissions for the project that you are viewing metrics for.
- You are familiar with querying metrics in user-defined projects. See [Monitoring project and application metrics using the Developer perspective in Red Hat OpenShift Dedicated](https://docs.redhat.com/en/documentation/openshift_dedicated/4/html-single/building_applications/index#odc-monitoring-project-and-application-metrics-using-developer-perspective) or [Monitoring project and application metrics using the Developer perspective in Red Hat OpenShift Service on AWS (classic architecture)](https://docs.redhat.com/en/documentation/red_hat_openshift_service_on_aws_classic_architecture/4/html/building_applications/odc-monitoring-project-and-application-metrics-using-developer-perspective).

Procedure

1. Log in to the OpenShift web console.
2. Switch to the Developer perspective.
3. In the left menu, click Observe.
4. As described in [Monitoring your project metrics in Red Hat OpenShift Dedicated](https://docs.redhat.com/en/documentation/openshift_dedicated/4/html/building_applications/odc-monitoring-project-and-application-metrics-using-developer-perspective#odc-monitoring-your-project-metrics_monitoring-project-and-application-metrics-using-developer-perspective) or [Monitoring your project metrics in Red Hat OpenShift Service on AWS (classic architecture)](https://docs.redhat.com/en/documentation/red_hat_openshift_service_on_aws_classic_architecture/4/html/building_applications/odc-monitoring-project-and-application-metrics-using-developer-perspective#odc-monitoring-your-project-metrics_monitoring-project-and-application-metrics-using-developer-perspective), use the web console to run queries for`modelmesh_*` metrics.

### 4.9. Viewing performance metrics for all models on a model serverCopy link

You can monitor the following metrics for all the models that are deployed on a model server:

- HTTP requests per 5 minutes - The number of HTTP requests that have failed or succeeded for all models on the server.
- Average response time (ms) - For all models on the server, the average time it takes the model server to respond to requests.
- CPU utilization (%) - The percentage of the CPU’s capacity that is currently being used by all models on the server.
- Memory utilization (%) - The percentage of the system’s memory that is currently being used by all models on the server.

You can specify a time range and a refresh interval for these metrics to help you determine, for example, when the peak usage hours are and how the models are performing at a specified time.

Prerequisites

- You have installed Red Hat OpenShift AI.
- On the OpenShift cluster where OpenShift AI is installed, user workload monitoring is enabled.
- You have logged in to Red Hat OpenShift AI.
- You have deployed models on the multi-model serving platform.

Procedure

From the OpenShift AI dashboard navigation menu, click Data science projects.

The Data science projects page opens.

Optional: On the metrics page for the model server, set the following options:

- Time range - Specifies how long to track the metrics. You can select one of these values: 1 hour, 24 hours, 7 days, and 30 days.
- Refresh interval - Specifies how frequently the graphs on the metrics page are refreshed (to show the latest data). You can select one of these values: 15 seconds, 30 seconds, 1 minute, 5 minutes, 15 minutes, 30 minutes, 1 hour, 2 hours, and 1 day.

Verification

On the metrics page for the model server, the graphs provide data on performance metrics.

### 4.10. Viewing HTTP request metrics for a deployed modelCopy link

You can view a graph that illustrates the HTTP requests that have failed or succeeded for a specific model that is deployed on the multi-model serving platform.

Prerequisites

The following dashboard configuration options are set to the default values as shown:

```plaintext
disablePerformanceMetrics:false
disableKServeMetrics:false
```

For more information about setting dashboard configuration options, see [Customizing the dashboard](https://docs.redhat.com/en/documentation/red_hat_openshift_ai_cloud_service/1/html/managing_resources/customizing-the-dashboard).

- Scroll down to view data graphs for HTTP requests per 5 minutes, average response time, CPU utilization, and memory utilization.
- You have installed Red Hat OpenShift AI.
- On the OpenShift cluster where OpenShift AI is installed, user workload monitoring is enabled.
- You have logged in to Red Hat OpenShift AI.
- You have deployed models on the multi-model serving platform.

Procedure

Optional: On the Endpoint performance tab, set the following options:

- Time range - Specifies how long to track the metrics. You can select one of these values: 1 hour, 24 hours, 7 days, and 30 days.
- Refresh interval - Specifies how frequently the graphs on the metrics page are refreshed (to show the latest data). You can select one of these values: 15 seconds, 30 seconds, 1 minute, 5 minutes, 15 minutes, 30 minutes, 1 hour, 2 hours, and 1 day.

Verification

The Endpoint performance tab shows a graph of the HTTP metrics for the model.

## Chapter 5. Making inference requests to deployed modelsCopy link

When you deploy a model, it is available as a service that you can access with API requests. This allows you to get predictions from your model based on the data you provide in the request.

### 5.1. Accessing the authentication token for a deployed modelCopy link

If you secured your model inference endpoint by enabling token authentication, you must know how to access your authentication token so that you can specify it in your inference requests.

Prerequisites

- You have logged in to Red Hat OpenShift AI.
- You have deployed a model by using the single-model serving platform.

Procedure

From the OpenShift AI dashboard, click Data science projects.

The Data science projects page opens.

Click the name of the project that contains your deployed model.

A project details page opens.

In the Models and model servers list, expand the section for your model.

Your authentication token is shown in the Token authentication section, in the Token secret field.

1. Click the Models tab.
2. Optional: To copy the authentication token for use in an inference request, click the Copy button () next to the token value.

### 5.2. Accessing the inference endpoint for a deployed modelCopy link

To make inference requests to your deployed model, you must know how to access the inference endpoint that is available.

For a list of paths to use with the supported runtimes and example commands, see [Inference endpoints](https://docs.redhat.com/en/documentation/red_hat_openshift_ai_cloud_service/1/html/deploying_models/making_inference_requests_to_deployed_models#inference-endpoints_rhoai-user).

Prerequisites

- You have logged in to Red Hat OpenShift AI.
- You have deployed a model by using the single-model serving platform.
- If you enabled token authentication for your deployed model, you have the associated token value.

Procedure

From the OpenShift AI dashboard, click Models → Model deployments.

The inference endpoint for the model is shown in the Inference endpoint field.

1. Depending on what action you want to perform with the model (and if the model supports that action), copy the inference endpoint and then add a path to the end of the URL.
2. Use the endpoint to make API requests to your deployed model.

### 5.3. Making inference requests to models deployed on the single-model serving platformCopy link

When you deploy a model by using the single-model serving platform, the model is available as a service that you can access using API requests. This enables you to return predictions based on data inputs. To use API requests to interact with your deployed model, you must know the inference endpoint for the model.

In addition, if you secured your inference endpoint by enabling token authentication, you must know how to access your authentication token so that you can specify this in your inference requests.

### 5.4. Inference endpointsCopy link

These examples show how to use inference endpoints to query the model.

Note

If you enabled token authentication when deploying the model, add the`Authorization` header and specify a token value.

#### 5.4.1. Caikit TGIS ServingRuntime for KServeCopy link

- `:443/api/v1/task/text-generation`
- `:443/api/v1/task/server-streaming-text-generation`

Example command

```plaintext
curl --json '{"model_id": "<model_name__>", "inputs": "<text>"}' https://<inference_endpoint_url>:443/api/v1/task/server-streaming-text-generation -H 'Authorization: Bearer <token>'
```

#### 5.4.2. Caikit Standalone ServingRuntime for KServeCopy link

If you are serving multiple models, you can query`/info/models` or`:443 caikit.runtime.info.InfoService/GetModelsInfo` to view a list of served models.

REST endpoints

- `/api/v1/task/embedding`
- `/api/v1/task/embedding-tasks`
- `/api/v1/task/sentence-similarity`
- `/api/v1/task/sentence-similarity-tasks`
- `/api/v1/task/rerank`
- `/api/v1/task/rerank-tasks`
- `/info/models`
- `/info/version`
- `/info/runtime`

gRPC endpoints

- `:443 caikit.runtime.Nlp.NlpService/EmbeddingTaskPredict`
- `:443 caikit.runtime.Nlp.NlpService/EmbeddingTasksPredict`
- `:443 caikit.runtime.Nlp.NlpService/SentenceSimilarityTaskPredict`
- `:443 caikit.runtime.Nlp.NlpService/SentenceSimilarityTasksPredict`
- `:443 caikit.runtime.Nlp.NlpService/RerankTaskPredict`
- `:443 caikit.runtime.Nlp.NlpService/RerankTasksPredict`
- `:443 caikit.runtime.info.InfoService/GetModelsInfo`
- `:443 caikit.runtime.info.InfoService/GetRuntimeInfo`

Note

By default, the Caikit Standalone Runtime exposes REST endpoints. To use gRPC protocol, manually deploy a custom Caikit Standalone ServingRuntime. For more information, see [Adding a custom model-serving runtime for the single-model serving platform](https://docs.redhat.com/en/documentation/red_hat_openshift_ai_cloud_service/1/html/configuring_your_model-serving_platform/configuring_model_servers_on_the_single_model_serving_platform#adding-a-custom-model-serving-runtime-for-the-single-model-serving-platform_rhoai-admin).

An example manifest is available in the [caikit-tgis-serving GitHub repository](https://github.com/opendatahub-io/caikit-tgis-serving/blob/main/demo/kserve/custom-manifests/caikit/caikit-standalone/caikit-standalone-servingruntime-grpc.yaml).

Example command

REST

```plaintext
curl -H 'Content-Type: application/json' -d '{"inputs": "<text>", "model_id": "<model_id>"}' <inference_endpoint_url>/api/v1/task/embedding -H 'Authorization: Bearer <token>'
```

gRPC

```plaintext
grpcurl -d '{"text": "<text>"}' -H \"mm-model-id: <model_id>\" <inference_endpoint_url>:443 caikit.runtime.Nlp.NlpService/EmbeddingTaskPredict -H 'Authorization: Bearer <token>'
```

#### 5.4.3. TGIS Standalone ServingRuntime for KServeCopy link

Important

The Text Generation Inference Server (TGIS) Standalone ServingRuntime for KServe is deprecated. For more information, see [OpenShift AI release notes](https://docs.redhat.com/en/documentation/red_hat_openshift_ai_cloud_service/1/html/release_notes/index).

`:443 fmaas.GenerationService/GenerateStream`

Note

To query the endpoint for the TGIS standalone runtime, you must also download the files in the [proto](https://github.com/opendatahub-io/text-generation-inference/blob/main/proto) directory of the OpenShift AI`text-generation-inference` repository.

- `:443 fmaas.GenerationService/Generate`

Example command

```plaintext
grpcurl -proto text-generation-inference/proto/generation.proto -d '{"requests": [{"text":"<text>"}]}' -H 'Authorization: Bearer <token>' -insecure <inference_endpoint_url>:443 fmaas.GenerationService/Generate
```

#### 5.4.4. OpenVINO Model ServerCopy link

- `/v2/models/ /infer`

Example command

```plaintext
curl -ks <inference_endpoint_url>/v2/models/<model_name>/infer -d '{ "model_name": "<model_name>", "inputs": [{ "name": "<name_of_model_input>", "shape": [<shape>], "datatype": "<data_type>", "data": [<data>] }]}' -H 'Authorization: Bearer <token>'
```

#### 5.4.5. vLLM NVIDIA GPU ServingRuntime for KServeCopy link

`:443/detokenize`

Note

As of vLLM v0.5.5, you must provide a chat template while querying a model using the`/v1/chat/completions` endpoint. If your model does not include a predefined chat template, you can use the`chat-template` command-line parameter to specify a chat template in your custom vLLM runtime, as shown in the example. Replace` ` with the path to your template.

```plaintext
containers:
  - args:
      - --chat-template=<CHAT_TEMPLATE>
```

You can use the chat templates that are available as`.jinja` files [here](https://github.com/opendatahub-io/vllm/tree/main/examples) or with the vLLM image under`/app/data/template`. For more information, see [Chat templates](https://huggingface.co/docs/transformers/main/chat_templating).

- The vLLM runtime is compatible with the OpenAI REST API. For a list of models that the vLLM runtime supports, see [Supported models](https://docs.vllm.ai/en/latest/models/supported_models.html).
- To use the embeddings inference endpoint in vLLM, you must use an embeddings model that the vLLM supports. You cannot use the embeddings endpoint with generative models. For more information, see [Supported embeddings models in vLLM](https://github.com/vllm-project/vllm/pull/3734).

As indicated by the paths shown, the single-model serving platform uses the HTTPS port of your OpenShift router (usually port 443) to serve external API requests.

Example command

```plaintext
curl -v https://<inference_endpoint_url>:443/v1/chat/completions -H "Content-Type: application/json" -d '{ "messages": [{ "role": "<role>", "content": "<content>" }] -H 'Authorization: Bearer <token>'
```

#### 5.4.6. vLLM Intel Gaudi Accelerator ServingRuntime for KServeCopy link

See [vLLM NVIDIA GPU ServingRuntime for KServe](https://docs.redhat.com/en/documentation/red_hat_openshift_ai_cloud_service/1/html/deploying_models/making_inference_requests_to_deployed_models#vllm_nvidia_gpu_servingruntime_for_kserve).

#### 5.4.7. vLLM AMD GPU ServingRuntime for KServeCopy link

See [vLLM NVIDIA GPU ServingRuntime for KServe](https://docs.redhat.com/en/documentation/red_hat_openshift_ai_cloud_service/1/html/deploying_models/making_inference_requests_to_deployed_models#vllm_nvidia_gpu_servingruntime_for_kserve).

#### 5.4.8. vLLM Spyre AI Accelerator ServingRuntime for KServeCopy link

Important

Support for IBM Spyre AI Accelerators on x86 is currently available in Red Hat OpenShift AI as a Technology Preview feature. Technology Preview features are not supported with Red Hat production service level agreements (SLAs) and might not be functionally complete. Red Hat does not recommend using them in production. These features provide early access to upcoming product features, enabling customers to test functionality and provide feedback during the development process.

For more information about the support scope of Red Hat Technology Preview features, see [Technology Preview Features Support Scope](https://access.redhat.com/support/offerings/techpreview/).

See [vLLM NVIDIA GPU ServingRuntime for KServe](https://docs.redhat.com/en/documentation/red_hat_openshift_ai_cloud_service/1/html/deploying_models/making_inference_requests_to_deployed_models#vllm_nvidia_gpu_servingruntime_for_kserve).

#### 5.4.9. NVIDIA Triton Inference ServerCopy link

REST endpoints

- `v2/models/[/versions/]/infer`
- `v2/models/ [/versions/]`
- `v2/health/ready`
- `v2/health/live`
- `v2/models/ [/versions/]/ready`
- `v2`

Note

ModelMesh does not support the following REST endpoints:

- `v2/health/live`
- `v2/health/ready`
- `v2/models/ [/versions/]/ready`

Example command

```plaintext
curl -ks <inference_endpoint_url>/v2/models/<model_name>/infer -d '{ "model_name": "<model_name>", "inputs": [{ "name": "<name_of_model_input>", "shape": [<shape>], "datatype": "<data_type>", "data": [<data>] }]}' -H 'Authorization: Bearer <token>'
```

gRPC endpoints

- `:443 inference.GRPCInferenceService/ModelInfer`
- `:443 inference.GRPCInferenceService/ModelReady`
- `:443 inference.GRPCInferenceService/ModelMetadata`
- `:443 inference.GRPCInferenceService/ServerReady`
- `:443 inference.GRPCInferenceService/ServerLive`
- `:443 inference.GRPCInferenceService/ServerMetadata`

Example command

```plaintext
grpcurl -cacert ./openshift_ca_istio_knative.crt -proto ./grpc_predict_v2.proto -d @ -H "Authorization: Bearer <token>" <inference_endpoint_url>:443 inference.GRPCInferenceService/ModelMetadata
```

#### 5.4.10. Seldon MLServerCopy link

REST endpoints

- `v2/models/[/versions/]/infer`
- `v2/models/ [/versions/]`
- `v2/health/ready`
- `v2/health/live`
- `v2/models/ [/versions/]/ready`
- `v2`

Example command

```plaintext
curl -ks <inference_endpoint_url>/v2/models/<model_name>/infer -d '{ "model_name": "<model_name>", "inputs": [{ "name": "<name_of_model_input>", "shape": [<shape>], "datatype": "<data_type>", "data": [<data>] }]}' -H 'Authorization: Bearer <token>'
```

gRPC endpoints

- `:443 inference.GRPCInferenceService/ModelInfer`
- `:443 inference.GRPCInferenceService/ModelReady`
- `:443 inference.GRPCInferenceService/ModelMetadata`
- `:443 inference.GRPCInferenceService/ServerReady`
- `:443 inference.GRPCInferenceService/ServerLive`
- `:443 inference.GRPCInferenceService/ServerMetadata`

Example command

```plaintext
grpcurl -cacert ./openshift_ca_istio_knative.crt -proto ./grpc_predict_v2.proto -d @ -H "Authorization: Bearer <token>" <inference_endpoint_url>:443 inference.GRPCInferenceService/ModelMetadata
```

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



## Data science pipelines

Working with data science pipelines | Red Hat OpenShift AI Cloud Service | 1 | Red Hat Documentation

 

 

 

 

 

 

 Updates for Red Hat OpenShift AI Cloud Service are only provided up to the end of October 2025. 

 

# Working with data science pipelines

---

Red Hat OpenShift AI Cloud Service 1

## Work with data science pipelines from Red Hat OpenShift AI Cloud Service

Legal Notice

Abstract

Enhance your data science projects on OpenShift AI by building portable machine learning (ML) workflows with data science pipelines.

---

## PrefaceCopy link

As a data scientist, you can enhance your data science projects on OpenShift AI by building portable machine learning (ML) workflows with data science pipelines, using Docker containers. This enables you to standardize and automate machine learning workflows to enable you to develop and deploy your data science models.

For example, the steps in a machine learning workflow might include items such as data extraction, data processing, feature extraction, model training, model validation, and model serving. Automating these activities enables your organization to develop a continuous process of retraining and updating a model based on newly received data. This can help address challenges related to building an integrated machine learning deployment and continuously operating it in production.

You can also use the Elyra JupyterLab extension to create and run data science pipelines within JupyterLab. For more information, see [Working with pipelines in JupyterLab](https://docs.redhat.com/en/documentation/red_hat_openshift_ai_cloud_service/1/html/working_with_data_science_pipelines/working-with-pipelines-in-jupyterlab_ds-pipelines).

To use a data science pipeline in OpenShift AI, you need the following components:

Pipeline: A pipeline defines the configuration of your machine learning workflow and the relationship between each component in the workflow.

- Pipeline code: A definition of your pipeline in a YAML file.
- Pipeline graph: A graphical illustration of the steps executed in a pipeline run and the relationship between them.

Pipeline experiment: A workspace where you can try different configurations of your pipelines. You can use experiments to organize your runs into logical groups.

- Archived pipeline experiment: An archived pipeline experiment.
- Pipeline artifact: An output artifact produced by a pipeline component.
- Pipeline execution: The execution of a task in a pipeline.

Pipeline run: An execution of your pipeline.

- Active run: A pipeline run that is executing, or stopped.
- Scheduled run: A pipeline run that is scheduled to execute at least once.
- Archived run: An archived pipeline run.

This feature is based on Kubeflow Pipelines 2.0. Use the latest Kubeflow Pipelines 2.0 SDK to build your data science pipeline in Python code. After you have built your pipeline, use the SDK to compile it into an Intermediate Representation (IR) YAML file. The OpenShift AI user interface enables you to track and manage pipelines, experiments, and pipeline runs. To view a record of previously executed, scheduled, and archived runs, you can go to Data science pipelines → Runs, or you can select an experiment from the Experiments → Experiments and runs to access all of its pipeline runs. You can manage incremental changes to pipelines in OpenShift AI by using versioning. This allows you to develop and deploy pipelines iteratively, preserving a record of your changes.

You can store your pipeline artifacts in an S3-compatible object storage bucket so that you do not consume local storage. To do this, you must first configure write access to your S3 bucket on your storage account.

## Chapter 1. Managing data science pipelinesCopy link

### 1.1. Configuring a pipeline serverCopy link

Before you can successfully create a pipeline in OpenShift AI, you must configure a pipeline server. This task includes configuring where your pipeline artifacts and data are stored.

Note

You are not required to specify any storage directories when configuring a connection for your pipeline server. When you import a pipeline, the`/pipelines` folder is created in the`root` folder of the bucket, containing a YAML file for the pipeline. If you upload a new version of the same pipeline, a new YAML file with a different ID is added to the`/pipelines` folder.

When you run a pipeline, the artifacts are stored in the`/pipeline-name` folder in the`root` folder of the bucket.

Prerequisites

If you are configuring a pipeline server with an external MySQL database, your database must use at least MySQL version 5.x. However, Red Hat recommends that you use MySQL version 8.x.

Note

The`mysql_native_password` authentication plugin is required for the ML Metadata component to successfully connect to your database.`mysql_native_password` is disabled by default in MySQL 8.4 and later. If your database uses MySQL 8.4 or later, you must update your MySQL deployment to enable the`mysql_native_password` plugin.

For more information about enabling the`mysql_native_password` plugin, see [Native Pluggable Authentication](https://dev.mysql.com/doc/refman/8.4/en/native-pluggable-authentication.html) in the MySQL documentation.

- You have logged in to Red Hat OpenShift AI.
- You have created a data science project that you can add a pipeline server to.
- You have an existing S3-compatible object storage bucket and you have configured write access to your S3 bucket on your storage account.
- If you are configuring a pipeline server for production pipeline workloads, you have an existing external MySQL or MariaDB database.
- If you are configuring a pipeline server with a MariaDB database, your database must use MariaDB version 10.3 or later. However, Red Hat recommends that you use at least MariaDB version 10.5.

Procedure

From the OpenShift AI dashboard, click Data science projects.

The Data science projects page opens.

Click the name of the project that you want to configure a pipeline server for.

A project details page opens.

Click Configure pipeline server.

The Configure pipeline server dialog opens.

In the Object storage connection section, provide values for the mandatory fields:

In the Bucket field, enter the name of your S3-compatible object storage bucket.

Important

If you specify incorrect connection settings, you cannot update these settings on the same pipeline server. Therefore, you must delete the pipeline server and configure another one.

If you want to use an existing artifact that was not generated by a task in a pipeline, you can use the [kfp.dsl.importer component](https://kubeflow-pipelines.readthedocs.io/en/latest/source/dsl.html#kfp.dsl.importer) to import the artifact from its URI. You can only import these artifacts to the S3-compatible object storage bucket that you define in the Bucket field in your pipeline server configuration. For more information about the`kfp.dsl.importer` component, see [Special Case: Importer Components](https://www.kubeflow.org/docs/components/pipelines/user-guides/components/importer-component/).

1. In the Access key field, enter the access key ID for the S3-compatible object storage provider.
2. In the Secret key field, enter the secret access key for the S3-compatible object storage account that you specified.
3. In the Endpoint field, enter the endpoint of your S3-compatible object storage bucket.
4. In the Region field, enter the default region of your S3-compatible object storage account.

In the Database section, choose one of the following options to specify where to store your pipeline metadata and run information:

Select Default database on the cluster to deploy a MariaDB database in your project.

Important

The Default database on the cluster option is intended for development and testing purposes only. For production pipeline workloads, select the External MySQL database option to use an external MySQL or MariaDB database.

Select External MySQL database to add a new connection to an external MySQL or MariaDB database that your pipeline server can access.

1. In the Host field, enter the database hostname.
2. In the Port field, enter the database port.
3. In the Username field, enter the default user name that is connected to the database.
4. In the Password field, enter the password for the default user account.
5. In the Database field, enter the database name.

Verification

On the Pipelines tab for the project:

- Click Configure pipeline server.
- The Import pipeline button is available.
- When you click the action menu (⋮) and then click Manage pipeline server configuration, the pipeline server details are displayed.

#### 1.1.1. Configuring a pipeline server with an external Amazon RDS databaseCopy link

To configure a pipeline server with an external Amazon Relational Database Service (RDS) database, you must configure OpenShift AI to trust the certificates issued by its certificate authorities (CA).

Important

If you are configuring a pipeline server for production pipeline workloads, Red Hat recommends that you use an external MySQL or MariaDB database.

Prerequisites

- You have cluster administrator privileges for your OpenShift cluster.
- You have logged in to Red Hat OpenShift AI.
- You have created a data science project that you can add a pipeline server to.
- You have an existing S3-compatible object storage bucket, and you have configured your storage account with write access to your S3 bucket.

Procedure

Before configuring your pipeline server, from [Amazon RDS: Certificate bundles by AWS Region](https://docs.aws.amazon.com/AmazonRDS/latest/UserGuide/UsingWithRDS.SSL.html#UsingWithRDS.SSL.CertificatesAllRegions), download the PEM certificate bundle for the region that the database was created in.

For example, if the database was created in the`us-east-1` region, download`us-east-1-bundle.pem`.

In a terminal window, log in to the OpenShift cluster where OpenShift AI is deployed.

```plaintext
oc login api.<cluster_name>.<cluster_domain>:6443 --web
```

Run the following command to fetch the current OpenShift AI trusted CA configuration and store it in a new file:

```plaintext
oc get dscinitializations.dscinitialization.opendatahub.io default-dsci -o json | jq '.spec.trustedCABundle.customCABundle' > /tmp/my-custom-ca-bundles.crt
```

Run the following command to append the PEM certificate bundle that you downloaded to the new custom CA configuration file:

```plaintext
cat us-east-1-bundle.pem >> /tmp/my-custom-ca-bundles.crt
```

Run the following command to update the OpenShift AI trusted CA configuration to trust certificates issued by the CAs included in the new custom CA configuration file:

```plaintext
oc patch dscinitialization default-dsci --type='json' -p='[{"op":"replace","path":"/spec/trustedCABundle/customCABundle","value":"'"$(awk '{printf "%s\\n", $0}' /tmp/my-custom-ca-bundles.crt)"'"}]'
```

1. Configure a pipeline server, as described in [Configuring a pipeline server](https://docs.redhat.com/en/documentation/red_hat_openshift_ai_cloud_service/1/html/working_with_data_science_pipelines/managing-data-science-pipelines_ds-pipelines#configuring-a-pipeline-server_ds-pipelines).

Verification

- The pipeline server starts successfully.
- You can import and run data science pipelines.

### 1.2. Defining a pipelineCopy link

The Kubeflow Pipelines SDK enables you to define end-to-end machine learning and data pipelines. Use the latest Kubeflow Pipelines 2.0 SDK to build your data science pipeline in Python code. After you have built your pipeline, use the SDK to compile it into an Intermediate Representation (IR) YAML file. For more information about compiling pipelines, see Compiling the pipeline YAML with the Kubeflow Pipelines SDK and Compiling Kubernetes-native manifests with the Kubeflow Pipelines SDK. Compiling to Kubernetes-native manifests is optional and applies only when your pipeline server is configured to use Kubernetes API storage. After defining the pipeline, you can import the YAML file to the OpenShift AI dashboard to enable you to configure its execution settings.

Important

If you are using OpenShift AI on a cluster running in FIPS mode, any custom container images for data science pipelines must be based on UBI 9 or RHEL 9. This ensures compatibility with FIPS-approved pipeline components and prevents errors related to mismatched OpenSSL or GNU C Library (glibc) versions.

You can also use the Elyra JupyterLab extension to create and run data science pipelines within JupyterLab. For more information about creating pipelines in JupyterLab, see [Working with pipelines in JupyterLab](https://docs.redhat.com/en/documentation/red_hat_openshift_ai_cloud_service/1/html/working_with_data_science_pipelines/working-with-pipelines-in-jupyterlab_ds-pipelines). For more information about the Elyra JupyterLab extension, see [Elyra Documentation](https://elyra.readthedocs.io/en/stable/getting_started/overview.html).

#### 1.2.1. Compiling the pipeline YAML with the Kubeflow Pipelines SDKCopy link

Before you can define your pipeline in the cluster, you must convert your Python-defined pipeline into YAML format. You can use the Kubeflow Pipelines (KFP) Software Development Kit (SDK) to compile your pipeline code into a deployable YAML file for declarative GitOps deployment.

Prerequisites

- You have installed Python 3.11 or later in your local environment.
- You have installed the Kubeflow Pipelines SDK package (`kfp`) version 2.14.3 or later.
- You have a valid Python pipeline definition file.

Procedure

Compile your pipeline by using the KFP SDK to generate the pipeline YAML file.

In the following example, replace.py with the name of your Python pipeline file and specify an output file for the compiled YAML:

```plaintext
$ kfp dsl compile \
    --py <pipeline_file>.py \
    --output <compiled_pipeline_file>.yaml
```

Note

The generated`.yaml` file contains the compiled pipeline specification in YAML format. You can use this content as the value of the`pipelineSpec` field when you create a`PipelineVersion` custom resource (CR). You can also store the file in Git for declarative or GitOps-based deployment.

Verification

Verify that the generated file includes a`pipelineSpec` key followed by the compiled pipeline definition:

```plaintext
$ head -n 10 <compiled_pipeline_file>.yaml
```

Additional resources

- [Compiling a pipeline with the Kubeflow Pipelines SDK](https://www.kubeflow.org/docs/components/pipelines/user-guides/core-functions/compile-a-pipeline/)

#### 1.2.2. Compiling Kubernetes-native manifests with the Kubeflow Pipelines SDKCopy link

If your pipeline server uses the Kubernetes native API mode, you can compile your pipeline directly to Kubernetes manifests. The output includes`Pipeline` and`PipelineVersion` custom resources with`spec.pipelineSpec` and, when you use Kubernetes resource configuration, an optional`spec.platformSpec`.

Prerequisites

- You have installed Python 3.11 or later in your local environment.
- You have installed the Kubeflow Pipelines SDK package (`kfp`) version 2.14.3 or later.
- You have a valid Python pipeline definition file.

Procedure

Save the following code as a new file named`compile.py` in your working directory.

The example uses the`KubernetesManifestOptions` class from the`kfp.compiler.compiler_utils` module to define pipeline metadata such as the name, version, and namespace.

Example compile script

```plaintext
from kfp import dsl, compiler
from kfp.compiler.compiler_utils import KubernetesManifestOptions

@dsl.pipeline(name="<pipeline_name>")
def my_pipeline():
    pass  # define your tasks

compiler.Compiler().compile(
    pipeline_func=my_pipeline,
    package_path="<output_file>.yaml",
    kubernetes_manifest_format=True,
    kubernetes_manifest_options=KubernetesManifestOptions(
        pipeline_name="<pipeline_name>",
        pipeline_version_name="<version_name>",
        namespace="<namespace>",
        include_pipeline_manifest=True,
    ),
)
```

Run the script to compile your pipeline and generate the Kubernetes manifests:

```plaintext
$ python compile.py
```

Verification

Verify that the compiled output includes the expected resources:

```yaml
apiVersion: pipelines.kubeflow.org/v2beta1
kind: Pipeline
---
apiVersion: pipelines.kubeflow.org/v2beta1
kind: PipelineVersion
spec:
  pipelineSpec: ...
  platformSpec: ...   # present when Kubernetes resource configuration is used
```

Additional resources

- [Compiling for Kubernetes native API mode](https://www.kubeflow.org/docs/components/pipelines/user-guides/core-functions/compile-a-pipeline/#compiling-for-kubernetes-native-api-mode)

#### 1.2.3. Defining a pipeline by using the Kubernetes APICopy link

You can define data science pipelines and pipeline versions by using the Kubernetes API, which stores them as custom resources in the cluster instead of the internal database. This approach makes it easier to use OpenShift GitOps (Argo CD) or similar tools to manage pipelines and pipeline versions, while still allowing you to manage them through the OpenShift AI dashboard, API, and the Kubeflow Pipelines (KFP) Software Development Kit (SDK). You can generate the required manifests by using the Kubeflow Pipelines SDK; see Compiling the pipeline YAML with the Kubeflow Pipelines SDK or Compiling Kubernetes-native manifests with the Kubeflow Pipelines SDK.

Note

If your pipeline server is already configured to use Kubernetes API storage, you can still use the OpenShift AI dashboard and REST API to view pipeline details, run pipelines, and create schedules. In this mode, the Kubernetes API acts as the storage backend, so your existing tools continue to work as expected.

Prerequisites

You have installed the OpenShift CLI (`oc`) as described in the appropriate documentation for your cluster:

- [Installing the OpenShift CLI](https://docs.redhat.com/en/documentation/openshift_dedicated/4/html/cli_tools/openshift-cli-oc#installing-openshift-cli) for OpenShift Dedicated
- [Installing the OpenShift CLI](https://docs.redhat.com/en/documentation/red_hat_openshift_service_on_aws_classic_architecture/4/html/cli_tools/openshift-cli-oc#installing-openshift-cli) for Red Hat OpenShift Service on AWS (classic architecture)

If you plan to create a`PipelineVersion` custom resource, you have either:

- Compiled your Python pipeline to IR YAML by using the KFP SDK. See Compiling the pipeline YAML with the Kubeflow Pipelines SDK.
- Compiled Kubernetes-native manifests by using the KFP SDK. See Compiling Kubernetes-native manifests with the Kubeflow Pipelines SDK.

Procedure

In a terminal window, log in to your OpenShift cluster by using the OpenShift CLI (`oc`):

```plaintext
$ oc login -u <user_name>
```

When prompted, enter the OpenShift server URL, connection type, and your password.

To configure the pipeline server to use Kubernetes API storage instead of the default`database` option, set the`spec.apiServer.pipelineStore` field to`kubernetes` in your project’s`DataSciencePipelinesApplication`(DSPA) custom resource.

In the following command, replace with the name of your DSPA custom resource, and replace with the name of your project:

```plaintext
$ oc patch dspa <dspa_name> -n <namespace> \
  --type=merge \
  -p {"spec": {"apiServer": {"pipelineStore": "kubernetes"}}}
```

Warning

When you switch the pipeline server from database storage to Kubernetes API storage, existing pipelines that were stored in the internal database are no longer visible in the OpenShift AI dashboard or REST API. To view or manage those pipelines again, change the`spec.apiServer.pipelineStore` field back to`database`.

Define a`Pipeline` custom resource in a YAML file with the following contents:

Example pipeline definition

```yaml
apiVersion: pipelines.kubeflow.org/v2beta1
kind: Pipeline
metadata:
  name: <name>
  namespace: <namespace>
spec:
  displayName: <displayName>
```

- `name`: The immutable Kubernetes resource name of your pipeline.
- `namespace`: The name of your project.
- `displayName`: The user-friendly display name of your pipeline, which is shown in the dashboard and REST API.

Apply the pipeline definition to create the`Pipeline` custom resource in your cluster.

In the following command, replace with the name of your YAML file:

Example command

```plaintext
$ oc apply -f <pipeline_yaml_file>.yaml
```

Alternatively, if you compiled Kubernetes-native manifests with the KFP SDK, you can apply the generated file directly without manually creating separate YAML files:

```plaintext
$ oc apply -f <output_file>.yaml
```

The generated file includes both`Pipeline` and`PipelineVersion` resources. You can skip the following manual definition steps and proceed to the verification step.

Define a`PipelineVersion` custom resource in a YAML file with the following contents:

Example pipeline version definition

```yaml
apiVersion: pipelines.kubeflow.org/v2beta1
kind: PipelineVersion
metadata:
  name: <name>
  namespace: <namespace>
spec:
  pipelineName: <pipelineName>
  displayName: <displayName>
  description: This is the first version of the pipeline.
  pipelineSpec:
        # ... YAML generated by compiling Python pipeline with KFP SDK ...
```

- `name`: The name of your pipeline version.
- `namespace`: The name of your project.
- `pipelineName`: The immutable Kubernetes resource name of your pipeline. This value must match the`metadata.name` value in the`Pipeline` custom resource.
- `displayName`: The user-friendly display name of your pipeline version, which is shown in the dashboard and REST API.
- `pipelineSpec`: The YAML content that you generated by using the Kubeflow Pipelines (KFP) SDK.

Apply the pipeline version definition to create the`PipelineVersion` custom resource in your cluster.

In the following command, replace with the name of your YAML file:

Example command

```plaintext
$ oc apply -f <pipeline_version_yaml_file>.yaml
```

After creating the pipeline version, the system automatically applies the following labels to the pipeline version for easier filtering:

Example automatic labels

```yaml
pipelines.kubeflow.org/pipeline-id: <metadata.uid of the pipeline>
pipelines.kubeflow.org/pipeline: <pipeline name>
```

Verification

Check that the`Pipeline` custom resource was successfully created:

```plaintext
$ oc get pipeline <pipeline_name> -n <namespace>
```

Check that the`PipelineVersion` custom resource was successfully created:

```plaintext
$ oc get pipelineversion <pipeline_version_name> -n <namespace>
```

#### 1.2.4. Migrating pipelines from database to Kubernetes API storageCopy link

You can migrate existing pipelines and pipeline versions from the internal database to Kubernetes custom resources. This makes it easier to use OpenShift GitOps (Argo CD) or similar tools to manage pipelines and pipeline versions, while still allowing you to manage them through the OpenShift AI dashboard, API, and the Kubeflow Pipelines (KFP) Software Development Kit (SDK).

This procedure uses a community-supported Kubeflow Pipelines migration script to export pipelines from the Data Science Pipelines API and generate corresponding`Pipeline` and`PipelineVersion` custom resources for import into your cluster.

Important

The migration script in this procedure is maintained by the Kubeflow Pipelines community and is not supported by Red Hat. Before you use the script, review the repository and validate it in a non-production environment.

Warning

The pipeline and pipeline version IDs change during migration, so existing pipeline runs do not map to the migrated pipeline version. The original ID is stored in the`pipelines.kubeflow.org/original-id` label.

Prerequisites

You have installed the OpenShift CLI (`oc`) as described in the appropriate documentation for your cluster:

- [Installing the OpenShift CLI](https://docs.redhat.com/en/documentation/openshift_dedicated/4/html/cli_tools/openshift-cli-oc#installing-openshift-cli) for OpenShift Dedicated
- [Installing the OpenShift CLI](https://docs.redhat.com/en/documentation/red_hat_openshift_service_on_aws_classic_architecture/4/html/cli_tools/openshift-cli-oc#installing-openshift-cli) for Red Hat OpenShift Service on AWS (classic architecture)

Procedure

In a terminal window, log in to your OpenShift cluster by using the OpenShift CLI (`oc`):

```plaintext
$ oc login -u <user_name>
```

When prompted, enter the OpenShift server URL, connection type, and your password.

Set environment variables for your data science project and get the pipeline API route.

In the`export` command, replace with the name of your project:

```plaintext
echo "Setting the prerequisite variables"
export NAMESPACE=<namespace>
export DSPA_NAME=$(oc -n $NAMESPACE get dspa -o jsonpath={.items[0].metadata.name})
export API_URL="https://$(oc -n $NAMESPACE get route "ds-pipeline-$DSPA_NAME" -o jsonpath={.spec.host})"
```

Create a Python virtual environment and install the required dependencies.

```plaintext
echo "Set up the Python prerequisites"
python3.11 -m venv .venv
./.venv/bin/pip install kfp requests PyYAML
```

Download and run the Kubeflow Pipelines community migration script.

The script connects to the Data Science Pipelines API, exports all pipelines and versions from the specified data science project, and generates one YAML file per pipeline in a local`kfp-exported-pipelines/` directory. Each file includes a`Pipeline` resource followed by all associated`PipelineVersion` resources.

Run the following command:

```plaintext
curl -L https://raw.githubusercontent.com/kubeflow/pipelines/refs/heads/master/tools/k8s-native/migration.py -o migration.py
./.venv/bin/python migration.py --skip-tls-verify --kfp-server-host $API_URL --namespace $NAMESPACE --token "$(oc whoami --show-token)"
```

Note

The`--skip-tls-verify` option disables certificate validation and should be used only in development environments or when connecting to a server with a self-signed certificate. In production environments, provide a valid certificate bundle instead.

Additionally, passing the access token directly on the command line might expose it in shell history or process lists. To reduce this risk, store the token in an environment variable and reference it in your command:

```plaintext
export KFP_TOKEN=$(oc whoami --show-token)
./.venv/bin/python migration.py --kfp-server-host $API_URL --namespace $NAMESPACE --token "$KFP_TOKEN"
```

Alternatively, use a prompt with`read -s` to input the token securely at runtime.

Optional: For more information about the script, run the following command:

```plaintext
./.venv/bin/python migration.py --help
```

1. If you plan to create new or updated`PipelineVersion` custom resources after migration, you can compile your pipeline code by using the Kubeflow Pipelines SDK. For more information, see Compiling the pipeline YAML with the Kubeflow Pipelines SDK and Compiling Kubernetes-native manifests with the Kubeflow Pipelines SDK.

Apply the exported Kubernetes custom resources to your cluster.

```plaintext
oc apply -f ./kfp-exported-pipelines
```

Change the pipeline server to use Kubernetes API storage.

```plaintext
oc -n "$NAMESPACE" patch dspa "$DSPA_NAME" --type=merge -p {"spec":{"apiServer":{"pipelineStore":"kubernetes"}}}
```

Note

To view pipelines that were stored in the internal database and not migrated, you can temporarily change the pipeline server back to`database` storage.

```plaintext
oc -n $NAMESPACE patch dspa $DSPA_NAME --type=merge -p {"spec":{"apiServer":{"pipelineStore":"database"}}}
```

Optional: Clean up the local environment.

```plaintext
rm -rf .venv migration.py
```

Verification

Check that the`Pipeline` and`PipelineVersion` custom resources were created in your project:

```plaintext
$ oc -n <namespace> get pipelines.pipelines.kubeflow.org
$ oc -n <namespace> get pipelineversions.pipelines.kubeflow.org
```

Verify that the pipeline server is using Kubernetes API storage:

```plaintext
$ oc -n <namespace> get dspa <dspa_name> -o jsonpath={.spec.apiServer.pipelineStore}{"\n"}
```

The command should return`kubernetes`.

Additional resources

- [Kubeflow Pipelines community migration script source](https://github.com/kubeflow/pipelines/blob/master/tools/k8s-native/migration.py)
- [Script README and usage details](https://raw.githubusercontent.com/kubeflow/pipelines/refs/heads/master/tools/k8s-native/README.md)

### 1.3. Importing a data science pipelineCopy link

To help you begin working with data science pipelines in OpenShift AI, you can import a YAML file containing your pipeline’s code to an active pipeline server, or you can import the YAML file from a URL. This file contains a Kubeflow pipeline compiled by using the Kubeflow compiler. After you have imported the pipeline to a pipeline server, you can execute the pipeline by creating a pipeline run.

Prerequisites

- You have logged in to Red Hat OpenShift AI.
- You have previously created a data science project that is available and contains a configured pipeline server.
- You have compiled your pipeline with the Kubeflow compiler and you have access to the resulting YAML file.
- If you are uploading your pipeline from a URL, the URL is publicly accessible.

Note

If your pipeline is defined in Python code instead of a YAML file, compile it first by using the KFP SDK. For more information, see Compiling the pipeline YAML with the Kubeflow Pipelines SDK.

Procedure

In the Import pipeline dialog, enter the details for the pipeline that you want to import.

Select where you want to import your pipeline from by performing one of the following actions:

- Select Upload a file to upload your pipeline from your local machine’s file system. Import your pipeline by clicking Upload, or by dragging and dropping a file.
- Select Import by url to upload your pipeline from a URL, and then enter the URL into the text box.

Verification

- Click Import pipeline.
- The pipeline that you imported is displayed on the Pipelines page and on the Pipelines tab on the project details page.

### 1.4. Deleting a data science pipelineCopy link

If you no longer require access to your data science pipeline on the dashboard, you can delete it so that it does not appear on the Data science pipelines page.

Prerequisites

- You have logged in to Red Hat OpenShift AI.
- There are active pipelines available on the Pipelines page.
- The pipeline that you want to delete does not contain any pipeline versions.
- The pipeline that you want to delete does not contain any pipeline versions. For more information, see [Deleting a pipeline version](https://docs.redhat.com/en/documentation/red_hat_openshift_ai_cloud_service/1/html/working_with_data_science_pipelines/managing-data-science-pipelines_ds-pipelines#deleting-a-pipeline-version_ds-pipelines).

Procedure

1. From the OpenShift AI dashboard, click Data science pipelines → Pipelines.
2. On the Pipelines page, from the Project drop-down list, select the project that contains the pipeline that you want to delete.
3. Click the action menu (⋮) beside the pipeline that you want to delete, and then click Delete pipeline.
4. In the Delete pipeline dialog, enter the pipeline name in the text field to confirm that you intend to delete it.
5. Click Delete pipeline.

Verification

- The data science pipeline that you deleted is no longer displayed on the Pipelines page.

### 1.5. Deleting a pipeline serverCopy link

After you have finished running your data science pipelines, you can delete the pipeline server. Deleting a pipeline server automatically deletes all of its associated pipelines, pipeline versions, and runs. If your pipeline data is stored in a database, the database is also deleted along with its meta-data. In addition, after deleting a pipeline server, you cannot create new pipelines or pipeline runs until you create another pipeline server.

Prerequisites

- You have logged in to Red Hat OpenShift AI.
- You have previously created a data science project that is available and contains a pipeline server.

Procedure

1. From the OpenShift AI dashboard, click Data science pipelines → Pipelines.
2. On the Pipelines page, from the Project drop-down list, select the project that contains the pipeline server that you want to delete.
3. From the Pipeline server actions list, select Delete pipeline server.
4. In the Delete pipeline server dialog, enter the name of the pipeline server in the text field to confirm that you intend to delete it.
5. Click Delete.

Verification

- Pipelines previously assigned to the deleted pipeline server no longer appear on the Pipelines page for the relevant data science project.
- Pipeline runs previously assigned to the deleted pipeline server no longer appear on the Runs page for the relevant data science project.

### 1.6. Viewing the details of a pipeline serverCopy link

You can view the details of pipeline servers configured in OpenShift AI, such as the pipeline’s connection details and where its data is stored.

Prerequisites

- You have logged in to Red Hat OpenShift AI.
- You have previously created a data science project that contains an active and available pipeline server.

Procedure

1. From the OpenShift AI dashboard, click Data science pipelines → Pipelines.
2. On the Pipelines page, from the Project drop-down list, select the project that contains the pipeline server that you want to view.
3. From the Pipeline server actions list, select Manage pipeline server configuration.

Verification

- You can view the pipeline server details in the Manage pipeline server dialog.

### 1.7. Viewing existing pipelinesCopy link

You can view the details of pipelines that you have imported to Red Hat OpenShift AI, such as the pipeline’s last run, when it was created, the pipeline’s executed runs, and details of any associated pipeline versions.

Prerequisites

- You have logged in to Red Hat OpenShift AI.
- You have previously created a data science project that is available and contains a pipeline server.
- You have imported a pipeline to an active pipeline server.
- Existing pipelines are available.

Procedure

1. From the OpenShift AI dashboard, click Data science pipelines → Pipelines.
2. On the Pipelines page, from the Project drop-down list, select the project that contains the pipelines that you want to view.
3. Optional: Click Expand () on the row of a pipeline to view its pipeline versions.

Verification

- A list of data science pipelines is displayed on the Pipelines page.

### 1.8. Overview of pipeline versionsCopy link

You can manage incremental changes to pipelines in OpenShift AI by using versioning. This allows you to develop and deploy pipelines iteratively, preserving a record of your changes. You can track and manage your changes on the OpenShift AI dashboard, allowing you to schedule and execute runs against all available versions of your pipeline.

### 1.9. Uploading a pipeline versionCopy link

You can upload a YAML file to an active pipeline server that contains the latest version of your pipeline, or you can upload the YAML file from a URL. The YAML file must consist of a Kubeflow pipeline compiled by using the Kubeflow compiler. After you upload a pipeline version to a pipeline server, you can execute it by creating a pipeline run.

Prerequisites

- You have logged in to Red Hat OpenShift AI.
- You have previously created a data science project that is available and contains a configured pipeline server.
- You have a pipeline version available and ready to upload.
- If you are uploading your pipeline version from a URL, the URL is publicly accessible.
- If your pipeline version is based on Python code, compile it to YAML before uploading. For more information, see Compiling the pipeline YAML with the Kubeflow Pipelines SDK.

Procedure

In the Upload new version dialog, enter the details for the pipeline version that you are uploading.

Select where you want to upload your pipeline version from by performing one of the following actions:

- Select Upload a file to upload your pipeline version from your local machine’s file system. Import your pipeline version by clicking Upload, or by dragging and dropping a file.
- Select Import by url to upload your pipeline version from a URL, and then enter the URL into the text box.

Verification

- Click Upload.
- The pipeline version that you uploaded is displayed on the Pipelines page. Click Expand () on the row containing the pipeline to view its versions.
- The Version column on the row containing the pipeline version that you uploaded on the Pipelines page increments by one.

### 1.10. Deleting a pipeline versionCopy link

You can delete specific versions of a pipeline when you no longer require them. Deleting a default pipeline version automatically changes the default pipeline version to the next most recent version. If no pipeline versions exist, the pipeline persists without a default version.

Prerequisites

- You have logged in to Red Hat OpenShift AI.
- You have previously created a data science project that is available and contains a pipeline server.
- You have imported a pipeline to an active pipeline server.

Procedure

From the OpenShift AI dashboard, click Data science pipelines → Pipelines.

The Pipelines page opens.

Delete the pipeline versions that you no longer require:

To delete a single pipeline version:

Click the action menu (⋮) beside the project version that you want to delete, and then click Delete pipeline version.

The Delete pipeline version dialog opens.

1. From the Project list, select the project that contains a version of a pipeline that you want to delete.
2. On the row containing the pipeline, click Expand ().
3. Enter the name of the pipeline version in the text field to confirm that you intend to delete it.
4. Click Delete.

To delete multiple pipeline versions:

1. On the row containing each pipeline version that you want to delete, select the checkbox.
2. Click the action menu (⋮) next to the Import pipeline drop-down list, and then select Delete from the list.

Verification

- The pipeline version that you deleted is no longer displayed on the Pipelines page, or on the Pipelines tab for the data science project.

### 1.11. Viewing the details of a pipeline versionCopy link

You can view the details of a pipeline version that you have uploaded to Red Hat OpenShift AI, such as its graph and YAML code.

Prerequisites

- You have logged in to Red Hat OpenShift AI.
- You have previously created a data science project that is available and contains a pipeline server.
- You have a pipeline available on an active and available pipeline server.

Procedure

From the OpenShift AI dashboard, click Data science pipelines → Pipelines.

The Pipelines page opens.

Click the pipeline name to view further details of its most recent version. The pipeline version details page opens, displaying the Graph, Summary, and Pipeline spec tabs.

Alternatively, click Expand () on the row containing the pipeline that you want to view versions for, and then click the pipeline version that you want to view the details of. The pipeline version details page opens, displaying the Graph, Summary, and Pipeline spec tabs.

1. From the Project drop-down list, select the project that contains the pipeline versions that you want to view details for.

Verification

- On the pipeline version details page, you can view the pipeline graph, summary details, and YAML code.

### 1.12. Downloading a data science pipeline versionCopy link

To make further changes to a data science pipeline version that you previously uploaded to OpenShift AI, you can download pipeline version code from the user interface.

Prerequisites

- You have logged in to Red Hat OpenShift AI.
- You have previously created a data science project that is available and contains a configured pipeline server.
- You have created and imported a pipeline to an active pipeline server that is available to download.

Procedure

Click the pipeline version that you want to download.

The pipeline version details page opens.

1. From the OpenShift AI dashboard, click Data science pipelines → Pipelines.
2. On the Pipelines page, from the Project drop-down list, select the project that contains the version that you want to download.
3. Click Expand () beside the pipeline that contains the version that you want to download.
4. Click the Pipeline spec tab, and then click the Download button () to download the YAML file that contains the pipeline version code to your local machine.

Verification

- The pipeline version code downloads to your browser’s default directory for downloaded files.

### 1.13. Overview of data science pipelines cachingCopy link

You can use caching within data science pipelines to optimize execution times and improve resource efficiency. Caching reduces redundant task execution by reusing results from previous runs with identical inputs.

Caching is particularly beneficial for iterative tasks, where intermediate steps might not need to be repeated. Understanding caching can help you design more efficient pipelines and save time in model development.

Caching operates by storing the outputs of successfully completed tasks and comparing the inputs of new tasks against previously cached ones. If a match is found, OpenShift AI reuses the cached results instead of re-executing the task, reducing computation time and resource usage.

#### 1.13.1. Caching criteriaCopy link

For caching to be effective, the following criteria determine if a task can use previously cached results:

- Input data and parameters: If the input data and parameters for a task are unchanged from a previous run, cached results are eligible for reuse.
- Task code and configuration: Changes to the task code or configurations invalidate the cache to ensure that modifications are always reflected.
- Pipeline environment: Changes to the pipeline environment, such as dependency versions, also affect caching eligibility to maintain consistency.

#### 1.13.2. Viewing cached steps in the OpenShift AI user interfaceCopy link

Cached steps in pipelines are visually indicated in the user interface (UI):

- Tasks that use cached results display a green icon, helping you quickly identify which steps were cached. The Status field in the side panel displays`Cached` for cached tasks.
- The UI also includes information about when the task was previously executed, allowing for easy verification of cache usage.

To check the caching status of specific tasks, navigate to the pipeline details view in the UI. Cached and non-cached tasks are clearly indicated. Cached tasks do not display execution logs because they reuse previously generated outputs and are not re-executed.

#### 1.13.3. Controlling caching in data science pipelinesCopy link

Caching is enabled by default in OpenShift AI to improve performance. However, there are instances when disabling caching might be necessary for specific tasks, an entire pipeline, or all pipelines. For example, caching might not be beneficial for tasks that rely on frequently updated data or unique computational needs. In other cases, such as debugging, development, or when deterministic re-execution is required, you might want to disable caching for all pipelines.

Important

Disabling caching at the pipeline or pipeline server level causes all tasks to re-run, potentially increasing compute time and resource usage.

You can control caching for data science pipelines in the following ways:

- Individual task: Data scientists can disable caching for specific steps in a pipeline.
- Pipeline (submit time): Data scientists can disable caching when submitting a pipeline run.
- Pipeline (compile time): Data scientists can disable caching when compiling a pipeline.
- All pipelines (pipeline server): You can disable caching for all pipelines in the pipeline server, which overrides all pipeline and task-level caching settings.

##### 1.13.3.1. Disabling caching for individual tasksCopy link

To disable caching for a particular task, apply the`set_caching_options` method directly to the task in your pipeline code:

```plaintext
task_name.set_caching_options(False)
```

After applying this setting, OpenShift AI runs the task in future pipeline runs, ignoring any cached results.

You can re-enable caching for individual tasks by setting the`set_caching_options` parameter to`True` or by omitting`set_caching_options`.

This setting is ignored if caching is disabled in the pipeline server.

##### 1.13.3.2. Disabling caching for a pipeline at submit timeCopy link

To disable caching for the entire pipeline during pipeline submission, set the`enable_caching` parameter to`False` in your pipeline code. This setting ensures that no steps are cached during pipeline execution. The`enable_caching` parameter is available only when using the`kfp.client` to submit pipelines or start pipeline runs, such as the`run_pipeline` method.

Example:

```plaintext
import kfp
client = kfp.Client()
client.run_pipeline(
    experiment_id=experiment.id,
    pipeline_id=pipeline.id,
    job_name="no-cache-run",
    params={},                # optional
    enable_caching=False,
)
```

This setting is ignored if caching is disabled during pipeline compilation or in the pipeline server.

##### 1.13.3.3. Disabling caching for a pipeline at compile timeCopy link

To disable caching for the entire pipeline during compilation, set one of the following options in your local environment or workbench:

Environment variable:

```bash
export KFP_DISABLE_EXECUTION_CACHING_BY_DEFAULT=true
```

CLI flag (when using`kfp dsl compile`):

```bash
kfp dsl compile --disable-execution-caching-by-default
```

These settings are ignored if caching is disabled in the pipeline server.

##### 1.13.3.4. Disabling caching for all pipelines (pipeline server)Copy link

To disable caching for all pipelines in the pipeline server and override all pipeline and task-level caching settings, use either of the following methods:

Pipeline server configuration

1. From the OpenShift AI dashboard, click Data science pipelines → Pipelines.
2. On the Pipelines page, from the Project drop-down list, select the project that contains the pipeline server that you want to configure.
3. From the Pipeline server actions list, select Manage pipeline server configuration.
4. In the Pipeline caching section, clear the Allow caching to be configured per pipeline and task checkbox.
5. Click Save.

Data Science Pipelines Application (cluster administrator)

In the OpenShift console or CLI, set the`cacheEnabled` field to`false` in the`DataSciencePipelinesApplication`(DSPA) custom resource for the project.

Example:

```yaml
apiVersion: datasciencepipelinesapplications.opendatahub.io/v1
kind: DataSciencePipelinesApplication
metadata:
  name: my-dspa
  namespace: my-namespace
spec:
  apiServer:
    cacheEnabled: false
```

To allow caching to be configured at the pipeline and task level, set the`cacheEnabled` field to`true` in the DSPA custom resource.

After applying this setting, all pipeline and task-level caching settings are ignored.

Note

Changing this setting updates the`CACHEENABLED` environment variable in the pipeline server deployment.

Verification

After configuring caching settings, you can verify its behavior by using one of the following methods:

- Check the UI: Locate the green icons in the task list to identify cached steps.
- Test task re-runs: Disable caching on specific tasks or the pipeline to confirm that steps re-execute as expected.
- Validate inputs: Ensure the task inputs, parameters, and runtime settings are unchanged when caching is applied.

Note

You can also disable caching for a single node or for your entire pipeline in JupyterLab using Elyra. For more information, see [Disabling node caching in Elyra](https://docs.redhat.com/en/documentation/red_hat_openshift_ai_cloud_service/1/html/working_with_data_science_pipelines/working-with-pipelines-in-jupyterlab_ds-pipelines#disabling-node-caching-in-elyra_ds-pipelines).

## Chapter 2. Managing pipeline experimentsCopy link

### 2.1. Overview of pipeline experimentsCopy link

A pipeline experiment is a workspace where you can try different configurations of your pipelines. You can use experiments to organize your runs into logical groups. As a data scientist, you can use OpenShift AI to define, manage, and track pipeline experiments. You can view a record of previously created and archived experiments from the Experiments page in the OpenShift AI user interface. Pipeline experiments contain pipeline runs, including recurring runs. This allows you to try different configurations of your pipelines.

When you work with data science pipelines, it is important to monitor and record your pipeline experiments to track the performance of your data science pipelines. You can compare the results of up to 10 pipeline runs at one time, and view available parameter, scalar metric, confusion matrix, and receiver operating characteristic (ROC) curve data for all selected runs.

You can view artifacts for an executed pipeline run from the OpenShift AI dashboard. Pipeline artifacts can help you to evaluate the performance of your pipeline runs and make it easier to understand your pipeline components. Pipeline artifacts can range from plain text data to detailed, interactive data visualizations.

### 2.2. Creating a pipeline experimentCopy link

Pipeline experiments are workspaces where you can try different configurations of your pipelines. You can also use experiments to organize your pipeline runs into logical groups. Pipeline experiments contain pipeline runs, including recurring runs.

Prerequisites

- You have logged in to Red Hat OpenShift AI.
- You have previously created a data science project that is available and contains a configured pipeline server.
- You have imported a pipeline to an active pipeline server.

Procedure

In the Create experiment dialog, configure the pipeline experiment:

1. In the Experiment name field, enter a name for the pipeline experiment.
2. In the Description field, enter a description for the pipeline experiment.
3. Click Create experiment.

Verification

- The pipeline experiment that you created is displayed on the Experiments tab.

### 2.3. Archiving a pipeline experimentCopy link

You can archive your pipeline experiments to store and retain records for future reference. If you need to reuse an archived experiment, you can restore it at any time. Deleting pipeline experiments is a separate action and happens only when you explicitly choose to delete them. Unarchived experiments remain stored unless you manually delete them.

Prerequisites

- You have logged in to Red Hat OpenShift AI.
- You have previously created a data science project that is available and has a pipeline server.
- You have imported a pipeline to an active pipeline server.
- A pipeline experiment is available to archive.

Procedure

1. From the OpenShift AI dashboard, click Experiments → Experiments and runs.
2. On the Experiments page, from the Project drop-down list, select the project that contains the pipeline experiment that you want to archive.
3. Click the action menu (⋮) beside the pipeline experiment that you want to archive, and then click Archive.
4. In the Archiving experiment dialog, enter the pipeline experiment name in the text field to confirm that you intend to archive it.
5. Click Archive.

Verification

- The archived pipeline experiment does not appear on the Experiments tab, and instead is displayed on the Archive tab on the Experiments page for the pipeline experiment.

### 2.4. Deleting an archived pipeline experimentCopy link

You can delete pipeline experiments from the OpenShift AI experiment archive.

Prerequisites

- You have logged in to Red Hat OpenShift AI.
- You have previously created a data science project that is available and contains a configured pipeline server.
- You have imported a pipeline to an active pipeline server.
- A pipeline experiment is available in the pipeline archive.

Procedure

1. From the OpenShift AI dashboard, click Experiments → Experiments and runs.
2. On the Experiments page, from the Project drop-down list, select the project that contains the archived pipeline experiment that you want to delete.
3. Click the Archive tab.
4. Click the action menu (⋮) beside the pipeline experiment that you want to delete, and then click Delete.
5. In the Delete experiment? dialog, enter the pipeline experiment name in the text field to confirm that you intend to delete it.
6. Click Delete.

Verification

- The pipeline experiment that you deleted is no longer displayed on the Archive tab on the Experiments page.

### 2.5. Restoring an archived pipeline experimentCopy link

You can restore an archived pipeline experiment to the active state.

Prerequisites

- You have logged in to Red Hat OpenShift AI.
- You have previously created a data science project that is available and has a pipeline server.
- An archived pipeline experiment exists in your project.

Procedure

1. From the OpenShift AI dashboard, click Experiments → Experiments and runs.
2. On the Experiments page, from the Project drop-down list, select the project that contains the archived pipeline experiment that you want to restore.
3. Click the Archive tab.
4. Click the action menu (⋮) beside the pipeline experiment that you want to restore, and then click Restore.
5. In the Restore experiment dialog, click Restore.

Verification

- The restored pipeline experiment is displayed on the Experiments tab on the Experiments page.

### 2.6. Viewing pipeline task executionsCopy link

When a pipeline run executes, you can view details of executed tasks in each step in a pipeline run from the OpenShift AI dashboard. A step forms part of a task in a pipeline.

Prerequisites

- You have logged in to Red Hat OpenShift AI.
- You have previously created a data science project that is available and contains a pipeline server.
- You have imported a pipeline to an active pipeline server.
- You have previously triggered a pipeline run.

Procedure

1. From the OpenShift AI dashboard, click Experiments → Executions.
2. On the Executions page, from the Project drop-down list, select the project that contains the experiment for the pipeline task executions that you want to view.

Verification

- On the Executions page, you can view the execution details of each pipeline task execution, such as its name, status, unique ID, and execution type. The execution status indicates whether the pipeline task has successfully executed. For further information about the details of the task execution, click the execution name.

### 2.7. Viewing pipeline artifactsCopy link

After a pipeline run executes, you can view its pipeline artifacts from the OpenShift AI dashboard. Pipeline artifacts can help you to evaluate the performance of your pipeline runs and make it easier to understand your pipeline components. Pipeline artifacts can range from plain text data to detailed, interactive data visualizations.

Prerequisites

- You have logged in to Red Hat OpenShift AI.
- You have previously created a data science project that is available and contains a pipeline server.
- You have imported a pipeline to an active pipeline server.
- You have previously triggered a pipeline run.

Procedure

To view or download the content of an artifact stored in S3-compatible object storage, click the preview icon, the download icon, or the active artifact URI link.

Clicking the preview icon or the URI link for content that your browser can display (such as plain text, HTML, or markdown) opens the artifact in a new browser tab. Clicking the download icon or the URI link for content that your browser cannot display (such as a model file) downloads the artifact. To download an artifact that is displayed in a browser tab, right-click the content and then click Save as.

1. From the OpenShift AI dashboard, click Experiments → Artifacts.
2. On the Artifacts page, from the Project drop-down list, select the project that contains the pipeline experiment for the pipeline artifacts that you want to view.
3. Click an artifact name in the list to view additional information about the artifact, including its original run, original execution, and properties.

Verification

- On the Artifacts page, you can view the details of each pipeline artifact, such as its name, unique ID, type, and URI.

### 2.8. Comparing runs in an experimentCopy link

You can compare up to 10 pipeline runs in the same experiment at one time, and view available parameter, scalar metric, confusion matrix, and receiver operating characteristic (ROC) curve data for all selected runs.

To compare runs from different experiments or pipelines, or to view every pipeline run in a project, see [Comparing runs in different experiments](https://docs.redhat.com/en/documentation/red_hat_openshift_ai_cloud_service/1/html/working_with_data_science_pipelines/managing-pipeline-experiments_ds-pipelines#comparing-runs-in-different-experiments_ds-pipelines).

Prerequisites

- You have logged in to Red Hat OpenShift AI.
- You have previously created a data science project that is available and has a pipeline server.
- You have imported a pipeline to an active pipeline server.
- You have created at least two pipeline runs.

Procedure

In the OpenShift AI dashboard, select Experiments → Experiments and runs.

The Experiments page opens.

On the Experiments tab, in the Experiment column, click the experiment that you want to compare runs for. To select runs that are not in an experiment, click Default. All runs that are created without specifying an experiment will appear in the Default group.

The Runs page opens.

Select the checkbox next to each run that you want to compare, and then click Compare runs. You can compare a maximum of 10 runs at one time.

The Compare runs page opens and displays data for the runs that you selected.

The Metrics section displays scalar metric, confusion matrix, and ROC curve data for all selected runs.

1. On the Scalar metrics tab, set the Hide parameters with no differences switch to On to hide parameters that have the same values.
2. On the ROC curve tab, in the artifacts list, adjust the ROC curve chart by clearing the checkbox next to artifacts that you want to remove from the chart.

To select different runs for comparison, click Manage runs.

The Manage runs dialog opens.

1. From the filter drop-down list, select Run, Pipeline version, Created after, or Status to filter the run list by each value.
2. Clear the checkbox next to each run that you want to remove from your comparison.
3. Select the checkbox next to each run that you want to add to your comparison.

Verification

- Click Update.
- The Compare runs page opens and displays data for the runs that you selected.

### 2.9. Comparing runs in different experimentsCopy link

You can compare up to 10 pipeline runs from any experiment or pipeline in a project, including runs that do not have a corresponding pipeline, and view available parameter, scalar metric, confusion matrix, and receiver operating characteristic (ROC) curve data for all selected runs.

Prerequisites

- You have logged in to Red Hat OpenShift AI.
- You have previously created a data science project that is available and has a pipeline server.
- You have imported a pipeline to an active pipeline server.
- You have created at least two pipeline runs.

Procedure

In the OpenShift AI dashboard, select Data science pipelines → Runs.

The Runs page opens.

On the Runs tab, in the Run column, select the checkbox next to each run that you want to compare, and then click Compare runs. You can compare a maximum of 10 runs at one time.

The Compare runs page opens and displays data for the runs that you selected.

The Metrics section displays scalar metric, confusion matrix, and ROC curve data for all selected runs.

1. On the Scalar metrics tab, set the Hide parameters with no differences switch to On to hide parameters that have the same values.
2. On the ROC curve tab, in the artifacts list, adjust the ROC curve chart by clearing the checkbox next to artifacts that you want to remove from the chart.

To select different runs for comparison, click Manage runs.

The Manage runs page opens.

1. From the filter drop-down list, select Run, Experiment, Pipeline version, Created after, or Status to filter the run list by each value.
2. Clear the checkbox next to each run that you want to remove from your comparison.
3. Select the checkbox next to each run that you want to add to your comparison.

Verification

- Click Update.
- The Compare runs page opens and displays data for the runs that you selected.

## Chapter 3. Managing pipeline runsCopy link

### 3.1. Overview of pipeline runsCopy link

A pipeline run is a single execution of a data science pipeline. As data scientist, you can use OpenShift AI to define, manage, and track executions of a data science pipeline. To view a record of previously executed, scheduled, and archived runs, you must first select the experiment from the Experiments → Experiments and runs page in the OpenShift AI interface. After selecting the experiment, you can access all of its pipeline runs from the Runs page.

You can optimize your use of pipeline runs for portability and repeatability by using pipeline experiments. With experiments, you can logically group pipeline runs and try different configurations of your pipelines. You can also clone your pipeline runs to reproduce and scale them, or archive them when you want to retain a record of their execution, but no longer require them. You can delete archived runs that you no longer want to retain, or you can restore them to their former state.

You can execute a run once, that is, immediately after its creation, or on a recurring basis. Recurring runs consist of a copy of a pipeline with all of its parameter values and a run trigger. A run trigger indicates when a recurring run executes. You can define the following run triggers:

- Periodic: Used for scheduling runs to execute in intervals
- Cron: Used for scheduling runs as a cron job

You can also configure up to 10 instances of the same run to execute concurrently. You can track the progress of a run from the run details page on the OpenShift AI user interface. From here, you can view the graph and output artifacts for the run.

A pipeline run can be in one of the following states:

- Scheduled: A pipeline run that is scheduled to execute at least once
- Active: A pipeline run that is executing, or stopped
- Archived: An archived pipeline run

You can use catch up runs to ensure your pipeline runs do not permanently fall behind schedule when paused. For example, if you re-enable a paused recurring run, the run scheduler backfills each missed run interval. If you disable catch up runs, and you have a scheduled run interval ready to execute, the run scheduler only schedules the run execution for the latest run interval. Catch up runs are enabled by default. However, if your pipeline handles backfill internally, Red Hat recommends that you disable catch up runs to avoid duplicate backfill.

After a pipeline run executes, you can view details of its executed tasks on the Executions page, along with its artifacts, on the Artifacts page. From the Executions page, you can view the execution status of each task, which indicates whether it completed successfully. You can also view further information about each executed task by clicking the execution name in the list. From the Artifacts page, you can view the details of each pipeline artifact, such as its name, unique ID, type, and URI. Pipeline artifacts can help you to evaluate the performance of your pipeline runs and make it easier to understand your pipeline components. Pipeline artifacts can range from plain text data to detailed, interactive data visualizations.

You can view further information about each artifact, including its original run and original execution, by clicking the artifact name in the list. You can also view or download the content of artifacts stored in S3-compatible object storage by clicking the preview icon, the download icon, or the active artifact URI link.

Clicking the preview icon or the URI link for content that your browser can display (such as plain text, HTML, or markdown) opens the artifact in a new browser tab. Clicking the download icon or the URI link for content that your browser cannot display (such as a model file) downloads the artifact. To download an artifact that is displayed in a browser tab, right-click the content and then click Save as.

Note

Artifacts that are not stored in S3-compatible object storage are not available to download and do not display an active URI link.

You can review and analyze logs for each step in an active pipeline run. With the log viewer, you can search for specific log messages, view the log for each step, and download the step logs to your local machine.

### 3.2. Storing data with data science pipelinesCopy link

When you run a data science pipeline, OpenShift AI stores the pipeline YAML configuration file and resulting pipeline run artifacts in the`root` directory of your storage bucket. The directories that contain pipeline run artifacts can differ depending on where you executed the pipeline run from. See the following table for further information:

Expand

OpenShift AI dashboard

`/pipelines/ `

Example:`/pipelines/1d01c4eb-d2ab-4916-9935-a73a5580f1fb`

`/ / `

Example:`iris-training-pipeline/2g48k8pw-a8ib-4884-9145-h41j7599h3ds`

JupyterLab Elyra extension

`/pipelines/ `

`/ `

Example:`/hello-generic-world-0523161704`

With the JupyterLab Elyra extension, you can also set an [object storage path prefix](https://elyra.readthedocs.io/en/latest/user_guide/pipelines.html#generic-node-properties).

Example:`/iris-project/hello-generic-world-0523161704`

| Table 3.1. Pipeline configuration file and artifacts storage locations | Pipeline run source | Pipeline storage directory | Run artifacts storage directory |
| --- | --- | --- | --- |

Show more

If you want to use an existing artifact that was not generated by a task in a pipeline, you can use the [kfp.dsl.importer component](https://kubeflow-pipelines.readthedocs.io/en/latest/source/dsl.html#kfp.dsl.importer) to import the artifact from its URI. You can only import these artifacts to the S3-compatible object storage bucket that you define in the Bucket field in your pipeline server configuration. For more information about the`kfp.dsl.importer` component, see [Special Case: Importer Components](https://www.kubeflow.org/docs/components/pipelines/user-guides/components/importer-component/).

### 3.3. Viewing active pipeline runsCopy link

You can view a list of pipeline runs that were previously executed in a pipeline experiment. From this list, you can view details relating to your pipeline runs, such as the pipeline version that the run belongs to, along with the run status, duration, and execution start time.

Prerequisites

- You have logged in to Red Hat OpenShift AI.
- You have previously created a data science project that is available and has a pipeline server.
- You have imported a pipeline to an active pipeline server.
- You have previously executed a pipeline run that is available.

Procedure

From the list of experiments, click the experiment that contains the active pipeline runs that you want to view.

The Runs page opens.

After a run has completed its execution, the run status is displayed in the Status column of the Runs tab, indicating whether the run succeeded or failed.

1. From the OpenShift AI dashboard, click Experiments → Experiments and runs.
2. On the Experiments page, from the Project drop-down list, select the project that contains the pipeline experiment for the active pipeline runs that you want to view.

Verification

- A list of active runs is displayed on the Runs tab on the Runs page for the pipeline experiment.

### 3.4. Executing a pipeline runCopy link

By default, a pipeline run executes once immediately after it is created.

Prerequisites

- You have logged in to Red Hat OpenShift AI.
- You have previously created a data science project that is available and contains a configured pipeline server.
- You have imported a pipeline to an active pipeline server.

Procedure

On the Create run page, configure the run:

Click Create run.

The details page for the run opens.

1. From the Experiment list, select the pipeline experiment that you want to create a run for. Alternatively, to create a new pipeline experiment, click Create new experiment, and then complete the relevant fields in the Create experiment dialog.
2. In the Name field, enter a name for the run, up to 255 characters.
3. In the Description field, enter a description for the run, up to 255 characters.
4. From the Pipeline list, select the pipeline that you want to create a run for. Alternatively, to create a new pipeline, click Create new pipeline, and then complete the relevant fields in the Import pipeline dialog.
5. From the Pipeline version list, select the pipeline version to create a run for. Alternatively, to upload a new version, click Upload new version, and then complete the relevant fields in the Upload new version dialog.
6. Configure the input parameters for the run by selecting the parameters from the list.

Verification

- The pipeline run that you created is displayed on the Runs tab on the Runs page for the pipeline experiment.

### 3.5. Stopping an active pipeline runCopy link

If you no longer require an active pipeline run to continue executing in a pipeline experiment, you can stop the run before its defined end date.

Prerequisites

- You have logged in to Red Hat OpenShift AI.
- There is a previously created data science project available that contains a pipeline server.
- You have imported a pipeline to an active pipeline server.
- An active pipeline run is currently executing.

Procedure

On the Runs tab, click the action menu (⋮) beside the active run that you want to stop, and then click Stop.

There might be a short delay while the run stops.

1. From the OpenShift AI dashboard, click Experiments → Experiments and runs.
2. On the Experiments page, from the Project drop-down list, select the project that contains the pipeline experiment for the active run that you want to stop.
3. From the list of pipeline experiments, click the pipeline experiment that contains the run that you want to stop.

Verification

- The`Failed` status icon () is displayed in the Status column of the stopped run.

### 3.6. Duplicating an active pipeline runCopy link

To make it easier to quickly execute pipeline runs with the same configuration in a pipeline experiment, you can duplicate them.

Prerequisites

- You have logged in to Red Hat OpenShift AI.
- You have previously created a data science project that is available and contains a configured pipeline server.
- You have imported a pipeline to an active pipeline server.
- An active run is available to duplicate on the Active tab on the Runs page.

Procedure

On the Duplicate run page, configure the duplicate run:

Click Create run.

The details page for the run opens.

1. From the Experiment list, select a pipeline experiment to contain the duplicate run. Alternatively, to create a new pipeline experiment, click Create new experiment, and then complete the relevant fields in the Create experiment dialog.
2. In the Name field, enter a name for the duplicate run.
3. In the Description field, enter a description for the duplicate run.
4. From the Pipeline list, select a pipeline to contain the duplicate run. Alternatively, to create a new pipeline, click Create new pipeline, and then complete the relevant fields in the Import pipeline dialog.
5. From the Pipeline version list, select a pipeline version to contain the duplicate run. Alternatively, to upload a new version, click Upload new version, and then complete the relevant fields in the Upload new version dialog.
6. In the Parameters section, configure input parameters for the duplicate run by selecting parameters from the list.

Verification

- The duplicate pipeline run is displayed on the Runs tab on the Runs page for the pipeline experiment.

### 3.7. Viewing scheduled pipeline runsCopy link

You can view a list of pipeline runs that are scheduled for execution in a pipeline experiment. From this list, you can view details relating to your pipeline runs, such as the pipeline version that the run belongs to. You can also view the run status, execution frequency, and schedule.

Prerequisites

- You have logged in to Red Hat OpenShift AI.
- You have previously created a data science project that is available and contains a pipeline server.
- You have imported a pipeline to an active pipeline server.
- You have scheduled a pipeline run that is available to view.

Procedure

On the Runs page, click the Schedules tab.

After a run is scheduled, the Status column indicates whether the run is ready or unavailable for execution. To change its execution availability, set the Status switch to On or Off. Alternatively, you can change its execution availability from the details page for the scheduled run by clicking the Actions drop-down menu, and then selecting Enable or Disable.

1. From the OpenShift AI dashboard, click Experiments → Experiments and runs.
2. On the Experiments page, from the Project drop-down list, select the project that contains the pipeline experiment for the scheduled pipeline runs that you want to view.
3. From the list of pipeline experiments, click the experiment that contains the pipeline runs that you want to view.

Verification

- A list of scheduled runs is displayed on the Schedules tab on the Runs page for the pipeline experiment.

### 3.8. Scheduling a pipeline run using a cron jobCopy link

You can use a cron job to schedule a pipeline run to execute at a specific time. Cron jobs are useful for creating periodic and recurring tasks, and can also schedule individual tasks for a specific time, such as if you want to schedule a run for a low activity period. To successfully execute runs in OpenShift AI, you must use the supported format. See [Cron Expression Format](https://pkg.go.dev/github.com/robfig/cron#hdr-CRON_Expression_Format) for more information.

The following examples show the correct format:

Expand

Every five minutes

@every 5m

Every 10 minutes

0 */10 * * * *

Daily at 16:16 UTC

0 16 16 * * *

Daily every quarter of the hour

0 0,15,30,45 * * * *

On Monday and Tuesday at 15:40 UTC

0 40 15 * * MON,TUE

| | Run occurrence | Cron format |
| --- | --- | --- |

Show more

### 3.9. Scheduling a pipeline runCopy link

To repeatedly run a pipeline, you can create a scheduled pipeline run.

Prerequisites

- You have logged in to Red Hat OpenShift AI.
- You have previously created a data science project that is available and contains a configured pipeline server.
- You have imported a pipeline to an active pipeline server.

Procedure

To go to the Schedules tab for a run, perform one of the following sets of actions:

To select a run from an experiment

1. From the OpenShift AI dashboard, click Experiments → Experiments and runs.
2. On the Experiments page, from the Project drop-down list, select the project that contains the pipeline experiment for the run that you want to schedule.
3. From the list of pipeline experiments, click the experiment that contains the run that you want to schedule.
4. Click the Schedules tab.

To select any run

1. From the OpenShift AI dashboard, click Data science pipelines → Runs.
2. On the Runs page, from the Project drop-down list, select the project that contains the run that you want to schedule.
3. Click the Schedules tab.

On the Create schedule page, configure the run that you are scheduling:

From the Trigger type list, select one of the following options:

- Select Periodic to specify an execution frequency. In the Run every field, enter a number and select an execution frequency from the list.
- Select Cron to specify the execution schedule in`cron` format in the Cron string field. This creates a cron job to execute the run. Click the Copy button () to copy the cron job schedule to the clipboard. The field furthest to the left represents seconds. For more information about scheduling tasks using the supported`cron` format, see [Cron Expression Format](https://pkg.go.dev/github.com/robfig/cron#hdr-CRON_Expression_Format).

For Pipeline version, select one of the following options:

- Select Always use the latest pipeline version so that each recurring run automatically uses the most recent pipeline version.
- Select Use fixed version, then select a specific pipeline version for all recurring runs. Alternatively, to upload a new version, click Upload new version, and then complete the relevant fields in the Upload new version dialog.

Verification

- Click Create schedule.
- The pipeline run that you scheduled is displayed on the Schedules tab on the Runs page for the pipeline experiment.

### 3.10. Duplicating a scheduled pipeline runCopy link

To make it easier to schedule runs to execute as part of your pipeline experiment, you can duplicate existing scheduled runs.

Prerequisites

- You have logged in to Red Hat OpenShift AI.
- You have previously created a data science project that is available and contains a configured pipeline server.
- You have imported a pipeline to an active pipeline server.
- A scheduled run is available to duplicate on the Schedules tab on the Runs page.

Procedure

To go to the Schedules tab for a run, perform one of the following sets of actions:

To select a run from an experiment

1. From the OpenShift AI dashboard, click Experiments → Experiments and runs.
2. On the Experiments page, from the Project drop-down list, select the project that contains the pipeline experiment for the run that you want to duplicate.
3. From the list of pipeline experiments, click the experiment that contains the run that you want to duplicate.
4. Click the Schedules tab.

To select any run

1. From the OpenShift AI dashboard, click Data science pipelines → Runs.
2. On the Runs page, from the Project drop-down list, select the project that contains the run that you want to schedule.
3. Click the Schedules tab.

On the Duplicate schedule page, configure the duplicate run:

From the Trigger type list, select one of the following options:

- Select Periodic to specify an execution frequency. In the Run every field, enter a number, and select an execution frequency from the list.
- Select Cron to specify the execution schedule in`cron` format in the Cron string field. This creates a cron job to execute the run. Click the Copy button () to copy the cron job schedule to the clipboard. The field furthest to the left represents seconds. For more information about scheduling tasks using the supported`cron` format, see [Cron Expression Format](https://pkg.go.dev/github.com/robfig/cron#hdr-CRON_Expression_Format).

For Pipeline version, select one of the following options:

- Select Always use the latest pipeline version so that each recurring run automatically uses the most recent pipeline version.
- Select Use fixed version, then select a specific pipeline version for all recurring runs. Alternatively, to upload a new version, click Upload new version, and then complete the relevant fields in the Upload new version dialog.

Verification

- Click Schedule run.
- The pipeline run that you duplicated is displayed on the Schedules tab on the Runs page for the pipeline experiment.

### 3.11. Deleting a scheduled pipeline runCopy link

To discard pipeline runs that you previously scheduled, but no longer require, you can delete them so that they do not appear on the Schedules page.

Prerequisites

- You have logged in to Red Hat OpenShift AI.
- You have previously created a data science project that is available and contains a configured pipeline server.
- You have imported a pipeline to an active pipeline server.
- You have previously scheduled a run that is available to delete.

Procedure

1. From the OpenShift AI dashboard, click Experiments → Experiments and runs.
2. On the Experiments page, from the Project drop-down list, select the project that contains the pipeline experiment for the scheduled pipeline run that you want to delete.
3. From the list of pipeline experiments, click the experiment that contains the scheduled pipeline run that you want to delete.
4. On the Runs page, click the Schedules tab.
5. Click the action menu (⋮) beside the scheduled pipeline run that you want to delete, and then click Delete.
6. In the Delete schedule dialog, enter the run name in the text field to confirm that you intend to delete it.
7. Click Delete.

Verification

- The run that you deleted is no longer displayed on the Schedules tab for the pipeline experiment.

### 3.12. Viewing the details of a pipeline runCopy link

To gain a clearer understanding of your pipeline runs, you can view the details of a previously triggered pipeline run, such as its graph, execution details, and run output.

Prerequisites

- You have logged in to Red Hat OpenShift AI.
- You have previously created a data science project that is available and contains a pipeline server.
- You have imported a pipeline to an active pipeline server.
- You have previously triggered a pipeline run.

Procedure

On the Runs page, click the name of the run that you want to view the details of.

The details page for the run opens.

1. From the OpenShift AI dashboard, click Data science pipelines → Runs.
2. On the Runs page, from the Project drop-down list, select the project that you want to view the details of a pipeline run for.

Verification

- On the run details page, you can view the run graph, execution details, input parameters, step logs, and run output.

### 3.13. Viewing archived pipeline runsCopy link

You can view a list of pipeline runs that you have archived. You can view details for your archived pipeline runs, such as the pipeline version, run status, duration, and execution start date.

Prerequisites

- You have logged in to Red Hat OpenShift AI.
- You have previously created a data science project that is available and has a pipeline server.
- You have imported a pipeline to an active pipeline server.
- An archived pipeline run exists.

Procedure

1. From the OpenShift AI dashboard, click Experiments → Experiments and runs.
2. On the Experiments page, from the Project drop-down list, select the project that contains the pipeline experiment for the archived pipeline runs that you want to view.
3. From the list of pipeline experiments, click the experiment that contains the archived pipeline runs that you want to view.
4. On the Runs page, click the Archive tab.

Verification

- A list of archived runs is displayed on the Archive tab on the Runs page for the pipeline experiment.

### 3.14. Archiving a pipeline runCopy link

You can retain records of your pipeline runs by archiving them. If required, you can restore runs from your archive to reuse, or delete runs that are no longer required.

Prerequisites

- You have logged in to Red Hat OpenShift AI.
- You have previously created a data science project that is available and has a pipeline server.
- You have imported a pipeline to an active pipeline server.
- You have previously executed a pipeline run that is available.

Procedure

From the list of pipeline experiments, click the experiment that contains the pipeline run that you want to archive.

The Runs page opens.

1. From the OpenShift AI dashboard, click Experiments → Experiments and runs.
2. On the Experiments page, from the Project drop-down list, select the project that contains the pipeline experiment for the run that you want to archive.
3. On the Runs tab, click the action menu (⋮) beside the pipeline run that you want to archive, and then click Archive.
4. In the Archiving run dialog, enter the run name in the text field to confirm that you intend to archive it.
5. Click Archive.

Verification

- The archived run does not appear on the Runs tab, and instead is displayed on the Archive tab on the Runs page for the pipeline experiment.

### 3.15. Restoring an archived pipeline runCopy link

You can restore an archived run to the active state.

Prerequisites

- You have logged in to Red Hat OpenShift AI.
- You have previously created a data science project that is available and has a pipeline server.
- You have imported a pipeline to an active pipeline server.
- An archived run exists in your project.

Procedure

1. From the OpenShift AI dashboard, click Experiments → Experiments and runs.
2. On the Experiments page, from the Project drop-down list, select the project that contains the pipeline experiment that you want to restore.
3. From the list of pipeline experiments, click the experiment that contains the archived pipeline run that you want to restore.
4. On the Runs page, click the Archive tab.
5. Click the action menu (⋮) beside the pipeline run that you want to restore, and then click Restore.
6. In the Restore run? dialog, click Restore.

Verification

- The restored run is displayed on the Runs tab on the Runs page for the pipeline experiment.

### 3.16. Deleting an archived pipeline runCopy link

You can delete pipeline runs from the OpenShift AI run archive.

Prerequisites

- You have logged in to Red Hat OpenShift AI.
- You have previously created a data science project that is available and has a pipeline server.
- You have imported a pipeline to an active pipeline server.
- You have previously archived a pipeline run.

Procedure

1. From the OpenShift AI dashboard, click Data science pipelines → Runs.
2. On the Runs page, from the Project drop-down list, select the project that you want to delete an archived pipeline run from.
3. Click the Archive tab.
4. Click the action menu (⋮) beside the pipeline run that you want to delete, and then click Delete.
5. In the Delete run? dialog, enter the run name in the text field to confirm that you intend to delete it.
6. Click Delete.

Verification

- The archived run that you deleted is no longer displayed on the Archive tab on the Runs page.

### 3.17. Duplicating an archived pipeline runCopy link

To make it easier to reproduce runs with the same configuration as runs in your archive, you can duplicate them.

Prerequisites

- You have logged in to Red Hat OpenShift AI.
- You have previously created a data science project that is available and contains a configured pipeline server.
- You have imported a pipeline to an active pipeline server.
- An archived run is available to duplicate on the Archived tab on the Runs page.

Procedure

On the Duplicate run page, configure the duplicate run:

Click Create run.

The details page for the run opens.

1. From the Experiment list, select a pipeline experiment to contain the duplicate run. Alternatively, to create a new pipeline experiment, click Create new experiment, and then complete the relevant fields in the Create experiment dialog.
2. In the Name field, enter a name for the duplicate run.
3. In the Description field, enter a description for the duplicate run.
4. From the Pipeline list, select a pipeline to contain the duplicate run. Alternatively, to create a new pipeline, click Create new pipeline, and then complete the relevant fields in the Import pipeline dialog.
5. From the Pipeline version list, select a pipeline version to contain the duplicate run. Alternatively, to upload a new version, click Upload new version, and then complete the relevant fields in the Upload new version dialog.
6. In the Parameters section, configure input parameters for the duplicate run by selecting parameters from the list.

Verification

- The duplicate pipeline run is displayed on the Runs tab on the Runs page for the pipeline experiment.

## Chapter 4. Working with pipeline logsCopy link

### 4.1. About pipeline logsCopy link

You can review and analyze step logs for each step in a triggered pipeline run.

To help you troubleshoot and audit your pipelines, you can review and analyze these step logs by using the log viewer in the OpenShift AI dashboard. From here, you can search for specific log messages, view the log for each step, and download the step logs to your local machine.

If the step log file exceeds its capacity, a warning is displayed above the log viewer stating that the log window displays partial content. Expanding the warning displays further information, such as how the log viewer refreshes every three seconds, and that each step log displays the last 500 lines of log messages received. In addition, you can click download all step logs to download all step logs to your local machine.

Each step has a set of container logs. You can view these container logs by selecting a container from the Steps list in the log viewer. The`Step-main` container log consists of the log output for the step. The`step-copy-artifact` container log consists of output relating to artifact data sent to s3-compatible storage. If the data transferred between the steps in your pipeline is larger than 3 KB, five container logs are typically available. These logs contain output relating to data transferred between your persistent volume claims (PVCs).

### 4.2. Viewing pipeline step logsCopy link

To help you troubleshoot and audit your pipelines, you can review and analyze the log of each pipeline step using the log viewer. From here, you can search for specific log messages and download the logs for each step in your pipeline. If the pipeline is running, you can also pause and resume the log from the log viewer.

Note

Logs are no longer stored in S3-compatible storage for Python scripts which are running in Elyra pipelines. From OpenShift AI version 2.11, you can view these logs in the pipeline step log viewer.

For this change to take effect, you must use the Elyra runtime images provided in workbench images at version 2024.1 or later.

If you have an older workbench image version, update the Version selection field to a compatible workbench image version, for example,`2024.1`, as described in [Updating a project workbench](https://docs.redhat.com/en/documentation/red_hat_openshift_ai_cloud_service/1/html/working_on_data_science_projects/using-project-workbenches_projects#updating-a-project-workbench_projects).

Updating your workbench image version will clear any existing runtime image selections for your pipeline. After you update your workbench version, open your workbench IDE and update the properties of your pipeline to select a runtime image.

Prerequisites

- You have logged in to Red Hat OpenShift AI.
- You have previously created a data science project that is available and contains a pipeline server.
- You have imported a pipeline to an active pipeline server.
- You have previously triggered a pipeline run.

Procedure

Analyze the log using the log viewer.

- To search for a specific log message, enter at least part of the message in the search bar.
- To view the full log in a separate browser window, click the action menu (⋮) and select View raw logs. Alternatively, to expand the size of the log viewer, click the action menu (⋮) and select Expand.

Verification

- You can view the logs for each step in your pipeline.

### 4.3. Downloading pipeline step logsCopy link

Instead of viewing the step logs of a pipeline run using the log viewer on the OpenShift AI dashboard, you can download them for further analysis. You can choose to download the logs belonging to all steps in your pipeline, or you can download the log only for the step log displayed in the log viewer.

Prerequisites

- You have logged in to Red Hat OpenShift AI.
- You have previously created a data science project that is available and contains a pipeline server.
- You have imported a pipeline to an active pipeline server.
- You have previously triggered a pipeline run.

Procedure

In the log viewer, click the Download button ().

1. Select Download current stop log to download the log for the current pipeline step.
2. Select Download all step logs to download the logs for all steps in your pipeline run.

Verification

- The step logs download to your browser’s default directory for downloaded files.

## Chapter 5. Working with pipelines in JupyterLabCopy link

### 5.1. Overview of pipelines in JupyterLabCopy link

You can use Elyra to create visual end-to-end pipeline workflows in JupyterLab. Elyra is an extension for JupyterLab that provides you with a Pipeline Editor to create pipeline workflows that can be executed in OpenShift AI.

You can access the Elyra extension within JupyterLab when you create the most recent version of one of the following workbench images:

- Standard Data Science
- PyTorch
- TensorFlow
- TrustyAI
- ROCm-PyTorch
- ROCm-TensorFlow

The Elyra pipeline editor is only available in specific workbench images. To use Elyra, the workbench must be based on a JupyterLab image. The Elyra extension is not available in code-server or RStudio IDEs. The workbench must also be derived from the Standard Data Science image. It is not available in Minimal Python or CUDA-based workbenches. All other supported JupyterLab-based workbench images have access to the Elyra extension.

When you use the Pipeline Editor to visually design your pipelines, minimal coding is required to create and run pipelines. For more information about Elyra, see [Elyra Documentation](https://elyra.readthedocs.io/en/stable/getting_started/overview.html). For more information about the Pipeline Editor, see [Visual Pipeline Editor](https://elyra.readthedocs.io/en/stable/user_guide/jupyterlab-interface.html#visual-pipeline-editor). After you have created your pipeline, you can run it locally in JupyterLab, or remotely using data science pipelines in OpenShift AI.

The pipeline creation process consists of the following tasks:

- Create a data science project that contains a workbench.
- Create a pipeline server.
- Create a new pipeline in the Pipeline Editor in JupyterLab.
- Develop your pipeline by adding Python notebooks or Python scripts and defining their runtime properties.
- Define execution dependencies.
- Run or export your pipeline.

Before you can run a pipeline in JupyterLab, your pipeline instance must contain a runtime configuration. A runtime configuration defines connectivity information for your pipeline instance and S3-compatible cloud storage.

If you create a workbench as part of a data science project, a default runtime configuration is created automatically. However, if you create a workbench from the Start basic workbench tile in the OpenShift AI dashboard, you must create a runtime configuration before you can run your pipeline in JupyterLab. For more information about runtime configurations, see [Runtime Configuration](https://elyra.readthedocs.io/en/stable/user_guide/runtime-conf.html). As a prerequisite, before you create a workbench, ensure that you have created and configured a pipeline server within the same data science project as your workbench.

You can use S3-compatible cloud storage to make data available to your notebooks and scripts while they are executed. Your cloud storage must be accessible from the machine in your deployment that runs JupyterLab and from the cluster that hosts data science pipelines. Before you create and run pipelines in JupyterLab, ensure that you have your s3-compatible storage credentials readily available.

### 5.2. Accessing the pipeline editorCopy link

You can use Elyra to create visual end-to-end pipeline workflows in JupyterLab. Elyra is an extension for JupyterLab that provides you with a Pipeline Editor to create pipeline workflows that can execute in OpenShift AI.

Prerequisites

You have created and configured a pipeline server within the data science project that contains your workbench.

Important

To ensure that the runtime configuration is created automatically, you must create the pipeline server before you create the workbench.

You have started the workbench and opened the JupyterLab interface, as described in [Accessing your workbench IDE](https://docs.redhat.com/en/documentation/red_hat_openshift_ai_cloud_service/1/html/working_in_your_data_science_ide/accessing-your-workbench-ide_ide).

Important

The Elyra pipeline editor is available in specific workbench images only. To use Elyra, the workbench must be based on a JupyterLab image. The Elyra extension is not available in code-server or RStudio IDEs. The workbench must also be derived from the Standard Data Science image. It is not available in Minimal Python or CUDA-based workbenches. All other supported JupyterLab-based workbench images have access to the Elyra extension.

- You have logged in to Red Hat OpenShift AI.
- You have created a data science project.
- You have created a workbench with a workbench image that contains the Elyra extension (Standard Data Science, TensorFlow, TrustyAI, ROCm-PyTorch, ROCm-TensorFlow, or PyTorch), as described in [Creating a workbench and selecting an IDE](https://docs.redhat.com/en/documentation/red_hat_openshift_ai_cloud_service/1/html/working_on_data_science_projects/using-project-workbenches_projects#creating-a-workbench-select-ide_projects).
- You have access to S3-compatible storage.

Procedure

In the Elyra section of the JupyterLab launcher, click the Pipeline Editor tile.

The Pipeline Editor opens.

1. After you open JupyterLab, confirm that the JupyterLab launcher is automatically displayed.

Verification

- You can view the Pipeline Editor in JupyterLab.

### 5.3. Creating a runtime configurationCopy link

If you create a workbench as part of a data science project, a default runtime configuration is created automatically. However, if you create a workbench from the Start basic workbench tile in the OpenShift AI dashboard, you must create a runtime configuration before you can run your pipeline in JupyterLab. This enables you to specify connectivity information for your pipeline instance and S3-compatible cloud storage.

Prerequisites

You have created and launched a workbench from a workbench image that contains the Elyra extension (Standard Data Science, TensorFlow, TrustyAI, ROCm-PyTorch, ROCm-TensorFlow, or PyTorch).

Important

The Elyra pipeline editor is available in specific workbench images only. To use Elyra, the workbench must be based on a JupyterLab image. The Elyra extension is not available in code-server or RStudio IDEs. The workbench must also be derived from the Standard Data Science image. It is not available in Minimal Python or CUDA-based workbenches. All other supported JupyterLab-based workbench images have access to the Elyra extension.

- You have logged in to Red Hat OpenShift AI.
- You have access to S3-compatible cloud storage.
- You have created a data science project that contains a workbench.
- You have created and configured a pipeline server within the data science project that contains your workbench.

Procedure

Click the Create new runtime configuration button ().

The Add new Data Science Pipelines runtime configuration page opens.

Complete the relevant fields to define your runtime configuration.

Define the credentials of your data science pipeline:

In the Data Science Pipelines API Endpoint field, enter the API endpoint of your data science pipeline. Do not specify the pipelines namespace in this field.

Important

With Elyra, you can now use a service-based URL instead of the route-based URL by including the port number. Using the service-based URL allows your data science pipeline to access the service directly.

In the Public Data Science Pipelines API Endpoint field, enter the public API endpoint of your data science pipeline.

Important

You can obtain the data science pipelines API endpoint from the Data science pipelines → Runs page in the dashboard. Copy the relevant endpoint and enter it in the Public Data Science Pipelines API Endpoint field.

From the Authentication Type list, select the authentication type required to authenticate your pipeline.

Important

If you created a workbench directly from the Start basic workbench tile on the dashboard, select`EXISTING_BEARER_TOKEN` from the Authentication Type list.

In the Data Science Pipelines API Endpoint Password Or Token, enter the password or token required for the authentication type.

Important

To obtain the data science pipelines API endpoint token, in the upper-right corner of the OpenShift web console, click your user name and select Copy login command. After you have logged in, click Display token and copy the value of`--token=` from the Log in with this token command.

1. Optional: In the Data Science Pipelines User Namespace field, enter the relevant user namespace to run pipelines.
2. In the Data Science Pipelines API Endpoint Username field, enter the user name required for the authentication type.

Define the connectivity information of your S3-compatible storage:

1. In the Cloud Object Storage Endpoint field, enter the endpoint of your S3-compatible storage. For more information about Amazon s3 endpoints, see [Amazon Simple Storage Service endpoints and quotas](https://docs.aws.amazon.com/general/latest/gr/s3.html).
2. Optional: In the Public Cloud Object Storage Endpoint field, enter the URL of your S3-compatible storage.
3. In the Cloud Object Storage Bucket Name field, enter the name of the bucket where your pipeline artifacts are stored. If the bucket name does not exist, it is created automatically.
4. From the Cloud Object Storage Authentication Type list, select the authentication type required to access to your S3-compatible cloud storage. If you use AWS S3 buckets, select`KUBERNETES_SECRET` from the list.
5. In the Cloud Object Storage Credentials Secret field, enter the secret that contains the storage user name and password. This secret is defined in the relevant user namespace, if applicable. In addition, it must be stored on the cluster that hosts your pipeline runtime.
6. In the Cloud Object Storage Username field, enter the user name to connect to your S3-compatible cloud storage, if applicable. If you use AWS S3 buckets, enter your AWS Secret Access Key ID.
7. In the Cloud Object Storage Password field, enter the password to connect to your S3-compatible cloud storage, if applicable. If you use AWS S3 buckets, enter your AWS Secret Access Key.

Verification

- Click Save & Close.
- The runtime configuration that you created is displayed on the Runtimes tab () in the left sidebar of JupyterLab.

### 5.4. Updating a runtime configurationCopy link

To ensure that your runtime configuration is accurate and updated, you can change the settings of an existing runtime configuration.

Prerequisites

You have created and launched a workbench from a workbench image that contains the Elyra extension (Standard Data Science, TensorFlow, TrustyAI, ROCm-PyTorch, ROCm-TensorFlow, or PyTorch).

Important

The Elyra pipeline editor is available in specific workbench images only. To use Elyra, the workbench must be based on a JupyterLab image. The Elyra extension is not available in code-server or RStudio IDEs. The workbench must also be derived from the Standard Data Science image. It is not available in Minimal Python or CUDA-based workbenches. All other supported JupyterLab-based workbench images have access to the Elyra extension.

- You have logged in to Red Hat OpenShift AI.
- You have access to S3-compatible storage.
- You have created a data science project that contains a workbench.
- You have created and configured a pipeline server within the data science project that contains your workbench.
- A previously created runtime configuration is available in the JupyterLab interface.

Procedure

Hover the cursor over the runtime configuration that you want to update and click the Edit button ().

The Data Science Pipelines runtime configuration page opens.

Fill in the relevant fields to update your runtime configuration.

Define the credentials of your data science pipeline:

In the Data Science Pipelines API Endpoint field, update the API endpoint of your data science pipeline, if applicable. Do not specify the pipelines namespace in this field.

Important

With Elyra, you can now use a service-based URL instead of the route-based URL by including the port number. Using the service-based URL allows your data science pipeline to access the service directly.

From the Authentication Type list, select a new authentication type required to authenticate your pipeline, if applicable.

Important

If you created a workbench directly from the Start basic workbench tile on the dashboard, select`EXISTING_BEARER_TOKEN` from the Authentication Type list.

In the Data Science Pipelines API Endpoint Password Or Token, update the password or token required for the authentication type, if applicable.

Important

To obtain the data science pipelines API endpoint token, in the upper-right corner of the OpenShift web console, click your user name and select Copy login command. After you have logged in, click Display token and copy the value of`--token=` from the Log in with this token command.

1. In the Public Data Science Pipelines API Endpoint field, update the API endpoint of your data science pipeline, if applicable.
2. Optional: In the Data Science Pipelines User Namespace field, update the relevant user namespace to run pipelines, if applicable.
3. In the Data Science Pipelines API Endpoint Username field, update the user name required for the authentication type, if applicable.

Define the connectivity information of your S3-compatible storage:

1. In the Cloud Object Storage Endpoint field, update the endpoint of your S3-compatible storage, if applicable. For more information about Amazon s3 endpoints, see [Amazon Simple Storage Service endpoints and quotas](https://docs.aws.amazon.com/general/latest/gr/s3.html).
2. Optional: In the Public Cloud Object Storage Endpoint field, update the URL of your S3-compatible storage, if applicable.
3. In the Cloud Object Storage Bucket Name field, update the name of the bucket where your pipeline artifacts are stored, if applicable. If the bucket name does not exist, it is created automatically.
4. From the Cloud Object Storage Authentication Type list, update the authentication type required to access to your S3-compatible cloud storage, if applicable. If you use AWS S3 buckets, you must select`USER_CREDENTIALS` from the list.
5. Optional: In the Cloud Object Storage Credentials Secret field, update the secret that contains the storage user name and password, if applicable. This secret is defined in the relevant user namespace. You must save the secret on the cluster that hosts your pipeline runtime.
6. Optional: In the Cloud Object Storage Username field, update the user name to connect to your S3-compatible cloud storage, if applicable. If you use AWS S3 buckets, update your AWS Secret Access Key ID.
7. Optional: In the Cloud Object Storage Password field, update the password to connect to your S3-compatible cloud storage, if applicable. If you use AWS S3 buckets, update your AWS Secret Access Key.

Verification

- Click Save & Close.
- The runtime configuration that you updated is shown on the Runtimes tab () in the left sidebar of JupyterLab.

### 5.5. Disabling node caching in ElyraCopy link

Elyra offers a feature within OpenShift AI that allows you to cache components, or "nodes," within your data pipelines. When a pipeline component runs, Elyra stores its outputs by default. In subsequent runs, if Elyra detects that a particular component has already been executed and its inputs have not changed, it reuses the cached outputs instead of re-running the entire component.

For more information about data science pipelines caching, see [Overview of data science pipelines caching](https://docs.redhat.com/en/documentation/red_hat_openshift_ai_cloud_service/1/html/working_with_data_science_pipelines/managing-data-science-pipelines_ds-pipelines#overview-of-data-science-pipelines-caching_ds-pipelines).

If you do not want your component output to be cached, you can disable this feature for a single node or for all of the nodes in your pipeline.

Prerequisites

You have created and configured a pipeline server within the data science project that contains your workbench.

Important

To ensure that the runtime configuration is created by default, you must create the pipeline server before you create the workbench.

You have started the workbench and opened the JupyterLab interface, as described in [Accessing your workbench IDE](https://docs.redhat.com/en/documentation/red_hat_openshift_ai_cloud_service/1/html/working_in_your_data_science_ide/accessing-your-workbench-ide_ide).

Important

The Elyra pipeline editor is available in specific workbench images only. To use Elyra, the workbench must be based on a JupyterLab image that includes the Elyra extension. The Elyra extension is not available in code-server or RStudio IDEs, and it is not included in Minimal Python workbenches. Supported JupyterLab-based images such as Standard Data Science, TensorFlow, PyTorch, TrustyAI, and ROCm variants include the Elyra extension.

- You have logged in to Red Hat OpenShift AI.
- You have created a data science project.
- You have created a workbench with a workbench image that contains the Elyra extension (Standard Data Science, TensorFlow, TrustyAI, ROCm-PyTorch, ROCm-TensorFlow, or PyTorch), as described in [Creating a workbench and selecting an IDE](https://docs.redhat.com/en/documentation/red_hat_openshift_ai_cloud_service/1/html/working_on_data_science_projects/using-project-workbenches_projects#creating-a-workbench-select-ide_projects).
- You have access to S3-compatible storage.
- You have created a pipeline in JupyterLab.

Procedure

To disable caching on a single node, complete the following steps:

1. Click the Node Properties tab in the slide-out menu on the right.
2. Under Additional Properties, click the option bar under Disable node caching that is automatically populated with Use runtime default.
3. Select True.

To disable caching for all nodes on your pipeline, complete the following steps:

1. Click the Pipeline Properties tab in the slide-out menu on the right.
2. Under Node Defaults, click the option bar under Disable node caching that is automatically populated with Use runtime default.
3. Select True.

Verification

- To verify that caching is disabled for a single node, check that node runs are re-executed in your target runtime environment.
- To verify that caching is disabled for all nodes on your pipeline, check that your entire pipeline runs are re-executed in your target runtime environment.

### 5.6. Deleting a runtime configurationCopy link

After you have finished using your runtime configuration, you can delete it from the JupyterLab interface. After deleting a runtime configuration, you cannot run pipelines in JupyterLab until you create another runtime configuration.

Prerequisites

You have created and launched a workbench from a workbench image that contains the Elyra extension (Standard Data Science, TensorFlow, TrustyAI, ROCm-PyTorch, ROCm-TensorFlow, or PyTorch).

Important

The Elyra pipeline editor is available in specific workbench images only. To use Elyra, the workbench must be based on a JupyterLab image. The Elyra extension is not available in code-server or RStudio IDEs. The workbench must also be derived from the Standard Data Science image. It is not available in Minimal Python or CUDA-based workbenches. All other supported JupyterLab-based workbench images have access to the Elyra extension.

- You have logged in to Red Hat OpenShift AI.
- You have created a data science project that contains a workbench.
- You have created and configured a pipeline server within the data science project that contains your workbench.
- A previously created runtime configuration is visible in the JupyterLab interface.

Procedure

Hover the cursor over the runtime configuration that you want to delete and click the Delete Item button ().

A dialog box opens prompting you to confirm the deletion of your runtime configuration.

1. In the left sidebar of JupyterLab, click Runtimes ().
2. Click OK.

Verification

- The runtime configuration that you deleted is no longer shown on the Runtimes tab () in the left sidebar of JupyterLab.

### 5.7. Duplicating a runtime configurationCopy link

To prevent you from re-creating runtime configurations with similar values in their entirety, you can duplicate an existing runtime configuration in the JupyterLab interface.

Prerequisites

You have created and launched a workbench from a workbench image that contains the Elyra extension (Standard Data Science, TensorFlow, TrustyAI, ROCm-PyTorch, ROCm-TensorFlow, or PyTorch).

Important

The Elyra pipeline editor is available in specific workbench images only. To use Elyra, the workbench must be based on a JupyterLab image. The Elyra extension is not available in code-server or RStudio IDEs. The workbench must also be derived from the Standard Data Science image. It is not available in Minimal Python or CUDA-based workbenches. All other supported JupyterLab-based workbench images have access to the Elyra extension.

- You have logged in to Red Hat OpenShift AI.
- You have created a data science project that contains a workbench.
- You have created and configured a pipeline server within the data science project that contains your workbench.
- A previously created runtime configuration is visible in the JupyterLab interface.

Procedure

1. In the left sidebar of JupyterLab, click Runtimes ().
2. Hover the cursor over the runtime configuration that you want to duplicate and click the Duplicate button ().

Verification

- The runtime configuration that you duplicated is shown on the Runtimes tab () in the left sidebar of JupyterLab.

### 5.8. Running a pipeline in JupyterLabCopy link

You can run pipelines that you have created in JupyterLab from the Pipeline Editor user interface. Before you can run a pipeline, you must create a data science project and a pipeline server. After you create a pipeline server, you must create a workbench within the same project as your pipeline server. Your pipeline instance in JupyterLab must contain a runtime configuration. If you create a workbench as part of a data science project, a default runtime configuration is created automatically. However, if you create a workbench from the Start basic workbench tile in the OpenShift AI dashboard, you must create a runtime configuration before you can run your pipeline in JupyterLab. A runtime configuration defines connectivity information for your pipeline instance and S3-compatible cloud storage.

Prerequisites

You have created and launched a workbench from a workbench image that contains the Elyra extension (Standard Data Science, TensorFlow, TrustyAI, ROCm-PyTorch, ROCm-TensorFlow, or PyTorch).

Important

The Elyra pipeline editor is available in specific workbench images only. To use Elyra, the workbench must be based on a JupyterLab image. The Elyra extension is not available in code-server or RStudio IDEs. The workbench must also be derived from the Standard Data Science image. It is not available in Minimal Python or CUDA-based workbenches. All other supported JupyterLab-based workbench images have access to the Elyra extension.

- You have logged in to Red Hat OpenShift AI.
- You have access to S3-compatible storage.
- You have created a pipeline in JupyterLab.
- You have opened your pipeline in the Pipeline Editor in JupyterLab.
- Your pipeline instance contains a runtime configuration.
- You have created and configured a pipeline server within the data science project that contains your workbench.

Procedure

In the Pipeline Editor user interface, click Run Pipeline ().

The Run Pipeline dialog opens. The Pipeline Name field is automatically populated with the pipeline file name.

Note

After you run your pipeline, a pipeline experiment containing your pipeline run is automatically created on the Experiments → Experiments and runs page in the OpenShift AI dashboard. The experiment name matches the name that you assigned to the pipeline.

Define the settings for your pipeline run.

1. From the Runtime Configuration list, select the relevant runtime configuration to run your pipeline.
2. Optional: Configure your pipeline parameters, if applicable. If your pipeline contains nodes that reference pipeline parameters, you can change the default parameter values. If a parameter is required and has no default value, you must enter a value.

Verification

- Click OK.
- You can view the details of your pipeline run on the Experiments → Experiments and runs page in the OpenShift AI dashboard.
- You can view the output artifacts of your pipeline run. The artifacts are stored in your designated object storage bucket.

### 5.9. Exporting a pipeline in JupyterLabCopy link

You can export pipelines that you have created in JupyterLab. When you export a pipeline, the pipeline is prepared for later execution, but is not uploaded or executed immediately. During the export process, any package dependencies are uploaded to S3-compatible storage. Also, pipeline code is generated for the target runtime.

Before you can export a pipeline, you must create a data science project and a pipeline server. After you create a pipeline server, you must create a workbench within the same project as your pipeline server. In addition, your pipeline instance in JupyterLab must contain a runtime configuration. If you create a workbench as part of a data science project, a default runtime configuration is created automatically. However, if you create a workbench from the Start basic workbench tile in the OpenShift AI dashboard, you must create a runtime configuration before you can export your pipeline in JupyterLab. A runtime configuration defines connectivity information for your pipeline instance and S3-compatible cloud storage.

Prerequisites

You have created and launched a workbench from a workbench image that contains the Elyra extension (Standard Data Science, TensorFlow, TrustyAI, ROCm-PyTorch, ROCm-TensorFlow, or PyTorch).

Important

The Elyra pipeline editor is available in specific workbench images only. To use Elyra, the workbench must be based on a JupyterLab image. The Elyra extension is not available in code-server or RStudio IDEs. The workbench must also be derived from the Standard Data Science image. It is not available in Minimal Python or CUDA-based workbenches. All other supported JupyterLab-based workbench images have access to the Elyra extension.

- You have logged in to Red Hat OpenShift AI.
- You have created a data science project that contains a workbench.
- You have created and configured a pipeline server within the data science project that contains your workbench.
- You have access to S3-compatible storage.
- You have a created a pipeline in JupyterLab.
- You have opened your pipeline in the Pipeline Editor in JupyterLab.
- Your pipeline instance contains a runtime configuration.

Procedure

In the Pipeline Editor user interface, click Export Pipeline ().

The Export Pipeline dialog opens. The Pipeline Name field is automatically populated with the pipeline file name.

Define the settings to export your pipeline.

1. From the Runtime Configuration list, select the relevant runtime configuration to export your pipeline.
2. From the Export Pipeline as select an appropriate file format
3. In the Export Filename field, enter a file name for the exported pipeline.
4. Select the Replace if file already exists check box to replace an existing file of the same name as the pipeline you are exporting.
5. Optional: Configure your pipeline parameters, if applicable. If your pipeline contains nodes that reference pipeline parameters, you can change the default parameter values. If a parameter is required and has no default value, you must enter a value.

Verification

- Click OK.
- You can view the file containing the pipeline that you exported in your designated object storage bucket.

## Chapter 6. Troubleshooting DSPA component errorsCopy link

This table displays common errors found in DataSciencePipelinesApplication (DSPA) components, along with the associated status, message, and proposed solution. The Ready condition type accumulates errors from various DSPA components, providing a status view of the DSPA deployment.

Expand

`ObjectStorageAvailable`

Ready

False

False

Error message:`Could not connect to Object Store: tls: failed to verify certificate: x509: certificate signed by unknown authority`

Solution: This issue occurs in clusters that use self-signed certificates with OpenShift AI version 2.9 or later. The data science pipelines manager cannot connect to the object storage because it does not trust the object storage SSL certificate. Therefore, the pipeline server cannot be created. Contact your IT operations administrator to add the relevant Certificate Authority bundle.

For more information, see [Working with certificates](https://docs.redhat.com/en/documentation/red_hat_openshift_ai_cloud_service/1/html/installing_and_uninstalling_openshift_ai_cloud_service/working-with-certificates_certs).

`ObjectStorageAvailable`

Ready

False

False

Error message:`Could not connect to Object Store Deployment for component "ds-pipeline-pipelines-definition" is missing - prerequisite component might not yet be available. Deployment for component "ds-pipeline-persistenceagent-pipelines-definition" is missing - prerequisite component might not yet be available. Deployment for component "ds-pipeline-scheduledworkflow-pipelines-definition" is missing - prerequisite component might not yet be available.`

Solution: In clusters running OpenShift AI 2.8.x, the data science pipelines manager might fail to connect to the object storage, and the pipeline server might not be created.

Ensure that your object store credentials and connection information are accurate, and verify that the object store is accessible from within the data science project’s associated OpenShift namespace. One common issue is that the object storage SSL certificate is not trusted, particularly if self-signed certificates are used.

Verify and update your object storage credentials, then retry the operation.

`ObjectStorageAvailable`

Ready

False

False

Error message:`Wrong credentials for Object Storage: Could not connect to (minio-my-project.apps.my-cluster.com), Error: The request signature we calculated does not match the signature you provided. Check your key and signing method.`

Solution: Provide the correct credentials for your object storage and retry the operation.

`DatabaseAvailable`

Ready

False

False

Error message:`FailingToDeploy: Dial tcp XXX.XX.XXX.XXX:3306 : i/o timeout`

Solution: If the issue persists beyond startup, check for network issues or misconfigurations in the database connection settings.

`DatabaseAvailable`

Ready

False

False

Error message:`Unable to connect to external database: tls: failed to verify certificate: x509: certificate signed by unknown authority`

Solution: This issue can occur when you use any external database, such as Amazon RDS. The data science pipelines manager cannot connect to the database because it does not trust the database SSL certificate, preventing the creation of the pipeline server. Contact your IT operations administrator to add the relevant certificates.

For more information, see [Working with certificates](https://docs.redhat.com/en/documentation/red_hat_openshift_ai_cloud_service/1/html/installing_and_uninstalling_openshift_ai_cloud_service/working-with-certificates_certs).

`DatabaseAvailable`

Ready

False

False

Error message:`Error 1129: Host 'A.B.C.D' is blocked because of many connection errors.`

Solution: This issue might occur when using an external database, such as Amazon RDS. Initially, the pipeline server is created successfully. However, after some time, the OpenShift AI dashboard displays an "Error displaying pipelines" message, and the DSPA conditions indicate that the host is blocked due to multiple connection errors.

For more information on how resolve this issue for an external Amazon RDS database, see [Resolving "Host is blocked because of many connection errors" error in Amazon RDS for MySQL](https://repost.aws/knowledge-center/rds-blocked-host-error). Note: Clicking this link opens an external website.

`APIServerReady`

Ready

False

False

Error message:`Route creation failed due to lengthy project name: Route.route.openshift.io is invalid: spec.host exceeds 63 characters.`

Solution: Ensure that the project name in OpenShift is less than 40 characters.

`APIServerReady`

Ready

False

False

Error message:`FailingToDeploy: Component replica failed to create. Message: serviceaccount "ds-pipeline-sample" not found.`

Solution: If the failure persists for more than 25 seconds during DSPA startup, recreate the missing service account.

`PersistenceAgentReady`

Ready

False

False

Error message:`FailingToDeploy: Component's replica failed to create. Message: serviceaccount "ds-pipeline-persistenceagent-sample" not found.`

Solution: If the failure persists for more than 25 seconds during DSPA startup, recreate the missing service account.

`ScheduledWorkflowReady`

Ready

False

False

Error message:`FailingToDeploy: Component's replica failed to create. Message: serviceaccount "ds-pipeline-scheduledworkflow-sample" not found.`

Solution: If the failure persists for more than 25 seconds during DSPA startup, recreate the missing service account.

`MLMDProxyReady`

Ready

False

False

Error message:`Deploying: Component [ds-pipeline-scheduledworkflow-sample] is still deploying.`

Solution: Wait for DSPA startup to complete. If deployment fails after 25 seconds, check the logs for further information.

| | Type | Status | Error message and solution |
| --- | --- | --- | --- |

Show more

### 6.1. Common errors across DSP componentsCopy link

The following table lists errors that might occur across multiple DSPA components:

Expand

Condition: Component Deployment Not Found

Condition type:`ComponentDeploymentNotFound`

False

Error message:`Deployment for component is missing - prerequisite component might not yet be available.`

Solution: The deployment for the component does not exist. Typically, this issue occurs due to missing deployments or issues that occurred during creation.

Condition: Deployment Scaled Down

Condition type:`MinimumReplicasAvailable`

False

Error message:`Deployment for component is scaled down.`

Solution: The component is unavailable as the deployment replica count is set to zero.

Condition: Component Failing to Progress

Condition type:`FailingToDeploy`

False

Error message:`Component has failed to progress. Reason:. Message: `

Solution: The deployment has stalled due to`ProgressDeadlineExceeded` or`ReplicaSetCreateError` issues, or similar.

Condition: Replica Creation Failure

Condition type:`FailingToDeploy`

False

Error message:`Component's replica has failed to create. Reason:. Message: `

Solution: Replica creation has failed, typically due to an error in the replica set or with the service accounts.

Condition: Pod-Level Failures

Condition type:`FailingToDeploy`

False

Error message:`Concatenated failure messages for each pod.`

Solution: Deployment pods are in a failed state. Check the pod logs for further information.

Condition: Pod in CrashLoopBackOff

Condition type:`FailingToDeploy`

False

Error message:`Component is in CrashLoopBackOff. Message from pod: `

Solution: Pod containers are failing repeatedly, often due to incorrect environment variables or missing service accounts.

Condition: Component Deploying (No Errors)

Condition: type:`Deploying`

False

Error message:`Component is deploying.`

Solution: The component deployment process is ongoing with no errors detected.

Condition: Component Minimally Available

Condition type:`MinimumReplicasAvailable`

True

Error message:`Component is minimally available.`

Solution: The component is available, but with only the minimum number of replicas running.

| | Deployment condition and condition type | Status | Error message and solution |
| --- | --- | --- | --- |

Show more

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



## Configuring your model-serving platform

Configuring your model-serving platform | Red Hat OpenShift AI Cloud Service | 1 | Red Hat Documentation

 

 

 

 

 

 

 Updates for Red Hat OpenShift AI Cloud Service are only provided up to the end of October 2025. 

 

# Configuring your model-serving platform

---

Red Hat OpenShift AI Cloud Service 1

## Configure your model-serving platform in Red Hat OpenShift AI Cloud Service

Legal Notice

Abstract

As a Red Hat OpenShift AI administrator, you can configure your model serving platform in Red Hat OpenShift AI Cloud Service.

---

## Chapter 1. About model-serving platformsCopy link

As an OpenShift AI administrator, you can enable your preferred serving platform and make it available for serving models. You can also add a custom or a tested and verified model-serving runtime.

### 1.1. About model servingCopy link

When you serve a model, you upload a trained model into Red Hat OpenShift AI for querying, which allows you to integrate your trained models into intelligent applications.

You can upload a model to an S3-compatible object storage, persistent volume claim, or Open Container Initiative (OCI) image. You can then access and train the model from your project workbench. After training the model, you can serve or deploy the model using a model-serving platform.

Serving or deploying the model makes the model available as a service, or model runtime server, that you can access using an API. You can then access the inference endpoints for the deployed model from the dashboard and see predictions based on data inputs that you provide through API calls. Querying the model through the API is also called model inferencing.

You can serve models on one of the following model-serving platforms:

- Legal Notice
- Single-model serving platform
- Multi-model serving platform
- NVIDIA NIM model serving platform

The model-serving platform that you choose depends on your business needs:

- If you want to deploy each model on its own runtime server, or want to use a serverless deployment, select the single-model serving platform. The single-model serving platform is recommended for production use.
- If you want to deploy multiple models with only one runtime server, select the multi-model serving platform. This option is best if you are deploying more than 1,000 small and medium models and want to reduce resource consumption.
- If you want to use NVIDIA Inference Microservices (NIMs) to deploy a model, select the NVIDIA NIM-model serving platform.

#### 1.1.1. Single-model serving platformCopy link

You can deploy each model from a dedicated model server on the single-model serving platform. Deploying models from a dedicated model server can help you deploy, monitor, scale, and maintain models that require increased resources. This model serving platform is ideal for serving large models. The single-model serving platform is based on the [KServe](https://github.com/kserve/kserve) component.

The single-model serving platform is helpful for use cases such as:

- Large language models (LLMs)
- Generative AI

For more information about setting up the single-model serving platform, see [Installing the single-model serving platform](https://docs.redhat.com/en/documentation/red_hat_openshift_ai_cloud_service/1/html/installing_and_uninstalling_openshift_ai_cloud_service/installing-the-single-model-serving-platform_component-install).

#### 1.1.2. Multi-model serving platformCopy link

You can deploy multiple models from the same model server on the multi-model serving platform. Each of the deployed models shares the server resources. Deploying multiple models from the same model server can be advantageous on OpenShift clusters that have finite compute resources or pods. This model serving platform is ideal for serving small and medium models in large quantities. The multi-model serving platform is based on the [ModelMesh](https://github.com/kserve/modelmesh) component.

For more information about setting up the multi-model serving platform, see [Installing the multi-model serving platform](https://docs.redhat.com/en/documentation/red_hat_openshift_ai_cloud_service/1/html/installing_and_uninstalling_openshift_ai_cloud_service/installing-the-multi-model-serving-platform_component-install).

#### 1.1.3. NVIDIA NIM model serving platformCopy link

You can deploy models using NVIDIA Inference Microservices (NIM) on the NVIDIA NIM model serving platform.

NVIDIA NIM, part of NVIDIA AI Enterprise, is a set of microservices designed for secure, reliable deployment of high performance AI model inferencing across clouds, data centers and workstations.

NVIDIA NIM inference services are helpful for use cases such as:

- Using GPU-accelerated containers inferencing models optimized by NVIDIA
- Deploying generative AI for virtual screening, content generation, and avatar creation

The NVIDIA NIM model serving platform is based on the single-model serving platform. To use the NVIDIA NIM model serving platform, you must first install the single-model serving platform.

For more information, see [Installing the single-model serving platform](https://docs.redhat.com/en/documentation/red_hat_openshift_ai_cloud_service/1/html/installing_and_uninstalling_openshift_ai_cloud_service/installing-the-single-model-serving-platform_component-install).

### 1.2. Model-serving runtimesCopy link

You can serve models on the single-model serving platform by using model-serving runtimes. The configuration of a model-serving runtime is defined by the ServingRuntime and InferenceService custom resource definitions (CRDs).

#### 1.2.1. ServingRuntimeCopy link

The ServingRuntime CRD creates a serving runtime, an environment for deploying and managing a model. It creates the templates for pods that dynamically load and unload models of various formats and also exposes a service endpoint for inferencing requests.

The following YAML configuration is an example of the vLLM ServingRuntime for KServe model-serving runtime. The configuration includes various flags, environment variables and command-line arguments.

```plaintext
apiVersion: serving.kserve.io/v1alpha1
kind: ServingRuntime
metadata:
  annotations:
    opendatahub.io/recommended-accelerators: '["nvidia.com/gpu"]' 
```

1

```plaintext

    openshift.io/display-name: vLLM ServingRuntime for KServe 
```

2

```plaintext

  labels:
    opendatahub.io/dashboard: "true"
  name: vllm-runtime
  namespace: <namespace>
spec:
  annotations:
    prometheus.io/path: /metrics 
```

3

```plaintext

    prometheus.io/port: "8080" 
```

4

```plaintext

  containers:
    - args:
        - --port=8080
        - --model=/mnt/models 
```

5

```plaintext

        - --served-model-name={{.Name}} 
```

6

```plaintext

      command: 
```

7

```plaintext

        - python
        - '-m'
        - vllm.entrypoints.openai.api_server
      env:
        - name: HF_HOME
          value: /tmp/hf_home
      image: quay.io/modh/vllm@sha256:8a3dd8ad6e15fe7b8e5e471037519719d4d8ad3db9d69389f2beded36a6f5b21 
```

8

```plaintext

      name: kserve-container
      ports:
        - containerPort: 8080
          protocol: TCP
  multiModel: false 
```

9

```plaintext

  supportedModelFormats: 
```

10

```plaintext

    - autoSelect: true
      name: vLLM
```

1

The recommended accelerator to use with the runtime.

2

The name with which the serving runtime is displayed.

3

The endpoint used by Prometheus to scrape metrics for monitoring.

4

The port used by Prometheus to scrape metrics for monitoring.

5

The path to where the model files are stored in the runtime container.

6

Passes the model name that is specified by the`{{.Name}}` template variable inside the runtime container specification to the runtime environment. The`{{.Name}}` variable maps to the`spec.predictor.name` field in the`InferenceService` metadata object.

7

The entrypoint command that starts the runtime container.

8

The runtime container image used by the serving runtime. This image differs depending on the type of accelerator used.

9

Specifies that the runtime is used for single-model serving.

10

Specifies the model formats supported by the runtime.

#### 1.2.2. InferenceServiceCopy link

The InferenceService CRD creates a server or inference service that processes inference queries, passes it to the model, and then returns the inference output.

The inference service also performs the following actions:

- Specifies the location and format of the model.
- Specifies the serving runtime used to serve the model.
- Enables the passthrough route for gRPC or REST inference.
- Defines HTTP or gRPC endpoints for the deployed model.

The following example shows the InferenceService YAML configuration file that is generated when deploying a granite model with the vLLM runtime:

```plaintext
apiVersion: serving.kserve.io/v1beta1
kind: InferenceService
metadata:
  annotations:
    openshift.io/display-name: granite
    serving.knative.openshift.io/enablePassthrough: 'true'
    sidecar.istio.io/inject: 'true'
    sidecar.istio.io/rewriteAppHTTPProbers: 'true'
  name: granite
  labels:
    opendatahub.io/dashboard: 'true'
spec:
  predictor:
    maxReplicas: 1
    minReplicas: 1
    model:
      modelFormat:
        name: vLLM
      name: ''
      resources:
        limits:
          cpu: '6'
          memory: 24Gi
          nvidia.com/gpu: '1'
        requests:
          cpu: '1'
          memory: 8Gi
          nvidia.com/gpu: '1'
      runtime: vllm-runtime
      storage:
        key: aws-connection-my-storage
        path: models/granite-7b-instruct/
    tolerations:
      - effect: NoSchedule
        key: nvidia.com/gpu
        operator: Exists
```

#### 1.2.3. Model-serving runtimes for acceleratorsCopy link

OpenShift AI provides support for accelerators through preinstalled model-serving runtimes.

##### 1.2.3.1. NVIDIA GPUsCopy link

You can serve models with NVIDIA graphics processing units (GPUs) by using the vLLM NVIDIA GPU ServingRuntime for KServe runtime. To use the runtime, you must enable GPU support in OpenShift AI. This includes installing and configuring the Node Feature Discovery Operator on your cluster. For more information, see [Installing the Node Feature Discovery Operator](https://docs.redhat.com/en/documentation/openshift_container_platform/4.19/html/specialized_hardware_and_driver_enablement/psap-node-feature-discovery-operator#installing-the-node-feature-discovery-operator_psap-node-feature-discovery-operator) and [Enabling NVIDIA GPUs](https://docs.redhat.com/en/documentation/red_hat_openshift_ai_cloud_service/1/html/managing_openshift_ai/enabling-accelerators#enabling-nvidia-gpus_managing-rhoai).

##### 1.2.3.2. Intel Gaudi acceleratorsCopy link

You can serve models with Intel Gaudi accelerators by using the vLLM Intel Gaudi Accelerator ServingRuntime for KServe runtime. To use the runtime, you must enable hybrid processing (HPU) support in OpenShift AI. This includes installing the Intel Gaudi Base Operator and configuring a hardware profile. For more information, see [Intel Gaudi Base Operator OpenShift installation](https://docs.habana.ai/en/latest/Installation_Guide/Additional_Installation/OpenShift_Installation/index.html#openshift-installation) and [Working with hardware profiles](https://docs.redhat.com/en/documentation/red_hat_openshift_ai_cloud_service/1/html/working_with_accelerators/working-with-hardware-profiles_accelerators).

For information about recommended vLLM parameters, environment variables, supported configurations and more, see [vLLM with Intel® Gaudi® AI Accelerators](https://github.com/HabanaAI/vllm-fork/blob/habana_main/README_GAUDI.md).

Note

Warm-up is a model initialization and performance optimization step that is useful for reducing cold-start delays and first-inference latency. Depending on the model size, warm-up can lead to longer model loading times.

While highly recommended in production environments to avoid performance limitations, you can choose to skip warm-up for non-production environments to reduce model loading times and accelerate model development and testing cycles. To skip warm-up, follow the steps described in [Customizing the parameters of a deployed model-serving runtime](https://docs.redhat.com/en/documentation/red_hat_openshift_ai_cloud_service/1/html/configuring_your_model-serving_platform/customizing_model_deployments#customizing-parameters-serving-runtime_rhoai-admin) to add the following environment variable in the Configuration parameters section of your model deployment:

```plaintext
`VLLM_SKIP_WARMUP="true"`
```

##### 1.2.3.3. AMD GPUsCopy link

You can serve models with AMD GPUs by using the vLLM AMD GPU ServingRuntime for KServe runtime. To use the runtime, you must enable support for AMD graphic processing units (GPUs) in OpenShift AI. This includes installing the AMD GPU operator and configuring a hardware profile. For more information, see [Deploying the AMD GPU operator on OpenShift](https://instinct.docs.amd.com/projects/gpu-operator/en/latest/installation/openshift-olm.html) in the AMD documentation and [Working with hardware profiles](https://docs.redhat.com/en/documentation/red_hat_openshift_ai_cloud_service/1/html/working_with_accelerators/working-with-hardware-profiles_accelerators).

##### 1.2.3.4. IBM Spyre AI accelerators on x86Copy link

Important

Support for IBM Spyre AI Accelerators on x86 is currently available in Red Hat OpenShift AI as a Technology Preview feature. Technology Preview features are not supported with Red Hat production service level agreements (SLAs) and might not be functionally complete. Red Hat does not recommend using them in production. These features provide early access to upcoming product features, enabling customers to test functionality and provide feedback during the development process.

For more information about the support scope of Red Hat Technology Preview features, see [Technology Preview Features Support Scope](https://access.redhat.com/support/offerings/techpreview/).

You can serve models with IBM Spyre AI accelerators on x86 by using the vLLM Spyre AI Accelerator ServingRuntime for KServe runtime. To use the runtime, you must install the Spyre Operator and configure a hardware profile. For more information, see [Spyre operator image](https://catalog.redhat.com/en/software/containers/ibm-aiu/spyre-operator/688a1121575e62c686a471d4) and [Working with hardware profiles](https://docs.redhat.com/en/documentation/red_hat_openshift_ai_cloud_service/1/html/working_with_accelerators/working-with-hardware-profiles_accelerators).

#### 1.2.4. Supported model-serving runtimesCopy link

OpenShift AI includes several preinstalled model-serving runtimes. You can use preinstalled model-serving runtimes to start serving models without modifying or defining the runtime yourself. You can also add a custom runtime to support a model.

See [Supported configurations](https://access.redhat.com/articles/rhoai-supported-configs) for a list of the supported model-serving runtimes and deployment requirements.

For help adding a custom runtime, see [Adding a custom model-serving runtime for the single-model serving platform](https://docs.redhat.com/en/documentation/red_hat_openshift_ai_cloud_service/1/html/configuring_your_model-serving_platform/configuring_model_servers_on_the_single_model_serving_platform#adding-a-custom-model-serving-runtime-for-the-single-model-serving-platform_rhoai-admin).

#### 1.2.5. Tested and verified model-serving runtimesCopy link

Tested and verified runtimes are community versions of model-serving runtimes that have been tested and verified against specific versions of OpenShift AI.

Red Hat tests the current version of a tested and verified runtime each time there is a new version of OpenShift AI. If a new version of a tested and verified runtime is released in the middle of an OpenShift AI release cycle, it will be tested and verified in an upcoming release.

See [Supported configurations](https://access.redhat.com/articles/rhoai-supported-configs) for a list of tested and verified runtimes in OpenShift AI.

Note

Tested and verified runtimes are not directly supported by Red Hat. You are responsible for ensuring that you are licensed to use any tested and verified runtimes that you add, and for correctly configuring and maintaining them.

For more information, see [Tested and verified runtimes in OpenShift AI](https://access.redhat.com/articles/7089743).

Additional resources

- [Inference endpoints](https://docs.redhat.com/en/documentation/red_hat_openshift_ai_cloud_service/1/html/deploying_models/making_inference_requests_to_deployed_models#inference-endpoints_rhoai-user)

## Chapter 2. Configuring model servers on the single-model serving platformCopy link

On the single-model serving platform, you configure model servers by using model-serving runtimes. A model-serving runtime adds support for a specified set of model frameworks and the model formats that they support.

### 2.1. About the single-model serving platformCopy link

For deploying large models such as large language models (LLMs), OpenShift AI includes a single-model serving platform that is based on the [KServe](https://github.com/kserve/kserve) component. Because each model is deployed on its own model server, the single-model serving platform helps you to deploy, monitor, scale, and maintain large models that require increased resources.

#### 2.1.1. ComponentsCopy link

- [KServe](https://github.com/opendatahub-io/kserve): A Kubernetes custom resource definition (CRD) that orchestrates model serving for all types of models. KServe includes model-serving runtimes that implement the loading of given types of model servers. KServe also handles the lifecycle of the deployment object, storage access, and networking setup.
- [Red Hat OpenShift Serverless](https://docs.redhat.com/en/documentation/openshift_container_platform/4.19/html/serverless/about-serverless): A cloud-native development model that allows for serverless deployments of models. OpenShift Serverless is based on the open source [Knative](https://knative.dev/docs/) project.
- [Red Hat OpenShift Service Mesh](https://docs.redhat.com/en/documentation/red_hat_openshift_service_on_aws/4/html/service_mesh/service-mesh-2-x#ossm-architecture): A service mesh networking layer that manages traffic flows and enforces access policies. OpenShift Service Mesh is based on the open source [Istio](https://istio.io/) project.

#### 2.1.2. Installation optionsCopy link

To install the single-model serving platform, you have the following options:

Automated installation

If you have not already created a`ServiceMeshControlPlane` or`KNativeServing` resource on your OpenShift cluster, you can configure the Red Hat OpenShift AI Operator to install KServe and configure its dependencies.

For more information about automated installation, see [Configuring automated installation of KServe](https://docs.redhat.com/en/documentation/red_hat_openshift_ai_cloud_service/1/html/installing_and_uninstalling_openshift_ai_cloud_service/installing-the-single-model-serving-platform_component-install#configuring-automated-installation-of-kserve_component-install).

Manual installation

If you have already created a`ServiceMeshControlPlane` or`KNativeServing` resource on your OpenShift cluster, you cannot configure the Red Hat OpenShift AI Operator to install KServe and configure its dependencies. In this situation, you must install KServe manually.

For more information about manual installation, see [Manually installing KServe](https://docs.redhat.com/en/documentation/red_hat_openshift_ai_cloud_service/1/html/installing_and_uninstalling_openshift_ai_cloud_service/installing-the-single-model-serving-platform_component-install#manually-installing-kserve_component-install).

#### 2.1.3. AuthorizationCopy link

You can add [Authorino](https://github.com/kuadrant/authorino) as an authorization provider for the single-model serving platform. Adding an authorization provider allows you to enable token authentication for models that you deploy on the platform, which ensures that only authorized parties can make inference requests to the models.

To add Authorino as an authorization provider on the single-model serving platform, you have the following options:

- If automated installation of the single-model serving platform is possible on your cluster, you can include Authorino as part of the automated installation process.
- If you need to manually install the single-model serving platform, you must also manually configure Authorino.

For guidance on choosing an installation option for the single-model serving platform, see Installation options.

#### 2.1.4. MonitoringCopy link

You can configure monitoring for the single-model serving platform and use Prometheus to scrape metrics for each of the pre-installed model-serving runtimes.

### 2.2. Enabling the single-model serving platformCopy link

When you have installed KServe, you can use the Red Hat OpenShift AI dashboard to enable the single-model serving platform. You can also use the dashboard to enable model-serving runtimes for the platform.

Prerequisites

The`spec.dashboardConfig.disableKServe` dashboard configuration option is set to`false`(the default).

For more information about setting dashboard configuration options, see [Customizing the dashboard](https://docs.redhat.com/en/documentation/red_hat_openshift_ai_cloud_service/1/html/managing_resources/customizing-the-dashboard).

- You have logged in to OpenShift AI as a user with OpenShift AI administrator privileges.
- You have installed KServe.

Procedure

Enable the single-model serving platform as follows:

Select KServe RawDeployment or Knative Serverless deployment mode.

For more information about these deployment mode options, see [About KServe deployment modes](https://docs.redhat.com/en/documentation/red_hat_openshift_ai_cloud_service/1/html/deploying_models/deploying_models_on_the_single_model_serving_platform#about-kserve-deployment-modes_rhoai-user).

1. In the left menu, click Settings → Cluster settings.
2. Locate the Model serving platforms section.
3. To enable the single-model serving platform for projects, select the Single-model serving platform checkbox.
4. Click Save changes.

Enable preinstalled runtimes for the single-model serving platform as follows:

In the left menu of the OpenShift AI dashboard, click Settings → Serving runtimes.

The Serving runtimes page shows preinstalled runtimes and any custom runtimes that you have added.

For more information about preinstalled runtimes, see [Supported runtimes](https://docs.redhat.com/en/documentation/red_hat_openshift_ai_cloud_service/1/html/configuring_your_model-serving_platform/configuring-your-model-serving-platform_rhoai-admin#supported-model-serving-runtimes_rhoai-admin).

Set the runtime that you want to use to Enabled.

The single-model serving platform is now available for model deployments.

### 2.3. Enabling speculative decoding and multi-modal inferencingCopy link

You can configure the vLLM NVIDIA GPU ServingRuntime for KServe runtime to use speculative decoding, a parallel processing technique to optimize inferencing time for large language models (LLMs).

You can also configure the runtime to support inferencing for vision-language models (VLMs). VLMs are a subset of multi-modal models that integrate both visual and textual data.

The following procedure describes customizing the vLLM NVIDIA GPU ServingRuntime for KServe runtime for speculative decoding and multi-modal inferencing.

Prerequisites

- You have logged in to OpenShift AI as a user with OpenShift AI administrator privileges.
- If you are using the vLLM model-serving runtime for speculative decoding with a draft model, you have stored the original model and the speculative model in the same folder within your S3-compatible object storage.

Procedure

To configure the vLLM model-serving runtime for speculative decoding by matching n-grams in the prompt, add the following arguments under Additional serving runtime arguments in the Configuration parameters section:

```plaintext
--speculative-model=[ngram]
--num-speculative-tokens=<NUM_SPECULATIVE_TOKENS>
--ngram-prompt-lookup-max=<NGRAM_PROMPT_LOOKUP_MAX>
--use-v2-block-manager
```

Replace` ` and` ` with your own values.

Note

Inferencing throughput varies depending on the model used for speculating with n-grams.

To configure the vLLM model-serving runtime for speculative decoding with a draft model, add the following arguments under Additional serving runtime arguments in the Configuration parameters section:

```plaintext
--port=8080
--served-model-name={{.Name}}
--distributed-executor-backend=mp
--model=/mnt/models/<path_to_original_model>
--speculative-model=/mnt/models/<path_to_speculative_model>
--num-speculative-tokens=<NUM_SPECULATIVE_TOKENS>
--use-v2-block-manager
```

1. Replace` ` and` ` with the paths to the speculative model and original model on your S3-compatible object storage.
2. Replace` ` with your own value.

To configure the vLLM model-serving runtime for multi-modal inferencing, add the following arguments under Additional serving runtime arguments in the Configuration parameters section:

```plaintext
--trust-remote-code
```

Note

Only use the`--trust-remote-code` argument with models from trusted sources.

Verification

If you have configured the vLLM model-serving runtime for speculative decoding, use the following example command to verify API requests to your deployed model:

```plaintext
curl -v https://<inference_endpoint_url>:443/v1/chat/completions
-H "Content-Type: application/json"
-H "Authorization: Bearer <token>"
```

If you have configured the vLLM model-serving runtime for multi-modal inferencing, use the following example command to verify API requests to the vision-language model (VLM) that you have deployed:

```plaintext
curl -v https://<inference_endpoint_url>:443/v1/chat/completions
-H "Content-Type: application/json"
-H "Authorization: Bearer <token>"
-d '{"model":"<model_name>",
     "messages":
        [{"role":"<role>",
          "content":
             [{"type":"text", "text":"<text>"
              },
              {"type":"image_url", "image_url":"<image_url_link>"
              }
             ]
         }
        ]
    }'
```

- Click Deploy.

### 2.4. Adding a custom model-serving runtime for the single-model serving platformCopy link

A model-serving runtime adds support for a specified set of model frameworks and the model formats supported by those frameworks. You can use the [preinstalled runtimes](https://docs.redhat.com/en/documentation/red_hat_openshift_ai_cloud_service/1/html/configuring_your_model-serving_platform/configuring-your-model-serving-platform_rhoai-admin#supported-model-serving-runtimes_rhoai-admin) that are included with OpenShift AI. You can also add your own custom runtimes if the default runtimes do not meet your needs.

As an administrator, you can use the OpenShift AI interface to add and enable a custom model-serving runtime. You can then choose the custom runtime when you deploy a model on the single-model serving platform.

Note

Red Hat does not provide support for custom runtimes. You are responsible for ensuring that you are licensed to use any custom runtimes that you add, and for correctly configuring and maintaining them.

Prerequisites

- You have logged in to OpenShift AI as a user with OpenShift AI administrator privileges.
- You have built your custom runtime and added the image to a container image repository such as [Quay](https://quay.io/).

Procedure

From the OpenShift AI dashboard, click Settings → Serving runtimes.

The Serving runtimes page opens and shows the model-serving runtimes that are already installed and enabled.

To add a custom runtime, choose one of the following options:

- To start with an existing runtime (for example, vLLM NVIDIA GPU ServingRuntime for KServe), click the action menu (⋮) next to the existing runtime and then click Duplicate.
- To add a new custom runtime, click Add serving runtime.

Optional: If you started a new runtime (rather than duplicating an existing one), add your code by choosing one of the following options:

Upload a YAML file

In the file browser, select a YAML file on your computer.

The embedded YAML editor opens and shows the contents of the file that you uploaded.

1. Click Upload files.

Enter YAML code directly in the editor

1. Click Start from scratch.
2. Enter or paste YAML code directly in the embedded editor.

Note

In many cases, creating a custom runtime will require adding new or custom parameters to the`env` section of the`ServingRuntime` specification.

Click Add.

The Serving runtimes page opens and shows the updated list of runtimes that are installed. Observe that the custom runtime that you added is automatically enabled. The API protocol that you specified when creating the runtime is shown.

Verification

- Optional: To edit your custom runtime, click the action menu (⋮) and select Edit.
- The custom model-serving runtime that you added is shown in an enabled state on the Serving runtimes page.

### 2.5. Adding a tested and verified model-serving runtime for the single-model serving platformCopy link

In addition to preinstalled and custom model-serving runtimes, you can also use Red Hat tested and verified model-serving runtimes to support your requirements. For more information about Red Hat tested and verified runtimes, see [Tested and verified runtimes for Red Hat OpenShift AI](https://access.redhat.com/articles/7089743).

You can use the Red Hat OpenShift AI dashboard to add and enable tested and verified runtimes for the single-model serving platform. You can then choose the runtime when you deploy a model on the single-model serving platform.

Prerequisites

- You have logged in to OpenShift AI as a user with OpenShift AI administrator privileges.
- If you are deploying the IBM Z Accelerated for NVIDIA Triton Inference Server runtime, you have access to IBM Cloud Container Registry to pull the container image. For more information about obtaining credentials to the IBM Cloud Container Registry, see [Downloading the IBM Z Accelerated for NVIDIA Triton Inference Server container image](https://github.com/IBM/ibmz-accelerated-for-nvidia-triton-inference-server?tab=readme-ov-file#container).
- If you are deploying the IBM Power Accelerated Triton Inference Server runtime, you can access the container image from the [Triton Inference Server Quay repository](https://quay.io/repository/powercloud/tritonserver).

Procedure

From the OpenShift AI dashboard, click Settings → Serving runtimes.

The Serving runtimes page opens and shows the model-serving runtimes that are already installed and enabled.

Follow these steps to add the IBM Power Accelerated for NVIDIA Triton Inference Server runtime:

If you selected the REST API protocol, enter or paste the following YAML code directly in the embedded editor.

```plaintext
apiVersion: serving.kserve.io/v1alpha1
kind: ServingRuntime
metadata:
  name: triton-ppc64le-runtime
  annotations:
    openshift.io/display-name: Triton Server ServingRuntime for KServe(ppc64le)
spec:
  supportedModelFormats:
    - name: python
    - name: onnx
      autoSelect: true
  multiModel: false
  containers:
    - command:
        - tritonserver
        - --model-repository=/mnt/models
      name: kserve-container
      image: quay.io/powercloud/tritonserver:latest
      resources:
        requests:
          cpu: 2
          memory: 8Gi
        limits:
          cpu: 2
          memory: 8Gi
      ports:
        - containerPort: 8000
```

Follow these steps to add the IBM Z Accelerated for NVIDIA Triton Inference Server runtime:

If you selected the REST API protocol, enter or paste the following YAML code directly in the embedded editor.

```plaintext
apiVersion: serving.kserve.io/v1alpha1
kind: ServingRuntime
metadata:
  name: ibmz-triton-rest
  labels:
    opendatahub.io/dashboard: "true"
spec:
  containers:
    - name: kserve-container
      command:
        - /bin/sh
        - -c
      args:
        - /opt/tritonserver/bin/tritonserver --model-repository=/mnt/models --http-port=8000 --grpc-port=8001 --metrics-port=8002
      image: icr.io/ibmz/ibmz-accelerated-for-nvidia-triton-inference-server:<version>
      securityContext:
        allowPrivilegeEscalation: false
        capabilities:
          drop:
            - ALL
        runAsNonRoot: true
        seccompProfile:
          type: RuntimeDefault
      resources:
        limits:
          cpu: "2"
          memory: 4Gi
        requests:
          cpu: "2"
          memory: 4Gi
      ports:
        - containerPort: 8000
          protocol: TCP
  protocolVersions:
    - v2
    - grpc-v2
  supportedModelFormats:
    - name: onnx-mlir
      version: "1"
      autoSelect: true
    - name: snapml
      version: "1"
      autoSelect: true
    - name: pytorch
      version: "1"
      autoSelect: true
```

If you selected the gRPC API protocol, enter or paste the following YAML code directly in the embedded editor.

```plaintext
apiVersion: serving.kserve.io/v1alpha1
kind: ServingRuntime
metadata:
  name: ibmz-triton-grpc
  labels:
    opendatahub.io/dashboard: "true"
spec:
  containers:
    - name: kserve-container
      command:
        - /bin/sh
        - -c
      args:
        - /opt/tritonserver/bin/tritonserver --model-repository=/mnt/models --grpc-port=8001 --http-port=8000 --metrics-port=8002
      image: icr.io/ibmz/ibmz-accelerated-for-nvidia-triton-inference-server:<version>
      securityContext:
        allowPrivilegeEscalation: false
        capabilities:
          drop:
            - ALL
        runAsNonRoot: true
        seccompProfile:
          type: RuntimeDefault
      resources:
        limits:
          cpu: "2"
          memory: 4Gi
        requests:
          cpu: "2"
          memory: 4Gi
      ports:
        - containerPort: 8001
          name: grpc
          protocol: TCP
      volumeMounts:
        - mountPath: /dev/shm
          name: shm
  protocolVersions:
    - v2
    - grpc-v2
  supportedModelFormats:
    - name: onnx-mlir
      version: "1"
      autoSelect: true
    - name: snapml
      version: "1"
      autoSelect: true
    - name: pytorch
      version: "1"
      autoSelect: true
  volumes:
    - emptyDir: null
      medium: Memory
      sizeLimit: 2Gi
      name: shm
```

Follow these steps to add the NVIDIA Triton Inference Server runtime:

If you selected the REST API protocol, enter or paste the following YAML code directly in the embedded editor.

```plaintext
apiVersion: serving.kserve.io/v1alpha1
kind: ServingRuntime
metadata:
  name: triton-kserve-rest
  labels:
    opendatahub.io/dashboard: "true"
spec:
  annotations:
    prometheus.kserve.io/path: /metrics
    prometheus.kserve.io/port: "8002"
  containers:
    - args:
        - tritonserver
        - --model-store=/mnt/models
        - --grpc-port=9000
        - --http-port=8080
        - --allow-grpc=true
        - --allow-http=true
      image: nvcr.io/nvidia/tritonserver@sha256:xxxxx
      name: kserve-container
      resources:
        limits:
          cpu: "1"
          memory: 2Gi
        requests:
          cpu: "1"
          memory: 2Gi
      ports:
        - containerPort: 8080
          protocol: TCP
  protocolVersions:
    - v2
    - grpc-v2
  supportedModelFormats:
    - autoSelect: true
      name: tensorrt
      version: "8"
    - autoSelect: true
      name: tensorflow
      version: "1"
    - autoSelect: true
      name: tensorflow
      version: "2"
    - autoSelect: true
      name: onnx
      version: "1"
    - name: pytorch
      version: "1"
    - autoSelect: true
      name: triton
      version: "2"
    - autoSelect: true
      name: xgboost
      version: "1"
    - autoSelect: true
      name: python
      version: "1"
```

If you selected the gRPC API protocol, enter or paste the following YAML code directly in the embedded editor.

```plaintext
apiVersion: serving.kserve.io/v1alpha1
kind: ServingRuntime
metadata:
  name: triton-kserve-grpc
  labels:
    opendatahub.io/dashboard: "true"
spec:
  annotations:
    prometheus.kserve.io/path: /metrics
    prometheus.kserve.io/port: "8002"
  containers:
    - args:
        - tritonserver
        - --model-store=/mnt/models
        - --grpc-port=9000
        - --http-port=8080
        - --allow-grpc=true
        - --allow-http=true
      image: nvcr.io/nvidia/tritonserver@sha256:xxxxx
      name: kserve-container
      ports:
        - containerPort: 9000
          name: h2c
          protocol: TCP
      volumeMounts:
        - mountPath: /dev/shm
          name: shm
      resources:
        limits:
          cpu: "1"
          memory: 2Gi
        requests:
          cpu: "1"
          memory: 2Gi
  protocolVersions:
    - v2
    - grpc-v2
  supportedModelFormats:
    - autoSelect: true
      name: tensorrt
      version: "8"
    - autoSelect: true
      name: tensorflow
      version: "1"
    - autoSelect: true
      name: tensorflow
      version: "2"
    - autoSelect: true
      name: onnx
      version: "1"
    - name: pytorch
      version: "1"
    - autoSelect: true
      name: triton
      version: "2"
    - autoSelect: true
      name: xgboost
      version: "1"
    - autoSelect: true
      name: python
      version: "1"
  volumes:
    - name: shm
      emptyDir: null
        medium: Memory
        sizeLimit: 2Gi
```

Follow these steps to add the Seldon MLServer runtime:

If you selected the REST API protocol, enter or paste the following YAML code directly in the embedded editor.

```plaintext
apiVersion: serving.kserve.io/v1alpha1
kind: ServingRuntime
metadata:
  name: mlserver-kserve-rest
  labels:
    opendatahub.io/dashboard: "true"
spec:
  annotations:
    openshift.io/display-name: Seldon MLServer
    prometheus.kserve.io/port: "8080"
    prometheus.kserve.io/path: /metrics
  containers:
    - name: kserve-container
      image: 'docker.io/seldonio/mlserver@sha256:07890828601515d48c0fb73842aaf197cbcf245a5c855c789e890282b15ce390'
      env:
        - name: MLSERVER_HTTP_PORT
          value: "8080"
        - name: MLSERVER_GRPC_PORT
          value: "9000"
        - name: MODELS_DIR
          value: /mnt/models
      resources:
        requests:
          cpu: "1"
          memory: 2Gi
        limits:
          cpu: "1"
          memory: 2Gi
      ports:
        - containerPort: 8080
          protocol: TCP
      securityContext:
        allowPrivilegeEscalation: false
        capabilities:
          drop:
            - ALL
        privileged: false
        runAsNonRoot: true
  protocolVersions:
    - v2
  multiModel: false
  supportedModelFormats:
    - name: sklearn
      version: "0"
      autoSelect: true
      priority: 2
    - name: sklearn
      version: "1"
      autoSelect: true
      priority: 2
    - name: xgboost
      version: "1"
      autoSelect: true
      priority: 2
    - name: xgboost
      version: "2"
      autoSelect: true
      priority: 2
    - name: lightgbm
      version: "3"
      autoSelect: true
      priority: 2
    - name: lightgbm
      version: "4"
      autoSelect: true
      priority: 2
    - name: mlflow
      version: "1"
      autoSelect: true
      priority: 1
    - name: mlflow
      version: "2"
      autoSelect: true
      priority: 1
    - name: catboost
      version: "1"
      autoSelect: true
      priority: 1
    - name: huggingface
      version: "1"
      autoSelect: true
      priority: 1
```

If you selected the gRPC API protocol, enter or paste the following YAML code directly in the embedded editor.

```plaintext
apiVersion: serving.kserve.io/v1alpha1
kind: ServingRuntime
metadata:
  name: mlserver-kserve-grpc
  labels:
    opendatahub.io/dashboard: "true"
spec:
  annotations:
    openshift.io/display-name: Seldon MLServer
    prometheus.kserve.io/port: "8080"
    prometheus.kserve.io/path: /metrics
  containers:
    - name: kserve-container
      image: 'docker.io/seldonio/mlserver@sha256:07890828601515d48c0fb73842aaf197cbcf245a5c855c789e890282b15ce390'
      env:
        - name: MLSERVER_HTTP_PORT
          value: "8080"
        - name: MLSERVER_GRPC_PORT
          value: "9000"
        - name: MODELS_DIR
          value: /mnt/models
      resources:
        requests:
          cpu: "1"
          memory: 2Gi
        limits:
          cpu: "1"
          memory: 2Gi
      ports:
        - containerPort: 9000
          name: h2c
          protocol: TCP
      securityContext:
        allowPrivilegeEscalation: false
        capabilities:
          drop:
            - ALL
        privileged: false
        runAsNonRoot: true
  protocolVersions:
    - v2
  multiModel: false
  supportedModelFormats:
    - name: sklearn
      version: "0"
      autoSelect: true
      priority: 2
    - name: sklearn
      version: "1"
      autoSelect: true
      priority: 2
    - name: xgboost
      version: "1"
      autoSelect: true
      priority: 2
    - name: xgboost
      version: "2"
      autoSelect: true
      priority: 2
    - name: lightgbm
      version: "3"
      autoSelect: true
      priority: 2
    - name: lightgbm
      version: "4"
      autoSelect: true
      priority: 2
    - name: mlflow
      version: "1"
      autoSelect: true
      priority: 1
    - name: mlflow
      version: "2"
      autoSelect: true
      priority: 1
    - name: catboost
      version: "1"
      autoSelect: true
      priority: 1
    - name: huggingface
      version: "1"
      autoSelect: true
      priority: 1
```

Optional: To use a custom display name for the runtime that you are adding, add a`metadata.annotations.openshift.io/display-name` field and specify a value, as shown in the following example:

```plaintext
apiVersion: serving.kserve.io/v1alpha1
kind: ServingRuntime
metadata:
  name: kserve-triton
  annotations:
    openshift.io/display-name: Triton ServingRuntime
```

Note

If you do not configure a custom display name for your runtime, OpenShift AI shows the value of the`metadata.name` field.

Click Create.

The Serving runtimes page opens and shows the updated list of runtimes that are installed. Observe that the runtime that you added is automatically enabled. The API protocol that you specified when creating the runtime is shown.

Verification

- Optional: To edit the runtime, click the action menu (⋮) and select Edit.
- The model-serving runtime that you added is shown in an enabled state on the Serving runtimes page.

## Chapter 3. Configuring model servers on the NVIDIA NIM model serving platformCopy link

You configure and create a model server on the NVIDIA NIM model serving platform when you deploy an NVIDIA-optimized model. During the deployment process, you select a specific NIM from the available list and configure its properties, such as the number of replicas, server size, and the hardware profile.

### 3.1. Enabling the NVIDIA NIM model serving platformCopy link

As an OpenShift AI administrator, you can use the Red Hat OpenShift AI dashboard to enable the NVIDIA NIM model serving platform.

Prerequisites

The`disableNIMModelServing` dashboard configuration option is set to`false`.

For more information about setting dashboard configuration options, see [Customizing the dashboard](https://docs.redhat.com/en/documentation/red_hat_openshift_ai_cloud_service/1/html/managing_resources/customizing-the-dashboard).

- You have logged in to OpenShift AI as a user with OpenShift AI administrator privileges.
- You have enabled the single-model serving platform. You do not need to enable a preinstalled runtime. For more information about enabling the single-model serving platform, see [Enabling the single-model serving platform](https://docs.redhat.com/en/documentation/red_hat_openshift_ai_cloud_service/1/html/configuring_your_model-serving_platform/configuring_model_servers_on_the_single_model_serving_platform#enabling-the-single-model-serving-platform_rhoai-admin).
- You have enabled GPU support in OpenShift AI. This includes installing the Node Feature Discovery Operator and NVIDIA GPU Operator. For more information, see [Installing the Node Feature Discovery Operator](https://docs.redhat.com/en/documentation/openshift_container_platform/4.19/html/specialized_hardware_and_driver_enablement/psap-node-feature-discovery-operator#installing-the-node-feature-discovery-operator_psap-node-feature-discovery-operator) and [Enabling NVIDIA GPUs](https://docs.redhat.com/en/documentation/red_hat_openshift_ai_cloud_service/1/html/managing_openshift_ai/enabling-accelerators#enabling-nvidia-gpus_managing-rhoai).
- You have an NVIDIA Cloud Account (NCA) and can access the NVIDIA GPU Cloud (NGC) portal. For more information, see [NVIDIA GPU Cloud user guide](https://docs.nvidia.com/ngc/gpu-cloud/ngc-user-guide/index.html).
- Your NCA account is associated with the NVIDIA AI Enterprise Viewer role.
- You have generated a personal API key on the NGC portal. For more information, see [Generating a Personal API Key](https://docs.nvidia.com/ngc/gpu-cloud/ngc-user-guide/index.html#generating-personal-api-key).

Procedure

1. In the left menu of the OpenShift AI dashboard, click Applications → Explore.
2. On the Explore page, find the NVIDIA NIM tile.
3. Click Enable on the application tile.
4. Enter your personal API key and then click Submit.

Verification

- The NVIDIA NIM application that you enabled is displayed on the Enabled page.

## Chapter 4. Configuring model servers on the multi-model serving platformCopy link

On the multi-model serving platform, you configure model servers for your data science project before you deploy models. A model server can host multiple models, which share the server’s resources.

### 4.1. Enabling the multi-model serving platformCopy link

To use the multi-model serving platform, you must first enable the platform. The multi-model serving platform uses the ModelMesh component.

Prerequisites

The`spec.dashboardConfig.disableModelMesh` dashboard configuration option is set to`false`(the default).

For more information about setting dashboard configuration options, see [Customizing the dashboard](https://docs.redhat.com/en/documentation/red_hat_openshift_ai_cloud_service/1/html/managing_resources/customizing-the-dashboard).

- You have logged in to OpenShift AI as a user with OpenShift AI administrator privileges.

Procedure

1. In the left menu of the OpenShift AI dashboard, click Settings → Cluster settings.
2. Locate the Model serving platforms section.
3. Select the Multi-model serving platform checkbox.
4. Click Save changes.

### 4.2. Adding a custom model-serving runtime for the multi-model serving platformCopy link

A model-serving runtime adds support for a specified set of model frameworks and the model formats supported by those frameworks. By default, the multi-model serving platform includes the OpenVINO Model Server runtime. You can also add your own custom runtime if the default runtime does not meet your needs, such as supporting a specific model format.

As an administrator, you can use the Red Hat OpenShift AI dashboard to add and enable a custom model-serving runtime. You can then choose the custom runtime when you create a new model server for the multi-model serving platform.

Note

Red Hat does not provide support for custom runtimes. You are responsible for ensuring that you are licensed to use any custom runtimes that you add, and for correctly configuring and maintaining them.

Prerequisites

You have reviewed the example runtimes in the [kserve/modelmesh-serving](https://github.com/kserve/modelmesh-serving/tree/main/config/runtimes) repository. You can use these examples as starting points. However, each runtime requires some further modification before you can deploy it in OpenShift AI. The required modifications are described in the following procedure.

Note

OpenShift AI includes the OpenVINO Model Server runtime by default. You do not need to add this runtime to OpenShift AI.

- You have logged in to OpenShift AI as a user with OpenShift AI administrator privileges.
- You are familiar with how to [add a model server to your project](https://docs.redhat.com/en/documentation/red_hat_openshift_ai_cloud_service/1/html/deploying_models/deploying_models_on_the_multi_model_serving_platform#adding-a-model-server-for-the-multi-model-serving-platform_rhoai-user). When you have added a custom model-serving runtime, you must configure a new model server to use the runtime.

Procedure

From the OpenShift AI dashboard, click Settings → Serving runtimes.

The Serving runtimes page opens and shows the model-serving runtimes that are already installed and enabled.

To add a custom runtime, choose one of the following options:

- To start with an existing runtime (for example the OpenVINO Model Server runtime), click the action menu (⋮) next to the existing runtime and then click Duplicate.
- To add a new custom runtime, click Add serving runtime.

In the Select the model serving platforms this runtime supports list, select Multi-model serving platform.

Note

The multi-model serving platform supports only the REST protocol. Therefore, you cannot change the default value in the Select the API protocol this runtime supports list.

Optional: If you started a new runtime (rather than duplicating an existing one), add your code by choosing one of the following options:

Upload a YAML file

In the file browser, select a YAML file on your computer. This file might be the one of the example runtimes that you downloaded from the [kserve/modelmesh-serving](https://github.com/kserve/modelmesh-serving/tree/main/config/runtimes) repository.

The embedded YAML editor opens and shows the contents of the file that you uploaded.

1. Click Upload files.

Enter YAML code directly in the editor

1. Click Start from scratch.
2. Enter or paste YAML code directly in the embedded editor. The YAML that you paste might be copied from one of the example runtimes in the [kserve/modelmesh-serving](https://github.com/kserve/modelmesh-serving/tree/main/config/runtimes) repository.

Optional: If you are adding one of the example runtimes in the [kserve/modelmesh-serving](https://github.com/kserve/modelmesh-serving/tree/main/config/runtimes) repository, perform the following modifications:

Update the value of the`containers.image` field in the format`newName:newTag`, based on the values that you previously noted in the [kustomization.yaml](https://github.com/kserve/modelmesh-serving/blob/main/config/runtimes/kustomization.yaml) file. Some examples are shown.

Nvidia Triton Inference Server`image: nvcr.io/nvidia/tritonserver:23.04-py3` Seldon Python MLServer`image: seldonio/mlserver:1.3.2` TorchServe`image: pytorch/torchserve:0.7.1-cpu`

1. In the YAML editor, locate the`kind` field for your runtime. Update the value of this field to`ServingRuntime`.
2. In the [kustomization.yaml](https://github.com/kserve/modelmesh-serving/blob/main/config/runtimes/kustomization.yaml) file in the [kserve/modelmesh-serving](https://github.com/kserve/modelmesh-serving/tree/main/config/runtimes) repository, take note of the`newName` and`newTag` values for the runtime that you want to add. You will specify these values in a later step.
3. In the YAML editor for your custom runtime, locate the`containers.image` field.

Optional: To configure a custom display name for the runtime that you are adding, add a`metadata.annotations.openshift.io/display-name` field and specify a value, as shown in the following example:

```plaintext
apiVersion: serving.kserve.io/v1alpha1
kind: ServingRuntime
metadata:
  name: mlserver-0.x
  annotations:
    openshift.io/display-name: MLServer
```

Note

If you do not configure a custom display name for your runtime, OpenShift AI shows the value of the`metadata.name` field.

Click Add.

The Serving runtimes page opens and shows the updated list of runtimes that are installed. Observe that the runtime you added is automatically enabled.

Verification

- Optional: To edit your custom runtime, click the action menu (⋮) and select Edit.
- The custom model-serving runtime that you added is shown in an enabled state on the Serving runtimes page.

### 4.3. Adding a tested and verified model-serving runtime for the multi-model serving platformCopy link

In addition to preinstalled and custom model-serving runtimes, you can also use Red Hat tested and verified model-serving runtimes such as the [NVIDIA Triton Inference Server](https://developer.nvidia.com/triton-inference-server) to support your needs. For more information about Red Hat tested and verified runtimes, see [Tested and verified runtimes for Red Hat OpenShift AI](https://access.redhat.com/articles/7089743).

You can use the Red Hat OpenShift AI dashboard to add and enable the NVIDIA Triton Inference Server runtime and then choose the runtime when you create a new model server for the multi-model serving platform.

Prerequisites

- You have logged in to OpenShift AI as a user with OpenShift AI administrator privileges.
- You are familiar with how to [add a model server to your project](https://docs.redhat.com/en/documentation/red_hat_openshift_ai_cloud_service/1/html/deploying_models/deploying_models_on_the_multi_model_serving_platform#adding-a-model-server-for-the-multi-model-serving-platform_rhoai-user). After you have added a tested and verified model-serving runtime, you must configure a new model server to use the runtime.

Procedure

From the OpenShift AI dashboard, click Settings → Serving runtimes.

The Serving runtimes page opens and shows the model-serving runtimes that are already installed and enabled.

In the Select the model serving platforms this runtime supports list, select Multi-model serving platform.

Note

The multi-model serving platform supports only the REST protocol. Therefore, you cannot change the default value in the Select the API protocol this runtime supports list.

Enter or paste the following YAML code directly in the embedded editor.

```plaintext
apiVersion: serving.kserve.io/v1alpha1
kind: ServingRuntime
metadata:
  annotations:
    enable-route: "true"
  name: modelmesh-triton
  labels:
    opendatahub.io/dashboard: "true"
spec:
  annotations:
    opendatahub.io/modelServingSupport: '["multi"x`x`]'
    prometheus.kserve.io/path: /metrics
    prometheus.kserve.io/port: "8002"
  builtInAdapter:
    env:
      - name: CONTAINER_MEM_REQ_BYTES
        value: "268435456"
      - name: USE_EMBEDDED_PULLER
        value: "true"
    memBufferBytes: 134217728
    modelLoadingTimeoutMillis: 90000
    runtimeManagementPort: 8001
    serverType: triton
  containers:
    - args:
        - -c
        - 'mkdir -p /models/_triton_models;  chmod 777
          /models/_triton_models;  exec
          tritonserver "--model-repository=/models/_triton_models" "--model-control-mode=explicit" "--strict-model-config=false" "--strict-readiness=false" "--allow-http=true" "--allow-grpc=true"  '
      command:
        - /bin/sh
      image: nvcr.io/nvidia/tritonserver@sha256:xxxxx
      name: triton
      resources:
        limits:
          cpu: "1"
          memory: 2Gi
        requests:
          cpu: "1"
          memory: 2Gi
  grpcDataEndpoint: port:8001
  grpcEndpoint: port:8085
  multiModel: true
  protocolVersions:
    - grpc-v2
    - v2
  supportedModelFormats:
    - autoSelect: true
      name: onnx
      version: "1"
    - autoSelect: true
      name: pytorch
      version: "1"
    - autoSelect: true
      name: tensorflow
      version: "1"
    - autoSelect: true
      name: tensorflow
      version: "2"
    - autoSelect: true
      name: tensorrt
      version: "7"
    - autoSelect: false
      name: xgboost
      version: "1"
    - autoSelect: true
      name: python
      version: "1"
```

Optional: To use a custom display name for the runtime that you are adding, add a`metadata.annotations.openshift.io/display-name` field and specify a value, as shown in the following example:

```plaintext
apiVersion: serving.kserve.io/v1alpha1
kind: ServingRuntime
metadata:
  name: modelmesh-triton
  annotations:
    openshift.io/display-name: Triton ServingRuntime
```

Note

If you do not configure a custom display name for your runtime, OpenShift AI shows the value of the`metadata.name` field.

Click Create.

The Serving runtimes page opens and shows the updated list of runtimes that are installed. Observe that the runtime you added is automatically enabled.

1. To add a tested and verified runtime, click Add serving runtime.
2. Click Start from scratch.
3. In the`metadata.name` field, make sure that the value of the runtime you are adding does not match a runtime that you have already added).
4. Optional: To edit the runtime, click the action menu (⋮) and select Edit.

Verification

- The model-serving runtime that you added is shown in an enabled state on the Serving runtimes page.

## Chapter 5. Customizing model deploymentsCopy link

You can customize a model’s deployment on the single-model serving platform to suit your specific needs, for example, to deploy a particular family of models or to enhance an existing deployment. You can modify the runtime configuration for a specific deployment by setting additional serving runtime arguments and environment variables.

These customizations apply only to the selected model deployment and do not change the default runtime configuration. You can set these parameters when you first deploy a model or by editing an existing deployment.

### 5.1. Customizing the parameters of a deployed model-serving runtimeCopy link

You might need additional parameters beyond the default ones to deploy specific models or to enhance an existing model deployment. In such cases, you can modify the parameters of an existing runtime to suit your deployment needs.

Note

Customizing the parameters of a runtime only affects the selected model deployment.

Prerequisites

- You have logged in to OpenShift AI as a user with OpenShift AI administrator privileges.
- You have deployed a model on the single-model serving platform.

Procedure

From the OpenShift AI dashboard, click Models → Model deployments.

The Model deployments page opens.

Click the action menu (⋮) and select Edit.

The Configuration parameters section shows predefined serving runtime parameters, if any are available.

Customize the runtime parameters in the Configuration parameters section:

Modify the values in Additional environment variables to define variables in the model’s environment.

Note

Do not modify the port or model serving runtime arguments, because they require specific values to be set. Overwriting these parameters can cause the deployment to fail.

1. Modify the values in Additional serving runtime arguments to define how the deployed model behaves.

Verification

Confirm that the arguments and variables that you set appear in`spec.predictor.model.args` and`spec.predictor.model.env` by one of the following methods:

Using the following command in the OpenShift CLI:

```plaintext
oc get -o json inferenceservice <inferenceservicename/modelname> -n <projectname>
```

- Checking the InferenceService YAML from the OpenShift Console.

### 5.2. Customizable model serving runtime parametersCopy link

You can modify the parameters of an existing model serving runtime to suit your deployment needs.

For more information about parameters for each of the supported serving runtimes, see the following table:

Expand

Caikit Text Generation Inference Server (Caikit-TGIS) ServingRuntime for KServe

[Caikit NLP: Configuration](https://github.com/opendatahub-io/caikit-nlp?tab=readme-ov-file#configuration) [TGIS: Model configuration](https://github.com/IBM/text-generation-inference?tab=readme-ov-file#model-configuration)

Caikit Standalone ServingRuntime for KServe

[Caikit NLP: Configuration](https://github.com/opendatahub-io/caikit-nlp?tab=readme-ov-file#configuration)

NVIDIA Triton Inference Server

[NVIDIA Triton Inference Server: Model Parameters](https://docs.nvidia.com/deeplearning/triton-inference-server/user-guide/docs/tensorrtllm_backend/docs/model_config.html?#model-configuration)

OpenVINO Model Server

[OpenVINO Model Server Features: Dynamic Input Parameters](https://docs.openvino.ai/2024/openvino-workflow/model-server/ovms_docs_dynamic_input.html)

Seldon MLServer

[MLServer Documentation: Model Settings](https://mlserver.readthedocs.io/en/stable/reference/model-settings.html)

[Deprecated] Text Generation Inference Server (TGIS) Standalone ServingRuntime for KServe

[TGIS: Model configuration](https://github.com/IBM/text-generation-inference?tab=readme-ov-file#model-configuration)

vLLM NVIDIA GPU ServingRuntime for KServe

[vLLM: Engine Arguments](https://docs.vllm.ai/en/stable/serving/engine_args.html) [OpenAI-Compatible Server](https://docs.vllm.ai/en/stable/serving/openai_compatible_server.html)

vLLM AMD GPU ServingRuntime for KServe

[vLLM: Engine Arguments](https://docs.vllm.ai/en/stable/serving/engine_args.html) [OpenAI-Compatible Server](https://docs.vllm.ai/en/stable/serving/openai_compatible_server.html)

vLLM Intel Gaudi Accelerator ServingRuntime for KServe

[vLLM: Engine Arguments](https://docs.vllm.ai/en/stable/serving/engine_args.html) [OpenAI-Compatible Server](https://docs.vllm.ai/en/stable/serving/openai_compatible_server.html)

| | Serving runtime | Resource |
| --- | --- | --- |

Show more

### 5.3. Customizing the vLLM model-serving runtimeCopy link

In certain cases, you may need to add additional flags or environment variables to the vLLM ServingRuntime for KServe runtime to deploy a family of LLMs.

The following procedure describes customizing the vLLM model-serving runtime to deploy a Llama, Granite or Mistral model.

Prerequisites

- You have logged in to OpenShift AI as a user with OpenShift AI administrator privileges.
- For Llama model deployment, you have downloaded a meta-llama-3 model to your object storage.
- For Granite model deployment, you have downloaded a granite-7b-instruct or granite-20B-code-instruct model to your object storage.
- For Mistral model deployment, you have downloaded a mistral-7B-Instruct-v0.3 model to your object storage.
- You have enabled the vLLM ServingRuntime for KServe runtime.
- You have enabled GPU support in OpenShift AI and have installed and configured the Node Feature Discovery Operator on your cluster. For more information, see [Installing the Node Feature Discovery Operator](https://docs.redhat.com/en/documentation/openshift_container_platform/4.19/html/specialized_hardware_and_driver_enablement/psap-node-feature-discovery-operator#installing-the-node-feature-discovery-operator_psap-node-feature-discovery-operator) and [Enabling NVIDIA GPUs](https://docs.redhat.com/en/documentation/red_hat_openshift_ai_cloud_service/1/html/managing_openshift_ai/enabling-accelerators#enabling-nvidia-gpus_managing-rhoai)

Procedure

If you are deploying a meta-llama-3 model, add the following arguments under Additional serving runtime arguments in the Configuration parameters section:

```plaintext
–-distributed-executor-backend=mp 
```

1

```plaintext

--max-model-len=6144 
```

2

1

Sets the backend to multiprocessing for distributed model workers

2

Sets the maximum context length of the model to 6144 tokens

If you are deploying a granite-7B-instruct model, add the following arguments under Additional serving runtime arguments in the Configuration parameters section:

```plaintext
--distributed-executor-backend=mp 
```

1

1

Sets the backend to multiprocessing for distributed model workers

If you are deploying a granite-20B-code-instruct model, add the following arguments under Additional serving runtime arguments in the Configuration parameters section:

```plaintext
--distributed-executor-backend=mp 
```

1

```plaintext

–-tensor-parallel-size=4 
```

2

```plaintext

--max-model-len=6448 
```

3

1

Sets the backend to multiprocessing for distributed model workers

2

Distributes inference across 4 GPUs in a single node

3

Sets the maximum context length of the model to 6448 tokens

If you are deploying a mistral-7B-Instruct-v0.3 model, add the following arguments under Additional serving runtime arguments in the Configuration parameters section:

```plaintext
--distributed-executor-backend=mp 
```

1

```plaintext

--max-model-len=15344 
```

2

1

Sets the backend to multiprocessing for distributed model workers

2

Sets the maximum context length of the model to 15344 tokens

1. Follow the steps to deploy a model as described in [Deploying models on the single-model serving platform](https://docs.redhat.com/en/documentation/red_hat_openshift_ai_cloud_service/1/html/deploying_models/deploying_models_on_the_single_model_serving_platform#deploying-models-on-the-single-model-serving-platform_rhoai-user).
2. In the Serving runtime field, select vLLM ServingRuntime for KServe.
3. Click Deploy.

Verification

For granite models, use the following example command to verify API requests to your deployed model:

```plaintext
curl -q -X 'POST' \
    "https://<inference_endpoint_url>:443/v1/chat/completions" \
    -H 'accept: application/json' \
    -H 'Content-Type: application/json' \
    -d "{
    \"model\": \"<model_name>\",
    \"prompt\": \"<prompt>",
    \"max_tokens\": <max_tokens>,
    \"temperature\": <temperature>
    }"
```

- Confirm that the deployed model is shown on the Models tab for the project, and on the Model deployments page of the dashboard with a checkmark in the Status column.

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



## Working with Llama Stack

Working with Llama Stack | Red Hat OpenShift AI Cloud Service | 1 | Red Hat Documentation

 

 

 

 

 

 

 Updates for Red Hat OpenShift AI Cloud Service are only provided up to the end of October 2025. 

 

# Working with Llama Stack

---

Red Hat OpenShift AI Cloud Service 1

## Working with Llama Stack in Red Hat OpenShift AI Cloud Service

Legal Notice

Abstract

As a cluster administrator, you can use the Llama Stack Operator in Red Hat OpenShift AI.

---

## Chapter 1. Overview of Llama StackCopy link

Llama Stack is a unified AI runtime environment designed to simplify the deployment and management of generative AI workloads on OpenShift AI. Llama Stack integrates LLM inference servers, vector databases, and retrieval services in a single stack, optimized for Retrieval-Augmented Generation (RAG) and agent-based AI workflows. In OpenShift, the Llama Stack Operator manages the deployment lifecycle of these components, ensuring scalability, consistency, and integration with OpenShift AI projects.

Important

Llama Stack integration is currently available in Red Hat OpenShift AI as a Technology Preview feature. Technology Preview features are not supported with Red Hat production service level agreements (SLAs) and might not be functionally complete. Red Hat does not recommend using them in production. These features provide early access to upcoming product features, enabling customers to test functionality and provide feedback during the development process.

For more information about the support scope of Red Hat Technology Preview features, see [Technology Preview Features Support Scope](https://access.redhat.com/support/offerings/techpreview/).

Llama Stack includes the following components:

- Legal Notice
- Inference model servers such as vLLM, designed to efficiently serve large language models.
- Vector storage solutions, primarily Milvus, to store embeddings generated from your domain data.
- Retrieval and embedding management workflows using integrated tools, such as Docling, to handle continuous data ingestion and synchronization.
- Integration with OpenShift AI by using the`LlamaStackDistribution` custom resource, simplifying configuration and deployment.

For information about how to deploy Llama Stack in OpenShift AI, see [Deploying a RAG stack in a data science project](https://docs.redhat.com/en/documentation/red_hat_openshift_ai_cloud_service/1/html/working_with_llama_stack/deploying-a-rag-stack-in-a-data-science-project_rag).

### 1.1. The LlamaStackDistribution custom resource API providersCopy link

The`LlamaStackDistribution` custom resource includes various API types and providers that you can use in OpenShift AI. The following table displays the supported providers that are included in the distribution:

Expand

Agents

`inline::meta-reference`

Enabled by default

Technology Preview

DatasetIO

`inline::localfs`

Enabled by default

Technology Preview

`remote::huggingface`

Enabled by default

Technology Preview

Evaluation

`remote::trustyai_lmeval`

Set the`EMBEDDING_MODEL` environment variable

Technology Preview

Files

`inline::localfs`

Enabled by default

Technology Preview

Inference

`inline::sentence-transformers`

Enabled by default

Technology Preview

`remote::vllm`

Set the`VLLM_URL` environment variable

Technology Preview

`remote::azure`

Set the`AZURE_API_KEY` environment variable

Technology Preview

`remote::bedrock`

Set the`AWS_ACCESS_KEY_ID` environment variable

Technology Preview

`remote::openai`

Set the`OPENAI_API_KEY` environment variable

Technology Preview

`remote::vertexai`

Set the`VERTEX_AI_PROJECT` environment variable

Technology Preview

`remote::watsonx`

Set the`WATSONX_API_KEY` environment variable

Technology Preview

Safety

`remote::trustyai_fms`

Enabled by default

Technology Preview

Scoring

`inline::llm-as-a-judge`

Enabled by default

Technology Preview

`inline::basic`

Enabled by default

Technology Preview

`inline::braintrust`

Enabled by default

Technology Preview

Telemetry

`inline::meta-reference`

Enabled by default

Technology Preview

Tool_runtime

`inline::rag-runtime`

Enabled by default

Technology Preview

`remote::brave-search`

Enabled by default

Technology Preview

`remote::tavily-search`

Enabled by default

Technology Preview

`remote::model-context-protocol`

Enabled by default

Technology Preview

VectorIO

`inline::milvus`

Enabled by default

Technology Preview

`remote::milvus`

Set the`MILVUS_ENDPOINT` environment variable

Technology Preview

| | API type | Providers | How to Enable | Support status |
| --- | --- | --- | --- | --- |

Show more

### 1.2. OpenAI compatibility for RAG APIs in Llama StackCopy link

OpenShift AI supports OpenAI-compatible request and response schemas for Llama Stack RAG workflows. You can use OpenAI clients and schemas for files, vector stores, and Responses API file search end-to-end.

OpenAI compatibility enables the following capabilities:

- You can use OpenAI SDKs and tools with Llama Stack by setting the client`base_url` to the Llama Stack OpenAI path,`/v1/openai/v1`.
- You can manage files and vector stores by using OpenAI-compatible endpoints. You can then invoke RAG workflows by using the Responses API with the`file_search` tool.

### 1.3. OpenAI-compatible APIs in Llama StackCopy link

OpenShift AI includes a Llama Stack component that exposes OpenAI-compatible APIs. These APIs enable you to reuse existing OpenAI SDKs, tools, and workflows directly within your OpenShift environment, without changing your client code. This compatibility layer supports retrieval-augmented generation (RAG), inference, and embedding workloads by using the same endpoints, schemas, and authentication model as OpenAI.

This compatibility layer has the following capabilities:

- Standardized endpoints: REST API paths align with OpenAI specifications.
- Schema parity: Request and response fields follow OpenAI data structures.

Note

When connecting OpenAI SDKs or third-party tools to OpenShift AI, you must update the client configuration to use your deployment’s Llama Stack route as the`base_url`. This ensures that API calls are sent to the OpenAI-compatible endpoints that run inside your OpenShift cluster, rather than to the public OpenAI service.

#### 1.3.1. Supported OpenAI-compatible APIs in OpenShift AICopy link

##### 1.3.1.1. Chat Completions APICopy link

- Endpoint:`/v1/openai/v1/chat/completions`.
- Providers: All inference back ends deployed through OpenShift AI.
- Support level: Technology Preview.

The Chat Completions API enables conversational, message-based interactions with models served by Llama Stack in OpenShift AI.

##### 1.3.1.2. Completions APICopy link

- Endpoint:`/v1/openai/v1/completions`.
- Providers: All inference backends managed by OpenShift AI.
- Support level: Technology Preview.

The Completions API supports single-turn text generation and prompt completion.

##### 1.3.1.3. Embeddings APICopy link

- Endpoint:`/v1/openai/v1/embeddings`.
- Providers: All embedding models enabled in OpenShift AI.

The Embeddings API generates numerical embeddings for text or documents that can be used in downstream semantic search or RAG applications.

##### 1.3.1.4. Files APICopy link

- Endpoint:`/v1/openai/v1/files`.
- Providers: File system-based file storage provider for managing files and documents stored locally in your cluster.
- Support level: Technology Preview.

The Files API manages file uploads for use in embedding and retrieval workflows.

##### 1.3.1.5. Vector Stores APICopy link

- Endpoint:`/v1/openai/v1/vector_stores/`.
- Providers: Inline and Remote Milvus configured in OpenShift AI.
- Support level: Technology Preview.

The Vector Stores API manages the creation, configuration, and lifecycle of vector store resources in Llama Stack. Through this API, you can create new vector stores, list existing ones, delete unused stores, and query their metadata, all using OpenAI-compatible request and response formats.

##### 1.3.1.6. Vector Store Files APICopy link

- Endpoint:`/v1/openai/v1/vector_stores/{vector_store_id}/files`.
- Providers: Local inline provider configured for file storage and retrieval.
- Support level: Developer Preview.

The Vector Store Files API implements the OpenAI Vector Store Files interface and manages the link between document files and Milvus vector stores used for RAG.

##### 1.3.1.7. Models APICopy link

- Endpoint:`/v1/openai/v1/models`.
- Providers: All model-serving back ends configured within OpenShift AI.
- Support level: Technology Preview.

The Models API lists and retrieves available model resources from the Llama Stack deployment running on OpenShift AI. By using the Models API, you can enumerate models, view their capabilities, and verify deployment status through a standardized OpenAI-compatible interface.

##### 1.3.1.8. Responses APICopy link

- Endpoint:`/v1/openai/v1/responses`.
- Providers: All inference and retrieval providers configured in OpenShift AI.
- Support level: Developer Preview.

The Responses API generates model outputs by combining inference, file search, and tool-calling capabilities through a single OpenAI-compatible endpoint. It is particularly useful for retrieval-augmented generation (RAG) workflows that rely on the`file_search` tool to retrieve context from vector stores.

Note

The Responses API is an experimental feature that is still under active development in OpenShift AI. While the API is already functional and suitable for evaluation, some endpoints and parameters remain under implementation and might change in future releases. This API is provided for testing and feedback purposes only and is not recommended for production use.

## Chapter 2. Activating the Llama Stack OperatorCopy link

You can activate the Llama Stack Operator on your OpenShift cluster by setting its`managementState` to`Managed` in the OpenShift AI Operator`DataScienceCluster` custom resource (CR). This setting enables Llama-based model serving without reinstalling or directly editing Operator subscriptions. You can edit the CR in the OpenShift web console or by using the OpenShift CLI (`oc`).

Note

As an alternative to following the steps in this procedure, you can activate the Llama Stack Operator from the OpenShift CLI (`oc`) by running the following command:

```plaintext
$ oc patch datasciencecluster <name> --type=merge -p {"spec":{"components":{"llamastackoperator":{"managementState":"Managed"}}}}
```

Replace with your`DataScienceCluster` name, for example,`default-dsc`.

Prerequisites

You have installed the OpenShift CLI (`oc`) as described in the appropriate documentation for your cluster:

- [Installing the OpenShift CLI](https://docs.redhat.com/en/documentation/openshift_dedicated/4/html/cli_tools/openshift-cli-oc#installing-openshift-cli) for OpenShift Dedicated
- [Installing the OpenShift CLI](https://docs.redhat.com/en/documentation/red_hat_openshift_service_on_aws_classic_architecture/4/html/cli_tools/openshift-cli-oc#installing-openshift-cli) for Red Hat OpenShift Service on AWS (classic architecture)

Procedure

Click the YAML tab.

An embedded YAML editor opens, displaying the configuration for the`DataScienceCluster` custom resource.

In the YAML editor, locate the`spec.components` section. If the`llamastackoperator` field does not exist, add it. Then, set the`managementState` field to`Managed`:

```yaml
spec:
  components:
    llamastackoperator:
      managementState: Managed
```

1. You have created a`ClusterPolicy` resource instance with default values on your cluster, as described in [Creating the ClusterPolicy instance](https://docs.nvidia.com/datacenter/cloud-native/openshift/latest/install-gpu-ocp.html#create-the-clusterpolicy-instance) in the NVIDIA documentation.
2. Log in to the OpenShift web console as a cluster administrator.
3. In the Administrator perspective, click Operators → Installed Operators.
4. Click the Red Hat OpenShift AI Operator to open its details.
5. Click the Data Science Cluster tab.
6. On the DataScienceClusters page, click the`default-dsc` object.
7. Click Save to apply your changes.

Verification

After you activate the Llama Stack Operator, verify that it is running in your cluster:

1. In the OpenShift web console, click Workloads → Pods.
2. From the Project list, select the`redhat-ods-applications` namespace.
3. Confirm that a pod with the label`app.kubernetes.io/name=llama-stack-operator` is displayed and has a status of Running.

## Chapter 3. Deploying a RAG stack in a data science projectCopy link

Important

This feature is currently available in Red Hat OpenShift AI as a Technology Preview feature. Technology Preview features are not supported with Red Hat production service level agreements (SLAs) and might not be functionally complete. Red Hat does not recommend using them in production. These features provide early access to upcoming product features, enabling customers to test functionality and provide feedback during the development process.

For more information about the support scope of Red Hat Technology Preview features, see [Technology Preview Features Support Scope](https://access.redhat.com/support/offerings/techpreview/).

As an OpenShift cluster administrator, you can deploy a Retrieval-Augmented Generation (RAG) stack in OpenShift AI. This stack provides the infrastructure, including LLM inference, vector storage, and retrieval services that data scientists and AI engineers use to build conversational workflows in their projects.

To deploy the RAG stack in a data science project, complete the following tasks:

- Activate the Llama Stack Operator in OpenShift AI.
- Enable GPU support on the OpenShift cluster. This task includes installing the required NVIDIA Operators.
- Deploy an inference model, for example, the llama-3.2-3b-instruct model. This task includes creating a storage connection and configuring GPU allocation.
- Create a`LlamaStackDistribution` instance to enable RAG functionality. This action deploys LlamaStack alongside a Milvus vector store and connects both components to the inference model.
- Ingest domain data into Milvus by running Docling in a data science pipeline or Jupyter notebook. This process keeps the embeddings synchronized with the source data.
- Expose and secure the model endpoints.

### 3.1. Overview of RAGCopy link

Retrieval-augmented generation (RAG) in OpenShift AI enhances large language models (LLMs) by integrating domain-specific data sources directly into the model’s context. Domain-specific data sources can be structured data, such as relational database tables, or unstructured data, such as PDF documents.

RAG indexes content and builds an embedding store that data scientists and AI engineers can query. When data scientists or AI engineers pose a question to a RAG chatbot, the RAG pipeline retrieves the most relevant pieces of data, passes them to the LLM as context, and generates a response that reflects both the prompt and the retrieved content.

By implementing RAG, data scientists and AI engineers can obtain tailored, accurate, and verifiable answers to complex queries based on their own datasets within a data science project.

#### 3.1.1. Audience for RAGCopy link

The target audience for RAG is practitioners who build data-grounded conversational AI applications using OpenShift AI infrastructure.

For Data Scientists Data scientists can use RAG to prototype and validate models that answer natural-language queries against data sources without managing low-level embedding pipelines or vector stores. They can focus on creating prompts and evaluating model outputs instead of building retrieval infrastructure. For MLOps Engineers MLOps engineers typically deploy and operate RAG pipelines in production. Within OpenShift AI, they manage LLM endpoints, monitor performance, and ensure that both retrieval and generation scale reliably. RAG decouples vector store maintenance from the serving layer, enabling MLOps engineers to apply CI/CD workflows to data ingestion and model deployment alike. For Data Engineers Data engineers build workflows to load data into storage that OpenShift AI indexes. They keep embeddings in sync with source systems, such as S3 buckets or relational tables to ensure that chatbot responses are accurate. For AI Engineers AI engineers architect RAG chatbots by defining prompt templates, retrieval methods, and fallback logic. They configure agents and add domain-specific tools, such as OpenShift job triggers, enabling rapid iteration.

### 3.2. Overview of vector databasesCopy link

Vector databases are a crucial component of retrieval-augmented generation (RAG) in OpenShift AI. They store and index vector embeddings that represent the semantic meaning of text or other data. When you integrate vector databases with Llama Stack in OpenShift AI, you can build RAG applications that combine large language models (LLMs) with relevant, domain-specific knowledge.

Vector databases provide you with the following capabilities:

- Store vector embeddings generated by embedding models.
- Support efficient similarity search to retrieve semantically related content.
- Enable RAG workflows by supplying the LLM with contextually relevant data from a specific domain.

When you deploy RAG workloads in OpenShift AI, you can deploy vector databases through the Llama Stack Operator. Currently, OpenShift AI supports the following vector databases:

- Inline Milvus Lite An Inline Milvus vector database runs embedded within the Llama Stack Distribution (LSD) pod and is suitable for lightweight experimentation and small-scale development. Inline Milvus stores data in a local SQLite database and is limited in scale and persistence.
- Remote Milvus A remote Milvus vector database runs as a standalone service in your project namespace or as an external managed deployment. Remote Milvus is recommended for production-grade RAG use cases because it provides persistence, scalability, and isolation from the Llama Stack Distribution (LSD) pod. In OpenShift environments, you must deploy Milvus with an etcd service directly in your project. For more information on using etcd services, see [Providing redundancy with etcd](https://docs.redhat.com/en/documentation/openshift_container_platform/4.19/html/etcd/index).

Consider the following points when you decide on the vector database to use for your RAG workloads:

- Use inline Milvus Lite if you want to experiment quickly with RAG in a self-contained setup and do not require persistence across pod restarts.
- Use remote Milvus if you need reliable storage, high availability, and the ability to scale out RAG workloads in your OpenShift AI environment.

### 3.3. Overview of Milvus vector databasesCopy link

Milvus is an open source vector database designed for high-performance similarity search across embedding data. In OpenShift AI, Milvus is supported as a remote vector database provider for the Llama Stack Operator. Milvus enables retrieval-augmented generation (RAG) workloads that require persistence, scalability, and efficient search across large document collections.

Milvus vector databases provide you with the following capabilities in OpenShift AI:

- Similarity search using Approximate Nearest Neighbor (ANN) algorithms.
- Persistent storage support for vectors.
- Indexing and query optimizations for embedding-based search.
- Integration with external metadata and APIs.

In OpenShift AI, you can use Milvus vector databases in the following operational modes:

- Inline Milvus Lite, which runs embedded in the Llama Stack Distribution pod for testing or small-scale experiments.
- Remote Milvus, which runs as a standalone service in your OpenShift project or as an external managed Milvus service. Remote Milvus is recommended for production workloads.

When you deploy a remote Milvus vector database, you must run the following components in your OpenShift project:

- Secret (`milvus-secret`): Stores sensitive data such as the Milvus root password.
- PersistentVolumeClaim (`milvus-pvc`): Provides persistent storage for Milvus data.
- Deployment (`etcd-deployment`): Runs an etcd instance that Milvus uses for metadata storage and service coordination.
- Service (`etcd-service`): Exposes the etcd port for Milvus to connect to.
- Deployment (`milvus-standalone`): Runs Milvus in standalone mode and connects it to the etcd service and PVC.
- Service (`milvus-service`): Exposes Milvus gRPC (19530) and HTTP (9091 health check) ports for client access.

Milvus requires an etcd service to manage metadata such as collections, indexes, and partitions, and to provide service discovery and coordination among Milvus components. Even when running in standalone mode, Milvus depends on etcd to operate correctly and maintain metadata consistency. For more information on using etcd services, see [Providing redundancy with etcd](https://docs.redhat.com/en/documentation/openshift_container_platform/4.19/html/etcd/index).

Important

Do not use the OpenShift control plane etcd for Milvus. You must deploy a separate etcd instance inside your project or connect to an external etcd service.

Use Remote Milvus when you require a persistent, scalable, and production-ready vector database that integrates seamlessly with OpenShift AI. Consider choosing a remote Milvus vector database if your deployment must cater for the following requirements:

- Persistent vector storage across restarts or upgrades.
- Scalable indexing and high-performance vector search.
- A production-grade RAG architecture integrated with OpenShift AI.

### 3.4. Deploying a Llama model with KServeCopy link

To use Llama Stack and retrieval-augmented generation (RAG) workloads in OpenShift AI, you must deploy a Llama model with a vLLM model server and configure KServe in KServe RawDeployment mode.

Prerequisites

You have installed the OpenShift CLI (`oc`) as described in the appropriate documentation for your cluster:

- [Installing the OpenShift CLI](https://docs.redhat.com/en/documentation/openshift_dedicated/4/html/cli_tools/openshift-cli-oc#installing-openshift-cli) for OpenShift Dedicated
- [Installing the OpenShift CLI](https://docs.redhat.com/en/documentation/red_hat_openshift_service_on_aws_classic_architecture/4/html/cli_tools/openshift-cli-oc#installing-openshift-cli) for Red Hat OpenShift Service on AWS (classic architecture)

Procedure

These steps are only supported in OpenShift AI versions 2.19 and later.

Click the Deploy model button.

The Deploy model dialog opens.

Configure the deployment properties for your model:

In the Model server size field, select`Custom` from the drop-down list.

- Set CPUs requested to`1 core`.
- Set Memory requested to`10 GiB`.
- Set CPU limit to`2 core`.
- Set Memory limit to`14 GiB`.
- Set Accelerator to`NVIDIA GPUs`.
- Set Accelerator count to`1`.

In the Additional serving runtime arguments field, specify the following recommended arguments:

```plaintext
--dtype=half
--max-model-len=20000
--gpu-memory-utilization=0.95
--enable-chunked-prefill
--enable-auto-tool-choice
--tool-call-parser=llama3_json
--chat-template=/app/data/template/tool_chat_template_llama3.2_json.jinja
```

Click Deploy.

Note

Model deployment can take several minutes, especially for the first model that is deployed on the cluster. Initial deployment may take more than 10 minutes while the relevant images download.

Verification

Verify that the`kserve-controller-manager` and`odh-model-controller` pods are running:

Copy the Log in with this token command and paste it in the OpenShift CLI (`oc`).

```plaintext
$ oc login --token=<token> --server=<openshift_cluster_url>
```

Enter the following command to verify that the`kserve-controller-manager` and`odh-model-controller` pods are running:

```shell-session
$ oc get pods -n redhat-ods-applications | grep -E 'kserve-controller-manager|odh-model-controller'
```

Confirm that you see output similar to the following example:

```plaintext
kserve-controller-manager-7c865c9c9f-xyz12   1/1     Running   0          4m21s
odh-model-controller-7b7d5fd9cc-wxy34        1/1     Running   0          3m55s
```

If you do not see either of the`kserve-controller-manager` and`odh-model-controller` pods, there could be a problem with your deployment. In addition, if the pods appear in the list, but their`Status` is not set to`Running`, check the pod logs for errors:

```shell-session
$ oc logs <pod-name> -n redhat-ods-applications
```

Check the status of the inference service:

```shell-session
$ oc get inferenceservice -n llamastack
$ oc get pods -n <data science project name> | grep llama
```

The deployment automatically creates the following resources:

- A`ServingRuntime` resource.
- An`InferenceService` resource, a`Deployment`, a pod, and a service pointing to the pod.

Verify that the server is running. For example:

```shell-session
$ oc logs llama-32-3b-instruct-predictor-77f6574f76-8nl4r  -n <data science project name>
```

Check for output similar to the following example log:

```plaintext
INFO     2025-05-15 11:23:52,750 __main__:498 server: Listening on ['::', '0.0.0.0']:8321
INFO:     Started server process [1]
INFO:     Waiting for application startup.
INFO     2025-05-15 11:23:52,765 __main__:151 server: Starting up
INFO:     Application startup complete.
INFO:     Uvicorn running on http://['::', '0.0.0.0']:8321 (Press CTRL+C to quit)
```

If you see a`ConvertTritonGPUToLLVM` error in the pod logs when querying the`/v1/chat/completions` API, and the vLLM server restarts or returns a`500 Internal Server` error, apply the following workaround:

Before deploying the model, remove the`--enable-chunked-prefill` argument from the Additional serving runtime arguments field in the deployment dialog.

The error is displayed similar to the following:

```plaintext
/opt/vllm/lib64/python3.12/site-packages/vllm/attention/ops/prefix_prefill.py:36:0: error: Failures have been detected while processing an MLIR pass pipeline
/opt/vllm/lib64/python3.12/site-packages/vllm/attention/ops/prefix_prefill.py:36:0: note: Pipeline failed while executing [`ConvertTritonGPUToLLVM` on 'builtin.module' operation]: reproducer generated at `std::errs, please share the reproducer above with Triton project.`
INFO:     10.129.2.8:0 - "POST /v1/chat/completions HTTP/1.1" 500 Internal Server Error
```

### 3.5. Testing your vLLM model endpointsCopy link

To verify that your deployed Llama 3.2 model is accessible externally, ensure that your vLLM model server is exposed as a network endpoint. You can then test access to the model from outside both the OpenShift cluster and the OpenShift AI interface.

Important

If you selected Make deployed models available through an external route during deployment, your vLLM model endpoint is already accessible outside the cluster. You do not need to manually expose the model server. Manually exposing vLLM model endpoints, for example, by using`oc expose`, creates an unsecured route unless you configure authentication. Avoid exposing endpoints without security controls to prevent unauthorized access.

Prerequisites

You have installed the OpenShift CLI (`oc`) as described in the appropriate documentation for your cluster:

- [Installing the OpenShift CLI](https://docs.redhat.com/en/documentation/openshift_dedicated/4/html/cli_tools/openshift-cli-oc#installing-openshift-cli) for OpenShift Dedicated
- [Installing the OpenShift CLI](https://docs.redhat.com/en/documentation/red_hat_openshift_service_on_aws_classic_architecture/4/html/cli_tools/openshift-cli-oc#installing-openshift-cli) for Red Hat OpenShift Service on AWS (classic architecture)

Procedure

Open a new terminal window.

Copy the Log in with this token command and paste it in the OpenShift CLI (`oc`).

```plaintext
$ oc login --token=<token> --server=<openshift_cluster_url>
```

1. Log in to your OpenShift cluster from the CLI:
2. In the upper-right corner of the OpenShift web console, click your user name and select Copy login command.
3. After you have logged in, click Display token.

If you enabled Require token authentication during model deployment, retrieve your token:

```plaintext
$ export MODEL_TOKEN=$(oc get secret default-name-llama-32-3b-instruct-sa -n <project name> --template={{ .data.token }} | base64 -d)
```

Obtain your model endpoint URL:

In addition, if you did not enable Require token authentication during model deployment, you can also enter the following command to retrieve the endpoint URL:

```plaintext
$ export MODEL_ENDPOINT="https://$(oc get route llama-32-3b-instruct -n <project name> --template={{ .spec.host }})"
```

- If you enabled Make deployed models available through an external route during model deployment, click Endpoint details on the Model deployments page in the OpenShift AI dashboard to obtain your model endpoint URL.

Test the endpoint with a sample chat completion request:

If you did not enable Require token authentication during model deployment, enter a chat completion request. For example:

```plaintext
$ curl -X POST $MODEL_ENDPOINT/v1/chat/completions \
 -H "Content-Type: application/json" \
 -d '{
 "model": "llama-32-3b-instruct",
 "messages": [
   {
     "role": "user",
     "content": "Hello"
   }
 ]
}'
```

If you enabled Require token authentication during model deployment, include a token in your request. For example:

```plaintext
curl -s -k $MODEL_ENDPOINT/v1/chat/completions \
--header "Authorization: Bearer $MODEL_TOKEN" \
--header 'Content-Type: application/json' \
-d '{
  "model": "llama-32-3b-instruct",
  "messages": [
    {
      "role": "user",
      "content": "can you tell me a funny joke?"
    }
  ]
}' | jq .
```

Note

The`-k` flag disables SSL verification and should only be used in test environments or with self-signed certificates.

Verification

Confirm that you received a JSON response containing a chat completion. For example:

```plaintext
{
  "id": "chatcmpl-05d24b91b08a4b78b0e084d4cc91dd7e",
  "object": "chat.completion",
  "created": 1747279170,
  "model": "llama-32-3b-instruct",
  "choices": [{
    "index": 0,
    "message": {
      "role": "assistant",
      "reasoning_content": null,
      "content": "Hello! It's nice to meet you. Is there something I can help you with or would you like to chat?",
      "tool_calls": []
    },
    "logprobs": null,
    "finish_reason": "stop",
    "stop_reason": null
  }],
  "usage": {
    "prompt_tokens": 37,
    "total_tokens": 62,
    "completion_tokens": 25,
    "prompt_tokens_details": null
  },
  "prompt_logprobs": null
}
```

If you do not receive a response similar to the example, verify that the endpoint URL and token are correct, and ensure your model deployment is running.

### 3.6. Deploying a remote Milvus vector databaseCopy link

To use Milvus as a remote vector database provider for Llama Stack in OpenShift AI, you must deploy Milvus and its required etcd service in your OpenShift project. This procedure shows how to deploy Milvus in standalone mode without the Milvus Operator.

Note

The following example configuration is intended for testing or evaluation environments. For production-grade deployments, see [https://milvus.io/docs](https://milvus.io/docs) in the Milvus documentation.

Prerequisites

- You have installed OpenShift 4.17 or newer.
- You have enabled GPU support in OpenShift AI. This includes installing the Node Feature Discovery operator and NVIDIA GPU Operators. For more information, see [Installing the Node Feature Discovery operator](https://docs.redhat.com/en/documentation/openshift_container_platform/4.19/html/specialized_hardware_and_driver_enablement/psap-node-feature-discovery-operator#installing-the-node-feature-discovery-operator_psap-node-feature-discovery-operator) and [Enabling NVIDIA GPUs](https://docs.redhat.com/en/documentation/red_hat_openshift_ai_cloud_service/1/html/managing_openshift_ai/enabling-accelerators#enabling-nvidia-gpus_managing-rhoai).
- You have cluster administrator privileges for your OpenShift cluster.
- You are logged in to Red Hat OpenShift AI.
- You have a StorageClass available that can provision persistent volumes.
- You created a root password to secure your Milvus service.
- You have deployed an inference model with vLLM, for example, the llama-3.2-3b-instruct model, and you have selected Make deployed models available through an external route and Require token authentication during model deployment.
- You have the correct inference model identifier, for example, llama-3-2-3b.
- You have the model endpoint URL, ending with`/v1`, such as`https://llama-32-3b-instruct-predictor:8443/v1`.
- You have the API token required to access the model endpoint.
- You have installed the OpenShift command line interface (`oc`) as described in [Installing the OpenShift CLI (OpenShift Dedicated)](https://docs.redhat.com/en/documentation/openshift_dedicated/4/html/cli_tools/openshift-cli-oc#installing-openshift-cli) or [Installing the OpenShift CLI (Red Hat OpenShift Service on AWS)](https://docs.redhat.com/en/documentation/red_hat_openshift_service_on_aws/4/html/cli_tools/openshift-cli-oc#installing-openshift-cli).

Procedure

In the Import YAML editor, paste the following manifest and click Create:

```yaml
apiVersion: v1
kind: Secret
metadata:
  name: milvus-secret
type: Opaque
stringData:
  root-password: "MyStr0ngP@ssw0rd"
---
kind: PersistentVolumeClaim
apiVersion: v1
metadata:
  name: milvus-pvc
spec:
  accessModes:
    - ReadWriteOnce
  resources:
    requests:
      storage: 20Gi
  volumeMode: Filesystem
---
apiVersion: apps/v1
kind: Deployment
metadata:
  name: etcd-deployment
  labels:
    app: etcd
spec:
  replicas: 1
  selector:
    matchLabels:
      app: etcd
  strategy:
    type: Recreate
  template:
    metadata:
      labels:
        app: etcd
    spec:
      containers:
        - name: etcd
          image: quay.io/coreos/etcd:v3.5.5
          command:
            - etcd
            - --advertise-client-urls=http://127.0.0.1:2379
            - --listen-client-urls=http://0.0.0.0:2379
            - --data-dir=/etcd
          ports:
            - containerPort: 2379
          volumeMounts:
            - name: etcd-data
              mountPath: /etcd
          env:
            - name: ETCD_AUTO_COMPACTION_MODE
              value: revision
            - name: ETCD_AUTO_COMPACTION_RETENTION
              value: "1000"
            - name: ETCD_QUOTA_BACKEND_BYTES
              value: "4294967296"
            - name: ETCD_SNAPSHOT_COUNT
              value: "50000"
      volumes:
        - name: etcd-data
          emptyDir: {}
      restartPolicy: Always
---
apiVersion: v1
kind: Service
metadata:
  name: etcd-service
spec:
  ports:
    - port: 2379
      targetPort: 2379
  selector:
    app: etcd
---
apiVersion: apps/v1
kind: Deployment
metadata:
  labels:
    app: milvus-standalone
  name: milvus-standalone
spec:
  replicas: 1
  selector:
    matchLabels:
      app: milvus-standalone
  strategy:
    type: Recreate
  template:
    metadata:
      labels:
        app: milvus-standalone
    spec:
      containers:
        - name: milvus-standalone
          image: milvusdb/milvus:v2.6.0
          args: ["milvus", "run", "standalone"]
          env:
            - name: DEPLOY_MODE
              value: standalone
            - name: ETCD_ENDPOINTS
              value: etcd-service:2379
            - name: COMMON_STORAGETYPE
              value: local
            - name: MILVUS_ROOT_PASSWORD
              valueFrom:
                secretKeyRef:
                  name: milvus-secret
                  key: root-password
          livenessProbe:
            exec:
              command: ["curl", "-f", "http://localhost:9091/healthz"]
            initialDelaySeconds: 90
            periodSeconds: 30
            timeoutSeconds: 20
            failureThreshold: 5
          ports:
            - containerPort: 19530
              protocol: TCP
            - containerPort: 9091
              protocol: TCP
          volumeMounts:
            - name: milvus-data
              mountPath: /var/lib/milvus
      restartPolicy: Always
      volumes:
        - name: milvus-data
          persistentVolumeClaim:
            claimName: milvus-pvc
---
apiVersion: v1
kind: Service
metadata:
  name: milvus-service
spec:
  selector:
    app: milvus-standalone
  ports:
    - name: grpc
      port: 19530
      targetPort: 19530
    - name: http
      port: 9091
      targetPort: 9091
```

Note

- Use the gRPC port (`19530`) for the`MILVUS_ENDPOINT` setting in Llama Stack.
- The HTTP port (`9091`) is reserved for health checks.
- If you deploy Milvus in a different namespace, use the fully qualified service name in your Llama Stack configuration. For example:`http://milvus-service..svc.cluster.local:19530`

Verification

Verify that Milvus reports a healthy startup with output similar to:

```plaintext
Milvus Standalone is ready to serve ...
Listening on 0.0.0.0:19530 (gRPC)
```

(Optional) Click Pods → milvus-standalone → Terminal and run the following health check:

```shell-session
curl http://localhost:9091/healthz
```

A response of`{"status": "healthy"}` confirms that Milvus is running correctly.

1. In the OpenShift web console, click Workloads → Deployments.
2. Verify that both`etcd-deployment` and`milvus-standalone` show a status of 1 of 1 pods available.
3. Click Pods in the navigation panel and confirm that pods for both deployments are Running.
4. Click the`milvus-standalone` pod name, then select the Logs tab.
5. Click Networking → Services and confirm that the`milvus-service` and`etcd-service` resources exist and are exposed on ports`19530` and`2379`, respectively.

### 3.7. Deploying a LlamaStackDistribution instanceCopy link

You can deploy Llama Stack with retrieval-augmented generation (RAG) by pairing it with a vLLM-served Llama 3.2 model. This module provides two deployment examples of the`LlamaStackDistribution` custom resource (CR): one configured for Inline Milvus (single-node, embedded) and one for Remote Milvus (external Milvus service). When you create the CR, specify`rh-dev` in the`spec.server.distribution.name` field.

Prerequisites

You have installed the OpenShift CLI (`oc`) as described in the appropriate documentation for your cluster:

- [Installing the OpenShift CLI](https://docs.redhat.com/en/documentation/openshift_dedicated/4/html/cli_tools/openshift-cli-oc#installing-openshift-cli) for OpenShift Dedicated
- [Installing the OpenShift CLI](https://docs.redhat.com/en/documentation/red_hat_openshift_service_on_aws_classic_architecture/4/html/cli_tools/openshift-cli-oc#installing-openshift-cli) for Red Hat OpenShift Service on AWS (classic architecture)

Procedure

Open a new terminal window and log in to your OpenShift cluster from the CLI:

In the upper-right corner of the OpenShift web console, click your user name and select Copy login command. After you have logged in, click Display token. Copy the Log in with this token command and paste it in the OpenShift CLI (`oc`).

```plaintext
$ oc login --token=<token> --server=<openshift_cluster_url>
```

Create a secret that contains the inference model environment variables:

```shell-session
export INFERENCE_MODEL="llama-3-2-3b"
export VLLM_URL="https://llama-32-3b-instruct-predictor:8443/v1"
export VLLM_TLS_VERIFY="false"   # Use "true" in production
export VLLM_API_TOKEN="<token identifier>"

oc create secret generic llama-stack-inference-model-secret \
  --from-literal=INFERENCE_MODEL="$INFERENCE_MODEL" \
  --from-literal=VLLM_URL="$VLLM_URL" \
  --from-literal=VLLM_TLS_VERIFY="$VLLM_TLS_VERIFY" \
  --from-literal=VLLM_API_TOKEN="$VLLM_API_TOKEN"
```

1. Choose one of the following deployment examples:

#### 3.7.1. Example A: LlamaStackDistribution with Inline MilvusCopy link

Use this example for development or small datasets where an embedded, single-node Milvus is sufficient. No`MILVUS_*` connection variables are required.

In the OpenShift web console, select Administrator → Quick Create () → Import YAML, and create a CR similar to the following:

```yaml
apiVersion: llamastack.io/v1alpha1
kind: LlamaStackDistribution
metadata:
  name: lsd-llama-milvus-inline
spec:
  replicas: 1
  server:
    containerSpec:
      resources:
        requests:
          cpu: "250m"
          memory: "500Mi"
        limits:
          cpu: 4
          memory: "12Gi"
      env:
        - name: INFERENCE_MODEL
          valueFrom:
            secretKeyRef:
              name: llama-stack-inference-model-secret
              key: INFERENCE_MODEL
        - name: VLLM_MAX_TOKENS
          value: "4096"
        - name: VLLM_URL
          valueFrom:
            secretKeyRef:
              name: llama-stack-inference-model-secret
              key: VLLM_URL
        - name: VLLM_TLS_VERIFY
          valueFrom:
            secretKeyRef:
              name: llama-stack-inference-model-secret
              key: VLLM_TLS_VERIFY
        - name: VLLM_API_TOKEN
          valueFrom:
            secretKeyRef:
              name: llama-stack-inference-model-secret
              key: VLLM_API_TOKEN
      name: llama-stack
      port: 8321
    distribution:
      name: rh-dev
```

Note

The`rh-dev` value is an internal image reference. When you create the`LlamaStackDistribution` custom resource, the OpenShift AI Operator automatically resolves`rh-dev` to the container image in the appropriate registry. This internal image reference allows the underlying image to update without requiring changes to your custom resource.

#### 3.7.2. Example B: LlamaStackDistribution with Remote MilvusCopy link

Use this example for production-grade or large datasets with an external Milvus service. This configuration reads both`MILVUS_ENDPOINT` and`MILVUS_TOKEN` from a dedicated secret.

Create the Milvus connection secret:

```shell-session
# Required: gRPC endpoint on port 19530
export MILVUS_ENDPOINT="tcp://milvus-service:19530"
export MILVUS_TOKEN="<milvus-root-or-user-token>"
export MILVUS_CONSISTENCY_LEVEL="Bounded"   # Optional; choose per your deployment

oc create secret generic milvus-secret \
  --from-literal=MILVUS_ENDPOINT="$MILVUS_ENDPOINT" \
  --from-literal=MILVUS_TOKEN="$MILVUS_TOKEN" \
  --from-literal=MILVUS_CONSISTENCY_LEVEL="$MILVUS_CONSISTENCY_LEVEL"
```

Important

Use the gRPC port`19530` for`MILVUS_ENDPOINT`. Ports such as`9091` are typically used for health checks and are not valid for client traffic.

In the OpenShift web console, select Administrator → Quick Create () → Import YAML, and create a CR similar to the following:

```yaml
apiVersion: llamastack.io/v1alpha1
kind: LlamaStackDistribution
metadata:
  name: lsd-llama-milvus-remote
spec:
  replicas: 1
  server:
    containerSpec:
      resources:
        requests:
          cpu: "250m"
          memory: "500Mi"
        limits:
          cpu: 4
          memory: "12Gi"
      env:
        - name: INFERENCE_MODEL
          valueFrom:
            secretKeyRef:
              name: llama-stack-inference-model-secret
              key: INFERENCE_MODEL
        - name: VLLM_MAX_TOKENS
          value: "4096"
        - name: VLLM_URL
          valueFrom:
            secretKeyRef:
              name: llama-stack-inference-model-secret
              key: VLLM_URL
        - name: VLLM_TLS_VERIFY
          valueFrom:
            secretKeyRef:
              name: llama-stack-inference-model-secret
              key: VLLM_TLS_VERIFY
        - name: VLLM_API_TOKEN
          valueFrom:
            secretKeyRef:
              name: llama-stack-inference-model-secret
              key: VLLM_API_TOKEN
        # --- Remote Milvus configuration from secret ---
        - name: MILVUS_ENDPOINT
          valueFrom:
            secretKeyRef:
              name: milvus-secret
              key: MILVUS_ENDPOINT
        - name: MILVUS_TOKEN
          valueFrom:
            secretKeyRef:
              name: milvus-secret
              key: MILVUS_TOKEN
        - name: MILVUS_CONSISTENCY_LEVEL
          valueFrom:
            secretKeyRef:
              name: milvus-secret
              key: MILVUS_CONSISTENCY_LEVEL
      name: llama-stack
      port: 8321
    distribution:
      name: rh-dev
```

1. Click Create.

Verification

To verify that the Llama Stack server is running, click the pod name and select the Logs tab. Look for output similar to the following:

```plaintext
INFO     2025-05-15 11:23:52,750 __main__:498 server: Listening on ['::', '0.0.0.0']:8321
INFO:     Started server process [1]
INFO:     Waiting for application startup.
INFO     2025-05-15 11:23:52,765 __main__:151 server: Starting up
INFO:     Application startup complete.
INFO:     Uvicorn running on http://['::', '0.0.0.0']:8321 (Press CTRL+C to quit)
```

- In the left-hand navigation, click Workloads → Pods and verify that the Llama Stack pod is running in the correct namespace.
- Confirm that a Service resource for the Llama Stack backend is present in your namespace and points to the running pod: Networking → Services.

Tip

If you switch from Inline Milvus to Remote Milvus, delete the existing pod to ensure the new environment variables and backing store are picked up cleanly.

### 3.8. Ingesting content into a Llama modelCopy link

You can quickly customize and prototype your retrievable content by ingesting raw text into your model from inside a Jupyter notebook. This approach voids requiring a separate ingestion pipeline. By using the LlamaStack SDK, you can embed and store text in your vector store in real-time, enabling immediate RAG workflows.

Prerequisites

- You have installed OpenShift 4.17 or newer.
- You have deployed a Llama 3.2 model with a vLLM model server and you have integrated LlamaStack.
- You have created a project workbench within a data science project.
- You have opened a Jupyter notebook and it is running in your workbench environment.
- You have installed the`llama_stack_client` version 0.2.22 or later in your workbench environment.
- You have a vector database identifier, or you plan to create or register one in this procedure.

Procedure

In a new notebook cell, install the`llama_stack_client` package and its dependencies:

```plaintext
%pip install llama_stack_client fire
```

In a new notebook cell, import RAGDocument and LlamaStackClient:

```plaintext
from llama_stack_client import RAGDocument, LlamaStackClient
```

In a new notebook cell, assign your deployment endpoint to the`base_url` parameter to create a LlamaStackClient instance:

```plaintext
client = LlamaStackClient(base_url="<your deployment endpoint>")
```

List the available models:

```plaintext
# Fetch all registered models
models = client.models.list()
```

Verify that the list of registered models includes your Llama model and an embedding model. Here is an example of a list of registered models:

```plaintext
[Model(identifier='llama-32-3b-instruct', metadata={}, api_model_type='llm', provider_id='vllm-inference', provider_resource_id='llama-32-3b-instruct', type='model', model_type='llm'),
 Model(identifier='ibm-granite/granite-embedding-125m-english', metadata={'embedding_dimension': 768.0}, api_model_type='embedding', provider_id='sentence-transformers', provider_resource_id='ibm-granite/granite-embedding-125m-english', type='model', model_type='embedding')]
```

Select the first LLM and the first embedding model:

```plaintext
model_id = next(m.identifier for m in models if m.model_type == "llm")

embedding_model = next(m for m in models if m.model_type == "embedding")
embedding_model_id = embedding_model.identifier
embedding_dimension = int(embedding_model.metadata["embedding_dimension"])
```

(Optional) Register a vector database (choose one). Skip if you already have a vector DB ID.

Example 3.1. Option 1: Inline Milvus Lite (embedded)

```plaintext
vector_db_id = "my_inline_db"

vector_store = client.vector_stores.create(
    name=vector_db_id,
    embedding_model=embedding_model_id,
    embedding_dimension=embedding_dimension,
    provider_id="milvus",   # inline Milvus Lite
)
print(f"Registered inline Milvus Lite DB: {vector_db_id}")
```

Note

Use inline Milvus Lite for development and small datasets. Persistence and scale are limited compared to remote Milvus.

Example 3.2. Option 2: Remote Milvus (recommended for production)

```plaintext
vector_db_id = "my_remote_db"

vector_store = client.vector_stores.create(
    name=vector_db_id,
    embedding_model=embedding_model_id,
    embedding_dimension=embedding_dimension,
    provider_id="milvus-remote",  # remote Milvus provider (v2.25+)
)
print(f"Registered remote Milvus DB: {vector_db_id}")
```

- Ensure your`LlamaStackDistribution` sets`MILVUS_ENDPOINT`(gRPC`:19530`) and`MILVUS_TOKEN`.
- Aside from the`provider_id`, ingestion and query APIs are identical for inline and remote Milvus.

 

If you already have a vector database, set its identifier:

```plaintext
# If a DB already exists, set it here instead of registering above
# Example:
# vector_db_id = "<your existing vector database ID>"
```

In a new notebook cell, define the raw text that you want to ingest into the vector store:

```plaintext
# Example raw text passage
raw_text = """
LlamaStack can embed raw text into a vector store for retrieval.
This example ingests a small passage for demonstration.
"""
```

In a new notebook cell, create a RAGDocument object to contain the raw text:

```plaintext
document = RAGDocument(
    document_id="raw_text_001",
    content=raw_text,
    mime_type="text/plain",
    metadata={"source": "example_passage"},
)
```

In a new notebook cell, ingest the raw text:

```plaintext
client.tool_runtime.rag_tool.insert(
    documents=[document],
    vector_db_id=vector_db_id,
    chunk_size_in_tokens=100,
)
print("Raw text ingested successfully")
```

In a new notebook cell, create a RAGDocument from an HTML source and ingest it into the vector store:

```plaintext
source = "https://www.paulgraham.com/greatwork.html"
print("rag_tool> Ingesting document:", source)

document = RAGDocument(
    document_id="document_1",
    content=source,
    mime_type="text/html",
    metadata={},
)
```

In a new notebook cell, ingest the content into the vector store:

```plaintext
client.tool_runtime.rag_tool.insert(
    documents=[document],
    vector_db_id=vector_db_id,
    chunk_size_in_tokens=50,
)
print("Raw text ingested successfully")
```

Verification

- Review the output to confirm successful ingestion. A typical response after ingestion includes the number of text chunks inserted and any warnings or errors.
- The model list returned by`client.models.list()` includes your Llama 3.2 model and an embedding model.

### 3.9. Querying ingested content in a Llama modelCopy link

You can use the LlamaStack SDK in your Jupyter notebook to query ingested content by running retrieval-augmented generation (RAG) queries on raw text or HTML sources stored in your vector database. When you query the ingested content, you can perform one-off lookups or start multi-turn conversational flows without setting up a separate retrieval service.

Prerequisites

- You have installed OpenShift 4.17 or newer.
- You have enabled GPU support in OpenShift AI. This includes installing the Node Feature Discovery operator and NVIDIA GPU Operators. For more information, see [Installing the Node Feature Discovery operator](https://docs.redhat.com/en/documentation/openshift_container_platform/4.19/html/specialized_hardware_and_driver_enablement/psap-node-feature-discovery-operator#installing-the-node-feature-discovery-operator_psap-node-feature-discovery-operator) and [Enabling NVIDIA GPUs](https://docs.redhat.com/en/documentation/red_hat_openshift_ai_cloud_service/1/html/managing_openshift_ai/enabling-accelerators#enabling-nvidia-gpus_managing-rhoai).
- If you are using GPU acceleration, you have at least one NVIDIA GPU available.
- You have logged in to OpenShift web console.
- You have activated the Llama Stack Operator in OpenShift AI.
- You have deployed an inference model, for example, the llama-3.2-3b-instruct model.
- You have configured a Llama Stack deployment by creating a`LlamaStackDistribution` instance to enable RAG functionality.
- You have created a project workbench within a data science project.
- You have opened a Jupyter notebook and it is running in your workbench environment.
- You have installed the`llama_stack_client` version 0.2.14 or later in your workbench environment.
- You have ingested content into your model.

Note

This procedure does not require any specific type of content. It only requires that you have already ingested some text, HTML, or document data into your vector database, and that this content is available for retrieval. If you have previously ingested content, that content will be available to query. If you have not ingested any content yet, the queries in this procedure will return empty results or errors.

Procedure

In a new notebook cell, install the`llama_stack` client package:

```plaintext
%pip install llama_stack_client
```

In a new notebook cell, import`Agent`,`AgentEventLogger`, and`LlamaStackClient`:

```plaintext
from llama_stack_client import Agent, AgentEventLogger, LlamaStackClient
```

In a new notebook cell, assign your deployment endpoint to the`base_url` parameter to create a`LlamaStackClient` instance. For example:

```plaintext
client = LlamaStackClient(base_url="http://lsd-llama-milvus-service:8321/")
```

In a new notebook cell, list the available models:

```plaintext
models = client.models.list()
```

Verify that the list of registered models includes your Llama model and an embedding model. Here is an example of a list of registered models:

```plaintext
[Model(identifier='llama-32-3b-instruct', metadata={}, api_model_type='llm', provider_id='vllm-inference', provider_resource_id='llama-32-3b-instruct', type='model', model_type='llm'),
 Model(identifier='ibm-granite/granite-embedding-125m-english', metadata={'embedding_dimension': 768.0}, api_model_type='embedding', provider_id='sentence-transformers', provider_resource_id='ibm-granite/granite-embedding-125m-english', type='model', model_type='embedding')]
```

Select the first LLM:

```plaintext
model_id = next(m.identifier for m in models if m.model_type == "llm")
```

If you have not already created a vector store, select an embedding model for registration in the next step:

```plaintext
embedding = next(m for m in models if m.model_type == "embedding")
embedding_model_id = embedding.identifier
embedding_dimension = int(embedding.metadata["embedding_dimension"])
```

If you do not already have a vector store ID, register a vector store of your choice:

Example 3.3. Option 1: Inline Milvus Lite (embedded)

```plaintext
vector_db_id = "my_inline_db"

vector_store = client.vector_stores.create(
    name=vector_db_id,
    embedding_model=embedding_model_id,
    embedding_dimension=embedding_dimension,
    provider_id="milvus",   # inline Milvus Lite
)
print(f"Registered inline Milvus Lite DB: {vector_db_id}")
```

Note

Use inline Milvus Lite for development and small datasets. Persistence and scale are limited compared to remote Milvus.

Example 3.4. Option 2: Remote Milvus (recommended for production)

```plaintext
vector_db_id = "my_remote_db"

vector_store = client.vector_stores.create(
    name=vector_db_id,
    embedding_model=embedding_model_id,
    embedding_dimension=embedding_dimension,
    provider_id="milvus-remote",  # remote Milvus provider (v2.25+)
)
print(f"Registered remote Milvus DB: {vector_db_id}")
```

- Ensure your`LlamaStackDistribution` sets`MILVUS_ENDPOINT`(gRPC`:19530`) and`MILVUS_TOKEN`.
- Aside from the`provide_id`, querying APIs are identical for inline and remote Milvus.

 

If you already have a vector database, set its identifier:

```plaintext
# If a DB already exists, set it here instead of registering above
# Example:
# vector_db_id = "<your existing vector database ID>"
```

In a new notebook cell, query the ingested content using the low-level RAG tool:

```plaintext
# Example RAG query for one-off lookups
query = "What benefits do the ingested passages provide for retrieval?"
result = client.tool_runtime.rag_tool.query(
    vector_db_ids=[vector_db_id],
    content=query,
)
print("Low-level query result:", result)
```

In a new notebook cell, query the ingested content by using the high-level Agent API:

```plaintext
# Create an Agent for conversational RAG queries
agent = Agent(
    client,
    model=model_id,
    instructions="You are a helpful assistant.",
    tools=[
        {
            "name": "builtin::rag/knowledge_search",
            "args": {"vector_db_ids": [vector_db_id]},
        }
    ],
)

prompt = "How do you do great work?"
print("Prompt>", prompt)

# Create a session and run a streaming turn
session_id = agent.create_session("rag_session")
response = agent.create_turn(
    messages=[{"role": "user", "content": prompt}],
    session_id=session_id,
    stream=True,
)

# Log and print the agent's response
for log in AgentEventLogger().log(response):
    log.print()
```

Verification

- The notebook prints query results for both the low-level RAG tool and the high-level Agent API.
- No errors appear in the output, confirming the model can retrieve and respond to ingested content.

### 3.10. Preparing documents with Docling for Llama Stack retrievalCopy link

You can transform your source documents with a Docling-enabled data science pipeline and ingest the output into a Llama Stack vector store by using the Llama Stack SDK. This modular approach separates document preparation from ingestion, yet still delivers an end-to-end, retrieval-augmented generation (RAG) workflow.

The pipeline registers a Milvus vector database and downloads the source PDFs, then splits them for parallel processing and converts each batch to Markdown with Docling. It generates sentence-transformer embeddings from the Markdown and stores them in the vector store, making the documents instantly searchable in Llama Stack.

Prerequisites

- You have installed OpenShift 4.17 or newer.
- You have enabled GPU support in OpenShift AI. This includes installing the Node Feature Discovery operator and NVIDIA GPU Operators. For more information, see [Installing the Node Feature Discovery operator](https://docs.redhat.com/en/documentation/openshift_container_platform/4.19/html/specialized_hardware_and_driver_enablement/psap-node-feature-discovery-operator#installing-the-node-feature-discovery-operator_psap-node-feature-discovery-operator) and [Enabling NVIDIA GPUs](https://docs.redhat.com/en/documentation/red_hat_openshift_ai_cloud_service/1/html/managing_openshift_ai/enabling-accelerators#enabling-nvidia-gpus_managing-rhoai).
- You have logged in to OpenShift web console.
- You have a data science project and access to pipelines in the OpenShift AI dashboard.
- You have created and configured a pipeline server within the data science project that contains your workbench.
- You have activated the Llama Stack Operator in OpenShift AI.
- You have deployed an inference model, for example, the llama-3.2-3b-instruct model.
- You have configured a Llama Stack deployment by creating a`LlamaStackDistribution` instance to enable RAG functionality.
- You have created a project workbench within a data science project.
- You have opened a Jupyter notebook and it is running in your workbench environment.
- You have installed the`llama_stack_client` version 0.2.14 or later in your workbench environment.
- You have installed local object storage buckets and created connections, as described in [Adding a connection to your data science project](https://docs.redhat.com/en/documentation/red_hat_openshift_ai_cloud_service/1/html/working_on_data_science_projects/using-connections_projects#adding-a-connection-to-your-data-science-project_projects).
- You have compiled to YAML a data science pipeline that includes a Docling transform, either one of the RAG demo samples or your own custom pipeline.
- Your data science project quota allows between 500 millicores (0.5 CPU) and 4 CPU cores for the pipeline run.
- Your data science project quota allows from 2 GiB up to 6 GiB of RAM for the pipeline run.
- If you are using GPU acceleration, you have at least one NVIDIA GPU available.

Procedure

In a new notebook cell, install the`llama_stack` client package:

```plaintext
%pip install llama_stack_client
```

In a new notebook cell, import Agent, AgentEventLogger, and LlamaStackClient:

```plaintext
from llama_stack_client import Agent, AgentEventLogger, LlamaStackClient
```

In a new notebook cell, assign your deployment endpoint to the`base_url` parameter to create a LlamaStackClient instance:

```plaintext
client = LlamaStackClient(base_url="<your deployment endpoint>")
```

List the available models:

```plaintext
models = client.models.list()
```

Select the first LLM and the first embedding model:

```plaintext
model_id = next(m.identifier for m in models if m.model_type == "llm")
embedding_model = next(m for m in models if m.model_type == "embedding")
embedding_model_id = embedding_model.identifier
embedding_dimension = embedding_model.metadata["embedding_dimension"]
```

In a new notebook cell, register a vector database (choose one option):

Example 3.5. Option 1: Inline Milvus Lite (embedded)

```plaintext
vector_db_id = "my_inline_db"

vector_store = client.vector_stores.create(
    name=vector_db_id,
    embedding_model=embedding_model_id,
    embedding_dimension=embedding_dimension,
    provider_id="milvus",   # inline Milvus Lite
)
print(f"Registered inline Milvus Lite DB: {vector_db_id}")
```

Note

Inline Milvus Lite is best for development. Data durability and scale are limited compared to remote Milvus.

Example 3.6. Option 2: Remote Milvus (recommended for production)

```plaintext
vector_db_id = "my_remote_db"

vector_store = client.vector_stores.create(
    name=vector_db_id,
    embedding_model=embedding_model_id,
    embedding_dimension=embedding_dimension,
    provider_id="milvus-remote",  # remote Milvus provider (v2.25+)
)
print(f"Registered remote Milvus DB: {vector_db_id}")
```

- Ensure your`LlamaStackDistribution` includes`MILVUS_ENDPOINT` and`MILVUS_TOKEN`.
- Aside from the`provider_id`, ingestion and query APIs are identical between inline and remote Milvus.

 

+

Important

If you are using the sample Docling pipeline from the RAG demo repository, the pipeline registers the database automatically and you can skip this step. However, if you are using your own pipeline, you must register the database yourself.

Create a pipeline run to execute your Docling pipeline, as described in [Executing a pipeline run](https://docs.redhat.com/en/documentation/red_hat_openshift_ai_cloud_service/1/html/working_with_data_science_pipelines/managing-data-science-pipelines_ds-pipelines#executing-a-pipeline-run_ds-pipelines). The pipeline run inserts your PDF documents into the vector database. If you run the Docling pipeline from the [RAG demo samples repository](https://github.com/opendatahub-io/rag/tree/main/demos/kfp/docling/pdf-conversion), you can optionally customize the following parameters before starting the pipeline run:

- `base_url`: The base URL to fetch PDF files from.
- `pdf_filenames`: A comma-separated list of PDF filenames to download and convert.
- `num_workers`: The number of parallel workers.
- `vector_db_id`: The Milvus vector database ID.
- `service_url`: The Milvus service URL.
- `embed_model_id`: The embedding model to use.
- `max_tokens`: The maximum tokens for each chunk.
- `use_gpu`: Enable or disable GPU acceleration.

Verification

In your Jupyter notebook, query the LLM with a question that relates to the ingested content. For example:

```plaintext
from llama_stack_client import Agent, AgentEventLogger
import uuid

rag_agent = Agent(
    client,
    model=model_id,
    instructions="You are a helpful assistant",
    tools=[
        {
            "name": "builtin::rag/knowledge_search",
            "args": {"vector_db_ids": [vector_db_id]},
        }
    ],
)

prompt = "What can you tell me about the birth of word processing?"
print("prompt>", prompt)

session_id = rag_agent.create_session(session_name=f"s{uuid.uuid4().hex}")

response = rag_agent.create_turn(
    messages=[{"role": "user", "content": prompt}],
    session_id=session_id,
    stream=True,
)

for log in AgentEventLogger().log(response):
    log.print()
```

Query chunks from the vector database:

```plaintext
query_result = client.vector_io.query(
    vector_db_id=vector_db_id,
    query="what do you know about?",
)
print(query_result)
```

### 3.11. About Llama stack search typesCopy link

Llama Stack supports keyword, vector, and hybrid search modes for retrieving context in retrieval-augmented generation (RAG) workloads. Each mode offers different tradeoffs in precision, recall, semantic depth, and computational cost.

#### 3.11.1. Supported search modesCopy link

##### 3.11.1.1. Keyword searchCopy link

Keyword search applies lexical matching techniques, such as TF-IDF or BM25, to locate documents that contain exact or near-exact query terms. This approach is effective when precise term-matching is critical and remains widely used in information-retrieval systems. For more information, see [The Probabilistic Relevance Framework: BM25 and Beyond](https://www.researchgate.net/publication/220613776_The_Probabilistic_Relevance_Framework_BM25_and_Beyond).

##### 3.11.1.2. Vector searchCopy link

Vector search encodes documents and queries as dense numerical vectors, known as embeddings, and measures similarity with metrics such as cosine similarity or inner product. This approach captures contextual meaning and supports semantic matching beyond exact word overlap. For more information, see [Billion-scale similarity search with GPUs](https://ieeexplore.ieee.org/document/8733051).

##### 3.11.1.3. Hybrid searchCopy link

Hybrid search blends keyword and vector techniques, typically by combining individual scores with a weighted sum or methods, such as Reciprocal Rank Fusion (RRF). This approach returns results that balance exact matches with semantic relevance. For more information, see [Sparse, Dense, and Hybrid Retrieval for Answer Ranking](https://arxiv.org/html/2410.20381v1).

#### 3.11.2. Retrieval database supportCopy link

Milvus is the supported retrieval database for Llama Stack. It currently provides vector search. However, keyword and hybrid search capabilities are not currently supported.

## Chapter 4. Benchmarking embedding models with BEIR datasets and Llama StackCopy link

This procedure explains how to set up, run, and verify embedding-model benchmarks by using the Llama Stack framework. Embedding models are neural networks that convert text or other data into dense numerical vectors, called embeddings, which capture semantic meaning. In retrieval-augmented generation (RAG) systems, embeddings enable semantic search so that the system retrieves the documents most relevant to a query.

Selecting an embedding model depends on several factors, such as the content type, accuracy requirements, performance needs, and model license. The`beir_benchmarks.py` script compares the retrieval accuracy of embedding models by using standardized information-retrieval benchmarks from the BEIR framework. The script is included in the [RAG](https://github.com/opendatahub-io/rag) repository, which provides demonstrations, benchmarking scripts, and deployment guides for the RAG Stack on OpenShift.

The examples use the`sentence-transformers` inference provider, which you can replace with another provider if required.

Prerequisites

- You have cloned the`https://github.com/opendatahub-io/rag` repository.
- You have changed into the`/rag/benchmarks/beir-benchmarks` directory.
- You have initialized and activated a virtual environment.
- You have defined and installed the relevant script package dependencies to a`requirements.txt` file.
- You have built the Llama Stack starter distribution to install all dependencies.
- You have verified that your vector database is accessible and configured in the`run.yaml` file, and that any required embedding models were preloaded or registered with Llama Stack.

Note

The default supported embedding models are`granite-embedding-30m` and`granite-embedding-125m`, served by the`sentence-transformers` framework. Ollama is not required for basic benchmarks but can be used to serve custom embedding models.

To register an additional embedding model, such as`all-MiniLM-L6-v2`, perform the following steps:

Start the Llama Stack server:

```plaintext
MILVUS_URL=milvus uv run llama stack run run.yaml
```

Register the model by using the Llama Stack client. For example:

```plaintext
llama-stack-client models register all-MiniLM-L6-v2 \
  --provider-id sentence-transformers \
  --provider-model-id all-minilm:latest \
  --metadata {"embedding_dimension": 384} \
  --model-type embedding
```

- You have shut down the Llama Stack server before running the benchmark script.

Procedure

Run the`beir_benchmarks.py` benchmarking script:

Enter the following command to use the configuration from`run.yaml` and the default dataset (`scifact`):

```plaintext
MILVUS_URL=milvus uv run python beir_benchmarks.py
```

Alternatively, enter the following command to connect to a custom Llama Stack server:

```plaintext
LLAMA_STACK_URL="http://localhost:8321" MILVUS_URL=milvus uv run python beir_benchmarks.py
```

Use environment variables and command-line options to modify the benchmark run. For example, set the environment variable`ENABLE_MILVUS=milvus` before executing the script.

Enter the following command to benchmark with a specific LLM by using default settings:

```plaintext
ENABLE_MILVUS=milvus uv run python beir_benchmarks.py
```

Enter the following command to use a larger batch size for document ingestion:

```plaintext
ENABLE_MILVUS=milvus uv run python beir_benchmarks.py --batch-size 300
```

Enter the following command to benchmark multiple datasets (for example,`scifact` and`scidocs`):

```plaintext
ENABLE_MILVUS=milvus uv run python beir_benchmarks.py \
  --dataset-names scifact scidocs
```

Enter the following command to compare embedding models (for example,`granite-embedding-30m` and`all-MiniLM-L6-v2`):

```plaintext
ENABLE_MILVUS=milvus uv run python beir_benchmarks.py \
  --embedding-models granite-embedding-30m all-MiniLM-L6-v2
```

Enter the following command to use a custom BEIR-compatible dataset:

```plaintext
ENABLE_MILVUS=milvus uv run python beir_benchmarks.py \
  --dataset-names my-dataset \
  --custom-datasets-urls https://example.com/my-beir-dataset.zip
```

Enter the following command to change the vector database provider. The following example changes the vector database provider to remote Milvus:

```plaintext
ENABLE_MILVUS=milvus uv run python beir_benchmarks.py \
  --vector-db-provider-id remote-milvus
```

Command-line options

`--vector-db-provider-id`

Example:

```plaintext
--vector-db-provider-id remote-milvus
```

- Description: Specifies the vector database provider to use.
- Type: String.
- Default:`milvus`.

`--dataset-names`

Example:

```plaintext
--dataset-names scifact scidocs nq
```

- Description: Specifies which BEIR datasets to use for benchmarking. Use this option together with`--custom-datasets-urls` when testing custom datasets.
- Type: List of strings.
- Default:`["scifact"]`.

`--embedding-models`

Example:

```plaintext
--embedding-models all-MiniLM-L6-v2 granite-embedding-125m
```

- Description: Specifies the embedding models to compare. Models must be defined in the`run.yaml` file.
- Type: List of strings.
- Default:`["granite-embedding-30m", "granite-embedding-125m"]`.

`--batch-size`

Example:

```plaintext
--batch-size 50
--batch-size 300
```

- Description: Controls how many documents are processed per batch during ingestion. Larger batch sizes improve speed but use more memory.
- Type: Integer.
- Default:`150`.

`--custom-datasets-urls`

Example:

```plaintext
--dataset-names my-custom-dataset \
  --custom-datasets-urls https://example.com/my-dataset.zip
```

- Description: Specifies URLs for custom BEIR-compatible datasets. Use this option with`--dataset-names`.
- Type: List of strings.
- Default:`[]`.

Note

Custom BEIR datasets must follow the required file structure and format:

```plaintext
dataset-name.zip/
├── qrels/
│   └── test.tsv      # Maps query IDs to document IDs with relevance scores
├── corpus.jsonl      # Document collection with document IDs, titles, and text
└── queries.jsonl     # Test queries with query IDs and question text
```

Verification

To verify that the benchmark completed successfully and to review the results, perform the following steps:

Locate the`results` directory. All output files are saved to the following path:

` /rag/benchmarks/embedding-models-with-beir/results`

Examine the output. Compare your results with the sample output structure. The report includes performance metrics such as map@cut_k and ndcg@cut_k for each dataset and embedding model pair. The script also calculates a statistical significance test (p-value).

Example output (for`scifact` and`map_cut_10`):

```plaintext
scifact map_cut_10
 granite-embedding-125m : 0.6879
 granite-embedding-30m  : 0.6578
 p_value                : 0.0150

 p_value < 0.05 indicates a statistically significant difference.
 The granite-embedding-125m model performs better for this dataset and metric.
```

1. Interpret the results. A p-value below`0.05` indicates that the performance difference between models is statistically significant. Use these results to identify which embedding model performs best for your dataset.

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
