package dev.lenny.pages;

import com.codeborne.selenide.Condition;
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

    private final SelenideElement profileArrow = $$x("/html/body/div[2]/header/div[1]/div/ul/li[2]/span/button").get(1);
    private final SelenideElement profileInfoButton = $(By.xpath("//a[contains(@href, 'customer/account/')]"));

    private final SelenideElement welcomeMessage = $$x("//*[@class='logged-in']").get(0);

    public void goToRegisterPage() {
        registerButton.click();
    }

    public LoginPage goToLoginPage() {
        loginButton.click();
        return new LoginPage();
    }

    public void searchInvalidValue(String value) {
        searchField.setValue(value).pressEnter();
    }

    public SubscribePage goToSubscribePage() {
        subscribe.click();
        return new SubscribePage();
    }

    public HomePage waitForPageToLoad() {
        profileArrow.shouldBe(visible, enabled);
        return this;
    }

    public HomePage clickOnProfileArrow() {
        profileArrow.click();
        return this;
    }

    public void waitForWelcomeMessage() {
        welcomeMessage.shouldBe(Condition.visible).shouldHave(Condition.text("Welcome"));
    }

    public HomePage goToProfilePage() {
        profileInfoButton.click();
        return this;
    }
}
