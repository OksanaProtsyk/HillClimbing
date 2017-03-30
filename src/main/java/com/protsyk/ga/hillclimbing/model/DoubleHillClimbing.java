package com.protsyk.ga.hillclimbing.model;

import com.protsyk.ga.Calc;
import com.protsyk.ga.hillclimbing.utils.Utils;

import javax.swing.*;

/**
 * Created with IntelliJ IDEA.
 * User: okpr0814
 * Date: 3/24/17
 * Time: 4:33 PM
 * To change this template use File | Settings | File Templates.
 */
public class DoubleHillClimbing {
    double initialNeighbourhood;
    double eps;
    DoubleChomosome[] population;

    public DoubleHillClimbing(DoubleChomosome[] population, double initialNeighbourhood, double eps) {
        this.population = population;
        this.initialNeighbourhood = initialNeighbourhood;
        this.eps = eps;
    }

    public Chromosome[] run() {
        TwoDFisualizer visual = new TwoDFisualizer(population[0].getFunction());
        boolean change = true;
        while (initialNeighbourhood >= eps) {
            for (int i = 0; i < population.length; i++) {
                //int random = Calc.randomInt(0, population[i].decode().length);
                change = true;
                while (change) {
                    change = false;
                    for (int j = 0; j < population[i].values.length; j++) {
                        double currentFitness = population[i].fitness();
                        population[i].values[j] = population[i].values[j] + initialNeighbourhood ;
                        if (population[i].fitness() < currentFitness) {
                            population[i].values[j] = population[i].values[j] - 2 * initialNeighbourhood;
                            if (population[i].fitness() < currentFitness) {
                                population[i].values[j] = population[i].values[j] + initialNeighbourhood;

                            } else {
                                change = true;
                            }
                        } else {
                            change = true;
                        }

                    }


                }


            }

            //TwoDPrinter.printPopulation(population[0].getFunction(),population,frame);
            visual.printPopulation( population);
            initialNeighbourhood = initialNeighbourhood / 2;

        }
        return population;
    }
}
