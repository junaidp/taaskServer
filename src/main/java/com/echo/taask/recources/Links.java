package com.echo.taask.recources;
import lombok.Data;
import nonapi.io.github.classgraph.json.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Document@Data
public class Links {
    @Id
    private String id;
    private String uuid;
    private String email;
    private String link;
    private String description;
    private String customerSerial;
    private String customerTaskSerial;
    private String projectHocSerial;
}
