package com.echo.taask.helper;

import com.echo.taask.dto.customer.*;
import com.echo.taask.model.*;
import com.echo.taask.repository.CustomerFilesRepository;
import com.echo.taask.repository.CustomerLinksRepostiory;
import com.echo.taask.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class CustomerHelper {

    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    CustomerFilesRepository customerFilesRepository;
    @Autowired
    CustomerLinksRepostiory customerLinksRepostiory;
    @Autowired
    FilesHelper filesHelper;
    @Autowired
    MongoOperations mongoOperations;

    public ResponseEntity<?> saveCustomer(String authenticatedUser, CustomerDto customer, MultipartFile image, MultipartFile file, CustomerLinkDto customerLinkDto)
            throws IOException {
        Image uploadImage = new Image();
        CustomerFiles customerfile = new CustomerFiles();
        byte[] imageBytes = image.getBytes();
        if (image != null && imageBytes.length > 0) {
            if (!isImage(image)) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Only Image Is Accepted.");
            } else {
                uploadImage.setName(image.getOriginalFilename());
                uploadImage.setContentType(image.getContentType());
                uploadImage.setData(image.getBytes());
            }
        }
        try {
            String customerId = null;
            if (customer != null) {
                var saveCustomer = Customer.builder()
                        .serialNumber(generateSerialNumber())
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
                customerId = saveCustomer.getSerialNumber();
            }
            if (customerLinkDto != null) {
                CustomerLinks customerLinks = new CustomerLinks();
                customerLinks.setUuid(generateSerialNumber());
                customerLinks.setLink(customerLinkDto.getLink());
                customerLinks.setEmail(authenticatedUser);
                customerLinks.setDescription(customerLinks.getDescription());
                customerLinks.setCustomerSerial(customerId);
                customerLinksRepostiory.save(customerLinks);
            }
            byte[] fileBytes = file.getBytes();
            if (file != null && fileBytes.length > 0) {
                customerfile.setId(generateSerialNumber());
                customerfile.setEmail(authenticatedUser);
                customerfile.setFilename(file.getOriginalFilename());
                customerfile.setFiletype(file.getContentType());
                customerfile.setFile(file.getBytes());
                customerfile.setCustomerSerial(customerId);
                customerFilesRepository.save(customerfile);
            }
            if (!customerId.isEmpty()) {
                return ResponseEntity.status(HttpStatus.CREATED).body("Customer " + customer.getName() + " saved!");
            }
        } catch (Exception e) {
            throw e;
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Customer Body Required!");
    }

    //Checking Image Format
    public boolean isImage(MultipartFile file) {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        String fileExtension = StringUtils.getFilenameExtension(fileName);
        if (fileExtension == null || !isSupportedImageFormat(fileExtension)) {
            return false;
        } else {
            return true;
        }
    }

    //checking if uploaded file is image
    private boolean isSupportedImageFormat(String fileExtension) {
        String supportedFormats = "jpg,jpeg,png,gif"; // Add more supported formats if needed
        return supportedFormats.contains(fileExtension.toLowerCase());
    }

    public String generateSerialNumber() {
        // You can use a UUID or any other unique identifier generation mechanism
        return UUID.randomUUID().toString();
    }

    //get authenticated user customers list
    public ResponseEntity<?> getCustomersList(String authenticatedUser) {
        //fetch data in a list from customerRepository
        List<Customer> customers = customerRepository.findAllByEmail(authenticatedUser);
        if (!customers.isEmpty()) {
            List<CustomerResponse> listingAllCustomer = new ArrayList<>();
            for (Customer customerList : customers) {
                CustomerResponse customer = new CustomerResponse();
                //setting up imageResponse for better & secure JSON
                ImageResponse imageResponse = new ImageResponse();
                imageResponse.setName(customerList.getImage().getName());
                imageResponse.setContentType(customerList.getImage().getContentType());
                imageResponse.setData(customerList.getImage().getData());
                ////setting up ContactResponse for better & secure JSON
                ContactResponse contactResponse = new ContactResponse();
                contactResponse.setName(customerList.getContacts().getName());
                contactResponse.setJobTitle(customerList.getContacts().getJobTitle());
                contactResponse.setEmailAddress(customerList.getContacts().getEmailAddress());
                contactResponse.setLocation(customerList.getContacts().getLocation());
                //Mapping response entities to the CustomerDto
                customer.setSerialNumber(customerList.getSerialNumber());
                customer.setName(customerList.getName());
                customer.setCategory(customerList.getCategory());
                customer.setCustomerSince(customerList.getCustomerSince());
                customer.setCustomerStage(customerList.getCustomerStage());
                customer.setLocation(customerList.getLocation());
                customer.setWebsite(customerList.getWebsite());
                customer.setContacts(contactResponse);
                customer.setCustomerNotes(customerList.getCustomerNotes());
                customer.setImage(imageResponse);
                //Adding customer to the List
                listingAllCustomer.add(customer);
            }
            return ResponseEntity.status(HttpStatus.OK).body(listingAllCustomer);
        } else {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No Customer Found!");
        }
    }

    public ResponseEntity<?> findCustomerById(String authenticatedUser, String userid) {
        Optional<Customer> customerBySerial = customerRepository.findBySerialNumberAndEmail(userid, authenticatedUser);
        if (customerBySerial.isPresent()) {
            CustomerByIdResponse customer = new CustomerByIdResponse();
            //Mapping response entities to the CustomerDto
            customer.setSerialNumber(customerBySerial.get().getSerialNumber());
            customer.setName(customerBySerial.get().getName());
            customer.setCategory(customerBySerial.get().getCategory());
            customer.setCustomerSince(customerBySerial.get().getCustomerSince());
            customer.setCustomerStage(customerBySerial.get().getCustomerStage());
            customer.setLocation(customerBySerial.get().getLocation());
            customer.setWebsite(customerBySerial.get().getWebsite());
            customer.setCustomerNotes(customerBySerial.get().getCustomerNotes());
            //setting up ContactResponse for better & secure JSON
            ContactResponse contactResponse = new ContactResponse();
            contactResponse.setName(customerBySerial.get().getContacts().getName());
            contactResponse.setJobTitle(customerBySerial.get().getContacts().getJobTitle());
            contactResponse.setEmailAddress(customerBySerial.get().getContacts().getEmailAddress());
            contactResponse.setLocation(customerBySerial.get().getContacts().getLocation());
            customer.setContacts(contactResponse);
            //setting up imageResponse for better & secure JSON
            ImageResponse imageResponse = new ImageResponse();
            imageResponse.setName(customerBySerial.get().getImage().getName());
            imageResponse.setContentType(customerBySerial.get().getImage().getContentType());
            imageResponse.setData(customerBySerial.get().getImage().getData());
            customer.setImage(imageResponse);
            //setting up filesResponse
            List<CustomerFiles> customerFiles = customerFilesRepository.findByEmail(authenticatedUser);
            List<CustomerFilesDto> customerFilesList = new ArrayList<>();
            if (!customerFiles.isEmpty()) {
                for (CustomerFiles files : customerFiles) {
                    CustomerFilesDto customerFilesDto = new CustomerFilesDto();
                    customerFilesDto.setUuid(files.getUuid());
                    customerFilesDto.setFilename(files.getFilename());
                    customerFilesDto.setFiletype(files.getFiletype());
                    customerFilesDto.setFileSize(files.getFileSize());
                    customerFilesDto.setFile(files.getFile());
                    customerFilesList.add(customerFilesDto);
                }
            }
            customer.setCustomerFiles(customerFilesList);
            //setting up linksResponse
            List<CustomerLinks> customerLinks = customerLinksRepostiory.findByEmail(authenticatedUser);
            List<CustomerLinkDto> customerLinkList = new ArrayList<>();
            if (!customerLinks.isEmpty()) {
                for (CustomerLinks links : customerLinks) {
                    CustomerLinkDto customerLinkDto = new CustomerLinkDto();
                    customerLinkDto.setUuid(links.getUuid());
                    customerLinkDto.setDescription(links.getDescription());
                    customerLinkDto.setLink(links.getLink());
                    customerLinkList.add(customerLinkDto);
                }
            }
            customer.setCustomerLink(customerLinkList);
            return ResponseEntity.status(HttpStatus.OK).body(customer);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No Customer Found!");
        }
    }

    public ResponseEntity<?> deleteCustomerById(String authenticatedUser, String userId) {
        Optional<Customer> customerOptional = customerRepository.findBySerialNumberAndEmail(userId, authenticatedUser);
        if (customerOptional.isPresent()) {
            customerRepository.deleteBySerialNumberAndEmail(userId, authenticatedUser);
            customerLinksRepostiory.deleteAllByEmailAndCustomerSerial(authenticatedUser, customerOptional.get().getSerialNumber());
            customerFilesRepository.deleteAllByEmailAndCustomerSerial(authenticatedUser, customerOptional.get().getSerialNumber());
            return ResponseEntity.status(HttpStatus.OK).body("Customer deleted successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Customer not found.");
        }
    }

    public ResponseEntity<?> updateCustomer(String authenticatedUser, CustomerDto customerDto, String userId, MultipartFile image) {
        Optional<Customer> customerOptional = customerRepository.findBySerialNumberAndEmail(userId, authenticatedUser);
        if (customerOptional.isPresent()) {
            customerOptional.get().setCategory(customerDto.getCategory());
            customerOptional.get().setCustomerStage(customerDto.getCustomerStage() );
            customerOptional.get().setCustomerSince(customerDto.getCustomerSince() );
            customerOptional.get().setLocation(customerDto.getLocation() );
            customerOptional.get().setWebsite(customerDto.getWebsite() );
            customerOptional.get().setCustomerNotes(customerDto.getCustomerNotes());
            customerOptional.get().setName(customerDto.getName() );
            if (customerDto.getContacts() != null) {
                Contact contact = new Contact();
                contact.setName(customerDto.getContacts().getName());
                contact.setJobTitle(customerDto.getContacts().getJobTitle() );
                contact.setEmailAddress(customerDto.getContacts().getEmailAddress());
                contact.setLocation(customerDto.getContacts().getLocation());
                customerOptional.get().setContacts(contact);
            }
            Image uploadImage = new Image();
            try {
                if (image != null && image.getBytes().length > 0) {
                    if (!isImage(image)) {
                        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Only Image Is Accepted.");
                    } else {
                        uploadImage.setName(image.getOriginalFilename());
                        uploadImage.setContentType(image.getContentType());
                        uploadImage.setData(image.getBytes());
                        customerOptional.get().setImage(uploadImage);
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            customerRepository.save(customerOptional.get());
            return ResponseEntity.status(HttpStatus.OK).body("Customer Updated successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Customer not found.");
        }
    }
}
