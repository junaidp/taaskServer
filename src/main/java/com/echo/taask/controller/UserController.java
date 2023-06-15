package com.echo.taask.controller;

import com.echo.taask.dto.auth.UpdateUserRequest;
import com.echo.taask.helper.UserHelper;
import com.echo.taask.model.User;
import com.echo.taask.repository.UserRepository;
import io.swagger.annotations.Authorization;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.util.List;

@RequestMapping("user")
@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
public class UserController {
    private final UserHelper service;
    private final UserRepository userRepository;

    @PostMapping("/update")
    public ResponseEntity<?> updateUser(Principal principal, @RequestHeader("Authorization") String Authorization,
                                        @Valid @RequestPart("updateUser") UpdateUserRequest updateUser,
                                        @Valid @RequestPart("image") MultipartFile image) {
        try {
            String authenticatedUsername = principal.getName();
            User user = userRepository.findByEmail(authenticatedUsername);
            return service.updateUser(user, updateUser, image);
        } catch (Exception e) {
            return new ResponseEntity<>("User not found", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("profile")
    public ResponseEntity<?> getUser(Principal principal, @RequestHeader("Authorization") String Authorization,
                                     @RequestHeader("userName") String userName) {
        try {
            String authenticatedUsername = principal.getName();
            if (userName.equals(authenticatedUsername)) {
                return service.getUserByEmail(authenticatedUsername);
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("You Are Not Allowed To View Any Other Resource");
            }
        } catch (Exception ex) {
            return new ResponseEntity<>("Server Error Contact Help Center!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
