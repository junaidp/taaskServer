package com.echo.taask.controller;


import com.echo.taask.helper.TaskHelper;
import com.echo.taask.model.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("api/task")
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class TaskController {
    @Autowired
    TaskHelper taskhelper;

    @PostMapping("saveTask")
    public String saveTask(@RequestParam Task task)
    {
        return taskhelper.saveTasks(task);
    }

    @GetMapping("getAllTasks")
    public List<Task> getAllTasks()
    {
        return taskhelper.getAllTasks();
    }

    @GetMapping("gettask")
    public String gettask(@RequestParam String userid)
    {
        return taskhelper.getTasks(userid);
    }

}
