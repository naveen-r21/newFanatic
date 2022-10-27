package TestBase;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.NumberFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Currency;
import java.util.Locale;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.github.dockerjava.api.model.Repository;

import io.github.bonigarcia.wdm.WebDriverManager;

public class baseTest {
	
	public static WebDriver driver;
	public static ExtentSparkReporter htmlReports;
	public static ExtentReports extent;
	public static ExtentTest test;
	public static ExtentTest parenttest;
	public static ExtentTest childtest;
	public static WebDriverWait wait;
	public static NumberFormat formatter;
	public static Currency currency;
	public static Properties Repository = new Properties();
	public File f;
	public FileInputStream FI;
	public InputStream input;
	
	public void loadpropertiesFile() throws IOException {

		f = new File("./src/main/java/Properties/webElements.properties");
		FI = new FileInputStream(f);
		Repository.load(FI);

		f = new File("./src/main/java/Properties/data.properties");
		FI = new FileInputStream(f);
		Repository.load(FI);
		
	}

	@BeforeSuite
	public void setUp() throws InterruptedException, IOException {

		loadpropertiesFile();
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		Reporter.log("Launching chrome driver",true);

		String url = Repository.getProperty("URL");
		Reporter.log("Launching URL : "+url,true);
		driver.get(url);
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
		driver.manage().window().maximize();

		LocalDateTime dateTime = LocalDateTime.now();
		String[] x = dateTime.toString().split("T");
		String t = x[1].substring(0, 5).replace(":", " ");

		htmlReports = new ExtentSparkReporter(".//Reports//AmazonAutomation_" + x[0] + " " + t + ".html");
		extent = new ExtentReports();
		extent.attachReporter(htmlReports);
		htmlReports.config().setTheme(Theme.DARK);
		htmlReports.config().setDocumentTitle("Amazon Automation Report");
		htmlReports.config().setReportName("Automation Extent Report");
		
		
		formatter = NumberFormat.getInstance(new Locale("en", "IN"));
		currency = Currency.getInstance(new Locale("en", "IN"));
	}
	
	public static void wait(WebElement webelement) {
		
		wait = new WebDriverWait(driver,Duration.ofSeconds(15)); 
		wait.until(ExpectedConditions.visibilityOf(webelement));
		
	}
	
	public static WebElement getlocators(String locator) throws Exception {
		String[] split = locator.split("`");
		String locatortype = split[0];
		String locatorvalue = split[1];

		if (locatortype.toLowerCase().equals("xpath"))
			return driver.findElement(By.xpath(locatorvalue));
		else
			throw new Exception("Unknown locator type '" + locatortype + "'");
	}
	public WebElement getwebElement(String locator) throws Exception {

		return getlocators(Repository.getProperty(locator));
	}
	

	@AfterMethod
	public void tearDown(ITestResult result) throws IOException {

		if (result.getStatus() == ITestResult.FAILURE) {
			childtest.log(Status.FAIL,
					MarkupHelper.createLabel(result.getName() + " - Test Case Failed", ExtentColor.RED));
			childtest.log(Status.FAIL,
					MarkupHelper.createLabel(result.getThrowable() + " - Test Case Failed", ExtentColor.RED));
		} else if (result.getStatus() == ITestResult.SKIP) {
			childtest.log(Status.SKIP,
					MarkupHelper.createLabel(result.getName() + " - Test Case Skipped", ExtentColor.ORANGE));

		} else if (result.getStatus() == ITestResult.SUCCESS) {
			childtest.log(Status.PASS,
					MarkupHelper.createLabel(result.getName() + " - Test Case Completed", ExtentColor.GREEN));

		}

	}
	@AfterSuite
	public void tearUp() {

		Reporter.log("Closing the browser",true);
		extent.flush();
		driver.quit();

	}

}
