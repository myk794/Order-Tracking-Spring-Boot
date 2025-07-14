package com.yigit.kafkaProject.Service;

import com.yigit.kafkaProject.entity.Order;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;


@Service
public class OrderProducerService {

    private final KafkaTemplate<String, Order> kafkaTemplate; 

    public OrderProducerService(KafkaTemplate<String, Order> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public CompletableFuture<SendResult<String, Order>> sendOrderEvent(String topic, Order order) {

        return kafkaTemplate.send(topic, order.getOrderId(), order);
    }
}