package tests;

import com.codeborne.selenide.WebDriverRunner;
import org.junit.jupiter.api.Test;
import pages.LoginPage;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserLoginTests extends BaseTest {
    LoginPage loginPage = new LoginPage();

    @Test
    public void SuccessfulAuthenticationTest() {
        Map<String, String> inputValues = new HashMap<>();
        inputValues.put("email", "test@mail.ru");
        inputValues.put("password", "12345678dD");

        loginPage.open()
                .fillForm(inputValues)
                .submit();

        String currentUrl = WebDriverRunner.getWebDriver().getCurrentUrl();
        String expectedUrl = "https://magento.softwaretestingboard.com/customer/account/";
        assertEquals(expectedUrl, currentUrl);
    }

    @Test
    public void RegistrationWithInvalidPasswordTest() {
        Map<String, String> inputValues = new HashMap<>();
        inputValues.put("email", "test@mail.ru");
        inputValues.put("password", "invalid");

        loginPage.open()
                .fillForm(inputValues)
                .submit();
        String expectedText = "The account sign-in was incorrect or your account is disabled temporarily. Please wait and try again later.";
        String actualText = loginPage.getErrorText().getText();
        assertEquals(expectedText, actualText);
    }
}
