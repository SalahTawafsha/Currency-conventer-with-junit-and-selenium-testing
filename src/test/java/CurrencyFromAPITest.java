
import org.example.MainController;
import org.example.convert.Convert;
import org.example.convert.FromAPI;
import org.example.helper.Selenium;
import org.example.model.Currency;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class CurrencyFromAPITest {
    static Selenium selenium;
    @BeforeAll
    static void beforeAll() {
        selenium = new Selenium();

    }
    @Test
    @DisplayName("zero input")
    void zeroInput() throws InterruptedException {
        selenium.enterAmount("0");
        String result = selenium.submit();
        assertEquals("You must fill Amount of currancy !", result);
    }

    @Test
    @DisplayName("negative input")
    void negativeInput() throws InterruptedException {
        selenium.enterAmount("-10");
        String result = selenium.submit();
        assertEquals("Amount of currancy can't be negative !!", result);
    }



    @Nested
    class TestGUI {

        Convert convert = new FromAPI();
        @ParameterizedTest
        @CsvSource({
                "AED - United Arab Emirates Dirham, ILS - Israeli New Shekel, 15.0",
                "BRL - Brazilian Real, PLN - Polish Złoty, 19.0",
                "JOD - Jordanian Dinar, INR - Indian Rupee, 90.0",
                "EUR - Euro, TWD - Taiwan Dollar, 1.0",
                "SAR - Saudi Riyal, AUD - Australian Dollar, 1000.0",
                "MXN - Mexican Peso, PHP - Philippine Peso, 1000000000.0",
        })
        void successTest(String from, String to, Double amount) throws Exception {

            String[] fromData = from.split(" - ");
            String[] toData = to.split(" - ");
            Double[] result = convert.convert(new Currency(fromData[1], fromData[0]), new Currency(toData[1], toData[0]), amount);
            MainController.round(result);
            selenium.enterAmount(String.valueOf(amount));
            selenium.selectFrom(fromData[0]);
            selenium.selectTo(toData[0]);
            String seleniumResult = selenium.submit();
            String seleniumRate = selenium.getRate();

            Double[] seleniumResults = {Double.parseDouble(seleniumResult), Double.parseDouble(seleniumRate)};
            MainController.round(seleniumResults);

            assertAll(() ->
                assertEquals(result[Convert.RESULT], seleniumResults[0])
            , () ->
                assertEquals(result[Convert.RATE], seleniumResults[1])
            );
        }
    }


    @Test
    @DisplayName("positive input")
    void emptyInput() throws InterruptedException {
        selenium.enterAmount("");
        String result = selenium.submit();
        assertEquals("You must fill Amount of currancy !", result);
    }

}