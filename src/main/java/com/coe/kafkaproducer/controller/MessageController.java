package com.coe.kafkaproducer.controller;

import com.coe.kafkaproducer.model.GroupMember;
import com.coe.kafkaproducer.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("message")
public class MessageController {

    @Autowired
    private KafkaTemplate<String, Message> kafkaMessageTemplate;

    @Autowired
    private KafkaTemplate<String, Integer> kafkaMessageIdTemplate;

    @PostMapping("")
    public String saveMessage(@RequestBody Message message){
        kafkaMessageTemplate.send("message-save-topic",message);
        return "Message sent successfully";
    }

    @PutMapping("/{messageId}")
    public String updateMessage(@PathVariable int messageId, @RequestBody Message message){
        if (messageId <= 0){
            return "Message ID can not be null or 0";
        }
        message.setMessageId(messageId);
        kafkaMessageTemplate.send("message-update-topic",message);
        return "Message updated successfully";
    }

    @DeleteMapping("/{messageId}")
    public String deleteMessage(@PathVariable("messageId") int messageId){
        kafkaMessageIdTemplate.send("message-delete-topic",messageId);
        return "Message deleted successfully";
    }

}
