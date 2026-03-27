# Red Hat OpenShift Pipelines 1.21

_Compiled from Red Hat Documentation (html-single), March 2026._



## About OpenShift Pipelines

About OpenShift Pipelines | Red Hat OpenShift Pipelines | 1.21 | Red Hat Documentation

 

 

 

 

 

 

 

 

# About OpenShift Pipelines

---

Red Hat OpenShift Pipelines 1.21

## Introduction to OpenShift Pipelines

Red Hat OpenShift Documentation Team

Legal Notice

Abstract

This document provides an overview of OpenShift Pipelines features.

---

## Chapter 1. About Red Hat OpenShift PipelinesCopy link

Red Hat OpenShift Pipelines is a cloud-native, continuous integration and continuous delivery (CI/CD) solution based on Kubernetes resources. It uses Tekton building blocks to automate deployments across multiple platforms by abstracting away the underlying implementation details. Tekton introduces a number of standard custom resource definitions (CRDs) for defining CI/CD pipelines that are portable across Kubernetes distributions.

Note

Because Red Hat OpenShift Pipelines releases on a different cadence from OpenShift Container Platform, the Red Hat OpenShift Pipelines documentation is now available as separate documentation sets for each minor version of the product.

The Red Hat OpenShift Pipelines documentation is available at [OpenShift Pipelines documentation](https://docs.openshift.com/pipelines/).

You can access documentation for specific versions using the version selector drop-down list, or directly by adding the version to the URL, for example, [OpenShift Pipelines version 1.21 documentation](https://docs.openshift.com/pipelines/1.21).

In addition, the Red Hat OpenShift Pipelines documentation is also available on the Red Hat Customer Portal at [https://access.redhat.com/documentation/en-us/red_hat_openshift_pipelines/](https://access.redhat.com/documentation/en-us/red_hat_openshift_pipelines/).

For additional information about the Red Hat OpenShift Pipelines life cycle and supported platforms, refer to the [Platform Life Cycle Policy](https://access.redhat.com/support/policy/updates/openshift#pipelines).

## Chapter 2. Understanding OpenShift PipelinesCopy link

Red Hat OpenShift Pipelines is a cloud-native, continuous integration and continuous delivery (CI/CD) solution based on Kubernetes resources. It uses Tekton building blocks to automate deployments across multiple platforms by abstracting away the underlying implementation details. Tekton introduces a number of standard custom resource definitions (CRDs) for defining CI/CD pipelines that are portable across Kubernetes distributions.

### 2.1. Key featuresCopy link

- Legal Notice
- Red Hat OpenShift Pipelines is a serverless CI/CD system that runs pipelines with all the required dependencies in isolated containers.
- Red Hat OpenShift Pipelines are designed for decentralized teams that work on microservice-based architecture.
- Red Hat OpenShift Pipelines use standard CI/CD pipeline definitions that are easy to extend and integrate with the existing Kubernetes tools, enabling you to scale on-demand.
- You can use Red Hat OpenShift Pipelines to build images with Kubernetes tools such as Source-to-Image (S2I), Buildah, Buildpacks, and Kaniko that are portable across any Kubernetes platform.
- You can use the OpenShift Container Platform Developer console to create Tekton resources, view logs of pipeline runs, and manage pipelines in your OpenShift Container Platform namespaces.

### 2.2. OpenShift Pipelines ConceptsCopy link

This guide provides a detailed view of the various pipeline concepts.

#### 2.2.1. TasksCopy link

You can use`Task` resources as the building blocks of a pipeline to define a set of sequentially executed steps. Each task functions as a reusable unit of work with specific inputs and outputs, capable of running individually or as part of a larger pipeline.

`Task` resources are the building blocks of a pipeline and consist of sequentially executed steps. It is essentially a function of inputs and outputs. A task can run individually or as a part of the pipeline. You can reuse tasks in many pipelines.

Steps are a series of commands that are sequentially executed by the task and achieve a specific goal, such as building an image. Every task runs as a pod, and each step runs as a container within that pod. Because steps run within the same pod, they can access the same volumes for caching files, config maps, and secrets.

The following example shows the`apply-manifests` task.

```yaml
apiVersion: tekton.dev/v1
kind: Task
metadata:
  name: apply-manifests
spec:
  workspaces:
  - name: source
  params:
    - name: manifest_dir
      description: The directory in source that contains yaml manifests
      type: string
      default: "k8s"
  steps:
    - name: apply
      image: image-registry.openshift-image-registry.svc:5000/openshift/cli:latest
      workingDir: /workspace/source
      command: ["/bin/bash", "-c"]
      args:
        - |-
          echo Applying manifests in $(params.manifest_dir) directory
          oc apply -f $(params.manifest_dir)
          echo -----------------------------------
```

`apiVersion` The task API version,`v1`.`kind` The type of Kubernetes object,`Task`.`metadata.name` The unique name of this task.`spec` The list of parameters and steps in the task and the workspace used by the task.

This task starts the pod and runs a container inside that pod by using the specified image to run the specified commands.

Note

Starting with OpenShift Pipelines 1.6, the step YAML file no longer has the following defaults:

- The`HOME` environment variable does not default to the`/tekton/home` directory
- The`workingDir` field does not default to the`/workspace` directory

Instead, the container for the step defines the`HOME` environment variable and the`workingDir` field. However, you can override the default values by specifying the custom values in the YAML file for the step.

As a temporary measure, to maintain backward compatibility with the older OpenShift Pipelines versions, you can set the following fields in the`TektonConfig` custom resource definition to`false`:

```plaintext
spec:
  pipeline:
    disable-working-directory-overwrite: false
    disable-home-env-overwrite: false
```

#### 2.2.2. When expressionCopy link

You can use 'when' expressions to guard task execution by defining specific criteria that must be met before a task runs. These expressions allow you to control the flow of your pipeline, including the execution of tasks in the`finally` section, based on static inputs, variables, or results from previous tasks.

When expressions guard task execution by setting criteria for running tasks within a pipeline. They contain a list of components that allow a task to run only when certain criteria are met. You can also include when expressions in the final set of tasks that you specify by using the`finally` field in the pipeline YAML file.

The key components of a when expression are as follows:

- `input`: Specifies static inputs or variables such as a parameter, task result, and execution status. You must enter a valid input. If you do not enter a valid input, its value defaults to an empty string.
- `operator`: Specifies the relationship of an input to a set of`values`. Enter`in` or`notin` as your operator values.
- `values`: Specifies an array of string values. Enter a non-empty array of static values or variables such as parameters, results, and a bound state of a workspace.

The declared when expressions evaluate before the task runs. If the when expression evaluates to`True`, the task runs. If the when expression evaluates to`False`, the task skips.

You can use the when expressions in various use cases. For example, whether:

- The result of a preceding task is as expected.
- A file in a Git repository has changed in the earlier commits.
- An image exists in the registry.
- An optional workspace is available.

The following example shows the when expressions for a pipeline run. The pipeline run will run the`create-file` task only if the following criteria are met: the`path` parameter is`README.md`, and the`echo-file-exists` task runs only if the`exists` result from the`check-file` task is`yes`.

```yaml
apiVersion: tekton.dev/v1
kind: PipelineRun
metadata:
  generateName: guarded-pr-
spec:
  taskRunTemplate:
    serviceAccountName: pipeline
  pipelineSpec:
    params:
      - name: path
        type: string
        description: The path of the file to be created
    workspaces:
      - name: source
        description: |
          This workspace is shared among all the pipeline tasks to read/write common resources
    tasks:
      - name: create-file
        when:
          - input: "$(params.path)"
            operator: in
            values: ["README.md"]
        workspaces:
          - name: source
            workspace: source
        taskSpec:
          workspaces:
            - name: source
              description: The workspace to create the readme file in
          steps:
            - name: write-new-stuff
              image: ubuntu
              script: 'touch $(workspaces.source.path)/README.md'
      - name: check-file
        params:
          - name: path
            value: "$(params.path)"
        workspaces:
          - name: source
            workspace: source
        runAfter:
          - create-file
        taskSpec:
          params:
            - name: path
          workspaces:
            - name: source
              description: The workspace to check for the file
          results:
            - name: exists
              description: indicates whether the file exists or is missing
          steps:
            - name: check-file
              image: alpine
              script: |
                if test -f $(workspaces.source.path)/$(params.path); then
                  printf yes | tee /tekton/results/exists
                else
                  printf no | tee /tekton/results/exists
                fi
      - name: echo-file-exists
        when:
          - input: "$(tasks.check-file.results.exists)"
            operator: in
            values: ["yes"]
        taskSpec:
          steps:
            - name: echo
              image: ubuntu
              script: 'echo file exists'
...
      - name: task-should-be-skipped-1
        when:
          - input: "$(params.path)"
            operator: notin
            values: ["README.md"]
        taskSpec:
          steps:
            - name: echo
              image: ubuntu
              script: exit 1
...
    finally:
      - name: finally-task-should-be-executed
        when:
          - input: "$(tasks.echo-file-exists.status)"
            operator: in
            values: ["Succeeded"]
          - input: "$(tasks.status)"
            operator: in
            values: ["Succeeded"]
          - input: "$(tasks.check-file.results.exists)"
            operator: in
            values: ["yes"]
          - input: "$(params.path)"
            operator: in
            values: ["README.md"]
        taskSpec:
          steps:
            - name: echo
              image: ubuntu
              script: 'echo finally done'
  params:
    - name: path
      value: README.md
  workspaces:
    - name: source
      volumeClaimTemplate:
        spec:
          accessModes:
            - ReadWriteOnce
          resources:
            requests:
              storage: 16Mi
```

`kind` Specifies the type of Kubernetes object. In this example,`PipelineRun`.`tasks[0].name` Task`create-file` used in the pipeline.`tasks[2].name.when` The`when` expression that specifies to run the`echo-file-exists` task only if the`exists` result from the`check-file` task is`yes`.`tasks[3].name.when` The`when` expression that specifies to skip the`task-should-be-skipped-1` task only if the`path` parameter is`README.md`.`tasks[4].name.when` The`when` expression that specifies to run the`finally-task-should-be-executed` task only if the execution status of the`echo-file-exists` task and the task status is`Succeeded`, the`exists` result from the`check-file` task is`yes`, and the`path` parameter is`README.md`.

The Pipeline Run details page of the OpenShift Container Platform web console shows the status of the tasks and when expressions as follows:

- All the criteria are met: Tasks and the when expression symbol, which is represented as a diamond shape, appear in a success state.
- Any one of the criteria are not met: The Task skips. Skipped tasks and the when expression symbol appear in a skipped state.
- None of the criteria are met: The Task skips. Skipped tasks and the when expression symbol appear in a skipped state.
- Task run fails: Failed tasks and the when expression symbol appear in a failed state.

#### 2.2.3. Finally tasksCopy link

You can use`finally` tasks to execute a final set of tasks in your pipeline regardless of whether the previous tasks succeed or fail. These tasks run in parallel after all other pipeline tasks finish, allowing you to perform cleanup or notification actions before the pipeline exits.

The`finally` tasks are the final set of tasks specified using the`finally` field in the pipeline YAML file. A`finally` task always executes the tasks within the pipeline, irrespective of whether the pipeline runs succeed.

You can configure a`finally` task to consume the results of any task within the same pipeline. This approach does not change the order in which this final task runs.

The following example shows a code snippet of the`clone-cleanup-workspace` pipeline. This code clones the repository into a shared workspace and cleans up the workspace. After the pipeline tasks finish, the`cleanup` task specified in the`finally` section of the pipeline YAML file cleans up the workspace.

```yaml
apiVersion: tekton.dev/v1
kind: Pipeline
metadata:
  name: clone-cleanup-workspace
spec:
  workspaces:
    - name: git-source
  tasks:
    - name: clone-app-repo
      taskRef:
        name: git-clone-from-catalog
      params:
        - name: url
          value: https://github.com/tektoncd/community.git
        - name: subdirectory
          value: application
      workspaces:
        - name: output
          workspace: git-source
  finally:
    - name: cleanup
      taskRef:
        name: cleanup-workspace
      workspaces:
        - name: source
          workspace: git-source
    - name: check-git-commit
      params:
        - name: commit
          value: $(tasks.clone-app-repo.results.commit)
      taskSpec:
        params:
          - name: commit
        steps:
          - name: check-commit-initialized
            image: alpine
            script: |
              if [[ ! $(params.commit) ]]; then
                exit 1
              fi
```

`metadata.name` Unique name of the pipeline.`spec.workspaces[0].name` The shared workspace where the git repository is copied.`spec.tasks[0].name` The task to clone the application repository to the shared workspace.`spec.finally[0].name` The task to clean-up the shared workspace.`spec.finally.taskRef` A reference to the task that runs in the task run.`spec.finally[0].name.workspaces` A shared storage volume that a task in a pipeline needs at runtime to receive input or offer output.`spec.finally[1].name.params` A list of parameters required for a task. If a parameter does not have an implicit default value, you must explicitly set its value.`spec.finally[1].name.taskSpec` Embedded task definition.

#### 2.2.4. Task runCopy link

You can use a`TaskRun` resource to instantiate and execute a task with specific inputs, outputs, and execution parameters on a cluster. You can initiate a task run independently or as part of a pipeline run to execute the steps defined in a task.

A`TaskRun` instantiates a task for execution with specific inputs, outputs, and execution parameters on a cluster. You can start it on its own or as part of a pipeline run for each task in a pipeline.

A task consists of one or more steps that run container images, and each container image performs a specific piece of build work. A task run starts the steps in a task in the specified order, until all steps run successfully or a failure occurs. A`TaskRun` is automatically created by a`PipelineRun` for each task in a pipeline.

The following example shows a task run that runs the`apply-manifests` task with the relevant input parameters:

```yaml
apiVersion: tekton.dev/v1
kind: TaskRun
metadata:
  name: apply-manifests-taskrun
spec:
  taskRunTemplate:
    serviceAccountName: pipeline
  taskRef:
    kind: Task
    name: apply-manifests
  workspaces:
  - name: source
    persistentVolumeClaim:
      claimName: source-pvc
```

`apiVersion` The task run API version`v1`.`kind` Specifies the type of Kubernetes object. In this example,`TaskRun`.`metadata.name` Unique name to identify this task run.`spec` Definition of the task run. For this task run, the task and the required workspace you define.`spec.taskRef` Name of the task reference used for this task run. This task run runs the`apply-manifests` task.`spec.workspaces` Workspace used by the task run.

#### 2.2.5. PipelinesCopy link

You can use a`Pipeline` resource to arrange a collection of tasks in a specific order of execution. By defining a pipeline, you construct complex workflows that automate the build, deployment, and delivery of your applications.

A`Pipeline` is a collection of`Task` resources arranged in a specific order of execution. You run them to construct complex workflows that automate the build, deployment and delivery of applications by using one or more tasks to define a CI/CD workflow for your application.

A`Pipeline` resource definition consists of several fields or attributes, which together enable the pipeline to run a specific goal. Each`Pipeline` resource definition must contain at least one`Task` resource, which obtains specific inputs and produces specific outputs. The pipeline definition can also optionally include several Conditions, Workspaces, Parameters, or Resources depending on the application requirements.

The following example shows the`build-and-deploy` pipeline, which builds an application image from a Git repository by using the`buildah` task provided in the`openshift-pipelines` namespace:

```yaml
apiVersion: tekton.dev/v1
kind: Pipeline
metadata:
  name: build-and-deploy
spec:
  workspaces:
  - name: shared-workspace
  params:
  - name: deployment-name
    type: string
    description: name of the deployment to be patched
  - name: git-url
    type: string
    description: url of the git repo for the code of deployment
  - name: git-revision
    type: string
    description: revision to be used from repo of the code for deployment
    default: "pipelines-1.21"
  - name: IMAGE
    type: string
    description: image to be built from the code
  tasks:
  - name: fetch-repository
    taskRef:
      resolver: cluster
      params:
      - name: kind
        value: task
      - name: name
        value: git-clone
      - name: namespace
        value: openshift-pipelines
    workspaces:
    - name: output
      workspace: shared-workspace
    params:
    - name: URL
      value: $(params.git-url)
    - name: SUBDIRECTORY
      value: ""
    - name: DELETE_EXISTING
      value: "true"
    - name: REVISION
      value: $(params.git-revision)
  - name: build-image
    taskRef:
      resolver: cluster
      params:
      - name: kind
        value: task
      - name: name
        value: buildah
      - name: namespace
        value: openshift-pipelines
    workspaces:
    - name: source
      workspace: shared-workspace
    params:
    - name: TLSVERIFY
      value: "false"
    - name: IMAGE
      value: $(params.IMAGE)
    runAfter:
    - fetch-repository
  - name: apply-manifests
    taskRef:
      name: apply-manifests
    workspaces:
    - name: source
      workspace: shared-workspace
    runAfter:
    - build-image
  - name: update-deployment
    taskRef:
      name: update-deployment
    workspaces:
    - name: source
      workspace: shared-workspace
    params:
    - name: deployment
      value: $(params.deployment-name)
    - name: IMAGE
      value: $(params.IMAGE)
    runAfter:
    - apply-manifests
```

`apiVersion` Pipeline API version`v1`.`kind` Specifies the type of Kubernetes object. In this example,`Pipeline`.`metadata.name` Unique name of this pipeline.`spec` Specifies the definition and structure of the pipeline.`spec.workspaces` Workspaces used across all the tasks in the pipeline.`spec.params` Parameters used across all the tasks in the pipeline.`tasks[0].name` Specifies the list of tasks used in the pipeline.`tasks[1].name` Task`build-image`, which uses the`buildah` task provided in the`openshift-pipelines` namespace to build application images from a given Git repository.`tasks[2].name` Task`apply-manifests`, which uses a user-defined task with the same name.`tasks[2].name.runAfter` Specifies the sequence in which tasks run in a pipeline. In this example, the`apply-manifests` task runs only after the`build-image` task finishes.

Note

The Red Hat OpenShift Pipelines Operator installs the Buildah task in the`openshift-pipelines` namespace and creates the`pipeline` service account with enough permissions to build and push an image. The Buildah task can fail when associated with a different service account with insufficient permissions.

#### 2.2.6. Pipeline runCopy link

You can use a`PipelineRun` resource to instantiate and execute a pipeline with specific inputs, outputs, and credentials. This resource binds a pipeline to a workspace and parameter values, enabling you to run your CI/CD workflow for a specific scenario.

A`PipelineRun` is a type of resource that binds a pipeline, workspaces, credentials, and a set of parameter values specific to a scenario to run the CI/CD workflow.

A pipeline run is the running instance of a pipeline. It also creates a task run for each task in the pipeline run.

The pipeline executes the tasks sequentially until they are complete or a task fails. The`status` field tracks and the progress of each task run and stores it for monitoring and auditing purposes.

The following example runs the`build-and-deploy` pipeline with relevant resources and parameters:

```yaml
apiVersion: tekton.dev/v1
kind: PipelineRun
metadata:
  name: build-deploy-api-pipelinerun
spec:
  pipelineRef:
    name: build-and-deploy
  params:
  - name: deployment-name
    value: vote-api
  - name: git-url
    value: https://github.com/openshift-pipelines/vote-api.git
  - name: IMAGE
    value: image-registry.openshift-image-registry.svc:5000/pipelines-tutorial/vote-api
  workspaces:
  - name: shared-workspace
    volumeClaimTemplate:
      spec:
        accessModes:
          - ReadWriteOnce
        resources:
          requests:
            storage: 500Mi
```

`apiVersion` Pipeline run API version`v1`.`kind` The type of Kubernetes object. In this example,`PipelineRun`.`metadata.name` Unique name to identify this pipeline run.`spec.pipelineRef.name` Name of the pipeline to run. In this example,`build-and-deploy`.`spec.params` The list of parameters required to run the pipeline.`spec.workspaces` Workspace used by the pipeline run.

#### 2.2.7. Pod templatesCopy link

You can use a pod template in a`PipelineRun` or`TaskRun` custom resource (CR) to configure the pods that execute your tasks. This allows you to set specific parameters, such as security contexts or user IDs, for every pod created during the pipeline or task run.

Optionally, you can define a pod template in a`PipelineRun` or`TaskRun` custom resource (CR). You can use any parameters available for a`Pod` CR in the pod template. When creating pods for running the pipeline or task, OpenShift Pipelines sets these parameters for every pod.

For example, you can use a pod template to make the pod run as a user and not as root.

For a pipeline run, you can define a pod template in the`pipelineRunTemplate.podTemplate` spec, as in the following example:

Example`PipelineRun` CR with a pod template

```yaml
apiVersion: tekton.dev/v1
kind: PipelineRun
metadata:
  name: mypipelinerun
spec:
  pipelineRef:
    name: mypipeline
  taskRunTemplate:
    podTemplate:
      securityContext:
        runAsNonRoot: true
        runAsUser: 1001
```

Note

In the earlier API version`v1beta1`, the pod template for a`PipelineRun` CR defined`podTemplate` directly in the`spec:` section. This format is not supported in the`v1` API.

For a task run, you can define a pod template in the`podTemplate` spec, as in the following example:

Example`TaskRun` CR with a pod template

```yaml
apiVersion: tekton.dev/v1
kind: TaskRun
metadata:
  name: mytaskrun
  namespace: default
spec:
  taskRef:
    name: mytask
  podTemplate:
    schedulerName: volcano
    securityContext:
      runAsNonRoot: true
      runAsUser: 1001
```

#### 2.2.8. WorkspacesCopy link

You can use workspaces to declare shared storage volumes that tasks in a pipeline need for input or output at runtime. By separating volume declaration from runtime storage, workspaces allow you to specify the filesystem location independently, making your tasks reusable and flexible across different environments.

Note

We recommend that you use workspaces instead of the`PipelineResource` CRs in Red Hat OpenShift Pipelines, as`PipelineResource` CRs are difficult to debug, limited in scope, and make tasks less reusable.

Instead of specifying the actual location of the volumes, workspaces allow you to declare the filesystem or parts of the filesystem that you need at runtime. A task or pipeline declares the workspace, and you must provide the specific location details of the volume. It is then mounted into that workspace in a task run or a pipeline run. This separation of volume declaration from runtime storage volumes makes the tasks reusable, flexible, and independent of the user environment.

With workspaces, you can:

- Store task inputs and outputs
- Share data among tasks
- Use it as a mount point for credentials held in secrets
- Use it as a mount point for configurations held in config maps
- Use it as a mount point for common tools shared by an organization
- Create a cache of build artifacts that accelerate jobs

You can specify workspaces in the`TaskRun` or`PipelineRun` using:

- A read-only config map or secret
- An existing persistent volume claim shared with other tasks
- A persistent volume claim from a volume claim template
- An`emptyDir` that the system discards when the task run completes

The following example shows a code snippet of the`build-and-deploy` pipeline, which declares a`shared-workspace` workspace for the`build-image` and`apply-manifests` tasks you define in the pipeline.

```yaml
apiVersion: tekton.dev/v1
kind: Pipeline
metadata:
  name: build-and-deploy
spec:
  workspaces:
  - name: shared-workspace
  params:
...
  tasks:
  - name: build-image
    taskRef:
      resolver: cluster
      params:
      - name: kind
        value: task
      - name: name
        value: buildah
      - name: namespace
        value: openshift-pipelines
    workspaces:
    - name: source
      workspace: shared-workspace
    params:
    - name: TLSVERIFY
      value: "false"
    - name: IMAGE
      value: $(params.IMAGE)
    runAfter:
    - fetch-repository
  - name: apply-manifests
    taskRef:
      name: apply-manifests
    workspaces:
    - name: source
      workspace: shared-workspace
    runAfter:
      - build-image
...
```

`spec.workspaces` List of workspaces shared between the tasks defined in the pipeline. A pipeline can define as many workspaces as you need. In this example, only one workspace named`shared-workspace` is declared.`tasks` Definition of tasks used in the pipeline. This snippet defines two tasks,`build-image` and`apply-manifests`, which share a common workspace.`tasks.workspaces` List of workspaces used in the`build-image` task. A task definition can include as many workspaces as it requires. However, we recommend that a task uses at most one writable workspace.`tasks.workspaces[0].name` Name that uniquely identifies the workspace you use in the task. This task uses one workspace named`source`.`tasks.workspaces[0].name.workspace` Name of the pipeline workspace used by the task. Note that the workspace`source` in turn uses the pipeline workspace named`shared-workspace`.`tasks[1].name.workspaces` List of workspaces used in the`apply-manifests` task. Note that this task shares the`source` workspace with the`build-image` task.

Workspaces help tasks share data, and let you specify one or more volumes that each task in the pipeline requires during execution. You can create a persistent volume claim or give a volume claim template that creates a persistent volume claim for you.

The following code snippet of the`build-deploy-api-pipelinerun` pipeline run uses a volume claim template to create a persistent volume claim for defining the storage volume for the`shared-workspace` workspace used in the`build-and-deploy` pipeline.

```yaml
apiVersion: tekton.dev/v1
kind: PipelineRun
metadata:
  name: build-deploy-api-pipelinerun
spec:
  pipelineRef:
    name: build-and-deploy
  params:
...

  workspaces:
  - name: shared-workspace
    volumeClaimTemplate:
      spec:
        accessModes:
          - ReadWriteOnce
        resources:
          requests:
            storage: 500Mi
```

`workspaces` Specifies the list of pipeline workspaces for which volume binding you will offer in the pipeline run.`workspaces[0].name` The name of the workspace in the pipeline for which the volume is being offered.`workspaces[0].name.volumeClaimTemplates` Specifies a volume claim template that creates a persistent volume claim to define the storage volume for the workspace.

#### 2.2.9. Step actionsCopy link

You can use a`StepAction` custom resource (CR) to define a reusable action that a step performs. By referencing a`StepAction` object from a step, you can share and reuse action definitions across multiple tasks or reference actions from external sources.

A step is a part of a task. If you define a step in a task, you cannot reference this step from another task.

This CR contains the action that a step performs. You can reference a`StepAction` object from a step to create a step that performs the action. You can also use resolvers to reference a`StepAction` definition that is available from an external source.

The following examples shows a`StepAction` CR named`apply-manifests-action`. This step action applies manifests from a source tree to your OpenShift Container Platform environment:

```yaml
apiVersion: tekton.dev/v1
kind: StepAction
metadata:
  name: apply-manifests-action
spec:
  params:
  - name: working_dir
    description: The working directory where the source is located
    type: string
    default: "/workspace/source"
  - name: manifest_dir
    description: The directory in source that contains yaml manifests
    default: "k8s"
  results:
  - name: output
    description: The output of the oc apply command
  image: image-registry.openshift-image-registry.svc:5000/openshift/cli:latest
  env:
  - name: MANIFEST_DIR
    value: $(params.manifest_dir)
  workingDir: $(params.working_dir)
  script: |
      #!/usr/bin/env bash
      oc apply -f "$MANIFEST_DIR" | tee $(results.output)
```

`spec.params[n].name.type` The`type` specification for a parameter is optional.

The`StepAction` CR does not include definitions of workspaces. Instead, the step action expects that the task that includes the action also provides the mounted source tree, typically using a workspace.

A`StepAction` object can define parameters and results. When you reference this object, you must specify the values for the parameters of the`StepAction` object in the definition of the step. The results of the`StepAction` object automatically become the results of the step.

Important

To avoid malicious attacks that use the shell, the`StepAction` CR does not support using parameter values in a`script` value. Instead, you must use the`env:` section to define environment variables that contain the parameter values.

The following example task includes a step that references the`apply-manifests-action` step action, provides the necessary parameters, and uses the result:

```yaml
apiVersion: tekton.dev/v1
kind: Task
metadata:
  name: apply-manifests-with-action
spec:
  workspaces:
  - name: source
  params:
  - name: manifest_dir
    description: The directory in source that contains yaml manifests
    type: string
    default: "k8s"
  steps:
  - name: apply
    ref:
      name: apply-manifests-action
    params:
    - name: working_dir
      value: "/workspace/source"
    - name: manifest_dir
      value: $(params.manifest_dir)
  - name: display_result
    script: 'echo $(step.apply.results.output)'
```

#### 2.2.10. TriggersCopy link

You can use Triggers in conjunction with pipelines to create a comprehensive CI/CD system driven by Kubernetes resources. Triggers capture external events, such as Git pull requests, and process them to extract information, enabling you to automatically instantiate pipelines and deploy resources based on event data.

For example, you define a CI/CD workflow by using Red Hat OpenShift Pipelines for your application. The pipeline must start for any new changes to take effect in the application repository. Triggers automate this process by capturing and processing any change event and by triggering a pipeline run that deploys the new image with the latest changes.

Triggers consist of the following main resources that work together to form a reusable, decoupled, and self-sustaining CI/CD system:

The`TriggerBinding` resource extracts the fields from an event payload and stores them as parameters.

The following example shows a code snippet of the`TriggerBinding` resource, from which you extract the Git repository information from the received event payload:

```yaml
apiVersion: triggers.tekton.dev/v1
kind: TriggerBinding
metadata:
  name: vote-app
spec:
  params:
  - name: git-repo-url
    value: $(body.repository.url)
  - name: git-repo-name
    value: $(body.repository.name)
  - name: git-revision
    value: $(body.head_commit.id)
```

`apiVersion` The API version of the`TriggerBinding` resource. In this example,`v1`.`kind` Specifies the type of Kubernetes object. In this example,`TriggerBinding`.`metadata.name` A unique name to identify the`TriggerBinding` resource.`spec.params` The list of parameters extracted from the received event payload and passed to the`TriggerTemplate` resource. In this example, the`TriggerTemplate` extracts the Git repository URL, name, and revision from the body of the event payload.

The`TriggerTemplate` resource acts as a standard for creating resources. It specifies how the parameterized data from the`TriggerBinding` resource defines the new resources. A trigger template receives input from the trigger binding, then performs a series of actions that results in the creation of new pipeline resources and the initiation of a new pipeline run.

The following example shows a code snippet of a`TriggerTemplate` resource, which creates a pipeline run by using the Git repository information received from the`TriggerBinding` resource you just created:

```yaml
apiVersion: triggers.tekton.dev/v1
kind: TriggerTemplate
metadata:
  name: vote-app
spec:
  params:
  - name: git-repo-url
    description: The git repository url
  - name: git-revision
    description: The git revision
    default: pipelines-1.21
  - name: git-repo-name
    description: The name of the deployment to be created / patched

  resourcetemplates:
  - apiVersion: tekton.dev/v1
    kind: PipelineRun
    metadata:
      name: build-deploy-$(tt.params.git-repo-name)-$(uid)
    spec:
      taskRunTemplate:
        serviceAccountName: pipeline
      pipelineRef:
        name: build-and-deploy
      params:
      - name: deployment-name
        value: $(tt.params.git-repo-name)
      - name: git-url
        value: $(tt.params.git-repo-url)
      - name: git-revision
        value: $(tt.params.git-revision)
      - name: IMAGE
        value: image-registry.openshift-image-registry.svc:5000/pipelines-tutorial/$(tt.params.git-repo-name)
      workspaces:
      - name: shared-workspace
        volumeClaimTemplate:
         spec:
          accessModes:
           - ReadWriteOnce
          resources:
            requests:
              storage: 500Mi
```

`apiVersion` The API version of the`TriggerTemplate` resource. In this example,`v1`.`kind` Specifies the type of Kubernetes object. In this example,`TriggerTemplate`.`metadata.name` Unique name to identify the`TriggerTemplate` resource.`spec.params` Parameters supplied by the`TriggerBinding` resource.`resourcetemplates` List of templates that specify how you create resources by using the parameters received through the`TriggerBinding` or`EventListener` resources.

The`Trigger` resource combines the`TriggerBinding` and`TriggerTemplate` resources, and optionally, the`interceptors` event processor.

Interceptors process all the events for a specific platform that runs before the`TriggerBinding` resource. You can use interceptors to filter the payload, verify events, define and test trigger conditions, and implement other useful processing. Interceptors use secret for event verification. After the event data passes through an interceptor, it then goes to the trigger before you pass the payload data to the trigger binding. You can also use an interceptor to change the behavior of the associated trigger referenced in the`EventListener` specification.

The following example shows a code snippet of a`Trigger` resource, named`vote-trigger` that connects the`TriggerBinding` and`TriggerTemplate` resources, and the`interceptors` event processor.

```yaml
apiVersion: triggers.tekton.dev/v1
kind: Trigger
metadata:
  name: vote-trigger
spec:
  taskRunTemplate:
    serviceAccountName: pipeline
  interceptors:
    - ref:
        name: "github"
      params:
        - name: "secretRef"
          value:
            secretName: github-secret
            secretKey: secretToken
        - name: "eventTypes"
          value: ["push"]
  bindings:
    - ref: vote-app
  template:
     ref: vote-app
---
apiVersion: v1
kind: Secret
metadata:
  name: github-secret
type: Opaque
stringData:
  secretToken: "1234567"
```

`apiVersion` The API version of the`Trigger` resource. In this example,`v1`.`kind` Specifies the type of Kubernetes object. In this example,`Trigger`.`metadata.name` A unique name to identify the`Trigger` resource.`spec.taskRunTemplate.serviceAccountName` The service account name to use.`interceptors.ref.name` Interceptor name to reference. In this example,`github`.`interceptors.params` The required parameters to specify.`bindings.ref` The name of the`TriggerBinding` resource to connect to the`TriggerTemplate` resource.`template.ref` The name of the`TriggerTemplate` resource to connect to the`TriggerBinding` resource.`kind` The Secret to use to verify events.

The`EventListener` resource provides an endpoint, or an event sink, that listens for incoming HTTP-based events with a JSON payload. You extract event parameters from each`TriggerBinding` resource, and then process this data to create Kubernetes resources as specified by the corresponding`TriggerTemplate` resource. The`EventListener` resource also performs lightweight event processing or basic filtering on the payload by using event`interceptors`, which identify the type of payload and optionally change it. Currently, pipeline triggers support five types of interceptors:

Common Expression Language (CEL) Interceptors.

The following example shows an`EventListener` resource, which references the`Trigger` resource named`vote-trigger`.

```yaml
apiVersion: triggers.tekton.dev/v1
kind: EventListener
metadata:
  name: vote-app
spec:
  taskRunTemplate:
    serviceAccountName: pipeline
  triggers:
    - triggerRef: vote-trigger
```

`apiVersion` The API version of the`EventListener` resource. In this example,`v1`.`kind` Specifies the type of Kubernetes object. In this example,`EventListener`.`metadata.name` A unique name to identify the`EventListener` resource.`spec.taskRunTemplate.serviceAccountName` The service account name to use.`spec.triggers.triggerRef` The name of the`Trigger` resource that the`EventListener` resource references.

- Webhook Interceptors
- GitHub Interceptors
- GitLab Interceptors
- Bitbucket Interceptors, and

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



## Release notes

Release notes | Red Hat OpenShift Pipelines | 1.21 | Red Hat Documentation

 

 

 

 

 

 

 

 

# Release notes

---

Red Hat OpenShift Pipelines 1.21

## Highlights of what is new and what has changed with this OpenShift Pipelines release

Red Hat OpenShift Documentation Team

Legal Notice

Abstract

The release notes for OpenShift Pipelines summarize all new features and enhancements, notable technical changes, major corrections from the previous version, and any known bugs upon general availability.

---

## Chapter 1. Red Hat OpenShift Pipelines release notesCopy link

Note

For additional information about the OpenShift Pipelines lifecycle and supported platforms, refer to the [OpenShift Operator Life Cycles](https://access.redhat.com/support/policy/updates/openshift_operators) and [Red Hat OpenShift Container Platform Life Cycle Policy](https://access.redhat.com/support/policy/updates/openshift).

Release notes contain information about new and deprecated features, breaking changes, and known issues. The following release notes apply for the most recent OpenShift Pipelines releases on OpenShift Container Platform.

Red Hat OpenShift Pipelines is a cloud-native CI/CD experience based on the Tekton project which provides:

- Legal Notice
- Standard Kubernetes-native pipeline definitions (CRDs).
- Serverless pipelines with no CI server management overhead.
- Extensibility to build images using any Kubernetes tool, such as S2I, Buildah, JIB, and Kaniko.
- Portability across any Kubernetes distribution.
- Powerful CLI for interacting with pipelines.
- Integrated user experience with the OpenShift Container Platform web console, up to OpenShift Container Platform version 4.20.

For an overview of Red Hat OpenShift Pipelines, see [Understanding OpenShift Pipelines](https://docs.redhat.com/en/documentation/red_hat_openshift_pipelines/1.21/html-single/about_openshift_pipelines/#understanding-openshift-pipelines).

### 1.1. Compatibility and support matrixCopy link

Some features in this release are currently in [Technology Preview](https://access.redhat.com/support/offerings/techpreview). These experimental features are not intended for production use.

In the table, features are marked with the following statuses:

TP

Technology Preview

GA

General Availability

| |
| --- |

Expand

Operator

Pipelines

Triggers

CLI

Chains

Hub

Pipelines as Code

Results

Manual Approval Gate

Pruner

Cache

1.21

1.6.x

0.34.x

0.43.x

0.26.x (GA)

1.23.x (TP)

0.39.x (GA)

0.17.x (GA)

0.7.x (TP)

0.3.x (GA)

0.3.x (GA)

4.14, 4.16, 4.17, 4.18, 4.19, 4.20

GA

1.20

1.3.x

0.33.x

0.42.x

0.25.x (GA)

1.22.x (TP)

0.37.x (GA)

0.16.x (GA)

0.6.x (TP)

0.2.x (TP)

0.2.x (TP)

4.14, 4.16, 4.17, 4.18, 4.19, 4.20

GA

1.19

1.0.x

0.32.x

0.41.x

0.25.x (GA)

1.21.x (TP)

0.35.x (GA)

0.15.x (GA)

0.6.x (TP)

0.2.x (TP)

4.14, 4.16, 4.17, 4.18, 4.19, 4.20

GA

| Table 1.1. Compatibility and support matrix | Red Hat OpenShift Pipelines Version | Component Version | OpenShift Version | Support Status |
| --- | --- | --- | --- | --- |

Show more

Note

The OpenShift console plugin for OpenShift Pipelines follows the same version as the OpenShift Pipelines Operator.

For questions and feedback, you can send an email to the product team at [pipelines-interest@redhat.com](mailto:pipelines-interest@redhat.com).

### 1.2. Release notes for Red Hat OpenShift Pipelines 1.21Copy link

With this update, Red Hat OpenShift Pipelines General Availability (GA) 1.21 is available on OpenShift Container Platform 4.14 and later supported versions.

For more information about the supported versions of OpenShift Container Platform, see [Life Cycle Dates](https://access.redhat.com/support/policy/updates/openshift#dates).

#### 1.2.1. New features and enhancementsCopy link

In addition to fixes and stability improvements, the following sections highlight what is new in Red Hat OpenShift Pipelines 1.21:

Pipelines

Read-only root filesystems enabled for OpenShift Pipelines containers With this update, all OpenShift Pipelines containers, including controllers and webhooks, are configured with the`readOnlyRootFilesystem` parameter set to`true`. This change follows security best practices for Kubernetes-based workloads. By enforcing a read-only root filesystem, OpenShift Pipelines improves its security posture by helping to prevent unauthorized modifications to the container runtime environment.

Override individual TaskRun timeouts in a PipelineRun

With this update, you can override the timeout for individual`TaksRun` objects within a`PipelineRun` using the`spec.taskRunSpecs[].timeout` field.

```yaml
apiVersion: tekton.dev/v1
  kind: PipelineRun
  metadata:
    name: example-with-timeout-override
  spec:
    pipelineRef:
      name: my-pipeline
    timeouts:
      pipeline: "2h"
    taskRunSpecs:
      - pipelineTaskName: build-task
        timeout: "30m"
      - pipelineTaskName: test-task
        timeout: "1h"
```

This allows finer-grained control over task execution duration without affecting the overall`PipelineRun` timeout.

Resolver caching for bundle, Git, and cluster resolvers

With this update, resolver caching is supported for bundle, Git, and cluster resolvers. This helps reduce redundant fetches, minimize external API calls, and improve pipeline execution reliability, especially when external services impose rate limits or are temporarily unavailable.

Global settings: You can configure caching using your`TektonConfig` custom resource (CR), where you can set the cache size and adjust the time to live (TTL) value without restarting controllers:

```yaml
apiVersion: operator.tekton.dev/v1alpha1
kind: TektonConfig
metadata:
  name: config
spec:
  pipeline:
    options:
      configMaps:
        resolver-cache-config:
          data:
            max-size: "1000"
            ttl: "5m"
#...
```

- `max-size`: defines the maximum number of cached entries. The default value is`"1000"`.
- `ttl`: defines the time to live of the cache entry. The default value is`"5m"`.

Per-resolver defaults: You can set the default caching mode for specific resolvers using the`bundleresolver-config`,`git-resolver-config`, or`cluster-resolver-config` config maps:

```yaml
#...
data:
  cache: "auto"
  #...
```

- The available modes are`auto`(cache only immutable references),`always`(cache everything), and`never`(disable caching). The default value is`auto`.

`TaskRun` or`PipelineRun` overrides: You can override the default caching mode for individual runs by adding the`cache` parameter to the`TaskRun` or`PipelineRun` specification:

```yaml
#...
params:
  - name: cache
    value: "always"
#...
```

Resolver caching helps improve reliability, reduce latency for frequently accessed resources, and decrease load on external services such as GitHub and OCI registries. Cache hits, misses, and timestamps are added to resource annotations for resolved resources.

Array values can be resolved in when expressions

With this update, array value resolution is enabled in the`input` attribute of`when` expressions.

The following Tekton`PipelineRun` custom resource configures a parameter array.

```yaml
apiVersion: tekton.dev/v1
kind: PipelineRun
metadata:
  name: pipelinerun-with-array-when
spec:
  params:
    - name: branches
      type: array
      value:
        - main
        - develop
        - release
  pipelineSpec:
    params:
      - name: branches
        type: array
    tasks:
      - name: deploy-if-valid-branch
        when:
          - input: "main"
            operator: in
            values: ["$(params.branches[*])"]
        taskSpec:
          steps:
            - name: deploy
              image: alpine
              script: |-
                echo "Deploying..."
```

The following example demonstrates how a task can produce an array result that is consumed by a subsequent task.

```yaml
# ...
    tasks:
      - name: get-environments
        taskSpec:
          results:
            - name: envs
              type: array
          steps:
            - name: produce-array
              image: bash
              script: |-
                echo -n '["dev", "staging", "prod"]' | tee $(results.envs.path)

      - name: deploy-to-staging
        when:
          - input: "staging"
            operator: in
            values: ["$(tasks.get-environments.results.envs[*])"]
        taskRef:
          name: deploy
```

Support for display names added to steps

With this update, a`displayName` field is added to`Step` objects. The following example configures a`Task` resource with`displayName` values set:

```yaml
apiVersion: tekton.dev/v1
kind: Task
metadata:
  name: build-and-test
spec:
  params:
    - name: app-name
      type: string
  steps:
    - name: build
      displayName: "Build the application"
      image: golang:1.21
      script: |
        go build ./...

    - name: test
      displayName: "Run unit tests for $(params.app-name)" # Supports param substitution
      image: golang:1.21
      script: |
        go test ./...

    - name: lint
      displayName: "Lint source code"
      image: golangci/golangci-lint
      script: |
        golangci-lint run
  # ...
```

Operator

A new parameter for controlling pipeline service account permissions

Before this update, the pipeline service account automatically received the`edit` ClusterRole within its namespace, following legacy RBAC behavior. With this update, you can use the new`legacyPipelineRbac` parameter to control permissions. Set it to`false` to prevent the pipeline service account from receiving the`edit` ClusterRole, enforcing more restricted permissions by default. The default value is`true`.

```yaml
apiVersion: operator.tekton.dev/v1alpha1
kind: TektonConfig
metadata:
  name: config
spec:
  targetNamespace: openshift-pipelines
  params:
  - name: createRbacResource
    value: "true"
  - name: legacyPipelineRbac
    value: "true"
```

Important

Existing role bindings are not automatically removed from namespaces with the pipeline service account. You must remove them manually when changing this parameter on existing deployments.

Route is automatically created for Tekton Results API endpoint With this update, the Tekton Results component automatically creates an OpenShift`Route` CRD for its API endpoint. You can optionally configure a custom host and path for the`Route`. This helps ensure the Tekton Results API is accessible externally without requiring additional user configuration.

User interface

Group support for Approval Tasks With this update, Approval Tasks support group approvers. You can specify a group using the`group: ` syntax in the`params` list. Any member of the group can approve or reject the task. Approvals by any group member count as a single approval, while a rejection by any member immediately counts as a single rejection and fails the task. Group members also receive notifications about the task, just like individual approvers. Pipeline Overview page retains filter selections With this update, the Pipeline Overview page persists user selections for the Namespace, Time Range, and Refresh Interval filters. These selections are stored in the application state and URL query parameters. This helps ensure more consistent experience when navigating away, returning to the page, or refreshing the browser. The filters reset only when the user switches namespaces. Time-range filter label updated for clarity With this update, the time-range filter previously labeled "Last weeks" is updated to "Last week". This change resolves customer confusion regarding the intended single-week time range and helps ensure consistency between the UI and the Tekton Results API.

Pipelines as Code

Improved performance for GitLab project access control checks With this update,`Pipelines as Code` caches the results of GitLab Project Access Control List (ACL) membership queries. This optimization reduces repeated API calls to the GitLab API, improving performance and efficiency during permission checks.

Configure the number of lines in error log snippets

With this update, you can configure how many lines appear in error log snippets using the new`error-log-snippet-number-of-lines` setting:

```yaml
apiVersion: operator.tekton.dev/v1alpha1
kind: TektonConfig
metadata:
  name: config
spec:
  platforms:
    openshift:
      pipelinesAsCode:
        enable: true
        settings:
          error-log-snippet-number-of-lines: "3"
          #...
```

Log snippets are automatically truncated to 65,000 characters, preventing failures when posting check-run updates to the GitHub API.

GitLab commit status set on the source and target project by default With this update, Pipelines as Code attempts to set the commit status on both the source and target upstream projects in GitLab. If permission issues cause both attempts to fail, the system falls back to posting a comment instead. This ensures that the commit status is communicated in the most relevant location for merge requests.

Improved error message for GitLab private repository access failures

With this update, Pipelines as Code proactively checks whether the configured GitLab token has the required read access to the source repository when processing merge requests from private forks. If the token lacks the necessary`read_repository` scope, Pipelines as Code fails early with the following error:

```shell-session
an error occurred: failed to access GitLab source repository ID REPOSITORY_ID: please ensure token has 'read_repository' scope on that repository
```

This helps ensure that permission issues are easier to identify and prevents pipelines from failing unexpectedly later in the process.

Trigger and cancel PipelineRuns for Git tags

With this update, you can trigger`PipelineRuns` by using comments on commits associated with a Git tag. This provides flexible, version-specific control of CI/CD workflows.

To trigger a`PipelineRun`, add a comment on the tagged commit using the format`/test tag: `, for example:

```plaintext
/test xyz-pipeline-run tag:v1.0.0
```

To cancel a`PipelineRun`, use the format`/cancel tag: `, for example:

```plaintext
/cancel xyz-pipeline-run tag:v1.0.0
```

This feature is supported for GitHub and GitLab, enabling teams to trigger or cancel`PipelineRuns` tied to specific tagged versions.

SHA validation added to /ok-to-test commands to prevent race conditions With this update, a new setting,`require-ok-to-test-sha`, is introduced in Pipelines as Code to enforce commit SHA validation when using the`/ok-to-test` comment command on GitHub pull requests. This feature mitigates a critical TOCTOU (Time-of-Check to Time-of-Use) race condition vulnerability, specific to the GitHub provider, where an attacker could execute a pipeline on an unapproved SHA. When the setting is enabled, users must specify the exact commit SHA, for example,`/ok-to-test `, for approval. As a result, this ties the approval directly to a specific commit, preventing pipeline execution on any subsequent malicious force-pushed SHA. Incoming webhook targets support glob patterns

With this update, the`targets` field in the Pipelines as Code`Repository` custom resource (CR) is enhanced to support glob patterns for incoming webhook events. In addition to exact string matching, you can utilize patterns to match multiple branch names with a single rule, helping simplify configuration and management for complex repository structures.

The following shell-style glob patterns are supported:

`{a,b,c}`: Matches any of the alternatives separated by a comma, for example,`{dev,staging}/\*` matches`dev/test` or`staging/test`.

If multiple incoming webhooks match the same branch, the first matching webhook defined in the YAML order is used. To ensure expected behavior, place more specific webhooks before general catch-all webhooks in your configuration.

- `*`: Matches any sequence of characters, for example,`feature/\*` matches`feature/login` or`feature/api`.
- `?`: Matches exactly one single character, for example,`v?` matches`v1` or`v2`.
- `[abc]`: Matches a single character that is part of the defined set, for example,`[A-Z]\*` matches any branch starting with an uppercase letter.
- `[0-9]`: Matches a single digit, for example,`v[0-9]*.[0-9]\*` matches`v1.2` or`v10.5`.

Incoming webhook requests support a new namespace parameter An optional`namespace` parameter is added to Pipelines as Code incoming webhook requests. When specified along with the existing`repository` parameter, it uniquely identifies the targeted`Repository` custom resource (CR). This ensures correct routing even when multiple repositories share the same name across the cluster. If multiple`Repository` CRs exist with the same name and the`namespace` parameter is omitted, Pipelines as Code returns a`400` status code, requiring the user to provide the namespace for unambiguous identification. Default secret key added for incoming webhooks With this update, when no`secret` key is explicitly specified in the`Repository` custom resource incoming webhook configuration, the system defaults to using`"secret"` as the key name when retrieving the secret value from the`Secret` resource.

Tekton Results

Fine-grained retention policies

With this update, Tekton Results supports fine-grained retention policies. You can set different retention periods for`PipelineRun` and`TaskRun` results based on namespace, labels, annotations, and status. The first matching policy is applied; if none match, the`defaultRetention` period is used.

Configure policies in the`tekton-results-config-results-retention-policy` config map using the`policies` key. Each policy includes a`selector` and a`retention` period:

```yaml
result:
    disabled: false
    is_external_db: false
    options:
      configMaps:
        tekton-results-config-results-retention-policy:
          data:
            defaultRetention: "30d"
            policies:
              - name: "retain-critical-failures-long-term"
                selector:
                  matchNamespaces:
                    - "production"
                    - "prod-east"
                  matchLabels:
                    "criticality": ["high"]
                  matchStatuses:
                    - "Failed"
                retention: "180d"
              - name: "retain-annotated-for-debug"
                selector:
                  matchAnnotations:
                    "debug/retain": ["true"]
                retention: "14d"
              - name: "default-production-policy"
                selector:
                  matchNamespaces:
                    - "production"
                    - "prod-east"
                retention: "60d"
              - name: "short-term-ci-retention"
                selector:
                  matchNamespaces:
                    - "ci"
                retention: "10h"
            runAt: "0 2 * * *"
```

This config map example:

- Runs the pruning job daily at 2:00 AM, as specified by the`runAt` cron schedule.
- Keeps failed Results in`production` or`prod-east` with`criticality: high` for 180 days.
- Keeps Results with annotation`debug/retain: "true"` for 14 days.
- Keeps other Results in`production` or`prod-east` for 60 days.
- Keeps Results in the`ci` namespace for 10 hours.
- Keeps all other Results for the default retention period (30 days).

PostgreSQL support updated to version 17.5 With this update, Tekton Results supports PostgreSQL version 17.5.

New metrics for runs not stored in the database

With this update, Tekton Results adds the`runs_not_stored_count` metric, emitted by the default`watcher` container. This metric tracks the number of`PipelineRun` and`TaskRun` instances that are deleted before they can be persisted in the database. Supported tags include:

- `kind`– the type of run (`PipelineRun` or`TaskRun`),
- `namespace`– the namespace where the run was created.

For example:

```shell-session
watcher_runs_not_stored_count{kind="PipelineRun",namespace="default"} 5
```

Metrics for run storage latency

With this update, Tekton Results adds the`run_storage_latency_seconds` metric, emitted by the default`watcher` container. This metric measures the time between run completion and its successful storage in the database.

Supported tags include:

`namespace`– the namespace where the run was created

```plaintext
watcher_run_storage_latency_seconds{kind="PipelineRun",namespace="default"} 0.5
```

This metric is emitted only when a run transitions from completed to stored, helping ensure accurate measurement of storage latency without being skewed by multiple reconciliations.

- `kind`– the type of run (`PipelineRun` or`TaskRun`)

CLI configuration persists across namespaces Before this update, the API configuration for the Tekton Results CLI was fetched from the current namespace context. As a consequence, users had to re-authenticate or reconfigure after switching namespaces. With this update, the configuration persists across namespace switches, removing the need to run the`opc results config set` command repeatedly. Default database migration to PostgreSQL version 15

The PostgreSQL image used by the default Tekton Results deployment is upgraded from version 13 to version 15, addressing the upcoming end of life (EOL) for PostgreSQL 13. This process implements an automated migration, which uses the slower yet more reliable data copy mechanism from the original PostgreSQL image.

Important

If you are using the default PostgreSQL deployment, ensure you have backed up your data and that the underlying Persistent Volume Claim (PVC) has more than 50% free space before you start the OpenShift Pipelines upgrade. If you are using an external database with Tekton Results, you are not affected by this change.

Tekton Cache

Tekton Cache is generally available With this update, Tekton Cache is generally available (GA) and is fully supported for production use. Tekton Cache was previously available as a Technology Preview (TP) feature.

Tekton Cache binaries available for public download With this update, the Tekton Cache product binaries are available for download without authentication. This accessibility enables customers to use the Red Hat binaries for their custom`StepAction` configurations.

Improved support for Docker credentials Before this update,`Tekton Cache` required Docker secrets to include a`config.json` key. With this update, Docker secrets without a`config.json` key are supported. The`DOCKER_CONFIG` parameter can point to any location containing either a`config.json` file or a`.dockerconfigjson` file, improving flexibility for private registry authentication.

Tekton Triggers

GitHub interceptor enforces SHA-256 signature validation Before this update, the GitHub interceptor supported both SHA-1 (`X-Hub-Signature`) and SHA-256 (`X-Hub-Signature-256`) signatures for webhook validation. With this update, the GitHub interceptor enforces a stricter security posture and only accepts SHA-256 signatures via the`X-Hub-Signature-256` header, dropping support for SHA-1. As a result, standard GitHub webhooks remain unaffected, but any custom webhook implementations must update their HMAC signature generation from SHA-1 to SHA-256 to avoid validation errors.

Tekton Hub

Default database migration to PostgreSQL version 15 The PostgreSQL database version used by the Tekton Hub is migrated from version 13 to version 15 to address the upcoming end of life (EOL) for PostgreSQL 13. This upgrade ensures continued stability and support for the Tekton Hub. Additionally, the process implements an automated transition from version 13 to version 15 for existing deployments.

Tekton Chains

Flexible provenance and signing configuration With this update, you can choose to disable image signing while still enabling provenance generation and attestation signing. This enhancement helps provide more flexibility in managing security artifacts within your CI/CD pipelines. New option to disable OCI image signing With this update, a new configuration option,`artifacts.oci.disable-signing`, is added to the Tekton Chains config map. This option enables you to skip OCI image signing performed by Tekton Chains while still maintaining provenance generation and attestation signing. This feature is intended for users who prefer to sign images using an external workflow, such as`cosign sign`, but still require Tekton Chains to maintain supply-chain integrity for metadata. By default, this option is set to`false`, ensuring no change in behavior for existing configurations unless explicitly enabled.

CLI

Support for rerunning resolver-based PipelineRuns With this update, the`tkn` CLI introduces a new`--resolvertype` flag to the`tkn p start` command. This flag allows you to specify the resolver type, such as`git`,`http`,`hub`,`cluster`,`bundle`, or`remote`, for any supported resolver type, when re-running a resolver-based`PipelineRun`. You can reference an existing`PipelineRun` name to re-run it using the specified resolver type.

New --resolvertype to support rerunning resolver-based PipelineRuns With this update, the`tkn p start --last` command introduces the`--resolvertype` flag. This flag enables users to specify the resolver type, such as`git`,`hub`, or`bundle`, when re-running a previous resolver-based`PipelineRun`. Additionally, the help text for the command has been updated to use the correct pronoun.

Pruner

Event-driven pruner is generally available

With this update, the event-driven pruner`tektonpruner` is generally available (GA) and is fully supported as a pruning mechanism for OpenShift Pipelines with centralized and hierarchical configuration.

While existing pruning mechanisms, such as the default job-based pruner, Pipelines as Code`keep-max-run`, and Tekton Results based`retention`, continue to function, users currently relying on legacy pruning approaches are encouraged to adopt the event-driven pruner`tektonpruner` to help ensure smoother performance, more predictable cleanup, and reduced operational overhead.

The following enhancements are present in this release:

Pruner config map validation: With this update, the event-driven pruner validates config maps at apply-time using a Kubernetes admission webhook. Invalid configurations, such as unsupported formats, negative values, or namespace settings that exceed global limits, are rejected with clear error messages instead of failing silently. For validation to apply, pruner config maps must include the following labels:

```yaml
labels:
  app.kubernetes.io/part-of: tekton-pruner
  pruner.tekton.dev/config-type: namespace
```

- Namespace-level pruner configuration: With this update, the event-driven pruner supports custom pruning policies at the namespace level. You can define custom pruning policies, such as time to live and history limits, that override global defaults by creating a`tekton-pruner-namespace-spec` config map in your namespace.
- Selector-based pruning configuration: With this update, the event-driven pruner supports selector-based resource matching with the`matchLabels` and`matchAnnotations` selectors. The selector matching logic when you specify both`matchLabels` and`matchAnnotations` selectors is AND and name matching has absolute precedence regardless of selector presence. The selector-based resource matching is supported only in the namespace-level`tekton-pruner-namespace-spec` config maps, not in the global`TektonConfig` CR configuration.
- Cluster-wide maximum limits added for the event-driven pruner configuration: With this update, the event-driven pruner enforces cluster-wide maximum limits for configuration fields when global limits are not specified. If global limits are set, they take precedence. This validation helps ensure that namespace-specific pruner settings do not exceed the defined maximum values, helping prevent potential resource overuse. The maximum TTL in seconds is`2592000` and the maximum history limit is`100`.

#### 1.2.2. Technology preview featuresCopy link

Pipelines as Code

A new command for evaluating CEL expressions (Technology Preview)

A new`tkn` CLI command,`tkn pac cel`, allows administrators to interactively evaluate Common Expression Language (CEL) expressions against webhook payloads and headers. You can use the following syntax:

```shell-session
$ tkn pac cel -b <body.json> -H <headers.txt>
```

- `-b` or`--body`: Specify a path to a JSON body file. This is a webhook payload.
- `-H` or`--headers`: Specify a path to headers file. This can be a plain text, a JSON file, or a gosmee script.
- `-p` or`--provider`: Specify the provider. This can be`github`,`gitlab`,`bitbucket-cloud`,`bitbucket-datacenter`,`gitea`, or`auto` for automatic detection from the payload.

Key capabilities include:

Variable access using:

- direct variables, such as`event` or`target_branch`,
- webhook payload fields, for example,`body.action`,
- HTTP headers, for example,`headers['X-GitHub-Event']`,
- Pipelines as Code parameters, for example,`pac.revision`.

Manual Approval Gate

Group support for Approval Task With this update, the Approval Task supports group approvers. Specify group approvers using the`group: ` syntax in the`params` list. Any member of the group can approve or reject the task. An approval by any group member counts as a single approval, while a rejection by any member immediately counts as a single rejection and fails the task. This enhancement provides more flexible approval workflows by allowing teams to delegate approvals to groups as well as individuals.

ApprovalTask preserves messages from all group members With this update, messages added by any member of a group when approving or rejecting an`ApprovalTask` are preserved in both the group input and the`status.approverResponse` array. This helps ensure that all context and comments provided by group members remain visible for audit and review purposes.

#### 1.2.3. Breaking changesCopy link

User interface

Pipelines console navigation requires explicit plugin enablement With this update, the legacy static console plugin is fully deprecated. After installing the Red Hat OpenShift Pipelines Operator, you must explicitly enable the console plugin to access the Pipelines section in the OpenShift Container Platform console. The previous fallback behavior, which displayed a limited Pipelines entry when the plugin was disabled, is removed, and the Pipelines navigation menu is only visible when the console plugin is active.

Pipelines as Code

pipelinerun_status field in Repository custom resource is depracated With this update, the`pipelinerun_status` field of the`Repository` custom resource (CR) is deprecated and will be removed in a future release. Update any integrations or automation that reference this field to ensure compatibility with upcoming versions.

Tekton Chains

Cosign v2.6.0 update affects keyless signing With this update, Tekton Chains uses Cosign version 2.6.0, which no longer accepts HS256 JWT tokens for keyless signing. If your private OIDC provider uses HS256 tokens for authentication, you must switch to RS256 before upgrading to this release. If you use perform key-based signing, or use a private OIDC provider already configured with RS256, you are not affected by this change.

#### 1.2.4. Known issuesCopy link

User interface

Duplicate Pipelines navigation entry in the OpenShift Console

During the transition from static to dynamic console plugins, the OpenShift Console might temporarily display two Pipelines entries in the navigation menu. This is a UI-only issue and does not affect pipeline execution or data.

To work around this problem, apply the corresponding updates to the OpenShift Console and the Red Hat OpenShift Pipelines Operator. Users running older Red Hat OpenStack Platform versions should coordinate upgrades with OpenShift Container Platform to prevent a temporary disappearance of the Pipelines menu.

[SRVKP-10006](https://issues.redhat.com/browse/SRVKP-10006)

#### 1.2.5. Fixed issuesCopy link

Pipelines

PipelineRuns fail clearly on invalid apiVersion

Before this update, setting the`spec.tasks[].taskRef.apiVersion` field to an invalid value caused`PipelineRun` execution to fail silently. With this update,`PipelineRun` displays a clear error when`taskRef.apiVersion` is invalid.

[SRVKP-8514](https://issues.redhat.com/browse/SRVKP-8514)

PipelineRuns no longer fail on temporary TaskRef reconciliation errors

Before this update,`PipelineRuns` failed when`TaskRef` reconciliation encountered retryable errors. This led to unnecessary pipeline failures on transient issues. With this update, the controller logic ensures`PipelineRuns` only fail on explicit validation errors, not retryable errors. As a result, the reliability of pipelines resolving external tasks is improved.

[SRVKP-9135](https://issues.redhat.com/browse/SRVKP-9135)

Kubernetes-native sidecars no longer cause repeated init container restarts

Before this update, Kubernetes-native sidecars had issues with repeated init container restarts. With this update, signal handling is added to`SidecarLog` results. As a result, the sidecar gracefully handles signals, stabilizing the lifecycle and preventing unnecessary restarts of the init containers.

[SRVKP-9135](https://issues.redhat.com/browse/SRVKP-9135)

Pods for timed-out TaskRuns are retained

Before this update, pods for timed-out`TaskRuns` were not retained when the`keep-pod-on-cancel` feature flag was enabled. With this update, the system ensures pods are retained when the flag is enabled. As a result, debugging and analysis of timed-out tasks are consistently supported when the feature is active.

[SRVKP-9135](https://issues.redhat.com/browse/SRVKP-9135)

StepAction status steps no longer display in incorrect order

Before this update, status steps displayed in an incorrect order when using`StepAction`. This made it difficult to interpret the chronological flow of actions. With this update, the system ensures status steps display in the correct sequential order. As a result, the timeline and history of`StepAction` executions are accurately presented.

[SRVKP-9135](https://issues.redhat.com/browse/SRVKP-9135)

TaskRuns no longer fail on arm64 clusters due to platform mismatch

Before this update,`arm64` Kubernetes clusters experienced`TaskRun` failures due to platform variant mismatch in entrypoint lookup. This prevented successful execution on this architecture. With this update, the entrypoint logic correctly handles Linux platform variants. As a result,`TaskRuns` execute reliably on`arm64` clusters.

[SRVKP-9135](https://issues.redhat.com/browse/SRVKP-9135)

Operator

Improved proxy webhook performance by replacing synchronous checks

Before this update, the proxy webhook could timeout under high-concurrency workloads because it performed synchronous API calls to verify config map existence during pod admission. With this update, the webhook uses optional config map volumes that gracefully handle missing CA bundles without blocking pod creation. As a result, the defaulting webhook is less affected by etcd performance issues, the CA bundles configmaps are always mounted as Optional volumes, and the environment variable`SSL_CERT_DIR` is always set on TaskRun step-containers.

[SRVKP-8377](https://issues.redhat.com/browse/SRVKP-8377)

Corrected prioritySemaphore locking to prevent deadlocks and race conditions

Before this update, the`prioritySemaphore` implementation could cause deadlocks, race conditions, and panics due to unsynchronized data access. With this update, the locking logic is corrected and all shared data is properly synchronized, preventing these concurrency issues.

[SRVKP-8198](https://issues.redhat.com/browse/SRVKP-8198)

Retained pods on TaskRun timeout when the keep-pod-on-cancel flag is true

Before this update, when the`keep-pod-on-cancel` setting was set to`true`,`TaskRun` pods were retained only if the`TaskRun` was canceled. When a`TaskRun` timed out its pods would be deleted. With this update,`TaskRun` pods are not deleted if their`TaskRun` times out when the`keep-pod-on-cancel` seeting is set to`true`.

[SRVKP-9176](https://issues.redhat.com/browse/SRVKP-9176)

Normalized TektonConfig container args to -key=value format

Before this update, TektonConfig container args could contain duplicates and`["-key","value"]` pairs. With this update, flags are normalized to the`"-key=value"` format and duplicates are removed, simplifying configuration.

[SRVKP-7889](https://issues.redhat.com/browse/SRVKP-7889)

Default catalog name updates correctly during upgrade

Before this update, upgrading from versions 1.19.x to 1.20.0 incorrectly set the`hub-catalog-name` field in the Pipelines-as-Code config map to the deprecated Tekton Hub catalog name,`tekton`. As a consequence, this led to unexpected behavior when resolving catalog tasks. With this update, the default value points to the Artifact Hub catalog name. As a result, the upgrade process ensures consistent and expected behavior.

[SRVKP-8930](https://issues.redhat.com/browse/SRVKP-8930)

nodeSelector and tolerations propagate correctly to Results pods

Before this update, the`nodeSelector` and`tolerations` settings configured in the Tekton Config under the Results section were not applied to Tekton Results pods. As a consequence, pod scheduling behavior did not reflect the user-configured preferences. With this update, the`nodeSelector` and`tolerations` configurations from the Tekton Config propagate correctly to all Tekton Results pods.

[SRVKP-8922](https://issues.redhat.com/browse/SRVKP-8922)

Webhook validation no longer targets control-plane namespaces

Before this update, the logic for the`tekton-operator-proxy-webhook` parameter attempted to validate resources in control-plane namespaces, such as`kube-*` and`openshift-\*`. This behavior caused unintended webhook certificate issues that affected unrelated system components. With this update, the webhook logic excludes all control-plane namespaces from admission validation. This improvement ensures better isolation between Tekton components and other cluster operators.

[SRVKP-8891](https://issues.redhat.com/browse/SRVKP-8891)

Custom hub catalog configuration is preserved during conversion

Before this update, the OpenShift Pipelines Operator removed the`catalog-{INDEX}-type` field during conversion, which caused the loss of custom hub catalog types. With this update, the Operator preserves the`catalog-{INDEX}-type` field in its config map.

[SRVKP-9472](https://issues.redhat.com/browse/SRVKP-9472)

User interface

Fixed PipelineRun cancelling status in OpenShift Console after TaskRuns complete

Before this update, the OpenShift Container Platform Console showed`PipelineRuns` in a cancelling state even after all associated`TaskRuns` completed, due to an internal UI inconsistency. With this update, the OpenShift Console`PipelineRun` status mechanism is corrected. As a result,`PipelineRun` status accurately reflects the state of completed`TaskRuns`.

[SRVKP-6960](https://issues.redhat.com/browse/SRVKP-6960)

Fixed validation error preventing saving of Buildah tasks in Pipeline builder UI

Before this update, the`Pipeline` builder UI failed to save`Buildah` tasks due to a validation error with the default`BUILD_ARGS` parameter. With this update, the validation error is resolved. As a result, the`Pipeline` builder UI correctly saves`Buildah` tasks, even when using the default`BUILD_ARGS` parameter.

[SRVKP-8571](https://issues.redhat.com/browse/SRVKP-8571)

Fixed incorrect sorting of PipelineRuns by duration

Before this update, sorting`PipelineRuns` by duration used a string sort instead of actual duration values, causing misleading results. With this update, sorting correctly uses the duration in seconds. As a result,`PipelineRuns` are accurately sorted by actual duration.

[SRVKP-6211](https://issues.redhat.com/browse/SRVKP-6211)

Fixed TaskRun sorting by duration in the OpenShift Container Platform console

Before this update, sorting`TaskRun` resources by duration in the OpenShift Container Platform console incorrectly used completion time instead of elapsed time. As a result, the list displayed durations in an incorrect chronological order, such as a six-minute task appearing before a fifty-second task. With this update, the console correctly calculates the duration before sorting.

[SRVKP-8835](https://issues.redhat.com/browse/SRVKP-8835)

Added strict navigation URLs task name matching

Before this update, when two tasks had similar names, such as`tkn` and`kn`, the application returned the first partial string match. This issue caused incorrect navigation via the URL. With this update, the task names are matched using strict equality checks to avoid partial matches and guarantee correct URL navigation.

[SRVKP-8976](https://issues.redhat.com/browse/SRVKP-8976)

Fixed the Overview page displaying an error message

Previously, when the`tekton-results-postgres` or`tekton-results-api` pods were restarted, the Overview page displayed an "Oh No! Something Went Wrong" error. With this update, when pipeline results data is unavailable, an empty state is displayed instead of an error message. This provides a smoother and more consistent user experience.

[SRVKP-8076](https://issues.redhat.com/browse/SRVKP-8076)

Fixed immediate YAML editor updates using useEffect hook

Before this update, YAML editor changes were not applied until the component was remounted. With this update, changes are immediately reflected using the`useEffect` hook, improving the editing experience.

[SRVKP-8205](https://issues.redhat.com/browse/SRVKP-8205)

Pagination fix for archived PipelineRun results

Before this update, switching the`data_source` filter to`archived` prevented additional PipelineRun results from loading when scrolling. The UI expected a`nextPageToken` field, while the API returned`next_page_token`, causing the on-scroll callback to never request the next page. As a result, pagination stopped after the initial page of results. With this update, the client correctly handles the`next_page_token` field, ensuring that pagination proceeds as expected and all archived PipelineRun data loads properly.

[SRVKP-9397](https://issues.redhat.com/browse/SRVKP-9397)

Pipeline Builder no longer displays stale task parameter data across namespaces

Before this update, the Pipeline Builder displayed stale or incorrect task parameter data when multiple tasks with the same name existed in different namespaces. As a consequence, users configured pipelines with invalid parameters. With this update, the system performs stronger validation to detect task name conflicts, generates unique task names when duplicates appear, and cleans up task data during removal. As a result, the side panel shows accurate task information from the selected namespace.

[SRVKP-8998](https://issues.redhat.com/browse/SRVKP-8998)

Pagination works correctly for archived PipelineRun results

Before this update, scrolling failed to load additional archived`PipelineRun` results because the UI client expected the`nextPageToken` field while the API returned`next_page_token`. With this update, the UI is updated to support`next_page_token`. As a result, pagination functions correctly when the`data_source` filter is set to`archived`.

[SRVKP-9397](https://issues.redhat.com/browse/SRVKP-9397)

Time range and refresh interval selections now persist on the Pipeline Overview page

Before this update, the Time Range and Refresh Interval selections on the Pipeline Overview page did not persist across navigation or page refreshes, leading to an inconsistent user experience. With this update, these selections persist across navigation and page refreshes, and are reset to default values when switching namespaces.

[SRVKP-9607](https://issues.redhat.com/browse/SRVKP-9607)

Pipeline Overview page reliability is improved for slow backend responses

Before this update, slow or intermittent backend responses caused the Pipeline Overview page to display stale or partially loaded data, or fail silently in slower clusters. With this update, additional safeguards prevent stale or incomplete data from being displayed, and API timeouts have been increased to help improve reliability.

[SRVKP-9427](https://issues.redhat.com/browse/SRVKP-9427)

Ambiguous time-range filter label is corrected

Before this update, the time-range filter label Last weeks was ambiguous. With this update, the label is changed to Last week, and the associated API payload has been aligned to ensure more consistent behavior.

[SRVKP-9428](https://issues.redhat.com/browse/SRVKP-9428)

Loading indicators added to Pipeline Overview cards

Before this update, the Pipeline Overview page did not display loading indicators on individual cards while data was being fetched, making it unclear whether data was still loading. With this update, loading spinners are displayed on each card during data retrieval, helping provide clearer visual feedback.

[SRVKP-9436](https://issues.redhat.com/browse/SRVKP-9436)

Pipelines as Code

GitOps commands in GitLab MR discussion replies are recognized

Before this update, GitOps commands, such as`/ok-to-test`, posted as replies within GitLab merge request discussion threads were ignored; only commands in the top-level comment of a discussion were recognized. With this update, the GitLab provider honors commands posted in replies, improving command recognition and workflow reliability.

[SRVKP-8324](https://issues.redhat.com/browse/SRVKP-8324)

CI status correctly shows Pending for unauthorized Bitbucket PRs

Before this update, when a pull request was opened by an unauthorized user on Bitbucket Data Center, the CI status was incorrectly shown as`Running` instead of`Pending`. With this update, the status correctly shows`Pending` while awaiting administrator approval.

[SRVKP-8269](https://issues.redhat.com/browse/SRVKP-8269)

install info and namespace binding corrected

Before this update, the`opc-pac` CLI incorrectly bound`--namespace/-n` to kubeconfig, and the`install info` command did not show repositories for a single CR. With this update, the CLI binds correctly and`install info` displays repositories properly.

[SRVKP-7152](https://issues.redhat.com/browse/SRVKP-7152)

Tag push events no longer affected by skip-push-event-for-pr-commits setting

Before this update, push events triggered by Git tag pushes were incorrectly skipped when the`skip-push-event-for-pr-commits` setting was enabled. With this update, tag push events are no longer affected by this setting and proceed as expected.

[SRVKP-9111](https://issues.redhat.com/browse/SRVKP-9111)

Unauthorized /ok-to-test approvals are correctly invalidated on new commits

Before this update, when`remember-ok-to-test` was set to`false`, a single`/ok-to-test` approval on a merge request (MR) from an unauthorized user was incorrectly remembered for all subsequent commits pushed to that MR in GitLab. With this update, permissions are re-evaluated on every new commit, correctly halting Continuous Integration (CI) and requiring a new`/ok-to-test` for each change as intended.

[SRVKP-9200](https://issues.redhat.com/browse/SRVKP-9200)

GitLab API compatibility fix for canceled PipelineRuns

Before this update, an issue in the GitLab provider system did not properly recognize canceled`PipelineRuns` due to a spelling mismatch between "cancelled" and "canceled", which is expected by the GitLab API. This issue caused GitLab merge requests to automatically merge even though the associated`PipelineRun` was canceled. With this update, this issue is fixed. An explicit mapping from "cancelled" to "canceled" for GitLab API compatibility helps ensure that cancelled`PipelineRuns` are correctly reported and merge requests remain open as expected.

[SRVKP-9050](https://issues.redhat.com/browse/SRVKP-9050)

Placeholder variable evaluation no longer fails when data sources are missing

Before this update, placeholder variable evaluation failed when either the event`payload` or`headers` sources were missing. This failure occurred because the evaluation logic attempted to evaluate`body.*`,`headers.\*`, and`files.\*` placeholders together. With this update, the evaluation logic processes these placeholders independently. As a result, each placeholder works if its corresponding data is present.

[SRVKP-8984](https://issues.redhat.com/browse/SRVKP-8984)

GitHub App installation ID retrieval is optimized

Before this update, retrieving GitHub App installation IDs involved unnecessary API listings, impacting performance and increasing API calls. With this update, the system optimizes the retrieval process by removing these listings and directly fetching the installation using the repository URL, with fallback to organization installation.

[SRVKP-9141](https://issues.redhat.com/browse/SRVKP-9141)

Incorrect commit IDs for Bitbucket merge commits are fixed

Before this update, a change caused the`revision` variable to fetch incorrect commit IDs for Bitbucket merge commits. With this update, the problematic change is reverted. As a result, the expected behavior is restored, and the correct commit IDs are fetched for Bitbucket merge commits.

[SRVKP-9141](https://issues.redhat.com/browse/SRVKP-9141)

Cancellation of running PipelineRuns is correctly scoped

Before this update, cancellations triggered by PR-close events could accidentally cancel`push`-triggered`PipelineRuns`. With this update, the cancellation logic correctly targets only`PipelineRuns` that were triggered by the pull request (PR).

[SRVKP-9141](https://issues.redhat.com/browse/SRVKP-9141)

GitLab merge request comments post correctly from forks

Before this update, GitLab merge request comments were not posted correctly from forks due to the use of an incorrect Project ID. With this update, the system is fixed to use the correct`TargetProjectID` for merge request comments. As a result, comments are posted successfully even when originating from a fork.

[SRVKP-9141](https://issues.redhat.com/browse/SRVKP-9141)

Duplicate secret creation is handled gracefully

Before this update, the system would fail when encountering an existing secret during creation (duplicate secret error). With this update, the secret creation logic is modified to gracefully reuse the existing secret instead of failing.

[SRVKP-9141](https://issues.redhat.com/browse/SRVKP-9141)

GitHub check runs and patching logic is corrected

Before this update, Pipelines as Code created check runs incorrectly by always using the hardcoded state`'in_progress'`, omitting key output fields, and attempting to patch`PipelineRun` resources even after validation failed. With this update, the system uses the proper status and conclusion from`statusOpts`, adds the`Title`,`Summary`, and`Text` output fields to check runs, and prevents patch attempts when the`PipelineRun` name is invalid.

[SRVKP-9141](https://issues.redhat.com/browse/SRVKP-9141)

Failed commit status is correctly updated after validation fixes in GitLab

Before this update, when a`PipelineRun` execution failed due to validation errors in GitLab, the failed commit status persisted even after the user fixed the validation error. This caused the merge request to appear failed and blocked auto-merge. With this update, the system ensures that the commit status is correctly updated after validation fixes.

[SRVKP-9141](https://issues.redhat.com/browse/SRVKP-9141)

Commit status updates correctly after validation fixes in GitLab

Before this update,`PipelineRun` objects that failed validation in GitLab displayed incorrect names in the pipeline status, causing the failed commit status to persist even after the errors were resolved. With this update, the commit status updates correctly after validation issues are fixed. As a result, merge requests reflect the correct status and the auto-merge functionality is enabled.

[SRVKP-9044](https://issues.redhat.com/browse/SRVKP-9044)

Tekton Ecosystem

Multiple image copy enabled in skopeo-copy task using url.txt

Before this update, the`skopeo-copy` task failed to copy multiple images when source and destination image URLs were not provided, as it required non-empty image URLs and bypassed the`url.txt` file method. With this update, the`skopeo-copy` task parameters are optional, allowing the use of the`url.txt` file for multiple image copies regardless of source and destination URLs. As a result, the task supports copying multiple images using`url.txt`.

[SRVKP-6491](https://issues.redhat.com/browse/SRVKP-6491)

Tekton Results

Description for PipelineRun deletion metric is accurate

Before this update, the metrics exposed by Tekton Results had an inaccurate description for the metric tracking the duration of`PipelineRun` deletion. This caused confusion and reduced the reliability of metrics reporting. With this update, the description for the`prDeleteDuration` metric is corrected to accurately reflect the time between`PipelineRun` completion and final deletion.

[SRVKP-9138](https://issues.redhat.com/browse/SRVKP-9138)

Race condition causing database constraint violations is fixed

Before this update, a race condition existed in the Tekton Results watcher where`PipelineRun` and`TaskRun` handlers could concurrently attempt to create the same`Result` record. This led to PostgreSQL unique-constraint violations and ambiguous`Unknown` gRPC errors. With this update, proper handling of SQLSTATE duplicate key errors is introduced by refetching already-created records, and a PostgreSQL error-to-gRPC translator is added.

[SRVKP-9138](https://issues.redhat.com/browse/SRVKP-9138)

Annotations management updated for reliability

Before this update, Tekton Results used a multi-step logic and merge patches for managing annotation updates, which could lead to unreliable and conflict-prone updates to Kubernetes objects. With this update, Tekton Results refactors its annotation handling to use a single`Patch` operation and switches from merge patches to Server-Side Apply (SSA). These changes are internal and do not introduce user-facing changes but provide more reliable and conflict-aware updates.

[SRVKP-9138](https://issues.redhat.com/browse/SRVKP-9138)

The defaultRetention field takes precedence over the deprecated maxRetention field

Before this update, the configuration behavior was inconsistent if the deprecated maxRetention field was carried over during an upgrade. With this update, the defaultRetention field correctly takes precedence over the deprecated field. As a result, the retention policy remains consistent with the TektonConfig CR specification, maintaining backward compatibility during the deprecation period.

[SRVKP-9425](https://issues.redhat.com/browse/SRVKP-9425)

Tekton Hub

The system no longer downloads an outdated version of the git-clone task

Before this update, the system downloaded an outdated version of the`git-clone` task (0.9.0) instead of the latest version (0.10). As a result, end users installed the older 0.9 version, causing inconsistencies and missing improvements available in 0.10. With this update, the`git-clone` task is updated to version 0.10.

[SRVKP-8568](https://issues.redhat.com/browse/SRVKP-8568)

Tekton Chains

Anti-affinity rule added to tekton-chains-controller spec

Before this update, the`tekton-chains-controller` spec did not include an anti-affinity rule, causing pods to be unevenly distributed across nodes and potentially leading to resource contention. With this update, an anti-affinity rule is added to the`tekton-chains-controller` spec, improving pod scheduling and ensuring better resource distribution.

[SRVKP-8892](https://issues.redhat.com/browse/SRVKP-8892)

TaskRun finalizer no longer remains on resources

Before this update, an old`TaskRun` finalizer could remain on resources, which prevented the proper cleanup of completed tasks. With this update, the unnecessary finalizer is removed. As a result, TaskRun resources are cleaned up as expected.

[SRVKP-9137](https://issues.redhat.com/browse/SRVKP-9137)

End-to-End testing suite stability is restored

Before this update, a build error caused failures in the End-to-End (E2E) testing suite. With this update, the build error is resolved.

[SRVKP-9137](https://issues.redhat.com/browse/SRVKP-9137)

Pruner

Namespace-level pruner configuration updates takes effect immediately after upgrade

Before this update, if event based pruner was enabled before an upgrade, the Operator reverted the pruner config values to default values after the upgrade. With this update, the values of pruner config are retained after an upgrade.

[SRVKP-10008](https://issues.redhat.com/browse/SRVKP-10008)

#### 1.2.6. Deprecated featuresCopy link

pipelinerun_status field in Repository custom resource is deprecated

With this update, the`pipelinerun_status` field available in the`Repository` custom resource is deprecated and will be removed in a future release.

[SRVKP-8663](https://issues.redhat.com/browse/SRVKP-8663)

OpenCensus is deprecated

OpenCensus is supported in this release but is deprecated and might be removed in a future release. It is anticipated that a future version of Red Hat OpenShift Pipelines will migrate from OpenCensus to OpenTelemetry for observability and tracing. This migration might require updates to existing PromQL queries.

[SRVKP-8534](https://issues.redhat.com/browse/SRVKP-8534)

## Legal NoticeCopy link

Copyright © Red Hat.

The text of and illustrations in this document are licensed by Red Hat under a Creative Commons Attribution–Share Alike 3.0 Unported license ("CC-BY-SA"). An explanation of CC-BY-SA is available at [http://creativecommons.org/licenses/by-sa/3.0/](http://creativecommons.org/licenses/by-sa/3.0/). In accordance with CC-BY-SA, if you distribute this document or an adaptation of it, you must provide the URL for the original version.

Red Hat, as the licensor of this document, waives the right to enforce, and agrees not to assert, Section 4d of CC-BY-SA to the fullest extent permitted by applicable law.

Red Hat, Red Hat Enterprise Linux, the Shadowman logo, JBoss, OpenShift, Fedora, the Infinity logo, and RHCE are trademarks of Red Hat, Inc., registered in the United States and other countries.

Linux® is the registered trademark of Linus Torvalds in the United States and other countries.

Java® is a registered trademark of Oracle and/or its affiliates.

XFS® is a trademark of Silicon Graphics International Corp. or its subsidiaries in the United States and/or other countries.

MySQL® is a registered trademark of MySQL AB in the United States, the European Union and other countries.

Node.js® is an official trademark of Joyent. Red Hat Software Collections is not formally related to or endorsed by the official Joyent Node.js open source or commercial project.

The OpenStack® Word Mark and OpenStack logo are either registered trademarks/service marks or trademarks/service marks of the OpenStack Foundation, in the United States and other countries and are used with the OpenStack Foundation's permission. We are not affiliated with, endorsed or sponsored by the OpenStack Foundation, or the OpenStack community.

All other trademarks are the property of their respective owners.

 

 

[Github](https://github.com/redhat-documentation) [Youtube](https://www.youtube.com/@redhat) [Twitter](https://twitter.com/RedHat)

### Learn

- Debugging: Quickly test and debug CEL expressions used in`PipelineRun` resource configurations against real webhook data.
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



## Installing and configuring

Installing and configuring | Red Hat OpenShift Pipelines | 1.21 | Red Hat Documentation

 

 

 

 

 

 

 

 

# Installing and configuring

---

Red Hat OpenShift Pipelines 1.21

## Installing and configuring OpenShift Pipelines

Red Hat OpenShift Documentation Team

Legal Notice

Abstract

This document provides information about installing and configuring OpenShift Pipelines.

---

## Chapter 1. Installing OpenShift PipelinesCopy link

This guide walks cluster administrators through the process of installing the Red Hat OpenShift Pipelines Operator to an OpenShift Container Platform cluster.

#### Prerequisites

- Legal Notice
- You have access to an OpenShift Container Platform cluster using an account with`cluster-admin` permissions.
- You have installed`oc` CLI.
- You have installed [OpenShift Pipelines (tkn) CLI](https://docs.redhat.com/en/documentation/red_hat_openshift_pipelines/1.21/html-single/pipelines_cli_tkn_reference/#installing-tkn) on your local system.
- Your cluster has the [Marketplace capability](https://docs.openshift.com/container-platform/latest/installing/overview/cluster-capabilities.html#marketplace-operator_cluster-capabilities) enabled or the Red Hat Operator catalog source configured manually.

Note

In a cluster with both Windows and Linux nodes, Red Hat OpenShift Pipelines can run on only Linux nodes.

### 1.1. Installing the Red Hat OpenShift Pipelines Operator in web consoleCopy link

You can install the Red Hat OpenShift Pipelines Operator by using the OpenShift Container Platform web console to automatically configure the necessary custom resources (CRs) for your pipelines. This method provides a graphical interface to manage the installation and seamless upgrades of the Operator and its components.

The default Operator custom resource definition (CRD)`config.operator.tekton.dev` is now replaced by`tektonconfigs.operator.tekton.dev`. In addition, the Operator provides the following additional CRDs to individually manage OpenShift Pipelines components:`tektonpipelines.operator.tekton.dev`,`tektontriggers.operator.tekton.dev` and`tektonaddons.operator.tekton.dev`.

If you have OpenShift Pipelines already installed on your cluster, the existing installation is seamlessly upgraded. The Operator will replace the instance of`config.operator.tekton.dev` on your cluster with an instance of`tektonconfigs.operator.tekton.dev` and additional objects of the other CRDs as necessary.

Warning

If you manually changed your existing installation, such as, changing the target namespace in the`config.operator.tekton.dev` CRD instance by making changes to the`resource name - cluster` field, then the upgrade path is not smooth. In such cases, the recommended workflow is to uninstall your installation and reinstall the Red Hat OpenShift Pipelines Operator.

The Red Hat OpenShift Pipelines Operator now provides the option to select the components that you want to install by specifying profiles as part of the`TektonConfig` custom resource (CR). The Operator automatically installs the`TektonConfig` CR when you install the Operator. The supported profiles are:

- Lite: This profile installs only Tekton Pipelines.
- Basic: This profile installs Tekton Pipelines, Tekton Triggers, Tekton Chains, and Tekton Results.
- All: This is the default profile used when you install the`TektonConfig` CR. This profile installs all of the Tekton components, including Tekton Pipelines, Tekton Triggers, Tekton Chains, Tekton Results, Pipelines as Code, and Tekton add-ons. Tekton add-ons includes the`ClusterTriggerBindings`,`ConsoleCLIDownload`,`ConsoleQuickStart`, and`ConsoleYAMLSample` resources, and the tasks and step action definitions available by using the cluster resolver from the`openshift-pipelines` namespace.

Procedure

On the Install Operator page:

Select an Update Channel.

To install a specific version of the Red Hat OpenShift Pipelines Operator, cluster administrators can use the corresponding`pipelines- ` channel. For example, to install the Red Hat OpenShift Pipelines Operator version`1.8.x`, you can use the`pipelines-1.8` channel.

Note

Starting with OpenShift Container Platform 4.11, the`preview` and`stable` channels for installing and upgrading the Red Hat OpenShift Pipelines Operator are not available. However, in OpenShift Container Platform 4.10 and earlier versions, you can use the`preview` and`stable` channels for installing and upgrading the Operator.

- The`latest` channel enables installation of the most recent stable version of the Red Hat OpenShift Pipelines Operator. Currently, it is the default channel for installing the Red Hat OpenShift Pipelines Operator.

Click Install. You will see the Operator listed on the Installed Operators page.

Note

The Operator installs automatically into the`openshift-operators` namespace.

Verify that the Status displays Succeeded Up to date to confirm successful installation of Red Hat OpenShift Pipelines Operator.

Warning

The success status might show as Succeeded Up to date even if installation of other components is in-progress. Therefore, it is important to verify the installation manually in the terminal.

Verify that the Red Hat OpenShift Pipelines Operator installed all components successfully. Login to the cluster on the terminal, and run the following command:

```shell-session
$ oc get tektonconfig config
```

Example output

```shell-session
NAME     VERSION                          READY   REASON
config   1.21.0     True
```

If the READY condition is True, the Operator and its components installed successfully.

Additionally, check the components' versions by running the following command:

```shell-session
$ oc get tektonpipeline,tektontrigger,tektonchain,tektonaddon,pac
```

Example output

```shell-session
NAME                                                             VERSION   READY   REASON
tektonpipeline.operator.tekton.dev/pipeline                      v0.47.0   True

NAME                                                             VERSION   READY   REASON
tektontrigger.operator.tekton.dev/trigger                        v0.23.1   True

NAME                                                             VERSION   READY   REASON
tektonchain.operator.tekton.dev/chain                            v0.16.0   True

NAME                                                             VERSION   READY   REASON
tektonaddon.operator.tekton.dev/addon                            1.11.0     True

NAME                                                             VERSION   READY   REASON
openshiftpipelinesascode.operator.tekton.dev/pipelines-as-code   v0.19.0   True
```

### 1.2. Installing the OpenShift Pipelines Operator by using the CLICopy link

You can install the Red Hat OpenShift Pipelines Operator from the OperatorHub by using the command-line interface (CLI) to manage your installation programmatically. Once you install the Operator, you can create a`Subscription` object to subscribe a namespace to the Operator and automate the deployment process.

Procedure

Create a`Subscription` object YAML file to subscribe a namespace to the Red Hat OpenShift Pipelines Operator, for example,`sub.yaml`:

```yaml
apiVersion: operators.coreos.com/v1alpha1
kind: Subscription
metadata:
  name: openshift-pipelines-operator
  namespace: openshift-operators
spec:
  channel:  <channel_name>
  name: openshift-pipelines-operator-rh
  source: redhat-operators
  sourceNamespace: openshift-marketplace
```

`spec.channel` Name of the channel that you want to subscribe. The`pipelines- ` channel is the default channel. For example, the default channel for Red Hat OpenShift Pipelines Operator version`1.7` is`pipelines-1.7`. The`latest` channel enables installation of the most recent stable version of the Red Hat OpenShift Pipelines Operator.`spec.name` Name of the Operator to subscribe to.`spec.source` Name of the`CatalogSource` object that provides the Operator.`spec.sourceNamespace` Namespace of the`CatalogSource` object. Use`openshift-marketplace` for the default OperatorHub catalog sources.

Create the`Subscription` object by running the following command:

```shell-session
$ oc apply -f sub.yaml
```

The subscription installs the Red Hat OpenShift Pipelines Operator into the`openshift-operators` namespace. The Operator automatically installs OpenShift Pipelines into the default`openshift-pipelines` target namespace.

### 1.3. Red Hat OpenShift Pipelines Operator in a restricted environmentCopy link

You can use the Red Hat OpenShift Pipelines Operator to support the installation of pipelines in a restricted network environment. The Operator automatically configures proxy settings for your pipeline containers and resources, ensuring they can operate securely within your network constraints.

The Operator installs a proxy webhook that sets the proxy environment variables in the containers of the pod created by tekton-controllers based on the`cluster` proxy object. It also sets the proxy environment variables in the`TektonPipelines`,`TektonTriggers`,`Controllers`,`Webhooks`, and`Operator Proxy Webhook` resources.

By default, the proxy webhook is disabled for the`openshift-pipelines` namespace. To disable it for any other namespace, you can add the`operator.tekton.dev/disable-proxy: true` label to the`namespace` object.

## Chapter 2. Uninstalling OpenShift PipelinesCopy link

Cluster administrators can uninstall the Red Hat OpenShift Pipelines Operator by performing the following steps:

Delete the Custom Resources (CRs) for the optional components,`TektonHub` and`TektonResult`, if these CRs exist, and then delete the`TektonConfig` CR.

Important

If you uninstall the Operator without removing the CRs of optional components, you cannot remove the components later.

1. Uninstall the Red Hat OpenShift Pipelines Operator.
2. Delete the Custom Resource Definitions (CRDs) of the`operator.tekton.dev` group.

Uninstalling only the Operator will not remove the Red Hat OpenShift Pipelines components created by default when the Operator is installed.

### 2.1. Deleting the OpenShift Pipelines custom resourcesCopy link

You can remove the OpenShift Pipelines custom resources (CRs) to clean up the configuration before uninstalling the Operator. This involves deleting optional components such as`TektonHub` and`TektonResult`, followed by the main`TektonConfig` CR.

Procedure

1. In the Administrator perspective of the web console, navigate to Administration → CustomResourceDefinitions.
2. Type`TektonHub` in the Filter by name field to search for the`TektonHub` Custom Resource Definition (CRD).
3. Click the name of the`TektonHub` CRD to display the details page for the CRD.
4. Click the`Instances` tab.
5. If an instance is displayed, click the Options menu for the displayed instance.
6. Select Delete TektonHub.
7. Click Delete to confirm the deletion of the CR.
8. Repeat these steps, searching for`TektonResult` and then`TektonConfig` in the Filter by name box. If any instances are found for these CRDs, delete these instances.

Note

Deleting the CRs also deletes the Red Hat OpenShift Pipelines components and all the tasks and pipelines on the cluster.

Important

If you uninstall the Operator without removing the`TektonHub` and`TektonResult` CRs, you cannot remove the Tekton Hub and Tekton Results components later.

### 2.2. Uninstalling the Red Hat OpenShift Pipelines OperatorCopy link

You can uninstall the Red Hat OpenShift Pipelines Operator by using the OpenShift Container Platform web console to remove the OpenShift Pipelines service from your cluster. This process involves deleting the Operator subscription and its associated operand instances.

Procedure

1. From the Operators → OperatorHub page, use the Filter by keyword box to search for the Red Hat OpenShift Pipelines Operator.
2. Click the Red Hat OpenShift Pipelines Operator tile. The Operator tile indicates that the Operator is installed.
3. In the Red Hat OpenShift Pipelines Operator description page, click Uninstall.
4. In the Uninstall Operator? window, select Delete all operand instances for this operator, and then click Uninstall.

Warning

When you uninstall the OpenShift Pipelines Operator, the uninstallation process deletes all resources within the`openshift-pipelines` target namespace where OpenShift Pipelines is installed, including the secrets you configured.

### 2.3. Deleting the custom resource definitions of the operator.tekton.dev groupCopy link

You can delete the`operator.tekton.dev` custom resource definitions (CRDs) to fully remove all OpenShift Pipelines traces from your cluster. This step ensures that no residual definitions remain after the Operator uninstallation.

Delete the`CustomResourceDefinitions` of the`operator.tekton.dev` group. The Red Hat OpenShift Pipelines Operator creates these CRDs by default during installation.

Procedure

To delete each of the displayed CRDs, complete the following steps:

1. Click the Options menu.
2. Select Delete CustomResourceDefinition.
3. Click Delete to confirm the deletion of the CRD.

## Chapter 3. Customizing configurations in the TektonConfig custom resourceCopy link

In Red Hat OpenShift Pipelines, you can customize the following configurations by using the`TektonConfig` custom resource (CR):

- Optimizing OpenShift Pipelines performance, including high-availability mode for the OpenShift Pipelines controller
- Configuring the Red Hat OpenShift Pipelines control plane
- Changing the default service account
- Disabling the service monitor
- Configuring pipeline resolvers
- Disabling pipeline templates
- Disabling the integration of Tekton Hub
- Disabling the automatic creation of RBAC resources
- Pruning of task runs and pipeline runs

### 3.1. PrerequisitesCopy link

- You have installed the Red Hat OpenShift Pipelines Operator.

### 3.2. Performance tuning using the TektonConfig custom resourceCopy link

You can tune the performance and high availability (HA) of the OpenShift Pipelines controller by editing the`TektonConfig` custom resource (CR). You can adjust parameters such as replica counts, buckets, and API query limits to optimize the controller for your specific workload requirements.

```yaml
apiVersion: operator.tekton.dev/v1alpha1
kind: TektonConfig
metadata:
  name: config
spec:
  pipeline:
    performance:
      disable-ha: false
      buckets: 7
      replicas: 5
      threads-per-controller: 2
      kube-api-qps: 5.0
      kube-api-burst: 10
```

All fields are optional. If you set them, the Red Hat OpenShift Pipelines Operator includes most of the fields as arguments in the`openshift-pipelines-controller` deployment under the`openshift-pipelines-controller` container. The OpenShift Pipelines Operator also updates the`buckets` field in the`config-leader-election` config map under the`openshift-pipelines` namespace.

If you do not specify the values, the OpenShift Pipelines Operator does not update those fields and applies the default values for the OpenShift Pipelines controller.

Note

If you change or remove any of the performance fields, the OpenShift Pipelines Operator updates the`openshift-pipelines-controller` deployment and the`config-leader-election` config map (if the`buckets` field changed) and re-creates`openshift-pipelines-controller` pods.

High-availability (HA) mode applies to the OpenShift Pipelines controller, which creates and starts pods based on pipeline run and task run definitions. Without HA mode, a single pod executes these operations, potentially creating significant delays under a high load.

In HA mode, OpenShift Pipelines uses several pods (replicas) to run these operations. Initially, OpenShift Pipelines assigns every controller operation into a bucket. Each replica picks operations from one or more buckets. If two replicas could pick the same operation at the same time, the controller internally determines a leader that executes this operation.

HA mode does not affect execution of task runs after the pods are created.

Expand

`disable-ha`

Enable or disable the high availability (HA) mode. By default, the HA mode is enabled.

`false`

`buckets`

In HA mode, the number of buckets used to process controller operations. The maximum value is`10`

`1`

`replicas`

In HA mode, the number of pods created to process controller operations. Set this value to the same or lower number than the`buckets` value.

`1`

`threads-per-controller`

The number of threads (workers) to use when the work queue of the OpenShift Pipelines controller is processed.

`2`

`kube-api-qps`

The maximum queries per second (QPS) to the cluster control plane from the REST client.

`5.0`

`kube-api-burst`

The maximum burst for a throttle.

`10`

| Table 3.1. Modifiable fields for tuning OpenShift Pipelines performance | Name | Description | Default value for the OpenShift Pipelines controller |
| --- | --- | --- | --- |

Show more

Note

The OpenShift Pipelines Operator does not control the number of replicas of the OpenShift Pipelines controller. The`replicas` setting of the deployment determines the number of replicas. For example, to change the number of replicas to 3, enter the following command:

```shell-session
$ oc --namespace openshift-pipelines scale deployment openshift-pipelines-controller --replicas=3
```

Important

The`kube-api-qps` and`kube-api-burst` fields are multiplied by 2 in the OpenShift Pipelines controller. For example, if the`kube-api-qps` and`kube-api-burst` values are`10`, the actual QPS and burst values become`20`.

### 3.3. Configuring the Red Hat OpenShift Pipelines control planeCopy link

You can configure the OpenShift Pipelines control plane to suit your operational needs by editing the`TektonConfig` custom resource (CR). Customize settings such as metrics collection, sidecar injection, and service account defaults directly through the OpenShift Container Platform web console as needed.

Procedure

Edit the`TektonConfig` YAML file based on your requirements.

Example TektonConfig CR

```yaml
apiVersion: operator.tekton.dev/v1alpha1
kind: TektonConfig
metadata:
  name: config
spec:
  pipeline:
    running-in-environment-with-injected-sidecars: true
    metrics.taskrun.duration-type: histogram
    metrics.pipelinerun.duration-type: histogram
    await-sidecar-readiness: true
    params:
      - name: enableMetrics
        value: 'true'
    default-service-account: pipeline
    require-git-ssh-secret-known-hosts: false
    enable-tekton-oci-bundles: false
    metrics.taskrun.level: task
    metrics.pipelinerun.level: pipeline
    enable-api-fields: stable
    enable-provenance-in-status: false
    enable-custom-tasks: true
    disable-creds-init: false
    disable-affinity-assistant: true
```

1. In the Administrator perspective of the web console, navigate to Administration → CustomResourceDefinitions.
2. Use the Search by name box to search for the`tektonconfigs.operator.tekton.dev` custom resource definition (CRD). Click TektonConfig to see the CRD details page.
3. Click the Instances tab.
4. Click the config instance to see the`TektonConfig` CR details.
5. Click the YAML tab.

#### 3.3.1. Modifiable fields with default valuesCopy link

You can change various default configuration fields in the`TektonConfig` custom resource (CR) to tailor the behavior of your pipelines. This reference lists the available fields, such as sidecar injection and metric levels, along with their default values and descriptions.

The following list includes all modifiable fields with their default values in the`TektonConfig` CR:

`running-in-environment-with-injected-sidecars`(default:`true`): Set this field to`false` if pipelines run in a cluster that does not use injected sidecars, such as Istio. Setting it to`false` decreases the time a pipeline takes for a task run to start.

Note

For clusters that use injected sidecars, setting this field to`false` can lead to an unexpected behavior.

`require-git-ssh-secret-known-hosts`(default:`false`): Setting this field to`true` requires that any Git SSH secret must include the`known_hosts` field.

- For more information about configuring Git SSH secrets, see Configuring SSH authentication for Git in the Additional resources section.

`enable-api-fields`(default:`stable`): You can enable or disable API fields. Acceptable values are`stable`,`beta`, or`alpha`.

Note

Red Hat OpenShift Pipelines does not support the`alpha` value.

You can modify the default values of the following metrics fields in the`TektonConfig` CR:

- `disable-affinity-assistant`(default:`true`): Set this field to`false` to enable affinity assistant for each`TaskRun` resource sharing a persistent volume claim workspace.
- `metrics.taskrun.duration-type` and`metrics.pipelinerun.duration-type`(default:`histogram`): Setting these fields determines the duration type for a task or pipeline run. Acceptable value is`gauge` or`histogram`.
- `metrics.taskrun.level`(default:`task`): This field determines the level of the task run metrics. Acceptable value is`taskrun`,`task`, or`namespace`.
- `metrics.pipelinerun.level`(default:`pipeline`): This field determines the level of the pipeline run metrics. Acceptable value is`pipelinerun`,`pipeline`, or`namespace`.

#### 3.3.2. Optional configuration fieldsCopy link

You can configure optional fields in the`TektonConfig` custom resource (CR) to enable advanced features or override specific defaults. These fields, such as default timeouts and pod templates, are not set by default and allow for fine-grained control over your pipeline execution environment.

The following fields do not have a default value, and are considered only if you configure them. By default, the Operator does not add and configure these fields in the`TektonConfig` custom resource (CR).

- `default-timeout-minutes`: This field sets the default timeout for the`TaskRun` and`PipelineRun` resources, if none is specified when creating them. If a task run or pipeline run takes more time than the set number of minutes for its execution, then the task run or pipeline run is timed out and canceled. For example,`default-timeout-minutes: 60` sets 60 minutes as default.
- `default-managed-by-label-value`: This field contains the default value given to the`app.kubernetes.io/managed-by` label that is applied to all`TaskRun` pods, if none is specified. For example,`default-managed-by-label-value: tekton-pipelines`.
- `default-pod-template`: This field sets the default`TaskRun` and`PipelineRun` pod templates, if none is specified.
- `default-cloud-events-sink`: This field sets the default`CloudEvents` sink that is used for the`TaskRun` and`PipelineRun` resources, if none is specified.
- `default-task-run-workspace-binding`: This field contains the default workspace configuration for the workspaces that a`Task` resource declares, but a`TaskRun` resource does not explicitly declare.
- `default-affinity-assistant-pod-template`: This field sets the default`PipelineRun` pod template that is used for affinity assistant pods, if none is specified.
- `default-max-matrix-combinations-count`: This field contains the default maximum number of combinations generated from a matrix, if none is specified.

### 3.4. Changing the default service account for OpenShift PipelinesCopy link

You can change the default service account used by OpenShift Pipelines for task and pipeline runs to meet your security or operational requirements. By editing the`TektonConfig` custom resource (CR), you can specify a different service account for pipelines and triggers.

Example TektonConfig CR

```yaml
apiVersion: operator.tekton.dev/v1alpha1
kind: TektonConfig
metadata:
  name: config
spec:
  pipeline:
    default-service-account: pipeline
  trigger:
    default-service-account: pipeline
    enable-api-fields: stable
```

### 3.5. Setting labels and annotations for the OpenShift Pipelines installation namespaceCopy link

You can apply custom labels and annotations to the`openshift-pipelines` namespace to integrate with your organization’s metadata standards or tools. You can configure these metadata fields in the`TektonConfig` custom resource (CR) and apply them.

Note

Changing the name of the`openshift-pipelines` namespace is not supported.

Specify the labels and annotations by adding them to the`spec.targetNamespaceMetadata` specification in the`TektonConfig` custom resource (CR).

Example of setting labels and annotations for the`openshift-pipelines` namespace

```yaml
apiVersion: operator.tekton.dev/v1
kind: TektonConfig
metadata:
  name: config
spec:
  targetNamespaceMetadata:
    labels: {"example-label":"example-value"}
    annotations: {"example-annotation":"example-value"}
```

### 3.6. Setting the resync period for the pipelines controllerCopy link

You can configure the resync period for the pipelines controller to optimize resource usage in clusters with a large number of pipeline and task runs. By adjusting this interval in the`TektonConfig` custom resource, you control how often the controller reconciles all resources regardless of events.

The default resync period is 10 hours. If you have a large number of pipeline runs and task runs, a full reconciliation every 10 hours might consume too many resources. In this case, you can configure a longer resync period.

Prerequisites

- You are logged in to your OpenShift Container Platform cluster with`cluster-admin` privileges.

Procedure

In the`TektonConfig` custom resource, configure the resync period for the pipelines controller, as shown in the following example.

Example

```yaml
apiVersion: operator.tekton.dev/v1alpha1
kind: TektonConfig
metadata:
  name: config
spec:
  pipeline:
    options:
      deployments:
        tekton-pipelines-controller:
          spec:
            template:
              spec:
                containers:
                - name: tekton-pipelines-controller
                  args:
                    - "-resync-period=24h"
```

`name.args` This example sets the resync period to 24 hours.

### 3.7. Disabling the service monitorCopy link

You can disable the service monitor in OpenShift Pipelines if you do not need to expose telemetry data or want to reduce resource consumption. This configuration is managed by setting the`enableMetrics` parameter to`false` in the`TektonConfig` custom resource (CR).

Example

```yaml
apiVersion: operator.tekton.dev/v1alpha1
kind: TektonConfig
metadata:
  name: config
spec:
  pipeline:
    params:
       - name: enableMetrics
         value: 'false'
```

### 3.8. Configuring pipeline resolversCopy link

You can enable or disable specific pipeline resolvers, such as git, cluster, bundle, and hub resolvers, to control how your pipelines fetch resources. These settings are managed within the`TektonConfig` custom resource (CR), where you can also provide resolver-specific configurations.

- `enable-bundles-resolver`
- `enable-cluster-resolver`
- `enable-git-resolver`
- `enable-hub-resolver`

Example

```yaml
apiVersion: operator.tekton.dev/v1alpha1
kind: TektonConfig
metadata:
  name: config
spec:
  pipeline:
    enable-bundles-resolver: true
    enable-cluster-resolver: true
    enable-git-resolver: true
    enable-hub-resolver: true
```

You can also provide resolver specific configurations in the`TektonConfig` CR. For example, define the following fields in the`map[string]string` format to set configurations for each pipeline resolver:

Example

```yaml
apiVersion: operator.tekton.dev/v1alpha1
kind: TektonConfig
metadata:
  name: config
spec:
  pipeline:
    bundles-resolver-config:
      default-service-account: pipelines
    cluster-resolver-config:
      default-namespace: test
    git-resolver-config:
      server-url: localhost.com
    hub-resolver-config:
      default-tekton-hub-catalog: tekton
```

### 3.9. Disabling resolver tasks and pipeline templatesCopy link

You can disable the automatic installation of resolver tasks and pipeline templates to customize your cluster’s initial state. By modifying the`TektonConfig` custom resource (CR), you can prevent these default resources from being deployed if they are not required for your environment.

By default, the`TektonAddon` custom resource (CR) installs`resolverTasks` and`pipelineTemplates` resources along with OpenShift Pipelines on the cluster.

Procedure

Edit the`TektonConfig` CR by running the following command:

```shell-session
$ oc edit TektonConfig config
```

In the`TektonConfig` CR, set the`resolverTasks` and`pipelineTemplates` parameter value in`.addon.params` spec to`false`:

Example of disabling resolver task and pipeline template resources

```yaml
apiVersion: operator.tekton.dev/v1alpha1
kind: TektonConfig
metadata:
  name: config
spec:
# ...
  addon:
    params:
      - name: resolverTasks
        value: 'false'
      - name: pipelineTemplates
        value: 'false'
# ...
```

Important

You can set the value of the`pipelinesTemplates` parameter to`true` only when the value of the`resolverTasks` parameter is`true`.

### 3.10. Disabling the installation of Tekton TriggersCopy link

You can disable the automatic installation of Tekton Triggers during the OpenShift Pipelines deployment to manage triggers separately or exclude them from your environment. This is achieved by setting the`disabled` parameter to`true` in the`TektonConfig` custom resource (CR).

```yaml
apiVersion: operator.tekton.dev/v1alpha1
kind: TektonConfig
metadata:
  name: config
spec:
  trigger:
    disabled: true
#...
```

The default setting is`false`.

### 3.11. Disabling the integration of Tekton HubCopy link

You can disable the Tekton Hub integration in the OpenShift Container Platform web console Developer perspective to customize the user experience. This setting is controlled by the`enable-devconsole-integration` parameter in the`TektonConfig` custom resource (CR).

Example of disabling Tekton Hub

```yaml
apiVersion: operator.tekton.dev/v1alpha1
kind: TektonConfig
metadata:
  name: config
spec:
  hub:
    params:
      - name: enable-devconsole-integration
        value: false
```

### 3.12. Migrating from Tekton Hub to Artifact HubCopy link

Tekton Hub previously provided a hosted catalog of prebuilt Tekton resources, including Tasks and Pipelines. This service is deprecated in favor of Artifact Hub, a centralized catalog for Tekton resources.

Important

- The hub resolver now defaults to the`artifact` type.
- Tekton Hub (`type: tekton`) requires additional configuration to continue functioning.

#### 3.12.1. Assess migration impactCopy link

You must migrate to Artifact Hub to ensure uninterrupted catalog resolution.

You must migrate if:

- Your Tekton resources reference`type: tekton` or`catalog: Tekton`.
- You rely on Tekton Hub as a hosted catalog service.

Use the following scripts to identify which Tekton resources require modification and to verify the hub resolver configuration on your cluster.

```shell-session
# Count resources by type
echo -e "\nResources needing migration:"
find . -type f \( -name "*.yaml" -o -name "*.yml" \) \
  -exec grep -l "value: tekton\|value: Tekton" {} \; \
  | xargs grep "^kind:" | awk '{print $2}' | sort | uniq -c

# Check cluster hub resolver configuration
echo -e "\nHub resolver configuration:"
kubectl get configmap hubresolver-config -n openshift-pipelines \
  -o jsonpath='{.data.default-type}' 2>/dev/null \
  || kubectl get configmap hubresolver-config \
  -n tekton-pipelines-resolvers -o jsonpath='{.data.default-type}' 2>/dev/null \
  || echo "Hub resolver config not found (cluster may not be accessible)"
```

Example output:

```plaintext
Resources needing migration:
   1 Pipeline
   2 TaskRun

Hub resolver configuration:
tekton
```

#### 3.12.2. Migrating to Artifact HubCopy link

You can update existing Tekton resources to use Artifact Hub instead of the deprecated Tekton Hub.

Procedure

Remove the`type: tekton` parameter.

Note

```plaintext
Do not add `type: artifact`. The resolver defaults to the `artifact` type automatically.
```

Update the catalog name to the appropriate Artifact Hub catalog:

- For Tasks: change`catalog: Tekton` to`catalog: tekton-catalog-tasks`
- For Pipelines: change`catalog: Tekton` to`catalog: tekton-catalog-pipelines`
- For StepActions: change`catalog: Tekton` to`catalog: tekton-catalog-stepactions`

Update version values to full semantic versioning (semver).

For example, change a version such as`0.8` to`0.8.0`.

Update your resource definitions accordingly.

The following examples show how to migrate resolver parameters from Tekton Hub to Artifact Hub.

```yaml
params:
  - name: type
    value: tekton            # remove this type and value
  - name: catalog
    value: Tekton            # change to tekton-catalog-tasks
  - name: name
    value: git-clone
```

```yaml
params:
  # type: artifact is the default and does not need to be specified
  - name: catalog
    value: tekton-catalog-tasks
  - name: name
    value: git-clone
```

#### 3.12.3. Configuring a private Artifact Hub instanceCopy link

For disconnected or private environments, configure a custom Artifact Hub endpoint.

Procedure

Update the`hub-resolver-config` value in the following Tekton config example:

```yaml
apiVersion: operator.tekton.dev/v1alpha1
kind: TektonConfig
metadata:
  name: config
spec:
# ...
  pipeline:
    hub-resolver-config:
      default-artifact-hub-url: "https://artifacthub.io"
# ...
```

1. Save the updated files and reapply them to your cluster as needed.

Important

When using a private Artifact Hub:

- Verify network connectivity from the resolver pods.
- Configure TLS certificates for HTTPS endpoints.
- Configure authentication, if required.
- Ensure catalog names match those published in your private hub.

### 3.13. Disabling the automatic creation of RBAC resourcesCopy link

You can disable the automatic creation of cluster-wide RBAC resources by using the Red Hat OpenShift Pipelines Operator to improve security and control over permissions. This is done by setting the`createRbacResource` parameter to`false` in the`TektonConfig` custom resource (CR), preventing the creation of potentially privileged role bindings.

The default installation of the Red Hat OpenShift Pipelines Operator creates multiple role-based access control (RBAC) resources for all namespaces in the cluster, except the namespaces matching the`^(openshift|kube)-*` regular expression pattern. Among these RBAC resources, the`pipelines-scc-rolebinding` security context constraint (SCC) role binding resource is a potential security issue, because the associated`pipelines-scc` SCC has the`RunAsAny` privilege.

Prerequisites

- You have access to the cluster with`cluster-admin` privileges.
- You installed the OpenShift CLI (`oc`).

Procedure

Edit the`TektonConfig` CR by running the following command:

```shell-session
$ oc edit TektonConfig config
```

In the`TektonConfig` CR, set the`createRbacResource` param value to`false`:

```yaml
apiVersion: operator.tekton.dev/v1alpha1
kind: TektonConfig
metadata:
  name: config
spec:
  params:
  - name: createRbacResource
    value: "false"
# ...
```

### 3.14. Disabling inline specification of pipelines and tasksCopy link

You can disable the inline specification of tasks and pipelines to enforce the use of referenced resources and improve security. By configuring the`disable-inline-spec` field in the`TektonConfig` custom resource (CR), you can restrict the use of embedded specs in`Pipeline`,`PipelineRun`, and`TaskRun` resources.

By default, OpenShift Pipelines supports inline specification of pipelines and tasks in the following cases:

You can create a`Pipeline` CR that includes one or more task specifications, as in the following example:

Example of an inline specification in a`Pipeline` CR

```yaml
apiVersion: operator.tekton.dev/v1
kind: Pipeline
metadata:
  name: pipelineInline
spec:
  tasks:
    taskSpec:
# ...
```

You can create a`PipelineRun` custom resource (CR) that includes a pipeline specification, as in the following example:

Example of an inline specification in a`PipelineRun` CR

```yaml
apiVersion: operator.tekton.dev/v1
kind: PipelineRun
metadata:
  name: pipelineRunInline
spec:
  pipelineSpec:
    tasks:
# ...
```

You can create a`TaskRun` custom resource (CR) that includes a task specification, as in the following example:

Example of an inline specification in a`TaskRun` CR

```yaml
apiVersion: operator.tekton.dev/v1
kind: TaskRun
metadata:
  name: taskRunInline
spec:
  taskSpec:
    steps:
# ...
```

You can disable inline specification in some or all of these cases. To disable the inline specification, set the`disable-inline-spec` field of the`.spec.pipeline` specification of the`TektonConfig` CR, as in the following example:

Example configuration that disables inline specification

```yaml
apiVersion: operator.tekton.dev/v1alpha1
kind: TektonConfig
metadata:
  name: config
spec:
  pipeline:
    disable-inline-spec: "pipeline,pipelinerun,taskrun"
# ...
```

You can set the`disable-inline-spec` parameter to any single value or to a comma-separated list of multiple values. The following values for the parameter are valid:

Expand

`pipeline`

You cannot use a`taskSpec:` spec to define a task inside a`Pipeline` CR. Instead, you must use a`taskRef:` spec to incorporate a task from a`Task` CR or to specify a task using a resolver.

`pipelinerun`

You cannot use a`pipelineSpec:` spec to define a pipeline inside a`PipelineRun` CR. Instead, you must use a`pipelineRef:` spec to incorporate a pipeline from a`Pipeline` CR or to specify a pipeline using a resolver.

`taskrun`

You cannot use a`taskSpec:` spec to define a task inside a`TaskRun` CR. Instead, you must use a`taskRef:` spec to incorporate a task from a`Task` CR or to specify a task using a resolver.

| Table 3.2. Supported values for the disable-inline-spec parameter | Value | Description |
| --- | --- | --- |

Show more

### 3.15. Configuration of RBAC and Trusted CA flagsCopy link

You can independently control the creation of RBAC resources and Trusted CA bundle config maps to customize your OpenShift Pipelines installation. The`TektonConfig` custom resource (CR) provides specific flags,`createRbacResource` and`createCABundleConfigMaps`, to manage these components separately.

Expand

`createRbacResource`

Controls the creation of RBAC resources only. This flag does not affect Trusted CA bundle config map.

`true`

`createCABundleConfigMaps`

Controls the creation of Trusted CA bundle config map and Service CA bundle config map. This flag must be set to`false` to disable config map creation.

`true`

| | Parameter | Description | Default value |
| --- | --- | --- | --- |

Show more

```yaml
apiVersion: operator.tekton.dev/v1alpha1
kind: TektonConfig
metadata:
  name: config
spec:
  profile: all
  targetNamespace: openshift-pipelines
  params:
    - name: createRbacResource
      value: "true"
    - name: createCABundleConfigMaps
      value: "true"
    - name: legacyPipelineRbac
      value: "true"
```

`params[0].name` Specifies RBAC resource creation.`params[1].name` Specifies Trusted CA bundle config map creation.

### 3.16. Automatic pruning of task runs and pipeline runsCopy link

You can automatically prune stale`TaskRun` and`PipelineRun` resources to free up cluster resources and maintain optimal performance. Red Hat OpenShift Pipelines provides a configurable pruner component that removes unused objects based on your defined policies.

Note

You can configure the pruner for your entire installation by using the`TektonConfig` custom resource and modify configuration for a namespace by using namespace annotations. However, you cannot selectively auto-prune an individual task run or pipeline run in a namespace.

#### 3.16.1. Configuring the prunerCopy link

You can configure the default pruner to automatically remove old`TaskRun` and`PipelineRun` resources based on a schedule or resource count. By modifying the`TektonConfig` custom resource (CR), you can set retention limits and pruning intervals to manage resource usage.

The following example corresponds to the default configuration:

Example of the pruner configuration

```yaml
apiVersion: operator.tekton.dev/v1alpha1
kind: TektonConfig
metadata:
  name: config
# ...
spec:
  pruner:
    resources:
      - taskrun
      - pipelinerun
    keep: 100
    prune-per-resource: false
    schedule: "* 8 * * *"
    startingDeadlineSeconds: 60
# ...
```

Expand

`schedule`

The Cron schedule for running the pruner process. The default schedule runs the process at 08:00 every day. For more information about the Cron schedule syntax, see [Cron schedule syntax](https://kubernetes.io/docs/concepts/workloads/controllers/cron-jobs/#cron-schedule-syntax) in the Kubernetes documentation.

`resources`

The resource types to which the pruner applies. The available resource types are`taskrun` and`pipelinerun`

`keep`

The number of most recent resources of every type to keep.

`prune-per-resource`

If set to`false`, the value for the`keep` parameter denotes the total number of task runs or pipeline runs. For example, if`keep` is set to`100`, then the pruner keeps 100 most recent task runs and 100 most recent pipeline runs and removes all other resources.

If set to`true`, the value for the`keep` parameter is calculated separately for pipeline runs referencing each pipeline and for task runs referencing each task. For example, if`keep` is set to`100`, then the pruner keeps 100 most recent pipeline runs for`Pipeline1`, 100 most recent pipeline runs for`Pipeline2`, 100 most recent task runs for`Task1`, and so on, and removes all other resources.

`keep-since`

The maximum time for which to keep resources, in minutes. For example, to retain resources which were created not more than five days ago, set`keep-since` to`7200`.

`startingDeadlineSeconds`

This parameter is optional. If the pruner job is not started at the scheduled time for any reason, this setting configures the maximum time, in seconds, in which the job can still be started. If the job is not started within the specified time, OpenShift Pipelines considers this job failed and starts the pruner at the next scheduled time. If you do not specify this parameter and the pruner job does not start at the scheduled time, OpenShift Pipelines attempts to start the job at any later time possible.

| Table 3.3. Supported parameters for pruner configuration | Parameter | Description |
| --- | --- | --- |

Show more

Note

The`keep` and`keep-since` parameters are mutually exclusive. Use only one of them in your configuration.

#### 3.16.2. Annotations for automatically pruning task runs and pipeline runsCopy link

You can customize the pruning behavior for specific namespaces by applying annotations to the`Namespace` resource. These annotations allow you to override global pruning settings, such as retention limits and schedules, for individual projects.

The following namespace annotations have the same meanings as the corresponding keys in the`TektonConfig` custom resource:

- `operator.tekton.dev/prune.schedule`
- `operator.tekton.dev/prune.resources`
- `operator.tekton.dev/prune.keep`
- `operator.tekton.dev/prune.prune-per-resource`
- `operator.tekton.dev/prune.keep-since`

Note

The`operator.tekton.dev/prune.resources` annotation accepts a comma-separated list. To prune both task runs and pipeline runs, set this annotation to`"taskrun, pipelinerun"`.

The following additional namespace annotations are available:

- `operator.tekton.dev/prune.skip`: When set to`true`, the namespace for which the annotation is configured is not pruned.
- `operator.tekton.dev/prune.strategy`: Set the value of this annotation to either`keep` or`keep-since`.

For example, the following annotations retain all task runs and pipeline runs created in the last five days and delete the older resources:

Example of auto-pruning annotations

```yaml
kind: Namespace
apiVersion: v1
# ...
metadata:
  annotations:
    operator.tekton.dev/prune.resources: "taskrun, pipelinerun"
    operator.tekton.dev/prune.keep-since: 7200
# ...
```

### 3.17. Enabling the event-driven prunerCopy link

You can enable the event-based pruner to delete completed`PipelineRun` and`TaskRun` resources in near real-time. By configuring the`tektonpruner` controller in the`TektonConfig` custom resource (CR), you can replace the default scheduled pruner with an event-driven approach for more immediate resource cleanup.

Important

You must disable the job-based pruner in the`TektonConfig` custom resource (CR) before you enable the event-driven pruner. If both pruner types are enabled, the deployment readiness status changes to`False` and the following error message is displayed on the output:

```shell-session
Components not in ready state: Invalid Pruner Configuration!! Both pruners, tektonpruner(event based) and pruner(job based) cannot be enabled simultaneously. Please disable one of them.
```

Procedure

In your TektonConfig CR, disable the job-based pruner by setting`spec.pruner.disabled` field to`true` and enable the event-driven pruner by setting the`spec.tektonpruner.disabled` field to`false`.

For example:

```yaml
apiVersion: operator.tekton.dev/v1alpha1
kind: TektonConfig
metadata:
 name: config
spec:
  # ...
  pruner:
    disabled: true
  # ...
  tektonpruner:
    disabled: false
    options: {}
  # ...
```

After you apply the updated CR, the Operator deploys the`tekton-pruner-controller` pod in the`openshift-pipelines` namespace.

Ensure that the following config maps are present in the`openshift-pipelines` namespace:

Expand

`tekton-pruner-default-spec`

Define default pruning behavior

`pruner-info`

Store internal runtime data used by the controller

`config-logging-tekton-pruner`

Configure logging settings for the pruner

`config-observability-tekton-pruner`

Enable observability features such as metrics and tracing

| | Config map | Purpose |
| --- | --- | --- |

Show more

Verification

To verify that the`tekton-pruner-controller` pod is running, run the following command:

```shell-session
$ oc get pods -n openshift-pipelines
```

Verify that the output includes a`tekton-pruner-controller` and`tekton-pruner-webhook` pods in the`Running` state.

Example output:

```shell-session
$ tekton-pruner-controller-<id>       Running
  tekton-pruner-webhook-<id>          Running
```

#### 3.17.1. Configuration of the event-driven prunerCopy link

You can fine-tune the event-based pruner by adjusting settings in the`TektonConfig` custom resource (CR). This reference details the available configuration options, including history limits, time-to-live (TTL) values, and namespace-specific policies.

The following is an example of the`TektonConfig` CR with the default configuration that uses global pruning rules:

```yaml
apiVersion: operator.tekton.dev/v1alpha1
kind: TektonConfig
metadata:
 name: config
spec:
  # ...
  tektonpruner:
    disabled: false
    global-config:
      enforcedConfigLevel: global
      failedHistoryLimit: null
      historyLimit: 10
      namespaces: null
      successfulHistoryLimit: null
      ttlSecondsAfterFinished: null
    options: {}
  # ...
```

`failedHistoryLimit` The amount of retained failed runs.`historyLimit` The amount of runs to retain. Pruner uses this setting if status-specific limits are not defined.`namespaces` Definition of per-namespace pruning policies, when you set`enforcedConfigLevel` to`namespace`.`successfulHistoryLimit` The amount of retained successful runs.`ttlSecondsAfterFinished` Time in seconds after completion, after which the pruner deletes resources.

You can define pruning rules for individual namespaces by setting`enforcedConfigLevel` to`namespace` and configuring policies under the`namespaces` section. In the following example, a 60 second time to live (TTL) is applied to resources in the`dev-project` namespace:

```yaml
apiVersion: operator.tekton.dev/v1alpha1
kind: TektonConfig
metadata:
 name: config
spec:
  # ...
  tektonpruner:
    disabled: false
    global-config:
      enforcedConfigLevel: namespace
      ttlSecondsAfterFinished: 300
      namespaces:
        dev-project:
          ttlSecondsAfterFinished: 60
  # ...
```

You can use the following parameters in your`TektonConfig` CR`tektonpruner`:

Expand

`ttlSecondsAfterFinished`

Delete resources a fixed number of seconds after they complete.

`successfulHistoryLimit`

Retain the specified number of the most recent successful runs. Delete older successful runs.

`failedHistoryLimit`

Retain the specified number of the most recent failed runs. Delete older failed runs.

`historyLimit`

Apply a generic history limit when`failedHistoryLimit` and`successfulHistoryLimit` are not defined.

`enforcedConfigLevel`

Specify the level at which pruner applies the configuration. Accepted values:`global` or`namespace`.

`namespaces`

Define per-namespace pruning policies.

| | Parameter | Description |
| --- | --- | --- |

Show more

Note

You can use TTL-based pruning to prune resources exceeding set expiration times. Use history-based pruning to prune resources exceeding the configured`historyLimit`. TTL and history limits operate independently.

Global configuration of the event-driven pruner

The following example shows the default`TektonConfig` CR configuration, which applies global pruning rules:

```yaml
apiVersion: operator.tekton.dev/v1alpha1
kind: TektonConfig
metadata:
 name: config
spec:
  # ...
  tektonpruner:
    disabled: false
    global-config:
      enforcedConfigLevel: global
      failedHistoryLimit: 5
      historyLimit: 10
      successfulHistoryLimit: 5
      ttlSecondsAfterFinished: 3600
    options: {}
  # ...
```

- `failedHistoryLimit`: The amount of retained failed runs.
- `historyLimit`: The amount of runs to retain. Pruner uses this setting if status-specific limits are not defined.
- `successfulHistoryLimit`: The amount of retained successful runs.
- `ttlSecondsAfterFinished`: Time in seconds after completion, after which the pruner deletes resources.

Namespace-level configuration of the event-driven pruner

You can define pruning rules for individual namespaces by setting`enforcedConfigLevel` to`namespace` and configuring policies under the`namespaces` section. In the following example, a 60 second time to live (TTL) is applied to resources in the`dev-project` and`staging` namespaces:

```yaml
apiVersion: operator.tekton.dev/v1alpha1
kind: TektonConfig
metadata:
 name: config
spec:
  # ...
  tektonpruner:
    disabled: false
    global-config:
      enforcedConfigLevel: namespace
      ttlSecondsAfterFinished: 300
      namespaces:
        dev-project:
          ttlSecondsAfterFinished: 60
        staging:
          ttlSecondsAfterFinished: 60
  # ...
```

Resource-level configuration of the event-driven pruner

If you have configured the namespace-level configuration of the event-driven pruner, you can further configure resource-level pruning rules by creating a`tekton-pruner-namespace-spec` config map in your namespace. Resource-level rules take precedence over global and namespace-level pruning configuration when defined for a specific resource type.

When multiple config maps apply to the same resource, the event-driven pruner applies the most specific rule.

The following example defines a TTL and a history limit for both`PipelineRun` and`TaskRun` resources:

```yaml
apiVersion: v1
kind: ConfigMap
metadata:
  name: tekton-pruner-namespace-spec
  namespace: user-specified-namespace
  labels:
    app.kubernetes.io/part-of: tekton-pruner
    pruner.tekton.dev/config-type: namespace
data:
  ns-config: |
    ttlSecondsAfterFinished: 300
    historyLimit: 5
```

The`tekton-pruner-namespace-spec` name and the`app.kubernetes.io/part-of: tekton-pruner` and`pruner.tekton.dev/config-type: namespace` labels are required on all resource-level pruning config maps for the pruner controller to process them correctly. Config maps missing these labels or using an incorrect name are ignored.

Resource-level configuration of the event-driven pruner with selectors

In the following example, the pruning rule applies only if the resource has both the`priority: high` label and the`compliance: required` annotation. Resources that do not match this selector fall back to the namespace default or other selectors with lower specificity:

```yaml
apiVersion: v1
kind: ConfigMap
metadata:
  name: tekton-pruner-namespace-spec
  namespace: user-specified-namespace
  labels:
    app.kubernetes.io/part-of: tekton-pruner
    pruner.tekton.dev/config-type: namespace
data:
  ns-config: |
    ttlSecondsAfterFinished: 3600

    pipelineRuns:
      - selector:
        - matchLabels:
            priority: high
          matchAnnotations:
            compliance: required
        ttlSecondsAfterFinished: 7776000
        successfulHistoryLimit: 100
        failedHistoryLimit: 100
```

Common values

The following tables provide recommended values for TTL and history limits for`PipelineRuns` and`TaskRuns`. Use these values to help configure pruning policies that balance cluster performance, resource retention, and operational requirements.

Expand

5 minutes

300

Development and testing with rapid iteration

30 minutes

1800

Short-lived experiments

1 hour

3600

CI pipelines

6 hours

21600

Daily builds

1 day

86400

Staging environments

7 days

604800

Production, short retention

30 days

2592000

Compliance, auditing

90 days

7776000

Regulated industries

| | Time period | Seconds | Use case |
| --- | --- | --- | --- |

Show more

Expand

Development

3-5

5-10

Increase feedback turnaround and reduce storage requirements

Staging

5-10

10-20

Balance retention and resources

Production

10-50

20-100

Audit trial and debugging

CI/CD

3-5

10-20

Provide recent context for failure analysis

| | Environment | `successfulHistoryLimit` | `failedHistoryLimit` | Reason |
| --- | --- | --- | --- | --- |

Show more

#### 3.17.2. Observability metrics of the event-driven prunerCopy link

You can monitor the performance and health of the event-based pruner by using the metrics exposed by the`tekton-pruner-controller`. These metrics, available in OpenTelemetry format, provide insights into resource processing, error rates, and reconciliation times for effective troubleshooting and capacity planning.

Resource-level pruning rules configured by using config maps in individual namespaces also emit metrics by using the same labels, allowing you to track pruning at finer granularity.

The following bullets are categories of the metrics exposed:

- Resource processing
- Performance timing
- State tracking
- Error monitoring

Most pruner metrics use labels to provide additional context. You can use these labels in PromQL queries or dashboards to filter and group the metrics.

Expand

`namespace`

The Kubernetes namespace of the`PipelineRun` or`TaskRun`.

`resource_type`

The Tekton resource type.

`status`

The outcome of processing a resource.

`operation`

The pruning method that deleted a resource.

`reason`

Specific cause for skipping or error outcomes.

| | Label | Description |
| --- | --- | --- |

Show more

Resource processing metrics

The following resource processing metrics are exposed by the event-driven pruner:

Expand

`tekton_pruner_controller_resources_processed_total`

Counter

Total resources processed

namespace, resource_type, status

`tekton_pruner_controller_resources_deleted_total`

Counter

Total resources deleted

namespace, resource_type, operation

| | Name | Type | Description | Labels |
| --- | --- | --- | --- | --- |

Show more

Performance timing metrics

The following performance timing metrics are exposed by the event-driven pruner:

Expand

`tekton_pruner_controller_reconciliation_duration_seconds`

Histogram

Time spent in reconciliation

namespace, resource_type

0.1 to 30 seconds

`tekton_pruner_controller_ttl_processing_duration_seconds`

Histogram

Time spent processing TTL

namespace, resource_type

0.1 to 30 seconds

`tekton_pruner_controller_history_processing_duration_seconds`

Histogram

Time spent processing history limits

namespace, resource_type

0.1 to 30 seconds

| | Name | Type | Description | Labels | Bucket |
| --- | --- | --- | --- | --- | --- |

Show more

State tracking metrics

The following state tracking metrics are exposed by the event-driven pruner:

Expand

`kn_workqueue_adds_total`

Counter

Total resources queued

`kn_workqueue_depth`

Gauge

Number of current items in queue

| | Name | Type | Description |
| --- | --- | --- | --- |

Show more

Error monitoring metrics

The following error monitoring metrics are exposed by the event-driven pruner:

Expand

`tekton_pruner_controller_resources_errors_total`

Counter

Total processing errors

namespace, resource_type, reason

| | Name | Type | Description | Labels |
| --- | --- | --- | --- | --- |

Show more

### 3.18. Setting additional options for webhooksCopy link

You can configure advanced webhook options, such as failure policies and timeouts, for OpenShift Pipelines controllers to improve stability and error handling. These settings are applied by using the`TektonConfig` custom resource (CR) and allow you to customize how admission controllers interact with the Kubernetes API server.

Prerequisites

- You installed the`oc` command-line utility.
- You have logged in to your OpenShift Container Platform cluster with administrator rights for the namespace in which OpenShift Pipelines is installed, typically the`openshift-pipelines` namespace.

Procedure

View the list of webhooks that the OpenShift Pipelines controllers created. There are two types of webhooks: mutating webhooks and validating webhooks.

To view the list of mutating webhooks, enter the following command:

```shell-session
$ oc get MutatingWebhookConfiguration
```

Example output

```shell-session
NAME                             WEBHOOKS   AGE
annotation.operator.tekton.dev   1          4m20s
proxy.operator.tekton.dev        1          4m20s
webhook.operator.tekton.dev      1          4m22s
webhook.pipeline.tekton.dev      1          4m20s
webhook.triggers.tekton.dev      1          3m50s
```

To view the list of validating webhooks, enter the following command:

```shell-session
$ oc get ValidatingWebhookConfiguration
```

Example output

```shell-session
NAME                                                 WEBHOOKS   AGE
config.webhook.operator.tekton.dev                   1          4m24s
config.webhook.pipeline.tekton.dev                   1          4m22s
config.webhook.triggers.tekton.dev                   1          3m52s
namespace.operator.tekton.dev                        1          4m22s
validation.pipelinesascode.tekton.dev                1          2m49s
validation.webhook.operator.tekton.dev               1          4m24s
validation.webhook.pipeline.tekton.dev               1          4m22s
validation.webhook.triggers.tekton.dev               1          3m52s
```

In the`TektonConfig` custom resource (CR), add configuration for mutating and validating webhooks under the section for each of the controllers as necessary, as shown in the following examples. Use the`validation.webhook.pipeline.tekton.dev` spec for the validating webhooks and the`webhook.pipeline.tekton.dev` spec for the mutating webhooks.

Important

- You cannot set configuration for`operator` webhooks.
- All settings are optional. For example, you can set the`timeoutSeconds` parameter and omit the`failurePolicy` and`sideEffects` parameters.

Example settings for the Pipelines controller

```yaml
apiVersion: operator.tekton.dev/v1
kind: TektonConfig
metadata:
  name: config
spec:
  pipeline:
    options:
      webhookConfigurationOptions:
        validation.webhook.pipeline.tekton.dev:
          failurePolicy: Fail
          timeoutSeconds: 20
          sideEffects: None
        webhook.pipeline.tekton.dev:
          failurePolicy: Fail
          timeoutSeconds: 20
          sideEffects: None
```

Example settings for the Triggers controller

```yaml
apiVersion: operator.tekton.dev/v1
kind: TektonConfig
metadata:
  name: config
spec:
  triggers:
    options:
      webhookConfigurationOptions:
        validation.webhook.triggers.tekton.dev:
          failurePolicy: Fail
          timeoutSeconds: 20
          sideEffects: None
        webhook.triggers.tekton.dev:
          failurePolicy: Fail
          timeoutSeconds: 20
          sideEffects: None
```

Example settings for the Pipelines as Code controller

```yaml
apiVersion: operator.tekton.dev/v1
kind: TektonConfig
metadata:
  name: config
spec:
  pipelinesAsCode:
    options:
      webhookConfigurationOptions:
        validation.pipelinesascode.tekton.dev:
          failurePolicy: Fail
          timeoutSeconds: 20
          sideEffects: None
        pipelines.triggers.tekton.dev:
          failurePolicy: Fail
          timeoutSeconds: 20
          sideEffects: None
```

Example settings for the Tekton Hub controller

```yaml
apiVersion: operator.tekton.dev/v1
kind: TektonConfig
metadata:
  name: config
spec:
  hub:
    options:
      webhookConfigurationOptions:
        validation.webhook.hub.tekton.dev:
          failurePolicy: Fail
          timeoutSeconds: 20
          sideEffects: None
        webhook.hub.tekton.dev:
          failurePolicy: Fail
          timeoutSeconds: 20
          sideEffects: None
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



## Securing OpenShift Pipelines

Securing OpenShift Pipelines | Red Hat OpenShift Pipelines | 1.21 | Red Hat Documentation

 

 

 

 

 

 

 

 

# Securing OpenShift Pipelines

---

Red Hat OpenShift Pipelines 1.21

## Security features of OpenShift Pipelines

Red Hat OpenShift Documentation Team

Legal Notice

Abstract

This document provides information about security features of OpenShift Pipelines.

---

## Chapter 1. Using Tekton Chains for OpenShift Pipelines supply chain securityCopy link

Tekton Chains is a Kubernetes Custom Resource Definition (CRD) controller. You can use it to manage the supply chain security of the tasks and pipelines created using Red Hat OpenShift Pipelines.

By default, Tekton Chains observes all task run executions in your OpenShift Container Platform cluster. When the task runs complete, Tekton Chains takes a snapshot of the task runs. It then converts the snapshot to one or more standard payload formats, and finally signs and stores all artifacts.

To capture information about task runs, Tekton Chains uses`Result` objects. When the objects are unavailable, Tekton Chains the URLs and qualified digests of the OCI images.

### 1.1. Key featuresCopy link

- Legal Notice
- You can sign task runs, task run results, and OCI registry images with cryptographic keys that are generated by tools such as`cosign` and`skopeo`.
- You can use attestation formats such as`in-toto`.
- You can securely store signatures and signed artifacts using OCI repository as a storage backend.

### 1.2. Configuring Tekton ChainsCopy link

The Red Hat OpenShift Pipelines Operator installs Tekton Chains by default. You can configure Tekton Chains by modifying the`TektonConfig` custom resource; the Operator automatically applies the changes that you make in this custom resource.

To edit the custom resource, use the following command:

```shell-session
$ oc edit TektonConfig config
```

The custom resource includes a`chain:` array. You can add any supported configuration parameters to this array, as shown in the following example:

```yaml
apiVersion: operator.tekton.dev/v1alpha1
kind: TektonConfig
metadata:
  name: config
spec:
  addon: {}
  chain:
    artifacts.taskrun.format: tekton
  config: {}
```

#### 1.2.1. Supported parameters for Tekton Chains configurationCopy link

Cluster administrators can use various supported parameter keys and values to configure specifications about task runs, OCI images, and storage.

##### 1.2.1.1. Supported parameters for task run artifactsCopy link

Expand

`artifacts.taskrun.format`

The format for storing task run payloads.

`in-toto`,`slsa/v1`

`in-toto`

`artifacts.taskrun.storage`

The storage backend for task run signatures. You can specify multiple backends as a comma-separated list, such as`“tekton,oci”`. To disable storing task run artifacts, provide an empty string`“”`.

`tekton`,`oci`,`gcs`,`docdb`,`grafeas`

`oci`

`artifacts.taskrun.signer`

The signature backend for signing task run payloads.

`x509`,`kms`

`x509`

| Table 1.1. Chains configuration: Supported parameters for task run artifacts | Key | Description | Supported values | Default value |
| --- | --- | --- | --- | --- |

Show more

Note

`slsa/v1` is an alias of`in-toto` for backwards compatibility.

##### 1.2.1.2. Supported parameters for pipeline run artifactsCopy link

Expand

`artifacts.pipelinerun.format`

The format for storing pipeline run payloads.

`in-toto`,`slsa/v1`

`in-toto`

`artifacts.pipelinerun.storage`

The storage backend for storing pipeline run signatures. You can specify multiple backends as a comma-separated list, such as`“tekton,oci”`. To disable storing pipeline run artifacts, provide an empty string`“”`.

`tekton`,`oci`,`gcs`,`docdb`,`grafeas`

`oci`

`artifacts.pipelinerun.signer`

The signature backend for signing pipeline run payloads.

`x509`,`kms`

`x509`

`artifacts.pipelinerun.enable-deep-inspection`

When this parameter is`true`, Tekton Chains records the results of the child task runs of a pipeline run. When this parameter is`false`, Tekton Chains records the results of the pipeline run, but not of its child task runs.

`"true", "false"`

`"false"`

| Table 1.2. Chains configuration: Supported parameters for pipeline run artifacts | Parameter | Description | Supported values | Default value |
| --- | --- | --- | --- | --- |

Show more

Note

- `slsa/v1` is an alias of`in-toto` for backwards compatibility.
- For the`grafeas` storage backend, only Container Analysis is supported. You can not configure the`grafeas` server address in the current version of Tekton Chains.

##### 1.2.1.3. Supported parameters for OCI artifactsCopy link

Expand

`artifacts.oci.format`

The format for storing OCI payloads.

`simplesigning`

`simplesigning`

`artifacts.oci.storage`

The storage backend for storing OCI signatures. You can specify multiple backends as a comma-separated list, such as`“oci,tekton”`. To disable storing OCI artifacts, provide an empty string`“”`.

`tekton`,`oci`,`gcs`,`docdb`,`grafeas`

`oci`

`artifacts.oci.signer`

The signature backend for signing OCI payloads.

`x509`,`kms`

`x509`

| Table 1.3. Chains configuration: Supported parameters for OCI artifacts | Parameter | Description | Supported values | Default value |
| --- | --- | --- | --- | --- |

Show more

##### 1.2.1.4. Supported parameters for KMS signersCopy link

Expand

`signers.kms.kmsref`

The URI reference to a KMS service to use in`kms` signers.

Supported schemes:`gcpkms://`,`awskms://`,`azurekms://`,`hashivault://`. See [Providers](https://docs.sigstore.dev/cosign/key_management/overview/#providers) in the Sigstore documentation for more details.

| Table 1.4. Chains configuration: Supported parameters for KMS signers | Parameter | Description | Supported values | Default value |
| --- | --- | --- | --- | --- |

Show more

##### 1.2.1.5. Supported parameters for storageCopy link

Expand

`storage.gcs.bucket`

The GCS bucket for storage

`storage.oci.repository`

The OCI repository for storing OCI signatures and attestation.

If you configure one of the artifact storage backends to`oci` and do not define this key, Tekton Chains stores the attestation alongside the stored OCI artifact itself. If you define this key, the attestation is not stored alongside the OCI artifact and is instead stored in the designated location. See the [cosign documentation](https://github.com/sigstore/cosign#specifying-registry) for additional information.

`builder.id`

The builder ID to set for in-toto attestations

`https://tekton.dev/chains/v2`

`builddefinition.buildtype`

The build type for in-toto attestation. When this parameter is`https://tekton.dev/chains/v2/slsa`, Tekton Chains records in-toto attestations in strict conformance with the SLSA v1.0 specification. When this parameter is`https://tekton.dev/chains/v2/slsa-tekton`, Tekton Chains records in-toto attestations with additional information, such as the labels and annotations in each`TaskRun` and`PipelineRun` object, and also adds each task in a`PipelineRun` object under`resolvedDependencies`.

`https://tekton.dev/chains/v2/slsa`,`https://tekton.dev/chains/v2/slsa-tekton`

`https://tekton.dev/chains/v2/slsa`

| Table 1.5. Chains configuration: Supported parameters for storage | Parameter | Description | Supported values | Default value |
| --- | --- | --- | --- | --- |

Show more

If you enable the`docdb` storage method is for any artifacts, configure docstore storage options. For more information about the go-cloud docstore URI format, see the [docstore package documentation](https://gocloud.dev/howto/docstore/). Red Hat OpenShift Pipelines supports the following docstore services:

- `firestore`
- `dynamodb`

Expand

`storage.docdb.url`

The go-cloud URI reference to a`docstore` collection. Used if the`docdb` storage method is enabled for any artifacts.

`firestore://projects/[PROJECT]/databases/(default)/documents/[COLLECTION]?name_field=name`

`storage.docdb.mongo-server-url`

The value for the Mongo server URL to use for`docdb` storage (`MONGO_SERVER_URL`). This URL can include authentication information. For production environments, providing authentication information as plain-text configuration might be insecure. Use the alternative`storage.docdb.mongo-server-url-dir` configuration setting for production environments.

`storage.docdb.mongo-server-url-dir`

The directory where a file named`MONGO_SERVER_URL` is located. This file contains the Mongo server URL to use for`docdb` storage (`MONGO_SERVER_URL`). Provide this file as a secret and configure mounting this file for the Tekton Chains controller, as described in Creating and mounting the Mongo server URL secret.

Example value:`/tmp/mongo-url`

| Table 1.6. Chains configuration: Supported parameters for docstore storage | Parameter | Description | Supported values | Default value |
| --- | --- | --- | --- | --- |

Show more

If you enable the`grafeas` storage method for any artifacts, configure Grafeas storage options. For more information about Grafeas notes and occurrences, see [Grafeas concepts](https://github.com/grafeas/grafeas/blob/master/docs/grafeas_concepts.md).

To create occurrences, Red Hat OpenShift Pipelines must first create notes that are used to link occurrences. Red Hat OpenShift Pipelines creates two types of occurrences:`ATTESTATION` Occurrence and`BUILD` Occurrence.

Red Hat OpenShift Pipelines uses the configurable`noteid` as the prefix of the note name. It appends the suffix`-simplesigning` for the`ATTESTATION` note and the suffix`-intoto` for the`BUILD` note. If the`noteid` field is not configured, Red Hat OpenShift Pipelines uses`tekton- ` as the prefix.

Expand

`storage.grafeas.projectid`

The OpenShift Container Platform project in which the Grafeas server for storing occurrences is located.

`storage.grafeas.noteid`

Optional: the prefix to use for the name of all created notes.

A string without spaces.

`storage.grafeas.notehint`

Optional: the [human_readable_name](https://github.com/grafeas/grafeas/blob/cd23d4dc1bef740d6d6d90d5007db5c9a2431c41/proto/v1/attestation.proto#L49) field for the Grafeas`ATTESTATION` note.

`This attestation note was generated by Tekton Chains`

| Table 1.7. Chains configuration: Supported parameters for Grafeas storage | Parameter | Description | Supported values | Default value |
| --- | --- | --- | --- | --- |

Show more

Optionally, you can enable additional uploads of binary transparency attestations.

Expand

`transparency.enabled`

Enable or disable automatic binary transparency uploads.

`true`,`false`,`manual`

`false`

`transparency.url`

The URL for uploading binary transparency attestations, if enabled.

`https://rekor.sigstore.dev`

| Table 1.8. Chains configuration: Supported parameters for transparency attestation storage | Parameter | Description | Supported values | Default value |
| --- | --- | --- | --- | --- |

Show more

Note

If you set`transparency.enabled` to`manual`, only task runs and pipeline runs with the following annotation are uploaded to the transparency log:

```yaml
chains.tekton.dev/transparency-upload: "true"
```

If you configure the`x509` signature backend, you can optionally enable keyless signing with Fulcio.

Expand

`signers.x509.fulcio.enabled`

Enable or disable requesting automatic certificates from Fulcio.

`true`,`false`

`false`

`signers.x509.fulcio.address`

The Fulcio address for requesting certificates, if enabled.

`https://v1.fulcio.sigstore.dev`

`signers.x509.fulcio.issuer`

The expected OIDC issuer.

`https://oauth2.sigstore.dev/auth`

`signers.x509.fulcio.provider`

The provider from which to request the ID Token.

`google`,`spiffe`,`github`,`filesystem`

Red Hat OpenShift Pipelines attempts to use every provider

`signers.x509.identity.token.file`

Path to the file containing the ID Token.

`signers.x509.tuf.mirror.url`

The URL for the TUF server.`$TUF_URL/root.json` must be present.

`https://sigstore-tuf-root.storage.googleapis.com`

| Table 1.9. Chains configuration: Supported parameters for x509 keyless signing with Fulcio | Parameter | Description | Supported values | Default value |
| --- | --- | --- | --- | --- |

Show more

If you configure the`kms` signature backend, set the KMS configuration, including OIDC and Spire, as necessary.

Expand

`signers.kms.auth.address`

URI of the KMS server (the value of`VAULT_ADDR`).

`signers.kms.auth.token`

Authentication token for the KMS server (the value of`VAULT_TOKEN`). Providing the token as plain-text configuration might be insecure. Use the alternative`signers.kms.auth.token-path` configuration setting for production environments.

`signers.kms.auth.token-path`

The full pathname of the file that contains the authentication token for the KMS server (the value of`VAULT_TOKEN`). Provide this file as a secret and configure mounting this file for the Tekton Chains controller, as described in Creating and mounting the KMS authentication token secret.

Example value:`/etc/kms-secrets/KMS_AUTH_TOKEN`

`signers.kms.auth.oidc.path`

The path for OIDC authentication (for example,`jwt` for Vault).

`signers.kms.auth.oidc.role`

The role for OIDC authentication.

`signers.kms.auth.spire.sock`

The URI of the Spire socket for the KMS token (for example,`unix:///tmp/spire-agent/public/api.sock`).

`signers.kms.auth.spire.audience`

The audience for requesting a SVID from Spire.

| Table 1.10. Chains configuration: Supported parameters for KMS signing | Parameter | Description | Supported values | Default value |
| --- | --- | --- | --- | --- |

Show more

#### 1.2.2. Creating and mounting the Mongo server URL secretCopy link

You can provide the value of the Mongo server URL to use for`docdb` storage (`MONGO_SERVER_URL`) using a secret. You must create this secret, mount it on the Tekton Chains controller, and set the`storage.docdb.mongo-server-url-dir` parameter to the directory where the secret is mounted.

Prerequisites

- You installed the OpenShift CLI (`oc`) utility.
- You are logged in to your OpenShift Container Platform cluster with administrative rights for the`openshift-pipelines` namespace.

Procedure

Create a secret named`mongo-url` with the`MONGO_SERVER_URL` file that contains the the Mongo server URL value by entering the following command:

```shell-session
$ oc create secret generic mongo-url -n tekton-chains \
  --from-file=MONGO_SERVER_URL=<path>/MONGO_SERVER_URL 
```

1

1

The full path and name of the`MONGO_SERVER_URL` file that contains the the Mongo server URL value.

In the`TektonConfig` custom resource (CR), in the`chain` section, configure mounting the secret on the Tekton Chains controller and set the`storage.docdb.mongo-server-url-dir` parameter to the directory where the secret is mounted, as shown in the following example:

Example configuration for mounting the`mongo-url` secret

```yaml
apiVersion: operator.tekton.dev/v1
kind: TektonConfig
metadata:
  name: config
spec:
# ...
  chain:
    disabled: false
    storage.docdb.mongo-server-url-dir: /tmp/mongo-url
    options:
      deployments:
        tekton-chains-controller:
          spec:
            template:
              spec:
                containers:
                - name: tekton-chains-controller
                  volumeMounts:
                  - mountPath: /tmp/mongo-url
                    name: mongo-url
                volumes:
                -  name: mongo-url
                   secret:
                    secretName: mongo-url
# ...
```

#### 1.2.3. Creating and mounting the KMS authentication token secretCopy link

You can provide the authentication token for the KMS server using a secret. For example, if the KMS provider is Hashicorp Vault, the secret must contain the value of`VAULT_TOKEN`.

You must create this secret, mount it on the Tekton Chains controller, and set the`signers.kms.auth.token-path` parameter to the full pathname of the authentication token file.

Prerequisites

- You installed the OpenShift CLI (`oc`) utility.
- You are logged in to your OpenShift Container Platform cluster with administrative rights for the`openshift-pipelines` namespace.

Procedure

Create a secret named`kms-secrets` with the`KMS_AUTH_TOKEN` file that contains the authentication token for the KMS server by entering the following command:

```shell-session
$ oc create secret generic kms-secrets -n tekton-chains \
  --from-file=KMS_AUTH_TOKEN=<path_and_name> 
```

1

1

The full path and name of the file that contains the authentication token for the KMS server, for example,`/home/user/KMS_AUTH_TOKEN`. You can use another file name instead of`KMS_AUTH_TOKEN`.

In the`TektonConfig` custom resource (CR), in the`chain` section, configure mounting the secret on the Tekton Chains controller and set the`signers.kms.auth.token-path` parameter to the full pathname of the authentication token file, as shown in the following example:

Example configuration for mounting the`kms-secrets` secret

```yaml
apiVersion: operator.tekton.dev/v1
kind: TektonConfig
metadata:
  name: config
spec:
# ...
  chain:
    disabled: false
    signers.kms.auth.token-path: /etc/kms-secrets/KMS_AUTH_TOKEN
    options:
      deployments:
        tekton-chains-controller:
          spec:
            template:
              spec:
                containers:
                - name: tekton-chains-controller
                  volumeMounts:
                  - mountPath: /etc/kms-secrets
                    name: kms-secrets
                volumes:
                -  name: kms-secrets
                   secret:
                    secretName: kms-secrets
# ...
```

#### 1.2.4. Enabling Tekton Chains to operate only in selected namespacesCopy link

By default, the Tekton Chains controller monitors resources in all namespaces. You can customize Tekton Chains to run only in specific namespaces, which provides granular control over its operation.

Prerequisites

- You are logged in to your OpenShift Container Platform cluster with`cluster-admin` privileges.

Procedure

In the`TektonConfig` CR, in the`chain` section, add the`--namespace=` argument to contain the namespaces that the controller should monitor.

The following example shows the configuration for the Tekton Chains controller to only monitor resources within the`dev` and`test` namespaces, filtering`PipelineRun` and`TaskRun` objects accordingly:

```yaml
apiVersion: operator.tekton.dev/v1alpha1
kind: TektonConfig
metadata:
  name: config
spec:
  chain:
    disabled: false
    options:
      deployments:
        tekton-chains-controller:
          spec:
            template:
              spec:
                containers:
                - args:
                  - --namespace=dev, test 
```

1

```yaml

                  name: tekton-chains-controller
```

1

If the`--namespace` argument is not provided or is left empty, the controller watches all namespaces by default.

### 1.3. Secrets for signing data in Tekton ChainsCopy link

Cluster administrators can generate a key pair and use Tekton Chains to sign artifacts using a Kubernetes secret. For Tekton Chains to work, a private key and a password for encrypted keys must exist as part of the`signing-secrets` secret in the`openshift-pipelines` namespace.

Currently, Tekton Chains supports the`x509` and`cosign` signature schemes.

Note

Use only one of the supported signature schemes.

The x509 signing scheme

To use the`x509` signing scheme with Tekton Chains, you must fulfill the following requirements:

- Store the private key in the`signing-secrets` with the`x509.pem` structure.
- Store the private key as an unencrypted`PKCS #8` PEM file.
- The key is of`ed25519` or`ecdsa` type.

The cosign signing scheme

To use the`cosign` signing scheme with Tekton Chains, you must fulfill the following requirements:

- Store the private key in the`signing-secrets` with the`cosign.key` structure.
- Store the password in the`signing-secrets` with the`cosign.password` structure.
- Store the private key as an encrypted PEM file of type`ENCRYPTED COSIGN PRIVATE KEY`.

#### 1.3.1. Generating the cosign key pair by using the TektonConfig CRCopy link

To use the`cosign` signing scheme for Tekton Chains secrets, you can generate a`cosign` key pair that uses ECDSA encryption by setting the`generateSigningSecret` field in the`TektonConfig` custom resource (CR) to`true`.

Prerequisites

- You installed the OpenShift CLI (`oc`) utility.
- You are logged in to your OpenShift Container Platform cluster with administrative rights for the`openshift-pipelines` namespace.

Procedure

Edit the`TektonConfig` CR by running the following command:

```shell-session
$ oc edit TektonConfig config
```

In the`TektonConfig` CR, set the`generateSigningSecret` value to`true`:

Example of creating an ECDSA cosign key pair by using the TektonConfig CR

```yaml
apiVersion: operator.tekton.dev/v1
kind: TektonConfig
metadata:
  name: config
spec:
# ...
  chain:
    disabled: false
    generateSigningSecret: true 
```

1

```yaml

# ...
```

1

The default value is`false`. Setting the value to`true` generates the`ecdsa` key pair.

After a few minutes, extract the public key from the secret and store it, so that you can use it to verify artifact attestations. Run the following command to extract the key:

```shell-session
$ oc extract -n openshift-pipelines secret/signing-secrets --keys=cosign.pub
```

Result

The OpenShift Pipelines Operator generates an`ecdsa` type`cosign` key pair and stores it in the`signing-secrets` secret in the`openshift-pipelines` namespace. The secret includes the following files:

- `cosign.key`: The private key
- `cosign.password`: The password for decrypting the private key
- `cosign.pub` The public key

If a`signing-secrets` secret already exists, the Operator does not overwrite the secret.

The`cosign.pub` file in your current directory contains the public key that is extracted from the secret.

Warning

If you set the`generateSigningSecret` field from`true` to`false`, the Red Hat OpenShift Pipelines Operator overrides and empties any value in the`signing-secrets` secret.

The Red Hat OpenShift Pipelines Operator does not provide the following security functions:

- Key rotation
- Auditing key usage
- Proper access control to the key

#### 1.3.2. Manually generating signing secrets with the cosign toolCopy link

You can use the`cosign` signing scheme with Tekton Chains using the`cosign` tool.

Prerequisites

- You installed the Cosign tool. For information about installing the Cosign tool, see the [Sigstore documentation for Cosign](https://docs.sigstore.dev/cosign/system_config/installation/).

Procedure

Generate the`cosign.key` and`cosign.pub` key pairs by running the following command:

```shell-session
$ cosign generate-key-pair k8s://openshift-pipelines/signing-secrets
```

Cosign prompts you for a password and then creates a Kubernetes secret.

1. Store the encrypted`cosign.key` private key and the`cosign.password` decryption password in the`signing-secrets` Kubernetes secret. Ensure that the private key is stored as an encrypted PEM file of the`ENCRYPTED COSIGN PRIVATE KEY` type.

#### 1.3.3. Manually generating signing secrets with the skopeo toolCopy link

You can generate keys using the`skopeo` tool and use them in the`cosign` signing scheme with Tekton Chains.

Prerequisites

- You installed the`skopeo` package on your Linux system.

Procedure

Generate a public/private key pair by running the following command:

```shell-session
$ skopeo generate-sigstore-key --output-prefix <mykey> 
```

1

1

Replace` ` with a key name of your choice.

Skopeo prompts you for a passphrase for the private key and then creates the key files named`.private` and`.pub`.

Encode the`.pub` file using the`base64` tool by running the following command:

```shell-session
$ base64 -w 0 <mykey>.pub > b64.pub
```

Encode the`.private` file using the`base64` tool by running the following command:

```shell-session
$ base64 -w 0 <mykey>.private > b64.private
```

Encode the passhprase using the`base64` tool by running the following command:

```shell-session
$ echo -n '<passphrase>' | base64 -w 0 > b64.passphrase 
```

1

1

Replace` ` with the passphrase that you used for the key pair.

Create the`signing-secrets` secret in the`openshift-pipelines` namespace by running the following command:

```shell-session
$ oc create secret generic signing-secrets -n openshift-pipelines
```

Edit the`signing-secrets` secret by running the following command:

```plaintext
$ oc edit secret -n openshift-pipelines signing-secrets
```

Add the encoded keys in the data of the secret in the following way:

```yaml
apiVersion: v1
data:
  cosign.key: <Encoded <mykey>.private> 
```

1

```yaml

  cosign.password: <Encoded passphrase> 
```

2

```yaml

  cosign.pub: <Encoded <mykey>.pub> 
```

3

```yaml

immutable: true
kind: Secret
metadata:
  name: signing-secrets
# ...
type: Opaque
```

1

Replace`.private>` with the content of the`b64.private` file.

2

Replace` ` with the content of the`b64.passphrase` file.

3

Replace`.pub>` with the content of the`b64.pub` file.

#### 1.3.4. Resolving the "secret already exists" errorCopy link

If the`signing-secret` secret is already populated, the command to create this secret might output the following error message:

```shell-session
Error from server (AlreadyExists): secrets "signing-secrets" already exists
```

You can resolve this error by deleting the secret.

Procedure

Delete the`signing-secret` secret by running the following command:

```shell-session
$ oc delete secret signing-secrets -n openshift-pipelines
```

1. Re-create the key pairs and store them in the secret using your preferred signing scheme.

### 1.4. Authenticating to an OCI registryCopy link

Before pushing signatures to an OCI registry, cluster administrators must configure Tekton Chains to authenticate with the registry. The Tekton Chains controller uses the same service account under which the task runs execute. To set up a service account with the necessary credentials for pushing signatures to an OCI registry, perform the following steps:

Procedure

Set the namespace and name of the Kubernetes service account.

```shell-session
$ export NAMESPACE=<namespace> 
```

1

```shell-session

$ export SERVICE_ACCOUNT_NAME=<service_account> 
```

2

1

The namespace associated with the service account.

2

The name of the service account.

Create a Kubernetes secret.

```shell-session
$ oc create secret registry-credentials \
  --from-file=.dockerconfigjson \ 
```

1

```shell-session

  --type=kubernetes.io/dockerconfigjson \
  -n $NAMESPACE
```

1

Substitute with the path to your Docker config file. Default path is`~/.docker/config.json`.

Give the service account access to the secret.

```shell-session
$ oc patch serviceaccount $SERVICE_ACCOUNT_NAME \
  -p "{\"imagePullSecrets\": [{\"name\": \"registry-credentials\"}]}" -n $NAMESPACE
```

If you patch the default`pipeline` service account that Red Hat OpenShift Pipelines assigns to all task runs, the Red Hat OpenShift Pipelines Operator will override the service account. As a best practice, you can perform the following steps:

Create a separate service account to assign to user’s task runs.

```shell-session
$ oc create serviceaccount <service_account_name>
```

Associate the service account to the task runs by setting the value of the`serviceaccountname` field in the task run template.

```yaml
apiVersion: tekton.dev/v1
kind: TaskRun
metadata:
  name: build-push-task-run-2
spec:
  taskRunTemplate:
    serviceAccountName: build-bot 
```

1

```yaml

  taskRef:
    name: build-push
...
```

1

Substitute with the name of the newly created service account.

### 1.5. Creating and verifying task run signatures without any additional authenticationCopy link

To verify signatures of task runs using Tekton Chains with any additional authentication, perform the following tasks:

- Generate an encrypted`x509` or`cosign` key pair and store it as a Kubernetes secret.
- Configure the Tekton Chains backend storage.
- Create a task run, sign it, and store the signature and the payload as annotations on the task run itself.
- Retrieve the signature and payload from the signed task run.
- Verify the signature of the task run.

Prerequisites

Ensure that the following components are installed on the cluster:

- Red Hat OpenShift Pipelines Operator
- Tekton Chains
- [Cosign](https://docs.sigstore.dev/cosign/system_config/installation/)

Procedure

In the Tekton Chains configuration, disable the OCI storage, and set the task run storage and format to`tekton`. In the`TektonConfig` custom resource set the following values:

```yaml
apiVersion: operator.tekton.dev/v1alpha1
kind: TektonConfig
metadata:
  name: config
spec:
# ...
    chain:
      artifacts.oci.storage: ""
      artifacts.taskrun.format: tekton
      artifacts.taskrun.storage: tekton
# ...
```

For more information about configuring Tekton Chains using the`TektonConfig` custom resource, see "Configuring Tekton Chains".

To restart the Tekton Chains controller to ensure that the modified configuration is applied, enter the following command:

```plaintext
$ oc delete po -n openshift-pipelines -l app=tekton-chains-controller
```

Create a task run by entering the following command:

```shell-session
$ oc create -f https://raw.githubusercontent.com/tektoncd/chains/main/examples/taskruns/task-output-image.yaml 
```

1

1

Replace the example URI with the URI or file path pointing to your task run.

Example output

```shell-session
taskrun.tekton.dev/build-push-run-output-image-qbjvh created
```

Check the status of the steps by entering the following command. Wait until the process finishes.

```shell-session
$ tkn tr describe --last
```

Example output

```shell-session
[...truncated output...]
NAME                            STATUS
∙ create-dir-builtimage-9467f   Completed
∙ git-source-sourcerepo-p2sk8   Completed
∙ build-and-push                Completed
∙ echo                          Completed
∙ image-digest-exporter-xlkn7   Completed
```

To retrieve the signature from the object stored as`base64` encoded annotations, enter the following commands:

```shell-session
$ tkn tr describe --last -o jsonpath="{.metadata.annotations.chains\.tekton\.dev/signature-taskrun-$TASKRUN_UID}" | base64 -d > sig
```

```shell-session
$ export TASKRUN_UID=$(tkn tr describe --last -o  jsonpath='{.metadata.uid}')
```

1. Generate an encrypted`x509` or`cosign` key pair. For more information about creating a key pair and saving it as a secret, see "Secrets for signing data in Tekton Chains".
2. To verify the signature using the public key that you created, enter the following command:

```plaintext
$ cosign verify-blob-attestation --insecure-ignore-tlog --key path/to/cosign.pub --signature sig --type slsaprovenance --check-claims=false /dev/null 
```

1

1

Replace`path/to/cosign.pub` with the path name of the public key file.

Example output

```shell-session
Verified OK
```

### 1.6. Using Tekton Chains to sign and verify image and provenanceCopy link

Cluster administrators can use Tekton Chains to sign and verify images and provenances, by performing the following tasks:

- Generate an encrypted`x509` or`cosign` key pair and store it as a Kubernetes secret.
- Set up authentication for the OCI registry to store images, image signatures, and signed image attestations.
- Configure Tekton Chains to generate and sign provenance.
- Create an image with Kaniko in a task run.
- Verify the signed image and the signed provenance.

Prerequisites

Ensure that the following tools are installed on the cluster:

- Red Hat OpenShift Pipelines Operator
- Tekton Chains
- [Cosign](https://docs.sigstore.dev/cosign/system_config/installation/)
- [Rekor](https://docs.sigstore.dev/logging/installation/)
- [jq](https://stedolan.github.io/jq/)

Procedure

Configure authentication for the image registry.

To configure authentication for a Kaniko task that builds and pushes image to the registry, create a Kubernetes secret of the docker`config.json` file containing the required credentials.

```shell-session
$ oc create secret generic <docker_config_secret_name> \ 
```

1

```shell-session

  --from-file <path_to_config.json> 
```

2

1

Substitute with the name of the docker config secret.

2

Substitute with the path to docker`config.json` file.

1. To configure the Tekton Chains controller for pushing signature to an OCI registry, use the credentials associated with the service account of the task run. For detailed information, see the "Authenticating to an OCI registry" section.

Configure Tekton Chains by setting the`artifacts.taskrun.format`,`artifacts.taskrun.storage`, and`transparency.enabled` parameters in the`chains-config` object:

```shell-session
$ oc patch configmap chains-config -n openshift-pipelines -p='{"data":{"artifacts.taskrun.format": "in-toto"}}'

$ oc patch configmap chains-config -n openshift-pipelines -p='{"data":{"artifacts.taskrun.storage": "oci"}}'

$ oc patch configmap chains-config -n openshift-pipelines -p='{"data":{"transparency.enabled": "true"}}'
```

Start the Kaniko task.

Apply the Kaniko task to the cluster.

```shell-session
$ oc apply -f examples/kaniko/kaniko.yaml 
```

1

1

Substitute with the URI or file path to your Kaniko task.

Set the appropriate environment variables.

```shell-session
$ export REGISTRY=<url_of_registry> 
```

1

```shell-session


$ export DOCKERCONFIG_SECRET_NAME=<name_of_the_secret_in_docker_config_json> 
```

2

1

Substitute with the URL of the registry where you want to push the image.

2

Substitute with the name of the secret in the docker`config.json` file.

Start the Kaniko task.

```shell-session
$ tkn task start --param IMAGE=$REGISTRY/kaniko-chains --use-param-defaults --workspace name=source,emptyDir="" --workspace name=dockerconfig,secret=$DOCKERCONFIG_SECRET_NAME kaniko-chains
```

Observe the logs of this task until all steps are complete. On successful authentication, the final image will be pushed to`$REGISTRY/kaniko-chains`.

Wait for a minute to allow Tekton Chains to generate the provenance and sign it, and then check the availability of the`chains.tekton.dev/signed=true` annotation on the task run.

```shell-session
$ oc get tr <task_run_name> \ 
```

1

```shell-session

-o json | jq -r .metadata.annotations

{
  "chains.tekton.dev/signed": "true",
  ...
}
```

1

Substitute with the name of the task run.

Verify the image and the attestation.

```shell-session
$ cosign verify --key cosign.pub $REGISTRY/kaniko-chains

$ cosign verify-attestation --key cosign.pub $REGISTRY/kaniko-chains
```

Find the provenance for the image in Rekor.

Search Rekor to find all entries that match the`sha256` digest of the image.

```shell-session
$ rekor-cli search --sha <image_digest> 
```

1

```shell-session


<uuid_1> 
```

2

```shell-session

<uuid_2> 
```

3

```shell-session

...
```

1

Substitute with the`sha256` digest of the image.

2

The first matching universally unique identifier (UUID).

3

The second matching UUID.

The search result displays UUIDs of the matching entries. One of those UUIDs holds the attestation.

Check the attestation.

```shell-session
$ rekor-cli get --uuid <uuid> --format json | jq -r .Attestation | base64 --decode | jq
```

1. Get the digest of the $REGISTRY/kaniko-chains image. You can search for it ing the task run, or pull the image to extract the digest.

## Chapter 2. Setting up OpenShift Pipelines in the web console to view Software Supply Chain Security elementsCopy link

Use the Developer or Administrator perspective to create or modify a pipeline and view key Software Supply Chain Security elements within a project.

Set up OpenShift Pipelines to view:

- Project vulnerabilities: Visual representation of identified vulnerabilities within a project.
- Software Bill of Materials (SBOMs): Download or view detailed listing of PipelineRun components.

Additionally, PipelineRuns that meet Tekton Chains requirement displays signed badges next to their names. This badge indicates that the pipeline run execution results are cryptographically signed and stored securely, for example within an OCI image.

Figure 2.1. The signed badge

The PipelineRun displays the signed badge next to its name only if you have configured Tekton Chains. For information on configuring Tekton Chains, see [Using Tekton Chains for OpenShift Pipelines supply chain security](https://docs.openshift.com/pipelines/1.13/secure/using-tekton-chains-for-openshift-pipelines-supply-chain-security.html).

### 2.1. Setting up OpenShift Pipelines to view project vulnerabilitiesCopy link

The PipelineRun details page provides a visual representation of identified vulnerabilities, categorized by the severity (critical, high, medium, and low). This streamlined view facilitates prioritization and remediation efforts.

Figure 2.2. Viewing vulnerabilities on the`PipelineRun` details page

You can also review the vulnerabilities in the Vulnerabilities column in the pipeline run list view page.

Figure 2.3. Viewing vulnerabilities on the`PipelineRun` list view

Note

Visual representation of identified vulnerabilities is available starting from the OpenShift Container Platform version 4.15 release.

Prerequisites

- You have [logged in to the web console](https://docs.openshift.com/container-platform/4.14/web_console/web-console.html#web-console).
- You have the appropriate [roles and permissions](https://docs.openshift.com/container-platform/4.14/authentication/using-rbac.html#default-roles_using-rbac) in a project to create applications and other workloads in OpenShift Container Platform.
- You have an existing vulnerability scan task.

Procedures

Update your existing vulnerability scan task to ensure that it stores the output in the .json file and then extracts the vulnerability summary in the following format:

```yaml
# The format to extract vulnerability summary (adjust the jq command for different JSON structures).
jq -rce \
    '{vulnerabilities:{
      critical: (.result.summary.CRITICAL),
      high: (.result.summary.IMPORTANT),
      medium: (.result.summary.MODERATE),
      low: (.result.summary.LOW)
      }}' scan_output.json | tee $(results.SCAN_OUTPUT.path)
```

Note

You might need to adjust the [jq](https://jqlang.github.io/jq/download/) command for different JSON structures.

(Optional) If you do not have a vulnerability scan task, create one in the following format:

Example vulnerability scan task using Roxctl

```yaml
apiVersion: tekton.dev/v1
kind: Task
metadata:
  name: vulnerability-scan  
```

1

```yaml

  annotations:
    task.output.location: results  
```

2

```yaml

    task.results.format: application/json
    task.results.key: SCAN_OUTPUT  
```

3

```yaml

spec:
  params:
    - description: Image to be scanned
      name: image
      type: string
  results:
    - description: CVE result format  
```

4

```yaml

      name: SCAN_OUTPUT
  steps:
    - name: roxctl
      image: 'quay.io/lrangine/crda-maven:11.0'  
```

5

```yaml

      env:
        - name: ROX_CENTRAL_ENDPOINT
          valueFrom:
            secretKeyRef:
              key: rox_central_endpoint    
```

6

```yaml

              name: roxsecrets
        - name: ROX_API_TOKEN
          valueFrom:
            secretKeyRef:
              key: rox_api_token   
```

7

```yaml

              name: roxsecrets

      name: roxctl-scan
      script: | 
```

8

```yaml

        #!/bin/sh
        curl -k -L -H "Authorization: Bearer $ROX_API_TOKEN" https://$ROX_CENTRAL_ENDPOINT/api/cli/download/roxctl-linux --output ./roxctl
        chmod +x ./roxctl
        ./roxctl image scan --insecure-skip-tls-verify -e $ROX_CENTRAL_ENDPOINT --image $(params.image) --output json  > roxctl_output.json
        jq -rce \
        "{vulnerabilities:{
        critical: (.result.summary.CRITICAL),
        high: (.result.summary.IMPORTANT),
        medium: (.result.summary.MODERATE),
        low: (.result.summary.LOW)
        }}" roxctl_output.json | tee $(results.SCAN_OUTPUT.path)
```

1

The name of your task.

2

The location for storing the task outputs.

3

The naming convention of the scan task result. A valid naming convention must end with the`SCAN_OUTPUT` string. For example, SCAN_OUTPUT, MY_CUSTOM_SCAN_OUTPUT, or ACS_SCAN_OUTPUT.

4

The description of the result.

5

The location of the container image to run the scan tool.

6

The`rox_central_endpoint` key obtained from Advanced Cluster Security for Kubernetes (ACS).

7

The`rox_api_token` obtained from ACS.

8

The shell script performs the vulnerability scanning and sets the scan output in the Task run results.

Note

This is an example configuration. Modify the values according to your specific scanning tool to set results in the expected format.

Update an appropriate Pipeline to add vulnerabilities specifications in the following format:

```yaml
...
spec:
  results:
    - description: The common vulnerabilities and exposures (CVE) result
      name: SCAN_OUTPUT
      value: $(tasks.vulnerability-scan.results.SCAN_OUTPUT)
```

Verification

- Navigate to the`PipelineRun` details page and review the Vulnerabilities row for a visual representation of identified vulnerabilities.
- Alternatively, you can navigate to the`PipelineRun` list view page, and review the Vulnerabilities column.

### 2.2. Setting up OpenShift Pipelines to download or view SBOMsCopy link

The`PipelineRun` details page provides an option to download or view Software Bill of Materials (SBOMs), enhancing transparency and control within your supply chain. SBOMs lists all the software libraries that a component uses. Those libraries can enable specific functionality or facilitate development.

You can use an SBOM to better understand the composition of your software, identify vulnerabilities, and assess the potential impact of any security issues that might arise.

Figure 2.4. Options to download or view SBOMs

Prerequisites

- You have [logged in to the web console](https://docs.openshift.com/container-platform/4.14/web_console/web-console.html#web-console).
- You have the appropriate [roles and permissions](https://docs.openshift.com/container-platform/4.14/authentication/using-rbac.html#default-roles_using-rbac) in a project to create applications and other workloads in OpenShift Container Platform.

Procedure

Add a task in the following format to view or download the SBOM information:

Example SBOM task

```yaml
apiVersion: tekton.dev/v1
kind: Task
metadata:
  name: sbom-task 
```

1

```yaml

  annotations:
    task.output.location: results  
```

2

```yaml

    task.results.format: application/text
    task.results.key: LINK_TO_SBOM  
```

3

```yaml

    task.results.type: external-link  
```

4

```yaml

spec:
  results:
    - description: Contains the SBOM link  
```

5

```yaml

      name: LINK_TO_SBOM
  steps:
    - name: print-sbom-results
      image: quay.io/image  
```

6

```yaml

      script: | 
```

7

```yaml

        #!/bin/sh
        syft version
        syft quay.io/<username>/quarkus-demo:v2 --output cyclonedx-json=sbom-image.json
        echo 'BEGIN SBOM'
        cat sbom-image.json
        echo 'END SBOM'
        echo 'quay.io/user/workloads/<namespace>/node-express/node-express:build-8e536-1692702836' | tee $(results.LINK_TO_SBOM.path) 
```

8

1

The name of your task.

2

The location for storing the task outputs.

3

The SBOM task result name. Do not change the name of the SBOM result task.

4

(Optional) Set to open the SBOM in a new tab.

5

The description of the result.

6

The image that generates the SBOM.

7

The script that generates the SBOM image.

8

The SBOM image along with the path name.

Update the Pipeline to reference the newly created SBOM task.

```yaml
...
spec:
  tasks:
    - name: sbom-task
      taskRef:
        name: sbom-task 
```

1

```yaml

  results:
    - name: IMAGE_URL  
```

2

```yaml

      description: url
      value: <oci_image_registry_url> 
```

3

1

The same name as created in Step 2.

2

The name of the result.

3

The OCI image repository URL which contains the`.sbom` images.

1. In the Developer or Administrator perspective, switch to the relevant project where you want a visual representation of SBOMs.
2. Rerun the affected OpenShift Pipeline.

#### 2.2.1. Viewing an SBOM in the web UICopy link

Prerequisites

- You have set up OpenShift Pipelines to download or view SBOMs.

Procedure

On the`PipelineRun` details page, select View SBOM.

1. You can use your web browser to immediately search the SBOM for terms that indicate vulnerabilities in your software supply chain. For example, try searching for`log4j`.
2. You can select Download to download the SBOM, or Expand to view it full-screen.

#### 2.2.2. Downloading an SBOM in the CLICopy link

Prerequisites

- You have installed the Cosign CLI tool. For information about installing the Cosign tool, see the [Sigstore documentation for Cosign](https://docs.sigstore.dev/cosign/system_config/installation/).
- You have set up OpenShift Pipelines to download or view SBOMs.

Procedure

From the OpenShift web console, copy the`download sbom` command and run it on your terminal.

Example cosign command

```shell-session
$ cosign download sbom quay.io/<workspace>/user-workload@sha256
```

(Optional) To view the full SBOM in a searchable format, run the following command to redirect the output:

Example cosign command

```shell-session
$ cosign download sbom quay.io/<workspace>/user-workload@sha256 > sbom.txt
```

#### 2.2.3. Reading the SBOMCopy link

In the SBOM, as the following sample excerpt shows, you can see four characteristics of each library that a project uses:

- Its author or publisher
- Its name
- Its version
- Its licenses

This information helps you verify that individual libraries are safely-sourced, updated, and compliant.

Example SBOM

```shell-session
{
    "bomFormat": "CycloneDX",
    "specVersion": "1.4",
    "serialNumber": "urn:uuid:89146fc4-342f-496b-9cc9-07a6a1554220",
    "version": 1,
    "metadata": {
        ...
    },
    "components": [
        {
            "bom-ref": "pkg:pypi/flask@2.1.0?package-id=d6ad7ed5aac04a8",
            "type": "library",
            "author": "Armin Ronacher <armin.ronacher@active-4.com>",
            "name": "Flask",
            "version": "2.1.0",
            "licenses": [
                {
                    "license": {
                        "id": "BSD-3-Clause"
                    }
                }
            ],
            "cpe": "cpe:2.3:a:armin-ronacher:python-Flask:2.1.0:*:*:*:*:*:*:*",
            "purl": "pkg:pypi/Flask@2.1.0",
            "properties": [
                {
                    "name": "syft:package:foundBy",
                    "value": "python-package-cataloger"
                    ...
```

## Chapter 3. Configuring the security context for podsCopy link

The default service account for pods that OpenShift Pipelines starts is`pipeline`. The security context constraint (SCC) associated with the`pipeline` service account is`pipelines-scc`. The`pipelines-scc` SCC is based the`anyuid` SCC, with minor differences as defined in the following YAML specification:

Example`pipelines-scc.yaml` snippet

```yaml
apiVersion: security.openshift.io/v1
kind: SecurityContextConstraints
# ...
allowedCapabilities:
  - SETFCAP
# ...
fsGroup:
  type: MustRunAs
# ...
```

In addition, the`Buildah` task, shipped as part of OpenShift Pipelines, uses`vfs` as the default storage driver.

You can configure the security context for pods that OpenShift Pipelines creates for pipeline runs and task runs. You can make the following changes:

- Change the default and maximum SCC for all pods
- Change the default SCC for pods created for pipeline runs and task runs in a particular namespace
- Configure a particular pipeline run or task run to use a custom SCC and service account

Note

The simplest way to run`buildah` that ensures all images can build is to run it as root in a pod with the`privileged` SCC. For instructions about running`buildah` with more restrictive security settings, see Building of container images using Buildah as a non-root user.

### 3.1. Configuring the default and maximum SCC for pods that OpenShift Pipelines createsCopy link

You can configure the default security context constraint (SCC) for all pods that OpenShift Pipelines creates for task runs and pipeline runs. You can also configure the maximum SCC, which is the least restrictive SCC that can be configured for these pods in any namespace.

Procedure

Edit the`TektonConfig` custom resource (CR) by entering the following command:

```shell-session
$ oc edit TektonConfig config
```

Set the default and maximum SCC in the spec, as in the following example:

```yaml
apiVersion: operator.tekton.dev/v1alpha1
kind: TektonConfig
metadata:
  name: config
spec:
#  ...
  platforms:
    openshift:
      scc:
        default: "restricted-v2" 
```

1

```yaml

        maxAllowed: "privileged" 
```

2

1

`spec.platforms.openshift.scc.default` specifies the default SCC that OpenShift Pipelines attaches to the service account (SA) used for workloads, which is, by default, the`pipeline` SA. This SCC is used for all pipeline run and task run pods.

2

`spec.platforms.openshift.scc.maxAllowed` specifies the least restrictive SCC that you can configure for pipeline run and task run pods in any namespace. This setting does not apply when you configure a custom SA and SCC in a particular pipeline run or task run.

### 3.2. Configuring the SCC for pods in a namespaceCopy link

You can configure the security context constraint (SCC) for all pods that OpenShift Pipelines creates for pipeline runs and task runs that you create in a particular namespace. This SCC must not be less restrictive than the maximum SCC that you configured using the`TektonConfig` CR, in the`spec.platforms.openshift.scc.maxAllowed` spec.

Procedure

Set the`operator.tekton.dev/scc` annotation for the namespace to the name of the SCC.

Example namespace annotation for configuring the SCC for OpenShift Pipelines pods

```yaml
apiVersion: v1
kind: Namespace
metadata:
  name: test-namespace
  annotations:
    operator.tekton.dev/scc: nonroot
```

### 3.3. Running pipeline run and task run by using a custom SCC and a custom service accountCopy link

When using the`pipelines-scc` security context constraint (SCC) associated with the default`pipelines` service account, the pipeline run and task run pods might face timeouts. This happens because in the default`pipelines-scc` SCC, the`fsGroup.type` parameter is set to`MustRunAs`.

Note

For more information about pod timeouts, see [BZ#1995779](https://bugzilla.redhat.com/show_bug.cgi?id=1995779).

To avoid pod timeouts, you can create a custom SCC with the`fsGroup.type` parameter set to`RunAsAny`, and associate it with a custom service account.

Note

As a best practice, use a custom SCC and a custom service account for pipeline runs and task runs. This approach allows greater flexibility and does not break the runs when the defaults are modified during an upgrade.

Procedure

Define a custom SCC with the`fsGroup.type` parameter set to`RunAsAny`:

Example: Custom SCC

```yaml
apiVersion: security.openshift.io/v1
kind: SecurityContextConstraints
metadata:
  annotations:
    kubernetes.io/description: my-scc is a close replica of anyuid scc. pipelines-scc has fsGroup - RunAsAny.
  name: my-scc
allowHostDirVolumePlugin: false
allowHostIPC: false
allowHostNetwork: false
allowHostPID: false
allowHostPorts: false
allowPrivilegeEscalation: true
allowPrivilegedContainer: false
allowedCapabilities: null
defaultAddCapabilities: null
fsGroup:
  type: RunAsAny
groups:
- system:cluster-admins
priority: 10
readOnlyRootFilesystem: false
requiredDropCapabilities:
- MKNOD
runAsUser:
  type: RunAsAny
seLinuxContext:
  type: MustRunAs
supplementalGroups:
  type: RunAsAny
volumes:
- configMap
- downwardAPI
- emptyDir
- persistentVolumeClaim
- projected
- secret
```

Create the custom SCC:

Example: Create the`my-scc` SCC

```shell-session
$ oc create -f my-scc.yaml
```

Create a custom service account:

Example: Create a`fsgroup-runasany` service account

```shell-session
$ oc create serviceaccount fsgroup-runasany
```

Associate the custom SCC with the custom service account:

Example: Associate the`my-scc` SCC with the`fsgroup-runasany` service account

```shell-session
$ oc adm policy add-scc-to-user my-scc -z fsgroup-runasany
```

If you want to use the custom service account for privileged tasks, you can associate the`privileged` SCC with the custom service account by running the following command:

Example: Associate the`privileged` SCC with the`fsgroup-runasany` service account

```shell-session
$ oc adm policy add-scc-to-user privileged -z fsgroup-runasany
```

Use the custom service account in the pipeline run and task run:

Example: Pipeline run YAML with`fsgroup-runasany` custom service account

```yaml
apiVersion: tekton.dev/v1
kind: PipelineRun
metadata:
  name: <pipeline-run-name>
spec:
  pipelineRef:
    name: <pipeline-cluster-task-name>
  taskRunTemplate:
    serviceAccountName: 'fsgroup-runasany'
```

Example: Task run YAML with`fsgroup-runasany` custom service account

```yaml
apiVersion: tekton.dev/v1
kind: TaskRun
metadata:
  name: <task-run-name>
spec:
  taskRef:
    name: <cluster-task-name>
  taskRunTemplate:
    serviceAccountName: 'fsgroup-runasany'
```

## Chapter 4. Securing webhooks with event listenersCopy link

As an administrator, you can secure webhooks with event listeners. After creating a namespace, you enable HTTPS for the`Eventlistener` resource by adding the`operator.tekton.dev/enable-annotation=enabled` label to the namespace. Then, you create a`Trigger` resource and a secured route using the re-encrypted TLS termination.

Triggers in Red Hat OpenShift Pipelines support insecure HTTP and secure HTTPS connections to the`Eventlistener` resource. HTTPS secures connections within and outside the cluster.

Red Hat OpenShift Pipelines runs a`tekton-operator-proxy-webhook` pod that watches for the labels in the namespace. When you add the label to the namespace, the webhook sets the`service.beta.openshift.io/serving-cert-secret-name= ` annotation on the`EventListener` object. This, in turn, creates secrets and the required certificates.

```shell-session
service.beta.openshift.io/serving-cert-secret-name=<secret_name>
```

In addition, you can mount the created secret into the`Eventlistener` pod to secure the request.

### 4.1. Providing secure connection with OpenShift routesCopy link

To create a route with the re-encrypted TLS termination, run:

```shell-session
$ oc create route reencrypt --service=<svc-name> --cert=tls.crt --key=tls.key --ca-cert=ca.crt --hostname=<hostname>
```

Alternatively, you can create a re-encrypted TLS termination YAML file to create a secure route.

Example re-encrypt TLS termination YAML to create a secure route

```yaml
apiVersion: route.openshift.io/v1
kind: Route
metadata:
  name: route-passthrough-secured  
```

1

```yaml

spec:
  host: <hostname>
  to:
    kind: Service
    name: frontend 
```

2

```yaml

  tls:
    termination: reencrypt 
```

3

```yaml

    key: [as in edge termination]
    certificate: [as in edge termination]
    caCertificate: [as in edge termination]
    destinationCACertificate: |- 
```

4

```yaml

      -----BEGIN CERTIFICATE-----
      [...]
      -----END CERTIFICATE-----
```

1 2

The name of the object, which is limited to only 63 characters.

3

The termination field is set to`reencrypt`. This is the only required TLS field.

4

This is required for re-encryption. The`destinationCACertificate` field specifies a CA certificate to validate the endpoint certificate, thus securing the connection from the router to the destination pods. You can omit this field in either of the following scenarios:

- The service uses a service signing certificate.
- The administrator specifies a default CA certificate for the router, and the service has a certificate signed by that CA.

You can run the`oc create route reencrypt --help` command to display more options.

### 4.2. Configuring security context for event listenersCopy link

You can configure a custom security context directly in your`EventListener` custom resource (CR) to meet your security requirements. A custom security context can help ensure that containers run with restricted privileges and comply with OpenShift Container Platform security context constraints (SCCs).

Procedure

Create a YAML file that defines your`EventListener` CR:

Example EventListener custom resource with configured security context

```yaml
apiVersion: triggers.tekton.dev/v1beta1
kind: EventListener
metadata:
#...
spec:
  serviceAccountName: tekton-triggers-sa
  resources:
    kubernetesResource:
      spec:
        template:
          spec:
            securityContext:
              runAsNonRoot: true 
```

1

```yaml

            containers:
              - resources:
                  requests:
                    memory: "64Mi"
                    cpu: "250m"
                  limits:
                    memory: "128Mi"
                    cpu: "500m"
                securityContext:
                  readOnlyRootFilesystem: true 
```

2

```yaml

#...
```

1

Specify the pod-level security context settings. The example setting sets the pod-level security context to prevent the containers from running as the root user.

2

Specify the container-level security context settings. The example setting restricts the container root filesystem to read-only to limit potential file system modifications at runtime.

### 4.3. Creating a sample EventListener resource using a secure HTTPS connectionCopy link

This section uses the [pipelines-tutorial](https://github.com/openshift/pipelines-tutorial) example to demonstrate creation of a sample EventListener resource using a secure HTTPS connection.

Procedure

Create the`TriggerBinding` resource from the YAML file available in the pipelines-tutorial repository:

```shell-session
$ oc create -f https://raw.githubusercontent.com/openshift/pipelines-tutorial/master/03_triggers/01_binding.yaml
```

Create the`TriggerTemplate` resource from the YAML file available in the pipelines-tutorial repository:

```shell-session
$ oc create -f https://raw.githubusercontent.com/openshift/pipelines-tutorial/master/03_triggers/02_template.yaml
```

Create the`Trigger` resource directly from the pipelines-tutorial repository:

```shell-session
$ oc create -f https://raw.githubusercontent.com/openshift/pipelines-tutorial/master/03_triggers/03_trigger.yaml
```

Create an`EventListener` resource using a secure HTTPS connection:

Add a label to enable the secure HTTPS connection to the`Eventlistener` resource:

```shell-session
$ oc label namespace <ns-name> operator.tekton.dev/enable-annotation=enabled
```

Create the`EventListener` resource from the YAML file available in the pipelines-tutorial repository:

```shell-session
$ oc create -f https://raw.githubusercontent.com/openshift/pipelines-tutorial/master/03_triggers/04_event_listener.yaml
```

Create a route with the re-encrypted TLS termination:

```shell-session
$ oc create route reencrypt --service=<svc-name> --cert=tls.crt --key=tls.key --ca-cert=ca.crt --hostname=<hostname>
```

## Chapter 5. Authenticating pipelines with repositories using secretsCopy link

Pipelines and tasks can require credentials to authenticate with Git repositories and container repositories. In Red Hat OpenShift Pipelines, you can use secrets to authenticate pipeline runs and task runs that interact with a Git repository or container repository during execution.

A secret for authentication with a Git repository is known as a Git secret.

A pipeline run or a task run gains access to the secrets through an associated service account. Alternatively, you can define a workspace in the pipeline or task and bind the secret to the workspace.

### 5.1. PrerequisitesCopy link

- You installed the`oc` OpenShift command line utility.

### 5.2. Providing secrets using service accountsCopy link

You can use service accounts to provide secrets for authentication with Git repositories and container repositories.

You can associate a secret with a service account. The information in the secret becomes available to the tasks that run under this service account.

#### 5.2.1. Types and annotation of secrets for service accountsCopy link

If you provide authentication secrets using service accounts, OpenShift Pipelines supports several secret types. For most of these secret types, you must provide annotations that define the repositories for which the authentication secret is valid.

##### 5.2.1.1. Git authentication secretsCopy link

If you provide authentication secrets using service accounts, OpenShift Pipelines supports the following types of secrets for Git authentication:

- `kubernetes.io/basic-auth`: A username and password for basic authentication
- `kubernetes.io/ssh-auth`: Keys for SSH-based authentication

If you provide authentication secrets using service accounts, a Git secret must have one or more annotation keys. The names of each key must begin with`tekton.dev/git-` and the value is the URL of the host for which OpenShift Pipelines must use the credentials in the secret.

In the following example, OpenShift Pipelines uses a`basic-auth` secret to access repositories at`github.com` and`gitlab.com`.

Example: Credentials for basic authentication with multiple Git repositories

```yaml
apiVersion: v1
kind: Secret
metadata:
  name: git-secret-basic
  annotations:
    tekton.dev/git-0: github.com
    tekton.dev/git-1: gitlab.com
type: kubernetes.io/basic-auth
stringData:
  username: <username> 
```

1

```yaml

  password: <password> 
```

2

1

Username for the repository

2

Password or personal access token for the repository

You can also use`ssh-auth` secret to provide a private key for accessing a Git repository, as in the following example:

Example: Private key for SSH-based authentication

```yaml
apiVersion: v1
kind: Secret
metadata:
  name: git-secret-ssh
  annotations:
    tekton.dev/git-0: https://github.com
type: kubernetes.io/ssh-auth
stringData:
  ssh-privatekey: 
```

1

1

The content of the SSH private key file.

##### 5.2.1.2. Container registry authentication secretsCopy link

If you provide authentication secrets using service accounts, OpenShift Pipelines supports the following types of secrets for container (Docker) registry authentication:

- `kubernetes.io/basic-auth`: A username and password for basic authentication
- `kubernetes.io/dockercfg`: A serialized`~/.dockercfg` file
- `kubernetes.io/dockerconfigjson`: A serialized`~/.docker/config.json` file

If you provide authentication secrets using service accounts, a container registry secret of the`kubernetes.io/basic-auth` type must have one or more annotation keys. The names of each key must begin with`tekton.dev/docker-` and the value is the URL of the host for which OpenShift Pipelines must use the credentials in the secret. This annotation is not required for other types of container registry secrets.

In the following example, OpenShift Pipelines uses a`basic-auth` secret, which relies on a username and password, to access container registries at`quay.io` and`my-registry.example.com`.

Example: Credentials for basic authentication with multiple container repositories

```yaml
apiVersion: v1
kind: Secret
metadata:
  name: docker-secret-basic
  annotations:
    tekton.dev/docker-0: quay.io
    tekton.dev/docker-1: my-registry.example.com
type: kubernetes.io/basic-auth
stringData:
  username: <username> 
```

1

```yaml

  password: <password> 
```

2

1

Username for the registry

2

Password or personal access token for the registry

You can create`kubernetes.io/dockercfg` and`kubernetes.io/dockerconfigjson` secrets from an existing configuration file, as in the following example:

Example: Command for creating a secret for authenticating to a container repository from an existing configuration file

```shell-session
$ oc create secret generic docker-secret-config \
    --from-file=config.json=/home/user/.docker/config.json \
    --type=kubernetes.io/dockerconfigjson
```

You can also use the`oc` command line utility to create`kubernetes.io/dockerconfigjson` secrets from credentials, as in the following example:

Example: Command for creating a secret for authenticating to a container repository from credentials

```shell-session
$ oc create secret docker-registry docker-secret-config \
  --docker-email=<email> \ 
```

1

```shell-session

  --docker-username=<username> \ 
```

2

```shell-session

  --docker-password=<password> \ 
```

3

```shell-session

  --docker-server=my-registry.example.com:5000 
```

4

1

Email address for the registry

2

Username for the registry

3

Password or personal access token for the registry

4

The host name and port for the registry

#### 5.2.2. Configuring basic authentication for Git using a service accountCopy link

For a pipeline to retrieve resources from password-protected repositories, you can configure the basic authentication for that pipeline.

Note

Consider using SSH-based authentication rather than basic authentication.

To configure basic authentication for a pipeline, create a basic authentication secret, associate this secret with a service account, and associate this service account with a`TaskRun` or`PipelineRun` resource.

Note

For GitHub, authentication using a plain password is deprecated. Instead, use a [personal access token](https://docs.github.com/en/authentication/keeping-your-account-and-data-secure/creating-a-personal-access-token).

Procedure

Create the YAML manifest for the secret in the`secret.yaml` file. In this manifest, specify the username and password or [GitHub personal access token](https://docs.github.com/en/authentication/keeping-your-account-and-data-secure/creating-a-personal-access-token) to access the target Git repository.

```yaml
apiVersion: v1
kind: Secret
metadata:
  name: basic-user-pass 
```

1

```yaml

  annotations:
    tekton.dev/git-0: https://github.com
type: kubernetes.io/basic-auth
stringData:
  username: <username> 
```

2

```yaml

  password: <password> 
```

3

1

Name of the secret. In this example,`basic-user-pass`.

2

Username for the Git repository.

3

Password or personal access token for the Git repository.

Create the YAML manifest for the service account in the`serviceaccount.yaml` file. In this manifest, associate the secret with the service account.

```yaml
apiVersion: v1
kind: ServiceAccount
metadata:
  name: build-bot 
```

1

```yaml

secrets:
  - name: basic-user-pass 
```

2

1

Name of the service account. In this example,`build-bot`.

2

Name of the secret. In this example,`basic-user-pass`.

Create the YAML manifest for the task run or pipeline run in the`run.yaml` file and associate the service account with the task run or pipeline run. Use one of the following examples:

Associate the service account with a`TaskRun` resource:

```yaml
apiVersion: tekton.dev/v1
kind: TaskRun
metadata:
  name: build-push-task-run-2 
```

1

```yaml

spec:
  taskRunTemplate:
    serviceAccountName: build-bot 
```

2

```yaml

  taskRef:
    name: build-push 
```

3

1

Name of the task run. In this example,`build-push-task-run-2`.

2

Name of the service account. In this example,`build-bot`.

3

Name of the task. In this example,`build-push`.

Associate the service account with a`PipelineRun` resource:

```yaml
apiVersion: tekton.dev/v1
kind: PipelineRun
metadata:
  name: demo-pipeline 
```

1

```yaml

  namespace: default
spec:
  taskRunTemplate:
    serviceAccountName: build-bot 
```

2

```yaml

  pipelineRef:
    name: demo-pipeline 
```

3

1

Name of the pipeline run. In this example,`demo-pipeline`.

2

Name of the service account. In this example,`build-bot`.

3

Name of the pipeline. In this example,`demo-pipeline`.

Apply the YAML manifests that you created by entering the following command:

```shell-session
$ oc apply --filename secret.yaml,serviceaccount.yaml,run.yaml
```

#### 5.2.3. Configuring SSH authentication for Git using a service accountCopy link

For a pipeline to retrieve resources from repositories configured with SSH keys, you must configure the SSH-based authentication for that pipeline.

To configure SSH-based authentication for a pipeline, create an authentication secret with the SSH private key, associate this secret with a service account, and associate this service account with a`TaskRun` or`PipelineRun` resource.

Procedure

Create the YAML manifest for the secret in the`secret.yaml` file. In this manifest, set the value of`ssh-privatekey` to the content of the SSH private key file, and set the value of`known_hosts` to the content of the known hosts file.

```yaml
apiVersion: v1
kind: Secret
metadata:
  name: ssh-key 
```

1

```yaml

  annotations:
    tekton.dev/git-0: github.com
type: kubernetes.io/ssh-auth
stringData:
  ssh-privatekey: 
```

2

```yaml

  known_hosts: 
```

3

1

Name of the secret containing the SSH private key. In this example,`ssh-key`.

2

The content of the SSH private key file.

3

The content of the known hosts file.

Important

If you omit the known hosts file, OpenShift Pipelines accepts the public key of any server.

Create the YAML manifest for the service account in the`serviceaccount.yaml` file. In this manifest, associate the secret with the service account.

```yaml
apiVersion: v1
kind: ServiceAccount
metadata:
  name: build-bot 
```

1

```yaml

secrets:
  - name: ssh-key 
```

2

1

Name of the service account. In this example,`build-bot`.

2

Name of the secret containing the SSH private key. In this example,`ssh-key`.

In the`run.yaml` file, associate the service account with a task run or a pipeline run. Use one of the following examples:

To associate the service account with a task run:

```yaml
apiVersion: tekton.dev/v1
kind: TaskRun
metadata:
  name: build-push-task-run-2 
```

1

```yaml

spec:
  taskRunTemplate:
    serviceAccountName: build-bot 
```

2

```yaml

  taskRef:
    name: build-push 
```

3

1

Name of the task run. In this example,`build-push-task-run-2`.

2

Name of the service account. In this example,`build-bot`.

3

Name of the task. In this example,`build-push`.

To associate the service account with a pipeline run:

```yaml
apiVersion: tekton.dev/v1
kind: PipelineRun
metadata:
  name: demo-pipeline 
```

1

```yaml

  namespace: default
spec:
  taskRunTemplate:
    serviceAccountName: build-bot 
```

2

```yaml

  pipelineRef:
    name: demo-pipeline 
```

3

1

Name of the pipeline run. In this example,`demo-pipeline`.

2

Name of the service account. In this example,`build-bot`.

3

Name of the pipeline. In this example,`demo-pipeline`.

Apply the changes.

```shell-session
$ oc apply --filename secret.yaml,serviceaccount.yaml,run.yaml
```

#### 5.2.4. Configuring container registry authentication using a service accountCopy link

For a pipeline to retrieve container images from a registry or push container images to a registry, you must configure the authentication for that registry.

To configure registry authentication for a pipeline, create an authentication secret with the Docker configuration file, associate this secret with a service account, and associate this service account with a`TaskRun` or`PipelineRun` resource.

Procedure

Create the container registry authentication secret from an existing`config.json` file, which contains the authentication information, by entering the following command:

```shell-session
$ oc create secret generic my-registry-credentials \ 
```

1

```shell-session

  --from-file=config.json=/home/user/credentials/config.json 
```

2

1

The name of the secret, in this example,`my-registry-credentials`

2

The path name of the`config.json` file, in this example,`/home/user/credentials/config.json`

Create the YAML manifest for the service account in the`serviceaccount.yaml` file. In this manifest, associate the secret with the service account.

```yaml
apiVersion: v1
kind: ServiceAccount
metadata:
  name: container-bot 
```

1

```yaml

secrets:
  - name: my-registry-credentials 
```

2

1

Name of the service account. In this example,`container-bot`.

2

Name of the secret containing the SSH private key. In this example,`my-registry-credentials`.

Create a YAML manifest for a task run or pipeline run as the`run.yaml` file. In this file, associate the service account with a task run or a pipeline run. Use one of the following examples:

To associate the service account with a task run:

```yaml
apiVersion: tekton.dev/v1
kind: TaskRun
metadata:
  name: build-container-task-run-2 
```

1

```yaml

spec:
  taskRunTemplate:
    serviceAccountName: container-bot 
```

2

```yaml

  taskRef:
    name: build-container 
```

3

1

Name of the task run. In this example,`build-container-task-run-2`.

2

Name of the service account. In this example,`container-bot`.

3

Name of the task. In this example,`build-container`.

To associate the service account with a pipeline run:

```yaml
apiVersion: tekton.dev/v1
kind: PipelineRun
metadata:
  name: demo-pipeline 
```

1

```yaml

  namespace: default
spec:
  taskRunTemplate:
    serviceAccountName: container-bot 
```

2

```yaml

  pipelineRef:
    name: demo-pipeline 
```

3

1

Name of the pipeline run. In this example,`demo-pipeline`.

2

Name of the service account. In this example,`container-bot`.

3

Name of the pipeline. In this example,`demo-pipeline`.

Apply the changes by entering the following command:

```shell-session
$ oc apply --filename serviceaccount.yaml,run.yaml
```

#### 5.2.5. Additional considerations for authentication using service accountsCopy link

In certain cases, you must complete additional steps to use authentication secrets that you provide using service accounts.

##### 5.2.5.1. SSH Git authentication in tasksCopy link

You can directly invoke Git commands in the steps of a task and use SSH authentication, but you must complete an additional step.

OpenShift Pipelines provides the SSH files in the`/tekton/home/.ssh` directory and sets the`$HOME` variable to`/tekton/home`. However, Git SSH authentication ignores the`$HOME` variable and uses the home directory specified in the`/etc/passwd` file for the user. Therefore, a step that uses Git command must symlink the`/tekton/home/.ssh` directory to the home directory of the associated user.

For example, if the task runs as the`root` user, the step must include the following command before Git commands:

```yaml
apiVersion: tekton.dev/v1
kind: Task
metadata:
  name: example-git-task
spec:
  steps:
    - name: example-git-step
#     ...
      script:
        ln -s $HOME/.ssh /root/.ssh
#     ...
```

However, explicit symlinks are not necessary when you use a pipeline resource of the`git` type or the`git-clone` task available in the Tekton catalog.

As an example of using SSH authentication in`git` type tasks, refer to [authenticating-git-commands.yaml](https://github.com/openshift-pipelines/pipelines-examples/blob/main/v1/taskruns/authenticating-git-commands.yaml).

##### 5.2.5.2. Use of secrets as a non-root userCopy link

You might need to use secrets as a non-root user in certain scenarios, such as:

- The users and groups that the containers use to execute runs are randomized by the platform.
- The steps in a task define a non-root security context.
- A task specifies a global non-root security context, which applies to all steps in a task.

In such scenarios, consider the following aspects of executing task runs and pipeline runs as a non-root user:

- SSH authentication for Git requires the user to have a valid home directory configured in the`/etc/passwd` directory. Specifying a UID that has no valid home directory results in authentication failure.
- SSH authentication ignores the`$HOME` environment variable. So you must or symlink the appropriate secret files from the`$HOME` directory defined by OpenShift Pipelines (`/tekton/home`), to the non-root user’s valid home directory.

In addition, to configure SSH authentication in a non-root security context, refer to the`git-clone-and-check` step in the [example for authenticating git commands](https://github.com/openshift-pipelines/pipelines-examples/blob/main/v1/taskruns/authenticating-git-commands.yaml).

### 5.3. Providing secrets using workspacesCopy link

You can use workspaces to provide secrets for authentication with Git repositories and container repositories.

You can configure a named workspace in a task, specifying a path where the workspace is mounted. When you run the task, provide the secret as the workspace with this name. When OpenShift Pipelines executes the task, the information in the secret is available to the task.

If you provide authentication secrets using workspaces, annotations for the secrets are not required.

#### 5.3.1. Configuring SSH authentication for Git using workspacesCopy link

For a pipeline to retrieve resources from repositories configured with SSH keys, you must configure the SSH-based authentication for that pipeline.

To configure SSH-based authentication for a pipeline, create an authentication secret with the SSH private key, configure a named workspace for this secret in the task, and specify the secret when running the task.

Procedure

Create the Git SSH authentication secret from files in an existing`.ssh` directory by entering the following command:

```shell-session
$ oc create secret generic my-github-ssh-credentials \ 
```

1

```shell-session

  --from-file=id_ed25519=/home/user/.ssh/id_ed25519 \ 
```

2

```shell-session

  --from-file=known_hosts=/home/user/.ssh/known_hosts 
```

3

1

The name of the secret, in this example,`my-github-ssh-credentials`

2

The name and full path name of the private key file, in this example,`/home/user/.ssh/id_ed25519`

3

The name and full path name of the known hosts file, in this example,`/home/user/.ssh/known_hosts`

In your task definition, configure a named workspace for the Git authentication, for example,`ssh-directory`:

Example definition of a workspace

```yaml
apiVersion: tekton.dev/v1beta1
kind: Task
metadata:
  name: git-clone
spec:
  workspaces:
    - name: ssh-directory
      description: |
        A .ssh directory with private key, known_hosts, config, etc.
```

When running the task, specify the secret for the named workspace by including the`--workspace` argument in the`tkn task start` command:

```shell-session
$ tkn task start <task_name>
      --workspace name=<workspace_name>,secret=<secret_name> 
```

1

```shell-session

      # ...
```

1

Replace` ` with the name of the workspace that you configured and` ` with the name of the secret that you created.

1. In the steps of the task, access the directory using the path in the`$(workspaces..path)` environment variable, for example,`$(workspaces.ssh-directory.path)`

Example task for cloning a Git repository using an SSH key for authentication

```yaml
apiVersion: tekton.dev/v1beta1
kind: Task
metadata:
  name: git-clone
spec:
  workspaces:
    - name: output
      description: The git repo will be cloned onto the volume backing this Workspace.
    - name: ssh-directory
      description: |
        A .ssh directory with private key, known_hosts, config, etc. Copied to
        the user's home before git commands are executed. Used to authenticate
        with the git remote when performing the clone. Binding a Secret to this
        Workspace is strongly recommended over other volume types
  params:
    - name: url
      description: Repository URL to clone from.
      type: string
    - name: revision
      description: Revision to checkout. (branch, tag, sha, ref, etc...)
      type: string
      default: ""
    - name: gitInitImage
      description: The image providing the git-init binary that this Task runs.
      type: string
      default: "gcr.io/tekton-releases/github.com/tektoncd/pipeline/cmd/git-init:v0.37.0"
  results:
    - name: commit
      description: The precise commit SHA that was fetched by this Task.
    - name: url
      description: The precise URL that was fetched by this Task.
  steps:
    - name: clone
      image: "$(params.gitInitImage)"
      script: |
        #!/usr/bin/env sh
        set -eu
        # This is necessary for recent version of git
        git config --global --add safe.directory '*'
        cp -R "$(workspaces.ssh-directory.path)" "${HOME}"/.ssh 
```

1

```yaml

        chmod 700 "${HOME}"/.ssh
        chmod -R 400 "${HOME}"/.ssh/*
        CHECKOUT_DIR="$(workspaces.output.path)/"
        /ko-app/git-init \
          -url="$(params.url)" \
          -revision="$(params.revision)" \
          -path="${CHECKOUT_DIR}"
        cd "${CHECKOUT_DIR}"
        RESULT_SHA="$(git rev-parse HEAD)"
        EXIT_CODE="$?"
        if [ "${EXIT_CODE}" != 0 ] ; then
          exit "${EXIT_CODE}"
        fi
        printf "%s" "${RESULT_SHA}" > "$(results.commit.path)"
        printf "%s" "$(params.url)" > "$(results.url.path)"
```

1

The script copies the content of the secret (in the form of a folder) to`${HOME}/.ssh`, which is the standard folder where`ssh` searches for credentials.

Example command for running the task

```shell-session
$ tkn task start git-clone
      --param url=git@github.com:example-github-user/buildkit-tekton
      --workspace name=output,emptyDir=""
      --workspace name=ssh-directory,secret=my-github-ssh-credentials
      --use-param-defaults --showlog
```

#### 5.3.2. Configuring container registry authentication using workspacesCopy link

For a pipeline to retrieve container images from a registry, you must configure the authentication for that registry.

To configure authentication for a container registry, create an authentication secret with the Docker configuration file, configure a named workspace for this secret in the task, and specify the secret when running the task.

Procedure

Create the container registry authentication secret from an existing`config.json` file, which contains the authentication information, by entering the following command:

```shell-session
$ oc create secret generic my-registry-credentials \ 
```

1

```shell-session

  --from-file=config.json=/home/user/credentials/config.json 
```

2

1

The name of the secret, in this example,`my-registry-credentials`

2

The path name of the`config.json` file, in this example,`/home/user/credentials/config.json`

In your task definition, configure a named workspace for the Git authentication, for example,`ssh-directory`:

Example definition of a workspace

```yaml
apiVersion: tekton.dev/v1beta1
kind: Task
metadata:
  name: skopeo-copy
spec:
  workspaces:
    - name: dockerconfig
      description: Includes a docker `config.json`
# ...
```

To run the task, specify the secret for the named workspace by including the`--workspace` argument in the`tkn task start` command:

```shell-session
$ tkn task start <task_name>
      --workspace name=<workspace_name>,secret=<secret_name> 
```

1

```shell-session

      # ...
```

1

Replace` ` with the name of the workspace that you configured and` ` with the name of the secret that you created.

1. In the steps of the task, access the directory by using the path in the`$(workspaces..path)` environment variable, for example,`$(workspaces.dockerconfig.path)`.

Example task for copying an image from a container repository using Skopeo

```yaml
apiVersion: tekton.dev/v1beta1
kind: Task
metadata:
  name: skopeo-copy
spec:
  workspaces:
    - name: dockerconfig 
```

1

```yaml

      description: Includes a docker `config.json`
  steps:
    - name: clone
      image: quay.io/skopeo/stable:v1.8.0
      env:
      - name: DOCKER_CONFIG
        value: $(workspaces.dockerconfig.path) 
```

2

```yaml

      script: |
        #!/usr/bin/env sh
        set -eu
        skopeo copy docker://docker.io/library/ubuntu:latest docker://quay.io/example_repository/ubuntu-copy:latest
```

1

The name of the workspace that contains the`config.json` file.

2

The`DOCKER_CONFIG` environment variable points to the location of the`config.json` file in the`dockerconfig` workspace. Skopeo uses this environment variable to get the authentication information.

Example command for running the task

```shell-session
$ tkn task start skopeo-copy
      --workspace name=dockerconfig,secret=my-registry-credentials
      --use-param-defaults --showlog
```

#### 5.3.3. Limiting a secret to particular steps using workspacesCopy link

When you provide authentication secrets using workspaces and define the workspace in a task, by default the workspace is available to all steps in the task.

To limit a secret to specific steps, define the workspace both in the task specification and in the step specification.

Procedure

Add the`workspaces:` definition under both the task specification and the step specification, as in the following example:

Example task definition where only one step can access the credentials workspace

```yaml
apiVersion: tekton.dev/v1beta1
kind: Task
metadata:
  name: git-clone-build
spec:
  workspaces: 
```

1

```yaml

    - name: ssh-directory
      description: |
        A .ssh directory with private key, known_hosts, config, etc.
# ...
  steps:
    - name: clone
      workspaces: 
```

2

```yaml

        - name: ssh-directory
# ...
    - name: build 
```

3

```yaml

# ...
```

1

The definition of the`ssh-directory` workspace in the task specification.

2

The definition of the`ssh-directory` workspace in the step specification. The authentication information is available to this step as the`$(workspaces.ssh-directory.path)` directory.

3

As this step does not include a definition of the`ssh-directory` workspace, the authentication information is not available to this step.

## Chapter 6. Building of container images using Buildah as a non-root userCopy link

Running OpenShift Pipelines as the root user on a container can expose the container processes and the host to other potentially malicious resources. You can reduce this type of exposure by running the workload as a specific non-root user in the container.

In most cases, you can run Buildah without root privileges by creating a custom task for building the image and configuring user namespaces in this task.

If your image does not build successfully using this configuration, you can use custom service account (SA) and security context constraint (SCC) definitions; however, if you use this option, you must enable the Buildah step to raise its privileges (`allowPrivilegeEscalation: true`).

### 6.1. Running Buildah as a non-root user by configuring user namespacesCopy link

Configuring user namespaces is the simplest way to run Buildah in a task as a non-root user. However, some images might not build using this option.

Prerequisites

- You have installed the`oc` command-line utility.

Procedure

To create a copy of the`buildah` task, which is provided in the`openshift-pipelines` namespace, and to change the name of the copy to`buildah-as-user`, enter the following command:

```shell-session
$ oc get task buildah -n openshift-pipelines -o yaml | yq '. |= (del .metadata |= with_entries(select(.key == "name" )))' | yq '.kind="Task"' | yq '.metadata.name="buildah-as-user"' | oc create -f -
```

Edit the copied`buildah` task by entering the following command:

```shell-session
$ oc edit task buildah-as-user
```

In the new task, create`annotations` and`stepTemplate` sections, as shown in the following example:

Example additions to the`buildah-as-user` task

```yaml
apiVersion: tekton.dev/v1
kind: Task
metadata:
  annotations:
    io.kubernetes.cri-o.userns-mode: 'auto:size=65536;map-to-root=true'
    io.openshift.builder: 'true'
  name: assemble-containerimage
  namespace: pipeline-namespace
spec:
  description: This task builds an image.
#  ...
  stepTemplate:
    env:
      - name: HOME
        value: /tekton/home
    image: $(params.builder-image)
    imagePullPolicy: IfNotPresent
    name: ''
    resources:
      limits:
        cpu: '1'
        memory: 4Gi
      requests:
        cpu: 100m
        memory: 2Gi
    securityContext:
      capabilities:
        add:
          - SETFCAP
      runAsNonRoot: true
      runAsUser: 1000 
```

1

```yaml

    workingDir: $(workspaces.working-directory.path)
#  ...
```

1

The`runAsUser:` setting is not strictly necessary, because`podTemplate` is used.

1. Use the new`buildah-as-user` task to build the image in your pipeline.

### 6.2. Running Buildah as a non-root user by defining a custom SA and SCCCopy link

To run builds of container images using Buildah as a non-root user, you can perform the following steps:

- Define custom service account (SA) and security context constraint (SCC).
- Configure Buildah to use the`build` user with id`1000`.
- Start a task run with a custom config map, or integrate it with a pipeline run.

#### 6.2.1. Configuring custom service account and security context constraintCopy link

The default`pipeline` SA allows using a user id outside of the namespace range. To reduce dependency on the default SA, you can define a custom SA and SCC with necessary cluster role and role bindings for the`build` user with user id`1000`.

Important

At this time, enabling the`allowPrivilegeEscalation` setting is required for Buildah to run successfully in the container. With this setting, Buildah can leverage`SETUID` and`SETGID` capabilities when running as a non-root user.

Procedure

Create a custom SA and SCC with necessary cluster role and role bindings.

Example: Custom SA and SCC for used id`1000`

```yaml
apiVersion: v1
kind: ServiceAccount
metadata:
  name: pipelines-sa-userid-1000 
```

1

```yaml

---
kind: SecurityContextConstraints
metadata:
  annotations:
  name: pipelines-scc-userid-1000 
```

2

```yaml

allowHostDirVolumePlugin: false
allowHostIPC: false
allowHostNetwork: false
allowHostPID: false
allowHostPorts: false
allowPrivilegeEscalation: true 
```

3

```yaml

allowPrivilegedContainer: false
allowedCapabilities: null
apiVersion: security.openshift.io/v1
defaultAddCapabilities: null
fsGroup:
  type: MustRunAs
groups:
- system:cluster-admins
priority: 10
readOnlyRootFilesystem: false
requiredDropCapabilities:
- MKNOD
- KILL
runAsUser: 
```

4

```yaml

  type: MustRunAs
  uid: 1000
seLinuxContext:
  type: MustRunAs
supplementalGroups:
  type: RunAsAny
users: []
volumes:
- configMap
- downwardAPI
- emptyDir
- persistentVolumeClaim
- projected
- secret
---
apiVersion: rbac.authorization.k8s.io/v1
kind: ClusterRole
metadata:
  name: pipelines-scc-userid-1000-clusterrole 
```

5

```yaml

rules:
- apiGroups:
  - security.openshift.io
  resourceNames:
  - pipelines-scc-userid-1000
  resources:
  - securitycontextconstraints
  verbs:
  - use
---
apiVersion: rbac.authorization.k8s.io/v1
kind: RoleBinding
metadata:
  name: pipelines-scc-userid-1000-rolebinding 
```

6

```yaml

roleRef:
  apiGroup: rbac.authorization.k8s.io
  kind: ClusterRole
  name: pipelines-scc-userid-1000-clusterrole
subjects:
- kind: ServiceAccount
  name: pipelines-sa-userid-1000
```

1

Define a custom SA.

2

Define a custom SCC created based on restricted privileges, with modified`runAsUser` field.

3

At this time, enabling the`allowPrivilegeEscalation` setting is required for Buildah to run successfully in the container. With this setting, Buildah can leverage`SETUID` and`SETGID` capabilities when running as a non-root user.

4

Restrict any pod that gets attached with the custom SCC through the custom SA to run as user id`1000`.

5

Define a cluster role that uses the custom SCC.

6

Bind the cluster role that uses the custom SCC to the custom SA.

#### 6.2.2. Configuring Buildah to use build userCopy link

You can define a Buildah task to use the`build` user with user id`1000`.

Procedure

Create a copy of the`buildah` task, which is provided in the`openshift-pipelines` namespace; change the name of the copy to`buildah-as-user`.

```shell-session
$ oc get task buildah -n openshift-pipelines -o yaml | yq '. |= (del .metadata |= with_entries(select(.key == "name" )))' | yq '.kind="Task"' | yq '.metadata.name="buildah-as-user"' | oc create -f -
```

Edit the copied`buildah` task.

```shell-session
$ oc edit task buildah-as-user
```

Example: Modified Buildah task with`build` user

```yaml
apiVersion: tekton.dev/v1
kind: Task
metadata:
  name: buildah-as-user
spec:
  description: >-
    Buildah task builds source into a container image and
    then pushes it to a container registry.
    Buildah Task builds source into a container image using Project Atomic's
    Buildah build tool.It uses Buildah's support for building from Dockerfiles,
    using its buildah bud command.This command executes the directives in the
    Dockerfile to assemble a container image, then pushes that image to a
    container registry.
  params:
  - name: IMAGE
    description: Reference of the image buildah will produce.
  - name: BUILDER_IMAGE
    description: The location of the buildah builder image.
    default: registry.redhat.io/rhel8/buildah@sha256:99cae35f40c7ec050fed3765b2b27e0b8bbea2aa2da7c16408e2ca13c60ff8ee
  - name: STORAGE_DRIVER
    description: Set buildah storage driver
    default: vfs
  - name: DOCKERFILE
    description: Path to the Dockerfile to build.
    default: ./Dockerfile
  - name: CONTEXT
    description: Path to the directory to use as context.
    default: .
  - name: TLSVERIFY
    description: Verify the TLS on the registry endpoint (for push/pull to a non-TLS registry)
    default: "true"
  - name: FORMAT
    description: The format of the built container, oci or docker
    default: "oci"
  - name: BUILD_EXTRA_ARGS
    description: Extra parameters passed for the build command when building images.
    default: ""
  - description: Extra parameters passed for the push command when pushing images.
    name: PUSH_EXTRA_ARGS
    type: string
    default: ""
  - description: Skip pushing the built image
    name: SKIP_PUSH
    type: string
    default: "false"
  results:
  - description: Digest of the image just built.
    name: IMAGE_DIGEST
    type: string
  workspaces:
  - name: source
  steps:
  - name: build
    securityContext:
      runAsUser: 1000 
```

1

```yaml

    image: $(params.BUILDER_IMAGE)
    workingDir: $(workspaces.source.path)
    script: |
      echo "Running as USER ID `id`" 
```

2

```yaml

      buildah --storage-driver=$(params.STORAGE_DRIVER) bud \
        $(params.BUILD_EXTRA_ARGS) --format=$(params.FORMAT) \
        --tls-verify=$(params.TLSVERIFY) --no-cache \
        -f $(params.DOCKERFILE) -t $(params.IMAGE) $(params.CONTEXT)
      [[ "$(params.SKIP_PUSH)" == "true" ]] && echo "Push skipped" && exit 0
      buildah --storage-driver=$(params.STORAGE_DRIVER) push \
        $(params.PUSH_EXTRA_ARGS) --tls-verify=$(params.TLSVERIFY) \
        --digestfile $(workspaces.source.path)/image-digest $(params.IMAGE) \
        docker://$(params.IMAGE)
      cat $(workspaces.source.path)/image-digest | tee /tekton/results/IMAGE_DIGEST
    volumeMounts:
    - name: varlibcontainers
      mountPath: /home/build/.local/share/containers 
```

3

```yaml

  volumes:
  - name: varlibcontainers
    emptyDir: {}
```

1

Run the container explicitly as the user id`1000`, which corresponds to the`build` user in the Buildah image.

2

Display the user id to confirm that the process is running as user id`1000`.

3

You can change the path for the volume mount as necessary.

#### 6.2.3. Starting a task run with custom config map, or a pipeline runCopy link

After defining the custom Buildah task, you can create a`TaskRun` object that builds an image as a`build` user with user id`1000`. In addition, you can integrate the`TaskRun` object as part of a`PipelineRun` object.

Procedure

Create a`TaskRun` object with a custom`ConfigMap` and`Dockerfile` objects.

Example: A task run that runs Buildah as user id`1000`

```yaml
apiVersion: v1
data:
  Dockerfile: |
    ARG BASE_IMG=registry.access.redhat.com/ubi9/ubi
    FROM $BASE_IMG AS buildah-runner
    RUN dnf -y update && \
        dnf -y install git && \
        dnf clean all
    CMD git
kind: ConfigMap
metadata:
  name: dockerfile 
```

1

```yaml

---
apiVersion: tekton.dev/v1
kind: TaskRun
metadata:
  name: buildah-as-user-1000
spec:
  taskRunTemplate:
    serviceAccountName: pipelines-sa-userid-1000 
```

2

```yaml

  params:
  - name: IMAGE
    value: image-registry.openshift-image-registry.svc:5000/test/buildahuser
  taskRef:
    kind: Task
    name: buildah-as-user
  workspaces:
  - configMap:
      name: dockerfile 
```

3

```yaml

    name: source
```

1

Use a config map because the focus is on the task run, without any prior task that fetches some sources with a Dockerfile.

2

The name of the service account that you created.

3

Mount a config map as the source workspace for the`buildah-as-user` task.

(Optional) Create a pipeline and a corresponding pipeline run.

Example: A pipeline and corresponding pipeline run

```yaml
apiVersion: tekton.dev/v1
kind: Pipeline
metadata:
  name: pipeline-buildah-as-user-1000
spec:
  params:
  - name: IMAGE
  - name: URL
  workspaces:
  - name: shared-workspace
  - name: sslcertdir
    optional: true
  tasks:
  - name: fetch-repository 
```

1

```yaml

    taskRef:
      resolver: cluster
      params:
      - name: kind
        value: task
      - name: name
        value: git-clone
      - name: namespace
        value: openshift-pipelines
    workspaces:
    - name: output
      workspace: shared-workspace
    params:
    - name: URL
      value: $(params.URL)
    - name: SUBDIRECTORY
      value: ""
    - name: DELETE_EXISTING
      value: "true"
  - name: buildah
    taskRef:
      name: buildah-as-user 
```

2

```yaml

    runAfter:
    - fetch-repository
    workspaces:
    - name: source
      workspace: shared-workspace
    - name: sslcertdir
      workspace: sslcertdir
    params:
    - name: IMAGE
      value: $(params.IMAGE)
---
apiVersion: tekton.dev/v1
kind: PipelineRun
metadata:
  name: pipelinerun-buildah-as-user-1000
spec:
  taskRunSpecs:
    - pipelineTaskName: buildah
      taskServiceAccountName: pipelines-sa-userid-1000 
```

3

```yaml

  params:
  - name: URL
    value: https://github.com/openshift/pipelines-vote-api
  - name: IMAGE
    value: image-registry.openshift-image-registry.svc:5000/test/buildahuser
  pipelineRef:
    name: pipeline-buildah-as-user-1000
  workspaces:
  - name: shared-workspace 
```

4

```yaml

    volumeClaimTemplate:
      spec:
        accessModes:
          - ReadWriteOnce
        resources:
          requests:
            storage: 100Mi
```

1

Use the`git-clone` task to fetch the source containing a Dockerfile and build it using the modified Buildah task.

2

Refer to the modified Buildah task.

3

Use the service account that you created for the Buildah task.

4

Share data between the`git-clone` task and the modified Buildah task using a persistent volume claim (PVC) created automatically by the controller.

1. Start the task run or the pipeline run.

### 6.3. Limitations of unprivileged buildsCopy link

The process for unprivileged builds works with most`Dockerfile` objects. However, there are some known limitations might cause a build to fail:

- Using the`--mount=type=cache` option might fail due to lack of necessay permissions issues. For more information, see this [article](https://access.redhat.com/solutions/6969529).
- Using the`--mount=type=secret` option fails because mounting resources requires additionnal capabilities that are not provided by the custom SCC.

Additional resources

- [Managing security context constraints (SCCs)](https://docs.openshift.com/container-platform/latest/authentication/managing-security-context-constraints.html)

## Chapter 7. Using buildah-ns Tekton taskCopy link

The`buildah-ns` Tekton task builds Open Container Initiative (OCI) images without requiring a container runtime daemon, such as the Docker daemon. The task uses`buildah` and applies user namespace isolation to provide enhanced security.

After a successful build, the task produces the following results:

- The fully qualified image name
- The SHA256 digest of the image

The`buildah-ns` task is functionally identical to the standard`buildah` Tekton task, but applies additional security mechanisms to improve container isolation at the kernel level.

### 7.1. Differences between buildah and buildah-ns tasksCopy link

The buildah-ns task extends the standard buildah task with the following security-focused changes:

Annotations: The task includes security annotations that enable automatic user namespace mapping:

```plaintext
io.kubernetes.cri-o.userns-mode: "auto"
io.openshift.builder: "true"
```

- Task name: The task is named`buildah-ns` instead of`buildah`.
- Security model: User namespace separation improves privilege isolation and limits the impact of potential container escape vulnerabilities.

### 7.2. Security model of the buildah-ns taskCopy link

The`buildah-ns` task applies user namespace isolation to provide privilege separation between containers and the host system.

#### 7.2.1. UID mapping behaviorCopy link

When the task runs with namespace annotations, user IDs (UIDs) are mapped as follows:

- Inside the container: Processes run as UID 0, which appears as the root user.
- Outside the container: The same processes run as a non-zero UID on the host system.

This mapping allows processes inside the container to behave as if they have root privileges while restricting their privileges on the host system.

#### 7.2.2. Security benefitsCopy link

User namespace isolation provides the following security advantages:

- Kernel-level isolation: Adds an extra isolation boundary between containers.
- Reduced privilege exposure: Limits the impact of compromised workloads by running them as non-root users on the host.
- Container escape protection: Helps mitigate potential vulnerabilities that allow escaping from the container runtime environment.

### 7.3. Workspaces, parameters, and results for the buildah-ns taskCopy link

The`buildah-ns` task requires a workspace, accepts several parameters for image build customization, and provides results that contain information about the built image.

#### 7.3.1. WorkspaceCopy link

Expand

source

Yes

The build context for the container image. Typically contains application source code and a`Containerfile` or`Dockerfile`.

| | Name | Required | Description |
| --- | --- | --- | --- |

Show more

#### 7.3.2. ParametersCopy link

Expand

IMAGE

string

Required

Fully qualified name of the image to build, including tag.

CONTAINERFILE_PATH

string

Containerfile

Path to the container build file relative to the source workspace.

TLS_VERIFY

string

true

Whether to verify TLS when pushing images. Setting this value to`true` is recommended.

VERBOSE

string

false

Enables verbose build output.

SUBDIRECTORY

string

.

Subdirectory in the workspace to use as the build context.

STORAGE_DRIVER

string

overlay

Storage driver for Buildah, aligned with the cluster node configuration.

BUILD_EXTRA_ARGS

string

Empty

Additional flags for the`Buildah` build command.

PUSH_EXTRA_ARGS

string

Empty

Additional flags for the`Buildah` push command.

SKIP_PUSH

string

false

If set to`true`, the image is not pushed to the registry.

| | Name | Type | Default | Description |
| --- | --- | --- | --- | --- |

Show more

#### 7.3.3. ResultsCopy link

Expand

IMAGE_URL

Fully qualified name of the built image.

IMAGE_DIGEST

SHA256 digest of the built image.

| | Name | Description |
| --- | --- | --- |

Show more

### 7.4. Running the buildah-ns taskCopy link

You can run the`buildah-ns` task as part of a`PipelineRun` resource.

```yaml
apiVersion: tekton.dev/v1beta1
kind: PipelineRun
metadata: {}
spec:
  pipelineRef:
    name: task-buildah-ns
  params:
    - name: IMAGE
      value: your-image-name 
```

1

```yaml

    - name: TLS_VERIFY
      value: true
    - name: VERBOSE
      value: false
  workspaces:
    - name: source
      persistentVolumeClaim:
        claimName: your-pvc-name 
```

2

1

Replace`your-image-name` with the full name of the container image that you want to build.

2

Replace`your-pvc-name` with the name of the`PersistentVolumeClaim`(PVC) that stores the application source code.

Note

If the target container registry requires authentication, configure a Kubernetes secret for registry access and link it to the service account that runs the`TaskRun` or`PipelineRun` resources.

Additional resources

- [Managing security context constraints (SCCs)](https://docs.openshift.com/container-platform/latest/authentication/managing-security-context-constraints.html)

## Legal NoticeCopy link

Copyright © Red Hat.

The text of and illustrations in this document are licensed by Red Hat under a Creative Commons Attribution–Share Alike 3.0 Unported license ("CC-BY-SA"). An explanation of CC-BY-SA is available at [http://creativecommons.org/licenses/by-sa/3.0/](http://creativecommons.org/licenses/by-sa/3.0/). In accordance with CC-BY-SA, if you distribute this document or an adaptation of it, you must provide the URL for the original version.

Red Hat, as the licensor of this document, waives the right to enforce, and agrees not to assert, Section 4d of CC-BY-SA to the fullest extent permitted by applicable law.

Red Hat, Red Hat Enterprise Linux, the Shadowman logo, JBoss, OpenShift, Fedora, the Infinity logo, and RHCE are trademarks of Red Hat, Inc., registered in the United States and other countries.

Linux® is the registered trademark of Linus Torvalds in the United States and other countries.

Java® is a registered trademark of Oracle and/or its affiliates.

XFS® is a trademark of Silicon Graphics International Corp. or its subsidiaries in the United States and/or other countries.

MySQL® is a registered trademark of MySQL AB in the United States, the European Union and other countries.

Node.js® is an official trademark of Joyent. Red Hat Software Collections is not formally related to or endorsed by the official Joyent Node.js open source or commercial project.

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



## Observability

Observability in OpenShift Pipelines | Red Hat OpenShift Pipelines | 1.21 | Red Hat Documentation

 

 

 

 

 

 

 

 

# Observability in OpenShift Pipelines

---

Red Hat OpenShift Pipelines 1.21

## Observability features of OpenShift Pipelines

Red Hat OpenShift Documentation Team

Legal Notice

Abstract

This document provides information about observability features of OpenShift Pipelines.

---

## Chapter 1. Using Tekton Results for OpenShift Pipelines observabilityCopy link

Tekton Results is a service that archives the complete information for every pipeline run and task run. You can prune the`PipelineRun` and`TaskRun` resources as necessary and use the Tekton Results API or the`opc` command line utility to access their YAML manifests as well as logging information.

### 1.1. Tekton Results conceptsCopy link

Tekton Results archives pipeline runs and task runs in the form of results and records.

For every`PipelineRun` and`TaskRun` custom resource (CR) that completes running, Tekton Results creates a record.

A result can contain one or several records. A record is always a part of exactly one result.

A result corresponds to a pipeline run, and includes the records for the`PipelineRun` CR itself and for all the`TaskRun` CRs that were started as a part of the pipeline run.

If a task run was started directly, without the use of a pipeline run, a result is created for this task run. This result contains the record for the same task run.

Each result has a name that includes the namespace in which the`PipelineRun` or`TaskRun` CR was created and the UUID of the CR. The format for the result name is` /results/ `. In this format,` ` is the UUUD of a pipeline run or else of a task run that was started directly.

Example result name

```plaintext
results-testing/results/04e2fbf2-8653-405f-bc42-a262bcf02bed
```

Each record has a name that includes name of the result that contains the record, as well as the UUID of the`PipelineRun` or`TaskRun` CR to which the record corresponds. The format for the result name is` /results/ /results/ `.

Example record name

```plaintext
results-testing/results/04e2fbf2-8653-405f-bc42-a262bcf02bed/records/e9c736db-5665-441f-922f-7c1d65c9d621
```

The record includes the full YAML manifest of the`TaskRun` or`PipelineRun` CR as it existed after the completion of the run. This manifest contains the specification of the run, any annotation specified for the run, as well as certain information about the results of the run, such as the time when it was completed and whether the run was successful.

While the`TaskRun` or`PipelineRun` CR exists, you can view the YAML manifest by using the following command:

```shell-session
$ oc get pipelinerun <cr_name> -o yaml
```

Tekton Results preserves this manifest after the`TaskRun` or`PipelineRun` CR is deleted and makes it available for viewing and searching.

Example YAML manifest of a pipeline run after its completion

```yaml
  kind: PipelineRun
  spec:
    params:
      - name: message
        value: five
    timeouts:
      pipeline: 1h0m0s
    pipelineRef:
      name: echo-pipeline
    taskRunTemplate:
      serviceAccountName: pipeline
  status:
    startTime: "2023-08-07T11:41:40Z"
    conditions:
      - type: Succeeded
        reason: Succeeded
        status: "True"
        message: 'Tasks Completed: 1 (Failed: 0, Cancelled 0), Skipped: 0'
        lastTransitionTime: "2023-08-07T11:41:49Z"
    pipelineSpec:
      tasks:
        - name: echo-task
          params:
            - name: message
              value: five
          taskRef:
            kind: Task
            name: echo-task-pipeline
      params:
        - name: message
          type: string
    completionTime: "2023-08-07T11:41:49Z"
    childReferences:
      - kind: TaskRun
        name: echo-pipeline-run-gmzrx-echo-task
        apiVersion: tekton.dev/v1
        pipelineTaskName: echo-task
  metadata:
    uid: 62c3b02e-f12b-416c-9771-c02af518f6d4
    name: echo-pipeline-run-gmzrx
    labels:
      tekton.dev/pipeline: echo-pipeline
    namespace: releasetest-js5tt
    finalizers:
      - chains.tekton.dev/pipelinerun
    generation: 2
    annotations:
      results.tekton.dev/log: releasetest-js5tt/results/62c3b02e-f12b-416c-9771-c02af518f6d4/logs/c1e49dd8-d641-383e-b708-e3a02b6a4378
      chains.tekton.dev/signed: "true"
      results.tekton.dev/record: releasetest-js5tt/results/62c3b02e-f12b-416c-9771-c02af518f6d4/records/62c3b02e-f12b-416c-9771-c02af518f6d4
      results.tekton.dev/result: releasetest-js5tt/results/62c3b02e-f12b-416c-9771-c02af518f6d4
    generateName: echo-pipeline-run-
    managedFields:
      - time: "2023-08-07T11:41:39Z"
        manager: kubectl-create
        fieldsV1:
          f:spec:
            .: {}
            f:params: {}
            f:pipelineRef:
              .: {}
              f:name: {}
          f:metadata:
            f:generateName: {}
        operation: Update
        apiVersion: tekton.dev/v1
        fieldsType: FieldsV1
      - time: "2023-08-07T11:41:40Z"
        manager: openshift-pipelines-controller
        fieldsV1:
          f:metadata:
            f:labels:
              .: {}
              f:tekton.dev/pipeline: {}
        operation: Update
        apiVersion: tekton.dev/v1
        fieldsType: FieldsV1
      - time: "2023-08-07T11:41:49Z"
        manager: openshift-pipelines-chains-controller
        fieldsV1:
          f:metadata:
            f:finalizers:
              .: {}
              v:"chains.tekton.dev/pipelinerun": {}
            f:annotations:
              .: {}
              f:chains.tekton.dev/signed: {}
        operation: Update
        apiVersion: tekton.dev/v1
        fieldsType: FieldsV1
      - time: "2023-08-07T11:41:49Z"
        manager: openshift-pipelines-controller
        fieldsV1:
          f:status:
            f:startTime: {}
            f:conditions: {}
            f:pipelineSpec:
              .: {}
              f:tasks: {}
              f:params: {}
            f:completionTime: {}
            f:childReferences: {}
        operation: Update
        apiVersion: tekton.dev/v1
        fieldsType: FieldsV1
        subresource: status
      - time: "2023-08-07T11:42:15Z"
        manager: openshift-pipelines-results-watcher
        fieldsV1:
          f:metadata:
            f:annotations:
              f:results.tekton.dev/log: {}
              f:results.tekton.dev/record: {}
              f:results.tekton.dev/result: {}
        operation: Update
        apiVersion: tekton.dev/v1
        fieldsType: FieldsV1
    resourceVersion: "126429"
    creationTimestamp: "2023-08-07T11:41:39Z"
    deletionTimestamp: "2023-08-07T11:42:23Z"
    deletionGracePeriodSeconds: 0
  apiVersion: tekton.dev/v1
```

You can access every result and record by its name. You can also use Common Expression Language (CEL) queries to search for results and records by the information they contain, including the YAML manifest.

You can configure Tekton Results to facilitate forwarding the logging information of all the tools that ran as a part of a pipeline or task to LokiStack. You can then query Tekton Results for logging information of the task run associated with a Tekton Results record.

You can also query results and logs by the names of pipeline runs and task runs.

### 1.2. Configuring Tekton ResultsCopy link

After you install OpenShift Pipelines, Tekton Results is enabled by default.

However, if you want to store and access logging information for your pipeline runs and task runs, you must configure forwarding this information to LokiStack.

You can optionally complete additional configuration for Tekton Results.

#### 1.2.1. Configuring LokiStack forwarding for logging informationCopy link

If you want to use Tekton Results to query logging information for task runs, you must install LokiStack and OpenShift Logging on your OpenShift Container Platform cluster and configure forwarding of the logging information to LokiStack.

If you do not configure LokiStack forwarding for logging information, Tekton Results does not store this information or provide it from the command-line interface or API.

Prerequisites

- Legal Notice
- You installed the OpenShift CLI (`oc`) utility.
- You are logged in to your OpenShift Container Platform cluster as a cluster administrator user.

Procedure

To configure LokiStack forwarding, complete the following steps:

Create a`ClusterLogForwarder.yaml` manifest file for the`ClusterLogForwarder` custom resource (CR) with one of the following YAML manifests, depending on whether you installed OpenShift Logging version 6 or version 5:

YAML manifest for the`ClusterLogForwarder` CR if you installed OpenShift Logging version 6

```yaml
apiVersion: observability.openshift.io/v1
kind: ClusterLogForwarder
metadata:
  name: collector
  namespace: openshift-logging
spec:
  inputs:
  - application:
      selector:
        matchExpressions:
          - key: app.kubernetes.io/managed-by
            operator: In
            values: ["tekton-pipelines", "pipelinesascode.tekton.dev"]
    name: only-tekton
    type: application
  managementState: Managed
  outputs:
  - lokiStack:
      labelKeys:
        application:
          ignoreGlobal: true
          labelKeys:
          - log_type
          - kubernetes.namespace_name
          - openshift_cluster_id
      authentication:
        token:
          from: serviceAccount
      target:
        name: logging-loki
        namespace: openshift-logging
    name: default-lokistack
    tls:
      ca:
        configMapName: openshift-service-ca.crt
        key: service-ca.crt
    type: lokiStack
  pipelines:
  - inputRefs:
    - only-tekton
    name: default-logstore
    outputRefs:
    - default-lokistack
  serviceAccount:
    name: collector
```

YAML manifest for the`ClusterLogForwarder` CR if you installed OpenShift Logging version 5

```yaml
apiVersion: "logging.openshift.io/v1"
kind: ClusterLogForwarder
metadata:
  name: instance
  namespace: openshift-logging
spec:
  inputs:
  - name: only-tekton
    application:
      selector:
        matchLabels:
          app.kubernetes.io/managed-by: tekton-pipelines
  pipelines:
    - name: enable-default-log-store
      inputRefs: [ only-tekton ]
      outputRefs: [ default ]
```

Create the`ClusterLogForwarder` CR in the`openshift-logging` namespace by entering the following command:

```shell-session
$ oc apply -n openshift-logging ClusterLogForwarder.yaml
```

Edit the`TektonConfig` custom resource (CR) by using the following command:

```shell-session
$ oc edit TektonConfig config
```

Make the following changes in the`result` spec:

```yaml
apiVersion: operator.tekton.dev/v1alpha1
kind: TektonConfig
metadata:
  name: config
spec:
  result:
    loki_stack_name: logging-loki 
```

1

```yaml

    loki_stack_namespace: openshift-logging 
```

2

1

The name of the`LokiStack` CR, typically`logging-loki`.

2

The name of the namespace where LokiStack is deployed, typically`openshift-logging`.

1. On your OpenShift Container Platform cluster, install LokiStack by using the Loki Operator and also install the OpenShift Logging Operator.

#### 1.2.2. Configuring an external database serverCopy link

Tekton Results stores data in a PostgreSQL database. By default, the installation includes an internal PostgreSQL instance intended for testing and nonproduction use. The default instance does not provide production-grade capabilities, such as automated backups, performance tuning, storage lifecycle management, or support for database-level modifications through the OpenShift Pipelines Operator.

For production environments or business-critical workloads, you can configure Tekton Results to connect to an external PostgreSQL-compatible database that is present in your environment.

Procedure

Create a secret with the credentials for connecting to your PostgreSQL server by entering the following command:

```shell-session
$ oc create secret generic tekton-results-postgres \
  --namespace=openshift-pipelines \
  --from-literal=POSTGRES_USER=<user> \
  --from-literal=POSTGRES_PASSWORD=<password>
```

Edit the`TektonConfig` custom resource (CR) by using the following command:

```shell-session
$ oc edit TektonConfig config
```

Make the following changes in the`result` spec:

```yaml
apiVersion: operator.tekton.dev/v1alpha1
kind: TektonConfig
metadata:
  name: config
spec:
  result:
    is_external_db: true
    db_host: database.example.com 
```

1

```yaml

    db_port: 5342 
```

2

1

Provide the host name of your PostgreSQL server.

2

Provide the port number of your PostgreSQL server.

#### 1.2.3. Configuring the retention policy for Tekton ResultsCopy link

By default, Tekton Results stores pipeline runs, task runs, events, and logs indefinitely. This leads to an unnecesary use of storage resources and can affect your database performance.

You can configure the retention policy for Tekton Results at the cluster level to remove older results and their associated records and logs.

Procedure

Edit the`TektonConfig` custom resource (CR) by using the following command:

```shell-session
$ oc edit TektonConfig config
```

Make the following changes in the`result` spec:

```yaml
apiVersion: operator.tekton.dev/v1alpha1
kind: TektonConfig
metadata:
  name: config
spec:
  result:
    options:
      configMaps:
        config-results-retention-policy:
          data:
            runAt: "3 5 * * 0" 
```

1

```yaml

            maxRetention: "30" 
```

2

1

Specify, in cron format, when to run the pruning job in the database. This example runs the job at 5:03 AM every Sunday.

2

Specify how many days to keep the data in the database. This example retains the data for 30 days.

### 1.3. Querying Tekton Results for results and recordsCopy link

You can use the`opc` command line utility to query Tekton Results for results and records. To install the`opc` command line utility, install the package for the`tkn` command line utility. For instructions about installing this package, see [Installing tkn](https://docs.redhat.com/en/documentation/red_hat_openshift_pipelines/1.21/html-single/pipelines_cli_tkn_reference/#installing-tkn).

You can use the names of records and results to retrieve the data in them.

You can search for results and records using Common Expression Language (CEL) queries. These searches display the UUIDs of the results or records. You can use the provided examples to create queries for common search types. You can also use reference information to create other queries.

#### 1.3.1. Preparing the opc utility environment for querying Tekton ResultsCopy link

Before you can query Tekton Results, you must prepare the environment for the`opc` utility.

Prerequisites

- You installed the`opc` utility.
- You logged on to the OpenShift Container Platform cluster by using the OpenShift CLI (`oc`).

Procedure

Set the`RESULTS_API` environment variable to the route to the Tekton Results API by entering the following command:

```shell-session
$ export RESULTS_API=$(oc get route tekton-results-api-service -n openshift-pipelines --no-headers -o custom-columns=":spec.host"):443
```

Create an authentication token for the Tekton Results API by entering the following command:

```shell-session
$ oc create token <service_account>
```

Replace` ` with the name of an OpenShift Container Platform service account that has read access to the namespaces where OpenShift Pipelines ran the pipeline runs and task runs.

Save the string that this command outputs.

Optional: Create the`~/.config/tkn/results.yaml` file for automatic authentication with the Tekton Results API. The file must have the following contents:

```yaml
address: <tekton_results_route> 
```

1

```yaml

token: <authentication_token> 
```

2

```yaml

ssl:
   roots_file_path: /home/example/cert.pem 
```

3

```yaml

   server_name_override: tekton-results-api-service.openshift-pipelines.svc.cluster.local 
```

4

```yaml

 service_account:
   namespace: service_acc_1 
```

5

```yaml

   name: service_acc_1 
```

6

1

The route to the Tekton Results API. Use the same value as you set for`RESULTS_API`.

2

The authentication token that was created by the`oc create token` command. If you provide this token, it overrides the`service_account` setting and`opc` uses this token to authenticate.

3

The location of the file with the SSL certificate that you configured for the API endpoint.

4

If you configured a custom target namespace for OpenShift Pipelines, replace`openshift-pipelines` with the name of this namespace.

5 6

The name of a service account for authenticating with the Tekton Results API. If you provided the authentication token, you do not need to provide the`service_account` parameters.

Alternatively, if you do not create the`~/.config/tkn/results.yaml` file, you can pass the token to each`opc` command by using the`--authtoken` option.

#### 1.3.2. Querying for results and records by nameCopy link

You can list and query results and records using their names.

Prerequisites

- You installed the`opc` utility and prepared its environment to query Tekton Results.
- You installed the`jq` package.
- If you want to query logging information, you configured log forwarding to LokiStack.

Procedure

List the names of all results that correspond to pipeline runs and task runs created in a namespace. Enter the following command:

```shell-session
$ opc results result list --addr ${RESULTS_API} <namespace_name>
```

Example command

```shell-session
$ opc results result list --addr ${RESULTS_API} results-testing
```

Example output

```plaintext
Name                                                          Start                                   Update
results-testing/results/04e2fbf2-8653-405f-bc42-a262bcf02bed  2023-06-29 02:49:53 +0530 IST           2023-06-29 02:50:05 +0530 IST
results-testing/results/ad7eb937-90cc-4510-8380-defe51ad793f  2023-06-29 02:49:38 +0530 IST           2023-06-29 02:50:06 +0530 IST
results-testing/results/d064ce6e-d851-4b4e-8db4-7605a23671e4  2023-06-29 02:49:45 +0530 IST           2023-06-29 02:49:56 +0530 IST
```

List the names of all records in a result by entering the following command:

```shell-session
$ opc results records list --addr ${RESULTS_API} <result_name>
```

Example command

```shell-session
$ opc results records list --addr ${RESULTS_API} results-testing/results/04e2fbf2-8653-405f-bc42-a262bcf02bed
```

Example output

```plaintext
Name                                                                                                   Type                                    Start                                   Update
results-testing/results/04e2fbf2-8653-405f-bc42-a262bcf02bed/records/e9c736db-5665-441f-922f-7c1d65c9d621  tekton.dev/v1.TaskRun              2023-06-29 02:49:53 +0530 IST           2023-06-29 02:49:57 +0530 IST
results-testing/results/04e2fbf2-8653-405f-bc42-a262bcf02bed/records/5de23a76-a12b-3a72-8a6a-4f15a3110a3e  results.tekton.dev/v1alpha2.Log         2023-06-29 02:49:57 +0530 IST           2023-06-29 02:49:57 +0530 IST
results-testing/results/04e2fbf2-8653-405f-bc42-a262bcf02bed/records/57ce92f9-9bf8-3a0a-aefb-dc20c3e2862d  results.tekton.dev/v1alpha2.Log         2023-06-29 02:50:05 +0530 IST           2023-06-29 02:50:05 +0530 IST
results-testing/results/04e2fbf2-8653-405f-bc42-a262bcf02bed/records/e9a0c21a-f826-42ab-a9d7-a03bcefed4fd  tekton.dev/v1.TaskRun              2023-06-29 02:49:57 +0530 IST           2023-06-29 02:50:05 +0530 IST
results-testing/results/04e2fbf2-8653-405f-bc42-a262bcf02bed/records/04e2fbf2-8653-405f-bc42-a262bcf02bed  tekton.dev/v1.PipelineRun          2023-06-29 02:49:53 +0530 IST           2023-06-29 02:50:05 +0530 IST
results-testing/results/04e2fbf2-8653-405f-bc42-a262bcf02bed/records/e6eea2f9-ec80-388c-9982-74a018a548e4  results.tekton.dev/v1alpha2.Log         2023-06-29 02:50:05 +0530 IST           2023-06-29 02:50:05 +0530 IST
```

Retrieve the YAML manifest for a pipeline run or task run from a record by entering the following command:

```shell-session
$ opc results records get --addr ${RESULTS_API} <record_name> \
  | jq -r .data.value | base64 -d | \
  xargs -0 python3 -c 'import sys, yaml, json; j=json.loads(sys.argv[1]); print(yaml.safe_dump(j))'
```

Example command

```shell-session
$ opc results records get --addr ${RESULTS_API} \
  results-testing/results/04e2fbf2-8653-405f-bc42-a262bcf02bed/records/e9c736db-5665-441f-922f-7c1d65c9d621 | \
  jq -r .data.value | base64 -d | \
  xargs -0 python3 -c 'import sys, yaml, json; j=json.loads(sys.argv[1]); print(yaml.safe_dump(j))'
```

Optional: Retrieve the logging information for a task run from a record using the log record name. To get the log record name, replace`records` with`logs` in the record name. Enter the following command:

```shell-session
$ opc results logs get --addr ${RESULTS_API} <log_record_name> | jq -r .data | base64 -d
```

Example command

```shell-session
$ opc results logs get --addr ${RESULTS_API} \
  results-testing/results/04e2fbf2-8653-405f-bc42-a262bcf02bed/logs/e9c736db-5665-441f-922f-7c1d65c9d621 | \
  jq -r .data | base64 -d
```

#### 1.3.3. Searching for resultsCopy link

You can search for results using Common Expression Language (CEL) queries. For example, you can find results for pipeline runs that did not succeed. However, most of the relevant information is not contained in result objects; to search by the names, completion times, and other data, search for records.

Prerequisites

- You installed the`opc` utility and prepared its environment to query Tekton Results.

Procedure

Search for results using a CEL query by entering the following command:

```shell-session
$ opc results result list --addr ${RESULTS_API} --filter="<cel_query>" <namespace-name>
```

Replace` ` with the namespace in which the pipeline runs or task runs were created.

Expand

The results of all runs that failed

`!(summary.status == SUCCESS)`

The results all pipeline runs that contained the annotations`ann1` and`ann2`

`summary.annotations.contains('ann1') && summary.annotations.contains('ann2') && summary.type=='PIPELINE_RUN'`

| Table 1.1. Example CEL queries for results | Purpose | CEL query |
| --- | --- | --- |

Show more

#### 1.3.4. Searching for recordsCopy link

You can search for records using Common Expression Language (CEL) queries. As each record contains full YAML information for a pipeline run or task run, you can find records by many different criteria.

Prerequisites

- You installed the`opc` utility and prepared its environment to query Tekton Results.

Procedure

Search for records using a CEL query by entering the following command:

```shell-session
$ opc results records list --addr ${RESULTS_API} --filter="<cel_query>" <namespace_name>/result/-
```

Replace` ` with the namespace in which the pipeline runs or task runs were created. Alternatively, search for records within a single result by entering the following command:

```shell-session
$ opc results records list --addr ${RESULTS_API} --filter="<cel_query>" <result_name>
```

Replace` ` with the full name of the result.

Expand

Records of all task runs or pipeline runs that failed

`!(data.status.conditions[0].status == 'True')`

Records where the name of the`TaskRun` or`PipelineRun` custom resource (CR) was`run1`

`data.metadata.name == 'run1'`

Records for all task runs that were started by the`PipelineRun` CR named`run1`

`data_type == 'TASK_RUN' && data.metadata.labels['tekton.dev/pipelineRun'] == 'run1'`

Records of all pipeline runs and task runs that were created from a`Pipeline` CR named`pipeline1`

`data.metadata.labels['tekton.dev/pipeline'] == 'pipeline1'`

Records of all pipeline runs that were created from a`Pipeline` CR named`pipeline1`

`data.metadata.labels['tekton.dev/pipeline'] == 'pipeline1' && data_type == 'PIPELINE_RUN'`

Records of all task runs where the`TaskRun` CR name stared with`hello`

`data.metadata.name.startsWith('hello') && data_type=='TASK_RUN'`

Records of all pipeline runs that took more than five minutes to complete

`data.status.completionTime - data.status.startTime > duration('5m') && data_type == 'PIPELINE_RUN'`

Records of all pipeline runs and task runs that completed on October 7, 2023

`data.status.completionTime.getDate() == 7 && data.status.completionTime.getMonth() == 10 && data.status.completionTime.getFullYear() == 2023`

Records of all pipeline runs that included three or more tasks

`size(data.status.pipelineSpec.tasks) >= 3 && data_type == 'PIPELINE_RUN'`

Records of all pipeline runs that had annotations containing`ann1`

`data.metadata.annotations.contains('ann1') && data_type == 'PIPELINE_RUN'`

Records of all pipeline runs that had annotations containing`ann1` and the name of the`PipelineRun` CR started with`hello`

`data.metadata.annotations.contains('ann1') && data.metadata.name.startsWith('hello') && data_type == 'PIPELINE_RUN'`

| Table 1.2. Example CEL queries for records | Purpose | CEL query |
| --- | --- | --- |

Show more

#### 1.3.5. Reference information for searching resultsCopy link

You can use the following fields in Common Expression Language (CEL) queries for results:

Expand

`parent`

The namespace in which the`PipelineRun` or`TaskRun` custom resource (CR) was created.

`uid`

Unique identifier for the result.

`annotations`

Annotations added to the`PipelineRun` or`TaskRun` CR.

`summary`

The summary of the result.

`create_time`

The creation time of the result.

`update_time`

The last update time of the result.

| Table 1.3. Fields available in CEL queries for results | CEL field | Description |
| --- | --- | --- |

Show more

You can use the`summary.status` field to determine whether the pipeline run was successful. This field can have the following values:

- `UNKNOWN`
- `SUCCESS`
- `FAILURE`
- `TIMEOUT`
- `CANCELLED`

Note

Do not use quote characters such as`"` or`'` to provide the value for this field.

#### 1.3.6. Reference information for searching recordsCopy link

You can use the following fields in Common Expression Language (CEL) queries for records:

Expand

`name`

Record name

`data_type`

Record type identifier

`tekton.dev/v1.TaskRun` or`TASK_RUN``tekton.dev/v1.PipelineRun` or`PIPELINE_RUN``results.tekton.dev/v1alpha2.Log`

`data`

The YAML data for the task run or pipeline run. In log records, this field contains the logging output.

| Table 1.4. Fields available in CEL queries for records | CEL field | Description | Values |
| --- | --- | --- | --- |

Show more

Because the`data` field contains the entire YAML data for the task run or pipeline run, you can use all elements of this data in your CEL query. For example,`data.status.completionTime` contains the completion time of the task run or pipeline run.

### 1.4. Querying results and logs by the names of pipeline runs and task runsCopy link

You can use the`opc` command line utility to query Tekton Results for lists of pipeline runs and tasks runs and then retrieve manifest and log information using the names of pipeline runs and tasks runs.

This approach requires different configuration of the`opc` command line utility, compared to queries for results and records.

Important

Querying results and logs by the names of pipeline runs and task runs is a Technology Preview feature only. Technology Preview features are not supported with Red Hat production service level agreements (SLAs) and might not be functionally complete. Red Hat does not recommend using them in production. These features provide early access to upcoming product features, enabling customers to test functionality and provide feedback during the development process.

For more information about the support scope of Red Hat Technology Preview features, see [Technology Preview Features Support Scope](https://access.redhat.com/support/offerings/techpreview/).

#### 1.4.1. Configuring the opc utility for querying results by pipeline run and task run namesCopy link

Before you can query results from Tekton Results by pipeline run and task run names, you must configure the`opc` utility.

Prerequisites

- You installed the`opc` utility.
- You logged on to the OpenShift Container Platform cluster by using the OpenShift CLI (`oc`).

Procedure

Create an authentication token for the Tekton Results API by entering the following command:

```shell-session
$ oc create token <service_account>
```

Replace` ` with the name of an OpenShift Container Platform service account that has read access to the namespaces where OpenShift Pipelines ran the pipeline runs and task runs.

Save the string that this command outputs.

Complete one of the following steps:

Configure the`opc` utility interactively by entering the following command:

```shell-session
$ opc results config set
```

Reply to the prompts that the utility displays. For`Token`, enter the authentication token that you created.

Configure the`opc` utility from a command by entering the following command:

```shell-session
$ opc results config set --host="https://tekton-results.example.com" --token="<token>"
```

Replace the host name with the fully qualified domain name of your Tekton Results route. Replace` ` with the authentication token that you generated.

Verification

You can view the configuration that you set for the`opc` utility by entering the following command:

```shell-session
$ opc results config view
```

Example output

```shell-session
api-path: ""
apiVersion: results.tekton.dev/v1alpha2
host: https://tekton-results.openshiftapps.com
insecure-skip-tls-verify: "true"
kind: Client
token: sha256~xyz
```

#### 1.4.2. Viewing a list of pipeline run names and identifiersCopy link

You can use the`opc` utility to view a list of names and identifiers of pipeline runs in a namespace.

Prerequisites

- You installed the`opc` utility.
- You configured the`opc` utility to query results from Tekton Results by pipeline run and task run names.

Procedure

Use any of the following commands to view pipeline runs:

To view all pipeline runs in a specified namespace, enter the following command:

```shell-session
$ opc results pipelinerun list -n <namespace_name>
```

Optionally, specify the`--limit` command line option, for example,`--limit=10`. With this setting, the`opc` command displays the specified number of lines containing pipeline run names and then exits. If you add the`--single-page=false` command line option, the command displays the specified number of lines and then prompts you to continue or quit.

Optionally, specify the`--labels` command line option, for example,`--labels="app.kubernetes.io/name=test-app, app.kubernetes.io/component=database`. With this setting, the list includes only the pipeline runs that have the specified labels or annotations.

Example output of the`opc results pipelinerun list` command

```shell-session
NAME                                           UID                                    STARTED      DURATION   STATUS
openshift-pipelines-main-release-tests-zscq8   78515e3a-8e20-43e8-a064-d2442c2ae845   1 week ago   5s         Failed(CouldntGetPipeline)
openshift-pipelines-main-release-tests-zrgv6   14226144-2d08-440d-a600-d602ca46cdf6   1 week ago   26m13s     Failed
openshift-pipelines-main-release-tests-jdc24   e34daea2-66fb-4c7d-9d4b-d9d82a07b6cd   1 week ago   5s         Failed(CouldntGetPipeline)
openshift-pipelines-main-release-tests-6zj7f   9b3e5d68-70ab-4c23-8872-e7ad7121e60b   1 week ago   5s         Failed(CouldntGetPipeline)
openshift-pipelines-main-release-tests-kkk9t   2fd28c48-388b-4e6a-9ec3-2bcd9dedebc3   1 week ago   5s         Failed(CouldntGetPipeline)
```

To view pipeline runs related to specified named pipelines, enter the following command:

```shell-session
$ opc results pipelinerun list <pipeline_name> -n <namespace_name>
```

The command lists all pipeline runs for pipelines that have names containing` `. For example, if you specify`build`, the command displays all pipeline runs related to pipelines named`build`,`build_123`, or`enhancedbuild`.

Optionally, specify the`--limit` command line option, for example,`--limit=10`. With this setting, the`opc` command displays the specified number of lines containing pipeline run names and then exits. If you add the`--single-page=false` command line option, the command displays the specified number of lines and then prompts you to continue or quit.

#### 1.4.3. Viewing a list of task run names and identifiersCopy link

You can use the`opc` utility to view a lists of names and identifiers of task runs in a namespace or of task runs associated with a pipeline dun.

Prerequisites

- You installed the`opc` utility.
- You configured the`opc` utility to query results from Tekton Results by pipeline run and task run names.

Procedure

To view a list of all task runs in a namespace, enter the following command:

```shell-session
$ opc results taskrun list -n <namespace_name>
```

Optionally, specify the`--limit` command line option, for example,`--limit=10`. With this setting, the`opc` command displays the specified number of lines containing pipeline run names and then exits. If you add the`--single-page=false` command line option, the command displays the specified number of lines and then prompts you to continue or quit.

Optionally, specify the`--labels` parameter, for example,`--labels="app.kubernetes.io/name=test-app, app.kubernetes.io/component=database`. With this setting, the list includes only the task runs that have the specified labels or annotations.

Example output of the`opc results pipelinerun list` command for a namespace

```shell-session
NAME                                           UID                                    STARTED      DURATION   STATUS
openshift-pipelines-main-release-tests-zrgv6-e2e-test             10d6952f-b926-4e4b-a976-519867969ce7   16d ago   12m41s     Failed
openshift-pipelines-main-release-tests-zrgv6-deploy-operator      ab41b63b-16ec-4a32-8b95-f2678eb5c945   16d ago   22s        Succeeded
openshift-pipelines-main-release-tests-zrgv6-provision-cluster    b374df00-5132-4633-91df-3259670756b3   16d ago   12m30s     Succeeded
operator-main-index-4-18-on-pull-request-ml4ww-show-sbom          c5b77784-cd87-4be8-bc12-28957762f382   16d ago   16s        Succeeded
openshift-c4ae3a5a28e19ffc930e7c2aa758d85c-provision-eaas-space   22535d8e-d360-4143-9c0c-4bd0414a22b0   16d ago   17s        Succeeded
```

To view a list of task runs associate with a pipeline run, enter the following command:

```shell-session
$ opc results taskrun list --pipelinerun <pipelinerun_name> -n <namespace_name>
```

Optionally, specify the`--limit` command line option, for example,`--limit=10`. With this setting, the`opc` command displays the specified number of lines containing pipeline run names and then exits. If you add the`--single-page=false` command line option, the command displays the specified number of lines and then prompts you to continue or quit.

Example output of the`opc results taskrun list` command for a pipeline run

+

```shell-session
NAME                                                              UID                                    STARTED   DURATION   STATUS
operator-main-index-4-18-on-pull-request-g95fk-show-sbom          5b405941-0d3e-4f8c-a68a-9ffcc481abf1   16d ago   13s        Succeeded
operator-main-index-4-18-on-pul2b222db723593a186d12f1b82f1a1fd9   89588ae7-aa36-4b62-97d1-5634ee201850   16d ago   36s        Succeeded
operator-fb80434867bc15d89fea82506058f664-fbc-fips-check-oci-ta   7598d44a-4370-459b-8ef0-ae4165c58ba5   16d ago   5m52s      Succeeded
operator-main-index-4-18-on-pull-request-g95fk-validate-fbc       fb80d962-807b-4b63-80cb-6a57d383755a   16d ago   1m26s      Succeeded
operator-main-index-4-18-on-pull-request-g95fk-apply-tags         8a34b46d-74a9-4f20-9e99-a285f7b258d6   16d ago   13s        Succeeded
```

#### 1.4.4. Viewing result information for a pipeline runCopy link

You can use the`opc` utility to view a description of when and how a pipeline run completed, a full manifest for the pipeline run, and any logs that the pipeline run produced.

Prerequisites

- You installed the`opc` utility.
- You configured the`opc` utility to query results from Tekton Results by pipeline run and task run names.
- You have the name or UUID of the pipeline run. You can use the`ocp results list pipelineruns` commands to view names and UUIDs of pipeline runs for which results are available.

Procedure

Use any of the following commands to view the result information for a pipeline run:

To view a description of when and how the pipeline run completed, enter the following command:

```shell-session
$ opc results pipelinerun describe -n <namespace_name> <pipelinerun_name>
```

Alternatively, you can use the pipeline run UUID instead of the name:

```shell-session
$ opc results pipelinerun describe -n <namespace_name> --uid <pipelinerun_uuid>
```

Example output of the`opc results pipelinerun describe` command

```shell-session
Name: operator-main-index-4-18-on-pull-request-7kssl
Namespace: tekton-ecosystem-tenant
Service Account: appstudio-pipeline
Labels:
 app.kubernetes.io/managed-by=pipelinesascode.tekton.dev
 app.kubernetes.io/version=v0.33.0
Annotations:
 appstudio.openshift.io/snapshot=openshift-pipelines-main-b7jj6
 build.appstudio.openshift.io/repo=https://github.com/openshift-pipelines/operator?rev=ba5e62e51af0c88bc6c3fd4201e789bdfc093daa

📌 Status
STARTED          DURATION         STATUS
27d ago          9m54s            Succeeded

⏱ Timeouts
Pipeline:   2h0m0s

⚓ Params
  NAME                          VALUE
  • git-url                     https://github.com/pramodbindal/operator
  • revision                    ba5e62e51af0c88bc6c3fd4201e789bdfc093daa

🗂  Workspaces
  NAME                SUB PATH            WORKSPACE BINDING
  • workspace          ---                VolumeClaimTemplate
  • git-auth           ---                Secret (secret=pac-gitauth-ceqzjt)

📦 Taskruns
  NAME                                                                         TASK NAME
  • operator-main-index-4-18-on-pull-request-7kssl-init                        init
  • operator-main-index-4-18-on-pull-request-7kssl-clone-repository            clone-repository
```

To view the full YAML manifest of the pipeline run, enter the following command:

```shell-session
$ opc results pipelinerun describe -n <namespace_name> --output yaml <pipelinerun_name>
```

Alternatively, you can use the pipeline run UUID instead of the name:

```shell-session
$ opc results pipelinerun describe -n <namespace_name> --output yaml --uid <pipelinerun_uuid>
```

To view the logs associated with the pipeline run, enter the following command:

```shell-session
$ opc results pipelinerun logs -n <namespace_name> <pipelinerun_name>
```

Alternatively, you can use the pipeline run UUID instead of the name:

```shell-session
$ opc results pipelinerun logs -n <namespace_name> --uid <pipelinerun_uuid>
```

Important

Logs that the`opc results pipelinerun logs` displays do not include logs of task runs that completed within this pipeline run. To view these logs, find the names of the task runs in this pipeline run using the`opc results taskrun list --pipelinerun` command and specify the name of the pipeline run. Then use the`opc results taskrun log` command to view the logs for the task runs.

#### 1.4.5. Viewing result information for a task runCopy link

You can use the`opc` utility to view a description of when and how a task run completed, a full manifest for the task run, and any logs that the task run produced.

Prerequisites

- You installed the`opc` utility.
- You configured the`opc` utility to query results from Tekton Results by pipeline run and task run names.
- You have the name or UUID of the task run. You can use the`opc results taskrun list` command to view names or UUIDs of task runs for which results are available.
- If you want to retrieve logs, you configured forwarding logs to LokiStack.

Procedure

Use any of the following commands to view the result information for a task run:

To view a description of when and how the task run completed, enter the following command:

```shell-session
$ opc results taskrun describe -n <namespace_name> <taskrun_name>
```

Alternatively, you can use the task run UUID instead of the name:

```shell-session
$ opc results taskrun describe -n <namespace_name> --uid <taskrun_uuid>
```

Example output of the`opc results taskrun describe` command

```shell-session
Name: operator-main-index-4-18-on-push-gc699-build-images-0
Namespace: tekton-ecosystem-tenant
Service Account: appstudio-pipeline
Labels:
 tekton.dev/pipelineTask=build-images
 tekton.dev/task=buildah-remote-oci-ta
Annotations:
 pipelinesascode.tekton.dev/branch=main
 pipelinesascode.tekton.dev/check-run-id=40080193061

📌 Status
STARTED          DURATION         STATUS
28d ago          3m22s            Failed

⚓ Params
  NAME                          VALUE
  • PLATFORM                    linux-m2xlarge/arm64
  • IMAGE                       quay.io/redhat-user-workloads/tekton-ecosystem
```

To view the full YAML manifest of the task run, enter the following command:

```shell-session
$ opc results taskrun describe -n <namespace_name> --output yaml <taskrun_name>
```

Alternatively, you can use the task run UUID instead of the name:

```shell-session
$ opc results taskrun describe -n <namespace_name> --output yaml --uid <taskrun_uuid>
```

To view the logs associated with the task run, enter the following command:

```shell-session
$ opc results taskrun logs -n <namespace_name> <taskrun_name>
```

Alternatively, you can use the task run UUID instead of the name:

```shell-session
$ opc results taskrun logs -n <namespace_name> --uid <taskrun_uuid>
```

#### 1.4.6. Short names for command-line argumentsCopy link

When using the`opc` utility to query results from Tekton Results by pipeline run and task run names, you can replace long command-line arguments with short versions of their names.

Expand

`pipelinerun`

`pr`

`taskrun`

`tr`

`describe`

`desc`

| Table 1.5. Short names for command-line parameters | Full parameter name | Short parameter name |
| --- | --- | --- |

Show more

## Chapter 2. Understanding the Tekton Results retention policyCopy link

The Tekton Results Retention Policy Agent manages how long Tekton Results and Records are retained in the database before they are pruned. You can configure the retention behavior by using the`tekton-results-config-results-retention-policy` config map in the`tekton-pipelines` namespace.

The retention policy determines when and how old Tekton Results and Records are automatically deleted from the database. You can define global defaults as well as fine-grained rules based on namespaces, labels, annotations, or run statuses.

Use the following sample YAML configuration to define Tekton Results settings, including log retention policies, namespaces, and runtime options:

```yaml
apiVersion: operator.tekton.dev/v1alpha1
kind: TektonConfig
metadata:
  labels:
    openshift-pipelines.tekton.dev/sa-created: "true"
    operator.tekton.dev/release-version: 1.21.0
  name: config
spec:
...
  result:
    disabled: false
    is_external_db: false
    log_level: debug
    loki_stack_name: logging-loki
    loki_stack_namespace: openshift-logging
    options:
      configMaps:
        tekton-results-config-results-retention-policy:
          data:
            defaultRetention: 30m
            policies: |
              - name: "prod-failed-retention"
                selector:
                  matchNamespaces:
                    - "prod-1"
                    - "prod-west"
                  matchStatuses:
                  - "Failed"
                  matchLabels:
                    "env": ["prod"]
                retention: "10m"
              - name: "all-prod-retention"
                selector:
                  matchNamespaces:
                    - "prod-1"
                    - "prod-west"
                retention: "3m"
            runAt: '*/1 * * * *'
          metadata:
            creationTimestamp: null
```

The following fields in the config map define the retention configuration:

Expand

`runAt`

Specifies when the pruning job runs. The value must use a cron schedule format.

`"7 7 * * 7"`(every Sunday at 7:07 a.m.)

`defaultRetention`

Specifies the fallback retention period for Results and Records when no specific policy matches. The value can be a number (in days) or a duration string such as`30d` or`24h`.

`30d`

`maxRetention`

(Deprecated). Used for backward compatibility if`defaultRetention` is not set. This field will be removed in a future release. This field functions in the same way as`defaultRetention`.

N/A

`policies`

Defines a list of fine-grained retention policies for more specific control, in order of precedence. Each policy includes a name, selector, and retention period. For Records and Results matched by a policy’s selector, the policy’s retention period overrides the`defaultRetention`.

None

| | Field | Description | Default |
| --- | --- | --- | --- |

Show more

### 2.1. Fine-grained retention policiesCopy link

You can use the`policies` field to define rules that apply to specific Results. The policies are evaluated in order, and the first policy that matches is applied. If no policies match, the`defaultRetention` value is used.

Each policy includes the following fields:

Expand

`name`

A descriptive name for the policy.

`selector`

Defines criteria for matching Results. All selector types use AND logic, meaning a Result must satisfy all specified conditions to match. You can omit a selector type to match all Results for that criterion.

`matchNamespaces`

A list of namespaces. The policy matches Results in any of the listed namespaces.

`matchLabels`

A map of label keys and possible values. A Result must have all listed label keys, and each value must match one of the values in the list.

`matchAnnotations`

A map of annotation keys and possible values. Works similarly to`matchLabels`.

`matchStatuses`

A list of final statuses such as`Succeeded`,`Failed`,`Cancelled`,`Running`, or`Pending`. The policy matches if the Result’s final status reason is in this list.

`retention`

The retention period for matching Results. The value can be a number (days) or a duration string such as`24h` or`7d`.

| | Field | Description |
| --- | --- | --- |

Show more

The following example shows a config map that defines multiple retention policies:

```yaml
apiVersion: v1
kind: ConfigMap
metadata:
  name: tekton-results-config-results-retention-policy
  namespace: openshift-pipelines
data:
  runAt: "0 2 * * *"
  defaultRetention: "30d"
  policies: |
    - name: "retain-critical-failures-long-term"
      selector:
        matchNamespaces:
          - "production"
          - "prod-east"
        matchLabels:
          "criticality": ["high"]
        matchStatuses:
          - "Failed"
      retention: "180d"
    - name: "retain-annotated-for-debug"
      selector:
        matchAnnotations:
          "debug/retain": ["true"]
      retention: "14d"
    - name: "default-production-policy"
      selector:
        matchNamespaces:
          - "production"
          - "prod-east"
      retention: "60d"
    - name: "short-term-ci-retention"
      selector:
        matchNamespaces:
          - "ci"
      retention: "7d"
```

The following examples describe how the defined retention policies are applied to different Results based on their namespaces, labels, annotations, and statuses.

- A failed Result in the`production` or`prod-east` namespace with the label`criticality: high` is retained for 180 days.
- Any Result with the annotation`debug/retain: "true"` is retained for 14 days.
- Results in the`production` or`prod-east` namespaces are retained for 60 days.
- Results in the`ci` namespace are retained for 7 days.
- All other Results that do not match any policy are retained for the default 30 days.

## Legal NoticeCopy link

Copyright © Red Hat.

The text of and illustrations in this document are licensed by Red Hat under a Creative Commons Attribution–Share Alike 3.0 Unported license ("CC-BY-SA"). An explanation of CC-BY-SA is available at [http://creativecommons.org/licenses/by-sa/3.0/](http://creativecommons.org/licenses/by-sa/3.0/). In accordance with CC-BY-SA, if you distribute this document or an adaptation of it, you must provide the URL for the original version.

Red Hat, as the licensor of this document, waives the right to enforce, and agrees not to assert, Section 4d of CC-BY-SA to the fullest extent permitted by applicable law.

Red Hat, Red Hat Enterprise Linux, the Shadowman logo, JBoss, OpenShift, Fedora, the Infinity logo, and RHCE are trademarks of Red Hat, Inc., registered in the United States and other countries.

Linux® is the registered trademark of Linus Torvalds in the United States and other countries.

Java® is a registered trademark of Oracle and/or its affiliates.

XFS® is a trademark of Silicon Graphics International Corp. or its subsidiaries in the United States and/or other countries.

MySQL® is a registered trademark of MySQL AB in the United States, the European Union and other countries.

Node.js® is an official trademark of Joyent. Red Hat Software Collections is not formally related to or endorsed by the official Joyent Node.js open source or commercial project.

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



## Pipelines as Code

Pipelines as Code | Red Hat OpenShift Pipelines | 1.21 | Red Hat Documentation

 

 

 

 

 

 

 

 

# Pipelines as Code

---

Red Hat OpenShift Pipelines 1.21

## Configuring and using Pipelines as Code

Red Hat OpenShift Documentation Team

Legal Notice

Abstract

This document provides information about configuring and using Pipelines as Code, a subsystem of OpenShift Pipelines that enables defining pipeline templates as part of Git source code repositories.

---

## Chapter 1. About Pipelines as CodeCopy link

With Pipelines as Code, cluster administrators and users with the required privileges can define pipeline templates as part of source code Git repositories. When triggered by a source code push or a pull request for the configured Git repository, Pipelines as Code runs the pipeline and reports the status.

### 1.1. Key featuresCopy link

Pipelines as Code supports the following features:

- Legal Notice
- Pull request status and control on the platform hosting the Git repository.
- GitHub Checks API to set the status of a pipeline run, including rechecks.
- GitHub pull request and commit events.
- Pull request actions in comments, such as`/retest`.
- Git events filtering and a separate pipeline for each event.
- Automatic task resolution in OpenShift Pipelines, including local tasks, Tekton Hub, and remote URLs.
- Retrieval of configurations using GitHub blobs and objects API.
- Access Control List (ACL) over a GitHub organization or using a Prow style`OWNERS` file.
- The`tkn pac` CLI plugin for managing bootstrapping and Pipelines as Code repositories.
- Support for GitHub App, GitHub Webhook, Bitbucket Data Center, and Bitbucket Cloud.

### 1.2. Pipelines as Code conceptsCopy link

Pipelines as Code interacts with your Git repository provider. To use Pipelines as Code, you must first configure this integration.

You can use Pipelines as Code with any number of repositories within your Git repository provider. For each repository, you must create a`Repository` custom resource (CR) within an OpenShift Container Platform namespace that you control. This CR contains information that Pipelines as Code can use to access this repository.

Pipelines as Code starts the pipeline runs for a repository within the namespace in which the`Repository` CR for this repository is located.

Inside your Git repository, you must create a`.tekton` directory and store pipeline run definitions as YAML files in this directory. Each pipeline run that you define must include annotations that determine the events that must trigger this pipeline run.

The definition can reference other YAML files in the same directory. For example, you can define a pipeline in a separate YAML file and refer to this pipeline in the pipeline run file. You can also use annotations in the pipeline run definition to reference task and pipeline resources from Tekton Hub, from HTTP locations, and from other paths in your directory. You can use special variables in the definition to reference execution context, such as the name of the branch.

When a matching event happens, Pipelines as Code creates a`PipelineRun` CR based on the definition that you provided in the repository. Creating a`PipelineRun` CR triggers execution of the pipeline that is defined in the pipeline run.

Pipelines as Code uses the Pipelines as Code resolver to create the`PipelineRun` CR based on your definition. The Pipelines as Code resolver retrieves all of the resources that you referenced by using annotations and adds them to the`PipelineRun` CR. If the Pipelines as Code resolver fails to retrieve any referenced resource, Pipelines as Code logs an error and does not create a pipeline run.

Pipelines as Code also replaces dynamic variables in your pipeline run definition with their values.

Important

By default, when creating a`PipelineRun` CR for a pull request or push event, Pipelines as Code uses the definitions from the`.tekton` directory of the source branch of the pull request or push event. If the pull request or push event modifies the`.tekton` directory, Pipelines as Code uses the modified version.

You can define the`pipelinerun_provenance: "default_branch"` setting in the`Repository` CR to change this behaviour. If you specify this setting, Pipelines as Code always uses definitions from the`.tekton` directory of the default branch configured on the Git repository provider, such as`main`,`master`, or`trunk`.

## Chapter 2. Installing and configuring Pipelines as CodeCopy link

You can install Pipelines as Code as a part of Red Hat OpenShift Pipelines installation.

### 2.1. Installing Pipelines as Code on an OpenShift Container PlatformCopy link

Pipelines as Code is installed in the`openshift-pipelines` namespace when you install the Red Hat OpenShift Pipelines Operator. For more details, see Installing OpenShift Pipelines in the Additional resources section.

To disable the default installation of Pipelines as Code with the Operator, set the value of the`enable` parameter to`false` in the`TektonConfig` custom resource.

```yaml
apiVersion: operator.tekton.dev/v1alpha1
kind: TektonConfig
metadata:
  name: config
spec:
  platforms:
    openshift:
      pipelinesAsCode:
        enable: false
        settings:
          application-name: Pipelines as Code CI
          auto-configure-new-github-repo: "false"
          bitbucket-cloud-check-source-ip: "true"
          hub-catalog-name: tekton
          hub-url: https://api.hub.tekton.dev/v1
          remote-tasks: "true"
          secret-auto-create: "true"
# ...
```

Optionally, you can run the following command:

```shell-session
$ oc patch tektonconfig config --type="merge" -p '{"spec": {"platforms": {"openshift":{"pipelinesAsCode": {"enable": false}}}}}'
```

To enable the default installation of Pipelines as Code with the Red Hat OpenShift Pipelines Operator, set the value of the`enable` parameter to`true` in the`TektonConfig` custom resource:

```yaml
apiVersion: operator.tekton.dev/v1alpha1
kind: TektonConfig
metadata:
  name: config
spec:
  platforms:
    openshift:
      pipelinesAsCode:
        enable: true
        settings:
          application-name: Pipelines as Code CI
          auto-configure-new-github-repo: "false"
          bitbucket-cloud-check-source-ip: "true"
          hub-catalog-name: tekton
          hub-url: https://api.hub.tekton.dev/v1
          remote-tasks: "true"
          secret-auto-create: "true"
# ...
```

Optionally, you can run the following command:

```shell-session
$ oc patch tektonconfig config --type="merge" -p '{"spec": {"platforms": {"openshift":{"pipelinesAsCode": {"enable": true}}}}}'
```

### 2.2. Installing Pipelines as Code CLICopy link

Cluster administrators can use the`tkn pac` and`opc` CLI tools on local machines or as containers for testing. The`tkn pac` and`opc` CLI tools are installed automatically when you install the`tkn` CLI for Red Hat OpenShift Pipelines.

You can install the`tkn pac` and`opc` version`1.21.0` binaries for the supported platforms:

- [Linux (x86_64, amd64)](https://mirror.openshift.com/pub/cgw/pipelines/1.21.0/tkn-linux-amd64.tar.gz)
- [Linux on IBM zSystems and IBM® LinuxONE (s390x)](https://mirror.openshift.com/pub/cgw/pipelines/1.21.0/tkn-linux-s390x.tar.gz)
- [Linux on IBM Power (ppc64le)](https://mirror.openshift.com/pub/cgw/pipelines/1.21.0/tkn-linux-ppc64le.tar.gz)
- [Linux on ARM (aarch64, arm64)](https://mirror.openshift.com/pub/cgw/pipelines/1.21.0/tkn-linux-arm64.tar.gz)
- [macOS](https://mirror.openshift.com/pub/cgw/pipelines/1.21.0/tkn-macos-amd64.tar.gz)
- [Windows](https://mirror.openshift.com/pub/cgw/pipelines/1.21.0/tkn-windows-amd64.zip)

### 2.3. Customizing Pipelines as Code configurationCopy link

To customize Pipelines as Code, cluster administrators can configure the following parameters in the`TektonConfig` custom resource, in the`platforms.openshift.pipelinesAsCode.settings` spec:

Expand

`application-name`

The name of the application. For example, the name displayed in the GitHub Checks labels.

`"Pipelines as Code CI"`

`secret-auto-create`

Indicates whether or not a secret should be automatically created using the token generated in the GitHub application. This secret can then be used with private repositories.

`enabled`

`remote-tasks`

When enabled, allows remote tasks from pipeline run annotations.

`enabled`

`hub-url`

The base URL for the [Artifact Hub](https://artifacthub.io/).

[https://artifacthub.io](https://artifacthub.io/)

`hub-catalog-name`

The Tekton Hub catalog name.

`tekton`

`tekton-dashboard-url`

The URL of the Tekton Hub dashboard. Pipelines as Code uses this URL to generate a`PipelineRun` URL on the Tekton Hub dashboard.

NA

`bitbucket-cloud-check-source-ip`

Indicates whether to secure the service requests by querying IP ranges for a public Bitbucket. Changing the parameter’s default value might result into a security issue.

`enabled`

`bitbucket-cloud-additional-source-ip`

Indicates whether to provide an additional set of IP ranges or networks, which are separated by commas.

NA

`max-keep-run-upper-limit`

A maximum limit for the`max-keep-run` value for a pipeline run.

NA

`default-max-keep-runs`

A default limit for the`max-keep-run` value for a pipeline run. If defined, the value is applied to all pipeline runs that do not have a`max-keep-run` annotation.

NA

`auto-configure-new-github-repo`

Configures new GitHub repositories automatically. Pipelines as Code sets up a namespace and creates a custom resource for your repository. This parameter is only supported with GitHub applications.

`disabled`

`auto-configure-repo-namespace-template`

Configures a template to automatically generate the namespace for your new repository, if`auto-configure-new-github-repo` is enabled.

`{repo_name}-pipelines`

`auto-configure-repo-repository-template`

Configures a template to automatically generate the name for your new`Repository` custom resource (CR), if`auto-configure-new-github-repo` is enabled.

`{{repo_name}}-repo-cr`

`error-log-snippet`

Enables or disables the view of log snippets for failed tasks within a pipeline. You can disable this parameter to prevent potential data leakage. The snippets are automatically truncated to 65,000 characters.

`true`

`error-log-snippet-number-of-lines`

Configures the number of lines displayed in error log snippets.

`3`

`error-detection-from-container-logs`

Enables or disables the inspection of container logs to detect error message and expose them as annotations on the pull request. This setting applies only if you are using the GitHub app.

`true`

`error-detection-max-number-of-lines`

The maximum number of lines inspected in the container logs to search for error messages. Set to`-1` to inspect an unlimited number of lines.

50

`secret-github-app-token-scoped`

If set to`true`, the GitHub access token that Pipelines as Code generates using the GitHub app is scoped only to the repository from which Pipelines as Code fetches the pipeline definition. If set to`false`, you can use both the`TektonConfig` custom resource and the`Repository` custom resource to scope the token to additional repositories.

`true`

`secret-github-app-scope-extra-repos`

Additional repositories for scoping the generated GitHub access token.

`enable-cancel-in-progress-on-pull-requests`

Automatically cancels in-progress pipeline runs for all pull requests when a new commit is added. This ensures the old pipeline run is cancelled only after the new one has successfully started. The`cancel-in-progress` annotation on individual runs still applies and overrides the global setting.

`false`

`enable-cancel-in-progress-on-push`

Automatically cancels in-progress pipeline runs for all push commits when a new commit is pushed. The old pipeline run is cancelled only after the new one has successfully started. The`cancel-in-progress` annotation on individual runs still applies and overrides this global setting.

`false`

| Table 2.1. Customizing Pipelines as Code configuration | Parameter | Description | Default |
| --- | --- | --- | --- |

Show more

### 2.4. Configuring additional Pipelines as Code controllers to support additional GitHub appsCopy link

By default, you can configure Pipelines as Code to interact with one GitHub app. In some cases you might need to use more than one GitHub app, for example, if you need to use different GitHub accounts or different GitHub instances such as GitHub Enterprise or GitHub SaaS. If you want to use more than one GitHub app, you must configure an additional Pipelines as Code controller for every additional GitHub app.

Procedure

In the`TektonConfig` custom resource, add the`additionalPACControllers` section to the`platforms.openshift.pipelinesAsCode` spec, as in the following example:

Example`additionalPACControllers` section

```yaml
apiVersion: operator.tekton.dev/v1
kind: TektonConfig
metadata:
  name: config
spec:
  platforms:
    openshift:
      pipelinesAsCode:
        additionalPACControllers:
          pac_controller_2:  
```

1

```yaml

            enable: true    
```

2

```yaml

            secretName: pac_secret_2  
```

3

```yaml

            settings: #  
```

4

```yaml

# ...
```

1

The name of the controller. This name must be unique and not exceed 25 characters in length.

2

This parameter is optional. Set this parameter to`true` to enable the additional controller or to`false` to disable the additional controller. The default vaule is`true`.

3

Set this parameter to the name of a secret that you must create for the GitHub app.

4

This section is optional. In this section, you can set any Pipelines as Code settings for this controller if the settings must be different from the main Pipelines as Code controller.

1. Optional: If you want to use more than two GitHub apps, create additional sections under the`pipelinesAsCode.additionalPACControllers` spec to configure a Pipelines as Code controller for every GitHub instance. Use a unique name for every controller.

## Chapter 3. Using Pipelines as Code with a Git repository hosting service providerCopy link

After installing Pipelines as Code, cluster administrators can configure a Git repository hosting service provider. Currently, the following services are supported:

- GitHub App
- GitHub Webhook
- GitLab
- Bitbucket Data Center
- Bitbucket Cloud

Note

GitHub App is the recommended service for using with Pipelines as Code.

### 3.1. Using Pipelines as Code with a GitHub AppCopy link

GitHub Apps act as a point of integration with Red Hat OpenShift Pipelines and bring the advantage of Git-based workflows to OpenShift Pipelines. Cluster administrators can configure a single GitHub App for all cluster users. For GitHub Apps to work with Pipelines as Code, ensure that the webhook of the GitHub App points to the Pipelines as Code controller route (or ingress endpoint) that listens for GitHub events.

There are three ways to set up a GitHub app for Pipelines as Code:

- Use the`tkn` command line utility.
- Use the Administrator perspective of the web console.
- Set up the app manually in GitHub and then create a secret for Pipelines as Code.

By default, Pipelines as Code can communicate with one GitHub app. If you configured additional Pipelines as Code controllers to communicate with additional GitHub apps, configure each of the GitHub apps separately. You must set up GitHub apps for any additional controllers manually.

#### 3.1.1. Configuring a GitHub App using the command line interfaceCopy link

You can use the`tkn` command line utility to create a GitHub app and configure the Pipelines as Code controller for the GitHub app.

Important

If you created additional Pipelines as Code controllers to support additional GitHub apps, you can use this procedure only for the main controller. To create a GitHub app for an additional controller, use the manual procedure.

Prerequisites

- You are logged on to the OpenShift Container Platform cluster as a cluster administrator.
- You installed the`tkn` command line utility with the`tkn pac` plugin.

Procedure

Enter the following command:

```shell-session
$ tkn pac bootstrap github-app
```

This command assumes that your account uses a standard github.com API endpoint. If you use a different GitHub API endpoint, for example, if you use GitHub Enterprise, use the`--github-api-url` option to specify the endpoint, as in the following example:

Example command

```shell-session
$ tkn pac bootstrap github-app --github-api-url https://github.com/enterprises/example-enterprise
```

#### 3.1.2. Creating a GitHub App in administrator perspectiveCopy link

As a cluster administrator, you can configure your GitHub App with the OpenShift Container Platform cluster to use Pipelines as Code. This configuration allows you to execute a set of tasks required for build deployment.

Important

If you created additional Pipelines as Code controllers to support additional GitHub apps, you can use this procedure only for the main controller. To create a GitHub app for an additional controller, use the manual procedure.

Prerequisites

You have installed the Red Hat OpenShift Pipelines`pipelines-1.21` operator from the Operator Hub.

Procedure

1. In the administrator perspective, navigate to Pipelines using the navigation pane.
2. Click Setup GitHub App on the Pipelines page.
3. Enter your GitHub App name. For example,`pipelines-ci-clustername-testui`.
4. Click Setup.
5. Enter your Git password when prompted in the browser.
6. Click Create GitHub App for, where` ` is your GitHub user name.

Verification

After successful creation of the GitHub App, the OpenShift Container Platform web console opens and displays the details about the application.

The details of the GitHub App are saved as a secret in the`openShift-pipelines` namespace.

To view details such as name, link, and secret associated with the GitHub applications, navigate to Pipelines and click View GitHub App.

#### 3.1.3. Configuring a GitHub App manually and creating a secret for Pipelines as CodeCopy link

You can use the GitHub user interface to create a GitHub app. Then you must create a secret that configures Pipelines as Code to connect to GitHub app.

If you created additional Pipelines as Code controllers to support additional GitHub apps, you must use this procedure for the additional controllers.

Procedure

Provide the following information in the GitHub App form:

Webhook URL: The Pipelines as Code route or ingress URL. You can find it by running the following command:

```shell-session
$ echo https://$(oc get route -n openshift-pipelines pipelines-as-code-controller -o jsonpath='{.spec.host}')
```

Alternatively, to configure the GitHub app for an additional Pipelines as Code controller, replace`pipelines-as-code-controller` with the name of the controller that you configured, as in the following example:

Example command

```shell-session
$ echo https://$(oc get route -n openshift-pipelines pac_controller_2 -o jsonpath='{.spec.host}')
```

Webhook secret: An arbitrary secret. You can generate a secret by running the following command:

```shell-session
$ openssl rand -hex 20
```

- GitHub Application Name:`OpenShift Pipelines`
- Homepage URL: OpenShift Console URL

Select the following items in the Repository permissions section:

- Checks:`Read & Write`
- Contents:`Read & Write`
- Issues:`Read & Write`
- Metadata:`Read-only`
- Pull request:`Read & Write`

Select the following item in the Organization permissions section:

- Members:`Read-only`

Subscribe to the following events:

- Check run
- Check suite
- Commit comment
- Issue comment
- Pull request
- Push

Configure Pipelines as Code to access the newly created GitHub App by entering the following command:

```shell-session
$ oc -n openshift-pipelines create secret generic pipelines-as-code-secret \ 
```

1

```shell-session

        --from-literal github-private-key="$(cat <PATH_PRIVATE_KEY>)" \ 
```

2

```shell-session

        --from-literal github-application-id="<APP_ID>" \ 
```

3

```shell-session

        --from-literal webhook.secret="<WEBHOOK_SECRET>" 
```

4

1

If you created additional Pipelines as Code controllers to support additional GitHub apps and you are configuring the app for an additional controller, replace`pipelines-as-code-secret` with the name that you configured in the`secretName` parameter for the controller.

2

The path to the private key you downloaded while configuring the GitHub App.

3

The App ID of the GitHub App.

4

The webhook secret provided when you created the GitHub App.

Note

Pipelines as Code works automatically with GitHub Enterprise by detecting the header set from GitHub Enterprise and using it for the GitHub Enterprise API authorization URL.

#### 3.1.4. Scoping the GitHub token to additional repositoriesCopy link

Pipelines as Code uses the GitHub app to generate a GitHub access token. Pipelines as Code uses this token to retrieve the pipeline payload from the repository and to enable the CI/CD processes to interact with GitHub repositories.

By default, the access token is scoped only to the repository from which Pipelines as Code retrieves the pipeline definition. In some cases, you might want the token to have access to additional repositories. For example, there might be a CI repository where the`.tekton/pr.yaml` file and source payload are located, but the build process defined in`pr.yaml` fetches tasks from a separate private CD repository.

You can extend the scope of the GitHub token in two ways:

- Global configuration: You can extend the GitHub token to a list of repositories in different namespaces. You must have administrative permissions to set this configuration.
- Repository level configuration: You can extend the GitHub token to a list of repositories that exist in the same namespace as the original repository. You do not need administrative permissions to set this configuration.

Procedure

To set global configuration for scoping the GitHub token, in the`TektonConfig` CR, in the`pipelinesAsCode.settings` spec, specify the additional repositories in the`secret-github-app-scope-extra-repos` parameter, as in the following example:

```yaml
apiVersion: operator.tekton.dev/v1alpha1
kind: TektonConfig
metadata:
  name: config
spec:
  platforms:
    openshift:
      pipelinesAsCode:
        enable: true
        settings:
          secret-github-app-token-scoped: false
          secret-github-app-scope-extra-repos: "owner2/project2, owner3/project3"
```

To set repository level configuration for scoping the GitHub token, specify the additional repositories in the`github_app_token_scope_repos` parameter of the`Repository` CR, as in the following example:

```yaml
apiVersion: "pipelinesascode.tekton.dev/v1alpha1"
kind: Repository
metadata:
  name: test
  namespace: test-repo
spec:
  url: "https://github.com/linda/project"
  settings:
    github_app_token_scope_repos:
    - "owner/project"
    - "owner1/project1"
```

In this example, the`Repository` custom resource is associated with the`linda/project` repository in the`test-repo` namespace. The scope of the generated GitHub token is extended to the`owner/project` and`owner1/project1` repositories, and the`linda/project` repository. These repositories must exist under the`test-repo` namespace.

Note

The additional repositories can be public or private, but must reside in the same namespace as the repository with which the`Repository` resource is associated.

If any of the repositories do not exist in the namespace, the scoping of the GitHub token fails with an error message:

```shell-session
failed to scope GitHub token as repo owner1/project1 does not exist in namespace test-repo
```

1. In the`TektonConfig` custom resource (CR), in the`pipelinesAsCode.settings` spec, set the`secret-github-app-token-scoped` parameter to`false`. This setting enables scoping the GitHub token to private and public repositories listed in the global and repository level configuration.

Result

The generated GitHub token enables access to the additional repositories that you configured in the global and repository level configuration, and the original repository where the Pipelines as Code payload files are located.

If you provide both global configuration and repository level configuration, the token is scoped to all the repositories from both configurations, as in the following example.

`TektonConfig` custom resource

```yaml
apiVersion: operator.tekton.dev/v1alpha1
kind: TektonConfig
metadata:
  name: config
spec:
  platforms:
    openshift:
      pipelinesAsCode:
        enable: true
        settings:
          secret-github-app-token-scoped: false
          secret-github-app-scope-extra-repos: "owner2/project2, owner3/project3"
```

`Repository` custom resource

```yaml
apiVersion: "pipelinesascode.tekton.dev/v1alpha1"
kind: Repository
metadata:
 name: test
 namespace: test-repo
spec:
 url: "https://github.com/linda/project"
 settings:
   github_app_token_scope_repos:
   - "owner/project"
   - "owner1/project1"
```

The GitHub token is scoped to the`owner/project`,`owner1/project1`,`owner2/project2`,`owner3/project3`, and`linda/project` respositories.

### 3.2. Using Pipelines as Code with GitHub WebhookCopy link

Use Pipelines as Code with GitHub Webhook on your repository if you cannot create a GitHub App. However, using Pipelines as Code with GitHub Webhook does not give you access to the GitHub Check Runs API. The status of the tasks is added as comments on the pull request and is unavailable under the Checks tab.

Note

Pipelines as Code with GitHub Webhook does not support GitOps comments such as`/retest` and`/ok-to-test`. To restart the continuous integration (CI), create a new commit to the repository. For example, to create a new commit without any changes, you can use the following command:

```shell-session
$ git --amend -a --no-edit && git push --force-with-lease <origin> <branchname>
```

Prerequisites

For authorization, create a personal access token on GitHub.

To generate a secure and fine-grained token, restrict its scope to a specific repository and grant the following permissions:

Expand

Administration

Read-only

Metadata

Read-only

Content

Read-only

Commit statuses

Read and Write

Pull request

Read and Write

Webhooks

Read and Write

| Table 3.1. Permissions for fine-grained tokens | Name | Access |
| --- | --- | --- |

Show more

To use classic tokens, set the scope as`public_repo` for public repositories and`repo` for private repositories. In addition, provide a short token expiration period and note the token in an alternate location.

Note

If you want to configure the webhook using the`tkn pac` CLI, add the`admin:repo_hook` scope.

Procedure

Configure the webhook and create a`Repository` custom resource (CR).

To configure a webhook and create a`Repository` CR automatically using the`tkn pac` CLI tool, use the following command:

```shell-session
$ tkn pac create repo
```

Sample interactive output

```shell-session
? Enter the Git repository url (default: https://github.com/owner/repo):
? Please enter the namespace where the pipeline should run (default: repo-pipelines):
! Namespace repo-pipelines is not found
? Would you like me to create the namespace repo-pipelines? Yes
✓ Repository owner-repo has been created in repo-pipelines namespace
✓ Setting up GitHub Webhook for Repository https://github.com/owner/repo
👀 I have detected a controller url: https://pipelines-as-code-controller-openshift-pipelines.apps.example.com
? Do you want me to use it? Yes
? Please enter the secret to configure the webhook for payload validation (default: sJNwdmTifHTs):  sJNwdmTifHTs
ℹ ️You now need to create a GitHub personal access token, please checkout the docs at https://docs.github.com/en/authentication/keeping-your-account-and-data-secure/creating-a-personal-access-token for the required scopes
? Please enter the GitHub access token:  ****************************************
✓ Webhook has been created on repository owner/repo
🔑 Webhook Secret owner-repo has been created in the repo-pipelines namespace.
🔑 Repository CR owner-repo has been updated with webhook secret in the repo-pipelines namespace
ℹ Directory .tekton has been created.
✓ We have detected your repository using the programming language Go.
✓ A basic template has been created in /home/Go/src/github.com/owner/repo/.tekton/pipelinerun.yaml, feel free to customize it.
```

To configure a webhook and create a`Repository` CR manually, perform the following steps:

On your OpenShift cluster, extract the public URL of the Pipelines as Code controller.

```shell-session
$ echo https://$(oc get route -n openshift-pipelines pipelines-as-code-controller -o jsonpath='{.spec.host}')
```

On your GitHub repository or organization, perform the following steps:

Add a webhook secret and note it in an alternate location. With`openssl` installed on your local machine, generate a random secret.

```shell-session
$ openssl rand -hex 20
```

1. Go to Settings –> Webhooks and click Add webhook.
2. Set the Payload URL to the Pipelines as Code controller public URL.
3. Select the content type as application/json.
4. Click Let me select individual events and select these events: Commit comments, Issue comments, Pull request, and Pushes.
5. Click Add webhook.

On your OpenShift cluster, create a`Secret` object with the personal access token and webhook secret.

```shell-session
$ oc -n target-namespace create secret generic github-webhook-config \
  --from-literal provider.token="<GITHUB_PERSONAL_ACCESS_TOKEN>" \
  --from-literal webhook.secret="<WEBHOOK_SECRET>"
```

Create a`Repository` CR.

Example:`Repository` CR

```yaml
apiVersion: "pipelinesascode.tekton.dev/v1alpha1"
kind: Repository
metadata:
  name: my-repo
  namespace: target-namespace
spec:
  url: "https://github.com/owner/repo"
  git_provider:
    secret:
      name: "github-webhook-config"
      key: "provider.token" # Set this if you have a different key in your secret
    webhook_secret:
      name: "github-webhook-config"
      key: "webhook.secret" # Set this if you have a different key for your secret
```

Note

Pipelines as Code assumes that the OpenShift`Secret` object and the`Repository` CR are in the same namespace.

Optional: For an existing`Repository` CR, add multiple GitHub Webhook secrets or provide a substitute for a deleted secret.

Add a webhook by using the`tkn pac` CLI tool.

Example: Additional webhook by using the`tkn pac` CLI

```shell-session
$ tkn pac webhook add -n repo-pipelines
```

Sample interactive output

```shell-session
✓ Setting up GitHub Webhook for Repository https://github.com/owner/repo
👀 I have detected a controller url: https://pipelines-as-code-controller-openshift-pipelines.apps.example.com
? Do you want me to use it? Yes
? Please enter the secret to configure the webhook for payload validation (default: AeHdHTJVfAeH):  AeHdHTJVfAeH
✓ Webhook has been created on repository owner/repo
🔑 Secret owner-repo has been updated with webhook secert in the repo-pipelines namespace.
```

1. Update the`webhook.secret` key in the existing OpenShift`Secret` object.

Optional: For an existing`Repository` CR, update the personal access token.

Update the personal access token by using the`tkn pac` CLI tool.

Example: Updating personal access token by using the`tkn pac` CLI

```shell-session
$ tkn pac webhook update-token -n repo-pipelines
```

Sample interactive output

```shell-session
? Please enter your personal access token:  ****************************************
🔑 Secret owner-repo has been updated with new personal access token in the repo-pipelines namespace.
```

Alternatively, update the personal access token by modifying the`Repository` CR.

Find the name of the secret in the`Repository` CR.

```yaml
apiVersion: "pipelinesascode.tekton.dev/v1alpha1"
kind: Repository
metadata:
  name: my-repo
  namespace: target-namespace
spec:
# ...
  git_provider:
    secret:
      name: "github-webhook-config"
# ...
```

Use the`oc patch` command to update the values of the`$NEW_TOKEN` in the`$target_namespace` namespace.

```shell-session
$ oc -n $target_namespace patch secret github-webhook-config -p "{\"data\": {\"provider.token\": \"$(echo -n $NEW_TOKEN|base64 -w0)\"}}"
```

Additional resources

- [GitHub Webhook documentation on GitHub](https://docs.github.com/en/developers/webhooks-and-events/webhooks/creating-webhooks)
- [GitHub Check Runs documentation on GitHub](https://docs.github.com/en/rest/guides/getting-started-with-the-checks-api)
- [Creating a personal access token on GitHub](https://docs.github.com/en/authentication/keeping-your-account-and-data-secure/creating-a-personal-access-token)
- [Classic tokens with pre-filled permissions](https://github.com/settings/tokens/new?description=pipelines-as-code-token&amp;scopes=repo)

### 3.3. Using Pipelines as Code with GitLabCopy link

If your organization or project uses GitLab as the preferred platform, you can use Pipelines as Code for your repository with a webhook on GitLab.

Prerequisites

For authorization, generate a personal access token as the manager of the project or organization on GitLab.

Note

- If you want to configure the webhook using the`tkn pac` CLI, add the`admin:repo_hook` scope to the token.
- Using a token scoped for a specific project cannot provide API access to a merge request (MR) sent from a forked repository. In such cases, Pipelines as Code displays the result of a pipeline as a comment on the MR.

Procedure

Configure the webhook and create a`Repository` custom resource (CR).

To configure a webhook and create a`Repository` CR automatically using the`tkn pac` CLI tool, use the following command:

```shell-session
$ tkn pac create repo
```

Sample interactive output

```shell-session
? Enter the Git repository url (default: https://gitlab.com/owner/repo):
? Please enter the namespace where the pipeline should run (default: repo-pipelines):
! Namespace repo-pipelines is not found
? Would you like me to create the namespace repo-pipelines? Yes
✓ Repository repositories-project has been created in repo-pipelines namespace
✓ Setting up GitLab Webhook for Repository https://gitlab.com/owner/repo
? Please enter the project ID for the repository you want to be configured,
  project ID refers to an unique ID (e.g. 34405323) shown at the top of your GitLab project : 17103
👀 I have detected a controller url: https://pipelines-as-code-controller-openshift-pipelines.apps.example.com
? Do you want me to use it? Yes
? Please enter the secret to configure the webhook for payload validation (default: lFjHIEcaGFlF):  lFjHIEcaGFlF
ℹ ️You now need to create a GitLab personal access token with `api` scope
ℹ ️Go to this URL to generate one https://gitlab.com/-/profile/personal_access_tokens, see https://is.gd/rOEo9B for documentation
? Please enter the GitLab access token:  **************************
? Please enter your GitLab API URL::  https://gitlab.com
✓ Webhook has been created on your repository
🔑 Webhook Secret repositories-project has been created in the repo-pipelines namespace.
🔑 Repository CR repositories-project has been updated with webhook secret in the repo-pipelines namespace
ℹ Directory .tekton has been created.
✓ A basic template has been created in /home/Go/src/gitlab.com/repositories/project/.tekton/pipelinerun.yaml, feel free to customize it.
```

To configure a webhook and create a`Repository` CR manually, perform the following steps:

On your OpenShift cluster, extract the public URL of the Pipelines as Code controller.

```shell-session
$ echo https://$(oc get route -n openshift-pipelines pipelines-as-code-controller -o jsonpath='{.spec.host}')
```

On your GitLab project, perform the following steps:

Add a webhook secret and note it in an alternate location. With`openssl` installed on your local machine, generate a random secret.

```shell-session
$ openssl rand -hex 20
```

1. Use the left sidebar to go to Settings –> Webhooks.
2. Set the URL to the Pipelines as Code controller public URL.
3. Click Let me select individual events and select these events: Commit comments, Issue comments, Pull request, and Pushes.
4. Click Save changes.

On your OpenShift cluster, create a`Secret` object with the personal access token and webhook secret.

```shell-session
$ oc -n target-namespace create secret generic gitlab-webhook-config \
  --from-literal provider.token="<GITLAB_PERSONAL_ACCESS_TOKEN>" \
  --from-literal webhook.secret="<WEBHOOK_SECRET>"
```

Create a`Repository` CR.

Example:`Repository` CR

```yaml
apiVersion: "pipelinesascode.tekton.dev/v1alpha1"
kind: Repository
metadata:
  name: my-repo
  namespace: target-namespace
spec:
  url: "https://gitlab.com/owner/repo" # The repository URL
  git_provider:
    #url: "https://gitlab.example.com/" 
```

1

```yaml

    secret:
      name: "gitlab-webhook-config"
      key: "provider.token" # Set this if you have a different key in your secret
    webhook_secret:
      name: "gitlab-webhook-config"
      key: "webhook.secret" # Set this if you have a different key for your secret
```

1

If you are using a private instance of GitLab and not GitLab.com, uncomment this field and set it to the URL of your GitLab API. The GitLab API is the same host as the repository. For example, if the repository is`https://gitlab.example.com/owner/repo`, the API URL is`https://gitlab.example.com/`.

Note

- Pipelines as Code assumes that the OpenShift`Secret` object and the`Repository` CR are in the same namespace.

Optional: For an existing`Repository` CR, add multiple GitLab Webhook secrets or provide a substitute for a deleted secret.

Add a webhook by using the`tkn pac` CLI tool.

Example: Adding additional webhook by using the`tkn pac` CLI

```shell-session
$ tkn pac webhook add -n repo-pipelines
```

Sample interactive output

```shell-session
✓ Setting up GitLab Webhook for Repository https://gitlab.com/owner/repo
👀 I have detected a controller url: https://pipelines-as-code-controller-openshift-pipelines.apps.example.com
? Do you want me to use it? Yes
? Please enter the secret to configure the webhook for payload validation (default: AeHdHTJVfAeH):  AeHdHTJVfAeH
✓ Webhook has been created on repository owner/repo
🔑 Secret owner-repo has been updated with webhook secert in the repo-pipelines namespace.
```

1. Update the`webhook.secret` key in the existing OpenShift`Secret` object.

Optional: For an existing`Repository` CR, update the personal access token.

Update the personal access token by using the`tkn pac` CLI tool.

Example: Updating personal access token by using the`tkn pac` CLI

```shell-session
$ tkn pac webhook update-token -n repo-pipelines
```

Sample interactive output

```shell-session
? Please enter your personal access token:  ****************************************
🔑 Secret owner-repo has been updated with new personal access token in the repo-pipelines namespace.
```

Alternatively, update the personal access token by modifying the`Repository` CR.

Find the name of the secret in the`Repository` CR.

```yaml
...
spec:
  git_provider:
    secret:
      name: "gitlab-webhook-config"
...
```

Use the`oc patch` command to update the values of the`$NEW_TOKEN` in the`$target_namespace` namespace.

```shell-session
$ oc -n $target_namespace patch secret gitlab-webhook-config -p "{\"data\": {\"provider.token\": \"$(echo -n $NEW_TOKEN|base64 -w0)\"}}"
```

Additional resources

- [GitLab Webhook documentation on GitLab](https://docs.gitlab.com/ee/user/profile/personal_access_tokens.html)

### 3.4. Using Pipelines as Code with Bitbucket CloudCopy link

If your organization or project uses Bitbucket Cloud as the preferred platform, you can use Pipelines as Code for your repository with a webhook on Bitbucket Cloud.

Prerequisites

Create an app password on Bitbucket Cloud.

Check the following boxes to add appropriate permissions to the token:

Pull requests:`Read`,`Write`

Note

- If you want to configure the webhook using the`tkn pac` CLI, add the`Webhooks`:`Read` and`Write` permission to the token.
- Once generated, save a copy of the password or token in an alternate location.

Procedure

Configure the webhook and create a`Repository` CR.

To configure a webhook and create a`Repository` CR automatically using the`tkn pac` CLI tool, use the following command:

```shell-session
$ tkn pac create repo
```

Sample interactive output

```shell-session
? Enter the Git repository url (default: https://bitbucket.org/workspace/repo):
? Please enter the namespace where the pipeline should run (default: repo-pipelines):
! Namespace repo-pipelines is not found
? Would you like me to create the namespace repo-pipelines? Yes
✓ Repository workspace-repo has been created in repo-pipelines namespace
✓ Setting up Bitbucket Webhook for Repository https://bitbucket.org/workspace/repo
? Please enter your bitbucket cloud username:  <username>
ℹ ️You now need to create a Bitbucket Cloud app password, please checkout the docs at https://is.gd/fqMHiJ for the required permissions
? Please enter the Bitbucket Cloud app password:  ************************************
👀 I have detected a controller url: https://pipelines-as-code-controller-openshift-pipelines.apps.example.com
? Do you want me to use it? Yes
✓ Webhook has been created on repository workspace/repo
🔑 Webhook Secret workspace-repo has been created in the repo-pipelines namespace.
🔑 Repository CR workspace-repo has been updated with webhook secret in the repo-pipelines namespace
ℹ Directory .tekton has been created.
✓ A basic template has been created in /home/Go/src/bitbucket/repo/.tekton/pipelinerun.yaml, feel free to customize it.
```

To configure a webhook and create a`Repository` CR manually, perform the following steps:

On your OpenShift cluster, extract the public URL of the Pipelines as Code controller.

```shell-session
$ echo https://$(oc get route -n openshift-pipelines pipelines-as-code-controller -o jsonpath='{.spec.host}')
```

On Bitbucket Cloud, perform the following steps:

1. Use the left navigation pane of your Bitbucket Cloud repository to go to Repository settings –> Webhooks and click Add webhook.
2. Set a Title. For example, "Pipelines as Code".
3. Set the URL to the Pipelines as Code controller public URL.
4. Select these events: Repository: Push, Pull Request: Created, Pull Request: Updated, and Pull Request: Comment created.
5. Click Save.

On your OpenShift cluster, create a`Secret` object with the app password in the target namespace.

```shell-session
$ oc -n target-namespace create secret generic bitbucket-cloud-token \
  --from-literal provider.token="<BITBUCKET_APP_PASSWORD>"
```

Create a`Repository` CR.

Example:`Repository` CR

```yaml
apiVersion: "pipelinesascode.tekton.dev/v1alpha1"
kind: Repository
metadata:
  name: my-repo
  namespace: target-namespace
spec:
  url: "https://bitbucket.com/workspace/repo"
  branch: "main"
  git_provider:
    user: "<BITBUCKET_USERNAME>" 
```

1

```yaml

    secret:
      name: "bitbucket-cloud-token" 
```

2

```yaml

      key: "provider.token" # Set this if you have a different key in your secret
```

1

You can only reference a user by the`ACCOUNT_ID` in an owner file.

2

Pipelines as Code assumes that the secret referred in the`git_provider.secret` spec and the`Repository` CR is in the same namespace.

Note

Bitbucket Cloud does not support webhook secrets. To secure the payload and prevent hijacking of the CI, Pipelines as Code fetches the list of Bitbucket Cloud IP addresses and ensures that the webhook receptions come only from those IP addresses.

- To disable the default behavior, set the`bitbucket-cloud-check-source-ip` parameter to`false` in the`TektonConfig` custom resource, in the`pipelinesAsCode.settings` spec.
- To allow additional safe IP addresses or networks, add them as comma-separated values to the`bitbucket-cloud-additional-source-ip` parameter in the`TektonConfig` custom resource, in the`pipelinesAsCode.settings` spec.

Optional: For an existing`Repository` CR, add multiple Bitbucket Cloud Webhook secrets or provide a substitute for a deleted secret.

Add a webhook by using the`tkn pac` CLI tool.

Example: Adding additional webhook by using the`tkn pac` CLI

```shell-session
$ tkn pac webhook add -n repo-pipelines
```

Sample interactive output

```shell-session
✓ Setting up Bitbucket Webhook for Repository https://bitbucket.org/workspace/repo
? Please enter your bitbucket cloud username:  <username>
👀 I have detected a controller url: https://pipelines-as-code-controller-openshift-pipelines.apps.example.com
? Do you want me to use it? Yes
✓ Webhook has been created on repository workspace/repo
🔑 Secret workspace-repo has been updated with webhook secret in the repo-pipelines namespace.
```

Note

Use the`[-n]` option with the`tkn pac webhook add` command only when the`Repository` CR exists in a namespace other than the default namespace.

1. Update the`webhook.secret` key in the existing OpenShift`Secret` object.

Optional: For an existing`Repository` CR, update the personal access token.

Update the personal access token by using the`tkn pac` CLI tool.

Example: Updating personal access token by using the`tkn pac` CLI

```shell-session
$ tkn pac webhook update-token -n repo-pipelines
```

Sample interactive output

```shell-session
? Please enter your personal access token:  ****************************************
🔑 Secret owner-repo has been updated with new personal access token in the repo-pipelines namespace.
```

Note

Use the`[-n]` option with the`tkn pac webhook update-token` command only when the`Repository` CR exists in a namespace other than the default namespace.

Alternatively, update the personal access token by modifying the`Repository` CR.

Find the name of the secret in the`Repository` CR.

```yaml
...
spec:
  git_provider:
    user: "<BITBUCKET_USERNAME>"
    secret:
      name: "bitbucket-cloud-token"
      key: "provider.token"
...
```

Use the`oc patch` command to update the values of the`$password` in the`$target_namespace` namespace.

```shell-session
$ oc -n $target_namespace patch secret bitbucket-cloud-token -p "{\"data\": {\"provider.token\": \"$(echo -n $NEW_TOKEN|base64 -w0)\"}}"
```

Additional resources

- [Creating app password on Bitbucket Cloud](https://support.atlassian.com/bitbucket-cloud/docs/app-passwords/)(Atlassian documentation)
- [Introducing Atlassian Account ID and Nicknames](https://developer.atlassian.com/cloud/bitbucket/bitbucket-api-changes-gdpr/#introducing-atlassian-account-id-and-nicknames) in the Atlassian documentation

### 3.5. Using Pipelines as Code with Bitbucket Data CenterCopy link

If your organization or project uses Bitbucket Data Center as the preferred platform, you can use Pipelines as Code for your repository with a webhook on Bitbucket Data Center.

Prerequisites

Generate a personal access token as the manager of the project on Bitbucket Data Center, and save a copy of it in an alternate location.

Note

- The token must have the`PROJECT_ADMIN` and`REPOSITORY_ADMIN` permissions.
- The token must have access to forked repositories in pull requests.

Procedure

On your OpenShift cluster, extract the public URL of the Pipelines as Code controller.

```shell-session
$ echo https://$(oc get route -n openshift-pipelines pipelines-as-code-controller -o jsonpath='{.spec.host}')
```

On Bitbucket Data Center, perform the following steps:

Add a webhook secret and save a copy of it in an alternate location. If you have`openssl` installed on your local machine, generate a random secret using the following command:

```shell-session
$ openssl rand -hex 20
```

Select the following events:

- Repository: Push
- Repository: Modified
- Pull Request: Opened
- Pull Request: Source branch updated
- Pull Request: Comment added

On your OpenShift cluster, create a`Secret` object with the app password in the target namespace.

```shell-session
$ oc -n target-namespace create secret generic bitbucket-datacenter-webhook-config \
  --from-literal provider.token="<PERSONAL_TOKEN>" \
  --from-literal webhook.secret="<WEBHOOK_SECRET>"
```

Create a`Repository` CR.

Example:`Repository` CR

```yaml
apiVersion: "pipelinesascode.tekton.dev/v1alpha1"
kind: Repository
metadata:
  name: my-repo
  namespace: target-namespace
spec:
  url: "https://bitbucket.com/workspace/repo"
  git_provider:
    url: "https://bitbucket.datacenter.api.url/rest" 
```

1

```yaml

    user: "<BITBUCKET_USERNAME>" 
```

2

```yaml

    secret: 
```

3

```yaml

      name: "bitbucket-datacenter-webhook-config"
      key: "provider.token" # Set this if you have a different key in your secret
    webhook_secret:
      name: "bitbucket-datacenter-webhook-config"
      key: "webhook.secret" # Set this if you have a different key for your secret
```

1

Ensure that you have the right Bitbucket Data Center API URL without the`/api/v1.0` suffix. Usually, the default install has a`/rest` suffix.

2

You can only reference a user by the`ACCOUNT_ID` in an owner file.

3

Pipelines as Code assumes that the secret referred in the`git_provider.secret` spec and the`Repository` CR is in the same namespace.

Note

The`tkn pac create` and`tkn pac bootstrap` commands are not supported on Bitbucket Data Center.

Additional resources

- [Creating personal tokens on Bitbucket Data Center](https://confluence.atlassian.com/bitbucketserver/personal-access-tokens-939515499.html)(Atlassian documentation)
- [Managing webhooks on Bitbucket Data Center](https://confluence.atlassian.com/bitbucketserver/manage-webhooks-938025878.html)(Atlassian documentation)

### 3.6. Interfacing Pipelines as Code with custom certificatesCopy link

To configure Pipelines as Code with a Git repository that is accessible with a privately signed or custom certificate, you can expose the certificate to Pipelines as Code.

Procedure

- If you have installed Pipelines as Code using the Red Hat OpenShift Pipelines Operator, you can add your custom certificate to the cluster using the`Proxy` object. The Operator exposes the certificate in all Red Hat OpenShift Pipelines components and workloads, including Pipelines as Code.

Additional resources

- [Enabling the cluster-wide proxy](https://docs.openshift.com/container-platform/latest/networking/enable-cluster-wide-proxy.html)

### 3.7. Using private repositories with Pipelines as CodeCopy link

Pipelines as Code supports private repositories by creating or updating a secret in the target namespace with the user token. The`git-clone` task from Tekton Hub uses the user token to clone private repositories.

Whenever Pipelines as Code creates a new pipeline run in the target namespace, it creates or updates a secret with the`pac-gitauth- - - ` format.

You must reference the secret with the`basic-auth` workspace in your pipeline run and pipeline definitions, which is then passed on to the`git-clone` task.

```yaml
...
  workspace:
  - name: basic-auth
    secret:
      secretName: "{{ git_auth_secret }}"
...
```

In the pipeline, you can reference the`basic-auth` workspace for the`git-clone` task to reuse:

```yaml
...
workspaces:
  - name basic-auth
params:
    - name: repo_url
    - name: revision
...
tasks:
  workspaces:
    - name: basic-auth
      workspace: basic-auth
  ...
  tasks:
  - name: git-clone-from-catalog
      taskRef:
        name: git-clone 
```

1

```yaml

      params:
        - name: url
          value: $(params.repo_url)
        - name: revision
          value: $(params.revision)
...
```

1

The`git-clone` task picks up the`basic-auth` workspace and uses it to clone the private repository.

You can modify this configuration by setting the`secret-auto-create` parameter to either a`false` or`true` value, as required, in the`TektonConfig` custom resource, in the`pipelinesAsCode.settings` spec.

## Chapter 4. Using the Repository custom resourceCopy link

The`Repository` custom resource (CR) has the following primary functions:

- Inform Pipelines as Code about processing an event from a URL.
- Inform Pipelines as Code about the namespace for the pipeline runs.
- Reference an API secret, username, or an API URL necessary for Git provider platforms when using webhook methods.
- Provide the last pipeline run status for a repository.

### 4.1. Creating the Repository custom resourceCopy link

You can use the`tkn pac` CLI or other alternative methods to create a`Repository` custom resource (CR) inside the target namespace. For example:

```shell-session
cat <<EOF|kubectl create -n my-pipeline-ci -f- 
```

1

```shell-session


apiVersion: "pipelinesascode.tekton.dev/v1alpha1"
kind: Repository
metadata:
  name: project-repository
spec:
  url: "https://github.com/<repository>/<project>"
EOF
```

1

`my-pipeline-ci` is the target namespace.

Whenever there is an event coming from the URL such as`https://github.com/ / `, Pipelines as Code matches it and then starts checking out the content of the` / ` repository for the pipeline run to match the content in the`.tekton/` directory.

Note

- You must create the`Repository` CR in the same namespace where pipelines associated with the source code repository will be executed; it cannot target a different namespace.
- If multiple`Repository` CRs match the same event, Pipelines as Code processes only the oldest one. If you need to match a specific namespace, add the`pipelinesascode.tekton.dev/target-namespace: " "` annotation. Such explicit targeting prevents a malicious actor from executing a pipeline run in a namespace to which they do not have access.

### 4.2. Creating the global Repository custom resourceCopy link

Optionally, you can create a global`Repository` custom resource (CR) in the namespace where OpenShift Pipelines is installed, normally`openshift-pipelines`. If you create this CR, the settings that you specify in it apply by default to all`Repository` CRs that you create.

Important

The global`Repository` CR is a Technology Preview feature only. Technology Preview features are not supported with Red Hat production service level agreements (SLAs) and might not be functionally complete. Red Hat does not recommend using them in production. These features provide early access to upcoming product features, enabling customers to test functionality and provide feedback during the development process.

For more information about the support scope of Red Hat Technology Preview features, see [Technology Preview Features Support Scope](https://access.redhat.com/support/offerings/techpreview/).

Prerequisites

- You have administrator access to the`openshift-pipelines` namespace.
- You logged on to the OpenShift cluster using the`oc` command line utility.

Procedure

Create a`Repository` CR named`pipeline-as-code` in the`openshift-pipelines` namespace. Specify all the required default settings in this CR.

Example command to create the CR

```shell-session
$ cat <<EOF|oc create -n openshift-pipelines -f -

apiVersion: "pipelinesascode.tekton.dev/v1alpha1"
kind: Repository
metadata:
  name: pipelines-as-code
spec:
  git_provider:
    secret:
      name: "gitlab-webhook-config"
      key: "provider.token"
    webhook_secret:
      name: "gitlab-webhook-config"
      key: "webhook.secret"
EOF
```

In this example, all`Repository` CRs that you create include the common secrets for accessing your GitLab repositories. You can set different repository URLs and other settings in the CRs.

### 4.3. Setting concurrency limitsCopy link

You can use the`concurrency_limit` spec in the`Repository` custom resource definition (CRD) to define the maximum number of pipeline runs running simultaneously for a repository.

```yaml
apiVersion: "pipelinesascode.tekton.dev/v1alpha1"
kind: Repository
metadata:
  name: my-repo
  namespace: target-namespace
spec:
# ...
  concurrency_limit: <number>
# ...
```

If there are multiple pipeline runs matching an event, the pipeline runs that match the event start in an alphabetical order.

For example, if you have three pipeline runs in the`.tekton` directory and you create a pull request with a`concurrency_limit` of`1` in the repository configuration, then all the pipeline runs are executed in an alphabetical order. At any given time, only one pipeline run is in the running state while the rest are queued.

### 4.4. Changing the source branch for the pipeline definitionCopy link

By default, when processing a push event or a pull request event, Pipelines as Code fetches the pipeline definition from the branch that triggered the event. You can use the`pipelinerun_provenance` setting in the`Repository` custom resource definition (CRD) to fetch the definition from the default branch configured on the Git repository provider, such as`main`,`master`, or`trunk`.

```yaml
apiVersion: "pipelinesascode.tekton.dev/v1alpha1"
kind: Repository
metadata:
  name: my-repo
  namespace: target-namespace
spec:
# ...
  settings:
    pipelinerun_provenance: "default_branch"
# ...
```

Note

You can use this setting as a security precaution. With the default behaviour, Pipelines as Code uses the pipeline definition in the submitted pull request. With the`default-branch` setting, the pipeline definition must be merged into the default branch before it is run. This requirement ensures maximum possible verification of any changes during merge review.

### 4.5. Custom parameter expansionCopy link

You can use Pipelines as Code to expand a custom parameter within your`PipelineRun` resource by using the`params` field. You can specify a value for the custom parameter inside the template of the`Repository` custom resource (CR). The specified value replaces the custom parameter in your pipeline run.

You can use custom parameters in the following scenarios:

- To define a URL parameter, such as a registry URL that varies based on a push or a pull request.
- To define a parameter, such as an account UUID that an administrator can manage without necessitating changes to the`PipelineRun` execution in the Git repository.

Note

Use the custom parameter expansion feature only when you cannot use the Tekton`PipelineRun` parameters because Tekton parameters are defined in a`Pipeline` resource and customized alongside it inside a Git repository. However, custom parameters are defined and customized where the`Repository` CR is located. Therefore, you cannot manage your CI/CD pipeline from a single point.

The following example shows a custom parameter named`company` in the`Repository` CR:

```yaml
...
spec:
  params:
    - name: company
      value: "ABC Company"
...
```

The value`ABC Company` replaces the parameter name`company` in your pipeline run and in the remotely fetched tasks.

You can also retrieve the value for a custom parameter from a Kubernetes secret, as shown in the following example:

```yaml
...
spec:
  params:
    - name: company
      secret_ref:
        name: my-secret
        key: companyname
...
```

Pipelines as Code parses and uses custom parameters in the following manner:

- If you have a`value` and a`secret_ref` defined, Pipelines as Code uses the`value`.
- If you do not have a`name` in the`params` section, Pipelines as Code does not parse the parameter.
- If you have multiple`params` with the same`name`, Pipelines as Code uses the last parameter.

You can also define a custom parameter and use its expansion only when specified conditions were matched for a CEL filter. The following example shows a CEL filter applicable on a custom parameter named`company` when a pull request event is triggered:

```yaml
...
spec:
  params:
    - name: company
      value: "ABC Company"
      filter: pac.event_type == "pull_request"
...
```

Note

When you have multiple parameters with the same name and different filters, Pipelines as Code uses the first parameter that matches the filter. So, Pipelines as Code allows you to expand parameters according to different event types. For example, you can combine a push and a pull request event.

## Chapter 5. Creating pipeline runs in Pipelines as CodeCopy link

After integrating Pipelines as Code with your repository provider and defining your particular repository by using the`Repository` custom resource (CR), you can create pipeline run definitions in your repository.

### 5.1. Creating a pipeline run in Pipelines as CodeCopy link

You can create a pipeline run definition for Pipelines as Code in your repository and match it to an event, such as a pull request. When the event happens, Pipelines as Code creates a`PipelineRun` custom resource (CR) from this definition, and then OpenShift Pipelines executes the pipeline run.

Prerequisites

- You configured Pipelines as Code to integrate with your Git repository hosting service provider.
- You created a`Repository` custom resource (CR) to define the connection to your repository in Pipelines as Code.
- You have a`.tekton` directory in the root of your repository.

Procedure

Depending on the requirements of your pipeline run definition, complete any of the following optional steps:

Use dynamic variables in your pipeline run specification. Pipelines as Code replaces these variables with values that represent the current context. For example,`{{ repo_url }}` is the current URL for the repository and`{{ revision }}` is the commit SHA on which the pipeline run was started.

For more information about dynamic variables, see the "Dynamic variables in a pipeline run specification" section.

Add one or more Pipelines as Code resolver annotations to your pipeline run definition. A Pipelines as Code resolver annotation references a pipeline or task in Tekton Hub, an HTTP location, or a location in your repository outside the`.tekton` directory. If you create a Pipelines as Code resolver annotation to reference a resource, you can use this resource by name in your pipeline run definition. The Pipelines as Code resolver automatically resolves these resources and includes them in the`PipelineRun` CR that is based on your definition.

For more information about these annotations, see the "Pipelines as Code resolver annotations" section.

- Create other files with a`.yaml` or`.yml` extension in the`.tekton` directory. In these files, provide definitions of resources that your pipeline run definition references, such as`Pipeline`,`Task`, or`StepAction` CRs. The Pipelines as Code resolver automatically resolves these resources and includes them in the`PipelineRun` CR that is based on your definition.

Match the pipeline run to events by adding any of the following annotations to the pipeline run definition. When the defined event happens, Pipelines as Code starts the pipeline run. For more information about matching the pipeline run to events, see the "Annotations for matching events to a pipeline run" section.

A combination of the`pipelinesascode.tekton.dev/on-event` annotation, which defines a`pull request` or`push` event, and a`pipelinesascode.tekton.dev/on-target-branch` annotation, which defines the branch that the pull request or push event must target. If you match your pipeline run to a pull request or push event, the pipeline run starts when the event is created. For pull requests, it starts again each time the source branch event is updated.

Note

If your Git repository provider uses merge requests and not pull requests, a`pull_request` event definition matches a merge request.

- A`pipelinesascode.tekton.dev/on-comment` annotation, which matches the pipeline run to a comment on a pull request by regular expression. If you match your pipeline run to a comment, it starts when the comment is added to a pull request. To start the pipeline run again, add the comment again.
- A`pipelinesascode.tekton.dev/on-cel-expression` annotation, which matches the pipeline run if the specified Common Expression Language (CEL) expression evaluates to`true` on a pull request or push event.

Optional: Add one or more annotations that filter the matching events. With these annotations, when the defined matching event (such as a pull request, a push event, or a comment) happens, Pipelines as Code checks if these annotations are also matched. The pipeline run starts only if all the annotations that you added to it are matched. For more information about filtering matching events, see the "Annotations for filtering events" section.

- A`pipelinesascode.tekton.dev/on-path-changed` annotation is matched if the pull request or push event affect files in the specified paths.
- A`pipelinesascode.tekton.dev/on-path-changed-ignore` annotation excludes matching the event if the event changes only files in the specified paths and does not change any other files in the repository.
- A`pipelinesascode.tekton.dev/on-label` annotation is matched if the pull request or push event has one of the specified labels.

Example Pipelines as Code pipeline run definition

```yaml
apiVersion: tekton.dev/v1
kind: PipelineRun
metadata:
  name: maven-build
annotations:
  pipelinesascode.tekton.dev/task: "[git-clone]" 
```

1

```yaml

  pipelinesascode.tekton.dev/on-event: "[pull_request]" 
```

2

```yaml

  pipelinesascode.tekton.dev/on-target-branch: "[main, release]" 
```

3

```yaml

  pipelinesascode.tekton.dev/on-path-changed: "[src/**]" 
```

4

```yaml

  pipelinesascode.tekton.dev/cancel-in-progress: "true" 
```

5

```yaml

spec:
  pipelineSpec:
    workspaces:
    - name: shared-workspace
    tasks:
      - name: fetch-repo
        taskRef:
          - name: git-clone 
```

6

```yaml

        params:
        - name: url
          value: {{ repo_url }} 
```

7

```yaml

        - name: revision
          value: {{ revision }} 
```

8

```yaml

        workspaces:
        - name: output
          workspace: shared-workspace
      - name: build-from-source
        taskRef:
          resolver: cluster
          params:
          - name: kind
            value: task
          - name: name
            value: maven
          - name: namespace
            value: openshift-pipelines
        workspaces:
        - name: source
          workspace: shared-workspace
```

1

The`pipelinesascode.tekton.dev/task` annotation references the`git-clone` task from Tekton Hub.

2

The`pipelinesascode.tekton.dev/on-event` annotation matches the pipeline run to a pull request or merge request event.

3

The`pipelinesascode.tekton.dev/on-target-branch` annotation specifies that a pull request into either the`main` branch or`release` branch triggers this pipeline run.

4

The`pipelinesascode.tekton.dev/on-path-changed` annotation specifies that the pipeline run triggers only if the pull request contains changes to files under the`src` directory.

5

The`pipelinesascode.tekton.dev/cancel-in-progress` annotation specifies that, if the pipeline run is started again for the same pull request, Pipelines as Code cancels the previous run.

6

The pipeline run specification references the`git-clone` task by name. Because of the`pipelinesascode.tekton.dev/task` annotation, the Pipelines as Code resolver resolves this reference to the`git-clone` task from Tekton Hub.

7

Pipelines as Code replaces the`{{ repo_url }}` dynamic variable with the URL for the Git repository.

8

Pipelines as Code replaces the`{{ revision }}` dynamic variable with the revision of the branch for which the pipeline run was started.

### 5.2. Dynamic variables in a pipeline run specificationCopy link

You can use dynamic variables in a pipeline run specification to provide information about the commit that triggered the pipeline run and to use the temporary GitHub App token for Github API operations.

Important

Using Pipelines as Code dynamic variables in default values for pipeline or task parameters is currently not supported. You can use a dynamic variable in a`value:` field, but not in a`default:` field.

#### 5.2.1. Commit and URL informationCopy link

You can specify the parameters of your commit and URL by using dynamic, expandable variables with the`{{}}` format. Currently, you can use the following variables:

- Optional: Add the`pipelinesascode.tekton.dev/cancel-in-progress: "true"` annotation to enable automatic cancellation of the pipeline run in certain cases. For example, if a pull request triggers a pipeline run and then the user pushes new commits into the pull request source branch, each push triggers a new copy of the pipeline run. if you enable automatic cancellation-in-progress, after a new copy of the pipeline run starts, Pipelines as Code cancels the older run to avoid running many copies of the pipeline run at the same time. For more information about this annotation, see the "Annotations for specifying automatic cancellation-in-progress for a pipeline run" section.
- `{{repo_owner}}`: The repository owner.
- `{{repo_name}}`: The repository name.
- `{{repo_url}}`: The repository full URL.
- `{{revision}}`: Full SHA revision of a commit.
- `{{sender}}`: The username or account ID of the sender of the commit.
- `{{source_branch}}`: The branch name where the event originated.
- `{{target_branch}}`: The branch name that the event targets. For push events, it is the same as the`source_branch`.
- `{{pull_request_number}}`: The pull or merge request number, defined only for a`pull_request` event type.
- `{{git_auth_secret}}`: The secret name that is generated automatically with the Git provider token for checking out private repos.

#### 5.2.2. Temporary GitHub App token for GitHub API operationsCopy link

You can use the temporary installation token generated by Pipelines as Code from the GitHub App to access the GitHub API. The GitHub App generates a key for private repositories in the`git-provider-token` key. You can use the`{{git_auth_secret}}` dynamic variable in pipeline runs to access this key.

For example, if your pipeline run must add a comment to a pull request, you can use the a Pipelines as Code annotation to fetch the`github-add-comment` task definition from Tekton Hub, and then define the task that adds the comment, as shown in the following example:

```yaml
apiVersion: tekton.dev/v1
kind: PipelineRun
metadata:
  name: pipeline-with-comment
annotations:
  pipelinesascode.tekton.dev/task: "github-add-comment"
spec:
  pipelineSpec:
    tasks:
      - name: add-sample-comment
        taskRef:
          name: github-add-comment
        params:
          - name: REQUEST_URL
            value: "{{ repo_url }}/pull/{{ pull_request_number }}" 
```

1

```yaml

          - name: COMMENT_OR_FILE
            value: "Pipelines as Code IS GREAT!"
          - name: GITHUB_TOKEN_SECRET_NAME
            value: "{{ git_auth_secret }}"
          - name: GITHUB_TOKEN_SECRET_KEY
            value: "git-provider-token"
```

1

By using the dynamic variables, you can reuse this snippet template for any pull request from any repository that you use with Pipelines as Code.

Note

On GitHub Apps, the generated installation token is available for 8 hours and scoped to the repository from where the events originate. You can configure the scope differently, but the expiration time is determined by GitHub.

### 5.3. Pipelines as Code resolver annotationsCopy link

You can use Pipelines as Code resolver annotations to reference`Task` and`Pipeline` custom resource (CR) definitions. The Pipelines as Code resolver fetches the definitions from the locations that you specify in the annotations. If there is any error while fetching the remote tasks or parsing them, Pipelines as Code stops processing the tasks.

If you reference a remote task in a pipeline run, or a pipeline in a`PipelineRun` or a`PipelineSpec` object, the Pipelines as Code resolver automatically resolves the referenced resource and includes it in the resulting`PipelineRun` custom resource (CR).

Note

The public instance of Tekton Hub (`hub.tekton.dev`) is deprecated and will be removed in a future release. Use [Artifact Hub](https://artifacthub.io/) as an alternative for Tekton`Pipeline` and`Task` resources:

- [Tekton Catalog Tasks](https://artifacthub.io/packages/search?repo=tekton-catalog-tasks)
- [Tekton Catalog Pipelines](https://artifacthub.io/packages/search?repo=tekton-catalog-pipelines)

Artifact Hub is open source and supports self-hosting, providing greater flexibility for managing your Tekton`Pipeline` and`Task` resources.

#### 5.3.1. Remote task annotationsCopy link

To include remote tasks, refer to the following examples of annotation:

Reference remote tasks in Tekton Hub

Reference a single remote task in Tekton Hub.

```yaml
...
  pipelinesascode.tekton.dev/task: "git-clone" 
```

1

```yaml

...
```

1

Pipelines as Code includes the latest version of the task from the Tekton Hub.

Reference multiple remote tasks from Tekton Hub

```yaml
...
  pipelinesascode.tekton.dev/task: "[git-clone, golang-test, tkn]"
...
```

Reference multiple remote tasks from Tekton Hub using the`- ` suffix.

```yaml
...
  pipelinesascode.tekton.dev/task: "git-clone"
  pipelinesascode.tekton.dev/task-1: "golang-test"
  pipelinesascode.tekton.dev/task-2: "tkn" 
```

1

```yaml

...
```

1

By default, Pipelines as Code interprets the string as the latest task to fetch from Tekton Hub.

Reference a specific version of a remote task from Tekton Hub.

```yaml
...
  pipelinesascode.tekton.dev/task: "[git-clone:0.1]" 
```

1

```yaml

...
```

1

Refers to the`0.1` version of the`git-clone` remote task from Tekton Hub.

Remote tasks using URLs

```yaml
...
  pipelinesascode.tekton.dev/task: "<https://remote.url/task.yaml>" 
```

1

```yaml

...
```

1

The public URL to the remote task.

Note

If you use GitHub and the remote task URL uses the same host as the`Repository` custom resource definition (CRD), Pipelines as Code uses the GitHub token and fetches the URL using the GitHub API.

For example, if you have a repository URL similar to`https://github.com/ / ` and the remote HTTP URL references a GitHub blob similar to`https://github.com/ / /blob/ / / `, Pipelines as Code fetches the task definition files from that private repository with the GitHub App token.

When you work on a public GitHub repository, Pipelines as Code acts similarly for a GitHub raw URL such as`https://raw.githubusercontent.com/ / / / / `.

- GitHub App tokens are scoped to the owner or organization where the repository is located. When you use the GitHub webhook method, you can fetch any private or public repository on any organization where the personal token is allowed.

Reference a task from a YAML file inside your repository

```yaml
...
pipelinesascode.tekton.dev/task: "<share/tasks/git-clone.yaml>" 
```

1

```yaml

...
```

1

Relative path to the local file containing the task definition.

#### 5.3.2. Remote pipeline annotationsCopy link

You can share a pipeline definition across multiple repositories by using the remote pipeline annotation.

```yaml
...
    pipelinesascode.tekton.dev/pipeline: "<https://git.provider/raw/pipeline.yaml>" 
```

1

```yaml

...
```

1

URL to the remote pipeline definition. You can also provide locations for files inside the same repository.

Note

You can reference only one pipeline definition using the annotation.

##### 5.3.2.1. Overriding a task in a remote pipelineCopy link

By default, if you use a remote pipeline annotation in a pipeline run, Pipelines as Code uses all the tasks that are a part of the remote pipeline.

You can override a task in a remote pipeline by adding a task annotation to the pipeline run. The added task must have the same name as a task in the remote pipeline.

For example, you might use the following pipeline run definition:

Example pipeline run definition referencing a remote pipeline and overriding a task

```yaml
apiVersion: tekton.dev/v1
kind: PipelineRun
metadata:
  annotations:
    pipelinesascode.tekton.dev/pipeline: "https://git.provider/raw/pipeline.yaml"
    pipelinesascode.tekton.dev/task: "./my-git-clone-task.yaml"
```

For this example, assume the remote task found at`https://git.provider/raw/pipeline.yaml` includes a task named`git-clone` and the task that the`my-git-clone-task.yaml` file defines is also named`git-clone`.

In this case, the pipeline run executes the remote pipeline, but replaces the task named`git-clone` in the pipeline with the task you defined.

### 5.4. Annotations for matching events to a pipeline runCopy link

You can match different Git provider events with each pipeline run by using annotations on the pipeline run. If there are multiple pipeline runs matching an event, Pipelines as Code runs them in parallel and posts the results to the Git provider as soon as a pipeline run finishes.

#### 5.4.1. Matching a pull request event to a pipeline runCopy link

You can use the following example to match the`pipeline-pr-main` pipeline run with a`pull_request` event that targets the`main` branch:

```yaml
apiVersion: tekton.dev/v1
kind: PipelineRun
metadata:
  name: pipeline-pr-main
annotations:
  pipelinesascode.tekton.dev/on-target-branch: "[main]" 
```

1

```yaml

  pipelinesascode.tekton.dev/on-event: "[pull_request]"
# ...
```

1

You can specify multiple branches by adding comma-separated entries. For example,`"[main, release-nightly]"`. In addition, you can specify the following items:

- Full references to branches such as`"refs/heads/main"`
- Globs with pattern matching such as`"refs/heads/\*"`
- Tags such as`"refs/tags/1.\*"`

#### 5.4.2. Matching a push event to a pipeline runCopy link

You can use the following example to match the`pipeline-push-on-main` pipeline run with a`push` event targeting the`refs/heads/main` branch:

```yaml
apiVersion: tekton.dev/v1
kind: PipelineRun
metadata:
  name: pipeline-push-on-main
annotations:
  pipelinesascode.tekton.dev/on-target-branch: "[refs/heads/main]" 
```

1

```yaml

  pipelinesascode.tekton.dev/on-event: "[push]"
# ...
```

1

You can specify multiple branches by adding comma-separated entries. For example,`"[main, release-nightly]"`. In addition, you can specify the following items:

- Full references to branches such as`"refs/heads/main"`
- Globs with pattern matching such as`"refs/heads/\*"`
- Tags such as`"refs/tags/1.\*"`

#### 5.4.3. Matching a comment event to a pipeline runCopy link

Important

Matching a comment event to a pipeline run is a Technology Preview feature only. Technology Preview features are not supported with Red Hat production service level agreements (SLAs) and might not be functionally complete. Red Hat does not recommend using them in production. These features provide early access to upcoming product features, enabling customers to test functionality and provide feedback during the development process.

For more information about the support scope of Red Hat Technology Preview features, see [Technology Preview Features Support Scope](https://access.redhat.com/support/offerings/techpreview/).

You can use the following example to match the`pipeline-comment` pipeline run with a comment on a pull request, when the text of the comment matches the`^/merge-pr` regular expression:

```yaml
apiVersion: tekton.dev/v1
kind: PipelineRun
metadata:
  name: pipeline-comment
annotations:
  pipelinesascode.tekton.dev/on-comment: "^/merge-pr"
# ...
```

The pipeline run starts only if the comment author meets one of the following requirements:

- The author is the owner of the repository.
- The author is a collaborator on the repository.
- The author is a public member on the organization of the repository.
- The comment author is listed in the`approvers` or`reviewers` section of the`OWNERS` file in the root of the repository, as defined in the [Kubernetes documentation](https://www.kubernetes.dev/docs/guide/owners/). Pipelines as Code supports the specification for the`OWNERS` and`OWNERS_ALIASES` files. If the`OWNERS` file includes a [filters](https://github.com/kubernetes/community/blob/master/contributors/guide/owners.md#filters) section, Pipelines as Code matches approvers and reviewers only against the`.*` filter.

#### 5.4.4. Advanced event matchingCopy link

Pipelines as Code supports using Common Expression Language (CEL) based filtering for advanced event matching. If you have the`pipelinesascode.tekton.dev/on-cel-expression` annotation in your pipeline run, Pipelines as Code uses the CEL expression and skips the`on-target-branch` annotation. Compared to the simple`on-target-branch` annotation matching, the CEL expressions allow complex filtering and negation.

Important

If you use the`on-cel-expression` annotation in the same pipeline run as an`on-event`,`on-target-branch`,`on-label`,`on-path-change`, or`on-path-change-ignore` annotation, the`on-cel-expression` annotation takes priority and Pipelines as Code ignores the other annotations.

To use CEL-based filtering with Pipelines as Code, consider the following examples of annotations:

To match a`pull_request` event targeting the`main` branch and coming from the`wip` branch:

```yaml
apiVersion: tekton.dev/v1
kind: PipelineRun
metadata:
  name: pipeline-advanced-pr
annotations:
  pipelinesascode.tekton.dev/on-cel-expression: |
    event == "pull_request" && target_branch == "main" && source_branch == "wip"
...
```

To run a pipeline only if a path has changed, you can use the`.pathChanged` suffix function with a glob pattern:

```yaml
apiVersion: tekton.dev/v1
kind: PipelineRun
metadata:
  name: pipeline-advanced-pr-pathchanged
annotations:
  pipelinesascode.tekton.dev/on-cel-expression: |
    event == "pull_request" && "docs/\*.md".pathChanged() 
```

1

```yaml

# ...
```

1

Matches all markdown files in the`docs` directory.

To match all pull requests starting with the title`[DOWNSTREAM]`:

```yaml
apiVersion: tekton.dev/v1
kind: PipelineRun
metadata:
  name: pipeline-advanced-pr-downstream
annotations:
  pipelinesascode.tekton.dev/on-cel-expression: |
    event == "pull_request && event_title.startsWith("[DOWNSTREAM]")
# ...
```

To run a pipeline on a`pull_request` event, but skip the`experimental` branch:

```yaml
apiVersion: tekton.dev/v1
kind: PipelineRun
metadata:
  name: pipeline-advanced-pr-not-experimental
annotations:
  pipelinesascode.tekton.dev/on-cel-expression: |
    event == "pull_request" && target_branch != experimental"
# ...
```

For advanced CEL-based filtering while using Pipelines as Code, you can use the following fields and suffix functions:

- `event`: A`push` or`pull_request` event.
- `target_branch`: The target branch.
- `source_branch`: The branch of origin of a`pull_request` event. For`push` events, it is same as the`target_branch`.
- `event_title`: Matches the title of the event, such as the commit title for a`push` event, and the title of a pull or merge request for a`pull_request` event. Currently, only GitHub, Gitlab, and Bitbucket Cloud are the supported providers.
- `.pathChanged`: A suffix function to a string. The string can be a glob of a path to check if the path has changed. Currently, only GitHub and Gitlab are supported as providers.

In addition, you can access the full payload as passed by the Git repository provider. Use the`headers` field to access the headers of the payload, for example,`headers['x-github-event']`. Use the`body` field to access the body of the payload, for example,`body.pull_request.state`.

Important

Using the header and body of the payload for CEL-based filtering with Pipelines as Code is a Technology Preview feature only. Technology Preview features are not supported with Red Hat production service level agreements (SLAs) and might not be functionally complete. Red Hat does not recommend using them in production. These features provide early access to upcoming product features, enabling customers to test functionality and provide feedback during the development process.

For more information about the support scope of Red Hat Technology Preview features, see [Technology Preview Features Support Scope](https://access.redhat.com/support/offerings/techpreview/).

In the following example, the pipeline run starts only if all of the following conditions are true:

- The pull request is targeting the`main` branch.
- The author of the pull request is`superuser`.
- The action is`synchronize`; this action triggers when an update occurs on a pull request.

```yaml
apiVersion: tekton.dev/v1
kind: PipelineRun
metadata:
  annotations:
    pipelinesascode.tekton.dev/on-cel-expression: |
      body.pull_request.base.ref == "main" &&
        body.pull_request.user.login == "superuser" &&
        body.action == "synchronize"
# ...
```

Note

If you use the`header` or`body` field for event matching, you might be unable to trigger the pipeline run using Git commands such as`retest`. If you use a Git command, the payload body is the comment that contains this command, and not the original payload.

If you want to trigger the pipeline run again when using the`body` field for event matching, you can close and reopen the pull request or merge request, or alternatively add a new SHA commit. You can add a new SHA commit by using the following command:

```shell-session
git commit --amend --no-edit && git push --force-with-lease
```

### 5.5. Annotations for filtering events matched to a pipeline runCopy link

You can add one or more annotations to a pipeline run to filter the events that are matched to this pipeline run. In this case, when the defined matching event (such as a pull request, a push event, or a comment) happens, Pipelines as Code checks if these annotations are also matched. The pipeline run starts only if all the annotations that you added to it are matched.

#### 5.5.1. Matching changes in paths to a pipeline runCopy link

You can match a pipeline run to changes in a set of paths. Pipelines as Code starts the pipeline run only if the pull request or push event includes changes in any of the paths that you list.

The`*` wildcard denotes any file in the directory. The`**` wildcard denotes any file in the directory or any subdirectories on any level under the directory.

You can use the following example to match the`pipeline-pkg-or-cli` pipeline run when a pull request changes any files in the`pkg` directory, the`cli` directory, or any subdirectories under the`cli` directory.

```yaml
apiVersion: tekton.dev/v1
kind: PipelineRun
metadata:
  name: pipeline-pkg-or-cli
annotations:
  pipelinesascode.tekton.dev/on-path-changed: "[pkg/*, cli/**]"
# ...
```

#### 5.5.2. Excluding changes in paths from matching a pipeline runCopy link

You can configure a pipeline run to exclude matching if a pull request makes changes only to files in a specified set of paths. If the pipeline run matches an event but the pull request includes changes only to files in the paths that you list, Pipelines as Code does not start the pipeline run.

You can use the following example to match the`pipeline-docs-not-generated` pipeline run when a pull request changes any files under the`docs` directory or its subdirectories, except when the changes apply only to the`docs/generated` directory or its subdirectories.

```yaml
apiVersion: tekton.dev/v1
kind: PipelineRun
metadata:
  name: pipeline-docs-not-generated
annotations:
  pipelinesascode.tekton.dev/on-path-changed: "[docs/**]"
  pipelinesascode.tekton.dev/on-path-changed-ignore: "[docs/generated/**]"
# ...
```

You can use the following example to match the`pipeline-main-not-docs` pipeline run when a pull request targets the`main` branch, except when the changes apply only to the`docs` directory or its subdirectories.

```yaml
apiVersion: tekton.dev/v1
kind: PipelineRun
metadata:
  name: pipeline-main-not-docs
annotations:
  pipelinesascode.tekton.dev/on-target-branch: "[main]"
  pipelinesascode.tekton.dev/on-event: "[pull_request]"
  pipelinesascode.tekton.dev/on-path-changed-ignore: "[docs/**]"
# ...
```

#### 5.5.3. Matching a pull request label to a pipeline runCopy link

Important

Matching pull request labels to a pipeline run is a Technology Preview feature only. Technology Preview features are not supported with Red Hat production service level agreements (SLAs) and might not be functionally complete. Red Hat does not recommend using them in production. These features provide early access to upcoming product features, enabling customers to test functionality and provide feedback during the development process.

For more information about the support scope of Red Hat Technology Preview features, see [Technology Preview Features Support Scope](https://access.redhat.com/support/offerings/techpreview/).

You can match a pipeline run to one or several pull request labels. Pipelines as Code starts the pipeline run only when the pull request has any of these labels. When the pull request is updated with a new commit, if the label is still present, Pipelines as Code starts the pipeline run again.

You can use the following example to match the`pipeline-bug-or-defect` pipeline run when either the`bug` label or the`defect` label is added to a pull request, and also when a pull request with this label is updated with a new commit:

```yaml
apiVersion: tekton.dev/v1
kind: PipelineRun
metadata:
  name: pipeline-bug-or-defect
annotations:
  pipelinesascode.tekton.dev/on-label: "[bug, defect]"
# ...
```

Note

The current version of Pipelines as Code supports matching events to pull request labels only for the GitHub, Gitea, and GitLab repository hosting service providers.

### 5.6. Annotations for specifying automatic cancellation-in-progress for a pipeline runCopy link

By default, Pipelines as Code does not cancel pipeline runs automatically. Every pipeline run that Pipelines as Code creates and starts executes until it completes. However, events that trigger pipeline runs can come in quick succession. For example, if a pull request triggers a pipeline run and then the user pushes new commits into the pull request source branch, each push triggers a new copy of the pipeline run. If several pushes happen, several copies can run, which can consume excessive cluster resources.

You can configure a pipeline run to enable automatic cancellation-in-progress. If you enable automatic cancellation for a pipeline run, Pipelines as Code cancels the pipeline run in the following situations:

- Pipelines as Code has successfully started a copy of the same pipeline run for the same pull request or the same source branch.
- The pull request that triggered the pipeline run is merged or closed.

You can use the following example to enable automatic cancellation when you create the`sample-pipeline` pipeline run:

```yaml
apiVersion: tekton.dev/v1
kind: PipelineRun
metadata:
  name: sample-pipeline
annotations:
  pipelinesascode.tekton.dev/cancel-in-progress: "true"
# ...
```

Important

Automatic cancellation-in-progress of pipeline runs is a Technology Preview feature only. Technology Preview features are not supported with Red Hat production service level agreements (SLAs) and might not be functionally complete. Red Hat does not recommend using them in production. These features provide early access to upcoming product features, enabling customers to test functionality and provide feedback during the development process.

For more information about the support scope of Red Hat Technology Preview features, see [Technology Preview Features Support Scope](https://access.redhat.com/support/offerings/techpreview/).

Note

- Pipelines as Code cancels a pipeline run after starting a new copy of this pipeline run successfully. The`pipelinesascode.tekton.dev/cancel-in-progress` setting does not ensure that only one copy of the pipeline run is executing at any time.
- To enable`cancellation-in-progress` for all default pipeline runs, use the`enable-cancel-in-progress-on-pull-requests` and the`enable-cancel-in-progress-on-push` settings. These settings are available in the`platforms.openshift.pipelinesAsCode.settings` spec of the`TektonConfig` custom resource (CR) configure Pipelines as Code.

## Chapter 6. Managing pipeline runsCopy link

### 6.1. Verifying a pipeline runCopy link

You can verify that the Pipelines as Code resolver correctly processes the pipeline run definition that you created.

Prerequisites

- You installed the`tkn` command line utility.
- You are logged into your OpenShift Container Platform cluster using OpenShift CLI (`oc`).
- You cloned your Git repository locally.

Procedure

From the root of your cloned Git repository, run the following command, specifying your pipeline run definition file name:

```shell-session
$ tkn pac resolve .tekton/pipeline-run-definition.yaml
```

This command processes the pipeline run definition using the Pipelines as Code resolver. If the resolver fails to find any referenced resource, the output displays an error message. If the resolver completes successfully, the output displays the definition of a`PipelineRun` custom resource (CR) that includes all referenced resources, such as remote task definitions.

### 6.2. Running a pipeline run using Pipelines as CodeCopy link

With default configuration, Pipelines as Code runs any pipeline run in the`.tekton/` directory of the default branch of repository, when specified events such as pull request or push occurs on the repository. For example, if a pipeline run on the default branch has the annotation`pipelinesascode.tekton.dev/on-event: "[pull_request]"`, it will run whenever a pull request event occurs.

In the event of a pull request or a merge request, Pipelines as Code also runs pipelines from branches other than the default branch, if the following conditions are met by the author of the pull request:

- The author is the owner of the repository.
- The author is a collaborator on the repository.
- The author is a public member on the organization of the repository.
- The pull request author is listed in the`approvers` or`reviewers` section of the`OWNERS` file in the root of the repository, as defined in the [Kubernetes documentation](https://www.kubernetes.dev/docs/guide/owners/). Pipelines as Code supports the specification for the`OWNERS` and`OWNERS_ALIASES` files. If the`OWNERS` file includes a [filters](https://github.com/kubernetes/community/blob/master/contributors/guide/owners.md#filters) section, Pipelines as Code matches approvers and reviewers only against the`.*` filter.

If the pull request author does not meet the requirements, another user who meets the requirements can comment`/ok-to-test` on the pull request or in a discussion thread reply to start the pipeline run.

Pipeline run execution

A pipeline run always runs in the namespace of the`Repository` custom resource definition (CRD) associated with the repository that generated the event.

You can observe the execution of your pipeline runs using the`tkn pac` CLI tool.

To follow the execution of the last pipeline run, use the following example:

```shell-session
$ tkn pac logs -n <my-pipeline-ci> -L 
```

1

1

`my-pipeline-ci` is the namespace for the`Repository` CRD.

To follow the execution of any pipeline run interactively, use the following example:

```shell-session
$ tkn pac logs -n <my-pipeline-ci> 
```

1

1

`my-pipeline-ci` is the namespace for the`Repository` CRD. If you need to view a pipeline run other than the last one, you can use the`tkn pac logs` command to select a`PipelineRun` attached to the repository:

If you have configured Pipelines as Code with a GitHub App, Pipelines as Code posts a URL in the Checks tab of the GitHub App. You can click the URL and follow the pipeline execution.

### 6.3. Triggering a PipelineRun on Git tagsCopy link

When you create or reference a Git tag in a repository that is integrated with Pipelines as Code, you can trigger a`PipelineRun` on a tag via GitOps comment. This capability enables you to test or deploy tagged versions of your code, supporting release-based workflows and version control practices.

Pipelines as Code resolves the Git tag to its corresponding commit SHA and runs the`PipelineRun` defined for that commit. You can initiate or manage such runs by commenting on the tagged commit using supported GitOps commands.

Triggering`PipelineRun` on Git tags is supported for GitHub App, GitHub Webhook, and GitLab integrations. This functionality is not currently available for Gitea, Bitbucket Cloud, or Bitbucket Data Center.

#### 6.3.1. Supported GitOps commandsCopy link

- `/test tag: `:: Retrigger all matching`PipelineRun` for the tag commit
- `/test tag: `:: Retrigger only the named`PipelineRun`
- `/retest tag: `:: Retrigger all matching`PipelineRun` for the tag commit
- `/retest tag: `:: Retrigger only the named`PipelineRun`
- `/cancel tag: `:: Cancel all running`PipelineRun` for the tag commit
- `/cancel tag: `:: Cancel only the named`PipelineRun`

A`PipelineRun` configured to respond to tag events typically includes annotations such as`pipelinesascode.tekton.dev/on-target-branch: "[refs/tags/*]"` and`pipelinesascode.tekton.dev/on-event: "[push]"`. These annotations ensure that tag creation events are recognized as push events and processed correctly. When a tag is created or referenced, Pipelines as Code treats it as a push event and uses the resolved commit SHA to run the associated`PipelineRun`. The same logic applies to subsequent GitOps commands added as comments on the tagged commit.

Tag based triggering is particularly useful in continuous delivery scenarios where specific versions or releases are represented by tags. This method enables you to validate and promote release artifacts consistently without modifying the branch state.

The following example shows a minimal`PipelineRun` configuration that triggers on tag events:

```yaml
apiVersion: tekton.dev/v1
kind: PipelineRun
metadata:
  name: pipelinerun-on-tag
  annotations:
    pipelinesascode.tekton.dev/on-target-branch: "[refs/tags/*]"
    pipelinesascode.tekton.dev/on-event: "[push]"
spec:
  pipelineSpec:
    tasks:
      - name: tag-task
        taskSpec:
          steps:
            - name: echo
              image: registry.access.redhat.com/ubi9/ubi-micro
              script: |
                echo "tag: {{ git_tag }}"
```

#### 6.3.2. Triggering PipelineRuns for GitOps commands using tagged commitsCopy link

You can trigger a`PipelineRun` using a Git tag for tagged commits.

Procedure

1. Open your GitHub repository.
2. Navigate to the Tags view or the Releases section.
3. Select the required tag, for example`v1.0.0`, to view its associated commit.
4. Click the commit SHA for the selected tag.
5. In the comment field of the commit, enter a supported GitOps command, such as`/test tag:v1.0.0` or`/cancel tag:v1.0.0`.
6. Verify that Pipelines as Code processes the comment and triggers or updates the corresponding`PipelineRun`.

### 6.4. Restarting or canceling a pipeline run using Pipelines as CodeCopy link

You can restart or cancel a pipeline run without triggering an event, such as sending a new commit to a branch or creating a pull request. To restart all pipeline runs, use the Re-run all checks feature in the GitHub App.

To restart all or specific pipeline runs, use the following comments:

- The`/test` and`/retest` comments restart all pipeline runs.
- The`/test ` and`/retest ` comments start or restart a specific pipeline run. You can use these commands to start any Pipelines as Code pipeline run on the repository, whether or not it was triggered by an event for this pipeline run.

To cancel all or specific pipeline runs, use the following comments:

- The`/cancel` comment cancels all pipeline runs.
- The`/cancel ` comment cancels a specific pipeline run.

The results of the comments are visible under the Checks tab of the GitHub App.

The comment starts, restarts, or cancels any pipeline runs only if the comment author meets one of the following requirements:

- The author is the owner of the repository.
- The author is a collaborator on the repository.
- The author is a public member of the organization that owns the repository.
- The comment author is listed in the`approvers` or`reviewers` section of the`OWNERS` file in the root of the repository, as defined in the [Kubernetes documentation](https://www.kubernetes.dev/docs/guide/owners/). Pipelines as Code supports the specification for the`OWNERS` and`OWNERS_ALIASES` files. If the`OWNERS` file includes a [filters](https://github.com/kubernetes/community/blob/master/contributors/guide/owners.md#filters) section, Pipelines as Code matches approvers and reviewers only against the`.*` filter.

Important

Using a comment to start a pipeline run that does not match an event is a Technology Preview feature only. Technology Preview features are not supported with Red Hat production service level agreements (SLAs) and might not be functionally complete. Red Hat does not recommend using them in production. These features provide early access to upcoming product features, enabling customers to test functionality and provide feedback during the development process.

For more information about the support scope of Red Hat Technology Preview features, see [Technology Preview Features Support Scope](https://access.redhat.com/support/offerings/techpreview/).

Procedure

If you target a pull or merge request, use the comments inside your pull request:

Example comment that cancels all pipeline runs

```plaintext
This is a comment inside a pull request.
/cancel
```

If you target a push request, include the comments within your commit messages.

Note

This feature is supported for the GitHub and GitLab providers only.

click the line number where you want to add a comment. The following example shows a comment that starts or restarts a specific pipeline run:

```plaintext
This is a comment inside a commit.
/retest example_pipeline_run
```

Note

If you run a command on a commit that exists in multiple branches within a push request, the branch with the latest commit is used.

This results in two situations:

- If you run a command on a commit without any argument, such as`/test`, the test is automatically performed on the`main` branch.
- If you include a branch specification, such as`/test branch:user-branch`, the test is performed on the commit where the comment is located with the context of the`user-branch` branch.

### 6.5. Monitoring pipeline run status using Pipelines as CodeCopy link

Depending on the context and supported tools, you can monitor the status of a pipeline run in different ways.

Status on GitHub Apps

When a pipeline run finishes, the status is added in the Check tabs with limited information on how long each task of your pipeline took, and the output of the`tkn pipelinerun describe` command.

Log error snippet

When Pipelines as Code detects an error in one of the tasks of a pipeline, a small snippet consisting of the last 3 lines in the task breakdown of the first failed task is displayed.

Note

Pipelines as Code avoids leaking secrets by looking into the pipeline run and replacing secret values with hidden characters. However, Pipelines as Code cannot hide secrets coming from workspaces and envFrom source.

Annotations for log error snippets

In the`TektonConfig` custom resource, in the`pipelinesAsCode.settings` spec, you can set the`error-detection-from-container-logs` parameter to`true`. In this case, Pipelines as Code detects the errors from the container logs and adds them as annotations on the pull request where the error occurred.

Important

Adding annotations for log error snippets is a Technology Preview feature only. Technology Preview features are not supported with Red Hat production service level agreements (SLAs) and might not be functionally complete. Red Hat does not recommend using them in production. These features provide early access to upcoming product features, enabling customers to test functionality and provide feedback during the development process.

For more information about the support scope of Red Hat Technology Preview features, see [Technology Preview Features Support Scope](https://access.redhat.com/support/offerings/techpreview/).

Currently, Pipelines as Code supports only the simple cases where the error looks like`makefile` or`grep` output of the following format:

```yaml
<filename>:<line>:<column>: <error message>
```

You can customize the regular expression used to detect the errors with the`error-detection-simple-regexp` parameter. The regular expression uses named groups to give flexibility on how to specify the matching. The groups needed to match are`filename`,`line`, and`error`. You can view the Pipelines as Code config map for the default regular expression.

Note

By default, Pipelines as Code scans only the last 50 lines of the container logs. You can increase this value in the`error-detection-max-number-of-lines` field or set`-1` for an unlimited number of lines. However, such configurations might increase the memory usage of the watcher.

Status for webhook

For webhook, when the event is a pull request, the status is added as a comment on the pull or merge request.

Failures

If a namespace is matched to a`Repository` custom resource definition (CRD), Pipelines as Code emits its failure log messages in the Kubernetes events inside the namespace.

YAML parting error messages in pull request comments

Pipelines as Code detects and reports YAML errors in`PipelineRun` definitions within the`.tekton` directory. When an invalid`PipelineRun` YAML is found in a pull request (PR) event, Pipelines as Code creates a comment on the pull request (PR) describing the error and halts the execution of other correctly formatted pipeline runs. Consider the following scenarios:

- If the PR is updated and new YAML errors are detected, the comment is updated with the latest information.
- If no new errors are found, the existing comment remains unchanged and is not deleted.
- If a validation issue is found, Pipelines as Code logs the error in the user namespace events log and the Pipelines as Code controller log.

These error scenarios do not halt the execution of valid pipeline runs because the system continues to process validated and matched pipeline runs, preventing a single validation problem from disrupting the entire automation workflow.

Status associated with Repository CRD

The last 5 status messages for a pipeline run is stored inside the`Repository` custom resource.

```shell-session
$ oc get repo -n <pipelines-as-code-ci>
```

```shell-session
NAME                  URL                                                        NAMESPACE             SUCCEEDED   REASON      STARTTIME   COMPLETIONTIME
pipelines-as-code-ci   https://github.com/openshift-pipelines/pipelines-as-code   pipelines-as-code-ci   True        Succeeded   59m         56m
```

Using the`tkn pac describe` command, you can extract the status of the runs associated with your repository and its metadata.

Notifications

Pipelines as Code does not manage notifications. If you need to have notifications, use the`finally` feature of pipelines.

Additional resources

- [An example task to send Slack messages on success or failure](https://github.com/chmouel/tekton-slack-task-status)
- [An example of a pipeline run with finally tasks triggered on push events](https://github.com/openshift-pipelines/pipelines-as-code/blob/7b41cc3f769af40a84b7ead41c6f037637e95070/.tekton/push.yaml)

Additional resources

- [An example of the git-clone task used for cloning private repositories](https://github.com/openshift-pipelines/pipelines-as-code/blob/main/test/testdata/pipelinerun_git_clone_private.yaml)

### 6.6. Cleaning up pipeline run using Pipelines as CodeCopy link

There can be many pipeline runs in a user namespace. By setting the`max-keep-runs` annotation, you can configure Pipelines as Code to retain a limited number of pipeline runs that matches an event. For example:

```yaml
...
  pipelinesascode.tekton.dev/max-keep-runs: "<max_number>" 
```

1

```yaml

...
```

1

Pipelines as Code starts cleaning up right after it finishes a successful execution, retaining only the maximum number of pipeline runs configured using the annotation.

Note

- Pipelines as Code skips cleaning the running pipelines but cleans up the pipeline runs with an unknown status.
- Pipelines as Code skips cleaning a failed pull request.

### 6.7. Using incoming webhook with Pipelines as CodeCopy link

Using an incoming webhook URL and a shared secret, you can start a pipeline run in a repository.

To use incoming webhooks, specify the following within the`spec` section of the`Repository` custom resource definition (CRD):

The Git provider and the user token. Currently, Pipelines as Code supports`github`,`gitlab`, and`bitbucket-cloud`.

Note

When using incoming webhook URLs in the context of GitHub app, you must specify the token.

- The incoming webhook URL that Pipelines as Code matches.
- The target branches and a secret for the incoming webhook URL.

Example:`Repository` CRD with incoming webhook

```yaml
apiVersion: "pipelinesascode.tekton.dev/v1alpha1"
kind: Repository
metadata:
  name: repo
  namespace: ns
spec:
  url: "https://github.com/owner/repo"
  git_provider:
    type: github
    secret:
      name: "owner-token"
  incoming:
    - targets:
      - main
      secret:
        name: repo-incoming-secret
      type: webhook-url
```

Example: The`repo-incoming-secret` secret for incoming webhook

```yaml
apiVersion: v1
kind: Secret
metadata:
  name: repo-incoming-secret
  namespace: ns
type: Opaque
stringData:
  secret: <very-secure-shared-secret>
```

To trigger a pipeline run located in the`.tekton` directory of a Git repository, use the following command:

```shell-session
$ curl -X POST 'https://control.pac.url/incoming?secret=very-secure-shared-secret&repository=repo&branch=main&pipelinerun=target_pipelinerun'
```

Pipelines as Code matches the incoming URL and treats it as a`push` event. However, Pipelines as Code does not report status of the pipeline runs triggered by this command.

To get a report or a notification, add it directly with a`finally` task to your pipeline. Alternatively, you can inspect the`Repository` CRD with the`tkn pac` CLI tool.

## Chapter 7. Pipelines as Code command referenceCopy link

You can use the`tkn pac` CLI tool to control Pipelines as Code. You can also configure Pipelines as Code logging with the`TektonConfig` custom resource and use the`oc` command to view Pipelines as Code logs.

### 7.1. Pipelines as Code command referenceCopy link

The`tkn pac` CLI tool offers the following capabilities:

- Bootstrap Pipelines as Code installation and configuration.
- Create a new Pipelines as Code repository.
- List all Pipelines as Code repositories.
- Describe a Pipelines as Code repository and the associated runs.
- Generate a simple pipeline run to get started.
- Resolve a pipeline run as if it was executed by Pipelines as Code.

Tip

You can use the commands corresponding to the capabilities for testing and experimentation, so that you don’t have to make changes to the Git repository containing the application source code.

#### 7.1.1. Basic syntaxCopy link

```shell-session
$ tkn pac [command or options] [arguments]
```

#### 7.1.2. Global optionsCopy link

```shell-session
$ tkn pac --help
```

#### 7.1.3. Utility commandsCopy link

bootstrap 

Expand

`tkn pac bootstrap`

Installs and configures Pipelines as Code for Git repository hosting service providers, such as GitHub and GitHub Enterprise.

`tkn pac bootstrap --nightly`

Installs the nightly build of Pipelines as Code.

`tkn pac bootstrap --route-url `

Overrides the OpenShift route URL.

By default,`tkn pac bootstrap` detects the OpenShift route, which is automatically associated with the Pipelines as Code controller service.

If you do not have an OpenShift Container Platform cluster, it asks you for the public URL that points to the ingress endpoint.

`tkn pac bootstrap github-app`

Create a GitHub application and secrets in the`openshift-pipelines` namespace.

| | Command | Description |
| --- | --- | --- |

Show more

repository 

Expand

`tkn pac create repository`

Creates a new Pipelines as Code repository and a namespace based on the pipeline run template.

`tkn pac list`

Lists all the Pipelines as Code repositories and displays the last status of the associated runs.

`tkn pac repo describe`

Describes a Pipelines as Code repository and the associated runs.

| | Command | Description |
| --- | --- | --- |

Show more

generate 

Expand

`tkn pac generate`

Generates a simple pipeline run.

When executed from the directory containing the source code, it automatically detects current Git information.

In addition, it uses basic language detection capability and adds extra tasks depending on the language.

For example, if it detects a`setup.py` file at the repository root, the [pylint task](https://hub.tekton.dev/tekton/task/pylint) is automatically added to the generated pipeline run.

| | Command | Description |
| --- | --- | --- |

Show more

resolve 

Expand

`tkn pac resolve`

Executes a pipeline run as if it is owned by the Pipelines as Code on service.

`tkn pac resolve -f .tekton/pull-request.yaml | oc apply -f -`

Displays the status of a live pipeline run that uses the template in`.tekton/pull-request.yaml`.

Combined with a Kubernetes installation running on your local machine, you can observe the pipeline run without generating a new commit.

If you run the command from a source code repository, it attempts to detect the current Git information and automatically resolve parameters such as current revision or branch.

`tkn pac resolve -f .tekton/pr.yaml -p revision=main -p repo_name= `

Executes a pipeline run by overriding default parameter values derived from the Git repository.

The`-f` option can also accept a directory path and apply the`tkn pac resolve` command on all`.yaml` or`.yml` files in that directory. You can also use the`-f` flag multiple times in the same command.

You can override the default information gathered from the Git repository by specifying parameter values using the`-p` option. For example, you can use a Git branch as a revision and a different repository name.

| | Command | Description |
| --- | --- | --- |

Show more

cel 

Expand

`tkn pac cel`

Evaluate CEL (Common Expression Language) expressions against webhook payloads and headers. Because the command requires access to the raw webhook payload and header data provided by the Git provider, it is intended for administrator use for testing and debugging event filtering logic in Pipelines as Code.

| | Command | Description |
| --- | --- | --- |

Show more

Important

The`tkn pac cel` command is a Technology Preview feature only. Technology Preview features are not supported with Red Hat production service level agreements (SLAs) and might not be functionally complete. Red Hat does not recommend using them in production. These features provide early access to upcoming product features, enabling customers to test functionality and provide feedback during the development process.

For more information about the support scope of Red Hat Technology Preview features, see [Technology Preview Features Support Scope](https://access.redhat.com/support/offerings/techpreview/).

The`tkn pac cel` command uses the following syntax:

```shell-session
tkn pac cel -b <body.json> -H <headers.txt> [-p <provider>]
```

Expand

`-b, --body`

Specify the JSON payload file.

`-H, --headers`

Specify the headers file (plain text, JSON, or gosmee script).

`-p, --provider`

Optional: specify the Git provider (`auto`,`github`,`gitlab`,`bitbucket-cloud`,`bitbucket-datacenter`,`gitea`).

| | Option | Description |
| --- | --- | --- |

Show more

Header formats

The`tkn pac cel` command supports the following header formats:

Plain HTTP header format

```plaintext
X-GitHub-Event: pull_request
Content-Type: application/json
User-Agent: GitHub-Hookshot/abc123
```

JSON header format

```plaintext
{
  "X-GitHub-Event": "pull_request",
  "Content-Type": "application/json",
  "User-Agent": "GitHub-Hookshot/abc123"
}
```

Gosmee-generated shell scripts

The`tkn pac cel` command automatically detects and parses headers from gosmee`--save` scripts. The following is an example using GitHub:

```bash
#!/usr/bin/env bash
curl -X POST "http://localhost:8080/" \
  -H "X-GitHub-Event: pull_request" \
  -H "Content-Type: application/json" \
  -H "User-Agent: GitHub-Hookshot/abc123" \
  -d @payload.json
```

Interactive mode

The`tkn pac cel` command displays an interactive`CEL expression` prompt when executed in a terminal. Expression history is navigable using arrow keys and persists between sessions. Pressing Enter on an empty line exits the prompt.

noninteractive mode

The`tkn pac cel` command accepts CEL expressions using standard input (stdin), allowing evaluation without using the interactive prompt. For example:

```shell-session
echo 'event == "pull_request"' | tkn pac cel -b body.json -H headers.txt
```

Available variables

The`tkn pac cel` command exposes the following variables that can be used in CEL expressions to filter or inspect events:

- `event`: Event type (`push`,`pull_request`)
- `target_branch`: Target branch name
- `source_branch`: Source branch name
- `target_url`: Target repository URL
- `source_url`: Source repository URL
- `event_title`: Pull request title or commit message
- `body.*`: All fields from the webhook payload
- `headers.*`: All HTTP headers
- `files.*`: Always empty in CLI mode
- `pac.*`: All PAC parameters for backward compatibility

Note

When evaluating CEL expressions locally using the CLI,`files.*` variables are always empty, and functions such as`fileChanged`,`fileDeleted`, and`fileModified` are unavailable.

Example expressions

The following expressions illustrate typical use cases, including event filtering, branch matching, and header inspection:

```shell-session
event == "pull_request" && target_branch == "main"
event == "pull_request" && source_branch.matches(".*feat/.*")
body.action == "synchronize"
!body.pull_request.draft
headers["x-github-event"] == "pull_request"
```

History storage

The`tkn pac cel` command maintains a persistent history and creates the`cel-history` directory automatically if it does not exist in the following paths:

- Linux or macOS:`~/.cache/tkn-pac/cel-history`
- Windows:`%USERPROFILE%\.cache\tkn-pac\cel-history`

### 7.2. Configuring Pipelines as Code loggingCopy link

You can configure Pipelines as Code logging by editing the`pac-config-logging` config map in the`TektonConfig` custom resource (CR).

Prerequisites

- You have Pipelines as Code installed on your cluster.

Procedure

Edit the`loglevel.` fields under the`.options.configMaps.pac-config-logging.data` parameter based on your requirements.

Example`TektonConfig` CR with the Pipelines as Code log level fields set to`warn`

```yaml
apiVersion: operator.tekton.dev/v1alpha1
kind: TektonConfig
metadata:
  name: config
spec:
  platforms:
    openshift:
      pipelinesAsCode:
        options:
          configMaps:
            pac-config-logging:
              data:
                loglevel.pac-watcher: warn 
```

1

```yaml

                loglevel.pipelines-as-code-webhook: warn 
```

2

```yaml

                loglevel.pipelinesascode: warn 
```

3

```yaml

                zap-logger-config: |
                  {
                    "level": "info",
                    "development": false,
                    "sampling": {
                      "initial": 100,
                      "thereafter": 100
                    },
                    "outputPaths": ["stdout"],
                    "errorOutputPaths": ["stderr"],
                    "encoding": "json",
                    "encoderConfig": {
                      "timeKey": "ts",
                      "levelKey": "level",
                      "nameKey": "logger",
                      "callerKey": "caller",
                      "messageKey": "msg",
                      "stacktraceKey": "stacktrace",
                      "lineEnding": "",
                      "levelEncoder": "",
                      "timeEncoder": "iso8601",
                      "durationEncoder": "",
                      "callerEncoder": ""
                    }
                  }
```

1

The log level for the`pipelines-as-code-watcher` component.

2

The log level for the`pipelines-as-code-webhook` component.

3

The log level for the`pipelines-as-code-controller` component.

Optional: Create a custom logging config map for the Pipelines as Code components by changing the`.env.value` for each component under the`.options.deployments` field. The example below shows the configuration with the custom config map called`custom-pac-config-logging`.

Example`TektonConfig` CR with the Pipelines as Code custom logging config map

```yaml
apiVersion: operator.tekton.dev/v1alpha1
kind: TektonConfig
metadata:
  name: config
spec:
  platforms:
    openshift:
      pipelinesAsCode:
        enable: true
        options:
          configMaps:
            custom-pac-config-logging:
              data:
                loglevel.pac-watcher: warn
                loglevel.pipelines-as-code-webhook: warn
                loglevel.pipelinesascode: warn
                zap-logger-config: |
                  {
                    "level": "info",
                    "development": false,
                    "sampling": {
                      "initial": 100,
                      "thereafter": 100
                    },
                    "outputPaths": ["stdout"],
                    "errorOutputPaths": ["stderr"],
                    "encoding": "json",
                    "encoderConfig": {
                      "timeKey": "ts",
                      "levelKey": "level",
                      "nameKey": "logger",
                      "callerKey": "caller",
                      "messageKey": "msg",
                      "stacktraceKey": "stacktrace",
                      "lineEnding": "",
                      "levelEncoder": "",
                      "timeEncoder": "iso8601",
                      "durationEncoder": "",
                      "callerEncoder": ""
                    }
                  }
          deployments:
            pipelines-as-code-controller:
              spec:
                template:
                  spec:
                    containers:
                    - name: pac-controller
                      env:
                      - name: CONFIG_LOGGING_NAME
                        value: custom-pac-config-logging
            pipelines-as-code-watcher:
              spec:
                template:
                  spec:
                    containers:
                    - name: pac-watcher
                      env:
                      - name: CONFIG_LOGGING_NAME
                        value: custom-pac-config-logging
            pipelines-as-code-webhook:
              spec:
                template:
                  spec:
                    containers:
                    - name: pac-webhook
                      env:
                      - name: CONFIG_LOGGING_NAME
                        value: custom-pac-config-logging
```

1. In the Administrator perspective of the web console, go to Administration → CustomResourceDefinitions.
2. Use the Search by name field to search for the`tektonconfigs.operator.tekton.dev` custom resource definition (CRD) and click TektonConfig to view the CRD Details page.
3. Click the Instances tab.
4. Click the config instance to view the`TektonConfig` CR details.
5. Click the YAML tab.

### 7.3. Splitting Pipelines as Code logs by namespaceCopy link

Pipelines as Code logs contain the namespace information to make it possible to filter logs or split the logs by a particular namespace. For example, to view the Pipelines as Code logs related to the`mynamespace` namespace, enter the following command:

```shell-session
$ oc logs pipelines-as-code-controller-<unique-id> -n openshift-pipelines | grep mynamespace 
```

1

1

Replace`pipelines-as-code-controller- ` with the Pipelines as Code controller name.

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



## Creating CI/CD pipelines

Creating CI/CD pipelines | Red Hat OpenShift Pipelines | 1.21 | Red Hat Documentation

 

 

 

 

 

 

 

 

# Creating CI/CD pipelines

---

Red Hat OpenShift Pipelines 1.21

## Getting started with creating and running tasks and pipelines in OpenShift Pipelines

Red Hat OpenShift Documentation Team

Legal Notice

Abstract

This document provides information about creating and running tasks and pipelines in OpenShift Pipelines.

---

## Chapter 1. Creating CI/CD solutions for applications using OpenShift PipelinesCopy link

With Red Hat OpenShift Pipelines, you can create a customized CI/CD solution to build, test, and deploy your application.

To create a full-fledged, self-serving CI/CD pipeline for an application, perform the following tasks:

Provide a storage volume or filesystem that is attached to a workspace for the pipeline execution, using one of the following approaches:

- Specify a volume claim template that creates a persistent volume claim
- Specify a persistent volume claim

This section uses the`pipelines-tutorial` example to demonstrate the preceding tasks. The example uses a simple application which consists of:

- Add triggers to capture events in the source repository.
- A front-end interface,`pipelines-vote-ui`, with the source code in the [pipelines-vote-ui](https://github.com/openshift/pipelines-vote-ui/tree/pipelines-1.21) Git repository.
- A back-end interface,`pipelines-vote-api`, with the source code in the [pipelines-vote-api](https://github.com/openshift/pipelines-vote-api/tree/pipelines-1.21) Git repository.
- The`apply-manifests` and`update-deployment` tasks in the [pipelines-tutorial](https://github.com/openshift/pipelines-tutorial/tree/pipelines-1.21) Git repository.

### 1.1. PrerequisitesCopy link

- You have access to an OpenShift Container Platform cluster.
- You have installed [OpenShift Pipelines](https://docs.redhat.com/en/documentation/red_hat_openshift_pipelines/1.21/html-single/installing_and_configuring/#installing-pipelines) using the Red Hat OpenShift Pipelines Operator listed in the OpenShift OperatorHub. After it is installed, it is applicable to the entire cluster.
- You have installed [OpenShift Pipelines CLI](https://docs.redhat.com/en/documentation/red_hat_openshift_pipelines/1.21/html-single/pipelines_cli_tkn_reference/#installing-tkn).
- You have forked the front-end [pipelines-vote-ui](https://github.com/openshift/pipelines-vote-ui/tree/pipelines-1.21) and back-end [pipelines-vote-api](https://github.com/openshift/pipelines-vote-api/tree/pipelines-1.21) Git repositories using your GitHub ID, and have administrator access to these repositories.
- Optional: You have cloned the [pipelines-tutorial](https://github.com/openshift/pipelines-tutorial/tree/pipelines-1.21) Git repository.

### 1.2. Creating a project and checking your pipeline service accountCopy link

Procedure

Log in to your OpenShift Container Platform cluster:

```plaintext
$ oc login -u <login> -p <password> https://openshift.example.com:6443
```

Create a project for the sample application. For this example workflow, create the`pipelines-tutorial` project:

```plaintext
$ oc new-project pipelines-tutorial
```

Note

If you create a project with a different name, be sure to update the resource URLs used in the example with your project name.

View the`pipeline` service account:

Red Hat OpenShift Pipelines Operator adds and configures a service account named`pipeline` that has sufficient permissions to build and push an image. This service account is used by the`PipelineRun` object.

```plaintext
$ oc get serviceaccount pipeline
```

### 1.3. Creating pipeline tasksCopy link

Procedure

Install the`apply-manifests` and`update-deployment` task resources from the`pipelines-tutorial` repository, which contains a list of reusable tasks for pipelines:

```shell-session
$ oc create -f https://raw.githubusercontent.com/openshift/pipelines-tutorial/pipelines-1.21/01_pipeline/01_apply_manifest_task.yaml
$ oc create -f https://raw.githubusercontent.com/openshift/pipelines-tutorial/pipelines-1.21/01_pipeline/02_update_deployment_task.yaml
```

Use the`tkn task list` command to list the tasks you created:

```shell-session
$ tkn task list
```

The output verifies that the`apply-manifests` and`update-deployment` task resources were created:

```shell-session
NAME                DESCRIPTION   AGE
apply-manifests                   1 minute ago
update-deployment                 48 seconds ago
```

### 1.4. Assembling a pipelineCopy link

A pipeline represents a CI/CD flow and is defined by the tasks to be executed. It is designed to be generic and reusable in multiple applications and environments.

A pipeline specifies how the tasks interact with each other and their order of execution by using the`from` and`runAfter` parameters. It uses the`workspaces` field to specify one or more volumes that each task in the pipeline requires during execution.

In this section, you will create a pipeline that takes the source code of the application from GitHub, and then builds and deploys it on OpenShift Container Platform.

The pipeline performs the following tasks for the back-end application`pipelines-vote-api` and front-end application`pipelines-vote-ui`:

- Clones the source code of the application from the Git repository by referring to the`git-url` and`git-revision` parameters.
- Builds the container image using the`buildah` task provided in the`openshift-pipelines` namespace.
- Pushes the image to the OpenShift image registry by referring to the`image` parameter.
- Deploys the new image on OpenShift Container Platform by using the`apply-manifests` and`update-deployment` tasks.

Procedure

Copy the contents of the following sample pipeline YAML file and save it:

```yaml
apiVersion: tekton.dev/v1
kind: Pipeline
metadata:
  name: build-and-deploy
spec:
  workspaces:
  - name: shared-workspace
  params:
  - name: deployment-name
    type: string
    description: name of the deployment to be patched
  - name: git-url
    type: string
    description: url of the git repo for the code of deployment
  - name: git-revision
    type: string
    description: revision to be used from repo of the code for deployment
    default: "pipelines-1.21"
  - name: IMAGE
    type: string
    description: image to be built from the code
  tasks:
  - name: fetch-repository
    taskRef:
      resolver: cluster
      params:
      - name: kind
        value: task
      - name: name
        value: git-clone
      - name: namespace
        value: openshift-pipelines
    workspaces:
    - name: output
      workspace: shared-workspace
    params:
    - name: URL
      value: $(params.git-url)
    - name: SUBDIRECTORY
      value: ""
    - name: DELETE_EXISTING
      value: "true"
    - name: REVISION
      value: $(params.git-revision)
  - name: build-image
    taskRef:
      resolver: cluster
      params:
      - name: kind
        value: task
      - name: name
        value: buildah
      - name: namespace
        value: openshift-pipelines
    workspaces:
    - name: source
      workspace: shared-workspace
    params:
    - name: IMAGE
      value: $(params.IMAGE)
    runAfter:
    - fetch-repository
  - name: apply-manifests
    taskRef:
      name: apply-manifests
    workspaces:
    - name: source
      workspace: shared-workspace
    runAfter:
    - build-image
  - name: update-deployment
    taskRef:
      name: update-deployment
    params:
    - name: deployment
      value: $(params.deployment-name)
    - name: IMAGE
      value: $(params.IMAGE)
    runAfter:
    - apply-manifests
```

The pipeline definition abstracts away the specifics of the Git source repository and image registries. These details are added as`params` when a pipeline is triggered and executed.

Create the pipeline:

```plaintext
$ oc create -f <pipeline-yaml-file-name.yaml>
```

Alternatively, you can also execute the YAML file directly from the Git repository:

```shell-session
$ oc create -f https://raw.githubusercontent.com/openshift/pipelines-tutorial/pipelines-1.21/01_pipeline/04_pipeline.yaml
```

Use the`tkn pipeline list` command to verify that the pipeline is added to the application:

```plaintext
$ tkn pipeline list
```

The output verifies that the`build-and-deploy` pipeline was created:

```plaintext
NAME               AGE            LAST RUN   STARTED   DURATION   STATUS
build-and-deploy   1 minute ago   ---        ---       ---        ---
```

### 1.5. Mirroring images to run pipelines in a restricted environmentCopy link

To run OpenShift Pipelines in a disconnected cluster or a cluster provisioned in a restricted environment, ensure that either the Samples Operator is configured for a restricted network, or a cluster administrator has created a cluster with a mirrored registry.

The following procedure uses the`pipelines-tutorial` example to create a pipeline for an application in a restricted environment using a cluster with a mirrored registry. To ensure that the`pipelines-tutorial` example works in a restricted environment, you must mirror the respective builder images from the mirror registry for the front-end interface,`pipelines-vote-ui`; back-end interface,`pipelines-vote-api`; and the`cli`.

Procedure

Mirror the builder image from the mirror registry for the front-end interface,`pipelines-vote-ui`.

Verify that the required images tag is not imported:

```shell-session
$ oc describe imagestream python -n openshift
```

Example output

```shell-session
Name:			python
Namespace:		openshift
[...]

3.8-ubi9 (latest)
  tagged from registry.redhat.io/ubi9/python-38:latest
    prefer registry pullthrough when referencing this tag

  Build and run Python 3.8 applications on UBI 8. For more information about using this builder image, including OpenShift considerations, see https://github.com/sclorg/s2i-python-container/blob/master/3.8/README.md.
  Tags: builder, python
  Supports: python:3.8, python
  Example Repo: https://github.com/sclorg/django-ex.git

[...]
```

Mirror the supported image tag to the private registry:

```shell-session
$ oc image mirror registry.redhat.io/ubi9/python-39:latest <mirror-registry>:<port>/ubi9/python-39
```

Import the image:

```shell-session
$ oc tag <mirror-registry>:<port>/ubi9/python-39 python:latest --scheduled -n openshift
```

You must periodically re-import the image. The`--scheduled` flag enables automatic re-import of the image.

Verify that the images with the given tag have been imported:

```shell-session
$ oc describe imagestream python -n openshift
```

Example output

```shell-session
Name:			python
Namespace:		openshift
[...]

latest
  updates automatically from registry  <mirror-registry>:<port>/ubi9/python-39

  *  <mirror-registry>:<port>/ubi9/python-39@sha256:3ee...

[...]
```

Mirror the builder image from the mirror registry for the back-end interface,`pipelines-vote-api`.

Verify that the required images tag is not imported:

```shell-session
$ oc describe imagestream golang -n openshift
```

Example output

```shell-session
Name:			golang
Namespace:		openshift
[...]

1.14.7-ubi8 (latest)
  tagged from registry.redhat.io/ubi8/go-toolset:1.14.7
    prefer registry pullthrough when referencing this tag

  Build and run Go applications on UBI 8. For more information about using this builder image, including OpenShift considerations, see https://github.com/sclorg/golang-container/blob/master/README.md.
  Tags: builder, golang, go
  Supports: golang
  Example Repo: https://github.com/sclorg/golang-ex.git

[...]
```

Mirror the supported image tag to the private registry:

```shell-session
$ oc image mirror registry.redhat.io/ubi9/go-toolset:latest <mirror-registry>:<port>/ubi9/go-toolset
```

Import the image:

```shell-session
$ oc tag <mirror-registry>:<port>/ubi9/go-toolset golang:latest --scheduled -n openshift
```

You must periodically re-import the image. The`--scheduled` flag enables automatic re-import of the image.

Verify that the images with the given tag have been imported:

```shell-session
$ oc describe imagestream golang -n openshift
```

Example output

```shell-session
Name:			golang
Namespace:		openshift
[...]

latest
  updates automatically from registry <mirror-registry>:<port>/ubi9/go-toolset

  * <mirror-registry>:<port>/ubi9/go-toolset@sha256:59a74d581df3a2bd63ab55f7ac106677694bf612a1fe9e7e3e1487f55c421b37

[...]
```

Mirror the builder image from the mirror registry for the`cli`.

Verify that the required images tag is not imported:

```shell-session
$ oc describe imagestream cli -n openshift
```

Example output

```shell-session
Name:                   cli
Namespace:              openshift
[...]

latest
  updates automatically from registry quay.io/openshift-release-dev/ocp-v4.0-art-dev@sha256:65c68e8c22487375c4c6ce6f18ed5485915f2bf612e41fef6d41cbfcdb143551

  * quay.io/openshift-release-dev/ocp-v4.0-art-dev@sha256:65c68e8c22487375c4c6ce6f18ed5485915f2bf612e41fef6d41cbfcdb143551

[...]
```

Mirror the supported image tag to the private registry:

```shell-session
$ oc image mirror quay.io/openshift-release-dev/ocp-v4.0-art-dev@sha256:65c68e8c22487375c4c6ce6f18ed5485915f2bf612e41fef6d41cbfcdb143551 <mirror-registry>:<port>/openshift-release-dev/ocp-v4.0-art-dev:latest
```

Import the image:

```shell-session
$ oc tag <mirror-registry>:<port>/openshift-release-dev/ocp-v4.0-art-dev cli:latest --scheduled -n openshift
```

You must periodically re-import the image. The`--scheduled` flag enables automatic re-import of the image.

Verify that the images with the given tag have been imported:

```shell-session
$ oc describe imagestream cli -n openshift
```

Example output

```shell-session
Name:                   cli
Namespace:              openshift
[...]

latest
  updates automatically from registry <mirror-registry>:<port>/openshift-release-dev/ocp-v4.0-art-dev

  * <mirror-registry>:<port>/openshift-release-dev/ocp-v4.0-art-dev@sha256:65c68e8c22487375c4c6ce6f18ed5485915f2bf612e41fef6d41cbfcdb143551

[...]
```

### 1.6. Running a pipelineCopy link

A`PipelineRun` resource starts a pipeline and ties it to the Git and image resources that should be used for the specific invocation. It automatically creates and starts the`TaskRun` resources for each task in the pipeline.

Procedure

Start the pipeline for the back-end application:

```yaml
$ tkn pipeline start build-and-deploy \
    -w name=shared-workspace,volumeClaimTemplateFile=https://raw.githubusercontent.com/openshift/pipelines-tutorial/pipelines-1.21/01_pipeline/03_persistent_volume_claim.yaml \
    -p deployment-name=pipelines-vote-api \
    -p git-url=https://github.com/openshift/pipelines-vote-api.git \
    -p IMAGE='image-registry.openshift-image-registry.svc:5000/pipelines-tutorial/pipelines-vote-api' \
    --use-param-defaults
```

The previous command uses a volume claim template, which creates a persistent volume claim for the pipeline execution.

To track the progress of the pipeline run, enter the following command::

```yaml
$ tkn pipelinerun logs <pipelinerun_id> -f
```

The in the above command is the ID for the`PipelineRun` that was returned in the output of the previous command.

Start the pipeline for the front-end application:

```yaml
$ tkn pipeline start build-and-deploy \
    -w name=shared-workspace,volumeClaimTemplateFile=https://raw.githubusercontent.com/openshift/pipelines-tutorial/pipelines-1.21/01_pipeline/03_persistent_volume_claim.yaml \
    -p deployment-name=pipelines-vote-ui \
    -p git-url=https://github.com/openshift/pipelines-vote-ui.git \
    -p IMAGE='image-registry.openshift-image-registry.svc:5000/pipelines-tutorial/pipelines-vote-ui' \
    --use-param-defaults
```

To track the progress of the pipeline run, enter the following command:

```yaml
$ tkn pipelinerun logs <pipelinerun_id> -f
```

The in the above command is the ID for the`PipelineRun` that was returned in the output of the previous command.

After a few minutes, use`tkn pipelinerun list` command to verify that the pipeline ran successfully by listing all the pipeline runs:

```yaml
$ tkn pipelinerun list
```

The output lists the pipeline runs:

```yaml
 NAME                         STARTED      DURATION     STATUS
 build-and-deploy-run-xy7rw   1 hour ago   2 minutes    Succeeded
 build-and-deploy-run-z2rz8   1 hour ago   19 minutes   Succeeded
```

Get the application route:

```yaml
$ oc get route pipelines-vote-ui --template='http://{{.spec.host}}'
```

Note the output of the previous command. You can access the application using this route.

To rerun the last pipeline run, using the pipeline resources and service account of the previous pipeline, run:

```yaml
$ tkn pipeline start build-and-deploy --last
```

### 1.7. Adding triggers to a pipelineCopy link

Triggers enable pipelines to respond to external GitHub events, such as push events and pull requests. After you assemble and start a pipeline for the application, add the`TriggerBinding`,`TriggerTemplate`,`Trigger`, and`EventListener` resources to capture the GitHub events.

Procedure

Copy the content of the following sample`TriggerBinding` YAML file and save it:

```yaml
apiVersion: triggers.tekton.dev/v1
kind: TriggerBinding
metadata:
  name: vote-app
spec:
  params:
  - name: git-repo-url
    value: $(body.repository.url)
  - name: git-repo-name
    value: $(body.repository.name)
  - name: git-revision
    value: $(body.head_commit.id)
```

Create the`TriggerBinding` resource:

```shell-session
$ oc create -f <triggerbinding-yaml-file-name.yaml>
```

Alternatively, you can create the`TriggerBinding` resource directly from the`pipelines-tutorial` Git repository:

```shell-session
$ oc create -f https://raw.githubusercontent.com/openshift/pipelines-tutorial/pipelines-1.21/03_triggers/01_binding.yaml
```

Copy the content of the following sample`TriggerTemplate` YAML file and save it:

```yaml
apiVersion: triggers.tekton.dev/v1
kind: TriggerTemplate
metadata:
  name: vote-app
spec:
  params:
  - name: git-repo-url
    description: The git repository url
  - name: git-revision
    description: The git revision
    default: pipelines-1.21
  - name: git-repo-name
    description: The name of the deployment to be created / patched

  resourcetemplates:
  - apiVersion: tekton.dev/v1
    kind: PipelineRun
    metadata:
      generateName: build-deploy-$(tt.params.git-repo-name)-
    spec:
      taskRunTemplate:
        serviceAccountName: pipeline
      pipelineRef:
        name: build-and-deploy
      params:
      - name: deployment-name
        value: $(tt.params.git-repo-name)
      - name: git-url
        value: $(tt.params.git-repo-url)
      - name: git-revision
        value: $(tt.params.git-revision)
      - name: IMAGE
        value: image-registry.openshift-image-registry.svc:5000/pipelines-tutorial/$(tt.params.git-repo-name)
      workspaces:
      - name: shared-workspace
        volumeClaimTemplate:
          spec:
            accessModes:
              - ReadWriteOnce
            resources:
              requests:
                storage: 500Mi
```

The template specifies a volume claim template to create a persistent volume claim for defining the storage volume for the workspace. Therefore, you do not need to create a persistent volume claim to provide data storage.

Create the`TriggerTemplate` resource:

```shell-session
$ oc create -f <triggertemplate-yaml-file-name.yaml>
```

Alternatively, you can create the`TriggerTemplate` resource directly from the`pipelines-tutorial` Git repository:

```shell-session
$ oc create -f https://raw.githubusercontent.com/openshift/pipelines-tutorial/pipelines-1.21/03_triggers/02_template.yaml
```

Copy the contents of the following sample`Trigger` YAML file and save it:

```yaml
apiVersion: triggers.tekton.dev/v1
kind: Trigger
metadata:
  name: vote-trigger
spec:
  serviceAccountName: pipeline
  bindings:
    - ref: vote-app
  template:
    ref: vote-app
```

Create the`Trigger` resource:

```shell-session
$ oc create -f <trigger-yaml-file-name.yaml>
```

Alternatively, you can create the`Trigger` resource directly from the`pipelines-tutorial` Git repository:

```shell-session
$ oc create -f https://raw.githubusercontent.com/openshift/pipelines-tutorial/pipelines-1.21/03_triggers/03_trigger.yaml
```

Copy the contents of the following sample`EventListener` YAML file and save it:

```yaml
apiVersion: triggers.tekton.dev/v1
kind: EventListener
metadata:
  name: vote-app
spec:
  serviceAccountName: pipeline
  triggers:
    - triggerRef: vote-trigger
```

Alternatively, if you have not defined a trigger custom resource, add the binding and template spec to the`EventListener` YAML file, instead of referring to the name of the trigger:

```yaml
apiVersion: triggers.tekton.dev/v1
kind: EventListener
metadata:
  name: vote-app
spec:
  serviceAccountName: pipeline
  triggers:
  - bindings:
    - ref: vote-app
    template:
      ref: vote-app
```

Create the`EventListener` resource by performing the following steps:

To create an`EventListener` resource using a secure HTTPS connection:

Add a label to enable the secure HTTPS connection to the`Eventlistener` resource:

```shell-session
$ oc label namespace <ns-name> operator.tekton.dev/enable-annotation=enabled
```

Create the`EventListener` resource:

```shell-session
$ oc create -f <eventlistener-yaml-file-name.yaml>
```

Alternatively, you can create the`EvenListener` resource directly from the`pipelines-tutorial` Git repository:

```shell-session
$ oc create -f https://raw.githubusercontent.com/openshift/pipelines-tutorial/pipelines-1.21/03_triggers/04_event_listener.yaml
```

Create a route with the re-encrypt TLS termination:

```shell-session
$ oc create route reencrypt --service=<svc-name> --cert=tls.crt --key=tls.key --ca-cert=ca.crt --hostname=<hostname>
```

Alternatively, you can create a re-encrypt TLS termination YAML file to create a secured route.

Example Re-encrypt TLS Termination YAML of the Secured Route

```yaml
apiVersion: route.openshift.io/v1
kind: Route
metadata:
  name: route-passthrough-secured 
```

1

```yaml

spec:
  host: <hostname>
  to:
    kind: Service
    name: frontend 
```

2

```yaml

  tls:
    termination: reencrypt         
```

3

```yaml

    key: [as in edge termination]
    certificate: [as in edge termination]
    caCertificate: [as in edge termination]
    destinationCACertificate: |-   
```

4

```yaml

      -----BEGIN CERTIFICATE-----
      [...]
      -----END CERTIFICATE-----
```

1 2

The name of the object, which is limited to 63 characters.

3

The`termination` field is set to`reencrypt`. This is the only required`tls` field.

4

Required for re-encryption.`destinationCACertificate` specifies a CA certificate to validate the endpoint certificate, securing the connection from the router to the destination pods. If the service is using a service signing certificate, or the administrator has specified a default CA certificate for the router and the service has a certificate signed by that CA, this field can be omitted.

See`oc create route reencrypt --help` for more options.

To create an`EventListener` resource using an insecure HTTP connection:

Expose the`EventListener` service as an OpenShift Container Platform route to make it publicly accessible:

```shell-session
$ oc expose svc el-vote-app
```

1. Create the`EventListener` resource.

### 1.8. Configuring event listeners to serve multiple namespacesCopy link

Note

You can skip this section if you want to create a basic CI/CD pipeline. However, if your deployment strategy involves multiple namespaces, you can configure event listeners to serve multiple namespaces.

To increase reusability of`EvenListener` objects, cluster administrators can configure and deploy them as multi-tenant event listeners that serve multiple namespaces.

Procedure

Configure cluster-wide fetch permission for the event listener.

Set a service account name to be used in the`ClusterRoleBinding` and`EventListener` objects. For example,`el-sa`.

Example`ServiceAccount.yaml`

```yaml
apiVersion: v1
kind: ServiceAccount
metadata:
  name: el-sa
---
```

In the`rules` section of the`ClusterRole.yaml` file, set appropriate permissions for every event listener deployment to function cluster-wide.

Example`ClusterRole.yaml`

```yaml
kind: ClusterRole
apiVersion: rbac.authorization.k8s.io/v1
metadata:
  name: el-sel-clusterrole
rules:
- apiGroups: ["triggers.tekton.dev"]
  resources: ["eventlisteners", "clustertriggerbindings", "clusterinterceptors", "triggerbindings", "triggertemplates", "triggers"]
  verbs: ["get", "list", "watch"]
- apiGroups: [""]
  resources: ["configmaps", "secrets"]
  verbs: ["get", "list", "watch"]
- apiGroups: [""]
  resources: ["serviceaccounts"]
  verbs: ["impersonate"]
...
```

Configure cluster role binding with the appropriate service account name and cluster role name.

Example`ClusterRoleBinding.yaml`

```yaml
apiVersion: rbac.authorization.k8s.io/v1
kind: ClusterRoleBinding
metadata:
  name: el-mul-clusterrolebinding
subjects:
- kind: ServiceAccount
  name: el-sa
  namespace: default
roleRef:
  apiGroup: rbac.authorization.k8s.io
  kind: ClusterRole
  name: el-sel-clusterrole
...
```

In the`spec` parameter of the event listener, add the service account name, for example`el-sa`. Fill the`namespaceSelector` parameter with names of namespaces where event listener is intended to serve.

Example`EventListener.yaml`

```yaml
apiVersion: triggers.tekton.dev/v1
kind: EventListener
metadata:
  name: namespace-selector-listener
spec:
  taskRunTemplate:
    serviceAccountName: el-sa
  namespaceSelector:
    matchNames:
    - default
    - foo
...
```

Create a service account with the necessary permissions, for example`foo-trigger-sa`. Use it for role binding the triggers.

Example`ServiceAccount.yaml`

```yaml
apiVersion: v1
kind: ServiceAccount
metadata:
  name: foo-trigger-sa
  namespace: foo
...
```

Example`RoleBinding.yaml`

```yaml
apiVersion: rbac.authorization.k8s.io/v1
kind: RoleBinding
metadata:
  name: triggercr-rolebinding
  namespace: foo
subjects:
- kind: ServiceAccount
  name: foo-trigger-sa
  namespace: foo
roleRef:
  apiGroup: rbac.authorization.k8s.io
  kind: ClusterRole
  name: tekton-triggers-eventlistener-roles
...
```

Create a trigger with the appropriate trigger template, trigger binding, and service account name.

Example`Trigger.yaml`

```yaml
apiVersion: triggers.tekton.dev/v1
kind: Trigger
metadata:
  name: trigger
  namespace: foo
spec:
  taskRunTemplate:
    serviceAccountName: foo-trigger-sa
  interceptors:
    - ref:
        name: "github"
      params:
        - name: "secretRef"
          value:
            secretName: github-secret
            secretKey: secretToken
        - name: "eventTypes"
          value: ["push"]
  bindings:
    - ref: vote-app
  template:
    ref: vote-app
...
```

### 1.9. Creating webhooksCopy link

Webhooks are HTTP POST messages that are received by the event listeners whenever a configured event occurs in your repository. The event payload is then mapped to trigger bindings, and processed by trigger templates. The trigger templates eventually start one or more pipeline runs, leading to the creation and deployment of Kubernetes resources.

In this section, you will configure a webhook URL on your forked Git repositories`pipelines-vote-ui` and`pipelines-vote-api`. This URL points to the publicly accessible`EventListener` service route.

Note

Adding webhooks requires administrative privileges to the repository. If you do not have administrative access to your repository, contact your system administrator for adding webhooks.

Procedure

Get the webhook URL:

For a secure HTTPS connection:

```plaintext
$ echo "URL: $(oc  get route el-vote-app --template='https://{{.spec.host}}')"
```

For an HTTP (insecure) connection:

```plaintext
$ echo "URL: $(oc  get route el-vote-app --template='http://{{.spec.host}}')"
```

Note the URL obtained in the output.

Configure webhooks manually on the front-end repository:

On the Webhooks/Add Webhook page:

1. Enter the webhook URL from step 1 in Payload URL field
2. Select application/json for the Content type
3. Specify the secret in the Secret field
4. Ensure that the Just the push event is selected
5. Select Active
6. Click Add Webhook

### 1.10. Triggering a pipeline runCopy link

Whenever a`push` event occurs in the Git repository, the configured webhook sends an event payload to the publicly exposed`EventListener` service route. The`EventListener` service of the application processes the payload, and passes it to the relevant`TriggerBinding` and`TriggerTemplate` resource pairs. The`TriggerBinding` resource extracts the parameters, and the`TriggerTemplate` resource uses these parameters and specifies the way the resources must be created. This might rebuild and redeploy the application.

In this section, you push an empty commit to the front-end`pipelines-vote-ui` repository, which then triggers the pipeline run.

Procedure

From the terminal, clone your forked Git repository`pipelines-vote-ui`:

```shell-session
$ git clone git@github.com:<your GitHub ID>/pipelines-vote-ui.git -b pipelines-1.21
```

Push an empty commit:

```shell-session
$ git commit -m "empty-commit" --allow-empty && git push origin pipelines-1.21
```

Check if the pipeline run was triggered:

```plaintext
$ tkn pipelinerun list
```

Notice that a new pipeline run was initiated.

1. Repeat step 2 for the back-end repository`pipelines-vote-api`.

### 1.11. Enabling monitoring of event listeners for Triggers for user-defined projectsCopy link

As a cluster administrator, to gather event listener metrics for the`Triggers` service in a user-defined project and display them in the OpenShift Container Platform web console, you can create a service monitor for each event listener. On receiving an HTTP request, event listeners for the`Triggers` service return three metrics —`eventlistener_http_duration_seconds`,`eventlistener_event_count`, and`eventlistener_triggered_resources`.

Prerequisites

- You have logged in to the OpenShift Container Platform web console.
- You have installed the Red Hat OpenShift Pipelines Operator.
- You have enabled monitoring for user-defined projects.

Procedure

For each event listener, create a service monitor. For example, to view the metrics for the`github-listener` event listener in the`test` namespace, create the following service monitor:

```yaml
apiVersion: monitoring.coreos.com/v1
kind: ServiceMonitor
metadata:
  labels:
    app.kubernetes.io/managed-by: EventListener
    app.kubernetes.io/part-of: Triggers
    eventlistener: github-listener
  annotations:
    networkoperator.openshift.io/ignore-errors: ""
  name: el-monitor
  namespace: test
spec:
  endpoints:
    - interval: 10s
      port: http-metrics
  jobLabel: name
  namespaceSelector:
    matchNames:
      - test
  selector:
    matchLabels:
      app.kubernetes.io/managed-by: EventListener
      app.kubernetes.io/part-of: Triggers
      eventlistener: github-listener
...
```

Test the service monitor by sending a request to the event listener. For example, push an empty commit:

```shell-session
$ git commit -m "empty-commit" --allow-empty && git push origin main
```

1. On the OpenShift Container Platform web console, navigate to Administrator → Observe → Metrics.
2. To view a metric, search by its name. For example, to view the details of the`eventlistener_http_resources` metric for the`github-listener` event listener, search using the`eventlistener_http_resources` keyword.

### 1.12. Configuring pull request capabilities in GitHub InterceptorCopy link

With GitHub Interceptor, you can create logic that validates and filters GitHub webhooks. For example, you can validate the webhook’s origin and filter incoming events based on specified criteria. When you use GitHub Interceptor to filter event data, you can specify the event types that Interceptor can accept in a field. In Red Hat OpenShift Pipelines, you can use the following capabilities of GitHub Interceptor:

- Filter pull request events based on the files that have been changed
- Validate pull requests based on configured GitHub owners

#### 1.12.1. Filtering pull requests using GitHub InterceptorCopy link

You can filter GitHub events based on the files that have been changed for push and pull events. This helps you to execute a pipeline for only relevant changes in your Git repository. GitHub Interceptor adds a comma-delimited list of all files that have been changed and uses the CEL Interceptor to filter incoming events based on the changed files. The list of changed files is added to the`changed_files` property of the event payload in the top-level`extensions` field.

Prerequisites

- You have installed the Red Hat OpenShift Pipelines Operator.

Procedure

Perform one of the following steps:

For a public GitHub repository, set the value of the`addChangedFiles` parameter to`true` in the YAML configuration file shown below:

```yaml
apiVersion: triggers.tekton.dev/v1
kind: EventListener
metadata:
  name: github-add-changed-files-pr-listener
spec:
  triggers:
    - name: github-listener
      interceptors:
        - ref:
            name: "github"
            kind: ClusterInterceptor
            apiVersion: triggers.tekton.dev
          params:
          - name: "secretRef"
            value:
              secretName: github-secret
              secretKey: secretToken
          - name: "eventTypes"
            value: ["pull_request", "push"]
          - name: "addChangedFiles"
            value:
              enabled: true
        - ref:
            name: cel
          params:
          - name: filter
            value: extensions.changed_files.matches('controllers/')
...
```

For a private GitHub repository, set the value of the`addChangedFiles` parameter to`true` and provide the access token details,`secretName` and`secretKey` in the YAML configuration file shown below:

```yaml
apiVersion: triggers.tekton.dev/v1
kind: EventListener
metadata:
  name: github-add-changed-files-pr-listener
spec:
  triggers:
    - name: github-listener
      interceptors:
        - ref:
            name: "github"
            kind: ClusterInterceptor
            apiVersion: triggers.tekton.dev
          params:
          - name: "secretRef"
            value:
              secretName: github-secret
              secretKey: secretToken
          - name: "eventTypes"
            value: ["pull_request", "push"]
          - name: "addChangedFiles"
            value:
              enabled: true
              personalAccessToken:
                secretName: github-pat
                secretKey: token
        - ref:
            name: cel
          params:
          - name: filter
            value: extensions.changed_files.matches('controllers/')
...
```

#### 1.12.2. Validating pull requests using GitHub InterceptorsCopy link

You can use GitHub Interceptor to validate the processing of pull requests based on the GitHub owners configured for a repository. This validation helps you to prevent unnecessary execution of a`PipelineRun` or`TaskRun` object. GitHub Interceptor processes a pull request only if the user name is listed as an owner or if a configurable comment is issued by an owner of the repository. For example, when you comment`/ok-to-test` on a pull request as an owner, a`PipelineRun` or`TaskRun` is triggered.

Note

Owners are configured in an`OWNERS` file at the root of the repository.

Prerequisites

- Save the configuration file.
- You have installed the Red Hat OpenShift Pipelines Operator.

Procedure

Perform one of the following steps:

For a public GitHub repository, set the value of the`githubOwners` parameter to`true` in the YAML configuration file shown below:

```yaml
apiVersion: triggers.tekton.dev/v1
kind: EventListener
metadata:
  name: github-owners-listener
spec:
  triggers:
    - name: github-listener
      interceptors:
        - ref:
            name: "github"
            kind: ClusterInterceptor
            apiVersion: triggers.tekton.dev
          params:
            - name: "secretRef"
              value:
                secretName: github-secret
                secretKey: secretToken
            - name: "eventTypes"
              value: ["pull_request", "issue_comment"]
            - name: "githubOwners"
              value:
                enabled: true
                checkType: none
...
```

For a private GitHub repository, set the value of the`githubOwners` parameter to`true` and provide the access token details,`secretName` and`secretKey` in the YAML configuration file shown below:

```yaml
apiVersion: triggers.tekton.dev/v1
kind: EventListener
metadata:
  name: github-owners-listener
spec:
  triggers:
    - name: github-listener
      interceptors:
        - ref:
            name: "github"
            kind: ClusterInterceptor
            apiVersion: triggers.tekton.dev
          params:
            - name: "secretRef"
              value:
                secretName: github-secret
                secretKey: secretToken
            - name: "eventTypes"
              value: ["pull_request", "issue_comment"]
            - name: "githubOwners"
              value:
                enabled: true
                personalAccessToken:
                  secretName: github-token
                  secretKey: secretToken
                checkType: all
...
```

Note

The`checkType` parameter is used to specify the GitHub owners who need authentication. You can set its value to`orgMembers`,`repoMembers`, or`all`.

## Chapter 2. Working with Red Hat OpenShift Pipelines in the web consoleCopy link

You can use the Administrator or Developer perspective to create and modify`Pipeline`,`PipelineRun`, and`Repository` objects from the Pipelines page in the OpenShift Container Platform web console. You can also use the +Add page in the Developer perspective of the web console to create CI/CD pipelines for your software delivery process.

### 2.1. Working with Red Hat OpenShift Pipelines in the Developer perspectiveCopy link

In the Developer perspective, you can access the following options for creating pipelines from the +Add page:

- Use the +Add → Pipelines → Pipeline builder option to create customized pipelines for your application.
- Use the +Add → From Git option to create pipelines using pipeline templates and resources while creating an application.

After you create the pipelines for your application, you can view and visually interact with the deployed pipelines in the Pipelines view. You can also use the Topology view to interact with the pipelines created using the From Git option. You must apply custom labels to pipelines created using the Pipeline builder to see them in the Topology view.

#### Prerequisites

- You have access to an OpenShift Container Platform cluster and have switched to [the Developer perspective](https://docs.openshift.com/container-platform/latest/web_console/web-console-overview.html#about-developer-perspective_web-console-overview).
- You have the [OpenShift Pipelines Operator installed](https://docs.redhat.com/en/documentation/red_hat_openshift_pipelines/1.21/html-single/installing_and_configuring/#installing-pipelines) in your cluster.
- You are a cluster administrator or a user with create and edit permissions.
- You have created a project.

#### 2.1.1. Constructing pipelines using the Pipeline builderCopy link

In the Developer perspective of the console, you can use the +Add → Pipeline → Pipeline builder option to:

- Configure pipelines using either the Pipeline builder or the YAML view.
- Construct a pipeline flow using existing tasks. When you install the OpenShift Pipelines Operator, it adds reusable pipeline tasks to your cluster that can be used with the cluster resolver.
- Specify the type of resources required for the pipeline run, and if required, add additional parameters to the pipeline.
- Reference these pipeline resources in each of the tasks in the pipeline as input and output resources.
- If required, reference any additional parameters added to the pipeline in the task. The parameters for a task are prepopulated based on the specifications of the task.
- Use the Operator-installed, reusable snippets and samples to create detailed pipelines.
- Search and add tasks from your configured local Tekton Hub instance.

Important

In the developer perspective, you can create a customized pipeline using your own set of curated tasks. To search, install, and upgrade your tasks directly from the developer console, your cluster administrator needs to install and deploy a local Tekton Hub instance and link that hub to the OpenShift Container Platform cluster. For more details, see Using Tekton Hub with OpenShift Pipelines in the Additional resources section. If you do not deploy any local Tekton Hub instance, by default, you can only access namespace tasks and public Tekton Hub tasks.

Procedure

Configure the pipeline using either the Pipeline builder view or the YAML view.

Note

The Pipeline builder view supports a limited number of fields whereas the YAML view supports all available fields. Optionally, you can also use the Operator-installed, reusable snippets and samples to create detailed pipelines.

Figure 2.1. YAML view

Configure your pipeline by using Pipeline builder:

In the Tasks section:

Click Add or Install and add. In this example, use the s2i-Node.js task.

Note

The search list contains all the Tekton Hub tasks and tasks available in the cluster. Also, if a task is already installed it will show Add to add the task whereas it will show Install and add to install and add the task. It will show Update and add when you add the same task with an updated version.

To add sequential tasks to the pipeline:

Click Add or Install and add.

Figure 2.2. Pipeline builder

- Click the plus icon to the right or left of the task → click Add task.
- Search for a task using the quick search field and select the required task from the displayed list.

To add a final task:

- Click the Add finally task → Click Add task.
- Search for a task using the quick search field and select the required task from the displayed list.
- Click Add or Install and add.

In the Resources section, click Add Resources to specify the name and type of resources for the pipeline run. These resources are then used by the tasks in the pipeline as inputs and outputs. For this example:

Add an output resource. In the Name field, enter`Img`, and then from the Resource Type drop-down list, select Image.

Note

A red icon is displayed next to the task if a resource is missing.

1. Add an input resource. In the Name field, enter`Source`, and then from the Resource Type drop-down list, select Git.

In the Tasks section, click the s2i-Node.js task to see the side panel with details for the task. In the task side panel, specify the resources and parameters for the s2i-Node.js task:

1. If required, in the Parameters section, add more parameters to the default ones, by using the $(params.) syntax.
2. In the Image section, enter`Img` as specified in the Resources section.
3. Select a workspace from the source drop-down under Workspaces section.

#### 2.1.2. Creating OpenShift Pipelines along with applicationsCopy link

To create pipelines along with applications, use the From Git option in the Add+ view of the Developer perspective. You can view all of your available pipelines and select the pipelines you want to use to create applications while importing your code or deploying an image.

The Tekton Hub Integration is enabled by default and you can see tasks from the Tekton Hub that are supported by your cluster. Administrators can opt out of the Tekton Hub Integration and the Tekton Hub tasks will no longer be displayed. You can also check whether a webhook URL exists for a generated pipeline. Default webhooks are added for the pipelines that are created using the +Add flow and the URL is visible in the side panel of the selected resources in the Topology view.

#### 2.1.3. Adding a GitHub repository containing pipelinesCopy link

In the Developer perspective, you can add your GitHub repository containing pipelines to the OpenShift Container Platform cluster. This allows you to run pipelines and tasks from your GitHub repository on the cluster when relevant Git events, such as push or pull requests, are triggered.

Note

You can add both public and private GitHub repositories.

Prerequisites

- The Workspaces section lists the workspaces you created earlier. Use the respective drop-down to specify the volume source for your workspace. You have the following options: Empty Directory, Config Map, Secret, PersistentVolumeClaim, or VolumeClaimTemplate.
- Ensure that your cluster administrator has configured the required GitHub applications in the administrator perspective.

Procedure

Click Show configuration options. By default, you see only one option Setup a webhook. If you have a GitHub application configured, you see two options:

- Use GitHub App: Select this option to install your GitHub application in your repository.
- Setup a webhook: Select this option to add a webhook to your GitHub application.

Set up a webhook using one of the following options in the Secret section:

Setup a webhook using Git access token:

Click Generate corresponding to the Webhook secret field to generate a new webhook secret.

Note

You can click the link below the Git access token field if you do not have a personal access token and want to create a new one.

1. Enter your personal access token.

Setup a webhook using Git access token secret:

Select a secret in your namespace from the dropdown list. Depending on the secret you selected, a webhook secret is automatically generated.

Add the webhook secret details to your GitHub repository:

1. Copy the webhook URL and navigate to your GitHub repository settings.
2. Click Webhooks → Add webhook.
3. Copy the Webhook URL from the developer console and paste it in the Payload URL field of the GitHub repository settings.
4. Select the Content type.
5. Copy the Webhook secret from the developer console and paste it in the Secret field of the GitHub repository settings.
6. Select one of the SSL/TLS verification options.
7. Select the events to trigger this webhook.
8. Click Add webhook.

Note

When importing an application using Import from Git and the Git repository has a`.tekton` directory, you can configure`pipelines-as-code` for your application.

#### 2.1.4. Interacting with pipelines using the Developer perspectiveCopy link

The Pipelines view in the Developer perspective lists all the pipelines in a project, along with the following details:

- View the details of the repository you just created.
- The namespace in which the pipeline was created
- The last pipeline run
- The status of the tasks in the pipeline run
- The status of the pipeline run
- The creation time of the last pipeline run

Procedure

Click the required pipeline to see the Pipeline details page.

By default, the Details tab displays a visual representation of all the`serial` tasks,`parallel` tasks,`finally` tasks, and`when` expressions in the pipeline. The tasks and the`finally` tasks are listed in the lower right portion of the page.

To view the task details, click the listed Tasks and Finally tasks. In addition, you can do the following:

Hover over the tasks and see the task details.

Figure 2.3. Pipeline details

- Use the zoom in, zoom out, fit to screen, and reset view features using the standard icons displayed in the lower left corner of the Pipeline details visualization.
- Change the zoom factor of the pipeline visualization using the mouse wheel.

Optional: On the Pipeline details page, click the Metrics tab to see the following information about pipelines:

Task Run Duration

You can use this information to improve the pipeline workflow and eliminate issues early in the pipeline lifecycle.

- Pipeline Success Ratio
- Number of Pipeline Runs
- Pipeline Run Duration

Optional: Click the Pipeline Runs tab to see the completed, running, or failed runs for the pipeline.

The Pipeline Runs tab provides details about the pipeline run, the status of the task, and a link to debug failed pipeline runs. Use the Options menu to stop a running pipeline, to rerun a pipeline using the same parameters and resources as that of the previous pipeline execution, or to delete a pipeline run.

Click the required pipeline run to see the Pipeline Run details page. By default, the Details tab displays a visual representation of all the serial tasks, parallel tasks,`finally` tasks, and when expressions in the pipeline run. The results for successful runs are displayed under the Pipeline Run results pane at the bottom of the page. Additionally, you would only be able to see tasks from Tekton Hub which are supported by the cluster. While looking at a task, you can click the link beside it to jump to the task documentation.

Note

The Details section of the Pipeline Run Details page displays a Log Snippet of the failed pipeline run. Log Snippet provides a general error message and a snippet of the log. A link to the Logs section provides quick access to the details about the failed run.

On the Pipeline Run details page, click the Task Runs tab to see the completed, running, and failed runs for the task.

The Task Runs tab provides information about the task run along with the links to its task and pod, and also the status and duration of the task run. Use the Options menu to delete a task run.

Note

The TaskRuns list page features a Manage columns button, which you can also use to add a Duration column.

Click the required task run to see the Task Run details page. The results for successful runs are displayed under the Task Run results pane at the bottom of the page.

Note

The Details section of the Task Run details page displays a Log Snippet of the failed task run. Log Snippet provides a general error message and a snippet of the log. A link to the Logs section provides quick access to the details about the failed task run.

#### 2.1.5. Starting pipelines from Pipelines viewCopy link

After you create a pipeline, you need to start it to execute the included tasks in the defined sequence. You can start a pipeline from the Pipelines view, the Pipeline Details page, or the Topology view.

Procedure

To start a pipeline by using the Pipelines view:

The Start Pipeline dialog box displays the Git Resources and the Image Resources based on the pipeline definition.

Note

For pipelines created using the From Git option, the Start Pipeline dialog box also displays an`APP_NAME` field in the Parameters section, and all the fields in the dialog box are prepopulated by the pipeline template.

1. If you have resources in your namespace, the Git Resources and the Image Resources fields are prepopulated with those resources. If required, use the drop-downs to select or create the required resources and customize the pipeline run instance.

Optional: Modify the Advanced Options to add the credentials that authenticate the specified private Git server or the image registry.

In the Create Source Secret section, specify the following:

Select the Authentication Type and provide the credentials:

For the Authentication Type`Image Registry Credentials`, specify the Registry Server Address that you want to authenticate, and provide your credentials in the Username, Password, and Email fields.

Select Add Credentials if you want to specify an additional Registry Server Address.

For the Authentication Type`SSH Keys`, specify the value of the SSH Private Key field.

Note

For Basic HTTP authentication and SSH authentication, you can use annotations such as:

- [https://github.com](https://github.com/)`tekton.dev/git-0: `
- [https://gitlab.com](https://gitlab.com/)`tekton.dev/git-1: `.

You can add multiple secrets based upon the number of resources in your pipeline.

The PipelineRun details page displays the pipeline being executed. After the pipeline starts, the tasks and steps within each task are executed. You can:

Click the Events tab to see the stream of events generated by a pipeline run.

You can use the Task Runs, Logs, and Events tabs to assist in debugging a failed pipeline run or a failed task run.

Figure 2.4. Pipeline run details

- Use the zoom in, zoom out, fit to screen, and reset view features using the standard icons, which are in the lower left corner of the PipelineRun details page visualization.
- Change the zoom factor of the pipelinerun visualization using the mouse wheel. At specific zoom factors, the background color of the tasks changes to indicate the error or warning status.
- Hover over the tasks to see the details, such as the time taken to execute each step, task name, and task status.
- Hover over the tasks badge to see the total number of tasks and tasks completed.
- click a task to see the logs for each step in the task.
- Click the Logs tab to see the logs relating to the execution sequence of the tasks. You can also expand the pane and download the logs individually or in bulk, by using the relevant button.

#### 2.1.6. Starting pipelines from Topology viewCopy link

For pipelines created using the From Git option, you can use the Topology view to interact with pipelines after you start them:

Note

To see the pipelines created using Pipeline builder in the Topology view, customize the pipeline labels to link the pipeline with the application workload.

Procedure

In Pipeline Runs, click Start Last Run to start a new pipeline run with the same parameters and resources as the previous one. This option is disabled if a pipeline run has not been initiated. You can also start a pipeline run when you create it.

Figure 2.5. Pipelines in Topology view

1. Click Topology in the left navigation panel.
2. Click the application to display Pipeline Runs in the side panel.

In the Topology page, hover to the left of the application to see the status of its pipeline run. After a pipeline is added, a lower left icon indicates that there is an associated pipeline.

#### 2.1.7. Interacting with pipelines from Topology viewCopy link

The side panel of the application node in the Topology page displays the status of a pipeline run and you can interact with it.

- If a pipeline run does not start automatically, the side panel displays a message that the pipeline cannot be automatically started, hence it would need to be started manually.
- If a pipeline is created but the user has not started the pipeline, its status is not started. When the user clicks the Not started status icon, the start dialog box opens in the Topology view.
- If the pipeline has no build or build config, the Builds section is not visible. If there is a pipeline and build config, the Builds section is visible.
- The side panel displays a Log Snippet when a pipeline run fails on a specific task run. You can view the Log Snippet in the Pipeline Runs section, under the Resources tab. It provides a general error message and a snippet of the log. A link to the Logs section provides quick access to the details about the failed run.

#### 2.1.8. Editing pipelinesCopy link

You can edit the pipelines in your cluster by using the Developer perspective of the web console:

Procedure

On the Pipeline builder page, you can perform the following tasks:

- Add additional tasks, parameters, or resources to the pipeline.
- Click the task you want to modify to see the task details in the side panel and modify the required task details, such as the display name, parameters, and resources.
- Alternatively, to delete the task, click the task, and in the side panel, click Actions and select Remove Task.

#### 2.1.9. Deleting pipelinesCopy link

You can delete the pipelines in your cluster by using the Developer perspective of the web console.

Procedure

1. Click Save to save the modified pipeline.
2. In the Pipelines view of the Developer perspective, click the Options menu adjoining a Pipeline, and select Delete Pipeline.
3. In the Delete Pipeline confirmation prompt, click Delete to confirm the deletion.

### 2.3. Creating pipeline templates in the Administrator perspectiveCopy link

As a cluster administrator, you can create pipeline templates that developers can reuse when they create a pipeline on the cluster.

Prerequisites

- You have access to an OpenShift Container Platform cluster with cluster administrator permissions, and have switched to the Administrator perspective.
- You have installed the OpenShift Pipelines Operator in your cluster.

Procedure

Add the YAML for your pipeline template. The template must include the following information:

```yaml
apiVersion: tekton.dev/v1
kind: Pipeline
metadata:
# ...
  namespace: openshift 
```

1

```yaml

  labels:
    pipeline.openshift.io/runtime: <runtime> 
```

2

```yaml

    pipeline.openshift.io/type: <pipeline-type> 
```

3

```yaml

# ...
```

1

The template must be created in the`openshift` namespace.

2

The template must contain the`pipeline.openshift.io/runtime` label. The accepted runtime values for this label are`nodejs`,`golang`,`dotnet`,`java`,`php`,`ruby`,`perl`,`python`,`nginx`, and`httpd`.

3

The template must contain the`pipeline.openshift.io/type:` label. The accepted type values for this label are`openshift`,`knative`, and`kubernetes`.

1. Navigate to the Pipelines page to view existing pipeline templates.
2. Click the icon to go to the Import YAML page.
3. Click Create. After the pipeline has been created, you are taken to the Pipeline details page, where you can view information about or edit your pipeline.

### 2.4. Pipeline execution statistics in the web consoleCopy link

You can view statistics related to execution of pipelines in the web console.

To view the statistic information, you must complete the following steps:

- Install Tekton Results. For more information about installing Tekton Results, see Using Tekton Results for OpenShift Pipelines observability in the Additional resources section.
- Enable the OpenShift Pipelines console plugin.

Statistic information is available for all pipelines together and for each individual pipeline.

#### 2.4.1. Enabling the OpenShift Pipelines console pluginCopy link

To view the statistic information, you must first enable the OpenShift Pipelines console plugin.

Prerequisites

- You installed the Red Hat OpenShift Pipelines Operator in your cluster.
- You are logged on to the web console with cluster administrator permissions.

Important

The OpenShift Pipelines console plugin requires OpenShift Container Platform version 4.15 or a later version.

Procedure

1. In the Administrator perspective of the web console, select Operators → Installed Operators.
2. Click Red Hat OpenShift Pipelines in the table of Operators.
3. In the right pane on the screen, check the status label under Console plugin. The label is either Enabled or Disabled.
4. If the label is Disabled, click this label. In the window that displays, select Enable and then click Save.

#### 2.4.2. Viewing the statistics for all pipelines togetherCopy link

You can view consolidated statistic information related to all pipelines on the system.

Prerequisites

- You installed the Red Hat OpenShift Pipelines Operator in your cluster.
- You installed the OpenShift Pipelines web console plugin.

Procedure

In the Administrator perspective of the web console, select Pipelines → Overview.

A statistics overview displays. This overview includes the following information: A graph reflecting the number and status of pipeline runs over a time period The total, average, and maximum durations of pipeline execution over the same period. ** The total number of pipeline runs over the same period.

A table of pipelines also displays. This table lists all pipelines that were run in the time period, showing their duration and success rate.

Optional: Change the settings of the statistics display as necessary:

- Project: The project or namespace to display statistics for.
- Time range: The time period to display statistics for.
- Refresh interval: How often Red Hat OpenShift Pipelines must update the data in the window while you are viewing it.

#### 2.4.3. Viewing the statistics for a specific pipelineCopy link

You can view statistic information related to a particular pipeline.

Prerequisites

- You installed the Red Hat OpenShift Pipelines Operator in your cluster.
- You installed the OpenShift Pipelines web console plugin.

Procedure

Click the Metrics tab.

A statistics overview displays. This overview includes the following information: A graph reflecting the number and status of pipeline runs over a time period The total, average, and maximum durations of pipeline execution over the same period. ** The total number of pipeline runs over the same period.

Optional: Change the settings of the statistics display as necessary:

- Project: The project or namespace to display statistics for.
- Time range: The time period to display statistics for.
- Refresh interval: How often Red Hat OpenShift Pipelines must update the data in the window while you are viewing it.

## Chapter 3. Specifying remote pipelines, tasks, and step actions using resolversCopy link

Pipelines and tasks are reusable blocks for your CI/CD processes. You can reuse pipelines or tasks that you previously developed, or that were developed by others, without having to copy and paste their definitions. These pipelines or tasks can be available from several types of sources, from other namespaces on your cluster to public catalogs.

In a pipeline run resource, you can specify a pipeline from an existing source. In a pipeline resource or a task run resource, you can specify a task from an existing source.

Step actions, defined in`StepAction` custom resources (CRs), are reusable actions that a single step within a task completes. When specifying a step, you can reference a`StepAction` definition from an existing source.

In these cases, the resolvers in Red Hat OpenShift Pipelines retrieve the pipeline, task, or`StepAction` definition from the specified source at run time.

The following resolvers are available in a default installation of Red Hat OpenShift Pipelines:

Hub resolver Retrieves a task, pipeline, or`StepAction` definition from the Pipelines Catalog available on Artifact Hub or Tekton Hub. Bundles resolver Retrieves a task, pipeline, or`StepAction` definition from a Tekton bundle, which is an OCI image available from any OCI repository, such as an OpenShift container repository. Git resolver Retrieves a task, pipeline, or`StepAction` definition from a Git repository. You must specify the repository, the branch, and the path. HTTP resolver Retrieves a task, pipeline, or`StepAction` definition from a remote HTTP or HTTPS URL. You must specify the URL for authentication. Cluster resolver Retrieves a task, pipeline, or`StepAction` definition that is already created on the same OpenShift Container Platform cluster in a specific namespace.

An OpenShift Pipelines installation includes a set of standard tasks that you can use in your pipelines. These tasks are located in the OpenShift Pipelines installation namespace, which is normally the`openshift-pipelines` namespace. You can use the cluster resolver to access the tasks.

OpenShift Pipelines also provides a standard`StepAction` definition. You can use the cluster resolver to access this definition.

### 3.1. Specifying a remote pipeline, task, or step action from a Tekton catalogCopy link

You can use the hub resolver to specify a remote pipeline, task, or`StepAction` definition that is defined either in a public Tekton catalog of [Artifact Hub](https://artifacthub.io/) or in an instance of Tekton Hub.

Note

The public instance of Tekton Hub (`hub.tekton.dev`) is deprecated and will be removed in a future release. Use [Artifact Hub](https://artifacthub.io/) as an alternative for Tekton`Pipeline` and`Task` resources:

- [Tekton Catalog Tasks](https://artifacthub.io/packages/search?repo=tekton-catalog-tasks)
- [Tekton Catalog Pipelines](https://artifacthub.io/packages/search?repo=tekton-catalog-pipelines)

Artifact Hub is open source and supports self-hosting, providing greater flexibility for managing your Tekton`Pipeline` and`Task` resources.

#### 3.1.1. Configuring the hub resolverCopy link

You can change the default hub for pulling a resource, and the default catalog settings, by configuring the hub resolver.

Procedure

To edit the`TektonConfig` custom resource, enter the following command:

```shell-session
$ oc edit TektonConfig config
```

In the`TektonConfig` custom resource, edit the`pipeline.hub-resolver-config` spec:

```yaml
apiVersion: operator.tekton.dev/v1alpha1
kind: TektonConfig
metadata:
  name: config
spec:
  pipeline:
    hub-resolver-config:
      default-tekton-hub-catalog: Tekton 
```

1

```yaml

      default-artifact-hub-task-catalog: tekton-catalog-tasks 
```

2

```yaml

      default-artifact-hub-pipeline-catalog: tekton-catalog-pipelines 
```

3

```yaml

      defailt-kind: pipeline 
```

4

```yaml

      default-type: tekton 
```

5

```yaml

      tekton-hub-api: "https://my-custom-tekton-hub.example.com" 
```

6

```yaml

      artifact-hub-api: "https://my-custom-artifact-hub.example.com" 
```

7

1

The default Tekton Hub catalog for pulling a resource.

2

The default Artifact Hub catalog for pulling a task resource.

3

The default Artifact Hub catalog for pulling a pipeline resource.

4

The default object kind for references.

5

The default hub for pulling a resource, either`artifact` for Artifact Hub or`tekton` for Tekton Hub.

6

The Tekton Hub API used, if the`default-type` option is set to`tekton`.

7

Optional: The Artifact Hub API used, if the`default-type` option is set to`artifact`.

Important

If you set the`default-type` option to`tekton`, you must configure your own instance of the Tekton Hub by setting the`tekton-hub-api` value.

If you set the`default-type` option to`artifact` then the resolver uses the public hub API at [https://artifacthub.io/](https://artifacthub.io/) by default. You can configure your own Artifact Hub API by setting the`artifact-hub-api` value.

#### 3.1.2. Specifying a remote pipeline, task, or step action using the hub resolverCopy link

When creating a pipeline run, you can specify a remote pipeline from Artifact Hub or Tekton Hub. When creating a pipeline or a task run, you can specify a remote task from Artifact Hub or Tekton Hub. When creating a step within a task, you can reference a remote`StepAction` definition from Artifact Hub or Tekton Hub.

Procedure

To specify a remote pipeline, task, or`StepAction` definition from Artifact Hub or Tekton Hub, use the following reference format in the`pipelineRef`,`taskRef`, or`step.ref` spec:

```yaml
# ...
  resolver: hub
  params:
  - name: catalog
    value: <catalog>
  - name: type
    value: <catalog_type>
  - name: kind
    value: [pipeline|task]
  - name: name
    value: <resource_name>
  - name: version
    value: <resource_version>
# ...
```

Expand

`catalog`

The catalog for pulling the resource.

Default:`tekton-catalog-tasks`(for the`task` kind);`tekton-catalog-pipelines`(for the`pipeline` kind).

`type`

The type of the catalog for pulling the resource. Either`artifact` for Artifact Hub or`tekton` for Tekton Hub.

Default:`artifact`

`kind`

Either`task` or`pipeline`.

Default:`task`

`name`

The name of the task or pipeline to fetch from the hub.

`golang-build`

`version`

The version of the task or pipeline to fetch from the hub. You must use quotes (`"`) around the number.

`"0.5.0"`

| Table 3.1. Supported parameters for the hub resolver | Parameter | Description | Example value |
| --- | --- | --- | --- |

Show more

If the pipeline or task requires additional parameters, specify values for these parameters in the`params` section of the specification of the pipeline, pipeline run, or task run. The`params` section of the`pipelineRef` or`taskRef` specification must contain only the parameters that the resolver supports.

Examples

The following example pipeline run references a remote pipeline from a catalog:

```yaml
apiVersion: tekton.dev/v1
kind: PipelineRun
metadata:
  name: hub-pipeline-reference-demo
spec:
  pipelineRef:
    resolver: hub
    params:
    - name: catalog
      value: tekton-catalog-pipelines
    - name: type
      value: artifact
    - name: kind
      value: pipeline
    - name: name
      value: example-pipeline
    - name: version
      value: "0.1"
  params:
  - name: sample-pipeline-parameter
    value: test
```

The following example pipeline references a remote task from a catalog:

```yaml
apiVersion: tekton.dev/v1
kind: Pipeline
metadata:
  name: pipeline-with-hub-task-reference-demo
spec:
  tasks:
  - name: "cluster-task-reference-demo"
    taskRef:
      resolver: hub
      params:
      - name: catalog
        value: tekton-catalog-tasks
      - name: type
        value: artifact
      - name: kind
        value: task
      - name: name
        value: example-task
      - name: version
        value: "0.6"
    params:
    - name: sample-task-parameter
      value: test
```

The following example task run references a remote task from a catalog:

```yaml
apiVersion: tekton.dev/v1
kind: TaskRun
metadata:
  name: hub-task-reference-demo
spec:
  taskRef:
    resolver: hub
    params:
    - name: catalog
      value: tekton-catalog-tasks
    - name: type
      value: artifact
    - name: kind
      value: task
    - name: name
      value: example-task
    - name: version
      value: "0.6"
  params:
  - name: sample-task-parameter
    value: test
```

The following example task includes a step that references a`StepAction` definition from a catalog:

```yaml
apiVersion: tekton.dev/v1
kind: Task
metadata:
  name: hub-stepaction-reference-demo
spec:
  steps:
  - name: example-step
    ref:
    - resolver: hub
    - params:
      - name: catalog
        value: tekton-catalog-stepactions
      - name: type
        value: artifact
      - name: kind
        value: StepAction
      - name: name
        value: example-stepaction
      - name: version
        value: "0.6"
    params:
    - name: sample-stepaction-parameter
      value: test
```

### 3.2. Specifying a remote pipeline, task, or step action from a Tekton bundleCopy link

You can use the bundles resolver to specify a remote pipeline, task, or`StepAction` definition from a Tekton bundle. A Tekton bundle is an OCI image available from any OCI repository, such as an OpenShift container repository.

#### 3.2.1. Configuring the bundles resolverCopy link

You can change the default service account name and the default kind for pulling resources from a Tekton bundle by configuring the bundles resolver.

Procedure

To edit the`TektonConfig` custom resource, enter the following command:

```shell-session
$ oc edit TektonConfig config
```

In the`TektonConfig` custom resource, edit the`pipeline.bundles-resolver-config` spec:

```yaml
apiVersion: operator.tekton.dev/v1alpha1
kind: TektonConfig
metadata:
  name: config
spec:
  pipeline:
    bundles-resolver-config:
      default-service-account: pipelines 
```

1

```yaml

      default-kind: task 
```

2

1

The default service account name to use for bundle requests.

2

The default layer kind in the bundle image.

#### 3.2.2. Specifying a remote pipeline, task, or step action using the bundles resolverCopy link

When creating a pipeline run, you can specify a remote pipeline from a Tekton bundle. When creating a pipeline or a task run, you can specify a remote task from a Tekton bundle. When creating a step within a task, you can reference a remote`StepAction` definition from a Tekton bundle.

Procedure

To specify a remote pipeline, task, or`StepAction` definition from a Tekton bundle, use the following reference format in the`pipelineRef`,`taskRef`, or`step.ref` spec:

```yaml
# ...
  resolver: bundles
  params:
  - name: bundle
    value: <fully_qualified_image_name>
  - name: name
    value: <resource_name>
  - name: kind
    value: [pipeline|task]
# ...
```

Expand

`serviceAccount`

The name of the service account to use when constructing registry credentials.

`default`

`bundle`

The bundle URL pointing at the image to fetch.

`gcr.io/tekton-releases/catalog/upstream/golang-build:0.1`

`name`

The name of the resource to pull out of the bundle.

`golang-build`

`kind`

The kind of the resource to pull out of the bundle.

`task`

| Table 3.2. Supported parameters for the bundles resolver | Parameter | Description | Example value |
| --- | --- | --- | --- |

Show more

If the pipeline or task requires additional parameters, specify values for these parameters in the`params` section of the specification of the pipeline, pipeline run, or task run. The`params` section of the`pipelineRef` or`taskRef` specification must contain only the parameters that the resolver supports.

Examples

The following example pipeline run references a remote pipeline from a Tekton bundle:

```yaml
apiVersion: tekton.dev/v1
kind: PipelineRun
metadata:
  name: bundle-pipeline-reference-demo
spec:
  pipelineRef:
    resolver: bundles
    params:
    - name: bundle
      value: registry.example.com:5000/simple/pipeline:latest
    - name: name
      value: hello-pipeline
    - name: kind
      value: pipeline
  params:
  - name: sample-pipeline-parameter
    value: test
  - name: username
    value: "pipelines"
```

The following example pipeline references a remote task from a Tekton bundle:

```yaml
apiVersion: tekton.dev/v1
kind: Pipeline
metadata:
  name: pipeline-with-bundle-task-reference-demo
spec:
  tasks:
  - name: "bundle-task-demo"
    taskRef:
      resolver: bundles
      params:
      - name: bundle
        value: registry.example.com:5000/advanced/task:latest
      - name: name
        value: hello-world
      - name: kind
        value: task
    params:
    - name: sample-task-parameter
      value: test
```

The following example task run references a remote task from a Tekton bundle:

```yaml
apiVersion: tekton.dev/v1
kind: TaskRun
metadata:
  name: bundle-task-reference-demo
spec:
  taskRef:
    resolver: bundles
    params:
    - name: bundle
      value: registry.example.com:5000/simple/new_task:latest
    - name: name
      value: hello-world
    - name: kind
      value: task
  params:
  - name: sample-task-parameter
    value: test
```

The following example task includes a step that references a`StepAction` definition from a Tekton bundle:

```yaml
apiVersion: tekton.dev/v1
kind: Task
metadata:
  name: bundle-stepaction-reference-demo
spec:
  steps:
  - name: example-step
    ref:
      resolver: bundles
      params:
      - name: bundle
        value: registry.example.com:5000/simple/new_task:latest
      - name: name
        value: hello-world-action
      - name: kind
        value: StepAction
    params:
    - name: sample-stepaction-parameter
      value: test
```

### 3.3. Specifying a remote pipeline, task, or step action with anonymous Git cloningCopy link

You can use the Git resolver to access a remote pipeline, task, or`StepAction` definition from a Git repository. The repository must include a YAML file that defines the pipeline or task. For anonymous access, you can clone repositories with the resolver without needing authentication credentials.

#### 3.3.1. Configuring the Git resolver for anonymous Git cloningCopy link

If you want to use anonymous Git cloning, you can configure the default Git revision, fetch timeout, and default repository URL for pulling remote pipelines and tasks from a Git repository.

Procedure

To edit the`TektonConfig` custom resource, enter the following command:

```shell-session
$ oc edit TektonConfig config
```

In the`TektonConfig` custom resource, edit the`pipeline.git-resolver-config` spec:

```yaml
apiVersion: operator.tekton.dev/v1alpha1
kind: TektonConfig
metadata:
  name: config
spec:
  pipeline:
    git-resolver-config:
      default-revision: main 
```

1

```yaml

      fetch-timeout: 1m 
```

2

```yaml

      default-url: https://github.com/tektoncd/catalog.git 
```

3

1

The default Git revision to use if none is specified.

2

The maximum time any single Git clone resolution might take, for example,`1m`,`2s`,`700ms`. Red Hat OpenShift Pipelines also enforces a global maximum timeout of 1 minute on all resolution requests.

3

The default Git repository URL for anonymous cloning if none is specified.

#### 3.3.2. Specifying a remote pipeline, task, or step action by using the Git resolver for anonymous cloningCopy link

When creating a pipeline run, you can specify a remote pipeline from a Git repository by using anonymous cloning. When creating a pipeline or a task run, you can specify a remote task from a Git repository. When creating a step within a task, you can reference a remote`StepAction` definition from a Git repository.

Procedure

To specify a remote pipeline, task, or`StepAction` definition from a Git repository, use the following reference format in the`pipelineRef`,`taskRef`, or`step.ref` spec:

```yaml
# ...
  resolver: git
  params:
  - name: url
    value: <git_repository_url>
  - name: revision
    value: <branch_name>
  - name: pathInRepo
    value: <path_in_repository>
# ...
```

Expand

`url`

The URL of the repository, when using anonymous cloning.

`https://github.com/tektoncd/catalog.git`

`revision`

The Git revision in the repository. You can specify a branch name, a tag name, or a commit SHA hash.

`aeb957601cf41c012be462827053a21a420befca``main``v0.38.2`

`pathInRepo`

The path name of the YAML file in the repository.

`task/golang-build/0.3/golang-build.yaml`

| Table 3.3. Supported parameters for the Git resolver | Parameter | Description | Example value |
| --- | --- | --- | --- |

Show more

Note

To clone and fetch the repository anonymously, use the`url` parameter. Do not specify the`url` parameter and the`repo` parameter together.

If the pipeline or task requires additional parameters, provide these parameters in`params`.

Examples

The following example pipeline run references a remote pipeline from a Git repository:

```yaml
apiVersion: tekton.dev/v1
kind: PipelineRun
metadata:
  name: git-pipeline-reference-demo
spec:
  pipelineRef:
    resolver: git
    params:
      - name: url
        value: https://github.com/tektoncd/catalog.git
      - name: revision
        value: main
      - name: pathInRepo
        value: pipeline/simple/0.1/simple.yaml
  params:
    - name: name
      value: "testPipelineRun"
    - name: sample-pipeline-parameter
      value: test
```

The following example pipeline references a remote task from a Git repository:

```yaml
apiVersion: tekton.dev/v1
kind: Pipeline
metadata:
  name: pipeline-with-git-task-reference-demo
spec:
  tasks:
    - name: "git-task-reference-demo"
      taskRef:
        resolver: git
        params:
          - name: url
            value: https://github.com/tektoncd/catalog.git
          - name: revision
            value: main
          - name: pathInRepo
            value: task/git-clone/0.6/git-clone.yaml
      params:
      - name: sample-task-parameter
        value: test
```

The following example task run references a remote task from a Git repository:

```yaml
apiVersion: tekton.dev/v1
kind: TaskRun
metadata:
  name: git-task-reference-demo
spec:
  taskRef:
    resolver: git
    params:
      - name: url
        value: https://github.com/tektoncd/catalog.git
      - name: revision
        value: main
      - name: pathInRepo
        value: task/git-clone/0.6/git-clone.yaml
  params:
  - name: sample-task-parameter
    value: test
```

The following example task includes a step that references a`StepAction` definition from a Git repository:

```yaml
apiVersion: tekton.dev/v1
kind: Task
metadata:
  name: git-stepaction-reference-demo
spec:
  steps:
  - name: example-step
    ref:
      resolver: git
      - name: url
        value: https://github.com/openshift-pipelines/tektoncd-catalog.git
      - name: revision
        value: p
      - name: pathInRepo
        value: stepactions/stepaction-git-clone/0.4.1/stepaction-git-clone.yaml
    params:
    - name: sample-stepaction-parameter
      value: test
```

### 3.4. Specifying a remote pipeline, task, or step action with an authenticated Git APICopy link

You can specify a remote pipeline, task, or`StepAction` definition from a Git repository by using the Git resolver. The repository must contain a YAML file that defines the pipeline or task. You can securely access repositories by using an authenticated API, which supports user authentication.

#### 3.4.1. Configuring the Git resolver for an authenticated APICopy link

For an authenticated Source Control Management (SCM) API, you must set the configuration for the authenticated Git connection.

You can use Git repository providers that are supported by the`go-scm` library. Not all`go-scm` implementations have been tested with the Git resolver, but the following providers are known to work:

- `github.com` and GitHub Enterprise
- `gitlab.com` and self-hosted Gitlab
- Gitea
- Bitbucket Data Center
- Bitbucket Cloud

Note

- You can configure Git connections by using the authenticated SCM API. You can provide a security token that enables all users on your cluster to access one repository. Additionally, you can specify different SCM providers and tokens for specific pipelines or tasks.
- If you configure the Git resolver to use the authenticated SCM API, you can also use anonymous Git clone references to retrieve pipelines and tasks.

Procedure

To edit the`TektonConfig` custom resource, enter the following command:

```shell-session
$ oc edit TektonConfig config
```

In the`TektonConfig` custom resource, edit the`pipeline.git-resolver-config` spec:

```yaml
apiVersion: operator.tekton.dev/v1alpha1
kind: TektonConfig
metadata:
  name: config
spec:
  pipeline:
    git-resolver-config:
      default-revision: main 
```

1

```yaml

      fetch-timeout: 1m 
```

2

```yaml

      scm-type: github 
```

3

```yaml

      server-url: api.internal-github.com 
```

4

```yaml

      api-token-secret-name: github-auth-secret 
```

5

```yaml

      api-token-secret-key: github-auth-key 
```

6

```yaml

      api-token-secret-namespace: github-auth-namespace 
```

7

```yaml

      default-org: tektoncd 
```

8

1

The default Git revision to use if none is specified.

2

The maximum time any single Git clone resolution might take, for example,`1m`,`2s`,`700ms`. Red Hat OpenShift Pipelines also enforces a global maximum timeout of 1 minute on all resolution requests.

3

The SCM provider type.

4

The base URL for use with the authenticated SCM API. This setting is not required if you are using`github.com`,`gitlab.com`, or Bitbucket Cloud.

5

The name of the secret that contains the SCM provider API token.

6

The key within the token secret that contains the token.

7

The namespace containing the token secret, if not`default`.

8

Optional: The default organization for the repository, when using the authenticated API. This organization is used if you do not specify an organization in the resolver parameters.

Note

The`scm-type`,`api-token-secret-name`, and`api-token-secret-key` settings are required to use the authenticated SCM API.

#### 3.4.2. Configuring multiple Git providersCopy link

You can configure multiple Git providers, or you can add multiple configurations for the same Git provider, to use in different task runs and pipeline runs.

Add details in the`TektonConfig` custom resource (CR) with your unique identifier key prefix.

Procedure

Edit the`TektonConfig` CR by running the following command:

```shell-session
$ oc edit TektonConfig config
```

In the`TektonConfig` CR, edit the`pipeline.git-resolver-config` spec:

```yaml
apiVersion: operator.tekton.dev/v1alpha1
kind: TektonConfig
metadata:
  name: config
spec:
# ...
  pipeline:
    git-resolver-config:
      # configuration 1 
```

1

```yaml

      fetch-timeout: "1m"
      default-url: "https://github.com/tektoncd/catalog.git"
      default-revision: "main"
      scm-type: "github"
      server-url: ""
      api-token-secret-name: ""
      api-token-secret-key: ""
      api-token-secret-namespace: "default"
      default-org: ""
      # configuration 2 
```

2

```yaml

      test1.fetch-timeout: "5m"
      test1.default-url: ""
      test1.default-revision: "stable"
      test1.scm-type: "github"
      test1.server-url: "api.internal-github.com"
      test1.api-token-secret-name: "test1-secret"
      test1.api-token-secret-key: "token"
      test1.api-token-secret-namespace: "test1"
      test1.default-org: "tektoncd"
      # configuration 3 
```

3

```yaml

      test2.fetch-timeout: "10m"
      test2.default-url: ""
      test2.default-revision: "stable"
      test2.scm-type: "gitlab"
      test2.server-url: "api.internal-gitlab.com"
      test2.api-token-secret-name: "test2-secret"
      test2.api-token-secret-key: "pat"
      test2.api-token-secret-namespace: "test2"
      test2.default-org: "tektoncd-infra"
# ...
```

1

The default configuration to use if no`configKey` key is provided or the key is provided with the`default` value.

2

The configuration used if the`configKey` key is passed with the`test1` value.

3

The configuration used if the`configKey` key is passed with the`test2` value.

Warning

`configKey` values with the`.` symbol are not supported. If you try to pass a`configKey` value that contains the`.` symbol, the`TaskRun` or`PipelineRun` resource where you passed the value fails to run.

#### 3.4.3. Specifying a remote pipeline, task, or step action using the Git resolver with the authenticated SCM APICopy link

When creating a pipeline run, you can specify a remote pipeline from a Git repository using the authenticated SCM API. When creating a pipeline or a task run, you can specify a remote task from a Git repository. When creating a step within a task, you can reference a remote`StepAction` definition from a Git repository.

Prerequisites

- If you want to use the authenticated SCM API, you must configure the authenticated Git connection for the Git resolver.

Procedure

To specify a remote pipeline, task, or`StepAction` definition from a Git repository, use the following reference format in the`pipelineRef`,`taskRef`, or`step.ref` spec:

```yaml
# ...
  resolver: git
  params:
  - name: org
    value: <git_organization_name>
  - name: repo
    value: <git_repository_name>
  - name: revision
    value: <branch_name>
  - name: pathInRepo
    value: <path_in_repository>
# ...
```

Expand

`org`

The organization for the repository, when using the authenticated SCM API.

`tektoncd`

`repo`

The repository name, when using the authenticated SCM API.

`test-infra`

`revision`

The Git revision in the repository. You can specify a branch name, a tag name, or a commit SHA hash.

`aeb957601cf41c012be462827053a21a420befca``main``v0.38.2`

`pathInRepo`

The path name of the YAML file in the repository.

`task/golang-build/0.3/golang-build.yaml`

| Table 3.4. Supported parameters for the Git resolver | Parameter | Description | Example value |
| --- | --- | --- | --- |

Show more

Note

To clone and fetch the repository anonymously, use the`url` parameter. To use the authenticated SCM API, use the`repo` parameter. Do not specify the`url` parameter and the`repo` parameter together.

If the pipeline or task requires additional parameters, specify values for these parameters in the`params` section of the specification of the pipeline, pipeline run, or task run. The`params` section of the`pipelineRef` or`taskRef` specification must contain only the parameters that the resolver supports.

Examples

The following example pipeline run references a remote pipeline from a Git repository:

```yaml
apiVersion: tekton.dev/v1
kind: PipelineRun
metadata:
  name: git-pipeline-reference-demo
spec:
  pipelineRef:
    resolver: git
    params:
    - name: org
      value: tektoncd
    - name: repo
      value: catalog
    - name: revision
      value: main
    - name: pathInRepo
      value: pipeline/simple/0.1/simple.yaml
  params:
  - name: name
    value: "testPipelineRun"
  - name: sample-pipeline-parameter
    value: test
```

The following example pipeline references a remote task from a Git repository:

```yaml
apiVersion: tekton.dev/v1
kind: Pipeline
metadata:
  name: pipeline-with-git-task-reference-demo
spec:
  tasks:
  - name: "git-task-reference-demo"
    taskRef:
      resolver: git
      params:
      - name: org
        value: tektoncd
      - name: repo
        value: catalog
      - name: revision
        value: main
      - name: pathInRepo
        value: task/git-clone/0.6/git-clone.yaml
    params:
    - name: sample-task-parameter
      value: test
```

The following example task run references a remote task from a Git repository:

```yaml
apiVersion: tekton.dev/v1
kind: TaskRun
metadata:
  name: git-task-reference-demo
spec:
  taskRef:
    resolver: git
    params:
    - name: org
      value: tektoncd
    - name: repo
      value: catalog
    - name: revision
      value: main
    - name: pathInRepo
      value: task/git-clone/0.6/git-clone.yaml
  params:
  - name: sample-task-parameter
    value: test
```

The following example task includes a step that references a`StepAction` definition from a Git repository:

```yaml
apiVersion: tekton.dev/v1
kind: Task
metadata:
  name: git-stepaction-reference-demo
spec:
  steps:
  - name: example-step
    ref:
      resolver: git
      - name: org
        value: openshift-pipelines
      - name: repo
        value: tektoncd-catalog
      - name: revision
        value: p
      - name: pathInRepo
        value: stepactions/stepaction-git-clone/0.4.1/stepaction-git-clone.yaml
    params:
    - name: sample-stepaction-parameter
      value: test
```

#### 3.4.4. Specifying multiple Git providersCopy link

You can specify multiple Git providers by passing the unique`configKey` parameter when creating`TaskRun` and`PipelineRun` resources.

If no`configKey` parameter is passed, the default configuration is used. You can also specify default configuration by setting the`configKey` value to`default`.

Warning

`configKey` values with the`.` symbol are not supported. If you try to pass a`configKey` value that contains the`.` symbol, the`TaskRun` or`PipelineRun` resource where you passed the value fails to run.

Prerequisites

- Configure multiple Git providers through the`Tektonconfig` custom resource. For more information, see "Configuring multiple Git providers".

Procedure

To specify a Git provider, use the following reference format in the`pipelineRef` and`taskRef` spec:

```yaml
# ...
  resolver: git
  params:
  # ...
  - name: configKey
    value: <your_unique_key> 
```

1

```yaml

# ...
```

1

Your unique key that matches one of the configuration keys, for example,`test1`.

#### 3.4.5. Specifying a remote pipeline or task by using the Git resolver with the authenticated SCM API overriding the Git resolver configurationCopy link

You can override the initial configuration settings in specific pipeline runs or tasks to customize the behavior according to different use cases. You can use this method to access an authenticated provider that is not configured in the`TektonConfig` custom resource (CR).

The following example task run references a remote task from a Git repository that overrides the previous resolver configuration:

```yaml
apiVersion: tekton.dev/v1
kind: TaskRun
metadata:
  name: git-task-reference-demo
spec:
  taskRef:
    resolver: git
    params:
    - name: org
      value: tektoncd
    - name: repo
      value: catalog
    - name: revision
      value: main
    - name: pathInRepo
      value: task/git-clone/0.6/git-clone.yaml
    - name: token
      value: my-secret-token
    - name: tokenKey
      value: token
    - name: scmType
      value: github
    - name: serverURL
      value: https://ghe.mycompany.com
```

Expand

`org`

The organization for the repository.

`tektoncd`

repo

The repository name.

catalog

revision

The Git revision in the repository. You can specify a branch name, a tag name, or a commit SHA hash.

main

pathInRepo

The path name of the YAML file in the repository.

task/git-clone/0.6/git-clone.yaml

token

The secret name used for authentication.

my-secret-token

tokenKey

The key name for the token.

token

scmType

The type of SCM (Source Control Management) system.

github

serverURL

The URL of the server hosting the repository.

`https://ghe.mycompany.com`

| Table 3.5. Supported parameters to override the Git resolver | Parameter | Description | Example value |
| --- | --- | --- | --- |

Show more

### 3.5. Specifying a remote pipeline, task, or step action by using the HTTP resolverCopy link

You can specify a remote pipeline, task, or`StepAction` definition from an HTTP or HTTPS URL by using the HTTP resolver. The URL must point to a YAML file that defines the pipeline, task, or step action.

#### 3.5.1. Configuring the HTTP resolverCopy link

You can use the HTTP resolver to fetch pipelines or tasks from an HTTP or HTTPS URL. You can configure the default values for the HTTP resolver by editing the`TektonConfig` custom resource (CR).

Procedure

Edit the`TektonConfig` CR by entering the following command:

```shell-session
$ oc edit TektonConfig config
```

In the`TektonConfig` CR, edit the`pipeline.http-resolver-config` spec:

```yaml
apiVersion: operator.tekton.dev/v1alpha1
kind: TektonConfig
metadata:
  name: config
spec:
  pipeline:
    http-resolver-config:
      fetch-timeout: "1m" 
```

1

1

The maximum amount of time the HTTP resolver waits for a response from the server.

#### 3.5.2. Specifying a remote pipeline, task, or step action with the HTTP ResolverCopy link

When creating a pipeline run, you can specify a remote pipeline from an HTTP or HTTPS URL. When creating a pipeline or a task run, you can specify a remote task from an HTTP or HTTPS URL. When creating a step within a task, you can reference a remote`StepAction` definition from an HTTP or HTTPS URL.

Procedure

Specify a remote pipeline, task, or`StepAction` definition from an HTTP or HTTPS URL, using the following format in the`pipelineRef`,`taskRef`, or`step.ref` spec:

```yaml
# ...
  resolver: http
  params:
  - name: url
    value: <fully_qualified_http_url>
# ...
```

Expand

`url`

The HTTP URL pointing to the Tekton resource to fetch.

`https://raw.githubusercontent.com/openshift-pipelines/tektoncd-catalog/p/tasks/task-git-clone/0.4.1/task-git-clone.yaml`

| Table 3.6. Supported parameters for the HTTP Resolver | Parameter | Description | Example Value |
| --- | --- | --- | --- |

Show more

Examples

The following example pipeline run references a remote pipeline from the same cluster:

```yaml
apiVersion: tekton.dev/v1
kind: PipelineRun
metadata:
  name: http-pipeline-reference-demo
spec:
  pipelineRef:
    resolver: http
    params:
    - name: url
      value: https://raw.githubusercontent.com/tektoncd/catalog/main/pipeline/build-push-gke-deploy/0.1/build-push-gke-deploy.yaml
  params:
  - name: sample-pipeline-parameter
    value: test
  - name: username
    value: "pipelines"
```

The following example pipeline defines a task that references a remote task from an HTTPS URL:

```yaml
apiVersion: tekton.dev/v1
kind: Pipeline
metadata:
  name: pipeline-with-http-task-reference-demo
spec:
  tasks:
  - name: "http-task-demo"
    taskRef:
      resolver: http
      params:
      - name: url
        value: https://raw.githubusercontent.com/openshift-pipelines/tektoncd-catalog/p/tasks/task-git-clone/0.4.1/task-git-clone.yaml
    params:
    - name: sample-task-parameter
      value: test
```

The following example task run references a remote task from an HTTPS URL:

```yaml
apiVersion: tekton.dev/v1
kind: TaskRun
metadata:
  name: http-task-reference-demo
spec:
  taskRef:
    resolver: http
    params:
    - name: url
      value: https://raw.githubusercontent.com/openshift-pipelines/tektoncd-catalog/p/tasks/task-git-clone/0.4.1/task-git-clone.yaml
  params:
  - name: sample-task-parameter
    value: test
```

The following example task includes a step that references a`StepAction` definition from an HTTPS URL:

```yaml
apiVersion: tekton.dev/v1
kind: Task
metadata:
  name: http-stepaction-reference-demo
spec:
  steps:
  - name: example-step
    ref:
      resolver: http
      params:
      - name: url
        value: https://raw.githubusercontent.com/openshift-pipelines/tektoncd-catalog/p/stepactions/stepaction-git-clone/0.4.1/stepaction-git-clone.yaml
    params:
    - name: sample-stepaction-parameter
      value: test
```

### 3.6. Specifying a pipeline, task, or step action from the same clusterCopy link

You can use the cluster resolver to specify a pipeline, task, or`StepAction` definition that is defined in a namespace on the OpenShift Container Platform cluster where Red Hat OpenShift Pipelines is running.

In particular, you can use the cluster resolver to access tasks that OpenShift Pipelines provides in its installation namespace, which is normally the`openshift-pipelines` namespace.

#### 3.6.1. Configuring the cluster resolverCopy link

You can change the default kind and namespace for the cluster resolver, or limit the namespaces that the cluster resolver can use.

Procedure

To edit the`TektonConfig` custom resource, enter the following command:

```shell-session
$ oc edit TektonConfig config
```

In the`TektonConfig` custom resource, edit the`pipeline.cluster-resolver-config` spec:

```yaml
apiVersion: operator.tekton.dev/v1alpha1
kind: TektonConfig
metadata:
  name: config
spec:
  pipeline:
    cluster-resolver-config:
      default-kind: pipeline 
```

1

```yaml

      default-namespace: namespace1 
```

2

```yaml

      allowed-namespaces: namespace1, namespace2 
```

3

```yaml

      blocked-namespaces: namespace3, namespace4 
```

4

1

The default resource kind to fetch, if not specified in parameters.

2

The default namespace for fetching resources, if not specified in parameters.

3

A comma-separated list of namespaces that the resolver is allowed to access. If this key is not defined, all namespaces are allowed.

4

An optional comma-separated list of namespaces which the resolver is blocked from accessing. If this key is not defined, all namespaces are allowed.

#### 3.6.2. Specifying a pipeline, task, or step action from the same cluster using the cluster resolverCopy link

When creating a pipeline run, you can specify a pipeline that exists on the same cluster. When creating a pipeline or a task run, you can specify a task that exists on the same cluster. When creating a step within a task, you can specify a`StepAction` definition that exists on the same cluster.

Procedure

To specify a pipeline, task, or`StepAction` definition from the same cluster, use the following reference format in the`pipelineRef`,`taskRef`, or`step.ref` spec:

```yaml
# ...
  resolver: cluster
  params:
  - name: name
    value: <name>
  - name: namespace
    value: <namespace>
  - name: kind
    value: [pipeline|task|stepaction]
# ...
```

Expand

`name`

The name of the resource to fetch.

`some-pipeline`

`namespace`

The namespace in the cluster containing the resource.

`other-namespace`

`kind`

The kind of the resource to fetch.

`pipeline`

| Table 3.7. Supported parameters for the cluster resolver | Parameter | Description | Example value |
| --- | --- | --- | --- |

Show more

If the pipeline or task requires additional parameters, provide these parameters in`params`.

Examples

The following example pipeline run references a pipeline from the same cluster:

```yaml
apiVersion: tekton.dev/v1
kind: PipelineRun
metadata:
  name: cluster-pipeline-reference-demo
spec:
  pipelineRef:
    resolver: cluster
    params:
    - name: name
      value: some-pipeline
    - name: namespace
      value: test-namespace
    - name: kind
      value: pipeline
  params:
  - name: sample-pipeline-parameter
    value: test
```

The following example pipeline references a task from the same cluster:

```yaml
apiVersion: tekton.dev/v1
kind: Pipeline
metadata:
  name: pipeline-with-cluster-task-reference-demo
spec:
  tasks:
  - name: "cluster-task-reference-demo"
    taskRef:
      resolver: cluster
      params:
      - name: name
        value: some-task
      - name: namespace
        value: test-namespace
      - name: kind
        value: task
    params:
    - name: sample-task-parameter
      value: test
```

The following example task run references a task from the same cluster:

```yaml
apiVersion: tekton.dev/v1
kind: TaskRun
metadata:
  name: cluster-task-reference-demo
spec:
  taskRef:
    resolver: cluster
    params:
    - name: name
      value: some-task
    - name: namespace
      value: test-namespace
    - name: kind
      value: task
  params:
  - name: sample-task-parameter
    value: test
```

The following example task includes a step that references a`StepAction` definition from the same cluster:

```yaml
apiVersion: tekton.dev/v1
kind: Task
metadata:
  name: cluster-stepaction-reference-demo
spec:
  steps:
  - name: example-step
    ref:
      resolver: cluster
      params:
      - name: name
        value: some-step
      - name: namespace
        value: test-namespace
      - name: kind
        value: stepaction
  params:
  - name: sample-stepaction-parameter
    value: test
```

### 3.7. Tasks provided in the OpenShift Pipelines namespaceCopy link

An OpenShift Pipelines installation includes a set of standard tasks that you can use in your pipelines. These tasks are located in the OpenShift Pipelines installation namespace, which is normally the`openshift-pipelines` namespace. You can use the cluster resolver to access the tasks.

Until version 1.16, OpenShift Pipelines included`ClusterTask` functionality. Versions 1.17 and later no longer include this functionality. If your pipelines use`ClusterTask` references, you can re-create them with the tasks that are available from the OpenShift Pipelines installation namespace by using the cluster resolver. However, certain changes are made in these tasks compared to the previously existing`ClusterTask` definitions.

You cannot specify a custom execution image in any of the tasks available in the OpenShift Pipelines installation namespace. These tasks do not support parameters such as`BUILDER_IMAGE`,`gitInitImage`, or`KN_IMAGE`. If you want to use a custom execution image, create a copy of the task and replace the image by editing the copy.

##### buildah

The`buildah` task builds a source code tree into a container image and then pushes the image to a container registry.

Example usage of the`buildah` task

```yaml
apiVersion: tekton.dev/v1
kind: Pipeline
metadata:
  name: build-and-deploy
spec:
# ...
  tasks:
# ...
  - name: build-image
    taskRef:
      resolver: cluster
      params:
      - name: kind
        value: task
      - name: name
        value: buildah
      - name: namespace
        value: openshift-pipelines
    params:
    - name: IMAGE
      value: $(params.IMAGE)
    workspaces:
    - name: source
      workspace: shared-workspace
# ...
```

Expand

`IMAGE`

Fully qualified container image name to be built by Buildah.

`string`

`DOCKERFILE`

Path to the`Dockerfile`(or`Containerfile`) relative to the`source` workspace.

`string`

`./Dockerfile`

`CONTEXT`

Path to the directory to use as the context.

`string`

`.`

`STORAGE_DRIVER`

Set the Buildah storage driver to reflect the settings of the current cluster node settings.

`string`

`vfs`

`FORMAT`

The format of the container to build, either`oci` or`docker`.

`string`

`oci`

`BUILD_EXTRA_ARGS`

Extra parameters for the`build` command when building the image.

`string`

`PUSH_EXTRA_ARGS`

Extra parameters for the`push` command when pushing the image.

`string`

`SKIP_PUSH`

Skip pushing the image to the container registry.

`string`

`false`

`TLS_VERIFY`

The TLS verification flag, normally`true`.

`string`

`true`

`VERBOSE`

Turn on verbose logging; all commands executed are added to the log.

`string`

`false`

| Table 3.8. Supported parameters for the buildah task | Parameter | Description | Type | Default value |
| --- | --- | --- | --- | --- |

Show more

Expand

`source`

Container build context, usually the application source code that includes a`Dockerfile` or`Containerfile` file.

`dockerconfig`

An optional workspace for providing a`.docker/config.json` file that Buildah uses to access the container registry. Place the file at the root of the workspace with the name`config.json` or`.dockerconfigjson`.

`rhel-entitlement`

An optional workspace for providing the entitlement keys that Buildah uses to access a Red Hat Enterprise Linux (RHEL) subscription. The mounted workspace must contains the`entitlement.pem` and`entitlement-key.pem` files.

| Table 3.9. Supported workspaces for the buildah task | Workspace | Description |
| --- | --- | --- |

Show more

Expand

`IMAGE_URL`

`string`

The fully qualified name of the image that was built.

`IMAGE_DIGEST`

`string`

Digest of the image that was built.

| Table 3.10. Results that the buildah task returns | Result | Type | Description |
| --- | --- | --- | --- |

Show more

Changes from the`buildah``ClusterTask`

- The`VERBOSE` parameter was added.
- The`BUILDER_IMAGE` parameter was removed.

##### git-cli

The`git-cli` task runs the`git` command-line utility. You can pass the full Git command or several commands to run using the`GIT_SCRIPT` parameter. If the commands need authentication to a Git repository, for example, to complete a push, you must supply the authentication credentials.

Example usage of the`git-cli` task

```yaml
apiVersion: tekton.dev/v1
kind: Pipeline
metadata:
  name: update-repo
spec:
# ...
  tasks:
# ...
  - name: push-to-repo
    taskRef:
      resolver: cluster
      params:
      - name: kind
        value: task
      - name: name
        value: git-cli
      - name: namespace
        value: openshift-pipelines
    params:
    - name: GIT_SCRIPT
      value: "git push"
    - name: GIT_USER_NAME
      value: "Example Developer"
    - name: GIT_USER_EMAIL
      value: "developer@example.com"
    workspaces:
    - name: ssh-directory
      workspace: ssh-workspace 
```

1

```yaml

    - name: source
      workspace: shared-workspace
# ...
```

1

In this example,`ssh-workspace` must contain the contents of the`.ssh` directory with a valid key for authorization to the Git repository.

Expand

`CRT_FILENAME`

Certificate Authority (CA) bundle filename in the`ssl-ca-directory` workspace.

`string`

`ca-bundle.crt`

`HTTP_PROXY`

HTTP proxy server (non-TLS requests).

`string`

`HTTPS_PROXY`

HTTPS proxy server (TLS requests).

`string`

`NO_PROXY`

Opt out of proxying HTTP/HTTPS requests.

`string`

`SUBDIRECTORY`

Relative path to the`source` workspace where the git repository is present.

`string`

`USER_HOME`

Absolute path to the Git user home directory in the pod.

`string`

`/home/git`

`DELETE_EXISTING`

Erase any existing contents of the`source` workspace before completing the git operations.

`string`

`true`

`VERBOSE`

Log all the executed commands.

`string`

`false`

`SSL_VERIFY`

The global`http.sslVerify` value. Do not use`false` unless you trust the remote repository.

`string`

`true`

`GIT_USER_NAME`

Git user name for performing Git operations.

`string`

`GIT_USER_EMAIL`

Git user email for performing Git operations.

`string`

`GIT_SCRIPT`

The Git script to run.

`string`

`git help`

| Table 3.11. Supported parameters for the git-cli task | Parameter | Description | Type | Default value |
| --- | --- | --- | --- | --- |

Show more

Expand

`ssh-directory`

A`.ssh` directory with the private key,`known_hosts`,`config`, and other files as necessary. If you provide this workspace, the task uses it for authentication to the Git repository. Bind this workspace to a`Secret` resource for secure storage of authentication information.

`basic-auth`

A workspace containing a`.gitconfig` and`.git-credentials` files. If you provide this workspace, the task uses it for authentication to the Git repository. Use a`ssh-directory` workspace for authentication instead of`basic-auth` whenever possible. Bind this workspace to a`Secret` resource for secure storage of authentication information.

`ssl-ca-directory`

A workspace containing CA certificates. If you provide this workspace, Git uses these certificates to verify the peer when interacting with remote repositories using HTTPS.

`source`

A workspace that contains the fetched Git repository.

`input`

An optional workspace that contains the files that need to be added to the Git repository. You can access the workspace from your script using`$(workspaces.input.path)`, for example:

`cp $(workspaces.input.path)/ .``git add `

| Table 3.12. Supported workspaces for the git-cli task | Workspace | Description |
| --- | --- | --- |

Show more

Expand

`COMMIT`

`string`

The SHA digest of the commit that is at the HEAD of the current branch in the cloned Git repository.

| Table 3.13. Results that the git-cli task returns | Result | Type | Description |
| --- | --- | --- | --- |

Show more

Changes from the`git-cli``ClusterTask`

- Several new parameters were added.
- The`BASE_IMAGE` parameter was removed.
- The`ssl-ca-directory` workspace was added.
- The default values for the`USER_HOME` and`VERBOSE` parameters were changed.
- The name of the result was changed from`commit` to`COMMIT`.

##### git-clone

The`git-clone` task uses Git to initialize and clone a remote repository on a workspace. You can use this task at the start of a pipeline that builds or otherwise processes this source code.

Example usage of the`git-clone` task

```yaml
apiVersion: tekton.dev/v1
kind: Pipeline
metadata:
  name: build-source
spec:
# ...
  tasks:
  - name: clone-repo
    taskRef:
      resolver: cluster
      params:
      - name: kind
        value: task
      - name: name
        value: git-clone
      - name: namespace
        value: openshift-pipelines
    params:
    - name: URL
      value: "https://github.com/example/repo.git"
    workspaces:
    - name: output
      workspace: shared-workspace
```

Expand

`CRT_FILENAME`

Certificate Authority (CA) bundle filename in the`ssl-ca-directory` workspace.

`string`

`ca-bundle.crt`

`HTTP_PROXY`

HTTP proxy server (non-TLS requests).

`string`

`HTTPS_PROXY`

HTTPS proxy server (TLS requests).

`string`

`NO_PROXY`

Opt out of proxying HTTP/HTTPS requests.

`string`

`SUBDIRECTORY`

Relative path in the`output` workspace where the task places the Git repository.

`string`

`USER_HOME`

Absolute path to the Git user home directory in the pod.

`string`

`/home/git`

`DELETE_EXISTING`

Delete the contents of the default workspace, if they exist, before running the Git operations.

`string`

`true`

`VERBOSE`

Log the executed commands.

`string`

`false`

`SSL_VERIFY`

The global`http.sslVerify` value. Do not set this parameter to`false` unless you trust the remote repository.

`string`

`true`

`URL`

Git repository URL.

`string`

`REVISION`

The revision to check out, for example, a branch or tag.

`string`

`main`

`REFSPEC`

The`refspec` string for the repository that the task fetches before checking out the revision.

`string`

`SUBMODULES`

Initialize and fetch Git submodules.

`string`

`true`

`DEPTH`

Number of commits to fetch, a "shallow clone" is a single commit.

`string`

`1`

`SPARSE_CHECKOUT_DIRECTORIES`

List of directory patterns, separated by commas, for performing a "sparse checkout".

`string`

| Table 3.14. Supported parameters for the git-clone task | Parameter | Description | Type | Default value |
| --- | --- | --- | --- | --- |

Show more

Expand

`ssh-directory`

A`.ssh` directory with the private key,`known_hosts`,`config`, and other files as necessary. If you provide this workspace, the task uses it for authentication to the Git repository. Bind this workspace to a`Secret` resource for secure storage of authentication information.

`basic-auth`

A workspace containing a`.gitconfig` and`.git-credentials` files. If you provide this workspace, the task uses it for authentication to the Git repository. Use a`ssh-directory` workspace for authentication instead of`basic-auth` whenever possible. Bind this workspace to a`Secret` resource for secure storage of authentication information.

`ssl-ca-directory`

A workspace containing CA certificates. If you provide this workspace, Git uses these certificates to verify the peer when interacting with remote repositories using HTTPS.

`output`

A workspace that contains the fetched git repository, data will be placed on the root of the workspace or on the relative path defined by the`SUBDIRECTORY` parameter.

| Table 3.15. Supported workspaces for the git-clone task | Workspace | Description |
| --- | --- | --- |

Show more

Expand

`COMMIT`

`string`

The SHA digest of the commit that is at the HEAD of the current branch in the cloned Git repository.

`URL`

`string`

The URL of the repository that was cloned.

`COMMITTER_DATE`

`string`

The epoch timestamp of the commit that is at the HEAD of the current branch in the cloned Git repository.

| Table 3.16. Results that the git-clone task returns | Result | Type | Description |
| --- | --- | --- | --- |

Show more

Changes from the`git-clone``ClusterTask`

- All parameter names were changed to uppercase.
- All result names were changed to uppercase.
- The`gitInitImage` parameter was removed.

##### kn

The`kn` task uses the`kn` command-line utility to complete operations on Knative resources, such as services, revisions, or routes.

Example usage of the`kn` task

```yaml
apiVersion: tekton.dev/v1
kind: PipelineRun
metadata:
 name: kn-run
spec:
 pipelineSpec:
   tasks:
   - name: kn-run
     taskRef:
       resolver: cluster
       params:
       - name: kind
         value: task
       - name: name
         value: kn
       - name: namespace
         value: openshift-pipelines
     params:
     - name: ARGS
       value: [version]
```

Expand

`ARGS`

The arguments for the`kn` utility.

`array`

`- help`

| Table 3.17. Supported parameters for the kn task | Parameter | Description | Type | Default value |
| --- | --- | --- | --- | --- |

Show more

Changes from the`kn``ClusterTask`

- The`KN_IMAGE` parameter was removed.

##### kn-apply

The`kn-apply` task deploys a specified image to a Knative Service. This task uses the`kn service apply` command to create or update the specified Knative service.

Example usage of the`kn-apply` task

```yaml
apiVersion: tekton.dev/v1
kind: PipelineRun
metadata:
 name: kn-apply-run
spec:
 pipelineSpec:
   tasks:
   - name: kn-apply-run
     taskRef:
       resolver: cluster
       params:
       - name: kind
         value: task
       - name: name
         value: kn-apply
       - name: namespace
         value: openshift-pipelines
     params:
     - name: SERVICE
       value: "hello"
     - name: IMAGE
       value: "gcr.io/knative-samples/helloworld-go:latest"
```

Expand

`SERVICE`

The Knative service name.

`string`

`IMAGE`

The fully qualified name of the image to deploy.

`string`

| Table 3.18. Supported parameters for the kn-apply task | Parameter | Description | Type | Default value |
| --- | --- | --- | --- | --- |

Show more

Changes from the`kn-apply``ClusterTask`

- The`KN_IMAGE` parameter was removed.

##### maven

The`maven` task runs a Maven build.

Example usage of the`maven` task

```yaml
apiVersion: tekton.dev/v1
kind: Pipeline
metadata:
  name: build-and-deploy
spec:
# ...
  tasks:
# ...
  - name: build-from-source
    taskRef:
      resolver: cluster
      params:
      - name: kind
        value: task
      - name: name
        value: maven
      - name: namespace
        value: openshift-pipelines
    workspaces:
    - name: source
      workspace: shared-workspace
# ...
```

Expand

`GOALS`

The Maven goals to run.

`array`

`- package`

`MAVEN_MIRROR_URL`

The Maven repository mirror URL.

`string`

`SUBDIRECTORY`

The subdirectory within the`source` workspace that the task runs the Maven build on.

`string`

`.`

| Table 3.19. Supported parameters for the maven task | Parameter | Description | Type | Default value |
| --- | --- | --- | --- | --- |

Show more

Expand

`source`

The workspace that contains the Maven project.

`server_secret`

The workspace that contains the secrets for connecting to the Maven server, such as the user name and password.

`proxy_secret`

The workspace that contains the credentials for connecting to the proxy server, such as the user name and password.

`proxy_configmap`

The workspace that contains proxy configuration values, such as`proxy_port`,`proxy_host`,`proxy_protocol`,`proxy_non_proxy_hosts`.

`maven_settings`

The workspace that contains custom Maven settings.

| Table 3.20. Supported workspaces for the maven task | Workspace | Description |
| --- | --- | --- |

Show more

Changes from the`maven``ClusterTask`

- The parameter name`CONTEXT_DIR` was changed to`SUBDIRECTORY`.
- The workspace name`maven-settings` was changed to`maven_settings`.

##### openshift-client

The`openshift-client` task runs commands using the`oc` command-line interface. You can use this task to manage an OpenShift Container Platform cluster.

Example usage of the`openshift-client` task

```yaml
apiVersion: tekton.dev/v1
kind: PipelineRun
metadata:
 name: openshift-client-run
spec:
 pipelineSpec:
   tasks:
   - name: openshift-client-run
     taskRef:
       resolver: cluster
       params:
       - name: kind
         value: task
       - name: name
         value: openshift-client
       - name: namespace
         value: openshift-pipelines
     params:
     - name: SCRIPT
       value: "oc version"
```

Expand

`SCRIPT`

The`oc` CLI arguments to run.

`string`

`oc help`

`VERSION`

The OpenShift Container Platform version to use.

`string`

`latest`

| Table 3.21. Supported parameters for the openshift-client task | Parameter | Description | Type | Default value |
| --- | --- | --- | --- | --- |

Show more

Expand

`manifest_dir`

The workspace containing manifest files that you want to apply using the`oc` utility.

`kubeconfig_dir`

An optional workspace in which you can provide a`.kube/config` file that contains credentials for accessing the cluster. Place this file at the root of the workspace and name it`kubeconfig`.

| Table 3.22. Supported workspaces for the openshift-client task | Workspace | Description |
| --- | --- | --- |

Show more

Changes from the`openshift-client``ClusterTask`

- The workspace name`manifest-dir` was changed to`manifest_dir`.
- The workspace name`kubeconfig-dir` was changed to`kubeconfig_dir`.

##### s2i-dotnet

The`s2i-dotnet` task builds the source code using the Source to Image (S2I) dotnet builder image, which is available from the OpenShift Container Platform registry as`image-registry.openshift-image-registry.svc:5000/openshift/dotnet`.

Example usage of the`s2i-dotnet` task

```yaml
apiVersion: tekton.dev/v1
kind: Pipeline
metadata:
  name: build-and-deploy
spec:
# ...
  tasks:
# ...
  - name: build-s2i
    taskRef:
      resolver: cluster
      params:
      - name: kind
        value: task
      - name: name
        value: s2i-dotnet
      - name: namespace
        value: openshift-pipelines
    workspaces:
    - name: source
      workspace: shared-workspace
# ...
```

Expand

`IMAGE`

The fully qualified name for the container image that the S2I process builds.

`string`

`IMAGE_SCRIPTS_URL`

The URL containing the default assemble and run scripts for the builder image.

`string`

`image:///usr/libexec/s2i`

`ENV_VARS`

An array of values for environment variables to set in the build process, listed in the`KEY=VALUE` format.

`array`

`CONTEXT`

Path to the directory within the`source` workspace to use as the context.

`string`

`.`

`STORAGE_DRIVER`

Set the Buildah storage driver to reflect the settings of the current cluster node settings.

`string`

`vfs`

`FORMAT`

The format of the container to build, either`oci` or`docker`.

`string`

`oci`

`BUILD_EXTRA_ARGS`

Extra parameters for the`build` command when building the image.

`string`

`PUSH_EXTRA_ARGS`

Extra parameters for the`push` command when pushing the image.

`string`

`SKIP_PUSH`

Skip pushing the image to the container registry.

`string`

`false`

`TLS_VERIFY`

The TLS verification flag, normally`true`.

`string`

`true`

`VERBOSE`

Turn on verbose logging; all commands executed are added to the log.

`string`

`false`

`VERSION`

The tag of the image stream, which corresponds to the language version.

`string`

`latest`

| Table 3.23. Supported parameters for the s2i-dotnet task | Parameter | Description | Type | Default value |
| --- | --- | --- | --- | --- |

Show more

Expand

`source`

The application source code, which is the build context for the S2I workflow.

`dockerconfig`

An optional workspace for providing a`.docker/config.json` file that Buildah uses to access the container registry. Place the file at the root of the workspace with the name`config.json` or`.dockerconfigjson`.

| Table 3.24. Supported workspaces for the s2i-dotnet task | Workspace | Description |
| --- | --- | --- |

Show more

Expand

`IMAGE_URL`

`string`

The fully qualified name of the image that was built.

`IMAGE_DIGEST`

`string`

Digest of the image that was built.

| Table 3.25. Results that the s2i-dotnet task returns | Result | Type | Description |
| --- | --- | --- | --- |

Show more

##### s2i-go

The`s2i-go` task builds the source code using the S2I Golang builder image, which is available from the OpenShift Container Platform registry as`image-registry.openshift-image-registry.svc:5000/openshift/golang`.

Example usage of the`s2i-go` task

```yaml
apiVersion: tekton.dev/v1
kind: Pipeline
metadata:
  name: build-and-deploy
spec:
# ...
  tasks:
# ...
  - name: build-s2i
    taskRef:
      resolver: cluster
      params:
      - name: kind
        value: task
      - name: name
        value: s2i-go
      - name: namespace
        value: openshift-pipelines
    workspaces:
    - name: source
      workspace: shared-workspace
# ...
```

Expand

`IMAGE`

The fully qualified name for the container image that the S2I process builds.

`string`

`IMAGE_SCRIPTS_URL`

The URL containing the default assemble and run scripts for the builder image.

`string`

`image:///usr/libexec/s2i`

`ENV_VARS`

An array of values for environment variables to set in the build process, listed in the`KEY=VALUE` format.

`array`

`CONTEXT`

Path to the directory within the`source` workspace to use as the context.

`string`

`.`

`STORAGE_DRIVER`

Set the Buildah storage driver to reflect the settings of the current cluster node settings.

`string`

`vfs`

`FORMAT`

The format of the container to build, either`oci` or`docker`.

`string`

`oci`

`BUILD_EXTRA_ARGS`

Extra parameters for the`build` command when building the image.

`string`

`PUSH_EXTRA_ARGS`

Extra parameters for the`push` command when pushing the image.

`string`

`SKIP_PUSH`

Skip pushing the image to the container registry.

`string`

`false`

`TLS_VERIFY`

The TLS verification flag, normally`true`.

`string`

`true`

`VERBOSE`

Turn on verbose logging; all commands executed are added to the log.

`string`

`false`

`VERSION`

The tag of the image stream, which corresponds to the language version.

`string`

`latest`

| Table 3.26. Supported parameters for the s2i-go task | Parameter | Description | Type | Default value |
| --- | --- | --- | --- | --- |

Show more

Expand

`source`

The application source code, which is the build context for the S2I workflow.

`dockerconfig`

An optional workspace for providing a`.docker/config.json` file that Buildah uses to access the container registry. Place the file at the root of the workspace with the name`config.json` or`.dockerconfigjson`.

| Table 3.27. Supported workspaces for the s2i-go task | Workspace | Description |
| --- | --- | --- |

Show more

Expand

`IMAGE_URL`

`string`

The fully qualified name of the image that was built.

`IMAGE_DIGEST`

`string`

Digest of the image that was built.

| Table 3.28. Results that the s2i-go task returns | Result | Type | Description |
| --- | --- | --- | --- |

Show more

##### s2i-java

The`s2i-java` task builds the source code using the S2I Java builder image, which is available from the OpenShift Container Platform registry as`image-registry.openshift-image-registry.svc:5000/openshift/java`.

Expand

`IMAGE`

The fully qualified name for the container image that the S2I process builds.

`string`

`IMAGE_SCRIPTS_URL`

The URL containing the default assemble and run scripts for the builder image.

`string`

`image:///usr/libexec/s2i`

`ENV_VARS`

An array of values for environment variables to set in the build process, listed in the`KEY=VALUE` format.

`array`

`CONTEXT`

Path to the directory within the`source` workspace to use as the context.

`string`

`.`

`STORAGE_DRIVER`

Set the Buildah storage driver to reflect the settings of the current cluster node settings.

`string`

`vfs`

`FORMAT`

The format of the container to build, either`oci` or`docker`.

`string`

`oci`

`BUILD_EXTRA_ARGS`

Extra parameters for the`build` command when building the image.

`string`

`PUSH_EXTRA_ARGS`

Extra parameters for the`push` command when pushing the image.

`string`

`SKIP_PUSH`

Skip pushing the image to the container registry.

`string`

`false`

`TLS_VERIFY`

The TLS verification flag, normally`true`.

`string`

`true`

`VERBOSE`

Turn on verbose logging; all commands executed are added to the log.

`string`

`false`

`VERSION`

The tag of the image stream, which corresponds to the language version.

`string`

`latest`

| Table 3.29. Supported parameters for the s2i-java task | Parameter | Description | Type | Default value |
| --- | --- | --- | --- | --- |

Show more

Expand

`source`

The application source code, which is the build context for the S2I workflow.

`dockerconfig`

An optional workspace for providing a`.docker/config.json` file that Buildah uses to access the container registry. Place the file at the root of the workspace with the name`config.json` or`.dockerconfigjson`.

| Table 3.30. Supported workspaces for the s2i-java task | Workspace | Description |
| --- | --- | --- |

Show more

Expand

`IMAGE_URL`

`string`

The fully qualified name of the image that was built.

`IMAGE_DIGEST`

`string`

Digest of the image that was built.

| Table 3.31. Results that the s2i-java task returns | Result | Type | Description |
| --- | --- | --- | --- |

Show more

Changes from the`s2i-java``ClusterTask`

- Several new parameters were added.
- The`BUILDER_IMAGE`,`MAVEN_ARGS_APPEND`,`MAVEN_CLEAR_REPO`, and`MAVEN_MIRROR_URL` parameters were removed. You can pass the`MAVEN_ARGS_APPEND`,`MAVEN_CLEAR_REPO`, and`MAVEN_MIRROR_URL` values as environment variables.
- The parameter name`PATH_CONTEXT` was changed to`CONTEXT`.
- The parameter name`TLS_VERIFY` was changed to`TLSVERIFY`.
- The`IMAGE_URL` result was added.

##### s2i-nodejs

The`s2i-nodejs` task builds the source code using the S2I NodeJS builder image, which is available from the OpenShift Container Platform registry as`image-registry.openshift-image-registry.svc:5000/openshift/nodejs`.

Example usage of the`s2i-nodejs` task

```yaml
apiVersion: tekton.dev/v1
kind: Pipeline
metadata:
  name: build-and-deploy
spec:
# ...
  tasks:
# ...
  - name: build-s2i
    taskRef:
      resolver: cluster
      params:
      - name: kind
        value: task
      - name: name
        value: s2i-nodejs
      - name: namespace
        value: openshift-pipelines
    workspaces:
    - name: source
      workspace: shared-workspace
# ...
```

Expand

`IMAGE`

The fully qualified name for the container image that the S2I process builds.

`string`

`IMAGE_SCRIPTS_URL`

The URL containing the default assemble and run scripts for the builder image.

`string`

`image:///usr/libexec/s2i`

`ENV_VARS`

An array of values for environment variables to set in the build process, listed in the`KEY=VALUE` format.

`array`

`CONTEXT`

Path to the directory within the`source` workspace to use as the context.

`string`

`.`

`STORAGE_DRIVER`

Set the Buildah storage driver to reflect the settings of the current cluster node settings.

`string`

`vfs`

`FORMAT`

The format of the container to build, either`oci` or`docker`.

`string`

`oci`

`BUILD_EXTRA_ARGS`

Extra parameters for the`build` command when building the image.

`string`

`PUSH_EXTRA_ARGS`

Extra parameters for the`push` command when pushing the image.

`string`

`SKIP_PUSH`

Skip pushing the image to the container registry.

`string`

`false`

`TLS_VERIFY`

The TLS verification flag, normally`true`.

`string`

`true`

`VERBOSE`

Turn on verbose logging; all commands executed are added to the log.

`string`

`false`

`VERSION`

The tag of the image stream, which corresponds to the language version.

`string`

`latest`

| Table 3.32. Supported parameters for the s2i-nodejs task | Parameter | Description | Type | Default value |
| --- | --- | --- | --- | --- |

Show more

Expand

`source`

The application source code, which is the build context for the S2I workflow.

`dockerconfig`

An optional workspace for providing a`.docker/config.json` file that Buildah uses to access the container registry. Place the file at the root of the workspace with the name`config.json` or`.dockerconfigjson`.

| Table 3.33. Supported workspaces for the s2i-nodejs task | Workspace | Description |
| --- | --- | --- |

Show more

Expand

`IMAGE_URL`

`string`

The fully qualified name of the image that was built.

`IMAGE_DIGEST`

`string`

Digest of the image that was built.

| Table 3.34. Results that the s2i-nodejs task returns | Result | Type | Description |
| --- | --- | --- | --- |

Show more

##### s2i-perl

The`s2i-perl` task builds the source code using the S2I Perl builder image, which is available from the OpenShift Container Platform registry as`image-registry.openshift-image-registry.svc:5000/openshift/perl`.

Example usage of the`s2i-perl` task

```yaml
apiVersion: tekton.dev/v1
kind: Pipeline
metadata:
  name: build-and-deploy
spec:
# ...
  tasks:
# ...
  - name: build-s2i
    taskRef:
      resolver: cluster
      params:
      - name: kind
        value: task
      - name: name
        value: s2i-perl
      - name: namespace
        value: openshift-pipelines
    workspaces:
    - name: source
      workspace: shared-workspace
# ...
```

Expand

`IMAGE`

The fully qualified name for the container image that the S2I process builds.

`string`

`IMAGE_SCRIPTS_URL`

The URL containing the default assemble and run scripts for the builder image.

`string`

`image:///usr/libexec/s2i`

`ENV_VARS`

An array of values for environment variables to set in the build process, listed in the`KEY=VALUE` format.

`array`

`CONTEXT`

Path to the directory within the`source` workspace to use as the context.

`string`

`.`

`STORAGE_DRIVER`

Set the Buildah storage driver to reflect the settings of the current cluster node settings.

`string`

`vfs`

`FORMAT`

The format of the container to build, either`oci` or`docker`.

`string`

`oci`

`BUILD_EXTRA_ARGS`

Extra parameters for the`build` command when building the image.

`string`

`PUSH_EXTRA_ARGS`

Extra parameters for the`push` command when pushing the image.

`string`

`SKIP_PUSH`

Skip pushing the image to the container registry.

`string`

`false`

`TLS_VERIFY`

The TLS verification flag, normally`true`.

`string`

`true`

`VERBOSE`

Turn on verbose logging; all commands executed are added to the log.

`string`

`false`

`VERSION`

The tag of the image stream, which corresponds to the language version.

`string`

`latest`

| Table 3.35. Supported parameters for the s2i-perl task | Parameter | Description | Type | Default value |
| --- | --- | --- | --- | --- |

Show more

Expand

`source`

The application source code, which is the build context for the S2I workflow.

`dockerconfig`

An optional workspace for providing a`.docker/config.json` file that Buildah uses to access the container registry. Place the file at the root of the workspace with the name`config.json` or`.dockerconfigjson`.

| Table 3.36. Supported workspaces for the s2i-perl task | Workspace | Description |
| --- | --- | --- |

Show more

Expand

`IMAGE_URL`

`string`

The fully qualified name of the image that was built.

`IMAGE_DIGEST`

`string`

Digest of the image that was built.

| Table 3.37. Results that the s2i-perl task returns | Result | Type | Description |
| --- | --- | --- | --- |

Show more

##### s2i-php

The`s2i-php` task builds the source code using the S2I PHP builder image, which is available from the OpenShift Container Platform registry as`image-registry.openshift-image-registry.svc:5000/openshift/php`.

Example usage of the`s2i-php` task

```yaml
apiVersion: tekton.dev/v1
kind: Pipeline
metadata:
  name: build-and-deploy
spec:
# ...
  tasks:
# ...
  - name: build-s2i
    taskRef:
      resolver: cluster
      params:
      - name: kind
        value: task
      - name: name
        value: s2i-php
      - name: namespace
        value: openshift-pipelines
    workspaces:
    - name: source
      workspace: shared-workspace
# ...
```

Expand

`IMAGE`

The fully qualified name for the container image that the S2I process builds.

`string`

`IMAGE_SCRIPTS_URL`

The URL containing the default assemble and run scripts for the builder image.

`string`

`image:///usr/libexec/s2i`

`ENV_VARS`

An array of values for environment variables to set in the build process, listed in the`KEY=VALUE` format.

`array`

`CONTEXT`

Path to the directory within the`source` workspace to use as the context.

`string`

`.`

`STORAGE_DRIVER`

Set the Buildah storage driver to reflect the settings of the current cluster node settings.

`string`

`vfs`

`FORMAT`

The format of the container to build, either`oci` or`docker`.

`string`

`oci`

`BUILD_EXTRA_ARGS`

Extra parameters for the`build` command when building the image.

`string`

`PUSH_EXTRA_ARGS`

Extra parameters for the`push` command when pushing the image.

`string`

`SKIP_PUSH`

Skip pushing the image to the container registry.

`string`

`false`

`TLS_VERIFY`

The TLS verification flag, normally`true`.

`string`

`true`

`VERBOSE`

Turn on verbose logging; all commands executed are added to the log.

`string`

`false`

`VERSION`

The tag of the image stream, which corresponds to the language version.

`string`

`latest`

| Table 3.38. Supported parameters for the s2i-php task | Parameter | Description | Type | Default value |
| --- | --- | --- | --- | --- |

Show more

Expand

`source`

The application source code, which is the build context for the S2I workflow.

`dockerconfig`

An optional workspace for providing a`.docker/config.json` file that Buildah uses to access the container registry. Place the file at the root of the workspace with the name`config.json` or`.dockerconfigjson`.

| Table 3.39. Supported workspaces for the s2i-php task | Workspace | Description |
| --- | --- | --- |

Show more

Expand

`IMAGE_URL`

`string`

The fully qualified name of the image that was built.

`IMAGE_DIGEST`

`string`

Digest of the image that was built.

| Table 3.40. Results that the s2i-php task returns | Result | Type | Description |
| --- | --- | --- | --- |

Show more

##### s2i-python

The`s2i-python` task builds the source code using the S2I Python builder image, which is available from the OpenShift Container Platform registry as`image-registry.openshift-image-registry.svc:5000/openshift/python`.

Example usage of the`s2i-python` task

```yaml
apiVersion: tekton.dev/v1
kind: Pipeline
metadata:
  name: build-and-deploy
spec:
# ...
  tasks:
# ...
  - name: build-s2i
    taskRef:
      resolver: cluster
      params:
      - name: kind
        value: task
      - name: name
        value: s2i-python
      - name: namespace
        value: openshift-pipelines
    workspaces:
    - name: source
      workspace: shared-workspace
# ...
```

Expand

`IMAGE`

The fully qualified name for the container image that the S2I process builds.

`string`

`IMAGE_SCRIPTS_URL`

The URL containing the default assemble and run scripts for the builder image.

`string`

`image:///usr/libexec/s2i`

`ENV_VARS`

An array of values for environment variables to set in the build process, listed in the`KEY=VALUE` format.

`array`

`CONTEXT`

Path to the directory within the`source` workspace to use as the context.

`string`

`.`

`STORAGE_DRIVER`

Set the Buildah storage driver to reflect the settings of the current cluster node settings.

`string`

`vfs`

`FORMAT`

The format of the container to build, either`oci` or`docker`.

`string`

`oci`

`BUILD_EXTRA_ARGS`

Extra parameters for the`build` command when building the image.

`string`

`PUSH_EXTRA_ARGS`

Extra parameters for the`push` command when pushing the image.

`string`

`SKIP_PUSH`

Skip pushing the image to the container registry.

`string`

`false`

`TLS_VERIFY`

The TLS verification flag, normally`true`.

`string`

`true`

`VERBOSE`

Turn on verbose logging; all commands executed are added to the log.

`string`

`false`

`VERSION`

The tag of the image stream, which corresponds to the language version.

`string`

`latest`

| Table 3.41. Supported parameters for the s2i-python task | Parameter | Description | Type | Default value |
| --- | --- | --- | --- | --- |

Show more

Expand

`source`

The application source code, which is the build context for the S2I workflow.

`dockerconfig`

An optional workspace for providing a`.docker/config.json` file that Buildah uses to access the container registry. Place the file at the root of the workspace with the name`config.json` or`.dockerconfigjson`.

| Table 3.42. Supported workspaces for the s2i-python task | Workspace | Description |
| --- | --- | --- |

Show more

Expand

`IMAGE_URL`

`string`

The fully qualified name of the image that was built.

`IMAGE_DIGEST`

`string`

Digest of the image that was built.

| Table 3.43. Results that the s2i-python task returns | Result | Type | Description |
| --- | --- | --- | --- |

Show more

##### s2i-ruby

The`s2i-ruby` task builds the source code using the S2I Ruby builder image, which is available from the OpenShift Container Platform registry as`image-registry.openshift-image-registry.svc:5000/openshift/ruby`.

Example usage of the`s2i-ruby` task

```yaml
apiVersion: tekton.dev/v1
kind: Pipeline
metadata:
  name: build-and-deploy
spec:
# ...
  tasks:
# ...
  - name: build-s2i
    taskRef:
      resolver: cluster
      params:
      - name: kind
        value: task
      - name: name
        value: s2i-ruby
      - name: namespace
        value: openshift-pipelines
    workspaces:
    - name: source
      workspace: shared-workspace
# ...
```

Expand

`IMAGE`

The fully qualified name for the container image that the S2I process builds.

`string`

`IMAGE_SCRIPTS_URL`

The URL containing the default assemble and run scripts for the builder image.

`string`

`image:///usr/libexec/s2i`

`ENV_VARS`

An array of values for environment variables to set in the build process, listed in the`KEY=VALUE` format.

`array`

`CONTEXT`

Path to the directory within the`source` workspace to use as the context.

`string`

`.`

`STORAGE_DRIVER`

Set the Buildah storage driver to reflect the settings of the current cluster node settings.

`string`

`vfs`

`FORMAT`

The format of the container to build, either`oci` or`docker`.

`string`

`oci`

`BUILD_EXTRA_ARGS`

Extra parameters for the`build` command when building the image.

`string`

`PUSH_EXTRA_ARGS`

Extra parameters for the`push` command when pushing the image.

`string`

`SKIP_PUSH`

Skip pushing the image to the container registry.

`string`

`false`

`TLS_VERIFY`

The TLS verification flag, normally`true`.

`string`

`true`

`VERBOSE`

Turn on verbose logging; all commands executed are added to the log.

`string`

`false`

`VERSION`

The tag of the image stream, which corresponds to the language version.

`string`

`latest`

| Table 3.44. Supported parameters for the s2i-ruby task | Parameter | Description | Type | Default value |
| --- | --- | --- | --- | --- |

Show more

Expand

`source`

The application source code, which is the build context for the S2I workflow.

`dockerconfig`

An optional workspace for providing a`.docker/config.json` file that Buildah uses to access the container registry. Place the file at the root of the workspace with the name`config.json` or`.dockerconfigjson`.

| Table 3.45. Supported workspaces for the s2i-ruby task | Workspace | Description |
| --- | --- | --- |

Show more

Expand

`IMAGE_URL`

`string`

The fully qualified name of the image that was built.

`IMAGE_DIGEST`

`string`

Digest of the image that was built.

| Table 3.46. Results that the s2i-ruby task returns | Result | Type | Description |
| --- | --- | --- | --- |

Show more

##### skopeo-copy

The`skopeo-copy` task executes the`skopeo copy` command.

Skopeo is a command-line tool for working with remote container image registries without requiring a local container engine or daemon. The`skopeo-copy` command copies an image from one remote registry to another, for example, from an internal registry to a production registry. Skopeo supports authorization on image registries using credentials that you provide.

Example usage of the`skopeo-copy` task

```yaml
apiVersion: tekton.dev/v1
kind: Pipeline
metadata:
  name: build-deploy-image
spec:
# ...
  tasks:
  - name: copy-image
    taskRef:
      resolver: cluster
      params:
      - name: kind
        value: task
      - name: name
        value: skopeo-copy
      - name: namespace
        value: openshift-pipelines
    params:
    - name: SOURCE_IMAGE_URL
      value: "docker://internal.registry/myimage:latest"
    - name: DESTINATION_IMAGE_URL
      value: "docker://production.registry/myimage:v1.0"
    workspaces:
    - name: output
      workspace: shared-workspace
```

Expand

`SOURCE_IMAGE_URL`

Fully qualified name, including tag, of the source container image.

`string`

`DESTINATION_IMAGE_URL`

Fully qualified name, including tag, of the destination image to which Skopeo copies the source image.

`string`

`SRC_TLS_VERIFY`

The TLS verification flag for the source registry, normally`true`.

`string`

`true`

`DEST_TLS_VERIFY`

The TLS verification flag for the destination registry, normally`true`

`string`

`true`

`VERBOSE`

Output debug information to the log.

`string`

`false`

| Table 3.47. Supported parameters for the skopeo-copy task | Parameter | Description | Type | Default value |
| --- | --- | --- | --- | --- |

Show more

Expand

`images_url`

If you want to copy more than one image, use this workspace to provide the image URLs.

| Table 3.48. Supported workspaces for the skopeo-copy task | Workspace | Description |
| --- | --- | --- |

Show more

Expand

`SOURCE_DIGEST`

`string`

The SHA256 digest of the source image.

`DESTINATION_DIGEST`

`string`

The SHA256 digest of the destination image.

| Table 3.49. Results that the skopeo-copy task returns | Result | Type | Description |
| --- | --- | --- | --- |

Show more

Changes from the`skopeo-copy``ClusterTask`

- All parameter names were changed to uppercase.
- The`VERBOSE` parameter was added.
- The workspace name was changed from`images-url` to`images_url`.
- The`SOURCE_DIGEST` and`DESTINATION_DIGEST` results were added.

##### tkn

The`tkn` task performs operations on Tekton resources using tkn.

Example usage of the`tkn` task

```yaml
apiVersion: tekton.dev/v1
kind: PipelineRun
metadata:
 name: tkn-run
spec:
 pipelineSpec:
   tasks:
   - name: tkn-run
     taskRef:
       resolver: cluster
       params:
       - name: kind
         value: task
       - name: name
         value: tkn
       - name: namespace
         value: openshift-pipelines
     params:
     - name: ARGS
```

Expand

`SCRIPT`

The`tkn` CLI script to execute.

`string`

`tkn $@`

`ARGS`

The`tkn` CLI arguments to run.

`array`

`- --help`

| Table 3.50. Supported parameters for the tkn task | Parameter | Description | Type | Default value |
| --- | --- | --- | --- | --- |

Show more

Expand

`kubeconfig_dir`

An optional workspace in which you can provide a`.kube/config` file that contains credentials for accessing the cluster. Place this file at the root of the workspace and name it`kubeconfig`.

| Table 3.51. Supported workspaces for the tkn task | Workspace | Description |
| --- | --- | --- |

Show more

Changes from the`tkn``ClusterTask`

- The`TKN_IMAGE` parameter was removed.
- The workspace name was changed from`kubeconfig` to`kubeconfig_dir`.

##### opc task

The`opc` task simplifies interaction with Tekton resources in Red Hat OpenShift Pipelines by enabling you to run`opc` CLI commands directly in your Pipelines. You can use the`opc` task to perform various operations on Tekton resources, such as listing pipelines, viewing pipeline runs, or managing tasks. The following example shows how to invoke the`opc` task in a PipelineRun:

```yaml
apiVersion: tekton.dev/v1
kind: PipelineRun
metadata:
  name: opc-task-pipeline
spec:
  pipelineRef:
    name: task-opc
  params:
    - name: SCRIPT
      value: "opc $@"
    - name: ARGS
      value: opc-command-you-want-to-execute
```

Replace`opc-command-you-want-to-execute` with the`opc` CLI command you want to run.

The following example shows how to use the`opc` task to list pipelines in your cluster.

```yaml
apiVersion: tekton.dev/v1
kind: Pipeline
metadata:
  name: opc-task-pipeline
spec:
  tasks:
    - name: opc-pipeline-list
      taskRef:
        resolver: cluster
        params:
          - name: kind
            value: task
          - name: name
            value: opc
          - name: namespace
            value: openshift-pipelines
      params:
        - name: SCRIPT
          value: "opc $@"
        - name: ARGS
          value:
            - pipeline
            - list
            - -n
            - $(context.pipelineRun.namespace)
```

To execute the workflow defined in the`opc-task-pipeline` task, create and apply the following`PipelineRun` resource.

```yaml
apiVersion: tekton.dev/v1
kind: PipelineRun
metadata:
  name: opc-task-run
spec:
  pipelineRef:
    name: opc-task-pipeline
```

As a result, the following output is displayed:

```plaintext
[opc-pipeline-list : opc] Running Script /scripts/opc-client.sh
[opc-pipeline-list : opc] NAME                AGE              LAST RUN       STARTED         DURATION   STATUS
[opc-pipeline-list : opc] opc-task-pipeline   15 minutes ago   opc-task-run   5 seconds ago   ---        Running
```

If your container registry requires authentication, create a Kubernetes Secret that describes the following attributes:

```shell-session
kubectl create secret docker-registry imagestreams \
  --docker-server="image-registry.openshift-image-registry.svc:5000" \
  --docker-username="${REGISTRY_USERNAME}" \
  --docker-password="${REGISTRY_TOKEN}"
```

Note

Ensure that the`Secret` is linked to the`ServiceAccount` that runs the`TaskRun` or`PipelineRun`.

### 3.8. Community tasks provided in the OpenShift Pipelines namespaceCopy link

By default, an OpenShift Pipelines installation includes a set of community tasks that you can use in your pipelines. These tasks are located in the OpenShift Pipelines installation namespace, which is normally the`openshift-pipelines` namespace.

##### argocd-task-sync-and-wait

The`argocd-task-sync-and-wait` community task deploys an Argo CD application and waits for it to be healthy.

To do so, it requires the following configurations: * The address of the Argo CD server configured in the`argocd-env-configmap` config map. * The authentication information configured in the`argocd-env-secret` secret.

Example config map with the address information

```yaml
apiVersion: v1
kind: ConfigMap
metadata:
  name: argocd-env-configmap
data:
  ARGOCD_SERVER: https://argocd.example.com
# ...
```

Example secret with the authentication information

```yaml
apiVersion: v1
kind: Secret
metadata:
  name: argocd-env-secret
data:
  # Option 1
  ARGOCD_USERNAME: example_username 
```

1

```yaml

  ARGOCD_PASSWORD: example_password
  # Option 2
  ARGOCD_AUTH_TOKEN: exmaple_token
```

1

Configure either a username and password or an authentication token.

Example usage of the`argocd-task-sync-and-wait` community task

```yaml
apiVersion: tekton.dev/v1
kind: Pipeline
metadata:
  name: argocd-task-sync-and-wait
spec:
  tasks:
    - name: argocd-task-sync-and-wait
      params:
        - name: application-name
          value: example_app_name
        - name: revision
          value: HEAD
        - name: flags
          value: '--'
        - name: argocd-version
          value: v2.2.2
      taskRef:
        kind: Task
        name: argocd-task-sync-and-wait
```

Expand

`application-name`

Name of the application to deploy.

`revision`

Revision to deploy.

`HEAD`

`flags`

`--`

`argocd-version`

Version of Argo CD.

`v2.2.2`

| Table 3.52. Supported parameters for the argocd-task-sync-and-wait community task | Parameter | Description | Default value |
| --- | --- | --- | --- |

Show more

##### helm-upgrade-from-repo

The`helm-upgrade-from-repo` community task installs or upgrades a Helm chart in your OpenShift Container Platform cluster based on the given Helm repository and chart.

Example usage of the`helm-upgrade-from-repo` community task

```yaml
apiVersion: tekton.dev/v1
kind: Pipeline
metadata:
  name: helm-upgrade-from-repo
spec:
  tasks:
    - name: helm-upgrade-from-repo
      params:
        - name: helm_repo
          value: example_helm_repository
        - name: chart_name
          value: example_chart_name
        - name: release_version
          value: v1.0.0
        - name: release_name
          value: helm-release
        - name: release_namespace
          value: ''
        - name: overwrite_values
          value: ''
        - name: helm_image
          value: 'docker.io/lachlanevenson/k8s-helm@sha256:5c792f29950b388de24e7448d378881f68b3df73a7b30769a6aa861061fd08ae'
      taskRef:
        kind: Task
        name: helm-upgrade-from-repo
```

Expand

`helm_repo`

Helm repository.

`chart_name`

Helm chart name to be deployed.

`release_version`

Helm release version in semantic versioning format.

`v1.0.0`

`release_name`

Helm release name.

`helm-release`

`release_namespace`

Helm release namespace.

`""`

`overwrite_values`

Configuration parameters to overwrite, comma-separated. For example:`autoscaling.enabled=true,replicas=1`

`""`

`helm_image`

Helm image to be used.

`docker.io/lachlanevenson/k8s-helm@sha256:5c792f29950b388de24e7448d378881f68b3df73a7b30769a6aa861061fd08ae`

| Table 3.53. Supported parameters for the helm-upgrade-from-repo community task | Parameter | Description | Default value |
| --- | --- | --- | --- |

Show more

##### helm-upgrade-from-source

The`helm-upgrade-from-source` community task installs and upgrades a Helm chart in your OpenShift Container Platform cluster based on the given chart and source workspace.

Example usage of the`helm-upgrade-from-source` community task

```yaml
apiVersion: tekton.dev/v1
kind: Pipeline
metadata:
  name: helm-upgrade-from-source
spec:
  tasks:
    - name: helm-upgrade-from-source
      params:
        - name: charts_dir
          value: example_directory_path
        - name: release_version
          value: v1.0.0
        - name: release_name
          value: helm-release
        - name: release_namespace
          value: ''
        - name: overwrite_values
          value: ''
        - name: values_file
          value: values.yaml
        - name: helm_image
          value: 'docker.io/lachlanevenson/k8s-helm@sha256:5c792f29950b388de24e7448d378881f68b3df73a7b30769a6aa861061fd08ae'
        - name: upgrade_extra_params
          value: ''
      taskRef:
        kind: Task
        name: helm-upgrade-from-source
      workspaces:
        - name: source
          workspace: shared-workspace
  #...
```

Expand

`charts_dir`

Directory in the source workspace that contains the Helm chart.

`release_version`

Helm release version in semantic versioning format.

`v1.0.0`

`release_name`

Helm release name.

`helm-release`

`release_namespace`

Helm release namespace.

`""`

`overwrite_values`

Configuration parameters to overwrite, comma-separated. For example:`autoscaling.enabled=true,replicas=1`

`""`

`values_file`

File with configuration parameters for Helm.

`values.yaml`

`helm_image`

Helm image to be used.

`docker.io/lachlanevenson/k8s-helm@sha256:5c792f29950b388de24e7448d378881f68b3df73a7b30769a6aa861061fd08ae`

`upgrade_extra_params`

Extra parameters passed for the Helm upgrade command.

`""`

| Table 3.54. Supported parameters for the helm-upgrade-from-source community task | Parameter | Description | Default value |
| --- | --- | --- | --- |

Show more

Expand

`source`

The workspace that contains the Helm chart.

| Table 3.55. Supported workspaces for the helm-upgrade-from-source community task | Workspace | Description |
| --- | --- | --- |

Show more

##### jib-maven

The`jib-maven` community task builds Java, Kotlin, Groovy, and Scala sources into a container image by using the Jib tool for Maven projects.

Example usage of the`jib-maven` community task

```yaml
apiVersion: tekton.dev/v1
kind: Pipeline
metadata:
  name: jib-maven
spec:
  tasks:
    - name: jib-maven
      params:
        - name: IMAGE
          value: example_image
        - name: MAVEN_IMAGE
          value: 'registry.redhat.io/ubi9/openjdk-17@sha256:78613bdf887530100efb6ddf92d2a17f6176542740ed83e509cdc19ee7c072d6'
        - name: DIRECTORY
          value: .
        - name: CACHE
          value: empty-dir-volume
        - name: INSECUREREGISTRY
          value: 'false'
        - name: CACERTFILE
          value: service-ca.crt
      taskRef:
        kind: Task
        name: jib-maven
      workspaces:
        - name: source
          workspace: shared-workspace
  #...
```

Expand

`IMAGE`

Name of the image to build.

`MAVEN_IMAGE`

Maven base image.

`registry.redhat.io/ubi9/openjdk-17@sha256:78613bdf887530100efb6ddf92d2a17f6176542740ed83e509cdc19ee7c072d6`

`DIRECTORY`

Directory containing the app, relative to the source repository root.

`.`

`CACHE`

Name of the volume for caching Maven artifacts and base image layers.

`empty-dir-volume`

`INSECUREREGISTRY`

Allow an insecure registry.

`false`

`CACERTFILE`

Certificate authority (CA) bundle file name for an insecure registry service.

`service-ca.crt`

| Table 3.56. Supported parameters for the jib-maven community task | Parameter | Description | Default value |
| --- | --- | --- | --- |

Show more

Expand

`source`

Workspace that contains the Maven project.

`sslcertdir`

Optional workspace that contains SSL/TLS certificates.

| Table 3.57. Supported workspaces for the jib-maven community task | Workspace | Description |
| --- | --- | --- |

Show more

Expand

`IMAGE_DIGEST`

`string`

Digest of the image that was built.

| Table 3.58. Results that the jib-maven task returns | Result | Type | Description |
| --- | --- | --- | --- |

Show more

Changes from the`jib-maven` community cluster task

- The default values for the`IMAGE` and`MAVEN_IMAGE` parameters were changed.

##### kubeconfig-creator

The`kubeconfig-creator` community task creates a`kubeconfig` file that other tasks in the pipeline can use for accessing different clusters.

Example usage of the`kubeconfig-creator` community task

```yaml
apiVersion: tekton.dev/v1
kind: Pipeline
metadata:
  name: kubeconfig-creator
spec:
  tasks:
    - name: kubeconfig-creator
      params:
        - name: name
          value: example_cluster
        - name: url
          value: https://cluster.example.com
        - name: username
          value: example_username
        - name: password
          value: example_password
        - name: cadata
          value: ''
        - name: clientKeyData
          value: ''
        - name: clientCertificateData
          value: ''
        - name: namespace
          value: ''
        - name: token
          value: ''
        - name: insecure
          value: 'false'
      taskRef:
        kind: Task
        name: kubeconfig-creator
      workspaces:
        - name: output
          workspace: shared-workspace
  #...
```

Expand

`name`

Name of the cluster to access.

`url`

Address of the cluster to access.

`username`

Username for Basic HTTP authentication to the cluster.

`password`

Password for Basic HTTP authentication to the cluster.

`""`

`cadata`

PEM-encoded certificate authority (CA) certificates.

`""`

`clientKeyData`

PEM-encoded data from a client key file for TLS.

`""`

`clientCertificateData`

PEM-encoded data from a client certification file for TLS.

`""`

`namespace`

Default namespace to use on unspecified requests.

`""`

`token`

Bearer token for authentication to the cluster.

`""`

`insecure`

To indicate whether a server should be accessed without verifying the TLS certificate.

`false`

| Table 3.59. Supported parameters for the kubeconfig-creator community task | Parameter | Description | Default value |
| --- | --- | --- | --- |

Show more

Expand

`output`

The workspace where the`kubeconfig-creator` task stores the`kubeconfig` file.

| Table 3.60. Supported workspaces for the kubeconfig-creator community task | Workspace | Description |
| --- | --- | --- |

Show more

##### pull-request

You can use the`pull-request` community task to interact with a source control management (SCM) system through an abstracted interface.

This community task works with both public SCM instances and self-hosted or enterprise GitHub or GitLab instances.

In download mode, this task populates the`pr` workspace with the state of the existing pull request, including the`.MANIFEST` file.

In upload mode, this task compares the contents of the`pr` workspace, including the`.MANIFEST` file, with the content of the pull request and, if the content is different, updates the pull request to match the`pr` workspace.

Example usage of the`pull-request` community task

```yaml
apiVersion: tekton.dev/v1
kind: Pipeline
metadata:
  name: pull-request
spec:
spec:
  tasks:
    - name: pull-request
      params:
        - name: mode
          value: upload
        - name: url
          value: https://github.com/example/pull/xxxxx
        - name: provider
          value: github
        - name: secret-key-ref
          value: example_secret
        - name: insecure-skip-tls-verify
          value: 'false'
      taskRef:
        kind: Task
        name: pull-request
      workspaces:
        - name: pr
          workspace: shared-workspace
  #...
```

Expand

`mode`

If set to`download`, the state of the pull request at`url` is fetched. If set to`upload` then the pull request at`url` is updated.

`url`

URL of the pull request.

`provider`

Type of the SCM system. The supported values are`github` or`gitlab`.

`secret-key-ref`

Name of a`Secret` object of`Opaque` type that contains a key called`token` with a base64 encoded SCM token.

`insecure-skip-tls-verify`

If set to`true`, the certificate validation is disabled.

`false`

| Table 3.61. Supported parameters for the pull-request community task | Parameter | Description | Default value |
| --- | --- | --- | --- |

Show more

Expand

`pr`

The workspace that contains the state of the pull request.

| Table 3.62. Supported workspaces for the pull-request community task | Workspace | Description |
| --- | --- | --- |

Show more

##### trigger-jenkins-job

You can use the`trigger-jenkins-job` community task to trigger a Jenkins job by using a`curl` request.

Example usage of the`trigger-jenkins-job` community task

```yaml
apiVersion: tekton.dev/v1
kind: Pipeline
metadata:
  name: trigger-jenkins-job
spec:
  tasks:
    - name: trigger-jenkins-job
      params:
        - name: JENKINS_HOST_URL
          value: example_host_URL
        - name: JOB_NAME
          value: example_job_name
        - name: JENKINS_SECRETS
          value: jenkins-credentials
        - name: JOB_PARAMS
          value:
            - example_param
      taskRef:
        kind: Task
        name: trigger-jenkins-job
      workspaces:
        - name: source
          workspace: shared-workspace
  # ...
```

Expand

`JENKINS_HOST_URL`

Server URL on which Jenkins is running.

`JOB_NAME`

Jenkins Job which needs to be triggered.

`JENKINS_SECRETS`

Jenkins secret containing credentials.

`jenkins-credentials`

`JOB_PARAMS`

Extra arguments to append as a part of the`curl` request.

`""`

| Table 3.63. Supported parameters for the trigger-jenkins-job community task | Parameter | Description | Default value |
| --- | --- | --- | --- |

Show more

Expand

`source`

The workspace which can be used to mount files which can be sent through the`curl` request to the Jenkins job.

| Table 3.64. Supported workspaces for the trigger-jenkins-job community task | Workspace | Description |
| --- | --- | --- |

Show more

### 3.9. Step action definitions provided with OpenShift PipelinesCopy link

OpenShift Pipelines provides standard`StepAction` definitions that you can use in your tasks. Use the cluster resolver to reference these definitions.

##### git-clone

The`git-clone` step action uses Git to initialize and clone a remote repository on a workspace. You can use this step action to define a task that clones a repository at the start of a pipeline that builds or otherwise processes this source code.

Example usage of the`git-clone` step action in a task

```yaml
apiVersion: tekton.dev/v1
kind: Task
metadata:
  name: clone-repo-anon
spec:
# ...
  steps:
  - name: clone-repo-step
    ref:
      resolver: cluster
      params:
      - name: name
        value: git-clone
      - name: namespace
        value: openshift-pipelines
      - name: kind
        value: stepaction
    params:
    - name: URL
      value: $(params.url)
    - name: OUTPUT_PATH
      value: $(workspaces.output.path)
```

Expand

`OUTPUT_PATH`

A directory for the fetched Git repository. Cloned repo data is placed in the root of the directory or in the relative path defined by the`SUBDIRECTORY` parameter

`string`

`SSH_DIRECTORY_PATH`

A`.ssh` directory with the private key,`known_hosts`,`config`, and other files as necessary. If you provide this directory, the task uses it for authentication to the Git repository. Bind the workspace providing this directory to a`Secret` resource for secure storage of authentication information.

`string`

`BASIC_AUTH_PATH`

A directory containing a`.gitconfig` and`.git-credentials` files. If you provide this directgory, the task uses it for authentication to the Git repository. Use a`SSH_DIRECTORY_PATH` directory for authentication instead of`BASIC_AUTH_PATH` whenever possible. Bind the workspace providing this directory to a`Secret` resource for secure storage of authentication information.

`string`

`SSL_CA_DIRECTORY_PATH`

A workspace containing CA certificates. If you provide this workspace, Git uses these certificates to verify the peer when interacting with remote repositories using HTTPS.

`string`

`CRT_FILENAME`

Certificate authority (CA) bundle filename in the`ssl-ca-directory` workspace.

`string`

`ca-bundle.crt`

`HTTP_PROXY`

HTTP proxy server (non-TLS requests).

`string`

`HTTPS_PROXY`

HTTPS proxy server (TLS requests).

`string`

`NO_PROXY`

Opt out of proxying HTTP/HTTPS requests.

`string`

`SUBDIRECTORY`

Relative path in the`output` workspace where the task places the Git repository.

`string`

`USER_HOME`

Absolute path to the Git user home directory in the pod.

`string`

`/home/git`

`DELETE_EXISTING`

Delete the contents of the default workspace, if they exist, before running the Git operations.

`string`

`true`

`VERBOSE`

Log the executed commands.

`string`

`false`

`SSL_VERIFY`

The global`http.sslVerify` value. Do not set this parameter to`false` unless you trust the remote repository.

`string`

`true`

`URL`

Git repository URL.

`string`

`REVISION`

The revision to check out, for example, a branch or tag.

`string`

`main`

`REFSPEC`

The`refspec` string for the repository that the task fetches before checking out the revision.

`string`

`SUBMODULES`

Initialize and fetch Git submodules.

`string`

`true`

`DEPTH`

Number of commits to fetch, a "shallow clone" is a single commit.

`string`

`1`

`SPARSE_CHECKOUT_DIRECTORIES`

List of directory patterns, separated by commas, for performing a "sparse checkout".

`string`

| Table 3.65. Supported parameters for the git-clone step action | Parameter | Description | Type | Default value |
| --- | --- | --- | --- | --- |

Show more

Expand

`COMMIT`

`string`

The SHA digest of the commit that is at the HEAD of the current branch in the cloned Git repository.

`URL`

`string`

The URL of the repository that was cloned.

`COMMITTER_DATE`

`string`

The epoch timestamp of the commit that is at the HEAD of the current branch in the cloned Git repository.

| Table 3.66. Results that the git-clone step action returns | Result | Type | Description |
| --- | --- | --- | --- |

Show more

##### cache-upload and cache-fetch

Important

Using the`cache-upload` and`cache-fetch` step actions is a Technology Preview feature only. Technology Preview features are not supported with Red Hat production service level agreements (SLAs) and might not be functionally complete. Red Hat does not recommend using them in production. These features provide early access to upcoming product features, enabling customers to test functionality and provide feedback during the development process.

For more information about the support scope of Red Hat Technology Preview features, see [Technology Preview Features Support Scope](https://access.redhat.com/support/offerings/techpreview/).

Use the`cache-upload` and`cache-fetch` step actions to preserve the cache directory where a build process keeps its dependencies, storing it in an Amazon Simple Storage Service (S3) bucket, Google Cloud Services (GCS) bucket, or an Open Container Initiative (OCI) repository.

When you use the`cache-upload` step action, the step action calculates a hash based on certain files in your build. You must provide a regular expression to select these files. The`cache-upload` step action stores an image that contains the content of your cache directory, indexed with the hash.

When you use the`cache-fetch` step action, the step action calculates the same hash. Then it checks whether a cached image for this hash is already available. If the image is available, the step action populates your cache directory with the cached content. If the image is not available, the directory remains as it was.

After using the`cache-fetch` step action, you can run the build process. If the cache is successfully fetched, it includes the dependencies that the build process downloaded previously. If the cache was not fetched, the build process downloads dependencies through its normal procedure.

The result of`cache-fetch` indicates whether a cached image was fetched. The subsequent`cache-upload` step action can use the result and skip uploading a new cache image if the cache for the current hash was already available.

The following example task retrieves the source from a repository, fetches the cache (if available), runs a Maven build, and then, if the cache was not fetched, uploads the new cached image of the build directory.

Example usage of the`cache-fetch` and`cache-upload` step actions in a task

```yaml
apiVersion: tekton.dev/v1
kind: Task
metadata:
  name: java-demo-task
spec:
  workspaces:
  - name: source
  params:
  - name: repo_url
    type: string
    default: https://github.com/sample-organization/sample-java-project.git
  - name: revision
    type: string
    default: main
  - name: registry
    type: string
    default: image-registry.openshift-image-registry.svc:5000/sample-project/mvn-cache
  - name: image
    type: string
    default: openjdk:latest
  - name: buildCommand
    type: string
    default: "maven -Dmaven.repo.local=${LOCAL_CACHE_REPO} install"
  - name: cachePatterns
    type: array
    default: ["**pom.xml"]
  - name: force-cache-upload
    type: string
    default: "false"
  steps:
   - name: create-repo
     image: $(params.image)
     script: |
       mkdir -p $(workspaces.source.path)/repo
       chmod 777 $(workspaces.source.path)/repo
   - name: fetch-repo
     ref:
       resolver: cluster
       params:
       - name: name
         value: git-clone
       - name: namespace
         value: openshift-pipelines
       - name: kind
         value: stepaction
     params:
       - name: OUTPUT_PATH
         value: $(workspaces.source.path)/repo
       - name: URL
         value: $(params.repo_url)
       - name: REVISION
         value: $(params.revision)
   - name: cache-fetch
     ref:
       resolver: cluster
       params:
       - name: name
         value: cache-fetch
       - name: namespace
         value: openshift-pipelines
       - name: kind
         value: stepaction
     params:
     - name: PATTERNS
       value: $(params.cachePatterns)
     - name: SOURCE
       value: oci://$(params.registry):{{hash}}
     - name: CACHE_PATH
       value: $(workspaces.source.path)/cache
     - name: WORKING_DIR
       value: $(workspaces.source.path)/repo
   - name: run-build
     image: $(params.image)
     workingDir: $(workspaces.source.path)/repo
     env:
       - name: LOCAL_CACHE_REPO
         value: $(workspaces.source.path)/cache/repo
     script: |
       set -x
       $(params.buildCommand)
       echo "Cache size is $(du -sh $(workspaces.source.path)/cache)"
   - name: cache-upload
     ref:
       resolver: cluster
       params:
       - name: name
         value: cache-upload
       - name: namespace
         value: openshift-pipelines
       - name: kind
         value: stepaction
     params:
       - name: PATTERNS
         value: $(params.cachePatterns)
       - name: TARGET
         value: oci://$(params.registry):{{hash}}
       - name: CACHE_PATH
         value: $(workspaces.source.path)/cache
       - name: WORKING_DIR
         value: $(workspaces.source.path)/repo
       - name: FORCE_CACHE_UPLOAD
         value: $(params.force-cache-upload)
```

Expand

`PATTERNS`

Regular expression for selecting files to compute the hash. For example, for a Go project, you can use`go.mod` files to compute the cache, and then the value of this parameter is`**/go.sum`(where`**` accounts for subdirectories of any depth).

`array`

`SOURCE`

Source URI for fetching the cache; use`{{hash}}` to specify the cache hash. The supported types are`oci`(example:`oci://quay.io/example-user/go-cache:{{hash}}`) and`s3`(example:`s3://example-bucket/{{hash}}`)

`string`

`CACHE_PATH`

Path for extracting the cache content. Normally this path is in a workspace.

`string`

`WORKING_DIR`

Path where the files for calculating the hash are located.

`string`

`INSECURE`

If`"true"`, use insecure mode for fetching the cache.

`string`

`"false"`

`GOOGLE_APPLICATION_CREDENTIALS`

The path where Google credentials are located. Ignored if empty.

`string`

`AWS_CONFIG_FILE`

Path to the AWS configuration file. Ignored if empty.

`string`

`AWS_SHARED_CREDENTIALS_FILE`

Path to the AWS credentials file. Ignored if empty.

`string`

`BLOB_QUERY_PARAMS`

Blob query parameters for configuring S3, GCS, or Azure. Use these optional parameters for additional features such as S3 acceleration, FIPS, or path-style addressing.

`string`

| Table 3.67. Supported parameters for the cache-fetch step action | Parameter | Description | Type | Default value |
| --- | --- | --- | --- | --- |

Show more

Expand

`fetched`

`string`

`"true"` if the step has fetched the cache or`"false"` if the step has not fetched the cache.

| Table 3.68. Results that the cache-fetch step action returns | Result | Type | Description |
| --- | --- | --- | --- |

Show more

Expand

`PATTERNS`

Regular expression for selecting files to compute the hash. For example, for a Go project, you can use`go.mod` files to compute the cache, and then the value of this parameter is`**/go.sum`(where`**` accounts for subdirectories of any depth).

`array`

`TARGET`

Target URI for uploading the cache; use`{{hash}}` to specify the cache hash. The supported types are`oci`(example:`oci://quay.io/example-user/go-cache:{{hash}}`) and`s3`(example:`s3://example-bucket/{{hash}}`)

`string`

`CACHE_PATH`

Path for cache content, which the step packs into the image. Normally this path is in a workspace.

`string`

`WORKING_DIR`

Path where the files for calculating the hash are located.

`string`

`INSECURE`

If`"true"`, use insecure mode for uploading the cache.

`string`

`"false"`

`FETCHED`

If`"true"`, the cache for this hash was already fetched.

`string`

`"false"`

`FORCE_CACHE_UPLOAD`

If`"true"`, the step uploads the cache even if it was fetched previously.

`string`

`"false"`

`GOOGLE_APPLICATION_CREDENTIALS`

The path where Google credentials are located. Ignored if empty.

`string`

`AWS_CONFIG_FILE`

Path to the AWS configuration file. Ignored if empty.

`string`

`AWS_SHARED_CREDENTIALS_FILE`

Path to the AWS credentials file. Ignored if empty.

`string`

`BLOB_QUERY_PARAMS`

Blob query parameters for configuring S3, GCS, or Azure. Use these optional parameters for additional features such as S3 acceleration, FIPS, or path-style addressing.

`string`

| Table 3.69. Supported parameters for the cache-upload step action | Parameter | Description | Type | Default value |
| --- | --- | --- | --- | --- |

Show more

The`cache-upload` step action returns no results.

### 3.10. About non-versioned and versioned tasks and step actionsCopy link

The`openshift-pipelines` namespace includes versioned tasks and step actions alongside standard non-versioned tasks and step actions. For example, installing the Red Hat OpenShift Pipelines Operator version 1.18 creates the following items:

- `buildah-1-18-0` versioned task
- `buildah` non-versioned task
- `git-clone-1-18-0` versioned`StepAction` definition
- `git-clone` non-versioned`StepAction` definition

Non-versioned and versioned tasks and step actions have the same metadata, behavior, and specifications, including`params`,`workspaces`, and`steps`. However, they behave differently when you disable them or upgrade the Operator.

Before adopting non-versioned or versioned tasks and step actions as a standard in production environments, cluster administrators might consider their advantages and disadvantages.

Expand

Non-versioned tasks and step actions

- If you prefer deploying pipelines with the latest updates and bug fixes, use non-versioned tasks and step actions.
- Upgrading the Operator upgrades the non-versioned tasks and step actions, which consumes fewer resources than multiple versioned tasks and step actions.

- If you deploy pipelines that use non-versioned tasks and step actions, they might break after an Operator upgrade if the automatically upgraded tasks and step actions are not backward-compatible.

Versioned tasks and step actions

- If you prefer pipelines in production that do not change after a version update, use versioned tasks and step actions.
- When you install a new version of the Operator, the versioned tasks and step actions from the current minor version and the immediate previous minor version are retained.

- If you continue using the earlier versions, you might miss the latest features and critical security updates.
- After an upgrade, the Operator cannot manage the earlier versioned tasks and step actions. If you delete the earlier versions manually, you cannot restore them.
- After an upgrade, the Operator can delete versioned tasks and step actions from versions earlier than the previous minor release. When you install a new version of and the versioned tasks or step actions from an earlier version are deleted, pipelines that use the versioned tasks from the earlier version stop working.

| Table 3.70. Advantages and disadvantages of non-versioned and versioned tasks and step actions | Advantages | Disadvantages |
| --- | --- | --- |

Show more

Non-versioned and versioned tasks and step actions have different naming conventions, and the Red Hat OpenShift Pipelines Operator upgrades them differently.

Expand

Non-versioned tasks and step actions

Non-versioned tasks and step actions only contain the name of the task or step action. For example, the name of the non-versioned task of Buildah installed with Operator v1.18 is`buildah`.

When you upgrade the Operator, it updates the non-versioned tasks and step actions with the latest changes. The name remains unchanged.

Versioned tasks and step actions

Versioned tasks and step actions contain the name, followed by the version as a suffix. For example, the name of the versioned task of Buildah installed with Operator v1.18 is`buildah-1-18-0`.

Upgrading the Operator installs the latest version of versioned tasks and step actions, retains the immediate previous version, and deletes the earlier versions. The latest version corresponds to the upgraded Operator. For example, installing Operator 1.18 installs the`buildah-1-18-0` task, retains the`buildah-1-17-0` task, and deletes earlier versions such as`buildah-1-16-0`.

| Table 3.71. Differences between non-versioned and versioned tasks and step actions | Nomenclature | Upgrade |
| --- | --- | --- |

Show more

## Chapter 4. Using manual approval in OpenShift PipelinesCopy link

You can specify a manual approval task in a pipeline. When the pipeline reaches this task, it pauses and awaits approval from one or several OpenShift Container Platform users. If any of the users chooses to rejects the task instead of approving it, the pipeline fails. The manual approval gate controller provides this functionality.

Important

The manual approval gate is a Technology Preview feature only. Technology Preview features are not supported with Red Hat production service level agreements (SLAs) and might not be functionally complete. Red Hat does not recommend using them in production. These features provide early access to upcoming product features, enabling customers to test functionality and provide feedback during the development process.

For more information about the support scope of Red Hat Technology Preview features, see [Technology Preview Features Support Scope](https://access.redhat.com/support/offerings/techpreview/).

### 4.1. Enabling the manual approval gate controllerCopy link

To use manual approval tasks, you must first enable the manual approval gate controller.

Prerequisites

- You installed the Red Hat OpenShift Pipelines Operator in your cluster.
- You are logged on to the cluster using the`oc` command-line utility.
- You have administrator permissions for the`openshift-pipelines` namespace.

Procedure

Create a file named`manual-approval-gate-cr.yaml` with the following manifest for the`ManualApprovalGate` custom resource (CR):

```yaml
apiVersion: operator.tekton.dev/v1alpha1
kind: ManualApprovalGate
metadata:
  name: manual-approval-gate
spec:
  targetNamespace: openshift-pipelines
```

Apply the`ManualApprovalGate` CR by entering the following command:

```shell-session
$ oc apply -f manual-approval-gate-cr.yaml
```

Verify that the manual approval gate controller is running by entering the following command:

```shell-session
$ oc get manualapprovalgates.operator.tekton.dev
```

Example output

```shell-session
NAME                   VERSION    READY   REASON
manual-approval-gate   v0.1.0	    True
```

Ensure that the`READY` status is`True`. If it is not`True`, wait for a few minutes and enter the command again. The controller might take some time to reach a ready state.

### 4.2. Specifying a manual approval taskCopy link

You can specify a manual approval task in your pipeline to pause execution until specific stakeholders provide authorization. When a pipeline run reaches an approval task, the process stops and waits for approval from the designated users or groups.

Prerequisites

- You have enabled the manual approver gate controller.
- You have created a YAML specification of a pipeline.
- You have created the groups and added the required users before using group approval.

Procedure

Add an`ApprovalTask` to your pipeline by entering the task definition in your`Pipeline` definition.

The following example shows how to include an approval task in a typical deployment pipeline:

```yaml
apiVersion: tekton.dev/v1
kind: Pipeline
metadata:
  name: deployment-pipeline
spec:
  tasks:
  - name: build
    taskRef:
      name: build-task

  - name: test
    taskRef:
      name: test-task
    runAfter: [build]

  - name: approval-gate
    taskRef:
      apiVersion: openshift-pipelines.org/v1alpha1
      kind: ApprovalTask
    params:
    - name: approvers
      value:
      - user1
      - user2
      - group:security-team
    - name: numberOfApprovalsRequired
      value: "2"
    - name: description
      value: "Approve deployment to production"
    runAfter: [test]

  - name: deploy
    taskRef:
      name: deploy-task
    runAfter: [approval-gate]
```

Optionally, specify status fields for the approval, such as the approval state and approver responses. These fields allow you to track the approval lifecycle during pipeline execution.

The following example demonstrates an approval task with status information:

```yaml
apiVersion: tekton.dev/v1
kind: Pipeline
metadata:
  name: example-manual-approval-pipeline
spec:
  tasks:
  - name: example-manual-approval-task
    taskRef:
      apiVersion: openshift-pipelines.org/v1alpha1
      kind: ApprovalTask
    params:
    - name: approvers
      value:
      - user1
      - user2
      - user3
    - name: description
      value: Example manual approval task - please approve or reject
    - name: numberOfApprovalsRequired
      value: '2'
    - name: timeout
      value: '60m'
status:
  state: pending
  approvers:
  - user1
  - user2
  - user3
  approvalsRequired: 2
  approvalsReceived: 0
  approversResponse: []
  startTime: "2024-01-15T10:30:00Z"
```

The following table describes the parameters for a manual approval task.

Expand

`approvers`

array

The OpenShift Container Platform users or groups who can approve the task.

`description`

string

Optional: A description of the approval task. OpenShift Pipelines displays this text to the user who can approve or reject the task.

`numberOfApprovalsRequired`

string

The number of approvals required from different approvers.

`timeout`

string

Optional: The timeout period for approval. If the required approvals are not received within this period, the pipeline run fails. The default timeout is 1 hour.

`state`

string

The current approval state:`pending`,`approved`, or`rejected`.

`approvalsReceived`

int

The number of approvals received so far.

`approversResponse`

[]ApproverState

Detailed response from each approver.

| | Parameter | Type | Description |
| --- | --- | --- | --- |

Show more

You can define approval tasks for different workflows depending on your requirements. The following are a few examples of approval configurations.

The following is an example for multi user approval.

```yaml
apiVersion: tekton.dev/v1
kind: Pipeline
metadata:
  name: deployment-pipeline
spec:
  tasks:
  - name: build
    taskRef:
      name: build-task
  - name: test
    taskRef:
      name: test-task
    runAfter: [build]
  - name: approval-gate
    taskRef:
      apiVersion: openshift-pipelines.org/v1alpha1
      kind: ApprovalTask
    params:
    - name: approvers
      value:
      - <user_1>
      - <user_2>
      - <user_3>
    - name: numberOfApprovalsRequired
      value: "2"
    - name: description
      value: "Approve deployment to production"
    runAfter: [test]
  - name: deploy
    taskRef:
      name: deploy-task
    runAfter: [approval-gate]
```

The following is an example for a group-based user approval.

```yaml
apiVersion: tekton.dev/v1
kind: Pipeline
metadata:
  name: deployment-pipeline
spec:
  tasks:
  - name: build
    taskRef:
      name: build-task
  - name: test
    taskRef:
      name: test-task
    runAfter: [build]
  - name: approval-gate
    taskRef:
      apiVersion: openshift-pipelines.org/v1alpha1
      kind: ApprovalTask
    params:
    - name: approvers
      value:
      - group:qa-team
      - group:security-team
    - name: numberOfApprovalsRequired
      value: "2"
    - name: description
      value: "Approve deployment to production"
    runAfter: [test]
  - name: deploy
    taskRef:
      name: deploy-task
    runAfter: [approval-gate]
```

The following is an example for mixed user and group approvals.

```yaml
apiVersion: tekton.dev/v1
kind: Pipeline
metadata:
  name: deployment-pipeline
spec:
  tasks:
  - name: build
    taskRef:
      name: build-task
  - name: test
    taskRef:
      name: test-task
    runAfter: [build]
  - name: approval-gate
    taskRef:
      apiVersion: openshift-pipelines.org/v1alpha1
      kind: ApprovalTask
    params:
    - name: approvers
      value:
      - <tech-lead>
      - <group:qa-team>
      - <group:security-team>
    - name: numberOfApprovalsRequired
      value: "2"
    - name: description
      value: "Approve deployment to production"
    runAfter: [test]
  - name: deploy
    taskRef:
      name: deploy-task
    runAfter: [approval-gate]
```

### 4.3. Approving a manual approval taskCopy link

When you run a pipeline that includes an approval task and the execution reaches the approval task, the pipeline run pauses and waits for user approval or rejection.

Users can approve or reject the task by using either the web console or the`opc` command line utility.

If any one of the approvers configured in the task rejects the task, the pipeline run fails.

If one user approves the task but the configured number of approvals is still not reached, the same user can change to rejecting the task and the pipeline run fails

#### 4.3.1. Approving a manual approval task by using the web consoleCopy link

You can approve or reject a manual approval task by using the OpenShift Container Platform web console.

If you are listed as an approver in a manual approval task and a pipeline run reaches this task, the web console displays a notification. You can view a list of tasks that require your approval and approve or reject these tasks.

Prerequisites

- You enabled the OpenShift Pipelines console plugin.

Procedure

View a list of tasks that you can approve by completing one of the following actions:

- When a notification about a task requiring your approval displays, click Go to Approvals tab in this notification.
- In the Administrator perspective menu, select Pipelines → Pipelines and then click the Approvals tab.
- In the Developer perspective menu, select Pipelines and then click the Approvals tab.
- In the PipelineRun details window, in the Details tab, click the rectangle that represents the manual approval task. The list displays only the approval for this task.
- In the PipelineRun details window, click the ApprovalTasks tab. The list displays only the approval for this pipeline run.

In the list of approval tasks, in the line that represents the task that you want to approve, click the icon and then select one of the following options:

- To approve the task, select Approve.
- To reject the task, select Reject.

#### 4.3.2. Approving a manual approval task by using the command lineCopy link

You can approve or reject a manual approval task by using the`opc` command-line utility. You can view a list of tasks for which you are an approver and approve or reject the tasks that are pending approval.

Prerequisites

- You downloaded and installed the`opc` command-line utility. This utility is available in the same package as the`tkn` command-line utility.
- You are logged on to the cluster using the`oc` command-line utility.

Procedure

View a list of manual approval tasks for which you are listed as an approver by entering the following command:

```shell-session
$ opc approvaltask list
```

Example output

```shell-session
NAME                                     NumberOfApprovalsRequired   PendingApprovals   Rejected   STATUS
manual-approval-pipeline-01w6e1-task-2   2                           0                  0          Approved
manual-approval-pipeline-6ywv82-task-2   2                           2                  0          Rejected
manual-approval-pipeline-90gyki-task-2   2                           2                  0          Pending
manual-approval-pipeline-jyrkb3-task-2   2                           1                  1          Rejected
```

Optional: To view information about a manual approval task, including its name, namespace, pipeline run name, list of approvers, and current status, enter the following command:

```shell-session
$ opc approvaltask describe <approval_task_name>
```

Approve or reject a manual approval task as necessary:

To approve a manual approval task, enter the following command:

```shell-session
$ opc approvaltask approve <approval_task_name>
```

Optionally, you can specify a message for the approval by using the`-m` parameter:

```shell-session
$ opc approvaltask approve <approval_task_name> -m <message>
```

To reject a manual approval task, enter the following command:

```shell-session
$ opc approvaltask reject <approval_task_name>
```

Optionally, you can specify a message for the rejection by using the`-m` parameter:

```shell-session
$ opc approvaltask reject <approval_task_name> -m <message>
```

### 4.4. Behavior of ApprovalTask with groups and usersCopy link

You can define complex approval policies by combining individual users and groups in a single definition. This allows you to specify the total number of required approvals from different stakeholders, such as a tech lead, a QA engineer, and a member of the security team.

You can assign approval responsibility to a group. When a group is configured as an approver, the controller tracks individual approvals from group members and updates the group’s overall approval state automatically.

Example Initial ApprovalTask created by the controller with a group approver

```yaml
spec:
  approvers:
  - name: dev-team
    type: Group
    input: pending
```

As group members approve or reject the task, their individual actions appear in the`users` list. The group’s approval state changes automatically as soon as the necessary member-level approvals are recorded.

Example Group member approvals recorded by the controller

```yaml
spec:
  approvers:
  - name: dev-team
    type: Group
    input: approve
    users:
    - name: alice
      input: approve  # Alice has approved
    - name: bob
      input: approve  # Bob has approved
```

ApprovalTask also supports scenarios where approvals are required from both individual users and groups. You can combine users and groups in a single approval definition while specifying the total number of required approvals. This allows pipelines to enforce more granular approval policies, such as requiring sign-off from a tech lead, a QA engineer, and a member of the security team.

Important

The following YAML represents a runtime`ApprovalTask` object created automatically by the Manual Approval Gate controller when a`PipelineRun` pauses at an approval step. Do not apply this manifest. It is provided for debugging and inspection only.

```yaml
apiVersion: openshift-pipelines.org/v1alpha1
kind: ApprovalTask
metadata:
  name: mixed-approval
  namespace: default
spec:
  approvers:
  - name: tech-lead
    type: User
    input: pending
  - name: qa-team
    type: Group
    input: pending
    users:
    - name: <user_1>
      input: approve
    - name: <user_2>
      input: approve
  - name: security-team
    type: Group
    input: pending
  numberOfApprovalsRequired: 3
  description: "Requires an approval from the tech lead, a QA team member, and a security team member"
```

## Chapter 5. Using Red Hat entitlements in pipelinesCopy link

If you have Red Hat Enterprise Linux (RHEL) entitlements, you can use these entitlements to build container images in your pipelines.

The Insight Operator automatically manages your entitlements after you import them into this operator from Simple Common Access (SCA). This operator provides a secret named`etc-pki-entitlement` in the`openshift-config-managed` namespace.

You can use Red Hat entitlements in your pipelines in one of the following two ways:

- Manually copy the secret into the namespace of the pipeline. This method is least complex if you have a limited number of pipeline namespaces.
- Use the Shared Resources Container Storage Interface (CSI) Driver Operator to share the secret between namespaces automatically.

### 5.1. PrerequisitesCopy link

You enabled the Insights Operator feature on your OpenShift Container Platform cluster. If you want to use the Shared Resources CSI Driver operator to share the secret between namespaces, you must also enable the Shared Resources CSI driver. For information about enabling features, including the Insights Operator and Shared Resources CSI Driver, see [Enabling features using feature gates](https://docs.openshift.com/container-platform/latest/nodes/clusters/nodes-cluster-enabling-features.html).

Note

After you enable the Insights Operator, you must wait for some time to ensure that the cluster updates all the nodes with this operator. You can monitor the status of all nodes by entering the following command:

```shell-session
$ oc get nodes -w
```

To verify that the Insights Operator is active, check that the`insights-operator` pod is running in the`openshift-insights` namespace by entering the following command:

```shell-session
$ oc get pods -n openshift-insights
```

You configured the importing of your Red Hat entitlements into the Insights Operator. For information about importing the entitlements, see [Importing simple content access entitlements with Insights Operator](https://docs.openshift.com/container-platform/latest/support/remote_health_monitoring/insights-operator-simple-access.html).

Note

To verify that the Insights Operator made your entitlements available, is active, check that the`etc-pki-entitlement` secret is present in the`openshift-config-managed` namespace by entering the following command:

```shell-session
$ oc get secret etc-pki-entitlement -n openshift-config-managed
```

- You logged on to your OpenShift Container Platform cluster using the`oc` command line tool.

### 5.2. Using Red Hat entitlements by manually copying the etc-pki-entitlement secretCopy link

You can copy the`etc-pki-entitlement` secret from the`openshift-config-managed` namespace into the namespace of your pipeline. You can then configure your pipeline to use this secret for the Buildah task.

Prerequisites

- You installed the`jq` package on your system. This package is available in Red Hat Enterprise Linux (RHEL).

Procedure

Copy the`etc-pki-entitlement` secret from the`openshift-config-managed` namespace into the namespace of your pipeline by running the following command:

```shell-session
$ oc get secret etc-pki-entitlement -n openshift-config-managed -o json | \
  jq 'del(.metadata.resourceVersion)' | jq 'del(.metadata.creationTimestamp)' | \
  jq 'del(.metadata.uid)' | jq 'del(.metadata.namespace)' | \
  oc -n <pipeline_namespace> create -f - 
```

1

1

Replace` ` with the namespace of your pipeline.

1. In your Buildah task definition, use the`buildah` task provided in the`openshift-pipelines` namespace or a copy of this task and define the`rhel-entitlement` workspace, as shown in the following example.
2. In your task run or pipeline run that runs the Buildah task, assign the`etc-pki-entitlement` secret to the`rhel-entitlement` workspace, as in the following example.

Example pipeline run definition, including the pipeline and task definitions, that uses Red Hat entitlements

```yaml
apiVersion: tekton.dev/v1
kind: PipelineRun
metadata:
  name: buildah-pr-test
spec:
  workspaces:
    - name: shared-workspace
      volumeClaimTemplate:
        spec:
          accessModes:
            - ReadWriteOnce
          resources:
            requests:
              storage: 1Gi
    - name: dockerconfig
      secret:
        secretName: regred
    - name: rhel-entitlement  
```

1

```yaml

      secret:
        secretName: etc-pki-entitlement
  pipelineSpec:
    workspaces:
      - name: shared-workspace
      - name: dockerconfig
      - name: rhel-entitlement  
```

2

```yaml

    tasks:
# ...
      - name: buildah
        taskRef:
          resolver: cluster
          params:
          - name: kind
            value: task
          - name: name
            value: buildah
          - name: namespace
            value: openshift-pipelines
        workspaces:
        - name: source
          workspace: shared-workspace
        - name: dockerconfig
          workspace: dockerconfig
        - name: rhel-entitlement  
```

3

```yaml

          workspace: rhel-entitlement
        params:
        - name: IMAGE
          value: <image_where_you_want_to_push>
```

1

The definition of the`rhel-entitlement` workspace in the pipeline run, assigning the`etc-pki-entitlement` secret to the workspace

2

The definition of the`rhel-entitlement` workspace in the pipeline definition

3

The definition of the`rhel-entitlement` workspace in the task definition

### 5.3. Using Red Hat entitlements by sharing the secret using the Shared Resources CSI driver operatorCopy link

You can set up sharing of the`etc-pki-entitlement` secret from the`openshift-config-managed` namespace to other namespaces using the Shared Resources Container Storage Interface (CSI) Driver Operator. You can then configure your pipeline to use this secret for the Buildah task.

Prerequisites

- You are logged on to your OpenShift Container Platform cluster using the`oc` command line utility as a user with cluster administrator permissions.
- You enabled the Shared Resources CSI Driver operator on your OpenShift Container Platform cluster.

Procedure

Create a`SharedSecret` custom resource (CR) for sharing the`etc-pki-entitlement` secret by running the following command:

```shell-session
$ oc apply -f - <<EOF
apiVersion: sharedresource.openshift.io/v1alpha1
kind: SharedSecret
metadata:
  name: shared-rhel-entitlement
spec:
  secretRef:
    name: etc-pki-entitlement
    namespace: openshift-config-managed
EOF
```

Create an RBAC role that permits access to the shared secret by running the following command:

```shell-session
$ oc apply -f - <<EOF
apiVersion: rbac.authorization.k8s.io/v1
kind: Role
metadata:
  name: shared-resource-rhel-entitlement
  namespace: <pipeline_namespace> 
```

1

```shell-session

rules:
  - apiGroups:
      - sharedresource.openshift.io
    resources:
      - sharedsecrets
    resourceNames:
      - shared-rhel-entitlement
    verbs:
      - use
EOF
```

1

Replace` ` with the namespace of your pipeline.

Assign the role to the`pipeline` service account by running the following command:

```shell-session
$ oc create rolebinding shared-resource-rhel-entitlement --role=shared-shared-resource-rhel-entitlement \
  --serviceaccount=<pipeline-namespace>:pipeline 
```

1

1

Replace` ` with the namespace of your pipeline.

Note

If you changed the default service account for OpenShift Pipelines or if you define a custom service account in the pipeline run or task run, assign the role to this account instead of the`pipeline` account.

1. In your Buildah task definition, use the`buildah` task provided in the`openshift-pipelines` namespace or a copy of this task and define the`rhel-entitlement` workspace, as shown in the following example.
2. In your task run or pipeline run that runs the Buildah task, assign the shared secret to the`rhel-entitlement` workspace, as in the following example.

Example pipeline run definition, including the pipeline and task definitions, that uses Red Hat entitlements

```yaml
apiVersion: tekton.dev/v1
kind: PipelineRun
metadata:
  name: buildah-pr-test-csi
spec:
  workspaces:
    - name: shared-workspace
      volumeClaimTemplate:
        spec:
          accessModes:
          - ReadWriteOnce
          resources:
            requests:
              storage: 1Gi
    - name: dockerconfig
      secret:
        secretName: regred
    - name: rhel-entitlement  
```

1

```yaml

      csi:
        readOnly: true
        driver: csi.sharedresource.openshift.io
        volumeAttributes:
          sharedSecret: shared-rhel-entitlement
  pipelineSpec:
    workspaces:
    - name: shared-workspace
    - name: dockerconfig
    - name: rhel-entitlement  
```

2

```yaml

    tasks:
# ...
    - name: buildah
      taskRef:
        resolver: cluster
        params:
        - name: kind
          value: task
        - name: name
          value: buildah
        - name: namespace
          value: openshift-pipelines
      workspaces:
      - name: source
        workspace: shared-workspace
      - name: dockerconfig
        workspace: dockerconfig
      - name: rhel-entitlement  
```

3

```yaml

        workspace: rhel-entitlement
      params:
      - name: IMAGE
        value: <image_where_you_want_to_push>
```

1

The definition of the`rhel-entitlement` workspace in the pipeline run, assigning the`shared-rhel-entitlement` CSI shared secret to the workspace

2

The definition of the`rhel-entitlement` workspace in the pipeline definition

3

The definition of the`rhel-entitlement` workspace in the task definition

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
