package com.coe.kafkaproducer.controller;

import com.coe.kafkaproducer.model.Conversation;
import com.coe.kafkaproducer.model.GroupMember;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("groupmember")
public class GroupMemberController {

    @Autowired
    private KafkaTemplate<String, GroupMember> kafkaGroupMemberTemplate;

    @Autowired
    private KafkaTemplate<String, Integer> kafkaGroupMemberIdTemplate;

    @PostMapping("")
    public String saveGroupMember(@RequestBody GroupMember groupMember){
        kafkaGroupMemberTemplate.send("groupmember-save-topic",groupMember);
        return "GroupMember sent successfully";
    }

    @PutMapping("/{groupMemberId}")
    public String updateGroupMember(@PathVariable int groupMemberId, @RequestBody GroupMember groupMember){
        if (groupMemberId <= 0){
            return "GroupMember ID can not be null or 0";
        }
        groupMember.setGroupMemberId(groupMemberId);
        kafkaGroupMemberTemplate.send("groupmember-update-topic",groupMember);
        return "GroupMember updated successfully";
    }

    @DeleteMapping("/{groupmemberId}")
    public String deleteGroupMember(@PathVariable("groupmemberId") int groupMemberId){
        kafkaGroupMemberIdTemplate.send("groupmember-delete-topic",groupMemberId);
        return "GroupMember deleted successfully";
    }

}
