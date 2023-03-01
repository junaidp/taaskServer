package com.echo.taask.helper;

import ch.qos.logback.core.encoder.EchoEncoder;
import com.echo.taask.model.User;
import com.echo.taask.repository.UserRepository;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserHelper {

    @Autowired
    UserRepository userRepository;

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
}
