package com.car.carDealer.service;

import com.car.carDealer.model.User;
import com.car.carDealer.repository.UserRepository;
import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

@Service
public class UserService {

    @Autowired
    private final UserRepository userRep;

    @Autowired
    BCryptPasswordEncoder bCryptEncoder;

    @Autowired
    public UserService(UserRepository userRep) {
        this.userRep = userRep;
    }

    public User findByEmailAndPassword(String email, String password) {
        return userRep.findByEmailAndPassword(email, password);
    }

    public User findByEmail(String email) {
        return userRep.findByEmail(email);
    }

    public void saveUser(User user) {
        userRep.save(user);
    }


}
