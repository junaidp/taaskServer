package com.echo.taask.loginRegistration;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
public class Login_RegistrationController {
    private final Login_RegService service;

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody AuthenticationRequest request) {
        return service.authenticate(request);
    }

    @PostMapping("/register")
    public ResponseEntity<?> RegisterUser(@Valid @RequestBody RegisterUser register) {
        try {
            return service.registerUser(register);
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ResponseEntity<>("Please Contact Help Center", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
