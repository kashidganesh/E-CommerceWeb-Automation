import com.pages.CartPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;

public class ShoppingCartTest {
    static WebDriver browser;
    private static WebDriverWait browserWait;

    @BeforeTest
    public static void setupBrowser(){
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        browser = new ChromeDriver(options);
        browserWait = new WebDriverWait(browser, Duration.ofSeconds(10));
        browser.get("https://web-playground.ultralesson.com/");
    }

    @Test
    public void testAddingProductToCart() {
        CartPage cartPage = new CartPage(browser, browserWait);
        int initialCartValue = cartPage.cartValue();
        String itemToSearch = "88 BSLT Skis";
        int expectedCartCount = 0;

        WebElement openSearch = browser.findElement(By.cssSelector(".modal__toggle-open"));
        browserWait.until(ExpectedConditions.elementToBeClickable(openSearch));
        openSearch.click();

        WebElement searchField = browser.findElement(By.cssSelector("#Search-In-Modal"));
        searchField.sendKeys(itemToSearch + Keys.ENTER);

        WebElement foundProduct = browser.findElement(By.xpath("//a[contains(text(),'"+itemToSearch+"')]"));
        foundProduct.click();

        WebElement productTitle = browser.findElement(By.cssSelector(".product__title"));
        browserWait.until(ExpectedConditions.visibilityOf(productTitle));
        Assert.assertEquals(productTitle.getText(), itemToSearch);

        WebElement addToCartButton = browser.findElement(By.xpath("//button[@type='submit' and @name='add']"));
        if (!addToCartButton.isEnabled()) {
            String errorMsg = "This product is sold out";
            System.out.println(errorMsg);
            Assert.fail(errorMsg);
            return;
        }
        addToCartButton.click();

        WebElement cartNotificationButton = browser.findElement(By.cssSelector("#cart-notification-button"));
        browserWait.until(ExpectedConditions.visibilityOf(cartNotificationButton));
        if(cartNotificationButton.isDisplayed()){
            expectedCartCount++;
            System.out.println("Product successfully added to cart");
            Assert.assertEquals(new CartPage(browser, browserWait).cartValue(), expectedCartCount, "Product was not added to cart");
        }
    }

    @AfterTest
    public static void tearDownBrowser(){
        browser.quit();
    }

    @BeforeMethod
    public void ensureEmptyCart() {
        CartPage cartPage = new CartPage(browser, browserWait);
        cartPage.clearCart(); // Ensure the cart is initially empty
    }

}
