package tests;

import com.codeborne.selenide.WebDriverRunner;
import org.junit.jupiter.api.Test;
import dev.lenny.pages.HomePage;
import dev.lenny.pages.LoginPage;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserLoginTests extends BaseTest {
    LoginPage loginPage = new LoginPage();
    HomePage homePage = new HomePage();

    @Test
    public void SuccessfulAuthenticationTest() {
        // Шаг 1: Заполнение данных для аутентификации
        Map<String, String> inputValues = new HashMap<>();
        inputValues.put("email", "test@mail.ru");
        inputValues.put("password", "12345678dD");

        // Шаг 2: Переход на страницу логина
        homePage.goToLoginPage();

        // Шаг 3: Заполнение формы логина и отправка
        loginPage
                .fillForm(inputValues)
                .submit();

        // Шаг 4: Проверка текущего URL
        String currentUrl = WebDriverRunner.getWebDriver().getCurrentUrl();
        String expectedUrl = "https://magento.softwaretestingboard.com/";
        assertEquals(expectedUrl, currentUrl);
    }

    @Test
    public void loginWithIncorrectPasswordTest() {
        // Шаг 1: Заполнение данных для логина с неправильным паролем
        Map<String, String> inputValues = new HashMap<>();
        inputValues.put("email", "test@mail.ru");
        inputValues.put("password", "invalid");

        // Шаг 2: Переход на страницу логина
        homePage.goToLoginPage();

        // Шаг 3: Заполнение формы логина и отправка
        loginPage
                .fillForm(inputValues)
                .submit();

        // Шаг 4: Проверка текста ошибки
        String expectedText = "The account sign-in was incorrect or your account is disabled temporarily. Please wait and try again later.";
        String actualText = loginPage.getErrorText().getText();
        assertEquals(expectedText, actualText);
    }
}
