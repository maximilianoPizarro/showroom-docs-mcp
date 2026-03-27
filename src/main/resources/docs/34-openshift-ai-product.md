# Red Hat OpenShift AI

_Source: [developers.redhat.com](https://developers.redhat.com/products/red-hat-openshift-ai/overview)_  
_Note: The path `https://developers.redhat.com/products/openshift-ai/overview` returned 404; this page reflects the current product URL above._

## Overview

Red Hat OpenShift AI is an AI/ML platform that runs on **Red Hat OpenShift** and supports the full model lifecycle: develop, train, serve, and monitor—on-site, in public cloud, or at the edge.

Data scientists and developers share a consistent environment to move from experiment to production quickly. The product bundles popular open source tooling (e.g. Jupyter, TensorFlow, PyTorch) with MLOps capabilities (model serving, pipelines, monitoring) behind a unified UI.

**Availability:** Add-on on [Red Hat OpenShift Service on AWS (ROSA)](https://developers.redhat.com/products/red-hat-openshift-service-on-aws/overview) or [OpenShift Dedicated](https://developers.redhat.com/products/openshift/openshift-public-cloud), or as **self-managed** software.

## Features

### Red Hat AI Inference Server

Included with OpenShift AI (and RHEL AI); runs on OpenShift and RHEL. Uses a **vLLM** runtime for throughput and latency; includes a pre-optimized model repository and **LLM compressor** to reduce cost while preserving accuracy.

### Accelerate data science

- **Flexibility** — Managed cloud service or self-managed software; same trusted environment on-prem, cloud, or edge
- **Operationalize models** — Model serving, data science pipelines, monitoring; DevOps-style practices on OpenShift
- **Partners** — Validated integrations (e.g. Starburst, IBM Watson, Intel, Pachyderm) in the UI; broader partner catalog on OpenShift

### Open source foundations

- **Open Data Hub** — Meta-project integrating many AI/ML projects; OpenShift AI is a supported subset as product or service
- **Jupyter** — Interactive notebooks and data science workflows
- **TensorFlow** / **PyTorch** — Training and deployment ecosystems
- **Scikit-learn** — Classical ML on NumPy/SciPy/Matplotlib
- **Kubeflow** — Notebook controller, model serving, pipeline components integrated into the product

## Getting started

### Quick-start: install OpenShift AI operator

```bash
# Install the operator from OperatorHub
oc apply -f - <<EOF
apiVersion: operators.coreos.com/v1alpha1
kind: Subscription
metadata:
  name: rhods-operator
  namespace: redhat-ods-operator
spec:
  channel: stable
  name: rhods-operator
  source: redhat-operators
  sourceNamespace: openshift-marketplace
EOF
```

### Quick-start: create a Data Science Project and serve a model

```bash
# Create a data science project
oc new-project my-ml-project

# Deploy a model using the InferenceService CRD
oc apply -f - <<EOF
apiVersion: serving.kserve.io/v1beta1
kind: InferenceService
metadata:
  name: my-model
  namespace: my-ml-project
  annotations:
    serving.kserve.io/deploymentMode: ModelMesh
spec:
  predictor:
    model:
      modelFormat:
        name: sklearn
      storage:
        key: my-storage
        path: sklearn/mnist-svm.joblib
EOF

# Check model readiness
oc get inferenceservice my-model -n my-ml-project
```

### Quick-start: launch JupyterHub notebook

1. Open the OpenShift AI dashboard (available via Routes in `redhat-ods-applications` namespace)
2. Click **Launch application** under JupyterHub
3. Select a notebook image (e.g., Standard Data Science, PyTorch, TensorFlow)
4. Choose a container size and click **Start server**

### Links

- [Try in Developer Sandbox](https://developers.redhat.com/developer-sandbox)
- [Overview](https://developers.redhat.com/products/red-hat-openshift-ai)
- [Download / sign-up](https://developers.redhat.com/products/red-hat-openshift-ai/download)
- [Getting started](https://developers.redhat.com/products/red-hat-openshift-ai/getting-started)

## Technical details

OpenShift AI packages Kubernetes-native AI/ML with Red Hat support and lifecycle. For the broader OpenShift context, see [Red Hat OpenShift](https://developers.redhat.com/products/openshift/overview).
