package com.echo.taask.user;

import com.echo.taask.customer.dto.ImageResponse;
import com.echo.taask.recources.Image;
import lombok.Data;

@Data
public class UserResponse {
    private String firstname;
    private String lastname;
    private String email;
    private ImageResponse image;
}
