package drivers;

import java.net.MalformedURLException;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import constants.FrameworkConstants;
import customExceptions.DriverException;
import factories.DriverFactory;
import frameworkEnums.ConfigProperties;
import utilities.PropertyUtils;

public final class Driver {

	public static void initDriver() {
		String appURL = PropertyUtils.get(ConfigProperties.URL);
		String browser = PropertyUtils.get(ConfigProperties.BROWSER);
		String headlessMode = PropertyUtils.get(ConfigProperties.HEADLESSMODE);

		if (Objects.isNull(DriverManager.getDriver())) {
			try {
				
				DriverManager.setDriver(DriverFactory.createDriverSession(browser, headlessMode));
			} catch (MalformedURLException e) {
				
				throw new DriverException("Unable to Configure Driver. Please Check the Driver Configurations.");
			} catch (Exception e) {
				
				throw new DriverException("Unable to Configure and Launch Driver. "+e.getMessage());
			}

			DriverManager.getDriver().get(appURL);

			DriverManager.getDriver().manage().window().maximize();
			DriverManager.getDriver().manage().timeouts().implicitlyWait(FrameworkConstants.getImplicitWait(),
					TimeUnit.SECONDS);
			DriverManager.getDriver().manage().timeouts().pageLoadTimeout(FrameworkConstants.getPageLoadTimeout(),
					TimeUnit.SECONDS);
		}

	}

	public static void quitDriver() {
		if (Objects.nonNull(DriverManager.getDriver())) {
			DriverManager.getDriver().quit();
			DriverManager.unloadDriver();
		}
	}

}
