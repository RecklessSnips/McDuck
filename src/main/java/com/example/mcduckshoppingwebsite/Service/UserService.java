package com.example.mcduckshoppingwebsite.Service;

import com.example.mcduckshoppingwebsite.Entity.User;
import com.example.mcduckshoppingwebsite.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    public User getUserByEmail(String email){
        return userRepo.getUserByEmail(email);
    }


    public boolean validateUser(String email, String password){
        return userRepo.validateUser(email, password);
    }

    public Boolean register(User user) {
        return userRepo.register(user);
    }
}
