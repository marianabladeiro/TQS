package tqs.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AirQualityController {
    @GetMapping(value="/cache")
    public String cache()
    {

        return "cache";

    }

}
