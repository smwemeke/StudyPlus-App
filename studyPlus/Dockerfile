FROM amazoncorretto:21
RUN apt-get update && apt-get install -y ca-certificates

# setting the working directory inside the container to /app
WORKDIR /app

EXPOSE 8080

#copying the jar files from the target directory on the host to the /app directory in the container
COPY target/studyplus-1.0.1.jar /app

#Specifying the command to run the application using the java -jar command
ENTRYPOINT ["java", "-jar", "studyplus-1.0.1.jar"]

