package com.qa.opencart.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtil {

	private static Workbook book;
	private static Sheet sheet;

	public static Object[][] getExcelData(String sheetName) {

		Object data[][] = null;
		String excelEnv = System.getProperty("excel");
		FileInputStream ip = null;

		if (excelEnv == null) {
			try {
				ip = new FileInputStream("./src/test/resources/Testdata/OpenCartAppTestData.xlsx");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		} else {

			try {
				switch (excelEnv.toLowerCase()) {
				case "qa":
					ip = new FileInputStream("./src/test/resources/Testdata/QA_OpenCartAppTestData.xlsx");
//				book = WorkbookFactory.create(ip);
//				sheet = book.getSheet(sheetName);
					break;

				case "stage":
					ip = new FileInputStream("./src/test/resources/Testdata/STAGE_OpenCartAppTestData.xlsx");
//				book = WorkbookFactory.create(ip);
//				sheet = book.getSheet(sheetName);
					break;

				case "uat":
					ip = new FileInputStream("./src/test/resources/Testdata/UAT_OpenCartAppTestData.xlsx");
//				book = WorkbookFactory.create(ip);
//				sheet = book.getSheet(sheetName);
					break;

				case "dev":
					ip = new FileInputStream("./src/test/resources/Testdata/DEV_OpenCartAppTestData.xlsx");
//				book = WorkbookFactory.create(ip);
//				sheet = book.getSheet(sheetName);
					break;

				default:
				    System.out.println("Please provide correct excel sheet path...");
					break;
				}

			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
		
		try {
			book = WorkbookFactory.create(ip);
			sheet = book.getSheet(sheetName);
		} catch (InvalidFormatException | IOException e) {
			e.printStackTrace();
		}

		data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
		for (int i = 0; i < sheet.getLastRowNum(); i++) {
			for (int j = 0; j < sheet.getRow(0).getLastCellNum(); j++) {
				data[i][j] = sheet.getRow(i + 1).getCell(j).toString();
			}
		}

		return data;
	}
}
