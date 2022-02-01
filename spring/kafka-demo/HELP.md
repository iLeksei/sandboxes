*  pull images, create docker network bridge and run
2) docker pull bitnami/kafka
3) docker pull confluentinc/cp-zookeeper
4) docker network create kafkanet --driver bridge
5) docker run -d --network=kafkanet --name=zookeeper -e ZOOKEEPER_CLIENT_PORT=2181 -e ZOOKEEPER_TICK_TIME=2000 -p 2181:2181 confluentinc/cp-zookeeper
6) docker run -d --network=kafkanet --name=kafka -e KAFKA_ZOOKEEPER_CONNECT=zookeeper:2181 -e KAFKA_ADVERTISED_LISTENERS=PLAINTEXT://localhost:9092 -e KAFKA_OFFSETS_TOPIC_REPLICATION_FACTOR=1 -p 9092:9092 confluentinc/cp-kafka

* docker exec -it kafka bash
    ~/usr/bin/kafka-topics

* create demo kafka topic
1) /bin/kafka-topics --create --topic demo-topic --bootstrap-server kafka:9092

* useful commands:
show topics: /bin/kafka-topics --list --bootstrap-server kafka:9092
topic info: /bin/kafka-topics --describe --topic demo-topic --bootstrap-server kafka:9092
send message to topic: /bin/kafka-console-producer --topic demo-topic --bootstrap-server kafka:9092
read messages from topic: /bin/kafka-console-consumer --topic demo-topic --from-beginning --bootstrap-server kafka:9092
change topic props:  /bin/kafka-topics --alter --topic demo-topic --partitions 3 --bootstrap-server kafka:9092

* for testing with apache jmeter use: Pepper-Box plugin  
   article: https://www.blazemeter.com/blog/apache-kafka-how-to-load-test-with-jmeter