package main.java.pom.Amazon;

import main.java.DriverManager.DriverManager;
import main.java.runners.BaseTest;
import main.java.ExecutionNode.ExecutionNode;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.Set;

import static main.java.DriverManager.DriverManager.*;
import static main.java.utils.Utils.snapshot;

public class SearchPage extends BaseTest {


    public void selectGetItToday() throws InterruptedException {
        WebElement checkbox = DriverManager.getDriver().findElement(By.xpath("//span[text()='Get It Today'][1]/preceeding::div[@class='a-checkbox a-checkbox-fancy s-navigation-checkbox aok-float-left']following:://input[@type='checkbox']"));
        Actions oActions = new Actions(DriverManager.getDriver());
        checkbox.click();
        WebElement FourStarUp = DriverManager.getDriver().findElement(By.xpath("//section[aria-label='4 Stars & Up']/following::/span[text()='& Up']"));

        getDriver().wait(1000);

        FourStarUp.click();
        getDriver().wait(1000);
    }

    public void selectObjectFromSearchList(String ObjectNum){
        String ObjctLocator =  "//div[@data-index='{}']/following::a[1]".replace("{}",ObjectNum);
        WebElement objElement = DriverManager.getDriver().findElement(By.xpath(ObjctLocator));

        String mainHandle= DriverManager.getDriver().getWindowHandle();

        objElement.click();

        snapshot();

        Set<String> winHandles = DriverManager.getDriver().getWindowHandles();

        for(String handle : winHandles){
            DriverManager.getDriver().switchTo().window(handle);
        }
        snapshot();
    }

    public void addToCart(){

        WebElement addToCartBtn = DriverManager.getDriver().findElement(By.xpath("//input[@id='add-to-cart-button']"));
        addToCartBtn.click();
    }

}
