package com.echo.taask.repository;

import com.echo.taask.dto.customer.CustomerFilesDto;
import com.echo.taask.model.Customer;
import com.echo.taask.model.CustomerFiles;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;


public interface CustomerFilesRepository extends MongoRepository<CustomerFiles,String > {
    List<CustomerFiles> findByEmail(String email);
    CustomerFiles deleteAllByEmailAndCustomerSerial(String email,String serialNumber);
}
