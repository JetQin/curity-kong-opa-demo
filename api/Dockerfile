# Use the official Gradle image to create a build artifact.
FROM gradle:7.4-jdk11 as builder

# Copy local code to the container image.
WORKDIR /app
COPY . .

EXPOSE 8080
# Run the web service on container startup.
CMD ["./gradlew", "run"]
