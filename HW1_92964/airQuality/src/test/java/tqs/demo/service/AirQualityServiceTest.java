package tqs.demo.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.verification.VerificationModeFactory;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import tqs.demo.cache.Cache;
import tqs.demo.model.AirInfo;
import tqs.demo.model.CityData;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.slf4j.LoggerFactory.getLogger;

@ExtendWith(MockitoExtension.class)
class AirQualityServiceTest {
    private final static Logger LOGGER = getLogger(AirQualityServiceTest.class.getName());

    @Mock(lenient = true)
    private Cache cache;

    @InjectMocks
    private AirQualityService airQualityService;

    HashMap<String, String> statistics = new HashMap<>();

    @BeforeEach
    void init() {
        LOGGER.info("--Running Tests--");
        List<String> allCities = Arrays.asList("viseu", "lisboa", "aveiro", "guarda");
        statistics.put("requests", "10");
        statistics.put("hits", "3");
        statistics.put("misses", "4");
        statistics.put("citiesSearched", allCities.toString());

        AirInfo[] airQuality = { new AirInfo("3", "2.25", "33", "286.639", "71", "0.630505", "2")};

        CityData cityData = new CityData(airQuality, new Timestamp(System.currentTimeMillis()).getTime());
        Map<String, CityData> cacheMap = new HashMap<>();
        cacheMap.put("viseu", cityData);

        Mockito.when(cache.getCacheStatistics()).thenReturn(statistics);
        Mockito.when(cache.getCache()).thenReturn(cacheMap);
        Mockito.when(cache.isInCache("viseu")).thenReturn(true);

    }

    @Test
    void whenGetCityInCache_andCityExists_thenReturnRightResults() {
        AirInfo[] airQuality = { new AirInfo("3", "2.25", "33", "286.639", "71", "0.630505", "2")};

        CityData cityData = new CityData(airQuality, new Timestamp(System.currentTimeMillis()).getTime());

        Mockito.when(cache.getAirQuality("Viseu")).thenReturn(cityData);

        AirInfo airInfo = airQualityService.getAirQuality("Viseu")[0];

        assertEquals("33", airInfo.getAqi());
        assertEquals("3", airInfo.getPm10());
        assertEquals("2.25", airInfo.getPm25());
        assertEquals("286.639", airInfo.getCo());
        assertEquals("71", airInfo.getO3());
        assertEquals("0.630505", airInfo.getSo2());
        assertEquals("2", airInfo.getNo2());

        Mockito.verify(cache, Mockito.times(1)).setRequests();
        Mockito.verify(cache, Mockito.times(1)).isInCache("viseu");
        Mockito.verify(cache, Mockito.times(1)).setHit();
        Mockito.verify(cache, Mockito.times(1)).getAirQuality("Viseu");
    }

    @Test
    void whenGetCity_andCityExists_thenReturnRightResults() {

        AirInfo[] airQuality = { new AirInfo("2.62888", "1.10281", "29", "366.33", "63.2107", "0.233063", "16.3224")};

        CityData cityData = new CityData(airQuality, new Timestamp(System.currentTimeMillis()).getTime());

        Mockito.when(cache.getAirQuality("Berlim")).thenReturn(cityData);

        AirInfo airInfo = airQualityService.getAirQuality("Berlim")[0];
        Map<String, CityData> cacheMap = new HashMap<>();
        cacheMap.put("berlim", cityData);

        assertEquals("29", airInfo.getAqi());
        assertEquals("2.62888", airInfo.getPm10());
        assertEquals("1.10281", airInfo.getPm25());
        assertEquals("366.33", airInfo.getCo());
        assertEquals("63.2107", airInfo.getO3());
        assertEquals("0.233063", airInfo.getSo2());
        assertEquals("16.3224", airInfo.getNo2());

        Mockito.verify(cache, Mockito.times(1)).setRequests();
        Mockito.verify(cache, Mockito.times(1)).isInCache("berlim");
        Mockito.verify(cache, Mockito.times(1)).setMiss();
        Mockito.verify(cache, Mockito.times(1)).getAirQuality("Berlim");

    }

    @Test
    void whenGetCity_andCityDoesNotExist_thenReturnNull() throws ParseException  {
        AirInfo[] returned = airQualityService.getAirQuality("doesntexist");
        AirInfo airInfo = airQualityService.getAirQuality("doesntexist")[0];

        Mockito.when(cache.getAirQuality("doesntexist")).thenReturn(null);

        assertThat(returned).hasSize(1);

        assertEquals(null, airInfo.getAqi());
        assertEquals(null, airInfo.getPm10());
        assertEquals(null, airInfo.getPm25());
        assertEquals(null, airInfo.getCo());
        assertEquals(null, airInfo.getO3());
        assertEquals(null, airInfo.getSo2());
        assertEquals(null, airInfo.getNo2());

        Mockito.verify(cache, Mockito.times(2)).setRequests();
        Mockito.verify(cache, Mockito.times(2)).isInCache("doesntexist");


    }

    @Test
    void givenCacheStatistics_whenGetCacheStatistics_thenReturnStatistics() {
        int requests = 10;
        int misses = 3;
        int hits = 4;
        List<String> cities = Arrays.asList("viseu", "lisboa", "aveiro", "guarda");
        airQualityService.getCacheStatistics();

        Mockito.when(cache.getCacheStatistics()).thenReturn(statistics);

        assertThat(statistics).hasSize(4)
                .containsKeys("requests", "hits", "misses", "citiesSearched")
                .containsValues(String.valueOf(requests), String.valueOf(hits), String.valueOf(misses), cities.toString());
        Mockito.verify(cache, Mockito.times(1)).getCacheStatistics();

    }



}
