FROM openjdk:17-jdk-slim
COPY target/grocery-api.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]