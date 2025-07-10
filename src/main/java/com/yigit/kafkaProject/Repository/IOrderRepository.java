package com.yigit.kafkaProject.Repository;

import com.yigit.kafkaProject.entity.Order;
import org.springframework.data.couchbase.repository.CouchbaseRepository;

public interface IOrderRepository extends CouchbaseRepository<Order,String> {
    
}
