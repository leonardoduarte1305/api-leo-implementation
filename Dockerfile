FROM openjdk:12-alpine

ARG APP_NAME=target/*.jar
COPY ${APP_NAME} api.jar

ENV PORT=${PORT}


ENTRYPOINT ["java","-Xmx512m","-Dserver.port=${PORT}", "-jar", "api.jar"]