package pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import java.util.Map;

import static com.codeborne.selenide.Selenide.$;

public class RegistrationPage {
    private final SelenideElement firstName = $(By.xpath("//*[@id='firstname']"));
    private final SelenideElement lastName = $(By.xpath("//*[@id='lastname']"));
    private final SelenideElement email = $(By.xpath("//*[@id='email_address']"));
    private final SelenideElement password = $(By.xpath("//*[@id='password']"));
    private final SelenideElement confirmPassword = $(By.xpath("//*[@id='password-confirmation']"));
    private final SelenideElement submitButton = $(By.xpath("//button[@title='Create an Account']"));
    private final SelenideElement errorText = $(By.xpath("//div[contains(@data-bind, 'text')]"));

    public RegistrationPage setFirstName(String name) {
        firstName.setValue(name);
        return this;
    }

    public RegistrationPage setLastName(String surname) {
        lastName.setValue(surname);
        return this;
    }

    public RegistrationPage setEmail(String mail) {
        email.setValue(mail);
        return this;
    }

    public RegistrationPage setPassword(String pass) {
        password.setValue(pass);
        return this;
    }

    public RegistrationPage setConfirmPassword(String pass) {
        confirmPassword.setValue(pass);
        return this;
    }

    public RegistrationPage submit() {
        submitButton.click();
        return this;
    }

    public SelenideElement getExistingEmailError() {
        return errorText;
    }

    public RegistrationPage fillForm(Map<String, String> inputValues) {
        inputValues.forEach((key, value) -> {
            switch (key) {
                case "firstName":
                    setFirstName(value);
                    break;
                case "lastName":
                    setLastName(value);
                    break;
                case "email":
                    setEmail(value);
                    break;
                case "password":
                    setPassword(value);
                    break;
                case "confirmPassword":
                    setConfirmPassword(value);
                    break;
                default:
                    throw new AssertionError(key);
            }
        });
        return this;
    }
}
