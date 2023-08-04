package com.echo.taask.recources;

import com.echo.taask.customer.dto.CustomerFilesDto;
import com.echo.taask.customer.dto.CustomerLinkDto;
import com.echo.taask.customer.reporisoties.CustomerFilesRepository;
import com.echo.taask.customer.reporisoties.CustomerLinksRepostiory;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class ResourceService {


    private final CustomerFilesRepository customerFilesRepository;
    private final CustomerLinksRepostiory customerLinksRepostiory;

    public ResponseEntity<?> addResources(String customerId, String authenticatedUser, List<MultipartFile> file,
                                          List<CustomerLinkDto> customerLinkDto) throws IOException {
        Boolean fileCheck = false;
        Boolean linkCheck = false;

        if (file != null && !file.isEmpty()) {
            for (MultipartFile filesData : file) {
                Files customerfile = new Files();
                customerfile.setUuid(generateSerialNumber());
                customerfile.setEmail(authenticatedUser);
                customerfile.setFilename(filesData.getOriginalFilename());
                customerfile.setFiletype(filesData.getContentType());
                customerfile.setFile(filesData.getBytes());
                if (customerId == null || customerId.isEmpty() || customerId.equals("")) {
                    customerfile.setCustomerSerial(null);
                } else {
                    customerfile.setCustomerSerial(customerId);
                }
                customerFilesRepository.save(customerfile);
                fileCheck = true;
            }
        }
        if (customerLinkDto != null && !customerLinkDto.isEmpty()) {
            for (CustomerLinkDto customerLinksData : customerLinkDto) {
                Links customerLinks = new Links();
                customerLinks.setUuid(generateSerialNumber());
                customerLinks.setLink(customerLinksData.getLink());
                customerLinks.setEmail(authenticatedUser);
                customerLinks.setDescription(customerLinksData.getDescription());
                if (customerId == null || customerId.isEmpty() || customerId.equals("")) {
                    customerLinks.setCustomerSerial(null);
                } else {
                    customerLinks.setCustomerSerial(customerId);
                }
                customerLinksRepostiory.save(customerLinks);
                linkCheck = true;
            }
        }
        if (fileCheck == true && linkCheck == true) {
            return ResponseEntity.status(HttpStatus.OK)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body("File And Link Added");
        }
        if (fileCheck == true) {
            return ResponseEntity.status(HttpStatus.OK)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body("File Added");
        } else {
            return ResponseEntity.status(HttpStatus.OK)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body("Link Added");
        }
    }


    public String generateSerialNumber() {
        // You can use a UUID or any other unique identifier generation mechanism
        return UUID.randomUUID().toString();
    }

    public ResponseEntity<?> getUserResources(String authenticatedUser) {
        UserResourcesResponse userResourcesResponse = new UserResourcesResponse();
        List<Files> customerFiles = customerFilesRepository.findByEmailAndCustomerSerial(authenticatedUser, null);
        List<CustomerFilesDto> customerFilesList = new ArrayList<>();
        if (!customerFiles.isEmpty()) {
            for (Files files : customerFiles) {
                CustomerFilesDto userFiles = new CustomerFilesDto();
                userFiles.setUuid(files.getUuid());
                userFiles.setFilename(files.getFilename());
                userFiles.setFiletype(files.getFiletype());
                userFiles.setFileSize(files.getFileSize());
                userFiles.setFile(files.getFile());
                customerFilesList.add(userFiles);
            }
        }
        userResourcesResponse.setUserFiles(customerFilesList);
        //setting up linksResponse
        List<Links> customerLinks = customerLinksRepostiory.findByEmailAndCustomerSerial(authenticatedUser, null);
        List<CustomerLinkDto> customerLinkList = new ArrayList<>();
        if (!customerLinks.isEmpty()) {
            for (Links links : customerLinks) {
                CustomerLinkDto userLinks = new CustomerLinkDto();
                userLinks.setUuid(links.getUuid());
                userLinks.setDescription(links.getDescription());
                userLinks.setLink(links.getLink());
                customerLinkList.add(userLinks);
            }
        }
        userResourcesResponse.setUserLinks(customerLinkList);

        if (!customerFilesList.isEmpty() || !customerLinkList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(userResourcesResponse);
        } else {
            return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body("No Resources Found");
        }

    }

    public ResponseEntity<?> updateResources(String fileUuid, String linkUuid, String authenticatedUser,
                                             MultipartFile file, CustomerLinkDto customerLinkDto) throws IOException {

        Boolean fileCheck = false;
        Boolean linkCheck = false;

        if (file != null && !file.isEmpty()) {
            Optional<Files> fileData = customerFilesRepository.findByEmailAndUuid(authenticatedUser, fileUuid);
            if (fileData.isPresent()) {
                Files customerfile = new Files();
                customerfile.setUuid(fileData.get().getUuid());
                customerfile.setEmail(authenticatedUser);
                customerfile.setFilename(file.getOriginalFilename());
                customerfile.setFiletype(file.getContentType());
                customerfile.setFile(file.getBytes());
                customerfile.setCustomerSerial(fileData.get().getCustomerSerial());
                customerFilesRepository.save(customerfile);
                fileCheck = true;
            }
        }
        if (customerLinkDto != null) {
            Optional<Links> linkData = customerLinksRepostiory.findByEmailAndUuid(authenticatedUser, linkUuid);
            if (linkData.isPresent()) {
                Links customerLinks = new Links();
                customerLinks.setUuid(linkData.get().getUuid());
                customerLinks.setLink(customerLinkDto.getLink());
                customerLinks.setDescription(customerLinkDto.getDescription());
                customerLinks.setEmail(authenticatedUser);
                customerLinks.setCustomerSerial(linkData.get().getCustomerSerial());
                customerLinksRepostiory.save(customerLinks);
                linkCheck = true;
            }
        }
        if (fileCheck == true && linkCheck == true) {
            return ResponseEntity.status(HttpStatus.OK)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body("File And Link Updated");
        }
        if (fileCheck == true) {
            return ResponseEntity.status(HttpStatus.OK)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body("File Updated");
        } if (linkCheck==true){
            return ResponseEntity.status(HttpStatus.OK)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body("Link Updated");
        }else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body("No Data Updated");
        }

    }


    public ResponseEntity<?> deleteResource(String authenticatedUser, String resourceId, String resourceType) {
        if ("file".equals(resourceType)) {
            return deleteFileResource(authenticatedUser, resourceId);
        } else if ("link".equals(resourceType)) {
            return deteleLink(authenticatedUser, resourceId);
        } else {
            return ResponseEntity.badRequest().body("Invalid resource type");
        }
    }

    private ResponseEntity<?> deleteFileResource(String authenticatedUser, String resourceId) {
        Long contentDeleted = customerFilesRepository.deleteByEmailAndUuid(authenticatedUser, resourceId);
        if (contentDeleted > 0) {
            return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body("Content deleted");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No Resource Found");
        }
    }

    private ResponseEntity<?> deteleLink(String authenticatedUser, String resourceId) {
        Long contentDeleted = customerLinksRepostiory.deleteByEmailAndUuid(authenticatedUser, resourceId);
        if (contentDeleted > 0) {
            return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body("Content deleted");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No Resource Found");
        }
    }


}
