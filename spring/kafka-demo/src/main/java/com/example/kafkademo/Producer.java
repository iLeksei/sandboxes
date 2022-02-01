package com.example.kafkademo;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.IntegerDeserializer;
import org.apache.kafka.common.serialization.IntegerSerializer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;
import java.util.stream.Stream;

@Slf4j
public class Producer {

    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, IntegerSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        KafkaProducer<Integer, String> producer = new KafkaProducer<>(properties);
        Stream.iterate(0,  i -> i + 1 )
                .limit(20)
                .peek((i) -> {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                })
                .forEach( i -> {
                    ProducerRecord<Integer, String> record =
                            new ProducerRecord<>("demo-topic", i % 3, "" + i);
                    producer.send(record, (metadata, exception) -> {
                        if (exception != null) {
                            log.error("Producer error: {}", exception);
                        } else {
                            log.info("received new metadata. topic: {}, partition: {}, offset: {}, time: {}",
                                    metadata.topic(), metadata.partition(), metadata.offset(), metadata.timestamp());
                        }
                    });
                });

        producer.flush();
        producer.close();

    }

}
