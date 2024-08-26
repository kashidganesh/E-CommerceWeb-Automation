import com.pages.CartPage;
import com.pages.HomePage;
import com.pages.ProductDetails;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class testAddingProductToCart {

    @Test
    public void testAddingProductToCart() {
        HomePage homePage = new HomePage(ShoppingCartTest.browser);
        ProductDetails productDetails = homePage.searchAndNavigateToProduct("88 BSLT Skis");
        productDetails.clickAddToCart();
        CartPage cartPage = new CartPage("","");
    }

    @Test
    public void testAddMultipleProductsToCart() {
        // Navigate to HomePage and perform a search for the first product
        HomePage homePage = new HomePage(ShoppingCartTest.browser);
        ProductDetails productDetails1 = homePage.searchAndNavigateToProduct("First Product Name");
        productDetails1.specifyItemQuantity(2);
        productDetails1.clickAddToCart();

        // Navigate to HomePage and perform a search for the second product
        homePage.performSearch("Second Product Name");
        ProductDetails productDetails2 = homePage.searchAndNavigateToProduct("Second Product Name");
        productDetails2.specifyItemQuantity(1);
        productDetails2.clickAddToCart();

        // Validate items in the cart
        WebDriverWait ShoppingCartTest = null;
        CartPage cartPage = new CartPage((WebDriver) ShoppingCartTest, ShoppingCartTest );
        cartPage.verifyCartItem("First Product Name", 2);
        cartPage.verifyCartItem("Second Product Name", 1);

        // Verify total price logic here
    }
}
