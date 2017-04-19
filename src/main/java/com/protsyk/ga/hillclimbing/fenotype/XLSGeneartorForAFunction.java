package com.protsyk.ga.hillclimbing.fenotype;

import com.protsyk.ga.hillclimbing.fenotype.DoubleAlgorithmRunner;
import com.protsyk.ga.hillclimbing.function.*;
import com.protsyk.ga.hillclimbing.model.AlgorithmConfig;
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
        paramsNames.add("Зміна відстані");
    }

    static List<String> criteriaNamesForSingleRun = new ArrayList<>();

    static {
        criteriaNamesForSingleRun.add("Number of fitness function evaluations");
        criteriaNamesForSingleRun.add("Number of peaks");
        criteriaNamesForSingleRun.add("# Peaks if exact known");
        criteriaNamesForSingleRun.add("Peak Ratio");
        criteriaNamesForSingleRun.add("Exact Peak Ratio");
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
   // int[] spaceSize = {1,2,3,4};
    int[] spaceSize = {5};

    int[] populationSizes = {1000};
    double[] epsSizes = {0.0001, 0.00001, 0.000001};
    double[] changespeeds = {2, 3,1.5};
    double[] neighbourSizes = {0.1, 0.25};


    static List<AlgorithmConfig> config = new ArrayList<>();

    static {
        /*
        config.add(new AlgorithmConfig(1000,0.1,0.0001,1.5));
        config.add(new AlgorithmConfig(1000,0.3,0.00001,2));
        config.add(new AlgorithmConfig(1000,0.1,0.00001,1.5));
        config.add(new AlgorithmConfig(1000,0.25,0.000001,2));
        config.add(new AlgorithmConfig(1000,0.1,0.000001,1.5));
        config.add(new AlgorithmConfig(1000,0.0777,0.000112,1.6271));
        config.add(new AlgorithmConfig(1000,0.03617,0.0003677,1.01655));
          */


        config.add(new AlgorithmConfig(10000,0.1,0.0001,1.5));
        config.add(new AlgorithmConfig(10000,0.3,0.00001,2));
        config.add(new AlgorithmConfig(10000,0.1,0.00001,1.5));
        config.add(new AlgorithmConfig(10000,0.25,0.000001,2));
        config.add(new AlgorithmConfig(10000,0.1,0.000001,1.5));
        config.add(new AlgorithmConfig(10000,0.0777,0.000112,1.6271));
        config.add(new AlgorithmConfig(10000,0.03617,0.0003677,1.01655));

    }

    public void generateXLSX() {
      /*  for (int i = 0; i < spaceSize.length; i++) {
            deb1Functions.add(new Deb1Function(spaceSize[i]));
        }    */

     /* for (int i = 0; i < spaceSize.length; i++) {
            deb1Functions.add(new Deb2Function(spaceSize[i]));
        }   */
      /*
        for (int i = 0; i < spaceSize.length; i++) {
            deb1Functions.add(new Deb3Function(spaceSize[i]));
        }         */

      /*  for (int i = 0; i < spaceSize.length; i++) {
            deb1Functions.add(new Deb4Function(spaceSize[i]));
        }*/
       /* for (int i = 0; i < spaceSize.length; i++) {
            deb1Functions.add(new RastriginFunction(spaceSize[i]));
        }*/


        for (int i = 0; i < spaceSize.length; i++) {
            deb1Functions.add(new GraiwongFunction(spaceSize[i]));
        }


      /*  for (int i = 0; i < spaceSize.length; i++) {
            deb1Functions.add(new ShubertFunction(spaceSize[i]));
        }    */


      //  deb1Functions.add(new CamelFunction());



        try {
            //Create blank workbook
            XSSFWorkbook workbook = new XSSFWorkbook();
            for (AbstractFunction f : deb1Functions) {
                workbook = generateSheet(workbook, f);
            }


            FileOutputStream out = new FileOutputStream(
                    new File("GraiwongReal_5.xlsx"));
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

    public XSSFWorkbook generateSheet(XSSFWorkbook workbook, AbstractFunction func) {
        //Create a blank sheet
        String sheetName = func.name() + ",n=" + func.spaceSize + ",R";
        String globalSheetName = func.name() + ",n=" + func.spaceSize + ",R, Global";

        System.out.println("Running "+sheetName);

        XSSFSheet spreadsheet = workbook.createSheet(sheetName);
        XSSFRow row1 = spreadsheet.createRow((short) 1);
        XSSFRow row2 = spreadsheet.createRow((short) 2);
        XSSFRow row3 = spreadsheet.createRow((short) 3);
        XSSFRow row4 = spreadsheet.createRow((short) 4);

       /*GLOBAL*/
        XSSFSheet globalspreadsheet = workbook.createSheet(globalSheetName);
        XSSFRow globalrow1 = globalspreadsheet.createRow((short) 1);
        XSSFRow globalrow2 = globalspreadsheet.createRow((short) 2);
        XSSFRow globalrow3 = globalspreadsheet.createRow((short) 3);
        XSSFRow globalrow4 = globalspreadsheet.createRow((short) 4);

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


        // row.setHeight((short) 800);
        XSSFCell globalcell11 = (XSSFCell) globalrow1.createCell((short) 1);
        globalcell11.setCellValue("Конфігурація алгоритму");
        XSSFCell globalcell12 = (XSSFCell) globalrow1.createCell((short) 6);
        globalcell12.setCellValue("Набори тестових задач ");
        globalspreadsheet.addMergedRegion(new CellRangeAddress(
                1, //first row (0-based)
                3, //last row (0-based)
                1, //first column (0-based)
                5 //last column (0-based)
        ));
        globalspreadsheet.addMergedRegion(new CellRangeAddress(
                1, //first row (0-based)
                1, //last row (0-based)
                6, //first column (0-based)
                1000 //last column (0-based)
        ));
        XSSFCell globalcell21;
        CellStyle globalstyleVertical = workbook.createCellStyle();
        globalstyleVertical.setRotation((short) 90);


        for (int p = 0; p < paramsNames.size(); p++) {
            XSSFCell cell41 = row4.createCell(p + 1);
            cell41.setCellStyle(styleVertical);
            cell41.setCellValue(paramsNames.get(p));
        }
        int m = 0;

        cell21 = (XSSFCell) row2.createCell((short) 6 + m * (NUMBER_OF_CRITERIUMS_FOR_ONE_RUN * RUM_NUMBER + NUMBER_OF_CRITERIUMS_ALL_RUNS));
        cell21.setCellValue("Функція" + func.name() + ", n=" + func.spaceSize());


        spreadsheet.addMergedRegion(new CellRangeAddress(
                2, //first row (0-based)
                2, //last row (0-based)
                6 + m * ((NUMBER_OF_CRITERIUMS_FOR_ONE_RUN * RUM_NUMBER + NUMBER_OF_CRITERIUMS_ALL_RUNS)), //first column (0-based)
                6 + ((NUMBER_OF_CRITERIUMS_FOR_ONE_RUN * RUM_NUMBER + NUMBER_OF_CRITERIUMS_ALL_RUNS) - 1) + m * (NUMBER_OF_CRITERIUMS_FOR_ONE_RUN * RUM_NUMBER + NUMBER_OF_CRITERIUMS_ALL_RUNS) //last column (0-based)
        ));


        for (int p = 0; p < paramsNames.size(); p++) {
            XSSFCell globalcell41 = globalrow4.createCell(p + 1);
            globalcell41.setCellStyle(globalstyleVertical);
            globalcell41.setCellValue(paramsNames.get(p));
        }


        globalcell21 = (XSSFCell) globalrow2.createCell((short) 6 + m * (NUMBER_OF_CRITERIUMS_FOR_ONE_RUN * RUM_NUMBER + NUMBER_OF_CRITERIUMS_ALL_RUNS));
        globalcell21.setCellValue("Функція" + func.name() + ", n=" + func.spaceSize());


        globalspreadsheet.addMergedRegion(new CellRangeAddress(
                2, //first row (0-based)
                2, //last row (0-based)
                6 + m * ((NUMBER_OF_CRITERIUMS_FOR_ONE_RUN * RUM_NUMBER + NUMBER_OF_CRITERIUMS_ALL_RUNS)), //first column (0-based)
                6 + ((NUMBER_OF_CRITERIUMS_FOR_ONE_RUN * RUM_NUMBER + NUMBER_OF_CRITERIUMS_ALL_RUNS) - 1) + m * (NUMBER_OF_CRITERIUMS_FOR_ONE_RUN * RUM_NUMBER + NUMBER_OF_CRITERIUMS_ALL_RUNS) //last column (0-based)
        ));


        XSSFCell cell31;
        for (int runN = 0; runN < RUM_NUMBER; runN++) {
            cell31 = row3.createCell((short) 6 + m * ((NUMBER_OF_CRITERIUMS_FOR_ONE_RUN * RUM_NUMBER + NUMBER_OF_CRITERIUMS_ALL_RUNS)) + runN * (NUMBER_OF_CRITERIUMS_FOR_ONE_RUN));
            cell31.setCellValue("Прогін " + (runN + 1));
            // System.out.println("Прогін ** " + runN);

            for (int p = 0; p < criteriaNamesForSingleRun.size(); p++) {
                XSSFCell cell41 = row4.createCell(6 + m * ((NUMBER_OF_CRITERIUMS_FOR_ONE_RUN * RUM_NUMBER + NUMBER_OF_CRITERIUMS_ALL_RUNS)) + runN * criteriaNamesForSingleRun.size() + p);
                //System.out.println("** " + (6 + m * ((NUMBER_OF_CRITERIUMS_FOR_ONE_RUN * RUM_NUMBER + NUMBER_OF_CRITERIUMS_ALL_RUNS)) + runN * criteriaNamesForSingleRun.size() + (p)));
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
            //System.out.println("+++" + (6 + (m + 1) * ((NUMBER_OF_CRITERIUMS_FOR_ONE_RUN * RUM_NUMBER + NUMBER_OF_CRITERIUMS_ALL_RUNS)) - NUMBER_OF_CRITERIUMS_ALL_RUNS + p));
            XSSFCell cell41 = row4.createCell(6 + (m + 1) * ((NUMBER_OF_CRITERIUMS_FOR_ONE_RUN * RUM_NUMBER + NUMBER_OF_CRITERIUMS_ALL_RUNS)) - NUMBER_OF_CRITERIUMS_ALL_RUNS + p);
            cell41.setCellValue(criteriaNamesForAllRuns.get(p));
            cell41.setCellStyle(styleVertical);
        }


        XSSFCell globalcell31;
        for (int runN = 0; runN < RUM_NUMBER; runN++) {
            globalcell31 = globalrow3.createCell((short) 6 + m * ((NUMBER_OF_CRITERIUMS_FOR_ONE_RUN * RUM_NUMBER + NUMBER_OF_CRITERIUMS_ALL_RUNS)) + runN * (NUMBER_OF_CRITERIUMS_FOR_ONE_RUN));
            globalcell31.setCellValue("Прогін " + (runN + 1));
            //System.out.println("Прогін ** " + runN);

            for (int p = 0; p < criteriaNamesForSingleRun.size(); p++) {
                XSSFCell globalcell41 = globalrow4.createCell(6 + m * ((NUMBER_OF_CRITERIUMS_FOR_ONE_RUN * RUM_NUMBER + NUMBER_OF_CRITERIUMS_ALL_RUNS)) + runN * criteriaNamesForSingleRun.size() + p);
                //System.out.println("** " + (6 + m * ((NUMBER_OF_CRITERIUMS_FOR_ONE_RUN * RUM_NUMBER + NUMBER_OF_CRITERIUMS_ALL_RUNS)) + runN * criteriaNamesForSingleRun.size() + (p)));
                globalcell41.setCellValue(criteriaNamesForSingleRun.get(p));
                globalcell41.setCellStyle(styleVertical);

            }
            globalspreadsheet.addMergedRegion(new CellRangeAddress(
                    3, //first row (0-based)
                    3, //last row (0-based)
                    6 + m * ((NUMBER_OF_CRITERIUMS_FOR_ONE_RUN * RUM_NUMBER + NUMBER_OF_CRITERIUMS_ALL_RUNS)) + runN * (NUMBER_OF_CRITERIUMS_FOR_ONE_RUN), //first column (0-based)
                    6 + m * ((NUMBER_OF_CRITERIUMS_FOR_ONE_RUN * RUM_NUMBER + NUMBER_OF_CRITERIUMS_ALL_RUNS)) + runN * (NUMBER_OF_CRITERIUMS_FOR_ONE_RUN) + (NUMBER_OF_CRITERIUMS_FOR_ONE_RUN - 1)//last column (0-based)
            ));

        }
        globalcell31 = globalrow3.createCell((short) 6 + (m + 1) * ((NUMBER_OF_CRITERIUMS_FOR_ONE_RUN * RUM_NUMBER + NUMBER_OF_CRITERIUMS_ALL_RUNS)) - NUMBER_OF_CRITERIUMS_ALL_RUNS);
        globalcell31.setCellValue("По всіх прогонах ");
        globalspreadsheet.addMergedRegion(new CellRangeAddress(
                3, //first row (0-based)
                3, //last row (0-based)
                6 + (m + 1) * ((NUMBER_OF_CRITERIUMS_FOR_ONE_RUN * RUM_NUMBER + NUMBER_OF_CRITERIUMS_ALL_RUNS)) - NUMBER_OF_CRITERIUMS_ALL_RUNS, //first column (0-based)
                6 + (m + 1) * ((NUMBER_OF_CRITERIUMS_FOR_ONE_RUN * RUM_NUMBER + NUMBER_OF_CRITERIUMS_ALL_RUNS)) - 1//last column (0-based)
        ));

        for (int p = 0; p < criteriaNamesForAllRuns.size(); p++) {
            //System.out.println("+++" + (6 + (m + 1) * ((NUMBER_OF_CRITERIUMS_FOR_ONE_RUN * RUM_NUMBER + NUMBER_OF_CRITERIUMS_ALL_RUNS)) - NUMBER_OF_CRITERIUMS_ALL_RUNS + p));
            XSSFCell globalcell41 = globalrow4.createCell(6 + (m + 1) * ((NUMBER_OF_CRITERIUMS_FOR_ONE_RUN * RUM_NUMBER + NUMBER_OF_CRITERIUMS_ALL_RUNS)) - NUMBER_OF_CRITERIUMS_ALL_RUNS + p);
            globalcell41.setCellValue(criteriaNamesForAllRuns.get(p));
            globalcell41.setCellStyle(styleVertical);
        }

        int rowNumber = 1;
        int globalrowNumber = 1;

        //   for (int i = 0; i < populationSizes.length; i++) {
          //  for (int j = 0; j < epsSizes.length; j++) {
           //     for (int k = 0; k < neighbourSizes.length; k++) {
                //    for (int g = 0; g < changespeeds.length; g++) {
                      for ( AlgorithmConfig    a:config){
                        XSSFRow row5 = spreadsheet.createRow((short) 4 + rowNumber++);

                        XSSFCell cell51 = row5.createCell(1);
                        cell51.setCellValue(a.populationSize);

                        XSSFCell cell52 = row5.createCell(2);
                        cell52.setCellValue("Дійсне");
                        XSSFCell cell53 = row5.createCell(3);
                        cell53.setCellValue(a.neighbourhoodSize);
                        XSSFCell cell54 = row5.createCell(4);
                        cell54.setCellValue(a.epsSize);

                        XSSFCell cell55 = row5.createCell(5);
                        cell55.setCellValue(a.changeSpeed);
                        // for (int size = 0; size < deb1Functions.size(); size++) {
                        int size = 0;


                        XSSFRow globalrow5 = globalspreadsheet.createRow((short) 4 + globalrowNumber++);

                        XSSFCell globalcell51 = globalrow5.createCell(1);
                        globalcell51.setCellValue(a.populationSize);

                        XSSFCell globalcell52 = globalrow5.createCell(2);
                        globalcell52.setCellValue("Дійсне");
                        XSSFCell globalcell53 = globalrow5.createCell(3);
                        globalcell53.setCellValue(a.neighbourhoodSize);
                        XSSFCell globalcell54 = globalrow5.createCell(4);
                        globalcell54.setCellValue(a.epsSize);

                        XSSFCell globalcell55 = globalrow5.createCell(5);
                        globalcell55.setCellValue(a.changeSpeed);


                        DoubleAlgorithmRunner runner = new DoubleAlgorithmRunner(func, a.populationSize, a.neighbourhoodSize, a.epsSize,a.changeSpeed);
                        AllRunStatistics allRunStatistics = runner.run();
                        short sellStartNumber = 0;
                        for (int r = 0; r < allRunStatistics.listOFSinglerRuns.size(); r++) {
                            sellStartNumber = (short) (6 + size * ((NUMBER_OF_CRITERIUMS_FOR_ONE_RUN * RUM_NUMBER + NUMBER_OF_CRITERIUMS_ALL_RUNS)) + r * (NUMBER_OF_CRITERIUMS_FOR_ONE_RUN));
                            row5.createCell((sellStartNumber)).setCellValue(allRunStatistics.listOFSinglerRuns.get(r).NFE);

                            XSSFCell cell57 = row5.createCell((short) (sellStartNumber + 1));
                            cell57.setCellValue(allRunStatistics.listOFSinglerRuns.get(r).numberOfPeaks);

                            XSSFCell cell57a = row5.createCell((short) (sellStartNumber + 2));
                            cell57a.setCellValue(allRunStatistics.listOFSinglerRuns.get(r).exactnumberOfPeaks);

                            XSSFCell cell58 = row5.createCell((short) (sellStartNumber + 3));
                            cell58.setCellValue(allRunStatistics.listOFSinglerRuns.get(r).peakRatio);


                            XSSFCell cell58a = row5.createCell((short) (sellStartNumber + 4));
                            cell58a.setCellValue(allRunStatistics.listOFSinglerRuns.get(r).exactRatio);

                            XSSFCell cell59 = row5.createCell((short) (sellStartNumber + 5));
                            cell59.setCellValue(allRunStatistics.listOFSinglerRuns.get(r).peakAccurancy);

                            XSSFCell cell510 = row5.createCell((short) (sellStartNumber + 6));
                            cell510.setCellValue(allRunStatistics.listOFSinglerRuns.get(r).distanceAccurancy);

                            XSSFCell cell511 = row5.createCell(sellStartNumber + 7);
                            String s = "";
                            Set<double[]> l = allRunStatistics.listOFSinglerRuns.get(r).foundseeds.keySet();
                            for (double[] d : l) {
                                s += Arrays.toString(d);
                            }
                            cell511.setCellValue(s);

                            XSSFCell cell512 = row5.createCell(sellStartNumber + 8);
                            cell512.setCellValue(allRunStatistics.listOFSinglerRuns.get(r).foundseeds.values().toString()
                            );

                            short index = (short) (6 + (size + 1) * (NUMBER_OF_CRITERIUMS_FOR_ONE_RUN * RUM_NUMBER + NUMBER_OF_CRITERIUMS_ALL_RUNS) - NUMBER_OF_CRITERIUMS_ALL_RUNS);
                            XSSFCell cell5a = row5.createCell(index);
                            //   System.out.println(size + " )))  " + index);

                            cell5a.setCellValue(allRunStatistics.percentOfAllPeakFound);
                            XSSFCell cell5b = row5.createCell(index + 1);
                            cell5b.setCellValue(allRunStatistics.avaragePeakFound);

                            XSSFCell cell5c = row5.createCell(index + 2);
                            cell5c.setCellValue(allRunStatistics.avarageNFE);
                            XSSFCell cell5d = row5.createCell(index + 3);
                            cell5d.setCellValue(allRunStatistics.maxNFE);

                            XSSFCell cell5e = row5.createCell(index + 4);
                            cell5e.setCellValue(allRunStatistics.avaragePR);

                            XSSFCell cell5i = row5.createCell(index + 5);
                            cell5i.setCellValue(allRunStatistics.maxPR);
                            XSSFCell cell5j = row5.createCell(index + 6);
                            cell5j.setCellValue(allRunStatistics.avaragePA);
                            XSSFCell cell5k = row5.createCell(index + 7);
                            cell5k.setCellValue(allRunStatistics.maxPA);

                            XSSFCell cell5l = row5.createCell(index + 8);
                            cell5l.setCellValue(allRunStatistics.avarageDA);
                            XSSFCell cell5m = row5.createCell(index + 9);
                            cell5m.setCellValue(allRunStatistics.maxDA);


                            globalrow5.createCell((sellStartNumber)).setCellValue(allRunStatistics.listOFSinglerRuns.get(r).NFE);

                            XSSFCell globalcell57 = globalrow5.createCell((short) (sellStartNumber + 1));
                            globalcell57.setCellValue(allRunStatistics.listOFSinglerRuns.get(r).numberolGlobalPeaks);

                            XSSFCell globalcell58 = globalrow5.createCell((short) (sellStartNumber + 2));
                            globalcell58.setCellValue(allRunStatistics.listOFSinglerRuns.get(r).globalPeakRatio);

                            XSSFCell globalcell59 = globalrow5.createCell((short) (sellStartNumber + 3));
                            globalcell59.setCellValue(allRunStatistics.listOFSinglerRuns.get(r).globalPeakAccurancy);

                            XSSFCell globalcell510 = globalrow5.createCell((short) (sellStartNumber + 4));
                            globalcell510.setCellValue(allRunStatistics.listOFSinglerRuns.get(r).globalDistanceAccurancy);

                            XSSFCell globalcell511 = globalrow5.createCell(sellStartNumber + 5);
                            String globals = "";
                            Set<double[]> globall = allRunStatistics.listOFSinglerRuns.get(r).globalSeeds.keySet();
                            for (double[] d : globall) {
                                globals += Arrays.toString(d);
                            }
                            globalcell511.setCellValue(globals);

                            XSSFCell globalcell512 = globalrow5.createCell(sellStartNumber + 6);
                            globalcell512.setCellValue(allRunStatistics.listOFSinglerRuns.get(r).globalSeeds.values().toString()
                            );

                            XSSFCell globalcell5a = globalrow5.createCell(index);
                            //   System.out.println(size + " )))  " + index);

                            globalcell5a.setCellValue(allRunStatistics.globalPercentOfAllPeakFound);
                            XSSFCell globalcell5b = globalrow5.createCell(index + 1);
                            globalcell5b.setCellValue(allRunStatistics.globalAvaragePeakFound);

                            XSSFCell globalcell5c = globalrow5.createCell(index + 2);
                            globalcell5c.setCellValue(allRunStatistics.avarageNFE);
                            XSSFCell globalcell5d = globalrow5.createCell(index + 3);
                            globalcell5d.setCellValue(allRunStatistics.maxNFE);

                            XSSFCell globalcell5e = globalrow5.createCell(index + 4);
                            globalcell5e.setCellValue(allRunStatistics.globalAvaragePR);

                            XSSFCell globalcell5i = globalrow5.createCell(index + 5);
                            globalcell5i.setCellValue(allRunStatistics.globalMaxPR);
                            XSSFCell globalcell5j = globalrow5.createCell(index + 6);
                            globalcell5j.setCellValue(allRunStatistics.globalAvaragePA);
                            XSSFCell globalcell5k = globalrow5.createCell(index + 7);
                            globalcell5k.setCellValue(allRunStatistics.globalMaxPA);

                            XSSFCell globalcell5l = globalrow5.createCell(index + 8);
                            globalcell5l.setCellValue(allRunStatistics.globalAvarageDA);
                            XSSFCell globalcell5m = globalrow5.createCell(index + 9);
                            globalcell5m.setCellValue(allRunStatistics.avarageDA);
                        }
                     //   }
                   // }


               // }
            //}
        }

        return workbook;
    }


}
