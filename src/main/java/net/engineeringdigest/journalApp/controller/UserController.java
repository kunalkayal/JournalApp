package net.engineeringdigest.journalApp.controller;

import net.engineeringdigest.journalApp.entity.UserEntity;
import net.engineeringdigest.journalApp.services.UserEntryServices;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserEntryServices userService;

//    @GetMapping("/id/{id}")
//    public ResponseEntity<?> getUserById(@PathVariable ObjectId id) {
//        UserEntity user = userService.getUserByid(id);
//        if(user == null) {return new ResponseEntity<>(HttpStatus.NOT_FOUND);}
//        return new ResponseEntity<>(user, HttpStatus.OK);
//    }



    @PutMapping
    public ResponseEntity<?> update(@RequestBody UserEntity user){
        String msg = userService.updateUser(user);
        return new ResponseEntity<>(msg, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<?> delete(){
        userService.deleteUser();
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> greetings(){
        return  new ResponseEntity<>(userService.getGreetings(),HttpStatus.OK);
    }
}
