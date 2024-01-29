package test.java.pomTest;

import main.java.pom.sauceDemo.sauceDemo;
import main.java.runners.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class sauceDemoTest extends BaseTest {

    sauceDemo oSwag = new sauceDemo();

    @Test(groups = {"sauce"})
    public void TC00_SauceLogin(){
        oSwag.login("standard_user","secret_sauce");
    }

    @Test(groups = {"sauce"})
    public void TC00_SauceLogin_ProductAdd(){
        oSwag.login("standard_user","secret_sauce");
        oSwag.addProductTocart("Sauce Labs Backpack");
        oSwag.proceedToCartAndCheckout();
        oSwag.fillContactInfo("Siddharth","test","400097");
        Assert.assertEquals(oSwag.finishAndAssert(),"Thank you for your order!");
    }

}
