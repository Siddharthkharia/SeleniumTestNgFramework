package main.java.pom.sauceDemo;

import main.java.DriverManager.DriverManager;
import main.java.runners.BaseTest;
import main.java.utils.Utils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;


public class sauceDemo extends BaseTest {


    public void login(String user, String password){
        DriverManager.getDriver().get("https://www.saucedemo.com/");
        WebElement username = DriverManager.getDriver().findElement(By.id("user-name"));
        WebElement passwd = DriverManager.getDriver().findElement(By.id("password"));

        username.sendKeys(user);
        passwd.sendKeys(password);

        WebElement submitBtn = DriverManager.getDriver().findElement(By.xpath("//input[@type='submit']"));
        submitBtn.submit();
        DriverManager.getDriver().getTitle();
    }

    public void addProductTocart(String productName){
        WebElement addtocart = DriverManager.getDriver().findElement(By.xpath("//div[contains(text(),'"+productName+"')]//ancestor::div[@class='inventory_item_label']/following-sibling::div/button[text()='Add to cart']"));
//        WebElement addtocart = DriverManager.getDriver().findElement(By.xpath("//button[@data-test='add-to-cart-sauce-labs-backpack']"));
        addtocart.click();
    }

    public void proceedToCartAndCheckout(){
        WebElement cartBtn = DriverManager.getDriver().findElement(By.className("shopping_cart_link"));
        cartBtn.click();
        WebElement checkoutBtn = DriverManager.getDriver().findElement(By.id("checkout"));
        checkoutBtn.click();
    }

    public void fillContactInfo(String fname, String lname, String pincode){
        WebElement firstName = DriverManager.getDriver().findElement(By.id("first-name"));
        WebElement lastName = DriverManager.getDriver().findElement(By.id("last-name"));
        WebElement pin = DriverManager.getDriver().findElement(By.id("postal-code"));
        WebElement continueBtn = DriverManager.getDriver().findElement(By.id("continue"));

        firstName.sendKeys(fname);
        lastName.sendKeys(lname);
        pin.sendKeys(pincode);
        continueBtn.submit();
    }

    public String finishAndAssert(){
        WebElement finish = DriverManager.getDriver().findElement(By.id("finish"));
        finish.click();
        WebElement bannerText = DriverManager.getDriver().findElement(By.xpath("//div[@id='checkout_complete_container']//following::h2"));
        String text= bannerText.getText();
        System.out.println("text from final page is = "+text);
        Utils.snapshot();
        return text;
    }
}
