package tqs.demo.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import tqs.demo.cache.Cache;
import tqs.demo.model.AirInfo;
import tqs.demo.model.CityData;

import java.sql.Timestamp;
import java.util.Map;

@Service
public class AirQualityService {

    private static final String URL = "http://api.weatherbit.io/v2.0/current/airquality?city=";
    private static final String API_KEY = "08fe643cde43493983842c20450a4f33";

    Cache cache = new Cache();

    public void saveAirQuality(String city, CityData airQuality) {
        cache.saveCityAir(city, airQuality);
    }

    public AirInfo[] getAirQuality(String cityName) {
        cache.setRequests(); //call to cache
        if (!cache.isInCache(cityName.toLowerCase())) { //city is not in cache, lets call the external API
            RestTemplate restTemplate = new RestTemplate();
            String finalUrl = URL + cityName + "&key=" + API_KEY;
            CityData airQuality = restTemplate.getForObject(finalUrl, CityData.class);

            //prevent whitelables in case a city is not recognized by the API
            if (airQuality == null) {
                return new AirInfo[]{new AirInfo()};
            }
            //city is recognized
            else {
                cache.setMiss(); //once is not in cache
                airQuality.setTimestamp(new Timestamp(System.currentTimeMillis()).getTime());
                this.saveAirQuality(cityName, airQuality); //save the information to cache
            }

        }
        //city is in cache
        else {
            cache.setHit();
        }
        return cache.getAirQuality(cityName).getData();

    }

    public Map<String, String> getCacheStatistics() {
        return cache.getCacheStatistics();
    }
}
