package tst.account;


import io.qameta.allure.Description;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import tst.helpers.SetUp;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.executeJavaScript;


public class AccountRegistration_Tests extends SetUp{

    @DataProvider(name = "invalidEmails")
    public static Object[][] invalidEmails() {
        return new Object[][]{
                // case1: no value
                {""},
                // case2: space instead the value
                {" "},
                // case3: some random alphanumeric value
                {RandomStringUtils.randomAlphanumeric(1, 10)},
                // case4: some random ASCII value
                {RandomStringUtils.randomAscii(1, 10)},
                // case5: nothing before @
                {String.format("@%s.com", RandomStringUtils.randomAlphanumeric(1, 10))},
                // case6: nothing after @
                {String.format("%s@", RandomStringUtils.randomAlphanumeric(1, 10))},
                // case7: no domain extension
                {String.format("%s@%s", RandomStringUtils.randomAlphanumeric(1, 10),
                        RandomStringUtils.randomAlphanumeric(1, 10)) },
                // case8: empty domain extension
                {String.format("%s@%s.", RandomStringUtils.randomAlphanumeric(1, 10),
                        RandomStringUtils.randomAlphanumeric(1, 10)) },
                // case9: no @
                {String.format("%s.com", RandomStringUtils.randomAlphanumeric(1, 10))},
                // case10: double @
                {String.format("%s@@%s.com", RandomStringUtils.randomAlphanumeric(1, 10),
                        RandomStringUtils.randomAlphanumeric(1, 10))},
        };
    }

    @Test(testName = "testEmailInvalidValues",
            description = "Verify that invalid email values will cause the proper error message ",
    dataProvider = "invalidEmails")
    @Description("Test Description: this test opens the 'Get Started' dialog fills all the fields with valid values " +
            "except the 'Work email' filed which will be filled with various invalid values from the 'invalidEmails' " +
            "Data provider and verifies that 'Please enter your work email.' error message will be shown, the error " +
            "message has a proper look and will disappear within 15 sec.")
    public void testEmailInvalidValues(String invalidEmailValue) {
        // click on 'Sign up'
        $(".header-signup").shouldBe(visible).click();

        // enter a valid email for the new user
        $("#signup-payed-email").shouldBe(visible).val(invalidEmailValue);
        // enter user's first and last name
        $("#signup-payed-full_name").shouldBe(visible).val("Vasili Pupkin");
        // enter a company name
        $("#signup-payed-company").shouldBe(visible).val("R&K");
        // select a job title
        $("#signup-payed-position").shouldBe(visible).selectOption("CEO");
        // click on 'Create an Account'
        $("#payed-signup-button").shouldBe(visible).click();

        // verify that the proper error message appears, it has red text and pink background,
        // and it will disappear after ~15 sec
        $("#signup-payed-error")
                .shouldBe(visible)
                .shouldHave(
                        text("Please enter your work email."),
                        cssValue("background-color", "rgba(255, 225, 224, 1)"),
                        cssValue("color", "rgba(221, 44, 0, 1)")
                ).should(disappear, Duration.ofSeconds(15));

        // execute this JS code to start over
        executeJavaScript("$('.signup-payed__popup--scrollable').hide();");
    }




}
