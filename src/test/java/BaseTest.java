import com.codeborne.selenide.Configuration;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;

import static com.codeborne.selenide.Selenide.*;

class BaseTest {
    WebDriver driver;

    @BeforeAll
    static void setupClass() {
        WebDriverManager.chromedriver().setup();
        Configuration.baseUrl = "https://magento.softwaretestingboard.com";
        Configuration.browser = "chrome";
        Configuration.browserSize="1200x900";
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
