package com.echo.taask.customer.reporisoties;

import com.echo.taask.recources.Files;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;


public interface CustomerFilesRepository extends MongoRepository<Files, String> {

    List<Files> findByEmailAndCustomerSerial(String email, String serialNumber);

    Files deleteAllByEmailAndCustomerSerial(String email, String serialNumber);

    List<Files> findAllByCustomerTaskSerialAndEmail(String serialNumber, String email);

    Long deleteByEmailAndUuid(String email, String uuid);


}
