package tests;

import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Epic("Тестирование авторизации пользователя")
public class UserLoginTests extends BaseTest {

    @Feature("Тестирование авторизации пользователя с корректными данными")
    @Story("Пользователь должен успешно авторизоваться с валидными данными")
    @Severity(SeverityLevel.CRITICAL)
    @Tag("positive")
    @Test
    @DisplayName("Тест успешной авторизации пользователя")
    @Owner("Leonid Gevorgyan")
    @Description("Этот тест проверяет, что пользователь может успешно авторизоваться с валидными данными.")
    public void SuccessfulAuthenticationTest() {
        loginPage = homePage.goToLoginPage();
        loginPage.fillAuthForm();
        loginPage.submit();

        assertEquals("Hot Sellers", homePage.getHotSellersText());
    }

    @Feature("Тестирование авторизации пользователя с некорректным паролем")
    @Story("Пользователь должен получить ошибку из-за некорректных данных")
    @Severity(SeverityLevel.NORMAL)
    @Tag("negative")
    @Test
    @DisplayName("Тест авторизации c неверным паролем")
    @Owner("Leonid Gevorgyan")
    @Description("Этот тест проверяет, что пользователь не сможет войти в аккаунт с некорректным паролем.")
    public void loginWithIncorrectPasswordTest() {
        loginPage = homePage.goToLoginPage();
        loginPage.fillIncorrectAuthForm();
        loginPage.submit();

        assertEquals("The account sign-in was incorrect or your account is disabled temporarily." +
                " Please wait and try again later.", loginPage.getErrorText());
    }

}