package com.echo.taask.controller;
import com.echo.taask.helper.UserHelper;
import com.echo.taask.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("api/user")
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class UserController {
  private UserHelper service;


  @GetMapping("/getAllUsers")
    public ResponseEntity<List<User>> getUsers(){

      try {
          return new ResponseEntity<>(service.getAllUsers(),HttpStatus.OK);
      }catch (Exception ex)
      {
          return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
      }
  }

    @GetMapping("/cleanDb")
    public ResponseEntity<String> cleanDb(){

        try {
            return new ResponseEntity<>(service.cleanDb(),HttpStatus.OK);
        }catch (Exception ex)
        {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/login")
    public ResponseEntity<User> login(String userName , String password){

        try {
            return service.login(userName , password);
        }catch (Exception ex)
        {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

  @PostMapping("saveUser")
  public ResponseEntity<String> saveUser(@RequestBody User user){
      try {
          return new ResponseEntity<>(service.saveUser(user),HttpStatus.OK);
      }catch (Exception ex)
      {
          return new ResponseEntity<>("Failed to Save User", HttpStatus.BAD_REQUEST);
      }
  }

     @Autowired
    public UserController(UserHelper service) {
        System.out.println("UserController arg constructor called");
        this.service = service;
    }
}
