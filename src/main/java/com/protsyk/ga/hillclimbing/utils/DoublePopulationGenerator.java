package com.protsyk.ga.hillclimbing.utils;

import com.protsyk.ga.hillclimbing.function.FitnessFunction;
import com.protsyk.ga.hillclimbing.model.Chromosome;
import com.protsyk.ga.hillclimbing.model.DoubleChomosome;

/**
 * Created with IntelliJ IDEA.
 * User: okpr0814
 * Date: 3/24/17
 * Time: 3:54 PM
 * To change this template use File | Settings | File Templates.
 */
public class DoublePopulationGenerator implements PopulationGenerator {
    @Override
    public Chromosome[] generatePopulation(int numberOfChromosome,FitnessFunction fitnessFunction) {
        Chromosome[] population = new Chromosome[numberOfChromosome];
        for (int i = 0; i < numberOfChromosome; i++) {
            population[i] = new DoubleChomosome(fitnessFunction);
        }
        return population;    }
}
