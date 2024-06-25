package dev.lenny.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.conditions.Visible;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$x;

public class HomePage {
    private final SelenideElement subscribe = $(By.xpath("//a[contains(@href, 'subscribe')]"));
    private final SelenideElement loginButton =$(By.xpath("//a[contains(@href, 'customer/account/login/')]"));
    private final SelenideElement registerButton =$(By.xpath("//a[contains(@href, 'customer/account/create/')]"));

    private final SelenideElement searchField = $(By.xpath("//input[@id='search']"));

    private final SelenideElement profileArrow = $$x("//*[@class='action switch']").get(0);
    private final SelenideElement profileInfoButton = $(By.xpath("//a[contains(@href, 'customer/account/')]"));
    public void goToRegisterPage() {
        registerButton.click();
    }

    public void goToLoginPage() {
        loginButton.click();
    }

    public void searchInvalidValue(String value) {
        searchField.setValue(value).pressEnter();
    }

    public HomePage goToSubscribePage() {
        subscribe.click();
        return this;
    }

    public HomePage waitForPageToLoad() {
        profileArrow.shouldBe(visible, enabled);
        return this;
    }

    public HomePage clickOnProfileArrow() {
        profileArrow.click();
        return this;
    }

    public HomePage goToProfilePage() {
        profileInfoButton.click();
        return this;
    }
}
