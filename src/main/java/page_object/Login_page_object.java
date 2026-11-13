package page_object;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Login_page_object {
	
	WebDriver driver;
	WebDriverWait wait;
	
	// Locate elements
	private By username = By.cssSelector("input[id = 'username']");
	private By password = By.cssSelector("input[id = 'password']");
	private By submit = By.cssSelector("button[id = 'submit']");
	private By error_message = By.cssSelector("div[id = 'error']");
	
	// constructor
	public Login_page_object(WebDriver driver)
	{
		this.driver = driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}
	
	// action methods
	public void enter_username(String u_name)
	{
		driver.findElement(username).sendKeys(u_name);
	}
	
	public void enter_password(String pwd)
	{
		driver.findElement(password).sendKeys(pwd);
	}
	
	public void click_submit()
	{
		driver.findElement(submit).click();
	}
	
	public String get_url()
	{
		return driver.getCurrentUrl();
	}
	
	public String error_message()
	{
		WebElement error_element = wait.until(ExpectedConditions.visibilityOfElementLocated(error_message));
		String error_text = error_element.getText();
		return error_text;
	}

}
