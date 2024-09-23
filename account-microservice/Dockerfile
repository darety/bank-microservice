

# start with the base image containing java runtime
FROM openjdk:17-jdk-slim

#Information of who maintains the image
MAINTAINER olukotuno

#Add the application jar to the image
COPY target/account-microservice-0.0.1-SNAPSHOT.jar /app/account-microservice.jar

WORKDIR /app

#Execute the application
ENTRYPOINT ["java","-jar","/app/account-microservice.jar"]