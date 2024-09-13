package utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;

import javax.print.DocFlavor.URL;

import org.apache.xmlbeans.impl.xb.xsdschema.ListDocument.List;
import org.testng.ITestContext;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testBase.BaseClass;

public class ExtentReportManager {
	public ExtentSparkReporter sparkReporter;  // UI of the report
	public ExtentReports extent;  // populate common info on the report
	public ExtentTest test;     // createing test cases entries in the report and update status of the test method
	String reportName;
	public void onStart ( ITestContext testContext) {
		
		/*
		 * SimpleDateFormat df = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
		 * Date dt = new Date();
		 * String currentdatetimestamp = df.format(dt);
		 * 
		 */
		
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date(0));
		reportName = "Test-Report-"+timeStamp+".html";
		sparkReporter = new ExtentSparkReporter(".\\reports\\"+reportName); //specify location
		
		sparkReporter.config().setDocumentTitle(" Opencart Automation Report"); // Title of report
		
		sparkReporter.config().setReportName("opencart Functional Testing"); // name of the report
		sparkReporter.config().setTheme(Theme.DARK);
		
		 extent = new ExtentReports();
		 extent.attachReporter(sparkReporter);
		 extent.setSystemInfo("Appication", "opencart");
		 extent.setSystemInfo("Module",   "Admin");
		 extent.setSystemInfo("Sub Module", "Customers");
		 extent.setSystemInfo("User Name", System.getProperty("user.name"));
		 extent.setSystemInfo("Environment","QA");
		 
		 String os = testContext.getCurrentXmlTest().getParameter("os");
		 extent.setSystemInfo("Operating System", os);
		 
		 String browser = testContext.getCurrentXmlTest().getParameter("browser");
		 extent.setSystemInfo("Browser", browser);
	 	 
		  java.util.List<String> includedGroups = testContext.getCurrentXmlTest().getIncludedGroups();
		 
		 if(!includedGroups.isEmpty()) {
			 extent.setSystemInfo("Groups", includedGroups.toString());
			 
		 }
	}
	
	public void onTestSuccess(ITestResult result) {
		
		test  = extent.createTest(result.getTestClass().getName()); // create new entry in the report
		
		 test.assignCategory(result.getMethod().getGroups()); // to display groups in report
		 test.log(Status.PASS,result.getName()+"got successfully executed"); // update the status Pass / Fail/Skipped
		
	}
	
	public void onTestFailure(ITestResult result) throws IOException {
		
		test = extent.createTest(result.getName()+"got failed");
		
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.FAIL,result.getName()+"got failed ");
		test.log(Status.INFO, result.getThrowable().getMessage());
		
		
		String imgPath = new BaseClass().captureScreen(result.getName());
		test.addScreenCaptureFromPath(result.getName());
	}

	public void onTestSkipped(ITestResult result) {
		test = extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.SKIP, result.getName()+"got skipped");
		test.log(Status.INFO,result.getThrowable().getMessage());
	}
	
	public void onFinish(ITestContext testContext) {
		
		extent.flush();
		
		String pathOfExtentReport = System.getProperty("user.dir")+"\\reports\\"+reportName;
		File  extentReport = new File (pathOfExtentReport);
		
		try {
			Desktop.getDesktop().browse(extentReport.toURI());
		}catch(IOException e) {
			e.printStackTrace();
		}
	
	
	/*
	try {
	    URL url = new URL("file:///"+System.getProperty("user.dir")+"\\reports\\"+reportName);
	 

	 // Create the email message
	 ImageHtmlEmail email new ImageHtmlEmail();
	 email.setDataSourceResolver(new DataSourceUrlResolver (url));
	 email.setHostName("smtp.googlemail.com");
	 email.setSmtpPort(465);
	 email.setAuthenticator(new DefaultAuthenticator("satya.sms23@gmail.com","password"));
	 email.setSSLOnConnect(true);
	 email.setFrom("pavanoltraining@gmail.com"); //Sender
	 email.setSubject("Test Results");
	 email.setMsg("Please find Attached Report...."); 
	 email.addTo("pavankumar.busyqa@gmail.com"); //Receiver 
	 email.attach(url, "extent report", "please check report..."); 
	 email.send(); // send the email 
	}
	catch(Exception e) {
	 e.printStackTrace();
	 } 
   }
   */
		
	}
}
