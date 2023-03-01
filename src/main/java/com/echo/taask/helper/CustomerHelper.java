package com.echo.taask.helper;

import com.echo.taask.model.Customer;
import com.echo.taask.model.User;
import com.echo.taask.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CustomerHelper {

    @Autowired
    CustomerRepository customerRepository;


    public String saveCustomer(Customer customer){
        try {
            //String password = user.getPassword();
            customerRepository.save(customer);
            return "user saved";
        }catch (Exception e)
        {
            throw e;
        }
    }
    public List<Customer> getAllCustomers()
    {
        try{
            return customerRepository.findAll();
        }catch (Exception e)
        {
            throw e;
        }
    }

}
