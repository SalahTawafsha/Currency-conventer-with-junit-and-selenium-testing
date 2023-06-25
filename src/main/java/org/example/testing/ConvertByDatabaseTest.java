package org.example.testing;

import org.example.MainController;
import org.example.convert.Convert;
import org.example.convert.FromDatabase;
import org.example.model.Currency;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


class ConvertByDatabaseTest {

    private static Convert convert;


    @BeforeAll
    static void setUp() {
        convert = new FromDatabase();
    }

    @Test
    @DisplayName("Null Input")
    void nullInputTest() {
        Currency from = new Currency("United States dollar", "USD");
        Currency to = new Currency("Israeli New Shekel", "ILS");
        Double amount = 100.0;
        assertThrows(NullPointerException.class, () -> convert.convert(null, to, amount));
        assertThrows(NullPointerException.class, () -> convert.convert(from, null, amount));
        assertThrows(NullPointerException.class, () -> convert.convert(from, to, null));
    }

    @Test
    @DisplayName("Negative Input")
    void negativeInputTest() {
        Currency from = new Currency("United States dollar", "USD");
        Currency to = new Currency("Israeli New Shekel", "ILS");
        Double amount = -100.0;
        assertThrows(IllegalArgumentException.class, () -> convert.convert(from, to, amount));
    }

    @Test
    @DisplayName("Not in list Input")
    void notInListInputTest() {
        Currency from = new Currency("United States dollar", "USD");
        Currency to = new Currency("Israeli New Shekel", "ILS");
        Currency notInList = new Currency("Not in list", "NIL");
        Double amount = 100.0;
        assertThrows(IllegalArgumentException.class, () -> convert.convert(notInList, to, amount));
        assertThrows(IllegalArgumentException.class, () -> convert.convert(from, notInList, amount));
    }

    @Test
    @DisplayName("Round Test")
    void roundTest() {
        Currency from = new Currency("United States dollar", "USD");
        Currency to = new Currency("Israeli New Shekel", "ILS");
        Double amount = 100.0;

        try {
            Convert convert = mock(Convert.class);
            when(convert.convert(from, to, amount)).thenReturn(new Double[]{3.54445, 350.15559});

            Double[] result = convert.convert(from, to, amount);

            MainController.round(result);

            assertEquals(result[0], 3.54);
            assertEquals(result[1], 350.16);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }


}