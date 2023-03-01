package com.echo.taask.repository;

import com.echo.taask.model.Files;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.web.multipart.MultipartFile;


public interface FilesRepository extends MongoRepository<Files, MultipartFile> {
    @Query("{'FilesName': ?0'}")
    Files findByName(String name);
}
