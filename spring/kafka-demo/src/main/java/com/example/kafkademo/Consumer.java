package com.example.kafkademo;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.IntegerDeserializer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

@Slf4j
public class Consumer {

    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, IntegerDeserializer.class.getName());
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.put(ConsumerConfig.GROUP_ID_CONFIG, "demo-group");
        properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "latest"); // earliest, none

        KafkaConsumer<Integer, String> consumer = new KafkaConsumer<>(properties);
        consumer.subscribe(Collections.singleton("demo-topic"));
        while (true) {
            ConsumerRecords<Integer, String> records = consumer.poll(Duration.ofMillis(200));
            for (ConsumerRecord<Integer, String> record: records) {
                log.info("key: {}, value: {}, partition: {}, offset: {}",
                        record.key(), record.value(), record.partition(), record.offset());
            }

        }
    }

}
