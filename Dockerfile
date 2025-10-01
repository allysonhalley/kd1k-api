# Etapa 1: Build do projeto em Maven
FROM maven:3.8.7-eclipse-temurin-17 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Etapa 2: Execução da aplicação
FROM openjdk:17-jdk-slim
EXPOSE 8080
COPY --from=build /app/target/kd1k-api-1.0.0.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
