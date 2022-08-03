package com.facebook.testCases;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.facebook.utilities.XLUtils;

public class TC_LogInTest_001_To_004 extends BaseClass {

	@Test(dataProvider = "dataForLogin")
	public void loginProcedure(String number, String pass) throws IOException {
		WebElement email = driver.findElement(By.id("email"));
		email.sendKeys(number);
		//email.sendKeys(Keys.TAB);
		WebElement password = driver.findElement(By.id("pass"));
		password.sendKeys(pass);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		WebElement loginbutton=driver.findElement(By.name("login"));
		loginbutton.click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(6));

		optionButton = driver
				.findElement(By.xpath("//div[contains(@class,'q9uorilb l9j0dhe7 pzggbiyp du4w35lb')]//self::div[1]"));
		if (optionButton.isDisplayed()) {
			Assert.assertTrue(true);
			System.out.println("passed");
			logger.info("login to account test case passed");
		} else {
			captureScreenShot(driver, "loginProcedure");
			Assert.assertTrue(false);
			System.out.println("failed");
			logger.error("login to account test case failed");
			driver.get(url);
		}
		logout();
		removeAccountButton();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(6));
		WebElement remove=driver.findElement(By.xpath("//div[@class=\\\"uiContextualLayerParent _csi\\\"]//child::a[2]"));
		if(remove.isDisplayed()) {
			captureScreenShot(driver, "loginProcedure");
			Assert.assertTrue(false);
			System.out.println("failed");
			logger.error("account remember remove test case failed");
		}else {
			Assert.assertTrue(true);
			System.out.println("passed");
			logger.info("account remember remove test case passed");
		}
	}

	@DataProvider(name = "dataForLogin")
	public Object[][] dataProvider() {
		return new Object[][] {  
			{
				"7776817878","7776817979"
			}
				/*
				 * { "validmail@gmail.com", "validpassword"
				 * },{"   validemail.with.only.spaces@gmail.com","validpassword"}
				 */
		};
		
	}
}