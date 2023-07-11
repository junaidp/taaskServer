package com.echo.taask.other.dto;

import com.echo.taask.customer.Customer;
import com.echo.taask.other.model.SubTask;

import java.io.Serializable;
import java.util.ArrayList;


public class TaskDTO implements Serializable {
    private String taskName;
    private Customer customer;
    private ArrayList<SubTask> subTask;
    private String fileId;
    private String dueDate;
    private String time;

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public ArrayList<SubTask> getSubTask() {
        return subTask;
    }

    public void setSubTask(ArrayList<SubTask> subTask) {
        this.subTask = subTask;
    }

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }
}
