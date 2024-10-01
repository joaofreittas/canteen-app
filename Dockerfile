# Etapa 1: Compilar a aplicação
FROM gradle:8.10.0-jdk21-alpine AS build
WORKDIR /app
COPY . .
RUN gradle build --no-daemon

# Etapa 2: Executar a aplicação
FROM eclipse-temurin:21-jdk-alpine
WORKDIR /app
COPY --from=build /app/build/libs/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
