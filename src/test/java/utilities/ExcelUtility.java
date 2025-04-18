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
	
	public static FileInputStream fi;
	public static FileOutputStream fo;
	public static XSSFWorkbook workbook;
	public static XSSFSheet sheet;
	public static XSSFRow row;
	public static XSSFCell cell;
	public static CellStyle style;
	String path;
	
	public  ExcelUtility(String path)
	{
		this.path=path;
	}

	
	public  int getRowCount(String sheetName) throws IOException
	{
		fi=new  FileInputStream(path);
		workbook=new XSSFWorkbook(fi);
		sheet=workbook.getSheet(sheetName);
		int rowcount=sheet.getFirstRowNum();
		workbook.close();
		fi.close();
		return rowcount;
	}
	
	public int getCellCont(String sheetName,int rownum ) throws IOException
	{
		fi=new  FileInputStream( path);
		workbook=new XSSFWorkbook(fi);
		sheet=workbook.getSheet(sheetName);
		row=sheet.createRow(rownum);
		int cellcount=row.getFirstCellNum();
		workbook.close();
		fi.close();
		return cellcount;
	
	}
	
	
	public String getCellData(String sheetNane,int rownum,int colum) throws IOException
	{
		fi=new  FileInputStream( path);
		workbook=new XSSFWorkbook(fi);
		sheet=workbook.getSheet(sheetNane);
		row=sheet.getRow(rownum);
		cell=row.getCell(colum);
		
		DataFormatter formatter=new DataFormatter();
		String data;
		try{
		data=formatter.formatCellValue(cell);  // Return the formatted value of a cell as a String regardless of the cell type.
		}
		catch(Exception e)
		{
			data="";
		}
		
		workbook.close();
		fi.close();
		return data;
		
	}
	public void setCellData(String sheetName,int rownum,int colum,String data) throws IOException
	{
		File xlfile=new  File(path);
		try
		{
		if(!xlfile.exists())   // If file not exist then create new file
		{
		workbook=new XSSFWorkbook();
		fo=new FileOutputStream(path);
		workbook.write(fo);
		}
		
		fi=new  FileInputStream(path);
		workbook=new XSSFWorkbook(fi);
		
		
		if(workbook.getSheetIndex(sheetName)==-1 ) // if sheet not exist then create new sheet
		workbook.createSheet(sheetName);
		sheet=workbook.getSheet(sheetName);
		
		if(sheet.getRow(rownum)==null)
			sheet.createRow(rownum);
		row=sheet.getRow(rownum);
		
		cell=row.createCell(colum);
		cell.setCellValue(data);
		fo=new  FileOutputStream(path);
		workbook.write(fo);
		fi.close();
		fo.close();
		}catch(Exception e)
		{
			
		}
	}
	
	public void fillGreenColour(String sheetName,int rownum,int colum) throws IOException
	{
		fi=new  FileInputStream(path);
		workbook=new XSSFWorkbook(fi);
		sheet=workbook.getSheet(sheetName);
		
		row=sheet.getRow(rownum);
		cell=row.getCell(colum);
		
		 style = workbook.createCellStyle();
		 
	        style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
	        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
	        
	        cell.setCellStyle(style);
	        workbook.write(fo);
	        workbook.close();
	        fi.close();
	        fo.close();
	}
	
	
	public void fillRedColour(String sheetName,int rownum,int colum) throws IOException
	{
		fi=new  FileInputStream(path);
		workbook=new XSSFWorkbook(fi);
		sheet=workbook.getSheet(sheetName);
		row=sheet.getRow(rownum);
		cell=row.getCell(colum);
		
		 style = workbook.createCellStyle();
		 
	        style.setFillForegroundColor(IndexedColors.RED.getIndex());
	        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
	        
	        cell.setCellStyle(style);
	        workbook.write(fo);
	        workbook.close();
	        fi.close();
	        fo.close();
	}
	
		
	                                              
}
