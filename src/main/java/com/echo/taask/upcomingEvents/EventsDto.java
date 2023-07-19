package com.echo.taask.upcomingEvents;

import jakarta.validation.constraints.Pattern;
import lombok.*;
import java.util.Date;
@AllArgsConstructor
@NoArgsConstructor
@Builder@Getter@Setter
public class EventsDto {
    private Date date;
    private String title;
    private Date dueDate;
}
