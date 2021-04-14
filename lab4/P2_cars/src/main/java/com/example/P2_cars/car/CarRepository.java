package com.example.P2_cars.car;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
    public Car findByCarId(Long id);
    public List<Car> findAll();
}
