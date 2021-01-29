package tst.logging;

import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.testng.Reporter;

public class AllureHelpers {

    /**This method is used to attach Selenide's execution log to Allure report
     *
     * @param text
     * @return
     */
    @Attachment(value = "Test log")
    public static String attachText(String text) {
        return text;
    }

    /**This method is needed to pass log messages to Allure report. Simple use for logging any message
     * if this message is needed in Allure
     *
     * @param msg
     */
    @Step("{0}")
    public static void logToAllure(String msg) {
        Reporter.log(msg,true);
    }

}