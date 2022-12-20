package com.coe.kafkaproducer.model;

import lombok.Data;
import java.util.Date;

@Data
public class Message {

    private int messageId;
    private Contact fromContact;
    private String messageText;
    private Date sentDatetime;
    private Conversation conversation;


    public Message(){}

    public Message(int messageId, Contact fromContact, String messageText, Date sentDatetime, Conversation conversation) {
        this.messageId = messageId;
        this.fromContact = fromContact;
        this.messageText = messageText;
        this.sentDatetime = sentDatetime;
        this.conversation = conversation;
    }
}
