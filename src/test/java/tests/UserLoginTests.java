package tests;

import com.codeborne.selenide.WebDriverRunner;
import org.junit.jupiter.api.Test;
import pages.HomePage;
import pages.LoginPage;

import java.util.HashMap;
import java.util.Map;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.Wait;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserLoginTests extends BaseTest {

    LoginPage loginPage = new LoginPage();
    HomePage homePage = new HomePage();

    @Test
    public void successfulAuthenticationTest() {
        // Комментари шагов для Allure(на будущее)
        // Шаг 1: Подготовка данных для входа
        Map<String, String> inputValues = new HashMap<>();
        inputValues.put("email", "test@mail.ru");
        inputValues.put("password", "12345678dD");

        // Шаг 2: Переход на страницу входа
        homePage.goToLoginPage();

        // Шаг 3: Ввод данных и отправка формы
        loginPage
                .fillForm(inputValues)
                .submit();

        // Шаг 4: Проверка URL после успешного входа
        String currentUrl = WebDriverRunner.getWebDriver().getCurrentUrl();
        String expectedUrl = "https://magento.softwaretestingboard.com/";
        assertEquals(expectedUrl, currentUrl, "URL после авторизации не совпадает с ожидаемым");
    }

    @Test
    public void loginWithIncorrectPasswordTest() {
        // Шаг 1: Подготовка данных для входа с неверным паролем
        Map<String, String> inputValues = new HashMap<>();
        inputValues.put("email", "test@mail.ru");
        inputValues.put("password", "invalid");

        // Шаг 2: Переход на страницу входа
        homePage.goToLoginPage();

        // Шаг 3: Ввод данных и отправка формы
        loginPage
                .fillForm(inputValues)
                .submit();

        // Шаг 4: Проверка текста ошибки
        String expectedText = "The account sign-in was incorrect or your account is disabled temporarily. Please wait and try again later.";
        loginPage.getErrorText().shouldHave(text(expectedText));
    }
}
