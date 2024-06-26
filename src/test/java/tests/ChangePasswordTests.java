package tests;

import com.codeborne.selenide.WebDriverRunner;
import dev.lenny.pages.ChangeProfileDataPage;
import dev.lenny.pages.HomePage;
import dev.lenny.pages.LoginPage;
import dev.lenny.pages.ProfilePage;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Epic("Тестирование смены пароля")
public class ChangePasswordTests extends BaseTest {
    ProfilePage profilePage = new ProfilePage();
    ChangeProfileDataPage changeProfileDataPage = new ChangeProfileDataPage();
    LoginPage loginPage = new LoginPage();
    HomePage homePage = new HomePage();

    private  String oldPassword = "12345678dD";
    private String newPassword = "12345678dD";

    @Feature("Тестирование смены пароля c корректными данными")
    @Story("Пользователь должен успешно сменить пароль")
    @Severity(SeverityLevel.CRITICAL)
    @Tag("positive")
    @Test
    @DisplayName("Тест смены пароля")
    @Owner("Leonid Gevorgyan")
    @Description("Этот тест проверяет, что пользователь может успешно сменить пароль.")
    public void changePasswordTest() {
        Map<String, String> inputValues = new HashMap<>();
        prepareLoginData(inputValues);

        login(inputValues);
        homePage.clickOnProfileArrow();
        goToProfilePage();
        inputValues.clear();

        inputValues.put("currentPasswordField", oldPassword);
        inputValues.put("newPasswordField", newPassword);
        inputValues.put("confirmPasswordField", newPassword);

        changePassword(inputValues);
        inputValues.clear();

        inputValues.put("email", "test23@mail.ru");
        inputValues.put("password", newPassword);

        login(inputValues);
        checkUrl();
    }

    @Step("Подготовка данных для входа")
    public void prepareLoginData(Map<String, String> inputValues) {

        inputValues.put("email", "test23@mail.ru");
        inputValues.put("password", oldPassword);
    }

    @Step("Авторизация пользователя")
    public void login(Map<String, String> inputValues) {
        homePage.goToLoginPage();
        loginPage
                .fillForm(inputValues)
                .submit();
    }
    @Step("Переход на страницу личного кабинета пользователя")
    public void goToProfilePage() {
        homePage.goToProfilePage();
    }

    @Step("Смена пароля")
    public void changePassword(Map<String, String> inputValues) {
        profilePage.clickChangePasswordButton();
        changeProfileDataPage.fillForm(inputValues);
        changeProfileDataPage.submit();
    }

    @Step("Проверка URL")
    public void checkUrl() {
        String currentUrl = WebDriverRunner.getWebDriver().getCurrentUrl();
        String expectedUrl = "https://magento.softwaretestingboard.com/customer/account/";
        assertEquals(expectedUrl, currentUrl);
    }
}
