package dev.lenny.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import java.time.Duration;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

public class ProductCatalogPage {

    private final ElementsCollection prices = $$x("//div[@data-role='title']");
    private final SelenideElement priceRange = $x("//a[contains(@href, 'https://magento.softwaretestingboard.com/gear/bags.html?price=30-40')]");
    private final ElementsCollection products = $$x("//span[@data-price-type='finalPrice']/span");

    private final SelenideElement selectSortBy = $x("//*[@id='sorter']");
    private final SelenideElement sortOption = $x("//option[@value='price']");
    private final SelenideElement changePriceSort = $$x("//*[@title=\"Set Descending Direction\"]").get(0);
    private final SelenideElement productImage = $x(("//img[contains(@alt, 'Push')]"));
    private final SelenideElement addToCartButton = $x("//*[@id='product-addtocart-button']/span");

    @Step("Клик по фильтру цены")
    public void clickPriceFilter() {
        for (SelenideElement price : prices) {
            String desiredText = "PRICE";
            if (price.getText().equals(desiredText)) {
                price.click();
                break;
            }
        }
    }

    @Step("Клик по диапазону цен")
    public void clickPriceRange() {
        priceRange.shouldBe(Condition.clickable).click();
    }

    @Step("Получение цен товаров")
    public ElementsCollection getProductPrices() {
        return products;
    }

    @Step("Клик по списку сортировки")
    public ProductCatalogPage clickSortByList() {
        selectSortBy.click();
        sortOption.shouldBe(Condition.visible).click();
        return this;
    }

    @Step("Клик для установки цены по убыванию")
    public ProductCatalogPage clickToSetLowPrice() {
        changePriceSort.click();
        return this;
    }

    @Step("Нажатие на картинку карточки товара")
    public ProductPage clickOnProductCard() {
        productImage.click();
        return new ProductPage();
    }

    @Step("Проверка текста кнопки добавления в корзину")
    public void checkAddProductButtonText() {
        addToCartButton.shouldBe(Condition.text("Add to Cart"), Duration.ofSeconds(10));
    }

    @Step("Установка фильтра по цене")
    public ProductCatalogPage setPriceFilter() {
        clickPriceFilter();
        clickPriceRange();
        return this;
    }

    @Step("Проверка, все ли цены в пределах 30 и 39.99")
    public boolean arePricesInRange(ElementsCollection productPrices) {
        for (SelenideElement product : productPrices) {
            String productString = product.getText();
            String numberString = productString.replace("$", "");
            double number;
            try {
                number = Double.parseDouble(numberString);
            } catch (NumberFormatException e) {
                return false;
            }
            if (number < 30 || number > 39.99) {
                return false;
            }
        }
        return true;
    }

    @Step("Проверка цен товаров по возрастанию")
    public boolean arePricesAscending(ElementsCollection productPrices) {
        double previousPrice = 0;
        for (SelenideElement product : productPrices) {
            String productString = product.getText();
            String numberString = productString.replace("$", "");
            double number = Double.parseDouble(numberString);
            if (number < previousPrice) {
                return false;
            }
            previousPrice = number;
        }
        return true;
    }

    @Step("Проверка цен товаров по убыванию")
    public boolean arePricesDescending(ElementsCollection productPrices) {
        double previousPrice = Double.MAX_VALUE;
        for (SelenideElement product : productPrices) {
            String productString = product.getText();
            String numberString = productString.replace("$", "");
            double number = Double.parseDouble(numberString);
            if (number > previousPrice) {
                return false;
            }
            previousPrice = number;
        }
        return true;
    }

    @Step("Установка сортировки по цен по убыванию")
    public ProductCatalogPage setPriceSortDescending() {
        clickSortByList().clickToSetLowPrice();
        return new ProductCatalogPage();
    }

    @Step("Установка сортировки цен по возрастанию")
    public ProductCatalogPage setPriceSortAscending() {
        clickSortByList();
        return new ProductCatalogPage();
    }
}
