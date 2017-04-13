package com.protsyk.ga.hillclimbing.statistics;


import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * Created with IntelliJ IDEA.
 * User: okpr0814
 * Date: 4/7/17
 * Time: 2:36 PM
 * To change this template use File | Settings | File Templates.
 */
public class XLSWriter {

    public static void main(String[] args){
        try{
            //Create blank workbook
            XSSFWorkbook workbook = new XSSFWorkbook();
            //Create a blank sheet
            XSSFSheet spreadsheet = workbook.createSheet(
                    "Деба 1");
            //Create row object
            //XSSFRow row;
            //This data needs to be written (Object[])
            XSSFRow row1 = spreadsheet.createRow((short) 1);
            XSSFRow row2 = spreadsheet.createRow((short) 2);

            // row.setHeight((short) 800);
            XSSFCell cell11 = (XSSFCell) row1.createCell((short) 1);
            cell11.setCellValue("Конфігурація алгоритму");
            XSSFCell cell12 = (XSSFCell) row1.createCell((short) 6);
            cell12.setCellValue("Набори тестових задач ");
            XSSFCell cell21 = (XSSFCell) row2.createCell((short) 6);
            cell21.setCellValue("Деба 1, n=1");
            //MEARGING CELLS
            //this statement for merging cells
            spreadsheet.addMergedRegion(new CellRangeAddress(
                    1, //first row (0-based)
                    3, //last row (0-based)
                    1, //first column (0-based)
                    5 //last column (0-based)
            ));
            spreadsheet.addMergedRegion(new CellRangeAddress(
                    1, //first row (0-based)
                    1, //last row (0-based)
                    6, //first column (0-based)
                    1000 //last column (0-based)
            ));
            spreadsheet.addMergedRegion(new CellRangeAddress(
                    2, //first row (0-based)
                    2, //last row (0-based)
                    6, //first column (0-based)
                    80 //last column (0-based)
            ));

            FileOutputStream out = new FileOutputStream(
                    new File("Writesheet.xlsx"));
            workbook.write(out);
            out.close();
            System.out.println(
                    "Writesheet.xlsx written successfully");
    } catch (FileNotFoundException e) {
        e.printStackTrace();
    } catch (IOException e) {
        e.printStackTrace();
    }
    }
}
