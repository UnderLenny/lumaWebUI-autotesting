package tests;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.WebDriverRunner;
import dev.lenny.helpers.ScreenshotUtils;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import dev.lenny.pages.HomePage;
import dev.lenny.pages.RegistrationPage;


import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Epic("Тестирование регистрации пользователя")
public class UserRegistrationTests extends BaseTest {
    RegistrationPage registrationPage = new RegistrationPage();
    HomePage homePage = new HomePage();

    @Feature("Тестирование регистрации пользователя")
    @Story("Пользователь должен успешно зарегистрироваться с валидными данными")
    @Severity(SeverityLevel.CRITICAL)
    @Tag("positive")
    @Test
    @DisplayName("Тест успешной регистрации пользователя")
    @Owner("Leonid Gevorgyan")
    @Description("Этот тест проверяет, что пользователь может успешно зарегистрироваться с валидными данными.")
    public void testSuccessfulRegistration() {
        Map<String, String> inputValues = new HashMap<>();
        inputValues.put("firstName", "TestName");
        inputValues.put("lastName", "TestSurname");
        inputValues.put("email", "test6@kuzstu.ru");
        inputValues.put("password", "12345678qQ!");
        inputValues.put("confirmPassword", "12345678qQ!");

        goToRegisterPage();
        fillFormAndSubmit(inputValues);
        checkCurrentUrl("https://magento.softwaretestingboard.com/customer/account/");
    }

    @Step("Переход на страницу регистрации")
    public void goToRegisterPage() {
        homePage.goToRegisterPage();
    }

    @Step("Заполнение формы регистрации и отправка")
    public void fillFormAndSubmit(Map<String, String> inputValues) {
        registrationPage
                .fillForm(inputValues)
                .submit();
    }

    @Step("Проверка текущего URL")
    public void checkCurrentUrl(String expectedUrl) {
        String currentUrl = WebDriverRunner.getWebDriver().getCurrentUrl();
        assertEquals(expectedUrl, currentUrl);
    }

    @Feature("Тестирование регистрации с существующим email")
    @Story("Регистрация с email, который уже существует в системе")
    @Severity(SeverityLevel.NORMAL)
    @Tag("negative")
    @Test
    @DisplayName("Тест регистрации с существующим email")
    @Description("Этот тест проверяет, что при регистрации с уже существующим email показывается соответствующее сообщение об ошибке.")
    public void testRegistrationWithExistingEmail() {
        Map<String, String> inputValues = new HashMap<>();
        inputValues.put("firstName", "TestName");
        inputValues.put("lastName", "TestSurname");
        inputValues.put("email", "test@mail.ru");
        inputValues.put("password", "12345678qQ!");
        inputValues.put("confirmPassword", "12345678qQ!");

        goToRegisterPage();
        fillFormAndSubmit(inputValues);
        checkErrorMessage();
    }
        @Step("Проверка текста ошибки")
        public void checkErrorMessage() {
            registrationPage.getExistingEmailError();

        }
    }

