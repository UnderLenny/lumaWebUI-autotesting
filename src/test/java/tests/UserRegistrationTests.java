package tests;

import com.codeborne.selenide.WebDriverRunner;
import org.junit.jupiter.api.Test;
import dev.lenny.pages.HomePage;
import dev.lenny.pages.RegistrationPage;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserRegistrationTests extends BaseTest {
    RegistrationPage registrationPage = new RegistrationPage();
    HomePage homePage = new HomePage();

    @Test
    public void testSuccessfulRegistration() {
        // Шаг 1: Заполнение данных для успешной регистрации
        Map<String, String> inputValues = new HashMap<>();
        inputValues.put("firstName", "TestName");
        inputValues.put("lastName", "TestSurname");
        inputValues.put("email", "0d556@kuzstu.ru");
        inputValues.put("password", "12345678qQ!");
        inputValues.put("confirmPassword", "12345678qQ!");

        // Шаг 2: Переход на страницу регистрации
        homePage.goToRegisterPage();

        // Шаг 3: Заполнение формы регистрации и отправка
        registrationPage
                .fillForm(inputValues)
                .submit();

        // Шаг 4: Проверка текущего URL
        String currentUrl = WebDriverRunner.getWebDriver().getCurrentUrl();
        String expectedUrl = "https://magento.softwaretestingboard.com/customer/account/";
        assertEquals(expectedUrl, currentUrl);
    }

    @Test
    public void testRegistrationWithExistingEmail() {
        // Шаг 1: Заполнение данных для регистрации с существующим email
        Map<String, String> inputValues = new HashMap<>();
        inputValues.put("firstName", "TestName");
        inputValues.put("lastName", "TestSurname");
        inputValues.put("email", "test@mail.ru");
        inputValues.put("password", "12345678qQ!");
        inputValues.put("confirmPassword", "12345678qQ!");

        // Шаг 2: Переход на страницу регистрации
        homePage.goToRegisterPage();

        // Шаг 3: Заполнение формы регистрации и отправка
        registrationPage
                .fillForm(inputValues)
                .submit();

        // Шаг 4: Проверка текста ошибки
        String actualText = registrationPage.getExistingEmailError().getText();
        String expectedText = "There is already an account with this email address. If you are sure that it is your email address, click here to get your password and access your account.";
        assertEquals(expectedText, actualText);
    }
}
