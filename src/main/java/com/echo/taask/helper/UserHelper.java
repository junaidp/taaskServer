package com.echo.taask.helper;

import com.echo.taask.model.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserHelper {

    public List<User> getAllUsers(){
        return null;
    }

    public String saveUser(User user){
        return "user saved";
    }
}
