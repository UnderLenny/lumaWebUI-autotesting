package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;

import static com.codeborne.selenide.Selenide.*;

public class ProductCatalogPage {

    private final SelenideElement gearCategory = $(By.xpath("//*[@id='ui-id-6']"));
    private final SelenideElement bagsSubcategory = $(By.xpath("//*[@id='ui-id-25']"));
    private final ElementsCollection prices = $$(By.xpath("//div[@data-role='title']"));
    private final SelenideElement priceRange = $(By.xpath("//a[@href='https://magento.softwaretestingboard.com/gear/bags.html?price=30-40']"));
    private final ElementsCollection products = $$(By.xpath("//span[@data-price-type='finalPrice']/span"));

    private final SelenideElement selectSortBy = $(By.xpath("//*[@id=\"sorter\"]"));
    private final SelenideElement nextPage = $$x("//*[@title='Next']").get(1); // временное решение

    private final SelenideElement changePriceSort = $$x("//*[@title=\"Set Descending Direction\"]").get(0);
    private final SelenideElement noResultsMessage = $(By.xpath("//div[@class='message notice']"));


    public ProductCatalogPage hoverOverGearCategory() {
        gearCategory.hover();
        return this;
    }

    public ProductCatalogPage clickBagsSubcategory() {
        bagsSubcategory.shouldBe(Condition.visible).click();
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

    public ProductCatalogPage clickSortByList() {
        selectSortBy.selectOptionByValue("price");
        return this;
    }

    public ProductCatalogPage clickToSetLowPrice() {
        changePriceSort.click();
        return this;
    }
//    public boolean hasNextPage() {
//        try {
//            nextPage.shouldBe(Condition.visible);
//            return nextPage.isDisplayed();
//        } catch (NoSuchElementException e) {
//            return false;
//        }
//    }

    public ProductCatalogPage clickNextPage() {
        nextPage.click();
        return this;
    }

    public ProductCatalogPage getNoResultMessage() {
        noResultsMessage.shouldBe(Condition.visible);
        return this;
    }
}
