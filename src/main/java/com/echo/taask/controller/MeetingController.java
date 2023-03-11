package com.echo.taask.controller;

import com.echo.taask.dto.MeetingDTO;
import com.echo.taask.helper.MeetingHelper;
import com.echo.taask.model.Meeting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequestMapping ("api/meeting")
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)

public class MeetingController {

    private MeetingHelper meetingHelper;

    @Autowired
    public MeetingController(MeetingHelper meetingHelper){
        this.meetingHelper = meetingHelper;
    }

    @PostMapping("/saveMeeting")
    public String saveMeeting(@RequestBody Meeting meeting){
        try {
            return meetingHelper.saveMeeting(meeting);
        }catch (Exception ex){
            return "Failed to Save Meeting";
        }
    }

    @GetMapping("/getAllMeetings")
    public ResponseEntity<List<MeetingDTO>> getAllMeetings()
    {
        try {
            return new ResponseEntity<>(meetingHelper.getAllMeeting(),HttpStatus.OK);
        }catch (Exception ex){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
