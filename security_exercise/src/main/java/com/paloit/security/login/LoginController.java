package com.paloit.security.login;

import com.paloit.security.users.User;
import com.paloit.security.users.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;


@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping
    public ResponseEntity post(@RequestParam("username") String username,
                                    @RequestParam("password") String password,
                                    HttpSession session) {
        User user = userRepository.getUserByUserName(username);
        if(user.password.equals(password)) {
            session.setAttribute("currentUser", user);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
}
