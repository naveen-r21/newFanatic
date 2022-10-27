package drivers;

import java.util.Objects;

import org.openqa.selenium.WebDriver;

public final class DriverManager {
	
	private static ThreadLocal<WebDriver> driverThread = new ThreadLocal<>() ;
	
	public static WebDriver getDriver() {
		return driverThread.get();
	}
	
	static void setDriver(WebDriver driverReference) {
		if(Objects.nonNull(driverReference)) {
			driverThread.set(driverReference);
		}
	}
	
	static void unloadDriver() {
		driverThread.remove();
	}

}
