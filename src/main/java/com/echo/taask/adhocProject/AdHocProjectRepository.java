package com.echo.taask.adhocProject;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface AdHocProjectRepository extends MongoRepository<AdHocProject, String> {
    Optional<AdHocProject> findByUuidAndEmail(String uuid, String email);
    List<AdHocProject> findByEmail(String email);
    AdHocProject deleteByEmailAndUuid(String email,String uuid);

}
