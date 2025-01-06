package net.engineeringdigest.journalApp.controller;

import lombok.extern.slf4j.Slf4j;
import net.engineeringdigest.journalApp.entity.UserEntity;
import net.engineeringdigest.journalApp.services.UserDetailsServiceImpl;
import net.engineeringdigest.journalApp.services.UserEntryServices;
import net.engineeringdigest.journalApp.utills.JwtUtill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/public")
public class PublicController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserEntryServices userService;
    @Autowired
    private UserDetailsServiceImpl userDetailsServiceImpl;
    @Autowired
    private JwtUtill jwtUtill;

    @GetMapping("/healthCheck")
    public String healthCheck() {return "OK";}

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody UserEntity user) {
        String msg = userService.createUser(user);
        if(msg == null) {return new ResponseEntity<>(HttpStatus.BAD_REQUEST);}
        return new ResponseEntity<>(msg, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserEntity user) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
            UserDetails userDetails = userDetailsServiceImpl.loadUserByUsername(user.getUsername());
            String s = jwtUtill.generateToken(userDetails.getUsername());
            return new ResponseEntity<>(s, HttpStatus.OK);

        } catch (AuthenticationException e) {
            log.error("Authentication error", e);
            return new ResponseEntity<>("Incorrect username or password", HttpStatus.UNAUTHORIZED);

        }

    }

}
