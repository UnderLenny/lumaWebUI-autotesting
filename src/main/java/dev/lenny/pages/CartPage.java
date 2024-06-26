package dev.lenny.pages;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

import java.time.Duration;

public class CartPage {
    private final SelenideElement productCard = $(By.xpath("//li[@class='item product product-item'][1]"));
    private final SelenideElement addToCartButton = $(By.xpath("//button[@title='Add to Cart']"));

    private final SelenideElement productName = $(By.xpath("//span[@class='base']"));
    private final SelenideElement productNameInCart = $(By.xpath("//strong[@class='product-item-name']"));
    private final SelenideElement cart = $(By.xpath("//a[contains(@data-bind, 'minicart')]"));

    private final SelenideElement productNumberInCart = $(By.xpath("//span[contains(@data-bind, 'block')]"));
    private final SelenideElement proceedToCheckoutButton = $(By.xpath("//*[@id='top-cart-btn-checkout']"));

    private final SelenideElement successAddToCartMessage= $x("//div[contains(@class, 'success')]");


    public SelenideElement getProductCard() {
        return productCard;
    }

    public String getProductName() {
        return productName.getText();
    }

    public String getProductNameInCart() {
        return productNameInCart.getText();
    }

    public CartPage clickOnProductCard() {
        productCard.click();
        return this;
    }

    public CartPage addProductToCart() {
        addToCartButton.shouldBe(exist).click();
        return this;
    }

    public CartPage clickOnCart() {
        cart.shouldBe(exist).click();
        return this;
    }

    public CartPage clickOnProceedButton() {
        proceedToCheckoutButton.shouldBe(exist).click();
        return new CartPage();
    }

    public CartPage checkSuccessAddToCartMessage() {
        successAddToCartMessage.shouldBe(visible).isDisplayed();
        return new CartPage();
    }

}
