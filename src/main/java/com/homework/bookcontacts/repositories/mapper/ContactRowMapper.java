package com.homework.bookcontacts.repositories.mapper;

import com.homework.bookcontacts.model.Contact;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ContactRowMapper implements RowMapper<Contact> {
    @Override
    public Contact mapRow(ResultSet rs, int rowNum) throws SQLException {
        Contact contact = new Contact();

        contact.setId(rs.getLong(Contact.Fields.id));
        contact.setFirstName(rs.getString("first_name"));
        contact.setLastName(rs.getString("last_name"));
        contact.setEmail(rs.getString(Contact.Fields.email));
        contact.setPhoneNumber(rs.getLong("phone_number"));

        return contact;
    }
}
