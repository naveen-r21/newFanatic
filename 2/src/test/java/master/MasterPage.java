package master;

import org.openqa.selenium.By;
import org.testng.Assert;

import applicationEnums.MasterMenuList;
import pages.Library;
import reports.ExtentLogger;
import pageElements.HomePageElements;
import utilities.DynamicXpathUtils;

public class MasterPage extends MasterWrapper implements HomePageElements{
	
	public Library navigateToLibraryPage() {
		try {
			waitUntil(3000);
			clickElement(By.xpath(DynamicXpathUtils.getXpathForEnum(linkPageMenu, MasterMenuList.library)));
		//	clickElement(Library);
			waitUntil(3000);
			ExtentLogger.pass("Navigated to Library page");
		} catch (Exception e) {
			Assert.fail("Unable to Navigate to Library Page. " + e.getMessage());
		}
		return new Library();
	}
	
	

}
