package com.echo.taask.controller;


import com.echo.taask.helper.TaskHelper;
import com.echo.taask.model.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("api/task")
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class TaskController {

    private TaskHelper taskHelper;
    public TaskController(TaskHelper taskHelper)
    {
        this.taskHelper = taskHelper;
    }

    @PostMapping("/saveTask")
    public ResponseEntity<String> saveTask(@RequestBody Task task)
    {
        try {
            return new ResponseEntity<>(taskHelper.saveTasks(task),HttpStatus.OK);
        }catch (Exception ex){
            return new ResponseEntity<>("Failed to Save Task", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("getAllTasks")
    public ResponseEntity<List<Task>> getAllTasks()
    {
        try {
            return new ResponseEntity<>(taskHelper.getAllTasks(),HttpStatus.OK);
        }catch (Exception ex){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("getTask")
    public ResponseEntity<String> getTask(@RequestParam String userid)
    {
        try {
            return new ResponseEntity<>(taskHelper.getTasks(userid),HttpStatus.OK);
        }catch (Exception ex)
        {
            return new ResponseEntity<>("Failed to Get Task",HttpStatus.BAD_REQUEST);
        }
    }

}
