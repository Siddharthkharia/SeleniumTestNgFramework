package main.java.pom.Naukri;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Homepage {
	
	public void editProfile (WebDriver oDriver, WebDriverWait oWait) {
		
//HOMEPAGE
		
	oWait.until(ExpectedConditions.elementToBeClickable(By.id("qsb-keyskill-sugg")));
	
	System.out.println("Search bar visible ");
			
	Actions oActions = new Actions(oDriver);
	
	WebElement myProfile = oDriver.findElement(By.xpath("//div[contains(text(),'My Naukri')]"));
	oActions.moveToElement(myProfile).perform();
	System.out.println("moved to my profile " );
	
	synchronized (oDriver)
	{
		try {
			oDriver.wait(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	System.out.println("Sync wait activity finished");

	WebElement editProfile = oDriver.findElement(By.xpath("//a[@title='Edit Profile']"));
	
	editProfile.click();
	System.out.println("Clicked on edit profile");
	

}
}