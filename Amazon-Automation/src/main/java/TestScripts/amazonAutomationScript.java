package TestScripts;

import java.io.IOException;
import java.text.NumberFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Currency;
import java.util.Locale;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
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

import TestBase.baseTest;
import io.github.bonigarcia.wdm.WebDriverManager;

public class amazonAutomationScript extends baseTest {

	public void addMenHat() throws Exception {
		
		parenttest = extent.createTest("Amazon Automation Report");

		childtest = parenttest.createNode("Add Men's Hat");

		String mainWindow = driver.getWindowHandle();

		WebElement searchBox = getwebElement("SearchIcon");
		
		String menHat = Repository.getProperty("manHat");
		wait(searchBox);
		searchBox.sendKeys(menHat);
		Reporter.log("Searching : "+menHat,true);
		childtest.log(Status.PASS, MarkupHelper.createLabel("Entering Search Term", ExtentColor.BLUE));
		childtest.info(menHat);
		
		String homePageScreenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.BASE64);
		childtest.log(Status.PASS, MarkupHelper.createLabel("Homepage", ExtentColor.BLUE));
		childtest.info(MediaEntityBuilder.createScreenCaptureFromBase64String(homePageScreenshot).build());

		WebElement searchBtn = getwebElement("SearchBtn");
		wait(searchBtn);
		searchBtn.click();
		Reporter.log("Clicked on search button",true);

		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
		
		WebElement hat = getwebElement("MenHat");
		wait(hat);
		hat.click();
		Reporter.log("Selected the first hat",true);

		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());

		for(String actual: tabs)
		{

			if(!actual.equalsIgnoreCase(mainWindow))
			{
			
				driver.switchTo().window(actual);

			}

		}
		Reporter.log("Moved to new tab",true);

		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
		
		WebElement quantityBtn = getwebElement("QuantityBtn");
		wait(quantityBtn);
		quantityBtn.click();
		Reporter.log("Clicked on Quantity button",true);

		WebElement sizeBtn = getwebElement("MenHatSizeBtn");
		wait(sizeBtn);
		sizeBtn.click();
		Reporter.log("Selected Quantity size to 2",true);

		WebElement addCart = getwebElement("AddCartBtn");
		wait(addCart);
		addCart.click();
		Reporter.log("Item added to the cart",true);

		WebElement CartBtn = getwebElement("CartPageBtn");
		wait(CartBtn);
		CartBtn.click();
		Reporter.log("Clicked on Cart button",true);

		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(15));
		
		String cartPage = ((TakesScreenshot)driver).getScreenshotAs(OutputType.BASE64);
		childtest.log(Status.PASS, MarkupHelper.createLabel("Men Hat Cart Page ", ExtentColor.BLUE));
		childtest.info(MediaEntityBuilder.createScreenCaptureFromBase64String(cartPage).build());

		WebElement priceElement = getwebElement("ItemPrice");
		wait(priceElement);
		String priceText =	priceElement.getText();
		Reporter.log("Retreiving the text as amount",true);
		
		String priceValue = priceText.replaceAll("\\s", ""); 

		String hatQuantity = getwebElement("ItemQuantity").getText();
		Reporter.log("Men hat total Quantity: "+hatQuantity,true);
		childtest.log(Status.PASS, MarkupHelper.createLabel("Men Hat Quantity", ExtentColor.BLUE));
		childtest.info(hatQuantity);

		float actual = Float.parseFloat(priceValue) * Float.parseFloat(hatQuantity);
		formatter.setMinimumFractionDigits(currency.getDefaultFractionDigits());
		String actualTotal = formatter.format(actual);
		Reporter.log("Actual Total Amount: "+actualTotal,true);
		childtest.log(Status.PASS, MarkupHelper.createLabel("Men hat Total Actual Amount", ExtentColor.BLUE));
		childtest.info(actualTotal);

		String subTotal = getwebElement("SubTotal").getText();
		String[] subTotalValue = subTotal.split(":");
		String value = subTotalValue[1];
		String	expectedTotal = value.replaceAll("\\s", ""); 
		Reporter.log("Expected Total Amount: "+expectedTotal,true);
		childtest.log(Status.PASS, MarkupHelper.createLabel("Men hat Total Expected Amount", ExtentColor.BLUE));
		childtest.info(expectedTotal);

		Assert.assertEquals(actualTotal, expectedTotal);
		Reporter.log("Actual amount matches expected amount",true);

		driver.navigate().refresh();

	}

	public void addWomenHat() throws Exception {
		
		childtest = parenttest.createNode("Add Women's Hat");

		String mainWindow = driver.getWindowHandle();

		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
		WebElement searchBox = getwebElement("SearchIcon");
		String womenHat = Repository.getProperty("womenHat");
		wait(searchBox);
		searchBox.sendKeys(womenHat);
		Reporter.log("Searching : "+womenHat,true);
		childtest.log(Status.PASS, MarkupHelper.createLabel("Entering Search Term", ExtentColor.BLUE));
		childtest.info(womenHat);
		
		String homePageScreenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.BASE64);
		childtest.log(Status.PASS, MarkupHelper.createLabel("Homepage", ExtentColor.BLUE));
		childtest.info(MediaEntityBuilder.createScreenCaptureFromBase64String(homePageScreenshot).build());

		WebElement searchBtn = getwebElement("SearchBtn");
		wait(searchBtn);
		searchBtn.click();
		Reporter.log("Clicked on search button",true);

		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
		
		WebElement hat = getwebElement("WomenHat");
		wait(hat);
		hat.click();
		Reporter.log("Selected the first hat",true);

		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());

		for(String actual: tabs)
		{

			if(!actual.equalsIgnoreCase(mainWindow))
			{
			
				driver.switchTo().window(actual);

			}

		}
		Reporter.log("Moved to new tab",true);

		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
		
		WebElement quantityBtn = getwebElement("QuantityBtn");
		wait(quantityBtn);
		quantityBtn.click();
		Reporter.log("Clicked on Quantity button",true);

		WebElement sizeBtn = getwebElement("WomenHatSizeBtn");
		wait(sizeBtn);
		sizeBtn.click();
		Reporter.log("Selected Quantity size to 1",true);

		WebElement addCart = getwebElement("AddCartBtn");
		wait(addCart);
		addCart.click();
		Reporter.log("Item added to the cart",true);

		WebElement CartBtn = getwebElement("CartPageBtn");
		wait(CartBtn);
		CartBtn.click();
		Reporter.log("Clicked on Cart button",true);

		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(15));

		WebElement womenHatPriceElement = getwebElement("ItemPrice");
		wait(womenHatPriceElement);
		String  womenHatPriceText =	womenHatPriceElement.getText();
		String womenHatPriceValue = womenHatPriceText.replaceAll("\\s", ""); 
		Reporter.log("Retreiving the text as amount",true);
		
		String womenHatQuantity = getwebElement("ItemQuantity").getText();
		Reporter.log("Women hat quantity: "+womenHatQuantity,true);
		childtest.log(Status.PASS, MarkupHelper.createLabel("Women Hat Total Quantity", ExtentColor.BLUE));
		childtest.info(womenHatQuantity);
		
		String WomenHatCart = ((TakesScreenshot)driver).getScreenshotAs(OutputType.BASE64);
		childtest.log(Status.PASS, MarkupHelper.createLabel("Women Hat Cart Page ", ExtentColor.BLUE));
		childtest.info(MediaEntityBuilder.createScreenCaptureFromBase64String(WomenHatCart).build());

		float actualWomenHatTotal = Float.parseFloat(womenHatPriceValue) * Float.parseFloat(womenHatQuantity);
		formatter.setMinimumFractionDigits(currency.getDefaultFractionDigits());
		String actualWomenHatTotalPrice = formatter.format(actualWomenHatTotal);
		Reporter.log("Women Hat Total Amount: "+womenHatPriceValue,true);
		childtest.log(Status.PASS, MarkupHelper.createLabel("Women Hat Total Amount", ExtentColor.BLUE));
		childtest.info(womenHatPriceValue);
		
		WebElement menHatPrice = getwebElement("MenHatTotalPrice");
		wait(menHatPrice);
		String menHatPriceText = menHatPrice.getText();
		//Reporter.log("Men hat Price: "+menHatPriceText,true);

		String quantityMenHat = getwebElement("MenHatTotalQuantity").getText();
		Reporter.log("Men hat quantity: "+quantityMenHat,true);
		childtest.log(Status.PASS, MarkupHelper.createLabel("Men Hat Total Quantity", ExtentColor.BLUE));
		childtest.info(quantityMenHat);

		float actualMenHatTotal = Float.parseFloat(menHatPriceText) * Float.parseFloat(quantityMenHat);
		formatter.setMinimumFractionDigits(currency.getDefaultFractionDigits());
		String actualMenHatTotalPrice = formatter.format(actualMenHatTotal);
		Reporter.log("Men hat total amount: "+actualMenHatTotalPrice,true);
		childtest.log(Status.PASS, MarkupHelper.createLabel("Men Hat Total Amount", ExtentColor.BLUE));
		childtest.info(actualMenHatTotalPrice);
		
		float sumOfMenAndWomenHat = actualWomenHatTotal + actualMenHatTotal;
		String actualSumOfPrice = formatter.format(sumOfMenAndWomenHat);
		Reporter.log("Actual Sum of Men hat and Women hat amount: "+actualSumOfPrice,true);
		childtest.log(Status.PASS, MarkupHelper.createLabel("Actual Sum Of Men Hat and Women Hat Price", ExtentColor.BLUE));
		childtest.info(actualSumOfPrice);
		
		String subTotal = getwebElement("SubTotal").getText();
		String[] subTotalValue = subTotal.split(":");
		String value = subTotalValue[1];
		String expectedSumOfPrice = value.replaceAll("\\s", ""); 
		Reporter.log("Expected Sum of Men hat and Women hat amount: "+expectedSumOfPrice,true);
		childtest.log(Status.PASS, MarkupHelper.createLabel("Expected Sum Of Men Hat and Women Hat Price", ExtentColor.BLUE));
		childtest.info(expectedSumOfPrice);
		
		Assert.assertEquals(actualSumOfPrice, expectedSumOfPrice);
		Reporter.log("Actual amount matches expected amount",true);

		driver.navigate().refresh();

	}
	
	public void sumOfMenAndWomenHat() throws Exception {
		
		
		childtest = parenttest.createNode("Final Sum of Men and Women Hat");

		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
		
		WebElement quantityBox = getwebElement("QuantityBox");
		wait(quantityBox);
		quantityBox.click();
		Reporter.log("clicked on Quantity Button",true);
		
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
		WebElement selectQuantity = getwebElement("SelectQuantity");
		wait(selectQuantity);
		selectQuantity.click();
		Reporter.log("Changed Men hat quantity to 1",true);

		Thread.sleep(3000);
		
		String finalCart = ((TakesScreenshot)driver).getScreenshotAs(OutputType.BASE64);
		childtest.log(Status.PASS, MarkupHelper.createLabel("Final Sum of amount in Cart Page ", ExtentColor.BLUE));
		childtest.info(MediaEntityBuilder.createScreenCaptureFromBase64String(finalCart).build());
		
		WebElement womenHatPriceElement = getwebElement("ItemPrice");
		wait(womenHatPriceElement);
		String  womenHatPriceText =	womenHatPriceElement.getText();
		String womenHatPriceValue = womenHatPriceText.replaceAll("\\s", ""); 
		
		String womenHatQuantity = getwebElement("ItemQuantity").getText();
		Reporter.log("Women hat quantity: "+womenHatQuantity,true);
		childtest.log(Status.PASS, MarkupHelper.createLabel("Women Hat Total Quantity", ExtentColor.BLUE));
		childtest.info(womenHatQuantity);

		float actualWomenHatTotal = Float.parseFloat(womenHatPriceValue) * Float.parseFloat(womenHatQuantity);
		formatter.setMinimumFractionDigits(currency.getDefaultFractionDigits());
		String actualWomenHatTotalPrice = formatter.format(actualWomenHatTotal);
		Reporter.log("Women Hat Total Amount: "+actualWomenHatTotalPrice,true);
		childtest.log(Status.PASS, MarkupHelper.createLabel("Women Hat Total Amount", ExtentColor.BLUE));
		childtest.info(actualWomenHatTotalPrice);
		
		WebElement menHatPrice = getwebElement("MenHatTotalPrice");
		wait(menHatPrice);
		String menHatPriceText = menHatPrice.getText();
		Reporter.log("Men hat price: "+menHatPriceText,true);

		String quantityMenHat = getwebElement("MenHatTotalQuantity").getText();
		Reporter.log("Men hat quantity: "+quantityMenHat,true);
		childtest.log(Status.PASS, MarkupHelper.createLabel("Men Hat Total Quantity", ExtentColor.BLUE));
		childtest.info(quantityMenHat);

		float actualMenHatTotal = Float.parseFloat(menHatPriceText) * Float.parseFloat(quantityMenHat);
		formatter.setMinimumFractionDigits(currency.getDefaultFractionDigits());
		String actualMenHatTotalPrice = formatter.format(actualMenHatTotal);
		Reporter.log("Men hat total amount: "+actualMenHatTotalPrice,true);
		childtest.log(Status.PASS, MarkupHelper.createLabel("Men Hat Total Amount", ExtentColor.BLUE));
		childtest.info(actualMenHatTotalPrice);
		
		float sumOfMenAndWomenHat = actualWomenHatTotal + actualMenHatTotal;
		String actualSumOfPrice = formatter.format(sumOfMenAndWomenHat);
		Reporter.log("Actual Sum of Men hat and Women hat amount: "+actualSumOfPrice,true);
		childtest.log(Status.PASS, MarkupHelper.createLabel("Actual Sum Of Men Hat and Women Hat Price", ExtentColor.BLUE));
		childtest.info(actualSumOfPrice);

		String subTotal = getwebElement("SubTotal").getText();
		String[] subTotalValue = subTotal.split(":");
		String value = subTotalValue[1];
		String expectedSumOfPrice = value.replaceAll("\\s", ""); 
		Reporter.log("Expected Sum of Men hat and Women hat amount: "+expectedSumOfPrice,true);
		childtest.log(Status.PASS, MarkupHelper.createLabel("Expected Sum Of Men Hat and Women Hat Price", ExtentColor.BLUE));
		childtest.info(expectedSumOfPrice);
		
		Assert.assertEquals(actualSumOfPrice, expectedSumOfPrice);
		Reporter.log("Actual amount matches expected amount",true);
		
		
	}


	

}
