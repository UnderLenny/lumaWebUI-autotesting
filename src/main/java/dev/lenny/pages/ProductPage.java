package dev.lenny.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import java.time.Duration;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class ProductPage {
    private final SelenideElement addToCartButton = $(By.xpath("//button[@title='Add to Cart']"));
    private final SelenideElement cart = $(By.xpath("//a[contains(@data-bind, 'minicart')]"));
    private final SelenideElement proceedToCheckoutButton = $(By.xpath("//*[@id='top-cart-btn-checkout']"));
    private final SelenideElement cartLink = $x("//a[@class='action showcart']");

    public CartPage addProductToCart() {
        addToCartButton.shouldBe(Condition.clickable, Duration.ofSeconds(10)).click();
        return new CartPage();
    }

    @Step("Клик на иконку корзинки")
    public ProductPage clickOnCart() {
        cartLink.shouldBe(Condition.clickable, Duration.ofSeconds(10)).click();
        return this;
    }

    @Step("Переход на страницу оформления заказа")
    public PaymentPage clickOnProceedButton() {
        proceedToCheckoutButton.shouldBe(Condition.visible, Duration.ofSeconds(10)).click();
        return new PaymentPage();
    }

}
