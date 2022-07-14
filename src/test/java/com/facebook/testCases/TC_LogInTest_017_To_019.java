package com.facebook.testCases;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TC_LogInTest_017_To_019 extends BaseClass {
	@Test(dataProvider = "dataForLogin")
	public void loginProcedure(String number,String pass) throws IOException, InterruptedException {
		// checking the account is remembering or not 
		// checking the accuont password is remebering or not
		// checking the account is able to login with saved credentials or not
		WebElement email = driver.findElement(By.id("email"));
		email.sendKeys(number);
		//email.sendKeys(Keys.TAB);
		WebElement password = driver.findElement(By.id("pass"));
		password.sendKeys(pass);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		WebElement loginbutton=driver.findElement(By.name("login"));
		loginbutton.click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		logout();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		WebElement accountElement=driver.findElement(By.xpath("//div[@class=\"uiContextualLayerParent _csi\"]//a[1]"));
		accountElement.click();
		List<WebElement> passwordfields=driver.findElements(By.xpath("//*[@id=\"pass\"]"));
		WebElement newbutton = null;
		for(int i=0;i<passwordfields.size();i++) {
			if(i==1) {
				newbutton=passwordfields.get(i);
			}
		}
		newbutton.sendKeys(pass);
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()=\"Remember password\"]"))).click();
		List<WebElement> buttons=driver.findElements(By.xpath("//*[@name=\"login\"]"));
		WebElement newLoginButton = null;
		for(int i=0;i<buttons.size();i++) {
			if(i==1) {
				newLoginButton=buttons.get(i);
			}
		}
		newLoginButton.click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		logout();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		WebElement newaccount=driver.findElement(By.xpath("//div[@class=\"uiContextualLayerParent _csi\"]//a[1]"));
		Actions actions=new Actions(driver);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		actions.moveToElement(newaccount).click().perform();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		WebElement optionbuttonforVerifying=driver
				.findElement(By.xpath("//div[contains(@class,'q9uorilb l9j0dhe7 pzggbiyp du4w35lb')]//self::div[1]"));
		if(optionbuttonforVerifying.isDisplayed()) {
			Assert.assertTrue(true);
			System.out.println("passed");
			logger.info("automatic account login test case passed");
		} else {
			captureScreenShot(driver, "loginProcedure");
			Assert.assertTrue(false);
			System.out.println("failed");
			logger.error("automatic account login test case failed");
		}
		logout();
		removeAccountButton();		
	}
	@DataProvider(name = "dataForLogin")
	public Object[][] dataProvider() {
		return new Object[][] { { "validemail@gmail.com", "validpassword" } };
	}
}
