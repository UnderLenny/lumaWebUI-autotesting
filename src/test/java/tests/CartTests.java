package tests;

import org.junit.jupiter.api.Test;
import pages.CartPage;
import pages.ProductCatalogPage;

import java.util.Objects;

import static com.codeborne.selenide.Condition.text;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CartTests extends BaseTest{

    CartPage cartPage = new CartPage();
    ProductCatalogPage productCatalogPage = new ProductCatalogPage();

    @Test
    public void addToCartTest() {
        productCatalogPage
                .hoverOverGearCategory()
                .clickBagsSubcategory();
        cartPage
                .clickOnProductCard()
                .addProductToCart()
                .checkNumberOnCartButton()
                .clickOnCart();
            String productName = String.valueOf(cartPage.getProductName());
            String productNameInCart = String.valueOf(cartPage.getProductNameInCart());
            assertEquals(productName, productNameInCart);
        }
    }
