{{/*
Expand the name of the chart.
*/}}
{{- define "showroom-docs-mcp.name" -}}
{{- default .Chart.Name .Values.nameOverride | trunc 63 | trimSuffix "-" }}
{{- end }}

{{/*
Create a default fully qualified app name.
*/}}
{{- define "showroom-docs-mcp.fullname" -}}
{{- if .Values.fullnameOverride }}
{{- .Values.fullnameOverride | trunc 63 | trimSuffix "-" }}
{{- else }}
{{- $name := default .Chart.Name .Values.nameOverride }}
{{- if contains $name .Release.Name }}
{{- .Release.Name | trunc 63 | trimSuffix "-" }}
{{- else }}
{{- printf "%s-%s" .Release.Name $name | trunc 63 | trimSuffix "-" }}
{{- end }}
{{- end }}
{{- end }}

{{/*
Common labels
*/}}
{{- define "showroom-docs-mcp.labels" -}}
helm.sh/chart: {{ include "showroom-docs-mcp.name" . }}-{{ .Chart.Version | replace "+" "_" }}
{{ include "showroom-docs-mcp.selectorLabels" . }}
app.kubernetes.io/version: {{ .Chart.AppVersion | quote }}
app.kubernetes.io/managed-by: {{ .Release.Service }}
app.kubernetes.io/part-of: openshift-lightspeed
{{- end }}

{{/*
Selector labels
*/}}
{{- define "showroom-docs-mcp.selectorLabels" -}}
app: {{ include "showroom-docs-mcp.fullname" . }}
app.kubernetes.io/name: {{ include "showroom-docs-mcp.name" . }}
app.kubernetes.io/instance: {{ .Release.Name }}
{{- end }}
