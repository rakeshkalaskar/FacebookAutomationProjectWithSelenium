package com.facebook.testCases;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TC_RegisterFunc_001_To_006 extends BaseClass{
	@Test(dataProvider = "RegistrationData")
	public void registerPage(String fname, String lname, String email, String pass, String day,
			String month, String year, String str) {
		driver.findElement(By.xpath("//a[@rel=\"async\"]")).click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		driver.findElement(By.name("firstname")).sendKeys(fname);
		driver.findElement(By.name("lastname")).sendKeys(lname);
		driver.findElement(By.name("reg_email__")).sendKeys(email);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		driver.findElement(By.name("reg_email_confirmation__")).sendKeys(email);
		driver.findElement(By.name("reg_passwd__")).sendKeys(pass);
		Select select = new Select(driver.findElement(By.id("day")));
		select.selectByVisibleText(day);
		Select select1 = new Select(driver.findElement(By.id("month")));
		select1.selectByVisibleText(month);
		Select select2 = new Select(driver.findElement(By.id("year")));
		select2.selectByVisibleText(year);
		if (str.equals("Male")) {
			driver.findElement(By.xpath("//input[@name=\"sex\" and @value=2]")).click();
		} else if (str.equals("Female")) {
			driver.findElement(By.xpath("//input[@name=\"sex\" and @value=1]"));
		} else {
			driver.findElement(By.xpath("//input[@name=\"sex\" and @value=-1]"));
		}
		driver.findElement(By.name("websubmit")).click();
		WebElement alert = driver.findElement(By.id("reg_error_inner"));
		if (alert.isDisplayed()) {
			System.out.println("Test cases passed");
		}
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement button = driver.findElement(By.id("login"));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name=\\\"sex\\\" and @value=-1]")));
		button.click();
	}

	@DataProvider(name = "RegistrationData")
	private Object[][] dataprovider() {
		return new Object[][] { { "fname", "lname", "validemail@gmail.com", "validpass", "4", "Mar", "2000", "Male" },
				{ "firstname", "lastname", "validemail@gmail.com", "validpass", "5", "Sept", "2002", "Female" },
				{ "firstname", "lastname", "validemail@gmail.com", "validpass", "5", "Sept", "2002", "Female" },
				{ "firstname", "lastname", "validemail@.com", "validpass", "5", "Sept", "2002", "Female" },
				{ "firstname", "lastname", "1111111111", "validpass", "5", "Sept", "2002", "Female" },
				{ "firstname", "lastname", "validemail@gmail.com", "validpass", "5", "Sept", "2002", "Custome" }};
	}
}