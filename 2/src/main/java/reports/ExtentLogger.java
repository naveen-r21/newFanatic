package reports;

import java.util.Objects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;

import frameworkEnums.ConfigProperties;
import utilities.PropertyUtils;
import utilities.ScreenshotUtils;

public final class ExtentLogger {

	private static Logger logger = LogManager.getLogger();

	public static void pass(String message) {

		if (PropertyUtils.get(ConfigProperties.LOGSTEPSINCONSOLE).trim().equalsIgnoreCase("yes")) {
			logger.info(message);
		}

		if (!Objects.isNull(ExtentReportManager.getExtentTest())) {
			if (PropertyUtils.get(ConfigProperties.SCREENSHOTFORPASSEDSTEPS).trim().equalsIgnoreCase("yes")) {
				ExtentReportManager.getExtentTest().pass(message,
						MediaEntityBuilder.createScreenCaptureFromBase64String(ScreenshotUtils.getBase64Image()).build());
			} else {
				ExtentReportManager.getExtentTest().pass(message);
			}
		}
	}

	public static void fail(String message) {
		logger.error(message);

		if (!Objects.isNull(ExtentReportManager.getExtentTest())) {
			ExtentReportManager.getExtentTest().fail(message,
					MediaEntityBuilder.createScreenCaptureFromBase64String(ScreenshotUtils.getBase64Image()).build());
		}
	}

	public static void skip(String message) {

		if (PropertyUtils.get(ConfigProperties.LOGSTEPSINCONSOLE).trim().equalsIgnoreCase("yes")) {
			logger.info(message);
		}

		if (!Objects.isNull(ExtentReportManager.getExtentTest())) {
			if (PropertyUtils.get(ConfigProperties.SCREENSHOTFORSKIPPEDSTEPS).trim().equalsIgnoreCase("yes")) {
				ExtentReportManager.getExtentTest().skip(message,
						MediaEntityBuilder.createScreenCaptureFromBase64String(ScreenshotUtils.getBase64Image()).build());
			} else {
				ExtentReportManager.getExtentTest().skip(message);
			}
		}
	}

	public static void log(String message) {
		
		if (PropertyUtils.get(ConfigProperties.LOGSTEPSINCONSOLE).trim().equalsIgnoreCase("yes")) {
			logger.info(message);
		}

		if (!Objects.isNull(ExtentReportManager.getExtentTest())) {
			ExtentReportManager.getExtentTest().log(Status.INFO, message);
		}
	}

	public static void print(String message) {

		if (PropertyUtils.get(ConfigProperties.LOGSTEPSINCONSOLE).trim().equalsIgnoreCase("yes")) {
			logger.info(message);
		}
	}
}
