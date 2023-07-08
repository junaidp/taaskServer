package com.echo.taask.repository;

import com.echo.taask.model.CustomerFiles;
import com.echo.taask.model.CustomerLinks;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface CustomerLinksRepostiory extends MongoRepository<CustomerLinks, String> {
    List<CustomerLinks> findByEmail(String email);

    CustomerLinks deleteAllByEmailAndCustomerSerial(String email,String serialNumber);
}
