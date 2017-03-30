package com.protsyk.ga.hillclimbing.model;

import com.protsyk.ga.Calc;

import javax.swing.*;

/**
 * Created with IntelliJ IDEA.
 * User: okpr0814
 * Date: 3/24/17
 * Time: 4:33 PM
 * To change this template use File | Settings | File Templates.
 */
public class BinaryHillClimbing {
    double initialNeighbourhood;
    double eps;
    BinaryChromosome[] population;

    public BinaryHillClimbing(BinaryChromosome[] population, double initialNeighbourhood, double eps) {
        this.population = population;
        this.initialNeighbourhood = initialNeighbourhood;
        this.eps = eps;
    }

    public BinaryChromosome[] run() {
        JFrame frame = new JFrame("sfk");
        TwoDFisualizer visual = new TwoDFisualizer(population[0].getFunction());
        while (initialNeighbourhood > eps) {
            for (int i = 0; i < population.length; i++) {
                int start = Calc.randomInt(0, population[i].bits.length);
                boolean change = true;
                while (change) {
                    change = false;
                    for (int j = start; j < population[i].bits.length; j++) {
                        double currFunc =  population[i].fitness();
                        population[i].bits[j] = 1-population[i].bits[j];
                        if (currFunc>population[i].fitness())  {
                            population[i].bits[j] = 1-population[i].bits[j];
                        }
                        else {
                            change = true;
                        }
                    }
                }
            }
            initialNeighbourhood=initialNeighbourhood/2;
            visual.printPopulation( population);
        }
        return population;
    }
}
