package pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class ProductCatalogPage {

    private final SelenideElement gearCategory = $("#ui-id-6");
    private final SelenideElement bagsSubcategory = $("#ui-id-25");
    private final ElementsCollection prices = $$("div[data-role='title']");
    private final SelenideElement priceRange = $("main > div:nth-of-type(3) > div:nth-of-type(2) > div > div:nth-of-type(2) > div > div:nth-of-type(8) > div:nth-of-type(2) > ol > li:nth-of-type(2) > a");
    private final ElementsCollection products = $$("span[data-price-type='finalPrice'] span");

    public ProductCatalogPage hoverOverGearCategory() {
        gearCategory.hover();
        return this;
    }

    public ProductCatalogPage clickBagsSubcategory() {
        bagsSubcategory.click();
        return this;
    }

    public ProductCatalogPage clickPriceFilter() {
        for (SelenideElement price : prices) {
            String desiredText = "PRICE";
            if (price.getText().equals(desiredText)) {
                price.click();
                break;
            }
        }
        return this;
    }

    public ProductCatalogPage clickPriceRange() {
        priceRange.click();
        return this;
    }

    public ElementsCollection getProductPrices() {
        return products;
    }

}
