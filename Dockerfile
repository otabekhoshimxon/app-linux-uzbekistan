FROM openjdk:17-jdk-slim-buster AS build
WORKDIR /opt/app
COPY target/app-linux-uzbekistan--demo.jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]
