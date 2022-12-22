FROM eclipse-temurin:11-jdk-alpine

ARG APP_NAME=app/target/*.jar
COPY ${APP_NAME} api.jar

EXPOSE 8080
ENTRYPOINT ["java","-Xmx512m","-Dserver.port=${PORT}", "-jar", "api.jar"]