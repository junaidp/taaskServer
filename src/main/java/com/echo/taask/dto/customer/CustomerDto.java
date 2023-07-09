package com.echo.taask.dto.customer;

import com.echo.taask.model.Contact;
import com.echo.taask.model.Image;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDto {
    private String category;
    private String customerStage;
    private String customerSince;
    private String location;
    private String website;
    private Contact contacts;
    private String customerNotes;
    private String name;
}
