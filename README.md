# How to run the Kafka dummy app

### Build the app jar file

1. Navigate to the KafkaDummy folder.
2. Run the command `mvn clean install`

### Run the Kafka broker

1. Run the command: `docker-compose pu -d`

### Run the Kafka Dummy App

1. Run the command: `docker build . -t solution:1.0`
2. Run the command: `docker run --name solution --network solution_kafka-net -p 8080:8080 solution:1.0`


