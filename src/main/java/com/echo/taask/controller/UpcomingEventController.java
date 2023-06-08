package com.echo.taask.controller;

import com.echo.taask.dto.EventsDto;
import com.echo.taask.model.UpcomingEvents;
import com.echo.taask.repository.UpcomingEventRepo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/upcoming-events")
@CrossOrigin(origins = "*", maxAge = 3600)
public class UpcomingEventController {
    private final UpcomingEventRepo repository;

    public UpcomingEventController(UpcomingEventRepo repository) {
        this.repository = repository;
    }

    @PostMapping
    public UpcomingEvents createUpcomingEvent(@RequestBody EventsDto eventDto) {
        UpcomingEvents event = new UpcomingEvents();
        event.setDate(eventDto.getDate());
        event.setTitle(eventDto.getTitle());
        event.setDueDate(eventDto.getDueDate());
        event.setEmail(eventDto.getEmail());
        return repository.save(event);
    }
    @GetMapping("/{email}")
    public ResponseEntity<List<UpcomingEvents>> getUpcomingEventsByEmail(@PathVariable("email") String email) {
        List<UpcomingEvents> events = repository.findByEmail(email);
        if (!events.isEmpty()) {
            return ResponseEntity.ok(events);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{email}")
    public ResponseEntity<Void> deleteUpcomingEventsByEmail(@PathVariable("email") String email) {
        List<UpcomingEvents> events = repository.findByEmail(email);
        if (!events.isEmpty()) {
            repository.deleteAll(events);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/update/event")
    public ResponseEntity<UpcomingEvents> updateUpcomingEventsByEmail( @RequestBody EventsDto eventDto) {
        List<UpcomingEvents> events = repository.findByEmail(eventDto.getEmail());
        if (!events.isEmpty()) {
            UpcomingEvents event = events.get(0); // Assuming there is only one event per email
            event.setDate(eventDto.getDate());
            event.setTitle(eventDto.getTitle());
            event.setDueDate(eventDto.getDueDate());
            return ResponseEntity.ok(repository.save(event));
        } else {
            return ResponseEntity.notFound().build();
        }
    }


}
