package com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.List;

public class CartPage {

    private WebDriver driver;
    private WebDriverWait wait;
    private static final String CART_PAGE_URL = "https://web-playground.ultralesson.com/cart";
    public CartPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
        driver.get(CART_PAGE_URL);
        navigateToCartPage();
    }

    public CartPage(String s, String s1) {
    }

    public int cartValue() {
        WebElement cartMessage = driver.findElement(By.xpath("//h1[contains(text(),'Your cart is empty')]"));
        if (cartMessage.isDisplayed()) {
            driver.navigate().back();
            return 0;
        }
        WebElement cartValue = driver.findElement(By.xpath("//div[@class='cart-count-bubble']/span[@aria-hidden='true']"));
        int value = Integer.parseInt(cartValue.getText());
        return value;
    }
    public int getCartItemCount() {
        WebElement emptyCartMessage = driver.findElement(By.xpath("//h1[contains(text(),'Your cart is empty')]"));
        if (emptyCartMessage.isDisplayed()) {
            driver.navigate().back();
            return 0;
        }
        WebElement itemCountElement = driver.findElement(By.xpath("//div[@class='cart-count-bubble']/span[@aria-hidden='true']"));
        return Integer.parseInt(itemCountElement.getText());
    }

    public void verifyCartItem(String itemName, int expectedQuantity, double expectedPrice) {
        WebElement itemNameElement = driver.findElement(By.xpath("//td[@class='cart-item__details']/a"));
        wait.until(ExpectedConditions.visibilityOf(itemNameElement));
        WebElement quantityElement = driver.findElement(By.cssSelector(".quantity__input"));
        WebElement priceElement = driver.findElement(By.xpath("//tbody/tr/td[@class='cart-item__totals right small-hide']//span"));

        Assert.assertTrue(itemName.equalsIgnoreCase(itemNameElement.getText().trim()), itemName + " is not present in the cart.");
        Assert.assertEquals(expectedQuantity, Integer.parseInt(quantityElement.getAttribute("value")));
        Assert.assertTrue(priceElement.getText().trim().contains(String.format("%.2f", expectedPrice * expectedQuantity)));
    }

    public void clearCart() {
        List<WebElement> removeButtons = driver.findElements(By.tagName("cart-remove-button"));
        for (WebElement button : removeButtons) {
            button.click();
        }
        WebElement emptyCartMessage = driver.findElement(By.xpath("//h1[contains(text(),'Your cart is empty')]"));
        wait.until(ExpectedConditions.visibilityOf(emptyCartMessage));
        Assert.assertTrue(emptyCartMessage.isDisplayed(), "Cart is not empty after removing items.");
    }

    public void verifyCartItem(String itemName, int expectedQuantity) {
        List<WebElement> itemNames = driver.findElements(By.xpath("//td[@class='cart-item__details']/a"));
        List<WebElement> quantities = driver.findElements(By.cssSelector(".quantity__input"));

        boolean found = false;
        for (int i = 0; i < itemNames.size(); i++) {
            if (itemNames.get(i).getText().equalsIgnoreCase(itemName) &&
                    Integer.parseInt(quantities.get(i).getAttribute("value")) == expectedQuantity) {
                found = true;
                break;
            }
        }

        Assert.assertTrue(found, "Item name with the expected quantity not found in the cart.");
    }
    public void verifyCartItemDetails(String itemName, String itemSize, int expectedQuantity, double expectedPrice) {
        // Example: Fetch and verify item size along with name and quantity
        WebElement itemSizeElement = driver.findElement(By.xpath("//td[contains(@class,'cart-item__size')]"));
        String actualSize = itemSizeElement.getText().trim();

        // Existing validations
        verifyCartItem(itemName, expectedQuantity);

        // Additional validation for item size
        Assert.assertEquals(actualSize, itemSize, "Item size in the cart does not match the expected.");
    }

    public void verifyCartItemPrice(String itemName, double expectedUnitPrice, int quantity) {
        WebElement priceElement = driver.findElement(By.xpath("//td[@class='cart-item__details']/a[text()='" + itemName + "']/following::td[@class='price']/span"));
        double actualUnitPrice = Double.parseDouble(priceElement.getText().replace("$", "").trim());
        Assert.assertEquals(actualUnitPrice, expectedUnitPrice, "Unit price for the item in the cart does not match the expected.");

        double expectedTotal = expectedUnitPrice * quantity;
        WebElement totalPriceElement = driver.findElement(By.xpath("...")); // Add the correct XPath for the total price
        double actualTotal = Double.parseDouble(totalPriceElement.getText().replace("$", "").trim());
        Assert.assertEquals(actualTotal, expectedTotal, "The total price for the item in the cart does not match the expected.");
    }

    public void navigateToCartPage() {
        driver.get(CART_PAGE_URL);
    }

    public void removeItemFromCart(String itemName) {
        List<WebElement> removeButtons = driver.findElements(By.tagName("cart-remove-button"));
        List<WebElement> items = driver.findElements(By.xpath("//td[@class='cart-item__details']/a"));
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getText().trim().contains(itemName)) {
                removeButtons.get(i).click();
                break;
            }
        }
    }
}
