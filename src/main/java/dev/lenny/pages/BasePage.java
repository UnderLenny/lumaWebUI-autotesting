package dev.lenny.pages;

import com.codeborne.selenide.SelenideElement;
import java.util.Map;

public abstract class BasePage {

    public void fillForm(Map<String, String> inputValues, Map<String, SelenideElement> fields) {
        inputValues.forEach((key, value) -> {
            if (fields.containsKey(key)) {
                fields.get(key).setValue(value);
            } else {
                throw new IllegalArgumentException("Поле " + key + " недоступно форме");
            }
        });
    }
}
