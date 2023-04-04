package com.echo.taask.helper;

import com.echo.taask.dto.TaskDTO;
import com.echo.taask.model.Customer;
import com.echo.taask.model.Task;
import com.echo.taask.repository.CustomerRepository;
import com.echo.taask.repository.TaskRepository;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Component;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.multipart.MultipartFile;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Component
public class TaskHelper {

    @Autowired
    TaskRepository taskRepository;
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    MongoOperations mongoOperations;
    @Autowired
    FilesHelper filesHelper;
    Gson gson = new Gson();

    private static final String DATE_FORMAT = "dd/MM/yyyy HH:mm a";

    public List<TaskDTO> getAllTasks(){
        try{
            List<Task> tasksModel = taskRepository.findAll();
            return toDto(tasksModel);
        }catch (Exception e)
        {
            throw e;
        }
    }

    private List<TaskDTO> toDto(List<Task> tasksModel) {
        List<TaskDTO> taskDTOS = new ArrayList<TaskDTO>();
        for(Task task: tasksModel){
            TaskDTO dto = new TaskDTO();
            Customer customer = customerRepository.findById(task.getCustomerId()).get();
            dto.setCustomer(customer);
            dto.setTaskName(task.getTaskName());
            dto.setSubTask(task.getSubTask());
            dto.setFileId(task.getFileId());
            DateFormat formatter = new SimpleDateFormat(DATE_FORMAT);
            String formattedDate = formatter.format(task.getDueDate());
            dto.setDueDate(formattedDate.substring(0,10));
            dto.setTime(formattedDate.substring(11));
            taskDTOS.add(dto);
        }
        return taskDTOS;
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

    public String getTasks(String customerId){
        try{
            System.out.println("Getting tasks for : " + customerId);
            Query query = new Query();
            query.addCriteria(Criteria.where("customerId").is(customerId));
            List<Task> tasks = mongoOperations.find(query, Task.class);
            for(Task task:tasks)
            {
                System.out.println("Tasks found : " + task.getTaskName());
            }
            List<TaskDTO> taskDtos = toDto(tasks);
            String json = gson.toJson(taskDtos);
            return json;
        }catch (Exception e)
        {
            throw e;
        }
    }
}
