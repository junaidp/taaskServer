package com.echo.taask.controller;


import com.echo.taask.helper.CustomerHelper;
import com.echo.taask.model.Customer;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RequestMapping("api/customer")
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class CustomerController {
    private CustomerHelper customerHelper;


    @Autowired
    public CustomerController(CustomerHelper customerHelper)
    {
        this.customerHelper =  customerHelper;
    }
    @PostMapping("saveCustomer")
    public ResponseEntity<String> savecustomer(@RequestParam("file") MultipartFile file, @RequestPart("customer") Customer customer)
    {
        try {
            return new ResponseEntity<>(customerHelper.saveCustomer(customer,file), HttpStatus.OK);
        }catch(Exception ex)
        {
            return new ResponseEntity("Failed to save customer", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("saveCustomerWithoutFile")
    public ResponseEntity<String> saveCustomerWithoutFile( @RequestPart("customer") Customer customer)
    {
        try {
            return new ResponseEntity<>(customerHelper.saveCustomer(customer,null), HttpStatus.OK);
        }catch(Exception ex)
        {
            return new ResponseEntity("Failed to save customer", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("saveOnlyFile")
    public ResponseEntity<String> saveOnlyFile( @RequestParam("file") MultipartFile file)
    {
        try {
            return new ResponseEntity<>(customerHelper.saveCustomer(null,file), HttpStatus.OK);

        }catch(Exception ex)
        {
            return new ResponseEntity("Failed to save customer", HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/getAllCustomers")
    public ResponseEntity<List<Customer>> getAllCustomers()
    {
        try {
            return new ResponseEntity<>(customerHelper.getAllCustomers(),HttpStatus.OK);
        }catch (Exception ex)
        {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("getCustomers")
    public ResponseEntity<String> getCustomers(@RequestParam String userid)
    {
        try {
            return new ResponseEntity<>(customerHelper.getCustomers(userid),HttpStatus.OK);
        }catch (Exception ex)
        {
            return new ResponseEntity<>("Failed to Get Task",HttpStatus.BAD_REQUEST);
        }
    }
}
