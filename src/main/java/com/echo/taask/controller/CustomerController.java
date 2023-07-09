package com.echo.taask.controller;


import com.echo.taask.dto.customer.CustomerDto;
import com.echo.taask.dto.customer.CustomerLinkDto;
import com.echo.taask.helper.CustomerHelper;
import com.echo.taask.model.Customer;
import com.echo.taask.model.CustomerLinks;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.util.List;

@RequestMapping("api/customer")
@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
public class CustomerController {
    private final CustomerHelper customerHelper;
    Gson gson = new Gson();
    @PostMapping("saveCustomer")
    public ResponseEntity<?> savecustomer(Principal principal
            , @RequestPart(value = "image", required = false) MultipartFile image
            , @RequestPart("customer") String customer
            , @RequestPart(value = "file", required = false) MultipartFile file
            , @RequestPart("link") String customerLinkDto) {
        try {
            String authenticatedUser = principal.getName();
            CustomerDto customerJson = gson.fromJson(customer,CustomerDto.class);
            CustomerLinkDto customerlinks = gson.fromJson(customerLinkDto,CustomerLinkDto.class);
            return customerHelper.saveCustomer(authenticatedUser, customerJson, image, file, customerlinks);
        } catch (Exception ex) {
            return new ResponseEntity("Please Contact Help Center!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllCustomer(Principal principal,
                                            @RequestHeader("Authorization") String Authorizaion) {
        try {
            String authenticatedUser = principal.getName();
            return customerHelper.getCustomersList(authenticatedUser);
        } catch (Exception e) {
            return new ResponseEntity("Please Contact Help Center!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("getCustomer")
    public ResponseEntity<?> getCustomer(Principal principal,
                                         @RequestParam String userid) {
        try {
            String authenticatedUser = principal.getName();
            return customerHelper.findCustomerById(authenticatedUser, userid);
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().body("Please Contact Help Center");
        }
    }

    @DeleteMapping("DeleteCustomer")
    public ResponseEntity<?> deleteCustomer(Principal principal,
                                            @RequestParam String userId) {
        try {
            String authenticatedUser = principal.getName();
            return customerHelper.deleteCustomerById(authenticatedUser, userId);
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().body("Please Contact Help Center");
        }
    }

    @PutMapping("updateCustomer")
    public ResponseEntity<?> updateCustomer(Principal principal,
                                            @RequestPart(value = "image", required = false) MultipartFile image,
                                            @RequestPart("customer") CustomerDto customerDto,
                                            @RequestParam String userId) {
        try {
            String authenticatedUser = principal.getName();
            return customerHelper.updateCustomer(authenticatedUser, customerDto, userId, image);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.internalServerError().body("Please Contact Help Center");
        }
    }
}
