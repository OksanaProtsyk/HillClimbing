package com.protsyk.ga.hillclimbing;

import com.protsyk.ga.hillclimbing.function.*;
import com.protsyk.ga.hillclimbing.model.*;
import com.protsyk.ga.hillclimbing.utils.BinaryPopulationGenerator;
import com.protsyk.ga.hillclimbing.utils.DoublePopulationGenerator;
import com.protsyk.ga.hillclimbing.utils.Utils;

import javax.swing.*;

/**
 * Created with IntelliJ IDEA.
 * User: okpr0814
 * Date: 3/24/17
 * Time: 1:08 PM
 * To change this template use File | Settings | File Templates.
 */
public class Main {

    public static void main(String[] args){
        JFrame frame = new JFrame("a plot panel");

        Deb2Function deb2Function = new Deb2Function(1);
        Deb1Function deb1Function = new Deb1Function(1);
        Deb3Function deb3Function = new Deb3Function(1);

        Deb4Function deb4Function = new Deb4Function(1);
        RastriginFunction rastriginFunction = new RastriginFunction(1);
        GriewangkFunction griewangkFunction = new GriewangkFunction(1);
        ShubertFunction shubertFunction = new ShubertFunction(1);

        Deb1Function deb1Function2 = new Deb1Function(2);

        Deb2Function deb2Function2 = new Deb2Function(2);
        Deb3Function deb3Function2 = new Deb3Function(2);
        Deb4Function deb4Function2 = new Deb4Function(2);
        RastriginFunction rastriginFunction2 = new RastriginFunction(2);
        GriewangkFunction griewangkFunction2 = new GriewangkFunction(2);
        CamelFunction camelFunction = new CamelFunction();
        ShubertFunction shubertFunction2 = new ShubertFunction(2);








        DoublePopulationGenerator doublePopulationGenerator = new DoublePopulationGenerator();
        BinaryPopulationGenerator binaryPopulationGenerator = new BinaryPopulationGenerator();
        DoubleChomosome[] doublePopluation =  doublePopulationGenerator.generatePopulation(1000, shubertFunction);
        BinaryChromosome[] binarypopulation = binaryPopulationGenerator.generatePopulation(10000,deb1Function);
        Utils.printArr(doublePopluation);
        System.out.println("_________________________________");

        DoubleHillClimbing hillClimbing = new DoubleHillClimbing(doublePopluation,0.1,0.001);
       // TwoDFisualizer visual = new TwoDFisualizer(doublePopluation[0].getFunction());
        //visual.printPopulation(doublePopluation);

        Utils.printArr(hillClimbing.run());

        BinaryHillClimbing binaryHillClimbing = new BinaryHillClimbing(binarypopulation,1,0.000001);
        //BinaryChromosome[] finalPopulatin =  binaryHillClimbing.run();
       // Utils.printArr(finalPopulatin);


        //TwoDPrinter.printPopulation(deb2Function,doublePopluation);
        System.out.println("+++++++++++++++++");



    }
}
