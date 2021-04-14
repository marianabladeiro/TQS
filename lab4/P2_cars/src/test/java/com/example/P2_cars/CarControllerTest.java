package com.example.P2_cars;

import com.example.P2_cars.car.Car;
import com.example.P2_cars.car.CarController;
import com.example.P2_cars.car.CarManagerService;
import com.example.P2_cars.car.JsonUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.internal.verification.VerificationModeFactory;
import org.mockito.verification.VerificationMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.awt.*;
import java.util.Arrays;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import net.minidev.json.JSONUtil;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.*;
import org.mockito.Mockito;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import java.util.Arrays;
import java.util.List;

@WebMvcTest(CarController.class)
public class CarControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CarManagerService carManagerService;

    @BeforeEach
    public void setUp() {

    }

    @Test
    public void whenPost_thenCreateCar() throws Exception {
        Car car = new Car("Evoque", "RangeRover");

        when(carManagerService.save(Mockito.any())).thenReturn(car);

        mockMvc.perform(
            post("/api/cars").contentType(MediaType.APPLICATION_JSON).content(JsonUtil.toJson(car))
        ).andExpect(status().isCreated())
        .andExpect(jsonPath("$.maker", is(car.getMaker())));


        verify(carManagerService, times(1)).save(Mockito.any());

    }

    @Test
    public void givenCars_whenGetCars_thenReturnArray() throws Exception {
        Car car1 = new Car("c1", "m1");
        Car car2 = new Car("c2", "m2");
        Car car3 = new Car("c3", "m3");
        Car car4 = new Car("c4", "m4");

        List<Car> cars = Arrays.asList(car1, car2, car3, car4);
        given(carManagerService.getAllCars()).willReturn(cars);

        mockMvc.perform(get("/api/cars").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(4))).andExpect(jsonPath("$[0].maker", is(car1.getMaker())))
                .andExpect(jsonPath("$[1].maker", is(car2.getMaker()))).andExpect(jsonPath("$[2].maker", is(car3.getMaker())))
                .andExpect(jsonPath("$[3].maker", is(car4.getMaker())));

        verify(carManagerService, VerificationModeFactory.times(1)).getAllCars();
    }
}
