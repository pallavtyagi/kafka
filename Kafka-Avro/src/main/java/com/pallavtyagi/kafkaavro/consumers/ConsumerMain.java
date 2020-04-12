package com.pallavtyagi.kafkaavro.consumers;

import com.pallavtyagi.kafkaavro.model.Order;
import io.confluent.kafka.serializers.KafkaAvroDeserializer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.util.Arrays;
import java.util.Properties;

public class ConsumerMain {

    public static void main(String s[]) {
        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, KafkaAvroDeserializer.class.getName());
        props.put("schema.registry.url", "http://localhost:8081");
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "test");
        props.put("specific.avro.reader", "true");

        KafkaConsumer<String, Order> consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Arrays.asList("order_events"));
        while(true) {
            ConsumerRecords<String, Order> records = consumer.poll(1000);
            records.forEach(record -> {
                System.out.println("[Recorded Order:"+record.key()+", "+record.value());
            });

            consumer.commitSync();
        }




    }



}
