package com.echo.taask.helper;

import com.echo.taask.model.Customer;
import com.echo.taask.model.Image;
import com.echo.taask.model.Task;
import com.echo.taask.model.User;
import com.echo.taask.repository.CustomerRepository;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Component
public class CustomerHelper {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    FilesHelper filesHelper;
    @Autowired
    MongoOperations mongoOperations;
    Gson gson = new Gson();

    public String saveCustomer(Customer customer, MultipartFile file, Image image){
        try {
            if(file!=null)customer.setFileId(filesHelper.uploadFile(file));
            if(image!=null) customer.setImage(image);
            if(customer != null)
                customerRepository.save(customer);
            return "user saved";
        }catch (Exception e)
        {
            throw e;
        }
    }
    public List<Customer> getAllCustomers()
    {
        try{
            return customerRepository.findAll();
        }catch (Exception e)
        {
            throw e;
        }
    }

    public String getCustomers(String userid) {
        try{
            System.out.println("Getting Customers for : " + userid);
            Query query = new Query();
            query.addCriteria(Criteria.where("userid").is(userid));
            List<Customer> customers = mongoOperations.find(query, Customer.class);
            for(Customer customer:customers)
            {
                System.out.println("customer found : " + customer.getName());
            }
            String json = gson.toJson(customers);
            return json;
        }catch (Exception e)
        {
            throw e;
        }
    }

    public Customer findCustomerById(String userId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(userId));
        return mongoOperations.findOne(query, Customer.class);
    }
}
