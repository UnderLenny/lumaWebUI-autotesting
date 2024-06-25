package dev.lenny.pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import java.util.HashMap;
import java.util.Map;

import static com.codeborne.selenide.Selenide.$;

public class ChangeProfileDataPage extends BasePage {
    private final SelenideElement submitButton = $(By.xpath("//button[@title='Save']"));
    private final SelenideElement errorText = $(By.xpath("//div[contains(@data-bind, 'text')]"));

    private final Map<String, SelenideElement> fields = new HashMap<>();

    public ChangeProfileDataPage() {
        SelenideElement currentPasswordField = $(By.xpath("//*[@id='current-password']"));
        fields.put("currentPasswordField", currentPasswordField);

        SelenideElement newPasswordField = $(By.xpath("//*[@id='password']"));
        fields.put("newPasswordField", newPasswordField);

        SelenideElement confirmPasswordField = $(By.xpath("//*[@id='password-confirmation']"));
        fields.put("confirmPasswordField", confirmPasswordField);
    }

    public ChangeProfileDataPage submit() {
        submitButton.click();
        return this;
    }

    public ChangeProfileDataPage fillForm(Map<String, String> inputValues) {
        fillForm(inputValues, fields);
        return this;
    }

    public SelenideElement getErrorText() {
        return errorText;
    }

}
