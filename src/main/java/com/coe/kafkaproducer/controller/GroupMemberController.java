package com.coe.kafkaproducer.controller;

import com.coe.kafkaproducer.model.GroupMember;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("groupmember")
public class GroupMemberController {

    @Autowired
    private KafkaTemplate<String, GroupMember> kafkaGroupMemberTemplate;

    @PostMapping("")
    public String saveGroupMember(@RequestBody GroupMember groupMember){
        kafkaGroupMemberTemplate.send("groupmember-save-topic",groupMember);
        return "GroupMember sent successfully";
    }

}
