package tqs.demo.model;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

import java.sql.Timestamp;

class CityDataTest {

    @Test
    void testSettersAndGetters() {
        Long timestamp = new Timestamp(System.currentTimeMillis()).getTime();
        AirInfo data = new AirInfo("1", "2", "54", "1", "5", "6.6", "0");
        AirInfo[] dataAir = {data};

        CityData city = new CityData(dataAir,timestamp ,"Aveiro");

        //setName
        String newCityName = "Faro";
        city.setCityName(newCityName);

        //setTimestamp
        Long newTimestamp = 150000L;
        city.setTimestamp(newTimestamp);

        //setData
        AirInfo new_Data = new AirInfo("1", "2", "0", "0", "0", "0", "0");
        AirInfo[] newData = {new_Data};
        city.setData(newData);

        assertThat(city.getCityName()).isEqualTo(newCityName);
        assertThat(city.getTimestamp()).isEqualTo(newTimestamp);
        assertThat(city.getData()).isEqualTo(newData);

    }

}
