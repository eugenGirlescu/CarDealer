package com.car.carDealer.controller;

import com.car.carDealer.model.User;
import com.car.carDealer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class Login {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView showLoginForm() {
        ModelAndView mv = new ModelAndView();
        mv.addObject("user", new User());
        return mv;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView login(@RequestParam("email") String email,
                              @RequestParam("password") String password,
                              User user,
                              BindingResult result,
                              Model model) {

        ModelAndView mod = new ModelAndView();

        if (result.hasErrors()) {
            mod.setViewName("login");
        }
        user.setEmail(email);
        user.setPassword(password);
        User valid = userService.findByEmailAndPassword(user.getEmail(), user.getPassword());

        if (valid != null) {
            mod.setViewName("welcome");
        } else {
            String msg = "Userul nu este inregistrat";
            model.addAttribute("msg", msg);
            mod.setViewName("login");
        }
        return mod;
    }
}

