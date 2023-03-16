package com.echo.taask.dto;

public class TaskSnapShotDTO {
    int activeTasks;
    int completedTasks;
    int assignTasks;
    int RecievedTasks;

    public int getActiveTasks() {
        return activeTasks;
    }

    public void setActiveTasks(int activeTasks) {
        this.activeTasks = activeTasks;
    }

    public int getCompletedTasks() {
        return completedTasks;
    }

    public void setCompletedTasks(int completedTasks) {
        this.completedTasks = completedTasks;
    }

    public int getAssignTasks() {
        return assignTasks;
    }

    public void setAssignTasks(int assignTasks) {
        this.assignTasks = assignTasks;
    }

    public int getRecievedTasks() {
        return RecievedTasks;
    }

    public void setRecievedTasks(int recievedTasks) {
        RecievedTasks = recievedTasks;
    }
}
