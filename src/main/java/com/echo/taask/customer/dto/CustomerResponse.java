package com.echo.taask.customer.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomerResponse {
    private String serialNumber;
    private String name;
    private String category;
    private String customerStage;
    private String customerSince;
    private String location;
    private String website;
    private String customerNotes;
    private ContactResponse contacts;
    private ImageResponse image;
}
