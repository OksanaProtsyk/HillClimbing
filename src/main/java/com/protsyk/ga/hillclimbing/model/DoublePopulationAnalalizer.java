package com.protsyk.ga.hillclimbing.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: okpr0814
 * Date: 4/5/17
 * Time: 4:40 PM
 * To change this template use File | Settings | File Templates.
 */
public class DoublePopulationAnalalizer {
    public void analyzeFinalPopulation(DoubleChomosome[] population) {

    }

    public int numberOfPelk(DoubleChomosome[] population, double delta, double omega) {
        int numOfPeaks = 0;
        Map<double[], Double> extremas = population[0].getFunction().allMaximas();
        for (double[] data : extremas.keySet()) {
            boolean max = false;
            for (int i = 0; i < population.length; i++) {
                if ((Math.abs(extremas.get(data) - population[i].fitness())) < delta) {
                    boolean valuesAlso = true;
                    for (int j = 0; j < data.length; j++) {
                        if (Math.abs(data[j] - population[i].values[j]) > omega) {
                            valuesAlso = false;
                        }
                    }
                    if (valuesAlso) {
                        max = true;
                    }
                }

            }
            if (max){
                numOfPeaks++;
            }
        }

        return numOfPeaks;
    }

}
