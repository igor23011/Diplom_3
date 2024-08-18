import io.qameta.allure.junit4.DisplayName;
import org.example.Browser;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.example.pageObject.MainPageLocators.*;
import static org.junit.Assert.assertEquals;

public class WayToConstructorTest {
    private WebDriver driver;

    @Before
    public void setUp() {
        Browser browser = new Browser();
        //Для запуска тестов в Яндекс.Браузере написать browserName = "yandex"
        //Для запуска тестов в Google Chrome написать browserName = "chrome"
        driver = browser.getWebDriver("chrome");
        driver.get("https://stellarburgers.nomoreparties.site/");
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(buttonMakeOrder));

    }

    @Test
    @DisplayName("Переход к разделу Булки")
    public void checkSuccessBunInConstructorTest() {
        driver.findElement(sauces).click();
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.elementToBeClickable(bun));
        driver.findElement(bun).click();
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(bunInConstructor));
        String actual = driver.findElement(activeConstructorElement).getText();
        String expected = "Булки";
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Переход к разделу Соусы")
    public void checkSuccessSaucesInConstructorTest() {
        driver.findElement(sauces).click();
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(saucesInConstructor));
        String actual = driver.findElement(activeConstructorElement).getText();
        String expected = "Соусы";
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Переход к разделу Начинки")
    public void checkFillingsInConstructorTest() {
        driver.findElement(filling).click();
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(fillingInConstructor));
        String actual = driver.findElement(activeConstructorElement).getText();
        String expected = "Начинки";
        assertEquals(expected, actual);
    }

    @After
    public void teardown() {
        driver.quit();
    }
}