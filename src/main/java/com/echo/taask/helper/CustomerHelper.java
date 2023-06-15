package com.echo.taask.helper;

import com.echo.taask.dto.customer.CustomerDto;
import com.echo.taask.model.Customer;
import com.echo.taask.model.Image;
import com.echo.taask.repository.CustomerRepository;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Component
public class CustomerHelper {

    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    FilesHelper filesHelper;
    @Autowired
    MongoOperations mongoOperations;
    Gson gson = new Gson();

    public ResponseEntity<?> saveCustomer(String authenticatedUser, CustomerDto customer, MultipartFile image)
            throws IOException {
        Image uploadImage = new Image();
        if (image != null) {
            if (!isImage(image)) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Only Image Is Accepted.");
            } else {
                uploadImage.setName(image.getOriginalFilename());
                uploadImage.setContentType(image.getContentType());
                uploadImage.setData(image.getBytes());
            }
        }
        try {
            if (customer != null) {
                var saveCustomer = Customer.builder()
                        .name(customer.getName())
                        .category(customer.getCategory())
                        .customerSince(customer.getCustomerSince())
                        .customerStage(customer.getCustomerStage())
                        .location(customer.getLocation())
                        .website(customer.getWebsite())
                        .contacts(customer.getContacts())
                        .image(uploadImage)
                        .customerNotes(customer.getCustomerNotes())
                        .email(authenticatedUser)
                        .build();
                customerRepository.save(saveCustomer);
                return ResponseEntity.status(HttpStatus.CREATED).body("Customer " + customer.getName() + " saved!");
            }
        } catch (Exception e) {
            throw e;
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Customer Body Required!");
    }

    public  boolean isImage(MultipartFile file) {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        String fileExtension = StringUtils.getFilenameExtension(fileName);
        if (fileExtension == null || !isSupportedImageFormat(fileExtension)) {
            return false;
        }else
        {
            return true;
        }
    }
    private boolean isSupportedImageFormat(String fileExtension) {
        String supportedFormats = "jpg,jpeg,png,gif"; // Add more supported formats if needed
        return supportedFormats.contains(fileExtension.toLowerCase());
    }
}
//
//        public List<Customer> getAllCustomers ()
//        {
//            try {
//                return customerRepository.findAll();
//            } catch (Exception e) {
//                throw e;
//            }
//        }
//
//        public String getCustomers (String userid){
//            try {
//                System.out.println("Getting Customers for : " + userid);
//                Query query = new Query();
//                query.addCriteria(Criteria.where("userid").is(userid));
//                List<Customer> customers = mongoOperations.find(query, Customer.class);
//                for (Customer customer : customers) {
//                    System.out.println("customer found : " + customer.getName());
//                }
//                String json = gson.toJson(customers);
//                return json;
//            } catch (Exception e) {
//                throw e;
//            }
//        }
//
//        public Customer findCustomerById (String userId){
//            Query query = new Query();
//            query.addCriteria(Criteria.where("id").is(userId));
//            return mongoOperations.findOne(query, Customer.class);
//        }
//    }
