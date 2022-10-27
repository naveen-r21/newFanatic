package pageElements;

import org.openqa.selenium.By;

public interface WebLoginPageElements {
	
//Login Elements
	By LoginText = By.xpath("//h1[text()='Login']");
	By LoginEmail = By.xpath("//input[@placeholder='User Name']");
	By LoginPassword = By.xpath("//input[@type='password']");
	By Loginbutton = By.xpath("//button[@type='submit']");
	By LibraryText = By.xpath("//h1[text()='Library']");
	

}
