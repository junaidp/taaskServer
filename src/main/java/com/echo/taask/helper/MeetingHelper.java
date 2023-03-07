package com.echo.taask.helper;

import com.echo.taask.model.Meeting;
import com.echo.taask.repository.MeetingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MeetingHelper {

    @Autowired
    MeetingRepository meetingRepository;

    public String saveMeeting(Meeting meeting)
    {
        try {
            meetingRepository.save(meeting);
            return "Meeting Saved";
        }catch (Exception e) {
            throw e;
        }
    }
    public List<Meeting> getAllMeeting()
    {
        try{
            return meetingRepository.findAll();
        }catch (Exception e)
        {
            throw e;
        }
    }

}
