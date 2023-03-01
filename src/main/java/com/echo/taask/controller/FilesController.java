package com.echo.taask.controller;


import com.echo.taask.helper.FilesHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RequestMapping("api/files")
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)

public class FilesController {

    @Autowired
    FilesHelper filesHelper;

    @PostMapping("uploadfile")
    public String uploadfile(@RequestParam MultipartFile file)
    {
        return filesHelper.uploadFile(file);
    }

    @GetMapping("downloadfile")
    public String downloadfile( String fileid) throws IOException {
        return filesHelper.downloadFile(fileid);
    }
}
