FROM amazoncorretto:17-alphine-jdk

COPY target/portfolio-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java", "-jar", "/app.jar"]