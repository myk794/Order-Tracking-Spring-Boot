package com.yigit.kafkaProject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yigit.kafkaProject.entity.Person;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/kafka")
public class KafkaTestController {

    private final KafkaProducerService producerService;
    private final ObjectMapper objectMapper;

    public KafkaTestController(KafkaProducerService producerService, ObjectMapper objectMapper) {
        this.producerService = producerService;
        this.objectMapper = objectMapper;
    }

    @GetMapping("/send")
    public String sendMessage(@RequestBody Person person) throws JsonProcessingException {
        String jsonPerson = objectMapper.writeValueAsString(person);
        producerService.sendMessage("my-topic", jsonPerson);
        return "Message sent: " + jsonPerson;
    }
}