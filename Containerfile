FROM maven:3.9-eclipse-temurin-21 AS builder

WORKDIR /build
COPY pom.xml .
RUN mvn dependency:go-offline -B
COPY src ./src
RUN mvn package -DskipTests -Dquarkus.package.jar.type=uber-jar

FROM registry.access.redhat.com/ubi9/openjdk-21-runtime:1.21

WORKDIR /app

COPY --from=builder /build/target/*-runner.jar app.jar

EXPOSE 8080

ENV JAVA_OPTS_APPEND="-Dquarkus.http.host=0.0.0.0"

CMD ["java", "-jar", "app.jar"]
