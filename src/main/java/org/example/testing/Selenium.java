package org.example.testing;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;


public class Selenium {
    private WebDriver driver;

    public void open() {
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://salah_projectqa.mohammadf.site/index.php");
        driver.manage().window().maximize();

    }

    public void enterAmount(String amount) {
        WebElement amountWebElement = driver.findElement(By.name("amo"));
        amountWebElement.clear();
        amountWebElement.sendKeys(amount);
    }
    public String submit() throws InterruptedException {
        WebElement submit = driver.findElement(By.name("submit"));
        submit.click();
        Thread.sleep(1000);
        return driver.findElement(By.name("result ")).getAttribute("value");
    }
}
