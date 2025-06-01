FROM maven:3.9.9-eclipse-temurin

## Define working directory.
WORKDIR /data
COPY ./src  /data/src
COPY ./pom.xml  /data/pom.xml

## Compile and run jar app
EXPOSE 8080
RUN mvn package

CMD ["java", "-jar", "/data/target/task13Docker.jar"]