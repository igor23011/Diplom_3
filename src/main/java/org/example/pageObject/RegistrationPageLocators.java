package org.example.pageObject;

import org.openqa.selenium.By;

public class RegistrationPageLocators {
    public static final By fieldNameReg = By.xpath("//*[@id=\"root\"]/div/main/div/form/fieldset[1]/div/div/input");
    public static final By fieldEmailReg = By.xpath("//*[@id=\"root\"]/div/main/div/form/fieldset[2]/div/div/input");
    public static final By fieldPasswordReg = By.xpath("//*[@id=\"root\"]/div/main/div/form/fieldset[3]/div/div/input");
    public static final By buttonRegister = By.xpath("//button[text()='Зарегистрироваться']");
    public static final By getTextPassword = By.xpath("//*[@id=\"root\"]/div/main/div/form/fieldset[3]/div/p");
    public static final By buttonEnterPageReg = By.xpath("//*[@id=\"root\"]/div/main/div/div/p/a");
}