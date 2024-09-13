package testCases;

import org.junit.Test;
import org.testng.Assert;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC002_LoginTest extends BaseClass{
  //@Test(groups= {"Sanity"," Master"})
	public void verify_login() {
		logger.info("*****Starting TC_002_LoginTest***");
		try {
	   // homepage
		HomePage hp= new HomePage(driver);
		         hp.clickMyAccount();
		         hp.clickLogin();
		       
		//loginpage         
		LoginPage lp = new LoginPage(driver);         
		        
		          lp.setEmail(p.getProperty("email"));
		          lp.setPassword(p.getProperty("password"));
		          lp.clickLogin();
		 //MyAccount  
		          MyAccountPage macc = new MyAccountPage(driver);
		          
		       boolean targetPage = macc.isMyAccountPageExists();
		       
		     //  Assert.assertEquals(targetPage,true,"Login failed");
		       Assert.assertTrue(targetPage);   
		      }
		catch(Exception e) {
			Assert.fail();
		}
		logger.info("***Finished TC-002_loginTest*****");
	}
	
	
}
