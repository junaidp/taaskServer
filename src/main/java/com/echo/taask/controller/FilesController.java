package com.echo.taask.controller;


import com.echo.taask.helper.FilesHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RequestMapping("api/files")
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)

public class FilesController {


    private FilesHelper filesHelper;

    @Autowired
    public FilesController(FilesHelper filesHelper){
        this.filesHelper = filesHelper;
    }

    @PostMapping("uploadfile")
    public ResponseEntity<String> uploadFile(@RequestParam MultipartFile file)
    {
        try {
            return new ResponseEntity<>(filesHelper.uploadFile(file), HttpStatus.OK);
        }catch (Exception ex){
            return new ResponseEntity<>("Failed to Upload File" , HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("downloadfile")
    public ResponseEntity<String> downloadFile( String fileid) throws IOException {
        try {
            return new ResponseEntity<>(filesHelper.downloadFile(fileid),HttpStatus.OK);
        }catch (Exception ex){
            return new ResponseEntity<>("failed to Download File " , HttpStatus.BAD_REQUEST);
        }
    }
}
