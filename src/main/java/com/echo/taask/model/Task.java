package com.echo.taask.model;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import nonapi.io.github.classgraph.json.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Date;

@Document
public class Task {
    @Id
    private String id;
    @NotNull(message = "Task Name Should Not Be Null")
    private String taskName;
    @Pattern(regexp = "^[0-9a-fA-F]{24}$", message = "Randomly Generated Hexadecimal String Of Registered Customer")
    private String customerId;

    private ArrayList<SubTask> subTask;

    private String fileId;

    private Date dueDate;

    private String formattedTime;

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public ArrayList<SubTask> getSubTask() {
        return subTask;
    }

    public void setSubTask(ArrayList<SubTask> subTask) {
        this.subTask = subTask;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getFormattedTime() {
        return formattedTime;
    }

    public void setFormattedTime(String formattedTime) {
        this.formattedTime = formattedTime;
    }
}
