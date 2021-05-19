package ru.netology.delivery.test;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import ru.netology.delivery.data.DataGenerator;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;


class DeliveryTest {

    @BeforeEach
    void setUp() {
        open("http://localhost:9999/");
    }

    @Test
    @DisplayName("Should successful plan and replan meeting")
    void shouldSuccessfulPlanAndReplanMeeting() {
        val validUser = DataGenerator.Registration.generateUser("ru");
        val daysToAddForFirstMeeting = 4;
        val firstMeetingDate = DataGenerator.generateDate(daysToAddForFirstMeeting);
        val daysToAddForSecondMeeting = 7;
        val secondMeetingDate = DataGenerator.generateDate(daysToAddForSecondMeeting);
        $("[data-test-id=city] input").setValue(DataGenerator.generateCity("ru"));
        $("[data-test-id=date] input").sendKeys(Keys.CONTROL+"A"+Keys.DELETE);
        $("[data-test-id=date] input").setValue(DataGenerator.generateDate(3));
        $("[data-test-id=date] input").setValue(DataGenerator.generateName("ru"));
        $("[data-test-id=date] input").setValue(DataGenerator.generatePhone("ru"));
        $("[data-test-id=agreement]").click();
        $("button.button").click();
    }
}




    //@Test
    //void shouldCardDeliveryOnOneDate() {
        //$("[data-test-id=city] input").setValue("Челябинск");
        //$("[data-test-id=date] input").sendKeys(Keys.CONTROL+"A"+Keys.DELETE);
        //$("[data-test-id=date] input").setValue(DataGenerator.Registration.getDayVisit(3));
        //$("[data-test-id=name] input").setValue("Андреев Алексей");
        //$("[data-test-id=phone] input").setValue("+79525140000");
        //$("[data-test-id=agreement]").click();
        //$("button.button").click();
        //$(withText("Успешно!")).waitUntil(Condition.visible, 150);
        //$("button.button").click();
        //$(withText("У вас уже запланирована встреча на другую дату. Перепланировать?")).waitUntil(Condition.visible, 150);





