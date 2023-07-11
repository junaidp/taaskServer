package com.echo.taask.upcomingEvents;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document @Getter
@Setter
public class UpcomingEvents {
    @Id
    private String id;
    private String serialNumber;
    private Date date;
    private String title;
    private Date dueDate;
    private String email;

}
