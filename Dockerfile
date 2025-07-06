# Use an official OpenJDK image
FROM openjdk:17-jdk-slim

# Add a volume to store logs
VOLUME /tmp

# Set working directory
WORKDIR /app

# Copy build jar from Maven target
COPY target/myApp-0.0.1-SNAPSHOT.jar app.jar

# Run the jar
ENTRYPOINT ["java", "-jar", "app.jar"]