package ru.netology.delivery.test;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import lombok.val;
import org.junit.jupiter.api.*;
import org.openqa.selenium.Keys;
import ru.netology.delivery.data.DataGenerator;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;



class DeliveryTest {

    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @BeforeEach
    void setUp() {
        open("http://localhost:9999/");
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
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
        $("[data-test-id=name] input").setValue(DataGenerator.generateName("ru"));
        $("[data-test-id=phone] input").setValue(DataGenerator.generatePhone("ru"));
        $("[data-test-id=agreement] .checkbox__box").click();
        $("button.button").click();
    }


    @Test
    void shouldSubmitForm() {
        $("[data-test-id=city] input").setValue("Калуга");
        LocalDate date=LocalDate.now().plusDays(3);
        String dayVisit=date.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[data-test-id=date] input").sendKeys(Keys.CONTROL+"A"+Keys.DELETE);
        $("[data-test-id=date] input").setValue(dayVisit);
        $("[data-test-id=name] input").setValue("Елифанова Анастасия");
        $("[data-test-id=phone] input").setValue("+79270000000");
        $("[data-test-id=agreement]").click();
        $("button.button").click();
        $(withText("Успешно!")).waitUntil(visible,15000);
        $("button.button").click();
        $(withText("У Вас уже запланирована встрача на эту дату. Хотите перепланировать!")).waitUntil(visible,15000);
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





