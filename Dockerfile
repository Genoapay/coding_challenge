FROM openjdk:8-jdk-alpine
MAINTAINER michael

ENV VERSION 0.0.1

RUN echo "copying jar file"
COPY target/coding-challenge-$VERSION-SNAPSHOT.jar coding-challenge-$VERSION-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/coding-challenge-$VERSION-SNAPSHOT.jar"]