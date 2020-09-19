package ru.netology;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class CallBackTest {
    @Test
    void shouldSubmitRequest() {
        open("http://localhost:9999");
        $("[data-test-id='city'] input").setValue("Москва");
//        Очищаем поле ввода даты
        $("[data-test-id=date] input.input__control").doubleClick().sendKeys(Keys.BACK_SPACE);
//        Генерируем строку с датой
        String date = LocalDate.now().plusDays(5).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
//        Ввод сгенерированной даты
        $("[data-test-id=date] input.input__control").setValue(date);
        $("[data-test-id='name'] input").setValue("Анна Иванова");
        $("[data-test-id='phone'] input").setValue("+79001234567");
        $("[data-test-id=agreement]").click();
        $(byText("Забронировать")).click();

        $(withText("Успешно!")).waitUntil(visible, 15000);
//        Проверка даты в сплывающем сообщении
        $("[data-test-id=notification] .notification__content").waitUntil(visible, 15000).shouldHave(text(date));
    }

}
