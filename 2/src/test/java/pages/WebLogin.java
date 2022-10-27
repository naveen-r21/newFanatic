package pages;

import java.util.HashMap;

import org.testng.Assert;

import master.MasterPage;
import pageElements.WebLoginPageElements;
import reports.ExtentLogger;

public class WebLogin extends MasterPage implements WebLoginPageElements{

	public WebLogin ValidUserLogin(HashMap<String, String> loginpage) throws Throwable {

		try {
			if (!findElementPresence(LoginText)) {
				ExtentLogger.fail("Not able to launch Web Url");
                throw new Exception("Not able to launch Web Url");
            }
		   ExtentLogger.pass("User is in Login Page");
			
			enterData(LoginEmail, loginpage.get("USERNAME"));
			ExtentLogger.print("Username entered successfully ");
			enterData(LoginPassword, loginpage.get("PASSWORD"));
			ExtentLogger.print("Password entered successfully ");
			clickElement(Loginbutton);
			waitUntil(5000);
			if (!findElementPresence(LibraryText)) {
			ExtentLogger.fail("Unable to Login");
                throw new Exception("Unable to Login");
            }
			ExtentLogger.pass("Login Success");
		} catch (Exception e) {
			Assert.fail("Unable to Login. " + e.getMessage());
		}
		return this ;
	}
}
