package net.engineeringdigest.journalApp.controller;

import net.engineeringdigest.journalApp.entity.UserEntity;
import net.engineeringdigest.journalApp.services.UserEntryServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
public class PublicController {

    @Autowired
    UserEntryServices userService;

    @GetMapping("/healthCheck")
    public String healthCheck() {return "OK";}

    @PostMapping("/addUser")
    public ResponseEntity<?> addUser(@RequestBody UserEntity user) {
        String msg = userService.createUser(user);
        if(msg == null) {return new ResponseEntity<>(HttpStatus.BAD_REQUEST);}
        return new ResponseEntity<>(msg, HttpStatus.CREATED);
    }
}
