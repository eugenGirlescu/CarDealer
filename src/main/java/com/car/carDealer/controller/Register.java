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
    private final UserService userService;

    public Register(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public ModelAndView showRegisterForm() {
        ModelAndView mv = new ModelAndView("user/register");
        mv.addObject("user", new User()); // this is required to display the form
        return mv;
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(
            @RequestParam("firstName") String firstName,
            @RequestParam("lastName") String lastName,
            @RequestParam("email") String email,
            @RequestParam("password") String password,
            @Valid User user, BindingResult result, Model model) {
            //here i save users in db if everything is ok
        if (result.hasErrors()) {
            return "user/register";
        } else {
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setEmail(email);
            user.setPassword(password);
            User valid = userService.findByEmail(user.getEmail());

            if (valid == null) {
                userService.saveUser(user);
                String msg = "Userul a fost inregistrat cu succes!";
                model.addAttribute("msg", msg);
                return "welcome";
            } else {
                String msg = "Emailul a mai fost folosit !";
                model.addAttribute("msg", msg);
                return "user/register";
            }
        }

    }

}
