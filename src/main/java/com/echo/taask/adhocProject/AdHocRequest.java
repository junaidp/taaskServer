package com.echo.taask.adhocProject;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class AdHocRequest {
    private String uuid;
    private String customerId;
    private String projectName;
    private List<String> projectTask;
    private String discription;
    private String resource;
    private Date dueDate;
    private String Status;
}
