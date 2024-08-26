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
}
