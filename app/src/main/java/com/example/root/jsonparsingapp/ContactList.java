package com.example.root.jsonparsingapp;

import com.example.root.jsonparsingapp.Contact;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ContactList {

    @SerializedName("contacts")
    @Expose
    private List<Contact> contacts = null;

    public List<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }

}