package com.example.demo3.chat;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

public class Message {

    private String subscribe;
    private String from;
    private String message;

    private String subscribeId;

    private String type;

    public Message() {
    }

    public Message(String message){
        this.message=message;
    }

    public Message(String subscribe, String from, String message,String subscribeId,String type) {
        this.subscribe = subscribe;
        this.from = from;
        this.message = message;
        this.subscribeId=subscribeId;
        this.type=type;
    }

    public String getSubscribe() {
        return subscribe;
    }

    public void setSubscribe(String subscribe) {
        this.subscribe = subscribe;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSubscribeId() {
        return subscribeId;
    }

    public void setSubscribeId(String subscribeId) {
        this.subscribeId = subscribeId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}