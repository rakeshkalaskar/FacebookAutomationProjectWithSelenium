package com.facebook.utilities;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class Reporting extends TestListenerAdapter{
	public WebDriver driver;
	public ExtentReports extent=new ExtentReports();
	public ExtentTest test;
	public ExtentSparkReporter spark;
	
	public void inStart() throws IOException {
		String timestamp=new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		String repname="Test-Report-"+timestamp+".html";	
		spark=new ExtentSparkReporter("./test-output/"+repname);
		spark.loadXMLConfig("./target/extent-config.xml");

		extent.attachReporter(spark);
		
		extent.setSystemInfo("Host name", "localhost");
		extent.setSystemInfo("Environment", "QA");
		extent.setSystemInfo("user", "Rakesh");
		
		spark.config().setDocumentTitle("Facebook Demo Test Project");
		spark.config().setReportName("functional test report");
		spark.config().setTheme(Theme.DARK);
	}
	public void onTestSuccess(ITestResult tr) {
		test=extent.createTest(tr.getName());
		test.log(Status.PASS, MarkupHelper.createLabel(tr.getName(), ExtentColor.GREEN));
	}
	public void onTestFailure(ITestResult tr) {
		test=extent.createTest(tr.getName());
		test.log(Status.FAIL, MarkupHelper.createLabel(tr.getName(),ExtentColor.RED));
		
		String screenshotPath="./Screenshot/"+tr.getName()+".png";
		File f=new File(screenshotPath);
		if(f.exists()) {
			test.fail("screenshot is below:"+test.addScreenCaptureFromPath(screenshotPath));
			//test.log(Status.SKIP, MarkupHelper.createLabel(tr.getName(),ExtentColor.ORANGE));
		}
	}
	public void onTestSkipped(ITestResult tr) {
		test=extent.createTest(tr.getName());
		test.log(Status.SKIP, MarkupHelper.createLabel(tr.getName(), ExtentColor.ORANGE));
	}
	public void onFinish(ITestResult tr) {
		extent.flush();
	}
	
}
