package org.example.testing;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CurrencyAPITest {
    static Selenium selenium;
    @BeforeAll
    static void beforeAll() {
        selenium = new Selenium();
        selenium.open();
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


    @Test
    @DisplayName("positive input")
    void emptyInput() throws InterruptedException {
        selenium.enterAmount("");
        String result = selenium.submit();
        assertEquals("You must fill Amount of currancy !", result);
    }



}