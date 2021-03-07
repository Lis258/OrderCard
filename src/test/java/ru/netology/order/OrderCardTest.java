package ru.netology.order;

import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class OrderCardTest {
    @Test
    void shouldSubmitRequest() {
        open("http://localhost:9999");
        $("[data-test-id = name] input").setValue("Иванов Иван");
        $("[data-test-id = phone] input").setValue("+79001236987");
        $("[data-test-id = agreement]").click();
        $("[type = button]").click();
        $("[data-test-id = order-success]").shouldHave(exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));

    }
}
