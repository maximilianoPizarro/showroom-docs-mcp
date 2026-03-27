# 📊 OpenTelemetry Observability

Observability is crucial for understanding how your `customer-service-mcp` performs in production. **OpenTelemetry** provides distributed tracing, metrics, and logs to help you monitor the entire Neuralbank system.

## What is OpenTelemetry?

**OpenTelemetry** is an open-source observability framework that provides:

- **Distributed Tracing**: Track requests across multiple services
- **Metrics**: Collect performance and business metrics
- **Logs**: Structured logging with correlation IDs
- **Instrumentation**: Automatic and manual code instrumentation

For Neuralbank, OpenTelemetry helps you:

- **Track Requests**: See how requests flow from Playground → customer-service-mcp → Credit Risk Service
- **Identify Bottlenecks**: Find slow operations
- **Debug Issues**: Trace errors across services
- **Monitor Performance**: Track response times and throughput
- **Ensure Compliance**: Audit trail of all operations

## Viewing Traces for customer-service-mcp

The `customer-service-mcp` service is instrumented with OpenTelemetry. You can view traces and logs to understand how the service is performing.

### Distributed Traces

![OpenTelemetry customer-service-mcp Traces](../images/optl-customer-service-mcp.png)

The trace view shows:

- **Service Name**: `customer-service-mcp`
- **Operation**: MCP tool calls (query_credit_risk, update_credit_risk)
- **Duration**: Time taken for each operation
- **Spans**: Individual operations within a trace
- **Dependencies**: Service-to-service calls via Connectivity Link

### Service Logs

![OpenTelemetry customer-service-mcp Logs](../images/optl-customer-service-mcp-logs.png)

The logs view provides:

- **Structured Logs**: JSON-formatted log entries
- **Trace Correlation**: Logs linked to trace IDs
- **Error Details**: Stack traces and error messages
- **Request Context**: Customer IDs, operation types, etc.

## Architecture

```
┌─────────────────────────────────────────────────────────┐
│              Commercial Agent (Frontend)                │
│                    [Trace Start]                         │
└────────────────────┬────────────────────────────────────┘
                     │
                     │ HTTP Request (Trace ID: abc123)
                     ▼
┌─────────────────────────────────────────────────────────┐
│              MCP Agent (OpenShift)                       │
│         [Span: MCP Request Processing]                   │
│                     │                                    │
│                     │ Keycloak Auth (Span)               │
│                     │ Connectivity Link (Span)           │
└────────────────────┬────────────────────────────────────┘
                     │
                     │ Service Call (Trace ID: abc123)
                     ▼
┌─────────────────────────────────────────────────────────┐
│         Credit Risk Service (Backend)                    │
│            [Span: Risk Update]                           │
│                     │                                    │
│                     │ Database Query (Span)              │
└────────────────────┬────────────────────────────────────┘
                     │
                     │ Response (Trace ID: abc123)
                     ▼
┌─────────────────────────────────────────────────────────┐
│            OpenTelemetry Collector                       │
│         [Aggregates All Spans]                           │
└────────────────────┬────────────────────────────────────┘
                     │
                     ▼
┌─────────────────────────────────────────────────────────┐
│         Observability Backend (Jaeger/Tempo)            │
│         [Stores and Visualizes Traces]                   │
└─────────────────────────────────────────────────────────┘
```

## Instrumenting the MCP Agent

### 1. Add Dependencies

Add OpenTelemetry dependencies to your `pom.xml`:

```xml
<dependencies>
    <!-- OpenTelemetry API -->
    <dependency>
        <groupId>io.opentelemetry</groupId>
        <artifactId>opentelemetry-api</artifactId>
        <version>1.32.0</version>
    </dependency>
    
    <!-- OpenTelemetry SDK -->
    <dependency>
        <groupId>io.opentelemetry</groupId>
        <artifactId>opentelemetry-sdk</artifactId>
        <version>1.32.0</version>
    </dependency>
    
    <!-- OpenTelemetry Exporter -->
    <dependency>
        <groupId>io.opentelemetry</groupId>
        <artifactId>opentelemetry-exporter-otlp</artifactId>
        <version>1.32.0</version>
    </dependency>
    
    <!-- OpenTelemetry Instrumentation -->
    <dependency>
        <groupId>io.opentelemetry.instrumentation</groupId>
        <artifactId>opentelemetry-spring-boot-starter</artifactId>
        <version>2.1.0</version>
    </dependency>
</dependencies>
```

### 2. Configure OpenTelemetry

Add configuration to `application.properties`:

```properties
# OpenTelemetry Configuration
otel.service.name=customer-service-mcp
otel.service.version=1.0.0

# OTLP Exporter
otel.exporter.otlp.endpoint=http://otel-collector.observability.svc.cluster.local:4317
otel.exporter.otlp.protocol=grpc

# Tracing
otel.traces.exporter=otlp
otel.metrics.exporter=otlp
otel.logs.exporter=otlp

# Sampling
otel.traces.sampler=traceidratio
otel.traces.sampler.arg=1.0
```

### 3. Instrument Code

Add tracing to your MCP Agent:

```java
package com.neuralbank.mcp;

import io.opentelemetry.api.OpenTelemetry;
import io.opentelemetry.api.trace.Span;
import io.opentelemetry.api.trace.Tracer;
import io.opentelemetry.api.trace.StatusCode;
import io.opentelemetry.context.Scope;

@Service
public class CreditRiskTool {
    
    private final Tracer tracer;
    
    public CreditRiskTool(OpenTelemetry openTelemetry) {
        this.tracer = openTelemetry.getTracer("customer-service-mcp");
    }
    
    public MCPResult queryCreditRisk(String customerId) {
        Span span = tracer.spanBuilder("query_credit_risk")
            .setAttribute("customer.id", customerId)
            .startSpan();
            
        try (Scope scope = span.makeCurrent()) {
            // Add span attributes
            span.setAttribute("tool.name", "query_credit_risk");
            span.setAttribute("customer.id", customerId);
            
            // Execute the query
            CreditRisk risk = creditRiskService.getCreditRisk(customerId);
            
            // Add result attributes
            span.setAttribute("risk.level", risk.getLevel());
            span.setAttribute("risk.score", risk.getScore());
            
            return MCPResult.success()
                .withData("customer_id", customerId)
                .withData("risk_level", risk.getLevel())
                .withData("score", risk.getScore());
                
        } catch (Exception e) {
            span.setStatus(StatusCode.ERROR, e.getMessage());
            span.recordException(e);
            throw e;
        } finally {
            span.end();
        }
    }
    
    public MCPResult updateCreditRisk(
        String customerId, 
        String loanAmount, 
        String loanPurpose
    ) {
        Span span = tracer.spanBuilder("update_credit_risk")
            .setAttribute("customer.id", customerId)
            .setAttribute("loan.amount", loanAmount)
            .setAttribute("loan.purpose", loanPurpose)
            .startSpan();
            
        try (Scope scope = span.makeCurrent()) {
            span.setAttribute("tool.name", "update_credit_risk");
            
            // Execute the update
            CreditRiskUpdate update = creditRiskService.updateCreditRisk(
                customerId, 
                loanAmount, 
                loanPurpose
            );
            
            // Add update attributes
            span.setAttribute("risk.previous", update.getPreviousRisk());
            span.setAttribute("risk.new", update.getNewRisk());
            span.setAttribute("update.timestamp", update.getTimestamp().toString());
            
            // Add business event
            span.addEvent("credit_risk_updated", 
                Attributes.of(
                    AttributeKey.stringKey("customer.id"), customerId,
                    AttributeKey.stringKey("risk.change"), 
                        update.getPreviousRisk() + " -> " + update.getNewRisk()
                )
            );
            
            return MCPResult.success()
                .withData("customer_id", customerId)
                .withData("previous_risk", update.getPreviousRisk())
                .withData("new_risk", update.getNewRisk());
                
        } catch (Exception e) {
            span.setStatus(StatusCode.ERROR, e.getMessage());
            span.recordException(e);
            throw e;
        } finally {
            span.end();
        }
    }
}
```

?> **Note**: The OpenTelemetry Operator is already installed in the environment. You can proceed directly to viewing traces.

## Viewing Traces for customer-service-mcp

The `customer-service-mcp` service is instrumented with OpenTelemetry. You can view traces and logs to understand how the service is performing.

### Distributed Traces

![OpenTelemetry customer-service-mcp Traces](../images/optl-customer-service-mcp.png)

The trace view shows:

- **Service Name**: `customer-service-mcp`
- **Operation**: MCP tool calls (query_credit_risk, update_credit_risk)
- **Duration**: Time taken for each operation
- **Spans**: Individual operations within a trace
- **Dependencies**: Service-to-service calls via Connectivity Link

### Service Logs

![OpenTelemetry customer-service-mcp Logs](../images/optl-customer-service-mcp-logs.png)

The logs view provides:

- **Structured Logs**: JSON-formatted log entries
- **Trace Correlation**: Logs linked to trace IDs
- **Error Details**: Stack traces and error messages
- **Request Context**: Customer IDs, operation types, etc.

### Accessing OpenTelemetry UI

Navigate to OpenTelemetry/Jaeger:
<a href="https://jaeger-query-observability.apps.<CLUSTER_DOMAIN>" target="_blank">https://jaeger-query-observability.apps.<CLUSTER_DOMAIN></a>

### Query Traces

1. **Select Service**: Choose `customer-service-mcp`
2. **Select Operation**: Choose `update_credit_risk` or `query_credit_risk`
3. **Set Time Range**: Select the time period
4. **Click "Find Traces"**

### Understanding Trace Views

**Trace Timeline**: See the complete request flow
- Playground → customer-service-mcp → Credit Risk Service → Database
- See duration of each span
- Identify slow operations

**Span Details**: Click on any span to see:
- Attributes (customer ID, risk levels, etc.)
- Events (credit_risk_updated)
- Logs
- Tags

**Service Map**: Visualize service dependencies
- See how services connect
- Identify bottlenecks
- Monitor service health

## Example Trace

Here's what a complete trace looks like:

```
Trace: abc123def456
├─ Span: http_request (Frontend)
│  ├─ Duration: 2.5s
│  └─ Attributes:
│     ├─ http.method: POST
│     ├─ http.route: /mcp/tools/update_credit_risk
│     └─ customer.id: CUST-12345
│
├─ Span: mcp_request (MCP Agent)
│  ├─ Duration: 2.3s
│  ├─ Attributes:
│  │  ├─ tool.name: update_credit_risk
│  │  ├─ loan.amount: 50000
│  │  └─ loan.purpose: HOME_IMPROVEMENT
│  │
│  ├─ Span: keycloak_auth
│  │  └─ Duration: 0.1s
│  │
│  └─ Span: connectivity_link_call
│     ├─ Duration: 1.8s
│     └─ Attributes:
│        └─ service: credit-risk-service
│
└─ Span: credit_risk_update (Credit Risk Service)
   ├─ Duration: 1.5s
   ├─ Attributes:
   │  ├─ risk.previous: MEDIUM
   │  └─ risk.new: LOW
   │
   ├─ Event: credit_risk_updated
   │  └─ Timestamp: 2025-01-15T11:00:00Z
   │
   └─ Span: database_update
      └─ Duration: 0.3s
```

## Metrics and Dashboards

### Key Metrics to Monitor

- **Request Rate**: Requests per second
- **Error Rate**: Percentage of failed requests
- **Latency**: P50, P95, P99 response times
- **Tool Usage**: Which tools are used most
- **Customer Impact**: Number of credit risk updates

### Creating Dashboards

Use Grafana or OpenShift monitoring to create dashboards:

1. **MCP Agent Health**: Overall service health
2. **Tool Performance**: Performance by tool
3. **Error Analysis**: Error rates and types
4. **Business Metrics**: Credit risk updates per day

## Best Practices

<input type="checkbox" id="best1"> <label for="best1">**Add Meaningful Attributes**: Include business context (customer ID, risk levels)</label>  
<input type="checkbox" id="best2"> <label for="best2">**Use Semantic Conventions**: Follow OpenTelemetry naming conventions</label>  
<input type="checkbox" id="best3"> <label for="best3">**Correlate Logs**: Use trace IDs in log messages</label>  
<input type="checkbox" id="best4"> <label for="best4">**Sample Appropriately**: Balance detail with performance</label>  
<input type="checkbox" id="best5"> <label for="best5">**Monitor Key Operations**: Focus on critical business operations</label>  

## Compliance and Auditing

OpenTelemetry traces provide:

- **Complete Audit Trail**: Every operation is traced
- **Who**: User information from Keycloak
- **What**: Tool name and parameters
- **When**: Timestamps for all operations
- **Result**: Success or failure with details

This satisfies Neuralbank's compliance requirements for transparent and auditable operations.

## 🎉🎉🎉 Congratulations! You've Completed the Full Journey! 🎉🎉🎉

**Outstanding work!** You've successfully completed the entire MCP Agents workshop from zero to hero. Here's what you've accomplished:

<input type="checkbox" id="journey1"> <label for="journey1">**Built and deployed an MCP Agent** - Created a production-ready MCP service using Quarkus</label>  
<input type="checkbox" id="journey2"> <label for="journey2">**Integrated with Neuralbank's systems** - Connected to backend services via Connectivity Link</label>  
<input type="checkbox" id="journey3"> <label for="journey3">**Set up comprehensive observability** - Implemented distributed tracing with OpenTelemetry</label>  
<input type="checkbox" id="journey4"> <label for="journey4">**Configured authentication** - Integrated Keycloak for secure access</label>  
<input type="checkbox" id="journey5"> <label for="journey5">**Implemented rate limiting** - Protected services with Connectivity Link policies</label>  
<input type="checkbox" id="journey6"> <label for="journey6">**Tested thoroughly** - Validated functionality with MCP Inspector and Cursor</label>  
<input type="checkbox" id="journey7"> <label for="journey7">**Deployed to production** - Integrated with LlamaStack Playground for real-world use</label>  

### What You've Built

Your MCP Agent is now **production-ready** and actively helping Neuralbank:
- ⚡ **Accelerate credit decisions** from days to minutes
- 🔒 **Maintain compliance** with full audit trails
- 📊 **Provide transparency** in risk assessment changes
- 🚀 **Scale operations** without proportional staff increases

### Skills You've Gained

Throughout this journey, you've mastered:
- **MCP Protocol**: Understanding and implementing Model Context Protocol
- **Quarkus Development**: Building cloud-native Java applications
- **Service Mesh**: Working with Connectivity Link for secure communication
- **Observability**: Implementing distributed tracing and monitoring
- **DevOps**: CI/CD pipelines, GitOps, and container deployment
- **AI Integration**: Connecting LLMs with enterprise systems

**Your MCP Agent is now helping Neuralbank accelerate credit decisions while maintaining compliance!**

---

## Continue Your Journey

📚 **Resources**: [Additional Resources](6-resources/README) - Source code, installation methods, and more  
👥 **About**: [About the Team](5-about-the-team/README) - Thank you for participating!

---

**Return to**: [Main Page](/)

