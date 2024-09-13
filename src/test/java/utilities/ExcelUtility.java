package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility {
	
	public FileInputStream fileinput;
	
	public FileOutputStream fileoutput;
	
	public XSSFWorkbook workbook;
	
	public XSSFSheet sheet;
	
	public XSSFRow row;
	
	public XSSFCell cell;
	
	public CellStyle style;
	String path ;
	
	public ExcelUtility(String path) {
		
		this.path=path;    
		/*
		 * at time of object creation we need to pass Excel path 
		 * becuase there is a constructor  inside the Excel utility
		 * what this constructor will do 
		 * this constructor will take path of  the excel and initiate it same thing we have done
		 */
		
		
	}
	
	public int getRowCount(String sheetName) throws IOException{
		
		fileinput =new FileInputStream(path);
		
		workbook =new XSSFWorkbook(fileinput);
		sheet=workbook.getSheet(sheetName);
		
		int rowcount = sheet.getLastRowNum();	
		workbook.close();
		fileinput.close();
		return rowcount;
		
	}
	public int getCellCount(String sheetName,int rownum) throws IOException {
		
		fileinput =new FileInputStream(path);
		workbook =new XSSFWorkbook(fileinput);
		sheet=workbook.getSheet(sheetName);
		row=sheet.getRow(rownum);
		int cellcount = row.getLastCellNum();
		workbook.close();
		fileinput.close();
		return cellcount;
		
	}
public String getCellData(String sheetName,int rownum,int colnum) throws IOException {
	
	fileinput =new FileInputStream(path);
	workbook =new XSSFWorkbook(fileinput);
	sheet=workbook.getSheet(sheetName);
	row=sheet.getRow(rownum);
	cell = row.getCell(colnum);
	 
	DataFormatter formatter = new DataFormatter();
	
	String data;
	try {
		data= formatter.formatCellValue(cell);  // return the formatted value of a cell as a String regardless
	
	}catch(Exception e) {
		data="";
	}
	workbook.close();
	fileinput.close();
	return data;
	
    }

public void setCelldata(String sheetName, int rownum, int colnum, String data) throws IOException
  {
	
	  File xlfile = new File(path);
	
	   if(!xlfile.exists())  // if file not exists then create new file
	   {
		   workbook = new XSSFWorkbook();
		   fileoutput = new FileOutputStream(path);
		   workbook.write(fileoutput);
	   }
	       fileinput = new FileInputStream(path);
	       workbook = new XSSFWorkbook(fileinput);
	
	
	    if(workbook.getSheetIndex(sheetName)==-1) // if sheet not exists then  create new sheet
	        	workbook.createSheet(sheetName);
	         sheet = workbook.getSheet(sheetName);
	
	    if(sheet.getRow(rownum)==null)
		   sheet.createRow(rownum);
	       row=sheet.getRow(rownum);
	    
	       cell = row.createCell(colnum);
	       cell.setCellValue(data);
	       fileoutput = new FileOutputStream(path);
	       workbook.write(fileoutput);
	       workbook.close();
	       fileoutput.close();
	     
  }
	
	public void fillGreencolor(String sheetName, int rownum, int colnum) throws IOException
	{
		
		fileinput =new FileInputStream(path);
		workbook =new XSSFWorkbook(fileinput);
		sheet=workbook.getSheet(sheetName);
		
		row=sheet.getRow(rownum);
		cell = row.getCell(colnum);
		
		style = workbook.createCellStyle();
		style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		
		cell.setCellStyle(style);
		workbook.write(fileoutput);
		workbook.close();
		fileinput.close();
		fileoutput.close();
		
	}
	
public void fillRedcolor(String sheetName, int rownum, int colnum ) throws IOException
   {
		
		fileinput =new FileInputStream(path);
		workbook =new XSSFWorkbook(fileinput);
		sheet=workbook.getSheet(sheetName);
		
		row=sheet.getRow(rownum);
		cell = row.getCell(colnum);
		
		style = workbook.createCellStyle();
		style.setFillForegroundColor(IndexedColors.RED.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		
		cell.setCellStyle(style);
		workbook.write(fileoutput);
		workbook.close();
		fileinput.close();
		fileoutput.close();
		
	}	
		
	
	
}


