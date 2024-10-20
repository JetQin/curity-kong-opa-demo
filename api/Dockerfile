# Use the official Gradle image to create a build artifact.
FROM gradle:7.4-jdk11 as builder

# Copy local code to the container image.
WORKDIR /app
COPY . .

# Build a release artifact.
RUN gradle build --no-daemon

# Use OpenJDK for runtime
FROM openjdk:11-jre-slim

# Copy the jar to the production image from the builder stage.
COPY --from=builder /app/build/libs/*-all.jar /app/ktor-api.jar

# Run the web service on container startup.
CMD ["java", "-jar", "/app/ktor-api.jar"]
