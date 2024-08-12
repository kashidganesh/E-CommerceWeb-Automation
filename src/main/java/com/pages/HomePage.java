package com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;


public class HomePage {
    private WebDriver driver;
    private WebDriverWait wait;
    private Actions actions;

    // Locators for homepage elements
    private By searchBoxLocator = By.id("search_query_top");
    private By searchButtonLocator = By.name("submit_search");
    private By productListLocator = By.cssSelector(".product_list");
    private By productItemLocator = By.cssSelector(".product_item");

    /**
     * Initializes the HomePage with a WebDriver instance.
     *
     * @param driver The WebDriver used to interact with the web page.
     */
    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.actions = new Actions(driver);
    }

    /**
     * Performs a search for a given product keyword.
     *
     * @param keyword The product keyword to search for.
     */
    public void searchForProduct(String keyword) {
        WebElement searchBox = driver.findElement(searchBoxLocator);
        searchBox.clear();
        searchBox.sendKeys(keyword);
        driver.findElement(searchButtonLocator).click();
    }

    /**
     * Retrieves a list of products displayed on the homepage.
     *
     * @return A list of WebElements representing the products.
     */
    public List<WebElement> getDisplayedProducts() {
        WebElement productList = driver.findElement(productListLocator);
        return productList.findElements(productItemLocator);
    }

    /**
     * Navigates to the details page of a specific product.
     *
     * @param productName The name of the product to navigate to.
     */
    public void goToProductDetails(String productName) {
        List<WebElement> products = getDisplayedProducts();
        for (WebElement product : products) {
            if (product.getText().contains(productName)) {
                actions.moveToElement(product).click().perform();
                break;
            }
        }
    }
}

