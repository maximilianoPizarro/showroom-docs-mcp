# Working with an LLM

You will have used an LLM like Chat GPT before. In this module you will explore how to work with LLMs programmatically.

Agenda:

* [🚀 Introduction](1-llm/0-intro.md)
* [👟 Getting started - configure the lab workbench](1-llm/1-getting-started.md)
* [☑️ Validate the lab environment](1-llm/2-validate-environment.md)
* [🗣️ Notebook-based LLM example](1-llm/3-llm-example.md)
* [💬 Text summarisation](1-llm/4-summarisation.md)
* [📃 Information extraction](1-llm/5-information-extraction.md)

---

# 💁 Introduction

In this exercise we will configure the workbench that you will use for the lab. Follow the steps described in this document to configure the lab environment.

## Login to OpenShift AI

Your instructor will supply you with a link that you will use to login to your OpenShift AI cluster.

1. Open the OpenShift AI console using the url provided by your instructor.

2. Use the following credentials to login to OpenShift AI: 
 **Username:** *admin* 
 **Password:** *(Your instructor will supply your passowrd on the day.)*

## Create a workbench

1. Select the `ai-roadshow` data science project.

 ![images/create-workbench-0.png](images/create-workbench-0.png)

2. Enter the following details into the **Create workbench** form:

 Name: **llm-workbench** 
 Image Selection: **CUDA** 
 Version: **2025.1 (Recommended)** 
 Hardware profile: **NVIDIA L4 (Shared)** 

 ![images/create-workbench-1.png](images/create-workbench-1.png)

3. Click **Create workbench**

 OpenShift AI creates and starts the workbench.

 ![images/create-workbench-2.png](images/create-workbench-2.png)

 Wait for the status to change to *Running*. 

 The workbench has now been created. You will now open the workbench, which will launch **JupyterLab**, your IDE for the lab.

## Open the Jupyter notebook

1. Click **llm-workbench** to open **JupyterLab**.

 In the login dialog box, enter the same credentials you used to log into OpenShift at the start of this lab.

2. Click **Login**

 OpenShift AI launches JupyterLab. 

 With JupyterLab now running, you will now download all of the lab materials.

3. Click the **Git** button in the toolbar on the left side of JupyterLab. 

 ![./images/jupyter-lab.png](images/jupyter-lab.png)

4. Click **Clone a Repository**

 OpenShift AI prompts you to enter the repository URL and other options. 

5. Copy and paste the following into the *URI of the remote Git repository* text box: `https://github.com/odh-labs/rhoai-roadshow-v2.git`

6. Click **Include submodules**.

 ![images/clone-git-repo-2.png](images/clone-git-repo-2.png) 

7. Click **Clone**.

 JupyterLab copies the source code from GitHub into your Workspace.

 ![images/clone-git-repo-3.png](images/clone-git-repo-3.png)

8. In the *File Explorer* panel, navigate into the directory: `/rhoai-roadshow-v2` 

 ![images/clone-git-repo-4.png](images/clone-git-repo-4.png) 

9. Double click `1-llm.jupyterlab-workspace` to open the workspace for this activity.

 JupyterLab opens the workspace. All of the notebooks you will use are visible in the *File Explorer*. 

 ![images/clone-git-repo-5.png](images/clone-git-repo-5.png) 

When done, you have successfully connected to your environment and this completes this activity. Click the link below to move to the next activity 'Validate the lab environment'.

---

# Validate the Lab Environment

Now that you are connected to your workbench, you will validate that the lab environment has been properly configured with everything that is needed to successfully run the lab.

From the `rhoai-roadshow-v2/lab-materials/1-llm` folder, please open the notebook called: 2-validate-environment.ipynb 

Follow all instructions from inside the Notebook. If your environment has been provisioned correctly then you will see a result like the following image:

![images/validate-env-1.png](images/validate-env-1.png)

When done, you can close the notebook and head to the next activity by clicking the link below.

---

# Notebook-Based LLM Example

In this exercise, we will use a notebook to investigate how LLMs can be used.

From the `rhoai-roadshow-v2/lab-materials/1-llm` folder, please open the notebook called: 3-nb-llm-example.ipynb 

When done, you can close the notebook and head to the next page.

---

# Text Summarisation

As part of our claim processing, we want to summarize the text of a claim. In the next notebook we will investigate how the LLM can be used to do this task.

From the `rhoai-roadshow-v2/lab-materials/1-llm` folder, please open the notebook called: 4-summarisation.ipynb 

When done, you can close the notebook and head to the next page.

---

# Information Extraction

As part of our claim processing, we want to extract some information from the text of a claim. In the next notebook we will investigate how the LLM can be used to do this task.

From the `rhoai-roadshow-v2/lab-materials/1-llm` folder, please open the notebook called: 5-information-extraction.ipynb 


When done, you can close the notebook and head to the next page.
