package dev.lenny.pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import java.util.HashMap;
import java.util.Map;

import static com.codeborne.selenide.Selenide.$;

public class LoginPage extends BasePage {
    private final SelenideElement submitButton = $(By.xpath("//button[@class='action login primary']"));
    private final SelenideElement errorText = $(By.xpath("//div[contains(@data-bind, 'text')]"));

    private final Map<String, SelenideElement> fields = new HashMap<>();

    public LoginPage() {
        SelenideElement email = $(By.id("email"));
        fields.put("email", email);

        SelenideElement password = $(By.xpath("//input[@title='Password']"));
        fields.put("password", password);
    }

    public LoginPage submit() {
        submitButton.click();
        return this;
    }

    public SelenideElement getErrorText() {
        return errorText;
    }

    public LoginPage fillForm(Map<String, String> inputValues) {
        fillForm(inputValues, fields);
        return this;
    }
}
