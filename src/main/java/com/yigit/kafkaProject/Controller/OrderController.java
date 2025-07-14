package com.yigit.kafkaProject.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.yigit.kafkaProject.Service.OrderProducerService;
import com.yigit.kafkaProject.entity.Order;
import com.yigit.kafkaProject.entity.OrderStatus;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.kafka.support.SendResult;

import java.sql.Date;
import java.time.Instant;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderProducerService orderProducerService;

    public OrderController(OrderProducerService orderProducerService) {
        this.orderProducerService = orderProducerService;
    }

    @PostMapping("/create")
    public ResponseEntity<String> createOrder(@RequestBody Order order) {
        order.setCurrentStatus("Created");

        try {

            SendResult<String, Order> result = orderProducerService.sendOrderEvent("orders-topic", order)
                    .get(5, TimeUnit.SECONDS);

            System.out.println("Order event sent: " + order + " to topic " + result.getRecordMetadata().topic() +
                    ", partition " + result.getRecordMetadata().partition() +
                    ", offset " + result.getRecordMetadata().offset());
            return new ResponseEntity<>("Order created and event sent to Kafka: " + order.getOrderId(), HttpStatus.OK);

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return new ResponseEntity<>("Order creation interrupted: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (ExecutionException e) {
            return new ResponseEntity<>("Failed to send order event to Kafka: " + e.getCause().getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (TimeoutException e) {
            return new ResponseEntity<>("Order event send to Kafka timed out: " + e.getMessage(), HttpStatus.REQUEST_TIMEOUT);
        }
    }


    @PostMapping("/update-status")
    public ResponseEntity<String> updateOrderStatus(@RequestBody Order order) {
        try {
            SendResult<String, Order> result = orderProducerService.sendOrderEvent("orders-topic", order)
                    .get(5, TimeUnit.SECONDS);

            System.out.println("Order status updated and event sent: " + order);
            return new ResponseEntity<>("Order status updated and event sent for: " + order.getOrderId(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to update order status: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}