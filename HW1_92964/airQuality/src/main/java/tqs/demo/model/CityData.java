package tqs.demo.model;

public class CityData {
    private AirInfo[] data;
    private long timestamp;
    private String cityName;

    public CityData() {}

    public CityData(AirInfo[] data, long timestamp, String cityName) {
        this.data = data;
        this.timestamp = timestamp;
        this.cityName = cityName;
    }

    public CityData(AirInfo[] data, long timestamp) {
        this.data = data;
        this.timestamp = timestamp;

    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public AirInfo[] getData() {
        return data;
    }

    public void setData(AirInfo[] data) {
        this.data = data;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
