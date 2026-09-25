# syntax=docker/dockerfile:1

# Build the Spring Boot application
FROM eclipse-temurin:17-jdk AS builder

WORKDIR /app

COPY . .

RUN ./gradlew clean bootJar -x test --no-daemon


# Run the Spring Boot application
FROM eclipse-temurin:17-jre

WORKDIR /app

COPY --from=builder /app/build/libs/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]