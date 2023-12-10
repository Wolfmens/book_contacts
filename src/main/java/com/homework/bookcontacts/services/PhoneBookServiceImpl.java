package com.homework.bookcontacts.services;

import com.homework.bookcontacts.exceptions.NotFoundContactException;
import com.homework.bookcontacts.model.Contact;
import com.homework.bookcontacts.repositories.PhoneBookRepository;
import com.homework.bookcontacts.utils.Constants;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class PhoneBookServiceImpl implements PhoneBookService {

    private final PhoneBookRepository repository;

    @Override
    public List<Contact> findAll() {
        log.debug("Call findAll from PhoneBookServiceImpl");

        return repository.findAll();
    }

    @Override
    public Contact findById(Long id) {
        log.debug("Call findById from PhoneBookServiceImpl by id  {}", id);

        return repository.findById(id)
                .orElseThrow(() -> new NotFoundContactException(Constants.MESSAGE_NOT_FOUND_CONTACT));
    }

    @Override
    public Contact save(Contact contact) {
        log.debug("Call save from PhoneBookServiceImpl by contact {}", contact);

        return repository.save(contact);
    }

    @Override
    public void deleteById(Long id) {
        log.debug("Call deleteById from PhoneBookServiceImpl by id {}", id);

        repository.deleteById(id);
    }
}
