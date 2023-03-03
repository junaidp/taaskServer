package com.echo.taask.helper;

import com.echo.taask.model.Contact;
import com.echo.taask.repository.ContactsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ContactsHelper {

    @Autowired
    ContactsRepository contactsRepository;

    public String saveContacts(Contact contact)
    {
        try{
            contactsRepository.save(contact);
            return "Contacts saved";
        }catch (Exception e)
        {
            throw e;
        }
    }

    public List<Contact> getAllcontacts()
    {
        try{
             return contactsRepository.findAll();
        }catch (Exception e)
        {
            throw e;
        }
    }
}
