package com.pallavtyagi.kafkaavro.producer;

import com.pallavtyagi.kafkaavro.model.Order;
import io.confluent.kafka.serializers.KafkaAvroSerializer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

import static java.lang.Thread.sleep;

public class Main {

    public static void main(String s[]) throws InterruptedException {
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, KafkaAvroSerializer.class.getName());

        props.put("schema.registry.url", "http://localhost:8081");

        OrderEventGenerator eventGenerator = new OrderEventGenerator();


        org.apache.kafka.clients.producer.Producer<String, Order> producer = new KafkaProducer<>(props);

        for (int i=10; i< 1000; i++) {
            Order order = eventGenerator.generateEvent();
            producer.send(new ProducerRecord<String, Order>("order_events", order.getOderId().toString(), order));
            System.out.println("Published Order!");
            sleep(1000);
        }
    }

}
