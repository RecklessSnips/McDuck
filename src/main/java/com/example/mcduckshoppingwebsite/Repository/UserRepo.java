package com.example.mcduckshoppingwebsite.Repository;


import com.example.mcduckshoppingwebsite.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class UserRepo {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public boolean register(User user) {
        String sql = "INSERT INTO User(\n" +
                "    user_id,\n" +
                "    password,\n" +
                "    nick_name,\n" +
                "    first_name,\n" +
                "    last_name ,\n" +
                "    full_name ,\n" +
                "    address ,\n" +
                "    phone_number,\n" +
                "    email ,\n" +
                "    credit_card,\n" +
                "    balance\n" +
                ") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        // 新创建的用户只有 用户名(nickname)，邮箱，密码，和 余额为0的账户
        int rows = jdbcTemplate.update(sql, UUID.randomUUID().toString().substring(0, 6), user.getPassword(), user.getNickName(), user.getFirstName(), user.getLastName(),
                user.getFullName(), user.getAddress(), user.getPhoneNumber(), user.getEmail(),
                user.getCreditCard(), 0);
        System.out.println("Affected rows: " + rows);

        // 无row被影响，说明注册失败
        // 1row被影响，说明注册成功
        return rows != 0;

    }

    public User getUserByEmail(String email){
        String sql = "SELECT * FROM User WHERE email = ?";
        User user = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), email);
        System.out.println("User search result: " + user);
        return user;
    }

    public boolean validateUser(String email, String password){
        User user = getUserByEmail(email);
        if (!(user == null)) {
            String pwd = user.getPassword();
            System.out.println("Password is: " + pwd);
            if (pwd.equals(password)){
                System.out.println("Password correct");
            }else{
                System.out.println("Password Incorrect!!!");
            }
            return pwd.equals(password);
        }else{
            System.out.println("User not exist!");
            return false;
        }
    }
}
