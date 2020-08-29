package com.car.carDealer.controller;

import com.car.carDealer.model.Cars;
import com.car.carDealer.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
public class Car {

    @Autowired
    private final CarRepository carRepository;

    public Car(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @GetMapping(value = "/cars/add")
    public String showAddForm(Model model) {
        model.addAttribute("cars", new Cars());
        return "cars/add-car";
    }

    @GetMapping(value = "/cars/list")
    public String showUpdatedList(Model model) {
        model.addAttribute("cars", carRepository.findAll());
        return "cars/car-list";
    }

    @PostMapping(value = "/cars/add")
    public String addCars(Cars cars, BindingResult result, Model model) {
        carRepository.save(cars);
        return "redirect:/cars/list";
    }

    @GetMapping(value = "/cars/edit/{id}")
    public String showUpdateForm(@PathVariable("id") int id, Model model) {
        Cars cars = carRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid car Id:" + id));

        model.addAttribute("cars", cars);
        return "cars/update-cars";

    }

    @PostMapping(value = "/cars/edit/{id}")
    public String updateCar(@PathVariable("id") int id, Model model, Cars cars, BindingResult result) {
        carRepository.save(cars);
        model.addAttribute("cars", carRepository.findAll());
        return "redirect:/cars/list";
    }

    @GetMapping(value = "/cars/delete/{id}")
    public String deleteCar(@PathVariable("id") int id, Model model) {
        Cars cars = carRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid car Id:" + id));
        carRepository.delete(cars);
        return "redirect:/cars/list";
    }

    @GetMapping(value = "/cars/view")
    public String showCarList(Model model) {
        List<Cars> allCars = carRepository.findAll();
        model.addAttribute("car", allCars);
        return "cars/view-list";
    }

    @GetMapping(value = "/cars/view/{id}")
    public String showCarDetails(@PathVariable("id") int id, Model model) {
        Cars cars = carRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid car Id:" + id));
        model.addAttribute("cars", cars);
        return "cars/view-details";
    }
}
