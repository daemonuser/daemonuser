package com.paloit.security.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @GetMapping()
    public ResponseEntity<User> getCurrentUser(HttpSession session) {
        return ResponseEntity.ok().body(getUserFromSession(session));
    }

    private User getUserFromSession(HttpSession session) {
        Object user = session.getAttribute("currentUser");
        if (user != null && user instanceof User) {
            return (User)user;
        } else {
            return new AnonymousUser();
        }
    }

    @PostMapping
    public ResponseEntity postUser(@RequestBody User user) {
        try {
            userRepository.saveUser(user);
            return ResponseEntity.ok().build();
        } catch (SaveUserException e) {
            List<String> errors = e.getErrors().stream().map(error -> error.getMessage()).collect(Collectors.toList());
            return ResponseEntity.badRequest().body(errors);
        }
    }
}
