package com.car.carDealer.repository;

import com.car.carDealer.model.Cars;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CarRepository extends CrudRepository<Cars,Integer> {
    @Override
     public List<Cars> findAll();
}
