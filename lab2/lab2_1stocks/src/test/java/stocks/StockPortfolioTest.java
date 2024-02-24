package stocks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * Unit test for simple App.
 */
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = org.mockito.quality.Strictness.LENIENT)
public class StockPortfolioTest {

    // 1. Prepare a mock to substitute the remote service (@Mock annotation)

    @Mock
    IStockmarketService market;

    // 2. Create an instance of the subject under test (SuT) and use the mock to set
    // the (remote) service instance.

    @InjectMocks
    StocksPortfolio portfolio;

    @Test
    void getTotalValueTest() {

        // 3. Load the mock with the proper expectations (when...thenReturn)
        when(market.lookUpPrice("EBAY")).thenReturn(4.0);
        when(market.lookUpPrice("MSFT")).thenReturn(1.5);

        // 4. Execute the test (use the service in the SuT)
        portfolio.addStock(new Stock("EBAY", 2));
        portfolio.addStock(new Stock("MSFT", 4));
        double result = portfolio.totalValue();

        // 5. Verify the result (assert) and the use of the mock (verify)
        assertEquals(14.0, result);

        
        
        assertThat(result, is(14.0));
        verify(market, times(2)).lookUpPrice(anyString());

    }

}
