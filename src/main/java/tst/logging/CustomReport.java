package tst.logging;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import static com.codeborne.selenide.WebDriverRunner.closeWebDriver;

public class CustomReport implements ITestListener {

    protected SelenideReport report = new SelenideReport();

    public CustomReport(){

    }

    @Override
    public void onTestStart(ITestResult result) {
        Reporter.log("TEST: "+ result.getName(),true);

        report.start();
    }

    @Override
    public void onTestSuccess(ITestResult result) {

        report.finish(result.getName());
    }

    @Override
    public void onTestFailure(ITestResult result) {

        report.finish(result.getName());
    }

    @Override
    public void onTestSkipped(ITestResult result) {

        report.finish("The test " +result.getName() +" was skipped");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        report.finish(result.getName());

    }

    @Override
    public void onStart(ITestContext iTestContext) {
        Reporter.log("--------Starting tests--------", true);
    }

    @Override
    public void onFinish(ITestContext iTestContext) {
        Reporter.log("--------Tests are finished--------", true);

        closeWebDriver();
    }

}
