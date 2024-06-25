package dev.lenny.pages;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.$;
import org.openqa.selenium.By;

public class PaymentPage {
    private final SelenideElement shippingMethodInput = $(By.xpath("//input[@name='ko_unique_1']"));
    private final SelenideElement nextButton =$(By.xpath("//button[contains(@class, 'button')]"));
    private final SelenideElement placeOrderButton = $(By.xpath("//button[@title='Place Order']"));
    private final SelenideElement messageText = $(By.xpath("//span[@class='base']"));

    public PaymentPage selectShippingMethod() {
        shippingMethodInput.click();
        return this;
    }

    public PaymentPage clickNextButton() {
        nextButton.click();
        return this;
    }

    public PaymentPage clickPlaceOrderButton() {
        placeOrderButton.click();
        return this;
    }

    public String getMessageText() {
        return messageText.getText();
    }

    public SelenideElement getMessageTextElement() {
        return messageText;
    }

}
