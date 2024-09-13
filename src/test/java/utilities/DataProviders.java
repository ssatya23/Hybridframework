package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {
	// DataProviders
	 @DataProvider(name="LoginData")
	public String [][] getData() throws IOException {
		              /* i put dot/  slash this is also similar of system.getproperty of user.d 
		               *   earlier if you want to get the current project location we used 
		               */
		String path =".\\testData\\Opencart_LoginData.xlsx";  // taking xl file from testData
		
		ExcelUtility xlutil = new ExcelUtility(path); // creating an object for XLUtility
		
		int totalrows = xlutil.getRowCount("Sheet1");
		
		int totalcols = xlutil.getCellCount("Sheet1",1);
		
		String logindata[][]= new String[totalrows][totalcols];
		
		for(int i=1;i<=totalrows;i++) // read the data from xl storing  in two dimensional array
		{
			for(int j=0;j<totalcols;j++)  // 0  i is rows j is col
			{
				
				logindata[i-1][j]=xlutil.getCellData("sheet1",i,j);  //1, 0
				// why i-1 because array index will start from  zero array index start form zero
			}
		}
		return logindata; // returning two dimension array
		
		
	}
	
  // DataProvider2
}
