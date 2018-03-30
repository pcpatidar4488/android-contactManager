package com.example.berylsystems.apiusingservices.network.pojo.response.signin;


import java.util.ArrayList;

/**
 * Created by BerylSystems on 17-Mar-18.
 */

public class ResponseSignIn {
    int status;
    String message;
    ArrayList<Contact> contact;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ArrayList<Contact> getContact() {
        return contact;
    }

    public void setContact(ArrayList<Contact> contact) {
        this.contact = contact;
    }
}
