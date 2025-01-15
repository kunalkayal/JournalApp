package me.kunalkayal.journalApp.services;

import me.kunalkayal.journalApp.entity.UserEntity;
import me.kunalkayal.journalApp.repository.userRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    userRepo uRepo;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = uRepo.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return User.withUsername(username)
                .password(user.getPassword())
                .roles(user.getRoles().toArray((new String[0]))).build();
    }
}
