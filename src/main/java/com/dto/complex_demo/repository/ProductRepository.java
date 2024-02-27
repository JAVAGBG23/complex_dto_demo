package com.dto.complex_demo.repository;

import com.dto.complex_demo.models.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product, String> {
}
