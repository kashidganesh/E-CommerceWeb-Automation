package com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class ProductDetails {

    private WebDriver webDriver;
    private WebDriverWait webDriverWait;

    private By itemTitleLocator = By.cssSelector(".product_title");
    private By itemPriceLocator = By.cssSelector(".product_price");
    private By addButtonLocator = By.id("add_to_cart");
    private By quantityInputLocator = By.id("quantity_wanted");

    public ProductDetails(WebDriver webDriver) {
        this.webDriver = webDriver;
        this.webDriverWait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
    }

    public String retrieveItemName() {
        return webDriver.findElement(itemTitleLocator).getText();
    }

    public String retrieveItemPrice() {
        return webDriver.findElement(itemPriceLocator).getText();
    }

    public void specifyItemQuantity(int quantity) {
        WebElement quantityInput = webDriver.findElement(quantityInputLocator);
        quantityInput.clear();
        quantityInput.sendKeys(String.valueOf(quantity));
    }

    public void clickAddToCart() {
        webDriver.findElement(addButtonLocator).click();
    }

    public void searchForProduct(String productName) {

    }
}
