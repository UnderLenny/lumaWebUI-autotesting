package dev.lenny.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import java.util.HashMap;
import java.util.Map;

import static com.codeborne.selenide.Selenide.$;

public class RegistrationPage extends BasePage {
    private final SelenideElement firstName = $(By.id("firstname"));
    private final SelenideElement lastName = $(By.id("lastname"));
    private final SelenideElement email = $(By.id("email_address"));
    private final SelenideElement password = $(By.id("password"));
    private final SelenideElement confirmPassword = $(By.id("password-confirmation"));
    private final SelenideElement submitButton = $(By.xpath("//button[@title='Create an Account']"));
    private final SelenideElement errorText = $(By.xpath("//div[contains(@data-bind, 'text')]"));

    private final Map<String, SelenideElement> fields = new HashMap<>();

    public RegistrationPage() {
        fields.put("firstName", firstName);
        fields.put("lastName", lastName);
        fields.put("email", email);
        fields.put("password", password);
        fields.put("confirmPassword", confirmPassword);
    }

    public RegistrationPage submit() {
        submitButton.click();
        return this;
    }

    public SelenideElement getExistingEmailError() {
        return errorText.shouldHave(Condition.text("There is already an account with this email address. If you are sure that it is your email address, click here to get your password and access your account."));
    }

    public RegistrationPage fillForm(Map<String, String> inputValues) {
        fillForm(inputValues, fields);
        return this;
    }
}
