FROM openjdk:11-alpine
EXPOSE 8090
ADD target/miniassignment2-0.0.1-SNAPSHOT.jar miniassignment2-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "/miniassignment2-0.0.1-SNAPSHOT.jar"]