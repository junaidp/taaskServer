package com.echo.taask.customer.reporisoties;

import com.echo.taask.customer.dto.Contact;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface ContactsRepository extends MongoRepository<Contact, String> {
}
