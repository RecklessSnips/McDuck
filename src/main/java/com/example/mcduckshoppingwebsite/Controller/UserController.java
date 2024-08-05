package com.example.mcduckshoppingwebsite.Controller;

import com.example.mcduckshoppingwebsite.Entity.User;
import com.example.mcduckshoppingwebsite.Service.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.UUID;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

//    private final TokenStoreController tokenStore = new TokenStoreController();

    @Autowired
    private TokenStoreController tokenStore;

    @PostMapping("/api/register")
    public ResponseEntity<Boolean> register(@RequestBody User user){
        Boolean ifSuccess = userService.register(user);
        return (ifSuccess) ? new ResponseEntity<>(true, HttpStatus.OK) : new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
    }

    @PostMapping("/api/logout")
    public ResponseEntity<Boolean> logout(@RequestBody User user,
                                          HttpSession session,
                                          HttpServletRequest request,
                                          HttpServletResponse response){
        session.invalidate();
        // 在服务器端接受所有的cookie
        Cookie[] cookies = request.getCookies();
        boolean ifLogOut = false;
        // 拿到当前登陆的用户
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                System.out.println("Cookie name: " + cookie.getName());
                System.out.println("Cookie value: " + cookie.getValue());
                if (!cookie.getName().equals("JSESSIONID")){
                    ifLogOut = tokenStore.deleteUser(cookie.getValue());
                    System.out.println("If logout: " + ifLogOut);
                }
                // Set the cookie to expire immediately
                cookie.setMaxAge(0);

                // Set the path if necessary. This is important if the cookies were set with a specific path,
                // otherwise, the browser may not delete them. Adjust "/yourappcontext" as needed.
                cookie.setPath("/");

                // Add the cookie back to the response to update it in the client's browser
                response.addCookie(cookie);
            }
        }
        
        return ifLogOut ? new ResponseEntity<>(true, HttpStatus.OK)
                : new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/api/login")
    public ResponseEntity<Boolean> validateUser(@RequestBody User user, HttpSession session, HttpServletResponse response){
        boolean validate = userService.validateUser(user.getEmail(), user.getPassword());
        // 差是否user以及登陆/注册
        if (validate){
            User currentUser = userService.getUserByEmail(user.getEmail());
            String token = UUID.randomUUID().toString();
            session.setAttribute("token", token); // Store token in session
            session.setAttribute("userInfo", currentUser);

            tokenStore.storeUser(token, currentUser);

            // Add token to cookie
            String cookieName = user.getEmail().substring(0,3);
            Cookie cookie = new Cookie(cookieName, token);

            // 24小时的登陆时长
            int expireSeconds = 3600 * 24;
            cookie.setMaxAge(expireSeconds);
            cookie.setHttpOnly(true);
            cookie.setPath("/");
            response.addCookie(cookie);

            session.setMaxInactiveInterval(expireSeconds);
            return new ResponseEntity<>(true, HttpStatus.OK);
        }else{
            return new ResponseEntity<>( false, HttpStatus.UNAUTHORIZED);
        }
    }

    // check if there's user cookie can be used to sign in
    @GetMapping("/api/validate-session")
    public ResponseEntity<Boolean> validateSession(HttpServletRequest request) {
        // 开一个session方便cookie数组拿取登陆者信息
        HttpSession session = request.getSession();
        Cookie[] cookies = request.getCookies();
        if (cookies != null){
            // 开启 session 后 cookies 大小最少为1，如果不为1说明有user登陆过
            if (!(cookies.length == 1)){
                return new ResponseEntity<>(true, HttpStatus.OK);
            }
        }
        // Ensure the response is in JSON format for consistency
        return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
    }

    // 如果有登陆，调用这个方法来拿取最近登陆人的信息
    @GetMapping("/api/get-user-session")
    public ResponseEntity<User> getUserSession(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (!(cookies.length == 1)){
            // 拿到最近，上一次登陆的用户
            Cookie lastCookie = cookies[cookies.length - 2];
            // 从存储的 tokenStore 里调取用户信息
            User user = tokenStore.getUser(lastCookie.getValue());
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }
}
