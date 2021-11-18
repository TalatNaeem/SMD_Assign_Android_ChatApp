package com.talat.i170213_i170208;

public class Contact {
    private String name, phone, last_message, last_active;
    int image;

    public Contact() {}

    public Contact(int image, String last_active, String last_message, String name, String phone) {
        this.image = image;
        this.last_active = last_active;
        this.last_message = last_message;
        this.name = name;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getLast_message() {
        return last_message;
    }

    public void setLast_message(String last_message) {
        this.last_message = last_message;
    }

    public String getLast_active() {
        return last_active;
    }

    public void setLast_active(String last_active) {
        this.last_active = last_active;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
