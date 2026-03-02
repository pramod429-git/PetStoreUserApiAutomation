package api.utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {
	
	String path= System.getProperty("user.dir")+"//testData//userDataDDT.xlsx";
	ExcelUtility xl=new ExcelUtility(path);
	
	@DataProvider(name="data")
	public String[][] getData() throws IOException{
		
		int rowcount=xl.getRowCount("Sheet1");
		
		int colcount=xl.getCellCount("Sheet1",1 );
		
		 String dataArr [][]= new String[rowcount][colcount];
		 
		 for(int i=1;i<=rowcount;i++) {
			 
			 for(int j=0;j<colcount;j++) {
				 
				 dataArr[i-1][j]=xl.getCellData("Sheet1", i, j);
			 }
		 }
		 
		 return dataArr;
		 
		
	}
	
	@DataProvider(name="username")
	public String[] getUserName() throws IOException {
		
		String path= System.getProperty("user.dir")+"//testData//userDataDDT.xlsx";
		ExcelUtility xl=new ExcelUtility(path);
		
		int rowcount=xl.getRowCount("Sheet1");
		
		String dataArr []= new String[rowcount];
		 
		 for(int i=1;i<=rowcount;i++) {
				 
				 dataArr[i-1]=xl.getCellData("Sheet1", i, 1);
			 }
		 
		 return dataArr;
		
		
	}
	
	
}
