package com.echo.taask.dto;

import jakarta.validation.constraints.Pattern;
import lombok.*;
import java.util.Date;
@AllArgsConstructor
@NoArgsConstructor
@Builder@Getter@Setter
public class EventsDto {
    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}\\.\\d{3}\\+\\d{2}:\\d{2}$",
            message = "Date must contain the following pattern 2023-06-11T02:26:15.375+00:00 ")
    private Date date;
    private String title;
    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}\\.\\d{3}\\+\\d{2}:\\d{2}$",
            message = "Date must contain the following pattern 2023-06-11T02:26:15.375+00:00 ")
    private Date dueDate;
}
