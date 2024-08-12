package com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;


public class ProductDetails {
    private WebDriver driver;
    private WebDriverWait wait;

    // Locators for product details elements
    private By productNameLocator = By.cssSelector(".product_title");
    private By productPriceLocator = By.cssSelector(".product_price");
    private By addToCartButtonLocator = By.id("add_to_cart");
    private By quantityFieldLocator = By.id("quantity_wanted");

    /**
     * Initializes the ProductDetails with a WebDriver instance.
     *
     * @param driver The WebDriver used to interact with the web page.
     */
    public ProductDetails(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    /**
     * Retrieves the name of the product.
     *
     * @return The product name as a String.
     */
    public String getProductName() {
        return driver.findElement(productNameLocator).getText();
    }

    /**
     * Retrieves the price of the product.
     *
     * @return The product price as a String.
     */
    public String getProductPrice() {
        return driver.findElement(productPriceLocator).getText();
    }

    /**
     * Sets the desired quantity for the product.
     *
     * @param quantity The quantity to set.
     */
    public void setProductQuantity(int quantity) {
        WebElement quantityField = driver.findElement(quantityFieldLocator);
        quantityField.clear();
        quantityField.sendKeys(String.valueOf(quantity));
    }

    /**
     * Adds the product to the shopping cart.
     */
    public void addToCart() {
        driver.findElement(addToCartButtonLocator).click();
    }
}
