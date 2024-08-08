import com.codeborne.selenide.Condition;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDate;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
;
import static io.opentelemetry.semconv.trace.attributes.SemanticAttributes.FaasDocumentOperationValues.DELETE;

//java -jar ./artifacts/app-replan-delivery.jar


public class WebSiteTest {
    @BeforeAll
    static void setUpAll(){
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void tearDownAll(){
        SelenideLogger.removeListener("allure");
    }

    @BeforeEach
     void setup(){
        open("http://localhost:9999");
    }


    @Test
    void sendingTheForm() {


        var validUser = DataGenerator.Registration.generateUser();
        var daysToAddForFirstMeeting = 4;
        var firstMeetingDate = DataGenerator.generateDate(daysToAddForFirstMeeting);
        var daysToAddForSecondMeeting = 7;
        var secondMeetingDate = DataGenerator.generateDate(daysToAddForSecondMeeting);
        $("[data-test-id=city] input").setValue(validUser.getCity());
        $("[data-test-id='date'] input").doubleClick().sendKeys(DELETE);
        $("[data-test-id='date'] input").setValue(firstMeetingDate);
        $("[data-test-id=name] input").setValue(validUser.getName());
        $("[data-test-id=phone] input").setValue(validUser.getPhone());
        $("[data-test-id=agreement]").click();
        $("[class=\"button__content\"]").click();
        $("[class='notification__content']")
                .shouldBe(Condition.visible, Duration.ofSeconds(10))
                .shouldHave(Condition.text("Встреча успешно запланирована на " + firstMeetingDate));


        $("[data-test-id='date'] input").doubleClick().sendKeys(DELETE);
        $("[data-test-id='date'] input").setValue(secondMeetingDate);
        $("[class=\"button__content\"]").click();
        $("[class=\"button button_view_extra button_size_s button_theme_alfa-on-white\"]").click();
        $("[class='notification__content']")
                .shouldBe(Condition.visible, Duration.ofSeconds(10))
                .shouldHave(Condition.text("Встреча успешно запланирована на " + secondMeetingDate));

    }

    @Test
    public void noValidPhoneTest(){
        var validUser = DataGenerator.Registration.generateUser();
        var daysToAddForFirstMeeting = 4;
        var firstMeetingDate = DataGenerator.generateDate(daysToAddForFirstMeeting);
        $("[data-test-id=city] input").setValue(validUser.getCity());
        $("[data-test-id='date'] input").doubleClick().sendKeys(DELETE);
        $("[data-test-id='date'] input").setValue(firstMeetingDate);
        $("[data-test-id=name] input").setValue(validUser.getName());
        $("[data-test-id=phone] input").setValue(DataGenerator.generateNoValidPhone());
        $("[data-test-id=agreement]").click();
        $("[class=\"button__content\"]").click();
        $("[data-test-id=phone].input_invalid .input_sub").shouldHave(Condition.exactText("Неверный формат номера мобильного телефона"));


    }


}

