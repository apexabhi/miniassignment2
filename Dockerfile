FROM openjdk:11
EXPOSE 8090
ADD target/devopsassignment.jar devopsassignment.jar
ENTRYPOINT ["java", "-jar", "/devopsassignment.jar"]
