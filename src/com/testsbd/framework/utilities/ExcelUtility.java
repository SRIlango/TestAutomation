package com.testsbd.framework.utilities;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Ranjani.Ilango on 19/06/2017.
 */
public class ExcelUtility
{
    static FileOutputStream outputSheet;
    static XSSFWorkbook workbook;
    static XSSFSheet worksheet;
    static int rownum;
    static CellStyle allCellStyle;
    static Font font;
    static Row row;
    static Cell cell;

    public void Open() {
        workbook = new XSSFWorkbook();
        worksheet = workbook.createSheet("ABD Results");

        font = workbook.createFont();
        font.setBold(true);

        allCellStyle = workbook.createCellStyle();
        allCellStyle.setBorderBottom(BorderStyle.THIN);
        allCellStyle.setBorderTop(BorderStyle.THIN);
        allCellStyle.setBorderLeft(BorderStyle.THIN);
        allCellStyle.setBorderRight(BorderStyle.THIN);
    }

    public void setHeaders()
    {
        CellStyle headerCellStyle = workbook.createCellStyle();
        headerCellStyle.setBorderBottom(BorderStyle.THIN);
        headerCellStyle.setBorderTop(BorderStyle.THIN);
        headerCellStyle.setBorderLeft(BorderStyle.THIN);
        headerCellStyle.setBorderRight(BorderStyle.THIN);
        headerCellStyle.setFillBackgroundColor(IndexedColors.GREY_50_PERCENT.getIndex());
        headerCellStyle.setAlignment(HorizontalAlignment.CENTER);
        headerCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        headerCellStyle.setFont(font);

        int colnum = 0;

        row = worksheet.createRow(rownum);

        cell = row.createCell(colnum++);
        cell.setCellValue("TestCase ID");

        cell = row.createCell(colnum++);
        cell.setCellValue("TestCase Objective");
        //worksheet.setColumnWidth(colnum, 10000);

        cell = row.createCell(colnum++);
        cell.setCellValue("Status");
        //worksheet.setColumnWidth(colnum, 2500);

        cell = row.createCell(colnum++);
        cell.setCellValue("ScreenShot ID");
        //worksheet.setColumnWidth(colnum, 2500);

        row = worksheet.getRow(rownum);
        for (int i=0; i<row.getLastCellNum(); i++)
        {
            cell = row.getCell(i);
            cell.setCellStyle(headerCellStyle);
            worksheet.autoSizeColumn(i);
        }
        rownum = rownum + 1;
    }

    public void UpdateResults(String ScenarioName, boolean ScenarioPassed, String failedScreenshot) throws IOException
    {
        CellStyle statusCellStyle = workbook.createCellStyle();
        statusCellStyle.setBorderBottom(BorderStyle.THIN);
        statusCellStyle.setBorderTop(BorderStyle.THIN);
        statusCellStyle.setBorderLeft(BorderStyle.THIN);
        statusCellStyle.setBorderRight(BorderStyle.THIN);
        statusCellStyle.setFont(font);

        int colnum = 0;

        row = worksheet.createRow(rownum);

        cell = row.createCell(colnum++);
        cell.setCellValue(rownum);
        cell.setCellStyle(allCellStyle);

        cell = row.createCell(colnum++);
        cell.setCellValue(ScenarioName);
        worksheet.autoSizeColumn(colnum);
        cell.setCellStyle(allCellStyle);

        cell = row.createCell(colnum++);
        if(ScenarioPassed)
        {
            statusCellStyle.setFillForegroundColor(IndexedColors.GREEN.getIndex());
            statusCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            cell.setCellStyle(statusCellStyle);
            cell.setCellValue("PASS");
            cell = row.createCell(colnum++);
            cell.setCellStyle(allCellStyle);
        }
        else
        {
            statusCellStyle.setFillForegroundColor(IndexedColors.RED.getIndex());
            statusCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            cell.setCellStyle(statusCellStyle);
            cell.setCellValue("FAIL");
            cell = row.createCell(colnum++);
            cell.setCellValue(failedScreenshot);
            worksheet.autoSizeColumn(colnum);
            cell.setCellStyle(allCellStyle);
        }
        rownum = rownum + 1;
    }

    public void CloseFile(String fileName) throws IOException
    {
        outputSheet = new FileOutputStream(fileName);
        workbook.write(outputSheet);
        outputSheet.close();
    }

    public void ReadRelocInput()
    {
        try {
            FileInputStream RelocInputFile = new FileInputStream("C:\\Automation\\Input\\RelocInput.xslx");
            XSSFWorkbook RelocWorkbook = new XSSFWorkbook(RelocInputFile);
            XSSFSheet RelocSheet = RelocWorkbook.getSheetAt(0);

            ArrayList<String> RelocDetails = new ArrayList <>();
            ArrayList<String> ScenariosNeeded = new ArrayList <>();

            Iterator<Row> rowIterator = RelocSheet.iterator();

            while(rowIterator.hasNext())
            {
                Row row = rowIterator.next();

                Iterator<Cell> cellIterator = row.iterator();
                int i=0;
                while(cellIterator.hasNext())
                {
                    Cell cell = cellIterator.next();
                    i=i+1;
                    if (i==1)
                    {
                        ScenariosNeeded.add(cell.getStringCellValue());
                    }
                    if(i==3)
                    {
                        RelocDetails.add(cell.getStringCellValue());
                    }
                }
            }
        }
        catch(Exception e)
        {

        }
    }

}
