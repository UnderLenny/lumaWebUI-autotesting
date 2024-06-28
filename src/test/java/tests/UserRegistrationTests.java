package tests;

import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Epic("Тестирование регистрации пользователя")
public class UserRegistrationTests extends BaseTest {

    @Feature("Тестирование регистрации пользователя")
    @Story("Пользователь должен успешно зарегистрироваться с валидными данными")
    @Severity(SeverityLevel.CRITICAL)
    @Tag("positive")
    @Test
    @DisplayName("Тест успешной регистрации пользователя")
    @Owner("Leonid Gevorgyan")
    @Description("Этот тест проверяет, что пользователь может успешно зарегистрироваться с валидными данными.")
    public void testSuccessfulRegistration() {
        registrationPage = homePage.goToRegisterPage();
        registrationPage.fillRegistrationForm();
        registrationPage.fillEmailInForm();
        profilePage = registrationPage.submit();

        assertEquals("Thank you for registering with Main Website Store.", profilePage.getSuccessRegistrationMessage());
    }


    @Feature("Тестирование регистрации с существующим email")
    @Story("Регистрация с email, который уже существует в системе")
    @Severity(SeverityLevel.NORMAL)
    @Tag("negative")
    @Test
    @DisplayName("Тест регистрации с существующим email")
    @Description("Этот тест проверяет, что при регистрации с уже существующим email показывается соответствующее сообщение об ошибке.")
    public void testRegistrationWithExistingEmail() {
        registrationPage = homePage.goToRegisterPage();
        registrationPage.fillRegistrationForm();
        registrationPage.fillIncorrectEmailInForm();
        registrationPage.submit();

        assertEquals("There is already an account with this email address. " +
                "If you are sure that it is your email address, " +
                "click here to get your password and access your account.", registrationPage.getExistingEmailError());
    }
}

