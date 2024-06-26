package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import dev.lenny.helpers.Helpers;
import dev.lenny.helpers.ScreenshotUtils;
import dev.lenny.pages.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.chrome.ChromeOptions;
import io.qameta.allure.selenide.AllureSelenide;

import static com.codeborne.selenide.Selenide.*;

class BaseTest {
    HomePage homePage = new HomePage();
    Helpers helpers = new Helpers();
    CartPage cartPage;
    ChangeProfileDataPage changeProfileDataPage;
    LoginPage loginPage;
    PaymentPage paymentPage;
    ProductCatalogPage productCatalogPage;
    SubscribePage subscribePage;

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
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide().screenshots(true).savePageSource(false));
        open("/");
    }

    @AfterEach
    void teardown() {
        ScreenshotUtils.takeScreenshot();
        ScreenshotUtils.attachDomTree();
        closeWebDriver();
    }
}
