package main.java.DriverManager;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class DriverManager {

    private static ThreadLocal<WebDriver> threadLocalDriver = new ThreadLocal();

    public static WebDriver initDriver(){
            System.out.println("PersonalLOGGER_INIT Launching new driver");
            WebDriver oDriver;
            String browserName = System.getProperty("browser","chrome");
            boolean isHeadless = Boolean.parseBoolean(System.getProperty("isHeadless", String.valueOf(false)));

            switch (browserName.toLowerCase()){
                case "chrome":
                    WebDriverManager.chromedriver().setup();
                    ChromeOptions chromeOptions = new ChromeOptions();
                    chromeOptions.setHeadless(isHeadless);
                    oDriver = new ChromeDriver(chromeOptions);
                    System.out.println("PersonalLOGGER_INIT Launched new driver and saved in ExcutionNode");
                    return oDriver;
                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    FirefoxOptions firefoxOptions = new FirefoxOptions();
                    firefoxOptions.setHeadless(isHeadless);
                    oDriver = new FirefoxDriver(firefoxOptions);
                    System.out.println("PersonalLOGGER_INIT Launched new driver and saved in ExcutionNode");
                    return oDriver;
            }
            return null;
    }

    public static WebDriver getDriver(){
        return threadLocalDriver.get();
    }

    public static void setDriver(WebDriver driver){
        threadLocalDriver.set(driver);
    }

    public static void setDriver(){
        threadLocalDriver.set(initDriver());
    }

    public static void flushDriver(){
        threadLocalDriver.remove();
    }

}
