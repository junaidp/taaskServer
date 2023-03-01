package com.echo.taask.controller;

import com.echo.taask.helper.MeetingHelper;
import com.echo.taask.model.Meeting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequestMapping ("api/meeting")
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)

public class MeetingController {
    @Autowired
    MeetingHelper meetinghelper;

    @PostMapping("savemeeting")
    public String savemeeting(@RequestParam Meeting meeting){
        return meetinghelper.savemeeting(meeting);
    }

    @GetMapping("getallmeetings")
    public List<Meeting> getallmeetings()
    {
        return meetinghelper.getAllMeeting();
    }
}
