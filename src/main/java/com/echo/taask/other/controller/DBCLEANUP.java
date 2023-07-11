package com.echo.taask.other.controller;

import com.echo.taask.customer.reporisoties.ContactsRepository;
import com.echo.taask.customer.reporisoties.CustomerRepository;
import com.echo.taask.other.repository.MeetingRepository;
import com.echo.taask.other.repository.ResourcesRepository;
import com.echo.taask.other.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class DBCLEANUP {
    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    ContactsRepository contactsRepository;


    @Autowired
    MeetingRepository meetingRepository;

    @Autowired
    ResourcesRepository resourcesRepository;

    @Autowired
    TaskRepository taskRepository;

    @Autowired
    MongoOperations mongoOperations;

    @GetMapping("/cleanDb")
    public ResponseEntity<String> cleanDb() {

        try {
            return new ResponseEntity<>(clean(), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    public String clean() {
        try {
            customerRepository.deleteAll();
            contactsRepository.deleteAll();
            resourcesRepository.deleteAll();
            taskRepository.deleteAll();
            meetingRepository.deleteAll();
            return "Db Cleaned";
        } catch (Exception ex) {
            return "Error in cleaning db: " + ex.getMessage();
        }
    }
}
