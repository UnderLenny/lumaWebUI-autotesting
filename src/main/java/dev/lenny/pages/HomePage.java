package dev.lenny.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class HomePage {
    private final SelenideElement subscribe = $x(("//a[contains(@href, 'subscribe')]"));
    private final SelenideElement loginButton =$x(("//a[contains(@href, 'customer/account/login/')]"));
    private final SelenideElement registerButton =$x(("//a[contains(@href, 'customer/account/create/')]"));

    private final SelenideElement gearCategory = $x(("//*[@id='ui-id-6']"));
    private final SelenideElement bagsSubcategory = $x(("//*[@id='ui-id-25']"));

    private final SelenideElement searchField = $x(("//input[@id='search']"));

    private final SelenideElement hotSellersText = $x("//h2[contains(@class, 'title')]");

    private final SelenideElement noResultsMessage = $x("//div[@class='message notice']");


    public RegistrationPage goToRegisterPage() {
        registerButton.click();
        return new RegistrationPage();
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
        Selenide.switchTo().window(1);
        return new SubscribePage();
    }

    public HomePage hoverOverGearCategory() {
        gearCategory.shouldBe(visible).hover();
        return this;
    }

    public ProductCatalogPage clickBagsSubcategory() {
        bagsSubcategory.shouldBe(Condition.visible).click();
        return new ProductCatalogPage();
    }

    public String getHotSellersText() {
        return hotSellersText.getText();
    }

    @Step("Открытие каталога товаров")
    public ProductCatalogPage openProductCatalog() {
        hoverOverGearCategory();
        clickBagsSubcategory();
        return new ProductCatalogPage();
    }

    @Step("Проверка поиска с несуществующим названием товаров")
    public HomePage searchInvalidProduct() {
        searchInvalidValue("testTest#");
        return new HomePage();
    }

    @Step("Получение сообщения о пустых результатах")
    public String getNoResultMessage() {
        return noResultsMessage.shouldBe(Condition.visible).getText();
    }
}
