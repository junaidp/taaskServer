package com.echo.taask.helper;

import com.echo.taask.dto.MeetingDTO;
import com.echo.taask.model.Meeting;
import com.echo.taask.repository.MeetingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Component
public class MeetingHelper {

    @Autowired
    MeetingRepository meetingRepository;
    private static final String DATE_FORMAT = "dd/MM/yyyy HH:mm a";

    public String saveMeeting(Meeting meeting)
    {
        try {
            meetingRepository.save(meeting);
            return "Meeting Saved";
        }catch (Exception e) {
            throw e;
        }
    }
    public List<MeetingDTO> getAllMeeting()
    {
        try{
            return toDto(meetingRepository.findAll());
        }catch (Exception e)
        {
            throw e;
        }
    }

    private List<MeetingDTO> toDto(List<Meeting> meetings) {
        DateFormat formatter = new SimpleDateFormat(DATE_FORMAT);
        List<MeetingDTO> meetingDTOs = new ArrayList<MeetingDTO>();
        for(Meeting meeting: meetings)
        {
            MeetingDTO meetingDTO = new MeetingDTO();
            String formattedDate = formatter.format(meeting.getDueDate());
            meetingDTO.setDueDate(formattedDate.substring(0,10));
            meetingDTO.setTime(formattedDate.substring(11));
            meetingDTO.setMeetingName(meeting.getMeetingName());
            meetingDTOs.add(meetingDTO);
        }
        return meetingDTOs;

    }

}
