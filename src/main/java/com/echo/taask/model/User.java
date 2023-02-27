package com.echo.taask.model;

import java.util.Date;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class User {

    @Id
    private String id;
    private String name;
    private String email;
    private String password;
    private String userRole;
    private Date creationDate = new Date();

}
