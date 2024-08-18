package org.example;

import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import org.openqa.selenium.WebDriver;

import static org.example.pageObject.MainPageLocators.buttonPersonalCabinet;
import static org.example.pageObject.AuthPageLocators.buttonPageReg;
import static org.example.pageObject.RegistrationPageLocators.*;

public class RegisterPageController {
    private WebDriver driver;

    public RegisterPageController(WebDriver driver) {
        this.driver = driver;
    }

    public RegisterPageController() {

    }

    public void clickOnPersonalCabinetButton() {
        driver.findElement(buttonPersonalCabinet).click();
    }
    public void clickOnRegisterButton() {
        driver.findElement(buttonPageReg).click();
    }
    public void clickOnFinalRegisterButton() {
        driver.findElement(buttonRegister).click();
    }

    public void setName(String name) {
        driver.findElement(fieldNameReg).sendKeys(name);
    }

    public void setEmail(String email) {
        driver.findElement(fieldEmailReg).sendKeys(email);
    }

    public void setPassword(String password) {
        driver.findElement(fieldPasswordReg).sendKeys(password);
    }

    @Step
    @DisplayName("Регистрация пользователя")
    public void register(String name, String email, String password) {
        clickOnPersonalCabinetButton();
        clickOnRegisterButton();
        setName(name);
        setEmail(email);
        setPassword(password);
        clickOnFinalRegisterButton();
    }
}
