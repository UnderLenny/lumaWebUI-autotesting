package dev.lenny.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import java.util.HashMap;
import java.util.Map;

import static com.codeborne.selenide.Selenide.$;

public class SubscribePage extends BasePage {
    private final SelenideElement modalWindowData = $(By.xpath("//button[@aria-label='Соглашаюсь']"));
    private final SelenideElement subscribeButton = $(By.xpath("//input[@value='Subscribe']"));
    private final SelenideElement successMessage = $(By.xpath("//div[@id='mce-success-response']"));

    private final Map<String, SelenideElement> fields = new HashMap<>();

    public SubscribePage() {
        SelenideElement emailInput = $(By.xpath("//input[@type='email']"));
        fields.put("email", emailInput);

        SelenideElement firstNameInput = $(By.xpath("//*[@id='mce-FNAME']"));
        fields.put("firstName", firstNameInput);

        SelenideElement lastNameInput = $(By.xpath("//*[@id='mce-LNAME']"));
        fields.put("lastName", lastNameInput);

        SelenideElement companyNameInput = $(By.xpath("//*[@id='mce-COMPANY']"));
        fields.put("companyName", companyNameInput);

        SelenideElement positionInput = $(By.xpath("//*[@id='mce-POSITION']"));
        fields.put("position", positionInput);
    }

    public SelenideElement getModalWindow() {
        return modalWindowData;
    }
    public SubscribePage acceptModalWindow() {
        modalWindowData.shouldBe(Condition.visible).click();
        return this;
    }

    public SubscribePage clickSubscribeButton() {
        subscribeButton.click();
        return this;
    }

    public SelenideElement getSuccessMessage() {
        return successMessage;
    }

    public SubscribePage fillForm(Map<String, String> inputValues) {
        fillForm(inputValues, fields);
        return this;
    }
}
