package main.java.utils;

import main.java.DriverManager.DriverManager;
import main.java.ExecutionNode.ExecutionNode;
import main.java.runners.BaseTest;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {
    public static String baseDir = System.getProperty("user.dir")+"/Screenshots/";

    public static String snapshot() {

        String scenarioName = ExecutionNode.getScenarioName();
        String timestamp = Utils.getCurrentDateTime();
        String filename = baseDir +scenarioName+"/WebDriver"+timestamp+".png";

        TakesScreenshot scrShot = ((TakesScreenshot) DriverManager.getDriver());
        File file = scrShot.getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(file, new File(filename));
            System.out.println("the Screenshot is taken and saved as = "+filename);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return filename;
    }

    public static String snapshot(String scenarioStatus) {

        String scenarioName = ExecutionNode.getScenarioName();
        String timestamp = Utils.getCurrentDateTime();
        String filename = baseDir +scenarioStatus+"/"+scenarioName+"/WebDriver"+timestamp+".png";

        TakesScreenshot scrShot = ((TakesScreenshot)DriverManager.getDriver());
        File file = scrShot.getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(file, new File(filename));
            System.out.println("the Screenshot is taken and saved as = "+filename);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return filename;
    }

    public static void snapshot(WebElement webElement) throws IOException {
        String scenarioName = ExecutionNode.getScenarioName();
        String timestamp = Utils.getCurrentDateTime();
        String filename= baseDir+scenarioName+"/WebElementScr"+timestamp+".png";
        File file = webElement.getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(file, new File(filename));
            System.out.println("the Screenshot is taken and saved as : "+filename);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw e;
        }
    }

    public static String getCurrentDateTime(){

        DateFormat dateFormat = new SimpleDateFormat("MM_dd_yyyy_HH_mm_ss");
        Date currentDate = new Date();
        return dateFormat.format(currentDate);
    }



}
