package dev.lenny.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;


import java.time.Duration;

import static com.codeborne.selenide.Selenide.$x;

public class OrderPage {
    private final SelenideElement successPurchaseMessage = $x("//span[@class='base']");

    @Step("Проверка текста о покупке")
    public String getSuccessPurchaseMessage() {
        return successPurchaseMessage.shouldBe(Condition.enabled, Duration.ofSeconds(10)).getText();
    }

}
