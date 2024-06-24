package tests;

import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.Test;
import pages.HomePage;
import pages.SubscribePage;

import java.util.HashMap;
import java.util.Map;

import static com.codeborne.selenide.Condition.text;

public class SubscribeTests extends BaseTest
{
    HomePage homePage = new HomePage();
    SubscribePage subscribePage = new SubscribePage();

    @Test
    public void newsletterSubscriptionTest() {
        homePage
                .goToSubscribePage();

        Selenide.switchTo().window(1);
        subscribePage
                .acceptModalWindow();
        Map<String, String> inputValues = new HashMap<>();
        inputValues.put("email", "rovolis366@elahan.com");
        inputValues.put("firstName", "TestName");
        inputValues.put("lastName", "TestSurname");
        inputValues.put("companyName", "TestCompany");
        inputValues.put("position", "TestPosition");

        subscribePage
                .fillForm(inputValues)
                .clickSubscribeButton()
                .getSuccessMessage()
                .shouldHave(text("Thank you for subscribing!"));
    }
}
