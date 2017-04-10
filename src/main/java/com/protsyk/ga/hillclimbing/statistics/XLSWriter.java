package com.protsyk.ga.hillclimbing.statistics;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.util.CellRangeAddress;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

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
        FileOutputStream fileOut = new FileOutputStream("poi-test.xls");
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet worksheet = workbook.createSheet("POI Worksheet");

        // index from 0,0... cell A1 is cell(0,0)
        HSSFRow row1 = worksheet.createRow((short) 2);
        int mergerRegion1=  worksheet.addMergedRegion(new CellRangeAddress(2,7,1,6));


        HSSFCellStyle cellStyle = workbook.createCellStyle();
       // cellStyle.setFillForegroundColor(HSSFColor.GOLD.index);
        //cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

        HSSFCell cellB1 = row1.createCell(1);
        cellB1.setCellValue("Конфігурація алгоритму: ");
        cellStyle = workbook.createCellStyle();
        cellStyle.setFillForegroundColor(HSSFColor.WHITE.index);
        cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        cellStyle.setBorderBottom((short)1);
         cellStyle.setBottomBorderColor(HSSFColor.BLACK.index);

        cellB1.setCellStyle(cellStyle);

            /*
        HSSFCell cellC1 = row1.createCell((short) 2);
        cellC1.setCellValue(true);

        HSSFCell cellD1 = row1.createCell((short) 3);
        cellD1.setCellValue(new Date());
        cellStyle = workbook.createCellStyle();
        cellStyle.setDataFormat(HSSFDataFormat
                .getBuiltinFormat("m/d/yy h:mm"));
        cellD1.setCellStyle(cellStyle);
            */

        workbook.write(fileOut);
        fileOut.flush();
        fileOut.close();
    } catch (FileNotFoundException e) {
        e.printStackTrace();
    } catch (IOException e) {
        e.printStackTrace();
    }
    }
}
