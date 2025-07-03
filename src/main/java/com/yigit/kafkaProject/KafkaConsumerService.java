package com.yigit.kafkaProject;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yigit.kafkaProject.Repository.PersonRepository;
import com.yigit.kafkaProject.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {

    private final ObjectMapper objectMapper;

    @Autowired
    private PersonRepository _personRepository;

    public KafkaConsumerService(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @KafkaListener(topics = "my-topic", groupId = "my-group")
    public void listen(String message) {
        try{
            Person person= objectMapper.readValue(message, Person.class);
             _personRepository.save(person);
            System.out.println("Recieved message: " + person);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}