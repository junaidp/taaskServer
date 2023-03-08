package com.echo.taask.controller;


import com.echo.taask.helper.FilesHelper;
import com.echo.taask.model.Files;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<?> upload(@RequestParam("file") MultipartFile file) throws IOException {
        return new ResponseEntity<>(filesHelper.uploadFile(file), HttpStatus.OK);
    }

    @GetMapping("downloadfile")
    public ResponseEntity<ByteArrayResource> downloadfile(@RequestParam String fileid) throws IOException {
        Files loadFile = filesHelper.downloadFile(fileid);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(loadFile.getFiletype()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + loadFile.getFilename() + "\"")
                .body(new ByteArrayResource(loadFile.getFile()));
    }
}
