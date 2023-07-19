package com.echo.taask.customerTask;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.repository.Update;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.util.List;

@RequestMapping("customerTask")
@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
public class CustomerTaskController {
    private final CustomerTaskService customerTaskService;

    @PostMapping
    public ResponseEntity<?> saveCustomerTask(@Valid @RequestPart(value = "customerTask") CustomerTaskRequest customerTaskRequest,
                                              @RequestPart(required = false) List<MultipartFile> file, Principal principal) {
        try {
            String authenticatedUser = principal.getName();
            return customerTaskService.saveTask(authenticatedUser, customerTaskRequest, file);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).contentType(MediaType.APPLICATION_JSON)
                    .body("Please Contact Help Center");
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllCustomerTask(Principal principal,
                                                @RequestParam int page,
                                                @RequestParam int size) {
        try {
            return customerTaskService.getAllTask(principal, page, size);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).contentType(MediaType.APPLICATION_JSON)
                    .body("Please Contact Help Center");
        }
    }
    @GetMapping("/detail")
    public ResponseEntity<?> getCustomerTaskById(Principal principal,
                                                 @RequestParam String customerTaskSerial)
    {
        try {
            return customerTaskService.getCustomerTaskById(principal,customerTaskSerial);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).contentType(MediaType.APPLICATION_JSON)
                    .body("Please Contact Help Center");
        }
    }
    @DeleteMapping("delete/customerTask")
    public ResponseEntity<?> deleteCustomerTask(Principal principal,
                                                @RequestParam String customerTaskSerial) {
        try {
            return customerTaskService.deleteCustomerTask(principal,customerTaskSerial);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).contentType(MediaType.APPLICATION_JSON)
                    .body("Please Contact Help Center");
        }
    }

    @PutMapping("update")
    public ResponseEntity<?> updateCustomerTask(Principal principal, @RequestBody CustomerTaskRequest customerTaskRequest,
                                                @RequestParam String customerTaskSerial)
    {
        try {
            return customerTaskService.updateCustomerTask(principal,customerTaskSerial,customerTaskRequest);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).contentType(MediaType.APPLICATION_JSON)
                    .body("Please Contact Help Center");
        }
    }


}
