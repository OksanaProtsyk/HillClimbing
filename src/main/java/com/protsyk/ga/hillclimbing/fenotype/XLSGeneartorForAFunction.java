package com.protsyk.ga.hillclimbing.fenotype;

import com.protsyk.ga.hillclimbing.fenotype.DoubleAlgorithmRunner;
import com.protsyk.ga.hillclimbing.function.*;
import com.protsyk.ga.hillclimbing.statistics.AllRunStatistics;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: okpr0814
 * Date: 4/11/17
 * Time: 5:26 PM
 * To change this template use File | Settings | File Templates.
 */
public class XLSGeneartorForAFunction {
    static List<String> paramsNames = new ArrayList<>();

    static {
        paramsNames.add("Poмір популяції");
        paramsNames.add("Кодування");
        paramsNames.add("Відстань");
        paramsNames.add("Окіл");
        paramsNames.add("Epps");
    }

    static List<String> criteriaNamesForSingleRun = new ArrayList<>();

    static {
        criteriaNamesForSingleRun.add("Number of fitness function evaluations");
        criteriaNamesForSingleRun.add("Number of peaks");
        criteriaNamesForSingleRun.add("Peak Ratio");
        criteriaNamesForSingleRun.add("Peak accuracy");
        criteriaNamesForSingleRun.add("Distance accuracy");
        criteriaNamesForSingleRun.add("Extrema Data (Seed data)");
        criteriaNamesForSingleRun.add("Extrema Value (Seed value)");

    }

    static List<String> criteriaNamesForAllRuns = new ArrayList<>();

    static {
        criteriaNamesForAllRuns.add("All peaks found %");
        criteriaNamesForAllRuns.add("Average peaks found");
        criteriaNamesForAllRuns.add("Average NFE");
        criteriaNamesForAllRuns.add("Max NFE");
        criteriaNamesForAllRuns.add("Average PR");
        criteriaNamesForAllRuns.add("Max PR");
        criteriaNamesForAllRuns.add("Average PA");
        criteriaNamesForAllRuns.add("Max PA");
        criteriaNamesForAllRuns.add("Average DA");
        criteriaNamesForAllRuns.add("Max DA");


    }

    static int NUMBER_OF_CRITERIUMS_FOR_ONE_RUN = criteriaNamesForSingleRun.size();
    static int NUMBER_OF_CRITERIUMS_ALL_RUNS = criteriaNamesForAllRuns.size();
    static int RUM_NUMBER = 10;
    List<AbstractFunction> deb1Functions = new ArrayList();
    int[] spaceSize = {1,2,3,4,5};
    int[] populationSizes = {1000,2000,5000,10000};
    double[] epsSizes = {0.001,0.0001,0.00001};
    double[] neighbourSizes = {0.1,0.2,0.3};


    public XSSFWorkbook generateSheet(XSSFWorkbook workbook, AbstractFunction func,boolean GLOBAL) {
        //Create a blank sheet
        String sheetName =func.name()+",n="+func.spaceSize+",R";
        if (GLOBAL){
            sheetName+=",Global";
        }
        XSSFSheet spreadsheet = workbook.createSheet(sheetName);
        XSSFRow row1 = spreadsheet.createRow((short) 1);
        XSSFRow row2 = spreadsheet.createRow((short) 2);
        XSSFRow row3 = spreadsheet.createRow((short) 3);
        XSSFRow row4 = spreadsheet.createRow((short) 4);


        // row.setHeight((short) 800);
        XSSFCell cell11 = (XSSFCell) row1.createCell((short) 1);
        cell11.setCellValue("Конфігурація алгоритму");
        XSSFCell cell12 = (XSSFCell) row1.createCell((short) 6);
        cell12.setCellValue("Набори тестових задач ");
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
        XSSFCell cell21;
        CellStyle styleVertical = workbook.createCellStyle();
        styleVertical.setRotation((short) 90);

        for (int p = 0; p < paramsNames.size(); p++) {
            XSSFCell cell41 = row4.createCell(p + 1);
            cell41.setCellStyle(styleVertical);
            cell41.setCellValue(paramsNames.get(p));
        }
        int m = 0;

        cell21 = (XSSFCell) row2.createCell((short) 6 + m * (NUMBER_OF_CRITERIUMS_FOR_ONE_RUN * RUM_NUMBER + NUMBER_OF_CRITERIUMS_ALL_RUNS));
        cell21.setCellValue("Функція" +func.name()+", n=" + func.spaceSize());


        spreadsheet.addMergedRegion(new CellRangeAddress(
                2, //first row (0-based)
                2, //last row (0-based)
                6 + m * ((NUMBER_OF_CRITERIUMS_FOR_ONE_RUN * RUM_NUMBER + NUMBER_OF_CRITERIUMS_ALL_RUNS)), //first column (0-based)
                6 + ((NUMBER_OF_CRITERIUMS_FOR_ONE_RUN * RUM_NUMBER + NUMBER_OF_CRITERIUMS_ALL_RUNS) - 1) + m * (NUMBER_OF_CRITERIUMS_FOR_ONE_RUN * RUM_NUMBER + NUMBER_OF_CRITERIUMS_ALL_RUNS) //last column (0-based)
        ));

        XSSFCell cell31;
        for (int runN = 0; runN < RUM_NUMBER; runN++) {
            cell31 = row3.createCell((short) 6 + m * ((NUMBER_OF_CRITERIUMS_FOR_ONE_RUN * RUM_NUMBER + NUMBER_OF_CRITERIUMS_ALL_RUNS)) + runN * (NUMBER_OF_CRITERIUMS_FOR_ONE_RUN));
            cell31.setCellValue("Прогін " + (runN + 1));
            System.out.println("Прогін ** " + runN);

            for (int p = 0; p < criteriaNamesForSingleRun.size(); p++) {
                XSSFCell cell41 = row4.createCell(6 + m * ((NUMBER_OF_CRITERIUMS_FOR_ONE_RUN * RUM_NUMBER + NUMBER_OF_CRITERIUMS_ALL_RUNS)) + runN * criteriaNamesForSingleRun.size() + p);
                System.out.println("** " + (6 + m * ((NUMBER_OF_CRITERIUMS_FOR_ONE_RUN * RUM_NUMBER + NUMBER_OF_CRITERIUMS_ALL_RUNS)) + runN * criteriaNamesForSingleRun.size() + (p)));
                cell41.setCellValue(criteriaNamesForSingleRun.get(p));
                cell41.setCellStyle(styleVertical);

            }
            spreadsheet.addMergedRegion(new CellRangeAddress(
                    3, //first row (0-based)
                    3, //last row (0-based)
                    6 + m * ((NUMBER_OF_CRITERIUMS_FOR_ONE_RUN * RUM_NUMBER + NUMBER_OF_CRITERIUMS_ALL_RUNS)) + runN * (NUMBER_OF_CRITERIUMS_FOR_ONE_RUN), //first column (0-based)
                    6 + m * ((NUMBER_OF_CRITERIUMS_FOR_ONE_RUN * RUM_NUMBER + NUMBER_OF_CRITERIUMS_ALL_RUNS)) + runN * (NUMBER_OF_CRITERIUMS_FOR_ONE_RUN) + (NUMBER_OF_CRITERIUMS_FOR_ONE_RUN - 1)//last column (0-based)
            ));

        }
        cell31 = row3.createCell((short) 6 + (m + 1) * ((NUMBER_OF_CRITERIUMS_FOR_ONE_RUN * RUM_NUMBER + NUMBER_OF_CRITERIUMS_ALL_RUNS)) - NUMBER_OF_CRITERIUMS_ALL_RUNS);
        cell31.setCellValue("По всіх прогонах ");
        spreadsheet.addMergedRegion(new CellRangeAddress(
                3, //first row (0-based)
                3, //last row (0-based)
                6 + (m + 1) * ((NUMBER_OF_CRITERIUMS_FOR_ONE_RUN * RUM_NUMBER + NUMBER_OF_CRITERIUMS_ALL_RUNS)) - NUMBER_OF_CRITERIUMS_ALL_RUNS, //first column (0-based)
                6 + (m + 1) * ((NUMBER_OF_CRITERIUMS_FOR_ONE_RUN * RUM_NUMBER + NUMBER_OF_CRITERIUMS_ALL_RUNS)) - 1//last column (0-based)
        ));

        for (int p = 0; p < criteriaNamesForAllRuns.size(); p++) {
            System.out.println("+++" + (6 + (m + 1) * ((NUMBER_OF_CRITERIUMS_FOR_ONE_RUN * RUM_NUMBER + NUMBER_OF_CRITERIUMS_ALL_RUNS)) - NUMBER_OF_CRITERIUMS_ALL_RUNS + p));
            XSSFCell cell41 = row4.createCell(6 + (m + 1) * ((NUMBER_OF_CRITERIUMS_FOR_ONE_RUN * RUM_NUMBER + NUMBER_OF_CRITERIUMS_ALL_RUNS)) - NUMBER_OF_CRITERIUMS_ALL_RUNS + p);
            cell41.setCellValue(criteriaNamesForAllRuns.get(p));
            cell41.setCellStyle(styleVertical);
        }


        int rowNumber = 1;
        for (int i = 0; i < populationSizes.length; i++) {
            for (int j = 0; j < epsSizes.length; j++) {
                for (int k = 0; k < neighbourSizes.length; k++) {
                    XSSFRow row5 = spreadsheet.createRow((short) 4 + rowNumber++);

                    XSSFCell cell51 = row5.createCell(1);
                    cell51.setCellValue(populationSizes[i]);

                    XSSFCell cell52 = row5.createCell(2);
                    cell52.setCellValue("Дійсне");
                    XSSFCell cell53 = row5.createCell(3);
                    cell53.setCellValue(neighbourSizes[k]);
                    XSSFCell cell54 = row5.createCell(4);
                    cell54.setCellValue(epsSizes[j]);

                    XSSFCell cell55 = row5.createCell(5);
                    // for (int size = 0; size < deb1Functions.size(); size++) {
                    int size = 0;

                    DoubleAlgorithmRunner runner = new DoubleAlgorithmRunner(func, populationSizes[i], neighbourSizes[k], epsSizes[j]);
                    AllRunStatistics allRunStatistics = runner.run();
                    short sellStartNumber = 0;
                    for (int r = 0; r < allRunStatistics.listOFSinglerRuns.size(); r++) {
                        sellStartNumber = (short) (6 + size * ((NUMBER_OF_CRITERIUMS_FOR_ONE_RUN * RUM_NUMBER + NUMBER_OF_CRITERIUMS_ALL_RUNS)) + r * (NUMBER_OF_CRITERIUMS_FOR_ONE_RUN));
                        row5.createCell((sellStartNumber)).setCellValue(allRunStatistics.listOFSinglerRuns.get(r).NFE);

                        XSSFCell cell57 = row5.createCell((short) (sellStartNumber + 1));
                        cell57.setCellValue(GLOBAL ?
                                allRunStatistics.listOFSinglerRuns.get(r).numberolGlobalPeaks:
                                allRunStatistics.listOFSinglerRuns.get(r).numberOfPeaks);

                        XSSFCell cell58 = row5.createCell((short) (sellStartNumber + 2));
                        cell58.setCellValue(GLOBAL?
                                allRunStatistics.listOFSinglerRuns.get(r).globalPeakRatio
                                :allRunStatistics.listOFSinglerRuns.get(r).peakRatio);

                        XSSFCell cell59 = row5.createCell((short) (sellStartNumber + 3));
                        cell59.setCellValue(GLOBAL?
                                allRunStatistics.listOFSinglerRuns.get(r).globalPeakAccurancy
                                :allRunStatistics.listOFSinglerRuns.get(r).peakAccurancy);

                        XSSFCell cell510 = row5.createCell((short) (sellStartNumber + 4));
                        cell510.setCellValue(GLOBAL?
                                allRunStatistics.listOFSinglerRuns.get(r).globalDistanceAccurancy
                               :allRunStatistics.listOFSinglerRuns.get(r).distanceAccurancy);

                        XSSFCell cell511 = row5.createCell(sellStartNumber + 5);
                        String s = "";
                        Set<double[]> l =GLOBAL?
                                allRunStatistics.listOFSinglerRuns.get(r).globalSeeds.keySet():
                                allRunStatistics.listOFSinglerRuns.get(r).foundseeds.keySet();
                        for (double[] d : l ){
                            s += Arrays.toString(d);
                        }
                        cell511.setCellValue(s);

                        XSSFCell cell512 = row5.createCell(sellStartNumber + 6);
                        cell512.setCellValue(GLOBAL?
                                allRunStatistics.listOFSinglerRuns.get(r).globalSeeds.values().toString()
                                :allRunStatistics.listOFSinglerRuns.get(r).foundseeds.values().toString()
                        );

                        short index = (short) (6 + (size + 1) * (NUMBER_OF_CRITERIUMS_FOR_ONE_RUN * RUM_NUMBER + NUMBER_OF_CRITERIUMS_ALL_RUNS) - NUMBER_OF_CRITERIUMS_ALL_RUNS);
                        XSSFCell cell5a = row5.createCell(index);
                        System.out.println(size + " )))  " + index);

                        cell5a.setCellValue(GLOBAL?
                                allRunStatistics.globalPercentOfAllPeakFound
                                :allRunStatistics.percentOfAllPeakFound);
                        XSSFCell cell5b = row5.createCell(index + 1);
                        cell5b.setCellValue(GLOBAL?
                                allRunStatistics.globalAvaragePeakFound
                                :allRunStatistics.avaragePeakFound);

                        XSSFCell cell5c = row5.createCell(index + 2);
                        cell5c.setCellValue(allRunStatistics.avarageNFE);
                        XSSFCell cell5d = row5.createCell(index + 3);
                        cell5d.setCellValue(allRunStatistics.maxNFE);

                        XSSFCell cell5e = row5.createCell(index + 4);
                        cell5e.setCellValue(GLOBAL?
                                allRunStatistics.globalAvaragePR:
                                allRunStatistics.avaragePR);

                        XSSFCell cell5i = row5.createCell(index + 5);
                        cell5i.setCellValue(GLOBAL?
                                allRunStatistics.globalMaxPR
                                :allRunStatistics.maxPR);
                        XSSFCell cell5j = row5.createCell(index + 6);
                        cell5j.setCellValue(GLOBAL?
                                allRunStatistics.globalAvaragePA
                                :allRunStatistics.avaragePA);
                        XSSFCell cell5k = row5.createCell(index + 7);
                        cell5k.setCellValue(GLOBAL?
                                allRunStatistics.globalMaxPA
                                :allRunStatistics.maxPA);

                        XSSFCell cell5l = row5.createCell(index + 8);
                        cell5l.setCellValue(GLOBAL?
                                allRunStatistics.globalAvarageDA
                                :allRunStatistics.avarageDA);
                        XSSFCell cell5m = row5.createCell(index + 9);
                        cell5m.setCellValue(GLOBAL?
                                allRunStatistics.globalMaxDA
                                :allRunStatistics.maxDA);


                    }


                }
            }
        }

        return workbook;
    }

    public void generateXLSX() {
      /*  for (int i = 0; i < spaceSize.length; i++) {
            deb1Functions.add(new Deb1Function(spaceSize[i]));
        }

        for (int i = 0; i < spaceSize.length; i++) {
            deb1Functions.add(new Deb2Function(spaceSize[i]));
        }
        for (int i = 0; i < spaceSize.length; i++) {
            deb1Functions.add(new Deb3Function(spaceSize[i]));
        }
        for (int i = 0; i < spaceSize.length; i++) {
            deb1Functions.add(new Deb4Function(spaceSize[i]));
        }
        for (int i = 0; i < spaceSize.length; i++) {
            deb1Functions.add(new RastriginFunction(spaceSize[i]));
        } */


        for (int i = 0; i < spaceSize.length; i++) {
            deb1Functions.add(new GraiwongFunction(spaceSize[i]));
        }
        for (int i = 0; i < spaceSize.length; i++) {
            deb1Functions.add(new ShubertFunction(spaceSize[i]));
        }

       // deb1Functions.add(new CamelFunction());


        try {
            //Create blank workbook
            XSSFWorkbook workbook = new XSSFWorkbook();
            for (AbstractFunction f:deb1Functions) {
               workbook =  generateSheet(workbook, f, false);
               workbook= generateSheet(workbook, f, true);
            }



            FileOutputStream out = new FileOutputStream(
                    new File("RealFunctions.xlsx"));
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
