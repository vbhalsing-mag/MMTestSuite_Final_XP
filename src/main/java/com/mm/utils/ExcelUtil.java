package com.mm.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.relevantcodes.extentreports.LogStatus;

public class ExcelUtil extends CommonAction {
    String xlFilePath = System.getProperty("user.dir") + "\\src\\main\\resources\\Form_Data.xlsx";

    ExcelApiTest eat = null;
    List<String> listRowData = null;

    // Logic to get the excel sheet data into object.
    public HashMap<String, List<String>> testData(String sheetName) throws Exception {

        HashMap<String, List<String>> excelData = new HashMap<String, List<String>>();
        // Read excel sheet and get rows and column count
        eat = new ExcelApiTest(xlFilePath);
        int rows = eat.getRowCount(sheetName);
        int columns = eat.getColumnCount(sheetName);

        // Browse through all columns
        for (int j = 0; j < columns; j++) {
            // Read the ColumnName
            String sColumnName = eat.getCellData(sheetName, j, 0).toString().toLowerCase();
            listRowData = new ArrayList<String>();

            // Read All rows
            for (int i = 1; i < rows; i++) {
                listRowData.add(eat.getCellData(sheetName, j, i).toString());
                // ToDo- Check if the field is blank, don't add in list
            }
            // Populate the HashMap

            excelData.put(sColumnName, listRowData);
        }

        return excelData;
    }

    public void downloadExcel() {
        try {
            sleep(6000);
            String[] executionPath = {
                    System.getProperty("user.dir") + "\\src\\main\\java\\autoItScripts\\saveExcel.exe" };
            Runtime.getRuntime().exec(executionPath).waitFor(30, TimeUnit.SECONDS);
            sleep(12000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void writeData(String testCaseId, String columnName, String cellValue, int rowNum, String saveDataFilePath) {

        try {
            String excelFilePath = saveDataFilePath;
            FileInputStream inputStream;
            ExtentReporter.logger.log(LogStatus.INFO,
                    "Note the policy number to use in the next test case - " + testCaseId + " is " + cellValue);
            inputStream = new FileInputStream(new File(excelFilePath));

            sleep(2000);
            XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
            XSSFSheet dataSheet = (XSSFSheet) workbook.getSheet(testCaseId);

            Row headerRow = dataSheet.getRow(0);

            for (int cellNumber = headerRow.getFirstCellNum(); cellNumber <= headerRow.getLastCellNum()
                    - 1; cellNumber++) {
                Cell headerCell = headerRow.getCell(cellNumber);
                if (headerCell.getStringCellValue().toLowerCase().trim().equals(columnName.toLowerCase())) {
                    Row dataRow = dataSheet.getRow(rowNum);
                    Cell dataCell = dataRow.getCell(cellNumber);
                    sleep(2000);
                    dataCell.setCellValue(cellValue);
                    break;
                }

            } // for loop - cellNumber
            sleep(1000);
            inputStream.close();
            FileOutputStream outputStream = new FileOutputStream(excelFilePath);
            workbook.write(outputStream);
            workbook.close();
            outputStream.flush();
            outputStream.close();
            ExtentReporter.logger.log(LogStatus.PASS,
                    "Policy Number " + cellValue + " is written successfully in test case data sheet - " + testCaseId);
        } catch (Exception e) {
            e.printStackTrace();
            ExtentReporter.logger.log(LogStatus.FAIL,
                    "Issue occured while writting value " + cellValue + " in test case data sheet - " + testCaseId);
        }

    }
}
