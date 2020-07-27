package com.car.carDealer.repository;

import com.car.carDealer.model.User;
import org.springframework.data.repository.CrudRepository;


public interface UserRepository extends CrudRepository<User,Integer> {

        User findByEmail(String email);
        User findByEmailAndPassword(String email,String password);




}
