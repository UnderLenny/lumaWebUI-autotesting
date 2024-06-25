package tests;

import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.Test;
import dev.lenny.pages.HomePage;
import dev.lenny.pages.SubscribePage;

import java.util.HashMap;
import java.util.Map;

import static com.codeborne.selenide.Condition.text;

public class SubscribeTests extends BaseTest {
    HomePage homePage = new HomePage();
    SubscribePage subscribePage = new SubscribePage();

    @Test
    public void newsletterSubscriptionTest() {
        // Шаг 1. Переход на страницу подписки
        homePage.goToSubscribePage();

        // Шаг 2. Переключение на новое окно
        Selenide.switchTo().window(1);

        if(subscribePage.getModalWindow().isDisplayed()) {
            subscribePage.acceptModalWindow();
        }

        // Шаг 3. Ввод тестовых данных
        Map<String, String> inputValues = new HashMap<>();
        inputValues.put("email", "rovolis366@elahan.com");
        inputValues.put("firstName", "TestName");
        inputValues.put("lastName", "TestSurname");
        inputValues.put("companyName", "TestCompany");
        inputValues.put("position", "TestPosition");

        // Шаг 4. Заполнение формы подписки и отправка
        subscribePage
                .fillForm(inputValues)
                .clickSubscribeButton()
                .getSuccessMessage()
                .shouldHave(text("Thank you for subscribing!"));
    }
}
