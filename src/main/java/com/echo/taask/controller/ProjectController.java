package com.echo.taask.controller;

import com.echo.taask.helper.ProjectHelper;
import com.echo.taask.model.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("api/project")
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class ProjectController {

    private ProjectHelper projectHelper;

    @Autowired
    public ProjectController(ProjectHelper projectHelper){
        this.projectHelper = projectHelper;
    }

    @PostMapping("/saveProject")
    public String saveMeeting(@RequestBody Project project){
        try {
            return projectHelper.saveProject(project);
        }catch (Exception ex){
            return "Failed to Save Project";
        }
    }

    @GetMapping("/getAllProjects")
    public ResponseEntity<List<Project>> getAllProjects()
    {
        try {
            return new ResponseEntity<>(projectHelper.getAllProjects(), HttpStatus.OK);
        }catch (Exception ex){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
