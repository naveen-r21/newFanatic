package factories;

import java.net.MalformedURLException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public final class DriverFactory {
	
	public static WebDriver createDriverSession(String browser, String headlessMode) throws MalformedURLException {
		
		WebDriver driver=null;
		
		switch (browser.toLowerCase()) {
		case "chrome":
			WebDriverManager.chromedriver().setup();
			
			if(headlessMode.equalsIgnoreCase("yes")) {
				ChromeOptions options=new ChromeOptions();
				options.addArguments("--headless");
				options.addArguments("--disable-extensions");
				options.addArguments("--disable-gpu");
				options.addArguments("--no-sandbox");
				options.addArguments("--disable-dev-shm-usage");
				driver= new ChromeDriver(options);
				
			}
			else {
				driver = new ChromeDriver();
			}
			break;
			
		case "firefox":
			WebDriverManager.firefoxdriver().setup();
			
			if(headlessMode.equalsIgnoreCase("yes")) {
				FirefoxOptions options=new FirefoxOptions();
				options.addArguments("--headless");
				driver=new FirefoxDriver(options);
			}
			else {
				driver = new FirefoxDriver();
			}
			
			break;
		}
		
		return driver;
		
	}

}
