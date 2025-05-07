FROM amazoncorretto:17-alpine-jdk
LABEL maintainer="UTMB score API"
COPY target/utmb-scores-ws-1.0.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]