package base;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;

import utils.UtilityClass;

public class ProjectSpecificationMethods extends UtilityClass {
	
	@Parameters({"browser", "url"})
	@BeforeMethod
	public void browserLaunch(String browser, String url)
	{
		launchBrowser(browser, url);
		
	}
	
	@DataProvider(name = "Data")
	public String[][] getData() throws Exception
	{
		String [] [] data = ReadExcel(sheetnum);
		return data;
	}
	
	
	@AfterMethod
	public void closeBrowser()
	{
		driver.quit();
	}
}
