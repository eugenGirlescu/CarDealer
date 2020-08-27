package com.car.carDealer.controller;

import com.car.carDealer.model.User;
import com.car.carDealer.service.UserService;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Controller
public class Login {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView showLoginForm() {
        ModelAndView mv = new ModelAndView("user/login");
        mv.addObject("user", new User());
        return mv;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView login(
            @Valid User user,
            BindingResult result,
            Model model) {

        ModelAndView mod = new ModelAndView();

        if (result.hasErrors()) {
            mod.setViewName("user/login");
        }

        User valid = userService.findByEmailAndPassword(user.getEmail(), user.getPassword());

        if (valid != null) {
            mod.setViewName("hello");
        } else {
            String msg = "Verificati emailul sau parola !";
            model.addAttribute("msg", msg);
            mod.setViewName("user/login");
        }
        return mod;
    }
}


