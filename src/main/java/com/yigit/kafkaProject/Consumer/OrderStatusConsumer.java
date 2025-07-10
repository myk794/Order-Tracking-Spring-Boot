package com.yigit.kafkaProject.Consumer;

import com.yigit.kafkaProject.entity.Order;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;
import java.util.Map;

@Component
public class OrderStatusConsumer {


    private final Map<String, Order> currentOrderStatuses = new ConcurrentHashMap<>();

    @KafkaListener(topics = "orders-topic", groupId = "order-status-group", containerFactory = "kafkaListenerContainerFactory")
    public void listenOrderEvents(Order order,
                                  @Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
                                  @Header(KafkaHeaders.RECEIVED_PARTITION) int partition,
                                  @Header(KafkaHeaders.OFFSET) long offset) {


        currentOrderStatuses.put(order.getOrderId(), order);

        System.out.println(String.format("Consumed Order Event -> Topic: %s, Partition: %d, Offset: %d, Order: %s",
                topic, partition, offset, order));


        printAllOrderStatuses();
    }

    private void printAllOrderStatuses() {
        System.out.println("\n--- Current Order Statuses ---");
        if (currentOrderStatuses.isEmpty()) {
            System.out.println("No orders processed yet.");
        } else {
            currentOrderStatuses.forEach((orderId, order) ->
                    System.out.println("Order ID: " + orderId + ", Status: " + order.getCurrentStatus())
            );
        }
        System.out.println("------------------------------\n");
    }
}