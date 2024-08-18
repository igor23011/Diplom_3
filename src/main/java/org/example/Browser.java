package org.example;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.chromium.ChromiumDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Browser {
    public WebDriver getWebDriver(String browserName) {
        WebDriver driver = null;
        switch (browserName) {
            case "chrome":
                System.setProperty("webdriver.chrome.driver","src\\test\\resources\\chromedriver.exe");
                driver = new ChromeDriver();
                break;
            case "yandex":
                System.setProperty("webdriver.chrome.driver","src\\test\\resources\\yandexdriver.exe");
                ChromeOptions options = new ChromeOptions();
                driver = new ChromeDriver(options);
                break;
        }
        return driver;
    }
}
