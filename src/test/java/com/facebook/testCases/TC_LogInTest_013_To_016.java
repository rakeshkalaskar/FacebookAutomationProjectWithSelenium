package com.facebook.testCases;

import java.io.IOException;
import java.time.Duration;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TC_LogInTest_013_To_016 extends BaseClass {
	@Test(dataProvider = "dataForLogin")
	public void loginProcedure(String number, String pass) throws IOException {
		String typeOfbutton = "password";
		String typeOfbutton1 = "text";
		String placeholderofemail = "Email address or phone number";
		String placeholderofpassword = "Password";
		String textoflogin = "Log In";
		String inputfield = "";
		String urltext = "https://www.facebook.com/";
		String titleText = "Facebook – log in or sign up";
		WebElement email = driver.findElement(By.id("email"));
		email.sendKeys(number);
		// email.sendKeys(Keys.TAB);
		WebElement password = driver.findElement(By.id("pass"));
		password.sendKeys(pass);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		// checking the url is correct or not
		String urlOfWebPage = driver.getCurrentUrl();
		if (urlOfWebPage.equalsIgnoreCase(urltext)) {
			Assert.assertTrue(true);
			System.out.println("passed");
			logger.info("url test case passed");
		} else {
			captureScreenShot(driver, "loginProcedure");
			Assert.assertTrue(false);
			System.out.println("failed");
			logger.error("url test case failed");
			driver.get(url);
		}
		// checking title of web page
		String titleofWebPage = driver.getTitle();
		if (titleofWebPage.equalsIgnoreCase(titleText)) {
			Assert.assertTrue(true);
			System.out.println("passed");
			logger.info("title test case passed");
		} else {
			captureScreenShot(driver, "loginProcedure");
			Assert.assertTrue(false);
			System.out.println("failed");
			logger.error("title test case failed");
			driver.get(url);
		}
		// eye button visible or not when type password
		WebElement eyebutton = driver
				.findElement(By.xpath("//div[@id=\"passContainer\"]//div[contains(@class,\"_9lsa\")]"));
		if (eyebutton.isDisplayed()) {
			Assert.assertTrue(true);
			System.out.println("passed");
			logger.info("login test case passed");
		} else {
			captureScreenShot(driver, "loginProcedure");
			Assert.assertTrue(false);
			System.out.println("failed");
			logger.error("test case failed");
			driver.get(url);
		}

		eyebutton.click();
		// eye button performance checking able to change visibility of password
		String str1 = eyebutton.getAttribute("type");
		System.out.println(str1);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		if (password.getAttribute("type").equalsIgnoreCase(typeOfbutton1)) {
			Assert.assertTrue(true);
			System.out.println("passed");
			logger.info("password button test case passed");
		} else {
			captureScreenShot(driver, "loginProcedure");
			Assert.assertTrue(false);
			System.out.println("failed");
			logger.error("password button test case failed");
		}
		eyebutton.click();
		// eye button performing well when clicking on button
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		if (password.getAttribute("type").equalsIgnoreCase(typeOfbutton)) {
			Assert.assertTrue(true);
			System.out.println("passed");
			logger.info("password button test case passed");
		} else {
			captureScreenShot(driver, "loginProcedure");
			Assert.assertTrue(false);
			System.out.println("failed");
			logger.error("password button test case failed");
		}

		// email placeholder checking
		if (email.getAttribute("placeholder").equalsIgnoreCase(placeholderofemail)) {
			Assert.assertTrue(true);
			System.out.println("passed");
			logger.info("email placeholder test case passed");
		} else {
			captureScreenShot(driver, "loginProcedure");
			Assert.assertTrue(false);
			System.out.println("failed");
			logger.error("email button test case failed");
		}
		// email placeholder checking
		if (password.getAttribute("placeholder").equalsIgnoreCase(placeholderofpassword)) {
			Assert.assertTrue(true);
			System.out.println("passed");
			logger.info("password placeholder test case passed");
		} else {
			captureScreenShot(driver, "loginProcedure");
			Assert.assertTrue(false);
			System.out.println("failed");
			logger.error("password placeholder test case failed");
		}
		WebElement loginbutton = driver.findElement(By.name("login"));
		// login text checking
		if (loginbutton.getText().equalsIgnoreCase(textoflogin)) {
			Assert.assertTrue(true);
			System.out.println("passed");
			logger.info("login button text test case passed");
		} else {
			captureScreenShot(driver, "loginProcedure");
			Assert.assertTrue(false);
			System.out.println("failed");
			logger.error("login button text test case failed");
		}
		// checking the input field text clear when we refresh the page
		driver.navigate().refresh();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		String inputfieldvalue = email.getText();
		if (inputfieldvalue.equals(inputfield)) {
			Assert.assertTrue(true);
			System.out.println("passed");
			logger.info("input field text test case passed");
		} else {
			captureScreenShot(driver, "loginProcedure");
			Assert.assertTrue(false);
			System.out.println("failed");
			logger.error("input field text test case failed");
		}
		List<WebElement> languages = driver.findElements(By.xpath("//div[@id=\"pageFooter\"]//a[@class=\"_sv4\"]"));

		for (int i = 0; i < languages.size(); i++) {
			String url1 = languages.get(i).getAttribute("href");
			String text = languages.get(i).getText();
			System.out.println(url1 + " " + text);
		}
	}

	@DataProvider(name = "dataForLogin")
	public Object[][] dataProvider() {
		return new Object[][] { { "1234567", "12" } };
	}

}
