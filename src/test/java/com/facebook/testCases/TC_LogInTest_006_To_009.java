package com.facebook.testCases;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.facebook.utilities.XLUtils;

public class TC_LogInTest_006_To_009 extends BaseClass{
	@Test(dataProvider = "loginData")
	public void loginProcedure(String number, String pass) throws IOException {
		driver.get(url);
		WebElement email = driver.findElement(By.id("email"));
		email.getText();
		email.sendKeys(number);
		WebElement password = driver.findElement(By.id("pass"));
		password.sendKeys(pass);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		driver.findElement(By.name("login")).click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(6));

		optionButton = driver.findElement(
				By.xpath("//a[text()=\"Find your account and log in.\"]"));
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
		logout();
	}
	@DataProvider(name = "loginData")
	public String[][] getData() throws Exception {
		String path = System.getProperty("user.dir") + "/src/test/java/com/facebook/testData/testDataForFB.xlsx";

		int rownum = XLUtils.getRowCount(path, "sheet1");
		int cellnum = XLUtils.getCellCount(path, "sheet1", 1);

		String logindata[][] = new String[rownum][cellnum];
		for (int i = 1; i <= rownum; i++) {
			for (int j = 0; j < cellnum; j++) {
				logindata[i - 1][j] = XLUtils.getCellData(path, "sheet1", i, j);
			}
		}
		return logindata;
	}
}

