package pageElements;

import org.openqa.selenium.By;

public interface LibraryPageElements {

// Library
	By Library= By.xpath("//a[@href='/library']");
	By LibraryText = By.xpath("//h1[text()='Library']");
	By AddVideos = By.xpath("//button[@class='btn btn-red px-4']");
	
	By SelectChannel = By.xpath("//button[@id='selectChannel']");
	By SelectStation = By.xpath("//button[@id='selectStation']");
	By SelectCategory = By.xpath("//button[@id='category']");
	By SelectSubCategory = By.xpath("//button[@id='subCategory']");
	By VideoTitle = By.xpath("(//input[@type='text'])[2]");
	By ArtistName = By.xpath("(//input[@type='text'])[3]");
	By ReleaseDate = By.xpath("(//input[@type='text'])[4]");
	By Tags = By.xpath("(//input[@type='text'])[5]");
	By Cancel = By.xpath("//button[@class='btn btn-grey py-2 fs14 px-5 me-3']");
	By Save = By.xpath("//button[@class='btn btn-red py-2 px-5 fs14 ng-star-inserted']");
	By Delete = By.xpath("(//img[@title='Delete'])[1]");
	By Deletetext = By.xpath("//p[@class='fw-500 m-0 fs17']");
	By DeleteYes = By.xpath("//button[@class='btn btn-red py-2 px-5 fs14 ng-star-inserted']");
	By DeleteNo = By.xpath("//button[@class='btn btn-grey py-2 fs14 px-5']");
	
	By ChooseChannel = By.xpath("(//div[@class='dropdown filter-dropdown select-options'])[1]");
	By ChooseStation = By.xpath("(//div[@class='dropdown filter-dropdown select-options'])[2]");
	By ChooseCategory = By.xpath("(//div[@class='dropdown filter-dropdown select-options'])[3]");
	By ChooseSubCategory = By.xpath("(//div[@class='dropdown filter-dropdown select-options'])[4]");
	By Error = By.xpath("//span[@class='text-danger ng-star-inserted']");
	By ErrorForAddVideo = By.xpath("//span[@class='text-danger text-center']");
}
