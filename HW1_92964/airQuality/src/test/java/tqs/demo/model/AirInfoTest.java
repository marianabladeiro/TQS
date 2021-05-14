package tqs.demo.model;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class AirInfoTest {

    @Test
    void testSettersAndGetters() {
        AirInfo data = new AirInfo("1", "2", "54", "1", "5", "6.6", "0");
        data.setAqi("55");
        data.setPm10("2");
        data.setPm25("1");
        data.setCo("5");
        data.setSo2("5.8");
        data.setNo2("2.3");

        assertThat(data.getAqi()).isEqualTo("55");
        assertThat(data.getPm10()).isEqualTo("2");
        assertThat(data.getPm25()).isEqualTo("1");
        assertThat(data.getCo()).isEqualTo("5");
        assertThat(data.getSo2()).isEqualTo("5.8");
        assertThat(data.getNo2()).isEqualTo("2.3");

    }
}
