package com.example.P2_cars.car;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long carId;

    private String name, maker;

    public Car() {}


    public Car(String name, String maker) {
        this.name = name;
        this.maker = maker;
    }

    public Long getId() {
        return carId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return Objects.equals(carId, car.carId) &&
                Objects.equals(name, car.name) &&
                Objects.equals(maker, car.maker);
    }

    @Override
    public int hashCode() {
        return Objects.hash(carId, name, maker);
    }

    public void setCarId(Long id) {
        this.carId = id;
    }

    public Long getCarId() {
        return carId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMaker() {
        return maker;
    }

    public void setMaker(String maker) {
        this.maker = maker;
    }

    @Override
    public String toString() {
        return "Car{" +
                "carId=" + carId +
                ", name='" + name + '\'' +
                ", maker='" + maker + '\'' +
                '}';
    }
}
