package com.echo.taask.helper;

import com.echo.taask.controller.FilesController;
import com.echo.taask.model.Files;
import com.echo.taask.model.Resource;
import com.echo.taask.repository.ResourcesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Component
public class ResourcesHelper {

    @Autowired
    ResourcesRepository resourcesRepository;
    @Autowired
    MongoOperations mongoOperations;
    @Autowired
    FilesHelper filesHelper;

    public String saveResources(Resource resource,MultipartFile file)
    {
        try {
            resource.setFileId(filesHelper.uploadFile(file));
            resourcesRepository.save(resource);
            return "resources saved successfully";
        }catch (Exception ex)
        {
            return "Error saving resources " + ex;
        }
    }
    public List<Resource> getResources(String userid)
    {
        try{
            Files file = new Files();
            Query query = new Query();
            query.addCriteria(Criteria.where("userId").is(userid));
            List<Resource> resources = mongoOperations.find(query,Resource.class);

//            Files file = filesHelper.downloadFile(res.getAttachment());
            return resources;
        }catch (Exception ex){
            throw ex;
        }
    }
}
