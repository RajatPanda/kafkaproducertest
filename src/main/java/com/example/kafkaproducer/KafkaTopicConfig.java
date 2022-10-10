package com.example.kafkaproducer;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaAdmin;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaTopicConfig {
    @Value(value = "${kafka.bootstrapAddress}")
    private String bootstrapAddress;

    @Bean
    public KafkaAdmin kafkaAdmin() {
        Map<String, Object> configs = new HashMap<>();
        configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        return new KafkaAdmin(configs);
    }

    @Bean
    public NewTopic stringTopic() {
        return new NewTopic("TEST.STRING.CONFIG", 9, (short) 3);
    }

    @Bean
    public NewTopic stringTopicPartition3() {
        return new NewTopic("TEST.STRING.PARTITION3.CONFIG", 3, (short) 3);
    }

    @Bean
    public NewTopic avroTopic() {
        return new NewTopic("TEST.AVRO.CONFIG", 9, (short) 3);
    }

    @Bean
    public NewTopic jsonTopic() {
        return new NewTopic("TEST.JSON.CONFIG", 9, (short) 3);
    }
}
