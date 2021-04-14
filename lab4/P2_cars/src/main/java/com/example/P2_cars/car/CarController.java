package com.example.P2_cars.car;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class CarController {
    @Autowired
    CarManagerService carManagerService;

    @PostMapping("/cars")
    public ResponseEntity<Car> createCar(@RequestBody Car car) {
        Car created = carManagerService.save(car);
        HttpStatus status = HttpStatus.CREATED;

        return new ResponseEntity<>(created, status);
    }

    @GetMapping("/cars")
    public List<Car> getAllCars() {
        return carManagerService.getAllCars();
    }

    @GetMapping("/cars/{id}")
    public ResponseEntity<Car> getCarById(@PathVariable Long id) {
        Optional<Car> car = carManagerService.getCarDetails(id);

        //because its only optional
        if (car.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.accepted().body(car.get());

    }
}
