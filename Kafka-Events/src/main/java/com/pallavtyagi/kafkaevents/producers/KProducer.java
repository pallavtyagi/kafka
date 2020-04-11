package com.pallavtyagi.kafkaevents.producers;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

public class KProducer {

    public static void main(String s[]) {
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        org.apache.kafka.clients.producer.Producer<String, String> producer = new KafkaProducer<String, String>(props);

        for (int i=10; i< 1000; i++) {
            producer.send(new ProducerRecord<>("order_events", Integer.toString(i), Integer.toString(i)));
            System.out.println(i);
        }

    }

}
