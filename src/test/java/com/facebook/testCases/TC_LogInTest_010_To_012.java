package com.facebook.testCases;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TC_LogInTest_010_To_012 extends BaseClass{
	@Test(dataProvider = "dataForLogin")
	public void loginProcedure(String number, String pass) throws IOException {
		WebElement email = driver.findElement(By.id("email"));
		email.sendKeys(number);
		WebElement password = driver.findElement(By.id("pass"));
		password.sendKeys(pass);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		WebElement loginbutton=driver.findElement(By.name("login"));
		loginbutton.click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(6));

		optionButton = driver
				.findElement(By.xpath("//div[text()=\"The password that you've entered is incorrect. \"]"));
		
		String nameofalert=optionButton.getText();
		System.out.println(nameofalert);
		optionButton.click();
		if (optionButton.isDisplayed()) {
			Assert.assertTrue(true);
			System.out.println("passed");
			logger.info("login test case passed");
			driver.get(url);
			
		} else {
			captureScreenShot(driver, "loginProcedure");
			Assert.assertTrue(false);
			System.out.println("failed");
			logger.error("test case failed");
			driver.get(url);
		}
		//logout();
	}

	@DataProvider(name = "dataForLogin")
	public Object[][] dataProvider() {
		return new Object[][] { { "validemail@gmail.com", "invalidpassword" },{"validemail@gmail.com","   invalidpasswt  "},{"",""} };
	}

}
