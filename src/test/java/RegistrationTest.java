import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import org.example.Browser;
import org.example.RegisterPageController;
import org.example.UserControllerStep;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.example.pageObject.AuthPageLocators.buttonSignIn;
import static org.example.pageObject.MainPageLocators.buttonMakeOrder;
import static org.example.pageObject.RegistrationPageLocators.getTextPassword;
import static org.junit.Assert.assertEquals;

public class RegistrationTest {
    private WebDriver driver;

    @Before
    public void setUp() {
        Browser browser = new Browser();
        //Для запуска тестов в Яндекс.Браузере написать browserName = "yandex"
        //Для запуска тестов в Google Chrome написать browserName = "chrome"
        driver = browser.getWebDriver("chrome");
        driver.get("https://stellarburgers.nomoreparties.site/");
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(buttonMakeOrder));
        RestAssured.baseURI = "https://stellarburgers.nomoreparties.site/";

    }

    @Test
    @DisplayName("Успешная регистрация пользователя")
    public void checkSuccessRegistrationTest() {
        RegisterPageController registerPage = new RegisterPageController(driver);
        String name = "Ivan";
        String email = "ivan_molot007@yandex.ru";
        String password = "!12345qwer";
        registerPage.register(name, email, password);
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(buttonSignIn));
        String actualResult = driver.findElement(buttonSignIn).getText();
        String expectedResult = "Войти";
        assertEquals(expectedResult, actualResult);
        UserControllerStep.apiDeleteUser(email, password);
    }

    @Test
    @DisplayName("Некорректный пароль")
    public void checkFailRegistrationTest() {
        RegisterPageController objRegisterPage = new RegisterPageController(driver);
        objRegisterPage.register("Ivan", "ivan_molot001@yandex.ru", "123");
        String actualResult = driver.findElement(getTextPassword).getText();
        String expectedResult = "Некорректный пароль";
        assertEquals(expectedResult, actualResult);
    }

    @After
    public void teardown() {
        driver.quit();
    }
}