package com.coe.kafkaproducer.controller;

import com.coe.kafkaproducer.model.Conversation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("conversation")
public class ConversationController {

    @Autowired
    private KafkaTemplate<String, Conversation> kafkaConversationTemplate;

    @PostMapping("")
    public String saveConversation(@RequestBody Conversation conversation){
        kafkaConversationTemplate.send("conversation-save-topic",conversation);
        return "Conversation sent successfully";
    }

}
