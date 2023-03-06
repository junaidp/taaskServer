package com.echo.taask.controller;


import com.echo.taask.helper.CustomerHelper;
import com.echo.taask.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("api/customer")
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class CustomerController {

    @Autowired
    CustomerHelper customerhelper;

    @PostMapping("savecustomer")
    public String savecustomer(@RequestBody Customer customer)
    {

        return customerhelper.saveCustomer(customer);
    }

    @GetMapping("getallcustomers")
    public List<Customer> getallcustomers()
    {
        return customerhelper.getAllCustomers();
    }
}
