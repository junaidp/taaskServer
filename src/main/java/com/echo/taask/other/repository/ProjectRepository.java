package com.echo.taask.other.repository;

import com.echo.taask.other.model.Meeting;
import com.echo.taask.other.model.Project;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface ProjectRepository extends MongoRepository<Project,String> {
    @Query("{ 'ProjectName' : ?0'}")
    Meeting findByName(String name);
}
