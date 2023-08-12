package com.echo.taask.user;
import com.echo.taask.user.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User , String> {
    boolean existsByEmail(String email);
    User findByEmail(String email);
    Optional<User> findByEmailAndImageIsNull(String email);
}
