package com.example.hw27.Repository;

import com.example.hw27.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    User findUsersByUsername(String username);
    User findUsersById(Integer id);
}