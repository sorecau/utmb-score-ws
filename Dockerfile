FROM openjdk:11-jre-slim
WORKDIR /app
COPY target/utmb-scores-ws-1.0.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]