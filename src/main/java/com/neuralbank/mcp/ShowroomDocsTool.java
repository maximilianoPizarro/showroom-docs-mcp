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
    private final Map<String, String> fileTitles = new LinkedHashMap<>();
    private final Map<String, List<String>> topicKeywords = new LinkedHashMap<>();

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
                    String title = extractTitle(content);
                    fileTitles.put(path, title);
                    topicKeywords.put(path, buildKeywords(path, title, content));
                }
            } catch (IOException e) {
                // skip
            }
        }
    }

    @Tool(description = "Search Red Hat and workshop documentation by keywords. " +
            "USE THIS TOOL to answer questions about: OpenShift Service Mesh, Connectivity Link, " +
            "Developer Hub, OpenShift Lightspeed, Observability, OpenTelemetry, Pipelines, " +
            "API Management, OpenShift AI, and the Neuralbank workshop (MCP agents, Quarkus, " +
            "Keycloak, DevSpaces, RAG, Golden Path). " +
            "Example queries: 'deploy model openshift ai', 'developer hub architecture plugins', " +
            "'service mesh install istio', 'connectivity link gateway policy', " +
            "'neuralbank workshop mcp agent', 'pipelines as code tekton', " +
            "'lightspeed olsconfig configure'.")
    String searchDocs(
            @ToolArg(description = "Keywords to search, e.g. 'developer hub architecture' or 'deploy model openshift ai'") String query
    ) {
        if (query == null || query.isBlank()) {
            return "Please provide a search query.";
        }

        String[] keywords = query.toLowerCase().split("\\s+");
        List<ScoredSection> results = new ArrayList<>();

        for (var entry : documents.entrySet()) {
            String[] sections = entry.getValue().split("(?m)(?=^#{1,3}\\s)");
            for (String section : sections) {
                if (section.isBlank()) continue;
                String lower = section.toLowerCase();
                int score = 0;
                int matchedKeywords = 0;
                for (String kw : keywords) {
                    if (kw.length() < 2) continue;
                    int occ = countOccurrences(lower, kw);
                    if (occ > 0) {
                        score += occ;
                        matchedKeywords++;
                    }
                }
                double ratio = keywords.length > 0 ? (double) matchedKeywords / keywords.length : 0;
                if (matchedKeywords >= 1 && ratio >= 0.3) {
                    int adjustedScore = (int) (score * ratio * 10);
                    String title = fileTitles.getOrDefault(entry.getKey(), entry.getKey());
                    results.add(new ScoredSection(entry.getKey(), title, section.trim(), adjustedScore));
                }
            }
        }

        results.sort((a, b) -> Integer.compare(b.score, a.score));

        if (results.isEmpty()) {
            return "No results found for: " + query + ". Try different keywords. " +
                   "Available topics: Service Mesh, Connectivity Link, Developer Hub, " +
                   "Lightspeed, Observability, OpenTelemetry, Pipelines, API Management, " +
                   "OpenShift AI, Neuralbank workshop, MCP agents, Quarkus, Keycloak, RAG.";
        }

        StringBuilder sb = new StringBuilder();
        sb.append("# Documentation Results for: ").append(query).append("\n\n");

        int limit = Math.min(results.size(), 5);
        for (int i = 0; i < limit; i++) {
            ScoredSection r = results.get(i);
            sb.append("## From: ").append(r.title).append(" (").append(r.file).append(")\n\n");
            String content = r.content.length() > 3000 ? r.content.substring(0, 3000) + "\n..." : r.content;
            sb.append(content).append("\n\n---\n\n");
        }

        return sb.toString();
    }

    @Tool(description = "Get a specific documentation file by topic name. " +
            "You can use the exact filename (e.g. '22-developer-hub.md') OR a topic keyword " +
            "(e.g. 'developer hub', 'service mesh', 'pipelines', 'openshift ai', 'lightspeed', " +
            "'connectivity link', 'observability', 'opentelemetry', 'api management', " +
            "'neuralbank', 'keycloak', 'rag', 'devspaces', 'golden path', 'mcp agent').")
    String getDocSection(
            @ToolArg(description = "Filename like '22-developer-hub.md' or topic like 'developer hub'") String fileName
    ) {
        String content = resolveDocument(fileName);
        if (content == null) {
            StringBuilder suggestion = new StringBuilder();
            suggestion.append("Document not found for: '").append(fileName).append("'.\n\n");
            suggestion.append("Available documents:\n");
            for (var entry : fileTitles.entrySet()) {
                String fn = entry.getKey().replace("docs/", "");
                suggestion.append("- ").append(fn).append(": ").append(entry.getValue()).append("\n");
            }
            return suggestion.toString();
        }
        if (content.length() > 8000) {
            return content.substring(0, 8000) + "\n\n... (truncated - use searchDocs with specific keywords for targeted results)";
        }
        return content;
    }

    @Tool(description = "List all available documentation files with titles. " +
            "Returns table of contents for the entire knowledge base.")
    String listDocSections() {
        StringBuilder sb = new StringBuilder();
        sb.append("# Documentation Index\n\n");

        sb.append("## Neuralbank Workshop (IA Development From Zero To Hero)\n\n");
        for (var entry : fileTitles.entrySet()) {
            if (!entry.getKey().startsWith("docs/2")) {
                String fn = entry.getKey().replace("docs/", "");
                sb.append("- **").append(fn).append("**: ").append(entry.getValue()).append("\n");
            }
        }

        sb.append("\n## Red Hat Product Documentation\n\n");
        for (var entry : fileTitles.entrySet()) {
            if (entry.getKey().startsWith("docs/2")) {
                String fn = entry.getKey().replace("docs/", "");
                sb.append("- **").append(fn).append("**: ").append(entry.getValue()).append("\n");
            }
        }

        return sb.toString();
    }

    @Tool(description = "Get a summary of all indexed documentation. " +
            "Covers the Neuralbank workshop and 9 Red Hat products.")
    String getDocSummary() {
        int totalDocs = documents.size();
        long totalChars = documents.values().stream().mapToLong(String::length).sum();

        return """
                # Showroom Docs MCP - Knowledge Base Summary

                ## Workshop: IA Development From Zero To Hero (Neuralbank)
                A hands-on workshop where you play Kevin, a Java developer at Neuralbank (a financial
                institution), building an MCP Agent (customer-service-mcp) using Quarkus to automate
                credit risk management via a chat interface.
                Technologies: OpenShift AI, MCP, LlamaStack, DevSpaces, Developer Hub, Quarkus,
                Keycloak, Connectivity Link, OpenTelemetry, GitLab, ArgoCD.

                ## Red Hat Product Documentation (Official)
                | Product | Version | Key Topics |
                |---------|---------|------------|
                | OpenShift Service Mesh | 3.3 | Istio, gateways, traffic management, observability |
                | Connectivity Link | 1.3 | API gateway, DNS policies, TLS, rate limiting |
                | Developer Hub | 1.9 | Internal developer portal, plugins, software catalog, templates |
                | OpenShift Lightspeed | 1.0 | AI assistant, OLSConfig, MCP servers, RAG |
                | OpenShift Observability | 1 | Monitoring, logging, distributed tracing |
                | OpenTelemetry | 3.9 | Collectors, instrumentation, OTLP, Jaeger |
                | OpenShift Pipelines | 1.21 | Tekton, Pipelines as Code, CI/CD, tasks |
                | API Management | 1 | 3scale, API products, rate limits, developer portal |
                | OpenShift AI Cloud Service | 1 | Model serving, data science projects, Llama Stack |

                ## Statistics
                - Total documents: """ + totalDocs + """

                - Total content size: """ + (totalChars / 1024) + """
                 KB

                ## Example Questions You Can Ask
                - "How do I install OpenShift Service Mesh 3.3?"
                - "What is the architecture of Developer Hub?"
                - "How do I deploy a model with OpenShift AI?"
                - "Explain Connectivity Link gateway policies"
                - "How do I configure OpenTelemetry collectors?"
                - "What is the Neuralbank workshop about?"
                - "How do I set up Pipelines as Code with Tekton?"
                - "How does OpenShift Lightspeed OLSConfig work?"
                - "What is RAG and how does it work in OpenShift AI?"

                Use searchDocs with keywords to find specific information.
                """;
    }

    private String resolveDocument(String input) {
        if (input == null || input.isBlank()) return null;

        String key = input.startsWith("docs/") ? input : "docs/" + input;
        if (documents.containsKey(key)) return documents.get(key);

        String normalized = input.toLowerCase()
                .replace(".md", "")
                .replaceAll("[^a-z0-9\\s]", " ")
                .trim();

        int bestScore = 0;
        String bestKey = null;

        for (var entry : topicKeywords.entrySet()) {
            int score = 0;
            for (String kw : normalized.split("\\s+")) {
                if (kw.length() < 2) continue;
                for (String topic : entry.getValue()) {
                    if (topic.contains(kw)) {
                        score += 3;
                    }
                }
                String title = fileTitles.getOrDefault(entry.getKey(), "").toLowerCase();
                if (title.contains(kw)) {
                    score += 5;
                }
            }
            if (score > bestScore) {
                bestScore = score;
                bestKey = entry.getKey();
            }
        }

        if (bestKey != null && bestScore >= 5) {
            return documents.get(bestKey);
        }

        return null;
    }

    private List<String> buildKeywords(String path, String title, String content) {
        List<String> kws = new ArrayList<>();
        kws.add(path.toLowerCase());
        kws.add(title.toLowerCase());

        String fn = path.replace("docs/", "").replace(".md", "").replaceAll("^\\d+-", "");
        for (String part : fn.split("-")) {
            if (part.length() > 2) kws.add(part);
        }

        for (String line : content.split("\n")) {
            if (line.startsWith("# ") || line.startsWith("## ")) {
                kws.add(line.replaceAll("^#+\\s*", "").toLowerCase().trim());
            }
        }

        return kws;
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

    private record ScoredSection(String file, String title, String content, int score) {}
}
