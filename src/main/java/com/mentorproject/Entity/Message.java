package com.mentorproject.Entity;

public class Message {

    private String sender;

    private String receiver;

    private String message;

    private  Integer isRead;

    public Message(String sender, String receiver, String message, Integer isRead) {
        this.sender = sender;
        this.receiver = receiver;
        this.message = message;
        this.isRead = isRead;
    }

    public Message() {

    }

    @Override
    public String toString() {
        return "Message{" +
                "sender='" + sender + '\'' +
                ", receiver='" + receiver + '\'' +
                ", message='" + message + '\'' +
                ", isRead=" + isRead +
                '}';
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getIsRead() {
        return isRead;
    }

    public void setIsRead(Integer isRead) {
        this.isRead = isRead;
    }
}
