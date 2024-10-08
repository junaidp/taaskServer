package com.echo.taask.customer.reporisoties;

import com.echo.taask.customer.Customer;
import com.echo.taask.snapshots.dto.CategoryCount;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends MongoRepository<Customer, String> {
    Optional<Customer> findByIdAndEmail(String id, String email);
    List<Customer> findAllByEmail(String email);
    Optional<Customer> findBySerialNumberAndEmail(String serialNumber, String email);
    Customer deleteBySerialNumberAndEmail(String serialNumber, String email);
}
