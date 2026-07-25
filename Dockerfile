# ---- Stage 1: Build ----
FROM eclipse-temurin:21-jdk-alpine AS build
WORKDIR /app

# Copy Gradle wrapper and config files first (better Docker layer caching)
COPY gradlew .
COPY gradle gradle
COPY build.gradle settings.gradle ./

# Download dependencies (cached separately from source code changes)
RUN chmod +x gradlew
RUN ./gradlew dependencies --no-daemon || true

# Now copy the actual source code
COPY src src

# Build the JAR, skipping tests (tests should run in CI, not during deploy)
RUN ./gradlew build -x test --no-daemon

# ---- Stage 2: Run ----
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app

# Copy only the built JAR from the build stage
COPY --from=build /app/build/libs/*.jar app.jar

# Render provides the PORT env var dynamically — Spring Boot needs to listen on it
ENV SERVER_PORT=8080
EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]