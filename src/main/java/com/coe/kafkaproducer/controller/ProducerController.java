package com.coe.kafkaproducer.controller;

import com.coe.kafkaproducer.model.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("produce")
public class ProducerController {
    @Autowired
    private KafkaTemplate<String, Contact> kafkaTemplate;

    @PostMapping("/prod")
    public String send(@RequestBody Contact contact){
        kafkaTemplate.send("NewTopic2",contact);
        return "Element sent successfully";
    }

}
