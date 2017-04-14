package com.protsyk.ga.hillclimbing;

import com.protsyk.ga.hillclimbing.function.*;
import com.protsyk.ga.hillclimbing.model.*;
import com.protsyk.ga.hillclimbing.statistics.DoubleAlgorithmRunner;
import com.protsyk.ga.hillclimbing.utils.BinaryPopulationGenerator;
import com.protsyk.ga.hillclimbing.utils.DoublePopulationGenerator;
import com.protsyk.ga.hillclimbing.utils.Utils;

import javax.swing.*;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: okpr0814
 * Date: 3/24/17
 * Time: 1:08 PM
 * To change this template use File | Settings | File Templates.
 */
public class Main {

    public static void main(String[] args) {
        JFrame frame = new JFrame("a plot panel");

        Deb2Function deb2Function = new Deb2Function(1);
        Deb1Function deb1Function = new Deb1Function(1);
        Deb3Function deb3Function = new Deb3Function(1);

        Deb4Function deb4Function = new Deb4Function(1);
        RastriginFunction rastriginFunction = new RastriginFunction(1);
        GriewangkFunction griewangkFunction = new GriewangkFunction(1);
        ShubertFunction shubertFunction = new ShubertFunction(1);

        Deb1Function deb1Function2 = new Deb1Function(2);
        Deb2Function deb2Function5 = new Deb2Function(5);

        Deb2Function deb2Function2 = new Deb2Function(2);
        Deb3Function deb3Function2 = new Deb3Function(2);
        Deb4Function deb4Function2 = new Deb4Function(2);
        RastriginFunction rastriginFunction2 = new RastriginFunction(2);
        GriewangkFunction griewangkFunction2 = new GriewangkFunction(2);
        CamelFunction camelFunction = new CamelFunction();
        ShubertFunction shubertFunction2 = new ShubertFunction(2);
        Deb1Function deb1Function50 = new Deb1Function(50);
        Deb1Function deb1Function20 = new Deb1Function(20);
        Deb1Function deb1Function10 = new Deb1Function(10);





        DoublePopulationGenerator doublePopulationGenerator = new DoublePopulationGenerator();
        BinaryPopulationGenerator binaryPopulationGenerator = new BinaryPopulationGenerator();
        DoubleChomosome[] doublePopluation = doublePopulationGenerator.generatePopulation(1000, griewangkFunction2);
        BinaryChromosome[] binarypopulation = binaryPopulationGenerator.generatePopulation(10000, shubertFunction);
        BinaryChromosome[] graypopulation = binaryPopulationGenerator.generateGrayPopulation(10000, shubertFunction);

        //Utils.printArr(doublePopluation);
        System.out.println("_________________________________");

        DoubleHillClimbing hillClimbing = new DoubleHillClimbing(doublePopluation, 0.1, 0.00001);
        // TwoDFisualizer visual = new TwoDFisualizer(doublePopluation[0].getFunction());
        //visual.printPopulation(doublePopluation);
       /* Map<double[],Double> mapp = deb3Function.allMaximas();
        for (double[] l: mapp.keySet()){
            Utils.printArr(l);
            System.out.println("Va;;" + mapp.get(l));

        }

*/
        hillClimbing.run();

       // DoubleAlgorithmRunner runner = new DoubleAlgorithmRunner(deb1Function2,1000,0.1,0.0001);
       // System.out.println(runner.run());
        //BinaryHillClimbing binaryHillClimbing = new BinaryHillClimbing(binarypopulation, 1, 0.000000000000000000001);

        BinaryHillClimbing grayHillClimbing = new BinaryHillClimbing(graypopulation, 1, 0.000000000000001);
        // BinaryChromosome[] finalPopulatin =  binaryHillClimbing.run();
        // Utils.printArr(finalPopulatin);
        // grayHillClimbing.run();

        int[] arr = {0, 1, 1, 0, 1, 1};

        Utils.printArr(Utils.grayToBinary(arr));
        //TwoDPrinter.printPopulation(deb2Function,doublePopluation);
        System.out.println("+++++++++++++++++");



    }
}
