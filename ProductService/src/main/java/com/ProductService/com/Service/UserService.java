package com.ProductService.com.Service;

import com.ProductService.com.Entity.User;
import com.ProductService.com.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserService {
    @Autowired
    private UserRepo userRepo;

    public User getUser(String username){
        return userRepo.findByUsername(username);
    }
}
