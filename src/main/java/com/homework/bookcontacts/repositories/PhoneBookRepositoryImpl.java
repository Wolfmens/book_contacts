package com.homework.bookcontacts.repositories;

import com.homework.bookcontacts.exceptions.NotFoundContactException;
import com.homework.bookcontacts.model.Contact;
import com.homework.bookcontacts.repositories.mapper.ContactRowMapper;
import com.homework.bookcontacts.utils.Constants;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.ArgumentPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapperResultSetExtractor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
@Slf4j
public class PhoneBookRepositoryImpl implements PhoneBookRepository {

    private final JdbcTemplate jdbcTemplate;


    @Override
    public List<Contact> findAll() {
        log.debug("Call findAll from PhoneBookRepositoryImpl");
        String sql = "SELECT * FROM contacts";

        return jdbcTemplate.query(sql, new ContactRowMapper());
    }

    @Override
    public Optional<Contact> findById(Long id) {
        log.debug("Call findById from PhoneBookRepositoryImpl");
        String sql = "SELECT * FROM contacts WHERE id=?";

        Contact contact = DataAccessUtils
                .singleResult(jdbcTemplate.query(sql,
                        new ArgumentPreparedStatementSetter(new Object[]{id}),
                        new RowMapperResultSetExtractor<>(new ContactRowMapper(), 1)));

        return Optional.ofNullable(contact);
    }

    @Override
    public Contact save(Contact contact) {
        log.debug("Call save from PhoneBookRepositoryImpl by contact {}", contact);
        if (contact.getId() != null) {
            update(contact);
            return contact;
        }
        contact.setId(Constants.getId());
        String sql = "INSERT INTO contacts (first_name, last_name, email, phone_number, id) VALUES (?,?,?,?,?)";

        jdbcTemplate.update(sql,
                contact.getFirstName(),
                contact.getLastName(),
                contact.getEmail(),
                contact.getPhoneNumber(),
                contact.getId());

        return contact;
    }

    @Override
    public Contact update(Contact contact) {
        log.debug("Call update from PhoneBookRepositoryImpl by contact {}", contact);
        Contact contactFromBase = findById(contact.getId()).orElse(null);

        if (contactFromBase != null) {
            String sql = "UPDATE contacts SET first_name=?, last_name=?, email=?, phone_number=? WHERE id=?";
            jdbcTemplate.update(sql,
                    contact.getFirstName(),
                    contact.getLastName(),
                    contact.getEmail(),
                    contact.getPhoneNumber(),
                    contact.getId());
            return contact;
        }

        log.warn("Contact with ID {} not found", contact.getId());
        throw new NotFoundContactException("Task for update not found! ID : " + contact.getId());
    }

    @Override
    public void deleteById(Long id) {
        log.debug("Call deleteById from PhoneBookRepositoryImpl by id contact {}", id);
        String sql = "DELETE FROM contacts WHERE id=?";

        jdbcTemplate.update(sql,id);
    }
}

