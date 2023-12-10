package com.homework.bookcontacts.repositories;

import com.homework.bookcontacts.model.Contact;

import java.util.List;
import java.util.Optional;

public interface PhoneBookRepository {

    List<Contact> findAll();

    Optional<Contact> findById(Long id);

    Contact save(Contact contact);

    Contact update(Contact contact);

    void deleteById (Long id);
}
