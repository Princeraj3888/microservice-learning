FROM maven:3.9.9-eclipse-temurin-21-alpine AS build
WORKDIR /home/app
COPY . /home/app
RUN mvn -f /home/app/pom.xml clean package

FROM openjdk:21-bookworm
EXPOSE 5000
COPY --from=build /home/app/target/*.jar app.jar
ENTRYPOINT ["sh","-C", "java -jar /app.jar"]