package com.echo.taask.controller;


import com.echo.taask.helper.ContactsHelper;
import com.echo.taask.helper.CustomerHelper;
import com.echo.taask.model.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("api/contacts")
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class ContactsController {


    private ContactsHelper contactsHelper;

    @Autowired
    public ContactsController(ContactsHelper contactsHelper)
    {
        this.contactsHelper =  contactsHelper;
    }

    @PostMapping("/saveContact")
    public ResponseEntity<String> saveContacts(@RequestBody Contact contact)
    {
        try {
            return new ResponseEntity<>(contactsHelper.saveContacts(contact), HttpStatus.OK);
        }catch (Exception ex){
            return new ResponseEntity<>("Failed to Save Contacts" , HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getAllContacts")
    public ResponseEntity<List<Contact>> getAllContacts()
    {
        try{
            return new ResponseEntity<>(contactsHelper.getAllContacts(),HttpStatus.OK);
        }catch (Exception ex){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

}
