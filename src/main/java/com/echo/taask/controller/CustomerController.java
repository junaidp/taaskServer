package com.echo.taask.controller;


import com.echo.taask.dto.customer.CustomerDto;
import com.echo.taask.helper.CustomerHelper;
import com.echo.taask.model.Customer;
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

    @PostMapping("saveCustomer")
    public ResponseEntity<?> savecustomer(Principal principal
            , @RequestPart("image") MultipartFile image
            , @RequestPart("customer") CustomerDto customer) {
        try {
            String authenticatedUser = principal.getName();
            return customerHelper.saveCustomer(authenticatedUser, customer, image);
        } catch (Exception ex) {
            return new ResponseEntity("Please Contact Help Center!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }




//    @GetMapping("/getAllCustomers")
//    public ResponseEntity<List<Customer>> getAllCustomers() {
//        try {
//            return new ResponseEntity<>(customerHelper.getAllCustomers(), HttpStatus.OK);
//        } catch (Exception ex) {
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }
//    }
//
//    @GetMapping("getCustomer")
//    public ResponseEntity<?> getCustomer(@RequestParam String userid) {
//        try {
//            return new ResponseEntity<>(customerHelper.findCustomerById(userid), HttpStatus.OK);
//        } catch (Exception ex) {
//            return ResponseEntity.badRequest().body("Failed to Get Customer");
//        }
//    }
}
