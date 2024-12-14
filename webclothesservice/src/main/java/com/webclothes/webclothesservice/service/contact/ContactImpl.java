package com.webclothes.webclothesservice.service.contact;


import com.webclothes.webclothesservice.model.Contact;
import com.webclothes.webclothesservice.repository.ContactRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ContactImpl implements IContactService{
    private final ContactRepository contactRepository;
    @Override
    public Contact addContact(String fullName, String email, String phoneNumber, String message) {
        Contact contact = new Contact();
        contact.setFullName(fullName);
        contact.setEmail(email);
        contact.setPhoneNumber(phoneNumber);
        contact.setMessage(message);
        return contactRepository.save(contact);
    }

    @Override
    public List<Contact> getAllContact() {
        return contactRepository.findAll();
    }

    @Override
    public void deleteContact(Long id) {
        Optional<Contact> contact = contactRepository.findById(id);
        if(contact.isPresent()){
            contactRepository.deleteById(id);
        }
    }
}
