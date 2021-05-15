package ru.netology.delivery.test;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import ru.netology.delivery.data.DataGenerator;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class CardDeliveryNewFunctionalTest {

    @BeforeEach
    void setUpAll() {
        open("http://localhost:9999/");

    }

    @Test
    void shouldCardDeliveryOnOneDate() {
        $("[data-test-id=city] input").setValue("Челябинск");
        $("[data-test-id=date] input").sendKeys(Keys.CONTROL+"A"+Keys.DELETE);
        $("[data-test-id=date] input").setValue(DataGenerator.Registration.getDayVisit(3));
        $("[data-test-id=name] input").setValue("Андреев Алексей");
        $("[data-test-id=phone] input").setValue("+79525140000");
        $("[data-test-id=agreement] input").click();
        $("button.button").click();
        $(withText("Успешно!")).waitUntil(Condition.visible, 1500);
        $("[data-test-id=success-notification].notification_content").shouldHave(Condition.exactTextCaseSensitive("Встреча успешно запаланирована на "+DataGenerator.Registration.getDayVisit(3)));
        $("button.button").click();
        $(withText("У вас уже запланирована встреча на эту дату. Хотите перепланировать?")).waitUntil(Condition.visible, 1500);

    }


}
