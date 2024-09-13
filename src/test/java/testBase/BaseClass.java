package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Properties;

import javax.print.DocFlavor.URL;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.testng.annotations.Parameters;


import  org.apache.logging.log4j.LogManager;


public class BaseClass {

public	WebDriver driver;

public  org.apache.logging.log4j.Logger logger; // log4j
 
 public Properties p;
 
 DesiredCapabilities cap;
	
	//@BeforeClass(groups = {"Sanity","regression" ,"Master", "DataDriven"} )
	@Parameters({"os" , "browser"})
	public void setup(String os, String br) throws IOException 
	{  
		
		
        // loading config.properties file       // .// represents current project location
		FileReader file = new  FileReader("./src//test//resources//config.properties");
		//now we want to load this file
		p=new Properties();
		p.load(file);
		
		logger= LogManager.getLogger(this.getClass());
		
		if(p.getProperty("execution_env").equalsIgnoreCase("Remote")) {
			
			DesiredCapabilities capabilities = new DesiredCapabilities();
			//os(operating system)
			
			if(os.equalsIgnoreCase("windows")) {
				
				capabilities.setPlatform(Platform.WIN11);
				
			}else if(os.equalsIgnoreCase("mac")){
				capabilities.setPlatform(Platform.MAC);
			}else if(os.equalsIgnoreCase("linux"))
			{  capabilities.setPlatform(Platform.LINUX);
				
			}else {
				System.out.println(" No matching ");
				return;
			}
			
			//browser
			switch(br.toLowerCase()) 
			{
			case "chrome": capabilities.setBrowserName("chrome"); break;
			case "edge"  : capabilities.setBrowserName("MicrosoftEdge");break;
			case "firefox"  : capabilities.setBrowserName("Firefox");break;
			default: System.out.println("No matching browser"); return;
			}
			
		//s	driver = new RemoteWebDriver(new URL ("http://localhost:4444/wd/hub"), capabilities);	
		}
		
		if(p.getProperty("execution_env").equalsIgnoreCase("local")) {
			
			switch(br.toLowerCase()) {
			case "chrome" : driver= new ChromeDriver();break;
			case "edge"   : driver = new EdgeDriver(); break;
			case "firefox" : driver= new FirefoxDriver(); break;
			default : System.out.println("invalid browser name..."); return;
			}
			
		}
		
		
		
		
		
		
		
		
		driver = new ChromeDriver();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		driver.get(p.getProperty("appURL1"));   //reading url from properties file.
		driver.manage().window().maximize();
		
		
	}
	
	//@AfterClass (groups = {"Sanity","regression" ,"Master", "DataDriven"} )	
	public void teardown() {
		
		driver.quit();
	}
	
	public String randomeString() {
		
		String generatedstring = RandomStringUtils.randomAlphabetic(5);
		return generatedstring;
	}
	
	public String randomNumber() {
		String generatedstring = RandomStringUtils.randomNumeric(10);
		return generatedstring;
	}
	
	public String randomeSAlphaNumberic() {
		
		String generatedstring =RandomStringUtils.randomAlphabetic(3)
;
		String generatednumber=RandomStringUtils.randomNumeric(3);
		
		return(generatedstring+"@"+generatednumber);
		
	}
	
	public String  captureScreen(String tname) {
		String timeStamp =  new   SimpleDateFormat("yyyyMMddhhmmss").format(new Date(0));
		
		TakesScreenshot takeScreenshot = (TakesScreenshot) driver;
		
	    File sourcefile = takeScreenshot.getScreenshotAs(OutputType.FILE);
		
	    String targetFilePath = System.getProperty("user.dir")+"\\screenshots\\"+ "_"+timeStamp+".png";
	    
	   File targetfile = new File(targetFilePath);
	    
	    sourcefile.renameTo(targetfile);
	    
	    return targetFilePath;
	}
	
}
