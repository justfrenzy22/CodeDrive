# syntax=docker/dockerfile:1

FROM eclipse-temurin:17-jdk AS builder

WORKDIR /app

COPY . .

# Fix Windows line endings and execution permission
RUN sed -i 's/\r$//' gradlew && chmod +x gradlew

RUN ./gradlew clean bootJar -x test --no-daemon


FROM eclipse-temurin:17-jre

WORKDIR /app

COPY --from=builder /app/build/libs/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]