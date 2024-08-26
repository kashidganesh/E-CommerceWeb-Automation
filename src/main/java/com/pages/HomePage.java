package com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public class HomePage {

    private WebDriver webDriver;
    private WebDriverWait webDriverWait;
    private Actions actionHandler;

    // Element locators for the main page
    private By searchFieldLocator = By.id("search_query_top");
    private By submitButtonLocator = By.name("submit_search");
    private By productContainerLocator = By.cssSelector(".product_list");
    private By individualProductLocator = By.cssSelector(".product_item");

    public HomePage(WebDriver webDriver) {
        this.webDriver = webDriver;
        this.webDriverWait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        this.actionHandler = new Actions(webDriver);
    }

    public void performSearch(String query) {
        WebElement searchField = webDriver.findElement(searchFieldLocator);
        searchField.clear();
        searchField.sendKeys(query);
        webDriver.findElement(submitButtonLocator).click();
    }

    public List<WebElement> retrieveProducts() {
        WebElement productContainer = webDriver.findElement(productContainerLocator);
        return productContainer.findElements(individualProductLocator);
    }

    public void navigateToProduct(String productName) {
        List<WebElement> products = retrieveProducts();
        for (WebElement product : products) {
            if (product.getText().contains(productName)) {
                actionHandler.moveToElement(product).click().perform();
                break;
            }
        }
    }

    public ProductDetails searchAndNavigateToProduct(String s) {

        return null;
    }
}
