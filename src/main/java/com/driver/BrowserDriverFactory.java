package com.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class BrowserDriverFactory {

    public static WebDriver initializeDriver(String browserType) {
        WebDriver webDriver;

        switch (browserType.toLowerCase()) {
            case "chrome":
                ChromeOptions chromeSettings = new ChromeOptions();
                chromeSettings.addArguments("start-maximized");
                webDriver = new ChromeDriver(chromeSettings);
                break;

            case "firefox":
                FirefoxOptions firefoxSettings = new FirefoxOptions();
                firefoxSettings.addArguments("-start-maximized");
                webDriver = new FirefoxDriver(firefoxSettings);
                break;

            default:
                throw new IllegalArgumentException("Unsupported browser: " + browserType);
        }

        return webDriver;
    }
}
