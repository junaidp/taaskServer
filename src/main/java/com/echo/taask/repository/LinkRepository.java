package com.echo.taask.repository;

import com.echo.taask.model.Files;
import com.echo.taask.model.Link;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.web.multipart.MultipartFile;


public interface LinkRepository extends MongoRepository<Link, String> {
    @Query("{'FilesName': ?0'}")
    Files findByName(String name);
}
