package com.echo.taask.repository;

import com.echo.taask.model.Contact;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface ContactsRepository extends MongoRepository<Contact, String> {
    @Query("{'Contactsname': ?0'}")
    Contact findByName(String name);
}
