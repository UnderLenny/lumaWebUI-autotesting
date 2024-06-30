package dev.lenny.helpers;

import com.codeborne.selenide.WebDriverRunner;

import java.net.MalformedURLException;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class Helpers {
    public static void checkCurrentUrl(String expectedUrl) {
        String currentUrl = WebDriverRunner.getWebDriver().getCurrentUrl();
        try {
            URL url = new URL(expectedUrl);
            expectedUrl = url.getProtocol() + "://" + url.getHost() + "/";
            assertEquals(expectedUrl, currentUrl);
        } catch (MalformedURLException e) {
            fail("Ошибка при парсинге текущего URL");
        }

    }

    public static void checkErrorText(String expectedText, String actualText) {
        assertEquals(expectedText, actualText);
    }
    
    public static String generateRandomEmail() {
        return "testMail" + Math.random() + "@test.com";
    }
}
