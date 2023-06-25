package org.example;

import org.example.testing.Selenium;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        Selenium s = new Selenium();
        System.out.println("test");
        s.open();
        s.enterAmount("15");
        System.out.println( "'"+ s.submit() +"'");
    }
}