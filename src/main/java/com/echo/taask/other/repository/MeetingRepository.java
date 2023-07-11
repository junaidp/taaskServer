package com.echo.taask.other.repository;

import com.echo.taask.other.model.Meeting;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface MeetingRepository extends MongoRepository<Meeting,String> {
    @Query("{ 'MeetingName' : ?0'}")
    Meeting findByName(String name);
}
