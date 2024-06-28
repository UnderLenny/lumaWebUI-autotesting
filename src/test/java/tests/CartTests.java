package tests;

import com.codeborne.selenide.Selenide;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Epic("Тестирование корзины")
public class CartTests extends BaseTest {

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
        productCatalogPage.clickOnProductCard();
        productPage.addProductToCart();
//        cartPage.verifyProductAddedToCart();
//        productPage.clickOnCart();
//        assertEquals(cartPage.getProductNameFromCatalog(), cartPage.getProductNameInCart(), "Названия продуктов не совпадают");
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
        loginPage.submit();
        assertEquals("Hot Sellers", homePage.getHotSellersText());
        homePage.openProductCatalog();
        productPage.addProductToCart();
        cartPage.checkSuccessAddingToCart();
        productPage.clickOnCart();
        productPage.proceedToCheckout();
        paymentPage.selectShippingMethod();
        paymentPage.proceedToNextPage();
        paymentPage.placeOrder();
        assertEquals(paymentPage.getMessageText(), "Thank you for your purchase!", "Сообщение об успешном оформлении заказа не совпадает");
    }
}
