package com.echo.taask.loginRegistration;
import org.springframework.security.core.AuthenticationException;
import com.echo.taask.user.User;
import com.echo.taask.user.UserRepository;
import com.echo.taask.security_configuration.JwtService;
import lombok.AllArgsConstructor;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@AllArgsConstructor
@Service
public class Login_RegService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public ResponseEntity<?> authenticate(AuthenticationRequest request) {
        try {
            if (authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    request.getEmail(), request.getPassword())).isAuthenticated()) {
                var user = userRepository.findByEmail(request.getEmail());
                return ResponseEntity.status(HttpStatus.OK).body(
                        AuthenticationResponse.builder()
                                .firstname(user.getFirstname())
                                .lastname(user.getLastname())
                                .email(user.getEmail())
                                .token(jwtService.generateToken(user))
                                .build());
            }else {
                return ResponseEntity.status(HttpStatus.OK).body("User Email Or Password Incorrect");
            }
        }catch (AuthenticationException e) {
            // Handle authentication failures (e.g., invalid credentials)
            return ResponseEntity.status(HttpStatus.OK).body("Invalid credentials");
        } catch (Exception e) {
            // Handle other exceptions
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Please Contact Help Center");
        }
    }


    public ResponseEntity<?> registerUser(RegisterUser register) {
        var user = User.builder()
                .firstname(register.getFirstname())
                .lastname(register.getLastname())
                .email(register.getEmail())
                .password(passwordEncoder.encode(register.getPassword()))
                .creationDate(new Date())
                .userRole(null)
                .image(null)
                .build();
        userRepository.save(user);
        JSONObject responseBody = new JSONObject();
        responseBody.put("Registerd_User", new JSONObject()
                .put("firstname", register.getFirstname())
                .put("lastname", register.getLastname())
                .put("email", register.getEmail()));
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(responseBody.toString());
    }
}
