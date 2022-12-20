package com.coe.kafkaproducer.controller;

import com.coe.kafkaproducer.model.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("contact")
public class ContactController {
    @Autowired
    private KafkaTemplate<String, Contact> kafkaContactTemplate;

    @Autowired
    private KafkaTemplate<String, Integer> kafkaContactIdTemplate;

    @PostMapping("")
    public String saveContact(@RequestBody Contact contact){
        kafkaContactTemplate.send("contact-save-topic",contact);
        return "Contact saved successfully";
    }

    @PutMapping("")
    public String updateContact(@RequestBody Contact contact){
        if (contact.getContactId() <= 0){
            return "Contact ID can not be null or 0";}
        kafkaContactTemplate.send("contact-update-topic",contact);
        return "Contact updated successfully";
    }

    @DeleteMapping("/{contactId}")
    public String deleteContact(@PathVariable("contactId") int contactId){
        kafkaContactIdTemplate.send("contact-delete-topic",contactId);
        return "Contact deleted successfully";
    }

}
