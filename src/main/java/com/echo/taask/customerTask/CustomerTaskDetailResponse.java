package com.echo.taask.customerTask;

import com.echo.taask.customer.dto.CustomerFilesDto;
import com.echo.taask.customer.dto.CustomerLinkDto;
import com.echo.taask.customer.dto.ImageResponse;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class CustomerTaskDetailResponse {
    private String taskSerial;
    private String taskPriority;
    private String customerName;
    private String customerStage;
    private String customerTask;
    private Date dueDate;
    private Date assignedDate;
    private String status;
    private ArrayList<SubTask> subTask;
    private List<CustomerFilesDto> files;
    private ImageResponse image;
}
