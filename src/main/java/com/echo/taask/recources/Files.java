package com.echo.taask.recources;

import lombok.Data;
import nonapi.io.github.classgraph.json.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document@Data
public class Files {
    @Id
    private String id;
    private long fileSize;
    private String uuid;
    private byte[] file;
    private String filename;
    private String filetype;
    private String email;
    private String customerSerial;
    private String customerTaskSerial;
    private String projectHocSerial;
}
