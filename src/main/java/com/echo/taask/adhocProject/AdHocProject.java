package com.echo.taask.adhocProject;

import lombok.Data;
import nonapi.io.github.classgraph.json.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Document@Data
public class AdHocProject {
    @Id
    private String id;
    private String uuid;
    private String customerId;
    private String projectName;
    private List<String> projectTask;
    private Date dueDate;
    private String Status;
    private String discription;
    private String resource;
    private String email;
}
