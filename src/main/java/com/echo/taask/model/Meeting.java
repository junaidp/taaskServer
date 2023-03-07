package com.echo.taask.model;


import nonapi.io.github.classgraph.json.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document
public class Meeting {
    @Id
    private String id;
    private String meetingName;
    private int meetingId;

    private String dueDate;

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    private String time;
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMeetingName() {
        return meetingName;
    }

    public void setMeetingName(String meetingname) {
        this.meetingName = meetingname;
    }

    public int getMeetingId() {
        return meetingId;
    }

    public void setMeetingId(int meetingid) {
        this.meetingId = meetingid;
    }
}
