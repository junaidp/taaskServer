package com.echo.taask.user;

import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserRequest {
    @Pattern(regexp = "^[A-Za-z]+$", message = "First Name can only contain Alphabets")
    private String firstname;
    @Pattern(regexp = "^[A-Za-z]+$", message = "Last Name can only contain Alphabets")
    private String lastname;
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$",
            message = "The string must contain at least one lowercase letter.\n" +
                    "The string must contain at least one uppercase letter.\n" +
                    "The string must contain at least one digit.\n" +
                    "The string must contain at least one special character.\n" +
                    "The string can only consist of alphanumeric characters and the specified special characters.\n" +
                    "The string must be at least 8 characters long.")
    private String password;
}
