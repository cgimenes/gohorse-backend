FROM openjdk:8-jdk-alpine
RUN mkdir -p /opt/gohorse/backend
WORKDIR /opt/gohorse/backend/
ARG JAR_FILE
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","app.jar"]
