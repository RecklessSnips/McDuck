package com.example.mcduckshoppingwebsite.Controller;

import com.example.mcduckshoppingwebsite.Entity.Cart;
import com.example.mcduckshoppingwebsite.Entity.Product;
import com.example.mcduckshoppingwebsite.Entity.User;
import com.example.mcduckshoppingwebsite.Service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Controller
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping("/api/addCart")
    public ResponseEntity<Boolean> addCart(@RequestBody Cart cart){
        boolean success = cartService.addCart(cart.getUser(), cart.getProduct());
        System.out.println("If insert cart successfully: " + success);
        return success
                ? new ResponseEntity<>(true, HttpStatus.OK)
                : new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
    }

    @PostMapping("/api/deleteCart")
    public ResponseEntity<Boolean> deleteCart(@RequestBody Cart cart){
        boolean deleted = cartService.deleteCart(cart.getUser(), cart.getProduct());
        return deleted
                ? new ResponseEntity<>(true, HttpStatus.OK)
                : new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/api/getCart")
    public ResponseEntity<List<Product>> getCart(@RequestBody User user){
        System.out.println("ok"+user);
        List<Product> products = cartService.getProductsByUserCart(user);
        return !products.isEmpty()
                ? new ResponseEntity<>(products, HttpStatus.OK)
                : new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }
}
