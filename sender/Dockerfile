FROM openjdk:8-jdk-alpine
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} sender-app.jar
LABEL version=1.0
LABEL tag=1.0
LABEL repository=sender

ENTRYPOINT ["java", "-jar", "sender-app.jar"]