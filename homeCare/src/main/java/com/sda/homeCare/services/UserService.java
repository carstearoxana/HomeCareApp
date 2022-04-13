package com.sda.homeCare.services;

import com.sda.homeCare.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserService implements UserDetailsService {

    public String getLogInUser(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user=(User)auth.getPrincipal();
        return user.getUsername();
    }

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException{
       com.sda.homeCare.entities.User user = userRepository.findByUserEmail(email);
        if(user ==null){
            throw new UsernameNotFoundException("invalid email");
        }
        return new org.springframework.security.core.userdetails.User(email, user.getUserPassword(), Collections.singletonList(new SimpleGrantedAuthority(user.getRole().name())));
    }
}
