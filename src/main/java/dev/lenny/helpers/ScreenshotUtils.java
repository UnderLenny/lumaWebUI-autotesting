package dev.lenny.helpers;

import com.codeborne.selenide.Screenshots;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import com.codeborne.selenide.WebDriverRunner;

public class ScreenshotUtils {

    @Attachment(value = "Screenshot", type = "image/png")
    public static byte[] takeScreenshot() {
        return ((TakesScreenshot) WebDriverRunner.getWebDriver()).getScreenshotAs(OutputType.BYTES);
    }

    @Attachment(value = "DOM Tree", type = "text/html")
    public static String attachDomTree() {
        return WebDriverRunner.getWebDriver().getPageSource();
    }
}
