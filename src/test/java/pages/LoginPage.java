package pages;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.DataProvider;
import org.testng.asserts.SoftAssert;

import base.ProjectSpecificationMethods;

public class LoginPage extends ProjectSpecificationMethods {
	
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
	//Constructor
	public LoginPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	//Locating elements
	@FindBy(id="loginusername") 
	WebElement LoginUserName;
	
	@FindBy(id="loginpassword")
	WebElement LoginPassword;
	
	@FindBy(xpath="//button[@onClick='logIn()']")
	WebElement LoginButton;
	
	@FindBy(id="nameofuser")
	WebElement AfterLoginName;
	
	@FindBy(xpath="(//button[contains(@class,'btn btn-secondary')])[3]")
	WebElement LoginCloseButton;
	
	//Methods
	public LoginPage LoginUserName(String UserName)
	{
		wait.until(ExpectedConditions.visibilityOf(LoginUserName));
		LoginUserName.sendKeys(UserName);
		return this;
	}
	
	public LoginPage LoginPassword(String Password)
	{
		wait.until(ExpectedConditions.visibilityOf(LoginPassword));
		LoginPassword.sendKeys(Password);
		return this;
	}
	
	public HomePage ClickLogin()
	{
		LoginButton.click();
		return new HomePage(driver);
	}

	public void Validation(String UserName, String Type) throws InterruptedException, IOException
	{
		SoftAssert sassert = new SoftAssert();
		if(Type.equalsIgnoreCase("Valid")) 
		{
		String expectedText = "Welcome "+UserName+"";
		wait.until(ExpectedConditions.visibilityOf(AfterLoginName));
		String Actualtext= AfterLoginName.getText();
		//Soft Assertion
		sassert.assertEquals(Actualtext, expectedText);
		sassert.assertAll();
		}
		else if(Type.equalsIgnoreCase("InvalidPassword"))
		{
			String expectedText = "Wrong password.";
			wait.until(ExpectedConditions.alertIsPresent());
			Alert alert = driver.switchTo().alert();
			String Actualtext= alert.getText();
			//Soft Assertion
			sassert.assertEquals(Actualtext, expectedText);
			sassert.assertAll();
			alert.accept();
			Thread.sleep(3000);
		}
		else if(Type.equalsIgnoreCase("InvalidUser")||Type.equalsIgnoreCase("InvalidUserPwd"))
		{
			String expectedText = "User does not exist.";
			wait.until(ExpectedConditions.alertIsPresent());
			Alert alert = driver.switchTo().alert();
			String Actualtext= alert.getText();
			//Soft Assertion
			sassert.assertEquals(Actualtext, expectedText);
			sassert.assertAll();
			alert.accept();
			Thread.sleep(3000);
		}
		TakeScreenshot(Type);
	}
	
	public HomePage clickCloseButton()
	{
		LoginCloseButton.click();
		return new HomePage(driver);
	}
}
