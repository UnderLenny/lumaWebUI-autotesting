package tests;

import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Epic("Тестирование корзины")
public class ProductTests extends BaseTest {

    @Feature("Добавление товара в корзину")
    @Story("Пользователь добавляет товар в корзину")
    @Severity(SeverityLevel.NORMAL)
    @Test
    @DisplayName("Тест добавления товара в корзину")
    @Owner("Leonid Gevorgyan")
    @Tag("positive")
    @Description("Этот тест проверяет, что пользователь может добавить товар в корзину.")
    public void addToCart() {
        productCatalogPage = homePage.openProductCatalog();
        productPage = productCatalogPage.clickOnProductCard();
        cartPage = productPage.addProductToCart();
        cartPage.checkSuccessAddingToCart();
        productPage.clickOnCart();
        assertEquals(cartPage.getProductNameFromCatalog(), cartPage.getProductNameInCart(), "Названия продуктов не совпадают");
    }

    @Feature("Покупка товара")
    @Story("Пользователь покупает товар")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    @DisplayName("Тест покупки товара")
    @Owner("Leonid Gevorgyan")
    @Description("Этот тест проверяет, что пользователь может купить товар.")
    public void buyProduct() {
        loginPage = homePage.goToLoginPage();
        loginPage.fillAuthForm();
        homePage = loginPage.submit();
        assertEquals("Hot Sellers", homePage.getHotSellersText());
        productCatalogPage = homePage.openProductCatalog();
        productPage = productCatalogPage.clickOnProductCard();
        cartPage = productPage.addProductToCart();
        cartPage.checkSuccessAddingToCart();
        productPage.clickOnCart();
        paymentPage = productPage.clickOnProceedButton();
        paymentPage.selectShippingMethod();
        paymentPage.proceedToNextPage();
        orderPage = paymentPage.placeOrder();
        assertEquals( "Checkout", orderPage.getSuccessPurchaseMessage(),"Сообщение об успешном оформлении заказа не совпадает");
    }
}
