package com.echo.taask.controller;


import com.echo.taask.dto.TaskDTO;
import com.echo.taask.helper.TaskHelper;
import com.echo.taask.model.Task;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
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

    //TODO SAVE
    @PostMapping("/saveTask")
    public ResponseEntity<String> saveTask(@RequestParam("file") MultipartFile file, @RequestPart("task") @Valid Task task)
    {
        try {
            return new ResponseEntity<>(taskHelper.saveTasks(task,file),HttpStatus.OK);
        }catch (Exception ex){
            return new ResponseEntity<>("Failed to Save Task", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("getAllTasks")
    public ResponseEntity<List<TaskDTO>> getAllTasks()
    {
        try {
            return new ResponseEntity<>(taskHelper.getAllTasks(),HttpStatus.OK);
        }catch (Exception ex){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("getTasks")
    public ResponseEntity<String> getCustomerTasks(@RequestParam String customerId)
    {
        try {
            return new ResponseEntity<>(taskHelper.getTasks(customerId),HttpStatus.OK);
        }catch (Exception ex)
        {
            return new ResponseEntity<>("Failed to Get Task",HttpStatus.BAD_REQUEST);
        }
    }

}
