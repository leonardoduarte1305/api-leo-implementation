FROM adoptopenjdk:11-jre-hotspot as builder
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} application.jar
RUN java -Djarmode=layertools -jar application.jar extract

FROM adoptopenjdk:11-jre-hotspot
COPY --from=builder dependencies/ ./
COPY --from=builder snapshot-dependencies/ ./
COPY --from=builder spring-boot-loader/ ./
COPY --from=builder application/ ./

ENV HOST_MACHINE_IP=${HOST_MACHINE_IP}

EXPOSE 8383

ENTRYPOINT ["java","-Xmx512m","-Dserver.port=8383", "org.springframework.boot.loader.JarLauncher"]