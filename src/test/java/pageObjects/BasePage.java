package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class BasePage {
	
	WebDriver driver;
	
	public  BasePage (WebDriver driver) {  // recieve the driver
		/*this is reusable componant of all the page object classes 
		 so all the page object classes should be derived from base page
		 because to achieve the reusability
		 
		*/
		this.driver = driver;  //intiate the driver
		PageFactory.initElements(driver,this);
		
	}
	

	
	
	

}
