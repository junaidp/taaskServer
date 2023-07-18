package com.echo.taask.customerTask;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface CustomerTaskRepository extends MongoRepository<CustomerTask, String> {
    List<CustomerTask> findAllByUserEmail(String email);
    Page<CustomerTask> findAllByUserEmail(String email, Pageable pageable);
    Optional<CustomerTask> findByCustomerTaskSerialAndUserEmail(String taskSerial,String userEmail);
    CustomerTask deleteByCustomerTaskSerialAndUserEmail(String taskSerial, String userEmail);
}
