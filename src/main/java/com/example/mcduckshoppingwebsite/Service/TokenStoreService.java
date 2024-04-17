package com.example.mcduckshoppingwebsite.Service;

import com.example.mcduckshoppingwebsite.Entity.User;
import com.example.mcduckshoppingwebsite.Repository.TokenStoreRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TokenStoreService {

    @Autowired
    private TokenStoreRepo storeRepo;

    public void addUserByToken(String token, User user){
        storeRepo.addUserByToken(token, user);
    }

    public User getUserByToken(String token){
        return storeRepo.getUserByToken(token);
    }

    public boolean deleteUserByToken(String token) {
        return storeRepo.deleteUserByToken(token);
    }
}
