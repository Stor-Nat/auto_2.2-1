package ru.netology;

import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class CallBackTest {
    @Test
    void shouldSubmitRequest() {
        open("http://localhost:9999");
        SelenideElement form = $("[id=root]");
        $("[data-test-id='city'] input").setValue("Москва");
        $("[data-test-id='date']");
        $("[data-test-id='name'] input").setValue("Анна Иванова");
        $("[data-test-id='phone'] input").setValue("+79001234567");
        $("[data-test-id=agreement]").click();
        $(byText("Забронировать")).click();

        $(withText("Успешно!")).waitUntil(visible, 15000);
    }

}
