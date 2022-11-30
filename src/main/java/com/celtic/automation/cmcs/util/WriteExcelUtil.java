package com.celtic.automation.cmcs.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;


public class WriteExcelUtil {
	public Logger w;
	public WriteExcelUtil(Logger w) {
		this.w=w;
	}
	private Map<String, Integer> columns =null;
	public  void setCellData(String filepath,String sheetname, String columnName, int rownum,String value) throws IOException {
		FileInputStream fis = null;
		 
		Cell cellC=null;;
		Workbook workbook = null;
		Boolean bool = null;
		Sheet newSheet;
		Row row ;
		FileOutputStream out = null;
		String eachcolumnName=null;
		try {
			fis = new FileInputStream(new File(filepath));
			workbook = WorkbookFactory.create(fis);
			for (int i=0; i<workbook.getNumberOfSheets(); i++) {
				bool=workbook.getSheetName(i).toString().equalsIgnoreCase(sheetname);
				if(bool==true) {
					break;
				}
			}
			if(bool==false) {
				newSheet = workbook.createSheet(sheetname);
			}
			else {
				newSheet =workbook.getSheet(sheetname);
			}
			columns	= new HashMap<>();
			if(newSheet.getLastRowNum()<0) {
				row=  newSheet.createRow(0);
			}
			row= newSheet.getRow(rownum);
			
			if(checkIfRowIsEmpty(row)==false) {
				row= newSheet.getRow(rownum);
			}
			else {
				row= newSheet.createRow(rownum);
			}
			newSheet.getRow(0).forEach(cellA ->{
                columns.put(cellA.getStringCellValue(), cellA.getColumnIndex());
            });

			if(newSheet.getRow(0).getLastCellNum()<0) {
				newSheet.getRow(0).createCell(0).setCellValue(columnName);
			}
			int lastcellnum=newSheet.getRow(0).getLastCellNum();
			if(columns.isEmpty()) {
				columns.put(columnName, columns.size());
				newSheet.getRow(0).createCell(lastcellnum-1).setCellValue(columnName); // row=0,cell=0 lo setting value
			}
			lastcellnum=newSheet.getRow(0).getLastCellNum();
			for(int j=0;j<lastcellnum;j++) {
				eachcolumnName=newSheet.getRow(0).getCell(j).getStringCellValue().trim();
				if(!(columns.containsKey(eachcolumnName))) {
					columns.put(eachcolumnName, columns.size()); //b=1
				}
					
			}
			if(!(columns.containsKey(columnName))) {
				newSheet.getRow(0).createCell(newSheet.getRow(0).getLastCellNum()).setCellValue(columnName);
				columns.put(columnName, columns.size()); //b=1
			}

			if(newSheet.getRow(rownum).getLastCellNum()<0) {
				newSheet.getRow(rownum).createCell(0).setCellValue(value);
			}
			else if(newSheet.getRow(rownum).getLastCellNum()==1){
				newSheet.getRow(rownum).createCell(1).setCellValue(value);
			}
			else {
				cellC=newSheet.getRow(rownum).createCell(columns.get(columnName));
			cellC.setCellValue(value);
			}
				out  = new FileOutputStream(new File(filepath));
			workbook.write(out); 
		}
		catch (Exception e) {
			w.error("Error in WriteExcelUtil"+e);
		}

		finally {
			workbook.close();
			out.close();
		}
	}
	private boolean checkIfRowIsEmpty(Row row) {
	    if (row == null) {
	        return true;
	    }
	    if (row.getLastCellNum() <= 0) {
	        return true;
	    }
	    for (int cellNum = row.getFirstCellNum(); cellNum < row.getLastCellNum(); cellNum++) {
	        Cell cell = row.getCell(cellNum);
	        if (cell != null && cell.getCellType() != CellType.BLANK && StringUtils.isNotBlank(cell.toString())) {
	            return false;
	        }
	    }
	    return true;
	}
}