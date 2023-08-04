package com.echo.taask.snapshots.dto;

import lombok.Data;

import java.util.List;

@Data
public class SnapShots {
    private long totalNumberOfCustomer;
    private int totaltaskCount;
    private int totalNumberOfProjects;
    private List<CategoryCount> customerCategoryCount;
    private List<StageCount> customerStageCount;
    private List<TaskCount> taskCount;
    private List<AdHocCount> adHocCount;

}
