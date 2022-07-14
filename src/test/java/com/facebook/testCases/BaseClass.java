package com.facebook.testCases;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.facebook.utilities.Readconfig;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {
	Readconfig readcon = new Readconfig();
	public WebDriver driver;
	public String url = readcon.getApplicationURL();
	public WebElement optionButton;
	public static Logger logger;

	@Parameters("browser")
	@BeforeClass
	public void startBrowser(String brws) {
		logger = Logger.getLogger(BaseClass.class.getName());
		PropertyConfigurator.configure("log4j.properties");

		if (brws.equals("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			logger.info("chrome browser opening");
			driver.manage().window().maximize();
		} else if (brws.equals("ie")) {
			WebDriverManager.iedriver().setup();
			driver = new InternetExplorerDriver();
			logger.info("internet explorer browser opening");
			driver.manage().window().maximize();
		} else if (brws.equals("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			logger.info("firefox browser opening");
			driver.manage().window().maximize();
		}  else if (brws.equals("edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			logger.info("microsoft edge browser opening");
			driver.manage().window().maximize();
		}
		logger.info("chrome browser opened");
		// WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(5));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(url);
	}

	@AfterClass
	public void closeBrowser() {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		driver.close();
	}
	public void loginAccount() {
		
	}
	public void logout() {
		optionButton = driver
				.findElement(By.xpath("//div[contains(@class,'q9uorilb l9j0dhe7 pzggbiyp du4w35lb')]//self::div[1]"));
		optionButton.click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		driver.findElement(By.xpath("//span[text()='Log Out']")).click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(7));
		
	}public void removeAccountButton() {
		driver.findElement(By.xpath("//div[@class=\"uiContextualLayerParent _csi\"]//child::a[2]")).click();
	}

	public void captureScreenShot(WebDriver driver, String tname) throws IOException {
		String timestamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File target = new File("./Screenshot/" + timestamp + ".png");
		FileUtils.copyFile(source, target);
		System.out.println("screenshot taken");
	}
}
