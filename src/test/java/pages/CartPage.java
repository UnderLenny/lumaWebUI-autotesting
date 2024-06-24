package pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class CartPage {
    private final SelenideElement productCard = $(By.xpath("//li[@class='item product product-item'][1]"));
    private final SelenideElement addToCartButton = $(By.xpath("//button[@title='Add to Cart']"));

    private final SelenideElement productName = $(By.xpath("//span[@class='base']"));
    private final SelenideElement productNameInCart = $(By.xpath("//strong[@class='product-item-name']"));
    private final SelenideElement cart = $(By.xpath("//a[contains(@href, 'cart')]"));

    private final SelenideElement productNumberInCart = $(By.xpath("//span[contains(@data-bind, 'block')]"));
    private final SelenideElement addedButton = $(By.xpath("//*[@title='Added']"));


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
        addToCartButton.click();
        return this;
    }

    public CartPage clickOnCart() {
        cart.click();
        return this;
    }

    public CartPage checkNumberOnCartButton() {
        productNumberInCart.shouldBe(text("1"));
        return this;
    }
}
