package com.echo.taask.upcomingEvents;

import com.echo.taask.upcomingEvents.UpcomingEvents;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface UpcomingEventRepo extends MongoRepository<UpcomingEvents, String> {
    List<UpcomingEvents> findByEmail(String email);
    UpcomingEvents findByEmailAndSerialNumber(String email,String serial);
}
