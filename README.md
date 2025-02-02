# Spring Boot Application

## Overview
This is a Spring Boot application. It includes an integrated Swagger UI for easy API documentation and testing.

## Prerequisites
Ensure you have the following installed on your system:
- Java 21 or higher
- Maven
- An IDE like IntelliJ IDEA or Eclipse (optional, for easy development)

## Running the Application
1. Clone the repository:
   
   git clone https://github.com/CundullahT-CT/spartan-app-new-nonsecure.git

   cd spartan-app-new-nonsecure

2. Build and run the application using Maven:

   mvn spring-boot:run

### Running with JAR file
1. Build the JAR file:

   mvn clean install

2. Run the JAR file:

   java -jar target/spartan-app-new-nonsecure-0.0.1-SNAPSHOT.jar

The application will run on http://localhost:8000

## Swagger Documentation

After the application is running, you can access the Swagger UI to explore the available APIs.

- Open your browser and go to the following URL:
  http://localhost:8000/swagger-ui.html

## Troubleshooting

- If the application does not start, ensure that port 8000 is not being used by another process. You can change the port by updating the application.properties file:
  server.port=8000
