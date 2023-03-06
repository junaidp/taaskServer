package com.echo.taask.controller;


import com.echo.taask.helper.CustomerHelper;
import com.echo.taask.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("api/customer")
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class CustomerController {

    @Autowired
    CustomerHelper customerhelper;

    @PostMapping("savecustomer")
    public ResponseEntity<String> savecustomer(@RequestBody Customer customer)
    {
        try {
            return new ResponseEntity<>(customerhelper.saveCustomer(customer), HttpStatus.OK);
        }catch(Exception ex)
        {
            return new ResponseEntity("Failed to save customer", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("getallcustomers")
    public List<Customer> getallcustomers()
    {
        return customerhelper.getAllCustomers();
    }
}
