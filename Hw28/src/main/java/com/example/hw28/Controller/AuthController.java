package com.example.hw28.Controller;

import com.example.hw28.Model.User;
import com.example.hw28.Service.AuthService;
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

    @GetMapping("/logout")
    public ResponseEntity logout(){
        return ResponseEntity.status(200).body("logout");
    }

    @DeleteMapping("/deletebyadmin/{userid}")
    public ResponseEntity deleteUserByAdmin(@PathVariable Integer userid){
        authService.deleteUserByAdmin(userid);
        return ResponseEntity.status(200).body(" User deleted");
    }

    @GetMapping("/get-id/{id}")
    public ResponseEntity getUserById(@PathVariable Integer id){
        User use= authService.getUserById(id);
        return ResponseEntity.status(200).body(use);
    }
}