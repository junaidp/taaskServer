package com.echo.taask.customerTask;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import java.util.ArrayList;
import java.util.Date;
@Data
public class CustomerTaskRequest {
    @Pattern(regexp = "^(HIGH|MODERATE|LOW)$",message = "Only HIGH, MODERATE, LOW Is Accepted In TaskPriority")
    private String taskPriority;
    private String customerSerialNumber;
    @NotNull(message = "Task Name Should Not Be Null")
    private String taskName;
    private Date dueDate;
    private Date assignedDate;
    @Pattern(regexp = "^(TODO|DOING|DONE)$",message = "Only TODO, DOING, DONE Is Accepted In Status")
    private String status;
    private ArrayList<SubTask> subTask;
}
