package pages;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.$;
import org.openqa.selenium.By;

public class PaymentPage {
    private final SelenideElement shippingMethodInput = $(By.xpath("//input[@name='ko_unique_1']"));
}
