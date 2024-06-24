package pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import java.util.Map;

import static com.codeborne.selenide.Selenide.$;

public class LoginPage {
    private final SelenideElement email = $("#email");
    private final SelenideElement password = $("#pass");
    private final SelenideElement submitButton = $("#send2");
    private final SelenideElement errorText = $("div[data-bind='html: $parent.prepareMessageForHtml(message.text)']");


    public LoginPage open() {
        Selenide.open("https://magento.softwaretestingboard.com/customer/account/login");
        return this;
    }

    public LoginPage enterEmail(String mail) {
        email.setValue(mail);
        return this;
    }

    public LoginPage enterPassword(String pass) {
        password.setValue(pass);
        return this;
    }

    public LoginPage submit() {
        submitButton.click();
        return this;
    }

    public SelenideElement getErrorText() {
        return errorText;
    }

    public LoginPage fillForm(Map<String, String> inputValues) {
        inputValues.forEach((key, value) -> {
            switch (key) {
                case "email":
                    enterEmail(value);
                    break;
                case "password":
                    enterPassword(value);
                    break;
                default:
                    throw new AssertionError(key);
            }
        });
        return this;
    }
}
