package com.example.mcduckshoppingwebsite.Repository;

import com.example.mcduckshoppingwebsite.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class TokenStoreRepo {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private UserRepo userRepo;

    // TODO: 先完成正常的user添加问题，然后这里再处理user的相关
    public void addUserByToken(String token, User user){
        String sql = "INSERT INTO TokenStore VALUES(?, ?)";
        System.out.println("USER: " + user);
        System.out.println("User id: " + user.getUserId());
        int rows = jdbcTemplate.update(sql, token, user.getUserId());
        System.out.println("Affected rows: " + rows);
    }

    public User getUserByToken(String token){
        String sql = "SELECT *\n" +
                "FROM User WHERE user_id = ( SELECT user_id\n" +
                "                            FROM TokenStore\n" +
                "                            JOIN User USING(user_id))";
        User user = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(User.class));
        System.out.println(user);
        return user;
    }

    public boolean deleteUserByToken(String token){
        String sql = "DELETE FROM TokenStore WHERE token = ?";
        int rows = jdbcTemplate.update(sql, token);
        System.out.println("Deletion Affected rows: " + rows);
        // 如果不为0，说明logout成功
        return rows != 0;
    }
}
