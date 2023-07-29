package com.echo.taask.adhocProject;

import com.echo.taask.customer.Customer;
import com.echo.taask.customer.dto.CustomerFilesDto;
import com.echo.taask.customer.reporisoties.CustomerFilesRepository;
import com.echo.taask.customer.reporisoties.CustomerRepository;
import com.echo.taask.recources.Files;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AdHocService {
    private final AdHocProjectRepository adHocProjectRepository;
    private final CustomerRepository customerRepository;
    private final CustomerFilesRepository filesRepository;

    public ResponseEntity<?> saveProjectHoc(Principal principal, AdHocRequest adHocRequest, MultipartFile file) throws IOException {
        if (adHocRequest.getCustomerId() != null && !adHocRequest.getCustomerId().isEmpty()) {
            Optional<Customer> customerData = customerRepository.findBySerialNumberAndEmail(adHocRequest.getCustomerId(), principal.getName());
            if (!customerData.isPresent()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).contentType(MediaType.APPLICATION_JSON)
                        .body("Try It With Valid Customer Id");
            }
        }
        AdHocProject adHocProjectModel = new AdHocProject();
        //Save New AdHoc Project
        if (adHocRequest != null && (adHocRequest.getUuid() == null || adHocRequest.getUuid().isEmpty())) {
            String projectHocUuid = generateSerialNumber();
            adHocProjectModel.setUuid(projectHocUuid);
            adHocProjectModel.setCustomerId(adHocRequest.getCustomerId());
            adHocProjectModel.setProjectName(adHocRequest.getProjectName());
            List<String> projectTask = new ArrayList<>();
            if (!adHocRequest.getProjectTask().isEmpty()) {
                for (String projectTaskData : adHocRequest.getProjectTask()) {
                    projectTask.add(projectTaskData);
                }
            }
            adHocProjectModel.setProjectTask(projectTask);
            adHocProjectModel.setDueDate(adHocRequest.getDueDate());
            adHocProjectModel.setStatus(adHocRequest.getStatus());
            adHocProjectModel.setEmail(principal.getName());
            adHocProjectRepository.save(adHocProjectModel);
            storeAdHocProjectFile(file, principal.getName(), projectHocUuid);
            return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON)
                    .body("New AdHoc Projcet Added");
        } else {
            //Update New AdHoc Project
            Optional<AdHocProject> adHocProjectCheck = adHocProjectRepository.findByUuidAndEmail(adHocRequest.getUuid(), principal.getName());
            if (adHocProjectCheck.isPresent()) {
                adHocProjectModel.setUuid(adHocProjectCheck.get().getUuid());
                adHocProjectModel.setCustomerId(adHocRequest.getCustomerId());
                adHocProjectModel.setProjectName(adHocRequest.getProjectName());
                List<String> projectTask = new ArrayList<>();
                if (!adHocRequest.getProjectTask().isEmpty()) {
                    for (String projectTaskData : adHocRequest.getProjectTask()) {
                        projectTask.add(projectTaskData);
                    }
                }
                adHocProjectModel.setProjectTask(projectTask);
                adHocProjectModel.setDueDate(adHocRequest.getDueDate());
                adHocProjectModel.setStatus(adHocRequest.getStatus());
                adHocProjectModel.setEmail(adHocProjectModel.getEmail());
                adHocProjectRepository.save(adHocProjectModel);
                return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON)
                        .body("AdHoc Projcet Updated");
            } else {
                return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON)
                        .body("Ad HocProject Does Not Exists With The Following Unique Identifier!");
            }
        }
    }

    //save file related to adHod Project!
    private void storeAdHocProjectFile(MultipartFile file, String authenticatedUser, String projectHocUuid) throws IOException {
        if (file != null && !file.isEmpty() && file.getBytes().length > 0) {
            Files projectFile = new Files();
            projectFile.setUuid(generateSerialNumber());
            projectFile.setEmail(authenticatedUser);
            projectFile.setFilename(file.getOriginalFilename());
            projectFile.setFiletype(file.getContentType());
            projectFile.setFile(file.getBytes());
            projectFile.setCustomerSerial(null);
            projectFile.setProjectHocSerial(projectHocUuid);
            projectFile.setCustomerTaskSerial(null);
            filesRepository.save(projectFile);
        }
    }

    public String generateSerialNumber() {
        // You can use a UUID or any other unique identifier generation mechanism
        return UUID.randomUUID().toString();
    }

    public ResponseEntity<?> getAllAdHocProjects(Principal principal) {
        List<AdHocProjectResponse> adHocProjectResponse = new ArrayList<>();
        List<AdHocProject> adHocProjectdata = adHocProjectRepository.findByEmail(principal.getName());
        if (!adHocProjectdata.isEmpty()) {
            for (AdHocProject data : adHocProjectdata) {
                AdHocProjectResponse responsedata = new AdHocProjectResponse();
                responsedata.setUuid(data.getUuid());
                responsedata.setProjectName(data.getProjectName());
                responsedata.setResource(null);
                Optional<Customer> customer = customerRepository.findBySerialNumberAndEmail(data.getCustomerId(), principal.getName());
                if (customer.isPresent()) {
                    responsedata.setCustomerName(customer.get().getName());
                }
                List<String> projectTask = new ArrayList<>();
                if (data.getProjectTask() != null && !data.getProjectTask().isEmpty()) {
                    for (String projectTaskData : data.getProjectTask()) {
                        projectTask.add(projectTaskData);
                    }
                }
                responsedata.setProjectTask(projectTask);
                responsedata.setDueDate(data.getDueDate());
                responsedata.setStatus(data.getStatus());
                adHocProjectResponse.add(responsedata);
            }
            return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON)
                    .body(adHocProjectResponse);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).contentType(MediaType.APPLICATION_JSON)
                    .body("No AdHoc Projects Are Available");
        }
    }

    public ResponseEntity<?> getAdHocProjectDetails(Principal principal, String uuid) {
        Optional<AdHocProject> adHocProjectCheck = adHocProjectRepository.findByUuidAndEmail(uuid, principal.getName());
        if (adHocProjectCheck.isPresent()) {
            AdHocProjectResponse responsedata = new AdHocProjectResponse();
            responsedata.setUuid(adHocProjectCheck.get().getUuid());
            responsedata.setProjectName(adHocProjectCheck.get().getProjectName());
            responsedata.setResource(null);
            Optional<Customer> customer = customerRepository.findBySerialNumberAndEmail(adHocProjectCheck.get().getCustomerId(), principal.getName());
            if (customer.isPresent()) {
                responsedata.setCustomerName(customer.get().getName());
            }
            List<String> projectTask = new ArrayList<>();
            if (adHocProjectCheck.get().getProjectTask() != null && !adHocProjectCheck.get().getProjectTask().isEmpty()) {
                for (String projectTaskData : adHocProjectCheck.get().getProjectTask()) {
                    projectTask.add(projectTaskData);
                }
            }
            responsedata.setProjectTask(projectTask);
            responsedata.setDueDate(adHocProjectCheck.get().getDueDate());
            responsedata.setStatus(adHocProjectCheck.get().getStatus());
            List<Files> adHocFile = filesRepository.findByEmailAndProjectHocSerial(principal.getName(), uuid);
            List<CustomerFilesDto> responseFile = new ArrayList<>();
            if (!adHocFile.isEmpty()) {
                for (Files fileData : adHocFile) {
                    CustomerFilesDto customerFilesDto = new CustomerFilesDto();
                    customerFilesDto.setUuid(fileData.getUuid());
                    customerFilesDto.setFilename(fileData.getFilename());
                    customerFilesDto.setFiletype(fileData.getFiletype());
                    customerFilesDto.setFileSize(fileData.getFileSize());
                    customerFilesDto.setFile(fileData.getFile());
                    responseFile.add(customerFilesDto);
                }
                responsedata.setFile(responseFile);
            }
            return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON)
                    .body(responsedata);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).contentType(MediaType.APPLICATION_JSON)
                    .body("No AdHoc Project Is Available For The Following Unique Identifier ");
        }
    }

    public ResponseEntity<?> deleteAdHocData(Principal principal, String uuid) {
        Optional<AdHocProject> adHocProject = adHocProjectRepository.findByUuidAndEmail(uuid,principal.getName());
        if (adHocProject.isPresent()){
            adHocProjectRepository.deleteByEmailAndUuid(principal.getName(),uuid);
            filesRepository.deleteByEmailAndProjectHocSerial(principal.getName(),uuid);
            return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON)
                    .body("Project AdHoc Removed Successfully.");
        }else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).contentType(MediaType.APPLICATION_JSON)
                    .body("No AdHoc Project Is Available For The Following Unique Identifier ");
        }
    }
}
