package com.talat.i170213_i170208;

public class Message {
    private String message, time, username;

    public Message() {}

    public Message(String message, String time, String username) {
        this.message = message;
        this.time = time;
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
