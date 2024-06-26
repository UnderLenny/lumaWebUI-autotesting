package tests;

import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import dev.lenny.pages.HomePage;
import dev.lenny.pages.LoginPage;

import java.util.HashMap;
import java.util.Map;

@Epic("Тестирование авторизации пользователя")
public class UserLoginTests extends BaseTest {
    LoginPage loginPage = new LoginPage();
    HomePage homePage = new HomePage();

    @Feature("Тестирование авторизации пользователя с корректными данными")
    @Story("Пользователь должен успешно авторизоваться с валидными данными")
    @Severity(SeverityLevel.CRITICAL)
    @Tag("positive")
    @Test
    @DisplayName("Тест успешной авторизации пользователя")
    @Owner("Leonid Gevorgyan")
    @Description("Этот тест проверяет, что пользователь может успешно авторизоваться с валидными данными.")
    public void SuccessfulAuthenticationTest() {
        Map<String, String> inputValues = new HashMap<>();
        inputValues.put("email", "test@mail.ru");
        inputValues.put("password", "12345678dD");

        goToLoginPage();
        fillFormAndSubmit(inputValues);
        checkCurrentUrl();
    }

    @Step("Переход на страницу логина")
    public void goToLoginPage() {
        loginPage = homePage.goToLoginPage();
    }

    @Step("Заполнение формы авторизации и отправка")
    public void fillFormAndSubmit(Map<String, String> inputValues) {
        loginPage
                .fillForm(inputValues)
                .submit();
    }

    @Step("Проверка текущего Url")
    public void checkCurrentUrl() {
        String expectedBaseUrl = "https://magento.softwaretestingboard.com/";
        helpers.checkCurrentUrl(expectedBaseUrl);
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
        Map<String, String> inputValues = new HashMap<>();
        inputValues.put("email", "test@mail.ru");
        inputValues.put("password", "invalid");

        goToLoginPage();
        fillFormAndSubmit(inputValues);
        checkError();
    }

    @Step("Проверка текста ошибки")
    public void checkError() {
        String expectedText = "The account sign-in was incorrect or your account is disabled temporarily. Please wait and try again later.";
        String actualText = loginPage.getErrorText();
        System.out.println(actualText);
        helpers.checkErrorText(expectedText, actualText);
    }
}