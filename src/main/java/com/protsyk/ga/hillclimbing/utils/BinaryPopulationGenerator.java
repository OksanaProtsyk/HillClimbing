package com.protsyk.ga.hillclimbing.utils;

import com.protsyk.ga.hillclimbing.function.FitnessFunction;
import com.protsyk.ga.hillclimbing.genotype.BinaryChromosome;
import com.protsyk.ga.hillclimbing.genotype.GrayChromosome;

/**
 * Created with IntelliJ IDEA.
 * User: okpr0814
 * Date: 3/24/17
 * Time: 3:52 PM
 * To change this template use File | Settings | File Templates.
 */
public class BinaryPopulationGenerator implements PopulationGenerator {

    public BinaryChromosome[] generatePopulation(int numberOfChromosome, FitnessFunction fitnessFunction) {
        BinaryChromosome[] population = new BinaryChromosome[numberOfChromosome];
        for (int i = 0; i < numberOfChromosome; i++) {
            population[i] = new BinaryChromosome(fitnessFunction);
        }
        return population;
    }

    public GrayChromosome[] generateGrayPopulation(int numberOfChromosome, FitnessFunction fitnessFunction) {
        GrayChromosome[] population = new GrayChromosome[numberOfChromosome];
        for (int i = 0; i < numberOfChromosome; i++) {
            population[i] = new GrayChromosome(fitnessFunction);
        }
        return population;
    }
}
