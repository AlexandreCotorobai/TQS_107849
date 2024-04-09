package tqs.hw1.bustickets.UnitTests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import tqs.hw1.bustickets.entities.Currency;

class CurrencyTest {
    @Test
    void testCurrencyGettersAndSetters() {
        Currency currency = new Currency();
        currency.setId("USD");
        currency.setEurRate(1.0);

        assertEquals("USD", currency.getId());
        assertEquals(1.0, currency.getEurRate());
    }
}
