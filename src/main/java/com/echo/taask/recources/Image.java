package com.echo.taask.recources;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import nonapi.io.github.classgraph.json.Id;

@Getter
@Setter
@Document
public class Image {
    @Id
    private String id;
    private String name;
    private String contentType;
    private byte[] data;

}
