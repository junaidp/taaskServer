package com.echo.taask.helper;

import ch.qos.logback.core.encoder.EchoEncoder;
import com.echo.taask.model.User;
import com.echo.taask.repository.*;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserHelper {

    @Autowired
    UserRepository userRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    ContactsRepository contactsRepository;

    @Autowired
    FilesRepository filesRepository;

    @Autowired
    MeetingRepository meetingRepository;

    @Autowired
    ResourcesRepository resourcesRepository;

    @Autowired
    TaskRepository taskRepository;

    @Autowired
    MongoOperations mongoOperations;

    Gson gson = new Gson();
    public List<User> getAllUsers(){
        try{
            return userRepository.findAll();
        }catch (Exception e)
        {
            throw e;
        }
    }

    public String saveUser(User user){
        try {
            //String password = user.getPassword();
            userRepository.save(user);
            return "user saved";
        }catch (Exception e)
        {
            throw e;
        }
    }

    public String deleteAllUsers()
    {
        try{
            userRepository.deleteAll();
            return "All users Deleted";

        }
        catch (Exception e)
        {
            throw e;
        }
    }

    public ResponseEntity<User> login(String userName , String password){
        try{
            Query query = new Query();
            query.addCriteria(Criteria.where("email").is(userName));
            query.addCriteria(Criteria.where("password").is(password));
            User user = mongoOperations.findOne(query,User.class);
            if(user!= null)
                return new ResponseEntity<>(user ,HttpStatus.OK);
            else
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        catch (Exception e)
        {
            throw e;
        }
    }

    public String updateUser(User user)
    {
        try{
            Query query = new Query();
            query.addCriteria(Criteria.where("id").is(user.getId()));
            User updateuser = mongoOperations.findOne(query,User.class);
            if(updateuser.equals(null))
            {
                return "could not find user";
            }
            else {
                userRepository.save(user);
                return "Updated Successfully";
            }
        }catch (Exception e)
        {
            throw e;
        }
    }

    public String cleanDb(){
        try {
            customerRepository.deleteAll();
            contactsRepository.deleteAll();
            resourcesRepository.deleteAll();
            taskRepository.deleteAll();
            meetingRepository.deleteAll();
            return "Db Cleaned";
        }catch(Exception ex)
        {
            return "Error in cleaning db: "+ ex.getMessage();
        }
    }
}
