package dev.lenny.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selenide.$;

public class PaymentPage {
    private final SelenideElement shippingMethodInput = $(By.xpath("//input[@name='ko_unique_1']"));
    private final SelenideElement nextButton = $(By.xpath("//button[contains(@class, 'button')]"));
    private final SelenideElement placeOrderButton = $(By.xpath("//button[@title='Place Order']"));
    private final SelenideElement messageText = $(By.xpath("//span[@class='base']"));

    @Step("Выбор метода доставки")
    public void selectShippingMethod() {
        shippingMethodInput.shouldBe(exist).click();
    }

    @Step("Переход на следующую страницу")
    public void proceedToNextPage() {
        nextButton.shouldBe(exist).click();
    }

    @Step("Оформление заказа")
    public void placeOrder() {
        placeOrderButton.shouldBe(exist).click();
    }

    public String getMessageText() {
        return messageText.getText();
    }
}
