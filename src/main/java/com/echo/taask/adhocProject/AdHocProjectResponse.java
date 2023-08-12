package com.echo.taask.adhocProject;

import com.echo.taask.customer.dto.CustomerFilesDto;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class AdHocProjectResponse {
    private String uuid;
    private String projectName;
    private String resource;
    private String discription;
    private String customerName;
    private List<String> projectTask;
    private Date dueDate;
    private String Status;
    private List<CustomerFilesDto> file;
}
