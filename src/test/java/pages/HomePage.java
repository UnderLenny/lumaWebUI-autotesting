package pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class HomePage {
    public SelenideElement getLoginButton() {
        return $(".authorization-link a");
    }
    private final SelenideElement email = $(By.xpath("//a[contains(@href, 'subscribe')]"));

    public void goToRegisterPage() {
        $("a[href*='customer/account/create/']").click();
    }

    public HomePage goToSubscribePage() {
        email.click();
        return this;
    }
}
