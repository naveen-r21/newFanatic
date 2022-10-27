package pages;

import org.testng.Assert;

import frameworkEnums.ElementCheckStrategy;
import master.MasterPage;
import pageElements.LibraryPageElements;
import reports.ExtentLogger;

public class Library extends MasterPage implements LibraryPageElements {
	
	public Library DisplayAddVideofunctions() throws Throwable {
		try {
		checkCondition(AddVideos, ElementCheckStrategy.DISPLAYED);
		ExtentLogger.pass("Add video button present");
		clickElement(AddVideos);
		waitUntil(2000);
		if(checkCondition(SelectChannel, ElementCheckStrategy.DISPLAYED) &&
		checkCondition(SelectStation, ElementCheckStrategy.DISPLAYED) &&
		checkCondition(SelectCategory, ElementCheckStrategy.DISPLAYED) &&
		checkCondition(SelectSubCategory, ElementCheckStrategy.DISPLAYED) &&
		checkCondition(VideoTitle, ElementCheckStrategy.DISPLAYED) &&
		checkCondition(ArtistName, ElementCheckStrategy.DISPLAYED) &&
		checkCondition(ReleaseDate, ElementCheckStrategy.DISPLAYED) &&
		checkCondition(Tags, ElementCheckStrategy.DISPLAYED))
		{
		ExtentLogger.pass("SelectChannel,SelectStation,SelectCategory,SelectSubCategory,VideoTitle,ArtistName,ReleaseDate and Tags are Present");
		}
		} catch (Exception e) {
			ExtentLogger.fail("Elements are not present in Add videos");
			Assert.fail("Elements are not present in Add videos" + e.getMessage());
		}
		return this ;
	}
	
	public Library CheckAddVideoFunction() throws Throwable {
		try {
			clickElement(SelectChannel);
			waitUntil(1000);
			ExtentLogger.pass("Channel category are present");
			clickElement(SelectStation);
			waitUntil(1000);
			ExtentLogger.pass("Station category are present");
			clickElement(SelectCategory);
			waitUntil(1000);
			ExtentLogger.pass("Category are present");
			clickElement(SelectSubCategory);
			waitUntil(1000);
			ExtentLogger.pass("Subcategory are present");
	
		} catch (Exception e) {
			ExtentLogger.fail("Elements are not displayed category in Add video");
			Assert.fail("Elements are not displayed category in Add video" + e.getMessage());
		}
		return this ;
	}
	
	public Library CategoryfieldChange() throws Throwable {
		try {
			clickElement(SelectChannel);
			waitUntil(1000);
			dropdown("FanaticTV");
			clickElement(SelectCategory);
			waitUntil(1000);
			dropdown("Music Videos");
			clickElement(SelectSubCategory);
			waitUntil(1000);
			ExtentLogger.pass("Category field changed-Music video");
			
			clickElement(SelectCategory);
			waitUntil(1000);
			dropdown("Fan Videos");
			clickElement(SelectSubCategory);
			waitUntil(1000);
			ExtentLogger.pass("Category field changed-Fan video");
			
			clickElement(SelectCategory);
			waitUntil(1000);
			dropdown("Funny Videos");
			clickElement(SelectSubCategory);
			waitUntil(1000);
			ExtentLogger.pass("Category field changed-Funny video");
			
		} catch (Exception e) {
			ExtentLogger.fail("Category field are not changed");
			Assert.fail("Category field are not changed" + e.getMessage());
		}
		return this ;
	}
	
	public Library deleteOption() throws Throwable {
		try {
			clickElement(Cancel);
			checkCondition(Delete, ElementCheckStrategy.DISPLAYED);
			clickElement(Delete);
			waitUntil(1000);
			checkCondition(Deletetext, ElementCheckStrategy.DISPLAYED);
			clickElement(DeleteYes);
			ExtentLogger.pass("User checks Delete Option by clicking Yes");
			
			clickElement(Delete);
			waitUntil(1000);
			checkCondition(Deletetext, ElementCheckStrategy.DISPLAYED);
			clickElement(DeleteNo);
			ExtentLogger.pass("User checks Delete Option by clicking No");
		} catch (Exception e) {
			ExtentLogger.fail("User not able to checks Delete Options");
			Assert.fail("User not able to checks Delete Options" + e.getMessage());
		}
		return this ;
	}
	
	public Library AddvideobyfillingDetails() throws Throwable {
		try {
			clickElement(AddVideos);
			waitUntil(2000);
			clickElement(SelectChannel);
			dropdown("FanaticTV");
			clickElement(SelectStation);
			dropdown("FanCam");
			clickElement(SelectCategory);
			dropdown("Fan Videos");
			clickElement(SelectSubCategory);
			dropdown("Comedy");
			enterData(VideoTitle, "songs");
			enterData(ArtistName, "vijay");
			clickElement(ReleaseDate);
			calendar("17");
			enterData(Tags, "Comedy");
			
		} catch (Exception e) {
			ExtentLogger.fail("Category field are not changed");
			Assert.fail("Category field are not changed" + e.getMessage());
		}
		return this ;
	}
	
	public Library AddvideoByNotFilling() throws Throwable {
		try {
			clickElement(AddVideos);
			waitUntil(2000);
			clickElement(SelectChannel);
			clickElement(SelectStation);
			clickElement(SelectCategory);
			clickElement(SelectSubCategory);
			clickElement(VideoTitle);
			clickElement(ArtistName);
			clickElement(ReleaseDate);
			clickElement(Tags);
			
			if(checkCondition(ChooseChannel, ElementCheckStrategy.DISPLAYED) &&
					checkCondition(ChooseStation, ElementCheckStrategy.DISPLAYED) &&
					checkCondition(ChooseCategory, ElementCheckStrategy.DISPLAYED) &&
					checkCondition(ChooseSubCategory, ElementCheckStrategy.DISPLAYED) &&
					checkCondition(Error, ElementCheckStrategy.DISPLAYED) )
					{
					ExtentLogger.pass("Error Message is displayed");
					}
			
		} catch (Exception e) {
			ExtentLogger.fail("Error Message is not displayed");
			Assert.fail("Error Message is not displayed" + e.getMessage());
		}
		return this ;
	}
	
	public Library AddvideobyfillingDetailsWithoutVideo() throws Throwable {
		try {
			clickElement(AddVideos);
			waitUntil(2000);
			clickElement(SelectChannel);
			dropdown("FanaticTV");
			clickElement(SelectStation);
			dropdown("FanCam");
			clickElement(SelectCategory);
			dropdown("Fan Videos");
			clickElement(SelectSubCategory);
			dropdown("Comedy");
			enterData(VideoTitle, "songs");
			enterData(ArtistName, "vijay");
			clickElement(ReleaseDate);
			calendar("17");
			enterData(Tags, "Comedy");
			waitUntil(2000);
			clickElement(Save);
			
			if(checkCondition(ErrorForAddVideo, ElementCheckStrategy.DISPLAYED)) {
				ExtentLogger.pass("Error Message is displayed for not adding Video");	
			}
		} catch (Exception e) {
			ExtentLogger.fail("Error Message is not displayed for not adding Video");
			Assert.fail("Error Message is not displayed for not adding Video" + e.getMessage());
		}
		return this ;
	}
	
	public Library CancelAddVideo() throws Throwable {
		try {
			clickElement(AddVideos);
			waitUntil(2000);
			javascriptupload("C:\\FanaticVideo\\Funnyvideo.mp4");
			clickElement(Cancel);
			ExtentLogger.pass("Added Video is Cancelled");	
			if (findElementPresence(LibraryText)) {
				ExtentLogger.pass("Admin Navigate to Library screen");	
			}
		} catch (Exception e) {
			ExtentLogger.fail("Added Video is not Cancelled");
			Assert.fail("Added Video is not Cancelled" + e.getMessage());
		}
		return this ;
	}
	
	public Library AddvideobyfillingDetailsWithVideo() throws Throwable {
		try {
			clickElement(AddVideos);
			waitUntil(2000);
			clickElement(SelectChannel);
			dropdown("FanaticTV");
			clickElement(SelectStation);
			dropdown("FanCam");
			clickElement(SelectCategory);
			dropdown("Fan Videos");
			clickElement(SelectSubCategory);
			dropdown("Comedy");
			waitUntil(3000);
			javascriptupload("C:\\FanaticVideo\\Funnyvideo.mp4");
			waitUntil(2000);
			enterData(VideoTitle, "songs");
			enterData(ArtistName, "vijay");
			clickElement(ReleaseDate);
			calendar("17");
			enterData(Tags, "Comedy");
			waitUntil(2000);
			clickElement(Save);
			ExtentLogger.pass("Admin Add video and fill all the Details");	
		} catch (Exception e) {
			ExtentLogger.fail("Admin not able to Add video and fill all the Details");
			Assert.fail("Admin not able to Add video and fill all the Details" + e.getMessage());
		}
		return this ;
	}
	
}
