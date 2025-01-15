package me.kunalkayal.journalApp.services;

import lombok.extern.slf4j.Slf4j;
import me.kunalkayal.journalApp.entity.UserEntity;
import me.kunalkayal.journalApp.repository.userRepo;
import me.kunalkayal.journalApp.responseController.WeatherResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;



@Component
@Slf4j
public class UserEntryServices {
    @Autowired
    userRepo userRepo;
    @Autowired
    WeatherResponse response;


    /*
    This two get method is for admin
     */
    public List<UserEntity> getAllUser(){
        return userRepo.findAll();
    }
    public UserEntity getUserByName(String username){
        return userRepo.findByUsername(username);
    }
    public String addAdmin(UserEntity user) {
        try {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setRoles(Arrays.asList("USER","ADMIN"));
            userRepo.save(user);
            return  "Save successful";
        } catch (Exception e) {
            //throw new RuntimeException(e);
            return null;
        }
    }


    public  static  final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    public String createUser(UserEntity user){

        try {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setRoles(Arrays.asList("USER"));
            userRepo.save(user);
            return  "Save successful";
        } catch (Exception e) {
            log.error("error on adding user {}",user.getUsername(),e.getMessage());
            return null;
        }

    }

    public String updateUser(UserEntity user){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();

        UserEntity oldUser = userRepo.findByUsername(username);

        oldUser.setUsername(user.getUsername());
        oldUser.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepo.save(oldUser);
        return  "Update successful";
    }

    public void deleteUser(){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        userRepo.deleteByUsername(username);
    }


    public String getGreetings() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        String greetings =response.getWeather("Kolkata");
        return "Hi"+username+"!"+greetings;
    }
}
