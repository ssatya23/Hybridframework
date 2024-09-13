package testCases;

import org.junit.Assert;
import org.junit.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC001_AccountRegistrationTest extends BaseClass {
	
	
 // @Test(groups= {"regression","Master"})
  public void verify_account_registration() {
	  
	  logger.info("*****Starting TC001_AccountRegistrationTest  ***");
	  logger.debug("thid is a debug lo message");
	  try {
	  HomePage hp = new HomePage(driver);
	  hp.clickMyAccount();
	  logger.info("Clicked on MyAccount Link");
	  
	  hp.clickRegister();
	  logger.info("Clicked on Register Link");
	  
	  AccountRegistrationPage regpage = new AccountRegistrationPage(driver);
	  
	  logger.info("Provinding customer details...");
	  regpage.setFirstName(randomeString().toUpperCase());  
	  regpage.setLastName(randomeString().toUpperCase());
	  regpage.setEmail(randomeString()+"@gmail.com");//randomly generated the email
	  regpage.setTelephone(randomNumber());
	  
	  String password = randomeSAlphaNumberic();
	  
	  regpage.setPassword(password);
	  regpage.setConfirmPassword(password);
	
	  regpage.setPrivacyPolicy();
	  regpage.clickContinue();
	
	
	 logger.info("validating expected message...");
	String confmsg = regpage.getConfirmationMsg();
	
	if(confmsg.equals("your Account Has Been Created!!!")) {
		Assert.assertTrue(true); 
	}
	else
	{
		logger.error("Test failed");
		logger.debug("Debug logs");
		Assert.assertTrue(false);
	}
	//Assert.assertEquals(confmsg, "Your Account Has Been Created!");
    }
	  catch(Exception e)
	  {
		 logger.error("Test failed..."); 
		 logger.debug("Debug logs...");
		 Assert.fail();
	  }
	  
	  
	logger.info("*****Finished TC001_AccountRegistrationTest ***");
	  
  }
	
}
