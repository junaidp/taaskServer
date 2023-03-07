package com.echo.taask.model;


import nonapi.io.github.classgraph.json.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Meeting {
    @Id
    private String id;

    private String meetingName;
    private int meetingId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMeetingname() {
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
