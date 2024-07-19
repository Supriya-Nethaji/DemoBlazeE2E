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

public class SignUpPage extends ProjectSpecificationMethods {
	
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	
	//Constructor
	public SignUpPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	//Locating elements
	
	@FindBy(id="sign-username")
	WebElement SignUserName;
	
	@FindBy(id="sign-password")
	WebElement SignPassword;
	
	@FindBy(xpath="//button[text()='Sign up']")
	WebElement SignButton;
	
	@FindBy(xpath="(//button[contains(@class,'btn btn-secondary')])[2]")
	WebElement CloseButton;
	
	//Methods
	public SignUpPage enterUserName(String UserName, String Password)
	{
		wait.until(ExpectedConditions.visibilityOf(SignUserName));
		SignUserName.sendKeys(UserName);
		
		wait.until(ExpectedConditions.visibilityOf(SignPassword));
		SignPassword.sendKeys(Password);
		
		return this;
	}
	
	public void clickButton()
	{
		SignButton.click();
		
	}
	
	public void SignUpValidation(String Type) throws IOException, InterruptedException
	{
		wait.until(ExpectedConditions.alertIsPresent());
		Alert alert = driver.switchTo().alert();
		SoftAssert sassert = new SoftAssert();
		if(Type.equalsIgnoreCase("Valid")) 
		{
		String actual= alert.getText();
		String expected = "Sign up successful.";
		sassert.assertEquals(actual, expected);
		sassert.assertAll();
		alert.accept();
		Thread.sleep(3000);
		}
		else if(Type.equalsIgnoreCase("Invalid"))
		{
			String actual= alert.getText();
			String expected = "This user already exist.";
			sassert.assertEquals(actual, expected);
			sassert.assertAll();
			alert.accept();
			Thread.sleep(3000);
		}
		else if(Type.equalsIgnoreCase("PassBlank")|| (Type.equalsIgnoreCase("UserBlank")))
		{
			String actual= alert.getText();
			String expected = "Please fill out Username and Password.";
			sassert.assertEquals(actual, expected);
			sassert.assertAll();
			alert.accept();
			Thread.sleep(3000);
		}
		TakeScreenshot(Type);
	}
	
	public HomePage clickClose()
	{
		CloseButton.click();
		return new HomePage(driver);
	}


}
