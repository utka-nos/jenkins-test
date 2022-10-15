FROM openjdk:8-jdk-alpine
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} receiver-app.jar

LABEL version=1.0
LABEL TAG=1.0
LABEL REPOSITORY=receiver

ENTRYPOINT ["java", "-jar", "receiver-app.jar"]