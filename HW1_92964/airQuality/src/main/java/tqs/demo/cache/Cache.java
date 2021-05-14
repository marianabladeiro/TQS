package tqs.demo.cache;

import tqs.demo.model.CityData;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Cache {
    private Map<String, CityData> cacheMap = new HashMap<>();
    private int requests = 0;
    private int misses = 0;
    private int hits = 0;

    public Cache() {
        // Do nothing because of tests.
    }

    public Cache(int requests, int misses, int hits) {
        this.requests = requests;
        this.misses = misses;
        this.hits = hits;
    }

    //checks if city is in cache, meaning hasn't expired yet
    public boolean isInCache(String city) {
        long currentTime = new Timestamp(System.currentTimeMillis()).getTime();
        return (cacheMap.containsKey(city) && currentTime - cacheMap.get(city).getTimestamp() < 60000);
    }

    public void setRequests() {
        requests++;
    }

    public void setMiss() {
        misses++;
    }

    public void setHit() {
        hits++;
    }

    public int getRequests() {
        return requests;
    }

    public int getMisses() {
        return misses;
    }

    public int getHits() {
        return hits;
    }

    public void saveCityAir(String city, CityData cityData) {
        cacheMap.put(city.toLowerCase(), cityData);
    }

    public CityData getAirQuality(String city) {
        return cacheMap.get(city.toLowerCase());
    }

    public Map<String, String> getCacheStatistics() {
        Map<String, String> cacheStatistics = new HashMap<>();
        cacheStatistics.put("requests", String.valueOf(requests));
        cacheStatistics.put("misses", String.valueOf(misses));
        cacheStatistics.put("hits", String.valueOf(hits));
        for (Iterator<String> city = cacheMap.keySet().iterator(); city.hasNext();) {
            String s = city.next();
            if (!isInCache(s)){
                city.remove();
            }
        }
        setRequests();

        cacheStatistics.put("citiesSearched", cacheMap.keySet().toString());

        return cacheStatistics;
    }


    public Map<String, CityData> getCache() {
        return cacheMap;
    }




}
