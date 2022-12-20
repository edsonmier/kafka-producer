package com.coe.kafkaproducer.controller;

import com.coe.kafkaproducer.model.Contact;
import com.coe.kafkaproducer.model.Conversation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("conversation")
public class ConversationController {

    @Autowired
    private KafkaTemplate<String, Conversation> kafkaConversationTemplate;

    @Autowired
    private KafkaTemplate<String, Integer> kafkaConversationIdTemplate;

    @PostMapping("")
    public String saveConversation(@RequestBody Conversation conversation){
        kafkaConversationTemplate.send("conversation-save-topic",conversation);
        return "Conversation sent successfully";
    }

    @PutMapping("")
    public String updateConversation(@RequestBody Conversation conversation){
        if (conversation.getConversationId() <= 0){
            return "Conversation ID can not be null or 0";}
        kafkaConversationTemplate.send("conversation-update-topic",conversation);
        return "Conversation updated successfully";
    }

    @DeleteMapping("/{conversationId}")
    public String deleteConversation(@PathVariable("conversationId") int conversationId){
        kafkaConversationIdTemplate.send("conversation-delete-topic",conversationId);
        return "Conversation deleted successfully";
    }

}
