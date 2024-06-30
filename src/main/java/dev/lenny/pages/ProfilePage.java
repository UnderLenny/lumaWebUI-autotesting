package dev.lenny.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.$x;

public class ProfilePage {
    private final SelenideElement successRegistrationMessage = $x("//div[contains(@data-bind, 'html:')]");

    public String getSuccessRegistrationMessage() {
         return successRegistrationMessage.shouldBe(Condition.visible).getText();
    }
}
