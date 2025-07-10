package com.yigit.kafkaProject.Service;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public KafkaProducerService(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(String topic, String person) {


        for (int i = 0; i < 1000; i++) {
            String key = "key-" + i; // Her mesaj için farklı bir anahtar
            String value = "message-" + i;
            ProducerRecord<String, String> record = new ProducerRecord<>("test-topic",key, value);
            kafkaTemplate.send(record);
        }
        kafkaTemplate.send(topic, person);

        System.out.println("Sent message: " + person + " to topic: " + topic);
    }
}