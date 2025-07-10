package com.yigit.kafkaProject.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderStatus {
    private String status;
    private String location;
    private String timestamp;
    private String description;
}
