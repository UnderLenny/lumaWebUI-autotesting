package dev.lenny.pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class HomePage {
    private final SelenideElement subscribe = $(By.xpath("//a[contains(@href, 'subscribe')]"));
    private final SelenideElement loginButton =$(By.xpath("//a[contains(@href, 'customer/account/login/')]"));
    private final SelenideElement registerButton =$(By.xpath("//a[contains(@href, 'customer/account/create/')]"));

    private final SelenideElement searchField = $(By.xpath("//input[@id='search']"));

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
}
