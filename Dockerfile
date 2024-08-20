FROM adoptopenjdk:21.0.4_7-jdk-hotspot


COPY target/Personal-0.0.1-SNAPSHOT.jar app.jar



ENTRYPOINT ["java","-jar","/app.jar"]