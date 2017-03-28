package com.protsyk.ga.hillclimbing.utils;

import com.protsyk.ga.Calc;
import com.protsyk.ga.hillclimbing.function.FitnessFunction;
import com.protsyk.ga.hillclimbing.model.BinaryChromosome;
import com.protsyk.ga.hillclimbing.model.Chromosome;

/**
 * Created with IntelliJ IDEA.
 * User: okpr0814
 * Date: 3/24/17
 * Time: 3:52 PM
 * To change this template use File | Settings | File Templates.
 */
public class BinaryPopulationGenerator implements PopulationGenerator {

    public Chromosome[] generatePopulation(int numberOfChromosome, FitnessFunction fitnessFunction) {
        Chromosome[] population = new Chromosome[numberOfChromosome];
        for (int i = 0; i < numberOfChromosome; i++) {
            population[i] = new BinaryChromosome(fitnessFunction);
        }
        return population;
    }
}
