#FROM openjdk:17-jdk-slim
#
#WORKDIR /app
#
## Copy the application JAR file into the container at /usr/src/app
#COPY build/libs/api-0.0.1-SNAPSHOT.jar api.jar
#
## Make port 8080 available to the world outside this container
#EXPOSE 8080
#
## Run the application
#CMD ["java", "-jar", "api.jar"]


## Stage 1: Build the application
FROM openjdk:17-jdk-slim as builder
WORKDIR /app
ENV SPRING_PROFILES_ACTIVE=docker
COPY . .
RUN ./gradlew build -x test

# Stage 2: Create a smaller image
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=builder /app/build/libs/api-0.0.1-SNAPSHOT.jar api.jar
ENTRYPOINT ["java", "-jar", "api.jar"]