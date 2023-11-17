package hu.pcvilag.app.controllers;

import hu.pcvilag.app.models.UserEntity;
import hu.pcvilag.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class RegistrationController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserEntity user) {
        if (userService.usernameExists(user.getUsername())) {
            return ResponseEntity.badRequest().body("User with this name already exists");
        }
        userService.registerNewUser(user);
        return ResponseEntity.ok("User registered successfully");
    }
}