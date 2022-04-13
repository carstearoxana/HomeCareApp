package com.sda.homeCare.controllers;

import com.sda.homeCare.entities.Role;
import com.sda.homeCare.entities.User;
import com.sda.homeCare.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import javax.validation.Valid;

@Controller
public class IndexController {


    @Autowired
    private UserRepository userRepository;

    @GetMapping("/")
    public ModelAndView getIndex(){
        ModelAndView modelAndView= new ModelAndView("index");
        return  modelAndView;
    }

    @GetMapping("/login")
    public ModelAndView login(){
        ModelAndView modelAndView= new ModelAndView("login");

        return modelAndView;
    }

    @Autowired
    PasswordEncoder passwordEncoder;
    @PostMapping("/register")
    public ModelAndView register(@ModelAttribute @Valid User user, BindingResult bindingResult){
        ModelAndView modelAndView= new ModelAndView("index");
        user.setEnabled(true);
        user.setRole(Role.CUSTOMER);
        user.setUserPassword(passwordEncoder.encode(user.getUserPassword()));
        userRepository.save(user);
        return modelAndView;
    }

    @GetMapping("/show-register")
    public ModelAndView showRegister(){
        ModelAndView modelAndView= new ModelAndView("register");
        return modelAndView;
    }
}
