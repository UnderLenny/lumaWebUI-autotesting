package tests;

import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Epic("Тестирование подписки на рассылку")
public class SubscribeTests extends BaseTest {
    @Feature("Тестирование подписки на рассылку")
    @Story("Пользователь подписывается на рассылку с валидными данными")
    @Severity(SeverityLevel.NORMAL)
    @Tag("positive")
    @Test
    @DisplayName("Тест подписки на рассылку")
    @Owner("Leonid Gevorgyan")
    @Description("Этот тест проверяет, что пользователь может успешно подписаться на рассылку с валидными данными.")
    public void newsletterSubscriptionTest() {
        subscribePage = homePage.goToSubscribePage();
        subscribePage.fillSubscribeForm();
        subscribePage.clickSubscribeButton();

        assertEquals("Almost finished... We need to confirm your email address. " +
                "To complete the subscription process, " +
                "please click the link in the email we just sent you.", subscribePage.getSuccessMessage());
    }
}