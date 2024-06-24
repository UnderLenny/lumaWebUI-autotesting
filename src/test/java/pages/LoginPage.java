package pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import java.util.Map;

import static com.codeborne.selenide.Selenide.$;

public class LoginPage {
    private final SelenideElement email = $(By.xpath("//*[@id='email']"));
    private final SelenideElement password = $(By.xpath("//input[@title='Password']"));
    private final SelenideElement submitButton = $(By.xpath("//button[@class='action login primary']"));
    private final SelenideElement errorText = $(By.xpath("//div[contains(@data-bind, 'text')]"));


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
