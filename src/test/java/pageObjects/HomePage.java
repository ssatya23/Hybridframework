package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy; 


public class HomePage extends BasePage{
	
	
	public HomePage(WebDriver driver) {
		
		super(driver);
		                 /*without constructor we can not invoke the parent class constructor
		                  *this is inheritance reusability 
		                  * 
		                  *  in homepage chiled class how we can invoke the parent class constructor can we invoke the parent class constructor
		                  *  .... yes we can invoke we have
		                  *     we can invoke immediate parent class variable and
		                  *     we  can invoke immediate parent class method
		                  *     we can invoke immediate parent class constructor by super() keyword
		                  * 
		                  */
	}	
		
		@FindBy(xpath="//span[normalize-space()='My Account']")
		WebElement lnkMyaccount;
		
		@FindBy(xpath="//a[normalize-space()='Register']")
		WebElement lnkRegister;
		
		@FindBy(linkText = "Login")
		WebElement linkLogin;
		
		public void clickMyAccount() {
			
			lnkMyaccount.click();
			
		}
		public void clickRegister() {
			
			lnkRegister.click();
		}
		
		public void  clickLogin() {
			linkLogin.click();
		}
		
	}

	
	
	 
	


