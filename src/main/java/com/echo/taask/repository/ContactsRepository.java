package com.echo.taask.repository;

import com.echo.taask.model.Contacts;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface ContactsRepository extends MongoRepository<Contacts , String> {
    @Query("{'Contactsname': ?0'}")
    Contacts findByName(String name);
}
