package dev.lenny.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$x;

public class PaymentPage {
    private final SelenideElement shippingMethodInput = $x(("//input[@name='ko_unique_1']"));
    private final SelenideElement nextButton = $x(("//button[contains(@class, 'button')]"));
    private final SelenideElement placeOrderButton = $x(("//button[@title='Place Order']"));
    private final SelenideElement messageText = $x(("//span[@class='base']"));

    @Step("Выбор метода доставки")
    public void selectShippingMethod() {
        shippingMethodInput.scrollTo().shouldBe(enabled).click();
    }

    @Step("Переход на следующую страницу")
    public void proceedToNextPage() {
        nextButton.shouldBe(enabled).click();
    }

    @Step("Оформление заказа")
    public OrderPage placeOrder() {
        placeOrderButton.shouldBe(enabled).click();
        return new OrderPage();
    }

    public String getMessageText() {
        return messageText.shouldBe(Condition.visible).getText();
    }
}
