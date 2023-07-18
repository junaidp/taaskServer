package com.echo.taask.customerTask;

import com.echo.taask.customer.Customer;
import com.echo.taask.customer.dto.ImageResponse;
import com.echo.taask.customer.reporisoties.CustomerFilesRepository;
import com.echo.taask.customer.reporisoties.CustomerRepository;
import com.echo.taask.recources.Files;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.IOException;
import java.security.Principal;
import java.util.*;

@Service
@RequiredArgsConstructor
public class CustomerTaskService {
    private final CustomerRepository customerRepository;
    private final CustomerFilesRepository projectFilesReporitory;
    private final CustomerTaskRepository customerTaskRepository;
    public static final List<String> ALLOWED_STATUSES = Arrays.asList("high", "moderate", "low");

    public ResponseEntity<?> saveTask(String authenticatedUser, CustomerTaskRequest customerTaskRequest, List<MultipartFile> file) throws IOException {
        CustomerTask customerTask = new CustomerTask();
        Optional<Customer> customer = customerRepository.findBySerialNumberAndEmail(customerTaskRequest.getCustomerSerialNumber()
                , authenticatedUser);
        if (!customer.isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).contentType(MediaType.APPLICATION_JSON).body("Please Select A Valid Customer");
        }
        if (customerTaskRequest != null) {
            if (!ALLOWED_STATUSES.contains(customerTaskRequest.getTaskPriority().toLowerCase())) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).contentType(MediaType.APPLICATION_JSON).body("Invalid Priority value. Allowed values are: high, moderate, low");
            }
            ArrayList<SubTask> subTasks = new ArrayList<>();
            for (SubTask task : customerTaskRequest.getSubTask()) {
                subTasks.add(task);
            }
            customerTask.setTaskPriority(customerTaskRequest.getTaskPriority().toLowerCase());
            customerTask.setCustomerTaskSerial(generateSerialNumber());
            customerTask.setCustomerSerialNumber(customerTaskRequest.getCustomerSerialNumber());
            customerTask.setTaskName(customerTaskRequest.getTaskName());
            customerTask.setDueDate(customerTaskRequest.getDueDate());
            customerTask.setAssignedDate(customerTaskRequest.getAssignedDate());
            customerTask.setStatus(customerTaskRequest.getStatus());
            customerTask.setUserEmail(authenticatedUser);
            customerTask.setSubTask(subTasks);
            customerTaskRepository.save(customerTask);
        }
        if (file != null && !file.isEmpty()) {
            for (MultipartFile fileData : file) {
                Files projectFile = new Files();
                projectFile.setUuid(generateSerialNumber());
                projectFile.setEmail(authenticatedUser);
                projectFile.setFilename(fileData.getOriginalFilename());
                projectFile.setFiletype(fileData.getContentType());
                projectFile.setFile(fileData.getBytes());
                projectFile.setCustomerTaskSerial(customerTask.getCustomerSerialNumber());
                projectFilesReporitory.save(projectFile);
            }
        }
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body("Customer Task Created!");
    }

    public String generateSerialNumber() {
        // You can use a UUID or any other unique identifier generation mechanism
        return UUID.randomUUID().toString();
    }

    public ResponseEntity<?> getAllTask(Principal principal, int page, int size) {
        List<CustomerTaskResponse> returnListResponse = new ArrayList<>();
        ImageResponse imageResponse = new ImageResponse();
        Pageable pageable = PageRequest.of(page, size, Sort.by("taskPriority").descending());
        Page<CustomerTask> availableCustomerTask = customerTaskRepository.findAllByUserEmail(principal.getName(), pageable);
        if (availableCustomerTask.hasContent()) {
            for (CustomerTask customerTaskData : availableCustomerTask.getContent()) {
                CustomerTaskResponse customerTaskResponse = new CustomerTaskResponse();
                Optional<Customer> relatedCustomer = customerRepository
                        .findBySerialNumberAndEmail(customerTaskData.getCustomerSerialNumber(), principal.getName());
                if (relatedCustomer.isPresent()) {
                    getCustomerData(relatedCustomer.get(), customerTaskResponse, imageResponse);
                }
                customerTaskResponse.setCustomerTask(customerTaskData.getTaskName());
                customerTaskResponse.setTaskPriority(customerTaskData.getTaskPriority());
                customerTaskResponse.setDueDate(customerTaskData.getDueDate());
                customerTaskResponse.setAssignedDate(customerTaskData.getAssignedDate());
                customerTaskResponse.setStatus(String.valueOf(customerTaskData.getStatus()));
                customerTaskResponse.setImage(imageResponse);
                returnListResponse.add(customerTaskResponse);
            }
            return ResponseEntity.status(HttpStatus.OK)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(returnListResponse);
        } else {
            return ResponseEntity.status(HttpStatus.OK)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body("User Ain't Got No Task!");
        }
    }

    private void getCustomerData(Customer customer, CustomerTaskResponse customerTaskResponse, ImageResponse imageResponse) {
        customerTaskResponse.setCustomerName(customer.getName());
        customerTaskResponse.setCustomerStage(customer.getCustomerStage());
        imageResponse.setName(customer.getImage().getName());
        imageResponse.setContentType(customer.getImage().getContentType());
        imageResponse.setData(customer.getImage().getData());
    }
}
