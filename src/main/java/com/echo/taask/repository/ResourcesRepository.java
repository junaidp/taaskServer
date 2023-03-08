package com.echo.taask.repository;


import com.echo.taask.model.Resource;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface ResourcesRepository extends MongoRepository<Resource,String> {
    @Query("{'resource': ?0'}")
    Resource findByName(String name);
}
