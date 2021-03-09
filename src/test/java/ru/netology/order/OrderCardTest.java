package ru.netology.order;

import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class OrderCardTest {
    @Test
    void shouldSubmitRequestValidData() {
        open("http://localhost:9999");
        $("[data-test-id = name] input").setValue("Иванов Иван");
        $("[data-test-id = phone] input").setValue("+79001236987");
        $("[data-test-id = agreement]").click();
        $("[type = button]").click();
        $("[data-test-id = order-success]").shouldHave(exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }

    @Test
    void shouldSubmitRequestNoData() {
        open("http://localhost:9999");
        $("[data-test-id = name] input").setValue("");
        $("[data-test-id = phone] input").setValue("");
        $("[type = button]").click();
        $(".input_invalid[data-test-id=name] .input__sub").shouldHave(exactText("Поле обязательно для заполнения"));
    }

    @Test
    void shouldSubmitRequestInvalidName() {
        open("http://localhost:9999");
        $("[data-test-id = name] input").setValue("Ivanov Ivan");
        $("[data-test-id = phone] input").setValue("+79001236987");
        $("[data-test-id = agreement]").click();
        $("[type = button]").click();
        $(".input_invalid[data-test-id=name] .input__sub").shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    void shouldSubmitRequestInvalidPhoneNumber() {
        open("http://localhost:9999");
        $("[data-test-id = name] input").setValue("Иванов Иван");
        $("[data-test-id = phone] input").setValue("+790012369877777777");
        $("[data-test-id = agreement]").click();
        $("[type = button]").click();
        $(".input_invalid[data-test-id=phone] .input__sub").shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

}
