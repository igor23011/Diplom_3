package org.example;

import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import org.openqa.selenium.WebDriver;

import static org.example.pageObject.AuthPageLocators.*;

public class AuthPageController {
    private WebDriver driver;

    public AuthPageController(WebDriver driver) {
        this.driver = driver;
    }

    public void setEmail(String email) {
        driver.findElement(fieldEmailAuth).sendKeys(email);
    }

    public void setPassword(String password) {
        driver.findElement(fieldPasswordAuth).sendKeys(password);
    }

    public void clickAuthButton() {
        driver.findElement(buttonSignIn).click();
    }

    @Step("Вход в личный кабинет пользователя")
    public void signLoginFromMainPage(String email, String password) {
        setEmail(email);
        setPassword(password);
        clickAuthButton();
    }
}
