package com.webclothes.webclothesservice.service.contact;

public interface IContactService {
    Contact addContact(String fullName, String email, String phoneNumber, String message);

    List<Contact> getAllContact();

    void deleteContact(Long id);
}
