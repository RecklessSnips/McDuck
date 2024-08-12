package com.example.mcduckshoppingwebsite.Repository;

import com.example.mcduckshoppingwebsite.Entity.Product;
import com.example.mcduckshoppingwebsite.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CartRepo {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public boolean addCart(User user, Product product){
        String sql = "INSERT INTO Cart VALUES(?, ?)";
        int rows = jdbcTemplate.update(sql, user.getUserId(), product.getProduct_id());
        System.out.println("Cart rows affected: " + rows);
        return rows != 0;
    }

    public boolean deleteCart(User user, Product product){
        String sql = "DELETE FROM Cart WHERE user_id = ? AND product_id = ?";
        int rows = jdbcTemplate.update(sql, user.getUserId(), product.getProduct_id());
        System.out.println("Deleted rows from cart: " + rows);
        return rows != 0;
    }

    public List<Product> getProductsByUserCart(User user){
        System.out.println("!!!!!"+user);
        String sql = "SELECT * FROM Product\n" +
                        "JOIN Cart USING (product_id)\n" +
                        "WHERE user_id = ?";
        List<Product> products = jdbcTemplate.query(sql, new Object[]{user.getUserId()}, new BeanPropertyRowMapper<>(Product.class));
        System.out.println("?????????????????" + products);
        return products;
    }
}
