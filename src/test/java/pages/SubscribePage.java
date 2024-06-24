package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import java.util.Map;

import static com.codeborne.selenide.Selenide.$;

public class SubscribePage {
    private final SelenideElement emailInput = $(By.xpath("//input[@type='email']"));
    private final SelenideElement modalWindowData = $(By.xpath("//button[@aria-label='Соглашаюсь']"));
    private final SelenideElement firstNameInput = $(By.xpath("//input[@id='mce-FNAME']"));
    private final SelenideElement lastNameInput = $(By.xpath("//input[@id='mce-LNAME']"));
    private final SelenideElement companyNameInput = $(By.xpath("//input[@id='mce-COMPANY']"));
    private final SelenideElement positionInput = $(By.xpath("//input[@id='mce-POSITION']"));
    private final SelenideElement subscribeButton = $(By.xpath("//input[@value='Subscribe']"));
    private final SelenideElement successMessage = $(By.xpath("//div[@id='mce-success-response']"));

    public SubscribePage acceptModalWindow() {
        modalWindowData.shouldBe(Condition.visible).click();
        return this;
    }

    public SubscribePage enterEmail(String email) {
        emailInput.setValue(email);
        return this;
    }

    public SubscribePage enterFirstName(String name) {
        firstNameInput.setValue(name);
        return this;
    }

    public SubscribePage enterLastName(String surname) {
        lastNameInput.setValue(surname);
        return this;
    }

    public SubscribePage enterCompanyName(String company) {
        companyNameInput.setValue(company);
        return this;
    }

    public SubscribePage enterPosition(String position) {
        positionInput.setValue(position);
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
        inputValues.forEach((key, value) -> {
            switch (key) {
                case "firstName":
                    enterFirstName(value);
                    break;
                case "lastName":
                    enterLastName(value);
                    break;
                case "email":
                    enterEmail(value);
                    break;
                case "companyName":
                    enterCompanyName(value);
                    break;
                case "position":
                    enterPosition(value);
                    break;
                default:
                    throw new AssertionError(key);
            }
        });
        return this;
    }
}