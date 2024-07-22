package utils;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;

public class UtilityClass 
{
	public static WebDriver driver;
	public static Properties prop;
	public String Screenshotname;
	public int sheetnum;
	
	//Launch the browser
	public void launchBrowser(String browser, String url)
	{
		if(browser.equalsIgnoreCase("Chrome"))
		{
			driver= new ChromeDriver();
		}else if(browser.equalsIgnoreCase("Firefox")){
			driver = new FirefoxDriver();
		}else if(browser.equalsIgnoreCase("Edge")){
			driver = new EdgeDriver();
		}
		
		driver.get(url);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	}
	
	//Read property file
	public void readPropertyFile() throws Exception
	{
		FileReader file = new FileReader("C:\\Users\\USER\\Desktop\\GuviMini\\DemoShopE2E\\src\\test\\resources\\Data\\Data.properties");
		prop= new Properties();
		prop.load(file);
	}
	
	//Reading data from excel
	public String[][] ReadExcel(int num) throws Exception
	{
		String FilePath= "C:\\Users\\USER\\Desktop\\GuviMini\\DemoShopE2E\\src\\test\\resources\\Data\\Book2.xlsx";
		XSSFWorkbook book = new XSSFWorkbook(FilePath);
		XSSFSheet sheet = book.getSheetAt(num);
		int rowCount = sheet.getLastRowNum();
		int columnCount = sheet.getRow(0).getLastCellNum();
		
		String [] [] data = new String[rowCount][columnCount];
		for(int i=1;i<=rowCount;i++)
		{
			XSSFRow row = sheet.getRow(i);
			for(int j=0;j<columnCount;j++)
			{
				XSSFCell cell = row.getCell(j);
				data[i-1][j] = cell.getStringCellValue();
			}
		}
		
		book.close();
		return data;
	}
	
	public String TakeScreenshot(String name) throws IOException
	{
		String path = "C:\\Users\\USER\\Desktop\\GuviMini\\DemoShopE2E\\Screenshots\\"+name+".png";
		File source = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		File dest = new File(path);
		FileUtils.copyFile(source, dest);
		return path;
	}
	
	
}
