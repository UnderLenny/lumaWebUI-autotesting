package dev.lenny.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.checkerframework.checker.units.qual.C;
import org.openqa.selenium.By;

import java.time.Duration;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class ProductPage {
    private final SelenideElement addToCartButton = $(By.xpath("//button[@title='Add to Cart']"));
    private final SelenideElement cart = $(By.xpath("//a[contains(@data-bind, 'minicart')]"));
    private final SelenideElement proceedToCheckoutButton = $(By.xpath("//*[@id='top-cart-btn-checkout']"));
    private final SelenideElement cartLink = $x("//a[@class='action showcart']");

    public void addProductToCart() {
        Selenide.sleep(5000);
        addToCartButton.click();
    }

    @Step("Клик на иконку корзинки")
    public CartPage clickOnCart() {
        cartLink.click();
        return new CartPage();
    }

    public PaymentPage clickOnProceedButton() {
        proceedToCheckoutButton.shouldBe(exist).click();
        return new PaymentPage();
    }


    @Step("Переход на страницу оформления заказа")
    public void proceedToCheckout() {
        clickOnProceedButton();
    }
}
