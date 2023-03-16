package com.echo.taask.controller;


import com.echo.taask.helper.LoginHelper;
import com.echo.taask.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("api/login")
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class LoginController {

    private LoginHelper loginHelper;

    @Autowired
    public LoginController(LoginHelper loginHelper){
        this.loginHelper = loginHelper;
    }
    @PostMapping("/loginUser")
    public ResponseEntity<String> loginUser(@RequestParam String email,String password){
        try {
            return new ResponseEntity<>(loginHelper.login(email,password),HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("Could not Login ", HttpStatus.BAD_REQUEST);
        }
    }
}
