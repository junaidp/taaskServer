package com.echo.taask.upcomingEvents;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/upcoming-events")
@CrossOrigin(origins = "*", maxAge = 3600)
public class UpcomingEventController {
    private final UpcomingEventHelper upcomingEventHelper;

    @PostMapping
    public ResponseEntity<?> createUpcomingEvent(Principal principal, @RequestHeader("Authorization") String Authorization
            , @Valid @RequestBody EventsDto eventDto) {
        String authenticatedUsername = principal.getName();
        return upcomingEventHelper.createEvent(authenticatedUsername, eventDto);
    }

    @GetMapping
    public ResponseEntity<?> getUpcomingEventsByEmail(Principal principal, @RequestHeader("Authorization") String Authorization) {
        String authenticatedUserName = principal.getName();
        if (authenticatedUserName != null || authenticatedUserName != "") {
            try {
                return upcomingEventHelper.getEventList(authenticatedUserName);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Contact Help Center");
    }

    @DeleteMapping("/{serial}")
    public ResponseEntity<?> deleteUpcomingEventsByEmail(Principal principal, @RequestHeader("Authorization") String Authorization,
                                                         @PathVariable("serial") String serial) {
        String authenticatedUserName = principal.getName();
        if (authenticatedUserName != null || authenticatedUserName != "") {
            try {
                return upcomingEventHelper.deleteMyEvent(authenticatedUserName, serial);
            } catch (Exception e) {
                System.out.println(e.getMessage());
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Contact Help Center");
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No Data Found");
        }
    }

    @PutMapping("/update/event")
    public ResponseEntity<?> updateUpcomingEventsByEmail(Principal principal, @RequestParam("serial") String serial,
                                                         @RequestHeader("Authorization") String Authorization,
                                                         @RequestBody EventsDto eventDto) {
        try {
            String authenticatedUserName = principal.getName();
            return upcomingEventHelper.updateEvent(authenticatedUserName, serial, eventDto);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Contact Help Center");
        }

    }
}
