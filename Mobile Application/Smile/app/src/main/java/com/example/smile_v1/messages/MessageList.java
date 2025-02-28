package com.example.smile_v1.messages;

public class MessageList {

    private String name, mobile, lastMessage, chatKey;
    private int unSeenMessage;

    public MessageList(String name, String mobile, String lastMessage, int unSeenMessage, String chatKey) {
        this.name = name;
        this.mobile = mobile;
        this.lastMessage = lastMessage;
        this.unSeenMessage = unSeenMessage;
        this.chatKey = chatKey;
    }

    public String getName() {
        return name;
    }

    public String getMobile() {
        return mobile;
    }

    public String getLastMessage() {
        return lastMessage;
    }


    public int getUnSeenMessage() {
        return unSeenMessage;
    }

    public String getChatKey() {
        return chatKey;
    }
}
