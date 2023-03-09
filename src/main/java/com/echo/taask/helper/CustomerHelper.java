package com.echo.taask.helper;

import com.echo.taask.model.Customer;
import com.echo.taask.model.User;
import com.echo.taask.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Component
public class CustomerHelper {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    FilesHelper filesHelper;


    public String saveCustomer(Customer customer, MultipartFile file){
        try {
            customer.setFileId(filesHelper.uploadFile(file));
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
