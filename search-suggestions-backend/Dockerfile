FROM openjdk:17-jdk-slim

WORKDIR /app

COPY target/*.jar search-suggestions-backend-DEV.jar

# Run the application
ENTRYPOINT ["java", "-jar", "search-suggestions-backend-DEV.jar"]
