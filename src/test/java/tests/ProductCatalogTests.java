package tests;

import com.codeborne.selenide.ElementsCollection;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Epic("Тестирование каталога товаров")
public class ProductCatalogTests extends BaseTest {

    @Feature("Тестирование фильтрации товаров")
    @Story("Пользователь фильтрует товары по цене")
    @Severity(SeverityLevel.NORMAL)
    @Tag("positive")
    @Test
    @DisplayName("Тест фильтрации товаров по цене")
    @Owner("Leonid Gevorgyan")
    @Description("Этот тест проверяет, что пользователь может фильтровать товары по цене.")
    public void filterProductsByPriceRange() {
        productCatalogPage = homePage.openProductCatalog();
        productCatalogPage.setPriceFilter();
        ElementsCollection productPrices = productCatalogPage.getProductPrices();
        boolean pricesInRange = productCatalogPage.arePricesInRange(productPrices);
        assertTrue(pricesInRange, "Все цены товаров должны быть между 30 и 39.99");
    }

    @Feature("Тестирование сортировки товаров")
    @Story("Пользователь сортирует товары по цене")
    @Severity(SeverityLevel.NORMAL)
    @Tag("positive")
    @Test
    @DisplayName("Тест сортировки цены товаров по возрастанию")
    @Owner("Leonid Gevorgyan")
    @Description("Этот тест проверяет, что пользователь может сортировать товары по цене по возрастанию.")
    public void sortProductsByHighPrice() {
        productCatalogPage = homePage.openProductCatalog();
        productCatalogPage.setPriceSortAscending();
        ElementsCollection productPrices = productCatalogPage.getProductPrices();
        boolean pricesAscending = productCatalogPage.arePricesAscending(productPrices);
        assertTrue(pricesAscending, "Цены товаров должны быть отсортированы по возрастанию");
    }

    @Feature("Тестирование сортировки товаров")
    @Story("Пользователь сортирует товары по цене")
    @Severity(SeverityLevel.NORMAL)
    @Tag("positive")
    @Test
    @DisplayName("Тест сортировки цены товаров по убыванию")
    @Owner("Leonid Gevorgyan")
    @Description("Этот тест проверяет, что пользователь может сортировать товары по цене по убыванию.")
    public void sortProductsByLowPrice() {
        productCatalogPage = homePage.openProductCatalog();
        productCatalogPage.setPriceSortDescending();
        ElementsCollection productPrices = productCatalogPage.getProductPrices();
        boolean pricesDescending = productCatalogPage.arePricesDescending(productPrices);
        assertTrue(pricesDescending, "Цены товаров должны быть отсортированы по убыванию");
    }



    @Feature("Тестирование поиска товаров")
    @Story("Пользователь ищет товары по названию")
    @Severity(SeverityLevel.NORMAL)
    @Tag("negative")
    @Test
    @DisplayName("Тест поиска несуществующего товара")
    @Owner("Leonid Gevorgyan")
    @Description("Этот тест проверяет, реакцию сайта на поиск несуществующего товара.")
    public void searchForInvalidProduct() {
        homePage.searchInvalidProduct();
        assertEquals(homePage.getNoResultMessage(), "Your search returned no results.");
    }
}
