package com.giocom.sharingit;

import java.util.UUID;


public class Contact {
    private String username;
    private String phone;
    private String id;

    Contact(String username, String phone, String id) {
        this.username = username;
        this.phone = phone;

        if (id == null){
            setId();
        } else {
            updateId(id);
        }
    }

    public String getId(){
        return this.id;
    }

    public void setId() {
        this.id = UUID.randomUUID().toString();
    }

    public void updateId(String id){
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}