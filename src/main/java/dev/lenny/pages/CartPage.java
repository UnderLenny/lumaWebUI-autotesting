package dev.lenny.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import com.codeborne.selenide.WebDriverRunner;

import static com.codeborne.selenide.Selenide.$x;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CartPage {
    private final SelenideElement productName = $x("//span[@class='base']");
    private final SelenideElement productNameInCart = $x("//strong[@class='product-item-name']");
    private final SelenideElement successAddToCartMessage = $x("//div[contains(@class, 'success')]");

    @Step("Проверка успешного добавления продукта в корзину")
    public void verifyProductAddedToCart() {
        successAddToCartMessage.shouldBe(Condition.visible).isDisplayed();
    }

    @Step("Проверка названия продукта в корзине")
    public String getProductNameFromCatalog() {
        return productName.getText();
    }

    public String getProductNameInCart() {
        return productNameInCart.getText();
    }

    @Step("Проверка успешного входа")
    public void verifyLogin() {
        String currentUrl = WebDriverRunner.getWebDriver().getCurrentUrl();
        String expectedUrl = "https://magento.softwaretestingboard.com/";
        assertEquals(expectedUrl, currentUrl, "URL после авторизации не совпадает с ожидаемым");
    }

    @Step("Проверка успешного добавления продукта в корзину")
    public void checkSuccessAddingToCart() {
        successAddToCartMessage.shouldBe(Condition.visible).isDisplayed();
    }
}
