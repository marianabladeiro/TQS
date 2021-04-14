package com.example.P2_cars;

import com.example.P2_cars.car.Car;
import com.example.P2_cars.car.CarManagerService;
import com.example.P2_cars.car.CarRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.verification.VerificationModeFactory;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.BDDMockito.given;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class CarServiceTest {

    @Mock(lenient = true)
    private CarRepository carRepository;

    @InjectMocks
    private CarManagerService carManagerService;

    Car car1;
    Car car2;

    @BeforeEach
    public void setUp() {
        car1 = new Car("car1", "m1");
        car1.setCarId(1L);

        car2 = new Car("car2", "m2");
        car2.setCarId(2L);

        List<Car> cars = Arrays.asList(car1, car2);
        when(carRepository.findByCarId(car1.getCarId())).thenReturn(car1);
        when(carRepository.findByCarId(5L)).thenReturn(null);
        when(carRepository.findAll()).thenReturn(cars);
    }


    @Test
    public void getCarDetails_returnsCarInfo() {
        Optional<Car> c = carManagerService.getCarDetails(1L);
        assertThat(c.get().getCarId()).isEqualTo(1L);
        verify(carRepository, VerificationModeFactory.times(1)).findByCarId(1L);

    }

    @Test
    public void getCarDetails_whenDoesntExist_returnsNothing() {
       Optional<Car> c = carManagerService.getCarDetails(5L);
       verify(carRepository, VerificationModeFactory.times(1)).findByCarId(5L);
       assertThat(c).isNull();

     }

     @Test
    public void givenArrayCars_whenGetAll_thenReturnArray() {
         List<Car> cars = carManagerService.getAllCars();
         verify(carRepository, VerificationModeFactory.times(1)).findAll();
         assertThat(cars).hasSize(2).extracting(Car::getMaker).contains(car1.getMaker(), car2.getMaker());
     }



}
