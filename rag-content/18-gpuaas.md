# Introduction

## Create a workbench

Login to OpenShift AI and select the `ai-roadshow` data science project.

![images/create-workbench.png](images/create-workbench.png)

We are going to `Create a workbench` using the following parameters:

 Name: gpuaas
 Image Selection: CUDA
 Version: 2025.1 (select the latest version)

For this workbench we need to use storage than can be used in an AWS ZONE that is not the same as the SNO master node.

By default the `lvms-vgsno` storage class is only useable on the Single Node Master (SNO).

`gp3` storage is still not shareable across AZs but we at least we can provision our GPU node in another AZ if there are resource shortages.

Create a Persistent Volume Claim for our new Notebook using the `gp3-csi` cloud storage class.

Paste the following YAML into the OpenShift Console to create the PVC for our workbench - use the **+** sign up on the top right on the OpenShift we console (quick create).

```yaml
apiVersion: v1
kind: PersistentVolumeClaim
metadata:
  annotations:
    openshift.io/display-name: gpuaas-storage
  labels:
    opendatahub.io/dashboard: "true"
  name: gpuaas-storage
  namespace: ai-roadshow
spec:
  accessModes:
  - ReadWriteOnce
  resources:
    requests:
      storage: "40Gi"
  storageClassName: gp3-csi
  volumeMode: Filesystem
```

You need to `Detach` the automatic `gpuaas-storage` storage, then - Select `Attach existing storage` using the PVC we created above when creating the workbench.

![images/attach-existing-storage.png](images/attach-existing-storage.png)

Add the following `Environment Variables` to your workbench.

 ADMIN_PASSWORD: your OpenShift admin password
 BASE_DOMAIN: your OpenShift lab base domain e.g sandbox.opentlc.com

![images/workbench-env-vars.png](images/workbench-env-vars.png)

Leave all the other fields as defaults. You should see the `Hardware profile` auto-selected to use the GPU Accelerator `Nvidida L4 (Shared)`.

![images/cuda-workbench.png](images/cuda-workbench.png)

Select `Create workbench`. It may take a few minutes to pull the workbench image for the first time.

Open and login to your `gpuaas` workbench (click the arrow in a box next to `gpuaas`)

![images/open-cuda-workbench.png](images/open-cuda-workbench.png)

---

# Adding a GPU worker node to a Red Hat OpenShift cluster on AWS

Clone the code using your github user into your workbench by using the `Terminal` and entering:

```bash
git clone https://github.com/odh-labs/rhoai-roadshow-v2
```

![images/clone-code.png](images/clone-code.png)

Go into the `rhoai-roadshow-v2` folder and open the top level `6-gpuaas.jupyterlab-workspace` workspace.

Open up the following notebook in your workspace.

 Level1_add_gpu_node.ipynb 

We will cover scaling our GPUs in OpenShift.

![images/level1-add-gpu-node-cluster.png](images/level1-add-gpu-node-cluster.png)

---

# Configure new GPUs

Clone the code using your github user into your workbench by using the `Terminal` and entering:

```bash
git clone https://github.com/odh-labs/rhoai-roadshow-v2
```

![images/clone-code.png](images/clone-code.png)

Go into the `rhoai-roadshow-v2` folder and open the top level `6-gpuaas.jupyterlab-workspace` workspace.

Open up the following notebook in your workspace.

 Level2_gpu_operator.ipynb 

We will cover configuring and scaling our new GPUs in OpenShift.

![images/level2-gpu-operator.png](images/level2-gpu-operator.png)

---

# Deploy a new GPU workload

Clone the code using your github user into your workbench by using the `Terminal` and entering:

```bash
git clone https://github.com/odh-labs/rhoai-roadshow-v2
```

![images/clone-code.png](images/clone-code.png)

Go into the `rhoai-roadshow-v2` folder and open the top level `6-gpuaas.jupyterlab-workspace` workspace.

Open up the following notebook in your workspace.

 Level3_new_gpu_workload.ipynb 

We will cover how we can deploy and isolate new workloads using our new GPU node.

![images/level3-new-gpu-workload.png](images/level3-new-gpu-workload.png)

---

# Advanced GPUaaS with Kueue

Clone the code using your github user into your workbench by using the `Terminal` and entering:

```bash
git clone https://github.com/odh-labs/rhoai-roadshow-v2
```

![images/clone-code.png](images/clone-code.png)

Go into the `rhoai-roadshow-v2` folder and open the top level `6-gpuaas.jupyterlab-workspace` workspace.

Open up the following notebook in your workspace.

 Level4_advanced_gpuaas.ipynb 

We will cover how we can deploy workloads using Kueue/Ray/Cloudflare.

![images/level4-advanced-gpuaas.png](images/level4-advanced-gpuaas.png)

👷‍♂️ Work In Progress 👷‍♂️

The distributed workloads using Kueue is under active development.

The future of GPUaaS within OpenShift is using Kueue available for all workloads across GPU enabled nodes.

For now we are using the current state notebooks that are part of the product.

![images/ocp-gpu-multitenant.png](images/ocp-gpu-multitenant.png)

---

# Advanced GPUaaS - Inference with Multi GPU + Multi Node

Clone the code using your github user into your workbench by using the `Terminal` and entering:

```bash
git clone https://github.com/odh-labs/rhoai-roadshow-v2
```

![images/clone-code.png](images/clone-code.png)

Go into the `rhoai-roadshow-v2` folder and open the top level `6-gpuaas.jupyterlab-workspace` workspace.

Open up the following notebook in your workspace.

 Level5_multi-gpu-node.ipynb 

We will cover how we can deploy larger LLM workloads using multiple GPUs and multiple nodes together.

![images/level4-advanced-gpuaas.png](images/level4-advanced-gpuaas.png)
