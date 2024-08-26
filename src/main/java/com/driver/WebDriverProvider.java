package com.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class WebDriverProvider {

    private static final ThreadLocal<WebDriver> threadLocalDriver = new ThreadLocal<>();

    private WebDriverProvider() {}

    public static WebDriver getInstance() {
        if (threadLocalDriver.get() == null) {
            synchronized (WebDriverProvider.class) {
                if (threadLocalDriver.get() == null) {
                    ChromeOptions options = new ChromeOptions();
                    options.addArguments("start-maximized");
                    threadLocalDriver.set(new ChromeDriver(options));
                }
            }
        }
        return threadLocalDriver.get();
    }

    public static void closeDriver() {
        WebDriver driver = threadLocalDriver.get();
        if (driver != null) {
            driver.quit();
            threadLocalDriver.remove();
        }
    }
}
