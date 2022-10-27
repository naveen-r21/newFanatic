package reports;

import java.util.Objects;

import com.aventstack.extentreports.ExtentTest;

public final class ExtentReportManager {
	
	private static ThreadLocal<ExtentTest> extentTestThread = new ThreadLocal<>();
	
	static ExtentTest getExtentTest() {
		return extentTestThread.get();
	}
	
	static void setExtentTest(ExtentTest test) {
		if(Objects.nonNull(test)) {
			extentTestThread.set(test);
		}
	}
	
	static void unloadTest() {
		extentTestThread.remove();
	}

}
