package dev.lenny.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import java.time.Duration;

import static com.codeborne.selenide.Selenide.$x;

public class CartPage {
    private final SelenideElement productName = $x("//span[@class='base']");
    private final SelenideElement productNameInCart = $x("//strong[@class='product-item-name']");
    private final SelenideElement successAddToCartMessage = $x("//div[contains(@class, 'success')]");

    @Step("Проверка названия продукта в корзине")
    public String getProductNameFromCatalog() {
        return productName.getText();
    }

    public String getProductNameInCart() {
        return productNameInCart.getText();
    }


    @Step("Проверка успешного добавления продукта в корзину")
    public void checkSuccessAddingToCart() {
        successAddToCartMessage.shouldBe(Condition.visible, Duration.ofSeconds(10));
    }
}
