package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base.ProjectSpecificationMethods;

public class HomePage extends ProjectSpecificationMethods {

	//Constructor
	public HomePage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	//Locating elements
	@FindBy(id = "signin2")
	WebElement SignUpClick;
	
	@FindBy(id = "login2")
	WebElement LoginClick;
	
	//Methods
	public SignUpPage SignUpLinkClick()
	{
		SignUpClick.click();
		return new SignUpPage(driver);
	}
	
	public LoginPage LoginLinkClick()
	{
		LoginClick.click();
		return new LoginPage(driver);
	}
}
