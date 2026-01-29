package com.automation.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Excel Reader - Reads test data from Excel files
 */
public class ExcelReader {
    
    private static final Logger logger = LogManager.getLogger(ExcelReader.class);
    private String filePath;
    private Workbook workbook;
    
    public ExcelReader(String filePath) {
        this.filePath = filePath;
        try {
            FileInputStream fis = new FileInputStream(filePath);
            this.workbook = new XSSFWorkbook(fis);
            fis.close();
        } catch (IOException e) {
            logger.error("Error loading Excel file: " + filePath, e);
            throw new RuntimeException("Failed to load Excel file: " + filePath);
        }
    }
    
    /**
     * Gets test data from a specific sheet as a list of maps
     */
    public List<Map<String, String>> getTestData(String sheetName) {
        List<Map<String, String>> testData = new ArrayList<>();
        
        Sheet sheet = workbook.getSheet(sheetName);
        if (sheet == null) {
            throw new RuntimeException("Sheet not found: " + sheetName);
        }
        
        // Get header row
        Row headerRow = sheet.getRow(0);
        if (headerRow == null) {
            throw new RuntimeException("Header row not found in sheet: " + sheetName);
        }
        
        List<String> headers = new ArrayList<>();
        for (Cell cell : headerRow) {
            headers.add(getCellValue(cell));
        }
        
        // Get data rows
        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);
            if (row == null) continue;
            
            Map<String, String> rowData = new HashMap<>();
            for (int j = 0; j < headers.size(); j++) {
                Cell cell = row.getCell(j);
                String value = cell != null ? getCellValue(cell) : "";
                rowData.put(headers.get(j), value);
            }
            testData.add(rowData);
        }
        
        logger.info("Loaded " + testData.size() + " rows from sheet: " + sheetName);
        return testData;
    }
    
    /**
     * Gets test steps for a specific test case
     */
    public List<Map<String, String>> getTestSteps(String sheetName, String testCaseId) {
        List<Map<String, String>> allData = getTestData(sheetName);
        List<Map<String, String>> filteredData = new ArrayList<>();
        
        for (Map<String, String> row : allData) {
            String tcId = row.get("TestCaseID");
            if (tcId != null && tcId.equals(testCaseId)) {
                filteredData.add(row);
            }
        }
        
        logger.info("Found " + filteredData.size() + " steps for test case: " + testCaseId);
        return filteredData;
    }
    
    /**
     * Gets all test case IDs from a sheet
     */
    public List<String> getTestCaseIds(String sheetName) {
        List<Map<String, String>> allData = getTestData(sheetName);
        List<String> testCaseIds = new ArrayList<>();
        
        for (Map<String, String> row : allData) {
            String tcId = row.get("TestCaseID");
            if (tcId != null && !tcId.isEmpty() && !testCaseIds.contains(tcId)) {
                testCaseIds.add(tcId);
            }
        }
        
        logger.info("Found " + testCaseIds.size() + " unique test cases");
        return testCaseIds;
    }
    
    /**
     * Gets a specific cell value
     */
    public String getCellData(String sheetName, int rowNum, int colNum) {
        Sheet sheet = workbook.getSheet(sheetName);
        if (sheet == null) return "";
        
        Row row = sheet.getRow(rowNum);
        if (row == null) return "";
        
        Cell cell = row.getCell(colNum);
        return cell != null ? getCellValue(cell) : "";
    }
    
    /**
     * Gets a specific cell value by column name
     */
    public String getCellData(String sheetName, int rowNum, String columnName) {
        Sheet sheet = workbook.getSheet(sheetName);
        if (sheet == null) return "";
        
        Row headerRow = sheet.getRow(0);
        if (headerRow == null) return "";
        
        int colNum = -1;
        for (int i = 0; i < headerRow.getLastCellNum(); i++) {
            Cell cell = headerRow.getCell(i);
            if (cell != null && getCellValue(cell).equals(columnName)) {
                colNum = i;
                break;
            }
        }
        
        if (colNum == -1) return "";
        
        return getCellData(sheetName, rowNum, colNum);
    }
    
    /**
     * Gets row count for a sheet
     */
    public int getRowCount(String sheetName) {
        Sheet sheet = workbook.getSheet(sheetName);
        return sheet != null ? sheet.getLastRowNum() + 1 : 0;
    }
    
    /**
     * Gets column count for a sheet
     */
    public int getColumnCount(String sheetName) {
        Sheet sheet = workbook.getSheet(sheetName);
        if (sheet == null) return 0;
        
        Row headerRow = sheet.getRow(0);
        return headerRow != null ? headerRow.getLastCellNum() : 0;
    }
    
    /**
     * Writes data to a cell
     */
    public void setCellData(String sheetName, int rowNum, int colNum, String value) {
        Sheet sheet = workbook.getSheet(sheetName);
        if (sheet == null) return;
        
        Row row = sheet.getRow(rowNum);
        if (row == null) {
            row = sheet.createRow(rowNum);
        }
        
        Cell cell = row.getCell(colNum);
        if (cell == null) {
            cell = row.createCell(colNum);
        }
        
        cell.setCellValue(value);
        
        try {
            FileOutputStream fos = new FileOutputStream(filePath);
            workbook.write(fos);
            fos.close();
        } catch (IOException e) {
            logger.error("Error writing to Excel file", e);
        }
    }
    
    /**
     * Writes data to a cell by column name
     */
    public void setCellData(String sheetName, int rowNum, String columnName, String value) {
        Sheet sheet = workbook.getSheet(sheetName);
        if (sheet == null) return;
        
        Row headerRow = sheet.getRow(0);
        if (headerRow == null) return;
        
        int colNum = -1;
        for (int i = 0; i < headerRow.getLastCellNum(); i++) {
            Cell cell = headerRow.getCell(i);
            if (cell != null && getCellValue(cell).equals(columnName)) {
                colNum = i;
                break;
            }
        }
        
        if (colNum != -1) {
            setCellData(sheetName, rowNum, colNum, value);
        }
    }
    
    /**
     * Gets all sheet names
     */
    public List<String> getSheetNames() {
        List<String> sheetNames = new ArrayList<>();
        for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
            sheetNames.add(workbook.getSheetName(i));
        }
        return sheetNames;
    }
    
    /**
     * Checks if a sheet exists
     */
    public boolean sheetExists(String sheetName) {
        return workbook.getSheet(sheetName) != null;
    }
    
    /**
     * Helper method to get cell value as string
     */
    private String getCellValue(Cell cell) {
        if (cell == null) return "";
        
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    return cell.getDateCellValue().toString();
                }
                // Remove decimal for whole numbers
                double numValue = cell.getNumericCellValue();
                if (numValue == Math.floor(numValue)) {
                    return String.valueOf((long) numValue);
                }
                return String.valueOf(numValue);
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            case FORMULA:
                try {
                    return cell.getStringCellValue();
                } catch (Exception e) {
                    return String.valueOf(cell.getNumericCellValue());
                }
            case BLANK:
                return "";
            default:
                return "";
        }
    }
    
    /**
     * Closes the workbook
     */
    public void close() {
        try {
            if (workbook != null) {
                workbook.close();
            }
        } catch (IOException e) {
            logger.error("Error closing workbook", e);
        }
    }
}
