FROM openjdk:17-jdk-slim
COPY target/your-project-name.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
