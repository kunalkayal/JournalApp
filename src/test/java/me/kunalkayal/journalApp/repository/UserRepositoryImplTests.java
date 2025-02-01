package me.kunalkayal.journalApp.repository;

import me.kunalkayal.journalApp.entity.UserEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;


@SpringBootTest
public class UserRepositoryImplTests {
    @Autowired
    UserRepositoryImpl userRepository;

    @Test
    public void getUsersForSATest(){
//        List<UserEntity> users = userRepository.getUsersForSA();
//        assertNotNull(users);
    }
}
