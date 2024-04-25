package com.example.mcduckshoppingwebsite.Service;

import com.example.mcduckshoppingwebsite.Entity.Product;
import com.example.mcduckshoppingwebsite.Entity.User;
import com.example.mcduckshoppingwebsite.Repository.CartRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {

    @Autowired
    private CartRepo cartRepo;

    public boolean addCart(User user, Product product){
        return cartRepo.addCart(user, product);
    }

    public boolean deleteCart(User user, Product product){
        return cartRepo.deleteCart(user, product);
    }

    public List<Product> getProductsByUserCart(User user){
        return cartRepo.getProductsByUserCart(user);
    }
}
