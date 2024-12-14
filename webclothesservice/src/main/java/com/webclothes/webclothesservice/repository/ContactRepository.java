package com.webclothes.webclothesservice.repository;

import com.webclothes.webclothesservice.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository<Contact, Long> {
}
