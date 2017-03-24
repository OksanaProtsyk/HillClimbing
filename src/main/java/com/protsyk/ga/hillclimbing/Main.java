package com.protsyk.ga.hillclimbing;

import com.protsyk.ga.hillclimbing.function.Deb2Function;
import com.protsyk.ga.hillclimbing.utils.BinaryPopulationGenerator;
import com.protsyk.ga.hillclimbing.utils.DoublePopulationGenerator;
import com.protsyk.ga.hillclimbing.utils.Utils;

/**
 * Created with IntelliJ IDEA.
 * User: okpr0814
 * Date: 3/24/17
 * Time: 1:08 PM
 * To change this template use File | Settings | File Templates.
 */
public class Main {

    public static void main(String[] args){
        Deb2Function deb2Function = new Deb2Function(10);
        DoublePopulationGenerator doublePopulationGenerator = new DoublePopulationGenerator();
        BinaryPopulationGenerator binaryPopulationGenerator = new BinaryPopulationGenerator();
        Utils.printArr(doublePopulationGenerator.generatePopulation(15,deb2Function));
        Utils.printArr(binaryPopulationGenerator.generatePopulation(15,deb2Function));

    }
}
