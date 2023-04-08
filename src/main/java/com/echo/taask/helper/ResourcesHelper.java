package com.echo.taask.helper;

import com.echo.taask.controller.FilesController;
import com.echo.taask.model.Files;
import com.echo.taask.model.Link;
import com.echo.taask.model.Resource;
import com.echo.taask.repository.LinkRepository;
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
    LinkRepository linkRepository;
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

    public String saveLink(Link link)
    {
        try {
            linkRepository.save(link);
            return "Link saved successfully";
        }catch (Exception ex)
        {
            return "Error saving Link " + ex;
        }
    }

    public List<Link> getLinks(String userid)
    {
        try{
            Query query = new Query();
            query.addCriteria(Criteria.where("userId").is(userid));
            List<Link> resources = mongoOperations.find(query,Link.class);

//            Files file = filesHelper.downloadFile(res.getAttachment());
            return resources;
        }catch (Exception ex){
            throw ex;
        }
    }
}
