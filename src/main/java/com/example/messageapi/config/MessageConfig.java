package com.example.messageapi.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;

import java.util.concurrent.CompletableFuture;

@Configuration
public class MessageConfig {

    @Bean
    public MessageProducer messageProducer() {
        return new MessageProducer();
    }


    @Bean
    public MessageListener messageListener() {
        return new MessageListener();
    }

    public static class MessageProducer {
        @Value(value = "${message.topic.name}")
        private String topicName;

        @Autowired
        private KafkaTemplate<String, Object> kafkaTemplate;

        public void sendMessage(Object message) {
            CompletableFuture<SendResult<String, Object>> future = kafkaTemplate.send(topicName, message);
            future.whenComplete((result, ex) -> {
                if (ex == null) {
                    System.out.println("Sent message=[" + message +
                            "] with offset=[" + result.getRecordMetadata().offset() + "]");
                } else {
                    System.out.println("Unable to send message=[" + message + "] due to : " + ex.getMessage());
                }
            });
        }
    }

    public static class MessageListener {
        @KafkaListener(topics = "${message.topic.name}")
        public void listen(Object message) {
            System.out.println("Received Message: " + message);
        }
    }
}
