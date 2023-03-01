package com.echo.taask.repository;

import com.echo.taask.model.Task;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface TaskRepository extends MongoRepository<Task, String> {
    @Query("{ 'TaskName' : ?0'}")
    Task findByName(String name);
}
