package com.yigit.kafkaProject.Service;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public KafkaProducerService(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(String topic, String person) {

        kafkaTemplate.send(topic, person);

        System.out.println("Sent message: " + person + " to topic: " + topic);
    }
}