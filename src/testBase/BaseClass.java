package testBase;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import utility.UtilClass;

public class BaseClass extends UtilClass {

	@BeforeSuite
	public void startReport() {
		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
		ExtentSparkReporter reporter = new ExtentSparkReporter(System.getProperty("user.dir") + "/target/result-" + timeStamp + ".html");
		extent = new ExtentReports();
		extent.attachReporter(reporter);
	}

	@BeforeClass
	public void detail() {
		test = extent.createTest(testName, testDescription);
		test.assignAuthor(testAuthor);
	}

	@BeforeMethod()
	public void main() throws Exception {
		LaunchBrowser(readProperty("browser"));
		launch(readProperty("url"));
	}

	@AfterMethod()
	public void teardown() {
		driver.quit();
	}

	@AfterSuite
	public void endReport() {
		extent.flush();
	}
}