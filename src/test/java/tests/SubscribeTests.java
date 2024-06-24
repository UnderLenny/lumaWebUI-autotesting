package tests;

import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.Test;
import pages.HomePage;
import pages.SubscribePage;

import java.util.HashMap;
import java.util.Map;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.switchTo;

public class SubscribeTests extends BaseTest {

    HomePage homePage = new HomePage();
    SubscribePage subscribePage = new SubscribePage();

    @Test
    public void newsletterSubscriptionTest() {
        // Комментари шагов для Allure(на будущее)
        // Шаг 1: Переход на страницу подписки
        homePage.goToSubscribePage();

        // Шаг 2: Переключение на новое окно
        switchTo().window(1);

        // Шаг 3: Заполнение формы подписки
        Map<String, String> inputValues = new HashMap<>();
        inputValues.put("email", "rovolis366@elahan.com");
        inputValues.put("firstName", "TestName");
        inputValues.put("lastName", "TestSurname");
        inputValues.put("companyName", "TestCompany");
        inputValues.put("position", "TestPosition");

        subscribePage
                .fillForm(inputValues)
                .clickSubscribeButton();

        // Шаг 4: Проверка успешного сообщения
        subscribePage.getSuccessMessage().shouldHave(text("Thank you for subscribing!"));
    }
}
