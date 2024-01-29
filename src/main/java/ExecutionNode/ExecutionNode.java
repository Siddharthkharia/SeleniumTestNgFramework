package main.java.ExecutionNode;

import com.aventstack.extentreports.ExtentTest;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class ExecutionNode {
//    static ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();

    static ThreadLocal<String> scenarioName = new ThreadLocal();

    static ThreadLocal<ExtentTest> extentTestLocal = new ThreadLocal();


    public static ExtentTest getExtentTest() {
        return extentTestLocal.get();
    }

    public static void setExtentTest(ExtentTest extentTest) {
        ExecutionNode.extentTestLocal.set(extentTest);
    }

//    public static WebDriver getDriver() {
////        if(driver.get()==null){
////            driver.set(initDrivers());
////            System.out.println("DRIVER_LOG :- driver initiated from executionNode");
////        }
//        return driver.get();
//    }
//
//    public static void setDriver(WebDriver webDriver) {
//        driver.set(webDriver);
//    }
//
//    public static void flushDriver (){
//        driver.remove();
//    }


    public static String getScenarioName() {
        return scenarioName.get();
    }

    public static void setScenarioName(String scenario) {
        scenarioName.set(scenario); ;
    }

    public static void removeScenarioName (){
        scenarioName.remove();
    }


}
