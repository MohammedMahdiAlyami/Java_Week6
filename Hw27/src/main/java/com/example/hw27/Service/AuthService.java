package com.example.hw27.Service;

import com.example.hw27.ApiException.ApiException;
import com.example.hw27.Model.User;
import com.example.hw27.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public void register(User user) {
        String hash = new BCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword(hash);
        userRepository.save(user);
    }

    public void updateUser(Integer userid, User user) {
        User oldUser = userRepository.findUsersById(userid);
        if (oldUser == null) {
            throw new ApiException("User not registered");
        }
        oldUser.setUsername(user.getUsername());
        String hash = new BCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword(hash);
        userRepository.save(oldUser);
    }

    public void deleteUser(Integer userid) {
        User oldUser = userRepository.findUsersById(userid);
        if (oldUser == null) {
            throw new ApiException("User not registered");
        }
        userRepository.delete(oldUser);
    }


}