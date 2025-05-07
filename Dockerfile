FROM sapmachine:17.0.6
WORKDIR /app
COPY target/utmb-scores-ws-1.0.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]