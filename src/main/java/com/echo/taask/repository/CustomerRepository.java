package com.echo.taask.repository;

import com.echo.taask.model.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface CustomerRepository extends MongoRepository<Customer, String> {
    @Query("{'Customername' : ?0'}")
    Customer findByName(String name);
}
