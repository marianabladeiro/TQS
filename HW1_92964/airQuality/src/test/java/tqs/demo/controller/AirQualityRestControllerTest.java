package tqs.demo.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import tqs.demo.model.AirInfo;
import tqs.demo.service.AirQualityService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.hamcrest.collection.IsMapContaining.hasKey;

@WebMvcTest(AirQualityRestController.class)
class AirQualityRestControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private AirQualityService airQualityService;

    @Test
    void whenGetCityAirQuality_thenReturnCityAirQualityInfo() throws Exception{

        AirInfo[] airInfo = { new AirInfo("2.62888", "1.10281", "29", "366.33", "63.2107", "0.233063", "16.3224")};

        given(airQualityService.getAirQuality("Berlim")).willReturn(airInfo);

        mvc.perform(get("/api/location/".concat("Berlim"))).andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0]", hasKey("aqi")))
                .andExpect(jsonPath("$[0]", hasKey("o3")))
                .andExpect(jsonPath("$[0]", hasKey("co")))
                .andExpect(jsonPath("$[0]", hasKey("so2")))
                .andExpect(jsonPath("$[0]", hasKey("no2")));

    }

    @Test
    void whenGetInvalidCityAirQuality_thenReturnNull() throws Exception {
        String city = "doesntexist";

        given(airQualityService.getAirQuality(city)).willReturn(null);

        mvc.perform(get("/api/location/".concat(city))).andExpect(status().isOk());

    }

    @Test
    void whenGetCacheStatistics_thenReturnStatisticsInJson() throws Exception {

        List<String> citiesList = new ArrayList<>(Arrays.asList("Aveiro", "Viseu", "Guarda", "Lisboa"));

        HashMap<String, String> cacheStatistics = new HashMap<>();
        cacheStatistics.put("requests", "15");
        cacheStatistics.put("hits", "3");
        cacheStatistics.put("misses", "5");
        cacheStatistics.put("citiesSearched", citiesList.toString());

        given(airQualityService.getCacheStatistics()).willReturn(cacheStatistics);
        mvc.perform(get("/api/cache")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.requests", is(cacheStatistics.get("requests"))))
                .andExpect(jsonPath("$.hits", is(cacheStatistics.get("hits"))))
                .andExpect(jsonPath("$.misses", is(cacheStatistics.get("misses"))))
                .andExpect(jsonPath("$.citiesSearched", is(cacheStatistics.get("citiesSearched"))));
    }

    @Test
    void givenNoCitiesInCache_whenGetCacheStatistics_thenReturnStatisticsInJson() throws Exception {
        List<String> citiesList = new ArrayList<>();

        HashMap<String, String> cacheStatistics = new HashMap<>();
        cacheStatistics.put("requests", "1");
        cacheStatistics.put("hits", "0");
        cacheStatistics.put("misses", "0");
        cacheStatistics.put("citiesSearched", citiesList.toString());

        given(airQualityService.getCacheStatistics()).willReturn(cacheStatistics);
        mvc.perform(get("/api/cache")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.requests", is(cacheStatistics.get("requests"))))
                .andExpect(jsonPath("$.hits", is(cacheStatistics.get("hits"))))
                .andExpect(jsonPath("$.misses", is(cacheStatistics.get("misses"))))
                .andExpect(jsonPath("$.citiesSearched", is(cacheStatistics.get("citiesSearched"))));
    }

}
