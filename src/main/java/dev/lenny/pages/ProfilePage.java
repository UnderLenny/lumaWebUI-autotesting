package dev.lenny.pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class ProfilePage {
    private final SelenideElement changePasswordButton = $(By.xpath("//a[contains(@class, 'change-password')]"));

    public ProfilePage clickChangePasswordButton() {
        changePasswordButton.click();
        return this;
    }
}
