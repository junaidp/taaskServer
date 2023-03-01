package com.echo.taask.model;


import nonapi.io.github.classgraph.json.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Customer {

    @Id
    private String id;

    private String category;
    private String name;
    private String location;
    private String website;
    private String customerstage;
    private String customersince;
    private String customernotes;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getCustomerstage() {
        return customerstage;
    }

    public void setCustomerstage(String customerstage) {
        this.customerstage = customerstage;
    }

    public String getCustomersince() {
        return customersince;
    }

    public void setCustomersince(String customersince) {
        this.customersince = customersince;
    }

    public String getCustomernotes() {
        return customernotes;
    }

    public void setCustomernotes(String customernotes) {
        this.customernotes = customernotes;
    }
}
