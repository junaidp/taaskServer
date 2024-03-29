package com.echo.taask.customer.reporisoties;

import com.echo.taask.recources.Links;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface CustomerLinksRepostiory extends MongoRepository<Links, String> {
    List<Links> findByEmailAndCustomerSerial(String email, String serialNumber);
    Long deleteByEmailAndUuid(String email, String uuid);
    Links deleteAllByEmailAndCustomerSerial(String email, String serialNumber);
    Optional<Links> findByEmailAndUuid(String email,String uuid);
}
