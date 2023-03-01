package com.echo.taask.helper;


import com.echo.taask.model.Files;
import com.echo.taask.repository.FilesRepository;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.client.gridfs.model.GridFSFile;
import org.apache.poi.util.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Component
public class FilesHelper {

    @Autowired
    FilesRepository filesRepository;
    @Autowired
    private GridFsTemplate template;

    @Autowired
    private GridFsOperations operations;

    public String uploadFile(MultipartFile file)
    {
        try{
            DBObject metadata = new BasicDBObject();
            Object fileID = template.store(file.getInputStream(), file.getOriginalFilename(), file.getContentType(), metadata);
//            filesRepository.save(file);
            return "file uploaded Successfully " + fileID.toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String downloadFile(String id) throws IOException {

        GridFSFile gridFSFile = template.findOne( new Query(Criteria.where("_id").is(id)) );

        Files loadFile = new Files();

        if (gridFSFile != null && gridFSFile.getMetadata() != null) {
            loadFile.setFilename( gridFSFile.getFilename() );

            loadFile.setFiletype( gridFSFile.getMetadata().get("_contentType").toString() );

            loadFile.setFilesize( gridFSFile.getMetadata().get("fileSize").toString() );

            loadFile.setFile( IOUtils.toByteArray(operations.getResource(gridFSFile).getInputStream()) );
        }

//        return loadFile;
        return "File downloaded successfully" + loadFile.getFilename();
    }

}
