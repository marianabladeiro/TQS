package com.example.P2_cars;

import com.example.P2_cars.car.Car;
import com.example.P2_cars.car.CarRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class CarRepositoryTest {

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void whenFindById_thenReturnCar() {
        Car car1 = new Car("c1", "m1");
        entityManager.persistAndFlush(car1);

        Car found = carRepository.findByCarId(car1.getId());
        assertThat(found).isEqualTo(car1);
    }

    @Test
    public void whenInvalidId_thenReturnNull() {
        Car found = carRepository.findByCarId(100L);
        assertThat(found).isNull();
    }
}
