package master;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.google.common.util.concurrent.Uninterruptibles;

import drivers.Driver;
import drivers.DriverManager;
import factories.ExplicitWaitFactory;
import frameworkEnums.ElementCheckStrategy;
import frameworkEnums.SelectStrategy;
import frameworkEnums.WaitStrategy;
import pages.WebLogin;
import utilities.RuntimePropertyFileUtils;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;

public class MasterWrapper {

	protected WebLogin weblogin;
//	private WebDriver ;
	protected static ThreadLocal<String> testCaseThread = new ThreadLocal<>();

	public String getTestCaseName() {
		return testCaseThread.get();
	}

	public void setTestCaseName(String testcasename) {
		testCaseThread.set(testcasename);
	}

	@BeforeSuite
	protected void suiteSetup() {
		try {
			Runtime.getRuntime().exec("taskkill /F /IM chromedriver.exe");
			Runtime.getRuntime().exec("taskkill /F /IM geckodriver.exe");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@BeforeMethod
	protected void methodSetup(Method method) {
		Driver.initDriver();
		setTestCaseName(method.getName());
		System.out.println(getTestCaseName()+" Execution Started.");
		weblogin = new WebLogin();
		RuntimePropertyFileUtils.createRunTimePropFile();
	}

	@AfterMethod
	protected void methodTearDown() {
		Driver.quitDriver();
		System.out.println(getTestCaseName()+" Execution Completed.");
	}

	public static void enterData(By elementLocator, String data) throws Exception {
		try {
			ExplicitWaitFactory.explicitWaitUntil(WaitStrategy.PRESENCE, elementLocator);
			clearData(elementLocator);
			DriverManager.getDriver().findElement(elementLocator).sendKeys(data);
		} catch (Exception e) {
			throw new Exception("Unable to Enter Data in: " + elementLocator);
		}
	}
	
	public  static String getData(By elementLocator) throws Exception {
		String data = null;
		try {
			findElementPresence(elementLocator);
			data = DriverManager.getDriver().findElement(elementLocator).getText();
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Unable to Get Data from the Locator: " + elementLocator);
		}
		return data;
	}

	public  static void clickElement(By elementLocator) throws Exception {
		try {
			ExplicitWaitFactory.explicitWaitUntil(WaitStrategy.CLICKABLE, elementLocator);
			DriverManager.getDriver().findElement(elementLocator).click();
		} catch (Exception e) {
			throw new Exception("Unable to click: " + elementLocator);
		}
	}
	
	public static boolean findElementPresence(By elementLocator) throws Exception {
		int size = 0;
		try {
			size = DriverManager.getDriver().findElements(elementLocator).size();
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Unable to Find Element with Locator: " + elementLocator);
		}
		return size > 0 ? true : false;
	}
	
	public static void clearData(By elementLocator) throws Exception {
		try {
			ExplicitWaitFactory.explicitWaitUntil(WaitStrategy.PRESENCE, elementLocator);
			DriverManager.getDriver().findElement(elementLocator).clear();
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Unable to Clear Data in the locator" + elementLocator);
		}
	}
	
	public  static boolean checkCondition(By elementLocator, ElementCheckStrategy checkCondition) throws Exception {
		try {
			if (checkCondition.equals(ElementCheckStrategy.DISPLAYED)) {
				return DriverManager.getDriver().findElement(elementLocator).isDisplayed();
			} else if (checkCondition.equals(ElementCheckStrategy.ENABLED)) {
				return DriverManager.getDriver().findElement(elementLocator).isEnabled();
			} else if (checkCondition.equals(ElementCheckStrategy.SELECTED)) {
				return DriverManager.getDriver().findElement(elementLocator).isSelected();
			} else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Unable to Verify " + elementLocator + " " + checkCondition.toString());
		}
	}
	
	public  static  void selectOption( By elementLocator, SelectStrategy strategy, String option) throws Exception {
		try {
			
			WebElement selectElement = DriverManager.getDriver().findElement(elementLocator);  
			Select selectDropDown = new Select(selectElement);
			
			if (strategy.equals(SelectStrategy.VISIBLETEXT)) {
				selectDropDown.selectByVisibleText(option);
			} 
			else if (strategy.equals(SelectStrategy.INDEX)) {
				selectDropDown.selectByIndex(Integer.parseInt(option));
			} 
			else if (strategy.equals(SelectStrategy.VALUE)) {
				selectDropDown.selectByValue(option);
			} 
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Unable to Select " + option + " in " + elementLocator+" Dropdown");
		}
		
	}
	
	public  static void executeJS(String command, By elementLocator) throws Exception{
		try {
			ExplicitWaitFactory.explicitWaitUntil(WaitStrategy.PRESENCE, elementLocator);
			JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
			js.executeScript(command, elementLocator);
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Unable to execute the command "+command+" on the element "+elementLocator);
		}
		
	}
	public  static void waitUntil(long milliSec) {
		Uninterruptibles.sleepUninterruptibly(milliSec, TimeUnit.MILLISECONDS);
	}

	public  static void downArrowKeyPressMCQ() {
		Actions action = new Actions(DriverManager.getDriver());
        action.sendKeys(Keys.DOWN).perform();
//        action.sendKeys(Keys.DOWN).perform();
        action.sendKeys(Keys.ENTER).perform();
       
    }
	public  static void doubleClick(By elementLocator) {
		
		Actions action = new Actions(DriverManager.getDriver());
		action.doubleClick(DriverManager.getDriver().findElement(elementLocator)).perform();
	}
	public static  void ClickEnter() {
		Actions action = new Actions(DriverManager.getDriver());
        action.sendKeys(Keys.ENTER).perform();
		
	}
	public static void moveToElement(By elementLocator) {

		Actions action = new Actions(DriverManager.getDriver());
		action.moveToElement(DriverManager.getDriver().findElement(elementLocator)).perform();
		
	}
	
	public static void moveToElementClick(By elementLocator) {

		Actions action = new Actions(DriverManager.getDriver());
		action.moveToElement(DriverManager.getDriver().findElement(elementLocator)).click().perform();
		
	}
	public void ImplicitWait() {
		DriverManager.getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
public  static void javascriptupload(String filepath) {
    WebElement element = DriverManager.getDriver().findElement(By.xpath("//input[@type='file']"));
    JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
    js.executeScript("arguments[0].style.display='block';", element);
    DriverManager.getDriver().findElement(By.xpath("//input[@type='file']")).sendKeys(filepath);
}

public static void dragAndDrop(By projectnamefrom, By draganddrop) {
	Actions action = new Actions(DriverManager.getDriver());
	action.dragAndDrop(DriverManager.getDriver().findElement(projectnamefrom), DriverManager.getDriver().findElement(draganddrop)).perform();
}
public void scrollDown() {
	JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
	js.executeScript("window.scrollBy(0,1000)", "");
}
public void javaClick(By elementLocator) {
	JavascriptExecutor executor = (JavascriptExecutor)DriverManager.getDriver();
	executor.executeScript("arguments[0].click();", elementLocator);
}

public static String captureScreenshot() throws IOException {

	File srcFiler = ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.FILE);
	byte[] encoded;
	try {
		encoded = Base64.encodeBase64(FileUtils.readFileToByteArray(srcFiler));
		String s = new String(encoded, StandardCharsets.US_ASCII);
		return s;

	} catch (IOException e) {
		e.printStackTrace();

	}
	return null;

}

public static void dropdown(String searchText) {
    List<WebElement> p = DriverManager.getDriver().findElements(By.xpath("//ul[@class='dropdown-menu w-100 show']//li"));
  //            System.out.println("Menu Items are: ");
              //iterate through list
//              for( WebElement i: p){
//                 System.out.println(i.getText());
//                 }
              
              for( WebElement i: p){
                     if (i.getText().equals(searchText))
                        {
                            i.click(); // click the desired option
                            break;
                        }
                     }
    }

 public static void calendar(String number)
 {
	 List<WebElement> allDates=DriverManager.getDriver().findElements(By.xpath("//table[@class='days weeks']//td"));
		
for(WebElement ele:allDates)
{
	String date=ele.getText();
	if(date.equalsIgnoreCase(number))
	{
		ele.click();
		break;
	}
	
}
 }


}
