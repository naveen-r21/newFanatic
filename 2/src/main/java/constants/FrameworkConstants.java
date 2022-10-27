package constants;

public final class FrameworkConstants {

	private static final int EXPLICITWAIT = 5;
	private static final int IMPLICITWAIT = 5;
	private static final int PAGELOADTIMEOUT = 30;
	private static final int FAILEDTESTCASERETRYCOUNT = 1;
	private static final String RESOURCESPATH = System.getProperty("user.dir") + "/src/test/resources";
	private static final String CONFIGFILEPATH = RESOURCESPATH + "/config/config.properties";
	private static final String EXCELPATH = RESOURCESPATH + "/excel/TestDataSheet.xlsx";
	private static final String JSONPATH = RESOURCESPATH + "/json/";
	private static final String TESTMANAGERSHEETNAME = "TestCaseManager";
	private static final String EXTENTREPORTSPATH = System.getProperty("user.dir") + "/Report/FanaticTvReport.html";
	private static final String LOG4JCONFIGFILEPATH = System.getProperty("user.dir") + "/config/log4j2.xml";
	private static final String RUNTIMEPROPERTYFILEPATH = RESOURCESPATH + "/config/RunTimeData.properties";

	public static int getExplicitWait() {
		return EXPLICITWAIT;
	}

	public static int getImplicitWait() {
		return IMPLICITWAIT;
	}

	public static int getPageLoadTimeout() {
		return PAGELOADTIMEOUT;
	}

	public static int getFailedTestCaseRetryCount() {
		return FAILEDTESTCASERETRYCOUNT;
	}

	public static String getResourcesPath() {
		return RESOURCESPATH;
	}

	public static String getConfigFilePath() {
		return CONFIGFILEPATH;
	}

	public static String getExcelPath() {
		return EXCELPATH;
	}

	public static String getJsonPath() {
		return JSONPATH;
	}

	public static String getExtentReportFilePath() {
		return EXTENTREPORTSPATH;
	}
	
	public static String getLog4jConfigFilePath() {
		return LOG4JCONFIGFILEPATH;
	}

	public static String getTestManagerSheetName() {
		return TESTMANAGERSHEETNAME;
	}
	public static String getRuntimePropertyFilePath() {
		return RUNTIMEPROPERTYFILEPATH;
	}

}
