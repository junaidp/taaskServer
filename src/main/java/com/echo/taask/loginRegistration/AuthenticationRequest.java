package com.echo.taask.loginRegistration;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.stereotype.Component;

@Data@Builder
@AllArgsConstructor@NoArgsConstructor
@Component
public class AuthenticationRequest {
  @NotNull(message = "Email Should Not Be Null")
  private String email;
  @NotNull(message = "Passowrd Should Not Be Null")
  String password;
}
