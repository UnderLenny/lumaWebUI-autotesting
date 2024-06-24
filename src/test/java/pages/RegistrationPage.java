package pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import java.util.Map;

import static com.codeborne.selenide.Selenide.$;

public class RegistrationPage {
    private final SelenideElement firstName = $("#firstname");
    private final SelenideElement lastName = $("#lastname");
    private final SelenideElement email = $("#email_address");
    private final SelenideElement password = $("#password");
    private final SelenideElement confirmPassword = $("#password-confirmation");
    private final SelenideElement submitButton = $("button[title='Create an Account']");
    private final SelenideElement errorText = $("div[data-bind='html: $parent.prepareMessageForHtml(message.text)'] a");

    public RegistrationPage open() {
        Selenide.open("https://magento.softwaretestingboard.com/customer/account/create/");
        return this;
    }

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
