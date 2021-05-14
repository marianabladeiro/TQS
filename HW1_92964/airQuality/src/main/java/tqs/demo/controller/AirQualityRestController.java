package tqs.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tqs.demo.model.AirInfo;
import tqs.demo.service.AirQualityService;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class AirQualityRestController {

    @Autowired
    private AirQualityService airQualityService;

    @GetMapping("/location/{city}")
    public AirInfo[] getCityAirQuality(@PathVariable(value = "city") String city){
        return airQualityService.getAirQuality(city);
    }

    @GetMapping(value="/cache")
    public Map<String, String> cache()
    {

        return airQualityService.getCacheStatistics();

    }

}
