#FROM openjdk:22-jdk
## for copying jar file
#ADD target/InventoryManagement.jar InventoryManagement.jar
#ENTRYPOINT ["java", "-jar", "/InventoryManagement.jar"]


# Use an appropriate base image with Java and necessary build tools
FROM openjdk:22-jdk AS build

## Install Maven (or Gradle, if you use Gradle) for building the project
#RUN apt-get update && apt-get install -y maven

# Download and install Maven
RUN curl -fsSL https://archive.apache.org/dist/maven/maven-3/3.8.6/binaries/apache-maven-3.8.6-bin.tar.gz | tar xz -C /opt \
    && ln -s /opt/apache-maven-3.8.6/bin/mvn /usr/bin/mvn

# Set the working directory
WORKDIR /app

# Copy your Maven or Gradle project files
COPY pom.xml ./
COPY src ./src

# Build the JAR file
RUN mvn clean package

FROM openjdk:22-jdk

# Copy the built JAR file from the build stage
COPY --from=build /app/target/InventoryManagement.jar /InventoryManagement.jar

# Define the command to run the JAR file
ENTRYPOINT ["java", "-jar", "/InventoryManagement.jar"]

#docker build -t inventory-management-app .
#
#docker run -p 8080:8080 inventory-management-app (image name)

#docker run --name mysql-container --network my-network -e MYSQL_ROOT_PASSWORD=password -e MYSQL_DATABASE=inventoryManagement -e MYSQL_USER=user -e MYSQL_PASSWORD=password -p 3307:3306 -d mysql:latest


