package tests;

import com.codeborne.selenide.Selenide;
import dev.lenny.helpers.ScreenshotUtils;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import dev.lenny.pages.HomePage;
import dev.lenny.pages.SubscribePage;

import java.util.HashMap;
import java.util.Map;

import static com.codeborne.selenide.Condition.text;

@Epic("Тестирование подписки на рассылку")
public class SubscribeTests extends BaseTest {
    HomePage homePage = new HomePage();
    SubscribePage subscribePage = new SubscribePage();

    @Feature("Тестирование подписки на рассылку")
    @Story("Пользователь подписывается на рассылку с валидными данными")
    @Severity(SeverityLevel.NORMAL)
    @Tag("positive")
    @Test
    @DisplayName("Тест подписки на рассылку")
    @Owner("Leonid Gevorgyan")
    @Description("Этот тест проверяет, что пользователь может успешно подписаться на рассылку с валидными данными.")
    public void newsletterSubscriptionTest() {
        Map<String, String> inputValues = new HashMap<>();
        prepareLoginData(inputValues);

        goToSubscribePage();
        Selenide.switchTo().window(1);
        fillAndSubmitForm(inputValues);
        checkSubscribe();
        ScreenshotUtils.takeScreenshot();

//        if(subscribePage.getModalWindow().isDisplayed()) {
//            subscribePage.acceptModalWindow();
//        }

    }

    @Step("Подготовка данных для подписки на рассылку")
    public void prepareLoginData(Map<String, String> inputValues) {
        inputValues.put("email", "test@elahan.com");
        inputValues.put("firstName", "TestName");
        inputValues.put("lastName", "TestSurname");
        inputValues.put("companyName", "TestCompany");
        inputValues.put("position", "TestPosition");
    }

    @Step("Переход на страницу рассылки")
    public void goToSubscribePage() {
        homePage.goToSubscribePage();
    }

    @Step("Заполнение и отправка формы")
    public void fillAndSubmitForm(Map<String, String> inputValues) {
        subscribePage.fillForm(inputValues).clickSubscribeButton();
    }

    @Step("Проверка подписки на рассылку")
    public void checkSubscribe() {
        subscribePage.getSuccessMessage().shouldHave(text("Almost finished... " +
                "We need to confirm your email address. " +
                "To complete the subscription process, please click the link in the email we just sent you."));
    }

}
