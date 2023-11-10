# Use an official OpenJDK runtime as a parent image
FROM openjdk:21-oracle

# Set the working directory in the container
WORKDIR /app

# Copy the application JAR file into the container
COPY target/api-gateway-0.0.1-SNAPSHOT.jar /app/app.jar

# Specify the command to run on container start
CMD ["java", "-jar", "app.jar"]
