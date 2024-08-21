FROM 8.10-jdk21-alpine AS build

COPY build.gradle settings.gradle /app/
COPY src /app/src

RUN ./gradlew clean build -x test

FROM openjdk:21-jdk-slim

WORKDIR /app

COPY --from=build /app/build/libs/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]