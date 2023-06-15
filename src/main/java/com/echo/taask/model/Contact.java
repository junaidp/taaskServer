package com.echo.taask.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Contact {
    private String id;
    private String name;
    private String jobTitle;
    private String emailAddress;
    private String location;

}
