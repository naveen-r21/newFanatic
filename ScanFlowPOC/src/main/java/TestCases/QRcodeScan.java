package TestCases;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;

import org.testng.Reporter;

import io.github.bonigarcia.wdm.WebDriverManager;

public class QRcodeScan {

	private static final String String = null;
	static WebDriver driver;
	public static ExtentSparkReporter htmlReports;
	public static ExtentReports extent;
	public static ExtentTest test;
	public static ExtentTest parenttest;
	public static ExtentTest childtest;


	@BeforeSuite
	public void setUp() throws InterruptedException {

		WebDriverManager.chromedriver().setup();

		driver = new ChromeDriver();

		Reporter.log("Launching chrome driver",true);

		String url = "https://zxing.org/w/decode.jspx";

		Reporter.log("Launching URL : "+url);

		driver.get(url);

		driver.manage().window().maximize();

		Thread.sleep(1000);

		LocalDateTime dateTime = LocalDateTime.now();
		System.out.println();
		String[] x = dateTime.toString().split("T");
		String t = x[1].substring(0, 5).replace(":", " ");

		htmlReports = new ExtentSparkReporter(".//Reports//Scanflow_" + x[0] + " " + t + ".html");


		extent = new ExtentReports();
		extent.attachReporter(htmlReports);
		htmlReports.config().setTheme(Theme.DARK);
		htmlReports.config().setDocumentTitle("Scanflow Report");
		htmlReports.config().setReportName("Scanflow Automation POC Report");

		parenttest = extent.createTest("Scanflow POC");
	}


	@Test
	public static void InvalidscanQR() throws InterruptedException, IOException {


		childtest = parenttest.createNode("Invalid QR code scan");

		WebElement upload = driver.findElement(By.xpath("//input[@type='file']"));

		String imagePath = ".//TestData//InvalidQR.jpg";

		String uploadPath = System.getProperty("user.dir") + imagePath;

		upload.sendKeys(uploadPath);

		Reporter.log("Uploading the invalid QR Image",true);

		WebElement submit = driver.findElement(By.xpath("(//input[@type='submit'])[2]"));

		Thread.sleep(1000);

		submit.click();


		String error = driver.findElement(By.xpath("//div[@id='main']")).getText();


		Reporter.log("Invalid QR Code Error : "+error);

		childtest.log(Status.PASS, MarkupHelper.createLabel("Invalid QRcode Error Text", ExtentColor.BLUE));
		childtest.info(error);

		System.out.println("error: "+error);

		Reporter.log("Attaching Error Screenshot");
		String scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.BASE64);
		
		childtest.log(Status.PASS, MarkupHelper.createLabel("Invalid QRcode Error Screenshot", ExtentColor.BLUE));
		childtest.info(MediaEntityBuilder.createScreenCaptureFromBase64String(scrFile).build());

		Thread.sleep(1000);

		driver.navigate().back();

	}

	@Test
	public static void validscanQR() throws InterruptedException, IOException {

		childtest = parenttest.createNode("Valid QR code scan");

		WebElement upload = driver.findElement(By.xpath("//input[@type='file']"));

		String imagePath = ".//TestData//ValidQR.png";

		String uploadPath = System.getProperty("user.dir") + imagePath;

		upload.sendKeys(uploadPath);

		Reporter.log("Uploading the Valid QR Image",true);

		WebElement submit = driver.findElement(By.xpath("(//input[@type='submit'])[2]"));

		Thread.sleep(1000);

		submit.click();

		String type = driver.findElement(By.xpath("//tr[4]//td[2]")).getText();

		System.out.println("Result type: "+ type);

		childtest.log(Status.PASS, MarkupHelper.createLabel("Extracted QRcode Result Type", ExtentColor.BLUE));
		childtest.info(type);

		Reporter.log("Extracting the Result Type : "+type);

		Thread.sleep(1000);

		String text = driver.findElement(By.xpath("//tr[5]//td[2]")).getText();

		System.out.println("Result: "+ text);

		childtest.log(Status.PASS, MarkupHelper.createLabel("Extracted QRcode Result Text", ExtentColor.BLUE));
		childtest.info(text);


		Reporter.log("Extracting the Text : "+text);

		Reporter.log("Attaching Result Screenshot");

		String scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.BASE64);
		//		  File screenshotName = new File (".//ScreenShots//"+System.currentTimeMillis()+"_"+".png");
		//		  FileUtils.copyFile(scrFile, screenshotName);
		//		  Reporter.log("<br><img src='"+screenshotName+"' height='500' width='800'/><br>"); 

		childtest.log(Status.PASS, MarkupHelper.createLabel("Extracted QRcode Result Screenshot", ExtentColor.BLUE));
		childtest.info(MediaEntityBuilder.createScreenCaptureFromBase64String(scrFile).build());

		Thread.sleep(1000);


	}
	
//@SuppressWarnings("unused")
//public static void main(String[] args) throws Exception {
//		
//		try {
//			String path = ".//TestData//InvalidQR.jpg";
//			
//			BufferedImage bf = ImageIO.read(new FileInputStream(path));
//			
//			BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(
//					new BufferedImageLuminanceSource(bf)));
//			
//			Result result = new MultiFormatReader().decode(bitmap);
//			
//			String text =		result.toString();
//			
//		//	String text = result.getText();
//			
//			if(text == null) {
//				
//				System.out.println("Valid");
//			}
//			
//			System.out.println(text);
//			System.out.println(result.getBarcodeFormat());
//			
//		
//			
//			//System.out.println(result.getText());
//			
//		
//		} catch(Exception e) {
//			
//		}
//	}


	@AfterSuite
	public void tearUp() {

		Reporter.log("Closing the browser",true);
		extent.flush();
		driver.quit();

	}

}
