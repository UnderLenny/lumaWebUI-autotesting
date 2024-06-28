package tests;

import com.codeborne.selenide.Configuration;
import dev.lenny.extensions.ResultAttacher;
import dev.lenny.helpers.Constants;
import dev.lenny.helpers.Helpers;
import dev.lenny.pages.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.chrome.ChromeOptions;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;

@ExtendWith(ResultAttacher.class)
class BaseTest {
    HomePage homePage = new HomePage();
    CartPage cartPage;
    LoginPage loginPage;
    RegistrationPage registrationPage;
    PaymentPage paymentPage;
    ProductCatalogPage productCatalogPage;
    ProductPage productPage;
    SubscribePage subscribePage;
    OrderPage orderPage;
    ProfilePage profilePage;

    @BeforeAll
    static void setupClass() {
        WebDriverManager.chromedriver().setup();
        Configuration.baseUrl = "https://magento.softwaretestingboard.com";
        Configuration.browser = "chrome";
        Configuration.pageLoadStrategy = "eager";
//        Configuration.headless = true;
        Configuration.holdBrowserOpen = true;
        // Настройка параметров браузера
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito"); // Запуск в режиме инкогнито
        options.addArguments("--disable-popup-blocking"); // Отключение блокировки всплывающих окон
        options.addArguments("--disable-extensions"); // Отключение расширений
        options.addArguments("--start-maximized"); // Максимизация окна браузера

        Configuration.browserCapabilities = options;    }

    @BeforeEach
    public void setupTest() {
        open("/");
    }

    @AfterEach
    void teardown() {
        closeWebDriver();
    }
}
