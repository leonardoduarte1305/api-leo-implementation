FROM eclipse-temurin:17-jdk-alpine as builder

VOLUME /tmp
COPY ./target/* ./
RUN mvnw clean package

ARG JAR_FILE
COPY ${JAR_FILE} app.jar

RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring

RUN java -Djarmode=layertools -jar application.jar extract

FROM adoptopenjdk:11-jre-hotspot
COPY --from=builder dependencies/ ./
COPY --from=builder snapshot-dependencies/ ./
COPY --from=builder spring-boot-loader/ ./
COPY --from=builder application/ ./

ENV HOST_MACHINE_IP=${HOST_MACHINE_IP}

EXPOSE 8080

ENTRYPOINT ["java","-jar","-Xmx512m","-Dserver.port=${PORT:8080}","/app.jar"]