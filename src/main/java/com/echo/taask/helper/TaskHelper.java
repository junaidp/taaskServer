package com.echo.taask.helper;

import com.echo.taask.model.Customer;
import com.echo.taask.model.Task;
import com.echo.taask.repository.TaskRepository;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Component;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Component
public class TaskHelper {

    @Autowired
    TaskRepository taskRepository;

    @Autowired
    MongoOperations mongoOperations;
    @Autowired
    FilesHelper filesHelper;


    Gson gson = new Gson();
    public List<Task> getAllTasks(){
        try{
            return taskRepository.findAll();
        }catch (Exception e)
        {
            throw e;
        }
    }



    public String saveTasks(Task task, MultipartFile file){
        try {
            task.setFileId(filesHelper.uploadFile(file));
            taskRepository.save(task);
            return "Task saved";
        }catch (Exception e)
        {
            throw e;
        }
    }

    public String getTasks(String Customerid){
        try{
            System.out.println("Getting tasks for : " + Customerid);
            Query query = new Query();
            query.addCriteria(Criteria.where("userid").is(Customerid));
            List<Task> tasks = mongoOperations.find(query, Task.class);
            for(Task task:tasks)
            {
                System.out.println("Tasks found : " + task.getTaskName());
            }
            String json = gson.toJson(tasks);
            return json;
        }catch (Exception e)
        {
            throw e;
        }
    }
}
