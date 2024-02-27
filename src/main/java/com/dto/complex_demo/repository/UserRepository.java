package com.dto.complex_demo.repository;

import com.dto.complex_demo.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
}
