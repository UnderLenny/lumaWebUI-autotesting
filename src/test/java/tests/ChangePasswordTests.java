package tests;

import com.codeborne.selenide.WebDriverRunner;
import dev.lenny.pages.ChangeProfileDataPage;
import dev.lenny.pages.HomePage;
import dev.lenny.pages.LoginPage;
import dev.lenny.pages.ProfilePage;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ChangePasswordTests extends BaseTest {
    ProfilePage profilePage = new ProfilePage();
    ChangeProfileDataPage changeProfileDataPage = new ChangeProfileDataPage();
    LoginPage loginPage = new LoginPage();
    HomePage homePage = new HomePage();

    @Test
    public void changePasswordTest() {

        // Шаг 1: Заполнение данных для аутентификации
        Map<String, String> inputValues = new HashMap<>();
        inputValues.put("email", "test@mail.ru");
        inputValues.put("password", "12345678dQ");

        // Шаг 2: Переход на страницу логина
        homePage.goToLoginPage();

        // Шаг 3: Заполнение формы логина и отправка
        loginPage
                .fillForm(inputValues)
                .submit();

        // Шаг 4: Переход на страницу Управления аккаунтом
        homePage.clickOnProfileArrow().goToProfilePage();

        // Шаг 5: Очистка предыдущих значений формы
        inputValues.clear();

        // Шаг 6: Заполнение данных для смены пароля
        inputValues.put("currentPasswordField", "12345678dQ");
        inputValues.put("newPasswordField", "12345678dD");
        inputValues.put("confirmPasswordField", "12345678dD");

        // Шаг 7: Заполнение формы и отправка
        profilePage.clickChangePasswordButton();
        changeProfileDataPage.fillForm(inputValues);
        changeProfileDataPage.submit();

        // Шаг 8: Очистка предыдущих значений формы
        inputValues.clear();

        // Шаг 9: Заполнение новых данных для аутентификации
        inputValues.put("email", "test@mail.ru");
        inputValues.put("password", "12345678dD");

        // Шаг 10: Заполнение формы и отправка
        loginPage
                .fillForm(inputValues)
                .submit();

        // Шаг 8: Проверка текущего URL
        String currentUrl = WebDriverRunner.getWebDriver().getCurrentUrl();
        String expectedUrl = "https://magento.softwaretestingboard.com/customer/account/";
        assertEquals(expectedUrl, currentUrl);
    }
}
