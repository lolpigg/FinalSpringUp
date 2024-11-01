package com.practica.finalup.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class BaseController {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserDetailsService userDetailsService;
    @GetMapping("/")
    public String getHome(){
        return "home";
    }
    @PostMapping("/register")
    @ResponseBody
    public String register(@RequestParam String username, @RequestParam String password) {
        UserDetails newUser = User.withUsername(username)
                .password(passwordEncoder.encode(password))
                .roles("USER")
                .build();

        ((InMemoryUserDetailsManager) userDetailsService).createUser(newUser);

        return "Регистрация успешна!<br> <a href='/'>Назад на главную</a>";
    }
}
