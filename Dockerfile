FROM maven:3.9.9-eclipse-temurin-21-alpine AS builder

WORKDIR usr/spartan-app-new-nonsecure

COPY pom.xml .
COPY src ./src

RUN mvn clean package -DskipTests

FROM eclipse-temurin:21-jre

COPY --from=builder usr/spartan-app-new-nonsecure/target/spartan-app-new-nonsecure-0.0.1-SNAPSHOT.jar /spartan-app-new-nonsecure-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java", "-jar", "spartan-app-new-nonsecure-0.0.1-SNAPSHOT.jar"]
