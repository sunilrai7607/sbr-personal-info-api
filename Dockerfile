FROM openjdk:8-jdk-alpine
MAINTAINER Sunil Rai <sunilrai7607@gmail.com>
VOLUME /app
ARG version
ENV version_number=$version
COPY ./build/libs/sbr-personal-info-api-$version_number.jar sbr-personal-info-api.jar
ENTRYPOINT ["java", "-jar","/sbr-personal-info-api.jar"]