# Use an official OpenJDK image
FROM openjdk:17-jdk-slim

# Add a volume to store logs
VOLUME /tmp

# Set working directory
WORKDIR /app

# Copy build jar from Maven target
COPY target/ms-edge-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080


# Run the jar
ENTRYPOINT ["java", "-jar", "app.jar"]