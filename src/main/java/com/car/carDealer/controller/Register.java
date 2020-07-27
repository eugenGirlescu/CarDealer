package com.car.carDealer.controller;

import com.car.carDealer.model.User;
import com.car.carDealer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


import javax.validation.Valid;

@Controller
public class Register {

    @Autowired
    private UserService userService;

    public Register(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public ModelAndView showRegisterForm() {
        ModelAndView mv = new ModelAndView("register");
        mv.addObject("user", new User()); // this is required to display the form
        return mv;
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(
            @RequestParam("firstName") String firstName,
            @RequestParam("lastName") String lastName,
            @RequestParam("email") String email,
            @RequestParam("password") String password,
            @RequestParam("checkPassword") String checkPassword,
            @Valid User user, BindingResult result, Model model) {

        if (result.hasErrors()) {
            return "register";
        } else {
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setEmail(email);
            user.setPassword(password);
            user.setCheckPassword(checkPassword);
            User valid = userService.findByEmail(user.getEmail());

            if (valid == null) {
                userService.saveUser(user);
                String msg = "Userul a fost inregistrat cu succes!";
                model.addAttribute("msg", msg);
                return "register";
            } else {
                String msg = "Emailul a mai fost folosit !";
                model.addAttribute("msg", msg);
                return "register";
            }
        }

    }

}
