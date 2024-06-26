package dev.lenny.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class OrderPage {
    private final SelenideElement successPurchaseMessage = $x("//span[@class='base']");


    public String getSuccessPurchaseMessage() {
        return successPurchaseMessage.getText();
    }
}
