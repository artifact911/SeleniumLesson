package org.example;


import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class SecondTest {

    private WebDriver driver;

    @BeforeClass
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver");
        driver = new ChromeDriver();
        // driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5)); // Неявное ожидание. Стараться не юзать
    }

    @AfterClass
    public void finish() {
        driver.quit();
    }

    @Test
    public void openGoogle() {
        driver.get("https://www.google.com");
        WebElement searchField = driver.findElement(By.xpath("//input[@name='q']"));
        searchField.click();
        searchField.sendKeys("java");
        searchField.sendKeys(Keys.ENTER);

        WebElement result = waiter(By.xpath("//cite[contains(text(), 'https://www.java.com')]/../../h3[text()='Загрузка Java " +
                                                    "для всех операционных систем']"));

        Assert.assertTrue(result.isDisplayed());
    }

    private void getSleep() {
        try {
            Thread.sleep(5000);
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("You have exception");
        }
    }

    private WebElement waiter(By by) {
        return new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(by));

    }
}

