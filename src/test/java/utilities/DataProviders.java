package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {
	
	
	//DataProvider 1
	
	@DataProvider(name="LoginData")
	public String [][] getData() throws IOException
	
	
	{  
		String path=".\\testData\\Opencart_LoginData.xlsx";  //taking xl file from data
		
		ExcelUtility xlutil=new ExcelUtility(path); //creating an object for xlutility
		
		int totalrows=xlutil.getRowCount("Sheet1");
		int totalcols=xlutil.getCellCont("Sheet1",1);
		
		String logindata[][]=new String[totalrows][totalcols];//created for two dimentional array
		
		for(int i=1; i<=totalrows;i++) // 1 //read data from xl storing in two dimensional array
		{
			for(int j=0;j<totalcols;j++) //0  // i is rows j is colm
			{
				logindata[i-1][j]= xlutil.getCellData("Sheet1", i, j);
			}
		}
			
		return logindata;	
	}
	
		
	}
	
		
	//DataProvider 2		
			
			
		
		
		
		
	
	
	


