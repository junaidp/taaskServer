package com.echo.taask.helper;

import com.echo.taask.dto.EventsDto;
import com.echo.taask.model.UpcomingEvents;
import com.echo.taask.model.User;
import com.echo.taask.repository.UpcomingEventRepo;
import com.echo.taask.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class UpcomingEventHelper {
    private final UpcomingEventRepo upcomingRepository;
    private final UserRepository userRepository;

    public ResponseEntity<?> createEvent(String authenticatedUsername, EventsDto eventDto) {
        User userData = userRepository.findByEmail(authenticatedUsername);
        var event = UpcomingEvents.builder().date(eventDto.getDate())
                .serialNumber(generateSerialNumber())
                .title(eventDto.getTitle())
                .dueDate(eventDto.getDueDate())
                .email(userData.getEmail()).build();
        upcomingRepository.save(event);
        return ResponseEntity.status(HttpStatus.CREATED).body("Event "+ eventDto.getTitle()+" created");
    }

    public String generateSerialNumber() {
        // You can use a UUID or any other unique identifier generation mechanism
        return UUID.randomUUID().toString();
    }

    public ResponseEntity<?> getEventList(String authenticatedUserName) {
        List<UpcomingEvents> upcomingEventsList = upcomingRepository.findByEmail(authenticatedUserName);
        List<Map<String, Object>> eventResponses = new ArrayList<>();
        if (upcomingEventsList != null || !upcomingEventsList.isEmpty()) {
            for (UpcomingEvents events : upcomingEventsList) {
                Map<String, Object> eventMap = new HashMap<>();
                eventMap.put("date", events.getDate());
                eventMap.put("title", events.getTitle());
                eventMap.put("dueDate", events.getDueDate());
                eventMap.put("srNo", events.getSerialNumber());
                eventResponses.add(eventMap);
            }
            return ResponseEntity.status(HttpStatus.OK).body(eventResponses);
        } else {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("You ain't got no data for upcoming events");
        }
    }

    public ResponseEntity<?> deleteMyEvent(String authenticatedUserName, String serial) {
        UpcomingEvents upcomingEvent = upcomingRepository.findByEmailAndSerialNumber(authenticatedUserName, serial);
        if (upcomingEvent != null) {
            upcomingRepository.deleteById(upcomingEvent.getId());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.badRequest().body("No event to be deleted");
        }
    }

    public ResponseEntity<?> updateEvent(String authenticatedUserName, String serial, EventsDto eventDto) {
        UpcomingEvents upcomingEvent = upcomingRepository.findByEmailAndSerialNumber(authenticatedUserName, serial);
        if (upcomingEvent != null) {
            if (!serial.equals(upcomingEvent.getSerialNumber())) {
                return ResponseEntity.badRequest().body("No event to be Found for Id" + serial);
            } else {
                var event = UpcomingEvents.builder()
                        .id(upcomingEvent.getId())
                        .date(eventDto.getDate() != null ? eventDto.getDate() : upcomingEvent.getDate())
                        .serialNumber(upcomingEvent.getSerialNumber())
                        .title(eventDto.getTitle() != null ? eventDto.getTitle() : upcomingEvent.getTitle())
                        .dueDate(eventDto.getDueDate() != null ? eventDto.getDueDate() : upcomingEvent.getDueDate())
                        .email(upcomingEvent.getEmail())
                        .build();
                upcomingRepository.save(event);
                return ResponseEntity.status(HttpStatus.ACCEPTED).body("Event Updated Sucessfully");
            }
        } else {
            return ResponseEntity.badRequest().body("No event Found!");
        }
    }
}
