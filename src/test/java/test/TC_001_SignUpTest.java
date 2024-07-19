package test;

import java.time.Duration;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import base.ProjectSpecificationMethods;
import pages.HomePage;
import pages.SignUpPage;

public class TC_001_SignUpTest extends ProjectSpecificationMethods {

	@BeforeTest
	public void setUp()
	{
		sheetnum = 0;
	}
	@Test(dataProvider = "Data")
	public void SignUpLinkClick(String UserName, String Password,String Type) throws Exception 
	{
		Screenshotname = Type;
		HomePage obj = new HomePage(driver);
		
		obj.SignUpLinkClick();
	 
		SignUpPage obj1 = new SignUpPage(driver);
		
		obj1.enterUserName(UserName, Password);
		obj1.clickButton();
		obj1.SignUpValidation(Type);
		Thread.sleep(3000);
		if(Type.equalsIgnoreCase("Invalid")||(Type.equalsIgnoreCase("PassBlank"))||(Type.equalsIgnoreCase("UserBlank")))
		{
		obj1.clickClose();
		}
		
		//obj.closeBrowser();
	}

}
