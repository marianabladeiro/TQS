package com.example.P2_cars.car;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CarManagerService {
    @Autowired
    private CarRepository carRepository;

    public Car save(Car car) {
        return carRepository.save(car);
    }

    public List<Car> getAllCars() {
        return carRepository.findAll();

    }

    public Optional<Car> getCarDetails(Long id) {
        Car car = carRepository.findByCarId(id);
        if (car == null) {
            return null;
        }
        else {
            return Optional.of(car);
        }


    }
}
