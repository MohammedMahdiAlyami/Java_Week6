package com.example.hw27.Controller;

import com.example.hw27.Model.User;
import com.example.hw27.Service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @GetMapping("/get")
    public ResponseEntity getAll(){
        return ResponseEntity.status(200).body(authService.getAll());
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody User myuser){
        authService.register(myuser);
        return ResponseEntity.status(200).body("User registered");
    }

    @GetMapping("/login")
    public ResponseEntity login(){
        return ResponseEntity.status(200).body("login");
    }

    @GetMapping("/logout")
    public ResponseEntity logout(){
        return ResponseEntity.status(200).body("logout");
    }

    @GetMapping("/user")
    public ResponseEntity user(){
        return ResponseEntity.status(200).body("Welcome User");
    }

    @PutMapping("/update")
    public ResponseEntity updateUser(@AuthenticationPrincipal User user, @RequestBody@Valid User user1){
        authService.updateUser(user.getId(),user1);
        return ResponseEntity.status(200).body("User updated");
    }

    @DeleteMapping("/delete")
    public ResponseEntity deleteUser(@AuthenticationPrincipal User user){
        authService.deleteUser(user.getId());
        return ResponseEntity.status(200).body("User deleted");
    }

}