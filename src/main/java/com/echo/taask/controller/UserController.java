package com.echo.taask.controller;

import com.echo.taask.helper.TaskHelper;
import com.echo.taask.helper.UserHelper;

import com.echo.taask.model.Task;
import com.echo.taask.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

    @RequestMapping("api/user")
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class UserController {
  private UserHelper service;


  @GetMapping("getAllUsers")
    public List<User> getUsers(){
      return service.getAllUsers();
  }

  @PostMapping("saveUser")
  public String saveUser(@RequestParam User user){
      return service.saveUser(user);
  }

     @Autowired
    public UserController(UserHelper service) {
        System.out.println("UserController arg constructor called");
        this.service = service;
    }
}