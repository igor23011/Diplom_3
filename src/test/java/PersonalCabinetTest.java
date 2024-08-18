import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import org.example.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.example.pageObject.AuthPageLocators.buttonSignIn;
import static org.example.pageObject.MainPageLocators.*;
import static org.example.pageObject.PersonalCabinetPageLocators.buttonExit;
import static org.example.pageObject.PersonalCabinetPageLocators.buttonSave;
import static org.junit.Assert.assertEquals;

public class PersonalCabinetTest {
    private WebDriver driver;
    private static CreateUser createUser;
    private static String token;

    @Before
    public void setUp() {
        RestAssured.baseURI = "https://stellarburgers.nomoreparties.site/";
        createUser = new CreateUser("Ivan Bail", "qwer12345", "ivan_molot008@yandex.ru");
        UserControllerStep.apiExecuteCreate(createUser);
        token = UserControllerStep.getUserToken(new LoginUser(createUser.getEmail(), createUser.getPassword()));

        Browser browser = new Browser();
        //Для запуска тестов в Яндекс.Браузере написать browserName = "yandex"
        //Для запуска тестов в Google Chrome написать browserName = "chrome"
        driver = browser.getWebDriver("chrome");
        driver.get("https://stellarburgers.nomoreparties.site/");
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(buttonMakeOrder));

        AuthPageController authPage = new AuthPageController(driver);
        driver.findElement(buttonSignInAccount).click();
        authPage.singLoginFromMainPage(createUser.getEmail(), createUser.getPassword());
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(buttonMakeOrder));

    }

    @Test
    @DisplayName("Переход в личный кабинет")
    public void checkTransferToPersonalCabinetTest() {
        driver.findElement(buttonPersonalCabinet).click();
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.visibilityOfElementLocated(buttonSave));
        String actualResult = driver.findElement(buttonSave).getText();
        String expectedResult = "Сохранить";
        assertEquals(expectedResult, actualResult);
    }

    @Test
    @DisplayName("Переход из личного кабинета в конструктор")
    public void checkTransferToConstructorTest() {
        driver.findElement(buttonPersonalCabinet).click();
        new WebDriverWait(driver, Duration.ofSeconds(3));
        driver.findElement(buttonConstructor).click();
        String actualResult = driver.findElement(buttonEmblemsBurger).getText();
        String expectedResult = "Соберите бургер";
        assertEquals(expectedResult, actualResult);
    }

    @Test
    @DisplayName("Выход из аккаунта")
    public void checkLogoutTest() {
        driver.findElement(buttonPersonalCabinet).click();
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.visibilityOfElementLocated(buttonSave));
        driver.findElement(buttonExit).click();
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.visibilityOfElementLocated(buttonSignIn));
        String actualResult = driver.findElement(buttonSignIn).getText();
        String expectedResult = "Войти";
        assertEquals(expectedResult, actualResult);
    }

    @After
    public void teardown() {
        driver.quit();
        UserControllerStep.apiExecuteDelete(token);
    }
}
