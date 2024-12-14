package com.webclothes.webclothesservice.service.contact;

import com.webclothes.webclothesservice.model.Contact;

import java.util.List;

public interface IContactService {
    Contact addContact(String fullName, String email, String phoneNumber, String message);

    List<Contact> getAllContact();

    void deleteContact(Long id);
}
