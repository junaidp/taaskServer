package com.echo.taask.controller;


import com.echo.taask.helper.ResourcesHelper;
import com.echo.taask.model.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RequestMapping("api/resources")
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class ResourcesController {

    private ResourcesHelper resourcesHelper;
    @Autowired
    public ResourcesController (ResourcesHelper resourcesHelper){
        this.resourcesHelper = resourcesHelper;
    }

    @PostMapping("/saveResources")
    public ResponseEntity<String> saveResources (@RequestParam("file") MultipartFile file,@RequestPart("resources") Resource resources)
    {

        try {
            return new ResponseEntity<>(resourcesHelper.saveResources(resources,file), HttpStatus.OK);
        }catch (Exception e)
        {
            throw e;
        }
    }

    @GetMapping("/getResources")
    public List<Resource> getResources(@RequestParam String userId){

        return resourcesHelper.getResources(userId);
    }

}
