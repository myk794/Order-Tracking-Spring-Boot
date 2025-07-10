package com.yigit.kafkaProject.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.sql.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    @Id
    private String id;

    private String orderId;

    private String currentStatus;

    private Date estimatedDelivery;

    private List<StatusHistory> history;

}
