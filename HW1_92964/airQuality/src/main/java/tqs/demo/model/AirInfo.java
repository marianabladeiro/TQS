package tqs.demo.model;

public class AirInfo {
    private String aqi;
    private String co;
    private String o3;
    private String so2;
    private String no2;
    private String pm10;
    private String pm25;

    public AirInfo() {
    }

    public AirInfo(String pm10, String pm25, String aqi, String co, String o3, String so2, String no2) {
        this.aqi = aqi;
        this.co = co;
        this.o3 = o3;
        this.so2 = so2;
        this.no2 = no2;
        this.pm25 = pm25;
        this.pm10 = pm10;
    }


    public String getAqi() {
        return aqi;
    }

    public void setAqi(String aqi) {
        this.aqi = aqi;
    }

    public String getCo() {
        return co;
    }

    public void setCo(String co) {
        this.co = co;
    }

    public String getO3() {
        return o3;
    }

    public void setO3(String o3) {
        this.o3 = o3;
    }

    public String getSo2() {
        return so2;
    }

    public void setSo2(String so2) {
        this.so2 = so2;
    }

    public String getNo2() {
        return no2;
    }

    public void setNo2(String no2) {
        this.no2 = no2;
    }

    public String getPm10() {
        return pm10;
    }

    public void setPm10(String pm10) {
        this.pm10 = pm10;
    }

    public String getPm25() {
        return pm25;
    }

    public void setPm25(String pm25) {
        this.pm25 = pm25;
    }

}


