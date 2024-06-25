package tests;

import com.codeborne.selenide.Configuration;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import static com.codeborne.selenide.Selenide.*;

class BaseTest {

    @BeforeAll
    static void setupClass() {
        WebDriverManager.chromedriver().setup();
        Configuration.baseUrl = "https://magento.softwaretestingboard.com";
        Configuration.browser = "chrome";
        Configuration.browserSize="2560x1600";
//        Configuration.holdBrowserOpen = true;
    }

    @BeforeEach
    public void setupTest() {
        open("/");
    }


    @AfterEach
    void teardown() {
        closeWebDriver();
    }
}
