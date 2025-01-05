package net.engineeringdigest.journalApp.repository;

import net.engineeringdigest.journalApp.entity.UserEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.User;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;


@SpringBootTest
public class UserRepositoryImplTests {
    @Autowired
    UserRepositoryImpl userRepository;

    @Test
    public void getUsersForSATest(){
        List<UserEntity> users = userRepository.getUsersForSA();
        assertNotNull(users);
    }
}
