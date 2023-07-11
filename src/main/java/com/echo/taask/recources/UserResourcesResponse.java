package com.echo.taask.recources;

import com.echo.taask.customer.dto.CustomerFilesDto;
import com.echo.taask.customer.dto.CustomerLinkDto;
import lombok.Data;

import java.util.List;

@Data
public class UserResourcesResponse {
    private List<CustomerLinkDto> userLinks;
    private List<CustomerFilesDto> userFiles;
}
