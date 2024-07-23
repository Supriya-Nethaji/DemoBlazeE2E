package test;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import base.ProjectSpecificationMethods;
import pages.HomePage;
import pages.LoginPage;

public class TC002_LoginTest extends ProjectSpecificationMethods{
	@BeforeTest
	public void setUp()
	{
		sheetnum = 1;
	}
	@Test(dataProvider = "Data")
	public  void Login(String UserName, String Password,String Type) throws Exception 
	{
		HomePage obj = new HomePage(driver);
		
		obj.LoginLinkClick();
		
		LoginPage obj1= new LoginPage(driver);
		
		obj1.LoginUserName(UserName);
		obj1.LoginPassword(Password);
		obj1.ClickLogin();
		Thread.sleep(2000);
		obj1.Validation(UserName,Type);
		Thread.sleep(2000);
		
		if(Type.equalsIgnoreCase("InvalidPassword")||(Type.equalsIgnoreCase("InvalidUser"))||(Type.equalsIgnoreCase("InvalidUserPwd")))
		{
		obj1.clickCloseButton();
		}
		
		//obj.closeBrowser();
	}

}
