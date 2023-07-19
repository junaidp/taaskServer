package com.echo.taask.user;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;

@RequestMapping("user")
@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
public class UserController {
    private final UserHelper service;
    private final UserRepository userRepository;

    @PostMapping("/update")
    public ResponseEntity<?> updateUser(Principal principal,
                                        @Valid @RequestPart("updateUser") UpdateUserRequest updateUser,
                                        @RequestPart(value = "image", required = false) MultipartFile image) {
        try {
            String authenticatedUsername = principal.getName();
            User user = userRepository.findByEmail(authenticatedUsername);
            return service.updateUser(user, updateUser, image);
        } catch (Exception e) {
            return new ResponseEntity<>("User not found", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("profile")
    public ResponseEntity<?> getUser(Principal principal) {
        try {
            String authenticatedUsername = principal.getName();
            return service.getUserByEmail(authenticatedUsername);
        } catch (Exception ex) {
            return new ResponseEntity<>("Server Error Contact Help Center!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
