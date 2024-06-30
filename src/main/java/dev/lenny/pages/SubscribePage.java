package dev.lenny.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;
import static dev.lenny.helpers.Constants.*;

public class SubscribePage {
    private final SelenideElement subscribeButton = $(By.xpath("//input[@value='Subscribe']"));
    private final SelenideElement successMessage = $(By.xpath("//div[@id='mce-success-response']"));
    private final SelenideElement emailInput = $x(("//input[@id='mce-EMAIL']"));
    private final SelenideElement firstNameInput = $x(("//input[@id='mce-FNAME']"));
    private final SelenideElement lastNameInput = $x(("//input[@id='mce-LNAME']"));
    private final SelenideElement companyNameInput = $x(("//input[@id='mce-COMPANY']"));
    private final SelenideElement positionInput = $x(("//input[@id='mce-POSITION']"));

    @Step("Заполнение формы подписки на рассылку")
    public void fillSubscribeForm() {
        emailInput.shouldBe(Condition.visible).scrollTo().setValue(SUBSCRIBE_EMAIL);
        firstNameInput.shouldBe(Condition.visible).setValue(FIRSTNAME);
        lastNameInput.shouldBe(Condition.visible).setValue(LASTNAME);
        companyNameInput.shouldBe(Condition.visible).setValue(COMPANY);
        positionInput.shouldBe(Condition.visible).setValue(STATE);
    }

    @Step("Нажатие на кнопку подписки на рассылку")
    public void clickSubscribeButton() {
        subscribeButton.shouldBe(Condition.visible).click();
    }

    public String getSuccessMessage() {
        return successMessage.shouldBe(Condition.visible).getText();
    }
}
