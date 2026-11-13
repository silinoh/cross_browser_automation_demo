package base_class;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import utils.Excel_utils;

public class Base_class {
	
	protected WebDriver driver;
	
	@BeforeClass
	@Parameters("browser")
	public void browser_setup(String parameter_value) throws Exception
	{
		// Open excel sheet
		Excel_utils.openExcel("Login");  // in src/test/resources–  sheetName
		
		if(parameter_value.equalsIgnoreCase("chrome_browser"))
		{
			driver = new ChromeDriver();
		}
		
		else if(parameter_value.equalsIgnoreCase("firefox_browser"))
		{
			driver = new FirefoxDriver();
		}
		
		else if(parameter_value.equalsIgnoreCase("edge_browser"))
		{
			driver = new EdgeDriver();
		}
		
		else
		{
			throw new IllegalArgumentException("invalid browser" + parameter_value);
		}
		
		driver.manage().window().maximize();
		System.out.println("application launced in : " + parameter_value);
	}
	
	@BeforeMethod
	public void url_launch()
	{
		driver.get("https://practicetestautomation.com/practice-test-login/");
	}
	
	@AfterClass
	public void tear_down() throws Exception
	{
		Excel_utils.saveAndClose();
		driver.quit();
	}

}
