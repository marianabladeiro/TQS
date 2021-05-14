package tqs.demo.cache;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.springframework.boot.test.context.SpringBootTest;
import tqs.demo.model.AirInfo;
import tqs.demo.model.CityData;

import java.sql.Timestamp;
import java.util.*;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;
import static org.slf4j.LoggerFactory.getLogger;


@SpringBootTest
class CacheTest {
    private Cache cache = new Cache();
    private Map<String, CityData> cacheMap = new HashMap<>();

    private final static Logger LOGGER = getLogger(CacheTest.class.getName());

    @BeforeEach
    private void init() {
        LOGGER.info("--Running Tests--");
        cache = new Cache();
        assertThat(cacheMap.size()).isZero();
        assertThat(cache.getRequests()).isZero();
        assertThat(cache.getHits()).isZero();
        assertThat(cache.getMisses()).isZero();

    }

    @Test
    void testGetCityAirQuality() {
        AirInfo[] airInfo = { new AirInfo("2.62888", "1.10281", "29", "366.33", "63.2107", "0.233063", "16.3224")};
        CityData cityData = new CityData(airInfo, new Timestamp(System.currentTimeMillis()).getTime());

        cache.saveCityAir("Berlim", cityData);

        CityData dataReturned = cache.getAirQuality("Berlim");

        assertThat(dataReturned.getData().toString()).hasToString(airInfo.toString());
        assertThat(dataReturned.toString()).hasToString(cityData.toString());

    }

    @Test
    void testSaveCityAir() {
        CityData cityData = new CityData();
        cache.saveCityAir("Berlim", cityData);

        Map<String, CityData> cacheData = cache.getCache();
        assertThat(cacheData).containsKey("berlim").containsValue(cityData); //since it puts in map in lower case
    }

    @Test
    void testGetCacheStatistics() {
        int requests = 2;
        int misses = 1;
        int hits = 1;

        List<String> citiesList = new ArrayList<>(Arrays.asList("guarda"));

        CityData cityData = new CityData();
        cityData.setTimestamp(new Timestamp(System.currentTimeMillis()).getTime());

        cache.setRequests();
        cache.setRequests();
        cache.setMiss();
        cache.setHit();

        cache.saveCityAir("guarda", cityData);
        Map<String, String> found = cache.getCacheStatistics();

        HashMap<String, String> expected = new HashMap<>();

        expected.put("requests", String.valueOf(requests));
        expected.put("misses", String.valueOf(misses));
        expected.put("hits", String.valueOf(hits));
        expected.put("citiesSearched", citiesList.toString());
        assertThat(expected).isEqualTo(found);

    }


    @Test
    void testIsExpiredCityInCache() throws InterruptedException {

        CityData cityData = new CityData();
        cityData.setTimestamp(new Timestamp(System.currentTimeMillis()).getTime());

        cache.saveCityAir("viseu", cityData);

        TimeUnit.SECONDS.sleep(60);

        assertThat(cache.getCache()).containsKey("viseu");
        assertThat(cache.isInCache("viseu")).isFalse();
    }


    @Test
    void testIsValidCityInCache() {

        CityData cityData = new CityData();
        cityData.setTimestamp(new Timestamp(System.currentTimeMillis()).getTime());

        cache.saveCityAir("viseu", cityData);

        assertThat(cache.getCache()).containsKey("viseu");
        assertThat(cache.isInCache("viseu")).isTrue();
    }

}
