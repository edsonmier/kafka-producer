package com.coe.kafkaproducer.controller;

import com.coe.kafkaproducer.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("message")
public class MessageController {

    @Autowired
    private KafkaTemplate<String, Message> kafkaMessageTemplate;

    @PostMapping("")
    public String saveMessage(@RequestBody Message message){
        kafkaMessageTemplate.send("message-save-topic",message);
        return "Message sent successfully";
    }

}
