package tests;

import com.codeborne.selenide.Configuration;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.chrome.ChromeOptions;

import static com.codeborne.selenide.Selenide.*;

class BaseTest {

    @BeforeAll
    static void setupClass() {
        WebDriverManager.chromedriver().setup();
        Configuration.baseUrl = "https://magento.softwaretestingboard.com";
        Configuration.browser = "chrome";
        Configuration.browserSize="2560x1600";
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
