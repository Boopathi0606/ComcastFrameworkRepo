package com.comcast.crm.generic.fileutility;

import java.io.FileInputStream;

import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtility {

	public String getDataFromExcel(String sheetName,int rowNum,int cellNum) throws EncryptedDocumentException, IOException {
		FileInputStream fis=new FileInputStream("./configAppData/tsData.xlsx");
		Workbook wb=WorkbookFactory.create(fis);
		String data=wb.getSheet(sheetName).getRow(rowNum).getCell(cellNum).getStringCellValue();
		wb.close();
		return data;
	}
	
	public int getRowCount(String sheetName) throws EncryptedDocumentException, IOException {
		FileInputStream fis=new FileInputStream("./configAppData/tsData.xlsx");
		Workbook wb=WorkbookFactory.create(fis);
		int rowCount=wb.getSheet(sheetName).getLastRowNum();
		wb.close();
		return rowCount;
	}
		
		public void setDataIntoExcel(String sheetName,int rowNum,int cellNum,String data) throws Throwable {
			FileInputStream fis1=new FileInputStream("./configAppData/tsData.xlsx");
			Workbook wb1=WorkbookFactory.create(fis1);
			wb1.getSheet(sheetName).getRow(rowNum).createCell(cellNum);
			FileOutputStream fos=new FileOutputStream("./configAppData/tsData.xlsx");
			wb1.write(fos);
			wb1.close();
			
			
		}
		
		
		
		
		
	
}
