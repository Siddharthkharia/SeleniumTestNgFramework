package main.java.reporting;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import main.java.DriverManager.DriverManager;
import main.java.ExecutionNode.ExecutionNode;
import main.java.utils.Utils;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import java.io.IOException;

import static main.java.runners.BaseTest.extentReports;

public class iTestReporting implements ITestListener {

    /**
     * Invoked each time before a test will be invoked.
     * The <code>ITestResult</code> is only partially filled with the references to
     * class, method, start millis and status.
     *
     * @param result the partially filled <code>ITestResult</code>
     * @see ITestResult#STARTED
     */
    @Override
    public void onTestStart(ITestResult result) {
        ExtentTest extentTest = extentReports.createTest(result.getMethod().getMethodName());
        ExecutionNode.setExtentTest(extentTest);
        ExecutionNode.setScenarioName(result.getMethod().getMethodName());
    }

    /**
     * Invoked each time a test succeeds.
     *
     * @param result <code>ITestResult</code> containing information about the run test
     * @see ITestResult#SUCCESS
     */
    @Override
    public void onTestSuccess(ITestResult result) {
        try {
            ExecutionNode.getExtentTest().pass(result.getMethod().getMethodName()+" Passed", MediaEntityBuilder.createScreenCaptureFromPath(Utils.snapshot("Passed")).build());
            ExecutionNode.removeScenarioName();
        } catch (IOException e) {
            Reporter.log("Error while capturing the screenshot", true);
            throw new RuntimeException(e);
        }
    }

    /**
     * Invoked each time a test fails.
     *
     * @param result <code>ITestResult</code> containing information about the run test
     * @see ITestResult#FAILURE
     */
    @Override
    public void onTestFailure(ITestResult result) {
        try {
            System.out.println("Saving the screenshot on failure ");
            ExecutionNode.getExtentTest().fail(result.getMethod().getMethodName()+" Failed", MediaEntityBuilder.createScreenCaptureFromPath(Utils.snapshot("Failed")).build());
            ExecutionNode.removeScenarioName();
        } catch (IOException e) {
            Reporter.log("Error while capturing the screenshot", true);
            throw new RuntimeException(e);
        }
    }

    /**
     * Invoked each time a test is skipped.
     *
     * @param result <code>ITestResult</code> containing information about the run test
     * @see ITestResult#SKIP
     */
    @Override
    public void onTestSkipped(ITestResult result) {
        try {
            ExecutionNode.getExtentTest().skip(result.getMethod().getMethodName()+" Skipped", MediaEntityBuilder.createScreenCaptureFromPath(Utils.snapshot("Skipped")).build());
            ExecutionNode.removeScenarioName();
        } catch (IOException e) {
            Reporter.log("Error while capturing the screenshot", false);
            throw new RuntimeException(e);
        }
    }

    /**
     * Invoked each time a method fails but has been annotated with
     * successPercentage and this failure still keeps it within the
     * success percentage requested.
     *
     * @param result <code>ITestResult</code> containing information about the run test
     * @see ITestResult#SUCCESS_PERCENTAGE_FAILURE
     */
    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

    }

    /**
     * Invoked after the test class is instantiated and before
     * any configuration method is called.
     *
     * @param context
     */
    @Override
    public void onStart(ITestContext context) {
//        init();
    }

    /**
     * Invoked after all the tests have run and all their
     * Configuration methods have been called.
     *
     * @param context
     */
    @Override
    public void onFinish(ITestContext context) {
        if(DriverManager.getDriver()!=null){
            DriverManager.getDriver().quit();
        }
    }
}
