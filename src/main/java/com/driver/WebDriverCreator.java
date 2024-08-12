package com.driver;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public abstract  class WebDriverCreator {

    protected abstract ChromeDriver createWebDriver();

    public ChromeDriver getDriver() {
        return createWebDriver();
    }
}

