package com.yigit.kafkaProject.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StatusHistory {
    private String status;
    private String location;
    private Date timestamp;
    private String description;
}
