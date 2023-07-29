package com.echo.taask.customer;


import com.echo.taask.customer.dto.Contact;
import com.echo.taask.recources.Image;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import nonapi.io.github.classgraph.json.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document@AllArgsConstructor
@Data@Builder@RequiredArgsConstructor
public class Customer {
    @Id
    private String id;
    private String serialNumber;
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
