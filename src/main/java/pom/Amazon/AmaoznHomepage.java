package main.java.pom.Amazon;

import main.java.runners.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import  main.java.DriverManager.DriverManager;
import static main.java.utils.Utils.snapshot;

public class AmaoznHomepage extends BaseTest {


    public void launchurlAndSearch(String SearchText){

        DriverManager.getDriver().get("https://www.amazon.in/");
        snapshot();

        WebElement searchBox = DriverManager.getDriver().findElement(By.xpath("//input[@id='twotabsearchtextbox']"));
        searchBox.sendKeys(SearchText);

        snapshot();

        WebElement submitBtn = DriverManager.getDriver().findElement(By.xpath("//input[@id='nav-search-submit-button']"));
        submitBtn.submit();
        snapshot();
    }


}
