package com.car.carDealer.controller;

import com.car.carDealer.model.User;
import com.car.carDealer.repository.UserRepository;
import com.car.carDealer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


import javax.validation.Valid;

@Controller
public class Register {

    @Autowired
    BCryptPasswordEncoder bCryptEncoder;

    @Autowired
    UserService userService;
    @Autowired
    UserRepository userRepo;

    public Register(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "user/register";

    }

    @PostMapping("/register")
    public String register(@Valid User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "user/register";
        } else {

            User valid = userService.findByEmail(user.getEmail());
            if (valid == null) {
                user.setPassword(bCryptEncoder.encode(user.getPassword()));
                userService.saveUser(user);
                String msg = "Userul a fost inregistrat cu succes!";
                model.addAttribute("msg", msg);
                return "user/register";
            } else {
                String msg = "Emailul a mai fost inregistrat! Va rugam folositi alt email !";
                model.addAttribute("msg", msg);
                return "user/register";
            }
        }
    }
}
