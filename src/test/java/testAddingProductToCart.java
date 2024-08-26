import com.pages.CartPage;
import com.pages.HomePage;
import com.pages.ProductDetails;
import org.junit.jupiter.api.Assertions;
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
        // Set up pages needed for the test
        // Add necessary steps to navigate to and add multiple products
        // Assert that all products are added to the cart with the correct quantities
    }
}
