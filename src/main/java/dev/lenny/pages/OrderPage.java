package dev.lenny.pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static com.codeborne.selenide.Selenide.$x;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class OrderPage {
    private final SelenideElement successPurchaseMessage = $x("//span[@class='base']");


    public String getSuccessPurchaseMessage() {
        return successPurchaseMessage.getText();
    }

//    @Step("Проверка успешного оформления заказа")
//    public void verifyOrderSuccess() {
//        Selenide.Wait().until(ExpectedConditions.textToBePresentInElement(getSuccessPurchaseMessage(), "Thank you for your purchase!"));
//        String messageText = paymentPage.getMessageText();
//        assertEquals("Thank you for your purchase!", messageText, "Сообщение об успешном оформлении заказа не совпадает");
//    }
}
