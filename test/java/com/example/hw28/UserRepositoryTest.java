package com.example.hw28;

import com.example.hw28.Model.User;
import com.example.hw28.Repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    User user, user1, user2,user3;

    @BeforeEach
    void setUp(){

        user1 =new User(null, "Maha", "Maha1122", "ADMIN", null);
        user2 =new User(null, "Majd", "Majd1122", "CUSTOMER", null);
        user3 =new User(null, "Salem", "Salem1122", "CUSTOMER", null);
    }

    @Test
    public void findMyUserByUsername(){
        userRepository.save(user1);
        user = userRepository.findUsersByUsername(user1.getUsername());
        Assertions.assertThat(user).isEqualTo(user1);

    }

    @Test
    public void findMyUserById(){
        userRepository.save(user1);
        user = userRepository.findUsersById(user1.getId());
        Assertions.assertThat(user).isEqualTo(user1);

    }
}