package com.car.carDealer.repository;

import com.car.carDealer.model.Cars;
import org.springframework.data.repository.CrudRepository;

public interface CarRepository extends CrudRepository<Cars,Integer> {

}
