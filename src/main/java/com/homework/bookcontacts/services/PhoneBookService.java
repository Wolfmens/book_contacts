package com.homework.bookcontacts.services;

import com.homework.bookcontacts.model.Contact;

import java.util.List;
import java.util.Optional;

public interface PhoneBookService {

    List<Contact> findAll();

    Contact findById(Long id);

    Contact save(Contact contact);

    void deleteById (Long id);
}
