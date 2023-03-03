package com.echo.taask.controller;


import com.echo.taask.helper.ContactsHelper;
import com.echo.taask.model.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("api/contacts")
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class ContactsController {


    @Autowired
    ContactsHelper contactsHelper;

    @PostMapping("savecontact")
    public String savecontacts(@RequestParam Contact contact)
    {
        return contactsHelper.saveContacts(contact);
    }

    @GetMapping("getallcontacts")
    public List<Contact> getallcontacts()
    {
        return contactsHelper.getAllcontacts();
    }


}
