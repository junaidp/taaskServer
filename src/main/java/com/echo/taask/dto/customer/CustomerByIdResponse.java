package com.echo.taask.dto.customer;

import lombok.Data;

import java.util.List;

@Data
public class CustomerByIdResponse {
    private String serialNumber;
    private String name;
    private String category;
    private String customerStage;
    private String customerSince;
    private String location;
    private String website;
    private String customerNotes;
    private ContactResponse contacts;
    private List<CustomerLinkDto> customerLink;
    private List<CustomerFilesDto> customerFiles;
    private ImageResponse image;
}
