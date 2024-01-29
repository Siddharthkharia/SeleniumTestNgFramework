package main.java.runners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import main.java.DriverManager.DriverManager;
import main.java.ExecutionNode.ExecutionNode;
import main.java.utils.Utils;
import org.openqa.selenium.WebDriver;
import org.testng.*;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import main.java.DriverManager.DriverManager;

//@Listeners(ITestListener.class)
public class BaseTest extends DriverManager{

    public static ExtentReports extentReports;

    @BeforeSuite(alwaysRun = true)
    public void BeforeSuite(){

        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(new File("./Reports/extentReport"+ Utils.getCurrentDateTime()+".html"));
        extentReports = new ExtentReports();
        extentReports.attachReporter(htmlReporter);
    }

    @BeforeClass(alwaysRun = true)
    public void beforeMethodCalls(){
        WebDriver oDriver = DriverManager.initDriver();
        DriverManager.setDriver(oDriver);
    }

    @BeforeMethod
    public void beforeTestMethods(Method method){
        ExtentTest extentTest = extentReports.createTest(method.getName());
        ExecutionNode.setExtentTest(extentTest);
        ExecutionNode.setScenarioName(method.getName());
    }

    @AfterMethod
    public void afterMethod(ITestResult result) throws IOException {

        switch (result.getStatus()){

            case ITestResult.SUCCESS:
                ExecutionNode.getExtentTest().pass(result.getMethod().getMethodName()+" Passed", MediaEntityBuilder.createScreenCaptureFromPath(Utils.snapshot("Passed")).build());
                break;

                case ITestResult.FAILURE:

                    String path= Utils.snapshot("Failed");
                    ExecutionNode.getExtentTest().addScreenCaptureFromPath(path);
                    System.out.println("Saving the screenshot on failure ");
                    ExecutionNode.getExtentTest().fail(result.getMethod().getMethodName()+" Failed", MediaEntityBuilder.createScreenCaptureFromPath(path).build());
                    break;

                    case ITestResult.SKIP:
                        ExecutionNode.getExtentTest().skip(result.getMethod().getMethodName()+" Skipped", MediaEntityBuilder.createScreenCaptureFromPath(Utils.snapshot("Skipped")).build());
                        break;
        }

        System.out.println("ExecutionCompleted for testcase :"+ExecutionNode.getScenarioName());
        ExecutionNode.removeScenarioName();
    }

    @AfterClass(alwaysRun = true)
    public void classTearDown(){
        DriverManager.getDriver().quit();
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown(){
        extentReports.flush();
        DriverManager.flushDriver();
    }

}
