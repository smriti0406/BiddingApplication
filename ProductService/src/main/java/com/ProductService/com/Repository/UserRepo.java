package com.ProductService.com.Repository;

import com.ProductService.com.Entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepo extends MongoRepository<User, String> {
    User findByUsername(String username);


}