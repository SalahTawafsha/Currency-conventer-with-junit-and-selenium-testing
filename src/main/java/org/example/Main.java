package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\user\\Downloads\\chromedriver_win32\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://salah_projectqa.mohammadf.site/index.php");
        driver.manage().window().maximize();

        WebElement amount = driver.findElement(By.name("amo"));

        String input ="100";
        amount.sendKeys(input);
        WebElement submit = driver.findElement(By.name("submit"));
        submit.click();


    }
}