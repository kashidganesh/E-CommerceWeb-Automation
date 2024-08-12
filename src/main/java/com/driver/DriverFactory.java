package com.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class DriverFactory {

    private static ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();

    private DriverFactory() {}

    public static WebDriver getDriver() {
        if (driverThreadLocal.get() == null) {
            synchronized (DriverFactory.class) {
                if (driverThreadLocal.get() == null) {
                    ChromeOptions options = new ChromeOptions();
                    options.addArguments("start-maximized");
                    driverThreadLocal.set(new ChromeDriver(options));
                }
            }
        }
        return driverThreadLocal.get();
    }

    public static void quitDriver() {
        if (driverThreadLocal.get() != null) {
            driverThreadLocal.get().quit();
            driverThreadLocal.remove();
        }
    }
}