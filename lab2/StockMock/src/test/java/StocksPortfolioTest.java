import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class StocksPortfolioTest {

    @InjectMocks StocksPortfolio portfolio;

    @Mock
    IStockMarket stockMarket;

    @BeforeEach
    void setUp() {
        stockMarket = Mockito.mock(IStockMarket.class);
        portfolio.setMarketService(stockMarket);
    }

    @Test
    void testGetTotalValue() {
        portfolio.addStock(new Stock("a", 1));
        portfolio.addStock(new Stock("b", 5));
        portfolio.addStock(new Stock("c", 7));

        Mockito.when(stockMarket.getPrice("a")).thenReturn(3.4);
        Mockito.when(stockMarket.getPrice("b")).thenReturn(20.0);
        Mockito.when(stockMarket.getPrice("c")).thenReturn(100.0);

        double value = 3.4*1 + 20.0*5 + 100.0*7;

        //assertEquals(value, portfolio.getTotalValue());

        //b
        assertThat(portfolio.getTotalValue(), is(value));

        Mockito.verify(stockMarket, Mockito.times(3)).getPrice(Mockito.anyString());

    }
}
