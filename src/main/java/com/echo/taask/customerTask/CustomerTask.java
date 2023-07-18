package com.echo.taask.customerTask;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import nonapi.io.github.classgraph.json.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Date;

@Document
@Data
public class CustomerTask {
    @Id
    private String id;
    private String taskPriority;
    private String customerTaskSerial;
    private String customerSerialNumber;
    private String userEmail;
    @NotNull(message = "Task Name Should Not Be Null")
    private String taskName;
    private Date dueDate;
    private Date assignedDate;
    private String status;
    private ArrayList<SubTask> subTask;
}
