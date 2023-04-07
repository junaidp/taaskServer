package com.echo.taask.helper;

import com.echo.taask.dto.MeetingDTO;
import com.echo.taask.model.Meeting;
import com.echo.taask.model.Project;
import com.echo.taask.repository.MeetingRepository;
import com.echo.taask.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProjectHelper {

    @Autowired
    ProjectRepository projectRepository;

    public String saveProject(Project project)
    {
        try {
            projectRepository.save(project);
            return "Project Saved";
        }catch (Exception e) {
            throw e;
        }
    }

    public List<Project> getAllProjects()
    {
        try{
            return projectRepository.findAll();
        }catch (Exception e)
        {
            throw e;
        }
    }
}
