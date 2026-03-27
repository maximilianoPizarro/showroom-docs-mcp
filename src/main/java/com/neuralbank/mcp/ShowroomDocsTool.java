package com.neuralbank.mcp;

import io.quarkiverse.mcp.server.Tool;
import io.quarkiverse.mcp.server.ToolArg;
import jakarta.annotation.PostConstruct;
import jakarta.inject.Singleton;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

@Singleton
public class ShowroomDocsTool {

    private final Map<String, String> documents = new LinkedHashMap<>();
    private String fullContent;

    private static final String[] DOC_FILES = {
        "docs/00-index.md",
        "docs/01-business-case.md",
        "docs/02-workshop-overview.md",
        "docs/03-configure-environment.md",
        "docs/04-agents-intro.md",
        "docs/05-golden-path.md",
        "docs/06-devspaces.md",
        "docs/07-keycloak-user-management.md",
        "docs/08-build-mcp-agent.md",
        "docs/09-connectivity-link.md",
        "docs/10-mcp-inspector.md",
        "docs/11-deploy-integrate.md",
        "docs/12-opentelemetry.md",
        "docs/13-rag-intro.md",
        "docs/14-vector-databases.md",
        "docs/15-simple-rag.md",
        "docs/20-service-mesh.md",
        "docs/21-connectivity-link.md",
        "docs/22-developer-hub.md",
        "docs/23-openshift-lightspeed.md",
        "docs/24-observability.md",
        "docs/25-opentelemetry.md",
        "docs/26-pipelines.md",
        "docs/27-api-management.md",
        "docs/28-openshift-ai.md"
    };

    @PostConstruct
    void init() {
        for (String path : DOC_FILES) {
            try (InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(path)) {
                if (is != null) {
                    String content = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8))
                            .lines().collect(Collectors.joining("\n"));
                    documents.put(path, content);
                }
            } catch (IOException e) {
                // skip files that can't be loaded
            }
        }
        fullContent = documents.values().stream().collect(Collectors.joining("\n\n---\n\n"));
    }

    @Tool(description = "Search the workshop and Red Hat product documentation. " +
            "Returns relevant documentation sections matching the query keywords. " +
            "Covers: Neuralbank workshop (MCP agents, Quarkus, Keycloak, Connectivity Link, " +
            "DevSpaces, OpenTelemetry, RAG, Golden Path, Developer Hub), plus official Red Hat docs for " +
            "OpenShift Service Mesh 3.3, Connectivity Link 1.3, Developer Hub 1.9, " +
            "OpenShift Lightspeed 1.0, OpenShift Observability, OpenTelemetry 3.9, " +
            "OpenShift Pipelines 1.21, OpenShift API Management, OpenShift AI Cloud Service.")
    String searchWorkshopDocs(
            @ToolArg(description = "Search query - keywords to find in the workshop documentation") String query
    ) {
        if (query == null || query.isBlank()) {
            return "Please provide a search query.";
        }

        String[] keywords = query.toLowerCase().split("\\s+");
        List<ScoredSection> results = new ArrayList<>();

        for (var entry : documents.entrySet()) {
            String[] sections = entry.getValue().split("(?=^#{1,3}\\s)", -1);
            for (String section : sections) {
                if (section.isBlank()) continue;
                String lower = section.toLowerCase();
                int score = 0;
                for (String kw : keywords) {
                    if (lower.contains(kw)) {
                        score += countOccurrences(lower, kw);
                    }
                }
                if (score > 0) {
                    results.add(new ScoredSection(entry.getKey(), section.trim(), score));
                }
            }
        }

        results.sort((a, b) -> Integer.compare(b.score, a.score));

        if (results.isEmpty()) {
            return "No results found for: " + query;
        }

        StringBuilder sb = new StringBuilder();
        sb.append("## Workshop Documentation Results for: ").append(query).append("\n\n");

        int limit = Math.min(results.size(), 5);
        for (int i = 0; i < limit; i++) {
            ScoredSection r = results.get(i);
            sb.append("### Source: ").append(r.file).append(" (relevance: ").append(r.score).append(")\n\n");
            String content = r.content.length() > 2000 ? r.content.substring(0, 2000) + "\n..." : r.content;
            sb.append(content).append("\n\n---\n\n");
        }

        return sb.toString();
    }

    @Tool(description = "List all available documentation sections. " +
            "Returns the table of contents including workshop docs and Red Hat product documentation.")
    String listWorkshopSections() {
        StringBuilder sb = new StringBuilder();
        sb.append("# Documentation Index\n\n");
        sb.append("## Workshop & Red Hat Product Documentation\n\n");

        for (var entry : documents.entrySet()) {
            String filename = entry.getKey().replace("docs/", "");
            String content = entry.getValue();
            String title = extractTitle(content);
            sb.append("- **").append(filename).append("**: ").append(title).append("\n");
        }

        return sb.toString();
    }

    @Tool(description = "Get the full content of a specific workshop documentation section. " +
            "Use listWorkshopSections first to see available files.")
    String getWorkshopSection(
            @ToolArg(description = "The documentation file name, e.g. '08-build-mcp-agent.md'") String fileName
    ) {
        String key = fileName.startsWith("docs/") ? fileName : "docs/" + fileName;
        String content = documents.get(key);
        if (content == null) {
            return "Document not found: " + fileName + ". Use listWorkshopSections to see available files.";
        }
        if (content.length() > 8000) {
            return content.substring(0, 8000) + "\n\n... (truncated, use searchWorkshopDocs for specific topics)";
        }
        return content;
    }

    @Tool(description = "Get a summary of all indexed documentation - workshop overview, " +
            "Red Hat products covered, and available search topics.")
    String getWorkshopSummary() {
        return """
                # Documentation Knowledge Base Summary
                
                ## Workshop: IA Development From Zero To Hero
                **Neuralbank** is a financial institution building an MCP Agent (customer-service-mcp)
                using Quarkus to automate credit risk management via chat interface.
                Role: Kevin, Java developer. Technologies: OpenShift AI, MCP, LlamaStack, DevSpaces,
                Developer Hub, Quarkus, Keycloak, Connectivity Link, OpenTelemetry, GitLab, ArgoCD.
                
                ## Red Hat Product Documentation Indexed
                - **OpenShift Service Mesh 3.3**: Istio-based service mesh (about, installing, gateways, observability)
                - **Connectivity Link 1.3**: API gateway and connectivity (discover, install, configure, observe)
                - **Developer Hub 1.9**: Internal developer portal (install, configure, auth, GitHub, MCP tools)
                - **OpenShift Lightspeed 1.0**: AI assistant for OpenShift (install, configure, OLSConfig API, operate)
                - **OpenShift Observability 1**: Monitoring, logging, tracing hub
                - **OpenTelemetry 3.9**: Distributed tracing and telemetry (install, collector config, instrumentation)
                - **OpenShift Pipelines 1.21**: Tekton CI/CD (install, Pipelines as Code, security)
                - **OpenShift API Management 1**: 3scale-based API management (getting started, admin)
                - **OpenShift AI Cloud Service 1**: ML platform (data science projects, model serving, Llama Stack, pipelines)
                
                ## Key Repositories
                - Workshop: https://github.com/maximilianoPizarro/rhoai-roadshow-from-zero-to-hero
                - Source Code: https://github.com/pkstaz/neuralbank-workshop
                - Helm Chart: https://github.com/maximilianoPizarro/neuralbank-stack
                
                ## How to Search
                Use searchWorkshopDocs with keywords like "service mesh install", "pipelines as code",
                "keycloak mcp", "model serving openshift ai", "connectivity link gateway policy", etc.
                """;
    }

    private String extractTitle(String content) {
        for (String line : content.split("\n")) {
            if (line.startsWith("# ")) {
                return line.substring(2).trim();
            }
        }
        return "(untitled)";
    }

    private int countOccurrences(String text, String keyword) {
        int count = 0;
        int idx = 0;
        while ((idx = text.indexOf(keyword, idx)) != -1) {
            count++;
            idx += keyword.length();
        }
        return count;
    }

    private record ScoredSection(String file, String content, int score) {}
}
