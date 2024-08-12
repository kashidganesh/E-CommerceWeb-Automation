import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.pages.CartPage;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;

public class AddToCart {
    private static WebDriver driver;
    private static WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    @BeforeTest
    public static void initailizeBrowser(){
        driver = new ChromeDriver(new ChromeOptions().addArguments("start-maximized"));
        driver.get("https://web-playground.ultralesson.com/");
    }

    @Test
    public void AddToCartProduct() {

        String searchTerm = "88 BSLT Skis";
        int cartCount = 0;
        WebElement searchIcon = driver.findElement(By.cssSelector(".modal__toggle-open"));
        wait.until(ExpectedConditions.elementToBeClickable(searchIcon));
        searchIcon.click();
        WebElement searchBar = driver.findElement(By.cssSelector("#Search-In-Modal"));
        searchBar.sendKeys(searchTerm + Keys.ENTER);
        WebElement searchedProduct = driver.findElement(By.xpath("//a[contains(text(),'"+searchTerm+"')]"));
        searchedProduct.click();
        WebElement searchedProductTittle = driver.findElement(By.cssSelector(".product__title"));
        wait.until(ExpectedConditions.visibilityOf(searchedProductTittle));
        Assert.assertEquals(searchedProductTittle.getText(),searchTerm);
        WebElement addToCart = driver.findElement(By.xpath("//button[@type='submit' and @name='add']"));
        if (!addToCart.isEnabled()) {
            System.out.println("This product is sold out");
            driver.quit();
            return;
        }
        addToCart.click();
        WebElement cartValue = driver.findElement(By.cssSelector("#cart-notification-button"));
        wait.until(ExpectedConditions.visibilityOf(cartValue));
        if(cartValue.isDisplayed()){
            cartCount++;
            System.out.println("Item added to your cart");
            Assert.assertEquals(new CartPage(driver,wait).cartValue(),cartCount,"Item has not been added to your cart");
        }
    }

    @AfterTest
    public static void closeBrowser(){
        driver.quit();
    }

}
