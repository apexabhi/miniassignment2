FROM openjdk:11
EXPOSE 8090
ADD target/dockerassignment.jar dockerassignment.jar
ENTRYPOINT ["java", "-jar", "/dockerassignment.jar"]