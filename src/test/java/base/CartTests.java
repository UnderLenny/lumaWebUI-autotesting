package tests;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import dev.lenny.pages.*;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Epic("Тестирование корзины")
public class CartTests extends BaseTest {

    CartPage cartPage = new CartPage();
    ProductCatalogPage productCatalogPage = new ProductCatalogPage();
    LoginPage loginPage = new LoginPage();
    HomePage homePage = new HomePage();
    PaymentPage paymentPage = new PaymentPage();

    @Feature("Добавление товара в корзину")
    @Story("Пользователь добавляет товар в корзину")
    @Severity(SeverityLevel.NORMAL)
    @Test
    @DisplayName("Тест добавления товара в корзину")
    @Owner("Leonid Gevorgyan")
    @Tag("debug")
    @Description("Этот тест проверяет, что пользователь может добавить товар в корзину.")
    public void addToCart() {
        addProductToCart();
        verifyProductAddedToCart();
        goToCart();
        verifyProductNameInCart();
    }

    @Step("Добавление продукта в корзину")
    public void addProductToCart() {
        productCatalogPage = homePage.hoverOverGearCategory();
        productCatalogPage = homePage.clickBagsSubcategory();
        cartPage.clickOnProductCard().addProductToCart();
    }

    @Step("Проверка успешного добавления продукта в корзину")
    public void verifyProductAddedToCart() {
        cartPage.checkSuccessAddToCartMessage();
    }

    @Step("Переход в корзину")
    public void goToCart() {
        cartPage.clickOnCart();
    }

    @Step("Проверка названия продукта в корзине")
    public void verifyProductNameInCart() {
        String productNameFromCatalog = cartPage.getProductName();
        String productNameInCart = cartPage.getProductNameInCart();
        assertEquals(productNameFromCatalog, productNameInCart, "Названия продуктов не совпадают");
    }

    @Feature("Покупка товара")
    @Story("Пользователь покупает товар")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    @DisplayName("Тест покупки товара")
    @Owner("Leonid Gevorgyan")
    @Description("Этот тест проверяет, что пользователь может купить товар.")
    public void buyProduct() {
        Map<String, String> inputValues = new HashMap<>();
        prepareLoginData(inputValues);
        goToLoginPage();
        login(inputValues);
        verifyLogin();
        addProductToCart();
        checkSuccessAddingToCart();
        goToCart();
        proceedToCheckout();
        selectShippingMethod();
        proceedToNextPage();
        placeOrder();
        verifyOrderSuccess();
    }
}
