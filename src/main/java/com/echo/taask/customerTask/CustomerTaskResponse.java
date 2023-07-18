package com.echo.taask.customerTask;

import com.echo.taask.customer.dto.ImageResponse;
import com.echo.taask.recources.Image;
import lombok.Data;

import java.util.Date;

@Data
public class CustomerTaskResponse {
    private String taskPriority;
    private String customerName;
    private String customerStage;
    private String customerTask;
    private Date dueDate;
    private Date assignedDate;
    private String status;
    private ImageResponse image;
}
