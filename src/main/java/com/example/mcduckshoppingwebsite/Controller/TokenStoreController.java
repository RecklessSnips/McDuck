package com.example.mcduckshoppingwebsite.Controller;

import com.example.mcduckshoppingwebsite.Entity.User;
import com.example.mcduckshoppingwebsite.Service.TokenStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.HashMap;

@Controller
public class TokenStoreController {

    /*
     每当一个user log in之后，会创建cookie和token，将这个cookie
     以 token + User 保存进来
     在每次检查user是否login的时候从这里查看是当前cookie的value可以和这里的token匹配
    */

    @Autowired
    private TokenStoreService storeService;

    public void storeUser(String token, User user){
        storeService.addUserByToken(token, user);
    }

    public User getUser(String token){
        return storeService.getUserByToken(token);
    }

    public boolean deleteUser(String token){
        return storeService.deleteUserByToken(token);
    }
}
