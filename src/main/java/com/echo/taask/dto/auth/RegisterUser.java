package com.echo.taask.dto.auth;

import com.echo.taask.dto.senatization.UniqueEmail;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterUser {
    @NotNull(message = "First Name Should Not Be Null")
    @Pattern(regexp = "^[A-Za-z]+$", message = "First Name can only contain Alphabets")
    private String firstname;
    @NotNull(message = "Last Name Should Not Be Null")
    @Pattern(regexp = "^[A-Za-z]+$", message = "Last Name can only contain Alphabets")
    private String lastname;
    @NotNull(message = "Email Should Not Be Null")
    @UniqueEmail
    @Pattern(regexp = "^[A-Za-z0-9+_.-]+@(.+)$", message = "Invalid Email Format")
    private String email;
    @NotNull(message = "Password Should Not Be Null")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$",
            message = "The string must contain at least one lowercase letter.\n" +
                    "The string must contain at least one uppercase letter.\n" +
                    "The string must contain at least one digit.\n" +
                    "The string must contain at least one special character.\n" +
                    "The string can only consist of alphanumeric characters and the specified special characters.\n" +
                    "The string must be at least 8 characters long.")
    private String password;
}
