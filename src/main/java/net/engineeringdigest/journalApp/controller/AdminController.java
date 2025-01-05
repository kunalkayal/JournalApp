package net.engineeringdigest.journalApp.controller;

import net.engineeringdigest.journalApp.entity.UserEntity;
import net.engineeringdigest.journalApp.services.UserEntryServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private final UserEntryServices userEntryServices;

    public AdminController(UserEntryServices userEntryServices) {
        this.userEntryServices = userEntryServices;
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllUsers() {
        List<UserEntity> userList= userEntryServices.getAllUser();
        if(userList.isEmpty()) {return  new ResponseEntity<>(HttpStatus.NO_CONTENT);}
        return new ResponseEntity<>(userList, HttpStatus.OK);
    }

    @GetMapping("/{username}")
    public ResponseEntity<?> getUser(@PathVariable String username) {
        UserEntity user = userEntryServices.getUserByName(username);
        if(user == null) {return  new ResponseEntity<>(HttpStatus.NOT_FOUND);}
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> addAdmin(@RequestBody UserEntity user) {
        String msg =userEntryServices.addAdmin(user);
        if(msg == null) {return  new ResponseEntity<>(HttpStatus.CONFLICT);}
        return new ResponseEntity<>(msg,HttpStatus.CREATED);
    }
}
