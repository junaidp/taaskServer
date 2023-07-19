package com.echo.taask.adhocProject;

import lombok.Data;
import nonapi.io.github.classgraph.json.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document@Data
public class AdHocProject {
    @Id
    private String id;
    private String uuid;
    private String customerId;



}
