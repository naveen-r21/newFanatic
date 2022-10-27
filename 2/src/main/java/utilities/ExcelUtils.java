package utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import constants.FrameworkConstants;
import customExceptions.ResourceFileException;

public final class ExcelUtils {

	public static Map<String, HashMap<String, String>> readExcelData(String sheetName) {
		HashMap<String, HashMap<String, String>> completeSheetData = new HashMap<>();
		String s3 = null;
		try (FileInputStream file = new FileInputStream(FrameworkConstants.getExcelPath())) {
			
			DataFormatter formatter = new DataFormatter();
			XSSFWorkbook workbook = new XSSFWorkbook(file);
			XSSFSheet sheet = workbook.getSheet(sheetName);
			Row headerow = sheet.getRow(0);
			for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) {
				Row currentRow = sheet.getRow(i);
				HashMap<String, String> currentHash = new HashMap<String, String>();

				for (int j = 0; j < currentRow.getPhysicalNumberOfCells(); j++) {
					Cell currentCell1 = currentRow.getCell(0);
					s3 = currentCell1.getStringCellValue();

					Cell currentCell = currentRow.getCell(j);
					currentHash.put(formatter.formatCellValue(headerow.getCell(j)),
							formatter.formatCellValue(currentCell));
				}

				completeSheetData.put(s3, currentHash);
			}

		} catch (FileNotFoundException e) {
			throw new ResourceFileException("Unable to Interact with " + sheetName
					+ " as the Excel File is not Found in the path: " + FrameworkConstants.getExcelPath());

		} catch (IOException e) {
			throw new ResourceFileException("Error Occured during Interacting with the ExcelSheet");

		} catch (Exception e) {
			throw new ResourceFileException("Unable to Read Test Data from Excel. " + e.getMessage());
		}

		return completeSheetData;
	}

	public static List<Map<String, String>> getTestDetailsForInterceptor(String sheetName) {
		List<Map<String, String>> list = null;

		try (FileInputStream fs = new FileInputStream(FrameworkConstants.getExcelPath())) {

			XSSFWorkbook workbook = new XSSFWorkbook(fs);
			XSSFSheet sheet = workbook.getSheet(sheetName);

			int lastrownum = sheet.getLastRowNum();
			int lastcolnum = sheet.getRow(0).getLastCellNum();

			Map<String, String> map = null;
			list = new ArrayList<>();

			for (int i = 1; i <= lastrownum; i++) {
				map = new HashMap<>();
				for (int j = 0; j < lastcolnum; j++) {
					String key = sheet.getRow(0).getCell(j).getStringCellValue();
					String value = sheet.getRow(i).getCell(j).getStringCellValue();
					map.put(key, value);
				}
				list.add(map);
			}

		} catch (FileNotFoundException e) {
			throw new ResourceFileException("Unable to Interact with " + sheetName
					+ " as the Excel File is not Found in the path: " + FrameworkConstants.getExcelPath());

		} catch (IOException e) {
			throw new ResourceFileException(
					"Error Occured during Interaction with " + sheetName + " Sheet in Excel File");

		} catch (Exception e) {
			throw new ResourceFileException("Unable to Read Test Data from Excel. " + e.getMessage());
		}

		return list;
	}

}
