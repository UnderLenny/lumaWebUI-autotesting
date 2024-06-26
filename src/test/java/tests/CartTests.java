package tests;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import dev.lenny.pages.*;
import io.qameta.allure.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
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
    @Description("Этот тест проверяет, что пользователь может добавить товар в корзину.")
    public void addToCart() {
        addProductToCart();
        verifyProductAddedToCart();
        goToCart();
        verifyProductNameInCart();
    }

    @Step("Добавление продукта в корзину")
    public void addProductToCart() {
        productCatalogPage
                .hoverOverGearCategory()
                .clickBagsSubcategory();
        cartPage.clickOnProductCard().addProductToCart();
    }

    @Step("Проверка числа на кнопке корзины")
    public void verifyProductAddedToCart() {
        cartPage.checkNumberOnCartButton();
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
        goToCart();
        proceedToCheckout();
        selectShippingMethod();
        proceedToNextPage();
        placeOrder();
        verifyOrderSuccess();
    }

    @Step("Подготовка данных для входа")
    public void prepareLoginData(Map<String, String> inputValues) {

        inputValues.put("email", "test@mail.ru");
        inputValues.put("password", "12345678dD");
    }

    @Step("Переход на страницу входа")
    public void goToLoginPage() {
        homePage.goToLoginPage();
    }

    @Step("Ввод данных и отправка формы")
    public void login(Map<String, String> inputValues) {
        loginPage
                .fillForm(inputValues)
                .submit();
    }

    @Step("Проверка URL после успешного входа")
    public void verifyLogin() {
        String currentUrl = WebDriverRunner.getWebDriver().getCurrentUrl();
        String expectedUrl = "https://magento.softwaretestingboard.com/";
        assertEquals(expectedUrl, currentUrl, "URL после авторизации не совпадает с ожидаемым");
    }

    @Step("Переход на страницу оформления заказа")
    public void proceedToCheckout() {
        cartPage.clickOnProccessedButton();
    }

    @Step("Выбор метода доставки")
    public void selectShippingMethod() {
        paymentPage.selectShippingMethod();
    }

    @Step("Переход на следующую страницу")
    public void proceedToNextPage() {
        paymentPage.clickNextButton();
    }

    @Step("Оформление заказа")
    public void placeOrder() {
        paymentPage.clickPlaceOrderButton();
    }

    @Step("Проверка успешного оформления заказа")
    public void verifyOrderSuccess() {
        Selenide.Wait().until(ExpectedConditions.textToBePresentInElement(paymentPage.getMessageTextElement(), "Thank you for your purchase!"));
        String messageText = paymentPage.getMessageText();
        assertEquals("Thank you for your purchase!", messageText, "Сообщение об успешном оформлении заказа не совпадает");
    }
}
