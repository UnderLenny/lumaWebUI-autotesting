package dev.lenny.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import static com.codeborne.selenide.Selenide.$x;
import static dev.lenny.helpers.Constants.*;

public class LoginPage {
    private final SelenideElement submitButton = $x(("//button[@class='action login primary']"));
    private final SelenideElement errorMessage = $x(("//div[contains(@data-bind, 'text')]"));
    private final SelenideElement emailInput = $x(("//input[@title='Email']"));
    private final SelenideElement passwordInput = $x(("//input[@title='Password']"));

    @Step("Заполнение формы авторизации(Email, пароль)")
    public void fillAuthForm() {
        emailInput.setValue(AUTH_EMAIL);
        passwordInput.setValue(CORRECT_PASSWORD);
    }

    @Step("Заполнение формы некорректными данными")
    public void fillIncorrectAuthForm() {
        emailInput.setValue(INCORRECT_EMAIL);
        passwordInput.setValue(INVALID_PASSWORD);
    }

    @Step("Нажатие на кнопку 'Sign In'")
    public HomePage submit() {
        submitButton.click();
        return new HomePage();
    }

    @Step("Получение текста ошибки")
    public String getErrorText() {
        return errorMessage.shouldBe(Condition.visible).getText();
    }

//    public void waitErrorText() {
//        errorMessage.should(Condition.exist, Duration.ofSeconds(13));
//        errorMessage.should(Condition.visible, Duration.ofSeconds(13));
//    }
}
