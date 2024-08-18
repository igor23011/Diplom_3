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

import static org.example.pageObject.AuthPageLocators.forgotPasswordLink;
import static org.example.pageObject.MainPageLocators.*;
import static org.example.pageObject.AuthPageLocators.buttonPageReg;
import static org.example.pageObject.RegistrationPageLocators.buttonEnterPageReg;
import static org.junit.Assert.assertEquals;

public class LoginTest {
    private WebDriver driver;
    private static CreateUser createUser;
    private static String token;

    @Before
    public void setUp() {
        RestAssured.baseURI = "https://stellarburgers.nomoreparties.site/";
        createUser = new CreateUser("Ivan Bail", "!qwer12345", "ivan_molot008@yandex.ru");
        UserControllerStep.apiExecuteCreate(createUser);
        token = UserControllerStep.getUserToken(new LoginUser(createUser.getEmail(), createUser.getPassword()));

        Browser browser = new Browser();
        //Для запуска тестов в Яндекс.Браузере написать browserName = "yandex"
        //Для запуска тестов в Google Chrome написать browserName = "chrome"
        driver = browser.getWebDriver("chrome");
        driver.get("https://stellarburgers.nomoreparties.site/");
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(buttonMakeOrder));

    }

    @Test
    @DisplayName("Вход в ЛК через кнопку «Личный кабинет»")
    public void checkSuccessFromSignInPersonalCabinetTest() {
        AuthPageController authPage = new AuthPageController(driver);
        driver.findElement(buttonPersonalCabinet).click();
        authPage.singLoginFromMainPage(createUser.getEmail(), createUser.getPassword());
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.visibilityOfElementLocated(buttonMakeOrder));
        String actualResult = driver.findElement(buttonMakeOrder).getText();
        String expectedResult = "Оформить заказ";
        assertEquals(expectedResult, actualResult);
    }

    @Test
    @DisplayName("Вход в ЛК по кнопке «Войти в аккаунт»")
    public void checkSuccessLoginFromPersonalCabinetTest() {
        AuthPageController authPage = new AuthPageController(driver);
        driver.findElement(buttonSignInAccount).click();
        authPage.singLoginFromMainPage(createUser.getEmail(), createUser.getPassword());
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.visibilityOfElementLocated(buttonMakeOrder));
        String actualResult = driver.findElement(buttonMakeOrder).getText();
        String expectedResult = "Оформить заказ";
        assertEquals(expectedResult, actualResult);
    }

    @Test
    @DisplayName("Вход через кнопку в форме регистрации")
    public void checkSuccessLoginFromRegisterPageTest() {
        AuthPageController authPage = new AuthPageController(driver);
        driver.findElement(buttonPersonalCabinet).click();
        driver.findElement(buttonPageReg).click();
        driver.findElement(buttonEnterPageReg).click();
        authPage.singLoginFromMainPage(createUser.getEmail(), createUser.getPassword());
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.visibilityOfElementLocated(buttonMakeOrder));
        String actualResult = driver.findElement(buttonMakeOrder).getText();
        String expectedResult = "Оформить заказ";
        assertEquals(expectedResult, actualResult);
    }

    @Test
    @DisplayName("Вход через кнопку в форме восстановления пароля")
    public void checkSuccessLoginFromForgotPasswordFormTest() {
        AuthPageController authPage = new AuthPageController(driver);
        driver.findElement(buttonPersonalCabinet).click();
        driver.findElement(forgotPasswordLink).click();
        driver.findElement(buttonEnterPageReg).click();
        authPage.singLoginFromMainPage(createUser.getEmail(), createUser.getPassword());
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.visibilityOfElementLocated(buttonMakeOrder));
        String actualResult = driver.findElement(buttonMakeOrder).getText();
        String expectedResult = "Оформить заказ";
        assertEquals(expectedResult, actualResult);
    }

    @After
    public void teardown() {
        driver.quit();
        UserControllerStep.apiExecuteDelete(token);
    }
}