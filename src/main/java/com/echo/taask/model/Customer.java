package com.echo.taask.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import nonapi.io.github.classgraph.json.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
@Data
@Builder
@AllArgsConstructor
public class Customer {
    @Id
    private String id;
    private String category;
    private String name;
    private String location;
    private String website;
    private String customerStage;
    private String customerSince;
    private String customerNotes;
    private Contact contacts;
    private Image image;
    private String email;

}
