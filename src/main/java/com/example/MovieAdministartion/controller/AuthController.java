package com.example.MovieAdministartion.controller;

import com.example.MovieAdministartion.dto.UserDto;
import com.example.MovieAdministartion.model.User;
import com.example.MovieAdministartion.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/api")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody UserDto request) {
        try {
            userService.registerUser(request);
            return ResponseEntity.ok("User registered successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to register user: " + e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody UserDto request) {
        // Check if the user exists
        User existingUser = userService.getUserByEmail(request.getEmail());
        if (existingUser == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User does not exist");
        }

        // Verify the password
        if (!passwordEncoder.matches(request.getPassword(), existingUser.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
        }

        // Login successful
        return ResponseEntity.ok("Login successful");
    }


}