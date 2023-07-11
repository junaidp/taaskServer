package com.echo.taask.customer.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomerFilesDto {
    private String uuid;
    private long fileSize;
    private byte[] file;
    private String filename;
    private String filetype;
}
