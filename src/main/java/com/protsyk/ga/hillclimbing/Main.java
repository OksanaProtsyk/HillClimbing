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
        Deb1Function deb1Function = new Deb1Function(4);
        Deb3Function deb3Function = new Deb3Function(1);

        Deb4Function deb4Function = new Deb4Function(1);


        GriewangkFunction  griewangkFunction = new GriewangkFunction(1);
        DoublePopulationGenerator doublePopulationGenerator = new DoublePopulationGenerator();
        BinaryPopulationGenerator binaryPopulationGenerator = new BinaryPopulationGenerator();
        DoubleChomosome[] doublePopluation =  doublePopulationGenerator.generatePopulation(10000, deb1Function);
        BinaryChromosome[] binarypopulation = binaryPopulationGenerator.generatePopulation(10000,deb3Function);
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
