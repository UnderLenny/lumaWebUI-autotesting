package tests;

import com.codeborne.selenide.WebDriverRunner;
import org.junit.jupiter.api.Test;
import pages.HomePage;
import pages.RegistrationPage;

import java.util.HashMap;
import java.util.Map;

import static com.codeborne.selenide.Condition.text;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserRegistrationTests extends BaseTest {

    RegistrationPage registrationPage = new RegistrationPage();
    HomePage homePage = new HomePage();

    @Test
    public void testSuccessfulRegistration() {
        // Комментари шагов для Allure(на будущее)
        // Шаг 1: Подготовка данных для регистрации
        Map<String, String> inputValues = new HashMap<>();
        inputValues.put("firstName", "TestName");
        inputValues.put("lastName", "TestSurname");
        inputValues.put("email", "21t0556@kuzstu.ru");
        inputValues.put("password", "12345678qQ!");
        inputValues.put("confirmPassword", "12345678qQ!");

        // Шаг 2: Переход на страницу регистрации
        homePage.goToRegisterPage();

        // Шаг 3: Заполнение формы и отправка
        registrationPage
                .fillForm(inputValues)
                .submit();

        // Шаг 4: Проверка URL после успешной регистрации
        assertEquals("https://magento.softwaretestingboard.com/customer/account/", WebDriverRunner.getWebDriver().getCurrentUrl());
    }

    @Test
    public void testRegistrationWithExistingEmail() {
        // Шаг 1: Подготовка данных для регистрации с существующим email
        Map<String, String> inputValues = new HashMap<>();
        inputValues.put("firstName", "TestName");
        inputValues.put("lastName", "TestSurname");
        inputValues.put("email", "test@mail.ru");
        inputValues.put("password", "12345678qQ!");
        inputValues.put("confirmPassword", "12345678qQ!");

        // Шаг 2: Переход на страницу регистрации
        homePage.goToRegisterPage();

        // Шаг 3: Заполнение формы и отправка
        registrationPage
                .fillForm(inputValues)
                .submit();

        // Шаг 4: Проверка текста ошибки при существующем email
        assertEquals("There is already an account with this email address. If you are sure that it is your email address, click here to get your password and access your account.",
                registrationPage.getExistingEmailError().getText());
    }
}
