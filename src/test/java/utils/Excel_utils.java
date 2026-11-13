package utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;


import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class Excel_utils {

	// ExcelFile----> WorkBook---->Sheet--->Row---> Cell
	// Cell is the actual container to read and write
	//Workbook, Sheet etc are interfaces: Workbook is an interface provided by Apache POI.
	// Workbook defines methods like:getSheet(), getNumberOfSheets(), write(), close( )
	// static keyword:  access these variables directly using the class name


	private static Workbook workbook;
	private static Sheet sheet;
	private static String path = "src/test/resources/test_data/Login_data.xlsx";

	// Open Excel sheet
	public static void openExcel(String sheetName) throws Exception {

		//FileInputStream --- reads bytes from the Excel file on disk.
		FileInputStream fis = new FileInputStream(path);
		// XSSFWorkbook is a concrete class. Provides the actual implementation to Read/write .xlsx files
		workbook = new XSSFWorkbook(fis);  //Apache POI parses those bytes and loads everything into memory
		// fis.close();  so we can close fis
		// searches inside the workbook and returns a Sheet object
		//Selects a sheet by name
		sheet = workbook.getSheet(sheetName);
	}
	// Get last row number dynamically
	public static int getRowCount() {
		//getLastRowNum() is a method of the Sheet interface
		//Returns index of the last row with data
		return sheet.getLastRowNum();
	}

	// Method to read data from a specific row and column in an Excel sheet
	public static String getCellData(int rowNum, int colNum) {
		// Check if the sheet object is initialized
		// If sheet is null, return an empty string to avoid NullPointerException
		if (sheet == null) {
			return "";
		}
		// Get the row from the Excel sheet using the row number
		Row row = sheet.getRow(rowNum);
		// Check if the requested row exists
		// If the row is not present, return an empty string
		if (row == null) {
			return "";
		}
		// Get the cell from the row using the column number
		Cell cell = row.getCell(colNum);
		// Check if the requested cell exists
		// If the cell is empty or not present, return an empty string
		if (cell == null) {
			return "";
		}
		// Create a DataFormatter object
		// This converts any cell type (String, Number, Date, Boolean, etc.)
		// into a readable String format
		DataFormatter formatter = new DataFormatter();
		// Return the formatted cell value as a String
		return formatter.formatCellValue(cell);
	}

	// Updates the Workbook object in JVM memory 
	//This does not Save anything to .xlsx file
	public static void setCellData(int row, int col, String value) {
		Row r = sheet.getRow(row);
		if (r.getCell(col) == null) {
			r.createCell(col);
		}
		r.getCell(col).setCellValue(value);
	}
	// Save and close Excel
	public static void saveAndClose() throws Exception {
		//  FileOutputStream ---> Java Class for writing into a file
		FileOutputStream fos = new FileOutputStream(path);
		//commits those changes to the Excel file.
		workbook.write(fos);
		workbook.close();
		fos.close();
	}

}
