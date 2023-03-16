package com.echo.taask.dto;



public class SnapShotDTO {
    ProjectSnapShotDTO projectSnapShotDTO;
    TaskSnapShotDTO taskSnapShotDTO;
    CustomerSnapShotDTO customerSnapShotDTO;

    public ProjectSnapShotDTO getProjectSnapShotDTO() {
        return projectSnapShotDTO;
    }

    public void setProjectSnapShotDTO(ProjectSnapShotDTO projectSnapShotDTO) {
        this.projectSnapShotDTO = projectSnapShotDTO;
    }

    public TaskSnapShotDTO getTaskSnapShotDTO() {
        return taskSnapShotDTO;
    }

    public void setTaskSnapShotDTO(TaskSnapShotDTO taskSnapShotDTO) {
        this.taskSnapShotDTO = taskSnapShotDTO;
    }

    public CustomerSnapShotDTO getCustomerSnapShotDTO() {
        return customerSnapShotDTO;
    }

    public void setCustomerSnapShotDTO(CustomerSnapShotDTO customerSnapShotDTO) {
        this.customerSnapShotDTO = customerSnapShotDTO;
    }
}
