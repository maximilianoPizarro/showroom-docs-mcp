# Red Hat OpenShift AI Cloud Service 1

Source: Official Red Hat Documentation (PDF)


---

## Openshift Ai Introduction

Red Hat OpenShift AI Cloud Service 1
Introduction to Red Hat OpenShift AI Cloud
Service
OpenShift AI is a platform for data scientists and developers of artificial intelligence
and machine learning (AI/ML) applications
Last Updated: 2025-10-28

Red Hat OpenShift AI Cloud Service 1 Introduction to Red Hat OpenShift
AI Cloud Service
OpenShift AI is a platform for data scientists and developers of artificial intelligence and machine
learning (AI/ML) applications

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
Red Hat OpenShift AI is a platform for data scientists and developers of artificial intelligence and
machine learning applications.

. . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 
. . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 
. . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 
. . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 
Table of Contents
## OVERVIEW OF OPENSHIFT AI
## PRODUCT FEATURES
### FEATURES FOR DATA SCIENTISTS
### FEATURES FOR OPENSHIFT CLUSTER ADMINISTRATORS
## TRY IT
## GET IT
3
4
4
5
6
7
Table of Contents
1

Red Hat OpenShift AI Cloud Service 1 Introduction to Red Hat OpenShift AI Cloud Service
2
## OVERVIEW OF OPENSHIFT AI
Red Hat OpenShift AI is a platform for data scientists and developers of artificial intelligence and
machine learning (AI/ML) applications.
OpenShift AI provides an environment to develop, train, serve, test, and monitor AI/ML models and
applications on-premise or in the cloud.
For data scientists, OpenShift AI includes Jupyter and a collection of default workbench images
optimized with the tools and libraries required for model development, and the TensorFlow and PyTorch
frameworks. Deploy and host your models, integrate models into external applications, and export
models to host them in any hybrid cloud environment. You can enhance your data science projects on
OpenShift AI by building portable machine learning (ML) workflows with data science pipelines, using
Docker containers. You can also accelerate your data science experiments through the use of graphics
processing units (GPUs) and Intel Gaudi AI accelerators.
For administrators, OpenShift AI enables data science workloads in an existing Red Hat OpenShift or
ROSA environment. Manage users with your existing OpenShift identity provider, and manage the
resources available to workbenches to ensure data scientists have what they require to create, train, and
host models. Use accelerators to reduce costs and allow your data scientists to enhance the
performance of their end-to-end data science workflows using graphics processing units (GPUs) and
Intel Gaudi AI accelerators.
OpenShift AI has two deployment options:
Self-managed software that you can install on-premise or in the cloud. You can install
OpenShift AI Self-Managed in a self-managed environment such as OpenShift Container
Platform, or in Red Hat-managed cloud environments such as Red Hat OpenShift Dedicated
(with a Customer Cloud Subscription for AWS or GCP), Red Hat OpenShift Service on Amazon
Web Services (ROSA classic or ROSA HCP), or Microsoft Azure Red Hat OpenShift.
For information about OpenShift AI as self-managed software on your OpenShift cluster in a
connected or a disconnected environment, see Product Documentation for Red Hat OpenShift
AI Self-Managed.
A managed cloud service, installed as an add-on in Red Hat OpenShift Dedicated (with a
Customer Cloud Subscription for AWS or GCP) or in Red Hat OpenShift Service on Amazon
Web Services (ROSA classic).
For information about OpenShift AI supported software platforms, components, and dependencies, see
the Red Hat OpenShift AI: Supported Configurations Knowledgebase article.
For a detailed view of the release lifecycle, including the full support phase window, see the Red Hat
OpenShift AI Cloud Service Life Cycle Knowledgebase article.
## OVERVIEW OF OPENSHIFT AI
3
## PRODUCT FEATURES
Red Hat OpenShift AI provides several features for data scientists and OpenShift cluster
administrators.
### FEATURES FOR DATA SCIENTISTS
Containers
While tools such as JupyterLab already offer intuitive ways for data scientists to develop models on
their machines, there are always inherent complexities involved with collaboration and sharing work.
Moreover, using specialized hardware such as powerful GPUs can be very expensive when you have
to buy and maintain your own. The Jupyter environment that is included with OpenShift AI lets you
take your development environment anywhere you need it to be. Because all of the workloads are
run as containers, collaboration is as easy as sharing an image with your team members, or even
simply adding it to the list of default containers that they can use. As a result, GPUs and large
amounts of memory are significantly more accessible, since you are no longer limited by what your
laptop can support.
Integration with third-party machine learning tools
We have all run into situations where our favorite tools or services do not play well with one another.
OpenShift AI is designed with flexibility in mind. You can use a wide range of open source and third-
party tools with OpenShift AI. These tools support the complete machine learning lifecycle, from
data engineering and feature extraction to model deployment and management.
Collaboration on Jupyter notebooks with Git
Use Jupyter’s Git interface to work collaboratively with others, and track the changes to your code.
Securely built workbench images
Choose from a default set of workbench images that are pre-configured with the tools and libraries
that you need for model development. Software stacks, especially those involved in machine learning,
tend to be complex systems. There are many modules and libraries in the Python ecosystem that can
be used, so determining which versions of what libraries to use can be very challenging. OpenShift AI
includes many packaged workbench images that have been built with insight from data scientists and
recommendation engines. You can start new projects quickly on the right foot without worrying
about downloading unproven and possibly insecure images from random upstream repositories.
Custom workbench images
In addition to workbench images provided and supported by Red Hat and independent software
vendors (ISVs), you can configure custom workbench images that cater to your project’s specific
requirements.
Data science pipelines
OpenShift AI with data science pipelines 2.0 provides an efficient way to run your data science
workloads. You can standardize and automate machine learning workflows that enable you to
develop and deploy your data science models.
Model serving
As a data scientist, you can deploy your trained machine-learning models to serve intelligent
applications in production. Deploying or serving a model makes the model’s functions available as a
service endpoint that can be used for testing or integration into applications. You have much control
over how this serving is performed.
Optimize your data science models with accelerators
If you work with large data sets, you can optimize the performance of your data science models in
OpenShift AI with NVIDIA graphics processing units (GPUs) or Intel Gaudi AI accelerators.
Accelerators enable you to scale your work, reduce latency, and increase productivity.
Red Hat OpenShift AI Cloud Service 1 Introduction to Red Hat OpenShift AI Cloud Service
4

Model catalog and model registry
The model catalog provides a curated library where data scientists and AI engineers can discover and
evaluate the available generative AI models to find the best fit for their use cases.
A model registry acts as a central repository for AI administrators and data scientists to register,
version, and manage the lifecycle of AI models before configuring them for deployment. A model
registry is a key component for AI model governance.
### FEATURES FOR OPENSHIFT CLUSTER ADMINISTRATORS
Manage users with an identity provider
OpenShift AI uses the same authentication systems as your OpenShift cluster. By default, OpenShift
AI is accessible to all users listed in your identity provider and those users do not need a separate set
of credentials to access OpenShift AI. Optionally, you can limit the set of users who have access by
creating an OpenShift group that specifies a subset of users. You can also create an OpenShift
group that identifies the list of users who have administrator access to OpenShift AI.
Manage resources with OpenShift
Use your existing OpenShift knowledge to configure and manage resources for your OpenShift AI
users.
Control Red Hat usage data collection
Choose whether to allow Red Hat to collect data about OpenShift AI usage in your cluster. Usage
data collection is enabled by default when you install OpenShift AI on your OpenShift cluster.
Apply autoscaling to your cluster to reduce usage costs
Use the cluster autoscaler to adjust the size of your cluster to meet its current needs and optimize
costs.
Manage resource usage by stopping idle workbenches
Reduce resource usage in your OpenShift AI deployment by automatically stopping workbenches
that have been idle for a period of time.
Implement model-serving runtimes
OpenShift AI provides support for model-serving runtimes. A model-serving runtime provides
integration with a specified model server and the model frameworks that it supports. By default,
OpenShift AI includes the OpenVINO Model Server runtime. However, if this runtime does not meet
your needs (for example, if it does not support a particular model framework), you can add your own
custom runtimes.
Install in a disconnected environment
OpenShift AI Self-Managed supports installation in a disconnected environment. Disconnected
clusters are on a restricted network, typically behind a firewall and unable to reach the Internet. In this
case, clusters cannot access the remote registries where Red Hat provided OperatorHub sources
reside. In this case, you deploy the OpenShift AI Operator to a disconnected environment by using a
private registry in which you have mirrored (copied) the relevant images.
Manage accelerators
Enable NVIDIA graphics processing units (GPUs) or Intel Gaudi AI accelerators in OpenShift AI and
allow your data scientists to use compute-heavy workloads.
## PRODUCT FEATURES
5
## TRY IT
Data scientists and developers can try OpenShift AI and access tutorials and activities in the Red Hat
Developer Sandbox environment.
OpenShift cluster administrators can try OpenShift AI in your own cluster with a 60-day product trial .
Red Hat OpenShift AI Cloud Service 1 Introduction to Red Hat OpenShift AI Cloud Service
6
## GET IT
Managed cloud service
You have the following options for subscribing to OpenShift AI as a managed service:
For OpenShift Dedicated, subscribe through Red Hat.
For Red Hat OpenShift Service on Amazon Web Services (ROSA), subscribe through
Red Hat or subscribe through the AWS Marketplace.
Self-managed software
To get Red Hat OpenShift AI as self-managed software, sign up for it with your Red Hat account
team.
## GET IT
7

---

## Openshift Ai Getting Started

Red Hat OpenShift AI Cloud Service 1
Getting started with Red Hat OpenShift AI
Cloud Service
Learn how to work in an OpenShift AI environment
Last Updated: 2025-10-28

Red Hat OpenShift AI Cloud Service 1 Getting started with Red Hat
OpenShift AI Cloud Service
Learn how to work in an OpenShift AI environment

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
Learn how to work in an OpenShift AI environment.

. . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 
. . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 
. . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 
. . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 
. . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 
Table of Contents
## OVERVIEW
### DATA SCIENCE WORKFLOW
### ABOUT THIS GUIDE
### GLOSSARY OF COMMON TERMS
## LOGGING IN TO OPENSHIFT AI
### VIEWING INSTALLED OPENSHIFT AI COMPONENTS
## CREATING A DATA SCIENCE PROJECT
## CREATING A WORKBENCH AND SELECTING AN IDE
### ABOUT WORKBENCH IMAGES
### BUILDING THE RSTUDIO SERVER WORKBENCH IMAGES
### CREATING A WORKBENCH
## NEXT STEPS
### ADDITIONAL RESOURCES
3
3
4
4
8
8
10
11
11
14
17
22
23
Table of Contents
1

Red Hat OpenShift AI Cloud Service 1 Getting started with Red Hat OpenShift AI Cloud Service
2
## OVERVIEW
Red Hat OpenShift AI is an artificial intelligence (AI) platform that provides tools to rapidly train, serve,
and monitor machine learning (ML) models onsite, in the public cloud, or at the edge.
OpenShift AI provides a powerful AI/ML platform for building AI-enabled applications. Data scientists
and MLOps engineers can collaborate to move from experiment to production in a consistent
environment quickly.
You can deploy OpenShift AI on any supported version of OpenShift, whether on-premise, in the cloud,
or in disconnected environments. For details on supported versions, see Red Hat OpenShift AI:
Supported Configurations.
### DATA SCIENCE WORKFLOW
For the purpose of getting you started with OpenShift AI, the following figure illustrates a simplified
data science workflow. The real world process of developing ML models is an iterative one.
Figure 1.1. Simplified data science workflow
The simplified data science workflow for predictive AI use cases includes the following tasks:
Defining your business problem and setting goals to solve it.
Gathering, cleaning, and preparing data. Data often has to be federated from a range of
sources, and exploring and understanding data plays a key role in the success of a data science
project.
Evaluating and selecting ML models for your business use case.
Train models for your business use case by tuning model parameters based on your set of
training data. In practice, data scientists train a range of models, and compare performance
while considering tradeoffs such as time and memory constraints.
Integrate models into an application, including deployment and testing. After model training, the
next step of the workflow is production. Data scientists are often responsible for putting the
model in production and making it accessible so that a developer can integrate the model into
an application.
Monitor and manage deployed models. Depending on the organization, data scientists, data
engineers, or ML engineers must monitor the performance of models in production, tracking
prediction and performance metrics.
## OVERVIEW
3

Refine and retrain models. Data scientists can evaluate model performance results and refine
models to improve outcome by excluding or including features, changing the training data, and
modifying other configuration parameters.
### ABOUT THIS GUIDE
This guide assumes you are familiar with data science and ML Ops concepts. It describes the following
tasks to get you started with using OpenShift AI:
Log in to the OpenShift AI dashboard
Create a data science project
If you have data stored in Object Storage, configure a connection to more easily access it
Create a workbench and choose an IDE, such as JupyterLab or code-server, for your data
scientist development work
Learn where to get information about the next steps:
Developing and training a model
Automating the workflow with pipelines
Implementing distributed workloads
Testing your model
Deploying your model
Monitoring and managing your model
See also OpenShift AI tutorial: Fraud detection example . It provides step-by-step guidance for using
OpenShift AI to develop and train an example model in JupyterLab, deploy the model, and refine the
model by using automated pipelines.
### GLOSSARY OF COMMON TERMS
This glossary defines common terms for Red Hat OpenShift AI.
accelerator
In high-performance computing, a specialized circuit that is used to take some of the computational
load from the CPU, increasing the efficiency of the system. For example, in deep learning, GPU-
accelerated computing is often employed to offload part of the compute workload to a GPU while
the main application runs off the CPU.
artificial intelligence (AI)
The capability to acquire, process, create and apply knowledge in the form of a model to make
predictions, recommendations or decisions.
bias detection
The process of calculating fairness metrics to detect when AI models are delivering unfair outcomes
based on certain attributes.
custom resource (CR)
Red Hat OpenShift AI Cloud Service 1 Getting started with Red Hat OpenShift AI Cloud Service
4

A resource implemented through the Kubernetes CustomResourceDefinition API. A custom resource
is distinct from the built-in Kubernetes resources, such as the pod and service resources. Every CR is
part of an API group.
custom resource definition (CRD)
In Red Hat OpenShift, a custom resource definition (CRD) defines a new, unique object Kind in the
cluster and lets the Kubernetes API server handle its entire lifecycle.
connections
A configuration that stores the parameters required to connect to an S3-compatible object storage,
database or OCI-compliant container registry from a data science project.
connection type
The type of external source to connect to from a data science project, such as an OCI-compliant
container registry, S3-compatible object storage, or Uniform Resource Identifiers (URIs).
data science pipelines
A workflow engine that is used by data scientists and AI engineers to automate pipelines, such as
model training and evaluation pipelines. Data science pipelines also includes experiment tracking
capabilities, artifact storage, and versioning.
data science project
An OpenShift project for organizing data science work. Each project is scoped to its own Kubernetes
namespace.
disconnected environment
An environment on a restricted network that does not have an active connection to the internet.
distributed workloads
Data science workloads that are run simultaneously across multiple nodes in an OpenShift cluster.
fine-tuning
The process of adapting a pre-trained model to perform a specific task by conducting additional
training. Fine tuning may involve (1) updating the model’s existing parameters, known as full fine
tuning, or (2) updating a subset of the model’s existing parameters or adding new parameters to the
model and training them while freezing the model’s existing parameters, known as parameter-
efficient fine tuning.
graphics processing unit (GPU)
A specialized processor designed to rapidly manipulate and alter memory to accelerate the creation
of images in a frame buffer intended for output to a display. GPUs are heavily utilized in machine
learning due to their parallel processing capabilities.
inference
The process of using a trained AI model to generate predictions or conclusions based on the input
data provided to the model.
inference server
A server that performs inference. Inference servers feed the input requests through a machine
learning model and return an output.
large language model (LLM)
A language model with a large number of parameters, trained on a large quantity of text.
machine learning (ML)
A branch of artificial intelligence (AI) and computer science that focuses on the use of data and
algorithms to imitate the way that humans learn, gradually improving the accuracy of AI models.
model
## OVERVIEW
5

In a machine learning context, a set of functions and algorithms that have been trained and tested on
a data set to provide predictions or decisions.
model registry
A central repository containing metadata related to machine learning models from inception to
deployment. The metadata ranges from high-level information such as the deployment environment
and project origins, to intricate details like training hyperparameters, performance metrics, and
deployment events.
model server
A container that hosts a machine learning model, exposes an API to handle incoming requests,
performs inference, and returns model predictions.
model-serving runtime
A component or framework that helps create model servers for deploying machine learning models
and build APIs optimized for inference.
MLOps
The practice for collaboration between data scientists and operations professionals to help manage
the production machine learning (or deep learning) lifecycle. MLOps looks to increase automation
and improve the quality of production ML while also focusing on business and regulatory
requirements. It involves model development, training, validation, deployment, monitoring, and
management and uses methods like CI/CD.
notebook interface
An interactive document that contains executable code, descriptive text for that code, and the
results of any code that is run.
object storage
A method of storing data, typically used in the cloud, in which data is stored as discrete units, or
objects, in a storage pool or repository that does not use a file hierarchy but that stores all objects at
the same level.
OpenShift Container Platform cluster
A group of physical machines that contains the controllers, pods, services, and configurations
required to build and run containerized applications.
persistent storage
A persistent volume that retains files, models or other artifacts across components such as model
deployments, data science pipelines and workbenches.
persistent volume claim (PVC)
A persistent volume claim (PVC) is a request for storage in the cluster by a user.
quantization
A method of compressing foundation model weights to speed up inferencing and reduce memory
needs.
serving
The process of hosting a trained machine learning model as a network-accessible service. Real-world
applications can send inference requests to the service by using a REST or gRPC API and receive
predictions.
ServingRuntime
A custom resource definition (CRD) that defines the templates for pods that can serve one or more
particular model formats. Each ServingRuntime CRD defines key information such as the container
image of the runtime and a list of the model formats that the runtime supports. Other configuration
settings for the runtime can be conveyed through environment variables in the container
specification. It also dynamically loads and unloads models from disk into memory on demand and
exposes a gRPC service endpoint to serve inferencing requests for loaded models.
Red Hat OpenShift AI Cloud Service 1 Getting started with Red Hat OpenShift AI Cloud Service
6

vLLM
A high-throughput and efficient inference engine for running large-language models that integrates
with popular models and frameworks.
workbench
An isolated environment for development and experimentation with ML models. Workbenches
typically contain integrated development environments (IDEs), such as JupyterLab, RStudio, and
Visual Studio Code.
workbench image
An image that includes preinstalled tools and libraries that you need for model development.
Includes an IDE for developing your machine learning (ML) models.
YAML
A human-readable data-serialization language. It is commonly used for configuration files and in
applications where data is being stored or transmitted.
## OVERVIEW
7
## LOGGING IN TO OPENSHIFT AI
After you install OpenShift AI, log in to the OpenShift AI dashboard so that you can set up your
development and deployment environment.
Prerequisites
OpenShift AI is installed on your OpenShift cluster. For information, see Installing and deploying
OpenShift AI.
You know the OpenShift AI identity provider and your login credentials.
If you are a data scientist, data engineer, or ML engineer, your administrator must provide
you with the OpenShift AI instance URL, for example:
https://rhoai-dashboard-redhat-oai-
applications.apps.example.abc1.p1.openshiftapps.com/
You have the latest version of one of the following supported browsers:
Google Chrome
Mozilla Firefox
Safari
Procedure
1. Browse to the OpenShift AI instance URL and click Log in with OpenShift.
If you have access to OpenShift, you can browse to the OpenShift web console and click
the Application Launcher ( 
 ) → Red Hat OpenShift AI.
2. Click the name of your identity provider, for example, GitHub,Google, or your company’s single
sign-on method.
3. Enter your credentials and click Log in (or equivalent for your identity provider).
Verification
The OpenShift AI dashboard opens on the Home page.
### VIEWING INSTALLED OPENSHIFT AI COMPONENTS
In the Red Hat OpenShift AI dashboard, you can view a list of the installed OpenShift AI components,
their corresponding source (upstream) components, and the versions of the installed components.
Prerequisites
OpenShift AI is installed in your OpenShift cluster.
Procedure
Red Hat OpenShift AI Cloud Service 1 Getting started with Red Hat OpenShift AI Cloud Service
8

1. Log in to the OpenShift AI dashboard.
2. In the top navigation bar, click the help icon ( 
 ) and then select About.
Verification
The About page shows a list of the installed OpenShift AI components along with their corresponding
upstream components and upstream component versions.
Additional resources
Installing and managing Red Hat OpenShift AI components .
## LOGGING IN TO OPENSHIFT A
9
## CREATING A DATA SCIENCE PROJECT
To implement a data science workflow, you must create a project. In OpenShift, a project is a Kubernetes
namespace with additional annotations, and is the main way that you can manage user access to
resources. A project organizes your data science work in one place and also allows you to collaborate
with other developers and data scientists in your organization.
Within a project, you can add the following functionality:
Connections so that you can access data without having to hardcode information like endpoints
or credentials.
Workbenches for working with and processing data, and for developing models.
Deployed models so that you can test them and then integrate them into intelligent
applications. Deploying a model makes it available as a service that you can access by using an
API.
Pipelines for automating your ML workflow.
Prerequisites
You have logged in to Red Hat OpenShift AI.
You have the appropriate roles and permissions to create projects.
Procedure
1. From the OpenShift AI dashboard, select Data science projects.
The Data science projects page shows a list of projects that you can access. For each user-
requested project in the list, the Name column shows the project display name, the user who
requested the project, and the project description.
2. Click Create project.
3. In the Create project dialog, update the Name field to enter a unique display name for your
project.
4. Optional: If you want to change the default resource name for your project, click Edit resource
name.
The resource name is what your resource is labeled in OpenShift. Valid characters include
lowercase letters, numbers, and hyphens (-). The resource name cannot exceed 30 characters,
and it must start with a letter and end with a letter or number.
Note: You cannot change the resource name after the project is created. You can edit only the
display name and the description.
5. Optional: In the Description field, provide a project description.
6. Click Create.
Verification
A project details page opens. From this page, you can add connections, create workbenches,
configure pipelines, and deploy models.
Red Hat OpenShift AI Cloud Service 1 Getting started with Red Hat OpenShift AI Cloud Service
10
## CREATING A WORKBENCH AND SELECTING AN
IDE
A workbench is an isolated area where you can examine and work with ML models. You can also work
with data and run programs, for example to prepare and clean data. While a workbench is not required if,
for example, you only want to service an existing model, one is needed for most data science workflow
tasks, such as writing code to process data or training a model.
When you create a workbench, you specify an image (an IDE, packages, and other dependencies).
Supported IDEs include JupyterLab, code-server, and RStudio (Technology Preview).
The IDEs are based on a server-client architecture. Each IDE provides a server that runs in a container
on the OpenShift cluster, while the user interface (the client) is displayed in your web browser. For
example, the Jupyter workbench runs in a container on the Red Hat OpenShift cluster. The client is the
JupyterLab interface that opens in your web browser on your local computer. All of the commands that
you enter in JupyterLab are executed by the workbench. Similarly, other IDEs like code-server or
RStudio Server provide a server that runs in a container on the OpenShift cluster, while the user
interface is displayed in your web browser. This architecture allows you to interact through your local
computer in a browser environment, while all processing occurs on the cluster. The cluster provides the
benefits of larger available resources and security because the data being processed never leaves the
cluster.
In a workbench, you can also configure connections (to access external data for training models and to
save models so that you can deploy them) and cluster storage (for persisting data). Workbenches within
the same project can share models and data through object storage with the data science pipelines and
model servers.
For data science projects that require data retention, you can add container storage to the workbench
you are creating.
Within a project, you can create multiple workbenches. When to create a new workbench depends on
considerations, such as the following:
The workbench configuration (for example, CPU, RAM, or IDE). If you want to avoid editing the
configuration of an existing workbench’s configuration to accommodate a new task, you can
create a new workbench instead.
Separation of tasks or activities. For example, you might want to use one workbench for your
Large Language Models (LLM) experimentation activities, another workbench dedicated to a
demo, and another workbench for testing.
### ABOUT WORKBENCH IMAGES
A workbench image is preinstalled with the tools and libraries that you need for model development. You
can use the provided workbench images or an OpenShift AI administrator can create custom workbench
images adapted to your needs.
To provide a consistent, stable platform for your model development, many provided workbench images
contain the same version of Python. Most workbench images available on OpenShift AI are pre-built and
ready for you to use immediately after OpenShift AI is installed or upgraded.
For information about Red Hat support of workbench images and packages, see Red Hat OpenShift AI:
Supported Configurations.
The following table lists the workbench images that are installed with Red Hat OpenShift AI by default.
## CREATING A WORKBENCH AND SELECTING AN IDE
11

If the preinstalled packages that are provided in these images are not sufficient for your use case, you
have the following options:
Install additional libraries after launching a default image. This option is good if you want to add
libraries on an ad hoc basis as you develop models. However, it can be challenging to manage
the dependencies of installed libraries and your changes are not saved when the workbench
restarts.
Create a custom image that includes the additional libraries or packages. For more information,
see Creating custom workbench images.
IMPORTANT
Workbench images denoted with (Technology Preview) in this table are not supported
with Red Hat production service level agreements (SLAs) and might not be functionally
complete. Red Hat does not recommend using Technology Preview features in
production. These features provide early access to upcoming product features, enabling
customers to test functionality and provide feedback during the development process.
For more information about the support scope of Red Hat Technology Preview features,
see Technology Preview Features Support Scope .
Table 4.1. Default workbench images
Image name
Description
CUDA
If you are working with compute-intensive data science models that require GPU support,
use the Compute Unified Device Architecture (CUDA) workbench image to gain access to
the NVIDIA CUDA Toolkit. Using this toolkit, you can optimize your work by using GPU-
accelerated libraries and optimization tools.
Standard Data
Science
Use the Standard Data Science workbench image for models that do not require
TensorFlow or PyTorch. This image contains commonly-used libraries to assist you in
developing your machine learning models.
TensorFlow
TensorFlow is an open source platform for machine learning. With TensorFlow, you can
build, train and deploy your machine learning models. TensorFlow contains advanced data
visualization features, such as computational graph visualizations. It also allows you to
easily monitor and track the progress of your models.
PyTorch
PyTorch is an open source machine learning library optimized for deep learning. If you are
working with computer vision or natural language processing models, use the Pytorch
workbench image.
Minimal Python
If you do not require advanced machine learning features, or additional resources for
compute-intensive data science work, you can use the Minimal Python image to develop
your models.
TrustyAI
Use the TrustyAI workbench image to leverage your data science work with model
explainability, tracing, and accountability, and runtime monitoring. See the TrustyAI
Explainability repository for some example Jupyter notebooks.
Red Hat OpenShift AI Cloud Service 1 Getting started with Red Hat OpenShift AI Cloud Service
12

code-server
With the code-server workbench image, you can customize your workbench environment
to meet your needs using a variety of extensions to add new languages, themes,
debuggers, and connect to additional services. Enhance the efficiency of your data science
work with syntax highlighting, auto-indentation, and bracket matching, as well as an
automatic task runner for seamless automation. For more information, see code-server in
GitHub.
NOTE: Elyra-based pipelines are not available with the code-server workbench image.
RStudio Server
(Technology
preview)
Use the RStudio Server workbench image to access the RStudio IDE, an integrated
development environment for R, a programming language for statistical computing and
graphics. For more information, see the RStudio Server site.
To use the RStudio Server workbench image, you must first build it by creating a secret
and triggering the BuildConfig, and then enable it in the OpenShift AI UI by editing the 
rstudio-rhel9 image stream. For more information, see Building the RStudio Server
workbench images.
IMPORTANT
Disclaimer:
Red Hat supports managing workbenches in OpenShift AI. However,
Red Hat does not provide support for the RStudio software. RStudio
Server is available through https://rstudio.org/ and is subject to RStudio
licensing terms. Review the licensing terms before you use this sample
workbench.
Image name
Description
## CREATING A WORKBENCH AND SELECTING AN IDE
13

CUDA -
RStudio Server
(Technology
Preview)
Use the CUDA - RStudio Server workbench image to access the RStudio IDE and NVIDIA
CUDA Toolkit. RStudio is an integrated development environment for R, a programming
language for statistical computing and graphics. With the NVIDIA CUDA toolkit, you can
optimize your work using GPU-accelerated libraries and optimization tools. For more
information, see the RStudio Server site.
To use the CUDA - RStudio Server workbench image, you must first build it by creating a
secret and triggering the BuildConfig, and then enable it in the OpenShift AI UI by editing
the cuda-rstudio-rhel9 image stream. For more information, see Building the RStudio
Server workbench images.
IMPORTANT
Disclaimer:
Red Hat supports managing workbenches in OpenShift AI. However,
Red Hat does not provide support for the RStudio software. RStudio
Server is available through https://rstudio.org/ and is subject to RStudio
licensing terms. Review the licensing terms before you use this sample
workbench.
The CUDA - RStudio Server workbench image contains NVIDIA CUDA
technology. CUDA licensing information is available at
https://docs.nvidia.com/cuda/. Review the licensing terms before you use
this sample workbench.
ROCm
Use the ROCm workbench image to run AI and machine learning workloads on AMD GPUs
in OpenShift AI. It includes ROCm libraries and tools optimized for high-performance GPU
acceleration, supporting custom AI workflows and data processing tasks. Use this image
integrating additional frameworks or dependencies tailored to your specific AI
development needs.
ROCm-
PyTorch
Use the ROCm-PyTorch workbench image to run PyTorch workloads on AMD GPUs in
OpenShift AI. It includes ROCm-accelerated PyTorch libraries, enabling efficient deep
learning training, inference, and experimentation. This image is designed for data scientists
working with PyTorch-based workflows, offering integration with GPU scheduling.
ROCm-
TensorFlow
Use the ROCm-TensorFlow workbench image to run TensorFlow workloads on AMD GPUs
in OpenShift AI. It includes ROCm-accelerated TensorFlow libraries to support high-
performance deep learning model training and inference. This image simplifies TensorFlow
development on AMD GPUs and integrates with OpenShift AI for resource scaling and
management.
Image name
Description
### BUILDING THE RSTUDIO SERVER WORKBENCH IMAGES
Red Hat OpenShift AI Cloud Service 1 Getting started with Red Hat OpenShift AI Cloud Service
14

IMPORTANT
The RStudio Server and CUDA - RStudio Server workbench images are currently
available in Red Hat OpenShift AI as Technology Preview features.
Technology Preview features are not supported with Red Hat production service level
agreements (SLAs) and might not be functionally complete. Red Hat does not
recommend using them in production. These features provide early access to upcoming
product features, enabling customers to test functionality and provide feedback during
the development process.
For more information about the support scope of Red Hat Technology Preview features,
see Technology Preview Features Support Scope .
Red Hat OpenShift AI includes the following RStudio Server workbench images:
RStudio Server workbench image
With the RStudio Server workbench image, you can access the RStudio IDE, an integrated
development environment for the R programming language. R is used for statistical computing
and graphics to support data analysis and predictions.
IMPORTANT
Disclaimer: Red Hat supports managing workbenches in OpenShift AI. However,
Red Hat does not provide support for the RStudio software. RStudio Server is
available through rstudio.org and is subject to their licensing terms. You should
review their licensing terms before you use this sample workbench.
CUDA - RStudio Server workbench image
With the CUDA - RStudio Server workbench image, you can access the RStudio IDE and
NVIDIA CUDA Toolkit. The RStudio IDE is an integrated development environment for the R
programming language for statistical computing and graphics. With the NVIDIA CUDA toolkit,
you can enhance your work by using GPU-accelerated libraries and optimization tools.
IMPORTANT
Disclaimer: Red Hat supports managing workbenches in OpenShift AI. However,
Red Hat does not provide support for the RStudio software. RStudio Server is
available through rstudio.org and is subject to their licensing terms. You should
review their licensing terms before you use this sample workbench.
The CUDA - RStudio Server workbench image contains NVIDIA CUDA
technology. CUDA licensing information is available in the CUDA Toolkit
documentation. You should review their licensing terms before you use this
sample workbench.
To use the RStudio Server and CUDA - RStudio Server workbench images, you must first build them
by creating a secret and triggering the BuildConfig, and then enable them in the OpenShift AI UI by
editing the rstudio-rhel9 and cuda-rstudio-rhel9 image streams.
Prerequisites
f
h
d
b ld
h
l
d
## CREATING A WORKBENCH AND SELECTING AN IDE
15

Before starting the RStudio Server build process, you have at least 1 CPU and 2Gi memory
available for rstudio-server-rhel9, and 1.5 CPUs and 8Gi memory available for cuda-rstudio-
server-rhel9 on your cluster.
You are logged in to your OpenShift cluster.
You have the cluster-admin role in OpenShift.
You have an active Red Hat Enterprise Linux (RHEL) subscription.
Procedure
1. Create a secret with Subscription Manager credentials. These are usually your Red Hat
Customer Portal username and password.
Note: The secret must be named rhel-subscription-secret, and its USERNAME and 
PASSWORD keys must be in capital letters.
oc create secret generic rhel-subscription-secret --from-literal=USERNAME=<username> --
from-literal=PASSWORD=<password> -n redhat-ods-applications
2. Start the build:
a. To start the lightweight RStudio Server build:
oc start-build rstudio-server-rhel9 -n redhat-ods-applications --follow
b. To start the CUDA-enabled RStudio Server build, trigger the cuda-rstudio-server-rhel9
BuildConfig:
oc start-build cuda-rstudio-server-rhel9 -n redhat-ods-applications --follow
3. Confirm that the build process has completed successfully using the following command.
Successful builds appear as Complete.
oc get builds -n redhat-ods-applications
4. After the builds complete successfully, use the following commands to make the workbench
images available in the OpenShift AI UI.
a. To enable the RStudio Server workbench image:
oc label -n redhat-ods-applications imagestream rstudio-rhel9 opendatahub.io/notebook-
image='true'
b. To enable the CUDA - RStudio Server workbench image:
oc label -n redhat-ods-applications imagestream cuda-rstudio-rhel9 
opendatahub.io/notebook-image='true'
Verification
You can see RStudio Server and CUDA - RStudio Server images on the Applications →
Enabled menu in the Red Hat OpenShift AI dashboard.
Red Hat OpenShift AI Cloud Service 1 Getting started with Red Hat OpenShift AI Cloud Service
16

You can see R Studio Server or CUDA - RStudio Server in the Data science projects →
Workbenches → Create workbench → Notebook image → Image selection dropdown list.
### CREATING A WORKBENCH
When you create a workbench, you specify an image (an IDE, packages, and other dependencies). You
can also configure connections, cluster storage, and add container storage.
Prerequisites
You have logged in to Red Hat OpenShift AI.
You have created a project.
If you created a Simple Storage Service (S3) account outside of Red Hat OpenShift AI and you
want to create connections to your existing S3 storage buckets, you have the following
credential information for the storage buckets:
Endpoint URL
Access key
Secret key
Region
Bucket name
For more information, see Working with data in an S3-compatible object store .
Procedure
1. From the OpenShift AI dashboard, click Data science projects.
The Data science projects page opens.
2. Click the name of the project that you want to add the workbench to.
A project details page opens.
3. Click the Workbenches tab.
4. Click Create workbench.
The Create workbench page opens.
5. In the Name field, enter a unique name for your workbench.
6. Optional: If you want to change the default resource name for your workbench, click Edit
resource name.
The resource name is what your resource is labeled in OpenShift. Valid characters include
lowercase letters, numbers, and hyphens (-). The resource name cannot exceed 30 characters,
and it must start with a letter and end with a letter or number.
Note: You cannot change the resource name after the workbench is created. You can edit only
the display name and the description.
7. Optional: In the Description field, enter a description for your workbench.
## CREATING A WORKBENCH AND SELECTING AN IDE
17

8. In the Workbench image section, complete the fields to specify the workbench image to use
with your workbench.
From the Image selection list, select a workbench image that suits your use case. A workbench
image includes an IDE and Python packages (reusable code). If project-scoped images exist,
the Image selection list includes subheadings to distinguish between global images and
project-scoped images.
Optionally, click View package information to view a list of packages that are included in the
image that you selected.
If the workbench image has multiple versions available, select the workbench image version to
use from the Version selection list. To use the latest package versions, Red Hat recommends
that you use the most recently added image.
NOTE
You can change the workbench image after you create the workbench.
9. In the Deployment size section, select one of the following options, depending on whether the
hardware profiles feature is enabled.
IMPORTANT
The hardware profiles feature is currently available in Red Hat OpenShift AI as a
Technology Preview feature. Technology Preview features are not supported
with Red Hat production service level agreements (SLAs) and might not be
functionally complete. Red Hat does not recommend using them in production.
These features provide early access to upcoming product features, enabling
customers to test functionality and provide feedback during the development
process.
For more information about the support scope of Red Hat Technology Preview
features, see Technology Preview Features Support Scope .
If the hardware profiles feature is not enabled:
a. From the Container size list, select the appropriate size for the size of the model that
you want to train or tune.
For example, to run the example fine-tuning job described in Fine-tuning a model by
using Kubeflow Training, select Medium.
b. From the Accelerator list, select a suitable accelerator profile for your workbench.
If project-scoped accelerator profiles exist, the Accelerator list includes subheadings
to distinguish between global accelerator profiles and project-scoped accelerator
profiles.
If the hardware profiles feature is enabled:
a. From the Hardware profile list, select a suitable hardware profile for your workbench.
If project-scoped hardware profiles exist, the Hardware profile list includes
subheadings to distinguish between global hardware profiles and project-scoped
hardware profiles.
h h
d
f l
f
h
b
f
d h
f
Red Hat OpenShift AI Cloud Service 1 Getting started with Red Hat OpenShift AI Cloud Service
18

The hardware profile specifies the number of CPUs and the amount of memory
allocated to the container, setting the guaranteed minimum (request) and maximum
(limit) for both.
b. If you want to change the default values, click Customize resource requests and limit
and enter new minimum (request) and maximum (limit) values.
IMPORTANT
By default, the hardware profiles feature is not enabled: hardware
profiles are not shown in the dashboard navigation menu or elsewhere in
the user interface. In addition, user interface components associated
with the deprecated accelerator profiles functionality are still displayed.
To show the Settings → Hardware profiles option in the dashboard
navigation menu, and the user interface components associated with
hardware profiles, set the disableHardwareProfiles value to false in the
OdhDashboardConfig custom resource (CR) in OpenShift. For more
information about setting dashboard configuration options, see
Customizing the dashboard.
10. Optional: In the Environment variables section, select and specify values for any environment
variables.
Setting environment variables during the workbench configuration helps you save time later
because you do not need to define them in the body of your workbenches, or with the IDE
command line interface.
If you are using S3-compatible storage, add these recommended environment variables:
AWS_ACCESS_KEY_ID specifies your Access Key ID for Amazon Web Services.
AWS_SECRET_ACCESS_KEY specifies your Secret access key for the account specified
in AWS_ACCESS_KEY_ID.
OpenShift AI stores the credentials as Kubernetes secrets in a protected namespace if you
select Secret when you add the variable.
11. In the Cluster storage section, configure the storage for your workbench. Select one of the
following options:
Create new persistent storage to create storage that is retained after you shut down your
workbench. Complete the relevant fields to define the storage:
a. Enter a name for the cluster storage.
b. Enter a description for the cluster storage.
c. Select a storage class for the cluster storage.
NOTE
You cannot change the storage class after you add the cluster storage to
the workbench.
d
l
h
l
l
d
l
d
## CREATING A WORKBENCH AND SELECTING AN IDE
19

d. For storage classes that support multiple access modes, select an Access mode to
define how the volume can be accessed. For more information, see About persistent
storage.
Only the access modes that have been enabled for the storage class by your cluster and
OpenShift AI administrators are visible.
e. Under Persistent storage size, enter a new size in gibibytes or mebibytes.
Use existing persistent storage to reuse existing storage and select the storage from the
Persistent storage list.
12. Optional: You can add a connection to your workbench. A connection is a resource that contains
the configuration parameters needed to connect to a data source or sink, such as an object
storage bucket. You can use storage buckets for storing data, models, and pipeline artifacts.
You can also use a connection to specify the location of a model that you want to deploy.
In the Connections section, use an existing connection or create a new connection:
Use an existing connection as follows:
a. Click Attach existing connections.
b. From the Connection list, select a connection that you previously defined.
Create a new connection as follows:
a. Click Create connection. The Add connection dialog opens.
b. From the Connection type drop-down list, select the type of connection. The
Connection details section is displayed.
c. If you selected S3 compatible object storage in the preceding step, configure the
connection details:
i. In the Connection name field, enter a unique name for the connection.
ii. Optional: In the Description field, enter a description for the connection.
iii. In the Access key field, enter the access key ID for the S3-compatible object
storage provider.
iv. In the Secret key field, enter the secret access key for the S3-compatible object
storage account that you specified.
v. In the Endpoint field, enter the endpoint of your S3-compatible object storage
bucket.
vi. In the Region field, enter the default region of your S3-compatible object storage
account.
vii. In the Bucket field, enter the name of your S3-compatible object storage bucket.
viii. Click Create.
d. If you selected URI in the preceding step, configure the connection details:
i. In the Connection name field, enter a unique name for the connection.
ii. Optional: In the Description field, enter a description for the connection.
Red Hat OpenShift AI Cloud Service 1 Getting started with Red Hat OpenShift AI Cloud Service
20

iii. In the URI field, enter the Uniform Resource Identifier (URI).
iv. Click Create.
13. Click Create workbench.
Verification
The workbench that you created is visible on the Workbenches tab for the project.
Any cluster storage that you associated with the workbench during the creation process is
displayed on the Cluster storage tab for the project.
The Status column on the Workbenches tab displays a status of Starting when the workbench
server is starting, and Running when the workbench has successfully started.
Optional: Click the open icon ( 
 ) to open the IDE in a new window.
## CREATING A WORKBENCH AND SELECTING AN IDE
21
## NEXT STEPS
The following product documentation provides more information on how to develop, test, and deploy
data science solutions with OpenShift AI.
Try the end-to-end tutorial
OpenShift AI tutorial - Fraud detection example
Step-by-step guidance to complete the following tasks with an example fraud detection model:
Explore a pre-trained fraud detection model by using a Jupyter notebook.
Deploy the model by using OpenShift AI model serving.
Refine and train the model by using automated pipelines.
Develop and train a model in your workbench IDE
Working in your data science IDE
Learn how to access your workbench IDE (JupyterLab, code-server, or RStudio Server).
For the JupyterLab IDE, learn about the following tasks:
Creating and importing Jupyter notebooks
Using Git to collaborate on Jupyter notebooks
Viewing and installing Python packages
Troubleshooting common problems
Automate your ML workflow with pipelines
Working with data science pipelines
Enhance your data science projects on OpenShift AI by building portable machine learning (ML)
workflows with data science pipelines, by using Docker containers. Use pipelines for continuous
retraining and updating of a model based on newly received data.
Deploy and test a model
Deploying models
Deploy your ML models on your OpenShift cluster to test and then integrate them into intelligent
applications. When you deploy a model, it is available as a service that you can access by using API
calls. You can return predictions based on data inputs that you provide through API calls.
Monitor and manage models
Deploying models
The Red Hat OpenShift AI service includes model deployment options for hosting the model on Red
Hat OpenShift Dedicated or Red Hat OpenShift Service on AWS for integration into an external
application.
Add accelerators to optimize performance
Working with accelerators
If you work with large data sets, you can use accelerators, such as NVIDIA GPUs, AMD GPUs, and
Intel Gaudi AI accelerators, to optimize the performance of your data science models in OpenShift
AI. With accelerators, you can scale your work, reduce latency, and increase productivity.
Red Hat OpenShift AI Cloud Service 1 Getting started with Red Hat OpenShift AI Cloud Service
22

Implement distributed workloads for higher performance
Working with distributed workloads
Implement distributed workloads to use multiple cluster nodes in parallel for faster, more efficient
data processing and model training.
Explore extensions
Working with connected applications
Extend your core OpenShift AI solution with integrated third-party applications. Several leading
AI/ML software technology partners, including Starburst, Intel AI Tools, and IBM are also available
through Red Hat partners and IBM Partner Plus Directory .
### ADDITIONAL RESOURCES
In addition to product documentation, Red Hat provides a rich set of learning resources for OpenShift
AI and supported applications.
On the Resources page of the OpenShift AI dashboard, you can use the category links to filter the
resources for various stages of your data science workflow. For example, click the Model serving
category to display resources that describe various methods of deploying models. Click All items to
show the resources for all categories.
For the selected category, you can apply additional options to filter the available resources. For
example, you can filter by type, such as how-to articles, quick starts, or tutorials; these resources provide
the answers to common questions.
For information about Red Hat OpenShift AI support requirements and limitations, see Red Hat
OpenShift AI: Supported Configurations.
## NEXT STEPS
23

---

## Openshift Ai Working Data Science

Red Hat OpenShift AI Cloud Service 1
Working on data science projects
Organize your work in projects and workbenches.
Last Updated: 2025-10-28

Red Hat OpenShift AI Cloud Service 1 Working on data science projects
Organize your work in projects and workbenches.

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
Organize your work in projects and workbenches.

. . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 
. . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 
. . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 
. . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 
. . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 
. . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 
. . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 
Table of Contents
PREFACE
## USING DATA SCIENCE PROJECTS
### CREATING A DATA SCIENCE PROJECT
### UPDATING A DATA SCIENCE PROJECT
### DELETING A DATA SCIENCE PROJECT
## USING PROJECT WORKBENCHES
### CREATING A WORKBENCH AND SELECTING AN IDE
2.1.1. About workbench images
2.1.2. Creating a workbench
### STARTING A WORKBENCH
### UPDATING A PROJECT WORKBENCH
### DELETING A WORKBENCH FROM A DATA SCIENCE PROJECT
## USING CONNECTIONS
### ADDING A CONNECTION TO YOUR DATA SCIENCE PROJECT
### UPDATING A CONNECTION
### DELETING A CONNECTION
## CONFIGURING CLUSTER STORAGE
### ABOUT PERSISTENT STORAGE
4.1.1. Storage classes in OpenShift AI
4.1.2. Access modes
4.1.2.1. Using shared storage (RWX)
### ADDING CLUSTER STORAGE TO YOUR DATA SCIENCE PROJECT
### UPDATING CLUSTER STORAGE
### CHANGING THE STORAGE CLASS FOR AN EXISTING CLUSTER STORAGE INSTANCE
### DELETING CLUSTER STORAGE FROM A DATA SCIENCE PROJECT
## MANAGING ACCESS TO DATA SCIENCE PROJECTS
### GRANTING ACCESS TO A DATA SCIENCE PROJECT
### UPDATING ACCESS TO A DATA SCIENCE PROJECT
### REMOVING ACCESS TO A DATA SCIENCE PROJECT
## CREATING PROJECT-SCOPED RESOURCES FOR YOUR PROJECT
3
4
4
5
5
7
7
7
10
15
15
16
17
17
18
19
20
20
20
20
21
21
22
24
26
28
28
29
30
32
Table of Contents
1

Red Hat OpenShift AI Cloud Service 1 Working on data science projects
2

PREFACE
As a data scientist, you can organize your data science work into a single project. A data science project
in OpenShift AI can consist of the following components:
Workbenches
Creating a workbench allows you to work with models in your preferred IDE, such as JupyterLab.
Cluster storage
For data science projects that require data retention, you can add cluster storage to the project.
Connections
Adding a connection to your project allows you to connect data inputs to your workbenches.
Pipelines
Standardize and automate machine learning workflows to enable you to further enhance and deploy
your data science models.
Models and model servers
Deploy a trained data science model to serve intelligent applications. Your model is deployed with an
endpoint that allows applications to send requests to the model.
PREFACE
3
## USING DATA SCIENCE PROJECTS
### CREATING A DATA SCIENCE PROJECT
To implement a data science workflow, you must create a project. In OpenShift, a project is a Kubernetes
namespace with additional annotations, and is the main way that you can manage user access to
resources. A project organizes your data science work in one place and also allows you to collaborate
with other developers and data scientists in your organization.
Within a project, you can add the following functionality:
Connections so that you can access data without having to hardcode information like endpoints
or credentials.
Workbenches for working with and processing data, and for developing models.
Deployed models so that you can test them and then integrate them into intelligent
applications. Deploying a model makes it available as a service that you can access by using an
API.
Pipelines for automating your ML workflow.
Prerequisites
You have logged in to Red Hat OpenShift AI.
You have the appropriate roles and permissions to create projects.
Procedure
1. From the OpenShift AI dashboard, select Data science projects.
The Data science projects page shows a list of projects that you can access. For each user-
requested project in the list, the Name column shows the project display name, the user who
requested the project, and the project description.
2. Click Create project.
3. In the Create project dialog, update the Name field to enter a unique display name for your
project.
4. Optional: If you want to change the default resource name for your project, click Edit resource
name.
The resource name is what your resource is labeled in OpenShift. Valid characters include
lowercase letters, numbers, and hyphens (-). The resource name cannot exceed 30 characters,
and it must start with a letter and end with a letter or number.
Note: You cannot change the resource name after the project is created. You can edit only the
display name and the description.
5. Optional: In the Description field, provide a project description.
6. Click Create.
Verification
Red Hat OpenShift AI Cloud Service 1 Working on data science projects
4

A project details page opens. From this page, you can add connections, create workbenches,
configure pipelines, and deploy models.
### UPDATING A DATA SCIENCE PROJECT
You can update the project details by changing the project name and description.
Prerequisites
You have logged in to Red Hat OpenShift AI.
You have created a data science project.
Procedure
1. From the OpenShift AI dashboard, click Data science projects.
The Data science projects page opens.
2. Click the action menu (⋮) beside the project whose details you want to update and click Edit
project.
The Edit project dialog opens.
3. Optional: Edit the Name field to change the display name for your project.
4. Optional: Edit the Description field to change the description of your project.
5. Click Update.
Verification
You can see the updated project details on the Data science projects page.
### DELETING A DATA SCIENCE PROJECT
You can delete data science projects so that they do not appear on the OpenShift AI Data science
projects page when you no longer want to use them.
Prerequisites
You have logged in to Red Hat OpenShift AI.
You have created a data science project.
Procedure
1. From the OpenShift AI dashboard, click Data science projects.
The Data science projects page opens.
2. Click the action menu (⋮) beside the project that you want to delete and then click Delete
project.
The Delete project dialog opens.
3. Enter the project name in the text field to confirm that you intend to delete it.
## USING DATA SCIENCE PROJECTS
5

4. Click Delete project.
Verification
The data science project that you deleted is no longer displayed on the Data science projects
page.
Deleting a data science project deletes any associated workbenches, data science pipelines,
cluster storage, and connections. This data is permanently deleted and is not recoverable.
Red Hat OpenShift AI Cloud Service 1 Working on data science projects
6
## USING PROJECT WORKBENCHES
### CREATING A WORKBENCH AND SELECTING AN IDE
A workbench is an isolated area where you can examine and work with ML models. You can also work
with data and run programs, for example to prepare and clean data. While a workbench is not required if,
for example, you only want to service an existing model, one is needed for most data science workflow
tasks, such as writing code to process data or training a model.
When you create a workbench, you specify an image (an IDE, packages, and other dependencies).
Supported IDEs include JupyterLab, code-server, and RStudio (Technology Preview).
The IDEs are based on a server-client architecture. Each IDE provides a server that runs in a container
on the OpenShift cluster, while the user interface (the client) is displayed in your web browser. For
example, the Jupyter workbench runs in a container on the Red Hat OpenShift cluster. The client is the
JupyterLab interface that opens in your web browser on your local computer. All of the commands that
you enter in JupyterLab are executed by the workbench. Similarly, other IDEs like code-server or
RStudio Server provide a server that runs in a container on the OpenShift cluster, while the user
interface is displayed in your web browser. This architecture allows you to interact through your local
computer in a browser environment, while all processing occurs on the cluster. The cluster provides the
benefits of larger available resources and security because the data being processed never leaves the
cluster.
In a workbench, you can also configure connections (to access external data for training models and to
save models so that you can deploy them) and cluster storage (for persisting data). Workbenches within
the same project can share models and data through object storage with the data science pipelines and
model servers.
For data science projects that require data retention, you can add container storage to the workbench
you are creating.
Within a project, you can create multiple workbenches. When to create a new workbench depends on
considerations, such as the following:
The workbench configuration (for example, CPU, RAM, or IDE). If you want to avoid editing the
configuration of an existing workbench’s configuration to accommodate a new task, you can
create a new workbench instead.
Separation of tasks or activities. For example, you might want to use one workbench for your
Large Language Models (LLM) experimentation activities, another workbench dedicated to a
demo, and another workbench for testing.
2.1.1. About workbench images
A workbench image is preinstalled with the tools and libraries that you need for model development. You
can use the provided workbench images or an OpenShift AI administrator can create custom workbench
images adapted to your needs.
To provide a consistent, stable platform for your model development, many provided workbench images
contain the same version of Python. Most workbench images available on OpenShift AI are pre-built and
ready for you to use immediately after OpenShift AI is installed or upgraded.
For information about Red Hat support of workbench images and packages, see Red Hat OpenShift AI:
Supported Configurations.
## USING PROJECT WORKBENCHES
7

The following table lists the workbench images that are installed with Red Hat OpenShift AI by default.
If the preinstalled packages that are provided in these images are not sufficient for your use case, you
have the following options:
Install additional libraries after launching a default image. This option is good if you want to add
libraries on an ad hoc basis as you develop models. However, it can be challenging to manage
the dependencies of installed libraries and your changes are not saved when the workbench
restarts.
Create a custom image that includes the additional libraries or packages. For more information,
see Creating custom workbench images.
IMPORTANT
Workbench images denoted with (Technology Preview) in this table are not supported
with Red Hat production service level agreements (SLAs) and might not be functionally
complete. Red Hat does not recommend using Technology Preview features in
production. These features provide early access to upcoming product features, enabling
customers to test functionality and provide feedback during the development process.
For more information about the support scope of Red Hat Technology Preview features,
see Technology Preview Features Support Scope .
Table 2.1. Default workbench images
Image name
Description
CUDA
If you are working with compute-intensive data science models that require GPU support,
use the Compute Unified Device Architecture (CUDA) workbench image to gain access to
the NVIDIA CUDA Toolkit. Using this toolkit, you can optimize your work by using GPU-
accelerated libraries and optimization tools.
Standard Data
Science
Use the Standard Data Science workbench image for models that do not require
TensorFlow or PyTorch. This image contains commonly-used libraries to assist you in
developing your machine learning models.
TensorFlow
TensorFlow is an open source platform for machine learning. With TensorFlow, you can
build, train and deploy your machine learning models. TensorFlow contains advanced data
visualization features, such as computational graph visualizations. It also allows you to
easily monitor and track the progress of your models.
PyTorch
PyTorch is an open source machine learning library optimized for deep learning. If you are
working with computer vision or natural language processing models, use the Pytorch
workbench image.
Minimal Python
If you do not require advanced machine learning features, or additional resources for
compute-intensive data science work, you can use the Minimal Python image to develop
your models.
TrustyAI
Use the TrustyAI workbench image to leverage your data science work with model
explainability, tracing, and accountability, and runtime monitoring. See the TrustyAI
Explainability repository for some example Jupyter notebooks.
Red Hat OpenShift AI Cloud Service 1 Working on data science projects
8

code-server
With the code-server workbench image, you can customize your workbench environment
to meet your needs using a variety of extensions to add new languages, themes,
debuggers, and connect to additional services. Enhance the efficiency of your data science
work with syntax highlighting, auto-indentation, and bracket matching, as well as an
automatic task runner for seamless automation. For more information, see code-server in
GitHub.
NOTE: Elyra-based pipelines are not available with the code-server workbench image.
RStudio Server
(Technology
preview)
Use the RStudio Server workbench image to access the RStudio IDE, an integrated
development environment for R, a programming language for statistical computing and
graphics. For more information, see the RStudio Server site.
To use the RStudio Server workbench image, you must first build it by creating a secret
and triggering the BuildConfig, and then enable it in the OpenShift AI UI by editing the 
rstudio-rhel9 image stream. For more information, see Building the RStudio Server
workbench images.
IMPORTANT
Disclaimer:
Red Hat supports managing workbenches in OpenShift AI. However,
Red Hat does not provide support for the RStudio software. RStudio
Server is available through https://rstudio.org/ and is subject to RStudio
licensing terms. Review the licensing terms before you use this sample
workbench.
Image name
Description
## USING PROJECT WORKBENCHES
9

CUDA -
RStudio Server
(Technology
Preview)
Use the CUDA - RStudio Server workbench image to access the RStudio IDE and NVIDIA
CUDA Toolkit. RStudio is an integrated development environment for R, a programming
language for statistical computing and graphics. With the NVIDIA CUDA toolkit, you can
optimize your work using GPU-accelerated libraries and optimization tools. For more
information, see the RStudio Server site.
To use the CUDA - RStudio Server workbench image, you must first build it by creating a
secret and triggering the BuildConfig, and then enable it in the OpenShift AI UI by editing
the cuda-rstudio-rhel9 image stream. For more information, see Building the RStudio
Server workbench images.
IMPORTANT
Disclaimer:
Red Hat supports managing workbenches in OpenShift AI. However,
Red Hat does not provide support for the RStudio software. RStudio
Server is available through https://rstudio.org/ and is subject to RStudio
licensing terms. Review the licensing terms before you use this sample
workbench.
The CUDA - RStudio Server workbench image contains NVIDIA CUDA
technology. CUDA licensing information is available at
https://docs.nvidia.com/cuda/. Review the licensing terms before you use
this sample workbench.
ROCm
Use the ROCm workbench image to run AI and machine learning workloads on AMD GPUs
in OpenShift AI. It includes ROCm libraries and tools optimized for high-performance GPU
acceleration, supporting custom AI workflows and data processing tasks. Use this image
integrating additional frameworks or dependencies tailored to your specific AI
development needs.
ROCm-
PyTorch
Use the ROCm-PyTorch workbench image to run PyTorch workloads on AMD GPUs in
OpenShift AI. It includes ROCm-accelerated PyTorch libraries, enabling efficient deep
learning training, inference, and experimentation. This image is designed for data scientists
working with PyTorch-based workflows, offering integration with GPU scheduling.
ROCm-
TensorFlow
Use the ROCm-TensorFlow workbench image to run TensorFlow workloads on AMD GPUs
in OpenShift AI. It includes ROCm-accelerated TensorFlow libraries to support high-
performance deep learning model training and inference. This image simplifies TensorFlow
development on AMD GPUs and integrates with OpenShift AI for resource scaling and
management.
Image name
Description
2.1.2. Creating a workbench
When you create a workbench, you specify an image (an IDE, packages, and other dependencies). You
can also configure connections, cluster storage, and add container storage.
Prerequisites
You have logged in to Red Hat OpenShift AI.
Red Hat OpenShift AI Cloud Service 1 Working on data science projects
10

You have created a project.
If you created a Simple Storage Service (S3) account outside of Red Hat OpenShift AI and you
want to create connections to your existing S3 storage buckets, you have the following
credential information for the storage buckets:
Endpoint URL
Access key
Secret key
Region
Bucket name
For more information, see Working with data in an S3-compatible object store .
Procedure
1. From the OpenShift AI dashboard, click Data science projects.
The Data science projects page opens.
2. Click the name of the project that you want to add the workbench to.
A project details page opens.
3. Click the Workbenches tab.
4. Click Create workbench.
The Create workbench page opens.
5. In the Name field, enter a unique name for your workbench.
6. Optional: If you want to change the default resource name for your workbench, click Edit
resource name.
The resource name is what your resource is labeled in OpenShift. Valid characters include
lowercase letters, numbers, and hyphens (-). The resource name cannot exceed 30 characters,
and it must start with a letter and end with a letter or number.
Note: You cannot change the resource name after the workbench is created. You can edit only
the display name and the description.
7. Optional: In the Description field, enter a description for your workbench.
8. In the Workbench image section, complete the fields to specify the workbench image to use
with your workbench.
From the Image selection list, select a workbench image that suits your use case. A workbench
image includes an IDE and Python packages (reusable code). If project-scoped images exist,
the Image selection list includes subheadings to distinguish between global images and
project-scoped images.
Optionally, click View package information to view a list of packages that are included in the
image that you selected.
If the workbench image has multiple versions available, select the workbench image version to
use from the Version selection list. To use the latest package versions, Red Hat recommends
that you use the most recently added image.
## USING PROJECT WORKBENCHES
11

NOTE
You can change the workbench image after you create the workbench.
9. In the Deployment size section, select one of the following options, depending on whether the
hardware profiles feature is enabled.
IMPORTANT
The hardware profiles feature is currently available in Red Hat OpenShift AI as a
Technology Preview feature. Technology Preview features are not supported
with Red Hat production service level agreements (SLAs) and might not be
functionally complete. Red Hat does not recommend using them in production.
These features provide early access to upcoming product features, enabling
customers to test functionality and provide feedback during the development
process.
For more information about the support scope of Red Hat Technology Preview
features, see Technology Preview Features Support Scope .
If the hardware profiles feature is not enabled:
a. From the Container size list, select the appropriate size for the size of the model that
you want to train or tune.
For example, to run the example fine-tuning job described in Fine-tuning a model by
using Kubeflow Training, select Medium.
b. From the Accelerator list, select a suitable accelerator profile for your workbench.
If project-scoped accelerator profiles exist, the Accelerator list includes subheadings
to distinguish between global accelerator profiles and project-scoped accelerator
profiles.
If the hardware profiles feature is enabled:
a. From the Hardware profile list, select a suitable hardware profile for your workbench.
If project-scoped hardware profiles exist, the Hardware profile list includes
subheadings to distinguish between global hardware profiles and project-scoped
hardware profiles.
The hardware profile specifies the number of CPUs and the amount of memory
allocated to the container, setting the guaranteed minimum (request) and maximum
(limit) for both.
b. If you want to change the default values, click Customize resource requests and limit
and enter new minimum (request) and maximum (limit) values.
Red Hat OpenShift AI Cloud Service 1 Working on data science projects
12

IMPORTANT
By default, the hardware profiles feature is not enabled: hardware
profiles are not shown in the dashboard navigation menu or elsewhere in
the user interface. In addition, user interface components associated
with the deprecated accelerator profiles functionality are still displayed.
To show the Settings → Hardware profiles option in the dashboard
navigation menu, and the user interface components associated with
hardware profiles, set the disableHardwareProfiles value to false in the
OdhDashboardConfig custom resource (CR) in OpenShift. For more
information about setting dashboard configuration options, see
Customizing the dashboard.
10. Optional: In the Environment variables section, select and specify values for any environment
variables.
Setting environment variables during the workbench configuration helps you save time later
because you do not need to define them in the body of your workbenches, or with the IDE
command line interface.
If you are using S3-compatible storage, add these recommended environment variables:
AWS_ACCESS_KEY_ID specifies your Access Key ID for Amazon Web Services.
AWS_SECRET_ACCESS_KEY specifies your Secret access key for the account specified
in AWS_ACCESS_KEY_ID.
OpenShift AI stores the credentials as Kubernetes secrets in a protected namespace if you
select Secret when you add the variable.
11. In the Cluster storage section, configure the storage for your workbench. Select one of the
following options:
Create new persistent storage to create storage that is retained after you shut down your
workbench. Complete the relevant fields to define the storage:
a. Enter a name for the cluster storage.
b. Enter a description for the cluster storage.
c. Select a storage class for the cluster storage.
NOTE
You cannot change the storage class after you add the cluster storage to
the workbench.
d. For storage classes that support multiple access modes, select an Access mode to
define how the volume can be accessed. For more information, see About persistent
storage.
Only the access modes that have been enabled for the storage class by your cluster and
OpenShift AI administrators are visible.
e. Under Persistent storage size, enter a new size in gibibytes or mebibytes.
Use existing persistent storage to reuse existing storage and select the storage from the
Persistent storage list.
## USING PROJECT WORKBENCHES
13

12. Optional: You can add a connection to your workbench. A connection is a resource that contains
the configuration parameters needed to connect to a data source or sink, such as an object
storage bucket. You can use storage buckets for storing data, models, and pipeline artifacts.
You can also use a connection to specify the location of a model that you want to deploy.
In the Connections section, use an existing connection or create a new connection:
Use an existing connection as follows:
a. Click Attach existing connections.
b. From the Connection list, select a connection that you previously defined.
Create a new connection as follows:
a. Click Create connection. The Add connection dialog opens.
b. From the Connection type drop-down list, select the type of connection. The
Connection details section is displayed.
c. If you selected S3 compatible object storage in the preceding step, configure the
connection details:
i. In the Connection name field, enter a unique name for the connection.
ii. Optional: In the Description field, enter a description for the connection.
iii. In the Access key field, enter the access key ID for the S3-compatible object
storage provider.
iv. In the Secret key field, enter the secret access key for the S3-compatible object
storage account that you specified.
v. In the Endpoint field, enter the endpoint of your S3-compatible object storage
bucket.
vi. In the Region field, enter the default region of your S3-compatible object storage
account.
vii. In the Bucket field, enter the name of your S3-compatible object storage bucket.
viii. Click Create.
d. If you selected URI in the preceding step, configure the connection details:
i. In the Connection name field, enter a unique name for the connection.
ii. Optional: In the Description field, enter a description for the connection.
iii. In the URI field, enter the Uniform Resource Identifier (URI).
iv. Click Create.
13. Click Create workbench.
Verification
The workbench that you created is visible on the Workbenches tab for the project.
Red Hat OpenShift AI Cloud Service 1 Working on data science projects
14

Any cluster storage that you associated with the workbench during the creation process is
displayed on the Cluster storage tab for the project.
The Status column on the Workbenches tab displays a status of Starting when the workbench
server is starting, and Running when the workbench has successfully started.
Optional: Click the open icon ( 
 ) to open the IDE in a new window.
### STARTING A WORKBENCH
You can manually start a data science project’s workbench from the Workbenches tab on the project
details page. By default, workbenches start immediately after you create them.
Prerequisites
You have logged in to Red Hat OpenShift AI.
You have created a data science project that contains a workbench.
Procedure
1. From the OpenShift AI dashboard, click Data science projects.
The Data science projects page opens.
2. Click the name of the project whose workbench you want to start.
A project details page opens.
3. Click the Workbenches tab.
4. In the Status column for the workbench that you want to start, click Start.
The Status column changes from Stopped to Starting when the workbench server is starting,
and then to Running when the workbench has successfully started. * Optional: Click the open
icon ( 
 ) to open the IDE in a new window.
Verification
The workbench that you started is displayed on the Workbenches tab for the project, with the
status of Running.
### UPDATING A PROJECT WORKBENCH
If your data science work requires you to change your workbench image, container size, or identifying
information, you can update the properties of your project’s workbench. If you require extra power for
use with large datasets, you can assign accelerators to your workbench to optimize performance.
Prerequisites
You have logged in to Red Hat OpenShift AI.
You have created a data science project that has a workbench.
Procedure
## USING PROJECT WORKBENCHES
15

1. From the OpenShift AI dashboard, click Data science projects.
The Data science projects page opens.
2. Click the name of the project whose workbench you want to update.
A project details page opens.
3. Click the Workbenches tab.
4. Click the action menu (⋮) beside the workbench that you want to update and then click Edit
workbench.
The Edit workbench page opens.
5. Update any of the workbench properties and then click Update workbench.
Verification
The workbench that you updated is displayed on the Workbenches tab for the project.
### DELETING A WORKBENCH FROM A DATA SCIENCE PROJECT
You can delete workbenches from your data science projects to help you remove Jupyter notebooks
that are no longer relevant to your work.
Prerequisites
You have logged in to Red Hat OpenShift AI.
You have created a data science project with a workbench.
Procedure
1. From the OpenShift AI dashboard, click Data science projects.
The Data science projects page opens.
2. Click the name of the project that you want to delete the workbench from.
A project details page opens.
3. Click the Workbenches tab.
4. Click the action menu (⋮) beside the workbench that you want to delete and then click Delete
workbench.
The Delete workbench dialog opens.
5. Enter the name of the workbench in the text field to confirm that you intend to delete it.
6. Click Delete workbench.
Verification
The workbench that you deleted is no longer displayed on the Workbenches tab for the project.
The custom resource (CR) associated with the workbench’s Jupyter notebook is deleted.
Red Hat OpenShift AI Cloud Service 1 Working on data science projects
16
## USING CONNECTIONS
### ADDING A CONNECTION TO YOUR DATA SCIENCE PROJECT
You can enhance your data science project by adding a connection that contains the configuration
parameters needed to connect to a data source or sink.
When you want to work with a very large data sets, you can store your data in an Open Container
Initiative (OCI)-compliant registry, S3-compatible object storage bucket, or a URI-based repository, so
that you do not fill up your local storage. You also have the option of associating the connection with an
existing workbench that does not already have a connection.
Prerequisites
You have logged in to Red Hat OpenShift AI.
You have created a data science project that you can add a connection to.
You have access to S3-compatible object storage, URI-based repository, or OCI-compliant
registry.
If you intend to add the connection to an existing workbench, you have saved any data in the
workbench to avoid losing work.
Procedure
1. From the OpenShift AI dashboard, click Data science projects.
The Data science projects page opens.
2. Click the name of the project that you want to add a connection to.
A project details page opens.
3. Click the Connections tab.
4. Click Add connection.
5. In the Add connection modal, select a Connection type. The OCI-compliant registry, S3
compatible object storage, and URI options are pre-installed connection types. Additional
options might be available if your OpenShift AI administrator added them.
The Add connection form opens with fields specific to the connection type that you selected.
6. Enter a unique name for the connection.
A resource name is generated based on the name of the connection. A resource name is the
label for the underlying resource in OpenShift.
7. Optional: Edit the default resource name. Note that you cannot change the resource name
after you create the connection.
8. Optional: Provide a description of the connection.
9. Complete the form depending on the connection type that you selected. For example:
a. If you selected S3 compatible object storage as the connection type, configure the
connection details:
## USING CONNECTIONS
17

i. In the Access key field, enter the access key ID for the S3-compatible object storage
provider.
ii. In the Secret key field, enter the secret access key for the S3-compatible object
storage account that you specified.
iii. In the Endpoint field, enter the endpoint of your S3-compatible object storage bucket.
NOTE
Make sure to use the appropriate endpoint format. Improper formatting
might cause connection errors or restrict access to storage resources.
For more information about how to format object storage endpoints, see
Overview of object storage endpoints.
iv. In the Region field, enter the default region of your S3-compatible object storage
account.
v. In the Bucket field, enter the name of your S3-compatible object storage bucket.
vi. Click Create.
b. If you selected URI in the preceding step, in the URI field, enter the Uniform Resource
Identifier (URI).
c. If you selected OCI-compliant registry in the preceding step, in the OCI storage location
field, enter the URI.
10. Click Add connection.
Verification
The connection that you added is displayed on the Connections tab for the project.
### UPDATING A CONNECTION
You can edit the configuration of an existing connection as described in this procedure.
NOTE
Any changes that you make to a connection are not applied to dependent resources (for
example, a workbench) until those resources are restarted, redeployed, or otherwise
regenerated.
Prerequisites
You have logged in to Red Hat OpenShift AI.
You have created a data science project, created a workbench, and you have defined a
connection.
Procedure
1. From the OpenShift AI dashboard, click Data science projects.
Red Hat OpenShift AI Cloud Service 1 Working on data science projects
18

The Data science projects page opens.
2. Click the name of the project that contains the connection that you want to change.
A project details page opens.
3. Click the Connections tab.
4. Click the action menu (⋮) beside the connection that you want to change and then click Edit.
The Edit connection form opens.
5. Make your changes.
6. Click Save.
Verification
The updated connection is displayed on the Connections tab for the project.
### DELETING A CONNECTION
You can delete connections that are no longer relevant to your data science project.
Prerequisites
You have logged in to Red Hat OpenShift AI.
You have created a data science project with a connection.
Procedure
1. From the OpenShift AI dashboard, click Data science projects.
The Data science projects page opens.
2. Click the name of the project that you want to delete the connection from.
A project details page opens.
3. Click the Connections tab.
4. Click the action menu (⋮) beside the connection that you want to delete and then click Delete
connection.
The Delete connection dialog opens.
5. Enter the name of the connection in the text field to confirm that you intend to delete it.
6. Click Delete connection.
Verification
The connection that you deleted is no longer displayed on the Connections page for the
project.
## USING CONNECTIONS
19
## CONFIGURING CLUSTER STORAGE
### ABOUT PERSISTENT STORAGE
OpenShift AI uses persistent storage to support workbenches, project data, and model training.
Persistent storage is provisioned through OpenShift storage classes and persistent volumes. Volume
provisioning and data access are determined by access modes.
Understanding storage classes and access modes can help you choose the right storage for your use
case and avoid potential risks when sharing data across multiple workbenches.
4.1.1. Storage classes in OpenShift AI
Storage classes in OpenShift AI are available from the underlying OpenShift cluster. A storage class
defines how persistent volumes are provisioned, including which storage backend is used and what
access modes the provisioned volumes can support. For more information, see Dynamic provisioning in
the OpenShift documentation.
Cluster administrators create and configure storage classes in the OpenShift cluster. These storage
classes provision persistent volumes that support one or more access modes, depending on the
capabilities of the storage backend. OpenShift AI administrators then enable specific storage classes
and access modes for use in OpenShift AI.
When adding cluster storage to your project or workbench, you can choose from any enabled storage
classes and access modes.
4.1.2. Access modes
Storage classes create persistent volumes that can support different access modes, depending on the
storage backend. Access modes control how a volume can be mounted and used by one or more
workbenches. If a storage class allows more than one access mode, you can select the one that best fits
your needs when you request storage. All persistent volumes support ReadWriteOnce (RWO) by
default.
Access mode
Description
ReadWriteOnce 
(RWO) (Default)
The storage can be attached to a single workbench or pod at a time and is ideal
for most individual workloads. RWO is always enabled by default and cannot be
disabled by the administrator.
ReadWriteMany 
(RWX)
The storage can be attached to many workbenches simultaneously. RWX enables
shared data access, but can introduce data risks.
ReadOnlyMany 
(ROX)
The storage can be attached to many workbenches as read-only. ROX is useful
for sharing reference data without allowing changes.
ReadWriteOncePod 
(RWOP)
The storage can be attached to a single pod on a single node with read-write
permissions. RWOP is similar to RWO but includes additional node-level
restrictions.
Red Hat OpenShift AI Cloud Service 1 Working on data science projects
20

NOTE
You can enable access modes that are required. A warning is displayed if you request an
access mode with unknown support, but you can continue to select Save to create the
storage class with the selected access mode.
4.1.2.1. Using shared storage (RWX)
The ReadWriteMany (RWX) access mode allows multiple workbenches to access and write to the same
storage volume at the same time. Use RWX access mode for collaborative work where multiple users
need to access shared datasets or project files.
However, shared storage introduces several risks:
Data corruption or data loss: If multiple workbenches modify the same part of a file
simultaneously, the data can become corrupted or lost. Ensure your applications or workflows
are designed to safely handle shared access, for example, by using file locking or database
transactions.
Security and privacy: If a workbench with access to shared storage is compromised, all data on
that volume might be at risk. Only share sensitive data with trusted workbenches and users.
To use shared storage safely:
Ensure that your tools or workflows are designed to work with shared storage and can manage
simultaneous writes. For example, use databases or distributed data processing frameworks.
Be cautious with changes. Deleting or editing files affects everyone who shares the volume.
Back up your data regularly, which can help prevent data loss due to mistakes or
misconfigurations.
Limit access to RWX volumes to trusted users and secure workbenches.
Use ReadWriteMany (RWX) only when collaboration on a shared volume is required. For most
individual tasks, ReadWriteOnce (RWO) is ideal because only one workbench can write to the
volume at a time.
### ADDING CLUSTER STORAGE TO YOUR DATA SCIENCE PROJECT
For data science projects that require data to be retained, you can add cluster storage to the project.
Additionally, you can also connect cluster storage to a specific project’s workbench.
Prerequisites
You have logged in to Red Hat OpenShift AI.
You have created a data science project that you can add cluster storage to.
Procedure
1. From the OpenShift AI dashboard, click Data science projects.
The Data science projects page opens.
2. Click the name of the project that you want to add the cluster storage to.
## CONFIGURING CLUSTER STORAGE
21

A project details page opens.
3. Click the Cluster storage tab.
4. Click Add cluster storage.
The Add cluster storage dialog opens.
5. In the Name field, enter a unique name for the cluster storage.
6. Optional: In the Description field, enter a description for the cluster storage.
7. From the Storage class list, select the type of cluster storage.
NOTE
You cannot change the storage class after you add the cluster storage to the
project.
8. For storage classes that support multiple access modes, select an Access mode to define how
the volume can be accessed. For more information, see About persistent storage.
Only the access modes that have been enabled for the storage class by your cluster and
OpenShift AI administrators are visible.
9. In the Persistent storage size section, specify a new size in gibibytes or mebibytes.
10. Optional: If you want to connect the cluster storage to an existing workbench:
a. In the Workbench connections section, click Add workbench.
b. In the Name field, select an existing workbench from the list.
c. In the Path format field, select Standard if your storage directory begins with /opt/app-
root/src, otherwise select Custom.
d. In the Mount path field, enter the path to a model or directory within a container where a
volume is mounted and accessible. The path must consist of lowercase alphanumeric
characters or -. Use / to indicate subdirectories.
11. Click Add storage.
Verification
The cluster storage that you added is displayed on the Cluster storage tab for the project.
A new persistent volume claim (PVC) is created with the storage size that you defined.
The persistent volume claim (PVC) is visible as an attached storage on the Workbenches tab
for the project.
### UPDATING CLUSTER STORAGE
If your data science work requires you to change the identifying information of a project’s cluster
storage or the workbench that the storage is connected to, you can update your project’s cluster
storage to change these properties.
Red Hat OpenShift AI Cloud Service 1 Working on data science projects
22

NOTE
You cannot directly change the storage class for cluster storage that is already
configured for a workbench or project. To switch to a different storage class, you need to
migrate your data to a new cluster storage instance that uses the required storage class.
For more information, see Changing the storage class for an existing cluster storage
instance.
Prerequisites
You have logged in to Red Hat OpenShift AI.
You have created a data science project that contains cluster storage.
Procedure
1. From the OpenShift AI dashboard, click Data science projects.
The Data science projects page opens.
2. Click the name of the project whose storage you want to update.
A project details page opens.
3. Click the Cluster storage tab.
4. Click the action menu (⋮) beside the storage that you want to update and then click Edit
storage.
The Update cluster storage page opens.
5. Optional: Edit the Name field to change the display name for your storage.
6. Optional: Edit the Description field to change the description of your storage.
7. Optional: In the Persistent storage size section, specify a new size in gibibytes or mebibytes.
Note that you can only increase the storage size. Updating the storage size restarts the
workbench and makes it unavailable for a period of time that is usually proportional to the size
change.
8. Optional: If you want to connect the cluster storage to a different workbench:
a. In the Workbench connections section, click Add workbench.
b. In the Name field, select an existing workbench from the list.
c. In the Path format field, select Standard if your storage directory begins with /opt/app-
root/src, otherwise select Custom.
d. In the Mount path field, enter the path to a model or directory within a container where a
volume is mounted and accessible. The path must consist of lowercase alphanumeric
characters or -. Use / to indicate subdirectories.
9. Click Update storage.
If you increased the storage size, the workbench restarts and is unavailable for a period of time that is
usually proportional to the size change.
Verification
## CONFIGURING CLUSTER STORAGE
23

The storage that you updated is displayed on the Cluster storage tab for the project.
### CHANGING THE STORAGE CLASS FOR AN EXISTING CLUSTER
STORAGE INSTANCE
When you create a workbench with cluster storage, the cluster storage is tied to a specific storage class.
Later, if your data science work requires a different storage class, or if the current storage class has been
deprecated, you cannot directly change the storage class on the existing cluster storage instance.
Instead, you must migrate your data to a new cluster storage instance that uses the storage class that
you want to use.
Prerequisites
You have logged in to Red Hat OpenShift AI.
You have created a workbench or data science project that contains cluster storage.
Procedure
1. Stop the workbench with the storage class that you want to change.
a. From the OpenShift AI dashboard, click Data science projects.
The Data science projects page opens.
b. Click the name of the project with the cluster storage instance that uses the storage class
you want to change.
The project details page opens.
c. Click the Workbenches tab.
d. In the Status column for the relevant workbench, click Stop.
Wait until the Status column for the relevant workbench changes from Running to
Stopped.
2. Add a new cluster storage instance that uses the needed storage class.
a. Click the Cluster storage tab.
b. Click Add cluster storage.
The Add cluster storage dialog opens.
c. Enter a name for the cluster storage.
d. Optional: Enter a description for the cluster storage.
e. Select the needed storage class for the cluster storage.
f. For storage classes that support multiple access modes, select an Access mode to define
how the volume can be accessed. For more information, see About persistent storage.
Only the access modes that have been enabled for the storage class by your cluster and
OpenShift AI administrators are visible.
g. Under Persistent storage size, enter a size in gibibytes or mebibytes.
h. In the Workbench connections section, click Add workbench.
Red Hat OpenShift AI Cloud Service 1 Working on data science projects
24

i. In the Name field, select an existing workbench from the list.
j. In the Path format field, select Standard if your storage directory begins with /opt/app-
root/src, otherwise select Custom.
k. In the Mount path field, enter the path to a model or directory within a container where a
volume is mounted and accessible. For example, backup.
l. Click Add storage.
3. Copy the data from the existing cluster storage instance to the new cluster storage instance.
a. Click the Workbenches tab.
b. In the Status column for the relevant workbench, click Start.
c. When the workbench status is Running, click Open to open the workbench.
d. In JupyterLab, click File → New → Terminal.
e. Copy the data to the new storage directory. Replace <mount_folder_name> with the storage
directory of your new cluster storage instance.
rsync -avO --exclude='/opt/app-root/src/__<mount_folder_name>__' /opt/app-root/src/ 
/opt/app-root/src/__<mount_folder_name>__/
For example:
rsync -avO --exclude='/opt/app-root/src/backup' /opt/app-root/src/ /opt/app-
root/src/backup/
f. After the data has finished copying, log out of JupyterLab.
4. Stop the workbench.
a. Click the Workbenches tab.
b. In the Status column for the relevant workbench, click Stop.
Wait until the Status column for the relevant workbench changes from Running to
Stopped.
5. Remove the original cluster storage instance from the workbench.
a. Click the Cluster storage tab.
b. Click the action menu (⋮) beside the existing cluster storage instance, and then click Edit
storage.
c. Under Existing connected workbenches, remove the workbench.
d. Click Update.
6. Update the mount folder of the new cluster storage instance by removing it and re-adding it to
the workbench.
a. On the Cluster storage tab, click the action menu ( ⋮) beside the new cluster storage
instance, and then click Edit storage.
## CONFIGURING CLUSTER STORAGE
25

b. Under Existing connected workbenches, remove the workbench.
c. Click Update.
d. Click the Workbenches tab.
e. Click the action menu (⋮) beside the workbench and then click Edit workbench.
f. In the Cluster storage section, under Use existing persistent storage, select the new
cluster storage instance.
g. Click Update workbench.
7. Restart the workbench.
a. Click the Workbenches tab.
b. In the Status column for the relevant workbench, click Start.
8. Optional: The initial cluster storage that uses the previous storage class is still visible on the
Cluster storage tab. If you no longer need this cluster storage (for example, if the storage class
is deprecated), you can delete it.
9. Optional: You can delete the mount folder of your new cluster storage instance (for example,
the backup folder).
Verification
On the Cluster storage tab for the project, the new cluster storage instance is displayed with
the needed storage class in the Storage class column and the relevant workbench in the
Connected workbenches column.
On the Workbenches tab for the project, the new cluster storage instance is displayed for the
workbench in the Cluster storage section and has the mount path: /opt/app-root/src.
### DELETING CLUSTER STORAGE FROM A DATA SCIENCE PROJECT
You can delete cluster storage from your data science projects to help you free up resources and delete
unwanted storage space.
Prerequisites
You have logged in to Red Hat OpenShift AI.
You have created a data science project with cluster storage.
Procedure
1. From the OpenShift AI dashboard, click Data science projects.
The Data science projects page opens.
2. Click the name of the project that you want to delete the storage from.
A project details page opens.
3. Click the Cluster storage tab.
Red Hat OpenShift AI Cloud Service 1 Working on data science projects
26

4. Click the action menu (⋮) beside the storage that you want to delete and then click Delete
storage.
The Delete storage dialog opens.
5. Enter the name of the storage in the text field to confirm that you intend to delete it.
6. Click Delete storage.
Verification
The storage that you deleted is no longer displayed on the Cluster storage tab for the project.
The persistent volume (PV) and persistent volume claim (PVC) associated with the cluster
storage are both permanently deleted. This data is not recoverable.
## CONFIGURING CLUSTER STORAGE
27
## MANAGING ACCESS TO DATA SCIENCE
PROJECTS
### GRANTING ACCESS TO A DATA SCIENCE PROJECT
To enable your organization to work collaboratively, you can grant access to your data science project to
other users and groups.
Prerequisites
You have logged in to Red Hat OpenShift AI.
You have created a data science project.
Procedure
1. From the OpenShift AI dashboard, click Data science projects.
The Data science projects page opens.
2. From the list of data science projects, click the name of the data science project that you want
to grant access to.
A project details page opens.
3. Click the Permissions tab.
The Permissions page for the project opens.
4. Provide one or more users with access to the project.
a. In the Users section, click Add user.
b. In the Name field, enter the user name of the user whom you want to provide access to the
project.
c. From the Permissions list, select one of the following access permission levels:
Admin: Users with this access level can edit project details and manage access to the
project.
Contributor: Users with this access level can view and edit project components, such as
its workbenches, connections, and storage.
d. To confirm your entry, click Confirm ( 
 ).
e. Optional: To add an additional user, click Add user and repeat the process.
5. Provide one or more OpenShift groups with access to the project.
a. In the Groups section, click Add group.
b. From the Name list, select a group to provide access to the project.
Red Hat OpenShift AI Cloud Service 1 Working on data science projects
28

NOTE
If you do not have cluster-admin or dedicated-admin permissions, the
Name list is not visible. Instead, an input field is displayed enabling you to
configure group permissions.
c. From the Permissions list, select one of the following access permission levels:
Admin: Groups with this access level can edit project details and manage access to the
project.
Contributor: Groups with this access level can view and edit project components, such
as its workbenches, connections, and storage.
d. To confirm your entry, click Confirm ( 
 ).
e. Optional: To add an additional group, click Add group and repeat the process.
Verification
Users to whom you provided access to the project can perform only the actions permitted by
their access permission level.
The Users and Groups sections on the Permissions tab show the respective users and groups
that you granted access to.
### UPDATING ACCESS TO A DATA SCIENCE PROJECT
To change the level of collaboration on your data science project, you can update the access
permissions of users and groups who have access to your project.
Prerequisites
You have logged in to Red Hat OpenShift AI.
You have OpenShift AI administrator privileges or you are the project owner.
You have created a data science project.
You have previously shared access to your project with other users or groups.
Procedure
1. From the OpenShift AI dashboard, click Data science projects.
The Data science projects page opens.
2. Click the name of the project that you want to change the access permissions of.
A project details page opens.
3. Click the Permissions tab.
The Permissions page for the project opens.
4. Update the user access permissions to the project.
## MANAGING ACCESS TO DATA SCIENCE PROJECTS
29

a. Click the action menu (⋮) beside the user whose access permissions you want to update
and click Edit.
b. In the Name field, update the user name of the user whom you want to provide access to
the project.
c. From the Permissions list, update the user access permissions by selecting one of the
following:
Admin: Users with this access level can edit project details and manage access to the
project.
Contributor: Users with this access level can view and edit project components, such as
its workbenches, connections, and storage.
d. To confirm the update to the entry, click Confirm ( 
 ).
5. Update the OpenShift groups access permissions to the project.
a. Click the action menu (⋮) beside the group whose access permissions you want to update
and click Edit.
b. From the Name list, update the group that has access to the project by selecting another
group from the list.
NOTE
If you do not have cluster-admin or dedicated-admin permissions, the
Name list is not visible. Instead, an input field displays enabling you to
configure group permissions.
c. From the Permissions list, update the group access permissions by selecting one of the
following:
Admin: Groups with this access level can edit project details and manage access to the
project.
Contributor: Groups with this access level can view and edit project components, such
as its workbenches, connections, and storage.
d. To confirm the update to the entry, click Confirm ( 
 ).
Verification
The Users and Groups sections on the Permissions tab show the respective users and groups
whose project access permissions you changed.
### REMOVING ACCESS TO A DATA SCIENCE PROJECT
If you no longer want to work collaboratively on your data science project, you can restrict access to your
project by removing users and groups that you previously provided access to your project.
Prerequisites
Red Hat OpenShift AI Cloud Service 1 Working on data science projects
30

You have logged in to Red Hat OpenShift AI.
You have OpenShift AI administrator privileges or you are the project owner.
You have created a data science project.
You have previously shared access to your project with other users or groups.
Procedure
1. From the OpenShift AI dashboard, click Data science projects.
The Data science projects page opens.
2. Click the name of the project that you want to change the access permissions of.
A project details page opens.
3. Click the Permissions tab.
The Permissions page for the project opens.
4. Click the action menu (⋮) beside the user or group whose access permissions you want to
revoke and click Delete.
Verification
Users whose access you have revoked can no longer perform the actions that were permitted
by their access permission level.
## MANAGING ACCESS TO DATA SCIENCE PROJECTS
31
## CREATING PROJECT-SCOPED RESOURCES FOR
YOUR PROJECT
As an OpenShift AI user, you can access global resources in all OpenShift AI projects, but you can access
project-scoped resources within the specified project only.
As a user with access permissions to a project, you can create the following types of project-scoped
resources for your OpenShift AI project:
Workbench images
Model-serving runtimes for KServe
All resource names must be unique within a project.
NOTE
A user with access permissions to a project can create project-scoped resources for that
project, as described in Creating project-scoped resources.
Prerequisites
You can access the OpenShift console.
An OpenShift AI administrator has set the disableProjectScoped dashboard configuration
option to false, as described in Customizing the dashboard.
You can access a project in the OpenShift AI console.
You have example YAML code for the type of resource that you want to create.
You can get the YAML code from a trusted source, such as an existing project-scoped resource,
a Git repository, or documentation. Alternatively, you can contact your cluster administrator to
request the relevant YAML code.
Procedure
1. Log in to the OpenShift console.
2. From a trusted source, copy the YAML code that you want to use for your project resource.
For example, if you can access an existing project-scoped resource in one of your projects, you
can copy the YAML code as follows:
a. In the Administrator perspective, click Home → Search.
b. From the Projects list, select the appropriate project.
c. In the Resources list, search for the relevant resource type, as follows:
For workbench images, search for ImageStream.
For serving runtimes, search for Template. From the resulting list, find the templates
that have the objects.kind specification set to ServingRuntime.
d. Select a resource, and then click the YAML tab.
Red Hat OpenShift AI Cloud Service 1 Working on data science projects
32

e. Copy the YAML content, and then click Cancel.
3. From the Project list, select your project name.
4. From the toolbar, click the + icon to open the Import YAML page.
5. Paste the example YAML content into the code area.
6. Edit the metadata.namespace value to specify the name of your project.
7. If necessary, edit the metadata.name value to ensure that the resource name is unique within
the specified project.
8. Optional: Edit the resource name that is displayed in the OpenShift AI console, as follows:
For workbench images, edit the metadata.annotations.opendatahub.io/notebook-image-
name value.
For serving runtimes, edit the objects.metadata.annotations.openshift.io/display-name
value.
9. Click Create.
Verification
1. Log in to the OpenShift AI console.
2. Verify that the project-scoped resource is shown in the specified project:
For workbench images, when you create a workbench in the project, as described in
Creating a workbench , the workbench image that you added is available in the Image
selection list.
For model-serving runtimes, see Deploying models on the single-model serving platform .
## CREATING PROJECT-SCOPED RESOURCES FOR YOUR PROJECT
33

---

## Openshift Ai Deploying Models

Red Hat OpenShift AI Cloud Service 1
Deploying models
Deploy models in Red Hat OpenShift AI Cloud Service
Last Updated: 2025-10-28

Red Hat OpenShift AI Cloud Service 1 Deploying models
Deploy models in Red Hat OpenShift AI Cloud Service

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
As a Red Hat OpenShift AI user, you can deploy your machine-learning models in Red Hat
OpenShift AI Cloud Service.

. . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 
. . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 
. . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 
. . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 
. . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 
Table of Contents
## STORING MODELS
### USING OCI CONTAINERS FOR MODEL STORAGE
### STORING A MODEL IN AN OCI IMAGE
### UPLOADING MODEL FILES TO A PERSISTENT VOLUME CLAIM (PVC)
## DEPLOYING MODELS ON THE SINGLE-MODEL SERVING PLATFORM
### ABOUT KSERVE DEPLOYMENT MODES
### DEPLOYING MODELS ON THE SINGLE-MODEL SERVING PLATFORM
### DEPLOYING A MODEL STORED IN AN OCI IMAGE BY USING THE CLI
### DEPLOYING MODELS BY USING DISTRIBUTED INFERENCE WITH LLM-D
2.4.1. Example usage for Distributed Inference with llm-d
2.4.1.1. Single-node GPU deployment
2.4.1.2. Multi-node deployment
2.4.1.3. Intelligent inference scheduler with KV cache routing
### MONITORING MODELS ON THE SINGLE-MODEL SERVING PLATFORM
2.5.1. Viewing performance metrics for a deployed model
2.5.2. Viewing model-serving runtime metrics for the single-model serving platform
## DEPLOYING MODELS ON THE NVIDIA NIM MODEL SERVING PLATFORM
### DEPLOYING MODELS ON THE NVIDIA NIM MODEL SERVING PLATFORM
### VIEWING NVIDIA NIM METRICS FOR A NIM MODEL
### VIEWING PERFORMANCE METRICS FOR A NIM MODEL
## DEPLOYING MODELS ON THE MULTI-MODEL SERVING PLATFORM
### ADDING A MODEL SERVER FOR THE MULTI-MODEL SERVING PLATFORM
### DELETING A MODEL SERVER
### DEPLOYING A MODEL BY USING THE MULTI-MODEL SERVING PLATFORM
### VIEWING A DEPLOYED MODEL
### UPDATING THE DEPLOYMENT PROPERTIES OF A DEPLOYED MODEL
### DELETING A DEPLOYED MODEL
### CONFIGURING MONITORING FOR THE MULTI-MODEL SERVING PLATFORM
### VIEWING MODEL-SERVING RUNTIME METRICS FOR THE MULTI-MODEL SERVING PLATFORM
### VIEWING PERFORMANCE METRICS FOR ALL MODELS ON A MODEL SERVER
### VIEWING HTTP REQUEST METRICS FOR A DEPLOYED MODEL
## MAKING INFERENCE REQUESTS TO DEPLOYED MODELS
### ACCESSING THE AUTHENTICATION TOKEN FOR A DEPLOYED MODEL
### ACCESSING THE INFERENCE ENDPOINT FOR A DEPLOYED MODEL
### MAKING INFERENCE REQUESTS TO MODELS DEPLOYED ON THE SINGLE-MODEL SERVING
PLATFORM
### INFERENCE ENDPOINTS
5.4.1. Caikit TGIS ServingRuntime for KServe
5.4.2. Caikit Standalone ServingRuntime for KServe
5.4.3. TGIS Standalone ServingRuntime for KServe
5.4.4. OpenVINO Model Server
5.4.5. vLLM NVIDIA GPU ServingRuntime for KServe
5.4.6. vLLM Intel Gaudi Accelerator ServingRuntime for KServe
5.4.7. vLLM AMD GPU ServingRuntime for KServe
5.4.8. vLLM Spyre AI Accelerator ServingRuntime for KServe
5.4.9. NVIDIA Triton Inference Server
5.4.10. Seldon MLServer
5.4.11. Additional resources
3
3
3
5
7
7
8
12
14
17
17
17
17
18
18
19
21
21
23
24
26
26
28
29
30
31
32
32
33
34
35
37
37
37
38
38
38
39
40
40
40
41
41
41
42
43
44
Table of Contents
1

Red Hat OpenShift AI Cloud Service 1 Deploying models
2
## STORING MODELS
You must store your model before you can deploy it. You can store a model in an S3 bucket, URI or
Open Container Initiative (OCI) containers.
### USING OCI CONTAINERS FOR MODEL STORAGE
As an alternative to storing a model in an S3 bucket or URI, you can upload models to Open Container
Initiative (OCI) containers. Deploying models from OCI containers is also known as modelcars in KServe.
Using OCI containers for model storage can help you:
Reduce startup times by avoiding downloading the same model multiple times.
Reduce disk space usage by reducing the number of models downloaded locally.
Improve model performance by allowing pre-fetched images.
Using OCI containers for model storage involves the following tasks:
Storing a model in an OCI image.
Deploying a model from an OCI image by using either the user interface or the command line
interface. To deploy a model by using:
The user interface, see Deploying models on the single-model serving platform .
The command line interface, see Deploying a model stored in an OCI image by using the
CLI.
### STORING A MODEL IN AN OCI IMAGE
You can store a model in an OCI image. The following procedure uses the example of storing a
MobileNet v2-7 model in ONNX format.
Prerequisites
You have a model in the ONNX format. The example in this procedure uses the MobileNet v2-7
model in ONNX format.
You have installed the Podman tool.
Procedure
1. In a terminal window on your local machine, create a temporary directory for storing both the
model and the support files that you need to create the OCI image:
cd $(mktemp -d)
2. Create a models folder inside the temporary directory:
mkdir -p models/1
## STORING MODELS
3

NOTE
This example command specifies the subdirectory 1 because OpenVINO requires
numbered subdirectories for model versioning. If you are not using OpenVINO,
you do not need to create the 1 subdirectory to use OCI container images.
3. Download the model and support files:
DOWNLOAD_URL=https://github.com/onnx/models/raw/main/validated/vision/classification/mob
ilenet/model/mobilenetv2-7.onnx
curl -L $DOWNLOAD_URL -O --output-dir models/1/
4. Use the tree command to confirm that the model files are located in the directory structure as
expected:
tree
The tree command should return a directory structure similar to the following example:
.
├── Containerfile
└── models
    └── 1
        └── mobilenetv2-7.onnx
5. Create a Docker file named Containerfile:
NOTE
Specify a base image that provides a shell. In the following example, ubi9-
micro is the base container image. You cannot specify an empty image that
does not provide a shell, such as scratch, because KServe uses the shell to
ensure the model files are accessible to the model server.
Change the ownership of the copied model files and grant read permissions
to the root group to ensure that the model server can access the files.
OpenShift runs containers with a random user ID and the root group ID.
FROM registry.access.redhat.com/ubi9/ubi-micro:latest
COPY --chown=0:0 models /models
RUN chmod -R a=rX /models
# nobody user
USER 65534
6. Use podman build commands to create the OCI container image and upload it to a registry.
The following commands use Quay as the registry.
NOTE
If your repository is private, ensure that you are authenticated to the registry
before uploading your container image.
Red Hat OpenShift AI Cloud Service 1 Deploying models
4

podman build --format=oci -t quay.io/<user_name>/<repository_name>:<tag_name> .
podman push quay.io/<user_name>/<repository_name>:<tag_name>
### UPLOADING MODEL FILES TO A PERSISTENT VOLUME CLAIM
(PVC)
When deploying a model, you can serve it from a preexisting Persistent Volume Claim (PVC) where your
model files are stored. You can upload your local model files to a PVC in the IDE that you access from a
running workbench.
Prerequisites
You have access to the OpenShift AI dashboard.
You have access to a project that has a running workbench.
You have created a persistent volume claim (PVC) with a context type of Model storage.
The workbench is attached to the persistent volume (PVC).
For instructions on attaching a PVC, see Creating a project workbench .
You have the model files saved on your local machine.
Procedure
Follow these steps to upload your model files to the PVC mount point (/opt/app-root/src/) within your
workbench:
1. From the OpenShift AI dashboard, click the open icon ( 
 ) to open your IDE in a new window.
2. In your IDE, navigate to the File Browser pane on the left-hand side.
a. In JupyterLab, this is usually labeled Files.
b. In code-server, this is usually the Explorer view.
3. In the file browser, navigate to the /opt/app-root/src/ folder. This folder represents the root of
your attached PVC.
NOTE
Any files or folders that you create or upload to this folder persist in the PVC.
4. Optional: Create a new folder to organize your models:
a. In the file browser, right-click within the /opt/app-root/src/ folder in the file browser and
select New Folder.
b. Name the folder (for example, models).
c. Double-click the new models folder to enter it.
5. Upload your model files to the current folder (/opt/app-root/src/ or /opt/app-root/src/models/):
i
b
## STORING MODELS
5

Using JupyterLab:
a. Click the Upload Files icon ( 
 ) in the file browser toolbar above the folder listing.
b. In the file selection dialog, navigate to and select the model files from your local
computer. Click Open.
c. Wait for the upload progress bars next to the filenames to complete.
Using code-server:
a. Drag the model files directly from your local file explorer and drop them into the file
browser pane in the target folder within code-server.
6. Wait for the upload process to complete.
Verification
Confirm that your files appear in the file browser at the path where you uploaded them.
Next steps
When you follow the procedure to deploy a model, you can access the model files from the specified
path within your PVC:
1. In the Deploy model dialog, select Existing cluster storage under the Source model location
section.
2. From the Cluster storage list, select the PVC associated with your workbench.
3. In the Model path field, enter the path to your model or the folder containing your model.
Red Hat OpenShift AI Cloud Service 1 Deploying models
6
## DEPLOYING MODELS ON THE SINGLE-MODEL
SERVING PLATFORM
The single-model serving platform deploys each model from its own dedicated model server. This
architecture is ideal for deploying, monitoring, scaling, and maintaining large models that require more
resources, such as large language models (LLMs).
The platform is based on the KServe component and offers two deployment modes:
KServe RawDeployment: Uses a standard deployment method that does not require serverless
dependencies.
Knative Serverless: Uses Red Hat OpenShift Serverless for deployments that can automatically
scale based on demand.
### ABOUT KSERVE DEPLOYMENT MODES
KServe offers two deployment modes for serving models. The default mode, Knative Serverless, is
based on the open-source Knative project and provides powerful autoscaling capabilities. It integrates
with Red Hat OpenShift Serverless and Red Hat OpenShift Service Mesh. Alternatively, the KServe
RawDeployment mode offers a more traditional deployment method with fewer dependencies.
Before you choose an option, understand how your initial configuration affects future deployments:
If you configure for Knative Serverless: You can use both Knative Serverless and KServe
RawDeployment modes.
If you configure for KServe RawDeployment only: You can only use the KServe
RawDeployment mode.
Use the following comparison to choose the option that best fits your requirements.
Table 2.1. Comparison of deployment modes
Criterion
Knative Serverless
KServe RawDeployment
Default mode
Yes
No
Recommended
use case
Most workloads.
Custom serving setups or models that
must remain active.
Autoscaling
Scales up automatically based
on request volume.
Supports scaling down to zero
when idle to save costs.
No built-in autoscaling; you can
configure Kubernetes Event-
Driven Autoscaling (KEDA) or
Horizontal Pod Autoscaler
(HPA) on your deployment.
Does not support scaling to zero
by default, which might result in
higher costs during periods of
low traffic.
## DEPLOYING MODELS ON THE SINGLE-MODEL SERVING PLATFORM
7

Dependencies
Red Hat OpenShift Serverless
Operator
Red Hat OpenShift Service
Mesh
Authorino. Only required only if
you enable token authentication
and external routes.
None; uses standard Kubernetes
resources such as Deployment, 
Service, and Horizontal Pod 
Autoscaler.
Configuration
flexibility
Has some customization limitations
inherited from Knative compared to raw
Kubernetes deployments.
Provides full control over pod
specifications because it uses standard
Kubernetes Deployment resources.
Resource footprint
Larger, due to the additional
dependencies required for serverless
functionality.
Smaller.
Setup complexity
Might require additional configuration in
setup and management. If Serverless is
not already installed on the cluster, you
must install and configure it.
Requires a simpler setup with fewer
dependencies.
Criterion
Knative Serverless
KServe RawDeployment
### DEPLOYING MODELS ON THE SINGLE-MODEL SERVING
PLATFORM
When you have enabled the single-model serving platform, you can enable a preinstalled or custom
model-serving runtime and deploy models on the platform.
You can use preinstalled model-serving runtimes to start serving models without modifying or defining
the runtime yourself. For help adding a custom runtime, see Adding a custom model-serving runtime for
the single-model serving platform.
To successfully deploy a model, you must meet the following prerequisites.
General prerequisites
You have logged in to Red Hat OpenShift AI.
You have installed KServe and enabled the single-model serving platform.
(Knative Serverless deployments only) To enable token authentication and external model
routes for deployed models, you have added Authorino as an authorization provider. For more
information, see Adding an authorization provider for the single-model serving platform .
(Knative Serverless deployments only) To enable token authentication and external model
routes for deployed models, you have added Authorino as an authorization provider.
You have created a data science project.
You have access to S3-compatible object storage, a URI-based repository, an OCI-compliant
Red Hat OpenShift AI Cloud Service 1 Deploying models
8

registry or a persistent volume claim (PVC) and have added a connection to your data science
project. For more information about adding a connection, see Adding a connection to your data
science project.
If you want to use graphics processing units (GPUs) with your model server, you have enabled
GPU support in OpenShift AI. If you use NVIDIA GPUs, see Enabling NVIDIA GPUs. If you use
AMD GPUs, see AMD GPU integration.
Runtime-specific prerequisites
Meet the requirements for the specific runtime you intend to use.
Caikit-TGIS runtime
To use the Caikit-TGIS runtime, you have converted your model to Caikit format. For an
example, see Converting Hugging Face Hub models to Caikit format  in the caikit-tgis-
serving repository.
vLLM NVIDIA GPU ServingRuntime for KServe
To use the vLLM NVIDIA GPU ServingRuntime for KServe runtime, you have enabled
GPU support in OpenShift AI and have installed and configured the Node Feature
Discovery Operator on your cluster. For more information, see Installing the Node Feature
Discovery Operator and Enabling NVIDIA GPUs.
vLLM Intel Gaudi Accelerator ServingRuntime for KServe
To use the vLLM Intel Gaudi Accelerator ServingRuntime for KServe runtime, you have
enabled support for hybrid processing units (HPUs) in OpenShift AI. This includes installing
the Intel Gaudi Base Operator and configuring a hardware profile. For more information, see
Intel Gaudi Base Operator OpenShift installation in the AMD documentation and Working
with hardware profiles.
vLLM AMD GPU ServingRuntime for KServe
To use the vLLM AMD GPU ServingRuntime for KServe runtime, you have enabled
support for AMD graphic processing units (GPUs) in OpenShift AI. This includes installing
the AMD GPU operator and configuring a hardware profile. For more information, see
Deploying the AMD GPU operator on OpenShift  and Working with hardware profiles.
vLLM Spyre AI Accelerator ServingRuntime for KServe
IMPORTANT
Support for IBM Spyre AI Accelerators on x86 is currently available in Red Hat
OpenShift AI as a Technology Preview feature. Technology Preview features are
not supported with Red Hat production service level agreements (SLAs) and
might not be functionally complete. Red Hat does not recommend using them in
production. These features provide early access to upcoming product features,
enabling customers to test functionality and provide feedback during the
development process.
For more information about the support scope of Red Hat Technology Preview
features, see Technology Preview Features Support Scope .
## DEPLOYING MODELS ON THE SINGLE-MODEL SERVING PLATFORM
9

To use the vLLM Spyre AI Accelerator ServingRuntime for KServe runtime on x86, you
have installed the Spyre Operator and configured a hardware profile. For more information,
see Spyre operator image and Working with hardware profiles.
Procedure
1. In the left menu, click Data science projects.
2. Click the name of the project that you want to deploy a model in.
A project details page opens.
3. Click the Models tab.
4. Click Select single-model to deploy your model using single-model serving.
5. Click the Deploy model button.
The Deploy model dialog opens.
6. In the Model deployment name field, enter a unique name for the model that you are
deploying.
7. In the Serving runtime field, select an enabled runtime. If project-scoped runtimes exist, the
Serving runtime list includes subheadings to distinguish between global runtimes and project-
scoped runtimes.
8. From the Model framework (name - version) list, select a value if applicable.
9. From the Deployment mode list, select KServe RawDeployment or Knative Serverless. For
more information about deployment modes, see About KServe deployment modes.
10. In the Number of model server replicas to deploy field, specify a value.
11. The following options are only available if you have created a hardware profile:
a. From the Hardware profile list, select a hardware profile. If project-scoped hardware
profiles exist, the Hardware profile list includes subheadings to distinguish between global
hardware profiles and project-scoped hardware profiles.
IMPORTANT
By default, hardware profiles are hidden in the dashboard navigation menu
and user interface, while accelerator profiles remain visible. In addition, user
interface components associated with the deprecated accelerator profiles
functionality are still displayed. If you enable hardware profiles, the Hardware
profiles list is displayed instead of the Accelerator profiles list. To show the
Settings → Hardware profiles option in the dashboard navigation menu, and
the user interface components associated with hardware profiles, set the 
disableHardwareProfiles value to false in the OdhDashboardConfig
custom resource (CR) in OpenShift. For more information about setting
dashboard configuration options, see Customizing the dashboard.
b. Optional: To change these default values, click Customize resource requests and limit
and enter new minimum (request) and maximum (limit) values. The hardware profile
specifies the number of CPUs and the amount of memory allocated to the container,
setting the guaranteed minimum (request) and maximum (limit) for both.
Red Hat OpenShift AI Cloud Service 1 Deploying models
10

12. Optional: In the Model route section, select the Make deployed models available through an
external route checkbox to make your deployed models available to external clients.
13. To require token authentication for inference requests to the deployed model, perform the
following actions:
a. Select Require token authentication.
b. In the Service account name field, enter the service account name that the token will be
generated for.
c. To add an additional service account, click Add a service account and enter another service
account name.
14. To specify the location of your model, select a Connection type that you have added. The OCI-
compliant registry, S3 compatible object storage, and URI options are pre-installed
connection types. Additional options might be available if your OpenShift AI administrator
added them.
a. For S3-compatible object storage: In the Path field, enter the folder path that contains
the model in your specified data source.
IMPORTANT
The OpenVINO Model Server runtime has specific requirements for how you
specify the model path. For more information, see known issue RHOAIENG-
3025 in the OpenShift AI release notes.
b. For Open Container Image connections: In the OCI storage location field, enter the
model URI where the model is located.
NOTE
If you are deploying a registered model version with an existing S3, URI, or
OCI data connection, some of your connection details might be autofilled.
This depends on the type of data connection and the number of matching
connections available in your data science project. For example, if only one
matching connection exists, fields like the path, URI, endpoint, model URI,
bucket, and region might populate automatically. Matching connections will
be labeled as Recommended.
c. Complete the connection detail fields.
d. Optional: If you have uploaded model files to a persistent volume claim (PVC) and the PVC
is attached to your workbench, use the Existing cluster storage option to select the PVC
and specify the path to the model file.
IMPORTANT
If your connection type is an S3-compatible object storage, you must provide
the folder path that contains your data file. The OpenVINO Model Server
runtime has specific requirements for how you specify the model path. For
more information, see known issue RHOAIENG-3025 in the OpenShift AI
release notes.
## DEPLOYING MODELS ON THE SINGLE-MODEL SERVING PLATFORM
11

15. (Optional) Customize the runtime parameters in the Configuration parameters section:
a. Modify the values in Additional serving runtime arguments to define how the deployed
model behaves.
b. Modify the values in Additional environment variables to define variables in the model’s
environment.
The Configuration parameters section shows predefined serving runtime parameters, if
any are available.
NOTE
Do not modify the port or model serving runtime arguments, because they
require specific values to be set. Overwriting these parameters can cause the
deployment to fail.
16. Click Deploy.
Verification
Confirm that the deployed model is shown on the Models tab for the project, and on the Model
deployments page of the dashboard with a checkmark in the Status column.
Additional resources
Model-serving runtimes for accelerators
### DEPLOYING A MODEL STORED IN AN OCI IMAGE BY USING THE
CLI
You can deploy a model that is stored in an OCI image from the command line interface.
The following procedure uses the example of deploying a MobileNet v2-7 model in ONNX format,
stored in an OCI image on an OpenVINO model server.
NOTE
By default in KServe, models are exposed outside the cluster and not protected with
authentication.
Prerequisites
You have stored a model in an OCI image as described in Storing a model in an OCI image .
If you want to deploy a model that is stored in a private OCI repository, you must configure an
image pull secret. For more information about creating an image pull secret, see Using image
pull secrets.
You are logged in to your OpenShift cluster.
Procedure
1. Create a project to deploy the model:
Red Hat OpenShift AI Cloud Service 1 Deploying models
12

oc new-project oci-model-example
2. Use the OpenShift AI Applications project kserve-ovms template to create a ServingRuntime
resource and configure the OpenVINO model server in the new project:
oc process -n redhat-ods-applications -o yaml kserve-ovms | oc apply -f -
3. Verify that the ServingRuntime named kserve-ovms is created:
oc get servingruntimes
The command should return output similar to the following:
NAME          DISABLED   MODELTYPE     CONTAINERS         AGE
kserve-ovms              openvino_ir   kserve-container   1m
4. Create an InferenceService YAML resource, depending on whether the model is stored from a
private or a public OCI repository:
For a model stored in a public OCI repository, create an InferenceService YAML file with
the following values, replacing <user_name>, <repository_name>, and <tag_name> with
values specific to your environment:
apiVersion: serving.kserve.io/v1beta1
kind: InferenceService
metadata:
  name: sample-isvc-using-oci
spec:
  predictor:
    model:
      runtime: kserve-ovms # Ensure this matches the name of the ServingRuntime 
resource
      modelFormat:
        name: onnx
      storageUri: oci://quay.io/<user_name>/<repository_name>:<tag_name>
      resources:
        requests:
          memory: 500Mi
          cpu: 100m
          # nvidia.com/gpu: "1" # Only required if you have GPUs available and the model 
and runtime will use it
        limits:
          memory: 4Gi
          cpu: 500m
          # nvidia.com/gpu: "1" # Only required if you have GPUs available and the model 
and runtime will use it
For a model stored in a private OCI repository, create an InferenceService YAML file that
specifies your pull secret in the spec.predictor.imagePullSecrets field, as shown in the
following example:
apiVersion: serving.kserve.io/v1beta1
kind: InferenceService
metadata:
## DEPLOYING MODELS ON THE SINGLE-MODEL SERVING PLATFORM
13

  name: sample-isvc-using-private-oci
spec:
  predictor:
    model:
      runtime: kserve-ovms # Ensure this matches the name of the ServingRuntime 
resource
      modelFormat:
        name: onnx
      storageUri: oci://quay.io/<user_name>/<repository_name>:<tag_name>
      resources:
        requests:
          memory: 500Mi
          cpu: 100m
          # nvidia.com/gpu: "1" # Only required if you have GPUs available and the model 
and runtime will use it
        limits:
          memory: 4Gi
          cpu: 500m
          # nvidia.com/gpu: "1" # Only required if you have GPUs available and the model 
and runtime will use it
    imagePullSecrets: # Specify image pull secrets to use for fetching container images, 
including OCI model images
    - name: <pull-secret-name>
After you create the InferenceService resource, KServe deploys the model stored in the
OCI image referred to by the storageUri field.
Verification
Check the status of the deployment:
oc get inferenceservice
The command should return output that includes information, such as the URL of the deployed model
and its readiness state.
### DEPLOYING MODELS BY USING DISTRIBUTED INFERENCE WITH
LLM-D
IMPORTANT
Distributed Inference with llm-d is currently available in Red Hat OpenShift AI as a
Technology Preview feature. Technology Preview features are not supported with
Red Hat production service level agreements (SLAs) and might not be functionally
complete. Red Hat does not recommend using them in production. These features
provide early access to upcoming product features, enabling customers to test
functionality and provide feedback during the development process.
For more information about the support scope of Red Hat Technology Preview features,
see Technology Preview Features Support Scope .
Distributed Inference with llm-d is a Kubernetes-native, open-source framework designed for serving
large language models (LLMs) at scale. You can use Distributed Inference with llm-d to simplify the
deployment of generative AI, focusing on high performance and cost-effectiveness across various
Red Hat OpenShift AI Cloud Service 1 Deploying models
14

hardware accelerators.
Key features of Distributed Inference with llm-d include:
Efficiently handles large models using optimizations such as prefix-cache aware routing and
disaggregated serving.
Integrates into a standard Kubernetes environment, where it leverages specialized components
like the Envoy proxy to handle networking and routing, and high-performance libraries such as
vLLM and NVIDIA Inference Transfer Library (NIXL).
Tested recipes and well-known presets reduce the complexity of deploying inference at scale,
so users can focus on building applications rather than managing infrastructure.
Serving models using Distributed Inference with llm-d on Red Hat OpenShift AI consists of the following
steps:
1. Installing OpenShift AI.
NOTE
Because KServe Serverless conflicts with the Gateway API used for Distributed
Inference with llm-d, KServe Serverless is not supported on the same cluster.
Instead, use KServe RawDeployment.
2. Enabling the single model serving platform.
3. Enabling Distributed Inference with llm-d on a Kubernetes cluster.
4. Creating an LLMInferenceService Custom Resource (CR).
5. Deploying a model.
This procedure describes how to create a custom resource (CR) for an LLMInferenceService resource.
You replace the default InferenceService with the LLMInferenceService.
Prerequisites
You have enabled the single model-serving platform.
You have access to an OpenShift cluster running version 4.19.9 or later.
OpenShift Service Mesh v2 is not installed in the cluster.
You have created a GatewayClass and a Gateway named openshift-ai-inference in the 
openshift-ingress namespace as described in Gateway API with OpenShift Container Platform
Networking.
You have installed the LeaderWorkerSet Operator in OpenShift. For more information, see the
OpenShift documentation.
Procedure
1. Log in to the OpenShift console as a cluster administrator.
## DEPLOYING MODELS ON THE SINGLE-MODEL SERVING PLATFORM
15

2. Create a data science cluster initialization (DSCI) and set the serviceMesh.managementState
to removed, as shown in the following example:
serviceMesh:
  ...
  managementState: Removed
3. Create a data science cluster (DSC) with the following information set in kserve and serving:
kserve:
  defaultDeploymentMode: RawDeployment
  managementState: Managed
  ...
  serving:
    ...
    managementState: Removed
    ...
4. Create the LLMInferenceService CR with the following information:
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
Customize the following parameters in the spec section of the inference service:
replicas - Specify the number of replicas.
model - Provide the URI to the model based on how the model is stored ( uri) and the model
name to use in chat completion requests (name).
S3 bucket: s3://<bucket-name>/<object-key>
Red Hat OpenShift AI Cloud Service 1 Deploying models
16

Persistent volume claim (PVC): pvc://<claim-name>/<pvc-path>
OCI container image: oci://<registry_host>/<org_or_username>/<repository_name>
<tag_or_digest>
HuggingFace: hf://<model>/<optional-hash>
router - Provide an HTTPRoute and gateway, or leave blank to automatically create one.
5. Save the file.
2.4.1. Example usage for Distributed Inference with llm-d
These examples show how to use Distributed Inference with llm-d in common scenarios.
IMPORTANT
Distributed Inference with llm-d is currently available in Red Hat OpenShift AI as a
Technology Preview feature. Technology Preview features are not supported with
Red Hat production service level agreements (SLAs) and might not be functionally
complete. Red Hat does not recommend using them in production. These features
provide early access to upcoming product features, enabling customers to test
functionality and provide feedback during the development process.
For more information about the support scope of Red Hat Technology Preview features,
see Technology Preview Features Support Scope .
2.4.1.1. Single-node GPU deployment
Use single-GPU-per-replica deployment patterns for development, testing, or production deployments
of smaller models, such as 7-billion-parameter models.
You can use the following examples for single-node GPU deployments:
Basic deployment with default scheduler
Deployment without scheduler
Prefill-decode separation
2.4.1.2. Multi-node deployment
You can use the following examples for multi-node deployments:
Basic Data Parallelism (DP) and Expert Parallelism (EP) configuration
Prefill-decode separation with a high-throughput backend
Prefill-decode separation with a mixed backend
2.4.1.3. Intelligent inference scheduler with KV cache routing
You can configure the scheduler to track key-value (KV) cache blocks across inference endpoints and
route requests to the endpoint with the highest cache hit rate. This configuration improves throughput
and reduces latency by maximizing cache reuse.
## DEPLOYING MODELS ON THE SINGLE-MODEL SERVING PLATFORM
17

For an example, see Precise Prefix KV Cache Routing .
### MONITORING MODELS ON THE SINGLE-MODEL SERVING
PLATFORM
You can monitor models that are deployed on the single-model serving platform to view performance
and resource usage metrics.
2.5.1. Viewing performance metrics for a deployed model
You can monitor the following metrics for a specific model that is deployed on the single-model serving
platform:
Number of requests - The number of requests that have failed or succeeded for a specific
model.
Average response time (ms) - The average time it takes a specific model to respond to
requests.
CPU utilization (%) - The percentage of the CPU limit per model replica that is currently
utilized by a specific model.
Memory utilization (%) - The percentage of the memory limit per model replica that is utilized
by a specific model.
You can specify a time range and a refresh interval for these metrics to help you determine, for
example, when the peak usage hours are and how the model is performing at a specified time.
Prerequisites
You have installed Red Hat OpenShift AI.
You have logged in to Red Hat OpenShift AI.
The following dashboard configuration options are set to the default values as shown:
disablePerformanceMetrics:false
disableKServeMetrics:false
For more information about setting dashboard configuration options, see Customizing the
dashboard.
You have deployed a model on the single-model serving platform by using a preinstalled
runtime.
NOTE
Metrics are only supported for models deployed by using a preinstalled model-
serving runtime or a custom runtime that is duplicated from a preinstalled
runtime.
Procedure
1. From the OpenShift AI dashboard navigation menu, click Data science projects.
Red Hat OpenShift AI Cloud Service 1 Deploying models
18

The Data science projects page opens.
2. Click the name of the project that contains the data science models that you want to monitor.
3. In the project details page, click the Models tab.
4. Select the model that you are interested in.
5. On the Endpoint performance tab, set the following options:
Time range - Specifies how long to track the metrics. You can select one of these values: 1
hour, 24 hours, 7 days, and 30 days.
Refresh interval - Specifies how frequently the graphs on the metrics page are refreshed
(to show the latest data). You can select one of these values: 15 seconds, 30 seconds, 1
minute, 5 minutes, 15 minutes, 30 minutes, 1 hour, 2 hours, and 1 day.
6. Scroll down to view data graphs for number of requests, average response time, CPU utilization,
and memory utilization.
Verification
The Endpoint performance tab shows graphs of metrics for the model.
2.5.2. Viewing model-serving runtime metrics for the single-model serving platform
When a cluster administrator has configured monitoring for the single-model serving platform, non-
admin users can use the OpenShift web console to view model-serving runtime metrics for the KServe
component.
Prerequisites
You have access to the OpenShift cluster as a developer or as a user with view permissions for
the project that you are viewing metrics for.
You are familiar with querying metrics in user-defined projects. See Monitoring project and
application metrics using the Developer perspective in Red Hat OpenShift Dedicated or
Monitoring project and application metrics using the Developer perspective in Red Hat
OpenShift Service on AWS (classic architecture).
Procedure
1. Log in to the OpenShift web console.
2. Switch to the Developer perspective.
3. In the left menu, click Observe.
4. As described in Monitoring your project metrics in Red Hat OpenShift Dedicated  or Monitoring
your project metrics in Red Hat OpenShift Service on AWS (classic architecture), use the web
console to run queries for caikit_*, tgi_*, ovms_* and vllm:* model-serving runtime metrics.
You can also run queries for istio_* metrics that are related to OpenShift Service Mesh. Some
examples are shown.
a. The following query displays the number of successful inference requests over a period of
time for a model deployed with the vLLM runtime:
## DEPLOYING MODELS ON THE SINGLE-MODEL SERVING PLATFORM
19

sum(increase(vllm:request_success_total{namespace=${namespace},model_name=${m
odel_name}}[${rate_interval}]))
b. The following query displays the number of successful inference requests over a period of
time for a model deployed with the standalone TGIS runtime:
sum(increase(tgi_request_success{namespace=${namespace}, pod=~${model_name}-
predictor-.*}[${rate_interval}]))
c. The following query displays the number of successful inference requests over a period of
time for a model deployed with the Caikit Standalone runtime:
sum(increase(predict_rpc_count_total{namespace=${namespace},code=OK,model_id=$
{model_name}}[${rate_interval}]))
d. The following query displays the number of successful inference requests over a period of
time for a model deployed with the OpenVINO Model Server runtime:
sum(increase(ovms_requests_success{namespace=${namespace},name=${model_nam
e}}[${rate_interval}]))
Additional resources
OVMS metrics
TGIS metrics
vLLM metrics
Red Hat OpenShift AI Cloud Service 1 Deploying models
20
## DEPLOYING MODELS ON THE NVIDIA NIM
MODEL SERVING PLATFORM
You can deploy models using NVIDIA NIM inference services on the NVIDIA NIM model serving platform.
NVIDIA NIM, part of NVIDIA AI Enterprise, is a set of microservices designed for secure, reliable
deployment of high performance AI model inferencing across clouds, data centers and workstations.
### DEPLOYING MODELS ON THE NVIDIA NIM MODEL SERVING
PLATFORM
When you have enabled the NVIDIA NIM model serving platform, you can start to deploy NVIDIA-
optimized models on the platform.
Prerequisites
You have logged in to Red Hat OpenShift AI.
You have enabled the NVIDIA NIM model serving platform.
You have created a data science project.
You have enabled support for graphic processing units (GPUs) in OpenShift AI. This includes
installing the Node Feature Discovery Operator and NVIDIA GPU Operator. For more
information, see Installing the Node Feature Discovery Operator  and Enabling NVIDIA GPUs.
Procedure
1. In the left menu, click Data science projects.
The Data science projects page opens.
2. Click the name of the project that you want to deploy a model in.
A project details page opens.
3. Click the Models tab.
4. In the Models section, perform one of the following actions:
On the ​NVIDIA NIM model serving platform tile, click Select NVIDIA NIM on the tile, and
then click Deploy model.
If you have previously selected the NVIDIA NIM model serving type, the Models page
displays NVIDIA model serving enabled on the upper-right corner, along with the Deploy
model button. To proceed, click Deploy model.
The Deploy model dialog opens.
5. Configure properties for deploying your model as follows:
a. In the Model deployment name field, enter a unique name for the deployment.
b. From the NVIDIA NIM list, select the NVIDIA NIM model that you want to deploy. For more
information, see Supported Models
## DEPLOYING MODELS ON THE NVIDIA NIM MODEL SERVING PLATFORM
21

c. In the NVIDIA NIM storage size field, specify the size of the cluster storage instance that
will be created to store the NVIDIA NIM model.
NOTE
When resizing a PersistentVolumeClaim (PVC) backed by Amazon EBS in
OpenShift AI, you may encounter VolumeModificationRateExceeded: 
You've reached the maximum modification rate per volume limit. To
avoid this error, wait at least six hours between modifications per EBS
volume. If you resize a PVC before the cooldown expires, the Amazon EBS
CSI driver (ebs.csi.aws.com) fails with this error. This error is an Amazon
EBS service limit that applies to all workloads using EBS-backed PVCs.
d. In the Number of model server replicas to deploy field, specify a value.
e. From the Model server size list, select a value.
6. From the Hardware profile list, select a hardware profile.
IMPORTANT
By default, hardware profiles are hidden in the dashboard navigation menu and
user interface, while accelerator profiles remain visible. In addition, user interface
components associated with the deprecated accelerator profiles functionality are
still displayed. If you enable hardware profiles, the Hardware profiles list is
displayed instead of the Accelerator profiles list. To show the Settings →
Hardware profiles option in the dashboard navigation menu, and the user
interface components associated with hardware profiles, set the 
disableHardwareProfiles value to false in the OdhDashboardConfig custom
resource (CR) in OpenShift. For more information about setting dashboard
configuration options, see Customizing the dashboard.
7. Optional: Click Customize resource requests and limit and update the following values:
a. In the CPUs requests field, specify the number of CPUs to use with your model server. Use
the list beside this field to specify the value in cores or millicores.
b. In the CPU limits field, specify the maximum number of CPUs to use with your model server.
Use the list beside this field to specify the value in cores or millicores.
c. In the Memory requests field, specify the requested memory for the model server in
gibibytes (Gi).
d. In the Memory limits field, specify the maximum memory limit for the model server in
gibibytes (Gi).
8. Optional: In the Model route section, select the Make deployed models available through an
external route checkbox to make your deployed models available to external clients.
9. To require token authentication for inference requests to the deployed model, perform the
following actions:
a. Select Require token authentication.
Red Hat OpenShift AI Cloud Service 1 Deploying models
22

b. In the Service account name field, enter the service account name that the token will be
generated for.
c. To add an additional service account, click Add a service account and enter another service
account name.
10. Click Deploy.
Verification
Confirm that the deployed model is shown on the Models tab for the project, and on the Model
deployments page of the dashboard with a checkmark in the Status column.
Additional resources
NVIDIA NIM API reference
Supported Models
### VIEWING NVIDIA NIM METRICS FOR A NIM MODEL
In OpenShift AI, you can observe the following NVIDIA NIM metrics for a NIM model deployed on the
NVIDIA NIM model serving platform:
GPU cache usage over time (ms)
Current running, waiting, and max requests count
Tokens count
Time to first token
Time per output token
Request outcomes
You can specify a time range and a refresh interval for these metrics to help you determine, for
example, the peak usage hours and model performance at a specified time.
Prerequisites
You have enabled the NVIDIA NIM model serving platform.
You have deployed a NIM model on the NVIDIA NIM model serving platform.
A cluster administrator has enabled metrics collection and graph generation for your
deployment.
The disableKServeMetrics OpenShift AI dashboard configuration option is set to its default
value of false:
disableKServeMetrics: false
For more information about setting dashboard configuration options, see Customizing the
dashboard.
## DEPLOYING MODELS ON THE NVIDIA NIM MODEL SERVING PLATFORM
23

Procedure
1. From the OpenShift AI dashboard navigation menu, click Data science projects.
The Data science projects page opens.
2. Click the name of the project that contains the NIM model that you want to monitor.
3. In the project details page, click the Models tab.
4. Click the NIM model that you want to observe.
5. On the NIM Metrics tab, set the following options:
Time range - Specifies how long to track the metrics. You can select one of these values: 1
hour, 24 hours, 7 days, and 30 days.
Refresh interval - Specifies how frequently the graphs on the metrics page are refreshed
(to show the latest data). You can select one of these values: 15 seconds, 30 seconds, 1
minute, 5 minutes, 15 minutes, 30 minutes, 1 hour, 2 hours, and 1 day.
6. Scroll down to view data graphs for NIM metrics.
Verification
The NIM Metrics tab shows graphs of NIM metrics for the deployed NIM model.
Additional resources
NVIDIA NIM observability
### VIEWING PERFORMANCE METRICS FOR A NIM MODEL
You can observe the following performance metrics for a NIM model deployed on the NVIDIA NIM
model serving platform:
Number of requests - The number of requests that have failed or succeeded for a specific
model.
Average response time (ms) - The average time it takes a specific model to respond to
requests.
CPU utilization (%) - The percentage of the CPU limit per model replica that is currently
utilized by a specific model.
Memory utilization (%) - The percentage of the memory limit per model replica that is utilized
by a specific model.
You can specify a time range and a refresh interval for these metrics to help you determine, for
example, the peak usage hours and model performance at a specified time.
Prerequisites
You have enabled the NVIDIA NIM model serving platform.
You have deployed a NIM model on the NVIDIA NIM model serving platform.
Red Hat OpenShift AI Cloud Service 1 Deploying models
24

A cluster administrator has enabled metrics collection and graph generation for your
deployment.
The disableKServeMetrics OpenShift AI dashboard configuration option is set to its default
value of false:
disableKServeMetrics: false
For more information about setting dashboard configuration options, see Customizing the
dashboard.
Procedure
1. From the OpenShift AI dashboard navigation menu, click Data science projects.
The Data science projects page opens.
2. Click the name of the project that contains the NIM model that you want to monitor.
3. In the project details page, click the Models tab.
4. Click the NIM model that you want to observe.
5. On the Endpoint performance tab, set the following options:
Time range - Specifies how long to track the metrics. You can select one of these values: 1
hour, 24 hours, 7 days, and 30 days.
Refresh interval - Specifies how frequently the graphs on the metrics page are refreshed
to show the latest data. You can select one of these values: 15 seconds, 30 seconds, 1
minute, 5 minutes, 15 minutes, 30 minutes, 1 hour, 2 hours, and 1 day.
6. Scroll down to view data graphs for performance metrics.
Verification
The Endpoint performance tab shows graphs of performance metrics for the deployed NIM model.
## DEPLOYING MODELS ON THE NVIDIA NIM MODEL SERVING PLATFORM
25
## DEPLOYING MODELS ON THE MULTI-MODEL
SERVING PLATFORM
For deploying small and medium-sized models, OpenShift AI includes a multi-model serving platform
that is based on the ModelMesh component. On the multi-model serving platform, multiple models can
be deployed from the same model server and share the server resources.
IMPORTANT
Starting with OpenShift AI version 2.19, the multi-model serving platform based on
ModelMesh is deprecated. You can continue to deploy models on the multi-model
serving platform, but it is recommended that you migrate to the single-model serving
platform.
For more information or for help on using the single-model serving platform, contact your
account manager.
### ADDING A MODEL SERVER FOR THE MULTI-MODEL SERVING
PLATFORM
When you have enabled the multi-model serving platform, you must configure a model server to deploy
models. If you require extra computing power for use with large datasets, you can assign accelerators to
your model server.
NOTE
In OpenShift AI, Red Hat supports only NVIDIA and AMD GPU accelerators for model
serving.
Prerequisites
You have logged in to Red Hat OpenShift AI.
You have created a data science project that you can add a model server to.
You have enabled the multi-model serving platform.
If you want to use a custom model-serving runtime for your model server, you have added and
enabled the runtime. See Adding a custom model-serving runtime .
If you want to use graphics processing units (GPUs) with your model server, you have enabled
GPU support in OpenShift AI. If you use NVIDIA GPUs, see Enabling NVIDIA GPUs. If you use
AMD GPUs, see AMD GPU integration.
Procedure
1. In the left menu of the OpenShift AI dashboard, click Data science projects.
The Data science projects page opens.
2. Click the name of the project that you want to configure a model server for.
A project details page opens.
3. Click the Models tab.
Red Hat OpenShift AI Cloud Service 1 Deploying models
26

4. Perform one of the following actions:
If you see a ​Multi-model serving platform tile, click Add model server on the tile.
If you do not see any tiles, click the Add model server button.
The Add model server dialog opens.
5. In the Model server name field, enter a unique name for the model server.
6. From the Serving runtime list, select a model-serving runtime that is installed and enabled in
your OpenShift AI deployment.
NOTE
If you are using a custom model-serving runtime with your model server and want
to use GPUs, you must ensure that your custom runtime supports GPUs and is
appropriately configured to use them.
7. In the Number of model replicas to deploy field, specify a value.
8. From the Accelerator profile list, select an accelerator profile.
IMPORTANT
By default, hardware profiles are hidden in the dashboard navigation menu and
user interface, while accelerator profiles remain visible. In addition, user interface
components associated with the deprecated accelerator profiles functionality are
still displayed. If you enable hardware profiles, the Hardware profiles list is
displayed instead of the Accelerator profiles list. To show the Settings →
Hardware profiles option in the dashboard navigation menu, and the user
interface components associated with hardware profiles, set the 
disableHardwareProfiles value to false in the OdhDashboardConfig custom
resource (CR) in OpenShift. For more information about setting dashboard
configuration options, see Customizing the dashboard.
9. Optional: Click Customize resource requests and limit and update the following values:
a. In the CPUs requests field, specify the number of CPUs to use with your model server. Use
the list beside this field to specify the value in cores or millicores.
b. In the CPU limits field, specify the maximum number of CPUs to use with your model server.
Use the list beside this field to specify the value in cores or millicores.
c. In the Memory requests field, specify the requested memory for the model server in
gibibytes (Gi).
d. In the Memory limits field, specify the maximum memory limit for the model server in
gibibytes (Gi).
10. Optional: In the Model route section, select the Make deployed models available through an
external route checkbox to make your deployed models available to external clients.
11. Optional: In the Token authentication section, select the Require token authentication
checkbox to require token authentication for your model server. To finish configuring token
authentication, perform the following actions:
## DEPLOYING MODELS ON THE MULTI-MODEL SERVING PLATFORM
27

a. In the Service account name field, enter a service account name for which the token will be
generated. The generated token is created and displayed in the Token secret field when
the model server is configured.
b. To add an additional service account, click Add a service account and enter another service
account name.
12. Click Add.
The model server that you configured is displayed on the Models tab for the project, in the
Models and model servers list.
13. Optional: To update the model server, click the action menu (⋮) beside the model server and
select Edit model server.
### DELETING A MODEL SERVER
When you no longer need a model server to host models, you can remove it from your data science
project.
NOTE
When you remove a model server, you also remove the models that are hosted on that
model server. As a result, the models are no longer available to applications.
Prerequisites
You have created a data science project and an associated model server.
You have notified the users of the applications that access the models that the models will no
longer be available.
Procedure
1. From the OpenShift AI dashboard, click Data science projects.
The Data science projects page opens.
2. Click the name of the project from which you want to delete the model server.
A project details page opens.
3. Click the Models tab.
4. Click the action menu (⋮) beside the project whose model server you want to delete and then
click Delete model server.
The Delete model server dialog opens.
5. Enter the name of the model server in the text field to confirm that you intend to delete it.
6. Click Delete model server.
Verification
The model server that you deleted is no longer displayed on the Models tab for the project.
Red Hat OpenShift AI Cloud Service 1 Deploying models
28
### DEPLOYING A MODEL BY USING THE MULTI-MODEL SERVING
PLATFORM
You can deploy trained models on OpenShift AI to enable you to test and implement them into
intelligent applications. Deploying a model makes it available as a service that you can access by using an
API. This enables you to return predictions based on data inputs.
When you have enabled the multi-model serving platform, you can deploy models on the platform.
Prerequisites
You have logged in to Red Hat OpenShift AI.
You have enabled the multi-model serving platform.
You have created a data science project and added a model server.
You have access to S3-compatible object storage.
For the model that you want to deploy, you know the associated folder path in your S3-
compatible object storage bucket.
Procedure
1. In the left menu of the OpenShift AI dashboard, click Data science projects.
The Data science projects page opens.
2. Click the name of the project that you want to deploy a model in.
A project details page opens.
3. Click the Models tab.
4. Click Deploy model.
5. Configure properties for deploying your model as follows:
a. In the Model name field, enter a unique name for the model that you are deploying.
b. From the Model framework list, select a framework for your model.
NOTE
The Model framework list shows only the frameworks that are supported by
the model-serving runtime that you specified when you configured your
model server.
c. To specify the location of the model you want to deploy from S3-compatible object
storage, perform one of the following sets of actions:
To use an existing connection
i. Select Existing connection.
ii. From the Name list, select a connection that you previously defined.
h
h f ld
h f ld
h h
h
d l
f d
## DEPLOYING MODELS ON THE MULTI-MODEL SERVING PLATFORM
29

iii. In the Path field, enter the folder path that contains the model in your specified
data source.
NOTE
If you are deploying a registered model version with an existing S3 or
URI data connection, some of your connection details might be
autofilled. This depends on the type of data connection and the
number of matching connections available in your data science
project. For example, if only one matching connection exists, fields
like the path, URI, endpoint, bucket, and region might populate
automatically. Matching connections will be labeled as
Recommended.
To use a new connection
i. To define a new connection that your model can access, select New connection.
ii. In the Add connection modal, select a Connection type. The S3 compatible
object storage and URI options are pre-installed connection types. Additional
options might be available if your OpenShift AI administrator added them.
The Add connection form opens with fields specific to the connection type that
you selected.
iii. Enter the connection detail fields.
d. (Optional) Customize the runtime parameters in the Configuration parameters section:
i. Modify the values in Additional serving runtime arguments to define how the
deployed model behaves.
ii. Modify the values in Additional environment variables to define variables in the
model’s environment.
e. Click Deploy.
Verification
Confirm that the deployed model is shown on the Models tab for the project, and on the Model
deployments page of the dashboard with a checkmark in the Status column.
### VIEWING A DEPLOYED MODEL
To analyze the results of your work, you can view a list of deployed models on Red Hat OpenShift AI. You
can also view the current statuses of deployed models and their endpoints.
Prerequisites
You have logged in to Red Hat OpenShift AI.
Procedure
1. From the OpenShift AI dashboard, click Models → Model deployments.
The Model deployments page opens.
Red Hat OpenShift AI Cloud Service 1 Deploying models
30

For each model, the page shows details such as the model name, the project in which the model
is deployed, the model-serving runtime that the model uses, and the deployment status.
2. Optional: For a given model, click the link in the Inference endpoint column to see the
inference endpoints for the deployed model.
Verification
A list of previously deployed data science models is displayed on the Model deployments page.
### UPDATING THE DEPLOYMENT PROPERTIES OF A DEPLOYED
MODEL
You can update the deployment properties of a model that has been deployed previously. For example,
you can change the model’s connection and name.
Prerequisites
You have logged in to Red Hat OpenShift AI.
You have deployed a model on OpenShift AI.
Procedure
1. From the OpenShift AI dashboard, click Models → Model deployments.
The Model deployments page opens.
2. Click the action menu (⋮) beside the model whose deployment properties you want to update
and click Edit.
The Edit model dialog opens.
3. Update the deployment properties of the model as follows:
a. In the Model name field, enter a new, unique name for your model.
b. From the Model servers list, select a model server for your model.
c. From the Model framework list, select a framework for your model.
NOTE
The Model framework list shows only the frameworks that are supported by
the model-serving runtime that you specified when you configured your
model server.
d. Optionally, update the connection by specifying an existing connection or by creating a new
connection.
e. Click Redeploy.
Verification
The model whose deployment properties you updated is displayed on the Model deployments
page of the dashboard.
## DEPLOYING MODELS ON THE MULTI-MODEL SERVING PLATFORM
31
### DELETING A DEPLOYED MODEL
You can delete models you have previously deployed. This enables you to remove deployed models that
are no longer required.
Prerequisites
You have logged in to Red Hat OpenShift AI.
You have deployed a model.
Procedure
1. From the OpenShift AI dashboard, click Models → Model deployments.
The Model deployments page opens.
2. Click the action menu (⋮) beside the deployed model that you want to delete and click Delete.
The Delete deployed model dialog opens.
3. Enter the name of the deployed model in the text field to confirm that you intend to delete it.
4. Click Delete deployed model.
Verification
The model that you deleted is no longer displayed on the Model deployments page.
### CONFIGURING MONITORING FOR THE MULTI-MODEL SERVING
PLATFORM
The multi-model serving platform includes model and model server metrics for the ModelMesh
component. ModelMesh generates its own set of metrics and does not rely on the underlying model-
serving runtimes to provide them. The set of metrics that ModelMesh generates includes metrics for
model request rates and timings, model loading and unloading rates, times and sizes, internal queuing
delays, capacity and usage, cache state, and least recently-used models. For more information, see
ModelMesh metrics.
After you have configured monitoring, you can view metrics for the ModelMesh component.
Prerequisites
You have cluster administrator privileges for your OpenShift cluster.
You have installed the OpenShift CLI (oc) as described in the appropriate documentation for
your cluster:
Installing the OpenShift CLI for OpenShift Dedicated
Installing the OpenShift CLI for Red Hat OpenShift Service on AWS (classic architecture)
You are familiar with creating a config map for monitoring a user-defined workflow. You will
perform similar steps in this procedure.
You are familiar with enabling monitoring for user-defined projects in OpenShift. You will
perform similar steps in this procedure.
Red Hat OpenShift AI Cloud Service 1 Deploying models
32

You have assigned the monitoring-rules-view role to users that will monitor metrics.
Procedure
1. In a terminal window, if you are not already logged in to your OpenShift cluster as a cluster
administrator, log in to the OpenShift CLI (oc) as shown in the following example:
$ oc login <openshift_cluster_url> -u <admin_username> -p <password>
2. Define a ConfigMap object in a YAML file called uwm-cm-conf.yaml with the following
contents:
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
The user-workload-monitoring-config object configures the components that monitor user-
defined projects. Observe that the retention time is set to the recommended value of 15 days.
3. Apply the configuration to create the user-workload-monitoring-config object.
$ oc apply -f uwm-cm-conf.yaml
4. Define another ConfigMap object in a YAML file called uwm-cm-enable.yaml with the
following contents:
apiVersion: v1
kind: ConfigMap
metadata:
  name: cluster-monitoring-config
  namespace: openshift-monitoring
data:
  config.yaml: |
    enableUserWorkload: true
The cluster-monitoring-config object enables monitoring for user-defined projects.
5. Apply the configuration to create the cluster-monitoring-config object.
$ oc apply -f uwm-cm-enable.yaml
### VIEWING MODEL-SERVING RUNTIME METRICS FOR THE MULTI-
MODEL SERVING PLATFORM
## DEPLOYING MODELS ON THE MULTI-MODEL SERVING PLATFORM
33

After a cluster administrator has configured monitoring for the multi-model serving platform, non-admin
users can use the OpenShift web console to view model-serving runtime metrics for the ModelMesh
component.
Prerequisites
You have access to the OpenShift cluster as a developer or as a user with view permissions for
the project that you are viewing metrics for.
You are familiar with querying metrics in user-defined projects. See Monitoring project and
application metrics using the Developer perspective in Red Hat OpenShift Dedicated or
Monitoring project and application metrics using the Developer perspective in Red Hat
OpenShift Service on AWS (classic architecture).
Procedure
1. Log in to the OpenShift web console.
2. Switch to the Developer perspective.
3. In the left menu, click Observe.
4. As described in Monitoring your project metrics in Red Hat OpenShift Dedicated  or Monitoring
your project metrics in Red Hat OpenShift Service on AWS (classic architecture), use the web
console to run queries for modelmesh_* metrics.
### VIEWING PERFORMANCE METRICS FOR ALL MODELS ON A
MODEL SERVER
You can monitor the following metrics for all the models that are deployed on a model server:
HTTP requests per 5 minutes - The number of HTTP requests that have failed or succeeded
for all models on the server.
Average response time (ms) - For all models on the server, the average time it takes the model
server to respond to requests.
CPU utilization (%) - The percentage of the CPU’s capacity that is currently being used by all
models on the server.
Memory utilization (%) - The percentage of the system’s memory that is currently being used
by all models on the server.
You can specify a time range and a refresh interval for these metrics to help you determine, for
example, when the peak usage hours are and how the models are performing at a specified time.
Prerequisites
You have installed Red Hat OpenShift AI.
On the OpenShift cluster where OpenShift AI is installed, user workload monitoring is enabled.
You have logged in to Red Hat OpenShift AI.
You have deployed models on the multi-model serving platform.
Red Hat OpenShift AI Cloud Service 1 Deploying models
34

Procedure
1. From the OpenShift AI dashboard navigation menu, click Data science projects.
The Data science projects page opens.
2. Click the name of the project that contains the data science models that you want to monitor.
3. In the project details page, click the Models tab.
4. In the row for the model server that you are interested in, click the action menu (⋮) and then
select View model server metrics.
5. Optional: On the metrics page for the model server, set the following options:
Time range - Specifies how long to track the metrics. You can select one of these values: 1
hour, 24 hours, 7 days, and 30 days.
Refresh interval - Specifies how frequently the graphs on the metrics page are refreshed
(to show the latest data). You can select one of these values: 15 seconds, 30 seconds, 1
minute, 5 minutes, 15 minutes, 30 minutes, 1 hour, 2 hours, and 1 day.
6. Scroll down to view data graphs for HTTP requests per 5 minutes, average response time, CPU
utilization, and memory utilization.
Verification
On the metrics page for the model server, the graphs provide data on performance metrics.
### VIEWING HTTP REQUEST METRICS FOR A DEPLOYED MODEL
You can view a graph that illustrates the HTTP requests that have failed or succeeded for a specific
model that is deployed on the multi-model serving platform.
Prerequisites
You have installed Red Hat OpenShift AI.
On the OpenShift cluster where OpenShift AI is installed, user workload monitoring is enabled.
The following dashboard configuration options are set to the default values as shown:
disablePerformanceMetrics:false
disableKServeMetrics:false
For more information about setting dashboard configuration options, see Customizing the
dashboard.
You have logged in to Red Hat OpenShift AI.
You have deployed models on the multi-model serving platform.
Procedure
1. From the OpenShift AI dashboard, click Models → Model deployments.
2. On the Model deployments page, select the model that you are interested in.
## DEPLOYING MODELS ON THE MULTI-MODEL SERVING PLATFORM
35

3. Optional: On the Endpoint performance tab, set the following options:
Time range - Specifies how long to track the metrics. You can select one of these values: 1
hour, 24 hours, 7 days, and 30 days.
Refresh interval - Specifies how frequently the graphs on the metrics page are refreshed
(to show the latest data). You can select one of these values: 15 seconds, 30 seconds, 1
minute, 5 minutes, 15 minutes, 30 minutes, 1 hour, 2 hours, and 1 day.
Verification
The Endpoint performance tab shows a graph of the HTTP metrics for the model.
Red Hat OpenShift AI Cloud Service 1 Deploying models
36
## MAKING INFERENCE REQUESTS TO DEPLOYED
MODELS
When you deploy a model, it is available as a service that you can access with API requests. This allows
you to get predictions from your model based on the data you provide in the request.
### ACCESSING THE AUTHENTICATION TOKEN FOR A DEPLOYED
MODEL
If you secured your model inference endpoint by enabling token authentication, you must know how to
access your authentication token so that you can specify it in your inference requests.
Prerequisites
You have logged in to Red Hat OpenShift AI.
You have deployed a model by using the single-model serving platform.
Procedure
1. From the OpenShift AI dashboard, click Data science projects.
The Data science projects page opens.
2. Click the name of the project that contains your deployed model.
A project details page opens.
3. Click the Models tab.
4. In the Models and model servers list, expand the section for your model.
Your authentication token is shown in the Token authentication section, in the Token secret
field.
5. Optional: To copy the authentication token for use in an inference request, click the Copy
button ( 
 ) next to the token value.
### ACCESSING THE INFERENCE ENDPOINT FOR A DEPLOYED
MODEL
To make inference requests to your deployed model, you must know how to access the inference
endpoint that is available.
For a list of paths to use with the supported runtimes and example commands, see Inference endpoints.
Prerequisites
You have logged in to Red Hat OpenShift AI.
You have deployed a model by using the single-model serving platform.
If you enabled token authentication for your deployed model, you have the associated token
value.
d
## MAKING INFERENCE REQUESTS TO DEPLOYED MODELS
37

Procedure
1. From the OpenShift AI dashboard, click Models → Model deployments.
The inference endpoint for the model is shown in the Inference endpoint field.
2. Depending on what action you want to perform with the model (and if the model supports that
action), copy the inference endpoint and then add a path to the end of the URL.
3. Use the endpoint to make API requests to your deployed model.
Additional resources
Text Generation Inference Server (TGIS)
Caikit API documentation
Caikit NLP GitHub project
OpenVINO KServe-compatible REST API documentation
OpenAI API documentation
### MAKING INFERENCE REQUESTS TO MODELS DEPLOYED ON THE
SINGLE-MODEL SERVING PLATFORM
When you deploy a model by using the single-model serving platform, the model is available as a service
that you can access using API requests. This enables you to return predictions based on data inputs. To
use API requests to interact with your deployed model, you must know the inference endpoint for the
model.
In addition, if you secured your inference endpoint by enabling token authentication, you must know how
to access your authentication token so that you can specify this in your inference requests.
### INFERENCE ENDPOINTS
These examples show how to use inference endpoints to query the model.
NOTE
If you enabled token authentication when deploying the model, add the Authorization
header and specify a token value.
5.4.1. Caikit TGIS ServingRuntime for KServe
:443/api/v1/task/text-generation
:443/api/v1/task/server-streaming-text-generation
Example command
curl --json '{"model_id": "<model_name__>", "inputs": "<text>"}' 
https://<inference_endpoint_url>:443/api/v1/task/server-streaming-text-generation -H 'Authorization: 
Bearer <token>'
Red Hat OpenShift AI Cloud Service 1 Deploying models
38

5.4.2. Caikit Standalone ServingRuntime for KServe
If you are serving multiple models, you can query /info/models or :443 
caikit.runtime.info.InfoService/GetModelsInfo to view a list of served models.
REST endpoints
/api/v1/task/embedding
/api/v1/task/embedding-tasks
/api/v1/task/sentence-similarity
/api/v1/task/sentence-similarity-tasks
/api/v1/task/rerank
/api/v1/task/rerank-tasks
/info/models
/info/version
/info/runtime
gRPC endpoints
:443 caikit.runtime.Nlp.NlpService/EmbeddingTaskPredict
:443 caikit.runtime.Nlp.NlpService/EmbeddingTasksPredict
:443 caikit.runtime.Nlp.NlpService/SentenceSimilarityTaskPredict
:443 caikit.runtime.Nlp.NlpService/SentenceSimilarityTasksPredict
:443 caikit.runtime.Nlp.NlpService/RerankTaskPredict
:443 caikit.runtime.Nlp.NlpService/RerankTasksPredict
:443 caikit.runtime.info.InfoService/GetModelsInfo
:443 caikit.runtime.info.InfoService/GetRuntimeInfo
NOTE
By default, the Caikit Standalone Runtime exposes REST endpoints. To use gRPC
protocol, manually deploy a custom Caikit Standalone ServingRuntime. For more
information, see Adding a custom model-serving runtime for the single-model serving
platform.
An example manifest is available in the caikit-tgis-serving GitHub repository.
Example command
REST
## MAKING INFERENCE REQUESTS TO DEPLOYED MODELS
39

curl -H 'Content-Type: application/json' -d '{"inputs": "<text>", "model_id": "<model_id>"}' 
<inference_endpoint_url>/api/v1/task/embedding -H 'Authorization: Bearer <token>'
gRPC
grpcurl -d '{"text": "<text>"}' -H \"mm-model-id: <model_id>\" <inference_endpoint_url>:443 
caikit.runtime.Nlp.NlpService/EmbeddingTaskPredict -H 'Authorization: Bearer <token>'
5.4.3. TGIS Standalone ServingRuntime for KServe
IMPORTANT
The Text Generation Inference Server (TGIS) Standalone ServingRuntime for KServe
is deprecated. For more information, see OpenShift AI release notes.
:443 fmaas.GenerationService/Generate
:443 fmaas.GenerationService/GenerateStream
NOTE
To query the endpoint for the TGIS standalone runtime, you must also download
the files in the proto directory of the OpenShift AI text-generation-inference
repository.
Example command
grpcurl -proto text-generation-inference/proto/generation.proto -d '{"requests": [{"text":"<text>"}]}' -H 
'Authorization: Bearer <token>' -insecure <inference_endpoint_url>:443 
fmaas.GenerationService/Generate
5.4.4. OpenVINO Model Server
/v2/models/<model-name>/infer
Example command
curl -ks <inference_endpoint_url>/v2/models/<model_name>/infer -d '{ "model_name": "
<model_name>", "inputs": [{ "name": "<name_of_model_input>", "shape": [<shape>], "datatype": "
<data_type>", "data": [<data>] }]}' -H 'Authorization: Bearer <token>'
5.4.5. vLLM NVIDIA GPU ServingRuntime for KServe
:443/version
:443/docs
:443/v1/models
:443/v1/chat/completions
Red Hat OpenShift AI Cloud Service 1 Deploying models
40

:443/v1/completions
:443/v1/embeddings
:443/tokenize
:443/detokenize
NOTE
The vLLM runtime is compatible with the OpenAI REST API. For a list of
models that the vLLM runtime supports, see Supported models.
To use the embeddings inference endpoint in vLLM, you must use an
embeddings model that the vLLM supports. You cannot use the embeddings
endpoint with generative models. For more information, see Supported
embeddings models in vLLM.
As of vLLM v0.5.5, you must provide a chat template while querying a model
using the /v1/chat/completions endpoint. If your model does not include a
predefined chat template, you can use the chat-template command-line
parameter to specify a chat template in your custom vLLM runtime, as shown
in the example. Replace <CHAT_TEMPLATE> with the path to your
template.
containers:
  - args:
      - --chat-template=<CHAT_TEMPLATE>
You can use the chat templates that are available as .jinja files here or with
the vLLM image under /app/data/template. For more information, see Chat
templates.
As indicated by the paths shown, the single-model serving platform uses the HTTPS port of
your OpenShift router (usually port 443) to serve external API requests.
Example command
curl -v https://<inference_endpoint_url>:443/v1/chat/completions -H "Content-Type: application/json" -
d '{ "messages": [{ "role": "<role>", "content": "<content>" }] -H 'Authorization: Bearer <token>'
5.4.6. vLLM Intel Gaudi Accelerator ServingRuntime for KServe
See vLLM NVIDIA GPU ServingRuntime for KServe.
5.4.7. vLLM AMD GPU ServingRuntime for KServe
See vLLM NVIDIA GPU ServingRuntime for KServe.
5.4.8. vLLM Spyre AI Accelerator ServingRuntime for KServe
## MAKING INFERENCE REQUESTS TO DEPLOYED MODELS
41

IMPORTANT
Support for IBM Spyre AI Accelerators on x86 is currently available in Red Hat OpenShift
AI as a Technology Preview feature. Technology Preview features are not supported with
Red Hat production service level agreements (SLAs) and might not be functionally
complete. Red Hat does not recommend using them in production. These features
provide early access to upcoming product features, enabling customers to test
functionality and provide feedback during the development process.
For more information about the support scope of Red Hat Technology Preview features,
see Technology Preview Features Support Scope .
See vLLM NVIDIA GPU ServingRuntime for KServe.
5.4.9. NVIDIA Triton Inference Server
REST endpoints
v2/models/[/versions/<model_version>]/infer
v2/models/<model_name>[/versions/<model_version>]
v2/health/ready
v2/health/live
v2/models/<model_name>[/versions/]/ready
v2
NOTE
ModelMesh does not support the following REST endpoints:
v2/health/live
v2/health/ready
v2/models/<model_name>[/versions/]/ready
Example command
curl -ks <inference_endpoint_url>/v2/models/<model_name>/infer -d '{ "model_name": "
<model_name>", "inputs": [{ "name": "<name_of_model_input>", "shape": [<shape>], "datatype": "
<data_type>", "data": [<data>] }]}' -H 'Authorization: Bearer <token>'
gRPC endpoints
:443 inference.GRPCInferenceService/ModelInfer
:443 inference.GRPCInferenceService/ModelReady
:443 inference.GRPCInferenceService/ModelMetadata
Red Hat OpenShift AI Cloud Service 1 Deploying models
42

:443 inference.GRPCInferenceService/ServerReady
:443 inference.GRPCInferenceService/ServerLive
:443 inference.GRPCInferenceService/ServerMetadata
Example command
grpcurl -cacert ./openshift_ca_istio_knative.crt -proto ./grpc_predict_v2.proto -d @ -H "Authorization: 
Bearer <token>" <inference_endpoint_url>:443 inference.GRPCInferenceService/ModelMetadata
5.4.10. Seldon MLServer
REST endpoints
v2/models/[/versions/<model_version>]/infer
v2/models/<model_name>[/versions/<model_version>]
v2/health/ready
v2/health/live
v2/models/<model_name>[/versions/]/ready
v2
Example command
curl -ks <inference_endpoint_url>/v2/models/<model_name>/infer -d '{ "model_name": "
<model_name>", "inputs": [{ "name": "<name_of_model_input>", "shape": [<shape>], "datatype": "
<data_type>", "data": [<data>] }]}' -H 'Authorization: Bearer <token>'
gRPC endpoints
:443 inference.GRPCInferenceService/ModelInfer
:443 inference.GRPCInferenceService/ModelReady
:443 inference.GRPCInferenceService/ModelMetadata
:443 inference.GRPCInferenceService/ServerReady
:443 inference.GRPCInferenceService/ServerLive
:443 inference.GRPCInferenceService/ServerMetadata
Example command
grpcurl -cacert ./openshift_ca_istio_knative.crt -proto ./grpc_predict_v2.proto -d @ -H "Authorization: 
Bearer <token>" <inference_endpoint_url>:443 inference.GRPCInferenceService/ModelMetadata
## MAKING INFERENCE REQUESTS TO DEPLOYED MODELS
43

5.4.11. Additional resources
Text Generation Inference Server (TGIS)
Caikit API documentation
Caikit NLP GitHub project
OpenVINO KServe-compatible REST API documentation
OpenAI API documentation
Open Inference Protocol
Supported model-serving runtimes.
Red Hat OpenShift AI Cloud Service 1 Deploying models
44

---

## Openshift Ai Model Serving

Red Hat OpenShift AI Cloud Service 1
Configuring your model-serving platform
Configure your model-serving platform in Red Hat OpenShift AI Cloud Service
Last Updated: 2025-10-28

Red Hat OpenShift AI Cloud Service 1 Configuring your model-serving
platform
Configure your model-serving platform in Red Hat OpenShift AI Cloud Service

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
As a Red Hat OpenShift AI administrator, you can configure your model serving platform in Red Hat
OpenShift AI Cloud Service.

. . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 
. . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 
. . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 
. . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 
. . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 
Table of Contents
## ABOUT MODEL-SERVING PLATFORMS
### ABOUT MODEL SERVING
1.1.1. Single-model serving platform
1.1.2. Multi-model serving platform
1.1.3. NVIDIA NIM model serving platform
### MODEL-SERVING RUNTIMES
1.2.1. ServingRuntime
1.2.2. InferenceService
1.2.3. Model-serving runtimes for accelerators
1.2.3.1. NVIDIA GPUs
1.2.3.2. Intel Gaudi accelerators
1.2.3.3. AMD GPUs
1.2.3.4. IBM Spyre AI accelerators on x86
1.2.4. Supported model-serving runtimes
1.2.5. Tested and verified model-serving runtimes
## CONFIGURING MODEL SERVERS ON THE SINGLE-MODEL SERVING PLATFORM
### ABOUT THE SINGLE-MODEL SERVING PLATFORM
2.1.1. Components
2.1.2. Installation options
2.1.3. Authorization
2.1.4. Monitoring
### ENABLING THE SINGLE-MODEL SERVING PLATFORM
### ENABLING SPECULATIVE DECODING AND MULTI-MODAL INFERENCING
### ADDING A CUSTOM MODEL-SERVING RUNTIME FOR THE SINGLE-MODEL SERVING PLATFORM
### ADDING A TESTED AND VERIFIED MODEL-SERVING RUNTIME FOR THE SINGLE-MODEL SERVING
PLATFORM
## CONFIGURING MODEL SERVERS ON THE NVIDIA NIM MODEL SERVING PLATFORM
### ENABLING THE NVIDIA NIM MODEL SERVING PLATFORM
## CONFIGURING MODEL SERVERS ON THE MULTI-MODEL SERVING PLATFORM
### ENABLING THE MULTI-MODEL SERVING PLATFORM
### ADDING A CUSTOM MODEL-SERVING RUNTIME FOR THE MULTI-MODEL SERVING PLATFORM
### ADDING A TESTED AND VERIFIED MODEL-SERVING RUNTIME FOR THE MULTI-MODEL SERVING
PLATFORM
## CUSTOMIZING MODEL DEPLOYMENTS
### CUSTOMIZING THE PARAMETERS OF A DEPLOYED MODEL-SERVING RUNTIME
### CUSTOMIZABLE MODEL SERVING RUNTIME PARAMETERS
### CUSTOMIZING THE VLLM MODEL-SERVING RUNTIME
3
3
3
4
4
4
4
5
7
7
7
7
7
8
8
10
10
10
10
10
11
11
12
14
15
25
25
26
26
26
29
32
32
33
34
Table of Contents
1

Red Hat OpenShift AI Cloud Service 1 Configuring your model-serving platform
2
## ABOUT MODEL-SERVING PLATFORMS
As an OpenShift AI administrator, you can enable your preferred serving platform and make it available
for serving models. You can also add a custom or a tested and verified model-serving runtime.
### ABOUT MODEL SERVING
When you serve a model, you upload a trained model into Red Hat OpenShift AI for querying, which
allows you to integrate your trained models into intelligent applications.
You can upload a model to an S3-compatible object storage, persistent volume claim, or Open
Container Initiative (OCI) image. You can then access and train the model from your project workbench.
After training the model, you can serve or deploy the model using a model-serving platform.
Serving or deploying the model makes the model available as a service, or model runtime server, that
you can access using an API. You can then access the inference endpoints for the deployed model from
the dashboard and see predictions based on data inputs that you provide through API calls. Querying
the model through the API is also called model inferencing.
You can serve models on one of the following model-serving platforms:
Single-model serving platform
Multi-model serving platform
NVIDIA NIM model serving platform
The model-serving platform that you choose depends on your business needs:
If you want to deploy each model on its own runtime server, or want to use a serverless
deployment, select the single-model serving platform. The single-model serving platform is
recommended for production use.
If you want to deploy multiple models with only one runtime server, select the multi-model
serving platform. This option is best if you are deploying more than 1,000 small and medium
models and want to reduce resource consumption.
If you want to use NVIDIA Inference Microservices (NIMs) to deploy a model, select the NVIDIA
NIM-model serving platform.
1.1.1. Single-model serving platform
You can deploy each model from a dedicated model server on the single-model serving platform.
Deploying models from a dedicated model server can help you deploy, monitor, scale, and maintain
models that require increased resources. This model serving platform is ideal for serving large models.
The single-model serving platform is based on the KServe component.
The single-model serving platform is helpful for use cases such as:
Large language models (LLMs)
Generative AI
For more information about setting up the single-model serving platform, see Installing the single-
model serving platform.
## ABOUT MODEL-SERVING PLATFORMS
3

1.1.2. Multi-model serving platform
You can deploy multiple models from the same model server on the multi-model serving platform. Each
of the deployed models shares the server resources. Deploying multiple models from the same model
server can be advantageous on OpenShift clusters that have finite compute resources or pods. This
model serving platform is ideal for serving small and medium models in large quantities. The multi-
model serving platform is based on the ModelMesh component.
For more information about setting up the multi-model serving platform, see Installing the multi-model
serving platform.
1.1.3. NVIDIA NIM model serving platform
You can deploy models using NVIDIA Inference Microservices (NIM) on the NVIDIA NIM model serving
platform.
NVIDIA NIM, part of NVIDIA AI Enterprise, is a set of microservices designed for secure, reliable
deployment of high performance AI model inferencing across clouds, data centers and workstations.
NVIDIA NIM inference services are helpful for use cases such as:
Using GPU-accelerated containers inferencing models optimized by NVIDIA
Deploying generative AI for virtual screening, content generation, and avatar creation
The NVIDIA NIM model serving platform is based on the single-model serving platform. To use the
NVIDIA NIM model serving platform, you must first install the single-model serving platform.
For more information, see Installing the single-model serving platform.
### MODEL-SERVING RUNTIMES
You can serve models on the single-model serving platform by using model-serving runtimes. The
configuration of a model-serving runtime is defined by the ServingRuntime and InferenceService
custom resource definitions (CRDs).
1.2.1. ServingRuntime
The ServingRuntime CRD creates a serving runtime, an environment for deploying and managing a
model. It creates the templates for pods that dynamically load and unload models of various formats
and also exposes a service endpoint for inferencing requests.
The following YAML configuration is an example of the vLLM ServingRuntime for KServe model-
serving runtime. The configuration includes various flags, environment variables and command-line
arguments.
apiVersion: serving.kserve.io/v1alpha1
kind: ServingRuntime
metadata:
  annotations:
    opendatahub.io/recommended-accelerators: '["nvidia.com/gpu"]' 1
    openshift.io/display-name: vLLM ServingRuntime for KServe 2
  labels:
    opendatahub.io/dashboard: "true"
  name: vllm-runtime
Red Hat OpenShift AI Cloud Service 1 Configuring your model-serving platform
4

1
2
3
4
5
6
7
8
9
10
  namespace: <namespace>
spec:
  annotations:
    prometheus.io/path: /metrics 3
    prometheus.io/port: "8080" 4
  containers:
    - args:
        - --port=8080
        - --model=/mnt/models 5
        - --served-model-name={{.Name}} 6
      command: 7
        - python
        - '-m'
        - vllm.entrypoints.openai.api_server
      env:
        - name: HF_HOME
          value: /tmp/hf_home
      image: 
quay.io/modh/vllm@sha256:8a3dd8ad6e15fe7b8e5e471037519719d4d8ad3db9d69389f2beded36a6f5
b21 8
      name: kserve-container
      ports:
        - containerPort: 8080
          protocol: TCP
  multiModel: false 9
  supportedModelFormats: 10
    - autoSelect: true
      name: vLLM
The recommended accelerator to use with the runtime.
The name with which the serving runtime is displayed.
The endpoint used by Prometheus to scrape metrics for monitoring.
The port used by Prometheus to scrape metrics for monitoring.
The path to where the model files are stored in the runtime container.
Passes the model name that is specified by the {{.Name}} template variable inside the runtime
container specification to the runtime environment. The {{.Name}} variable maps to the 
spec.predictor.name field in the InferenceService metadata object.
The entrypoint command that starts the runtime container.
The runtime container image used by the serving runtime. This image differs depending on the
type of accelerator used.
Specifies that the runtime is used for single-model serving.
Specifies the model formats supported by the runtime.
1.2.2. InferenceService
## ABOUT MODEL-SERVING PLATFORMS
5

The InferenceService CRD creates a server or inference service that processes inference queries,
passes it to the model, and then returns the inference output.
The inference service also performs the following actions:
Specifies the location and format of the model.
Specifies the serving runtime used to serve the model.
Enables the passthrough route for gRPC or REST inference.
Defines HTTP or gRPC endpoints for the deployed model.
The following example shows the InferenceService YAML configuration file that is generated when
deploying a granite model with the vLLM runtime:
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
Additional resources
KServe Serving Runtimes
Red Hat OpenShift AI Cloud Service 1 Configuring your model-serving platform
6

1.2.3. Model-serving runtimes for accelerators
OpenShift AI provides support for accelerators through preinstalled model-serving runtimes.
1.2.3.1. NVIDIA GPUs
You can serve models with NVIDIA graphics processing units (GPUs) by using the vLLM NVIDIA GPU
ServingRuntime for KServe runtime. To use the runtime, you must enable GPU support in OpenShift
AI. This includes installing and configuring the Node Feature Discovery Operator on your cluster. For
more information, see Installing the Node Feature Discovery Operator  and Enabling NVIDIA GPUs.
1.2.3.2. Intel Gaudi accelerators
You can serve models with Intel Gaudi accelerators by using the vLLM Intel Gaudi Accelerator
ServingRuntime for KServe runtime. To use the runtime, you must enable hybrid processing (HPU)
support in OpenShift AI. This includes installing the Intel Gaudi Base Operator and configuring a
hardware profile. For more information, see Intel Gaudi Base Operator OpenShift installation and
Working with hardware profiles.
For information about recommended vLLM parameters, environment variables, supported
configurations and more, see vLLM with Intel® Gaudi® AI Accelerators.
NOTE
Warm-up is a model initialization and performance optimization step that is useful for
reducing cold-start delays and first-inference latency. Depending on the model size,
warm-up can lead to longer model loading times.
While highly recommended in production environments to avoid performance limitations,
you can choose to skip warm-up for non-production environments to reduce model
loading times and accelerate model development and testing cycles. To skip warm-up,
follow the steps described in Customizing the parameters of a deployed model-serving
runtime to add the following environment variable in the Configuration parameters
section of your model deployment:
`VLLM_SKIP_WARMUP="true"`
1.2.3.3. AMD GPUs
You can serve models with AMD GPUs by using the vLLM AMD GPU ServingRuntime for KServe
runtime. To use the runtime, you must enable support for AMD graphic processing units (GPUs) in
OpenShift AI. This includes installing the AMD GPU operator and configuring a hardware profile. For
more information, see Deploying the AMD GPU operator on OpenShift  in the AMD documentation and
Working with hardware profiles.
1.2.3.4. IBM Spyre AI accelerators on x86
## ABOUT MODEL-SERVING PLATFORMS
7

IMPORTANT
Support for IBM Spyre AI Accelerators on x86 is currently available in Red Hat OpenShift
AI as a Technology Preview feature. Technology Preview features are not supported with
Red Hat production service level agreements (SLAs) and might not be functionally
complete. Red Hat does not recommend using them in production. These features
provide early access to upcoming product features, enabling customers to test
functionality and provide feedback during the development process.
For more information about the support scope of Red Hat Technology Preview features,
see Technology Preview Features Support Scope .
You can serve models with IBM Spyre AI accelerators on x86 by using the vLLM Spyre AI Accelerator
ServingRuntime for KServe runtime. To use the runtime, you must install the Spyre Operator and
configure a hardware profile. For more information, see Spyre operator image and Working with
hardware profiles.
Additional resources
Supported model-serving runtimes
1.2.4. Supported model-serving runtimes
OpenShift AI includes several preinstalled model-serving runtimes. You can use preinstalled model-
serving runtimes to start serving models without modifying or defining the runtime yourself. You can
also add a custom runtime to support a model.
See Supported configurations for a list of the supported model-serving runtimes and deployment
requirements.
For help adding a custom runtime, see Adding a custom model-serving runtime for the single-model
serving platform.
Additional resources
Inference endpoints
1.2.5. Tested and verified model-serving runtimes
Tested and verified runtimes are community versions of model-serving runtimes that have been tested
and verified against specific versions of OpenShift AI.
Red Hat tests the current version of a tested and verified runtime each time there is a new version of
OpenShift AI. If a new version of a tested and verified runtime is released in the middle of an OpenShift
AI release cycle, it will be tested and verified in an upcoming release.
See Supported configurations for a list of tested and verified runtimes in OpenShift AI.
NOTE
Tested and verified runtimes are not directly supported by Red Hat. You are responsible
for ensuring that you are licensed to use any tested and verified runtimes that you add,
and for correctly configuring and maintaining them.
Red Hat OpenShift AI Cloud Service 1 Configuring your model-serving platform
8

For more information, see Tested and verified runtimes in OpenShift AI .
Additional resources
Inference endpoints
## ABOUT MODEL-SERVING PLATFORMS
9
## CONFIGURING MODEL SERVERS ON THE
SINGLE-MODEL SERVING PLATFORM
On the single-model serving platform, you configure model servers by using model-serving runtimes. A
model-serving runtime adds support for a specified set of model frameworks and the model formats
that they support.
### ABOUT THE SINGLE-MODEL SERVING PLATFORM
For deploying large models such as large language models (LLMs), OpenShift AI includes a single-
model serving platform that is based on the KServe component. Because each model is deployed on its
own model server, the single-model serving platform helps you to deploy, monitor, scale, and maintain
large models that require increased resources.
2.1.1. Components
KServe: A Kubernetes custom resource definition (CRD) that orchestrates model serving for all
types of models. KServe includes model-serving runtimes that implement the loading of given
types of model servers. KServe also handles the lifecycle of the deployment object, storage
access, and networking setup.
Red Hat OpenShift Serverless: A cloud-native development model that allows for serverless
deployments of models. OpenShift Serverless is based on the open source Knative project.
Red Hat OpenShift Service Mesh: A service mesh networking layer that manages traffic flows
and enforces access policies. OpenShift Service Mesh is based on the open source Istio project.
2.1.2. Installation options
To install the single-model serving platform, you have the following options:
Automated installation
If you have not already created a ServiceMeshControlPlane or KNativeServing resource on your
OpenShift cluster, you can configure the Red Hat OpenShift AI Operator to install KServe and
configure its dependencies.
For more information about automated installation, see Configuring automated installation of
KServe.
Manual installation
If you have already created a ServiceMeshControlPlane or KNativeServing resource on your
OpenShift cluster, you cannot configure the Red Hat OpenShift AI Operator to install KServe and
configure its dependencies. In this situation, you must install KServe manually.
For more information about manual installation, see Manually installing KServe.
2.1.3. Authorization
You can add Authorino as an authorization provider for the single-model serving platform. Adding an
authorization provider allows you to enable token authentication for models that you deploy on the
platform, which ensures that only authorized parties can make inference requests to the models.
To add Authorino as an authorization provider on the single-model serving platform, you have the
following options:
Red Hat OpenShift AI Cloud Service 1 Configuring your model-serving platform
10

If automated installation of the single-model serving platform is possible on your cluster, you
can include Authorino as part of the automated installation process.
If you need to manually install the single-model serving platform, you must also manually
configure Authorino.
For guidance on choosing an installation option for the single-model serving platform, see Installation
options.
2.1.4. Monitoring
You can configure monitoring for the single-model serving platform and use Prometheus to scrape
metrics for each of the pre-installed model-serving runtimes.
### ENABLING THE SINGLE-MODEL SERVING PLATFORM
When you have installed KServe, you can use the Red Hat OpenShift AI dashboard to enable the single-
model serving platform. You can also use the dashboard to enable model-serving runtimes for the
platform.
Prerequisites
You have logged in to OpenShift AI as a user with OpenShift AI administrator privileges.
You have installed KServe.
The spec.dashboardConfig.disableKServe dashboard configuration option is set to false (the
default).
For more information about setting dashboard configuration options, see Customizing the
dashboard.
Procedure
1. Enable the single-model serving platform as follows:
a. In the left menu, click Settings → Cluster settings.
b. Locate the Model serving platforms section.
c. To enable the single-model serving platform for projects, select the Single-model serving
platform checkbox.
d. Select KServe RawDeployment or Knative Serverless deployment mode.
For more information about these deployment mode options, see About KServe
deployment modes.
e. Click Save changes.
2. Enable preinstalled runtimes for the single-model serving platform as follows:
a. In the left menu of the OpenShift AI dashboard, click Settings → Serving runtimes.
The Serving runtimes page shows preinstalled runtimes and any custom runtimes that you
have added.
For more information about preinstalled runtimes, see Supported runtimes.
## CONFIGURING MODEL SERVERS ON THE SINGLE-MODEL SERVING PLATFORM
11

b. Set the runtime that you want to use to Enabled.
The single-model serving platform is now available for model deployments.
### ENABLING SPECULATIVE DECODING AND MULTI-MODAL
INFERENCING
You can configure the vLLM NVIDIA GPU ServingRuntime for KServe runtime to use speculative
decoding, a parallel processing technique to optimize inferencing time for large language models
(LLMs).
You can also configure the runtime to support inferencing for vision-language models (VLMs). VLMs
are a subset of multi-modal models that integrate both visual and textual data.
The following procedure describes customizing the vLLM NVIDIA GPU ServingRuntime for KServe
runtime for speculative decoding and multi-modal inferencing.
Prerequisites
You have logged in to OpenShift AI as a user with OpenShift AI administrator privileges.
If you are using the vLLM model-serving runtime for speculative decoding with a draft model,
you have stored the original model and the speculative model in the same folder within your S3-
compatible object storage.
Procedure
1. Follow the steps to deploy a model as described in Deploying models on the single-model
serving platform.
2. In the Serving runtime field, select the vLLM NVIDIA GPU ServingRuntime for KServe
runtime.
3. To configure the vLLM model-serving runtime for speculative decoding by matching n-grams in
the prompt, add the following arguments under Additional serving runtime arguments in the
Configuration parameters section:
--speculative-model=[ngram]
--num-speculative-tokens=<NUM_SPECULATIVE_TOKENS>
--ngram-prompt-lookup-max=<NGRAM_PROMPT_LOOKUP_MAX>
--use-v2-block-manager
a. Replace <NUM_SPECULATIVE_TOKENS> and <NGRAM_PROMPT_LOOKUP_MAX>
with your own values.
NOTE
Inferencing throughput varies depending on the model used for speculating
with n-grams.
4. To configure the vLLM model-serving runtime for speculative decoding with a draft model, add
the following arguments under Additional serving runtime arguments in the Configuration
parameters section:
--port=8080
Red Hat OpenShift AI Cloud Service 1 Configuring your model-serving platform
12

--served-model-name={{.Name}}
--distributed-executor-backend=mp
--model=/mnt/models/<path_to_original_model>
--speculative-model=/mnt/models/<path_to_speculative_model>
--num-speculative-tokens=<NUM_SPECULATIVE_TOKENS>
--use-v2-block-manager
a. Replace <path_to_speculative_model> and <path_to_original_model> with the paths to
the speculative model and original model on your S3-compatible object storage.
b. Replace <NUM_SPECULATIVE_TOKENS> with your own value.
5. To configure the vLLM model-serving runtime for multi-modal inferencing, add the following
arguments under Additional serving runtime arguments in the Configuration parameters
section:
--trust-remote-code
NOTE
Only use the --trust-remote-code argument with models from trusted sources.
6. Click Deploy.
Verification
If you have configured the vLLM model-serving runtime for speculative decoding, use the
following example command to verify API requests to your deployed model:
curl -v https://<inference_endpoint_url>:443/v1/chat/completions
-H "Content-Type: application/json"
-H "Authorization: Bearer <token>"
If you have configured the vLLM model-serving runtime for multi-modal inferencing, use the
following example command to verify API requests to the vision-language model (VLM) that
you have deployed:
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
Additional resources
## CONFIGURING MODEL SERVERS ON THE SINGLE-MODEL SERVING PLATFORM
13

Additional resources
vLLM: Engine Arguments
OpenAI Compatible Server
### ADDING A CUSTOM MODEL-SERVING RUNTIME FOR THE
SINGLE-MODEL SERVING PLATFORM
A model-serving runtime adds support for a specified set of model frameworks and the model formats
supported by those frameworks. You can use the preinstalled runtimes that are included with OpenShift
AI. You can also add your own custom runtimes if the default runtimes do not meet your needs.
As an administrator, you can use the OpenShift AI interface to add and enable a custom model-serving
runtime. You can then choose the custom runtime when you deploy a model on the single-model serving
platform.
NOTE
Red Hat does not provide support for custom runtimes. You are responsible for ensuring
that you are licensed to use any custom runtimes that you add, and for correctly
configuring and maintaining them.
Prerequisites
You have logged in to OpenShift AI as a user with OpenShift AI administrator privileges.
You have built your custom runtime and added the image to a container image repository such
as Quay.
Procedure
1. From the OpenShift AI dashboard, click Settings → Serving runtimes.
The Serving runtimes page opens and shows the model-serving runtimes that are already
installed and enabled.
2. To add a custom runtime, choose one of the following options:
To start with an existing runtime (for example, vLLM NVIDIA GPU ServingRuntime for
KServe), click the action menu (⋮) next to the existing runtime and then click Duplicate.
To add a new custom runtime, click Add serving runtime.
3. In the Select the model serving platforms this runtime supports list, select Single-model
serving platform.
4. In the Select the API protocol this runtime supports list, select REST or gRPC.
5. Optional: If you started a new runtime (rather than duplicating an existing one), add your code
by choosing one of the following options:
Upload a YAML file
a. Click Upload files.
b. In the file browser, select a YAML file on your computer.
Red Hat OpenShift AI Cloud Service 1 Configuring your model-serving platform
14

The embedded YAML editor opens and shows the contents of the file that you
uploaded.
Enter YAML code directly in the editor
a. Click Start from scratch.
b. Enter or paste YAML code directly in the embedded editor.
NOTE
In many cases, creating a custom runtime will require adding new or custom
parameters to the env section of the ServingRuntime specification.
6. Click Add.
The Serving runtimes page opens and shows the updated list of runtimes that are installed.
Observe that the custom runtime that you added is automatically enabled. The API protocol
that you specified when creating the runtime is shown.
7. Optional: To edit your custom runtime, click the action menu (⋮) and select Edit.
Verification
The custom model-serving runtime that you added is shown in an enabled state on the Serving
runtimes page.
### ADDING A TESTED AND VERIFIED MODEL-SERVING RUNTIME
FOR THE SINGLE-MODEL SERVING PLATFORM
In addition to preinstalled and custom model-serving runtimes, you can also use Red Hat tested and
verified model-serving runtimes to support your requirements. For more information about Red Hat
tested and verified runtimes, see Tested and verified runtimes for Red Hat OpenShift AI .
You can use the Red Hat OpenShift AI dashboard to add and enable tested and verified runtimes for
the single-model serving platform. You can then choose the runtime when you deploy a model on the
single-model serving platform.
Prerequisites
You have logged in to OpenShift AI as a user with OpenShift AI administrator privileges.
If you are deploying the IBM Z Accelerated for NVIDIA Triton Inference Server runtime, you have
access to IBM Cloud Container Registry to pull the container image. For more information about
obtaining credentials to the IBM Cloud Container Registry, see Downloading the IBM Z
Accelerated for NVIDIA Triton Inference Server container image.
If you are deploying the IBM Power Accelerated Triton Inference Server runtime, you can access
the container image from the Triton Inference Server Quay repository .
Procedure
1. From the OpenShift AI dashboard, click Settings → Serving runtimes.
The Serving runtimes page opens and shows the model-serving runtimes that are already
installed and enabled.
## CONFIGURING MODEL SERVERS ON THE SINGLE-MODEL SERVING PLATFORM
15

2. Click Add serving runtime.
3. In the Select the model serving platforms this runtime supports list, select Single-model
serving platform.
4. In the Select the API protocol this runtime supports list, select REST or gRPC.
5. Click Start from scratch.
6. Follow these steps to add the IBM Power Accelerated for NVIDIA Triton Inference Server
runtime:
a. If you selected the REST API protocol, enter or paste the following YAML code directly in
the embedded editor.
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
7. Follow these steps to add the IBM Z Accelerated for NVIDIA Triton Inference Server runtime:
a. If you selected the REST API protocol, enter or paste the following YAML code directly in
the embedded editor.
apiVersion: serving.kserve.io/v1alpha1
kind: ServingRuntime
metadata:
  name: ibmz-triton-rest
  labels:
    opendatahub.io/dashboard: "true"
spec:
  containers:
Red Hat OpenShift AI Cloud Service 1 Configuring your model-serving platform
16

    - name: kserve-container
      command:
        - /bin/sh
        - -c
      args:
        - /opt/tritonserver/bin/tritonserver --model-repository=/mnt/models --http-port=8000 -
-grpc-port=8001 --metrics-port=8002
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
b. If you selected the gRPC API protocol, enter or paste the following YAML code directly in
the embedded editor.
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
## CONFIGURING MODEL SERVERS ON THE SINGLE-MODEL SERVING PLATFORM
17

        - /opt/tritonserver/bin/tritonserver --model-repository=/mnt/models --grpc-port=8001 
--http-port=8000 --metrics-port=8002
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
8. Follow these steps to add the NVIDIA Triton Inference Server runtime:
a. If you selected the REST API protocol, enter or paste the following YAML code directly in
the embedded editor.
apiVersion: serving.kserve.io/v1alpha1
kind: ServingRuntime
metadata:
  name: triton-kserve-rest
  labels:
    opendatahub.io/dashboard: "true"
spec:
Red Hat OpenShift AI Cloud Service 1 Configuring your model-serving platform
18

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
b. If you selected the gRPC API protocol, enter or paste the following YAML code directly in
the embedded editor.
apiVersion: serving.kserve.io/v1alpha1
kind: ServingRuntime
## CONFIGURING MODEL SERVERS ON THE SINGLE-MODEL SERVING PLATFORM
19

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
Red Hat OpenShift AI Cloud Service 1 Configuring your model-serving platform
20

    - autoSelect: true
      name: python
      version: "1"
  volumes:
    - name: shm
      emptyDir: null
        medium: Memory
        sizeLimit: 2Gi
9. Follow these steps to add the Seldon MLServer runtime:
a. If you selected the REST API protocol, enter or paste the following YAML code directly in
the embedded editor.
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
      image: 
'docker.io/seldonio/mlserver@sha256:07890828601515d48c0fb73842aaf197cbcf245a5c85
5c789e890282b15ce390'
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
## CONFIGURING MODEL SERVERS ON THE SINGLE-MODEL SERVING PLATFORM
21

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
b. If you selected the gRPC API protocol, enter or paste the following YAML code directly in
the embedded editor.
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
Red Hat OpenShift AI Cloud Service 1 Configuring your model-serving platform
22

    prometheus.kserve.io/path: /metrics
  containers:
    - name: kserve-container
      image: 
'docker.io/seldonio/mlserver@sha256:07890828601515d48c0fb73842aaf197cbcf245a5c85
5c789e890282b15ce390'
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
## CONFIGURING MODEL SERVERS ON THE SINGLE-MODEL SERVING PLATFORM
23

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
10. In the metadata.name field, make sure that the value of the runtime you are adding does not
match a runtime that you have already added.
11. Optional: To use a custom display name for the runtime that you are adding, add a 
metadata.annotations.openshift.io/display-name field and specify a value, as shown in the
following example:
apiVersion: serving.kserve.io/v1alpha1
kind: ServingRuntime
metadata:
  name: kserve-triton
  annotations:
    openshift.io/display-name: Triton ServingRuntime
NOTE
If you do not configure a custom display name for your runtime, OpenShift AI
shows the value of the metadata.name field.
12. Click Create.
The Serving runtimes page opens and shows the updated list of runtimes that are installed.
Observe that the runtime that you added is automatically enabled. The API protocol that you
specified when creating the runtime is shown.
13. Optional: To edit the runtime, click the action menu (⋮) and select Edit.
Verification
The model-serving runtime that you added is shown in an enabled state on the Serving
runtimes page.
Additional resources
Tested and verified model-serving runtimes
Red Hat OpenShift AI Cloud Service 1 Configuring your model-serving platform
24
## CONFIGURING MODEL SERVERS ON THE NVIDIA
NIM MODEL SERVING PLATFORM
You configure and create a model server on the NVIDIA NIM model serving platform when you deploy an
NVIDIA-optimized model. During the deployment process, you select a specific NIM from the available
list and configure its properties, such as the number of replicas, server size, and the hardware profile.
### ENABLING THE NVIDIA NIM MODEL SERVING PLATFORM
As an OpenShift AI administrator, you can use the Red Hat OpenShift AI dashboard to enable the
NVIDIA NIM model serving platform.
Prerequisites
You have logged in to OpenShift AI as a user with OpenShift AI administrator privileges.
You have enabled the single-model serving platform. You do not need to enable a preinstalled
runtime. For more information about enabling the single-model serving platform, see Enabling
the single-model serving platform.
The disableNIMModelServing dashboard configuration option is set to false.
For more information about setting dashboard configuration options, see Customizing the
dashboard.
You have enabled GPU support in OpenShift AI. This includes installing the Node Feature
Discovery Operator and NVIDIA GPU Operator. For more information, see Installing the Node
Feature Discovery Operator and Enabling NVIDIA GPUs.
You have an NVIDIA Cloud Account (NCA) and can access the NVIDIA GPU Cloud (NGC)
portal. For more information, see NVIDIA GPU Cloud user guide.
Your NCA account is associated with the NVIDIA AI Enterprise Viewer role.
You have generated a personal API key on the NGC portal. For more information, see
Generating a Personal API Key .
Procedure
1. In the left menu of the OpenShift AI dashboard, click Applications → Explore.
2. On the Explore page, find the NVIDIA NIM tile.
3. Click Enable on the application tile.
4. Enter your personal API key and then click Submit.
Verification
The NVIDIA NIM application that you enabled is displayed on the Enabled page.
## CONFIGURING MODEL SERVERS ON THE NVIDIA NIM MODEL SERVING PLATFORM
25
## CONFIGURING MODEL SERVERS ON THE
MULTI-MODEL SERVING PLATFORM
On the multi-model serving platform, you configure model servers for your data science project before
you deploy models. A model server can host multiple models, which share the server’s resources.
### ENABLING THE MULTI-MODEL SERVING PLATFORM
To use the multi-model serving platform, you must first enable the platform. The multi-model serving
platform uses the ModelMesh component.
Prerequisites
You have logged in to OpenShift AI as a user with OpenShift AI administrator privileges.
The spec.dashboardConfig.disableModelMesh dashboard configuration option is set to false
(the default).
For more information about setting dashboard configuration options, see Customizing the
dashboard.
Procedure
1. In the left menu of the OpenShift AI dashboard, click Settings → Cluster settings.
2. Locate the Model serving platforms section.
3. Select the Multi-model serving platform checkbox.
4. Click Save changes.
### ADDING A CUSTOM MODEL-SERVING RUNTIME FOR THE MULTI-
MODEL SERVING PLATFORM
A model-serving runtime adds support for a specified set of model frameworks and the model formats
supported by those frameworks. By default, the multi-model serving platform includes the OpenVINO
Model Server runtime. You can also add your own custom runtime if the default runtime does not meet
your needs, such as supporting a specific model format.
As an administrator, you can use the Red Hat OpenShift AI dashboard to add and enable a custom
model-serving runtime. You can then choose the custom runtime when you create a new model server
for the multi-model serving platform.
NOTE
Red Hat does not provide support for custom runtimes. You are responsible for ensuring
that you are licensed to use any custom runtimes that you add, and for correctly
configuring and maintaining them.
Prerequisites
You have logged in to OpenShift AI as a user with OpenShift AI administrator privileges.
Red Hat OpenShift AI Cloud Service 1 Configuring your model-serving platform
26

You are familiar with how to add a model server to your project . When you have added a custom
model-serving runtime, you must configure a new model server to use the runtime.
You have reviewed the example runtimes in the kserve/modelmesh-serving repository. You can
use these examples as starting points. However, each runtime requires some further
modification before you can deploy it in OpenShift AI. The required modifications are described
in the following procedure.
NOTE
OpenShift AI includes the OpenVINO Model Server runtime by default. You do
not need to add this runtime to OpenShift AI.
Procedure
1. From the OpenShift AI dashboard, click Settings → Serving runtimes.
The Serving runtimes page opens and shows the model-serving runtimes that are already
installed and enabled.
2. To add a custom runtime, choose one of the following options:
To start with an existing runtime (for example the OpenVINO Model Server runtime), click
the action menu (⋮) next to the existing runtime and then click Duplicate.
To add a new custom runtime, click Add serving runtime.
3. In the Select the model serving platforms this runtime supports list, select Multi-model
serving platform.
NOTE
The multi-model serving platform supports only the REST protocol. Therefore,
you cannot change the default value in the Select the API protocol this runtime
supports list.
4. Optional: If you started a new runtime (rather than duplicating an existing one), add your code
by choosing one of the following options:
Upload a YAML file
a. Click Upload files.
b. In the file browser, select a YAML file on your computer. This file might be the one of
the example runtimes that you downloaded from the kserve/modelmesh-serving
repository.
The embedded YAML editor opens and shows the contents of the file that you
uploaded.
Enter YAML code directly in the editor
a. Click Start from scratch.
b. Enter or paste YAML code directly in the embedded editor. The YAML that you paste
might be copied from one of the example runtimes in the kserve/modelmesh-serving
repository.
## CONFIGURING MODEL SERVERS ON THE MULTI-MODEL SERVING PLATFORM
27

5. Optional: If you are adding one of the example runtimes in the kserve/modelmesh-serving
repository, perform the following modifications:
a. In the YAML editor, locate the kind field for your runtime. Update the value of this field to 
ServingRuntime.
b. In the kustomization.yaml file in the kserve/modelmesh-serving repository, take note of the 
newName and newTag values for the runtime that you want to add. You will specify these
values in a later step.
c. In the YAML editor for your custom runtime, locate the containers.image field.
d. Update the value of the containers.image field in the format newName:newTag, based on
the values that you previously noted in the kustomization.yaml file. Some examples are
shown.
Nvidia Triton Inference Server
image: nvcr.io/nvidia/tritonserver:23.04-py3
Seldon Python MLServer
image: seldonio/mlserver:1.3.2
TorchServe
image: pytorch/torchserve:0.7.1-cpu
6. In the metadata.name field, ensure that the value of the runtime you are adding is unique (that
is, the value does not match a runtime that you have already added).
7. Optional: To configure a custom display name for the runtime that you are adding, add a 
metadata.annotations.openshift.io/display-name field and specify a value, as shown in the
following example:
apiVersion: serving.kserve.io/v1alpha1
kind: ServingRuntime
metadata:
  name: mlserver-0.x
  annotations:
    openshift.io/display-name: MLServer
NOTE
If you do not configure a custom display name for your runtime, OpenShift AI
shows the value of the metadata.name field.
8. Click Add.
The Serving runtimes page opens and shows the updated list of runtimes that are installed.
Observe that the runtime you added is automatically enabled.
9. Optional: To edit your custom runtime, click the action menu (⋮) and select Edit.
Verification
The custom model-serving runtime that you added is shown in an enabled state on the Serving
runtimes page.
Red Hat OpenShift AI Cloud Service 1 Configuring your model-serving platform
28

Additional resources
To learn how to configure a model server that uses a custom model-serving runtime that you
have added, see Adding a model server to your data science project .
### ADDING A TESTED AND VERIFIED MODEL-SERVING RUNTIME
FOR THE MULTI-MODEL SERVING PLATFORM
In addition to preinstalled and custom model-serving runtimes, you can also use Red Hat tested and
verified model-serving runtimes such as the NVIDIA Triton Inference Server to support your needs. For
more information about Red Hat tested and verified runtimes, see Tested and verified runtimes for
Red Hat OpenShift AI.
You can use the Red Hat OpenShift AI dashboard to add and enable the NVIDIA Triton Inference
Server runtime and then choose the runtime when you create a new model server for the multi-model
serving platform.
Prerequisites
You have logged in to OpenShift AI as a user with OpenShift AI administrator privileges.
You are familiar with how to add a model server to your project . After you have added a tested
and verified model-serving runtime, you must configure a new model server to use the runtime.
Procedure
1. From the OpenShift AI dashboard, click Settings → Serving runtimes.
The Serving runtimes page opens and shows the model-serving runtimes that are already
installed and enabled.
2. To add a tested and verified runtime, click Add serving runtime.
3. In the Select the model serving platforms this runtime supports list, select Multi-model
serving platform.
NOTE
The multi-model serving platform supports only the REST protocol. Therefore,
you cannot change the default value in the Select the API protocol this runtime
supports list.
4. Click Start from scratch.
5. Enter or paste the following YAML code directly in the embedded editor.
apiVersion: serving.kserve.io/v1alpha1
kind: ServingRuntime
metadata:
  annotations:
    enable-route: "true"
  name: modelmesh-triton
  labels:
    opendatahub.io/dashboard: "true"
spec:
## CONFIGURING MODEL SERVERS ON THE MULTI-MODEL SERVING PLATFORM
29

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
          tritonserver "--model-repository=/models/_triton_models" "--model-control-
mode=explicit" "--strict-model-config=false" "--strict-readiness=false" "--allow-http=true" "--
allow-grpc=true"  '
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
Red Hat OpenShift AI Cloud Service 1 Configuring your model-serving platform
30

      name: xgboost
      version: "1"
    - autoSelect: true
      name: python
      version: "1"
6. In the metadata.name field, make sure that the value of the runtime you are adding does not
match a runtime that you have already added).
7. Optional: To use a custom display name for the runtime that you are adding, add a 
metadata.annotations.openshift.io/display-name field and specify a value, as shown in the
following example:
apiVersion: serving.kserve.io/v1alpha1
kind: ServingRuntime
metadata:
  name: modelmesh-triton
  annotations:
    openshift.io/display-name: Triton ServingRuntime
NOTE
If you do not configure a custom display name for your runtime, OpenShift AI
shows the value of the metadata.name field.
8. Click Create.
The Serving runtimes page opens and shows the updated list of runtimes that are installed.
Observe that the runtime you added is automatically enabled.
9. Optional: To edit the runtime, click the action menu (⋮) and select Edit.
Verification
The model-serving runtime that you added is shown in an enabled state on the Serving
runtimes page.
Additional resources
To learn how to configure a model server that uses a model-serving runtime that you have
added, see Adding a model server to your data science project .
## CONFIGURING MODEL SERVERS ON THE MULTI-MODEL SERVING PLATFORM
31
## CUSTOMIZING MODEL DEPLOYMENTS
You can customize a model’s deployment on the single-model serving platform to suit your specific
needs, for example, to deploy a particular family of models or to enhance an existing deployment. You
can modify the runtime configuration for a specific deployment by setting additional serving runtime
arguments and environment variables.
These customizations apply only to the selected model deployment and do not change the default
runtime configuration. You can set these parameters when you first deploy a model or by editing an
existing deployment.
### CUSTOMIZING THE PARAMETERS OF A DEPLOYED MODEL-
SERVING RUNTIME
You might need additional parameters beyond the default ones to deploy specific models or to enhance
an existing model deployment. In such cases, you can modify the parameters of an existing runtime to
suit your deployment needs.
NOTE
Customizing the parameters of a runtime only affects the selected model deployment.
Prerequisites
You have logged in to OpenShift AI as a user with OpenShift AI administrator privileges.
You have deployed a model on the single-model serving platform.
Procedure
1. From the OpenShift AI dashboard, click Models → Model deployments.
The Model deployments page opens.
2. Click Stop next to the name of the model you want to customize.
3. Click the action menu (⋮) and select Edit.
The Configuration parameters section shows predefined serving runtime parameters, if any
are available.
4. Customize the runtime parameters in the Configuration parameters section:
a. Modify the values in Additional serving runtime arguments to define how the deployed
model behaves.
b. Modify the values in Additional environment variables to define variables in the model’s
environment.
NOTE
Do not modify the port or model serving runtime arguments, because they
require specific values to be set. Overwriting these parameters can cause the
deployment to fail.
5. After you are done customizing the runtime parameters, click Redeploy to save.
Red Hat OpenShift AI Cloud Service 1 Configuring your model-serving platform
32

6. Click Start to deploy the model with your changes.
Verification
Confirm that the deployed model is shown on the Models tab for the project, and on the Model
deployments page of the dashboard with a checkmark in the Status column.
Confirm that the arguments and variables that you set appear in spec.predictor.model.args
and spec.predictor.model.env by one of the following methods:
Checking the InferenceService YAML from the OpenShift Console.
Using the following command in the OpenShift CLI:
oc get -o json inferenceservice <inferenceservicename/modelname> -n <projectname>
### CUSTOMIZABLE MODEL SERVING RUNTIME PARAMETERS
You can modify the parameters of an existing model serving runtime to suit your deployment needs.
For more information about parameters for each of the supported serving runtimes, see the following
table:
Serving runtime
Resource
Caikit Text Generation Inference Server (Caikit-
TGIS) ServingRuntime for KServe
Caikit NLP: Configuration
TGIS: Model configuration
Caikit Standalone ServingRuntime for KServe
Caikit NLP: Configuration
NVIDIA Triton Inference Server
NVIDIA Triton Inference Server: Model Parameters
OpenVINO Model Server
OpenVINO Model Server Features: Dynamic Input
Parameters
Seldon MLServer
MLServer Documentation: Model Settings
[Deprecated] Text Generation Inference Server
(TGIS) Standalone ServingRuntime for KServe
TGIS: Model configuration
vLLM NVIDIA GPU ServingRuntime for KServe
vLLM: Engine Arguments
OpenAI-Compatible Server
vLLM AMD GPU ServingRuntime for KServe
vLLM: Engine Arguments
OpenAI-Compatible Server
vLLM Intel Gaudi Accelerator ServingRuntime for
KServe
vLLM: Engine Arguments
OpenAI-Compatible Server
Additional resources
## CUSTOMIZING MODEL DEPLOYMENTS
33

1
2
1
Customizing the parameters of a deployed model serving runtime
### CUSTOMIZING THE VLLM MODEL-SERVING RUNTIME
In certain cases, you may need to add additional flags or environment variables to the vLLM
ServingRuntime for KServe runtime to deploy a family of LLMs.
The following procedure describes customizing the vLLM model-serving runtime to deploy a Llama,
Granite or Mistral model.
Prerequisites
You have logged in to OpenShift AI as a user with OpenShift AI administrator privileges.
For Llama model deployment, you have downloaded a meta-llama-3 model to your object
storage.
For Granite model deployment, you have downloaded a granite-7b-instruct or granite-20B-
code-instruct model to your object storage.
For Mistral model deployment, you have downloaded a mistral-7B-Instruct-v0.3 model to your
object storage.
You have enabled the vLLM ServingRuntime for KServe runtime.
You have enabled GPU support in OpenShift AI and have installed and configured the Node
Feature Discovery Operator on your cluster. For more information, see Installing the Node
Feature Discovery Operator and Enabling NVIDIA GPUs
Procedure
1. Follow the steps to deploy a model as described in Deploying models on the single-model
serving platform.
2. In the Serving runtime field, select vLLM ServingRuntime for KServe.
3. If you are deploying a meta-llama-3 model, add the following arguments under Additional
serving runtime arguments in the Configuration parameters section:
–-distributed-executor-backend=mp 1
--max-model-len=6144 2
Sets the backend to multiprocessing for distributed model workers
Sets the maximum context length of the model to 6144 tokens
4. If you are deploying a granite-7B-instruct model, add the following arguments under Additional
serving runtime arguments in the Configuration parameters section:
--distributed-executor-backend=mp 1
Sets the backend to multiprocessing for distributed model workers
Red Hat OpenShift AI Cloud Service 1 Configuring your model-serving platform
34

1
2
3
1
2
5. If you are deploying a granite-20B-code-instruct model, add the following arguments under
Additional serving runtime arguments in the Configuration parameters section:
--distributed-executor-backend=mp 1
–-tensor-parallel-size=4 2
--max-model-len=6448 3
Sets the backend to multiprocessing for distributed model workers
Distributes inference across 4 GPUs in a single node
Sets the maximum context length of the model to 6448 tokens
6. If you are deploying a mistral-7B-Instruct-v0.3 model, add the following arguments under
Additional serving runtime arguments in the Configuration parameters section:
--distributed-executor-backend=mp 1
--max-model-len=15344 2
Sets the backend to multiprocessing for distributed model workers
Sets the maximum context length of the model to 15344 tokens
7. Click Deploy.
Verification
Confirm that the deployed model is shown on the Models tab for the project, and on the Model
deployments page of the dashboard with a checkmark in the Status column.
For granite models, use the following example command to verify API requests to your deployed
model:
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
Additional resources
vLLM: Engine Arguments
## CUSTOMIZING MODEL DEPLOYMENTS
35

---

## Openshift Ai Llama Stack

Red Hat OpenShift AI Cloud Service 1
Working with Llama Stack
Working with Llama Stack in Red Hat OpenShift AI Cloud Service
Last Updated: 2025-10-31

Red Hat OpenShift AI Cloud Service 1 Working with Llama Stack
Working with Llama Stack in Red Hat OpenShift AI Cloud Service

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
As a cluster administrator, you can use the Llama Stack Operator in Red Hat OpenShift AI.

. . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 
. . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 
. . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 
. . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 
Table of Contents
## OVERVIEW OF LLAMA STACK
### THE LLAMASTACKDISTRIBUTION CUSTOM RESOURCE API PROVIDERS
### OPENAI COMPATIBILITY FOR RAG APIS IN LLAMA STACK
### OPENAI-COMPATIBLE APIS IN LLAMA STACK
1.3.1. Supported OpenAI-compatible APIs in OpenShift AI
1.3.1.1. Chat Completions API
1.3.1.2. Completions API
1.3.1.3. Embeddings API
1.3.1.4. Files API
1.3.1.5. Vector Stores API
1.3.1.6. Vector Store Files API
1.3.1.7. Models API
1.3.1.8. Responses API
## ACTIVATING THE LLAMA STACK OPERATOR
## DEPLOYING A RAG STACK IN A DATA SCIENCE PROJECT
### OVERVIEW OF RAG
3.1.1. Audience for RAG
### OVERVIEW OF VECTOR DATABASES
### OVERVIEW OF MILVUS VECTOR DATABASES
### DEPLOYING A LLAMA MODEL WITH KSERVE
### TESTING YOUR VLLM MODEL ENDPOINTS
### DEPLOYING A REMOTE MILVUS VECTOR DATABASE
### DEPLOYING A LLAMASTACKDISTRIBUTION INSTANCE
3.7.1. Example A: LlamaStackDistribution with Inline Milvus
3.7.2. Example B: LlamaStackDistribution with Remote Milvus
### INGESTING CONTENT INTO A LLAMA MODEL
### QUERYING INGESTED CONTENT IN A LLAMA MODEL
### PREPARING DOCUMENTS WITH DOCLING FOR LLAMA STACK RETRIEVAL
### ABOUT LLAMA STACK SEARCH TYPES
3.11.1. Supported search modes
3.11.1.1. Keyword search
3.11.1.2. Vector search
3.11.1.3. Hybrid search
3.11.2. Retrieval database support
## BENCHMARKING EMBEDDING MODELS WITH BEIR DATASETS AND LLAMA STACK
3
3
5
6
6
6
6
6
6
7
7
7
7
9
11
11
12
12
13
14
17
20
24
25
26
28
31
34
38
38
38
38
38
38
39
Table of Contents
1

Red Hat OpenShift AI Cloud Service 1 Working with Llama Stack
2
## OVERVIEW OF LLAMA STACK
Llama Stack is a unified AI runtime environment designed to simplify the deployment and management
of generative AI workloads on OpenShift AI. Llama Stack integrates LLM inference servers, vector
databases, and retrieval services in a single stack, optimized for Retrieval-Augmented Generation
(RAG) and agent-based AI workflows. In OpenShift, the Llama Stack Operator manages the
deployment lifecycle of these components, ensuring scalability, consistency, and integration with
OpenShift AI projects.
IMPORTANT
Llama Stack integration is currently available in Red Hat OpenShift AI as a Technology
Preview feature. Technology Preview features are not supported with Red Hat production
service level agreements (SLAs) and might not be functionally complete. Red Hat does
not recommend using them in production. These features provide early access to
upcoming product features, enabling customers to test functionality and provide
feedback during the development process.
For more information about the support scope of Red Hat Technology Preview features,
see Technology Preview Features Support Scope .
Llama Stack includes the following components:
Inference model servers such as vLLM, designed to efficiently serve large language models.
Vector storage solutions, primarily Milvus, to store embeddings generated from your domain
data.
Retrieval and embedding management workflows using integrated tools, such as Docling, to
handle continuous data ingestion and synchronization.
Integration with OpenShift AI by using the LlamaStackDistribution custom resource,
simplifying configuration and deployment.
For information about how to deploy Llama Stack in OpenShift AI, see Deploying a RAG stack in a data
science project.
### THE LLAMASTACKDISTRIBUTION CUSTOM RESOURCE API PROVIDERS
The LlamaStackDistribution custom resource includes various API types and providers that you can
use in OpenShift AI. The following table displays the supported providers that are included in the
distribution:
API type
Providers
How to Enable
Support status
Agents
inline::meta-
reference
Enabled by default
Technology
Preview
DatasetIO
inline::localfs
Enabled by default
Technology
Preview
## OVERVIEW OF LLAMA STACK
3

remote::huggin
gface
Enabled by default
Technology
Preview
Evaluation
remote::trustyai
_lmeval
Set the EMBEDDING_MODEL
environment variable
Technology
Preview
Files
inline::localfs
Enabled by default
Technology
Preview
Inference
inline::sentence
-transformers
Enabled by default
Technology
Preview
remote::vllm
Set the VLLM_URL environment
variable
Technology
Preview
remote::azure
Set the AZURE_API_KEY environment
variable
Technology
Preview
remote::bedroc
k
Set the AWS_ACCESS_KEY_ID
environment variable
Technology
Preview
remote::openai
Set the OPENAI_API_KEY
environment variable
Technology
Preview
remote::vertexai
Set the VERTEX_AI_PROJECT
environment variable
Technology
Preview
remote::watson
x
Set the WATSONX_API_KEY
environment variable
Technology
Preview
Safety
remote::trustyai
_fms
Enabled by default
Technology
Preview
Scoring
inline::llm-as-a-
judge
Enabled by default
Technology
Preview
inline::basic
Enabled by default
Technology
Preview
inline::braintrus
t
Enabled by default
Technology
Preview
Telemetry
inline::meta-
reference
Enabled by default
Technology
Preview
API type
Providers
How to Enable
Support status
Red Hat OpenShift AI Cloud Service 1 Working with Llama Stack
4

Tool_runtime
inline::rag-
runtime
Enabled by default
Technology
Preview
remote::brave-
search
Enabled by default
Technology
Preview
remote::tavily-
search
Enabled by default
Technology
Preview
remote::model-
context-
protocol
Enabled by default
Technology
Preview
VectorIO
inline::milvus
Enabled by default
Technology
Preview
remote::milvus
Set the MILVUS_ENDPOINT
environment variable
Technology
Preview
API type
Providers
How to Enable
Support status
Additional resources
Llama Stack Demos repository
Llama Stack Kubernetes Operator documentation
Llama Stack documentation
### OPENAI COMPATIBILITY FOR RAG APIS IN LLAMA STACK
OpenShift AI supports OpenAI-compatible request and response schemas for Llama Stack RAG
workflows. You can use OpenAI clients and schemas for files, vector stores, and Responses API file
search end-to-end.
OpenAI compatibility enables the following capabilities:
You can use OpenAI SDKs and tools with Llama Stack by setting the client base_url to the
Llama Stack OpenAI path, /v1/openai/v1.
You can manage files and vector stores by using OpenAI-compatible endpoints. You can then
invoke RAG workflows by using the Responses API with the file_search tool.
Additional resources
OpenAI API Compatibility
OpenAI API Reference
llama-stack-client-python
## OVERVIEW OF LLAMA STACK
5
### OPENAI-COMPATIBLE APIS IN LLAMA STACK
OpenShift AI includes a Llama Stack component that exposes OpenAI-compatible APIs. These APIs
enable you to reuse existing OpenAI SDKs, tools, and workflows directly within your OpenShift
environment, without changing your client code. This compatibility layer supports retrieval-augmented
generation (RAG), inference, and embedding workloads by using the same endpoints, schemas, and
authentication model as OpenAI.
This compatibility layer has the following capabilities:
Standardized endpoints: REST API paths align with OpenAI specifications.
Schema parity: Request and response fields follow OpenAI data structures.
NOTE
When connecting OpenAI SDKs or third-party tools to OpenShift AI, you must update the
client configuration to use your deployment’s Llama Stack route as the base_url. This
ensures that API calls are sent to the OpenAI-compatible endpoints that run inside your
OpenShift cluster, rather than to the public OpenAI service.
1.3.1. Supported OpenAI-compatible APIs in OpenShift AI
1.3.1.1. Chat Completions API
Endpoint: /v1/openai/v1/chat/completions.
Providers: All inference back ends deployed through OpenShift AI.
Support level: Technology Preview.
The Chat Completions API enables conversational, message-based interactions with models served by
Llama Stack in OpenShift AI.
1.3.1.2. Completions API
Endpoint: /v1/openai/v1/completions.
Providers: All inference backends managed by OpenShift AI.
Support level: Technology Preview.
The Completions API supports single-turn text generation and prompt completion.
1.3.1.3. Embeddings API
Endpoint: /v1/openai/v1/embeddings.
Providers: All embedding models enabled in OpenShift AI.
The Embeddings API generates numerical embeddings for text or documents that can be used in
downstream semantic search or RAG applications.
1.3.1.4. Files API
Red Hat OpenShift AI Cloud Service 1 Working with Llama Stack
6

Endpoint: /v1/openai/v1/files.
Providers: File system-based file storage provider for managing files and documents stored
locally in your cluster.
Support level: Technology Preview.
The Files API manages file uploads for use in embedding and retrieval workflows.
1.3.1.5. Vector Stores API
Endpoint: /v1/openai/v1/vector_stores/.
Providers: Inline and Remote Milvus configured in OpenShift AI.
Support level: Technology Preview.
The Vector Stores API manages the creation, configuration, and lifecycle of vector store resources in
Llama Stack. Through this API, you can create new vector stores, list existing ones, delete unused stores,
and query their metadata, all using OpenAI-compatible request and response formats.
1.3.1.6. Vector Store Files API
Endpoint: /v1/openai/v1/vector_stores/{vector_store_id}/files.
Providers: Local inline provider configured for file storage and retrieval.
Support level: Developer Preview.
The Vector Store Files API implements the OpenAI Vector Store Files interface and manages the link
between document files and Milvus vector stores used for RAG.
1.3.1.7. Models API
Endpoint: /v1/openai/v1/models.
Providers: All model-serving back ends configured within OpenShift AI.
Support level: Technology Preview.
The Models API lists and retrieves available model resources from the Llama Stack deployment running
on OpenShift AI. By using the Models API, you can enumerate models, view their capabilities, and verify
deployment status through a standardized OpenAI-compatible interface.
1.3.1.8. Responses API
Endpoint: /v1/openai/v1/responses.
Providers: All inference and retrieval providers configured in OpenShift AI.
Support level: Developer Preview.
The Responses API generates model outputs by combining inference, file search, and tool-calling
capabilities through a single OpenAI-compatible endpoint. It is particularly useful for retrieval-
augmented generation (RAG) workflows that rely on the file_search tool to retrieve context from
vector stores.
## OVERVIEW OF LLAMA STACK
7

NOTE
The Responses API is an experimental feature that is still under active development in
OpenShift AI. While the API is already functional and suitable for evaluation, some
endpoints and parameters remain under implementation and might change in future
releases. This API is provided for testing and feedback purposes only and is not
recommended for production use.
Additional resources
OpenAI API Compatibility
Red Hat OpenShift AI Cloud Service 1 Working with Llama Stack
8
## ACTIVATING THE LLAMA STACK OPERATOR
You can activate the Llama Stack Operator on your OpenShift cluster by setting its managementState
to Managed in the OpenShift AI Operator DataScienceCluster custom resource (CR). This setting
enables Llama-based model serving without reinstalling or directly editing Operator subscriptions. You
can edit the CR in the OpenShift web console or by using the OpenShift CLI (oc).
NOTE
As an alternative to following the steps in this procedure, you can activate the Llama
Stack Operator from the OpenShift CLI (oc) by running the following command:
$ oc patch datasciencecluster <name> --type=merge -p {"spec":{"components":
{"llamastackoperator":{"managementState":"Managed"}}}}
Replace <name> with your DataScienceCluster name, for example, default-dsc.
Prerequisites
You have installed OpenShift 4.17 or newer.
You have cluster administrator privileges.
You have installed the OpenShift CLI (oc) as described in the appropriate documentation for
your cluster:
Installing the OpenShift CLI for OpenShift Dedicated
Installing the OpenShift CLI for Red Hat OpenShift Service on AWS (classic architecture)
You have installed the Red Hat OpenShift AI Operator on your cluster.
You have a DataScienceCluster custom resource in your environment; the default is default-
dsc.
Your infrastructure supports GPU-enabled instance types, for example, g4dn.xlarge on AWS.
You have enabled GPU support in OpenShift AI, including installing the Node Feature Discovery
Operator and NVIDIA GPU Operator. For more information, see Installing the Node Feature
Discovery Operator and Enabling NVIDIA GPUs.
You have created a NodeFeatureDiscovery resource instance on your cluster, as described in
Installing the Node Feature Discovery Operator and creating a NodeFeatureDiscovery instance
in the NVIDIA documentation.
You have created a ClusterPolicy resource instance with default values on your cluster, as
described in Creating the ClusterPolicy instance in the NVIDIA documentation.
Procedure
1. Log in to the OpenShift web console as a cluster administrator.
2. In the Administrator perspective, click Operators → Installed Operators.
3. Click the Red Hat OpenShift AI Operator to open its details.
## ACTIVATING THE LLAMA STACK OPERATOR
9

4. Click the Data Science Cluster tab.
5. On the DataScienceClusters page, click the default-dsc object.
6. Click the YAML tab.
An embedded YAML editor opens, displaying the configuration for the DataScienceCluster
custom resource.
7. In the YAML editor, locate the spec.components section. If the llamastackoperator field does
not exist, add it. Then, set the managementState field to Managed:
8. Click Save to apply your changes.
Verification
After you activate the Llama Stack Operator, verify that it is running in your cluster:
1. In the OpenShift web console, click Workloads → Pods.
2. From the Project list, select the redhat-ods-applications namespace.
3. Confirm that a pod with the label app.kubernetes.io/name=llama-stack-operator is displayed
and has a status of Running.
spec:
  components:
    llamastackoperator:
      managementState: Managed
Red Hat OpenShift AI Cloud Service 1 Working with Llama Stack
10
## DEPLOYING A RAG STACK IN A DATA SCIENCE
PROJECT
IMPORTANT
This feature is currently available in Red Hat OpenShift AI as a Technology Preview
feature. Technology Preview features are not supported with Red Hat production service
level agreements (SLAs) and might not be functionally complete. Red Hat does not
recommend using them in production. These features provide early access to upcoming
product features, enabling customers to test functionality and provide feedback during
the development process.
For more information about the support scope of Red Hat Technology Preview features,
see Technology Preview Features Support Scope .
As an OpenShift cluster administrator, you can deploy a Retrieval-Augmented Generation (RAG) stack
in OpenShift AI. This stack provides the infrastructure, including LLM inference, vector storage, and
retrieval services that data scientists and AI engineers use to build conversational workflows in their
projects.
To deploy the RAG stack in a data science project, complete the following tasks:
Activate the Llama Stack Operator in OpenShift AI.
Enable GPU support on the OpenShift cluster. This task includes installing the required NVIDIA
Operators.
Deploy an inference model, for example, the llama-3.2-3b-instruct model. This task includes
creating a storage connection and configuring GPU allocation.
Create a LlamaStackDistribution instance to enable RAG functionality. This action deploys
LlamaStack alongside a Milvus vector store and connects both components to the inference
model.
Ingest domain data into Milvus by running Docling in a data science pipeline or Jupyter
notebook. This process keeps the embeddings synchronized with the source data.
Expose and secure the model endpoints.
### OVERVIEW OF RAG
Retrieval-augmented generation (RAG) in OpenShift AI enhances large language models (LLMs) by
integrating domain-specific data sources directly into the model’s context. Domain-specific data
sources can be structured data, such as relational database tables, or unstructured data, such as PDF
documents.
RAG indexes content and builds an embedding store that data scientists and AI engineers can query.
When data scientists or AI engineers pose a question to a RAG chatbot, the RAG pipeline retrieves the
most relevant pieces of data, passes them to the LLM as context, and generates a response that
reflects both the prompt and the retrieved content.
By implementing RAG, data scientists and AI engineers can obtain tailored, accurate, and verifiable
answers to complex queries based on their own datasets within a data science project.
## DEPLOYING A RAG STACK IN A DATA SCIENCE PROJECT
11

3.1.1. Audience for RAG
The target audience for RAG is practitioners who build data-grounded conversational AI applications
using OpenShift AI infrastructure.
For Data Scientists
Data scientists can use RAG to prototype and validate models that answer natural-language queries
against data sources without managing low-level embedding pipelines or vector stores. They can
focus on creating prompts and evaluating model outputs instead of building retrieval infrastructure.
For MLOps Engineers
MLOps engineers typically deploy and operate RAG pipelines in production. Within OpenShift AI,
they manage LLM endpoints, monitor performance, and ensure that both retrieval and generation
scale reliably. RAG decouples vector store maintenance from the serving layer, enabling MLOps
engineers to apply CI/CD workflows to data ingestion and model deployment alike.
For Data Engineers
Data engineers build workflows to load data into storage that OpenShift AI indexes. They keep
embeddings in sync with source systems, such as S3 buckets or relational tables to ensure that
chatbot responses are accurate.
For AI Engineers
AI engineers architect RAG chatbots by defining prompt templates, retrieval methods, and fallback
logic. They configure agents and add domain-specific tools, such as OpenShift job triggers, enabling
rapid iteration.
### OVERVIEW OF VECTOR DATABASES
Vector databases are a crucial component of retrieval-augmented generation (RAG) in OpenShift AI.
They store and index vector embeddings that represent the semantic meaning of text or other data.
When you integrate vector databases with Llama Stack in OpenShift AI, you can build RAG applications
that combine large language models (LLMs) with relevant, domain-specific knowledge.
Vector databases provide you with the following capabilities:
Store vector embeddings generated by embedding models.
Support efficient similarity search to retrieve semantically related content.
Enable RAG workflows by supplying the LLM with contextually relevant data from a specific
domain.
When you deploy RAG workloads in OpenShift AI, you can deploy vector databases through the Llama
Stack Operator. Currently, OpenShift AI supports the following vector databases:
Inline Milvus Lite An Inline Milvus vector database runs embedded within the Llama Stack
Distribution (LSD) pod and is suitable for lightweight experimentation and small-scale
development. Inline Milvus stores data in a local SQLite database and is limited in scale and
persistence.
Remote Milvus A remote Milvus vector database runs as a standalone service in your project
namespace or as an external managed deployment. Remote Milvus is recommended for
production-grade RAG use cases because it provides persistence, scalability, and isolation from
the Llama Stack Distribution (LSD) pod. In OpenShift environments, you must deploy Milvus
with an etcd service directly in your project. For more information on using etcd services, see
Providing redundancy with etcd.
Red Hat OpenShift AI Cloud Service 1 Working with Llama Stack
12

Consider the following points when you decide on the vector database to use for your RAG workloads:
Use inline Milvus Lite if you want to experiment quickly with RAG in a self-contained setup and
do not require persistence across pod restarts.
Use remote Milvus if you need reliable storage, high availability, and the ability to scale out RAG
workloads in your OpenShift AI environment.
### OVERVIEW OF MILVUS VECTOR DATABASES
Milvus is an open source vector database designed for high-performance similarity search across
embedding data. In OpenShift AI, Milvus is supported as a remote vector database provider for the
Llama Stack Operator. Milvus enables retrieval-augmented generation (RAG) workloads that require
persistence, scalability, and efficient search across large document collections.
Milvus vector databases provide you with the following capabilities in OpenShift AI:
Similarity search using Approximate Nearest Neighbor (ANN) algorithms.
Persistent storage support for vectors.
Indexing and query optimizations for embedding-based search.
Integration with external metadata and APIs.
In OpenShift AI, you can use Milvus vector databases in the following operational modes:
Inline Milvus Lite, which runs embedded in the Llama Stack Distribution pod for testing or
small-scale experiments.
Remote Milvus, which runs as a standalone service in your OpenShift project or as an external
managed Milvus service. Remote Milvus is recommended for production workloads.
When you deploy a remote Milvus vector database, you must run the following components in your
OpenShift project:
Secret (milvus-secret): Stores sensitive data such as the Milvus root password.
PersistentVolumeClaim (milvus-pvc): Provides persistent storage for Milvus data.
Deployment (etcd-deployment): Runs an etcd instance that Milvus uses for metadata storage
and service coordination.
Service (etcd-service): Exposes the etcd port for Milvus to connect to.
Deployment (milvus-standalone): Runs Milvus in standalone mode and connects it to the etcd
service and PVC.
Service (milvus-service): Exposes Milvus gRPC (19530) and HTTP (9091 health check) ports
for client access.
Milvus requires an etcd service to manage metadata such as collections, indexes, and partitions, and to
provide service discovery and coordination among Milvus components. Even when running in standalone
mode, Milvus depends on etcd to operate correctly and maintain metadata consistency. For more
information on using etcd services, see Providing redundancy with etcd.
## DEPLOYING A RAG STACK IN A DATA SCIENCE PROJECT
13

IMPORTANT
Do not use the OpenShift control plane etcd for Milvus. You must deploy a separate etcd
instance inside your project or connect to an external etcd service.
Use Remote Milvus when you require a persistent, scalable, and production-ready vector database that
integrates seamlessly with OpenShift AI. Consider choosing a remote Milvus vector database if your
deployment must cater for the following requirements:
Persistent vector storage across restarts or upgrades.
Scalable indexing and high-performance vector search.
A production-grade RAG architecture integrated with OpenShift AI.
### DEPLOYING A LLAMA MODEL WITH KSERVE
To use Llama Stack and retrieval-augmented generation (RAG) workloads in OpenShift AI, you must
deploy a Llama model with a vLLM model server and configure KServe in KServe RawDeployment
mode.
Prerequisites
You have installed OpenShift 4.17 or newer.
You have logged in to Red Hat OpenShift AI.
You have cluster administrator privileges for your OpenShift cluster.
You have activated the Llama Stack Operator.
You have installed KServe.
You have enabled the single-model serving platform. For more information about enabling the
single-model serving platform, see Enabling the single-model serving platform.
You can access the single-model serving platform in the dashboard configuration. For more
information about setting dashboard configuration options, see Customizing the dashboard.
You have enabled GPU support in OpenShift AI, including installing the Node Feature Discovery
Operator and NVIDIA GPU Operator. For more information, see Installing the Node Feature
Discovery Operator and Enabling NVIDIA GPUs.
You have installed the OpenShift CLI (oc) as described in the appropriate documentation for
your cluster:
Installing the OpenShift CLI for OpenShift Dedicated
Installing the OpenShift CLI for Red Hat OpenShift Service on AWS (classic architecture)
You have created a data science project.
The vLLM serving runtime is installed and available in your environment.
You have created a storage connection for your model that contains a URI - v1 connection type.
This storage connection must define the location of your Llama 3.2 model artifacts. For
Red Hat OpenShift AI Cloud Service 1 Working with Llama Stack
14

example, oci://quay.io/redhat-ai-services/modelcar-catalog:llama-3.2-3b-instruct. For more
information about creating storage connections, see Adding a connection to your data science
project.
PROCEDURE
These steps are only supported in OpenShift AI versions 2.19 and later.
1. In the OpenShift AI dashboard, navigate to the project details page and click the Models tab.
2. In the Single-model serving platform tile, click Select single-model.
3. Click the Deploy model button.
The Deploy model dialog opens.
4. Configure the deployment properties for your model:
a. In the Model deployment name field, enter a unique name for your deployment.
b. In the Serving runtime field, select vLLM NVIDIA GPU serving runtime for KServe from
the drop-down list.
c. In the Deployment mode field, select KServe RawDeployment from the drop-down list.
d. Set Number of model server replicas to deploy to 1.
e. In the Model server size field, select Custom from the drop-down list.
Set CPUs requested to 1 core.
Set Memory requested to 10 GiB.
Set CPU limit to 2 core.
Set Memory limit to 14 GiB.
Set Accelerator to NVIDIA GPUs.
Set Accelerator count to 1.
f. From the Connection type, select a relevant data connection from the drop-down list.
5. In the Additional serving runtime arguments field, specify the following recommended
arguments:
a. Click Deploy.
--dtype=half
--max-model-len=20000
--gpu-memory-utilization=0.95
--enable-chunked-prefill
--enable-auto-tool-choice
--tool-call-parser=llama3_json
--chat-template=/app/data/template/tool_chat_template_llama3.2_json.jinja
## DEPLOYING A RAG STACK IN A DATA SCIENCE PROJECT
15

NOTE
Model deployment can take several minutes, especially for the first model
that is deployed on the cluster. Initial deployment may take more than 10
minutes while the relevant images download.
Verification
1. Verify that the kserve-controller-manager and odh-model-controller pods are running:
a. Open a new terminal window.
b. Log in to your OpenShift cluster from the CLI:
c. In the upper-right corner of the OpenShift web console, click your user name and select
Copy login command.
d. After you have logged in, click Display token.
e. Copy the Log in with this token command and paste it in the OpenShift CLI ( oc).
$ oc login --token=<token> --server=<openshift_cluster_url>
f. Enter the following command to verify that the kserve-controller-manager and odh-
model-controller pods are running:
g. Confirm that you see output similar to the following example:
kserve-controller-manager-7c865c9c9f-xyz12   1/1     Running   0          4m21s
odh-model-controller-7b7d5fd9cc-wxy34        1/1     Running   0          3m55s
h. If you do not see either of the kserve-controller-manager and odh-model-controller pods,
there could be a problem with your deployment. In addition, if the pods appear in the list, but
their Status is not set to Running, check the pod logs for errors:
i. Check the status of the inference service:
The deployment automatically creates the following resources:
A ServingRuntime resource.
An InferenceService resource, a Deployment, a pod, and a service pointing to the
pod.
Verify that the server is running. For example:
$ oc get pods -n redhat-ods-applications | grep -E 'kserve-controller-manager|odh-model-
controller'
$ oc logs <pod-name> -n redhat-ods-applications
$ oc get inferenceservice -n llamastack
$ oc get pods -n <data science project name> | grep llama
Red Hat OpenShift AI Cloud Service 1 Working with Llama Stack
16

Check for output similar to the following example log:
The deployed model displays in the Models tab on the Data Science project details
page for the project it was deployed under.
2. If you see a ConvertTritonGPUToLLVM error in the pod logs when querying the 
/v1/chat/completions API, and the vLLM server restarts or returns a 500 Internal Server error,
apply the following workaround:
Before deploying the model, remove the --enable-chunked-prefill argument from the
Additional serving runtime arguments field in the deployment dialog.
The error is displayed similar to the following:
### TESTING YOUR VLLM MODEL ENDPOINTS
To verify that your deployed Llama 3.2 model is accessible externally, ensure that your vLLM model
server is exposed as a network endpoint. You can then test access to the model from outside both the
OpenShift cluster and the OpenShift AI interface.
IMPORTANT
If you selected Make deployed models available through an external route during
deployment, your vLLM model endpoint is already accessible outside the cluster. You do
not need to manually expose the model server. Manually exposing vLLM model
endpoints, for example, by using oc expose, creates an unsecured route unless you
configure authentication. Avoid exposing endpoints without security controls to prevent
unauthorized access.
Prerequisites
You have cluster administrator privileges for your OpenShift cluster.
You have logged in to Red Hat OpenShift AI.
You have activated the Llama Stack Operator in OpenShift AI.
$ oc logs llama-32-3b-instruct-predictor-77f6574f76-8nl4r  -n <data science project 
name>
INFO     2025-05-15 11:23:52,750 __main__:498 server: Listening on ['::', 
'0.0.0.0']:8321
INFO:     Started server process [1]
INFO:     Waiting for application startup.
INFO     2025-05-15 11:23:52,765 __main__:151 server: Starting up
INFO:     Application startup complete.
INFO:     Uvicorn running on http://['::', '0.0.0.0']:8321 (Press CTRL+C to quit)
/opt/vllm/lib64/python3.12/site-packages/vllm/attention/ops/prefix_prefill.py:36:0: error: 
Failures have been detected while processing an MLIR pass pipeline
/opt/vllm/lib64/python3.12/site-packages/vllm/attention/ops/prefix_prefill.py:36:0: note: 
Pipeline failed while executing [`ConvertTritonGPUToLLVM` on 'builtin.module' operation]: 
reproducer generated at `std::errs, please share the reproducer above with Triton project.`
INFO:     10.129.2.8:0 - "POST /v1/chat/completions HTTP/1.1" 500 Internal Server Error
## DEPLOYING A RAG STACK IN A DATA SCIENCE PROJECT
17

You have deployed an inference model, for example, the llama-3.2-3b-instruct model.
You have installed the OpenShift CLI (oc) as described in the appropriate documentation for
your cluster:
Installing the OpenShift CLI for OpenShift Dedicated
Installing the OpenShift CLI for Red Hat OpenShift Service on AWS (classic architecture)
Procedure
1. Open a new terminal window.
a. Log in to your OpenShift cluster from the CLI:
b. In the upper-right corner of the OpenShift web console, click your user name and select
Copy login command.
c. After you have logged in, click Display token.
d. Copy the Log in with this token command and paste it in the OpenShift CLI ( oc).
$ oc login --token=<token> --server=<openshift_cluster_url>
2. If you enabled Require token authentication during model deployment, retrieve your token:
3. Obtain your model endpoint URL:
If you enabled Make deployed models available through an external route during model
deployment, click Endpoint details on the Model deployments page in the OpenShift AI
dashboard to obtain your model endpoint URL.
In addition, if you did not enable Require token authentication during model deployment,
you can also enter the following command to retrieve the endpoint URL:
4. Test the endpoint with a sample chat completion request:
If you did not enable Require token authentication during model deployment, enter a chat
completion request. For example:
$ export MODEL_TOKEN=$(oc get secret default-name-llama-32-3b-instruct-sa -n <project 
name> --template={{ .data.token }} | base64 -d)
$ export MODEL_ENDPOINT="https://$(oc get route llama-32-3b-instruct -n <project 
name> --template={{ .spec.host }})"
$ curl -X POST $MODEL_ENDPOINT/v1/chat/completions \
 -H "Content-Type: application/json" \
 -d '{
 "model": "llama-32-3b-instruct",
 "messages": [
   {
     "role": "user",
     "content": "Hello"
Red Hat OpenShift AI Cloud Service 1 Working with Llama Stack
18

If you enabled Require token authentication during model deployment, include a token in
your request. For example:
NOTE
The -k flag disables SSL verification and should only be used in test
environments or with self-signed certificates.
Verification
Confirm that you received a JSON response containing a chat completion. For example:
   }
 ]
}'
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
      "content": "Hello! It's nice to meet you. Is there something I can help you with or would you like to 
chat?",
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
## DEPLOYING A RAG STACK IN A DATA SCIENCE PROJECT
19

If you do not receive a response similar to the example, verify that the endpoint URL and token are
correct, and ensure your model deployment is running.
### DEPLOYING A REMOTE MILVUS VECTOR DATABASE
To use Milvus as a remote vector database provider for Llama Stack in OpenShift AI, you must deploy
Milvus and its required etcd service in your OpenShift project. This procedure shows how to deploy
Milvus in standalone mode without the Milvus Operator.
NOTE
The following example configuration is intended for testing or evaluation environments.
For production-grade deployments, see https://milvus.io/docs in the Milvus
documentation.
Prerequisites
You have installed OpenShift 4.17 or newer.
You have enabled GPU support in OpenShift AI. This includes installing the Node Feature
Discovery operator and NVIDIA GPU Operators. For more information, see Installing the Node
Feature Discovery operator and Enabling NVIDIA GPUs.
You have cluster administrator privileges for your OpenShift cluster.
You are logged in to Red Hat OpenShift AI.
You have a StorageClass available that can provision persistent volumes.
You created a root password to secure your Milvus service.
You have deployed an inference model with vLLM, for example, the llama-3.2-3b-instruct
model, and you have selected Make deployed models available through an external route and
Require token authentication during model deployment.
You have the correct inference model identifier, for example, llama-3-2-3b.
You have the model endpoint URL, ending with /v1, such as https://llama-32-3b-instruct-
predictor:8443/v1.
You have the API token required to access the model endpoint.
You have installed the OpenShift command line interface (oc) as described in Installing the
OpenShift CLI (OpenShift Dedicated) or Installing the OpenShift CLI (Red Hat OpenShift
Service on AWS).
Procedure
1. In the OpenShift console, click the Quick Create ( 
 ) icon and then click the Import YAML
option.
2. Verify that your data science project is the selected project.
Red Hat OpenShift AI Cloud Service 1 Working with Llama Stack
20

3. In the Import YAML editor, paste the following manifest and click Create:
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
## DEPLOYING A RAG STACK IN A DATA SCIENCE PROJECT
21

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
Red Hat OpenShift AI Cloud Service 1 Working with Llama Stack
22

NOTE
Use the gRPC port (19530) for the MILVUS_ENDPOINT setting in Llama
Stack.
The HTTP port (9091) is reserved for health checks.
If you deploy Milvus in a different namespace, use the fully qualified service
name in your Llama Stack configuration. For example: http://milvus-service.
<namespace>.svc.cluster.local:19530
Verification
1. In the OpenShift web console, click Workloads → Deployments.
2. Verify that both etcd-deployment and milvus-standalone show a status of 1 of 1 pods
available.
3. Click Pods in the navigation panel and confirm that pods for both deployments are Running.
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
## DEPLOYING A RAG STACK IN A DATA SCIENCE PROJECT
23

4. Click the milvus-standalone pod name, then select the Logs tab.
5. Verify that Milvus reports a healthy startup with output similar to:
6. Click Networking → Services and confirm that the milvus-service and etcd-service resources
exist and are exposed on ports 19530 and 2379, respectively.
7. (Optional) Click Pods → milvus-standalone → Terminal and run the following health check:
A response of {"status": "healthy"} confirms that Milvus is running correctly.
### DEPLOYING A LLAMASTACKDISTRIBUTION INSTANCE
You can deploy Llama Stack with retrieval-augmented generation (RAG) by pairing it with a vLLM-
served Llama 3.2 model. This module provides two deployment examples of the 
LlamaStackDistribution custom resource (CR): one configured for Inline Milvus (single-node,
embedded) and one for Remote Milvus (external Milvus service). When you create the CR, specify rh-
dev in the spec.server.distribution.name field.
Prerequisites
You have installed OpenShift 4.17 or newer.
You have enabled GPU support in OpenShift AI. This includes installing the Node Feature
Discovery Operator and NVIDIA GPU Operator. For more information, see Installing the Node
Feature Discovery Operator and Enabling NVIDIA GPUs.
You have cluster administrator privileges for your OpenShift cluster.
You are logged in to Red Hat OpenShift AI.
You have activated the Llama Stack Operator in OpenShift AI.
You have deployed an inference model with vLLM (for example, llama-3.2-3b-instruct) and
selected Make deployed models available through an external route and Require token
authentication during model deployment.
You have the correct inference model identifier, for example, llama-3-2-3b.
You have the model endpoint URL ending with /v1, for example, https://llama-32-3b-instruct-
predictor:8443/v1.
You have the API token required to access the model endpoint.
You have installed the OpenShift CLI (oc) as described in the appropriate documentation for
your cluster:
Installing the OpenShift CLI for OpenShift Dedicated
Installing the OpenShift CLI for Red Hat OpenShift Service on AWS (classic architecture)
Milvus Standalone is ready to serve ...
Listening on 0.0.0.0:19530 (gRPC)
curl http://localhost:9091/healthz
Red Hat OpenShift AI Cloud Service 1 Working with Llama Stack
24

Procedure
1. Open a new terminal window and log in to your OpenShift cluster from the CLI:
In the upper-right corner of the OpenShift web console, click your user name and select Copy
login command. After you have logged in, click Display token. Copy the Log in with this token
command and paste it in the OpenShift CLI (oc).
$ oc login --token=<token> --server=<openshift_cluster_url>
2. Create a secret that contains the inference model environment variables:
3. Choose one of the following deployment examples:
3.7.1. Example A: LlamaStackDistribution with Inline Milvus
Use this example for development or small datasets where an embedded, single-node Milvus is
sufficient. No MILVUS_* connection variables are required.
1. In the OpenShift web console, select Administrator → Quick Create ( 
 ) → Import
YAML, and create a CR similar to the following:
export INFERENCE_MODEL="llama-3-2-3b"
export VLLM_URL="https://llama-32-3b-instruct-predictor:8443/v1"
export VLLM_TLS_VERIFY="false"   # Use "true" in production
export VLLM_API_TOKEN="<token identifier>"
oc create secret generic llama-stack-inference-model-secret \
  --from-literal=INFERENCE_MODEL="$INFERENCE_MODEL" \
  --from-literal=VLLM_URL="$VLLM_URL" \
  --from-literal=VLLM_TLS_VERIFY="$VLLM_TLS_VERIFY" \
  --from-literal=VLLM_API_TOKEN="$VLLM_API_TOKEN"
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
## DEPLOYING A RAG STACK IN A DATA SCIENCE PROJECT
25

NOTE
The rh-dev value is an internal image reference. When you create the 
LlamaStackDistribution custom resource, the OpenShift AI Operator
automatically resolves rh-dev to the container image in the appropriate registry.
This internal image reference allows the underlying image to update without
requiring changes to your custom resource.
3.7.2. Example B: LlamaStackDistribution with Remote Milvus
Use this example for production-grade or large datasets with an external Milvus service. This
configuration reads both MILVUS_ENDPOINT and MILVUS_TOKEN from a dedicated secret.
1. Create the Milvus connection secret:
IMPORTANT
Use the gRPC port 19530 for MILVUS_ENDPOINT. Ports such as 9091 are
typically used for health checks and are not valid for client traffic.
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
# Required: gRPC endpoint on port 19530
export MILVUS_ENDPOINT="tcp://milvus-service:19530"
export MILVUS_TOKEN="<milvus-root-or-user-token>"
export MILVUS_CONSISTENCY_LEVEL="Bounded"   # Optional; choose per your 
deployment
oc create secret generic milvus-secret \
  --from-literal=MILVUS_ENDPOINT="$MILVUS_ENDPOINT" \
  --from-literal=MILVUS_TOKEN="$MILVUS_TOKEN" \
  --from-literal=MILVUS_CONSISTENCY_LEVEL="$MILVUS_CONSISTENCY_LEVEL"
Red Hat OpenShift AI Cloud Service 1 Working with Llama Stack
26

2. In the OpenShift web console, select Administrator → Quick Create ( 
 ) → Import
YAML, and create a CR similar to the following:
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
## DEPLOYING A RAG STACK IN A DATA SCIENCE PROJECT
27

3. Click Create.
Verification
In the left-hand navigation, click Workloads → Pods and verify that the Llama Stack pod is
running in the correct namespace.
To verify that the Llama Stack server is running, click the pod name and select the Logs tab.
Look for output similar to the following:
Confirm that a Service resource for the Llama Stack backend is present in your namespace and
points to the running pod: Networking → Services.
TIP
If you switch from Inline Milvus to Remote Milvus, delete the existing pod to ensure the new environment
variables and backing store are picked up cleanly.
### INGESTING CONTENT INTO A LLAMA MODEL
You can quickly customize and prototype your retrievable content by ingesting raw text into your model
from inside a Jupyter notebook. This approach voids requiring a separate ingestion pipeline. By using the
LlamaStack SDK, you can embed and store text in your vector store in real-time, enabling immediate
RAG workflows.
Prerequisites
You have installed OpenShift 4.17 or newer.
You have deployed a Llama 3.2 model with a vLLM model server and you have integrated
LlamaStack.
You have created a project workbench within a data science project.
You have opened a Jupyter notebook and it is running in your workbench environment.
You have installed the llama_stack_client version 0.2.22 or later in your workbench
environment.
You have a vector database identifier, or you plan to create or register one in this procedure.
              name: milvus-secret
              key: MILVUS_CONSISTENCY_LEVEL
      name: llama-stack
      port: 8321
    distribution:
      name: rh-dev
INFO     2025-05-15 11:23:52,750 __main__:498 server: Listening on ['::', '0.0.0.0']:8321
INFO:     Started server process [1]
INFO:     Waiting for application startup.
INFO     2025-05-15 11:23:52,765 __main__:151 server: Starting up
INFO:     Application startup complete.
INFO:     Uvicorn running on http://['::', '0.0.0.0']:8321 (Press CTRL+C to quit)
Red Hat OpenShift AI Cloud Service 1 Working with Llama Stack
28

Procedure
1. In a new notebook cell, install the llama_stack_client package and its dependencies:
2. In a new notebook cell, import RAGDocument and LlamaStackClient:
3. In a new notebook cell, assign your deployment endpoint to the base_url parameter to create a
LlamaStackClient instance:
4. List the available models:
5. Verify that the list of registered models includes your Llama model and an embedding model.
Here is an example of a list of registered models:
6. Select the first LLM and the first embedding model:
7. (Optional) Register a vector database (choose one). Skip if you already have a vector DB ID.
Example 3.1. Option 1: Inline Milvus Lite (embedded)
%pip install llama_stack_client fire
from llama_stack_client import RAGDocument, LlamaStackClient
client = LlamaStackClient(base_url="<your deployment endpoint>")
# Fetch all registered models
models = client.models.list()
[Model(identifier='llama-32-3b-instruct', metadata={}, api_model_type='llm', provider_id='vllm-
inference', provider_resource_id='llama-32-3b-instruct', type='model', model_type='llm'),
 Model(identifier='ibm-granite/granite-embedding-125m-english', metadata=
{'embedding_dimension': 768.0}, api_model_type='embedding', provider_id='sentence-
transformers', provider_resource_id='ibm-granite/granite-embedding-125m-english', 
type='model', model_type='embedding')]
model_id = next(m.identifier for m in models if m.model_type == "llm")
embedding_model = next(m for m in models if m.model_type == "embedding")
embedding_model_id = embedding_model.identifier
embedding_dimension = int(embedding_model.metadata["embedding_dimension"])
vector_db_id = "my_inline_db"
vector_store = client.vector_stores.create(
    name=vector_db_id,
    embedding_model=embedding_model_id,
    embedding_dimension=embedding_dimension,
    provider_id="milvus",   # inline Milvus Lite
)
print(f"Registered inline Milvus Lite DB: {vector_db_id}")
## DEPLOYING A RAG STACK IN A DATA SCIENCE PROJECT
29

NOTE
Use inline Milvus Lite for development and small datasets. Persistence and
scale are limited compared to remote Milvus.
Example 3.2. Option 2: Remote Milvus (recommended for production)
Ensure your LlamaStackDistribution sets MILVUS_ENDPOINT (gRPC :19530) and 
MILVUS_TOKEN.
Aside from the provider_id, ingestion and query APIs are identical for inline and remote Milvus.
1. If you already have a vector database, set its identifier:
2. In a new notebook cell, define the raw text that you want to ingest into the vector store:
3. In a new notebook cell, create a RAGDocument object to contain the raw text:
4. In a new notebook cell, ingest the raw text:
vector_db_id = "my_remote_db"
vector_store = client.vector_stores.create(
    name=vector_db_id,
    embedding_model=embedding_model_id,
    embedding_dimension=embedding_dimension,
    provider_id="milvus-remote",  # remote Milvus provider (v2.25+)
)
print(f"Registered remote Milvus DB: {vector_db_id}")
# If a DB already exists, set it here instead of registering above
# Example:
# vector_db_id = "<your existing vector database ID>"
# Example raw text passage
raw_text = """
LlamaStack can embed raw text into a vector store for retrieval.
This example ingests a small passage for demonstration.
"""
document = RAGDocument(
    document_id="raw_text_001",
    content=raw_text,
    mime_type="text/plain",
    metadata={"source": "example_passage"},
)
client.tool_runtime.rag_tool.insert(
    documents=[document],
    vector_db_id=vector_db_id,
Red Hat OpenShift AI Cloud Service 1 Working with Llama Stack
30

5. In a new notebook cell, create a RAGDocument from an HTML source and ingest it into the
vector store:
6. In a new notebook cell, ingest the content into the vector store:
Verification
Review the output to confirm successful ingestion. A typical response after ingestion includes
the number of text chunks inserted and any warnings or errors.
The model list returned by client.models.list() includes your Llama 3.2 model and an
embedding model.
### QUERYING INGESTED CONTENT IN A LLAMA MODEL
You can use the LlamaStack SDK in your Jupyter notebook to query ingested content by running
retrieval-augmented generation (RAG) queries on raw text or HTML sources stored in your vector
database. When you query the ingested content, you can perform one-off lookups or start multi-turn
conversational flows without setting up a separate retrieval service.
Prerequisites
You have installed OpenShift 4.17 or newer.
You have enabled GPU support in OpenShift AI. This includes installing the Node Feature
Discovery operator and NVIDIA GPU Operators. For more information, see Installing the Node
Feature Discovery operator and Enabling NVIDIA GPUs.
If you are using GPU acceleration, you have at least one NVIDIA GPU available.
You have logged in to OpenShift web console.
You have activated the Llama Stack Operator in OpenShift AI.
    chunk_size_in_tokens=100,
)
print("Raw text ingested successfully")
source = "https://www.paulgraham.com/greatwork.html"
print("rag_tool> Ingesting document:", source)
document = RAGDocument(
    document_id="document_1",
    content=source,
    mime_type="text/html",
    metadata={},
)
client.tool_runtime.rag_tool.insert(
    documents=[document],
    vector_db_id=vector_db_id,
    chunk_size_in_tokens=50,
)
print("Raw text ingested successfully")
## DEPLOYING A RAG STACK IN A DATA SCIENCE PROJECT
31

You have deployed an inference model, for example, the llama-3.2-3b-instruct model.
You have configured a Llama Stack deployment by creating a LlamaStackDistribution instance
to enable RAG functionality.
You have created a project workbench within a data science project.
You have opened a Jupyter notebook and it is running in your workbench environment.
You have installed the llama_stack_client version 0.2.14 or later in your workbench
environment.
You have ingested content into your model.
NOTE
This procedure does not require any specific type of content. It only requires that you
have already ingested some text, HTML, or document data into your vector database,
and that this content is available for retrieval. If you have previously ingested content,
that content will be available to query. If you have not ingested any content yet, the
queries in this procedure will return empty results or errors.
Procedure
1. In a new notebook cell, install the llama_stack client package:
2. In a new notebook cell, import Agent, AgentEventLogger, and LlamaStackClient:
3. In a new notebook cell, assign your deployment endpoint to the base_url parameter to create a 
LlamaStackClient instance. For example:
4. In a new notebook cell, list the available models:
5. Verify that the list of registered models includes your Llama model and an embedding model.
Here is an example of a list of registered models:
6. Select the first LLM:
%pip install llama_stack_client
from llama_stack_client import Agent, AgentEventLogger, LlamaStackClient
client = LlamaStackClient(base_url="http://lsd-llama-milvus-service:8321/")
models = client.models.list()
[Model(identifier='llama-32-3b-instruct', metadata={}, api_model_type='llm', provider_id='vllm-
inference', provider_resource_id='llama-32-3b-instruct', type='model', model_type='llm'),
 Model(identifier='ibm-granite/granite-embedding-125m-english', metadata=
{'embedding_dimension': 768.0}, api_model_type='embedding', provider_id='sentence-
transformers', provider_resource_id='ibm-granite/granite-embedding-125m-english', 
type='model', model_type='embedding')]
Red Hat OpenShift AI Cloud Service 1 Working with Llama Stack
32

7. If you have not already created a vector store, select an embedding model for registration in the
next step:
8. If you do not already have a vector store ID, register a vector store of your choice:
Example 3.3. Option 1: Inline Milvus Lite (embedded)
NOTE
Use inline Milvus Lite for development and small datasets. Persistence and
scale are limited compared to remote Milvus.
Example 3.4. Option 2: Remote Milvus (recommended for production)
Ensure your LlamaStackDistribution sets MILVUS_ENDPOINT (gRPC :19530) and 
MILVUS_TOKEN.
Aside from the provide_id, querying APIs are identical for inline and remote Milvus.
1. If you already have a vector database, set its identifier:
model_id = next(m.identifier for m in models if m.model_type == "llm")
embedding = next(m for m in models if m.model_type == "embedding")
embedding_model_id = embedding.identifier
embedding_dimension = int(embedding.metadata["embedding_dimension"])
vector_db_id = "my_inline_db"
vector_store = client.vector_stores.create(
    name=vector_db_id,
    embedding_model=embedding_model_id,
    embedding_dimension=embedding_dimension,
    provider_id="milvus",   # inline Milvus Lite
)
print(f"Registered inline Milvus Lite DB: {vector_db_id}")
vector_db_id = "my_remote_db"
vector_store = client.vector_stores.create(
    name=vector_db_id,
    embedding_model=embedding_model_id,
    embedding_dimension=embedding_dimension,
    provider_id="milvus-remote",  # remote Milvus provider (v2.25+)
)
print(f"Registered remote Milvus DB: {vector_db_id}")
# If a DB already exists, set it here instead of registering above
# Example:
# vector_db_id = "<your existing vector database ID>"
## DEPLOYING A RAG STACK IN A DATA SCIENCE PROJECT
33

2. In a new notebook cell, query the ingested content using the low-level RAG tool:
3. In a new notebook cell, query the ingested content by using the high-level Agent API:
Verification
The notebook prints query results for both the low-level RAG tool and the high-level Agent API.
No errors appear in the output, confirming the model can retrieve and respond to ingested
content.
### PREPARING DOCUMENTS WITH DOCLING FOR LLAMA STACK
RETRIEVAL
You can transform your source documents with a Docling-enabled data science pipeline and ingest the
output into a Llama Stack vector store by using the Llama Stack SDK. This modular approach separates
document preparation from ingestion, yet still delivers an end-to-end, retrieval-augmented generation
(RAG) workflow.
# Example RAG query for one-off lookups
query = "What benefits do the ingested passages provide for retrieval?"
result = client.tool_runtime.rag_tool.query(
    vector_db_ids=[vector_db_id],
    content=query,
)
print("Low-level query result:", result)
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
Red Hat OpenShift AI Cloud Service 1 Working with Llama Stack
34

The pipeline registers a Milvus vector database and downloads the source PDFs, then splits them for
parallel processing and converts each batch to Markdown with Docling. It generates sentence-
transformer embeddings from the Markdown and stores them in the vector store, making the
documents instantly searchable in Llama Stack.
Prerequisites
You have installed OpenShift 4.17 or newer.
You have enabled GPU support in OpenShift AI. This includes installing the Node Feature
Discovery operator and NVIDIA GPU Operators. For more information, see Installing the Node
Feature Discovery operator and Enabling NVIDIA GPUs.
You have logged in to OpenShift web console.
You have a data science project and access to pipelines in the OpenShift AI dashboard.
You have created and configured a pipeline server within the data science project that contains
your workbench.
You have activated the Llama Stack Operator in OpenShift AI.
You have deployed an inference model, for example, the llama-3.2-3b-instruct model.
You have configured a Llama Stack deployment by creating a LlamaStackDistribution instance
to enable RAG functionality.
You have created a project workbench within a data science project.
You have opened a Jupyter notebook and it is running in your workbench environment.
You have installed the llama_stack_client version 0.2.14 or later in your workbench
environment.
You have installed local object storage buckets and created connections, as described in Adding
a connection to your data science project.
You have compiled to YAML a data science pipeline that includes a Docling transform, either
one of the RAG demo samples or your own custom pipeline.
Your data science project quota allows between 500 millicores (0.5 CPU) and 4 CPU cores for
the pipeline run.
Your data science project quota allows from 2 GiB up to 6 GiB of RAM for the pipeline run.
If you are using GPU acceleration, you have at least one NVIDIA GPU available.
Procedure
1. In a new notebook cell, install the llama_stack client package:
2. In a new notebook cell, import Agent, AgentEventLogger, and LlamaStackClient:
%pip install llama_stack_client
from llama_stack_client import Agent, AgentEventLogger, LlamaStackClient
## DEPLOYING A RAG STACK IN A DATA SCIENCE PROJECT
35

3. In a new notebook cell, assign your deployment endpoint to the base_url parameter to create a
LlamaStackClient instance:
4. List the available models:
5. Select the first LLM and the first embedding model:
6. In a new notebook cell, register a vector database (choose one option):
Example 3.5. Option 1: Inline Milvus Lite (embedded)
NOTE
Inline Milvus Lite is best for development. Data durability and scale are limited
compared to remote Milvus.
Example 3.6. Option 2: Remote Milvus (recommended for production)
Ensure your LlamaStackDistribution includes MILVUS_ENDPOINT and MILVUS_TOKEN.
Aside from the provider_id, ingestion and query APIs are identical between inline and remote
Milvus.
client = LlamaStackClient(base_url="<your deployment endpoint>")
models = client.models.list()
model_id = next(m.identifier for m in models if m.model_type == "llm")
embedding_model = next(m for m in models if m.model_type == "embedding")
embedding_model_id = embedding_model.identifier
embedding_dimension = embedding_model.metadata["embedding_dimension"]
vector_db_id = "my_inline_db"
vector_store = client.vector_stores.create(
    name=vector_db_id,
    embedding_model=embedding_model_id,
    embedding_dimension=embedding_dimension,
    provider_id="milvus",   # inline Milvus Lite
)
print(f"Registered inline Milvus Lite DB: {vector_db_id}")
vector_db_id = "my_remote_db"
vector_store = client.vector_stores.create(
    name=vector_db_id,
    embedding_model=embedding_model_id,
    embedding_dimension=embedding_dimension,
    provider_id="milvus-remote",  # remote Milvus provider (v2.25+)
)
print(f"Registered remote Milvus DB: {vector_db_id}")
Red Hat OpenShift AI Cloud Service 1 Working with Llama Stack
36

+
IMPORTANT
If you are using the sample Docling pipeline from the RAG demo repository, the pipeline
registers the database automatically and you can skip this step. However, if you are using
your own pipeline, you must register the database yourself.
1. In the OpenShift web console, import the YAML file containing your docling pipeline into your
data science project, as described in Importing a data science pipeline .
2. Create a pipeline run to execute your Docling pipeline, as described in Executing a pipeline run.
The pipeline run inserts your PDF documents into the vector database. If you run the Docling
pipeline from the RAG demo samples repository, you can optionally customize the following
parameters before starting the pipeline run:
base_url: The base URL to fetch PDF files from.
pdf_filenames: A comma-separated list of PDF filenames to download and convert.
num_workers: The number of parallel workers.
vector_db_id: The Milvus vector database ID.
service_url: The Milvus service URL.
embed_model_id: The embedding model to use.
max_tokens: The maximum tokens for each chunk.
use_gpu: Enable or disable GPU acceleration.
Verification
1. In your Jupyter notebook, query the LLM with a question that relates to the ingested content.
For example:
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
## DEPLOYING A RAG STACK IN A DATA SCIENCE PROJECT
37

2. Query chunks from the vector database:
### ABOUT LLAMA STACK SEARCH TYPES
Llama Stack supports keyword, vector, and hybrid search modes for retrieving context in retrieval-
augmented generation (RAG) workloads. Each mode offers different tradeoffs in precision, recall,
semantic depth, and computational cost.
3.11.1. Supported search modes
3.11.1.1. Keyword search
Keyword search applies lexical matching techniques, such as TF-IDF or BM25, to locate documents that
contain exact or near-exact query terms. This approach is effective when precise term-matching is
critical and remains widely used in information-retrieval systems. For more information, see The
Probabilistic Relevance Framework: BM25 and Beyond.
3.11.1.2. Vector search
Vector search encodes documents and queries as dense numerical vectors, known as embeddings, and
measures similarity with metrics such as cosine similarity or inner product. This approach captures
contextual meaning and supports semantic matching beyond exact word overlap. For more information,
see Billion-scale similarity search with GPUs.
3.11.1.3. Hybrid search
Hybrid search blends keyword and vector techniques, typically by combining individual scores with a
weighted sum or methods, such as Reciprocal Rank Fusion (RRF). This approach returns results that
balance exact matches with semantic relevance. For more information, see Sparse, Dense, and Hybrid
Retrieval for Answer Ranking.
3.11.2. Retrieval database support
Milvus is the supported retrieval database for Llama Stack. It currently provides vector search. However,
keyword and hybrid search capabilities are not currently supported.
session_id = rag_agent.create_session(session_name=f"s{uuid.uuid4().hex}")
response = rag_agent.create_turn(
    messages=[{"role": "user", "content": prompt}],
    session_id=session_id,
    stream=True,
)
for log in AgentEventLogger().log(response):
    log.print()
query_result = client.vector_io.query(
    vector_db_id=vector_db_id,
    query="what do you know about?",
)
print(query_result)
Red Hat OpenShift AI Cloud Service 1 Working with Llama Stack
38
## BENCHMARKING EMBEDDING MODELS WITH
BEIR DATASETS AND LLAMA STACK
This procedure explains how to set up, run, and verify embedding-model benchmarks by using the Llama
Stack framework. Embedding models are neural networks that convert text or other data into dense
numerical vectors, called embeddings, which capture semantic meaning. In retrieval-augmented
generation (RAG) systems, embeddings enable semantic search so that the system retrieves the
documents most relevant to a query.
Selecting an embedding model depends on several factors, such as the content type, accuracy
requirements, performance needs, and model license. The beir_benchmarks.py script compares the
retrieval accuracy of embedding models by using standardized information-retrieval benchmarks from
the BEIR framework. The script is included in the RAG repository, which provides demonstrations,
benchmarking scripts, and deployment guides for the RAG Stack on OpenShift.
The examples use the sentence-transformers inference provider, which you can replace with another
provider if required.
Prerequisites
You have cloned the https://github.com/opendatahub-io/rag repository.
You have changed into the /rag/benchmarks/beir-benchmarks directory.
You have initialized and activated a virtual environment.
You have defined and installed the relevant script package dependencies to a requirements.txt
file.
You have built the Llama Stack starter distribution to install all dependencies.
You have verified that your vector database is accessible and configured in the run.yaml file,
and that any required embedding models were preloaded or registered with Llama Stack.
NOTE
The default supported embedding models are granite-embedding-30m and granite-
embedding-125m, served by the sentence-transformers framework. Ollama is not
required for basic benchmarks but can be used to serve custom embedding models.
To register an additional embedding model, such as all-MiniLM-L6-v2, perform the
following steps:
1. Start the Llama Stack server:
MILVUS_URL=milvus uv run llama stack run run.yaml
2. Register the model by using the Llama Stack client. For example:
llama-stack-client models register all-MiniLM-L6-v2 \
  --provider-id sentence-transformers \
  --provider-model-id all-minilm:latest \
  --metadata {"embedding_dimension": 384} \
  --model-type embedding
## BENCHMARKING EMBEDDING MODELS WITH BEIR DATASETS AND LLAMA STACK
39

You have shut down the Llama Stack server before running the benchmark script.
Procedure
1. Run the beir_benchmarks.py benchmarking script:
Enter the following command to use the configuration from run.yaml and the default
dataset (scifact):
MILVUS_URL=milvus uv run python beir_benchmarks.py
Alternatively, enter the following command to connect to a custom Llama Stack server:
LLAMA_STACK_URL="http://localhost:8321" MILVUS_URL=milvus uv run python 
beir_benchmarks.py
2. Use environment variables and command-line options to modify the benchmark run. For
example, set the environment variable ENABLE_MILVUS=milvus before executing the script.
Enter the following command to benchmark with a specific LLM by using default settings:
ENABLE_MILVUS=milvus uv run python beir_benchmarks.py
Enter the following command to use a larger batch size for document ingestion:
ENABLE_MILVUS=milvus uv run python beir_benchmarks.py --batch-size 300
Enter the following command to benchmark multiple datasets (for example, scifact and 
scidocs):
ENABLE_MILVUS=milvus uv run python beir_benchmarks.py \
  --dataset-names scifact scidocs
Enter the following command to compare embedding models (for example, granite-
embedding-30m and all-MiniLM-L6-v2):
ENABLE_MILVUS=milvus uv run python beir_benchmarks.py \
  --embedding-models granite-embedding-30m all-MiniLM-L6-v2
Enter the following command to use a custom BEIR-compatible dataset:
ENABLE_MILVUS=milvus uv run python beir_benchmarks.py \
  --dataset-names my-dataset \
  --custom-datasets-urls https://example.com/my-beir-dataset.zip
Enter the following command to change the vector database provider. The following
example changes the vector database provider to remote Milvus:
ENABLE_MILVUS=milvus uv run python beir_benchmarks.py \
  --vector-db-provider-id remote-milvus
Command-line options
Red Hat OpenShift AI Cloud Service 1 Working with Llama Stack
40

--vector-db-provider-id
Description: Specifies the vector database provider to use.
Type: String.
Default: milvus.
Example:
--vector-db-provider-id remote-milvus
--dataset-names
Description: Specifies which BEIR datasets to use for benchmarking. Use this option
together with --custom-datasets-urls when testing custom datasets.
Type: List of strings.
Default: ["scifact"].
Example:
--dataset-names scifact scidocs nq
--embedding-models
Description: Specifies the embedding models to compare. Models must be defined in the 
run.yaml file.
Type: List of strings.
Default: ["granite-embedding-30m", "granite-embedding-125m"].
Example:
--embedding-models all-MiniLM-L6-v2 granite-embedding-125m
--batch-size
Description: Controls how many documents are processed per batch during ingestion.
Larger batch sizes improve speed but use more memory.
Type: Integer.
Default: 150.
Example:
--batch-size 50
--batch-size 300
--custom-datasets-urls
D
i ti
S
ifi
URL f
t
BEIR
tibl d t
t
U
thi
ti
ith
## BENCHMARKING EMBEDDING MODELS WITH BEIR DATASETS AND LLAMA STACK
41

Description: Specifies URLs for custom BEIR-compatible datasets. Use this option with --
dataset-names.
Type: List of strings.
Default: [].
Example:
--dataset-names my-custom-dataset \
  --custom-datasets-urls https://example.com/my-dataset.zip
NOTE
Custom BEIR datasets must follow the required file structure and format:
dataset-name.zip/
├── qrels/
│   └── test.tsv      # Maps query IDs to document IDs with relevance scores
├── corpus.jsonl      # Document collection with document IDs, titles, and text
└── queries.jsonl     # Test queries with query IDs and question text
Verification
To verify that the benchmark completed successfully and to review the results, perform the following
steps:
1. Locate the results directory. All output files are saved to the following path:
<path-to>/rag/benchmarks/embedding-models-with-beir/results
2. Examine the output. Compare your results with the sample output structure. The report
includes performance metrics such as map@cut_k and ndcg@cut_k for each dataset and
embedding model pair. The script also calculates a statistical significance test (p-value).
Example output (for scifact and map_cut_10):
scifact map_cut_10
 granite-embedding-125m : 0.6879
 granite-embedding-30m  : 0.6578
 p_value                : 0.0150
 p_value < 0.05 indicates a statistically significant difference.
 The granite-embedding-125m model performs better for this dataset and metric.
3. Interpret the results. A p-value below 0.05 indicates that the performance difference between
models is statistically significant. Use these results to identify which embedding model performs
best for your dataset.
Red Hat OpenShift AI Cloud Service 1 Working with Llama Stack
42

---

## Openshift Ai Pipelines

Red Hat OpenShift AI Cloud Service 1
Working with data science pipelines
Work with data science pipelines from Red Hat OpenShift AI Cloud Service
Last Updated: 2025-10-28

Red Hat OpenShift AI Cloud Service 1 Working with data science pipelines
Work with data science pipelines from Red Hat OpenShift AI Cloud Service

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
Enhance your data science projects on OpenShift AI by building portable machine learning (ML)
workflows with data science pipelines.

. . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 
. . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 
. . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 
. . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 
Table of Contents
PREFACE
## MANAGING DATA SCIENCE PIPELINES
### CONFIGURING A PIPELINE SERVER
1.1.1. Configuring a pipeline server with an external Amazon RDS database
### DEFINING A PIPELINE
1.2.1. Compiling the pipeline YAML with the Kubeflow Pipelines SDK
1.2.2. Compiling Kubernetes-native manifests with the Kubeflow Pipelines SDK
1.2.3. Defining a pipeline by using the Kubernetes API
1.2.4. Migrating pipelines from database to Kubernetes API storage
### IMPORTING A DATA SCIENCE PIPELINE
### DELETING A DATA SCIENCE PIPELINE
### DELETING A PIPELINE SERVER
### VIEWING THE DETAILS OF A PIPELINE SERVER
### VIEWING EXISTING PIPELINES
### OVERVIEW OF PIPELINE VERSIONS
### UPLOADING A PIPELINE VERSION
### DELETING A PIPELINE VERSION
### VIEWING THE DETAILS OF A PIPELINE VERSION
### DOWNLOADING A DATA SCIENCE PIPELINE VERSION
### OVERVIEW OF DATA SCIENCE PIPELINES CACHING
1.13.1. Caching criteria
1.13.2. Viewing cached steps in the OpenShift AI user interface
1.13.3. Controlling caching in data science pipelines
1.13.3.1. Disabling caching for individual tasks
1.13.3.2. Disabling caching for a pipeline at submit time
1.13.3.3. Disabling caching for a pipeline at compile time
1.13.3.4. Disabling caching for all pipelines (pipeline server)
## MANAGING PIPELINE EXPERIMENTS
### OVERVIEW OF PIPELINE EXPERIMENTS
### CREATING A PIPELINE EXPERIMENT
### ARCHIVING A PIPELINE EXPERIMENT
### DELETING AN ARCHIVED PIPELINE EXPERIMENT
### RESTORING AN ARCHIVED PIPELINE EXPERIMENT
### VIEWING PIPELINE TASK EXECUTIONS
### VIEWING PIPELINE ARTIFACTS
### COMPARING RUNS IN AN EXPERIMENT
### COMPARING RUNS IN DIFFERENT EXPERIMENTS
## MANAGING PIPELINE RUNS
### OVERVIEW OF PIPELINE RUNS
### STORING DATA WITH DATA SCIENCE PIPELINES
### VIEWING ACTIVE PIPELINE RUNS
### EXECUTING A PIPELINE RUN
### STOPPING AN ACTIVE PIPELINE RUN
### DUPLICATING AN ACTIVE PIPELINE RUN
### VIEWING SCHEDULED PIPELINE RUNS
### SCHEDULING A PIPELINE RUN USING A CRON JOB
### SCHEDULING A PIPELINE RUN
### DUPLICATING A SCHEDULED PIPELINE RUN
### DELETING A SCHEDULED PIPELINE RUN
4
6
6
8
9
10
10
12
15
17
18
19
20
20
21
21
22
23
24
24
24
25
25
25
26
26
26
28
28
28
29
29
30
30
31
32
33
35
35
36
36
37
38
39
40
40
41
43
44
Table of Contents
1

. . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 
. . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 
. . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 
. . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 
### VIEWING THE DETAILS OF A PIPELINE RUN
### VIEWING ARCHIVED PIPELINE RUNS
### ARCHIVING A PIPELINE RUN
### RESTORING AN ARCHIVED PIPELINE RUN
### DELETING AN ARCHIVED PIPELINE RUN
### DUPLICATING AN ARCHIVED PIPELINE RUN
## WORKING WITH PIPELINE LOGS
### ABOUT PIPELINE LOGS
### VIEWING PIPELINE STEP LOGS
### DOWNLOADING PIPELINE STEP LOGS
## WORKING WITH PIPELINES IN JUPYTERLAB
### OVERVIEW OF PIPELINES IN JUPYTERLAB
### ACCESSING THE PIPELINE EDITOR
### CREATING A RUNTIME CONFIGURATION
### UPDATING A RUNTIME CONFIGURATION
### DISABLING NODE CACHING IN ELYRA
### DELETING A RUNTIME CONFIGURATION
### DUPLICATING A RUNTIME CONFIGURATION
### RUNNING A PIPELINE IN JUPYTERLAB
### EXPORTING A PIPELINE IN JUPYTERLAB
## TROUBLESHOOTING DSPA COMPONENT ERRORS
### COMMON ERRORS ACROSS DSP COMPONENTS
## ADDITIONAL RESOURCES
45
46
46
47
48
48
50
50
50
51
53
53
54
55
57
60
61
62
63
64
66
68
70
Red Hat OpenShift AI Cloud Service 1 Working with data science pipelines
2

Table of Contents
3

PREFACE
As a data scientist, you can enhance your data science projects on OpenShift AI by building portable
machine learning (ML) workflows with data science pipelines, using Docker containers. This enables you
to standardize and automate machine learning workflows to enable you to develop and deploy your data
science models.
For example, the steps in a machine learning workflow might include items such as data extraction, data
processing, feature extraction, model training, model validation, and model serving. Automating these
activities enables your organization to develop a continuous process of retraining and updating a model
based on newly received data. This can help address challenges related to building an integrated
machine learning deployment and continuously operating it in production.
You can also use the Elyra JupyterLab extension to create and run data science pipelines within
JupyterLab. For more information, see Working with pipelines in JupyterLab .
To use a data science pipeline in OpenShift AI, you need the following components:
Pipeline server: A server that is attached to your data science project and hosts your data
science pipeline.
Pipeline: A pipeline defines the configuration of your machine learning workflow and the
relationship between each component in the workflow.
Pipeline code: A definition of your pipeline in a YAML file.
Pipeline graph: A graphical illustration of the steps executed in a pipeline run and the
relationship between them.
Pipeline experiment: A workspace where you can try different configurations of your pipelines.
You can use experiments to organize your runs into logical groups.
Archived pipeline experiment: An archived pipeline experiment.
Pipeline artifact: An output artifact produced by a pipeline component.
Pipeline execution: The execution of a task in a pipeline.
Pipeline run: An execution of your pipeline.
Active run: A pipeline run that is executing, or stopped.
Scheduled run: A pipeline run that is scheduled to execute at least once.
Archived run: An archived pipeline run.
This feature is based on Kubeflow Pipelines 2.0. Use the latest Kubeflow Pipelines 2.0 SDK to build your
data science pipeline in Python code. After you have built your pipeline, use the SDK to compile it into an
Intermediate Representation (IR) YAML file. The OpenShift AI user interface enables you to track and
manage pipelines, experiments, and pipeline runs. To view a record of previously executed, scheduled,
and archived runs, you can go to Data science pipelines → Runs, or you can select an experiment from
the Experiments → Experiments and runs to access all of its pipeline runs. You can manage
incremental changes to pipelines in OpenShift AI by using versioning. This allows you to develop and
deploy pipelines iteratively, preserving a record of your changes.
Red Hat OpenShift AI Cloud Service 1 Working with data science pipelines
4

You can store your pipeline artifacts in an S3-compatible object storage bucket so that you do not
consume local storage. To do this, you must first configure write access to your S3 bucket on your
storage account.
PREFACE
5
## MANAGING DATA SCIENCE PIPELINES
### CONFIGURING A PIPELINE SERVER
Before you can successfully create a pipeline in OpenShift AI, you must configure a pipeline server. This
task includes configuring where your pipeline artifacts and data are stored.
NOTE
You are not required to specify any storage directories when configuring a connection for
your pipeline server. When you import a pipeline, the /pipelines folder is created in the 
root folder of the bucket, containing a YAML file for the pipeline. If you upload a new
version of the same pipeline, a new YAML file with a different ID is added to the 
/pipelines folder.
When you run a pipeline, the artifacts are stored in the /pipeline-name folder in the root
folder of the bucket.
Prerequisites
You have logged in to Red Hat OpenShift AI.
You have created a data science project that you can add a pipeline server to.
You have an existing S3-compatible object storage bucket and you have configured write
access to your S3 bucket on your storage account.
If you are configuring a pipeline server for production pipeline workloads, you have an existing
external MySQL or MariaDB database.
If you are configuring a pipeline server with an external MySQL database, your database must
use at least MySQL version 5.x. However, Red Hat recommends that you use MySQL version
8.x.
NOTE
The mysql_native_password authentication plugin is required for the ML
Metadata component to successfully connect to your database. 
mysql_native_password is disabled by default in MySQL 8.4 and later. If your
database uses MySQL 8.4 or later, you must update your MySQL deployment to
enable the mysql_native_password plugin.
For more information about enabling the mysql_native_password plugin, see
Native Pluggable Authentication in the MySQL documentation.
If you are configuring a pipeline server with a MariaDB database, your database must use
MariaDB version 10.3 or later. However, Red Hat recommends that you use at least MariaDB
version 10.5.
Procedure
1. From the OpenShift AI dashboard, click Data science projects.
The Data science projects page opens.
Red Hat OpenShift AI Cloud Service 1 Working with data science pipelines
6

2. Click the name of the project that you want to configure a pipeline server for.
A project details page opens.
3. Click the Pipelines tab.
4. Click Configure pipeline server.
The Configure pipeline server dialog opens.
5. In the Object storage connection section, provide values for the mandatory fields:
a. In the Access key field, enter the access key ID for the S3-compatible object storage
provider.
b. In the Secret key field, enter the secret access key for the S3-compatible object storage
account that you specified.
c. In the Endpoint field, enter the endpoint of your S3-compatible object storage bucket.
d. In the Region field, enter the default region of your S3-compatible object storage account.
e. In the Bucket field, enter the name of your S3-compatible object storage bucket.
IMPORTANT
If you specify incorrect connection settings, you cannot update these
settings on the same pipeline server. Therefore, you must delete the pipeline
server and configure another one.
If you want to use an existing artifact that was not generated by a task in a
pipeline, you can use the kfp.dsl.importer component to import the artifact
from its URI. You can only import these artifacts to the S3-compatible object
storage bucket that you define in the Bucket field in your pipeline server
configuration. For more information about the kfp.dsl.importer component,
see Special Case: Importer Components .
6. Click Advanced settings to display the Database, Pipeline definition storage, and Pipeline
caching sections.
7. In the Database section, choose one of the following options to specify where to store your
pipeline metadata and run information:
Select Default database on the cluster to deploy a MariaDB database in your project.
IMPORTANT
The Default database on the cluster option is intended for development
and testing purposes only. For production pipeline workloads, select the
External MySQL database option to use an external MySQL or MariaDB
database.
Select External MySQL database to add a new connection to an external MySQL or
MariaDB database that your pipeline server can access.
i. In the Host field, enter the database hostname.
ii. In the Port field, enter the database port.
## MANAGING DATA SCIENCE PIPELINES
7

iii. In the Username field, enter the default user name that is connected to the database.
iv. In the Password field, enter the password for the default user account.
v. In the Database field, enter the database name.
8. Optional: By default, pipeline definitions are stored as Kubernetes resources, enabling version
control, GitOps workflows, and integration with OpenShift GitOps or similar tools. To store
pipeline definitions in the internal database instead, clear the Store pipeline definitions in
Kubernetes checkbox in the Pipeline definition storage section.
9. Optional: By default, caching is configurable at both the pipeline and task levels. To disable
caching for all pipelines and tasks in the pipeline server and override any pipeline-level and task-
level caching settings, clear the Allow caching to be configured per pipeline and task
checkbox in the Pipeline caching section.
10. Click Configure pipeline server.
Verification
On the Pipelines tab for the project:
The Import pipeline button is available.
When you click the action menu (⋮) and then click Manage pipeline server configuration, the
pipeline server details are displayed.
Additional resources
Overview of data science pipelines caching
1.1.1. Configuring a pipeline server with an external Amazon RDS database
To configure a pipeline server with an external Amazon Relational Database Service (RDS) database, you
must configure OpenShift AI to trust the certificates issued by its certificate authorities (CA).
IMPORTANT
If you are configuring a pipeline server for production pipeline workloads, Red Hat
recommends that you use an external MySQL or MariaDB database.
Prerequisites
You have cluster administrator privileges for your OpenShift cluster.
You have logged in to Red Hat OpenShift AI.
You have created a data science project that you can add a pipeline server to.
You have an existing S3-compatible object storage bucket, and you have configured your
storage account with write access to your S3 bucket.
Procedure
1. Before configuring your pipeline server, from Amazon RDS: Certificate bundles by AWS Region ,
download the PEM certificate bundle for the region that the database was created in.
Red Hat OpenShift AI Cloud Service 1 Working with data science pipelines
8

For example, if the database was created in the us-east-1 region, download us-east-1-
bundle.pem.
2. In a terminal window, log in to the OpenShift cluster where OpenShift AI is deployed.
oc login api.<cluster_name>.<cluster_domain>:6443 --web
3. Run the following command to fetch the current OpenShift AI trusted CA configuration and
store it in a new file:
oc get dscinitializations.dscinitialization.opendatahub.io default-dsci -o json | jq 
'.spec.trustedCABundle.customCABundle' > /tmp/my-custom-ca-bundles.crt
4. Run the following command to append the PEM certificate bundle that you downloaded to the
new custom CA configuration file:
cat us-east-1-bundle.pem >> /tmp/my-custom-ca-bundles.crt
5. Run the following command to update the OpenShift AI trusted CA configuration to trust
certificates issued by the CAs included in the new custom CA configuration file:
oc patch dscinitialization default-dsci --type='json' -
p='[{"op":"replace","path":"/spec/trustedCABundle/customCABundle","value":"'"$(awk '{printf 
"%s\\n", $0}' /tmp/my-custom-ca-bundles.crt)"'"}]'
6. Configure a pipeline server, as described in Configuring a pipeline server.
Verification
The pipeline server starts successfully.
You can import and run data science pipelines.
### DEFINING A PIPELINE
The Kubeflow Pipelines SDK enables you to define end-to-end machine learning and data pipelines. Use
the latest Kubeflow Pipelines 2.0 SDK to build your data science pipeline in Python code. After you have
built your pipeline, use the SDK to compile it into an Intermediate Representation (IR) YAML file. For
more information about compiling pipelines, see Compiling the pipeline YAML with the Kubeflow
Pipelines SDK and Compiling Kubernetes-native manifests with the Kubeflow Pipelines SDK . Compiling to
Kubernetes-native manifests is optional and applies only when your pipeline server is configured to use
Kubernetes API storage. After defining the pipeline, you can import the YAML file to the OpenShift AI
dashboard to enable you to configure its execution settings.
IMPORTANT
If you are using OpenShift AI on a cluster running in FIPS mode, any custom container
images for data science pipelines must be based on UBI 9 or RHEL 9. This ensures
compatibility with FIPS-approved pipeline components and prevents errors related to
mismatched OpenSSL or GNU C Library (glibc) versions.
## MANAGING DATA SCIENCE PIPELINES
9

You can also use the Elyra JupyterLab extension to create and run data science pipelines within
JupyterLab. For more information about creating pipelines in JupyterLab, see Working with pipelines in
JupyterLab. For more information about the Elyra JupyterLab extension, see Elyra Documentation.
Additional resources
Kubeflow Pipelines 2.0 Documentation
Elyra Documentation
1.2.1. Compiling the pipeline YAML with the Kubeflow Pipelines SDK
Before you can define your pipeline in the cluster, you must convert your Python-defined pipeline into
YAML format. You can use the Kubeflow Pipelines (KFP) Software Development Kit (SDK) to compile
your pipeline code into a deployable YAML file for declarative GitOps deployment.
Prerequisites
You have installed Python 3.11 or later in your local environment.
You have installed the Kubeflow Pipelines SDK package (kfp) version 2.14.3 or later.
You have a valid Python pipeline definition file.
Procedure
Compile your pipeline by using the KFP SDK to generate the pipeline YAML file.
In the following example, replace <pipeline_file>.py with the name of your Python pipeline file and
specify an output file for the compiled YAML:
$ kfp dsl compile \
    --py <pipeline_file>.py \
    --output <compiled_pipeline_file>.yaml
NOTE
The generated <compiled_pipeline_file>.yaml file contains the compiled pipeline
specification in YAML format. You can use this content as the value of the pipelineSpec
field when you create a PipelineVersion custom resource (CR). You can also store the
file in Git for declarative or GitOps-based deployment.
Verification
Verify that the generated file includes a pipelineSpec key followed by the compiled pipeline definition:
$ head -n 10 <compiled_pipeline_file>.yaml
Additional resources
Compiling a pipeline with the Kubeflow Pipelines SDK
1.2.2. Compiling Kubernetes-native manifests with the Kubeflow Pipelines SDK
Red Hat OpenShift AI Cloud Service 1 Working with data science pipelines
10

If your pipeline server uses the Kubernetes native API mode, you can compile your pipeline directly to
Kubernetes manifests. The output includes Pipeline and PipelineVersion custom resources with 
spec.pipelineSpec and, when you use Kubernetes resource configuration, an optional 
spec.platformSpec.
Prerequisites
You have installed Python 3.11 or later in your local environment.
You have installed the Kubeflow Pipelines SDK package (kfp) version 2.14.3 or later.
You have a valid Python pipeline definition file.
Procedure
1. Save the following code as a new file named compile.py in your working directory.
The example uses the KubernetesManifestOptions class from the 
kfp.compiler.compiler_utils module to define pipeline metadata such as the name, version,
and namespace.
Example compile script
2. Run the script to compile your pipeline and generate the Kubernetes manifests:
$ python compile.py
Verification
Verify that the compiled output includes the expected resources:
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
apiVersion: pipelines.kubeflow.org/v2beta1
kind: Pipeline
---
apiVersion: pipelines.kubeflow.org/v2beta1
kind: PipelineVersion
## MANAGING DATA SCIENCE PIPELINES
11

Additional resources
Compiling for Kubernetes native API mode
1.2.3. Defining a pipeline by using the Kubernetes API
You can define data science pipelines and pipeline versions by using the Kubernetes API, which stores
them as custom resources in the cluster instead of the internal database. This approach makes it easier
to use OpenShift GitOps (Argo CD) or similar tools to manage pipelines and pipeline versions, while still
allowing you to manage them through the OpenShift AI dashboard, API, and the Kubeflow Pipelines
(KFP) Software Development Kit (SDK). You can generate the required manifests by using the Kubeflow
Pipelines SDK; see Compiling the pipeline YAML with the Kubeflow Pipelines SDK  or Compiling
Kubernetes-native manifests with the Kubeflow Pipelines SDK.
NOTE
If your pipeline server is already configured to use Kubernetes API storage, you can still
use the OpenShift AI dashboard and REST API to view pipeline details, run pipelines, and
create schedules. In this mode, the Kubernetes API acts as the storage backend, so your
existing tools continue to work as expected.
Prerequisites
You have OpenShift AI administrator privileges or you are the project owner.
You have a data science project with a running pipeline server.
You have installed the OpenShift CLI (oc) as described in the appropriate documentation for
your cluster:
Installing the OpenShift CLI for OpenShift Dedicated
Installing the OpenShift CLI for Red Hat OpenShift Service on AWS (classic architecture)
If you plan to create a PipelineVersion custom resource, you have either:
Compiled your Python pipeline to IR YAML by using the KFP SDK. See Compiling the
pipeline YAML with the Kubeflow Pipelines SDK.
Compiled Kubernetes-native manifests by using the KFP SDK. See Compiling Kubernetes-
native manifests with the Kubeflow Pipelines SDK.
Procedure
1. In a terminal window, log in to your OpenShift cluster by using the OpenShift CLI (oc):
$ oc login -u <user_name>
When prompted, enter the OpenShift server URL, connection type, and your password.
2
T
fi
th
i
li
t
K b
t
API t
i
t
d
f th d f
lt d t b
spec:
  pipelineSpec: ...
  platformSpec: ...   # present when Kubernetes resource configuration is used
Red Hat OpenShift AI Cloud Service 1 Working with data science pipelines
12

2. To configure the pipeline server to use Kubernetes API storage instead of the default database
option, set the spec.apiServer.pipelineStore field to kubernetes in your project’s 
DataSciencePipelinesApplication (DSPA) custom resource.
In the following command, replace <dspa_name> with the name of your DSPA custom resource,
and replace <namespace> with the name of your project:
$ oc patch dspa <dspa_name> -n <namespace> \
  --type=merge \
  -p {"spec": {"apiServer": {"pipelineStore": "kubernetes"}}}
WARNING
When you switch the pipeline server from database storage to Kubernetes
API storage, existing pipelines that were stored in the internal database are
no longer visible in the OpenShift AI dashboard or REST API. To view or
manage those pipelines again, change the spec.apiServer.pipelineStore
field back to database.
3. Define a Pipeline custom resource in a YAML file with the following contents:
Example pipeline definition
name: The immutable Kubernetes resource name of your pipeline.
namespace: The name of your project.
displayName: The user-friendly display name of your pipeline, which is shown in the
dashboard and REST API.
4. Apply the pipeline definition to create the Pipeline custom resource in your cluster.
In the following command, replace <pipeline_yaml_file> with the name of your YAML file:
Example command
$ oc apply -f <pipeline_yaml_file>.yaml
5. Alternatively, if you compiled Kubernetes-native manifests with the KFP SDK, you can apply the
generated file directly without manually creating separate YAML files:
$ oc apply -f <output_file>.yaml

apiVersion: pipelines.kubeflow.org/v2beta1
kind: Pipeline
metadata:
  name: <name>
  namespace: <namespace>
spec:
  displayName: <displayName>
## MANAGING DATA SCIENCE PIPELINES
13

The generated file includes both Pipeline and PipelineVersion resources. You can skip the
following manual definition steps and proceed to the verification step.
6. Define a PipelineVersion custom resource in a YAML file with the following contents:
Example pipeline version definition
name: The name of your pipeline version.
namespace: The name of your project.
pipelineName: The immutable Kubernetes resource name of your pipeline. This value must
match the metadata.name value in the Pipeline custom resource.
displayName: The user-friendly display name of your pipeline version, which is shown in the
dashboard and REST API.
pipelineSpec: The YAML content that you generated by using the Kubeflow Pipelines
(KFP) SDK.
7. Apply the pipeline version definition to create the PipelineVersion custom resource in your
cluster.
In the following command, replace <pipeline_version_yaml_file> with the name of your YAML file:
Example command
$ oc apply -f <pipeline_version_yaml_file>.yaml
After creating the pipeline version, the system automatically applies the following labels to the
pipeline version for easier filtering:
Example automatic labels
Verification
1. Check that the Pipeline custom resource was successfully created:
$ oc get pipeline <pipeline_name> -n <namespace>
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
pipelines.kubeflow.org/pipeline-id: <metadata.uid of the pipeline>
pipelines.kubeflow.org/pipeline: <pipeline name>
Red Hat OpenShift AI Cloud Service 1 Working with data science pipelines
14

2. Check that the PipelineVersion custom resource was successfully created:
$ oc get pipelineversion <pipeline_version_name> -n <namespace>
1.2.4. Migrating pipelines from database to Kubernetes API storage
You can migrate existing pipelines and pipeline versions from the internal database to Kubernetes
custom resources. This makes it easier to use OpenShift GitOps (Argo CD) or similar tools to manage
pipelines and pipeline versions, while still allowing you to manage them through the OpenShift AI
dashboard, API, and the Kubeflow Pipelines (KFP) Software Development Kit (SDK).
This procedure uses a community-supported Kubeflow Pipelines migration script to export pipelines
from the Data Science Pipelines API and generate corresponding Pipeline and PipelineVersion custom
resources for import into your cluster.
IMPORTANT
The migration script in this procedure is maintained by the Kubeflow Pipelines community
and is not supported by Red Hat. Before you use the script, review the repository and
validate it in a non-production environment.
WARNING
The pipeline and pipeline version IDs change during migration, so existing pipeline
runs do not map to the migrated pipeline version. The original ID is stored in the 
pipelines.kubeflow.org/original-id label.
Prerequisites
You have OpenShift AI administrator privileges or you are the project owner.
You have a data science project with a running pipeline server.
The pipeline server is configured with spec.apiServer.pipelineStore: database.
You have Python 3.11 installed in your local environment.
You have installed the OpenShift CLI (oc) as described in the appropriate documentation for
your cluster:
Installing the OpenShift CLI for OpenShift Dedicated
Installing the OpenShift CLI for Red Hat OpenShift Service on AWS (classic architecture)
Procedure
1. In a terminal window, log in to your OpenShift cluster by using the OpenShift CLI (oc):
$ oc login -u <user_name>

## MANAGING DATA SCIENCE PIPELINES
15

When prompted, enter the OpenShift server URL, connection type, and your password.
2. Set environment variables for your data science project and get the pipeline API route.
In the export command, replace <namespace> with the name of your project:
echo "Setting the prerequisite variables"
export NAMESPACE=<namespace>
export DSPA_NAME=$(oc -n $NAMESPACE get dspa -o 
jsonpath={.items[0].metadata.name})
export API_URL="https://$(oc -n $NAMESPACE get route "ds-pipeline-$DSPA_NAME" -o 
jsonpath={.spec.host})"
3. Create a Python virtual environment and install the required dependencies.
echo "Set up the Python prerequisites"
python3.11 -m venv .venv
./.venv/bin/pip install kfp requests PyYAML
4. Download and run the Kubeflow Pipelines community migration script.
The script connects to the Data Science Pipelines API, exports all pipelines and versions from
the specified data science project, and generates one YAML file per pipeline in a local kfp-
exported-pipelines/ directory. Each file includes a Pipeline resource followed by all associated 
PipelineVersion resources.
a. Run the following command:
curl -L https://raw.githubusercontent.com/kubeflow/pipelines/refs/heads/master/tools/k8s-
native/migration.py -o migration.py
./.venv/bin/python migration.py --skip-tls-verify --kfp-server-host $API_URL --namespace 
$NAMESPACE --token "$(oc whoami --show-token)"
NOTE
The --skip-tls-verify option disables certificate validation and should be used
only in development environments or when connecting to a server with a self-
signed certificate. In production environments, provide a valid certificate
bundle instead.
Additionally, passing the access token directly on the command line might
expose it in shell history or process lists. To reduce this risk, store the token
in an environment variable and reference it in your command:
export KFP_TOKEN=$(oc whoami --show-token)
./.venv/bin/python migration.py --kfp-server-host $API_URL --namespace 
$NAMESPACE --token "$KFP_TOKEN"
Alternatively, use a prompt with read -s to input the token securely at
runtime.
b. Optional: For more information about the script, run the following command:
./.venv/bin/python migration.py --help
Red Hat OpenShift AI Cloud Service 1 Working with data science pipelines
16

c. If you plan to create new or updated PipelineVersion custom resources after migration,
you can compile your pipeline code by using the Kubeflow Pipelines SDK. For more
information, see Compiling the pipeline YAML with the Kubeflow Pipelines SDK  and
Compiling Kubernetes-native manifests with the Kubeflow Pipelines SDK .
5. Apply the exported Kubernetes custom resources to your cluster.
oc apply -f ./kfp-exported-pipelines
6. Change the pipeline server to use Kubernetes API storage.
oc -n "$NAMESPACE" patch dspa "$DSPA_NAME" --type=merge -p {"spec":{"apiServer":
{"pipelineStore":"kubernetes"}}}
NOTE
To view pipelines that were stored in the internal database and not migrated, you
can temporarily change the pipeline server back to database storage.
oc -n $NAMESPACE patch dspa $DSPA_NAME --type=merge -p {"spec":
{"apiServer":{"pipelineStore":"database"}}}
7. Repeat this procedure for each additional data science project that you want to migrate,
changing NAMESPACE to the appropriate project name.
8. Optional: Clean up the local environment.
rm -rf .venv migration.py
Verification
1. Check that the Pipeline and PipelineVersion custom resources were created in your project:
$ oc -n <namespace> get pipelines.pipelines.kubeflow.org
$ oc -n <namespace> get pipelineversions.pipelines.kubeflow.org
2. Verify that the pipeline server is using Kubernetes API storage:
$ oc -n <namespace> get dspa <dspa_name> -o jsonpath={.spec.apiServer.pipelineStore}
{"\n"}
The command should return kubernetes.
Additional resources
Kubeflow Pipelines community migration script source
Script README and usage details
### IMPORTING A DATA SCIENCE PIPELINE
## MANAGING DATA SCIENCE PIPELINES
17

To help you begin working with data science pipelines in OpenShift AI, you can import a YAML file
containing your pipeline’s code to an active pipeline server, or you can import the YAML file from a URL.
This file contains a Kubeflow pipeline compiled by using the Kubeflow compiler. After you have imported
the pipeline to a pipeline server, you can execute the pipeline by creating a pipeline run.
Prerequisites
You have logged in to Red Hat OpenShift AI.
You have previously created a data science project that is available and contains a configured
pipeline server.
You have compiled your pipeline with the Kubeflow compiler and you have access to the
resulting YAML file.
If you are uploading your pipeline from a URL, the URL is publicly accessible.
NOTE
If your pipeline is defined in Python code instead of a YAML file, compile it first by using
the KFP SDK. For more information, see Compiling the pipeline YAML with the Kubeflow
Pipelines SDK.
Procedure
1. From the OpenShift AI dashboard, click Data science pipelines → Pipelines.
2. On the Pipelines page, from the Project drop-down list, select the project that you want to
import a pipeline to.
3. Click Import pipeline.
4. In the Import pipeline dialog, enter the details for the pipeline that you want to import.
a. In the Pipeline name field, enter a name for the pipeline that you want to import.
b. In the Pipeline description field, enter a description for the pipeline that want to import.
c. Select where you want to import your pipeline from by performing one of the following
actions:
Select Upload a file to upload your pipeline from your local machine’s file system.
Import your pipeline by clicking Upload, or by dragging and dropping a file.
Select Import by url to upload your pipeline from a URL, and then enter the URL into
the text box.
d. Click Import pipeline.
Verification
The pipeline that you imported is displayed on the Pipelines page and on the Pipelines tab on
the project details page.
### DELETING A DATA SCIENCE PIPELINE
Red Hat OpenShift AI Cloud Service 1 Working with data science pipelines
18

If you no longer require access to your data science pipeline on the dashboard, you can delete it so that
it does not appear on the Data science pipelines page.
Prerequisites
You have logged in to Red Hat OpenShift AI.
There are active pipelines available on the Pipelines page.
The pipeline that you want to delete does not contain any pipeline versions.
The pipeline that you want to delete does not contain any pipeline versions. For more
information, see Deleting a pipeline version.
Procedure
1. From the OpenShift AI dashboard, click Data science pipelines → Pipelines.
2. On the Pipelines page, from the Project drop-down list, select the project that contains the
pipeline that you want to delete.
3. Click the action menu (⋮) beside the pipeline that you want to delete, and then click Delete
pipeline.
4. In the Delete pipeline dialog, enter the pipeline name in the text field to confirm that you intend
to delete it.
5. Click Delete pipeline.
Verification
The data science pipeline that you deleted is no longer displayed on the Pipelines page.
### DELETING A PIPELINE SERVER
After you have finished running your data science pipelines, you can delete the pipeline server. Deleting a
pipeline server automatically deletes all of its associated pipelines, pipeline versions, and runs. If your
pipeline data is stored in a database, the database is also deleted along with its meta-data. In addition,
after deleting a pipeline server, you cannot create new pipelines or pipeline runs until you create another
pipeline server.
Prerequisites
You have logged in to Red Hat OpenShift AI.
You have previously created a data science project that is available and contains a pipeline
server.
Procedure
1. From the OpenShift AI dashboard, click Data science pipelines → Pipelines.
2. On the Pipelines page, from the Project drop-down list, select the project that contains the
pipeline server that you want to delete.
## MANAGING DATA SCIENCE PIPELINES
19

3. From the Pipeline server actions list, select Delete pipeline server.
4. In the Delete pipeline server dialog, enter the name of the pipeline server in the text field to
confirm that you intend to delete it.
5. Click Delete.
Verification
Pipelines previously assigned to the deleted pipeline server no longer appear on the Pipelines
page for the relevant data science project.
Pipeline runs previously assigned to the deleted pipeline server no longer appear on the Runs
page for the relevant data science project.
### VIEWING THE DETAILS OF A PIPELINE SERVER
You can view the details of pipeline servers configured in OpenShift AI, such as the pipeline’s connection
details and where its data is stored.
Prerequisites
You have logged in to Red Hat OpenShift AI.
You have previously created a data science project that contains an active and available pipeline
server.
Procedure
1. From the OpenShift AI dashboard, click Data science pipelines → Pipelines.
2. On the Pipelines page, from the Project drop-down list, select the project that contains the
pipeline server that you want to view.
3. From the Pipeline server actions list, select Manage pipeline server configuration.
Verification
You can view the pipeline server details in the Manage pipeline server dialog.
### VIEWING EXISTING PIPELINES
You can view the details of pipelines that you have imported to Red Hat OpenShift AI, such as the
pipeline’s last run, when it was created, the pipeline’s executed runs, and details of any associated
pipeline versions.
Prerequisites
You have logged in to Red Hat OpenShift AI.
You have previously created a data science project that is available and contains a pipeline
server.
You have imported a pipeline to an active pipeline server.
Red Hat OpenShift AI Cloud Service 1 Working with data science pipelines
20

Existing pipelines are available.
Procedure
1. From the OpenShift AI dashboard, click Data science pipelines → Pipelines.
2. On the Pipelines page, from the Project drop-down list, select the project that contains the
pipelines that you want to view.
3. Optional: Click Expand ( 
 ) on the row of a pipeline to view its pipeline versions.
Verification
A list of data science pipelines is displayed on the Pipelines page.
### OVERVIEW OF PIPELINE VERSIONS
You can manage incremental changes to pipelines in OpenShift AI by using versioning. This allows you to
develop and deploy pipelines iteratively, preserving a record of your changes. You can track and manage
your changes on the OpenShift AI dashboard, allowing you to schedule and execute runs against all
available versions of your pipeline.
### UPLOADING A PIPELINE VERSION
You can upload a YAML file to an active pipeline server that contains the latest version of your pipeline,
or you can upload the YAML file from a URL. The YAML file must consist of a Kubeflow pipeline
compiled by using the Kubeflow compiler. After you upload a pipeline version to a pipeline server, you
can execute it by creating a pipeline run.
Prerequisites
You have logged in to Red Hat OpenShift AI.
You have previously created a data science project that is available and contains a configured
pipeline server.
You have a pipeline version available and ready to upload.
If you are uploading your pipeline version from a URL, the URL is publicly accessible.
If your pipeline version is based on Python code, compile it to YAML before uploading. For more
information, see Compiling the pipeline YAML with the Kubeflow Pipelines SDK .
Procedure
1. From the OpenShift AI dashboard, click Data science pipelines → Pipelines.
2. On the Pipelines page, from the Project drop-down list, select the project that you want to
upload a pipeline version to.
3. Click the Import pipeline drop-down list, and then select Upload new version.
4. In the Upload new version dialog, enter the details for the pipeline version that you are
uploading.
## MANAGING DATA SCIENCE PIPELINES
21

a. From the Pipeline list, select the pipeline that you want to upload your pipeline version to.
b. In the Pipeline version name field, confirm the name for the pipeline version, and change it
if necessary.
c. In the Pipeline version description field, enter a description for the pipeline version.
d. Select where you want to upload your pipeline version from by performing one of the
following actions:
Select Upload a file to upload your pipeline version from your local machine’s file
system. Import your pipeline version by clicking Upload, or by dragging and dropping a
file.
Select Import by url to upload your pipeline version from a URL, and then enter the
URL into the text box.
e. Click Upload.
Verification
The pipeline version that you uploaded is displayed on the Pipelines page. Click Expand ( 
 )
on the row containing the pipeline to view its versions.
The Version column on the row containing the pipeline version that you uploaded on the
Pipelines page increments by one.
### DELETING A PIPELINE VERSION
You can delete specific versions of a pipeline when you no longer require them. Deleting a default
pipeline version automatically changes the default pipeline version to the next most recent version. If no
pipeline versions exist, the pipeline persists without a default version.
Prerequisites
You have logged in to Red Hat OpenShift AI.
You have previously created a data science project that is available and contains a pipeline
server.
You have imported a pipeline to an active pipeline server.
Procedure
1. From the OpenShift AI dashboard, click Data science pipelines → Pipelines.
The Pipelines page opens.
2. Delete the pipeline versions that you no longer require:
To delete a single pipeline version:
a. From the Project list, select the project that contains a version of a pipeline that you
want to delete.
b. On the row containing the pipeline, click Expand ( 
 ).
Red Hat OpenShift AI Cloud Service 1 Working with data science pipelines
22

c. Click the action menu (⋮) beside the project version that you want to delete, and then
click Delete pipeline version.
The Delete pipeline version dialog opens.
d. Enter the name of the pipeline version in the text field to confirm that you intend to
delete it.
e. Click Delete.
To delete multiple pipeline versions:
a. On the row containing each pipeline version that you want to delete, select the
checkbox.
b. Click the action menu (⋮) next to the Import pipeline drop-down list, and then select
Delete from the list.
Verification
The pipeline version that you deleted is no longer displayed on the Pipelines page, or on the
Pipelines tab for the data science project.
### VIEWING THE DETAILS OF A PIPELINE VERSION
You can view the details of a pipeline version that you have uploaded to Red Hat OpenShift AI, such as
its graph and YAML code.
Prerequisites
You have logged in to Red Hat OpenShift AI.
You have previously created a data science project that is available and contains a pipeline
server.
You have a pipeline available on an active and available pipeline server.
Procedure
1. From the OpenShift AI dashboard, click Data science pipelines → Pipelines.
The Pipelines page opens.
2. From the Project drop-down list, select the project that contains the pipeline versions that you
want to view details for.
3. Click the pipeline name to view further details of its most recent version. The pipeline version
details page opens, displaying the Graph, Summary, and Pipeline spec tabs.
Alternatively, click Expand ( 
 ) on the row containing the pipeline that you want to view
versions for, and then click the pipeline version that you want to view the details of. The pipeline
version details page opens, displaying the Graph, Summary, and Pipeline spec tabs.
Verification
On the pipeline version details page, you can view the pipeline graph, summary details, and
YAML code.
## MANAGING DATA SCIENCE PIPELINES
23
### DOWNLOADING A DATA SCIENCE PIPELINE VERSION
To make further changes to a data science pipeline version that you previously uploaded to OpenShift
AI, you can download pipeline version code from the user interface.
Prerequisites
You have logged in to Red Hat OpenShift AI.
You have previously created a data science project that is available and contains a configured
pipeline server.
You have created and imported a pipeline to an active pipeline server that is available to
download.
Procedure
1. From the OpenShift AI dashboard, click Data science pipelines → Pipelines.
2. On the Pipelines page, from the Project drop-down list, select the project that contains the
version that you want to download.
3. Click Expand ( 
 ) beside the pipeline that contains the version that you want to download.
4. Click the pipeline version that you want to download.
The pipeline version details page opens.
5. Click the Pipeline spec tab, and then click the Download button ( 
 ) to download the
YAML file that contains the pipeline version code to your local machine.
Verification
The pipeline version code downloads to your browser’s default directory for downloaded files.
### OVERVIEW OF DATA SCIENCE PIPELINES CACHING
You can use caching within data science pipelines to optimize execution times and improve resource
efficiency. Caching reduces redundant task execution by reusing results from previous runs with
identical inputs.
Caching is particularly beneficial for iterative tasks, where intermediate steps might not need to be
repeated. Understanding caching can help you design more efficient pipelines and save time in model
development.
Caching operates by storing the outputs of successfully completed tasks and comparing the inputs of
new tasks against previously cached ones. If a match is found, OpenShift AI reuses the cached results
instead of re-executing the task, reducing computation time and resource usage.
1.13.1. Caching criteria
For caching to be effective, the following criteria determine if a task can use previously cached results:
Input data and parameters: If the input data and parameters for a task are unchanged from a
previous run, cached results are eligible for reuse.
Red Hat OpenShift AI Cloud Service 1 Working with data science pipelines
24

Task code and configuration: Changes to the task code or configurations invalidate the cache
to ensure that modifications are always reflected.
Pipeline environment: Changes to the pipeline environment, such as dependency versions, also
affect caching eligibility to maintain consistency.
1.13.2. Viewing cached steps in the OpenShift AI user interface
Cached steps in pipelines are visually indicated in the user interface (UI):
Tasks that use cached results display a green icon, helping you quickly identify which steps were
cached. The Status field in the side panel displays Cached for cached tasks.
The UI also includes information about when the task was previously executed, allowing for easy
verification of cache usage.
To check the caching status of specific tasks, navigate to the pipeline details view in the UI. Cached and
non-cached tasks are clearly indicated. Cached tasks do not display execution logs because they reuse
previously generated outputs and are not re-executed.
1.13.3. Controlling caching in data science pipelines
Caching is enabled by default in OpenShift AI to improve performance. However, there are instances
when disabling caching might be necessary for specific tasks, an entire pipeline, or all pipelines. For
example, caching might not be beneficial for tasks that rely on frequently updated data or unique
computational needs. In other cases, such as debugging, development, or when deterministic re-
execution is required, you might want to disable caching for all pipelines.
CAUTION
Disabling caching at the pipeline or pipeline server level causes all tasks to re-run, potentially increasing
compute time and resource usage.
You can control caching for data science pipelines in the following ways:
Individual task: Data scientists can disable caching for specific steps in a pipeline.
Pipeline (submit time): Data scientists can disable caching when submitting a pipeline run.
Pipeline (compile time): Data scientists can disable caching when compiling a pipeline.
All pipelines (pipeline server): You can disable caching for all pipelines in the pipeline server,
which overrides all pipeline and task-level caching settings.
1.13.3.1. Disabling caching for individual tasks
To disable caching for a particular task, apply the set_caching_options method directly to the task in
your pipeline code:
task_name.set_caching_options(False)
After applying this setting, OpenShift AI runs the task in future pipeline runs, ignoring any cached
results.
## MANAGING DATA SCIENCE PIPELINES
25

You can re-enable caching for individual tasks by setting the set_caching_options parameter to True
or by omitting set_caching_options.
This setting is ignored if caching is disabled in the pipeline server.
1.13.3.2. Disabling caching for a pipeline at submit time
To disable caching for the entire pipeline during pipeline submission, set the enable_caching parameter
to False in your pipeline code. This setting ensures that no steps are cached during pipeline execution.
The enable_caching parameter is available only when using the kfp.client to submit pipelines or start
pipeline runs, such as the run_pipeline method.
Example:
This setting is ignored if caching is disabled during pipeline compilation or in the pipeline server.
1.13.3.3. Disabling caching for a pipeline at compile time
To disable caching for the entire pipeline during compilation, set one of the following options in your
local environment or workbench:
Environment variable:
CLI flag (when using kfp dsl compile):
These settings are ignored if caching is disabled in the pipeline server.
1.13.3.4. Disabling caching for all pipelines (pipeline server)
To disable caching for all pipelines in the pipeline server and override all pipeline and task-level caching
settings, use either of the following methods:
Pipeline server configuration
1. From the OpenShift AI dashboard, click Data science pipelines → Pipelines.
2. On the Pipelines page, from the Project drop-down list, select the project that contains the
pipeline server that you want to configure.
3. From the Pipeline server actions list, select Manage pipeline server configuration.
import kfp
client = kfp.Client()
client.run_pipeline(
    experiment_id=experiment.id,
    pipeline_id=pipeline.id,
    job_name="no-cache-run",
    params={},                # optional
    enable_caching=False,
)
export KFP_DISABLE_EXECUTION_CACHING_BY_DEFAULT=true
kfp dsl compile --disable-execution-caching-by-default
Red Hat OpenShift AI Cloud Service 1 Working with data science pipelines
26

4. In the Pipeline caching section, clear the Allow caching to be configured per pipeline and
task checkbox.
5. Click Save.
Data Science Pipelines Application (cluster administrator)
In the OpenShift console or CLI, set the cacheEnabled field to false in the 
DataSciencePipelinesApplication (DSPA) custom resource for the project.
Example:
To allow caching to be configured at the pipeline and task level, set the cacheEnabled field to true
in the DSPA custom resource.
After applying this setting, all pipeline and task-level caching settings are ignored.
NOTE
Changing this setting updates the CACHEENABLED environment variable in the pipeline
server deployment.
Verification
After configuring caching settings, you can verify its behavior by using one of the following methods:
Check the UI: Locate the green icons in the task list to identify cached steps.
Test task re-runs: Disable caching on specific tasks or the pipeline to confirm that steps re-
execute as expected.
Validate inputs: Ensure the task inputs, parameters, and runtime settings are unchanged when
caching is applied.
NOTE
You can also disable caching for a single node or for your entire pipeline in JupyterLab
using Elyra. For more information, see Disabling node caching in Elyra .
Additional resources
Kubeflow caching documentation: Kubeflow Pipelines - Use Caching
The kfp.client module documentation for the enable_caching parameter: KFP SDK API
Reference - kfp.client
apiVersion: datasciencepipelinesapplications.opendatahub.io/v1
kind: DataSciencePipelinesApplication
metadata:
  name: my-dspa
  namespace: my-namespace
spec:
  apiServer:
    cacheEnabled: false
## MANAGING DATA SCIENCE PIPELINES
27
## MANAGING PIPELINE EXPERIMENTS
### OVERVIEW OF PIPELINE EXPERIMENTS
A pipeline experiment is a workspace where you can try different configurations of your pipelines. You
can use experiments to organize your runs into logical groups. As a data scientist, you can use OpenShift
AI to define, manage, and track pipeline experiments. You can view a record of previously created and
archived experiments from the Experiments page in the OpenShift AI user interface. Pipeline
experiments contain pipeline runs, including recurring runs. This allows you to try different configurations
of your pipelines.
When you work with data science pipelines, it is important to monitor and record your pipeline
experiments to track the performance of your data science pipelines. You can compare the results of up
to 10 pipeline runs at one time, and view available parameter, scalar metric, confusion matrix, and
receiver operating characteristic (ROC) curve data for all selected runs.
You can view artifacts for an executed pipeline run from the OpenShift AI dashboard. Pipeline artifacts
can help you to evaluate the performance of your pipeline runs and make it easier to understand your
pipeline components. Pipeline artifacts can range from plain text data to detailed, interactive data
visualizations.
### CREATING A PIPELINE EXPERIMENT
Pipeline experiments are workspaces where you can try different configurations of your pipelines. You
can also use experiments to organize your pipeline runs into logical groups. Pipeline experiments contain
pipeline runs, including recurring runs.
Prerequisites
You have logged in to Red Hat OpenShift AI.
You have previously created a data science project that is available and contains a configured
pipeline server.
You have imported a pipeline to an active pipeline server.
Procedure
1. From the OpenShift AI dashboard, click Experiments → Experiments and runs.
2. On the Experiments page, from the Project drop-down list, select the project to create the
pipeline experiment in.
3. Click Create experiment.
4. In the Create experiment dialog, configure the pipeline experiment:
a. In the Experiment name field, enter a name for the pipeline experiment.
b. In the Description field, enter a description for the pipeline experiment.
c. Click Create experiment.
Verification
Red Hat OpenShift AI Cloud Service 1 Working with data science pipelines
28

The pipeline experiment that you created is displayed on the Experiments tab.
### ARCHIVING A PIPELINE EXPERIMENT
You can archive your pipeline experiments to store and retain records for future reference. If you need
to reuse an archived experiment, you can restore it at any time. Deleting pipeline experiments is a
separate action and happens only when you explicitly choose to delete them. Unarchived experiments
remain stored unless you manually delete them.
Prerequisites
You have logged in to Red Hat OpenShift AI.
You have previously created a data science project that is available and has a pipeline server.
You have imported a pipeline to an active pipeline server.
A pipeline experiment is available to archive.
Procedure
1. From the OpenShift AI dashboard, click Experiments → Experiments and runs.
2. On the Experiments page, from the Project drop-down list, select the project that contains
the pipeline experiment that you want to archive.
3. Click the action menu (⋮) beside the pipeline experiment that you want to archive, and then
click Archive.
4. In the Archiving experiment dialog, enter the pipeline experiment name in the text field to
confirm that you intend to archive it.
5. Click Archive.
Verification
The archived pipeline experiment does not appear on the Experiments tab, and instead is
displayed on the Archive tab on the Experiments page for the pipeline experiment.
### DELETING AN ARCHIVED PIPELINE EXPERIMENT
You can delete pipeline experiments from the OpenShift AI experiment archive.
Prerequisites
You have logged in to Red Hat OpenShift AI.
You have previously created a data science project that is available and contains a configured
pipeline server.
You have imported a pipeline to an active pipeline server.
A pipeline experiment is available in the pipeline archive.
## MANAGING PIPELINE EXPERIMENTS
29

Procedure
1. From the OpenShift AI dashboard, click Experiments → Experiments and runs.
2. On the Experiments page, from the Project drop-down list, select the project that contains
the archived pipeline experiment that you want to delete.
3. Click the Archive tab.
4. Click the action menu (⋮) beside the pipeline experiment that you want to delete, and then click
Delete.
5. In the Delete experiment? dialog, enter the pipeline experiment name in the text field to
confirm that you intend to delete it.
6. Click Delete.
Verification
The pipeline experiment that you deleted is no longer displayed on the Archive tab on the
Experiments page.
### RESTORING AN ARCHIVED PIPELINE EXPERIMENT
You can restore an archived pipeline experiment to the active state.
Prerequisites
You have logged in to Red Hat OpenShift AI.
You have previously created a data science project that is available and has a pipeline server.
An archived pipeline experiment exists in your project.
Procedure
1. From the OpenShift AI dashboard, click Experiments → Experiments and runs.
2. On the Experiments page, from the Project drop-down list, select the project that contains
the archived pipeline experiment that you want to restore.
3. Click the Archive tab.
4. Click the action menu (⋮) beside the pipeline experiment that you want to restore, and then
click Restore.
5. In the Restore experiment dialog, click Restore.
Verification
The restored pipeline experiment is displayed on the Experiments tab on the Experiments
page.
### VIEWING PIPELINE TASK EXECUTIONS
Red Hat OpenShift AI Cloud Service 1 Working with data science pipelines
30

When a pipeline run executes, you can view details of executed tasks in each step in a pipeline run from
the OpenShift AI dashboard. A step forms part of a task in a pipeline.
Prerequisites
You have logged in to Red Hat OpenShift AI.
You have previously created a data science project that is available and contains a pipeline
server.
You have imported a pipeline to an active pipeline server.
You have previously triggered a pipeline run.
Procedure
1. From the OpenShift AI dashboard, click Experiments → Executions.
2. On the Executions page, from the Project drop-down list, select the project that contains the
experiment for the pipeline task executions that you want to view.
Verification
On the Executions page, you can view the execution details of each pipeline task execution,
such as its name, status, unique ID, and execution type. The execution status indicates whether
the pipeline task has successfully executed. For further information about the details of the task
execution, click the execution name.
### VIEWING PIPELINE ARTIFACTS
After a pipeline run executes, you can view its pipeline artifacts from the OpenShift AI dashboard.
Pipeline artifacts can help you to evaluate the performance of your pipeline runs and make it easier to
understand your pipeline components. Pipeline artifacts can range from plain text data to detailed,
interactive data visualizations.
Prerequisites
You have logged in to Red Hat OpenShift AI.
You have previously created a data science project that is available and contains a pipeline
server.
You have imported a pipeline to an active pipeline server.
You have previously triggered a pipeline run.
Procedure
1. From the OpenShift AI dashboard, click Experiments → Artifacts.
2. On the Artifacts page, from the Project drop-down list, select the project that contains the
pipeline experiment for the pipeline artifacts that you want to view.
3. Click an artifact name in the list to view additional information about the artifact, including its
original run, original execution, and properties.
## MANAGING PIPELINE EXPERIMENTS
31

4. To view or download the content of an artifact stored in S3-compatible object storage, click the
preview icon, the download icon, or the active artifact URI link.
Clicking the preview icon or the URI link for content that your browser can display (such as plain
text, HTML, or markdown) opens the artifact in a new browser tab. Clicking the download icon or
the URI link for content that your browser cannot display (such as a model file) downloads the
artifact. To download an artifact that is displayed in a browser tab, right-click the content and
then click Save as.
Verification
On the Artifacts page, you can view the details of each pipeline artifact, such as its name,
unique ID, type, and URI.
### COMPARING RUNS IN AN EXPERIMENT
You can compare up to 10 pipeline runs in the same experiment at one time, and view available
parameter, scalar metric, confusion matrix, and receiver operating characteristic (ROC) curve data for
all selected runs.
To compare runs from different experiments or pipelines, or to view every pipeline run in a project, see
Comparing runs in different experiments .
Prerequisites
You have logged in to Red Hat OpenShift AI.
You have previously created a data science project that is available and has a pipeline server.
You have imported a pipeline to an active pipeline server.
You have created at least two pipeline runs.
Procedure
1. In the OpenShift AI dashboard, select Experiments → Experiments and runs.
The Experiments page opens.
2. From the Project drop-down list, select the project that contains the runs that you want to
compare.
3. On the Experiments tab, in the Experiment column, click the experiment that you want to
compare runs for. To select runs that are not in an experiment, click Default. All runs that are
created without specifying an experiment will appear in the Default group.
The Runs page opens.
4. Select the checkbox next to each run that you want to compare, and then click Compare runs.
You can compare a maximum of 10 runs at one time.
The Compare runs page opens and displays data for the runs that you selected.
a. The Run list section displays a list of selected runs. You can filter the list by run name,
pipeline version, start date, and status.
b. The Parameters section displays parameter information for each selected run. Set the Hide
parameters with no differences switch to On to hide parameters that have the same
values.
Red Hat OpenShift AI Cloud Service 1 Working with data science pipelines
32

c. The Metrics section displays scalar metric, confusion matrix, and ROC curve data for all
selected runs.
i. On the Scalar metrics tab, set the Hide parameters with no differences switch to On
to hide parameters that have the same values.
ii. On the ROC curve tab, in the artifacts list, adjust the ROC curve chart by clearing the
checkbox next to artifacts that you want to remove from the chart.
5. To select different runs for comparison, click Manage runs.
The Manage runs dialog opens.
a. From the filter drop-down list, select Run, Pipeline version, Created after, or Status to
filter the run list by each value.
b. Clear the checkbox next to each run that you want to remove from your comparison.
c. Select the checkbox next to each run that you want to add to your comparison.
6. Click Update.
Verification
The Compare runs page opens and displays data for the runs that you selected.
### COMPARING RUNS IN DIFFERENT EXPERIMENTS
You can compare up to 10 pipeline runs from any experiment or pipeline in a project, including runs that
do not have a corresponding pipeline, and view available parameter, scalar metric, confusion matrix, and
receiver operating characteristic (ROC) curve data for all selected runs.
Prerequisites
You have logged in to Red Hat OpenShift AI.
You have previously created a data science project that is available and has a pipeline server.
You have imported a pipeline to an active pipeline server.
You have created at least two pipeline runs.
Procedure
1. In the OpenShift AI dashboard, select Data science pipelines → Runs.
The Runs page opens.
2. From the Project drop-down list, select the project that contains the runs that you want to
compare.
3. On the Runs tab, in the Run column, select the checkbox next to each run that you want to
compare, and then click Compare runs. You can compare a maximum of 10 runs at one time.
The Compare runs page opens and displays data for the runs that you selected.
a. The Run list section displays a list of selected runs. You can filter the list by run name,
experiment, pipeline version, start date, and status.
## MANAGING PIPELINE EXPERIMENTS
33

b. The Parameters section displays parameter information for each selected run. Set the Hide
parameters with no differences switch to On to hide parameters that have the same
values.
c. The Metrics section displays scalar metric, confusion matrix, and ROC curve data for all
selected runs.
i. On the Scalar metrics tab, set the Hide parameters with no differences switch to On
to hide parameters that have the same values.
ii. On the ROC curve tab, in the artifacts list, adjust the ROC curve chart by clearing the
checkbox next to artifacts that you want to remove from the chart.
4. To select different runs for comparison, click Manage runs.
The Manage runs page opens.
a. From the filter drop-down list, select Run, Experiment, Pipeline version, Created after, or
Status to filter the run list by each value.
b. Clear the checkbox next to each run that you want to remove from your comparison.
c. Select the checkbox next to each run that you want to add to your comparison.
5. Click Update.
Verification
The Compare runs page opens and displays data for the runs that you selected.
Red Hat OpenShift AI Cloud Service 1 Working with data science pipelines
34
## MANAGING PIPELINE RUNS
### OVERVIEW OF PIPELINE RUNS
A pipeline run is a single execution of a data science pipeline. As data scientist, you can use OpenShift AI
to define, manage, and track executions of a data science pipeline. To view a record of previously
executed, scheduled, and archived runs, you must first select the experiment from the Experiments →
Experiments and runs page in the OpenShift AI interface. After selecting the experiment, you can
access all of its pipeline runs from the Runs page.
You can optimize your use of pipeline runs for portability and repeatability by using pipeline
experiments. With experiments, you can logically group pipeline runs and try different configurations of
your pipelines. You can also clone your pipeline runs to reproduce and scale them, or archive them when
you want to retain a record of their execution, but no longer require them. You can delete archived runs
that you no longer want to retain, or you can restore them to their former state.
You can execute a run once, that is, immediately after its creation, or on a recurring basis. Recurring runs
consist of a copy of a pipeline with all of its parameter values and a run trigger. A run trigger indicates
when a recurring run executes. You can define the following run triggers:
Periodic: Used for scheduling runs to execute in intervals
Cron: Used for scheduling runs as a cron job
You can also configure up to 10 instances of the same run to execute concurrently. You can track the
progress of a run from the run details page on the OpenShift AI user interface. From here, you can view
the graph and output artifacts for the run.
A pipeline run can be in one of the following states:
Scheduled: A pipeline run that is scheduled to execute at least once
Active: A pipeline run that is executing, or stopped
Archived: An archived pipeline run
You can use catch up runs to ensure your pipeline runs do not permanently fall behind schedule when
paused. For example, if you re-enable a paused recurring run, the run scheduler backfills each missed
run interval. If you disable catch up runs, and you have a scheduled run interval ready to execute, the run
scheduler only schedules the run execution for the latest run interval. Catch up runs are enabled by
default. However, if your pipeline handles backfill internally, Red Hat recommends that you disable catch
up runs to avoid duplicate backfill.
After a pipeline run executes, you can view details of its executed tasks on the Executions page, along
with its artifacts, on the Artifacts page. From the Executions page, you can view the execution status
of each task, which indicates whether it completed successfully. You can also view further information
about each executed task by clicking the execution name in the list. From the Artifacts page, you can
view the details of each pipeline artifact, such as its name, unique ID, type, and URI. Pipeline artifacts can
help you to evaluate the performance of your pipeline runs and make it easier to understand your
pipeline components. Pipeline artifacts can range from plain text data to detailed, interactive data
visualizations.
You can view further information about each artifact, including its original run and original execution, by
clicking the artifact name in the list. You can also view or download the content of artifacts stored in S3-
compatible object storage by clicking the preview icon, the download icon, or the active artifact URI link.
## MANAGING PIPELINE RUNS
35

Clicking the preview icon or the URI link for content that your browser can display (such as plain text,
HTML, or markdown) opens the artifact in a new browser tab. Clicking the download icon or the URI link
for content that your browser cannot display (such as a model file) downloads the artifact. To download
an artifact that is displayed in a browser tab, right-click the content and then click Save as.
NOTE
Artifacts that are not stored in S3-compatible object storage are not available to
download and do not display an active URI link.
You can review and analyze logs for each step in an active pipeline run. With the log viewer, you can
search for specific log messages, view the log for each step, and download the step logs to your local
machine.
### STORING DATA WITH DATA SCIENCE PIPELINES
When you run a data science pipeline, OpenShift AI stores the pipeline YAML configuration file and
resulting pipeline run artifacts in the root directory of your storage bucket. The directories that contain
pipeline run artifacts can differ depending on where you executed the pipeline run from. See the
following table for further information:
Table 3.1. Pipeline configuration file and artifacts storage locations
Pipeline run source
Pipeline storage directory
Run artifacts storage directory
OpenShift AI dashboard
/pipelines/<pipeline_version
_id>
Example: /pipelines/1d01c4eb-
d2ab-4916-9935-
a73a5580f1fb
/<pipeline_name>/<pipeline 
run_id>
Example: iris-training-
pipeline/2g48k8pw-a8ib-
4884-9145-h41j7599h3ds
JupyterLab Elyra extension
/pipelines/<pipeline_version
_id>
/<pipeline_name_timestamp
>
Example: /hello-generic-world-
0523161704
With the JupyterLab Elyra
extension, you can also set an
object storage path prefix.
Example: /iris-project/hello-
generic-world-0523161704
If you want to use an existing artifact that was not generated by a task in a pipeline, you can use the
kfp.dsl.importer component to import the artifact from its URI. You can only import these artifacts to the
S3-compatible object storage bucket that you define in the Bucket field in your pipeline server
configuration. For more information about the kfp.dsl.importer component, see Special Case: Importer
Components.
### VIEWING ACTIVE PIPELINE RUNS
Red Hat OpenShift AI Cloud Service 1 Working with data science pipelines
36

You can view a list of pipeline runs that were previously executed in a pipeline experiment. From this list,
you can view details relating to your pipeline runs, such as the pipeline version that the run belongs to,
along with the run status, duration, and execution start time.
Prerequisites
You have logged in to Red Hat OpenShift AI.
You have previously created a data science project that is available and has a pipeline server.
You have imported a pipeline to an active pipeline server.
You have previously executed a pipeline run that is available.
Procedure
1. From the OpenShift AI dashboard, click Experiments → Experiments and runs.
2. On the Experiments page, from the Project drop-down list, select the project that contains
the pipeline experiment for the active pipeline runs that you want to view.
3. From the list of experiments, click the experiment that contains the active pipeline runs that you
want to view.
The Runs page opens.
After a run has completed its execution, the run status is displayed in the Status column of the
Runs tab, indicating whether the run succeeded or failed.
Verification
A list of active runs is displayed on the Runs tab on the Runs page for the pipeline experiment.
### EXECUTING A PIPELINE RUN
By default, a pipeline run executes once immediately after it is created.
Prerequisites
You have logged in to Red Hat OpenShift AI.
You have previously created a data science project that is available and contains a configured
pipeline server.
You have imported a pipeline to an active pipeline server.
Procedure
1. From the OpenShift AI dashboard, click Experiments → Experiments and runs.
2. On the Experiments page, from the Project drop-down list, select the project that contains
the pipeline experiment that you want to create a run for.
3. From the list of pipeline experiments, click the experiment that you want to create a run for.
4. Click Create run.
## MANAGING PIPELINE RUNS
37

5. On the Create run page, configure the run:
a. From the Experiment list, select the pipeline experiment that you want to create a run for.
Alternatively, to create a new pipeline experiment, click Create new experiment, and then
complete the relevant fields in the Create experiment dialog.
b. In the Name field, enter a name for the run, up to 255 characters.
c. In the Description field, enter a description for the run, up to 255 characters.
d. From the Pipeline list, select the pipeline that you want to create a run for. Alternatively, to
create a new pipeline, click Create new pipeline, and then complete the relevant fields in
the Import pipeline dialog.
e. From the Pipeline version list, select the pipeline version to create a run for. Alternatively,
to upload a new version, click Upload new version, and then complete the relevant fields in
the Upload new version dialog.
f. Configure the input parameters for the run by selecting the parameters from the list.
g. Click Create run.
The details page for the run opens.
Verification
The pipeline run that you created is displayed on the Runs tab on the Runs page for the pipeline
experiment.
### STOPPING AN ACTIVE PIPELINE RUN
If you no longer require an active pipeline run to continue executing in a pipeline experiment, you can
stop the run before its defined end date.
Prerequisites
You have logged in to Red Hat OpenShift AI.
There is a previously created data science project available that contains a pipeline server.
You have imported a pipeline to an active pipeline server.
An active pipeline run is currently executing.
Procedure
1. From the OpenShift AI dashboard, click Experiments → Experiments and runs.
2. On the Experiments page, from the Project drop-down list, select the project that contains
the pipeline experiment for the active run that you want to stop.
3. From the list of pipeline experiments, click the pipeline experiment that contains the run that
you want to stop.
4. On the Runs tab, click the action menu ( ⋮) beside the active run that you want to stop, and
then click Stop.
There might be a short delay while the run stops.
Red Hat OpenShift AI Cloud Service 1 Working with data science pipelines
38

Verification
The Failed status icon ( 
 ) is displayed in the Status column of the stopped run.
### DUPLICATING AN ACTIVE PIPELINE RUN
To make it easier to quickly execute pipeline runs with the same configuration in a pipeline experiment,
you can duplicate them.
Prerequisites
You have logged in to Red Hat OpenShift AI.
You have previously created a data science project that is available and contains a configured
pipeline server.
You have imported a pipeline to an active pipeline server.
An active run is available to duplicate on the Active tab on the Runs page.
Procedure
1. From the OpenShift AI dashboard, click Experiments → Experiments and runs.
2. On the Experiments page, from the Project drop-down list, select the project that contains
the pipeline experiment for the pipeline run that you want to duplicate.
3. From the list of pipeline experiments, click the experiment that contains the pipeline run that
you want to duplicate.
4. Click the action menu (⋮) beside the relevant active run, and then click Duplicate.
5. On the Duplicate run page, configure the duplicate run:
a. From the Experiment list, select a pipeline experiment to contain the duplicate run.
Alternatively, to create a new pipeline experiment, click Create new experiment, and then
complete the relevant fields in the Create experiment dialog.
b. In the Name field, enter a name for the duplicate run.
c. In the Description field, enter a description for the duplicate run.
d. From the Pipeline list, select a pipeline to contain the duplicate run. Alternatively, to create
a new pipeline, click Create new pipeline, and then complete the relevant fields in the
Import pipeline dialog.
e. From the Pipeline version list, select a pipeline version to contain the duplicate run.
Alternatively, to upload a new version, click Upload new version, and then complete the
relevant fields in the Upload new version dialog.
f. In the Parameters section, configure input parameters for the duplicate run by selecting
parameters from the list.
g. Click Create run.
The details page for the run opens.
## MANAGING PIPELINE RUNS
39

Verification
The duplicate pipeline run is displayed on the Runs tab on the Runs page for the pipeline
experiment.
### VIEWING SCHEDULED PIPELINE RUNS
You can view a list of pipeline runs that are scheduled for execution in a pipeline experiment. From this
list, you can view details relating to your pipeline runs, such as the pipeline version that the run belongs
to. You can also view the run status, execution frequency, and schedule.
Prerequisites
You have logged in to Red Hat OpenShift AI.
You have previously created a data science project that is available and contains a pipeline
server.
You have imported a pipeline to an active pipeline server.
You have scheduled a pipeline run that is available to view.
Procedure
1. From the OpenShift AI dashboard, click Experiments → Experiments and runs.
2. On the Experiments page, from the Project drop-down list, select the project that contains
the pipeline experiment for the scheduled pipeline runs that you want to view.
3. From the list of pipeline experiments, click the experiment that contains the pipeline runs that
you want to view.
4. On the Runs page, click the Schedules tab.
After a run is scheduled, the Status column indicates whether the run is ready or unavailable for
execution. To change its execution availability, set the Status switch to On or Off. Alternatively,
you can change its execution availability from the details page for the scheduled run by clicking
the Actions drop-down menu, and then selecting Enable or Disable.
Verification
A list of scheduled runs is displayed on the Schedules tab on the Runs page for the pipeline
experiment.
### SCHEDULING A PIPELINE RUN USING A CRON JOB
You can use a cron job to schedule a pipeline run to execute at a specific time. Cron jobs are useful for
creating periodic and recurring tasks, and can also schedule individual tasks for a specific time, such as if
you want to schedule a run for a low activity period. To successfully execute runs in OpenShift AI, you
must use the supported format. See Cron Expression Format for more information.
The following examples show the correct format:
Red Hat OpenShift AI Cloud Service 1 Working with data science pipelines
40

Run occurrence
Cron format
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
Additional resources
Cron Expression Format
### SCHEDULING A PIPELINE RUN
To repeatedly run a pipeline, you can create a scheduled pipeline run.
Prerequisites
You have logged in to Red Hat OpenShift AI.
You have previously created a data science project that is available and contains a configured
pipeline server.
You have imported a pipeline to an active pipeline server.
Procedure
1. To go to the Schedules tab for a run, perform one of the following sets of actions:
To select a run from an experiment
a. From the OpenShift AI dashboard, click Experiments → Experiments and runs.
b. On the Experiments page, from the Project drop-down list, select the project that
contains the pipeline experiment for the run that you want to schedule.
c. From the list of pipeline experiments, click the experiment that contains the run that
you want to schedule.
d. Click the Schedules tab.
To select any run
a. From the OpenShift AI dashboard, click Data science pipelines → Runs.
b. On the Runs page, from the Project drop-down list, select the project that contains
the run that you want to schedule.
c. Click the Schedules tab.
## MANAGING PIPELINE RUNS
41

2. Click Create schedule.
3. On the Create schedule page, configure the run that you are scheduling:
a. From the Experiment list, select the pipeline experiment that you want to contain the
scheduled run. Alternatively, to create a new pipeline experiment, click Create new
experiment, and then complete the relevant fields in the Create experiment dialog.
b. In the Name field, enter a name for the run.
c. In the Description field, enter a description for the run.
d. From the Trigger type list, select one of the following options:
Select Periodic to specify an execution frequency. In the Run every field, enter a
number and select an execution frequency from the list.
Select Cron to specify the execution schedule in cron format in the Cron string field.
This creates a cron job to execute the run. Click the Copy button ( 
 ) to copy the
cron job schedule to the clipboard. The field furthest to the left represents seconds. For
more information about scheduling tasks using the supported cron format, see Cron
Expression Format.
e. In the Maximum concurrent runs field, specify the number of runs that can execute
concurrently, from a range of one to ten.
f. For Start date, specify a start date for the run. Select a start date using the calendar, and
the start time from the list of times.
g. For End date, specify an end date for the run. Select an end date using the calendar, and
the end time from the list of times.
h. For Catch up, enable or disable catch up runs. You can use catch up runs to ensure your
pipeline runs do not permanently fall behind schedule when they are paused. For example, if
you re-enable a paused recurring run, the run scheduler backfills each missed run interval.
i. From the Pipeline list, select the pipeline that you want to create a run for. Alternatively, to
create a new pipeline, click Create new pipeline, and then complete the relevant fields in
the Import pipeline dialog.
j. For Pipeline version, select one of the following options:
Select Always use the latest pipeline version so that each recurring run automatically
uses the most recent pipeline version.
Select Use fixed version, then select a specific pipeline version for all recurring runs.
Alternatively, to upload a new version, click Upload new version, and then complete the
relevant fields in the Upload new version dialog.
k. Configure the input parameters for the run by selecting the parameters from the list.
l. Click Create schedule.
Verification
Red Hat OpenShift AI Cloud Service 1 Working with data science pipelines
42

The pipeline run that you scheduled is displayed on the Schedules tab on the Runs page for the
pipeline experiment.
### DUPLICATING A SCHEDULED PIPELINE RUN
To make it easier to schedule runs to execute as part of your pipeline experiment, you can duplicate
existing scheduled runs.
Prerequisites
You have logged in to Red Hat OpenShift AI.
You have previously created a data science project that is available and contains a configured
pipeline server.
You have imported a pipeline to an active pipeline server.
A scheduled run is available to duplicate on the Schedules tab on the Runs page.
Procedure
1. To go to the Schedules tab for a run, perform one of the following sets of actions:
To select a run from an experiment
a. From the OpenShift AI dashboard, click Experiments → Experiments and runs.
b. On the Experiments page, from the Project drop-down list, select the project that
contains the pipeline experiment for the run that you want to duplicate.
c. From the list of pipeline experiments, click the experiment that contains the run that
you want to duplicate.
d. Click the Schedules tab.
To select any run
a. From the OpenShift AI dashboard, click Data science pipelines → Runs.
b. On the Runs page, from the Project drop-down list, select the project that contains
the run that you want to schedule.
c. Click the Schedules tab.
2. Click the action menu (⋮) beside the run that you want to duplicate, and then click Duplicate.
3. On the Duplicate schedule page, configure the duplicate run:
a. From the Experiment list, select a pipeline experiment to contain the duplicate run.
Alternatively, to create a new pipeline experiment, click Create new experiment, and then
complete the relevant fields in the Create experiment dialog.
b. In the Name field, enter a name for the duplicate run.
c. In the Description field, enter a description for the duplicate run.
d. From the Trigger type list, select one of the following options:
## MANAGING PIPELINE RUNS
43

Select Periodic to specify an execution frequency. In the Run every field, enter a
number, and select an execution frequency from the list.
Select Cron to specify the execution schedule in cron format in the Cron string field.
This creates a cron job to execute the run. Click the Copy button ( 
 ) to copy the
cron job schedule to the clipboard. The field furthest to the left represents seconds. For
more information about scheduling tasks using the supported cron format, see Cron
Expression Format.
e. For Maximum concurrent runs, specify the number of runs that can execute concurrently,
from a range of one to ten.
f. For Start date, specify a start date for the duplicate run. Select a start date using the
calendar, and the start time from the list of times.
g. For End date, specify an end date for the duplicate run. Select an end date using the
calendar, and the end time from the list of times.
h. For Catch up, enable or disable catch up runs. You can use catch up runs to ensure your
pipeline runs do not permanently fall behind schedule when they are paused. For example, if
you re-enable a paused recurring run, the run scheduler backfills each missed run interval.
i. From the Pipeline list, select the pipeline that you want to create a duplicate run for.
Alternatively, to create a new pipeline, click Create new pipeline, and then complete the
relevant fields in the Import pipeline dialog.
j. For Pipeline version, select one of the following options:
Select Always use the latest pipeline version so that each recurring run automatically
uses the most recent pipeline version.
Select Use fixed version, then select a specific pipeline version for all recurring runs.
Alternatively, to upload a new version, click Upload new version, and then complete the
relevant fields in the Upload new version dialog.
k. Configure input parameters for the run by selecting parameters from the list.
l. Click Schedule run.
Verification
The pipeline run that you duplicated is displayed on the Schedules tab on the Runs page for the
pipeline experiment.
### DELETING A SCHEDULED PIPELINE RUN
To discard pipeline runs that you previously scheduled, but no longer require, you can delete them so
that they do not appear on the Schedules page.
Prerequisites
You have logged in to Red Hat OpenShift AI.
You have previously created a data science project that is available and contains a configured
pipeline server.
Red Hat OpenShift AI Cloud Service 1 Working with data science pipelines
44

You have imported a pipeline to an active pipeline server.
You have previously scheduled a run that is available to delete.
Procedure
1. From the OpenShift AI dashboard, click Experiments → Experiments and runs.
2. On the Experiments page, from the Project drop-down list, select the project that contains
the pipeline experiment for the scheduled pipeline run that you want to delete.
3. From the list of pipeline experiments, click the experiment that contains the scheduled pipeline
run that you want to delete.
4. On the Runs page, click the Schedules tab.
5. Click the action menu (⋮) beside the scheduled pipeline run that you want to delete, and then
click Delete.
6. In the Delete schedule dialog, enter the run name in the text field to confirm that you intend to
delete it.
7. Click Delete.
Verification
The run that you deleted is no longer displayed on the Schedules tab for the pipeline
experiment.
### VIEWING THE DETAILS OF A PIPELINE RUN
To gain a clearer understanding of your pipeline runs, you can view the details of a previously triggered
pipeline run, such as its graph, execution details, and run output.
Prerequisites
You have logged in to Red Hat OpenShift AI.
You have previously created a data science project that is available and contains a pipeline
server.
You have imported a pipeline to an active pipeline server.
You have previously triggered a pipeline run.
Procedure
1. From the OpenShift AI dashboard, click Data science pipelines → Runs.
2. On the Runs page, from the Project drop-down list, select the project that you want to view the
details of a pipeline run for.
3. On the Runs page, click the name of the run that you want to view the details of.
The details page for the run opens.
## MANAGING PIPELINE RUNS
45

Verification
On the run details page, you can view the run graph, execution details, input parameters, step
logs, and run output.
### VIEWING ARCHIVED PIPELINE RUNS
You can view a list of pipeline runs that you have archived. You can view details for your archived
pipeline runs, such as the pipeline version, run status, duration, and execution start date.
Prerequisites
You have logged in to Red Hat OpenShift AI.
You have previously created a data science project that is available and has a pipeline server.
You have imported a pipeline to an active pipeline server.
An archived pipeline run exists.
Procedure
1. From the OpenShift AI dashboard, click Experiments → Experiments and runs.
2. On the Experiments page, from the Project drop-down list, select the project that contains
the pipeline experiment for the archived pipeline runs that you want to view.
3. From the list of pipeline experiments, click the experiment that contains the archived pipeline
runs that you want to view.
4. On the Runs page, click the Archive tab.
Verification
A list of archived runs is displayed on the Archive tab on the Runs page for the pipeline
experiment.
### ARCHIVING A PIPELINE RUN
You can retain records of your pipeline runs by archiving them. If required, you can restore runs from
your archive to reuse, or delete runs that are no longer required.
Prerequisites
You have logged in to Red Hat OpenShift AI.
You have previously created a data science project that is available and has a pipeline server.
You have imported a pipeline to an active pipeline server.
You have previously executed a pipeline run that is available.
Procedure
1. From the OpenShift AI dashboard, click Experiments → Experiments and runs.
Red Hat OpenShift AI Cloud Service 1 Working with data science pipelines
46

2. On the Experiments page, from the Project drop-down list, select the project that contains
the pipeline experiment for the run that you want to archive.
3. From the list of pipeline experiments, click the experiment that contains the pipeline run that
you want to archive.
The Runs page opens.
4. On the Runs tab, click the action menu ( ⋮) beside the pipeline run that you want to archive, and
then click Archive.
5. In the Archiving run dialog, enter the run name in the text field to confirm that you intend to
archive it.
6. Click Archive.
Verification
The archived run does not appear on the Runs tab, and instead is displayed on the Archive tab
on the Runs page for the pipeline experiment.
### RESTORING AN ARCHIVED PIPELINE RUN
You can restore an archived run to the active state.
Prerequisites
You have logged in to Red Hat OpenShift AI.
You have previously created a data science project that is available and has a pipeline server.
You have imported a pipeline to an active pipeline server.
An archived run exists in your project.
Procedure
1. From the OpenShift AI dashboard, click Experiments → Experiments and runs.
2. On the Experiments page, from the Project drop-down list, select the project that contains
the pipeline experiment that you want to restore.
3. From the list of pipeline experiments, click the experiment that contains the archived pipeline
run that you want to restore.
4. On the Runs page, click the Archive tab.
5. Click the action menu (⋮) beside the pipeline run that you want to restore, and then click
Restore.
6. In the Restore run? dialog, click Restore.
Verification
The restored run is displayed on the Runs tab on the Runs page for the pipeline experiment.
## MANAGING PIPELINE RUNS
47
### DELETING AN ARCHIVED PIPELINE RUN
You can delete pipeline runs from the OpenShift AI run archive.
Prerequisites
You have logged in to Red Hat OpenShift AI.
You have previously created a data science project that is available and has a pipeline server.
You have imported a pipeline to an active pipeline server.
You have previously archived a pipeline run.
Procedure
1. From the OpenShift AI dashboard, click Data science pipelines → Runs.
2. On the Runs page, from the Project drop-down list, select the project that you want to delete
an archived pipeline run from.
3. Click the Archive tab.
4. Click the action menu (⋮) beside the pipeline run that you want to delete, and then click Delete.
5. In the Delete run? dialog, enter the run name in the text field to confirm that you intend to
delete it.
6. Click Delete.
Verification
The archived run that you deleted is no longer displayed on the Archive tab on the Runs page.
### DUPLICATING AN ARCHIVED PIPELINE RUN
To make it easier to reproduce runs with the same configuration as runs in your archive, you can
duplicate them.
Prerequisites
You have logged in to Red Hat OpenShift AI.
You have previously created a data science project that is available and contains a configured
pipeline server.
You have imported a pipeline to an active pipeline server.
An archived run is available to duplicate on the Archived tab on the Runs page.
Procedure
1. From the OpenShift AI dashboard, click Data science pipelines → Runs.
Red Hat OpenShift AI Cloud Service 1 Working with data science pipelines
48

2. On the Runs page, from the Project drop-down list, select the project that you want to
duplicate a pipeline run for.
3. On the Runs page, click the Archive tab.
4. Click the action menu (⋮) beside the pipeline run that you want to duplicate, and then click
Duplicate.
5. On the Duplicate run page, configure the duplicate run:
a. From the Experiment list, select a pipeline experiment to contain the duplicate run.
Alternatively, to create a new pipeline experiment, click Create new experiment, and then
complete the relevant fields in the Create experiment dialog.
b. In the Name field, enter a name for the duplicate run.
c. In the Description field, enter a description for the duplicate run.
d. From the Pipeline list, select a pipeline to contain the duplicate run. Alternatively, to create
a new pipeline, click Create new pipeline, and then complete the relevant fields in the
Import pipeline dialog.
e. From the Pipeline version list, select a pipeline version to contain the duplicate run.
Alternatively, to upload a new version, click Upload new version, and then complete the
relevant fields in the Upload new version dialog.
f. In the Parameters section, configure input parameters for the duplicate run by selecting
parameters from the list.
g. Click Create run.
The details page for the run opens.
Verification
The duplicate pipeline run is displayed on the Runs tab on the Runs page for the pipeline
experiment.
## MANAGING PIPELINE RUNS
49
## WORKING WITH PIPELINE LOGS
### ABOUT PIPELINE LOGS
You can review and analyze step logs for each step in a triggered pipeline run.
To help you troubleshoot and audit your pipelines, you can review and analyze these step logs by using
the log viewer in the OpenShift AI dashboard. From here, you can search for specific log messages, view
the log for each step, and download the step logs to your local machine.
If the step log file exceeds its capacity, a warning is displayed above the log viewer stating that the log
window displays partial content. Expanding the warning displays further information, such as how the log
viewer refreshes every three seconds, and that each step log displays the last 500 lines of log messages
received. In addition, you can click download all step logs to download all step logs to your local
machine.
Each step has a set of container logs. You can view these container logs by selecting a container from
the Steps list in the log viewer. The Step-main container log consists of the log output for the step. The
step-copy-artifact container log consists of output relating to artifact data sent to s3-compatible
storage. If the data transferred between the steps in your pipeline is larger than 3 KB, five container logs
are typically available. These logs contain output relating to data transferred between your persistent
volume claims (PVCs).
### VIEWING PIPELINE STEP LOGS
To help you troubleshoot and audit your pipelines, you can review and analyze the log of each pipeline
step using the log viewer. From here, you can search for specific log messages and download the logs
for each step in your pipeline. If the pipeline is running, you can also pause and resume the log from the
log viewer.
NOTE
Logs are no longer stored in S3-compatible storage for Python scripts which are running
in Elyra pipelines. From OpenShift AI version 2.11, you can view these logs in the pipeline
step log viewer.
For this change to take effect, you must use the Elyra runtime images provided in
workbench images at version 2024.1 or later.
If you have an older workbench image version, update the Version selection field to a
compatible workbench image version, for example, 2024.1, as described in Updating a
project workbench.
Updating your workbench image version will clear any existing runtime image selections
for your pipeline. After you update your workbench version, open your workbench IDE and
update the properties of your pipeline to select a runtime image.
Prerequisites
You have logged in to Red Hat OpenShift AI.
You have previously created a data science project that is available and contains a pipeline
server.
Red Hat OpenShift AI Cloud Service 1 Working with data science pipelines
50

You have imported a pipeline to an active pipeline server.
You have previously triggered a pipeline run.
Procedure
1. From the OpenShift AI dashboard, click Data science pipelines → Runs.
2. On the Runs page, from the Project drop-down list, select the project that you want to view
pipeline step logs for.
3. On the Runs page, click the name of the run that you want to view logs for.
4. On the run details page, on the Graph tab, click the pipeline step that you want to view logs for.
5. Click the Logs tab.
6. To view the logs of another pipeline step, from the Steps list, select the step that you want to
view logs for.
7. Analyze the log using the log viewer.
To search for a specific log message, enter at least part of the message in the search bar.
To view the full log in a separate browser window, click the action menu (⋮) and select
View raw logs. Alternatively, to expand the size of the log viewer, click the action menu (⋮)
and select Expand.
Verification
You can view the logs for each step in your pipeline.
### DOWNLOADING PIPELINE STEP LOGS
Instead of viewing the step logs of a pipeline run using the log viewer on the OpenShift AI dashboard,
you can download them for further analysis. You can choose to download the logs belonging to all steps
in your pipeline, or you can download the log only for the step log displayed in the log viewer.
Prerequisites
You have logged in to Red Hat OpenShift AI.
You have previously created a data science project that is available and contains a pipeline
server.
You have imported a pipeline to an active pipeline server.
You have previously triggered a pipeline run.
Procedure
1. From the OpenShift AI dashboard, click Data science pipelines → Runs.
2. On the Runs page, from the Project drop-down list, select the project that you want to
download logs for.
## WORKING WITH PIPELINE LOGS
51

3. On the Runs page, click the name of the run that you want to download logs for.
4. On the run details page, on the Graph tab, click the pipeline step that you want to download
logs for.
5. Click the Logs tab.
6. In the log viewer, click the Download button ( 
 ).
a. Select Download current stop log to download the log for the current pipeline step.
b. Select Download all step logs to download the logs for all steps in your pipeline run.
Verification
The step logs download to your browser’s default directory for downloaded files.
Red Hat OpenShift AI Cloud Service 1 Working with data science pipelines
52
## WORKING WITH PIPELINES IN JUPYTERLAB
### OVERVIEW OF PIPELINES IN JUPYTERLAB
You can use Elyra to create visual end-to-end pipeline workflows in JupyterLab. Elyra is an extension for
JupyterLab that provides you with a Pipeline Editor to create pipeline workflows that can be executed in
OpenShift AI.
You can access the Elyra extension within JupyterLab when you create the most recent version of one
of the following workbench images:
Standard Data Science
PyTorch
TensorFlow
TrustyAI
ROCm-PyTorch
ROCm-TensorFlow
The Elyra pipeline editor is only available in specific workbench images. To use Elyra, the workbench
must be based on a JupyterLab image. The Elyra extension is not available in code-server or RStudio
IDEs. The workbench must also be derived from the Standard Data Science image. It is not available in
Minimal Python or CUDA-based workbenches. All other supported JupyterLab-based workbench
images have access to the Elyra extension.
When you use the Pipeline Editor to visually design your pipelines, minimal coding is required to create
and run pipelines. For more information about Elyra, see Elyra Documentation. For more information
about the Pipeline Editor, see Visual Pipeline Editor. After you have created your pipeline, you can run it
locally in JupyterLab, or remotely using data science pipelines in OpenShift AI.
The pipeline creation process consists of the following tasks:
Create a data science project that contains a workbench.
Create a pipeline server.
Create a new pipeline in the Pipeline Editor in JupyterLab.
Develop your pipeline by adding Python notebooks or Python scripts and defining their runtime
properties.
Define execution dependencies.
Run or export your pipeline.
Before you can run a pipeline in JupyterLab, your pipeline instance must contain a runtime
configuration. A runtime configuration defines connectivity information for your pipeline instance and
S3-compatible cloud storage.
If you create a workbench as part of a data science project, a default runtime configuration is created
automatically. However, if you create a workbench from the Start basic workbench tile in the OpenShift
AI dashboard, you must create a runtime configuration before you can run your pipeline in JupyterLab.
## WORKING WITH PIPELINES IN JUPYTERLAB
53

For more information about runtime configurations, see Runtime Configuration. As a prerequisite,
before you create a workbench, ensure that you have created and configured a pipeline server within the
same data science project as your workbench.
You can use S3-compatible cloud storage to make data available to your notebooks and scripts while
they are executed. Your cloud storage must be accessible from the machine in your deployment that
runs JupyterLab and from the cluster that hosts data science pipelines. Before you create and run
pipelines in JupyterLab, ensure that you have your s3-compatible storage credentials readily available.
Additional resources
Elyra Documentation
Visual Pipeline Editor
Runtime Configuration.
### ACCESSING THE PIPELINE EDITOR
You can use Elyra to create visual end-to-end pipeline workflows in JupyterLab. Elyra is an extension for
JupyterLab that provides you with a Pipeline Editor to create pipeline workflows that can execute in
OpenShift AI.
Prerequisites
You have logged in to Red Hat OpenShift AI.
You have created a data science project.
You have created and configured a pipeline server within the data science project that contains
your workbench.
IMPORTANT
To ensure that the runtime configuration is created automatically, you must
create the pipeline server before you create the workbench.
You have created a workbench with a workbench image that contains the Elyra extension
(Standard Data Science, TensorFlow, TrustyAI, ROCm-PyTorch, ROCm-TensorFlow, or
PyTorch), as described in Creating a workbench and selecting an IDE .
You have started the workbench and opened the JupyterLab interface, as described in
Accessing your workbench IDE .
IMPORTANT
The Elyra pipeline editor is available in specific workbench images only. To use
Elyra, the workbench must be based on a JupyterLab image. The Elyra extension
is not available in code-server or RStudio IDEs. The workbench must also be
derived from the Standard Data Science image. It is not available in Minimal
Python or CUDA-based workbenches. All other supported JupyterLab-based
workbench images have access to the Elyra extension.
You have access to S3-compatible storage.
Red Hat OpenShift AI Cloud Service 1 Working with data science pipelines
54

Procedure
1. After you open JupyterLab, confirm that the JupyterLab launcher is automatically displayed.
2. In the Elyra section of the JupyterLab launcher, click the Pipeline Editor tile.
The Pipeline Editor opens.
Verification
You can view the Pipeline Editor in JupyterLab.
### CREATING A RUNTIME CONFIGURATION
If you create a workbench as part of a data science project, a default runtime configuration is created
automatically. However, if you create a workbench from the Start basic workbench tile in the OpenShift
AI dashboard, you must create a runtime configuration before you can run your pipeline in JupyterLab.
This enables you to specify connectivity information for your pipeline instance and S3-compatible cloud
storage.
Prerequisites
You have logged in to Red Hat OpenShift AI.
You have access to S3-compatible cloud storage.
You have created a data science project that contains a workbench.
You have created and configured a pipeline server within the data science project that contains
your workbench.
You have created and launched a workbench from a workbench image that contains the Elyra
extension (Standard Data Science, TensorFlow, TrustyAI, ROCm-PyTorch, ROCm-TensorFlow,
or PyTorch).
IMPORTANT
The Elyra pipeline editor is available in specific workbench images only. To use
Elyra, the workbench must be based on a JupyterLab image. The Elyra extension
is not available in code-server or RStudio IDEs. The workbench must also be
derived from the Standard Data Science image. It is not available in Minimal
Python or CUDA-based workbenches. All other supported JupyterLab-based
workbench images have access to the Elyra extension.
Procedure
1. In the left sidebar of JupyterLab, click Runtimes ( 
 ).
2. Click the Create new runtime configuration button ( 
 ).
The Add new Data Science Pipelines runtime configuration page opens.
3. Complete the relevant fields to define your runtime configuration.
a. In the Display Name field, enter a name for your runtime configuration.
## WORKING WITH PIPELINES IN JUPYTERLAB
55

b. Optional: In the Description field, enter a description to define your runtime configuration.
c. Optional: In the Tags field, click Add Tag to define a category for your pipeline instance.
Enter a name for the tag and press Enter.
d. Define the credentials of your data science pipeline:
i. In the Data Science Pipelines API Endpoint field, enter the API endpoint of your data
science pipeline. Do not specify the pipelines namespace in this field.
IMPORTANT
With Elyra, you can now use a service-based URL instead of the route-
based URL by including the port number. Using the service-based URL
allows your data science pipeline to access the service directly.
ii. In the Public Data Science Pipelines API Endpoint field, enter the public API endpoint
of your data science pipeline.
IMPORTANT
You can obtain the data science pipelines API endpoint from the Data
science pipelines → Runs page in the dashboard. Copy the relevant
endpoint and enter it in the Public Data Science Pipelines API
Endpoint field.
iii. Optional: In the Data Science Pipelines User Namespace field, enter the relevant user
namespace to run pipelines.
iv. From the Authentication Type list, select the authentication type required to
authenticate your pipeline.
IMPORTANT
If you created a workbench directly from the Start basic workbench tile
on the dashboard, select EXISTING_BEARER_TOKEN from the
Authentication Type list.
v. In the Data Science Pipelines API Endpoint Username field, enter the user name
required for the authentication type.
vi. In the Data Science Pipelines API Endpoint Password Or Token, enter the password
or token required for the authentication type.
IMPORTANT
To obtain the data science pipelines API endpoint token, in the upper-
right corner of the OpenShift web console, click your user name and
select Copy login command. After you have logged in, click Display
token and copy the value of --token= from the Log in with this token
command.
e. Define the connectivity information of your S3-compatible storage:
Red Hat OpenShift AI Cloud Service 1 Working with data science pipelines
56

i. In the Cloud Object Storage Endpoint field, enter the endpoint of your S3-compatible
storage. For more information about Amazon s3 endpoints, see Amazon Simple Storage
Service endpoints and quotas.
ii. Optional: In the Public Cloud Object Storage Endpoint field, enter the URL of your
S3-compatible storage.
iii. In the Cloud Object Storage Bucket Name field, enter the name of the bucket where
your pipeline artifacts are stored. If the bucket name does not exist, it is created
automatically.
iv. From the Cloud Object Storage Authentication Type list, select the authentication
type required to access to your S3-compatible cloud storage. If you use AWS S3
buckets, select KUBERNETES_SECRET from the list.
v. In the Cloud Object Storage Credentials Secret field, enter the secret that contains
the storage user name and password. This secret is defined in the relevant user
namespace, if applicable. In addition, it must be stored on the cluster that hosts your
pipeline runtime.
vi. In the Cloud Object Storage Username field, enter the user name to connect to your
S3-compatible cloud storage, if applicable. If you use AWS S3 buckets, enter your AWS
Secret Access Key ID.
vii. In the Cloud Object Storage Password field, enter the password to connect to your
S3-compatible cloud storage, if applicable. If you use AWS S3 buckets, enter your AWS
Secret Access Key.
f. Click Save & Close.
Verification
The runtime configuration that you created is displayed on the Runtimes tab ( 
 ) in the left
sidebar of JupyterLab.
### UPDATING A RUNTIME CONFIGURATION
To ensure that your runtime configuration is accurate and updated, you can change the settings of an
existing runtime configuration.
Prerequisites
You have logged in to Red Hat OpenShift AI.
You have access to S3-compatible storage.
You have created a data science project that contains a workbench.
You have created and configured a pipeline server within the data science project that contains
your workbench.
A previously created runtime configuration is available in the JupyterLab interface.
## WORKING WITH PIPELINES IN JUPYTERLAB
57

You have created and launched a workbench from a workbench image that contains the Elyra
extension (Standard Data Science, TensorFlow, TrustyAI, ROCm-PyTorch, ROCm-TensorFlow,
or PyTorch).
IMPORTANT
The Elyra pipeline editor is available in specific workbench images only. To use
Elyra, the workbench must be based on a JupyterLab image. The Elyra extension
is not available in code-server or RStudio IDEs. The workbench must also be
derived from the Standard Data Science image. It is not available in Minimal
Python or CUDA-based workbenches. All other supported JupyterLab-based
workbench images have access to the Elyra extension.
Procedure
1. In the left sidebar of JupyterLab, click Runtimes ( 
 ).
2. Hover the cursor over the runtime configuration that you want to update and click the Edit
button ( 
 ).
The Data Science Pipelines runtime configuration page opens.
3. Fill in the relevant fields to update your runtime configuration.
a. In the Display Name field, update name for your runtime configuration, if applicable.
b. Optional: In the Description field, update the description of your runtime configuration, if
applicable.
c. Optional: In the Tags field, click Add Tag to define a category for your pipeline instance.
Enter a name for the tag and press Enter.
d. Define the credentials of your data science pipeline:
i. In the Data Science Pipelines API Endpoint field, update the API endpoint of your
data science pipeline, if applicable. Do not specify the pipelines namespace in this field.
IMPORTANT
With Elyra, you can now use a service-based URL instead of the route-
based URL by including the port number. Using the service-based URL
allows your data science pipeline to access the service directly.
ii. In the Public Data Science Pipelines API Endpoint field, update the API endpoint of
your data science pipeline, if applicable.
iii. Optional: In the Data Science Pipelines User Namespace field, update the relevant
user namespace to run pipelines, if applicable.
iv. From the Authentication Type list, select a new authentication type required to
authenticate your pipeline, if applicable.
Red Hat OpenShift AI Cloud Service 1 Working with data science pipelines
58

IMPORTANT
If you created a workbench directly from the Start basic workbench tile
on the dashboard, select EXISTING_BEARER_TOKEN from the
Authentication Type list.
v. In the Data Science Pipelines API Endpoint Username field, update the user name
required for the authentication type, if applicable.
vi. In the Data Science Pipelines API Endpoint Password Or Token, update the password
or token required for the authentication type, if applicable.
IMPORTANT
To obtain the data science pipelines API endpoint token, in the upper-
right corner of the OpenShift web console, click your user name and
select Copy login command. After you have logged in, click Display
token and copy the value of --token= from the Log in with this token
command.
e. Define the connectivity information of your S3-compatible storage:
i. In the Cloud Object Storage Endpoint field, update the endpoint of your S3-
compatible storage, if applicable. For more information about Amazon s3 endpoints,
see Amazon Simple Storage Service endpoints and quotas .
ii. Optional: In the Public Cloud Object Storage Endpoint field, update the URL of your
S3-compatible storage, if applicable.
iii. In the Cloud Object Storage Bucket Name field, update the name of the bucket where
your pipeline artifacts are stored, if applicable. If the bucket name does not exist, it is
created automatically.
iv. From the Cloud Object Storage Authentication Type list, update the authentication
type required to access to your S3-compatible cloud storage, if applicable. If you use
AWS S3 buckets, you must select USER_CREDENTIALS from the list.
v. Optional: In the Cloud Object Storage Credentials Secret field, update the secret that
contains the storage user name and password, if applicable. This secret is defined in the
relevant user namespace. You must save the secret on the cluster that hosts your
pipeline runtime.
vi. Optional: In the Cloud Object Storage Username field, update the user name to
connect to your S3-compatible cloud storage, if applicable. If you use AWS S3 buckets,
update your AWS Secret Access Key ID.
vii. Optional: In the Cloud Object Storage Password field, update the password to connect
to your S3-compatible cloud storage, if applicable. If you use AWS S3 buckets, update
your AWS Secret Access Key.
f. Click Save & Close.
Verification
## WORKING WITH PIPELINES IN JUPYTERLAB
59

The runtime configuration that you updated is shown on the Runtimes tab ( 
 ) in the left
sidebar of JupyterLab.
### DISABLING NODE CACHING IN ELYRA
Elyra offers a feature within OpenShift AI that allows you to cache components, or "nodes," within your
data pipelines. When a pipeline component runs, Elyra stores its outputs by default. In subsequent runs,
if Elyra detects that a particular component has already been executed and its inputs have not changed,
it reuses the cached outputs instead of re-running the entire component.
For more information about data science pipelines caching, see Overview of data science pipelines
caching.
If you do not want your component output to be cached, you can disable this feature for a single node or
for all of the nodes in your pipeline.
Prerequisites
You have logged in to Red Hat OpenShift AI.
You have created a data science project.
You have created and configured a pipeline server within the data science project that contains
your workbench.
IMPORTANT
To ensure that the runtime configuration is created by default, you must create
the pipeline server before you create the workbench.
You have created a workbench with a workbench image that contains the Elyra extension
(Standard Data Science, TensorFlow, TrustyAI, ROCm-PyTorch, ROCm-TensorFlow, or
PyTorch), as described in Creating a workbench and selecting an IDE .
You have started the workbench and opened the JupyterLab interface, as described in
Accessing your workbench IDE .
IMPORTANT
The Elyra pipeline editor is available in specific workbench images only. To use
Elyra, the workbench must be based on a JupyterLab image that includes the
Elyra extension. The Elyra extension is not available in code-server or RStudio
IDEs, and it is not included in Minimal Python workbenches. Supported
JupyterLab-based images such as Standard Data Science, TensorFlow, PyTorch,
TrustyAI, and ROCm variants include the Elyra extension.
You have access to S3-compatible storage.
You have created a pipeline in JupyterLab.
Procedure
1. After you open JupyterLab, confirm that the JupyterLab launcher is automatically displayed.
Red Hat OpenShift AI Cloud Service 1 Working with data science pipelines
60

2. Open the pipeline that includes the nodes that you want to modify.
3. Right-click the node that you want to edit, and then click Open Properties.
4. To disable caching on a single node, complete the following steps:
a. Click the Node Properties tab in the slide-out menu on the right.
b. Under Additional Properties, click the option bar under Disable node caching that is
automatically populated with Use runtime default.
c. Select True.
5. To disable caching for all nodes on your pipeline, complete the following steps:
a. Click the Pipeline Properties tab in the slide-out menu on the right.
b. Under Node Defaults, click the option bar under Disable node caching that is automatically
populated with Use runtime default.
c. Select True.
Verification
To verify that caching is disabled for a single node, check that node runs are re-executed in your
target runtime environment.
To verify that caching is disabled for all nodes on your pipeline, check that your entire pipeline
runs are re-executed in your target runtime environment.
### DELETING A RUNTIME CONFIGURATION
After you have finished using your runtime configuration, you can delete it from the JupyterLab
interface. After deleting a runtime configuration, you cannot run pipelines in JupyterLab until you create
another runtime configuration.
Prerequisites
You have logged in to Red Hat OpenShift AI.
You have created a data science project that contains a workbench.
You have created and configured a pipeline server within the data science project that contains
your workbench.
A previously created runtime configuration is visible in the JupyterLab interface.
You have created and launched a workbench from a workbench image that contains the Elyra
extension (Standard Data Science, TensorFlow, TrustyAI, ROCm-PyTorch, ROCm-TensorFlow,
or PyTorch).
## WORKING WITH PIPELINES IN JUPYTERLAB
61

IMPORTANT
The Elyra pipeline editor is available in specific workbench images only. To use
Elyra, the workbench must be based on a JupyterLab image. The Elyra extension
is not available in code-server or RStudio IDEs. The workbench must also be
derived from the Standard Data Science image. It is not available in Minimal
Python or CUDA-based workbenches. All other supported JupyterLab-based
workbench images have access to the Elyra extension.
Procedure
1. In the left sidebar of JupyterLab, click Runtimes ( 
 ).
2. Hover the cursor over the runtime configuration that you want to delete and click the Delete
Item button ( 
 ).
A dialog box opens prompting you to confirm the deletion of your runtime configuration.
3. Click OK.
Verification
The runtime configuration that you deleted is no longer shown on the Runtimes tab ( 
 ) in
the left sidebar of JupyterLab.
### DUPLICATING A RUNTIME CONFIGURATION
To prevent you from re-creating runtime configurations with similar values in their entirety, you can
duplicate an existing runtime configuration in the JupyterLab interface.
Prerequisites
You have logged in to Red Hat OpenShift AI.
You have created a data science project that contains a workbench.
You have created and configured a pipeline server within the data science project that contains
your workbench.
A previously created runtime configuration is visible in the JupyterLab interface.
You have created and launched a workbench from a workbench image that contains the Elyra
extension (Standard Data Science, TensorFlow, TrustyAI, ROCm-PyTorch, ROCm-TensorFlow,
or PyTorch).
IMPORTANT
The Elyra pipeline editor is available in specific workbench images only. To use
Elyra, the workbench must be based on a JupyterLab image. The Elyra extension
is not available in code-server or RStudio IDEs. The workbench must also be
derived from the Standard Data Science image. It is not available in Minimal
Python or CUDA-based workbenches. All other supported JupyterLab-based
workbench images have access to the Elyra extension.
Red Hat OpenShift AI Cloud Service 1 Working with data science pipelines
62

Procedure
1. In the left sidebar of JupyterLab, click Runtimes ( 
 ).
2. Hover the cursor over the runtime configuration that you want to duplicate and click the
Duplicate button ( 
 ).
Verification
The runtime configuration that you duplicated is shown on the Runtimes tab ( 
 ) in the left
sidebar of JupyterLab.
### RUNNING A PIPELINE IN JUPYTERLAB
You can run pipelines that you have created in JupyterLab from the Pipeline Editor user interface.
Before you can run a pipeline, you must create a data science project and a pipeline server. After you
create a pipeline server, you must create a workbench within the same project as your pipeline server.
Your pipeline instance in JupyterLab must contain a runtime configuration. If you create a workbench as
part of a data science project, a default runtime configuration is created automatically. However, if you
create a workbench from the Start basic workbench tile in the OpenShift AI dashboard, you must
create a runtime configuration before you can run your pipeline in JupyterLab. A runtime configuration
defines connectivity information for your pipeline instance and S3-compatible cloud storage.
Prerequisites
You have logged in to Red Hat OpenShift AI.
You have access to S3-compatible storage.
You have created a pipeline in JupyterLab.
You have opened your pipeline in the Pipeline Editor in JupyterLab.
Your pipeline instance contains a runtime configuration.
You have created and configured a pipeline server within the data science project that contains
your workbench.
You have created and launched a workbench from a workbench image that contains the Elyra
extension (Standard Data Science, TensorFlow, TrustyAI, ROCm-PyTorch, ROCm-TensorFlow,
or PyTorch).
IMPORTANT
The Elyra pipeline editor is available in specific workbench images only. To use
Elyra, the workbench must be based on a JupyterLab image. The Elyra extension
is not available in code-server or RStudio IDEs. The workbench must also be
derived from the Standard Data Science image. It is not available in Minimal
Python or CUDA-based workbenches. All other supported JupyterLab-based
workbench images have access to the Elyra extension.
Procedure
## WORKING WITH PIPELINES IN JUPYTERLAB
63

1. In the Pipeline Editor user interface, click Run Pipeline ( 
 ).
The Run Pipeline dialog opens. The Pipeline Name field is automatically populated with the
pipeline file name.
NOTE
After you run your pipeline, a pipeline experiment containing your pipeline run is
automatically created on the Experiments → Experiments and runs page in the
OpenShift AI dashboard. The experiment name matches the name that you
assigned to the pipeline.
2. Define the settings for your pipeline run.
a. From the Runtime Configuration list, select the relevant runtime configuration to run your
pipeline.
b. Optional: Configure your pipeline parameters, if applicable. If your pipeline contains nodes
that reference pipeline parameters, you can change the default parameter values. If a
parameter is required and has no default value, you must enter a value.
3. Click OK.
Verification
You can view the details of your pipeline run on the Experiments → Experiments and runs
page in the OpenShift AI dashboard.
You can view the output artifacts of your pipeline run. The artifacts are stored in your
designated object storage bucket.
### EXPORTING A PIPELINE IN JUPYTERLAB
You can export pipelines that you have created in JupyterLab. When you export a pipeline, the pipeline
is prepared for later execution, but is not uploaded or executed immediately. During the export process,
any package dependencies are uploaded to S3-compatible storage. Also, pipeline code is generated for
the target runtime.
Before you can export a pipeline, you must create a data science project and a pipeline server. After you
create a pipeline server, you must create a workbench within the same project as your pipeline server. In
addition, your pipeline instance in JupyterLab must contain a runtime configuration. If you create a
workbench as part of a data science project, a default runtime configuration is created automatically.
However, if you create a workbench from the Start basic workbench tile in the OpenShift AI dashboard,
you must create a runtime configuration before you can export your pipeline in JupyterLab. A runtime
configuration defines connectivity information for your pipeline instance and S3-compatible cloud
storage.
Prerequisites
You have logged in to Red Hat OpenShift AI.
You have created a data science project that contains a workbench.
You have created and configured a pipeline server within the data science project that contains
your workbench.
Red Hat OpenShift AI Cloud Service 1 Working with data science pipelines
64

You have access to S3-compatible storage.
You have a created a pipeline in JupyterLab.
You have opened your pipeline in the Pipeline Editor in JupyterLab.
Your pipeline instance contains a runtime configuration.
You have created and launched a workbench from a workbench image that contains the Elyra
extension (Standard Data Science, TensorFlow, TrustyAI, ROCm-PyTorch, ROCm-TensorFlow,
or PyTorch).
IMPORTANT
The Elyra pipeline editor is available in specific workbench images only. To use
Elyra, the workbench must be based on a JupyterLab image. The Elyra extension
is not available in code-server or RStudio IDEs. The workbench must also be
derived from the Standard Data Science image. It is not available in Minimal
Python or CUDA-based workbenches. All other supported JupyterLab-based
workbench images have access to the Elyra extension.
Procedure
1. In the Pipeline Editor user interface, click Export Pipeline ( 
 ).
The Export Pipeline dialog opens. The Pipeline Name field is automatically populated with the
pipeline file name.
2. Define the settings to export your pipeline.
a. From the Runtime Configuration list, select the relevant runtime configuration to export
your pipeline.
b. From the Export Pipeline as select an appropriate file format
c. In the Export Filename field, enter a file name for the exported pipeline.
d. Select the Replace if file already exists check box to replace an existing file of the same
name as the pipeline you are exporting.
e. Optional: Configure your pipeline parameters, if applicable. If your pipeline contains nodes
that reference pipeline parameters, you can change the default parameter values. If a
parameter is required and has no default value, you must enter a value.
3. Click OK.
Verification
You can view the file containing the pipeline that you exported in your designated object
storage bucket.
## WORKING WITH PIPELINES IN JUPYTERLAB
65
## TROUBLESHOOTING DSPA COMPONENT
ERRORS
This table displays common errors found in DataSciencePipelinesApplication (DSPA) components,
along with the associated status, message, and proposed solution. The Ready condition type
accumulates errors from various DSPA components, providing a status view of the DSPA deployment.
Type
Status
Error message and solution
ObjectStorageAvailable
Ready
False
False
Error message: Could not connect to Object Store: tls: 
failed to verify certificate: x509: certificate signed by 
unknown authority
Solution: This issue occurs in clusters that use self-signed
certificates with OpenShift AI version 2.9 or later. The data
science pipelines manager cannot connect to the object storage
because it does not trust the object storage SSL certificate.
Therefore, the pipeline server cannot be created. Contact your
IT operations administrator to add the relevant Certificate
Authority bundle.
For more information, see Working with certificates.
ObjectStorageAvailable
Ready
False
False
Error message: Could not connect to Object Store 
Deployment for component "ds-pipeline-pipelines-
definition" is missing - prerequisite component might 
not yet be available. Deployment for component "ds-
pipeline-persistenceagent-pipelines-definition" is 
missing - prerequisite component might not yet be 
available. Deployment for component "ds-pipeline-
scheduledworkflow-pipelines-definition" is missing - 
prerequisite component might not yet be available.
Solution: In clusters running OpenShift AI 2.8.x, the data science
pipelines manager might fail to connect to the object storage,
and the pipeline server might not be created.
Ensure that your object store credentials and connection
information are accurate, and verify that the object store is
accessible from within the data science project’s associated
OpenShift namespace. One common issue is that the object
storage SSL certificate is not trusted, particularly if self-signed
certificates are used.
Verify and update your object storage credentials, then retry
the operation.
Red Hat OpenShift AI Cloud Service 1 Working with data science pipelines
66

ObjectStorageAvailable
Ready
False
False
Error message: Wrong credentials for Object Storage: 
Could not connect to (minio-my-project.apps.my-
cluster.com), Error: The request signature we 
calculated does not match the signature you provided. 
Check your key and signing method.
Solution: Provide the correct credentials for your object storage
and retry the operation.
DatabaseAvailable
Ready
False
False
Error message: FailingToDeploy: Dial tcp 
XXX.XX.XXX.XXX:3306 : i/o timeout
Solution: If the issue persists beyond startup, check for network
issues or misconfigurations in the database connection settings.
DatabaseAvailable
Ready
False
False
Error message: Unable to connect to external database: 
tls: failed to verify certificate: x509: certificate signed 
by unknown authority
Solution: This issue can occur when you use any external
database, such as Amazon RDS. The data science pipelines
manager cannot connect to the database because it does not
trust the database SSL certificate, preventing the creation of
the pipeline server. Contact your IT operations administrator to
add the relevant certificates.
For more information, see Working with certificates.
DatabaseAvailable
Ready
False
False
Error message: Error 1129: Host 'A.B.C.D' is blocked 
because of many connection errors.
Solution: This issue might occur when using an external
database, such as Amazon RDS. Initially, the pipeline server is
created successfully. However, after some time, the OpenShift
AI dashboard displays an "Error displaying pipelines" message,
and the DSPA conditions indicate that the host is blocked due
to multiple connection errors.
For more information on how resolve this issue for an external
Amazon RDS database, see Resolving "Host is blocked because
of many connection errors" error in Amazon RDS for MySQL.
Note: Clicking this link opens an external website.
APIServerReady
Ready
False
False
Error message: Route creation failed due to lengthy 
project name: Route.route.openshift.io is invalid: 
spec.host exceeds 63 characters.
Solution: Ensure that the project name in OpenShift is less than
40 characters.
Type
Status
Error message and solution
## TROUBLESHOOTING DSPA COMPONENT ERRORS
67

APIServerReady
Ready
False
False
Error message: FailingToDeploy: Component replica 
failed to create. Message: serviceaccount "ds-pipeline-
sample" not found.
Solution: If the failure persists for more than 25 seconds during
DSPA startup, recreate the missing service account.
PersistenceAgentReady
Ready
False
False
Error message: FailingToDeploy: Component's replica 
failed to create. Message: serviceaccount "ds-pipeline-
persistenceagent-sample" not found.
Solution: If the failure persists for more than 25 seconds during
DSPA startup, recreate the missing service account.
ScheduledWorkflowRead
y
Ready
False
False
Error message: FailingToDeploy: Component's replica 
failed to create. Message: serviceaccount "ds-pipeline-
scheduledworkflow-sample" not found.
Solution: If the failure persists for more than 25 seconds during
DSPA startup, recreate the missing service account.
MLMDProxyReady
Ready
False
False
Error message: Deploying: Component [ds-pipeline-
scheduledworkflow-sample] is still deploying.
Solution: Wait for DSPA startup to complete. If deployment fails
after 25 seconds, check the logs for further information.
Type
Status
Error message and solution
### COMMON ERRORS ACROSS DSP COMPONENTS
The following table lists errors that might occur across multiple DSPA components:
Deployment condition and
condition type
Stat
us
Error message and solution
Condition: Component
Deployment Not Found
Condition type: 
ComponentDeploymentNot
Found
False
Error message: Deployment for component <component> 
is missing - prerequisite component might not yet be 
available.
Solution: The deployment for the component does not exist.
Typically, this issue occurs due to missing deployments or issues
that occurred during creation.
Condition: Deployment Scaled
Down
Condition type: 
MinimumReplicasAvailable
False
Error message: Deployment for component <component> 
is scaled down.
Solution: The component is unavailable as the deployment
replica count is set to zero.
Red Hat OpenShift AI Cloud Service 1 Working with data science pipelines
68

Condition: Component Failing to
Progress
Condition type: 
FailingToDeploy
False
Error message: Component <component> has failed to 
progress. Reason: <progressingCond.Reason>. 
Message: <progressingCond.Message>
Solution: The deployment has stalled due to 
ProgressDeadlineExceeded or ReplicaSetCreateError
issues, or similar.
Condition: Replica Creation
Failure
Condition type: 
FailingToDeploy
False
Error message: Component's replica <component> has 
failed to create. Reason: <replicaFailureCond.Reason>. 
Message: <replicaFailureCond.Message>
Solution: Replica creation has failed, typically due to an error in
the replica set or with the service accounts.
Condition: Pod-Level Failures
Condition type: 
FailingToDeploy
False
Error message: Concatenated failure messages for each 
pod.
Solution: Deployment pods are in a failed state. Check the pod
logs for further information.
Condition: Pod in
CrashLoopBackOff
Condition type: 
FailingToDeploy
False
Error message: Component <component> is in 
CrashLoopBackOff. Message from pod: 
<crashLoopBackOffMessage>
Solution: Pod containers are failing repeatedly, often due to
incorrect environment variables or missing service accounts.
Condition: Component
Deploying (No Errors)
Condition: type: Deploying
False
Error message: Component <component> is deploying.
Solution: The component deployment process is ongoing with
no errors detected.
Condition: Component Minimally
Available
Condition type: 
MinimumReplicasAvailable
True
Error message: Component <component> is minimally 
available.
Solution: The component is available, but with only the minimum
number of replicas running.
Deployment condition and
condition type
Stat
us
Error message and solution
## TROUBLESHOOTING DSPA COMPONENT ERRORS
69
## ADDITIONAL RESOURCES
Kubeflow Pipelines 2.0 Documentation
Red Hat OpenShift AI Cloud Service 1 Working with data science pipelines
70