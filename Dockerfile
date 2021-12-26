FROM openjdk:8-jdk-alpine
COPY ./target/solution-0.0.1-SNAPSHOT.jar ./app/solution.jar
ENTRYPOINT ["java","-jar","./app/solution.jar"]

#docker build . -t solution:1.0
#docker run --rm --name solution --network solution_kafka-net -p 8080:8080 solution:1.0
