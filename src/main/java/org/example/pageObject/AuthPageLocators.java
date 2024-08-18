package org.example.pageObject;

import org.openqa.selenium.By;

public class AuthPageLocators {

    public static final By fieldEmailAuth = By.xpath("//*[@id=\"root\"]/div/main/div/form/fieldset[1]/div/div/input");
    public static final By fieldPasswordAuth = By.xpath("//*[@id=\"root\"]/div/main/div/form/fieldset[2]/div/div/input");
    public static final By buttonSignIn = By.xpath("//button[text()='Войти']");
    public static final By forgotPasswordLink = By.xpath("//*[@id=\"root\"]/div/main/div/div/p[2]/a");
    public static final By buttonPageReg = By.xpath("//*[@id=\"root\"]/div/main/div/div/p[1]/a");


}
