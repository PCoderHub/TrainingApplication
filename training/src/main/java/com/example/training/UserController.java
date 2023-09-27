package com.example.training;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/signup")          // to enable user registration
    public ResponseEntity<?> createUser(@RequestBody User user) {
        Optional<User> existingUser = userService.findByEmail(user.getEmail());
        if(existingUser.isPresent()) {
            return new ResponseEntity<String>("User already exists!!", HttpStatus.BAD_REQUEST);
        }

        User newUser = userService.saveUser(user);
        return ResponseEntity.ok(newUser);
    }

    @PostMapping("/login")            // to authenticate users
    public ResponseEntity<?> loginUser(@RequestBody User user) {
        Optional<User> existingUser = userService.findByEmail(user.getEmail());
        if(existingUser == null) {
            return new ResponseEntity<String>("User not found!!", HttpStatus.BAD_REQUEST);
        }
        if(user.getPassword() != existingUser.get().getPassword()) {
            return new ResponseEntity<String>("Wrong Password!!", HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(user.getEmail());
    }
}
