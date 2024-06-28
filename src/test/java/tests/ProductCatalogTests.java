//package base;
//
//import com.codeborne.selenide.ElementsCollection;
//import com.codeborne.selenide.SelenideElement;
//import io.qameta.allure.*;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Tag;
//import org.junit.jupiter.api.Test;
//import tests.BaseTest;
//
//import static org.junit.jupiter.api.Assertions.assertTrue;
//
//@Epic("Тестирование каталога товаров")
//public class ProductCatalogTests extends BaseTest {
//    @Feature("Тестирование фильтрации товаров")
//    @Story("Пользователь фильтрует товары по цене")
//    @Severity(SeverityLevel.NORMAL)
//    @Tag("positive")
//    @Test
//    @DisplayName("Тест фильтрации товаров по цене")
//    @Owner("Leonid Gevorgyan")
//    @Description("Этот тест проверяет, что пользователь может фильтровать товары по цене.")
//    public void filterProductsByPriceRange() {
//        openProductCatalog();
//        setPriceFilter();
//        ElementsCollection productPrices = productCatalogPage.getProductPrices();
//        checkProductPrices(productPrices);
//    }
//
//    @Step("Открытие каталога товаров")
//    public void openProductCatalog() {
//        productCatalogPage = homePage.hoverOverGearCategory();
//        productCatalogPage = homePage. clickBagsSubcategory();
//    }
//
//    @Step("Установка фильтра по цене")
//    public void setPriceFilter() {
//        productCatalogPage
//                .clickPriceFilter()
//                .clickPriceRange();
//    }
//
//    @Step("Проверка цен товаров")
//    public void checkProductPrices(ElementsCollection productPrices) {
//        for (SelenideElement product : productPrices) {
//            String productString = product.getText();
//            String numberString = productString.replace("$", "");
//            double number = Double.parseDouble(numberString);
//            assertTrue(number >= 30 && number <= 39.99, "Сумма товара должна быть между 30 и 39.99");
//        }
//    }
//
//    @Feature("Тестирование сортировки товаров")
//    @Story("Пользователь сортирует товары по цене")
//    @Severity(SeverityLevel.NORMAL)
//    @Tag("positive")
//    @Test
//    @DisplayName("Тест сортировки цены товаров по возрастанию")
//    @Owner("Leonid Gevorgyan")
//    @Description("Этот тест проверяет, что пользователь может сортировать товары по цене по возрастанию.")
//    public void sortProductsByHighPrice() {
//        openProductCatalog();
//        setPriceSortAscending();
//
//        ElementsCollection productPrices = productCatalogPage.getProductPrices();
//        checkProductPricesByAscending(productPrices);
//    }
//
//    @Step("Установка сортировки цены по возрастанию")
//    public void setPriceSortAscending() {
//        productCatalogPage
//                .clickSortByList();
//    }
//
//    @Step("Проверка цен товаров по возрастанию")
//    public void checkProductPricesByAscending(ElementsCollection productPrices) {
//        double previousPrice = 0;
//        for (SelenideElement product : productPrices) {
//            String productString = product.getText();
//            String numberString = productString.replace("$", "");
//            double number = Double.parseDouble(numberString);
//            assertTrue(number >= previousPrice, "Цена товара должна быть больше или равна предыдущей");
//            previousPrice = number;
//        }
//    }
//
//    @Feature("Тестирование сортировки товаров")
//    @Story("Пользователь сортирует товары по цене")
//    @Severity(SeverityLevel.NORMAL)
//    @Tag("positive")
//    @Test
//    @DisplayName("Тест сортировки цены товаров по убыванию")
//    @Owner("Leonid Gevorgyan")
//    @Description("Этот тест проверяет, что пользователь может сортировать товары по цене по убыванию.")
//    public void sortProductsByLowPrice() {
//        openProductCatalog();
//        setPriceSortDescending();
//        ElementsCollection productPrices = productCatalogPage.getProductPrices();
//        checkProductPricesByDescending(productPrices);
//    }
//
//    @Step("Установка сортировки по цене по убыванию")
//    public void setPriceSortDescending() {
//        productCatalogPage
//                .clickSortByList()
//                .clickToSetLowPrice();
//    }
//
//    @Step("Проверка цен товаров по убыванию")
//    public void checkProductPricesByDescending(ElementsCollection productPrices) {
//        double previousPrice = Double.MAX_VALUE;
//        for (SelenideElement product : productPrices) {
//            String productString = product.getText();
//            String numberString = productString.replace("$", "");
//            double number = Double.parseDouble(numberString);
//            assertTrue(number <= previousPrice, "Цена товара должна быть меньше или равна предыдущей");
//            previousPrice = number;
//        }
//    }
//
//    @Feature("Тестирование поиска товаров")
//    @Story("Пользователь ищет товары по названию")
//    @Severity(SeverityLevel.NORMAL)
//    @Tag("negative")
//    @Test
//    @DisplayName("Тест поиска несуществующего товара")
//    @Owner("Leonid Gevorgyan")
//    @Description("Этот тест проверяет, реакцию сайта на поиск несуществующего товара.")
//    public void searchForInvalidProduct() {
//        searchInvalidProduct();
//        checkSearchResults();
//    }
//
//    @Step("Проверка поиска с несуществующим названием товаров")
//    public void searchInvalidProduct() {
//        homePage
//                .searchInvalidValue("testTest#");
//    }
//
//    @Step("Проверка результатов поиска")
//    public void checkSearchResults() {
//        productCatalogPage
//                .getNoResultMessage();
//    }
//}
