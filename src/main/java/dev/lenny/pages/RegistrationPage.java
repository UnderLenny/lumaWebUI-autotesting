package dev.lenny.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import java.util.HashMap;
import java.util.Map;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;
import static dev.lenny.helpers.Constants.*;

public class RegistrationPage {
    private final SelenideElement firstNameInput = $(By.id("firstname"));
    private final SelenideElement lastNameInput = $(By.id("lastname"));
    private final SelenideElement emailInput = $(By.id("email_address"));
    private final SelenideElement passwordInput = $(By.id("password"));
    private final SelenideElement confirmPasswordInput = $(By.id("password-confirmation"));
    private final SelenideElement submitButton = $x(("//button[@title='Create an Account']"));
    private final SelenideElement errorText = $x(("//div[contains(@data-bind, 'text')]"));

    @Step("Заполнение формы регистрации(Имя, фамилия, пароль)")
    public void fillRegistrationForm() {
        firstNameInput.setValue(FIRSTNAME);
        lastNameInput.setValue(LASTNAME);
        emailInput.setValue(NEW_EMAIL);
        passwordInput.setValue(PASSWORD);
        confirmPasswordInput.setValue(PASSWORD);
    }

    @Step("Ввод правильной почты")
    public void fillEmailInForm() {
        emailInput.setValue(NEW_EMAIL);
    }

    @Step("Ввод неправильной почты")
    public void fillIncorrectEmailInForm() {
        emailInput.setValue(INCORRECT_EMAIL);
    }

    @Step("Нажатие на кнопку 'Create an Account'")
    public ProfilePage submit() {
        submitButton.click();
        return new ProfilePage();
    }

    @Step("Проверка сообщения об ошибке регистрации")
    public String getExistingEmailError() {
        return errorText.shouldBe(Condition.visible).getText();
    }

}
