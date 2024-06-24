package tests;

import com.codeborne.selenide.WebDriverRunner;
import org.junit.jupiter.api.Test;
import pages.CartPage;
import pages.HomePage;
import pages.LoginPage;
import pages.ProductCatalogPage;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CartTests extends BaseTest {

    CartPage cartPage = new CartPage();
    ProductCatalogPage productCatalogPage = new ProductCatalogPage();
    LoginPage loginPage = new LoginPage();
    HomePage homePage = new HomePage();

    @Test
    public void addToCart() {
        // Комментари шагов для Allure(на будущее)
        // Шаг 1: Добавление продукта в корзину
        productCatalogPage
                .hoverOverGearCategory()
                .clickBagsSubcategory();
        cartPage.clickOnProductCard().addProductToCart();

        // Шаг 2: Проверка числа на кнопке корзины
        cartPage.checkNumberOnCartButton();

        // Шаг 3: Переход в корзину
        cartPage.clickOnCart();

        // Шаг 4: Проверка названия продукта в корзине
        String productNameFromCatalog = cartPage.getProductName();
        String productNameInCart = cartPage.getProductNameInCart();
        assertEquals(productNameFromCatalog, productNameInCart, "Названия продуктов не совпадают");
    }

    @Test
    public void buyProduct() {
        // Шаг 1: Подготовка данных для входа
        Map<String, String> inputValues = new HashMap<>();
        inputValues.put("email", "test@mail.ru");
        inputValues.put("password", "12345678dD");

        // Шаг 2: Переход на страницу входа
        homePage.goToLoginPage();

        // Шаг 3: Ввод данных и отправка формы
        loginPage
                .fillForm(inputValues)
                .submit();

        // Шаг 4: Проверка URL после успешного входа
        String currentUrl = WebDriverRunner.getWebDriver().getCurrentUrl();
        String expectedUrl = "https://magento.softwaretestingboard.com/";
        assertEquals(expectedUrl, currentUrl, "URL после авторизации не совпадает с ожидаемым");

        // Шаг 5: Добавление продукта в корзину
        productCatalogPage
                .hoverOverGearCategory()
                .clickBagsSubcategory();
        cartPage.clickOnProductCard().addProductToCart();

        // Шаг 6: Переход в корзину
        cartPage.clickOnCart();

        // Шаг 7: Переход на страницу офорлмения заказа
        cartPage.clickOnProccessedButton();
    }
}
