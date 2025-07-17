FROM openjdk:21-bookworm
COPY target/*.jar app.jar
EXPOSE 5000
ENTRYPOINT ["java", "-jar", "/app.jar"]