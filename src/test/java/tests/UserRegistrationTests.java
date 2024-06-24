package tests;

import com.codeborne.selenide.WebDriverRunner;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pages.RegistrationPage;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserRegistrationTests extends BaseTest{
    RegistrationPage registrationPage = new RegistrationPage();

    @Test
    public void testSuccessfulRegistration() {
        Map<String, String> inputValues = new HashMap<>();
        inputValues.put("firstName", "TestName");
        inputValues.put("lastName", "TestSurname");
        inputValues.put("email", "21t0553@kuzstu.ru");
        inputValues.put("password", "12345678qQ!");
        inputValues.put("confirmPassword", "12345678qQ!");

        registrationPage.open()
                .fillForm(inputValues)
                .submit();

        String currentUrl = WebDriverRunner.getWebDriver().getCurrentUrl();
        String expectedUrl = "https://magento.softwaretestingboard.com/customer/account/";
        assertEquals(expectedUrl, currentUrl);
    }

    @Test
    public void testRegistrationWithExistingEmail() {
        Map<String, String> inputValues = new HashMap<>();
        inputValues.put("firstName", "TestName");
        inputValues.put("lastName", "TestSurname");
        inputValues.put("email", "21t0553@kuzstu.ru");
        inputValues.put("password", "12345678qQ!");
        inputValues.put("confirmPassword", "12345678qQ!");

        registrationPage.open()
                .fillForm(inputValues)
                .submit();


        String actualText = registrationPage.getExistingEmailError().getText();
        String expectedText = "click here";
        assertEquals(expectedText, actualText);
    }
}
