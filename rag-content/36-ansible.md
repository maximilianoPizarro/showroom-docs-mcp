# Red Hat Ansible Automation Platform

_Source: [developers.redhat.com](https://developers.redhat.com/products/ansible/overview)_

## Overview

Red Hat Ansible Automation Platform helps platform engineering and DevOps teams **create, manage, and scale automation** across physical, cloud, virtual, and edge environments. Use simple **YAML**-based automation spanning infrastructure, network, applications, storage, security, ITSM, and more—for consistency and compliance.

## Features

### Platform 2.6 highlights

- **Self-service automation portal** — Point-and-click jobs for broader users without full platform admin access ([learn more](https://www.redhat.com/en/technologies/management/ansible/self-service-automation))
- **Automation dashboard** — Real-time insights, custom reports for time savings, ROI, and job performance; on-prem, data stays in your environment ([video overview](https://www.youtube.com/watch?v=9SMjtQtwwQQ))
- **Ansible Lightspeed** — Generative AI assistant in the UI for troubleshooting, onboarding, and day-to-day management; links to validated content; works with Red Hat AI and supported third-party models ([details](https://developers.redhat.com/products/ansible/lightspeed))
- **Tech preview: Ansible development workspaces** — One-click, browser-based environments with VS Code, Ansible tooling, Lightspeed, and docs; Windows and macOS clients; runs on **OpenShift Dev Spaces**

### Edge and public cloud

- **Edge** — Central automation for remote devices and locations ([edge automation blog](https://www.ansible.com/blog/2022-the-year-for-edge-automation))
- **Public cloud** — Managed on **Microsoft Azure**; self-managed on **AWS** and **GCP** with native integrations and billing options ([download & deployment options](https://developers.redhat.com/products/ansible/download#ansibleways))

### IT integrations

Hundreds of modules and documented integrations with vendors such as AWS, Azure, GCP, Cisco, VMware, ServiceNow, NetApp, Atlassian, and more—see [ansible.com/integrations](https://www.ansible.com/integrations) and links on the product page.

## Getting started

### Quick-start: install Ansible and run your first playbook

```bash
# Install Ansible on RHEL/Fedora
sudo dnf install ansible-core

# Create an inventory file
cat > inventory.ini <<EOF
[webservers]
web1.example.com
web2.example.com

[dbservers]
db1.example.com
EOF

# Create your first playbook
cat > site.yml <<EOF
---
- name: Configure web servers
  hosts: webservers
  become: true
  tasks:
    - name: Install httpd
      ansible.builtin.dnf:
        name: httpd
        state: present

    - name: Start and enable httpd
      ansible.builtin.service:
        name: httpd
        state: started
        enabled: true

    - name: Open firewall for HTTP
      ansible.posix.firewalld:
        service: http
        permanent: true
        state: enabled
        immediate: true
EOF

# Run the playbook
ansible-playbook -i inventory.ini site.yml
```

### Quick-start: use Ansible collections

```bash
# Install a collection from Ansible Galaxy
ansible-galaxy collection install community.general

# Install from Automation Hub (with token)
ansible-galaxy collection install redhat.openshift --server https://console.redhat.com/api/automation-hub/
```

### Quick-start: Ansible Navigator (container-based execution)

```bash
# Install navigator
pip install ansible-navigator

# Run a playbook inside an execution environment
ansible-navigator run site.yml -i inventory.ini --mode stdout
```

### Links

- [Overview](https://developers.redhat.com/products/ansible)
- [Download](https://developers.redhat.com/products/ansible/download)
- [Getting started](https://developers.redhat.com/products/ansible/getting-started)
- [Ansible Lightspeed](https://developers.redhat.com/products/ansible/lightspeed)
- [Ansible development tools](https://developers.redhat.com/products/ansible/developmenttools)
- [YAML essentials for Ansible](https://developers.redhat.com/learn/ansible/yaml-essentials-ansible)

## Technical details

Upstream and community:

- **[ansible/ansible](https://github.com/ansible/ansible)** — Core engine (ansible-base)
- **AWX** — Fast-moving upstream for automation controller features
- **Galaxy** — Roles and collections for reusable automation

Study resource: **RHCE Ansible Automation Study Guide** (e-book on product page)—aligns heavily with Ansible for certification prep.

## What’s new

[What’s new in Ansible Automation Platform 2.6](https://developers.redhat.com/articles/2025/10/08/whats-new-ansible-automation-platform-2-6) — platform enhancements and integrations for resilient operations.
