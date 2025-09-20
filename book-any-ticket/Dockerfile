FROM maven:3.9.9-eclipse-temurin-21-alpine AS build
WORKDIR /home/app

COPY ./pom.xml /home/app/pom.xml
COPY ./src/main/java/com/explorertech/BookAnyTicketApplication.java /home/app/src/main/java/com/explorertech/BookAnyTicketApplication.java

RUN mvn -f /home/app/pom.xml clean package

COPY . /home/app
RUN mvn -f /home/app/pom.xml clean package

FROM openjdk:21-bookworm
EXPOSE 5000
COPY --from=build /home/app/target/*.jar app.jar
ENTRYPOINT [ "sh", "-c", "java -jar /app.jar"]