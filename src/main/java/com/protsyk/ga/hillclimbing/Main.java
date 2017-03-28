package com.protsyk.ga.hillclimbing;

import com.protsyk.ga.hillclimbing.function.Deb2Function;
import com.protsyk.ga.hillclimbing.function.GriewangkFunction;
import com.protsyk.ga.hillclimbing.model.DoubleChomosome;
import com.protsyk.ga.hillclimbing.model.DoubleHillClimbing;
import com.protsyk.ga.hillclimbing.model.TwoDPrinter;
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
        GriewangkFunction  griewangkFunction = new GriewangkFunction(1);
        DoublePopulationGenerator doublePopulationGenerator = new DoublePopulationGenerator();
        BinaryPopulationGenerator binaryPopulationGenerator = new BinaryPopulationGenerator();
        DoubleChomosome[] doublePopluation =  doublePopulationGenerator.generatePopulation(100, deb2Function);
        Utils.printArr(doublePopluation);
        System.out.println("_________________________________");

        DoubleHillClimbing hillClimbing = new DoubleHillClimbing(doublePopluation,1,0.000000000000000000000000000000000000001);
        Utils.printArr(hillClimbing.run());
        //TwoDPrinter.printPopulation(deb2Function,doublePopluation);


    }
}
