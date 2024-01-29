package main.java.pom.Naukri;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LandingPage {

	public void closeWindows(WebDriver oDriver, String mainWindow) {

		System.out.println("function for handling multiple widows initiated "); //log

		for (String handle : oDriver.getWindowHandles()) {
			if (!handle.equals(mainWindow)) {
				oDriver.switchTo().window(handle);
				oDriver.close();
			}
		}
		oDriver.switchTo().window(mainWindow);
	}

	public void login(WebDriver oDriver, WebDriverWait oWait) {

		///Scrolling the window js.executeScript("window.scrollBy(x,y)");
			JavascriptExecutor js = (JavascriptExecutor) oDriver;
			js.executeScript("window.scrollBy(0,1000)");


			System.out.println("Opening given URL");

			oDriver.get("https://www.naukri.com/");

			System.out.println("URL opened successfully"); //log

			System.out.println("main Window Title :- " + oDriver.getTitle());
			String mainWindow = oDriver.getWindowHandle();

			closeWindows(oDriver, mainWindow); //Function called to close multplie Windows


			oDriver.findElement(By.xpath("//div[contains(text(),'Login')]")).click();

			System.out.println("wait activity started");
			oWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@id='block']")));

			System.out.println("element visible");

			oDriver.findElement(By.xpath("//span[@id='block']")).click();

			oDriver.findElement(By.xpath("//div[@class='drawer-wrapper']")).click();
			oDriver.findElement(By.xpath("//input[@placeholder='Enter your active Email ID / Username']")).sendKeys("####EMAIL_NEEDED####");
			oDriver.findElement(By.xpath("//input[@placeholder='Enter your password']")).sendKeys("###PasswordNeeded####");

			oDriver.findElement(By.xpath("//button[@class='btn-primary loginButton']")).click();
			Actions actions = new Actions(oDriver);
	}
}
