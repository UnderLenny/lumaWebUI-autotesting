package tests;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

import pages.HomePage;
import pages.ProductCatalogPage;

public class ProductCatalogTests extends BaseTest {

    HomePage homePage = new HomePage();
    ProductCatalogPage productCatalogPage = new ProductCatalogPage();

    @Test
    public void setPriceFilter() {
        productCatalogPage
                .hoverOverGearCategory()
                .clickBagsSubcategory()
                .clickPriceFilter()
                .clickPriceRange();

        ElementsCollection productPrices = productCatalogPage.getProductPrices();
        for (SelenideElement product : productPrices) {
            String productString = product.getText();
            String numberString = productString.replace("$", "");
            double number = Double.parseDouble(numberString);
            assertTrue(number >= 30 && number <= 39.99, "Сумма товара должна быть между 30 и 39.99");
        }
    }

    @Test
    public void setSortByPrice() {
        productCatalogPage
                .hoverOverGearCategory()
                .clickBagsSubcategory()
                .clickSortByList();

        ElementsCollection productPrices = productCatalogPage.getProductPrices();
        boolean isNextPage = productCatalogPage.hasNextPage();
        double previousPrice = 0;
        while (isNextPage) {
            for (SelenideElement product : productPrices) {
                String productString = product.getText();
                String numberString = productString.replace("$", "");
                double number = Double.parseDouble(numberString);
                assertTrue(number >= previousPrice, "Цена товара должна быть больше или равна предыдущей");
                previousPrice = number;
            }
            if(isNextPage) {
                productCatalogPage.clickNextPage();
                productPrices = productCatalogPage.getProductPrices();
            }
            isNextPage = productCatalogPage.hasNextPage();
        }
    }

    @Test
    public void searchInvalidProduct() {
        homePage
                .searchInvalidValue("testTest#");

        productCatalogPage
                .getNoResultMessage();
    }
}
