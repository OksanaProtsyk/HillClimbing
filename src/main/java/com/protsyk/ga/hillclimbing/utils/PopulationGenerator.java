package com.protsyk.ga.hillclimbing.utils;

import com.protsyk.ga.hillclimbing.function.FitnessFunction;
import com.protsyk.ga.hillclimbing.model.Chromosome;

/**
 * Created with IntelliJ IDEA.
 * User: okpr0814
 * Date: 3/24/17
 * Time: 3:50 PM
 * To change this template use File | Settings | File Templates.
 */
public interface PopulationGenerator {
     Chromosome[] generatePopulation(int numberOfChromosome,FitnessFunction fitnessFunction);
}
