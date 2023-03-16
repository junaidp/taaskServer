package com.echo.taask.helper;

import com.echo.taask.model.User;
import com.echo.taask.repository.UserRepository;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

@Component
public class LoginHelper {


    @Autowired
    public MongoOperations mongoOperation;

    Gson gson = new Gson();

    public String login(String email, String password)
    {
        try {

            System.out.println("{ email : '" + email + "'}");
            System.out.println("{ password : '" + password + "'}");
            Query query = new Query();
            query.addCriteria(Criteria.where("email").is(email).and("password").is(password));
            //	BasicQuery query1 = new BasicQuery("{ name : '"+name+"'} , { password: '"+password+"'}");
            System.out.println("ff");
            User user = mongoOperation.findOne(query, User.class);
            System.out.println(user);
            String json = gson.toJson(user);
            return json;
        } catch (Exception ex) {
            System.out.println("Error is :" + ex.getMessage());
            throw ex;
        }
    }
}
