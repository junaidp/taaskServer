package com.echo.taask.controller;


import com.echo.taask.helper.ResourcesHelper;
import com.echo.taask.model.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public String saveResources(@RequestBody Resource resource)
    {
        return resourcesHelper.saveResources(resource);
    }

    @GetMapping("/getResources")
    public List<Resource> getResources(@RequestParam String userid){
        return resourcesHelper.getResources(userid);
    }

}
