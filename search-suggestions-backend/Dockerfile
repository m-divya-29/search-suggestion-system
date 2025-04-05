FROM maven:3.9.4-eclipse-temurin-17 as builder

WORKDIR /app
COPY . .

RUN mvn clean package -DskipTests

FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=builder /app/target/*.jar search-suggestions-backend-DEV.jar

ENTRYPOINT ["java", "-jar", "search-suggestions-backend-DEV.jar"]
